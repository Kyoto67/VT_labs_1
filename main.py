from openpyxl import load_workbook
from openpyxl.styles import Font
from openpyxl.styles.colors import Color


columns='EFGHIJKLMNOPQRSTUVW'
RED='FF0000'

workbook=load_workbook("C:\\edu\\inf\\lab5\\table_copy.xlsx", data_only= True)
work_sheet = workbook['Лист1']


for stroka in range(4,16):
    for stolb in columns:
        cell_name=stolb+str(stroka)
        cell=work_sheet[f'{cell_name}']
        if(cell.value == 0):
            cell.font = Font(italic=True)
        if(cell.value == 1):
            cell.font = Font(color=RED)

workbook.save("C:\\edu\\inf\\lab5\\expy.xlsx")
workbook.close()