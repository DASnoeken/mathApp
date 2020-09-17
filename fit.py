# import sympy
from sympy import *
M = Matrix([[1,(80)**1,(80)**2,910278581122570360195603958544441172139796200789911631],[1,(59)**1,(59)**2,497011816540753727636861240124927316297416732490156553],[1,(-98)**1,(-98)**2,1340248283807808886609166242937210601758141943979257199]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
print("Your number of interest should be : {}".format(M_rref[0][3]))
