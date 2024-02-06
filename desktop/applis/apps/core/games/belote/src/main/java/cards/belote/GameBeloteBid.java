package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.consts.CardChar;
import cards.consts.Suit;
import code.util.*;
import code.util.core.NumberUtil;

public final class GameBeloteBid {
    private final HandBelote currentHand;
    private final RulesBelote rules;
    private final BidBeloteSuit bid;
    private final boolean endBidsFirstRound;
    private final HandBelote lastSeenHand;

    public GameBeloteBid(HandBelote _currentHand, RulesBelote _rules,
                         BidBeloteSuit _bid, boolean _endBidsFirstRound, HandBelote _lastSeenHand) {
        currentHand = _currentHand;
        rules = _rules;
        bid = _bid;
        endBidsFirstRound = _endBidsFirstRound;
        lastSeenHand = _lastSeenHand;
    }
    public BidBeloteSuit strategieContrat() {
        BidBeloteSuit contratJoueur_=contrat();
        if (rules.dealAll()) {
            return contratJoueur_;
        }
        if(contratJoueur_.estDemandable(bid)) {
            return contratJoueur_;
        }
        return new BidBeloteSuit();
    }

    public CustList<BidBeloteSuit> allowedBids() {
        if (rules.dealAll()) {
            return allowedBidsDealAll();
        }
        CustList<BidBeloteSuit> encheres_ = new CustList<BidBeloteSuit>();
        Suit s_ = lastSeenHand.premiereCarte().getId().getCouleur();
//        for(BidBelote e: rules.getListeEncheresAutorisees()) {
////            if(rules.getAllowedBids().getVal(e) != BoolVal.TRUE) {
////                continue;
////            }
//            if (!e.getCouleurDominante()) {
//                BidBeloteSuit e_ = new BidBeloteSuit();
//                e_.setBid(e);
//                encheres_.add(e_);
//            }
//        }
        BidBeloteSuit fold_;
        fold_ = new BidBeloteSuit();
        fold_.setSuit(Suit.UNDEFINED);
        fold_.setBid(BidBelote.FOLD);
        encheres_.add(fold_);
        if (!endBidsFirstRound) {
            /*First round of bidding*/
            BidBeloteSuit e_ = new BidBeloteSuit();
            e_.setBid(BidBelote.SUIT);
            e_.setSuit(s_);
            encheres_.add(e_);
        } else {
            /*Second round of bidding*/
            for (Suit s: GameBeloteCommon.couleurs()) {
                if (s == s_) {
                    continue;
                }
                BidBeloteSuit e_ = new BidBeloteSuit();
                e_.setBid(BidBelote.OTHER_SUIT);
                e_.setSuit(s);
                encheres_.add(e_);
            }
        }
//        encheres_.sortElts(new BidBeloteSuitComparator());
        nonDomBids(rules.getListeEncheresAutorisees(), encheres_);
        return encheres_;
    }

    private CustList<BidBeloteSuit> allowedBidsDealAll() {
        CustList<BidBeloteSuit> encheres_ = new CustList<BidBeloteSuit>();
        for (BidBeloteSuit b: baseBidsDealAll(rules.getListeEncheresAutorisees())) {
            for (int p: RulesBelote.getPoints()) {
                if (bid.getPoints() >= p) {
                    continue;
                }
                BidBeloteSuit bid_;
                bid_ = new BidBeloteSuit();
                bid_.setSuit(b.getSuit());
                bid_.setBid(b.getBid());
                bid_.setPoints(p);
                encheres_.add(bid_);
            }
        }
        return encheres_;
    }
    public static CustList<BidBeloteSuit> baseBidsDealAll(IdList<BidBelote> _ls) {
        CustList<BidBeloteSuit> encheres_ = new CustList<BidBeloteSuit>();
        for (Suit s: GameBeloteCommon.couleurs()) {
            BidBeloteSuit bid_;
            bid_ = new BidBeloteSuit();
            bid_.setSuit(s);
            bid_.setBid(BidBelote.SUIT);
            encheres_.add(bid_);
        }
        nonDomBids(_ls, encheres_);
        return encheres_;
    }

    private static void nonDomBids(IdList<BidBelote> _ls, CustList<BidBeloteSuit> _encheres) {
        for (BidBelote b: BidBelote.getNonZeroBids()) {
            if (b.getCouleurDominante() || !_ls.containsObj(b)) {
                continue;
            }
            BidBeloteSuit bid_;
            bid_ = new BidBeloteSuit();
            bid_.setSuit(Suit.UNDEFINED);
            bid_.setBid(b);
            _encheres.add(bid_);
        }
    }

    BidBeloteSuit contrat() {
        HandBelote reunion_=new HandBelote();
        reunion_.ajouterCartes(currentHand);
        reunion_.ajouterCartes(lastSeenHand);
        BidBelote enchereCouleurDominante_ = BidBelote.FOLD;
        IdList<Suit> suits_ = new IdList<Suit>();
        for(BidBeloteSuit e: allowedBids()) {
            if(!e.getCouleurDominante()) {
                continue;
            }
            enchereCouleurDominante_ = e.getBid();
            if (!suits_.containsObj(e.getSuit())) {
                suits_.add(e.getSuit());
            }
        }
        if (!rules.dealAll()) {
            return end(reunion_, enchereCouleurDominante_, suits_);
        }
        return dealAllBid(reunion_, enchereCouleurDominante_, suits_);
    }

    private BidBeloteSuit dealAllBid(HandBelote _reunion, BidBelote _enchereCouleurDominante, IdList<Suit> _suits) {
        IdList<Suit> s_ = new IdList<Suit>();
        for(Suit c: GameBeloteCommon.couleurs()) {
            BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
            enchereBeloteLoc_.setSuit(c);
            enchereBeloteLoc_.setBid(BidBelote.SUIT);
            IdMap<Suit, HandBelote> played_ = new HandBelote().couleurs(enchereBeloteLoc_);
            IdMap<Suit,HandBelote> repartition_= _reunion.couleurs(enchereBeloteLoc_);
            if (repartition_.getVal(c).estVide()) {
                continue;
            }
            IdMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(repartition_, played_, enchereBeloteLoc_);
            if (HandBelote.reunion(leading_).total() == _reunion.total()) {
                s_.add(c);
            }
        }
        if (!s_.isEmpty()) {
            BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
            enchereCouleur_.setBid(_enchereCouleurDominante);
            enchereCouleur_.setSuit(s_.first());
            enchereCouleur_.setPoints(HandBelote.pointsTotauxDixDeDer());
            return enchereCouleur_;
        }
        IdList<BidBeloteSuit> b_ = new IdList<BidBeloteSuit>();
        for(BidBelote e: rules.getListeEncheresAutorisees()) {
            if (e.getCouleurDominante() || e == BidBelote.FOLD) {
                continue;
            }
            BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
            enchereBeloteLoc_.setSuit(Suit.UNDEFINED);
            enchereBeloteLoc_.setBid(e);
            IdMap<Suit, HandBelote> played_ = new HandBelote().couleurs(enchereBeloteLoc_);
            IdMap<Suit,HandBelote> repartition_= _reunion.couleurs(enchereBeloteLoc_);
            IdMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(repartition_, played_, enchereBeloteLoc_);
            if (HandBelote.reunion(leading_).total() == _reunion.total()) {
                b_.add(enchereBeloteLoc_);
            }
        }
        if (!b_.isEmpty()) {
            b_.first().setPoints(HandBelote.pointsTotauxDixDeDer());
            return b_.first();
        }
        return end(_reunion, _enchereCouleurDominante, _suits);
    }

    private BidBeloteSuit end(HandBelote _reunion, BidBelote _enchereCouleurDominante, IdList<Suit> _suits) {
        IdMap<BidBelote,Long> couleurPointsFictifsContrats_ = new IdMap<BidBelote,Long>();
//        IdMap<BidBelote,Long> couleurPointsFictifsContratsRequis_ = new IdMap<BidBelote,Long>();
        IdMap<Suit, Long> couleursCandidates_ = couleursCandidates(_reunion, _suits, couleurPointsFictifsContrats_);
        Suit couleurMax_ = Suit.UNDEFINED;
        long max_ = 0;
        for(EntryCust<Suit, Long> c: couleursCandidates_.entryList()) {
            if(c.getValue() > max_) {
                couleurMax_ = c.getKey();
                max_ = c.getValue();
            }
        }
        long max2_ = 0;
        if(couleurMax_ == Suit.UNDEFINED) {
            if(couleurPointsFictifsContrats_.isEmpty()) {
                return new BidBeloteSuit();
            }
        } else {
            max2_ = max_;
        }
        BidBelote e_ = BidBelote.FOLD;
        int lim_ = Suit.couleursOrdinaires().size() / 2;
        long pts_ = HandBelote.pointsTotauxDixDeDer();
        pts_ /= 2;
        for(EntryCust<BidBelote, Long> e: couleurPointsFictifsContrats_.entryList()) {
            if(e.getValue() < pts_) {
//            if(couleurPointsFictifsContrats_.getVal(e) < couleurPointsFictifsContratsRequis_.getVal(e)) {
                continue;
            }
            int ls_ = suitWithLeadingCards(_reunion, e.getKey());
            if (ls_ > lim_ && e.getValue() > max2_) {
                e_ = e.getKey();
                max2_ = e.getValue();
            }
        }
        if (!rules.dealAll()) {
            return notAllBidChoice(_enchereCouleurDominante, couleurMax_, max_, max2_, e_);
        }
        return allBidChoice(_enchereCouleurDominante, couleurPointsFictifsContrats_, couleursCandidates_, couleurMax_, max_, max2_, e_);
    }

    private BidBeloteSuit allBidChoice(BidBelote _enchereCouleurDominante, IdMap<BidBelote, Long> _couleurPointsFictifsContrats, IdMap<Suit, Long> _couleursCandidates, Suit _couleurMax, long _max, long _max2, BidBelote _e) {
        if(_couleurMax != Suit.UNDEFINED && _max == _max2) {
            int minPointsMinusOne_ = RulesBelote.LEAST - 1;
            long pts_ = _couleursCandidates.getVal(_couleurMax);
            pts_ /= RulesBelote.DIVISIONS;
            pts_ *= RulesBelote.DIVISIONS;
            if (pts_ > NumberUtil.max(minPointsMinusOne_, bid.getPoints())) {
                BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
                enchereCouleur_.setBid(_enchereCouleurDominante);
                enchereCouleur_.setSuit(_couleurMax);
                enchereCouleur_.setPoints((int) pts_);
                return enchereCouleur_;
            }
        }
        if(_e != BidBelote.FOLD) {
            int minPointsMinusOne_ = RulesBelote.LEAST - 1;
            long pts_ = _couleurPointsFictifsContrats.getVal(_e);
            pts_ /= RulesBelote.DIVISIONS;
            pts_ *= RulesBelote.DIVISIONS;
            if (pts_ > NumberUtil.max(minPointsMinusOne_, bid.getPoints())) {
                BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
                enchereCouleur_.setBid(_e);
                enchereCouleur_.setPoints((int) pts_);
                return enchereCouleur_;
            }
        }
        return new BidBeloteSuit();
    }

    private BidBeloteSuit notAllBidChoice(BidBelote _enchereCouleurDominante, Suit _couleurMax, long _max, long _max2, BidBelote _e) {
        if(_couleurMax != Suit.UNDEFINED && _max == _max2) {
            BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
            enchereCouleur_.setBid(_enchereCouleurDominante);
            enchereCouleur_.setSuit(_couleurMax);
            return enchereCouleur_;
        }
        if(_e != BidBelote.FOLD) {
            BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
            enchereCouleur_.setBid(_e);
            return enchereCouleur_;
        }
        return new BidBeloteSuit();
    }

    private int suitWithLeadingCards(HandBelote _reunion, BidBelote _e) {
        BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
        enchereBeloteLoc_.setSuit(Suit.UNDEFINED);
        enchereBeloteLoc_.setBid(_e);
        IdMap<Suit, HandBelote> played_ = new HandBelote().couleurs(enchereBeloteLoc_);
        IdMap<Suit,HandBelote> repartition_= _reunion.couleurs(enchereBeloteLoc_);
        IdMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(repartition_, played_, enchereBeloteLoc_);
        int ls_ = 0;
        for (HandBelote h: leading_.values()) {
            if (!h.estVide()) {
                ls_++;
            }
        }
        return ls_;
    }

    private IdMap<Suit, Long> couleursCandidates(HandBelote _reunion, IdList<Suit> _suits, IdMap<BidBelote, Long> _couleurPointsFictifsContrats) {
        int nbPlayers_ = rules.getDealing().getId().getNombreJoueurs();
        int nbCartesFinales_ = rules.getDealing().getNombreCartesParJoueur();
        IdMap<Suit,Long> couleurPointsFictifs_ = _reunion.pointsAvg(nbPlayers_,nbCartesFinales_);
        IdMap<Suit, Long> couleurPointsFictifsRequis_ = couleurPointsFictifsRequis(_reunion);
        for(BidBelote e: rules.getListeEncheresAutorisees()) {
            if (e.getCouleurDominante() || e == BidBelote.FOLD) {
                continue;
            }
            _couleurPointsFictifsContrats.put(e, _reunion.pointsBid(nbPlayers_, nbCartesFinales_,e));
//            long pts_ = HandBelote.pointsTotauxDixDeDer();
//            pts_ /= 2;
//            _couleurPointsFictifsContratsRequis.put(e, pts_);
        }
        IdMap<Suit,Long> couleursCandidates_ = new IdMap<Suit,Long>();
        for(Suit c: _suits) {
            if(couleurPointsFictifs_.getVal(c) < couleurPointsFictifsRequis_.getVal(c)) {
                continue;
            }
            couleursCandidates_.put(c, couleurPointsFictifs_.getVal(c));
        }
        return couleursCandidates_;
    }

    private IdMap<Suit, Long> couleurPointsFictifsRequis(HandBelote _reunion) {
        IdMap<Suit,Long> couleurPointsFictifsRequis_ = new IdMap<Suit,Long>();
        for(Suit c: GameBeloteCommon.couleurs()) {
            long pts_ = pts(_reunion, c);
            couleurPointsFictifsRequis_.put(c, pts_);
        }
        return couleurPointsFictifsRequis_;
    }

    private long pts(HandBelote _reunion, Suit _s) {
        long pts_ = HandBelote.pointsTotauxDixDeDer();
        int nb_ = 0;
        for(CardBelote t: _reunion.couleur(_s)){
            if(t.getId().getNomFigure() == CardChar.KING) {
                nb_++;
            }
            if(t.getId().getNomFigure() == CardChar.QUEEN) {
                nb_++;
            }
        }
//        HandBelote cartes_ = new HandBelote();
//        for(CardBelote t: GameBeloteCommonPlaying.cartesAtouts(_s)) {
//            if(t.getId().getNomFigure() == CardChar.KING) {
//                cartes_.ajouter(t);
//            }
//            if(t.getId().getNomFigure() == CardChar.QUEEN) {
//                cartes_.ajouter(t);
//            }
//        }
        if (nb_ == 2) {
            pts_ += DeclaresBeloteRebelote.BELOTE_REBELOTE.getPoints();
        }
        pts_ /= 2;
        return pts_;
    }
}
