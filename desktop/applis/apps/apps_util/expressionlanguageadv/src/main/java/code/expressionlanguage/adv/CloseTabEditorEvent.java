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
        String rel_ = tabEditor.getFullPath().substring(tabEditor.getWindowEditor().getCurrentFolder().length() + tabEditor.getWindowEditor().getCurrentFolderSrc().length() + 2);
        tabEditor.getWindowEditor().getOpenedFiles().removeObj(rel_);
        AbsTabbedPane editor_ = tabEditor.getWindowEditor().getEditors();
        int index_ = editor_.getSelectedIndex();
        editor_.remove(index_);
        tabEditor.getWindowEditor().getTabs().remove(index_);
        GuiBaseUtil.recalculate(tabEditor.getCommonFrame().getPane());
        tabEditor.getWindowEditor().updateDoc();
        tabEditor.getTaskManager().shutdown();
    }
}
