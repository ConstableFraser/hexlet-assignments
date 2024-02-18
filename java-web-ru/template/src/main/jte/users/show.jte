@import exercise.dto.users.UserPage
@param UserPage user

<html>
    <head>
        <title>User</title>
    </head>
    <body>
        <h1>${user.getUser()}</h1>
        <p><b>ID: </b> ${user.getUser().getId()}</p> <br>
        <p><b>first name: </b> ${user.getUser().getFirstName()}</p> <br>
        <p><b>last name: </b> ${user.getUser().getLastName()}</p> <br>
        <p><b>email: </b> ${user.getUser().getEmail()}</p> <br>
    </body>
</html>
