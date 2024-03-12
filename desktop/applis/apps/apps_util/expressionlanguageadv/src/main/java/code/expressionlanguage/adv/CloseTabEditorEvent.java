package code.expressionlanguage.adv;

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
        GuiBaseUtil.recalculateWindow(tabEditor.getWindowSecEditor().getCommonFrame());
        tabEditor.getWindowSecEditor().updateDoc();
        tabEditor.shutdownTasks();
        tabEditor.getTaskManagerExp().shutdown();
        tabEditor.tryInterrupt();
    }
}
