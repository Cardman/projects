package cards.belote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import code.util.CustList;
import code.util.EqList;


public final class TricksHandsBelote {

    private RulesBelote rules;

    private DealBelote distribution;

    private byte preneur;

    private BidBeloteSuit bid;

    private CustList<TrickBelote> tricks;

    private EqList<HandBelote> cardsHandsAtInitialState;

    public void sortHands(DisplayingBelote _displaying,
            byte _nombreJoueurs) {
        if(bid.getCouleurDominante()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),bid.getCouleur());
            }
        } else if(bid.ordreCouleur()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),Order.SUIT);
            }
        } else if(bid.ordreAtout()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),Order.TRUMP);
            }
        }
    }
    public void restituerMains(DisplayingBelote _displaying,byte _nombreJoueurs,byte _numeroPli) {
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
            getDistribution().supprimerCartes(joueur_);
            getDistribution().main(joueur_).ajouterCartes(cardsHandsAtInitialState.get(joueur_));
        }
        if(_numeroPli>=0) {
            getDistribution().completerDonne(preneur, rules);
        }
        byte key_ = 0;
        for(TrickBelote pli_:tricks) {
            if(key_ >= _numeroPli) {
                continue;
            }
            for(CardBelote carte_:pli_) {
                getDistribution().jouer(pli_.joueurAyantJoue(carte_),carte_);
            }
            key_++;
        }
        if(bid.getCouleurDominante()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),bid.getCouleur());
            }
        } else if(bid.ordreCouleur()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),Order.SUIT);
            }
        } else if(bid.ordreAtout()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),Order.TRUMP);
            }
        }
    }
    public void restituerMains(DisplayingBelote _displaying,byte _nombreJoueurs,byte _numeroPli,byte _numeroCarte) {
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
            getDistribution().supprimerCartes(joueur_);
            getDistribution().main(joueur_).ajouterCartes(cardsHandsAtInitialState.get(joueur_));
        }
        if(_numeroPli>=0) {
            getDistribution().completerDonne(preneur, rules);
        }
        byte key_ = 0;
        for(TrickBelote pli_:tricks) {
            if(key_ >= _numeroPli) {
                continue;
            }
            if(key_ == _numeroPli + 1) {
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
        if(bid.getCouleurDominante()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),bid.getCouleur());
            }
        } else if(bid.ordreCouleur()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),Order.SUIT);
            }
        } else if(bid.ordreAtout()) {
            for (byte joueur_ = CustList.FIRST_INDEX;joueur_<_nombreJoueurs;joueur_++) {
                getDistribution().trier(joueur_,_displaying.getCouleurs(),_displaying.getDecroissant(),Order.TRUMP);
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

    public void setDistribution(DealBelote _distribution, boolean _copy) {
        if (_copy) {
            distribution = new DealBelote(_distribution);
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
        cardsHandsAtInitialState = new EqList<HandBelote>();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
            HandBelote hand_ = new HandBelote();
            hand_.ajouterCartes(distribution.main(joueur_));
            for (TrickBelote pli_ : tricks) {
                hand_.ajouter(pli_.carteDuJoueur(joueur_));
            }
            cardsHandsAtInitialState.add(hand_);
        }
        HandBelote stack_ = new HandBelote();
        stack_.ajouterCartes(distribution.derniereMain());
        cardsHandsAtInitialState.add(stack_);
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
            cardsHandsAtInitialState.get(joueur_).supprimerCartes(cardsHandsAtInitialState.last());
        }
    }
    public EqList<HandBelote> getCardsHandsAtInitialState() {
        return cardsHandsAtInitialState;
    }
    public void setCardsHandsAtInitialState(
            EqList<HandBelote> _cardsHandsAtInitialState) {
        cardsHandsAtInitialState = _cardsHandsAtInitialState;
    }
    public void setDistribution(DealBelote _distribution) {
        distribution = _distribution;
    }
    public void setTricks(CustList<TrickBelote> _tricks) {
        tricks = _tricks;
    }
}
