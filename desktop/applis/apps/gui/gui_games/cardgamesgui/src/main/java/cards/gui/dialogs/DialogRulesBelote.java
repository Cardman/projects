package cards.gui.dialogs;


import cards.belote.RulesBelote;
import cards.gui.WindowCards;
import cards.gui.dialogs.events.ValidateRulesEvent;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.GuiConstants;
import code.gui.initialize.AbstractProgramInfos;

public final class DialogRulesBelote extends DialogBelote implements DialogRules {

    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogrulesbelote";
    private static final String VALIDATE = "validate";
    private boolean validated;

    public DialogRulesBelote(AbstractProgramInfos _frameFactory){
        super(_frameFactory);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogRulesBelote(String _titre, WindowCards _fenetre, RulesBelote _rulesBelote) {
        _fenetre.getDialogRulesBelote().setMain(_fenetre);
        _fenetre.getDialogRulesBelote().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogRulesBelote().getCardDialog().setTitle(_titre);
        _fenetre.getDialogRulesBelote().setReglesBelote(_rulesBelote);
        _fenetre.getDialogRulesBelote().getCardDialog().setLocationRelativeTo(_fenetre.getCommonFrame());
        _fenetre.getDialogRulesBelote().getJt().removeAll();
        _fenetre.getDialogRulesBelote().setDialogue(_fenetre);
    }

    @Override
    public void setDialogue(WindowCards _parent) {
        validated = false;
        AbsPanel container_=_parent.getCompoFactory().newBorder();
        initMessageName(_parent);
        String lg_ = _parent.getLanguageKey();
        initJt(_parent,null,lg_);

        container_.add(getJt(), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPlainButton bouton_=getCompoFactory().newPlainButton(getMessages().getVal(VALIDATE));
        bouton_.addActionListener(new ValidateRulesEvent(this));
        container_.add(bouton_,GuiConstants.BORDER_LAYOUT_SOUTH);
        getCardDialog().setContentPane(container_);
        getCardDialog().pack();
    }

    @Override
    public void validateRulesClose() {
        validateRules();
        validated = true;
        closeWindow();
    }

    public static RulesBelote getRegles(DialogRulesBelote _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.getReglesBelote();
    }

    public static boolean isValidated(DialogRulesBelote _dialog) {
        return _dialog.validated;
    }

}
