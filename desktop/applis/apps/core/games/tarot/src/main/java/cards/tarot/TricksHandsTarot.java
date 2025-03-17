package cards.tarot;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdList;
import code.util.core.IndexConstants;


public final class TricksHandsTarot {

    private DealTarot distribution;

    private int preneur;

    private CustList<TrickTarot> tricks;

    private CustList<HandTarot> cardsHandsAtInitialState;

    public void sortHands(DisplayingTarot _displaying,
            int _nombreJoueurs) {
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().getSuits(),
                    _displaying.getDisplaying().isDecreasing());
        }
    }
    public void restoreHandsAtSelectedNumberedTrick(DisplayingTarot _displaying,
                                                    int _nombreJoueurs, int _numeroPli) {
        reinit(_nombreJoueurs, _numeroPli);
        int key_ = 1;
        for (TrickTarot pli_ : tricks.mid(1)) {
//            if(!pli_.getVuParToutJoueur()) {
//                key_++;
//            } else {
//                if (key_ <= _numeroPli) {
//                    previousTrick(pli_,_nombreJoueurs);
//                    key_++;
//                }
//            }
            if (key_ <= _numeroPli) {
                previousTrick(pli_,_nombreJoueurs);
                key_++;
            }
        }
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().getSuits(),
                    _displaying.getDisplaying().isDecreasing());
        }
    }

    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard(DisplayingTarot _displaying,
                                                                    int _nombreJoueurs, int _numeroPli, int _numeroCarte) {
        reinit(_nombreJoueurs, _numeroPli);
        int key_ = 1;
        for (TrickTarot pli_ : tricks.mid(1)) {
//            if(!pli_.getVuParToutJoueur()) {
//                key_++;
//                continue;
//            }
            if (key_ <= _numeroPli) {
                if (key_ == _numeroPli) {
                    currentTrick(_numeroCarte, pli_,_nombreJoueurs);
                } else {
                    previousTrick(pli_,_nombreJoueurs);
                }
                key_++;
            }
        }
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().getSuits(),
                    _displaying.getDisplaying().isDecreasing());
        }
    }

    private void currentTrick(int _numeroCarte, TrickTarot _pli, int _nombreJoueurs) {
        int indice_ = 0;
        for (int p: _pli.joueursAyantJoue(_nombreJoueurs)) {
            if (indice_ <= _numeroCarte) {
                getDistribution().jouer(p, _pli.carteDuJoueur(p,_nombreJoueurs));
            }
            indice_++;
        }
    }

    private void previousTrick(TrickTarot _pli, int _nombreJoueurs) {
        for (int p: _pli.joueursAyantJoue(_nombreJoueurs)) {
            getDistribution().jouer(p, _pli.carteDuJoueur(p,_nombreJoueurs));
        }
    }

    private void reinit(int _nombreJoueurs, int _numeroPli) {
        for (int p = IndexConstants.FIRST_INDEX; p< _nombreJoueurs; p++) {
            supprimerCartes(p);
            ajouterCartes(p, cardsHandsAtInitialState.get(p));
        }
        if (_numeroPli >= 0 && preneur > -1) {
            ajouterCartes(preneur, derniereMain());
            supprimerCartes(preneur,tricks.first().getCartes());
        }
    }

    private void supprimerCartes(int _joueur) {
        distribution.supprimerCartes(_joueur);
    }

    private void trier(int _joueur, IdList<Suit> _couleurs, boolean _decroissant) {
        distribution.trier(_joueur, _couleurs, _decroissant);
    }

    private void supprimerCartes(int _preneur, HandTarot _main) {
        distribution.supprimerCartes(_preneur, _main);
    }

    private void ajouterCartes(int _preneur, HandTarot _derniereMain) {
        distribution.ajouterCartes(_preneur, _derniereMain);
    }

    private HandTarot derniereMain() {
        return distribution.derniereMain();
    }

    public DealTarot getDistribution() {
        return distribution;
    }

    public void setDistributionCopy(DealTarot _distribution) {
        distribution = new DealTarot(_distribution);
    }

    public int getPreneur() {
        return preneur;
    }

    public void setPreneur(int _preneur) {
        preneur = _preneur;
    }

    public CustList<TrickTarot> getTricks() {
        return tricks;
    }

    public void tricks(GameTarot _g) {
        players(_g);
        if (preneur > -1) {
            cardsHandsAtInitialState.get(preneur)
                .ajouterCartes(tricks.first().getCartes());
            cardsHandsAtInitialState.get(preneur)
                .supprimerCartes(derniereMain());
        }
    }

    public void players(GameTarot _g) {
        setDistributionCopy(_g.getDistribution());
        setPreneur(_g.getPreneur());
        CustList<TrickTarot> tr_ = _g.getTricks();
        tricks = new CustList<TrickTarot>(tr_);
        if (!_g.getProgressingTrick().estVide()) {
            tricks.add(_g.getProgressingTrick());
        }
        int nb_ = _g.getNombreDeJoueurs();
        cardsHandsAtInitialState = _g.getProgressingTrick().completeCurrent(nb_, !tr_.isEmpty());
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nb_; joueur_++) {
            HandTarot hand_ = new HandTarot();
            hand_.ajouterCartes(distribution.hand(joueur_));
            for (TrickTarot pli_ : tr_.mid(1)) {
//                if (!pli_.getVuParToutJoueur()) {
//                    continue;
//                }
                hand_.ajouter(pli_.carteDuJoueur(joueur_));
            }
            cardsHandsAtInitialState.get(joueur_).ajouterCartes(hand_);
        }
        HandTarot dog_ = new HandTarot();
        dog_.ajouterCartes(distribution.derniereMain());
        cardsHandsAtInitialState.add(dog_);
    }

    public CustList<HandTarot> getCardsHandsAtInitialState() {
        return cardsHandsAtInitialState;
    }
    public void setCardsHandsAtInitialState(
            CustList<HandTarot> _cardsHandsAtInitialState) {
        cardsHandsAtInitialState = _cardsHandsAtInitialState;
    }
    public void setDistribution(DealTarot _distribution) {
        distribution = _distribution;
    }
    public void setTricks(CustList<TrickTarot> _tricks) {
        tricks = _tricks;
    }
}
