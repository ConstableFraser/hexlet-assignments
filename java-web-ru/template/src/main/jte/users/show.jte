@import org.example.hexlet.dto.users.UserPage
@param UserPage user

<html>
    <head>
        <title>User</title>
    </head>
    <body>
        <h1>${user}</h1>
        <p><b>ID: </b> ${user.getId()}</p> <br>
        <p><b>first name: </b> ${user.getFirstName()}</p> <br>
        <p><b>last name: </b> ${user.getLastName()}</p> <br>
        <p><b>email: </b> ${user.getEmail()}</p> <br>
    </body>
</html>
