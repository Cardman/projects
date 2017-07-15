package code.formathtml.exceptions;
import code.expressionlanguage.exceptions.IndirectException;
import code.expressionlanguage.opers.util.Struct;

public class SettingArrayException extends IndirectException {

    public SettingArrayException(String _message, Struct _cause) {
        super(_message, _cause);
    }

}
