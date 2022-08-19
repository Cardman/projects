package cards.belote;

import cards.belote.enumerations.BonusBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.EndGameState;
import code.util.CustList;
import code.util.*;
import code.util.core.IndexConstants;

public final class EndBeloteGame {
    private GameBeloteTeamsRelation relations;
    private CustList<DeclaresBelote> declares;
    private Shorts declaresBeloteRebelote;
    private CustList<Boolean> wonLastTrick;
    /**Le contrat permet de dire quel va etre le deroulement
     de la partie*/
    private BidBeloteSuit bid;
    /**Ce sont les plis faits par les joueurs*/
    private CustList<TrickBelote> tricks;

    public EndBeloteGame(GameBeloteTeamsRelation _relations, CustList<DeclaresBelote> _declares,
                         Shorts _declaresBeloteRebelote, CustList<Boolean> _wonLastTrick,
                         BidBeloteSuit _bid, CustList<TrickBelote> _tricks) {
        relations = _relations;
        declares = _declares;
        declaresBeloteRebelote = _declaresBeloteRebelote;
        wonLastTrick = _wonLastTrick;
        bid = _bid;
        tricks = _tricks;
    }

    public int pointsAttaqueSansPrime() {
        return pointsSansPrime(getPlisAttaque(),bid);
    }
    public int pointsAttackWithBonus() {
        int nbPoints_ = pointsAttaqueSansPrime();
        for (Bytes t: relations.playersBelongingToSameTeam()) {
            if (t.containsObj(relations.getTaker())) {
                for (byte p: t) {
                    for (short s: pointsAnnoncesPrimes(p)) {
                        nbPoints_+= s;
                    }
                }
            }
        }
        nbPoints_ += valeurCapot();
        return nbPoints_;
    }
    public int pointsDefenseSansPrime() {
        return pointsSansPrime(getPlisDefense(),bid);
    }
    public int pointsDefenseWithBonus() {
        int nbPoints_ = pointsDefenseSansPrime();
        for (Bytes t: relations.playersBelongingToSameTeam()) {
            if (!t.containsObj(relations.getTaker())) {
                for (byte p: t) {
                    for (short s: pointsAnnoncesPrimes(p)) {
                        nbPoints_+= s;
                    }
                }
            }
        }
        return nbPoints_;
    }
    Shorts pointsAnnoncesPrimes(byte _joueur) {
        Shorts totaux_=new Shorts();
        totaux_.add(declaresBeloteRebelote.get(_joueur));
        if(wonLastTrick.get(_joueur)) {
            totaux_.add((short) BonusBelote.LAST_TRICK.getPoints());
        }
        totaux_.add((short) declares.get(_joueur).getPoints());
        return totaux_;
    }
    public int valeurCapot() {
        return valeurCapot(getPlisDefense());
    }
    static int valeurCapot(CustList<TrickBelote> _trs) {
        if(_trs.isEmpty()) {
            /*Le capot n est fait que si l attaque a fait tous les plis*/
            return 100;
        }
        return 0;
    }
    public int getDiffAttackPointsMinusDefensePoints(Shorts _scores) {
        int scoreDef_;
        int foe_ = relations.adversaires(relations.getTaker()).first();
        scoreDef_ = _scores.get(foe_);
        return _scores.get(relations.getTaker()) - scoreDef_;
    }
    public int scoreDefinitifAttaque(int _scoreTmpAttaque, int _scoreTmpDefense) {
        RulesBelote rules_ = relations.getRules();
        CustList<TrickBelote> trs_ = getPlisDefense();
        return scoreDefinitifAttaque(_scoreTmpAttaque, _scoreTmpDefense, rules_, trs_, bid);
    }

    static int scoreDefinitifAttaque(int _scoreTmpAttaque, int _scoreTmpDefense, RulesBelote _rules, CustList<TrickBelote> _trDef, BidBeloteSuit _bid) {
        if (!_rules.getComptePointsClassique()) {
            return _scoreTmpAttaque;
        }
        if (_rules.dealAll()) {
            if (_bid.getPoints() == HandBelote.pointsTotauxDixDeDer()) {
                if (!_trDef.isEmpty()) {
                    return 0;
                }
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

    public int scoreDefinitifDefense(int _scoreDefinitifAttaque, int _scoreTmpDefense) {
        return scoreDefinitifDefense(_scoreDefinitifAttaque, _scoreTmpDefense, bid);
    }

    static int scoreDefinitifDefense(int _scoreDefinitifAttaque, int _scoreTmpDefense, BidBeloteSuit _bid) {
        int sum_ = HandBelote.pointsTotauxDixDeDer(_bid);
        if (_scoreDefinitifAttaque == 0) {
            return sum_;
        }
        return _scoreTmpDefense;
    }

    Shorts scores(int _scoreDefinitifAttaque,int _scoreDefinitifDefense) {
        byte nombreJoueurs_=getNombreDeJoueurs();
        Shorts scores_=new Shorts();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreJoueurs_; joueur_++) {
            if(!relations.aPourDefenseur(joueur_)) {
                scores_.add((short)_scoreDefinitifAttaque);
            } else {
                scores_.add((short)_scoreDefinitifDefense);
            }
        }
        return scores_;
    }
    /**Renvoie 1, si l utilisateur gagne la partie,<br>
     0, s il y a match nul,<br>
     -1, sinon*/
    public EndGameState getUserState(byte _user, Shorts _scores) {
        Bytes foe_ = relations.adversaires(_user);
        return getUserState(_user, _scores, foe_);
    }

    static EndGameState getUserState(byte _user, Shorts _scores, Bytes _foe) {
        short userScore_ = _scores.get(_user);
        for (byte p: _foe) {
            if(userScore_<_scores.get(p)) {
                return EndGameState.LOOSE;
            }
            if(userScore_==_scores.get(p)) {
                return EndGameState.EQUALLITY;
            }
        }
        return EndGameState.WIN;
    }

    static int pointsSansPrime(CustList<TrickBelote> _trs, BidBeloteSuit _bid) {
        int nbPointsDef_=0;
        for (TrickBelote pli_:_trs) {
            for(CardBelote carte_:pli_) {
                nbPointsDef_+=carte_.points(_bid);
            }
        }
        return nbPointsDef_;
    }
    CustList<TrickBelote> getPlisAttaque() {
        return getTeamTrick(relations.getTaker());
    }
    CustList<TrickBelote> getPlisDefense() {
        Bytes team_ = relations.adversaires(relations.getTaker());
        return getTeamTrick(team_,tricks,bid);
    }
    private CustList<TrickBelote> getTeamTrick(byte _player) {
        Bytes team_ = relations.partenaires(_player);
        team_.add(_player);
        return getTeamTrick(team_, tricks, bid);
    }

    static CustList<TrickBelote> getTeamTrick(Bytes _team, CustList<TrickBelote> _tricks, BidBeloteSuit _bid) {
        CustList<TrickBelote> tricks_ = new CustList<TrickBelote>();
        for (TrickBelote t: _tricks) {
            if (!_team.contains(t.getRamasseur(_bid))) {
                continue;
            }
            tricks_.add(t);
        }
        return tricks_;
    }

    byte getNombreDeJoueurs() {
        return (byte) relations.getRules().getDealing().getId().getNombreJoueurs();
    }
}
