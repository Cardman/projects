package code.formathtml.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.formathtml.structs.MessageStruct;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class DefaultBeanAliases {
    public static final String BEAN = "Bean";
    public static final String MAP_KEYS = "MapKeys";
    public static final String MAP_VALUES = "MapValues";
    public static final String MAP_INDEX_OF_ENTRY = "MapIndexOfEntry";
    public static final String MAP_ADD_ENTRY = "MapAddEntry";
    public static final String MAP_GET_VALUE = "MapGetValue";
    public static final String MAP_FIRST_VALUE = "MapFirstValue";
    public static final String MAP_LAST_VALUE = "MapLastValue";
    public static final String MAP_SET_VALUE = "MapSetValue";
    public static final String MAP_PUT = "MapPut";
    public static final String MAP_CONTAINS = "MapContains";
    public static final String MAP_PUT_ALL = "MapPutAll";
    public static final String MAP_GET_VAL = "MapGetVal";
    public static final String MAP_REMOVE_KEY = "MapRemoveKey";
    public static final String MAP_GET_KEY = "MapGetKey";
    public static final String MAP_FIRST_KEY = "MapFirstKey";
    public static final String MAP_LAST_KEY = "MapLastKey";
    public static final String MAP_SET_KEY = "MapSetKey";
    public static final String MAP_SIZE = "MapSize";
    public static final String MAP_IS_EMPTY = "MapIsEmpty";
    public static final String MAP_CLEAR = "MapClear";
    public static final String VALIDATOR = "Validator";
    public static final String VALIDATE = "Validate";
    public static final String DATA_BASE_FIELD = "DataBaseField";
    public static final String FORMS = "Forms";
    public static final String SET_FORMS = "SetForms";
    public static final String GET_FORMS = "GetForms";
    public static final String LANGUAGE = "Language";
    public static final String SET_LANGUAGE = "SetLanguage";
    public static final String GET_LANGUAGE = "GetLanguage";
    public static final String SCOPE = "Scope";
    public static final String SET_SCOPE = "SetScope";
    public static final String GET_SCOPE = "GetScope";
    public static final String SET_DATA_BASE = "SetDataBase";
    public static final String GET_DATA_BASE = "GetDataBase";
    public static final String BEFORE_DISPLAYING = "BeforeDisplaying";
    public static final String STRING_MAP_OBJECT = "StringMapObject";
    public static final String MESSAGE = "Message";
    public static final String NEW_MESSAGE = "NewMessage";
    public static final String MESSAGE_FORMAT = "MessageFormat";
    public static final String MESSAGE_GET_ARGS = "MessageGetArgs";
    public static final String MESSAGE_SET_ARGS = "MessageSetArgs";

    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;


    private String iteratorTableVarCust;
    private String hasNextPairVarCust;
    private String nextPairVarCust;
    private String firstVarCust;
    private String secondVarCust;
    private String beforeDisplayingVar;

    private String putVarCust;
    private String putVarCustKey;
    private String putVarCustValue;
    private String putAllVarCust;
    private String putAllVarCustArg;
    private String getValVar;
    private String getValVarArg;
    private String getFormsVar;
    private String setFormsVarArg;
    private String setFormsVar;
    private String getDataBaseVar;
    private String setDataBaseVarArg;
    private String setDataBaseVar;
    private String getScopeVar;
    private String setScopeVarArg;
    private String setScopeVar;
    private String setLanguageVarArg;
    private String setLanguageVar;
    private String aliasBean = "code.bean.Bean";
    private String aliasMapKeys = "keys";
    private String aliasMapValues = "values";
    private String aliasMapIndexOfEntry = "indexOfEntry";
    private String aliasMapAddEntry = "addEntry";
    private String aliasMapGetValue = "getValue";
    private String aliasMapFirstValue = "firstValue";
    private String aliasMapLastValue = "lastValue";
    private String aliasMapSetValue = "setValue";
    private String aliasMapPut = "put";
    private String aliasMapContains = "contains";
    private String aliasMapPutAll = "putAll";
    private String aliasMapGetVal = "getVal";
    private String aliasMapRemoveKey = "removeKey";
    private String aliasMapGetKey = "getKey";
    private String aliasMapFirstKey = "firstKey";
    private String aliasMapLastKey = "lastKey";
    private String aliasMapSetKey = "setKey";
    private String aliasMapSize = "size";
    private String aliasMapIsEmpty = "isEmpty";
    private String aliasMapClear = "clear";

    private String aliasValidator="code.bean.Validator";
    private String aliasValidate="validate";
    private String validateVar;
    private String validateVarArgNewValue;
    private String validateVarArgOldValue;
    private String validateVarArgBean;
    private String validateVarArgForm;
    private String validateVarArgClassField;
    private String vlidateVarArgNameField;

    private String aliasDataBaseField= "dataBase";

    private String aliasForms= "forms";
    private String aliasSetForms= "setForms";
    private String aliasGetForms= "getForms";
    private String aliasLanguage= "language";
    private String aliasSetLanguage= "setLanguage";
    private String aliasGetLanguage= "getLanguage";
    private String aliasScope= "scope";
    private String aliasSetScope= "setScope";
    private String aliasGetScope= "getScope";
    private String aliasSetDataBase= "setDataBase";
    private String aliasGetDataBase= "getDataBase";
    private String aliasBeforeDisplaying= "beforeDisplaying";
    private String aliasStringMapObject="code.util.StringMapObject";
    private String aliasMessage="code.bean.Message";
    private String aliasNewMessage="newStandardMessage";
    private String aliasMessageFormat="format";
    private String aliasMessageGetArgs="getArgs";
    private String aliasMessageSetArgs="setArgs";

    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content, StringList _predefinedClasses, StringList _predefinedInterfacesInitOrder) {
        StringMap<String> files_ = new StringMap<String>();
        KeyWords keyWords_ = _keyWords;
        StringMap<PrimitiveType> primitiveTypes_ = _content.getPrimTypes().getPrimitiveTypes();
        AliasCore coreNames_ = _content.getCoreNames();
        StringList predefinedInterfacesInitOrder_ = _predefinedClasses;
        String public_ = keyWords_.getKeyWordPublic();
        String private_ = keyWords_.getKeyWordPrivate();
        String interface_ = keyWords_.getKeyWordInterface();
        String class_ = keyWords_.getKeyWordClass();
        String return_ = keyWords_.getKeyWordReturn();
        String if_ = keyWords_.getKeyWordIf();
        String for_ = keyWords_.getKeyWordFor();
        String null_ = keyWords_.getKeyWordNull();
        String new_ = keyWords_.getKeyWordNew();
        String int_ = _content.getPrimTypes().getAliasPrimInteger();
        String endLine_ = String.valueOf(';');
        String suffixParam_ = "";
        StringBuilder file_ = new StringBuilder();
        file_.append(public_).append(" ").append(class_).append(" ").append(getAliasBean()).append("{");
        String string_ = _content.getCharSeq().getAliasString();
        String language_ = getAliasLanguage();
        String scope_ = getAliasScope();
        String dataBase_ = getAliasDataBaseField();
        String this_ = keyWords_.getKeyWordThis();
        String object_ = coreNames_.getAliasObject();
        String forms_ = getAliasForms();
        String boolean_ = _content.getPrimTypes().getAliasPrimBoolean();
        String length_ = _content.getCoreNames().getAliasArrayLength();
        file_.append(" ").append(private_).append(" ").append(string_).append(" ")
                .append(language_).append(endLine_);
        file_.append(" ").append(private_).append(" ").append(string_).append(" ")
                .append(scope_).append(endLine_);
        file_.append(" ").append(private_).append(" ").append(object_).append(" ")
                .append(dataBase_).append(endLine_);
        file_.append(" ").append(private_).append(" ").append(getAliasStringMapObject()).append(" ")
                .append(forms_).append(endLine_);
        String void_ = coreNames_.getAliasVoid();
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasBeforeDisplaying()).append("(){");
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(string_).append(" ")
                .append(getAliasGetLanguage()).append("(){");
        file_.append("  ").append(return_).append(" ").append(language_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetLanguage()).append("(").append(string_).append(" ").append(language_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(language_).append("=")
                .append(language_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(string_).append(" ")
                .append(getAliasGetScope()).append("(){");
        file_.append("  ").append(return_).append(" ").append(scope_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetScope()).append("(").append(string_).append(" ").append(scope_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(scope_).append("=")
                .append(scope_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(object_).append(" ")
                .append(getAliasGetDataBase()).append("(){");
        file_.append("  ").append(return_).append(" ").append(dataBase_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetDataBase()).append("(").append(object_).append(" ")
                .append(dataBase_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(dataBase_).append("=")
                .append(dataBase_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(getAliasStringMapObject())
                .append(" ").append(getAliasGetForms()).append("(){");
        file_.append("  ").append(return_).append(" ").append(forms_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetForms()).append("(").append(getAliasStringMapObject())
                .append(" ").append(forms_).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(forms_).append("=")
                .append(forms_).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append("}");
        files_.put(getAliasBean(), file_.toString());
        predefinedInterfacesInitOrder_.add(getAliasBean());
        file_ = new StringBuilder();
        String keys_ = getAliasMapKeys();
        String values_ = getAliasMapValues();
        String indexOfEntry_ = getAliasMapIndexOfEntry();
        String addEntry_ = getAliasMapAddEntry();
        String getValue_ = getAliasMapGetValue();
        String setValue_ = getAliasMapSetValue();
        String put_ = getAliasMapPut();
        String putAll_ = getAliasMapPutAll();
        String getVal_ = getAliasMapGetVal();
        String removeKey_ = getAliasMapRemoveKey();
        String getKey_ = getAliasMapGetKey();
        String setKey_ = getAliasMapSetKey();
        file_.append(public_).append(" ").append(class_).append(" ").append(getAliasStringMapObject())
                .append("{");
        file_.append(private_).append(" ").append(string_).append("[] ").append(keys_)
                .append("=").append(new_).append(" ").append(string_).append("[0]").append(endLine_);
        file_.append(private_).append(" ").append(object_).append("[] ").append(values_)
                .append("=").append(new_).append(" ").append(object_).append("[0]").append(endLine_);
        file_.append(public_).append(" ").append(string_).append("[] ").append(keys_).append("(){");
        file_.append(return_).append(" ").append(keys_).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(string_).append(" ").append(aliasMapFirstKey).append("(){");
        file_.append(return_).append(" ").append(keys_).append("[0]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(string_).append(" ").append(aliasMapLastKey).append("(){");
        file_.append(return_).append(" ").append(keys_).append("[").append(keys_).append(".")
                .append(length_).append("-1]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append("[] ").append(values_).append("(){");
        file_.append(return_).append(" ").append(values_).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(aliasMapFirstValue).append("(){");
        file_.append(return_).append(" ").append(values_).append("[0]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(aliasMapLastValue).append("(){");
        file_.append(return_).append(" ").append(values_).append("[").append(values_)
                .append(".").append(length_).append("-1]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(setKey_).append("(")
                .append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append(",").append(string_)
                .append(" ").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(this_).append(".").append(keys_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("]=").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(string_).append(" ").append(getKey_)
                .append("(").append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(return_).append(" ").append(this_).append(".").append(keys_).append("[")
                .append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(setValue_)
                .append("(").append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append(",").append(object_).append(" ").append(tr("v", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(this_).append(".").append(values_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("]=").append(tr("v", keyWords_, primitiveTypes_, coreNames_)).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(getValue_).append("(")
                .append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(return_).append(" ").append(this_).append(".").append(values_)
                .append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(aliasMapClear).append("(){");
        file_.append(keys_).append("=").append(new_).append(" ").append(string_)
                .append("[0]").append(endLine_);
        file_.append(values_).append("=").append(new_).append(" ").append(object_)
                .append("[0]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(boolean_).append(" ").append(aliasMapIsEmpty).append("(){");
        file_.append(return_).append(" ").append(keys_).append(".").append(length_)
                .append("==0").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(int_).append(" ").append(aliasMapSize).append("(){");
        file_.append(return_).append(" ").append(keys_).append(".").append(length_).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ")
                .append(put_).append("(").append(string_).append(" ")
                .append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append(",").append(object_)
                .append(" ").append(tr("v", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(int_).append(" ").append(tr("index", keyWords_, primitiveTypes_, coreNames_)).append("=")
                .append(indexOfEntry_).append("(").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append(")")
                .append(endLine_);
        file_.append(if_).append("(").append(tr("index", keyWords_, primitiveTypes_, coreNames_)).append("==-1){");
        file_.append(addEntry_).append("(").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append(", ")
                .append(tr("v", keyWords_, primitiveTypes_, coreNames_)).append(")").append(endLine_);
        file_.append(return_).append(endLine_);
        file_.append("}");
        file_.append(setValue_).append("(").append(tr("index", keyWords_, primitiveTypes_, coreNames_)).append(", ")
                .append(tr("v", keyWords_, primitiveTypes_, coreNames_)).append(")").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(boolean_).append(" ").append(aliasMapContains)
                .append("(").append(string_).append(" ").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(return_).append(" ").append(indexOfEntry_).append("(")
                .append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append(") != -1").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(getVal_).append("(")
                .append(string_).append(" ").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(int_).append(" ").append(tr("index", keyWords_, primitiveTypes_, coreNames_)).append("=").append(indexOfEntry_)
                .append("(").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append(")").append(endLine_);
        file_.append(if_).append("(").append(tr("index", keyWords_, primitiveTypes_, coreNames_)).append("==-1){");
        file_.append(return_).append(" ").append(null_).append(endLine_);
        file_.append("}");
        file_.append(return_).append(" ").append(getValue_).append("(").append(tr("index", keyWords_, primitiveTypes_, coreNames_))
                .append(")").append(endLine_);
        file_.append("}");

        file_.append(public_).append(" ").append(void_).append(" ")
                .append(addEntry_).append("(").append(string_).append(" ")
                .append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append(",").append(object_)
                .append(" ").append(tr("v", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(string_).append("[] ").append(keys_).append("=").append(new_)
                .append(" ").append(string_).append("[").append(this_)
                .append(".").append(keys_).append(".").append(length_)
                .append("+1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("=0").append(endLine_).append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("<")
                .append(this_).append(".")
                .append(keys_).append(".").append(length_).append(endLine_)
                .append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("++){");
        file_.append(tr(keys_, keyWords_, primitiveTypes_, coreNames_)).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("]=").append(this_).append(".").append(keys_).append("[")
                .append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("]").append(endLine_);
        file_.append("}");
        file_.append(tr(keys_, keyWords_, primitiveTypes_, coreNames_)).append("[").append(this_).append(".").append(keys_)
                .append(".").append(length_).append("]=").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append(endLine_);
        file_.append(this_).append(".").append(keys_).append("=").append(tr(keys_, keyWords_, primitiveTypes_, coreNames_)).append(endLine_);
        file_.append(object_).append("[] ").append(values_).append("=").append(new_).append(" ")
                .append(object_).append("[").append(this_).append(".").append(values_).append(".")
                .append(length_).append("+1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("=0").append(endLine_).append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("<")
                .append(this_).append(".").append(values_).append(".").append(length_).append(endLine_)
                .append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("++){");
        file_.append(tr(values_, keyWords_, primitiveTypes_, coreNames_)).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("]=").append(this_).append(".").append(values_).append("[")
                .append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("]").append(endLine_);
        file_.append("}");
        file_.append(tr(values_, keyWords_, primitiveTypes_, coreNames_)).append("[").append(this_).append(".").append(values_)
                .append(".").append(length_).append("]=").append(tr("v", keyWords_, primitiveTypes_, coreNames_)).append(endLine_);
        file_.append(this_).append(".").append(values_).append("=").append(tr(values_, keyWords_, primitiveTypes_, coreNames_))
                .append(endLine_);
        file_.append("}");

        file_.append(public_).append(" ").append(int_).append(" ").append(indexOfEntry_)
                .append("(").append(string_).append(" ").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(for_).append("(").append(int_)
                .append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("=0")
                .append(endLine_).append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("<").append(this_)
                .append(".").append(keys_).append(".").append(length_).append(endLine_)
                .append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("++){");
        file_.append(if_).append("(").append(this_).append(".").append(keys_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("]==").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(return_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append(endLine_);
        file_.append("}");
        file_.append("}");
        file_.append(return_).append(" -1").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(putAll_).append("(")
                .append(getAliasStringMapObject()).append(" ").append(tr("m", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(int_).append(" ").append(tr("len", keyWords_, primitiveTypes_, coreNames_)).append("=").append("(")
                .append(tr("m", keyWords_, primitiveTypes_, coreNames_)).append(").").append(keys_).append(".")
                .append(length_).append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("=0").append(endLine_).append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("<")
                .append(tr("len", keyWords_, primitiveTypes_, coreNames_)).append(endLine_).append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("++){");
        file_.append(" ").append(put_).append("(").append("(")
                .append(tr("m", keyWords_, primitiveTypes_, coreNames_)).append(").")
                .append(keys_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("], ").append("(").append(tr("m", keyWords_, primitiveTypes_, coreNames_))
                .append(").").append(values_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("])").append(endLine_);
        file_.append("}");
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(removeKey_)
                .append("(").append(string_).append(" ").append(tr("k", keyWords_, primitiveTypes_, coreNames_)).append("){");
        file_.append(int_).append(" ").append(tr("index", keyWords_, primitiveTypes_, coreNames_))
                .append("=").append(indexOfEntry_).append("(").append(tr("k", keyWords_, primitiveTypes_, coreNames_))
                .append(")").append(endLine_);
        file_.append(if_).append("(").append(tr("index", keyWords_, primitiveTypes_, coreNames_)).append("==-1){");
        file_.append(return_).append(endLine_);
        file_.append("}");
        file_.append(string_).append("[] ").append(keys_).append("=").append(new_)
                .append(" ").append(string_).append("[").append(this_).append(".").append(keys_)
                .append(".").append(length_).append("-1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("=0")
                .append(endLine_).append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("<").append(tr("index", keyWords_, primitiveTypes_, coreNames_))
                .append(endLine_).append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("++){");
        file_.append(keys_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("]=")
                .append(this_).append(".").append(keys_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("]").append(endLine_);
        file_.append("}");
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("=").append(tr("index", keyWords_, primitiveTypes_, coreNames_)).append("+1").append(endLine_)
                .append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("<").append(this_).append(".")
                .append(keys_).append(".").append(length_).append(endLine_)
                .append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("++){");
        file_.append(keys_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("-1]=").append(this_)
                .append(".").append(keys_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("]")
                .append(endLine_);
        file_.append("}");
        file_.append(this_).append(".").append(keys_).append("=").append(keys_).append(endLine_);

        file_.append(object_).append("[] ").append(values_).append("=").append(new_).append(" ")
                .append(object_).append("[").append(this_).append(".")
                .append(values_).append(".").append(length_).append("-1]")
                .append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("=0")
                .append(endLine_).append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("<").append(tr("index", keyWords_, primitiveTypes_, coreNames_))
                .append(endLine_).append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("++){");
        file_.append(values_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("]=")
                .append(this_).append(".").append(values_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("]").append(endLine_);
        file_.append("}");
        file_.append(for_).append("(").append(int_).append(" ").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("=").append(tr("index", keyWords_, primitiveTypes_, coreNames_)).append("+1").append(endLine_)
                .append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("<").append(this_).append(".").append(values_)
                .append(".").append(length_).append(endLine_).append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("++){");
        file_.append(values_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_)).append("-1]=")
                .append(this_).append(".").append(values_).append("[").append(tr("i", keyWords_, primitiveTypes_, coreNames_))
                .append("]").append(endLine_);
        file_.append("}");
        file_.append(this_).append(".").append(values_).append("=").append(values_).append(endLine_);
        file_.append("}");
        file_.append("}");
        files_.put(getAliasStringMapObject(), file_.toString());
        predefinedInterfacesInitOrder_.add(getAliasStringMapObject());
        file_ = new StringBuilder();
        file_.append(public_).append(" ").append(interface_).append(" ")
                .append(aliasValidator).append("{");
        file_.append(public_).append(" ").append(getAliasMessage()).append(" ")
                .append(aliasValidate).append("(");
        file_.append(object_).append(" ").append(tr("newValue", keyWords_, primitiveTypes_, coreNames_)).append(",");
        file_.append(object_).append(" ").append(tr("oldValue", keyWords_, primitiveTypes_, coreNames_)).append(",");
        file_.append(object_).append(" ").append(tr("bean", keyWords_, primitiveTypes_, coreNames_)).append(",");
        file_.append(object_).append("[] ").append(tr("form", keyWords_, primitiveTypes_, coreNames_)).append(",");
        file_.append(string_).append(" ").append(tr("className", keyWords_, primitiveTypes_, coreNames_)).append(",");
        file_.append(string_).append(" ").append(tr("fieldName", keyWords_, primitiveTypes_, coreNames_));
        file_.append(")");
        file_.append(endLine_);
        file_.append("}");
        files_.put(aliasValidator, file_.toString());
        predefinedInterfacesInitOrder_.add(aliasValidator);
        return files_;
    }
    private static String tr(String _var, KeyWords _keyWords, StringMap<PrimitiveType> _primitiveTypes, AliasCore _coreNames) {
        CustList<String> allKeysWords_ = _keyWords.allKeyWords().values();
        allKeysWords_.addAllElts(_primitiveTypes.getKeys());
        allKeysWords_.add(_coreNames.getAliasVoid());
        return getCandidate(_var, allKeysWords_);
    }

    private static String getCandidate(String _var, CustList<String> allKeysWords_) {
        String candidate_ = _var;
        int index_ = 0;
        while (StringList.contains(allKeysWords_,candidate_)) {
            candidate_ = StringList.concatNbs(_var,index_);
            index_++;
        }
        return candidate_;
    }
    public void buildOther(LgNamesContent _content) {
        CustList<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        std_ = new StandardClass(aliasMessage, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageFormat, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageGetArgs, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasMessageSetArgs, params_, _content.getCoreNames().getAliasVoid(), true, MethodModifier.NORMAL);
        methods_.add( method_);
        _content.getStandards().addEntry(aliasMessage, std_);
    }

    public StringMap<String> allRefTypes() {
        StringMap<String> types_ = new StringMap<String>();
        types_.addEntry(MESSAGE,getAliasMessage());
        types_.addEntry(VALIDATOR,getAliasValidator());
        types_.addEntry(STRING_MAP_OBJECT,getAliasStringMapObject());
        types_.addEntry(BEAN,getAliasBean());
        return types_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> methods_ = new StringMap<CustList<KeyValueMemberName>>();
        methods_.addEntry(getAliasMessage(),
                new CustList<KeyValueMemberName>(new KeyValueMemberName(NEW_MESSAGE,getAliasNewMessage()),
                        new KeyValueMemberName(MESSAGE_FORMAT,getAliasMessageFormat()),
                        new KeyValueMemberName(MESSAGE_GET_ARGS,getAliasMessageGetArgs()),
                        new KeyValueMemberName(MESSAGE_SET_ARGS,getAliasMessageSetArgs())));
        methods_.addEntry(getAliasValidator(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(VALIDATE,getAliasValidate())));
        methods_.addEntry(getAliasStringMapObject(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(MAP_GET_VAL,getAliasMapGetVal()),
                new KeyValueMemberName(MAP_PUT,getAliasMapPut()),
                new KeyValueMemberName(MAP_PUT_ALL,getAliasMapPutAll()),
                new KeyValueMemberName(MAP_INDEX_OF_ENTRY,getAliasMapIndexOfEntry()),
                new KeyValueMemberName(MAP_ADD_ENTRY,getAliasMapAddEntry()),
                new KeyValueMemberName(MAP_CONTAINS,getAliasMapContains()),
                new KeyValueMemberName(MAP_SIZE,getAliasMapSize()),
                new KeyValueMemberName(MAP_IS_EMPTY,getAliasMapIsEmpty()),
                new KeyValueMemberName(MAP_CLEAR,getAliasMapClear()),
                new KeyValueMemberName(MAP_REMOVE_KEY,getAliasMapRemoveKey()),
                new KeyValueMemberName(MAP_FIRST_VALUE,getAliasMapFirstValue()),
                new KeyValueMemberName(MAP_LAST_VALUE,getAliasMapLastValue()),
                new KeyValueMemberName(MAP_GET_VALUE,getAliasMapGetValue()),
                new KeyValueMemberName(MAP_SET_VALUE,getAliasMapSetValue()),
                new KeyValueMemberName(MAP_FIRST_KEY,getAliasMapFirstKey()),
                new KeyValueMemberName(MAP_LAST_KEY,getAliasMapLastKey()),
                new KeyValueMemberName(MAP_GET_KEY,getAliasMapGetKey()),
                new KeyValueMemberName(MAP_SET_KEY,getAliasMapSetKey())
        ));
        methods_.addEntry(getAliasBean(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BEFORE_DISPLAYING,getAliasBeforeDisplaying()),
                new KeyValueMemberName(GET_DATA_BASE,getAliasGetDataBase()),
                new KeyValueMemberName(GET_LANGUAGE,getAliasGetLanguage()),
                new KeyValueMemberName(GET_SCOPE,getAliasGetScope()),
                new KeyValueMemberName(GET_FORMS,getAliasGetForms()),
                new KeyValueMemberName(SET_DATA_BASE,getAliasSetDataBase()),
                new KeyValueMemberName(SET_LANGUAGE,getAliasSetLanguage()),
                new KeyValueMemberName(SET_SCOPE,getAliasSetScope()),
                new KeyValueMemberName(SET_FORMS,getAliasSetForms())
        ));
        return methods_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> fields_ = new StringMap<CustList<KeyValueMemberName>>();
        fields_.addEntry(getAliasMessage(),
                new CustList<KeyValueMemberName>(
                        new KeyValueMemberName(MAP_KEYS,getAliasMapKeys()),
                        new KeyValueMemberName(MAP_VALUES,getAliasMapValues())));
        fields_.addEntry(getAliasBean(),
                new CustList<KeyValueMemberName>(
                        new KeyValueMemberName(FORMS,getAliasForms()),
                        new KeyValueMemberName(LANGUAGE,getAliasLanguage()),
                        new KeyValueMemberName(DATA_BASE_FIELD,getAliasDataBaseField()),
                        new KeyValueMemberName(SCOPE,getAliasScope())));
        return fields_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        StringList list_ = _method.getConstraints().getParametersTypes();
        String type_ = _method.getClassName();
        if (StringList.quickEq(type_, _cont.getStandards().getContent().getCoreNames().getAliasEnums())) {
            return ApplyCoreMethodUtil.getOtherResultBase(_cont, _method, _args);
        }
        String name_ = _method.getConstraints().getName();
        MessageStruct instance_ = getMessageStruct(_instance, aliasMessage);
        if (StringList.quickEq(name_, aliasNewMessage)) {
            if (list_.isEmpty()) {
                res_.setResult(MessageStruct.newInstance(Message.newStandardMessage(),aliasMessage));
            } else {
                String value_ = NumParsers.getString(_args[0]).getInstance();
                res_.setResult(MessageStruct.newInstance(Message.newStandardMessage(value_),aliasMessage));
            }
            return res_;
        }
        if (StringList.quickEq(name_, aliasMessageFormat)) {
            res_.setResult(BeanLgNames.wrapStd(instance_.getMessage()));
            return res_;
        }
        if (StringList.quickEq(name_, aliasMessageGetArgs)) {
            StringList resArgs_ = instance_.getArgs();
            String arrStr_ = StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getCharSeq().getAliasString());
            int len_ = resArgs_.size();
            ArrayStruct arr_ = new ArrayStruct(len_,arrStr_);
            for (int i = 0; i < len_; i++){
                arr_.set(i, BeanLgNames.wrapStd(resArgs_.get(i)));
            }
            res_.setResult(arr_);
            return res_;
        }
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_args[0], _cont);
        int len_ = array_.getLength();
        String[] resArgs_ = new String[len_];
        for (int i = 0; i < len_; i++){
            Struct argInst_ = array_.get(i);
            if (argInst_ instanceof StringStruct) {
                resArgs_[i] = ((StringStruct)argInst_).getInstance();
            } else {
                resArgs_[i] = _cont.getStandards().getDisplayedStrings().getNullString();
            }
        }
        instance_.setArgs(resArgs_);
        res_.setResult(NullStruct.NULL_VALUE);
        return res_;
    }
    public static MessageStruct getMessageStruct(Struct _str, String _aliasMessage) {
        if (_str instanceof MessageStruct) {
            return (MessageStruct) _str;
        }
        return MessageStruct.newInstance(Message.newStandardMessage(), _aliasMessage);
    }
    public String getAliasMapKeys() {
        return aliasMapKeys;
    }

    public void setAliasMapKeys(String _aliasMapKeys) {
        aliasMapKeys = _aliasMapKeys;
    }

    public String getAliasMapValues() {
        return aliasMapValues;
    }

    public void setAliasMapValues(String _aliasMapValues) {
        aliasMapValues = _aliasMapValues;
    }

    public String getAliasMapIndexOfEntry() {
        return aliasMapIndexOfEntry;
    }

    public void setAliasMapIndexOfEntry(String _aliasMapIndexOfEntry) {
        aliasMapIndexOfEntry = _aliasMapIndexOfEntry;
    }

    public String getAliasMapAddEntry() {
        return aliasMapAddEntry;
    }

    public void setAliasMapAddEntry(String _aliasMapAddEntry) {
        aliasMapAddEntry = _aliasMapAddEntry;
    }

    public String getAliasMapGetValue() {
        return aliasMapGetValue;
    }

    public void setAliasMapGetValue(String _aliasMapGetValue) {
        aliasMapGetValue = _aliasMapGetValue;
    }

    public String getAliasMapFirstValue() {
        return aliasMapFirstValue;
    }

    public void setAliasMapFirstValue(String _aliasMapFirstValue) {
        aliasMapFirstValue = _aliasMapFirstValue;
    }

    public String getAliasMapLastValue() {
        return aliasMapLastValue;
    }

    public void setAliasMapLastValue(String _aliasMapLastValue) {
        aliasMapLastValue = _aliasMapLastValue;
    }

    public String getAliasMapSetValue() {
        return aliasMapSetValue;
    }

    public void setAliasMapSetValue(String _aliasMapSetValue) {
        aliasMapSetValue = _aliasMapSetValue;
    }

    public String getAliasMapPut() {
        return aliasMapPut;
    }

    public void setAliasMapPut(String _aliasMapPut) {
        aliasMapPut = _aliasMapPut;
    }

    public String getAliasMapContains() {
        return aliasMapContains;
    }

    public void setAliasMapContains(String _aliasMapContains) {
        aliasMapContains = _aliasMapContains;
    }

    public String getAliasMapPutAll() {
        return aliasMapPutAll;
    }

    public void setAliasMapPutAll(String _aliasMapPutAll) {
        aliasMapPutAll = _aliasMapPutAll;
    }

    public String getAliasMapGetVal() {
        return aliasMapGetVal;
    }

    public void setAliasMapGetVal(String _aliasMapGetVal) {
        aliasMapGetVal = _aliasMapGetVal;
    }

    public String getAliasMapRemoveKey() {
        return aliasMapRemoveKey;
    }

    public void setAliasMapRemoveKey(String _aliasMapRemoveKey) {
        aliasMapRemoveKey = _aliasMapRemoveKey;
    }

    public String getAliasMapGetKey() {
        return aliasMapGetKey;
    }

    public void setAliasMapGetKey(String _aliasMapGetKey) {
        aliasMapGetKey = _aliasMapGetKey;
    }

    public String getAliasMapFirstKey() {
        return aliasMapFirstKey;
    }

    public void setAliasMapFirstKey(String _aliasMapFirstKey) {
        aliasMapFirstKey = _aliasMapFirstKey;
    }

    public String getAliasMapLastKey() {
        return aliasMapLastKey;
    }

    public void setAliasMapLastKey(String _aliasMapLastKey) {
        aliasMapLastKey = _aliasMapLastKey;
    }

    public String getAliasMapSetKey() {
        return aliasMapSetKey;
    }

    public void setAliasMapSetKey(String _aliasMapSetKey) {
        aliasMapSetKey = _aliasMapSetKey;
    }

    public String getAliasMapSize() {
        return aliasMapSize;
    }

    public void setAliasMapSize(String _aliasMapSize) {
        aliasMapSize = _aliasMapSize;
    }

    public String getAliasMapIsEmpty() {
        return aliasMapIsEmpty;
    }

    public void setAliasMapIsEmpty(String _aliasMapIsEmpty) {
        aliasMapIsEmpty = _aliasMapIsEmpty;
    }

    public String getAliasMapClear() {
        return aliasMapClear;
    }

    public void setAliasMapClear(String _aliasMapClear) {
        aliasMapClear = _aliasMapClear;
    }

    public String getAliasValidator() {
        return aliasValidator;
    }

    public void setAliasValidator(String _aliasValidator) {
        aliasValidator = _aliasValidator;
    }

    public String getAliasValidate() {
        return aliasValidate;
    }

    public void setAliasValidate(String _aliasValidate) {
        aliasValidate = _aliasValidate;
    }

    public String getAliasBean() {
        return aliasBean;
    }

    public void setAliasBean(String _aliasBean) {
        aliasBean = _aliasBean;
    }

    public String getAliasBeforeDisplaying() {
        return aliasBeforeDisplaying;
    }

    public void setAliasBeforeDisplaying(String _aliasBeforeDisplaying) {
        aliasBeforeDisplaying = _aliasBeforeDisplaying;
    }

    public String getAliasDataBaseField() {
        return aliasDataBaseField;
    }

    public void setAliasDataBaseField(String _aliasDataBaseField) {
        aliasDataBaseField = _aliasDataBaseField;
    }

    public String getAliasGetDataBase() {
        return aliasGetDataBase;
    }

    public void setAliasGetDataBase(String _aliasGetDataBase) {
        aliasGetDataBase = _aliasGetDataBase;
    }

    public String getAliasSetDataBase() {
        return aliasSetDataBase;
    }

    public void setAliasSetDataBase(String _aliasSetDataBase) {
        aliasSetDataBase = _aliasSetDataBase;
    }

    public String getAliasForms() {
        return aliasForms;
    }

    public void setAliasForms(String _aliasForms) {
        aliasForms = _aliasForms;
    }

    public String getAliasGetForms() {
        return aliasGetForms;
    }

    public void setAliasGetForms(String _aliasGetForms) {
        aliasGetForms = _aliasGetForms;
    }

    public String getAliasSetForms() {
        return aliasSetForms;
    }

    public void setAliasSetForms(String _aliasSetForms) {
        aliasSetForms = _aliasSetForms;
    }

    public String getAliasLanguage() {
        return aliasLanguage;
    }

    public void setAliasLanguage(String _aliasLanguage) {
        aliasLanguage = _aliasLanguage;
    }
    public String getAliasGetLanguage() {
        return aliasGetLanguage;
    }

    public void setAliasGetLanguage(String _aliasGetLanguage) {
        aliasGetLanguage = _aliasGetLanguage;
    }

    public String getAliasSetLanguage() {
        return aliasSetLanguage;
    }

    public void setAliasSetLanguage(String _aliasSetLanguage) {
        aliasSetLanguage = _aliasSetLanguage;
    }

    public String getAliasScope() {
        return aliasScope;
    }

    public void setAliasScope(String _aliasScope) {
        aliasScope = _aliasScope;
    }

    public String getAliasGetScope() {
        return aliasGetScope;
    }

    public void setAliasGetScope(String _aliasGetScope) {
        aliasGetScope = _aliasGetScope;
    }

    public String getAliasSetScope() {
        return aliasSetScope;
    }

    public void setAliasSetScope(String _aliasSetScope) {
        aliasSetScope = _aliasSetScope;
    }
    public String getAliasStringMapObject() {
        return aliasStringMapObject;
    }

    public void setAliasStringMapObject(String _aliasStringMapObject) {
        aliasStringMapObject = _aliasStringMapObject;
    }

    public String getAliasMessage() {
        return aliasMessage;
    }

    public void setAliasMessage(String _aliasMessage) {
        aliasMessage = _aliasMessage;
    }

    public String getAliasNewMessage() {
        return aliasNewMessage;
    }

    public void setAliasNewMessage(String _aliasNewMessage) {
        aliasNewMessage = _aliasNewMessage;
    }

    public String getAliasMessageFormat() {
        return aliasMessageFormat;
    }

    public void setAliasMessageFormat(String _aliasMessageFormat) {
        aliasMessageFormat = _aliasMessageFormat;
    }

    public String getAliasMessageGetArgs() {
        return aliasMessageGetArgs;
    }

    public void setAliasMessageGetArgs(String _aliasMessageGetArgs) {
        aliasMessageGetArgs = _aliasMessageGetArgs;
    }

    public String getAliasMessageSetArgs() {
        return aliasMessageSetArgs;
    }

    public void setAliasMessageSetArgs(String _aliasMessageSetArgs) {
        aliasMessageSetArgs = _aliasMessageSetArgs;
    }

}
