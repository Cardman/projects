package cards.belote;

import cards.belote.enumerations.BonusBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.EndGameState;
import code.util.CustList;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class EndBeloteGame {
    private final GameBeloteTeamsRelation relations;
    private final CustList<DeclaresBelote> declares;
    private final Longs declaresBeloteRebelote;
    private final CustList<BoolVal> wonLastTrick;
    /**Le contrat permet de dire quel va etre le deroulement
     de la partie*/
    private final BidBeloteSuit bid;
    /**Ce sont les plis faits par les joueurs*/
    private final CustList<TrickBelote> tricks;

    public EndBeloteGame(GameBeloteTeamsRelation _relations, CustList<DeclaresBelote> _declares,
                         Longs _declaresBeloteRebelote, CustList<BoolVal> _wonLastTrick,
                         BidBeloteSuit _bid, CustList<TrickBelote> _tricks) {
        relations = _relations;
        declares = _declares;
        declaresBeloteRebelote = _declaresBeloteRebelote;
        wonLastTrick = _wonLastTrick;
        bid = _bid;
        tricks = _tricks;
    }

    public long pointsAttaqueSansPrime() {
        return pointsSansPrime(getPlisAttaque(),bid);
    }
    public long pointsAttackWithBonus() {
        long nbPoints_ = pointsAttaqueSansPrime();
        for (Ints t: relations.playersBelongingToSameTeam()) {
            if (t.containsObj(relations.getTaker())) {
                for (int p: t) {
                    for (long s: pointsAnnoncesPrimes(p)) {
                        nbPoints_+= s;
                    }
                }
            }
        }
        nbPoints_ += valeurCapot();
        return nbPoints_;
    }
    public long pointsDefenseSansPrime() {
        return pointsSansPrime(getPlisDefense(),bid);
    }
    public long pointsDefenseWithBonus() {
        long nbPoints_ = pointsDefenseSansPrime();
        for (Ints t: relations.playersBelongingToSameTeam()) {
            if (!t.containsObj(relations.getTaker())) {
                for (int p: t) {
                    for (long s: pointsAnnoncesPrimes(p)) {
                        nbPoints_+= s;
                    }
                }
            }
        }
        return nbPoints_;
    }
    Longs pointsAnnoncesPrimes(int _joueur) {
        Longs totaux_=new Longs();
        totaux_.add(declaresBeloteRebelote.get(_joueur));
        if(wonLastTrick.get(_joueur) == BoolVal.TRUE) {
            totaux_.add(BonusBelote.LAST_TRICK.getPoints());
        }
        totaux_.add(declares.get(_joueur).getPoints());
        return totaux_;
    }
    public long valeurCapot() {
        return valeurCapot(getPlisDefense());
    }
    static long valeurCapot(CustList<TrickBelote> _trs) {
        if(_trs.isEmpty()) {
            /*Le capot n est fait que si l attaque a fait tous les plis*/
            return 100;
        }
        return 0;
    }
    public long getDiffAttackPointsMinusDefensePoints(Longs _scores) {
        long scoreDef_;
        int foe_ = relations.adversaires(relations.getTaker()).first();
        scoreDef_ = _scores.get(foe_);
        return _scores.get(relations.getTaker()) - scoreDef_;
    }
    public long scoreDefinitifAttaque(long _scoreTmpAttaque, long _scoreTmpDefense) {
        RulesBelote rules_ = relations.getRules();
        CustList<TrickBelote> trs_ = getPlisDefense();
        return scoreDefinitifAttaque(_scoreTmpAttaque, _scoreTmpDefense, rules_, trs_, bid);
    }

    static long scoreDefinitifAttaque(long _scoreTmpAttaque, long _scoreTmpDefense, RulesBelote _rules, CustList<TrickBelote> _trDef, BidBeloteSuit _bid) {
        if (!_rules.getComptePointsClassique()) {
            return _scoreTmpAttaque;
        }
        if (_rules.withBidPointsForAllPlayers()) {
            if (_bid.getPoints() == RulesBelote.MOST && !_trDef.isEmpty()) {
                return 0;
            }
            if (_scoreTmpAttaque >= _bid.getPoints()) {
                return _scoreTmpAttaque;
            }
            return 0;
        }
        if(_scoreTmpAttaque>=_scoreTmpDefense) {
            return _scoreTmpAttaque;
        }
        /*L attaque est dedans*/
        return 0;
    }

    public static long scoreDefinitifDefense(long _scoreDefinitifAttaque, long _scoreTmpDefense) {
        if (_scoreDefinitifAttaque == 0) {
            return RulesBelote.MOST;
        }
        return _scoreTmpDefense;
    }

    Longs scores(long _scoreDefinitifAttaque,long _scoreDefinitifDefense) {
        int nombreJoueurs_=getNombreDeJoueurs();
        int coeffPreneur_;
        if (nombreJoueurs_ == 3) {
            coeffPreneur_ = 2;
        } else {
            coeffPreneur_ = 1;
        }
        Longs scores_=new Longs();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
            if(!relations.aPourDefenseur(joueur_)) {
                scores_.add(coeffPreneur_*_scoreDefinitifAttaque);
            } else {
                scores_.add(_scoreDefinitifDefense);
            }
        }
        return scores_;
    }
    /**Renvoie 1, si l utilisateur gagne la partie,<br>
     0, s il y a match nul,<br>
     -1, sinon*/
    public EndGameState getUserState(int _user, Longs _scores) {
        Ints foe_ = relations.adversaires(_user);
        return getUserState(_user, _scores, foe_);
    }

    static EndGameState getUserState(int _user, Longs _scores, Ints _foe) {
        long userScore_ = _scores.get(_user);
        for (int p: _foe) {
            if(userScore_<_scores.get(p)) {
                return EndGameState.LOOSE;
            }
            if(userScore_==_scores.get(p)) {
                return EndGameState.EQUALLITY;
            }
        }
        return EndGameState.WIN;
    }

    static long pointsSansPrime(CustList<TrickBelote> _trs, BidBeloteSuit _bid) {
        long nbPointsDef_=0;
        for (TrickBelote pli_:_trs) {
            for(CardBelote carte_:pli_) {
                nbPointsDef_+=carte_.points(_bid);
            }
        }
        return nbPointsDef_;
    }
    CustList<TrickBelote> getPlisAttaque() {
        int taker_ = relations.getTaker();
        Ints team_ = relations.partenaires(taker_);
        team_.add(taker_);
        CustList<TrickBelote> all_ = tricks.left(RulesBelote.offset(relations.getRules()));
        all_.addAllElts(getTeamTrick(team_, tricks.mid(RulesBelote.offset(relations.getRules())), bid));
        return all_;
    }
    CustList<TrickBelote> getPlisDefense() {
        Ints team_ = relations.adversaires(relations.getTaker());
        return getTeamTrick(team_,tricks.mid(RulesBelote.offset(relations.getRules())),bid);
    }

    static CustList<TrickBelote> getTeamTrick(Ints _team, CustList<TrickBelote> _tricks, BidBeloteSuit _bid) {
        CustList<TrickBelote> tricks_ = new CustList<TrickBelote>();
        for (TrickBelote t: _tricks) {
            if (!_team.contains(t.getRamasseur(_bid))) {
                continue;
            }
            tricks_.add(t);
        }
        return tricks_;
    }

    int getNombreDeJoueurs() {
        return relations.getRules().getDealing().getId().getNombreJoueurs();
    }
}
