package code.formathtml;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import code.bean.Bean;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.expressionlanguage.exceptions.BadIndexException;
import code.expressionlanguage.exceptions.DivideZeroException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.DynamicNumberFormatException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.IndirectException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.exceptions.NegativeSizeException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.exceptions.WrapperException;
import code.expressionlanguage.methods.ProcessXmlMethod;
import code.expressionlanguage.methods.exceptions.AlreadyDefinedVarException;
import code.expressionlanguage.methods.exceptions.BadCaseException;
import code.expressionlanguage.methods.exceptions.BadCatchException;
import code.expressionlanguage.methods.exceptions.BadDefaultException;
import code.expressionlanguage.methods.exceptions.BadElseException;
import code.expressionlanguage.methods.exceptions.BadElseIfException;
import code.expressionlanguage.methods.exceptions.BadLoopException;
import code.expressionlanguage.methods.exceptions.BadSwitchException;
import code.expressionlanguage.methods.exceptions.BadTagBreakException;
import code.expressionlanguage.methods.exceptions.BadTagContinueException;
import code.expressionlanguage.methods.exceptions.BadTryException;
import code.expressionlanguage.methods.exceptions.BadVariableNameException;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exceptions.BadEnumeratingException;
import code.formathtml.exceptions.BadFilePropertiesException;
import code.formathtml.exceptions.BadReferenceEqualsException;
import code.formathtml.exceptions.BadSelectException;
import code.formathtml.exceptions.CharacterFormatException;
import code.formathtml.exceptions.GettingKeysException;
import code.formathtml.exceptions.InexistingTranslatorException;
import code.formathtml.exceptions.KeyValueException;
import code.formathtml.exceptions.MessageKeyNotFoundException;
import code.formathtml.exceptions.NoSuchResourceException;
import code.formathtml.exceptions.NotCastableException;
import code.formathtml.exceptions.NotPrimitivableException;
import code.formathtml.exceptions.RenderingException;
import code.formathtml.exceptions.SettingArrayException;
import code.formathtml.util.ActionNext;
import code.formathtml.util.BeanElement;
import code.formathtml.util.BlockHtml;
import code.formathtml.util.BreakableHtmlStack;
import code.formathtml.util.FormInputCoords;
import code.formathtml.util.IfHtmlStack;
import code.formathtml.util.IndexesFormInput;
import code.formathtml.util.LoopHtmlStack;
import code.formathtml.util.NodeAction;
import code.formathtml.util.NodeAttribute;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.formathtml.util.ParentElement;
import code.formathtml.util.ReadWriteHtml;
import code.formathtml.util.SwitchHtmlStack;
import code.formathtml.util.TryHtmlStack;
import code.formathtml.util.VariableInformation;
import code.images.ConverterBufferedImage;
import code.serialize.ConverterMethod;
import code.serialize.SerializeXmlObject;
import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.NoSuchDeclaredFieldException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstClasses;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.util.ints.Listable;
import code.xml.AttributePart;
import code.xml.XmlParser;
import code.xml.exceptions.XmlParseException;

final class FormatHtml {

    private static final char RIGHT_ARR = ']';
    private static final char LEFT_ARR = '[';
    private static final String SUFFIX_INT = "i";
    static final String XMLNS = "xmlns";
    static final String NAMESPACE_URI = "javahtml";
    static final String ATTRIBUTE_CLASS_NAME = "className";
    static final String ATTRIBUTE_INDEX_CLASS_NAME = "indexclassName";
    static final String EMPTY_STRING = "";
    static final String RETURN_LINE = "\n";
    static final String RETURN_TAB = "\n\t";

    static final String NULL_METHOD = "goto";
    static final String SPACE = " ";
    static final String VAR_METHOD = "varMethod";
    static final String BEAN_ATTRIBUTE = "bean";
    static final String ATTRIBUTE_VALUE_CHANGE_EVENT = "valueChangeEvent";
    static final String COMMA = ",";
    static final String DOT = ".";
    static final String GET_KEY = "!key";

    static final String TMP_VAR = "tmpvar";
    private static final String SEP_PREFIX = ":";
    private static final String GET_ENTRY = "!";
    private static final String ATTRIBUTE_SRC = "src";
    private static final String ATTRIBUTE_ENCODE_IMG = "wrap";
    private static final String TAG_IMG = "img";
    private static final String TAG_HEAD = "head";
    private static final String STYLESHEET = "stylesheet";
    private static final String TAG_STYLE = "style";
    private static final String ATTRIBUTE_REL = "rel";
    private static final String SCRIPT = "script";
    private static final String TAG_LINK = "link";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String COMMA_RIGHT_PAR = ",)";
    private static final String LEFT_PAR_COMMA = "(,";
    private static final String DOUBLE_COMMA = ",,";
    private static final char RIGHT_PAR_CHAR = ')';
    private static final String RIGHT_PAR = EMPTY_STRING+RIGHT_PAR_CHAR;
    private static final String TAG_OPTION = "option";
    private static final String SELECTED = "selected";
    private static final String ATTRIBUTE_VALIDATOR = "validator";
    private static final String ATTRIBUTE_UPDATE = "update";
    private static final String TR_TAG = "tr";
    private static final String TR_END = "</tr>";
    private static final String TR_BEGIN = "<tr>";
    private static final String RIGHT_END_TAG = "/>";
    private static final String TAG_FORM = "form";
    private static final String TAG_A = "a";
    private static final String TAG_HTML = "html";
    private static final String SUBMIT_TYPE = "submit";
    private static final String TAG_BODY = "body";
    private static final char LEFT_PAR_CHAR = '(';
    private static final String LEFT_PAR = EMPTY_STRING+LEFT_PAR_CHAR;
    private static final String TAG_PARAM = "param";
    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_TYPE = "type";
    private static final String SCRIPT_TYPE = "text/javascript";
    private static final String CHECKBOX = "checkbox";

    private static final String TEXT = "text";

    private static final String RANGE = "range";

    private static final String RADIO = "radio";

    private static final String NUMBER = "number";
    private static final String ATTRIBUTE_LIST = "list";
    private static final String ATTRIBUTE_MAP = "map";
    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_GROUP_ID = "groupId";
    private static final String ATTRIBUTE_MULTIPLE = "multiple";
    private static final String ATTRIBUTE_KEY = "key";
    private static final String ATTRIBUTE_VAR = "var";
    private static final String ATTRIBUTE_HREF = "href";
    private static final String ATTRIBUTE_COMMAND = "command";
    private static final String ATTRIBUTE_ACTION = "action";
    private static final String SELECT_TAG = "select";
    private static final String CHECKED = "checked";
    private static final String INPUT_TAG = "input";
    private static final String TEXT_AREA = "textarea";
    private static final String SPAN_TAG = "span";
    private static final String ATTRIBUTE_FOR = "for";
    private static final String ATTRIBUTE_VAR_VALUE = "varValue";
    private static final String GET_LOC_VAR = ";.";
    private static final String ATTRIBUTE_ESCAPED = "escaped";
    private static final String GET_VALUE = "!value";
    private static final String GET_ATTRIBUTE = ";";
    private static final String CALL_METHOD = "$";
    private static final String DEFAULT_ATTRIBUTE = "default";
    private static final String NO_PARAM_METHOD = "()";
    private static final String ATTRIBUTE_VALUE_SUBMIT = "message";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";

    private static final String ATTRIBUTE_METHOD = "method";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_FORM = "form";
    private static final String PAGE_ATTRIBUTE = "page";
    private static final String KEEPFIELD_ATTRIBUTE = "keepfields";
    private static final String EXPRESSION_ATTRIBUTE = "expression";
    private static final String ARRAY_INDEX_ATTRIBUTE = "arrayindex";
    private static final String ARRAY_ELEMENT_ATTRIBUTE = "arrayelement";
    private static final String KEY_CLASS_NAME_ATTRIBUTE = "keyClassName";
    private static final String VAR_CLASS_NAME_ATTRIBUTE = "varClassName";
    private static final String ATTRIBUTE_FROM = "from";
    private static final String ATTRIBUTE_TO = "to";
    private static final String ATTRIBUTE_EQ = "eq";
    private static final String ATTRIBUTE_STEP = "step";
    private static final String LT_END_TAG = "</";
    private static final char GT_TAG = '>';
    private static final char LT_BEGIN_TAG = '<';

    private static final String FOR_BLOCK_TAG = "for";
    private static final String WHILE_BLOCK_TAG = "while";
    private static final String ELSE_BLOCK_TAG = "else";
    private static final String MESSAGE_BLOCK_TAG = "message";
    private static final String IMPORT_BLOCK_TAG = "import";
    private static final String PACKAGE_BLOCK_TAG = "package";
    private static final String CLASS_BLOCK_TAG = "class";
    private static final String FIELD_BLOCK_TAG = "field";
    private static final String PARAM_BLOCK_TAG = "param";
    private static final String SET_RETURNED_VALUE_BLOCK_TAG = "setreturnedvalue";
    private static final String TMP_BLOCK_TAG = "tmp";
    private static final String SUBMIT_BLOCK_TAG = "submit";
    private static final String FORM_BLOCK_TAG = "form";
    private static final String TR_BEGIN_BLOCK_TAG = "tr_begin";
    private static final String TR_END_BLOCK_TAG = "tr_end";
    private static final String UNSET_BLOCK_TAG = "unset";
    private static final String SET_BLOCK_TAG = "set";
    private static final String CONTINUE_TAG = "continue";
    private static final String BREAK_TAG = "break";
    private static final String RETURN_TAG = "return";
    private static final String EXIT_TAG = "exit";
    private static final String TRY_TAG = "try";
    private static final String CATCH_TAG = "catch";
    private static final String THROW_TAG = "throw";
    private static final String TAG_FINALLY = "finally";
    private static final String TAG_SWITCH = "switch";
    private static final String TAG_CASE = "case";
    private static final String TAG_DEFAULT = "default";
    private static final String TAG_DO = "do";
    private static final char SEP_ENUMS = ',';
    private static final String NUMBER_EXPRESSION = "mathexpr";
    private static final String IS_STRING_CONST_ATTRIBUTE = "isstringconst";
    private static final String IS_CHAR_CONST_ATTRIBUTE = "ischarconst";
    private static final String IS_BOOL_CONST_ATTRIBUTE = "isboolconst";
    private static final String EVALUATE_BOOLEAN = "isbool";
    private static final String INSTANTIATE_PREFIX = "new.";
    private static final char ESCAPED = '\\';
    private static final char MATH_INTERPRET = '`';
    private static final String NUMBER_FORM = "n-f";
    private static final String NUMBER_ANCHOR = "n-a";
    private static final String NUMBER_INPUT = "n-i";
    private static final String INTERPRET = "interpret";
    private static final String END_TEMP = ">";

    private static final String BEG_TEMP = "<";
    private static final char END_ESCAPED = ';';

    private static final char ENCODED = '&';
    private static final char EQUALS = '=';
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private static final String NEXT_COMMENT = "--";
    private static final String END_COMMENT = "--"+GT_TAG;
    private FormatHtml() {
    }

    private static String addToRoot(Configuration _conf, String _prefix, String _body) {
        if (!_prefix.isEmpty()) {
            return LT_BEGIN_TAG+TMP_BLOCK_TAG+SPACE+XMLNS+SEP_PREFIX+_prefix.substring(CustList.FIRST_INDEX, _prefix.length()-1)+EQUALS+QUOT+_conf.getNamespaceUri()+QUOT+GT_TAG+_body+LT_END_TAG+TMP_BLOCK_TAG+GT_TAG;
        }
        return LT_BEGIN_TAG+TMP_BLOCK_TAG+GT_TAG+_body+LT_END_TAG+TMP_BLOCK_TAG+GT_TAG;
    }

    /**@throws XmlParseException*/
    static String getCurrentBean(String _htmlText) {
        String htmlText_ = _htmlText;
        try {
            Document doc_ = XmlParser.parseSaxHtml(htmlText_);
            Element root_ = doc_.getDocumentElement();
            return root_.getAttribute(BEAN_ATTRIBUTE);
        } catch (RuntimeException _0) {
            throw new XmlParseException(htmlText_);
        }
    }

    static String processImports(String _htmlText, Configuration _conf, String _loc, StringMap<String> _files, String... _resourcesFolder) {
        _conf.getHtmlPage().getSelects().clear();
        _conf.clearPages();
        String htmlText_ = processHtmlJava(_htmlText, _conf, _loc, _files, _resourcesFolder);
        if (htmlText_ == null) {
            return null;
        }
        Document doc_ = XmlParser.parseSaxHtml(htmlText_, false, true);
        _conf.setDocument(doc_);
        _conf.clearPages();
        return XmlParser.toHtml(doc_);
    }

    static BeanElement tryToOpenDocument(Element _importElement,
            ImportingPage _ip,
            Configuration _conf, StringMap<String> _files, String... _resourcesFolder) {
        String pageName_ = _importElement.getAttribute(PAGE_ATTRIBUTE);
        if (pageName_.isEmpty()) {
            return null;
        }
        pageName_ = ExtractObject.formatNumVariables(pageName_, _conf, _ip, _files);
        String subHtml_ = ExtractFromResources.loadPage(_conf, _files, pageName_,_resourcesFolder);
        Document doc_;
        try {
            doc_ = XmlParser.parseSaxHtml(subHtml_, false, true);
        } catch (XmlParseException _0) {
            throw new XmlParseException(_0.getMessage()+RETURN_LINE+_conf.joinPages());
        }
        NodeList bodies_ = doc_.getElementsByTagName(TAG_BODY);
        if (bodies_.getLength() != CustList.ONE_ELEMENT) {
            return null;
        }
        Node n_ = bodies_.item(CustList.FIRST_INDEX);
        if (!n_.hasChildNodes()) {
            return null;
        }
        BeanElement b_ = new BeanElement();
        b_.setUrl(pageName_);
        b_.setRoot((Element) n_);
        b_.setHtml(subHtml_);
        String prefix_ = getPrefix(_conf, doc_);
        b_.setPrefix(prefix_);
        b_.setBeanName(doc_.getDocumentElement().getAttribute(prefix_+BEAN_ATTRIBUTE));
        return b_;
    }

    private static void setBeanForms(Configuration _conf, Bean _mainBean,
            Node _node, boolean _keepField, String _beanName) {
        try {
            Bean bean_ = _conf.getBeans().getVal(_beanName);
            ImportingPage ip_ = _conf.getLastPage();
            String prefix_ = ip_.getPrefix();
            if (_keepField) {
                for (Element f_: XmlParser.childrenElements(_node)) {
                    if (!StringList.quickEq(f_.getNodeName(),prefix_+FORM_BLOCK_TAG)) {
                        continue;
                    }
                    String name_ = f_.getAttribute(ATTRIBUTE_FORM);
                    bean_.getForms().put(name_, _mainBean.getForms().getVal(name_));
                }
            } else {
                //add option for copying forms (default copy)
                bean_.getForms().putAllMap(_mainBean.getForms());
            }
        } catch (Throwable _0) {
        }
    }

    private static void setFieldsImportBean(Configuration _conf, Node _node,
            String _beanName) {
        Bean bean_ = getBean(_conf, _beanName);
        ImportingPage ip_ = _conf.getLastPage();
        String prefix_ = ip_.getPrefix();
        for (Element n: XmlParser.childrenElements(_node)) {
            if (!StringList.quickEq(n.getNodeName(),prefix_+PACKAGE_BLOCK_TAG)) {
                continue;
            }
            String package_ = n.getAttribute(ATTRIBUTE_NAME);
            for (Element nTwo_: XmlParser.childrenElements(n)) {
                if (!StringList.quickEq(nTwo_.getNodeName(),prefix_+CLASS_BLOCK_TAG)) {
                    continue;
                }
                String className_ = nTwo_.getAttribute(ATTRIBUTE_NAME);
                ip_.setProcessingNode(_node);
                ip_.setProcessingAttribute(EMPTY_STRING);
                ip_.setOffset(0);
                ip_.setLookForAttrValue(false);
                String searchedClass_ = package_+DOT+className_;
                if (bean_ == null) {
                    throw new NullObjectException(_conf.joinPages());
                }
                ip_.setProcessingNode(nTwo_);
                ip_.setProcessingAttribute(ATTRIBUTE_NAME);
                ip_.setOffset(0);
                ip_.setLookForAttrValue(true);
                boolean found_ = false;
                Class<?> class_ = bean_.getClass();
                while (true) {
                    if (class_.getName().equalsIgnoreCase(searchedClass_)) {
                        found_ = true;
                        break;
                    }
                    if (class_ == Object.class) {
                        break;
                    }
                    class_ = class_.getSuperclass();
                }
                if (!found_) {
                    throw new RuntimeClassNotFoundException(searchedClass_+RETURN_LINE+_conf.joinPages());
                }
                for (Element nThree_: XmlParser.childrenElements(nTwo_)) {
                    if (!StringList.quickEq(nThree_.getNodeName(),prefix_+FIELD_BLOCK_TAG)) {
                        continue;
                    }
                    if (nThree_.hasAttribute(ATTRIBUTE_METHOD)) {
                        String methodName_ =nThree_.getAttribute(ATTRIBUTE_METHOD);
                        String fieldValue_ = nThree_.getAttribute(ATTRIBUTE_VALUE);
                        String classNameParam_ = nThree_.getAttribute(ATTRIBUTE_CLASS_NAME);
                        ip_.setProcessingNode(nThree_);
                        ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
                        ip_.setOffset(0);
                        ip_.setLookForAttrValue(true);
                        Argument argt_ = ElUtil.processEl(fieldValue_, 0, _conf.toContextEl());
                        ip_.setProcessingNode(nThree_);
                        ip_.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                        ip_.setOffset(0);
                        ip_.setLookForAttrValue(true);
                        ExtractObject.classForName(_conf, 0, classNameParam_);
                        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameParam_, argt_.getObjectClassName(), _conf.toContextEl().getClasses())) {
                            throw new DynamicCastClassException(argt_.getObjectClassName()+RETURN_LINE+classNameParam_+RETURN_LINE+_conf.joinPages());
                        }
                        ip_.setProcessingNode(nThree_);
                        ip_.setProcessingAttribute(ATTRIBUTE_METHOD);
                        ip_.setOffset(0);
                        ip_.setLookForAttrValue(true);
                        Argument argument_ = new Argument();
                        argument_.setObject(bean_);
                        ip_.setGlobalArgument(argument_);
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setClassName(ConstClasses.resolve(classNameParam_));
                        lv_.setStruct(argt_.getStruct());
                        String nameVar_ = ip_.getNextTempVar();
                        ip_.getLocalVars().put(nameVar_, lv_);
                        ElUtil.processEl(methodName_+LEFT_PAR+nameVar_+GET_LOC_VAR+RIGHT_PAR, 0, _conf.toContextEl());
                        ip_.getLocalVars().removeKey(nameVar_);
                        continue;
                    }
                    ip_.setProcessingNode(nThree_);
                    ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
                    ip_.setOffset(0);
                    ip_.setLookForAttrValue(true);
                    String fieldValue_ = nThree_.getAttribute(ATTRIBUTE_VALUE);
                    Argument argt_ = ElUtil.processEl(fieldValue_, 0, _conf.toContextEl());
                    Object arg_ = argt_.getObject();
                    ip_.setProcessingNode(nThree_);
                    ip_.setProcessingAttribute(ATTRIBUTE_NAME);
                    ip_.setOffset(0);
                    ip_.setLookForAttrValue(true);
                    String fieldName_ = nThree_.getAttribute(ATTRIBUTE_NAME);
                    Field f_;
                    try {
                        f_ = SerializeXmlObject.getDeclaredField(class_, fieldName_);
                    } catch (NoSuchDeclaredFieldException _0) {
                        throw new NoSuchDeclaredFieldException(_0.getMessage()+RETURN_LINE+_conf.joinPages());
                    }
                    ContextEl context_ = _conf.toContextEl();
                    context_.getAccessValue().setAccess(f_, context_);
                    try {
                        ConverterMethod.setField(f_, bean_, arg_);
                    } catch (Throwable _0) {
                        throw new BadAccessException(_0, fieldName_+RETURN_LINE+_conf.joinPages());
                    }
                }
            }
        }
    }

    private static StringMap<LocalVariable> newParametersBeforeRender(Configuration _conf, Node _node) {
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LocalVariable> parameters_ = new StringMap<LocalVariable>();
        String prefix_ = ip_.getPrefix();
        for (Element n: XmlParser.childrenElements(_node)) {
            if (!StringList.quickEq(n.getNodeName(),prefix_+PARAM_BLOCK_TAG)) {
                continue;
            }
            ip_.setProcessingNode(n);
            if (!n.hasAttribute(ATTRIBUTE_VAR)) {
                ip_.setProcessingAttribute(EMPTY_STRING);
                ip_.setLookForAttrValue(false);
            } else {
                ip_.setProcessingAttribute(ATTRIBUTE_VAR);
                ip_.setLookForAttrValue(true);
            }
            ip_.setOffset(0);
            String var_ = n.getAttribute(ATTRIBUTE_VAR);
            if (!StringList.isWord(var_)) {
                throw new BadVariableNameException(_conf.joinPages(), var_, ATTRIBUTE_VAR);
            }
            VariableInformation vi_ = tryToGetVariableInformation(_conf, ip_, n);
            Class<?> cl_ = vi_.getClassRef();
            String className_ = cl_.getName();

            Struct obj_ = vi_.getStruct();
            parameters_.put(var_, tryToCreateVariable(_conf, ip_, className_, obj_));
        }
        return parameters_;
    }
    private static StringMap<LocalVariable> newReturnedValues(Configuration _conf, Node _node) {
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LocalVariable> parameters_ = new StringMap<LocalVariable>();
        String prefix_ = ip_.getPrefix();
        for (Element n: XmlParser.childrenElements(_node)) {
            if (!StringList.quickEq(n.getNodeName(),prefix_+SET_BLOCK_TAG)) {
                continue;
            }
            ip_.setProcessingNode(n);
            if (!n.hasAttribute(ATTRIBUTE_VAR)) {
                ip_.setProcessingAttribute(EMPTY_STRING);
                ip_.setLookForAttrValue(false);
            } else {
                ip_.setProcessingAttribute(ATTRIBUTE_VAR);
                ip_.setLookForAttrValue(true);
            }
            ip_.setOffset(0);
            String var_ = n.getAttribute(ATTRIBUTE_VAR);
            if (!StringList.isWord(var_)) {
                throw new BadVariableNameException(_conf.joinPages(), var_, ATTRIBUTE_VAR);
            }
            VariableInformation vi_ = tryToGetVoidVariable(_conf, ip_, n);
            Class<?> cl_ = vi_.getClassRef();
            String className_ = cl_.getName();

            LocalVariable loc_ = new LocalVariable();
            loc_.setClassName(ConstClasses.resolve(className_));
            parameters_.put(var_, loc_);
        }
        return parameters_;
    }

    static String processHtmlJava(String _htmlText, Configuration _conf, String _loc, StringMap<String> _files, String... _resourcesFolder) {
        String htmlText_ = _htmlText;
        String beanName_ = null;
        Bean bean_ = null;
        Document docOrig_ = _conf.getDocument();
        Element root_ = docOrig_.getDocumentElement();
        beanName_ = root_.getAttribute(_conf.getPrefix()+BEAN_ATTRIBUTE);
        bean_ = getBean(_conf, beanName_);
        beforeDisplaying(bean_, _conf);
        _conf.setHtml(_htmlText);
        htmlText_ = processHtml(docOrig_, beanName_, _conf, _loc, _files, _resourcesFolder);
        if (htmlText_ == null) {
            return null;
        }
        String prefix_ = _conf.getPrefix();
        StringBuilder str_ = new StringBuilder();
        int indexLoc_ = 0;
        while (true) {
            int indexMin_ = CustList.INDEX_NOT_FOUND_ELT;
            int indexEndTag_ = htmlText_.indexOf(LT_BEGIN_TAG+prefix_+TR_END_BLOCK_TAG+RIGHT_END_TAG, indexLoc_);
            int indexBeginTag_ = htmlText_.indexOf(LT_BEGIN_TAG+prefix_+TR_BEGIN_BLOCK_TAG, indexLoc_);
            if (indexEndTag_ <= indexBeginTag_) {
                if (indexEndTag_ > CustList.INDEX_NOT_FOUND_ELT) {
                    indexMin_ = indexEndTag_;
                } else {
                    indexMin_ = indexBeginTag_;
                }
            } else {
                if (indexBeginTag_ > CustList.INDEX_NOT_FOUND_ELT) {
                    indexMin_ = indexBeginTag_;
                } else {
                    indexMin_ = indexEndTag_;
                }
            }
            if (indexMin_ == CustList.INDEX_NOT_FOUND_ELT) {
                str_.append(htmlText_.substring(indexLoc_));
                break;
            }
            str_.append(htmlText_.substring(indexLoc_, indexMin_));
            if (indexMin_ == indexEndTag_) {
                str_.append(TR_END);
                indexLoc_ = indexEndTag_ + (LT_BEGIN_TAG+prefix_+TR_END_BLOCK_TAG+RIGHT_END_TAG).length();
                continue;
            }
            String begin_ = htmlText_.substring(indexMin_);
            String nameTag_ = LT_BEGIN_TAG+prefix_+TR_BEGIN_BLOCK_TAG;
            if (begin_.startsWith(nameTag_+RIGHT_END_TAG)) {
                str_.append(TR_BEGIN);
                indexLoc_ = indexBeginTag_ + (nameTag_+RIGHT_END_TAG).length();
                continue;
            }
            String beginTag_ = begin_.substring(nameTag_.length(), begin_.indexOf(RIGHT_END_TAG));
            str_.append(LT_BEGIN_TAG + TR_TAG + beginTag_ + GT_TAG);
            indexLoc_ = indexBeginTag_ + nameTag_.length() + beginTag_.length() + RIGHT_END_TAG.length();
        }
        return str_.toString();
    }

    static String processHtml(Document _docOrig, String _beanName, Configuration _conf, String _loc, StringMap<String> _files, String... _resourcesFolder) {
        Bean bean_ = getBean(_conf, _beanName);
        Bean mainBean_ = bean_;

        ImportingPage ip_ = new ImportingPage(false);
        ip_.setTabWidth(getTabWidth(_conf));
        ip_.setHtml(_conf.getHtml());
        ip_.setReadUrl(_conf.getCurrentUrl());
        ip_.setBeanName(_beanName);
        ip_.setPrefix(_conf.getPrefix());
        Element r_ = _docOrig.getDocumentElement();
        if (bean_ != null) {
            ip_.setGlobalArgumentObj(bean_);
        }
        _conf.addPage(ip_);
        checkSyntax(_conf, _docOrig, ip_.getHtml());
        Node en_ = r_;
        Document doc_ = XmlParser.newXmlDocument(true);
        Node currentNode_ = doc_;
        ReadWriteHtml rw_ = new ReadWriteHtml();
        rw_.setRead(en_);
        rw_.setWrite(currentNode_);
        ip_.setRoot(r_);
        ip_.setReadWrite(rw_);
        NumberMap<Long,NatTreeMap<Long,NodeContainer>> containersMap_;
        containersMap_ = new NumberMap<Long,NatTreeMap<Long,NodeContainer>>();
        NatTreeMap<Long,NodeContainer> containers_;
        containers_ = new NatTreeMap<Long,NodeContainer>();
        IndexesFormInput indexes_ = new IndexesFormInput();
        Element curForm_ = null;
        long currentAnchor_ = 0;
        long currentForm_ = 0;
        while (true) {
            if (_conf.isInterrupt()) {
                return null;
            }
            try {
                ip_ = _conf.getLastPage();
                if (ip_.getReadWrite() == null) {
                    ImportingPage last_ = _conf.getLastPage();
                    _conf.removeLastPage();
                    if (_conf.noPages()) {
                        break;
                    }
                    ip_ = _conf.getLastPage();
                    ip_.getLocalVars().putAllMap(last_.getReturnedValues());
                    continue;
                }
                rw_ = ip_.getReadWrite();
                en_ = rw_.getRead();
                ip_.setProcessingNode(en_);
                ip_.setProcessingAttribute(EMPTY_STRING);
                ip_.setOffset(0);
                currentNode_ = rw_.getWrite();
                if (en_ instanceof Comment) {
                    processElementOrText(_conf, ip_);
                    continue;
                }
                if (en_.getNodeName().startsWith(ip_.getPrefix()) && !ip_.getPrefix().isEmpty()) {
                    if (StringList.quickEq(en_.getNodeName(),ip_.getPrefix()+EXIT_TAG)) {
                        _conf.clearPages();
                        break;
                    }
                    ImportingPage ret_ = processProcessingTags(_conf, doc_, ip_, containersMap_, containers_, indexes_, currentForm_, mainBean_, _loc, _files, _resourcesFolder);
                    if (ret_ != null ) {
                        ip_ = ret_;
                        continue;
                    }
                }
                if (en_ instanceof Element) {
                    appendChild(doc_, _conf, currentNode_, (Element) en_);
                    Element tag_ = (Element) currentNode_.getLastChild();
                    if (StringList.quickEq(en_.getNodeName(),TAG_FORM)) {
                        curForm_ = tag_;
                        containersMap_.put(currentForm_, containers_);
                        containers_ = new NatTreeMap<Long, NodeContainer>();
                        currentForm_ ++;
                        indexes_.setInput(0);
                    }
                    processAttributes(_conf, _loc, _files, ip_, doc_, tag_,
                            indexes_, containersMap_, containers_, _resourcesFolder);
                    if (StringList.quickEq(en_.getNodeName(),TAG_FORM)) {
                        curForm_.setAttribute(NUMBER_FORM, String.valueOf(currentForm_ - 1));
                    }
                    if (StringList.quickEq(tag_.getNodeName(), TAG_A) && (tag_.hasAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND)|| !tag_.getAttribute(ATTRIBUTE_HREF).isEmpty() )) {
                        currentAnchor_ = indexes_.getAnchor();
                        tag_.setAttribute(NUMBER_ANCHOR, String.valueOf(currentAnchor_));
                        currentAnchor_++;
                        indexes_.setAnchor(currentAnchor_);
                    }
                    processElementOrText(_conf, ip_);
                    continue;
                }
                String content_ = en_.getTextContent();
                if (content_.trim().isEmpty()) {
                    Text t_ = doc_.createTextNode(content_);
                    currentNode_.appendChild(t_);
                    processElementOrText(_conf, ip_);
                    continue;
                }
                if (interpretBrackets(en_)) {
                    content_ = ExtractObject.formatNumVariables(content_, _conf, ip_, _files);
                }
                Text t_ = doc_.createTextNode(content_);
                currentNode_.appendChild(t_);
                processElementOrText(_conf, ip_);
            } catch (Throwable _0){
                Throwable realCaught_ = _0;
                if (_0 instanceof WrapperException) {
                    realCaught_ = ((WrapperException)_0).getWrapped();
                }
                Throwable t_ = throwException(_conf, realCaught_);
                if (t_ == null) {
                    continue;
                }
                throw new RenderingException(new Struct(_0));
            }
        }
        containersMap_.put(currentForm_, containers_);
        containersMap_.removeKey(0l);
        if (currentForm_ > 1) {
            curForm_.setAttribute(NUMBER_FORM, String.valueOf(currentForm_ - 1));
        }
        for (Long k: containersMap_.getKeys()) {
            containersMap_.move(k, k - 1);
        }
        _conf.getHtmlPage().setContainers(containersMap_);
        doc_.getDocumentElement().removeAttribute(_conf.getPrefix()+BEAN_ATTRIBUTE);
        return XmlParser.toHtml(doc_);
    }

    static Throwable throwException(Configuration _conf, Throwable _t) {
        Element catchElt_ = null;
        boolean indirect_ = _t instanceof IndirectException;
        Struct custCause_;
        if (_t instanceof IndirectException) {
            custCause_ = ((IndirectException)_t).getCustCause();
        } else {
            if (_t.getCause() == null) {
                custCause_ = new Struct();
            } else {
                custCause_ = new Struct(_t.getCause());
            }
        }
        while (!_conf.noPages()) {
            ImportingPage bkIp_ = _conf.getLastPage();
            String prefix_ = bkIp_.getPrefix();
            while (!bkIp_.noBlock()) {
                BlockHtml bl_ = bkIp_.getLastStack();
                if (!(bl_ instanceof TryHtmlStack)) {
                    if (bl_ instanceof LoopHtmlStack) {
                        LoopHtmlStack loopStack_ = (LoopHtmlStack) bl_;
                        Element forNode_ = loopStack_.getReadNode();
                        removeVarAndLoop(_conf, forNode_, bkIp_.getVars());
                    } else {
                        bkIp_.removeLastBlock();
                    }
                    continue;
                }
                TryHtmlStack try_ = (TryHtmlStack)bl_;
                boolean addFinallyClause_ = true;
                if (!StringList.quickEq(try_.getCatchNodes().last().getNodeName(), prefix_+TAG_FINALLY)) {
                    addFinallyClause_ = false;
                }
                if (try_.getVisitedCatch() >= CustList.FIRST_INDEX) {
                    if (!StringList.quickEq(try_.getCurrentCatchNode().getNodeName(), prefix_+TAG_FINALLY)) {
                        if (addFinallyClause_) {
                            try_.setThrownException(new WrapperException(_t));
                            Element newCurrentNode_ = try_.getWriteNode();
                            bkIp_.getReadWrite().setRead(try_.getCatchNodes().last());
                            bkIp_.getReadWrite().setWrite(newCurrentNode_);
                            return null;
                        }
                    }
                    bkIp_.removeLastBlock();
                    continue;
                }
                //process try block
                int i_ = 0;
                for (Element e: try_.getCatchNodes()) {
                    if (StringList.quickEq(e.getNodeName(), prefix_+TAG_FINALLY)) {
                        break;
                    }
                    String name_ = e.getAttribute(ATTRIBUTE_CLASS_NAME);
                    if (!indirect_) {
                        if (PrimitiveTypeUtil.canBeUseAsArgument(name_, _t.getClass().getName(), _conf.toContextEl().getClasses())) {
                            catchElt_ = e;
                            try_.setVisitedCatch(i_);
                            break;
                        }
                    } else {
                        if (PrimitiveTypeUtil.canBeUseAsArgument(name_, custCause_.getClassName(), _conf.toContextEl().getClasses())) {
                            catchElt_ = e;
                            try_.setVisitedCatch(i_);
                            break;
                        }
                    }
                    i_++;
                }
                if (catchElt_ != null) {
                    Element newCurrentNode_ = ((BlockHtml)bkIp_.getLastStack()).getWriteNode();
                    Element catchElement_ = catchElt_;
                    try_.setThrownException(null);
                    if (catchElement_.getFirstChild() != null) {
                        String var_ = catchElement_.getAttribute(ATTRIBUTE_VAR);
                        LocalVariable lv_ = new LocalVariable();
                        Throwable t_ = _t;
                        if (indirect_) {
                            lv_.setStruct(custCause_);
                        } else {
                            lv_.setStruct(new Struct(t_));
                        }
                        lv_.setClassName(ConstClasses.resolve(catchElement_.getAttribute(ATTRIBUTE_CLASS_NAME)));
                        bkIp_.getCatchVars().put(var_, lv_);
                        bkIp_.getReadWrite().setRead(catchElement_.getFirstChild());
                        bkIp_.getReadWrite().setWrite(newCurrentNode_);
                        return null;
                    }
                    bkIp_.getReadWrite().setRead(catchElement_);
                    bkIp_.getReadWrite().setWrite(newCurrentNode_);
                    return null;
                }
                if (addFinallyClause_) {
                    try_.setThrownException(new WrapperException(_t));
                    Element newCurrentNode_ = try_.getWriteNode();
                    bkIp_.getReadWrite().setRead(try_.getCatchNodes().last());
                    bkIp_.getReadWrite().setWrite(newCurrentNode_);
                    return null;
                }
                bkIp_.removeLastBlock();
            }
            _conf.removeLastPage();
        }
        return _t;
    }

    /**@throws InvokeRedinedMethException
    @throws DivideZeroException
    @throws BadIndexException
    @throws NegativeSizeException
    @throws ErrorCausingException
    @throws DynamicCastClassException
    @throws RuntimeClassNotFoundException
    @throws NullObjectException
    @throws InvokeException
    @throws UnwrappingException
    @throws BadEnumeratingException
    @throws BadFilePropertiesException
    @throws BadReferenceEqualsException
    @throws CharacterFormatException
    @throws GettingKeysException
    @throws InexistingTranslatorException
    @throws MessageKeyNotFoundException
    @throws NoSuchResourceException
    @throws NotCastableException
    @throws NotPrimitivableException
    @throws SettingArrayException*/
    private static ImportingPage processProcessingTags(Configuration _conf, Document _doc,
            ImportingPage _ip,
            NumberMap<Long,NatTreeMap<Long,NodeContainer>> _containersMap,
            NatTreeMap<Long,NodeContainer> _containers,
            IndexesFormInput _indexes,
            long _currentForm,
            Bean _mainBean,
            String _loc, StringMap<String> _files, String... _resourcesFolder) {
        NumberMap<Long,NatTreeMap<Long,NodeContainer>> containersMap_ = _containersMap;
        String prefix_ = _conf.getLastPage().getPrefix();
        NatTreeMap<Long,NodeContainer> containers_ = _containers;
        ReadWriteHtml rw_ = _ip.getReadWrite();
        IndexesFormInput indexes_ = _indexes;
        long currentAnchor_ = _indexes.getAnchor();
        Document doc_ = _doc;
        Node en_ = rw_.getRead();
        ImportingPage ip_ = _ip;
        Node currentNode_ = rw_.getWrite();
        if (StringList.quickEq(en_.getNodeName(), prefix_+SET_BLOCK_TAG)) {
            Element set_ = (Element) en_;
            processSetTag(_conf, ip_, set_);
            processBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(), prefix_+PARAM_BLOCK_TAG)) {
            Element set_ = (Element) en_;
            processSetClassNameParamTag(_conf, _ip, set_);
            processBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(), prefix_+SET_RETURNED_VALUE_BLOCK_TAG)) {
            Element set_ = (Element) en_;
            processSetReturnValueTag(_conf, ip_, set_);
            processBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(), prefix_+UNSET_BLOCK_TAG)) {
            Element set_ = (Element) en_;
            String var_ = set_.getAttribute(ATTRIBUTE_VAR);
            ip_.getLocalVars().removeKey(var_);
            processBlock(_conf, _ip);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+CONTINUE_TAG)) {
            while (true) {
                BlockHtml blStack_ = ip_.getLastStack();
                if (blStack_ instanceof LoopHtmlStack) {
                    break;
                }
                if (blStack_ instanceof TryHtmlStack) {
                    TryHtmlStack tryStack_ = (TryHtmlStack) blStack_;
                    int index_ = tryStack_.getVisitedCatch();
                    if (index_ >= CustList.FIRST_INDEX) {
                        Element catchBlock_ = tryStack_.getCatchNodes().get(index_);
                        String var_ = catchBlock_.getAttribute(ATTRIBUTE_VAR);
                        ip_.getCatchVars().removeKey(var_);
                    }
                }
                ip_.removeLastBlock();
            }
            processLastElementLoop(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+BREAK_TAG)) {
            BreakableHtmlStack stack_ = null;
            while (true) {
                BlockHtml blStack_ = ip_.getLastStack();
                if (blStack_ instanceof BreakableHtmlStack) {
                    stack_ = (BreakableHtmlStack) blStack_;
                    break;
                }
                if (blStack_ instanceof TryHtmlStack) {
                    TryHtmlStack tryStack_ = (TryHtmlStack) blStack_;
                    int index_ = tryStack_.getVisitedCatch();
                    if (index_ >= CustList.FIRST_INDEX) {
                        Element catchBlock_ = tryStack_.getCatchNodes().get(index_);
                        String var_ = catchBlock_.getAttribute(ATTRIBUTE_VAR);
                        ip_.getCatchVars().removeKey(var_);
                    }
                }
                ip_.removeLastBlock();
            }
            Element forLoopLoc_ = stack_.getReadNode();
            currentNode_ = stack_.getWriteNode();
            en_ = forLoopLoc_;
            rw_.setRead(en_);
            rw_.setWrite(currentNode_);
            stack_.setFinished(true);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+RETURN_TAG)) {
            ip_.setReturning(true);
            return removeBlockFinally(_conf, ip_);
        }

        if (StringList.quickEq(en_.getNodeName(),prefix_+CATCH_TAG)) {
            TryHtmlStack ts_ = (TryHtmlStack) ip_.getLastStack();
            ts_.setThrownException(null);
            if (ts_.getLastCatchNode() == en_) {
                ip_.removeLastBlock();
                processBlock(_conf, ip_);
            } else {
                rw_.setRead(en_.getNextSibling());
            }
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+TAG_FINALLY)) {
            TryHtmlStack ts_ = (TryHtmlStack) ip_.getLastStack();
            ts_.setVisitedCatch(ts_.getCatchNodes().size()-1);
            if (ip_.isReturning()) {
                processAfterBlock(_conf, ip_);
                return ip_;
            }
            if (ts_.isVisitedFinally()) {
                ip_.removeLastBlock();
                processBlock(_conf, ip_);
                return ip_;
            }
            processAfterBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+THROW_TAG)) {
            String el_ = ((Element)en_).getAttribute(EXPRESSION_ATTRIBUTE);
            ContextEl cont_ = _conf.toContextEl();
            Argument arg_ = ElUtil.processEl(el_, 0, cont_);
            if (!PrimitiveTypeUtil.canBeUseAsArgument(Throwable.class.getName(), arg_.getObjectClassName(), cont_.getClasses())) {
                throw new InvokeException(_conf.joinPages(), new Struct(new NullPointerException()));
            }
            Throwable o_ = (Throwable) arg_.getObject();
            if (o_ == null) {
                o_ = new NullPointerException();
            }
            throw new InvokeException(_conf.joinPages(), new Struct(o_));
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+TRY_TAG)) {
            processAfterBlock(_conf, ip_);
            Node n_ = en_.getNextSibling();
            TryHtmlStack tryStack_ = new TryHtmlStack();
            while (n_ != null) {
                if (n_ instanceof Text) {
                    n_ = n_.getNextSibling();
                    continue;
                }
                if (n_ instanceof Comment) {
                    n_ = n_.getNextSibling();
                    continue;
                }
                if (!StringList.quickEq(n_.getNodeName(), prefix_+CATCH_TAG)) {
                    if (!StringList.quickEq(n_.getNodeName(), prefix_+TAG_FINALLY)) {
                        break;
                    }
                }
                tryStack_.getCatchNodes().add((Element) n_);
                n_ = n_.getNextSibling();
            }
            tryStack_.setWriteNode((Element) currentNode_);
            tryStack_.setReadNode((Element) en_);
            ip_.addBlock(tryStack_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+ELSE_BLOCK_TAG)) {
            IfHtmlStack if_ = (IfHtmlStack) ip_.getLastStack();
            if_.changeVisitedElement(en_);
            if (!if_.isEntered()) {
                if_.setEntered(true);
                processAfterBlock(_conf, ip_);
                return ip_;
            }
            ip_.removeLastBlock();
            processBlock(_conf, ip_);
            return ip_;
        }
        if (ExtractCondition.isContentOfConditionNode(_conf, en_)) {
            IfHtmlStack if_ = (IfHtmlStack) ip_.getLastStack();
            if_.changeVisitedElement(en_);
            if (!if_.isEntered()) {
                boolean assert_ = ExtractCondition.evaluateGenericCondition((Element) en_, _conf, ip_);
                if (assert_) {
                    if_.setEntered(true);
                    processAfterBlock(_conf, ip_);
                    return ip_;
                }
            }
            if (if_.getNodes().last() == en_) {
                ip_.removeLastBlock();
                processBlock(_conf, ip_);
                return ip_;
            }
            rw_.setRead(en_.getNextSibling());
            return ip_;
        }
        if (ExtractCondition.isBeginOfConditionNode(_conf, en_)) {
            if (!ip_.noBlock()) {
                BlockHtml bl_ = (BlockHtml) ip_.getLastStack();
                if (bl_.getReadNode() == en_) {
                    ip_.removeLastBlock();
                    processBlock(_conf, ip_);
                    return ip_;
                }
            }
            IfHtmlStack if_ = new IfHtmlStack();
            if_.getNodes().add((Element) en_);
            Node n_ = en_.getNextSibling();
            while (n_ != null) {
                if (n_ instanceof Text) {
                    n_ = n_.getNextSibling();
                    continue;
                }
                if (n_ instanceof Comment) {
                    n_ = n_.getNextSibling();
                    continue;
                }
                if (!StringList.quickEq(n_.getNodeName(),prefix_+ELSE_BLOCK_TAG)) {
                    if (!ExtractCondition.isContentOfConditionNode(_conf, n_)) {
                        break;
                    }
                }
                if_.getNodes().add((Element) n_);
                n_ = n_.getNextSibling();
            }
            if_.setReadNode((Element) en_);
            if_.setWriteNode((Element) currentNode_);
            if_.setVisitedBlock(CustList.FIRST_INDEX);
            boolean assert_ = ExtractCondition.evaluateGenericCondition((Element) en_, _conf, ip_);
            if (assert_) {
                if (en_.hasChildNodes()) {
                    ip_.addBlock(if_);
                    if_.setEntered(true);
                } else {
                    if (if_.getNodes().size() > CustList.ONE_ELEMENT) {
                        if_.setEntered(true);
                        ip_.addBlock(if_);
                        rw_.setRead(en_.getNextSibling());
                        return ip_;
                    }
                }
                processAfterBlock(_conf, ip_);
            } else {
                ip_.addBlock(if_);
                if (if_.lastVisitedNode() == en_) {
                    return ip_;
                }
                rw_.setRead(en_.getNextSibling());
            }
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+TAG_SWITCH)) {
            if (!ip_.noBlock()) {
                BlockHtml bl_ = (BlockHtml) ip_.getLastStack();
                if (bl_.getReadNode() == en_) {
                    ip_.removeLastBlock();
                    processBlock(_conf, ip_);
                    return ip_;
                }
            }
            SwitchHtmlStack if_ = new SwitchHtmlStack();
            Node n_ = en_.getFirstChild();
            while (n_ != null) {
                if (n_ instanceof Text) {
                    n_ = n_.getNextSibling();
                    continue;
                }
                if (n_ instanceof Comment) {
                    n_ = n_.getNextSibling();
                    continue;
                }
                if_.getNodes().add((Element) n_);
                n_ = n_.getNextSibling();
            }
            if_.setReadNode((Element) en_);
            if_.setWriteNode((Element) currentNode_);
            ip_.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
            ip_.setOffset(0);
            ip_.setLookForAttrValue(true);
            String expr_ = ((Element) en_).getAttribute(EXPRESSION_ATTRIBUTE);
            Argument arg_ = ElUtil.processEl(expr_, 0, _conf.toContextEl());
            if_.setStruct(arg_.getStruct());
            if (if_.getNodes().isEmpty()) {
                if_.setFinished(true);
                ip_.addBlock(if_);
                return ip_;
            }
            rw_.setRead(en_.getFirstChild());
            ip_.addBlock(if_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+TAG_CASE)) {
            SwitchHtmlStack sw_ = (SwitchHtmlStack) ip_.getLastStack();
            Object value_ = sw_.getValue();
            if (sw_.isEntered()) {
                if (!en_.hasChildNodes()) {
                    sw_.increment();
                    if (sw_.lastVisitedNode() == en_) {
                        sw_.setFinished(true);
                        rw_.setRead(sw_.getReadNode());
                        return ip_;
                    }
                    rw_.setRead(en_.getNextSibling());
                    return ip_;
                }
                processAfterBlock(_conf, ip_);
                return ip_;
            } else {
                ip_.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                ip_.setOffset(0);
                ip_.setLookForAttrValue(true);
                String expr_ = ((Element) en_).getAttribute(EXPRESSION_ATTRIBUTE);
                Argument arg_ = ElUtil.processEl(expr_, 0, _conf.toContextEl());
                //TODO processEl
                boolean enter_ = false;
                if (value_ == null) {
                    if (arg_.getObject() == null) {
                        enter_ = true;
                    }
                } else {
                    if (ExtractObject.eq(_conf, value_, arg_.getObject())) {
                        enter_ = true;
                    }
                }
                int i_ = CustList.FIRST_INDEX;
                while (true) {
                    if (sw_.getNodes().get(i_) == en_) {
                        break;
                    }
                    i_++;
                }
                sw_.setVisitedBlock(i_);
                if (enter_) {
                    if (en_.hasChildNodes()) {
                        sw_.setEntered(true);
                    } else {
                        sw_.increment();
                        if (sw_.lastVisitedNode() != en_) {
                            sw_.setEntered(true);
                            rw_.setRead(en_.getNextSibling());
                            return ip_;
                        } else {
                            sw_.setFinished(true);
                            rw_.setRead(sw_.getReadNode());
                            return ip_;
                        }
                    }
                    processAfterBlock(_conf, ip_);
                    return ip_;
                }
                if (sw_.lastVisitedNode() == en_) {
                    sw_.setFinished(true);
                    rw_.setRead(sw_.getReadNode());
                    return ip_;
                }
                rw_.setRead(en_.getNextSibling());
                return ip_;
            }
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+TAG_DEFAULT)) {
            SwitchHtmlStack sw_ = (SwitchHtmlStack) ip_.getLastStack();
            if (sw_.isEntered()) {
                if (!en_.hasChildNodes()) {
                    sw_.increment();
                    if (sw_.lastVisitedNode() == en_) {
                        sw_.setFinished(true);
                        rw_.setRead(sw_.getReadNode());
                        return ip_;
                    }
                    rw_.setRead(en_.getNextSibling());
                    return ip_;
                }
                processAfterBlock(_conf, ip_);
                return ip_;
            } else {
                int i_ = CustList.FIRST_INDEX;
                while (true) {
                    if (sw_.getNodes().get(i_) == en_) {
                        break;
                    }
                    i_++;
                }
                sw_.setVisitedBlock(i_);
                if (en_.hasChildNodes()) {
                    sw_.setEntered(true);
                } else {
                    sw_.increment();
                    if (sw_.lastVisitedNode() != en_) {
                        sw_.setEntered(true);
                        rw_.setRead(en_.getNextSibling());
                        return ip_;
                    } else {
                        sw_.setFinished(true);
                        rw_.setRead(sw_.getReadNode());
                        return ip_;
                    }
                }
                processAfterBlock(_conf, ip_);
                return ip_;
            }
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+SELECT_TAG)) {
            String map_ = ((Element) en_).getAttribute(ATTRIBUTE_MAP);
            String id_ = ((Element) en_).getAttribute(ATTRIBUTE_ID);
            String groupId_ = ((Element) en_).getAttribute(ATTRIBUTE_GROUP_ID);
            String class_ = ((Element) en_).getAttribute(ATTRIBUTE_CLASS);
            boolean multiple_ = ((Element) en_).hasAttribute(ATTRIBUTE_MULTIPLE);
            //TODO converter
            if (!map_.isEmpty()) {
                processOptionsMap(_conf, doc_,
                        currentNode_, en_, map_, id_, groupId_, multiple_);
            } else {
                processOptionsList(_conf,
                        doc_, currentNode_, (Element) en_, id_, groupId_, multiple_);
            }
            Element selectTag_ = (Element) currentNode_.getLastChild();
            //format class before evaluate it
            if (!class_.isEmpty()) {
                CustList<BlockHtml> stacks_ = ip_.getBlockStacks();
                class_ = format(stacks_, class_, false);
                selectTag_.setAttribute(ATTRIBUTE_CLASS, class_);
                evaluateAttribute(_conf, selectTag_, ATTRIBUTE_CLASS);
            }
            //format name
            String name_ = selectTag_.getAttribute(ATTRIBUTE_NAME);
            if (!name_.isEmpty()) {
                ip_.setProcessingAttribute(ATTRIBUTE_NAME);
                ip_.setLookForAttrValue(true);
                ip_.setOffset(0);
                setIndexes(indexes_, _conf, ip_, containersMap_, containers_, selectTag_, name_);
                if (indexes_.getNb() >= 0) {
                    FormInputCoords inputs_ = new FormInputCoords();
                    inputs_.setForm(_currentForm - 1);
                    inputs_.setInput(indexes_.getNb());
                    StringList allOptions_ = new StringList();
                    NodeList elts_ = selectTag_.getElementsByTagName(TAG_OPTION);
                    int nbElts_ = elts_.getLength();
                    for (int i = 0; i < nbElts_; i++) {
                        Element opt_ = (Element) elts_.item(i);
                        allOptions_.add(opt_.getAttribute(ATTRIBUTE_VALUE));
                    }
                    _conf.getHtmlPage().getSelects().put(inputs_, allOptions_);
                    selectTag_.setAttribute(NUMBER_INPUT, String.valueOf(indexes_.getNb()));
                }
                selectTag_.setAttribute(ATTRIBUTE_NAME, ip_.getBeanName()+DOT+name_);
            }
            String selectId_ = selectTag_.getAttribute(ATTRIBUTE_ID);
            if (!selectId_.isEmpty()) {
                ip_.setProcessingAttribute(ATTRIBUTE_ID);
                selectId_ = interpretPartiallyIds(_conf, ip_, selectId_);
                selectTag_.setAttribute(ATTRIBUTE_ID, selectId_);
            }
            String selectGroupId_ = selectTag_.getAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID);
            if (!selectGroupId_.isEmpty()) {
                ip_.setProcessingAttribute(ATTRIBUTE_GROUP_ID);
                selectGroupId_ = interpretPartiallyIds(_conf, ip_, selectGroupId_);
                selectTag_.setAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID, selectGroupId_);
            }
            processBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+MESSAGE_BLOCK_TAG)) {
            Element element_ = (Element) en_;
            String formatted_ = ExtractObject.formatMessage(_conf, _loc, ip_, element_, _files, _resourcesFolder);
            if (!element_.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
                Text n_ = doc_.createTextNode(formatted_);
                currentNode_.appendChild(n_);
                ip_.setMessageValue(null);
                ip_.setKey(null);
                processBlock(_conf, ip_);
                return ip_;
            }
            ImportingPage ipMess_ = new ImportingPage(false);
            ipMess_.setTabWidth(getTabWidth(_conf));
            ipMess_.setBeanName(ip_.getBeanName());
            boolean treatAsFullHtml_ = false;
            if (formatted_.endsWith(LT_END_TAG+TAG_HTML+GT_TAG) && formatted_.startsWith(LT_BEGIN_TAG+TAG_HTML)) {
                if (Character.isWhitespace(formatted_.charAt(TAG_HTML.length()+1))) {
                    treatAsFullHtml_ = true;
                } else if (formatted_.charAt(TAG_HTML.length()+1) == GT_TAG) {
                    treatAsFullHtml_ = true;
                }
            }
            if (treatAsFullHtml_) {
                ipMess_.setHtml(formatted_);
            } else {
                ipMess_.setHtml(addToRoot(_conf, ip_.getPrefix(), formatted_));
            }
            ipMess_.setReadUrl(_conf.getResourceUrl());
            ipMess_.setCatchVars(ip_.getCatchVars());
            ipMess_.setLocalVars(ip_.getLocalVars());
            ipMess_.setVars(ip_.getVars());
            Node readNode_ = ip_.getReadWrite().getRead();
            ipMess_.setReadWrite(ip_.getReadWrite());
            ipMess_.setBlockStacks(ip_.getBlockStacks());
            ipMess_.setMessageValue(ip_.getMessageValue());
            ipMess_.setKey(ip_.getKey());
            ip_.setMessageValue(null);
            ip_.setKey(null);
            _conf.addPage(ipMess_);
            Document docLoc_;
            try {
                docLoc_ = XmlParser.parseSaxHtml(ipMess_.getHtml(), false, true);
                ipMess_.setPrefix(getPrefix(_conf, docLoc_));
                ipMess_.setRoot(docLoc_.getDocumentElement());
            } catch (XmlParseException _0) {
                throw new XmlParseException(ipMess_.getHtml()+RETURN_LINE+_conf.joinPages());
            }
            setEscapedChars(_conf, docLoc_, ipMess_.getHtml());
            Element rootLoc_ = docLoc_.getDocumentElement();
            CustList<NodeAction> nas_ = getDeepChildNodesDocOrder(rootLoc_);
            boolean j_ = false;
            for (NodeAction n: nas_) {
                if (n.getActions().first() == ActionNext.NONE) {
                    continue;
                }
                Node nLoc_ = n.getNode();
                if (n.getActions().first() == ActionNext.FIRST_CHILD) {
                    if (j_) {
                        currentNode_ = currentNode_.getLastChild();
                    }
                } else {
                    int i_ = 0;
                    while (n.getActions().get(i_) == ActionNext.PARENT_NODE) {
                        currentNode_ = currentNode_.getParentNode();
                        i_ ++;
                    }
                }
                if (nLoc_ instanceof Text) {
                    Text t_ = doc_.createTextNode(nLoc_.getTextContent());
                    currentNode_.appendChild(t_);
                } else if (nLoc_ instanceof Element) {
                    ipMess_.setProcessingNode(nLoc_);
                    ipMess_.getReadWrite().setRead(nLoc_);
                    FormatHtml.appendChild(doc_, _conf, currentNode_, (Element) nLoc_);
                    Element tag_ = (Element) currentNode_.getLastChild();
                    processAttributes(_conf, _loc, _files, ipMess_, doc_, tag_,
                            indexes_, containersMap_, containers_, _resourcesFolder);
                    if (StringList.quickEq(tag_.getNodeName(), TAG_A) && (tag_.hasAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND)|| !tag_.getAttribute(ATTRIBUTE_HREF).isEmpty() )) {
                        tag_.setAttribute(NUMBER_ANCHOR, String.valueOf(currentAnchor_));
                        currentAnchor_++;
                        _indexes.setAnchor(currentAnchor_);
                    }
                }
                j_ = true;
            }
            _conf.removeLastPage();
            ip_.getReadWrite().setRead(readNode_);
            processBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+IMPORT_BLOCK_TAG)) {
            BeanElement newElt_ = tryToOpenDocument((Element) en_, ip_, _conf, _files, _resourcesFolder);
            if (newElt_ == null) {
                processBlock(_conf, ip_);
                return ip_;
            }
            StringMap<LocalVariable> params_ = newParametersBeforeRender(_conf, en_);
            StringMap<LocalVariable> returnedValues_ = newReturnedValues(_conf, en_);
            boolean keepField_ = ((Element)en_).hasAttribute(KEEPFIELD_ATTRIBUTE);
            String beanName_ = newElt_.getBeanName();
            setBeanForms(_conf, _mainBean, en_, keepField_,
                    beanName_);
            setFieldsImportBean(_conf, en_,
                    beanName_);
            ip_.setProcessingNode(en_);
            ip_.setProcessingAttribute(EMPTY_STRING);
            ip_.setOffset(0);
            ip_.setLookForAttrValue(false);
            Bean newBean_ = getBean(_conf, beanName_);
            beforeDisplaying(newBean_, _conf);
            ImportingPage newIp_ = new ImportingPage(false);
            newIp_.setTabWidth(getTabWidth(_conf));
            newIp_.setHtml(newElt_.getHtml());
            newIp_.setOffset(0);
            newIp_.setRoot(newElt_.getRoot());
            newIp_.setProcessingNode(newElt_.getRoot().getFirstChild());
            newIp_.setReadUrl(newElt_.getUrl());
            newIp_.setBeanName(beanName_);
            newIp_.setPrefix(newElt_.getPrefix());
            ReadWriteHtml rwLoc_ = new ReadWriteHtml();
            rwLoc_.setWrite(currentNode_);
            rwLoc_.setRead(newElt_.getRoot().getFirstChild());
            newIp_.setReadWrite(rwLoc_);
            if (newBean_ != null) {
                newIp_.setGlobalArgumentObj(newBean_);
            }
            _conf.addPage(newIp_);
            newIp_.getParameters().putAllMap(params_);
            newIp_.getReturnedValues().putAllMap(returnedValues_);
            checkSyntax(_conf, newElt_.getRoot().getOwnerDocument(), newElt_.getHtml());
            processBlock(_conf, ip_);
            return newIp_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+TAG_DO)) {
            LoopHtmlStack c_ = null;
            if (!ip_.noBlock() && ip_.getLastStack() instanceof LoopHtmlStack){
                c_ = (LoopHtmlStack) ip_.getLastStack();
            }
            if (c_ != null && c_.getReadNode() == en_) {
                if (c_.isFinished()) {
                    removeVarAndLoop(_conf, c_.getReadNode(), ip_.getVars());
                    Node next_ = en_.getNextSibling();
                    while (true) {
                        if (next_ instanceof Text) {
                            next_ = next_.getNextSibling();
                            continue;
                        }
                        if (next_ instanceof Comment) {
                            next_ = next_.getNextSibling();
                            continue;
                        }
                        break;
                    }
                    rw_.setRead(next_);
                    processBlock(_conf, ip_);
                    return ip_;
                }
                processAfterBlock(_conf, ip_);
                return ip_;
            }
            if (en_.getFirstChild() == null) {
                Node next_ = en_.getNextSibling();
                while (true) {
                    if (next_ instanceof Text) {
                        next_ = next_.getNextSibling();
                        continue;
                    }
                    if (next_ instanceof Comment) {
                        next_ = next_.getNextSibling();
                        continue;
                    }
                    break;
                }
                while (ExtractCondition.evaluateCondition((Element) next_, _conf, _ip)) {
                    continue;
                }
                processBlock(_conf, ip_);
                return ip_;
            }
            processDoWhile(_conf, ip_);
            processAfterBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+WHILE_BLOCK_TAG)) {
            LoopHtmlStack c_ = null;
            if (!ip_.noBlock() && ip_.getLastStack() instanceof LoopHtmlStack){
                c_ = (LoopHtmlStack) ip_.getLastStack();
            }
            if (c_ != null && c_.getReadNode() == en_) {
                if (c_.isFinished()) {
                    removeVarAndLoop(_conf, c_.getReadNode(), ip_.getVars());
                    processBlock(_conf, ip_);
                    return ip_;
                }
                processAfterBlock(_conf, ip_);
                return ip_;
            }
            if (en_.getFirstChild() == null) {
                while (ExtractCondition.evaluateCondition((Element) en_, _conf, _ip)) {
                    continue;
                }
                processBlock(_conf, ip_);
                return ip_;
            }
            processWhile(_conf, ip_);
            c_ = (LoopHtmlStack) ip_.getLastStack();
            if (c_.isFinished()) {
                ip_.removeLastBlock();
                processBlock(_conf, ip_);
                return ip_;
            }
            processAfterBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getNodeName(),prefix_+FOR_BLOCK_TAG)) {
            LoopHtmlStack c_ = null;
            if (!ip_.noBlock() && ip_.getLastStack() instanceof LoopHtmlStack){
                c_ = (LoopHtmlStack) ip_.getLastStack();
            }
            if (c_ != null && c_.getReadNode() == en_) {
                if (c_.isFinished()) {
                    removeVarAndLoop(_conf, c_.getReadNode(), ip_.getVars());
                    processBlock(_conf, ip_);
                    return ip_;
                }
                processAfterBlock(_conf, ip_);
                return ip_;
            }
            processLoop(_conf, ip_);
            c_ = (LoopHtmlStack) ip_.getLastStack();
            if (c_.isFinished()) {
                ip_.removeLastBlock();
                processBlock(_conf, ip_);
                return ip_;
            } else {
                processAfterBlock(_conf, ip_);
                return ip_;
            }
        }
        return null;
    }
    static void interruptAfterFinally(ImportingPage _ip) {
        TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getLastStack();
        WrapperException t_ = tryStack_.getThrownException();
        if (t_ != null) {
            _ip.removeLastBlock();
            throw t_;
        }
    }
    private static ImportingPage removeBlockFinally(Configuration _conf,
            ImportingPage _ip) {
        String prefix_ = _conf.getLastPage().getPrefix();
        ReadWriteHtml rw_ = _ip.getReadWrite();
        ImportingPage ip_ = _ip;
        while (!ip_.noBlock()) {
            BlockHtml bl_ = ip_.getLastStack();
            if (bl_ instanceof TryHtmlStack) {
                TryHtmlStack t_ = (TryHtmlStack) bl_;
                if (t_.getVisitedCatch() >= CustList.FIRST_INDEX) {
                    Element catchBlock_ = t_.getCurrentCatchNode();
                    String var_ = catchBlock_.getAttribute(ATTRIBUTE_VAR);
                    ip_.getCatchVars().removeKey(var_);
                }
                if (StringList.quickEq(t_.getLastCatchNode().getNodeName(), prefix_+TAG_FINALLY)) {
                    if (t_.getLastCatchNode().hasChildNodes()) {
                        rw_.setRead(t_.getLastCatchNode());
                        rw_.setWrite(t_.getWriteNode());
                        return ip_;
                    }
                }
                ip_.removeLastBlock();
            } else if (bl_ instanceof LoopHtmlStack){
                LoopHtmlStack loopStack_ = (LoopHtmlStack) bl_;
                Element forNode_ = loopStack_.getReadNode();
                removeVarAndLoop(_conf, forNode_, ip_.getVars());
            } else {
                ip_.removeLastBlock();
            }
        }
        ip_.setReadWrite(null);
        return ip_;
    }
    private static void processSetReturnValueTag(Configuration _conf, ImportingPage _ip,
            Element _set) {
        String var_ = _set.getAttribute(ATTRIBUTE_VAR);
        LocalVariable ret_ = ExtractObject.getCurrentLocVariable(_conf, 0, _ip.getReturnedValues(), var_);
        Object elt_ = tryToGetObject(_conf, _ip, _set);
        String className_ = ret_.getClassName();
        checkClass(_conf, _ip, ConstClasses.classForNameNotInit(className_), elt_);
        ret_.setElement(elt_);
    }
    private static void processSetClassNameParamTag(Configuration _conf, ImportingPage _ip,
            Element _set) {
        String var_ = _set.getAttribute(ATTRIBUTE_VAR);
        LocalVariable ret_ = ExtractObject.getCurrentLocVariable(_conf, 0, _ip.getParameters(), var_);
        Object elt_ = ret_.getElement();
        String className_ = _set.getAttribute(ATTRIBUTE_CLASS_NAME);
        if (className_.isEmpty()) {
            className_ = Object.class.getName();
        }
        _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
        _ip.setLookForAttrValue(true);
        Class<?> newClassRef_ = ExtractObject.classForName(_conf, 0, className_);
        checkClass(_conf, _ip, newClassRef_, elt_);
        ret_.setClassName(ConstClasses.resolve(className_));
    }
    private static void processSetTag(Configuration _conf, ImportingPage _ip,
            Element _set) {
        if (!_set.hasAttribute(ATTRIBUTE_VAR)) {
            String expression_ = _set.getAttribute(EXPRESSION_ATTRIBUTE);
            if (_set.hasAttribute(ARRAY_INDEX_ATTRIBUTE) && _set.hasAttribute(ARRAY_ELEMENT_ATTRIBUTE)) {
                String indexExpr_ = _set.getAttribute(ARRAY_INDEX_ATTRIBUTE);
                _conf.getLastPage().setProcessingAttribute(ARRAY_INDEX_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
                Object index_;
                if (!StringList.isPositiveNumber(indexExpr_)) {
                    index_ = ElUtil.processEl(indexExpr_, 0, _conf.toContextEl()).getObject();
                } else {
                    index_ = Integer.parseInt(indexExpr_);
                }
                String elementExpr_ = _set.getAttribute(ARRAY_ELEMENT_ATTRIBUTE);
                _conf.getLastPage().setProcessingAttribute(ARRAY_ELEMENT_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
                Struct elementArg_ = ElUtil.processEl(elementExpr_, 0, _conf.toContextEl()).getStruct();
                _conf.getLastPage().setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
                Struct arrayArg_ = ElUtil.processEl(expression_, 0, _conf.toContextEl()).getStruct();
                int indexNb_;
                try {
                    indexNb_ = (Integer)index_;
                } catch (RuntimeException _0) {
                    _conf.getLastPage().setProcessingAttribute(ARRAY_INDEX_ATTRIBUTE);
                    _conf.getLastPage().setLookForAttrValue(false);
                    _conf.getLastPage().setOffset(0);
                    throw new DynamicNumberFormatException(_conf.joinPages());
                }
                LocalVariable right_ = new LocalVariable();
                right_.setClassName(elementArg_.getClassName());
                right_.setStruct(elementArg_);
                LocalVariable left_ = new LocalVariable();
                left_.setClassName(arrayArg_.getClassName());
                left_.setStruct(arrayArg_);
                String nameOne_ = _ip.getNextTempVar();
                _ip.getLocalVars().put(nameOne_, left_);
                String nameTwo_ = _ip.getNextTempVar();
                _ip.getLocalVars().put(nameTwo_, right_);
                try {
                    ElUtil.processAffect(EMPTY_STRING,ARRAY_ELEMENT_ATTRIBUTE, EXPRESSION_ATTRIBUTE, nameOne_+GET_LOC_VAR+LEFT_ARR+indexNb_+SUFFIX_INT+RIGHT_ARR, nameTwo_+GET_LOC_VAR, String.valueOf(EQUALS), _conf.toContextEl());
                } catch (RuntimeException _0) {
                    _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
                    _conf.getLastPage().setLookForAttrValue(false);
                    _conf.getLastPage().setOffset(0);
                    throw new SettingArrayException(_conf.joinPages(), new Struct(_0));
                } finally {
                    _ip.getLocalVars().removeKey(nameOne_);
                    _ip.getLocalVars().removeKey(nameTwo_);
                }
            } else {
                ElUtil.processEl(expression_, 0, _conf.toContextEl());
            }
            return;
        }
        String var_ = _set.getAttribute(ATTRIBUTE_VAR);
        if (!StringList.isWord(var_)) {
            _ip.setOffset(0);
            _ip.setProcessingAttribute(ATTRIBUTE_VAR);
            _ip.setLookForAttrValue(true);
            throw new BadVariableNameException(_conf.joinPages(), var_, ATTRIBUTE_VAR);
        }
        VariableInformation vi_ = tryToGetVariableInformation(_conf, _ip, _set);
        Class<?> classRef_ = vi_.getClassRef();
        String className_ = classRef_.getName();
        if (classRef_.isPrimitive()) {
            className_ = PrimitiveTypeUtil.PRIM+className_;
        }
        Struct obj_ = vi_.getStruct();
        _ip.getLocalVars().put(var_, tryToCreateVariable(_conf, _ip, className_, obj_));
    }

    static VariableInformation tryToGetVoidVariable(Configuration _conf, ImportingPage _ip,
            Element _element) {
        String className_ = _element.getAttribute(ATTRIBUTE_CLASS_NAME);
        Class<?> cl_ = Object.class;
        if (!className_.isEmpty()) {
            _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            cl_ = ExtractObject.classForName(_conf, 0, className_);
        } else {
            className_ = cl_.getName();
        }
        VariableInformation vi_ = new VariableInformation();
        vi_.setClassRef(cl_);
        return vi_;
    }
    static Object tryToGetObject(Configuration _conf, ImportingPage _ip,
            Element _element) {
        String numExpr_ = _element.getAttribute(NUMBER_EXPRESSION);
        Object obj_;
        String expression_ = _element.getAttribute(EXPRESSION_ATTRIBUTE);
        if (!numExpr_.isEmpty()) {
            expression_ = numExpr_;
            String evalBool_ = _element.getAttribute(EVALUATE_BOOLEAN);
            boolean eval_ = Boolean.parseBoolean(evalBool_);
            _ip.setProcessingAttribute(NUMBER_EXPRESSION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            obj_ = ExtractObject.evaluateMathExpression(_ip, _conf, eval_, numExpr_);
            ExtractObject.checkNullPointer(_conf, obj_);
            return obj_;
        } else {
            _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            if (_element.hasAttribute(IS_STRING_CONST_ATTRIBUTE)){
                if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                    obj_ = null;
                } else {
                    obj_ = expression_;
                }
                return obj_;
            } else if (_element.hasAttribute(IS_CHAR_CONST_ATTRIBUTE)){
                if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                    obj_ = null;
                } else {
                    obj_ = ExtractObject.getChar(_conf, expression_);
                }
                return obj_;
            } else if (_element.hasAttribute(IS_BOOL_CONST_ATTRIBUTE)){
                if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                    obj_ = null;
                } else {
                    obj_ = Boolean.parseBoolean(expression_);
                }
                return obj_;
            } else if (StringList.isNumber(expression_)) {
                obj_ = ExtractObject.instanceByString(_conf, long.class, expression_);
                return obj_;
            } else if (expression_.startsWith(INSTANTIATE_PREFIX)){
                return ElUtil.processEl(expression_, 0, _conf.toContextEl()).getObject();
            } else {
                return ElUtil.processEl(expression_, 0, _conf.toContextEl()).getObject();
            }
        }
    }
    static VariableInformation tryToGetVariableInformation(Configuration _conf, ImportingPage _ip,
            Element _element) {
        String className_ = _element.getAttribute(ATTRIBUTE_CLASS_NAME);
        String numExpr_ = _element.getAttribute(NUMBER_EXPRESSION);
        Object obj_ = null;
        boolean useStruct_ = false;
        Struct struct_ = null;
        Class<?> cl_ = null;
        String expression_ = _element.getAttribute(EXPRESSION_ATTRIBUTE);
        if (!numExpr_.isEmpty()) {
            expression_ = numExpr_;
            String evalBool_ = _element.getAttribute(EVALUATE_BOOLEAN);
            boolean eval_ = Boolean.parseBoolean(evalBool_);
            _ip.setProcessingAttribute(NUMBER_EXPRESSION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            obj_ = ExtractObject.evaluateMathExpression(_ip, _conf, eval_, numExpr_);
            if (className_.isEmpty()) {
                ExtractObject.checkNullPointer(_conf, obj_);
                cl_ = obj_.getClass();
                className_ = cl_.getName();
            } else {
                cl_ = ExtractObject.classForName(_conf, 0, className_);
            }
        } else {
            if (className_.isEmpty()) {
                _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                if (_element.hasAttribute(IS_STRING_CONST_ATTRIBUTE)){
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        cl_ = Object.class;
                        obj_ = null;
                    } else {
                        cl_ = String.class;
                        obj_ = expression_;
                    }
                    className_ = cl_.getName();
                } else if (_element.hasAttribute(IS_CHAR_CONST_ATTRIBUTE)){
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        cl_ = Object.class;
                        obj_ = null;
                    } else {
                        cl_ = Character.class;
                        obj_ = ExtractObject.getChar(_conf, expression_);
                    }
                    className_ = cl_.getName();
                } else if (_element.hasAttribute(IS_BOOL_CONST_ATTRIBUTE)){
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        cl_ = Object.class;
                        obj_ = null;
                    } else {
                        cl_ = Boolean.class;
                        obj_ = Boolean.parseBoolean(expression_);
                    }
                    className_ = cl_.getName();
                } else if (StringList.isNumber(expression_)) {
                    cl_ = long.class;
                    className_ = cl_.getName();
                    obj_ = ExtractObject.instanceByString(_conf, long.class, expression_);
                } else if (expression_.startsWith(INSTANTIATE_PREFIX)){
                    Argument a_ = ElUtil.processEl(expression_, 0, _conf.toContextEl());
                    struct_ = a_.getStruct();
                    useStruct_ = true;
                    cl_ = Object.class;
                    className_ = cl_.getName();
                } else {
                    cl_ = Object.class;
                    className_ = cl_.getName();
                    Argument a_ = ElUtil.processEl(expression_, 0, _conf.toContextEl());
                    struct_ = a_.getStruct();
                    useStruct_ = true;
                }
            } else {
                if (_element.hasAttribute(IS_STRING_CONST_ATTRIBUTE)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        obj_ = null;
                    } else {
                        obj_ = expression_;
                    }
                } else if (_element.hasAttribute(IS_CHAR_CONST_ATTRIBUTE)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        obj_ = null;
                    } else {
                        obj_ = ExtractObject.getChar(_conf, expression_);
                    }
                } else if (_element.hasAttribute(IS_BOOL_CONST_ATTRIBUTE)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        obj_ = null;
                    } else {
                        obj_ = Boolean.parseBoolean(expression_);
                    }
                } else if (StringList.isNumber(expression_)) {
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                    _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    obj_ = ExtractObject.instanceByString(_conf, cl_, expression_);
                } else if (expression_.startsWith(INSTANTIATE_PREFIX)){
                    Argument a_ = ElUtil.processEl(expression_, 0, _conf.toContextEl());
                    struct_ = a_.getStruct();
                    useStruct_ = true;
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                } else {
                    _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    Argument a_ = ElUtil.processEl(expression_, 0, _conf.toContextEl());
                    struct_ = a_.getStruct();
                    useStruct_ = true;
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                }
            }
        }
        VariableInformation vi_ = new VariableInformation();
        vi_.setClassRef(cl_);
        if (useStruct_) {
            vi_.setStruct(struct_);
        } else {
            vi_.setElement(obj_);
        }
        return vi_;
    }
    static LocalVariable tryToCreateVariable(Configuration _conf, ImportingPage _ip, String _className, Struct _object) {
        ClassArgumentMatching clMatch_ = new ClassArgumentMatching(_className);
        if (clMatch_.isPrimitive()) {
            _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            if (_object == null || _object.isNull()) {
                throw new NotPrimitivableException(clMatch_.getName()+RETURN_LINE+_conf.joinPages());
            }
            LocalVariable loc_ = new LocalVariable();
            loc_.setClassName(ConstClasses.resolve(_className));
            try {
                if (clMatch_.getClazz() == int.class) {
                    loc_.setElement((Integer)_object.getInstance());
                } else if (clMatch_.getClazz() == long.class) {
                    loc_.setElement((Long)_object.getInstance());
                } else if (clMatch_.getClazz() == byte.class) {
                    loc_.setElement((Byte)_object.getInstance());
                } else if (clMatch_.getClazz() == short.class) {
                    loc_.setElement((Short)_object.getInstance());
                } else if (clMatch_.getClazz() == float.class) {
                    loc_.setElement((Float)_object.getInstance());
                } else if (clMatch_.getClazz() == double.class) {
                    loc_.setElement((Double)_object.getInstance());
                } else if (clMatch_.getClazz() == char.class) {
                    loc_.setElement((Character)_object.getInstance());
                } else {
                    loc_.setElement((Boolean)_object.getInstance());
                }
            } catch (ClassCastException _0) {
                throw new DynamicCastClassException(_object.getClass().getName()+SPACE+_className+RETURN_LINE+_conf.joinPages());
            }
            return loc_;
        }
        if (_object == null) {
            LocalVariable loc_ = new LocalVariable();
            loc_.setClassName(ConstClasses.resolve(_className));
            return loc_;
        }
        String param_ = _className;
        String arg_ = _object.getClassName();
        param_ = PrimitiveTypeUtil.getPrettyArrayClass(param_);
        if (PrimitiveTypeUtil.canBeUseAsArgument(param_, arg_, _conf.toContextEl().getClasses())) {
            LocalVariable loc_ = new LocalVariable();
            loc_.setClassName(ConstClasses.resolve(_className));
            loc_.setStruct(_object);
            return loc_;
        }
        throw new NotCastableException(_object.getClass().getName()+SPACE+_className+RETURN_LINE+_conf.joinPages());
    }
    static void checkClass(Configuration _conf, ImportingPage _ip, Class<?> _class, Object _object) {
        if (_class.isPrimitive()) {
            _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            if (_object == null) {
                throw new NotPrimitivableException(_class.getName()+RETURN_LINE+_conf.joinPages());
            }
            try {
                if (_class == int.class) {
                    ((Integer)_object).getClass();
                } else if (_class == long.class) {
                    ((Long)_object).getClass();
                } else if (_class == byte.class) {
                    ((Byte)_object).getClass();
                } else if (_class == short.class) {
                    ((Short)_object).getClass();
                } else if (_class == float.class) {
                    ((Float)_object).getClass();
                } else if (_class == double.class) {
                    ((Double)_object).getClass();
                } else if (_class == char.class) {
                    ((Character)_object).getClass();
                } else {
                    ((Boolean)_object).getClass();
                }
            } catch (ClassCastException _0) {
                throw new DynamicCastClassException(_object.getClass().getName()+SPACE+_class.getName()+RETURN_LINE+_conf.joinPages());
            }
            return;
        }
        if (_object == null) {
            return;
        }
        if (_class.isInstance(_object)) {
            return;
        }
        throw new NotCastableException(_object.getClass().getName()+SPACE+_class.getName()+RETURN_LINE+_conf.joinPages());
    }
    static void checkSyntax(Configuration _conf,Document _doc, String _html) {
        Node root_ = _doc.getDocumentElement();
        Node en_ = root_;
        int indexGlobal_ = _html.indexOf(LT_BEGIN_TAG)+1;
        CustList<StringList> vars_ = new CustList<StringList>();
        StringList catchVars_ = new StringList();
        ObjectMap<NodeAttribute, NatTreeMap<Integer, Integer>> infos_;
        infos_ = new ObjectMap<NodeAttribute, NatTreeMap<Integer, Integer>>();
        String prefix_ = _conf.getLastPage().getPrefix();
        while (true) {
            _conf.getLastPage().setProcessingNode(en_);
            if (StringList.quickEq(en_.getNodeName(), prefix_+FOR_BLOCK_TAG)) {
                StringList varsLoc_ = checkForLoop(_conf, (Element) en_, _html);
                for (StringList l: vars_) {
                    for (String v: l) {
                        if (varsLoc_.containsStr(v)) {
                            CustList<StringList> alls_ = new CustList<StringList>();
                            for (StringList g: vars_) {
                                alls_.add(g);
                            }
                            throw new AlreadyDefinedVarException(alls_+RETURN_LINE+v+RETURN_LINE+_conf.joinPages());
                        }
                    }
                }
                if (en_.hasChildNodes()) {
                    vars_.add(varsLoc_);
                }
            } else if (StringList.quickEq(en_.getNodeName(), prefix_+SELECT_TAG)) {
                if (((Element) en_).getAttribute(ATTRIBUTE_MAP).isEmpty()) {
                    if (((Element) en_).getAttribute(ATTRIBUTE_LIST).isEmpty()) {
                        _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
                        _conf.getLastPage().setOffset(0);
                        _conf.getLastPage().setLookForAttrValue(false);
                        throw new BadSelectException(_conf.joinPages());
                    }
                }
            } else if (ExtractCondition.isContentOfConditionNode(_conf, en_)) {
                Node prev_ = en_.getPreviousSibling();
                boolean existIf_ = false;
                while (prev_ != null) {
                    if (prev_ instanceof Text && prev_.getTextContent().trim().isEmpty()) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    if (prev_ instanceof Comment) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    if (ExtractCondition.isContentOfConditionNode(_conf, prev_)) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    existIf_ = ExtractCondition.isBeginOfConditionNode(_conf, prev_);
                    break;
                }
                if (!existIf_) {
                    throw new BadElseIfException(_conf.joinPages());
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+ELSE_BLOCK_TAG)) {
                Node prev_ = en_.getPreviousSibling();
                boolean existIf_ = false;
                while (prev_ != null) {
                    if (prev_ instanceof Text && prev_.getTextContent().trim().isEmpty()) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    if (prev_ instanceof Comment) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    if (ExtractCondition.isContentOfConditionNode(_conf, prev_)) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    existIf_ = ExtractCondition.isBeginOfConditionNode(_conf, prev_);
                    break;
                }
                if (!existIf_) {
                    throw new BadElseException(_conf.joinPages());
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+TRY_TAG)) {
                Node next_ = en_.getNextSibling();
                boolean existCatch_ = false;
                while (next_ != null) {
                    if (next_ instanceof Text && next_.getTextContent().trim().isEmpty()) {
                        next_ = next_.getNextSibling();
                        continue;
                    }
                    if (next_ instanceof Comment) {
                        next_ = next_.getNextSibling();
                        continue;
                    }
                    if (StringList.quickEq(next_.getNodeName(),prefix_+TAG_FINALLY)) {
                        existCatch_ = true;
                        break;
                    }
                    existCatch_ = StringList.quickEq(next_.getNodeName(),prefix_+CATCH_TAG);
                    break;
                }
                if (!existCatch_) {
                    throw new BadCatchException(_conf.joinPages());
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+CATCH_TAG)) {
                String className_ = ((Element)en_).getAttribute(ATTRIBUTE_CLASS_NAME);
                _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                _conf.getLastPage().setOffset(0);
                _conf.getLastPage().setLookForAttrValue(false);
                Class<?> class_ = ExtractObject.classForName(_conf, 0, className_);
                if (!Throwable.class.isAssignableFrom(class_)) {
                    throw new DynamicCastClassException(class_+RETURN_LINE+_conf.joinPages());
                }
                String var_ = ((Element)en_).getAttribute(ATTRIBUTE_VAR);
                if (!StringList.isWord(var_)) {
                    throw new BadVariableNameException(var_, _conf.joinPages(), ATTRIBUTE_VAR);
                }
                Node prev_ = en_.getPreviousSibling();
                boolean existTry_ = false;
                while (prev_ != null) {
                    if (prev_ instanceof Text && prev_.getTextContent().trim().isEmpty()) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    if (prev_ instanceof Comment) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    if (StringList.quickEq(prev_.getNodeName(),prefix_+CATCH_TAG)) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    existTry_ = StringList.quickEq(prev_.getNodeName(),prefix_+TRY_TAG);
                    break;
                }
                if (!existTry_) {
                    throw new BadTryException(_conf.joinPages());
                }
                for (String v: catchVars_) {
                    if (StringList.quickEq(var_, v)) {
                        CustList<StringList> alls_ = new CustList<StringList>();
                        for (StringList g: vars_) {
                            alls_.add(g);
                        }
                        throw new AlreadyDefinedVarException(alls_+RETURN_LINE+v+RETURN_LINE+_conf.joinPages());
                    }
                }
                if (en_.hasChildNodes()) {
                    catchVars_.add(var_);
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+TAG_FINALLY)) {
                if (en_.getFirstChild() == null) {
                    throw new BadTryException(_conf.joinPages());
                }
                Node prev_ = en_.getPreviousSibling();
                boolean existTry_ = false;
                while (prev_ != null) {
                    if (prev_ instanceof Text && prev_.getTextContent().trim().isEmpty()) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    if (prev_ instanceof Comment) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    if (StringList.quickEq(prev_.getNodeName(),prefix_+CATCH_TAG)) {
                        prev_ = prev_.getPreviousSibling();
                        continue;
                    }
                    existTry_ = StringList.quickEq(prev_.getNodeName(),prefix_+TRY_TAG);
                    break;
                }
                if (!existTry_) {
                    throw new BadTryException(_conf.joinPages());
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+BREAK_TAG)) {
                Node parent_ = en_.getParentNode();
                boolean ok_ = false;
                while (parent_ != null) {
                    if (StringList.quickEq(parent_.getNodeName(),prefix_+FOR_BLOCK_TAG)) {
                        ok_ = true;
                        break;
                    }
                    if (StringList.quickEq(parent_.getNodeName(),prefix_+WHILE_BLOCK_TAG)) {
                        ok_ = true;
                        break;
                    }
                    if (StringList.quickEq(parent_.getNodeName(),prefix_+TAG_DO)) {
                        ok_ = true;
                        break;
                    }
                    if (StringList.quickEq(parent_.getNodeName(),prefix_+TAG_SWITCH)) {
                        ok_ = true;
                        break;
                    }
                    parent_ = parent_.getParentNode();
                }
                if (!ok_) {
                    throw new BadTagBreakException(_conf.joinPages());
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+CONTINUE_TAG)) {
                Node parent_ = en_.getParentNode();
                boolean ok_ = false;
                while (parent_ != null) {
                    if (StringList.quickEq(parent_.getNodeName(),prefix_+FOR_BLOCK_TAG)) {
                        ok_ = true;
                        break;
                    }
                    if (StringList.quickEq(parent_.getNodeName(),prefix_+WHILE_BLOCK_TAG)) {
                        ok_ = true;
                        break;
                    }
                    if (StringList.quickEq(parent_.getNodeName(),prefix_+TAG_DO)) {
                        ok_ = true;
                        break;
                    }
                    parent_ = parent_.getParentNode();
                }
                if (!ok_) {
                    throw new BadTagContinueException(_conf.joinPages());
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+TAG_SWITCH)) {
                Node first_ = en_.getFirstChild();
                while (first_ != null) {
                    if (first_ instanceof Text) {
                        if (first_.getTextContent().trim().isEmpty()) {
                            first_ = first_.getNextSibling();
                            continue;
                        }
                        throw new BadSwitchException(_conf.joinPages());
                    }
                    if (first_ instanceof Comment) {
                        first_ = first_.getNextSibling();
                        continue;
                    }
                    Element elt_ = (Element) first_;
                    if (StringList.quickEq(elt_.getNodeName(),prefix_+TAG_CASE)) {
                        first_ = first_.getNextSibling();
                        continue;
                    }
                    if (StringList.quickEq(elt_.getNodeName(),prefix_+TAG_DEFAULT)) {
                        first_ = first_.getNextSibling();
                        continue;
                    }
                    throw new BadSwitchException(_conf.joinPages());
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+TAG_CASE)) {
                if (en_.getParentNode() == null || !StringList.quickEq(en_.getParentNode().getNodeName(), prefix_+TAG_SWITCH)) {
                    throw new BadCaseException(_conf.joinPages());
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+TAG_DEFAULT)) {
                if (en_.getParentNode() == null || !StringList.quickEq(en_.getParentNode().getNodeName(), prefix_+TAG_SWITCH)) {
                    throw new BadDefaultException(_conf.joinPages());
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+WHILE_BLOCK_TAG)) {
                if (en_.getFirstChild() != null) {
                    Node prev_ = en_.getPreviousSibling();
                    boolean existDo_ = false;
                    while (prev_ != null) {
                        if (prev_ instanceof Text && prev_.getTextContent().trim().isEmpty()) {
                            prev_ = prev_.getPreviousSibling();
                            continue;
                        }
                        if (prev_ instanceof Comment) {
                            prev_ = prev_.getPreviousSibling();
                            continue;
                        }
                        existDo_ = StringList.quickEq(prev_.getNodeName(),prefix_+TAG_DO);
                        break;
                    }
                    if (existDo_) {
                        throw new BadLoopException(_conf.joinPages());
                    }
                }
            } else if (StringList.quickEq(en_.getNodeName(),prefix_+TAG_DO)) {
                Node next_ = en_.getNextSibling();
                boolean existWhile_ = false;
                while (next_ != null) {
                    if (next_ instanceof Text && next_.getTextContent().trim().isEmpty()) {
                        next_ = next_.getNextSibling();
                        continue;
                    }
                    if (next_ instanceof Comment) {
                        next_ = next_.getNextSibling();
                        continue;
                    }
                    existWhile_ = StringList.quickEq(next_.getNodeName(),prefix_+WHILE_BLOCK_TAG);
                    break;
                }
                if (!existWhile_) {
                    throw new BadLoopException(_conf.joinPages());
                }
            }
            if (en_ instanceof Element) {
                int endHeader_ = _html.indexOf(GT_TAG, indexGlobal_);
                int beginHeader_ = indexGlobal_ + en_.getNodeName().length();
                StringMap<AttributePart> attr_;
                attr_ = getAttributes(_html, beginHeader_, endHeader_);
                for (EntryCust<String, AttributePart> e: attr_.entryList()) {
                    NodeAttribute nodeAttr_ = new NodeAttribute();
                    nodeAttr_.setNode(en_);
                    nodeAttr_.setAttribue(e.getKey());
                    infos_.put(nodeAttr_, getIndexesSpecChars(_html, true, e.getValue(), indexGlobal_));
                }
                indexGlobal_ = endHeader_;
            } else if (en_ instanceof Text) {
                int endHeader_ = _html.indexOf(LT_BEGIN_TAG, indexGlobal_);
                NodeAttribute nodeAttr_ = new NodeAttribute();
                nodeAttr_.setNode(en_);
                nodeAttr_.setAttribue(EMPTY_STRING);
                AttributePart attrPart_ = new AttributePart();
                attrPart_.setBegin(indexGlobal_);
                attrPart_.setEnd(endHeader_);
                infos_.put(nodeAttr_, getIndexesSpecChars(_html, false, attrPart_, indexGlobal_));
                indexGlobal_ = endHeader_;
            } else {
                indexGlobal_ = _html.indexOf(END_COMMENT, indexGlobal_+NEXT_COMMENT.length())+END_COMMENT.length()-1;
            }
            Node n_ = en_.getFirstChild();
            if (n_ != null) {
                indexGlobal_ = indexOfBeginNode(n_, _html, indexGlobal_);
                en_ = n_;
                continue;
            }
            if (en_ == root_) {
                _conf.getLastPage().setEncodedChars(infos_);
                return;
            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                indexGlobal_ = indexOfBeginNode(n_, _html, indexGlobal_);
                en_ = n_;
                continue;
            }
            n_ = en_.getParentNode();
            if (StringList.quickEq(n_.getNodeName(), prefix_+FOR_BLOCK_TAG)) {
                vars_.removeLast();
            }
            if (StringList.quickEq(n_.getNodeName(), prefix_+CATCH_TAG)) {
                catchVars_.removeLast();
            }
            if (n_ == root_) {
                _conf.getLastPage().setEncodedChars(infos_);
                return;
            }
            Node next_ = n_.getNextSibling();
            while (next_ == null) {
                Node par_ = n_.getParentNode();
                if (StringList.quickEq(par_.getNodeName(), prefix_+FOR_BLOCK_TAG)) {
                    vars_.removeLast();
                }
                if (StringList.quickEq(par_.getNodeName(), prefix_+CATCH_TAG)) {
                    catchVars_.removeLast();
                }
                if (par_ == root_) {
                    break;
                }
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (next_ == null) {
                _conf.getLastPage().setEncodedChars(infos_);
                return;
            }
            indexGlobal_ = indexOfBeginNode(next_, _html, indexGlobal_);
            en_ = next_;
        }
    }

    static void setEscapedChars(Configuration _conf,Document _doc, String _html) {
        Node root_ = _doc.getDocumentElement();
        Node en_ = root_;
        int indexGlobal_ = _html.indexOf(LT_BEGIN_TAG)+1;
        ObjectMap<NodeAttribute, NatTreeMap<Integer, Integer>> infos_;
        infos_ = new ObjectMap<NodeAttribute, NatTreeMap<Integer, Integer>>();
        while (true) {
            _conf.getLastPage().setProcessingNode(en_);
            if (en_ instanceof Element) {
                int endHeader_ = _html.indexOf(GT_TAG, indexGlobal_);
                int beginHeader_ = indexGlobal_ + en_.getNodeName().length();
                StringMap<AttributePart> attr_;
                attr_ = getAttributes(_html, beginHeader_, endHeader_);
                for (EntryCust<String, AttributePart> e: attr_.entryList()) {
                    NodeAttribute nodeAttr_ = new NodeAttribute();
                    nodeAttr_.setNode(en_);
                    nodeAttr_.setAttribue(e.getKey());
                    infos_.put(nodeAttr_, getIndexesSpecChars(_html, true, e.getValue(), indexGlobal_));
                }
                indexGlobal_ = endHeader_;
            } else if (en_ instanceof Text) {
                int endHeader_ = _html.indexOf(LT_BEGIN_TAG, indexGlobal_);
                NodeAttribute nodeAttr_ = new NodeAttribute();
                nodeAttr_.setNode(en_);
                nodeAttr_.setAttribue(EMPTY_STRING);
                AttributePart attrPart_ = new AttributePart();
                attrPart_.setBegin(indexGlobal_);
                attrPart_.setEnd(endHeader_);
                infos_.put(nodeAttr_, getIndexesSpecChars(_html, false, attrPart_, indexGlobal_));
                indexGlobal_ = endHeader_;
            } else {
                indexGlobal_ = _html.indexOf(END_COMMENT, indexGlobal_+NEXT_COMMENT.length())+END_COMMENT.length()-1;
            }
            Node n_ = en_.getFirstChild();
            if (n_ != null) {
                indexGlobal_ = indexOfBeginNode(n_, _html, indexGlobal_);
                en_ = n_;
                continue;
            }
            if (en_ == root_) {
                _conf.getLastPage().setEncodedChars(infos_);
                return;
            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                indexGlobal_ = indexOfBeginNode(n_, _html, indexGlobal_);
                en_ = n_;
                continue;
            }
            n_ = en_.getParentNode();
            if (n_ == root_) {
                _conf.getLastPage().setEncodedChars(infos_);
                return;
            }
            Node next_ = n_.getNextSibling();
            while (next_ == null) {
                Node par_ = n_.getParentNode();
                if (par_ == root_) {
                    break;
                }
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (next_ == null) {
                _conf.getLastPage().setEncodedChars(infos_);
                return;
            }
            indexGlobal_ = indexOfBeginNode(next_, _html, indexGlobal_);
            en_ = next_;
        }
    }

    private static NatTreeMap<Integer, Integer> getIndexesSpecChars(String _html, boolean _realAttr, AttributePart _att, int _beginNode) {
        int begin_ = _att.getBegin();
        int end_ = _att.getEnd();
        int i_ = begin_;
        int delta_ = 0;
        if (_realAttr) {
            delta_ = begin_ - _beginNode;
        }
        int beginEscaped_ = i_;
        NatTreeMap<Integer, Integer> indexes_;
        indexes_ = new NatTreeMap<Integer, Integer>();
        while (i_ < end_) {
            if (_html.charAt(i_) == ENCODED) {
                beginEscaped_ = i_;
                i_++;
                while (_html.charAt(i_) != END_ESCAPED) {
                    i_++;
                }
                indexes_.put(beginEscaped_ - _beginNode - delta_, i_ - beginEscaped_);
            }
            i_++;
        }
        return indexes_;
    }
    private static StringMap<AttributePart> getAttributes(String _html, int _from, int _to) {
        StringMap<AttributePart> attributes_;
        attributes_ = new StringMap<AttributePart>();
        StringBuilder str_ = new StringBuilder();
        int beginToken_ = _from;
        Character delimiter_ = null;
        for (int i = _from; i < _to; i++) {
            char ch_ = _html.charAt(i);
            if (delimiter_ == null) {
                if (ch_ == APOS) {
                    delimiter_ = ch_;
                    beginToken_ = i + 1;
                } else if (ch_ == QUOT) {
                    delimiter_ = ch_;
                    beginToken_ = i + 1;
                }
            } else {
                if (ch_ == delimiter_) {
                    AttributePart attrPart_ = new AttributePart();
                    attrPart_.setBegin(beginToken_);
                    attrPart_.setEnd(i);
                    attributes_.put(str_.toString(), attrPart_);
                    str_ = new StringBuilder();
                    delimiter_ = null;
                    continue;
                }
            }
            if (delimiter_ == null) {
                if (Character.isWhitespace(ch_) || ch_ == EQUALS) {
                    continue;
                }
                str_.append(ch_);
            }
        }
        return attributes_;
    }
    private static int indexOfBeginNode(Node _node, String _html, int _from) {
        if (_node instanceof Element) {
            return _html.indexOf(LT_BEGIN_TAG+_node.getNodeName(), _from) + 1;
        }
        if (_node instanceof Text) {
            int indexText_ = _html.indexOf(GT_TAG, _from);
            while (true) {
                if (_html.charAt(indexText_ + 1) != LT_BEGIN_TAG) {
                    break;
                }
                indexText_ = _html.indexOf(GT_TAG, indexText_ + 1);
            }
            return indexText_ + 1;
        }
        return _html.indexOf(LT_BEGIN_TAG+GET_ENTRY, _from);
    }
    static StringList checkForLoop(Configuration _conf, Element _node, String _html) {
        StringList vars_ = new StringList();
        _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
        _conf.getLastPage().setOffset(0);
        _conf.getLastPage().setLookForAttrValue(false);
        if (_node.hasAttribute(ATTRIBUTE_LIST)) {
            String varName_ = _node.getAttribute(ATTRIBUTE_VAR);
            if (!StringList.isWord(varName_)) {
                throw new BadVariableNameException(varName_, _conf.joinPages(), ATTRIBUTE_VAR);
            }
            vars_.add(varName_);
            return vars_;
        }
        if (_node.hasAttribute(ATTRIBUTE_MAP)) {
            String key_ = _node.getAttribute(ATTRIBUTE_KEY);
            if (!StringList.isWord(key_)) {
                throw new BadVariableNameException(key_, _conf.joinPages(), ATTRIBUTE_KEY);
            }
            String value_ = _node.getAttribute(ATTRIBUTE_VALUE);
            if (!StringList.isWord(value_)) {
                throw new BadVariableNameException(value_, _conf.joinPages(), ATTRIBUTE_VALUE);
            }
            if (StringList.quickEq(key_,value_)) {
                throw new KeyValueException(key_, _conf.joinPages());
            }
            vars_.add(key_);
            vars_.add(value_);
            return vars_;
        }
        if (_node.hasAttribute(ATTRIBUTE_FROM)) {
            if (_node.hasAttribute(ATTRIBUTE_TO)) {
                if (_node.hasAttribute(ATTRIBUTE_STEP)) {
                    String varName_ = _node.getAttribute(ATTRIBUTE_VAR);
                    if (!StringList.isWord(varName_)) {
                        throw new BadVariableNameException(varName_, _conf.joinPages(), ATTRIBUTE_VAR);
                    }
                    vars_.add(varName_);
                    return vars_;
                }
            }
        }
        throw new BadLoopException(_conf.joinPages());
    }

    private static boolean interpretBrackets(Node _node) {
        Node par_ = _node.getParentNode();
        if (!StringList.quickEq(par_.getNodeName(), TAG_STYLE)) {
            if (!StringList.quickEq(par_.getNodeName(), SCRIPT)) {
                return true;
            }
        }
        Element elt_ = (Element) par_;
        return !elt_.getAttribute(INTERPRET).isEmpty();
    }

    private static void setIndexes(IndexesFormInput _indexes,
            Configuration _conf,
            ImportingPage _ip,
            NumberMap<Long,NatTreeMap<Long,NodeContainer>> _containersMap,
            NatTreeMap<Long,NodeContainer> _containers,
            Element _input, String _name) {
        String name_ = _name;
        if (name_.endsWith(GET_LOC_VAR)) {
            _indexes.setNb(-1);
            return;
        }
        Object obj_;
        Struct currentField_;
        String end_ = EMPTY_STRING;
        long index_ = -1;
        long found_ = -1;
        if (name_.endsWith(GET_ATTRIBUTE)) {
            String oldVar_ = name_.substring(CustList.FIRST_INDEX, name_.length() - GET_ATTRIBUTE.length());
            LoopVariable lv_ = ExtractObject.getCurrentVariable(_conf, 0, _ip.getVars(), oldVar_);
            if (lv_.getMap() != null) {
                obj_ = lv_.getMap();
            } else if (lv_.getList() != null ){
                obj_ = lv_.getList();
            } else {
                obj_ = lv_.getArray();
            }
            ExtractObject.checkNullPointer(_conf, obj_);
            index_ = lv_.getIndex();
            for (EntryCust<Long, NodeContainer> e: _containers.entryList()) {
                if (e.getValue().getObject() != obj_) {
                    continue;
                }
                if (e.getValue().getIndex() != index_) {
                    continue;
                }
                found_ = e.getKey();
                break;
            }
            currentField_ = lv_.getStruct();
            end_ = lv_.getExtendedExpression();
        } else {
            int len_ = name_.length();
            int i_ = len_ - 1;
            while (i_ >= 0) {
                if (!isWordCharOrPar(name_.charAt(i_))) {
                    break;
                }
                i_--;
            }
            if (i_ >= 0) {
                String begin_ = name_.substring(CustList.FIRST_INDEX, i_ + 1);
                if (!begin_.endsWith(GET_ATTRIBUTE)) {
                    if (!begin_.endsWith(GET_LOC_VAR)) {
                        begin_ = name_.substring(CustList.FIRST_INDEX, i_);
                    }
                }
                obj_ = ElUtil.processEl(begin_, 0, _conf.toContextEl()).getObject();
                _ip.addToOffset(begin_.length());
                end_ = name_.substring(i_ + 1);
            } else {
                obj_ = getBean(_conf, _ip.getBeanName());
                end_ = name_;
            }
            ExtractObject.checkNullPointer(_conf, obj_);
            for (EntryCust<Long, NodeContainer> e: _containers.entryList()) {
                if (e.getValue().getObject() != obj_) {
                    continue;
                }
                if (!StringList.quickEq(e.getValue().getLastToken(), end_)) {
                    continue;
                }
                found_ = e.getKey();
                break;
            }
            Object current_ = _ip.getGlobalArgument().getObject();
            _ip.setGlobalArgumentObj(obj_);
            currentField_ = ElUtil.processEl(end_, 0, _conf.toContextEl()).getStruct();
            _ip.setGlobalArgumentObj(current_);
        }
        if (found_ == -1) {
            long currentInput_ = _indexes.getInput();
            NodeContainer nodeCont_ = new NodeContainer(name_);
            nodeCont_.setEnabled(true);
            nodeCont_.setLastToken(end_);
            nodeCont_.setIndex(index_);
            nodeCont_.setTypedStruct(currentField_);
            nodeCont_.setBeanName(_ip.getBeanName());
            nodeCont_.setObject(obj_);
            NodeInformations nodeInfos_ = nodeCont_.getNodeInformation();
            String id_ = _input.getAttribute(ATTRIBUTE_ID);
            if (id_.isEmpty()) {
                id_ = _input.getAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID);
            }
            String type_ = _input.getAttribute(ATTRIBUTE_TYPE);
            String class_ = _input.getAttribute(_conf.getPrefix()+ATTRIBUTE_CLASS_NAME);
            if (class_.isEmpty()) {
                if (StringList.quickEq(type_,NUMBER)) {
                    class_= Long.class.getName();
                }
                if (StringList.quickEq(type_,RANGE)) {
                    class_= Long.class.getName();
                }
                if (StringList.quickEq(type_,RADIO)) {
                    class_= Long.class.getName();
                }
                if (StringList.quickEq(type_,TEXT)) {
                    class_= String.class.getName();
                }
                if (StringList.quickEq(type_,CHECKBOX)) {
                    class_= Boolean.class.getName();
                }
                if (StringList.quickEq(_input.getNodeName(), SELECT_TAG)) {
                    type_ = SELECT_TAG;
                    if (_input.hasAttribute(ATTRIBUTE_MULTIPLE)) {
                        class_ = Object.class.getName();
                        class_ = CustList.class.getName()+BEG_TEMP+class_+END_TEMP;
                    }
                }
                if (StringList.quickEq(_input.getNodeName(), TEXT_AREA)) {
                    type_ = TEXT_AREA;
                    class_ = String.class.getName();
                }
            } else {
                if (StringList.quickEq(_input.getNodeName(), SELECT_TAG)) {
                    type_ = SELECT_TAG;
                    if (_input.hasAttribute(ATTRIBUTE_MULTIPLE)) {
                        StringList params_ = Templates.getTypesByBases(class_, Listable.class.getName(), _conf.toContextEl().getClasses());
                        if (params_ == null) {
                            class_ = CustList.class.getName()+BEG_TEMP+class_+END_TEMP;
                        }
                    }
                }
                if (StringList.quickEq(_input.getNodeName(), TEXT_AREA)) {
                    type_ = TEXT_AREA;
                }
            }
            nodeInfos_.setType(type_);
            nodeInfos_.setValidator(_input.getAttribute(_conf.getPrefix()+ATTRIBUTE_VALIDATOR));
            nodeInfos_.setId(id_);
            nodeInfos_.setInputClass(class_);
            nodeInfos_.setVarMethod(_input.getAttribute(_conf.getPrefix()+VAR_METHOD));
            nodeInfos_.setChanging(_input.getAttribute(_conf.getPrefix()+ATTRIBUTE_VALUE_CHANGE_EVENT));
            _containers.put(currentInput_, nodeCont_);
            _indexes.setNb(currentInput_);
            currentInput_++;
            _indexes.setInput(currentInput_);
        } else {
            _indexes.setNb(found_);
        }
    }

    private static boolean isWordCharOrPar(char _char) {
        if (StringList.isWordChar(_char)) {
            return true;
        }
        if (_char == LEFT_PAR_CHAR) {
            return true;
        }
        if (_char == RIGHT_PAR_CHAR) {
            return true;
        }
        return false;
    }

    private static void processAttributes(Configuration _conf, String _loc, StringMap< String> _files,
                ImportingPage _ip,Document _doc, Element _tag,
                IndexesFormInput _indexes,
                NumberMap<Long,NatTreeMap<Long,NodeContainer>> _containersMap,
                NatTreeMap<Long,NodeContainer> _containers,
            String... _resourcesFolder) {
        String prefixWrite_ = _conf.getPrefix();
        String beanName_ = _ip.getBeanName();
        StringList attributesNames_ = new StringList();
        StringList allAttributesNames_ = new StringList();
        NamedNodeMap mapAttr_ = _tag.getAttributes();
        int nbAttrs_ = mapAttr_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            attributesNames_.add(mapAttr_.item(i).getNodeName());
        }
        allAttributesNames_.addAllElts(attributesNames_);
        attributesNames_.removeAllString(ATTRIBUTE_ID);
        String id_ = _tag.getAttribute(ATTRIBUTE_ID);
        if (!id_.isEmpty()) {
            _ip.setProcessingAttribute(ATTRIBUTE_ID);
            id_ = interpretPartiallyIds(_conf, _ip, id_);
            _tag.setAttribute(ATTRIBUTE_ID, id_);
        }
        attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_GROUP_ID);
        String groupId_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID);
        if (!groupId_.isEmpty()) {
            _ip.setProcessingAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID);
            groupId_ = interpretPartiallyIds(_conf, _ip, groupId_);
            _tag.setAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID, groupId_);
        }
        if (StringList.quickEq(_tag.getNodeName(),prefixWrite_+SUBMIT_BLOCK_TAG) && !prefixWrite_.isEmpty()) {
            attributesNames_.removeAllString(ATTRIBUTE_VALUE_SUBMIT);
            String value_ = _tag.getAttribute(ATTRIBUTE_VALUE_SUBMIT);
            StringList elts_ = StringList.splitStrings(value_, COMMA);
            String var_ = elts_.first();
            String fileName_ = ExtractObject.getProperty(_conf, var_);
            if (fileName_ == null) {
                fileName_ = var_;
            }
            StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
            _ip.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
            _ip.setOffset(var_.length()+1);
            _ip.setLookForAttrValue(true);
            String key_ = elts_.last();
            String preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
            preformatted_ = XmlParser.transformSpecialChars(preformatted_, _tag.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
            _tag.removeAttribute(ATTRIBUTE_VALUE_SUBMIT);
            _tag.removeAttribute(ATTRIBUTE_ESCAPED_EAMP);
            IdList<Object> objects_ = new IdList<Object>();
            int i_ = CustList.FIRST_INDEX;
            while (_tag.hasAttribute(TAG_PARAM+i_)) {
                attributesNames_.removeAllString(TAG_PARAM+i_);
                String attribute_ = _tag.getAttribute(TAG_PARAM+i_);
                if (attribute_.startsWith(CALL_METHOD)) {
                    _ip.setProcessingAttribute(TAG_PARAM+i_);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(1);
                    objects_.add(ElUtil.processEl(attribute_, 1, _conf.toContextEl()).getObject());
                } else {
                    objects_.add(attribute_);
                }
                _tag.removeAttribute(TAG_PARAM+i_);
                i_++;
            }
            attributesNames_.removeAllString(ATTRIBUTE_VALUE);
            attributesNames_.removeAllString(ATTRIBUTE_TYPE);
            _tag.setAttribute(ATTRIBUTE_VALUE, StringList.simpleFormat(preformatted_, objects_.toArray()));
            _tag.setAttribute(ATTRIBUTE_TYPE, SUBMIT_TYPE);
            _doc.renameNode(_tag, null, INPUT_TAG);
        }
        if (StringList.quickEq(_tag.getNodeName(),prefixWrite_+TAG_A) && !prefixWrite_.isEmpty()) {
            attributesNames_.removeAllString(ATTRIBUTE_VALUE);
            String value_ = _tag.getAttribute(ATTRIBUTE_VALUE);
            StringList elts_ = StringList.splitStrings(value_, COMMA);
            String var_ = elts_.first();
            String fileName_ = ExtractObject.getProperty(_conf, var_);
            if (fileName_ == null) {
                fileName_ = var_;
            }
            StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
            _ip.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
            _ip.setOffset(var_.length()+1);
            _ip.setLookForAttrValue(true);
            String key_ = elts_.last();
            String preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
            preformatted_ = XmlParser.transformSpecialChars(preformatted_, _tag.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
            _tag.removeAttribute(ATTRIBUTE_VALUE_SUBMIT);
            _tag.removeAttribute(ATTRIBUTE_VALUE);
            IdList<Object> objects_ = new IdList<Object>();
            int i_ = CustList.FIRST_INDEX;
            while (_tag.hasAttribute(TAG_PARAM+i_)) {
                attributesNames_.removeAllString(TAG_PARAM+i_);
                String attribute_ = _tag.getAttribute(TAG_PARAM+i_);
                if (attribute_.startsWith(CALL_METHOD)) {
                    _ip.setProcessingAttribute(TAG_PARAM+i_);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(1);
                    objects_.add(ElUtil.processEl(attribute_, 1, _conf.toContextEl()).getObject());
                } else {
                    objects_.add(attribute_);
                }
                _tag.removeAttribute(TAG_PARAM+i_);
                i_++;
            }
            attributesNames_.removeAllString(ATTRIBUTE_TITLE);
            _tag.setAttribute(ATTRIBUTE_TITLE, StringList.simpleFormat(preformatted_, objects_.toArray()));
            _doc.renameNode(_tag, null, TAG_A);
        }
        if (StringList.quickEq(_tag.getNodeName(),prefixWrite_+TAG_IMG) && !prefixWrite_.isEmpty()) {
            _doc.renameNode(_tag, null, TAG_IMG);
        } else if (StringList.quickEq(_tag.getNodeName(),TAG_IMG)) {
            String src_ = _tag.getAttribute(ATTRIBUTE_SRC);
            if (!src_.isEmpty()) {
                _ip.setProcessingAttribute(ATTRIBUTE_SRC);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                src_ = ExtractObject.formatNumVariables(src_, _conf, _ip, _files);
                String wrap_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_ENCODE_IMG);
                boolean keep_ = wrap_.isEmpty();
                attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_ENCODE_IMG);
                attributesNames_.removeAllString(ATTRIBUTE_SRC);
                String content_ = ExtractFromResources.getContentFile(_conf, _files,src_,_resourcesFolder);
                if (keep_) {
                    _tag.setAttribute(ATTRIBUTE_SRC, content_);
                } else {
                    content_ = ConverterBufferedImage.surroundImage(content_, wrap_);
                    _tag.setAttribute(ATTRIBUTE_SRC, content_);
                }
                _tag.removeAttribute(_conf.getPrefix()+ATTRIBUTE_ENCODE_IMG);
            }
        }
        if (StringList.quickEq(_tag.getNodeName(),TAG_LINK)) {
            NodeList heads_ = _doc.getElementsByTagName(TAG_HEAD);
            attributesNames_.removeAllString(ATTRIBUTE_HREF);
            attributesNames_.removeAllString(ATTRIBUTE_REL);
            String href_ = getCssHref(_tag);
            if (href_ != null && heads_.getLength() == CustList.ONE_ELEMENT){
                Element head_ = (Element) heads_.item(CustList.FIRST_INDEX);
                CustList<Element> children_ = new CustList<Element>();
                for (Element c: XmlParser.childrenElements(head_)) {
                    if (!StringList.quickEq(c.getNodeName(), TAG_STYLE)) {
                        continue;
                    }
                    children_.add(c);
                }
                _ip.setProcessingAttribute(ATTRIBUTE_HREF);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                CustList<String> objects_ = new CustList<String>();
                int i_ = CustList.FIRST_INDEX;
                while (_tag.hasAttribute(TAG_PARAM+i_)) {
                    attributesNames_.removeAllString(TAG_PARAM+i_);
                    String attribute_ = _tag.getAttribute(TAG_PARAM+i_);
                    if (attribute_.startsWith(CALL_METHOD)) {
                        _ip.setProcessingAttribute(TAG_PARAM+i_);
                        _ip.setLookForAttrValue(true);
                        _ip.setOffset(1);
                        objects_.add(ExtractObject.valueOf(_conf,ElUtil.processEl(attribute_, 1, _conf.toContextEl()).getObject()));
                    } else {
                        objects_.add(attribute_);
                    }
                    _tag.removeAttribute(TAG_PARAM+i_);
                    i_++;
                }
                String fileContent_ = ExtractFromResources.getContentFile(_conf, _files, href_, _resourcesFolder);
                if (!objects_.isEmpty()) {
                    fileContent_ = StringList.simpleFormat(fileContent_, objects_.toArray());
                }
                boolean successAdd_ = children_.isEmpty();
                if (!successAdd_) {
                    Element eltStyle_ = children_.last();
                    CustList<Node> chNode_ = XmlParser.childrenNodes(eltStyle_);
                    if (chNode_.isEmpty()) {
                        Text text_ = _doc.createTextNode(fileContent_);
                        eltStyle_.appendChild(text_);
                    } else {
                        Text text_ = (Text) chNode_.last();
                        text_.appendData(fileContent_);
                    }
                } else {
                    Element eltStyle_ = _doc.createElement(TAG_STYLE);
                    Text text_ = _doc.createTextNode(fileContent_);
                    eltStyle_.appendChild(text_);
                    head_.appendChild(eltStyle_);
                }
            }
        }
        if (StringList.quickEq(_tag.getNodeName(),TAG_STYLE)) {
            NodeList links_ = _doc.getElementsByTagName(TAG_LINK);
            int len_ = links_.getLength();
            StringList refs_ = new StringList();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                Element link_ = (Element) links_.item(i);
                String href_ = getCssHref(link_);
                if (href_ != null) {
                    refs_.add(href_);
                }
            }
            StringList filesContents_ = new StringList();
            for (String r: refs_) {
                filesContents_.add(ExtractFromResources.getContentFile(_conf, _files, r, _resourcesFolder));
            }
            CustList<Node> chNode_ = XmlParser.childrenNodes(_tag);
            if (chNode_.isEmpty()) {
                Text text_ = _doc.createTextNode(filesContents_.join(RETURN_LINE));
                _tag.appendChild(text_);
            } else {
                Text text_ = (Text) chNode_.last();
                text_.appendData(filesContents_.join(RETURN_LINE));
            }
        }
        if (StringList.quickEq(_tag.getNodeName(),SCRIPT)) {
            NamedNodeMap map_ = _tag.getAttributes();
            Node href_ = map_.getNamedItem(ATTRIBUTE_HREF);
            if (href_ != null){
                _ip.setProcessingAttribute(ATTRIBUTE_HREF);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                attributesNames_.removeAllString(ATTRIBUTE_HREF);
                attributesNames_.removeAllString(ATTRIBUTE_TYPE);
                String ref_ = href_.getNodeValue();
                ref_ = ExtractObject.formatNumVariables(ref_, _conf, _ip, _files);
                _tag.setAttribute(ATTRIBUTE_TYPE, SCRIPT_TYPE);
                Text txt_ = _doc.createTextNode(ExtractFromResources.getContentFile(_conf, _files,ref_,_resourcesFolder));
                _tag.appendChild(txt_);
            }
        }
        String accessName_ = EMPTY_STRING;
        if (StringList.quickEq(_tag.getNodeName(),INPUT_TAG)) {
            //format name
            attributesNames_.removeAllString(ATTRIBUTE_VALUE);
            attributesNames_.removeAllString(ATTRIBUTE_NAME);
            attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_CLASS_NAME);
            attributesNames_.removeAllString(_conf.getPrefix()+VAR_METHOD);
            String name_ = _tag.getAttribute(ATTRIBUTE_NAME);
            accessName_ = name_;
            if (!name_.isEmpty()) {
                _ip.setProcessingAttribute(ATTRIBUTE_NAME);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                setIndexes(_indexes, _conf, _ip, _containersMap, _containers, _tag, name_);
                if (_indexes.getNb() >= 0) {
                    _tag.setAttribute(NUMBER_INPUT, String.valueOf(_indexes.getNb()));
                }
                attributesNames_.removeAllString(NUMBER_INPUT);
                _tag.setAttribute(ATTRIBUTE_NAME, beanName_+DOT+name_);
            }
        }
        if (StringList.quickEq(_tag.getNodeName(),TEXT_AREA)) {
            //format name
            attributesNames_.removeAllString(ATTRIBUTE_NAME);
            attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_CLASS_NAME);
            attributesNames_.removeAllString(_conf.getPrefix()+VAR_METHOD);
            String name_ = _tag.getAttribute(ATTRIBUTE_NAME);
            if (!name_.isEmpty()) {
                _ip.setProcessingAttribute(ATTRIBUTE_NAME);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                setIndexes(_indexes, _conf, _ip, _containersMap, _containers, _tag, name_);
                if (_indexes.getNb() >= 0) {
                    _tag.setAttribute(NUMBER_INPUT, String.valueOf(_indexes.getNb()));
                }
                attributesNames_.removeAllString(NUMBER_INPUT);
                _tag.setAttribute(ATTRIBUTE_NAME, beanName_+DOT+name_);
            }
        }
        if (_tag.hasAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE)) {
            if (StringList.quickEq(_tag.getNodeName(),INPUT_TAG)) {
                attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
                attributesNames_.removeAllString(ATTRIBUTE_VALUE);
                attributesNames_.removeAllString(CHECKED);
                setValueInput(_conf, _tag);
                _tag.removeAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
            }
            if (StringList.quickEq(_tag.getNodeName(),TEXT_AREA)) {
                attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
                setValueTextArea(_conf, _doc, _tag);
                _tag.removeAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
            }
        }
        if (StringList.quickEq(_tag.getNodeName(),INPUT_TAG)) {
            attributesNames_.removeAllString(ATTRIBUTE_TYPE);
            if (StringList.quickEq(_tag.getAttribute(ATTRIBUTE_TYPE),RADIO)) {
                attributesNames_.removeAllString(CHECKED);
                _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_NAME);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
                Object o_ = null;
                if (!accessName_.isEmpty()) {
                    o_ = ElUtil.processEl(accessName_, 0, _conf.toContextEl()).getObject();
                }
                if (o_ == null) {
                    _tag.removeAttribute(CHECKED);
                } else {
                    String strObj_;
                    if (o_.getClass().isEnum()) {
                        strObj_ = ConverterMethod.getName(o_);
                    } else {
                        strObj_ = ExtractObject.toString(_conf, o_);
                    }
                    if (StringList.quickEq(_tag.getAttribute(ATTRIBUTE_VALUE),strObj_)) {
                        _tag.setAttribute(CHECKED, CHECKED);
                    } else {
                        _tag.removeAttribute(CHECKED);
                    }
                }
            }
        }
        if (StringList.quickEq(_tag.getNodeName(),SPAN_TAG)) {
            String forId_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_FOR);
            if (!forId_.isEmpty()) {
                _ip.setProcessingAttribute(_conf.getPrefix()+ATTRIBUTE_FOR);
                attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_FOR);
                forId_ = interpretPartiallyIds(_conf, _ip, forId_);
                _tag.setAttribute(_conf.getPrefix()+ATTRIBUTE_FOR, forId_);
                if (!_ip.getReadWrite().getRead().hasChildNodes()) {
                    Text txt_ = _doc.createTextNode(SPACE);
                    _tag.appendChild(txt_);
                }
            }
        }
        if (StringList.quickEq(_tag.getNodeName(),TAG_A)) {
            String attr_ = _tag.getAttribute(ATTRIBUTE_HREF);
            if (!attr_.isEmpty()) {
                attributesNames_.removeAllString(ATTRIBUTE_HREF);
                CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                attr_ = format(stacks_, attr_, false);
                _tag.setAttribute(ATTRIBUTE_HREF, attr_);
            }
            attr_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND);
            if (!attr_.isEmpty()) {
                CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                attr_ = format(stacks_, attr_, false);
                _tag.setAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND, attr_);
            }
            String href_ = _tag.getAttribute(ATTRIBUTE_HREF);
            if (href_.startsWith(CALL_METHOD)) {
                _tag.setAttribute(ATTRIBUTE_HREF, CALL_METHOD+beanName_+DOT+href_.substring(1));
            } else {
                href_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND);
                if (href_.startsWith(CALL_METHOD)) {
                    _tag.setAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND, CALL_METHOD+beanName_+DOT+href_.substring(1));
                }
            }
            if (_tag.hasAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND)) {
                _tag.setAttribute(ATTRIBUTE_HREF, EMPTY_STRING);
            }
        }
        if (StringList.quickEq(_tag.getNodeName(),TAG_FORM)) {
            String attr_ = _tag.getAttribute(ATTRIBUTE_ACTION);
            if (!attr_.isEmpty()) {
                CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                attributesNames_.removeAllString(ATTRIBUTE_ACTION);
                attr_ = format(stacks_, attr_, false);
                _tag.setAttribute(ATTRIBUTE_ACTION, attr_);
            }
            attr_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND);
            if (!attr_.isEmpty()) {
                CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                attr_ = format(stacks_, attr_, false);
                _tag.setAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND, attr_);
            }
            attr_ = _tag.getAttribute(ATTRIBUTE_NAME);
            if (!attr_.isEmpty()) {
                CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                attributesNames_.removeAllString(ATTRIBUTE_NAME);
                attr_ = format(stacks_, attr_, true);
                _tag.setAttribute(ATTRIBUTE_NAME, attr_);
            }
            String href_ = _tag.getAttribute(ATTRIBUTE_ACTION);
            if (href_.startsWith(CALL_METHOD)) {
                _tag.setAttribute(ATTRIBUTE_ACTION, CALL_METHOD+beanName_+DOT+href_.substring(1));
            } else {
                href_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND);
                if (href_.startsWith(CALL_METHOD)) {
                    _tag.setAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND, CALL_METHOD+beanName_+DOT+href_.substring(1));
                }
            }
            if (_tag.hasAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND)) {
                _tag.setAttribute(ATTRIBUTE_ACTION, EMPTY_STRING);
            }
        }
        if (!StringList.quickEq(_tag.getNodeName(),prefixWrite_+TR_END_BLOCK_TAG) || prefixWrite_.isEmpty()) {
            for (String a: attributesNames_) {
                String v_ = _tag.getAttribute(a);
                if (!v_.isEmpty()) {
                    if (StringList.quickEq(a, ATTRIBUTE_CLASS)) {
                        CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                        v_ = format(stacks_, v_, false);
                        _ip.setProcessingAttribute(a);
                        _ip.setLookForAttrValue(true);
                        _ip.setOffset(1);
                        _tag.setAttribute(a, v_);
                        evaluateAttribute(_conf,_tag, a);
                    } else {
                        _ip.setProcessingAttribute(a);
                        _ip.setLookForAttrValue(true);
                        _ip.setOffset(0);
                        v_ = ExtractObject.formatNumVariables(v_, _conf, _ip, _files);
                        _tag.setAttribute(a, v_);
                    }
                }
            }
        } else {
            //remove all attributes of tr_end
            for (String a: allAttributesNames_) {
                _tag.removeAttribute(a);
            }
        }
    }

    private static String getCssHref(Element _link) {
        NamedNodeMap map_ = _link.getAttributes();
        if (!StringList.quickEq(_link.getAttribute(ATTRIBUTE_REL),STYLESHEET)) {
            return null;
        }
        Node href_ = map_.getNamedItem(ATTRIBUTE_HREF);
        if (href_ == null){
            return null;
        }
        return href_.getNodeValue();
    }

    private static void setValueTextArea(Configuration _conf, Document _doc, Element _tag) {
        String attribute_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
        _conf.getLastPage().setProcessingAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
        _conf.getLastPage().setLookForAttrValue(true);
        _conf.getLastPage().setOffset(0);
        Object o_ = ElUtil.processEl(attribute_, 0, _conf.toContextEl()).getObject();
        if (o_ == null) {
            o_ = EMPTY_STRING;
        }
        //TODO converter
        Text text_ = _doc.createTextNode(ExtractObject.toString(_conf, o_));
        _tag.appendChild(text_);
    }

    private static void setValueInput(Configuration _conf, Element _tag) {
        String attribute_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
        try {
            //TODO converter
            _tag.setAttribute(ATTRIBUTE_VALUE, String.valueOf(Long.parseLong(attribute_)));
        } catch (NumberFormatException _0) {
            _conf.getLastPage().setProcessingAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
            _conf.getLastPage().setLookForAttrValue(true);
            _conf.getLastPage().setOffset(0);
            Object o_ = ElUtil.processEl(attribute_, 0, _conf.toContextEl()).getObject();
            if (o_ == null) {
                _tag.setAttribute(ATTRIBUTE_VALUE, (String) o_);
            } else if (o_ instanceof Boolean) {
                if ((Boolean) o_) {
                    _tag.setAttribute(CHECKED, CHECKED);
                } else {
                    _tag.removeAttribute(CHECKED);
                }
            } else {
                _tag.setAttribute(ATTRIBUTE_VALUE, ExtractObject.toString(_conf, o_));
            }
        }
    }

    static String interpretPartiallyIds(Configuration _conf, ImportingPage _ip, String _pattern) {
        StringBuilder calculateVariables_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        int len_ = _pattern.length();
        boolean escaped_ = false;
        while (i_ < len_) {
            char curChar_ = _pattern.charAt(i_);
            if (escaped_) {
                if (curChar_ == ESCAPED) {
                    escaped_ = false;
                    calculateVariables_.append(ESCAPED);
                    i_++;
                    continue;
                }
                if (curChar_ == MATH_INTERPRET) {
                    escaped_ = false;
                    calculateVariables_.append(MATH_INTERPRET);
                    i_++;
                    continue;
                }
                throw new BadExpressionLanguageException(_pattern+RETURN_LINE+_conf.joinPages());
            }
            if (curChar_ == ESCAPED) {
                escaped_ = true;
                i_++;
                continue;
            }
            if (curChar_ == MATH_INTERPRET) {
                ContextEl context_ = _conf.toContextEl();
                _ip.setOffset(i_+1);
                Argument arg_ = ElUtil.processEl(_pattern, context_, i_+1, MATH_INTERPRET, MATH_INTERPRET);
                calculateVariables_.append(ExtractObject.valueOf(_conf, arg_.getObject()));
                i_ = context_.getNextIndex();
                continue;
            }
            calculateVariables_.append(curChar_);
            i_++;
        }
        return calculateVariables_.toString();
    }

    static String format(CustList<BlockHtml> _stacks, String _pattern, boolean _emptyTestCase) {
        String ret_ = _pattern;
        if (_emptyTestCase) {
            if (ret_.isEmpty()) {
                return ret_;
            }
        } else {
            if (!ret_.contains(CALL_METHOD)) {
                return ret_;
            }
        }
        for (BlockHtml l: _stacks) {
            if (!(l instanceof LoopHtmlStack)) {
                continue;
            }
            LoopHtmlStack l_ = (LoopHtmlStack) l;
            long index_ = l_.getIndex();
            if (ret_.contains(LEFT_PAR_COMMA)) {
                ret_ = StringList.replace(ret_, LEFT_PAR_COMMA, LEFT_PAR+index_+COMMA);
            } else if (ret_.contains(DOUBLE_COMMA)) {
                ret_ = insertArguments(ret_,index_);
            } else if (ret_.contains(COMMA_RIGHT_PAR)) {
                ret_ = StringList.replace(ret_, COMMA_RIGHT_PAR, COMMA+index_+RIGHT_PAR);
            } else if (ret_.contains(NO_PARAM_METHOD)) {
                ret_ = StringList.replace(ret_, NO_PARAM_METHOD, LEFT_PAR+index_+RIGHT_PAR);
            }
        }
        return ret_;
    }

    static void evaluateAttribute(Configuration _conf, Element _elt, String _attrName) {
        String class_ = _elt.getAttribute(_attrName);
        if (!class_.contains(CALL_METHOD)) {
            return;
        }
        String command_ = class_.substring(class_.indexOf(CALL_METHOD)+1);
        Object returnedObject_ = ElUtil.processEl(command_, 0, _conf.toContextEl()).getObject();
        if (returnedObject_ == null) {
            _elt.removeAttribute(_attrName);
            return;
        }
        _elt.setAttribute(_attrName, ExtractObject.toString(_conf, returnedObject_));
    }

    private static void processOptionsList(Configuration _conf, Document _doc,
            Node _currentModifiedNode, Element _n, String _id, String _groupId, boolean _multiple) {
        String list_ = _n.getAttribute(ATTRIBUTE_LIST);
        String name_ = ((Element) _n).getAttribute(ATTRIBUTE_NAME);
        boolean update_ = ((Element) _n).hasAttribute(ATTRIBUTE_UPDATE);
        String varValue_ = ((Element) _n).getAttribute(ATTRIBUTE_VAR_VALUE);
        String varMethod_ = ((Element) _n).getAttribute(VAR_METHOD);
        IdList<Object> returnedVarValue_ = null;
        if (!varValue_.isEmpty()) {
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR_VALUE);
            _conf.getLastPage().setLookForAttrValue(true);
            _conf.getLastPage().setOffset(0);
            returnedVarValue_ = new IdList<Object>();
            if (!_multiple) {
                returnedVarValue_.add(ElUtil.processEl(varValue_, 0, _conf.toContextEl()).getObject());
            } else {
                Object o_ = ElUtil.processEl(varValue_, 0, _conf.toContextEl()).getObject();
                ExtractObject.addAll(_conf, varValue_.length(), returnedVarValue_, o_);
            }
        }
        _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_LIST);
        _conf.getLastPage().setLookForAttrValue(true);
        _conf.getLastPage().setOffset(0);
        Object li_ = ElUtil.processEl(list_, 0, _conf.toContextEl()).getObject();
        Object extractedList_ = li_;
        String default_ = ((Element) _n).getAttribute(DEFAULT_ATTRIBUTE);
        Element docElementSelect_ = _doc.createElement(SELECT_TAG);
        if (!_id.isEmpty() || !_groupId.isEmpty()) {
            if (!_id.isEmpty()) {
                docElementSelect_.setAttribute(ATTRIBUTE_ID, _id);
            } else {
                docElementSelect_.setAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID, _groupId);
            }
            docElementSelect_.setAttribute(_conf.getPrefix()+ATTRIBUTE_VALIDATOR, ((Element) _n).getAttribute(ATTRIBUTE_VALIDATOR));
        }
        if (_multiple) {
            docElementSelect_.setAttribute(ATTRIBUTE_MULTIPLE, ATTRIBUTE_MULTIPLE);
        }
        if (default_.isEmpty() || returnedVarValue_ != null && update_) {
            checkEnums(_conf, extractedList_);
            Object it_ = ProcessXmlMethod.iterator(_conf.toContextEl(), extractedList_);
            while (ProcessXmlMethod.hasNext(_conf.toContextEl(), it_)) {
                Object o_ = ProcessXmlMethod.next(_conf.toContextEl(), it_);
                Element option_ = _doc.createElement(TAG_OPTION);
                option_.setAttribute(ATTRIBUTE_VALUE, ConverterMethod.getName(o_));
                if (returnedVarValue_ != null) {
                    for (Object a: returnedVarValue_) {
                        if (a == o_) {
                            option_.setAttribute(SELECTED, SELECTED);
                            break;
                        }
                    }
                }
                option_.appendChild(_doc.createTextNode(ExtractObject.toString(_conf, o_)));
                docElementSelect_.appendChild(option_);
            }
        } else {
            if (default_.startsWith(CALL_METHOD)) {
                String command_ = default_.substring(1);
                _conf.getLastPage().setProcessingAttribute(DEFAULT_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(1);
                IdList<Object> defaults_ = new IdList<Object>();
                if (!_multiple) {
                    defaults_.add(ElUtil.processEl(command_, 0, _conf.toContextEl()).getObject());
                } else {
                    li_ = ElUtil.processEl(command_, 0, _conf.toContextEl()).getObject();
                    ExtractObject.addAll(_conf, command_.length(), defaults_, li_);
                }
                checkEnums(_conf, extractedList_);
                _conf.getLastPage().setProcessingAttribute(DEFAULT_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
                checkEnums(_conf, defaults_);
                Object it_ = ProcessXmlMethod.iterator(_conf.toContextEl(), extractedList_);
                while (ProcessXmlMethod.hasNext(_conf.toContextEl(), it_)) {
                    Object o_ = ProcessXmlMethod.next(_conf.toContextEl(), it_);
                    Element option_ = _doc.createElement(TAG_OPTION);
                    String nameEnum_ = ConverterMethod.getName(o_);
                    option_.setAttribute(ATTRIBUTE_VALUE, nameEnum_);
                    for (Object d: defaults_) {
                        if (o_ == d) {
                            option_.setAttribute(SELECTED, SELECTED);
                            break;
                        }
                    }
                    option_.appendChild(_doc.createTextNode(ExtractObject.toString(_conf, o_)));
                    docElementSelect_.appendChild(option_);
                }
            } else {
                StringList names_ = StringList.splitChars(default_, SEP_ENUMS);
                checkEnums(_conf, extractedList_);
                Object it_ = ProcessXmlMethod.iterator(_conf.toContextEl(), extractedList_);
                while (ProcessXmlMethod.hasNext(_conf.toContextEl(), it_)) {
                    Object o_ = ProcessXmlMethod.next(_conf.toContextEl(), it_);
                    Element option_ = _doc.createElement(TAG_OPTION);
                    String enumName_ = ConverterMethod.getName(o_);
                    option_.setAttribute(ATTRIBUTE_VALUE, enumName_);
                    for (String n: names_) {
                        if (StringList.quickEq(enumName_,n)) {
                            option_.setAttribute(SELECTED, SELECTED);
                            break;
                        }
                    }
                    option_.appendChild(_doc.createTextNode(ExtractObject.toString(_conf, o_)));
                    docElementSelect_.appendChild(option_);
                }
            }
        }
        docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
        if (!varMethod_.isEmpty()) {
            docElementSelect_.setAttribute(_conf.getPrefix()+VAR_METHOD, varMethod_);
        }
        docElementSelect_.setAttribute(_conf.getPrefix()+ATTRIBUTE_CLASS_NAME, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
        _currentModifiedNode.appendChild(docElementSelect_);
    }

    private static void checkEnums(Configuration _conf, Object _list) {
        try {
            Object iterator_ = ProcessXmlMethod.iterator(_conf.toContextEl(), _list);
            while (ProcessXmlMethod.hasNext(_conf.toContextEl(), iterator_)) {
                Object element_ = ProcessXmlMethod.next(_conf.toContextEl(), iterator_);
                if (!element_.getClass().isEnum()) {
                    throw new DynamicCastClassException();
                }
            }
        } catch (RuntimeException _0) {
            throw new BadEnumeratingException(_list, _conf.joinPages());
        }
    }
    private static void processOptionsMap(Configuration _conf, Document _doc,
            Node _currentModifiedNode, Node _n, String _map, String _id, String _groupId, boolean _multiple) {
        String name_ = ((Element) _n).getAttribute(ATTRIBUTE_NAME);
        boolean update_ = ((Element)_n).hasAttribute(ATTRIBUTE_UPDATE);
        String varValue_ = ((Element) _n).getAttribute(ATTRIBUTE_VAR_VALUE);
        String varMethod_ = ((Element) _n).getAttribute(VAR_METHOD);
        Object returnedVarValue_ = null;
        if (!varValue_.isEmpty()) {
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR_VALUE);
            _conf.getLastPage().setLookForAttrValue(true);
            _conf.getLastPage().setOffset(0);
            returnedVarValue_ = ElUtil.processEl(varValue_, 0, _conf.toContextEl()).getObject();
        }
        _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_MAP);
        _conf.getLastPage().setLookForAttrValue(true);
        _conf.getLastPage().setOffset(0);
        Object map_ = ElUtil.processEl(_map, 0, _conf.toContextEl()).getObject();
        String default_ = ((Element) _n).getAttribute(DEFAULT_ATTRIBUTE);
        Element docElementSelect_ = _doc.createElement(SELECT_TAG);
        if (!_id.isEmpty() || !_groupId.isEmpty()) {
            if (!_id.isEmpty()) {
                docElementSelect_.setAttribute(ATTRIBUTE_ID, _id);
            } else {
                docElementSelect_.setAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID, _groupId);
            }
            docElementSelect_.setAttribute(_conf.getPrefix()+ATTRIBUTE_VALIDATOR, ((Element) _n).getAttribute(ATTRIBUTE_VALIDATOR));
        }
        if (_multiple) {
            docElementSelect_.setAttribute(ATTRIBUTE_MULTIPLE, ATTRIBUTE_MULTIPLE);
        }
        if (default_.isEmpty() || returnedVarValue_ != null && update_) {
            processOptionsMapEnumName(_conf, map_,
                    _doc, docElementSelect_,
                    returnedVarValue_, _multiple);
        } else {
            if (default_.startsWith(CALL_METHOD)) {
                _conf.getLastPage().setProcessingAttribute(DEFAULT_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(1);
                Object defaultEnum_ = ElUtil.processEl(default_, 1, _conf.toContextEl()).getObject();
                processOptionsMapEnumName(_conf, map_,
                        _doc, docElementSelect_,
                        defaultEnum_, _multiple);
            } else {
                String className_ = ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME);
                processOptionsMapEnum(_conf, map_, default_,
                        _doc, docElementSelect_, className_);
            }
        }
        if (!varMethod_.isEmpty()) {
            docElementSelect_.setAttribute(_conf.getPrefix()+VAR_METHOD, varMethod_);
        }
        docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
        docElementSelect_.setAttribute(_conf.getPrefix()+ATTRIBUTE_CLASS_NAME, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
        _currentModifiedNode.appendChild(docElementSelect_);
    }

    private static void processOptionsMapEnum(Configuration _conf, Object _extractedMap,
            String _default, Document _docSelect, Element _docElementSelect, String _className) {
        boolean isEnumClass_;
        try {
            Class<?> class_ = ConstClasses.classAliasForObjectNameNotInit(_className);
            isEnumClass_ = class_.isEnum();
        } catch (Throwable _0) {
            if (_className.isEmpty()) {
                isEnumClass_ = false;
            } else {
                throw new RuntimeClassNotFoundException(_className+_conf.joinPages());
            }
        }
        StringList names_;
        if (isEnumClass_) {
            names_ = StringList.splitChars(_default, SEP_ENUMS);
        } else {
            names_ = new StringList(_default);
        }
        Object l_ = ExtractObject.entryList(_conf, 0, _extractedMap);
        Object it_ = ProcessXmlMethod.iterator(_conf.toContextEl(), l_);
        while (ProcessXmlMethod.hasNext(_conf.toContextEl(), it_)) {
            Object entry_ = ProcessXmlMethod.next(_conf.toContextEl(), it_);
            ExtractObject.checkNullPointer(_conf, entry_);
            Object o_ = ExtractObject.getKey(_conf, entry_);
            if (o_ == null) {
                continue;
            }
            Element option_ = _docSelect.createElement(TAG_OPTION);
            String cmp_;
            if (o_.getClass().isEnum()) {
                cmp_ = ConverterMethod.getName(o_);
            } else {
                cmp_ = ExtractObject.toString(_conf, o_);
            }
            option_.setAttribute(ATTRIBUTE_VALUE, cmp_);
            for (String n: names_) {
                if (StringList.quickEq(n,cmp_)) {
                    option_.setAttribute(SELECTED, SELECTED);
                    break;
                }
            }
            option_.appendChild(_docSelect.createTextNode(ExtractObject.toString(_conf, ExtractObject.getValue(_conf,entry_))));
            _docElementSelect.appendChild(option_);
        }
    }

    private static void processOptionsMapEnumName(Configuration _conf, Object _extractedMap,
            Document _docSelect, Element _docElementSelect, Object _returnedVarValue,
            boolean _multiple) {
        IdList<Object> obj_ = new IdList<Object>();
        if (_multiple) {
            if (_returnedVarValue != null) {
                ExtractObject.addAll(_conf, 0, obj_, _returnedVarValue);
            }
        } else {
            obj_.add(_returnedVarValue);
        }
        Object l_ = ExtractObject.entryList(_conf, 0, _extractedMap);
        Object it_ = ProcessXmlMethod.iterator(_conf.toContextEl(), l_);
        while (ProcessXmlMethod.hasNext(_conf.toContextEl(), it_)) {
            Object entry_ = ProcessXmlMethod.next(_conf.toContextEl(), it_);
            ExtractObject.checkNullPointer(_conf, entry_);
            Object o_ = ExtractObject.getKey(_conf, entry_);
            if (o_ == null) {
                continue;
            }
            Element option_ = _docSelect.createElement(TAG_OPTION);
            if (o_.getClass().isEnum()) {
                option_.setAttribute(ATTRIBUTE_VALUE, ConverterMethod.getName(o_));
            } else {
                option_.setAttribute(ATTRIBUTE_VALUE, ExtractObject.toString(_conf, o_));
            }
            for (Object o: obj_) {
                if (ExtractObject.eq(_conf, o_, o)) {
                    option_.setAttribute(SELECTED, SELECTED);
                }
            }
            option_.appendChild(_docSelect.createTextNode(ExtractObject.toString(_conf, ExtractObject.getValue(_conf, entry_))));
            _docElementSelect.appendChild(option_);
        }
    }

    private static String insertArguments(String _string, long _arg) {
        StringBuilder str_ = new StringBuilder();
        int index_ = _string.indexOf(DOUBLE_COMMA);
        int len_ = _string.length();
        int i_ = CustList.FIRST_INDEX;
        while (i_ < index_) {
            str_.append(_string.charAt(i_));
            i_++;
        }
        str_.append(COMMA);
        str_.append(_arg);
        str_.append(COMMA);
        i_ += DOUBLE_COMMA.length();
        while (i_ < len_) {
            str_.append(_string.charAt(i_));
            i_++;
        }
        return str_.toString();
    }

    private static NodeAction getNextNodeWrite(Configuration _conf, Node _node, boolean _firstChild) {
        NodeAction na_ = new NodeAction();
        Node n_ = null;
        if (_firstChild) {
            n_ = _node.getFirstChild();
            if (n_ != null && !StringList.quickEq(_node.getNodeName(), TEXT_AREA)) {
                na_.getActions().add(ActionNext.FIRST_CHILD);
                na_.setNode(n_);
                return na_;
            }
        }
        n_ = _node.getNextSibling();
        if (n_ != null) {
            na_.getActions().add(ActionNext.NEXT_SIBLING);
            na_.setNode(n_);
            return na_;
        }
        n_ = _node.getParentNode();
        na_.getActions().add(ActionNext.PARENT_NODE);
        Node next_ = n_.getNextSibling();
        if (next_ != null) {
            na_.getActions().add(ActionNext.NEXT_SIBLING);
        }
        while (next_ == null) {
            Node par_ = n_.getParentNode();
            if (par_ == null) {
                break;
            }
            na_.getActions().add(ActionNext.PARENT_NODE);
            next_ = par_.getNextSibling();
            if (next_ != null) {
                na_.getActions().add(ActionNext.NEXT_SIBLING);
            }
            n_ = par_;
        }
        na_.setNode(next_);
        return na_;
    }

    private static boolean isLoopNode(Configuration _conf, Node _node) {
        String prefix_ = _conf.getLastPage().getPrefix();
        String nodeName_ = _node.getNodeName();
        if (StringList.quickEq(nodeName_, prefix_+FOR_BLOCK_TAG)) {
            return true;
        }
        if (StringList.quickEq(nodeName_, prefix_+WHILE_BLOCK_TAG)) {
            return true;
        }
        if (StringList.quickEq(nodeName_, prefix_+TAG_DO)) {
            return true;
        }
        return false;
    }
    private static boolean isCatchNode(Configuration _conf, Node _node) {
        String prefix_ = _conf.getLastPage().getPrefix();
        String nodeName_ = _node.getNodeName();
        if (StringList.quickEq(nodeName_, prefix_+CATCH_TAG)) {
            return true;
        }
        return false;
    }
    private static boolean isFinallyNode(Configuration _conf, Node _node) {
        String prefix_ = _conf.getLastPage().getPrefix();
        String nodeName_ = _node.getNodeName();
        if (StringList.quickEq(nodeName_, prefix_+TAG_FINALLY)) {
            return true;
        }
        return false;
    }

    private static boolean isTryNode(Configuration _conf, Node _node) {
        String prefix_ = _conf.getLastPage().getPrefix();
        String nodeName_ = _node.getNodeName();
        if (StringList.quickEq(nodeName_, prefix_+TRY_TAG)) {
            return true;
        }
        return false;
    }

    private static boolean isSwitchNode(Configuration _conf, Node _node) {
        String nodeName_ = _node.getNodeName();
        String prefix_ = _conf.getLastPage().getPrefix();
        if (StringList.quickEq(nodeName_, prefix_+TAG_CASE)) {
            return true;
        }
        if (StringList.quickEq(nodeName_, prefix_+TAG_DEFAULT)) {
            return true;
        }
        return false;
    }

    private static boolean isInstructionNode(Configuration _conf, Node _node) {
        if (ExtractCondition.isBeginOfConditionNode(_conf, _node)) {
            return true;
        }
        if (ExtractCondition.isContentOfConditionNode(_conf, _node)) {
            return true;
        }
        String nodeName_ = _node.getNodeName();
        String prefix_ = _conf.getLastPage().getPrefix();
        if (StringList.quickEq(nodeName_, prefix_+ELSE_BLOCK_TAG)) {
            return true;
        }
        return false;
    }

    private static void processAfterBlock(Configuration _conf, ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        Node en_ = rw_.getRead();
        if (en_.getFirstChild() != null) {
            en_ = en_.getFirstChild();
            rw_.setRead(en_);
        } else {
            processBlock(_conf, _ip);
        }
    }


    private static void processBlock(Configuration _conf, ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        Node en_ = rw_.getRead();
        Node currentNode_ = rw_.getWrite();
        ParentElement parElt_ = getParentOfLastNode(_conf, en_, false);
        if (parElt_ == null) {
            _ip.setReadWrite(null);
            return;
        }
        Element par_ = parElt_.getElement();
        if (par_ == null) {
            NodeAction na_ = getNextNodeWrite(_conf, en_, false);
            int i_ = 0;
            while (na_.getActions().get(i_) == ActionNext.PARENT_NODE) {
                currentNode_ = currentNode_.getParentNode();
                i_ ++;
            }
            en_ = na_.getNode();
            rw_.setRead(en_);
            rw_.setWrite(currentNode_);
            return;
        }
        if (isCatchNode(_conf, par_)) {
            TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getLastStack();
            Element catch_ = tryStack_.getCurrentCatchNode();
            String var_ = catch_.getAttribute(ATTRIBUTE_VAR);
            StringMap<LocalVariable> vars_ = _ip.getCatchVars();
            vars_.removeKey(var_);
            //exit catch block
            currentNode_ = tryStack_.getWriteNode();
            rw_.setRead(catch_);
            rw_.setWrite(currentNode_);
            return;
        }
        if (isFinallyNode(_conf, par_)) {
            interruptAfterFinally(_ip);
            if (_ip.isReturning()) {
                _ip.removeLastBlock();
                removeBlockFinally(_conf, _ip);
                return;
            }
            TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getLastStack();
            Element catch_ = tryStack_.getCurrentCatchNode();
            currentNode_ = tryStack_.getWriteNode();
            tryStack_.setVisitedFinally(true);
            rw_.setRead(catch_);
            rw_.setWrite(currentNode_);
            return;
        }
        if (isLoopNode(_conf, par_)) {
            processLastElementLoop(_conf, _ip);
        } else {
            if (isTryNode(_conf, par_)) {
                TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getLastStack();
                currentNode_ = tryStack_.getWriteNode();
                rw_.setRead(par_.getNextSibling());
                rw_.setWrite(currentNode_);
                return;
            }
            if (isInstructionNode(_conf, par_)) {
                IfHtmlStack if_ = (IfHtmlStack) _ip.getLastStack();
                if (if_.lastVisitedNode() == par_) {
                    rw_.setRead(par_);
                } else {
                    rw_.setRead(par_.getNextSibling());
                }
                currentNode_ = if_.getWriteNode();
                rw_.setWrite(currentNode_);
                return;
            }
            if (isSwitchNode(_conf, par_)) {
                SwitchHtmlStack if_ = (SwitchHtmlStack) _ip.getLastStack();
                if (if_.lastVisitedNode() == par_) {
                    Node sib_ = par_.getNextSibling();
                    Document d_ = rw_.getWrite().getOwnerDocument();
                    while (sib_ != null) {
                        if (sib_ instanceof Text) {
                            Text n_ = d_.createTextNode(sib_.getTextContent());
                            if_.getWriteNode().appendChild(n_);
                        }
                        sib_ = sib_.getNextSibling();
                    }
                    if_.setFinished(true);
                    rw_.setRead(if_.getReadNode());
                } else {
                    if_.increment();
                    rw_.setRead(par_.getNextSibling());
                }
                currentNode_ = if_.getWriteNode();
                rw_.setWrite(currentNode_);
                return;
            }
        }
    }


    private static void processElementOrText(Configuration _conf, ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        Node en_ = rw_.getRead();
        Node currentNode_ = rw_.getWrite();
        ParentElement parElt_ = getParentOfLastNode(_conf, en_, true);
        if (parElt_ == null) {
            _ip.setReadWrite(null);
            return;
        }
        Element par_ = parElt_.getElement();
        if (par_ == null) {
            NodeAction na_ = getNextNodeWrite(_conf, en_, true);
            if (na_.getActions().first() == ActionNext.FIRST_CHILD) {
                currentNode_ = currentNode_.getLastChild();
            } else {
                int i_ = 0;
                while (na_.getActions().get(i_) == ActionNext.PARENT_NODE) {
                    currentNode_ = currentNode_.getParentNode();
                    i_ ++;
                }
            }
            en_ = na_.getNode();
            rw_.setRead(en_);
            rw_.setWrite(currentNode_);
            return;
        }
        if (isCatchNode(_conf, par_)) {
            TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getLastStack();
            Element catch_ = tryStack_.getCurrentCatchNode();
            String var_ = catch_.getAttribute(ATTRIBUTE_VAR);
            StringMap<LocalVariable> vars_ = _ip.getCatchVars();
            vars_.removeKey(var_);
            //exit catch block
            currentNode_ = tryStack_.getWriteNode();
            rw_.setRead(catch_);
            rw_.setWrite(currentNode_);
            return;
        }
        if (isFinallyNode(_conf, par_)) {
            interruptAfterFinally(_ip);
            if (_ip.isReturning()) {
                _ip.removeLastBlock();
                removeBlockFinally(_conf, _ip);
                return;
            }
            TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getLastStack();
            Element catch_ = tryStack_.getCurrentCatchNode();
            currentNode_ = tryStack_.getWriteNode();
            tryStack_.setVisitedFinally(true);
            rw_.setRead(catch_);
            rw_.setWrite(currentNode_);
            return;
        }
        if (isLoopNode(_conf, par_)) {
            processLastElementLoop(_conf, _ip);
        } else {
            if (isTryNode(_conf, par_)) {
                TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getLastStack();
                currentNode_ = tryStack_.getWriteNode();
                rw_.setRead(par_.getNextSibling());
                rw_.setWrite(currentNode_);
                return;
            }
            if (isInstructionNode(_conf, par_)) {
                IfHtmlStack if_ = (IfHtmlStack) _ip.getLastStack();
                currentNode_ = if_.getWriteNode();
                if (if_.lastVisitedNode() == par_) {
                    rw_.setRead(par_);
                } else {
                    rw_.setRead(par_.getNextSibling());
                }
                rw_.setWrite(currentNode_);
                return;
            }
            if (isSwitchNode(_conf, par_)) {
                SwitchHtmlStack if_ = (SwitchHtmlStack) _ip.getLastStack();
                if (if_.lastVisitedNode() == par_) {
                    Node sib_ = par_.getNextSibling();
                    Document d_ = rw_.getWrite().getOwnerDocument();
                    while (sib_ != null) {
                        if (sib_ instanceof Text) {
                            Text n_ = d_.createTextNode(sib_.getTextContent());
                            if_.getWriteNode().appendChild(n_);
                        }
                        sib_ = sib_.getNextSibling();
                    }
                    if_.setFinished(true);
                    rw_.setRead(if_.getReadNode());
                } else {
                    if_.increment();
                    rw_.setRead(par_.getNextSibling());
                }
                currentNode_ = if_.getWriteNode();
                rw_.setWrite(currentNode_);
                return;
            }
        }
    }
    private static void processLastElementLoop(Configuration _conf, ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        StringMap<LoopVariable> vars_ = _ip.getVars();

        Node en_ = rw_.getRead();
        Node currentNode_ = rw_.getWrite();
        LoopHtmlStack l_ = (LoopHtmlStack) _ip.getLastStack();
        if (keepLoop(_conf, _ip)) {
            Element forLoopLoc_ = l_.getReadNode();
            incrementLoop(_conf, l_, vars_);
            currentNode_ = l_.getWriteNode();
            en_ = forLoopLoc_;
            rw_.setRead(en_);
            rw_.setWrite(currentNode_);
            return;
        }
        Element forLoopLoc_ = l_.getReadNode();
        currentNode_ = l_.getWriteNode();
        en_ = forLoopLoc_;
        rw_.setRead(en_);
        rw_.setWrite(currentNode_);
        l_.setFinished(true);
    }

    private static void incrementLoop(Configuration _conf, LoopHtmlStack _l, StringMap<LoopVariable> _vars) {
        Element forLoopLoc_ = _l.getReadNode();
        String prefix_ = _conf.getLastPage().getPrefix();
        if (!StringList.quickEq(forLoopLoc_.getNodeName(), prefix_+FOR_BLOCK_TAG)) {
            return;
        }
        _l.setIndex(_l.getIndex() + 1);
        if (forLoopLoc_.hasAttribute(ATTRIBUTE_LIST)) {
            _conf.getLastPage().setProcessingNode(forLoopLoc_);
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR);
            _conf.getLastPage().setLookForAttrValue(false);
            _conf.getLastPage().setOffset(0);
            String var_ = forLoopLoc_.getAttribute(ATTRIBUTE_VAR);
            LoopVariable lv_ = _vars.getVal(var_);
            Struct iterator_ = _l.getStructIterator();
            if (iterator_ != null) {
                lv_.setElement(ExtractObject.next(_conf, iterator_));
            } else {
                lv_.setElement(Array.get(lv_.getContainer().getInstance(), (int) _l.getIndex()));
            }
            lv_.setIndex(lv_.getIndex() + 1);
        } else if (forLoopLoc_.hasAttribute(ATTRIBUTE_MAP)) {
            String key_ = forLoopLoc_.getAttribute(ATTRIBUTE_KEY);
            _conf.getLastPage().setProcessingNode(forLoopLoc_);
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_KEY);
            _conf.getLastPage().setLookForAttrValue(false);
            _conf.getLastPage().setOffset(0);
            LoopVariable lv_ = _vars.getVal(key_);
            Object k_;
            Struct entry_ = ExtractObject.next(_conf, _l.getStructIterator());
            k_ = ExtractObject.getKey(_conf, entry_);
            lv_.setElement(k_);
            lv_.setIndex(lv_.getIndex() + 1);
            String value_ = forLoopLoc_.getAttribute(ATTRIBUTE_VALUE);
            lv_ = _vars.getVal(value_);
            _conf.getLastPage().setProcessingNode(forLoopLoc_);
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VALUE);
            _conf.getLastPage().setLookForAttrValue(false);
            _conf.getLastPage().setOffset(0);
            lv_.setElement(ExtractObject.getValue(_conf, entry_));
            lv_.setIndex(lv_.getIndex() + 1);
        } else {
            _conf.getLastPage().setProcessingNode(forLoopLoc_);
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR);
            _conf.getLastPage().setLookForAttrValue(false);
            _conf.getLastPage().setOffset(0);
            String var_ = forLoopLoc_.getAttribute(ATTRIBUTE_VAR);
            LoopVariable lv_ = _vars.getVal(var_);
            Number element_ = (Number) lv_.getElement();
            lv_.setElement(element_.longValue()+lv_.getStep());
            lv_.setIndex(lv_.getIndex() + 1);
        }
    }

    private static void removeVarAndLoop(Configuration _conf, Element _forLoop, StringMap<LoopVariable> _vars) {
        ImportingPage ip_ = _conf.getLastPage();
        String prefix_ = ip_.getPrefix();
        ip_.removeLastBlock();
        if (!StringList.quickEq(_forLoop.getNodeName(), prefix_+FOR_BLOCK_TAG)) {
            return;
        }
        if (_forLoop.hasAttribute(ATTRIBUTE_LIST) || _forLoop.hasAttribute(ATTRIBUTE_FROM)) {
            String var_ = _forLoop.getAttribute(ATTRIBUTE_VAR);
            _vars.removeKey(var_);
        } else {
            String key_ = _forLoop.getAttribute(ATTRIBUTE_KEY);
            String value_ = _forLoop.getAttribute(ATTRIBUTE_VALUE);
            _vars.removeKey(key_);
            _vars.removeKey(value_);
        }
    }

    static void appendChild(Document _doc, Configuration _conf, Node _parent, Element _read) {
        Element currentNode_;
        String prefix_ = getPrefix(_conf, _read.getOwnerDocument());
        if (_parent instanceof Document) {
            if (prefix_.isEmpty()) {
                currentNode_ = _doc.createElement(_read.getNodeName());
                setNormalAttributes(_doc, _conf, _read, currentNode_);
            } else {
                String nodeName_ = _read.getNodeName();
                String newNodeName_;
                if (nodeName_.startsWith(prefix_)) {
                    String suffix_ = _read.getNodeName().substring(prefix_.length());
                    newNodeName_ = getPrefix(_conf, (Document)_parent)+suffix_;
                } else {
                    newNodeName_ = nodeName_;
                }
                currentNode_ = _doc.createElementNS(_conf.getNamespaceUri(), newNodeName_);
                setPrefixedAttributes(_doc, _conf, _read, currentNode_, prefix_);
            }
        } else {
            String replacing_ = _conf.getPrefix();
            if (!prefix_.isEmpty()) {
                String nodeName_ = _read.getNodeName();
                String newNodeName_;
                if (nodeName_.startsWith(prefix_)) {
                    String suffix_ = _read.getNodeName().substring(prefix_.length());
                    newNodeName_ = replacing_+suffix_;
                } else {
                    newNodeName_ = nodeName_;
                }
                currentNode_ = _doc.createElement(newNodeName_);
                setPrefixedAttributes(_doc, _conf, _read, currentNode_, prefix_);
            } else {
                currentNode_ = _doc.createElement(_read.getNodeName());
                setNormalAttributes(_doc, _conf, _read, currentNode_);
            }
        }
        _parent.appendChild(currentNode_);
    }

    private static void setNormalAttributes(Document _doc, Configuration _conf, Element _read, Element _write) {
        NamedNodeMap map_ = _read.getAttributes();
        int nbAttrs_ = map_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            Node at_ = map_.item(i);
            String name_ = at_.getNodeName();
            String value_ = at_.getNodeValue();
            _write.setAttribute(name_, value_);
        }
    }
    private static void setPrefixedAttributes(Document _doc, Configuration _conf, Element _read, Element _write, String _readPrefix) {
        NamedNodeMap map_ = _read.getAttributes();
        String mainPrefix_ = _conf.getPrefix();
        int nbAttrs_ = map_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            Node at_ = map_.item(i);
            String name_ = at_.getNodeName();
            String value_ = at_.getNodeValue();
            if (!name_.startsWith(_readPrefix)) {
                _write.setAttribute(name_, value_);
            } else {
                String suffix_ = name_.substring(_readPrefix.length());
                _write.setAttribute(mainPrefix_ + suffix_, value_);
            }
        }
    }

    static void processWhile(Configuration _conf,
            ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        Element written_ = (Element) rw_.getWrite();
        Element currentWhileNode_ = (Element) rw_.getRead();
        boolean res_ = ExtractCondition.evaluateCondition(currentWhileNode_, _conf, _ip);
        LoopHtmlStack l_ = new LoopHtmlStack();
        l_.setReadNode(currentWhileNode_);
        l_.setWriteNode(written_);
        l_.setFinished(!res_);
        _ip.addBlock(l_);
    }

    static void processDoWhile(Configuration _conf,
            ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        Element written_ = (Element) rw_.getWrite();
        Element currentWhileNode_ = (Element) rw_.getRead();
        LoopHtmlStack l_ = new LoopHtmlStack();
        l_.setReadNode(currentWhileNode_);
        l_.setWriteNode(written_);
        _ip.addBlock(l_);
    }

    static void processLoop(Configuration _conf,
            ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        Element currentForNode_ = (Element) rw_.getRead();
        Element written_ = (Element) rw_.getWrite();
        boolean map_ = false;
        StringMap<LoopVariable> varsLoop_ = _ip.getVars();
        Struct container_ = null;
        Object iterable_ = null;
        String var_ = currentForNode_.getAttribute(ATTRIBUTE_VAR);
        String key_ = currentForNode_.getAttribute(ATTRIBUTE_KEY);
        String value_ = currentForNode_.getAttribute(ATTRIBUTE_VALUE);
        Object mapCast_ = null;
        String listMethod_ = null;
        long nbMaxIterations_ = 0;
        boolean iterationNb_ = false;
        long stepValue_ = 0;
        long fromValue_ = 0;
        Object realFromValue_ = 0;
        if (currentForNode_.hasAttribute(ATTRIBUTE_LIST)) {
            String listAttr_ = currentForNode_.getAttribute(ATTRIBUTE_LIST);
            _ip.setProcessingAttribute(ATTRIBUTE_LIST);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            container_ = ElUtil.processEl(listAttr_, 0, _conf.toContextEl()).getStruct();
            Object it_ = container_.getInstance();
            if (it_ == null) {
                _conf.getLastPage().addToOffset(listAttr_.length()+1);
                throw new NullObjectException(_conf.joinPages());
            }
            iterable_ = it_;
        } else if (currentForNode_.hasAttribute(ATTRIBUTE_MAP)) {
            map_ = true;
            String mapAttr_ = currentForNode_.getAttribute(ATTRIBUTE_MAP);
            _ip.setProcessingAttribute(ATTRIBUTE_MAP);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            container_ = ElUtil.processEl(mapAttr_, 0, _conf.toContextEl()).getStruct();
            Object o_ = container_.getInstance();
            mapCast_ = o_;
            iterable_ = ExtractObject.getKeys(_conf, mapAttr_.length(), container_).getInstance();
            if (iterable_ == null) {
                throw new NullObjectException(_conf.joinPages());
            }
            listMethod_ = NULL_METHOD;
        } else {
            iterationNb_ = true;
            String from_ = currentForNode_.getAttribute(ATTRIBUTE_FROM);
            String to_ = currentForNode_.getAttribute(ATTRIBUTE_TO);
            String step_ = currentForNode_.getAttribute(ATTRIBUTE_STEP);
            boolean eq_ = currentForNode_.hasAttribute(ATTRIBUTE_EQ);
            _ip.setProcessingAttribute(ATTRIBUTE_FROM);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Argument argFrom_ = ElUtil.processEl(from_, 0, _conf.toContextEl());
            if (!argFrom_.isIntegerType() || argFrom_.getObject() == null) {
                throw new DynamicCastClassException(argFrom_.getObjectClassName()+RETURN_LINE+_conf.joinPages());
            }
            _ip.setProcessingAttribute(ATTRIBUTE_TO);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Argument argTo_ = ElUtil.processEl(to_, 0, _conf.toContextEl());
            if (!argTo_.isIntegerType() || argTo_.getObject() == null) {
                throw new DynamicCastClassException(argTo_.getObjectClassName()+RETURN_LINE+_conf.joinPages());
            }
            _ip.setProcessingAttribute(ATTRIBUTE_STEP);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Argument argStep_ = ElUtil.processEl(step_, 0, _conf.toContextEl());
            if (!argStep_.isIntegerType() || argStep_.getObject() == null) {
                throw new DynamicCastClassException(argStep_.getObjectClassName()+RETURN_LINE+_conf.joinPages());
            }
            realFromValue_ = argFrom_.getObject();
            fromValue_ = (Long)PrimitiveTypeUtil.convert(long.class, realFromValue_);
            long toValue_ = (Long)PrimitiveTypeUtil.convert(long.class, argTo_.getObject());
            stepValue_ = (Long)PrimitiveTypeUtil.convert(long.class, argStep_.getObject());
            if (stepValue_ > 0) {
                if (fromValue_ > toValue_) {
                    stepValue_ = -stepValue_;
                }
            } else if (stepValue_ < 0) {
                if (fromValue_ < toValue_) {
                    stepValue_ = -stepValue_;
                }
            }
            if (stepValue_ > 0) {
                long copyFrom_ = fromValue_;
                while (true) {
                    if (copyFrom_ >= toValue_ && !eq_) {
                        break;
                    }
                    if (copyFrom_ > toValue_) {
                        break;
                    }
                    nbMaxIterations_++;
                    copyFrom_ += stepValue_;
                }
            } else if (stepValue_ < 0) {
                long copyFrom_ = fromValue_;
                while (true) {
                    if (copyFrom_ <= toValue_ && !eq_) {
                        break;
                    }
                    if (copyFrom_ < toValue_) {
                        break;
                    }
                    nbMaxIterations_++;
                    copyFrom_ += stepValue_;
                }
            }
        }
        Struct itStr_ = null;
        ResultsIterator res_ = new ResultsIterator();
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        if (iterationNb_) {
            length_ = nbMaxIterations_;
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
                res_.setFinished(true);
            }
        } else if (iterable_.getClass().isArray()) {
            length_ = Array.getLength(iterable_);
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
                res_.setFinished(true);
            }
        } else {
            if (map_) {
                itStr_ = ExtractObject.iterator(_conf, ExtractObject.entryList(_conf, 0, container_));
            } else {
                itStr_ = ExtractObject.iterator(_conf, container_);
            }
            if (!ExtractObject.hasNext(_conf, itStr_)) {
                finished_ = true;
                res_.setFinished(true);
            }
        }
        if (currentForNode_.getFirstChild() == null) {
            finished_ = true;
        }
        LoopHtmlStack l_ = new LoopHtmlStack();
        l_.setFinished(finished_);
        l_.setReadNode(currentForNode_);
        l_.setWriteNode(written_);
        l_.setStructIterator(itStr_);
        l_.setMaxIteration(length_);
        _ip.addBlock(l_);
        if (finished_) {
            return;
        }
        Object int_ = null;
        Struct elt_ = null;
        if (iterationNb_) {
            int_ = realFromValue_;
        } else if (iterable_.getClass().isArray()) {
            elt_ = Struct.wrapOrId(Array.get(iterable_, CustList.FIRST_INDEX));
        } else {
            elt_ = ExtractObject.next(_conf, itStr_);
        }
        String indexClassName_;
        indexClassName_ = currentForNode_.getAttribute(ATTRIBUTE_INDEX_CLASS_NAME);
        ExtractObject.checkClassNotEmptyName(_conf, 0, indexClassName_);
        String className_;
        if (iterationNb_) {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(ATTRIBUTE_CLASS_NAME);
            if (className_.isEmpty()) {
                className_ = long.class.getName();
            }
            Class<?> cl_ = ExtractObject.classForName(_conf, 0, className_);
            lv_.setClassName(ConstClasses.resolve(className_));
            lv_.setIndexClassName(ConstClasses.resolve(indexClassName_));
            lv_.setElement(PrimitiveTypeUtil.convert(cl_, int_));
            lv_.setStep(stepValue_);
            lv_.setExtendedExpression(EMPTY_STRING);
            varsLoop_.put(var_, lv_);
        } else if (currentForNode_.hasAttribute(ATTRIBUTE_LIST)) {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(ATTRIBUTE_CLASS_NAME);
            ExtractObject.checkClassNotEmptyName(_conf, 0, className_);
            lv_.setClassName(ConstClasses.resolve(className_));
            lv_.setIndexClassName(ConstClasses.resolve(indexClassName_));
            lv_.setElement(elt_);
            lv_.setContainer(container_);
            if (iterable_.getClass().isArray()) {
                lv_.setArray(iterable_);
            } else {
                lv_.setList(iterable_);
            }
            lv_.setExtendedExpression(EMPTY_STRING);
            varsLoop_.put(var_, lv_);
        } else {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(KEY_CLASS_NAME_ATTRIBUTE);
            ExtractObject.checkClassNotEmptyName(_conf, 0, className_);
            lv_.setClassName(ConstClasses.resolve(className_));
            lv_.setIndexClassName(ConstClasses.resolve(indexClassName_));
            lv_.setElement(ExtractObject.getKey(_conf, elt_));
            lv_.setMap(mapCast_);
            lv_.setContainer(container_);
            lv_.setExtendedExpression(listMethod_+GET_KEY);
            varsLoop_.put(key_, lv_);
            lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(VAR_CLASS_NAME_ATTRIBUTE);
            ExtractObject.checkClassNotEmptyName(_conf, 0, className_);
            lv_.setClassName(ConstClasses.resolve(className_));
            lv_.setIndexClassName(ConstClasses.resolve(indexClassName_));
            lv_.setElement(ExtractObject.getValue(_conf, elt_));
            lv_.setMap(mapCast_);
            lv_.setExtendedExpression(listMethod_+GET_VALUE);
            varsLoop_.put(value_, lv_);
        }
    }

    private static ParentElement getParentOfLastNode(Configuration _conf, Node _node, boolean _child) {
        Node n_ = _node.getFirstChild();
        if (n_ != null && _child && !StringList.quickEq(_node.getNodeName(), TEXT_AREA)) {
            return new ParentElement(null);
        }
        n_ = _node.getNextSibling();
        if (n_ != null) {
            return new ParentElement(null);
        }
        n_ = _node.getParentNode();
        if (n_ == null) {
            return null;
        }
        ImportingPage ip_ = _conf.getLastPage();
        Element b_ = null;
        if (!ip_.noBlock()) {
            BlockHtml bl_ = (BlockHtml) ip_.getLastStack();
            b_ = bl_.getReadNode();
            if (bl_ instanceof TryHtmlStack) {
                TryHtmlStack t_ = (TryHtmlStack)bl_;
                int vis_ = t_.getVisitedCatch();
                if (vis_ > CustList.INDEX_NOT_FOUND_ELT) {
                    b_ = t_.getCatchNodes().get(vis_);
                }
            } else if (bl_ instanceof IfHtmlStack) {
                IfHtmlStack t_ = (IfHtmlStack)bl_;
                b_ = t_.getCurentVisitedNode();
            } else if (bl_ instanceof SwitchHtmlStack) {
                SwitchHtmlStack t_ = (SwitchHtmlStack)bl_;
                if (t_.getVisitedBlock() > CustList.INDEX_NOT_FOUND_ELT) {
                    b_ = t_.getCurentVisitedNode();
                }
            }
        }
        if (b_ == n_) {
            //n_ != null => b_ != null
            return new ParentElement(b_);
        }
        Node next_ = n_.getNextSibling();
        while (next_ == null) {
            Node par_ = n_.getParentNode();
            if (par_ == null) {
                break;
            }
            if (b_ == par_) {
                //par_ != null => b_ != null
                return new ParentElement(b_);
            }
            next_ = par_.getNextSibling();
            n_ = par_;
        }
        if (next_ != null) {
            return new ParentElement(null);
        }
        //n_ is instance of Document
        // so _node is last element to treat in the document
        return null;
    }

    static boolean keepLoop(Configuration _conf, ImportingPage _ip) {
        LoopHtmlStack l_ = (LoopHtmlStack) _ip.getLastStack();
        _conf.getLastPage().setProcessingNode(l_.getReadNode());
        _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
        _conf.getLastPage().setOffset(0);
        _conf.getLastPage().setLookForAttrValue(false);
        String prefix_ = _conf.getLastPage().getPrefix();
        if (StringList.quickEq(l_.getReadNode().getNodeName(), prefix_+TAG_DO)) {
            Node n_ = l_.getReadNode().getNextSibling();
            while (true) {
                if (n_ instanceof Text) {
                    n_ = n_.getNextSibling();
                    continue;
                }
                if (n_ instanceof Comment) {
                    n_ = n_.getNextSibling();
                    continue;
                }
                break;
            }
            return ExtractCondition.evaluateCondition((Element) n_, _conf, _ip);
        }
        if (StringList.quickEq(l_.getReadNode().getNodeName(), prefix_+FOR_BLOCK_TAG)) {
            if (l_.getStructIterator() != null) {
                return ExtractObject.hasNext(_conf, l_.getStructIterator());
            }
            return l_.hasNext();
        }
        return ExtractCondition.evaluateCondition(l_.getReadNode(), _conf, _ip);
    }

    static CustList<NodeAction> getDeepChildNodesDocOrder(Node _root) {
        CustList<NodeAction> nodes_ = new CustList<NodeAction>();
        if (_root == null) {
            return nodes_;
        }
        ActionNext action_ = ActionNext.NONE;
        NodeAction na_ = new NodeAction();
        na_.getActions().add(action_);
        na_.setNode(_root);
        Node en_ = _root;
        while (true) {
            nodes_.add(na_);
            na_ = new NodeAction();
            Node n_ = en_.getFirstChild();
            if (n_ != null) {
                en_ = n_;
                na_.getActions().add(ActionNext.FIRST_CHILD);
                na_.setNode(n_);
                continue;
            }
            if (en_ == _root) {
                break;
            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                en_ = n_;
                na_.getActions().add(ActionNext.NEXT_SIBLING);
                na_.setNode(n_);
                continue;
            }
            n_ = en_.getParentNode();
            if (n_ == _root) {
                break;
            }
            na_.getActions().add(ActionNext.PARENT_NODE);
            Node next_ = n_.getNextSibling();
            if (next_ != null) {
                na_.getActions().add(ActionNext.NEXT_SIBLING);
            }
            while (next_ == null) {
                Node par_ = n_.getParentNode();
                if (par_ == _root) {
                    break;
                }
                na_.getActions().add(ActionNext.PARENT_NODE);
                next_ = par_.getNextSibling();
                if (next_ != null) {
                    na_.getActions().add(ActionNext.NEXT_SIBLING);
                }
                n_ = par_;
            }
            if (next_ == null) {
                break;
            }
            en_ = next_;
            na_.setNode(next_);
        }
        return nodes_;
    }

    private static String getPrefix(Configuration _conf, Document _doc) {
        String prefix_ = _doc.lookupPrefix(_conf.getNamespaceUri());
        if (prefix_ == null) {
            prefix_ = EMPTY_STRING;
        } else {
            prefix_ += SEP_PREFIX;
        }
        return prefix_;
    }
    private static Bean getBean(Configuration _conf, String _beanName) {
        try {
            return _conf.getBeans().getVal(_beanName);
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    private static int getTabWidth(Configuration _conf) {
        try {
            return _conf.getTabWidth();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    private static void beforeDisplaying(Bean _bean, Configuration _conf) {
        try {
            if (_bean != null) {
                _bean.beforeDisplaying();
            }
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
}
