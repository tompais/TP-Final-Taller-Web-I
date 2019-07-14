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
                        window.asientosDisponibles++;
                        if (window.asientosDisponibles++ < 6) {
                            spanContadorAsientosDisponibles.text(num++);
                        }
                        asiento.prop('disabled', false).attr('checked', false).parent().removeClass().addClass('seat');
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
        cambiarEstadoAsientoEnServidor(codigoAsientoReservado.fila, codigoAsientoReservado.columna, asientoLibre);
    });
    disconnect();
});

function cambiarEstadoAsientoEnServidor(fila, columna, estadoId) {
    var obj = {};
    obj.fila = parseInt(fila);
    obj.columna = parseInt(columna);
    obj.funcionId = funcionId;
    obj.estadoId = estadoId;
    obj.sender = sender;
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
            cambiarEstadoAsientoEnServidor(objPos.fila, objPos.columna, asientoReservado);
            window.asientosDisponibles--;
            totalAsientosSeleccionados++;
            spanContadorAsientosDisponibles.text(--contador);
            pFinal.removeClass('d-none');
            pFinal.text("Precio: $" + precioUnitario * totalAsientosSeleccionados + ".00");
        }
    } else if ($(this).is(":not(:checked)")) {
        arrayObjPosAsientosReservados.splice(arrayObjPosAsientosReservados.indexOf(objPos), 1);
        cambiarEstadoAsientoEnServidor(objPos.fila, objPos.columna, asientoLibre);
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