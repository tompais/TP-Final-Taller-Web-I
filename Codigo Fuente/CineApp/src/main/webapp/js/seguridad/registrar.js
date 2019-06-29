var inputNombre = $('#inputNombre');
var inputApellido = $('#inputApellido');
var inputEmail = $('#inputEmail');
var inputNickname = $('#inputNickname');
var inputPassword = $('#inputPassword');
var inputRePassword = $('#inputRePassword');
var checkboxAgreePolitics = $('#checkboxAgreePolitics');
var inputFechaNacimiento = $("#inputFechaNacimiento");
var btnInputFechaNacimiento = $("#btnInputFechaNacimiento");
var btnRegistrar = $('#btnRegistrar');

inicializarDatePicker();

function inicializarDatePicker() {

    inputFechaNacimiento.daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        opens: "left",
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'), 10),
        startDate: moment().format("DD/MM/YYYY"),
        locale: {
            format: "DD/MM/YYYY",
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

function showPassword(btn) {
    var pwd = $(btn).parent().siblings(".pwd");
    var eye = $(btn).children();

    if(pwd.attr("type") === "password") {
        pwd.attr("type", "text");
        eye.removeClass("fa-eye").addClass("fa-eye-slash");
    } else {
        pwd.attr("type", "password");
        eye.removeClass("fa-eye-slash").addClass("fa-eye");
    }
}

function validarNombre() {
    var nombre = inputNombre.val();
    var validacion = false;

    if(isNullOrEmpty(nombre)) {
        $('#errorNombre').fadeIn('slow').find('span').text('Ingrese su Nombre');
    } else if(!regexLetrasYEspacio.test(nombre)) {
        $('#errorNombre').fadeIn('slow').find('span').text('Formato de nombre inválido');
    } else if(nombre.length < 3 || nombre.length > 10) {
        $('#errorNombre').fadeIn('slow').find('span').text('Entre 3 y 10 letras únicamente');
    } else {
        validacion = true;
    }

    return validacion;
}

function validarApellido() {
    var apellido = inputApellido.val();
    var validacion = false;

    if(isNullOrEmpty(apellido)) {
        $('#errorApellido').fadeIn('slow').find('span').text('Ingrese su Apellido');
    } else if(!regexLetrasYEspacio.test(apellido)) {
        $('#errorApellido').fadeIn('slow').find('span').text('Formato de apellido inválido');
    } else if(apellido.length < 3 || apellido.length > 15) {
        $('#errorApellido').fadeIn('slow').find('span').text('Entre 3 y 15 letras únicamente');
    } else {
        validacion = true;
    }

    return validacion;
}

function validarEmail() {
    var email = inputEmail.val();
    var validacion = false;

    if(isNullOrEmpty(email)) {
        $('#errorEmail').fadeIn('slow').find('span').text('Ingrese su Email');
    } else if(!regexEmail.test(email)) {
        $('#errorEmail').fadeIn('slow').find('span').text('Formato de email inválido');
    } else {
        validacion = true;
    }

    return validacion;
}

function validarNickname() {
    var nickname = inputNickname.val();
    var validacion = false;

    if(isNullOrEmpty(nickname)) {
        $('#errorNickname').fadeIn('slow').find('span').text('Ingrese su Nickname');
    } else if(!regexLetrasYNumeros.test(nickname)) {
        $('#errorNickname').fadeIn('slow').find('span').text('Formato de nickname inválido');
    } else if(nickname.length < 3 || nickname.length > 15) {
        $('#errorNickname').fadeIn('slow').find('span').text('Entre 3 y 15 caracteres');
    } else {
        validacion = true;
    }

    return validacion;
}

function validarPassword() {
    var pass = inputPassword.val();
    var validacion = false;

    if(isNullOrEmpty(pass)) {
        $('#errorPassword').fadeIn('slow').find('span').text('Ingrese su contraseña');
    } else if(!regexLetrasYNumeros.test(pass)) {
        $('#errorPassword').fadeIn('slow').find('span').text('Ingrese caracteres válidos');
    } else if(pass.length < 5 || pass.length > 15) {
        $('#errorPassword').fadeIn('slow').find('span').text('Entre 5 y 15 caracteres');
    } else {
        validacion = true;
    }

    return validacion;
}

function confirmarPassword() {
    var pass = inputPassword.val();
    var rePass = inputRePassword.val();
    var validacion = false;

    if(isNullOrEmpty(rePass)) {
        $('#errorRePassword').fadeIn('slow').find('span').text('Confirme su contraseña');
    } else if(pass !== rePass) {
        $('#errorRePassword').fadeIn('slow').find('span').text('Las contraseñas no coinciden');
    } else {
        validacion = true;
    }

    return validacion;
}

function validarFechaNacimiento() {
    var fechaNacimiento = inputFechaNacimiento.val();

    var validacion = false;

    if (!moment(fechaNacimiento, "DD/MM/YYYY").isValid()) {
        $("#errorFechaNacimiento").fadeIn("slow").find('span').text('Formato de fecha inválido');
    } else if (Math.round(moment.duration(moment().diff(moment(fechaNacimiento, "DD/MM/YYYY"))).asYears()) < 18) {
        $("#errorFechaNacimiento").fadeIn("slow").find('span').text('Debe ser mayor de 18 años');
    } else {
        validacion = true;
    }

    return validacion;
}

function confirmarPoliticas() {
    var validacion = false;

    if(!checkboxAgreePolitics.prop('checked')) {
        $('#errorPoliticas').fadeIn('slow').find('span').text('Para continuar, acepte las políticas');
    } else {
        validacion = true;
    }

    return validacion;
}

btnRegistrar.click(function () {
    $('.error').fadeOut().find('span').empty();

    var validacion = validarNombre() && validarApellido() && validarEmail()
        && validarNickname() && validarPassword() && confirmarPassword()
    && validarFechaNacimiento() && confirmarPoliticas();

    if(validacion) {
        var obj = {};
        obj.nombre = inputNombre.val();
        obj.apellido = inputApellido.val();
        obj.username = inputNickname.val();
        obj.email = inputEmail.val();
        obj.uPassword = inputPassword.val();
        obj.fechaNacimiento = moment(inputFechaNacimiento.val(), 'DD/MM/YYYY').format('YYYY-MM-DD');
        $.post(pathRegistrar, obj)
            .done(function (response) {
                window.location.href = pathHome;
            })
            .fail(function (xhr, status, error) {
                alertify.alert("Error de Registración", xhr.responseText);
            });
    }
});