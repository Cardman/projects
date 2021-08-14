package code.expressionlanguage.options;

import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.files.DefaultAccess;
import code.util.CustList;
import code.util.StringList;

public final class Options {

    private final StringList typesInit = new StringList();
    private boolean readOnly;
    private boolean covering;
    private boolean gettingErrors;
    private boolean displayImplicit;
    private WarningShow warningShow;
    private boolean encodeHeader = true;
    private final CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
    private final DefaultAccess defaultAccess = new DefaultAccess();
    private int tabWidth = 4;
    private int stack = -1;

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

    public WarningShow getWarningShow() {
        return warningShow;
    }

    public void setWarningShow(WarningShow _warningShow) {
        warningShow = _warningShow;
    }

    public boolean isEncodeHeader() {
        return encodeHeader;
    }

    public void setEncodeHeader(boolean _encodeHeader) {
        encodeHeader = _encodeHeader;
    }

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public DefaultAccess getDefaultAccess() {
        return defaultAccess;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        this.tabWidth = _tabWidth;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int _stack) {
        this.stack = _stack;
    }
}
