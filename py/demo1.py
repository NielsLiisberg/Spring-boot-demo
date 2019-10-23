def doGet(uri, queryString):

	resultSet   = dbcon.prepareStatement("Select * from microdemo.users_full").executeQuery()
	
	data = []
	while resultSet.next():
		data.append({
			"id": resultSet.getString("user_id"), 
			"name": resultSet.getString("name")
		})
	
	return {"uri": uri, "queryString": queryString, "data": data}