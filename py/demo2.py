	
def doGet(uri, queryString):

	resultSet   = dbcon.prepareStatement("call microdemo.user_list (search => 'sen')").executeQuery()
	
	data = []
	while resultSet.next():
		data.append({
			"id": resultSet.getString("user_id"), 
			"name": resultSet.getString("name")
		})
	
	return {"uri": uri, "queryString": queryString, "data": data}