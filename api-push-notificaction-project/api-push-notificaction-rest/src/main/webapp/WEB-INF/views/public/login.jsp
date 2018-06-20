<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="include/header-include.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="include/top.jsp"></jsp:include>

	<div class="progress" id="loadingMaskTop" style="display: none;">
		<div class="indeterminate"></div>
	</div>
	<!-- Page Layout here -->

<div class="container">
      <div class="row">

          <div class="col s12 l6 m6 offset-l3 offset-m3 z-depth-1" id="panell">
            <h5 id="title">Acceso</h5>
            
		     <div class="row message-error-login" style="padding-bottom: 0px;margin-bottom: 0px;display: none;">
		        <div class="col s12 m12 l12">
		          <div class="card red darken-1">
		            <div class="card-content white-text">
		              <p><c:if test="${mensajeError != null}">${mensajeError}</c:if></p>
		            </div>
		          </div>
		        </div>
		      </div>
            
            <form action="<c:url value="/autenticar" />" method="post">

            <div class="input-field" id="username">
              <input  
              		type="text" 
              		class="validate"
              		name="username"
              		value="<c:if test="${username != null}">${username}</c:if>"
              		>
              <label for="username">Usuario</label>
          </div>
          <div class="input-field" id="password">
            <input  type="password"  name="password"  class="validate">
            <label for="password">Password</label>
        </div>
        <button type="submit" class="waves-effect waves-light btn" id="loginbtn">Login</button>
        </form>

        </div>
      </div>

    </div>
	<jsp:include page="include/foot-include.jsp"></jsp:include>
	<script type="text/javascript" src="<c:url value="/js/login.js" />"></script>
	<c:if test="${mensajeError != null}">
	<script type="text/javascript">
		var msgError = '${mensajeError}';
		var isError = (msgError.length > 0);
		if (isError) {
			$('.message-error-login').show();
		}
	</script>
</c:if>
</body>
</html>