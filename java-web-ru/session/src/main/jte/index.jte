@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
content = @`
    @if(page.getName() != null)
        <div class="mb-3">
            ${String.valueOf(page.getName())}<br>
            <div class="mx-auto p-4 py-md-5">
                <form action="${NamedRoutes.logoutPath()}" method="post">
                    <input type="submit" class="btn btn-primary" value="Выйти" />
                </form>
            </div>
        </div>
    @else
        <div class="mb-3 p-2">
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        </div>
    @endif
`
)
