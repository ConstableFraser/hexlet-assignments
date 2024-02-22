@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <h3>Search users</h3>
        <form action="/users" method="get">
        <input type="search" name="term" value="${page.getTerm()}" />
        <input type="submit" value="search" />
        </form>
        <hr>
        @if (!page.getUsers().isEmpty())
             <table class="table table-striped">
                 @for(var user : page.getUsers())
                    <tr>
                        <td>
                            ${user.getId()}
                        </td>
                        <td>
                            ${user.getFirstName()} ${user.getLastName()}
                        </td>
                    </tr>
                 @endfor
             </table>
        @else
            <p><i>empty list</i></p>
        @endif
    `
)
