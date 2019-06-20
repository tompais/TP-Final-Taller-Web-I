package ar.edu.unlam.tallerweb1.enumerables;

public enum CodigoError {
    NOENCONTRADO (404);

    private int codigoError;

    CodigoError(int codigoError) {
        this.codigoError = codigoError;
    }

    CodigoError(){
    }
}
