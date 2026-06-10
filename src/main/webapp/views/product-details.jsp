<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${product.name} | Coctail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .details-container {
            display: flex;
            gap: 80px;
            padding: 120px 0;
            align-items: flex-start;
        }
        .product-gallery {
            flex: 1.2;
            position: sticky;
            top: 100px;
        }
        .product-gallery img {
            width: 100%;
            border-radius: 30px;
            background: var(--bg-secondary);
        }
        .product-info {
            flex: 0.8;
            padding-top: 20px;
        }
        .product-info .brand {
            font-size: 14px;
            font-weight: 600;
            color: var(--accent);
            text-transform: uppercase;
            letter-spacing: 0.1em;
            margin-bottom: 10px;
        }
        .product-info h1 {
            font-size: 40px;
            margin-bottom: 15px;
        }
        .product-info .price {
            font-size: 24px;
            font-weight: 400;
            margin-bottom: 40px;
        }
        .selection-label {
            font-size: 14px;
            font-weight: 600;
            margin-bottom: 15px;
            display: block;
        }
        .size-grid {
            display: flex;
            gap: 12px;
            margin-bottom: 40px;
        }
        .size-option {
            border: 1px solid var(--border-color);
            padding: 12px 24px;
            border-radius: 12px;
            cursor: pointer;
            transition: all 0.3s ease;
            font-size: 14px;
        }
        .size-option:hover {
            border-color: var(--text-primary);
        }
        .size-option.active {
            background: var(--text-primary);
            color: white;
            border-color: var(--text-primary);
        }
        .description {
            line-height: 1.6;
            color: var(--text-secondary);
            margin-bottom: 40px;
            font-size: 17px;
        }
    </style>
</head>
<body>

    <jsp:include page="partials/navbar.jsp" />

    <div class="container fade-in">
        <div class="details-container">
            <!-- Left Side: Image -->
            <div class="product-gallery">
                <img src="${pageContext.request.contextPath}/${product.imageUrl}" alt="${product.name}" onerror="this.src='https://via.placeholder.com/800x800?text=No+Image';">
            </div>

            <!-- Right Side: Info -->
            <div class="product-info">
                <span class="brand">${product.brand}</span>
                <h1>${product.name}</h1>
                <p class="price">$${product.price}</p>
                
                <p class="description">${product.description}</p>

                <form action="${pageContext.request.contextPath}/add-to-cart" method="POST">
                    <input type="hidden" name="productId" value="${product.id}">
                    
                    <span class="selection-label">Select Size</span>
                    <div class="size-grid">
                        <c:forEach var="variant" items="${variants}">
                            <label class="size-option">
                                <input type="radio" name="variantId" value="${variant.id}" required style="display:none;" onchange="updateSelection(this)">
                                ${variant.size}
                            </label>
                        </c:forEach>
                    </div>

                    <button type="submit" class="btn-primary" style="width: 100%; padding: 18px;">Add to Bag</button>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="partials/footer.jsp" />

    <script>
        function updateSelection(input) {
            document.querySelectorAll('.size-option').forEach(el => el.classList.remove('active'));
            input.parentElement.classList.add('active');
        }
    </script>

</body>
</html>