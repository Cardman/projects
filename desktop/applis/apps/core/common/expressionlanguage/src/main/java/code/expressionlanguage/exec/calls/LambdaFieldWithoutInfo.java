package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.FieldLambdaParentRetriever;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.LambdaFieldStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public final class LambdaFieldWithoutInfo extends AbstractBasicReflectPageEl {
    private final LambdaFieldStruct lambdaFieldStruct;
    private final ArgumentListCall methodLambdaParentRetriever;
    private boolean entered;
    private boolean exited;
    private Struct instance;
    private int ancestor;
    public LambdaFieldWithoutInfo(LambdaFieldStruct _lms, ArgumentListCall _l) {
        super(true);
        lambdaFieldStruct = _lms;
        methodLambdaParentRetriever = _l;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        entered = true;
        if (!lambdaFieldStruct.isInstanceField()) {
            ancestor = 1;
        }
        instance = FieldLambdaParentRetriever.retrInstance(methodLambdaParentRetriever, lambdaFieldStruct);
        if (_stack.getStopper().isStopAtRef(_context, _stack)) {
            return false;
        }
        exited = true;
        if (lambdaFieldStruct.isInstanceField()) {
            String ownerType_ = StringUtil.nullToEmpty(lambdaFieldStruct.getOwnerType());
            boolean res_ = ExecInherits.safeObject(ownerType_, instance.getClassName(_context), _context) == ErrorType.NOTHING;
            setReturnedArgument(BooleanStruct.of(res_));
            return true;
        }
        setReturnedArgument(instance.getParent());
        return true;
    }

    public boolean isExited() {
        return exited;
    }

    public int getAncestor(){
        return ancestor;
    }

    public boolean isEntered() {
        return entered;
    }

    public Struct getInstance() {
        return instance;
    }


}
