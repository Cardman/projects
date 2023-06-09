package code.expressionlanguage.adv;

import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.AbsTabbedPane;
import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListener;

public final class CloseTabEditorEvent implements AbsActionListener {
    private final TabEditor tabEditor;

    public CloseTabEditorEvent(TabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        String rel_ = tabEditor.getFullPath().substring(tabEditor.getWindowSecEditor().pathToSrc().length());
        tabEditor.getWindowSecEditor().openedFiles().removeObj(rel_);
        AbsTabbedPane editor_ = tabEditor.getWindowSecEditor().getEditors();
        int index_ = editor_.getSelectedIndex();
        editor_.remove(index_);
        tabEditor.getWindowSecEditor().getTabs().remove(index_);
        GuiBaseUtil.recalculate(tabEditor.getWindowSecEditor().getCommonFrame().getPane());
        tabEditor.getWindowSecEditor().updateDoc();
        tabEditor.getTaskManager().shutdown();
        tabEditor.getTaskManagerExp().shutdown();
        RunnableContextEl rCont_ = tabEditor.getAction();
        if (rCont_ == null) {
            return;
        }
        rCont_.getInterrupt().set(true);
        rCont_.getThread().getThread().stopJoinSleep();
    }
}
