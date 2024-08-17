<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment Form</title>
</head>
<body>
    <h1>Payment Form</h1>
    <form action="/createPayment" method="post">
        <label for="orderId">Order ID:</label>
        <input type="text" id="orderId" name="orderId" required><br><br>
        
        <label for="amount">Amount:</label>
        <input type="text" id="amount" name="amount" required><br><br>

        <input type="submit" value="Submit">
    </form>
</body>
</html>