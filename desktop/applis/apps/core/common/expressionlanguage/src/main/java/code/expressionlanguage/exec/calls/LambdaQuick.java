package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.structs.Struct;

public final class LambdaQuick extends AbstractBasicReflectPageEl {
    private final Struct lambda;
    public LambdaQuick(Struct _lda) {
        super(true);
        lambda = _lda;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        String last_ = StringExpUtil.getAllTypes(lambda.getClassName(_context)).last();
        setReturnedArgument(ExecClassArgumentMatching.defaultValue(last_, _context));
        return true;
    }

}
