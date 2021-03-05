package cards.belote;

import cards.belote.enumerations.BeloteTrumpPartner;
import cards.consts.Role;
import code.util.CustList;
import code.util.*;
import code.util.core.IndexConstants;

public final class GameBeloteTeamsRelation {
    private final byte taker;
    private final RulesBelote rules;

    public GameBeloteTeamsRelation(byte _taker, RulesBelote _rules) {
        taker = _taker;
        rules = _rules;
    }
    byte playerAfter(byte _player) {
        return rules.getRepartition().getNextPlayer(_player);
    }
    Bytes adversaires(byte _numero) {
        Bytes adversaires_ = new Bytes();
        byte player_ = playerAfter(_numero);
        adversaires_.add(player_);
        player_ = playerAfter(player_);
        player_ = playerAfter(player_);
        adversaires_.add(player_);
        return adversaires_;
    }
    public Bytes partenaires(byte _numero) {
        Bytes partenaires_ = new Bytes();
        byte player_ = playerAfter(_numero);
        player_ = playerAfter(player_);
        partenaires_.add(player_);
        return partenaires_;
    }
    //methode utilisee pour l'affichage
    public Role statutDe(byte _numero) {
        if(_numero==taker) {
            return Role.TAKER;
        }
        if(partenaires(taker).containsObj(_numero)) {
            return Role.CALLED_PLAYER;
        }
        return Role.DEFENDER;
    }

    boolean isSameTeam(Bytes _players) {
        int nbPlayers_ = _players.size();
        for (byte i = IndexConstants.SECOND_INDEX; i<nbPlayers_; i++) {
            if (!memeEquipe(_players.getPrev(i), _players.get(i))) {
                return false;
            }
        }
        return true;
    }
    public boolean memeEquipe(byte _numero1, byte _numero2) {
        if (aPourDefenseur(_numero1)) {
            return aPourDefenseur(_numero2);
        }
        return !aPourDefenseur(_numero2);
    }
    CustList<Bytes> playersBelongingToSameTeam() {
        CustList<Bytes> teams_ = new CustList<Bytes>();
        Bytes takerTeam_ = partenaires(taker);
        takerTeam_.add(taker);
        teams_.add(takerTeam_);
        Bytes takerFoeTeam_ = adversaires(taker);
        teams_.add(takerFoeTeam_);
        return teams_;
    }
    boolean aPourDefenseur(byte _numero) {
        return _numero!=taker&&!partenaires(taker).containsObj(_numero);
    }
    static Bytes intersectionJoueurs(Bytes _joueurs1, Bytes _joueurs2) {
        Bytes joueurs_ = new Bytes();
        for (byte j : _joueurs1) {
            if(!_joueurs2.containsObj(j)) {
                continue;
            }
            joueurs_.add(j);
        }
        return joueurs_;
    }

    static Bytes autresJoueurs(Bytes _joueurs,
                                               byte _nombreJoueurs) {
        Bytes joueurs_ = new Bytes();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
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

    public boolean surCoupeObligatoirePartenaire() {
        return rules.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP
                ||rules.getGestionCoupePartenaire()==BeloteTrumpPartner.OVERTRUMP_ONLY;
    }
    public boolean sousCoupeObligatoirePartenaire() {
        return rules.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP
                || rules.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_ONLY;
    }
    boolean sousCoupeObligatoireAdversaire() {
        return rules.getSousCoupeAdv();
    }
    byte getTaker() {
        return taker;
    }
}
