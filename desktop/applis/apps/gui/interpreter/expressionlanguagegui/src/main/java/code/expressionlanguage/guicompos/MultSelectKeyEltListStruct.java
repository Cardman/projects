package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.IndexableListener;
import code.util.CustList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class MultSelectKeyEltListStruct extends KeyAdapter implements IndexableListener {
    private GraphicListStruct grList;

    private int index;
    private CommonExecutionInfos executionInfos;

    public MultSelectKeyEltListStruct(ContextEl _contextEl, GraphicListStruct _graphicList, int _index) {
        executionInfos = _contextEl.getExecutionInfos();
        grList = _graphicList;
        index = _index;
    }

    @Override
    public void keyReleased(KeyEvent _e) {
        if (!grList.isEnabledList()) {
            return;
        }
        if (!_e.isControlDown()) {
            return;
        }
        if (_e.getKeyCode() != KeyEvent.VK_A) {
            return;
        }
        boolean sel_ = !_e.isShiftDown();
        int min_ = 0;
        int max_ = grList.getListComponents().size() - 1;
        if (!sel_) {
            for (int i = min_; i <= max_; i++) {
                grList.getSelectedIndexes().removeObj(i);
            }
        } else {
            for (int i = min_; i <= max_; i++) {
                grList.getSelectedIndexes().add(i);
            }
            grList.getSelectedIndexes().removeDuplicates();
        }
        GuiContextEl ctx_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(grList));
        SimpleSelectEltListStruct.invokePaint(ctx_,args_);
        if (!sel_) {
            grList.setFirstIndex(0);
            grList.setLastIndex(max_);
            grList.clearRange();
            grList.setFirstIndex(-1);
            grList.setLastIndex(-1);
        } else {
            grList.setFirstIndex(0);
            grList.setLastIndex(max_);
            grList.addRange();
        }
        SelectionStructUtil.selectEvent(0,max_,grList,false);
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
