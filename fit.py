# import sympy
from sympy import *
M = Matrix([[1,(-182)**1,(-182)**2,(-182)**3,63727484539540875920725884210098596622200952520],[1,(126)**1,(126)**2,(126)**3,-20567632750825386143166743333571842371709571756],[1,(109)**1,(109)**2,(109)**3,-13278773327382108348188303586066173582127269640],[1,(-35)**1,(-35)**2,(-35)**3,471574263477134770947445423770198627268222632]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
print("Your number of interest should be : {}".format(M_rref[0][4]))
