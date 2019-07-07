var selectCine = $('#selectCine');
var divFormGroupTipoFuncion = $('#divFormGroupTipoFuncion');
var selectTipoFuncion = $('#selectTipoFuncion');
var divFormGroupFechaCompra = $('#divFormGroupFechaCompra');
var inputFechaCompra = $('#inputFechaCompra');
var btnInputFechaCompra = $('#btnInputFechaCompra');
var divFormGroupHorarioCompra = $('#divFormGroupHorarioCompra');
var selectHorarioCompra = $('#selectHorarioCompra');
var textSinHorariosCompra = $('#textSinHorariosCompra');
var anchorSeleccionarSala = $('#anchorSeleccionarSala');

/*Anchor seleccionar asiento*/

function getFuncionIdByConfiguracionExitoso(data) {
    console.log(data); //TODO cambiar para que arme un href que lleve a la siguiente pantalla
    habilitarElemento(anchorSeleccionarSala);
    anchorSeleccionarSala.removeClass('d-none');
}

function inicializarAnchorSeleccionarAsiento() {
    var obj = {};
    obj.peliculaId = parseInt(peliculaId);
    obj.cineId = parseInt(selectCine.val());
    obj.tipoFuncionId = parseInt(selectTipoFuncion.val());
    obj.dia = moment(inputFechaCompra.val(), "DD/MM/YYYY").format("YYYY-MM-DDTHH:mm:ssZ");
    obj.hora = moment(selectHorarioCompra.val(), "HH:mm").format("HH:mm:ss");
    llamadaAjax(pathGetFuncionIdByConfiguracion, JSON.stringify(obj), true, "getFuncionIdByConfiguracionExitoso", "dummy");
}

function showHideAnchorSeleccionarAsiento() {
    deshabilitarElemento(anchorSeleccionarSala);
    if(selectHorarioCompra.val() != 0) {
        inicializarAnchorSeleccionarAsiento();
    } else {
        anchorSeleccionarSala.addClass('d-none');
        anchorSeleccionarSala.attr('href', '#');
    }
}

/*Fin anchor seleccionar asiento*/

/*Select horario compra*/

function getHorariosLibresFuncionExitoso(horarios) {
    if(horarios === null || horarios.length === 0) {
        divFormGroupHorarioCompra.addClass('d-none');
        textSinHorariosCompra.removeClass('d-none');
    } else {
        textSinHorariosCompra.addClass('d-none');
        divFormGroupHorarioCompra.removeClass('d-none');
        $.each(horarios, function (i, horario) {
            var option = $('<option>');
            var horarioFormateado = moment(horario).format('HH:mm');
            option.val(horarioFormateado);
            option.text(horarioFormateado);
            selectHorarioCompra.append(option);
        });
        habilitarElemento(selectHorarioCompra);
    }
}

function inicializarSelectHorarioCompra() {
    deshabilitarElemento(selectHorarioCompra);
    var obj = {};
    obj.peliculaId = parseInt(peliculaId);
    obj.cineId = parseInt(selectCine.val());
    obj.dia = moment(inputFechaCompra.val(), 'DD/MM/YYYY').format('YYYY-MM-DDTHH:mm:ssZ');
    obj.tipoFuncionId = parseInt(selectTipoFuncion.val());
    llamadaAjax(pathGetHorariosLibresFuncion, JSON.stringify(obj), true, "getHorariosLibresFuncionExitoso", "dummy");
}

function showHideHorarioCompra() {
    reinicializarSelect(selectHorarioCompra, 'Seleccione un horario...');
    if(inputFechaCompra.val() !== null && inputFechaCompra.val() !==  "") {
        inicializarSelectHorarioCompra();
    } else {
        divFormGroupHorarioCompra.addClass('d-none');
    }
}

selectHorarioCompra.change(function () {
    showHideAnchorSeleccionarAsiento();
});

/*Fin select horario compra*/

/*Datepicker fecha compra*/

function inicializarDatePicker(maxDate) {
    inputFechaCompra.daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        opens: "left",
        minDate: moment(),
        maxDate: maxDate,
        startDate: moment().format("DD/MM/YYYY"),
        locale: {
            format: "DD/MM/YYYY",
            daysOfWeek: nombresDias,
            monthNames: nombresMeses
        }
    });

    inputFechaCompra.mask("99/99/9999", {
        translation: {
            'r': {
                pattern: /[\/]/,
                fallback: '/'
            },
            placeholder: "__/__/____"
        },
        placeholder: "__/__/____",
        selectOnFocus: true
    });
}

function destruirDatePicker() {
    if(inputFechaCompra.data('daterangepicker') !== undefined) {
        inputFechaCompra.data('daterangepicker').remove();
    }
    inputFechaCompra.val('');
}

btnInputFechaCompra.click(function () {
    inputFechaCompra.data('daterangepicker').toggle();
});

function getRangoFechaCompraExitoso(date) {
    inicializarDatePicker(moment(date).format("DD/MM/YYYY"));
    divFormGroupFechaCompra.removeClass('d-none');
}

function inicializarFechaCompra(tipoFuncionId) {
    var obj = {};
    obj.peliculaId = parseInt(peliculaId);
    obj.cineId = parseInt(selectCine.val());
    obj.tipoFuncionId = parseInt(tipoFuncionId);
    llamadaAjax(pathGetRangoFechaCompra, JSON.stringify(obj), true, 'getRangoFechaCompraExitoso', 'dummy');
}

function showHideFechaCompra(tipoFuncionId) {
    if(tipoFuncionId != 0) {
        inicializarFechaCompra(tipoFuncionId);
    } else {
        divFormGroupFechaCompra.addClass('d-none');
        destruirDatePicker();
    }
}

inputFechaCompra.change(function () {
    showHideHorarioCompra();
});

/*Fin Datepicker fecha compra*/

/*Select Tipo Función*/

function getTipoFuncionesDisponiblesExitoso(tipoFunciones) {
    $.each(tipoFunciones, function (i, tipoFuncion) {
        var option = $('<option>');
        option.val(tipoFuncion.id);
        option.text(tipoFuncion.tipo);
        selectTipoFuncion.append(option);
    });
    divFormGroupTipoFuncion.removeClass('d-none');
    habilitarElemento(selectTipoFuncion);
}

function inicializarSelectTipoFuncion() {
    var obj = {};
    obj.peliculaId = parseInt(peliculaId);
    obj.cineId = parseInt(selectCine.val());
    llamadaAjax(pathGetTipoFuncionesDisponibles, JSON.stringify(obj), true, "getTipoFuncionesDisponiblesExitoso", "dummy");
}

function showHideTipoFuncion() {
    deshabilitarElemento(selectTipoFuncion);
    reinicializarSelect(selectTipoFuncion, 'Seleccione un tipo de función...');
    showHideFechaCompra(selectTipoFuncion.find('option').val());
    if(selectCine.val() != 0) {
        inicializarSelectTipoFuncion();
    } else {
        divFormGroupTipoFuncion.addClass('d-none');
    }
}

selectTipoFuncion.change(function () {
    showHideFechaCompra(selectTipoFuncion.val());
});

/*Fin Select Tipo Función*/

/*Select Cine*/

selectCine.change(function () {
    showHideTipoFuncion();
});

/*Fin Select cine*/