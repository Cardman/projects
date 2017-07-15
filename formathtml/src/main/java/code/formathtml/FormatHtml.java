package code.formathtml;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Iterator;

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
import code.util.ints.ListableEntries;
import code.util.ints.SortableMap;
import code.xml.AttributePart;
import code.xml.XmlParser;
import code.xml.exceptions.XmlParseException;

final class FormatHtml {

    static final String XMLNS = "xmlns";
    static final String NAMESPACE_URI = "javahtml";
    static final String ATTRIBUTE_CLASS_NAME = "className";
    static final String ATTRIBUTE_INDEX_CLASS_NAME = "indexclassName";
    static final String EMPTY_STRING = "";
    static final String RETURN_LINE = "\n";
    static final String RETURN_TAB = "\n\t";

//    static final String CLASS_NAME_ATTRIBUTE = ATTRIBUTE_CLASS_NAME;
    //    static final String NULL_METHOD = String.valueOf((Object)null);
    static final String NULL_METHOD = "goto";
//    static final String NULL_METHOD = "";
    static final String SPACE = " ";
    static final String VAR_METHOD = "varMethod";
    static final String BEAN_ATTRIBUTE = "bean";
    static final String ATTRIBUTE_VALUE_CHANGE_EVENT = "valueChangeEvent";
    static final String COMMA = ",";
    static final String DOT = ".";
    static final String GET_KEY = "!key";

    //    private static final String UNPREFIXED_END = "</c_";
    //
    //    private static final String UNPREFIXED_BEGIN = "<c_";
    //
    //    private static final String PREFIXED_END = "</c:";
    //
    //    private static final String PREFIXED_BEGIN = "<c:";
    static final String TMP_VAR = "tmpvar";
    private static final String SEP_PREFIX = ":";
    //    private static final String ESCAPED_RIGHT_EL = "\\}";
    //    private static final String ESCAPED_LEFT_EL = "\\{";
    //    private static final String NEXT_FIELDS = "([^,\\}]+(,\\w+){0,2})";
    //    private static final String FORMAT_VAR = "$1";
    //    private static final String NEXT_ARG = "([^,]+),,";
    private static final String GET_ENTRY = "!";
//    private static final String FILE = "file:";
    //    private static final String REG_EXP_EL = "\\{[^\\}]*\\}";
    //    private static final String REG_EXP_EL = "\\{[^\\{\\}]*\\}";
    //    private static final String METHOD_ADD_ALL = "addAll";
    //    private static final String REG_EXP_GET_INDEX = "^\\[[0-9]+\\]$";
    //for test private static final String REG_EXP_FIELD = "^\\w+$";
    //    private static final String REG_EXP_FIELD = "^\\w+(\\^\\w+)*$";
    //    private static final String REG_EXP_ARGS = "^\\([0-9]+(,[0-9]+)*\\)$";
    //for test private static final String TOKENS_CALLS = "(\\w+|\\([0-9]+(,[0-9]+)*\\)|\\[[0-9]+\\])";
    //    private static final String TOKENS_CALLS = "(\\w+(\\^\\w+)*|\\([0-9]+(,[0-9]+)*\\)|\\[[0-9]+\\])";
    //    private static final String REG_EXP_MAP_METH = "\\w+!";
    //    private static final String REG_EXP_GET_ARR = "(\\[|\\])";
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
//    private static final String ATTRIBUTE_ORDERED_KEYS = "orderedKeys";
    private static final String TAG_OPTION = "option";
    private static final String SELECTED = "selected";
    private static final String ATTRIBUTE_VALIDATOR = "validator";
    //    private static final String EMPTY_SELECT = "<select/>";
    private static final String ATTRIBUTE_UPDATE = "update";
    //    private static final String NEG_REG_EXP = "^!+";
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
//    private static final String ESCAPED_FORMAT_QUOTE = "''";
    private static final String TAG_PARAM = "param";
    //    private static final String ALL_TAGS = "*";
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
    //private static final String SPACE_MESSAGE = " ";
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
    //    private static final String GET_FORMAT_VAR = ".$1";
    private static final String ATTRIBUTE_ESCAPED = "escaped";
    private static final String GET_VALUE = "!value";
//    private static final String LEFT_GET_ARR = "[";
    private static final String GET_ATTRIBUTE = ";";
    //    private static final String CALL_AFTER = ";(.+)";
    private static final String CALL_METHOD = "$";
    private static final String DEFAULT_ATTRIBUTE = "default";
    private static final String NO_PARAM_METHOD = "()";
//    private static final String AMP = "&";
//    private static final String E_AMP = "&amp;";
//    private static final String E_GT = "&gt;";
//    private static final String E_LT = "&lt;";
    private static final String ATTRIBUTE_VALUE_SUBMIT = "message";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";
    /**with ATTRIBUTE_CLASS_NAME attribute*/
    private static final String ATTRIBUTE_METHOD = "method";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_FORM = "form";
    private static final String PAGE_ATTRIBUTE = "page";
    private static final String KEEPFIELD_ATTRIBUTE = "keepfields";
//    private static final String EMPTY_HTML_DOC = "<html/>";
//    private static final String BEAN_ATTRIBUTE = "bean";
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

    //    private static final String PREFIX = "c:";
//    private static final String PREFIX = "c:";
    private static final String FOR_BLOCK_TAG = "for";
    private static final String WHILE_BLOCK_TAG = "while";
//    private static final String IF_BLOCK_TAG = "if";
    private static final String ELSE_BLOCK_TAG = "else";
//    private static final String ELSE_IF_BLOCK_TAG = "elseif";
//    private static final String SELECT_BLOCK_TAG = PREFIX+SELECT_TAG;
    private static final String MESSAGE_BLOCK_TAG = "message";
    private static final String IMPORT_BLOCK_TAG = "import";
    private static final String PACKAGE_BLOCK_TAG = "package";
    private static final String CLASS_BLOCK_TAG = "class";
    private static final String FIELD_BLOCK_TAG = "field";
    private static final String PARAM_BLOCK_TAG = "param";
    private static final String SET_RETURNED_VALUE_BLOCK_TAG = "setreturnedvalue";
    private static final String TMP_BLOCK_TAG = "tmp";
//    private static final String IMG_BLOCK_TAG = PREFIX+TAG_IMG;
    //private static final String A_BLOCK_TAG = PREFIX+TAG_A;
    private static final String SUBMIT_BLOCK_TAG = "submit";
    private static final String FORM_BLOCK_TAG = "form";
//    private static final String TR_BEGIN_BLOCK_TAG = PREFIX+"tr_begin";
//    private static final String TR_END_BLOCK_TAG = PREFIX+"tr_end";
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
//    @Deprecated
//    static String processImports(String _htmlText, Configuration _conf, String _loc, Map<String,String> _files, String... _resourcesFolder) {
//        String mainBeanName_ = getCurrentBean(_htmlText);
//        Bean mainBean_ = _conf.getBeans().getVal(mainBeanName_);
//        String htmlText_ = processHtmlJava(_htmlText, _conf, _loc, _files, _resourcesFolder);
//        //        StringList imported_ = new StringList();
//        //        while (i_ < _files.size()) {
//        //
//        //        }
//        int i_ = CustList.FIRST_INDEX;
//        while (keepLoop(htmlText_, i_, _files)) {
//            Document docOrig_ = XmlParser.parseSaxHtml(htmlText_);
//            //            NodeList imports_ = docOrig_.getElementsByTagName(IMPORT_BLOCK_TAG);
//            //            int lenImports_ = imports_.getLength();
//            //            if (lenImports_ == 0) {
//            //                break;
//            //            }
//            //            boolean breakAll_ = false;
//            //            for (int i = CustList.FIRST_INDEX; i < lenImports_; i++) {
//            //                Element elt_ = (Element) imports_.item(i);
//            //                String pageName_ = elt_.getAttribute(PAGE_ATTRIBUTE);
//            //                if (imported_.containsObj(pageName_)) {
//            //                    breakAll_ = true;
//            //                    break;
//            //                }
//            //            }
//            //            if (breakAll_) {
//            //                break;
//            //            }
//            CustList<Node> currentNodesToBeRead_ = new CustList<Node>();
//            currentNodesToBeRead_.add(docOrig_.getDocumentElement());
//            CustList<Node> newNodesToBeRead_ = new CustList<Node>();
//            Document doc_ = XmlParser.parseSaxHtml(EMPTY_HTML_DOC);
//            CustList<Node> currentNodesToBeModified_ = new CustList<Node>();
//            currentNodesToBeModified_.add(doc_.getDocumentElement());
//            setAttributes(doc_.getDocumentElement(), docOrig_.getDocumentElement());
//            CustList<Node> newNodesToBeModified_ = new CustList<Node>();
//            boolean modif_ = true;
//            while (modif_) {
//                modif_ = false;
//                newNodesToBeRead_ = new CustList<Node>();
//                newNodesToBeModified_ = new CustList<Node>();
//                int len_;
//                len_ = currentNodesToBeRead_.size();
//                for (int i = CustList.FIRST_INDEX; i <len_; i++) {
//                    Node currentNode_ = currentNodesToBeRead_.get(i);
//                    Node currentModifiedNode_ = currentNodesToBeModified_.get(i);
//                    for (Node n : XmlParser.childrenNodes(currentNode_)) {
//                        if (n instanceof Element) {
//                            if (StringList.eq(n.getNodeName(),IMPORT_BLOCK_TAG)) {
//                                String pageName_ = ((Element)n).getAttribute(PAGE_ATTRIBUTE);
//                                if (pageName_.isEmpty()) {
//                                    continue;
//                                }
//                                //                                imported_.add(pageName_);
//                                boolean keepField_ = ((Element)n).hasAttribute(KEEPFIELD_ATTRIBUTE);
//                                String subHtml_ = loadPage(_files, pageName_,_resourcesFolder);
//                                String beanName_ = getCurrentBean(subHtml_);
//                                setBeanForms(_conf, mainBean_, n, keepField_,
//                                        beanName_);
//                                setFieldsImportBean(_conf, n,
//                                        beanName_);
//                                subHtml_ = processHtmlJava(subHtml_, _conf, _loc, _files, _resourcesFolder);
//                                Document subDoc_ = XmlParser.parseSaxHtml(subHtml_);
//                                NodeList subList_ = subDoc_.getElementsByTagName(TAG_BODY).item(CustList.FIRST_INDEX).getChildNodes();
//                                int length_ = subList_.getLength();
//                                for (int k = CustList.FIRST_INDEX; k < length_; k++) {
//                                    Node subNode_ = subList_.item(k);
//                                    newNodesToBeRead_.add(subNode_);
//                                    if (subNode_ instanceof Element) {
//                                        Element n_ = doc_.createElement(subNode_.getNodeName());
//                                        NamedNodeMap map_ = subNode_.getAttributes();
//                                        int lentgh_ = map_.getLength();
//                                        for (int j = CustList.FIRST_INDEX;j<lentgh_;j++) {
//                                            String formatted_ = map_.item(j).getNodeValue();
//                                            //                                            formatted_ = interpretLtGt(formatted_);
//                                            n_.setAttribute(map_.item(j).getNodeName(), formatted_);
//                                        }
//                                        newNodesToBeModified_.add(n_);
//                                        currentModifiedNode_.appendChild(n_);
//                                    }
//                                    if (subNode_ instanceof Text) {
//                                        String formatted_ = subNode_.getTextContent();
//                                        //                                        formatted_ = interpretLtGt(formatted_);
//                                        Text n_ = doc_.createTextNode(formatted_);
//                                        newNodesToBeModified_.add(n_);
//                                        currentModifiedNode_.appendChild(n_);
//                                    }
//                                }
//                                continue;
//                            }
//                            newNodesToBeRead_.add(n);
//                            Element n_ = doc_.createElement(n.getNodeName());
//                            NamedNodeMap map_ = n.getAttributes();
//                            int lentgh_ = map_.getLength();
//                            for (int j = CustList.FIRST_INDEX;j<lentgh_;j++) {
//                                String formatted_ = map_.item(j).getNodeValue();
//                                //                                formatted_ = interpretLtGt(formatted_);
//                                n_.setAttribute(map_.item(j).getNodeName(), formatted_);
//                            }
//                            newNodesToBeModified_.add(n_);
//                            currentModifiedNode_.appendChild(n_);
//                            continue;
//                        }
//                        if (n instanceof Text) {
//                            newNodesToBeRead_.add(n);
//                            String formatted_ = n.getTextContent();
//                            //                            formatted_ = interpretLtGt(formatted_);
//                            Text n_ = doc_.createTextNode(formatted_);
//                            newNodesToBeModified_.add(n_);
//                            currentModifiedNode_.appendChild(n_);
//                        }
//                    }
//                }
//                if (!newNodesToBeRead_.isEmpty()) {
//                    currentNodesToBeRead_ = new CustList<Node>(newNodesToBeRead_);
//                    currentNodesToBeModified_ = new CustList<Node>(newNodesToBeModified_);
//                    modif_ = true;
//                }
//            }
//            htmlText_ = XmlParser.toHtml(doc_);
//            i_ ++;
//        }
//        Document docOrig_ = XmlParser.parseSaxHtml(htmlText_);
//        CustList<Node> currentNodesToBeRead_ = new CustList<Node>();
//        currentNodesToBeRead_.add(docOrig_.getDocumentElement());
//        CustList<Node> newNodesToBeRead_ = new CustList<Node>();
//        Document doc_ = XmlParser.parseSaxHtml(EMPTY_HTML_DOC);
//        CustList<Node> currentNodesToBeModified_ = new CustList<Node>();
//        currentNodesToBeModified_.add(doc_.getDocumentElement());
//        setAttributes(doc_.getDocumentElement(), docOrig_.getDocumentElement());
//        CustList<Node> newNodesToBeModified_ = new CustList<Node>();
//        boolean modif_ = true;
//        while (modif_) {
//            modif_ = false;
//            int len_;
//            newNodesToBeRead_ = new CustList<Node>();
//            newNodesToBeModified_ = new CustList<Node>();
//            len_ = currentNodesToBeRead_.size();
//            for (int i = CustList.FIRST_INDEX; i <len_; i++) {
//                Node currentNode_ = currentNodesToBeRead_.get(i);
//                Node currentModifiedNode_ = currentNodesToBeModified_.get(i);
//                for (Node n : XmlParser.childrenNodes(currentNode_)) {
//                    if (n instanceof Element) {
//                        if (StringList.eq(n.getNodeName(),IMPORT_BLOCK_TAG)) {
//                            continue;
//                        }
//                        newNodesToBeRead_.add(n);
//                        Element n_ = doc_.createElement(n.getNodeName());
//                        NamedNodeMap map_ = n.getAttributes();
//                        int lentgh_ = map_.getLength();
//                        for (int j = CustList.FIRST_INDEX;j<lentgh_;j++) {
//                            String formatted_ = map_.item(j).getNodeValue();
//                            //                            formatted_ = interpretLtGt(formatted_);
//                            n_.setAttribute(map_.item(j).getNodeName(), formatted_);
//                        }
//                        newNodesToBeModified_.add(n_);
//                        currentModifiedNode_.appendChild(n_);
//                        continue;
//                    }
//                    if (n instanceof Text) {
//                        newNodesToBeRead_.add(n);
//                        String formatted_ = n.getTextContent();
//                        //                        formatted_ = interpretLtGt(formatted_);
//                        Text n_ = doc_.createTextNode(formatted_);
//                        newNodesToBeModified_.add(n_);
//                        currentModifiedNode_.appendChild(n_);
//                    }
//                }
//            }
//            if (!newNodesToBeRead_.isEmpty()) {
//                currentNodesToBeRead_ = new CustList<Node>(newNodesToBeRead_);
//                currentNodesToBeModified_ = new CustList<Node>(newNodesToBeModified_);
//                modif_ = true;
//            }
//        }
//        NodeList anchors_ = doc_.getElementsByTagName(TAG_A);
//        int len_ = anchors_.getLength();
//        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//            Element elt_ = (Element) anchors_.item(i);
//            if (elt_.hasAttribute(ATTRIBUTE_COMMAND)) {
//                elt_.setAttribute(ATTRIBUTE_HREF, EMPTY_STRING);
//            }
//        }
//        anchors_ = doc_.getElementsByTagName(TAG_FORM);
//        len_ = anchors_.getLength();
//        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//            Element elt_ = (Element) anchors_.item(i);
//            if (elt_.hasAttribute(ATTRIBUTE_COMMAND)) {
//                elt_.setAttribute(ATTRIBUTE_ACTION, EMPTY_STRING);
//            }
//        }
//        htmlText_ = XmlParser.toHtml(doc_);
//        return htmlText_;
//    }

    static String processImports(String _htmlText, Configuration _conf, String _loc, StringMap<String> _files, String... _resourcesFolder) {
        _conf.getHtmlPage().getSelects().clear();
        _conf.clearPages();
//        HTML_PAGE.getSelects().clear();
        String htmlText_ = processHtmlJava(_htmlText, _conf, _loc, _files, _resourcesFolder);
        Document doc_ = XmlParser.parseSaxHtml(htmlText_, false, true);
        _conf.setDocument(doc_);
//        Document doc_ = XmlParser.parseSaxHtml(htmlText_);
//        NodeList anchors_ = doc_.getElementsByTagName(TAG_A);
//        int len_ = anchors_.getLength();
//        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//            Element elt_ = (Element) anchors_.item(i);
//            if (elt_.hasAttribute(ATTRIBUTE_COMMAND)) {
//                elt_.setAttribute(ATTRIBUTE_HREF, EMPTY_STRING);
//            }
//        }
//        anchors_ = doc_.getElementsByTagName(TAG_FORM);
//        len_ = anchors_.getLength();
//        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//            Element elt_ = (Element) anchors_.item(i);
//            if (elt_.hasAttribute(ATTRIBUTE_COMMAND)) {
//                elt_.setAttribute(ATTRIBUTE_ACTION, EMPTY_STRING);
//            }
//        }
//        htmlText_ = XmlParser.toHtml(doc_);
//        _conf.getHtmlPage().setHtml(htmlText_);
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
//        Map<String, LocalVariable> locVars_ = _ip.getLocalVars();
//        Map<String, LoopVariable> vars_ = _ip.getVars();
//        pageName_ = formatIndexVariables(pageName_, locVars_ , vars_);
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

    //    private static String interpretLtGt(String _formatted) {
    //        Map<String,String> rep_ = new Map<String,String>();
    //        rep_.put(E_LT, LT_BEGIN_TAG);
    //        rep_.put(E_GT, GT_TAG);
    ////        String formatted_ = _formatted.replace(E_LT, LT_BEGIN_TAG);
    ////        formatted_ = formatted_.replace(E_GT, GT_TAG);
    //        return StringList.replace(_formatted, rep_);
    //    }

//    private static boolean keepLoop(String _htmlText, int _i, Map<String,String> _files) {
//        if (_files.isEmpty()) {
//            Document docOrig_ = XmlParser.parseSaxHtml(_htmlText);
//            NodeList imports_ = docOrig_.getElementsByTagName(IMPORT_BLOCK_TAG);
//            int lenImports_ = imports_.getLength();
//            if (lenImports_ == 0) {
//                return false;
//            }
//            return true;
//        }
//        return _i < _files.size();
//    }

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
        } catch (Error _0) {
        } catch (RuntimeException _0) {
        }
    }

//    private static void setFieldsImportBean(Configuration _conf, Node _node,
//            String _beanName) {
//        Bean bean_ = _conf.getBeans().getVal(_beanName);
//        for (Element n: XmlParser.childrenElements(_node)) {
//            if (!StringList.eq(n.getNodeName(),PACKAGE_BLOCK_TAG)) {
//                continue;
//            }
//            String package_ = n.getAttribute(ATTRIBUTE_NAME);
//            for (Element nTwo_: XmlParser.childrenElements(n)) {
//                if (!StringList.eq(nTwo_.getNodeName(),CLASS_BLOCK_TAG)) {
//                    continue;
//                }
//                String className_ = nTwo_.getAttribute(ATTRIBUTE_NAME);
//                Class<?> class_ = bean_.getClass();
//                while (true) {
//                    if (class_ == Object.class) {
//                        break;
//                    }
//                    if (class_.getName().equalsIgnoreCase(package_+DOT+className_)) {
//                        break;
//                    }
//                    class_ = class_.getSuperclass();
//                }
//                //                try {
//                //                    class_ = Constants.classForName(package_+DOT+className_);
//                //                } catch (Exception e) {
//                //                    continue;
//                //                }
//                for (Element nThree_: XmlParser.childrenElements(nTwo_)) {
//                    if (!StringList.eq(nThree_.getNodeName(),FIELD_BLOCK_TAG)) {
//                        continue;
//                    }
//                    try {
//                        if (nThree_.hasAttribute(ATTRIBUTE_METHOD)) {
//                            String methodName_ =nThree_.getAttribute(ATTRIBUTE_METHOD);
//                            String fieldValue_ = nThree_.getAttribute(ATTRIBUTE_VALUE);
//                            String classNameParam_ = nThree_.getAttribute(ATTRIBUTE_CLASS_NAME);
//                            Class<?> classParam_ = ConstClasses.classForName(classNameParam_);
//                            Method m_ = SerializeXmlObject.getDeclaredMethod(class_, methodName_, classParam_);
//                            m_.setAccessible(access(m_));
//                            Object arg_;
//                            if (StringList.isNumber(fieldValue_)) {
//                                arg_ = Integer.parseInt(fieldValue_);
//                            } else {
//                                String beanNameFromValue_ = fieldValue_.substring(CustList.FIRST_INDEX, fieldValue_.indexOf(DOT));
//                                String command_ = fieldValue_.substring(fieldValue_.indexOf(DOT) + 1);
//                                Bean beanFromValue_ = _conf.getBeans().getVal(beanNameFromValue_);
//                                arg_ = extractObject(command_, beanFromValue_);
//                            }
//                            m_.invoke(bean_, arg_);
//                            //                            try {
//                            //                                int intValue_ = Integer.parseInt(fieldValue_);
//                            //                                m_.invoke(bean_, intValue_);
//                            //                                continue;
//                            //                            } catch (Exception _0) {
//                            //                            }
//                            //                            String beanNameFromValue_ = fieldValue_.substring(CustList.FIRST_INDEX, fieldValue_.indexOf(DOT));
//                            //                            String command_ = fieldValue_.substring(fieldValue_.indexOf(DOT) + 1);
//                            //                            Bean beanFromValue_ = _conf.getBeans().getVal(beanNameFromValue_);
//                            //                            m_.invoke(bean_, extractObject(command_, beanFromValue_));
//                            continue;
//                        }
//                        String fieldName_ = nThree_.getAttribute(ATTRIBUTE_NAME);
//                        String fieldValue_ = nThree_.getAttribute(ATTRIBUTE_VALUE);
//                        Field f_ = SerializeXmlObject.getDeclaredField(class_, fieldName_);
//                        f_.setAccessible(access(f_));
//                        Object arg_;
//                        if (StringList.isNumber(fieldValue_)) {
//                            arg_ = Integer.parseInt(fieldValue_);
//                        } else {
//                            String beanNameFromValue_ = fieldValue_.substring(0, fieldValue_.indexOf(DOT));
//                            String command_ = fieldValue_.substring(fieldValue_.indexOf(DOT) + 1);
//                            Bean beanFromValue_ = _conf.getBeans().getVal(beanNameFromValue_);
//                            arg_ = extractObject(command_, beanFromValue_);
//                        }
//                        f_.set(bean_, arg_);
//                        //                        try {
//                        //                            int intValue_ = Integer.parseInt(fieldValue_);
//                        //                            f_.set(bean_, intValue_);
//                        //                            continue;
//                        //                        } catch (Exception _0) {
//                        //                        }
//                        //                        String beanNameFromValue_ = fieldValue_.substring(0, fieldValue_.indexOf(DOT));
//                        //                        String command_ = fieldValue_.substring(fieldValue_.indexOf(DOT) + 1);
//                        //                        Bean beanFromValue_ = _conf.getBeans().getVal(beanNameFromValue_);
//                        //                        f_.set(bean_, extractObject(command_, beanFromValue_));
//                    } catch (Exception _0) {
//                        _0.printStackTrace();
//                    }
//                }
//            }
//        }
//    }

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
                //RuntimeClassNotFoundException
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
//                        Object arg_ = argt_.getObject();
//                        if (StringList.isNumber(fieldValue_)) {
//                            arg_ = Integer.parseInt(fieldValue_);
//                        } else {
////                            String beanNameFromValue_ = fieldValue_.substring(CustList.FIRST_INDEX, fieldValue_.indexOf(DOT));
////                            String command_ = fieldValue_.substring(fieldValue_.indexOf(DOT) + 1);
////                            Bean beanFromValue_ = _conf.getBeans().getVal(beanNameFromValue_);
////                            arg_ = extractObject(command_, beanFromValue_);
//                            arg_ = ExtractObject.improvedExtractObject(_conf, fieldValue_);
//                        }
                        ip_.setProcessingNode(nThree_);
                        ip_.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                        ip_.setOffset(0);
                        ip_.setLookForAttrValue(true);
                        //Class<?> classParam_ = 
                        ExtractObject.classForName(_conf, 0, classNameParam_);
//                        if (!PrimitiveTypeUtil.canBeUseAsArgument(classParam_, argt_.getArgClass()))
//                        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameParam_, argt_.getArgClassName())) {
//                            throw new DynamicCastClassException(argt_.getArgClassName()+RETURN_LINE+classNameParam_+RETURN_LINE+_conf.joinPages());
//                        }
                        if (!PrimitiveTypeUtil.canBeUseAsArgument(classNameParam_, argt_.getObjectClassName(), _conf.toContextEl().getClasses())) {
                            throw new DynamicCastClassException(argt_.getObjectClassName()+RETURN_LINE+classNameParam_+RETURN_LINE+_conf.joinPages());
                        }
//                        try {
//                            classParam_ = ConstClasses.classForName(classNameParam_);
//                        } catch (RuntimeClassNotFoundException _0) {
//                            throw new RuntimeClassNotFoundException(classNameParam_+RETURN_LINE+_conf.joinPages());
//                        }
                        ip_.setProcessingNode(nThree_);
                        ip_.setProcessingAttribute(ATTRIBUTE_METHOD);
                        ip_.setOffset(0);
                        ip_.setLookForAttrValue(true);
                        Argument argument_ = new Argument();
                        argument_.setObject(bean_);
                        argument_.setArgClassName(bean_.getClass().getName());
                        ip_.setGlobalArgument(argument_);
                        String tmp_ = TMP_VAR;
                        int i_ = CustList.FIRST_INDEX;
                        while (ip_.getLocalVars().contains(tmp_+i_)) {
                            i_++;
                        }
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setClassName(classNameParam_);
                        lv_.setStruct(argt_.getStruct());
                        ip_.getLocalVars().put(tmp_+i_, lv_);
                        ElUtil.processEl(methodName_+LEFT_PAR+tmp_+i_+GET_LOC_VAR+RIGHT_PAR, 0, _conf.toContextEl());
                        ip_.getLocalVars().removeKey(tmp_+i_);

//                        ExtractObject.getMethodThenInvoke(_conf, 0, bean_, class_, methodName_, argument_);
//                        Method m_ = SerializeXmlObject.getDeclaredMethod(class_, methodName_, classParam_);
//                        m_.setAccessible(access(m_));
//                        invokeMethod(_conf, true, m_, bean_, arg_);
                        //                            try {
                        //                                int intValue_ = Integer.parseInt(fieldValue_);
                        //                                m_.invoke(bean_, intValue_);
                        //                                continue;
                        //                            } catch (Exception _0) {
                        //                            }
                        //                            String beanNameFromValue_ = fieldValue_.substring(CustList.FIRST_INDEX, fieldValue_.indexOf(DOT));
                        //                            String command_ = fieldValue_.substring(fieldValue_.indexOf(DOT) + 1);
                        //                            Bean beanFromValue_ = _conf.getBeans().getVal(beanNameFromValue_);
                        //                            m_.invoke(bean_, extractObject(command_, beanFromValue_));
                        continue;
                    }
                    ip_.setProcessingNode(nThree_);
                    ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
                    ip_.setOffset(0);
                    ip_.setLookForAttrValue(true);
                    String fieldValue_ = nThree_.getAttribute(ATTRIBUTE_VALUE);
                    Argument argt_ = ElUtil.processEl(fieldValue_, 0, _conf.toContextEl());
                    Object arg_ = argt_.getObject();
//                    if (StringList.isNumber(fieldValue_)) {
//                        arg_ = Integer.parseInt(fieldValue_);
//                    } else {
////                        String beanNameFromValue_ = fieldValue_.substring(0, fieldValue_.indexOf(DOT));
////                        String command_ = fieldValue_.substring(fieldValue_.indexOf(DOT) + 1);
////                        Bean beanFromValue_ = _conf.getBeans().getVal(beanNameFromValue_);
////                        arg_ = extractObject(command_, beanFromValue_);
//                        arg_ = ExtractObject.improvedExtractObject(_conf, fieldValue_);
//                    }
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
                    } catch (RuntimeException _0) {
                        throw new BadAccessException(_0, fieldName_+RETURN_LINE+_conf.joinPages());
                    } catch (ExceptionInInitializerError _0) {
                        throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
                    }
                    //                        try {
                    //                            int intValue_ = Integer.parseInt(fieldValue_);
                    //                            f_.set(bean_, intValue_);
                    //                            continue;
                    //                        } catch (Exception _0) {
                    //                        }
                    //                        String beanNameFromValue_ = fieldValue_.substring(0, fieldValue_.indexOf(DOT));
                    //                        String command_ = fieldValue_.substring(fieldValue_.indexOf(DOT) + 1);
                    //                        Bean beanFromValue_ = _conf.getBeans().getVal(beanNameFromValue_);
                    //                        f_.set(bean_, extractObject(command_, beanFromValue_));
                }
//                Class<?> class_ = bean_.getClass();
//                while (true) {
//                    if (class_ == Object.class) {
//                        break;
//                    }
//                    if (class_.getName().equalsIgnoreCase(searchedClass_)) {
//                        break;
//                    }
//                    class_ = class_.getSuperclass();
//                }
                //                try {
                //                    class_ = Constants.classForName(package_+DOT+className_);
                //                } catch (Exception e) {
                //                    continue;
                //                }
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

//            if (className_.isEmpty()) {
//                className_ = cl_.getName();
//            } else {
//                ip_.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
//                ip_.setLookForAttrValue(true);
//                ip_.setOffset(0);
//                cl_ = ExtractObject.classForName(_conf, 0, className_);
//            }
//            String expression_ = n.getAttribute(EXPRESSION_ATTRIBUTE);
//            ip_.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
//            ip_.setLookForAttrValue(true);
//            ip_.setOffset(0);
            Struct obj_ = vi_.getStruct();
            parameters_.put(var_, tryToCreateVariable(_conf, ip_, className_, obj_));
//            Object obj_ = vi_.getStruct().getInstance();
//            parameters_.put(var_, tryToCreateVariable(_conf, ip_, className_, obj_));
//            if (cl_.isPrimitive()) {
//                ip_.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
//                ip_.setLookForAttrValue(true);
//                ip_.setOffset(0);
//                if (obj_ == null) {
//                    throw new NotPrimitivableException(cl_.getName()+RETURN_LINE+_conf.joinPages());
//                }
//                LocalVariable loc_ = new LocalVariable();
//                loc_.setClassName(className_);
////                        loc_.setExpression(expression_);
//                try {
//                    if (cl_ == int.class) {
//                        loc_.setElement((Integer)obj_);
//                    } else if (cl_ == long.class) {
//                        loc_.setElement((Long)obj_);
//                    } else if (cl_ == byte.class) {
//                        loc_.setElement((Byte)obj_);
//                    } else if (cl_ == short.class) {
//                        loc_.setElement((Short)obj_);
//                    } else if (cl_ == float.class) {
//                        loc_.setElement((Float)obj_);
//                    } else if (cl_ == double.class) {
//                        loc_.setElement((Double)obj_);
//                    } else if (cl_ == char.class) {
//                        loc_.setElement((Character)obj_);
//                    } else {
//                        loc_.setElement((Boolean)obj_);
//                    }
//                } catch (ClassCastException _0) {
//                    throw new DynamicCastClassException(obj_.getClass().getName()+SPACE+cl_.getName()+RETURN_LINE+_conf.joinPages());
//                }
//                parameters_.put(var_, loc_);
//            } else if (obj_ == null) {
//                LocalVariable loc_ = new LocalVariable();
//                loc_.setClassName(className_);
////                        loc_.setExpression(expression_);
//                parameters_.put(var_, loc_);
//            } else if (cl_.isInstance(obj_)) {
//                LocalVariable loc_ = new LocalVariable();
//                loc_.setClassName(className_);
//                loc_.setElement(obj_);
////                        loc_.setExpression(expression_);
//                parameters_.put(var_, loc_);
//            } else {
//                throw new NotCastableException(obj_.getClass().getName()+SPACE+cl_.getName()+RETURN_LINE+_conf.joinPages());
//            }
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

//            if (className_.isEmpty()) {
//                className_ = cl_.getName();
//            } else {
//                ip_.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
//                ip_.setLookForAttrValue(true);
//                ip_.setOffset(0);
//                cl_ = ExtractObject.classForName(_conf, 0, className_);
//            }
//            String expression_ = n.getAttribute(EXPRESSION_ATTRIBUTE);
//            ip_.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
//            ip_.setLookForAttrValue(true);
//            ip_.setOffset(0);
            LocalVariable loc_ = new LocalVariable();
            loc_.setClassName(className_);
            parameters_.put(var_, loc_);
//            if (cl_.isPrimitive()) {
//                ip_.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
//                ip_.setLookForAttrValue(true);
//                ip_.setOffset(0);
//                if (obj_ == null) {
//                    throw new NotPrimitivableException(cl_.getName()+RETURN_LINE+_conf.joinPages());
//                }
//                LocalVariable loc_ = new LocalVariable();
//                loc_.setClassName(className_);
////                        loc_.setExpression(expression_);
//                try {
//                    if (cl_ == int.class) {
//                        loc_.setElement((Integer)obj_);
//                    } else if (cl_ == long.class) {
//                        loc_.setElement((Long)obj_);
//                    } else if (cl_ == byte.class) {
//                        loc_.setElement((Byte)obj_);
//                    } else if (cl_ == short.class) {
//                        loc_.setElement((Short)obj_);
//                    } else if (cl_ == float.class) {
//                        loc_.setElement((Float)obj_);
//                    } else if (cl_ == double.class) {
//                        loc_.setElement((Double)obj_);
//                    } else if (cl_ == char.class) {
//                        loc_.setElement((Character)obj_);
//                    } else {
//                        loc_.setElement((Boolean)obj_);
//                    }
//                } catch (ClassCastException _0) {
//                    throw new DynamicCastClassException(obj_.getClass().getName()+SPACE+cl_.getName()+RETURN_LINE+_conf.joinPages());
//                }
//                parameters_.put(var_, loc_);
//            } else if (obj_ == null) {
//                LocalVariable loc_ = new LocalVariable();
//                loc_.setClassName(className_);
////                        loc_.setExpression(expression_);
//                parameters_.put(var_, loc_);
//            } else if (cl_.isInstance(obj_)) {
//                LocalVariable loc_ = new LocalVariable();
//                loc_.setClassName(className_);
//                loc_.setElement(obj_);
////                        loc_.setExpression(expression_);
//                parameters_.put(var_, loc_);
//            } else {
//                throw new NotCastableException(obj_.getClass().getName()+SPACE+cl_.getName()+RETURN_LINE+_conf.joinPages());
//            }
        }
        return parameters_;
    }
//    @Deprecated
//    static String processHtmlJava(String _htmlText, Configuration _conf, String _loc, Map<String,String> _files, String... _resourcesFolder) {
//        String htmlText_ = _htmlText;
//        String beanName_ = null;
//        Bean bean_ = null;
//        Document doc_ = XmlParser.parseSaxHtml(htmlText_);
//        beanName_ = getCurrentBean(htmlText_);
//        bean_ = _conf.getBeans().getVal(beanName_);
//        htmlText_ = XmlParser.toHtml(doc_);
//        //        try {
//            //            Document doc_ = XmlParser.parseSaxHtml(htmlText_);
//        //            beanName_ = getCurrentBean(_htmlText);
//        //            bean_ = _conf.getBeans().getVal(beanName_);
//        //            htmlText_ = XmlParser.toHtml(doc_);
//        //        } catch (Exception e) {
//        //            e.printStackTrace();
//        //        }
//        if (bean_ != null) {
//            bean_.beforeDisplaying();
//        }
//        while (true) {
//            Document doc_ = XmlParser.parseSaxHtml(htmlText_);
//            if (doc_.getElementsByTagName(SELECT_BLOCK_TAG).getLength() == 0) {
//                if (doc_.getElementsByTagName(FOR_BLOCK_TAG).getLength() == 0) {
//                    if (doc_.getElementsByTagName(IF_BLOCK_TAG).getLength() == 0) {
//                        break;
//                    }
//                }
//            }
//            htmlText_ = FormatHtml.processHtml(doc_.getDocumentElement(), bean_);
//        }
//        htmlText_ = FormatHtml.processHtml(XmlParser.parseSaxHtml(htmlText_).getDocumentElement(), _conf, _files, bean_);
//        htmlText_ = StringList.removeStrings(htmlText_,
//                LT_BEGIN_TAG+TMP_BLOCK_TAG+GT_TAG,
//                LT_BEGIN_TAG+TMP_BLOCK_TAG+RIGHT_END_TAG,
//                LT_END_TAG+TMP_BLOCK_TAG+GT_TAG);
//        Map<String,String> loc_ = new Map<String,String>();
//        loc_.put(LT_BEGIN_TAG+TR_BEGIN_BLOCK_TAG+RIGHT_END_TAG, TR_BEGIN);
//        loc_.put(LT_BEGIN_TAG+TR_END_BLOCK_TAG+RIGHT_END_TAG, TR_END);
//        htmlText_ = StringList.replace(htmlText_, loc_);
//        //        htmlText_ = htmlText_.replace(LT_BEGIN_TAG+TMP_BLOCK_TAG+GT_TAG, EMPTY_STRING);
//        //        htmlText_ = htmlText_.replace(LT_BEGIN_TAG+TMP_BLOCK_TAG+RIGHT_END_TAG, EMPTY_STRING);
//        //        htmlText_ = htmlText_.replace(LT_END_TAG+TMP_BLOCK_TAG+GT_TAG, EMPTY_STRING);
//        //        htmlText_ = htmlText_.replace(LT_BEGIN_TAG+TR_BEGIN_BLOCK_TAG+RIGHT_END_TAG, TR_BEGIN);
//        //        htmlText_ = htmlText_.replace(LT_BEGIN_TAG+TR_END_BLOCK_TAG+RIGHT_END_TAG, TR_END);
//        htmlText_ = FormatHtml.processVarInputValue(htmlText_, bean_);
//        htmlText_ = formatClassAndMessage(htmlText_, bean_);
//        htmlText_ = FormatHtml.formatNamedVariables(htmlText_, _conf, _files, bean_);
//        htmlText_ = processMessages(_conf, htmlText_, bean_, _loc, _files, _resourcesFolder);
//        htmlText_ = processSubmitTags(_conf, htmlText_, bean_, _loc, _files, _resourcesFolder);
//        htmlText_ = processTitles(_conf, htmlText_, bean_, _loc, _files, _resourcesFolder);
//        htmlText_ = FormatHtml.processRadio(htmlText_, bean_);
//        htmlText_ = FormatHtml.processVarInputName(htmlText_, beanName_);
//        htmlText_ = processImages(htmlText_, _files, _resourcesFolder);
//        htmlText_ = processImagesTags(htmlText_);
//        htmlText_ = processCss(htmlText_, _files, _resourcesFolder);
//        htmlText_ = processScript(htmlText_, _files, _resourcesFolder);
//        htmlText_ = processSpansErrors(htmlText_);
//        return htmlText_;
//    }

    static String processHtmlJava(String _htmlText, Configuration _conf, String _loc, StringMap<String> _files, String... _resourcesFolder) {
        String htmlText_ = _htmlText;
        String beanName_ = null;
        Bean bean_ = null;
        Document docOrig_ = _conf.getDocument();
        Element root_ = docOrig_.getDocumentElement();
        beanName_ = root_.getAttribute(_conf.getPrefix()+BEAN_ATTRIBUTE);
//        try {
////            if (docOrig_ == null) {
////                docOrig_ = XmlParser.parseSaxHtml(htmlText_);
////            }
//            Element root_ = docOrig_.getDocumentElement();
//            beanName_ = root_.getAttribute(BEAN_ATTRIBUTE);
//        } catch (RuntimeException _0) {
//            throw new XmlParseException(htmlText_);
//        }
        bean_ = getBean(_conf, beanName_);
        beforeDisplaying(bean_, _conf);
//        if (bean_ != null) {
//            bean_.beforeDisplaying();
//        }
        _conf.setHtml(_htmlText);
        htmlText_ = processHtml(docOrig_, beanName_, _conf, _loc, _files, _resourcesFolder);
        //process other blocks
//        htmlText_ = processRadio(htmlText_, beanName_, vars_);
//        htmlText_ = processImages(htmlText_, _files, _resourcesFolder);
//        htmlText_ = processImagesTags(htmlText_);
//        htmlText_ = processCss(htmlText_, _files, _resourcesFolder);
//        htmlText_ = processScript(htmlText_, _files, _resourcesFolder);
//        htmlText_ = processSpansErrors(htmlText_);
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
//        Map<String,String> loc_ = new Map<String,String>();
//        loc_.put(LT_BEGIN_TAG+TR_BEGIN_BLOCK_TAG+RIGHT_END_TAG, TR_BEGIN);
//        loc_.put(LT_BEGIN_TAG+TR_END_BLOCK_TAG+RIGHT_END_TAG, TR_END);
//        htmlText_ = StringList.replace(htmlText_, loc_);
//        return htmlText_;
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
//        Map<String,LoopVariable> vars_ = new Map<String,LoopVariable>();
//        LoopVariable global_ = new LoopVariable();
        //global_.setBaseExpression(_beanName);
//        global_.setExtendedExpression(EMPTY_STRING);
//        global_.setElement(bean_);
//        vars_.put(EMPTY_STRING, global_);
//        ip_.getVars().put(EMPTY_STRING, global_);
        if (bean_ != null) {
            ip_.setGlobalArgumentObj(bean_);
        }
//        CustList<ImportingPage> ips_ = new CustList<ImportingPage>();
//        CustList<ImportingPage> ips_ = _conf.getImporting();
//        ips_.add(ip_);
        _conf.addPage(ip_);
        checkSyntax(_conf, _docOrig, ip_.getHtml());
//        CustList<LoopHtmlStack> stacks_ = new CustList<LoopHtmlStack>();
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
//        long currentInput_ = 0;
        while (true) {
//            if (en_ == null) {
//                break;
//            }
//            if (rw_ == null) {
//                break;
//            }
            try {
                ip_ = _conf.getLastPage();
                if (ip_.getReadWrite() == null) {
                    ImportingPage last_ = _conf.getLastPage();
                    _conf.removeLastPage();
//                    ips_.removeLast();
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
//                stacks_ = ip_.getStacks();
//                Map<String,LoopVariable> vars_ = ip_.getVars();
//                Map<String,LocalVariable> locVars_ = ip_.getLocalVars();
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
//                    ip_ = processProcessingTags(_conf, doc_, ips_, ip_, containersMap_, containers_, indexes_, currentForm_, mainBean_, _loc, _files, _resourcesFolder);
//                    continue;
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
//                content_ = formatIndexVariables(content_, locVars_, vars_);
                if (interpretBrackets(en_)) {
                    content_ = ExtractObject.formatNumVariables(content_, _conf, ip_, _files);
                }
                Text t_ = doc_.createTextNode(content_);
                currentNode_.appendChild(t_);
                processElementOrText(_conf, ip_);
            } catch (IndirectException _0){
                //indirect
                Throwable t_ = throwException(_conf, _0);
                if (t_ == null) {
                    continue;
                }
                throw _0;
            } catch (RuntimeException _0){
//                Element catchElt_ = getCatchElement(_conf, _0, true);
                Throwable t_ = throwException(_conf, _0);
                if (t_ == null) {
                    continue;
                }
                throw _0;
//                FoundHtmlCatch catchElt_ = getCatchElement(_conf, _0, true);
//                if (catchElt_.processCatching()) {
//                    ip_ = createException(catchElt_, _conf, _0);
//                    continue;
//                }
//                throw _0;
            } catch (Error _0){
                Throwable t_ = throwException(_conf, _0);
                if (t_ == null) {
                    continue;
                }
                throw _0;
//                FoundHtmlCatch catchElt_ = getCatchElement(_conf, _0, true);
//                if (catchElt_.processCatching()) {
//                    ip_ = createException(catchElt_, _conf, _0);
//                    continue;
//                }
//                throw _0;
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
//        boolean entered_ = false;
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
//        boolean exit_ = false;
        while (!_conf.noPages()) {
            ImportingPage bkIp_ = _conf.getLastPage();
            String prefix_ = bkIp_.getPrefix();
//            CustList<BlockStack> l_ = bkIp_.getBlockStacks();
//            int indexTry_ = l_.size() - 1;
            while (!bkIp_.noBlock()) {
                BlockHtml bl_ = bkIp_.getLastStack();
                if (!(bl_ instanceof TryHtmlStack)) {
                    if (bl_ instanceof LoopHtmlStack) {
                        LoopHtmlStack loopStack_ = (LoopHtmlStack) bl_;
                        Element forNode_ = loopStack_.getReadNode();
//                        removeLocalVars(bkIp_, forNode_);
                        removeVarAndLoop(_conf, forNode_, bkIp_.getVars());
//                        try {
//                            removeVarAndLoop(_conf, forNode_, bkIp_.getVars());
//                        } catch (Exception _0) {
//                            _0.printStackTrace();
//                        }
                    } else {
//                        if (bl_ instanceof IfHtmlStack) {
//                            IfHtmlStack t_ = (IfHtmlStack) bl_;
//                            Element cur_ = t_.getCurentVisitedNode();
////                            removeLocalVars(bkIp_, cur_);
//                        }
//                        if (bl_ instanceof SwitchHtmlStack) {
//                            SwitchHtmlStack t_ = (SwitchHtmlStack) bl_;
//                            Element cur_ = t_.getCurentVisitedNode();
////                            removeLocalVars(bkIp_, cur_);
//                        }
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
//                            f_.setProcessingFinallyBlock(try_.getCatchBlocks().last());
//                            f_.setImportingPage(indexIp_);
//                            if (entered_) {
//                                f_.setTryBlock(indexTry_ - 1);
//                            } else {
//                                f_.setTryBlock(indexTry_);
//                            }
                            try_.setThrownException(_t);
//                            bkIp_.getCurrentEls().clear();
//                            bkIp_.getReadWrite().setBlock(try_.getCatchBlocks().last());
                            Element newCurrentNode_ = try_.getWriteNode();
//                            ip_.setIndexCatchBlock(t_.getVisitedCatch());
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
//                    CatchEval ca_ = (CatchEval) e;
                    String name_ = e.getAttribute(ATTRIBUTE_CLASS_NAME);
//                    Class<?> cl_ = ConstClasses.classForNameNotInit(name_);
                    if (!indirect_) {
                        if (PrimitiveTypeUtil.isAssignableFrom(name_, _t.getClass().getName(), _conf.toContextEl().getClasses())) {
                            catchElt_ = e;
//                            f_.setCause(false);
//                            f_.setExceptionClass(cl_);
//                            f_.setImportingPage(indexIp_);
//                            f_.setTryBlock(indexTry_);
//                            f_.setCatchBlock(i_);
//                            f_.setCatchBl(ca_);
                            try_.setVisitedCatch(i_);
                            break;
                        }
                    } else {
                        if (PrimitiveTypeUtil.isAssignableFrom(name_, custCause_.getClassName(), _conf.toContextEl().getClasses())) {
                            catchElt_ = e;
//                            f_.setCause(true);
//                            f_.setExceptionClass(cl_);
//                            f_.setImportingPage(indexIp_);
//                            f_.setTryBlock(indexTry_);
//                            f_.setCatchBlock(i_);
//                            f_.setCatchBl(ca_);
                            try_.setVisitedCatch(i_);
                            break;
                        }
                    }
                    i_++;
                }
                if (catchElt_ != null) {
                    Element newCurrentNode_ = ((BlockHtml)bkIp_.getLastStack()).getWriteNode();
                    Element catchElement_ = catchElt_;
//                    bkIp_.setEvaluatingKeepLoop(false);
                    try_.setThrownException(null);
//                    bkIp_.getCurrentEls().clear();
                    if (catchElement_.getFirstChild() != null) {
                        String var_ = catchElement_.getAttribute(ATTRIBUTE_VAR);
                        LocalVariable lv_ = new LocalVariable();
                        Throwable t_ = _t;
//                        if (_t instanceof IndirectException) {
//                            custCause_ = ((IndirectException)_t).getCustCause();
//                        } else {
//                            if (_t.getCause() == null) {
//                                custCause_ = new Struct();
//                            } else {
//                                custCause_ = new Struct(_t.getCause());
//                            }
//                        }
//                        if (f_.isCause())
                        if (indirect_) {
//                            t_ = t_.getCause();
                            lv_.setStruct(custCause_);
                        } else {
                            lv_.setStruct(new Struct(t_));
                        }
//                        lv_.setElement(t_);
                        lv_.setClassName(catchElement_.getAttribute(ATTRIBUTE_CLASS_NAME));
                        bkIp_.getCatchVars().put(var_, lv_);
                        bkIp_.getReadWrite().setRead(catchElement_.getFirstChild());
                        bkIp_.getReadWrite().setWrite(newCurrentNode_);
//                        bkIp_.getReadWrite().setBlock(catchElement_.getFirstChild());
                        return null;
                    }
                    bkIp_.getReadWrite().setRead(catchElement_);
                    bkIp_.getReadWrite().setWrite(newCurrentNode_);
//                    bkIp_.getReadWrite().setBlock(catchElement_);
                    return null;
                }
                if (addFinallyClause_) {
//                    f_.setProcessingFinallyBlock(try_.getCatchBlocks().last());
//                    f_.setImportingPage(indexIp_);
//                    if (entered_) {
//                        f_.setTryBlock(indexTry_ - 1);
//                    } else {
//                        f_.setTryBlock(indexTry_);
//                    }
                    try_.setThrownException(_t);
//                    bkIp_.getCurrentEls().clear();
//                    bkIp_.getReadWrite().setBlock(try_.getCatchNodes().last());
                    Element newCurrentNode_ = try_.getWriteNode();
//                    ip_.setIndexCatchBlock(t_.getVisitedCatch());
                    bkIp_.getReadWrite().setRead(try_.getCatchNodes().last());
                    bkIp_.getReadWrite().setWrite(newCurrentNode_);
                    return null;
                }
                bkIp_.removeLastBlock();
            }
//            if (catchElt_ != null) {
//                break;
//            }
            _conf.removeLastPage();
//            if (exit_) {
//                break;
//            }
//            entered_ = true;
//            indexIp_--;
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
            if (!PrimitiveTypeUtil.isAssignableFrom(Throwable.class.getName(), arg_.getObjectClassName(), cont_.getClasses())) {
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
//            if_.increment();
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
        Throwable t_ = tryStack_.getThrownException();
        if (t_ != null) {
            _ip.removeLastBlock();
//            _ip.setThrownException(null);
//            TryHtmlStack tr_ = null;
//            int i_ = _ip.getBlockStacks().size();
//            while (i_ >= CustList.FIRST_INDEX) {
//                BlockStack bl_ = _ip.getBlockStacks().get(i_);
//                if (bl_ instanceof TryHtmlStack) {
//                    tr_ = (TryHtmlStack) bl_;
//                    break;
//                }
//                i_--;
//            }
//            if (tr_ != null) {
//                _ip.set
//            }
            if (t_ instanceof RuntimeException) {
                throw (RuntimeException)t_;
            }
            throw (Error)t_;
        }
    }
    private static ImportingPage removeBlockFinally(Configuration _conf,
            ImportingPage _ip) {
        String prefix_ = _conf.getLastPage().getPrefix();
        ReadWriteHtml rw_ = _ip.getReadWrite();
        ImportingPage ip_ = _ip;
//        CustList<BlockStack> l_ = ip_.getBlockStacks();
//        int i_ = l_.size() - 1;
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
//                if (t_.getVisitedCatch() >= CustList.FIRST_INDEX) {
//                    Element catchBlock_ = t_.getCurrentCatchNode();
//                    String var_ = catchBlock_.getAttribute(ATTRIBUTE_VAR);
//                    ip_.getCatchVars().removeKey(var_);
//                }
//                else {
//                    if (t_.getVisitedCatch() >= CustList.FIRST_INDEX) {
//                        Element catchBlock_ = t_.getCurrentCatchNode();
//                        String var_ = catchBlock_.getAttribute(ATTRIBUTE_VAR);
//                        ip_.getCatchVars().removeKey(var_);
//                    }
//                }
                ip_.removeLastBlock();
//                l_.removeLast();
            } else if (bl_ instanceof LoopHtmlStack){
                LoopHtmlStack loopStack_ = (LoopHtmlStack) bl_;
                Element forNode_ = loopStack_.getReadNode();
                removeVarAndLoop(_conf, forNode_, ip_.getVars());
            } else {
                ip_.removeLastBlock();
//                l_.removeLast();
            }
//            i_--;
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
        checkClass(_conf, _ip, ConstClasses.classAliasForNameNotInit(className_), elt_);
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
        ret_.setClassName(className_);
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
//                    index_ = ExtractObject.improvedExtractObject(_conf, indexExpr_);
                    index_ = ElUtil.processEl(indexExpr_, 0, _conf.toContextEl()).getObject();
                } else {
                    index_ = Integer.parseInt(indexExpr_);
                }
                String elementExpr_ = _set.getAttribute(ARRAY_ELEMENT_ATTRIBUTE);
                _conf.getLastPage().setProcessingAttribute(ARRAY_ELEMENT_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
//                Object element_ = ExtractObject.improvedExtractObject(_conf, elementExpr_);
                Struct elementArg_ = ElUtil.processEl(elementExpr_, 0, _conf.toContextEl()).getStruct();
                Object element_ = elementArg_.getInstance();
                //ExtractObject.improvedExtractObject(_conf, elementExpr_);
                _conf.getLastPage().setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
//                Object array_ = ExtractObject.improvedExtractObject(_conf, expression_);
                Struct arrayArg_ = ElUtil.processEl(expression_, 0, _conf.toContextEl()).getStruct();
                Object array_ = arrayArg_.getInstance();
//                if (elementArg_.getClassName())
//                ExtractObject.checkNullPointer(_conf, array_);
//                if (!array_.getClass().isArray()) {
//                    array_ 
//                }
//                if (arrayArg_.getInstance().getClass().isArray()) {
//                    array_ = arrayArg_.getInstance();
//                } else {
//                    array_ = arrayArg_;
//                }
                int indexNb_;
                try {
                    indexNb_ = (Integer)index_;
                } catch (RuntimeException _0) {
                    _conf.getLastPage().setProcessingAttribute(ARRAY_INDEX_ATTRIBUTE);
                    _conf.getLastPage().setLookForAttrValue(false);
                    _conf.getLastPage().setOffset(0);
                    throw new DynamicNumberFormatException(_conf.joinPages());
                }
                try {
                    Array.set(array_, indexNb_, element_);
                } catch (RuntimeException _0) {
                    try {
                        Array.set(array_, indexNb_, new Struct(element_));
                    } catch (RuntimeException _1) {
                        _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
                        _conf.getLastPage().setLookForAttrValue(false);
                        _conf.getLastPage().setOffset(0);
                        throw new SettingArrayException(_conf.joinPages(), new Struct(_1));
                    }
                }
            } else {
                ElUtil.processEl(expression_, 0, _conf.toContextEl());
//                ExtractObject.improvedExtractObject(_conf, expression_);
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
//        String className_ = _set.getAttribute(ATTRIBUTE_CLASS_NAME);
//        String numExpr_ = _set.getAttribute(NUMBER_EXPRESSION);
//        Object obj_;
//        Class<?> cl_ = null;
//        String expression_ = _set.getAttribute(EXPRESSION_ATTRIBUTE);
//        if (!numExpr_.isEmpty()) {
//            expression_ = numExpr_;
//            String evalBool_ = _set.getAttribute(EVALUATE_BOOLEAN);
//            boolean eval_ = Boolean.parseBoolean(evalBool_);
//            _ip.setProcessingAttribute(NUMBER_EXPRESSION);
//            _ip.setLookForAttrValue(true);
//            _ip.setOffset(0);
//            obj_ = ExtractObject.evaluateMathExpression(_ip, _conf, eval_, numExpr_);
//            if (className_.isEmpty()) {
//                ExtractObject.checkNullPointer(_conf, obj_);
//                cl_ = obj_.getClass();
//                className_ = cl_.getName();
//            } else {
//                cl_ = ExtractObject.classForName(_conf, 0, className_);
////                try {
////                    cl_ = ConstClasses.classForName(className_);
////                } catch (RuntimeClassNotFoundException _0) {
////                    throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
////                }
//            }
//        } else {
//            if (className_.isEmpty()) {
//                _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
//                _ip.setLookForAttrValue(true);
//                _ip.setOffset(0);
//                if (_set.hasAttribute(IS_STRING_CONST_ATTRIBUTE)){
//                    if (!_set.hasAttribute(EXPRESSION_ATTRIBUTE)) {
//                        cl_ = Object.class;
//                        obj_ = null;
//                    } else {
//                        cl_ = String.class;
//                        obj_ = expression_;
//                    }
//                    className_ = cl_.getName();
//                } else if (_set.hasAttribute(IS_CHAR_CONST_ATTRIBUTE)){
//                    if (!_set.hasAttribute(EXPRESSION_ATTRIBUTE)) {
//                        cl_ = Object.class;
//                        obj_ = null;
//                    } else {
//                        cl_ = Character.class;
//                        obj_ = ExtractObject.getChar(_conf, expression_);
//                    }
//                    className_ = cl_.getName();
//                } else if (_set.hasAttribute(IS_BOOL_CONST_ATTRIBUTE)){
//                    if (!_set.hasAttribute(EXPRESSION_ATTRIBUTE)) {
//                        cl_ = Object.class;
//                        obj_ = null;
//                    } else {
//                        cl_ = Boolean.class;
//                        obj_ = Boolean.parseBoolean(expression_);
//                    }
//                    className_ = cl_.getName();
//                } else if (StringList.isNumber(expression_)) {
//                    cl_ = long.class;
//                    className_ = cl_.getName();
//                    obj_ = ExtractObject.instanceByString(_conf, long.class, expression_);
//                } else if (expression_.startsWith(INSTANTIATE_PREFIX)){
//                    int offs_ = INSTANTIATE_PREFIX.length();
//                    String command_ = expression_.substring(INSTANTIATE_PREFIX.length());
//                    if (!command_.contains(LEFT_GET_ARR)) {
//                        obj_ = ExtractObject.instantiate(_conf, offs_, command_);
//                    } else {
//                        obj_ = ExtractObject.instantiateArray(_conf, offs_, command_);
//                    }
//                    cl_ = Object.class;
//                    className_ = cl_.getName();
//                } else {
//                    cl_ = Object.class;
//                    className_ = cl_.getName();
//                    obj_ = ExtractObject.improvedExtractObject(_conf, expression_);
//                }
//            } else {
//                if (_set.hasAttribute(IS_STRING_CONST_ATTRIBUTE)){
//                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
//                    _ip.setLookForAttrValue(true);
//                    _ip.setOffset(0);
//                    cl_ = ExtractObject.classForName(_conf, 0, className_);
////                    try {
////                        cl_ = ConstClasses.classForName(className_);
////                    } catch (RuntimeClassNotFoundException _0) {
////                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
////                    }
//                    if (!_set.hasAttribute(EXPRESSION_ATTRIBUTE)) {
//                        obj_ = null;
//                    } else {
//                        obj_ = expression_;
//                    }
//                } else if (_set.hasAttribute(IS_CHAR_CONST_ATTRIBUTE)){
//                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
//                    _ip.setLookForAttrValue(true);
//                    _ip.setOffset(0);
//                    cl_ = ExtractObject.classForName(_conf, 0, className_);
////                    try {
////                        cl_ = ConstClasses.classForName(className_);
////                    } catch (RuntimeClassNotFoundException _0) {
////                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
////                    }
//                    if (!_set.hasAttribute(EXPRESSION_ATTRIBUTE)) {
//                        obj_ = null;
//                    } else {
//                        obj_ = ExtractObject.getChar(_conf, expression_);
//                    }
//                } else if (_set.hasAttribute(IS_BOOL_CONST_ATTRIBUTE)){
//                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
//                    _ip.setLookForAttrValue(true);
//                    _ip.setOffset(0);
//                    cl_ = ExtractObject.classForName(_conf, 0, className_);
////                    try {
////                        cl_ = ConstClasses.classForName(className_);
////                    } catch (RuntimeClassNotFoundException _0) {
////                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
////                    }
//                    if (!_set.hasAttribute(EXPRESSION_ATTRIBUTE)) {
//                        obj_ = null;
//                    } else {
//                        obj_ = Boolean.parseBoolean(expression_);
//                    }
//                } else if (StringList.isNumber(expression_)) {
//                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
//                    _ip.setLookForAttrValue(true);
//                    _ip.setOffset(0);
//                    cl_ = ExtractObject.classForName(_conf, 0, className_);
////                    try {
////                        cl_ = ConstClasses.classForName(className_);
////                    } catch (RuntimeClassNotFoundException _0) {
////                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
////                    }
//                    _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
//                    _ip.setLookForAttrValue(true);
//                    _ip.setOffset(0);
//                    obj_ = ExtractObject.instanceByString(_conf, cl_, expression_);
//                } else if (expression_.startsWith(INSTANTIATE_PREFIX)){
//                    int offs_ = INSTANTIATE_PREFIX.length();
//                    String command_ = expression_.substring(INSTANTIATE_PREFIX.length());
//                    if (!command_.contains(LEFT_GET_ARR)) {
//                        obj_ = ExtractObject.instantiate(_conf, offs_, command_);
//                    } else {
//                        obj_ = ExtractObject.instantiateArray(_conf, offs_, command_);
//                    }
//                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
//                    _ip.setLookForAttrValue(true);
//                    _ip.setOffset(0);
//                    cl_ = ExtractObject.classForName(_conf, 0, className_);
////                    try {
////                        cl_ = ConstClasses.classForName(className_);
////                    } catch (RuntimeClassNotFoundException _0) {
////                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
////                    }
//                } else {
//                    _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
//                    _ip.setLookForAttrValue(true);
//                    _ip.setOffset(0);
//                    obj_ = ExtractObject.improvedExtractObject(_conf, expression_);
//                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
//                    _ip.setLookForAttrValue(true);
//                    _ip.setOffset(0);
//                    cl_ = ExtractObject.classForName(_conf, 0, className_);
////                    try {
////                        cl_ = ConstClasses.classForName(className_);
////                    } catch (RuntimeClassNotFoundException _0) {
////                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
////                    }
//                }
//            }
//        }
        Class<?> classRef_ = vi_.getClassRef();
        String className_ = classRef_.getName();
        if (classRef_.isPrimitive()) {
            className_ = PrimitiveTypeUtil.PRIM+className_;
        }
        Struct obj_ = vi_.getStruct();
        _ip.getLocalVars().put(var_, tryToCreateVariable(_conf, _ip, className_, obj_));
//        Object obj_ = vi_.getStruct().getInstance();
//        _ip.getLocalVars().put(var_, tryToCreateVariable(_conf, _ip, className_, obj_));
//        if (cl_.isPrimitive()) {
//            _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
//            _ip.setLookForAttrValue(true);
//            _ip.setOffset(0);
//            if (obj_ == null) {
//                throw new NotPrimitivableException(cl_.getName()+RETURN_LINE+_conf.joinPages());
//            }
//            LocalVariable loc_ = new LocalVariable();
//            loc_.setClassName(className_);
////                    loc_.setExpression(expression_);
//            try {
//                if (cl_ == int.class) {
//                    loc_.setElement((Integer)obj_);
//                } else if (cl_ == long.class) {
//                    loc_.setElement((Long)obj_);
//                } else if (cl_ == byte.class) {
//                    loc_.setElement((Byte)obj_);
//                } else if (cl_ == short.class) {
//                    loc_.setElement((Short)obj_);
//                } else if (cl_ == float.class) {
//                    loc_.setElement((Float)obj_);
//                } else if (cl_ == double.class) {
//                    loc_.setElement((Double)obj_);
//                } else if (cl_ == char.class) {
//                    loc_.setElement((Character)obj_);
//                } else {
//                    loc_.setElement((Boolean)obj_);
//                }
//            } catch (ClassCastException _0) {
//                throw new DynamicCastClassException(obj_.getClass().getName()+SPACE+cl_.getName()+RETURN_LINE+_conf.joinPages());
//            }
//            _ip.getLocalVars().put(var_, loc_);
//        } else if (obj_ == null) {
//            LocalVariable loc_ = new LocalVariable();
//            loc_.setClassName(className_);
////                    loc_.setExpression(expression_);
//            _ip.getLocalVars().put(var_, loc_);
//        } else if (cl_.isInstance(obj_)) {
//            LocalVariable loc_ = new LocalVariable();
//            loc_.setClassName(className_);
//            loc_.setElement(obj_);
////                    loc_.setExpression(expression_);
//            _ip.getLocalVars().put(var_, loc_);
//        } else {
//            throw new NotCastableException(obj_.getClass().getName()+SPACE+cl_.getName()+RETURN_LINE+_conf.joinPages());
//        }
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
//        String className_ = _element.getAttribute(ATTRIBUTE_CLASS_NAME);
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
//                int offs_ = INSTANTIATE_PREFIX.length();
//                String command_ = expression_.substring(INSTANTIATE_PREFIX.length());
//                if (!command_.contains(LEFT_GET_ARR)) {
//                    obj_ = ExtractObject.instantiate(_conf, offs_, command_);
//                } else {
//                    obj_ = ExtractObject.instantiateArray(_conf, offs_, command_);
//                }
//                return obj_;
                return ElUtil.processEl(expression_, 0, _conf.toContextEl()).getObject();
            } else {
//                obj_ = ExtractObject.improvedExtractObject(_conf, expression_);
                return ElUtil.processEl(expression_, 0, _conf.toContextEl()).getObject();
            }
        }
    }
    static Class<?> tryToGetClass(Configuration _conf, ImportingPage _ip,
            Element _element, Object _object) {
        String className_ = _element.getAttribute(ATTRIBUTE_CLASS_NAME);
        String numExpr_ = _element.getAttribute(NUMBER_EXPRESSION);
//        Object obj_;
        Class<?> cl_ = null;
        String expression_ = _element.getAttribute(EXPRESSION_ATTRIBUTE);
        if (!numExpr_.isEmpty()) {
            expression_ = numExpr_;
            _ip.setProcessingAttribute(NUMBER_EXPRESSION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            if (className_.isEmpty()) {
                ExtractObject.checkNullPointer(_conf, _object);
                cl_ = _object.getClass();
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
                    } else {
                        cl_ = String.class;
                    }
                    className_ = cl_.getName();
                } else if (_element.hasAttribute(IS_CHAR_CONST_ATTRIBUTE)){
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        cl_ = Object.class;
                    } else {
                        cl_ = Character.class;
                    }
                    className_ = cl_.getName();
                } else if (_element.hasAttribute(IS_BOOL_CONST_ATTRIBUTE)){
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        cl_ = Object.class;
                    } else {
                        cl_ = Boolean.class;
                    }
                    className_ = cl_.getName();
                } else if (StringList.isNumber(expression_)) {
                    cl_ = long.class;
                    className_ = cl_.getName();
                } else if (expression_.startsWith(INSTANTIATE_PREFIX)){
                    cl_ = Object.class;
                    className_ = cl_.getName();
                } else {
                    cl_ = Object.class;
                    className_ = cl_.getName();
                }
            } else {
                if (_element.hasAttribute(IS_STRING_CONST_ATTRIBUTE)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                } else if (_element.hasAttribute(IS_CHAR_CONST_ATTRIBUTE)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                } else if (_element.hasAttribute(IS_BOOL_CONST_ATTRIBUTE)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                } else if (StringList.isNumber(expression_)) {
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                    _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                } else if (expression_.startsWith(INSTANTIATE_PREFIX)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                } else {
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
                }
            }
        }
        return cl_;
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
//                try {
//                    cl_ = ConstClasses.classForName(className_);
//                } catch (RuntimeClassNotFoundException _0) {
//                    throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
//                }
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
//                    int offs_ = INSTANTIATE_PREFIX.length();
//                    String command_ = expression_.substring(INSTANTIATE_PREFIX.length());
//                    if (!command_.contains(LEFT_GET_ARR)) {
//                        obj_ = ExtractObject.instantiate(_conf, offs_, command_);
//                    } else {
//                        obj_ = ExtractObject.instantiateArray(_conf, offs_, command_);
//                    }
                    Argument a_ = ElUtil.processEl(expression_, 0, _conf.toContextEl());
                    struct_ = a_.getStruct();
                    useStruct_ = true;
//                    obj_ = ElUtil.processEl(expression_, 0, _conf.toContextEl()).getObject();
                    cl_ = Object.class;
                    className_ = cl_.getName();
                } else {
                    cl_ = Object.class;
                    className_ = cl_.getName();
                    Argument a_ = ElUtil.processEl(expression_, 0, _conf.toContextEl());
                    struct_ = a_.getStruct();
                    useStruct_ = true;
//                    obj_ = ElUtil.processEl(expression_, 0, _conf.toContextEl()).getObject();
//                    obj_ = ExtractObject.improvedExtractObject(_conf, expression_);
                }
            } else {
                if (_element.hasAttribute(IS_STRING_CONST_ATTRIBUTE)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
//                    try {
//                        cl_ = ConstClasses.classForName(className_);
//                    } catch (RuntimeClassNotFoundException _0) {
//                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
//                    }
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
//                    try {
//                        cl_ = ConstClasses.classForName(className_);
//                    } catch (RuntimeClassNotFoundException _0) {
//                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
//                    }
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
//                    try {
//                        cl_ = ConstClasses.classForName(className_);
//                    } catch (RuntimeClassNotFoundException _0) {
//                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
//                    }
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
//                    try {
//                        cl_ = ConstClasses.classForName(className_);
//                    } catch (RuntimeClassNotFoundException _0) {
//                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
//                    }
                    _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    obj_ = ExtractObject.instanceByString(_conf, cl_, expression_);
                } else if (expression_.startsWith(INSTANTIATE_PREFIX)){
//                    int offs_ = INSTANTIATE_PREFIX.length();
//                    String command_ = expression_.substring(INSTANTIATE_PREFIX.length());
//                    if (!command_.contains(LEFT_GET_ARR)) {
//                        obj_ = ExtractObject.instantiate(_conf, offs_, command_);
//                    } else {
//                        obj_ = ExtractObject.instantiateArray(_conf, offs_, command_);
//                    }
                    Argument a_ = ElUtil.processEl(expression_, 0, _conf.toContextEl());
                    struct_ = a_.getStruct();
                    useStruct_ = true;
//                    obj_ = ElUtil.processEl(expression_, 0, _conf.toContextEl()).getObject();
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
//                    try {
//                        cl_ = ConstClasses.classForName(className_);
//                    } catch (RuntimeClassNotFoundException _0) {
//                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
//                    }
                } else {
                    _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    Argument a_ = ElUtil.processEl(expression_, 0, _conf.toContextEl());
                    struct_ = a_.getStruct();
                    useStruct_ = true;
//                    obj_ = ElUtil.processEl(expression_, 0, _conf.toContextEl()).getObject();
//                    obj_ = ExtractObject.improvedExtractObject(_conf, expression_);
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    cl_ = ExtractObject.classForName(_conf, 0, className_);
//                    try {
//                        cl_ = ConstClasses.classForName(className_);
//                    } catch (RuntimeClassNotFoundException _0) {
//                        throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
//                    }
                }
            }
        }
        VariableInformation vi_ = new VariableInformation();
        vi_.setClassRef(cl_);
        if (useStruct_) {
            vi_.setStruct(struct_);
//            vi_.setElement(struct_.getInstance());
        } else {
            vi_.setElement(obj_);
        }
        return vi_;
    }
//    static LocalVariable tryToCreateVariable(Configuration _conf, ImportingPage _ip, Class<?> _class, String _className, Object _object)
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
            loc_.setClassName(_className);
//                    loc_.setExpression(expression_);
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
            loc_.setClassName(_className);
//                    loc_.setExpression(expression_);
            return loc_;
        }
//        if (PrimitiveTypeUtil.canBeUseAsArgument(_className, _object.getClassName())) {
//            LocalVariable loc_ = new LocalVariable();
//            loc_.setClassName(_className);
//            loc_.setElement(_object);
////                    loc_.setExpression(expression_);
//            return loc_;
//        }
//        if (_class.isInstance(_object)) {
//            LocalVariable loc_ = new LocalVariable();
//            loc_.setClassName(_className);
//            loc_.setElement(_object);
////                    loc_.setExpression(expression_);
//            return loc_;
//        }
//        if (PrimitiveTypeUtil.canBeUseAsArgument(_className, _object.getClass().getName())) {
//            LocalVariable loc_ = new LocalVariable();
//            loc_.setClassName(_className);
//            loc_.setStruct(_object);
////                    loc_.setExpression(expression_);
//            return loc_;
//        }
        String param_ = _className;
        String arg_ = _object.getClassName();
//        System.out.println(arg_);
//        System.out.println(PrimitiveTypeUtil.getPrettyArrayClass(param_));
        param_ = PrimitiveTypeUtil.getPrettyArrayClass(param_);
//        arg_ = PrimitiveTypeUtil.getPrettyArrayClass(arg_);
//        System.out.println(param_);
        if (PrimitiveTypeUtil.canBeUseAsArgument(param_, arg_, _conf.toContextEl().getClasses())) {
            LocalVariable loc_ = new LocalVariable();
            loc_.setClassName(_className);
            loc_.setStruct(_object);
//                    loc_.setExpression(expression_);
            return loc_;
        }
//        throw new NotCastableException(_object.getClass().getName()+SPACE+_class.getName()+RETURN_LINE+_conf.joinPages());
//        throw new NotCastableException(_object.getClassName()+SPACE+_class.getName()+RETURN_LINE+_conf.joinPages());
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
//                    loc_.setExpression(expression_);
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
//                if (first_ == null) {
//                    throw new BadSwitchException(_conf.joinPages());
//                }
//                boolean exist_ = false;
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
//                        exist_ = true;
                        first_ = first_.getNextSibling();
                        continue;
                    }
                    if (StringList.quickEq(elt_.getNodeName(),prefix_+TAG_DEFAULT)) {
//                        exist_ = true;
                        first_ = first_.getNextSibling();
                        continue;
                    }
                    throw new BadSwitchException(_conf.joinPages());
                }
//                if (!exist_) {
//                    throw new BadSwitchException(_conf.joinPages());
//                }
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
//                if (!en_.hasAttributes()) {
//
//                }
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
//        CustList<StringList> vars_ = new CustList<StringList>();
        ObjectMap<NodeAttribute, NatTreeMap<Integer, Integer>> infos_;
        infos_ = new ObjectMap<NodeAttribute, NatTreeMap<Integer, Integer>>();
//        String prefix_ = _conf.getLastPage().getPrefix();
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
//        if (name_.endsWith(GET_ATTRIBUTE) || name_.endsWith(GET_LOC_VAR)) {
////            System.out.println(_name);
//            _indexes.setNb(-1);
//            return;
//        }
        if (name_.endsWith(GET_LOC_VAR)) {
//            System.out.println(_name);
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
//            LoopVariable lv_ = _ip.getVars().getVal(oldVar_);
            LoopVariable lv_ = ExtractObject.getCurrentVariable(_conf, 0, _ip.getVars(), oldVar_);
            if (lv_.getMap() != null) {
//                obj_ = lv_.getMap().entryList();
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
//                obj_ = ExtractObject.improvedExtractObject(_conf, begin_);
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
//            currentField_ = ExtractObject.extractObject(_conf, end_, obj_);
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
                    //continue;
                }
                if (StringList.quickEq(type_,RANGE)) {
                    class_= Long.class.getName();
                }
                //Begin
                if (StringList.quickEq(type_,RADIO)) {
                    class_= Long.class.getName();
                }
                //End
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
                        class_ = CustList.class.getName()+BEG_TEMP+class_+END_TEMP;
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
//        containers_.put(_key, _v);
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
//        String prefix_ = _ip.getPrefix();
        String prefixWrite_ = _conf.getPrefix();
        String beanName_ = _ip.getBeanName();
//        StringMap<LoopVariable> vars_ = _ip.getVars();
//        StringMap<LocalVariable> locVars_ = _ip.getLocalVars();
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
//            id_ = replaceVariable(vars_, stacks_, id_);
            _ip.setProcessingAttribute(ATTRIBUTE_ID);
            id_ = interpretPartiallyIds(_conf, _ip, id_);
            _tag.setAttribute(ATTRIBUTE_ID, id_);
        }
        attributesNames_.removeAllString(_conf.getPrefix()+ATTRIBUTE_GROUP_ID);
        String groupId_ = _tag.getAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID);
        if (!groupId_.isEmpty()) {
//            groupId_ = replaceVariable(vars_, stacks_, groupId_);
            _ip.setProcessingAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID);
            groupId_ = interpretPartiallyIds(_conf, _ip, groupId_);
            _tag.setAttribute(_conf.getPrefix()+ATTRIBUTE_GROUP_ID, groupId_);
        }
        if (StringList.quickEq(_tag.getNodeName(),prefixWrite_+SUBMIT_BLOCK_TAG) && !prefixWrite_.isEmpty()) {
            attributesNames_.removeAllString(ATTRIBUTE_VALUE_SUBMIT);
            String value_ = _tag.getAttribute(ATTRIBUTE_VALUE_SUBMIT);
            StringList elts_ = StringList.splitStrings(value_, COMMA);
            //            String var_ = value_.split(COMMA)[0];
            String var_ = elts_.first();
            String fileName_ = ExtractObject.getProperty(_conf, var_);
            if (fileName_ == null) {
                fileName_ = var_;
            }
            StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
            //            String preformatted_ = messages_.getVal(value_.split(COMMA)[1]);
//            String preformatted_ = messages_.getVal(elts_.last());
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
//                    _ip.setOffset(0);
                    _ip.setOffset(1);
//                    objects_.add(ExtractObject.improvedExtractObject(_conf, attribute_.substring(1)));
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
//            _doc.renameNode(_tag, _tag.getNamespaceURI(), INPUT_TAG);
            _doc.renameNode(_tag, null, INPUT_TAG);
        }
        if (StringList.quickEq(_tag.getNodeName(),prefixWrite_+TAG_A) && !prefixWrite_.isEmpty()) {
            attributesNames_.removeAllString(ATTRIBUTE_VALUE);
            String value_ = _tag.getAttribute(ATTRIBUTE_VALUE);
            StringList elts_ = StringList.splitStrings(value_, COMMA);
            //            String var_ = value_.split(COMMA)[0];
            String var_ = elts_.first();
            String fileName_ = ExtractObject.getProperty(_conf, var_);
            if (fileName_ == null) {
                fileName_ = var_;
            }
            StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
            //            String preformatted_ = messages_.getVal(value_.split(COMMA)[1]);
            _ip.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
            _ip.setOffset(var_.length()+1);
            _ip.setLookForAttrValue(true);
            String key_ = elts_.last();
            String preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
//            String preformatted_ = messages_.getVal(elts_.last());
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
//                    _ip.setOffset(0);
//                    objects_.add(ExtractObject.improvedExtractObject(_conf, attribute_.substring(1)));
                    objects_.add(ElUtil.processEl(attribute_, 1, _conf.toContextEl()).getObject());
                } else {
                    objects_.add(attribute_);
                }
                _tag.removeAttribute(TAG_PARAM+i_);
                i_++;
            }
            attributesNames_.removeAllString(ATTRIBUTE_TITLE);
            _tag.setAttribute(ATTRIBUTE_TITLE, StringList.simpleFormat(preformatted_, objects_.toArray()));
//            _doc.renameNode(_tag, _tag.getNamespaceURI(), TAG_A);
            _doc.renameNode(_tag, null, TAG_A);
        }
        if (StringList.quickEq(_tag.getNodeName(),prefixWrite_+TAG_IMG) && !prefixWrite_.isEmpty()) {
//            _doc.renameNode(_tag, _tag.getNamespaceURI(), TAG_IMG);
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
//                        _ip.setOffset(0);
                        _ip.setOffset(1);
//                        objects_.add(ExtractObject.valueOf(_conf,ExtractObject.improvedExtractObject(_conf, attribute_.substring(1))));
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
//                NodeList styles_ = head_.getElementsByTagName(TAG_STYLE);
//                int len_ = styles_.getLength();
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
//        if (StringList.quickEq(_tag.getNodeName(),FIELD_BLOCK_TAG)) {
//            if (_tag.hasAttribute(ATTRIBUTE_VALUE)) {
//                attributesNames_.removeAllString(ATTRIBUTE_VALUE);
//                String v_ = _tag.getAttribute(ATTRIBUTE_VALUE);
//                v_ = replaceVariable(vars_, stacks_, v_);
//                try {
//                    Integer.parseInt(v_);
//                } catch (NumberFormatException _0) {
//                    _tag.setAttribute(ATTRIBUTE_VALUE, beanName_+DOT+v_);
//                }
//            }
//        }
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
                //name_ = interpretPartiallyIds(_ip, name_);
                _ip.setProcessingAttribute(ATTRIBUTE_NAME);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                setIndexes(_indexes, _conf, _ip, _containersMap, _containers, _tag, name_);
                if (_indexes.getNb() >= 0) {
                    _tag.setAttribute(NUMBER_INPUT, String.valueOf(_indexes.getNb()));
                }
                attributesNames_.removeAllString(NUMBER_INPUT);
//                name_ = interpretPartiallyIds(_ip, name_);
                //name_ = replaceVariable(vars_, stacks_, name_);
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
                //name_ = interpretPartiallyIds(_ip, name_);
                _ip.setProcessingAttribute(ATTRIBUTE_NAME);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                setIndexes(_indexes, _conf, _ip, _containersMap, _containers, _tag, name_);
                if (_indexes.getNb() >= 0) {
                    _tag.setAttribute(NUMBER_INPUT, String.valueOf(_indexes.getNb()));
                }
                attributesNames_.removeAllString(NUMBER_INPUT);
                //name_ = replaceVariable(vars_, stacks_, name_);
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
//                String name_ = _tag.getAttribute(ATTRIBUTE_NAME);
//                name_ = name_.substring(_beanName.length() + 1);
                _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_NAME);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
//                Object o_ = ExtractObject.improvedExtractObject(_conf, accessName_);
                Object o_ = null;
                if (!accessName_.isEmpty()) {
                    o_ = ElUtil.processEl(accessName_, 0, _conf.toContextEl()).getObject();
                }
                if (o_ == null) {
                    _tag.removeAttribute(CHECKED);
                } else {
                    String strObj_;
                    if (o_ instanceof Enum) {
                        strObj_ = ((Enum<?>)o_).name();
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
//                forId_ = replaceVariable(vars_, stacks_, forId_);
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
                //attributesNames_.removeAllString(ATTRIBUTE_COMMAND);
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
                //attributesNames_.removeAllString(ATTRIBUTE_COMMAND);
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
//            String class_ = _tag.getAttribute(ATTRIBUTE_CLASS);
//            if (!class_.isEmpty()) {
//                attributesNames_.removeAllString(ATTRIBUTE_CLASS);
//                //format class before evaluate it
//                class_ = format(_stacks, class_, false);
//                _tag.setAttribute(ATTRIBUTE_CLASS, class_);
//                evaluateAttribute(_vars, _tag, ATTRIBUTE_CLASS);
//            }
            for (String a: attributesNames_) {
                String v_ = _tag.getAttribute(a);
                if (!v_.isEmpty()) {
                    if (StringList.quickEq(a, ATTRIBUTE_CLASS)) {
                        CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                        v_ = format(stacks_, v_, false);
                        _ip.setProcessingAttribute(a);
                        _ip.setLookForAttrValue(true);
                        _ip.setOffset(1);
//                        v_ = formatNumVariables(v_, _conf, locVars_, vars_, _files);
//                        _tag.setAttribute(a, v_);
                        _tag.setAttribute(a, v_);
                        evaluateAttribute(_conf,_tag, a);
                    } else {
//                        v_ = formatIndexVariables(v_, locVars_, vars_);
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
//        Object o_ = ExtractObject.improvedExtractObject(_conf, attribute_);
        Object o_ = ElUtil.processEl(attribute_, 0, _conf.toContextEl()).getObject();
        if (o_ == null) {
            o_ = EMPTY_STRING;
        }
        //TODO converter
//        NodeList children_ = _tag.getChildNodes();
//        int ch_ = children_.getLength();
//        for (int j = CustList.FIRST_INDEX; j < ch_; j++) {
//            _tag.removeChild(children_.item(j));
//        }
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
//            Object o_ = ExtractObject.improvedExtractObject(_conf, attribute_);
            Object o_ = ElUtil.processEl(attribute_, 0, _conf.toContextEl()).getObject();
            if (o_ == null) {
                _tag.setAttribute(ATTRIBUTE_VALUE, (String) o_);
                //_tag.hasAttribute(ATTRIBUTE_VALUE) && _tag.getAttribute(ATTRIBUTE_VALUE).isEmpty()
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
//        StringMap<LoopVariable> vars_ = _ip.getVars();
//        StringMap<LocalVariable> locVars_ = _ip.getLocalVars();
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
//        int index_ = 0;
//        for (String p: StringList.splitChars(_pattern, MATH_INTERPRET)) {
//            if (index_ % 2 == 1) {
//                Object arg_ = ExtractObject.improvedExtractObject(_conf,  p);
//                calculateVariables_.append(ExtractObject.valueOf(_conf, arg_));
//            } else {
//                calculateVariables_.append(p);
//            }
//            _ip.addToOffset(p.length()+1);
//            index_++;
//        }
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
                //                varLoc_ = varLoc_.replaceAll(NEXT_ARG, FORMAT_VAR+COMMA+String.valueOf(_i)+COMMA);
                ret_ = insertArguments(ret_,index_);
            } else if (ret_.contains(COMMA_RIGHT_PAR)) {
                ret_ = StringList.replace(ret_, COMMA_RIGHT_PAR, COMMA+index_+RIGHT_PAR);
            } else if (ret_.contains(NO_PARAM_METHOD)) {
                ret_ = StringList.replace(ret_, NO_PARAM_METHOD, LEFT_PAR+index_+RIGHT_PAR);
            }
        }
        return ret_;
    }

//    static String formatNotEmpty(CustList<LoopHtmlStack> _stacks, String _pattern) {
//        String ret_ = _pattern;
//        if (ret_.isEmpty()) {
//            return ret_;
//        }
//        for (LoopHtmlStack l: _stacks) {
//            long index_ = l.getIndex();
//            if (ret_.contains(LEFT_PAR_COMMA)) {
//                ret_ = StringList.replace(ret_, LEFT_PAR_COMMA, LEFT_PAR+index_+COMMA);
//            } else if (ret_.contains(DOUBLE_COMMA)) {
//                //                varLoc_ = varLoc_.replaceAll(NEXT_ARG, FORMAT_VAR+COMMA+String.valueOf(_i)+COMMA);
//                ret_ = insertArguments(ret_,index_);
//            } else if (ret_.contains(COMMA_RIGHT_PAR)) {
//                ret_ = StringList.replace(ret_, COMMA_RIGHT_PAR, COMMA+index_+RIGHT_PAR);
//            } else if (ret_.contains(NO_PARAM_METHOD)) {
//                ret_ = StringList.replace(ret_, NO_PARAM_METHOD, LEFT_PAR+index_+RIGHT_PAR);
//            }
//        }
//        return ret_;
//    }

    static void evaluateAttribute(Configuration _conf, Element _elt, String _attrName) {
        String class_ = _elt.getAttribute(_attrName);
        if (!class_.contains(CALL_METHOD)) {
            return;
        }
        String command_ = class_.substring(class_.indexOf(CALL_METHOD)+1);
//        Object returnedObject_ = ExtractObject.improvedExtractObject(_conf, command_);
        Object returnedObject_ = ElUtil.processEl(command_, 0, _conf.toContextEl()).getObject();
        if (returnedObject_ == null) {
            _elt.removeAttribute(_attrName);
            return;
        }
        _elt.setAttribute(_attrName, ExtractObject.toString(_conf, returnedObject_));
    }

//    @Deprecated
//    static String formatClassAndMessage(String _htmlText, Bean _bean) {
//        Document doc_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList nodes_ = doc_.getElementsByTagName(ALL_TAGS);
//        int length_ = nodes_.getLength();
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            Node node_ = nodes_.item(j);
//            //            if (!(node_ instanceof Element)) {
//            //                continue;
//            //            }
//            Element element_ = (Element) node_;
//            String class_ = element_.getAttribute(ATTRIBUTE_CLASS);
//            if (!class_.contains(CALL_METHOD)) {
//                continue;
//            }
//            String command_ = class_.substring(class_.indexOf(CALL_METHOD)+1);
//            Object returnedObject_ = extractObject(command_, _bean);
//            if (returnedObject_ == null) {
//                element_.removeAttribute(ATTRIBUTE_CLASS);
//                continue;
//            }
//            element_.setAttribute(ATTRIBUTE_CLASS, returnedObject_.toString());
//        }
//        //        htmlText_ = XmlParser.toHtml(doc_);
//        //        doc_ = XmlParser.parseSaxHtml(htmlText_);
//        nodes_ = doc_.getElementsByTagName(MESSAGE_BLOCK_TAG);
//        length_ = nodes_.getLength();
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            Node node_ = nodes_.item(j);
//            Element element_ = (Element) node_;
//            if (!element_.hasAttribute(ATTRIBUTE_QUOTED)) {
//                continue;
//            }
//            NodeList listParam_ = element_.getElementsByTagName(TAG_PARAM);
//            int len_ = listParam_.getLength();
//            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//                Element param_ = (Element) listParam_.item(i);
//                param_.setAttribute(ATTRIBUTE_VALUE, StringList.replace(param_.getAttribute(ATTRIBUTE_VALUE), FORMAT_QUOTE, ESCAPED_FORMAT_QUOTE));
//            }
//        }
//        //_htmlText = XmlParser.toHtml(doc_);
//        return XmlParser.toHtml(doc_);
//    }
//    @Deprecated
//    static String processVarInputValue(String _htmlText, Object _o) {
//        Document doc_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList inputs_ = doc_.getElementsByTagName(INPUT_TAG);
//        int length_ = inputs_.getLength();
//        for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//            Element elt_ = (Element) inputs_.item(i);
//            if (!elt_.hasAttribute(ATTRIBUTE_VAR_VALUE)) {
//                continue;
//            }
//            String attribute_ = elt_.getAttribute(ATTRIBUTE_VAR_VALUE);
//            try {
//                elt_.setAttribute(ATTRIBUTE_VALUE, String.valueOf(Long.parseLong(attribute_)));
//            } catch (NumberFormatException _0) {
//                Object o_ = extractObject(attribute_, _o);
//                if (o_ == null) {
//                    elt_.setAttribute(ATTRIBUTE_VALUE, (String) o_);
//                } else if (o_ instanceof Boolean) {
//                    if ((Boolean) o_) {
//                        elt_.setAttribute(CHECKED, CHECKED);
//                    }
//                } else {
//                    elt_.setAttribute(ATTRIBUTE_VALUE, o_.toString());
//                }
//            }
//        }
//        inputs_ = doc_.getElementsByTagName(TEXT_AREA);
//        length_ = inputs_.getLength();
//        for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//            Element elt_ = (Element) inputs_.item(i);
//            if (!elt_.hasAttribute(ATTRIBUTE_VAR_VALUE)) {
//                continue;
//            }
//            String attribute_ = elt_.getAttribute(ATTRIBUTE_VAR_VALUE);
//            Object o_ = extractObject(attribute_, _o);
//            if (o_ == null) {
//                o_ = EMPTY_STRING;
//            }
//            NodeList children_ = elt_.getChildNodes();
//            int ch_ = children_.getLength();
//            for (int j = CustList.FIRST_INDEX; j < ch_; j++) {
//                elt_.removeChild(children_.item(j));
//            }
//            Text text_ = doc_.createTextNode(o_.toString());
//            elt_.appendChild(text_);
//        }
//        return XmlParser.toHtml(doc_);
//    }
//    @Deprecated
//    static String processVarInputName(String _htmlText, String _beanName) {
//        Document doc_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList inputs_ = doc_.getElementsByTagName(INPUT_TAG);
//        int length_ = inputs_.getLength();
//        for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//            Element elt_ = (Element) inputs_.item(i);
//            if (!elt_.hasAttribute(ATTRIBUTE_NAME)) {
//                continue;
//            }
//            elt_.setAttribute(ATTRIBUTE_NAME, _beanName+DOT+elt_.getAttribute(ATTRIBUTE_NAME));
//        }
//        inputs_ = doc_.getElementsByTagName(SELECT_TAG);
//        length_ = inputs_.getLength();
//        for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//            Element elt_ = (Element) inputs_.item(i);
//            if (elt_.getAttribute(ATTRIBUTE_NAME).isEmpty()) {
//                continue;
//            }
//            elt_.setAttribute(ATTRIBUTE_NAME, _beanName+DOT+elt_.getAttribute(ATTRIBUTE_NAME));
//        }
//        inputs_ = doc_.getElementsByTagName(TEXT_AREA);
//        length_ = inputs_.getLength();
//        for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//            Element elt_ = (Element) inputs_.item(i);
//            if (elt_.getAttribute(ATTRIBUTE_NAME).isEmpty()) {
//                continue;
//            }
//            elt_.setAttribute(ATTRIBUTE_NAME, _beanName+DOT+elt_.getAttribute(ATTRIBUTE_NAME));
//        }
//        inputs_ = doc_.getElementsByTagName(FIELD_BLOCK_TAG);
//        length_ = inputs_.getLength();
//        for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//            Element elt_ = (Element) inputs_.item(i);
//            if (!elt_.hasAttribute(ATTRIBUTE_VALUE)) {
//                continue;
//            }
//            try {
//                Integer.parseInt(elt_.getAttribute(ATTRIBUTE_VALUE));
//            } catch (NumberFormatException _0) {
//                elt_.setAttribute(ATTRIBUTE_VALUE, _beanName+DOT+elt_.getAttribute(ATTRIBUTE_VALUE));
//            }
//        }
//        inputs_ = doc_.getElementsByTagName(TAG_A);
//        length_ = inputs_.getLength();
//        for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//            Element elt_ = (Element) inputs_.item(i);
//            if (!elt_.getAttribute(ATTRIBUTE_HREF).startsWith(CALL_METHOD)) {
//                if (!elt_.getAttribute(ATTRIBUTE_COMMAND).startsWith(CALL_METHOD)) {
//                    continue;
//                }
//            }
//            String href_ = elt_.getAttribute(ATTRIBUTE_HREF);
//            if (href_.startsWith(CALL_METHOD)) {
//                elt_.setAttribute(ATTRIBUTE_HREF, CALL_METHOD+_beanName+DOT+href_.substring(1));
//                continue;
//            }
//            href_ = elt_.getAttribute(ATTRIBUTE_COMMAND);
//            elt_.setAttribute(ATTRIBUTE_COMMAND, CALL_METHOD+_beanName+DOT+href_.substring(1));
//        }
//        inputs_ = doc_.getElementsByTagName(TAG_FORM);
//        length_ = inputs_.getLength();
//        for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//            Element elt_ = (Element) inputs_.item(i);
//            if (!elt_.getAttribute(ATTRIBUTE_ACTION).startsWith(CALL_METHOD)) {
//                if (!elt_.getAttribute(ATTRIBUTE_COMMAND).startsWith(CALL_METHOD)) {
//                    continue;
//                }
//            }
//            String href_ = elt_.getAttribute(ATTRIBUTE_ACTION);
//            if (href_.startsWith(CALL_METHOD)) {
//                elt_.setAttribute(ATTRIBUTE_ACTION, CALL_METHOD+_beanName+DOT+href_.substring(1));
//                continue;
//            }
//            href_ = elt_.getAttribute(ATTRIBUTE_COMMAND);
//            elt_.setAttribute(ATTRIBUTE_COMMAND, CALL_METHOD+_beanName+DOT+href_.substring(1));
//        }
//        return XmlParser.toHtml(doc_);
//    }
//    @Deprecated
//    static String processRadio(String _htmlText, Bean _bean) {
//        Document doc_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList inputs_ = doc_.getElementsByTagName(INPUT_TAG);
//        int length_ = inputs_.getLength();
//        StringList names_ = new StringList();
//        for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//            Element elt_ = (Element) inputs_.item(i);
//            if (!StringList.eq(elt_.getAttribute(ATTRIBUTE_TYPE),RADIO)) {
//                continue;
//            }
//            names_.add(elt_.getAttribute(ATTRIBUTE_NAME));
//        }
//        names_.removeDuplicates();
//        StringMap< Object> objects_ = new StringMap< Object>();
//        for (String n: names_) {
//            objects_.put(n, extractObject(n, _bean));
//        }
//        for (String n: names_) {
//            for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//                Element elt_ = (Element) inputs_.item(i);
//                if (!StringList.eq(elt_.getAttribute(ATTRIBUTE_TYPE),RADIO)) {
//                    continue;
//                }
//                if (!StringList.eq(elt_.getAttribute(ATTRIBUTE_NAME),n)) {
//                    continue;
//                }
//                Object o_ = objects_.getVal(n);
//                if (o_ == null) {
//                    elt_.removeAttribute(CHECKED);
//                    continue;
//                }
//                String strObj_;
//                if (o_ instanceof Enum) {
//                    strObj_ = ((Enum<?>)o_).name();
//                } else {
//                    strObj_ = o_.toString();
//                }
//                if (StringList.eq(elt_.getAttribute(ATTRIBUTE_VALUE),strObj_)) {
//                    elt_.setAttribute(CHECKED, CHECKED);
//                } else {
//                    elt_.removeAttribute(CHECKED);
//                }
//            }
//        }
//        return XmlParser.toHtml(doc_);
//    }
//
//    @Deprecated
//    static String processRadio(String _htmlText, String _beanName, StringMap<LoopVariable> _vars) {
//        Document doc_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList inputs_ = doc_.getElementsByTagName(INPUT_TAG);
//        int length_ = inputs_.getLength();
//        StringList names_ = new StringList();
//        for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//            Element elt_ = (Element) inputs_.item(i);
//            if (!StringList.eq(elt_.getAttribute(ATTRIBUTE_TYPE),RADIO)) {
//                continue;
//            }
//            String name_ = elt_.getAttribute(ATTRIBUTE_NAME);
//            name_ = name_.substring(_beanName.length() + 1);
//            names_.add(name_);
//        }
//        names_.removeDuplicates();
//        StringMap< Object> objects_ = new StringMap< Object>();
//        for (String n: names_) {
//            objects_.put(n, improvedExtractObject(_vars, n));
//        }
//        for (String n: names_) {
//            for (int i = CustList.FIRST_INDEX;i<length_;i++) {
//                Element elt_ = (Element) inputs_.item(i);
//                if (!StringList.eq(elt_.getAttribute(ATTRIBUTE_TYPE),RADIO)) {
//                    continue;
//                }
//                if (!StringList.eq(elt_.getAttribute(ATTRIBUTE_NAME),_beanName+DOT+n)) {
//                    continue;
//                }
//                Object o_ = objects_.getVal(n);
//                if (o_ == null) {
//                    elt_.removeAttribute(CHECKED);
//                    continue;
//                }
//                String strObj_;
//                if (o_ instanceof Enum) {
//                    strObj_ = ((Enum<?>)o_).name();
//                } else {
//                    strObj_ = o_.toString();
//                }
//                if (StringList.eq(elt_.getAttribute(ATTRIBUTE_VALUE),strObj_)) {
//                    elt_.setAttribute(CHECKED, CHECKED);
//                } else {
//                    elt_.removeAttribute(CHECKED);
//                }
//            }
//        }
//        return XmlParser.toHtml(doc_);
//    }
//
//    @Deprecated
//    static String processHtml(Element _root, Configuration _conf, StringMap<String> _files, Bean _o) {
//        CustList<Node> currentNodesToBeRead_ = new CustList<Node>();
//        currentNodesToBeRead_.add(_root);
//        CustList<Node> newNodesToBeRead_ = new CustList<Node>();
//        Document doc_ = XmlParser.parseSaxHtml(EMPTY_HTML_DOC);
//        CustList<Node> currentNodesToBeModified_ = new CustList<Node>();
//        Element htmlElt_;
//        htmlElt_ = doc_.getDocumentElement();
//        setAttributes(htmlElt_, _root);
//        currentNodesToBeModified_.add(doc_.getDocumentElement());
//        CustList<Node> newNodesToBeModified_ = new CustList<Node>();
//        boolean modif_ = true;
//        while (modif_) {
//            modif_ = false;
//            newNodesToBeRead_ = new CustList<Node>();
//            newNodesToBeModified_ = new CustList<Node>();
//            int len_;
//            len_ = currentNodesToBeRead_.size();
//            for (int i = CustList.FIRST_INDEX; i <len_; i++) {
//                Node currentNode_ = currentNodesToBeRead_.get(i);
//                Node currentModifiedNode_ = currentNodesToBeModified_.get(i);
//                for (Node n : XmlParser.childrenNodes(currentNode_)) {
//                    if (n instanceof Element) {
//                        if (StringList.eq(n.getNodeName(),SELECT_BLOCK_TAG)) {
//                            String map_ = ((Element) n).getAttribute(ATTRIBUTE_MAP);
//                            String id_ = ((Element) n).getAttribute(ATTRIBUTE_ID);
//                            String groupId_ = ((Element) n).getAttribute(ATTRIBUTE_GROUP_ID);
//                            boolean multiple_ = ((Element) n).hasAttribute(ATTRIBUTE_MULTIPLE);
//                            if (!map_.isEmpty()) {
//                                processOptionsMap(_o, doc_,
//                                        currentModifiedNode_, n, map_, id_, groupId_, multiple_);
//                                continue;
//                            }
//                            processOptionsList(_o,
//                                    doc_, currentModifiedNode_, n, id_, groupId_, multiple_);
//                            continue;
//                        }
//                        if (StringList.eq(n.getNodeName(),FOR_BLOCK_TAG)) {
//                            String contents_ = toHtmlString(n);
//                            NodeList forElements_ = ((Element) n).getElementsByTagName(FOR_BLOCK_TAG);
//                            int forNbs_ = forElements_.getLength();
//                            StringList vars_ = new StringList();
//                            for (int j = CustList.FIRST_INDEX; j < forNbs_; j++) {
//                                Element elt_ = (Element) forElements_.item(j);
//                                vars_.add(elt_.getAttribute(ATTRIBUTE_VAR));
//                                vars_.add(elt_.getAttribute(ATTRIBUTE_KEY));
//                                vars_.add(elt_.getAttribute(ATTRIBUTE_VALUE));
//                            }
//                            vars_.removeDuplicates();
//                            vars_.removeObj(EMPTY_STRING);
//                            int nbVars_ = vars_.size();
//                            vars_.removeObj(((Element) n).getAttribute(ATTRIBUTE_VAR));
//                            vars_.removeObj(((Element) n).getAttribute(ATTRIBUTE_KEY));
//                            vars_.removeObj(((Element) n).getAttribute(ATTRIBUTE_VALUE));
//                            if (nbVars_ != vars_.size()) {
//                                String message_ = ((Element) n).getAttribute(ATTRIBUTE_VAR);
//                                message_ += SPACE_MESSAGE;
//                                message_ += ((Element) n).getAttribute(ATTRIBUTE_KEY);
//                                message_ += SPACE_MESSAGE;
//                                message_ += ((Element) n).getAttribute(ATTRIBUTE_VALUE);
//                                throw new AlreadyDefinedVarException(message_);
//                            }
//                            if (n.getAttributes().getNamedItem(ATTRIBUTE_LIST) == null) {
//                                processLoopMap(_conf, _files, _o,
//                                        newNodesToBeRead_, doc_,
//                                        newNodesToBeModified_,
//                                        currentModifiedNode_, n, contents_);
//                                continue;
//                            }
//                            processLoopList(_conf, _files, _o, newNodesToBeRead_, doc_, newNodesToBeModified_, currentModifiedNode_, n, contents_);
//                            continue;
//                        }
//                        if (StringList.eq(n.getNodeName(),IF_BLOCK_TAG)) {
//                            String condition_ = n.getAttributes().getNamedItem(ATTRIBUTE_CONDITION).getNodeValue();
//                            //                            String conditionWithoutNeg_ = condition_.replaceAll(NEG_REG_EXP, EMPTY_STRING);
//                            String conditionWithoutNeg_ = replaceNegPrefix(condition_);
//                            int nbNeg_ = condition_.length() - conditionWithoutNeg_.length();
//                            conditionWithoutNeg_ = formatNamedVariables(conditionWithoutNeg_, _conf, _files, _o);
//                            Boolean b_ = (Boolean) extractObject(conditionWithoutNeg_, _o);
//                            if (nbNeg_%2 == 1) {
//                                b_ = !b_;
//                            }
//                            if (!b_) {
//                                continue;
//                            }
//                            String contents_ = toHtmlString(n);
//                            Document docLoc_ = XmlParser.parseSaxHtml(addToRoot(contents_));
//                            newNodesToBeRead_.add(docLoc_.getDocumentElement());
//                            Element n_ = doc_.createElement(docLoc_.getDocumentElement().getNodeName());
//                            NamedNodeMap map_ = docLoc_.getDocumentElement().getAttributes();
//                            setAttributes(n_, map_);
//                            newNodesToBeModified_.add(n_);
//                            currentModifiedNode_.appendChild(n_);
//                            continue;
//                        }
//                        newNodesToBeRead_.add(n);
//                        Element n_ = doc_.createElement(n.getNodeName());
//                        NamedNodeMap map_ = n.getAttributes();
//                        setAttributes(n_, map_);
//                        newNodesToBeModified_.add(n_);
//                        currentModifiedNode_.appendChild(n_);
//                        continue;
//                    }
//                    if (n instanceof Text) {
//                        newNodesToBeRead_.add(n);
//                        Text n_ = doc_.createTextNode(n.getTextContent());
//                        newNodesToBeModified_.add(n_);
//                        currentModifiedNode_.appendChild(n_);
//                    }
//                }
//            }
//            if (!newNodesToBeRead_.isEmpty()) {
//                currentNodesToBeRead_ = new CustList<Node>(newNodesToBeRead_);
//                currentNodesToBeModified_ = new CustList<Node>(newNodesToBeModified_);
//                modif_ = true;
//            }
//        }
//        return XmlParser.toHtml(doc_);
//    }

//    private static String toHtmlString(Node _node) {
//        String contents_ = EMPTY_STRING;
//        for (Node nTwo_: XmlParser.childrenNodes(_node)) {
//            contents_ += XmlParser.toXmlWithoutHeader(nTwo_);
//        }
//        return contents_;
//    }
//
//    @Deprecated
//    private static void processOptionsMap(Bean _o, Document _doc,
//            Node _currentModifiedNode, Node _n, String _map, String _id, String _groupId, boolean _multiple) {
//        String name_ = ((Element) _n).getAttribute(ATTRIBUTE_NAME);
//        boolean update_ = ((Element)_n).hasAttribute(ATTRIBUTE_UPDATE);
//        String varValue_ = ((Element) _n).getAttribute(ATTRIBUTE_VAR_VALUE);
//        Object returnedVarValue_ = null;
//        if (!varValue_.isEmpty()) {
//            returnedVarValue_ = extractObject(varValue_, _o);
//        }
//        ListableEntries<?,?> extractedMap_ = (ListableEntries<?,?>) extractObject(_map, _o);
//        String default_ = ((Element) _n).getAttribute(DEFAULT_ATTRIBUTE);
//        //        Document docSelect_ = XmlParser.parseSaxHtml(EMPTY_SELECT);
//        //        Element docElementSelect_ = docSelect_.getDocumentElement();
//        Element docElementSelect_ = _doc.createElement(SELECT_TAG);
//        if (!_id.isEmpty() || !_groupId.isEmpty()) {
//            if (!_id.isEmpty()) {
//                docElementSelect_.setAttribute(ATTRIBUTE_ID, _id);
//            } else {
//                docElementSelect_.setAttribute(ATTRIBUTE_GROUP_ID, _groupId);
//            }
//            docElementSelect_.setAttribute(ATTRIBUTE_VALIDATOR, ((Element) _n).getAttribute(ATTRIBUTE_VALIDATOR));
//        }
//        if (_multiple) {
//            docElementSelect_.setAttribute(ATTRIBUTE_MULTIPLE, ATTRIBUTE_MULTIPLE);
//        }
//        if (default_.isEmpty() || returnedVarValue_ != null && update_) {
//            //            processOptionsMapEnumName(extractedMap_,
//            //                    docSelect_, docElementSelect_,
//            //                    returnedVarValue_);
//            processOptionsMapEnumName(extractedMap_,
//                    _doc, docElementSelect_,
//                    returnedVarValue_, _multiple);
//            //            docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
//            //            docElementSelect_.setAttribute(CLASS_NAME_ATTRIBUTE, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
//            //            _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
//        } else {
//            if (default_.startsWith(CALL_METHOD)) {
//                String command_ = default_.substring(1);
//                Object defaultEnum_ = extractObject(command_, _o);
//                //                processOptionsMapEnumName(extractedMap_,
//                //                        docSelect_, docElementSelect_,
//                //                        defaultEnum_);
//                processOptionsMapEnumName(extractedMap_,
//                        _doc, docElementSelect_,
//                        defaultEnum_, _multiple);
//                //                docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
//                //                docElementSelect_.setAttribute(CLASS_NAME_ATTRIBUTE, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
//                //                _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
//            } else {
//                //                processOptionsMapEnum(extractedMap_, default_,
//                //                        docSelect_, docElementSelect_);
//                String className_ = ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME);
//                processOptionsMapEnum(extractedMap_, default_,
//                        _doc, docElementSelect_, className_);
//                //                docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
//                //                docElementSelect_.setAttribute(CLASS_NAME_ATTRIBUTE, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
//                //                _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
//            }
//        }
//        docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
//        docElementSelect_.setAttribute(ATTRIBUTE_CLASS_NAME, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
//        //        _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
//        _currentModifiedNode.appendChild(docElementSelect_);
//    }

    private static void processOptionsList(Configuration _conf, Document _doc,
            Node _currentModifiedNode, Element _n, String _id, String _groupId, boolean _multiple) {
        String list_ = _n.getAttribute(ATTRIBUTE_LIST);
        String name_ = ((Element) _n).getAttribute(ATTRIBUTE_NAME);
        boolean update_ = ((Element) _n).hasAttribute(ATTRIBUTE_UPDATE);
        String varValue_ = ((Element) _n).getAttribute(ATTRIBUTE_VAR_VALUE);
        //        Enum<?> returnedVarValue_ = null;
        IdList<Object> returnedVarValue_ = null;
        if (!varValue_.isEmpty()) {
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR_VALUE);
            _conf.getLastPage().setLookForAttrValue(true);
            _conf.getLastPage().setOffset(0);
            returnedVarValue_ = new IdList<Object>();
            if (!_multiple) {
//                returnedVarValue_.add(ExtractObject.improvedExtractObject(_conf, varValue_));
                returnedVarValue_.add(ElUtil.processEl(varValue_, 0, _conf.toContextEl()).getObject());
            } else {
//                Object o_ = ExtractObject.improvedExtractObject(_conf, varValue_);
                Object o_ = ElUtil.processEl(varValue_, 0, _conf.toContextEl()).getObject();
//                returnedVarValue_.addAllElts((Listable<?>)o_);
                returnedVarValue_.addAllElts(ExtractObject.castListable(_conf, varValue_.length(), o_));
            }
            //            returnedVarValue_ = (Enum<?>) extractObject(varValue_, _o);
        }
        _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_LIST);
        _conf.getLastPage().setLookForAttrValue(true);
        _conf.getLastPage().setOffset(0);
//        Object li_ = ExtractObject.improvedExtractObject(_conf, list_);
        Object li_ = ElUtil.processEl(list_, 0, _conf.toContextEl()).getObject();
        Listable<?> extractedList_ = ExtractObject.castListable(_conf, list_.length(), li_);
        String default_ = ((Element) _n).getAttribute(DEFAULT_ATTRIBUTE);
        //        Document docSelect_ = XmlParser.parseSaxHtml(EMPTY_SELECT);
        //        Document docSelect_ = _doc.createElement(SELECT_TAG);
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
            Iterator<?> it_ = ExtractObject.iterator(_conf, extractedList_);
            while (ExtractObject.hasNext(_conf, it_)) {
                Object o_ = ExtractObject.next(_conf, it_);
                Enum<?> enum_ = (Enum<?>)o_;
                //                Element option_ = docSelect_.createElement(TAG_OPTION);
                Element option_ = _doc.createElement(TAG_OPTION);
                option_.setAttribute(ATTRIBUTE_VALUE, enum_.name());
                if (returnedVarValue_ != null) {
                    for (Object a: returnedVarValue_) {
                        if (a == enum_) {
                            option_.setAttribute(SELECTED, SELECTED);
                            break;
                        }
                    }
                }
                //                if (returnedVarValue_ == enum_) {
                    //                    option_.setAttribute(SELECTED, SELECTED);
                //                }
                //                option_.appendChild(docSelect_.createTextNode(o.toString()));
                option_.appendChild(_doc.createTextNode(ExtractObject.toString(_conf, o_)));
                docElementSelect_.appendChild(option_);
            }
//            for (Object o: extractedList_) {
//                Enum<?> enum_ = (Enum<?>)o;
//                //                Element option_ = docSelect_.createElement(TAG_OPTION);
//                Element option_ = _doc.createElement(TAG_OPTION);
//                option_.setAttribute(ATTRIBUTE_VALUE, enum_.name());
//                if (returnedVarValue_ != null) {
//                    for (Object a: returnedVarValue_) {
//                        if (a == enum_) {
//                            option_.setAttribute(SELECTED, SELECTED);
//                            break;
//                        }
//                    }
//                }
//                //                if (returnedVarValue_ == enum_) {
//                    //                    option_.setAttribute(SELECTED, SELECTED);
//                //                }
//                //                option_.appendChild(docSelect_.createTextNode(o.toString()));
//                option_.appendChild(_doc.createTextNode(toString(_conf, o)));
//                docElementSelect_.appendChild(option_);
//            }
            //            docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
            //            docElementSelect_.setAttribute(CLASS_NAME_ATTRIBUTE, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
            //            _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
        } else {
            if (default_.startsWith(CALL_METHOD)) {
                String command_ = default_.substring(1);
                _conf.getLastPage().setProcessingAttribute(DEFAULT_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(1);
                IdList<Object> defaults_ = new IdList<Object>();
                if (!_multiple) {
//                    defaults_.add(ExtractObject.improvedExtractObject(_conf, command_));
                    defaults_.add(ElUtil.processEl(command_, 0, _conf.toContextEl()).getObject());
                } else {
//                    li_ = ExtractObject.improvedExtractObject(_conf, command_);
                    li_ = ElUtil.processEl(command_, 0, _conf.toContextEl()).getObject();
                    defaults_.addAllElts(ExtractObject.castListable(_conf, command_.length(), li_));
                }
                //                Enum<?> defaultEnum_ = (Enum<?>) extractObject(command_, _o);
                checkEnums(_conf, extractedList_);
                _conf.getLastPage().setProcessingAttribute(DEFAULT_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
                checkEnums(_conf, defaults_);
                Iterator<?> it_ = ExtractObject.iterator(_conf, extractedList_);
                while (ExtractObject.hasNext(_conf, it_)) {
                    Object o_ = ExtractObject.next(_conf, it_);
                    Enum<?> enum_ = (Enum<?>)o_;
                    //                    Element option_ = docSelect_.createElement(TAG_OPTION);
                    Element option_ = _doc.createElement(TAG_OPTION);
                    option_.setAttribute(ATTRIBUTE_VALUE, enum_.name());
                    for (Object d: defaults_) {
                        Enum<?> defaultEnum_ = (Enum<?>) d;
                        if (StringList.quickEq(enum_.name(),defaultEnum_.name())) {
                            option_.setAttribute(SELECTED, SELECTED);
                            break;
                        }
                    }
//                    option_.appendChild(_doc.createTextNode(o.toString()));
                    option_.appendChild(_doc.createTextNode(ExtractObject.toString(_conf, o_)));
                    docElementSelect_.appendChild(option_);
                }
//                for (Object o: extractedList_) {
//                    Enum<?> enum_ = (Enum<?>)o;
//                    //                    Element option_ = docSelect_.createElement(TAG_OPTION);
//                    Element option_ = _doc.createElement(TAG_OPTION);
//                    option_.setAttribute(ATTRIBUTE_VALUE, enum_.name());
//                    for (Object d: defaults_) {
//                        Enum<?> defaultEnum_ = (Enum<?>) d;
//                        if (StringList.eq(enum_.name(),defaultEnum_.name())) {
//                            option_.setAttribute(SELECTED, SELECTED);
//                            break;
//                        }
//                    }
////                    option_.appendChild(_doc.createTextNode(o.toString()));
//                    option_.appendChild(_doc.createTextNode(toString(_conf, o)));
//                    docElementSelect_.appendChild(option_);
//                }
                //                docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
                //                docElementSelect_.setAttribute(CLASS_NAME_ATTRIBUTE, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
                //                _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
            } else {
                StringList names_ = StringList.splitChars(default_, SEP_ENUMS);
                checkEnums(_conf, extractedList_);
                Iterator<?> it_ = ExtractObject.iterator(_conf, extractedList_);
                while (ExtractObject.hasNext(_conf, it_)) {
                    Object o_ = ExtractObject.next(_conf, it_);
                    Enum<?> enum_ = (Enum<?>)o_;
                    //                    Element option_ = docSelect_.createElement(TAG_OPTION);
                    Element option_ = _doc.createElement(TAG_OPTION);
                    option_.setAttribute(ATTRIBUTE_VALUE, enum_.name());
                    for (String n: names_) {
                        if (StringList.quickEq(enum_.name(),n)) {
                            option_.setAttribute(SELECTED, SELECTED);
                            break;
                        }
                    }
//                    option_.appendChild(_doc.createTextNode(o.toString()));
                    option_.appendChild(_doc.createTextNode(ExtractObject.toString(_conf, o_)));
                    docElementSelect_.appendChild(option_);
                }
//                for (Object o: extractedList_) {
//                    Enum<?> enum_ = (Enum<?>)o;
//                    //                    Element option_ = docSelect_.createElement(TAG_OPTION);
//                    Element option_ = _doc.createElement(TAG_OPTION);
//                    option_.setAttribute(ATTRIBUTE_VALUE, enum_.name());
//                    for (String n: names_) {
//                        if (StringList.eq(enum_.name(),n)) {
//                            option_.setAttribute(SELECTED, SELECTED);
//                            break;
//                        }
//                    }
////                    option_.appendChild(_doc.createTextNode(o.toString()));
//                    option_.appendChild(_doc.createTextNode(toString(_conf, o)));
//                    docElementSelect_.appendChild(option_);
//                }
                //                docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
                //                docElementSelect_.setAttribute(CLASS_NAME_ATTRIBUTE, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
                //                _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
            }
        }
        docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
        docElementSelect_.setAttribute(_conf.getPrefix()+ATTRIBUTE_CLASS_NAME, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
        //        _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
        _currentModifiedNode.appendChild(docElementSelect_);
    }

    private static void checkEnums(Configuration _conf, Listable<?> _list) {
        try {
            for (Object o: _list) {
                ((Enum<?>)o).name();
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
        Object returnedVarValue_ = null;
        if (!varValue_.isEmpty()) {
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR_VALUE);
            _conf.getLastPage().setLookForAttrValue(true);
            _conf.getLastPage().setOffset(0);
//            returnedVarValue_ = ExtractObject.improvedExtractObject(_conf, varValue_);
            returnedVarValue_ = ElUtil.processEl(varValue_, 0, _conf.toContextEl()).getObject();
        }
        _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_MAP);
        _conf.getLastPage().setLookForAttrValue(true);
        _conf.getLastPage().setOffset(0);
//        Object map_ = ExtractObject.improvedExtractObject(_conf, _map);
        Object map_ = ElUtil.processEl(_map, 0, _conf.toContextEl()).getObject();
        ListableEntries<?,?> extractedMap_ = ExtractObject.castListableEntries(_conf, _map.length(), map_);
        String default_ = ((Element) _n).getAttribute(DEFAULT_ATTRIBUTE);
        //        Document docSelect_ = XmlParser.parseSaxHtml(EMPTY_SELECT);
        //        Element docElementSelect_ = docSelect_.getDocumentElement();
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
            //            processOptionsMapEnumName(extractedMap_,
            //                    docSelect_, docElementSelect_,
            //                    returnedVarValue_);
            processOptionsMapEnumName(_conf, extractedMap_,
                    _doc, docElementSelect_,
                    returnedVarValue_, _multiple);
            //            docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
            //            docElementSelect_.setAttribute(CLASS_NAME_ATTRIBUTE, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
            //            _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
        } else {
            if (default_.startsWith(CALL_METHOD)) {
//                String command_ = default_.substring(1);
                _conf.getLastPage().setProcessingAttribute(DEFAULT_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(1);
//                Object defaultEnum_ = ExtractObject.improvedExtractObject(_conf, command_);
                Object defaultEnum_ = ElUtil.processEl(default_, 1, _conf.toContextEl()).getObject();
                //                processOptionsMapEnumName(extractedMap_,
                //                        docSelect_, docElementSelect_,
                //                        defaultEnum_);
                processOptionsMapEnumName(_conf, extractedMap_,
                        _doc, docElementSelect_,
                        defaultEnum_, _multiple);
                //                docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
                //                docElementSelect_.setAttribute(CLASS_NAME_ATTRIBUTE, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
                //                _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
            } else {
                //                processOptionsMapEnum(extractedMap_, default_,
                //                        docSelect_, docElementSelect_);
                String className_ = ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME);
                processOptionsMapEnum(_conf, extractedMap_, default_,
                        _doc, docElementSelect_, className_);
                //                docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
                //                docElementSelect_.setAttribute(CLASS_NAME_ATTRIBUTE, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
                //                _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
            }
        }
        docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
        docElementSelect_.setAttribute(_conf.getPrefix()+ATTRIBUTE_CLASS_NAME, ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
        //        _currentModifiedNode.appendChild(_doc.importNode(docElementSelect_, true));
        _currentModifiedNode.appendChild(docElementSelect_);
    }

    private static void processOptionsMapEnum(Configuration _conf, ListableEntries<?, ?> _extractedMap,
            String _default, Document _docSelect, Element _docElementSelect, String _className) {
        boolean isEnumClass_;
        try {
            //            Class<?> class_ = ConstClasses.classForName(_className);
            Class<?> class_ = ConstClasses.classAliasForObjectNameNotInit(_className);
//            class_.asSubclass(Enum.class);
            isEnumClass_ = class_.isEnum();
//        } catch (ClassCastException _0) {
//            isEnumClass_ = false;
        } catch (RuntimeException _0) {
            if (_className.isEmpty()) {
                isEnumClass_ = false;
            } else {
                throw new RuntimeClassNotFoundException(_className+_conf.joinPages());
            }
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(),new Struct(_0));
        } catch (ExceptionInInitializerError _0) {
            throw new ErrorCausingException(_conf.joinPages(),new Struct(_0));
        }
        StringList names_;
        if (isEnumClass_) {
            names_ = StringList.splitChars(_default, SEP_ENUMS);
        } else {
            names_ = new StringList(_default);
        }
//        for (EntryCust<?, ?> e: _extractedMap.entryList())
        Listable<?> l_ = ExtractObject.entryList(_conf, 0, (ListableEntries<?, ?>) _extractedMap);
        Iterator<?> it_ = (Iterator<?>) ExtractObject.iterator(_conf, l_);
        while (ExtractObject.hasNext(_conf, it_)) {
            EntryCust<?,?> e_ = ExtractObject.nextEntry(_conf, it_);
            ExtractObject.checkNullPointer(_conf, e_);
            Object o_ = e_.getKey();
            if (o_ == null) {
                continue;
            }
            Element option_ = _docSelect.createElement(TAG_OPTION);
            String cmp_;
            if (o_ instanceof Enum<?>) {
                cmp_ = ((Enum<?>) o_).name();
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
            option_.appendChild(_docSelect.createTextNode(ExtractObject.toString(_conf, e_.getValue())));
            _docElementSelect.appendChild(option_);
        }
//        for (EntryCust<Object, Object> e: entryList(_conf, 0, (ListableEntries<Object, Object>) _extractedMap)) {
//            Object o_ = e.getKey();
//            if (o_ == null) {
//                continue;
//            }
//            Element option_ = _docSelect.createElement(TAG_OPTION);
//            String cmp_;
//            if (o_ instanceof Enum<?>) {
//                cmp_ = ((Enum<?>) o_).name();
//            }
//            option_.setAttribute(ATTRIBUTE_VALUE, cmp_);
//            for (String n: names_) {
//                if (StringList.eq(n,cmp_)) {
//                    option_.setAttribute(SELECTED, SELECTED);
//                    break;
//                }
//            }
//            option_.appendChild(_docSelect.createTextNode(toString(_conf, e.getValue())));
//            _docElementSelect.appendChild(option_);
//        }
    }

    private static void processOptionsMapEnumName(Configuration _conf, ListableEntries<?, ?> _extractedMap,
            Document _docSelect, Element _docElementSelect, Object _returnedVarValue,
            boolean _multiple) {
        IdList<Object> obj_ = new IdList<Object>();
        if (_multiple) {
            if (_returnedVarValue != null) {
//                obj_.addAllElts((Listable<?>)_returnedVarValue);
                obj_.addAllElts(ExtractObject.castListable(_conf, 0, _returnedVarValue));
            }
        } else {
            obj_.add(_returnedVarValue);
        }
        Listable<?> l_ = ExtractObject.entryList(_conf, 0, (ListableEntries<?, ?>) _extractedMap);
        Iterator<?> it_ = (Iterator<?>) ExtractObject.iterator(_conf, l_);
        while (ExtractObject.hasNext(_conf, it_)) {
            EntryCust<?,?> e_ = ExtractObject.nextEntry(_conf, it_);
            ExtractObject.checkNullPointer(_conf, e_);
            Object o_ = e_.getKey();
            if (o_ == null) {
                continue;
            }
            Element option_ = _docSelect.createElement(TAG_OPTION);
            if (o_ instanceof Enum<?>) {
                option_.setAttribute(ATTRIBUTE_VALUE, ((Enum<?>) o_).name());
                //                if (o_ == _returnedVarValue) {
                //                    option_.setAttribute(SELECTED, SELECTED);
                //                }
            } else {
                option_.setAttribute(ATTRIBUTE_VALUE, ExtractObject.toString(_conf, o_));
            }
            for (Object o: obj_) {
                if (ExtractObject.eq(_conf, o_, o)) {
                    option_.setAttribute(SELECTED, SELECTED);
                }
            }
            option_.appendChild(_docSelect.createTextNode(ExtractObject.toString(_conf, e_.getValue())));
            _docElementSelect.appendChild(option_);
        }
    }

//    @Deprecated
//    private static void processLoopList(Configuration _conf,
//            Map<String, String> _files, Bean _o, CustList<Node> _newNodesToBeRead,
//            Document _doc, CustList<Node> _newNodesToBeModified,
//            Node _currentModifiedNode, Node _n, String _contents) {
//        String container_ = _n.getAttributes().getNamedItem(ATTRIBUTE_LIST).getNodeValue();
//        container_ = formatNamedVariables(container_, _conf, _files, _o);
//        Object o_ = extractObject(container_, _o);
//        String var_ = _n.getAttributes().getNamedItem(ATTRIBUTE_VAR).getNodeValue();
//        Iterable<?> listCast_ = (Iterable<?>) o_;
//        int i_ = 0;
//        for (Object o: listCast_) {
//            String copyContents_ = _contents;
//            copyContents_ = StringList.replace(copyContents_, LEFT_EL+var_+GET_INDEX+RIGHT_EL, Integer.toString(i_));
//            //            copyContents_ = copyContents_.replaceAll(ESCAPED_LEFT_EL+var_+GET_ATTRIBUTE+NEXT_FIELDS+ESCAPED_RIGHT_EL, LEFT_EL+container_+LEFT_GET_ARR+i_+RIGHT_GET_ARR+GET_FORMAT_VAR+RIGHT_EL);
//            copyContents_ = getToken(copyContents_, var_ ,container_+LEFT_GET_ARR+i_+RIGHT_GET_ARR);
//            copyContents_ = addToRoot(StringList.replace(copyContents_, LEFT_EL+var_+GET_ATTRIBUTE+RIGHT_EL, o.toString()));
//            Document docLoc_ = XmlParser.parseSaxHtml(copyContents_);
//            updateNotEmptyAttributesMap(docLoc_, i_, FOR_BLOCK_TAG, ATTRIBUTE_LIST, var_, var_, container_, EMPTY_STRING);
//            updateNotEmptyAttributesMap(docLoc_, i_, FOR_BLOCK_TAG, ATTRIBUTE_MAP, var_, var_, container_, EMPTY_STRING);
//            updateNotEmptyAttributesMap(docLoc_, i_, FOR_BLOCK_TAG, ATTRIBUTE_ORDERED_KEYS, var_, var_, container_, EMPTY_STRING);
//            updateNotEmptyAttributesMap(docLoc_, i_, FIELD_BLOCK_TAG, ATTRIBUTE_METHOD, var_, var_, container_, EMPTY_STRING);
//            updateNotEmptyAttributesMap(docLoc_, i_, TEXT_AREA, VAR_METHOD, var_, var_, container_, EMPTY_STRING);
//            updateNotEmptyAttributesMap(docLoc_, i_, INPUT_TAG, VAR_METHOD, var_, var_, container_, EMPTY_STRING);
//            updateNotEmptyAttributesMap(docLoc_, i_, SELECT_BLOCK_TAG, VAR_METHOD, var_, var_, container_, EMPTY_STRING);
//            updateAllAttributesMap(docLoc_, i_, IF_BLOCK_TAG, ATTRIBUTE_CONDITION, var_, var_, container_, EMPTY_STRING);
//            updateAllAttributesMap(docLoc_, i_, TEXT_AREA, ATTRIBUTE_NAME, var_, var_, container_, EMPTY_STRING);
//            updateAllAttributesMap(docLoc_, i_, TEXT_AREA, ATTRIBUTE_VAR_VALUE, var_, var_, container_, EMPTY_STRING);
//            updateAllAttributesMap(docLoc_, i_, INPUT_TAG, ATTRIBUTE_NAME, var_, var_, container_, EMPTY_STRING);
//            updateAllAttributesMap(docLoc_, i_, INPUT_TAG, ATTRIBUTE_VAR_VALUE, var_, var_, container_, EMPTY_STRING);
//            updateAllAttributesMap(docLoc_, i_, SELECT_BLOCK_TAG, ATTRIBUTE_LIST, var_, var_, container_, EMPTY_STRING);
//            updateAllAttributesMap(docLoc_, i_, SELECT_BLOCK_TAG, ATTRIBUTE_NAME, var_, var_, container_, EMPTY_STRING);
//            updateAllAttributesMap(docLoc_, i_, SELECT_BLOCK_TAG, ATTRIBUTE_VAR_VALUE, var_, var_, container_, EMPTY_STRING);
//            updateAllAttributesMap(docLoc_, i_, FIELD_BLOCK_TAG, ATTRIBUTE_VALUE, var_, var_, container_, EMPTY_STRING);
//            updateNotEmptyAttributesMap(docLoc_, i_, ALL_TAGS, ATTRIBUTE_ID, var_, var_, container_, EMPTY_STRING);
//            updateNotEmptyAttributesMap(docLoc_, i_, ALL_TAGS, ATTRIBUTE_GROUP_ID, var_, var_, container_, EMPTY_STRING);
//            updateNotEmptyAttributesMap(docLoc_, i_, SPAN_TAG, ATTRIBUTE_FOR, var_, var_, container_, EMPTY_STRING);
//            updateSelectTags(container_, var_, var_, EMPTY_STRING, i_, docLoc_);
//            updateAttributesWithPrefix(docLoc_, i_,TAG_A,ATTRIBUTE_HREF);
//            updateAttributesWithPrefix(docLoc_, i_, TAG_FORM, ATTRIBUTE_ACTION);
//            updateAttributesWithPrefix(docLoc_, i_,TAG_A,ATTRIBUTE_COMMAND);
//            updateAttributesWithPrefix(docLoc_, i_, TAG_FORM, ATTRIBUTE_COMMAND);
//            updateAttributesWithPrefix(docLoc_, i_, ALL_TAGS, ATTRIBUTE_CLASS);
//            updateFormTags(i_, docLoc_);
//
//            _newNodesToBeRead.add(docLoc_.getDocumentElement());
//            Element n_ = _doc.createElement(docLoc_.getDocumentElement().getNodeName());
//            NamedNodeMap map_ = docLoc_.getDocumentElement().getAttributes();
//            setAttributes(n_, map_);
//            _newNodesToBeModified.add(n_);
//            _currentModifiedNode.appendChild(n_);
//            i_++;
//        }
//    }
//
//    @Deprecated
//    private static void processLoopMap(Configuration _conf,
//            Map<String, String> _files, Bean _o, CustList<Node> _newNodesToBeRead,
//            Document _doc, CustList<Node> _newNodesToBeModified,
//            Node _currentModifiedNode, Node _n, String _contents) {
//        String container_ = _n.getAttributes().getNamedItem(ATTRIBUTE_MAP).getNodeValue();
//        container_ = formatNamedVariables(container_, _conf, _files, _o);
//        String key_ = _n.getAttributes().getNamedItem(ATTRIBUTE_KEY).getNodeValue();
//        String value_ = _n.getAttributes().getNamedItem(ATTRIBUTE_VALUE).getNodeValue();
//        if (StringList.eq(key_,value_)) {
//            throw new KeyValueException(key_);
//        }
//        Object o_ = extractObject(container_, _o);
//        String listMethod_ = null;
//        if (_n.getAttributes().getNamedItem(ATTRIBUTE_ORDERED_KEYS) != null) {
//            listMethod_ = _n.getAttributes().getNamedItem(ATTRIBUTE_ORDERED_KEYS).getNodeValue();
//        }
//        Iterable<?> iterable_;
//        ListableEntries<?,?> mapCast_ = (ListableEntries<?,?>) o_;
//        if (o_ instanceof SortableMap) {
//            iterable_ = mapCast_.getKeys();
//        } else {
//            Listable<?> keys_ = mapCast_.getKeys();
//            if (listMethod_ == null) {
//                iterable_ = orderedList(keys_);
//            } else {
//                iterable_ = (Iterable<?>) extractObject(listMethod_+NO_PARAM_METHOD, _o);
//            }
//        }
//        if (listMethod_ == null) {
//            listMethod_ = NULL_METHOD;
//        }
//        int i_ = 0;
//        for (Object o: iterable_) {
//            String copyContents_ = _contents;
//            copyContents_ = StringList.replace(copyContents_, LEFT_EL+key_+GET_INDEX+RIGHT_EL, Integer.toString(i_));
//            //            copyContents_ = copyContents_.replaceAll(ESCAPED_LEFT_EL+key_+GET_ATTRIBUTE+NEXT_FIELDS+ESCAPED_RIGHT_EL, LEFT_EL+container_+LEFT_GET_ARR+i_+RIGHT_GET_ARR+listMethod_+GET_KEY+GET_FORMAT_VAR+RIGHT_EL);
//            copyContents_ = getToken(copyContents_, key_ ,container_+LEFT_GET_ARR+i_+RIGHT_GET_ARR+listMethod_+GET_KEY);
//            //            copyContents_ = copyContents_.replaceAll(ESCAPED_LEFT_EL+value_+GET_ATTRIBUTE+NEXT_FIELDS+ESCAPED_RIGHT_EL, LEFT_EL+container_+LEFT_GET_ARR+i_+RIGHT_GET_ARR+listMethod_+GET_VALUE+GET_FORMAT_VAR+RIGHT_EL);
//            copyContents_ = getToken(copyContents_, value_ ,container_+LEFT_GET_ARR+i_+RIGHT_GET_ARR+listMethod_+GET_VALUE);
//            Map<String,String> loc_ = new Map<String,String>();
//            loc_.put(LEFT_EL+key_+GET_ATTRIBUTE+RIGHT_EL, o.toString());
//            loc_.put(LEFT_EL+value_+GET_ATTRIBUTE+RIGHT_EL, mapCast_.get(o).toString());
//            //            copyContents_ = copyContents_.replace(LEFT_EL+key_+GET_ATTRIBUTE+RIGHT_EL, o.toString());
//            //            copyContents_ = addToRoot(copyContents_.replace(LEFT_EL+value_+GET_ATTRIBUTE+RIGHT_EL, mapCast_.get(o).toString()));
//            copyContents_ = addToRoot(StringList.replace(copyContents_, loc_));
//            Document docLoc_ = XmlParser.parseSaxHtml(copyContents_);
//            //NodeList nodes_ = docLoc_.getElementsByTagName(FOR_BLOCK_TAG);
//            //int length_ = nodes_.getLength();
//            updateNotEmptyAttributesMap(docLoc_, i_, FOR_BLOCK_TAG, ATTRIBUTE_LIST, key_, value_, container_, listMethod_);
//            updateNotEmptyAttributesMap(docLoc_, i_, FOR_BLOCK_TAG, ATTRIBUTE_MAP, key_, value_, container_, listMethod_);
//            updateNotEmptyAttributesMap(docLoc_, i_, FOR_BLOCK_TAG, ATTRIBUTE_ORDERED_KEYS, key_, value_, container_, listMethod_);
//            updateNotEmptyAttributesMap(docLoc_, i_, FIELD_BLOCK_TAG, ATTRIBUTE_METHOD, key_, value_, container_, listMethod_);
//            updateNotEmptyAttributesMap(docLoc_, i_, TEXT_AREA, VAR_METHOD, key_, value_, container_, listMethod_);
//            updateNotEmptyAttributesMap(docLoc_, i_, INPUT_TAG, VAR_METHOD, key_, value_, container_, listMethod_);
//            updateNotEmptyAttributesMap(docLoc_, i_, SELECT_BLOCK_TAG, VAR_METHOD, key_, value_, container_, listMethod_);
//            updateAllAttributesMap(docLoc_, i_, IF_BLOCK_TAG, ATTRIBUTE_CONDITION, key_, value_, container_, listMethod_);
//            updateAllAttributesMap(docLoc_, i_, TEXT_AREA, ATTRIBUTE_NAME, key_, value_, container_, listMethod_);
//            updateAllAttributesMap(docLoc_, i_, TEXT_AREA, ATTRIBUTE_VAR_VALUE, key_, value_, container_, listMethod_);
//            updateAllAttributesMap(docLoc_, i_, INPUT_TAG, ATTRIBUTE_NAME, key_, value_, container_, listMethod_);
//            updateAllAttributesMap(docLoc_, i_, INPUT_TAG, ATTRIBUTE_VAR_VALUE, key_, value_, container_, listMethod_);
//            updateAllAttributesMap(docLoc_, i_, SELECT_BLOCK_TAG, ATTRIBUTE_LIST, key_, value_, container_, listMethod_);
//            updateAllAttributesMap(docLoc_, i_, SELECT_BLOCK_TAG, ATTRIBUTE_NAME, key_, value_, container_, listMethod_);
//            updateAllAttributesMap(docLoc_, i_, SELECT_BLOCK_TAG, ATTRIBUTE_VAR_VALUE, key_, value_, container_, listMethod_);
//            updateAllAttributesMap(docLoc_, i_, FIELD_BLOCK_TAG, ATTRIBUTE_VALUE, key_, value_, container_, listMethod_);
//            updateNotEmptyAttributesMap(docLoc_, i_, ALL_TAGS, ATTRIBUTE_ID, key_, value_, container_, listMethod_);
//            updateNotEmptyAttributesMap(docLoc_, i_, ALL_TAGS, ATTRIBUTE_GROUP_ID, key_, value_, container_, listMethod_);
//            updateNotEmptyAttributesMap(docLoc_, i_, SPAN_TAG, ATTRIBUTE_FOR, key_, value_, container_, listMethod_);
//            updateSelectTags(container_, key_, value_,
//                    listMethod_, i_, docLoc_);
//            updateAttributesWithPrefix(docLoc_, i_, TAG_A, ATTRIBUTE_HREF);
//            updateAttributesWithPrefix(docLoc_, i_, TAG_FORM, ATTRIBUTE_ACTION);
//            updateAttributesWithPrefix(docLoc_, i_, TAG_A, ATTRIBUTE_COMMAND);
//            updateAttributesWithPrefix(docLoc_, i_, TAG_FORM, ATTRIBUTE_COMMAND);
//            updateAttributesWithPrefix(docLoc_, i_, ALL_TAGS, ATTRIBUTE_CLASS);
//            updateFormTags(i_, docLoc_);
//
//            _newNodesToBeRead.add(docLoc_.getDocumentElement());
//            Element n_ = _doc.createElement(docLoc_.getDocumentElement().getNodeName());
//            NamedNodeMap map_ = docLoc_.getDocumentElement().getAttributes();
//            setAttributes(n_, map_);
//            _newNodesToBeModified.add(n_);
//            _currentModifiedNode.appendChild(n_);
//            i_++;
//        }
//    }

//    private static String getToken(String _string,String _var, String _replace) {
//        StringList tokens_ = StringList.getTokensSets(_string);
//        StringList newList_ = new StringList();
//        String prefix_ = LEFT_EL+_var+GET_ATTRIBUTE;
//        for (String t: tokens_) {
//            if (t.startsWith(prefix_)) {
//                String next_ = t.substring(prefix_.length());
//                if (!StringList.eq(next_,RIGHT_EL)) {
//                    newList_.add(LEFT_EL+_replace+DOT+next_);
//                } else {
//                    //                    newList_.add(LEFT_EL+_replace+next_);
//                    newList_.add(t);
//                }
//            } else {
//                newList_.add(t);
//            }
//        }
//        return newList_.join(EMPTY_STRING);
//    }
//
//    @Deprecated
//    static void updateFormTags(int _index, Document _docLoc) {
//        NodeList nodes_;
//        int length_;
//        nodes_ = _docLoc.getElementsByTagName(TAG_FORM);
//        length_ = nodes_.getLength();
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            Node node_ = nodes_.item(j);
//            Element elt_ = (Element) node_;
//            String name_ = elt_.getAttribute(ATTRIBUTE_NAME);
//            if (name_.isEmpty()) {
//                continue;
//            }
//            if (name_.contains(LEFT_PAR_COMMA)) {
//                name_ = StringList.replace(name_, LEFT_PAR_COMMA, LEFT_PAR+String.valueOf(_index)+COMMA);
//            } else if (name_.contains(DOUBLE_COMMA)) {
//                //                name_ = name_.replaceAll(NEXT_ARG, FORMAT_VAR+COMMA+String.valueOf(_index)+COMMA);
//                name_ = insertArguments(name_,_index);
//                //name_.replaceAll(NEXT_ARG, FORMAT_VAR+COMMA+String.valueOf(_index)+COMMA);
//            } else if (name_.contains(COMMA_RIGHT_PAR)) {
//                name_ = StringList.replace(name_, COMMA_RIGHT_PAR, COMMA+String.valueOf(_index)+RIGHT_PAR);
//            } else if (name_.contains(NO_PARAM_METHOD)) {
//                name_ = StringList.replace(name_, NO_PARAM_METHOD, LEFT_PAR+String.valueOf(_index)+RIGHT_PAR);
//            }
//            elt_.setAttribute(ATTRIBUTE_NAME, name_);
//        }
//    }
//
//    @Deprecated
//    static void updateSelectTags(String _container, String _key,
//            String _value, String _listMethod, int _index, Document _docLoc) {
//        NodeList nodes_;
//        int length_;
//        nodes_ = _docLoc.getElementsByTagName(SELECT_BLOCK_TAG);
//        length_ = nodes_.getLength();
//        if (StringList.eq(_key,_value)) {
//            for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//                Node node_ = nodes_.item(j);
//                Element elt_ = (Element) node_;
//                String varLoc_ = elt_.getAttribute(DEFAULT_ATTRIBUTE);
//                if (varLoc_.startsWith(CALL_METHOD)) {
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+CALL_AFTER, _container+LEFT_GET_ARR+_index+RIGHT_GET_ARR+GET_FORMAT_VAR);
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+GET_ATTRIBUTE, _container+LEFT_GET_ARR+_index+RIGHT_GET_ARR);
//                    varLoc_ = formatContainer(varLoc_, _key, GET_ATTRIBUTE, _container+LEFT_GET_ARR+_index+RIGHT_GET_ARR);
//                    elt_.setAttribute(DEFAULT_ATTRIBUTE, varLoc_);
//                }
//            }
//        } else {
//            for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//                Node node_ = nodes_.item(j);
//                Element elt_ = (Element) node_;
//                String varLoc_ = elt_.getAttribute(DEFAULT_ATTRIBUTE);
//                if (varLoc_.startsWith(CALL_METHOD)) {
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+CALL_AFTER, _container+LEFT_GET_ARR+_index+RIGHT_GET_ARR+_listMethod+GET_KEY+GET_FORMAT_VAR);
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+GET_ATTRIBUTE, _container+LEFT_GET_ARR+_index+RIGHT_GET_ARR+_listMethod+GET_KEY);
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_value+CALL_AFTER, _container+LEFT_GET_ARR+_index+RIGHT_GET_ARR+_listMethod+GET_VALUE+GET_FORMAT_VAR);
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_value+GET_ATTRIBUTE, _container+LEFT_GET_ARR+_index+RIGHT_GET_ARR+_listMethod+GET_VALUE);
//                    varLoc_ = formatContainer(varLoc_, _key, GET_ATTRIBUTE, _container+LEFT_GET_ARR+_index+RIGHT_GET_ARR+_listMethod+GET_KEY);
//                    varLoc_ = formatContainer(varLoc_, _value, GET_ATTRIBUTE, _container+LEFT_GET_ARR+_index+RIGHT_GET_ARR+_listMethod+GET_VALUE);
//                    elt_.setAttribute(DEFAULT_ATTRIBUTE, varLoc_);
//                }
//            }
//        }
//    }

//    static void setAttributes(Element _n, Element _other) {
//        setAttributes(_n, _other.getAttributes());
//    }
//
//    static void setAttributes(Element _n, NamedNodeMap _map) {
//        int length_ = _map.getLength();
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            _n.setAttribute(_map.item(j).getNodeName(), _map.item(j).getNodeValue());
//        }
//    }

//    @Deprecated
//    static void updateNotEmptyAttributesMap(Document _docLoc, int _i,
//            String _tagName, String _attributeName,
//            String _key, String _value,
//            String _container, String _listMethod) {
//        NodeList nodes_ = _docLoc.getElementsByTagName(_tagName);
//        int length_ = nodes_.getLength();
//        if (StringList.eq(_key,_value)) {
//            for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//                Node node_ = nodes_.item(j);
//                Element elt_ = (Element) node_;
//                String varLoc_ = elt_.getAttribute(_attributeName);
//                if (!varLoc_.isEmpty()) {
//                    varLoc_ = formatIndex(varLoc_, _key, GET_INDEX, Integer.toString(_i));
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+GET_INDEX, Integer.toString(_i));
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+CALL_AFTER, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+GET_FORMAT_VAR);
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR);
//                    varLoc_ = formatContainer(varLoc_, _key, GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR);
//                    elt_.setAttribute(_attributeName, varLoc_);
//                }
//            }
//        } else {
//            for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//                Node node_ = nodes_.item(j);
//                Element elt_ = (Element) node_;
//                String varLoc_ = elt_.getAttribute(_attributeName);
//                if (!varLoc_.isEmpty()) {
//                    varLoc_ = formatIndex(varLoc_, _key, GET_INDEX, Integer.toString(_i));
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+GET_INDEX, Integer.toString(_i));
//                    varLoc_ = formatContainer(varLoc_, _key, GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_KEY);
//                    varLoc_ = formatContainer(varLoc_, _value, GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_VALUE);
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+CALL_AFTER, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_KEY+GET_FORMAT_VAR);
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_KEY);
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_value+CALL_AFTER, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_VALUE+GET_FORMAT_VAR);
//                    //                    varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_value+GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_VALUE);
//                    elt_.setAttribute(_attributeName, varLoc_);
//                }
//            }
//        }
//    }
//
//    @Deprecated
//    static void updateAllAttributesMap(Document _docLoc, int _i,
//            String _tagName, String _attributeName,
//            String _key, String _value,
//            String _container, String _listMethod) {
//        NodeList nodes_ = _docLoc.getElementsByTagName(_tagName);
//        int length_ = nodes_.getLength();
//        if (StringList.eq(_key,_value)) {
//            for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//                Node node_ = nodes_.item(j);
//                Element elt_ = (Element) node_;
//                String varLoc_ = elt_.getAttribute(_attributeName);
//                varLoc_ = formatIndex(varLoc_, _key, GET_INDEX, Integer.toString(_i));
//                //                varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+GET_INDEX, Integer.toString(_i));
//                //                varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+CALL_AFTER, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+GET_FORMAT_VAR);
//                //                varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR);
//                varLoc_ = formatContainer(varLoc_, _key, GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR);
//                elt_.setAttribute(_attributeName, varLoc_);
//            }
//        } else {
//            for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//                Node node_ = nodes_.item(j);
//                Element elt_ = (Element) node_;
//                String varLoc_ = elt_.getAttribute(_attributeName);
//                //                varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+GET_INDEX, Integer.toString(_i));
//                varLoc_ = formatIndex(varLoc_, _key, GET_INDEX, Integer.toString(_i));
//                varLoc_ = formatContainer(varLoc_, _key, GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_KEY);
//                varLoc_ = formatContainer(varLoc_, _value, GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_VALUE);
//                //                varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+CALL_AFTER, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_KEY+GET_FORMAT_VAR);
//                //                varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_key+GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_KEY);
//                //                varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_value+CALL_AFTER, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_VALUE+GET_FORMAT_VAR);
//                //                varLoc_ = varLoc_.replaceAll(StringList.BOUNDS+_value+GET_ATTRIBUTE, _container+LEFT_GET_ARR+_i+RIGHT_GET_ARR+_listMethod+GET_VALUE);
//                elt_.setAttribute(_attributeName, varLoc_);
//            }
//        }
//    }
//
//    @Deprecated
//    private static String formatIndex(String _string, String _key, String _suffix,String _arg) {
//        StringList list_ = StringList.getWordsSeparators(_string);
//        StringList newList_ = new StringList();
//        int i_ = CustList.FIRST_INDEX;
//        for (String t : list_) {
//            if (i_ % 2 == 0) {
//                if (newList_.isEmpty()) {
//                    newList_.add(t);
//                } else if (!StringList.eq(newList_.last(),_key)) {
//                    newList_.setLast(newList_.last()+t);
//                } else {
//                    newList_.add(t);
//                }
//                i_ ++;
//                continue;
//            }
//            if (StringList.eq(t,_key)) {
//                newList_.add(t);
//                i_ ++;
//                continue;
//            }
//            newList_.setLast(newList_.last()+t);
//            i_ ++;
//        }
//        i_ = CustList.FIRST_INDEX;
//        for (String t : newList_) {
//            if (StringList.eq(t,_key)) {
//                if (i_ + 1 == newList_.size()) {
//                    break;
//                }
//                String nextSep_ = newList_.get(i_ + 1);
//                if (nextSep_.startsWith(_suffix)) {
//                    newList_.set(i_, t+_suffix);
//                    newList_.set(i_ + 1, nextSep_.substring(_suffix.length()));
//                }
//            }
//            i_ ++;
//            continue;
//        }
//        StringBuilder str_ = new StringBuilder();
//        for (String t : newList_) {
//            if (StringList.eq(t,_key+_suffix)) {
//                str_.append(_arg);
//            } else {
//                str_.append(t);
//            }
//        }
//        return str_.toString();
//    }
//
//    @Deprecated
//    private static String formatContainer(String _string, String _key, String _suffix,String _arg) {
//        StringList list_ = StringList.getWordsSeparators(_string);
//        StringList newList_ = new StringList();
//        int i_ = CustList.FIRST_INDEX;
//        for (String t : list_) {
//            if (i_ % 2 == 0) {
//                if (newList_.isEmpty()) {
//                    newList_.add(t);
//                } else if (!StringList.eq(newList_.last(),_key)) {
//                    newList_.setLast(newList_.last()+t);
//                } else {
//                    newList_.add(t);
//                }
//                i_ ++;
//                continue;
//            }
//            if (StringList.eq(t,_key)) {
//                newList_.add(t);
//                i_ ++;
//                continue;
//            }
//            newList_.setLast(newList_.last()+t);
//            i_ ++;
//        }
//        int len_ = newList_.size();
//        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
//            String t_ = newList_.get(i);
//            if (!StringList.eq(t_,_key)) {
//                continue;
//            }
//            if (i + 1 == newList_.size()) {
//                break;
//            }
//            String nextSep_ = newList_.get(i + 1);
//            if (nextSep_.startsWith(_suffix)) {
//                newList_.set(i, t_+_suffix);
//                newList_.set(i + 1, nextSep_.substring(_suffix.length()));
//            }
//        }
//        StringBuilder str_ = new StringBuilder();
//        i_ = CustList.FIRST_INDEX;
//        for (String t : newList_) {
//            if (StringList.eq(t,_key+_suffix)) {
//                str_.append(_arg);
//                if (!newList_.get(i_ + 1).isEmpty()) {
//                    //i_ + 1 < newList_.size()
//                    str_.append(DOT);
//                }
//            } else {
//                str_.append(t);
//            }
//            i_ ++;
//        }
//        return str_.toString();
//    }
//
//    @Deprecated
//    static void updateAttributesWithPrefix(Document _docLoc, int _i,
//            String _tagName, String _attributeName) {
//        NodeList nodes_;
//        int length_;
//        nodes_ = _docLoc.getElementsByTagName(_tagName);
//        length_ = nodes_.getLength();
//        for (int j=CustList.FIRST_INDEX;j<length_;j++) {
//            Node node_ = nodes_.item(j);
//            //            if (!(node_ instanceof Element)) {
//            //                continue;
//            //            }
//            Element elt_ = (Element) node_;
//            String varLoc_ = elt_.getAttribute(_attributeName);
//            if (!varLoc_.contains(CALL_METHOD)) {
//                continue;
//            }
//            if (varLoc_.contains(LEFT_PAR_COMMA)) {
//                varLoc_ = StringList.replace(varLoc_, LEFT_PAR_COMMA, LEFT_PAR+String.valueOf(_i)+COMMA);
//            } else if (varLoc_.contains(DOUBLE_COMMA)) {
//                //                varLoc_ = varLoc_.replaceAll(NEXT_ARG, FORMAT_VAR+COMMA+String.valueOf(_i)+COMMA);
//                varLoc_ = insertArguments(varLoc_,_i);
//            } else if (varLoc_.contains(COMMA_RIGHT_PAR)) {
//                varLoc_ = StringList.replace(varLoc_, COMMA_RIGHT_PAR, COMMA+String.valueOf(_i)+RIGHT_PAR);
//            } else if (varLoc_.contains(NO_PARAM_METHOD)) {
//                varLoc_ = StringList.replace(varLoc_, NO_PARAM_METHOD, LEFT_PAR+String.valueOf(_i)+RIGHT_PAR);
//            }
//            elt_.setAttribute(_attributeName, varLoc_);
//        }
//    }
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
//    @Deprecated
//    static String processMessages(Configuration _conf,String _htmlText, Bean _bean, String _loc, Map<String,String> _files, String... _resourcesFolder) {
//        Document docOrig_ = XmlParser.parseSaxHtml(_htmlText);
//        CustList<Node> currentNodesToBeRead_ = new CustList<Node>();
//        currentNodesToBeRead_.add(docOrig_.getDocumentElement());
//        CustList<Node> newNodesToBeRead_ = new CustList<Node>();
//        Document doc_ = XmlParser.parseSaxHtml(EMPTY_HTML_DOC);
//        CustList<Node> currentNodesToBeModified_ = new CustList<Node>();
//        currentNodesToBeModified_.add(doc_.getDocumentElement());
//        setAttributes(doc_.getDocumentElement(), docOrig_.getDocumentElement());
//        CustList<Node> newNodesToBeModified_ = new CustList<Node>();
//        boolean modif_ = true;
//        int len_;
//        while (modif_) {
//            modif_ = false;
//            newNodesToBeRead_ = new CustList<Node>();
//            newNodesToBeModified_ = new CustList<Node>();
//            len_ = currentNodesToBeRead_.size();
//            for (int i = CustList.FIRST_INDEX; i <len_; i++) {
//                Node currentNode_ = currentNodesToBeRead_.get(i);
//                Node currentModifiedNode_ = currentNodesToBeModified_.get(i);
//                for (Node n : XmlParser.childrenNodes(currentNode_)) {
//                    if (n instanceof Element) {
//                        if (StringList.eq(n.getNodeName(),MESSAGE_BLOCK_TAG)) {
//                            String formatted_ = formatMessage(_conf, _loc, _bean, (Element) n, _files, _resourcesFolder);
//                            if (!((Element) n).getAttribute(ATTRIBUTE_ESCAPED).isEmpty()) {
//                                Text n_ = doc_.createTextNode(formatted_);
//                                //newNodesToBeModified_.add(n_);
//                                currentModifiedNode_.appendChild(n_);
//                                continue;
//                            }
//                            //                            formatted_ = interpretLgGtAmp(formatted_);
//                            Document docLoc_ = XmlParser.parseSaxHtml(addToRoot(formatted_));
//                            for (Node nTwo_: XmlParser.childrenNodes(docLoc_.getDocumentElement())) {
//                                if (nTwo_ instanceof Element) {
//                                    newNodesToBeRead_.add(nTwo_);
//                                    Element n_ = doc_.createElement(nTwo_.getNodeName());
//                                    //                                    NamedNodeMap map_ = nTwo_.getAttributes();
//                                    //                                    int lentgh_ = map_.getLength();
//                                    //                                    for (int j = CustList.FIRST_INDEX;j<lentgh_;j++) {
//                                    //                                        formatted_ = map_.item(j).getNodeValue();
//                                    ////                                        formatted_ = interpretLgGtAmp(formatted_);
//                                    //                                        n_.setAttribute(map_.item(j).getNodeName(), formatted_);
//                                    //                                    }
//                                    setAttributes(n_, (Element) nTwo_);
//                                    newNodesToBeModified_.add(n_);
//                                    currentModifiedNode_.appendChild(n_);
//                                }
//                                if (nTwo_ instanceof Text) {
//                                    formatted_ = nTwo_.getTextContent();
//                                    //                                    formatted_ = interpretLgGtAmp(formatted_);
//                                    Text n_ = doc_.createTextNode(formatted_);
//                                    //newNodesToBeModified_.add(n_);
//                                    currentModifiedNode_.appendChild(n_);
//                                }
//                            }
//                            continue;
//                        }
//                        newNodesToBeRead_.add(n);
//                        Element n_ = doc_.createElement(n.getNodeName());
//                        //                        NamedNodeMap map_ = n.getAttributes();
//                        //                        int lentgh_ = map_.getLength();
//                        //                        for (int j = CustList.FIRST_INDEX;j<lentgh_;j++) {
//                        //                            String formatted_ = map_.item(j).getNodeValue();
//                        ////                            formatted_ = interpretLgGtAmp(formatted_);
//                        //                            n_.setAttribute(map_.item(j).getNodeName(), formatted_);
//                        //                        }
//                        setAttributes(n_, (Element) n);
//                        newNodesToBeModified_.add(n_);
//                        currentModifiedNode_.appendChild(n_);
//                        continue;
//                    }
//                    if (n instanceof Text) {
//                        //newNodesToBeRead_.add(n);
//                        String formatted_ = n.getTextContent();
//                        //                        formatted_ = interpretLgGtAmp(formatted_);
//                        Text n_ = doc_.createTextNode(formatted_);
//                        //newNodesToBeModified_.add(n_);
//                        currentModifiedNode_.appendChild(n_);
//                    }
//                }
//            }
//            if (!newNodesToBeRead_.isEmpty()) {
//                currentNodesToBeRead_ = new CustList<Node>(newNodesToBeRead_);
//                currentNodesToBeModified_ = new CustList<Node>(newNodesToBeModified_);
//                modif_ = true;
//            }
//        }
//        return XmlParser.toHtml(doc_);
//    }
//
//    //    private static String interpretLgGtAmp(String _formatted) {
//    //        Map<String,String> rep_ = new Map<String,String>();
//    //        rep_.put(E_LT, LT_BEGIN_TAG);
//    //        rep_.put(E_GT, GT_TAG);
//    //        rep_.put(E_AMP, AMP);
//    ////        String formatted_ = _formatted.replace(E_LT, LT_BEGIN_TAG);
//    ////        formatted_ = formatted_.replace(E_GT, GT_TAG);
//    //        return StringList.replace(_formatted, rep_);
//    ////        String formatted_ = interpretLtGt(_formatted);
//    ////        formatted_ = StringList.replace(formatted_, E_AMP, AMP);
//    ////        return formatted_;
//    //    }
//
//    @Deprecated
//    static String processSubmitTags(Configuration _conf,String _htmlText, Bean _bean, String _loc, Map<String,String> _files, String... _resourcesFolder) {
//        Document docOrig_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList list_ = docOrig_.getElementsByTagName(SUBMIT_BLOCK_TAG);
//        int length_ = list_.getLength();
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            Element node_ = (Element) list_.item(j);
//            String value_ = node_.getAttribute(ATTRIBUTE_VALUE_SUBMIT);
//            StringList elts_ = StringList.splitStrings(value_, COMMA);
//            //            String var_ = value_.split(COMMA)[0];
//            String var_ = elts_.first();
//            String fileName_ = _conf.getProperties().getVal(var_);
//            if (fileName_ == null) {
//                fileName_ = var_;
//            }
//            Map<String,String> messages_ = getInnerMessagesFromLocaleClass(_conf.getMessagesFolder(), _loc, fileName_, _files, _resourcesFolder);
//            //            String preformatted_ = messages_.getVal(value_.split(COMMA)[1]);
//            String preformatted_ = messages_.getVal(elts_.last());
//            preformatted_ = XmlParser.transformSpecialChars(preformatted_);
//            CustList<Object> objects_ = new CustList<Object>();
//            int i_ = CustList.FIRST_INDEX;
//            while (node_.hasAttribute(TAG_PARAM+i_)) {
//                String attribute_ = node_.getAttribute(TAG_PARAM+i_);
//                if (attribute_.startsWith(CALL_METHOD)) {
//                    objects_.add(extractObject(attribute_.substring(1), _bean));
//                } else {
//                    objects_.add(attribute_);
//                }
//                i_++;
//            }
//            node_.setAttribute(ATTRIBUTE_VALUE, StringList.simpleFormat(preformatted_, objects_.toArray()));
//            node_.setAttribute(ATTRIBUTE_TYPE, SUBMIT_TYPE);
//        }
//        list_ = docOrig_.getElementsByTagName(SUBMIT_BLOCK_TAG);
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            Element node_ = (Element) list_.item(j);
//            docOrig_.renameNode(node_, node_.getNamespaceURI(), INPUT_TAG);
//        }
//        return XmlParser.toHtml(docOrig_);
//    }
//
//    @Deprecated
//    static String processTitles(Configuration _conf,String _htmlText, Bean _bean, String _loc, Map<String,String> _files, String... _resourcesFolder) {
//        Document docOrig_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList list_ = docOrig_.getElementsByTagName(A_BLOCK_TAG);
//        int length_ = list_.getLength();
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            Element node_ = (Element) list_.item(j);
//            String value_ = node_.getAttribute(ATTRIBUTE_VALUE);
//            StringList elts_ = StringList.splitStrings(value_, COMMA);
//            //            String var_ = value_.split(COMMA)[0];
//            String var_ = elts_.first();
//            String fileName_ = _conf.getProperties().getVal(var_);
//            if (fileName_ == null) {
//                fileName_ = var_;
//            }
//            Map<String,String> messages_ = getInnerMessagesFromLocaleClass(_conf.getMessagesFolder(), _loc, fileName_, _files, _resourcesFolder);
//            //            String preformatted_ = messages_.getVal(value_.split(COMMA)[1]);
//            String preformatted_ = messages_.getVal(elts_.last());
//            preformatted_ = XmlParser.transformSpecialChars(preformatted_);
//            CustList<Object> objects_ = new CustList<Object>();
//            int i_ = CustList.FIRST_INDEX;
//            while (node_.hasAttribute(TAG_PARAM+i_)) {
//                String attribute_ = node_.getAttribute(TAG_PARAM+i_);
//                if (attribute_.startsWith(CALL_METHOD)) {
//                    objects_.add(extractObject(attribute_.substring(1), _bean));
//                } else {
//                    objects_.add(attribute_);
//                }
//                i_++;
//            }
//            node_.setAttribute(ATTRIBUTE_TITLE, StringList.simpleFormat(preformatted_, objects_.toArray()));
//        }
//        list_ = docOrig_.getElementsByTagName(A_BLOCK_TAG);
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            Element node_ = (Element) list_.item(j);
//            docOrig_.renameNode(node_, node_.getNamespaceURI(), TAG_A);
//        }
//        return XmlParser.toHtml(docOrig_);
//    }

//    static String processScript(String _htmlText, Map<String,String> _files, String... _resourcesFolder) {
//        Document docOrig_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList list_ = docOrig_.getElementsByTagName(SCRIPT);
//        Element link_ = null;
//        int length_ = list_.getLength();
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            link_ = (Element) list_.item(j);
//            NamedNodeMap map_ = link_.getAttributes();
//            Node href_ = map_.getNamedItem(ATTRIBUTE_HREF);
//            if (href_ == null){
//                continue;
//            }
//            link_.setAttribute(ATTRIBUTE_TYPE, SCRIPT_TYPE);
//            Text txt_ = docOrig_.createTextNode(getContentFile(_files,href_.getNodeValue(),_resourcesFolder));
//            link_.appendChild(txt_);
//        }
//        return XmlParser.toHtml(docOrig_);
//    }

//    static String processSpansErrors(String _htmlText) {
//        Document docOrig_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList list_ = docOrig_.getElementsByTagName(SPAN_TAG);
//        Element link_ = null;
//        int length_ = list_.getLength();
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            link_ = (Element) list_.item(j);
//            NamedNodeMap map_ = link_.getAttributes();
//            Node href_ = map_.getNamedItem(ATTRIBUTE_FOR);
//            if (href_ == null){
//                continue;
//            }
//            if (!XmlParser.childrenNodes(link_).isEmpty()) {
//                continue;
//            }
//            Text txt_ = docOrig_.createTextNode(SPACE);
//            link_.appendChild(txt_);
//        }
//        return XmlParser.toHtml(docOrig_);
//    }

//    static String processImagesTags(String _htmlText) {
//        Document docOrig_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList list_ = docOrig_.getElementsByTagName(IMG_BLOCK_TAG);
//        int length_ = list_.getLength();
//        for (int j = CustList.FIRST_INDEX;j<length_;j++) {
//            Element node_ = (Element) list_.item(j);
//            docOrig_.renameNode(node_, node_.getNamespaceURI(), TAG_IMG);
//        }
//        return XmlParser.toHtml(docOrig_);
//    }
//
//    static String processImages(String _htmlText, Map<String,String> _files, String... _resourcesFolder) {
//        Document docOrig_ = XmlParser.parseSaxHtml(_htmlText);
//        NodeList list_ = docOrig_.getElementsByTagName(TAG_IMG);
//        int length_ = list_.getLength();
//        for (int j=CustList.FIRST_INDEX;j<length_;j++) {
//            Node node_ = list_.item(j);
//            String src_ = ((Element)node_).getAttribute(ATTRIBUTE_SRC);
//            if (src_.isEmpty()) {
//                continue;
//            }
//            String wrap_ = ((Element)node_).getAttribute(ATTRIBUTE_ENCODE_IMG);
//            boolean keep_ = wrap_.isEmpty();
//            String content_ = getContentFile(_files,src_,_resourcesFolder);
//            if (keep_) {
//                ((Element)node_).setAttribute(ATTRIBUTE_SRC, content_);
//            } else {
//                content_ = ConverterBufferedImage.surroundImage(content_, wrap_);
//                ((Element)node_).setAttribute(ATTRIBUTE_SRC, content_);
//            }
//        }
//        return XmlParser.toHtml(docOrig_);
//    }

    //    public static String format(String _pattern, Configuration _conf, Map<String,String> _files, Bean _object) throws Exception{
    //        Map<String,Object> fields_ = getFields(_pattern, _conf, _files, _object);
    //        return formatFields(_pattern, fields_);
    //    }
    //
    //    public static String format(String _pattern, Object _object) throws Exception{
    //        Map<String,Object> fields_ = getFields(_pattern, _object);
    //        return formatFields(_pattern, fields_);
    //    }

    //    static Map<String,Object> getFields(String _pattern, Object _object) throws Exception {
    //        Map<String,Object> fields_ = new Map<>();
    //        Map<String,Integer> numbers_ = numberedFiels(_pattern);
    //        for (String k: numbers_.keySet()) {
    //            Object o_ = extractObject(k, _object);
    //            fields_.put(k, o_);
    //        }
    //        return fields_;
    //    }

//    @Deprecated
//    static String formatNamedVariables(String _pattern, Configuration _conf, Map<String,String> _files, Bean _object) {
//        //        String patternFormat_ = REG_EXP_EL;
//        //        StringList tokensSep_ = StringList.getTokensSeparators(_pattern, patternFormat_);
//        //        int nbTokensSep_ = tokensSep_.size();
//        //        StringList tokens_;
//        //        tokens_ = new StringList();
//        //        for (int i = CustList.FIRST_INDEX; i < nbTokensSep_; i++) {
//        //            if (i % 2 == 0) {
//        //                continue;
//        //            }
//        //            String token_ = tokensSep_.get(i).replaceAll(BOUNDS_EL, EMPTY_STRING);
//        //            tokens_.add(token_);
//        //        }
//        Map<String,String> fields_ = new Map<String,String>();
//        //        for (String k: tokens_) {}
//        for (String k: StringList.getFields(_pattern)) {
//            if (!k.contains(TR)) {
//                Object o_ = extractObject(k, _object);
//                fields_.put(LEFT_EL+k+RIGHT_EL, encodeXml(o_));
//                //                try {
//                //                    Object o_ = extractObject(k, _object);
//                //                    fields_.put(LEFT_EL+k+RIGHT_EL, encodeXml(o_));
//                //                } catch (Exception e) {
//                //                    e.printStackTrace();
//                //                }
//            } else {
//                //                StringList infos_ = new StringList(k.split(ESCAPED_TR));
//                StringList infos_ = StringList.splitStrings(k, TR);
//                //                Object o_ = extractObject(infos_.first().replaceAll(DOT_END, EMPTY_STRING), _object);
//                Object o_ = extractObject(StringList.replaceEnd(infos_.first(),DOT), _object);
//                o_ = _conf.getTranslators().getVal(infos_.last()).getString(_pattern, _conf, _files, _object, o_);
//                fields_.put(LEFT_EL+k+RIGHT_EL, encodeXml(o_));
//                //                try {
//                //                    StringList infos_ = new StringList(k.split(ESCAPED_TR));
//                //                    Object o_ = extractObject(infos_.first().replaceAll(DOT_END, EMPTY_STRING), _object);
//                //                    o_ = _conf.getTranslators().getVal(infos_.last()).getString(_pattern, _conf, _files, _object, o_);
//                //                    fields_.put(LEFT_EL+k+RIGHT_EL, encodeXml(o_));
//                //                } catch (Exception e) {
//                //                    e.printStackTrace();
//                //                }
//            }
//        }
//        return StringList.formatQuote(_pattern, fields_);
//    }
    //
    //    static StringList getFields(String _pattern) {
    //        StringList tokens_;
    //        tokens_ = new StringList();
    //        int i_ = CustList.FIRST_INDEX;
    //        while (i_ < _pattern.length()) {
    //            int indexOne_ = _pattern.indexOf(FORMAT_QUOTE, i_);
    //            if (indexOne_ == CustList.INDEX_NOT_FOUND_ELT) {
    //                //add tokens from _pattern (min:i_+1)
    ////                StringList tokensSep_ = StringList.getTokensSeparators(_pattern.substring(i_), REG_EXP_EL);
    ////                int nbTokensSep_ = tokensSep_.size();
    ////                for (int i = CustList.FIRST_INDEX; i < nbTokensSep_; i++) {
    ////                    if (i % 2 == 0) {
    ////                        continue;
    ////                    }
    //////                    String token_ = tokensSep_.get(i).replaceAll(BOUNDS_EL, EMPTY_STRING);
    ////                    String token_ = removeElBounds(tokensSep_.get(i));
    ////                    tokens_.add(token_);
    ////                }
    //                tokens_.addAll(getEl(_pattern.substring(i_)));
    //                break;
    //            }
    ////            StringList tokensSep_ = StringList.getTokensSeparators(_pattern.substring(i_, indexOne_), REG_EXP_EL);
    ////            int nbTokensSep_ = tokensSep_.size();
    ////            for (int i = CustList.FIRST_INDEX; i < nbTokensSep_; i++) {
    ////                if (i % 2 == 0) {
    ////                    continue;
    ////                }
    //////                String token_ = tokensSep_.get(i).replaceAll(BOUNDS_EL, EMPTY_STRING);
    ////                String token_ = removeElBounds(tokensSep_.get(i));
    ////                tokens_.add(token_);
    ////            }
    //            tokens_.addAll(getEl(_pattern.substring(i_, indexOne_)));
    //            int indexTwo_ = _pattern.indexOf(FORMAT_QUOTE, indexOne_ + 1);
    //            if (indexTwo_ == CustList.INDEX_NOT_FOUND_ELT) {
    //                break;
    //            }
    //            i_ = indexTwo_ + 1;
    //        }
    //        return tokens_;
    //    }

    //    private static StringList getEl(String _string) {
    //        StringList tokens_ = StringList.getTokensSets(_string);
    //        StringList newList_ = new StringList();
    //        for (String t: tokens_) {
    //            if (t.lastIndexOf(LEFT_EL) != CustList.FIRST_INDEX) {
    //                continue;
    //            }
    //            newList_.add(removeElBounds(t));
    //        }
    //        return newList_;
    //    }

//    private static String removeArrayBounds(String _string) {
//        StringBuilder str_ = new StringBuilder();
//        for (char c: _string.toCharArray()) {
//            if (c == StringList.LEFT_BRACKET) {
//                continue;
//            }
//            if (c == StringList.RIGHT_BRACKET) {
//                continue;
//            }
//            str_.append(c);
//        }
//        return str_.toString();
//    }

    //    private static String removeElBounds(String _string) {
    //        StringBuilder str_ = new StringBuilder();
    //        for (char c: _string.toCharArray()) {
    //            if (c == StringList.LEFT_BRACE) {
    //                continue;
    //            }
    //            if (c == StringList.RIGHT_BRACE) {
    //                continue;
    //            }
    //            str_.append(c);
    //        }
    //        return str_.toString();
    //    }

//    static String encodeXml(Object _object) {
//        if (_object == null) {
//            return null;
//        }
//        String string_ = _object.toString();
//        Map<String,String> chars_ = new Map<String,String>();
//        chars_.put(AMP, E_AMP);
//        chars_.put(LT_BEGIN_TAG, E_LT);
//        chars_.put(GT_TAG, E_GT);
//        //        string_ = XmlParser.transformSpecialChars(string_);
//        return StringList.formatBasic(string_, chars_, true);
//    }

//    private static String transformResourceUrl(String _htmlText) {
//        String newHtmlText_ = _htmlText;
//        try {
//            Document doc_ = XmlParser.parseSaxHtml(newHtmlText_);
//            NodeList ls_ = doc_.getElementsByTagName(TAG_IMG);
//            int s_ = ls_.getLength();
//            for (int i = CustList.FIRST_INDEX; i < s_; i++) {
//                NamedNodeMap map_ = ls_.item(i).getAttributes();
//                Node src_ = map_.getNamedItem(ATTRIBUTE_SRC);
//                if (src_ == null){
//                    continue;
//                }
//                String attribute_ = src_.getNodeValue();
//                if (attribute_.startsWith(FILE)) {
//                    continue;
//                }
//                attribute_ = StringList.replace(attribute_, IMPLICIT_LANGUAGE, SEPARATOR_PATH+Constants.getLanguage()+SEPARATOR_PATH);
//                src_.setNodeValue(attribute_);
//            }
//            ls_ = doc_.getElementsByTagName(TAG_LINK);
//            s_ = ls_.getLength();
//            for (int i = CustList.FIRST_INDEX; i < s_; i++) {
//                NamedNodeMap map_ = ls_.item(i).getAttributes();
//                Node rel_ = map_.getNamedItem(ATTRIBUTE_REL);
//                if (rel_ == null || !StringList.eq(rel_.getNodeValue(),STYLESHEET)){
//                    continue;
//                }
//                Node href_ = map_.getNamedItem(ATTRIBUTE_HREF);
//                if (href_ == null){
//                    continue;
//                }
//                String attribute_ = href_.getNodeValue();
//                if (attribute_.startsWith(FILE)) {
//                    continue;
//                }
//                attribute_ = StringList.replace(attribute_, IMPLICIT_LANGUAGE, SEPARATOR_PATH+Constants.getLanguage()+SEPARATOR_PATH);
//                href_.setNodeValue(attribute_);
//            }
//            ls_ = doc_.getElementsByTagName(TAG_A);
//            s_ = ls_.getLength();
//            for (int i = CustList.FIRST_INDEX; i < s_; i++) {
//                NamedNodeMap map_ = ls_.item(i).getAttributes();
//                Node href_ = map_.getNamedItem(ATTRIBUTE_HREF);
//                if (href_ == null){
//                    continue;
//                }
//                String attribute_ = href_.getNodeValue();
//                if (attribute_.isEmpty()) {
//                    href_ = map_.getNamedItem(ATTRIBUTE_COMMAND);
//                    if (href_ == null){
//                        continue;
//                    }
//                    attribute_ = href_.getNodeValue();
//                }
//                if (attribute_.startsWith(FILE)) {
//                    continue;
//                }
//                attribute_ = StringList.replace(attribute_, IMPLICIT_LANGUAGE, SEPARATOR_PATH+Constants.getLanguage()+SEPARATOR_PATH);
//                href_.setNodeValue(attribute_);
//            }
//            ls_ = doc_.getElementsByTagName(TAG_FORM);
//            s_ = ls_.getLength();
//            for (int i = CustList.FIRST_INDEX; i < s_; i++) {
//                NamedNodeMap map_ = ls_.item(i).getAttributes();
//                Node action_ = map_.getNamedItem(ATTRIBUTE_ACTION);
//                if (action_ == null){
//                    continue;
//                }
//                String attribute_ = action_.getNodeValue();
//                if (attribute_.isEmpty()) {
//                    action_ = map_.getNamedItem(ATTRIBUTE_COMMAND);
//                    if (action_ == null){
//                        continue;
//                    }
//                    attribute_ = action_.getNodeValue();
//                }
//                if (attribute_.startsWith(FILE)) {
//                    continue;
//                }
//                attribute_ = StringList.replace(attribute_, IMPLICIT_LANGUAGE, SEPARATOR_PATH+Constants.getLanguage()+SEPARATOR_PATH);
//                action_.setNodeValue(attribute_);
//            }
//            newHtmlText_ = XmlParser.toHtml(doc_);
//        } catch (Exception _0) {
//        }
//        return newHtmlText_;
//    }

//    private static NodeAction getNextNode(Node _node, boolean _firstChild) {
//        NodeAction na_ = new NodeAction();
//        Node n_ = null;
//        if (_firstChild) {
//            n_ = _node.getFirstChild();
//            if (n_ != null) {
//                na_.getActions().add(ActionNext.FIRST_CHILD);
//                na_.setNode(n_);
//                return na_;
//            }
//        }
//        n_ = _node.getNextSibling();
//        if (n_ != null) {
//            na_.getActions().add(ActionNext.NEXT_SIBLING);
//            na_.setNode(n_);
//            return na_;
//        }
//        n_ = _node.getParentNode();
//        na_.getActions().add(ActionNext.PARENT_NODE);
//        Node next_ = n_.getNextSibling();
//        if (next_ != null) {
//            na_.getActions().add(ActionNext.NEXT_SIBLING);
//        }
//        while (next_ == null) {
//            Node par_ = n_.getParentNode();
//            if (par_ == null) {
//                break;
//            }
//            na_.getActions().add(ActionNext.PARENT_NODE);
//            next_ = par_.getNextSibling();
//            if (next_ != null) {
//                na_.getActions().add(ActionNext.NEXT_SIBLING);
//            }
//            n_ = par_;
//        }
//        if (next_ == null) {
//            return null;
//        }
//        na_.setNode(next_);
//        return na_;
//    }

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
//        }
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
//        if (next_ == null) {
//            return null;
//        }
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

//    static BlockStack hasSatckAncestor(Configuration _conf) {
//        ImportingPage ip_ = _conf.getLastPage();
//        if (ip_.getBlockStacks().isEmpty()) {
//            return null;
//        }
//        return ip_.getBlockStacks().last();
//    }

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
    //    public static Document formatDoc(Document _docOrig) throws Exception {
    //        Node r_ = _docOrig.getDocumentElement();
    //        Map<String,LoopVariable> vars_ = new Map<String,LoopVariable>();
    //        CustList<LoopHtmlStack> stacks_ = new CustList<LoopHtmlStack>();
    ////        Node en_ = r_.getFirstChild();
    //        Node en_ = r_;
    //        DocumentBuilderFactory dbf_ = DocumentBuilderFactory.newInstance();
    //        DocumentBuilder db_ = dbf_.newDocumentBuilder();
    //        Document doc_ = db_.newDocument();
    //        Node currentNode_ = doc_;
    ////        Node currentNode_ = doc_.createElement(r_.getNodeName());
    ////        NamedNodeMap map_ = r_.getAttributes();
    ////        int nbAttrs_ = map_.getLength();
    ////        for (int i = 0; i < nbAttrs_; i++) {
    ////            Node at_ = map_.item(i);
    ////            String name_ = at_.getNodeName();
    ////            String value_ = at_.getNodeValue();
    ////            ((Element) currentNode_).setAttribute(name_, value_);
    ////        }
    ////        doc_.appendChild(currentNode_);
    //
    //        return doc_;
    //    }

    private static void processAfterBlock(Configuration _conf, ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
//        Map<String,LoopVariable> vars_ = _ip.getVars();
//        CustList<LoopHtmlStack> stacks_ = _ip.getStacks();
        Node en_ = rw_.getRead();
//        Node currentNode_ = rw_.getWrite();
        if (en_.getFirstChild() != null) {
            en_ = en_.getFirstChild();
            rw_.setRead(en_);
        } else {
//            ReadWrite rw_ = new ReadWrite();
//            rw_.setRead(en_);
//            rw_.setWrite(currentNode_);
            processBlock(_conf, _ip);
//            return;
//            if (rw_ == null) {
//                return null;
//            }
//            en_ = rw_.getRead();
//            currentNode_ = rw_.getWrite();
        }
//        ReadWrite rw_ = new ReadWrite();
//        rw_.setRead(en_);
//        rw_.setWrite(currentNode_);
    }


    private static void processBlock(Configuration _conf, ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
//        Map<String,LoopVariable> vars_ = _ip.getVars();
//        CustList<LoopHtmlStack> stacks_ = _ip.getStacks();
        Node en_ = rw_.getRead();
        Node currentNode_ = rw_.getWrite();
        ParentElement parElt_ = getParentOfLastNode(_conf, en_, false);
        if (parElt_ == null) {
//            if (_ip.getThrownException() != null) {
//                _ip.getBlockStacks().removeLast();
//                throwingExceptionAfterFinally(_ip);
//            }
//            if (_ip.isReturning()) {
//                _ip.getBlockStacks().removeLast();
//                removeBlockFinally(_conf, _ip);
//                return;
//            }
            _ip.setReadWrite(null);
            return;
        }
//        Element par_ = getParentOfLastNode(_conf, en_, false);
        Element par_ = parElt_.getElement();
        if (par_ == null) {
//            if (interruptProcess(_conf)) {
//                return;
//            }
            NodeAction na_ = getNextNodeWrite(_conf, en_, false);
//            if (na_ == null) {
//                _ip.setReadWrite(null);
//                return;
//            }
            int i_ = 0;
            while (na_.getActions().get(i_) == ActionNext.PARENT_NODE) {
                //if (currentNode_.getParentNode() instanceof Element) {
                currentNode_ = currentNode_.getParentNode();
                //}
                i_ ++;
            }
            en_ = na_.getNode();
//            ReadWrite rw_ = new ReadWrite();
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
//            if (_ip.getThrownException() != null) {
//                _ip.getBlockStacks().removeLast();
//                throwingExceptionAfterFinally(_ip);
//            }
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
//        if (!isLastNodeForLoop(_conf, en_, false)) {
//            if (isLastNodeCatch(_conf, en_, false)) {
//                TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getBlockStacks().last();
//                Element catch_ = tryStack_.getCurrentCatchNode();
//                String var_ = catch_.getAttribute(ATTRIBUTE_VAR);
//                Map<String,LocalVariable> vars_ = _ip.getCatchVars();
//                vars_.removeKey(var_);
//                //exit catch block
//                currentNode_ = tryStack_.getWriteNode();
//                rw_.setRead(catch_);
//                rw_.setWrite(currentNode_);
//                return;
//            }
//            NodeAction na_ = getNextNodeWrite(_conf, en_, false);
//            if (na_ == null) {
//                _ip.setReadWrite(null);
//                return;
//            }
//            int i_ = 0;
//            while (na_.getActions().get(i_) == ActionNext.PARENT_NODE) {
//                //if (currentNode_.getParentNode() instanceof Element) {
//                currentNode_ = currentNode_.getParentNode();
//                //}
//                i_ ++;
//            }
//            en_ = na_.getNode();
////            ReadWrite rw_ = new ReadWrite();
//            rw_.setRead(en_);
//            rw_.setWrite(currentNode_);
//            return;
//        }
//        ReadWrite rw_ = new ReadWrite();
//        rw_.setRead(en_);
//        rw_.setWrite(currentNode_);
        if (isLoopNode(_conf, par_)) {
            processLastElementLoop(_conf, _ip);
        } else {
            if (isTryNode(_conf, par_)) {
                TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getLastStack();
                currentNode_ = tryStack_.getWriteNode();
//                rw_.setRead(tryStack_.getCatchNodes().first());
                rw_.setRead(par_.getNextSibling());
                rw_.setWrite(currentNode_);
                return;
            }
            if (isInstructionNode(_conf, par_)) {
                IfHtmlStack if_ = (IfHtmlStack) _ip.getLastStack();
                if (if_.lastVisitedNode() == par_) {
                    rw_.setRead(par_);
                } else {
//                    if_.increment();
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
//            if (ExtractCondition.isBeginOfConditionNode(_conf, par_)) {
//                
//            }
//            IfHtmlStack ifStack_ = (IfHtmlStack) _ip.getBlockStacks().last();
//            currentNode_ = ifStack_.getWriteNode();
//            rw_.setRead(ifStack_.getNodes().first());
//            rw_.setWrite(currentNode_);
//            NodeAction na_ = getNextNodeWrite(_conf, en_, false);
//            if (na_ == null) {
//                _ip.setReadWrite(null);
//                return;
//            }
//            int i_ = 0;
//            while (na_.getActions().get(i_) == ActionNext.PARENT_NODE) {
//                //if (currentNode_.getParentNode() instanceof Element) {
//                currentNode_ = currentNode_.getParentNode();
//                //}
//                i_ ++;
//            }
//            en_ = na_.getNode();
////            ReadWrite rw_ = new ReadWrite();
//            rw_.setRead(en_);
//            rw_.setWrite(currentNode_);
        }
    }


    private static void processElementOrText(Configuration _conf, ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
//        Map<String,LoopVariable> vars_ = _ip.getVars();
//        CustList<LoopHtmlStack> stacks_ = _ip.getStacks();
//        ReadWrite ret_;
        Node en_ = rw_.getRead();
        Node currentNode_ = rw_.getWrite();
        ParentElement parElt_ = getParentOfLastNode(_conf, en_, true);
        if (parElt_ == null) {
//            if (interruptProcess(_conf)) {
//                return;
//            }
            _ip.setReadWrite(null);
            return;
        }
//        Element par_ = getParentOfLastNode(_conf, en_, true);
        Element par_ = parElt_.getElement();
        if (par_ == null) {
//            if (interruptProcess(_conf)) {
//                return;
//            }
            NodeAction na_ = getNextNodeWrite(_conf, en_, true);
//            if (na_ == null) {
//                _ip.setReadWrite(null);
//                return;
//            }
            if (na_.getActions().first() == ActionNext.FIRST_CHILD) {
                currentNode_ = currentNode_.getLastChild();
            } else {
                int i_ = 0;
                while (na_.getActions().get(i_) == ActionNext.PARENT_NODE) {
                    //if (currentNode_.getParentNode() instanceof Element) {
                    currentNode_ = currentNode_.getParentNode();
                    //}
                    i_ ++;
                }
            }
            en_ = na_.getNode();
//            ret_ = new ReadWrite();
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
//            if (_ip.getThrownException() != null) {
//                _ip.getBlockStacks().removeLast();
//                throwingExceptionAfterFinally(_ip);
//            }
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
//        if (!isLastNodeForLoop(_conf, en_, true)) {
//            if (isLastNodeCatch(_conf, en_, true)) {
//                TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getBlockStacks().last();
//                Element catch_ = tryStack_.getCurrentCatchNode();
//                String var_ = catch_.getAttribute(ATTRIBUTE_VAR);
//                Map<String,LocalVariable> vars_ = _ip.getCatchVars();
//                vars_.removeKey(var_);
//                //exit catch block
//                currentNode_ = tryStack_.getWriteNode();
//                rw_.setRead(catch_);
//                rw_.setWrite(currentNode_);
//                return;
//            }
//            NodeAction na_ = getNextNodeWrite(_conf, en_, true);
//            if (na_ == null) {
//                _ip.setReadWrite(null);
//                return;
//            }
//            if (na_.getActions().first() == ActionNext.FIRST_CHILD) {
//                currentNode_ = currentNode_.getLastChild();
//            } else {
//                int i_ = 0;
//                while (na_.getActions().get(i_) == ActionNext.PARENT_NODE) {
//                    //if (currentNode_.getParentNode() instanceof Element) {
//                    currentNode_ = currentNode_.getParentNode();
//                    //}
//                    i_ ++;
//                }
//            }
//            en_ = na_.getNode();
////            ret_ = new ReadWrite();
//            rw_.setRead(en_);
//            rw_.setWrite(currentNode_);
//            return;
//        }
        if (isLoopNode(_conf, par_)) {
            processLastElementLoop(_conf, _ip);
        } else {
            if (isTryNode(_conf, par_)) {
                TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getLastStack();
                currentNode_ = tryStack_.getWriteNode();
//                rw_.setRead(tryStack_.getCatchNodes().first());
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
//                    if_.increment();
                    rw_.setRead(par_.getNextSibling());
                }
//                rw_.setRead(if_.getCurentVisitedNode());
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
//            NodeAction na_ = getNextNodeWrite(_conf, en_, false);
//            if (na_ == null) {
//                _ip.setReadWrite(null);
//                return;
//            }
//            int i_ = 0;
//            while (na_.getActions().get(i_) == ActionNext.PARENT_NODE) {
//                //if (currentNode_.getParentNode() instanceof Element) {
//                currentNode_ = currentNode_.getParentNode();
//                //}
//                i_ ++;
//            }
//            en_ = na_.getNode();
////            ReadWrite rw_ = new ReadWrite();
//            rw_.setRead(en_);
//            rw_.setWrite(currentNode_);
        }
    }
    private static void processLastElementLoop(Configuration _conf, ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        StringMap<LoopVariable> vars_ = _ip.getVars();

//        CustList<BlockStack> stacks_ = _ip.getBlockStacks();
//        ReadWrite ret_;
        Node en_ = rw_.getRead();
        Node currentNode_ = rw_.getWrite();
        LoopHtmlStack l_ = (LoopHtmlStack) _ip.getLastStack();
//        int index_ = stacks_.size() - 1;
//        while (index_ >= CustList.FIRST_INDEX) {
//            if (stacks_.get(index_) instanceof LoopHtmlStack) {
//                l_ = (LoopHtmlStack) stacks_.get(index_);
//                break;
//            }
//            index_--;
//        }
        //if (l_.getIterator().hasNext())
        if (keepLoop(_conf, _ip)) {
            Element forLoopLoc_ = l_.getReadNode();
            incrementLoop(_conf, l_, vars_);
            currentNode_ = l_.getWriteNode();
            en_ = forLoopLoc_;//.getFirstChild();
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
//        Element forLoopLoc_ = l_.getForNode();
//        removeVarAndLoop(forLoopLoc_, vars_, stacks_);
////        boolean continue_ = false;
////        ret_ = new ReadWrite();
//        while (isLastNodeForLoop(forLoopLoc_, false)) {
//            LoopHtmlStack lTwo_ = stacks_.last();
////            if (lTwo_.getIterator().hasNext())
//            if (keepLoop(_conf, _ip, _files)) {
//                Element forLoopLocTwo_ = lTwo_.getForNode();
//                incrementLoop(_conf, lTwo_, vars_);
//                currentNode_ = lTwo_.getCurrentNode();
//                en_ = forLoopLocTwo_.getFirstChild();
////                continue_ = true;
//                rw_.setRead(en_);
//                rw_.setWrite(currentNode_);
//                return;
//            }
//            l_ = lTwo_;
//            forLoopLoc_ = lTwo_.getForNode();
//            removeVarAndLoop(forLoopLoc_, vars_, stacks_);
//        }
////        if (continue_) {
////            return ret_;
////        }
//        NodeAction na_ = getNextNodeWrite(forLoopLoc_, false);
//        if (na_ == null) {
//            _ip.setReadWrite(null);
//            return;
//        }
//        currentNode_ = l_.getCurrentNode();
//        int i_ = 0;
//        while (na_.getActions().get(i_) == ActionNext.PARENT_NODE) {
//            //if (currentNode_.getParentNode() instanceof Element) {
//            currentNode_ = currentNode_.getParentNode();
//            //}
//            i_ ++;
//        }
//        en_ = na_.getNode();
////        ret_.setRead(en_);
////        ret_.setWrite(currentNode_);
//        rw_.setRead(en_);
//        rw_.setWrite(currentNode_);
//        return ret_;
//        exitLoops(_conf, _ip);
    }

//    private static void exitLoops(Configuration _conf, ImportingPage _ip) {
//        ReadWrite rw_ = _ip.getReadWrite();
//        Map<String,LoopVariable> vars_ = _ip.getVars();
//        CustList<BlockStack> stacks_ = _ip.getBlockStacks();
////        ReadWrite ret_;
//        Node en_ = rw_.getRead();
//        Node currentNode_ = rw_.getWrite();
//        LoopHtmlStack l_ = null;
//        int index_ = stacks_.size() - 1;
//        while (index_ >= CustList.FIRST_INDEX) {
//            if (stacks_.get(index_) instanceof LoopHtmlStack) {
//                l_ = (LoopHtmlStack) stacks_.get(index_);
//                break;
//            }
//            index_--;
//        }
//        Element forLoopLoc_ = l_.getReadNode();
//        removeVarAndLoop(_conf, forLoopLoc_, vars_);
////        boolean continue_ = false;
////        ret_ = new ReadWrite();
////        while (isLastNodeForLoop(_conf, forLoopLoc_, false))
//        while (true) {
//            Element elt_ = getParentOfLastNode(_conf, forLoopLoc_, false);
//            if (elt_ == null || !isLoopNode(_conf, elt_)) {
//                break;
//            }
//            LoopHtmlStack lTwo_ = (LoopHtmlStack) stacks_.last();
////            if (lTwo_.getIterator().hasNext())
//            if (keepLoop(_conf, _ip)) {
//                Element forLoopLocTwo_ = lTwo_.getReadNode();
//                incrementLoop(_conf, lTwo_, vars_);
//                currentNode_ = lTwo_.getWriteNode();
////                en_ = forLoopLocTwo_.getFirstChild();
//                en_ = forLoopLocTwo_;//.getFirstChild();
////                continue_ = true;
//                rw_.setRead(en_);
//                rw_.setWrite(currentNode_);
//                return;
//            }
//            l_ = lTwo_;
//            forLoopLoc_ = lTwo_.getReadNode();
//            removeVarAndLoop(_conf, forLoopLoc_, vars_);
//        }
//        Element elt_ = getParentOfLastNode(_conf, forLoopLoc_, false);
//        if (elt_ != null && isCatchNode(_conf, elt_)) {
////            TryHtmlStack tryStack_ = _ip.getTries().last();
//            TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getBlockStacks().last();
//            Element catch_ = tryStack_.getCurrentCatchNode();
//            String var_ = catch_.getAttribute(ATTRIBUTE_VAR);
//            vars_.removeKey(var_);
//            //exit catch block
//            currentNode_ = tryStack_.getWriteNode();
//            rw_.setRead(catch_);
//            rw_.setWrite(currentNode_);
//            return;
//        }
////        if (continue_) {
////            return ret_;
////        }
//        NodeAction na_ = getNextNodeWrite(_conf, forLoopLoc_, false);
//        if (na_ == null) {
//            _ip.setReadWrite(null);
//            return;
//        }
//        currentNode_ = l_.getWriteNode();
//        int i_ = 0;
//        while (na_.getActions().get(i_) == ActionNext.PARENT_NODE) {
//            //if (currentNode_.getParentNode() instanceof Element) {
//            currentNode_ = currentNode_.getParentNode();
//            //}
//            i_ ++;
//        }
//        en_ = na_.getNode();
////        ret_.setRead(en_);
////        ret_.setWrite(currentNode_);
//        rw_.setRead(en_);
//        rw_.setWrite(currentNode_);
////        return ret_;
//    }
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
            Iterator<?> iterator_ = _l.getIterator();
            if (iterator_ != null) {
                lv_.setElement(ExtractObject.next(_conf, iterator_));
            } else {
                lv_.setElement(Array.get(lv_.getArray(), (int) _l.getIndex()));
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
            EntryCust<?, ?> e_ = null;
            if (_l.isKeyValue()) {
                e_ = ExtractObject.castEntryCust(_conf,0,ExtractObject.next(_conf, _l.getIterator()));
                k_ = e_.getKey();
            } else {
                k_ = ExtractObject.next(_conf, _l.getIterator());
            }
//            Object k_ = ExtractObject.next(_conf, _l.getIterator());
            lv_.setElement(k_);
            lv_.setIndex(lv_.getIndex() + 1);
            String value_ = forLoopLoc_.getAttribute(ATTRIBUTE_VALUE);
            lv_ = _vars.getVal(value_);
            _conf.getLastPage().setProcessingNode(forLoopLoc_);
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VALUE);
            _conf.getLastPage().setLookForAttrValue(false);
            _conf.getLastPage().setOffset(0);
//            lv_.setElement(lv_.getMap().get(k_));
            lv_.setElement(e_.getValue());
//            if (_l.isKeyValue()) {
//                lv_.setElement(e_.getValue());
//            } else {
//                lv_.setElement(ExtractObject.getValue(_conf, 0, lv_.getMap(), k_));
//            }
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

    //, CustList<LoopHtmlStack> _stacks
    private static void removeVarAndLoop(Configuration _conf, Element _forLoop, StringMap<LoopVariable> _vars) {
        ImportingPage ip_ = _conf.getLastPage();
        String prefix_ = ip_.getPrefix();
//        ip_.getBlockStacks().removeLast();
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
//            String mainPrefix_ = _conf.getPrefix();
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
//        Element currentNode_ = _doc.createElement(_read.getNodeName());
//        NamedNodeMap map_ = _read.getAttributes();
//        int nbAttrs_ = map_.getLength();
//        for (int i = 0; i < nbAttrs_; i++) {
//            Node at_ = map_.item(i);
//            String name_ = at_.getNodeName();
//            String value_ = at_.getNodeValue();
//            currentNode_.setAttribute(name_, value_);
//        }
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
//        CustList<LoopHtmlStack> stacks_ = _ip.getStacks();
        Element currentWhileNode_ = (Element) rw_.getRead();
        boolean res_ = ExtractCondition.evaluateCondition(currentWhileNode_, _conf, _ip);
        LoopHtmlStack l_ = new LoopHtmlStack();
        l_.setReadNode(currentWhileNode_);
        l_.setWriteNode(written_);
        l_.setFinished(!res_);
//        if (!res_) {
//            return false;
//        }
//        stacks_.add(l_);
        _ip.addBlock(l_);
//        return true;
    }

    static void processDoWhile(Configuration _conf,
            ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        Element written_ = (Element) rw_.getWrite();
//        CustList<LoopHtmlStack> stacks_ = _ip.getStacks();
        Element currentWhileNode_ = (Element) rw_.getRead();
//        Node next_ = currentWhileNode_.getNextSibling();
//        while (true) {
//            if (next_ instanceof Text) {
//                next_ = next_.getNextSibling();
//                continue;
//            }
//            if (next_ instanceof Comment) {
//                next_ = next_.getNextSibling();
//                continue;
//            }
//            break;
//        }
//        boolean res_ = ExtractCondition.evaluateCondition((Element) next_, _conf, _ip);
        LoopHtmlStack l_ = new LoopHtmlStack();
        l_.setReadNode(currentWhileNode_);
        l_.setWriteNode(written_);
//        l_.setFinished(!res_);
//        if (!res_) {
//            return false;
//        }
//        stacks_.add(l_);
        _ip.addBlock(l_);
//        return true;
    }

    static void processLoop(Configuration _conf,
            ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        Element currentForNode_ = (Element) rw_.getRead();
        Element written_ = (Element) rw_.getWrite();
        boolean keyValue_ = false;
//        StringMap<LocalVariable> varsLoc_ = _ip.getLocalVars();
        StringMap<LoopVariable> varsLoop_ = _ip.getVars();
//        CustList<LoopHtmlStack> stacks_ = _ip.getStacks();
//        NodeList forElements_ = currentForNode_.getElementsByTagName(FOR_BLOCK_TAG);
//        int forNbs_ = forElements_.getLength();
//        StringList vars_ = new StringList();
//        for (int j = CustList.FIRST_INDEX; j < forNbs_; j++) {
//            Element elt_ = (Element) forElements_.item(j);
//            vars_.add(elt_.getAttribute(ATTRIBUTE_VAR));
//            vars_.add(elt_.getAttribute(ATTRIBUTE_KEY));
//            vars_.add(elt_.getAttribute(ATTRIBUTE_VALUE));
//        }
//        vars_.removeDuplicates();
//        vars_.removeObj(EMPTY_STRING);
//        int nbVars_ = vars_.size();
//        vars_.removeObj(currentForNode_.getAttribute(ATTRIBUTE_VAR));
//        vars_.removeObj(currentForNode_.getAttribute(ATTRIBUTE_KEY));
//        vars_.removeObj(currentForNode_.getAttribute(ATTRIBUTE_VALUE));
//        if (nbVars_ != vars_.size()) {
//            String message_ = currentForNode_.getAttribute(ATTRIBUTE_VAR);
//            message_ += SPACE_MESSAGE;
//            message_ += currentForNode_.getAttribute(ATTRIBUTE_KEY);
//            message_ += SPACE_MESSAGE;
//            message_ += currentForNode_.getAttribute(ATTRIBUTE_VALUE);
//            throw new AlreadyDefinedVarException(message_);
//        }
        Object iterable_ = null;
        String var_ = currentForNode_.getAttribute(ATTRIBUTE_VAR);
        String key_ = currentForNode_.getAttribute(ATTRIBUTE_KEY);
        String value_ = currentForNode_.getAttribute(ATTRIBUTE_VALUE);
        ListableEntries<?,?> mapCast_ = null;
//        String base_;
        String listMethod_ = null;
        long nbMaxIterations_ = 0;
        boolean iterationNb_ = false;
        long stepValue_ = 0;
        long fromValue_ = 0;
        Object realFromValue_ = 0;
        if (currentForNode_.hasAttribute(ATTRIBUTE_LIST)) {
            String listAttr_ = currentForNode_.getAttribute(ATTRIBUTE_LIST);
//            listAttr_ = formatNumVariables(listAttr_, _conf, _ip.getLocalVars(), varsLoop_, _files);
//            base_ = listAttr_;
            _ip.setProcessingAttribute(ATTRIBUTE_LIST);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
//            Object it_ = ExtractObject.improvedExtractObject(_conf, listAttr_);
            Object it_ = ElUtil.processEl(listAttr_, 0, _conf.toContextEl()).getObject();
            if (it_ == null) {
                _conf.getLastPage().addToOffset(listAttr_.length()+1);
                throw new NullObjectException(_conf.joinPages());
            }
            if (it_.getClass().isArray()) {
                iterable_ = it_;
            } else {
                iterable_ = ExtractObject.castIterable(_conf, listAttr_.length(), it_);
            }
        } else if (currentForNode_.hasAttribute(ATTRIBUTE_MAP)) {
            String mapAttr_ = currentForNode_.getAttribute(ATTRIBUTE_MAP);
//            mapAttr_ = formatNumVariables(mapAttr_, _conf, _ip.getLocalVars(), varsLoop_, _files);
//            base_ = mapAttr_;
//            if (StringList.eq(key_,value_)) {
//                throw new KeyValueException(key_);
//            }
            _ip.setProcessingAttribute(ATTRIBUTE_MAP);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
//            Object o_ = ExtractObject.improvedExtractObject(_conf, mapAttr_);
            Object o_ = ElUtil.processEl(mapAttr_, 0, _conf.toContextEl()).getObject();
//            if (currentForNode_.hasAttribute(ATTRIBUTE_ORDERED_KEYS)) {
//                listMethod_ = currentForNode_.getAttribute(ATTRIBUTE_ORDERED_KEYS);
//            }
//            mapCast_ = (ListableEntries<?,?>) o_;
            mapCast_ = ExtractObject.castListableEntries(_conf, mapAttr_.length(), o_);
            if (o_ instanceof SortableMap) {
                keyValue_ = true;
//                getKeys(_conf, mapAttr_.length(), true, mapCast_);
                iterable_ = ExtractObject.getKeys(_conf, false, mapAttr_.length(), mapCast_);
                if (iterable_ == null) {
                    throw new NullObjectException(_conf.joinPages());
                }
            } else {
//                Listable<?> keys_ = mapCast_.getKeys();
//                Listable<?> keys_ = ExtractObject.getKeys(_conf, listMethod_ == null, mapAttr_.length(), mapCast_);
                Listable<?> keys_ = ExtractObject.getKeys(_conf, true, mapAttr_.length(), mapCast_);
//                if (listMethod_ != null) {
////                    _ip.setProcessingAttribute(ATTRIBUTE_ORDERED_KEYS);
//                    _ip.setLookForAttrValue(true);
//                    _ip.setOffset(0);
////                    Object it_ = ExtractObject.improvedExtractObject(_conf, listMethod_+NO_PARAM_METHOD);
//                    Object it_ = ElUtil.processEl(listMethod_+NO_PARAM_METHOD, 0, _conf.toContextEl()).getObject();
//                    //ExtractObject.improvedExtractObject(_conf, listMethod_+NO_PARAM_METHOD);
//                    if (it_ == null) {
//                        _conf.getLastPage().addToOffset((listMethod_+NO_PARAM_METHOD).length());
//                        throw new NullObjectException(_conf.joinPages());
//                    }
//                    iterable_ = ExtractObject.castIterable(_conf, (listMethod_+NO_PARAM_METHOD).length(), it_);
//                } else {
//                    keyValue_ = true;
//                    iterable_ = keys_;
//                    if (iterable_ == null) {
//                        throw new NullObjectException(_conf.joinPages());
//                    }
//                }
                keyValue_ = true;
                iterable_ = keys_;
                if (iterable_ == null) {
                    throw new NullObjectException(_conf.joinPages());
                }
//                if (listMethod_ == null) {
//                    iterable_ = orderedList(keys_);
//                } else {
//                    _ip.setProcessingAttribute(ATTRIBUTE_ORDERED_KEYS);
//                    _ip.setLookForAttrValue(true);
//                    _ip.setOffset(0);
//                    iterable_ = (Iterable<?>) improvedExtractObject(_conf, _ip.getLocalVars(), varsLoop_, listMethod_+NO_PARAM_METHOD);
//                }
            }
            if (listMethod_ == null) {
                listMethod_ = NULL_METHOD;
            }
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
//                    if (copyFrom_ > toValue_ && eq_) {
//                        break;
//                    }
                    if (copyFrom_ > toValue_) {
                        //eq_
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
//                    if (copyFrom_ < toValue_ && eq_) {
//                        break;
//                    }
                    if (copyFrom_ < toValue_) {
                        //eq_
                        break;
                    }
                    nbMaxIterations_++;
                    copyFrom_ += stepValue_;
                }
            }
        }
        Iterator<?> it_ = null;
        ResultsIterator res_ = new ResultsIterator();
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        if (iterationNb_) {
            length_ = nbMaxIterations_;
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
                res_.setFinished(true);
//                res_.setNextNode(getNextNodeWrite(_currentForNode, false));
//                return res_;
            }
        } else if (iterable_.getClass().isArray()) {
            length_ = Array.getLength(iterable_);
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
                res_.setFinished(true);
//                res_.setNextNode(getNextNodeWrite(_currentForNode, false));
//                return res_;
            }
        } else {
            if (keyValue_) {
                it_ = ExtractObject.iterator(_conf, mapCast_.entryList());
            } else {
                it_ = ExtractObject.iterator(_conf, (Iterable<?>) iterable_);
            }
//            it_ = ExtractObject.iterator(_conf, (Iterable<?>) iterable_);
            if (!ExtractObject.hasNext(_conf, it_)) {
                finished_ = true;
                res_.setFinished(true);
//                res_.setNextNode(getNextNodeWrite(_currentForNode, false));
//                return res_;
            }
        }
        if (currentForNode_.getFirstChild() == null) {
            finished_ = true;
        }
        LoopHtmlStack l_ = new LoopHtmlStack();
        l_.setFinished(finished_);
        l_.setReadNode(currentForNode_);
        l_.setWriteNode(written_);
        l_.setIterator(it_, length_);
        l_.setKeyValue(keyValue_);
//        stacks_.add(l_);
        _ip.addBlock(l_);
        if (finished_) {
            return;
        }
        Object int_;
        if (iterationNb_) {
            int_ = realFromValue_;
        } else if (iterable_.getClass().isArray()) {
            int_ = Array.get(iterable_, CustList.FIRST_INDEX);
        } else {
            int_ = ExtractObject.next(_conf, it_);
        }
        String indexClassName_;
        indexClassName_ = currentForNode_.getAttribute(ATTRIBUTE_INDEX_CLASS_NAME);
        ExtractObject.checkClassNotEmptyName(_conf, 0, indexClassName_);
//        try {
//            if (!indexClassName_.isEmpty()) {
//                ConstClasses.classForName(indexClassName_);
//            }
//        } catch (RuntimeClassNotFoundException _0) {
//            throw new RuntimeClassNotFoundException(indexClassName_+RETURN_LINE+_conf.joinPages());
//        }
        String className_;
        if (iterationNb_) {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(ATTRIBUTE_CLASS_NAME);
            if (className_.isEmpty()) {
                className_ = long.class.getName();
            }
            Class<?> cl_ = ExtractObject.classForName(_conf, 0, className_);
            lv_.setClassName(className_);
            lv_.setIndexClassName(indexClassName_);
            lv_.setElement(PrimitiveTypeUtil.convert(cl_, int_));
            lv_.setStep(stepValue_);
            lv_.setExtendedExpression(EMPTY_STRING);
            varsLoop_.put(var_, lv_);
        } else if (currentForNode_.hasAttribute(ATTRIBUTE_LIST)) {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(ATTRIBUTE_CLASS_NAME);
            ExtractObject.checkClassNotEmptyName(_conf, 0, className_);
//            try {
//                if (!className_.isEmpty()) {
//                    ConstClasses.classForName(className_);
//                }
//            } catch (RuntimeClassNotFoundException _0) {
//                throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
//            }
            lv_.setClassName(className_);
            lv_.setIndexClassName(indexClassName_);
            lv_.setElement(int_);
            //lv_.setBaseExpression(calcultateBase(base_, varsLoc_, varsLoop_));
//            lv_.setList(iterable_);
            if (iterable_.getClass().isArray()) {
                lv_.setArray(iterable_);
            } else {
                lv_.setList(ExtractObject.castListable(_conf, 0, iterable_));
            }
            lv_.setExtendedExpression(EMPTY_STRING);
            varsLoop_.put(var_, lv_);
        } else {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(KEY_CLASS_NAME_ATTRIBUTE);
            ExtractObject.checkClassNotEmptyName(_conf, 0, className_);
//            try {
//                if (!className_.isEmpty()) {
//                    ConstClasses.classForName(className_);
//                }
//            } catch (RuntimeClassNotFoundException _0) {
//                throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
//            }
            lv_.setClassName(className_);
            lv_.setIndexClassName(indexClassName_);
            if (keyValue_) {
                lv_.setElement(ExtractObject.castEntryCust(_conf, 0, int_).getKey());
            } else {
                lv_.setElement(int_);
            }
            //lv_.setBaseExpression(calcultateBase(base_, varsLoc_, varsLoop_));
            lv_.setMap(mapCast_);
            lv_.setExtendedExpression(listMethod_+GET_KEY);
            varsLoop_.put(key_, lv_);
            lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(VAR_CLASS_NAME_ATTRIBUTE);
            ExtractObject.checkClassNotEmptyName(_conf, 0, className_);
//            try {
//                if (!className_.isEmpty()) {
//                    ConstClasses.classForName(className_);
//                }
//            } catch (RuntimeClassNotFoundException _0) {
//                throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages());
//            }
            lv_.setClassName(className_);
            lv_.setIndexClassName(indexClassName_);
//            lv_.setElement(mapCast_.get(int_));
            lv_.setElement(ExtractObject.castEntryCust(_conf, 0, int_).getValue());
//            if (keyValue_) {
//                lv_.setElement(ExtractObject.castEntryCust(_conf, 0, int_).getValue());
//            } else {
//                lv_.setElement(ExtractObject.getValue(_conf, 0, mapCast_, int_));
//            }
            //lv_.setBaseExpression(calcultateBase(base_, varsLoc_, varsLoop_));
            lv_.setMap(mapCast_);
            lv_.setExtendedExpression(listMethod_+GET_VALUE);
            varsLoop_.put(value_, lv_);
        }
//        res_.setNextNode(getNextNode(_currentForNode, true));
//        return res_;
    }

//    private static String calcultateBase(String _base, Map<String,LocalVariable> _locVars, Map<String,LoopVariable> _vars) {
//        if (_base.endsWith(GET_INDEX)) {
//            return _base;
//        } else if (_base.endsWith(GET_ATTRIBUTE)) {
//            String oldVar_ = _base.substring(CustList.FIRST_INDEX, _base.length() - GET_ATTRIBUTE.length());
//            return getCurrentExpression(_vars.getVal(oldVar_));
//        } else if (_base.contains(GET_LOC_VAR)) {
//            if (_base.endsWith(GET_LOC_VAR)) {
//                String oldVar_ = _base.substring(CustList.FIRST_INDEX, _base.length() - GET_LOC_VAR.length());
//                return _locVars.getVal(oldVar_).getExpression();
//            } else {
////                String oldVar_ = _base.substring(CustList.FIRST_INDEX, _base.indexOf(GET_ATTRIBUTE));
////                String base_ = _locVars.getVal(oldVar_).getExpression();
////                String command_ = _base.substring(_base.indexOf(GET_ATTRIBUTE) + 1);
////                return base_ + DOT + command_;
//                return _base;
//            }
//        } else if (_base.contains(GET_ATTRIBUTE)) {
//            if (!_base.endsWith(GET_ATTRIBUTE)) {
//                return _base;
//            }
//            String oldVar_ = _base.substring(CustList.FIRST_INDEX, _base.indexOf(GET_ATTRIBUTE));
//            String base_ = getCurrentExpression(_vars.getVal(oldVar_));
//            String command_ = _base.substring(_base.indexOf(GET_ATTRIBUTE) + 1);
//            return base_ + DOT + command_;
//        } else {
//            return _base;
//        }
//    }
//    private static String getCurrentExpression(LoopVariable _lv) {
//        return _lv.getBaseExpression()+LEFT_GET_ARR+_lv.getIndex()+RIGHT_GET_ARR+_lv.getExtendedExpression();
//    }
//    static String formatIndexVariables(String _pattern, Map<String,LocalVariable> _locVars, Map<String,LoopVariable> _vars) {
//        Map<String,String> fields_ = new Map<String,String>();
//        //      for (String k: tokens_) {}
////        Bean bean_ = (Bean) _vars.getVal(EMPTY_STRING).getElement();
//        for (EntryCust<String, LoopVariable> v: _vars.entryList()) {
//            if (v.getKey().isEmpty()) {
//                continue;
//            }
//            Object o_ = v.getValue().getElement();
//            fields_.put(LEFT_EL+v.getKey()+GET_ATTRIBUTE+RIGHT_EL, String.valueOf(o_));
//            long index_ = v.getValue().getIndex();
//            fields_.put(LEFT_EL+v.getKey()+GET_INDEX+RIGHT_EL, String.valueOf(index_));
//        }
//        for (EntryCust<String, LocalVariable> v: _locVars.entryList()) {
//            Object o_ = v.getValue().getElement();
//            fields_.put(LEFT_EL+v.getKey()+GET_LOC_VAR+RIGHT_EL, String.valueOf(o_));
//        }
////        for (String k: StringList.getFields(_pattern)) {
////            if (!k.contains(TR)) {
////                Object o_ = improvedExtractObject(_vars, k);
////                fields_.put(LEFT_EL+k+RIGHT_EL, encodeXml(o_));
////            } else {
////                StringList infos_ = StringList.splitStrings(k, TR);
////                Object o_ = improvedExtractObject(_vars, StringList.replaceEnd(infos_.first(),DOT));
////                o_ = _conf.getTranslators().getVal(infos_.last()).getString(_pattern, _conf, _files, bean_, o_);
////                fields_.put(LEFT_EL+k+RIGHT_EL, encodeXml(o_));
////            }
////        }
////        return StringList.formatQuote(_pattern, fields_);
//        return StringList.formatBasic(_pattern, fields_, false);
//    }

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
//        CustList<BlockStack> l_ = ip_.getBlockStacks();
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
//        BlockStack bl_ = ip_.getBlockStacks().last();
//        Element b_ = bl_.getReadNode();
//        if (bl_ instanceof TryHtmlStack) {
//            TryHtmlStack t_ = (TryHtmlStack)bl_;
//            int vis_ = t_.getVisitedCatch();
//            if (vis_ > CustList.INDEX_NOT_FOUND_ELT) {
//                b_ = t_.getCatchNodes().get(vis_);
//            }
//        } else if (bl_ instanceof IfHtmlStack) {
//            IfHtmlStack t_ = (IfHtmlStack)bl_;
//            b_ = t_.getCurentVisitedNode();
//        }
        if (b_ == n_) {
            //n_ != null => b_ != null
            return new ParentElement(b_);
        }
        Node next_ = n_.getNextSibling();
//        String prefix_ = _conf.getLastPage().getPrefix();
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
//        CustList<BlockStack> stacks_ = _ip.getBlockStacks();
//        ReadWrite ret_;
        LoopHtmlStack l_ = (LoopHtmlStack) _ip.getLastStack();
//        int index_ = stacks_.size() - 1;
//        while (index_ >= CustList.FIRST_INDEX) {
//            if (stacks_.get(index_) instanceof LoopHtmlStack) {
//                l_ = (LoopHtmlStack) stacks_.get(index_);
//                break;
//            }
//            index_--;
//        }
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
//            return hasNext(_conf, l_.getIterator());
            return l_.hasNext(_conf.toContextEl());
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

//    static CustList<NodeAction> getDeepChildNodesDocOrderSkipImports(Node _root) {
//        CustList<NodeAction> nodes_ = new CustList<NodeAction>();
//        if (_root == null) {
//            return nodes_;
//        }
//        ActionNext action_ = ActionNext.NONE;
//        NodeAction na_ = new NodeAction();
//        na_.getActions().add(action_);
//        na_.setNode(_root);
//        Node en_ = _root;
//        while (true) {
//            nodes_.add(na_);
//            na_ = new NodeAction();
//            Node n_ = en_.getFirstChild();
//            if (n_ != null && !StringList.quickEq(en_.getNodeName(),IMPORT_BLOCK_TAG)) {
//                en_ = n_;
//                na_.getActions().add(ActionNext.FIRST_CHILD);
//                na_.setNode(n_);
//                continue;
//            }
//            if (en_ == _root) {
//                break;
//            }
//            n_ = en_.getNextSibling();
//            if (n_ != null) {
//                en_ = n_;
//                na_.getActions().add(ActionNext.NEXT_SIBLING);
//                na_.setNode(n_);
//                continue;
//            }
//            n_ = en_.getParentNode();
//            if (n_ == _root) {
//                break;
//            }
//            na_.getActions().add(ActionNext.PARENT_NODE);
//            Node next_ = n_.getNextSibling();
//            if (next_ != null) {
//                na_.getActions().add(ActionNext.NEXT_SIBLING);
//            }
//            while (next_ == null) {
//                Node par_ = n_.getParentNode();
//                if (par_ == _root) {
//                    break;
//                }
//                na_.getActions().add(ActionNext.PARENT_NODE);
//                next_ = par_.getNextSibling();
//                if (next_ != null) {
//                    na_.getActions().add(ActionNext.NEXT_SIBLING);
//                }
//                n_ = par_;
//            }
//            if (next_ == null) {
//                break;
//            }
//            en_ = next_;
//            na_.setNode(next_);
//        }
//        return nodes_;
//    }

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
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    private static int getTabWidth(Configuration _conf) {
        try {
            return _conf.getTabWidth();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    private static void beforeDisplaying(Bean _bean, Configuration _conf) {
        try {
            if (_bean != null) {
//                ImportingPage ip_ = _conf.getLastPage();
//                String tmp_ = FormatHtml.TMP_VAR;
//                int i_ = CustList.FIRST_INDEX;
//                while (ip_.getLocalVars().contains(tmp_+i_)) {
//                    i_++;
//                }
//                Struct srtOne_ = new Struct(_bean);
//                LocalVariable lvOne_ = new LocalVariable();
//                lvOne_.setClassName(srtOne_.getClassName());
//                lvOne_.setStruct(srtOne_);
//                ip_.getLocalVars().put(tmp_+i_, lvOne_);
//                String nameOne_ = tmp_+i_;
//                ElUtil.processEl(nameOne_+GET_LOC_VAR+"beforeDisplaying()", 0, _conf.toContextEl());
                _bean.beforeDisplaying();
            }
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
}
