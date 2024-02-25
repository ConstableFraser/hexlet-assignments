@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h3>Все посты</h3>
        </main>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>BODY</th>
                </tr>
            </thead>
            @for(var post : page.getPosts())
                <tr>
                    <td>
                        ${post.getId()}
                    </td>
                    <td>
                        <a href="/posts/${post.getId()}">${post.getName()}</a>
                    </td>
                    <td>
                        @if (post.getBody() != null)
                            ${post.getBody().substring(0, Math.min(20, post.getBody().length())).concat(" <...>")}
                        @endif
                    </td>
                </tr>
            @endfor
            <tfoot>
                <tr>
                    <th colspan="3">
                            @if (page.getCurPos() > 1)
                                <a href="/posts?page=${page.getCurPos()-1}">< назад</a>
                            @endif
                            <a href="/posts?page=${page.getCurPos()+1}">вперед ></a>
                    </th>
                </tr>
            </tfoot>
        </table>
    </div>
`
)
