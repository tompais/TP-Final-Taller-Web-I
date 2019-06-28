var singInButton = $('#singInButton');
var inputEmailOrNick = $('#inputEmailOrNick');
var inputPassword = $('#inputPassword');
var remember = $('#remember');

singInButton.click(function () {
    loguearUsuario();
});

function validarEmail() {
    var validacion = false;
    var emailOrNick = inputEmailOrNick.val();

    if(emailOrNick === null || emailOrNick.length === 0 || emailOrNick === "") {
        $("#errorEmailOrNick").fadeIn("slow").find("span").text("Ingrese su Email o Username");
    } else if(!regexEmail.test(emailOrNick) && !regexLetrasYNumeros.test(emailOrNick)) {
        $("#errorEmailOrNick").fadeIn("slow").find("span").text("Formato de Email o Username Inválido");
    } else {
        validacion = true;
    }

    return validacion;
}

function validarPassword() {
    var validacion = false;
    var pass = inputPassword.val();

    if (pass === null || pass.length === 0 || pass === "") {
        $("#errorPass").fadeIn("slow").find("span").text("Ingrese su contraseña");
        return false;
    } else if(pass.length < 6 || pass.length > 15) {
        $("#errorPass").fadeIn("slow").find("span").text("Entre 6 y 15 caracteres");
    } else if(!regexLetrasYNumeros.test(pass)){
        $("#errorPass").fadeIn("slow").find("span").text("Caracteres inválidos. Solo números y letras");
    } else {
        validacion = true;
    }

    return validacion;
}

function loguearUsuario() {
    $(".error").fadeOut().find("span").text("");

    var validacion = validarEmail() && validarPassword();

    if(validacion) {
        var obj = {};
        obj.emailOrNick = inputEmailOrNick.val();
        obj.password = inputPassword.val();
        obj.rememberMe = remember.prop('checked');
        $.post(pathLogin, obj)
            .done(function (response) {
                window.location.href = pathHome;
            })
            .fail(function (xhr, status, error) {
                alertify.alert("Error de Login", "Usuario y/o contraseña inválido/s. Por favor, revise sus datos y vuelva a intentarlo");
            });
    }
}

$("input").keypress(function (e) {
    if(e.keyCode === 13) {
        loguearUsuario();
    }
});