package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class SelectClassEvent implements AbsActionListener {
    private final TabEditor editor;

    public SelectClassEvent(TabEditor _e) {
        this.editor = _e;
    }

    @Override
    public void action() {
        String text_ = editor.getCenter().getText();
        editor.getPreview().setEditable(true);
        editor.getPreview().setText(text_);
        editor.getPreview().setEditable(false);
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
