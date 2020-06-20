package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.exec.blocks.WithEl;

public abstract class AbstractMethodPageEl extends AbstractPageEl implements ForwardPageEl {

    public AbstractMethodPageEl(ContextEl _context,String _ret, Argument _gl, String _glClass) {
        setGlobalArgument(_gl);
        setGlobalClass(_glClass);
        String ret_ = formatVarType(_ret, _context);
        setReturnedArgument(new Argument(PrimitiveTypeUtil.defaultValue(ret_,_context)));
    }

    @Override
    public boolean checkCondition(ContextEl _context) {
        return true;
    }

    @Override
    public void tryProcessEl(ContextEl _context) {
        //method walk through
        ReadWrite rw_ = getReadWrite();
        ExecBlock en_ = rw_.getBlock();
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context);
            return;
        }
        setNullReadWrite();
    }

    @Override
    public void forwardTo(AbstractPageEl _page, ContextEl _context) {
        Argument a_ = getReturnedArgument();
        _page.receive(a_, _context);
    }

}
