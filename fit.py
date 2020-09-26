# import sympy
from sympy import *
M = Matrix([[1,(-12)**1,(-12)**2,(-12)**3,(-12)**4,-2492293980569997188882],[1,(91)**1,(91)**2,(91)**3,(91)**4,-7658494049241497719956578],[1,(107)**1,(107)**2,(107)**3,(107)**4,-14660772000586505572379282],[1,(-147)**1,(-147)**2,(-147)**3,(-147)**4,-52969739556038313509251112],[1,(-188)**1,(-188)**2,(-188)**3,(-188)**4,-141527272804702834989738722]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
print("Your number of interest should be : {}".format(M_rref[0][5]))
