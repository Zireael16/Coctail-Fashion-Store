<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Coctail | Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .shop-header {
            padding: 100px 0 60px 0;
            text-align: left;
        }
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
            gap: 40px;
            margin-bottom: 80px;
        }
        .product-card {
            background: var(--bg-primary);
            border-radius: 20px;
            padding: 20px;
            text-decoration: none;
            color: inherit;
            transition: transform 0.4s cubic-bezier(0.4, 0, 0.2, 1), box-shadow 0.4s ease;
            display: flex;
            flex-direction: column;
            border: 1px solid transparent;
        }
        .product-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 20px 40px rgba(0,0,0,0.08);
            border: 1px solid var(--border-color);
        }
        .product-image {
            width: 100%;
            aspect-ratio: 1/1;
            background-color: var(--bg-secondary);
            border-radius: 12px;
            margin-bottom: 20px;
            object-fit: cover;
        }
        .brand-label {
            font-size: 12px;
            color: var(--accent);
            text-transform: uppercase;
            letter-spacing: 0.1em;
            font-weight: 600;
            margin-bottom: 4px;
        }
        .product-name {
            font-size: 19px;
            font-weight: 600;
            margin-bottom: 8px;
        }
        .product-price {
            font-size: 16px;
            color: var(--text-primary);
        }
        .empty-state {
            text-align: center;
            padding: 100px 0;
            color: var(--text-secondary);
        }
    </style>
</head>
<body>

    <jsp:include page="/views/partials/navbar.jsp" />

    <div class="container">
        <header class="shop-header fade-in">
            <h1 style="font-size: 48px;">${pageTitle}</h1>
        </header>

        <c:choose>
            <c:when test="${not empty products}">
                <div class="product-grid fade-in" style="animation-delay: 0.1s;">
                    <c:forEach var="product" items="${products}">
                        <a href="${pageContext.request.contextPath}/product?id=${product.id}" class="product-card">
                            <img src="${pageContext.request.contextPath}/${product.imageUrl}" alt="${product.name}" class="product-image" onerror="this.src='https://via.placeholder.com/400x400?text=No+Image';">
                            <span class="brand-label">${product.brand}</span>
                            <span class="product-name">${product.name}</span>
                            <span class="product-price">$${product.price}</span>
                        </a>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div class="empty-state fade-in">
                    <h2>No products found.</h2>
                    <p class="subtitle">Try adjusting your search or filters.</p>
                    <a href="${pageContext.request.contextPath}/shop" class="btn-primary">View All Products</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <jsp:include page="/views/partials/footer.jsp" />

</body>
</html>