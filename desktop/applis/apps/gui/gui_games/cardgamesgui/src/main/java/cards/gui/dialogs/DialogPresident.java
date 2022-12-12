package cards.gui.dialogs;

import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.WindowCardsInt;
import cards.gui.comboboxes.ComboBoxEnumCards;
import cards.gui.dialogs.events.ClosingEditorCards;
import cards.gui.dialogs.events.ListenerEqualityPlaying;
import cards.gui.dialogs.events.ListenerPlayers;
import cards.gui.dialogs.events.ListenerStacks;
import cards.president.RulesPresident;
import cards.president.enumerations.EqualtyPlaying;
import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.util.*;
import code.util.core.StringUtil;
import code.util.ints.Listable;

public abstract class DialogPresident extends DialogCards implements DialogVaryingPlayerNumber {

    private static final String EMPTY = "";
    private static final String DEALING = "dealing";
    private static final String MIX_CARDS = "mixCards";
    private static final String NUMBER_DEALS = "numberDeals";

    private static final String CST_EQUALITY = "equality";
    private static final String STOP_ALL_PLAYED_CARDS = "stopAllPlayedCards";
    private static final String CAN_PASS = "canPass";
    private static final String POSSIBLE_REVERSING = "possibleReversing";
    private static final String RULES = "rules";

    private static final String LOOSE_FINISH_BEST_CARDS = "looseFinishBestCards";
    private static final String SWITCH_CARDS = "switchCards";
    private static final String LOOSER_STARTS_FIRST = "looserStartsFirst";
    private static final String END_DEAL = "endDeal";

    private static final String NUMBER_PLAYERS = "numberPlayers";
    private static final String NUMBER_STACKS = "numberStacks";
    private static final String REPARTITION = "repartition";

    private RulesPresident reglesPresident=new RulesPresident();
    private AbsSpinner nbGames;
    private StringMap<String> messages = new StringMap<String>();
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

    protected DialogPresident(AbstractProgramInfos _frameFactory, ClosingEditorCards _ch) {
        super(_frameFactory, _ch);
    }

    public abstract void setDialogue(boolean _enabledChangingNbPlayers,int _nbPlayers, WindowCardsInt _window);

    protected void initMessageName(WindowCardsInt _parent) {
        setMessages(WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, _parent.getLanguageKey(), getCardDialog().getAccessFile()));
    }

    protected void initJt(AbsSpinner _nbGames, boolean _enabledChangingNbPlayers, int _nbPlayers, WindowCardsInt _window, AbsTabbedPane _jt) {
        String lg_ = _window.getLanguageKey();
        setNbGames(_nbGames);
        AbsPanel dealing_=_window.getCompoFactory().newGrid(0,2);
        //Sous - panneau Battre les cartes
        dealing_.add(getCompoFactory().newPlainLabel(getMessages().getVal(MIX_CARDS)));
        listeChoix=new ComboBox<MixCardsChoice>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1, _window.getCompoFactory()));
        Listable<MixCardsChoice> mix_;
        mix_ = new IdList<MixCardsChoice>(MixCardsChoice.values());
        IdMap<MixCardsChoice, String> trMix_;
        trMix_ = new IdMap<MixCardsChoice, String>();
        for (MixCardsChoice choix_: mix_) {
            trMix_.put(choix_, Games.toString(choix_,lg_));
        }
        listeChoix.refresh(mix_, trMix_);
//        for (MixCardsChoice choix_:MixCardsChoice.values()) {
//            listeChoix.addItem(choix_);
//        }
        listeChoix.setSelectedItem(getReglesPresident().getCommon().getMixedCards());
        dealing_.add(listeChoix.self());
        if (getNbGames() != null) {
            dealing_.add(getCompoFactory().newPlainLabel(getMessages().getVal(NUMBER_DEALS)));
            dealing_.add(getNbGames());
        }

        //Panneau Distribution
        _jt.add(getMessages().getVal(DEALING),dealing_);

        AbsPanel rules_=_window.getCompoFactory().newGrid(0,2);
        rules_.add(getCompoFactory().newPlainLabel(getMessages().getVal(CST_EQUALITY)));
        equality = new ComboBoxEnumCards<EqualtyPlaying>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(new IntTreeMap<String>().values()), 0, _window.getCompoFactory()));
        EqualtyPlaying curThree_ = getReglesPresident().getEqualty();
        int index_ = 0;
        int i_ = -1;
        for (EqualtyPlaying choix_:EqualtyPlaying.values()) {
            if (choix_ == curThree_) {
                i_ = index_;
            }
            equality.addItem(choix_, Games.toString(choix_,lg_));
            index_++;
        }
        if (i_ > -1) {
            equality.selectItem(i_);
        }
        equality.setListener(new ListenerEqualityPlaying(this));
        rules_.add(equality.self());
        rules_.add(getCompoFactory().newPlainLabel(""));
        stopAllPlayedCards = getCompoFactory().newPlainLabel("");
        if (getReglesPresident().getEqualty() == EqualtyPlaying.SKIP_DIFF_NEXT_STOP) {
            stopAllPlayedCards.setText(getMessages().getVal(STOP_ALL_PLAYED_CARDS));
        } else {
            stopAllPlayedCards.setText(EMPTY);
        }
        rules_.add(stopAllPlayedCards);
        rules_.add(getCompoFactory().newPlainLabel(""));
        canPass = getCompoFactory().newCustCheckBox(getMessages().getVal(CAN_PASS));
        canPass.setSelected(!getReglesPresident().isHasToPlay());
        rules_.add(canPass);
        rules_.add(getCompoFactory().newPlainLabel(""));
        int nbSuits_ = Suit.couleursOrdinaires().size();
        nbSuits_ *= getReglesPresident().getNbStacks();
        String message_ = StringUtil.simpleNumberFormat(getMessages().getVal(POSSIBLE_REVERSING), nbSuits_);
        possibleReversing = getCompoFactory().newCustCheckBox(message_);
        possibleReversing.setSelected(getReglesPresident().isPossibleReversing());
        rules_.add(possibleReversing);
        _jt.add(getMessages().getVal(RULES),rules_);

        AbsPanel endDeal_ = _window.getCompoFactory().newPageBox();
        looseFinishBestCards = getCompoFactory().newCustCheckBox(getMessages().getVal(LOOSE_FINISH_BEST_CARDS));
        looseFinishBestCards.setSelected(getReglesPresident().isLoosingIfFinishByBestCards());
        endDeal_.add(looseFinishBestCards);
        switchCards = getCompoFactory().newCustCheckBox(getMessages().getVal(SWITCH_CARDS));
        switchCards.setSelected(getReglesPresident().isSwitchCards());
        endDeal_.add(switchCards);
        looserStartsFirst = getCompoFactory().newCustCheckBox(getMessages().getVal(LOOSER_STARTS_FIRST));
        looserStartsFirst.setSelected(getReglesPresident().isLooserStartsFirst());
        endDeal_.add(looserStartsFirst);
        _jt.add(getMessages().getVal(END_DEAL),endDeal_);

        AbsPanel players_ = _window.getCompoFactory().newGrid(2,0);
        players_.add(getCompoFactory().newPlainLabel(getMessages().getVal(NUMBER_PLAYERS)));
        players_.add(getCompoFactory().newPlainLabel(getMessages().getVal(NUMBER_STACKS)));

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
        _jt.add(getMessages().getVal(REPARTITION),players_);
    }

    public void displayMessagePlaying() {
        if (equality.getCurrentElement() == EqualtyPlaying.SKIP_DIFF_NEXT_STOP) {
            stopAllPlayedCards.setText(getMessages().getVal(STOP_ALL_PLAYED_CARDS));
        } else {
            stopAllPlayedCards.setText(EMPTY);
        }
    }

    @Override
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
        String message_ = StringUtil.simpleNumberFormat(getMessages().getVal(POSSIBLE_REVERSING), nbSuits_);
        possibleReversing.setText(message_);
    }

    public void validateStacks() {
        int nbSuits_ = Suit.couleursOrdinaires().size();
        nbSuits_ *= nbStacks.getValue();
        String message_ = StringUtil.simpleNumberFormat(getMessages().getVal(POSSIBLE_REVERSING), nbSuits_);
        possibleReversing.setText(message_);
    }

    protected void validateRules() {
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

    protected StringMap<String> getMessages() {
        return messages;
    }

    protected void setMessages(StringMap<String> _messages) {
        messages = _messages;
    }

    protected RulesPresident getReglesPresident() {
        return reglesPresident;
    }

    protected void setReglesPresident(RulesPresident _reglesPresident) {
        reglesPresident = _reglesPresident;
    }

    protected AbsSpinner getNbGames() {
        return nbGames;
    }

    protected void setNbGames(AbsSpinner _nbGames) {
        nbGames = _nbGames;
    }
}
