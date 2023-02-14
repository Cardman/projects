package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ToggleFindOptionEvent implements AbsActionListener {
    private final TabEditor tabEditor;

    public ToggleFindOptionEvent(TabEditor _a) {
        this.tabEditor = _a;
    }

    @Override
    public void action() {
        if (tabEditor.getNavModifPanel().isVisible()) {
            new UpdatingEditorAndSelect(tabEditor).run();
        }
    }
}
