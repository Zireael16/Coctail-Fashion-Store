<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar" style="justify-content: space-between; padding: 0 40px; display: flex; align-items: center; background: var(--bg-primary); border-bottom: 1px solid var(--border-color); height: 60px;">

    <div style="flex: 1;">
        <a href="${pageContext.request.contextPath}/" style="text-decoration: none; color: #1d1d1f; font-weight: 600; font-size: 15px;">Coctail</a>
    </div>

    <ul class="nav-links" style="flex: 2; display: flex; list-style: none; justify-content: center; align-items: center; margin: 0; gap: 32px; padding: 0;">
        <li><a href="${pageContext.request.contextPath}/shop" style="text-decoration: none; color: rgba(0,0,0,0.8); font-size: 13px; font-weight: 500;">Store</a></li>
        <li><a href="${pageContext.request.contextPath}/categories" style="text-decoration: none; color: rgba(0,0,0,0.8); font-size: 13px; font-weight: 500;">Categories</a></li>
        
        <li>
            <form action="${pageContext.request.contextPath}/shop" method="GET" style="display: flex; align-items: center; margin: 0;">
                <input type="text" name="query" placeholder="Search products..." 
                       style="padding: 8px 16px; border: none; border-radius: 980px; background: rgba(0,0,0,0.05); font-family: inherit; font-size: 12px; outline: none; transition: background 0.3s; width: 180px;">
                <button type="submit" style="display: none;">Search</button>
            </form>
        </li>
    </ul>

    <div style="flex: 1; display: flex; justify-content: flex-end; gap: 24px; align-items: center;">
        <c:choose>
            <c:when test="${not empty sessionScope.currentUser}">
                <!-- FIXED LINK HERE: Removed .jsp -->
                <a href="${pageContext.request.contextPath}/profile" style="text-decoration: none; color: #1d1d1f; font-size: 13px; font-weight: 600; transition: opacity 0.3s;">
                    Hi, ${sessionScope.currentUser.name}
                </a>
                <a href="${pageContext.request.contextPath}/logout" style="text-decoration: none; color: #d32f2f; font-size: 13px; font-weight: 600; transition: color 0.3s;">
                    Logout
                </a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login.jsp" style="text-decoration: none; color: rgba(0,0,0,0.8); font-size: 13px; font-weight: 500; transition: color 0.3s;">
                    Sign In
                </a>
            </c:otherwise>
        </c:choose>
        
        <a href="${pageContext.request.contextPath}/cart" style="text-decoration: none; color: rgba(0,0,0,0.8); font-size: 13px; font-weight: 500; transition: color 0.3s;">
            Cart (<c:out value="${not empty sessionScope.cartItems ? sessionScope.cartItems.size() : 0}" />)
        </a>
    </div>
</nav>