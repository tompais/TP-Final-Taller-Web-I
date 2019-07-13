var totalAsientosSeleccionados = 0;
var pFinal = $("#precioFinal");
var spanContadorAsientosDisponibles = $('#spanContadorAsientosDisponibles');

inicializarContadorAsientosDisponibles();

function inicializarContadorAsientosDisponibles() {
    spanContadorAsientosDisponibles.text(window.asientosDisponibles >= 6 ? '6' : window.asientosDisponibles);
}

$("input[type='checkbox']").prop("checked", false).change(function (e) {
    var contador = parseInt(spanContadorAsientosDisponibles.text());
    if ($(this).is(":checked")) {
        if (window.asientosDisponibles === 0 || contador === 0) {
            $(this).prop('checked', false);
            e.stopPropagation();
            e.preventDefault();
        } else {
            window.asientosDisponibles--;
            totalAsientosSeleccionados++;
            spanContadorAsientosDisponibles.text(--contador);
            pFinal.removeClass('d-none');
            pFinal.text("Precio: $" + precioUnitario * totalAsientosSeleccionados + ".00");
        }
    } else if ($(this).is(":not(:checked)")) {
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