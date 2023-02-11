package code.expressionlanguage.adv;

public final class UpdatingEditorQuick implements Runnable {
    private final TabEditor tabEditor;

    public UpdatingEditorQuick(TabEditor _t) {
        this.tabEditor = _t;
    }
    @Override
    public void run() {
        FindAction.updateEditorStyle(tabEditor);
        tabEditor.getCommonFrame().pack();
    }
}
