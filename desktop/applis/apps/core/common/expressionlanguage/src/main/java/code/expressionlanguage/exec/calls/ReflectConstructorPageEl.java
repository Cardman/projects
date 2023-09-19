package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.inherits.ReflectInstanceParamChecker;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.CustList;

public final class ReflectConstructorPageEl extends AbstractReflectConstructorPageEl {

    private final ConstructorMetaInfo metaInfo;

    private final ArrayRefState arrRef;
    public ReflectConstructorPageEl(ConstructorMetaInfo _metaInfo, ArrayRefState _a) {
        super(false);
        metaInfo = _metaInfo;
        setGlobalArgumentStruct(_metaInfo);
        arrRef = _a;
    }

    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!keep(metaInfo.getDeclType(),_context, _stack)) {
            return false;
        }
        LgNames stds_ = _context.getStandards();
        GeneType type_ = metaInfo.getDeclType();
        boolean static_ = type_.withoutInstance();
        setWrapException(false);
        if (!arrRef.isFalseArr()) {
            setCheckingEntryExit(true);
            if (checkParams(_stack,metaInfo)) {
                return false;
            }
            setCheckingEntryExit(false);
        }
        if (!isCalledMethod()) {
            setCalledMethod(true);
            ConstructorId mid_ = metaInfo.getRealId();
            ArrayStruct struct_ = arrRef.getArray();
            if (arrRef.isFalseArr()) {
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_, _stack)));
                return false;
            }
            CustList<Argument> args_ = struct_.listArgs();
            Argument previous_;
            if (static_) {
                if (args_.size() != mid_.getParametersTypesLength()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args_.size(), mid_.getParametersTypesLength()).toString(), null_, _stack)));
                    return false;
                }
                previous_ = Argument.createVoid();
                return callPhase(_context, _stack, struct_, previous_,0);
            }
            if (args_.size() != 1 + mid_.getParametersTypesLength()) {
                String null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args_.size(), 1 + mid_.getParametersTypesLength()).toString(), null_, _stack)));
                return false;
            }
            previous_ = args_.first();
            return callPhase(_context, _stack, struct_, previous_,1);
        }
        return postArg(metaInfo,_stack);
    }

    private boolean callPhase(ContextEl _context, StackCall _stack, ArrayStruct _args, Argument _previous, int _delta) {
        ExecFormattedRootBlock res_ = metaInfo.getFormatted();
        ConstructorId mid_ = metaInfo.getRealId();
        Argument arg_ = Argument.createVoid();
        ExecTypeFunction pair_ = metaInfo.getPair();
        ExecRootBlock execSuperClass_ = pair_.getType();
        if (execSuperClass_ != null) {
            new ReflectInstanceParamChecker(pair_, arrRef, "", -1,_delta).checkParams(res_, _previous, null, _context, _stack);
        }
        if (metaInfo.getStandardType() != null) {
            ArgumentListCall l_ = ArgumentListCall.wrapCall(_args.listArgs());
            arg_ = ParamCheckerUtil.instancePrepareStd(_context, metaInfo.getStandardConstructor(), mid_, l_, _stack).getValue();
        }
        return end(_context, _stack, metaInfo, arg_);
    }

    public ConstructorMetaInfo getMetaInfo() {
        return metaInfo;
    }

    @Override
    protected ExecFormattedRootBlock getFormatted() {
        return metaInfo.getFormatted();
    }

    @Override
    public int getRef() {
        return getArrRef().getRef();
    }

    public ArrayRefState getArrRef() {
        return arrRef;
    }
}
