# import sympy
from sympy import *
M = Matrix([[1,(594)**1,(594)**2,12704648666162],[1,(-534)**1,(-534)**2,10136589908282],[1,(-847)**1,(-847)**2,25566000680574]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
