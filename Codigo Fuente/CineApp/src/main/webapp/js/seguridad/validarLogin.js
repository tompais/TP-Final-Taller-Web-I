const regexEmail = /^[a-zA-Z0-9_\.\-]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$/;
const regexLetrasYNumeros = /^[0-9a-zA-Z]+$/;

var inputEmail = $('#inputEmail');
var inputPassword = $('#inputPassword');
var btnIngresar = $("#btnIngresar");

function validarEmail() {
    var validacion = false;
    var email = inputEmail.val();

    if(email === null || email.length === 0 || email === "") {
    	$("#errorEmail").fadeIn("slow");
    } else if(!regexEmail.test(email)) {
    	$("#errorEmail2").fadeIn("slow");
    } else {
        validacion = true;
    }

    return validacion;
}

function validarPassword() {
    var validacion = false;
    var pass = inputPassword.val();

    if (pass === null || pass.length === 0 || pass === "") {
        $("#errorPass").fadeIn("slow");
        return false;
    } else if(pass.length < 6 || pass.length > 15 || !regexLetrasYNumeros.test(pass)) {
        $("#errorPass2").fadeIn("slow");
    } else {
        validacion = true;
    }

    return validacion;
}

function loguearUsuario() {
    $(".error").fadeOut();

    var validacion = validarEmail() && validarPassword();

    if(validacion) {
    	btnIngresar.prop("type", "submit");
        btnIngresar.submit();
    }
}

btnIngresar.click(function () {
    loguearUsuario();
});

$("input").keypress(function (e) {
    if(e.keyCode === 13) {
        loguearUsuario();
    }
});