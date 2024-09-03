package cards.gui.dialogs.events;

import cards.gui.WindowCardsInt;
import cards.gui.dialogs.DialogHelpCards;
import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.AbsButton;
import code.gui.AbsCustComponent;
import code.gui.AbsPanel;
import code.gui.events.AbsActionListener;
import code.gui.files.MessagesGuiFct;
import code.scripts.messages.cards.MessagesEditorCards;

public class ValidateRulesDealEvent implements AbsActionListener {

    private final SetterSelectedCardList dialog;
    private final WindowCardsInt window;

    public ValidateRulesDealEvent(SetterSelectedCardList _dialog, WindowCardsInt _parent) {
        dialog = _dialog;
        window = _parent;
    }
    public static void addButton(AbsCustComponent _jt, WindowCardsInt _parent, SetterSelectedCardList _dialog, DialogHelpCards _d) {
        AbsPanel container_=_parent.getCompoFactory().newBorder();
        container_.add(_jt, MessagesGuiFct.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=_parent.getCompoFactory().newLineBox();
        AbsButton bouton_=_parent.getCompoFactory().newPlainButton(_dialog.getEditorCards().translate(MessagesEditorCards.NEXT));
        bouton_.addActionListener(new ValidateRulesDealEvent(_dialog, _parent));
        panneau_.add(bouton_);
        container_.add(panneau_, MessagesGuiFct.BORDER_LAYOUT_SOUTH);
        _dialog.getEditorCards().setValidateRules(bouton_);
        _d.getAbsDialog().setContentPane(container_);
        _d.getAbsDialog().pack();
    }

    @Override
    public void action() {
        dialog.validateRulesDeal(window);
    }
}
