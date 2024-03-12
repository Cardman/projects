package cards.tarot;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdList;
import code.util.core.IndexConstants;


public final class TricksHandsTarot {

    private DealTarot distribution;

    private byte preneur;

    private CustList<TrickTarot> tricks;

    private CustList<HandTarot> cardsHandsAtInitialState;

    public void sortHands(DisplayingTarot _displaying,
            byte _nombreJoueurs) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().getSuits(),
                    _displaying.getDisplaying().isDecreasing());
        }
    }
    public void restoreHandsAtSelectedNumberedTrick(DisplayingTarot _displaying,
            byte _nombreJoueurs, byte _numeroPli) {
        reinit(_nombreJoueurs, _numeroPli);
        byte key_ = 1;
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
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().getSuits(),
                    _displaying.getDisplaying().isDecreasing());
        }
    }

    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard(DisplayingTarot _displaying,
            byte _nombreJoueurs, byte _numeroPli, byte _numeroCarte) {
        reinit(_nombreJoueurs, _numeroPli);
        byte key_ = 1;
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
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_, _displaying.getDisplaying().getSuits(),
                    _displaying.getDisplaying().isDecreasing());
        }
    }

    private void currentTrick(byte _numeroCarte, TrickTarot _pli, byte _nombreJoueurs) {
        byte indice_ = 0;
        for (byte p: _pli.joueursAyantJoue(_nombreJoueurs)) {
            if (indice_ <= _numeroCarte) {
                getDistribution().jouer(p, _pli.carteDuJoueur(p,_nombreJoueurs));
            }
            indice_++;
        }
    }

    private void previousTrick(TrickTarot _pli, byte _nombreJoueurs) {
        for (byte p: _pli.joueursAyantJoue(_nombreJoueurs)) {
            getDistribution().jouer(p, _pli.carteDuJoueur(p,_nombreJoueurs));
        }
    }

    private void reinit(byte _nombreJoueurs, byte _numeroPli) {
        for (byte p = IndexConstants.FIRST_INDEX; p< _nombreJoueurs; p++) {
            supprimerCartes(p);
            ajouterCartes(p, cardsHandsAtInitialState.get(p));
        }
        if (_numeroPli >= 0 && preneur > -1) {
            ajouterCartes(preneur, derniereMain());
            supprimerCartes(preneur,tricks.first().getCartes());
        }
    }

    private void supprimerCartes(byte _joueur) {
        distribution.supprimerCartes(_joueur);
    }

    private void trier(byte _joueur, IdList<Suit> _couleurs, boolean _decroissant) {
        distribution.trier(_joueur, _couleurs, _decroissant);
    }

    private void supprimerCartes(byte _preneur, HandTarot _main) {
        distribution.supprimerCartes(_preneur, _main);
    }

    private void ajouterCartes(byte _preneur, HandTarot _derniereMain) {
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

    public byte getPreneur() {
        return preneur;
    }

    public void setPreneur(byte _preneur) {
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
        byte nb_ = _g.getNombreDeJoueurs();
        cardsHandsAtInitialState = _g.getProgressingTrick().completeCurrent(nb_, !tr_.isEmpty());
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nb_; joueur_++) {
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
