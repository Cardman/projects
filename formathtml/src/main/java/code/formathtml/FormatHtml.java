package code.formathtml;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.BadVariableName;
import code.expressionlanguage.errors.custom.DuplicateVariable;
import code.expressionlanguage.errors.custom.UnknownClassName;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.CharStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exec.ExecInvokingOperation;
import code.formathtml.util.ActionNext;
import code.formathtml.util.BadElRender;
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
import code.sml.Attr;
import code.sml.AttributePart;
import code.sml.CharacterData;
import code.sml.Comment;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.sml.Element;
import code.sml.ElementList;
import code.sml.FullDocument;
import code.sml.NamedNodeMap;
import code.sml.Node;
import code.sml.NodeList;
import code.sml.Text;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;

public final class FormatHtml {

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

    static final String TMP_VAR = "tmpvar";
    private static final String COMMENT = "!";
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
    private static final String GET_ATTRIBUTE = ";";
    private static final String CALL_METHOD = "$";
    private static final String DEFAULT_ATTRIBUTE = "default";
    private static final String NO_PARAM_METHOD = "()";
    private static final String ATTRIBUTE_VALUE_SUBMIT = "message";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";

    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_PREPARE_BEAN = "prepare";
    private static final String ATTRIBUTE_FORM = "form";
    private static final String PAGE_ATTRIBUTE = "page";
    private static final String KEEPFIELD_ATTRIBUTE = "keepfields";
    private static final String EXPRESSION_ATTRIBUTE = "expression";
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
    private static final char ESCAPED = '\\';
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private static final String NUMBER_FORM = "n-f";
    private static final String NUMBER_ANCHOR = "n-a";
    private static final String NUMBER_INPUT = "n-i";
    private static final String INTERPRET = "interpret";

    private static final char END_ESCAPED = ';';

    private static final char ENCODED = '&';
    private static final char EQUALS = '=';
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private static final String END_COMMENT = "-->";

    private FormatHtml() {
    }

    private static String addToRoot(Configuration _conf, String _prefix, String _body) {
        return StringList.concat(String.valueOf(LT_BEGIN_TAG),TMP_BLOCK_TAG,String.valueOf(GT_TAG),_body,LT_END_TAG,TMP_BLOCK_TAG,String.valueOf(GT_TAG));
    }

    static String processImports(String _htmlText, Configuration _conf, String _loc, StringMap<String> _files, String... _resourcesFolder) {
        _conf.getHtmlPage().getSelects().clear();
        _conf.clearPages();
        String htmlText_ = processHtmlJava(_htmlText, _conf, _loc, _files, _resourcesFolder);
        if (htmlText_ == null) {
            return null;
        }
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(htmlText_);
        Document doc_ = res_.getDocument();
        if (doc_ == null) {
            _conf.getContext().setException(NullStruct.NULL_VALUE);
            return null;
        }
        _conf.setDocument(doc_);
        _conf.clearPages();
        return DocumentBuilder.toXmlDocument(doc_);
    }

    static BeanElement tryToOpenDocument(String _lg, Element _importElement,
            ImportingPage _ip,
            Configuration _conf, StringMap<String> _files, String... _resourcesFolder) {
        String pageName_ = _importElement.getAttribute(PAGE_ATTRIBUTE);
        if (pageName_.isEmpty()) {
            return null;
        }
        pageName_ = ExtractObject.formatNumVariables(pageName_, _conf, _ip);
        if (_conf.getContext().getException() != null) {
            return null;
        }
        String subHtml_ = ExtractFromResources.loadPage(_lg, _conf, _files, pageName_,_resourcesFolder);
        if (_conf.getContext().getException() != null) {
            return null;
        }
        DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(subHtml_);
        Document doc_ = res_.getDocument();
        if (doc_ == null) {
            _conf.getContext().setException(NullStruct.NULL_VALUE);
            return null;
        }
        ElementList bodies_ = doc_.getElementsByTagName(TAG_BODY);
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
        b_.setBeanName(doc_.getDocumentElement().getAttribute(StringList.concat(prefix_,BEAN_ATTRIBUTE)));
        return b_;
    }

    private static void setBeanForms(Configuration _conf, Struct _mainBean,
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
        StringMapObject forms_ = (StringMapObject) ExtractObject.getForms(_conf, bean_).getInstance();
        if (_conf.getContext().getException() != null) {
            return;
        }
        StringMapObject formsMap_ = (StringMapObject) ExtractObject.getForms(_conf, _mainBean).getInstance();
        if (_conf.getContext().getException() != null) {
            return;
        }
        if (_keepField) {
            for (Element f_: _node.getChildElements()) {
                if (!StringList.quickEq(f_.getTagName(),StringList.concat(prefix_,FORM_BLOCK_TAG))) {
                    continue;
                }
                String name_ = f_.getAttribute(ATTRIBUTE_FORM);
                forms_.put(name_, formsMap_.getVal(name_));
            }
        } else {
            //add option for copying forms (default copy)
            forms_.putAllMap(formsMap_);
        }
    }

    private static void setFieldsImportBean(Configuration _conf, Node _node,
            String _beanName) {
        Struct bean_ = getBean(_conf, _beanName);
        ImportingPage ip_ = _conf.getLastPage();
        String prefix_ = ip_.getPrefix();
        ContextEl context_ = _conf.getContext();
        LgNames lgNames_ = _conf.getStandards();
        for (Element n: _node.getChildElements()) {
            if (!StringList.quickEq(n.getTagName(),StringList.concat(prefix_,PACKAGE_BLOCK_TAG))) {
                continue;
            }
            String package_ = n.getAttribute(ATTRIBUTE_NAME);
            for (Element nTwo_: n.getChildElements()) {
                if (!StringList.quickEq(nTwo_.getTagName(),StringList.concat(prefix_,CLASS_BLOCK_TAG))) {
                    continue;
                }
                String className_ = nTwo_.getAttribute(ATTRIBUTE_NAME);
                ip_.setProcessingNode(_node);
                ip_.setProcessingAttribute(EMPTY_STRING);
                ip_.setOffset(0);
                ip_.setLookForAttrValue(false);
                String searchedClass_ = StringList.concat(package_,DOT,className_);
                if (bean_ == null || bean_.isNull()) {
                    _conf.getContext().setException(new ErrorStruct(_conf, _conf.getStandards().getAliasNullPe()));
                    return;
                }
                ip_.setProcessingNode(nTwo_);
                ip_.setProcessingAttribute(ATTRIBUTE_NAME);
                ip_.setOffset(0);
                ip_.setLookForAttrValue(true);
                String res_ = _conf.resolveCorrectTypeWithoutErrors(searchedClass_, true);
                if (res_.isEmpty()) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(searchedClass_);
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(un_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return;
                }
                if (!PrimitiveTypeUtil.canBeUseAsArgument(false, res_, lgNames_.getStructClassName(bean_, context_), context_)) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(lgNames_.getStructClassName(bean_, context_));
                    mapping_.setParam(res_);
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(cast_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return;
                }
                for (Element nThree_: nTwo_.getChildElements()) {
                    if (!StringList.quickEq(nThree_.getTagName(),StringList.concat(prefix_,FIELD_BLOCK_TAG))) {
                        continue;
                    }
                    if (nThree_.hasAttribute(ATTRIBUTE_PREPARE_BEAN)) {
                        ip_.setInternGlobal(bean_);
                        ip_.setProcessingNode(nThree_);
                        ip_.setProcessingAttribute(ATTRIBUTE_PREPARE_BEAN);
                        ip_.setOffset(0);
                        ip_.setLookForAttrValue(true);
                        String el_ = nThree_.getAttribute(ATTRIBUTE_PREPARE_BEAN);
                        ElRenderUtil.processEl(el_, 0, _conf);
                        ip_.setInternGlobal(null);
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private static StringMap<LocalVariable> newParametersBeforeRender(Configuration _conf, Node _node) {
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LocalVariable> parameters_ = new StringMap<LocalVariable>();
        String prefix_ = ip_.getPrefix();
        for (Element n: _node.getChildElements()) {
            if (!StringList.quickEq(n.getTagName(),StringList.concat(prefix_,PARAM_BLOCK_TAG))) {
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
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(_conf.getCurrentFileName());
                b_.setIndexFile(_conf.getCurrentLocationIndex());
                b_.setVarName(var_);
                _conf.getClasses().getErrorsDet().add(b_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return parameters_;
            }
            VariableInformation vi_ = tryToGetVariableInformation(_conf, ip_, n);
            if (_conf.getContext().getException() != null) {
                return parameters_;
            }
            String className_ = vi_.getClassName();

            Struct obj_ = vi_.getStruct();
            parameters_.put(var_, tryToCreateVariable(_conf, ip_, className_, obj_));
            if (_conf.getContext().getException() != null) {
                return parameters_;
            }
        }
        return parameters_;
    }
    private static StringMap<LocalVariable> newReturnedValues(Configuration _conf, Node _node) {
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LocalVariable> parameters_ = new StringMap<LocalVariable>();
        String prefix_ = ip_.getPrefix();
        for (Element n: _node.getChildElements()) {
            if (!StringList.quickEq(n.getTagName(),StringList.concat(prefix_,SET_BLOCK_TAG))) {
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
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(_conf.getCurrentFileName());
                b_.setIndexFile(_conf.getCurrentLocationIndex());
                b_.setVarName(var_);
                _conf.getClasses().getErrorsDet().add(b_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return parameters_;
            }
            VariableInformation vi_ = tryToGetVoidVariable(_conf, ip_, n);
            String className_ = vi_.getClassName();

            LocalVariable loc_ = new LocalVariable();
            loc_.setClassName(className_);
            parameters_.put(var_, loc_);
        }
        return parameters_;
    }

    static String processHtmlJava(String _htmlText, Configuration _conf, String _loc, StringMap<String> _files, String... _resourcesFolder) {
        String htmlText_ = _htmlText;
        String beanName_ = null;
        Struct bean_ = null;
        Document docOrig_ = _conf.getDocument();
        Element root_ = docOrig_.getDocumentElement();
        beanName_ = root_.getAttribute(StringList.concat(_conf.getPrefix(),BEAN_ATTRIBUTE));
        bean_ = getBean(_conf, beanName_);
        ExtractObject.beforeDiplaying(_conf, bean_, true);
        if (_conf.getContext().getException() != null) {
            return null;
        }
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
            int indexEndTag_ = htmlText_.indexOf(StringList.concat(String.valueOf(LT_BEGIN_TAG),prefix_,TR_END_BLOCK_TAG,RIGHT_END_TAG), indexLoc_);
            int indexBeginTag_ = htmlText_.indexOf(StringList.concat(String.valueOf(LT_BEGIN_TAG),prefix_,TR_BEGIN_BLOCK_TAG), indexLoc_);
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
                indexLoc_ = indexEndTag_ +prefix_.length()+ TR_END_BLOCK_TAG.length() + RIGHT_END_TAG.length() + 1;
                continue;
            }
            String begin_ = htmlText_.substring(indexMin_);
            String nameTag_ = StringList.concat(String.valueOf(LT_BEGIN_TAG),prefix_,TR_BEGIN_BLOCK_TAG);
            if (begin_.startsWith(StringList.concat(nameTag_,RIGHT_END_TAG))) {
                str_.append(TR_BEGIN);
                indexLoc_ = indexBeginTag_ + nameTag_.length()+RIGHT_END_TAG.length();
                continue;
            }
            String beginTag_ = begin_.substring(nameTag_.length(), begin_.indexOf(RIGHT_END_TAG));
            str_.append(LT_BEGIN_TAG);
            str_.append(TR_TAG);
            str_.append(beginTag_);
            str_.append(GT_TAG);
            indexLoc_ = indexBeginTag_ + nameTag_.length() + beginTag_.length() + RIGHT_END_TAG.length();
        }
        return str_.toString();
    }

    static String processHtml(Document _docOrig, String _beanName, Configuration _conf, String _loc, StringMap<String> _files, String... _resourcesFolder) {
        Struct bean_ = getBean(_conf, _beanName);
        Struct mainBean_ = bean_;

        ImportingPage ip_ = new ImportingPage(false);
        int tabWidth_ = getTabWidth(_conf);
        ip_.setTabWidth(tabWidth_);
        ip_.setHtml(_conf.getHtml());
        ip_.setReadUrl(_conf.getCurrentUrl());
        ip_.setBeanName(_beanName);
        ip_.setPrefix(_conf.getPrefix());
        Element r_ = _docOrig.getDocumentElement();
        if (bean_ != null && !bean_.isNull()) {
            ip_.setGlobalArgumentStruct(bean_, _conf);
        }
        _conf.addPage(ip_);
        checkSyntax(_conf, _docOrig, ip_.getHtml());
        if (_conf.getContext().getException() != null) {
            return null;
        }
        Node en_ = r_;
        FullDocument doc_ = DocumentBuilder.newXmlDocument(tabWidth_);
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
                    for (EntryCust<String, LocalVariable> e: last_.getReturnedValues().entryList()) {
                        ip_.putLocalVar(e.getKey(), e.getValue());
                    }
                    processBlock(_conf, ip_);
                    if (_conf.getContext().getException() != null) {
                        throwException(_conf);
                        if (_conf.getContext().getException() != null) {
                            return null;
                        }
                    }
                    continue;
                }
                rw_ = ip_.getReadWrite();
                en_ = rw_.getRead();
                ip_.setProcessingNode(en_);
                ip_.setProcessingAttribute(EMPTY_STRING);
                ip_.setOffset(0);
                currentNode_ = rw_.getWrite();
                if (en_ instanceof Comment) {
                    processElementOrText(_conf, ip_, true);
                    if (_conf.getContext().getException() != null) {
                        throwException(_conf);
                        if (_conf.getContext().getException() != null) {
                            return null;
                        }
                    }
                    continue;
                }
                if (en_ instanceof Element) {
                    if (((Element) en_).getTagName().startsWith(ip_.getPrefix()) && !ip_.getPrefix().isEmpty()) {
                        if (StringList.quickEq(((Element) en_).getTagName(),StringList.concat(ip_.getPrefix(),EXIT_TAG))) {
                            _conf.clearPages();
                            break;
                        }
                        ImportingPage ret_ = processProcessingTags(_loc,_conf, doc_, ip_, containersMap_, containers_, indexes_, currentForm_, mainBean_, _loc, _files, _resourcesFolder);
                        if (_conf.getContext().getException() != null) {
                            throwException(_conf);
                            if (_conf.getContext().getException() != null) {
                                return null;
                            }
                            continue;
                        }
                        if (ret_ != null) {
                            ip_ = ret_;
                            continue;
                        }
                    }
                    appendChild(doc_, _conf, currentNode_, (Element) en_);
                    Element tag_ = (Element) currentNode_.getLastChild();
                    if (StringList.quickEq(((Element) en_).getTagName(),TAG_FORM)) {
                        curForm_ = tag_;
                        containersMap_.put(currentForm_, containers_);
                        containers_ = new NatTreeMap<Long, NodeContainer>();
                        currentForm_ ++;
                        indexes_.setInput(0);
                    }
                    processAttributes(_conf, _loc, _files, ip_, doc_, tag_,
                            indexes_, containersMap_, containers_, _resourcesFolder);
                    if (_conf.getContext().getException() != null) {
                        throwException(_conf);
                        if (_conf.getContext().getException() != null) {
                            return null;
                        }
                        continue;
                    }
                    if (StringList.quickEq(((Element) en_).getTagName(),TAG_FORM)) {
                        curForm_.setAttribute(NUMBER_FORM, String.valueOf(currentForm_ - 1));
                    }
                    if (StringList.quickEq(tag_.getTagName(), TAG_A) && (tag_.hasAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND))|| !tag_.getAttribute(ATTRIBUTE_HREF).isEmpty() )) {
                        currentAnchor_ = indexes_.getAnchor();
                        tag_.setAttribute(NUMBER_ANCHOR, String.valueOf(currentAnchor_));
                        currentAnchor_++;
                        indexes_.setAnchor(currentAnchor_);
                    }
                    processElementOrText(_conf, ip_, true);
                    if (_conf.getContext().getException() != null) {
                        throwException(_conf);
                        if (_conf.getContext().getException() != null) {
                            return null;
                        }
                    }
                    continue;
                }
                String content_ = en_.getTextContent();
                if (content_.trim().isEmpty()) {
                    Text t_ = doc_.createTextNode(content_);
                    currentNode_.appendChild(t_);
                    processElementOrText(_conf, ip_, true);
                    if (_conf.getContext().getException() != null) {
                        throwException(_conf);
                        if (_conf.getContext().getException() != null) {
                            return null;
                        }
                    }
                    continue;
                }
                if (interpretBrackets((CharacterData) en_)) {
                    content_ = ExtractObject.formatNumVariables(content_, _conf, ip_);
                    if (_conf.getContext().getException() != null) {
                        throwException(_conf);
                        if (_conf.getContext().getException() != null) {
                            return null;
                        }
                        continue;
                    }
                }
                Text t_ = doc_.createTextNode(content_);
                currentNode_.appendChild(t_);
                processElementOrText(_conf, ip_, true);
                if (_conf.getContext().getException() != null) {
                    throwException(_conf);
                    if (_conf.getContext().getException() != null) {
                        return null;
                    }
                }
            } catch (OutOfMemoryError _0){
                ContextEl cont_ = _conf.getContext();
                cont_.setException(cont_.getMemoryError());
                throwException(_conf);
                if (_conf.getContext().getException() != null) {
                    return null;
                }
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
        doc_.getDocumentElement().removeAttribute(StringList.concat(_conf.getPrefix(),BEAN_ATTRIBUTE));
        return DocumentBuilder.toXml(doc_);
    }

    static void throwException(Configuration _conf) {
        Element catchElt_ = null;
        ContextEl context_ = _conf.getContext();
        LgNames lgNames_ = _conf.getStandards();
        Struct custCause_ = context_.getException();
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
                if (!StringList.quickEq(try_.getCatchNodes().last().getTagName(),StringList.concat(prefix_,TAG_FINALLY))) {
                    addFinallyClause_ = false;
                }
                if (try_.getVisitedCatch() >= CustList.FIRST_INDEX) {
                    if (!StringList.quickEq(try_.getCurrentCatchNode().getTagName(),StringList.concat(prefix_,TAG_FINALLY))) {
                        if (addFinallyClause_) {
                            try_.setException(custCause_);
                            try_.setFinallyNode(try_.getCatchNodes().last());
                            context_.setException(null);
                            Element newCurrentNode_ = try_.getWriteNode();
                            bkIp_.getReadWrite().setRead(try_.getCatchNodes().last());
                            bkIp_.getReadWrite().setWrite(newCurrentNode_);
                            return;
                        }
                    }
                    bkIp_.removeLastBlock();
                    continue;
                }
                //process try block
                int i_ = 0;
                for (Element e: try_.getCatchNodes()) {
                    if (StringList.quickEq(e.getTagName(),StringList.concat(prefix_,TAG_FINALLY))) {
                        break;
                    }
                    String name_ = e.getAttribute(ATTRIBUTE_CLASS_NAME);
                    Mapping mapping_ = new Mapping();
                    String excepClass_ = lgNames_.getStructClassName(custCause_, context_);
                    if (excepClass_ == null) {
                        catchElt_ = e;
                        try_.setVisitedCatch(i_);
                        break;
                    }
                    mapping_.setArg(excepClass_);
                    mapping_.setParam(name_);
                    if (Templates.isCorrect(mapping_, context_)) {
                        catchElt_ = e;
                        try_.setVisitedCatch(i_);
                        break;
                    }
                    i_++;
                }
                if (catchElt_ != null) {
                    Element newCurrentNode_ = bkIp_.getLastStack().getWriteNode();
                    Element catchElement_ = catchElt_;
                    try_.setFinallyNode(null);
                    context_.setException(null);
                    if (catchElement_.getFirstChild() != null) {
                        String var_ = catchElement_.getAttribute(ATTRIBUTE_VAR);
                        LocalVariable lv_ = new LocalVariable();
                        lv_.setStruct(custCause_);
                        lv_.setClassName(catchElement_.getAttribute(ATTRIBUTE_CLASS_NAME));
                        bkIp_.getCatchVars().put(var_, lv_);
                        bkIp_.getReadWrite().setRead(catchElement_.getFirstChild());
                        bkIp_.getReadWrite().setWrite(newCurrentNode_);
                        return;
                    }
                    bkIp_.getReadWrite().setRead(catchElement_);
                    bkIp_.getReadWrite().setWrite(newCurrentNode_);
                    return;
                }
                if (addFinallyClause_) {
                    try_.setFinallyNode(try_.getCatchNodes().last());
                    context_.setException(null);
                    try_.setException(custCause_);
                    Element newCurrentNode_ = try_.getWriteNode();
                    bkIp_.getReadWrite().setRead(try_.getCatchNodes().last());
                    bkIp_.getReadWrite().setWrite(newCurrentNode_);
                    return;
                }
                bkIp_.removeLastBlock();
            }
            _conf.removeLastPage();
        }
    }

    private static ImportingPage processProcessingTags(String _lg, Configuration _conf, Document _doc,
            ImportingPage _ip,
            NumberMap<Long,NatTreeMap<Long,NodeContainer>> _containersMap,
            NatTreeMap<Long,NodeContainer> _containers,
            IndexesFormInput _indexes,
            long _currentForm,
            Struct _mainBean,
            String _loc, StringMap<String> _files, String... _resourcesFolder) {
        NumberMap<Long,NatTreeMap<Long,NodeContainer>> containersMap_ = _containersMap;
        String prefix_ = _conf.getLastPage().getPrefix();
        NatTreeMap<Long,NodeContainer> containers_ = _containers;
        ReadWriteHtml rw_ = _ip.getReadWrite();
        IndexesFormInput indexes_ = _indexes;
        long currentAnchor_ = _indexes.getAnchor();
        Document doc_ = _doc;
        Element en_ = (Element) rw_.getRead();
        ImportingPage ip_ = _ip;
        Node currentNode_ = rw_.getWrite();
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,SET_BLOCK_TAG))) {
            Element set_ = en_;
            processSetTag(_conf, ip_, set_);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            processBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,PARAM_BLOCK_TAG))) {
            Element set_ = en_;
            processSetClassNameParamTag(_conf, _ip, set_);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            processBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,SET_RETURNED_VALUE_BLOCK_TAG))) {
            Element set_ = en_;
            processSetReturnValueTag(_conf, ip_, set_);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            processBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,UNSET_BLOCK_TAG))) {
            Element set_ = en_;
            String var_ = set_.getAttribute(ATTRIBUTE_VAR);
            ip_.removeLocalVar(var_);
            processBlock(_conf, _ip);
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,CONTINUE_TAG))) {
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
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,BREAK_TAG))) {
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
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,RETURN_TAG))) {
            ip_.setReturning(true);
            return removeBlockFinally(_conf, ip_);
        }

        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,CATCH_TAG))) {
            TryHtmlStack ts_ = (TryHtmlStack) ip_.getLastStack();
            ts_.setFinallyNode(null);
            if (ts_.getLastCatchNode() == en_) {
                ip_.removeLastBlock();
                processBlock(_conf, ip_);
            } else {
                rw_.setRead(en_.getNextSibling());
            }
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,TAG_FINALLY))) {
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
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,THROW_TAG))) {
            String el_ = en_.getAttribute(EXPRESSION_ATTRIBUTE);
            Argument arg_ = ElRenderUtil.processEl(el_, 0, _conf);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            Struct o_ = arg_.getStruct();
            _conf.getContext().setException(o_);
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,TRY_TAG))) {
            rw_.setRead(en_.getFirstChild());
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
                if (!StringList.quickEq(((Element) n_).getTagName(),StringList.concat(prefix_,CATCH_TAG))) {
                    if (!StringList.quickEq(((Element) n_).getTagName(),StringList.concat(prefix_,TAG_FINALLY))) {
                        break;
                    }
                }
                tryStack_.getCatchNodes().add((Element) n_);
                n_ = n_.getNextSibling();
            }
            tryStack_.setWriteNode((Element) currentNode_);
            tryStack_.setReadNode(en_);
            ip_.addBlock(tryStack_);
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,ELSE_BLOCK_TAG))) {
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
                boolean assert_ = ExtractCondition.evaluateGenericCondition(en_, _conf, ip_);
                if (_conf.getContext().getException() != null) {
                    return ip_;
                }
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
                BlockHtml bl_ = ip_.getLastStack();
                if (bl_.getReadNode() == en_) {
                    ip_.removeLastBlock();
                    processBlock(_conf, ip_);
                    return ip_;
                }
            }
            IfHtmlStack if_ = new IfHtmlStack();
            if_.getNodes().add(en_);
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
                if (!StringList.quickEq(((Element) n_).getTagName(),StringList.concat(prefix_,ELSE_BLOCK_TAG))) {
                    if (!ExtractCondition.isContentOfConditionNode(_conf, n_)) {
                        break;
                    }
                }
                if_.getNodes().add((Element) n_);
                n_ = n_.getNextSibling();
            }
            if_.setReadNode(en_);
            if_.setWriteNode((Element) currentNode_);
            if_.setVisitedBlock(CustList.FIRST_INDEX);
            boolean assert_ = ExtractCondition.evaluateGenericCondition(en_, _conf, ip_);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
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
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,TAG_SWITCH))) {
            if (!ip_.noBlock()) {
                BlockHtml bl_ = ip_.getLastStack();
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
            if_.setReadNode(en_);
            if_.setWriteNode((Element) currentNode_);
            ip_.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
            ip_.setOffset(0);
            ip_.setLookForAttrValue(true);
            String expr_ = en_.getAttribute(EXPRESSION_ATTRIBUTE);
            Argument arg_ = ElRenderUtil.processEl(expr_, 0, _conf);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
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
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,TAG_CASE))) {
            SwitchHtmlStack sw_ = (SwitchHtmlStack) ip_.getLastStack();
            Struct value_ = sw_.getStruct();
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
            }
            ip_.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
            ip_.setOffset(0);
            ip_.setLookForAttrValue(true);
            String expr_ = en_.getAttribute(EXPRESSION_ATTRIBUTE);
            Argument arg_ = ElRenderUtil.processEl(expr_, 0, _conf);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            boolean enter_ = false;
            if (value_ == null) {
                if (arg_.getStruct().isNull()) {
                    enter_ = true;
                }
            } else {
                if (ExtractObject.eq(_conf, value_, arg_.getStruct())) {
                    enter_ = true;
                }
                if (_conf.getContext().getException() != null) {
                    return ip_;
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
                    }
                    sw_.setFinished(true);
                    rw_.setRead(sw_.getReadNode());
                    return ip_;
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
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,TAG_DEFAULT))) {
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
            }
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
                }
                sw_.setFinished(true);
                rw_.setRead(sw_.getReadNode());
                return ip_;
            }
            processAfterBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,SELECT_TAG))) {
            String map_ = en_.getAttribute(ATTRIBUTE_MAP);
            String id_ = en_.getAttribute(ATTRIBUTE_ID);
            String groupId_ = en_.getAttribute(ATTRIBUTE_GROUP_ID);
            String class_ = en_.getAttribute(ATTRIBUTE_CLASS);
            boolean multiple_ = en_.hasAttribute(ATTRIBUTE_MULTIPLE);
            //TODO converter
            if (!map_.isEmpty()) {
                processOptionsMap(_conf, doc_,
                        currentNode_, en_, map_, id_, groupId_, multiple_);
            } else {
                processOptionsList(_conf,
                        doc_, currentNode_, en_, id_, groupId_, multiple_);
            }
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            Element selectTag_ = (Element) currentNode_.getLastChild();
            //format class before evaluate it
            if (!class_.isEmpty()) {
                CustList<BlockHtml> stacks_ = ip_.getBlockStacks();
                class_ = format(stacks_, class_, false);
                selectTag_.setAttribute(ATTRIBUTE_CLASS, class_);
                evaluateAttribute(_conf, selectTag_, ATTRIBUTE_CLASS);
            }
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            //format name
            String name_ = selectTag_.getAttribute(ATTRIBUTE_NAME);
            if (!name_.isEmpty()) {
                ip_.setProcessingAttribute(ATTRIBUTE_NAME);
                ip_.setLookForAttrValue(true);
                ip_.setOffset(0);
                setIndexes(indexes_, _conf, ip_, containersMap_, containers_, selectTag_, name_);
                if (_conf.getContext().getException() != null) {
                    return ip_;
                }
                if (indexes_.getNb() >= 0) {
                    FormInputCoords inputs_ = new FormInputCoords();
                    inputs_.setForm(_currentForm - 1);
                    inputs_.setInput(indexes_.getNb());
                    StringList allOptions_ = new StringList();
                    ElementList elts_ = selectTag_.getElementsByTagName(TAG_OPTION);
                    int nbElts_ = elts_.getLength();
                    for (int i = 0; i < nbElts_; i++) {
                        Element opt_ = elts_.item(i);
                        allOptions_.add(opt_.getAttribute(ATTRIBUTE_VALUE));
                    }
                    _conf.getHtmlPage().getSelects().put(inputs_, allOptions_);
                    selectTag_.setAttribute(NUMBER_INPUT, String.valueOf(indexes_.getNb()));
                }
                selectTag_.setAttribute(ATTRIBUTE_NAME, StringList.concat(ip_.getBeanName(),DOT,name_));
            }
            String selectId_ = selectTag_.getAttribute(ATTRIBUTE_ID);
            if (!selectId_.isEmpty()) {
                ip_.setProcessingAttribute(ATTRIBUTE_ID);
                selectId_ = interpretPartiallyIds(_conf, ip_, selectId_);
                selectTag_.setAttribute(ATTRIBUTE_ID, selectId_);
            }
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            String selectGroupId_ = selectTag_.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_GROUP_ID));
            if (!selectGroupId_.isEmpty()) {
                ip_.setProcessingAttribute(ATTRIBUTE_GROUP_ID);
                selectGroupId_ = interpretPartiallyIds(_conf, ip_, selectGroupId_);
                selectTag_.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_GROUP_ID), selectGroupId_);
            }
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            processBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,MESSAGE_BLOCK_TAG))) {
            Element element_ = en_;
            String formatted_ = ExtractObject.formatMessage(_conf, _loc, ip_, element_, _files, _resourcesFolder);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
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
            if (formatted_.endsWith(StringList.concat(LT_END_TAG,TAG_HTML,String.valueOf(GT_TAG))) && formatted_.startsWith(StringList.concat(String.valueOf(LT_BEGIN_TAG),TAG_HTML))) {
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
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(ipMess_.getHtml());
            Document docLoc_ = res_.getDocument();
            if (docLoc_ == null) {
                _conf.getContext().setException(NullStruct.NULL_VALUE);
                return ip_;
            }
            ipMess_.setPrefix(getPrefix(_conf, docLoc_));
            ipMess_.setRoot(docLoc_.getDocumentElement());
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
                    if (_conf.getContext().getException() != null) {
                        return ip_;
                    }
                    if (StringList.quickEq(tag_.getTagName(), TAG_A) && (tag_.hasAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND))|| !tag_.getAttribute(ATTRIBUTE_HREF).isEmpty() )) {
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
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,IMPORT_BLOCK_TAG))) {
            BeanElement newElt_ = tryToOpenDocument(_lg,en_, ip_, _conf, _files, _resourcesFolder);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            if (newElt_ == null) {
                processBlock(_conf, ip_);
                return ip_;
            }
            StringMap<LocalVariable> params_ = newParametersBeforeRender(_conf, en_);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            StringMap<LocalVariable> returnedValues_ = newReturnedValues(_conf, en_);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            boolean keepField_ = en_.hasAttribute(KEEPFIELD_ATTRIBUTE);
            String beanName_ = newElt_.getBeanName();
            setBeanForms(_conf, _mainBean, en_, keepField_,
                    beanName_);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            setFieldsImportBean(_conf, en_,
                    beanName_);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            ip_.setProcessingNode(en_);
            ip_.setProcessingAttribute(EMPTY_STRING);
            ip_.setOffset(0);
            ip_.setLookForAttrValue(false);
            Struct newBean_ = getBean(_conf, beanName_);
            ExtractObject.beforeDiplaying(_conf, newBean_, false);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
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
            if (newBean_ != null && !newBean_.isNull()) {
                newIp_.setGlobalArgumentStruct(newBean_, _conf);
            }
            _conf.addPage(newIp_);
            newIp_.getParameters().putAllMap(params_);
            newIp_.getReturnedValues().putAllMap(returnedValues_);
            checkSyntax(_conf, newElt_.getRoot().getOwnerDocument(), newElt_.getHtml());
            return newIp_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,TAG_DO))) {
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
                    if (_conf.getContext().getException() != null) {
                        return ip_;
                    }
                    continue;
                }
                processBlock(_conf, ip_);
                return ip_;
            }
            processDoWhile(_conf, ip_);
            processAfterBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,WHILE_BLOCK_TAG))) {
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
                while (ExtractCondition.evaluateCondition(en_, _conf, _ip)) {
                    if (_conf.getContext().getException() != null) {
                        return ip_;
                    }
                    continue;
                }
                processBlock(_conf, ip_);
                return ip_;
            }
            processWhile(_conf, ip_);
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            c_ = (LoopHtmlStack) ip_.getLastStack();
            if (c_.isFinished()) {
                ip_.removeLastBlock();
                processBlock(_conf, ip_);
                return ip_;
            }
            processAfterBlock(_conf, ip_);
            return ip_;
        }
        if (StringList.quickEq(en_.getTagName(),StringList.concat(prefix_,FOR_BLOCK_TAG))) {
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
            if (_conf.getContext().getException() != null) {
                return ip_;
            }
            c_ = (LoopHtmlStack) ip_.getLastStack();
            if (c_.isFinished()) {
                ip_.removeLastBlock();
                processBlock(_conf, ip_);
                return ip_;
            }
            processAfterBlock(_conf, ip_);
            return ip_;
        }
        return null;
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
                if (StringList.quickEq(t_.getLastCatchNode().getTagName(),StringList.concat(prefix_,TAG_FINALLY))) {
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
        if (_conf.getContext().getException() != null) {
            return;
        }
        Struct elt_ = tryToGetObject(_conf, _ip, _set);
        if (_conf.getContext().getException() != null) {
            return;
        }
        String className_ = ret_.getClassName();
        checkClass(_conf, _ip, className_, elt_);
        if (_conf.getContext().getException() != null) {
            return;
        }
        ret_.setStruct(elt_);
    }
    private static void processSetClassNameParamTag(Configuration _conf, ImportingPage _ip,
            Element _set) {
        String var_ = _set.getAttribute(ATTRIBUTE_VAR);
        LocalVariable ret_ = ExtractObject.getCurrentLocVariable(_conf, 0, _ip.getParameters(), var_);
        if (_conf.getContext().getException() != null) {
            return;
        }
        String className_ = _set.getAttribute(ATTRIBUTE_CLASS_NAME);
        if (className_.isEmpty()) {
            className_ = _conf.getStandards().getAliasObject();
        }
        _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
        _ip.setLookForAttrValue(true);
        String res_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
        if (res_.isEmpty()) {
            UnknownClassName un_ = new UnknownClassName();
            un_.setClassName(className_);
            un_.setFileName(_conf.getCurrentFileName());
            un_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().getErrorsDet().add(un_);
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            return;
        }
        className_ = res_;
        checkClass(_conf, _ip, className_, ret_.getStruct());
        if (_conf.getContext().getException() != null) {
            return;
        }
        ret_.setClassName(className_);
    }
    private static void processSetTag(Configuration _conf, ImportingPage _ip,
            Element _set) {
        if (!_set.hasAttribute(ATTRIBUTE_VAR)) {
            String expression_ = _set.getAttribute(EXPRESSION_ATTRIBUTE);
            ElRenderUtil.processEl(expression_, 0, _conf);
            return;
        }
        String var_ = _set.getAttribute(ATTRIBUTE_VAR);
        if (!StringList.isWord(var_)) {
            _ip.setOffset(0);
            _ip.setProcessingAttribute(ATTRIBUTE_VAR);
            _ip.setLookForAttrValue(true);
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(_conf.getCurrentFileName());
            b_.setIndexFile(_conf.getCurrentLocationIndex());
            b_.setVarName(var_);
            _conf.getClasses().getErrorsDet().add(b_);
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            return;
        }
        VariableInformation vi_ = tryToGetVariableInformation(_conf, _ip, _set);
        if (_conf.getContext().getException() != null) {
            return;
        }
        String className_ = vi_.getClassName();
        Struct obj_ = vi_.getStruct();
        _ip.putLocalVar(var_, tryToCreateVariable(_conf, _ip, className_, obj_));
    }

    static VariableInformation tryToGetVoidVariable(Configuration _conf, ImportingPage _ip,
            Element _element) {
        String className_ = _element.getAttribute(ATTRIBUTE_CLASS_NAME);
        if (!className_.isEmpty()) {
            _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            String res_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
            if (res_.isEmpty()) {
                UnknownClassName un_ = new UnknownClassName();
                un_.setClassName(className_);
                un_.setFileName(_conf.getCurrentFileName());
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(un_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                className_ = _conf.getStandards().getAliasObject();
            } else {
                className_ = res_;
            }
        } else {
            className_ = _conf.getStandards().getAliasObject();
        }
        VariableInformation vi_ = new VariableInformation();
        vi_.setClassName(className_);
        return vi_;
    }
    static Struct tryToGetObject(Configuration _conf, ImportingPage _ip,
            Element _element) {
        String numExpr_ = _element.getAttribute(NUMBER_EXPRESSION);
        String expression_ = _element.getAttribute(EXPRESSION_ATTRIBUTE);
        if (!numExpr_.isEmpty()) {
            expression_ = numExpr_;
            String evalBool_ = _element.getAttribute(EVALUATE_BOOLEAN);
            boolean eval_ = Boolean.parseBoolean(evalBool_);
            _ip.setProcessingAttribute(NUMBER_EXPRESSION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            return ExtractObject.evaluateMathExpression(_ip, _conf, eval_, numExpr_);
        }
        _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
        _ip.setLookForAttrValue(true);
        _ip.setOffset(0);
        if (_element.hasAttribute(IS_STRING_CONST_ATTRIBUTE)){
            if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                return NullStruct.NULL_VALUE;
            }
            return new StringStruct(expression_);
        } else if (_element.hasAttribute(IS_CHAR_CONST_ATTRIBUTE)){
            if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                return NullStruct.NULL_VALUE;
            }
            return new CharStruct(ExtractObject.getChar(_conf, expression_));
        } else if (_element.hasAttribute(IS_BOOL_CONST_ATTRIBUTE)){
            if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                return NullStruct.NULL_VALUE;
            }
            return new BooleanStruct(Boolean.parseBoolean(expression_));
        } else if (StringList.isNumber(expression_)) {
            String primLong_ = _conf.getStandards().getAliasPrimLong();
            return ExtractObject.instanceByString(_conf, primLong_, expression_);
        } else {
            return ElRenderUtil.processEl(expression_, 0, _conf).getStruct();
        }
    }
    static VariableInformation tryToGetVariableInformation(Configuration _conf, ImportingPage _ip,
            Element _element) {
        String className_ = _element.getAttribute(ATTRIBUTE_CLASS_NAME);
        String numExpr_ = _element.getAttribute(NUMBER_EXPRESSION);
        Struct struct_ = null;
        String expression_ = _element.getAttribute(EXPRESSION_ATTRIBUTE);
        ContextEl context_ = _conf.getContext();
        LgNames lgNames_ = _conf.getStandards();
        if (!numExpr_.isEmpty()) {
            expression_ = numExpr_;
            String evalBool_ = _element.getAttribute(EVALUATE_BOOLEAN);
            boolean eval_ = Boolean.parseBoolean(evalBool_);
            _ip.setProcessingAttribute(NUMBER_EXPRESSION);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            struct_ = ExtractObject.evaluateMathExpression(_ip, _conf, eval_, numExpr_);
            if (_conf.getContext().getException() != null) {
                VariableInformation vi_ = new VariableInformation();
                vi_.setClassName(className_);
                vi_.setStruct(struct_);
                return vi_;
            }
            if (className_.isEmpty()) {
                ExtractObject.checkNullPointer(_conf, struct_.getInstance());
                if (_conf.getContext().getException() != null) {
                    VariableInformation vi_ = new VariableInformation();
                    vi_.setClassName(className_);
                    vi_.setStruct(struct_);
                    return vi_;
                }
                className_ = lgNames_.getStructClassName(struct_, _conf.getContext());
            } else {
                String res_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                if (res_.isEmpty()) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(className_);
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(un_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                }
                className_ = res_;
            }
        } else {
            if (className_.isEmpty()) {
                _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                if (_element.hasAttribute(IS_STRING_CONST_ATTRIBUTE)){
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        struct_ = NullStruct.NULL_VALUE;
                        className_ = _conf.getStandards().getAliasObject();
                    } else {
                        struct_ = new StringStruct(expression_);
                        className_ = lgNames_.getStructClassName(struct_, context_);
                    }
                } else if (_element.hasAttribute(IS_CHAR_CONST_ATTRIBUTE)){
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        struct_ = NullStruct.NULL_VALUE;
                        className_ = _conf.getStandards().getAliasObject();
                    } else {
                        struct_ = new CharStruct(ExtractObject.getChar(_conf, expression_));
                        className_ = lgNames_.getStructClassName(struct_, context_);
                    }
                } else if (_element.hasAttribute(IS_BOOL_CONST_ATTRIBUTE)){
                    if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                        struct_ = NullStruct.NULL_VALUE;
                        className_ = _conf.getStandards().getAliasObject();
                    } else {
                        struct_ = new BooleanStruct(Boolean.parseBoolean(expression_));
                        className_ = lgNames_.getStructClassName(struct_, context_);
                    }
                } else if (StringList.isNumber(expression_)) {
                    className_ = _conf.getStandards().getAliasPrimLong();
                    struct_ = ExtractObject.instanceByString(_conf, className_, expression_);
                } else {
                    className_ = _conf.getStandards().getAliasObject();
                    Argument a_ = ElRenderUtil.processEl(expression_, 0, _conf);
                    struct_ = a_.getStruct();
                }
            } else {
                if (_element.hasAttribute(IS_STRING_CONST_ATTRIBUTE)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    String res_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                    if (!res_.isEmpty()) {
                        className_ = res_;
                        if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                            struct_ = NullStruct.NULL_VALUE;
                        } else {
                            struct_ = new StringStruct(expression_);
                        }
                    } else {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(className_);
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getClasses().getErrorsDet().add(un_);
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_conf.getClasses().getErrorsDet());
                        badEl_.setFileName(_conf.getCurrentFileName());
                        badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                        className_ = _conf.getStandards().getAliasObject();
                    }
                } else if (_element.hasAttribute(IS_CHAR_CONST_ATTRIBUTE)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    String res_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                    if (!res_.isEmpty()) {
                        className_ = res_;
                        if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                            struct_ = NullStruct.NULL_VALUE;
                        } else {
                            struct_ = new CharStruct(ExtractObject.getChar(_conf, expression_));
                        }
                    } else {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(className_);
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getClasses().getErrorsDet().add(un_);
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_conf.getClasses().getErrorsDet());
                        badEl_.setFileName(_conf.getCurrentFileName());
                        badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                        className_ = _conf.getStandards().getAliasObject();
                    }
                } else if (_element.hasAttribute(IS_BOOL_CONST_ATTRIBUTE)){
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    String res_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                    if (!res_.isEmpty()) {
                        className_ = res_;
                        if (!_element.hasAttribute(EXPRESSION_ATTRIBUTE)) {
                            struct_ = NullStruct.NULL_VALUE;
                        } else {
                            struct_ = new BooleanStruct(Boolean.parseBoolean(expression_));
                        }
                    } else {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(className_);
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getClasses().getErrorsDet().add(un_);
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_conf.getClasses().getErrorsDet());
                        badEl_.setFileName(_conf.getCurrentFileName());
                        badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                        className_ = _conf.getStandards().getAliasObject();
                    }
                } else if (StringList.isNumber(expression_)) {
                    _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    String res_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                    if (!res_.isEmpty()) {
                        className_ = res_;
                        _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                        _ip.setLookForAttrValue(true);
                        _ip.setOffset(0);
                        struct_ = ExtractObject.instanceByString(_conf, className_, expression_);
                    } else {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(className_);
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getClasses().getErrorsDet().add(un_);
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_conf.getClasses().getErrorsDet());
                        badEl_.setFileName(_conf.getCurrentFileName());
                        badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                        className_ = _conf.getStandards().getAliasObject();
                    }
                } else {
                    _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(0);
                    Argument a_ = ElRenderUtil.processEl(expression_, 0, _conf);
                    struct_ = a_.getStruct();
                    if (_conf.getContext().getException() == null) {
                        _ip.setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                        _ip.setLookForAttrValue(true);
                        _ip.setOffset(0);
                        String res_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                        if (res_.isEmpty()) {
                            UnknownClassName un_ = new UnknownClassName();
                            un_.setClassName(className_);
                            un_.setFileName(_conf.getCurrentFileName());
                            un_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.getClasses().getErrorsDet().add(un_);
                            BadElRender badEl_ = new BadElRender();
                            badEl_.setErrors(_conf.getClasses().getErrorsDet());
                            badEl_.setFileName(_conf.getCurrentFileName());
                            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                            className_ = _conf.getStandards().getAliasObject();
                        } else {
                            className_ = res_;
                        }
                    }
                }
            }
        }
        VariableInformation vi_ = new VariableInformation();
        vi_.setClassName(className_);
        vi_.setStruct(struct_);
        return vi_;
    }
    static LocalVariable tryToCreateVariable(Configuration _conf, ImportingPage _ip, String _className, Struct _object) {
        ClassArgumentMatching clMatch_ = new ClassArgumentMatching(_className);
        LgNames lgNames_ = _conf.getStandards();
        if (clMatch_.isPrimitive(_conf.getContext())) {
            _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            if (_object == null || _object.isNull()) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(EMPTY_STRING);
                mapping_.setParam(_className);
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(cast_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                LocalVariable loc_ = new LocalVariable();
                loc_.setClassName(_className);
                loc_.setStruct(_object);
                return loc_;
            }
            LocalVariable loc_ = new LocalVariable();
            loc_.setClassName(_className);
            String type_ = lgNames_.getStructClassName(_object, _conf.getContext());
            if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(type_, _conf.getContext())) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(type_);
                mapping_.setParam(lgNames_.getAliasDouble());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(cast_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                loc_.setStruct(_object);
                return loc_;
            }
            String typeNameArg_ = PrimitiveTypeUtil.toPrimitive(type_, true, _conf.getStandards());
            if (StringList.quickEq(typeNameArg_, lgNames_.getAliasPrimBoolean())) {
                String typeNameParam_ = PrimitiveTypeUtil.toPrimitive(_className, true, _conf.getStandards());
                if (!StringList.quickEq(typeNameParam_, lgNames_.getAliasPrimBoolean())) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(typeNameArg_);
                    mapping_.setParam(typeNameParam_);
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(cast_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    loc_.setStruct(_object);
                    return loc_;
                }
            } else {
                if (PrimitiveTypeUtil.getOrderClass(clMatch_, _conf.getContext()) == 0) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(typeNameArg_);
                    mapping_.setParam(_className);
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(cast_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    loc_.setStruct(_object);
                    return loc_;
                }
            }
            loc_.setStruct(_object);
            return loc_;
        }
        if (_object == null) {
            LocalVariable loc_ = new LocalVariable();
            loc_.setClassName(_className);
            return loc_;
        }
        String param_ = _className;
        String arg_ = lgNames_.getStructClassName(_object, _conf.getContext());
        if (PrimitiveTypeUtil.canBeUseAsArgument(false, param_, arg_, _conf.getContext())) {
            LocalVariable loc_ = new LocalVariable();
            loc_.setClassName(_className);
            loc_.setStruct(_object);
            return loc_;
        }
        Mapping mapping_ = new Mapping();
        mapping_.setArg(arg_);
        mapping_.setParam(param_);
        BadImplicitCast cast_ = new BadImplicitCast();
        cast_.setMapping(mapping_);
        cast_.setFileName(_conf.getCurrentFileName());
        cast_.setIndexFile(_conf.getCurrentLocationIndex());
        _conf.getClasses().getErrorsDet().add(cast_);
        BadElRender badEl_ = new BadElRender();
        badEl_.setErrors(_conf.getClasses().getErrorsDet());
        badEl_.setFileName(_conf.getCurrentFileName());
        badEl_.setIndexFile(_conf.getCurrentLocationIndex());
        _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
        LocalVariable loc_ = new LocalVariable();
        loc_.setClassName(_className);
        return loc_;
    }
    static void checkClass(Configuration _conf, ImportingPage _ip, String _class, Struct _object) {
        if (PrimitiveTypeUtil.primitiveTypeNullObject(_class, _object, _conf.getContext())) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(EMPTY_STRING);
            mapping_.setParam(_class);
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_conf.getCurrentFileName());
            cast_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().getErrorsDet().add(cast_);
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            return;
        }
        if (_object.isNull()) {
            return;
        }
        _ip.setProcessingAttribute(EXPRESSION_ATTRIBUTE);
        _ip.setLookForAttrValue(true);
        _ip.setOffset(0);
        ContextEl context_ = _conf.getContext();
        LgNames lgNames_ = _conf.getStandards();
        String argClassName_ = lgNames_.getStructClassName(_object, context_);
        if (!PrimitiveTypeUtil.isPrimitive(_class, context_)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(argClassName_);
            String paramName_ = _conf.getLastPage().getPageEl().formatVarType(_class, context_);
            mapping_.setParam(paramName_);
            if (!Templates.isCorrect(mapping_, context_)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(cast_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return;
            }
        } else {
            if (PrimitiveTypeUtil.getOrderClass(_class, _conf.getContext()) > 0) {
                if (PrimitiveTypeUtil.getOrderClass(argClassName_, _conf.getContext()) == 0) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(argClassName_);
                    mapping_.setParam(_class);
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(cast_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return;
                }
            } else {
                String typeNameArg_ = PrimitiveTypeUtil.toPrimitive(argClassName_, true,_conf.getStandards());
                if (!StringList.quickEq(typeNameArg_, context_.getStandards().getAliasPrimBoolean())) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setArg(typeNameArg_);
                    mapping_.setParam(_class);
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(_conf.getCurrentFileName());
                    cast_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(cast_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return;
                }
            }
        }
    }
    static void checkSyntax(Configuration _conf,Document _doc, String _html) {
        Element root_ = _doc.getDocumentElement();
        Node en_ = root_;
        int indexGlobal_ = _html.indexOf(LT_BEGIN_TAG)+1;
        CustList<StringList> vars_ = new CustList<StringList>();
        StringList catchVars_ = new StringList();
        ObjectMap<NodeAttribute, NatTreeMap<Integer, Integer>> infos_;
        infos_ = new ObjectMap<NodeAttribute, NatTreeMap<Integer, Integer>>();
        String prefix_ = _conf.getLastPage().getPrefix();
        while (true) {
            _conf.getLastPage().setProcessingNode(en_);
            if (en_ instanceof Element) {
                Element elt_ = (Element) en_;
                if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,FOR_BLOCK_TAG))) {
                    StringList varsLoc_ = checkForLoop(_conf, elt_, _html);
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    for (StringList l: vars_) {
                        for (String v: l) {
                            if (varsLoc_.containsStr(v)) {
                                StringList alls_ = new StringList();
                                for (StringList g: vars_) {
                                    alls_.addAllElts(g);
                                }
                                DuplicateVariable d_ = new DuplicateVariable();
                                d_.setId(v);
                                d_.setFileName(_conf.getCurrentFileName());
                                d_.setIndexFile(_conf.getCurrentLocationIndex());
                                _conf.getClasses().getErrorsDet().add(d_);
                                BadElRender badEl_ = new BadElRender();
                                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                                badEl_.setFileName(_conf.getCurrentFileName());
                                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                                return;
                            }
                        }
                    }
                    if (elt_.hasChildNodes()) {
                        vars_.add(varsLoc_);
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,SELECT_TAG))) {
                    if (elt_.getAttribute(ATTRIBUTE_MAP).isEmpty()) {
                        if (elt_.getAttribute(ATTRIBUTE_LIST).isEmpty()) {
                            _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
                            _conf.getLastPage().setOffset(0);
                            _conf.getLastPage().setLookForAttrValue(false);
                            _conf.getContext().setException(NullStruct.NULL_VALUE);
                            return;
                        }
                    }
                } else if (ExtractCondition.isContentOfConditionNode(_conf, elt_)) {
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
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,ELSE_BLOCK_TAG))) {
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
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,TRY_TAG))) {
                    if (elt_.getFirstChild() == null) {
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
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
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,CATCH_TAG))) {
                    String className_ = elt_.getAttribute(ATTRIBUTE_CLASS_NAME);
                    _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_CLASS_NAME);
                    _conf.getLastPage().setOffset(0);
                    _conf.getLastPage().setLookForAttrValue(false);
                    String res_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                    if (res_.isEmpty()) {
                        UnknownClassName un_ = new UnknownClassName();
                        un_.setClassName(className_);
                        un_.setFileName(_conf.getCurrentFileName());
                        un_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getClasses().getErrorsDet().add(un_);
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_conf.getClasses().getErrorsDet());
                        badEl_.setFileName(_conf.getCurrentFileName());
                        badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                        return;
                    }
                    className_ = res_;
                    String var_ = elt_.getAttribute(ATTRIBUTE_VAR);
                    if (!StringList.isWord(var_)) {
                        BadVariableName b_ = new BadVariableName();
                        b_.setFileName(_conf.getCurrentFileName());
                        b_.setIndexFile(_conf.getCurrentLocationIndex());
                        b_.setVarName(var_);
                        _conf.getClasses().getErrorsDet().add(b_);
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_conf.getClasses().getErrorsDet());
                        badEl_.setFileName(_conf.getCurrentFileName());
                        badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                        return;
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
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
                    }
                    for (String v: catchVars_) {
                        if (StringList.quickEq(var_, v)) {
                            StringList alls_ = new StringList();
                            for (StringList g: vars_) {
                                alls_.addAllElts(g);
                            }
                            DuplicateVariable d_ = new DuplicateVariable();
                            d_.setId(var_);
                            d_.setFileName(_conf.getCurrentFileName());
                            d_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.getClasses().getErrorsDet().add(d_);
                            BadElRender badEl_ = new BadElRender();
                            badEl_.setErrors(_conf.getClasses().getErrorsDet());
                            badEl_.setFileName(_conf.getCurrentFileName());
                            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                            _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                            return;
                        }
                    }
                    if (elt_.hasChildNodes()) {
                        catchVars_.add(var_);
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,TAG_FINALLY))) {
                    if (elt_.getFirstChild() == null) {
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
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
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,BREAK_TAG))) {
                    Element parent_ = elt_.getParentNode();
                    boolean ok_ = false;
                    while (parent_ != null) {
                        if (StringList.quickEq(parent_.getTagName(), StringList.concat(prefix_,FOR_BLOCK_TAG))) {
                            ok_ = true;
                            break;
                        }
                        if (StringList.quickEq(parent_.getTagName(), StringList.concat(prefix_,WHILE_BLOCK_TAG))) {
                            ok_ = true;
                            break;
                        }
                        if (StringList.quickEq(parent_.getTagName(), StringList.concat(prefix_,TAG_DO))) {
                            ok_ = true;
                            break;
                        }
                        if (StringList.quickEq(parent_.getTagName(), StringList.concat(prefix_,TAG_SWITCH))) {
                            ok_ = true;
                            break;
                        }
                        parent_ = parent_.getParentNode();
                    }
                    if (!ok_) {
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,CONTINUE_TAG))) {
                    Element parent_ = elt_.getParentNode();
                    boolean ok_ = false;
                    while (parent_ != null) {
                        if (StringList.quickEq(parent_.getTagName(), StringList.concat(prefix_,FOR_BLOCK_TAG))) {
                            ok_ = true;
                            break;
                        }
                        if (StringList.quickEq(parent_.getTagName(), StringList.concat(prefix_,WHILE_BLOCK_TAG))) {
                            ok_ = true;
                            break;
                        }
                        if (StringList.quickEq(parent_.getTagName(), StringList.concat(prefix_,TAG_DO))) {
                            ok_ = true;
                            break;
                        }
                        parent_ = parent_.getParentNode();
                    }
                    if (!ok_) {
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,TAG_SWITCH))) {
                    Node first_ = elt_.getFirstChild();
                    while (first_ != null) {
                        if (first_ instanceof Text) {
                            if (first_.getTextContent().trim().isEmpty()) {
                                first_ = first_.getNextSibling();
                                continue;
                            }
                            _conf.getContext().setException(NullStruct.NULL_VALUE);
                            return;
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
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,TAG_CASE))) {
                    if (elt_.getParentNode() == null || !StringList.quickEq(elt_.getParentNode().getTagName(),StringList.concat(prefix_,TAG_SWITCH))) {
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,TAG_DEFAULT))) {
                    if (elt_.getParentNode() == null || !StringList.quickEq(elt_.getParentNode().getTagName(),StringList.concat(prefix_,TAG_SWITCH))) {
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,WHILE_BLOCK_TAG))) {
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
                            _conf.getContext().setException(NullStruct.NULL_VALUE);
                            return;
                        }
                    }
                } else if (StringList.quickEq(elt_.getTagName(), StringList.concat(prefix_,TAG_DO))) {
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
                        _conf.getContext().setException(NullStruct.NULL_VALUE);
                        return;
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
                infos_.put(nodeAttr_, getIndexesSpecChars(_html, false, attrPart_, indexGlobal_));
                indexGlobal_ = endHeader_;
            } else {
                indexGlobal_ = _html.indexOf(END_COMMENT, indexGlobal_+END_COMMENT.length() - 1)+END_COMMENT.length()-1;
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
            if (StringList.quickEq(((Element) n_).getTagName(),StringList.concat(prefix_,FOR_BLOCK_TAG))) {
                vars_.removeLast();
            }
            if (StringList.quickEq(((Element) n_).getTagName(),StringList.concat(prefix_,CATCH_TAG))) {
                catchVars_.removeLast();
            }
            Node next_ = n_.getNextSibling();
            while (next_ == null) {
                Element par_ = n_.getParentNode();
                if (par_ == root_) {
                    break;
                }
                if (StringList.quickEq(par_.getTagName(), StringList.concat(prefix_,FOR_BLOCK_TAG))) {
                    vars_.removeLast();
                }
                if (StringList.quickEq(par_.getTagName(), StringList.concat(prefix_,CATCH_TAG))) {
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
                int beginHeader_ = indexGlobal_ + ((Element) en_).getTagName().length();
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
                indexGlobal_ = _html.indexOf(END_COMMENT, indexGlobal_+END_COMMENT.length() - 1)+END_COMMENT.length()-1;
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
    static StringList checkForLoop(Configuration _conf, Element _node, String _html) {
        StringList vars_ = new StringList();
        _conf.getLastPage().setProcessingAttribute(EMPTY_STRING);
        _conf.getLastPage().setOffset(0);
        _conf.getLastPage().setLookForAttrValue(false);
        if (_node.hasAttribute(ATTRIBUTE_LIST)) {
            String varName_ = _node.getAttribute(ATTRIBUTE_VAR);
            if (!StringList.isWord(varName_)) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(_conf.getCurrentFileName());
                b_.setIndexFile(_conf.getCurrentLocationIndex());
                b_.setVarName(varName_);
                _conf.getClasses().getErrorsDet().add(b_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return vars_;
            }
            vars_.add(varName_);
            return vars_;
        }
        if (_node.hasAttribute(ATTRIBUTE_MAP)) {
            String key_ = _node.getAttribute(ATTRIBUTE_KEY);
            if (!StringList.isWord(key_)) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(_conf.getCurrentFileName());
                b_.setIndexFile(_conf.getCurrentLocationIndex());
                b_.setVarName(key_);
                _conf.getClasses().getErrorsDet().add(b_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return vars_;
            }
            String value_ = _node.getAttribute(ATTRIBUTE_VALUE);
            if (!StringList.isWord(value_)) {
                BadVariableName b_ = new BadVariableName();
                b_.setFileName(_conf.getCurrentFileName());
                b_.setIndexFile(_conf.getCurrentLocationIndex());
                b_.setVarName(value_);
                _conf.getClasses().getErrorsDet().add(b_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return vars_;
            }
            if (StringList.quickEq(key_,value_)) {
                DuplicateVariable d_ = new DuplicateVariable();
                d_.setId(key_);
                d_.setFileName(_conf.getCurrentFileName());
                d_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(d_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return vars_;
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
                        BadVariableName b_ = new BadVariableName();
                        b_.setFileName(_conf.getCurrentFileName());
                        b_.setIndexFile(_conf.getCurrentLocationIndex());
                        b_.setVarName(varName_);
                        _conf.getClasses().getErrorsDet().add(b_);
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_conf.getClasses().getErrorsDet());
                        badEl_.setFileName(_conf.getCurrentFileName());
                        badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                        return vars_;
                    }
                    vars_.add(varName_);
                    return vars_;
                }
            }
        }
        _conf.getContext().setException(NullStruct.NULL_VALUE);
        return vars_;
    }

    private static boolean interpretBrackets(CharacterData _node) {
        Element par_ = _node.getParentNode();
        if (!StringList.quickEq(par_.getTagName(), TAG_STYLE)) {
            if (!StringList.quickEq(par_.getTagName(), SCRIPT)) {
                return true;
            }
        }
        Element elt_ = par_;
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
        Struct obj_;
        Struct currentField_;
        String end_ = EMPTY_STRING;
        long index_ = -1;
        long found_ = -1;
        boolean key_ = false;
        if (name_.endsWith(GET_ATTRIBUTE)) {
            String oldVar_ = name_.substring(CustList.FIRST_INDEX, name_.length() - GET_ATTRIBUTE.length());
            LoopVariable lv_ = ExtractObject.getCurrentVariable(_conf, 0, _ip.getVars(), oldVar_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            obj_ = lv_.getContainer();
            ExtractObject.checkNullPointer(_conf, obj_.getInstance());
            if (_conf.getContext().getException() != null) {
                return;
            }
            index_ = lv_.getIndex();
            for (EntryCust<Long, NodeContainer> e: _containers.entryList()) {
                if (!ExtractObject.eq(_conf, e.getValue().getStruct(), obj_)) {
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    continue;
                }
                if (_conf.getContext().getException() != null) {
                    return;
                }
                if (e.getValue().getIndex() != index_) {
                    continue;
                }
                found_ = e.getKey();
                break;
            }
            currentField_ = lv_.getStruct();
            for (BlockHtml b: _ip.getBlockStacks()) {
                if (!(b instanceof LoopHtmlStack)) {
                    continue;
                }
                LoopHtmlStack l_ = (LoopHtmlStack) b;
                if (StringList.quickEq(l_.getReadNode().getAttribute(ATTRIBUTE_KEY), oldVar_)) {
                    key_ = true;
                    break;
                }
            }
            end_ = EMPTY_STRING;
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
                obj_ = ElRenderUtil.processEl(begin_, 0, _conf).getStruct();
                if (_conf.getContext().getException() != null) {
                    return;
                }
                _ip.addToOffset(begin_.length());
                end_ = name_.substring(i_ + 1);
            } else {
                obj_ = getBean(_conf, _ip.getBeanName());
                end_ = name_;
            }
            ExtractObject.checkNullPointer(_conf, obj_.getInstance());
            if (_conf.getContext().getException() != null) {
                return;
            }
            for (EntryCust<Long, NodeContainer> e: _containers.entryList()) {
                if (!ExtractObject.eq(_conf,e.getValue().getStruct(), obj_)) {
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    continue;
                }
                if (_conf.getContext().getException() != null) {
                    return;
                }
                if (!StringList.quickEq(e.getValue().getLastToken(), end_)) {
                    continue;
                }
                found_ = e.getKey();
                break;
            }
            Struct current_ = _ip.getGlobalArgument().getStruct();
            _ip.setGlobalArgumentStruct(obj_, _conf);
            currentField_ = ElRenderUtil.processEl(end_, 0, _conf).getStruct();
            if (_conf.getContext().getException() != null) {
                return;
            }
            _ip.setGlobalArgumentStruct(current_, _conf);
        }
        if (found_ == -1) {
            long currentInput_ = _indexes.getInput();
            NodeContainer nodeCont_ = new NodeContainer(name_);
            nodeCont_.setEnabled(true);
            nodeCont_.setLastToken(end_);
            nodeCont_.setIndex(index_);
            nodeCont_.setKey(key_);
            nodeCont_.setTypedStruct(currentField_);
            nodeCont_.setBeanName(_ip.getBeanName());
            nodeCont_.setStruct(obj_);
            NodeInformations nodeInfos_ = nodeCont_.getNodeInformation();
            String id_ = _input.getAttribute(ATTRIBUTE_ID);
            if (id_.isEmpty()) {
                id_ = _input.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_GROUP_ID));
            }
            String type_ = _input.getAttribute(ATTRIBUTE_TYPE);
            String class_ = _input.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_CLASS_NAME));
            if (class_.isEmpty()) {
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
                if (StringList.quickEq(_input.getTagName(), SELECT_TAG)) {
                    type_ = SELECT_TAG;
                    if (_input.hasAttribute(ATTRIBUTE_MULTIPLE)) {
                        class_ = _conf.getStandards().getCustList();
                    } else {
                        class_ = _conf.getStandards().getAliasString();
                    }
                }
                if (StringList.quickEq(_input.getTagName(), TEXT_AREA)) {
                    type_ = TEXT_AREA;
                    class_ = _conf.getStandards().getAliasString();
                }
            } else {
                if (StringList.quickEq(_input.getTagName(), SELECT_TAG)) {
                    type_ = SELECT_TAG;
                }
                if (StringList.quickEq(_input.getTagName(), TEXT_AREA)) {
                    type_ = TEXT_AREA;
                }
            }
            nodeInfos_.setType(type_);
            nodeInfos_.setValidator(_input.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VALIDATOR)));
            nodeInfos_.setId(id_);
            nodeInfos_.setInputClass(class_);
            nodeInfos_.setVarMethod(_input.getAttribute(StringList.concat(_conf.getPrefix(),VAR_METHOD)));
            nodeInfos_.setChanging(_input.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VALUE_CHANGE_EVENT)));
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
            attributesNames_.add(mapAttr_.item(i).getName());
        }
        allAttributesNames_.addAllElts(attributesNames_);
        attributesNames_.removeAllString(ATTRIBUTE_ID);
        String id_ = _tag.getAttribute(ATTRIBUTE_ID);
        if (!id_.isEmpty()) {
            _ip.setProcessingAttribute(ATTRIBUTE_ID);
            id_ = interpretPartiallyIds(_conf, _ip, id_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            _tag.setAttribute(ATTRIBUTE_ID, id_);
        }
        attributesNames_.removeAllString(StringList.concat(_conf.getPrefix(),ATTRIBUTE_GROUP_ID));
        String groupId_ = _tag.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_GROUP_ID));
        if (!groupId_.isEmpty()) {
            _ip.setProcessingAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_GROUP_ID));
            groupId_ = interpretPartiallyIds(_conf, _ip, groupId_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            _tag.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_GROUP_ID), groupId_);
        }
        if (StringList.quickEq(_tag.getTagName(),StringList.concat(prefixWrite_,SUBMIT_BLOCK_TAG)) && !prefixWrite_.isEmpty()) {
            attributesNames_.removeAllString(ATTRIBUTE_VALUE_SUBMIT);
            String value_ = _tag.getAttribute(ATTRIBUTE_VALUE_SUBMIT);
            StringList elts_ = StringList.splitStrings(value_, COMMA);
            String var_ = elts_.first();
            String fileName_ = ExtractObject.getProperty(_conf, var_);
            if (fileName_ == null) {
                fileName_ = var_;
            }
            StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
            if (_conf.getContext().getException() != null) {
                return;
            }
            _ip.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
            _ip.setOffset(var_.length()+1);
            _ip.setLookForAttrValue(true);
            String key_ = elts_.last();
            String preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            preformatted_ = DocumentBuilder.transformSpecialChars(preformatted_, _tag.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
            _tag.removeAttribute(ATTRIBUTE_VALUE_SUBMIT);
            _tag.removeAttribute(ATTRIBUTE_ESCAPED_EAMP);
            StringList objects_ = new StringList();
            int i_ = CustList.FIRST_INDEX;
            while (_tag.hasAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)))) {
                attributesNames_.removeAllString(StringList.concat(TAG_PARAM,Long.toString(i_)));
                String attribute_ = _tag.getAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
                if (attribute_.startsWith(CALL_METHOD)) {
                    _ip.setProcessingAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(1);
                    Struct str_ = ElRenderUtil.processEl(attribute_, 1, _conf).getStruct();
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    objects_.add(ExtractObject.valueOf(_conf, str_));
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                } else {
                    objects_.add(attribute_);
                }
                _tag.removeAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
                i_++;
            }
            attributesNames_.removeAllString(ATTRIBUTE_VALUE);
            attributesNames_.removeAllString(ATTRIBUTE_TYPE);
            _tag.setAttribute(ATTRIBUTE_VALUE, StringList.simpleStringsFormat(preformatted_, objects_.toArray()));
            _tag.setAttribute(ATTRIBUTE_TYPE, SUBMIT_TYPE);
            _doc.renameNode(_tag, INPUT_TAG);
        }
        if (StringList.quickEq(_tag.getTagName(),StringList.concat(prefixWrite_,TAG_A)) && !prefixWrite_.isEmpty()) {
            attributesNames_.removeAllString(ATTRIBUTE_VALUE);
            String value_ = _tag.getAttribute(ATTRIBUTE_VALUE);
            StringList elts_ = StringList.splitStrings(value_, COMMA);
            String var_ = elts_.first();
            String fileName_ = ExtractObject.getProperty(_conf, var_);
            if (fileName_ == null) {
                fileName_ = var_;
            }
            StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
            if (_conf.getContext().getException() != null) {
                return;
            }
            _ip.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
            _ip.setOffset(var_.length()+1);
            _ip.setLookForAttrValue(true);
            String key_ = elts_.last();
            String preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            preformatted_ = DocumentBuilder.transformSpecialChars(preformatted_, _tag.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
            _tag.removeAttribute(ATTRIBUTE_VALUE_SUBMIT);
            _tag.removeAttribute(ATTRIBUTE_VALUE);
            StringList objects_ = new StringList();
            int i_ = CustList.FIRST_INDEX;
            while (_tag.hasAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)))) {
                attributesNames_.removeAllString(StringList.concat(TAG_PARAM,Long.toString(i_)));
                String attribute_ = _tag.getAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
                if (attribute_.startsWith(CALL_METHOD)) {
                    _ip.setProcessingAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
                    _ip.setLookForAttrValue(true);
                    _ip.setOffset(1);
                    Struct str_ = ElRenderUtil.processEl(attribute_, 1, _conf).getStruct();
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    objects_.add(ExtractObject.valueOf(_conf, str_));
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                } else {
                    objects_.add(attribute_);
                }
                _tag.removeAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
                i_++;
            }
            attributesNames_.removeAllString(ATTRIBUTE_TITLE);
            _tag.setAttribute(ATTRIBUTE_TITLE, StringList.simpleStringsFormat(preformatted_, objects_.toArray()));
            _doc.renameNode(_tag, TAG_A);
        }
        if (StringList.quickEq(_tag.getTagName(),StringList.concat(prefixWrite_,TAG_IMG)) && !prefixWrite_.isEmpty()) {
            _doc.renameNode(_tag, TAG_IMG);
        } else if (StringList.quickEq(_tag.getTagName(),TAG_IMG)) {
            String src_ = _tag.getAttribute(ATTRIBUTE_SRC);
            if (!src_.isEmpty()) {
                _ip.setProcessingAttribute(ATTRIBUTE_SRC);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                src_ = ExtractObject.formatNumVariables(src_, _conf, _ip);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                attributesNames_.removeAllString(StringList.concat(_conf.getPrefix(),ATTRIBUTE_ENCODE_IMG));
                attributesNames_.removeAllString(ATTRIBUTE_SRC);
                String content_ = ExtractFromResources.getContentFile(_conf, _files,src_,_resourcesFolder);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                _tag.setAttribute(ATTRIBUTE_SRC, content_);
                _tag.removeAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_ENCODE_IMG));
            }
        }
        if (StringList.quickEq(_tag.getTagName(),TAG_LINK)) {
            ElementList heads_ = _doc.getElementsByTagName(TAG_HEAD);
            attributesNames_.removeAllString(ATTRIBUTE_HREF);
            attributesNames_.removeAllString(ATTRIBUTE_REL);
            String href_ = getCssHref(_tag);
            if (href_ != null && heads_.getLength() == CustList.ONE_ELEMENT){
                Element head_ = heads_.item(CustList.FIRST_INDEX);
                CustList<Element> children_ = new CustList<Element>();
                for (Element c: head_.getChildElements()) {
                    if (!StringList.quickEq(c.getTagName(), TAG_STYLE)) {
                        continue;
                    }
                    children_.add(c);
                }
                _ip.setProcessingAttribute(ATTRIBUTE_HREF);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                StringList objects_ = new StringList();
                int i_ = CustList.FIRST_INDEX;
                while (_tag.hasAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)))) {
                    attributesNames_.removeAllString(StringList.concat(TAG_PARAM,Long.toString(i_)));
                    String attribute_ = _tag.getAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
                    if (attribute_.startsWith(CALL_METHOD)) {
                        _ip.setProcessingAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
                        _ip.setLookForAttrValue(true);
                        _ip.setOffset(1);
                        Struct str_ = ElRenderUtil.processEl(attribute_, 1, _conf).getStruct();
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
                        objects_.add(ExtractObject.valueOf(_conf,str_));
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
                    } else {
                        objects_.add(attribute_);
                    }
                    _tag.removeAttribute(StringList.concat(TAG_PARAM,Long.toString(i_)));
                    i_++;
                }
                String fileContent_ = ExtractFromResources.getContentFile(_conf, _files, href_, _resourcesFolder);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                if (!objects_.isEmpty()) {
                    fileContent_ = StringList.simpleStringsFormat(fileContent_, objects_.toArray());
                }
                boolean successAdd_ = children_.isEmpty();
                if (!successAdd_) {
                    Element eltStyle_ = children_.last();
                    CustList<Node> chNode_ = eltStyle_.getChildNodes();
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
        if (StringList.quickEq(_tag.getTagName(),TAG_STYLE)) {
            ElementList links_ = _doc.getElementsByTagName(TAG_LINK);
            int len_ = links_.getLength();
            StringList refs_ = new StringList();
            for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                Element link_ = links_.item(i);
                String href_ = getCssHref(link_);
                if (href_ != null) {
                    refs_.add(href_);
                }
            }
            StringList filesContents_ = new StringList();
            for (String r: refs_) {
                filesContents_.add(ExtractFromResources.getContentFile(_conf, _files, r, _resourcesFolder));
                if (_conf.getContext().getException() != null) {
                    return;
                }
            }
            NodeList chNode_ = _tag.getChildNodes();
            if (chNode_.isEmpty()) {
                Text text_ = _doc.createTextNode(filesContents_.join(RETURN_LINE));
                _tag.appendChild(text_);
            } else {
                Text text_ = (Text) chNode_.last();
                text_.appendData(filesContents_.join(RETURN_LINE));
            }
        }
        if (StringList.quickEq(_tag.getTagName(),SCRIPT)) {
            NamedNodeMap map_ = _tag.getAttributes();
            Attr href_ = map_.getNamedItem(ATTRIBUTE_HREF);
            if (href_ != null){
                _ip.setProcessingAttribute(ATTRIBUTE_HREF);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                attributesNames_.removeAllString(ATTRIBUTE_HREF);
                attributesNames_.removeAllString(ATTRIBUTE_TYPE);
                String ref_ = href_.getValue();
                ref_ = ExtractObject.formatNumVariables(ref_, _conf, _ip);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                _tag.setAttribute(ATTRIBUTE_TYPE, SCRIPT_TYPE);
                Text txt_ = _doc.createTextNode(ExtractFromResources.getContentFile(_conf, _files,ref_,_resourcesFolder));
                if (_conf.getContext().getException() != null) {
                    return;
                }
                _tag.appendChild(txt_);
            }
        }
        String accessName_ = EMPTY_STRING;
        if (StringList.quickEq(_tag.getTagName(),INPUT_TAG)) {
            //format name
            attributesNames_.removeAllString(ATTRIBUTE_VALUE);
            attributesNames_.removeAllString(ATTRIBUTE_NAME);
            attributesNames_.removeAllString(StringList.concat(_conf.getPrefix(),ATTRIBUTE_CLASS_NAME));
            attributesNames_.removeAllString(StringList.concat(_conf.getPrefix(),VAR_METHOD));
            String name_ = _tag.getAttribute(ATTRIBUTE_NAME);
            accessName_ = name_;
            if (!name_.isEmpty()) {
                _ip.setProcessingAttribute(ATTRIBUTE_NAME);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                setIndexes(_indexes, _conf, _ip, _containersMap, _containers, _tag, name_);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                if (_indexes.getNb() >= 0) {
                    _tag.setAttribute(NUMBER_INPUT, String.valueOf(_indexes.getNb()));
                }
                attributesNames_.removeAllString(NUMBER_INPUT);
                _tag.setAttribute(ATTRIBUTE_NAME, StringList.concat(beanName_,DOT,name_));
            }
        }
        if (StringList.quickEq(_tag.getTagName(),TEXT_AREA)) {
            //format name
            attributesNames_.removeAllString(ATTRIBUTE_NAME);
            attributesNames_.removeAllString(StringList.concat(_conf.getPrefix(),ATTRIBUTE_CLASS_NAME));
            attributesNames_.removeAllString(StringList.concat(_conf.getPrefix(),VAR_METHOD));
            String name_ = _tag.getAttribute(ATTRIBUTE_NAME);
            if (!name_.isEmpty()) {
                _ip.setProcessingAttribute(ATTRIBUTE_NAME);
                _ip.setLookForAttrValue(true);
                _ip.setOffset(0);
                setIndexes(_indexes, _conf, _ip, _containersMap, _containers, _tag, name_);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                if (_indexes.getNb() >= 0) {
                    _tag.setAttribute(NUMBER_INPUT, String.valueOf(_indexes.getNb()));
                }
                attributesNames_.removeAllString(NUMBER_INPUT);
                _tag.setAttribute(ATTRIBUTE_NAME, StringList.concat(beanName_,DOT,name_));
            }
        }
        if (_tag.hasAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VAR_VALUE))) {
            if (StringList.quickEq(_tag.getTagName(),INPUT_TAG)) {
                attributesNames_.removeAllString(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VAR_VALUE));
                attributesNames_.removeAllString(ATTRIBUTE_VALUE);
                attributesNames_.removeAllString(CHECKED);
                setValueInput(_conf, _tag);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                _tag.removeAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VAR_VALUE));
            }
            if (StringList.quickEq(_tag.getTagName(),TEXT_AREA)) {
                attributesNames_.removeAllString(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VAR_VALUE));
                setValueTextArea(_conf, _doc, _tag);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                _tag.removeAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VAR_VALUE));
            }
        }
        if (StringList.quickEq(_tag.getTagName(),INPUT_TAG)) {
            attributesNames_.removeAllString(ATTRIBUTE_TYPE);
            if (StringList.quickEq(_tag.getAttribute(ATTRIBUTE_TYPE),RADIO)) {
                attributesNames_.removeAllString(CHECKED);
                _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_NAME);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
                Struct o_ = null;
                if (!accessName_.isEmpty()) {
                    o_ = ElRenderUtil.processEl(accessName_, 0, _conf).getStruct();
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                }
                if (o_ == null || o_.isNull()) {
                    _tag.removeAttribute(CHECKED);
                } else {
                    String strObj_ = ExtractObject.getStringKey(_conf, o_);
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    if (StringList.quickEq(_tag.getAttribute(ATTRIBUTE_VALUE),strObj_)) {
                        _tag.setAttribute(CHECKED, CHECKED);
                    } else {
                        _tag.removeAttribute(CHECKED);
                    }
                }
            }
        }
        if (StringList.quickEq(_tag.getTagName(),SPAN_TAG)) {
            String forId_ = _tag.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_FOR));
            if (!forId_.isEmpty()) {
                _ip.setProcessingAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_FOR));
                attributesNames_.removeAllString(StringList.concat(_conf.getPrefix(),ATTRIBUTE_FOR));
                forId_ = interpretPartiallyIds(_conf, _ip, forId_);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                _tag.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_FOR), forId_);
                if (!_ip.getReadWrite().getRead().hasChildNodes()) {
                    Text txt_ = _doc.createTextNode(SPACE);
                    _tag.appendChild(txt_);
                }
            }
        }
        if (StringList.quickEq(_tag.getTagName(),TAG_A)) {
            String attr_ = _tag.getAttribute(ATTRIBUTE_HREF);
            if (!attr_.isEmpty()) {
                attributesNames_.removeAllString(ATTRIBUTE_HREF);
                CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                attr_ = format(stacks_, attr_, false);
                _tag.setAttribute(ATTRIBUTE_HREF, attr_);
            }
            attr_ = _tag.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND));
            if (!attr_.isEmpty()) {
                CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                attr_ = format(stacks_, attr_, false);
                _tag.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND), attr_);
            }
            String href_ = _tag.getAttribute(ATTRIBUTE_HREF);
            if (href_.startsWith(CALL_METHOD)) {
                _tag.setAttribute(ATTRIBUTE_HREF, StringList.concat(CALL_METHOD,beanName_,DOT,href_.substring(1)));
            } else {
                href_ = _tag.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND));
                if (href_.startsWith(CALL_METHOD)) {
                    _tag.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND), StringList.concat(CALL_METHOD,beanName_,DOT,href_.substring(1)));
                }
            }
            if (_tag.hasAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND))) {
                _tag.setAttribute(ATTRIBUTE_HREF, EMPTY_STRING);
            }
        }
        if (StringList.quickEq(_tag.getTagName(),TAG_FORM)) {
            String attr_ = _tag.getAttribute(ATTRIBUTE_ACTION);
            if (!attr_.isEmpty()) {
                CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                attributesNames_.removeAllString(ATTRIBUTE_ACTION);
                attr_ = format(stacks_, attr_, false);
                _tag.setAttribute(ATTRIBUTE_ACTION, attr_);
            }
            attr_ = _tag.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND));
            if (!attr_.isEmpty()) {
                CustList<BlockHtml> stacks_ = _ip.getBlockStacks();
                attr_ = format(stacks_, attr_, false);
                _tag.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND), attr_);
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
                _tag.setAttribute(ATTRIBUTE_ACTION, StringList.concat(CALL_METHOD,beanName_,DOT,href_.substring(1)));
            } else {
                href_ = _tag.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND));
                if (href_.startsWith(CALL_METHOD)) {
                    _tag.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND), StringList.concat(CALL_METHOD,beanName_,DOT,href_.substring(1)));
                }
            }
            if (_tag.hasAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_COMMAND))) {
                _tag.setAttribute(ATTRIBUTE_ACTION, EMPTY_STRING);
            }
        }
        if (!StringList.quickEq(_tag.getTagName(),StringList.concat(prefixWrite_,TR_END_BLOCK_TAG)) || prefixWrite_.isEmpty()) {
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
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
                    } else {
                        _ip.setProcessingAttribute(a);
                        _ip.setLookForAttrValue(true);
                        _ip.setOffset(0);
                        v_ = ExtractObject.formatNumVariables(v_, _conf, _ip);
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
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
        Attr href_ = map_.getNamedItem(ATTRIBUTE_HREF);
        if (href_ == null){
            return null;
        }
        return href_.getValue();
    }

    private static void setValueTextArea(Configuration _conf, Document _doc, Element _tag) {
        String attribute_ = _tag.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VAR_VALUE));
        _conf.getLastPage().setProcessingAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VAR_VALUE));
        _conf.getLastPage().setLookForAttrValue(true);
        _conf.getLastPage().setOffset(0);
        Struct o_ = ElRenderUtil.processEl(attribute_, 0, _conf).getStruct();
        if (_conf.getContext().getException() != null) {
            return;
        }
        if (o_.isNull()) {
            o_ = new StringStruct(EMPTY_STRING);
        }
        //TODO converter
        Text text_ = _doc.createTextNode(ExtractObject.toString(_conf, o_));
        _tag.appendChild(text_);
    }

    private static void setValueInput(Configuration _conf, Element _tag) {
        String attribute_ = _tag.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VAR_VALUE));
        Long value_ = LgNames.parseLong(attribute_);
        if (value_ != null) {
            //TODO converter
            _tag.setAttribute(ATTRIBUTE_VALUE, Numbers.toString(value_));
        } else {
            _conf.getLastPage().setProcessingAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VAR_VALUE));
            _conf.getLastPage().setLookForAttrValue(true);
            _conf.getLastPage().setOffset(0);
            Struct o_ = ElRenderUtil.processEl(attribute_, 0, _conf).getStruct();
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (o_.isNull()) {
                _tag.setAttribute(ATTRIBUTE_VALUE, (String) o_.getInstance());
            } else if (o_.getInstance() instanceof Boolean) {
                if ((Boolean) o_.getInstance()) {
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
                if (curChar_ == LEFT_EL) {
                    escaped_ = false;
                    calculateVariables_.append(LEFT_EL);
                    i_++;
                    continue;
                }
                if (curChar_ == RIGHT_EL) {
                    escaped_ = false;
                    calculateVariables_.append(RIGHT_EL);
                    i_++;
                    continue;
                }
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return EMPTY_STRING;
            }
            if (curChar_ == ESCAPED) {
                escaped_ = true;
                i_++;
                continue;
            }
            if (curChar_ == LEFT_EL) {
                _ip.setOffset(i_+1);
                Argument arg_ = ElRenderUtil.processEl(_pattern, _conf, i_+1, LEFT_EL, RIGHT_EL);
                if (_conf.getContext().getException() != null) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return EMPTY_STRING;
                }
                calculateVariables_.append(ExtractObject.valueOf(_conf, arg_.getStruct()));
                if (_conf.getContext().getException() != null) {
                    return EMPTY_STRING;
                }
                i_ = _conf.getNextIndex();
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

    static void evaluateAttribute(Configuration _conf, Element _elt, String _attrName) {
        String class_ = _elt.getAttribute(_attrName);
        if (!class_.contains(CALL_METHOD)) {
            return;
        }
        String command_ = class_.substring(class_.indexOf(CALL_METHOD)+1);
        Struct returnedObject_ = ElRenderUtil.processEl(command_, 0, _conf).getStruct();
        if (_conf.getContext().getException() != null) {
            return;
        }
        if (returnedObject_.isNull()) {
            _elt.removeAttribute(_attrName);
            return;
        }
        _elt.setAttribute(_attrName, ExtractObject.toString(_conf, returnedObject_));
    }

    private static void processOptionsList(Configuration _conf, Document _doc,
            Node _currentModifiedNode, Element _n, String _id, String _groupId, boolean _multiple) {
        String list_ = _n.getAttribute(ATTRIBUTE_LIST);
        String name_ = _n.getAttribute(ATTRIBUTE_NAME);
        boolean update_ = _n.hasAttribute(ATTRIBUTE_UPDATE);
        String varValue_ = _n.getAttribute(ATTRIBUTE_VAR_VALUE);
        String varMethod_ = _n.getAttribute(VAR_METHOD);
        IdList<Struct> returnedVarValue_ = null;
        if (!varValue_.isEmpty()) {
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR_VALUE);
            _conf.getLastPage().setLookForAttrValue(true);
            _conf.getLastPage().setOffset(0);
            returnedVarValue_ = new IdList<Struct>();
            if (!_multiple) {
                returnedVarValue_.add(ElRenderUtil.processEl(varValue_, 0, _conf).getStruct());
                if (_conf.getContext().getException() != null) {
                    return;
                }
            } else {
                Struct o_ = ElRenderUtil.processEl(varValue_, 0, _conf).getStruct();
                if (_conf.getContext().getException() != null) {
                    return;
                }
                if (o_.isNull()) {
                    returnedVarValue_ = null;
                } else {
                    Struct it_ = ExtractObject.iterator(_conf, o_);
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    while (ExtractObject.hasNext(_conf, it_)) {
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
                        returnedVarValue_.add(ExtractObject.next(_conf, it_));
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
                    }
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                }
            }
        }
        _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_LIST);
        _conf.getLastPage().setLookForAttrValue(true);
        _conf.getLastPage().setOffset(0);
        Struct li_ = ElRenderUtil.processEl(list_, 0, _conf).getStruct();
        if (_conf.getContext().getException() != null) {
            return;
        }
        Struct extractedList_ = li_;
        String default_ = _n.getAttribute(DEFAULT_ATTRIBUTE);
        Element docElementSelect_ = _doc.createElement(SELECT_TAG);
        if (!_id.isEmpty() || !_groupId.isEmpty()) {
            if (!_id.isEmpty()) {
                docElementSelect_.setAttribute(ATTRIBUTE_ID, _id);
            } else {
                docElementSelect_.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_GROUP_ID), _groupId);
            }
            docElementSelect_.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VALIDATOR), _n.getAttribute(ATTRIBUTE_VALIDATOR));
        }
        if (_multiple) {
            docElementSelect_.setAttribute(ATTRIBUTE_MULTIPLE, ATTRIBUTE_MULTIPLE);
        }
        if (default_.isEmpty() || returnedVarValue_ != null && update_) {
            Struct it_ = ExtractObject.iterator(_conf, extractedList_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            while (ExtractObject.hasNext(_conf, it_)) {
                if (_conf.getContext().getException() != null) {
                    return;
                }
                Struct o_ = ExtractObject.next(_conf, it_);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                Element option_ = _doc.createElement(TAG_OPTION);
                option_.setAttribute(ATTRIBUTE_VALUE, ExtractObject.getStringKey(_conf, o_));
                if (_conf.getContext().getException() != null) {
                    return;
                }
                if (returnedVarValue_ != null) {
                    for (Struct a: returnedVarValue_) {
                        if (ExtractObject.eq(_conf, a, o_)) {
                            if (_conf.getContext().getException() != null) {
                                return;
                            }
                            option_.setAttribute(SELECTED, SELECTED);
                            break;
                        }
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
                    }
                }
                option_.appendChild(_doc.createTextNode(ExtractObject.toString(_conf, o_)));
                if (_conf.getContext().getException() != null) {
                    return;
                }
                docElementSelect_.appendChild(option_);
            }
            if (_conf.getContext().getException() != null) {
                return;
            }
        } else {
            if (default_.startsWith(CALL_METHOD)) {
                String command_ = default_.substring(1);
                _conf.getLastPage().setProcessingAttribute(DEFAULT_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(1);
                IdList<Struct> defaults_ = new IdList<Struct>();
                if (!_multiple) {
                    defaults_.add(ElRenderUtil.processEl(command_, 0, _conf).getStruct());
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                } else {
                    li_ = ElRenderUtil.processEl(command_, 0, _conf).getStruct();
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    Struct it_ = ExtractObject.iterator(_conf, li_);
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    while (ExtractObject.hasNext(_conf, it_)) {
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
                        defaults_.add(ExtractObject.next(_conf, it_));
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
                    }
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                }
                _conf.getLastPage().setProcessingAttribute(DEFAULT_ATTRIBUTE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
                Struct it_ = ExtractObject.iterator(_conf, extractedList_);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                while (ExtractObject.hasNext(_conf, it_)) {
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    Struct o_ = ExtractObject.next(_conf, it_);
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    Element option_ = _doc.createElement(TAG_OPTION);
                    String nameEnum_ = ExtractObject.getStringKey(_conf, o_);
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    option_.setAttribute(ATTRIBUTE_VALUE, nameEnum_);
                    for (Struct d: defaults_) {
                        if (ExtractObject.eq(_conf, o_, d)) {
                            if (_conf.getContext().getException() != null) {
                                return;
                            }
                            option_.setAttribute(SELECTED, SELECTED);
                            break;
                        }
                        if (_conf.getContext().getException() != null) {
                            return;
                        }
                    }
                    option_.appendChild(_doc.createTextNode(ExtractObject.toString(_conf, o_)));
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    docElementSelect_.appendChild(option_);
                }
                if (_conf.getContext().getException() != null) {
                    return;
                }
            } else {
                StringList names_ = StringList.splitChars(default_, SEP_ENUMS);
                Struct it_ = ExtractObject.iterator(_conf, extractedList_);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                while (ExtractObject.hasNext(_conf, it_)) {
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    Struct o_ = ExtractObject.next(_conf, it_);
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    Element option_ = _doc.createElement(TAG_OPTION);
                    String enumName_ = ExtractObject.getStringKey(_conf, o_);
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    option_.setAttribute(ATTRIBUTE_VALUE, enumName_);
                    for (String n: names_) {
                        if (StringList.quickEq(enumName_,n)) {
                            option_.setAttribute(SELECTED, SELECTED);
                            break;
                        }
                    }
                    option_.appendChild(_doc.createTextNode(ExtractObject.toString(_conf, o_)));
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    docElementSelect_.appendChild(option_);
                }
                if (_conf.getContext().getException() != null) {
                    return;
                }
            }
        }
        docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
        if (!varMethod_.isEmpty()) {
            docElementSelect_.setAttribute(StringList.concat(_conf.getPrefix(),VAR_METHOD), varMethod_);
        }
        docElementSelect_.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_CLASS_NAME), _n.getAttribute(ATTRIBUTE_CLASS_NAME));
        _currentModifiedNode.appendChild(docElementSelect_);
    }

    private static void processOptionsMap(Configuration _conf, Document _doc,
            Node _currentModifiedNode, Node _n, String _map, String _id, String _groupId, boolean _multiple) {
        String name_ = ((Element) _n).getAttribute(ATTRIBUTE_NAME);
        boolean update_ = ((Element)_n).hasAttribute(ATTRIBUTE_UPDATE);
        String varValue_ = ((Element) _n).getAttribute(ATTRIBUTE_VAR_VALUE);
        String varMethod_ = ((Element) _n).getAttribute(VAR_METHOD);
        Struct returnedVarValue_ = null;
        if (!varValue_.isEmpty()) {
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR_VALUE);
            _conf.getLastPage().setLookForAttrValue(true);
            _conf.getLastPage().setOffset(0);
            returnedVarValue_ = ElRenderUtil.processEl(varValue_, 0, _conf).getStruct();
            if (_conf.getContext().getException() != null) {
                return;
            }
        }
        _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_MAP);
        _conf.getLastPage().setLookForAttrValue(true);
        _conf.getLastPage().setOffset(0);
        Struct map_ = ElRenderUtil.processEl(_map, 0, _conf).getStruct();
        if (_conf.getContext().getException() != null) {
            return;
        }
        String default_ = ((Element) _n).getAttribute(DEFAULT_ATTRIBUTE);
        Element docElementSelect_ = _doc.createElement(SELECT_TAG);
        if (!_id.isEmpty() || !_groupId.isEmpty()) {
            if (!_id.isEmpty()) {
                docElementSelect_.setAttribute(ATTRIBUTE_ID, _id);
            } else {
                docElementSelect_.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_GROUP_ID), _groupId);
            }
            docElementSelect_.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_VALIDATOR), ((Element) _n).getAttribute(ATTRIBUTE_VALIDATOR));
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
                Struct defaultEnum_ = ElRenderUtil.processEl(default_, 1, _conf).getStruct();
                if (_conf.getContext().getException() != null) {
                    return;
                }
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
            docElementSelect_.setAttribute(StringList.concat(_conf.getPrefix(),VAR_METHOD), varMethod_);
        }
        docElementSelect_.setAttribute(ATTRIBUTE_NAME, name_);
        docElementSelect_.setAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_CLASS_NAME), ((Element) _n).getAttribute(ATTRIBUTE_CLASS_NAME));
        _currentModifiedNode.appendChild(docElementSelect_);
    }

    private static void processOptionsMapEnum(Configuration _conf, Struct _extractedMap,
            String _default, Document _docSelect, Element _docElementSelect, String _className) {
        StringList names_ = new StringList();
        if (!_className.isEmpty()) {
            ContextEl cont_ = _conf.getContext();
            names_ = _conf.getStandards().getDefaultValues(cont_, _className, _default);
        } else {
            names_ = new StringList(_default);
        }
        Struct l_ = ExtractObject.entryList(_conf, 0, _extractedMap);
        if (_conf.getContext().getException() != null) {
            return;
        }
        Struct it_ = ExtractObject.iterator(_conf, l_);
        if (_conf.getContext().getException() != null) {
            return;
        }
        while (ExtractObject.hasNext(_conf, it_)) {
            if (_conf.getContext().getException() != null) {
                return;
            }
            Struct entry_ = ExtractObject.next(_conf, it_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            ExtractObject.checkNullPointer(_conf, entry_.getInstance());
            if (_conf.getContext().getException() != null) {
                return;
            }
            Struct o_ = ExtractObject.getKey(_conf, entry_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (o_.isNull()) {
                continue;
            }
            Element option_ = _docSelect.createElement(TAG_OPTION);
            String cmp_ = ExtractObject.getStringKey(_conf, o_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            option_.setAttribute(ATTRIBUTE_VALUE, cmp_);
            for (String n: names_) {
                if (StringList.quickEq(n,cmp_)) {
                    option_.setAttribute(SELECTED, SELECTED);
                    break;
                }
            }
            Struct value_ = ExtractObject.getValue(_conf,entry_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            option_.appendChild(_docSelect.createTextNode(ExtractObject.toString(_conf, value_)));
            if (_conf.getContext().getException() != null) {
                return;
            }
            _docElementSelect.appendChild(option_);
        }
    }

    private static void processOptionsMapEnumName(Configuration _conf, Struct _extractedMap,
            Document _docSelect, Element _docElementSelect, Struct _returnedVarValue,
            boolean _multiple) {
        IdList<Struct> obj_ = new IdList<Struct>();
        if (_multiple) {
            if (_returnedVarValue != null) {
                Struct it_ = ExtractObject.iterator(_conf, _returnedVarValue);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                while (ExtractObject.hasNext(_conf, it_)) {
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    obj_.add(ExtractObject.next(_conf, it_));
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                }
                if (_conf.getContext().getException() != null) {
                    return;
                }
            }
        } else {
            obj_.add(_returnedVarValue);
        }
        Struct l_ = ExtractObject.entryList(_conf, 0, _extractedMap);
        if (_conf.getContext().getException() != null) {
            return;
        }
        Struct it_ = ExtractObject.iterator(_conf, l_);
        if (_conf.getContext().getException() != null) {
            return;
        }
        while (ExtractObject.hasNext(_conf, it_)) {
            if (_conf.getContext().getException() != null) {
                return;
            }
            Struct entry_ = ExtractObject.next(_conf, it_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            ExtractObject.checkNullPointer(_conf, entry_.getInstance());
            if (_conf.getContext().getException() != null) {
                return;
            }
            Struct o_ = ExtractObject.getKey(_conf, entry_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (o_.isNull()) {
                continue;
            }
            Element option_ = _docSelect.createElement(TAG_OPTION);
            option_.setAttribute(ATTRIBUTE_VALUE, ExtractObject.getStringKey(_conf, o_));
            if (_conf.getContext().getException() != null) {
                return;
            }
            for (Struct o: obj_) {
                if (o == null) {
                    continue;
                }
                if (ExtractObject.eq(_conf, o_, o)) {
                    if (_conf.getContext().getException() != null) {
                        return;
                    }
                    option_.setAttribute(SELECTED, SELECTED);
                }
                if (_conf.getContext().getException() != null) {
                    return;
                }
            }
            Struct value_ = ExtractObject.getValue(_conf, entry_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            option_.appendChild(_docSelect.createTextNode(ExtractObject.toString(_conf, value_)));
            if (_conf.getContext().getException() != null) {
                return;
            }
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
            if (n_ != null && !StringList.quickEq(((Element) _node).getTagName(), TEXT_AREA)) {
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
        String nodeName_ = ((Element) _node).getTagName();
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,FOR_BLOCK_TAG))) {
            return true;
        }
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,WHILE_BLOCK_TAG))) {
            return true;
        }
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,TAG_DO))) {
            return true;
        }
        return false;
    }
    private static boolean isCatchNode(Configuration _conf, Node _node) {
        String prefix_ = _conf.getLastPage().getPrefix();
        String nodeName_ = ((Element) _node).getTagName();
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,CATCH_TAG))) {
            return true;
        }
        return false;
    }
    private static boolean isFinallyNode(Configuration _conf, Node _node) {
        String prefix_ = _conf.getLastPage().getPrefix();
        String nodeName_ = ((Element) _node).getTagName();
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,TAG_FINALLY))) {
            return true;
        }
        return false;
    }

    private static boolean isTryNode(Configuration _conf, Node _node) {
        String prefix_ = _conf.getLastPage().getPrefix();
        String nodeName_ = ((Element) _node).getTagName();
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,TRY_TAG))) {
            return true;
        }
        return false;
    }

    private static boolean isSwitchNode(Configuration _conf, Node _node) {
        String nodeName_ = ((Element) _node).getTagName();
        String prefix_ = _conf.getLastPage().getPrefix();
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,TAG_CASE))) {
            return true;
        }
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,TAG_DEFAULT))) {
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
        String nodeName_ = ((Element) _node).getTagName();
        String prefix_ = _conf.getLastPage().getPrefix();
        if (StringList.quickEq(nodeName_,StringList.concat(prefix_,ELSE_BLOCK_TAG))) {
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
            processElementOrText(_conf, _ip, false);
        }
    }

    private static void processBlock(Configuration _conf, ImportingPage _ip) {
        processElementOrText(_conf, _ip, false);
    }


    private static void processElementOrText(Configuration _conf, ImportingPage _ip, boolean _firstChild) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        Node en_ = rw_.getRead();
        Node currentNode_ = rw_.getWrite();
        ParentElement parElt_ = getParentOfLastNode(_conf, en_, _firstChild);
        if (parElt_ == null) {
            _ip.setReadWrite(null);
            return;
        }
        Element par_ = parElt_.getElement();
        if (par_ == null) {
            NodeAction na_ = getNextNodeWrite(_conf, en_, _firstChild);
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
            TryHtmlStack tryStack_ = (TryHtmlStack) _ip.getLastStack();
            if (tryStack_.getFinallyNode() != null) {
                _conf.getContext().setException(tryStack_.getException());
                _ip.removeLastBlock();
                return;
            }
            if (_ip.isReturning()) {
                _ip.removeLastBlock();
                removeBlockFinally(_conf, _ip);
                return;
            }
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
            if (_conf.getContext().getException() != null) {
                return;
            }
            Element forLoopLoc_ = l_.getReadNode();
            incrementLoop(_conf, l_, vars_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            currentNode_ = l_.getWriteNode();
            en_ = forLoopLoc_;
            rw_.setRead(en_);
            rw_.setWrite(currentNode_);
            return;
        }
        if (_conf.getContext().getException() != null) {
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
        if (!StringList.quickEq(forLoopLoc_.getTagName(), StringList.concat(prefix_,FOR_BLOCK_TAG))) {
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
                lv_.setStruct(ExtractObject.next(_conf, iterator_));
            } else {
                LongStruct l_ = new LongStruct(_l.getIndex());
                lv_.setStruct(ExecInvokingOperation.getElement(lv_.getContainer(),l_, _conf));
            }
            if (_conf.getContext().getException() != null) {
                return;
            }
            lv_.setIndex(lv_.getIndex() + 1);
        } else if (forLoopLoc_.hasAttribute(ATTRIBUTE_MAP)) {
            String key_ = forLoopLoc_.getAttribute(ATTRIBUTE_KEY);
            _conf.getLastPage().setProcessingNode(forLoopLoc_);
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_KEY);
            _conf.getLastPage().setLookForAttrValue(false);
            _conf.getLastPage().setOffset(0);
            LoopVariable lv_ = _vars.getVal(key_);
            Struct k_;
            Struct entry_ = ExtractObject.next(_conf, _l.getStructIterator());
            if (_conf.getContext().getException() != null) {
                return;
            }
            k_ = ExtractObject.getKey(_conf, entry_);
            if (_conf.getContext().getException() != null) {
                return;
            }
            lv_.setStruct(k_);
            lv_.setIndex(lv_.getIndex() + 1);
            String value_ = forLoopLoc_.getAttribute(ATTRIBUTE_VALUE);
            lv_ = _vars.getVal(value_);
            _conf.getLastPage().setProcessingNode(forLoopLoc_);
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VALUE);
            _conf.getLastPage().setLookForAttrValue(false);
            _conf.getLastPage().setOffset(0);
            lv_.setStruct(ExtractObject.getValue(_conf, entry_));
            if (_conf.getContext().getException() != null) {
                return;
            }
            lv_.setIndex(lv_.getIndex() + 1);
        } else {
            _conf.getLastPage().setProcessingNode(forLoopLoc_);
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR);
            _conf.getLastPage().setLookForAttrValue(false);
            _conf.getLastPage().setOffset(0);
            String var_ = forLoopLoc_.getAttribute(ATTRIBUTE_VAR);
            LoopVariable lv_ = _vars.getVal(var_);
            Number element_ = ((NumberStruct) lv_.getStruct()).getInstance();
            lv_.setStruct(PrimitiveTypeUtil.convert(lv_.getClassName(), element_.longValue()+lv_.getStep(), _conf.getContext()));
            lv_.setIndex(lv_.getIndex() + 1);
        }
    }

    private static void removeVarAndLoop(Configuration _conf, Element _forLoop, StringMap<LoopVariable> _vars) {
        ImportingPage ip_ = _conf.getLastPage();
        String prefix_ = ip_.getPrefix();
        ip_.removeLastBlock();
        if (!StringList.quickEq(_forLoop.getTagName(), StringList.concat(prefix_,FOR_BLOCK_TAG))) {
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
                currentNode_ = _doc.createElement(_read.getTagName());
                setNormalAttributes(_doc, _conf, _read, currentNode_);
            } else {
                String nodeName_ = _read.getTagName();
                String newNodeName_;
                if (nodeName_.startsWith(prefix_)) {
                    String suffix_ = _read.getTagName().substring(prefix_.length());
                    newNodeName_ = StringList.concat(getPrefix(_conf, (Document)_parent),suffix_);
                } else {
                    newNodeName_ = nodeName_;
                }
                currentNode_ = _doc.createElement(newNodeName_);
                setPrefixedAttributes(_doc, _conf, _read, currentNode_, prefix_);
            }
        } else {
            String replacing_ = _conf.getPrefix();
            if (!prefix_.isEmpty()) {
                String nodeName_ = _read.getTagName();
                String newNodeName_;
                if (nodeName_.startsWith(prefix_)) {
                    String suffix_ = _read.getTagName().substring(prefix_.length());
                    newNodeName_ = StringList.concat(replacing_,suffix_);
                } else {
                    newNodeName_ = nodeName_;
                }
                currentNode_ = _doc.createElement(newNodeName_);
                setPrefixedAttributes(_doc, _conf, _read, currentNode_, prefix_);
            } else {
                currentNode_ = _doc.createElement(_read.getTagName());
                setNormalAttributes(_doc, _conf, _read, currentNode_);
            }
        }
        _parent.appendChild(currentNode_);
    }

    private static void setNormalAttributes(Document _doc, Configuration _conf, Element _read, Element _write) {
        NamedNodeMap map_ = _read.getAttributes();
        int nbAttrs_ = map_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            Attr at_ = map_.item(i);
            String name_ = at_.getName();
            String value_ = at_.getValue();
            _write.setAttribute(name_, value_);
        }
    }
    private static void setPrefixedAttributes(Document _doc, Configuration _conf, Element _read, Element _write, String _readPrefix) {
        NamedNodeMap map_ = _read.getAttributes();
        String mainPrefix_ = _conf.getPrefix();
        int nbAttrs_ = map_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            Attr at_ = map_.item(i);
            String name_ = at_.getName();
            String value_ = at_.getValue();
            if (!name_.startsWith(_readPrefix)) {
                _write.setAttribute(name_, value_);
            } else {
                String suffix_ = name_.substring(_readPrefix.length());
                _write.setAttribute(StringList.concat(mainPrefix_, suffix_), value_);
            }
        }
    }

    static void processWhile(Configuration _conf,
            ImportingPage _ip) {
        ReadWriteHtml rw_ = _ip.getReadWrite();
        Element written_ = (Element) rw_.getWrite();
        Element currentWhileNode_ = (Element) rw_.getRead();
        boolean res_ = ExtractCondition.evaluateCondition(currentWhileNode_, _conf, _ip);
        if (_conf.getContext().getException() != null) {
            return;
        }
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
//        Object iterable_ = null;
        String var_ = currentForNode_.getAttribute(ATTRIBUTE_VAR);
        String key_ = currentForNode_.getAttribute(ATTRIBUTE_KEY);
        String value_ = currentForNode_.getAttribute(ATTRIBUTE_VALUE);
        long nbMaxIterations_ = 0;
        boolean iterationNb_ = false;
        long stepValue_ = 0;
        long fromValue_ = 0;
        long realFromValue_ = 0;
        String primLong_ = _conf.getStandards().getAliasPrimLong();
        if (currentForNode_.hasAttribute(ATTRIBUTE_LIST)) {
            String listAttr_ = currentForNode_.getAttribute(ATTRIBUTE_LIST);
            _ip.setProcessingAttribute(ATTRIBUTE_LIST);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            container_ = ElRenderUtil.processEl(listAttr_, 0, _conf).getStruct();
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (container_.isNull()) {
                _conf.getLastPage().addToOffset(listAttr_.length()+1);
                _conf.getContext().setException(new ErrorStruct(_conf, _conf.getStandards().getAliasNullPe()));
                return;
            }
        } else if (currentForNode_.hasAttribute(ATTRIBUTE_MAP)) {
            map_ = true;
            String mapAttr_ = currentForNode_.getAttribute(ATTRIBUTE_MAP);
            _ip.setProcessingAttribute(ATTRIBUTE_MAP);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            container_ = ElRenderUtil.processEl(mapAttr_, 0, _conf).getStruct();
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (container_.isNull()) {
                _conf.getContext().setException(new ErrorStruct(_conf, _conf.getStandards().getAliasNullPe()));
                return;
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
            Argument argFrom_ = ElRenderUtil.processEl(from_, 0, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (!argFrom_.isIntegerType(_conf.getContext())) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(argFrom_.getObjectClassName(_conf.getContext()));
                mapping_.setParam(_conf.getStandards().getAliasPrimLong());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(cast_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return;
            }
            _ip.setProcessingAttribute(ATTRIBUTE_TO);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Argument argTo_ = ElRenderUtil.processEl(to_, 0, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (!argTo_.isIntegerType(_conf.getContext())) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(argTo_.getObjectClassName(_conf.getContext()));
                mapping_.setParam(_conf.getStandards().getAliasPrimLong());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(cast_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return;
            }
            _ip.setProcessingAttribute(ATTRIBUTE_STEP);
            _ip.setLookForAttrValue(true);
            _ip.setOffset(0);
            Argument argStep_ = ElRenderUtil.processEl(step_, 0, _conf);
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (!argStep_.isIntegerType(_conf.getContext())) {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(argStep_.getObjectClassName(_conf.getContext()));
                mapping_.setParam(_conf.getStandards().getAliasPrimLong());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(_conf.getCurrentFileName());
                cast_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(cast_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return;
            }
            realFromValue_ = argFrom_.getLong();
            fromValue_ = ((NumberStruct)PrimitiveTypeUtil.convert(primLong_, realFromValue_, _conf.getContext())).getInstance().longValue();
            long toValue_ = ((NumberStruct)PrimitiveTypeUtil.convert(primLong_, argTo_.getLong(), _conf.getContext())).getInstance().longValue();
            stepValue_ = ((NumberStruct)PrimitiveTypeUtil.convert(primLong_, argStep_.getLong(), _conf.getContext())).getInstance().longValue();
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
        } else if (container_ instanceof ArrayStruct) {
            ArrayStruct a_ = (ArrayStruct) container_;
            length_ = a_.getInstance().length;
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
                res_.setFinished(true);
            }
        } else {
            if (map_) {
                Struct str_ = ExtractObject.entryList(_conf, 0, container_);
                if (_conf.getContext().getException() != null) {
                    return;
                }
                itStr_ = ExtractObject.iterator(_conf, str_);
            } else {
                itStr_ = ExtractObject.iterator(_conf, container_);
            }
            if (_conf.getContext().getException() != null) {
                return;
            }
            if (!ExtractObject.hasNext(_conf, itStr_)) {
                finished_ = true;
                res_.setFinished(true);
            }
            if (_conf.getContext().getException() != null) {
                return;
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
        long int_ = 0;
        Struct elt_ = null;
        if (iterationNb_) {
            int_ = realFromValue_;
        } else if (container_.isArray()) {
            IntStruct i_ = new IntStruct(CustList.FIRST_INDEX);
            elt_ = ExecInvokingOperation.getElement(container_, i_, _conf);
        } else {
            elt_ = ExtractObject.next(_conf, itStr_);
        }
        if (_conf.getContext().getException() != null) {
            return;
        }
        String indexClassName_;
        indexClassName_ = currentForNode_.getAttribute(ATTRIBUTE_INDEX_CLASS_NAME);
        if (indexClassName_.isEmpty()) {
            indexClassName_ = _conf.getStandards().getAliasPrimLong();
        }
        if (!indexClassName_.isEmpty()) {
            String rest_ = _conf.resolveCorrectTypeWithoutErrors(indexClassName_, true);
            if (rest_.isEmpty()) {
                UnknownClassName un_ = new UnknownClassName();
                un_.setClassName(indexClassName_);
                un_.setFileName(_conf.getCurrentFileName());
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(un_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return;
            }
            indexClassName_ = rest_;
        }
        String className_;
        if (iterationNb_) {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(ATTRIBUTE_CLASS_NAME);
            if (className_.isEmpty()) {
                className_ = primLong_;
            }
            String rest_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
            if (rest_.isEmpty()) {
                UnknownClassName un_ = new UnknownClassName();
                un_.setClassName(className_);
                un_.setFileName(_conf.getCurrentFileName());
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getClasses().getErrorsDet().add(un_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return;
            }
            className_ = rest_;
            lv_.setClassName(className_);
            lv_.setIndexClassName(indexClassName_);
            lv_.setStruct(PrimitiveTypeUtil.convert(className_, int_, _conf.getContext()));
            lv_.setStep(stepValue_);
            varsLoop_.put(var_, lv_);
        } else if (currentForNode_.hasAttribute(ATTRIBUTE_LIST)) {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(ATTRIBUTE_CLASS_NAME);
            if (!className_.isEmpty()) {
                String rest_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                if (rest_.isEmpty()) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(className_);
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(un_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return;
                }
                className_ = rest_;
            }
            lv_.setClassName(className_);
            lv_.setIndexClassName(indexClassName_);
            lv_.setStruct(elt_);
            lv_.setContainer(container_);
            varsLoop_.put(var_, lv_);
        } else {
            LoopVariable lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(KEY_CLASS_NAME_ATTRIBUTE);
            if (!className_.isEmpty()) {
                String rest_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                if (rest_.isEmpty()) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(className_);
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(un_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return;
                }
                className_ = rest_;
            }
            lv_.setClassName(className_);
            lv_.setIndexClassName(indexClassName_);
            lv_.setStruct(ExtractObject.getKey(_conf, elt_));
            if (_conf.getContext().getException() != null) {
                return;
            }
            lv_.setContainer(container_);
            varsLoop_.put(key_, lv_);
            lv_ = new LoopVariable();
            className_ = currentForNode_.getAttribute(VAR_CLASS_NAME_ATTRIBUTE);
            if (!className_.isEmpty()) {
                String rest_ = _conf.resolveCorrectTypeWithoutErrors(className_, true);
                if (rest_.isEmpty()) {
                    UnknownClassName un_ = new UnknownClassName();
                    un_.setClassName(className_);
                    un_.setFileName(_conf.getCurrentFileName());
                    un_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getClasses().getErrorsDet().add(un_);
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return;
                }
                className_ = rest_;
            }
            lv_.setClassName(className_);
            lv_.setIndexClassName(indexClassName_);
            lv_.setStruct(ExtractObject.getValue(_conf, elt_));
            if (_conf.getContext().getException() != null) {
                return;
            }
            lv_.setContainer(container_);
            varsLoop_.put(value_, lv_);
        }
    }

    private static ParentElement getParentOfLastNode(Configuration _conf, Node _node, boolean _child) {
        Node n_ = _node.getFirstChild();
        if (n_ != null && _child && !StringList.quickEq(((Element) _node).getTagName(), TEXT_AREA)) {
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
            BlockHtml bl_ = ip_.getLastStack();
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
        if (StringList.quickEq(l_.getReadNode().getTagName(),StringList.concat(prefix_,TAG_DO))) {
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
        if (StringList.quickEq(l_.getReadNode().getTagName(),StringList.concat(prefix_,FOR_BLOCK_TAG))) {
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
        return _conf.getPrefix();
    }
    static Struct getBean(Configuration _conf, String _beanName) {
        return _conf.getBuiltBeans().getVal(_beanName);
    }
    private static int getTabWidth(Configuration _conf) {
        return _conf.getTabWidth();
    }
}
