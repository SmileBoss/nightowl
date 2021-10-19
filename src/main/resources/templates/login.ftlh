<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/starter-page-style.css">
    <link rel="icon" href="/assets/icon-for-site.png">
    <title>SignIn</title>
</head>
<body>
<img class="wave" src="/assets/wave.png">
<div class="container">
    <div class="img">
        <img src="/assets/form-image.svg" alt="bg">
    </div>
    <div class="login-content">
        <form action="/signIn" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <img src="/assets/avatar.svg" alt="avatar">

            <h2 class="title">Welcome</h2>
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
            <a href="/signUp">Need an account? Sign up now!</a>
            <div class="check-block">
                <input type="checkbox" id="check" name="remember-me">
                <label for="check">Remember Me</label>
            </div>
            <input type="submit" class="btn" value="Login">
        </form>
    </div>
</div>
<script rel="script" src="/js/login-script.js"></script>
</body>
</html>
