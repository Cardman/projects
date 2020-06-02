package code.formathtml.errors;

import code.util.StringMap;

public final class RendAnalysisMessages {
    public static final String BAD_INPUT_NAME="BadInputName";
    public static final String STATIC_INPUT_NAME="StaticInputName";
    public static final String UNEXPECTED_CHILD_TAG="UnexpectedChildTag";
    public static final String EMPTY_ATTR="EmptyAttr";
    public static final String UNEXPECTED_EXP="UnexpectedExp";
    public static final String INEXISTANT_FILE="InexistantFile";
    public static final String INEXISTANT_KEY="InexistantKey";
    public static final String BAD_DOCUMENT="BadDocument";
    private String badInputName="The name expression must refer a field.";
    private String staticInputName="The name {0} of the refered field must refer an instance field.";
    private String unexpectedChildTag="The {0} block must be child of a block {1}.";
    private String emptyAttr="The {0} attribute is empty.";
    private String unexpectedExp="The expression is unexpected.";
    private String inexistantFile="The file {0} does not exist.";
    private String inexistantKey="The key {0} does not exist.";
    private String badDocument="There is an issue in the document at {0}.";

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

    public void setBadInputName(String badInputName) {
        this.badInputName = badInputName;
    }

    public String getStaticInputName() {
        return staticInputName;
    }

    public void setStaticInputName(String staticInputName) {
        this.staticInputName = staticInputName;
    }

    public String getUnexpectedChildTag() {
        return unexpectedChildTag;
    }

    public void setUnexpectedChildTag(String unexpectedChildTag) {
        this.unexpectedChildTag = unexpectedChildTag;
    }

    public String getEmptyAttr() {
        return emptyAttr;
    }

    public void setEmptyAttr(String emptyAttr) {
        this.emptyAttr = emptyAttr;
    }

    public String getUnexpectedExp() {
        return unexpectedExp;
    }

    public void setUnexpectedExp(String unexpectedExp) {
        this.unexpectedExp = unexpectedExp;
    }

    public String getInexistantFile() {
        return inexistantFile;
    }

    public void setInexistantFile(String inexistantFile) {
        this.inexistantFile = inexistantFile;
    }

    public String getInexistantKey() {
        return inexistantKey;
    }

    public void setInexistantKey(String inexistantKey) {
        this.inexistantKey = inexistantKey;
    }

    public String getBadDocument() {
        return badDocument;
    }

    public void setBadDocument(String badDocument) {
        this.badDocument = badDocument;
    }
}
