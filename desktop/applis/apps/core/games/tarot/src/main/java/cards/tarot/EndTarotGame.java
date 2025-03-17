package cards.tarot;

import cards.consts.EndGameState;
import cards.consts.Role;
import cards.consts.Suit;
import cards.tarot.comparators.SortedHandfuls;
import cards.tarot.comparators.SortedMiseres;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.BonusTarot;
import cards.tarot.enumerations.CallingCard;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.EndDealTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.PlayingDog;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class EndTarotGame {

    public static final long NO_OUDLER_PTS = 56;
    public static final long ONE_OUDLER_PTS = 51;
    public static final long TWO_OUDLERS_PTS = 41;
    public static final long ALL_OUDLERS_PTS = 36;

    public static final long PTS_BASE = 25;
    private static final long FOUR_PLAYERS_WITHOUT_CALL_WIN = 1;
    private static final long FOUR_PLAYERS_WITHOUT_CALL_LOOSE = -1;
    private static final long SIX_PLAYERS_WITHOUT_CALL_THREE_WIN = 1;
    private static final long SIX_PLAYERS_WITHOUT_CALL_THREE_ZERO = 0;
    private static final long SIX_PLAYERS_WITHOUT_CALL_THREE_LOOSE = -1;
    private static final long SIX_PLAYERS_WITHOUT_CALL_WIN = 2;
    private static final long SIX_PLAYERS_WITHOUT_CALL_LOOSE = -1;
    private static final long TRHEE_PLAYERS_THREE_WIN = 1;
    private static final long TRHEE_PLAYERS_THREE_ZERO = 0;
    private static final long TRHEE_PLAYERS_THREE_LOOSE = -1;
    private static final long TRHEE_PLAYERS_WIN = 2;
    private static final long TRHEE_PLAYERS_LOOSE = -1;
    private static final long FOUR_PLAYERS_FOUR_WIN = 2;
    private static final long FOUR_PLAYERS_FOUR_SECOND = 1;
    private static final long FOUR_PLAYERS_FOUR_THIRD = -1;
    private static final long FOUR_PLAYERS_FOUR_LOOSE = -2;
    private static final long FOUR_PLAYERS_THREE_WIN = 3;
    private static final long FOUR_PLAYERS_THREE_ZERO = 1;
    private static final long FOUR_PLAYERS_THREE_LOOSE = -2;
    private static final long FOUR_PLAYERS_WIN = 6;
    private static final long FOUR_PLAYERS_LOOSE = -2;
    private static final long FIVE_PLAYERS_FIVE_WIN = 2;
    private static final long FIVE_PLAYERS_FIVE_SECOND = 1;
    private static final long FIVE_PLAYERS_FIVE_THIRD = 0;
    private static final long FIVE_PLAYERS_FIVE_FOURTH = -1;
    private static final long FIVE_PLAYERS_FIVE_LOOSE = -2;
    private static final long FIVE_PLAYERS_FOUR_WIN = 3;
    private static final long FIVE_PLAYERS_FOUR_SECOND = 1;
    private static final long FIVE_PLAYERS_FOUR_THIRD = 0;
    private static final long FIVE_PLAYERS_FOUR_LOOSE = -2;
    private static final long FIVE_PLAYERS_THREE_WIN = 6;
    private static final long FIVE_PLAYERS_THREE_ZERO = 0;
    private static final long FIVE_PLAYERS_THREE_LOOSE = -2;
    private static final long FIVE_PLAYERS_WIN = 8;
    private static final long FIVE_PLAYERS_LOOSE = -2;
    private static final long SIX_PLAYERS_SIX_WIN = 3;
    private static final long SIX_PLAYERS_SIX_SECOND = 2;
    private static final long SIX_PLAYERS_SIX_THIRD = 1;
    private static final long SIX_PLAYERS_SIX_FOURTH = -1;
    private static final long SIX_PLAYERS_SIX_FIFTH = -2;
    private static final long SIX_PLAYERS_SIX_LOOSE = -3;
    private static final long SIX_PLAYERS_FIVE_WIN = 3;
    private static final long SIX_PLAYERS_FIVE_SECOND = 2;
    private static final long SIX_PLAYERS_FIVE_THIRD = 1;
    private static final long SIX_PLAYERS_FIVE_FOURTH = 0;
    private static final long SIX_PLAYERS_FIVE_LOOSE = -3;
    private static final long SIX_PLAYERS_FOUR_WIN = 4;
    private static final long SIX_PLAYERS_FOUR_SECOND = 2;
    private static final long SIX_PLAYERS_FOUR_THIRD = 0;
    private static final long SIX_PLAYERS_FOUR_LOOSE = -2;
    private static final long SIX_PLAYERS_THREE_WIN = 8;
    private static final long SIX_PLAYERS_THREE_ZERO = 0;
    private static final long SIX_PLAYERS_THREE_LOOSE = -2;
    private static final long SIX_PLAYERS_WIN = 10;
    private static final long SIX_PLAYERS_LOOSE = -2;
    private static final long MIS_FOUR_PLAYERS_WITHOUT_CALL_WIN = 1;
    private static final long MIS_FOUR_PLAYERS_WITHOUT_CALL_LOOSE = -1;
    private static final long MIS_SIX_PLAYERS_WITHOUT_CALL_THREE_WIN = 1;
    private static final long MIS_SIX_PLAYERS_WITHOUT_CALL_THREE_ZERO = 0;
    private static final long MIS_SIX_PLAYERS_WITHOUT_CALL_THREE_LOOSE = -1;
    private static final long MIS_SIX_PLAYERS_WITHOUT_CALL_WIN = 1;
    private static final long MIS_SIX_PLAYERS_WITHOUT_CALL_LOOSE = -2;
    private static final long MIS_THREE_PLAYERS_THREE_WIN = 1;
    private static final long MIS_THREE_PLAYERS_THREE_ZERO = 0;
    private static final long MIS_THREE_PLAYERS_THREE_LOOSE = -1;
    private static final long MIS_THREE_PLAYERS_WIN = 1;
    private static final long MIS_THREE_PLAYERS_LOOSE = -2;
    private static final long MIS_FOUR_PLAYERS_FOUR_WIN = 2;
    private static final long MIS_FOUR_PLAYERS_FOUR_SECOND = 1;
    private static final long MIS_FOUR_PLAYERS_FOUR_THIRD = -1;
    private static final long MIS_FOUR_PLAYERS_FOUR_LOOSE = -2;
    private static final long MIS_FOUR_PLAYERS_THREE_WIN = 2;
    private static final long MIS_FOUR_PLAYERS_THREE_ZERO = -1;
    private static final long MIS_FOUR_PLAYERS_THREE_LOOSE = -3;
    private static final long MIS_FOUR_PLAYERS_LOOSE = 2;
    private static final long MIS_FOUR_PLAYERS_WIN = -6;
    private static final long MIS_FIVE_PLAYERS_FIVE_WIN = 2;
    private static final long MIS_FIVE_PLAYERS_FIVE_SECOND = 1;
    private static final long MIS_FIVE_PLAYERS_FIVE_THIRD = 0;
    private static final long MIS_FIVE_PLAYERS_FIVE_FOURTH = -1;
    private static final long MIS_FIVE_PLAYERS_FIVE_LOOSE = -2;
    private static final long MIS_FIVE_PLAYERS_FOUR_WIN = 2;
    private static final long MIS_FIVE_PLAYERS_FOUR_SECOND = 0;
    private static final long MIS_FIVE_PLAYERS_FOUR_THIRD = -1;
    private static final long MIS_FIVE_PLAYERS_FOUR_LOOSE = -3;
    private static final long MIS_FIVE_PLAYERS_THREE_WIN = 2;
    private static final long MIS_FIVE_PLAYERS_THREE_ZERO = 0;
    private static final long MIS_FIVE_PLAYERS_THREE_LOOSE = -6;
    private static final long MIS_FIVE_PLAYERS_WIN = 2;
    private static final long MIS_FIVE_PLAYERS_LOOSE = -8;
    private static final long MIS_SIX_PLAYERS_SIX_WIN = 3;
    private static final long MIS_SIX_PLAYERS_SIX_SECOND = 2;
    private static final long MIS_SIX_PLAYERS_SIX_THIRD = 1;
    private static final long MIS_SIX_PLAYERS_SIX_FOURTH = -1;
    private static final long MIS_SIX_PLAYERS_SIX_FIFTH = -2;
    private static final long MIS_SIX_PLAYERS_SIX_LOOSE = -3;
    private static final long MIS_SIX_PLAYERS_FIVE_WIN = 3;
    private static final long MIS_SIX_PLAYERS_FIVE_SECOND = 0;
    private static final long MIS_SIX_PLAYERS_FIVE_THIRD = -1;
    private static final long MIS_SIX_PLAYERS_FIVE_FOURTH = -2;
    private static final long MIS_SIX_PLAYERS_FIVE_LOOSE = -3;
    private static final long MIS_SIX_PLAYERS_FOUR_WIN = 2;
    private static final long MIS_SIX_PLAYERS_FOUR_SECOND = 0;
    private static final long MIS_SIX_PLAYERS_FOUR_THIRD = -2;
    private static final long MIS_SIX_PLAYERS_FOUR_LOOSE = -4;
    private static final long MIS_SIX_PLAYERS_THREE_WIN = 2;
    private static final long MIS_SIX_PLAYERS_THREE_ZERO = 0;
    private static final long MIS_SIX_PLAYERS_THREE_LOOSE = -8;
    private static final long MIS_SIX_PLAYERS_WIN = 2;
    private static final long MIS_SIX_PLAYERS_LOOSE = -10;
    private final GameTarotTeamsRelation relations;
    private final CustList<TrickTarot> tricks;
    /** Ce sont les poignees annoncees par le(s) joueur(s) */
    private final CustList<IdList<Handfuls>> declaresHandfuls;
    /** Ce sont les miseres annoncees par le(s) joueur(s) */
    private final CustList<IdList<Miseres>> declaresMiseres;
    /** Ce sont les primes annoncees par le(s) joueur(s) */
//    private final CustList<BoolVal> declaresSlam;
    /** Ce sont les petits au bout par le(s) joueur(s) */
    private final CustList<BoolVal> smallBound;
    private final CustList<HandTarot> wonPlayersTeam = new CustList<HandTarot>();
    private final Ints firstTrick = new Ints();
    private final Longs oulderPoints = new Longs();
    private final long nombrePointsChien;
    private boolean slamTaker;
    private boolean slamDefense;

    public EndTarotGame(GameTarotTeamsRelation _relations, CustList<TrickTarot> _tricks,
                        CustList<IdList<Handfuls>> _declaresHandfuls,
                        CustList<IdList<Miseres>> _declaresMiseres,
                        CustList<BoolVal> _smallBound) {
        relations = _relations;
        tricks = _tricks;
        declaresHandfuls = _declaresHandfuls;
        declaresMiseres = _declaresMiseres;
//        declaresSlam = _declaresSlam;
        smallBound = _smallBound;
        oulderPoints.add(NO_OUDLER_PTS);
        oulderPoints.add(ONE_OUDLER_PTS);
        oulderPoints.add(TWO_OUDLERS_PTS);
        oulderPoints.add(ALL_OUDLERS_PTS);
        long nombrePointsChien_ = 0;
        for (TrickTarot t: tricks.left(1)) {
//            if (t.getVuParToutJoueur()) {
//                continue;
//            }
            for (CardTarot c: t) {
                nombrePointsChien_ += c.points();
            }
        }
        nombrePointsChien = nombrePointsChien_;
    }

    public void setupPlayersWonTricks() {
        int nbPlayers_ = relations.getNombreDeJoueurs();
        for (int i = 0; i < nbPlayers_; i++) {
            firstTrick.add(indexOfFirstTrick(tricks.mid(1),i,relations));
            wonPlayersTeam.add(getWonTricksTeam(i));
        }
    }
    public void setupSlams() {
        slamDefense = getWonTricksListTeam(relations.getTaker()).isEmpty();
        int taker_ = relations.getTaker();
        Ints defs_ = relations.adversaires(taker_,GameTarotTeamsRelation.tousJoueurs(relations.getNombreDeJoueurs()));
        slamTaker = getWonTricksListTeam(tricks,defs_).isEmpty();
    }
    public long scorePreneurPlisDouble(BidTarot _bid) {
        long nbPointsAtt_ = 0;
        for (CardTarot c: getWonCardsTaker(_bid)) {
            nbPointsAtt_ += c.points();
        }
        return nbPointsAtt_;
    }

    public int nombreBoutsPreneur(BidTarot _bid) {
        int nombreBouts_ = 0;
        for (CardTarot c: getWonCardsTaker(_bid)) {
            if (c.estUnBout()) {
                nombreBouts_++;
            }
        }
        return nombreBouts_;
    }

    HandTarot getWonCardsTaker(BidTarot _bid) {
        return getWonCardsPlayer(relations.getTaker(),_bid);
    }

    HandTarot getWonCardsPlayer(int _player,BidTarot _bid) {
        HandTarot nbPointsAtt_ = new HandTarot();
        boolean chelemAttaque_ = aucunPliAdverseFin(_player, tricks);
        boolean chelemDefense_ = !chelemAttaque_ && slamTeam();
        boolean excuseEcartee_ = tricks.first().contient(CardTarot.excuse());
        CustList<TrickTarot> tricks_ = tricks(_player, _bid);
        for (TrickTarot pli_ : tricks_) {
            for (CardTarot carte_ : pli_) {
                nbPointsAtt_.ajouter(carte_);
            }
        }
        if(excuseEcartee_) {
            return nbPointsAtt_;
        }
        boolean excuseDansPlisAttaque_ = excuseDansPlisAttaque(tricks_);
        CustList<TrickTarot> otherTricks_ = getOtherTricksTarot(tricks_, tricks);
        CustList<TrickTarot> excuseTrick_;
        if(chelemAttaque_) {
            excuseTrick_ = getExcuseTrick(tricks_);
        } else if(chelemDefense_) {
            excuseTrick_ = getExcuseTrick(otherTricks_);
        } else {
            if(excuseDansPlisAttaque_) {
                excuseTrick_ = getExcuseTrick(tricks_);
            } else {
                excuseTrick_ = getExcuseTrick(otherTricks_);
            }
        }
        for (TrickTarot pli_ : excuseTrick_) {
            int joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
            nbPointsAtt_.removeCardIfPresent(CardTarot.EXCUSE);
            if(relations.memeEquipe(_player, joueurExcuse_)) {
                nbPointsAtt_.ajouter(CardTarot.EXCUSE);
            }
        }
        return nbPointsAtt_;
    }

    private CustList<TrickTarot> tricks(int _player, BidTarot _bid) {
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        if (_bid.getJeuChien() != PlayingDog.AGAINST) {
            tricks_.add(tricks.first());
        }
        for (TrickTarot t: getWonTricksListTeam(_player)) {
            tricks_.add(t);
        }
        return tricks_;
    }

    private static boolean excuseDansPlisAttaque(CustList<TrickTarot> _tricks) {
        boolean excuseDansPlisAttaque_ = false;
        for (TrickTarot pli_ : _tricks) {
            if (pli_.contient(CardTarot.excuse())) {
                excuseDansPlisAttaque_ = true;
            }
        }
        return excuseDansPlisAttaque_;
    }

    private static CustList<TrickTarot> getOtherTricksTarot(CustList<TrickTarot> _tricksTeam, CustList<TrickTarot> _tricks) {
        CustList<TrickTarot> otherTricks_ = new CustList<TrickTarot>();
        for (TrickTarot t: _tricks) {
            CardTarot cardTarot_ = t.premiereCarte();
            boolean other_ = true;
            for (TrickTarot pli_ : _tricksTeam) {
                if (cardTarot_ == pli_.premiereCarte()) {
                    other_ = false;
                }
            }
            if (other_) {
                otherTricks_.add(t);
            }
        }
        return otherTricks_;
    }

    private static CustList<TrickTarot> getExcuseTrick(CustList<TrickTarot> _trs) {
        CustList<TrickTarot> t_ = new CustList<TrickTarot>();
        if (_trs.last().contient(CardTarot.EXCUSE)) {
            return t_;
        }
        for (TrickTarot pli_ : _trs) {
//            if (!pli_.getVuParToutJoueur()) {
//                continue;
//            }
            if (pli_.contient(CardTarot.EXCUSE)) {
                t_.add(pli_);
            }
        }
        return t_;
    }
    public long scorePreneurPlis(long _scorePreneurPlisDouble,
                                 long _scoreNecessairePreneur) {
        RulesTarot rules_ = relations.getRules();
        return scorePreneurPlis(_scorePreneurPlisDouble, _scoreNecessairePreneur, rules_);
    }

    static long scorePreneurPlis(long _scorePreneurPlisDouble, long _scoreNecessairePreneur, RulesTarot _rules) {
        long scorePreneurPlis_ = _scorePreneurPlisDouble / 2;
        if (scorePreneurPlis_ >= _scoreNecessairePreneur) {
            if (_scorePreneurPlisDouble % 2 == 1) {
                scorePreneurPlis_++;
            }
        } else if (scorePreneurPlis_ + 1 == _scoreNecessairePreneur && _rules.getEndDealTarot() == EndDealTarot.ATTACK_WIN && _scorePreneurPlisDouble % 2 == 1) {
            scorePreneurPlis_++;
        }
        return scorePreneurPlis_;
    }

    public long scoreNecessairePreneur(BidTarot _bid) {
        int nombreBouts_ = nombreBoutsPreneur(_bid);
        return oulderPoints.get(nombreBouts_);
    }
    public long base(long _scorePreneurPlisDouble,
                     long _differenceScorePreneur) {
        RulesTarot rules_ = relations.getRules();
        return base(_scorePreneurPlisDouble, _differenceScorePreneur, rules_);
    }

    static long base(long _scorePreneurPlisDouble, long _differenceScorePreneur, RulesTarot _rules) {
        if (_differenceScorePreneur >= 0) {
            return PTS_BASE;
        }
        if (_differenceScorePreneur == -1
                && _scorePreneurPlisDouble % 2 == 1) {
            if (_rules.getEndDealTarot() == EndDealTarot.ATTACK_LOOSE) {
                return -PTS_BASE;
            }
            if (_rules.getEndDealTarot() == EndDealTarot.ZERO) {
                return 0;
            }
            return PTS_BASE;
        }
        return -PTS_BASE;
    }
    public long scorePreneurSansAnnonces(long _differenceScorePreneur,
                                          long _base) {
        Ints called_ = relations.getCalledPlayers();
        int nombreJoueurs_ = relations.getNombreDeJoueurs();
        int taker_ = relations.getTaker();
        return scorePreneurSansAnnonces(_differenceScorePreneur, _base, nombreJoueurs_, taker_, called_, smallBound);
    }

    static long scorePreneurSansAnnonces(long _differenceScorePreneur, long _base, int _nombreJoueurs, int _taker, Ints _called, CustList<BoolVal> _smallBound) {
        long scorePreneurSansAnnonces_ = 0;
        if (_base == 0) {
            return scorePreneurSansAnnonces_;
        }
        scorePreneurSansAnnonces_ = _base + _differenceScorePreneur;
        if (_smallBound.get(_taker) == BoolVal.TRUE) {
            scorePreneurSansAnnonces_ += BonusTarot.SMALL_BOUND
                    .getPoints();
        }
        boolean appelePetitAuBout_ = false;
        for (int a: _called) {
            if (_smallBound.get(a) == BoolVal.TRUE) {
                appelePetitAuBout_ = true;
            }
        }
        if(appelePetitAuBout_) {
            scorePreneurSansAnnonces_ += BonusTarot.SMALL_BOUND
                    .getPoints();
        }
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (joueur_ != _taker && !_called.containsObj(joueur_)
                    && _smallBound.get(joueur_) == BoolVal.TRUE) {
                scorePreneurSansAnnonces_ -= BonusTarot.SMALL_BOUND
                        .getPoints();
            }
        }
        return scorePreneurSansAnnonces_;
    }

    public Longs calculateScores(IdMap<Role,Rate> _coefficientsRepartition,
                                 long _sommeTemporaire, long _scorePreneurSansAnnonces) {
        return calculateScores(_coefficientsRepartition, _sommeTemporaire, _scorePreneurSansAnnonces, relations);
    }

    static Longs calculateScores(AbsMap<Role, Rate> _coefficientsRepartition, long _sommeTemporaire, long _scorePreneurSansAnnonces, GameTarotTeamsRelation _relations) {
        Longs scores_ = new Longs();
        int nombreJoueurs_ = _relations.getNombreDeJoueurs();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            scores_.add(0L);
        }
        if (_sommeTemporaire == 0) {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                scores_.set( joueur_, 0L);
            }
        } else {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                long ll_ = calculateScoresInd(_coefficientsRepartition, _sommeTemporaire, _scorePreneurSansAnnonces, _relations, joueur_);
                scores_.set(joueur_,
                        ll_);
            }
        }
        return scores_;
    }

    private static long calculateScoresInd(AbsMap<Role, Rate> _coefficientsRepartition, long _sommeTemporaire, long _scorePreneurSansAnnonces, GameTarotTeamsRelation _relations, int _joueur) {
        Role st_ = _relations.statutDe(_joueur);
        Rate rate_ = _coefficientsRepartition.getVal(st_);
        long ll_;
        if (st_ == Role.DEFENDER) {
            ll_ = Rate.multiply(rate_, new Rate(_sommeTemporaire)).ll();
            return ll_;
        }
        Rate mult_ = Rate.multiply(new Rate(rate_.getNumeratorCopy()), new Rate(_sommeTemporaire));
        if (st_ == Role.CALLED_PLAYER) {
            if (!LgInt.remain(mult_.getNumeratorCopy(),rate_.getDenominatorCopy()).isZero()) {
                if (_scorePreneurSansAnnonces > 0) {
                    mult_.removeNb(Rate.one());
                } else {
                    mult_.addNb(Rate.one());
                }
            }
        } else {
            if (!LgInt.remain(mult_.getNumeratorCopy(),rate_.getDenominatorCopy()).isZero()) {
                if (_scorePreneurSansAnnonces > 0) {
                    mult_.addNb(Rate.one());
                } else {
                    mult_.removeNb(Rate.one());
                }
            }
        }
        mult_.divideBy(new Rate(rate_.getDenominatorCopy()));
        ll_ = mult_.ll();
        return ll_;
    }

    String scoreSmallBound(){
        CustList<Role> st_ = new CustList<Role>();
        int nombreJoueurs_ = relations.getNombreDeJoueurs();
        for (int p= 0; p < nombreJoueurs_; p++) {
            st_.add(relations.statutDe(p));
        }
        return scoreSmallBound(nombreJoueurs_,smallBound,st_);
    }
    static String scoreSmallBound(int _nombreJoueurs, CustList<BoolVal> _smallBound, CustList<Role> _status) {
        int p_ = joueurPetitAuBout(_nombreJoueurs, _smallBound);
        if (p_ < 0) {
            return "0";
        }
        if (_status.get(p_) == Role.DEFENDER) {
            return StringUtil.concat("(-",Long.toString(BonusTarot.SMALL_BOUND.getPoints()),")");
        }
        return Long.toString(BonusTarot.SMALL_BOUND.getPoints());
    }

    String joueurPetitAuBout(StringList _nicknames) {
        int nombreJoueurs_ = relations.getNombreDeJoueurs();
        return joueurPetitAuBout(nombreJoueurs_,smallBound,_nicknames);
    }
    static String joueurPetitAuBout(int _nombreJoueurs, CustList<BoolVal> _smallBound, StringList _nicknames) {
        int p_ = joueurPetitAuBout(_nombreJoueurs, _smallBound);
        if (p_ < 0) {
            return "";
        }
        return _nicknames.get(p_);
    }
    static int joueurPetitAuBout(int _nombreJoueurs, CustList<BoolVal> _smallBound) {
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (_smallBound.get(joueur_) == BoolVal.TRUE) {
                return joueur_;
            }
        }
        return -1;
    }

    public CustList<SortedMiseres> getMiseresPointsForTaker() {

        CustList<SortedMiseres> scores1_ = new CustList<SortedMiseres>();
        int nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreDeJoueurs_; joueur_++) {
            SortedMiseres miseresPlayer_ = new SortedMiseres();
            scores1_.add(miseresPlayer_);
            if (relations.aPourDefenseur(joueur_)) {
                feedMiseres(miseresPlayer_,joueur_,-1);
            } else {
                feedMiseres(miseresPlayer_,joueur_,1);
            }
        }
        return scores1_;

    }
    private void feedMiseres(SortedMiseres _miseres,int _player, int _rate) {
        feedMiseres(_miseres, _player, _rate, declaresMiseres);
    }

    static void feedMiseres(SortedMiseres _miseres, int _player, long _rate, CustList<IdList<Miseres>> _declaresMiseres) {
        for (Miseres m : _declaresMiseres.get(_player)) {
            _miseres.put(m,_rate*m.getPoints());
        }
    }

    public CustList<SortedHandfuls> getHandfulsPointsForTaker(long _pointsTakerWithoutDeclaring) {

        int nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        return getHandfulsPointsForTaker(_pointsTakerWithoutDeclaring, nombreDeJoueurs_, declaresHandfuls);

    }

    static CustList<SortedHandfuls> getHandfulsPointsForTaker(long _pointsTakerWithoutDeclaring, int _nombreDeJoueurs, CustList<IdList<Handfuls>> _declaresHandfuls) {
        CustList<SortedHandfuls> scores1_ = new CustList<SortedHandfuls>();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreDeJoueurs; joueur_++) {
            scores1_.add(new SortedHandfuls());
            for (Handfuls poignee_ : _declaresHandfuls.get(joueur_)) {
                if (_pointsTakerWithoutDeclaring >= 0) {
                    scores1_.last().put(poignee_,
                            poignee_.getPoints());
                } else {
                    scores1_.last().put(poignee_,
                            -poignee_.getPoints());
                }
            }
        }
        return scores1_;
    }

    public long additionnalBonusesAttack(BidTarot _bid) {
        return additionnalBonusesAttack(_bid, slamTaker);
    }

    static long additionnalBonusesAttack(BidTarot _bid, boolean _slamTaker) {
        long primesSupplementaires_ =0;
        if (_bid.isFaireTousPlis()) {
            if (_slamTaker) {
                primesSupplementaires_ = BonusTarot.SLAM
                        .getPoints();
            } else {
                primesSupplementaires_ = -BonusTarot.SLAM
                        .getPoints() / 2;
            }
            return primesSupplementaires_;
        }
        if (_slamTaker) {
            primesSupplementaires_ = BonusTarot.SLAM
                    .getPoints() / 2;
        }
        return primesSupplementaires_;
    }

    public long additionnalBonusesDefense() {
        return additionnalBonusesDefense(slamDefense);
    }

    static long additionnalBonusesDefense(boolean _slamDefense) {
        long primesSupplementaires_ = 0;
        if (_slamDefense) {
            primesSupplementaires_ = BonusTarot.SLAM
                    .getPoints() / 2;
        }
        return primesSupplementaires_;
    }

    static long temporarySum(BidTarot _bid, long _scorePreneurSansAnnonces,
                                     CustList<SortedMiseres> _miseres,
                                     CustList<SortedHandfuls> _handfuls, long _primesSupplementairesAttack,
                             long _primesSupplementairesDefense) {
        long sommeTemporaire_ = 0;
        for (SortedMiseres m: _miseres) {
            sommeTemporaire_ += sum(m.values());
        }
        for (SortedHandfuls h: _handfuls) {
            sommeTemporaire_ += sum(h.values());
        }
        sommeTemporaire_ += _primesSupplementairesAttack
                - _primesSupplementairesDefense;
        if (_scorePreneurSansAnnonces != 0) {
            return _bid.getCoefficient() * _scorePreneurSansAnnonces + sommeTemporaire_;
        }
        return 0;
    }

    public IdMap<Role,Rate> coefficientsRepartition() {
        return coefficientsRepartition(relations);
    }

    static IdMap<Role, Rate> coefficientsRepartition(GameTarotTeamsRelation _relations) {
        IdMap<Role,Rate> coefficientsRepartition_;
        int nombreJoueurs_ = _relations.getNombreDeJoueurs();
        coefficientsRepartition_ = new IdMap<Role,Rate>();
        if (_relations.coequipiers(_relations.getTaker(),GameTarotTeamsRelation.tousJoueurs(nombreJoueurs_)).isEmpty()) {
            coefficientsRepartition_.put(Role.TAKER,new Rate(nombreJoueurs_ - 1L));
            coefficientsRepartition_.put(Role.DEFENDER,new Rate(-1));
        } else {
            if (nombreJoueurs_ == 4) {
                if (_relations.getRules().getDealing().getAppel() == CallingCard.DEFINED) {
                    coefficientsRepartition_.put(Role.TAKER,new Rate(1));
                    coefficientsRepartition_.put(Role.CALLED_PLAYER,new Rate(1));
                    coefficientsRepartition_.put(Role.DEFENDER,new Rate(-1));
                } else {
                    coefficientsRepartition_.put(Role.TAKER,new Rate(3,2));
                    coefficientsRepartition_.put(Role.CALLED_PLAYER,new Rate(1,2));
                    coefficientsRepartition_.put(Role.DEFENDER,new Rate(-1));
                }
            } else if (nombreJoueurs_ == 5) {
                coefficientsRepartition_.put(Role.TAKER,new Rate(2));
                coefficientsRepartition_.put(Role.CALLED_PLAYER,new Rate(1));
                coefficientsRepartition_.put(Role.DEFENDER,new Rate(-1));
            } else {
                if (_relations.getRules().getDealing().getAppel() == CallingCard.DEFINED) {
                    coefficientsRepartition_.put(Role.TAKER,new Rate(2));
                    coefficientsRepartition_.put(Role.CALLED_PLAYER,new Rate(2));
                    coefficientsRepartition_.put(Role.DEFENDER,new Rate(-1));
                } else {
                    coefficientsRepartition_.put(Role.TAKER,new Rate(3));
                    coefficientsRepartition_.put(Role.CALLED_PLAYER,new Rate(1));
                    coefficientsRepartition_.put(Role.DEFENDER,new Rate(-1));
                }
            }
        }
        return coefficientsRepartition_;
    }


    public EndGameState getUserState(long _scorePreneurSansAnnonces, int _user) {
        boolean def_ = relations.aPourDefenseur(_user);
        return getUserState(_scorePreneurSansAnnonces, def_);
    }

    static EndGameState getUserState(long _scorePreneurSansAnnonces, boolean _def) {
        if (!_def) {
            if (_scorePreneurSansAnnonces > 0) {
                return EndGameState.WIN;
            }
            if (_scorePreneurSansAnnonces == 0) {
                return EndGameState.EQUALLITY;
            }
            return EndGameState.LOOSE;
        }
        if (_scorePreneurSansAnnonces < 0) {
            return EndGameState.WIN;
        }
        if (_scorePreneurSansAnnonces == 0) {
            return EndGameState.EQUALLITY;
        }
        return EndGameState.LOOSE;
    }

    public long scoreJoueurPlisDouble(int _joueur) {
        long nbPointsAtt_ = IndexConstants.SIZE_EMPTY;
        for (CardTarot c: getWonCardsPlayer(_joueur,BidTarot.GUARD_AGAINST)) {
            nbPointsAtt_ += c.points();
        }
        return nbPointsAtt_;
    }
    boolean slamTeam() {
        CustList<Ints> teams_ = relations.teams();
        int noTrick_ = 0;
        for (Ints t: teams_) {
            if (getWonTricksListTeam(tricks,t).isEmpty()) {
                noTrick_++;
            }
        }
        return noTrick_ == teams_.size() -1;
    }

    public long scoreNecessaireJoueur(int _joueur) {


        int nombreBouts_ = nombreDeBoutsJoueur(_joueur);
        return oulderPoints.get(nombreBouts_);
    }

    public int nombreDeBoutsJoueur(int _joueur) {
        int nombreBouts_ = 0;
        for (CardTarot c: getWonCardsPlayer(_joueur,BidTarot.GUARD_AGAINST)) {
            if (c.estUnBout()) {
                nombreBouts_++;
            }
        }
        return nombreBouts_;
    }

    public static long differenceJoueurDouble(long _scoreNecessaireJoueur,
                                              long _scoreJoueurPlisDouble) {
        return _scoreJoueurPlisDouble - 2 * _scoreNecessaireJoueur;
    }

    public static long differenceJoueurDoubleMisere(
            long _scoreNecessaireJoueur, long _scoreJoueurPlisDouble) {
        return 2 * _scoreNecessaireJoueur - _scoreJoueurPlisDouble;
    }

    public static Ints positionsDifference(Longs _differences) {
        Ints positions_ = new Ints();
        int nbDiff_ = _differences.size();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbDiff_; joueur_++) {
            positions_.add(1);
            for (long difference_ : _differences) {
                if (difference_ > _differences.get(joueur_)) {
                    positions_.set(joueur_, positions_.get(joueur_)+1);
                }
            }
        }
        return positions_;
    }

    /**
     On classe les joueurs selon certains criteres pour les departager en
     changeant le tableau des positions
     */
    public Ints changePositionsOne(Ints _positions, boolean _pasJeuMisere) {
        return changePositionsOne(wonPlayersTeam, _positions, _pasJeuMisere);
    }

    static Ints changePositionsOne(CustList<HandTarot> _wonPlayersTeam, Ints _positions, boolean _pasJeuMisere) {
        if (_pasJeuMisere) {
            return changePositionsOneRate(_wonPlayersTeam,_positions,1);
        } else {
            return changePositionsOneRate(_wonPlayersTeam,_positions,-1);
        }
    }
    private static Ints changePositionsOneRate(CustList<HandTarot> _wonPlayersTeam, Ints _positions, int _pasJeuMisere) {
        Ints positions_ = new Ints(_positions);
        CustList<Ints> groupes_ = buildGroups(positions_);
        for (Ints groupe_ : groupes_) {
            int groupeLen_ = groupe_.size();
            for (int i = 0; i < groupeLen_; i++) {
                int joueur_ = groupe_.get(i);
                int positionTemporaire_ = positionTemporaireOne(_wonPlayersTeam, _pasJeuMisere, groupe_, joueur_);
                positions_.set(joueur_, positions_.get(joueur_) + positionTemporaire_ - 1);
            }
        }
        return positions_;
    }

    private static int positionTemporaireOne(CustList<HandTarot> _wonPlayersTeam, int _pasJeuMisere, Ints _groupe, int _joueur) {
        int groupeLen_ = _groupe.size();
        int positionTemporaire_ = 1;
        HandTarot main_ = _wonPlayersTeam.get(_joueur);
        int nombreBouts_ = main_.nombreDeBouts();
        for (int j = 0; j < groupeLen_; j++) {
            int joueur2_ = _groupe.get(j);
            HandTarot main2_ = _wonPlayersTeam.get(joueur2_);
            int nombreBouts2_ = main2_.nombreDeBouts();
            if (_pasJeuMisere *nombreBouts2_ > _pasJeuMisere *nombreBouts_) {
                positionTemporaire_++;
            }
        }
        return positionTemporaire_;
    }

    private static CustList<Ints> getGroups(CustList<Ints> _groupes) {
        CustList<Ints> ensemblesPluriels_ = new CustList<Ints>();
        for (Ints g: _groupes) {
            if (g.size() < 2) {
                continue;
            }
            ensemblesPluriels_.add(g);
        }
        return ensemblesPluriels_;
    }

    /**
     On classe les joueurs selon certains criteres pour les departager en
     changeant le tableau des positions
     */
    public Ints changePositionsTwo(Ints _positions, boolean _pasJeuMisere) {
        return changePositionsTwo(wonPlayersTeam, _positions, _pasJeuMisere);
    }

    static Ints changePositionsTwo(CustList<HandTarot> _wonPlayersTeam, Ints _positions, boolean _pasJeuMisere) {
        if (_pasJeuMisere) {
            return changePositionsTwoRate(_wonPlayersTeam,_positions,1,CardTarot.vingtEtUn(),CardTarot.petit());
        } else {
            return changePositionsTwoRate(_wonPlayersTeam,_positions,-1,CardTarot.petit(),CardTarot.vingtEtUn());
        }
    }

    static Ints changePositionsTwoRate(CustList<HandTarot> _wonPlayersTeam, Ints _positions, int _pasJeuMisere, CardTarot _greatest, CardTarot _lowest) {
        Ints positions_ = new Ints(_positions);
        CustList<Ints> groupes_ = buildGroups(positions_);
        for (Ints groupe_ : groupes_) {
            int groupeLen_ = groupe_.size();
            for (int i = 0; i < groupeLen_; i++) {
                int joueur_ = groupe_.get(i);
                int positionTemporaire_ = positionTemporaireTwo(_wonPlayersTeam, _pasJeuMisere, _greatest, _lowest, groupe_, joueur_);
                positions_.set(joueur_, positions_.get(joueur_) + positionTemporaire_ - 1);
            }
        }
        return positions_;
    }

    private static int positionTemporaireTwo(CustList<HandTarot> _wonPlayersTeam, int _pasJeuMisere, CardTarot _greatest, CardTarot _lowest, Ints _groupe, int _joueur) {
        HandTarot main_ = _wonPlayersTeam.get(_joueur);
        int nombreBouts_ = main_.nombreDeBouts();
        if (nombreBouts_ == 0) {
            return positionTemporaireTwoNoOudler(_wonPlayersTeam, _pasJeuMisere, _groupe, main_);
        }
        int groupeLen_ = _groupe.size();
        int positionTemporaire_ = 1;
        HandTarot main2_;
        CardTarot bout_ = main_.bouts().premiereCarte();
        CardTarot bout2_;
        if (CardTarot.eq(bout_,CardTarot.excuse())) {
            for (int j = 0; j < groupeLen_; j++) {
                int joueur2_ = _groupe.get(j);
                main2_ = _wonPlayersTeam.get(joueur2_);
                bout2_ = main2_.bouts().premiereCarte();
                if (CardTarot.eq(bout2_, _greatest)) {
                    positionTemporaire_++;
                }
            }
            return positionTemporaire_;
        }
        if (!CardTarot.eq(bout_, _lowest)) {
            return positionTemporaire_;
        }
        for (int j = 0; j < groupeLen_; j++) {
            int joueur2_ = _groupe.get(j);
            main2_ = _wonPlayersTeam.get(joueur2_);
            bout2_ = main2_.bouts().premiereCarte();
            if (CardTarot.eq(bout2_,CardTarot.excuse())
                    || CardTarot.eq(bout2_, _greatest)) {
                positionTemporaire_++;
            }
        }
        return positionTemporaire_;
    }

    private static int positionTemporaireTwoNoOudler(CustList<HandTarot> _wonPlayersTeam, int _pasJeuMisere, Ints _groupe, HandTarot _main) {
        int groupeLen_ = _groupe.size();
        int nombreFigures_ = _main.nombreDeFigures();
        int positionTemporaire_ = 1;
        for (int j = 0; j < groupeLen_; j++) {
            int joueur2_ = _groupe.get(j);
            HandTarot main2_ = _wonPlayersTeam.get(joueur2_);
            int nombreFigures2_ = main2_
                    .nombreDeFigures();
            if (_pasJeuMisere *nombreFigures2_ > _pasJeuMisere *nombreFigures_) {
                positionTemporaire_++;
            }
        }
        return positionTemporaire_;
    }

    /**
     On classe les joueurs selon certains criteres pour les departager en
     changeant le tableau des positions
     */
    public Ints changePositionsThree(Ints _positions, boolean _pasJeuMisere) {
        return changePositionsThree(_positions, _pasJeuMisere, wonPlayersTeam);
    }

    static Ints changePositionsThree(Ints _positions, boolean _pasJeuMisere, CustList<HandTarot> _wonPlayersTeam) {

        if (_pasJeuMisere) {
            return changePositionsThreeRate(_positions, _wonPlayersTeam, 1);
        } else {
            return changePositionsThreeRate(_positions, _wonPlayersTeam, -1);
        }
    }

    private static Ints changePositionsThreeRate(Ints _positions, CustList<HandTarot> _wonPlayersTeam, int _rate) {
        Ints positions_ = new Ints(_positions);
        CustList<Ints> groupes_ = buildGroups(positions_);
        for (Ints groupe_ : groupes_) {
            int groupeLen_ = groupe_.size();
            for (int i = 0; i < groupeLen_; i++) {
                int joueur_ = groupe_.get(i);
                int positionTemporaire_ = positionTemporaireThree(_wonPlayersTeam, _rate, groupe_, joueur_);
                positions_.set(joueur_, positions_.get(joueur_) + positionTemporaire_ - 1);
            }
        }
        return positions_;
    }

    private static int positionTemporaireThree(CustList<HandTarot> _wonPlayersTeam, int _rate, Ints _groupe, int _joueur) {
        int groupeLen_ = _groupe.size();
        int positionTemporaire_ = 1;
        HandTarot main_ = _wonPlayersTeam.get(_joueur);
        HandTarot figures_ = new HandTarot();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            figures_.ajouterCartes(main_.charCardsBySuit(couleur_));
        }
        figures_.sortCharsByGreaterPoints();
        for (int j = 0; j < groupeLen_; j++) {
            int joueur2_ = _groupe.get(j);
            HandTarot main2_ = _wonPlayersTeam.get(joueur2_);
            HandTarot figures2_ = new HandTarot();
            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                figures2_.ajouterCartes(main2_
                        .charCardsBySuit(couleur_));
            }
            figures2_.sortCharsByGreaterPoints();
            positionTemporaire_ = incrementPosByPoints(
                    figures_, figures2_, positionTemporaire_, _rate);
        }
        return positionTemporaire_;
    }

    private static int incrementPosByPoints(HandTarot _charactersOne,
                                                    HandTarot _charactersTwo, int _positionTmp, int _rate) {
        int positionTmp_ = _positionTmp;
        int nbCharacters_ = _charactersOne.total();
        for (int indiceFigure_ = IndexConstants.FIRST_INDEX; indiceFigure_ < nbCharacters_; indiceFigure_++) {
            if (_rate*_charactersTwo.carte(indiceFigure_).points() > _rate*_charactersOne
                    .carte(indiceFigure_).points()) {
                positionTmp_++;
                return positionTmp_;
            } else if (_rate*_charactersTwo.carte(indiceFigure_)
                    .points() < _rate*_charactersOne.carte(
                    indiceFigure_).points()) {
                return positionTmp_;
            }
        }
        return positionTmp_;
    }

    /**
     On classe les joueurs selon certains criteres pour les departager en
     changeant le tableau des positions
     */
    public Ints changePositionsFour(Ints _positions, boolean _pasJeuMisere) {
        return changePositionsFour(_positions, _pasJeuMisere, firstTrick);
    }

    static Ints changePositionsFour(Ints _positions, boolean _pasJeuMisere, Ints _tricks) {
        if (_pasJeuMisere) {
            return changePositionsFourRate(_positions,1,_tricks);
        } else {
            return changePositionsFourRate(_positions,-1,_tricks);
        }
    }
    private static Ints changePositionsFourRate(Ints _positions, int _pasJeuMisere, Ints _tricks) {
        Ints positions_ = new Ints(_positions);
        CustList<Ints> groupes_ = buildGroups(positions_);
        for (Ints groupe_ : groupes_) {
            int groupeLen_ = groupe_.size();
            for (int i = 0; i < groupeLen_; i++) {
                int joueur_ = groupe_.get(i);
                int positionTemporaire_ = positionTemporaireFour(_pasJeuMisere, _tricks, groupe_, joueur_);
                positions_.set(joueur_, positions_.get(joueur_) + positionTemporaire_ - 1);
            }
        }
        return positions_;
    }

    private static int positionTemporaireFour(int _pasJeuMisere, Ints _tricks, Ints _groupe, int _joueur) {
        int groupeLen_ = _groupe.size();
        int positionTemporaire_ = 1;
        for (int j = 0; j < groupeLen_; j++) {
            int joueur2_ = _groupe.get(j);
            int indexOne_ = _tricks.get(_joueur);
            int indexTwo_ = _tricks.get(joueur2_);
            if (_pasJeuMisere *indexTwo_ <
                    _pasJeuMisere *indexOne_) {
                positionTemporaire_++;
            }
        }
        return positionTemporaire_;
    }

    static CustList<Ints> buildGroups(Ints _positions) {
        int indice_;
        CustList<Ints> groupes_ = new CustList<Ints>();
        Ints positionsDistinctes_ = new Ints();
        for (int position_ : _positions) {
            if (!positionsDistinctes_.containsObj(position_)) {
                positionsDistinctes_.add(position_);
            }
        }
        for (int position2_ : positionsDistinctes_) {
            groupes_.add(new Ints());
            indice_ = 0;
            for (int position_ : _positions) {
                if (position_ == position2_) {
                    groupes_.last().add(indice_);
                }
                indice_++;
            }
        }
        groupes_ = getGroups(groupes_);
        return groupes_;
    }

    static int indexOfFirstTrick(CustList<TrickTarot> _tricks, int _player,GameTarotTeamsRelation _relations) {
        int indexOne_ = -1;
        int index_ = 1;
        for (TrickTarot t: _tricks) {
//            if (!t.getVuParToutJoueur()) {
//                index_++;
//                continue;
//            }
            if (_relations.memeEquipe(t.getRamasseur(), _player) && indexOne_ == -1) {
                indexOne_ = index_;
            }
            index_++;
        }
        return indexOne_;
    }

    public Longs coefficients(Ints _positions) {
        DealingTarot repartition_ = relations.getRules().getDealing();
        return coefficients(_positions, repartition_);
    }

    static Longs coefficients(Ints _positions, DealingTarot _repartition) {
        if (_repartition == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL) {
            return fourPlayersWithoutCall(_positions);
        }
        if (_repartition == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL) {
            return sixPlayersWithoutCall(_positions);
        }
        int nombreJoueurs_ = _repartition.getId().getNombreJoueurs();
        if (nombreJoueurs_ == 3) {
            return threePlayers(_positions, nombreJoueurs_);
        }
        if (nombreJoueurs_ == 4) {
            return fourPlayers(_positions, nombreJoueurs_);
        }
        if (nombreJoueurs_ == 5) {
            return fivePlayers(_positions, nombreJoueurs_);
        }
        return sixPlayers(_positions, nombreJoueurs_);
    }

    private static Longs sixPlayers(Ints _positions, int _nombreJoueurs) {
        int maxPosition_ = maxPosition(_positions);
        int nombreLitiges_ = _nombreJoueurs - maxPosition_ + 1;
        if (nombreLitiges_ == 1) {
            return sixPlayersDiff(_positions);
        }
        if (nombreLitiges_ == 2) {
            return sixPlayersNearDiff(_positions);
        }
        if (nombreLitiges_ == 3) {
            return sixPlayersNearNearDiff(_positions);
        }
        if (nombreLitiges_ == 4) {
            Longs coefficients_ = new Longs();
            for (int position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add(SIX_PLAYERS_THREE_WIN);
                } else if (position_ == 2) {
                    coefficients_.add(SIX_PLAYERS_THREE_ZERO);
                } else {
                    coefficients_.add(SIX_PLAYERS_THREE_LOOSE);
                }
            }
            return coefficients_;
        }
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(SIX_PLAYERS_WIN);
            } else {
                coefficients_.add(SIX_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs sixPlayersNearNearDiff(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(SIX_PLAYERS_FOUR_WIN);
            } else if (position_ == 2) {
                coefficients_.add(SIX_PLAYERS_FOUR_SECOND);
            } else if (position_ == 3) {
                coefficients_.add(SIX_PLAYERS_FOUR_THIRD);
            } else {
                coefficients_.add(SIX_PLAYERS_FOUR_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs sixPlayersNearDiff(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(SIX_PLAYERS_FIVE_WIN);
            } else if (position_ == 2) {
                coefficients_.add(SIX_PLAYERS_FIVE_SECOND);
            } else if (position_ == 3) {
                coefficients_.add(SIX_PLAYERS_FIVE_THIRD);
            } else if (position_ == 4) {
                coefficients_.add(SIX_PLAYERS_FIVE_FOURTH);
            } else {
                coefficients_.add(SIX_PLAYERS_FIVE_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs sixPlayersDiff(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(SIX_PLAYERS_SIX_WIN);
            } else if (position_ == 2) {
                coefficients_.add(SIX_PLAYERS_SIX_SECOND);
            } else if (position_ == 3) {
                coefficients_.add(SIX_PLAYERS_SIX_THIRD);
            } else if (position_ == 4) {
                coefficients_.add(SIX_PLAYERS_SIX_FOURTH);
            } else if (position_ == 5) {
                coefficients_.add(SIX_PLAYERS_SIX_FIFTH);
            } else {
                coefficients_.add(SIX_PLAYERS_SIX_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fivePlayers(Ints _positions, int _nombreJoueurs) {
        int maxPosition_ = maxPosition(_positions);
        int nombreLitiges_ = _nombreJoueurs - maxPosition_ + 1;
        if (nombreLitiges_ == 1) {
            return fivePlayersDiff(_positions);
        }
        if (nombreLitiges_ == 2) {
            return fivePlayersNearDiff(_positions);
        }
        Longs coefficients_ = new Longs();
        if (nombreLitiges_ == 3) {
            for (int position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add(FIVE_PLAYERS_THREE_WIN);
                } else if (position_ == 2) {
                    coefficients_.add(FIVE_PLAYERS_THREE_ZERO);
                } else {
                    coefficients_.add(FIVE_PLAYERS_THREE_LOOSE);
                }
            }
            return coefficients_;
        }
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(FIVE_PLAYERS_WIN);
            } else {
                coefficients_.add(FIVE_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fivePlayersNearDiff(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(FIVE_PLAYERS_FOUR_WIN);
            } else if (position_ == 2) {
                coefficients_.add(FIVE_PLAYERS_FOUR_SECOND);
            } else if (position_ == 3) {
                coefficients_.add(FIVE_PLAYERS_FOUR_THIRD);
            } else {
                coefficients_.add(FIVE_PLAYERS_FOUR_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fivePlayersDiff(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(FIVE_PLAYERS_FIVE_WIN);
            } else if (position_ == 2) {
                coefficients_.add(FIVE_PLAYERS_FIVE_SECOND);
            } else if (position_ == 3) {
                coefficients_.add(FIVE_PLAYERS_FIVE_THIRD);
            } else if (position_ == 4) {
                coefficients_.add(FIVE_PLAYERS_FIVE_FOURTH);
            } else {
                coefficients_.add(FIVE_PLAYERS_FIVE_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fourPlayers(Ints _positions, int _nombreJoueurs) {
        int maxPosition_ = maxPosition(_positions);
        int nombreLitiges_ = _nombreJoueurs - maxPosition_ + 1;
        if (nombreLitiges_ == 1) {
            return fourPlayersDiff(_positions);
        }
        Longs coefficients_ = new Longs();
        if (nombreLitiges_ == 2) {
            for (int position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add(FOUR_PLAYERS_THREE_WIN);
                } else if (position_ == 2) {
                    coefficients_.add(FOUR_PLAYERS_THREE_ZERO);
                } else {
                    coefficients_.add(FOUR_PLAYERS_THREE_LOOSE);
                }
            }
            return coefficients_;
        }
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(FOUR_PLAYERS_WIN);
            } else {
                coefficients_.add(FOUR_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fourPlayersDiff(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(FOUR_PLAYERS_FOUR_WIN);
            } else if (position_ == 2) {
                coefficients_.add(FOUR_PLAYERS_FOUR_SECOND);
            } else if (position_ == 3) {
                coefficients_.add(FOUR_PLAYERS_FOUR_THIRD);
            } else {
                coefficients_.add(FOUR_PLAYERS_FOUR_LOOSE);
            }
        }
        return coefficients_;
    }

    private static int maxPosition(Ints _positions) {
        int maxPosition_ = 0;
        for (int position_ : _positions) {
            maxPosition_ = NumberUtil.max(position_, maxPosition_);
        }
        return maxPosition_;
    }

    private static Longs threePlayers(Ints _positions, int _nombreJoueurs) {
        int maxPosition_ = maxPosition(_positions);
        int nombreLitiges_ = _nombreJoueurs - maxPosition_ + 1;
        Longs coefficients_ = new Longs();
        if (nombreLitiges_ == 1) {
            for (int position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add(TRHEE_PLAYERS_THREE_WIN);
                } else if (position_ == 2) {
                    coefficients_.add(TRHEE_PLAYERS_THREE_ZERO);
                } else {
                    coefficients_.add(TRHEE_PLAYERS_THREE_LOOSE);
                }
            }
            return coefficients_;
        }
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(TRHEE_PLAYERS_WIN);
            } else {
                coefficients_.add(TRHEE_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs sixPlayersWithoutCall(Ints _positions) {
        Ints positionsDist_ = new Ints(_positions);
        positionsDist_.removeDuplicates();
        if (positionsDist_.size() == 3) {
            Longs coefficients_ = new Longs();
            for (int position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add(SIX_PLAYERS_WITHOUT_CALL_THREE_WIN);
                } else if (position_ == 3) {
                    coefficients_.add(SIX_PLAYERS_WITHOUT_CALL_THREE_ZERO);
                } else {
                    coefficients_.add(SIX_PLAYERS_WITHOUT_CALL_THREE_LOOSE);
                }
            }
            return coefficients_;
        }
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(SIX_PLAYERS_WITHOUT_CALL_WIN);
            } else {
                coefficients_.add(SIX_PLAYERS_WITHOUT_CALL_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fourPlayersWithoutCall(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(FOUR_PLAYERS_WITHOUT_CALL_WIN);
            } else {
                coefficients_.add(FOUR_PLAYERS_WITHOUT_CALL_LOOSE);
            }
        }
        return coefficients_;
    }

    public Longs coefficientsMisere(Ints _positions) {
        DealingTarot repartition_ = relations.getRules().getDealing();
        return coefficientsMisere(_positions, repartition_);
    }

    static Longs coefficientsMisere(Ints _positions, DealingTarot _repartition) {
        if (_repartition == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL) {
            return fourPlayersWithoutCallMisere(_positions);
        }
        if (_repartition == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL) {
            return sixPlayersWithoutCallMisere(_positions);
        }
        int nombreJoueurs_ = _repartition.getId().getNombreJoueurs();
        if (nombreJoueurs_ == 3) {
            return threePlayersMisere(_positions);
        }
        if (nombreJoueurs_ == 4) {
            return fourPlayersMisere(_positions);
        }
        if (nombreJoueurs_ == 5) {
            return fivePlayersMisere(_positions);
        }
        return sixPlayersMisere(_positions);
    }

    private static Longs sixPlayersMisere(Ints _positions) {
        int nombreLitiges_ = nombreLitiges(_positions);
        if (nombreLitiges_ == 1) {
            return sixPlayersDiffMisere(_positions);
        }
        if (nombreLitiges_ == 2) {
            return sixPlayersNearDiffMisere(_positions);
        }
        if (nombreLitiges_ == 3) {
            return sixPlayersNearNearDiffMisere(_positions);
        }
        Longs coefficients_ = new Longs();
        if (nombreLitiges_ == 4) {
            for (int position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add(MIS_SIX_PLAYERS_THREE_WIN);
                } else if (position_ == 5) {
                    coefficients_.add(MIS_SIX_PLAYERS_THREE_ZERO);
                } else {
                    coefficients_.add(MIS_SIX_PLAYERS_THREE_LOOSE);
                }
            }
            return coefficients_;
        }
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_SIX_PLAYERS_WIN);
            } else {
                coefficients_.add(MIS_SIX_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs sixPlayersNearNearDiffMisere(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_SIX_PLAYERS_FOUR_WIN);
            } else if (position_ == 4) {
                coefficients_.add(MIS_SIX_PLAYERS_FOUR_SECOND);
            } else if (position_ == 5) {
                coefficients_.add(MIS_SIX_PLAYERS_FOUR_THIRD);
            } else {
                coefficients_.add(MIS_SIX_PLAYERS_FOUR_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs sixPlayersNearDiffMisere(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_SIX_PLAYERS_FIVE_WIN);
            } else if (position_ == 3) {
                coefficients_.add(MIS_SIX_PLAYERS_FIVE_SECOND);
            } else if (position_ == 4) {
                coefficients_.add(MIS_SIX_PLAYERS_FIVE_THIRD);
            } else if (position_ == 5) {
                coefficients_.add(MIS_SIX_PLAYERS_FIVE_FOURTH);
            } else {
                coefficients_.add(MIS_SIX_PLAYERS_FIVE_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs sixPlayersDiffMisere(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_SIX_PLAYERS_SIX_WIN);
            } else if (position_ == 2) {
                coefficients_.add(MIS_SIX_PLAYERS_SIX_SECOND);
            } else if (position_ == 3) {
                coefficients_.add(MIS_SIX_PLAYERS_SIX_THIRD);
            } else if (position_ == 4) {
                coefficients_.add(MIS_SIX_PLAYERS_SIX_FOURTH);
            } else if (position_ == 5) {
                coefficients_.add(MIS_SIX_PLAYERS_SIX_FIFTH);
            } else {
                coefficients_.add(MIS_SIX_PLAYERS_SIX_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fivePlayersMisere(Ints _positions) {
        int nombreLitiges_ = nombreLitiges(_positions);
        if (nombreLitiges_ == 1) {
            return fivePlayersDiffMisere(_positions);
        }
        if (nombreLitiges_ == 2) {
            return fivePlayersNearDiffMisere(_positions);
        }
        Longs coefficients_ = new Longs();
        if (nombreLitiges_ == 3) {
            for (int position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add(MIS_FIVE_PLAYERS_THREE_WIN);
                } else if (position_ == 4) {
                    coefficients_.add(MIS_FIVE_PLAYERS_THREE_ZERO);
                } else {
                    coefficients_.add(MIS_FIVE_PLAYERS_THREE_LOOSE);
                }
            }
            return coefficients_;
        }
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_FIVE_PLAYERS_WIN);
            } else {
                coefficients_.add(MIS_FIVE_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fivePlayersNearDiffMisere(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_FIVE_PLAYERS_FOUR_WIN);
            } else if (position_ == 3) {
                coefficients_.add(MIS_FIVE_PLAYERS_FOUR_SECOND);
            } else if (position_ == 4) {
                coefficients_.add(MIS_FIVE_PLAYERS_FOUR_THIRD);
            } else {
                coefficients_.add(MIS_FIVE_PLAYERS_FOUR_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fivePlayersDiffMisere(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_FIVE_PLAYERS_FIVE_WIN);
            } else if (position_ == 2) {
                coefficients_.add(MIS_FIVE_PLAYERS_FIVE_SECOND);
            } else if (position_ == 3) {
                coefficients_.add(MIS_FIVE_PLAYERS_FIVE_THIRD);
            } else if (position_ == 4) {
                coefficients_.add(MIS_FIVE_PLAYERS_FIVE_FOURTH);
            } else {
                coefficients_.add(MIS_FIVE_PLAYERS_FIVE_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fourPlayersMisere(Ints _positions) {
        int nombreLitiges_ = nombreLitiges(_positions);
        if (nombreLitiges_ == 1) {
            return fourPlayersDiffMisere(_positions);
        }
        Longs coefficients_ = new Longs();
        if (nombreLitiges_ == 2) {
            for (int position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add(MIS_FOUR_PLAYERS_THREE_WIN);
                } else if (position_ == 3) {
                    coefficients_.add(MIS_FOUR_PLAYERS_THREE_ZERO);
                } else {
                    coefficients_.add(MIS_FOUR_PLAYERS_THREE_LOOSE);
                }
            }
            return coefficients_;
        }
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_FOUR_PLAYERS_LOOSE);
            } else {
                coefficients_.add(MIS_FOUR_PLAYERS_WIN);
            }
        }
        return coefficients_;
    }

    private static Longs fourPlayersDiffMisere(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_FOUR_PLAYERS_FOUR_WIN);
            } else if (position_ == 2) {
                coefficients_.add(MIS_FOUR_PLAYERS_FOUR_SECOND);
            } else if (position_ == 3) {
                coefficients_.add(MIS_FOUR_PLAYERS_FOUR_THIRD);
            } else {
                coefficients_.add(MIS_FOUR_PLAYERS_FOUR_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs threePlayersMisere(Ints _positions) {
        Longs coefficients_ = new Longs();
        int nombreLitiges_ = nombreLitiges(_positions);
        if (nombreLitiges_ == 1) {
            for (int position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add(MIS_THREE_PLAYERS_THREE_WIN);
                } else if (position_ == 2) {
                    coefficients_.add(MIS_THREE_PLAYERS_THREE_ZERO);
                } else {
                    coefficients_.add(MIS_THREE_PLAYERS_THREE_LOOSE);
                }
            }
            return coefficients_;
        }
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_THREE_PLAYERS_WIN);
            } else {
                coefficients_.add(MIS_THREE_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs sixPlayersWithoutCallMisere(Ints _positions) {
        Ints positionsDist_ = new Ints(_positions);
        positionsDist_.removeDuplicates();
        if (positionsDist_.size() == 3) {
            Longs coefficients_ = new Longs();
            for (int position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add(MIS_SIX_PLAYERS_WITHOUT_CALL_THREE_WIN);
                } else if (position_ == 3) {
                    coefficients_.add(MIS_SIX_PLAYERS_WITHOUT_CALL_THREE_ZERO);
                } else {
                    coefficients_.add(MIS_SIX_PLAYERS_WITHOUT_CALL_THREE_LOOSE);
                }
            }
            return coefficients_;
        }
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_SIX_PLAYERS_WITHOUT_CALL_WIN);
            } else {
                coefficients_.add(MIS_SIX_PLAYERS_WITHOUT_CALL_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Longs fourPlayersWithoutCallMisere(Ints _positions) {
        Longs coefficients_ = new Longs();
        for (int position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_FOUR_PLAYERS_WITHOUT_CALL_WIN);
            } else {
                coefficients_.add(MIS_FOUR_PLAYERS_WITHOUT_CALL_LOOSE);
            }
        }
        return coefficients_;
    }

    private static int nombreLitiges(Ints _positions) {
        int nombreLitiges_ = 0;
        for (int position_ : _positions) {
            if (position_ == 1) {
                nombreLitiges_++;
            }
        }
        return nombreLitiges_;
    }

    public CustList<IdMap<Handfuls,Long>> calculHandfulsScorePlayer(int _player) {
        Ints pls_ = relations.equipe(_player);
        return calculHandfulsScorePlayer(relations.getNombreDeJoueurs(),pls_, declaresHandfuls);
    }

    static CustList<IdMap<Handfuls, Long>> calculHandfulsScorePlayer(int _nbPlayers,Ints _team, CustList<IdList<Handfuls>> _declaresHandfuls) {
        int nombreDeJoueurs_ = _team.size();
        CustList<IdMap<Handfuls,Long>> scores1_ = handfulsScore(_nbPlayers);
        for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {

            int pl_ = _team.get(joueur2_);
            for (Handfuls poignee_ : _declaresHandfuls.get(pl_)) {
                scores1_.get(pl_).put(poignee_,
                        poignee_.getPoints());
            }
        }
        return scores1_;
    }

    private static CustList<IdMap<Handfuls, Long>> handfulsScore(int _nbPlayers) {
        CustList<IdMap<Handfuls, Long>> scores1_ = new CustList<IdMap<Handfuls, Long>>();
        for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < _nbPlayers; joueur2_++) {
            scores1_.add(new IdMap<Handfuls,Long>());
        }
        return scores1_;
    }
    static Longs calculBonusScorePlayer(int _nbPlayers,Ints _team, Longs _additional) {
        int nombreDeJoueurs_ = _team.size();
        Longs bonus_ = bonusScore(_nbPlayers);
        for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {
            int pl_ = _team.get(joueur2_);
            bonus_.set(pl_,_additional.get(pl_));
        }
        return bonus_;
    }

    private static Longs bonusScore(int _nbPlayers) {
        Longs scores1_ = new Longs();
        for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < _nbPlayers; joueur2_++) {
            scores1_.add(0L);
        }
        return scores1_;
    }
    public CustList<IdMap<Miseres,Long>> calculMiseresScorePlayer(int _player) {
        Ints pls_ = relations.equipe(_player);
        return calculMiseresScorePlayer(relations.getNombreDeJoueurs(),pls_, declaresMiseres);
    }

    static CustList<IdMap<Miseres, Long>> calculMiseresScorePlayer(int _nbPlayers,Ints _team, CustList<IdList<Miseres>> _declaresMiseres) {
        int nombreDeJoueurs_ = _team.size();
        CustList<IdMap<Miseres,Long>> scores1_ = miseresScore(_nbPlayers);
        for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {

            int pl_ = _team.get(joueur2_);
            for (Miseres poignee_ : _declaresMiseres.get(pl_)) {
                scores1_.get(pl_).put(poignee_,
                        poignee_.getPoints());
            }
        }
        return scores1_;
    }

    private static CustList<IdMap<Miseres, Long>> miseresScore(int _nbPlayers) {
        CustList<IdMap<Miseres, Long>> scores1_ = new CustList<IdMap<Miseres, Long>>();
        for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < _nbPlayers; joueur2_++) {
            scores1_.add(new IdMap<Miseres, Long>());
        }
        return scores1_;
    }
    public CustList<Longs> calculSmallLastTurnScorePlayer(int _player) {
        Ints pls_ = relations.equipe(_player);
        return calculSmallLastTurnScorePlayer(relations.getNombreDeJoueurs(),pls_, smallBound);
    }

    static CustList<Longs> calculSmallLastTurnScorePlayer(int _nbPlayers,Ints _team, CustList<BoolVal> _smallBound) {
        int nombreDeJoueurs_ = _team.size();
        CustList<Longs> scores1_ = smallLastTurn(_nbPlayers);
        for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {

            int pl_ = _team.get(joueur2_);
            if (_smallBound.get(pl_) == BoolVal.TRUE) {
                scores1_.get(pl_).add(
                        BonusTarot.SMALL_BOUND.getPoints());
            }
        }
        return scores1_;
    }
    private static CustList<Longs> smallLastTurn(int _nbPlayers) {
        CustList<Longs> scores1_ = new CustList<Longs>();
        for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < _nbPlayers; joueur2_++) {
            scores1_.add(new Longs());
        }
        return scores1_;
    }

    public long primeSupplementaire(int _joueur) {
        return primeSupplementaire(_joueur, relations, wonPlayersTeam);
    }

    static long primeSupplementaire(int _joueur, GameTarotTeamsRelation _relations, CustList<HandTarot> _wonPlayersTeam) {
        int nombreJoueurs_ = _relations.getNombreDeJoueurs();
        HandTarot plisAdversaires_ = new HandTarot();
        for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nombreJoueurs_; joueur2_++) {
            if (!_relations.memeEquipe(_joueur, joueur2_)) {
                plisAdversaires_.ajouterCartes(_wonPlayersTeam.get(joueur2_));
            }
        }
        if (plisAdversaires_.estVide()) {
            return BonusTarot.SLAM.getPoints() / 2;
        }
        return 0;
    }

    public Longs calculerScoresJoueurs(Longs _coefficients,
                                      long _differenceMaxDouble) {
        int nombreJoueurs_ = relations.getNombreDeJoueurs();
        long parite_ = getParite(_differenceMaxDouble, nombrePointsChien);
        long sumLoc_ = _differenceMaxDouble
                + nombrePointsChien + parite_;
        return calculerScoresJoueurs(_coefficients, nombreJoueurs_, sumLoc_);
    }

    static Longs calculerScoresJoueurs(Longs _coefficients, int _nombreJoueurs, long _sumLoc) {
        Longs scores_ = new Longs();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            scores_.add(0L);
        }
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            scores_.set(joueur_,
                    4L * (_coefficients.get(joueur_) * (PTS_BASE + _sumLoc / 2)));
        }
        return scores_;
    }

    public long differenceMax(long _differenceMaxDouble) {
        return differenceMax(_differenceMaxDouble, nombrePointsChien);
    }

    static long differenceMax(long _differenceMaxDouble, long _nombrePointsChien) {
        long parite_ = getParite(_differenceMaxDouble, _nombrePointsChien);
        return (_differenceMaxDouble + _nombrePointsChien + parite_) / 2;
    }

    public Longs calculerScoresJoueurs(Longs _coefficients,
                                        long _differenceMaxDouble, Longs _primeSupplementaire) {

        long parite_ = getParite(_differenceMaxDouble, nombrePointsChien);
        long sumLoc_ = nombrePointsChien+parite_+_differenceMaxDouble;
        return calculerScoresJoueurs(_coefficients, _primeSupplementaire, sumLoc_, relations, declaresHandfuls, declaresMiseres, smallBound);
    }

    private static long getParite(long _differenceMaxDouble, long _nombrePointsChien) {
        int parite_;
        if ((_differenceMaxDouble + _nombrePointsChien) / 2 * 2 == _differenceMaxDouble
                + _nombrePointsChien) {
            parite_ = 0;
        } else {
            parite_ = 1;
        }
        return parite_;
    }

    static Longs calculerScoresJoueurs(Longs _coefficients, Longs _primeSupplementaire,
                                        long _sumLoc, GameTarotTeamsRelation _relations,
                                        CustList<IdList<Handfuls>> _declaresHandfuls,
                                        CustList<IdList<Miseres>> _declaresMiseres,
                                        CustList<BoolVal> _smallBound) {
        int nombreJoueurs_ = _relations.getNombreDeJoueurs();
        Longs scores_ = new Longs();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            Ints equipe_ = _relations.equipe(joueur_);
            long pointsAnnoncesAutresJoueurs_ = pointsAnnoncesJoueur(nombreJoueurs_,_primeSupplementaire,_declaresHandfuls,_declaresMiseres,_smallBound,_relations.adversaires(joueur_,GameTarotTeamsRelation.tousJoueurs(nombreJoueurs_)));
            long pointsAnnoncesJoueur_ = pointsAnnoncesJoueur(nombreJoueurs_,_primeSupplementaire,_declaresHandfuls, _declaresMiseres, _smallBound, equipe_);
//            short sommePrimeSupplementaire_ = 0;
//            int nbTeam_ = 0;
//            for (byte j_ = IndexConstants.FIRST_INDEX; j_ < nombreJoueurs_; j_++) {
//                if (_relations.memeEquipe(j_, joueur_)) {
//                    nbTeam_++;
//                    continue;
//                }
//                sommePrimeSupplementaire_ += _primeSupplementaire.get(j_);
//                for (Handfuls h : _declaresHandfuls.get(j_)) {
//                    pointsAnnoncesAutresJoueurs_ += h.getPoints();
//                }
//                for (Miseres h : _declaresMiseres.get(j_)) {
//                    pointsAnnoncesAutresJoueurs_ += h.getPoints();
//                }
//                if (_smallBound.get(j_) == BoolVal.TRUE) {
//                    pointsAnnoncesAutresJoueurs_ += BonusTarot.SMALL_BOUND.getPoints();
//                }
//            }
            int rate_ = equipe_.size();
            long score_ = (4L * (_coefficients.get(joueur_) * (PTS_BASE + _sumLoc / 2)
                    + (nombreJoueurs_ - rate_) * pointsAnnoncesJoueur_
                    - rate_ * pointsAnnoncesAutresJoueurs_));
//                    + (nombreJoueurs_ - nbTeam_) * (pointsAnnoncesJoueur_)
//                    - nbTeam_ * (pointsAnnoncesAutresJoueurs_)));
            scores_.add(
                    score_);
        }
        return scores_;
    }

    private static long pointsAnnoncesJoueur(int _nbPlayers,Longs _primeSupplementaire, CustList<IdList<Handfuls>> _declaresHandfuls, CustList<IdList<Miseres>> _declaresMiseres, CustList<BoolVal> _smallBound, Ints _equipe) {
        long pointsAnnoncesJoueur_ = sum(calculBonusScorePlayer(_nbPlayers,_equipe,_primeSupplementaire));
        for (IdMap<Handfuls,Long> annoncesJoueur_ : calculHandfulsScorePlayer(_nbPlayers,_equipe, _declaresHandfuls)) {
            CustList<Long> values_ = annoncesJoueur_.values();
            pointsAnnoncesJoueur_ += sum(values_);
        }
        for (IdMap<Miseres,Long> annoncesJoueur_ : calculMiseresScorePlayer(_nbPlayers,_equipe, _declaresMiseres)) {
            CustList<Long> values_ = annoncesJoueur_.values();
            pointsAnnoncesJoueur_ += sum(values_);
        }
        for (Longs annoncesJoueur_ : calculSmallLastTurnScorePlayer(_nbPlayers,_equipe, _smallBound)) {
            pointsAnnoncesJoueur_ += sum(annoncesJoueur_);
        }
        return pointsAnnoncesJoueur_;
    }

    static long sum(CustList<Long> _ls) {
        long s_ = 0;
        for (long s: _ls) {
            s_ += s;
        }
        return s_;
    }
    private CustList<TrickTarot> getWonTricksListTeam(int _player) {
        Ints team_ = relations.coequipiers(_player, GameTarotTeamsRelation.tousJoueurs(relations.getNombreDeJoueurs()));
        team_.add(_player);
        return getWonTricksListTeam(tricks,team_);
    }

    private boolean aucunPliAdverseFin(int _joueur, CustList<TrickTarot> _unionPlis) {
        int nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        Ints partenaires_ = relations.coequipiers(_joueur,
                GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        partenaires_.add(_joueur);
        return plisTousFaitsParFin(partenaires_, _unionPlis, nombreDeJoueurs_);
    }

    private static boolean plisTousFaitsParFin(Ints _joueurs,
                                               CustList<TrickTarot> _unionPlis, int _nombreJoueurs) {
        Ints autresJoueurs_ = GameTarotTeamsRelation.autresJoueurs(_joueurs, _nombreJoueurs);
        return getWonTricksListTeam(_unionPlis,autresJoueurs_).isEmpty();
    }

    HandTarot getWonTricksTeam(int _player) {
        HandTarot cards_ = new HandTarot();
        for (TrickTarot t: getWonTricksListTeam(_player)) {
            cards_.ajouterCartes(t.getCartes());
        }
        return cards_;
    }
    static CustList<TrickTarot> getWonTricksListTeam(CustList<TrickTarot> _tricks, Ints _players) {
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        TrickTarot lastTrick_ = _tricks.last();
        int indExc_ = lastTrick_.joueurAyantJoue(CardTarot.EXCUSE);
        int maxExclude_ = _tricks.size();
        if (indExc_ > -1) {
            maxExclude_--;
        }
        int nbOther_ = 0;
        for (TrickTarot t: _tricks.left(maxExclude_).mid(1)) {
//            if (!t.getVuParToutJoueur()) {
//                continue;
//            }
            if (!_players.containsObj(t.getRamasseur())) {
                nbOther_++;
            } else {
                tricks_.add(t);
            }
        }
        return wonTricksListTeam(_players, tricks_, lastTrick_, indExc_, nbOther_);
    }

    private static CustList<TrickTarot> wonTricksListTeam(Ints _players, CustList<TrickTarot> _tricks, TrickTarot _lastTrick, int _indExc, int _nbOther) {
        if (_indExc < 0) {
            return _tricks;
        }
        if (_nbOther == 0) {
            if (_players.containsObj(_indExc) || _players.containsObj(_lastTrick.getRamasseur())) {
                _tricks.add(_lastTrick);
            }
        } else {
            if (_players.containsObj(_lastTrick.getRamasseur()) && (!_tricks.isEmpty() || _players.containsObj(_indExc))) {
                _tricks.add(_lastTrick);
            }
        }
        return _tricks;
    }

    Ints getFirstTrick() {
        return firstTrick;
    }

    CustList<HandTarot> getWonPlayersTeam() {
        return wonPlayersTeam;
    }
}
