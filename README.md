# About service
The application is a multi-user REST API service for redirecting requests to https://jsonplaceholder.typicode.com/
Service supports the registration process, can handle a lot of users, and has a role-based access model(ROLE_ADMIN has access to all handlers, ROLE_POSTS - /posts/*, ROLE_USERS - /users/**, ROLE_ALBUMS -/albums/..)
The service also implements auditing, which allows you to save the date, time, the user, whether he has access, and request parameters.
The server implements in memory cash.
The H2 database is used for storage. You can also use any other database.
# Allowed endpoints
## /api/users/{userId}/albums
GET returns user's albums by userId.
POST receives userId and JSON objects, save them and returns a JSON object
DELETE deletes albums by userId.
PUT receives albums as a JSON objects and updates albums by userId.
## /api/posts/{postId}/comments
GET returns post's comments by postId.
POST receives userId and JSON objects,save them and returns a JSON objects
DELETE deletes comments by postId.
PUT receives comments as a JSON objects and updates comments by postId.
## /api/posts/{albumId}/photos
GET returns albums's photos by albumId.
POST receives albumId and JSON objects, save them and returns a JSON objects
DELETE deletes photos by albumId.
PUT receives photos as a JSON objects and updates photos by albumId.
## /api/posts
GET returns all posts
POST receives JSON object, save it and returns a JSON object
## /api/posts/{id}
GET returns posts by id.
DELETE deletes posts by id.
PUT receives posts as a JSON objects and updates posts by id.
## /api/users/{userId}/posts
GET returns user's posts by userId.
POST receives userId and JSON objects, save them and returns a JSON object
DELETE deletes posts by userId.
PUT receives posts as a JSON objects and updates posts by userId.
## /api/users/{userId}/todos
GET returns user's todos by userId.
POST receives userId and JSON objects, save them and returns a JSON object
DELETE deletes todos by userId.
PUT receives todos as a JSON objects and updates todos by userId.
## GET /alluser 
returns all users
## POST /user/new
Receives a user as a JSON object and registers a user
## /user/{id}
GET Returns a user with a specified id as a JSON object (where {id} is the id of a user). The server should respond with the 200 (Ok) status code. If a user with a specified id does not exist, the server should respond with 404 (Not found).
DELETE Deletes a user with a specified id.



