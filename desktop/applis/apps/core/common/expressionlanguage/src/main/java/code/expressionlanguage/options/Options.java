package code.expressionlanguage.options;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.DefaultAccess;
import code.util.CustList;
import code.util.StringList;

public final class Options {

    private StringList typesInit = new StringList();
    private boolean readOnly;
    private boolean covering;
    private boolean gettingErrors;
    private boolean displayImplicit;
    private final CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
    private final DefaultAccess defaultAccess = new DefaultAccess();

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

    public boolean isDisplayImplicit() {
        return displayImplicit;
    }

    public void setDisplayImplicit(boolean _displayImplicit) {
        this.displayImplicit = _displayImplicit;
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public DefaultAccess getDefaultAccess() {
        return defaultAccess;
    }
}
