package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecAnnotableBlock;
import code.expressionlanguage.exec.blocks.ExecAnnotationBlock;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ReflectGetDefaultValuePageEl extends AbstractReflectPageEl {

    private boolean init;
    private CustList<ExecOperationNode> ops;

    @Override
    public boolean checkCondition(ContextEl _context) {
        MethodMetaInfo instance_ = NumParsers.getMethod(getGlobalArgument().getStruct());
        ExecAnnotableBlock annotableBlock_ = instance_.getAnnotableBlock();
        if (!(annotableBlock_ instanceof ExecAnnotationMethodBlock)) {
            setReturnedArgument(new Argument());
            return true;
        }
        if (!init) {
            ExecAnnotationMethodBlock ann_ = (ExecAnnotationMethodBlock) annotableBlock_;
            ops = ann_.getOpValue();
            if (ops.isEmpty()) {
                String clMethod_ = ann_.getImportedReturnType();
                Struct value_ = PrimitiveTypeUtil.defaultValue(clMethod_, _context);
                Argument out_ = new Argument();
                out_.setStruct(value_);
                setReturnedArgument(out_);
                return true;
            }
            init = true;
        }
        ExpressionLanguage el_ = getCurrentEl(0,ops);
        Argument ret_ = ExpressionLanguage.tryToCalculate(_context,el_,0);
        if (_context.callsOrException()) {
            return false;
        }
        clearCurrentEls();
        setReturnedArgument(ret_);
        return true;
    }

    @Override
    public void receive(Argument _argument, ContextEl _context) {
        basicReceive(_argument,_context);
    }

}
