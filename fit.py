# import sympy
from sympy import *
M = Matrix([[1,(79)**1,(79)**2,(79)**3,-38219235829779163630646866897618446185190758132627],[1,(80)**1,(80)**2,(80)**3,-39700575758615117947081178449311867673691412069095],[1,(119)**1,(119)**2,(119)**3,-131645821312847463405069552747948119541010451679827],[1,(102)**1,(102)**2,(102)**3,-82692253628547422616421490702032751751105238514503]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
print("Your number of interest should be : {}".format(M_rref[0][4]))
