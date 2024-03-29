package cards.gui.dialogs;


import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.president.RulesPresident;
import code.gui.EnabledMenu;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogPresident;

public final class DialogRulesPresident extends DialogPresident implements DialogRules {

    private AfterValidateRulesPresident afterValidateRulesPresident;
    private final EnabledMenu associated;

    public DialogRulesPresident(AbstractProgramInfos _frameFactory, EnabledMenu _menu){
        super(_frameFactory);
        associated = _menu;
    }
    public static void initDialogRulesPresident(String _titre, WindowCards _fenetre, RulesPresident _rulesPresident, AfterValidateRulesPresident _after) {
        _fenetre.getDialogRulesPresident().associated.setEnabled(false);
        _fenetre.getDialogRulesPresident().afterValidateRulesPresident = _after;
//        _fenetre.getDialogRulesPresident().getAbsDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre.getCommonFrame());
        _fenetre.getDialogRulesPresident().setTitleDialog(_fenetre,_titre);
//        _fenetre.getDialogRulesPresident().getAbsDialog().setTitle(_titre);
        _fenetre.getDialogRulesPresident().setReglesPresident(_rulesPresident);
//        _fenetre.getDialogRulesPresident().getAbsDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
    }

    public static void setPresidentDialog(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCards _window) {
        _window.getDialogRulesPresident().setDialogue(_enabledChangingNbPlayers, _nbPlayers, _window);
    }

    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window) {
        setValidateButton(ValidateRulesEvent.addButton(initJt(null, _enabledChangingNbPlayers, _nbPlayers, _window),getCompoFactory(),this,translate(MessagesDialogPresident.VALIDATE)));
        getAbsDialog().setVisible(true);
    }

    @Override
    public void closeWindow() {
        super.closeWindow();
        associated.setEnabled(true);
    }
    @Override
    public void saveRules() {
        afterValidateRulesPresident.apply(getReglesPresident());
    }

}
