@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <h1>Users</h1>
        @if(page.getUsers().isEmpty())
            <p>There have not anyone users</p>
        @else
            @for(var user : page.getUsers())
                <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
            @endfor
        @endif
    `
)

