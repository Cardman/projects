package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.opers.ExecWrappOperation;
import code.expressionlanguage.exec.stacks.LoopBlockStackContent;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fwd.opers.ExecSettableOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.*;
import code.formathtml.exec.opers.*;
import code.formathtml.exec.stacks.*;
import code.formathtml.fwd.RendGeneLinkTypes;
import code.formathtml.util.*;
import code.sml.*;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class RendBlock {
    public static final String SPACE = " ";
    public static final String RETURN_LINE = "\n";
//    public static final String CALL_METHOD = "$";
    public static final String EMPTY_STRING = "";
    public static final String TMP_BLOCK_TAG = "_";
    public static final String LT_END_TAG = "</";
    public static final char GT_TAG = '>';
    public static final char LT_BEGIN_TAG = '<';

    public static final String DOT = ".";
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
    private static final char QUOTE = 39;

    private RendParentBlock parent;

    private RendBlock nextSibling;

    private RendBlock previousSibling;

    RendBlock() {
    }
    public static boolean isPossibleEmpty(RendBlock _rb) {
        return _rb instanceof RendEmptyText;
    }
    public static String res(RendDocumentBlock _rend, Configuration _conf, BeanCustLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getFormParts().initFormsSpec();
        String beanName_ = _rend.getBeanName();
        Struct bean_ = _stds.getBuiltBeans().getVal(beanName_);
        _rendStackCall.setMainBean(bean_);
        _rendStackCall.addPage(new ImportingPage());
        Struct resDis_ = RendImport.befDisp(_stds, _ctx, _rendStackCall, bean_);
        _rendStackCall.removeLastPage();
        if (resDis_ == null) {
            return EMPTY_STRING;
        }
        _rendStackCall.getStackCall().setFullStack(new AdvancedFullStack(_ctx, _rendStackCall));
        ImportingPage ip_ = new ImportingPage();
        int tabWidth_ = _conf.getTabWidth();
//        ip_.setReadUrl(_currentUrl);
        ip_.setBeanName(beanName_);
        ip_.doc(_rend);
        if (bean_ != null) {
            ip_.setGlobalArgumentStruct(bean_,_ctx);
        }
        _rendStackCall.addPage(ip_);
        FullDocument doc_ = DocumentBuilder.newXmlDocument(tabWidth_);
        appendedChild(doc_, null, _rend.getElt());
        DefRendReadWrite rw_ = new DefRendReadWrite();
        rw_.setConf(_rendStackCall.getFormParts());
        rw_.setRead(_rend.getDocElt());
        rw_.setDocument(doc_);
        ip_.setRendReadWrite(rw_);
        while (true) {
            if (exitRender(_conf, _stds, _ctx, _rendStackCall)) {
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
    private static boolean exitRender(Configuration _conf, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        ImportingPage p_ = _rendStackCall.getLastPage();
        DefRendReadWrite rendReadWrite_ = p_.getRendReadWrite();
        RendBlock read_ = null;
        if (rendReadWrite_ != null) {
            read_ = rendReadWrite_.getRead();
        }
        if (read_ == null) {
            _rendStackCall.removeLastPage();
            return _rendStackCall.getImporting().isEmpty();
        }
        processTags(_conf, _stds, _ctx, read_, _rendStackCall);
        processGeneException(_ctx, _rendStackCall);
        return _ctx.callsOrException(_rendStackCall.getStackCall());
    }

    private static void processTags(Configuration _context, BeanLgNames _stds, ContextEl _ctx, RendBlock _read, RendStackCall _rendStackCall) {
        tryProcessEl(_context, _stds, _ctx, _read, _rendStackCall);
    }

    private static void tryProcessEl(Configuration _context, BeanLgNames _stds, ContextEl _ctx, RendBlock _read, RendStackCall _rendStackCall) {
        ((RendWithEl) _read).processEl(_context, _stds, _ctx, _rendStackCall);
    }

    private static boolean isNextIfParts(RendBlock _n) {
        return isStrictNextIfParts(_n) || isPossibleEmpty(_n);
    }

    private static boolean isStrictNextIfParts(RendBlock _n) {
        return _n instanceof RendElseIfCondition || _n instanceof RendElseCondition;
    }

    private static boolean isNextTryParts(RendBlock _n) {
        return isStrictNextTryParts(_n) || isPossibleEmpty(_n);
    }

    private static boolean isStrictNextTryParts(RendBlock _n) {
        return _n instanceof RendAbstractCatchEval || _n instanceof RendFinallyEval;
    }

    private static boolean hasToIncr(ConfigurationCore _cont, Element _nextEltWrite, RendKeyWordsGroup _k) {
        return StringUtil.quickEq(_nextEltWrite.getTagName(), _k.getKeyWordsTags().getKeyWordAnchor())
                && (_nextEltWrite.hasAttribute(StringUtil.concat(_cont.getPrefix(), _k.getKeyWordsAttrs().getAttrCommand()))
                || !_nextEltWrite.getAttribute(_k.getKeyWordsAttrs().getAttrHref()).isEmpty());
    }

    public static void incrAncNb(ConfigurationCore _cont, Element _nextEltWrite, IndexesFormInput _indexes, RendKeyWordsGroup _k) {
        if (hasToIncr(_cont, _nextEltWrite, _k)) {
            _nextEltWrite.setAttribute(_k.getKeyWordsAttrs().getAttrNa(), Long.toString(_indexes.getAnchor()));
        }
    }

    public static Element appendedChild(Document _doc, Element _parent, Element _read) {
        String tagName_ = _read.getTagName();
        Element currentNode_ = _doc.createElement(tagName_);
        NavigationCore.setNormalAttributes(_read, currentNode_);
        NavigationCore.simpleAppendChild(_doc, _parent, currentNode_);
        return currentNode_;
    }

    public static void procLink(RendKeyWordsTags _cont, String _fileContent, Document _ownerDocument) {
        ElementList heads_ = _ownerDocument.getElementsByTagName(_cont.getKeyWordHead());
        if (_fileContent != null && heads_.getLength() == IndexConstants.ONE_ELEMENT) {
            Element head_ = heads_.item(IndexConstants.FIRST_INDEX);
            NavigationCore.prHeader(_cont, _fileContent, _ownerDocument, head_);
        }
    }

    protected final void setParent(RendParentBlock _b) {
        parent = _b;
    }

    public static Struct processLink(Configuration _cont, Element _nextWrite, Element _read, StringMap<CustList<RendDynOperationNode>> _textPart, RendGeneLinkTypes _anc, ContextEl _ctx, RendStackCall _rendStackCall) {
//        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()));
//        if (!href_.startsWith(BeanLgNames.CALL_METHOD)) {
//            _rendStackCall.getFormParts().getCallsExps().add(new AnchorCall(_anc.getGeneLink(),new CustList<AbstractWrapper>()));
//            procCstAnc(_cont, _nextWrite, _rendStackCall.getFormParts());
//            return;
//        }
        CustList<AbstractWrapper> values_ = new CustList<AbstractWrapper>();
        int i_ = 0;
        for (EntryCust<String, CustList<RendDynOperationNode>> e: _textPart.entryList()) {
            Struct args_ = RenderExpUtil.getFinalArg(e.getValue(), _ctx, _rendStackCall);
            if (args_ == null) {
                return null;
            }
            LocalVariable locVar_ = LocalVariable.newLocalVariable(args_, _rendStackCall.formatVarType(_anc.getTypes().get(i_)));
            values_.add(new VariableWrapper(locVar_));
            _nextWrite.removeAttribute(e.getKey());
            i_++;
        }
        _rendStackCall.getFormParts().getCallsExps().add(new AnchorCall(_anc.getGeneLink(),values_));
        String beanName_ = _rendStackCall.getLastPage().getBeanName();
        _nextWrite.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrCommand()), beanName_);
        _nextWrite.setAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrSgn()), _read.getAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrSgn())));
        _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrHref(), EMPTY_STRING);
        incrAncNb(_cont.getRend(), _nextWrite, _rendStackCall.getFormParts().getIndexes(), _cont.getRendKeyWords().group());
        IndexesFormInput.incr(_rendStackCall.getFormParts().getIndexes());
        return NullStruct.NULL_VALUE;
    }

    public static String getCssHref(Configuration _cont,Element _link) {
        if (!StringUtil.quickEq(_link.getAttribute(_cont.getRendKeyWords().getAttrRel()),_cont.getRendKeyWords().getValueStyle())) {
            return null;
        }
        if (!_link.hasAttribute(_cont.getRendKeyWords().getAttrHref())){
            return null;
        }
        return _link.getAttribute(_cont.getRendKeyWords().getAttrHref());
    }

    protected static Struct iteratorMultTable(Struct _arg, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = ((BeanCustLgNames)_advStandards).getIteratorTableVarCust();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Struct arg_ = RenderExpUtil.getFinalArg(((BeanCustLgNames) _advStandards).getExpsIteratorTableCust(), _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }
    protected static Struct hasNextPair(Struct _arg, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = ((BeanCustLgNames)_advStandards).getHasNextPairVarCust();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Struct arg_ = RenderExpUtil.getFinalArg(((BeanCustLgNames) _advStandards).getExpsHasNextPairCust(), _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }
    protected static Struct nextPair(Struct _arg, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = ((BeanCustLgNames)_advStandards).getNextPairVarCust();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Struct arg_ = RenderExpUtil.getFinalArg(((BeanCustLgNames) _advStandards).getExpsNextPairCust(), _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }
    protected static Struct first(Struct _arg, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = ((BeanCustLgNames)_advStandards).getFirstVarCust();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Struct arg_ = RenderExpUtil.getFinalArg(((BeanCustLgNames) _advStandards).getExpsFirstCust(), _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }
    protected static Struct second(Struct _arg, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = ((BeanCustLgNames)_advStandards).getSecondVarCust();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Struct arg_ = RenderExpUtil.getFinalArg(((BeanCustLgNames) _advStandards).getExpsSecondCust(), _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }
    protected static Struct iterator(Struct _arg, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = ((BeanCustLgNames)_advStandards).getIteratorVar();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Struct arg_ = RenderExpUtil.getFinalArg(((BeanCustLgNames) _advStandards).getExpsIterator(), _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }
    protected static Struct hasNext(Struct _arg, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = ((BeanCustLgNames)_advStandards).getHasNextVar();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Struct arg_ = RenderExpUtil.getFinalArg(((BeanCustLgNames) _advStandards).getExpsHasNext(), _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }
    protected static Struct next(Struct _arg, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = ((BeanCustLgNames)_advStandards).getNextVar();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Struct arg_ = RenderExpUtil.getFinalArg(((BeanCustLgNames) _advStandards).getExpsNext(), _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }

    protected static DefFetchedObjs fetchName(Configuration _cont, Element _read, ContextEl _ctx, RendStackCall _rendStackCall, String _idRad, CustList<RendDynOperationNode> _opRead) {
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            CustList<LongTreeMap<DefNodeContainer>> stack_;
            stack_ = _rendStackCall.getFormParts().getContainersMapStack();
            return new DefFetchedObjs(_idRad,null, new CustList<Struct>(), stack_, NullStruct.NULL_VALUE, "");
        }
        IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(_opRead, _ctx, _rendStackCall);
        if (args_.isEmpty()) {
            CustList<LongTreeMap<DefNodeContainer>> stack_;
            stack_ = _rendStackCall.getFormParts().getContainersMapStack();
            return new DefFetchedObjs(_idRad,null, new CustList<Struct>(), stack_, null, "");
        }
        RendDynOperationNode root_ = args_.lastKey();
        RendDynOperationNode res_;
        if (root_ instanceof RendIdOperation) {
            res_ = RendAbstractAffectOperation.deepId(((RendIdOperation)root_).getFirstChild());
        } else {
            res_ = root_;
        }
        CustList<Struct> allObj_ = new CustList<Struct>();
        RendDynOperationNode settable_ = RendAbstractAffectOperation.castDottedTo(res_);
        Struct arg_ = ArgumentListCall.getNull(args_.getValue(settable_.getOrder()).getArgument());
        AbstractWrapper wr_;
        if (settable_ instanceof RendSettableFieldStatOperation) {
            ExecSettableOperationContent settableFieldContent_ = ((RendSettableFieldStatOperation) settable_).getSettableFieldContent();
            allObj_.add(NullStruct.NULL_VALUE);
            ExecRootBlock rootBlock_ = ((RendSettableFieldStatOperation) settable_).getRootBlock();
            wr_ = new StaticFieldWrapper(arg_,settableFieldContent_.getRealType(),rootBlock_,
                    settableFieldContent_.getClassField());
        } else if (settable_ instanceof RendSettableFieldInstOperation) {
            ExecSettableOperationContent settableFieldContent_ = ((RendSettableFieldInstOperation) settable_).getSettableFieldContent();
            ArgumentsPair pairCh_ = RendDynOperationNode.getArgumentPair(args_, settable_);
            Struct parent_ = pairCh_.getArgumentParent();
            ExecTypeReturn pair_ = ((RendSettableFieldInstOperation) settable_).getPair();
            allObj_.add(parent_);
            wr_ = new InstanceFieldWrapper(arg_,parent_, parent_.getClassName(_ctx),settableFieldContent_.getRealType(),
                    settableFieldContent_.getClassField(), pair_);
        } else if (settable_ instanceof RendCustArrOperation) {
            RendCustArrOperation ch_ = (RendCustArrOperation)settable_;
            ArgumentsPair pairCh_ = RendDynOperationNode.getArgumentPair(args_, ch_);
            Struct parent_ = pairCh_.getArgumentParent();
            CustList<ArgumentWrapper> argumentList_ = pairCh_.getArgumentList();
            wr_ = new ArrayCustWrapper(arg_,argumentList_,parent_, parent_.getClassName(_ctx), ch_.getReadWrite());
            allObj_.add(parent_);
            feed(argumentList_, allObj_);
        } else if (settable_ instanceof RendArrOperation){
            RendArrOperation ch_ = (RendArrOperation)settable_;
            Struct previousArgument_ = ArgumentListCall.getNull(RendDynOperationNode.getArgumentPair(args_, ch_).getArgumentParent());
            ArgumentsPair pairIndex_ = RendDynOperationNode.getArgumentPair(args_, ch_.getFirstChild());
            wr_ = ExecWrappOperation.buildArrWrapp(previousArgument_,pairIndex_.getArgument(),arg_);
            allObj_.add(previousArgument_);
            allObj_.add(pairIndex_.getArgument());
        } else {
            ArgumentsPair pairCh_ = RendDynOperationNode.getArgumentPair(args_, settable_);
            Struct parent_ = ArgumentListCall.getNull(pairCh_.getArgumentParent());
            CustList<ArgumentWrapper> argumentList_ = pairCh_.getArgumentList();
            wr_ = ExecTemplates.getWrap(pairCh_.getWrapper());
            allObj_.add(parent_);
            feed(argumentList_, allObj_);
        }
        CustList<LongTreeMap<DefNodeContainer>> stack_ = _rendStackCall.getFormParts().getContainersMapStack();
        return new DefFetchedObjs(_idRad,wr_, allObj_, stack_, arg_, StringUtil.concat(_rendStackCall.getLastPage().getBeanName(), DOT, name_));
    }

    private static void feed(CustList<ArgumentWrapper> _argumentList, CustList<Struct> _allObj) {
        for (ArgumentWrapper a: _argumentList) {
            _allObj.add(a.getValue());
        }
    }

    public static void prStack(Configuration _cont, Element _write, DefFieldUpdates _f, DefFetchedObjs _fetch, Struct _globalArgument, RendStackCall _rend) {
        if (koInput(_fetch)) {
            return;
        }
        long found_ = foundId(_fetch);
        Struct currentField_ = _fetch.getArg();
        FormParts formParts_ = _rend.getFormParts();
        if (found_ == -1) {
            Longs inputs_ = formParts_.getInputs();
            long currentInput_ = inputs_.last();
            DefNodeContainer nodeCont_ = new DefNodeContainer();
            nodeCont_.setIdRadio(_fetch.getRad());
            nodeCont_.setIdFieldClass(_f.getIdClass());
            nodeCont_.setIdFieldName(_f.getIdName());
            nodeCont_.setTypedStruct(currentField_);
            nodeCont_.setAllObject(_fetch.getAllObj());
            nodeCont_.setOpsConvert(_f.getOpsConverter());
            nodeCont_.setInput(_fetch.getInput());
            nodeCont_.setArrayConverter(_f.isArrayConverter());
            nodeCont_.setBean(_globalArgument);
            NodeInformations nodeInfos_ = nodeCont_.getNodeInformation();
            String id_ = NavigationCore.getId(_cont.getRend(), _write, _cont.getRendKeyWords().group());
            String class_ = NavigationCore.getInputClass(_cont.getRend(), _write, _f, _cont.getRendKeyWords().group());
            nodeInfos_.setValidator(_write.getAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrValidator())));
            nodeInfos_.setId(id_);
            nodeInfos_.setInputClass(class_);
            _fetch.getStack().last().put(currentInput_, nodeCont_);
            currentInput_++;
            inputs_.set(inputs_.getLastIndex(),currentInput_);
        }
    }

    private static boolean koInput(DefFetchedObjs _fetch) {
        return _fetch.getInput() == null || _fetch.getStack().isEmpty();
    }

    private static long foundId(DefFetchedObjs _fetch) {
        long found_ = -1;
        if (!_fetch.getRad().isEmpty()) {
            for (EntryCust<Long, DefNodeContainer> e: _fetch.getStack().last().entryList()) {
                if (StringUtil.quickEq(e.getValue().getIdRadio(), _fetch.getRad())) {
                    found_ = e.getKey();
                    break;
                }
            }
        }
        return found_;
    }

    public static void look(Configuration _cont, Element _write, DefFetchedObjs _fetch, RendStackCall _rend) {
        if (koInput(_fetch)) {
            return;
        }
        long found_ = foundId(_fetch);
        FormParts formParts_ = _rend.getFormParts();
        if (found_ == -1) {
            Longs inputs_ = formParts_.getInputs();
            long currentInput_ = inputs_.last();
            _write.setAttribute(_cont.getRendKeyWords().getAttrNi(), Long.toString(currentInput_));
        } else {
            _write.setAttribute(_cont.getRendKeyWords().getAttrNi(), Long.toString(found_));
        }
        _write.setAttribute(_cont.getRendKeyWords().getAttrName(), _fetch.getInputName());
    }

    protected static DefFetchedObjs fetchValue(Configuration _cont, Element _read, Element _write, RendSelectOperators _opers, ContextEl _ctx, RendStackCall _rendStackCall, DefFetchedObjs _arg) {
        if (_arg.getArg() == null) {
            return _arg;
        }
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty() || _opers.getOpsValue().isEmpty()) {
            return _arg;
        }
        if (StringUtil.quickEq(_read.getTagName(), _cont.getRendKeyWords().getKeyWordInput()) && exitInput(_cont, _read, _write, _opers.getOpsValue(), _opers.getOpsConverterField(), _ctx, _rendStackCall)) {
            return new DefFetchedObjs("",null,new CustList<Struct>(),new CustList<LongTreeMap<DefNodeContainer>>(),null,"");
        }
        if (StringUtil.quickEq(_read.getTagName(), _cont.getRendKeyWords().getKeyWordTextarea()) && exitTextArea(_write, _opers.getOpsValue(), _opers.getOpsConverterField(), _ctx, _rendStackCall)) {
            return new DefFetchedObjs("",null,new CustList<Struct>(),new CustList<LongTreeMap<DefNodeContainer>>(),null,"");
        }
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()));
        return _arg;
    }
    private static boolean exitInput(Configuration _cont, Element _read, Element _write, CustList<RendDynOperationNode> _ops, CustList<RendDynOperationNode> _opsConv, ContextEl _ctx, RendStackCall _rendStackCall) {
        Struct o_ = RenderExpUtil.getFinalArg(_ops, _ctx, _rendStackCall);
        if (o_ == null) {
            return true;
        }
        if (StringUtil.quickEq(_read.getAttribute(_cont.getRendKeyWords().getAttrType()),_cont.getRendKeyWords().getValueCheckbox())) {
            if (BooleanStruct.isTrue(o_)) {
                _write.setAttribute(_cont.getRendKeyWords().getAttrChecked(), _cont.getRendKeyWords().getAttrChecked());
            } else {
                _write.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
            }
        } else {
            o_ = convertField(o_, _opsConv, _ctx, _rendStackCall);
            if (o_ == NullStruct.NULL_VALUE) {
                return true;
            }
            Struct value_ = RendDynOperationNode.processString(o_, _ctx, _rendStackCall);
            if (value_ == null) {
                return true;
            }
            _write.setAttribute(_cont.getRendKeyWords().getAttrValue(), NumParsers.getString(value_).getInstance());
        }
        return false;
    }
    private static boolean exitTextArea(Element _write, CustList<RendDynOperationNode> _ops, CustList<RendDynOperationNode> _opsConv, ContextEl _ctx, RendStackCall _rendStackCall) {
        Struct o_ = RenderExpUtil.getFinalArg(_ops, _ctx, _rendStackCall);
        if (o_ == null) {
            return true;
        }
        o_ = convertField(o_, _opsConv, _ctx, _rendStackCall);
        if (o_ == NullStruct.NULL_VALUE) {
            return true;
        }
        Struct value_ = RendDynOperationNode.processString(o_, _ctx, _rendStackCall);
        if (value_ == null) {
            return true;
        }
        Text text_ = _write.getOwnerDocument().createTextNode(NumParsers.getString(value_).getInstance());
        _write.appendChild(text_);
        return false;
    }

    private static Struct convertField(Struct _o, CustList<RendDynOperationNode> _opsConv, ContextEl _ctx, RendStackCall _rendStackCall) {
        Struct o_ = _o;
        if (!_opsConv.isEmpty()) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(o_, _ctx.getStandards().getContent().getCoreNames().getAliasObject());
            _rendStackCall.getLastPage().putValueVar("0", new VariableWrapper(locVar_));
            Struct arg_ = RenderExpUtil.getFinalArg(_opsConv, _ctx, _rendStackCall);
            _rendStackCall.getLastPage().removeRefVar("0");
            if (arg_ == null) {
                return NullStruct.NULL_VALUE;
            }
            o_ = arg_;
        }
        if (o_ == NullStruct.NULL_VALUE) {
            o_ = new StringStruct(EMPTY_STRING);
        }
        return o_;
    }

    static String escapeParam(Struct _arg, ContextEl _ctx, RendStackCall _stackCall) {
        String str_ = BeanCustLgNames.processString(_arg, _ctx, _stackCall);
        if (str_ == null) {
            return null;
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
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        rw_.setRead(_l.getCurrentVisitedBlock().getFirstChild());
    }
    public final void processBlockAndRemove(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.removeRendLastBlock();
        processBlock(_conf, _advStandards, _ctx, _rendStackCall);
    }
    public final void processBlock(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
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
                nextIfStack(ip_, rw_, par_, (RendIfStack) lastStack_);
            }
            if (lastStack_ instanceof RendTryBlockStack) {
                nextTryStack(_conf, _advStandards, _ctx, _rendStackCall, par_, (RendTryBlockStack) lastStack_);
            }
            if (lastStack_ instanceof RendSwitchBlockStack) {
                nextSwitchBlock(ip_, rw_, par_, (RendSwitchBlockStack) lastStack_);
            }
            return;
        }
        ip_.setNullRendReadWrite();
    }

    private static void nextSwitchBlock(ImportingPage _ip, DefRendReadWrite _rw, RendParentBlock _par, RendSwitchBlockStack _lastStack) {
        if (_par instanceof RendDefaultCondition) {
            _par.removeAllVars(_ip);
            redirect(_rw, _par, _lastStack);
        }
        if (_par instanceof RendAbstractCaseCondition) {
            _par.removeAllVars(_ip);
            redirect(_rw, _par, _lastStack);
        }
    }

    private static void redirect(DefRendReadWrite _rw, RendParentBlock _par, RendSwitchBlockStack _if) {
        if (_if.getLastVisitedBlock() == _par) {
            _rw.setRead(_if.getBlock());
        } else {
            _rw.setRead(_par.getNextSibling());
        }
    }

    private static void nextTryStack(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall, RendParentBlock _par, RendTryBlockStack _lastStack) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        if (_par instanceof RendTryEval) {
            _par.removeAllVars(ip_);
            rw_.setRead(_par.getNextSibling());
        }
        if (_par instanceof RendAbstractCatchEval) {
            _par.removeAllVars(ip_);
            rw_.setRead(_par);
        }
        if (_par instanceof RendFinallyEval) {
            _par.removeAllVars(ip_);
            rw_.setRead(_par);
            RendAbruptCallingFinally call_ = _lastStack.getCalling();
            if (call_ != null) {
                RendMethodCallingFinally callingFinally_ = call_.getCallingFinally();
                if (callingFinally_ != null) {
                    callingFinally_.removeBlockFinally(_conf, _advStandards, _ctx, _rendStackCall);
                } else {
                    _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(ArgumentListCall.getNull(_lastStack.getException())));
                    processGeneException(_ctx, _rendStackCall);
                }
            }
        }
    }

    private static void nextIfStack(ImportingPage _ip, DefRendReadWrite _rw, RendParentBlock _par, RendIfStack _lastStack) {
        if (_par instanceof RendIfCondition) {
            _par.removeAllVars(_ip);
        }
        if (_par instanceof RendElseIfCondition) {
            _par.removeAllVars(_ip);
        }
        if (_par instanceof RendElseCondition) {
            _par.removeAllVars(_ip);
        }
        if (_par instanceof RendElement) {
            _par.removeAllVars(_ip);
        }
        _rw.setRead(_lastStack.getLastBlock());
    }

    private static void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall, RendParentBlock _par, RendLoopBlockStack _lastStack) {
        if (_par instanceof RendDoBlock) {
            processLastElementLoop(_rendStackCall, ((RendDoBlock) _par));
        }
        if (_par instanceof RendAbstractForEachLoop) {
            ((RendAbstractForEachLoop) _par).processLastElementLoop(_conf, _advStandards, _ctx, _lastStack, _rendStackCall);
        }
        if (_par instanceof RendForIterativeLoop) {
            ((RendForIterativeLoop) _par).processLastElementLoop(_ctx, _lastStack, _rendStackCall);
        }
        if (_par instanceof RendForMutableIterativeLoop) {
            processLastElementLoop(_ctx, _lastStack, _rendStackCall, ((RendForMutableIterativeLoop) _par));
        }
        if (_par instanceof RendForEachTable) {
            ((RendForEachTable) _par).processLastElementLoop(_advStandards, _ctx, _lastStack, _rendStackCall);
        }
        if (_par instanceof RendWhileCondition) {
            processLastElementLoop(_ctx, _lastStack, _rendStackCall, ((RendWhileCondition) _par));
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


    public static RendAbstractStask hasBlockBreak(ImportingPage _ip, String _label) {
        RendAbstractStask last_ = _ip.tryGetRendLastStack();
        if (last_ == null) {
            _ip.setNullRendReadWrite();
            return null;
        }
        DefRendReadWrite rw_ = _ip.getRendReadWrite();
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

    static void processIf(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, String _label, RendIfCondition _cond) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(_cond)) {
            _cond.processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ConditionReturn assert_ = evaluateCondition(_ctx, _rendStack, _cond.getCondition());
        if (assert_ == ConditionReturn.CALL_EX) {
            return;
        }
        RendIfStack if_ = new RendIfStack();
        if_.setLabel(_label);
        if_.setLastBlock(_cond);
        RendBlock n_ = _cond.getNextSibling();
        while (isNextIfParts(n_)) {
            if (n_ instanceof RendParentBlock) {
                if_.setLastBlock((RendParentBlock) n_);
            }
            n_ = n_.getNextSibling();
        }
        if_.setBlock(_cond);
        if_.setCurrentVisitedBlock(_cond);
        ip_.addBlock(if_);
        if (assert_ == ConditionReturn.YES) {
            if_.setEntered(true);
            rw_.setRead(_cond.getFirstChild());
        } else {
            RendBlock next_ = _cond.getNextSibling();
            if (isNextIfParts(next_)) {
                rw_.setRead(next_);
            }
        }
    }

    public static void processElseIf(Configuration _conf, BeanLgNames _stds, ContextEl _cont, RendCondition _cond, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        RendAbstractStask if_ = ip_.tryGetRendLastStack();
        if (!(if_ instanceof RendIfStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!((RendIfStack) if_).isEntered()) {
            ConditionReturn assert_ = evaluateCondition(_cont, _rendStackCall, _cond.getCondition());
            if (assert_ == ConditionReturn.CALL_EX) {
                return;
            }
            if (assert_ == ConditionReturn.YES) {
                ((RendIfStack) if_).setEntered(true);
                rw_.setRead(_cond.getFirstChild());
                return;
            }
        }
        RendBlock n_ = skEmpty(_cond.getNextSibling());
        if (isStrictNextIfParts(n_)) {
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
    public static void processDo(ContextEl _cont, RendCondition _cond, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        RendAbstractStask l_ = ip_.tryGetRendLastStack();
        if (!(l_ instanceof RendLoopBlockStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        ConditionReturn keep_ = evaluateCondition(_cont, _rendStackCall, _cond.getCondition());
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        afterConditLoop(keep_, ((RendLoopBlockStack) l_).getContent());
        //        if (keep_ == ConditionReturn.NO) {
//            ((RendLoopBlockStack) l_).getContent().setFinished(true);
//        }
        RendBlock previousSibling_ = _cond.getPreviousSibling();
        if (isPossibleEmpty(previousSibling_)) {
            previousSibling_ = previousSibling_.getPreviousSibling();
        }
        rw_.setRead(previousSibling_);
    }

    private static void afterConditLoop(ConditionReturn _keep, LoopBlockStackContent _content) {
        if (_keep == ConditionReturn.CALL_EX) {
            return;
        }
        if (_keep == ConditionReturn.NO) {
            _content.setFinished(true);
        }
        _content.setEvaluatingKeepLoop(false);
    }

    private static ConditionReturn evaluateCondition(ContextEl _ctx, RendStackCall _rendStackCall, RendOperationNodeListOff _condition) {
        ImportingPage last_ = _rendStackCall.getLastPage();
        last_.setOffset(_condition.getOffset());
        Struct arg_ = RenderExpUtil.getFinalArg(_condition.getList(), _ctx, _rendStackCall);
        if (arg_ == null) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_)) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    private static void processGeneException(ContextEl _context, RendStackCall _rendStackCall) {
        CustomFoundExc callingState_ = _rendStackCall.getStackCall().trueException();
        if (callingState_ != null) {
            Struct exc_ = callingState_.getStruct();
            RendLocalThrowing.removeBlockFinally(_context,exc_, _rendStackCall);
        }
    }

    private static void processLastElementLoop(ContextEl _ctx, RendLoopBlockStack _loopBlock, RendStackCall _rendStack, RendWhileCondition _bl) {
//        ImportingPage ip_ = _rendStack.getLastPage();
//        RendReadWrite rw_ = ip_.getRendReadWrite();
//        RendBlock forLoopLoc_ = _loopBlock.getCurrentVisitedBlock();
        ConditionReturn keep_ = evaluateCondition(_ctx, _rendStack, _bl.getCondition());
        afterConditLoop(keep_, _loopBlock.getContent());
        //        if (keep_ == ConditionReturn.NO) {
//            _loopBlock.getContent().setFinished(true);
//        } else {
//            rw_.setRead(forLoopLoc_.getFirstChild());
//        }
    }

    protected static void processCatch(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, RendAbstractCatchEval _catch) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock n_ = skEmpty(_catch.getNextSibling());
        if (isStrictNextTryParts(n_)) {
            rw_.setRead(n_);
        } else {
            _catch.processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
        }
    }

    private static ConditionReturn evaluateConditionMutable(ContextEl _ctx, RendStackCall _rendStackCall, RendOperationNodeListOff _exp) {
        ImportingPage last_ = _rendStackCall.getLastPage();
        if (_exp.getList().isEmpty()) {
            return ConditionReturn.YES;
        }
        last_.setOffset(_exp.getOffset());
        Struct arg_ = RenderExpUtil.getFinalArg(_exp.getList(), _ctx, _rendStackCall);
        if (arg_ == null) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_)) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    static void processTry(RendStackCall _rendStack, String _label, RendTryEval _try) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendBlock n_ = _try.getNextSibling();
        RendTryBlockStack tryStack_ = new RendTryBlockStack();
        tryStack_.setLabel(_label);
        while (isNextTryParts(n_)) {
            if (n_ instanceof RendParentBlock) {
                tryStack_.setLastBlock((RendParentBlock) n_);
            }
            n_ = n_.getNextSibling();
        }
        tryStack_.setCurrentVisitedBlock(_try);
        ip_.addBlock(tryStack_);
        ip_.getRendReadWrite().setRead(_try.getFirstChild());
    }

    static void processDo(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, String _label, RendDoBlock _block) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(_block);
        if (c_ != null) {
            RendBlock next_ = skipEmpty(_block);
            processVisitedLoop(_cont, _stds,c_,next_, _ctx, _rendStack);
//            if (c_.getContent().isFinished()) {
//                next_.processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
//                return;
//            }
//            rw_.setRead(getFirstChild());
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(_label);
        l_.setBlock(_block);
        l_.setCurrentVisitedBlock(_block);
        ip_.addBlock(l_);
        rw_.setRead(_block.getFirstChild());
    }

    public static void processLastElementLoop(RendStackCall _rendStack, RendDoBlock _block) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock nextSibling_ = skipEmpty(_block);
        rw_.setRead(nextSibling_);
    }

    private static RendBlock skipEmpty(RendDoBlock _block) {
        RendBlock next_ = _block.getNextSibling();
        return skEmpty(next_);
    }

    private static RendBlock skEmpty(RendBlock _next) {
        RendBlock nextSibling_ = _next;
        if (isPossibleEmpty(nextSibling_)) {
            nextSibling_ = nextSibling_.getNextSibling();
        }
        return nextSibling_;
    }

    static void processWhile(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, String _label, RendWhileCondition _bl) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(_bl);
        if (c_ != null) {
            processVisitedLoop(_cont, _stds,c_, _bl, _ctx, _rendStack);
//            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ConditionReturn res_ = evaluateCondition(_ctx, _rendStack, _bl.getCondition());
        afterCond(_cont, _stds, _ctx, _rendStack, _label, _bl, res_);
    }

    static void processForMutable(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, String _label, RendForMutableIterativeLoop _bl) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(_bl);
        if (c_ != null) {
            processVisitedLoop(_cont, _stds,c_, _bl, _ctx, _rendStack);
//            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ip_.setOffset(_bl.getInit().getOffset());
        String importedClassName_ = _rendStack.formatVarType(_bl.getImportedClassName());
        Struct struct_ = ExecClassArgumentMatching.defaultValue(importedClassName_, _ctx);
        for (String v: _bl.getVariableNames()) {
            LoopVariable lv_ = new LoopVariable();
            lv_.setIndexClassName(_bl.getImportedClassIndexName());
            ip_.getVars().put(v, lv_);
            putVar(struct_, ip_, v, importedClassName_);
        }
        if (!_bl.getInit().getList().isEmpty() && RenderExpUtil.getFinalArg(_bl.getInit().getList(), _ctx, _rendStack) == null) {
            return;
        }
        ConditionReturn res_ = evaluateConditionMutable(_ctx, _rendStack, _bl.getExp());
        afterCond(_cont, _stds, _ctx, _rendStack, _label, _bl, res_);
    }

    private static void afterCond(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, String _label, RendParentBlock _bl, ConditionReturn _res) {
        if (_res == ConditionReturn.CALL_EX) {
            return;
        }
        ImportingPage ip_ = _rendStack.getLastPage();
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(_label);
        l_.setBlock(_bl);
        l_.setCurrentVisitedBlock(_bl);
        l_.getContent().setFinished(_res == ConditionReturn.NO);
        ip_.addBlock(l_);
        visitOrFinish(_cont, _stds, _ctx, _rendStack, _bl, ip_, l_);
    }

    protected static void visitOrFinish(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, RendParentBlock _bl, ImportingPage _ip, RendLoopBlockStack _l) {
        if (_l.getContent().isFinished()) {
            _bl.processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        DefRendReadWrite rw_ = _ip.getRendReadWrite();
        rw_.setRead(_bl.getFirstChild());
    }

    private static void processLastElementLoop(ContextEl _ctx, RendLoopBlockStack _loopBlock, RendStackCall _rendStack, RendForMutableIterativeLoop _bl) {
        ImportingPage ip_ = _rendStack.getLastPage();
//        RendReadWrite rw_ = ip_.getRendReadWrite();
//        RendBlock forLoopLoc_ = _loopBlock.getCurrentVisitedBlock();
        ip_.setOffset(_bl.getStep().getOffset());
        if (!_bl.getStep().getList().isEmpty() && RenderExpUtil.getFinalArg(_bl.getStep().getList(), _ctx, _rendStack) == null) {
            return;
        }
        for (String v: _bl.getVariableNames()) {
            LoopVariable lv_ = ip_.getVars().getVal(v);
            lv_.setIndex(lv_.getIndex()+1);
        }
        ConditionReturn keep_ = evaluateConditionMutable(_ctx, _rendStack, _bl.getExp());
        afterConditLoop(keep_, _loopBlock.getContent());
        //        if (keep_ == ConditionReturn.NO) {
//            _loopBlock.getContent().setFinished(true);
////        } else {
////            rw_.setRead(forLoopLoc_.getFirstChild());
//        }
    }

    static void processSwitch(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, String _label, RendOperationNodeListOff _value, RendAbsSwitchBlock _bl) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(_bl)) {
            _bl.processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ip_.setOffset(_value.getOffset());
        Struct arg_ = RenderExpUtil.getFinalArg(_value.getList(), _ctx, _rendStack);
        if (arg_ == null) {
            return;
        }
        RendSwitchBlockStack if_ = new RendSwitchBlockStack();
        if_.setLabel(_label);
        RendBlock n_ = _bl.getFirstChild();
        CustList<RendParentBlock> children_ = children(if_, n_);
        if_.setBlock(_bl);
        RendParentBlock found_ = tryFind(_ctx, _rendStack, arg_, children_, _bl);
        if (found_== null) {
            if_.setCurrentVisitedBlock(_bl);
        } else {
            if (_bl instanceof RendSwitchInstBlock) {
                if_.setLastVisitedBlock(found_);
            }
            rw_.setRead(found_);
            if_.setCurrentVisitedBlock(found_);
        }
        ip_.addBlock(if_);
    }

    private static RendParentBlock tryFind(ContextEl _ctx, RendStackCall _rendStack, Struct _arg, CustList<RendParentBlock> _children, RendAbsSwitchBlock _bl) {
        RendParentBlock def_ = null;
        CustList<RendParentBlock> filtered_ = new CustList<RendParentBlock>();
        for (RendParentBlock b: _children) {
            if (!(b instanceof RendDefaultCondition)) {
                filtered_.add(b);
            } else {
                def_ = b;
            }
        }
        RendParentBlock found_ = null;
        for (RendParentBlock b: filtered_) {
            found_ = tryFind(_ctx,_rendStack, found_, b, _arg);
        }
        if (found_ != null) {
            return found_;
        }
        if (def_ != null) {
            ImportingPage ip_ = _rendStack.getLastPage();
            String var_ = ((RendDefaultCondition) def_).getVariableName();
            if (!var_.isEmpty()) {
                putVar(_arg, ip_, var_, _rendStack.formatVarType( _bl.getInstanceTest()));
            }
        }
        return def_;
    }

    private static CustList<RendParentBlock> children(RendSwitchBlockStack _if, RendBlock _n) {
        RendBlock n_ = _n;
        CustList<RendParentBlock> children_;
        children_ = new CustList<RendParentBlock>();
        while (n_ != null) {
            if (n_ instanceof RendParentBlock) {
                children_.add((RendParentBlock) n_);
                _if.setLastVisitedBlock((RendParentBlock) n_);
            }
            n_ = n_.getNextSibling();
        }
        return children_;
    }

    private static RendParentBlock tryFind(ContextEl _cont, RendStackCall _rendStack, RendParentBlock _found, RendParentBlock _in, Struct _arg) {
        if (_found != null) {
            return _found;
        }
        return procTypeVar(_cont,_rendStack, (RendAbstractCaseCondition) _in, _arg);
    }

    private static RendParentBlock procTypeVar(ContextEl _cont, RendStackCall _rendStack, RendAbstractCaseCondition _in, Struct _arg) {
        boolean safe_ = ExecHelperBlocks.first(0, _cont,_rendStack,_in.getContent(),_arg,false) > -1;
        if (!safe_) {
            return null;
        }
        RendOperationNodeListOff exp_ = _in.getExp();
        CustList<RendDynOperationNode> list_ = exp_.getList();
        if (list_.isEmpty()) {
            return _in;
        }
        ImportingPage ip_ = _rendStack.getLastPage();
        ip_.setOffset(exp_.getOffset());
        Struct visit_ = RenderExpUtil.getFinalArg(list_, _cont, _rendStack);
        if (visit_ == null || BooleanStruct.isFalse(visit_)) {
            String varName_ = _in.getContent().getVariableName();
            if (!varName_.isEmpty()) {
                ip_.removeRefVar(varName_);
            }
            return null;
        }
        return _in;
    }

    private static void putVar(Struct _struct, ImportingPage _ip, String _var, String _importedClassName) {
        _ip.putValueVar(_var, new VariableWrapper(LocalVariable.newLocalVariable(_struct, _importedClassName)));
    }

}
