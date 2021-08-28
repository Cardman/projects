package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.SpecSelectionStruct;
import code.util.CustList;

import java.awt.Rectangle;

public final class DefSpecSelectionStruct implements SpecSelectionStruct {

    private final CommonExecutionInfos executionInfos;
    private final Struct component;

    public DefSpecSelectionStruct(ContextEl _ctx, Struct _component) {
        executionInfos = _ctx.getExecutionInfos();
        this.component = _component;
    }

    @Override
    public Argument execute(CustList<Argument> _args, Rectangle _rect) {
        CustList<Argument> real_ = new CustList<Argument>(_args);
        real_.remove(2);
        real_.add(0,new Argument(component));
        Struct dest_ = _args.get(2).getStruct();
        if (dest_ instanceof PreparedLabelStruct) {
            real_.add(1,new Argument(new IntStruct(_rect.width)));
            real_.add(2,new Argument(new IntStruct(_rect.height)));
            GuiContextEl r_ = newCtx(executionInfos);
            Argument argument_ = invokePaint(r_, real_);
            WindowFull window_ = ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindow();
            ((PreparedLabelStruct)dest_).setImage(window_.getImageFactory(),argument_.getStruct());
            return argument_;
        }
        return Argument.createVoid();
    }

    private static Argument invokePaint(GuiContextEl _r, CustList<Argument> _args) {
        Argument arg_ = new Argument();
        ExecTypeFunction pair_ = ((LgNamesGui) _r.getStandards()).getGuiExecutingBlocks().getPairPaintRefreshOne();
        LgNamesGui stds_ = (LgNamesGui) _r.getStandards();
        ArgumentListCall argList_ = new ArgumentListCall(_args);
        return RunnableStruct.invoke(arg_, new ExecFormattedRootBlock(pair_.getType(), stds_.getGuiAliases().getAliasPaint()), _r,pair_, StackCall.newInstance(InitPhase.NOTHING,_r), argList_);
    }
    private static GuiContextEl newCtx(CommonExecutionInfos _executionInfos) {
        GuiContextEl r_ = new GuiContextEl(InitPhase.NOTHING, _executionInfos);
        RunnableStruct.setupThread(r_);
        return r_;
    }
}
