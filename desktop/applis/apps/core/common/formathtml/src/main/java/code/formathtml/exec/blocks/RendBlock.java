package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.AdvancedFullStack;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.*;
import code.formathtml.stacks.*;
import code.formathtml.util.*;
import code.sml.*;
import code.util.*;
import code.util.core.StringUtil;

public abstract class RendBlock {
    public static final String SPACE = " ";
    static final String RETURN_LINE = "\n";
    static final String CALL_METHOD = "$";
    static final String EMPTY_STRING = "";
    static final String TMP_BLOCK_TAG = "tmp";
    static final String LT_END_TAG = "</";
    static final char GT_TAG = '>';
    static final char LT_BEGIN_TAG = '<';

    static final String DOT = ".";
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private static final char QUOTE = 39;

    private RendParentBlock parent;

    private RendBlock nextSibling;

    private RendBlock previousSibling;


    private StringMap<IntTreeMap<Integer>> escapedChars = new StringMap<IntTreeMap<Integer>>();

    RendBlock() {
    }

    public static String getRes(RendDocumentBlock _rend, Configuration _conf, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall, String _currentUrl) {
        _rendStackCall.getFormParts().initForms();
        String beanName_ = _rend.getBeanName();
        Struct bean_ = _conf.getBuiltBeans().getVal(beanName_);
        _rendStackCall.setMainBean(bean_);
        _rendStackCall.addPage(new ImportingPage());
        beforeDisplaying(bean_,_conf, _stds, _ctx, _rendStackCall);
        _rendStackCall.removeLastPage();
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return EMPTY_STRING;
        }
        _rendStackCall.getStackCall().setFullStack(new AdvancedFullStack(_ctx, _rendStackCall));
        ImportingPage ip_ = new ImportingPage();
        int tabWidth_ = _conf.getTabWidth();
        ip_.setTabWidth(tabWidth_);
        ip_.setReadUrl(_currentUrl);
        ip_.setBeanName(beanName_);
        ip_.setFile(_rend.getFile());
        if (bean_ != null) {
            ip_.setGlobalArgumentStruct(bean_);
        }
        _rendStackCall.addPage(ip_);
        FullDocument doc_ = DocumentBuilder.newXmlDocument(tabWidth_);
        appendChild(doc_, (Element)null, _rend.getElt());
        RendReadWrite rw_ = new RendReadWrite();
        rw_.setConf(_rendStackCall.getFormParts());
        rw_.setRead(_rend.getFirstChild());
        rw_.setDocument(doc_);
        ip_.setRendReadWrite(rw_);
        while (true) {
            ImportingPage p_ = _rendStackCall.getLastPage();
            RendReadWrite rendReadWrite_ = p_.getRendReadWrite();
            RendBlock read_ = null;
            if (rendReadWrite_ != null) {
                read_ = rendReadWrite_.getRead();
            }
            if (read_ == null) {
                _rendStackCall.removeLastPage();
                if (_rendStackCall.getImporting().isEmpty()) {
                    break;
                }
                continue;
            }
            processTags(_conf, _stds, _ctx, read_, _rendStackCall);
            processGeneException(_ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                break;
            }
        }
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return EMPTY_STRING;
        }
        _rendStackCall.getHtmlPage().set(_rendStackCall.getFormParts());
        _rendStackCall.setBeanName(doc_.getDocumentElement().getAttribute(StringUtil.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrBean())));
        doc_.getDocumentElement().removeAttribute(StringUtil.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrBean()));
        doc_.getDocumentElement().removeAttribute(StringUtil.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrAlias()));
        _rendStackCall.setDocument(doc_);
        _rendStackCall.clearPages();
        return doc_.export();
    }

    private static void processTags(Configuration _context, BeanLgNames _stds, ContextEl _ctx, RendBlock _read, RendStackCall _rendStackCall) {
        tryProcessEl(_context, _stds, _ctx, _read, _rendStackCall);
    }

    private static void tryProcessEl(Configuration _context, BeanLgNames _stds, ContextEl _ctx, RendBlock _read, RendStackCall _rendStackCall) {
        ((RendWithEl) _read).processEl(_context, _stds, _ctx, _rendStackCall);
    }

    protected final void setParent(RendParentBlock _b) {
        parent = _b;
    }

    protected static void processLink(Configuration _cont, Element _nextWrite, Element _read, StringList _varNames, ExecTextPart _textPart, CustList<RendDynOperationNode> _anc, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
        _rendStackCall.getFormParts().getCallsExps().add(_anc);
        _rendStackCall.getFormParts().getAnchorsVars().add(_varNames);
        if (!href_.startsWith(CALL_METHOD)) {
            _rendStackCall.getFormParts().getAnchorsArgs().add(new StringList());
            if (_nextWrite.hasAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()))) {
                _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrHref(), EMPTY_STRING);
            }
            incrAncNb(_cont, _nextWrite, _rendStackCall);
            return;
        }
        StringList alt_ = RenderingText.renderAltList(_textPart, _advStandards, _ctx, _rendStackCall);
        StringList arg_ = new StringList();
        int len_ = alt_.size();
        for (int i = 1; i < len_; i += 2) {
            arg_.add(alt_.get(i));
        }
        _rendStackCall.getFormParts().getAnchorsArgs().add(arg_);
        String render_ = StringUtil.join(alt_,"");
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            incrAncNb(_cont, _nextWrite, _rendStackCall);
            return;
        }
        String beanName_ = _rendStackCall.getLastPage().getBeanName();
        _nextWrite.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(CALL_METHOD,beanName_,DOT,render_));
        _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrHref(), EMPTY_STRING);
        incrAncNb(_cont, _nextWrite, _rendStackCall);
    }

    protected static void incrAncNb(Configuration _cont, Element _nextEltWrite, RendStackCall _rendStackCall) {
        if (StringUtil.quickEq(_nextEltWrite.getTagName(), _cont.getRendKeyWords().getKeyWordAnchor())
                && (_nextEltWrite.hasAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()))
                || !_nextEltWrite.getAttribute(_cont.getRendKeyWords().getAttrHref()).isEmpty() )) {
            long currentAnchor_ = _rendStackCall.getFormParts().getIndexes().getAnchor();
            _nextEltWrite.setAttribute(_cont.getRendKeyWords().getAttrNa(), Long.toString(currentAnchor_));
            currentAnchor_++;
            _rendStackCall.getFormParts().getIndexes().setAnchor(currentAnchor_);
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
        if (!StringUtil.quickEq(_link.getAttribute(_cont.getRendKeyWords().getAttrRel()),_cont.getRendKeyWords().getValueStyle())) {
            return null;
        }
        if (!_link.hasAttribute(_cont.getRendKeyWords().getAttrHref())){
            return null;
        }
        return _link.getAttribute(_cont.getRendKeyWords().getAttrHref());
    }

    static Element appendChild(Document _doc, RendReadWrite _rw, Element _read) {
        return appendChild(_doc,_rw.getWrite(),_read);
    }
    static Element appendChild(Document _doc, Element _parent, Element _read) {
        String tagName_ = _read.getTagName();
        Element currentNode_ = _doc.createElement(tagName_);
        setNormalAttributes(_read, currentNode_);
        simpleAppendChild(_doc, _parent, currentNode_);
        return currentNode_;
    }

    static Element appendChild(Document _doc, RendReadWrite _rw, String _read) {
        Element currentNode_ = _doc.createElement(_read);
        simpleAppendChild(_doc, _rw.getWrite(), currentNode_);
        return currentNode_;
    }

    static void simpleAppendChild(Document _doc, RendReadWrite _rw, Node _currentNode) {
        simpleAppendChild(_doc,_rw.getWrite(),_currentNode);
    }
    static void simpleAppendChild(Document _doc, Element _parent, Node _currentNode) {
        if (_parent == null) {
            _doc.appendChild(_currentNode);
        } else {
            _parent.appendChild(_currentNode);
        }
    }
    public static Element getParentNode(RendReadWrite _rw) {
        Element wr_ = _rw.getWrite();
        return getParentNode(wr_);
    }

    public static Element getParentNode(Element _elt) {
        if (_elt == null) {
            return null;
        }
        return _elt.getParentNode();
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

    protected static Argument iteratorMultTable(Struct _arg, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        return _advStandards.iteratorMultTable(_arg,_cont, _ctx, _rendStackCall);
    }
    protected static Argument hasNextPair(Struct _arg, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        return _advStandards.hasNextPair(_arg,_conf, _ctx, _rendStackCall);
    }
    protected static Argument nextPair(Struct _arg, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        return _advStandards.nextPair(_arg,_conf, _ctx, _rendStackCall);
    }
    protected static Argument first(Struct _arg, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        return _advStandards.first(_arg,_conf, _ctx, _rendStackCall);
    }
    protected static Argument second(Struct _arg, Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        return _advStandards.second(_arg,_conf, _ctx, _rendStackCall);
    }
    protected static Argument iterator(Struct _arg, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        return _advStandards.iteratorList(_arg,_cont, _ctx, _rendStackCall);
    }
    protected static Argument hasNext(Struct _arg, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        return _advStandards.hasNext(_arg,_cont, _ctx, _rendStackCall);
    }
    protected static Argument next(Struct _arg, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        return _advStandards.nextList(_arg,_cont, _ctx, _rendStackCall);
    }
    protected static void beforeDisplaying(Struct _arg, Configuration _cont, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_arg == null) {
            return;
        }
        _advStandards.beforeDisplaying(_arg,_cont, _ctx, _rendStackCall);
    }

    static String getStringKey(Struct _instance, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack) {
        return _advStandards.getStringKey(_instance, _ctx, _rendStack);
    }

    protected static Argument fetchName(Configuration _cont, Element _read, Element _write, FieldUpdates _f, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            return Argument.createVoid();
        }
        CustList<Struct> obj_ = new CustList<Struct>();
        CustList<Struct> allObj_ = new CustList<Struct>();
        CustList<AbstractWrapper> wrap_ = new CustList<AbstractWrapper>();
        StringList objClasses_ = new StringList();
        long found_ = -1;
        CustList<RendDynOperationNode> opsRead_ = _f.getOpsRead();
        CustList<RendDynOperationNode> opsWrite_ = _f.getOpsWrite();
        String idCl_ = _f.getId();
        String varName_ = _f.getVarName();
        IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(opsRead_, _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return Argument.createVoid();
        }
        RendDynOperationNode root_ = args_.lastKey();
        RendDynOperationNode res_;
        if (root_ instanceof RendIdOperation) {
            res_ = RendAbstractAffectOperation.getIdOp((RendMethodOperation) root_);
        } else {
            res_ = root_;
        }
        CustList<LongTreeMap<NodeContainer>> stack_ = new CustList<LongTreeMap<NodeContainer>>();
        RendDynOperationNode settable_ = RendAbstractAffectOperation.castDottedTo(res_);
        Argument arg_ = Argument.createVoid();
        boolean indexer_ = false;
        if (settable_ instanceof RendSettableFieldOperation) {
            stack_ = _rendStackCall.getFormParts().getContainersMapStack();
            ArgumentsPair pair_ = args_.getValue(settable_.getOrder());
            if (((RendSettableFieldOperation) settable_).isIntermediateDottedOperation()) {
                obj_ = new CustList<Struct>(Argument.getNullableValue(pair_.getPreviousArgument()).getStruct());
            } else {
                obj_ = new CustList<Struct>(_rendStackCall.getLastPage().getGlobalArgument().getStruct());
            }
            objClasses_ = new StringList(_f.getVarNames().getVarTypes());
//            objClasses_ = new StringList(NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames()));
            arg_ = Argument.getNullableValue(pair_.getArgument());
            allObj_ = obj_;
        }
        if (settable_ instanceof RendCustArrOperation){
            stack_ = _rendStackCall.getFormParts().getContainersMapStack();
            ArgumentsPair pair_ = args_.getValue(settable_.getOrder());
            Struct instance_ = Argument.getNullableValue(pair_.getPreviousArgument()).getStruct();
            obj_ = new CustList<Struct>(instance_);
            allObj_.add(instance_);
            objClasses_ = new StringList(_f.getVarNames().getVarTypes());
            arg_ = Argument.getNullableValue(pair_.getArgument());
            indexer_ = true;
//            for (Argument a: _rendStackCall.getLastPage().getList().getArguments()) {
//                obj_.add(a.getStruct());
//            }
//            for (String p: _f.getVarNames().getVarTypes()) {
//                objClasses_.add(p);
//            }
//            for (AbstractWrapper a: _rendStackCall.getLastPage().getList().getWrappers()) {
//                wrap_.add(a);
//            }
            ArgumentListCall list_ = ExecInvokingOperation.fectchArgs(((RendCustArrOperation)settable_).getInstFctContent().getLastType(),((RendCustArrOperation)settable_).getInstFctContent().getNaturalVararg(), null,_ctx,_rendStackCall.getStackCall(),((RendCustArrOperation)settable_).buildInfos(args_));
            for (ArgumentWrapper a: list_.getArgumentWrappers()) {
                allObj_.add(a.getValue().getStruct());
                if (a.getWrapper() != null) {
                    wrap_.add(a.getWrapper());
                } else {
                    obj_.add(a.getValue().getStruct());
                }
            }
        } else if (settable_ instanceof RendMethodOperation){
            stack_ = _rendStackCall.getFormParts().getContainersMapStack();
            ArgumentsPair pair_ = args_.getValue(settable_.getOrder());
            obj_ = new CustList<Struct>(Argument.getNullableValue(pair_.getPreviousArgument()).getStruct());
            objClasses_ = new StringList(_f.getVarNames().getVarTypes());
//            objClasses_ = new StringList(NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames()));
            arg_ = Argument.getNullableValue(pair_.getArgument());
            for (RendDynOperationNode r: ((RendMethodOperation) settable_).getChildrenNodes()) {
                pair_ = args_.getValue(r.getOrder());
                obj_.add(Argument.getNullableValue(pair_.getArgument()).getStruct());
//                objClasses_.add(NumParsers.getSingleNameOrEmpty(r.getResultClass().getNames()));
            }
            allObj_ = obj_;
        }
        if (stack_.isEmpty()) {
            return arg_;
        }
        for (EntryCust<Long, NodeContainer> e: stack_.last().entryList()) {
            if (!e.getValue().eqObj(allObj_)) {
                continue;
            }
            if (!StringUtil.quickEq(e.getValue().getIdClass(),idCl_)) {
                continue;
            }
            found_ = e.getKey();
            break;
        }
        Struct currentField_ = arg_.getStruct();
        if (found_ == -1) {
            Longs inputs_ = _rendStackCall.getFormParts().getInputs();
            long currentInput_ = inputs_.last();
            NodeContainer nodeCont_ = new NodeContainer();
            nodeCont_.setIdFieldClass(_f.getIdClass());
            nodeCont_.setIdFieldName(_f.getIdName());
            nodeCont_.setIdClass(idCl_);
            nodeCont_.setTypedStruct(currentField_);
            nodeCont_.setStruct(obj_);
            nodeCont_.setAllObject(allObj_);
            StringList strings_ = StringUtil.splitChar(varName_, ',');
            nodeCont_.setVarPrevName(strings_.first());
            nodeCont_.setVarParamName(_f.getVarNames());
            nodeCont_.setVarName(strings_.last());
            nodeCont_.setOpsWrite(opsWrite_);
            nodeCont_.setOpsConvert(_f.getOpsConverter());
            nodeCont_.setObjectClasses(objClasses_);
            nodeCont_.setWrappers(wrap_);
            nodeCont_.setIndexer(indexer_);
            nodeCont_.setVarNameConvert(_f.getVarNameConverter());
            nodeCont_.setArrayConverter(_f.isArrayConverter());
            nodeCont_.setBean(_rendStackCall.getLastPage().getGlobalArgument().getStruct());
            NodeInformations nodeInfos_ = nodeCont_.getNodeInformation();
            String id_ = _write.getAttribute(_cont.getRendKeyWords().getAttrId());
            if (id_.isEmpty()) {
                id_ = _write.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrGroupId()));
            }
            String class_ = _advStandards.getInputClass(_write,_cont);
            if (class_.isEmpty()) {
                class_ = _f.getClassName();
            }
            nodeInfos_.setValidator(_write.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrValidator())));
            nodeInfos_.setId(id_);
            nodeInfos_.setInputClass(class_);
            stack_.last().put(currentInput_, nodeCont_);
            _rendStackCall.getFormParts().getIndexes().setNb(currentInput_);
            currentInput_++;
            inputs_.set(inputs_.getLastIndex(),currentInput_);
        } else {
            _rendStackCall.getFormParts().getIndexes().setNb(found_);
        }
        _write.setAttribute(_cont.getRendKeyWords().getAttrNi(), Long.toString(_rendStackCall.getFormParts().getIndexes().getNb()));
//        attributesNames_.removeAllString(NUMBER_INPUT);
        _write.setAttribute(_cont.getRendKeyWords().getAttrName(), StringUtil.concat(_rendStackCall.getLastPage().getBeanName(),DOT,name_));
        return arg_;
    }

    protected static void fetchValue(Configuration _cont, Element _read, Element _write, CustList<RendDynOperationNode> _ops, String _varNameConv, CustList<RendDynOperationNode> _opsConv, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            return;
        }
        if (_ops.isEmpty()) {
            return;
        }
        if (StringUtil.quickEq(_read.getTagName(),_cont.getRendKeyWords().getKeyWordInput())) {
            Argument o_ = RenderExpUtil.calculateReuse(_ops, _advStandards, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            if (StringUtil.quickEq(_read.getAttribute(_cont.getRendKeyWords().getAttrType()),_cont.getRendKeyWords().getValueCheckbox())) {
                if (Argument.isTrueValue(o_)) {
                    _write.setAttribute(_cont.getRendKeyWords().getAttrChecked(), _cont.getRendKeyWords().getAttrChecked());
                } else {
                    _write.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
                }
            } else {
                o_ = convertField(o_,_varNameConv,_opsConv, _advStandards, _ctx, _rendStackCall);
                if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                    return;
                }
                String value_ = _advStandards.processString(o_, _ctx, _rendStackCall);
                if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                    return;
                }
                _write.setAttribute(_cont.getRendKeyWords().getAttrValue(), value_);
            }
        }
        if (StringUtil.quickEq(_read.getTagName(),_cont.getRendKeyWords().getKeyWordTextarea())) {
            Argument o_ = RenderExpUtil.calculateReuse(_ops, _advStandards, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            o_ = convertField(o_,_varNameConv,_opsConv, _advStandards, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            Document doc_ = _write.getOwnerDocument();
            String value_ = _advStandards.processString(o_, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return;
            }
            Text text_ = doc_.createTextNode(value_);
            _write.appendChild(text_);
        }
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()));
    }
    private static Argument convertField(Argument _o, String _varNameConv, CustList<RendDynOperationNode> _opsConv, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        Struct o_ = _o.getStruct();
        if (!_opsConv.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(o_, _ctx.getStandards().getContent().getCoreNames().getAliasObject());
            _rendStackCall.getLastPage().putValueVar(_varNameConv, locVar_);
            Argument arg_ = RenderExpUtil.calculateReuse(_opsConv, _advStandards, _ctx, _rendStackCall);
            _rendStackCall.getLastPage().removeRefVar(_varNameConv);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return Argument.createVoid();
            }
            o_ = arg_.getStruct();
        }
        if (o_ == NullStruct.NULL_VALUE) {
            o_ = new StringStruct(EMPTY_STRING);
        }
        return new Argument(o_);
    }

    static String escapeParam(Argument _arg, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _stackCall) {
        String str_ = _advStandards.processString(_arg, _ctx, _stackCall);
        if (_ctx.callsOrException(_stackCall.getStackCall())) {
            return str_;
        }
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = Character.toString(QUOTE);
        rep_.put(Character.toString(LEFT_EL), StringUtil.concat(quote_,Character.toString(LEFT_EL),quote_));
        rep_.put(Character.toString(RIGHT_EL), StringUtil.concat(quote_,Character.toString(RIGHT_EL),quote_));
        rep_.put(Character.toString(QUOTE), StringUtil.concat(quote_,quote_));
        return StringUtil.replaceMultiple(str_, rep_);
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

    protected static void processVisitedLoop(Configuration _conf, BeanLgNames _advStandards, RendLoopBlockStack _l, RendBlock _next, ContextEl _context, RendStackCall _stackCall) {
        if (_l.getContent().isFinished()) {
            _next.processBlockAndRemove(_conf, _advStandards, _context,_stackCall);
            return;
        }
        ImportingPage ip_ = _stackCall.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(_l.getCurrentVisitedBlock().getFirstChild());
    }
    public final void processBlockAndRemove(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.removeRendLastBlock();
        processBlock(_conf, _advStandards, _ctx, _rendStackCall);
    }
    public final void processBlock(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock nextSibling_ = getNextSibling();
        if (nextSibling_ != null) {
            rw_.setRead(nextSibling_);
            return;
        }
        RendParentBlock par_ = getParent();
        RendAbstractStask lastStack_ = ip_.tryGetRendLastStack();
        if (lastStack_ != null) {
            rw_.setRead(par_);
            if (lastStack_ instanceof RendLoopBlockStack) {
                removeLocalVarsLoop(ip_, par_);
                processLastElementLoop(_conf, _advStandards, _ctx, _rendStackCall, par_, (RendLoopBlockStack) lastStack_);
            }
            if (lastStack_ instanceof RendIfStack) {
                if (par_ instanceof RendIfCondition) {
                    par_.removeAllVars(ip_);
                }
                if (par_ instanceof RendElseIfCondition) {
                    par_.removeAllVars(ip_);
                }
                if (par_ instanceof RendElseCondition) {
                    par_.removeAllVars(ip_);
                }
                if (par_ instanceof RendElement) {
                    par_.removeAllVars(ip_);
                }
                rw_.setRead(((RendIfStack)lastStack_).getLastBlock());
            }
            if (lastStack_ instanceof RendTryBlockStack) {
                if (par_ instanceof RendTryEval) {
                    par_.removeAllVars(ip_);
                    rw_.setRead(par_.getNextSibling());
                }
                if (par_ instanceof RendAbstractCatchEval) {
                    par_.removeAllVars(ip_);
                    rw_.setRead(par_);
                }
                if (par_ instanceof RendFinallyEval) {
                    par_.removeAllVars(ip_);
                    rw_.setRead(par_);
                    RendAbruptCallingFinally call_ = ((RendTryBlockStack)lastStack_).getCalling();
                    if (call_ != null) {
                        RendMethodCallingFinally callingFinally_ = call_.getCallingFinally();
                        if (callingFinally_ != null) {
                            callingFinally_.removeBlockFinally(_conf,_advStandards,_ctx, _rendStackCall);
                        } else {
                            Struct exception_ = ((RendTryBlockStack)lastStack_).getException();
                            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(exception_));
                            processGeneException(_ctx, _rendStackCall);
                        }
                    }
                }
            }
            if (lastStack_ instanceof RendSwitchBlockStack) {
                if (par_ instanceof RendDefaultCondition) {
                    par_.removeAllVars(ip_);
                    RendSwitchBlockStack if_ = (RendSwitchBlockStack) lastStack_;
                    if (if_.getLastVisitedBlock() == par_) {
                        rw_.setRead(if_.getBlock());
                    } else {
                        rw_.setRead(par_.getNextSibling());
                    }
                }
                if (par_ instanceof RendCaseCondition) {
                    par_.removeAllVars(ip_);
                    RendSwitchBlockStack if_ = (RendSwitchBlockStack) lastStack_;
                    if (if_.getLastVisitedBlock() == par_) {
                        rw_.setRead(if_.getBlock());
                    } else {
                        rw_.setRead(par_.getNextSibling());
                    }
                }
            }
            return;
        }
        ip_.setNullRendReadWrite();
    }

    private static void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall, RendParentBlock _par, RendLoopBlockStack _lastStack) {
        if (_par instanceof RendDoBlock) {
            ((RendDoBlock) _par).processLastElementLoop(_rendStackCall);
        }
        if (_par instanceof RendAbstractForEachLoop) {
            ((RendAbstractForEachLoop) _par).processLastElementLoop(_conf, _advStandards, _ctx, _lastStack, _rendStackCall);
        }
        if (_par instanceof RendForIterativeLoop) {
            ((RendForIterativeLoop) _par).processLastElementLoop(_ctx, _lastStack, _rendStackCall);
        }
        if (_par instanceof RendForMutableIterativeLoop) {
            ((RendForMutableIterativeLoop) _par).processLastElementLoop(_conf, _advStandards, _ctx, _lastStack, _rendStackCall);
        }
        if (_par instanceof RendForEachTable) {
            ((RendForEachTable) _par).processLastElementLoop(_conf, _advStandards, _ctx, _lastStack, _rendStackCall);
        }
        if (_par instanceof RendWhileCondition) {
            ((RendWhileCondition) _par).processLastElementLoop(_conf, _advStandards, _ctx, _lastStack, _rendStackCall);
        }
    }

    private static void removeLocalVarsLoop(ImportingPage _ip, RendParentBlock _par) {
        if (_par instanceof RendDoBlock) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof RendAbstractForEachLoop) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof RendForIterativeLoop) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof RendForMutableIterativeLoop) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof RendForEachTable) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof RendWhileCondition) {
            _par.removeLocalVars(_ip);
        }
    }

    public void setEscapedChars(StringMap<IntTreeMap<Integer>> _escapedChars) {
        escapedChars = _escapedChars;
    }

    public StringMap<IntTreeMap<Integer>> getEscapedChars() {
        return escapedChars;
    }


    public static RendAbstractStask hasBlockBreak(ImportingPage _ip, String _label) {
        RendAbstractStask last_ = _ip.tryGetRendLastStack();
        if (last_ == null) {
            _ip.setNullRendReadWrite();
            return null;
        }
        RendReadWrite rw_ = _ip.getRendReadWrite();
        if (_label.isEmpty()) {
            if (last_ instanceof RendLoopBlockStack) {
                RendBlock forLoopLoc_ = last_.getCurrentVisitedBlock();
                rw_.setRead(forLoopLoc_);
                ((RendLoopBlockStack)last_).getContent().setFinished(true);
                return null;
            }
            if (last_ instanceof RendSwitchBlockStack) {
                RendBlock forLoopLoc_ = ((RendSwitchBlockStack)last_).getBlock();
                rw_.setRead(forLoopLoc_);
                return null;
            }
            return last_;
        }
        if (StringUtil.quickEq(_label, last_.getLabel())){
            if (last_ instanceof RendLoopBlockStack) {
                rw_.setRead(last_.getCurrentVisitedBlock());
                ((RendLoopBlockStack) last_).getContent().setFinished(true);
            }
            if (last_ instanceof RendIfStack) {
                rw_.setRead(((RendIfStack) last_).getLastBlock());
            }
            if (last_ instanceof RendTryBlockStack) {
                rw_.setRead(((RendTryBlockStack) last_).getLastBlock());
            }
            if (last_ instanceof RendSwitchBlockStack) {
                rw_.setRead(((RendSwitchBlockStack) last_).getBlock());
            }
            return null;
        }
        return last_;
    }

    public static RendAbstractStask hasBlockContinue(Configuration _config, BeanLgNames _stds, ContextEl _conf, ImportingPage _ip, String _label, RendStackCall _rendStackCall) {
        RendAbstractStask last_ = _ip.tryGetRendLastStack();
        if (last_ == null) {
            _ip.setNullRendReadWrite();
            return null;
        }
        if (last_ instanceof RendLoopBlockStack) {
            RendParentBlock br_ = last_.getCurrentVisitedBlock();
            if (_label.isEmpty()) {
                RendLoopBlockStack lSt_;
                lSt_ = (RendLoopBlockStack) last_;
                removeLocalVarsLoop(_ip,br_);
                _ip.getRendReadWrite().setRead(br_);
                processLastElementLoop(_config,_stds,_conf,_rendStackCall,br_,lSt_);
                return null;
            }
            if (StringUtil.quickEq(_label, last_.getLabel())){
                RendLoopBlockStack lSt_;
                lSt_ = (RendLoopBlockStack) last_;
                removeLocalVarsLoop(_ip,br_);
                _ip.getRendReadWrite().setRead(br_);
                processLastElementLoop(_config,_stds,_conf,_rendStackCall,br_,lSt_);
                return null;
            }
        }
        return last_;
    }
    public static void setVisited(ImportingPage _ip, RendParentBlock _block) {
        RendAbstractStask lastStack_ = _ip.tryGetRendLastStack();
        if (lastStack_ == null) {
            _ip.setNullRendReadWrite();
            return;
        }
        lastStack_.setCurrentVisitedBlock(_block);
    }
    public static void processFinally(Configuration _conf, BeanLgNames _stds, ContextEl _cont, RendParentBlock _block, RendStackCall _rendStackCall) {
        processEnt(_conf, _stds, _cont, _block, _rendStackCall);

//        ImportingPage ip_ = _rendStackCall.getLastPage();
//        RendTryBlockStack ts_ = ip_.getLastTry();
//        if (ts_ == null) {
//            ip_.setNullRendReadWrite();
//            return;
//        }
//        ts_.setCurrentVisitedBlock(_block);
//        if (ts_.isVisitedFinally()) {
//            _block.processBlockAndRemove(_conf,_stds,_cont, _rendStackCall);
//            return;
//        }
//        ts_.setVisitedFinally(true);
//        ip_.getRendReadWrite().setRead(_block.getFirstChild());
    }
    public static void processElseIf(Configuration _conf, BeanLgNames _stds, ContextEl _cont, RendCondition _cond, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendAbstractStask if_ = ip_.tryGetRendLastStack();
        if (!(if_ instanceof RendIfStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!((RendIfStack) if_).isEntered()) {
            ConditionReturn assert_ = _cond.evaluateCondition(_conf,_stds,_cont, _rendStackCall);
            if (assert_ == ConditionReturn.CALL_EX) {
                return;
            }
            if (assert_ == ConditionReturn.YES) {
                ((RendIfStack) if_).setEntered(true);
                rw_.setRead(_cond.getFirstChild());
                return;
            }
        }
        RendBlock n_ = _cond.getNextSibling();
        if (n_ instanceof RendPossibleEmpty) {
            n_ = n_.getNextSibling();
        }
        if (RendParentBlock.isStrictNextIfParts(n_)) {
            rw_.setRead(n_);
            return;
        }
        _cond.processBlockAndRemove(_conf,_stds,_cont, _rendStackCall);
    }
    public static void processElse(Configuration _conf, BeanLgNames _stds, ContextEl _cont, RendParentBlock _cond, RendStackCall _rendStackCall) {
        processEnt(_conf, _stds, _cont, _cond, _rendStackCall);
//        ImportingPage ip_ = _rendStackCall.getLastPage();
//        RendIfStack if_ = ip_.getLastIf();
//        if (if_ == null) {
//            ip_.setNullRendReadWrite();
//            return;
//        }
//        if_.setCurrentVisitedBlock(_cond);
//        if (!if_.isEntered()) {
//            if_.setEntered(true);
//            ip_.getRendReadWrite().setRead(_cond.getFirstChild());
//            return;
//        }
//        _cond.processBlockAndRemove(_conf,_stds,_cont, _rendStackCall);
    }
   public static void processEnt(Configuration _conf, BeanLgNames _stds, ContextEl _cont, RendParentBlock _cond, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        RendAbstractStask if_ = ip_.tryGetRendLastStack();
        if (!(if_ instanceof RendEnteredStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!((RendEnteredStack)if_).isEntered()) {
            ((RendEnteredStack)if_).setEntered(true);
            ip_.getRendReadWrite().setRead(_cond.getFirstChild());
            return;
        }
        _cond.processBlockAndRemove(_conf,_stds,_cont, _rendStackCall);
    }
    public static void processDo(Configuration _conf, BeanLgNames _stds, ContextEl _cont, RendCondition _cond, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendAbstractStask l_ = ip_.tryGetRendLastStack();
        if (!(l_ instanceof RendLoopBlockStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        ConditionReturn keep_ = _cond.evaluateCondition(_conf,_stds,_cont, _rendStackCall);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        ExecHelperBlocks.afterConditLoop(keep_, ((RendLoopBlockStack) l_).getContent());
//        if (keep_ == ConditionReturn.NO) {
//            ((RendLoopBlockStack) l_).getContent().setFinished(true);
//        }
        RendBlock previousSibling_ = _cond.getPreviousSibling();
        if (previousSibling_ instanceof RendPossibleEmpty) {
            previousSibling_ = previousSibling_.getPreviousSibling();
        }
        rw_.setRead(previousSibling_);
    }

    private static void processGeneException(ContextEl _context, RendStackCall _rendStackCall) {
        CallingState callingState_ = _rendStackCall.getStackCall().getCallingState();
        if (callingState_ instanceof CustomFoundExc) {
            Struct exc_ = ((CustomFoundExc) callingState_).getStruct();
            RendLocalThrowing.removeBlockFinally(_context,exc_, _rendStackCall);
        }
    }
}
