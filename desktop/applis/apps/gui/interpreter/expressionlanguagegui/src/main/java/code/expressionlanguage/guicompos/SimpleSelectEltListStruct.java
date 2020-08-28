package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.IndexableListener;
import code.util.CustList;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class SimpleSelectEltListStruct extends MouseAdapter implements IndexableListener {
    private ContextEl original;
    private GraphicListStruct grList;

    private int index;

    public SimpleSelectEltListStruct(ContextEl _contextEl, GraphicListStruct _graphicList, int _index) {
        original = _contextEl;
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
        ExecRootBlock classBody_ = _r.getPaint();
        ExecNamedFunctionBlock fct_ = _r.getPaintRefresh();
        LgNamesGui stds_ = (LgNamesGui) _r.getStandards();
        RunnableStruct.invoke(arg_, stds_.getAliasPaint(), classBody_, fct_, _args, _r);
    }
    private GuiContextEl newCtx() {
        GuiContextEl r_ = new GuiContextEl(original);
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
