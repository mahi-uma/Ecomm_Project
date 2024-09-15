<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buyer Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #43cea2, #185a9d);
        }

        .registration-form {
            width: 400px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        }

        .registration-form h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-group input, 
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .form-group textarea {
            resize: vertical;
        }

        .submit-btn {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .submit-btn:hover {
            background-color: #218838;
        }
        .error,#er {
            color: red;
            font-size: 12px;
        }
    </style>
</head>
<body>
    <div class="registration-form">
        <h2>Buyer Registration</h2>
        <form action="/ecommProject/BuyerController" method="post" onsubmit="return fun_valid()">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <span id="er"></span>
            </div>
            <div class="form-group">
                <label for="phno">Phone Number:</label>
                <input type="text" id="phno" name="phno" required>
                <span class="error"></span>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" rows="4" required></textarea>
            </div>
            <button type="submit" class="submit-btn">Register</button>
        </form>
    </div>
	<% 
	    String errorMessage = (String) request.getAttribute("errorMessage");
	    if (errorMessage != null) { 
	%>
	    <script>
	        alert("<%= errorMessage %>");
	    </script>
	<% 
	    } 
	%>
	<script>
		function fun_valid(){
			var pwd=document.getElementById("phno").value;
			var phoneRegex = /^\d{10}$/;
			if (!phoneRegex.test(pwd)) {
				var sp=document.getElementsByClassName("error")[0]
				sp.textContent="Phone number must be exactly 10 digits."
				return false;
            }
			var phoneRegex = /^\d{10}$/;
			if (!phoneRegex.test(pwd)) {
				var sp=document.getElementsByClassName("error")[0]
				sp.textContent="Phone number must be exactly 10 digits."
				return false;
            }
			return true;
		}
	</script>
    
</body>
</html>
