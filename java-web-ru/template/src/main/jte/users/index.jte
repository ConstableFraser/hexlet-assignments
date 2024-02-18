@import exercise.dto.users.UsersPage
@param UsersPage users

<html>
    <head>
        <title>Users</title>
    </head>
    <body>
        <h1>Users</h1>
        @if(users.getUsers().isEmpty())
            <p>There have not anyone users</p>
        @else
            @for(var user : users.getUsers())
               <div>
                   <h3><a href="/user/${user.getId()}">${user.toString()}</a></h3>
               </div>
            @endfor
        @endif
    </body>
</html>
