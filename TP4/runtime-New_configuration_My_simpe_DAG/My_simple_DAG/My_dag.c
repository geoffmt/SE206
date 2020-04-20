#include <stdio.h>
#include "delay_until.h"


void T1(int * out) {
  printf("In task T1\n");
  printf("\t sending on port out %d\n", *out  );
}

void T2(int in) {
  printf("In task T2\n");
  printf("\t received on port in %d\n", in  );
}

void T3(int in) {
  printf("In task T3\n");
  printf("\t received on port in %d\n", in  );
}


int main(int argc, char* argv[]) {
  unsigned int base_period = 50;
  unsigned int it=0;
  set_start_time();
  while(1) {
    it++;
    delay_until(it, base_period);
  }
  return 0;
}
