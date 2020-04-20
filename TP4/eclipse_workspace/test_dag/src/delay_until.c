#include <time.h>
#include "delay_until.h"

#ifdef __MACH__
/* ******** */
/* __MACH__ */

#include <mach/mach_time.h>
#include <mach/mach.h>
#include <mach/clock.h>

/* timing struct for osx */
static struct TimingMach {
    mach_timebase_info_data_t timebase;
    clock_serv_t cclock;
} timing_mach_g;

/* mach clock port */
extern mach_port_t clock_port;

int timing_mach_init (void) {
    int retval = mach_timebase_info(&timing_mach_g.timebase);
    if (retval != 0) return retval;
    retval = host_get_clock_service(mach_host_self(),
                                    CALENDAR_CLOCK, &timing_mach_g.cclock);
    return retval;
}

int clock_gettime(clock_id_t id, struct timespec *tspec) {
    mach_timespec_t mts;
    int retval = 0;
    if (id == CLOCK_REALTIME) {
        retval = clock_get_time(timing_mach_g.cclock, &mts);
        if (retval != 0) return retval;
        tspec->tv_sec = mts.tv_sec;
        tspec->tv_nsec = mts.tv_nsec;
    } else if (id == CLOCK_MONOTONIC) {
        retval = clock_get_time(clock_port, &mts);
        if (retval != 0) return retval;
        tspec->tv_sec = mts.tv_sec;
        tspec->tv_nsec = mts.tv_nsec;
    } else {
        /* only CLOCK_MONOTOIC and CLOCK_REALTIME clocks supported */
        return -1;
    }
    return 0;
}

/* timespec difference (monotonic) right - left */
void timespec_monodiff_rml(struct timespec *ts_out,
                                    const struct timespec *ts_in) {
    /* out = in - out,
       where in > out
     */
    ts_out->tv_sec = ts_in->tv_sec - ts_out->tv_sec;
    ts_out->tv_nsec = ts_in->tv_nsec - ts_out->tv_nsec;
    if (ts_out->tv_nsec < 0) {
        ts_out->tv_sec = ts_out->tv_sec - 1;
        ts_out->tv_nsec = ts_out->tv_nsec + TIMING_GIGA;
    }
}

int clock_nanosleep_abstime(const struct timespec *req) {
    struct timespec ts_delta;
    int retval = clock_gettime(CLOCK_MONOTONIC, &ts_delta);
    if (retval != 0) return retval;
    timespec_monodiff_rml (&ts_delta, req);
    /* mach does not properly return remainder from nanosleep */
    retval = nanosleep(&ts_delta, NULL);
    return retval;
}

/* __MACH__ */
/* ******** */
#endif

struct timespec start_timespec;

void set_start_time()
{
  clock_gettime(CLOCK_MONOTONIC, &start_timespec);
}

void delay_until(unsigned int it, unsigned int period)
{
  struct timespec periodic_timespec;
  unsigned long sec = period/1000;
  unsigned long ns = (period*1000 - (sec * 1000000)) * 1000;
  
  periodic_timespec.tv_sec = start_timespec.tv_sec+ it*sec;
  periodic_timespec.tv_nsec = start_timespec.tv_nsec+ it*ns;

  if(periodic_timespec.tv_nsec >= 1000000000) {
    periodic_timespec.tv_nsec -= 1000000000;
    periodic_timespec.tv_sec++;
  }

#ifdef __MACH__
  clock_nanosleep_abstime(&periodic_timespec);
#else
  clock_nanosleep(CLOCK_MONOTONIC, TIMER_ABSTIME, &periodic_timespec, NULL);
#endif
}
