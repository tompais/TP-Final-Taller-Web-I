const nombresMeses = [
    "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
    "Diciembre"
];

const nombresDias = ["D", "L", "M", "M", "J", "V", "S"];

var inputFechaNacimiento = $("#inputFechaNacimiento");
var btnInputFechaNacimiento = $("#btnInputFechaNacimiento");

inicializarDatePicker();

function inicializarDatePicker() {

    inputFechaNacimiento.daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        opens: "left",
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'), 10),
        startDate: moment().format("MM/DD/YYYY"),
        locale: {
            format: "MM/DD/YYYY",
            daysOfWeek: nombresDias,
            monthNames: nombresMeses
        }
    });

    inputFechaNacimiento.mask("99/99/9999", {
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

btnInputFechaNacimiento.click(function () {
    inputFechaNacimiento.data("daterangepicker").toggle();
});