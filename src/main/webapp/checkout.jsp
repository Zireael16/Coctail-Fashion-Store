<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Secure Checkout | Coctail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .checkout-container { display: flex; gap: 60px; padding: 60px 0; }
        .checkout-form { flex: 2; }
        .checkout-summary {
            flex: 1; background: var(--bg-secondary); padding: 40px;
            border-radius: 24px; height: fit-content; position: sticky; top: 100px;
        }
        .form-section {
            background: #ffffff; border: 1px solid var(--border-color);
            border-radius: 16px; padding: 30px; margin-bottom: 30px;
        }
        .form-section h2 { font-size: 20px; margin-bottom: 20px; font-weight: 600; }
        .input-group { margin-bottom: 20px; }
        .input-group label { display: block; font-size: 14px; margin-bottom: 8px; color: var(--text-secondary); }
        .input-control {
            width: 100%; padding: 14px; border: 1px solid var(--border-color);
            border-radius: 10px; font-family: inherit; font-size: 15px; box-sizing: border-box;
        }
        .input-control:focus { outline: none; border-color: #1d1d1f; }
        .summary-row { display: flex; justify-content: space-between; margin-bottom: 15px; font-size: 15px; }
        .total-row { font-size: 24px; font-weight: 600; margin-top: 20px; padding-top: 20px; border-top: 1px solid var(--border-color); }
    </style>
</head>
<body>

    <jsp:include page="/views/partials/navbar.jsp" />

    <div class="container fade-in">
        <h1 style="font-size: 36px; margin-top: 40px;">Checkout.</h1>
        
        <c:if test="${param.error == 'order_failed'}">
            <div style="background-color: #ffebee; color: #c62828; padding: 16px 20px; border-radius: 12px; margin-bottom: 20px; font-weight: 500; border: 1px solid #ffcdd2;">
                Database Error: Could not create the main order in the 'orders' table. Please check your Eclipse Console for the SQL error!
            </div>
        </c:if>
        <c:if test="${param.error == 'items_failed'}">
            <div style="background-color: #fff3e0; color: #e65100; padding: 16px 20px; border-radius: 12px; margin-bottom: 20px; font-weight: 500; border: 1px solid #ffe0b2;">
                Database Error: The order was created, but saving to 'order_items' failed. Check your Eclipse Console!
            </div>
        </c:if>
        
        <div class="checkout-container">
            <form action="${pageContext.request.contextPath}/checkout" method="POST" class="checkout-form">
                
                <div class="form-section">
                    <h2>Shipping Details</h2>
                    <div class="input-group">
                        <label>Full Name</label>
                        <input type="text" name="fullName" class="input-control" value="${sessionScope.currentUser.name}" required>
                    </div>
                    <div class="input-group">
                        <label>Email Address</label>
                        <input type="email" name="email" class="input-control" value="${sessionScope.currentUser.email}" readonly style="background: #f5f5f7; cursor: not-allowed;">
                    </div>
                    <div class="input-group">
                        <label>Shipping Address</label>
                        <textarea name="shippingAddress" class="input-control" rows="3" required>${sessionScope.currentUser.address}</textarea>
                    </div>
                </div>

                <div class="form-section">
                    <h2>Payment Method</h2>
                    <p style="color: var(--text-secondary); font-size: 14px; margin-bottom: 15px;">This is a demo. No real payment is processed.</p>
                    <div class="input-group">
                        <label>Name on Card</label>
                        <input type="text" class="input-control" placeholder="Jane Doe" required>
                    </div>
                    <div class="input-group">
                        <label>Card Number</label>
                        <input type="text" class="input-control" placeholder="0000 0000 0000 0000" required>
                    </div>
                </div>

                <button type="submit" class="btn-primary" style="width: 100%; padding: 20px; font-size: 16px;">Place Order</button>
            </form>

            <div class="checkout-summary">
                <h2 style="font-size: 20px; margin-bottom: 24px;">Order Summary</h2>
                <div class="summary-row">
                    <span>Items (${sessionScope.cartItems.size()})</span>
                    <span>$${cartTotal}</span>
                </div>
                <div class="summary-row">
                    <span>Shipping</span>
                    <span>Free</span>
                </div>
                <div class="summary-row total-row">
                    <span>Total</span>
                    <span>$${cartTotal}</span>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/views/partials/footer.jsp" />

</body>
</html>