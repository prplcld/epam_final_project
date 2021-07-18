<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="pages/static/css/list.css">
<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
  <div class="container-fluid">
<%--    <a class="navbar-brand" href="#">--%>
<%--      <img src="pages/static/image/logo.png"/>--%>
<%--    </a>--%>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="controller?command=go_to_cocktails_list">List</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="controller?command=go_to_login">Login</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
