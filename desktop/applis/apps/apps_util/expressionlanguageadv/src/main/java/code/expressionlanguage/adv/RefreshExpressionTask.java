package code.expressionlanguage.adv;

public final class RefreshExpressionTask implements Runnable {
    private final TabEditor editor;

    public RefreshExpressionTask(TabEditor _e) {
        this.editor = _e;
    }

    @Override
    public void run() {
        editor.refresh();
    }
}
