var selectCine = $('#selectCine');
var divFormGroupFechaCompra = $('#divFormGroupFechaCompra');
var inputFechaCompra = $('#inputFechaCompra');
var btnInputFechaCompra = $('#btnInputFechaCompra');

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

btnInputFechaCompra.click(function () {
    inputFechaCompra.data('daterangepicker').toggle();
});

function getRangoFechaCompraExitoso(date) {
    inicializarDatePicker(moment(date).format("DD/MM/YYYY"));
    divFormGroupFechaCompra.removeClass('d-none');
}

function inicializarFechaCompra() {
    var obj = {};
    obj.peliculaId = parseInt(peliculaId);
    obj.cineId = parseInt(selectCine.val());
    llamadaAjax(pathGetRangoFechaCompra, JSON.stringify(obj), true, 'getRangoFechaCompraExitoso', 'dummy');
}

function showHideFechaCompra() {
    if(selectCine.val() != 0) {
        inicializarFechaCompra();
    } else {
        divFormGroupFechaCompra.addClass('d-none');
    }
}

/*Fin Datepicker fecha compra*/

/*Select Cine*/

selectCine.change(function () {
    showHideFechaCompra();
});

/*Fin Select cine*/