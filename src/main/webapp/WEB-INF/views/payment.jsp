<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
    <title>Bootstrap Elegant Sign Up Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            color: #999;
            background: #fafafa;
            font-family: 'Roboto', sans-serif;
        }
        .form-control {
            min-height: 41px;
            box-shadow: none;
            border-color: #e6e6e6;
        }
        .form-control:focus {
            border-color: #00c1c0;
        }
        .form-control, .btn {
            border-radius: 3px;
        }
        .signup-form {
            width: 425px;
            margin: 0 auto;
            padding: 30px 0;
        }
        .signup-form h2 {
            color: #333;
            font-weight: bold;
            margin: 0 0 25px;
        }
        .signup-form form {
            margin-bottom: 15px;
            background: #fff;
            border: 1px solid #f4f4f4;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 40px 50px;
        }
        .signup-form .form-group {
            margin-bottom: 20px;
        }
        .signup-form label {
            font-weight: normal;
            font-size: 13px;
        }
        .signup-form input[type="checkbox"] {
            margin-top: 2px;
        }
        .signup-form .btn {
            font-size: 16px;
            font-weight: bold;
            background: #00c1c0;
            border: none;
            min-width: 140px;
            outline: none !important;
        }
        .signup-form .btn:hover, .signup-form .btn:focus {
            background: #00b3b3;
        }
        .signup-form a {
            color: #00c1c0;
            text-decoration: none;
        }
        .signup-form a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="signup-form">
    <form action="/payment" method="post">
        <h2>Payment</h2>
        <div class="form-group">
            <input type="text" class="form-control" name="AMT" placeholder="Amount" required="required">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="CustomerName" placeholder="CustomerName" required="required">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="phoneNumber" placeholder="phoneNumber" required="required">
        </div>
        <div class="form-group">
            <input type="email" class="form-control" name="email" placeholder="Email Address" required="required">
        </div>
        <div class="form-group">
            <label class="checkbox-inline"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-lg">Pay</button>
        </div>
    </form>
</div>
</body>
</html>








