<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Categories | Coctail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .category-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            gap: 24px;
            padding: 40px 0 100px 0;
        }
        .category-card {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 160px;
            text-decoration: none;
            background: var(--bg-secondary);
            border: 1px solid var(--border-color);
            border-radius: 20px;
            color: var(--text-primary);
            transition: all 0.3s cubic-bezier(0.16, 1, 0.3, 1);
        }
        .category-card:hover {
            transform: translateY(-4px);
            background: #ffffff;
            border-color: #1d1d1f;
            box-shadow: 0 12px 24px rgba(0,0,0,0.06);
        }
        .category-name {
            font-size: 22px;
            font-weight: 600;
            letter-spacing: -0.3px;
        }
    </style>
</head>
<body>

    <jsp:include page="/views/partials/navbar.jsp" />

    <div class="container fade-in">
        <div style="text-align: center; margin-top: 80px; margin-bottom: 20px;">
            <h1 style="font-size: 48px; letter-spacing: -0.5px;">Shop by Category.</h1>
            <p style="color: var(--text-secondary); font-size: 18px; margin-top: 10px;">Find exactly what you're looking for.</p>
        </div>

        <div class="category-grid">
            <c:choose>
                <c:when test="${not empty categories}">
                    <c:forEach var="category" items="${categories}">
                        <a href="${pageContext.request.contextPath}/shop?categoryId=${category.id}" class="category-card">
                            <span class="category-name">${category.name}</span>
                        </a>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div style="grid-column: 1 / -1; text-align: center; padding: 60px; color: var(--text-secondary);">
                        <p>No categories found. Please add categories to your database.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <jsp:include page="/views/partials/footer.jsp" />

</body>
</html>