package code.expressionlanguage;

import code.expressionlanguage.methods.Block;

public final class MethodPageEl extends AbstractPageEl {

    @Override
    public boolean checkCondition(ContextEl _context) {
        return true;
    }

    @Override
    public void setReturnedArgument() {
        Argument void_ = Argument.createVoid();
        setReturnedArgument(void_);
    }

    @Override
    public void postBlock(ContextEl _context) {
        Block root_ = getBlockRoot();
        Argument global_ = getGlobalArgument();
        Argument arg_ = PrimitiveTypeUtil.defaultValue(root_, global_, _context);
        setReturnedArgument(arg_);
        setNullReadWrite();
    }

    @Override
    public void postReturn(ContextEl _context) {
        setNullReadWrite();
    }

    @Override
    public void endRoot(ContextEl _context) {
        Block root_ = getBlockRoot();
        setReturnedArgument(PrimitiveTypeUtil.defaultValue(root_, getGlobalArgument(), _context));
        setNullReadWrite();
    }

}
