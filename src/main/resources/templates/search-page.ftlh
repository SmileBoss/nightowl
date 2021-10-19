<!doctype html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/friends-style.css">
    <link rel="stylesheet" href="/css/search-style.css">
    <link rel="icon" href="/assets/icon-for-site.png">
    <title>Search</title>
</head>
<body>
<div class="container">
    <img class="image-friends" src="/assets/search.svg" alt="">
    <div class="block-btn">
        <a class="btn top-btn" href="/profile">Profile</a>
        <a class="btn top-btn" href="/user/friends">Friends</a>
    </div>
    <div class="search_wrap search_wrap_1">
        <form action="/user/search" method="get">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="search_box">
                <input type="text" name="search" class="input" placeholder="search...">
                <div class="btn-search btn_common">
                    <input class="iSearch" type="submit" value="">
                    <i class="fas fa-search"></i>
                </div>
            </div>
        </form>
    </div>
    <div class="friends-top">
        <table class="table-friends">
            <#list users.content as page>
                <#if page != client>
                    <tr data-href="/profile/${page.id}">
                        <td>
                            <img class="user-icon" src="${page.icon}" alt="">
                        </td>
                        <td>
                            <p>${page.firstname} ${page.lastname}</p>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>

                </#if>
            </#list>
        </table>
    </div>

    <div class="pagination">
<#--        <a class="pag" href="#">&laquo;</a>-->
        <#list 1..users.getTotalPages() as page>
            <#if (page - 1) == users.getNumber()>
                <a href="/user/search?page=${page}" class="pag active">${page}</a>
            <#else>
                <a class="pag" href="/user/search?page=${page - 1}">${page}</a>
            </#if>
        </#list>
<#--        <a class="pag" href="#">&raquo;</a>-->
    </div>
</div>
<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $('tr[data-href]').on("click", function() {
        document.location = $(this).data('href');
    });
</script>
</body>
</html>
