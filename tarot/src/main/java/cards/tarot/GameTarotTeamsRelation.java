package cards.tarot;

import cards.consts.Status;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.*;

public final class GameTarotTeamsRelation {

    private byte taker;
    private Numbers<Byte> calledPlayers;
    private CustList<BooleanList> confidence;
    private RulesTarot rules;

    public GameTarotTeamsRelation(byte _taker, Numbers<Byte> _calledPlayers,
                                  CustList<BooleanList> _confidence, RulesTarot _rules) {
        taker = _taker;
        calledPlayers = _calledPlayers;
        confidence = _confidence;
        rules = _rules;
    }

    CustList<Numbers<Byte>> teams() {
        CustList<Numbers<Byte>> teams_ =new CustList<Numbers<Byte>>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        Numbers<Byte> all_ =tousJoueurs(nombreDeJoueurs_);
        Numbers<Byte> done_ =new Numbers<Byte>();
        while(!all_.isEmpty()) {
            byte pl_ = all_.first();
            if (done_.contains(pl_)) {
                all_.remove(0);
                continue;
            }
            Numbers<Byte> parts_ = coequipiers(pl_, all_);
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
        return (byte) rules.getRepartition().getNombreJoueurs();
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

    Numbers<Byte> autresJoueurs(byte _numero) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        return autresJoueurs(new Numbers<Byte>(_numero), nombreDeJoueurs_);
    }
    static Numbers<Byte> tousJoueurs(byte _nombreJoueurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static boolean justeApresJoueur(byte _joueur, byte _joueurPrecedent,
                                            byte _nombreJoueurs) {
        return _joueur == (_joueurPrecedent + 1) % _nombreJoueurs;
    }

    Numbers<Byte> tousCoequipiers(byte _joueur) {
        Numbers<Byte> equipe_ = new Numbers<Byte>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ : tousJoueurs(nombreDeJoueurs_)) {
            if (joueur_ == _joueur) {
                continue;
            }
            if (memeEquipe(_joueur, joueur_)) {
                equipe_.add(joueur_);
            }
        }
        return equipe_;
    }
    Numbers<Byte> coequipiers(byte _joueur, Numbers<Byte> _joueurs) {
        Numbers<Byte> equipe_ = new Numbers<Byte>();
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
    public CustList<Numbers<Byte>> playersBelongingToSameTeam() {
        CustList<Numbers<Byte>> teams_ = new CustList<Numbers<Byte>>();
        if (existePreneur()) {
            Numbers<Byte> takerTeam_ = tousCoequipiers(taker);
            takerTeam_.add(taker);
            teams_.add(takerTeam_);
            byte nombreDeJoueurs_ = getNombreDeJoueurs();
            Numbers<Byte> takerFoeTeam_ = adversaires(taker, tousJoueurs(nombreDeJoueurs_));
            teams_.add(takerFoeTeam_);
        }
        return teams_;
    }
    Numbers<Byte> adversaires(byte _joueur, Numbers<Byte> _joueurs) {
        Numbers<Byte> equipe_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (!memeEquipe(_joueur, joueur_)) {
                equipe_.add(joueur_);
            }
        }
        return equipe_;
    }

    /** Utilise l'attribut confiance */
    Numbers<Byte> joueursNonConfiance(byte _joueur, Numbers<Byte> _joueurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (confidence.get(_joueur).get(joueur_)) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }

    /** Utilise l'attribut confiance */
    Numbers<Byte> joueursConfiance(byte _joueur, Numbers<Byte> _joueurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (joueur_ == _joueur) {
                continue;
            }
            if (!confidence.get(_joueur).get(joueur_)) {
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
        return confiance(_numero1, _numero2) || _numero1 == _numero2;
    }

    void determinerConfiance(byte _numero, byte _nombreJoueurs) {
        if (!aPourDefenseur(_numero)) {
            faireConfiance(_numero, taker);
            for (byte joueur_: calledPlayers) {
                faireConfiance(_numero, joueur_);
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
                if (joueur_ != taker && !calledPlayers.containsObj(joueur_)) {
                    faireMefiance(_numero, joueur_);
                }
            }
        } else {
            faireMefiance(_numero, taker);
            for (byte joueur_: calledPlayers) {
                faireMefiance(_numero, joueur_);
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
                if (joueur_ != taker && !calledPlayers.containsObj(joueur_)) {
                    faireConfiance(_numero, joueur_);
                }
            }
        }
    }
    boolean allKnownCalledPlayers(HandTarot _calledCards,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, byte _nbPlayers) {
        boolean appelesTousConnus_;
        appelesTousConnus_ = true;
        for(CardTarot c: _calledCards) {
            boolean trouve_ = false;
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
                if (_cartesCertaines.getVal(c.couleur())
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

    public boolean isSameTeam(Numbers<Byte> _players) {
        int nbPlayers_ = _players.size();
        for (byte i=CustList.SECOND_INDEX;i<nbPlayers_;i++) {
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
        Numbers<Byte> partenaires_ = coequipiers(_joueur,
                GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        partenaires_.add(_joueur);
        return GameTarotTrickInfo.plisTousFaitsPar(partenaires_, _unionPlis, nombreDeJoueurs_);
    }

    void faireConfiance(byte _joueur, byte _enjoueur) {
        confidence.get(_joueur).set( _enjoueur, true);
    }
    void faireMefiance(byte _joueur, byte _enjoueur) {
        confidence.get(_joueur).set( _enjoueur, false);
    }
    boolean confiance(byte _joueur, byte _enjoueur) {
        return confidence.get(_joueur).get(_enjoueur);
    }
    boolean existePreneur() {
        return taker > -1;
    }

    Numbers<Byte> getCalledPlayers() {
        return calledPlayers;
    }

    byte getTaker() {
        return taker;
    }

    RulesTarot getRules() {
        return rules;
    }
    boolean existeAppele() {
        return !calledPlayers.isEmpty();
    }
    public boolean aPourDefenseur(byte _numero) {
        return _numero != taker && statutDe(_numero) != Status.CALLED_PLAYER;
    }
    public Status statutDe(byte _numero) {
        if (_numero == taker) {
            return Status.TAKER;
        }
        if (calledPlayers.containsObj(_numero)) {
            return Status.CALLED_PLAYER;
        }
        return Status.DEFENDER;
    }
}
