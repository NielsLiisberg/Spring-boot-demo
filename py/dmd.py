import json

dmdObj = {}

def readDmd(dmdName):
	with open('./py/demo.dmd') as json_file:  
		dmdObj['portfdemo']  = json.load(json_file)


def buildSQLselect(dmdName, entityName): 
	dmd = dmdObj.get(dmdName)
	entities = dmd['model']['entities']
	entity = entities[entityName] 
	table = entity.get("file", entityName ).replace("/" , ".")
	columns  = entity['columns'] 

	sql = "Select " + ','.join(columns) + " from " + table
	return (sql)
	

def putResultInObj(resultSet):
	data = []
	meta = resultSet.getMetaData()
	while resultSet.next():
		row = {}
		for i in range(1, meta.getColumnCount()+1):
			row[meta.getColumnName(i).lower()] = resultSet.getObject(i)
		data.append(row);
	return data
	

def doGet(uri, queryString):
	path = uri.split("/")
	dmdName = path[3]
	entityName = path[4]

	readDmd(dmdName)
	sql = buildSQLselect(dmdName , entityName)
	print sql
	resultSet = dbcon.prepareStatement(sql).executeQuery()
	return putResultInObj(resultSet)
