import re,codecs,time,xmltodict,json

#ParseToJson
start_time = time.time()
for i in range(10):
    fileObj = codecs.open(r"C:\edu\inf\lab4\Thursday schedule.xml", "r", "utf_8_sig" )
    fileResult = open(r"C:\edu\inf\lab4\result.json", "w")
    json_final=''
    parse_raw=[]
    parse=[]
    tab_count=-1
    contsruction_count=0
    j=-1
    for line in fileObj:
        if not 'version' in line:
            for i in range(len(line)):
                if line[i]=='<':
                    if line[i+1]=='/':
                        contsruction_count+=1
                        parse_raw.append('}')
                    else:
                        k=i+1
                        s='"'
                        while line[k]!='>' and k!=len(line):
                            s+=line[k]
                            k+=1
                        s+='":'
                        if line[k-1]!='/':
                            s+='{'
                        parse_raw.append(s)
                        k+=1
                        s='"'
                        while k!=len(line) and line[k]!='<':
                            s+=line[k]
                            k+=1
                        s+='"'
                        parse_raw.append(s)

    for l in parse_raw:
        if (l!='""') and (l!='"\n"'):
            parse.append(l)
    for line in parse:
        if line[-1:]=='{':
            tab_count+=1
        json_final+="    "*tab_count
        json_final+=line+'\n'
        if line=='}':
            tab_count-=1

    fileResult.write(json_final)
    fileObj.close()
    fileResult.close()


print("--- %s seconds ---" % (time.time() - start_time))

#ParseToText
start_time = time.time()
for i in range(10):

    fileObj = codecs.open(r"C:\edu\inf\lab4\Thursday schedule.xml", "r", "utf_8_sig")
    fileResult = open(r"C:\edu\inf\lab4\result.txt", "w")
    parse_raw = []
    j = -1
    for line in fileObj:
        for i in range(len(line) - 6):
            if (line[i] + line[i + 1] + line[i + 2] + line[i + 3] + line[i + 4] + line[i + 5] == '<para>'):
                parse_raw.append('')
                j += 1
                l = i + 6
                while (line[l] + line[l + 1] + line[l + 2] + line[l + 3] + line[l + 4] + line[l + 5] + line[
                    l + 6] != '</para>'):
                    parse_raw[j] += line[l]
                    l += 1
    parse = []
    for i in range(len(parse_raw)):
        for j in range(len(parse_raw[i])):
            if (parse_raw[i][j] == '>') and (j != len(parse_raw[i]) - 1):
                s = ''
                k = j + 1
                while (parse_raw[i][k] != '<'):
                    s += parse_raw[i][k]
                    k += 1
                parse_raw[i] = s
                break

    for i in parse_raw:
        if (i not in parse) and (i[0] != '<'):
            if (i[0] != '$'):
                parse.append(i)
            else:
                parse.append(i[-2:])

    for i in range(len(parse)):
        fileResult.write(parse[i] + '\n')
    fileObj.close()
    fileResult.close()

print("--- %s seconds ---" % (time.time() - start_time))

#ex2
start_time = time.time()
for i in range(10):
    fileObj = codecs.open(r"C:\edu\inf\lab4\Thursday schedule.xml", "r", "utf_8_sig")
    fileResult = open(r"C:\edu\inf\lab4\result with lib.json", "w")
    file = fileObj.read()
    obj = xmltodict.parse(file)
    fileObj.close()
    fileResult.write(json.dumps(obj))
    fileResult.close()
print("--- %s seconds ---" % (time.time() - start_time))

#ex3
start_time = time.time()
for i in range(10):
    fileObj = codecs.open(r"C:\edu\inf\lab4\Thursday schedule.xml", "r", "utf_8_sig")
    fileResult = open(r"C:\edu\inf\lab4\result with reg.txt", 'w')
    file = fileObj.read()
    who = re.search(r'[А-Я]\w+\s[А-Я]\w+\s[А-Я]\w+', file).group()
    d = re.findall(r'<title>\w+</title>', file)
    day = re.search(r'[а-я]\w+', d[0], flags=re.IGNORECASE).group()
    when = re.search(r'\d\d:\d\d-\d\d:\d\d', file).group()
    where = re.search(r'\d\d\d\s\w+', file).group()
    address = re.search(r'[А-Я]\w+\s[а-я]\D+\d+\D+\s', file).group()
    science = re.search(r'[А-Я]\w+\s[а-я]\w{5,}', file).group()
    result = day + '\n' + when + '\n' + address + '\n' + where + '\n' + who + '\n' + science
    fileResult.write(result)
    fileObj.close()
    fileResult.close()
print("--- %s seconds ---" % (time.time() - start_time))

<a href="">имя</a>