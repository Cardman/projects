package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.InstanceParamChecker;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
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
        String className_ = metaInfo.getDeclaringClass();
        String id_ = StringExpUtil.getIdFromAllTypes(className_);
        GeneType type_ = _context.getClassBody(id_);
        boolean static_ = type_.withoutInstance();
        String res_ = getResolved();
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
                if (args_.size() != mid_.getParametersTypes().size()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args_.size(), mid_.getParametersTypes().size()).toString(), null_, _stack)));
                    return false;
                }
                previous_ = Argument.createVoid();
            } else {
                if (args_.size() != 1 + mid_.getParametersTypes().size()) {
                    String null_ = stds_.getContent().getCoreNames().getAliasBadArgNumber();
                    _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_context, ExecTemplates.countDiff(args_.size(), 1 + mid_.getParametersTypes().size()).toString(), null_, _stack)));
                    return false;
                }
                previous_ = args_.first();
                args_ = args_.mid(1);
            }
            Argument arg_ = Argument.createVoid();
            ExecTypeFunction pair_ = metaInfo.getPair();
            ExecRootBlock execSuperClass_ = pair_.getType();
            if (execSuperClass_ != null) {
                ArgumentListCall l_ = ExecTemplates.wrapAndCallDirect(pair_,res_,previous_,args_,_context, null);
                arg_ = new InstanceParamChecker(pair_, l_, "", -1).checkParams(res_, previous_, null, _context, _stack);
            }
            if (metaInfo.getStandardType() != null) {
                ArgumentListCall l_ = new ArgumentListCall();
                l_.addAllArgs(args_);
                arg_ = ExecInvokingOperation.instancePrepareStd(_context, mid_, l_, _stack);
            }
            if (_context.callsOrException(_stack)) {
                setWrapException(_stack.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

    @Override
    protected String getDeclaringClass() {
        return metaInfo.getDeclaringClass();
    }
}
