package cards.gui.dialogs;


import cards.belote.RulesBelote;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ValidateRulesEvent;
import code.gui.AbsTabbedPane;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogBelote;

public final class DialogRulesBelote extends DialogBelote implements DialogRules {

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogrulesbelote";
    private AfterValidateRulesBelote afterValidateRulesBelote;

    public DialogRulesBelote(AbstractProgramInfos _frameFactory){
        super(_frameFactory, null);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogRulesBelote(String _titre, WindowCardsInt _fenetre, RulesBelote _rulesBelote, AfterValidateRulesBelote _after) {
        _fenetre.getDialogRulesBelote().afterValidateRulesBelote = _after;
        _fenetre.getDialogRulesBelote().setMain(_fenetre);
        _fenetre.getDialogRulesBelote().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogRulesBelote().getCardDialog().setTitle(_titre);
        _fenetre.getDialogRulesBelote().setReglesBelote(_rulesBelote);
        _fenetre.getDialogRulesBelote().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getDialogRulesBelote().setDialogue(_fenetre);
    }

    @Override
    public void setDialogue(WindowCardsInt _parent) {
        AbsTabbedPane jt_ = _parent.getCompoFactory().newAbsTabbedPane();
        initJt(_parent,null, jt_);
        ValidateRulesEvent.addButton(jt_,getCompoFactory(),this,translate(_parent.getLanguageKey(), MessagesDialogBelote.VALIDATE));
        getCardDialog().setVisible(true);
    }

    @Override
    public void saveRules() {
        afterValidateRulesBelote.apply(getReglesBelote());
    }

}
