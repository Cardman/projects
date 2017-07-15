package aiki.exceptions;

public class NoWildPokemonException extends RuntimeException {

    public NoWildPokemonException() {
    }

    public NoWildPokemonException(String _message) {
        super(_message);
    }

}
