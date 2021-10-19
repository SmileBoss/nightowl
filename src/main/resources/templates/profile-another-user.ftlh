<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/profile-style.css">
    <link rel="icon" href="/assets/icon-for-site.png">
    <title>Profile</title>
</head>
<body>
<div class="container-up">
    <div class="block-rear"></div>
    <div class="block-front">
        <div class="info-block">
            <div class="profile-icon" style="background-image: url('${user.icon}')"></div>
            <div class="text-block">
                <p>${user.firstname} ${user.lastname}</p>

                <h4>Email:</h4>

                <p class="user-email">${user.email}</p>
            </div>
        </div>
        <div class="image-block"></div>
    </div>
</div>
<div class="container-down">
    <div class="content-block">
        <div class="col-3 first">
            <div class="count-friends">
                <p class="count">${friendsOfUser}</p>
            </div>
            <#if usersHaveFriendship != true>
                <a href="/user/friends/${user.id}/addToFriends" class="btn">Add Friend</a>
            <#else>
                <a href="/user/friends" class="btn">Friend<span class="glyphicon glyphicon-ok"></span></a>
            </#if>
        </div>
        <div class="col-3 other">
        </div>
        <div class="col-3 other">
            <a href="/chat/${user.id}" class="btn other-btn">Chat</a>
        </div>
    </div>
</div>
</body>
</html>
