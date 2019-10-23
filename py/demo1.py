	
def doGet(uri, queryString):
	return {"uri":uri, 
			"queryString":queryString, 
			"uriAsArray":uri.split("/"),
			"queryStringAsArray":queryString.split("&"), 
	}