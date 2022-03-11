import codecs
fileObj = codecs.open(r"C:\edu\inf\lab4\Thursday schedule.xml", "r", "utf_8_sig" )
fileResult = open(r"C:\edu\inf\lab4\result.txt", "w")
parse_raw=[]
j=-1


for line in fileObj:
    for i in range(len(line)-6):
        if(line[i]+line[i+1]+line[i+2]+line[i+3]+line[i+4]+line[i+5]=='<para>'):
            parse_raw.append('')
            j+=1
            l=i+6
            while (line[l]+line[l+1]+line[l+2]+line[l+3]+line[l+4]+line[l+5]+line[l+6]!='</para>'):
                parse_raw[j]+=line[l]
                l+=1
for i in range(len(parse_raw)):
    for j in range(len(parse_raw[i])):
        if(parse_raw[i][j]=='>')and(j!=len(parse_raw[i])-1):
            s=''
            k=j+1
            while(parse_raw[i][k]!='<'):
                s+=parse_raw[i][k]
                k+=1
            parse_raw[i]=s
            break

parse=[]

for i in parse_raw:
    if (i not in parse) and(i[0]!='<'):
        if(i[0]!='$'):
            parse.append(i)
        else:
            parse.append(i[-2:])

for i in range(len(parse)):
    fileResult.write(parse[i]+'\n')
fileObj.close()
fileResult.close()