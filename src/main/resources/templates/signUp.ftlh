<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/starter-page-style.css">
    <link rel="icon" href="/assets/icon-for-site.png">
    <title>SignUp</title>
</head>
<body>
<img class="wave" src="/assets/wave.png">
<div class="container">
    <div class="img">
        <img src="/assets/register.svg" alt="bg">
    </div>
    <div class="login-content">
        <form action="/signUp" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <img src="/assets/avatar.svg" alt="avatar">

            <h2 class="title">Welcome</h2>
            <div class="input-div one">
                <div class="i">
                    <i class="fas fa-user"></i>
                </div>
                <div class="div">
                    <h5>Firstname</h5>
                    <input name="firstname" type="text" class="input">
                </div>
            </div>
            <div class="input-div one">
                <div class="i">
                    <i class="fas fa-user"></i>
                </div>
                <div class="div">
                    <h5>Lastname</h5>
                    <input name="lastname" type="text" class="input">
                </div>
            </div>
            <div class="input-div one">
                <div class="i">
                    <i class="fas fa-user"></i>
                </div>
                <div class="div">
                    <h5>Email</h5>
                    <input name="email" type="email" class="input">
                </div>
            </div>
            <div class="input-div pass">
                <div class="i">
                    <i class="fas fa-lock"></i>
                </div>
                <div class="div">
                    <h5>Password</h5>
                    <input name="password" type="password" class="input">
                </div>
            </div>
            <a href="/signIn">Already have an account? Then come in soon!</a>
            <input type="submit" class="btn" value="SignUp">
        </form>
    </div>
</div>
<script rel="script" src="/js/login-script.js"></script>
</body>
</html>
