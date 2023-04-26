<%@include file="css/payment-header.jspf"%>
<body>
<div class="signup-form">
    <form action="/payment" method="post">
        <h1 style="color: #5556fd"><strong>LetPay</strong></h1>
        <br>
        <div class="form-group">
            <input type="number" class="form-control" name="AMT" placeholder="Amount" required="required">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" name="CustomerName" placeholder="Customer Name" required="required">
        </div>
        <div class="form-group">
            <input type="number" class="form-control" name="phoneNumber" placeholder="Phone Number" required="required">
        </div>
        <div class="form-group">
            <input type="email" class="form-control" name="email" placeholder="Email Address" required="required">
        </div>
        <div class="form-group">
            <label class="checkbox-inline"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-lg "><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lock" viewBox="0 0 16 16">
                <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"/>
            </svg><span> </span>Pay</button>
        </div>
    </form>
</div>
</body>
</html>








