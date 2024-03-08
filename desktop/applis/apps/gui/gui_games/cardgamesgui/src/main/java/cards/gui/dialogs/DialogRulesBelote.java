package cards.gui.dialogs;


import cards.belote.RulesBelote;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ValidateRulesEvent;
import code.gui.EnabledMenu;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogBelote;

public final class DialogRulesBelote extends DialogBelote implements DialogRules {

    private AfterValidateRulesBelote afterValidateRulesBelote;
    private final EnabledMenu associated;

    public DialogRulesBelote(AbstractProgramInfos _frameFactory, EnabledMenu _menu){
        super(_frameFactory);
        associated = _menu;
    }
    public static void initDialogRulesBelote(String _titre, WindowCards _fenetre, RulesBelote _rulesBelote, AfterValidateRulesBelote _after) {
        _fenetre.getDialogRulesBelote().associated.setEnabled(false);
        _fenetre.getDialogRulesBelote().afterValidateRulesBelote = _after;
        _fenetre.getDialogRulesBelote().setTitleDialog(_fenetre,_titre);
//        _fenetre.getDialogRulesBelote().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
//        _fenetre.getDialogRulesBelote().getAbsDialog().setTitle(_titre);
        _fenetre.getDialogRulesBelote().setReglesBelote(_rulesBelote);
//        _fenetre.getDialogRulesBelote().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getDialogRulesBelote().setDialogue(_fenetre);
    }

    @Override
    public void setDialogue(WindowCardsInt _parent) {
        setValidateButton(ValidateRulesEvent.addButton(initJt(_parent,null),getCompoFactory(),this,translate(MessagesDialogBelote.VALIDATE)));
        getAbsDialog().setVisible(true);
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        associated.setEnabled(true);
    }

    @Override
    public void saveRules() {
        afterValidateRulesBelote.apply(getReglesBelote());
    }

}
