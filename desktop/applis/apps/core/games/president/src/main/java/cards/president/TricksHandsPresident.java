package cards.president;
import code.util.CustList;
import code.util.*;
import code.util.core.IndexConstants;


public final class TricksHandsPresident {

    private boolean reversed;

    private DealPresident distribution;

    private CustList<TrickPresident> tricks;

    private TrickPresident progressingTrick;

    private int numberMaxSwitchedCards;

    private Bytes ranks;

    private CustList<HandPresident> switchedCards;

    private CustList<HandPresident> cardsHandsAtInitialState;

    public void sortHands(DisplayingPresident _displaying,
            byte _nombreJoueurs) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().isDecreasing(), reversed);
        }
    }

    public void restoreHandsAtSelectedNumberedTrick(DisplayingPresident _displaying,
            byte _nombreJoueurs) {
        for (byte p = IndexConstants.FIRST_INDEX; p<_nombreJoueurs; p++) {
            supprimerCartes(p);
            ajouterCartes(p, cardsHandsAtInitialState.get(p));
        }
        for (TrickPresident pli_ : tricks) {
            int index_ = 0;
            for (HandPresident carte_ : pli_) {
                supprimerCartes(pli_.getPlayer(index_, _nombreJoueurs),carte_);
                index_++;
            }
        }
        int index_ = 0;
        for (HandPresident carte_ : progressingTrick) {
            supprimerCartes(progressingTrick.getPlayer(index_, _nombreJoueurs),carte_);
            index_++;
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().isDecreasing(),reversed);
        }
    }

    public void restoreHandsAtSelectedNumberedTrick(DisplayingPresident _displaying,
            byte _nombreJoueurs, byte _numeroPli) {
        for (byte p = IndexConstants.FIRST_INDEX; p<_nombreJoueurs; p++) {
            supprimerCartes(p);
            ajouterCartes(p, cardsHandsAtInitialState.get(p));
        }
        if (_numeroPli < 0 && !switchedCards.isEmpty()) {
            for (byte p = IndexConstants.FIRST_INDEX; p<_nombreJoueurs; p++) {
                ajouterCartes(p, switchedCards.get(p));
            }
            for (byte l: getLoosers()) {
                byte w_ = getMatchingWinner(l);
                HandPresident gift_ = switchedCards.get(w_);
                supprimerCartes(l, gift_);
            }
            for (byte w: getWinners()) {
                byte l_ = getMatchingLoser(w);
                HandPresident gift_ = switchedCards.get(l_);
                supprimerCartes(w, gift_);
            }
        }
        byte key_ = 0;
        for (TrickPresident pli_ : tricks) {
            if(key_ > _numeroPli) {
                continue;
            }
            int index_ = 0;
            for (HandPresident carte_ : pli_) {
                supprimerCartes(pli_.getPlayer(index_, _nombreJoueurs),carte_);
                index_++;
            }
            key_++;
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().isDecreasing(),reversed);
        }
    }

    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard(DisplayingPresident _displaying,
            byte _nombreJoueurs, byte _numeroCarte) {
        for (byte p = IndexConstants.FIRST_INDEX; p<_nombreJoueurs; p++) {
            supprimerCartes(p);
            ajouterCartes(p, cardsHandsAtInitialState.get(p));
        }
        for (TrickPresident pli_ : tricks) {
            int index_ = 0;
            for (HandPresident carte_ : pli_) {
                supprimerCartes(pli_.getPlayer(index_, _nombreJoueurs),carte_);
                index_++;
            }
        }
        byte indice_ = 0;
        for (HandPresident carte_ : progressingTrick) {
            if (indice_ <= _numeroCarte) {
                supprimerCartes(progressingTrick.getPlayer(indice_, _nombreJoueurs),carte_);
            }
            indice_++;
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().isDecreasing(),reversed);
        }
    }

    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard(DisplayingPresident _displaying,
            byte _nombreJoueurs, byte _numeroPli, byte _numeroCarte) {
        for (byte p = IndexConstants.FIRST_INDEX; p<_nombreJoueurs; p++) {
            supprimerCartes(p);
            ajouterCartes(p, cardsHandsAtInitialState.get(p));
        }
        byte key_ = 0;
        for (TrickPresident pli_ : tricks) {
            if(key_ > _numeroPli) {
                continue;
            }
            if(key_ == _numeroPli) {
                currentTrick(_nombreJoueurs, _numeroCarte, pli_);
            } else {
                previousTrick(_nombreJoueurs, pli_);
            }
            key_++;
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().isDecreasing(),reversed);
        }
    }

    private void currentTrick(byte _nombreJoueurs, byte _numeroCarte, TrickPresident _pli) {
        byte indice_ = 0;
        for (HandPresident carte_ : _pli) {
            if (indice_ <= _numeroCarte) {
                supprimerCartes(_pli.getPlayer(indice_, _nombreJoueurs),carte_);
            }
            indice_++;
        }
    }

    private void previousTrick(byte _nombreJoueurs, TrickPresident _pli) {
        int index_ = 0;
        for (HandPresident carte_ : _pli) {
            supprimerCartes(_pli.getPlayer(index_, _nombreJoueurs),carte_);
            index_++;
        }
    }

    private void supprimerCartes(byte _joueur) {
        distribution.supprimerCartes(_joueur);
    }

    private void trier(byte _joueur, boolean _decroissant, boolean _reverse) {
        distribution.trier(_joueur, _decroissant, _reverse);
    }

    private void supprimerCartes(byte _preneur, HandPresident _main) {
        distribution.supprimerCartes(_preneur, _main);
    }

    private void ajouterCartes(byte _preneur, HandPresident _derniereMain) {
        distribution.ajouterCartes(_preneur, _derniereMain);
    }

    public DealPresident getDistribution() {
        return distribution;
    }

    public void setDistributionCopy(DealPresident _distribution) {
        distribution = new DealPresident(_distribution);
    }

    public TrickPresident getProgressingTrick() {
        return progressingTrick;
    }

    public CustList<TrickPresident> getTricks() {
        return tricks;
    }

    public void setTricks(CustList<TrickPresident> _tricks, TrickPresident _trick, byte _nbPlayers) {
        tricks = _tricks;
        progressingTrick = _trick;
        cardsHandsAtInitialState = new CustList<HandPresident>();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
            HandPresident hand_ = new HandPresident();
            hand_.ajouterCartes(distribution.hand(joueur_));
            for (TrickPresident pli_ : tricks) {
                for (HandPresident h: pli_.getPlayedCards(joueur_, _nbPlayers)) {
                    hand_.ajouterCartes(h);
                }
            }
            for (HandPresident h: progressingTrick.getPlayedCards(joueur_, _nbPlayers)) {
                hand_.ajouterCartes(h);
            }
            cardsHandsAtInitialState.add(hand_);
        }
    }
    public boolean isReversed() {
        return reversed;
    }
    public void setReversed(boolean _reversed) {
        reversed = _reversed;
    }

    public int getNumberMaxSwitchedCards() {
        return numberMaxSwitchedCards;
    }

    public void setNumberMaxSwitchedCards(int _numberMaxSwitchedCards) {
        numberMaxSwitchedCards = _numberMaxSwitchedCards;
    }

    public Bytes getRanks() {
        return ranks;
    }

    public void setRanks(Bytes _ranks) {
        ranks = _ranks;
    }

    public CustList<HandPresident> getSwitchedCards() {
        return switchedCards;
    }

    public void setSwitchedCards(CustList<HandPresident> _switchedCards) {
        switchedCards = _switchedCards;
    }

    public int getFilledTricksCount() {
        int nb_ = IndexConstants.SIZE_EMPTY;
        for (TrickPresident t: tricks) {
            if (t.getNombreDeCartesParJoueur() > IndexConstants.SIZE_EMPTY) {
                nb_++;
            }
        }
        return nb_;
    }

    public int getFilledTricksIndex(int _index) {
        int ret_ = IndexConstants.FIRST_INDEX;
        int in_ = IndexConstants.FIRST_INDEX;
        for (TrickPresident t: tricks) {
            if (t.getNombreDeCartesParJoueur() == IndexConstants.SIZE_EMPTY) {
                ret_++;
                continue;
            }
            if (in_ == _index) {
                return ret_;
            }
            in_++;
            ret_++;
        }
        return IndexConstants.INDEX_NOT_FOUND_ELT;
    }

    public byte getMatchingWinner(byte _loser) {
        int ind_ = getLoosers().indexOfNb(_loser);
        return getWinners().get(ind_);
    }

    public byte getMatchingLoser(byte _winner) {
        int ind_ = getWinners().indexOfNb(_winner);
        return getLoosers().get(ind_);
    }

    public Bytes getWinners() {
        return GamePresident.getWinners(numberMaxSwitchedCards,ranks);
    }

    public Bytes getLoosers() {
        return GamePresident.getLoosers(numberMaxSwitchedCards,ranks);
    }

    public CustList<HandPresident> getCardsHandsAtInitialState() {
        return cardsHandsAtInitialState;
    }

    public void setCardsHandsAtInitialState(
            CustList<HandPresident> _cardsHandsAtInitialState) {
        cardsHandsAtInitialState = _cardsHandsAtInitialState;
    }

    public void setDistribution(DealPresident _distribution) {
        distribution = _distribution;
    }

    public void setTricks(CustList<TrickPresident> _tricks) {
        tricks = _tricks;
    }

    public void setProgressingTrick(TrickPresident _progressingTrick) {
        progressingTrick = _progressingTrick;
    }
}
