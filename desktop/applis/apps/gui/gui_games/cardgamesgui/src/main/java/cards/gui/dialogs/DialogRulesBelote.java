package cards.gui.dialogs;


import cards.belote.RulesBelote;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ValidateRulesEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogBelote;

public final class DialogRulesBelote extends DialogBelote implements DialogRules {

    private AfterValidateRulesBelote afterValidateRulesBelote;

    public DialogRulesBelote(AbstractProgramInfos _frameFactory){
        super(_frameFactory, null);
    }
    public static void initDialogRulesBelote(String _titre, WindowCardsInt _fenetre, RulesBelote _rulesBelote, AfterValidateRulesBelote _after) {
        _fenetre.getDialogRulesBelote().afterValidateRulesBelote = _after;
        _fenetre.getDialogRulesBelote();
        _fenetre.getDialogRulesBelote().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogRulesBelote().getCardDialog().setTitle(_titre);
        _fenetre.getDialogRulesBelote().setReglesBelote(_rulesBelote);
        _fenetre.getDialogRulesBelote().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getDialogRulesBelote().setDialogue(_fenetre);
    }

    @Override
    public void setDialogue(WindowCardsInt _parent) {
        setValidateButton(ValidateRulesEvent.addButton(initJt(_parent,null),getCompoFactory(),this,translate(MessagesDialogBelote.VALIDATE)));
        getCardDialog().setVisible(true);
    }

    @Override
    public void saveRules() {
        afterValidateRulesBelote.apply(getReglesBelote());
    }

}
