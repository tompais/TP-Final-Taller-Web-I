package ar.edu.unlam.tallerweb1.Enums;

public enum CodigoError {
    NOENCONTRADO (404),
    USUARIOINVALIDO (1),
    USUARIODUPLICADO (2),
    PELICULANOENCONTRADA (3),
    REGISTROINVALIDO (4),
    USUARIONOENCONTRADO (5),
    FECHAULTIMAFUNCIONMENORAFECHAACTUAL (6),
    TIPOFUNCIONINVALIDA (7),
    FUNCIONBYCONFIGURACIONNOENCONTRADA (8);


    private int codigoError;

    CodigoError(int codigoError) {
        this.codigoError = codigoError;
    }

    CodigoError(){
    }

    public int getValor() {
        return codigoError;
    }
}
