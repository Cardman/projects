package cards.gui.dialogs;

import cards.consts.MixCardsChoice;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.ComboBoxEnumCards;
import cards.president.RulesPresident;
import cards.president.enumerations.EqualtyPlaying;
import code.gui.AbsCustCheckBox;
import code.gui.AbsSpinner;
import code.gui.AbsTabbedPane;
import code.gui.ComboBox;
import code.gui.initialize.AbstractProgramInfos;

public abstract class DialogPresident extends DialogHelpCards {

    private final DialogPresidentContent dialogPresidentContent;

    protected DialogPresident(AbstractProgramInfos _frameFactory) {
        super(_frameFactory);
        dialogPresidentContent = new DialogPresidentContent(_frameFactory);
    }

    public abstract void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window);

    protected AbsTabbedPane initJt(AbsSpinner _nbGames, boolean _enabledChangingNbPlayers, int _nbPlayers, WindowCardsInt _window) {
        return dialogPresidentContent.initJt(_nbGames, _enabledChangingNbPlayers, _nbPlayers, _window);
    }

    public ComboBox<MixCardsChoice> getListeChoix() {
        return dialogPresidentContent.getListeChoix();
    }

    public ComboBoxEnumCards<EqualtyPlaying> getEquality() {
        return dialogPresidentContent.getEquality();
    }

    public AbsCustCheckBox getCanPass() {
        return dialogPresidentContent.getCanPass();
    }

    public AbsCustCheckBox getPossibleReversing() {
        return dialogPresidentContent.getPossibleReversing();
    }

    public AbsCustCheckBox getLooseFinishBestCards() {
        return dialogPresidentContent.getLooseFinishBestCards();
    }

    public AbsCustCheckBox getLooserStartsFirst() {
        return dialogPresidentContent.getLooserStartsFirst();
    }

    public AbsCustCheckBox getSwitchCards() {
        return dialogPresidentContent.getSwitchCards();
    }

    public AbsSpinner getNbJoueurs() {
        return dialogPresidentContent.getNbJoueurs();
    }

    public AbsSpinner getNbStacks() {
        return dialogPresidentContent.getNbStacks();
    }

    public static EqualtyPlaying[] allEqualtyPlaying() {
        return new EqualtyPlaying[]{EqualtyPlaying.FORBIDDEN,EqualtyPlaying.SKIP_ALWAYS_NEXT,EqualtyPlaying.SKIP_DIFF_NEXT_STOP,EqualtyPlaying.NO_SKIP,EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL};
    }

    public String translate(String _k) {
        return dialogPresidentContent.translate(_k);
    }

    public void validateRules() {
        dialogPresidentContent.validateRules();
    }

    public RulesPresident getReglesPresident() {
        return dialogPresidentContent.getReglesPresident();
    }

    protected void setReglesPresident(RulesPresident _reglesPresident) {
        dialogPresidentContent.setReglesPresident(_reglesPresident);
    }

    public AbsSpinner getNbGames() {
        return dialogPresidentContent.getNbGames();
    }

}
