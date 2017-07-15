package cards.president;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.Numbers;
import code.util.annot.RwXml;
import cards.consts.MixCardsChoice;
import cards.president.enumerations.EqualtyPlaying;

@RwXml
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
    }

    public boolean isValidRules() {
        if (mixedCards == null) {
            return false;
        }
        if (equalty == null) {
            return false;
        }
        if (nbPlayers < NB_MIN_PLAYERS) {
            return false;
        }
        if (nbPlayers > NB_MAX_PLAYERS) {
            return false;
        }
        if (nbStacks < getNbMinStacks()) {
            return false;
        }
        if (nbStacks > getNbMaxStacks()) {
            return false;
        }
        return true;
    }

    public Numbers<Byte> getSortedPlayersAfterEq(byte _player) {
        Numbers<Byte> pl_ = new Numbers<Byte>();
        for (int p = _player; p < nbPlayers; p++) {
            pl_.add((byte) p);
        }
        for (int p = CustList.FIRST_INDEX; p < _player; p++) {
            pl_.add((byte) p);
        }
        return pl_;
    }

    public Numbers<Byte> getSortedPlayersAfter(byte _player) {
        Numbers<Byte> pl_ = new Numbers<Byte>();
        for (int p = _player + 1; p < nbPlayers; p++) {
            pl_.add((byte) p);
        }
        for (int p = CustList.FIRST_INDEX; p <= _player; p++) {
            pl_.add((byte) p);
        }
        return pl_;
    }
    public static int getNbMaxCards() {
        return NB_MAX_CARDS;
    }

    public static int getNbMinCards() {
        return NB_MIN_CARDS;
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

    public void sauvegarder(String _fichier){
        StreamTextFile.saveObject(_fichier, this);
    }
}
