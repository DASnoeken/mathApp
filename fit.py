# import sympy
from sympy import *
M = Matrix([[1,(-62)**1,(-62)**2,(-62)**3,-39174000709015419871660],[1,(-27)**1,(-27)**2,(-27)**3,-3295474391563744172630],[1,(135)**1,(135)**2,(135)**3,396139040542163344366138],[1,(126)**1,(126)**2,(126)**3,321926659177336358816848]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
print("Your number of interest should be : {}".format(M_rref[0][4]))
