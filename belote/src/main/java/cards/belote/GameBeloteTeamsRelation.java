package cards.belote;

import cards.consts.Status;
import code.util.CustList;
import code.util.Numbers;

public final class GameBeloteTeamsRelation {
    private byte taker;
    private RulesBelote rules;

    public GameBeloteTeamsRelation(byte _taker, RulesBelote _rules) {
        taker = _taker;
        rules = _rules;
    }
    byte playerAfter(byte _player) {
        return rules.getRepartition().getNextPlayer(_player);
    }
    Numbers<Byte> adversaires(byte _numero) {
        Numbers<Byte> adversaires_ = new Numbers<Byte>();
        byte player_ = playerAfter(_numero);
        adversaires_.add(player_);
        player_ = playerAfter(player_);
        player_ = playerAfter(player_);
        adversaires_.add(player_);
        return adversaires_;
    }
    Numbers<Byte> partenaires(byte _numero) {
        Numbers<Byte> partenaires_ = new Numbers<Byte>();
        byte player_ = playerAfter(_numero);
        player_ = playerAfter(player_);
        partenaires_.add(player_);
        return partenaires_;
    }
    //methode utilisee pour l'affichage
    Status statutDe(byte _numero) {
        if(_numero==taker) {
            return Status.TAKER;
        }
        if(partenaires(taker).containsObj(_numero)) {
            return Status.CALLED_PLAYER;
        }
        return Status.DEFENDER;
    }


    /**@throws NullPointerException si un des arguments est null*/
    static boolean egaliteJoueurs(Numbers<Byte> _joueurs1, Numbers<Byte> _joueurs2) {
        return Numbers.equalsSetBytes(_joueurs1,_joueurs2);
    }
    boolean isSameTeam(Numbers<Byte> _players) {
        int nbPlayers_ = _players.size();
        for (byte i = CustList.SECOND_INDEX; i<nbPlayers_; i++) {
            if (!memeEquipe(_players.getPrev(i), _players.get(i))) {
                return false;
            }
        }
        return true;
    }
    boolean memeEquipe(byte _numero1, byte _numero2) {
        if (aPourDefenseur(_numero1)) {
            return aPourDefenseur(_numero2);
        }
        return !aPourDefenseur(_numero2);
    }
    public CustList<Numbers<Byte>> playersBelongingToSameTeam() {
        CustList<Numbers<Byte>> teams_ = new CustList<Numbers<Byte>>();
        Numbers<Byte> takerTeam_ = partenaires(taker);
        takerTeam_.add(taker);
        teams_.add(takerTeam_);
        Numbers<Byte> takerFoeTeam_ = adversaires(taker);
        teams_.add(takerFoeTeam_);
        return teams_;
    }
    boolean aPourDefenseur(byte _numero) {
        return _numero!=taker&&!partenaires(taker).containsObj(_numero);
    }
    static Numbers<Byte> intersectionJoueurs(Numbers<Byte> _joueurs1, Numbers<Byte> _joueurs2) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte j : _joueurs1) {
            if(!_joueurs2.containsObj(j)) {
                continue;
            }
            joueurs_.add(j);
        }
        return joueurs_;
    }

    static Numbers<Byte> autresJoueurs(Numbers<Byte> _joueurs,
                                               byte _nombreJoueurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (!_joueurs.containsObj(joueur_)) {
                joueurs_.add(joueur_);
            }
        }
        return joueurs_;
    }
    byte getNombreDeJoueurs() {
        return (byte) rules.getRepartition().getNombreJoueurs();
    }
    RulesBelote getRules() {
        return rules;
    }

    byte getTaker() {
        return taker;
    }
}
