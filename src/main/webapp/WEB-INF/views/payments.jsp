<!DOCTYPE html>
<html lang="en">
<%@include file="css/header.jspf"%>
<body>

<div class="login-form">
  <form action="/payment" method="post">
    <p><span style="color: red; ">${errorMessage}</span></p>
    <h2 class="text-center">Pay</h2>
    <div class="form-group">
      <input type="text" name="name" class="form-control" placeholder="Username" required="required">
    </div>
    <div class="form-group">
      <input type="password" name="password" class="form-control" placeholder="Password" required="required">
    </div>
    <div class="form-group">
      <button type="submit" value="login" class="btn btn-primary btn-block">Log in</button>
    </div>
  </form>
</div>
</body>
</html>




