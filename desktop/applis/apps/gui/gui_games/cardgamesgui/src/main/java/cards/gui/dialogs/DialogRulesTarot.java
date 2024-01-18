package cards.gui.dialogs;


import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.tarot.RulesTarot;
import code.gui.AbsTabbedPane;
import code.gui.initialize.AbstractProgramInfos;

public final class DialogRulesTarot extends DialogTarot implements DialogRules {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogrulestarot";

    private static final String VALIDATE = "validate";
    private AfterValidateRulesTarot afterValidateRulesTarot;

    public DialogRulesTarot(AbstractProgramInfos _frameFactory){
        super(_frameFactory, null);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogRulesTarot(String _titre, WindowCardsInt _fenetre, RulesTarot _rulesTarot, AfterValidateRulesTarot _after) {
        //super(_titre, _fenetre,_rulesTarot);
        _fenetre.getDialogRulesTarot().afterValidateRulesTarot = _after;
        _fenetre.getDialogRulesTarot().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogRulesTarot().getCardDialog().setTitle(_titre);
        _fenetre.getDialogRulesTarot().setReglesTarot(_rulesTarot);
        _fenetre.getDialogRulesTarot().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
    }

    public static void setTarotDialog(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window) {
        _window.getDialogRulesTarot().setDialogue(_enabledChangingNbPlayers, _nbPlayers, _window);
    }
    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window) {
        AbsTabbedPane jt_ = _window.getCompoFactory().newAbsTabbedPane();
        initJt(null,_enabledChangingNbPlayers,_nbPlayers, _window, jt_);
        ValidateRulesEvent.addButton(jt_,getCompoFactory(),this,getMessages().getVal(VALIDATE));
        getCardDialog().setVisible(true);
    }

    @Override
    public void saveRules() {
        afterValidateRulesTarot.apply(getReglesTarot());
    }

}
