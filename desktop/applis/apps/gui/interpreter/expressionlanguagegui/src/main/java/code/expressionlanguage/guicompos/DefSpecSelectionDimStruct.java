package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.LambdaStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithoutParentIdStruct;
import code.expressionlanguage.utilcompo.RunnableFunctionalInstance;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.SpecSelectionStruct;
import code.util.CustList;

import java.awt.*;

public final class DefSpecSelectionDimStruct extends WithoutParentIdStruct implements SpecSelectionStruct, AbstractFunctionalInstance {

    private final CommonExecutionInfos executionInfos;
    private final String className;
    private final LambdaStruct lambda;
    private final ExecNamedFunctionBlock named;

    public DefSpecSelectionDimStruct(ContextEl _ctx, String _className,
                                     LambdaStruct _lambda, ExecNamedFunctionBlock _named) {
        executionInfos = _ctx.getExecutionInfos();
        this.className = _className;
        this.lambda = _lambda;
        this.named = _named;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public Argument execute(CustList<Argument> _args, Rectangle _rect) {
        GuiContextEl r_ = newCtx(executionInfos);
        return RunnableFunctionalInstance.callMethod(r_, lambda, _args);
    }

    private static GuiContextEl newCtx(CommonExecutionInfos _executionInfos) {
        GuiContextEl r_ = new GuiContextEl(InitPhase.NOTHING, _executionInfos);
        RunnableStruct.setupThread(r_);
        return r_;
    }
    @Override
    public LambdaStruct getFunctional() {
        return lambda;
    }

    @Override
    public ExecNamedFunctionBlock getNamed() {
        return named;
    }
}
