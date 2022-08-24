package cards.tarot;

import cards.consts.EndGameState;
import cards.consts.Role;
import cards.consts.Suit;
import cards.tarot.comparators.HandfulComparator;
import cards.tarot.comparators.MiseresComparator;
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
import code.util.EnumList;
import code.util.EnumMap;
import code.util.*;
import code.util.TreeMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class EndTarotGame {

    public static final int NO_OUDLER_PTS = 56;
    public static final int ONE_OUDLER_PTS = 51;
    public static final int TWO_OUDLERS_PTS = 41;
    public static final int ALL_OUDLERS_PTS = 36;

    public static final int PTS_BASE = 25;
    private static final short FOUR_PLAYERS_WITHOUT_CALL_WIN = (short) 1;
    private static final short FOUR_PLAYERS_WITHOUT_CALL_LOOSE = (short) -1;
    private static final short SIX_PLAYERS_WITHOUT_CALL_THREE_WIN = (short) 1;
    private static final short SIX_PLAYERS_WITHOUT_CALL_THREE_ZERO = (short) 0;
    private static final short SIX_PLAYERS_WITHOUT_CALL_THREE_LOOSE = (short) -1;
    private static final short SIX_PLAYERS_WITHOUT_CALL_WIN = (short) 2;
    private static final short SIX_PLAYERS_WITHOUT_CALL_LOOSE = (short) -1;
    private static final short TRHEE_PLAYERS_THREE_WIN = (short) 1;
    private static final short TRHEE_PLAYERS_THREE_ZERO = (short) 0;
    private static final short TRHEE_PLAYERS_THREE_LOOSE = (short) -1;
    private static final short TRHEE_PLAYERS_WIN = (short) 2;
    private static final short TRHEE_PLAYERS_LOOSE = (short) -1;
    private static final short FOUR_PLAYERS_FOUR_WIN = (short) 2;
    private static final short FOUR_PLAYERS_FOUR_SECOND = (short) 1;
    private static final short FOUR_PLAYERS_FOUR_THIRD = (short) -1;
    private static final short FOUR_PLAYERS_FOUR_LOOSE = (short) -2;
    private static final short FOUR_PLAYERS_THREE_WIN = (short) 3;
    private static final short FOUR_PLAYERS_THREE_ZERO = (short) 1;
    private static final short FOUR_PLAYERS_THREE_LOOSE = (short) -2;
    private static final short FOUR_PLAYERS_WIN = (short) 6;
    private static final short FOUR_PLAYERS_LOOSE = (short) -2;
    private static final short FIVE_PLAYERS_FIVE_WIN = (short) 2;
    private static final short FIVE_PLAYERS_FIVE_SECOND = (short) 1;
    private static final short FIVE_PLAYERS_FIVE_THIRD = (short) 0;
    private static final short FIVE_PLAYERS_FIVE_FOURTH = (short) -1;
    private static final short FIVE_PLAYERS_FIVE_LOOSE = (short) -2;
    private static final short FIVE_PLAYERS_FOUR_WIN = (short) 3;
    private static final short FIVE_PLAYERS_FOUR_SECOND = (short) 1;
    private static final short FIVE_PLAYERS_FOUR_THIRD = (short) 0;
    private static final short FIVE_PLAYERS_FOUR_LOOSE = (short) -2;
    private static final short FIVE_PLAYERS_THREE_WIN = (short) 6;
    private static final short FIVE_PLAYERS_THREE_ZERO = (short) 0;
    private static final short FIVE_PLAYERS_THREE_LOOSE = (short) -2;
    private static final short FIVE_PLAYERS_WIN = (short) 8;
    private static final short FIVE_PLAYERS_LOOSE = (short) -2;
    private static final short SIX_PLAYERS_SIX_WIN = (short) 3;
    private static final short SIX_PLAYERS_SIX_SECOND = (short) 2;
    private static final short SIX_PLAYERS_SIX_THIRD = (short) 1;
    private static final short SIX_PLAYERS_SIX_FOURTH = (short) -1;
    private static final short SIX_PLAYERS_SIX_FIFTH = (short) -2;
    private static final short SIX_PLAYERS_SIX_LOOSE = (short) -3;
    private static final short SIX_PLAYERS_FIVE_WIN = (short) 3;
    private static final short SIX_PLAYERS_FIVE_SECOND = (short) 2;
    private static final short SIX_PLAYERS_FIVE_THIRD = (short) 1;
    private static final short SIX_PLAYERS_FIVE_FOURTH = (short) 0;
    private static final short SIX_PLAYERS_FIVE_LOOSE = (short) -3;
    private static final short SIX_PLAYERS_FOUR_WIN = (short) 4;
    private static final short SIX_PLAYERS_FOUR_SECOND = (short) 2;
    private static final short SIX_PLAYERS_FOUR_THIRD = (short) 0;
    private static final short SIX_PLAYERS_FOUR_LOOSE = (short) -2;
    private static final short SIX_PLAYERS_THREE_WIN = (short) 8;
    private static final short SIX_PLAYERS_THREE_ZERO = (short) 0;
    private static final short SIX_PLAYERS_THREE_LOOSE = (short) -2;
    private static final short SIX_PLAYERS_WIN = (short) 10;
    private static final short SIX_PLAYERS_LOOSE = (short) -2;
    private static final short MIS_FOUR_PLAYERS_WITHOUT_CALL_WIN = (short) 1;
    private static final short MIS_FOUR_PLAYERS_WITHOUT_CALL_LOOSE = (short) -1;
    private static final short MIS_SIX_PLAYERS_WITHOUT_CALL_THREE_WIN = (short) 1;
    private static final short MIS_SIX_PLAYERS_WITHOUT_CALL_THREE_ZERO = (short) 0;
    private static final short MIS_SIX_PLAYERS_WITHOUT_CALL_THREE_LOOSE = (short) -1;
    private static final short MIS_SIX_PLAYERS_WITHOUT_CALL_WIN = (short) 1;
    private static final short MIS_SIX_PLAYERS_WITHOUT_CALL_LOOSE = (short) -2;
    private static final short MIS_THREE_PLAYERS_THREE_WIN = (short) 1;
    private static final short MIS_THREE_PLAYERS_THREE_ZERO = (short) 0;
    private static final short MIS_THREE_PLAYERS_THREE_LOOSE = (short) -1;
    private static final short MIS_THREE_PLAYERS_WIN = (short) 1;
    private static final short MIS_THREE_PLAYERS_LOOSE = (short) -2;
    private static final short MIS_FOUR_PLAYERS_FOUR_WIN = (short) 2;
    private static final short MIS_FOUR_PLAYERS_FOUR_SECOND = (short) 1;
    private static final short MIS_FOUR_PLAYERS_FOUR_THIRD = (short) -1;
    private static final short MIS_FOUR_PLAYERS_FOUR_LOOSE = (short) -2;
    private static final short MIS_FOUR_PLAYERS_THREE_WIN = (short) 2;
    private static final short MIS_FOUR_PLAYERS_THREE_ZERO = (short) -1;
    private static final short MIS_FOUR_PLAYERS_THREE_LOOSE = (short) -3;
    private static final short MIS_FOUR_PLAYERS_LOOSE = (short) 2;
    private static final short MIS_FOUR_PLAYERS_WIN = (short) -6;
    private static final short MIS_FIVE_PLAYERS_FIVE_WIN = (short) 2;
    private static final short MIS_FIVE_PLAYERS_FIVE_SECOND = (short) 1;
    private static final short MIS_FIVE_PLAYERS_FIVE_THIRD = (short) 0;
    private static final short MIS_FIVE_PLAYERS_FIVE_FOURTH = (short) -1;
    private static final short MIS_FIVE_PLAYERS_FIVE_LOOSE = (short) -2;
    private static final short MIS_FIVE_PLAYERS_FOUR_WIN = (short) 2;
    private static final short MIS_FIVE_PLAYERS_FOUR_SECOND = (short) 0;
    private static final short MIS_FIVE_PLAYERS_FOUR_THIRD = (short) -1;
    private static final short MIS_FIVE_PLAYERS_FOUR_LOOSE = (short) -3;
    private static final short MIS_FIVE_PLAYERS_THREE_WIN = (short) 2;
    private static final short MIS_FIVE_PLAYERS_THREE_ZERO = (short) 0;
    private static final short MIS_FIVE_PLAYERS_THREE_LOOSE = (short) -6;
    private static final short MIS_FIVE_PLAYERS_WIN = (short) 2;
    private static final short MIS_FIVE_PLAYERS_LOOSE = (short) -8;
    private static final short MIS_SIX_PLAYERS_SIX_WIN = (short) 3;
    private static final short MIS_SIX_PLAYERS_SIX_SECOND = (short) 2;
    private static final short MIS_SIX_PLAYERS_SIX_THIRD = (short) 1;
    private static final short MIS_SIX_PLAYERS_SIX_FOURTH = (short) -1;
    private static final short MIS_SIX_PLAYERS_SIX_FIFTH = (short) -2;
    private static final short MIS_SIX_PLAYERS_SIX_LOOSE = (short) -3;
    private static final short MIS_SIX_PLAYERS_FIVE_WIN = (short) 3;
    private static final short MIS_SIX_PLAYERS_FIVE_SECOND = (short) 0;
    private static final short MIS_SIX_PLAYERS_FIVE_THIRD = (short) -1;
    private static final short MIS_SIX_PLAYERS_FIVE_FOURTH = (short) -2;
    private static final short MIS_SIX_PLAYERS_FIVE_LOOSE = (short) -3;
    private static final short MIS_SIX_PLAYERS_FOUR_WIN = (short) 2;
    private static final short MIS_SIX_PLAYERS_FOUR_SECOND = (short) 0;
    private static final short MIS_SIX_PLAYERS_FOUR_THIRD = (short) -2;
    private static final short MIS_SIX_PLAYERS_FOUR_LOOSE = (short) -4;
    private static final short MIS_SIX_PLAYERS_THREE_WIN = (short) 2;
    private static final short MIS_SIX_PLAYERS_THREE_ZERO = (short) 0;
    private static final short MIS_SIX_PLAYERS_THREE_LOOSE = (short) -8;
    private static final short MIS_SIX_PLAYERS_WIN = (short) 2;
    private static final short MIS_SIX_PLAYERS_LOOSE = (short) -10;
    private final GameTarotTeamsRelation relations;
    private final CustList<TrickTarot> tricks;
    /** Ce sont les poignees annoncees par le(s) joueur(s) */
    private final CustList<EnumList<Handfuls>> declaresHandfuls;
    /** Ce sont les miseres annoncees par le(s) joueur(s) */
    private final CustList<EnumList<Miseres>> declaresMiseres;
    /** Ce sont les primes annoncees par le(s) joueur(s) */
    private final CustList<BoolVal> declaresSlam;
    /** Ce sont les petits au bout par le(s) joueur(s) */
    private final CustList<BoolVal> smallBound;
    private final CustList<HandTarot> wonPlayersTeam = new CustList<HandTarot>();
    private final Ints firstTrick = new Ints();
    private final Shorts oulderPoints = new Shorts();
    private final byte nombrePointsChien;
    private boolean slamTaker;
    private boolean slamDefense;

    public EndTarotGame(GameTarotTeamsRelation _relations, CustList<TrickTarot> _tricks,
                        CustList<EnumList<Handfuls>> _declaresHandfuls,
                        CustList<EnumList<Miseres>> _declaresMiseres,
                        CustList<BoolVal> _declaresSlam, CustList<BoolVal> _smallBound) {
        relations = _relations;
        tricks = _tricks;
        declaresHandfuls = _declaresHandfuls;
        declaresMiseres = _declaresMiseres;
        declaresSlam = _declaresSlam;
        smallBound = _smallBound;
        oulderPoints.add((short) NO_OUDLER_PTS);
        oulderPoints.add((short) ONE_OUDLER_PTS);
        oulderPoints.add((short) TWO_OUDLERS_PTS);
        oulderPoints.add((short) ALL_OUDLERS_PTS);
        byte nombrePointsChien_ = 0;
        for (TrickTarot t: tricks) {
            if (t.getVuParToutJoueur()) {
                continue;
            }
            for (CardTarot c: t) {
                nombrePointsChien_ += c.points();
            }
        }
        nombrePointsChien = nombrePointsChien_;
    }

    public void setupPlayersWonTricks() {
        byte nbPlayers_ = relations.getNombreDeJoueurs();
        for (byte i = 0; i < nbPlayers_; i++) {
            firstTrick.add(indexOfFirstTrick(tricks,i,relations));
            wonPlayersTeam.add(getWonTricksTeam(i));
        }
    }
    public void setupSlams() {
        slamDefense = getWonTricksListTeam(relations.getTaker()).isEmpty();
        byte taker_ = relations.getTaker();
        Bytes defs_ = relations.adversaires(taker_,GameTarotTeamsRelation.tousJoueurs(relations.getNombreDeJoueurs()));
        slamTaker = getWonTricksListTeam(tricks,defs_).isEmpty();
    }
    public short scorePreneurPlisDouble(BidTarot _bid) {
        short nbPointsAtt_ = 0;
        for (CardTarot c: getWonCardsTaker(_bid)) {
            nbPointsAtt_ += c.points();
        }
        return nbPointsAtt_;
    }

    public byte nombreBoutsPreneur(BidTarot _bid) {
        byte nombreBouts_ = 0;
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

    HandTarot getWonCardsPlayer(byte _player,BidTarot _bid) {
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
            byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
            nbPointsAtt_.removeCardIfPresent(CardTarot.EXCUSE);
            if(relations.memeEquipe(_player, joueurExcuse_)) {
                nbPointsAtt_.ajouter(CardTarot.EXCUSE);
            }
        }
        return nbPointsAtt_;
    }

    private CustList<TrickTarot> tricks(byte _player, BidTarot _bid) {
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        if (_bid.getJeuChien() != PlayingDog.AGAINST) {
            tricks_.add(tricks.first());
        }
        for (TrickTarot t: getWonTricksListTeam(_player)) {
            tricks_.add(t);
        }
        return tricks_;
    }

    private boolean excuseDansPlisAttaque(CustList<TrickTarot> _tricks) {
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
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            if (pli_.contient(CardTarot.EXCUSE)) {
                t_.add(pli_);
            }
        }
        return t_;
    }
    public short scorePreneurPlis(short _scorePreneurPlisDouble,
                                  short _scoreNecessairePreneur) {
        RulesTarot rules_ = relations.getRules();
        return scorePreneurPlis(_scorePreneurPlisDouble, _scoreNecessairePreneur, rules_);
    }

    static short scorePreneurPlis(short _scorePreneurPlisDouble, short _scoreNecessairePreneur, RulesTarot _rules) {
        short scorePreneurPlis_ = (short) (_scorePreneurPlisDouble / 2);
        if (scorePreneurPlis_ >= _scoreNecessairePreneur) {
            if (_scorePreneurPlisDouble % 2 == 1) {
                scorePreneurPlis_++;
            }
        } else if (scorePreneurPlis_ + 1 == _scoreNecessairePreneur && _rules.getEndDealTarot() == EndDealTarot.ATTACK_WIN && _scorePreneurPlisDouble % 2 == 1) {
            scorePreneurPlis_++;
        }
        return scorePreneurPlis_;
    }

    public short scoreNecessairePreneur(BidTarot _bid) {
        byte nombreBouts_ = nombreBoutsPreneur(_bid);
        return oulderPoints.get(nombreBouts_);
    }
    public short base(short _scorePreneurPlisDouble,
                      short _differenceScorePreneur) {
        RulesTarot rules_ = relations.getRules();
        return base(_scorePreneurPlisDouble, _differenceScorePreneur, rules_);
    }

    static short base(short _scorePreneurPlisDouble, short _differenceScorePreneur, RulesTarot _rules) {
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
    public short scorePreneurSansAnnonces(short _differenceScorePreneur,
                                          short _base) {
        Bytes called_ = relations.getCalledPlayers();
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        byte taker_ = relations.getTaker();
        return scorePreneurSansAnnonces(_differenceScorePreneur, _base, nombreJoueurs_, taker_, called_, smallBound);
    }

    static short scorePreneurSansAnnonces(short _differenceScorePreneur, short _base, byte _nombreJoueurs, byte _taker, Bytes _called, CustList<BoolVal> _smallBound) {
        short scorePreneurSansAnnonces_ = 0;
        if (_base == 0) {
            return scorePreneurSansAnnonces_;
        }
        scorePreneurSansAnnonces_ = (short) (_base + _differenceScorePreneur);
        if (_smallBound.get(_taker) == BoolVal.TRUE) {
            scorePreneurSansAnnonces_ += BonusTarot.SMALL_BOUND
                    .getPoints();
        }
        boolean appelePetitAuBout_ = false;
        for (byte a: _called) {
            if (_smallBound.get(a) == BoolVal.TRUE) {
                appelePetitAuBout_ = true;
            }
        }
        if(appelePetitAuBout_) {
            scorePreneurSansAnnonces_ += BonusTarot.SMALL_BOUND
                    .getPoints();
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (joueur_ != _taker && !_called.containsObj(joueur_)
                    && _smallBound.get(joueur_) == BoolVal.TRUE) {
                scorePreneurSansAnnonces_ -= BonusTarot.SMALL_BOUND
                        .getPoints();
            }
        }
        return scorePreneurSansAnnonces_;
    }

    public Shorts calculateScores(EnumMap<Role,Rate> _coefficientsRepartition,
                                short _sommeTemporaire, short _scorePreneurSansAnnonces) {
        return calculateScores(_coefficientsRepartition, _sommeTemporaire, _scorePreneurSansAnnonces, relations);
    }

    static Shorts calculateScores(AbsMap<Role, Rate> _coefficientsRepartition, short _sommeTemporaire, short _scorePreneurSansAnnonces, GameTarotTeamsRelation _relations) {
        Shorts scores_ = new Shorts();
        byte nombreJoueurs_ = _relations.getNombreDeJoueurs();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            scores_.add((short) 0);
        }
        if (_sommeTemporaire == 0) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                scores_.set( joueur_, (short) 0);
            }
        } else {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                short ll_ = calculateScoresInd(_coefficientsRepartition, _sommeTemporaire, _scorePreneurSansAnnonces, _relations, joueur_);
                scores_.set(joueur_,
                        ll_);
            }
        }
        return scores_;
    }

    private static short calculateScoresInd(AbsMap<Role, Rate> _coefficientsRepartition, short _sommeTemporaire, short _scorePreneurSansAnnonces, GameTarotTeamsRelation _relations, byte _joueur) {
        Role st_ = _relations.statutDe(_joueur);
        Rate rate_ = _coefficientsRepartition.getVal(st_);
        short ll_;
        if (st_ == Role.DEFENDER) {
            ll_ = (short) Rate.multiply(rate_, new Rate(_sommeTemporaire)).ll();
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
        ll_ = (short) mult_.ll();
        return ll_;
    }

    String scoreSmallBound(){
        CustList<Role> st_ = new CustList<Role>();
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        for (byte p= 0; p < nombreJoueurs_; p++) {
            st_.add(relations.statutDe(p));
        }
        return scoreSmallBound(nombreJoueurs_,smallBound,st_);
    }
    static String scoreSmallBound(byte _nombreJoueurs, CustList<BoolVal> _smallBound, CustList<Role> _status) {
        byte p_ = joueurPetitAuBout(_nombreJoueurs, _smallBound);
        if (p_ < 0) {
            return "0";
        }
        if (_status.get(p_) == Role.DEFENDER) {
            return StringUtil.concat("(-",Long.toString(BonusTarot.SMALL_BOUND.getPoints()),")");
        }
        return Long.toString(BonusTarot.SMALL_BOUND.getPoints());
    }

    String joueurPetitAuBout(StringList _nicknames) {
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        return joueurPetitAuBout(nombreJoueurs_,smallBound,_nicknames);
    }
    static String joueurPetitAuBout(byte _nombreJoueurs, CustList<BoolVal> _smallBound, StringList _nicknames) {
        byte p_ = joueurPetitAuBout(_nombreJoueurs, _smallBound);
        if (p_ < 0) {
            return "";
        }
        return _nicknames.get(p_);
    }
    static byte joueurPetitAuBout(byte _nombreJoueurs, CustList<BoolVal> _smallBound) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (_smallBound.get(joueur_) == BoolVal.TRUE) {
                return joueur_;
            }
        }
        return -1;
    }

    public CustList<TreeMap<Miseres,Short>> getMiseresPointsForTaker() {

        CustList<TreeMap<Miseres,Short>> scores1_ = new CustList<TreeMap<Miseres,Short>>();
        byte nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreDeJoueurs_; joueur_++) {
            TreeMap<Miseres, Short> miseresPlayer_ = new TreeMap<Miseres, Short>(new MiseresComparator());
            scores1_.add(miseresPlayer_);
            if (relations.aPourDefenseur(joueur_)) {
                feedMiseres(miseresPlayer_,joueur_,-1);
            } else {
                feedMiseres(miseresPlayer_,joueur_,1);
            }
        }
        return scores1_;

    }
    private void feedMiseres(TreeMap<Miseres, Short> _miseres,int _player, int _rate) {
        feedMiseres(_miseres, _player, _rate, declaresMiseres);
    }

    static void feedMiseres(TreeMap<Miseres, Short> _miseres, int _player, int _rate, CustList<EnumList<Miseres>> _declaresMiseres) {
        for (Miseres m : _declaresMiseres.get(_player)) {
            _miseres.put(m,
                    (short) (_rate*m.getPoints()));
        }
    }

    public CustList<TreeMap<Handfuls,Short>> getHandfulsPointsForTaker(short _pointsTakerWithoutDeclaring) {

        byte nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        return getHandfulsPointsForTaker(_pointsTakerWithoutDeclaring, nombreDeJoueurs_, declaresHandfuls);

    }

    static CustList<TreeMap<Handfuls, Short>> getHandfulsPointsForTaker(short _pointsTakerWithoutDeclaring, byte _nombreDeJoueurs, CustList<EnumList<Handfuls>> _declaresHandfuls) {
        CustList<TreeMap<Handfuls,Short>> scores1_ = new CustList<TreeMap<Handfuls,Short>>();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreDeJoueurs; joueur_++) {
            scores1_.add(new TreeMap<Handfuls,Short>(new HandfulComparator()));
            for (Handfuls poignee_ : _declaresHandfuls.get(joueur_)) {
                if (_pointsTakerWithoutDeclaring >= 0) {
                    scores1_.last().put(poignee_,
                            (short) poignee_.getPoints());
                } else {
                    scores1_.last().put(poignee_,
                            (short) (-poignee_.getPoints()));
                }
            }
        }
        return scores1_;
    }

    public short additionnalBonusesAttack(BidTarot _bid) {
        byte taker_ = relations.getTaker();
        return additionnalBonusesAttack(_bid, taker_, declaresSlam, slamTaker);
    }

    static short additionnalBonusesAttack(BidTarot _bid, byte _taker, CustList<BoolVal> _declaresSlam, boolean _slamTaker) {
        boolean declSlam_ = _declaresSlam.get(_taker) == BoolVal.TRUE;
        short primesSupplementaires_ =0;
        if (_bid == BidTarot.SLAM || declSlam_) {
            if (_slamTaker) {
                primesSupplementaires_ = (short) BonusTarot.SLAM
                        .getPoints();
            } else {
                primesSupplementaires_ = (short) (-BonusTarot.SLAM
                        .getPoints() / 2);
            }
            return primesSupplementaires_;
        }
        if (_slamTaker) {
            primesSupplementaires_ = (short) (BonusTarot.SLAM
                    .getPoints() / 2);
        }
        return primesSupplementaires_;
    }

    public short additionnalBonusesDefense() {
        return additionnalBonusesDefense(slamDefense);
    }

    static short additionnalBonusesDefense(boolean _slamDefense) {
        short primesSupplementaires_ = 0;
        if (_slamDefense) {
            primesSupplementaires_ = (short) (BonusTarot.SLAM
                    .getPoints() / 2);
        }
        return primesSupplementaires_;
    }

    static short temporarySum(BidTarot _bid, short _scorePreneurSansAnnonces,
                                     CustList<TreeMap<Miseres, Short>> _miseres,
                                     CustList<TreeMap<Handfuls, Short>> _handfuls, short _primesSupplementairesAttack,
                                     short _primesSupplementairesDefense) {
        short sommeTemporaire_ = 0;
        for (TreeMap<Miseres,Short> m: _miseres) {
            sommeTemporaire_ += sum(m.values());
        }
        for (TreeMap<Handfuls,Short> h: _handfuls) {
            sommeTemporaire_ += sum(h.values());
        }
        sommeTemporaire_ += _primesSupplementairesAttack
                - _primesSupplementairesDefense;
        if (_scorePreneurSansAnnonces != 0) {
            return (short) (_bid.getCoefficient() * _scorePreneurSansAnnonces + sommeTemporaire_);
        }
        return 0;
    }

    public EnumMap<Role,Rate> coefficientsRepartition() {
        return coefficientsRepartition(relations);
    }

    static EnumMap<Role, Rate> coefficientsRepartition(GameTarotTeamsRelation _relations) {
        EnumMap<Role,Rate> coefficientsRepartition_;
        byte nombreJoueurs_ = _relations.getNombreDeJoueurs();
        coefficientsRepartition_ = new EnumMap<Role,Rate>();
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


    public EndGameState getUserState(short _scorePreneurSansAnnonces, byte _user) {
        boolean def_ = relations.aPourDefenseur(_user);
        return getUserState(_scorePreneurSansAnnonces, def_);
    }

    static EndGameState getUserState(short _scorePreneurSansAnnonces, boolean _def) {
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

    public short scoreJoueurPlisDouble(byte _joueur) {
        short nbPointsAtt_ = IndexConstants.SIZE_EMPTY;
        for (CardTarot c: getWonCardsPlayer(_joueur,BidTarot.GUARD_AGAINST)) {
            nbPointsAtt_ += c.points();
        }
        return nbPointsAtt_;
    }
    boolean slamTeam() {
        CustList<Bytes> teams_ = relations.teams();
        int noTrick_ = 0;
        for (Bytes t: teams_) {
            if (getWonTricksListTeam(tricks,t).isEmpty()) {
                noTrick_++;
            }
        }
        return noTrick_ == teams_.size() -1;
    }

    public short scoreNecessaireJoueur(byte _joueur) {


        short nombreBouts_ = nombreDeBoutsJoueur(_joueur);
        return oulderPoints.get(nombreBouts_);
    }

    public short nombreDeBoutsJoueur(byte _joueur) {
        byte nombreBouts_ = 0;
        for (CardTarot c: getWonCardsPlayer(_joueur,BidTarot.GUARD_AGAINST)) {
            if (c.estUnBout()) {
                nombreBouts_++;
            }
        }
        return nombreBouts_;
    }

    public static short differenceJoueurDouble(short _scoreNecessaireJoueur,
                                               short _scoreJoueurPlisDouble) {
        return (short) (_scoreJoueurPlisDouble - 2 * _scoreNecessaireJoueur);
    }

    public static short differenceJoueurDoubleMisere(
            short _scoreNecessaireJoueur, short _scoreJoueurPlisDouble) {
        return (short) (2 * _scoreNecessaireJoueur - _scoreJoueurPlisDouble);
    }

    public static Shorts positionsDifference(Shorts _differences) {
        Shorts positions_ = new Shorts();
        int nbDiff_ = _differences.size();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbDiff_; joueur_++) {
            positions_.add((short) 1);
            for (short difference_ : _differences) {
                if (difference_ > _differences.get(joueur_)) {
                    positions_.set(joueur_, (short) (positions_.get(joueur_)+1));
                }
            }
        }
        return positions_;
    }

    /**
     On classe les joueurs selon certains criteres pour les departager en
     changeant le tableau des positions
     */
    public Shorts changePositionsOne(Shorts _positions, boolean _pasJeuMisere) {
        return changePositionsOne(wonPlayersTeam, _positions, _pasJeuMisere);
    }

    static Shorts changePositionsOne(CustList<HandTarot> _wonPlayersTeam, Shorts _positions, boolean _pasJeuMisere) {
        if (_pasJeuMisere) {
            return changePositionsOneRate(_wonPlayersTeam,_positions,1);
        } else {
            return changePositionsOneRate(_wonPlayersTeam,_positions,-1);
        }
    }
    private static Shorts changePositionsOneRate(CustList<HandTarot> _wonPlayersTeam, Shorts _positions, int _pasJeuMisere) {
        Shorts positions_ = new Shorts(_positions);
        CustList<Shorts> groupes_ = buildGroups(positions_);
        for (Shorts groupe_ : groupes_) {
            int groupeLen_ = groupe_.size();
            for (int i = 0; i < groupeLen_; i++) {
                short joueur_ = groupe_.get(i);
                byte positionTemporaire_ = positionTemporaireOne(_wonPlayersTeam, _pasJeuMisere, groupe_, (byte) joueur_);
                positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
            }
        }
        return positions_;
    }

    private static byte positionTemporaireOne(CustList<HandTarot> _wonPlayersTeam, int _pasJeuMisere, Shorts _groupe, byte _joueur) {
        int groupeLen_ = _groupe.size();
        byte positionTemporaire_ = 1;
        HandTarot main_ = _wonPlayersTeam.get(_joueur);
        byte nombreBouts_ = (byte) main_.nombreDeBouts();
        for (int j = 0; j < groupeLen_; j++) {
            short joueur2_ = _groupe.get(j);
            HandTarot main2_ = _wonPlayersTeam.get((byte) joueur2_);
            byte nombreBouts2_ = (byte) main2_.nombreDeBouts();
            if (_pasJeuMisere *nombreBouts2_ > _pasJeuMisere *nombreBouts_) {
                positionTemporaire_++;
            }
        }
        return positionTemporaire_;
    }

    private static CustList<Shorts> getGroups(CustList<Shorts> _groupes) {
        CustList<Shorts> ensemblesPluriels_ = new CustList<Shorts>();
        for (Shorts g: _groupes) {
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
    public Shorts changePositionsTwo(Shorts _positions, boolean _pasJeuMisere) {
        return changePositionsTwo(wonPlayersTeam, _positions, _pasJeuMisere);
    }

    static Shorts changePositionsTwo(CustList<HandTarot> _wonPlayersTeam, Shorts _positions, boolean _pasJeuMisere) {
        if (_pasJeuMisere) {
            return changePositionsTwoRate(_wonPlayersTeam,_positions,1,CardTarot.vingtEtUn(),CardTarot.petit());
        } else {
            return changePositionsTwoRate(_wonPlayersTeam,_positions,-1,CardTarot.petit(),CardTarot.vingtEtUn());
        }
    }

    static Shorts changePositionsTwoRate(CustList<HandTarot> _wonPlayersTeam, Shorts _positions, int _pasJeuMisere, CardTarot _greatest, CardTarot _lowest) {
        Shorts positions_ = new Shorts(_positions);
        CustList<Shorts> groupes_ = buildGroups(positions_);
        for (Shorts groupe_ : groupes_) {
            int groupeLen_ = groupe_.size();
            for (int i = 0; i < groupeLen_; i++) {
                short joueur_ = groupe_.get(i);
                byte positionTemporaire_ = positionTemporaireTwo(_wonPlayersTeam, _pasJeuMisere, _greatest, _lowest, groupe_, (byte) joueur_);
                positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
            }
        }
        return positions_;
    }

    private static byte positionTemporaireTwo(CustList<HandTarot> _wonPlayersTeam, int _pasJeuMisere, CardTarot _greatest, CardTarot _lowest, Shorts _groupe, byte _joueur) {
        HandTarot main_ = _wonPlayersTeam.get(_joueur);
        byte nombreBouts_ = (byte) main_.nombreDeBouts();
        if (nombreBouts_ == 0) {
            return positionTemporaireTwoNoOudler(_wonPlayersTeam, _pasJeuMisere, _groupe, main_);
        }
        int groupeLen_ = _groupe.size();
        byte positionTemporaire_ = 1;
        HandTarot main2_;
        CardTarot bout_ = main_.bouts().premiereCarte();
        CardTarot bout2_;
        if (CardTarot.eq(bout_,CardTarot.excuse())) {
            for (int j = 0; j < groupeLen_; j++) {
                short joueur2_ = _groupe.get(j);
                main2_ = _wonPlayersTeam.get((byte) joueur2_);
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
            short joueur2_ = _groupe.get(j);
            main2_ = _wonPlayersTeam.get((byte) joueur2_);
            bout2_ = main2_.bouts().premiereCarte();
            if (CardTarot.eq(bout2_,CardTarot.excuse())
                    || CardTarot.eq(bout2_, _greatest)) {
                positionTemporaire_++;
            }
        }
        return positionTemporaire_;
    }

    private static byte positionTemporaireTwoNoOudler(CustList<HandTarot> _wonPlayersTeam, int _pasJeuMisere, Shorts _groupe, HandTarot _main) {
        int groupeLen_ = _groupe.size();
        byte nombreFigures_ = (byte) _main.nombreDeFigures();
        byte positionTemporaire_ = 1;
        for (int j = 0; j < groupeLen_; j++) {
            short joueur2_ = _groupe.get(j);
            HandTarot main2_ = _wonPlayersTeam.get((byte) joueur2_);
            byte nombreFigures2_ = (byte) main2_
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
    public Shorts changePositionsThree(Shorts _positions, boolean _pasJeuMisere) {
        return changePositionsThree(_positions, _pasJeuMisere, wonPlayersTeam);
    }

    static Shorts changePositionsThree(Shorts _positions, boolean _pasJeuMisere, CustList<HandTarot> _wonPlayersTeam) {

        if (_pasJeuMisere) {
            return changePositionsThreeRate(_positions, _wonPlayersTeam, 1);
        } else {
            return changePositionsThreeRate(_positions, _wonPlayersTeam, -1);
        }
    }

    private static Shorts changePositionsThreeRate(Shorts _positions, CustList<HandTarot> _wonPlayersTeam, int _rate) {
        Shorts positions_ = new Shorts(_positions);
        CustList<Shorts> groupes_ = buildGroups(positions_);
        for (Shorts groupe_ : groupes_) {
            int groupeLen_ = groupe_.size();
            for (int i = 0; i < groupeLen_; i++) {
                short joueur_ = groupe_.get(i);
                byte positionTemporaire_ = positionTemporaireThree(_wonPlayersTeam, _rate, groupe_, (byte) joueur_);
                positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
            }
        }
        return positions_;
    }

    private static byte positionTemporaireThree(CustList<HandTarot> _wonPlayersTeam, int _rate, Shorts _groupe, byte _joueur) {
        int groupeLen_ = _groupe.size();
        byte positionTemporaire_ = 1;
        HandTarot main_ = _wonPlayersTeam.get(_joueur);
        HandTarot figures_ = new HandTarot();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            figures_.ajouterCartes(main_.charCardsBySuit(couleur_));
        }
        figures_.sortCharsByGreaterPoints();
        for (int j = 0; j < groupeLen_; j++) {
            short joueur2_ = _groupe.get(j);
            HandTarot main2_ = _wonPlayersTeam.get((byte) joueur2_);
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

    private static byte incrementPosByPoints(HandTarot _charactersOne,
                                                    HandTarot _charactersTwo, byte _positionTmp, int _rate) {
        byte positionTmp_ = _positionTmp;
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
    public Shorts changePositionsFour(Shorts _positions, boolean _pasJeuMisere) {
        return changePositionsFour(_positions, _pasJeuMisere, firstTrick);
    }

    static Shorts changePositionsFour(Shorts _positions, boolean _pasJeuMisere, Ints _tricks) {
        if (_pasJeuMisere) {
            return changePositionsFourRate(_positions,1,_tricks);
        } else {
            return changePositionsFourRate(_positions,-1,_tricks);
        }
    }
    private static Shorts changePositionsFourRate(Shorts _positions, int _pasJeuMisere, Ints _tricks) {
        Shorts positions_ = new Shorts(_positions);
        CustList<Shorts> groupes_ = buildGroups(positions_);
        for (Shorts groupe_ : groupes_) {
            int groupeLen_ = groupe_.size();
            for (int i = 0; i < groupeLen_; i++) {
                short joueur_ = groupe_.get(i);
                byte positionTemporaire_ = positionTemporaireFour(_pasJeuMisere, _tricks, groupe_, joueur_);
                positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
            }
        }
        return positions_;
    }

    private static byte positionTemporaireFour(int _pasJeuMisere, Ints _tricks, Shorts _groupe, short _joueur) {
        int groupeLen_ = _groupe.size();
        byte positionTemporaire_ = 1;
        for (int j = 0; j < groupeLen_; j++) {
            short joueur2_ = _groupe.get(j);
            int indexOne_ = _tricks.get(_joueur);
            int indexTwo_ = _tricks.get(joueur2_);
            if (_pasJeuMisere *indexTwo_ <
                    _pasJeuMisere *indexOne_) {
                positionTemporaire_++;
            }
        }
        return positionTemporaire_;
    }

    static CustList<Shorts> buildGroups(Shorts _positions) {
        short indice_;
        CustList<Shorts> groupes_ = new CustList<Shorts>();
        Shorts positionsDistinctes_ = new Shorts();
        for (short position_ : _positions) {
            if (!positionsDistinctes_.containsObj(position_)) {
                positionsDistinctes_.add(position_);
            }
        }
        for (short position2_ : positionsDistinctes_) {
            groupes_.add(new Shorts());
            indice_ = 0;
            for (short position_ : _positions) {
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
        int index_ = 0;
        for (TrickTarot t: _tricks) {
            if (!t.getVuParToutJoueur()) {
                index_++;
                continue;
            }
            if (_relations.memeEquipe(t.getRamasseur(), (byte) _player) && indexOne_ == -1) {
                indexOne_ = index_;
            }
            index_++;
        }
        return indexOne_;
    }

    public Shorts coefficients(Shorts _positions) {
        DealingTarot repartition_ = relations.getRules().getDealing();
        return coefficients(_positions, repartition_);
    }

    static Shorts coefficients(Shorts _positions, DealingTarot _repartition) {
        if (_repartition == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL) {
            return fourPlayersWithoutCall(_positions);
        }
        if (_repartition == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL) {
            return sixPlayersWithoutCall(_positions);
        }
        byte nombreJoueurs_ = (byte) _repartition.getId().getNombreJoueurs();
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

    private static Shorts sixPlayers(Shorts _positions, byte _nombreJoueurs) {
        byte maxPosition_ = maxPosition(_positions);
        byte nombreLitiges_ = (byte) (_nombreJoueurs - maxPosition_ + 1);
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
            Shorts coefficients_ = new Shorts();
            for (short position_ : _positions) {
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
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(SIX_PLAYERS_WIN);
            } else {
                coefficients_.add(SIX_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Shorts sixPlayersNearNearDiff(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts sixPlayersNearDiff(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts sixPlayersDiff(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts fivePlayers(Shorts _positions, byte _nombreJoueurs) {
        byte maxPosition_ = maxPosition(_positions);
        byte nombreLitiges_ = (byte) (_nombreJoueurs - maxPosition_ + 1);
        if (nombreLitiges_ == 1) {
            return fivePlayersDiff(_positions);
        }
        if (nombreLitiges_ == 2) {
            return fivePlayersNearDiff(_positions);
        }
        Shorts coefficients_ = new Shorts();
        if (nombreLitiges_ == 3) {
            for (short position_ : _positions) {
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
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(FIVE_PLAYERS_WIN);
            } else {
                coefficients_.add(FIVE_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Shorts fivePlayersNearDiff(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts fivePlayersDiff(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts fourPlayers(Shorts _positions, byte _nombreJoueurs) {
        byte maxPosition_ = maxPosition(_positions);
        byte nombreLitiges_ = (byte) (_nombreJoueurs - maxPosition_ + 1);
        if (nombreLitiges_ == 1) {
            return fourPlayersDiff(_positions);
        }
        Shorts coefficients_ = new Shorts();
        if (nombreLitiges_ == 2) {
            for (short position_ : _positions) {
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
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(FOUR_PLAYERS_WIN);
            } else {
                coefficients_.add(FOUR_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Shorts fourPlayersDiff(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static byte maxPosition(Shorts _positions) {
        byte maxPosition_ = 0;
        for (short position_ : _positions) {
            maxPosition_ = (byte) Math.max(position_, maxPosition_);
        }
        return maxPosition_;
    }

    private static Shorts threePlayers(Shorts _positions, byte _nombreJoueurs) {
        byte maxPosition_ = maxPosition(_positions);
        byte nombreLitiges_ = (byte) (_nombreJoueurs - maxPosition_ + 1);
        Shorts coefficients_ = new Shorts();
        if (nombreLitiges_ == 1) {
            for (short position_ : _positions) {
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
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(TRHEE_PLAYERS_WIN);
            } else {
                coefficients_.add(TRHEE_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Shorts sixPlayersWithoutCall(Shorts _positions) {
        Shorts positionsDist_ = new Shorts(_positions);
        positionsDist_.removeDuplicates();
        if (positionsDist_.size() == 3) {
            Shorts coefficients_ = new Shorts();
            for (short position_ : _positions) {
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
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(SIX_PLAYERS_WITHOUT_CALL_WIN);
            } else {
                coefficients_.add(SIX_PLAYERS_WITHOUT_CALL_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Shorts fourPlayersWithoutCall(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(FOUR_PLAYERS_WITHOUT_CALL_WIN);
            } else {
                coefficients_.add(FOUR_PLAYERS_WITHOUT_CALL_LOOSE);
            }
        }
        return coefficients_;
    }

    public Shorts coefficientsMisere(Shorts _positions) {
        DealingTarot repartition_ = relations.getRules().getDealing();
        return coefficientsMisere(_positions, repartition_);
    }

    static Shorts coefficientsMisere(Shorts _positions, DealingTarot _repartition) {
        if (_repartition == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL) {
            return fourPlayersWithoutCallMisere(_positions);
        }
        if (_repartition == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL) {
            return sixPlayersWithoutCallMisere(_positions);
        }
        byte nombreJoueurs_ = (byte) _repartition.getId().getNombreJoueurs();
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

    private static Shorts sixPlayersMisere(Shorts _positions) {
        byte nombreLitiges_ = nombreLitiges(_positions);
        if (nombreLitiges_ == 1) {
            return sixPlayersDiffMisere(_positions);
        }
        if (nombreLitiges_ == 2) {
            return sixPlayersNearDiffMisere(_positions);
        }
        if (nombreLitiges_ == 3) {
            return sixPlayersNearNearDiffMisere(_positions);
        }
        Shorts coefficients_ = new Shorts();
        if (nombreLitiges_ == 4) {
            for (short position_ : _positions) {
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
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_SIX_PLAYERS_WIN);
            } else {
                coefficients_.add(MIS_SIX_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Shorts sixPlayersNearNearDiffMisere(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts sixPlayersNearDiffMisere(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts sixPlayersDiffMisere(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts fivePlayersMisere(Shorts _positions) {
        byte nombreLitiges_ = nombreLitiges(_positions);
        if (nombreLitiges_ == 1) {
            return fivePlayersDiffMisere(_positions);
        }
        if (nombreLitiges_ == 2) {
            return fivePlayersNearDiffMisere(_positions);
        }
        Shorts coefficients_ = new Shorts();
        if (nombreLitiges_ == 3) {
            for (short position_ : _positions) {
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
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_FIVE_PLAYERS_WIN);
            } else {
                coefficients_.add(MIS_FIVE_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Shorts fivePlayersNearDiffMisere(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts fivePlayersDiffMisere(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts fourPlayersMisere(Shorts _positions) {
        byte nombreLitiges_ = nombreLitiges(_positions);
        if (nombreLitiges_ == 1) {
            return fourPlayersDiffMisere(_positions);
        }
        Shorts coefficients_ = new Shorts();
        if (nombreLitiges_ == 2) {
            for (short position_ : _positions) {
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
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_FOUR_PLAYERS_LOOSE);
            } else {
                coefficients_.add(MIS_FOUR_PLAYERS_WIN);
            }
        }
        return coefficients_;
    }

    private static Shorts fourPlayersDiffMisere(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
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

    private static Shorts threePlayersMisere(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        byte nombreLitiges_ = nombreLitiges(_positions);
        if (nombreLitiges_ == 1) {
            for (short position_ : _positions) {
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
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_THREE_PLAYERS_WIN);
            } else {
                coefficients_.add(MIS_THREE_PLAYERS_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Shorts sixPlayersWithoutCallMisere(Shorts _positions) {
        Shorts positionsDist_ = new Shorts(_positions);
        positionsDist_.removeDuplicates();
        if (positionsDist_.size() == 3) {
            Shorts coefficients_ = new Shorts();
            for (short position_ : _positions) {
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
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_SIX_PLAYERS_WITHOUT_CALL_WIN);
            } else {
                coefficients_.add(MIS_SIX_PLAYERS_WITHOUT_CALL_LOOSE);
            }
        }
        return coefficients_;
    }

    private static Shorts fourPlayersWithoutCallMisere(Shorts _positions) {
        Shorts coefficients_ = new Shorts();
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add(MIS_FOUR_PLAYERS_WITHOUT_CALL_WIN);
            } else {
                coefficients_.add(MIS_FOUR_PLAYERS_WITHOUT_CALL_LOOSE);
            }
        }
        return coefficients_;
    }

    private static byte nombreLitiges(Shorts _positions) {
        byte nombreLitiges_ = 0;
        for (short position_ : _positions) {
            if (position_ == 1) {
                nombreLitiges_++;
            }
        }
        return nombreLitiges_;
    }

    public CustList<EnumMap<Handfuls,Short>> calculHandfulsScorePlayer(byte _player) {
        return calculHandfulsScorePlayer(_player, relations, declaresHandfuls);
    }

    static CustList<EnumMap<Handfuls, Short>> calculHandfulsScorePlayer(byte _player, GameTarotTeamsRelation _relations, CustList<EnumList<Handfuls>> _declaresHandfuls) {
        byte nombreDeJoueurs_ = _relations.getNombreDeJoueurs();
        CustList<EnumMap<Handfuls,Short>> scores1_ = new CustList<EnumMap<Handfuls,Short>>();
        for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {
            scores1_.add(new EnumMap<Handfuls,Short>());

            if (_relations.memeEquipe(joueur2_,_player)) {
                for (Handfuls poignee_ : _declaresHandfuls.get(joueur2_)) {
                    scores1_.last().put(poignee_,
                            (short) poignee_.getPoints());
                }
            }
        }
        return scores1_;
    }

    public CustList<EnumMap<Miseres,Short>> calculMiseresScorePlayer(byte _player) {
        return calculMiseresScorePlayer(_player, relations, declaresMiseres);
    }

    static CustList<EnumMap<Miseres, Short>> calculMiseresScorePlayer(byte _player, GameTarotTeamsRelation _relations, CustList<EnumList<Miseres>> _declaresMiseres) {
        byte nombreDeJoueurs_ = _relations.getNombreDeJoueurs();
        CustList<EnumMap<Miseres,Short>> scores1_ = new CustList<EnumMap<Miseres,Short>>();
        for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {
            scores1_.add(new EnumMap<Miseres,Short>());

            if (_relations.memeEquipe(joueur2_,_player)) {
                for (Miseres poignee_ : _declaresMiseres.get(joueur2_)) {
                    scores1_.last().put(poignee_,
                            (short) poignee_.getPoints());
                }
            }
        }
        return scores1_;
    }

    public CustList<Shorts> calculSmallLastTurnScorePlayer(byte _player) {
        return calculSmallLastTurnScorePlayer(_player, relations, smallBound);
    }

    static CustList<Shorts> calculSmallLastTurnScorePlayer(byte _player, GameTarotTeamsRelation _relations, CustList<BoolVal> _smallBound) {
        byte nombreDeJoueurs_ = _relations.getNombreDeJoueurs();
        CustList<Shorts> scores1_ = new CustList<Shorts>();
        for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {
            scores1_.add(new Shorts());

            if (_relations.memeEquipe(joueur2_, _player) && _smallBound.get(joueur2_) == BoolVal.TRUE) {
                scores1_.last().add(
                        (short) BonusTarot.SMALL_BOUND.getPoints());
            }
        }
        return scores1_;
    }

    public short primeSupplementaire(byte _joueur) {
        return primeSupplementaire(_joueur, relations, wonPlayersTeam);
    }

    static short primeSupplementaire(byte _joueur, GameTarotTeamsRelation _relations, CustList<HandTarot> _wonPlayersTeam) {
        byte nombreJoueurs_ = _relations.getNombreDeJoueurs();
        HandTarot plisAdversaires_ = new HandTarot();
        for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nombreJoueurs_; joueur2_++) {
            if (!_relations.memeEquipe(_joueur, joueur2_)) {
                plisAdversaires_.ajouterCartes(_wonPlayersTeam.get(joueur2_));
            }
        }
        if (plisAdversaires_.estVide()) {
            return (short) (BonusTarot.SLAM.getPoints() / 2);
        }
        return 0;
    }

    public Shorts calculerScoresJoueurs(Shorts _coefficients,
                                      short _differenceMaxDouble) {
        byte nombreJoueurs_ = relations.getNombreDeJoueurs();
        byte parite_ = getParite(_differenceMaxDouble, nombrePointsChien);
        int sumLoc_ = _differenceMaxDouble
                + nombrePointsChien + parite_;
        return calculerScoresJoueurs(_coefficients, nombreJoueurs_, sumLoc_);
    }

    static Shorts calculerScoresJoueurs(Shorts _coefficients, byte _nombreJoueurs, int _sumLoc) {
        Shorts scores_ = new Shorts();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            scores_.add((short)0);
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            scores_.set(joueur_,
                    (short) (4 * (_coefficients.get(joueur_) * (PTS_BASE + _sumLoc / 2))));
        }
        return scores_;
    }

    public short differenceMax(short _differenceMaxDouble) {
        return differenceMax(_differenceMaxDouble, nombrePointsChien);
    }

    static short differenceMax(short _differenceMaxDouble, byte _nombrePointsChien) {
        byte parite_ = getParite(_differenceMaxDouble, _nombrePointsChien);
        return (short) ((_differenceMaxDouble + _nombrePointsChien + parite_) / 2);
    }

    public Shorts calculerScoresJoueurs(Shorts _coefficients,
                                      short _differenceMaxDouble, Shorts _primeSupplementaire) {

        byte parite_ = getParite(_differenceMaxDouble, nombrePointsChien);
        int sumLoc_ = nombrePointsChien+parite_+_differenceMaxDouble;
        return calculerScoresJoueurs(_coefficients, _primeSupplementaire, sumLoc_, relations, declaresHandfuls, declaresMiseres, smallBound);
    }

    private static byte getParite(short _differenceMaxDouble, byte _nombrePointsChien) {
        byte parite_;
        if ((_differenceMaxDouble + _nombrePointsChien) / 2 * 2 == _differenceMaxDouble
                + _nombrePointsChien) {
            parite_ = 0;
        } else {
            parite_ = 1;
        }
        return parite_;
    }

    static Shorts calculerScoresJoueurs(Shorts _coefficients, Shorts _primeSupplementaire,
                                        int _sumLoc, GameTarotTeamsRelation _relations,
                                        CustList<EnumList<Handfuls>> _declaresHandfuls,
                                        CustList<EnumList<Miseres>> _declaresMiseres,
                                        CustList<BoolVal> _smallBound) {
        byte nombreJoueurs_ = _relations.getNombreDeJoueurs();
        Shorts scores_ = new Shorts();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            short pointsAnnoncesAutresJoueurs_ = 0;
            short pointsAnnoncesJoueur_ = pointsAnnoncesJoueur(_relations, _declaresHandfuls, _declaresMiseres, _smallBound, joueur_);
            short sommePrimeSupplementaire_ = 0;
            for (byte j_ = IndexConstants.FIRST_INDEX; j_ < nombreJoueurs_; j_++) {
                if (_relations.memeEquipe(j_, joueur_)) {
                    continue;
                }
                sommePrimeSupplementaire_ += _primeSupplementaire.get(j_);
                for (Handfuls h : _declaresHandfuls.get(j_)) {
                    pointsAnnoncesAutresJoueurs_ += h.getPoints();
                }
                for (Miseres h : _declaresMiseres.get(j_)) {
                    pointsAnnoncesAutresJoueurs_ += h.getPoints();
                }
                if (_smallBound.get(j_) == BoolVal.TRUE) {
                    pointsAnnoncesAutresJoueurs_ += BonusTarot.SMALL_BOUND.getPoints();
                }
            }
            short score_ = (short) (4 * (_coefficients.get(joueur_) * (PTS_BASE + _sumLoc / 2)
                    + (nombreJoueurs_ - 1) * (pointsAnnoncesJoueur_ + _primeSupplementaire.get(joueur_))
                    - pointsAnnoncesAutresJoueurs_ - sommePrimeSupplementaire_));
            scores_.add(
                    score_);
        }
        return scores_;
    }

    private static short pointsAnnoncesJoueur(GameTarotTeamsRelation _relations, CustList<EnumList<Handfuls>> _declaresHandfuls, CustList<EnumList<Miseres>> _declaresMiseres, CustList<BoolVal> _smallBound, byte _joueur) {
        short pointsAnnoncesJoueur_ = 0;
        for (EnumMap<Handfuls,Short> annoncesJoueur_ : calculHandfulsScorePlayer(_joueur, _relations, _declaresHandfuls)) {
            CustList<Short> values_ = annoncesJoueur_.values();
            pointsAnnoncesJoueur_ += sum(values_);
        }
        for (EnumMap<Miseres,Short> annoncesJoueur_ : calculMiseresScorePlayer(_joueur, _relations, _declaresMiseres)) {
            CustList<Short> values_ = annoncesJoueur_.values();
            pointsAnnoncesJoueur_ += sum(values_);
        }
        for (Shorts annoncesJoueur_ : calculSmallLastTurnScorePlayer(_joueur, _relations, _smallBound)) {
            pointsAnnoncesJoueur_ += sum(annoncesJoueur_);
        }
        return pointsAnnoncesJoueur_;
    }

    static short sum(CustList<Short> _ls) {
        short s_ = 0;
        for (short s: _ls) {
            s_ += s;
        }
        return s_;
    }
    private CustList<TrickTarot> getWonTricksListTeam(byte _player) {
        Bytes team_ = relations.coequipiers(_player, GameTarotTeamsRelation.tousJoueurs(relations.getNombreDeJoueurs()));
        team_.add(_player);
        return getWonTricksListTeam(tricks,team_);
    }

    private boolean aucunPliAdverseFin(byte _joueur, CustList<TrickTarot> _unionPlis) {
        byte nombreDeJoueurs_ = relations.getNombreDeJoueurs();
        Bytes partenaires_ = relations.coequipiers(_joueur,
                GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        partenaires_.add(_joueur);
        return plisTousFaitsParFin(partenaires_, _unionPlis, nombreDeJoueurs_);
    }

    private static boolean plisTousFaitsParFin(Bytes _joueurs,
                                               CustList<TrickTarot> _unionPlis, byte _nombreJoueurs) {
        Bytes autresJoueurs_ = GameTarotTeamsRelation.autresJoueurs(_joueurs, _nombreJoueurs);
        return getWonTricksListTeam(_unionPlis,autresJoueurs_).isEmpty();
    }

    HandTarot getWonTricksTeam(byte _player) {
        HandTarot cards_ = new HandTarot();
        for (TrickTarot t: getWonTricksListTeam(_player)) {
            cards_.ajouterCartes(t.getCartes());
        }
        return cards_;
    }
    static CustList<TrickTarot> getWonTricksListTeam(CustList<TrickTarot> _tricks, Bytes _players) {
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        TrickTarot lastTrick_ = _tricks.last();
        byte indExc_ = lastTrick_.joueurAyantJoue(CardTarot.EXCUSE);
        int maxExclude_ = _tricks.size();
        if (indExc_ > -1) {
            maxExclude_--;
        }
        int nbOther_ = 0;
        for (TrickTarot t: _tricks.left(maxExclude_)) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            if (!_players.containsObj(t.getRamasseur())) {
                nbOther_++;
            } else {
                tricks_.add(t);
            }
        }
        return wonTricksListTeam(_players, tricks_, lastTrick_, indExc_, nbOther_);
    }

    private static CustList<TrickTarot> wonTricksListTeam(Bytes _players, CustList<TrickTarot> _tricks, TrickTarot _lastTrick, byte _indExc, int _nbOther) {
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
