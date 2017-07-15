package code.xml.exceptions;
import code.xml.RowCol;

public class XmlParseException extends RuntimeException {

    private static final String SEPARATOR = "\n";

    private final RowCol rowCol;

    public XmlParseException() {
        rowCol = new RowCol();
    }

    public XmlParseException(String _message) {
        super(_message);
        rowCol = new RowCol();
    }

    public XmlParseException(String _fileName,String _message) {
        super(_fileName+SEPARATOR+_message);
        rowCol = new RowCol();
    }

    public XmlParseException(RowCol _rc) {
        rowCol = _rc;
    }

    public XmlParseException(RowCol _rc, String _message) {
        super(_message);
        rowCol = _rc;
    }

    public XmlParseException(RowCol _rc, String _fileName,String _message) {
        super(_fileName+SEPARATOR+_message);
        rowCol = _rc;
    }

    public RowCol getRowCol() {
        return rowCol;
    }
}
