@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <p><b>ID: </b> ${page.getUser().getId()}</p>
        <p><b>first name: </b> ${page.getUser().getFirstName()} ${page.getUser().getLastName()}</p>
        <p><b>email: </b> ${page.getUser().getEmail()}</p>
    `
)
