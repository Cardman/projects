package cards.belote;

import cards.belote.enumerations.BonusBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.consts.EndGameState;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EqList;
import code.util.*;

public final class EndBeloteGame {
    private GameBeloteTeamsRelation relations;
    private EqList<DeclareHandBelote> declares;
    private EqList<HandBelote> declaresBeloteRebelote;
    private BooleanList wonLastTrick;
    /**Le contrat permet de dire quel va etre le deroulement
     de la partie*/
    private BidBeloteSuit bid;
    /**Ce sont les plis faits par les joueurs*/
    private CustList<TrickBelote> tricks;

    public EndBeloteGame(GameBeloteTeamsRelation _relations, EqList<DeclareHandBelote> _declares,
                         EqList<HandBelote> _declaresBeloteRebelote, BooleanList _wonLastTrick,
                         BidBeloteSuit _bid, CustList<TrickBelote> _tricks) {
        relations = _relations;
        declares = _declares;
        declaresBeloteRebelote = _declaresBeloteRebelote;
        wonLastTrick = _wonLastTrick;
        bid = _bid;
        tricks = _tricks;
    }

    public int pointsAttaqueSansPrime() {
        int nbPointsAtt_=0;
        for (TrickBelote pli_:getPlisAttaque()) {
            for(CardBelote carte_:pli_) {
                nbPointsAtt_+=carte_.points(bid);
            }
        }
        return nbPointsAtt_;
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
        int nbPointsDef_=0;
        for (TrickBelote pli_:getPlisDefense()) {
            for(CardBelote carte_:pli_) {
                nbPointsDef_+=carte_.points(bid);
            }
        }
        return nbPointsDef_;
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
        if(bid.getCouleurDominante()) {
            if(declaresBeloteRebelote.get(_joueur).contientCartes(GameBeloteCommonPlaying.cartesBeloteRebelote(bid))) {
                totaux_.add((short) DeclaresBeloteRebelote.BELOTE_REBELOTE.getPoints());
            }
        }
        if(wonLastTrick.get(_joueur)) {
            totaux_.add((short) BonusBelote.LAST_TRICK.getPoints());
        }
        totaux_.add((short) declares.get(_joueur).getAnnonce().getPoints());
        return totaux_;
    }
    public int valeurCapot() {
        if(getPlisDefense().isEmpty()) {
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
        if (!relations.getRules().getComptePointsClassique()) {
            return _scoreTmpAttaque;
        }
        if (relations.getRules().dealAll()) {
            if (bid.getPoints() == HandBelote.pointsTotauxDixDeDer()) {
                if (!getPlisDefense().isEmpty()) {
                    return 0;
                }
            }
            if (_scoreTmpAttaque >= bid.getPoints()) {
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
        int sum_ = HandBelote.pointsTotauxDixDeDer(bid);
        if (_scoreDefinitifAttaque == 0) {
            return sum_;
        }
        return _scoreTmpDefense;
    }
    Shorts scores(int _scoreDefinitifAttaque,int _scoreDefinitifDefense) {
        byte nombreJoueurs_=getNombreDeJoueurs();
        Shorts scores_=new Shorts();
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
            if(!relations.aPourDefenseur(joueur_)) {
                scores_.set(joueur_,(short)_scoreDefinitifAttaque);
            } else {
                scores_.set(joueur_,(short)_scoreDefinitifDefense);
            }
        }
        return scores_;
    }
    /**Renvoie 1, si l utilisateur gagne la partie,<br>
     0, s il y a match nul,<br>
     -1, sinon*/
    public EndGameState getUserState(byte _user, Shorts _scores) {
        short userScore_ = _scores.get(_user);
        boolean slam_ = getPlisDefense().isEmpty();
        for (byte p: relations.adversaires(_user)) {
            if(userScore_<_scores.get(p)) {
                return EndGameState.LOOSE;
            }
            if(userScore_==_scores.get(p) && !slam_) {
                return EndGameState.EQUALLITY;
            }
        }
        return EndGameState.WIN;
    }

    CustList<TrickBelote> getPlisAttaque() {
        CustList<TrickBelote> tricks_ = new CustList<TrickBelote>();
        for (TrickBelote t: tricks) {
            if (relations.aPourDefenseur(t.getRamasseur(bid))) {
                continue;
            }
            tricks_.add(t);
        }
        return tricks_;
    }
    CustList<TrickBelote> getPlisDefense() {
        CustList<TrickBelote> tricks_ = new CustList<TrickBelote>();
        for (TrickBelote t: tricks) {
            if (!relations.aPourDefenseur(t.getRamasseur(bid))) {
                continue;
            }
            tricks_.add(t);
        }
        return tricks_;
    }
    byte getNombreDeJoueurs() {
        return (byte) relations.getRules().getRepartition().getNombreJoueurs();
    }
}
