package cards.gui.dialogs;
import java.awt.BorderLayout;
import java.awt.Container;

import code.gui.LabelButton;
import cards.gui.MainWindow;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.tarot.RulesTarot;

public final class DialogRulesTarot extends DialogTarot implements DialogRules {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.DialogRulesTarot";

    private static final DialogRulesTarot DIALOG = new DialogRulesTarot();
    private static final String VALIDATE = "validate";
    private boolean validated;

    private DialogRulesTarot(){
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogRulesTarot(String _titre, MainWindow _fenetre, RulesTarot _rulesTarot) {
        //super(_titre, _fenetre,_rulesTarot);
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setTitle(_titre);
        DIALOG.setReglesTarot(_rulesTarot);
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.getJt().removeAll();
    }

    public static void setTarotDialog(boolean _enabledChangingNbPlayers,int _nbPlayers) {
        DIALOG.setDialogue(_enabledChangingNbPlayers, _nbPlayers);
    }
    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers) {
        validated = false;
        Container container_=new Container();
        container_.setLayout(new BorderLayout());
        initMessageName();
        initJt(null,_enabledChangingNbPlayers,_nbPlayers);
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

    public static RulesTarot getRegles() {
        DIALOG.setVisible(true);
        return DIALOG.getReglesTarot();
    }

    public static boolean isValidated() {
        return DIALOG.validated;
    }
}
