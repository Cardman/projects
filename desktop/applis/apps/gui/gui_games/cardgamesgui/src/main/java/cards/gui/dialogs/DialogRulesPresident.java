package cards.gui.dialogs;


import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.president.RulesPresident;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogPresident;

public final class DialogRulesPresident extends DialogPresident implements DialogRules {

    private AfterValidateRulesPresident afterValidateRulesPresident;

    public DialogRulesPresident(AbstractProgramInfos _frameFactory){
        super(_frameFactory, null);
    }
    public static void initDialogRulesPresident(String _titre, WindowCardsInt _fenetre, RulesPresident _rulesPresident, AfterValidateRulesPresident _after) {
        _fenetre.getDialogRulesPresident().afterValidateRulesPresident = _after;
        _fenetre.getDialogRulesPresident().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogRulesPresident().getCardDialog().setTitle(_titre);
        _fenetre.getDialogRulesPresident().setReglesPresident(_rulesPresident);
        _fenetre.getDialogRulesPresident().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
    }

    public static void setPresidentDialog(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window) {
        _window.getDialogRulesPresident().setDialogue(_enabledChangingNbPlayers, _nbPlayers, _window);
    }

    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window) {
        setValidateButton(ValidateRulesEvent.addButton(initJt(null, _enabledChangingNbPlayers, _nbPlayers, _window),getCompoFactory(),this,translate(MessagesDialogPresident.VALIDATE)));
        getCardDialog().setVisible(true);
    }

    @Override
    public void saveRules() {
        afterValidateRulesPresident.apply(getReglesPresident());
    }

}
