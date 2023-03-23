package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class SelectClassEvent implements AbsActionListener {
    private final TabEditor editor;

    public SelectClassEvent(TabEditor _e) {
        this.editor = _e;
    }

    @Override
    public void action() {
        editor.getTaskManagerExp().submit(new SelectClassTask(editor));
    }
}
