<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/friends-style.css">
    <link rel="icon" href="/assets/icon-for-site.png">
    <title>Friends</title>
</head>
<body>
<div class="container">
    <img class="image-friends" src="/assets/friends-logo.svg" alt="">
    <div class="block-btn">
        <a class="btn top-btn" href="/profile">Profile</a>
        <a class="btn top-btn" href="/user/search">Search</a>
    </div>
    <div class="friends-top">
        <div class="title">
            <h3>Your requests</h3>
        </div>
        <table class="table-friends">
            <#if usersNotAcceptedRequests?has_content>
                <#list usersNotAcceptedRequests as user>
                    <tr data-href="/profile/${user.id}">
                        <td>
                            <img class="user-icon" src="${user.icon}" alt="">
                        </td>
                        <td>
                            <p>${user.firstname} ${user.lastname}</p>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                </#list>
            <#else>
                <p style="text-align: center">No requests</p>
            </#if>

        </table>
    </div>

    <div class="friends-top">
        <div class="title">
            <h3>Friends requests to you</h3>
        </div>
        <table class="table-friends">
            <#if notAcceptedRequestsToUser?has_content>
                <#list notAcceptedRequestsToUser as userAccepted>
                    <tr data-href="/profile/${userAccepted.id}">
                        <td>
                            <img class="user-icon" src="${userAccepted.icon}"/>
                        </td>
                        <td>
                            <p>${userAccepted.firstname} ${userAccepted.lastname}</p>
                        </td>
                        <td>
                            <a class="table-btn" href="/user/friends/${userAccepted.id}/accept">Accept</a>
                        </td>
                        <td>
                            <a class="table-btn" href="/user/friends/${userAccepted.id}/decline">Decline</a>
                        </td>
                        <td>
                            <a class="table-btn" href="/chat/${userAccepted.id}">Message</a>
                        </td>
                    </tr>
                </#list>
            <#else>
                <p style="text-align: center">You have no friend requests</p>
            </#if>
        </table>
    </div>

    <div class="friends-top">
        <div class="title">
            <h3>Your friends</h3>
        </div>
        <table class="table-friends">
            <#if friendsOfUser?has_content>
                <#list friendsOfUser as friend>
                    <tr data-href="/profile/${friend.id}">
                        <td>
                            <img class="user-icon" src="${friend.icon}" alt="">
                        <td>
                        <td>
                            <p>${friend.firstname} ${friend.lastname}</p>
                        </td>
                        <td></td>
                        <td>
                            <a class="table-btn" href="/chat/${friend.id}">Message</a>
                        </td>
                        <td>
                            <a class="table-btn" href="/user/friends/${friend.id}/decline">Remove</a>
                        </td>
                    </tr>
                </#list>
            <#else>
                <p style="text-align: center">No friends</p>
            </#if>
        </table>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $('tr[data-href]').on("click", function() {
        document.location = $(this).data('href');
    });
</script>
</body>
</html>


