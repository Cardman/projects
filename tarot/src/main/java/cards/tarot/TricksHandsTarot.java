package cards.tarot;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.PlayingDog;
import code.util.CustList;
import code.util.EnumList;
import code.util.EqList;


public final class TricksHandsTarot {

    private DealTarot distribution;

    private byte preneur;

    private BidTarot bid;

    private CustList<TrickTarot> tricks;

    private CustList<HandTarot> cardsHandsAtInitialState;

    public void sortHands(DisplayingTarot _displaying,
            byte _nombreJoueurs) {
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_,_displaying.getCouleurs(),
                    _displaying.getDecroissant());
        }
    }
    public void restoreHandsAtSelectedNumberedTrick(DisplayingTarot _displaying,
            byte _nombreJoueurs, byte _numeroPli) {
        for (byte p = CustList.FIRST_INDEX; p<_nombreJoueurs; p++) {
            supprimerCartes(p);
            ajouterCartes(p, cardsHandsAtInitialState.get(p));
        }
        if (bid.getJeuChien() == PlayingDog.WITH) {
            if (_numeroPli >= 0 && !tricks.isEmpty()) {
                ajouterCartes(preneur, derniereMain());
                supprimerCartes(preneur,tricks.first().getCartes());
            }
        }
        byte key_ = 0;
        for (TrickTarot pli_ : tricks) {
            if(!pli_.getVuParToutJoueur()) {
                key_++;
                continue;
            }
            if(key_ > _numeroPli) {
                continue;
            }
            for (CardTarot carte_ : pli_) {
                jouer(pli_.joueurAyantJoue(carte_),carte_);
            }
            key_++;
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_,_displaying.getCouleurs(),
                    _displaying.getDecroissant());
        }
    }

    public void restoreHandsAtSelectedNumberedTrickWithSelectedCard(DisplayingTarot _displaying,
            byte _nombreJoueurs, byte _numeroPli, byte _numeroCarte) {
        for (byte p = CustList.FIRST_INDEX; p<_nombreJoueurs; p++) {
            supprimerCartes(p);
            ajouterCartes(p, cardsHandsAtInitialState.get(p));
        }
        if (bid.getJeuChien() == PlayingDog.WITH) {
            if (_numeroPli >= 0 && !tricks.isEmpty()) {
                ajouterCartes(preneur, derniereMain());
                supprimerCartes(preneur,tricks.first().getCartes());
            }
        }
        byte key_ = 0;
        for (TrickTarot pli_ : tricks) {
            if(!pli_.getVuParToutJoueur()) {
                key_++;
                continue;
            }
            if(key_ > _numeroPli) {
                continue;
            }
            if(key_ == _numeroPli) {
                byte indice_ = 0;
                for (CardTarot carte_ : pli_) {
                    if (indice_ <= _numeroCarte) {
                        jouer(pli_.joueurAyantJoue(carte_),carte_);
                    }
                    indice_++;
                }
                key_++;
                continue;
            }
            for (CardTarot carte_ : pli_) {
                jouer(pli_.joueurAyantJoue(carte_),carte_);
            }
            key_++;
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            trier(joueur_,_displaying.getCouleurs(),
                    _displaying.getDecroissant());
        }
    }
    private void supprimerCartes(byte _joueur) {
        distribution.supprimerCartes(_joueur);
    }

    private void trier(byte _joueur, EnumList<Suit> _couleurs, boolean _decroissant) {
        distribution.trier(_joueur, _couleurs, _decroissant);
    }

    private void jouer(byte _joueurAyantJoue, CardTarot _carte) {
        distribution.jouer(_joueurAyantJoue, _carte);
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

    public void setDistribution(DealTarot _distribution, boolean _copy) {
        if (_copy) {
            distribution = new DealTarot(_distribution);
        } else {
            distribution = _distribution;
        }
    }

    public byte getPreneur() {
        return preneur;
    }

    public void setPreneur(byte _preneur) {
        preneur = _preneur;
    }

    public BidTarot getBid() {
        return bid;
    }

    public void setBid(BidTarot _bid) {
        bid = _bid;
    }

    public CustList<TrickTarot> getTricks() {
        return tricks;
    }

    public void setTricks(CustList<TrickTarot> _tricks, byte _nbPlayers) {
        tricks = _tricks;
        cardsHandsAtInitialState = new CustList<HandTarot>();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
            HandTarot hand_ = new HandTarot();
            hand_.ajouterCartes(distribution.main(joueur_));
            for (TrickTarot pli_ : tricks) {
                if (!pli_.getVuParToutJoueur()) {
                    continue;
                }
                hand_.ajouter(pli_.carteDuJoueur(joueur_));
            }
            cardsHandsAtInitialState.add(hand_);
        }
        HandTarot dog_ = new HandTarot();
        dog_.ajouterCartes(distribution.derniereMain());
        cardsHandsAtInitialState.add(dog_);
        if (bid.getJeuChien() == PlayingDog.WITH && !tricks.isEmpty()) {
            cardsHandsAtInitialState.get(preneur)
                .ajouterCartes(tricks.first().getCartes());
            cardsHandsAtInitialState.get(preneur)
                .supprimerCartes(derniereMain());
        }
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
