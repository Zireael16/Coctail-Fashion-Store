<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In | Coctail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .auth-container {
            min-height: 80vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 40px 20px;
        }
        .auth-card {
            width: 100%;
            max-width: 400px;
            padding: 50px 40px;
            background: rgba(255, 255, 255, 0.8);
            backdrop-filter: blur(20px);
            border-radius: 24px;
            border: 1px solid var(--border-color);
            box-shadow: 0 20px 40px rgba(0,0,0,0.04);
            text-align: center;
        }
        .auth-input {
            width: 100%;
            padding: 16px;
            margin-bottom: 16px;
            border-radius: 12px;
            border: 1px solid var(--border-color);
            background: var(--bg-primary);
            font-family: inherit;
            font-size: 14px;
            transition: border-color 0.3s;
            box-sizing: border-box;
        }
        .auth-input:focus {
            outline: none;
            border-color: var(--text-primary);
        }
        .alert {
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
            font-size: 14px;
        }
        .alert-success {
            background: #e8f5e9;
            color: #2e7d32;
            border: 1px solid #c8e6c9;
        }
        .alert-danger {
            background: #ffebee;
            color: #c62828;
            border: 1px solid #ffcdd2;
        }
    </style>
</head>
<body>

    <jsp:include page="/views/partials/navbar.jsp" />

    <div class="auth-container fade-in">
        <div class="auth-card">
            <h1 style="font-size: 32px; margin-bottom: 10px;">Sign In</h1>
            <p style="color: var(--text-secondary); margin-bottom: 30px;">Enter your details to continue.</p>

            <c:if test="${param.msg == 'success'}">
                <div class="alert alert-success">Account created successfully! Please sign in.</div>
            </c:if>
            <c:if test="${param.error == 'invalid'}">
                <div class="alert alert-danger">Invalid email or password. Please try again.</div>
            </c:if>
            
            <c:if test="${param.msg == 'checkout'}">
                <div class="alert alert-danger" style="background: #fff3e0; color: #e65100; border-color: #ffe0b2;">
                    Please sign in or create an account to securely check out.
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/auth" method="POST">
                <input type="hidden" name="action" value="login">
                
                <input type="email" name="email" class="auth-input" placeholder="Email Address" required>
                <input type="password" name="password" class="auth-input" placeholder="Password" required>
                
                <button type="submit" class="btn-primary" style="width: 100%; margin-top: 10px;">Sign In</button>
            </form>
            
            <p style="margin-top: 30px; font-size: 14px; color: var(--text-secondary);">
                New to Coctail? <a href="${pageContext.request.contextPath}/register.jsp" style="color: var(--text-primary); font-weight: 600;">Create Account</a>
            </p>
        </div>
    </div>

    <jsp:include page="/views/partials/footer.jsp" />

</body>
</html>