import re
result=[]
line=''
while(line!='end'):
    line=input()
    if(line!='end'):
        try:
            group = re.search(r'P3130', line).group()
            NF = re.search(r'[А-Я][.][А-Я][.]', line).group()
            if (NF[0] != NF[2]):
                result.append(line)
        except AttributeError:
            result.append(line)
for i in range(len(result)):
    print(result[i])