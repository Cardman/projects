package cards.gui.dialogs;
import java.awt.BorderLayout;

import cards.gui.WindowCards;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.tarot.RulesTarot;
import code.gui.LabelButton;
import code.gui.Panel;

public final class DialogRulesTarot extends DialogTarot implements DialogRules {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogrulestarot";

    private static final String VALIDATE = "validate";
    private boolean validated;

    public DialogRulesTarot(){
        setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogRulesTarot(String _titre, WindowCards _fenetre, RulesTarot _rulesTarot) {
        //super(_titre, _fenetre,_rulesTarot);
        _fenetre.getDialogRulesTarot().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogRulesTarot().setTitle(_titre);
        _fenetre.getDialogRulesTarot().setReglesTarot(_rulesTarot);
        _fenetre.getDialogRulesTarot().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogRulesTarot().getJt().removeAll();
    }

    public static void setTarotDialog(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCards _window) {
        _window.getDialogRulesTarot().setDialogue(_enabledChangingNbPlayers, _nbPlayers, _window);
    }
    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCards _window) {
        validated = false;
        Panel container_=Panel.newBorder();
        initMessageName(_window);
        initJt(null,_enabledChangingNbPlayers,_nbPlayers, _window);
        container_.add(getJt(),BorderLayout.CENTER);
        LabelButton bouton_=new LabelButton(getMessages().getVal(VALIDATE));
        bouton_.addMouseList(new ValidateRulesEvent(this));
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

    public static RulesTarot getRegles(DialogRulesTarot _dialog) {
        _dialog.setVisible(true);
        return _dialog.getReglesTarot();
    }

    public static boolean isValidated(DialogRulesTarot _dialog) {
        return _dialog.validated;
    }

}
