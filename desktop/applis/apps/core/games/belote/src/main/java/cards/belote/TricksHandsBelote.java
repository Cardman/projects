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

    public void restituerMains(DisplayingBelote _displaying, byte _numeroPli) {
        init(_numeroPli);
        byte key_ = 1;
        for(TrickBelote pli_:tricks.mid(RulesBelote.offset(rules))) {
            if(key_ > _numeroPli) {
                continue;
            }
            previousTrick(pli_);
            key_++;
        }
        sortHands(_displaying);
    }
    public void restituerMains(DisplayingBelote _displaying, byte _numeroPli, byte _numeroCarte) {
        init(_numeroPli);
        byte key_ = 1;
        for(TrickBelote pli_:tricks.mid(RulesBelote.offset(rules))) {
            if(key_ > _numeroPli) {
                continue;
            }
            if(key_ == _numeroPli) {
                currentTrick(_numeroCarte, pli_);
            } else {
                previousTrick(pli_);
            }
            key_++;
        }
        sortHands(_displaying);
    }

    private void init(byte _numeroPli) {
        int nb_ = rules.getDealing().getId().getNombreJoueurs();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_< nb_; joueur_++) {
            getDistribution().supprimerCartes(joueur_);
            getDistribution().hand(joueur_).ajouterCartes(cardsHandsAtInitialState.get(joueur_));
        }
        if (rules.getDealing().getDiscarded() > 0) {
            if (_numeroPli >= 0) {
                restoreTakerHand(getDistribution(), preneur, tricks);
            }
            return;
        }
        if(_numeroPli >=0) {
            getDistribution().completerDonne(preneur, rules);
        }
    }

    private static void restoreTakerHand(DealBelote _deal, byte _preneur, CustList<TrickBelote> _tricks) {
        TrickBelote possibleFirst_ = GameBelote.discardedCards(_tricks, new TrickBelote());
        restoreTakerHand(_deal, _preneur, possibleFirst_);
    }

    public static void restoreTakerHand(DealBelote _deal, byte _preneur, TrickBelote _possibleFirst) {
        _deal.hand(_preneur).ajouterCartes(_deal.derniereMain());
        _deal.supprimerCartes(_preneur, _possibleFirst.getCartes());
    }

    private void previousTrick(TrickBelote _pli) {
        for (CardBelote carte_ : _pli) {
            getDistribution().jouer(_pli.joueurAyantJoue(carte_), carte_);
        }
    }

    private void currentTrick(byte _numeroCarte, TrickBelote _pli) {
        byte indice_ = 0;
        for (CardBelote carte_ : _pli) {
            if (indice_ <= _numeroCarte) {
                getDistribution().jouer(_pli.joueurAyantJoue(carte_),carte_);
            }
            indice_++;
        }
    }

    public void sortHands(DisplayingBelote _displaying) {
        sortHands(_displaying, bid);
    }

    void sortHands(DisplayingBelote _displaying, BidBeloteSuit _bid) {
        int nb_ = rules.getDealing().getId().getNombreJoueurs();
        if(_bid.getCouleurDominante()) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nb_; joueur_++) {
                getDistribution().trier(joueur_, _displaying.getDisplaying().getSuits(), _displaying.getDisplaying().isDecreasing(), _bid.getSuit());
            }
        } else if(_bid.ordreCouleur()) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nb_; joueur_++) {
                getDistribution().trier(joueur_, _displaying.getDisplaying().getSuits(), _displaying.getDisplaying().isDecreasing(),Order.SUIT);
            }
        } else if(_bid.ordreAtout()) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nb_; joueur_++) {
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

    public void tricks(GameBelote _g) {
        players(_g);
        endRestore(cardsHandsAtInitialState, preneur, tricks, _g.getRegles());
    }

    public static void endRestore(CustList<HandBelote> _hands, byte _preneur, CustList<TrickBelote> _tricks, RulesBelote _rules) {
        TrickBelote possibleFirst_ = GameBelote.discardedCards(_tricks, new TrickBelote());
        endRestore(_hands, _preneur, possibleFirst_, _rules);
    }

    public static void endRestore(CustList<HandBelote> _hands, byte _preneur, TrickBelote _possibleFirst, RulesBelote _rules) {
        HandBelote lastHand_ = new HandBelote(_hands.last());
        int nb_ = _rules.getDealing().getId().getNombreJoueurs();
        if (_preneur > -1 && _rules.getDealing().getDiscarded() > 0) {
            _hands.get(_preneur)
                    .ajouterCartes(_possibleFirst.getCartes());
            _hands.get(_preneur)
                    .supprimerCartes(lastHand_);
            return;
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nb_; joueur_++) {
            _hands.get(joueur_).supprimerCartes(lastHand_);
        }
    }

    public HandBelote players(GameBelote _g) {
        tricks = _g.getTricks();
        setRules(_g.getRegles());
        setDistributionCopy(_g.getDistribution());
        setPreneur(_g.getPreneur());
        setBid(_g.getBid());
        byte nb_ = _g.getNombreDeJoueurs();
        int off_ = RulesBelote.offset(getRules());
        boolean add_ = _g.addCurrentTrick();
        cardsHandsAtInitialState = _g.getProgressingTrick().completeCurrent(nb_, add_);
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nb_; joueur_++) {
            HandBelote hand_ = new HandBelote();
            hand_.ajouterCartes(distribution.hand(joueur_));
            for (TrickBelote pli_ : tricks.mid(off_)) {
                hand_.ajouter(pli_.carteDuJoueur(joueur_));
            }
            cardsHandsAtInitialState.get(joueur_).ajouterCartes(hand_);
        }
        HandBelote stack_ = new HandBelote();
        stack_.ajouterCartes(distribution.derniereMain());
        cardsHandsAtInitialState.add(stack_);
        return stack_;
    }

    public CustList<TrickBelote> getTricks() {
        return tricks;
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
