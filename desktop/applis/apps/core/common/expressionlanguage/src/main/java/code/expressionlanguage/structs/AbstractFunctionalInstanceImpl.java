package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;

public abstract class AbstractFunctionalInstanceImpl extends WithoutParentIdStruct implements AbstractFunctionalInstance {

    private final String className;

    private final LambdaStruct functional;

    private final ExecNamedFunctionBlock named;
    protected AbstractFunctionalInstanceImpl(String _className, LambdaStruct _functional, ExecNamedFunctionBlock _named) {
        className = _className;
        functional = _functional;
        named = _named;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return getClassName();
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(className);
    }

    public String getClassName() {
        return className;
    }

    @Override
    public ExecNamedFunctionBlock getNamed() {
        return named;
    }

    @Override
    public LambdaStruct getFunctional() {
        return functional;
    }
}
