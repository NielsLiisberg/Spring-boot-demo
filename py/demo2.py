	
def doGet(uri, queryString):

	resultSet   = dbcon.prepareStatement("select * from navusret00 fetch first 10 rows only").executeQuery()
	
	data = []
	while resultSet.next():
		data.append({
			"usrid": resultSet.getString("usrid"), 
			"usrnam": resultSet.getString("usrnam")
		})
	
	return {"uri": uri, "queryString": queryString, "msg": "Hahaha", "data": data}