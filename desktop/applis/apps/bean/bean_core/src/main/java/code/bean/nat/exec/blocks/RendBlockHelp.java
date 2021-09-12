package code.bean.nat.exec.blocks;

import code.bean.nat.*;
import code.bean.nat.exec.opers.NatAbstractAffectOperation;
import code.bean.nat.exec.opers.NatSettableFieldOperation;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.*;
import code.formathtml.exec.opers.*;
import code.formathtml.stacks.*;
import code.formathtml.util.*;
import code.sml.Element;
import code.util.*;
import code.util.core.StringUtil;

public final class RendBlockHelp {
    static final String EMPTY_STRING = "";
    static final String DOT = ".";
    static final String CALL_METHOD = "$";

    private RendBlockHelp(){
    }

    private static boolean isNextIfParts(RendBlock _n) {
        return isStrictNextIfParts(_n);
    }

    private static boolean isStrictNextIfParts(RendBlock _n) {
        return _n instanceof NatRendElseIfCondition || _n instanceof NatRendElseCondition;
    }

    public static void processBlockAndRemove(ContextEl _ctx, RendStackCall _rendStackCall, RendBlock _rendBlock) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.removeRendLastBlock();
        processBlock(_ctx, _rendStackCall, _rendBlock);
    }

    public static void processBlock(ContextEl _ctx, RendStackCall _rendStackCall, RendBlock _rendBlock) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
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
                removeLocalVarsLoop(ip_, par_);
                processLastElementLoop(_ctx, _rendStackCall, par_, (RendLoopBlockStack) lastStackNat_);
            }
            if (lastStackNat_ instanceof RendIfStack) {
                nextIfStack(ip_, rw_, par_, (RendIfStack) lastStackNat_);
            }
            return;
        }
        ip_.setNullRendReadWrite();
    }

    private static void nextIfStack(ImportingPage _ip, RendReadWrite _rw, RendParentBlock _par, RendIfStack _lastStack) {
        if (_par instanceof NatRendIfCondition) {
            _par.removeAllVars(_ip);
        }
        if (_par instanceof NatRendElseIfCondition) {
            _par.removeAllVars(_ip);
        }
        if (_par instanceof NatRendElseCondition) {
            _par.removeAllVars(_ip);
        }
        if (_par instanceof NatRendElement) {
            _par.removeAllVars(_ip);
        }
        _rw.setRead(_lastStack.getLastBlock());
    }

    private static void processLastElementLoop(ContextEl _ctx, RendStackCall _rendStackCall, RendParentBlock _par, RendLoopBlockStack _lastStack) {
        if (_par instanceof NatRendAbstractForEachLoop) {
            ((NatRendAbstractForEachLoop) _par).processLastElementLoop(_ctx, _lastStack, _rendStackCall);
        }
        if (_par instanceof NatRendForEachTable) {
            ((NatRendForEachTable) _par).processLastElementLoop(_ctx, _lastStack, _rendStackCall);
        }
    }

    static void processVisitedLoop(RendBlock _next, ContextEl _context, RendStackCall _stackCall) {
        processBlockAndRemove(_context,_stackCall, _next);
    }
    private static void removeLocalVarsLoop(ImportingPage _ip, RendParentBlock _par) {
        if (_par instanceof NatRendAbstractForEachLoop) {
            _par.removeLocalVars(_ip);
        }
        if (_par instanceof NatRendForEachTable) {
            _par.removeLocalVars(_ip);
        }
    }

    static void processIf(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack, String _label, NatRendIfCondition _cond) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(_cond)) {
            processBlockAndRemove(_ctx, _rendStack, _cond);
            return;
        }
        ConditionReturn toEnter_ = evaluateCondition(_cont, _stds, _ctx, _rendStack, _cond.getCondition());
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

    public static void processElseIf(Configuration _conf, BeanLgNames _stds, ContextEl _cont, NatRendCondition _cond, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendAbstractStask if_ = ip_.tryGetRendLastStack();
        if (!(if_ instanceof RendIfStack)) {
            ip_.setNullRendReadWrite();
            return;
        }
        if_.setCurrentVisitedBlock(_cond);
        if (!((RendIfStack) if_).isEntered()) {
            ConditionReturn assert_ = evaluateCondition(_conf,_stds,_cont, _rendStackCall, _cond.getCondition());
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
        processBlockAndRemove(_cont, _rendStackCall, _cond);
    }

    public static void processElse(ContextEl _cont, RendParentBlock _cond, RendStackCall _rendStackCall) {
        processEnt(_cont, _cond, _rendStackCall);
    }

    public static void processEnt(ContextEl _cont, RendParentBlock _cond, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
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
        processBlockAndRemove(_cont, _rendStackCall, _cond);
    }

    private static ConditionReturn evaluateCondition(Configuration _context, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall, RendOperationNodeListOff _condition) {
        ImportingPage last_ = _rendStackCall.getLastPage();
        last_.setOffset(_condition.getOffset());
        last_.setProcessingAttribute(_context.getRendKeyWords().getAttrCondition());
        Argument arg_ = RenderExpUtil.calculateReuse(_condition.getList(), _stds, _ctx, _rendStackCall);
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    static Argument fetchName(Configuration _cont, Element _read, Element _write, FieldUpdates _f, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            return Argument.createVoid();
        }
        CustList<AbstractWrapper> wrap_ = new CustList<AbstractWrapper>();
        CustList<RendDynOperationNode> opsRead_ = _f.getOpsRead();
        IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(opsRead_, _advStandards, _ctx, _rendStackCall);
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
        return RendBlock.prStack(_cont,_write,_f,_advStandards,_rendStackCall,name_,obj_,allObj_,wrap_,objClasses_,stack_,arg_, false);
    }

    static void fetchValue(Configuration _cont, Element _read, Element _write, CustList<RendDynOperationNode> _ops, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        String name_ = _read.getAttribute(_cont.getRendKeyWords().getAttrName());
        if (name_.isEmpty()) {
            return;
        }
//        if (_ops.isEmpty()) {
//            return;
//        }
        if (StringUtil.quickEq(_read.getTagName(),_cont.getRendKeyWords().getKeyWordInput())) {
            Argument o_ = RenderExpUtil.calculateReuse(_ops, _advStandards, _ctx, _rendStackCall);
            if (StringUtil.quickEq(_read.getAttribute(_cont.getRendKeyWords().getAttrType()),_cont.getRendKeyWords().getValueCheckbox())) {
                if (Argument.isTrueValue(o_)) {
                    _write.setAttribute(_cont.getRendKeyWords().getAttrChecked(), _cont.getRendKeyWords().getAttrChecked());
                } else {
                    _write.removeAttribute(_cont.getRendKeyWords().getAttrChecked());
                }
            } else {
                o_ = convertField(o_);
                String value_ = BeanNatLgNames.processString(o_, _ctx);
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

    static Argument iteratorMultTable(Struct _arg, ContextEl _ctx) {
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_arg, _ctx);
        return new Argument(new SimpleItrStruct(StringUtil.concat(BeanNatCommonLgNames.TYPE_ITERATOR,StringExpUtil.TEMPLATE_BEGIN, BeanNatCommonLgNames.TYPE_ENTRY,StringExpUtil.TEMPLATE_BEGIN, "?,?",StringExpUtil.TEMPLATE_END,StringExpUtil.TEMPLATE_END),array_));
    }

    static Argument nasNextCom(Struct _arg, ContextEl _ctx) {
        SimpleItrStruct simpleItrStruct_ = BeanNatCommonLgNames.getSimpleItrStruct(_arg, _ctx);
        return new Argument(BooleanStruct.of(simpleItrStruct_.hasNext()));
    }

    static Argument nextCom(Struct _arg, ContextEl _ctx) {
        SimpleItrStruct simpleItrStruct_ = BeanNatCommonLgNames.getSimpleItrStruct(_arg, _ctx);
        Struct resObj_ = simpleItrStruct_.next();
        return new Argument(resObj_);
    }

    static Argument first(Struct _arg, ContextEl _ctx) {
        PairStruct pairStruct_ = BeanNatCommonLgNames.getPairStruct(_arg, _ctx);
        Struct resObj_ = pairStruct_.getFirst();
        return new Argument(resObj_);
    }

    static Argument second(Struct _arg, ContextEl _ctx) {
        PairStruct pairStruct_ = BeanNatCommonLgNames.getPairStruct(_arg, _ctx);
        Struct resObj_ = pairStruct_.getSecond();
        return new Argument(resObj_);
    }

    static Argument iterator(Struct _arg, ContextEl _ctx) {
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_arg, _ctx);
        return new Argument(new SimpleItrStruct(StringUtil.concat(BeanNatCommonLgNames.TYPE_ITERATOR, StringExpUtil.TEMPLATE_BEGIN,"?",StringExpUtil.TEMPLATE_END),array_));
    }

    static String getStringKey(Struct _instance, BeanLgNames _advStandards, ContextEl _ctx) {
        ResultErrorStd res_ = ((BeanNatLgNames)_advStandards).getName(_ctx, _instance);
        Struct str_ = res_.getResult();
        return BeanNatLgNames.processString(new Argument(str_), _ctx);
    }

    public static void processLink(Configuration _cont, Element _nextWrite, Element _read, ExecTextPart _textPart, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        String href_ = _read.getAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()));
        if (!href_.startsWith(CALL_METHOD)) {
            RendBlock.procCstAnc(_cont, _nextWrite, _rendStackCall);
            return;
        }
        StringList alt_ = NatRenderingText.renderAltListNat(_textPart, _advStandards, _ctx, _rendStackCall);
        StringList arg_ = RendBlock.arg(alt_);
        _rendStackCall.getFormParts().getAnchorsArgs().add(arg_);
        String render_ = StringUtil.join(alt_,"");
        String beanName_ = _rendStackCall.getLastPage().getBeanName();
        _nextWrite.setAttribute(StringUtil.concat(_cont.getPrefix(), _cont.getRendKeyWords().getAttrCommand()), StringUtil.concat(CALL_METHOD,beanName_,DOT,render_));
        _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrHref(), EMPTY_STRING);
        RendBlock.incrAncNb(_cont, _nextWrite, _rendStackCall);
    }

    public static void feed(StringList _varNames, CustList<RendDynOperationNode> _anc, RendStackCall _rendStackCall) {
        _rendStackCall.getFormParts().getCallsExps().add(_anc);
        _rendStackCall.getFormParts().getAnchorsVars().add(_varNames);
    }

    public static void setupOverrides(StringMap<SpecialNatClass> _standardsTypes) {
        buildInherits(_standardsTypes);
    }

    public static void buildInherits(StringMap<SpecialNatClass> _standardsTypes){
        for (EntryCust<String, SpecialNatClass> s: _standardsTypes.entryList()) {
            buildInherits((SpecialNatClass) s.getValue(), _standardsTypes);
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
                SpecialNatClass st_ = (SpecialNatClass) _standardsTypes.getVal(c);
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
