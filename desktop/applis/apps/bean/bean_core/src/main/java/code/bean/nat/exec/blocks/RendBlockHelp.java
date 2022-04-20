package code.bean.nat.exec.blocks;

import code.bean.nat.*;
import code.bean.nat.exec.*;
import code.bean.nat.exec.opers.NatAbstractAffectOperation;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.bean.nat.exec.opers.NatSettableFieldOperation;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.FormParts;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.stacks.RendReadWrite;
import code.formathtml.util.NodeInformations;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.FullDocument;
import code.util.*;
import code.util.core.StringUtil;

public final class RendBlockHelp {
    static final String EMPTY_STRING = "";
    static final String DOT = ".";
    static final String CALL_METHOD = "$";

    private RendBlockHelp(){
    }

    public static String res(NatDocumentBlock _rend, Configuration _conf, NatRendStackCall _rendStackCall, String _beanName, Struct _bean) {
        NatImportingPage ip_ = new NatImportingPage();
        int tabWidth_ = _conf.getTabWidth();
        ip_.setBeanName(_beanName);
        ip_.setGlobalArgumentStruct(Argument.getNull(_bean));
        _rendStackCall.addPage(ip_);
        FullDocument doc_ = DocumentBuilder.newXmlDocument(tabWidth_);
        appendChild(doc_,(Element) null, _rend.getElt());
        NatRendReadWrite rw_ = new NatRendReadWrite();
        rw_.setConf(_rendStackCall.getFormParts());
        rw_.setRead(_rend);
        rw_.setDocument(doc_);
        ip_.setRendReadWrite(rw_);
        while (true) {
            NatImportingPage p_ = _rendStackCall.getLastPage();
            NatRendReadWrite rendReadWrite_ = p_.getRendReadWrite();
            NatBlock read_ = null;
            if (rendReadWrite_ != null) {
                read_ = rendReadWrite_.getRead();
            }
            if (read_ == null) {
                _rendStackCall.removeLastPage();
                if (_rendStackCall.getImporting().isEmpty()) {
                    break;
                }
            } else {
                read_.processEl(_conf, _rendStackCall);
            }
        }
        _rendStackCall.getHtmlPage().set(_rendStackCall.getFormParts());
        _rendStackCall.setBeanName(doc_.getDocumentElement().getAttribute(StringUtil.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrBean())));
        doc_.getDocumentElement().removeAttribute(StringUtil.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrBean()));
        doc_.getDocumentElement().removeAttribute(StringUtil.concat(_conf.getPrefix(), _conf.getRendKeyWords().getAttrAlias()));
        _rendStackCall.setDocument(doc_);
        _rendStackCall.clearPages();
        return doc_.export();
    }
    public static Element appendChild(Document _doc, RendReadWrite _rw, Element _read) {
        return appendChild(_doc,_rw.getWrite(),_read);
    }
    public static Element appendChild(Document _doc, Element _parent, Element _read) {
        String tagName_ = _read.getTagName();
        Element currentNode_ = _doc.createElement(tagName_);
        RendBlock.setNormalAttributes(_read, currentNode_);
        RendBlock.simpleAppendChild(_doc, _parent, currentNode_);
        return currentNode_;
    }
    private static boolean isNextIfParts(NatBlock _n) {
        return isStrictNextIfParts(_n);
    }

    private static boolean isStrictNextIfParts(NatBlock _n) {
        return _n instanceof NatRendElseIfCondition || _n instanceof NatRendElseCondition;
    }

    public static void processBlockAndRemove(NatRendStackCall _rendStackCall, NatBlock _rendBlock) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.removeRendLastBlock();
        processBlock(_rendStackCall, _rendBlock);
    }

    public static void processBlock(NatRendStackCall _rendStackCall, NatBlock _rendBlock) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        NatBlock nextSiblingNat_ = _rendBlock.getNextSibling();
        if (nextSiblingNat_ != null) {
            rw_.setRead(nextSiblingNat_);
            return;
        }
        NatParentBlock par_ = _rendBlock.getParent();
        NatAbstractStask lastStackNat_ = ip_.tryGetRendLastStack();
        if (lastStackNat_ != null) {
            rw_.setRead(par_);
            if (lastStackNat_ instanceof NatLoopBlockStack) {
                processLastElementLoop(_rendStackCall, par_, (NatLoopBlockStack) lastStackNat_);
            }
            if (lastStackNat_ instanceof NatIfStack) {
                nextIfStack(rw_, (NatIfStack) lastStackNat_);
            }
            return;
        }
        ip_.setNullRendReadWrite();
    }

    private static void nextIfStack(NatRendReadWrite _rw, NatIfStack _lastStack) {
        _rw.setRead(_lastStack.getLastBlock());
    }

    private static void processLastElementLoop(NatRendStackCall _rendStackCall, NatParentBlock _par, NatLoopBlockStack _lastStack) {
        if (_par instanceof NatRendAbstractForEachLoop) {
            ((NatRendAbstractForEachLoop) _par).processLastElementLoop(_lastStack, _rendStackCall);
        }
        if (_par instanceof NatRendForEachTable) {
            ((NatRendForEachTable) _par).processLastElementLoop(_lastStack, _rendStackCall);
        }
    }

    static void processVisitedLoop(NatBlock _next, NatRendStackCall _stackCall) {
        processBlockAndRemove(_stackCall, _next);
    }

    static void processIf(NatRendStackCall _rendStack, NatRendIfCondition _cond) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(_cond)) {
            processBlockAndRemove(_rendStack, _cond);
            return;
        }
        ConditionReturn toEnter_ = evaluateCondition(_rendStack, _cond.getCondition());
        NatIfStack if_ = new NatIfStack();
        if_.setLastBlock(_cond);
        NatBlock n_ = _cond.getNextSibling();
        while (isNextIfParts(n_)) {
            if_.setLastBlock((NatParentBlock) n_);
            n_ = n_.getNextSibling();
        }
        if_.setBlock(_cond);
        if_.setCurrentVisitedBlock(_cond);
        ip_.addBlock(if_);
        if (toEnter_ == ConditionReturn.YES) {
            if_.setEntered(true);
            rw_.setRead(_cond.getFirstChild());
        } else {
            NatBlock next_ = _cond.getNextSibling();
            if (isNextIfParts(next_)) {
                rw_.setRead(next_);
            }
        }
    }

    public static void processElseIf(NatRendCondition _cond, NatRendStackCall _rendStackCall) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        NatAbstractStask if_ = ip_.tryGetRendLastStack();
        if (!(if_ instanceof NatIfStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!((NatIfStack) if_).isEntered()) {
            ConditionReturn assert_ = evaluateCondition(_rendStackCall, _cond.getCondition());
            if (assert_ == ConditionReturn.YES) {
                ((NatIfStack) if_).setEntered(true);
                rw_.setRead(_cond.getFirstChild());
                return;
            }
        }
        NatBlock n_ = _cond.getNextSibling();
        if (isStrictNextIfParts(n_)) {
            rw_.setRead(n_);
            return;
        }
        processBlockAndRemove(_rendStackCall, _cond);
    }

    public static void processElse(NatParentBlock _cond, NatRendStackCall _rendStackCall) {
        processEnt(_cond, _rendStackCall);
    }

    public static void processEnt(NatParentBlock _cond, NatRendStackCall _rendStackCall) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        NatAbstractStask ifNat_ = ip_.tryGetRendLastStack();
        if (!(ifNat_ instanceof NatIfStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        ifNat_.setCurrentVisitedBlock(_cond);
        if (!((NatIfStack)ifNat_).isEntered()) {
            ((NatIfStack)ifNat_).setEntered(true);
            ip_.getRendReadWrite().setRead(_cond.getFirstChild());
            return;
        }
        processBlockAndRemove(_rendStackCall, _cond);
    }

    private static ConditionReturn evaluateCondition(NatRendStackCall _rendStackCall, NatRendOperationNodeListOff _condition) {
        Argument arg_ = Argument.getNullableValue(BeanNatCommonLgNames.getAllArgs(_condition.getList(), _rendStackCall).lastValue().getArgument());
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    static Argument fetchName(Configuration _cont, Element _read, Element _write, NatFieldUpdates _f, NatRendStackCall _rendStackCall) {
        if (_f.getOpsWrite() == null) {
            return Argument.createVoid();
        }
        CustList<NatExecOperationNode> opsRead_ = _f.getOpsRead();
        IdMap<NatExecOperationNode, NatArgumentsPair> args_ = BeanNatCommonLgNames.getAllArgs(opsRead_, _rendStackCall);
        NatExecOperationNode root_ = args_.lastKey();
//        if (root_ instanceof NatIdOperation) {
//            res_ = NatAbstractAffectOperation.getIdOp((RendMethodOperation) root_);
//        } else {
//            res_ = root_;
//        }
        NatExecOperationNode settable_ = NatAbstractAffectOperation.castDottedTo(root_);
        CustList<LongTreeMap<NatNodeContainer>> stack_ = _rendStackCall.getFormParts().getContainersMapStack();
        NatArgumentsPair pair_ = args_.getValue(settable_.getOrder());
        CustList<Struct> obj_;
        if (((NatSettableFieldOperation) settable_).isIntermediateDottedOperation()) {
            obj_ = new CustList<Struct>(Argument.getNullableValue(pair_.getPreviousArgument()).getStruct());
        } else {
            obj_ = new CustList<Struct>(_rendStackCall.getLastPage().getGlobalArgument().getStruct());
        }
//            objClasses_ = new StringList(NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames()));
        Argument arg_ = Argument.getNullableValue(pair_.getArgument());
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        return prStack(_cont,_write,_f, new NatFetchedObjs(obj_, stack_,arg_), _rendStackCall.getLastPage().getGlobalArgument(),_rendStackCall, StringUtil.concat(_rendStackCall.getLastPage().getBeanName(), RendBlock.DOT, name_));
    }

    public static Argument prStack(Configuration _cont, Element _write, NatFieldUpdates _f, NatFetchedObjs _fetch, Argument _globalArgument, NatRendStackCall _rend, String _concat) {
        if ( _fetch.getStack().isEmpty()) {
            return _fetch.getArg();
        }
        long found_ = -1;
        if (_f.isRad()) {
            for (EntryCust<Long, NatNodeContainer> e: _fetch.getStack().last().entryList()) {
                if (e.getValue().getOpsWrite() == _f.getOpsWrite()) {
                    found_ = e.getKey();
                    break;
                }
            }
        }
        FormParts formParts_ = _rend.getFormParts();
        if (found_ == -1) {
            NatCaller opsWrite_ = _f.getOpsWrite();
            NatNodeContainer nodeCont_ = new NatNodeContainer();
            Longs inputs_ = formParts_.getInputs();
            long currentInput_ = inputs_.last();
            long old_ = currentInput_;
            nodeCont_.setAllObject(_fetch.getObj());
            nodeCont_.setOpsWrite(opsWrite_);
            nodeCont_.setBean(_globalArgument.getStruct());
            NodeInformations nodeInfos_ = nodeCont_.getNodeInformation();
            nodeInfos_.setValidator(_write.getAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrValidator())));
            nodeInfos_.setId(RendBlock.getId(_cont,_write));
            nodeInfos_.setInputClass(RendBlock.getInputClass(_cont, _write, _f));
            _fetch.getStack().last().put(currentInput_, nodeCont_);
            currentInput_++;
            inputs_.set(inputs_.getLastIndex(),currentInput_);
            _write.setAttribute(_cont.getRendKeyWords().getAttrNi(), Long.toString(old_));
        } else {
            _write.setAttribute(_cont.getRendKeyWords().getAttrNi(), Long.toString(found_));
        }
//        attributesNames_.removeAllString(NUMBER_INPUT);
        _write.setAttribute(_cont.getRendKeyWords().getAttrName(), _concat);
        return _fetch.getArg();
    }

    static void fetchValue(Configuration _cont, Element _read, Element _write, CustList<NatExecOperationNode> _ops, NatRendStackCall _rendStackCall) {
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            return;
        }
//        if (_ops.isEmpty()) {
//            return;
//        }
        if (StringUtil.quickEq(_read.getTagName(),_cont.getRendKeyWords().getKeyWordInput())) {
            Argument o_ = Argument.getNullableValue(BeanNatCommonLgNames.getAllArgs(_ops, _rendStackCall).lastValue().getArgument());
            if (StringUtil.quickEq(_read.getAttribute(_cont.getRendKeyWords().getAttrType()),_cont.getRendKeyWords().getValueCheckbox())) {
                if (Argument.isTrueValue(o_)) {
                    _write.setAttribute(_cont.getRendKeyWords().getAttrChecked(), _cont.getRendKeyWords().getAttrChecked());
                } else {
                    _write.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
                }
            } else {
                o_ = convertField(o_);
                String value_ = BeanNatCommonLgNames.processString(o_);
                _write.setAttribute(_cont.getRendKeyWords().getAttrValue(), value_);
            }
        }
        _write.removeAttribute(StringUtil.concat(_cont.getPrefix(),_cont.getRendKeyWords().getAttrVarValue()));
    }

    private static Argument convertField(Argument _o) {
        Struct o_ = _o.getStruct();
        if (o_ == NullStruct.NULL_VALUE) {
            o_ = new StringStruct(EMPTY_STRING);
        }
        return new Argument(o_);
    }

    static Argument iteratorMultTable(Struct _arg) {
        ArrayStruct array_ = BeanNatCommonLgNames.getArray(_arg);
        return new Argument(new SimpleItrStruct(StringUtil.concat(BeanNatCommonLgNames.TYPE_ITERATOR,StringExpUtil.TEMPLATE_BEGIN, BeanNatCommonLgNames.TYPE_ENTRY,StringExpUtil.TEMPLATE_BEGIN, "?,?",StringExpUtil.TEMPLATE_END,StringExpUtil.TEMPLATE_END),array_));
    }

    static Argument nasNextCom(Struct _arg) {
        SimpleItrStruct simpleItrStruct_ = BeanNatCommonLgNames.getSimpleItrStruct(_arg);
        return new Argument(BooleanStruct.of(simpleItrStruct_.hasNext()));
    }

    static Argument nextCom(Struct _arg) {
        SimpleItrStruct simpleItrStruct_ = BeanNatCommonLgNames.getSimpleItrStruct(_arg);
        Struct resObj_ = simpleItrStruct_.next();
        return new Argument(resObj_);
    }

    static Argument first(Struct _arg) {
        PairStruct pairStruct_ = BeanNatCommonLgNames.getPairStruct(_arg);
        Struct resObj_ = pairStruct_.getFirst();
        return new Argument(resObj_);
    }

    static Argument second(Struct _arg) {
        PairStruct pairStruct_ = BeanNatCommonLgNames.getPairStruct(_arg);
        Struct resObj_ = pairStruct_.getSecond();
        return new Argument(resObj_);
    }

    static Argument iterator(Struct _arg) {
        ArrayStruct array_ = BeanNatCommonLgNames.getArray(_arg);
        return new Argument(new SimpleItrStruct(StringUtil.concat(BeanNatCommonLgNames.TYPE_ITERATOR, StringExpUtil.TEMPLATE_BEGIN,"?",StringExpUtil.TEMPLATE_END),array_));
    }

    static String getStringKey(Struct _instance) {
        return BeanNatCommonLgNames.processString(new Argument(_instance));
    }

    public static void processLink(Configuration _cont, Element _nextWrite, Element _read, NatExecTextPart _textPart, NatRendStackCall _rendStackCall) {
        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()));
        if (!href_.startsWith(CALL_METHOD)) {
            RendBlock.procCstAnc(_cont, _nextWrite, _rendStackCall.getFormParts());
            return;
        }
        StringList alt_ = NatRenderingText.renderAltListNat(_textPart, _rendStackCall);
        StringList arg_ = RendBlock.arg(alt_);
        _rendStackCall.getFormParts().getAnchorsArgs().add(arg_);
        String render_ = StringUtil.join(alt_,"");
        String beanName_ = _rendStackCall.getLastPage().getBeanName();
        _nextWrite.setAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(CALL_METHOD,beanName_,DOT,render_));
        _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrHref(), EMPTY_STRING);
        RendBlock.incrAncNb(_cont, _nextWrite, _rendStackCall.getFormParts().getIndexes());
    }

    public static void feed(StringList _varNames, CustList<NatExecOperationNode> _anc, NatRendStackCall _rendStackCall) {
        _rendStackCall.getFormParts().getCallsExps().add(_anc);
        _rendStackCall.getFormParts().getAnchorsVars().add(_varNames);
    }

    public static void setupOverrides(StringMap<SpecialNatClass> _standardsTypes) {
        buildInherits(_standardsTypes);
    }

    public static void buildInherits(StringMap<SpecialNatClass> _standardsTypes){
        for (EntryCust<String, SpecialNatClass> s: _standardsTypes.entryList()) {
            buildInherits(s.getValue(), _standardsTypes);
        }
    }

    private static void buildInherits(SpecialNatClass _type, StringMap<SpecialNatClass> _standardsTypes) {
        feedSupers(_type, _type.getAllSuperTypes(), _standardsTypes);
    }

    private static void feedSupers(SpecialNatClass _type, StringList _types, StringMap<SpecialNatClass> _standardsTypes) {
        StringList currentSuperTypes_ = new StringList(_type.getSuperClass());
        _types.addAllElts(currentSuperTypes_);
        while (true) {
            StringList newSuperTypes_ = new StringList();
            for (String c: currentSuperTypes_) {
                SpecialNatClass st_ = _standardsTypes.getVal(c);
                if (st_ == null) {
                    continue;
                }
                String superClass_ = st_.getSuperClass();
                newSuperTypes_.add(superClass_);
                _types.add(superClass_);
            }
            if (newSuperTypes_.isEmpty()) {
                break;
            }
            currentSuperTypes_ = newSuperTypes_;
        }
    }
}
