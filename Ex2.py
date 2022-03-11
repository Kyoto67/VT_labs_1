import re
text=input()
search=sorted(re.findall(r'([А-Я]\w+\s[А-Я][.][А-Я][.])',text))
for i in range(len(search)):
    print(search[i][:-4])

