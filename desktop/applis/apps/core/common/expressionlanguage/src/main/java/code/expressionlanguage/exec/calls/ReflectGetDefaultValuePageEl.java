package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ReflectGetDefaultValuePageEl extends AbstractReflectPageEl {

    private boolean init;
    private CustList<ExecOperationNode> ops = new CustList<ExecOperationNode>();
    private final MethodMetaInfo metaInfo;
    private ExecAnnotationMethodBlock annotMethod;

    public ReflectGetDefaultValuePageEl(MethodMetaInfo _metaInfo) {
        super(false);
        setGlobalArgumentStruct(_metaInfo);
        metaInfo = _metaInfo;
    }
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        ExecMemberCallingsBlock annotableBlock_ = metaInfo.getCallee();
        if (!(annotableBlock_ instanceof ExecAnnotationMethodBlock)) {
            setReturnedArgument(NullStruct.NULL_VALUE);
            return true;
        }
        ExecAnnotationMethodBlock ann_ = (ExecAnnotationMethodBlock) annotableBlock_;
        if (!init) {
            ops = ann_.getOpValue();
            if (ops.isEmpty()) {
                String clMethod_ = ann_.getImportedReturnType();
                Struct value_ = ExecClassArgumentMatching.defaultValue(clMethod_, _context);
                setReturnedArgument(value_);
                return true;
            }
            annotMethod = ann_;
            init = true;
        }
        globalOffset(ann_.getDefaultValueOffset());
        Struct ret_ = ExecHelperBlocks.tryToCalculate(_context,0, _stack,ops,0, null,-1);
        if (_stack.stopAt(_context)) {
            return false;
        }
        clearCurrentEls();
        setReturnedArgument(ret_);
        return true;
    }

    @Override
    public void receive(AbstractWrapper _wrap, Struct _argument, ContextEl _context, StackCall _stack) {
        basicReceive(_wrap, _argument,_context, _stack);
    }

    public ExecAnnotationMethodBlock getAnnotMethod() {
        return annotMethod;
    }
    public MethodMetaInfo getMetaInfo() {
        return metaInfo;
    }
}
