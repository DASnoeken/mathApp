# import sympy
from sympy import *
M = Matrix([[1,(25)**1,(25)**2,-19208830764435703091340199222070729651650757763245292734465],[1,(162)**1,(162)**2,-790317005024527569177000718886346100915920655836504235619225],[1,(-87)**1,(-87)**2,-224173915842997106062885315441836074842904435382871625700833]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
print("Your number of interest should be : {}".format(M_rref[0][3]))
