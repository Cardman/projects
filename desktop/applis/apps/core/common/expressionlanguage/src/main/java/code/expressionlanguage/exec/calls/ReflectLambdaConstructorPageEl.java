package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.InstanceParamChecker;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.ConstructorMetaInfo;

public final class ReflectLambdaConstructorPageEl extends AbstractReflectConstructorPageEl {

    private boolean calledMethod;
    private final ConstructorMetaInfo metaInfo;

    private final Argument argument;
    private final ArgumentListCall array;

    public ReflectLambdaConstructorPageEl(Argument _argument,ArgumentListCall _array, ConstructorMetaInfo _metaInfo) {
        argument = _argument;
        array = _array;
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
        if (!keep(_context, _stack)) {
            return false;
        }
        String res_ = getResolved();
        setWrapException(false);
        if (!calledMethod) {
            calledMethod = true;
            ConstructorId mid_ = metaInfo.getRealId();
            Argument arg_ = Argument.createVoid();
            ExecTypeFunction pair_ = metaInfo.getPair();
            ExecRootBlock execSuperClass_ = pair_.getType();
            if (execSuperClass_ != null) {
                arg_ = new InstanceParamChecker(pair_, array, "", -1).checkParams(res_, argument, null, _context, _stack);
            }
            if (metaInfo.getStandardType() != null) {
                arg_ = ExecInvokingOperation.instancePrepareStd(_context, mid_, array, _stack);
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
