import xmltodict,json,codecs
fileObj = codecs.open(r"C:\edu\1.xml", "r", "utf_8_sig" )
fileResult = codecs.open(r"C:\edu\lib.json", "w", "utf_8_sig")
file=fileObj.read()
obj= xmltodict.parse(file)
fileObj.close()
fileResult.write(json.dumps(obj))
fileResult.close()