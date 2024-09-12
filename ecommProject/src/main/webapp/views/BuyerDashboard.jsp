<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.BuyerDto"%>
<%
    HttpSession sellerSession = request.getSession();
    BuyerDto buyer = (BuyerDto) sellerSession.getAttribute("buyer");
    
    if (buyer == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buyer Dashboard</title>
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
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
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand">EcommRev</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">View Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Order History</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Pending Orders</a>
                </li>
            </ul>
            
            <!-- Profile dropdown -->
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="profileDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img src="https://img.icons8.com/ios-filled/50/ffffff/user-male-circle.png" alt="Profile" width="30" height="30">
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="profileDropdown">
                        <a class="dropdown-item" href="#">Profile</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Logout</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Welcome Message -->
    <div class="container mt-3">
        <div class="alert alert-info text-center" role="alert">
            <h4 class="alert-heading">Welcome, <%= buyer.getName() %>!</h4>
            <p>We're glad to have you here. Explore the products and enjoy shopping!</p>
        </div>
    </div>
    <!-- Product Search and Filter Form -->
    <div class="container mt-4">
        <h2>Products</h2>
        
        <form action="dashboard" method="get" class="mb-4">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <input type="text" name="search" class="form-control" placeholder="Search products...">
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="form-group">
                        <select name="category" class="form-control">
                            <option value="">All Categories</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category}">${category}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary btn-block">Search</button>
                </div>
            </div>
        </form>

        <!-- Products Display -->
        <div class="row">
            <c:forEach var="product" items="${products}">
                <div class="col-md-4 mb-4">
                    <div class="card d-flex flex-column" style="width: 18rem;">
                        <img src="${product.imageUrl}" class="card-img-top" alt="${product.name}">
                        <div class="card-body d-flex flex-column flex-grow-1">
                            <h5 class="card-title">${product.name}</h5>
                            <p class="card-text">${product.description}</p>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">Category: ${product.category}</li>
                            <li class="list-group-item">Rating: ${product.rating}</li>
                            <li class="list-group-item">Price: ${product.price}</li>
                        </ul>
                        <div class="card-body d-flex justify-content-between">
                            <a href="#" class="card-link">Add to Cart</a>
                            <a href="#" class="card-link">Buy Now</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>