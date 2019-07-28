package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.exec.*;
import code.formathtml.stacks.RendParentElement;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.*;
import code.sml.*;
import code.util.*;

public abstract class RendBlock {
    static final String TAG_PARAM = "param";
    static final String ATTRIBUTE_VALUE_SUBMIT = "message";
    static final String ATTRIBUTE_VALUE = "value";
    static final String ATTRIBUTE_QUOTED = "quoted";
    static final String ATTRIBUTE_ESCAPED = "escaped";
    static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";
    static final String ATTRIBUTE_CLASS_NAME = "className";
    static final String ATTRIBUTE_CONVERT = "convert";
    static final String ATTRIBUTE_CONVERT_VALUE = "convertValue";
    static final String ATTRIBUTE_INDEX_CLASS_NAME = "indexClassName";
    static final String ATTRIBUTE_FROM = "from";
    static final String ATTRIBUTE_INIT = "init";
    static final String ATTRIBUTE_STEP = "step";
    static final String ATTRIBUTE_LABEL = "label";
    static final String ATTRIBUTE_NAME = "name";
    static final String DEFAULT_ATTRIBUTE = "default";
    static final String ATTRIBUTE_PREPARE_BEAN = "prepare";
    static final String ATTRIBUTE_FORM = "form";
    static final String ATTRIBUTE_LIST = "list";
    static final String ATTRIBUTE_MAP = "map";
    static final String ATTRIBUTE_ID = "id";
    static final String ATTRIBUTE_GROUP_ID = "groupId";
    static final String ATTRIBUTE_MULTIPLE = "multiple";
    static final String ATTRIBUTE_KEY = "key";
    static final String ATTRIBUTE_VAR = "var";
    static final String ATTRIBUTE_HREF = "href";
    static final String ATTRIBUTE_COMMAND = "command";
    static final String ATTRIBUTE_ACTION = "action";
    static final String ATTRIBUTE_TO = "to";
    static final String ATTRIBUTE_EQ = "eq";
    static final String TAG_OPTION = "option";
    static final String SELECTED = "selected";
    static final String VAR_METHOD = "varMethod";
    static final String BEAN_ATTRIBUTE = "bean";
    static final String ATTRIBUTE_VALUE_CHANGE_EVENT = "valueChangeEvent";
    static final String CHECKED = "checked";
    static final String ATTRIBUTE_CONDITION = "condition";
    static final String KEY_CLASS_NAME_ATTRIBUTE = "keyClassName";
    static final String VAR_CLASS_NAME_ATTRIBUTE = "varClassName";
    static final String ATTRIBUTE_TYPE = "type";
    static final String CALL_METHOD = "$";
    static final String COMMA = ",";
    static final String SUBMIT_TYPE = "submit";
    static final String BODY_TAG = "body";
    static final String INPUT_TAG = "input";
    static final String EMPTY_STRING = "";
    static final char RIGHT_EL = '}';
    static final char LEFT_EL = '{';
    static final char QUOTE = 39;
    static final String TMP_BLOCK_TAG = "tmp";
    static final String LT_END_TAG = "</";
    static final char GT_TAG = '>';
    static final char LT_BEGIN_TAG = '<';
    static final String TAG_A = "a";
    static final String NUMBER_FORM = "n-f";
    static final String NUMBER_ANCHOR = "n-a";
    static final String NUMBER_INPUT = "n-i";
    static final String DOT = ".";
    static final String TMP_LOC = "tmpLoc";

    static final String CHECKBOX = "checkbox";

    static final String TEXT = "text";

    static final String RANGE = "range";

    static final String RADIO = "radio";

    static final String NUMBER = "number";
    static final String TEXT_AREA = "textarea";
    static final String SELECT_TAG = "select";
    static final String ATTRIBUTE_VALIDATOR = "validator";
    static final String ATTRIBUTE_VAR_VALUE = "varValue";
    private static final String FOR_BLOCK_TAG = "for";
    private static final String WHILE_BLOCK_TAG = "while";
    private static final String ELSE_BLOCK_TAG = "else";
    private static final String MESSAGE_BLOCK_TAG = "message";
    private static final String IMPORT_BLOCK_TAG = "import";
    private static final String PACKAGE_BLOCK_TAG = "package";
    private static final String CLASS_BLOCK_TAG = "class";
    private static final String FIELD_BLOCK_TAG = "field";
    private static final String SUBMIT_BLOCK_TAG = "submit";
    private static final String FORM_BLOCK_TAG = "form";
    private static final String SET_BLOCK_TAG = "set";
    private static final String CONTINUE_TAG = "continue";
    private static final String BREAK_TAG = "break";
    private static final String RETURN_TAG = "return";
    private static final String TRY_TAG = "try";
    private static final String CATCH_TAG = "catch";
    private static final String THROW_TAG = "throw";
    private static final String TAG_FINALLY = "finally";
    private static final String TAG_SWITCH = "switch";
    private static final String TAG_CASE = "case";
    private static final String TAG_DEFAULT = "default";
    private static final String TAG_DO = "do";
    private static final String IF_BLOCK_TAG = "if";
    private static final String ELSE_IF_BLOCK_TAG = "elseif";
    private static final char END_ESCAPED = ';';
    private static final char ENCODED = '&';
    private static final char EQUALS = '=';
    private static final char QUOT = 34;
    private static final char APOS = 39;
    private RendParentBlock parent;

    private RendBlock nextSibling;

    private RendBlock previousSibling;

    private OffsetsBlock offset;

    private StringMap<IntTreeMap<Integer>> escapedChars;

    RendBlock(OffsetsBlock _offset) {
        offset = _offset;
    }
    protected final void setParent(RendParentBlock _b) {
        parent = _b;
    }

    public static RendDocumentBlock newRendDocumentBlock(Configuration _conf, String _prefix, Document _doc, String _docText) {
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        int indexGlobal_ = _docText.indexOf(LT_BEGIN_TAG)+1;
        RendDocumentBlock out_ = new RendDocumentBlock(documentElement_,new OffsetsBlock());
        RendBlock curWrite_ = newRendBlockEsc(indexGlobal_,out_, _conf, _prefix, curNode_,_docText);
        out_.appendChild(curWrite_);
        while (curWrite_ != null) {
            MutableNode firstChild_ = curNode_.getFirstChild();
            if (firstChild_ != null) {
                indexGlobal_ = indexOfBeginNode(firstChild_, _docText, indexGlobal_);
                RendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,(RendParentBlock) curWrite_, _conf, _prefix, firstChild_,_docText);
                ((RendParentBlock) curWrite_).appendChild(rendBlock_);
                curWrite_ = rendBlock_;
                curNode_ = firstChild_;
                continue;
            }
            while (true) {
                MutableNode nextSibling_ = curNode_.getNextSibling();
                RendParentBlock par_ = curWrite_.getParent();
                if (nextSibling_ != null) {
                    indexGlobal_ = indexOfBeginNode(nextSibling_, _docText, indexGlobal_);
                    RendBlock rendBlock_ = newRendBlockEsc(indexGlobal_,par_, _conf, _prefix, nextSibling_,_docText);
                    par_.appendChild(rendBlock_);
                    curWrite_ = rendBlock_;
                    curNode_ = nextSibling_;
                    break;
                }
                Element parentNode_ = curNode_.getParentNode();
                if (parentNode_ == null) {
                    curWrite_ = null;
                    break;
                }
                if (parentNode_ == documentElement_) {
                    curWrite_ = null;
                    break;
                }
                curWrite_ = par_;
                curNode_ = parentNode_;
            }
        }
        return out_;
    }

    private static IntTreeMap< Integer> getIndexesSpecChars(String _html, boolean _realAttr, AttributePart _att, int _beginNode) {
        int begin_ = _att.getBegin();
        int end_ = _att.getEnd();
        int i_ = begin_;
        int delta_ = 0;
        if (_realAttr) {
            delta_ = begin_ - _beginNode;
        }
        IntTreeMap< Integer> indexes_;
        indexes_ = new IntTreeMap< Integer>();
        while (i_ < end_) {
            if (_html.charAt(i_) == ENCODED) {
                int beginEscaped_ = i_;
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
        int delimiter_ = -1;
        for (int i = _from; i < _to; i++) {
            char ch_ = _html.charAt(i);
            if (delimiter_ == -1) {
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
                    delimiter_ = -1;
                    continue;
                }
            }
            if (delimiter_ == -1) {
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
        int indexText_ = _html.indexOf(GT_TAG, _from);
        while (_html.charAt(indexText_ + 1) == LT_BEGIN_TAG) {
            indexText_ = _html.indexOf(GT_TAG, indexText_ + 1);
        }
        return indexText_ + 1;
    }
    private static RendBlock newRendBlockEsc(int _begin,RendParentBlock _curParent,Configuration _conf,String _prefix,Node _elt, String _docText) {
        RendBlock bl_ = newRendBlock(_begin, _curParent, _conf, _prefix, _elt, _docText);
        if (_elt instanceof Text) {
            int endHeader_ = _docText.indexOf(LT_BEGIN_TAG, _begin);
            AttributePart attrPart_ = new AttributePart();
            attrPart_.setBegin(_begin);
            attrPart_.setEnd(endHeader_);
            IntTreeMap<Integer> esc_ = getIndexesSpecChars(_docText, false, attrPart_, _begin);
            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
            infos_.addEntry(EMPTY_STRING,esc_);
            bl_.escapedChars = infos_;
        } else {
            Element elt_ = (Element) _elt;
            String tagName_ = elt_.getTagName();
            int endHeader_ = _docText.indexOf(GT_TAG, _begin);
            int beginHeader_ = _begin + tagName_.length();
            StringMap<AttributePart> attr_;
            attr_ = getAttributes(_docText, beginHeader_, endHeader_);
            StringMap<IntTreeMap<Integer>> infos_ = new StringMap<IntTreeMap<Integer>>();
            for (EntryCust<String, AttributePart> e: attr_.entryList()) {
                infos_.put(e.getKey(), getIndexesSpecChars(_docText, true, e.getValue(), _begin));
            }
            bl_.escapedChars = infos_;
        }
        return bl_;
    }
    private static RendBlock newRendBlock(int _begin,RendParentBlock _curParent,Configuration _conf,String _prefix,Node _elt, String _docText) {
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            if (t_.getTextContent().trim().isEmpty()) {
                return new RendEmptyText(new OffsetStringInfo(_begin,t_.getTextContent()),new OffsetsBlock(_begin,_begin));
            }
            return new RendText(new OffsetStringInfo(_begin,t_.getTextContent()),new OffsetsBlock(_begin,_begin));
        }
        Element elt_ = (Element) _elt;
        String tagName_ = elt_.getTagName();
        int endHeader_ = _docText.indexOf(GT_TAG, _begin);
        int beginHeader_ = _begin + tagName_.length();
        StringMap<AttributePart> attr_;
        attr_ = getAttributes(_docText, beginHeader_, endHeader_);
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,FOR_BLOCK_TAG))) {
            if (elt_.hasAttribute(ATTRIBUTE_LIST)) {
                return new RendForEachLoop(_conf,
                        newOffsetStringInfo(elt_,ATTRIBUTE_CLASS_NAME, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_VAR, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_LIST, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_INDEX_CLASS_NAME, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),
                        new OffsetsBlock(_begin,_begin)
                );
            }
            if (elt_.hasAttribute(ATTRIBUTE_MAP)) {
                return new RendForEachTable(_conf,
                        newOffsetStringInfo(elt_,KEY_CLASS_NAME_ATTRIBUTE, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_KEY, attr_),
                        newOffsetStringInfo(elt_,VAR_CLASS_NAME_ATTRIBUTE, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_VALUE, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_MAP, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_INDEX_CLASS_NAME, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),
                        new OffsetsBlock(_begin,_begin)
                );
            }
            if (elt_.hasAttribute(ATTRIBUTE_VAR)) {
                return new RendForIterativeLoop(_conf,
                        newOffsetStringInfo(elt_,ATTRIBUTE_CLASS_NAME, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_VAR, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_FROM, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_TO, attr_),
                        newOffsetBooleanInfo(elt_,ATTRIBUTE_EQ),
                        newOffsetStringInfo(elt_,ATTRIBUTE_STEP, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_INDEX_CLASS_NAME, attr_),
                        newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),
                        new OffsetsBlock(_begin,_begin)
                        );
            }
            return new RendForMutableIterativeLoop(_conf,
                    newOffsetStringInfo(elt_,ATTRIBUTE_CLASS_NAME, attr_),
                    newOffsetStringInfo(elt_,ATTRIBUTE_INIT, attr_),
                    newOffsetStringInfo(elt_,ATTRIBUTE_CONDITION, attr_),
                    newOffsetStringInfo(elt_,ATTRIBUTE_STEP, attr_),
                    newOffsetStringInfo(elt_,ATTRIBUTE_INDEX_CLASS_NAME, attr_)
                    ,newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),
                    new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,WHILE_BLOCK_TAG))) {
            MutableNode previousSibling_ = elt_.getPreviousSibling();
            if (previousSibling_ instanceof Text && previousSibling_.getTextContent().trim().isEmpty()) {
                previousSibling_ = previousSibling_.getPreviousSibling();
            }
            if (previousSibling_ instanceof Element && StringList.quickEq(((Element) previousSibling_).getTagName(),StringList.concat(_prefix,TAG_DO))) {
                return new RendDoWhileCondition(newOffsetStringInfo(elt_,ATTRIBUTE_CONDITION, attr_),new OffsetsBlock(_begin,_begin));
            }
            return new RendWhileCondition(newOffsetStringInfo(elt_,ATTRIBUTE_CONDITION, attr_),newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TAG_DO))) {
            return new RendDoBlock(newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,RETURN_TAG))) {
            return new RendReturnMehod(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,BREAK_TAG))) {
            return new RendBreakBlock(newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,CONTINUE_TAG))) {
            return new RendContinueBlock(newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,THROW_TAG))) {
            return new RendThrowing(newOffsetStringInfo(elt_,ATTRIBUTE_VALUE, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,SET_BLOCK_TAG))) {
            if (elt_.hasAttribute(ATTRIBUTE_CLASS_NAME)) {
                _curParent.appendChild(new RendDeclareVariable(newOffsetStringInfo(elt_,ATTRIBUTE_CLASS_NAME, attr_),new OffsetsBlock(_begin,_begin)));
            }
            return new RendLine(newOffsetStringInfo(elt_,ATTRIBUTE_VALUE, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,IF_BLOCK_TAG))) {
            return new RendIfCondition(newOffsetStringInfo(elt_,ATTRIBUTE_CONDITION, attr_),newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,ELSE_IF_BLOCK_TAG))) {
            return new RendElseIfCondition(newOffsetStringInfo(elt_,ATTRIBUTE_CONDITION, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,ELSE_BLOCK_TAG))) {
            return new RendElseCondition(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TRY_TAG))) {
            return new RendTryEval(newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,CATCH_TAG))) {
            if (elt_.hasAttribute(ATTRIBUTE_CLASS_NAME)) {
                return new RendCatchEval(newOffsetStringInfo(elt_,ATTRIBUTE_CLASS_NAME, attr_),newOffsetStringInfo(elt_,ATTRIBUTE_VAR, attr_),new OffsetsBlock(_begin,_begin));
            }
            return new RendNullCatchEval(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TAG_FINALLY))) {
            return new RendFinallyEval(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TAG_SWITCH))) {
            return new RendSwitchBlock(newOffsetStringInfo(elt_,ATTRIBUTE_VALUE, attr_),newOffsetStringInfo(elt_,ATTRIBUTE_LABEL, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TAG_CASE))) {
            return new RendCaseCondition(newOffsetStringInfo(elt_,ATTRIBUTE_VALUE, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TAG_DEFAULT))) {
            return new RendDefaultCondition(new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,IMPORT_BLOCK_TAG))) {
            return new RendImport(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,SUBMIT_BLOCK_TAG))) {
            return new RendSubmit(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,TAG_A)) {
            return new RendAnchor(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,PACKAGE_BLOCK_TAG))) {
            return new RendPackage(newOffsetStringInfo(elt_,ATTRIBUTE_NAME, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,CLASS_BLOCK_TAG))) {
            return new RendClass(newOffsetStringInfo(elt_,ATTRIBUTE_NAME, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,FIELD_BLOCK_TAG))) {
            return new RendField(newOffsetStringInfo(elt_,ATTRIBUTE_PREPARE_BEAN, attr_),new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,MESSAGE_BLOCK_TAG))) {
            return new RendMessage(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,SELECT_TAG))) {
            return new RendSelect(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,FORM_BLOCK_TAG)) {
            return new RendForm(elt_,new OffsetsBlock(_begin,_begin));
        }
        if (StringList.quickEq(tagName_,INPUT_TAG)) {
            if (StringList.quickEq(elt_.getAttribute(ATTRIBUTE_TYPE),RADIO)) {
                return new RendRadio(elt_,new OffsetsBlock(_begin,_begin));
            }
        }
        return new RendStdElement(elt_,new OffsetsBlock(_begin,_begin));
    }

    static String getPre(Configuration _cont, String _value) {
        StringList elts_ = StringList.splitStrings(_value, COMMA);
        String var_ = elts_.first();
        String fileName_ = getProperty(_cont, var_);
        if (fileName_ == null) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_cont.getClasses().getErrorsDet());
            badEl_.setFileName(_cont.getCurrentFileName());
            badEl_.setIndexFile(_cont.getCurrentLocationIndex());
            _cont.getClasses().addError(badEl_);
            return null;
        }
        AnalyzingDoc a_ = _cont.getAnalyzingDoc();
        String language_ = a_.getLanguage();
        StringMap<String> files_ = a_.getFiles();
        String[] resourcesFolder_ = a_.getResourcesFolder();
        String content_ = ExtractFromResources.tryGetContent(_cont, language_, fileName_, files_, resourcesFolder_);
        int index_ = ExtractFromResources.indexCorrectMessages(content_);
        if (index_ >= 0) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_cont.getClasses().getErrorsDet());
            badEl_.setFileName(_cont.getCurrentFileName());
            badEl_.setIndexFile(_cont.getCurrentLocationIndex());
            _cont.getClasses().addError(badEl_);
            return null;
        }
        StringMap<String> messages_ = ExtractFromResources.getMessages(content_);
        String key_ = elts_.last();
        String format_ = ExtractFromResources.getQuickFormat(messages_, key_);
        if (format_ == null) {
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_cont.getClasses().getErrorsDet());
            badEl_.setFileName(_cont.getCurrentFileName());
            badEl_.setIndexFile(_cont.getCurrentLocationIndex());
            _cont.getClasses().addError(badEl_);
            return null;
        }
        return format_;
    }
    protected static void incrAncNb(Configuration _cont, Element _nextEltWrite) {
        if (StringList.quickEq(_nextEltWrite.getTagName(), TAG_A) && (_nextEltWrite.hasAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_COMMAND))|| !_nextEltWrite.getAttribute(ATTRIBUTE_HREF).isEmpty() )) {
            long currentAnchor_ = _cont.getIndexes().getAnchor();
            _nextEltWrite.setAttribute(NUMBER_ANCHOR, String.valueOf(currentAnchor_));
            currentAnchor_++;
            _cont.getIndexes().setAnchor(currentAnchor_);
        }
    }
    private static OffsetStringInfo newOffsetStringInfo(Element _elt, String _key, StringMap<AttributePart> _attr) {
        AttributePart val_ = _attr.getVal(_key);
        int begin_;
        if (val_ == null) {
            begin_ = 0;
        } else {
            begin_ = val_.getBegin();
        }
        return new OffsetStringInfo(begin_,_elt.getAttribute(_key));
    }

    public abstract void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc);
    private static OffsetBooleanInfo newOffsetBooleanInfo(Element _elt, String _key) {
        return new OffsetBooleanInfo(0,_elt.hasAttribute(_key));
    }
    protected static void tryBuildExpressionLanguage(RendBlock _block, Configuration _cont,RendDocumentBlock _doc) {
        _block.buildExpressionLanguage(_cont,_doc);
    }

    static void appendChild(Document _doc, Node _parent, Element _read) {
        Element currentNode_;
        if (_parent instanceof Document) {
            currentNode_ = _doc.createElement(_read.getTagName());
            setNormalAttributes(_read, currentNode_);
            ((Document)_parent).appendChild(currentNode_);
            return;
        }
        currentNode_ = _doc.createElement(_read.getTagName());
        setNormalAttributes(_read, currentNode_);
        ((MutableNode)_parent).appendChild(currentNode_);
    }

    private static void setNormalAttributes(Element _read, Element _write) {
        NamedNodeMap map_ = _read.getAttributes();
        int nbAttrs_ = map_.getLength();
        for (int i = 0; i < nbAttrs_; i++) {
            Attr at_ = map_.item(i);
            String name_ = at_.getName();
            String value_ = at_.getValue();
            _write.setAttribute(name_, value_);
        }
    }

    protected static Argument iteratorMultTable(Struct _arg, Configuration _cont) {
        String locName_ = _cont.getAdvStandards().getIteratorTableVarCust();
        BeanLgNames stds_ = _cont.getAdvStandards();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(_arg, _cont.getContext()));
        locVar_.setStruct(_arg);
        _cont.getLastPage().getInternVars().put(locName_, locVar_);
        Argument arg_ = ElRenderUtil.calculateReuse(stds_.getExpsIteratorTableCust(), _cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return arg_;
        }
        if (stds_ instanceof BeanNatLgNames) {
            arg_=iterator(arg_.getStruct(),_cont);
        }
        return arg_;
    }
    protected static Argument hasNextPair(Struct _arg,Configuration _conf) {
        String locName_ = _conf.getAdvStandards().getHasNextPairVarCust();
        BeanLgNames stds_ = _conf.getAdvStandards();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(_arg, _conf.getContext()));
        locVar_.setStruct(_arg);
        _conf.getLastPage().getInternVars().put(locName_, locVar_);
        return ElRenderUtil.calculateReuse(stds_.getExpsHasNextPairCust(),_conf);
    }
    protected static Argument nextPair(Struct _arg,Configuration _conf) {
        String locName_ = _conf.getAdvStandards().getNextPairVarCust();
        BeanLgNames stds_ = _conf.getAdvStandards();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(_arg, _conf.getContext()));
        locVar_.setStruct(_arg);
        _conf.getLastPage().getInternVars().put(locName_, locVar_);
        return ElRenderUtil.calculateReuse(stds_.getExpsNextPairCust(), _conf);
    }
    protected static Argument first(Struct _arg,Configuration _conf) {
        String locName_ = _conf.getAdvStandards().getFirstVarCust();
        BeanLgNames stds_ = _conf.getAdvStandards();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(_arg, _conf.getContext()));
        locVar_.setStruct(_arg);
        _conf.getLastPage().getInternVars().put(locName_, locVar_);
        return ElRenderUtil.calculateReuse(stds_.getExpsFirstCust(),_conf);
    }
    protected static Argument second(Struct _arg,Configuration _conf) {
        String locName_ = _conf.getAdvStandards().getSecondVarCust();
        BeanLgNames stds_ = _conf.getAdvStandards();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(_arg, _conf.getContextEl()));
        locVar_.setStruct(_arg);
        _conf.getLastPage().getInternVars().put(locName_, locVar_);
        return ElRenderUtil.calculateReuse(stds_.getExpsSecondCust(),_conf);
    }
    protected static Argument iterator(Struct _arg,Configuration _cont) {
        String locName_ = _cont.getAdvStandards().getIteratorVar();
        BeanLgNames stds_ = _cont.getAdvStandards();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(_arg, _cont.getContext()));
        locVar_.setStruct(_arg);
        _cont.getLastPage().getInternVars().put(locName_, locVar_);
        return ElRenderUtil.calculateReuse(stds_.getExpsIterator(),_cont);
    }
    protected static Argument hasNext(Struct _arg,Configuration _cont) {
        String locName_ = _cont.getAdvStandards().getHasNextVar();
        BeanLgNames stds_ = _cont.getAdvStandards();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(_arg, _cont.getContext()));
        locVar_.setStruct(_arg);
        _cont.getLastPage().getInternVars().put(locName_, locVar_);
        return ElRenderUtil.calculateReuse(stds_.getExpsHasNext(),_cont);
    }
    protected static Argument next(Struct _arg,Configuration _cont) {
        String locName_ = _cont.getAdvStandards().getNextVar();
        BeanLgNames stds_ = _cont.getAdvStandards();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(_arg, _cont.getContext()));
        locVar_.setStruct(_arg);
        _cont.getLastPage().getInternVars().put(locName_, locVar_);
        return ElRenderUtil.calculateReuse(stds_.getExpsNext(),_cont);
    }
    protected static void beforeDisplaying(Struct _arg,Configuration _cont) {
        if (_arg == null) {
            return;
        }
        BeanLgNames stds_ = _cont.getAdvStandards();
        stds_.beforeDisplaying(_arg,_cont);
    }

    static String getStringKey(Configuration _conf, Struct _instance) {
        return _conf.getAdvStandards().getStringKey(_conf,_instance);
    }

    protected static Argument fetchName(Configuration _cont, Element _read, Element _write, FieldUpdates _f) {
        String name_ = _read.getAttribute(ATTRIBUTE_NAME);
        if (name_.isEmpty()) {
            return Argument.createVoid();
        }
        Struct obj_;
        Struct currentField_;
        long index_ = -1;
        long found_ = -1;
        CustList<RendDynOperationNode> opsRead_ = _f.getOpsRead();
        CustList<RendDynOperationNode> opsWrite_ = _f.getOpsWrite();
        ClassField idField_ = _f.getIdField();
        String varName_ = _f.getVarName();
        IdMap<RendDynOperationNode, ArgumentsPair> args_ = ElRenderUtil.getAllArgs(opsRead_, _cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return Argument.createVoid();
        }
        RendDynOperationNode root_ = args_.lastKey();
        RendDynOperationNode res_;
        if (root_ instanceof RendIdOperation) {
            res_ = RendAffectationOperation.getIdOp((RendMethodOperation) root_);
        } else {
            res_ = root_;
        }
        RendSettableElResult settable_ = RendAffectationOperation.castDottedTo(res_);
        ArgumentsPair pair_ = args_.getValue(((RendSettableFieldOperation) settable_).getOrder());
        if (((RendSettableFieldOperation) settable_).isIntermediateDottedOperation()) {
            obj_ = pair_.getPreviousArgument().getStruct();
        } else {
            obj_ = _cont.getLastPage().getGlobalArgument().getStruct();
        }
        for (EntryCust<Long, NodeContainer> e: _cont.getContainers().entryList()) {
            if (!e.getValue().getStruct().sameReference(obj_)) {
                continue;
            }
            if (!e.getValue().getIdField().eq(idField_)) {
                continue;
            }
            found_ = e.getKey();
            break;
        }
        Argument arg_ = pair_.getArgument();
        currentField_ = arg_.getStruct();
        if (found_ == -1) {
            long currentInput_ = _cont.getIndexes().getInput();
            NodeContainer nodeCont_ = new NodeContainer();
            nodeCont_.setEnabled(true);
            nodeCont_.setIdField(idField_);
            nodeCont_.setIndex(index_);
            nodeCont_.setTypedStruct(currentField_);
            nodeCont_.setBeanName(_cont.getLastPage().getBeanName());
            nodeCont_.setStruct(obj_);
            StringList strings_ = StringList.splitInTwo(varName_, varName_.indexOf(','));
            nodeCont_.setVarPrevName(StringList.removeChars(strings_.first(),','));
            nodeCont_.setVarName(StringList.removeChars(strings_.last(),','));
            nodeCont_.setOpsWrite(opsWrite_);
            nodeCont_.setOpsConvert(_f.getOpsConverter());
            nodeCont_.setVarNameConvert(_f.getVarNameConverter());
            nodeCont_.setArrayConverter(_f.isArrayConverter());
            NodeInformations nodeInfos_ = nodeCont_.getNodeInformation();
            String id_ = _write.getAttribute(ATTRIBUTE_ID);
            if (id_.isEmpty()) {
                id_ = _write.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_GROUP_ID));
            }
            String class_ = _cont.getAdvStandards().getInputClass(_write,_cont);
            nodeInfos_.setValidator(_write.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VALIDATOR)));
            nodeInfos_.setId(id_);
            nodeInfos_.setInputClass(class_);
            nodeInfos_.setVarMethod(_write.getAttribute(StringList.concat(_cont.getPrefix(),VAR_METHOD)));
            nodeInfos_.setChanging(_write.getAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VALUE_CHANGE_EVENT)));
            _cont.getContainers().put(currentInput_, nodeCont_);
            _cont.getIndexes().setNb(currentInput_);
            currentInput_++;
            _cont.getIndexes().setInput(currentInput_);
        } else {
            _cont.getIndexes().setNb(found_);
        }
        _write.setAttribute(NUMBER_INPUT, String.valueOf(_cont.getIndexes().getNb()));
//        attributesNames_.removeAllString(NUMBER_INPUT);
        _write.setAttribute(ATTRIBUTE_NAME, StringList.concat(_cont.getLastPage().getBeanName(),DOT,name_));
        return arg_;
    }
    static CustList<RendDynOperationNode> reduceList(CustList<RendDynOperationNode> _list) {
        if (_list.isEmpty()) {
            return _list;
        }
        return ElRenderUtil.getReducedNodes(_list.last());
    }

    protected static void fetchValue(Configuration _cont, Element _read, Element _write, CustList<RendDynOperationNode> _ops) {
//        _conf.getLastPage().setProcessingAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VAR_VALUE));
//        _conf.getLastPage().setLookForAttrValue(true);
//        _conf.getLastPage().setOffset(0);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return;
        }
        String name_ = _read.getAttribute(ATTRIBUTE_NAME);
        if (name_.isEmpty()) {
            return;
        }
        if (StringList.quickEq(_read.getTagName(),INPUT_TAG)) {
            Argument o_ = ElRenderUtil.calculateReuse(_ops,_cont);
            if (_cont.getContext().getException() != null) {
                return;
            }
            if (o_.getStruct() == NullStruct.NULL_VALUE) {
                _write.setAttribute(ATTRIBUTE_VALUE, EMPTY_STRING);
            } else if (o_.getStruct() instanceof BooleanStruct) {
                if (((BooleanStruct) o_.getStruct()).getInstance()) {
                    _write.setAttribute(CHECKED, CHECKED);
                } else {
                    _write.removeAttribute(CHECKED);
                }
            } else {
                _write.setAttribute(ATTRIBUTE_VALUE, _cont.getAdvStandards().processString(o_,_cont));
            }
        }
        if (StringList.quickEq(_read.getTagName(),TEXT_AREA)) {
            Argument o_ = ElRenderUtil.calculateReuse(_ops,_cont);
            if (_cont.getContext().getException() != null) {
                return;
            }
            if (o_.getStruct() == NullStruct.NULL_VALUE) {
                o_.setStruct(new StringStruct(EMPTY_STRING));
            }
            Document doc_ = _write.getOwnerDocument();
            Text text_ = doc_.createTextNode(_cont.getAdvStandards().processString(o_,_cont));
            _write.appendChild(text_);
        }
        _write.removeAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VAR_VALUE));
    }
    protected static String getProperty(Configuration _conf, String _key) {
        return _conf.getProperties().getVal(_key);
    }
    static String escapeParam(Configuration _conf, Argument _arg) {
        String str_ = _conf.getAdvStandards().processString(_arg,_conf);
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), StringList.concat(quote_,String.valueOf(LEFT_EL),quote_));
        rep_.put(String.valueOf(RIGHT_EL), StringList.concat(quote_,String.valueOf(RIGHT_EL),quote_));
        rep_.put(String.valueOf(QUOTE), StringList.concat(quote_,quote_));
        return StringList.replaceMultiple(str_, rep_);
    }
    protected static String escapeParam(String _arg) {
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), StringList.concat(quote_,String.valueOf(LEFT_EL),quote_));
        rep_.put(String.valueOf(RIGHT_EL), StringList.concat(quote_,String.valueOf(RIGHT_EL),quote_));
        rep_.put(String.valueOf(QUOTE), StringList.concat(quote_,quote_));
        return StringList.replaceMultiple(_arg, rep_);
    }

    public static CustList<RendBlock> getDirectChildren(RendBlock _block) {
        CustList<RendBlock> l_ = new CustList<RendBlock>();
        RendBlock child_ = _block.getFirstChild();
        while (child_ != null) {
            l_.add(child_);
            child_ = child_.getNextSibling();
        }
        return l_;
    }
    public final OffsetsBlock getOffset() {
        return offset;
    }

    public final RendBlock getPreviousSibling() {
        return previousSibling;
    }
    public abstract RendBlock getFirstChild();

    public final RendBlock getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(RendBlock _nextSibling) {
        nextSibling = _nextSibling;
    }
    final void setPreviousSibling(RendBlock _previousSibling) {
        previousSibling = _previousSibling;
    }

    public final RendParentBlock getParent() {
        return parent;
    }

    protected static String lookForVar(Configuration _cont, StringList _varNames) {
        String varLoc_ = TMP_LOC;
        int indexLoc_ = 0;
        while (!_cont.getContext().isNotVar(varLoc_) || StringList.contains(_varNames,varLoc_)) {
            varLoc_ = StringList.concatNbs(varLoc_,indexLoc_);
            indexLoc_++;
        }
        return varLoc_;
    }

    public final void processBlock(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendParentElement parElt_ = getNextBlock(ip_,this);
        if (parElt_ == null) {
            ip_.setNullRendReadWrite();
            return;
        }
        RendParentBlock par_ = parElt_.getElement();
        if (par_ == null) {
            RendReadWrite rw_ = ip_.getRendReadWrite();
            RendBlock n_ = getNextSibling();
            rw_.setRead(n_);
            return;
        }
        par_.removeLocalVars(ip_);
        par_.exitStack(_conf);
    }
    public static RendParentElement getNextBlock(ImportingPage _ip,RendBlock _bl) {
        RendParentElement parElt_;
        RendBlock nextSibling_ = _bl.getNextSibling();
        if (nextSibling_ != null) {
            parElt_ = new RendParentElement(null);
        } else {
            RendParentBlock n_ = _bl.getParent();
            //n_ != null because strictly in class
            if (_ip.hasBlock()) {
                parElt_ =  new RendParentElement(n_);
            } else {
                //directly at the root => last element in the block root
                parElt_ = null;
            }
        }
        return parElt_;
    }

    protected void setEscapedChars(StringMap<IntTreeMap<Integer>> _escapedChars) {
        escapedChars = _escapedChars;
    }

    public StringMap<IntTreeMap<Integer>> getEscapedChars() {
        return escapedChars;
    }
}
