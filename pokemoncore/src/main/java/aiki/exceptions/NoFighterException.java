/**
    */
package aiki.exceptions;
import java.util.NoSuchElementException;

/**
    @author Cardman
 */
public class NoFighterException extends NoSuchElementException {

    /**
    */
    public NoFighterException() {
    }

    /**
    @param _s
    */
    public NoFighterException(String _s) {
        super(_s);
    }

}
