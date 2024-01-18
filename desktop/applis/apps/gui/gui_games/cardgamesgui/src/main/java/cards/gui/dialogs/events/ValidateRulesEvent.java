package cards.gui.dialogs.events;

import cards.gui.dialogs.DialogRules;
import code.gui.AbsButton;
import code.gui.AbsCustComponent;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbsCompoFactory;

public class ValidateRulesEvent implements AbsActionListener {

    private final DialogRules dialog;

    public ValidateRulesEvent(DialogRules _dialog) {
        dialog = _dialog;
    }

    public static AbsButton addButton(AbsCustComponent _jt, AbsCompoFactory _compo, DialogRules _d, String _validate) {
        AbsPanel container_ = _compo.newBorder();
        container_.add(_jt, GuiConstants.BORDER_LAYOUT_CENTER);
        AbsButton bouton_=_compo.newPlainButton(_validate);
        bouton_.addActionListener(new ValidateRulesEvent(_d));
        container_.add(bouton_,GuiConstants.BORDER_LAYOUT_SOUTH);
        _d.getCardDialog().setContentPane(container_);
        _d.getCardDialog().pack();
        return bouton_;
    }
    @Override
    public void action() {
        dialog.validateRules();
        dialog.setValidated(true);
        dialog.closeWindow();
        dialog.saveRules();
    }
}
