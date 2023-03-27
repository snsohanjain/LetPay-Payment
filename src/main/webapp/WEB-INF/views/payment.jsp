<%@include file="css/payment-header.jspf"%>
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








