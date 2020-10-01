# import sympy
from sympy import *
M = Matrix([[1,(-154)**1,(-154)**2,(-154)**3,-417219905939830399210368081474989273673404450923],[1,(-92)**1,(-92)**2,(-92)**3,-89319536136061926310223105411815504035975733621],[1,(-128)**1,(-128)**2,(-128)**3,-239867575919902376178011860198516744424121256873],[1,(196)**1,(196)**2,(196)**3,850653251936928949117529281923934809817634474027]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
print("Your number of interest should be : {}".format(M_rref[0][4]))
input("Press ENTER to quit!")