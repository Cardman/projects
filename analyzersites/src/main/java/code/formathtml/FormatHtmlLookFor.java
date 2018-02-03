package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustElUtil;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
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
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exceptions.BadSelectException;
import code.formathtml.exceptions.InexistingTranslatorException;
import code.formathtml.exceptions.KeyValueException;
import code.formathtml.util.ActionNext;
import code.formathtml.util.BlockHtml;
import code.formathtml.util.LoopHtmlStack;
import code.formathtml.util.NodeAction;
import code.formathtml.util.NodeAttribute;
import code.serialize.ConstClasses;
import code.sml.AttributePart;
import code.sml.Comment;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.sml.Element;
import code.sml.NamedNodeMap;
import code.sml.Node;
import code.sml.Text;
import code.sml.exceptions.XmlParseException;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public class FormatHtmlLookFor {
    //
    //    static final String NAMESPACE_URI = "javahtml";
    //    static final String EMPTY_STRING = "";
    //    static final String RETURN_LINE = "\n";
    //    static final String RETURN_TAB = "\n\t";
    //
    //    static final String SPACE = " ";
    //    static final String VAR_METHOD = "varMethod";
    //    static final String BEAN_ATTRIBUTE = "bean";
    //    static final String COMMA = ",";
    //    static final String DOT = ".";
//    private static final String INSTANCE = "$new ";
//    private static final char BEGIN_ARGS = '(';
//    private static final char SEP_ARGS = ',';
//    private static final char END_ARGS = ')';
    //    private static final String GET_LOC_VAR = ";.";
    //    private static final String NO_PARAM_METHOD = "()";
//    private static final String CMP = "=";
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';

//    private static final String LOOP_MESSAGE = "loop";
//    private static final String LOCAL_MESSAGE = "local";

//    private static final String ATTRIBUTE_QUOTED = "quoted";
    //    private static final String ATTRIBUTE_ESCAPED = "escaped";
    //    private static final char ESCAPED = '\\';
    //    private static final char MATH_INTERPRET = '`';
    //    private static final String CALL_METHOD = "$";
    //    private static final String ATTRIBUTE_VALUE = "value";
    //    private static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";
    private static final char SEP_TR = ',';
    private static final char QUOTE = 39;
//    private static final String QUOTE_DOUBLE = "\"";
//    private static final String GET_STRING ="getString";
//    private static final String NAME ="name";
//    private static final String ITERATOR ="iterator";
//    private static final String HAS_NEXT ="hasNext";
//    private static final String NEXT ="next";
//    private static final String ENTRY_LIST ="entries";
//    private static final String GET_KEY ="getKey";
//    private static final String GET_VALUE ="getValue";
//    private static final String GET_SCOPE ="getScope";
//    private static final String SET_SCOPE ="setScope";
//    private static final String GET_FORMS ="getForms";
//    private static final String SET_FORMS ="setForms";
//    private static final String BEFORE_DISPLAYING ="beforeDisplaying";
//    private static final String GET_DATA_BASE ="getDataBase";
//    private static final String SET_DATA_BASE ="setDataBase";
//    private static final String GET_LANGUAGE ="getLanguage";
//    private static final String SET_LANGUAGE ="setLanguage";

    static final String XMLNS = "xmlns";
    static final String NAMESPACE_URI = "javahtml";
    static final String ATTRIBUTE_CLASS_NAME = "className";
    static final String ATTRIBUTE_INDEX_CLASS_NAME = "indexclassName";
    static final String EMPTY_STRING = "";
    static final String RETURN_LINE = "\n";
    static final String RETURN_TAB = "\n\t";

    static final String SPACE = " ";
    static final String VAR_METHOD = "varMethod";
    static final String BEAN_ATTRIBUTE = "bean";
    static final String ATTRIBUTE_VALUE_CHANGE_EVENT = "valueChangeEvent";
    static final String COMMA = ",";
    static final String DOT = ".";
    static final StringList FIELDS_NAMES = new StringList();
    static final StringList INPUT_CLASSES = new StringList();
    static final StringList SELECT_CLASSES = new StringList();

    static final String TMP_VAR = "tmpvar";
//    private static final char RIGHT_ARR = ']';
//    private static final char LEFT_ARR = '[';
//    private static final String SUFFIX_INT = "i";
    private static final String COMMENT = "!";
    private static final String ATTRIBUTE_SRC = "src";
//    private static final String ATTRIBUTE_ENCODE_IMG = "wrap";
    private static final String TAG_IMG = "img";
//    private static final String TAG_HEAD = "head";
//    private static final String STYLESHEET = "stylesheet";
//    private static final String TAG_STYLE = "style";
//    private static final String ATTRIBUTE_REL = "rel";
//    private static final String SCRIPT = "script";
//    private static final String TAG_LINK = "link";
//    private static final String ATTRIBUTE_TITLE = "title";
    private static final String COMMA_RIGHT_PAR = ",)";
    private static final String LEFT_PAR_COMMA = "(,";
    private static final String DOUBLE_COMMA = ",,";
    private static final char RIGHT_PAR_CHAR = ')';
//    private static final String TAG_OPTION = "option";
//    private static final String SELECTED = "selected";
//    private static final String ATTRIBUTE_VALIDATOR = "validator";
//    private static final String ATTRIBUTE_UPDATE = "update";
//    private static final String TR_TAG = "tr";
//    private static final String TR_END = "</tr>";
//    private static final String TR_BEGIN = "<tr>";
//    private static final String RIGHT_END_TAG = "/>";
    private static final String TAG_FORM = "form";
    private static final String TAG_A = "a";
    private static final String TAG_HTML = "html";
//    private static final String SUBMIT_TYPE = "submit";
//    private static final String TAG_BODY = "body";
    private static final char LEFT_PAR_CHAR = '(';
//    private static final String TAG_PARAM = "param";
    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_TYPE = "type";
//    private static final String SCRIPT_TYPE = "text/javascript";
//    private static final String CHECKBOX = "checkbox";
//
//    private static final String TEXT = "text";
//
//    private static final String RANGE = "range";

    private static final String RADIO = "radio";

//    private static final String NUMBER = "number";
    private static final String ATTRIBUTE_LIST = "list";
    private static final String ATTRIBUTE_MAP = "map";
    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_GROUP_ID = "groupId";
//    private static final String ATTRIBUTE_MULTIPLE = "multiple";
    private static final String ATTRIBUTE_KEY = "key";
    private static final String ATTRIBUTE_VAR = "var";
    private static final String ATTRIBUTE_HREF = "href";
    private static final String ATTRIBUTE_COMMAND = "command";
    private static final String ATTRIBUTE_ACTION = "action";
    private static final String SELECT_TAG = "select";
    private static final String CHECKED = "checked";
    private static final String INPUT_TAG = "input";
    private static final String TEXT_AREA = "textarea";
//    private static final String SPAN_TAG = "span";
//    private static final String ATTRIBUTE_FOR = "for";
    private static final String ATTRIBUTE_VAR_VALUE = "varValue";
    private static final String GET_LOC_VAR = ";.";
    private static final String ATTRIBUTE_ESCAPED = "escaped";
    private static final String GET_ATTRIBUTE = ";";
    private static final String CALL_METHOD = "$";
//    private static final String DEFAULT_ATTRIBUTE = "default";
    private static final String NO_PARAM_METHOD = "()";
//    private static final String ATTRIBUTE_VALUE_SUBMIT = "message";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";

    private static final String ATTRIBUTE_METHOD = "method";
    private static final String ATTRIBUTE_NAME = "name";
//    private static final String ATTRIBUTE_FORM = "form";
//    private static final String PAGE_ATTRIBUTE = "page";
//    private static final String KEEPFIELD_ATTRIBUTE = "keepfields";
//    private static final String EXPRESSION_ATTRIBUTE = "expression";
//    private static final String ARRAY_INDEX_ATTRIBUTE = "arrayindex";
//    private static final String ARRAY_ELEMENT_ATTRIBUTE = "arrayelement";
    private static final String KEY_CLASS_NAME_ATTRIBUTE = "keyClassName";
    private static final String VAR_CLASS_NAME_ATTRIBUTE = "varClassName";
    private static final String ATTRIBUTE_FROM = "from";
    private static final String ATTRIBUTE_TO = "to";
//    private static final String ATTRIBUTE_EQ = "eq";
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
//    private static final String PARAM_BLOCK_TAG = "param";
//    private static final String SET_RETURNED_VALUE_BLOCK_TAG = "setreturnedvalue";
    private static final String TMP_BLOCK_TAG = "tmp";
//    private static final String SUBMIT_BLOCK_TAG = "submit";
//    private static final String FORM_BLOCK_TAG = "form";
//    private static final String TR_BEGIN_BLOCK_TAG = "tr_begin";
    private static final String TR_END_BLOCK_TAG = "tr_end";
//    private static final String UNSET_BLOCK_TAG = "unset";
//    private static final String SET_BLOCK_TAG = "set";
    private static final String CONTINUE_TAG = "continue";
    private static final String BREAK_TAG = "break";
//    private static final String RETURN_TAG = "return";
//    private static final String EXIT_TAG = "exit";
    private static final String TRY_TAG = "try";
    private static final String CATCH_TAG = "catch";
//    private static final String THROW_TAG = "throw";
    private static final String TAG_FINALLY = "finally";
    private static final String TAG_SWITCH = "switch";
    private static final String TAG_CASE = "case";
    private static final String TAG_DEFAULT = "default";
    private static final String TAG_DO = "do";
//    private static final char SEP_ENUMS = ',';
//    private static final String NUMBER_EXPRESSION = "mathexpr";
//    private static final String IS_STRING_CONST_ATTRIBUTE = "isstringconst";
//    private static final String IS_CHAR_CONST_ATTRIBUTE = "ischarconst";
//    private static final String IS_BOOL_CONST_ATTRIBUTE = "isboolconst";
//    private static final String EVALUATE_BOOLEAN = "isbool";
//    private static final char ESCAPED = '\\';
//    private static final char MATH_INTERPRET = '`';
//    private static final String NUMBER_FORM = "n-f";
//    private static final String NUMBER_ANCHOR = "n-a";
    private static final String NUMBER_INPUT = "n-i";
//    private static final String INTERPRET = "interpret";

    private static final char END_ESCAPED = ';';

    private static final char ENCODED = '&';
    private static final char EQUALS = '=';
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private static final String END_COMMENT = "-->";

//    private static final StringList AVAILABLE_FORMATS = new StringList("png","jpg","bmp","gif","svg");
//
//    private static final String BASE64 = ";base64,";
//
//    private static final String DATA_IMAGE = "data:image/";
//
//    private static final String IMG_EXT = "png";

    public static void checkSyntax(Configuration _conf, boolean _cust, Document _doc, String _html, String _loc, String... _resourcesFolder) {
        Element root_ = _doc.getDocumentElement();
        Node en_ = root_;
        StringMap<String> files_ = new StringMap<String>();
        int indexGlobal_ = _html.indexOf(LT_BEGIN_TAG)+1;
        CustList<StringList> vars_ = new CustList<StringList>();
        StringList catchVars_ = new StringList();
        ObjectMap<NodeAttribute, NatTreeMap<Integer, Integer>> infos_;
        infos_ = new ObjectMap<NodeAttribute, NatTreeMap<Integer, Integer>>();
        String prefix_ = _conf.getLastPage().getPrefix();
        String beanName_ = root_.getAttribute(StringList.concat(_conf.getPrefix(),BEAN_ATTRIBUTE));
        String classBeanName_ = _conf.getBeans().getVal(beanName_).getClassName();
        _conf.getLastPage().setGlobalClass(classBeanName_);
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        while (true) {
            _conf.getLastPage().setProcessingNode(en_);
            if (en_ instanceof Element) {
                Element elt_ = (Element) en_;
                if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,FOR_BLOCK_TAG))) {
                    String resIterable_ = EMPTY_STRING;
                    if (elt_.hasAttribute(ATTRIBUTE_LIST)) {
                        String listAttr_ = elt_.getAttribute(ATTRIBUTE_LIST);
                        ip_.setProcessingAttribute(ATTRIBUTE_LIST);
                        ip_.setLookForAttrValue(true);
                        ip_.setOffset(0);
                        resIterable_ = CustElUtil.processAnalyzeEl(listAttr_, 0, _conf.toContextEl());
                        //                        System.out.println("list:"+resIterable_);
                    } else if (elt_.hasAttribute(ATTRIBUTE_MAP)) {
                        String mapAttr_ = elt_.getAttribute(ATTRIBUTE_MAP);
                        ip_.setProcessingAttribute(ATTRIBUTE_MAP);
                        ip_.setLookForAttrValue(true);
                        ip_.setOffset(0);
                        resIterable_ = CustElUtil.processAnalyzeEl(mapAttr_, 0, _conf.toContextEl());
                        //                      System.out.println("map:"+resIterable_);
                    }
                    StringList varsLoc_ = checkForLoop(_conf, elt_, _html);
                    if (elt_.hasAttribute(ATTRIBUTE_LIST)) {
                        LoopVariable lv_ = new LoopVariable();
                        String className_ = elt_.getAttribute(ATTRIBUTE_CLASS_NAME);
                        ExtractObject.checkClassNotEmptyName(_conf, 0, className_);
                        if (className_.isEmpty()) {
                            className_ = Object.class.getName();
                        }
                        CustElUtil.CLASSES.add(className_);
//                        String realClass_ = ConstClasses.resolve(className_);
//                        if (classForName(_conf, 0, realClass_).getTypeParameters().length == 0) {
//                            String all_ = Templates.getFullTypeByBases(resIterable_, Listable.class.getName(), _conf.toContextEl());
//                            String compo_ = StringList.getAllTypes(all_).last();
//                            if (!StringList.quickEq(compo_, realClass_)) {
//                                //System.err.println(DocumentBuilder.getRowColOfNodeOrAttribute(_html, elt_, 0, "", 4)+" "+resIterable_+" "+className_+" as "+realClass_);
//                            }
//                        }
                        lv_.setClassName(ConstClasses.resolve(className_));
                        String indexClassName_;
                        indexClassName_ = elt_.getAttribute(ATTRIBUTE_INDEX_CLASS_NAME);
                        if (indexClassName_.isEmpty()) {
                            indexClassName_ = _conf.getStandards().getAliasPrimLong();
                        }
                        CustElUtil.CLASSES.add(indexClassName_);
                        lv_.setIndexClassName(ConstClasses.resolve(indexClassName_));
                        String key_ = elt_.getAttribute(ATTRIBUTE_VAR);
                        varsLoop_.put(key_, lv_);
                    } else if (elt_.hasAttribute(ATTRIBUTE_MAP)) {
                        String key_ = elt_.getAttribute(ATTRIBUTE_KEY);
                        String value_ = elt_.getAttribute(ATTRIBUTE_VALUE);
                        String indexClassName_;
                        indexClassName_ = elt_.getAttribute(ATTRIBUTE_INDEX_CLASS_NAME);
                        if (indexClassName_.isEmpty()) {
                            indexClassName_ = _conf.getStandards().getAliasPrimLong();
                        }
                        CustElUtil.CLASSES.add(indexClassName_);
                        LoopVariable lv_ = new LoopVariable();
                        String className_ = elt_.getAttribute(KEY_CLASS_NAME_ATTRIBUTE);
                        ExtractObject.checkClassNotEmptyName(_conf, 0, className_);
                        if (className_.isEmpty()) {
                            className_ = Object.class.getName();
                        }
                        CustElUtil.CLASSES.add(className_);
                        lv_.setClassName(ConstClasses.resolve(className_));
//                        String realClass_ = ConstClasses.resolve(className_);
//                        if (classForName(_conf, 0, realClass_).getTypeParameters().length == 0) {
//                            String all_ = Templates.getFullTypeByBases(resIterable_, ListableEntries.class.getName(), _conf.toContextEl());
//                            if (all_ == null) {
//                                //System.out.println("%"+resIterable_);
//                            } else {
//                                String compo_ = StringList.getAllTypes(all_).get(1);
//                                if (!StringList.quickEq(compo_, realClass_)) {
//                                    //System.err.println(DocumentBuilder.getRowColOfNodeOrAttribute(_html, elt_, 0, "", 4)+" "+resIterable_+" "+className_+" as "+realClass_);
//                                }
//                            }
//                        }
                        lv_.setIndexClassName(ConstClasses.resolve(indexClassName_));
                        varsLoop_.put(key_, lv_);
                        lv_ = new LoopVariable();
                        className_ = elt_.getAttribute(VAR_CLASS_NAME_ATTRIBUTE);
                        ExtractObject.checkClassNotEmptyName(_conf, 0, className_);
                        if (className_.isEmpty()) {
                            className_ = Object.class.getName();
                        }
                        CustElUtil.CLASSES.add(className_);
//                        realClass_ = ConstClasses.resolve(className_);
//                        if (classForName(_conf, 0, realClass_).getTypeParameters().length == 0) {
//                            String all_ = Templates.getFullTypeByBases(resIterable_, ListableEntries.class.getName(), _conf.toContextEl());
//                            if (all_ == null) {
//                                //System.out.println("%"+resIterable_);
//                            } else {
//                                String compo_ = StringList.getAllTypes(all_).last();
//                                if (!StringList.quickEq(compo_, realClass_)) {
//                                    //System.err.println(DocumentBuilder.getRowColOfNodeOrAttribute(_html, elt_, 0, "", 4)+" "+resIterable_+" "+className_+" as "+realClass_);
//                                }
//                            }
//                        }
                        lv_.setClassName(ConstClasses.resolve(className_));
                        lv_.setIndexClassName(ConstClasses.resolve(indexClassName_));
                        varsLoop_.put(value_, lv_);
                    }
                    LoopHtmlStack l_ = new LoopHtmlStack();
                    ip_.addBlock(l_);
                    for (StringList l: vars_) {
                        for (String v: l) {
                            if (varsLoc_.containsStr(v)) {
                                StringList alls_ = new StringList();
                                for (StringList g: vars_) {
                                    alls_.addAllElts(g);
                                }
                                throw new AlreadyDefinedVarException(StringList.concat(alls_.display(),RETURN_LINE,v,RETURN_LINE,_conf.joinPages()));
                            }
                        }
                    }
                    if (elt_.hasChildNodes()) {
                        vars_.add(varsLoc_);
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,SELECT_TAG))) {
                    if (!elt_.getAttribute(ATTRIBUTE_MAP).isEmpty()) {
                        String mapAttr_ = elt_.getAttribute(ATTRIBUTE_MAP);
                        ip_.setProcessingAttribute(ATTRIBUTE_MAP);
                        ip_.setLookForAttrValue(true);
                        ip_.setOffset(0);
                        CustElUtil.processAnalyzeEl(mapAttr_, 0, _conf.toContextEl());
                        //System.out.println(CustElUtil.processAnalyzeEl(mapAttr_, 0, _conf.toContextEl()));
                        mapAttr_ = elt_.getAttribute(ATTRIBUTE_VAR_VALUE);
                        ip_.setProcessingAttribute(ATTRIBUTE_VAR_VALUE);
                        ip_.setLookForAttrValue(true);
                        ip_.setOffset(0);
                        CustElUtil.processAnalyzeEl(mapAttr_, 0, _conf.toContextEl());
                        String class_ = elt_.getAttribute(ATTRIBUTE_CLASS_NAME);
                        SELECT_CLASSES.add(class_);
                        //TODO name
                        //System.out.println(CustElUtil.processAnalyzeEl(mapAttr_, 0, _conf.toContextEl()));
                    }
                    String name_ = elt_.getAttribute(ATTRIBUTE_NAME);
                    if (!name_.isEmpty()) {
                        ip_.setProcessingAttribute(ATTRIBUTE_NAME);
                        ip_.setLookForAttrValue(true);
                        ip_.setOffset(0);
                        setAnalyzedIndexes(_conf, ip_, elt_, name_);
                    }
                    if (elt_.getAttribute(ATTRIBUTE_MAP).isEmpty()) {
                        if (elt_.getAttribute(ATTRIBUTE_LIST).isEmpty()) {
                            _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
                            _conf.getLastPage().setOffset(0);
                            _conf.getLastPage().setLookForAttrValue(false);
                            throw new BadSelectException(_conf.joinPages());
                        }
                    }
                } else if (ExtractCondition.isBeginOfConditionNode(_conf, elt_)) {
                    String condition_ = elt_.getAttribute("condition");
                    ip_.setProcessingAttribute("condition");
                    ip_.setLookForAttrValue(true);
                    ip_.setOffset(0);
                    CustElUtil.processAnalyzeEl(condition_, 0, _conf.toContextEl());
                } else if (ExtractCondition.isContentOfConditionNode(_conf, elt_)) {
                    String condition_ = elt_.getAttribute("condition");
                    ip_.setProcessingAttribute("condition");
                    ip_.setLookForAttrValue(true);
                    ip_.setOffset(0);
                    CustElUtil.processAnalyzeEl(condition_, 0, _conf.toContextEl());
                    Node prev_ = elt_.getPreviousSibling();
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
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,ELSE_BLOCK_TAG))) {
                    Node prev_ = elt_.getPreviousSibling();
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
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,TRY_TAG))) {
                    if (elt_.getFirstChild() == null) {
                        throw new BadCatchException(_conf.joinPages());
                    }
                    Node next_ = elt_.getNextSibling();
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
                        if (next_ instanceof Text){
                            break;
                        }
                        if (StringList.quickEq(((Element) next_).getTagName(),StringList.concat(prefix_,TAG_FINALLY))) {
                            existCatch_ = true;
                            break;
                        }
                        existCatch_ = StringList.quickEq(((Element) next_).getTagName(),StringList.concat(prefix_,CATCH_TAG));
                        break;
                    }
                    if (!existCatch_) {
                        throw new BadCatchException(_conf.joinPages());
                    }
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,CATCH_TAG))) {
                    String className_ = elt_.getAttribute(ATTRIBUTE_CLASS_NAME);
                    _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _conf.getLastPage().setOffset(0);
                    _conf.getLastPage().setLookForAttrValue(false);
                    ExtractObject.classNameForName(_conf, 0, className_);
                    String var_ = elt_.getAttribute(ATTRIBUTE_VAR);
                    if (!StringList.isWord(var_)) {
                        throw new BadVariableNameException(var_, _conf.joinPages(), ATTRIBUTE_VAR);
                    }
                    Node prev_ = elt_.getPreviousSibling();
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
                        if (prev_ instanceof Text){
                            break;
                        }
                        if (StringList.quickEq(((Element) prev_).getTagName(),StringList.concat(prefix_,CATCH_TAG))) {
                            prev_ = prev_.getPreviousSibling();
                            continue;
                        }
                        existTry_ = StringList.quickEq(((Element) prev_).getTagName(),StringList.concat(prefix_,TRY_TAG));
                        break;
                    }
                    if (!existTry_) {
                        throw new BadTryException(_conf.joinPages());
                    }
                    for (String v: catchVars_) {
                        if (StringList.quickEq(var_, v)) {
                            StringList alls_ = new StringList();
                            for (StringList g: vars_) {
                                alls_.addAllElts(g);
                            }
                            throw new AlreadyDefinedVarException(StringList.concat(alls_.display(),RETURN_LINE,v,RETURN_LINE,_conf.joinPages()));
                        }
                    }
                    if (elt_.hasChildNodes()) {
                        catchVars_.add(var_);
                    }
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,TAG_FINALLY))) {
                    if (elt_.getFirstChild() == null) {
                        throw new BadTryException(_conf.joinPages());
                    }
                    Node prev_ = elt_.getPreviousSibling();
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
                        if (prev_ instanceof Text){
                            break;
                        }
                        if (StringList.quickEq(((Element) prev_).getTagName(),StringList.concat(prefix_,CATCH_TAG))) {
                            prev_ = prev_.getPreviousSibling();
                            continue;
                        }
                        existTry_ = StringList.quickEq(((Element) prev_).getTagName(),StringList.concat(prefix_,TRY_TAG));
                        break;
                    }
                    if (!existTry_) {
                        throw new BadTryException(_conf.joinPages());
                    }
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,BREAK_TAG))) {
                    Element parent_ = elt_.getParentNode();
                    boolean ok_ = false;
                    while (parent_ != null) {
                        if (StringList.quickEq(parent_.getTagName(),StringList.concat(prefix_,FOR_BLOCK_TAG))) {
                            ok_ = true;
                            break;
                        }
                        if (StringList.quickEq(parent_.getTagName(),StringList.concat(prefix_,WHILE_BLOCK_TAG))) {
                            ok_ = true;
                            break;
                        }
                        if (StringList.quickEq(parent_.getTagName(),StringList.concat(prefix_,TAG_DO))) {
                            ok_ = true;
                            break;
                        }
                        if (StringList.quickEq(parent_.getTagName(),StringList.concat(prefix_,TAG_SWITCH))) {
                            ok_ = true;
                            break;
                        }
                        parent_ = parent_.getParentNode();
                    }
                    if (!ok_) {
                        throw new BadTagBreakException(_conf.joinPages());
                    }
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,CONTINUE_TAG))) {
                    Element parent_ = elt_.getParentNode();
                    boolean ok_ = false;
                    while (parent_ != null) {
                        if (StringList.quickEq(parent_.getTagName(),StringList.concat(prefix_,FOR_BLOCK_TAG))) {
                            ok_ = true;
                            break;
                        }
                        if (StringList.quickEq(parent_.getTagName(),StringList.concat(prefix_,WHILE_BLOCK_TAG))) {
                            ok_ = true;
                            break;
                        }
                        if (StringList.quickEq(parent_.getTagName(),StringList.concat(prefix_,TAG_DO))) {
                            ok_ = true;
                            break;
                        }
                        parent_ = parent_.getParentNode();
                    }
                    if (!ok_) {
                        throw new BadTagContinueException(_conf.joinPages());
                    }
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,TAG_SWITCH))) {
                    Node first_ = elt_.getFirstChild();
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
                        if (StringList.quickEq(((Element) first_).getTagName(),StringList.concat(prefix_,TAG_CASE))) {
                            first_ = first_.getNextSibling();
                            continue;
                        }
                        if (StringList.quickEq(((Element) first_).getTagName(),StringList.concat(prefix_,TAG_DEFAULT))) {
                            first_ = first_.getNextSibling();
                            continue;
                        }
                        throw new BadSwitchException(_conf.joinPages());
                    }
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,TAG_CASE))) {
                    if (elt_.getParentNode() == null || !StringList.quickEq(elt_.getParentNode().getTagName(), StringList.concat(prefix_,TAG_SWITCH))) {
                        throw new BadCaseException(_conf.joinPages());
                    }
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,TAG_DEFAULT))) {
                    if (elt_.getParentNode() == null || !StringList.quickEq(elt_.getParentNode().getTagName(), StringList.concat(prefix_,TAG_SWITCH))) {
                        throw new BadDefaultException(_conf.joinPages());
                    }
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,WHILE_BLOCK_TAG))) {
                    if (elt_.getFirstChild() != null) {
                        Node prev_ = elt_.getPreviousSibling();
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
                            if (prev_ instanceof Text){
                                break;
                            }
                            existDo_ = StringList.quickEq(((Element) prev_).getTagName(),StringList.concat(prefix_,TAG_DO));
                            break;
                        }
                        if (existDo_) {
                            throw new BadLoopException(_conf.joinPages());
                        }
                    }
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,TAG_DO))) {
                    Node next_ = elt_.getNextSibling();
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
                        if (next_ instanceof Text){
                            break;
                        }
                        existWhile_ = StringList.quickEq(((Element) next_).getTagName(),StringList.concat(prefix_,WHILE_BLOCK_TAG));
                        break;
                    }
                    if (!existWhile_) {
                        throw new BadLoopException(_conf.joinPages());
                    }
                } else if (StringList.quickEq(elt_.getTagName(),StringList.concat(prefix_,MESSAGE_BLOCK_TAG))) {
                    String preformatted_;
                    String value_ = elt_.getAttribute(ATTRIBUTE_VALUE);
                    StringList elts_ = StringList.splitStrings(value_, COMMA);
                    String var_ = elts_.first();
                    String fileName_ = ExtractObject.getProperty(_conf, var_);
                    if (fileName_ == null) {
                        fileName_ = var_;
                    }
                    String key_ = elts_.last();
                    if (!_conf.noPages()) {
                        _conf.getLastPage().setOffset(var_.length()+COMMA.length());
                    }
                    if (value_.startsWith(CALL_METHOD)) {
                        System.out.println(CustElUtil.processAnalyzeEl(value_, 1, _conf.toContextEl()));
                    } else if (!key_.contains("{")) {
                        StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, files_, _resourcesFolder);
                        preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
                        if (!_conf.noPages()) {
                            _conf.getLastPage().setKey(key_);
                            _conf.getLastPage().setMessageValue(preformatted_);
                        }
                        preformatted_ = DocumentBuilder.transformSpecialChars(preformatted_, elt_.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
                        if (!_conf.noPages()) {
                            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VALUE);
                            _conf.getLastPage().setLookForAttrValue(true);
                        }
                        if (elt_.getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
                            ImportingPage ipMess_ = new ImportingPage(false);
                            ipMess_.setTabWidth(getTabWidth(_conf));
                            ipMess_.setBeanName(ip_.getBeanName());
                            boolean treatAsFullHtml_ = false;
                            if (value_.endsWith(LT_END_TAG+TAG_HTML+GT_TAG) && value_.startsWith(LT_BEGIN_TAG+TAG_HTML)) {
                                if (Character.isWhitespace(value_.charAt(TAG_HTML.length()+1))) {
                                    treatAsFullHtml_ = true;
                                } else if (value_.charAt(TAG_HTML.length()+1) == GT_TAG) {
                                    treatAsFullHtml_ = true;
                                }
                            }
                            if (treatAsFullHtml_) {
                                ipMess_.setHtml(preformatted_);
                            } else {
                                ipMess_.setHtml(addToRoot(_conf, ip_.getPrefix(), preformatted_));
                            }
                            ipMess_.setReadUrl(_conf.getResourceUrl());
                            ipMess_.setCatchVars(ip_.getCatchVars());
                            ipMess_.setLocalVars(ip_.getLocalVars());
                            ipMess_.setVars(ip_.getVars());
                            ipMess_.setReadWrite(ip_.getReadWrite());
                            ipMess_.setBlockStacks(ip_.getBlockStacks());
                            ipMess_.setMessageValue(ip_.getMessageValue());
                            ipMess_.setKey(ip_.getKey());
                            ipMess_.setGlobalClass(ip_.getGlobalClass());
                            ip_.setMessageValue(null);
                            ip_.setKey(null);
                            _conf.addPage(ipMess_);
                            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(ipMess_.getHtml());
                            Document docLoc_ = res_.getDocument();
                            if (docLoc_ == null) {
                                throw new XmlParseException(StringList.concat(res_.getLocation().display(),RETURN_LINE,ipMess_.getHtml(),RETURN_LINE,_conf.joinPages()));
                            }
                            ipMess_.setPrefix(getPrefix(_conf, docLoc_));
                            ipMess_.setRoot(docLoc_.getDocumentElement());
                            Element rootLoc_ = docLoc_.getDocumentElement();
                            CustList<NodeAction> nas_ = getDeepChildNodesDocOrder(rootLoc_);
                            for (NodeAction n: nas_) {
                                //                                if (n.getActions().first() == ActionNext.NONE) {
                                //                                    continue;
                                //                                }
                                Node nLoc_ = n.getNode();
                                if (nLoc_ instanceof Text) {
                                } else if (nLoc_ instanceof Element) {
                                    ipMess_.setProcessingNode(nLoc_);
                                    Element eltLoc_ = (Element) nLoc_;
                                    if (StringList.quickEq(eltLoc_.getTagName(),TAG_A)) {
                                        String attr_ = eltLoc_.getAttribute(ATTRIBUTE_HREF);
                                        String resAttr_ = EMPTY_STRING;
                                        if (!attr_.isEmpty()) {
                                            CustList<BlockHtml> stacks_ = ip_.getBlockStacks();
                                            attr_ = format(stacks_, attr_, false);
                                            if (attr_.startsWith("$")) {
                                                resAttr_ = attr_.substring(1);
                                                if (!resAttr_.endsWith(")")) {
                                                    resAttr_ += "()";
                                                }
                                            }
                                        }
                                        attr_ = eltLoc_.getAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND);
                                        if (!attr_.isEmpty()) {
                                            CustList<BlockHtml> stacks_ = ip_.getBlockStacks();
                                            attr_ = format(stacks_, attr_, false);
                                            if (resAttr_.isEmpty() && attr_.startsWith("$")) {
                                                resAttr_ = attr_.substring(1);
                                                if (!resAttr_.endsWith(")")) {
                                                    resAttr_ += "()";
                                                }
                                            }
                                        }
                                        String resLoc_ = resAttr_;
                                        if (!resLoc_.isEmpty()) {
                                            resLoc_ = quickChange(resLoc_, _conf, ip_);
                                            CustElUtil.processAnalyzeEl(resLoc_, 0, _conf.toContextEl());
                                            //System.out.println(CustElUtil.processAnalyzeEl(resLoc_, 0, _conf.toContextEl()));
                                        }
                                    }
                                    //                                    if (StringList.quickEq(tag_.getTagName(), TAG_A) && (tag_.hasAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND)|| !tag_.getAttribute(ATTRIBUTE_HREF).isEmpty() )) {
                                    //                                        tag_.setAttribute(NUMBER_ANCHOR, String.valueOf(currentAnchor_));
                                    //                                        currentAnchor_++;
                                    //                                        _indexes.setAnchor(currentAnchor_);
                                    //                                    }
                                }
                            }
                            _conf.removeLastPage();
                        }
                    } else {
                        System.err.println("\t"+key_);
                    }
                    for (Element n: elt_.getChildElements()) {
                        String attribute_ = n.getAttribute(ATTRIBUTE_VALUE);
                        if (n.hasAttribute("quoted")) {
                            continue;
                        }
                        if (!_conf.noPages()) {
                            _conf.getLastPage().setProcessingNode(n);
                            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VALUE);
                            _conf.getLastPage().setLookForAttrValue(true);
                            _conf.getLastPage().setOffset(0);
                        }
                        int begin_ = 0;
                        if (attribute_.startsWith(CALL_METHOD)) {
                            begin_ = 1;
                        }
                        CustElUtil.processAnalyzeEl(attribute_, begin_, _conf.toContextEl());
                        //System.out.println(CustElUtil.processAnalyzeEl(attribute_, begin_, _conf.toContextEl()));
                    }
                } else if (StringList.quickEq(elt_.getTagName(),prefix_+IMPORT_BLOCK_TAG)) {
                    for (Element n: elt_.getChildElements()) {
                        if (!StringList.quickEq(n.getTagName(),prefix_+PACKAGE_BLOCK_TAG)) {
                            continue;
                        }
                        String package_ = n.getAttribute(ATTRIBUTE_NAME);
                        for (Element nTwo_: n.getChildElements()) {
                            if (!StringList.quickEq(nTwo_.getTagName(),prefix_+CLASS_BLOCK_TAG)) {
                                continue;
                            }
                            String className_ = nTwo_.getAttribute(ATTRIBUTE_NAME);
                            ip_.setProcessingNode(elt_);
                            ip_.setProcessingAttribute(EMPTY_STRING);
                            ip_.setOffset(0);
                            ip_.setLookForAttrValue(false);
                            String searchedClass_ = package_+DOT+className_;
                            ip_.setProcessingNode(nTwo_);
                            ip_.setProcessingAttribute(ATTRIBUTE_NAME);
                            ip_.setOffset(0);
                            ip_.setLookForAttrValue(true);
                            ExtractObject.classNameForName(_conf, 0, searchedClass_);
                            for (Element nThree_: nTwo_.getChildElements()) {
                                if (!StringList.quickEq(nThree_.getTagName(),prefix_+FIELD_BLOCK_TAG)) {
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
                                    CustElUtil.processAnalyzeEl(fieldValue_, 0, _conf.toContextEl());
                                    //System.out.println(CustElUtil.processAnalyzeEl(fieldValue_, 0, _conf.toContextEl()));;
                                    ip_.setProcessingNode(nThree_);
                                    ip_.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                                    ip_.setOffset(0);
                                    ip_.setLookForAttrValue(true);
                                    ExtractObject.classNameForName(_conf, 0, classNameParam_);
                                    ip_.setProcessingNode(nThree_);
                                    ip_.setProcessingAttribute(ATTRIBUTE_METHOD);
                                    ip_.setOffset(0);
                                    ip_.setLookForAttrValue(true);
                                    ip_.setGlobalClass(searchedClass_);
                                    LocalVariable lv_ = new LocalVariable();
                                    lv_.setClassName(ConstClasses.resolve(classNameParam_));
                                    String nameVar_ = ip_.getNextTempVar();
                                    ip_.getLocalVars().put(nameVar_, lv_);
                                    CustElUtil.processAnalyzeEl(methodName_+"("+nameVar_+GET_LOC_VAR+")", 0, _conf.toContextEl());
                                    //System.out.println(CustElUtil.processAnalyzeEl(methodName_+LEFT_PAR+nameVar_+GET_LOC_VAR+RIGHT_PAR, 0, _conf.toContextEl()));;
                                    ip_.getLocalVars().removeKey(nameVar_);
                                    continue;
                                }
                                ip_.setProcessingNode(nThree_);
                                ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
                                ip_.setOffset(0);
                                ip_.setLookForAttrValue(true);
                                String fieldValue_ = nThree_.getAttribute(ATTRIBUTE_VALUE);
                                String argt_ = CustElUtil.processAnalyzeEl(fieldValue_, 0, _conf.toContextEl());
                                LocalVariable lv_ = new LocalVariable();
                                lv_.setClassName(searchedClass_);
                                String nameVar_ = ip_.getNextTempVar();
                                ip_.getLocalVars().put(nameVar_, lv_);
                                ip_.setProcessingNode(nThree_);
                                ip_.setProcessingAttribute(ATTRIBUTE_NAME);
                                ip_.setOffset(0);
                                ip_.setLookForAttrValue(true);
                                String fieldName_ = nThree_.getAttribute(ATTRIBUTE_NAME);
                                String nameValue_ = ip_.getNextTempVar();
                                lv_ = new LocalVariable();
                                lv_.setClassName(argt_);
                                ip_.getLocalVars().put(nameValue_, lv_);
                                String expressionLeft_ = nameVar_ + GET_LOC_VAR + fieldName_;
                                String expressionRight_ = nameValue_ + GET_LOC_VAR;
                                CustElUtil.processAnalyzeAffect(EMPTY_STRING, ATTRIBUTE_NAME, ATTRIBUTE_VALUE, expressionLeft_, expressionRight_, String.valueOf(EQUALS), _conf.toContextEl(), true, true);
                                ip_.getLocalVars().removeKey(nameVar_);
                                ip_.getLocalVars().removeKey(nameValue_);
                            }
                        }
                    }
                } else {
                    //TODO attributes and names of inputs
                    String prefixWrite_ = _conf.getPrefix();
                    //                    String beanName_ = _ip.getBeanName();
                    StringList attributesNames_ = new StringList();
                    StringList allAttributesNames_ = new StringList();
                    NamedNodeMap mapAttr_ = elt_.getAttributes();
                    int nbAttrs_ = mapAttr_.getLength();
                    for (int i = 0; i < nbAttrs_; i++) {
                        attributesNames_.add(mapAttr_.item(i).getName());
                    }
                    allAttributesNames_.addAllElts(attributesNames_);
                    attributesNames_.removeAllString(ATTRIBUTE_ID);
                    //                    String id_ = _tag.getAttribute(ATTRIBUTE_ID);
                    attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_GROUP_ID);
                    //                    String groupId_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID);
                    //                    if (StringList.quickEq(_tag.getTagName(),prefixWrite_+SUBMIT_BLOCK_TAG) && !prefixWrite_.isEmpty()) {
                    //                        attributesNames_.removeAllString(ATTRIBUTE_VALUE_SUBMIT);
                    //                        String value_ = _tag.getAttribute(ATTRIBUTE_VALUE_SUBMIT);
                    //                        StringList elts_ = StringList.splitStrings(value_, COMMA);
                    //                        String var_ = elts_.first();
                    //                        String fileName_ = ExtractObject.getProperty(_conf, var_);
                    //                        if (fileName_ == null) {
                    //                            fileName_ = var_;
                    //                        }
                    //                        StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
                    //                        _ip.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
                    //                        _ip.setOffset(var_.length()+1);
                    //                        _ip.setLookForAttrValue(true);
                    //                        String key_ = elts_.last();
                    //                        String preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
                    //                        preformatted_ = DocumentBuilder.transformSpecialChars(preformatted_, _tag.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
                    //                        _tag.removeAttribute(ATTRIBUTE_VALUE_SUBMIT);
                    //                        _tag.removeAttribute(ATTRIBUTE_ESCAPED_EAMP);
                    //                        IdList<Object> objects_ = new IdList<Object>();
                    //                        attributesNames_.removeAllString(ATTRIBUTE_VALUE);
                    //                        attributesNames_.removeAllString(ATTRIBUTE_TYPE);
                    //                        _tag.setAttribute(ATTRIBUTE_VALUE, StringList.simpleFormat(preformatted_, objects_.toArray()));
                    //                        _tag.setAttribute(ATTRIBUTE_TYPE, SUBMIT_TYPE);
                    //                        _doc.renameNode(_tag, INPUT_TAG);
                    //                    }
                    //                    if (StringList.quickEq(_tag.getTagName(),prefixWrite_+TAG_A) && !prefixWrite_.isEmpty()) {
                    //                        attributesNames_.removeAllString(ATTRIBUTE_VALUE);
                    //                        String value_ = _tag.getAttribute(ATTRIBUTE_VALUE);
                    //                        StringList elts_ = StringList.splitStrings(value_, COMMA);
                    //                        String var_ = elts_.first();
                    //                        String fileName_ = ExtractObject.getProperty(_conf, var_);
                    //                        if (fileName_ == null) {
                    //                            fileName_ = var_;
                    //                        }
                    //                        StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
                    //                        _ip.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
                    //                        _ip.setOffset(var_.length()+1);
                    //                        _ip.setLookForAttrValue(true);
                    //                        String key_ = elts_.last();
                    //                        String preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
                    //                        preformatted_ = DocumentBuilder.transformSpecialChars(preformatted_, _tag.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
                    //                        _tag.removeAttribute(ATTRIBUTE_VALUE_SUBMIT);
                    //                        _tag.removeAttribute(ATTRIBUTE_VALUE);
                    //                        IdList<Object> objects_ = new IdList<Object>();
                    //                        attributesNames_.removeAllString(ATTRIBUTE_TITLE);
                    //                        _tag.setAttribute(ATTRIBUTE_TITLE, StringList.simpleFormat(preformatted_, objects_.toArray()));
                    //                        _doc.renameNode(_tag, TAG_A);
                    //                    }
                    if (StringList.quickEq(elt_.getTagName(),prefixWrite_+TAG_IMG) && !prefixWrite_.isEmpty()) {
                    } else if (StringList.quickEq(elt_.getTagName(),TAG_IMG)) {
                        String src_ = elt_.getAttribute(ATTRIBUTE_SRC);
                        if (!src_.isEmpty()) {
                            ip_.setProcessingAttribute(ATTRIBUTE_SRC);
                            ip_.setLookForAttrValue(true);
                            ip_.setOffset(0);
                            formatAnalyzeNumVariables(src_, _conf, ip_);
                        }
                    }
                    //                    if (StringList.quickEq(_tag.getTagName(),TAG_LINK)) {
                    //                        NodeList heads_ = _doc.getElementsByTagName(TAG_HEAD);
                    //                        attributesNames_.removeAllString(ATTRIBUTE_HREF);
                    //                        attributesNames_.removeAllString(ATTRIBUTE_REL);
                    //                        String href_ = getCssHref(_tag);
                    //                        if (href_ != null && heads_.getLength() == CustList.ONE_ELEMENT){
                    //                            Element head_ = (Element) heads_.item(CustList.FIRST_INDEX);
                    //                            CustList<Element> children_ = new CustList<Element>();
                    //                            for (Element c: head_.getChildElements()) {
                    //                                if (!StringList.quickEq(c.getTagName(), TAG_STYLE)) {
                    //                                    continue;
                    //                                }
                    //                                children_.add(c);
                    //                            }
                    //                            _ip.setProcessingAttribute(ATTRIBUTE_HREF);
                    //                            _ip.setLookForAttrValue(true);
                    //                            _ip.setOffset(0);
                    //                            CustList<String> objects_ = new CustList<String>();
                    //                            String fileContent_ = ExtractFromResources.getContentFile(_conf, _files, href_, _resourcesFolder);
                    //                            if (!objects_.isEmpty()) {
                    //                                fileContent_ = StringList.simpleFormat(fileContent_, objects_.toArray());
                    //                            }
                    //                            boolean successAdd_ = children_.isEmpty();
                    //                            if (!successAdd_) {
                    //                                Element eltStyle_ = children_.last();
                    //                                CustList<Node> chNode_ = eltStyle_.getChildNodes();
                    //                                if (chNode_.isEmpty()) {
                    //                                    Text text_ = _doc.createTextNode(fileContent_);
                    //                                    eltStyle_.appendChild(text_);
                    //                                } else {
                    //                                    Text text_ = (Text) chNode_.last();
                    //                                    text_.appendData(fileContent_);
                    //                                }
                    //                            } else {
                    //                                Element eltStyle_ = _doc.createElement(TAG_STYLE);
                    //                                Text text_ = _doc.createTextNode(fileContent_);
                    //                                eltStyle_.appendChild(text_);
                    //                                head_.appendChild(eltStyle_);
                    //                            }
                    //                        }
                    //                    }
                    //                    if (StringList.quickEq(_tag.getTagName(),TAG_STYLE)) {
                    //                        NodeList links_ = _doc.getElementsByTagName(TAG_LINK);
                    //                        int len_ = links_.getLength();
                    //                        StringList refs_ = new StringList();
                    //                        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    //                            Element link_ = (Element) links_.item(i);
                    //                            String href_ = getCssHref(link_);
                    //                            if (href_ != null) {
                    //                                refs_.add(href_);
                    //                            }
                    //                        }
                    //                        StringList filesContents_ = new StringList();
                    //                        for (String r: refs_) {
                    //                            filesContents_.add(ExtractFromResources.getContentFile(_conf, _files, r, _resourcesFolder));
                    //                        }
                    //                        NodeList chNode_ = _tag.getChildNodes();
                    //                        if (chNode_.isEmpty()) {
                    //                            Text text_ = _doc.createTextNode(filesContents_.join(RETURN_LINE));
                    //                            _tag.appendChild(text_);
                    //                        } else {
                    //                            Text text_ = (Text) chNode_.last();
                    //                            text_.appendData(filesContents_.join(RETURN_LINE));
                    //                        }
                    //                    }
                    //                    if (StringList.quickEq(_tag.getTagName(),SCRIPT)) {
                    //                        NamedNodeMap map_ = _tag.getAttributes();
                    //                        Attr href_ = map_.getNamedItem(ATTRIBUTE_HREF);
                    //                        if (href_ != null){
                    //                            _ip.setProcessingAttribute(ATTRIBUTE_HREF);
                    //                            _ip.setLookForAttrValue(true);
                    //                            _ip.setOffset(0);
                    //                            attributesNames_.removeAllString(ATTRIBUTE_HREF);
                    //                            attributesNames_.removeAllString(ATTRIBUTE_TYPE);
                    //                            String ref_ = href_.getValue();
                    //                            ref_ = ExtractObject.formatNumVariables(ref_, _conf, _ip, _files);
                    //                            _tag.setAttribute(ATTRIBUTE_TYPE, SCRIPT_TYPE);
                    //                            Text txt_ = _doc.createTextNode(ExtractFromResources.getContentFile(_conf, _files,ref_,_resourcesFolder));
                    //                            _tag.appendChild(txt_);
                    //                        }
                    //                    }
                    String accessName_ = EMPTY_STRING;
                    if (StringList.quickEq(elt_.getTagName(),INPUT_TAG)) {
                        //format name
                        attributesNames_.removeAllString(ATTRIBUTE_VALUE);
                        attributesNames_.removeAllString(ATTRIBUTE_NAME);
                        attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_CLASS_NAME);
                        attributesNames_.removeAllString(_conf.getPrefix()+VAR_METHOD);
                        String name_ = elt_.getAttribute(ATTRIBUTE_NAME);
                        accessName_ = name_;
                        if (!name_.isEmpty()) {
                            ip_.setProcessingAttribute(ATTRIBUTE_NAME);
                            ip_.setLookForAttrValue(true);
                            ip_.setOffset(0);
                            setAnalyzedIndexes(_conf, ip_, elt_, name_);
                            attributesNames_.removeAllString(NUMBER_INPUT);
                        }
                    }
                    if (StringList.quickEq(elt_.getTagName(),TEXT_AREA)) {
                        //format name
                        attributesNames_.removeAllString(ATTRIBUTE_NAME);
                        attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_CLASS_NAME);
                        attributesNames_.removeAllString(_conf.getPrefix()+VAR_METHOD);
                        String name_ = elt_.getAttribute(ATTRIBUTE_NAME);
                        if (!name_.isEmpty()) {
                            ip_.setProcessingAttribute(ATTRIBUTE_NAME);
                            ip_.setLookForAttrValue(true);
                            ip_.setOffset(0);
                            setAnalyzedIndexes(_conf, ip_, elt_, name_);
                            attributesNames_.removeAllString(NUMBER_INPUT);
                        }
                    }
                    if (elt_.hasAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE)) {
                        if (StringList.quickEq(elt_.getTagName(),INPUT_TAG)) {
                            attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
                            attributesNames_.removeAllString(ATTRIBUTE_VALUE);
                            attributesNames_.removeAllString(CHECKED);
                            setAnalyzedValueInput(_conf, elt_);
                        }
                        if (StringList.quickEq(elt_.getTagName(),TEXT_AREA)) {
                            attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
                            setAnalyzedValueTextArea(_conf, _doc, elt_);
                        }
                    }
                    if (StringList.quickEq(elt_.getTagName(),INPUT_TAG)) {
                        attributesNames_.removeAllString(ATTRIBUTE_TYPE);
                        if (StringList.quickEq(elt_.getAttribute(ATTRIBUTE_TYPE),RADIO)) {
                            attributesNames_.removeAllString(CHECKED);
                            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_NAME);
                            _conf.getLastPage().setLookForAttrValue(true);
                            _conf.getLastPage().setOffset(0);
                            if (!accessName_.isEmpty()) {
                                CustElUtil.processAnalyzeEl(accessName_, 0, _conf.toContextEl());
                                //System.out.println(CustElUtil.processAnalyzeEl(accessName_, 0, _conf.toContextEl()));
                            }
                        }
                    }
                    if (StringList.quickEq(elt_.getTagName(),TAG_A)) {
                        String attr_ = elt_.getAttribute(ATTRIBUTE_HREF);
                        String resAttr_ = EMPTY_STRING;
                        if (!attr_.isEmpty()) {
                            CustList<BlockHtml> stacks_ = ip_.getBlockStacks();
                            attr_ = format(stacks_, attr_, false);
                            if (attr_.startsWith("$")) {
                                resAttr_ = attr_.substring(1);
                                if (!resAttr_.endsWith(")")) {
                                    resAttr_ += "()";
                                }
                            }
                            attributesNames_.removeAllString(ATTRIBUTE_HREF);
                        }
                        attr_ = elt_.getAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND);
                        if (!attr_.isEmpty()) {
                            CustList<BlockHtml> stacks_ = ip_.getBlockStacks();
                            attr_ = format(stacks_, attr_, false);
                            if (resAttr_.isEmpty() && attr_.startsWith("$")) {
                                resAttr_ = attr_.substring(1);
                                if (!resAttr_.endsWith(")")) {
                                    resAttr_ += "()";
                                }
                            }
                            attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_COMMAND);
                        }
                        String res_ = resAttr_;
                        if (!res_.isEmpty()) {
                            res_ = quickChange(res_, _conf, ip_);
                            CustElUtil.processAnalyzeEl(res_, 0, _conf.toContextEl());
                            //System.out.println(CustElUtil.processAnalyzeEl(res_, 0, _conf.toContextEl()));
                        }
                    }
                    if (StringList.quickEq(elt_.getTagName(),TAG_FORM)) {
                        String attr_ = elt_.getAttribute(ATTRIBUTE_ACTION);
                        String resAttr_ = EMPTY_STRING;
                        if (!attr_.isEmpty()) {
                            CustList<BlockHtml> stacks_ = ip_.getBlockStacks();
                            attr_ = format(stacks_, attr_, false);
                            if (attr_.startsWith("$")) {
                                resAttr_ = attr_.substring(1);
                                if (!resAttr_.endsWith(")")) {
                                    resAttr_ += "()";
                                }
                            }
                            attributesNames_.removeAllString(ATTRIBUTE_ACTION);
                        }
                        attr_ = elt_.getAttribute(_conf.getPrefix()+ATTRIBUTE_COMMAND);
                        if (!attr_.isEmpty()) {
                            CustList<BlockHtml> stacks_ = ip_.getBlockStacks();
                            attr_ = format(stacks_, attr_, false);
                            if (resAttr_.isEmpty() && attr_.startsWith("$")) {
                                resAttr_ = attr_.substring(1);
                                if (!resAttr_.endsWith(")")) {
                                    resAttr_ += "()";
                                }
                            }
                            attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_COMMAND);
                        }
                        attr_ = elt_.getAttribute(ATTRIBUTE_NAME);
                        if (!attr_.isEmpty()) {
                            attributesNames_.removeAllString(ATTRIBUTE_NAME);
                        }
                        String res_ = resAttr_;
                        if (!res_.isEmpty()) {
                            CustElUtil.processAnalyzeEl(res_, 0, _conf.toContextEl());
                            //System.out.println(CustElUtil.processAnalyzeEl(res_, 0, _conf.toContextEl()));
                        }
                    }
                    if (!StringList.quickEq(elt_.getTagName(),prefixWrite_+TR_END_BLOCK_TAG) || prefixWrite_.isEmpty()) {
                        for (String a: attributesNames_) {
                            String v_ = elt_.getAttribute(a);
                            if (!v_.isEmpty()) {
                                if (StringList.quickEq(a, ATTRIBUTE_CLASS)) {
                                    CustList<BlockHtml> stacks_ = ip_.getBlockStacks();
                                    v_ = format(stacks_, v_, false);
                                    ip_.setProcessingAttribute(a);
                                    ip_.setLookForAttrValue(true);
                                    ip_.setOffset(1);
                                    evaluateAnalyzedAttribute(_conf, elt_, a);
                                } else {
                                    ip_.setProcessingAttribute(a);
                                    ip_.setLookForAttrValue(true);
                                    ip_.setOffset(0);
                                    formatAnalyzeNumVariables(v_, _conf, ip_);
                                }
                            }
                        }
                    }
                }
                int endHeader_ = _html.indexOf(GT_TAG, indexGlobal_);
                int beginHeader_ = indexGlobal_ + elt_.getTagName().length();
                StringMap<AttributePart> attr_;
                attr_ = getAttributes(_html, beginHeader_, endHeader_);
                for (EntryCust<String, AttributePart> e: attr_.entryList()) {
                    NodeAttribute nodeAttr_ = new NodeAttribute();
                    nodeAttr_.setNode(elt_);
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
                String text_ = _html.substring(indexGlobal_, endHeader_);
                if (!text_.trim().isEmpty()) {
                    formatAnalyzeNumVariables(text_, _conf, ip_);
                }
                infos_.put(nodeAttr_, getIndexesSpecChars(_html, false, attrPart_, indexGlobal_));
                indexGlobal_ = endHeader_;
            } else {
                indexGlobal_ = _html.indexOf(END_COMMENT, indexGlobal_+"--".length())+END_COMMENT.length()-1;
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
            if (StringList.quickEq(((Element) n_).getTagName(), prefix_+FOR_BLOCK_TAG)) {
                vars_.removeLast();
                ip_.removeLastBlock();
            }
            if (StringList.quickEq(((Element) n_).getTagName(), prefix_+CATCH_TAG)) {
                catchVars_.removeLast();
            }
            Node next_ = n_.getNextSibling();
            while (next_ == null) {
                Element par_ = n_.getParentNode();
                if (par_ == root_) {
                    break;
                }
                if (StringList.quickEq(par_.getTagName(), prefix_+FOR_BLOCK_TAG)) {
                    vars_.removeLast();
                    ip_.removeLastBlock();
                }
                if (StringList.quickEq(par_.getTagName(), prefix_+CATCH_TAG)) {
                    catchVars_.removeLast();
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
    private static int indexOfBeginNode(Node _node, String _html, int _from) {
        if (_node instanceof Element) {
            return _html.indexOf(StringList.concat(String.valueOf(LT_BEGIN_TAG),((Element) _node).getTagName()), _from) + 1;
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
        return _html.indexOf(StringList.concat(String.valueOf(LT_BEGIN_TAG),COMMENT), _from);
    }
//    private static StringMap<String> getAnaInnerMessagesFromLocaleClass(Configuration _conf, String _loc, String _relative, StringMap<String> _files, String... _resourcesFolder) {
//        String folder_ = ExtractObject.getMessageFolder(_conf);
//        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(folder_,_loc,_relative);
//        //        System.out.println(_resourcesFolder[0]+"/"+fileName_);
//        String content_ = ExtractFromResources.getContentFile(_conf, _files, _resourcesFolder[0]+"/"+fileName_, _resourcesFolder);
//        int index_ = ExtractFromResources.indexCorrectMessages(content_);
//        if (index_ >= 0) {
//            throw new BadFilePropertiesException(fileName_+RETURN_LINE+index_+RETURN_LINE+_conf.joinPages());
//        }
//        return ExtractFromResources.getMessages(content_);
//    }

    private static void evaluateAnalyzedAttribute(Configuration _conf, Element _elt, String _attrName) {
        String class_ = _elt.getAttribute(_attrName);
        if (!class_.contains(CALL_METHOD)) {
            return;
        }
        String command_ = class_.substring(class_.indexOf(CALL_METHOD)+1);
        CustElUtil.processAnalyzeEl(command_, 0, _conf.toContextEl());
        //System.out.println(CustElUtil.processAnalyzeEl(command_, 0, _conf.toContextEl()));
    }

    private static void setAnalyzedValueTextArea(Configuration _conf, Document _doc, Element _tag) {
        String attribute_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
        _conf.getLastPage().setProcessingAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
        _conf.getLastPage().setLookForAttrValue(true);
        _conf.getLastPage().setOffset(0);
        CustElUtil.processAnalyzeEl(attribute_, 0, _conf.toContextEl());
        //System.out.println(CustElUtil.processAnalyzeEl(attribute_, 0, _conf.toContextEl()));
    }

    private static void setAnalyzedValueInput(Configuration _conf, Element _tag) {
        String attribute_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
        try {
            Long.parseLong(attribute_);
        } catch (NumberFormatException _0) {
            _conf.getLastPage().setProcessingAttribute(_conf.getPrefix()+ATTRIBUTE_VAR_VALUE);
            _conf.getLastPage().setLookForAttrValue(true);
            _conf.getLastPage().setOffset(0);
            CustElUtil.processAnalyzeEl(attribute_, 0, _conf.toContextEl());
            //System.out.println(CustElUtil.processAnalyzeEl(attribute_, 0, _conf.toContextEl()));
        }
    }

    private static void setAnalyzedIndexes(
            Configuration _conf,
            ImportingPage _ip,
            Element _input, String _name) {
        String name_ = _name;
        if (!StringList.isWord(_name))
        System.out.println(_name);
        String className_;
        String end_ = EMPTY_STRING;
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
            className_ = CustElUtil.processAnalyzeEl(begin_, 0, _conf.toContextEl());
            if (className_.contains("<")) {
                System.err.println(className_);
            }
            //            System.out.println("begin:"+className_);
            _ip.addToOffset(begin_.length());
            end_ = name_.substring(i_ + 1);
        } else {
            className_ = _conf.getBeans().getVal(_ip.getBeanName()).getClassName();
            end_ = name_;
        }
        String key_ = StringList.concat(className_,".",end_);
        if (!CustElUtil.GETTERS_SETTERS_FIELDS.contains(key_)) {
            CustElUtil.GETTERS_SETTERS_FIELDS.put(key_, new BooleanList(true,false));
        } else {
            CustElUtil.GETTERS_SETTERS_FIELDS.getVal(key_).addAllElts(new BooleanList(true,false));
            CustElUtil.GETTERS_SETTERS_FIELDS.getVal(key_).removeDuplicates();
        }
        FIELDS_NAMES.add(key_);
        String current_ = _ip.getGlobalClass();
        _ip.setGlobalClass(className_);
        String endcl_ = CustElUtil.processAnalyzeEl(end_, 0, _conf.toContextEl());
        if (endcl_.contains("<")) {
            System.err.println(endcl_);
        }
        //        System.out.println("end:"+CustElUtil.processAnalyzeEl(end_, 0, _conf.toContextEl()));
        _ip.setGlobalClass(current_);
        String class_ = _input.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_CLASS_NAME));
        if (!class_.isEmpty()) {
            INPUT_CLASSES.add(class_);
        }
    }




    private static String quickChange(String _pattern, Configuration _conf, ImportingPage _ip) {
        StringBuilder str_ = new StringBuilder();
        StringBuilder arg_ = new StringBuilder();
        int length_ = _pattern.length();
        boolean escaped_ = false;
        int i_ = CustList.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _pattern.charAt(i_);
            if (cur_ == QUOTE) {
                escaped_ = !escaped_;
                if (i_ < length_ - 1) {
                    if (_pattern.charAt(i_ + 1) == QUOTE) {
                        str_.append(QUOTE);
                        i_++;
                        i_++;
                        escaped_ = false;
                        continue;
                    }
                }
                i_++;
                continue;
            }
            if (escaped_) {
                str_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == LEFT_EL) {
                StringBuilder tr_ = new StringBuilder();
                int indexSepTr_ = _pattern.indexOf(SEP_TR, i_ + 1);
                boolean processTr_ = false;
                if (i_ + 1 < length_ && indexSepTr_ != CustList.INDEX_NOT_FOUND_ELT) {
                    boolean allWord_ = true;
                    boolean existWord_ = false;
                    int j_ = i_;
                    while (true) {
                        if (j_ == indexSepTr_) {
                            break;
                        }
                        if (j_ > i_+1 && !StringList.isWordChar(_pattern.charAt(j_))) {
                            allWord_ = false;
                            break;
                        }
                        if (StringList.isWordChar(_pattern.charAt(j_))) {
                            existWord_ = true;
                        }
                        j_++;
                    }
                    if (!existWord_) {
                        _conf.getLastPage().setOffset(i_);
                        throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
                    }
                    processTr_ = allWord_;
                }
                if (processTr_) {
                    int j_ = i_;
                    while (true) {
                        if (j_ == indexSepTr_) {
                            j_++;
                            i_ = j_;
                            break;
                        }
                        j_++;
                        tr_.append(_pattern.charAt(j_));
                    }
                    tr_.deleteCharAt(tr_.length()-1);
                } else {
                    i_++;
                }
                if (i_ >= length_ || _pattern.charAt(i_) == RIGHT_EL) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
                }
                ContextEl context_ = _conf.toContextEl();
                Struct trloc_ = null;
                if (!tr_.toString().isEmpty()) {
                    try {
                        trloc_ = _conf.getBuiltTranslators().getVal(tr_.toString());
                        if (trloc_ == null) {
                            _conf.getLastPage().setOffset(i_);
                            throw new InexistingTranslatorException(tr_+RETURN_LINE+_conf.joinPages());
                        }
                    } catch (Throwable _0) {
                        _conf.getLastPage().setOffset(i_);
                        throw new InexistingTranslatorException(tr_+RETURN_LINE+_conf.joinPages());
                    }
                }
                _conf.getLastPage().setOffset(i_);
                String className_ = CustElUtil.processAnalyzeEl(_pattern, context_, i_, LEFT_EL, RIGHT_EL);
                str_.append(PrimitiveTypeUtil.convert(className_, 0, context_).getInstance());
                i_ = context_.getNextIndex();
                continue;
            }
            if (cur_ == RIGHT_EL){
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
            }
            str_.append(cur_);
            i_++;
        }
        return str_.toString();
    }



    private static String formatAnalyzeNumVariables(String _pattern, Configuration _conf, ImportingPage _ip) {
        StringBuilder str_ = new StringBuilder();
        StringBuilder arg_ = new StringBuilder();
        int length_ = _pattern.length();
        boolean escaped_ = false;
        int i_ = CustList.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _pattern.charAt(i_);
            if (cur_ == QUOTE) {
                escaped_ = !escaped_;
                if (i_ < length_ - 1) {
                    if (_pattern.charAt(i_ + 1) == QUOTE) {
                        str_.append(QUOTE);
                        i_++;
                        i_++;
                        escaped_ = false;
                        continue;
                    }
                }
                i_++;
                continue;
            }
            if (escaped_) {
                str_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == LEFT_EL) {
                StringBuilder tr_ = new StringBuilder();
                int indexSepTr_ = _pattern.indexOf(SEP_TR, i_ + 1);
                boolean processTr_ = false;
                if (i_ + 1 < length_ && indexSepTr_ != CustList.INDEX_NOT_FOUND_ELT) {
                    boolean allWord_ = true;
                    boolean existWord_ = false;
                    int j_ = i_;
                    while (true) {
                        if (j_ == indexSepTr_) {
                            break;
                        }
                        if (j_ > i_+1 && !StringList.isWordChar(_pattern.charAt(j_))) {
                            allWord_ = false;
                            break;
                        }
                        if (StringList.isWordChar(_pattern.charAt(j_))) {
                            existWord_ = true;
                        }
                        j_++;
                    }
                    if (!existWord_) {
                        _conf.getLastPage().setOffset(i_);
                        throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
                    }
                    processTr_ = allWord_;
                }
                if (processTr_) {
                    int j_ = i_;
                    while (true) {
                        if (j_ == indexSepTr_) {
                            j_++;
                            i_ = j_;
                            break;
                        }
                        j_++;
                        tr_.append(_pattern.charAt(j_));
                    }
                    tr_.deleteCharAt(tr_.length()-1);
                } else {
                    i_++;
                }
                if (i_ >= length_ || _pattern.charAt(i_) == RIGHT_EL) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
                }
                ContextEl context_ = _conf.toContextEl();
                Struct trloc_ = null;
                if (!tr_.toString().isEmpty()) {
                    try {
                        trloc_ = _conf.getBuiltTranslators().getVal(tr_.toString());
                        if (trloc_ == null) {
                            _conf.getLastPage().setOffset(i_);
                            throw new InexistingTranslatorException(tr_+RETURN_LINE+_conf.joinPages());
                        }
                    } catch (Throwable _0) {
                        _conf.getLastPage().setOffset(i_);
                        throw new InexistingTranslatorException(tr_+RETURN_LINE+_conf.joinPages());
                    }
                }
                _conf.getLastPage().setOffset(i_);
                CustElUtil.processAnalyzeEl(_pattern, context_, i_, LEFT_EL, RIGHT_EL);
                i_ = context_.getNextIndex();
                continue;
            }
            if (cur_ == RIGHT_EL){
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
            }
            str_.append(cur_);
            i_++;
        }
        return str_.toString();
    }
    private static String format(CustList<BlockHtml> _stacks, String _pattern, boolean _emptyTestCase) {
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
        String leftParStr_ = String.valueOf(LEFT_PAR_CHAR);
        String rightParStr_ = String.valueOf(RIGHT_PAR_CHAR);
        for (BlockHtml l: _stacks) {
            if (!(l instanceof LoopHtmlStack)) {
                continue;
            }
            LoopHtmlStack l_ = (LoopHtmlStack) l;
            long index_ = l_.getIndex();
            String indexStr_ = Long.toString(index_);
            if (ret_.contains(LEFT_PAR_COMMA)) {
                String con_ = StringList.concat(leftParStr_,indexStr_,COMMA);
                ret_ = StringList.replace(ret_, LEFT_PAR_COMMA, con_);
            } else if (ret_.contains(DOUBLE_COMMA)) {
                ret_ = insertArguments(ret_,index_);
            } else if (ret_.contains(COMMA_RIGHT_PAR)) {
                String con_ = StringList.concat(COMMA,indexStr_,rightParStr_);
                ret_ = StringList.replace(ret_, COMMA_RIGHT_PAR, con_);
            } else if (ret_.contains(NO_PARAM_METHOD)) {
                String con_ = StringList.concat(leftParStr_,indexStr_,rightParStr_);
                ret_ = StringList.replace(ret_, NO_PARAM_METHOD, con_);
            }
        }
        return ret_;
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
    private static CustList<NodeAction> getDeepChildNodesDocOrder(Node _root) {
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
        return _conf.getPrefix();
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
    private static StringList checkForLoop(Configuration _conf, Element _node, String _html) {
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
    private static int getTabWidth(Configuration _conf) {
        try {
            return _conf.getTabWidth();
        } catch (Throwable _0) {
            String err_ = _conf.getStandards().getAliasError();
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
        }
    }
    private static String addToRoot(Configuration _conf, String _prefix, String _body) {
        return StringList.concat(String.valueOf(LT_BEGIN_TAG),TMP_BLOCK_TAG,String.valueOf(GT_TAG),_body,LT_END_TAG,TMP_BLOCK_TAG,String.valueOf(GT_TAG));
    }
//    static Class<?> classForName(Configuration _conf, int _offest, String _className) {
////        if (_conf.getStandards().getStandards().contains(_className)) {
////            return null;
////        }
//        try {
//            if (PrimitiveTypeUtil.isPrimitive(_className, _conf.toContextEl())) {
//                if (!PrimitiveTypeUtil.isExistentPrimitive(_className, _conf.toContextEl())) {
//                    throw new RuntimeClassNotFoundException(StringList.concat(_className,RETURN_LINE,_conf.joinPages()));
//                }
//                return ConstClasses.getPrimitiveClass(_className.substring(1));
//            }
//            if (StringList.quickEq(_className, ConstClasses.LISTABLE_ALIAS)) {
//                return Listable.class;
//            }
//            if (StringList.quickEq(_className, ConstClasses.LISTABLE_ENTRIES_ALIAS)) {
//                return ListableEntries.class;
//            }
//            String className_ = ConstClasses.getMapping(_className);
//            if (className_ != null) {
//                return PrimitiveTypeUtil.getSingleNativeClass(className_);
//            }
//            return PrimitiveTypeUtil.getSingleNativeClass(_className);
//        } catch (Throwable _0) {
//            _conf.getLastPage().addToOffset(_offest);
//            throw new RuntimeClassNotFoundException(StringList.concat(_className,RETURN_LINE,_conf.joinPages()));
//        }
//    }
}
