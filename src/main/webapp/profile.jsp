<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Account | Coctail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .profile-container { display: flex; gap: 60px; padding: 60px 0; }
        .account-details { flex: 1; }
        .order-history { flex: 2; }
        .info-card {
            background: var(--bg-secondary); padding: 30px;
            border-radius: 24px; margin-bottom: 20px;
        }
        .info-label { font-size: 13px; color: var(--text-secondary); margin-bottom: 4px; text-transform: uppercase; letter-spacing: 0.5px;}
        .info-value { font-size: 16px; font-weight: 500; margin-bottom: 20px; }
        
        .order-card {
            border: 1px solid var(--border-color); border-radius: 16px;
            padding: 24px; margin-bottom: 20px; transition: box-shadow 0.3s;
        }
        .order-card:hover { box-shadow: 0 10px 20px rgba(0,0,0,0.05); }
        .order-header { display: flex; justify-content: space-between; margin-bottom: 15px; padding-bottom: 15px; border-bottom: 1px solid var(--border-color); }
        .status-badge {
            padding: 6px 12px; border-radius: 99px; font-size: 12px; font-weight: 600;
            background: #e8f5e9; color: #2e7d32; /* Green for success/default */
        }
        .status-pending { background: #fff8e1; color: #f57f17; } /* Orange for pending */
    </style>
</head>
<body>

    <jsp:include page="/views/partials/navbar.jsp" />

    <div class="container fade-in">
        <h1 style="font-size: 36px; margin-top: 40px;">My Account.</h1>
        
        <div class="profile-container">
            <!-- Left Side: User Info -->
            <div class="account-details">
                <div class="info-card">
                    <h2 style="font-size: 20px; margin-bottom: 24px;">Personal Details</h2>
                    
                    <div class="info-label">Full Name</div>
                    <div class="info-value">${sessionScope.currentUser.name}</div>
                    
                    <div class="info-label">Email Address</div>
                    <div class="info-value">${sessionScope.currentUser.email}</div>
                    
                    <div class="info-label">Shipping Address</div>
                    <div class="info-value">${sessionScope.currentUser.address}</div>
                    
                    <!-- Optional: Add a Logout Button here for convenience -->
                    <a href="${pageContext.request.contextPath}/logout" class="btn-primary" style="display: block; text-align: center; background: #d32f2f; color: white; margin-top: 30px; border: none;">Sign Out</a>
                </div>
            </div>

            <!-- Right Side: Order History -->
            <div class="order-history">
                <h2 style="font-size: 24px; margin-bottom: 24px;">Order History</h2>
                
                <c:choose>
                    <c:when test="${not empty myOrders}">
                        <c:forEach var="order" items="${myOrders}">
                            <div class="order-card">
                                <div class="order-header">
                                    <div>
                                        <div class="info-label">Order Number</div>
                                        <div style="font-weight: 600; font-size: 18px;">#${order.id}</div>
                                    </div>
                                    <div style="text-align: right;">
                                        <span class="status-badge ${order.status == 'Pending' ? 'status-pending' : ''}">
                                            ${order.status}
                                        </span>
                                    </div>
                                </div>
                                <div style="display: flex; justify-content: space-between; align-items: center;">
                                    <div style="color: var(--text-secondary); font-size: 14px;">
                                        Placed on: ${order.orderDate}
                                    </div>
                                    <div style="font-size: 20px; font-weight: 600;">
                                        $${order.totalAmount}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div style="text-align: center; padding: 60px; background: var(--bg-secondary); border-radius: 16px;">
                            <h3 style="margin-bottom: 10px;">No orders yet.</h3>
                            <p style="color: var(--text-secondary); margin-bottom: 20px;">When you place orders, they will appear here.</p>
                            <a href="${pageContext.request.contextPath}/shop" class="btn-primary" style="padding: 12px 24px;">Start Shopping</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <jsp:include page="/views/partials/footer.jsp" />

</body>
</html>