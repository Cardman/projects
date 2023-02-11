package code.expressionlanguage.adv;

public final class UpdatingEditorAndSelect implements Runnable {
    private final TabEditor tabEditor;

    public UpdatingEditorAndSelect(TabEditor _t) {
        this.tabEditor = _t;
    }
    @Override
    public void run() {
        FindAction.updateEditor(tabEditor);
        tabEditor.updateNavSelect();
        tabEditor.getCommonFrame().pack();
    }
}
