<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/admin-style.css">
    <link rel="icon" href="/assets/icon-for-site.png">
    <title>Admin</title>
</head>
<body>
<div class="container">
    <div class="title">
        <h3>Users List</h3>
    </div>

    <table class="content-table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Email</th>
            <th>Status</th>
            <th></th>
        </tr>
        </thead>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
                <td>${user.email}</td>
                <td>${user.state}</td>
                <#if user.state == "BANNED">
                    <td><a href="/active/${user.id}">Active</a></td>
                <#elseif user.role == "ADMIN">
                    <td style="color:#FF3D6D;">${user.role}</td>
                <#else>
                    <td><a href="/ban/${user.id}">Ban</a></td>
                </#if>
            </tr>
        </#list>
    </table>

    <form method="post" class="form-ban" action="/banAll">
        <#--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
        <input type="submit" class="btn" value="BAN All">
    </form>

    <div class="btn-back">
        <a href="/profile" class="btn other-btn exit back">Back</a>
    </div>
</div>
</body>
</html>
