package code.formathtml.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.formathtml.structs.MessageStruct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class DefaultBeanAliases {
    private static final String BEAN = "Bean";
    private static final String MAP_KEYS = "MapKeys";
    private static final String MAP_VALUES = "MapValues";
    private static final String MAP_INDEX_OF_ENTRY = "MapIndexOfEntry";
    private static final String MAP_ADD_ENTRY = "MapAddEntry";
    private static final String MAP_GET_VALUE = "MapGetValue";
    private static final String MAP_FIRST_VALUE = "MapFirstValue";
    private static final String MAP_LAST_VALUE = "MapLastValue";
    private static final String MAP_SET_VALUE = "MapSetValue";
    private static final String MAP_PUT = "MapPut";
    private static final String MAP_CONTAINS = "MapContains";
    private static final String MAP_PUT_ALL = "MapPutAll";
    private static final String MAP_GET_VAL = "MapGetVal";
    private static final String MAP_REMOVE_KEY = "MapRemoveKey";
    private static final String MAP_GET_KEY = "MapGetKey";
    private static final String MAP_FIRST_KEY = "MapFirstKey";
    private static final String MAP_LAST_KEY = "MapLastKey";
    private static final String MAP_SET_KEY = "MapSetKey";
    private static final String MAP_SIZE = "MapSize";
    private static final String MAP_IS_EMPTY = "MapIsEmpty";
    private static final String MAP_CLEAR = "MapClear";
    private static final String VALIDATOR = "Validator";
    private static final String VALIDATE = "Validate";
    private static final String DATA_BASE_FIELD = "DataBaseField";
    private static final String FORMS = "Forms";
    private static final String SET_FORMS = "SetForms";
    private static final String GET_FORMS = "GetForms";
    private static final String LANGUAGE = "Language";
    private static final String SET_LANGUAGE = "SetLanguage";
    private static final String GET_LANGUAGE = "GetLanguage";
    private static final String SCOPE = "Scope";
    private static final String SET_SCOPE = "SetScope";
    private static final String GET_SCOPE = "GetScope";
    private static final String SET_DATA_BASE = "SetDataBase";
    private static final String GET_DATA_BASE = "GetDataBase";
    private static final String BEFORE_DISPLAYING = "BeforeDisplaying";
    private static final String STRING_MAP_OBJECT = "StringMapObject";
    private static final String MESSAGE = "Message";
    private static final String NEW_MESSAGE = "NewMessage";
    private static final String MESSAGE_FORMAT = "MessageFormat";
    private static final String MESSAGE_GET_ARGS = "MessageGetArgs";
    private static final String MESSAGE_SET_ARGS = "MessageSetArgs";

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
    private BeanAliasParameters beanAliasParameters = new BeanAliasParameters();

    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content, StringList _predefinedClasses, StringList _predefinedInterfacesInitOrder) {
        StringMap<String> files_ = new StringMap<String>();
        StringMap<PrimitiveType> primitiveTypes_ = _content.getPrimTypes().getPrimitiveTypes();
        AliasCore coreNames_ = _content.getCoreNames();
        String public_ = _keyWords.getKeyWordPublic();
        String private_ = _keyWords.getKeyWordPrivate();
        String interface_ = _keyWords.getKeyWordInterface();
        String class_ = _keyWords.getKeyWordClass();
        String return_ = _keyWords.getKeyWordReturn();
        String if_ = _keyWords.getKeyWordIf();
        String for_ = _keyWords.getKeyWordFor();
        String null_ = _keyWords.getKeyWordNull();
        String new_ = _keyWords.getKeyWordNew();
        String int_ = _content.getPrimTypes().getAliasPrimInteger();
        String endLine_ = String.valueOf(';');
        String suffixParam_ = "";
        StringBuilder file_ = new StringBuilder();
        file_.append(public_).append(" ").append(class_).append(" ").append(getAliasBean()).append("{");
        String string_ = _content.getCharSeq().getAliasString();
        String language_ = getAliasLanguage();
        String scope_ = getAliasScope();
        String dataBase_ = getAliasDataBaseField();
        String this_ = _keyWords.getKeyWordThis();
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
                .append(getAliasSetLanguage()).append("(").append(string_).append(" ").append(beanAliasParameters.getAliasBean0SetLanguage0()).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(language_).append("=")
                .append(beanAliasParameters.getAliasBean0SetLanguage0()).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(string_).append(" ")
                .append(getAliasGetScope()).append("(){");
        file_.append("  ").append(return_).append(" ").append(scope_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetScope()).append("(").append(string_).append(" ").append(beanAliasParameters.getAliasBean0SetScope0()).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(scope_).append("=")
                .append(beanAliasParameters.getAliasBean0SetScope0()).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(object_).append(" ")
                .append(getAliasGetDataBase()).append("(){");
        file_.append("  ").append(return_).append(" ").append(dataBase_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetDataBase()).append("(").append(object_).append(" ")
                .append(beanAliasParameters.getAliasBean0SetDataBase0()).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(dataBase_).append("=")
                .append(beanAliasParameters.getAliasBean0SetDataBase0()).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(getAliasStringMapObject())
                .append(" ").append(getAliasGetForms()).append("(){");
        file_.append("  ").append(return_).append(" ").append(forms_).append(endLine_);
        file_.append(" ").append("}");
        file_.append(" ").append(public_).append(" ").append(void_).append(" ")
                .append(getAliasSetForms()).append("(").append(getAliasStringMapObject())
                .append(" ").append(beanAliasParameters.getAliasBean0SetForms0()).append(")").append("{");
        file_.append("  ").append(this_).append(".").append(forms_).append("=")
                .append(beanAliasParameters.getAliasBean0SetForms0()).append(suffixParam_).append(endLine_);
        file_.append(" ").append("}");
        file_.append("}");
        files_.put(getAliasBean(), file_.toString());
        _predefinedClasses.add(getAliasBean());
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
                .append(int_).append(" ").append(beanAliasParameters.getAliasStringMapObject0SetKey0()).append(",").append(string_)
                .append(" ").append(beanAliasParameters.getAliasStringMapObject0SetKey1()).append("){");
        file_.append(this_).append(".").append(keys_).append("[").append(beanAliasParameters.getAliasStringMapObject0SetKey0())
                .append("]=").append(beanAliasParameters.getAliasStringMapObject0SetKey1()).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(string_).append(" ").append(getKey_)
                .append("(").append(int_).append(" ").append(beanAliasParameters.getAliasStringMapObject0GetKey0()).append("){");
        file_.append(return_).append(" ").append(this_).append(".").append(keys_).append("[")
                .append(beanAliasParameters.getAliasStringMapObject0GetKey0()).append("]").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(setValue_)
                .append("(").append(int_).append(" ").append(beanAliasParameters.getAliasStringMapObject0SetValue0())
                .append(",").append(object_).append(" ").append(beanAliasParameters.getAliasStringMapObject0SetValue1()).append("){");
        file_.append(this_).append(".").append(values_).append("[").append(beanAliasParameters.getAliasStringMapObject0SetValue0())
                .append("]=").append(beanAliasParameters.getAliasStringMapObject0SetValue1()).append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(object_).append(" ").append(getValue_).append("(")
                .append(int_).append(" ").append(beanAliasParameters.getAliasStringMapObject0GetValue0()).append("){");
        file_.append(return_).append(" ").append(this_).append(".").append(values_)
                .append("[").append(beanAliasParameters.getAliasStringMapObject0GetValue0()).append("]").append(endLine_);
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
                .append(beanAliasParameters.getAliasStringMapObject0Put0()).append(",").append(object_)
                .append(" ").append(beanAliasParameters.getAliasStringMapObject0Put1()).append("){");
        String indexPut_ = tr("index", _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0Put0(),beanAliasParameters.getAliasStringMapObject0Put1());
        file_.append(int_).append(" ").append(indexPut_).append("=")
                .append(indexOfEntry_).append("(").append(beanAliasParameters.getAliasStringMapObject0Put0()).append(")")
                .append(endLine_);
        file_.append(if_).append("(").append(indexPut_).append("==-1){");
        file_.append(addEntry_).append("(").append(beanAliasParameters.getAliasStringMapObject0Put0()).append(", ")
                .append(beanAliasParameters.getAliasStringMapObject0Put1()).append(")").append(endLine_);
        file_.append(return_).append(endLine_);
        file_.append("}");
        file_.append(setValue_).append("(").append(indexPut_).append(", ")
                .append(beanAliasParameters.getAliasStringMapObject0Put1()).append(")").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(boolean_).append(" ").append(aliasMapContains)
                .append("(").append(string_).append(" ").append(beanAliasParameters.getAliasStringMapObject0Contains0()).append("){");
        file_.append(return_).append(" ").append(indexOfEntry_).append("(")
                .append(beanAliasParameters.getAliasStringMapObject0Contains0()).append(") != -1").append(endLine_);
        file_.append("}");
        String indexGetVal_ = tr("index", _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0GetVal0());
        file_.append(public_).append(" ").append(object_).append(" ").append(getVal_).append("(")
                .append(string_).append(" ").append(beanAliasParameters.getAliasStringMapObject0GetVal0()).append("){");
        file_.append(int_).append(" ").append(indexGetVal_).append("=").append(indexOfEntry_)
                .append("(").append(beanAliasParameters.getAliasStringMapObject0GetVal0()).append(")").append(endLine_);
        file_.append(if_).append("(").append(indexGetVal_).append("==-1){");
        file_.append(return_).append(" ").append(null_).append(endLine_);
        file_.append("}");
        file_.append(return_).append(" ").append(getValue_).append("(").append(indexGetVal_)
                .append(")").append(endLine_);
        file_.append("}");

        file_.append(public_).append(" ").append(void_).append(" ")
                .append(addEntry_).append("(").append(string_).append(" ")
                .append(beanAliasParameters.getAliasStringMapObject0AddEntry0()).append(",").append(object_)
                .append(" ").append(beanAliasParameters.getAliasStringMapObject0AddEntry1()).append("){");
        file_.append(string_).append("[] ").append(keys_).append("=").append(new_)
                .append(" ").append(string_).append("[").append(this_)
                .append(".").append(keys_).append(".").append(length_)
                .append("+1]").append(endLine_);
        String iAddEntry_ = tr("i", _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0AddEntry0(),beanAliasParameters.getAliasStringMapObject0AddEntry1());
        file_.append(for_).append("(").append(int_).append(" ").append(iAddEntry_)
                .append("=0").append(endLine_).append(iAddEntry_).append("<")
                .append(this_).append(".")
                .append(keys_).append(".").append(length_).append(endLine_)
                .append(iAddEntry_).append("++){");
        file_.append(keys_).append("[").append(iAddEntry_)
                .append("]=").append(this_).append(".").append(keys_).append("[")
                .append(iAddEntry_).append("]").append(endLine_);
        file_.append("}");
        file_.append(keys_).append("[").append(this_).append(".").append(keys_)
                .append(".").append(length_).append("]=").append(beanAliasParameters.getAliasStringMapObject0AddEntry0()).append(endLine_);
        file_.append(this_).append(".").append(keys_).append("=").append(keys_).append(endLine_);
        file_.append(object_).append("[] ").append(values_).append("=").append(new_).append(" ")
                .append(object_).append("[").append(this_).append(".").append(values_).append(".")
                .append(length_).append("+1]").append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(iAddEntry_)
                .append("=0").append(endLine_).append(iAddEntry_).append("<")
                .append(this_).append(".").append(values_).append(".").append(length_).append(endLine_)
                .append(iAddEntry_).append("++){");
        file_.append(values_).append("[").append(iAddEntry_)
                .append("]=").append(this_).append(".").append(values_).append("[")
                .append(iAddEntry_).append("]").append(endLine_);
        file_.append("}");
        file_.append(values_).append("[").append(this_).append(".").append(values_)
                .append(".").append(length_).append("]=").append(beanAliasParameters.getAliasStringMapObject0AddEntry1()).append(endLine_);
        file_.append(this_).append(".").append(values_).append("=").append(values_)
                .append(endLine_);
        file_.append("}");

        file_.append(public_).append(" ").append(int_).append(" ").append(indexOfEntry_)
                .append("(").append(string_).append(" ").append(beanAliasParameters.getAliasStringMapObject0IndexOfEntry0()).append("){");
        String iIndexOfEntry_ = tr("i", _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0IndexOfEntry0());
        file_.append(for_).append("(").append(int_)
                .append(" ").append(iIndexOfEntry_).append("=0")
                .append(endLine_).append(iIndexOfEntry_).append("<").append(this_)
                .append(".").append(keys_).append(".").append(length_).append(endLine_)
                .append(iIndexOfEntry_).append("++){");
        file_.append(if_).append("(").append(this_).append(".").append(keys_).append("[").append(iIndexOfEntry_).append("]==").append(beanAliasParameters.getAliasStringMapObject0IndexOfEntry0()).append("){");
        file_.append(return_).append(" ").append(iIndexOfEntry_).append(endLine_);
        file_.append("}");
        file_.append("}");
        file_.append(return_).append(" -1").append(endLine_);
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(putAll_).append("(")
                .append(getAliasStringMapObject()).append(" ").append(beanAliasParameters.getAliasStringMapObject0PutAll0()).append("){");
        String lenPutAll_ = tr("len", _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0PutAll0());
        file_.append(int_).append(" ").append(lenPutAll_).append("=").append("(")
                .append(beanAliasParameters.getAliasStringMapObject0PutAll0()).append(").").append(keys_).append(".")
                .append(length_).append(endLine_);
        String iPutAll_ = tr("i", _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0PutAll0());
        file_.append(for_).append("(").append(int_).append(" ").append(iPutAll_)
                .append("=0").append(endLine_).append(iPutAll_).append("<")
                .append(lenPutAll_).append(endLine_).append(iPutAll_).append("++){");
        file_.append(" ").append(put_).append("(").append("(")
                .append(beanAliasParameters.getAliasStringMapObject0PutAll0()).append(").")
                .append(keys_).append("[").append(iPutAll_)
                .append("], ").append("(").append(beanAliasParameters.getAliasStringMapObject0PutAll0())
                .append(").").append(values_).append("[").append(iPutAll_)
                .append("])").append(endLine_);
        file_.append("}");
        file_.append("}");
        file_.append(public_).append(" ").append(void_).append(" ").append(removeKey_)
                .append("(").append(string_).append(" ").append(beanAliasParameters.getAliasStringMapObject0RemoveKey0()).append("){");
        String indexRemoveKey_ = tr("index", _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0RemoveKey0());
        file_.append(int_).append(" ").append(indexRemoveKey_)
                .append("=").append(indexOfEntry_).append("(").append(beanAliasParameters.getAliasStringMapObject0RemoveKey0())
                .append(")").append(endLine_);
        file_.append(if_).append("(").append(indexRemoveKey_).append("==-1){");
        file_.append(return_).append(endLine_);
        file_.append("}");
        file_.append(string_).append("[] ").append(keys_).append("=").append(new_)
                .append(" ").append(string_).append("[").append(this_).append(".").append(keys_)
                .append(".").append(length_).append("-1]").append(endLine_);
        String iRemoveKey_ = tr("i", _keyWords, primitiveTypes_, coreNames_);
        file_.append(for_).append("(").append(int_).append(" ").append(iRemoveKey_).append("=0")
                .append(endLine_).append(iRemoveKey_)
                .append("<").append(indexRemoveKey_)
                .append(endLine_).append(iRemoveKey_)
                .append("++){");
        file_.append(keys_).append("[").append(iRemoveKey_).append("]=")
                .append(this_).append(".").append(keys_).append("[").append(iRemoveKey_)
                .append("]").append(endLine_);
        file_.append("}");
        file_.append(for_).append("(").append(int_).append(" ").append(iRemoveKey_)
                .append("=").append(indexRemoveKey_).append("+1").append(endLine_)
                .append(iRemoveKey_).append("<").append(this_).append(".")
                .append(keys_).append(".").append(length_).append(endLine_)
                .append(iRemoveKey_).append("++){");
        file_.append(keys_).append("[").append(iRemoveKey_).append("-1]=").append(this_)
                .append(".").append(keys_).append("[").append(iRemoveKey_).append("]")
                .append(endLine_);
        file_.append("}");
        file_.append(this_).append(".").append(keys_).append("=").append(keys_).append(endLine_);

        file_.append(object_).append("[] ").append(values_).append("=").append(new_).append(" ")
                .append(object_).append("[").append(this_).append(".")
                .append(values_).append(".").append(length_).append("-1]")
                .append(endLine_);
        file_.append(for_).append("(").append(int_).append(" ").append(iRemoveKey_).append("=0")
                .append(endLine_).append(iRemoveKey_)
                .append("<").append(indexRemoveKey_)
                .append(endLine_).append(iRemoveKey_)
                .append("++){");
        file_.append(values_).append("[").append(iRemoveKey_).append("]=")
                .append(this_).append(".").append(values_).append("[").append(iRemoveKey_)
                .append("]").append(endLine_);
        file_.append("}");
        file_.append(for_).append("(").append(int_).append(" ").append(iRemoveKey_)
                .append("=").append(indexRemoveKey_).append("+1").append(endLine_)
                .append(iRemoveKey_).append("<").append(this_).append(".").append(values_)
                .append(".").append(length_).append(endLine_).append(iRemoveKey_).append("++){");
        file_.append(values_).append("[").append(iRemoveKey_).append("-1]=")
                .append(this_).append(".").append(values_).append("[").append(iRemoveKey_)
                .append("]").append(endLine_);
        file_.append("}");
        file_.append(this_).append(".").append(values_).append("=").append(values_).append(endLine_);
        file_.append("}");
        file_.append("}");
        files_.put(getAliasStringMapObject(), file_.toString());
        _predefinedClasses.add(getAliasStringMapObject());
        file_ = new StringBuilder();
        file_.append(public_).append(" ").append(interface_).append(" ")
                .append(aliasValidator).append("{");
        file_.append(public_).append(" ").append(getAliasMessage()).append(" ")
                .append(aliasValidate).append("(");
        file_.append(object_).append(" ").append(beanAliasParameters.getAliasValidator0Validate0()).append(",");
        file_.append(object_).append(" ").append(beanAliasParameters.getAliasValidator0Validate1()).append(",");
        file_.append(object_).append(" ").append(beanAliasParameters.getAliasValidator0Validate2()).append(",");
        file_.append(object_).append("[] ").append(beanAliasParameters.getAliasValidator0Validate3()).append(",");
        file_.append(string_).append(" ").append(beanAliasParameters.getAliasValidator0Validate4()).append(",");
        file_.append(string_).append(" ").append(beanAliasParameters.getAliasValidator0Validate5());
        file_.append(")");
        file_.append(endLine_);
        file_.append("}");
        files_.put(aliasValidator, file_.toString());
        _predefinedInterfacesInitOrder.add(aliasValidator);
        _predefinedClasses.add(aliasValidator);
        return files_;
    }
    private static String tr(String _var, KeyWords _keyWords, StringMap<PrimitiveType> _primitiveTypes, AliasCore _coreNames, String... _params) {
        CustList<String> allKeysWords_ = _keyWords.allKeyWords().values();
        allKeysWords_.addAllElts(_primitiveTypes.getKeys());
        allKeysWords_.add(_coreNames.getAliasVoid());
        for (String p: _params){
            allKeysWords_.add(p);
        }
        return getCandidate(_var, allKeysWords_);
    }

    private static String getCandidate(String _var, CustList<String> allKeysWords_) {
        return ValidatorStandard.tr(allKeysWords_,_var);
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
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC, new StringList(beanAliasParameters.getAliasMessage1NewMessage0()));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageFormat, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageGetArgs, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasMessageSetArgs, params_, _content.getCoreNames().getAliasVoid(), true, MethodModifier.NORMAL, new StringList(beanAliasParameters.getAliasMessage0SetArgs0()));
        methods_.add( method_);
        _content.getStandards().addEntry(aliasMessage, std_);
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.addAllElts(beanAliasParameters.allTableTypeMethodParamNames());
        return m_;
    }
    public void build(StringMap<String> _util, StringMap<String> _cust) {
        beanAliasParameters.build(_util, _cust);
        setAliasBean(LgNamesContent.get(_util, _cust, BEAN));
        setAliasMapKeys(LgNamesContent.get(_util, _cust, MAP_KEYS));
        setAliasMapValues(LgNamesContent.get(_util, _cust, MAP_VALUES));
        setAliasMapIndexOfEntry(LgNamesContent.get(_util, _cust, MAP_INDEX_OF_ENTRY));
        setAliasMapAddEntry(LgNamesContent.get(_util, _cust, MAP_ADD_ENTRY));
        setAliasMapGetValue(LgNamesContent.get(_util, _cust, MAP_GET_VALUE));
        setAliasMapFirstValue(LgNamesContent.get(_util, _cust, MAP_FIRST_VALUE));
        setAliasMapLastValue(LgNamesContent.get(_util, _cust, MAP_LAST_VALUE));
        setAliasMapSetValue(LgNamesContent.get(_util, _cust, MAP_SET_VALUE));
        setAliasMapPut(LgNamesContent.get(_util, _cust, MAP_PUT));
        setAliasMapContains(LgNamesContent.get(_util, _cust, MAP_CONTAINS));
        setAliasMapPutAll(LgNamesContent.get(_util, _cust, MAP_PUT_ALL));
        setAliasMapGetVal(LgNamesContent.get(_util, _cust, MAP_GET_VAL));
        setAliasMapRemoveKey(LgNamesContent.get(_util, _cust, MAP_REMOVE_KEY));
        setAliasMapGetKey(LgNamesContent.get(_util, _cust, MAP_GET_KEY));
        setAliasMapFirstKey(LgNamesContent.get(_util, _cust, MAP_FIRST_KEY));
        setAliasMapLastKey(LgNamesContent.get(_util, _cust, MAP_LAST_KEY));
        setAliasMapSetKey(LgNamesContent.get(_util, _cust, MAP_SET_KEY));
        setAliasMapSize(LgNamesContent.get(_util, _cust, MAP_SIZE));
        setAliasMapIsEmpty(LgNamesContent.get(_util, _cust, MAP_IS_EMPTY));
        setAliasMapClear(LgNamesContent.get(_util, _cust, MAP_CLEAR));
        setAliasValidator(LgNamesContent.get(_util, _cust, VALIDATOR));
        setAliasValidate(LgNamesContent.get(_util, _cust, VALIDATE));
        setAliasDataBaseField(LgNamesContent.get(_util, _cust, DATA_BASE_FIELD));
        setAliasForms(LgNamesContent.get(_util, _cust, FORMS));
        setAliasSetForms(LgNamesContent.get(_util, _cust, SET_FORMS));
        setAliasGetForms(LgNamesContent.get(_util, _cust, GET_FORMS));
        setAliasLanguage(LgNamesContent.get(_util, _cust, LANGUAGE));
        setAliasSetLanguage(LgNamesContent.get(_util, _cust, SET_LANGUAGE));
        setAliasGetLanguage(LgNamesContent.get(_util, _cust, GET_LANGUAGE));
        setAliasScope(LgNamesContent.get(_util, _cust, SCOPE));
        setAliasSetScope(LgNamesContent.get(_util, _cust, SET_SCOPE));
        setAliasGetScope(LgNamesContent.get(_util, _cust, GET_SCOPE));
        setAliasSetDataBase(LgNamesContent.get(_util, _cust, SET_DATA_BASE));
        setAliasGetDataBase(LgNamesContent.get(_util, _cust, GET_DATA_BASE));
        setAliasBeforeDisplaying(LgNamesContent.get(_util, _cust, BEFORE_DISPLAYING));
        setAliasStringMapObject(LgNamesContent.get(_util, _cust, STRING_MAP_OBJECT));
        setAliasMessage(LgNamesContent.get(_util, _cust, MESSAGE));
        setAliasNewMessage(LgNamesContent.get(_util, _cust, NEW_MESSAGE));
        setAliasMessageFormat(LgNamesContent.get(_util, _cust, MESSAGE_FORMAT));
        setAliasMessageGetArgs(LgNamesContent.get(_util, _cust, MESSAGE_GET_ARGS));
        setAliasMessageSetArgs(LgNamesContent.get(_util, _cust, MESSAGE_SET_ARGS));
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
