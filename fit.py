# import sympy
from sympy import *
M = Matrix([[1,(97)**1,(97)**2,(97)**3,(97)**4,551212624325727255936499],[1,(196)**1,(196)**2,(196)**3,(196)**4,10080606913044632591102062],[1,(65)**1,(65)**2,(65)**3,(65)**4,100641946501199826379251],[1,(98)**1,(98)**2,(98)**3,(98)**4,575422387294883021798400],[1,(-139)**1,(-139)**2,(-139)**3,(-139)**4,3083686397428674621077667]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
print("Your number of interest should be : {}".format(M_rref[0][5]))
