package cards.gui.dialogs;
import java.awt.BorderLayout;

import cards.gui.MainWindow;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.president.RulesPresident;
import code.gui.LabelButton;
import code.gui.Panel;

public final class DialogRulesPresident extends DialogPresident implements DialogRules {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.DialogRulesPresident";

    private static final DialogRulesPresident DIALOG = new DialogRulesPresident();
    private static final String VALIDATE = "validate";
    private boolean validated;

    private DialogRulesPresident(){
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogRulesPresident(String _titre, MainWindow _fenetre, RulesPresident _rulesPresident) {
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setTitle(_titre);
        DIALOG.setReglesPresident(_rulesPresident);
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.getJt().removeAll();
    }

    public static void setPresidentDialog(boolean _enabledChangingNbPlayers,int _nbPlayers) {
        DIALOG.setDialogue(_enabledChangingNbPlayers, _nbPlayers);
    }

    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers) {
        validated = false;
        Panel container_=new Panel();
        container_.setLayout(new BorderLayout());
        initMessageName();
        initJt(null, _enabledChangingNbPlayers, _nbPlayers);

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

    public static RulesPresident getRegles() {
        DIALOG.setVisible(true);
        return DIALOG.getReglesPresident();
    }

    public static boolean isValidated() {
        return DIALOG.validated;
    }
}
