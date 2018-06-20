<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="side-nav" id="mobile-menu">
	<li class="primary">
		<div class="userView">
			<div class="background"></div>
			<h5 class="blue-text text-darken-2">Visitame Push</h5>
		</div>
	</li>
	<li><div class="divider"></div></li>
	<li><a href="#" class="btnEnvioMasivo">Envio masivo</a></li>
	<li><div class="divider"></div></li>
	<li><a href="<c:url value="/logout" />">Salir</a></li>
	<li><div class="divider"></div></li>
</ul>


<nav class="navbar-fixed">
	<nav>
		<div class="nav-wrapper blue accent-4">

			<a href="#" data-activates="mobile-menu" class="button-collapse"><i
				class="material-icons">menu</i></a> <a href="#"
				class="brand-logo center"><img class="logo" src="<c:url value="/images/icon-1x.png" />" />ísitame</a>

			<ul class="right hide-on-med-and-down">
				<li><a href="#!" class="btnEnvioMasivo">Envio masivo</a></li>
				<li><a href="<c:url value="/logout" />">Salir</a></li>
			</ul>

		</div>
	</nav>
</nav>
