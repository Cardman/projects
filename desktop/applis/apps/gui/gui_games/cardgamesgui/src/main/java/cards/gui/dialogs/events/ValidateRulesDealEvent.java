package cards.gui.dialogs.events;

import cards.gui.WindowCardsInt;
import cards.gui.dialogs.DialogCards;
import cards.gui.dialogs.SetterSelectedCardList;
import code.gui.AbsButton;
import code.gui.AbsCustComponent;
import code.gui.AbsPanel;
import code.gui.GuiConstants;
import code.gui.events.AbsActionListener;
import code.scripts.messages.cards.MessagesEditorCards;

public class ValidateRulesDealEvent implements AbsActionListener {

    private final SetterSelectedCardList dialog;
    private final WindowCardsInt window;

    public ValidateRulesDealEvent(SetterSelectedCardList _dialog, WindowCardsInt _parent) {
        dialog = _dialog;
        window = _parent;
    }
    public static void addButton(AbsCustComponent _jt, WindowCardsInt _parent, SetterSelectedCardList _dialog, DialogCards _d) {
        AbsPanel container_=_parent.getCompoFactory().newBorder();
        container_.add(_jt, GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=_parent.getCompoFactory().newLineBox();
        AbsButton bouton_=_parent.getCompoFactory().newPlainButton(_dialog.getEditorCards().translate(MessagesEditorCards.NEXT));
        bouton_.addActionListener(new ValidateRulesDealEvent(_dialog, _parent));
        panneau_.add(bouton_);
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        _dialog.getEditorCards().setValidateRules(bouton_);
        _d.getCardDialog().setContentPane(container_);
        _d.getCardDialog().pack();
    }

    @Override
    public void action() {
        dialog.validateRulesDeal(window);
    }
}
