package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.EndCallValue;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.*;
import code.formathtml.stacks.RendParentElement;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.*;
import code.sml.*;
import code.util.*;

public abstract class RendBlock {
    public static final String SPACE = " ";
    static final String RETURN_LINE = "\n";
    static final String CALL_METHOD = "$";
    static final String EMPTY_STRING = "";
    static final char RIGHT_EL = '}';
    static final char LEFT_EL = '{';
    static final char QUOTE = 39;
    static final String TMP_BLOCK_TAG = "tmp";
    static final String LT_END_TAG = "</";
    static final char GT_TAG = '>';
    static final char LT_BEGIN_TAG = '<';

    static final String DOT = ".";

    static final String LEFT_PAR = "(";
    static final String RIGHT_PAR = ")";

    private RendParentBlock parent;

    private RendBlock nextSibling;

    private RendBlock previousSibling;


    private int offsetTrim;

    private StringMap<IntTreeMap<Integer>> escapedChars = new StringMap<IntTreeMap<Integer>>();

    RendBlock(int _offsetTrim) {
        offsetTrim = _offsetTrim;
    }

    public static String getRes(RendDocumentBlock _rend, Configuration _conf, BeanLgNames _stds, ContextEl _ctx) {
        _conf.initForms();
        String beanName_ = _rend.getBeanName();
        Struct bean_ = _conf.getBuiltBeans().getVal(beanName_);
        _conf.setMainBean(bean_);
        _conf.addPage(new ImportingPage());
        beforeDisplaying(bean_,_conf, _stds, _ctx);
        _conf.removeLastPage();
        if (_ctx.callsOrException()) {
            return EMPTY_STRING;
        }
        ImportingPage ip_ = new ImportingPage();
        int tabWidth_ = _conf.getTabWidth();
        ip_.setTabWidth(tabWidth_);
        ip_.setReadUrl(_conf.getCurrentUrl());
        ip_.setBeanName(beanName_);
        ip_.setFile(_rend.getFile());
        ip_.setPrefix(_conf.getPrefix());
        if (bean_ != null) {
            ip_.setGlobalArgumentStruct(bean_, _ctx);
        }
        _conf.addPage(ip_);
        FullDocument doc_ = DocumentBuilder.newXmlDocument(tabWidth_);
        appendChild(doc_, doc_, _rend.getElt());
        RendReadWrite rw_ = new RendReadWrite();
        rw_.setConf(_conf);
        rw_.setRead(_rend.getFirstChild());
        ip_.setRoot(_rend);
        rw_.setWrite(doc_);
        rw_.setDocument(doc_);
        ip_.setRendReadWrite(rw_);
        while (true) {
            EndCallValue res_ = removeCall(_conf);
            if (res_ == EndCallValue.EXIT) {
                break;
            }
            if (res_ == EndCallValue.FORWARD) {
                continue;
            }
            processTags(_conf, _stds, _ctx);
            if (_ctx.callsOrException()) {
                _conf.getRendLocalThrowing().removeBlockFinally(_conf, _stds, _ctx);
            }
            if (_ctx.callsOrException()) {
                break;
            }
        }
        if (_ctx.callsOrException()) {
            return EMPTY_STRING;
        }
        LongMap<LongTreeMap<NodeContainer>> containersMap_ = _conf.getContainersMap();
        LongMap<StringList> formatIdMap_ = _conf.getFormatIdMap();
        _conf.getHtmlPage().setContainers(containersMap_);
        _conf.getHtmlPage().setFormatIdMap(formatIdMap_);
        _conf.getHtmlPage().setCallsExps(_conf.getCallsExps());
        _conf.getHtmlPage().setAnchorsArgs(_conf.getAnchorsArgs());
        _conf.getHtmlPage().setAnchorsVars(_conf.getAnchorsVars());
        _conf.getHtmlPage().setCallsFormExps(_conf.getCallsFormExps());
        _conf.getHtmlPage().setFormsArgs(_conf.getFormsArgs());
        _conf.getHtmlPage().setFormsVars(_conf.getFormsVars());
        _conf.setBeanName(doc_.getDocumentElement().getAttribute(StringList.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrBean())));
        doc_.getDocumentElement().removeAttribute(StringList.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrBean()));
        doc_.getDocumentElement().removeAttribute(StringList.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrAlias()));
        _conf.setDocument(doc_);
        _conf.clearPages();
        return doc_.export();
    }

    private static EndCallValue removeCall(Configuration _context) {
        ImportingPage p_ = _context.getLastPage();
        if (p_.getRendReadWrite() == null) {
            _context.removeLastPage();
            if (_context.getImporting().isEmpty()) {
                return EndCallValue.EXIT;
            }
            return EndCallValue.FORWARD;
        }
        return EndCallValue.NEXT;
    }

    private static void processTags(Configuration _context, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _context.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock en_ = rw_.getRead();
        ip_.setOffset(en_.getOffsetTrim());
        tryProcessEl(_context, _stds, _ctx);
    }

    private static void tryProcessEl(Configuration _context, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage lastPage_ = _context.getLastPage();
        RendReadWrite rw_ = lastPage_.getRendReadWrite();
        RendBlock en_ = rw_.getRead();
        ((RendWithEl)en_).processEl(_context, _stds, _ctx);
    }

    protected final void setParent(RendParentBlock _b) {
        parent = _b;
    }

    protected static void processLink(Configuration _cont, Element _nextWrite, Element _read, StringList _varNames, ExecTextPart _textPart, CustList<RendDynOperationNode> _anc, BeanLgNames _advStandards, ContextEl _ctx) {
        String href_ = _read.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        _cont.getCallsExps().add(_anc);
        _cont.getAnchorsVars().add(_varNames);
        if (!href_.startsWith(CALL_METHOD)) {
            _cont.getAnchorsArgs().add(new StringList());
            if (_nextWrite.hasAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()))) {
                _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrHref(), EMPTY_STRING);
            }
            incrAncNb(_cont, _nextWrite);
            return;
        }
        StringList alt_ = RenderingText.renderAltList(_textPart, _cont, _advStandards, _ctx);
        StringList arg_ = new StringList();
        int len_ = alt_.size();
        for (int i = 1; i < len_; i += 2) {
            arg_.add(alt_.get(i));
        }
        _cont.getAnchorsArgs().add(arg_);
        String render_ = StringList.join(alt_,"");
        if (_ctx.callsOrException()) {
            incrAncNb(_cont, _nextWrite);
            return;
        }
        String beanName_ = _cont.getLastPage().getBeanName();
        _nextWrite.setAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringList.concat(CALL_METHOD,beanName_,DOT,render_));
        _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrHref(), EMPTY_STRING);
        incrAncNb(_cont, _nextWrite);
    }

    protected static void incrAncNb(Configuration _cont, Element _nextEltWrite) {
        if (StringList.quickEq(_nextEltWrite.getTagName(), _cont.getRendKeyWords().getKeyWordAnchor())
                && (_nextEltWrite.hasAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()))
                || !_nextEltWrite.getAttribute(_cont.getRendKeyWords().getAttrHref()).isEmpty() )) {
            long currentAnchor_ = _cont.getIndexes().getAnchor();
            _nextEltWrite.setAttribute(_cont.getRendKeyWords().getAttrNa(), String.valueOf(currentAnchor_));
            currentAnchor_++;
            _cont.getIndexes().setAnchor(currentAnchor_);
        }
    }

    protected static void appendText(String _fileContent, Document _ownerDocument, Element _eltStyle) {
        CustList<Node> chNode_ = _eltStyle.getChildNodes();
        if (chNode_.isEmpty()) {
            Text text_ = _ownerDocument.createTextNode(_fileContent);
            _eltStyle.appendChild(text_);
        } else {
            Text text_ = (Text) chNode_.last();
            text_.appendData(_fileContent);
        }
    }
    static String getCssHref(Configuration _cont,Element _link) {
        if (!StringList.quickEq(_link.getAttribute(_cont.getRendKeyWords().getAttrRel()),_cont.getRendKeyWords().getValueStyle())) {
            return null;
        }
        if (!_link.hasAttribute(_cont.getRendKeyWords().getAttrHref())){
            return null;
        }
        return _link.getAttribute(_cont.getRendKeyWords().getAttrHref());
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

    protected static Argument iteratorMultTable(Struct _arg, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx) {
        return _advStandards.iteratorMultTable(_arg,_cont, _ctx);
    }
    protected static Argument hasNextPair(Struct _arg, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        return _advStandards.hasNextPair(_arg,_conf, _ctx);
    }
    protected static Argument nextPair(Struct _arg, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        return _advStandards.nextPair(_arg,_conf, _ctx);
    }
    protected static Argument first(Struct _arg, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        return _advStandards.first(_arg,_conf, _ctx);
    }
    protected static Argument second(Struct _arg, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        return _advStandards.second(_arg,_conf, _ctx);
    }
    protected static Argument iterator(Struct _arg, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx) {
        return _advStandards.iterator(_arg,_cont, _ctx);
    }
    protected static Argument hasNext(Struct _arg, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx) {
        return _advStandards.hasNext(_arg,_cont, _ctx);
    }
    protected static Argument next(Struct _arg, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx) {
        return _advStandards.next(_arg,_cont, _ctx);
    }
    protected static void beforeDisplaying(Struct _arg, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx) {
        if (_arg == null) {
            return;
        }
        _advStandards.beforeDisplaying(_arg,_cont, _ctx);
    }

    static String getStringKey(Struct _instance, BeanLgNames _advStandards, ContextEl _ctx) {
        return _advStandards.getStringKey(_instance, _ctx);
    }

    protected static Argument fetchName(Configuration _cont, Element _read, Element _write, FieldUpdates _f, BeanLgNames _advStandards, ContextEl _ctx) {
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            return Argument.createVoid();
        }
        CustList<Struct> obj_ = new CustList<Struct>();
        StringList objClasses_ = new StringList();
        long found_ = -1;
        CustList<RendDynOperationNode> opsRead_ = _f.getOpsRead();
        CustList<RendDynOperationNode> opsWrite_ = _f.getOpsWrite();
        String idCl_ = _f.getId();
        String varName_ = _f.getVarName();
        IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(opsRead_, _cont, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return Argument.createVoid();
        }
        RendDynOperationNode root_ = args_.lastKey();
        RendDynOperationNode res_;
        if (root_ instanceof RendIdOperation) {
            res_ = RendAffectationOperation.getIdOp((RendMethodOperation) root_);
        } else {
            res_ = root_;
        }
        CustList<LongTreeMap<NodeContainer>> stack_ = new CustList<LongTreeMap<NodeContainer>>();
        RendDynOperationNode settable_ = RendAffectationOperation.castDottedTo(res_);
        Argument arg_ = Argument.createVoid();
        if (settable_ instanceof RendSettableFieldOperation) {
            stack_ = _cont.getContainersMapStack();
            ArgumentsPair pair_ = args_.getValue(settable_.getOrder());
            if (((RendSettableFieldOperation) settable_).isIntermediateDottedOperation()) {
                obj_ = new CustList<Struct>(Argument.getNullableValue(pair_.getPreviousArgument()).getStruct());
            } else {
                obj_ = new CustList<Struct>(_cont.getLastPage().getGlobalArgument().getStruct());
            }
            objClasses_ = new StringList(NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames()));
            arg_ = Argument.getNullableValue(pair_.getArgument());
        }
        if (settable_ instanceof RendMethodOperation){
            stack_ = _cont.getContainersMapStack();
            ArgumentsPair pair_ = args_.getValue(settable_.getOrder());
            obj_ = new CustList<Struct>(Argument.getNullableValue(pair_.getPreviousArgument()).getStruct());
            objClasses_ = new StringList(NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames()));
            arg_ = Argument.getNullableValue(pair_.getArgument());
            for (RendDynOperationNode r: ((RendMethodOperation) settable_).getChildrenNodes()) {
                pair_ = args_.getValue(r.getOrder());
                obj_.add(Argument.getNullableValue(pair_.getArgument()).getStruct());
                objClasses_.add(NumParsers.getSingleNameOrEmpty(r.getResultClass().getNames()));
            }
        }
        if (stack_.isEmpty()) {
            return arg_;
        }
        for (EntryCust<Long, NodeContainer> e: stack_.last().entryList()) {
            if (!e.getValue().eqObj(obj_)) {
                continue;
            }
            if (!StringList.quickEq(e.getValue().getIdClass(),idCl_)) {
                continue;
            }
            found_ = e.getKey();
            break;
        }
        Struct currentField_ = arg_.getStruct();
        if (found_ == -1) {
            long currentInput_ = _cont.getInputs().last();
            NodeContainer nodeCont_ = new NodeContainer();
            nodeCont_.setIdFieldClass(_f.getIdClass());
            nodeCont_.setIdFieldName(_f.getIdName());
            nodeCont_.setIdClass(idCl_);
            nodeCont_.setTypedStruct(currentField_);
            nodeCont_.setStruct(obj_);
            StringList strings_ = StringList.splitChar(varName_, ',');
            nodeCont_.setVarPrevName(strings_.first());
            nodeCont_.setVarParamName(strings_.mid(1,strings_.size()-2));
            nodeCont_.setVarName(strings_.last());
            nodeCont_.setOpsWrite(opsWrite_);
            nodeCont_.setOpsConvert(_f.getOpsConverter());
            nodeCont_.setObjectClasses(objClasses_);
            nodeCont_.setVarNameConvert(_f.getVarNameConverter());
            nodeCont_.setArrayConverter(_f.isArrayConverter());
            nodeCont_.setBean(_cont.getLastPage().getGlobalArgument().getStruct());
            NodeInformations nodeInfos_ = nodeCont_.getNodeInformation();
            String id_ = _write.getAttribute(_cont.getRendKeyWords().getAttrId());
            if (id_.isEmpty()) {
                id_ = _write.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrGroupId()));
            }
            String class_ = _advStandards.getInputClass(_write,_cont);
            if (class_.isEmpty()) {
                class_ = _f.getClassName();
            }
            nodeInfos_.setValidator(_write.getAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator())));
            nodeInfos_.setId(id_);
            nodeInfos_.setInputClass(class_);
            stack_.last().put(currentInput_, nodeCont_);
            _cont.getIndexes().setNb(currentInput_);
            currentInput_++;
            _cont.getInputs().setLast(currentInput_);
        } else {
            _cont.getIndexes().setNb(found_);
        }
        _write.setAttribute(_cont.getRendKeyWords().getAttrNi(), String.valueOf(_cont.getIndexes().getNb()));
//        attributesNames_.removeAllString(NUMBER_INPUT);
        _write.setAttribute(_cont.getRendKeyWords().getAttrName(), StringList.concat(_cont.getLastPage().getBeanName(),DOT,name_));
        return arg_;
    }

    protected static void fetchValue(Configuration _cont, Element _read, Element _write, CustList<RendDynOperationNode> _ops, String _varNameConv, CustList<RendDynOperationNode> _opsConv, BeanLgNames _advStandards, ContextEl _ctx) {
//        _conf.getLastPage().setProcessingAttribute(StringList.concat(_cont.getPrefix(),ATTRIBUTE_VAR_VALUE));
//        _conf.getLastPage().setLookForAttrValue(true);
//        _conf.getLastPage().setOffset(0);
        if (_ctx.callsOrException()) {
            return;
        }
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            return;
        }
        if (_ops.isEmpty()) {
            return;
        }
        if (StringList.quickEq(_read.getTagName(),_cont.getRendKeyWords().getKeyWordInput())) {
            Argument o_ = RenderExpUtil.calculateReuse(_ops,_cont, _advStandards, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            if (StringList.quickEq(_read.getAttribute(_cont.getRendKeyWords().getAttrType()),_cont.getRendKeyWords().getValueCheckbox())) {
                if (Argument.isTrueValue(o_)) {
                    _write.setAttribute(_cont.getRendKeyWords().getAttrChecked(), _cont.getRendKeyWords().getAttrChecked());
                } else {
                    _write.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
                }
            } else {
                o_ = convertField(_cont,o_,_varNameConv,_opsConv, _advStandards, _ctx);
                if (_ctx.callsOrException()) {
                    return;
                }
                String value_ = _advStandards.processString(o_, _ctx);
                if (_ctx.callsOrException()) {
                    return;
                }
                _write.setAttribute(_cont.getRendKeyWords().getAttrValue(), value_);
            }
        }
        if (StringList.quickEq(_read.getTagName(),_cont.getRendKeyWords().getKeyWordTextarea())) {
            Argument o_ = RenderExpUtil.calculateReuse(_ops,_cont, _advStandards, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            o_ = convertField(_cont,o_,_varNameConv,_opsConv, _advStandards, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            Document doc_ = _write.getOwnerDocument();
            String value_ = _advStandards.processString(o_, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            Text text_ = doc_.createTextNode(value_);
            _write.appendChild(text_);
        }
        _write.removeAttribute(StringList.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()));
    }
    private static Argument convertField(Configuration _cont, Argument _o, String _varNameConv, CustList<RendDynOperationNode> _opsConv, BeanLgNames _advStandards, ContextEl _ctx) {
        Struct o_ = _o.getStruct();
        if (!_opsConv.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(o_, _ctx.getStandards().getContent().getCoreNames().getAliasObject());
            _cont.getLastPage().putLocalVar(_varNameConv, locVar_);
            Argument arg_ = RenderExpUtil.calculateReuse(_opsConv, _cont, _advStandards, _ctx);
            _cont.getLastPage().removeLocalVar(_varNameConv);
            if (_ctx.callsOrException()) {
                return Argument.createVoid();
            }
            o_ = arg_.getStruct();
        }
        if (o_ == NullStruct.NULL_VALUE) {
            o_ = new StringStruct(EMPTY_STRING);
        }
        return new Argument(o_);
    }

    static String escapeParam(Argument _arg, BeanLgNames _advStandards, ContextEl _ctx) {
        String str_ = _advStandards.processString(_arg, _ctx);
        if (_ctx.callsOrException()) {
            return str_;
        }
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), StringList.concat(quote_,String.valueOf(LEFT_EL),quote_));
        rep_.put(String.valueOf(RIGHT_EL), StringList.concat(quote_,String.valueOf(RIGHT_EL),quote_));
        rep_.put(String.valueOf(QUOTE), StringList.concat(quote_,quote_));
        return StringList.replaceMultiple(str_, rep_);
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

    public final int getOffsetTrim() {
        return offsetTrim;
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

    public final void processBlockAndRemove(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        ImportingPage ip_ = _conf.getLastPage();
        ip_.removeRendLastBlock();
        processBlock(_conf, _advStandards, _ctx);
    }
    public final void processBlock(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        ImportingPage ip_ = _conf.getLastPage();
        RendParentElement parElt_ = getNextBlock(ip_,this);
        if (parElt_ == null) {
            ip_.setNullRendReadWrite();
            return;
        }
        RendParentBlock par_ = parElt_.getElement();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (par_ == null) {
            RendBlock n_ = getNextSibling();
            rw_.setRead(n_);
            return;
        }
        if (par_ instanceof RendLoop) {
            par_.removeLocalVars(ip_);
        } else {
            par_.removeAllVars(ip_);
        }
        rw_.setRead(par_);
        par_.exitStack(_conf, _advStandards, _ctx);
    }
    public static RendParentElement getNextBlock(ImportingPage _ip,RendBlock _bl) {
        RendParentElement parElt_;
        RendBlock nextSibling_ = _bl.getNextSibling();
        if (nextSibling_ != null) {
            parElt_ = new RendParentElement(null);
        } else {
            RendParentBlock n_ = _bl.getParent();
            //n_ != null because strictly in class
            if (n_ != _ip.getRoot()) {
                parElt_ =  new RendParentElement(n_);
            } else {
                //directly at the root => last element in the block root
                parElt_ = null;
            }
        }
        return parElt_;
    }

    public void setEscapedChars(StringMap<IntTreeMap<Integer>> _escapedChars) {
        escapedChars = _escapedChars;
    }

    public StringMap<IntTreeMap<Integer>> getEscapedChars() {
        return escapedChars;
    }

}
