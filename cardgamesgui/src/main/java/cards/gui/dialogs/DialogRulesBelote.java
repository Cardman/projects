package cards.gui.dialogs;
import java.awt.BorderLayout;
import java.awt.Container;

import code.gui.LabelButton;
import cards.belote.RulesBelote;
import cards.gui.MainWindow;
import cards.gui.dialogs.events.ValidateRulesEvent;

public final class DialogRulesBelote extends DialogBelote implements DialogRules {

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.DialogRulesBelote";
    private static final DialogRulesBelote DIALOG = new DialogRulesBelote();
    private static final String VALIDATE = "validate";
    private boolean validated;

    private DialogRulesBelote(){
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogRulesBelote(String _titre, MainWindow _fenetre, RulesBelote _rulesBelote) {
        DIALOG.setDialogIcon(_fenetre);
        DIALOG.setTitle(_titre);
        DIALOG.setReglesBelote(_rulesBelote);
        DIALOG.setLocationRelativeTo(_fenetre);
        DIALOG.getJt().removeAll();
        DIALOG.setDialogue();
    }

    @Override
    public void setDialogue() {
        validated = false;
        Container container_=new Container();
        container_.setLayout(new BorderLayout());
        initMessageName();
        initJt(null);

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

    public static RulesBelote getRegles() {
        DIALOG.setVisible(true);
        return DIALOG.getReglesBelote();
    }

    public static boolean isValidated() {
        return DIALOG.validated;
    }
}
