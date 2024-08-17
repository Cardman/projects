package code.formathtml.errors;

import code.expressionlanguage.stds.LgNamesContent;
import code.sml.util.TranslationsFile;
import code.util.StringMap;

public final class RendAnalysisMessages {
    public static final String FILE="messages";
    public static final String BAD_INPUT_NAME="0";
    public static final String STATIC_INPUT_NAME="1";
    public static final String UNEXPECTED_CHILD_TAG="2";
    public static final String EMPTY_ATTR="3";
    public static final String UNEXPECTED_EXP="4";
    public static final String INEXISTANT_FILE="5";
    public static final String INEXISTANT_KEY="6";
    public static final String BAD_DOCUMENT="7";
    private String badInputName="The name expression must refer a field.";
    private String staticInputName="The name {0} of the refered field must refer an instance field.";
    private String unexpectedChildTag="The {0} block must be child of a block {1}.";
    private String emptyAttr="The {0} attribute is empty.";
    private String unexpectedExp="The expression is unexpected.";
    private String inexistantFile="The file {0} does not exist.";
    private String inexistantKey="The key {0} does not exist.";
    private String badDocument="There is an issue in the document at {0}.";
    public static TranslationsFile en() {
        TranslationsFile en_ = new TranslationsFile();
        en_.add(BAD_INPUT_NAME,"BadInputName=The name expression must refer a field.");
        en_.add(STATIC_INPUT_NAME,"StaticInputName=The name {0} of the refered field must refer an instance field.");
        en_.add(UNEXPECTED_CHILD_TAG,"UnexpectedChildTag=The {0} block must be child of a block {1}.");
        en_.add(EMPTY_ATTR,"EmptyAttr=The {0} attribute is empty.");
        en_.add(UNEXPECTED_EXP,"UnexpectedExp=The expression is unexpected.");
        en_.add(INEXISTANT_FILE,"InexistantFile=The file {0} does not exist.");
        en_.add(INEXISTANT_KEY,"InexistantKey=The key {0} does not exist.");
        en_.add(BAD_DOCUMENT,"BadDocument=There is an issue in the document at {0}.");
        return en_;
    }
    public static TranslationsFile fr() {
        TranslationsFile fr_ = new TranslationsFile();
        fr_.add(BAD_INPUT_NAME,"BadInputName=Le nom expression doit référer un champ.");
        fr_.add(STATIC_INPUT_NAME,"StaticInputName=Le nom {0} du champ référé doit référer un champ d''instance.");
        fr_.add(UNEXPECTED_CHILD_TAG,"UnexpectedChildTag=Le bloc {0} doit être enfant d''un bloc {1}.");
        fr_.add(EMPTY_ATTR,"EmptyAttr=L''attribut {0} est vide.");
        fr_.add(UNEXPECTED_EXP,"UnexpectedExp=L''expression est inattendue.");
        fr_.add(INEXISTANT_FILE,"InexistantFile=Le fichier {0} n''existe pas.");
        fr_.add(INEXISTANT_KEY,"InexistantKey=La clé {0} n''existe pas.");
        fr_.add(BAD_DOCUMENT,"BadDocument=Il y a un problème dans le document à {0}.");
        return fr_;
    }
    public void rendMessages(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setBadInputName(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_INPUT_NAME)));
        setStaticInputName(LgNamesContent.get(_util, _cust, _mapping.getVal(STATIC_INPUT_NAME)));
        setUnexpectedChildTag(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_CHILD_TAG)));
        setEmptyAttr(LgNamesContent.get(_util, _cust, _mapping.getVal(EMPTY_ATTR)));
        setUnexpectedExp(LgNamesContent.get(_util, _cust, _mapping.getVal(UNEXPECTED_EXP)));
        setInexistantFile(LgNamesContent.get(_util, _cust, _mapping.getVal(INEXISTANT_FILE)));
        setInexistantKey(LgNamesContent.get(_util, _cust, _mapping.getVal(INEXISTANT_KEY)));
        setBadDocument(LgNamesContent.get(_util, _cust, _mapping.getVal(BAD_DOCUMENT)));
    }
    public StringMap<String> allMessages(StringMap<String> _mapping) {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry(_mapping.getVal(BAD_INPUT_NAME),getBadInputName());
        mess_.addEntry(_mapping.getVal(STATIC_INPUT_NAME),getStaticInputName());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_CHILD_TAG),getUnexpectedChildTag());
        mess_.addEntry(_mapping.getVal(EMPTY_ATTR),getEmptyAttr());
        mess_.addEntry(_mapping.getVal(UNEXPECTED_EXP),getUnexpectedExp());
        mess_.addEntry(_mapping.getVal(INEXISTANT_FILE),getInexistantFile());
        mess_.addEntry(_mapping.getVal(INEXISTANT_KEY),getInexistantKey());
        mess_.addEntry(_mapping.getVal(BAD_DOCUMENT),getBadDocument());
        return mess_;
    }
    public static StringMap<String> mapping() {
        StringMap<String> mess_ = new StringMap<String>();
        mess_.addEntry(BAD_INPUT_NAME,"BadInputName");
        mess_.addEntry(STATIC_INPUT_NAME,"StaticInputName");
        mess_.addEntry(UNEXPECTED_CHILD_TAG,"UnexpectedChildTag");
        mess_.addEntry(EMPTY_ATTR,"EmptyAttr");
        mess_.addEntry(UNEXPECTED_EXP,"UnexpectedExp");
        mess_.addEntry(INEXISTANT_FILE,"InexistantFile");
        mess_.addEntry(INEXISTANT_KEY,"InexistantKey");
        mess_.addEntry(BAD_DOCUMENT,"BadDocument");
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
