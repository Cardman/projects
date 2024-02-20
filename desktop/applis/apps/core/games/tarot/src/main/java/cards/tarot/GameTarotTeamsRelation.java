package cards.tarot;

import cards.consts.Role;
import cards.consts.SortedPlayers;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class GameTarotTeamsRelation {

    private final byte taker;
    private final Bytes calledPlayers;
    private final CustList<CustList<BoolVal>> confidence;
    private final RulesTarot rules;

    public GameTarotTeamsRelation(byte _taker, Bytes _calledPlayers,
                                  CustList<CustList<BoolVal>> _confidence, RulesTarot _rules) {
        taker = _taker;
        calledPlayers = _calledPlayers;
        confidence = _confidence;
        rules = _rules;
    }

    void determinerConfiances() {
        byte nbPl_ = (byte) getRules().getDealing().getId().getNombreJoueurs();
        for (byte p = 0; p < nbPl_; p++) {
            determinerConfiance(p,nbPl_);
        }
    }

    public CustList<Bytes> teams() {
        CustList<Bytes> teams_ =new CustList<Bytes>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        Bytes all_ =tousJoueurs(nombreDeJoueurs_);
        Bytes done_ =new Bytes();
        while(!all_.isEmpty()) {
            byte pl_ = all_.first();
            Bytes parts_ = coequipiers(pl_, all_);
            parts_.add(pl_);
            teams_.add(parts_);
            done_.addAllElts(parts_);
            for (byte p:parts_) {
                all_.removeAllLong(p);
            }
        }
        return teams_;
    }
    byte getNombreDeJoueurs() {
        return (byte) rules.getDealing().getId().getNombreJoueurs();
    }
    static boolean contientJoueurs(Bytes _joueurs1, Bytes _joueurs2) {
        for (byte e: _joueurs2) {
            if (!_joueurs1.contains(e)) {
                return false;
            }
        }
        return true;
    }
    static Bytes intersectionJoueurs(Bytes _joueurs1, Bytes _joueurs2) {
        return SortedPlayers.intersectionJoueurs(_joueurs1, _joueurs2);
    }
    static Bytes autresJoueurs(Bytes _joueurs,
                                               byte _nombreJoueurs) {
        return SortedPlayers.autresJoueurs(_joueurs, _nombreJoueurs);
    }

    static Bytes tousJoueurs(byte _nombreJoueurs) {
        Bytes joueurs_ = new Bytes();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static boolean justeApresJoueur(byte _joueur, byte _joueurPrecedent,
                                            byte _nombreJoueurs) {
        return _joueur == (_joueurPrecedent + 1) % _nombreJoueurs;
    }

    Bytes equipe(byte _joueur) {
        Bytes pls_ = tousCoequipiers(_joueur);
        pls_.add(_joueur);
        return pls_;
    }
    Bytes tousCoequipiers(byte _joueur) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        return coequipiers(_joueur,tousJoueurs(nombreDeJoueurs_));
    }
    Bytes coequipiers(byte _joueur, Bytes _joueurs) {
        Bytes equipe_ = new Bytes();
        for (byte joueur_ : _joueurs) {
            if (joueur_ == _joueur) {
                continue;
            }
            if (memeEquipe(_joueur, joueur_)) {
                equipe_.add(joueur_);
            }
        }
        return equipe_;
    }

    Bytes adversaires(byte _joueur, Bytes _joueurs) {
        Bytes equipe_ = new Bytes();
        for (byte joueur_ : _joueurs) {
            if (!memeEquipe(_joueur, joueur_)) {
                equipe_.add(joueur_);
            }
        }
        return equipe_;
    }

    /** Utilise l'attribut confiance */
    Bytes joueursNonConfiance(byte _joueur, Bytes _joueurs) {
        Bytes joueurs_ = new Bytes();
        for (byte joueur_ : _joueurs) {
            if (confidence.get(_joueur).get(joueur_) == BoolVal.TRUE) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }

    /** Utilise l'attribut confiance */
    Bytes joueursConfiance(byte _joueur, Bytes _joueurs) {
        Bytes joueurs_ = new Bytes();
        for (byte joueur_ : _joueurs) {
            if (joueur_ == _joueur || confidence.get(_joueur).get(joueur_) != BoolVal.TRUE) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    boolean memeEquipe(byte _numero1, byte _numero2) {
        if (existePreneur()) {
            if (aPourDefenseur(_numero1)) {
                return aPourDefenseur(_numero2);
            }
            return !aPourDefenseur(_numero2);
        }
        return confiance(_numero1, _numero2);
    }

    void determinerConfiance(byte _numero, byte _nombreJoueurs) {
        if (!aPourDefenseur(_numero)) {
            attacker(_numero, _nombreJoueurs);
        } else {
            defender(_numero, _nombreJoueurs);
        }
    }

    private void defender(byte _numero, byte _nombreJoueurs) {
        faireMefiance(_numero, taker);
        for (byte joueur_: calledPlayers) {
            faireMefiance(_numero, joueur_);
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (joueur_ != taker && !calledPlayers.containsObj(joueur_)) {
                faireConfiance(_numero, joueur_);
            }
        }
    }

    private void attacker(byte _numero, byte _nombreJoueurs) {
        faireConfiance(_numero, taker);
        for (byte joueur_: calledPlayers) {
            faireConfiance(_numero, joueur_);
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (joueur_ != taker && !calledPlayers.containsObj(joueur_)) {
                faireMefiance(_numero, joueur_);
            }
        }
    }

    static boolean allKnownCalledPlayers(HandTarot _calledCards,
            IdMap<Suit,CustList<HandTarot>> _cartesCertaines, byte _nbPlayers) {
        boolean appelesTousConnus_;
        appelesTousConnus_ = true;
        for(CardTarot c: _calledCards) {
            boolean trouve_ = false;
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
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

    public boolean isSameTeam(Bytes _players) {
        int nbPlayers_ = _players.size();
        for (byte i = IndexConstants.SECOND_INDEX; i<nbPlayers_; i++) {
            if (!memeEquipe(_players.getPrev(i), _players.get(i))) {
                return false;
            }
        }
        return true;
    }
    boolean adversaireAFaitPlis(byte _numero, CustList<TrickTarot> _tricks) {
        return !aucunPliAdverse(_numero, _tricks);
    }
    boolean aucunPliAdverse(byte _joueur, CustList<TrickTarot> _unionPlis) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        Bytes partenaires_ = coequipiers(_joueur,
                GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        partenaires_.add(_joueur);
        return GameTarotTrickInfo.plisTousFaitsPar(partenaires_, _unionPlis, nombreDeJoueurs_);
    }

    void faireConfiance(byte _joueur, byte _enjoueur) {
        confidence.get(_joueur).set( _enjoueur, BoolVal.TRUE);
    }
    void faireMefiance(byte _joueur, byte _enjoueur) {
        confidence.get(_joueur).set( _enjoueur, BoolVal.FALSE);
    }
    boolean confiance(byte _joueur, byte _enjoueur) {
        return confidence.get(_joueur).get(_enjoueur) == BoolVal.TRUE;
    }
    boolean isVirtualTaker(byte _pl) {
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
    Bytes getCalledPlayers() {
        return calledPlayers;
    }

    byte getTaker() {
        return taker;
    }

    RulesTarot getRules() {
        return rules;
    }

    public boolean aPourDefenseur(byte _numero) {
        return _numero != taker && statutDe(_numero) != Role.CALLED_PLAYER;
    }
    public Role statutDe(byte _numero) {
        return statutDe(_numero, taker, calledPlayers);
    }

    public static Role statutDe(byte _numero, byte _taker, Bytes _calledPlayers) {
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
