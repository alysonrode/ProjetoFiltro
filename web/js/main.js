converteSenha = function() {

    document.input.senha.value = btoa(document.input.senha.value)

    alert("Senha em Base64: " + document.input.senha.value);

}