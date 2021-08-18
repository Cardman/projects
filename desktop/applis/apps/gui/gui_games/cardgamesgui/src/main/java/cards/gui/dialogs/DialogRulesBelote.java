package cards.gui.dialogs;
import java.awt.BorderLayout;

import cards.belote.RulesBelote;
import cards.gui.WindowCards;
import cards.gui.dialogs.events.ValidateRulesEvent;
import code.gui.LabelButton;
import code.gui.Panel;

public final class DialogRulesBelote extends DialogBelote implements DialogRules {

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogrulesbelote";
    private static final String VALIDATE = "validate";
    private boolean validated;

    public DialogRulesBelote(){
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogRulesBelote(String _titre, WindowCards _fenetre, RulesBelote _rulesBelote) {
        _fenetre.getDialogRulesBelote().setMain(_fenetre);
        _fenetre.getDialogRulesBelote().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogRulesBelote().setTitle(_titre);
        _fenetre.getDialogRulesBelote().setReglesBelote(_rulesBelote);
        _fenetre.getDialogRulesBelote().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogRulesBelote().getJt().removeAll();
        _fenetre.getDialogRulesBelote().setDialogue(_fenetre);
    }

    @Override
    public void setDialogue(WindowCards _parent) {
        validated = false;
        Panel container_=Panel.newBorder();
        initMessageName(_parent);
        String lg_ = _parent.getLanguageKey();
        initJt(_parent,null,lg_);

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

    public static RulesBelote getRegles(DialogRulesBelote _dialog) {
        _dialog.setVisible(true);
        return _dialog.getReglesBelote();
    }

    public static boolean isValidated(DialogRulesBelote _dialog) {
        return _dialog.validated;
    }

}
