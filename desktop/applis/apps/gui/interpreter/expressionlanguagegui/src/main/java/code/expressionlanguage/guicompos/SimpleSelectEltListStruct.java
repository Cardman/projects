package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.IndexableListener;
import code.util.CustList;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class SimpleSelectEltListStruct extends MouseAdapter implements IndexableListener {
    private final GraphicListStruct grList;

    private int index;
    private final CommonExecutionInfos executionInfos;

    public SimpleSelectEltListStruct(ContextEl _contextEl, GraphicListStruct _graphicList, int _index) {
        executionInfos = _contextEl.getExecutionInfos();
        grList = _graphicList;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        if (!grList.isEnabledList()) {
            return;
        }
        grList.setFirstIndex(index);
        grList.setLastIndex(index);
        boolean sel_ = SwingUtilities.isLeftMouseButton(_e);
        grList.getSelectedIndexes().clear();
        if (sel_) {
            grList.getSelectedIndexes().add(index);
        }
        GuiContextEl ctx_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(grList));
        invokePaint(ctx_, args_);
        SelectionStructUtil.selectEvent(index,index,grList,false);
    }

    public static void invokePaint(GuiContextEl _r, CustList<Argument> _args) {
        Argument arg_ = new Argument();
        ExecTypeFunction pair_ = ((LgNamesGui) _r.getStandards()).getGuiExecutingBlocks().getPairPaintRefresh();
        LgNamesGui stds_ = (LgNamesGui) _r.getStandards();
        RunnableStruct.invoke(arg_, stds_.getGuiAliases().getAliasPaint(), _args, _r,pair_, StackCall.newInstance(InitPhase.NOTHING,_r));
    }
    private GuiContextEl newCtx() {
        GuiContextEl r_ = new GuiContextEl(InitPhase.NOTHING, executionInfos);
        RunnableStruct.setupThread(r_);
        return r_;
    }
    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int _index) {
        index = _index;
    }
}
