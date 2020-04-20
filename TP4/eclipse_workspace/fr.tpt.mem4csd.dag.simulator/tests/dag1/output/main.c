#include <stdio.h>
#include "delay_until.h"

float T1_p1;
float T2_p2;

void T1(float * p1) {
  printf("In task T1\n");
  printf("\t sending on port p1 %f\n", *p1  );
}

void T2(float p2) {
  printf("In task T2\n");
  printf("\t received on port p2 %f\n", p2  );
}


int main(int argc, char* argv[]) {
  unsigned int it=0;
  set_start_time();
  while(1) {
    if ((it*1000)%5000==0){
      T1(&T1_p1);      
      T2_p2=T1_p1;
    }
    if ((it*1000)%1000==0){
      T2(T2_p2);      
    }
    it++;
    delay_until(it, 1000);
  }
  return 0;
}
