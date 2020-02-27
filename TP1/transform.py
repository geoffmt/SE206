#!/usr/bin/env python3

import sys

import circuit.circuit as circ
from circuit.cnf import SatVar, Solver, Cnf
from circuit.circuit import Circuit
from circuit.circuit import Node
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


def LiteralNode(nd, s, cnf):
    if (nd.getValue()):
        cnf = cnf & s
    else:
        cnf = cnf & ~s
    return cnf

def BinOpNode(nd, s, cnf, pf: str=''):
    x, myCnf1 = transform_recursive(nd.getChild(0), pf)
    y, myCnf2 = transform_recursive(nd.getChild(1), pf)
    cnf = cnf & myCnf1 & myCnf2
    if (nd.getOp() == "&"):
        cnf = cnf & AND(x, y, s)
    elif (nd.getOp() == "^"):
        cnf = cnf & XOR(x, y, s)
    elif (nd.getOp() == "|"):
        cnf = cnf & OR(x, y, s)
    return cnf

def UnOpNode(nd, s, cnf, pf: str=''):
    x, myCnf1 = transform_recursive(nd.getChild(0), pf)
    cnf = cnf & myCnf1
    if (nd.getOp() == "~"):
        cnf = cnf & NOT(x, s)
    return cnf

def transform_recursive(nd: Node, prefix: str=''):

    newCnf = Cnf()

    if type(nd).__name__ == "Variable":
        newSatVarName = SatVar(prefix+nd.getName())
        return newSatVarName, newCnf

    newSatVar = SatVar(prefix+str(nd.getID()))
    if type(nd).__name__ == "Literal":
        newCnf = LiteralNode(nd, newSatVar, newCnf)

    if type(nd).__name__ == "BinOp":
        newCnf = BinOpNode(nd, newSatVar, newCnf)

    if type(nd).__name__ == "UnOp":
        newCnf = UnOpNode(nd, newSatVar, newCnf)

    return newSatVar, newCnf


def transform(c: Circuit, prefix: str='') -> Cnf:

    '''The function transform takes a Circuit c and returns a myCnf obtained by the
    Tseitin transformation of c. The optional prefix string will be used for
    all variable names in the myCnf.
    '''

    myCnf = Cnf()

    inputs = c.getSignals()
    for signal in inputs:
        mySatVar = SatVar(prefix+signal)
        node = c.getEquation(signal)
        child = node.getChildren()

        # Il y a une erreur dans test.py : Did not find value for output signal 's_8' in solution
        # En se rendant dans cra8.crc, on se rend compte qu'il y a une opération d'affection :
        # 	s_8 = x19
        # On crée donc la fonction EQ dans le fichier adder.py qui est simplement
        # une opération d'égalité nécessaire pour passer le test de cra8.crc

        if type(node).__name__ == "Variable":
            SatVarName = SatVar(prefix+node.getName())
            myCnf = myCnf & EQ(SatVarName, mySatVar)

        if type(node).__name__ == "Literal":
            myCnf = LiteralNode(node, mySatVar, myCnf)

        if type(node).__name__ == "BinOp":
            myCnf = BinOpNode(node, mySatVar, myCnf, prefix)

        if type(node).__name__ == "UnOp":
            myCnf = UnOpNode(node, mySatVar, myCnf, prefix)

    return myCnf
