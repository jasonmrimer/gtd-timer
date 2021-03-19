<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link
  rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
  integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
  crossorigin="anonymous"
>
<!DOCTYPE html>
<html>
<head>
  <title>GTD Timer</title>
</head>
<body>
<div id="formContent">

  <main class="form-login">
    <form>
      <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
      <label for="input-username" class="visually-hidden">Username</label>
      <input type="email" id="input-username" class="form-control" placeholder="Email address" required autofocus>
      <label for="input-password" class="visually-hidden">Password</label>
      <input type="password" id="input-password" class="form-control" placeholder="Password" required>
      <button class="w-100 btn btn-lg btn-primary button-login" type="submit">Sign in</button>
    </form>
  </main>
</div>
</body>
</html>
<style>
    html,
    body {
        height: 100%;
    }

    body {
        display: flex;
        flex-direction: row;
        align-items: flex-start;
        justify-content: center;
        padding-top: 32px;
        background-color: #fafafa;
    }

    .form-login {
        width: 100%;
        max-width: 330px;
        padding: 16px;
        margin: auto;
    }

    label {
        margin-top: 16px;
        margin-bottom: 4px;
    }

    .form-login input[type="email"] {
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-login input[type="password"] {
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .button-login {
        margin-top: 8px;
    }
</style>