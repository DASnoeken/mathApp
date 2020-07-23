# import sympy
from sympy import *
M = Matrix([[1,(46)**1,(46)**2,226108065428],[1,(31)**1,(31)**2,102060526733],[1,(-25)**1,(-25)**2,69756285533]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
print("Your number of interest should be : {}".format(M_rref[0][3]))
