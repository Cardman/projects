package code.expressionlanguage.adv;

public final class SelectClassTask implements Runnable {
    private final TabEditor editor;

    public SelectClassTask(TabEditor _e) {
        this.editor = _e;
    }

    @Override
    public void run() {
        String text_ = editor.getCenter().getText();
        editor.getPreview().setText(text_);
        editor.getFactories().getCompoFactory().invokeNow(new ClearCharacterAttributes(editor.getPreview()));
        FindAction.syntax(editor.getWindowSecEditor().getManageOptions(), editor.getFactories().getCompoFactory(), editor.getPreview());
        editor.usedType(editor.getFinderExpClasses().getText());
        editor.getFindingExpression().setEnabled(editor.getTargetMethodView() != null);
        editor.getApplyExp().setEnabled(false);
        editor.enableExpRepl(false);
        editor.getPartsExp().clear();
        editor.setCurrentText(-1);
        editor.updateNavSelectExp();
    }
}
