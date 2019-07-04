var cineElegido = $("#cineElegido");

cineElegido.change(function(){
	var obj = {};
    obj.idCine = $(this).val();
    $.post("funciones", obj)
        .done(function (response) {
            
        })
        .fail(function (xhr, status, error) {
            alertify.alert("Error con las funciones", xhr.responseText);
        });
});