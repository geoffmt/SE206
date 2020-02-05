#!/usr/bin/env python3

import circuit.circuit as circ
from circuit.cnf import SatVar, Solver, Cnf

# circ full_adder {
#      inputs: a, b, cin
#      outputs: s, cout
#      s0 = a ^ b
#      s = s0 ^ cin
#      cout = (a & b) | (s0 & cin)
# }

# Inputs
a = SatVar('a')
b = SatVar('b')
cin = SatVar('cin')

# Outputs
s = SatVar('s')
cout = SatVar('cout')

# Internal variables (if needed)
s0 = SatVar('s0')
s1 = SatVar('s1')
s2 = SatVar('s2')

def OR(x, y, out):
    return (x|y|~out) & (~x|out) & (~y|out)

def AND(x, y, out):
    return (~x|~y|out) & (x|~out) & (y|~out)

def NOT(x, out):
    return (~x|~out) & (x|out)

def XOR(x,y,out):
    return (~x|~y|~out) & (x|y|~out) & (x|~y|out) & (~x|y|out)

def mk_adder() -> Cnf:
    return AND(a, b, s1) & XOR(a, b, s0) & AND(s0, cin, s2) & XOR(s0, cin, s) & OR(s1, s2, cout)