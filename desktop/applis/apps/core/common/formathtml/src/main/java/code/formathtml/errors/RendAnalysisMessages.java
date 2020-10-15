package code.formathtml.errors;

import code.expressionlanguage.stds.LgNamesContent;
import code.util.StringMap;

public final class RendAnalysisMessages {
    private static final String BAD_INPUT_NAME="BadInputName";
    private static final String STATIC_INPUT_NAME="StaticInputName";
    private static final String UNEXPECTED_CHILD_TAG="UnexpectedChildTag";
    private static final String EMPTY_ATTR="EmptyAttr";
    private static final String UNEXPECTED_EXP="UnexpectedExp";
    private static final String INEXISTANT_FILE="InexistantFile";
    private static final String INEXISTANT_KEY="InexistantKey";
    private static final String BAD_DOCUMENT="BadDocument";
    private String badInputName="The name expression must refer a field.";
    private String staticInputName="The name {0} of the refered field must refer an instance field.";
    private String unexpectedChildTag="The {0} block must be child of a block {1}.";
    private String emptyAttr="The {0} attribute is empty.";
    private String unexpectedExp="The expression is unexpected.";
    private String inexistantFile="The file {0} does not exist.";
    private String inexistantKey="The key {0} does not exist.";
    private String badDocument="There is an issue in the document at {0}.";

    public void rendMessages(StringMap<String> _util, StringMap<String> _cust) {
        setBadInputName(LgNamesContent.get(_util, _cust, BAD_INPUT_NAME));
        setStaticInputName(LgNamesContent.get(_util, _cust, STATIC_INPUT_NAME));
        setUnexpectedChildTag(LgNamesContent.get(_util, _cust, UNEXPECTED_CHILD_TAG));
        setEmptyAttr(LgNamesContent.get(_util, _cust, EMPTY_ATTR));
        setUnexpectedExp(LgNamesContent.get(_util, _cust, UNEXPECTED_EXP));
        setInexistantFile(LgNamesContent.get(_util, _cust, INEXISTANT_FILE));
        setInexistantKey(LgNamesContent.get(_util, _cust, INEXISTANT_KEY));
        setBadDocument(LgNamesContent.get(_util, _cust, BAD_DOCUMENT));
    }
    public StringMap<String> allMessages() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry(BAD_INPUT_NAME,getBadInputName());
        mess_.addEntry(STATIC_INPUT_NAME,getStaticInputName());
        mess_.addEntry(UNEXPECTED_CHILD_TAG,getUnexpectedChildTag());
        mess_.addEntry(EMPTY_ATTR,getEmptyAttr());
        mess_.addEntry(UNEXPECTED_EXP,getUnexpectedExp());
        mess_.addEntry(INEXISTANT_FILE,getInexistantFile());
        mess_.addEntry(INEXISTANT_KEY,getInexistantKey());
        mess_.addEntry(BAD_DOCUMENT,getBadDocument());
        return mess_;
    }

    public String getBadInputName() {
        return badInputName;
    }

    public void setBadInputName(String _badInputName) {
        this.badInputName = _badInputName;
    }

    public String getStaticInputName() {
        return staticInputName;
    }

    public void setStaticInputName(String _staticInputName) {
        this.staticInputName = _staticInputName;
    }

    public String getUnexpectedChildTag() {
        return unexpectedChildTag;
    }

    public void setUnexpectedChildTag(String _unexpectedChildTag) {
        this.unexpectedChildTag = _unexpectedChildTag;
    }

    public String getEmptyAttr() {
        return emptyAttr;
    }

    public void setEmptyAttr(String _emptyAttr) {
        this.emptyAttr = _emptyAttr;
    }

    public String getUnexpectedExp() {
        return unexpectedExp;
    }

    public void setUnexpectedExp(String _unexpectedExp) {
        this.unexpectedExp = _unexpectedExp;
    }

    public String getInexistantFile() {
        return inexistantFile;
    }

    public void setInexistantFile(String _inexistantFile) {
        this.inexistantFile = _inexistantFile;
    }

    public String getInexistantKey() {
        return inexistantKey;
    }

    public void setInexistantKey(String _inexistantKey) {
        this.inexistantKey = _inexistantKey;
    }

    public String getBadDocument() {
        return badDocument;
    }

    public void setBadDocument(String _badDocument) {
        this.badDocument = _badDocument;
    }
}
