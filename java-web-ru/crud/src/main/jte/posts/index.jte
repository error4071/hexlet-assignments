@import exercise.dto.posts.PostsPage
@param PostsPage page

<!-- BEGIN -->
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>User list</title>
</head>
<body>
<div class="mx-auto p-4 py-md-5">
    <main>
        <h1>Users</h1>
        <table class = "table">
            <tr>
                <td>ID</td>
                <td>User</td>
            </tr>
            @for(var post : page.getPosts())
                <tr>
                    <td>${post.getId()}</td>
                    <td><a href="/posts/${post.getId()}">${post.getName()}</a></td>
                </tr>
            @endfor
        </table>
        <div class="container">
            <nav>
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <a class="page-link" href="/posts?page=${Integer.parseInt(page.getTerm()) - 1 > 0 ? Integer.parseInt(page.getTerm()) - 1 : 1}">Prev</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="/posts?page=${Integer.parseInt(page.getTerm()) + 1}">Next</a>
                    </li>
                </ul>
            </nav>
        </div>
    </main>
</div>
</body>
</html>
<!-- END -->