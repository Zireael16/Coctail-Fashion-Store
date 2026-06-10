<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Coctail | Premium Fashion</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>

    <jsp:include page="/views/partials/navbar.jsp" />
	
	<c:if test="${param.msg == 'order_success'}">
        <div id="successToast" style="background-color: #e8f5e9; color: #2e7d32; padding: 16px 24px; text-align: center; font-weight: 500; font-size: 15px; border-bottom: 1px solid #c8e6c9; transform: translateY(-100%); animation: slideDown 0.5s cubic-bezier(0.16, 1, 0.3, 1) forwards;">
            🎉 Order placed successfully! Your cart has been cleared.
        </div>
        
        <style>
            @keyframes slideDown {
                from { transform: translateY(-100%); opacity: 0; }
                to { transform: translateY(0); opacity: 1; }
            }
        </style>
        
        <script>
            // Make the notification smoothly disappear after 4 seconds
            setTimeout(function() {
                var toast = document.getElementById('successToast');
                if(toast) {
                    toast.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
                    toast.style.opacity = '0';
                    toast.style.transform = 'translateY(-10px)';
                    setTimeout(() => toast.remove(), 500);
                }
            }, 4000);
        </script>
    </c:if>
    <section class="section" style="margin-top: 54px;">
        <div class="container fade-in">
            <h1>Elegance. Redefined.</h1>
            <p class="subtitle">Discover the new summer collection.</p>
            <a href="${pageContext.request.contextPath}/shop" class="btn-primary">Shop Now</a>
        </div>
    </section>

    <section class="section bg-gray">
        <div class="container fade-in" style="animation-delay: 0.2s;">
            <h2 style="font-size: 40px; font-weight: 600;">Pro design. <br> Everyday comfort.</h2>
            <p class="subtitle" style="font-size: 19px;">Engineered for the perfect fit.</p>
        </div>
    </section>

    <jsp:include page="/views/partials/footer.jsp" />

</body>
</html>