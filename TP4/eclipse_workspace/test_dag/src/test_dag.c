#include <stdio.h>
#include "delay_until.h"

int T1_o = 0;
int T2_i = 0;
int T2_o = 0;
int T3_i = 0;
float T3_o = 0;
float T4_i = 0;
int T5_i = 0;
int T6_i = 0;
int T6_o = 0;
int T7_i = 0;

void T1(int * o) {
  printf("In task T1\n");
  printf("\t sending on port o %d\n", *o  );
  *o += 1;;
}

void T2(int i, int * o) {
  printf("In task T2\n");
  printf("\t received on port i %d\n", i  );
  printf("\t sending on port o %d\n", *o  );
  *o += 1;;
}

void T3(int i, float * o) {
  printf("In task T3\n");
  printf("\t received on port i %d\n", i  );
  printf("\t sending on port o %f\n", *o  );
  *o += 0.1;
}

void T4(float i) {
  printf("In task T4\n");
  printf("\t received on port i %f\n", i  );
}

void T5(int i) {
  printf("In task T5\n");
  printf("\t received on port i %d\n", i  );
}

void T6(int i, int * o) {
  printf("In task T6\n");
  printf("\t received on port i %d\n", i  );
  printf("\t sending on port o %d\n", *o  );
  *o += 1;;
}

void T7(int i) {
  printf("In task T7\n");
  printf("\t received on port i %d\n", i  );
}


int main(int argc, char* argv[]) {
  unsigned int base_period = 25;
  unsigned int it=0;
  set_start_time();
  while(1) {
    if ((it*base_period)%50==0){
      T1(&T1_o);      
      T2_i = T1_o;
      T6_i = T1_o;
    }
    if ((it*base_period)%50==0){
      T6(T6_i, &T6_o);      
      T7_i = T6_o;
    }
    if ((it*base_period)%75==0){
      T7(T7_i);      
    }
    if ((it*base_period)%100==0){
      T2(T2_i, &T2_o);      
      T5_i = T2_o;
      T3_i = T2_o;
    }
    if ((it*base_period)%100==0){
      T3(T3_i, &T3_o);      
      T4_i = T3_o;
    }
    if ((it*base_period)%125==0){
      T4(T4_i);      
    }
    if ((it*base_period)%200==0){
      T5(T5_i);      
    }
    it++;
    delay_until(it, base_period);
  }
  return 0;
}
