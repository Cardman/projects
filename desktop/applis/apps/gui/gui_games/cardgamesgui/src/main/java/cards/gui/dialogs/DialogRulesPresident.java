package cards.gui.dialogs;


import cards.gui.WindowCardsInt;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.president.RulesPresident;
import code.gui.AbsTabbedPane;
import code.gui.initialize.AbstractProgramInfos;

public final class DialogRulesPresident extends DialogPresident implements DialogRules {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogrulespresident";

    private static final String VALIDATE = "validate";
    private AfterValidateRulesPresident afterValidateRulesPresident;

    public DialogRulesPresident(AbstractProgramInfos _frameFactory){
        super(_frameFactory, null);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
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
        AbsTabbedPane jt_ = _window.getCompoFactory().newAbsTabbedPane();
        initJt(null, _enabledChangingNbPlayers, _nbPlayers, _window, jt_);
        ValidateRulesEvent.addButton(jt_,getCompoFactory(),this,getMessages().getVal(VALIDATE));
        getCardDialog().setVisible(true);
    }

    @Override
    public void saveRules() {
        afterValidateRulesPresident.apply(getReglesPresident());
    }

}
