package cards.gui.dialogs;
import java.awt.BorderLayout;

import cards.gui.WindowCards;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.president.RulesPresident;
import code.gui.LabelButton;
import code.gui.Panel;

public final class DialogRulesPresident extends DialogPresident implements DialogRules {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogrulespresident";

    private static final String VALIDATE = "validate";
    private boolean validated;

    public DialogRulesPresident(){
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogRulesPresident(String _titre, WindowCards _fenetre, RulesPresident _rulesPresident) {
        _fenetre.getDialogRulesPresident().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogRulesPresident().setTitle(_titre);
        _fenetre.getDialogRulesPresident().setReglesPresident(_rulesPresident);
        _fenetre.getDialogRulesPresident().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogRulesPresident().getJt().removeAll();
    }

    public static void setPresidentDialog(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCards _window) {
        _window.getDialogRulesPresident().setDialogue(_enabledChangingNbPlayers, _nbPlayers, _window);
    }

    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCards _window) {
        validated = false;
        Panel container_=Panel.newBorder();
        initMessageName(_window);
        initJt(null, _enabledChangingNbPlayers, _nbPlayers, _window);

        container_.add(getJt(),BorderLayout.CENTER);
        LabelButton bouton_=new LabelButton(getMessages().getVal(VALIDATE));
        bouton_.addMouseListener(new ValidateRulesEvent(this));
        container_.add(bouton_,BorderLayout.SOUTH);
        setContentPane(container_);
        pack();
    }

    @Override
    public void validateRulesClose() {
        validateRules();
        validated = true;
        closeWindow();
    }

    public static RulesPresident getRegles(DialogRulesPresident _dialog) {
        _dialog.setVisible(true);
        return _dialog.getReglesPresident();
    }

    public static boolean isValidated(DialogRulesPresident _dialog) {
        return _dialog.validated;
    }

}
