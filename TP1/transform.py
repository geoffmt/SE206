#!/usr/bin/env python3

import sys

import circuit.circuit as circ
from circuit.cnf import SatVar, Solver, Cnf
from circuit.circuit import Circuit
from adder import *

# Implementation hints:
#
# 1) For all inputs, outputs, and named signals of the circuit, it is
#    required that the variable names in the myCnf are the same as the
#    signal names, i.e. after solving the myCnf, it must be possible to
#    obtain the assignment of a circuit signal by indexing the
#    solution with the signal name: a = solution['a']. The variable
#    names for any other internal nds can be chosen freely.  If a
#    prefix is given, then all variable names must begin with this
#    prefix.
#
# 2) In order to implement the transformation, you will need to
#    traverse the circuit graph for all outputs (and named internal
#    signals). Make sure that you do not forget any of the defined
#    nd types. Check the file circuit.py to find out about the
#    possible nd types. You can use the function nd.getID() in
#    order to obtain a unique identifier for a nd.
#
# 3) Test your code! There is a test script named test.py. If your
#    code passes all the tests, there is a good chance that it is
#    correct.


def LiteralNode(nd, s, myCnf):
    if (nd.getValue()):
        myCnf = myCnf & s
    else:
        myCnf = myCnf & ~s

def BinOpNode(nd, s, myCnf, prefix):
    x, myCnf1 = transform_recursive(nd.getChild(0), prefix)
    y, myCnf2 = transform_recursive(nd.getChild(1), prefix)
    myCnf = myCnf & myCnf1 & myCnf2
    if (nd.getOp() == "&"):
        myCnf = myCnf & AND(s, x, y)
    elif (nd.getOp() == "^"):
        myCnf = myCnf & XOR(s, x, y)
    elif (nd.getOp() == "|"):
        myCnf = myCnf & OR(s, x, y)

def UnOpNode(nd, s, myCnf):
    x, myCnf1 = transform_recursive(nd.getChild(0), prefix)
    myCnf = myCnf & myCnf1
    if (nd.getOp() == "~"):
        myCnf = myCnf & NOT(s, x)

def transform_recursive(nd, prefix: str=''):

    myCnf = Cnf()

    s = SatVar(prefix+nd.getID())

    if type(nd).__name__ == "Literal":
        LiteralNode(nd, s, myCnf)
        return s, myCnf

    if type(nd).__name__ == "BinOp":
        BinOpNode(nd, s, myCnf)
        return s, myCnf

    if type(nd).__name__ == "UnOp":
        UnOpNode(nd, s, myCnf)
        return s, myCnf


def transform(c: Circuit, prefix: str='') -> Cnf:

    '''The function transform takes a Circuit c and returns a myCnf obtained by the
    Tseitin transformation of c. The optional prefix string will be used for
    all variable names in the myCnf.
    '''

    myCnf = Cnf()

    inputs = c.getSignals()
    for signal in inputs:
        s = SatVar(prefix+signal)
        nd = c.getEquation(signal)
        child = nd.getChildren()

        if type(nd).__name__ == "Literal":
            LiteralNode(nd, s, myCnf)

        if type(nd).__name__ == "BinOp":
            BinOpNode(nd, s, myCnf)

        if type(nd).__name__ == "UnOp":
            UnOpNode(nd, s, myCnf)

    return myCnf


    # TODO: implement me
    pass
