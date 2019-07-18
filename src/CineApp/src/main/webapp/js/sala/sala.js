var totalAsientosSeleccionados = 0;
var pFinal = $("#precioFinal");
var spanContadorAsientosDisponibles = $('#spanContadorAsientosDisponibles');
var stompClient = null;
var arrayObjPosAsientosReservados = [];

function inicializarContadorAsientosDisponibles() {
    spanContadorAsientosDisponibles.text(window.asientosDisponibles >= 6 ? '6' : window.asientosDisponibles);
}

inicializarContadorAsientosDisponibles();

function connect() {
    var socket = new SockJS('/CineApp_war_exploded/onAsientoSeleccionado');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/onReceiveAsientoSeleccionado', function (asientoMessageDto) {
            var jsonBody = JSON.parse(asientoMessageDto.body);
            if (jsonBody.funcionId === funcionId && jsonBody.sender !== sender) {
                var asiento = $('#' + jsonBody.fila + "" + jsonBody.columna);
                var num = parseInt(spanContadorAsientosDisponibles.text());
                switch (jsonBody.estadoId) {
                    case asientoReservado:
                        if (--window.asientosDisponibles < 6) {
                            spanContadorAsientosDisponibles.text(--num);
                        }
                        asiento.attr('checked', true).prop('disabled', true).parent().removeClass().addClass('seatReservado');
                        break;
                    case asientoLibre:
                        if (window.asientosDisponibles++ < 6) {
                            spanContadorAsientosDisponibles.text(++num);
                        }
                        asiento.prop('disabled', false).attr('checked', false).parent().removeClass().addClass('seat');
                        break;
                    case asientoOcupado:
                    	asiento.attr('checked', true).prop('disabled', true).parent().removeClass().addClass('seatOcupado');
                    	break;
                    default:
                        break;
                }
            }
        });
    });
}

connect();

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
}

$(window).on('beforeunload', function () {
    $.each(arrayObjPosAsientosReservados, function (i, codigoAsientoReservado) {
        cambiarEstadoAsientoEnServidor(codigoAsientoReservado.fila, codigoAsientoReservado.columna, asientoLibre, false);
    });
    disconnect();
});

function cambiarEstadoAsientoEnServidor(fila, columna, estadoId, async) {
    var obj = {};
    obj.fila = parseInt(fila);
    obj.columna = parseInt(columna);
    obj.funcionId = funcionId;
    obj.estadoId = estadoId;
    obj.sender = sender;
    llamadaAjax(pathActualizarEstadoAsiento, JSON.stringify(obj), async, 'onAsientoSeleccionado', 'cambiarEstadoAsientoEnServidorFallido', obj);
}

function cambiarEstadoAsientoEnServidorFallido(err, asientoMessageDto) {
    var contador = parseInt(spanContadorAsientosDisponibles.text());
    var objPos = {};
    objPos.fila = asientoMessageDto.fila;
    objPos.columna = asientoMessageDto.columna;
    arrayObjPosAsientosReservados.splice(arrayObjPosAsientosReservados.indexOf(objPos), 1);
    window.asientosDisponibles++;
    totalAsientosSeleccionados--;
    spanContadorAsientosDisponibles.text(++contador);
    if (totalAsientosSeleccionados) {
        pFinal.text("Precio: $" + precioUnitario * totalAsientosSeleccionados + ".00");
    } else {
        pFinal.addClass('d-none');
    }
    var asiento = $('#' + asientoMessageDto.fila + "" + asientoMessageDto.columna);
    if (err.search(/reservado/i) !== -1) {
        asiento.attr('checked', true).prop('disabled', true).parent().removeClass().addClass('seatReservado');
    } else if (err.search(/ocupado/i) !== -1) {
        asiento.attr('checked', true).prop('disabled', true).parent().removeClass().addClass('seatOcupado');
    } else {
        asiento.prop('disabled', false).attr('checked', false).parent().removeClass().addClass('seat');
    }
    alertify.alert('Error', err);
}

function onAsientoSeleccionado(obj, dummy) {
    stompClient.send("/app/onAsientoSeleccionado", {},
        JSON.stringify(obj));
}

$("input[type='checkbox']").change(function (e) {
    var contador = parseInt(spanContadorAsientosDisponibles.text());
    var objPos = {};
    objPos.fila = $(this).attr('fila');
    objPos.columna = $(this).attr('columna');
    if ($(this).is(":checked")) {
        if (window.asientosDisponibles === 0 || contador === 0) {
            $(this).prop('checked', false);
            e.stopPropagation();
            e.preventDefault();
        } else {
            arrayObjPosAsientosReservados.push(objPos);
            cambiarEstadoAsientoEnServidor(objPos.fila, objPos.columna, asientoReservado, false);
            window.asientosDisponibles--;
            totalAsientosSeleccionados++;
            spanContadorAsientosDisponibles.text(--contador);
            pFinal.removeClass('d-none');
            pFinal.text("Precio: $" + precioUnitario * totalAsientosSeleccionados + ".00");
        }
    } else if ($(this).is(":not(:checked)")) {
        arrayObjPosAsientosReservados.splice(arrayObjPosAsientosReservados.indexOf(objPos), 1);
        cambiarEstadoAsientoEnServidor(objPos.fila, objPos.columna, asientoLibre, false);
        window.asientosDisponibles++;
        totalAsientosSeleccionados--;
        spanContadorAsientosDisponibles.text(++contador);
        if (totalAsientosSeleccionados) {
            pFinal.text("Precio: $" + precioUnitario * totalAsientosSeleccionados + ".00");
        } else {
            pFinal.addClass('d-none');
        }
    }
});

function reservar() {
	if(arrayObjPosAsientosReservados.length > 0) {
		var filas = [];
		var columnas = [];
		
		$.each(arrayObjPosAsientosReservados, function(index, asiento){
			filas.push(parseInt(asiento.fila));
			columnas.push(parseInt(asiento.columna));
		});

		var obj = {};
		obj.filas = filas;
		obj.columnas = columnas;
		obj.funcionId = funcionId;

		llamadaAjax(pathReserva, JSON.stringify(obj), true, 'reservaExitosa', 'errorReserva');
	}
	else
		alertify.alert("Debe seleccionar al menos un asiento para realizar una reserva");
}

function reservaExitosa(numeroTicket) {
	
	$.each(arrayObjPosAsientosReservados, function(index, asiento){
		cambiarEstadoAsientoEnServidor(asiento.fila, asiento.columna, asientoOcupado, false);
	});
	
	arrayObjPosAsientosReservados = [];
	window.location.href = pathReservaExitosa + '?nt=' + btoa(encodeURI(numeroTicket));
}

function errorReserva(err) {
	alertify.alert("Error al registrar la reserva", err);
}