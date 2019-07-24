package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.RendParentElement;
import code.formathtml.util.RendReadWrite;
import code.sml.*;
import code.util.CustList;
import code.util.StringList;

public abstract class RendBlock {
    static final String TAG_PARAM = "param";
    static final String ATTRIBUTE_VALUE_SUBMIT = "message";
    static final String ATTRIBUTE_VALUE = "value";
    static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";
    static final String ATTRIBUTE_CLASS_NAME = "className";
    static final String ATTRIBUTE_INDEX_CLASS_NAME = "indexClassName";
    static final String ATTRIBUTE_FROM = "from";
    static final String ATTRIBUTE_INIT = "init";
    static final String ATTRIBUTE_STEP = "step";
    static final String ATTRIBUTE_LABEL = "label";
    static final String ATTRIBUTE_NAME = "name";
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

    private static final String FOR_BLOCK_TAG = "for";
    private static final String WHILE_BLOCK_TAG = "while";
    private static final String ELSE_BLOCK_TAG = "else";
    private static final String MESSAGE_BLOCK_TAG = "message";
    private static final String IMPORT_BLOCK_TAG = "import";
    private static final String PACKAGE_BLOCK_TAG = "package";
    private static final String CLASS_BLOCK_TAG = "class";
    private static final String FIELD_BLOCK_TAG = "field";
    private static final String TMP_BLOCK_TAG = "tmp";
    private static final String SUBMIT_BLOCK_TAG = "submit";
    private static final String FORM_BLOCK_TAG = "form";
    private static final String TR_BEGIN_BLOCK_TAG = "tr_begin";
    private static final String TR_END_BLOCK_TAG = "tr_end";
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
    private static final String IF_BLOCK_TAG = "if";
    private static final String ELSE_IF_BLOCK_TAG = "elseif";

    private RendParentBlock parent;

    private RendBlock nextSibling;

    private RendBlock previousSibling;

    private OffsetsBlock offset;


    RendBlock(OffsetsBlock _offset) {
        offset = _offset;
    }
    protected final void setParent(RendParentBlock _b) {
        parent = _b;
    }

    public static RendDocumentBlock newRendDocumentBlock(Configuration _conf,String _prefix, Document _doc) {
        Element documentElement_ = _doc.getDocumentElement();
        Node curNode_ = documentElement_;
        RendDocumentBlock out_ = new RendDocumentBlock(documentElement_,new OffsetsBlock());
        RendBlock curWrite_ = newRendBlock(out_, _conf, _prefix, curNode_);
        out_.appendChild(curWrite_);
        while (curWrite_ != null) {
            MutableNode firstChild_ = curNode_.getFirstChild();
            if (firstChild_ != null) {
                RendBlock rendBlock_ = newRendBlock((RendParentBlock) curWrite_, _conf, _prefix, firstChild_);
                ((RendParentBlock) curWrite_).appendChild(rendBlock_);
                curWrite_ = rendBlock_;
                curNode_ = firstChild_;
                continue;
            }
            while (true) {
                MutableNode nextSibling_ = curNode_.getNextSibling();
                RendParentBlock par_ = curWrite_.getParent();
                if (nextSibling_ != null) {
                    RendBlock rendBlock_ = newRendBlock(par_, _conf, _prefix, nextSibling_);
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
    private static RendBlock newRendBlock(RendParentBlock _curParent,Configuration _conf,String _prefix,Node _elt) {
        if (_elt instanceof Text) {
            Text t_ = (Text) _elt;
            if (t_.getTextContent().trim().isEmpty()) {
                return new RendEmptyText(new OffsetStringInfo(0,t_.getTextContent()),new OffsetsBlock());
            }
            return new RendText(new OffsetStringInfo(0,t_.getTextContent()),new OffsetsBlock());
        }
        Element elt_ = (Element) _elt;
        String tagName_ = elt_.getTagName();
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,FOR_BLOCK_TAG))) {
            if (elt_.hasAttribute(ATTRIBUTE_LIST)) {
                return new RendForEachLoop(_conf,
                        newOffsetStringInfo(elt_,ATTRIBUTE_CLASS_NAME),
                        newOffsetStringInfo(elt_,ATTRIBUTE_VAR),
                        newOffsetStringInfo(elt_,ATTRIBUTE_LIST),
                        newOffsetStringInfo(elt_,ATTRIBUTE_INDEX_CLASS_NAME),
                        newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),
                        new OffsetsBlock()
                );
            }
            if (elt_.hasAttribute(ATTRIBUTE_MAP)) {
                return new RendForEachTable(_conf,
                        newOffsetStringInfo(elt_,KEY_CLASS_NAME_ATTRIBUTE),
                        newOffsetStringInfo(elt_,ATTRIBUTE_KEY),
                        newOffsetStringInfo(elt_,VAR_CLASS_NAME_ATTRIBUTE),
                        newOffsetStringInfo(elt_,ATTRIBUTE_VALUE),
                        newOffsetStringInfo(elt_,ATTRIBUTE_MAP),
                        newOffsetStringInfo(elt_,ATTRIBUTE_INDEX_CLASS_NAME),
                        newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),
                        new OffsetsBlock()
                );
            }
            if (elt_.hasAttribute(ATTRIBUTE_VAR)) {
                return new RendForIterativeLoop(_conf,
                        newOffsetStringInfo(elt_,ATTRIBUTE_CLASS_NAME),
                        newOffsetStringInfo(elt_,ATTRIBUTE_VAR),
                        newOffsetStringInfo(elt_,ATTRIBUTE_FROM),
                        newOffsetStringInfo(elt_,ATTRIBUTE_TO),
                        newOffsetBooleanInfo(elt_,ATTRIBUTE_EQ),
                        newOffsetStringInfo(elt_,ATTRIBUTE_STEP),
                        newOffsetStringInfo(elt_,ATTRIBUTE_INDEX_CLASS_NAME),
                        newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),
                        new OffsetsBlock()
                        );
            }
            return new RendForMutableIterativeLoop(_conf,
                    newOffsetStringInfo(elt_,ATTRIBUTE_CLASS_NAME),
                    newOffsetStringInfo(elt_,ATTRIBUTE_INIT),
                    newOffsetStringInfo(elt_,ATTRIBUTE_CONDITION),
                    newOffsetStringInfo(elt_,ATTRIBUTE_STEP),
                    newOffsetStringInfo(elt_,ATTRIBUTE_INDEX_CLASS_NAME)
                    ,newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),
                    new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,WHILE_BLOCK_TAG))) {
            MutableNode previousSibling_ = elt_.getPreviousSibling();
            if (previousSibling_ instanceof Text && previousSibling_.getTextContent().trim().isEmpty()) {
                previousSibling_ = previousSibling_.getPreviousSibling();
            }
            if (previousSibling_ instanceof Element && StringList.quickEq(((Element) previousSibling_).getTagName(),StringList.concat(_prefix,TAG_DO))) {
                return new RendDoWhileCondition(newOffsetStringInfo(elt_,ATTRIBUTE_CONDITION),new OffsetsBlock());
            }
            return new RendWhileCondition(newOffsetStringInfo(elt_,ATTRIBUTE_CONDITION),newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TAG_DO))) {
            return new RendDoBlock(newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,RETURN_TAG))) {
            return new RendReturnMehod(new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,BREAK_TAG))) {
            return new RendBreakBlock(newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,CONTINUE_TAG))) {
            return new RendContinueBlock(newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,THROW_TAG))) {
            return new RendThrowing(newOffsetStringInfo(elt_,ATTRIBUTE_VALUE),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,SET_BLOCK_TAG))) {
            if (elt_.hasAttribute(ATTRIBUTE_CLASS_NAME)) {
                _curParent.appendChild(new RendDeclareVariable(newOffsetStringInfo(elt_,ATTRIBUTE_CLASS_NAME),new OffsetsBlock()));
            }
            return new RendLine(newOffsetStringInfo(elt_,ATTRIBUTE_VALUE),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,IF_BLOCK_TAG))) {
            return new RendIfCondition(newOffsetStringInfo(elt_,ATTRIBUTE_CONDITION),newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,ELSE_IF_BLOCK_TAG))) {
            return new RendElseIfCondition(newOffsetStringInfo(elt_,ATTRIBUTE_CONDITION),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,ELSE_BLOCK_TAG))) {
            return new RendElseCondition(new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TRY_TAG))) {
            return new RendTryEval(newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,CATCH_TAG))) {
            if (elt_.hasAttribute(ATTRIBUTE_CLASS_NAME)) {
                return new RendCatchEval(newOffsetStringInfo(elt_,ATTRIBUTE_CLASS_NAME),newOffsetStringInfo(elt_,ATTRIBUTE_VAR),new OffsetsBlock());
            }
            return new RendNullCatchEval(new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TAG_FINALLY))) {
            return new RendFinallyEval(new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TAG_SWITCH))) {
            return new RendSwitchBlock(newOffsetStringInfo(elt_,ATTRIBUTE_VALUE),newOffsetStringInfo(elt_,ATTRIBUTE_LABEL),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TAG_CASE))) {
            return new RendCaseCondition(newOffsetStringInfo(elt_,ATTRIBUTE_VALUE),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,TAG_DEFAULT))) {
            return new RendDefaultCondition(new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,IMPORT_BLOCK_TAG))) {
            return new RendImport(elt_,new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,SUBMIT_BLOCK_TAG))) {
            return new RendSubmit(elt_,new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,PACKAGE_BLOCK_TAG))) {
            return new RendPackage(newOffsetStringInfo(elt_,ATTRIBUTE_NAME),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,CLASS_BLOCK_TAG))) {
            return new RendClass(newOffsetStringInfo(elt_,ATTRIBUTE_NAME),new OffsetsBlock());
        }
        if (StringList.quickEq(tagName_,StringList.concat(_prefix,FIELD_BLOCK_TAG))) {
            return new RendField(newOffsetStringInfo(elt_,ATTRIBUTE_PREPARE_BEAN),new OffsetsBlock());
        }
        return new RendStdElement(elt_,new OffsetsBlock());
    }
    private static OffsetStringInfo newOffsetStringInfo(Element _elt,String _key) {
        return new OffsetStringInfo(0,_elt.getAttribute(_key));
    }

    public abstract void buildExpressionLanguage(Configuration _cont, RendDocumentBlock _doc);
    private static OffsetBooleanInfo newOffsetBooleanInfo(Element _elt, String _key) {
        return new OffsetBooleanInfo(0,_elt.hasAttribute(_key));
    }
    protected static void tryBuildExpressionLanguage(RendBlock _block, Configuration _cont,RendDocumentBlock _doc) {
        _block.buildExpressionLanguage(_cont,_doc);
    }

    protected static Argument iteratorTable(Struct _arg, Configuration _cont) {
        String locName_ = _cont.getAdvStandards().getIteratorTableVarCust();
        BeanLgNames stds_ = _cont.getAdvStandards();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(_arg, _cont.getContext()));
        locVar_.setStruct(_arg);
        _cont.getLastPage().getInternVars().put(locName_, locVar_);
        return ElRenderUtil.calculateReuse(stds_.getExpsIteratorTableCust(),_cont);
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
        String clName_ = stds_.getStructClassName(_arg, _cont.getContext());
        if (!Templates.isCorrectExecute(clName_,stds_.getAliasBean(),_cont)) {
            return;
        }
        String locName_ = stds_.getBeforeDisplayingVar();
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(clName_);
        locVar_.setStruct(_arg);
        _cont.getLastPage().getInternVars().put(locName_, locVar_);
        ElRenderUtil.calculateReuse(stds_.getExpsBeforeDisplaying(),_cont);
    }
    protected static String getProperty(Configuration _conf, String _key) {
        String prop_ = _conf.getProperties().getVal(_key);
        return prop_;
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

    public boolean accessibleCondition() {
        return true;
    }

    public boolean accessibleForNext() {
        return true;
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
}
