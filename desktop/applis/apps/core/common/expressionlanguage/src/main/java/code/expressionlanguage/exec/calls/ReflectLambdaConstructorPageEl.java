package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.ConstructorMetaInfo;

public final class ReflectLambdaConstructorPageEl extends AbstractReflectConstructorPageEl {

    private boolean calledMethod;
    private ConstructorMetaInfo metaInfo;

    private final Argument argument;
    private final ArgumentListCall array;

    public ReflectLambdaConstructorPageEl(Argument _argument,ArgumentListCall _array, ConstructorMetaInfo _metaInfo) {
        argument = _argument;
        array = _array;
        metaInfo = _metaInfo;
        setGlobalArgumentStruct(_metaInfo);
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        if (!keep(_context)) {
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
                arg_ = ExecInvokingOperation.instancePrepareCust(_context, res_, pair_, argument, array, "", -1);
            }
            if (metaInfo.getStandardType() != null) {
                arg_ = ExecInvokingOperation.instancePrepareStd(_context, res_, mid_, array.getArguments());
            }
            if (_context.callsOrException()) {
                setWrapException(_context.calls());
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
