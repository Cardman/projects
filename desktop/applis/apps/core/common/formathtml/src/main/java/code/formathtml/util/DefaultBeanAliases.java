package code.formathtml.util;

import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.Struct;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.structs.Message;
import code.formathtml.structs.MessageStruct;
import code.formathtml.util.stds.*;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class DefaultBeanAliases implements AbsAliasFileBuilder {
    public static final String TYPES_RENDER = "types_render";
    public static final String GET_DECLARED_ANONYMOUS_TYPES="__________1932";
    public static final String GET_DECLARED_LOCAL_TYPES="__________1933";
    public static final String BEAN="2149";
    public static final String MAP_KEYS="2150";
    public static final String MAP_VALUES="2151";
    public static final String MAP_INDEX_OF_ENTRY="2152";
    public static final String MAP_ADD_ENTRY="2153";
    public static final String MAP_GET_VALUE="2154";
    public static final String MAP_FIRST_VALUE="2155";
    public static final String MAP_LAST_VALUE="2156";
    public static final String MAP_SET_VALUE="2157";
    public static final String MAP_PUT="2158";
    public static final String MAP_CONTAINS="2159";
    public static final String MAP_PUT_ALL="2160";
    public static final String MAP_GET_VAL="2161";
    public static final String MAP_REMOVE_KEY="2162";
    public static final String MAP_GET_KEY="2163";
    public static final String MAP_FIRST_KEY="2164";
    public static final String MAP_LAST_KEY="2165";
    public static final String MAP_SET_KEY="2166";
    public static final String MAP_SIZE="2167";
    public static final String MAP_IS_EMPTY="2168";
    public static final String MAP_CLEAR="2169";
    public static final String VALIDATOR="2170";
    public static final String VALIDATE="2171";
    public static final String DATA_BASE_FIELD="2172";
    public static final String FORMS="2173";
    public static final String SET_FORMS="2174";
    public static final String GET_FORMS="2175";
    public static final String LANGUAGE="2176";
    public static final String SET_LANGUAGE="2177";
    public static final String GET_LANGUAGE="2178";
    public static final String SCOPE="2179";
    public static final String SET_SCOPE="2180";
    public static final String GET_SCOPE="2181";
    public static final String SET_DATA_BASE="2182";
    public static final String GET_DATA_BASE="2183";
    public static final String BEFORE_DISPLAYING="2184";
    public static final String STRING_MAP_OBJECT="2185";
    public static final String MESSAGE="2186";
    public static final String DOCUMENT="2187";
    public static final String DOCUMENT_ALL="2188";
    public static final String DOCUMENT_VALIDATOR_ARRAY="2189";
    public static final String DOCUMENT_VALIDATOR_VALUE="2190";
    public static final String DOCUMENT_REINIT_ARRAY="2190_";
    public static final String DOCUMENT_REINIT_VALUE="2190__";
    public static final String DOCUMENT_BEAN_ARRAY="2191";
    public static final String DOCUMENT_BEAN_VALUE="2192";
    public static final String DOCUMENT_BEAN_NAME="2193";
    public static final String NEW_MESSAGE="2194";
    public static final String MESSAGE_FORMAT="2195";
    public static final String MESSAGE_GET_ARGS="2196";
    public static final String MESSAGE_SET_ARGS="2197";
    public static final String REINIT_EQ_INTERFACE="2227";
    public static final String REINIT_EQ_METHOD="2228";
    private static final char END_LINE = ';';
    private static final char SPACE = ' ';
    private static final char LEFT_BRACE = '{';
    private static final char LEFT_PAR = '(';
    private static final char RIGHT_PAR = ')';
    private static final String EMPTY_PARAMS_SGN = BeanLgNames.EMPTY_STRING+LEFT_PAR+RIGHT_PAR+LEFT_BRACE;
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
    private static final String AFFECT_ZERO = BeanLgNames.EMPTY_STRING+AFFECT+ZERO;
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
    private String aliasDocumentReinitArray="reinitArr";
    private String aliasDocumentReinitValue="reinitValue";
    private String aliasNewMessage="newStandardMessage";
    private String aliasMessageFormat="format";
    private String aliasMessageGetArgs="getArgs";
    private String aliasMessageSetArgs="setArgs";
    private String aliasReinitInterface="code.bean.Reinitialised";
    private String aliasReinitMethod="reinitialise";
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
        file_ = new StringBuilder();
        file_.append(public_).append(SPACE).append(interface_).append(SPACE)
                .append(aliasReinitInterface).append(LEFT_BRACE);
        file_.append(public_).append(SPACE).append(_content.getPrimTypes().getAliasPrimBoolean()).append(SPACE)
                .append(aliasReinitMethod).append(LEFT_PAR);
        file_.append(string_).append(SPACE).append(beanAliasParameters.getAliasReinit0ReinitBean0()).append(COMMA);
        file_.append(string_).append(SPACE).append(beanAliasParameters.getAliasReinit0ReinitBean1()).append(COMMA);
        file_.append(string_).append(SPACE).append(beanAliasParameters.getAliasReinit0ReinitBean2()).append(COMMA);
        file_.append(string_).append(SPACE).append(beanAliasParameters.getAliasReinit0ReinitBean3()).append(COMMA);
        file_.append(string_).append(SPACE).append(beanAliasParameters.getAliasReinit0ReinitBean4()).append(COMMA);
        file_.append(string_).append(SPACE).append(beanAliasParameters.getAliasReinit0ReinitBean5());
        file_.append(RIGHT_PAR);
        file_.append(END_LINE);
        file_.append(RIGHT_BRACE);
        files_.put(aliasReinitInterface, file_.toString());
        return files_;
    }
    private static String tr(String _var, KeyWords _keyWords, StringMap<PrimitiveType> _primitiveTypes, AliasCore _coreNames, String... _params) {
        CustList<String> allKeysWords_ = _keyWords.allKeyWordsValues();
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
        std_.addSuperStdTypes(_content.getCoreNames().getObjType());
        params_ = new StringList();
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC,new FctMessageNew0(aliasMessage));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC, new StringList(beanAliasParameters.getAliasMessage1NewMessage0()),new FctMessageNew1(aliasMessage));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageFormat, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.NORMAL,new FctMessageFormat(aliasMessage));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageGetArgs, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.NORMAL,new FctMessageGetArgs(aliasMessage));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasMessageSetArgs, params_, _content.getCoreNames().getAliasVoid(), true, MethodModifier.NORMAL, new StringList(beanAliasParameters.getAliasMessage0SetArgs0()),new FctMessageSetArgs(aliasMessage));
        StandardNamedFunction.addFct(methods_, method_);
        StandardType.addType(_content.getStandards(),aliasMessage, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasDocument, fields_, constructors_, methods_, _content.getReflect().getAliasAnnotated(), StdClassModifier.ABSTRACT);
        std_.addSuperStdTypes(_content.getReflect().getAnnotType());
        std_.addSuperStdTypes(_content.getCoreNames().getObjType());
        params_ = new StringList();
        method_ = new StandardMethod(aliasDocumentAll, params_, StringExpUtil.getPrettyArrayType(aliasDocument), false, MethodModifier.STATIC,new FctDocumentAll(_rendExecutingBlocks,aliasDocument));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(_content.getReflect().getAliasGetDeclaredLocalTypes(), params_, StringExpUtil.getPrettyArrayType(_content.getReflect().getAliasClassType()), false, MethodModifier.FINAL, new FctDocumentGetDeclaredLocalTypes());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(_content.getReflect().getAliasGetDeclaredAnonymousTypes(), params_, StringExpUtil.getPrettyArrayType(_content.getReflect().getAliasClassType()), false, MethodModifier.FINAL, new FctDocumentGetDeclaredAnonymousTypes());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDocumentBeanArray, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC,new FctDocumentBeanArray(_rendExecutingBlocks));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDocumentBeanName, params_, _content.getCharSeq().getAliasString(), false, MethodModifier.FINAL,new FctDocumentBeanName());
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasDocumentBeanValue, params_, aliasBean, false, MethodModifier.STATIC, new StringList(beanAliasParameters.getAliasDocument0BeanValue0()),new FctDocumentBeanValue(_rendExecutingBlocks));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDocumentValidatorArray, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC,new FctDocumentValidatorArray(_rendExecutingBlocks));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasDocumentValidatorValue, params_, aliasValidator, false, MethodModifier.STATIC, new StringList(beanAliasParameters.getAliasDocument0ValidatorValue0()),new FctDocumentValidatorValue(_rendExecutingBlocks));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDocumentReinitArray, params_, StringExpUtil.getPrettyArrayType(_content.getCharSeq().getAliasString()), false, MethodModifier.STATIC,new FctDocumentReinitArray(_rendExecutingBlocks));
        StandardNamedFunction.addFct(methods_, method_);
        params_ = new StringList(_content.getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasDocumentReinitValue, params_, aliasReinitInterface, false, MethodModifier.STATIC, new StringList(beanAliasParameters.getAliasDocument0ReinitValue0()),new FctDocumentReinitValue(_rendExecutingBlocks));
        StandardNamedFunction.addFct(methods_, method_);
        StandardType.addType(_content.getStandards(),aliasDocument, std_);

    }
    public static void enTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(TYPES_RENDER, DefaultBeanAliases.en());
        _lgs.getMapping().addEntry(RendKeyWords.TAGS_FILE, RendKeyWords.enTags());
        _lgs.getMapping().addEntry(RendKeyWords.ATTRS_FILE, RendKeyWords.enAttrs());
        _lgs.getMapping().addEntry(RendKeyWords.VALUES_FILE, RendKeyWords.enValues());
        _lgs.getMapping().addEntry(RendKeyWords.STYLE_VALUES_FILE, RendKeyWords.enStyleValues());
        _lgs.getMapping().addEntry(RendKeyWords.STYLE_UNITS_FILE, RendKeyWords.enStyleUnits());
        _lgs.getMapping().addEntry(RendKeyWords.STYLE_ATTRS_FILE, RendKeyWords.enStyleAttrs());
        _lgs.getMapping().addEntry(RendKeyWords.STYLE_DEF_FILE, RendKeyWords.messDef());
        _lgs.getMapping().addEntry(RendAnalysisMessages.FILE, RendAnalysisMessages.en());
    }

    public static void frTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(TYPES_RENDER, DefaultBeanAliases.fr());
        _lgs.getMapping().addEntry(RendKeyWords.TAGS_FILE, RendKeyWords.frTags());
        _lgs.getMapping().addEntry(RendKeyWords.ATTRS_FILE, RendKeyWords.frAttrs());
        _lgs.getMapping().addEntry(RendKeyWords.VALUES_FILE, RendKeyWords.frValues());
        _lgs.getMapping().addEntry(RendKeyWords.STYLE_VALUES_FILE, RendKeyWords.frStyleValues());
        _lgs.getMapping().addEntry(RendKeyWords.STYLE_UNITS_FILE, RendKeyWords.frStyleUnits());
        _lgs.getMapping().addEntry(RendKeyWords.STYLE_ATTRS_FILE, RendKeyWords.frStyleAttrs());
        _lgs.getMapping().addEntry(RendKeyWords.STYLE_DEF_FILE, RendKeyWords.messDef());
        _lgs.getMapping().addEntry(RendAnalysisMessages.FILE, RendAnalysisMessages.fr());
    }
    public static TranslationsFile en() {
        TranslationsFile en_ = new TranslationsFile();
        en(en_);
        BeanAliasParameters.en(en_);
        return en_;
    }
    public static TranslationsFile fr() {
        TranslationsFile fr_ = new TranslationsFile();
        fr(fr_);
        BeanAliasParameters.fr(fr_);
        return fr_;
    }
    public static void en(TranslationsFile _en) {
        _en.add(BEAN,"Bean=$core.Bean");
        _en.add(MAP_KEYS,"MapKeys=keys");
        _en.add(MAP_VALUES,"MapValues=values");
        _en.add(MAP_INDEX_OF_ENTRY,"MapIndexOfEntry=indexOfEntry");
        _en.add(MAP_ADD_ENTRY,"MapAddEntry=addEntry");
        _en.add(MAP_GET_VALUE,"MapGetValue=getValue");
        _en.add(MAP_FIRST_VALUE,"MapFirstValue=firstValue");
        _en.add(MAP_LAST_VALUE,"MapLastValue=lastValue");
        _en.add(MAP_SET_VALUE,"MapSetValue=setValue");
        _en.add(MAP_PUT,"MapPut=put");
        _en.add(MAP_CONTAINS,"MapContains=contains");
        _en.add(MAP_PUT_ALL,"MapPutAll=putAll");
        _en.add(MAP_GET_VAL,"MapGetVal=getVal");
        _en.add(MAP_REMOVE_KEY,"MapRemoveKey=removeKey");
        _en.add(MAP_GET_KEY,"MapGetKey=getKey");
        _en.add(MAP_FIRST_KEY,"MapFirstKey=firstKey");
        _en.add(MAP_LAST_KEY,"MapLastKey=lastKey");
        _en.add(MAP_SET_KEY,"MapSetKey=setKey");
        _en.add(MAP_SIZE,"MapSize=size");
        _en.add(MAP_IS_EMPTY,"MapIsEmpty=isEmpty");
        _en.add(MAP_CLEAR,"MapClear=clear");
        _en.add(VALIDATOR,"Validator=$core.Validator");
        _en.add(VALIDATE,"Validate=validate");
        _en.add(REINIT_EQ_INTERFACE,"ReinitInterface=$core.Reinitialised");
        _en.add(REINIT_EQ_METHOD,"ReinitMethod=reinitialise");
        _en.add(DATA_BASE_FIELD,"DataBaseField=dataBase");
        _en.add(FORMS,"Forms=forms");
        _en.add(SET_FORMS,"SetForms=setForms");
        _en.add(GET_FORMS,"GetForms=getForms");
        _en.add(LANGUAGE,"Language=language");
        _en.add(SET_LANGUAGE,"SetLanguage=setLanguage");
        _en.add(GET_LANGUAGE,"GetLanguage=getLanguage");
        _en.add(SCOPE,"Scope=scope");
        _en.add(SET_SCOPE,"SetScope=setScope");
        _en.add(GET_SCOPE,"GetScope=getScope");
        _en.add(SET_DATA_BASE,"SetDataBase=setDataBase");
        _en.add(GET_DATA_BASE,"GetDataBase=getDataBase");
        _en.add(BEFORE_DISPLAYING,"BeforeDisplaying=beforeDisplaying");
        _en.add(STRING_MAP_OBJECT,"StringMapObject=$core.StringMapObject");
        _en.add(MESSAGE,"Message=$core.Message");
        _en.add(DOCUMENT,"Document=$core.Document");
        _en.add(DOCUMENT_ALL,"DocumentAll=all");
        _en.add(DOCUMENT_VALIDATOR_ARRAY,"DocumentValidatorArray=validatorArray");
        _en.add(DOCUMENT_VALIDATOR_VALUE,"DocumentValidatorValue=validatorValue");
        _en.add(DOCUMENT_REINIT_ARRAY,"DocumentReinitArray=reinitArray");
        _en.add(DOCUMENT_REINIT_VALUE,"DocumentReinitValue=reinitValue");
        _en.add(DOCUMENT_BEAN_ARRAY,"DocumentBeanArray=beanArray");
        _en.add(DOCUMENT_BEAN_VALUE,"DocumentBeanValue=beanValue");
        _en.add(DOCUMENT_BEAN_NAME,"DocumentBeanName=beanName");
        _en.add(NEW_MESSAGE,"NewMessage=newStandardMessage");
        _en.add(MESSAGE_FORMAT,"MessageFormat=format");
        _en.add(MESSAGE_GET_ARGS,"MessageGetArgs=getArgs");
        _en.add(MESSAGE_SET_ARGS,"MessageSetArgs=setArgs");
    }
    public static void fr(TranslationsFile _fr) {
        _fr.add(BEAN,"Bean=$coeur.Graine");
        _fr.add(MAP_KEYS,"MapKeys=cles");
        _fr.add(MAP_VALUES,"MapValues=valeurs");
        _fr.add(MAP_INDEX_OF_ENTRY,"MapIndexOfEntry=indicePaire");
        _fr.add(MAP_ADD_ENTRY,"MapAddEntry=ajPaire");
        _fr.add(MAP_GET_VALUE,"MapGetValue=obtValeur");
        _fr.add(MAP_FIRST_VALUE,"MapFirstValue=preVal");
        _fr.add(MAP_LAST_VALUE,"MapLastValue=derVal");
        _fr.add(MAP_SET_VALUE,"MapSetValue=majVal");
        _fr.add(MAP_PUT,"MapPut=ajOuMaj");
        _fr.add(MAP_CONTAINS,"MapContains=contient");
        _fr.add(MAP_PUT_ALL,"MapPutAll=ajOuMajTous");
        _fr.add(MAP_GET_VAL,"MapGetVal=obtVal");
        _fr.add(MAP_REMOVE_KEY,"MapRemoveKey=supprCle");
        _fr.add(MAP_GET_KEY,"MapGetKey=obtCle");
        _fr.add(MAP_FIRST_KEY,"MapFirstKey=preCle");
        _fr.add(MAP_LAST_KEY,"MapLastKey=derCle");
        _fr.add(MAP_SET_KEY,"MapSetKey=majCle");
        _fr.add(MAP_SIZE,"MapSize=taille");
        _fr.add(MAP_IS_EMPTY,"MapIsEmpty=estVide");
        _fr.add(MAP_CLEAR,"MapClear=suppr");
        _fr.add(VALIDATOR,"Validator=$coeur.Validateur");
        _fr.add(VALIDATE,"Validate=valider");
        _fr.add(REINIT_EQ_INTERFACE,"ReinitInterface=$coeur.Reinitialise");
        _fr.add(REINIT_EQ_METHOD,"ReinitMethod=reinitialiser");
        _fr.add(DATA_BASE_FIELD,"DataBaseField=baseDonnees");
        _fr.add(FORMS,"Forms=formulaire");
        _fr.add(SET_FORMS,"SetForms=majFormulaire");
        _fr.add(GET_FORMS,"GetForms=obtFormulaire");
        _fr.add(LANGUAGE,"Language=langue");
        _fr.add(SET_LANGUAGE,"SetLanguage=majLangue");
        _fr.add(GET_LANGUAGE,"GetLanguage=obtLangue");
        _fr.add(SCOPE,"Scope=scope");
        _fr.add(SET_SCOPE,"SetScope=majScope");
        _fr.add(GET_SCOPE,"GetScope=obtScope");
        _fr.add(SET_DATA_BASE,"SetDataBase=majBaseDonnees");
        _fr.add(GET_DATA_BASE,"GetDataBase=obtBaseDonnees");
        _fr.add(BEFORE_DISPLAYING,"BeforeDisplaying=avantAffiche");
        _fr.add(STRING_MAP_OBJECT,"StringMapObject=$coeur.StringMapObject");
        _fr.add(MESSAGE,"Message=$coeur.Message");
        _fr.add(DOCUMENT,"Document=$coeur.Document");
        _fr.add(DOCUMENT_ALL,"DocumentAll=tous");
        _fr.add(DOCUMENT_VALIDATOR_ARRAY,"DocumentValidatorArray=validateurTab");
        _fr.add(DOCUMENT_VALIDATOR_VALUE,"DocumentValidatorValue=validateurValeur");
        _fr.add(DOCUMENT_REINIT_ARRAY,"DocumentReinitArray=reinitTab");
        _fr.add(DOCUMENT_REINIT_VALUE,"DocumentReinitValue=reinitValeur");
        _fr.add(DOCUMENT_BEAN_ARRAY,"DocumentBeanArray=graineTab");
        _fr.add(DOCUMENT_BEAN_VALUE,"DocumentBeanValue=graineValeur");
        _fr.add(DOCUMENT_BEAN_NAME,"DocumentBeanName=graineNom");
        _fr.add(NEW_MESSAGE,"NewMessage=nvuMessageStandard");
        _fr.add(MESSAGE_FORMAT,"MessageFormat=format");
        _fr.add(MESSAGE_GET_ARGS,"MessageGetArgs=obtArguments");
        _fr.add(MESSAGE_SET_ARGS,"MessageSetArgs=majArguments");
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> m_ = new CustList<CustList<KeyValueMemberName>>();
        m_.addAllElts(beanAliasParameters.allTableTypeMethodParamNames(_mapping));
        return m_;
    }
    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        beanAliasParameters.build(_util, _cust, _mapping);
        setAliasBean(LgNamesContent.get(_util, _cust, _mapping.getVal(BEAN)));
        setAliasMapKeys(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_KEYS)));
        setAliasMapValues(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_VALUES)));
        setAliasMapIndexOfEntry(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_INDEX_OF_ENTRY)));
        setAliasMapAddEntry(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_ADD_ENTRY)));
        setAliasMapGetValue(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_GET_VALUE)));
        setAliasMapFirstValue(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_FIRST_VALUE)));
        setAliasMapLastValue(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_LAST_VALUE)));
        setAliasMapSetValue(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_SET_VALUE)));
        setAliasMapPut(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_PUT)));
        setAliasMapContains(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_CONTAINS)));
        setAliasMapPutAll(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_PUT_ALL)));
        setAliasMapGetVal(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_GET_VAL)));
        setAliasMapRemoveKey(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_REMOVE_KEY)));
        setAliasMapGetKey(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_GET_KEY)));
        setAliasMapFirstKey(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_FIRST_KEY)));
        setAliasMapLastKey(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_LAST_KEY)));
        setAliasMapSetKey(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_SET_KEY)));
        setAliasMapSize(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_SIZE)));
        setAliasMapIsEmpty(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_IS_EMPTY)));
        setAliasMapClear(LgNamesContent.get(_util, _cust, _mapping.getVal(MAP_CLEAR)));
        setAliasValidator(LgNamesContent.get(_util, _cust, _mapping.getVal(VALIDATOR)));
        setAliasValidate(LgNamesContent.get(_util, _cust, _mapping.getVal(VALIDATE)));
        setAliasReinitInterface(LgNamesContent.get(_util, _cust, _mapping.getVal(REINIT_EQ_INTERFACE)));
        setAliasReinitMethod(LgNamesContent.get(_util, _cust, _mapping.getVal(REINIT_EQ_METHOD)));
        setAliasDataBaseField(LgNamesContent.get(_util, _cust, _mapping.getVal(DATA_BASE_FIELD)));
        setAliasForms(LgNamesContent.get(_util, _cust, _mapping.getVal(FORMS)));
        setAliasSetForms(LgNamesContent.get(_util, _cust, _mapping.getVal(SET_FORMS)));
        setAliasGetForms(LgNamesContent.get(_util, _cust, _mapping.getVal(GET_FORMS)));
        setAliasLanguage(LgNamesContent.get(_util, _cust, _mapping.getVal(LANGUAGE)));
        setAliasSetLanguage(LgNamesContent.get(_util, _cust, _mapping.getVal(SET_LANGUAGE)));
        setAliasGetLanguage(LgNamesContent.get(_util, _cust, _mapping.getVal(GET_LANGUAGE)));
        setAliasScope(LgNamesContent.get(_util, _cust, _mapping.getVal(SCOPE)));
        setAliasSetScope(LgNamesContent.get(_util, _cust, _mapping.getVal(SET_SCOPE)));
        setAliasGetScope(LgNamesContent.get(_util, _cust, _mapping.getVal(GET_SCOPE)));
        setAliasSetDataBase(LgNamesContent.get(_util, _cust, _mapping.getVal(SET_DATA_BASE)));
        setAliasGetDataBase(LgNamesContent.get(_util, _cust, _mapping.getVal(GET_DATA_BASE)));
        setAliasBeforeDisplaying(LgNamesContent.get(_util, _cust, _mapping.getVal(BEFORE_DISPLAYING)));
        setAliasStringMapObject(LgNamesContent.get(_util, _cust, _mapping.getVal(STRING_MAP_OBJECT)));
        setAliasMessage(LgNamesContent.get(_util, _cust, _mapping.getVal(MESSAGE)));
        setAliasDocument(LgNamesContent.get(_util, _cust, _mapping.getVal(DOCUMENT)));
        setAliasDocumentAll(LgNamesContent.get(_util, _cust, _mapping.getVal(DOCUMENT_ALL)));
        setAliasNewMessage(LgNamesContent.get(_util, _cust, _mapping.getVal(NEW_MESSAGE)));
        setAliasMessageFormat(LgNamesContent.get(_util, _cust, _mapping.getVal(MESSAGE_FORMAT)));
        setAliasMessageGetArgs(LgNamesContent.get(_util, _cust, _mapping.getVal(MESSAGE_GET_ARGS)));
        setAliasMessageSetArgs(LgNamesContent.get(_util, _cust, _mapping.getVal(MESSAGE_SET_ARGS)));
        setAliasDocumentBeanArray(LgNamesContent.get(_util, _cust, _mapping.getVal(DOCUMENT_BEAN_ARRAY)));
        setAliasDocumentBeanName(LgNamesContent.get(_util, _cust, _mapping.getVal(DOCUMENT_BEAN_NAME)));
        setAliasDocumentBeanValue(LgNamesContent.get(_util, _cust, _mapping.getVal(DOCUMENT_BEAN_VALUE)));
        setAliasDocumentValidatorArray(LgNamesContent.get(_util, _cust, _mapping.getVal(DOCUMENT_VALIDATOR_ARRAY)));
        setAliasDocumentValidatorValue(LgNamesContent.get(_util, _cust, _mapping.getVal(DOCUMENT_VALIDATOR_VALUE)));
        setAliasDocumentReinitArray(LgNamesContent.get(_util, _cust, _mapping.getVal(DOCUMENT_REINIT_ARRAY)));
        setAliasDocumentReinitValue(LgNamesContent.get(_util, _cust, _mapping.getVal(DOCUMENT_REINIT_VALUE)));
    }

    public static StringMap<String> mapping() {
        StringMap<String> m_ = new StringMap<String>();
        m_.addEntry(BEAN,"Bean");
        m_.addEntry(MAP_KEYS,"MapKeys");
        m_.addEntry(MAP_VALUES,"MapValues");
        m_.addEntry(MAP_INDEX_OF_ENTRY,"MapIndexOfEntry");
        m_.addEntry(MAP_ADD_ENTRY,"MapAddEntry");
        m_.addEntry(MAP_GET_VALUE,"MapGetValue");
        m_.addEntry(MAP_FIRST_VALUE,"MapFirstValue");
        m_.addEntry(MAP_LAST_VALUE,"MapLastValue");
        m_.addEntry(MAP_SET_VALUE,"MapSetValue");
        m_.addEntry(MAP_PUT,"MapPut");
        m_.addEntry(MAP_CONTAINS,"MapContains");
        m_.addEntry(MAP_PUT_ALL,"MapPutAll");
        m_.addEntry(MAP_GET_VAL,"MapGetVal");
        m_.addEntry(MAP_REMOVE_KEY,"MapRemoveKey");
        m_.addEntry(MAP_GET_KEY,"MapGetKey");
        m_.addEntry(MAP_FIRST_KEY,"MapFirstKey");
        m_.addEntry(MAP_LAST_KEY,"MapLastKey");
        m_.addEntry(MAP_SET_KEY,"MapSetKey");
        m_.addEntry(MAP_SIZE,"MapSize");
        m_.addEntry(MAP_IS_EMPTY,"MapIsEmpty");
        m_.addEntry(MAP_CLEAR,"MapClear");
        m_.addEntry(VALIDATOR,"Validator");
        m_.addEntry(VALIDATE,"Validate");
        m_.addEntry(REINIT_EQ_INTERFACE,"ReinitInterface");
        m_.addEntry(REINIT_EQ_METHOD,"ReinitMethod");
        m_.addEntry(DATA_BASE_FIELD,"DataBaseField");
        m_.addEntry(FORMS,"Forms");
        m_.addEntry(SET_FORMS,"SetForms");
        m_.addEntry(GET_FORMS,"GetForms");
        m_.addEntry(LANGUAGE,"Language");
        m_.addEntry(SET_LANGUAGE,"SetLanguage");
        m_.addEntry(GET_LANGUAGE,"GetLanguage");
        m_.addEntry(SCOPE,"Scope");
        m_.addEntry(SET_SCOPE,"SetScope");
        m_.addEntry(GET_SCOPE,"GetScope");
        m_.addEntry(SET_DATA_BASE,"SetDataBase");
        m_.addEntry(GET_DATA_BASE,"GetDataBase");
        m_.addEntry(BEFORE_DISPLAYING,"BeforeDisplaying");
        m_.addEntry(STRING_MAP_OBJECT,"StringMapObject");
        m_.addEntry(MESSAGE,"Message");
        m_.addEntry(DOCUMENT,"Document");
        m_.addEntry(DOCUMENT_ALL,"DocumentAll");
        m_.addEntry(DOCUMENT_VALIDATOR_ARRAY,"DocumentValidatorArray");
        m_.addEntry(DOCUMENT_VALIDATOR_VALUE,"DocumentValidatorValue");
        m_.addEntry(DOCUMENT_REINIT_ARRAY,"DocumentReinitArray");
        m_.addEntry(DOCUMENT_REINIT_VALUE,"DocumentReinitValue");
        m_.addEntry(DOCUMENT_BEAN_ARRAY,"DocumentBeanArray");
        m_.addEntry(DOCUMENT_BEAN_VALUE,"DocumentBeanValue");
        m_.addEntry(DOCUMENT_BEAN_NAME,"DocumentBeanName");
        m_.addEntry(NEW_MESSAGE,"NewMessage");
        m_.addEntry(MESSAGE_FORMAT,"MessageFormat");
        m_.addEntry(MESSAGE_GET_ARGS,"MessageGetArgs");
        m_.addEntry(MESSAGE_SET_ARGS,"MessageSetArgs");
        BeanAliasParameters.mapping(m_);
        return m_;
    }
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> types_ = new StringMap<String>();
        types_.addEntry(_mapping.getVal(MESSAGE),getAliasMessage());
        types_.addEntry(_mapping.getVal(DOCUMENT),getAliasDocument());
        types_.addEntry(_mapping.getVal(VALIDATOR),getAliasValidator());
        types_.addEntry(_mapping.getVal(STRING_MAP_OBJECT),getAliasStringMapObject());
        types_.addEntry(_mapping.getVal(BEAN),getAliasBean());
        types_.addEntry(_mapping.getVal(REINIT_EQ_INTERFACE),getAliasReinitInterface());
        return types_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(AliasReflection _reflect, StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> methods_ = new StringMap<CustList<KeyValueMemberName>>();
        methods_.addEntry(getAliasMessage(),
                new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(NEW_MESSAGE),getAliasNewMessage()),
                        new KeyValueMemberName(_mapping.getVal(MESSAGE_FORMAT),getAliasMessageFormat()),
                        new KeyValueMemberName(_mapping.getVal(MESSAGE_GET_ARGS),getAliasMessageGetArgs()),
                        new KeyValueMemberName(_mapping.getVal(MESSAGE_SET_ARGS),getAliasMessageSetArgs())));
        CustList<KeyValueMemberName> lis_ = _reflect.listAnnot(_mapping);
        lis_.add(new KeyValueMemberName(_mapping.getVal(DOCUMENT_ALL),getAliasDocumentAll()));
        lis_.add(new KeyValueMemberName(_mapping.getVal(DOCUMENT_BEAN_ARRAY),getAliasDocumentBeanArray()));
        lis_.add(new KeyValueMemberName(_mapping.getVal(DOCUMENT_BEAN_NAME),getAliasDocumentBeanName()));
        lis_.add(new KeyValueMemberName(_mapping.getVal(DOCUMENT_BEAN_VALUE),getAliasDocumentBeanValue()));
        lis_.add(new KeyValueMemberName(_mapping.getVal(DOCUMENT_VALIDATOR_ARRAY),getAliasDocumentValidatorArray()));
        lis_.add(new KeyValueMemberName(_mapping.getVal(DOCUMENT_VALIDATOR_VALUE),getAliasDocumentValidatorValue()));
        lis_.add(new KeyValueMemberName(_mapping.getVal(DOCUMENT_REINIT_ARRAY),getAliasDocumentReinitArray()));
        lis_.add(new KeyValueMemberName(_mapping.getVal(DOCUMENT_REINIT_VALUE),getAliasDocumentReinitValue()));
        lis_.add(new KeyValueMemberName(_mapping.getVal(GET_DECLARED_LOCAL_TYPES),_reflect.getAliasGetDeclaredLocalTypes()));
        lis_.add(new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_TYPES),_reflect.getAliasGetDeclaredAnonymousTypes()));
        methods_.addEntry(getAliasDocument(), lis_);
        methods_.addEntry(getAliasValidator(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(VALIDATE),getAliasValidate())));
        methods_.addEntry(getAliasReinitInterface(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(REINIT_EQ_METHOD),getAliasReinitMethod())));
        methods_.addEntry(getAliasStringMapObject(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(MAP_GET_VAL),getAliasMapGetVal()),
                new KeyValueMemberName(_mapping.getVal(MAP_PUT),getAliasMapPut()),
                new KeyValueMemberName(_mapping.getVal(MAP_PUT_ALL),getAliasMapPutAll()),
                new KeyValueMemberName(_mapping.getVal(MAP_INDEX_OF_ENTRY),getAliasMapIndexOfEntry()),
                new KeyValueMemberName(_mapping.getVal(MAP_ADD_ENTRY),getAliasMapAddEntry()),
                new KeyValueMemberName(_mapping.getVal(MAP_CONTAINS),getAliasMapContains()),
                new KeyValueMemberName(_mapping.getVal(MAP_SIZE),getAliasMapSize()),
                new KeyValueMemberName(_mapping.getVal(MAP_IS_EMPTY),getAliasMapIsEmpty()),
                new KeyValueMemberName(_mapping.getVal(MAP_CLEAR),getAliasMapClear()),
                new KeyValueMemberName(_mapping.getVal(MAP_REMOVE_KEY),getAliasMapRemoveKey()),
                new KeyValueMemberName(_mapping.getVal(MAP_FIRST_VALUE),getAliasMapFirstValue()),
                new KeyValueMemberName(_mapping.getVal(MAP_LAST_VALUE),getAliasMapLastValue()),
                new KeyValueMemberName(_mapping.getVal(MAP_GET_VALUE),getAliasMapGetValue()),
                new KeyValueMemberName(_mapping.getVal(MAP_SET_VALUE),getAliasMapSetValue()),
                new KeyValueMemberName(_mapping.getVal(MAP_FIRST_KEY),getAliasMapFirstKey()),
                new KeyValueMemberName(_mapping.getVal(MAP_LAST_KEY),getAliasMapLastKey()),
                new KeyValueMemberName(_mapping.getVal(MAP_GET_KEY),getAliasMapGetKey()),
                new KeyValueMemberName(_mapping.getVal(MAP_SET_KEY),getAliasMapSetKey())
        ));
        methods_.addEntry(getAliasBean(),new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BEFORE_DISPLAYING),getAliasBeforeDisplaying()),
                new KeyValueMemberName(_mapping.getVal(GET_DATA_BASE),getAliasGetDataBase()),
                new KeyValueMemberName(_mapping.getVal(GET_LANGUAGE),getAliasGetLanguage()),
                new KeyValueMemberName(_mapping.getVal(GET_SCOPE),getAliasGetScope()),
                new KeyValueMemberName(_mapping.getVal(GET_FORMS),getAliasGetForms()),
                new KeyValueMemberName(_mapping.getVal(SET_DATA_BASE),getAliasSetDataBase()),
                new KeyValueMemberName(_mapping.getVal(SET_LANGUAGE),getAliasSetLanguage()),
                new KeyValueMemberName(_mapping.getVal(SET_SCOPE),getAliasSetScope()),
                new KeyValueMemberName(_mapping.getVal(SET_FORMS),getAliasSetForms())
        ));
        return methods_;
    }

    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> fields_ = new StringMap<CustList<KeyValueMemberName>>();
        fields_.addEntry(getAliasMessage(),
                new CustList<KeyValueMemberName>(
                        new KeyValueMemberName(_mapping.getVal(MAP_KEYS),getAliasMapKeys()),
                        new KeyValueMemberName(_mapping.getVal(MAP_VALUES),getAliasMapValues())));
        fields_.addEntry(getAliasBean(),
                new CustList<KeyValueMemberName>(
                        new KeyValueMemberName(_mapping.getVal(FORMS),getAliasForms()),
                        new KeyValueMemberName(_mapping.getVal(LANGUAGE),getAliasLanguage()),
                        new KeyValueMemberName(_mapping.getVal(DATA_BASE_FIELD),getAliasDataBaseField()),
                        new KeyValueMemberName(_mapping.getVal(SCOPE),getAliasScope())));
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

    public String getAliasReinitInterface() {
        return aliasReinitInterface;
    }

    public void setAliasReinitInterface(String _p) {
        this.aliasReinitInterface = _p;
    }

    public String getAliasReinitMethod() {
        return aliasReinitMethod;
    }

    public void setAliasReinitMethod(String _p) {
        this.aliasReinitMethod = _p;
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

    public String getAliasDocumentReinitArray() {
        return aliasDocumentReinitArray;
    }

    public void setAliasDocumentReinitArray(String _v) {
        this.aliasDocumentReinitArray = _v;
    }

    public String getAliasDocumentReinitValue() {
        return aliasDocumentReinitValue;
    }

    public void setAliasDocumentReinitValue(String _v) {
        this.aliasDocumentReinitValue = _v;
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
