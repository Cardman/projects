package cards.belote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import code.util.CustList;
import code.util.core.IndexConstants;


public final class TricksHandsBelote {

    private RulesBelote rules;

    private DealBelote distribution;

    private byte preneur;

    private BidBeloteSuit bid;

    private CustList<TrickBelote> tricks;

    private CustList<HandBelote> cardsHandsAtInitialState;

    public void restituerMains(DisplayingBelote _displaying,byte _nombreJoueurs,byte _numeroPli) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_nombreJoueurs; joueur_++) {
            getDistribution().supprimerCartes(joueur_);
            getDistribution().hand(joueur_).ajouterCartes(cardsHandsAtInitialState.get(joueur_));
        }
        if(_numeroPli>=0) {
            getDistribution().completerDonne(preneur, rules);
        }
        byte key_ = 1;
        for(TrickBelote pli_:tricks) {
            if(key_ > _numeroPli) {
                continue;
            }
            for(CardBelote carte_:pli_) {
                getDistribution().jouer(pli_.joueurAyantJoue(carte_),carte_);
            }
            key_++;
        }
        sortHands(_displaying,_nombreJoueurs);
    }
    public void restituerMains(DisplayingBelote _displaying,byte _nombreJoueurs,byte _numeroPli,byte _numeroCarte) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_nombreJoueurs; joueur_++) {
            getDistribution().supprimerCartes(joueur_);
            getDistribution().hand(joueur_).ajouterCartes(cardsHandsAtInitialState.get(joueur_));
        }
        if(_numeroPli>=0) {
            getDistribution().completerDonne(preneur, rules);
        }
        byte key_ = 1;
        for(TrickBelote pli_:tricks) {
            if(key_ > _numeroPli) {
                continue;
            }
            if(key_ == _numeroPli) {
                byte indice_ = 0;
                for (CardBelote carte_ : pli_) {
                    if (indice_ <= _numeroCarte) {
                        getDistribution().jouer(pli_.joueurAyantJoue(carte_),carte_);
                    }
                    indice_++;
                }
                key_++;
                continue;
            }
            for(CardBelote carte_:pli_) {
                getDistribution().jouer(pli_.joueurAyantJoue(carte_),carte_);
            }
            key_++;
        }
        sortHands(_displaying,_nombreJoueurs);
    }

    public void sortHands(DisplayingBelote _displaying,
                          byte _nombreJoueurs) {
        sortHands(_displaying, _nombreJoueurs, bid);
    }

    void sortHands(DisplayingBelote _displaying, byte _nombreJoueurs, BidBeloteSuit _bid) {
        if(_bid.getCouleurDominante()) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_nombreJoueurs; joueur_++) {
                getDistribution().trier(joueur_, _displaying.getDisplaying().getSuits(), _displaying.getDisplaying().isDecreasing(), _bid.getCouleur());
            }
        } else if(_bid.ordreCouleur()) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_nombreJoueurs; joueur_++) {
                getDistribution().trier(joueur_, _displaying.getDisplaying().getSuits(), _displaying.getDisplaying().isDecreasing(),Order.SUIT);
            }
        } else if(_bid.ordreAtout()) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<_nombreJoueurs; joueur_++) {
                getDistribution().trier(joueur_, _displaying.getDisplaying().getSuits(), _displaying.getDisplaying().isDecreasing(),Order.TRUMP);
            }
        }
    }

    public RulesBelote getRules() {
        return rules;
    }
    public void setRules(RulesBelote _rules) {
        rules = _rules;
    }
    public DealBelote getDistribution() {
        return distribution;
    }

    public void setDistributionCopy(DealBelote _distribution) {
        distribution = new DealBelote(_distribution);
    }

    public byte getPreneur() {
        return preneur;
    }

    public void setPreneur(byte _preneur) {
        preneur = _preneur;
    }

    public BidBeloteSuit getBid() {
        return bid;
    }

    public void setBid(BidBeloteSuit _bid) {
        bid = _bid;
    }

    public CustList<TrickBelote> getTricks() {
        return tricks;
    }

    public void setTricks(CustList<TrickBelote> _tricks, byte _nbPlayers) {
        tricks = _tricks;
        cardsHandsAtInitialState = new CustList<HandBelote>();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
            HandBelote hand_ = new HandBelote();
            hand_.ajouterCartes(distribution.hand(joueur_));
            for (TrickBelote pli_ : tricks) {
                hand_.ajouter(pli_.carteDuJoueur(joueur_));
            }
            cardsHandsAtInitialState.add(hand_);
        }
        HandBelote stack_ = new HandBelote();
        stack_.ajouterCartes(distribution.derniereMain());
        cardsHandsAtInitialState.add(stack_);
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
            cardsHandsAtInitialState.get(joueur_).supprimerCartes(cardsHandsAtInitialState.last());
        }
    }
    public CustList<HandBelote> getCardsHandsAtInitialState() {
        return cardsHandsAtInitialState;
    }
    public void setCardsHandsAtInitialState(
            CustList<HandBelote> _cardsHandsAtInitialState) {
        cardsHandsAtInitialState = _cardsHandsAtInitialState;
    }
    public void setDistribution(DealBelote _distribution) {
        distribution = _distribution;
    }
    public void setTricks(CustList<TrickBelote> _tricks) {
        tricks = _tricks;
    }
}
