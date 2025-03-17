package cards.tarot;

import cards.consts.Role;
import cards.consts.SortedPlayers;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class GameTarotTeamsRelation {

    private final int taker;
    private final Ints calledPlayers;
    private final CustList<CustList<BoolVal>> confidence;
    private final RulesTarot rules;

    public GameTarotTeamsRelation(int _taker, Ints _calledPlayers,
                                  CustList<CustList<BoolVal>> _confidence, RulesTarot _rules) {
        taker = _taker;
        calledPlayers = _calledPlayers;
        confidence = _confidence;
        rules = _rules;
    }

    void determinerConfiances() {
        int nbPl_ = getRules().getDealing().getId().getNombreJoueurs();
        for (int p = 0; p < nbPl_; p++) {
            determinerConfiance(p,nbPl_);
        }
    }

    public CustList<Ints> teams() {
        CustList<Ints> teams_ =new CustList<Ints>();
        int nombreDeJoueurs_ = getNombreDeJoueurs();
        Ints all_ =tousJoueurs(nombreDeJoueurs_);
        Ints done_ =new Ints();
        while(!all_.isEmpty()) {
            int pl_ = all_.first();
            Ints parts_ = coequipiers(pl_, all_);
            parts_.add(pl_);
            teams_.add(parts_);
            done_.addAllElts(parts_);
            for (int p:parts_) {
                all_.removeAllLong(p);
            }
        }
        return teams_;
    }
    int getNombreDeJoueurs() {
        return rules.getDealing().getId().getNombreJoueurs();
    }
    static boolean contientJoueurs(Ints _joueurs1, Ints _joueurs2) {
        for (int e: _joueurs2) {
            if (!_joueurs1.contains(e)) {
                return false;
            }
        }
        return true;
    }
    static Ints intersectionJoueurs(Ints _joueurs1, Ints _joueurs2) {
        return SortedPlayers.intersectionJoueurs(_joueurs1, _joueurs2);
    }
    static Ints autresJoueurs(Ints _joueurs,
                               int _nombreJoueurs) {
        return SortedPlayers.autresJoueurs(_joueurs, _nombreJoueurs);
    }

    static Ints tousJoueurs(int _nombreJoueurs) {
        Ints joueurs_ = new Ints();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static boolean justeApresJoueur(int _joueur, int _joueurPrecedent,
                                            int _nombreJoueurs) {
        return _joueur == (_joueurPrecedent + 1) % _nombreJoueurs;
    }

    Ints equipe(int _joueur) {
        Ints pls_ = tousCoequipiers(_joueur);
        pls_.add(_joueur);
        return pls_;
    }
    Ints tousCoequipiers(int _joueur) {
        int nombreDeJoueurs_ = getNombreDeJoueurs();
        return coequipiers(_joueur,tousJoueurs(nombreDeJoueurs_));
    }
    Ints coequipiers(int _joueur, Ints _joueurs) {
        Ints equipe_ = new Ints();
        for (int joueur_ : _joueurs) {
            if (joueur_ == _joueur) {
                continue;
            }
            if (memeEquipe(_joueur, joueur_)) {
                equipe_.add(joueur_);
            }
        }
        return equipe_;
    }

    Ints adversaires(int _joueur, Ints _joueurs) {
        Ints equipe_ = new Ints();
        for (int joueur_ : _joueurs) {
            if (!memeEquipe(_joueur, joueur_)) {
                equipe_.add(joueur_);
            }
        }
        return equipe_;
    }

    /** Utilise l'attribut confiance */
    Ints joueursNonConfiance(int _joueur, Ints _joueurs) {
        Ints joueurs_ = new Ints();
        for (int joueur_ : _joueurs) {
            if (confidence.get(_joueur).get(joueur_) == BoolVal.TRUE) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }

    /** Utilise l'attribut confiance */
    Ints joueursConfiance(int _joueur, Ints _joueurs) {
        Ints joueurs_ = new Ints();
        for (int joueur_ : _joueurs) {
            if (joueur_ == _joueur || confidence.get(_joueur).get(joueur_) != BoolVal.TRUE) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    boolean memeEquipe(int _numero1, int _numero2) {
        if (existePreneur()) {
            if (aPourDefenseur(_numero1)) {
                return aPourDefenseur(_numero2);
            }
            return !aPourDefenseur(_numero2);
        }
        return confiance(_numero1, _numero2);
    }

    void determinerConfiance(int _numero, int _nombreJoueurs) {
        if (!aPourDefenseur(_numero)) {
            attacker(_numero, _nombreJoueurs);
        } else {
            defender(_numero, _nombreJoueurs);
        }
    }

    private void defender(int _numero, int _nombreJoueurs) {
        faireMefiance(_numero, taker);
        for (int joueur_: calledPlayers) {
            faireMefiance(_numero, joueur_);
        }
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (joueur_ != taker && !calledPlayers.containsObj(joueur_)) {
                faireConfiance(_numero, joueur_);
            }
        }
    }

    private void attacker(int _numero, int _nombreJoueurs) {
        faireConfiance(_numero, taker);
        for (int joueur_: calledPlayers) {
            faireConfiance(_numero, joueur_);
        }
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (joueur_ != taker && !calledPlayers.containsObj(joueur_)) {
                faireMefiance(_numero, joueur_);
            }
        }
    }

    static boolean allKnownCalledPlayers(HandTarot _calledCards,
            IdMap<Suit,CustList<HandTarot>> _cartesCertaines, int _nbPlayers) {
        boolean appelesTousConnus_;
        appelesTousConnus_ = true;
        for(CardTarot c: _calledCards) {
            boolean trouve_ = false;
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
                if (_cartesCertaines.getVal(c.getId().getCouleur())
                        .get(joueur_).contient(c)) {
                    trouve_ = true;
                    break;
                }
            }
            if(!trouve_) {
                appelesTousConnus_ = false;
                break;
            }
        }
        return appelesTousConnus_;
    }

    public boolean isSameTeam(Ints _players) {
        int nbPlayers_ = _players.size();
        for (int i = IndexConstants.SECOND_INDEX; i<nbPlayers_; i++) {
            if (!memeEquipe(_players.getPrev(i), _players.get(i))) {
                return false;
            }
        }
        return true;
    }
    boolean adversaireAFaitPlis(int _numero, CustList<TrickTarot> _tricks) {
        return !aucunPliAdverse(_numero, _tricks);
    }
    boolean aucunPliAdverse(int _joueur, CustList<TrickTarot> _unionPlis) {
        int nombreDeJoueurs_ = getNombreDeJoueurs();
        Ints partenaires_ = coequipiers(_joueur,
                GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        partenaires_.add(_joueur);
        return GameTarotTrickInfo.plisTousFaitsPar(partenaires_, _unionPlis, nombreDeJoueurs_);
    }

    void faireConfiance(int _joueur, int _enjoueur) {
        confidence.get(_joueur).set( _enjoueur, BoolVal.TRUE);
    }
    void faireMefiance(int _joueur, int _enjoueur) {
        confidence.get(_joueur).set( _enjoueur, BoolVal.FALSE);
    }
    boolean confiance(int _joueur, int _enjoueur) {
        return confidence.get(_joueur).get(_enjoueur) == BoolVal.TRUE;
    }
    boolean isVirtualTaker(int _pl) {
        if (!existePreneur()) {
            return true;
        }
        return taker == _pl;
    }
    boolean existePreneur() {
        return existePreneur(taker);
    }
    static boolean existePreneur(int _t) {
        return _t > -1;
    }
    Ints getCalledPlayers() {
        return calledPlayers;
    }

    int getTaker() {
        return taker;
    }

    RulesTarot getRules() {
        return rules;
    }

    public boolean aPourDefenseur(int _numero) {
        return _numero != taker && statutDe(_numero) != Role.CALLED_PLAYER;
    }
    public Role statutDe(int _numero) {
        return statutDe(_numero, taker, calledPlayers);
    }

    public static Role statutDe(int _numero, int _taker, Ints _calledPlayers) {
        if (_numero == _taker) {
            return Role.TAKER;
        }
        if (_calledPlayers.containsObj(_numero)) {
            return Role.CALLED_PLAYER;
        }
        return Role.DEFENDER;
    }

    public CustList<CustList<BoolVal>> getConfidence() {
        return confidence;
    }
}
