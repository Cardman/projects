package cards.gui.dialogs;

import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.MessagesCardGames;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.ComboBoxEnumCards;
import cards.gui.dialogs.events.ListenerEqualityPlaying;
import cards.gui.dialogs.events.ListenerPlayers;
import cards.gui.dialogs.events.ListenerStacks;
import cards.president.GamePresident;
import cards.president.RulesPresident;
import cards.president.enumerations.EqualtyPlaying;
import code.gui.*;
import code.gui.initialize.AbsCompoFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.scripts.messages.cards.MessagesDialogPresident;
import code.sml.util.TranslationsLg;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class DialogPresidentContent implements DialogVaryingPlayerNumber,DialogVaryingStack{

    private static final String EMPTY = "";

    private RulesPresident reglesPresident=new RulesPresident();
    private AbsSpinner nbGames;
    private ComboBox<MixCardsChoice> listeChoix;

    private ComboBoxEnumCards<EqualtyPlaying> equality;
    private AbsPlainLabel stopAllPlayedCards;
    private AbsCustCheckBox canPass;
    private AbsCustCheckBox possibleReversing;

    private AbsCustCheckBox looseFinishBestCards;
    private AbsCustCheckBox switchCards;
    private AbsCustCheckBox looserStartsFirst;

    private AbsSpinner nbJoueurs;
    private AbsSpinner nbStacks;
    private final AbstractProgramInfos frames;
    public DialogPresidentContent(AbstractProgramInfos _fr) {
        frames = _fr;
    }

    public AbsTabbedPane initJt(AbsSpinner _nbGames, boolean _enabledChangingNbPlayers, int _nbPlayers, WindowCardsInt _window) {
        AbsTabbedPane jt_ = _window.getCompoFactory().newAbsTabbedPane();
        TranslationsLg lg_ = getFrames().currentLg();
        setNbGames(_nbGames);
        AbsPanel dealing_=_window.getCompoFactory().newGrid(0,2);
        //Sous - panneau Battre les cartes
        dealing_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogPresident.MIX_CARDS)));
        listeChoix=DialogHelpCards.build(_window,getReglesPresident().getCommon().getMixedCards());
        dealing_.add(listeChoix.self());
        if (getNbGames() != null) {
            dealing_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogPresident.NUMBER_DEALS)));
            dealing_.add(getNbGames());
        }

        //Panneau Distribution
        jt_.add(translate(MessagesDialogPresident.DEALING),dealing_);

        AbsPanel rules_=_window.getCompoFactory().newGrid(0,2);
        rules_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogPresident.CST_EQUALITY)));
        equality = new ComboBoxEnumCards<EqualtyPlaying>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), 0, _window.getCompoFactory()));
        EqualtyPlaying curThree_ = getReglesPresident().getEqualty();
        int index_ = 0;
        for (EqualtyPlaying choix_:DialogPresident.allEqualtyPlaying()) {
            equality.addItem(choix_, Games.toString(choix_,lg_));
            if (choix_ == curThree_) {
                equality.selectItem(index_);
            }
            index_++;
        }
        equality.getCombo().repaint();
        equality.setListener(new ListenerEqualityPlaying(this));
        rules_.add(equality.self());
        rules_.add(getCompoFactory().newPlainLabel(""));
        stopAllPlayedCards = getCompoFactory().newPlainLabel("");
        if (GamePresident.equalling(getReglesPresident().getEqualty())) {
            stopAllPlayedCards.setText(translate(MessagesDialogPresident.STOP_ALL_PLAYED_CARDS));
        } else {
            stopAllPlayedCards.setText(EMPTY);
        }
        rules_.add(stopAllPlayedCards);
        rules_.add(getCompoFactory().newPlainLabel(""));
        canPass = getCompoFactory().newCustCheckBox(translate(MessagesDialogPresident.CAN_PASS));
        canPass.setSelected(!getReglesPresident().isHasToPlay());
        rules_.add(canPass);
        rules_.add(getCompoFactory().newPlainLabel(""));
        int nbSuits_ = Suit.couleursOrdinaires().size();
        nbSuits_ *= getReglesPresident().getNbStacks();
        String message_ = StringUtil.simpleNumberFormat(translate(MessagesDialogPresident.POSSIBLE_REVERSING), nbSuits_);
        possibleReversing = getCompoFactory().newCustCheckBox(message_);
        possibleReversing.setSelected(getReglesPresident().isPossibleReversing());
        rules_.add(possibleReversing);
        jt_.add(translate(MessagesDialogPresident.RULES),rules_);

        AbsPanel endDeal_ = _window.getCompoFactory().newPageBox();
        looseFinishBestCards = getCompoFactory().newCustCheckBox(translate(MessagesDialogPresident.LOOSE_FINISH_BEST_CARDS));
        looseFinishBestCards.setSelected(getReglesPresident().isLoosingIfFinishByBestCards());
        endDeal_.add(looseFinishBestCards);
        switchCards = getCompoFactory().newCustCheckBox(translate(MessagesDialogPresident.SWITCH_CARDS));
        switchCards.setSelected(getReglesPresident().isSwitchCards());
        endDeal_.add(switchCards);
        looserStartsFirst = getCompoFactory().newCustCheckBox(translate(MessagesDialogPresident.LOOSER_STARTS_FIRST));
        looserStartsFirst.setSelected(getReglesPresident().isLooserStartsFirst());
        endDeal_.add(looserStartsFirst);
        jt_.add(translate(MessagesDialogPresident.END_DEAL),endDeal_);

        AbsPanel players_ = _window.getCompoFactory().newGrid(2,0);
        players_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogPresident.NUMBER_PLAYERS)));
        players_.add(getCompoFactory().newPlainLabel(translate(MessagesDialogPresident.NUMBER_STACKS)));

        int minJoueurs_ = RulesPresident.getNbMinPlayers();
        int maxJoueurs_ = RulesPresident.getNbMaxPlayers();
        int value_;
        if (_nbPlayers != 0) {
            value_ = _nbPlayers;
        } else {
            value_ = getReglesPresident().getNbPlayers();
        }
        nbJoueurs=getCompoFactory().newSpinner(value_,minJoueurs_,maxJoueurs_,1);
        if (_enabledChangingNbPlayers) {
            nbJoueurs.addChangeListener(new ListenerPlayers(this, _window));
        } else {
            nbJoueurs.setEnabled(false);
        }
        players_.add(nbJoueurs);
        int minStacks_ = getReglesPresident().getNbMinStacks();
        int maxStacks_ = getReglesPresident().getNbMaxStacks();
        nbStacks=getCompoFactory().newSpinner(getReglesPresident().getNbStacks(),minStacks_,maxStacks_,1);
        nbStacks.addChangeListener(new ListenerStacks(this));
        players_.add(nbStacks);
        jt_.add(translate(MessagesDialogPresident.REPARTITION),players_);
        return jt_;
    }

    public void displayMessagePlaying() {
        if (GamePresident.equalling(equality.getCurrentElement())) {
            stopAllPlayedCards.setText(translate(MessagesDialogPresident.STOP_ALL_PLAYED_CARDS));
        } else {
            stopAllPlayedCards.setText(EMPTY);
        }
    }
    public void validateNbPlayers(WindowCardsInt _window) {
        int minStacks_ = RulesPresident.getNbMinStacks(nbJoueurs.getValue());
        int maxStacks_ = RulesPresident.getNbMaxStacks(nbJoueurs.getValue());
        int v_ = nbStacks.getValue();
        if (v_ < minStacks_) {
            v_ = minStacks_;
        }
        if (v_ > maxStacks_) {
            v_ = maxStacks_;
        }
        nbStacks.setRangeValue(v_,minStacks_,maxStacks_);
        int nbSuits_ = Suit.couleursOrdinaires().size();
        nbSuits_ *= v_;
        String message_ = StringUtil.simpleNumberFormat(translate(MessagesDialogPresident.POSSIBLE_REVERSING), nbSuits_);
        possibleReversing.setText(message_);
    }

    public void validateStacks() {
        int nbSuits_ = Suit.couleursOrdinaires().size();
        nbSuits_ *= nbStacks.getValue();
        String message_ = StringUtil.simpleNumberFormat(translate(MessagesDialogPresident.POSSIBLE_REVERSING), nbSuits_);
        possibleReversing.setText(message_);
    }

    public String translate(String _k) {
        return translates().getVal(_k);
    }
    public StringMap<String> translates() {
        return MessagesCardGames.getDialogPresidentTr(MessagesCardGames.getAppliTr(getFrames().currentLg())).getMapping();
    }
    public void validateRules() {
//        getReglesPresident().setMixedCards((MixCardsChoice)listeChoix.getSelectedItem());
        getReglesPresident().getCommon().setMixedCards(listeChoix.getCurrent());
        getReglesPresident().setEqualty(equality.getCurrentElement());
        getReglesPresident().setHasToPlay(!canPass.isSelected());
        getReglesPresident().setPossibleReversing(possibleReversing.isSelected());
        getReglesPresident().setLoosingIfFinishByBestCards(looseFinishBestCards.isSelected());
        getReglesPresident().setSwitchCards(switchCards.isSelected());
        getReglesPresident().setLooserStartsFirst(looserStartsFirst.isSelected());
        getReglesPresident().setNbPlayers(nbJoueurs.getValue());
        getReglesPresident().setNbStacks(nbStacks.getValue());
    }

    public RulesPresident getReglesPresident() {
        return reglesPresident;
    }

    public void setReglesPresident(RulesPresident _reglesPresident) {
        reglesPresident = _reglesPresident;
    }

    public AbsSpinner getNbGames() {
        return nbGames;
    }

    public void setNbGames(AbsSpinner _nbGames) {
        nbGames = _nbGames;
    }

    public AbsCompoFactory getCompoFactory() {
        return getFrames().getCompoFactory();
    }
    public AbstractProgramInfos getFrames() {
        return frames;
    }

    public ComboBox<MixCardsChoice> getListeChoix() {
        return listeChoix;
    }

    public ComboBoxEnumCards<EqualtyPlaying> getEquality() {
        return equality;
    }

    public AbsCustCheckBox getCanPass() {
        return canPass;
    }

    public AbsCustCheckBox getPossibleReversing() {
        return possibleReversing;
    }

    public AbsCustCheckBox getLooseFinishBestCards() {
        return looseFinishBestCards;
    }

    public AbsCustCheckBox getLooserStartsFirst() {
        return looserStartsFirst;
    }

    public AbsCustCheckBox getSwitchCards() {
        return switchCards;
    }

    public AbsSpinner getNbJoueurs() {
        return nbJoueurs;
    }

    public AbsSpinner getNbStacks() {
        return nbStacks;
    }
}
