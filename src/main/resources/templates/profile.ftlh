<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/profile-style.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" href="/assets/icon-for-site.png">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <title>Profile</title>
</head>
<body>
<div class="container-up">
    <div class="block-rear"></div>
    <div class="block-front">
        <div class="info-block">

            <div class="profile-icon" style="background-image: url('${user.icon}')">
                <div class="setting-icon modal-btn">
                    <i class="fa fa-cog" aria-hidden="true"></i>
                </div>
            </div>
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
            <a href="/user/friends" class="btn">Friends</a>
        </div>
        <div class="col-3 other">
            <#if user.role == "ADMIN">
                <a href="/admin" class="btn other-btn">Admin Panel</a>
            </#if>
        </div>
        <div class="col-3 other">
            <a href="/logout" class="btn other-btn exit">Logout</a>
        </div>
    </div>
</div>

<div class="modal-bg">

    <div class="modal">
        <h2>Update Profile Icon</h2>
        <form name="icon" class="form-icon" enctype="multipart/form-data" method="post" action="/profile/icon/update">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <label for="custom-file-upload" class="filupp">
                <span class="filupp-file-name js-value">Browse Files</span>
                <input type="file" name="image" value="1" id="custom-file-upload"/>
            </label>

            <button type="submit" class="btn">Submit</button>
        </form>
        <span class="modal-close">X</span>
    </div>
</div>
<script src="/js/modal-view-icon.js"></script>
</body>

<script>
    $(document).ready(function () {

        // get the name of uploaded file
        $('input[type="file"]').change(function () {
            var value = $("input[type='file']");
            $('.js-value').text(value.get(0).files.item(0).name);
        });

    });
</script>

</html>
