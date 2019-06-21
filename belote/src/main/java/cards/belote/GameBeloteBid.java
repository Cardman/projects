package cards.belote;

import cards.belote.comparators.BidBeloteSuitComparator;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;
import code.maths.Rate;
import code.util.*;

public final class GameBeloteBid {
    private HandBelote currentHand;
    private RulesBelote rules;
    private EqList<BidBeloteSuit> bids;
    private BidBeloteSuit bid;
    private boolean endBidsFirstRound;
    private HandBelote lastSeenHand;

    public GameBeloteBid(HandBelote _currentHand, RulesBelote _rules,
                         EqList<BidBeloteSuit> _bids, BidBeloteSuit _bid, boolean _endBidsFirstRound, HandBelote _lastSeenHand) {
        currentHand = _currentHand;
        rules = _rules;
        bids = _bids;
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

    public EqList<BidBeloteSuit> allowedBids() {
        EqList<BidBeloteSuit> encheres_ = new EqList<BidBeloteSuit>();
        if (rules.dealAll()) {
            for (Suit s: GameBeloteCommon.couleurs()) {
                for (int p: RulesBelote.getPoints()) {
                    if (bid.getPoints() >= p) {
                        continue;
                    }
                    BidBeloteSuit bid_;
                    bid_ = new BidBeloteSuit();
                    bid_.setCouleur(s);
                    bid_.setEnchere(BidBelote.SUIT);
                    bid_.setPoints(p);
                    encheres_.add(bid_);
                }
            }
            for (BidBelote b: BidBelote.getNonZeroBids()) {
                if (b.getCouleurDominante()) {
                    continue;
                }
                if (!rules.getEncheresAutorisees().getVal(b)) {
                    continue;
                }
                for (int p: RulesBelote.getPoints()) {
                    if (bid.getPoints() >= p) {
                        continue;
                    }
                    BidBeloteSuit bid_;
                    bid_ = new BidBeloteSuit();
                    bid_.setCouleur(Suit.UNDEFINED);
                    bid_.setEnchere(b);
                    bid_.setPoints(p);
                    encheres_.add(bid_);
                }
            }
            return encheres_;
        }
        Suit s_ = lastSeenHand.premiereCarte().couleur();
        for(BidBelote e: rules.getEncheresAutorisees().getKeys()) {
            if(!rules.getEncheresAutorisees().getVal(e)) {
                continue;
            }
            if (!e.getCouleurDominante()) {
                BidBeloteSuit e_ = new BidBeloteSuit();
                e_.setEnchere(e);
                encheres_.add(e_);
                continue;
            }
            boolean pasEncherePrio_ = true;
            for(BidBelote e2_: rules.getEncheresAutorisees().getKeys()) {
                if(e2_.estPrioritaire(e, !endBidsFirstRound)) {
                    pasEncherePrio_ = false;
                }
            }
            if(pasEncherePrio_) {
                if (!endBidsFirstRound) {
                    /*First round of bidding*/
                    BidBeloteSuit e_ = new BidBeloteSuit();
                    e_.setEnchere(e);
                    e_.setCouleur(s_);
                    encheres_.add(e_);
                    continue;
                }
                /*Second round of bidding*/
                for (Suit s: GameBeloteCommon.couleurs()) {
                    if (s == s_) {
                        continue;
                    }
                    BidBeloteSuit e_ = new BidBeloteSuit();
                    e_.setEnchere(e);
                    e_.setCouleur(s);
                    encheres_.add(e_);
                }
            }
        }
        encheres_.sortElts(new BidBeloteSuitComparator());
        return encheres_;
    }
    BidBeloteSuit contrat() {
        HandBelote reunion_=new HandBelote();
        reunion_.ajouterCartes(currentHand);
        reunion_.ajouterCartes(lastSeenHand);
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        BidBelote enchereCouleurDominante_ = BidBelote.FOLD;
        EnumList<Suit> suits_ = new EnumList<Suit>();
        Numbers<Integer> points_ = new Numbers<Integer>();
        for(BidBeloteSuit e: allowedBids()) {
            points_.add(e.getPoints());
            if(!e.getCouleurDominante()) {
                continue;
            }
            enchereCouleurDominante_ = e.getEnchere();
            if (!suits_.containsObj(e.getCouleur())) {
                suits_.add(e.getCouleur());
            }
        }
        points_.removeDuplicates();
        points_.sort();
        EnumMap<Suit,Rate> couleurPointsFictifs_ = new EnumMap<Suit,Rate>();
        EnumMap<Suit,Rate> couleurPointsFictifsRequis_ = new EnumMap<Suit,Rate>();
        int nbCartesFictives_ = reunion_.total();
        int nbCartesFinales_ = rules.getRepartition().getNombreCartesParJoueur();
        for(Suit c: GameBeloteCommon.couleurs()) {
            //c: couleur atout
            BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
            enchereBeloteLoc_.setCouleur(c);
            enchereBeloteLoc_.setEnchere(BidBelote.SUIT);
            EnumMap<Suit,HandBelote> repartition_=reunion_.couleurs(enchereBeloteLoc_);
            //repartition est la repartition des cartes a la couleur d'atout c
            int pointsFictifs_ = 0;
            for(Suit c2_: GameBeloteCommon.couleurs()) {
                if(c2_ == c) {
                    continue;
                }
                //c2: couleur ordinaire
                HandBelote cartesAssurantMax_ = repartition_.getVal(c2_).cartesPlisAssures(enchereBeloteLoc_);
                for(CardBelote c3_: cartesAssurantMax_) {
                    pointsFictifs_+=c3_.points(enchereBeloteLoc_)+4;
                }
            }
            HandBelote cartesAssurantMax_ = repartition_.getVal(c).cartesPlisAssures(enchereBeloteLoc_);
            for(CardBelote c3_: cartesAssurantMax_) {
                pointsFictifs_+=c3_.points(enchereBeloteLoc_)+8;
            }
            couleurPointsFictifs_.put(c, new Rate(pointsFictifs_*nbCartesFinales_,HandBelote.pointsTotauxDixDeDer(enchereBeloteLoc_)*nbCartesFictives_));
            couleurPointsFictifsRequis_.put(c, new Rate(1,2));
            //Comparaison
        }
        EnumMap<BidBelote,Rate> couleurPointsFictifsContrats_ = new EnumMap<BidBelote,Rate>();
        EnumMap<BidBelote,Rate> couleurPointsFictifsContratsRequis_ = new EnumMap<BidBelote,Rate>();
        for(BidBelote e: rules.getEncheresAutorisees().getKeys()) {
            if(!rules.getEncheresAutorisees().getVal(e)) {
                continue;
            }
            if (e.getCouleurDominante()) {
                continue;
            }
            Suit c_ = Suit.UNDEFINED;
            BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
            enchereBeloteLoc_.setCouleur(c_);
            enchereBeloteLoc_.setEnchere(e);
            EnumMap<Suit,HandBelote> repartition_=reunion_.couleurs(enchereBeloteLoc_);
            int pointsFictifs_ = 0;
            boolean toutesCouleursAvecCarteMaitresse_ = true;
            for(Suit c2_: GameBeloteCommon.couleurs()) {
                if(GameBeloteCommon.cartesMaitresses(repartition_, new HandBelote().couleurs(enchereBeloteLoc_),enchereBeloteLoc_).getVal(c2_).estVide()) {
                    toutesCouleursAvecCarteMaitresse_ = false;
                    break;
                }
                HandBelote cartesAssurantMax_ = repartition_.getVal(c2_).cartesPlisAssures(enchereBeloteLoc_);
                for(CardBelote c3_: cartesAssurantMax_) {
                    pointsFictifs_+=c3_.points(enchereBeloteLoc_)+5;
                }
            }
            if(!toutesCouleursAvecCarteMaitresse_) {
                continue;
            }
            couleurPointsFictifsContrats_.put(e, new Rate(pointsFictifs_*nbCartesFinales_,HandBelote.pointsTotauxDixDeDer(enchereBeloteLoc_)*nbCartesFictives_));
            couleurPointsFictifsContratsRequis_.put(e, new Rate(1,2));
        }
        EnumMap<Suit,Rate> couleursCandidates_ = new EnumMap<Suit,Rate>();
        for(Suit c: suits_) {
            if(Rate.strLower(couleurPointsFictifs_.getVal(c), couleurPointsFictifsRequis_.getVal(c))) {
                continue;
            }
            couleursCandidates_.put(c,Rate.divide(couleurPointsFictifs_.getVal(c),couleurPointsFictifsRequis_.getVal(c)));
        }
        Suit couleurMax_ = Suit.UNDEFINED;
        Rate max_ = Rate.zero();
        for(Suit c: couleursCandidates_.getKeys()) {
            if(Rate.strGreater(couleursCandidates_.getVal(c), max_)) {
                couleurMax_ = c;
                max_ = couleursCandidates_.getVal(c);
            }
        }
        Rate max2_ = Rate.zero();
        if(couleurMax_ == Suit.UNDEFINED) {
            if(couleurPointsFictifsContrats_.isEmpty()) {
                return enchereCouleur_;
            }
        } else {
            max2_ = max_;
        }
        BidBelote e_ = BidBelote.FOLD;
        for(BidBelote e: couleurPointsFictifsContrats_.getKeys()) {
            if(Rate.strLower(couleurPointsFictifsContrats_.getVal(e), couleurPointsFictifsContratsRequis_.getVal(e))) {
                continue;
            }
            if(Rate.strGreater(couleurPointsFictifsContrats_.getVal(e), max2_)) {
                e_ = e;
                max2_ = couleurPointsFictifsContrats_.getVal(e);
            }
        }
        if (rules.dealAll()) {
            if(Rate.eq(max_,max2_)) {
                int pts_ = (int)
                        Rate.multiply(couleurPointsFictifs_.getVal(couleurMax_),
                                new Rate(HandBelote.pointsTotauxDixDeDer(enchereCouleur_))).ll();
                if (!points_.isEmpty()) {
                    if (pts_ > points_.first()) {
                        enchereCouleur_.setEnchere(enchereCouleurDominante_);
                        enchereCouleur_.setCouleur(couleurMax_);
                        enchereCouleur_.setPoints(points_.first());
                        return enchereCouleur_;
                    }
                }
            } else if(e_ != BidBelote.FOLD) {
                int pts_ = (int)
                        Rate.multiply(couleurPointsFictifsContrats_.getVal(e_),
                                new Rate(HandBelote.pointsTotauxDixDeDer(enchereCouleur_))).ll();
                if (!points_.isEmpty()) {
                    if (pts_ > points_.first()) {
                        enchereCouleur_.setEnchere(e_);
                        enchereCouleur_.setPoints(pts_);
                        return enchereCouleur_;
                    }
                }
            }
        } else {
            if(Rate.eq(max_,max2_)) {
                enchereCouleur_.setEnchere(enchereCouleurDominante_);
                enchereCouleur_.setCouleur(couleurMax_);
                enchereCouleur_.setPoints((int)
                        Rate.multiply(couleurPointsFictifs_.getVal(couleurMax_),
                                new Rate(HandBelote.pointsTotauxDixDeDer(enchereCouleur_))).ll());
                return enchereCouleur_;
            } else if(e_ != BidBelote.FOLD) {
                enchereCouleur_.setEnchere(e_);
                enchereCouleur_.setPoints((int)
                        Rate.multiply(couleurPointsFictifsContrats_.getVal(e_),
                                new Rate(HandBelote.pointsTotauxDixDeDer(enchereCouleur_))).ll());
                return enchereCouleur_;
            }
        }
        return enchereCouleur_;
    }
}
