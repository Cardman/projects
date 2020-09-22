package code.expressionlanguage.options;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.util.CustList;
import code.util.StringList;

public final class Options {

    private StringList typesInit = new StringList();
    private boolean readOnly;
    private boolean covering;
    private boolean gettingErrors;
    private boolean failIfNotAllInit;
    private final CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();

    public StringList getTypesInit() {
        return typesInit;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean _readOnly) {
        readOnly = _readOnly;
    }

    public boolean isCovering() {
        return covering;
    }

    public void setCovering(boolean _covering) {
        covering = _covering;
    }

    public boolean isGettingErrors() {
        return gettingErrors;
    }

    public void setGettingErrors(boolean _gettingErrors) {
        gettingErrors = _gettingErrors;
    }

    public boolean isFailIfNotAllInit() {
        return failIfNotAllInit;
    }

    public void setFailIfNotAllInit(boolean _failIfNotAllInit) {
        failIfNotAllInit = _failIfNotAllInit;
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

}
