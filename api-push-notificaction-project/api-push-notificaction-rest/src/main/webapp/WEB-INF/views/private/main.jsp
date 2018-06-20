<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<jsp:include page="include/header-include.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="include/menu.jsp"></jsp:include>

	<div class="progress" id="loadingMaskTop">
		<div class="indeterminate"></div>
	</div>
	<!-- Page Layout here -->
	<div class="row">

		<div class="col s0 m0 l2">&nbsp</div>

		<!-- Cards container -->
		<div class="col s12 m12 l8">

			<div class="input-field col s12">
				<i class="material-icons prefix">search</i> <input
					placeholder="Buscar" id="icon_prefix" type="text">
			</div>

			<div class="row" id="contentUsuarios">
				<!-- List subscritos -->
			</div>
		</div>
	</div>

	<!-- Modal Footer -->
	<div id="modal1" class="modal bottom-sheet">
		<div class="modal-content">
			<h5>
				Notificar a <span id="nombrePersonaSingle"></span>
			</h5>
			<input type="hidden" id="playerId" />
			<div class="row">
				<div class="input-field col s12">
					<input id="tituloSendSingle" type="text" class="validate">
					<label for="tituloSendSingle">Título</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<textarea id="mensajeSingle" class="materialize-textarea"></textarea>
					<label for="mensajeSingle">Mensaje</label>
				</div>
			</div>
			<div class="progress" id="loadingMaskBottom" style="display: none">
				<div class="indeterminate"></div>
			</div>

			<a href="#!" class="modal-action waves-effect waves-green btn-flat"
				id="btnSendSingle"
				style="text-align: center; width: 100%; background-color: #00897b; color: #fff;">Enviar</a>

		</div>

	</div>
	
	<div id="modal2" class="modal modal-fixed-footer">
	    <div class="modal-content">
	      <h4>Envio masivo</h4>
	      <div class="row">
				<div class="progress" id="loadingMaskSendAll" style="display: none">
					<div class="indeterminate"></div>
				</div>
				<div class="input-field col s12">
					<input id="tituloSendAll" type="text" class="validate">
					<label for="tituloSendAll">Título</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<textarea id="mensajeAll" class="materialize-textarea"></textarea>
					<label for="mensajeAll">Mensaje</label>
					
					
					
				</div>
			</div>
			
		
			
			
	    </div>
	    <div class="modal-footer">
	      <a href="#!" class="btn blue white-text modal-action waves-effect waves-green btn-flat" id="btnSendAll">Enviar</a>
	    </div>
	  </div>


	<jsp:include page="include/foot-include.jsp"></jsp:include>
	<script src="https://momentjs.com/downloads/moment.js"></script>
	<script type="text/javascript" src="<c:url value="/js/main.js" />"></script>
</body>
</html>