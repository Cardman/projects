package cards.president;
import cards.consts.MixCardsChoice;
import cards.president.enumerations.EqualtyPlaying;
import code.util.*;
import code.util.core.IndexConstants;


public final class RulesPresident {

    private static final int NB_MIN_CARDS = 4;
    private static final int NB_MAX_CARDS = 32;
    private static final int NB_MIN_PLAYERS = 3;
    private static final int NB_MAX_PLAYERS = 32;
    private MixCardsChoice mixedCards=MixCardsChoice.EACH_LAUNCHING;
    private int nbPlayers = 4;
    private int nbStacks = 1;
    private EqualtyPlaying equalty = EqualtyPlaying.SKIP_DIFF_NEXT_STOP;
    private int nbDeals;
    private boolean possibleReversing;
    private boolean hasToPlay;
    private boolean loosingIfFinishByBestCards = true;
    private boolean switchCards = true;
    private boolean looserStartsFirst = true;
    private String general="";
    private String specific="";

    public RulesPresident() {
    }

    public RulesPresident(int _nbPlayers) {
        nbPlayers = _nbPlayers;
        nbStacks = getNbMinStacks();
    }

    public RulesPresident(RulesPresident _rules) {
        mixedCards = _rules.mixedCards;
        nbPlayers = _rules.nbPlayers;
        nbStacks = _rules.nbStacks;
        equalty = _rules.equalty;
        nbDeals = _rules.nbDeals;
        possibleReversing = _rules.possibleReversing;
        hasToPlay = _rules.hasToPlay;
        loosingIfFinishByBestCards = _rules.loosingIfFinishByBestCards;
        switchCards = _rules.switchCards;
        looserStartsFirst = _rules.looserStartsFirst;
        setSpecific(_rules.getSpecific());
        setGeneral(_rules.getGeneral());
    }

    public boolean isValidRules() {
        if (nbPlayers < NB_MIN_PLAYERS) {
            return false;
        }
        if (nbPlayers > NB_MAX_PLAYERS) {
            return false;
        }
        if (nbStacks < getNbMinStacks()) {
            return false;
        }
        return nbStacks <= getNbMaxStacks();
    }

    public Bytes getSortedPlayersAfterEq(byte _player) {
        Bytes pl_ = new Bytes();
        for (int p = _player; p < nbPlayers; p++) {
            pl_.add((byte) p);
        }
        for (int p = IndexConstants.FIRST_INDEX; p < _player; p++) {
            pl_.add((byte) p);
        }
        return pl_;
    }

    public Bytes getSortedPlayersAfter(byte _player) {
        Bytes pl_ = new Bytes();
        for (int p = _player + 1; p < nbPlayers; p++) {
            pl_.add((byte) p);
        }
        for (int p = IndexConstants.FIRST_INDEX; p <= _player; p++) {
            pl_.add((byte) p);
        }
        return pl_;
    }

    public static int getNbMaxPlayers() {
        return NB_MAX_PLAYERS;
    }

    public static int getNbMinPlayers() {
        return NB_MIN_PLAYERS;
    }

    public MixCardsChoice getMixedCards() {
        return mixedCards;
    }

    public void setMixedCards(MixCardsChoice _mixedCards) {
        mixedCards = _mixedCards;
    }

    public int getNbPlayers() {
        return nbPlayers;
    }

    public void setNbPlayers(int _nbPlayers) {
        nbPlayers = _nbPlayers;
    }

    public int getNbStacks() {
        return nbStacks;
    }

    public void setNbStacks(int _nbStacks) {
        nbStacks = _nbStacks;
    }

    public EqualtyPlaying getEqualty() {
        return equalty;
    }

    public void setEqualty(EqualtyPlaying _equalty) {
        equalty = _equalty;
    }

    public int getNbDeals() {
        return nbDeals;
    }

    public void setNbDeals(int _nbDeals) {
        nbDeals = _nbDeals;
    }

    public boolean isPossibleReversing() {
        return possibleReversing;
    }

    public void setPossibleReversing(boolean _possibleReversing) {
        possibleReversing = _possibleReversing;
    }

    public boolean isHasToPlay() {
        return hasToPlay;
    }

    public void setHasToPlay(boolean _hasToPlay) {
        hasToPlay = _hasToPlay;
    }

    public boolean isLoosingIfFinishByBestCards() {
        return loosingIfFinishByBestCards;
    }

    public void setLoosingIfFinishByBestCards(boolean _loosingIfFinishByBestCards) {
        loosingIfFinishByBestCards = _loosingIfFinishByBestCards;
    }

    public boolean isSwitchCards() {
        return switchCards;
    }

    public void setSwitchCards(boolean _switchCards) {
        switchCards = _switchCards;
    }

    public boolean isLooserStartsFirst() {
        return looserStartsFirst;
    }

    public void setLooserStartsFirst(boolean _looserStartsFirst) {
        looserStartsFirst = _looserStartsFirst;
    }

    public int getNbMaxCardsPerPlayer() {
        int nbCards_ = nbStacks * HandPresident.pileBase().total();
        int rem_ = nbCards_ % nbPlayers;
        boolean noRem_ = rem_ == 0;
        int nbCardsPerPlayer_ = nbCards_ / nbPlayers;
        if (noRem_) {
            return nbCardsPerPlayer_;
        }
        return nbCardsPerPlayer_ + 1;
    }

    public int getNbMaxStacks() {
        HandPresident base_ = HandPresident.pileBase();
        return nbPlayers * NB_MAX_CARDS / base_.total();
    }

    public int getNbMinStacks() {
        HandPresident base_ = HandPresident.pileBase();
        int ratio_ = nbPlayers * NB_MIN_CARDS / base_.total();
        int rem_ = nbPlayers * NB_MIN_CARDS % base_.total();
        if (rem_ == 0) {
            return Math.max(ratio_, 1);
        }
        return Math.max(ratio_ + 1, 1);
    }

    public static int getNbMaxStacks(int _nbPlayers) {
        HandPresident base_ = HandPresident.pileBase();
        return _nbPlayers * NB_MAX_CARDS / base_.total();
    }

    public static int getNbMinStacks(int _nbPlayers) {
        HandPresident base_ = HandPresident.pileBase();
        int ratio_ = _nbPlayers * NB_MIN_CARDS / base_.total();
        int rem_ = _nbPlayers * NB_MIN_CARDS % base_.total();
        if (rem_ == 0) {
            return Math.max(ratio_, 1);
        }
        return Math.max(ratio_ + 1, 1);
    }

    public static int getNbMaxStacksPlayers() {
        HandPresident base_ = HandPresident.pileBase();
        return NB_MAX_PLAYERS * NB_MAX_CARDS / base_.total();
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String specific) {
        this.specific = specific;
    }
}
