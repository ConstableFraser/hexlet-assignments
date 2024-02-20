@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <h1>${page.getUser().toString()}</h1>
        <p><b>ID: </b> ${page.getUser().getId()}</p> <br>
        <p><b>first name: </b> ${page.getUser().getFirstName()}</p> <br>
        <p><b>last name: </b> ${page.getUser().getLastName()}</p> <br>
        <p><b>email: </b> ${page.getUser().getEmail()}</p> <br>
    `
)
