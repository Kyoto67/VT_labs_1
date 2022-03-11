import codecs
fileObj = codecs.open(r"C:\edu\1.xml", "r", "utf_8_sig" )
fileResult = codecs.open(r"C:\edu\result.json", "w", "utf_8_sig")
json=''
parse_raw=[]
parse=[]
tab_count=-1
j=-1
for line in fileObj:
    if not 'version' in line:
        for i in range(len(line)):
            if line[i]=='<':
                if line[i+1]=='/':
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
    json+="    "*tab_count
    json+=line+'\n'
    if line=='}':
        tab_count-=1

fileResult.write(json)
fileObj.close()
fileResult.close()