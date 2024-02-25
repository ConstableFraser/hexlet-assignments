@import exercise.util.NamedRoutes;
@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <a href="${NamedRoutes.postsPath()}">Все посты</a>
        </main>
    </div>
    <div class="mx-5">
        <table class="table table-striped" style="width: auto">
            <tr>
                <td>
                    <b>ID:</b>
                </td>
                <td>
                    ${page.getPost().getId()}
                </td>

            </tr>
            <tr>
                <td>
                    <b>NAME:</b>
                </td>
                <td>
                    ${page.getPost().getName()}
                </td>
            </tr>
            <tr>
                <td>
                    <b>BODY:</b>
                </td>
                <td>
                    ${page.getPost().getBody()}
                </td>
            </tr>
        </table>
    </div>
`
)
