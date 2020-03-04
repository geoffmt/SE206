#!/usr/bin/env python3

import sys

import circuit.circuit as circ
from circuit.cnf import SatVar, Solver, Solution, Cnf
from circuit.circuit import Circuit
from adder import *
from transform import transform

# Implementation hints:
#
# 1) You need to build a *miter circuit* (or rather a miter CNF) in order to
#    compare the two circuits. For the circuit part, you can just use the
#    transform function from Exercise 2 (make sure to use different prefixes
#    for the two circuits, as they will usually have overlapping variable
#    names).
#
# 2) Make sure you cover the following error conditions:
#    * The two circuits have different number of inputs or outputs
#    * The inputs and outputs of the two circuits do not have the same names
#    In these cases you can return (False, None).
#
# 3) Run the test script to see if your code works!


    '''The function check() takes two Circuits as input and performs an equivalence
    check using a SAT solver. it returns a tuple, where the first entry is a
    Boolean value (True for equivalent, False for different) and the second
    value is -- in case of a difference found -- a Solution to the underlying
    SAT problem, representing a counterexample. If the circuits are indeed
    equivalent, the second entry will be None.

    '''

def check(c1: Circuit, c2: Circuit) -> (bool, Solution):
 

    numberOutputs_1 = len(c1.getOutputs())

    # Les deux circuits sont incompatibles
    if (c1.getInputs() == c2.getInputs() and c1.getOutputs() == c2.getOutputs()) == 0:
        return (False, None)


    # Etape 1 : transformation Tseitin des circuits c1 et c2
    cnf1 = transform(c1, 'c1_')
    cnf2 = transform(c2, 'c2_')

    cnf = cnf1 & cnf2


    output_mitter = SatVar('output_mitter')

    # Etape 2 : On connecte les entrées c1 et c2 grâce à connector_in
    for inputs_1 in c1.getInputs():
        connector_in = SatVar(inputs_1) #futur lien entre c2 et c1
        c1_in = SatVar('c1_'+str(inputs_1))
        c2_in = SatVar('c2_'+str(inputs_1))
        cnf = cnf & EQ(c1_in, connector_in) & EQ(c2_in, connector_in)


    # Etape 3 : On connecte les sorties aux différents XOR 
    connector_out_XOR_TAB = []
    for outputs_1 in c1.getOutputs():
        connector_out = SatVar(outputs_1)
        connector_out_XOR_TAB.append(connector_out)
        c1_out = SatVar('c1_'+str(outputs_1))
        c2_out = SatVar('c2_'+str(outputs_1))
        cnf = cnf & XOR(c1_out, c2_out, connector_out)


    # Etape 4 : On connecte les sorties des XOR à la porte logique OR finale 
    if (numberOutputs_1 == 1):
        cnf = cnf & EQ(output_mitter, connector_out_XOR_TAB[0])
    else:
        connector_out_OR_TAB = [SatVar('connector_or'+str(0))]
        cnf = cnf & OR(connector_out_XOR_TAB[0],connector_out_XOR_TAB[1],connector_out_OR_TAB[0])

        for i in range(1,len(connector_out_XOR_TAB)-2):
            connector_out_OR_TAB.append(SatVar('connector_or'+str(i)))
            cnf = cnf & OR(connector_out_XOR_TAB[i+1],connector_out_OR_TAB[i-1],connector_out_OR_TAB[i])

        cnf = cnf & EQ(output_mitter, connector_out_OR_TAB[len(connector_out_XOR_TAB)-3]) # On connecte à la sortie finale



    cnf = cnf & output_mitter

    solution = Solver().solve(cnf)

    if (solution):
        return (False, solution)
    else:
        return (True, None)
