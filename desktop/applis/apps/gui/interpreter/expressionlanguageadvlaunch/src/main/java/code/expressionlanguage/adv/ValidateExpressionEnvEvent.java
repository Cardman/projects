package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class ValidateExpressionEnvEvent implements AbsActionListener {
    private final OutputDialogExpresion dialogExpresion;
    public ValidateExpressionEnvEvent(OutputDialogExpresion _d) {
        dialogExpresion = _d;
    }

    @Override
    public void action() {
        dialogExpresion.getOwner().getDialogFolderExpression().setVisible(false);
        dialogExpresion.apply();
    }
}
