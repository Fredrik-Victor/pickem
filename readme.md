|HTTP |URL |ACCESS|INFO|

| :--- | :--- | :--- | :--- |

|POST|/roles|ADMIN| Create a role|

|PUT|/roles/{id}|ADMIN| Update existing role with {id}|

|DELETE|/roles/{id}|ADMIN| Delete role with {id}|

|GET|/roles/{id}|ADMIN| Find role with {id}|

|GET|/roles|ADMIN| Find all roles|

JSON-Body for POST:

```JSON

{

  "role": "ROLE_USER"

}

```

JSON-Body for PUT needs ID:

```JSON

{

  "id": 1,

  "role": "ROLE_USER"

}

```

### User:

|HTTP|URL|ACCESS|INFO|

| :--- | :--- |:--- | :--- |

|POST|/users/signup|EVERYBODY| Create a user with USER role|

|PUT|/users/{id}|ADMIN| Update existing user with {id}|

|DELETE|/users/{id}|ADMIN| Delete user with {id}|

|GET|/users/{id}|ADMIN| Find user with {id}|

|GET|/users|ADMIN| Find all users|

JSON-Body for POST:

```JSON

{

  "firstName": "First",

  "lastName": "Lastname",

  "username": "Newuser",

  "password": "password123",

  "email": "test@mail.com"

}

```

JSON-Body for PUT needs ID and Role:

```JSON

{

  "id": 1,

  "firstName": "First",

  "lastName": "Lastname",

  "username": "Newuser",

  "password": "password123",

  "email": "test@mail.com",

  "role": {

    "id": 2,

    "role": "USER"

 }

}

```

### Wines:

|HTTP|URL|ACCESS|INFO|

| :--- | :--- |:--- | :--- |

|POST|/wines|USER/ADMIN| Create a wine|

|PUT|/wines/{id}|USER/ADMIN| Update existing wine with {id}|

|DELETE|/wines/{id}|USER/ADMIN| Delete wine with {id}|

|GET|/wines/{id}|USER/ADMIN| Find wine with {id}|

|GET|/wines|USER/ADMIN| Find all wines|

JSON-Body for POST, adding a grape and country is optional but recommended.

```JSON

{

  "name": "WineExample",

  "grape": {

    "id": 1,

  	"name": "GrapeExample"	},

  "country": {

    "id": 3,

  	"name": "CountryExample"

	}

}

```

JSON-Body for PUT requiers ID and country + grape(if they are added before PUT)

### Grape:

|HTTP|URL|ACCESS|INFO|

| :--- | :--- |:--- | :--- |

|POST|/grapes|USER/ADMIN| Create a grape|

|PUT|/grapes/{id}|USER/ADMIN| Update existing grape with {id}|

|DELETE|/grapes/{id}|USER/ADMIN| Delete grape with {id}|

|GET|/grapes/{id}|USER/ADMIN| Find grape with {id}|

|GET|/grapes|USER/ADMIN| Find all grapes|

JSON-Body for POST, PUT requiers ID aswell

```JSON

{

  "name": "GrapeExample",

  "color": "Red"

}

```

### Country

|HTTP|URL|ACCESS|INFO|

| :--- | :--- |:--- | :--- |

|POST|/countries|USER/ADMIN| Create a country|

|PUT|/countries/{id}|USER/ADMIN| Update existing country with {id}|

|DELETE|/countries/{id}|USER/ADMIN| Delete country with {id}|

|GET|/countries/{id}|USER/ADMIN| Find country with {id}|

|GET|/countries|USER/ADMIN| Find all countries|

JSON-Body for POST, PUT requiers ID aswell

```JSON

{

  "name": "Portugal"

}

```
