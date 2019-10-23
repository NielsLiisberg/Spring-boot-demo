	
def doGet(uri, queryString):

	resultSet   = dbcon.prepareStatement("call qgpl.custlist").executeQuery()
	
	data = []
	while resultSet.next():
		data.append({
			"usrid": resultSet.getString("cusnum"), 
			"usrnam": resultSet.getString("LSTNAM")
		})
	
	return {"uri": uri, "queryString": queryString, "msg": "Hahaha", "data": data}