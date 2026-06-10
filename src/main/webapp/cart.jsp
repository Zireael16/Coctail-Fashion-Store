<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bag | Coctail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .cart-container { display: flex; gap: 60px; padding: 80px 0; }
        .cart-items { flex: 2; }
        .cart-summary {
            flex: 1; background: var(--bg-secondary); padding: 40px;
            border-radius: 24px; height: fit-content; position: sticky; top: 100px;
        }
        .cart-item {
            display: flex; gap: 30px; padding-bottom: 30px;
            margin-bottom: 30px; border-bottom: 1px solid var(--border-color);
        }
        .cart-item img {
            width: 150px; height: 150px; object-fit: cover;
            border-radius: 16px; background: var(--bg-secondary);
        }
        .item-details { flex: 1; display: flex; flex-direction: column; justify-content: center; }
        .item-name { font-size: 20px; font-weight: 600; margin-bottom: 8px; }
        .item-size { color: var(--text-secondary); font-size: 14px; margin-bottom: 15px; }
        .summary-row { display: flex; justify-content: space-between; margin-bottom: 15px; font-size: 15px; }
        .total-row {
            font-size: 24px; font-weight: 600; margin-top: 20px;
            padding-top: 20px; border-top: 1px solid var(--border-color);
        }
        .empty-cart { text-align: center; padding: 100px 0; }
    </style>
</head>
<body>

    <jsp:include page="/views/partials/navbar.jsp" />

    <div class="container fade-in">
        <h1 style="font-size: 40px; margin-top: 60px;">Review your bag.</h1>
        <p style="color: var(--text-secondary); margin-bottom: 40px;">Free delivery and free returns.</p>

        <c:choose>
            <c:when test="${not empty cartDetails and cartDetails.size() > 0}">
                <div class="cart-container">
                    
                    <div class="cart-items">
                        <c:forEach var="detail" items="${cartDetails}">
                            <div class="cart-item">
                                <img src="${pageContext.request.contextPath}/${detail.product.imageUrl}" onerror="this.src='https://via.placeholder.com/150';">
                                <div class="item-details">
                                    <span class="item-name">${detail.product.name}</span>
                                    <span class="item-size">Size: ${detail.variant.size} | Qty: ${detail.cartItem.quantity}</span>
                                    <a href="${pageContext.request.contextPath}/remove-from-cart?variantId=${detail.cartItem.variantId}" style="color: #d32f2f; text-decoration: none; font-size: 13px; font-weight: 500;">Remove</a>
                                </div>
                                <div style="font-size: 18px; font-weight: 500; padding-top: 10px;">
                                    $${detail.subtotal}
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="cart-summary">
                        <h2 style="font-size: 24px; margin-bottom: 30px;">Order Summary</h2>
                        <div class="summary-row">
                            <span>Subtotal</span>
                            <span>$${cartTotal}</span>
                        </div>
                        <div class="summary-row">
                            <span>Estimated Shipping</span>
                            <span>Free</span>
                        </div>
                        <div class="summary-row total-row">
                            <span>Total</span>
                            <span>$${cartTotal}</span>
                        </div>
                        
                        <a href="${pageContext.request.contextPath}/checkout" class="btn-primary" style="width: 100%; margin-top: 30px; padding: 18px; display: inline-block; text-align: center; text-decoration: none; box-sizing: border-box;">
                            Proceed to Checkout
                        </a>
                        
                    </div>

                </div>
            </c:when>
            
            <c:otherwise>
                <div class="empty-cart">
                    <h2>Your bag is empty.</h2>
                    <a href="${pageContext.request.contextPath}/shop" class="btn-primary" style="margin-top: 20px; display: inline-block;">Continue Shopping</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <jsp:include page="/views/partials/footer.jsp" />

</body>
</html>