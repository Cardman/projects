package code.formathtml.exceptions;

import code.util.StringList;

public class NoSuchResourceException extends RuntimeException {

    private static final String SEP = "\n";

    public NoSuchResourceException(String _file) {
        super(_file);
    }

    public NoSuchResourceException(String _file, String _addon) {
        super(StringList.concat(_file,SEP,_addon));
    }
}
