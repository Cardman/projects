package code.formathtml.util;

import code.bean.translator.Translator;
import code.bean.validator.Validator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.NumberInfos;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.ElRenderUtil;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.structs.StdStruct;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;
import code.util.ints.MathFactory;

public abstract class BeanLgNames extends LgNames {

    static final String ALIAS_STRING_MAP_OBJECT = "code.util.StringMapObject";
    static final String BEAN = "code.bean.Bean";
    static final String ATTRIBUTE_TYPE = "type";
    static final String ATTRIBUTE_CLASS_NAME = "className";
    static final String CHECKBOX = "checkbox";

    static final String TEXT = "text";

    static final String RANGE = "range";

    static final String RADIO = "radio";

    static final String NUMBER = "number";
    static final String TEXT_AREA = "textarea";
    static final String SELECT_TAG = "select";
    static final String ATTRIBUTE_MULTIPLE = "multiple";
    private static final String FORMS = "forms";
    private static final String SET_FORMS = "setForms";
    private static final String GET_FORMS = "getForms";
    private static final String LANGUAGE = "language";
    private static final String SET_LANGUAGE = "setLanguage";
    private static final String GET_LANGUAGE = "getLanguage";
    private static final String SCOPE = "scope";
    private static final String SET_SCOPE = "setScope";
    private static final String GET_SCOPE = "getScope";
    private static final String DATA_BASE = "dataBase";
    private static final String SET_DATA_BASE = "setDataBase";
    private static final String GET_DATA_BASE = "getDataBase";
    private static final String BEFORE_DISPLAYING = "beforeDisplaying";


    private static final int DEFAULT_RADIX = 10;
    private static final long MULTMIN_RADIX_TEN = Long.MIN_VALUE / DEFAULT_RADIX;
    private static final long N_MULTMAX_RADIX_TEN = -Long.MAX_VALUE / DEFAULT_RADIX;
    private static final String ON = "on";
    private final String validator = "code.bean.validator.Validator";

    private final String valueChangedEvent = "code.formathtml.util.ValueChangeEvent";

    private String errorEl = "$badEl";
    private String aliasRate;
    private String aliasDataBase;
    private String aliasDataBaseField=DATA_BASE;

    private final String custEntry = "$custentry";
    private final String custEntries = "$custentries";
    private String aliasDisplayable;
    private String aliasDisplay;
    private String custList = "$custlist";
    private String custMap = "$custmap";
    private String aliasSimpleIteratorType = "code.util.SimpleItr";
    private String aliasSimpleIterableType = "code.util.ints.SimpleIterable";
    private String aliasCountable = "code.util.ints.Countable";

    private String aliasGet;
    private String aliasSize;
    private String aliasSimpleIterator;
    private String aliasForms=FORMS;
    private String aliasSetForms=SET_FORMS;
    private String aliasGetForms=GET_FORMS;
    private String aliasLanguage=LANGUAGE;
    private String aliasSetLanguage=SET_LANGUAGE;
    private String aliasGetLanguage=GET_LANGUAGE;
    private String aliasScope=SCOPE;
    private String aliasSetScope=SET_SCOPE;
    private String aliasGetScope=GET_SCOPE;
    private String aliasSetDataBase=SET_DATA_BASE;
    private String aliasGetDataBase=GET_DATA_BASE;
    private String aliasBeforeDisplaying=BEFORE_DISPLAYING;
    private String aliasBean = BEAN;
    private String aliasStringMapObject = ALIAS_STRING_MAP_OBJECT;

    private StringMap<String> iterables = new StringMap<String>();

    public static Double parseDouble(String _nb) {
        NumberInfos infos_ = NumParsers.trySplitDouble(_nb);
        if (infos_ == null) {
            return null;
        }
        return NumParsers.parseDouble(infos_);
    }

    public static Float parseFloat(String _nb) {
        NumberInfos infos_ = NumParsers.trySplitDouble(_nb);
        if (infos_ == null) {
            return null;
        }
        double double_ = NumParsers.parseDouble(infos_);
        double abs_ = Math.abs(double_);
        if (abs_ > Float.MAX_VALUE) {
            return null;
        }
        return (float)double_;
    }

    public static Byte parseByte(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_ < Byte.MIN_VALUE) {
            return null;
        }
        if (int_ > Byte.MAX_VALUE) {
            return null;
        }
        return int_.byteValue();
    }

    public static Short parseShort(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_ < Short.MIN_VALUE) {
            return null;
        }
        if (int_ > Short.MAX_VALUE) {
            return null;
        }
        return int_.shortValue();
    }

    public static Integer parseInt(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_ < Integer.MIN_VALUE) {
            return null;
        }
        if (int_ > Integer.MAX_VALUE) {
            return null;
        }
        return int_.intValue();
    }

    public static Long parseLong(String _string) {
        if (_string == null) {
            return null;
        }
        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        long limit_;
        long multmin_;
        int digit_;

        if (max_ > 0) {
            if (_string.charAt(0) == '-') {
                negative_ = true;
                limit_ = Long.MIN_VALUE;
                i_++;
            } else {
                limit_ = -Long.MAX_VALUE;
            }
            if (negative_) {
                multmin_ = MULTMIN_RADIX_TEN;
            } else {
                multmin_ = N_MULTMAX_RADIX_TEN;
            }
            if (i_ < max_) {
                char ch_ = _string.charAt(i_);
                i_++;
                if (ch_ < '0' || ch_ > '9') {
                    return null;
                }
                digit_ = ch_ - '0';
                result_ = -digit_;
            }
            while (i_ < max_) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                char ch_ = _string.charAt(i_);
                i_++;
                if (ch_ < '0' || ch_ > '9') {
                    return null;
                }
                digit_ = ch_ - '0';
                if (result_ < multmin_) {
                    return null;
                }
                result_ *= 10;
                if (result_ < limit_ + digit_) {
                    return null;
                }
                result_ -= digit_;
            }
        } else {
            return null;
        }
        if (negative_) {
            if (i_ > 1) {
                return result_;
            }
            return null;
        }
        return -result_;
    }

    public StringMap<String> getIterables() {
        return iterables;
    }

    public abstract void buildIterables(Configuration _context);

    public abstract String getIteratorVar();
    public abstract String getHasNextVar();
    public abstract String getNextVar();
    public abstract CustList<RendDynOperationNode> getExpsIterator();
    public abstract CustList<RendDynOperationNode> getExpsHasNext();
    public abstract CustList<RendDynOperationNode> getExpsNext();

    public abstract void beforeDisplaying(Struct _arg,Configuration _cont);
    public abstract String processString(Argument _arg,Configuration _cont);

    public abstract String getIteratorTableVarCust();

    public abstract String getHasNextPairVarCust();

    public abstract String getNextPairVarCust();

    public abstract String getFirstVarCust();

    public abstract String getSecondVarCust();

    public abstract CustList<RendDynOperationNode> getExpsIteratorTableCust();

    public abstract CustList<RendDynOperationNode> getExpsHasNextPairCust();

    public abstract CustList<RendDynOperationNode> getExpsNextPairCust();

    public abstract CustList<RendDynOperationNode> getExpsFirstCust();

    public abstract CustList<RendDynOperationNode> getExpsSecondCust();

    public abstract String getStringKey(Configuration _conf, Struct _instance);

    public abstract void initBeans(Configuration _conf,String _language,Struct _db);
    public String getInputClass(Element _write, Configuration _conf) {
        String type_ = _write.getAttribute(ATTRIBUTE_TYPE);
        String class_ = _write.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_CLASS_NAME));
        if (StringList.quickEq(type_,NUMBER)) {
            class_= _conf.getStandards().getAliasLong();
        }
        if (StringList.quickEq(type_,RANGE)) {
            class_= _conf.getStandards().getAliasLong();
        }
        if (StringList.quickEq(type_,RADIO)) {
            class_= _conf.getStandards().getAliasLong();
        }
        if (StringList.quickEq(type_,TEXT)) {
            class_= _conf.getStandards().getAliasString();
        }
        if (StringList.quickEq(type_,CHECKBOX)) {
            class_= _conf.getStandards().getAliasBoolean();
        }
        if (StringList.quickEq(_write.getTagName(), SELECT_TAG)) {
            if (_write.hasAttribute(ATTRIBUTE_MULTIPLE)) {
                class_ = _conf.getAdvStandards().getCustList();
            } else {
                class_ = _conf.getStandards().getAliasString();
            }
        }
        if (StringList.quickEq(_write.getTagName(), TEXT_AREA)) {
            class_ = _conf.getStandards().getAliasString();
        }
        return class_;
    }
    public ResultErrorStd convert(StringList _values, NodeContainer _container, Configuration _conf) {
        String beanName_ = _container.getBeanName();
        Struct bean_ = _conf.getBuiltBeans().getVal(beanName_);
        if (bean_ != null) {
            CustList<RendDynOperationNode> ops_ = _container.getOpsConvert();
            if (!ops_.isEmpty()) {
                String varNameConvert_ = _container.getVarNameConvert();
                LocalVariable lv_ = new LocalVariable();
                BeanLgNames stds_ = _conf.getAdvStandards();
                if (_container.isArrayConverter()) {
                    int len_ = _values.size();
                    ArrayStruct arr_ = new ArrayStruct(new Struct[len_],PrimitiveTypeUtil.getPrettyArrayType(getAliasString()));
                    for (int i = 0; i < len_; i++) {
                        arr_.getInstance()[i] = new StringStruct(_values.get(i));
                    }
                    lv_.setClassName(stds_.getStructClassName(arr_, _conf.getContext()));
                    lv_.setStruct(arr_);
                } else {
                    lv_.setClassName(getAliasString());
                    if (!_values.isEmpty()) {
                        lv_.setStruct(new StringStruct(_values.first()));
                    } else {
                        lv_.setStruct(NullStruct.NULL_VALUE);
                    }
                }
                _conf.getLastPage().putLocalVar(varNameConvert_, lv_);
                _conf.getLastPage().setGlobalArgumentStruct(bean_, _conf);
                Argument res_ = ElRenderUtil.calculateReuse(ops_, _conf);
                ResultErrorStd out_ = new ResultErrorStd();
                if (_conf.getContext().hasExceptionOrFailInit()) {
                    return out_;
                }
                out_.setResult(res_.getStruct());
                return out_;
            }
        }
        Struct obj_ = _container.getTypedStruct();
        String className_ = _container.getNodeInformation().getInputClass();
        if (obj_ != NullStruct.NULL_VALUE) {
            ContextEl context_ = _conf.getContext();
            className_ = context_.getStandards().getStructClassName(obj_, context_);
        }
        ResultErrorStd out_ = getStructToBeValidated(_values, className_, _conf.getContext());
        if (out_.getError() != null) {
            String err_ = out_.getError();
            _conf.getContext().setException(new ErrorStruct(_conf,err_));
        }
        return out_;
    }
    public ResultErrorStd getStructToBeValidated(StringList _values, String _className, ContextEl _context) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_className, getAliasBoolean()) || StringList.quickEq(_className, getAliasPrimBoolean())) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(new BooleanStruct(StringList.quickEq(_values.first(),ON)));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasString())) {
            if (_values.isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(new StringStruct(_values.first()));
            return res_;
        }
        if (StringList.quickEq(_className, getCustList())) {
            res_.setResult(new StdStruct(_values, _className));
            return res_;
        }
        if (PrimitiveTypeUtil.isPrimitiveOrWrapper(_className,_context)) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (StringList.quickEq(_className, getAliasDouble()) || StringList.quickEq(_className, getAliasPrimDouble())) {
            Double val_ = parseDouble(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new DoubleStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasFloat()) || StringList.quickEq(_className, getAliasPrimFloat())) {
            Float val_ = parseFloat(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new FloatStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasLong()) || StringList.quickEq(_className, getAliasPrimLong())) {
            Long val_ = parseLong(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new LongStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasInteger()) || StringList.quickEq(_className, getAliasPrimInteger())) {
            Integer val_ = parseInt(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new IntStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasShort()) || StringList.quickEq(_className, getAliasPrimShort())) {
            Short val_ = parseShort(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new ShortStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasByte()) || StringList.quickEq(_className, getAliasPrimByte())) {
            Byte val_ = parseByte(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new ByteStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasCharacter()) || StringList.quickEq(_className, getAliasPrimChar())) {
            res_.setResult(new CharStruct(_values.first().trim().charAt(0)));
            return res_;
        }
        return getOtherStructToBeValidated(_values, _className, _context);
    }

    public void setBeanForms(Configuration _conf, Struct _mainBean,
                                     Node _node, boolean _keepField, String _beanName) {
        if (_mainBean == null) {
            return;
        }
        Struct bean_ = _conf.getBuiltBeans().getVal(_beanName);
        if (bean_ == null) {
            return;
        }
        ImportingPage ip_ = _conf.getLastPage();
        String prefix_ = ip_.getPrefix();
        Argument forms_ = getForms(bean_, _conf);
        if (_conf.getContext().getException() != null) {
            return;
        }
        Argument formsMap_ = getForms(_mainBean,_conf);
        if (_conf.getContext().getException() != null) {
            return;
        }
        if (_keepField) {
            for (Element f_: _node.getChildElements()) {
                if (!StringList.quickEq(f_.getTagName(),StringList.concat(prefix_,"form"))) {
                    continue;
                }
                String name_ = f_.getAttribute("form");
                forwardMap(formsMap_.getStruct(),forms_.getStruct(),new StringStruct(name_),_conf);
                if (_conf.getContext().getException() != null) {
                    return;
                }
            }
        } else {
            //add option for copying forms (default copy)
            putAllMap(forms_.getStruct(),formsMap_.getStruct(),_conf);
        }
    }


    public abstract void forwardDataBase(Struct _bean, Struct _to, Configuration _conf);
    public abstract Argument getForms(Struct _bean, Configuration _conf);
    public abstract void setForms(Struct _bean, Struct _map, Configuration _conf);
    public abstract void forwardMap(Struct _map, Struct _to, Struct _key, Configuration _conf);
    public abstract void putAllMap(Struct _map, Struct _other, Configuration _conf);
    public Validator buildValidator(Element _element) {
        return null;
    }
    public Translator buildTranslator(Element _element) {
        return null;
    }
    public MathFactory buildMathFactory(Element _element) {
        return null;
    }
    public ResultErrorStd getOtherStructToBeValidated(StringList _values, String _className, ContextEl _context) {
        return new ResultErrorStd();
    }
    public String getStdBeanStructClassName(Object _struct, ContextEl _context) {
        return getAliasObject();
    }
    public String getOtherBeanStructClassName(Object _struct, ContextEl _context) {
        return getAliasObject();
    }
    public ResultErrorStd setElementAtIndex(Struct _struct, int _index, boolean _key, Struct _element, ContextEl _context) {
        ResultErrorStd res_ = new ResultErrorStd();
        res_.setResult(NullStruct.NULL_VALUE);
        if (_struct instanceof ArrayStruct) {
            ArrayStruct a_ = (ArrayStruct) _struct;
            a_.getInstance()[_index] = _element;
            return res_;
        }
        return setOtherElementAtIndex(_struct, _index, _key, _element, _context);
    }
    public ResultErrorStd setOtherElementAtIndex(Struct _struct, int _index, boolean _key, Struct _element, ContextEl _context) {
        return new ResultErrorStd();
    }
    public StringList getDefaultValues(ContextEl _cont, String _className, String _value) {
        return new StringList();
    }
    public ResultErrorStd getName(ContextEl _cont, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance instanceof StringStruct) {
            res_.setResult(_instance);
            return res_;
        }
        if (_instance instanceof NumberStruct) {
            res_.setResult(new StringStruct(Long.toString(((NumberStruct)_instance).longStruct())));
            return res_;
        }
        return getOtherName(_cont, _instance);
    }
    public ResultErrorStd getOtherName(ContextEl _cont, Struct _instance) {
        return new ResultErrorStd();
    }


    public String getAliasRate() {
        return aliasRate;
    }

    public void setAliasRate(String _aliasRate) {
        aliasRate = _aliasRate;
    }

    public String getAliasDataBase() {
        return aliasDataBase;
    }

    public void setAliasDataBase(String _aliasDataBase) {
        aliasDataBase = _aliasDataBase;
    }

    public String getCustList() {
        return custList;
    }
    public void setCustList(String _custList) {
        custList = _custList;
    }
    public String getCustMap() {
        return custMap;
    }
    public void setCustMap(String _custMap) {
        custMap = _custMap;
    }
    public String getCustEntries() {
        return custEntries;
    }
    public String getValueChangedEvent() {
        return valueChangedEvent;
    }
    public String getBean() {
        return BEAN;
    }
    public String getCustEntry() {
        return custEntry;
    }
    public String getValidator() {
        return validator;
    }
    public String getErrorEl() {
        return errorEl;
    }
    public void setErrorEl(String _errorEl) {
        errorEl = _errorEl;
    }
    public String getAliasSimpleIteratorType() {
        return aliasSimpleIteratorType;
    }
    public String getAliasSimpleIterableType() {
        return aliasSimpleIterableType;
    }
    public String getAliasCountable() {
        return aliasCountable;
    }

    public String getAliasDisplayable() {
        return aliasDisplayable;
    }
    public void setAliasDisplayable(String _aliasDisplayable) {
        aliasDisplayable = _aliasDisplayable;
    }
    public String getAliasDisplay() {
        return aliasDisplay;
    }
    public void setAliasDisplay(String _aliasDisplay) {
        aliasDisplay = _aliasDisplay;
    }
    public String getAliasGet() {
        return aliasGet;
    }
    public void setAliasGet(String _aliasGet) {
        aliasGet = _aliasGet;
    }
    public String getAliasSize() {
        return aliasSize;
    }
    public void setAliasSize(String _aliasSize) {
        aliasSize = _aliasSize;
    }
    public String getAliasSimpleIterator() {
        return aliasSimpleIterator;
    }
    public void setAliasSimpleIterator(String _aliasSimpleIterator) {
        aliasSimpleIterator = _aliasSimpleIterator;
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
}
