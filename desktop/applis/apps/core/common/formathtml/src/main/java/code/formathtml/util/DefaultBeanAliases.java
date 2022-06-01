package code.formathtml.util;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.formathtml.structs.MessageStruct;
import code.formathtml.util.stds.*;
import code.util.CustList;
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
    private static final String DOCUMENT = "Document";
    private static final String DOCUMENT_ALL = "DocumentAll";
    private static final String GET_DECLARED_LOCAL_TYPES = "GetDeclaredLocalTypes";
    private static final String GET_DECLARED_ANONYMOUS_TYPES = "GetDeclaredAnonymousTypes";
    private static final String MESSAGE = "Message";
    private static final String NEW_MESSAGE = "NewMessage";
    private static final String MESSAGE_FORMAT = "MessageFormat";
    private static final String MESSAGE_GET_ARGS = "MessageGetArgs";
    private static final String MESSAGE_SET_ARGS = "MessageSetArgs";
    private static final String DOCUMENT_BEAN_ARRAY="DocumentBeanArray";
    private static final String DOCUMENT_BEAN_NAME="DocumentBeanName";
    private static final String DOCUMENT_BEAN_VALUE="DocumentBeanValue";
    private static final String DOCUMENT_VALIDATOR_ARRAY="DocumentValidatorArray";
    private static final String DOCUMENT_VALIDATOR_VALUE="DocumentValidatorValue";
    private static final char END_LINE = ';';
    private static final char SPACE = ' ';
    private static final char LEFT_BRACE = '{';
    private static final char LEFT_PAR = '(';
    private static final char RIGHT_PAR = ')';
    private static final String EMPTY_PARAMS_SGN = ""+LEFT_PAR+RIGHT_PAR+LEFT_BRACE;
    private static final char RIGHT_BRACE = '}';
    private static final char DOT = '.';
    private static final char AFFECT = '=';
    private static final String ARR_SPACE = "[] ";
    private static final String ACCESS_ZERO = "[0]";
    private static final char LEFT_BRACKET = '[';
    private static final char RIGHT_BRACKET = ']';
    private static final char COMMA = ',';
    private static final char ZERO = '0';
    private static final String LT = "<";
    private static final String INCR = "++";
    private static final String ADD_ONE = "+1";
    private static final String AFFECT_ZERO = "="+ZERO;
    private static final String MINUS_ONE = "-1";
    private static final String EQ = "==";
    private static final String DIFF = "!=";
    private static final String I_LOOP = "i";
    private static final String INDEX = "index";
    private static final String VALUES = "values";
    private static final String KEYS = "keys";
    private static final String LEN = "len";

    private String aliasBean = "code.bean.Bean";
    private String aliasMapKeys = KEYS;
    private String aliasMapValues = VALUES;
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
    private String aliasStringMapObject="code.formathtml.nat.StringMapObject";
    private String aliasMessage="code.bean.Message";
    private String aliasDocument="code.bean.Document";
    private String aliasDocumentAll="all";
    private String aliasDocumentBeanArray="beanArr";
    private String aliasDocumentBeanName="beanName";
    private String aliasDocumentBeanValue="beanValue";
    private String aliasDocumentValidatorArray="valArr";
    private String aliasDocumentValidatorValue="valValue";
    private String aliasNewMessage="newStandardMessage";
    private String aliasMessageFormat="format";
    private String aliasMessageGetArgs="getArgs";
    private String aliasMessageSetArgs="setArgs";
    private final BeanAliasParameters beanAliasParameters = new BeanAliasParameters();

    public StringMap<String> buildFiles(KeyWords _keyWords, LgNamesContent _content) {
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
        StringBuilder file_ = new StringBuilder();
        file_.append(public_).append(SPACE).append(class_).append(SPACE).append(getAliasBean()).append(LEFT_BRACE);
        String string_ = _content.getCharSeq().getAliasString();
        String language_ = getAliasLanguage();
        String scope_ = getAliasScope();
        String dataBase_ = getAliasDataBaseField();
        String this_ = _keyWords.getKeyWordThis();
        String object_ = coreNames_.getAliasObject();
        String forms_ = getAliasForms();
        String boolean_ = _content.getPrimTypes().getAliasPrimBoolean();
        String length_ = _content.getCoreNames().getAliasArrayLength();
        file_.append(SPACE).append(private_).append(SPACE).append(string_).append(SPACE)
                .append(language_).append(END_LINE);
        file_.append(SPACE).append(private_).append(SPACE).append(string_).append(SPACE)
                .append(scope_).append(END_LINE);
        file_.append(SPACE).append(private_).append(SPACE).append(object_).append(SPACE)
                .append(dataBase_).append(END_LINE);
        file_.append(SPACE).append(private_).append(SPACE).append(getAliasStringMapObject()).append(SPACE)
                .append(forms_).append(END_LINE);
        String void_ = coreNames_.getAliasVoid();
        file_.append(SPACE).append(public_).append(SPACE).append(void_).append(SPACE)
                .append(getAliasBeforeDisplaying()).append(EMPTY_PARAMS_SGN);
        file_.append(SPACE).append(RIGHT_BRACE);
        file_.append(SPACE).append(public_).append(SPACE).append(string_).append(SPACE)
                .append(getAliasGetLanguage()).append(EMPTY_PARAMS_SGN);
        file_.append(SPACE).append(return_).append(SPACE).append(language_).append(END_LINE);
        file_.append(SPACE).append(RIGHT_BRACE);
        file_.append(SPACE).append(public_).append(SPACE).append(void_).append(SPACE)
                .append(getAliasSetLanguage()).append(LEFT_PAR).append(string_).append(SPACE).append(beanAliasParameters.getAliasBean0SetLanguage0()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(SPACE).append(this_).append(DOT).append(language_).append(AFFECT)
                .append(beanAliasParameters.getAliasBean0SetLanguage0()).append(END_LINE);
        file_.append(SPACE).append(RIGHT_BRACE);
        file_.append(SPACE).append(public_).append(SPACE).append(string_).append(SPACE)
                .append(getAliasGetScope()).append(EMPTY_PARAMS_SGN);
        file_.append(SPACE).append(return_).append(SPACE).append(scope_).append(END_LINE);
        file_.append(SPACE).append(RIGHT_BRACE);
        file_.append(SPACE).append(public_).append(SPACE).append(void_).append(SPACE)
                .append(getAliasSetScope()).append(LEFT_PAR).append(string_).append(SPACE).append(beanAliasParameters.getAliasBean0SetScope0()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(SPACE).append(this_).append(DOT).append(scope_).append(AFFECT)
                .append(beanAliasParameters.getAliasBean0SetScope0()).append(END_LINE);
        file_.append(SPACE).append(RIGHT_BRACE);
        file_.append(SPACE).append(public_).append(SPACE).append(object_).append(SPACE)
                .append(getAliasGetDataBase()).append(EMPTY_PARAMS_SGN);
        file_.append(SPACE).append(return_).append(SPACE).append(dataBase_).append(END_LINE);
        file_.append(SPACE).append(RIGHT_BRACE);
        file_.append(SPACE).append(public_).append(SPACE).append(void_).append(SPACE)
                .append(getAliasSetDataBase()).append(LEFT_PAR).append(object_).append(SPACE)
                .append(beanAliasParameters.getAliasBean0SetDataBase0()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(SPACE).append(this_).append(DOT).append(dataBase_).append(AFFECT)
                .append(beanAliasParameters.getAliasBean0SetDataBase0()).append(END_LINE);
        file_.append(SPACE).append(RIGHT_BRACE);
        file_.append(SPACE).append(public_).append(SPACE).append(getAliasStringMapObject())
                .append(SPACE).append(getAliasGetForms()).append(EMPTY_PARAMS_SGN);
        file_.append(SPACE).append(return_).append(SPACE).append(forms_).append(END_LINE);
        file_.append(SPACE).append(RIGHT_BRACE);
        file_.append(SPACE).append(public_).append(SPACE).append(void_).append(SPACE)
                .append(getAliasSetForms()).append(LEFT_PAR).append(getAliasStringMapObject())
                .append(SPACE).append(beanAliasParameters.getAliasBean0SetForms0()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(SPACE).append(this_).append(DOT).append(forms_).append(AFFECT)
                .append(beanAliasParameters.getAliasBean0SetForms0()).append(END_LINE);
        file_.append(SPACE).append(RIGHT_BRACE);
        file_.append(RIGHT_BRACE);
        files_.put(getAliasBean(), file_.toString());
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
        file_.append(public_).append(SPACE).append(class_).append(SPACE).append(getAliasStringMapObject())
                .append(LEFT_BRACE);
        file_.append(private_).append(SPACE).append(string_).append(ARR_SPACE).append(keys_)
                .append(AFFECT).append(new_).append(SPACE).append(string_).append(ACCESS_ZERO).append(END_LINE);
        file_.append(private_).append(SPACE).append(object_).append(ARR_SPACE).append(values_)
                .append(AFFECT).append(new_).append(SPACE).append(object_).append(ACCESS_ZERO).append(END_LINE);
        file_.append(public_).append(SPACE).append(string_).append(ARR_SPACE).append(keys_).append(EMPTY_PARAMS_SGN);
        file_.append(return_).append(SPACE).append(keys_).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(string_).append(SPACE).append(aliasMapFirstKey).append(EMPTY_PARAMS_SGN);
        file_.append(return_).append(SPACE).append(keys_).append(ACCESS_ZERO).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(string_).append(SPACE).append(aliasMapLastKey).append(EMPTY_PARAMS_SGN);
        file_.append(return_).append(SPACE).append(keys_).append(LEFT_BRACKET).append(keys_).append(DOT)
                .append(length_).append(MINUS_ONE).append(RIGHT_BRACKET).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(object_).append(ARR_SPACE).append(values_).append(EMPTY_PARAMS_SGN);
        file_.append(return_).append(SPACE).append(values_).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(object_).append(SPACE).append(aliasMapFirstValue).append(EMPTY_PARAMS_SGN);
        file_.append(return_).append(SPACE).append(values_).append(ACCESS_ZERO).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(object_).append(SPACE).append(aliasMapLastValue).append(EMPTY_PARAMS_SGN);
        file_.append(return_).append(SPACE).append(values_).append(LEFT_BRACKET).append(values_)
                .append(DOT).append(length_).append(MINUS_ONE).append(RIGHT_BRACKET).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(void_).append(SPACE).append(setKey_).append(LEFT_PAR)
                .append(int_).append(SPACE).append(beanAliasParameters.getAliasStringMapObject0SetKey0()).append(COMMA).append(string_)
                .append(SPACE).append(beanAliasParameters.getAliasStringMapObject0SetKey1()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(this_).append(DOT).append(keys_).append(LEFT_BRACKET).append(beanAliasParameters.getAliasStringMapObject0SetKey0())
                .append(RIGHT_BRACKET).append(AFFECT).append(beanAliasParameters.getAliasStringMapObject0SetKey1()).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(string_).append(SPACE).append(getKey_)
                .append(LEFT_PAR).append(int_).append(SPACE).append(beanAliasParameters.getAliasStringMapObject0GetKey0()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(return_).append(SPACE).append(this_).append(DOT).append(keys_).append(LEFT_BRACKET)
                .append(beanAliasParameters.getAliasStringMapObject0GetKey0()).append(RIGHT_BRACKET).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(void_).append(SPACE).append(setValue_)
                .append(LEFT_PAR).append(int_).append(SPACE).append(beanAliasParameters.getAliasStringMapObject0SetValue0())
                .append(COMMA).append(object_).append(SPACE).append(beanAliasParameters.getAliasStringMapObject0SetValue1()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(this_).append(DOT).append(values_).append(LEFT_BRACKET).append(beanAliasParameters.getAliasStringMapObject0SetValue0())
                .append(RIGHT_BRACKET).append(AFFECT).append(beanAliasParameters.getAliasStringMapObject0SetValue1()).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(object_).append(SPACE).append(getValue_).append(LEFT_PAR)
                .append(int_).append(SPACE).append(beanAliasParameters.getAliasStringMapObject0GetValue0()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(return_).append(SPACE).append(this_).append(DOT).append(values_)
                .append(LEFT_BRACKET).append(beanAliasParameters.getAliasStringMapObject0GetValue0()).append(RIGHT_BRACKET).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(void_).append(SPACE).append(aliasMapClear).append(EMPTY_PARAMS_SGN);
        file_.append(keys_).append(AFFECT).append(new_).append(SPACE).append(string_)
                .append(ACCESS_ZERO).append(END_LINE);
        file_.append(values_).append(AFFECT).append(new_).append(SPACE).append(object_)
                .append(ACCESS_ZERO).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(boolean_).append(SPACE).append(aliasMapIsEmpty).append(EMPTY_PARAMS_SGN);
        file_.append(return_).append(SPACE).append(keys_).append(DOT).append(length_)
                .append(EQ).append(ZERO).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(int_).append(SPACE).append(aliasMapSize).append(EMPTY_PARAMS_SGN);
        file_.append(return_).append(SPACE).append(keys_).append(DOT).append(length_).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(void_).append(SPACE)
                .append(put_).append(LEFT_PAR).append(string_).append(SPACE)
                .append(beanAliasParameters.getAliasStringMapObject0Put0()).append(COMMA).append(object_)
                .append(SPACE).append(beanAliasParameters.getAliasStringMapObject0Put1()).append(RIGHT_PAR).append(LEFT_BRACE);
        String indexPut_ = tr(INDEX, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0Put0(),beanAliasParameters.getAliasStringMapObject0Put1());
        file_.append(int_).append(SPACE).append(indexPut_).append(AFFECT)
                .append(indexOfEntry_).append(LEFT_PAR).append(beanAliasParameters.getAliasStringMapObject0Put0()).append(RIGHT_PAR)
                .append(END_LINE);
        file_.append(if_).append(LEFT_PAR).append(indexPut_).append(EQ).append(MINUS_ONE).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(addEntry_).append(LEFT_PAR).append(beanAliasParameters.getAliasStringMapObject0Put0()).append(COMMA)
                .append(beanAliasParameters.getAliasStringMapObject0Put1()).append(RIGHT_PAR).append(END_LINE);
        file_.append(return_).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(setValue_).append(LEFT_PAR).append(indexPut_).append(COMMA)
                .append(beanAliasParameters.getAliasStringMapObject0Put1()).append(RIGHT_PAR).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(boolean_).append(SPACE).append(aliasMapContains)
                .append(LEFT_PAR).append(string_).append(SPACE).append(beanAliasParameters.getAliasStringMapObject0Contains0()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(return_).append(SPACE).append(indexOfEntry_).append(LEFT_PAR)
                .append(beanAliasParameters.getAliasStringMapObject0Contains0()).append(RIGHT_PAR).append(DIFF).append(MINUS_ONE).append(END_LINE);
        file_.append(RIGHT_BRACE);
        String indexGetVal_ = tr(INDEX, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0GetVal0());
        file_.append(public_).append(SPACE).append(object_).append(SPACE).append(getVal_).append(LEFT_PAR)
                .append(string_).append(SPACE).append(beanAliasParameters.getAliasStringMapObject0GetVal0()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(int_).append(SPACE).append(indexGetVal_).append(AFFECT).append(indexOfEntry_)
                .append(LEFT_PAR).append(beanAliasParameters.getAliasStringMapObject0GetVal0()).append(RIGHT_PAR).append(END_LINE);
        file_.append(if_).append(LEFT_PAR).append(indexGetVal_).append(EQ).append(MINUS_ONE).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(return_).append(SPACE).append(null_).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(return_).append(SPACE).append(getValue_).append(LEFT_PAR).append(indexGetVal_)
                .append(RIGHT_PAR).append(END_LINE);
        file_.append(RIGHT_BRACE);

        file_.append(public_).append(SPACE).append(void_).append(SPACE)
                .append(addEntry_).append(LEFT_PAR).append(string_).append(SPACE)
                .append(beanAliasParameters.getAliasStringMapObject0AddEntry0()).append(COMMA).append(object_)
                .append(SPACE).append(beanAliasParameters.getAliasStringMapObject0AddEntry1()).append(RIGHT_PAR).append(LEFT_BRACE);
        String keysLoc_ = tr(KEYS, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0AddEntry0(),beanAliasParameters.getAliasStringMapObject0AddEntry1());
        String valuesLoc_ = tr(VALUES, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0AddEntry0(),beanAliasParameters.getAliasStringMapObject0AddEntry1());
        file_.append(string_).append(ARR_SPACE).append(keysLoc_).append(AFFECT).append(new_)
                .append(SPACE).append(string_).append(LEFT_BRACKET).append(this_)
                .append(DOT).append(keys_).append(DOT).append(length_)
                .append(ADD_ONE).append(RIGHT_BRACKET).append(END_LINE);
        String iAddEntry_ = tr(I_LOOP, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0AddEntry0(),beanAliasParameters.getAliasStringMapObject0AddEntry1());
        file_.append(for_).append(LEFT_PAR).append(int_).append(SPACE).append(iAddEntry_)
                .append(AFFECT_ZERO).append(END_LINE).append(iAddEntry_).append(LT)
                .append(this_).append(DOT)
                .append(keys_).append(DOT).append(length_).append(END_LINE)
                .append(iAddEntry_).append(INCR).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(keysLoc_).append(LEFT_BRACKET).append(iAddEntry_)
                .append(RIGHT_BRACKET).append(AFFECT).append(this_).append(DOT).append(keys_).append(LEFT_BRACKET)
                .append(iAddEntry_).append(RIGHT_BRACKET).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(keysLoc_).append(LEFT_BRACKET).append(this_).append(DOT).append(keys_)
                .append(DOT).append(length_).append(RIGHT_BRACKET).append(AFFECT).append(beanAliasParameters.getAliasStringMapObject0AddEntry0()).append(END_LINE);
        file_.append(this_).append(DOT).append(keys_).append(AFFECT).append(keysLoc_).append(END_LINE);
        file_.append(object_).append(ARR_SPACE).append(valuesLoc_).append(AFFECT).append(new_).append(SPACE)
                .append(object_).append(LEFT_BRACKET).append(this_).append(DOT).append(values_).append(DOT)
                .append(length_).append(ADD_ONE).append(RIGHT_BRACKET).append(END_LINE);
        file_.append(for_).append(LEFT_PAR).append(int_).append(SPACE).append(iAddEntry_)
                .append(AFFECT_ZERO).append(END_LINE).append(iAddEntry_).append(LT)
                .append(this_).append(DOT).append(values_).append(DOT).append(length_).append(END_LINE)
                .append(iAddEntry_).append(INCR).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(valuesLoc_).append(LEFT_BRACKET).append(iAddEntry_)
                .append(RIGHT_BRACKET).append(AFFECT).append(this_).append(DOT).append(values_).append(LEFT_BRACKET)
                .append(iAddEntry_).append(RIGHT_BRACKET).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(valuesLoc_).append(LEFT_BRACKET).append(this_).append(DOT).append(values_)
                .append(DOT).append(length_).append(RIGHT_BRACKET).append(AFFECT).append(beanAliasParameters.getAliasStringMapObject0AddEntry1()).append(END_LINE);
        file_.append(this_).append(DOT).append(values_).append(AFFECT).append(valuesLoc_)
                .append(END_LINE);
        file_.append(RIGHT_BRACE);

        file_.append(public_).append(SPACE).append(int_).append(SPACE).append(indexOfEntry_)
                .append(LEFT_PAR).append(string_).append(SPACE).append(beanAliasParameters.getAliasStringMapObject0IndexOfEntry0()).append(RIGHT_PAR).append(LEFT_BRACE);
        String iIndexOfEntry_ = tr(I_LOOP, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0IndexOfEntry0());
        file_.append(for_).append(LEFT_PAR).append(int_)
                .append(SPACE).append(iIndexOfEntry_).append(AFFECT_ZERO)
                .append(END_LINE).append(iIndexOfEntry_).append(LT).append(this_)
                .append(DOT).append(keys_).append(DOT).append(length_).append(END_LINE)
                .append(iIndexOfEntry_).append(INCR).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(if_).append(LEFT_PAR).append(this_).append(DOT).append(keys_).append(LEFT_BRACKET).append(iIndexOfEntry_).append(RIGHT_BRACKET).append(EQ).append(beanAliasParameters.getAliasStringMapObject0IndexOfEntry0()).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(return_).append(SPACE).append(iIndexOfEntry_).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(RIGHT_BRACE);
        file_.append(return_).append(MINUS_ONE).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(public_).append(SPACE).append(void_).append(SPACE).append(putAll_).append(LEFT_PAR)
                .append(getAliasStringMapObject()).append(SPACE).append(beanAliasParameters.getAliasStringMapObject0PutAll0()).append(RIGHT_PAR).append(LEFT_BRACE);
        String lenPutAll_ = tr(LEN, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0PutAll0());
        file_.append(int_).append(SPACE).append(lenPutAll_).append(AFFECT).append(LEFT_PAR)
                .append(beanAliasParameters.getAliasStringMapObject0PutAll0()).append(RIGHT_PAR).append(DOT).append(keys_).append(DOT)
                .append(length_).append(END_LINE);
        String iPutAll_ = tr(I_LOOP, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0PutAll0());
        file_.append(for_).append(LEFT_PAR).append(int_).append(SPACE).append(iPutAll_)
                .append(AFFECT_ZERO).append(END_LINE).append(iPutAll_).append(LT)
                .append(lenPutAll_).append(END_LINE).append(iPutAll_).append(INCR).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(SPACE).append(put_).append(LEFT_PAR).append(LEFT_PAR)
                .append(beanAliasParameters.getAliasStringMapObject0PutAll0()).append(RIGHT_PAR).append(DOT)
                .append(keys_).append(LEFT_BRACKET).append(iPutAll_)
                .append(RIGHT_BRACKET).append(COMMA).append(LEFT_PAR).append(beanAliasParameters.getAliasStringMapObject0PutAll0())
                .append(RIGHT_PAR).append(DOT).append(values_).append(LEFT_BRACKET).append(iPutAll_)
                .append(RIGHT_BRACKET).append(RIGHT_PAR).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(RIGHT_BRACE);
        keysLoc_ = tr(KEYS, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0RemoveKey0());
        valuesLoc_ = tr(VALUES, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0RemoveKey0());
        file_.append(public_).append(SPACE).append(void_).append(SPACE).append(removeKey_)
                .append(LEFT_PAR).append(string_).append(SPACE).append(beanAliasParameters.getAliasStringMapObject0RemoveKey0()).append(RIGHT_PAR).append(LEFT_BRACE);
        String indexRemoveKey_ = tr(INDEX, _keyWords, primitiveTypes_, coreNames_,beanAliasParameters.getAliasStringMapObject0RemoveKey0());
        file_.append(int_).append(SPACE).append(indexRemoveKey_)
                .append(AFFECT).append(indexOfEntry_).append(LEFT_PAR).append(beanAliasParameters.getAliasStringMapObject0RemoveKey0())
                .append(RIGHT_PAR).append(END_LINE);
        file_.append(if_).append(LEFT_PAR).append(indexRemoveKey_).append(EQ).append(MINUS_ONE).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(return_).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(string_).append(ARR_SPACE).append(keysLoc_).append(AFFECT).append(new_)
                .append(SPACE).append(string_).append(LEFT_BRACKET).append(this_).append(DOT).append(keys_)
                .append(DOT).append(length_).append(MINUS_ONE).append(RIGHT_BRACKET).append(END_LINE);
        String iRemoveKey_ = tr(I_LOOP, _keyWords, primitiveTypes_, coreNames_);
        file_.append(for_).append(LEFT_PAR).append(int_).append(SPACE).append(iRemoveKey_).append(AFFECT_ZERO)
                .append(END_LINE).append(iRemoveKey_)
                .append(LT).append(indexRemoveKey_)
                .append(END_LINE).append(iRemoveKey_)
                .append(INCR).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(keysLoc_).append(LEFT_BRACKET).append(iRemoveKey_).append(RIGHT_BRACKET).append(AFFECT)
                .append(this_).append(DOT).append(keys_).append(LEFT_BRACKET).append(iRemoveKey_)
                .append(RIGHT_BRACKET).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(for_).append(LEFT_PAR).append(int_).append(SPACE).append(iRemoveKey_)
                .append(AFFECT).append(indexRemoveKey_).append(ADD_ONE).append(END_LINE)
                .append(iRemoveKey_).append(LT).append(this_).append(DOT)
                .append(keys_).append(DOT).append(length_).append(END_LINE)
                .append(iRemoveKey_).append(INCR).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(keysLoc_).append(LEFT_BRACKET).append(iRemoveKey_).append(MINUS_ONE).append(RIGHT_BRACKET).append(AFFECT).append(this_)
                .append(DOT).append(keys_).append(LEFT_BRACKET).append(iRemoveKey_).append(RIGHT_BRACKET)
                .append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(this_).append(DOT).append(keys_).append(AFFECT).append(keysLoc_).append(END_LINE);

        file_.append(object_).append(ARR_SPACE).append(valuesLoc_).append(AFFECT).append(new_).append(SPACE)
                .append(object_).append(LEFT_BRACKET).append(this_).append(DOT)
                .append(values_).append(DOT).append(length_).append(MINUS_ONE).append(RIGHT_BRACKET)
                .append(END_LINE);
        file_.append(for_).append(LEFT_PAR).append(int_).append(SPACE).append(iRemoveKey_).append(AFFECT_ZERO)
                .append(END_LINE).append(iRemoveKey_)
                .append(LT).append(indexRemoveKey_)
                .append(END_LINE).append(iRemoveKey_)
                .append(INCR).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(valuesLoc_).append(LEFT_BRACKET).append(iRemoveKey_).append(RIGHT_BRACKET).append(AFFECT)
                .append(this_).append(DOT).append(values_).append(LEFT_BRACKET).append(iRemoveKey_)
                .append(RIGHT_BRACKET).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(for_).append(LEFT_PAR).append(int_).append(SPACE).append(iRemoveKey_)
                .append(AFFECT).append(indexRemoveKey_).append(ADD_ONE).append(END_LINE)
                .append(iRemoveKey_).append(LT).append(this_).append(DOT).append(values_)
                .append(DOT).append(length_).append(END_LINE).append(iRemoveKey_).append(INCR).append(RIGHT_PAR).append(LEFT_BRACE);
        file_.append(valuesLoc_).append(LEFT_BRACKET).append(iRemoveKey_).append(MINUS_ONE).append(RIGHT_BRACKET).append(AFFECT)
                .append(this_).append(DOT).append(values_).append(LEFT_BRACKET).append(iRemoveKey_)
                .append(RIGHT_BRACKET).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(this_).append(DOT).append(values_).append(AFFECT).append(valuesLoc_).append(END_LINE);
        file_.append(RIGHT_BRACE);
        file_.append(RIGHT_BRACE);
        files_.put(getAliasStringMapObject(), file_.toString());
        file_ = new StringBuilder();
        file_.append(public_).append(SPACE).append(interface_).append(SPACE)
                .append(aliasValidator).append(LEFT_BRACE);
        file_.append(public_).append(SPACE).append(getAliasMessage()).append(SPACE)
                .append(aliasValidate).append(LEFT_PAR);
        file_.append(object_).append(SPACE).append(beanAliasParameters.getAliasValidator0Validate0()).append(COMMA);
        file_.append(object_).append(SPACE).append(beanAliasParameters.getAliasValidator0Validate1()).append(COMMA);
        file_.append(object_).append(SPACE).append(beanAliasParameters.getAliasValidator0Validate2()).append(COMMA);
        file_.append(object_).append(ARR_SPACE).append(beanAliasParameters.getAliasValidator0Validate3()).append(COMMA);
        file_.append(string_).append(SPACE).append(beanAliasParameters.getAliasValidator0Validate4()).append(COMMA);
        file_.append(string_).append(SPACE).append(beanAliasParameters.getAliasValidator0Validate5());
        file_.append(RIGHT_PAR);
        file_.append(END_LINE);
        file_.append(RIGHT_BRACE);
        files_.put(aliasValidator, file_.toString());
        return files_;
    }
    private static String tr(String _var, KeyWords _keyWords, StringMap<PrimitiveType> _primitiveTypes, AliasCore _coreNames, String... _params) {
        CustList<String> allKeysWords_ = _keyWords.allKeyWords().values();
        allKeysWords_.addAllElts(_primitiveTypes.getKeys());
        allKeysWords_.add(_coreNames.getAliasVoid());
        for (String p: _params){
            allKeysWords_.add(p);
        }
        return ValidatorStandard.tr(allKeysWords_, _var);
    }

    public void buildOther(LgNamesContent _content, RendExecutingBlocks _rendExecutingBlocks) {
        CustList<CstFieldInfo> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasMessage, fields_, constructors_, methods_, _content.getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC,new FctMessageNew0(aliasMessage));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC, new StringList(beanAliasParameters.getAliasMessage1NewMessage0()),new FctMessageNew1(aliasMessage));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageFormat, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.NORMAL,new FctMessageFormat(aliasMessage));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageGetArgs, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.NORMAL,new FctMessageGetArgs(aliasMessage));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasMessageSetArgs, params_, _content.getCoreNames().getAliasVoid(), true, MethodModifier.NORMAL, new StringList(beanAliasParameters.getAliasMessage0SetArgs0()),new FctMessageSetArgs(aliasMessage));
        methods_.add( method_);
        _content.getStandards().addEntry(aliasMessage, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasDocument, fields_, constructors_, methods_, _content.getReflect().getAliasAnnotated(), StdClassModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDocumentAll, params_, StringExpUtil.getPrettyArrayType(aliasDocument), false, MethodModifier.STATIC,new FctDocumentAll(_rendExecutingBlocks,aliasDocument));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(_content.getReflect().getAliasGetDeclaredLocalTypes(), params_, StringExpUtil.getPrettyArrayType(_content.getReflect().getAliasClassType()), false, MethodModifier.FINAL, new FctDocumentGetDeclaredLocalTypes());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(_content.getReflect().getAliasGetDeclaredAnonymousTypes(), params_, StringExpUtil.getPrettyArrayType(_content.getReflect().getAliasClassType()), false, MethodModifier.FINAL, new FctDocumentGetDeclaredAnonymousTypes());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDocumentBeanArray, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC,new FctDocumentBeanArray(_rendExecutingBlocks));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDocumentBeanName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new FctDocumentBeanName());
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasDocumentBeanValue, params_, aliasBean, false, MethodModifier.STATIC, new StringList(beanAliasParameters.getAliasDocument0BeanValue0()),new FctDocumentBeanValue(_rendExecutingBlocks));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDocumentValidatorArray, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC,new FctDocumentValidatorArray(_rendExecutingBlocks));
        methods_.add( method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasDocumentValidatorValue, params_, aliasValidator, false, MethodModifier.STATIC, new StringList(beanAliasParameters.getAliasDocument0ValidatorValue0()),new FctDocumentValidatorValue(_rendExecutingBlocks));
        methods_.add( method_);
        _content.getStandards().addEntry(aliasDocument, std_);

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
        setAliasDocument(LgNamesContent.get(_util, _cust, DOCUMENT));
        setAliasDocumentAll(LgNamesContent.get(_util, _cust, DOCUMENT_ALL));
        setAliasNewMessage(LgNamesContent.get(_util, _cust, NEW_MESSAGE));
        setAliasMessageFormat(LgNamesContent.get(_util, _cust, MESSAGE_FORMAT));
        setAliasMessageGetArgs(LgNamesContent.get(_util, _cust, MESSAGE_GET_ARGS));
        setAliasMessageSetArgs(LgNamesContent.get(_util, _cust, MESSAGE_SET_ARGS));
        setAliasDocumentBeanArray(LgNamesContent.get(_util, _cust, DOCUMENT_BEAN_ARRAY));
        setAliasDocumentBeanName(LgNamesContent.get(_util, _cust, DOCUMENT_BEAN_NAME));
        setAliasDocumentBeanValue(LgNamesContent.get(_util, _cust, DOCUMENT_BEAN_VALUE));
        setAliasDocumentValidatorArray(LgNamesContent.get(_util, _cust, DOCUMENT_VALIDATOR_ARRAY));
        setAliasDocumentValidatorValue(LgNamesContent.get(_util, _cust, DOCUMENT_VALIDATOR_VALUE));
    }

    public StringMap<String> allRefTypes() {
        StringMap<String> types_ = new StringMap<String>();
        types_.addEntry(MESSAGE,getAliasMessage());
        types_.addEntry(DOCUMENT,getAliasDocument());
        types_.addEntry(VALIDATOR,getAliasValidator());
        types_.addEntry(STRING_MAP_OBJECT,getAliasStringMapObject());
        types_.addEntry(BEAN,getAliasBean());
        return types_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(AliasReflection _reflect) {
        StringMap<CustList<KeyValueMemberName>> methods_ = new StringMap<CustList<KeyValueMemberName>>();
        methods_.addEntry(getAliasMessage(),
                new CustList<KeyValueMemberName>(new KeyValueMemberName(NEW_MESSAGE,getAliasNewMessage()),
                        new KeyValueMemberName(MESSAGE_FORMAT,getAliasMessageFormat()),
                        new KeyValueMemberName(MESSAGE_GET_ARGS,getAliasMessageGetArgs()),
                        new KeyValueMemberName(MESSAGE_SET_ARGS,getAliasMessageSetArgs())));
        CustList<KeyValueMemberName> lis_ = LgNamesContent.listAnnot(_reflect);
        lis_.add(new KeyValueMemberName(DOCUMENT_ALL,getAliasDocumentAll()));
        lis_.add(new KeyValueMemberName(DOCUMENT_BEAN_ARRAY,getAliasDocumentBeanArray()));
        lis_.add(new KeyValueMemberName(DOCUMENT_BEAN_NAME,getAliasDocumentBeanName()));
        lis_.add(new KeyValueMemberName(DOCUMENT_BEAN_VALUE,getAliasDocumentBeanValue()));
        lis_.add(new KeyValueMemberName(DOCUMENT_VALIDATOR_ARRAY,getAliasDocumentValidatorArray()));
        lis_.add(new KeyValueMemberName(DOCUMENT_VALIDATOR_VALUE,getAliasDocumentValidatorValue()));
        lis_.add(new KeyValueMemberName(GET_DECLARED_LOCAL_TYPES,_reflect.getAliasGetDeclaredLocalTypes()));
        lis_.add(new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES,_reflect.getAliasGetDeclaredAnonymousTypes()));
        methods_.addEntry(getAliasDocument(), lis_);
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

    public String getAliasDocument() {
        return aliasDocument;
    }

    public void setAliasDocument(String _doc) {
        this.aliasDocument = _doc;
    }

    public String getAliasDocumentAll() {
        return aliasDocumentAll;
    }

    public void setAliasDocumentAll(String _doc) {
        this.aliasDocumentAll = _doc;
    }

    public String getAliasDocumentBeanArray() {
        return aliasDocumentBeanArray;
    }

    public void setAliasDocumentBeanArray(String _v) {
        this.aliasDocumentBeanArray = _v;
    }

    public String getAliasDocumentBeanName() {
        return aliasDocumentBeanName;
    }

    public void setAliasDocumentBeanName(String _v) {
        this.aliasDocumentBeanName = _v;
    }

    public String getAliasDocumentBeanValue() {
        return aliasDocumentBeanValue;
    }

    public void setAliasDocumentBeanValue(String _v) {
        this.aliasDocumentBeanValue = _v;
    }

    public String getAliasDocumentValidatorArray() {
        return aliasDocumentValidatorArray;
    }

    public void setAliasDocumentValidatorArray(String _v) {
        this.aliasDocumentValidatorArray = _v;
    }

    public String getAliasDocumentValidatorValue() {
        return aliasDocumentValidatorValue;
    }

    public void setAliasDocumentValidatorValue(String _v) {
        this.aliasDocumentValidatorValue = _v;
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
