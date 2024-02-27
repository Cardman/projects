package cards.gui.dialogs;


import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.tarot.RulesTarot;
import code.gui.AbsButton;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogTarot;
import code.threads.AbstractAtomicBoolean;

public final class DialogRulesTarot extends DialogTarot implements DialogRules {

    private AfterValidateRulesTarot afterValidateRulesTarot;

    public DialogRulesTarot(AbstractProgramInfos _frameFactory, AbstractAtomicBoolean _modal){
        super(_frameFactory,_modal);
    }
    public static void initDialogRulesTarot(String _titre, WindowCardsInt _fenetre, RulesTarot _rulesTarot, AfterValidateRulesTarot _after) {
        //super(_titre, _fenetre,_rulesTarot);
        _fenetre.getDialogRulesTarot().afterValidateRulesTarot = _after;
//        _fenetre.getDialogRulesTarot().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogRulesTarot().getAbsDialog().setTitle(_titre);
        _fenetre.getDialogRulesTarot().setReglesTarot(_rulesTarot);
        _fenetre.getDialogRulesTarot().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
    }

    public static void setTarotDialog(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window) {
        _window.getDialogRulesTarot().setDialogue(_enabledChangingNbPlayers, _nbPlayers, _window);
    }
    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window) {
        setValidateButton(ValidateRulesEvent.addButton(initJt(null,_enabledChangingNbPlayers,_nbPlayers, _window),getCompoFactory(),this,translate(MessagesDialogTarot.VALIDATE)));
        getAbsDialog().setVisible(true);
    }

    @Override
    protected AbsButton validatingButton() {
        return getValidateButton();
    }

    @Override
    public void saveRules() {
        afterValidateRulesTarot.apply(getReglesTarot());
    }

}
