package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ReflectInstanceParamChecker;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReflectConstructorPageEl extends AbstractReflectConstructorPageEl {

    private boolean calledMethod;
    private final ConstructorMetaInfo metaInfo;

    private final Argument argument;

    public ReflectConstructorPageEl(Argument _argument, ConstructorMetaInfo _metaInfo) {
        argument = _argument;
        metaInfo = _metaInfo;
        setGlobalArgumentStruct(_metaInfo);
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_context, _stack)) {
            return;
        }
        setNullReadWrite();
    }

    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!keep(metaInfo.getDeclType(),_context, _stack)) {
            return false;
        }
        LgNames stds_ = _context.getStandards();
        GeneType type_ = metaInfo.getDeclType();
        boolean static_ = type_.withoutInstance();
        setWrapException(false);
        if (!calledMethod) {
            calledMethod = true;
            ConstructorId mid_ = metaInfo.getRealId();
            Struct struct_ = argument.getStruct();
            if (!(struct_ instanceof ArrayStruct)) {
                String null_;
                null_ = stds_.getContent().getCoreNames().getAliasNullPe();
                _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, null_, _stack)));
                return false;
            }
            CustList<Argument> args_ = ((ArrayStruct)struct_).listArgs();
            Argument previous_;
            if (static_) {
                if (args_.size() != mid_.getParametersTypesLength()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args_.size(), mid_.getParametersTypesLength()).toString(), null_, _stack)));
                    return false;
                }
                previous_ = Argument.createVoid();
            } else {
                if (args_.size() != 1 + mid_.getParametersTypesLength()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args_.size(), 1 + mid_.getParametersTypesLength()).toString(), null_, _stack)));
                    return false;
                }
                previous_ = args_.first();
                args_ = args_.mid(1);
            }
            return callPhase(_context, _stack, args_, previous_);
        }
        return true;
    }

    private boolean callPhase(ContextEl _context, StackCall _stack, CustList<Argument> _args, Argument _previous) {
        ExecFormattedRootBlock res_ = metaInfo.getFormatted();
        ConstructorId mid_ = metaInfo.getRealId();
        Argument arg_ = Argument.createVoid();
        ExecTypeFunction pair_ = metaInfo.getPair();
        ExecRootBlock execSuperClass_ = pair_.getType();
        if (execSuperClass_ != null) {
            arg_ = new ReflectInstanceParamChecker(pair_, _args, "", -1).checkParams(res_, _previous, null, _context, _stack);
        }
        if (metaInfo.getStandardType() != null) {
            ArgumentListCall l_ = new ArgumentListCall(_args);
            arg_ = ExecInvokingOperation.instancePrepareStd(_context, mid_, l_, _stack);
        }
        if (_context.callsOrException(_stack)) {
            setWrapException(_stack.calls());
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

    @Override
    protected GeneType getDeclaringType() {
        return metaInfo.getFormatted().getRootBlock();
    }

    @Override
    protected String getDeclaringClass() {
        return metaInfo.getFormatted().getFormatted();
    }
}
