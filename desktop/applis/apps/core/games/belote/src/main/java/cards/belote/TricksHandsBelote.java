package cards.belote;
import cards.consts.Order;
import code.util.CustList;
import code.util.core.IndexConstants;


public final class TricksHandsBelote {

    private DealBelote distribution;

    private byte preneur;

    private BidBeloteSuit bid;

    private CustList<TrickBelote> tricks;

    private CustList<HandBelote> cardsHandsAtInitialState;

    public void restituerMains(DisplayingBelote _displaying, byte _numeroPli, RulesBelote _rules) {
        init(_numeroPli,_rules);
        byte key_ = 1;
        for(TrickBelote pli_:tricks.mid(RulesBelote.offset(_rules))) {
            if(key_ > _numeroPli) {
                continue;
            }
            previousTrick(pli_,_rules);
            key_++;
        }
        sortHands(_displaying,_rules);
    }

    public void restituerMains(DisplayingBelote _displaying, byte _numeroPli, byte _numeroCarte, RulesBelote _rules) {
        init(_numeroPli,_rules);
        byte key_ = 1;
        for(TrickBelote pli_:tricks.mid(RulesBelote.offset(_rules))) {
            if(key_ > _numeroPli) {
                continue;
            }
            if(key_ == _numeroPli) {
                currentTrick(_numeroCarte, pli_,_rules);
            } else {
                previousTrick(pli_,_rules);
            }
            key_++;
        }
        sortHands(_displaying,_rules);
    }

    private void init(byte _numeroPli, RulesBelote _rules) {
        int nb_ = _rules.getDealing().getId().getNombreJoueurs();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_< nb_; joueur_++) {
            getDistribution().supprimerCartes(joueur_);
            getDistribution().hand(joueur_).ajouterCartes(cardsHandsAtInitialState.get(joueur_));
        }
        if (_rules.getDealing().getDiscarded() > 0) {
            if (_numeroPli >= 0) {
                restoreTakerHand(getDistribution(), preneur, tricks);
            }
            return;
        }
        if(_numeroPli >=0) {
            getDistribution().completerDonne(preneur, _rules);
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

    private void previousTrick(TrickBelote _pli, RulesBelote _rules) {
        byte nombreJoueurs_ = (byte) _rules.getDealing().getId().getNombreJoueurs();
        for (byte p: _pli.playersHavingPlayed(nombreJoueurs_)) {
            getDistribution().jouer(p, _pli.carteDuJoueur(p,nombreJoueurs_));
        }
    }

    private void currentTrick(byte _numeroCarte, TrickBelote _pli, RulesBelote _rules) {
        byte nombreJoueurs_ = (byte) _rules.getDealing().getId().getNombreJoueurs();
        byte indice_ = 0;
        for (byte p: _pli.playersHavingPlayed(nombreJoueurs_)) {
            if (indice_ <= _numeroCarte) {
                getDistribution().jouer(p, _pli.carteDuJoueur(p,nombreJoueurs_));
            }
            indice_++;
        }
    }


    public void sortHands(DisplayingBelote _displaying, RulesBelote _rules) {
        sortHands(_displaying, bid,_rules);
    }

    void sortHands(DisplayingBelote _displaying, BidBeloteSuit _bid, RulesBelote _rules) {
        int nb_ = _rules.getDealing().getId().getNombreJoueurs();
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
        tricks(_g,_g.getRegles());
    }

    public void tricks(GameBelote _g, RulesBelote _r) {
        players(_g);
        endRestore(cardsHandsAtInitialState, preneur, _g.getTricks(), _r);
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
        return players(_g,_g.getRegles());
    }
    public HandBelote players(GameBelote _g, RulesBelote _r) {
        CustList<TrickBelote> tr_ = _g.getTricks();
        tricks = new CustList<TrickBelote>(tr_);
        if (_g.keepPlayingCurrentGame()) {
            tricks.add(_g.getProgressingTrick());
        }
        setDistributionCopy(_g.getDistribution());
        setPreneur(_g.getPreneur());
        setBid(_g.getBid());
        byte nb_ = _g.getNombreDeJoueurs();
        int off_ = RulesBelote.offset(_r);
        boolean add_ = _g.addCurrentTrick();
        cardsHandsAtInitialState = _g.getProgressingTrick().completeCurrent(nb_, add_);
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nb_; joueur_++) {
            HandBelote hand_ = new HandBelote();
            hand_.ajouterCartes(distribution.hand(joueur_));
            for (TrickBelote pli_ : tr_.mid(off_)) {
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
