var singInButton = $('#singInButton');
var inputEmailOrNick = $('#inputEmailOrNick');
var inputPassword = $('#inputPassword');
var remember = $('#remember');

singInButton.click(function () {
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
});