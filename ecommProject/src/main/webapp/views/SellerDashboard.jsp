<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.SellerDto"%>
<%
    HttpSession sellerSession = request.getSession();
    SellerDto seller = (SellerDto) sellerSession.getAttribute("seller");
    
    if (seller == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EcommRev</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
    <style>
        /* Navbar customization */
        .navbar {
            background-color: #343a40; /* Dark background */
            padding: 1rem; /* Padding for more space */
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* Add shadow */
        }
        .navbar-brand {
            color: #ffffff; /* White text for brand */
        }
        .navbar-nav .nav-link {
            color: #ffffff; /* White text for links */
        }
        .navbar-nav .nav-link:hover {
            color: #d3d3d3; /* Light grey hover color */
        }
        .navbar-toggler-icon {
            background-color: #ffffff; /* White color for the toggle icon */
        }

        /* Dropdown menu customization */
        .dropdown-menu {
            background-color: #343a40; /* Dark dropdown */
            border: none; /* Remove borders */
        }
        .dropdown-item {
            color: #ffffff; /* White text */
        }
        .dropdown-item:hover {
            background-color: #495057; /* Slightly lighter grey on hover */
        }

        /* Welcome message styling */
        .alert {
            border-radius: 10px; /* Rounded corners */
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="#">
            <img src="./cartoon.webp" width="30" height="30" alt="Cartoon Logo"> EcommRev
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<%= request.getContextPath() %>/ServerController?userType=product_add">Add Product</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Manage Products
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="viewProducts.jsp">View Products</a>
                        <a class="dropdown-item" href="editProduct.jsp">Edit Product</a>
                        <a class="dropdown-item" href="deleteProduct.jsp">Delete Product</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">Check Product Availability</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="viewOrders.jsp">View Orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="accountSettings.jsp">Account Settings</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Welcome Message -->
    <div class="container mt-3">
        <div class="alert alert-info text-center" role="alert">
            <h4 class="alert-heading">Welcome, <%= seller.getName() %>!</h4>
            <p>We're glad to have you back on your dashboard. Let's manage your products and sales!</p>
        </div>
    </div>

    <!-- Dashboard Summary -->
    <div class="container mt-4">
        <section class="dashboard">
            <h3 class="mb-4">Dashboard Overview</h3>
            <div class="row">
                <div class="col-md-4">
                    <div class="card text-center">
                        <div class="card-body">
                            <h4 class="card-title">Total Products</h4>
                            <p class="card-text">50</p> <!-- Replace this with dynamic data -->
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-center">
                        <div class="card-body">
                            <h4 class="card-title">Orders Today</h4>
                            <p class="card-text">10</p> <!-- Replace this with dynamic data -->
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-center">
                        <div class="card-body">
                            <h4 class="card-title">Total Sales</h4>
                            <p class="card-text">$2,500</p> <!-- Replace this with dynamic data -->
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Orders Section -->
        <section class="orders mt-4">
            <h3>Recent Orders</h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Customer</th>
                        <th>Status</th>
                        <th>Total Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1001</td>
                        <td>John Doe</td>
                        <td>Shipped</td>
                        <td>$120</td>
                    </tr>
                </tbody>
            </table>
        </section>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
