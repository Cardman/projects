package code.expressionlanguage.adv;

import code.gui.AbsTabbedPane;
import code.gui.GuiBaseUtil;
import code.gui.events.AbsActionListener;

public final class CloseReadOnlyTabEditorEvent implements AbsActionListener {
    private final ReadOnlyTabEditor tabEditor;

    public CloseReadOnlyTabEditorEvent(ReadOnlyTabEditor _t) {
        this.tabEditor = _t;
    }

    @Override
    public void action() {
        AbsTabbedPane editor_ = tabEditor.getDebuggerGui().getTabbedPane();
        int index_ = editor_.getSelectedIndex();
        editor_.remove(index_);
        tabEditor.getDebuggerGui().getTabs().remove(index_);
        GuiBaseUtil.recalculateWindow(tabEditor.getDebuggerGui().getCommonFrame());
    }
}
