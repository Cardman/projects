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

    private Ints ranks;

    private CustList<HandPresident> switchedCards;

    private CustList<HandPresident> cardsHandsAtInitialState;

    public void sortHands(DisplayingPresident _displaying,
            int  _nombreJoueurs) {
        trier(_displaying, _nombreJoueurs);
    }

    public void restoreHandsAtSelectedNumberedTrick(DisplayingPresident _displaying,
            int  _nombreJoueurs) {
        restaurer(_nombreJoueurs);
        beforePlay();
        trier(_displaying, _nombreJoueurs);
    }

    public void restoreHandsAtSelectedNumberedTrick(DisplayingPresident _displaying,
            int  _nombreJoueurs, int _numeroPli) {
        restaurer(_nombreJoueurs);
        if (_numeroPli == -1) {
            boolean ready_ = GamePresident.ready(switchedCards, GamePresident.getWinners(numberMaxSwitchedCards, ranks));
            if (!ready_) {
                Ints loosers_ = GamePresident.getLoosers(numberMaxSwitchedCards, ranks);
                for (int l: loosers_) {
                    distribution.hand(l).supprimerCartes(switchedCards.get(l));
                }
            }
        }
        int  key_ = 0;
        for (TrickPresident pli_ : union()) {
            if(key_ > _numeroPli) {
                continue;
            }
            previousTrick(_nombreJoueurs, pli_);
            key_++;
        }
        trier(_displaying, _nombreJoueurs);
    }

    private void beforePlay() {
//        if (!switchedCards.isEmpty()) {
//            GamePresident.revert(numberMaxSwitchedCards,ranks,switchedCards,distribution);
//        }
        GamePresident.revert(numberMaxSwitchedCards,ranks,switchedCards,distribution);
    }

    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard(DisplayingPresident _displaying,
            int  _nombreJoueurs, int _numeroCarte) {
        restaurer(_nombreJoueurs);
        for (TrickPresident pli_ : tricks) {
            previousTrick(_nombreJoueurs, pli_);
        }
        currentTrick(_nombreJoueurs, _numeroCarte, progressingTrick);
        trier(_displaying, _nombreJoueurs);
    }

    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard(DisplayingPresident _displaying,
            int  _nombreJoueurs, int _numeroPli, int _numeroCarte) {
        restaurer(_nombreJoueurs);
        int  key_ = 0;
        for (TrickPresident pli_ : union()) {
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
        trier(_displaying, _nombreJoueurs);
    }

    private void restaurer(int  _nombreJoueurs) {
        for (int  p = IndexConstants.FIRST_INDEX; p< _nombreJoueurs; p++) {
            supprimerCartes(p);
            ajouterCartes(p, cardsHandsAtInitialState.get(p));
        }
    }

    private void trier(DisplayingPresident _displaying, int  _nombreJoueurs) {
        for (int  joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().isDecreasing(),reversed);
        }
    }

    private void currentTrick(int _nombreJoueurs, int _numeroCarte, TrickPresident _pli) {
        int  indice_ = 0;
        for (HandPresident carte_ : _pli) {
            if (indice_ <= _numeroCarte) {
                supprimerCartes(_pli.getPlayer(indice_, _nombreJoueurs),carte_);
            }
            indice_++;
        }
    }

    private void previousTrick(int _nombreJoueurs, TrickPresident _pli) {
        int index_ = 0;
        for (HandPresident carte_ : _pli) {
            supprimerCartes(_pli.getPlayer(index_, _nombreJoueurs),carte_);
            index_++;
        }
    }

    private void supprimerCartes(int _joueur) {
        distribution.supprimerCartes(_joueur);
    }

    private void trier(int _joueur, boolean _decroissant, boolean _reverse) {
        distribution.trier(_joueur, _decroissant, _reverse);
    }

    private void supprimerCartes(int _preneur, HandPresident _main) {
        distribution.supprimerCartes(_preneur, _main);
    }

    private void ajouterCartes(int _preneur, HandPresident _derniereMain) {
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

    public void setTricks(CustList<TrickPresident> _tricks, TrickPresident _trick, int _nbPlayers) {
        tricks = _tricks;
        progressingTrick = _trick;
        cardsHandsAtInitialState = new CustList<HandPresident>();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
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

    public Ints getRanks() {
        return ranks;
    }

    public void setRanks(Ints _ranks) {
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
        for (TrickPresident t: union()) {
            if (t.getNombreDeCartesParJoueur() > IndexConstants.SIZE_EMPTY) {
                nb_++;
            }
        }
        return nb_;
    }

    public int getFilledTricksIndex(int _index) {
        int ret_ = IndexConstants.FIRST_INDEX;
        int in_ = IndexConstants.FIRST_INDEX;
        for (TrickPresident t: union()) {
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
    private CustList<TrickPresident> union() {
        CustList<TrickPresident> u_ = new CustList<TrickPresident>(tricks);
        u_.add(progressingTrick);
        return u_;
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
