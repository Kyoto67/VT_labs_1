import re

def main(ISU_Number):
    eyes=':;X8='
    nose=['-','<','-{','<{']
    mouth="()O|\/P"
    search_pattern=''
    search_pattern+=eyes[ISU_Number%5]+nose[ISU_Number%4]+mouth[ISU_Number%7]
    counter=0
    search_pattern=re.escape(search_pattern)
    f=open("C:\edu\inf\lab3\Text.txt")
    for line in f:
        result = re.findall(search_pattern,line)
        counter+=len(result)
    return(counter)

def test():
    for i in range(1,6):
        a=int(str(i)*6)
        b=main(a)
        print(b)

test()