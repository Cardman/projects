package code.util.exceptions;

public class NullComparatorException extends RuntimeException {

    public NullComparatorException() {
    }

    public NullComparatorException(String _nullCmp) {
        super(_nullCmp);
    }

}
