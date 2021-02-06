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

public final class DefSpecSelectionStruct extends WithoutParentIdStruct implements SpecSelectionStruct, AbstractFunctionalInstance {

    private final CommonExecutionInfos executionInfos;
    private final String className;
    private final Struct component;
    private final LambdaStruct lambda;
    private final ExecNamedFunctionBlock named;

    public DefSpecSelectionStruct(ContextEl _ctx,String _className, Struct _component,
                                  LambdaStruct _lambda, ExecNamedFunctionBlock _named) {
        executionInfos = _ctx.getExecutionInfos();
        this.className = _className;
        this.component = _component;
        this.lambda = _lambda;
        this.named = _named;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public Argument execute(CustList<Argument> _args, Rectangle _rect) {
        CustList<Argument> real_ = new CustList<Argument>(_args);
        real_.add(0,new Argument(component));
        Argument sel_ = _args.last();
        Struct dest_ = _args.get(2).getStruct();
        int w_ = 100;
        if (_rect != null) {
            w_ = Math.max(w_, _rect.width);
        }
        int h_ = 12;
        if (_rect != null) {
            h_ = Math.max(h_, _rect.height);
        }
        if (dest_ instanceof PreparedLabelStruct) {
            ImageStruct text_ = new ImageStruct(w_, h_, true);
            real_.set(3,sel_);
            real_.set(4,new Argument(text_));
            GuiContextEl r_ = newCtx(executionInfos);
            Argument argument_ = RunnableFunctionalInstance.callMethod(r_, lambda, real_);
            ((PreparedLabelStruct)dest_).setImage(text_);
            return argument_;
        }
        return Argument.createVoid();
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
