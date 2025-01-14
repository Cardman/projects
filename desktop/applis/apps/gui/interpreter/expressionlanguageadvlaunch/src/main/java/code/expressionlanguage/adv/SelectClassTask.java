package code.expressionlanguage.adv;

import code.util.core.DefaultUniformingString;

public final class SelectClassTask implements Runnable {
    private final TabEditor editor;

    public SelectClassTask(TabEditor _e) {
        this.editor = _e;
    }

    @Override
    public void run() {
        String text_ = editor.centerText();
        editor.previewText(new DefaultUniformingString().apply(text_));
        editor.previewSelect(0,0);
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
