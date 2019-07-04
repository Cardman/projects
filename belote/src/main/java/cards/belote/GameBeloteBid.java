package cards.belote;

import cards.belote.comparators.BidBeloteSuitComparator;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.consts.CardChar;
import cards.consts.Suit;
import code.util.*;

public final class GameBeloteBid {
    private HandBelote currentHand;
    private RulesBelote rules;
    private BidBeloteSuit bid;
    private boolean endBidsFirstRound;
    private HandBelote lastSeenHand;

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
        CustList<BidBeloteSuit> encheres_ = new CustList<BidBeloteSuit>();
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
        int minPointsMinusOne_ = RulesBelote.getPoints().first() - 1;
        for(BidBeloteSuit e: allowedBids()) {
            if(!e.getCouleurDominante()) {
                continue;
            }
            enchereCouleurDominante_ = e.getEnchere();
            if (!suits_.containsObj(e.getCouleur())) {
                suits_.add(e.getCouleur());
            }
        }
        if (rules.dealAll()) {
            EnumList<Suit> s_ = new EnumList<Suit>();
            for(Suit c: GameBeloteCommon.couleurs()) {
                BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
                enchereBeloteLoc_.setCouleur(c);
                enchereBeloteLoc_.setEnchere(BidBelote.SUIT);
                EnumMap<Suit, HandBelote> played_ = new HandBelote().couleurs(enchereBeloteLoc_);
                EnumMap<Suit,HandBelote> repartition_=reunion_.couleurs(enchereBeloteLoc_);
                if (repartition_.getVal(c).estVide()) {
                    continue;
                }
                EnumMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(repartition_, played_, enchereBeloteLoc_);
                if (HandBelote.reunion(leading_).total() == reunion_.total()) {
                    s_.add(c);
                }
            }
            if (!s_.isEmpty()) {
                enchereCouleur_.setEnchere(enchereCouleurDominante_);
                enchereCouleur_.setCouleur(s_.first());
                enchereCouleur_.setPoints(HandBelote.pointsTotauxDixDeDer());
                return enchereCouleur_;
            }
            EnumList<BidBeloteSuit> b_ = new EnumList<BidBeloteSuit>();
            for(BidBelote e: rules.getEncheresAutorisees().getKeys()) {
                if(!rules.getEncheresAutorisees().getVal(e)) {
                    continue;
                }
                if (e.getCouleurDominante()) {
                    continue;
                }
                if (e == BidBelote.FOLD) {
                    continue;
                }
                BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
                enchereBeloteLoc_.setCouleur(Suit.UNDEFINED);
                enchereBeloteLoc_.setEnchere(e);
                EnumMap<Suit, HandBelote> played_ = new HandBelote().couleurs(enchereBeloteLoc_);
                EnumMap<Suit,HandBelote> repartition_=reunion_.couleurs(enchereBeloteLoc_);
                EnumMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(repartition_, played_, enchereBeloteLoc_);
                if (HandBelote.reunion(leading_).total() == reunion_.total()) {
                    b_.add(enchereBeloteLoc_);
                }
            }
            if (!b_.isEmpty()) {
                b_.first().setPoints(HandBelote.pointsTotauxDixDeDer());
                return b_.first();
            }
        }
        EnumMap<Suit,Long> couleurPointsFictifsRequis_ = new EnumMap<Suit,Long>();
        int nbPlayers_ = rules.getRepartition().getNombreJoueurs();
        int nbCartesFinales_ = rules.getRepartition().getNombreCartesParJoueur();
        EnumMap<Suit,Long> couleurPointsFictifs_ = reunion_.pointsAvg(nbPlayers_,nbCartesFinales_);
        for(Suit c: GameBeloteCommon.couleurs()) {
            long pts_ = HandBelote.pointsTotauxDixDeDer();
            HandBelote cartes_ = new HandBelote();
            for(CardBelote t: GameBeloteCommonPlaying.cartesAtouts(c)) {
                if(t.getNomFigure() == CardChar.KING) {
                    cartes_.ajouter(t);
                }
                if(t.getNomFigure() == CardChar.QUEEN) {
                    cartes_.ajouter(t);
                }
            }
            if (reunion_.contientCartes(cartes_)) {
                pts_ += DeclaresBeloteRebelote.BELOTE_REBELOTE.getPoints();
            }
            pts_ /= 2;
            couleurPointsFictifsRequis_.put(c, pts_);
        }
        EnumMap<BidBelote,Long> couleurPointsFictifsContrats_ = new EnumMap<BidBelote,Long>();
        EnumMap<BidBelote,Long> couleurPointsFictifsContratsRequis_ = new EnumMap<BidBelote,Long>();
        for(BidBelote e: rules.getEncheresAutorisees().getKeys()) {
            if(!rules.getEncheresAutorisees().getVal(e)) {
                continue;
            }
            if (e.getCouleurDominante()) {
                continue;
            }
            if (e == BidBelote.FOLD) {
                continue;
            }
            couleurPointsFictifsContrats_.put(e, reunion_.pointsBid(nbPlayers_,nbCartesFinales_,e));
            long pts_ = HandBelote.pointsTotauxDixDeDer();
            pts_ /= 2;
            couleurPointsFictifsContratsRequis_.put(e, pts_);
        }
        EnumMap<Suit,Long> couleursCandidates_ = new EnumMap<Suit,Long>();
        for(Suit c: suits_) {
            if(couleurPointsFictifs_.getVal(c) < couleurPointsFictifsRequis_.getVal(c)) {
                continue;
            }
            couleursCandidates_.put(c,couleurPointsFictifs_.getVal(c));
        }
        Suit couleurMax_ = Suit.UNDEFINED;
        long max_ = 0;
        for(Suit c: couleursCandidates_.getKeys()) {
            if(couleursCandidates_.getVal(c) > max_) {
                couleurMax_ = c;
                max_ = couleursCandidates_.getVal(c);
            }
        }
        long max2_ = 0;
        if(couleurMax_ == Suit.UNDEFINED) {
            if(couleurPointsFictifsContrats_.isEmpty()) {
                return enchereCouleur_;
            }
        } else {
            max2_ = max_;
        }
        BidBelote e_ = BidBelote.FOLD;
        int lim_ = Suit.couleursOrdinaires().size() / 2;
        for(BidBelote e: couleurPointsFictifsContrats_.getKeys()) {
            if(couleurPointsFictifsContrats_.getVal(e) < couleurPointsFictifsContratsRequis_.getVal(e)) {
                continue;
            }
            BidBeloteSuit enchereBeloteLoc_ = new BidBeloteSuit();
            enchereBeloteLoc_.setCouleur(Suit.UNDEFINED);
            enchereBeloteLoc_.setEnchere(e);
            EnumMap<Suit, HandBelote> played_ = new HandBelote().couleurs(enchereBeloteLoc_);
            EnumMap<Suit,HandBelote> repartition_=reunion_.couleurs(enchereBeloteLoc_);
            EnumMap<Suit, HandBelote> leading_ = GameBeloteCommon.cartesMaitresses(repartition_, played_, enchereBeloteLoc_);
            int ls_ = 0;
            for (HandBelote h: leading_.values()) {
                if (!h.estVide()) {
                    ls_++;
                }
            }
            if (ls_ <= lim_) {
                continue;
            }
            if(couleurPointsFictifsContrats_.getVal(e) > max2_) {
                e_ = e;
                max2_ = couleurPointsFictifsContrats_.getVal(e);
            }
        }
        if (rules.dealAll()) {
            if(couleurMax_ != Suit.UNDEFINED && max_ == max2_) {
                long pts_ = couleursCandidates_.getVal(couleurMax_);
                pts_ /= RulesBelote.DIVISIONS;
                pts_ *= RulesBelote.DIVISIONS;
                if (pts_ > Math.max(minPointsMinusOne_, bid.getPoints())) {
                    enchereCouleur_.setEnchere(enchereCouleurDominante_);
                    enchereCouleur_.setCouleur(couleurMax_);
                    enchereCouleur_.setPoints((int) pts_);
                    return enchereCouleur_;
                }
            }
            if(e_ != BidBelote.FOLD) {
                long pts_ = couleurPointsFictifsContrats_.getVal(e_);
                pts_ /= RulesBelote.DIVISIONS;
                pts_ *= RulesBelote.DIVISIONS;
                if (pts_ > Math.max(minPointsMinusOne_, bid.getPoints())) {
                    enchereCouleur_.setEnchere(e_);
                    enchereCouleur_.setPoints((int) pts_);
                    return enchereCouleur_;
                }
            }
        } else {
            if(couleurMax_ != Suit.UNDEFINED && max_ == max2_) {
                enchereCouleur_.setEnchere(enchereCouleurDominante_);
                enchereCouleur_.setCouleur(couleurMax_);
                return enchereCouleur_;
            }
            if(e_ != BidBelote.FOLD) {
                enchereCouleur_.setEnchere(e_);
                return enchereCouleur_;
            }
        }
        return enchereCouleur_;
    }
}
