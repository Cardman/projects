package code.bean.nat.exec.blocks;

import code.bean.nat.*;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatAbstractAffectOperation;
import code.bean.nat.exec.opers.NatSettableFieldOperation;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.*;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.*;
import code.formathtml.util.FieldUpdates;
import code.formathtml.util.NodeContainer;
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

    public static String res(RendDocumentBlock _rend, Configuration _conf, NatRendStackCall _rendStackCall, String _beanName, Struct _bean) {
        NatImportingPage ip_ = new NatImportingPage();
        int tabWidth_ = _conf.getTabWidth();
        ip_.setBeanName(_beanName);
        ip_.setGlobalArgumentStruct(Argument.getNull(_bean));
        _rendStackCall.addPage(ip_);
        FullDocument doc_ = DocumentBuilder.newXmlDocument(tabWidth_);
        RendBlock.appendChild(doc_, (Element)null, _rend.getElt());
        RendReadWrite rw_ = new RendReadWrite();
        rw_.setConf(_rendStackCall.getFormParts());
        rw_.setRead(_rend.getDocElt());
        rw_.setDocument(doc_);
        ip_.setRendReadWrite(rw_);
        while (true) {
            NatImportingPage p_ = _rendStackCall.getLastPage();
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
            } else {
                ((NatRendWithEl) read_).processEl(_conf, _rendStackCall);
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
    private static boolean isNextIfParts(RendBlock _n) {
        return isStrictNextIfParts(_n);
    }

    private static boolean isStrictNextIfParts(RendBlock _n) {
        return _n instanceof NatRendElseIfCondition || _n instanceof NatRendElseCondition;
    }

    public static void processBlockAndRemove(NatRendStackCall _rendStackCall, RendBlock _rendBlock) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.removeRendLastBlock();
        processBlock(_rendStackCall, _rendBlock);
    }

    public static void processBlock(NatRendStackCall _rendStackCall, RendBlock _rendBlock) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock nextSiblingNat_ = _rendBlock.getNextSibling();
        if (nextSiblingNat_ != null) {
            rw_.setRead(nextSiblingNat_);
            return;
        }
        RendParentBlock par_ = _rendBlock.getParent();
        RendAbstractStask lastStackNat_ = ip_.tryGetRendLastStack();
        if (lastStackNat_ != null) {
            rw_.setRead(par_);
            if (lastStackNat_ instanceof RendLoopBlockStack) {
                processLastElementLoop(_rendStackCall, par_, (RendLoopBlockStack) lastStackNat_);
            }
            if (lastStackNat_ instanceof RendIfStack) {
                nextIfStack(rw_, (RendIfStack) lastStackNat_);
            }
            return;
        }
        ip_.setNullRendReadWrite();
    }

    private static void nextIfStack(RendReadWrite _rw, RendIfStack _lastStack) {
        _rw.setRead(_lastStack.getLastBlock());
    }

    private static void processLastElementLoop(NatRendStackCall _rendStackCall, RendParentBlock _par, RendLoopBlockStack _lastStack) {
        if (_par instanceof NatRendAbstractForEachLoop) {
            ((NatRendAbstractForEachLoop) _par).processLastElementLoop(_lastStack, _rendStackCall);
        }
        if (_par instanceof NatRendForEachTable) {
            ((NatRendForEachTable) _par).processLastElementLoop(_lastStack, _rendStackCall);
        }
    }

    static void processVisitedLoop(RendBlock _next, NatRendStackCall _stackCall) {
        processBlockAndRemove(_stackCall, _next);
    }

    static void processIf(NatRendStackCall _rendStack, String _label, NatRendIfCondition _cond) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(_cond)) {
            processBlockAndRemove(_rendStack, _cond);
            return;
        }
        ConditionReturn toEnter_ = evaluateCondition(_rendStack, _cond.getCondition());
        RendIfStack if_ = new RendIfStack();
        if_.setLabel(_label);
        if_.setLastBlock(_cond);
        RendBlock n_ = _cond.getNextSibling();
        while (isNextIfParts(n_)) {
            if_.setLastBlock((RendParentBlock) n_);
            n_ = n_.getNextSibling();
        }
        if_.setBlock(_cond);
        if_.setCurrentVisitedBlock(_cond);
        ip_.addBlock(if_);
        if (toEnter_ == ConditionReturn.YES) {
            if_.setEntered(true);
            rw_.setRead(_cond.getFirstChild());
        } else {
            RendBlock next_ = _cond.getNextSibling();
            if (isNextIfParts(next_)) {
                rw_.setRead(next_);
            }
        }
    }

    public static void processElseIf(NatRendCondition _cond, NatRendStackCall _rendStackCall) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendAbstractStask if_ = ip_.tryGetRendLastStack();
        if (!(if_ instanceof RendIfStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!((RendIfStack) if_).isEntered()) {
            ConditionReturn assert_ = evaluateCondition(_rendStackCall, _cond.getCondition());
            if (assert_ == ConditionReturn.YES) {
                ((RendIfStack) if_).setEntered(true);
                rw_.setRead(_cond.getFirstChild());
                return;
            }
        }
        RendBlock n_ = _cond.getNextSibling();
        if (isStrictNextIfParts(n_)) {
            rw_.setRead(n_);
            return;
        }
        processBlockAndRemove(_rendStackCall, _cond);
    }

    public static void processElse(RendParentBlock _cond, NatRendStackCall _rendStackCall) {
        processEnt(_cond, _rendStackCall);
    }

    public static void processEnt(RendParentBlock _cond, NatRendStackCall _rendStackCall) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        RendAbstractStask ifNat_ = ip_.tryGetRendLastStack();
        if (!(ifNat_ instanceof RendEnteredStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        ifNat_.setCurrentVisitedBlock(_cond);
        if (!((RendEnteredStack)ifNat_).isEntered()) {
            ((RendEnteredStack)ifNat_).setEntered(true);
            ip_.getRendReadWrite().setRead(_cond.getFirstChild());
            return;
        }
        processBlockAndRemove(_rendStackCall, _cond);
    }

    private static ConditionReturn evaluateCondition(NatRendStackCall _rendStackCall, RendOperationNodeListOff _condition) {
        Argument arg_ = Argument.getNullableValue(BeanNatCommonLgNames.getAllArgs(_condition.getList(), _rendStackCall).lastValue().getArgument());
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    static Argument fetchName(Configuration _cont, Element _read, Element _write, FieldUpdates _f, NatRendStackCall _rendStackCall) {
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            return Argument.createVoid();
        }
        CustList<AbstractWrapper> wrap_ = new CustList<AbstractWrapper>();
        CustList<RendDynOperationNode> opsRead_ = _f.getOpsRead();
        IdMap<RendDynOperationNode, ArgumentsPair> args_ = BeanNatCommonLgNames.getAllArgs(opsRead_, _rendStackCall);
        RendDynOperationNode root_ = args_.lastKey();
//        if (root_ instanceof NatIdOperation) {
//            res_ = NatAbstractAffectOperation.getIdOp((RendMethodOperation) root_);
//        } else {
//            res_ = root_;
//        }
        RendDynOperationNode settable_ = NatAbstractAffectOperation.castDottedTo(root_);
        CustList<LongTreeMap<NodeContainer>> stack_ = _rendStackCall.getFormParts().getContainersMapStack();
        ArgumentsPair pair_ = args_.getValue(settable_.getOrder());
        CustList<Struct> obj_;
        if (((NatSettableFieldOperation) settable_).isIntermediateDottedOperation()) {
            obj_ = new CustList<Struct>(Argument.getNullableValue(pair_.getPreviousArgument()).getStruct());
        } else {
            obj_ = new CustList<Struct>(_rendStackCall.getLastPage().getGlobalArgument().getStruct());
        }
        StringList objClasses_ = new StringList(_f.getVarNames().getVarTypes());
//            objClasses_ = new StringList(NumParsers.getSingleNameOrEmpty(settable_.getResultClass().getNames()));
        Argument arg_ = Argument.getNullableValue(pair_.getArgument());
        CustList<Struct> allObj_ = obj_;
        return RendBlock.prStack(_cont,_write,_f, new FetchedObjs(obj_,allObj_,wrap_,objClasses_,stack_,arg_, false), _rendStackCall.getLastPage().getGlobalArgument(), _rendStackCall.getFormParts(), StringUtil.concat(_rendStackCall.getLastPage().getBeanName(), RendBlock.DOT, name_));
    }

    static void fetchValue(Configuration _cont, Element _read, Element _write, CustList<RendDynOperationNode> _ops, NatRendStackCall _rendStackCall) {
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
                String value_ = BeanNatLgNames.processString(o_);
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
        return BeanNatLgNames.processString(new Argument(_instance));
    }

    public static void processLink(Configuration _cont, Element _nextWrite, Element _read, ExecTextPart _textPart, NatRendStackCall _rendStackCall) {
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
        RendBlock.incrAncNb(_cont, _nextWrite, _rendStackCall.getFormParts());
    }

    public static void feed(StringList _varNames, CustList<RendDynOperationNode> _anc, NatRendStackCall _rendStackCall) {
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
