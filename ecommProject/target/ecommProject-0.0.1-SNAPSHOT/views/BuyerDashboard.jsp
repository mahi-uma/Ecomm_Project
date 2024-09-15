<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List"%>
<%@ page import="dto.BuyerDto"%>
<%@ page import="dto.ProductDto"%>
<%@ page import="dto.CategoryDto"%>
<%
    HttpSession session1 = request.getSession();
    BuyerDto buyer = (BuyerDto) session1.getAttribute("buyer");
    List<ProductDto> products = (List<ProductDto>) session.getAttribute("products");
    List<CategoryDto> categories = (List<CategoryDto>) session.getAttribute("categories");
    List<Boolean> favorites = (List<Boolean>) session.getAttribute("favorites"); // List indicating favorite status
    List<Boolean> cart = (List<Boolean>) session.getAttribute("cart");
    
    if (buyer == null) {
        response.sendRedirect("/ecommProject/");
        return;
    }
%>
<% 
 String updateSuccess = (String) session1.getAttribute("updateSuccess");
    if (updateSuccess != null) {
%>
    <script>
        alert("<%= updateSuccess %>");
    </script>
<%
        session1.removeAttribute("updateSuccess");
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
    <!-- FontAwesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
                    <a class="nav-link" href="/ecommProject/UpdateServlet?action=display">My WishList</a>
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
                        <a class="dropdown-item" href="/ecommProject/ServerController?userType=profile">Profile</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/ecommProject/">Logout</a>
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
    <div class="container mt-4">
        <h2>Products</h2>

        <div class="row ">
            <%
                for (int i = 0; i < products.size(); i++) {
                    ProductDto product = products.get(i);
                    boolean isFavorite = favorites.get(i);
                    String favoriteAction = isFavorite ? "remove" : "add";
                    String favoriteIcon = isFavorite ? "fas fa-star" : "far fa-star";
                    String favoriteColor = isFavorite ? "btn-warning" : "btn-outline-warning";
                    boolean isInCart = cart.get(i);
                    String cartAction = isInCart ? "removecart" : "addcart";
                    String cartIcon = isInCart ? "fas fa-shopping-cart" : "far fa-shopping-cart";
                    String cartColor = isInCart ? "btn-danger" : "btn-outline-danger";
                    String buttonText = isInCart ? "Remove from Cart" : "Add to Cart";
            %>
                <div class="col-md-4 mb-4 ">
                    <div class="card d-flex flex-column" style="width: 400px;">
                        <img src="<%= request.getContextPath() %>/images/download.jpg" alt="Login Picture" style="width:340px;height:200px"/>
                        <div class="card-body d-flex flex-column flex-grow-1">
                            <h5 class="card-title"><%= product.getPro_name() %></h5>
                            <p class="card-text"><%= product.getPdesc() %></p>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item" style="color:green">Price: $<%= product.getPpp() %></li>
                            <li class="list-group-item">Discount Per Item: <%= product.getDpp() %></li>
                        </ul>
                        <div class="card-body">
                            <div class="row">
                            	<div class="col-6">
                                    <a href="#" class="btn btn-info btn-block">See Review</a>
                                </div>
                                <div class="col-6">
                                    <a href="#" class="btn btn-success btn-block">Buy Now</a>
                                </div>
                            </div>
                            <div class="row mt-2">
                                <div class="col-6">
                                    <!-- Add to Cart / Remove from Cart Form -->
                                    <form action="/ecommProject/CartServlet" method="post">
                                        <input type="hidden" name="productId" value="<%= product.getP_id() %>">
                                        <input type="hidden" name="action" value="<%= cartAction %>">
                                        <button type="submit" class="btn <%= cartColor %> btn-block">
                                            <i class="<%= cartIcon %>"></i> <span><%= buttonText %></span>
                                        </button>
                                    </form>
                                </div>
                                <div class="col-6">
                                    <a href="FavServlet?productId=<%= product.getP_id() %>&action=<%= favoriteAction %>" class="btn <%= favoriteColor %> btn-block">
                                        <i class="<%= favoriteIcon %>"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="row mt-3 justify-content-center">
                                <a href="#" class="btn btn-info btn-block" data-toggle="modal" data-target="#addReviewModal<%= i %>" style="width:150px">Add review</a>
                            </div>
                            <!-- Modal for each product -->
                            <div class="modal fade" id="addReviewModal<%= i %>" tabindex="-1" role="dialog" aria-labelledby="addReviewModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="addReviewModalLabel">Add Your Review</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <!-- Review Form -->
                                            <form id="reviewForm<%= i %>" action="/ecommProject/ReviewServlet" method="post">
                                                <input type="hidden" name="productId" value="<%= product.getP_id() %>">
                                                <div class="form-group">
                                                    <label for="rating">Rating (0 to 5)</label>
                                                    <input type="number" class="form-control" id="rating" name="rating" min="0" max="5" required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="review">Review</label>
                                                    <textarea class="form-control" id="review" name="review" rows="3" required></textarea>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                            <button type="submit" class="btn btn-primary" form="reviewForm<%= i %>">OK</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        </div>
            <%
                }
            %>
        </div>
    </div>
    <!-- Bootstrap JS and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
</body>
</html>
