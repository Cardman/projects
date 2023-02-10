package code.expressionlanguage.adv;

public final class UpdatingEditor implements Runnable {
    private final TabEditor tabEditor;

    public UpdatingEditor(TabEditor _t) {
        this.tabEditor = _t;
    }
    @Override
    public void run() {
        FindAction.updateEditor(tabEditor);
        tabEditor.getCommonFrame().pack();
    }
}
