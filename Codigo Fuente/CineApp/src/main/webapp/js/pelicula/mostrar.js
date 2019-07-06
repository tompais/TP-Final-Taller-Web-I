var selectCine = $('#selectCine');
var divFormGroupTipoFuncion = $('#divFormGroupTipoFuncion');
var selectTipoFuncion = $('#selectTipoFuncion');
var divFormGroupFechaCompra = $('#divFormGroupFechaCompra');
var inputFechaCompra = $('#inputFechaCompra');
var btnInputFechaCompra = $('#btnInputFechaCompra');

/*Select horario compra*/



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
    habilitarElemento(inputFechaCompra);
}

function inicializarFechaCompra(tipoFuncionId) {
    deshabilitarElemento(inputFechaCompra);
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
    deshabilitarElemento(selectTipoFuncion);
    var obj = {};
    obj.peliculaId = parseInt(peliculaId);
    obj.cineId = parseInt(selectCine.val());
    llamadaAjax(pathGetTipoFuncionesDisponibles, JSON.stringify(obj), true, "getTipoFuncionesDisponiblesExitoso", "dummy");
}

function showHideTipoFuncion() {
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