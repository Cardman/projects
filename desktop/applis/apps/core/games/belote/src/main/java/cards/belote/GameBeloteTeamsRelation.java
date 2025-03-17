package cards.belote;

import cards.belote.enumerations.BeloteTrumpPartner;
import cards.consts.Role;
import cards.consts.SortedPlayers;
import code.util.CustList;
import code.util.*;
import code.util.core.IndexConstants;

public final class GameBeloteTeamsRelation {
    private final int taker;
    private final RulesBelote rules;

    public GameBeloteTeamsRelation(int _taker, RulesBelote _rules) {
        taker = _taker;
        rules = _rules;
    }
    int playerAfter(int _player) {
        return rules.getDealing().getId().getNextPlayer(_player);
    }
    Ints adversaires(int _numero) {
        int nb_ = rules.getDealing().getId().getNombreJoueurs();
        if (nb_ <= 3) {
            if (_numero == taker) {
                return autresJoueurs(Ints.newList(taker), nb_);
            }
            return Ints.newList(taker);
        }
        Ints adversaires_ = new Ints();
        int player_ = playerAfter(_numero);
        adversaires_.add(player_);
        player_ = playerAfter(player_);
        player_ = playerAfter(player_);
        adversaires_.add(player_);
        return adversaires_;
    }
    public Ints partenaires(int _numero) {
        int nb_ = rules.getDealing().getId().getNombreJoueurs();
        if (nb_ <= 3) {
            if (_numero == taker) {
                return new Ints();
            }
            return autresJoueurs(Ints.newList(taker,_numero), nb_);
        }
        Ints partenaires_ = new Ints();
        int player_ = playerAfter(_numero);
        player_ = playerAfter(player_);
        partenaires_.add(player_);
        return partenaires_;
    }
    //methode utilisee pour l'affichage
    public Role statutDe(int _numero) {
        if(_numero==taker) {
            return Role.TAKER;
        }
        if(partenaires(taker).containsObj(_numero)) {
            return Role.CALLED_PLAYER;
        }
        return Role.DEFENDER;
    }

    boolean isSameTeam(Ints _players) {
        int nbPlayers_ = _players.size();
        for (int i = IndexConstants.SECOND_INDEX; i<nbPlayers_; i++) {
            if (!memeEquipe(_players.getPrev(i), _players.get(i))) {
                return false;
            }
        }
        return true;
    }
    public boolean memeEquipe(int _numero1, int _numero2) {
        if (aPourDefenseur(_numero1)) {
            return aPourDefenseur(_numero2);
        }
        return !aPourDefenseur(_numero2);
    }
    CustList<Ints> playersBelongingToSameTeam() {
        CustList<Ints> teams_ = new CustList<Ints>();
        Ints takerTeam_ = partenaires(taker);
        takerTeam_.add(taker);
        teams_.add(takerTeam_);
        Ints takerFoeTeam_ = adversaires(taker);
        teams_.add(takerFoeTeam_);
        return teams_;
    }
    boolean aPourDefenseur(int _numero) {
        return _numero!=taker&&!partenaires(taker).containsObj(_numero);
    }
    static Ints intersectionJoueurs(Ints _joueurs1, Ints _joueurs2) {
        return SortedPlayers.intersectionJoueurs(_joueurs1, _joueurs2);
    }

    static Ints autresJoueurs(Ints _joueurs,
                               int _nombreJoueurs) {
        return SortedPlayers.autresJoueurs(_joueurs, _nombreJoueurs);
    }
    int getNombreDeJoueurs() {
        return rules.getDealing().getId().getNombreJoueurs();
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
    int getTaker() {
        return taker;
    }
}
