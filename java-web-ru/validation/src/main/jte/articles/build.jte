@import exercise.dto.articles.ArticlesPage
@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
content = @`

    @if(page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif

    <form action="/articles" method="post">
        <div class="mb-3">
            <label class="form-label">
                Title
                <input type="text" class="form-control" name="title" value="${page.getTitle()}" />
            </label>
        </div>
        <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control" id="content" name="content" rows="5">${page.getContent()}</textarea>
        </div>
        <input type="submit" value="Create" />
    </form>
`
)
