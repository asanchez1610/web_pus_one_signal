var MainAdmin = {
		
		baseProy : '/push-notification',
		
		init : function(){
			$(document).ready(function(){
				$(".button-collapse").sideNav();
				$('.modal').modal();
				MainAdmin.listarSubscriptores();
				$('#btnSendSingle').click(MainAdmin.sendSingleNotificacion);
				$('#icon_prefix').keyup(MainAdmin.buscar);
				$('.btnEnvioMasivo').click(MainAdmin.openEnvioMasivo);
				$('#btnSendAll').click(MainAdmin.sendAll);
			});
		},
		
		openEnvioMasivo : function(){
			$('#tituloSendAll').val('');
			$('#mensajeAll').val('');
			$('#loadingMaskSendAll').hide(); 
			$('#btnSendAll').removeClass('disabled');
			$('#modal2').modal('open');
		},
		
		sendAll : function(){
			
			var titulo = $('#tituloSendAll').val();
			var mensaje = $('#mensajeAll').val();
			
			if(titulo.length == 0){
				$('#tituloSendAll').focus();
				$('#tituloSendAll').removeClass('validate');
				$('#tituloSendAll').addClass('invalid');
			    return;
			 }
			
			 $('#tituloSendAll').removeClass('invalid');
			 $('#tituloSendAll').addClass('validate');
			
			if(mensaje.length == 0){
				$('#mensajeAll').focus();
				$('#mensajeAll').removeClass('validate');
				$('#mensajeAll').addClass('invalid');
			    return;
			 }
			
			 $('#mensajeAll').removeClass('invalid');
			 $('#mensajeAll').addClass('validate');
			 
			 $('#loadingMaskSendAll').show(); 
			 
			 $(this).addClass('disabled');
			 
			 var paramMensaje = {
					 	titulo:titulo,
						mensaje:mensaje
			 }
			 
			 $.ajax({
				    method: "POST",
				    url:  MainAdmin.baseProy+"/admin/notificacions/all",
				    dataType: "json",
				    data: paramMensaje
				  }).done(function(response){
					  $('#loadingMaskSendAll').hide(); 
					  $('#modal2').modal('close');
					  swal(
							  'Listo!',
							  'las notificaciones han sido enviadas.',
							  'success'
							);
				  });
			 
			
		},
		
		buscar : function(){
			
			var s = $(this);
			
			if(s.val().length == 0){
				$('.contenedorListaResults').show();
				return;
			}
			
			$('.contenedorListaResults').hide();
			
			$('.nombreSubscriptorB').each(function(){
				var a = ($(this).html()+'').toLowerCase();
				var b = (s.val()+'').toLowerCase();
				if(a.indexOf(b) >= 0){
					$(this).parent().parent().parent().show();
				}
			});
			
		},
		
		listarSubscriptores : function(){
		
			$.ajax({
			    method: "GET",
			    url: MainAdmin.baseProy+"/admin/subscriptores/listar",
			    dataType: "json"
			  }).done(function(response){
				  $('#loadingMaskTop').hide();
			    console.log(response);
			    if(response.data){
			    	var fecha = '';
			    	response.data.forEach(function(item){
			    		
			    		if(item.registro){
			    			fecha = moment(new Date(item.registro)).format("DD/MM/YYYY HH:mm")
			    		}
			    		
			    		$('#contentUsuarios').append('<div class="col s12 m6 l4 contenedorListaResults">'+
						    		        '<div class="card">'+
						  		          '<div class="card-content white-text center">'+
						  		          '<div class="center" style="margin-bottom: 10px;">'+
						  		         	'<img class="responsive-img" src="'+MainAdmin.baseProy+'/images/user-icon-blue.png" />'+
						  		         '</div>'+
						  		            '<span class="card-title amber-text text-darken-4 nombreSubscriptorB">'+item.nombre+'</span>'+
						  		            '<p class="card-subtitle grey-text text-darken-2">registrado el '+fecha+'</p>'+
						  		          '</div>'+
						  		          '<div class="card-action center">'+
						  		            '<a href="#" class="card-action-right btn waves-light enviarMensajeSingle" data-nombre="'+item.nombre+'" data-playerid="'+item.playerId+'" >Enviar mensaje</a>'+
						  		          '</div>'+
						  		        '</div>'+
						  		      '</div>');
			    	});
			    	
			    	$('.enviarMensajeSingle').click(MainAdmin.openModalSingleMensaje);
			    	
			    }
			    
			  });
			
		},
		
		openModalSingleMensaje: function(){
			$('#nombrePersonaSingle').html($(this).data('nombre'));
			$('#playerId').val($(this).data('playerid'));
			$('#tituloSendSingle').val('');
			$('#mensajeSingle').val('');
			$('#loadingMaskBottom').hide();
			$('#btnSendSingle').removeClass('disabled');
			$('#modal1').modal('open');
		},
		
		sendSingleNotificacion : function(btn){
			
			var titulo = $('#tituloSendSingle').val();
			var mensaje = $('#mensajeSingle').val();
			var playerId = $('#playerId').val();
			
			if(titulo.length == 0){
				$('#tituloSendSingle').focus();
				$('#tituloSendSingle').removeClass('validate');
				$('#tituloSendSingle').addClass('invalid');
			    return;
			 }
			
			 $('#tituloSendSingle').removeClass('invalid');
			 $('#tituloSendSingle').addClass('validate');
			
			if(mensaje.length == 0){
				$('#mensajeSingle').focus();
				$('#mensajeSingle').removeClass('validate');
				$('#mensajeSingle').addClass('invalid');
			    return;
			 }
			
			 $('#mensajeSingle').removeClass('invalid');
			 $('#mensajeSingle').addClass('validate');
			
			 var paramMensaje = {
					 	titulo:titulo,
						mensaje:mensaje,
						playerId:playerId
			 }
			 
			 $('#loadingMaskBottom').show(); 
			 $('#btnSendSingle').addClass('disabled');
			 
			 $.ajax({
				    method: "POST",
				    url:  MainAdmin.baseProy+"/admin/notificacions/single",
				    dataType: "json",
				    data: paramMensaje
				  }).done(function(response){
					  $('#loadingMaskBottom').hide(); 
					  $('#modal1').modal('close'); 
					  console.log(response);
					  swal(
							  'Listo!',
							  'La notificacion ha sido enviada.',
							  'success'
							);
				  });
		}
		
}

MainAdmin.init();
