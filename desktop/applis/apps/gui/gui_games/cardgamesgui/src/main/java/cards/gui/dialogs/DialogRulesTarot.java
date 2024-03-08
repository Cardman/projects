package cards.gui.dialogs;


import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.tarot.RulesTarot;
import code.gui.AbsButton;
import code.gui.EnabledMenu;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogTarot;

public final class DialogRulesTarot extends DialogTarot implements DialogRules {

    private AfterValidateRulesTarot afterValidateRulesTarot;
    private final EnabledMenu associated;

    public DialogRulesTarot(AbstractProgramInfos _frameFactory, EnabledMenu _menu){
        super(_frameFactory);
        associated = _menu;
    }
    public static void initDialogRulesTarot(String _titre, WindowCards _fenetre, RulesTarot _rulesTarot, AfterValidateRulesTarot _after) {
        //super(_titre, _fenetre,_rulesTarot);
        _fenetre.getDialogRulesTarot().associated.setEnabled(false);
        _fenetre.getDialogRulesTarot().afterValidateRulesTarot = _after;
//        _fenetre.getDialogRulesTarot().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogRulesTarot().setTitleDialog(_fenetre,_titre);
//        _fenetre.getDialogRulesTarot().getAbsDialog().setTitle(_titre);
        _fenetre.getDialogRulesTarot().setReglesTarot(_rulesTarot);
//        _fenetre.getDialogRulesTarot().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
    }

    public static void setTarotDialog(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCards _window) {
        _window.getDialogRulesTarot().setDialogue(_enabledChangingNbPlayers, _nbPlayers, _window);
    }
    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window) {
        setValidateButton(ValidateRulesEvent.addButton(initJt(null,_enabledChangingNbPlayers,_nbPlayers, _window),getCompoFactory(),this,translate(MessagesDialogTarot.VALIDATE)));
        getDialogTarotContent().setValidateButton(validatingButton());
        getAbsDialog().setVisible(true);
    }

    @Override
    protected AbsButton validatingButton() {
        return getValidateButton();
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        associated.setEnabled(true);
    }
    @Override
    public void saveRules() {
        afterValidateRulesTarot.apply(getReglesTarot());
    }

}
