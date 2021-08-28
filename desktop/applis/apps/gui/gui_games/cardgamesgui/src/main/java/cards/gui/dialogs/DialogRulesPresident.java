package cards.gui.dialogs;


import cards.gui.WindowCards;
import cards.gui.dialogs.events.ValidateRulesEvent;
import cards.president.RulesPresident;
import code.gui.AbsPanel;
import code.gui.AbsPlainButton;
import code.gui.GuiConstants;
import code.gui.initialize.AbstractProgramInfos;

public final class DialogRulesPresident extends DialogPresident implements DialogRules {
    private static final String DIALOG_ACCESS = "cards.gui.dialogs.dialogrulespresident";

    private static final String VALIDATE = "validate";
    private boolean validated;

    public DialogRulesPresident(AbstractProgramInfos _frameFactory){
        super(_frameFactory);
        getCardDialog().setAccessFile(DIALOG_ACCESS);
    }
    public static void initDialogRulesPresident(String _titre, WindowCards _fenetre, RulesPresident _rulesPresident) {
        _fenetre.getDialogRulesPresident().getCardDialog().setDialogIcon(_fenetre.getImageFactory(),_fenetre);
        _fenetre.getDialogRulesPresident().getCardDialog().setTitle(_titre);
        _fenetre.getDialogRulesPresident().setReglesPresident(_rulesPresident);
        _fenetre.getDialogRulesPresident().getCardDialog().setLocationRelativeTo(_fenetre);
        _fenetre.getDialogRulesPresident().getJt().removeAll();
    }

    public static void setPresidentDialog(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCards _window) {
        _window.getDialogRulesPresident().setDialogue(_enabledChangingNbPlayers, _nbPlayers, _window);
    }

    @Override
    public void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCards _window) {
        validated = false;
        AbsPanel container_=_window.getCompoFactory().newBorder();
        initMessageName(_window);
        initJt(null, _enabledChangingNbPlayers, _nbPlayers, _window);

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

    public static RulesPresident getRegles(DialogRulesPresident _dialog) {
        _dialog.getCardDialog().setVisible(true);
        return _dialog.getReglesPresident();
    }

    public static boolean isValidated(DialogRulesPresident _dialog) {
        return _dialog.validated;
    }

}
