package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.*;
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
        real_.remove(2);
        real_.add(0,new Argument(component));
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
            real_.add(1,new Argument(new IntStruct(w_)));
            real_.add(2,new Argument(new IntStruct(h_)));
            GuiContextEl r_ = newCtx(executionInfos);
            Argument argument_ = invokePaint(r_, real_);
            ((PreparedLabelStruct)dest_).setImage(argument_.getStruct());
            return argument_;
        }
        return Argument.createVoid();
    }

    private static Argument invokePaint(GuiContextEl _r, CustList<Argument> _args) {
        Argument arg_ = new Argument();
        ExecTypeFunction pair_ = ((LgNamesGui) _r.getStandards()).getGuiExecutingBlocks().getPairPaintRefreshOne();
        LgNamesGui stds_ = (LgNamesGui) _r.getStandards();
        ArgumentListCall argList_ = new ArgumentListCall();
        argList_.getArguments().addAllElts(_args);
        return RunnableStruct.invoke(arg_, stds_.getGuiAliases().getAliasPaint(), _r,pair_, StackCall.newInstance(InitPhase.NOTHING,_r), argList_, null);
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
