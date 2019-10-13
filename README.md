# Crud api Project


# Testing environment
- https://springbootherokumongo.herokuapp.com
- https://springbootherokumongo.herokuapp.com/swagger-ui.html#/

# REST Resource

# GET /places
- Will return the list of registered places

Response Body example:

```json
[
  {
    "city": "string",
    "createdAt": "string",
    "id": "string",
    "name": "string",
    "slug": "string",
    "state": "string",
    "updatedAt": "string",
  }
]
```

# GET /places/cities
- Will return to list of registered places filtered by cities

Path parameters
Parameter:city	Description:city	Parameter Type:query Data Type:String

Response Body example:
```json
[
  {
    "city": "string",
    "createdAt": "string",
    "id": "string",
    "name": "string",
    "slug": "string",
    "state": "string",
    "updatedAt": "string",
  }
]
```

# GET /places/filter/{slug}
- Will return to list of registered places filtered by name(or a part of the name)

Path parameters
Parameter:slug	Description:place name in slug format	Parameter Type:path Data Type:String

Response Body example:
```json
[
  {
    "city": "string",
    "createdAt": "string",
    "id": "string",
    "name": "string",
    "slug": "string",
    "state": "string",
    "updatedAt": "string",
  }
]
```

# GET /places/placename
-Will return a place object
Path parameters
Parameter:name	Description:Place name	Parameter Type:query Data Type:String

```json
Response Body example:
 {
    "city": "string",
    "createdAt": "string",
    "id": "string",
    "name": "string",
    "slug": "string",
    "state": "string",
    "updatedAt": "string",
}
```

  
 -#GET /places/{id}
 Will return a place dto, selected by id
 
Path parameters
Parameter:id	Description:the place id on database	Parameter Type:path  Data Type:String

Response Body example:
```json
{
  "city": "string",
  "name": "string",
  "state": "string"
}
```

# POST /places
- Will insert a place dto

Request Body Example:
```json
{
  "city": "string",
  "name": "string",
  "state": "string"
}
```

Response Body
no content


# DELETE /places/{id}
- Will delete a place in database

Path parameters
Parameter:id	Description:the place id on database	Parameter Type:path  Data Type:String

# PUT /places/{id}
- Will update Place by id 

Path parameters
Parameter:id	Description:the place id on database	Parameter Type:path  Data Type:String

Response Body example:
```json
{
  "city": "string",
  "name": "string",
  "state": "string"
}
```