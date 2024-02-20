@import gg.jte.Content
@param Content content
@param Content footer = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hexlet Javalin Example</title>
    </head>
    <body>
        <div>
            <h3><a href="/">Main</a> |
            <a href="/users">Users</a></h3>
        </div>
        <hr>
        ${content}
        @if(footer == null)
            <div>
                <hr>
                <h3><a href="https://github.com/ConstableFraser">ConstableFraser [GitHub]</a></h3>
            </div>
        @else
            ${footer}
        @endif
    </body>
</html>
