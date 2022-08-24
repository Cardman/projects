package cards.tarot;

import cards.consts.PossibleTrickWinner;
import cards.consts.Role;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.maths.Rate;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

final class GameTarotTrickHypothesis {

    private GameTarotTrickHypothesis(){
    }
    static void hypothesesRepartitionsJoueurs(GameTarotTeamsRelation _teamReal, HandTarot _calledCards,
                                              CustList<TrickTarot> _plisFaits, byte _numero,
                                              EnumMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                              EnumMap<Suit, CustList<HandTarot>> _cartesCertaines) {
        Role st_ = _teamReal.statutDe(_numero);
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        CustList<TrickTarot> fullTricksProg_ = new CustList<TrickTarot>();
        for (TrickTarot t:_plisFaits) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            fullTricksProg_.add(t);
        }
        boolean appelesTousConnus_ = _teamReal.allKnownCalledPlayers(_calledCards,_cartesCertaines,
                nombreJoueurs_);
        Bytes joueursNonConfiancePresqueSure_ = new Bytes();
        Bytes possibleAlly_ = new Bytes();
        possibleAlly_.add(_numero);
        if(appelesTousConnus_) {
            feedJoueursNonConfiancePresqueSurePossibleAlly(_teamReal, _calledCards, _cartesCertaines, st_, joueursNonConfiancePresqueSure_, possibleAlly_);
        }
        trumpAceFirstPlayedTrump(_teamReal, _numero, fullTricksProg_, joueursNonConfiancePresqueSure_, possibleAlly_);
        trumpAceStarter(_teamReal, _numero, fullTricksProg_, joueursNonConfiancePresqueSure_, possibleAlly_);
        trumpAce(_teamReal, _numero, _cartesPossibles, fullTricksProg_, joueursNonConfiancePresqueSure_, possibleAlly_);
        charsFollowSuit(_teamReal, _numero, fullTricksProg_, joueursNonConfiancePresqueSure_, possibleAlly_);
        charsDiscard(_teamReal, _numero, fullTricksProg_, joueursNonConfiancePresqueSure_, possibleAlly_);
        completeJoueursNonConfiancePresqueSurePossibleAlly(_teamReal, _calledCards, _cartesPossibles, st_, joueursNonConfiancePresqueSure_, possibleAlly_);
        possibleAlly_.removeDuplicates();
        if (st_ == Role.DEFENDER) {
            joueursNonConfiancePresqueSure_.add(_teamReal.getTaker());
        }
        joueursNonConfiancePresqueSure_.removeObj(_numero);
        joueursNonConfiancePresqueSure_.removeDuplicates();
        changeConfidence(_teamReal, _numero, joueursNonConfiancePresqueSure_, possibleAlly_);
    }

    private static void completeJoueursNonConfiancePresqueSurePossibleAlly(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, Role _st, Bytes _joueursNonConfiancePresqueSure, Bytes _possibleAlly) {
        if (_st == Role.DEFENDER) {
            completeJoueursNonConfiancePresqueSurePossibleAllyDefender(_teamReal, _calledCards, _cartesPossibles, _possibleAlly);
        } else {
            completeJoueursNonConfiancePresqueSurePossibleAllyAttacker(_teamReal, _calledCards, _cartesPossibles, _joueursNonConfiancePresqueSure);
        }
    }

    private static void completeJoueursNonConfiancePresqueSurePossibleAllyAttacker(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, Bytes _joueursNonConfiancePresqueSure) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for(CardTarot c: _calledCards) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (_cartesPossibles.getVal(c.getId().getCouleur())
                        .get(joueur_).estVide()) {
                    _joueursNonConfiancePresqueSure.add(joueur_);
                }
            }
        }
    }

    private static void completeJoueursNonConfiancePresqueSurePossibleAllyDefender(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, Bytes _possibleAlly) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for(CardTarot c: _calledCards) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ == _teamReal.getTaker()) {
                    continue;
                }
                if (_cartesPossibles.getVal(c.getId().getCouleur())
                        .get(joueur_).estVide()) {
                    _possibleAlly.add(joueur_);
                }
            }
        }
    }

    private static void changeConfidence(GameTarotTeamsRelation _teamReal, byte _numero, Bytes _joueursNonConfiancePresqueSure, Bytes _possibleAlly) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        Role st_ = _teamReal.statutDe(_numero);
        Bytes allConf_ = new Bytes();
        allConf_.addAllElts(_possibleAlly);
        allConf_.addAllElts(_joueursNonConfiancePresqueSure);
        if (NumberUtil.equalsSetBytes(allConf_, GameTarotTeamsRelation.tousJoueurs(nombreJoueurs_))) {
            for (byte b: _possibleAlly) {
                _teamReal.faireConfiance(_numero,b);
            }
            for (byte b: _joueursNonConfiancePresqueSure) {
                _teamReal.faireMefiance(_numero,b);
            }
        } else if (st_ == Role.DEFENDER) {
            for (byte b: _possibleAlly) {
                _teamReal.faireConfiance(_numero,b);
            }
        } else {
            if (_possibleAlly.size() < 3) {
                for (byte b: _possibleAlly) {
                    _teamReal.faireConfiance(_numero,b);
                }
            }
        }
    }

    private static void charsDiscard(GameTarotTeamsRelation _teamReal, byte _numero, CustList<TrickTarot> _fullTricksProg, Bytes _joueursNonConfiancePresqueSure, Bytes _possibleAlly) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        Role st_ = _teamReal.statutDe(_numero);
        for (byte j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant les figures defaussese sur demande d'atout
            for(Suit c: Suit.couleursOrdinaires()) {
                HandTarot cartesCouleurJouees_ = new HandTarot();
                byte ramasseurVirtuel_ = ramasseurVirtuelCharsDiscard(_teamReal, _fullTricksProg, j, c, cartesCouleurJouees_);
                if (ramasseurVirtuel_ == -1 || onlyOnePlayedCard(_fullTricksProg, nombreJoueurs_, cartesCouleurJouees_, j)) {
                    continue;
                }
                res(j, _possibleAlly, ramasseurVirtuel_, _teamReal, st_, _joueursNonConfiancePresqueSure, _numero);
            }

        }
    }

    private static byte ramasseurVirtuelCharsDiscard(GameTarotTeamsRelation _teamReal, CustList<TrickTarot> _fullTricksProg, byte _j, Suit _c, HandTarot _cartesCouleurJouees) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        byte ramasseurVirtuel_ = -1;
        for(TrickTarot p: filter(_fullTricksProg, nombreJoueurs_, _j)) {
            Suit couleurDemandee_ = p.couleurDemandee();
            if (couleurDemandee_ == Suit.TRUMP) {
                CardTarot carteJouee_ = p.carteDuJoueur(_j, nombreJoueurs_);
                //Premier tour d'atout
                if (carteJouee_.getId().getCouleur() == _c && carteJouee_.isCharacter()) {//carteJouee est une carte defaussee de la couleur c
                    //carteJouee est une figure defaussee de la couleur c
                    ramasseurVirtuel_ = p.getRamasseur(nombreJoueurs_);
                    //ramasseurVirtuel != j, car une defause ne permet JAMAIS de prendre la main
                    _cartesCouleurJouees.ajouter(carteJouee_);
                    break;
                }
            }
        }
        return ramasseurVirtuel_;
    }

    private static void charsFollowSuit(GameTarotTeamsRelation _teamReal, byte _numero, CustList<TrickTarot> _fullTricksProg, Bytes _joueursNonConfiancePresqueSure, Bytes _possibleAlly) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        Role st_ = _teamReal.statutDe(_numero);
        for (byte j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant les figures jouees au premier tour d'une couleur ordinaire demandee
            for(Suit c: Suit.couleursOrdinaires()) {
                HandTarot cartesCouleurJouees_ = new HandTarot();
                byte ramasseurVirtuel_ = charsFollowSuitRamasseurVirtuel(_teamReal, _fullTricksProg, j, c, cartesCouleurJouees_);
                if (ramasseurVirtuel_ == -1 || onlyOnePlayedCard(_fullTricksProg, nombreJoueurs_, cartesCouleurJouees_, j)) {
                    continue;
                }
                res(j, _possibleAlly, ramasseurVirtuel_, _teamReal, st_, _joueursNonConfiancePresqueSure, _numero);
            }

        }
    }

    private static byte charsFollowSuitRamasseurVirtuel(GameTarotTeamsRelation _teamReal, CustList<TrickTarot> _fullTricksProg, byte _j, Suit _c, HandTarot _cartesCouleurJouees) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        byte ramasseurVirtuel_ = -1;
        for(TrickTarot p: filter(_fullTricksProg, nombreJoueurs_, _j)) {
            Suit couleurDemandee_ = p.couleurDemandee();
            if (couleurDemandee_ == _c) {
                CardTarot carteJouee_ = p.carteDuJoueur(_j, nombreJoueurs_);
                //Premier tour a la couleur demandee c
                if (carteJouee_.getId().getCouleur() == _c && carteJouee_.isCharacter()) {//carteJouee est une carte de la couleur demandee au premier tour
                    //carteJouee est une figure de la couleur demandee au premier tour
                    ramasseurVirtuel_ = majRamasseurVirtuel(_teamReal, _j, _c, _cartesCouleurJouees, ramasseurVirtuel_, p, carteJouee_);
                }
                break;
            }
        }
        return ramasseurVirtuel_;
    }

    private static byte majRamasseurVirtuel(GameTarotTeamsRelation _teamReal, byte _j, Suit _c, HandTarot _cartesCouleurJouees, byte _ramasseurVirtuel, TrickTarot _p, CardTarot _carteJouee) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        byte ramasseurVirtuel_ = _ramasseurVirtuel;
        boolean carteJoueeRamassee_ = false;
        byte max_ = _carteJouee.strength(_c);
        for(byte j2_: _p.joueursAyantJoueAvant(_j, _teamReal.getRules().getDealing())) {
            CardTarot carteJoueeAvant_ = _p.carteDuJoueur(j2_, nombreJoueurs_);
            if(carteJoueeAvant_.strength(_c) < max_) {
                continue;
            }
            max_ = carteJoueeAvant_.strength(_c);
            ramasseurVirtuel_ = j2_;
            carteJoueeRamassee_ = true;
        }
        if (carteJoueeRamassee_) {
            _cartesCouleurJouees.ajouter(_carteJouee);
        }
        return ramasseurVirtuel_;
    }

    private static void trumpAce(GameTarotTeamsRelation _teamReal, byte _numero, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, CustList<TrickTarot> _fullTricksProg, Bytes _joueursNonConfiancePresqueSure, Bytes _possibleAlly) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for (byte j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
            byte ramasseur_ = trumpAceWin(_teamReal, _cartesPossibles, _fullTricksProg, j);
            if (ramasseur_ != -1) {
                if (ramasseur_ == -2) {
                    break;
                }
                trumpAceFeed(_teamReal, _numero, _joueursNonConfiancePresqueSure, _possibleAlly, j);
            }
        }
    }

    private static byte trumpAceWin(GameTarotTeamsRelation _teamReal, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, CustList<TrickTarot> _fullTricksProg, byte _j) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        byte ramasseur_ = -1;
        boolean petitJoueDemandeAtout_ = false;
        boolean defausse_ = _cartesPossibles.getVal(Suit.TRUMP).get(_j).estVide();
        for(TrickTarot p: filter(_fullTricksProg, nombreJoueurs_, _j)) {
            CardTarot carte_ = p.carteDuJoueur(_j, nombreJoueurs_);
            if (petitJoueDemandeAtout_ && !defausse_) {
                return -2;
            }
            if (followTrumpAce(p, carte_)) {
                ramasseur_ = p.getRamasseur(nombreJoueurs_);
                if (ramasseur_ != _teamReal.getTaker()) {
                    return -2;
                }
                petitJoueDemandeAtout_ = true;
            }
        }
        return ramasseur_;
    }

    private static void trumpAceFeed(GameTarotTeamsRelation _teamReal, byte _numero, Bytes _joueursNonConfiancePresqueSure, Bytes _possibleAlly, byte _j) {
        Role st_ = _teamReal.statutDe(_numero);
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        //le ramasseur du pli et le joueur du Petit (entameur) sont dans la meme equipe
        if(st_ == Role.DEFENDER) {
            //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
            //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
            //de plus numero est un defenseur
            Bytes found_ = new Bytes();
            found_.add(_j);
            found_.add(_numero);
            for (byte b: GameTarotTeamsRelation.autresJoueurs(found_, nombreJoueurs_)) {
                _joueursNonConfiancePresqueSure.add(b);
            }
            _possibleAlly.add(_j);
            // confiance en j
        } else {
            //!aPourDefenseur(numero) ==> numero == preneur ==> mefiance de j
            _joueursNonConfiancePresqueSure.add(_j);
        }
    }

    private static boolean followTrumpAce(TrickTarot _p, CardTarot _carte) {
        return _carte.getId().getCouleur() == Suit.TRUMP && _p.couleurDemandee() == Suit.TRUMP && _carte == CardTarot.petit();
    }

    private static void trumpAceStarter(GameTarotTeamsRelation _teamReal, byte _numero, CustList<TrickTarot> _fullTricksProg, Bytes _joueursNonConfiancePresqueSure, Bytes _possibleAlly) {
        Role st_ = _teamReal.statutDe(_numero);
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for (byte j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant l'entameur du Petit
            byte ramasseur_ = trumpAceStarterWin(_fullTricksProg, nombreJoueurs_, j);
            if (ramasseur_ != -1) {
                if (ramasseur_ == -2) {
                    break;
                }
                res(j, _possibleAlly, ramasseur_, _teamReal, st_, _joueursNonConfiancePresqueSure, _numero);
            }
        }
    }

    private static byte trumpAceStarterWin(CustList<TrickTarot> _fullTricksProg, byte _nombreJoueurs, byte _j) {
        byte ramasseur_ = -1;
        for(TrickTarot p: filter(_fullTricksProg, _nombreJoueurs, _j)) {
            if (trumpAceAsFirstPlayedCardTrick(_nombreJoueurs, _j, p)) {
                //le joueur j a entame
                //Entame du Petit par le joueur j
                ramasseur_ = p.getRamasseur(_nombreJoueurs);
                if (ramasseur_ == _j) {
                    //Si le joueur j a entame et ramasse le pli avec le Petit,
                    //alors on passe au joueur suivant
                    ramasseur_ = -2;
                }
                //ramasseur != -1 && passerAuJoueurSuivant = false
                break;
            }
        }
        return ramasseur_;
    }

    private static boolean trumpAceAsFirstPlayedCardTrick(byte _nombreJoueurs, byte _j, TrickTarot _p) {
        return _j == _p.getEntameur() && _p.carteDuJoueur(_j, _nombreJoueurs) == CardTarot.petit();
    }

    private static void trumpAceFirstPlayedTrump(GameTarotTeamsRelation _teamReal, byte _numero, CustList<TrickTarot> _fullTricksProg, Bytes _joueursNonConfiancePresqueSure, Bytes _possibleAlly) {
        Role st_ = _teamReal.statutDe(_numero);
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for (byte j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
            byte ramasseur_ = trumpAceFirstPlayedTrumpPlayer(_teamReal, _fullTricksProg, j);
            if(ramasseur_ == -1) {
                continue;
            }
            res(j, _possibleAlly, ramasseur_, _teamReal, st_, _joueursNonConfiancePresqueSure, _numero);
        }
    }

    private static byte trumpAceFirstPlayedTrumpPlayer(GameTarotTeamsRelation _teamReal, CustList<TrickTarot> _fullTricksProg, byte _j) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        //iteration sur la confiance du joueur numero en le joueur j
        //vis a vis du Petit joue en premier atout mais jamais virtuellement maitre
        //le Petit doit etre ramasse par le plus grand atout encore en jeu
        //ramasse par un autre atout
        byte ramasseur_ = -1;
        for(TrickTarot p: filter(_fullTricksProg, nombreJoueurs_, _j)) {
            CardTarot carte_ = p.carteDuJoueur(_j, nombreJoueurs_);
            if (carte_.getId().getCouleur() == Suit.TRUMP) {
                //arret de la boucle sur les plis des que le joueur a joue un atout
                if (carte_ == CardTarot.petit() && petitRamasse(_teamReal, _j, p, carte_)) {
                    //carte == CarteTarot.petit()
                    ramasseur_ = p.getRamasseur(nombreJoueurs_);
                    //ramasseur != -1 && passerAuJoueurSuivant = false
                }
                break;
            }
        }
        return ramasseur_;
    }

    private static boolean petitRamasse(GameTarotTeamsRelation _teamReal, byte _j, TrickTarot _p, CardTarot _carte) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        boolean petitRamasse_ = false;
        Suit couleurDemandee_ = _p.couleurDemandee();
        byte forcePetit_ = _carte.strength(couleurDemandee_);
        for (byte j2_ : _p.joueursAyantJoueAvant(_j, _teamReal.getRules().getDealing())) {
            if (_p.carteDuJoueur(j2_, nombreJoueurs_).strength(couleurDemandee_) >= forcePetit_) {//la carte du joueur j2 est un atout ramassant temporairement le Petit
                petitRamasse_ = true;
                break;
            }
        }
        return petitRamasse_;
    }

    private static void feedJoueursNonConfiancePresqueSurePossibleAlly(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, Role _st, Bytes _joueursNonConfiancePresqueSure, Bytes _possibleAlly) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        if (_st == Role.DEFENDER) {
            feedJoueursNonConfiancePresqueSurePossibleAllyDefender(_teamReal, _calledCards, _cartesCertaines, _possibleAlly);
        } else {
            feedJoueursNonConfiancePresqueSurePossibleAllyAttacker(_teamReal, _calledCards, _cartesCertaines, _possibleAlly);
        }
        for (byte b: GameTarotTeamsRelation.autresJoueurs(_possibleAlly, nombreJoueurs_)) {
            _joueursNonConfiancePresqueSure.add(b);
        }
    }

    private static void feedJoueursNonConfiancePresqueSurePossibleAllyAttacker(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, Bytes _possibleAlly) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for(CardTarot c: _calledCards) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (_cartesCertaines.getVal(c.getId().getCouleur())
                        .get(joueur_).contient(c)) {
                    _possibleAlly.add(joueur_);
                }
            }
        }
    }

    private static void feedJoueursNonConfiancePresqueSurePossibleAllyDefender(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, Bytes _possibleAlly) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for(CardTarot c: _calledCards) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ == _teamReal.getTaker()) {
                    continue;
                }
                if (!_cartesCertaines.getVal(c.getId().getCouleur())
                        .get(joueur_).contient(c)) {
                    _possibleAlly.add(joueur_);
                }
            }
        }
    }

    private static boolean onlyOnePlayedCard(CustList<TrickTarot> _full, byte _nbPl, HandTarot _played, byte _cur) {
        boolean autreCarteCouleurJouee_ = false;
        boolean figureJouee_ = false;
        CardTarot premiereFigureJouee_ = _played.premiereCarte();
        for(TrickTarot p: filter(_full,_nbPl,_cur)) {
            CardTarot carteJouee_ = p.carteDuJoueur(_cur,_nbPl);
            if (carteJouee_.getId().getCouleur() == premiereFigureJouee_.getId().getCouleur()) {
                if (carteJouee_ != premiereFigureJouee_) {
                    if (figureJouee_ && carteJouee_.points() <= premiereFigureJouee_.points()) {
                        //la carte jouee est une carte inferieure a la premiere en points mais de la meme couleur
                        autreCarteCouleurJouee_ = true;
                        break;
                    }
                } else {
                    figureJouee_ = true;
                }
            }
        }
        return !autreCarteCouleurJouee_;
    }
    private static CustList<TrickTarot> filter(CustList<TrickTarot> _tricks, byte _nbPl, byte _current) {
        CustList<TrickTarot> l_ = new CustList<TrickTarot>();
        for (TrickTarot t: _tricks) {
            if (!t.aJoue(_current,_nbPl)) {
                continue;
            }
            l_.add(t);
        }
        return l_;
    }
    private static void res(byte _current, Bytes _possibleAlly, byte _ram, GameTarotTeamsRelation _teamReal, Role _st,
                            Bytes _potentialFoesNearlySure, byte _numero) {
        //ramasseur != j && ramasseur != -1
        byte nbPl_ = _teamReal.getNombreDeJoueurs();
        Bytes all_ = GameTarotTeamsRelation.tousJoueurs(nbPl_);
        if(_st == Role.DEFENDER) {
            if(_ram == _teamReal.getTaker() || _current == _teamReal.getTaker()) {
                all_.removeObj(_current);
                all_.removeObj(_ram);
                all_.removeObj(_teamReal.getTaker());
                _possibleAlly.addAllElts(all_);
                return;
            }
            //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
            //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
            //de plus numero est un defenseur
            Bytes found_ = new Bytes();
            found_.add(_current);
            found_.add(_ram);
            _possibleAlly.add(_current);
            _possibleAlly.add(_ram);
            return;
        }
        //!aPourDefenseur(numero) ==> numero == preneur ==> j == appele
        if(_numero == _ram) {
            Bytes found_ = new Bytes();
            found_.add(_current);
            _possibleAlly.add(_current);
            return;
        }
        addPotentialFoePlayers(_potentialFoesNearlySure, _current,
                _ram);
    }
    private static void addPotentialFoePlayers(Bytes _potentialFoesNearlySure,
                                               byte _otherPlayer, byte _leader) {
        _potentialFoesNearlySure.add(_leader);
        _potentialFoesNearlySure.add(_otherPlayer);
    }
    /**
     Renvoie un entier 0 si joueur de non confiance qui va faire le pli 1 si
     joueur de confiance va faire le pli et -1 sinon
     @param cartes_possibles
     l'ensemble des cartes probablement possedees par les joueurs
     @param cartes_certaines
     l'ensemble des cartes surement possedees par les joueurs
     @param ramasseur_virtuel
     le joueur, qui sans les cartes jouees par les derniers joueurs
     du pli est ramasseur
     @param joueurs_non_joue
     l'ensemble des joueurs n'ayant pas encore joue leur carte
     @param joueurs_confiance
     l'ensemble des joueurs de confiance
     @param joueurs_non_confiance
     l'ensemble des joueurs de non confiance
     @param _numero
     le numero du joueur qui va jouer
     @param couleur_appelee
     la couleur appelee si elle existe -1 sinon
     @param carte_appelee_jouee
     une valeur booleenne vrai si et seulement si la carte appelee
     est jouee
     */
    static PossibleTrickWinner equipeQuiVaFairePli(
            TarotInfoPliEnCours _info) {
        if (Suit.couleursOrdinaires().containsObj(_info.getProgressingTrick().couleurDemandee())) {
            return getPossibleTrickWinnerNormalSuit(_info);
        }
        return getPossibleTrickWinnerTrump(_info);
    }

    static PossibleTrickWinner getPossibleTrickWinnerNormalSuit(TarotInfoPliEnCours _info) {
        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        byte nbPlayers_ = _info.getNbPlayers();
        CardTarot card_ = _info.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nbPlayers_);
        if (card_.getId().getCouleur() == Suit.TRUMP) {
            return getPossibleTrickWinnerTrumpSuit(_info);
        }
        return getPossibleTrickWinnerNoTrump(_info);
    }

    static PossibleTrickWinner getPossibleTrickWinnerTrumpSuit(TarotInfoPliEnCours _info) {
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        CardTarot card_ = _info.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), _info.getNbPlayers());
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        byte player_ = _info.getCurrentPlayer();
        Bytes joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Bytes joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
        Suit couleurDemandee_ = _info.getProgressingTrick().couleurDemandee();
        /*
        Le pli est coupe
        */
        if (!cartesCertaines_.getVal(couleurDemandee_).get(player_).estVide()
                || cartesCertaines_.getVal(Suit.TRUMP).get(player_).estVide()
                || cartesCertaines_.getVal(Suit.TRUMP).get(player_).premiereCarte()
                .strength(couleurDemandee_) < card_.strength(couleurDemandee_)) {
            return possibleTrickWinnerTrumpSuitCannotLeadTrick(_info, couleurDemandee_);
        }
        /*
        Fin
        !cartesCertaines.get(couleurDemandee).get(numero).estVide()||
        cartesCertaines
        .get(1).get(numero).estVide()||cartesCertaines.get
        (1).get(numero)
        .premiereCarte().getforceJeu(couleurDemandee)<carteForte.getforceJeu(couleurDemandee) (fin test de
        possibilite pour le joueur numero de prendre le pli)
        */
        /*
        Le joueur numero peut prendre la main en surcoupant le ramasseur
        virtuel
        */
        CardTarot cardPl_ = cartesCertaines_.getVal(Suit.TRUMP).get(player_)
                .premiereCarte();
        Bytes other_ = new Bytes(joueursNonJoue_);
        other_.removeObj(player_);
        if (ramasseurBatAdvSur(other_,
                couleurDemandee_, cardPl_, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.UNKNOWN;
        }
        /*
        On cherche les joueurs de confiance battant de maniere certaine
        les joueurs de non confiance n'ayant pas joue ou possedant des
        cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
        de non confiance n'ayant pas joue
        */
        if (existeJoueurBatAdvNum(joueursNonConfianceNonJoue_,
                joueursConfianceNonJoue_, player_, couleurDemandee_,
                cartesPossibles_, cartesCertaines_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*
        On cherche les joueurs de non confiance battant de maniere
        certaine les joueurs de confiance n'ayant pas joue ou possedant
        des cartes que les joueurs ayant joue n'ont pas ainsi que les
        joueurs de non confiance n'ayant pas joue
        */
        if (existeJoueurBatAdvNum(joueursConfianceNonJoue_,
                joueursNonConfianceNonJoue_, player_, couleurDemandee_,
                cartesPossibles_, cartesCertaines_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    private static PossibleTrickWinner possibleTrickWinnerTrumpSuitCannotLeadTrick(TarotInfoPliEnCours _info, Suit _couleurDemandee) {
        EnumList<Suit> couleursAppelees_ = _info.getCalledSuits();
        boolean carteAppeleeJouee_ =_info.isCarteAppeleeJouee();
        byte player_ = _info.getCurrentPlayer();
        CardTarot card_ = _info.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), _info.getNbPlayers());
        Bytes joueursConfiance_ = _info.getJoueursConfiance();
        Bytes joueursNonConfiance_ = _info.getJoueursNonConfiance();
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        Bytes joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, joueursNonConfiance_);
        Bytes joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, joueursConfiance_);
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        /*
    Le joueur
    numero ne
    peut pas
    prendre la
    main
    */
        if (joueursConfiance_.containsObj(_info.getRamasseurVirtuel())) {
            return possibleTrickWinnerTrumpSuitCannotLeadTrickPartners(_info, _couleurDemandee, card_);
        }
            /*
            ramasseurVirtuel n'est pas un joueur de confiance pour le
            joueur numero
            */
        if (couleursAppelees_.containsObj(_couleurDemandee) && !carteAppeleeJouee_
                && player_ == _info.getTaker()) {
            joueursConfianceNonJoue_ = new Bytes();
        }
            /*
            On cherche a savoir si le ramasseur virtuel (joueur de non
            confiance) bat tous les joueurs de confiance n'ayant pas joue
            */
        if (ramasseurBatAdvSur(joueursConfianceNonJoue_,
                _couleurDemandee, card_, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
            /*
            On cherche les joueurs de non confiance battant de maniere
            certaine les joueurs de confiance n'ayant pas joue ou
            possedant des cartes que les joueurs ayant joue n'ont pas
            ainsi que les joueurs de non confiance n'ayant pas joue
            */
        if (existeJoueurNonJoueBattantAdv(
                joueursConfianceNonJoue_,
                joueursNonConfianceNonJoue_, _couleurDemandee,
                cartesPossibles_, cartesCertaines_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
            /*
            On cherche les joueurs de confiance battant de maniere
            certaine les joueurs de non confiance n'ayant pas joue ou
            possedant des cartes que les joueurs ayant joue n'ont pas
            ainsi que les joueurs de non confiance n'ayant pas joue
            */
        if (existeJoueurAdvRamBatAdvSur(
                joueursNonConfianceNonJoue_,
                joueursConfianceNonJoue_, _couleurDemandee,
                card_, cartesPossibles_, cartesCertaines_)) {
            return PossibleTrickWinner.TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
        /* Fin joueurDeConfiance.contains(ramasseurVirtuel) */
    }

    private static PossibleTrickWinner possibleTrickWinnerTrumpSuitCannotLeadTrickPartners(TarotInfoPliEnCours _info, Suit _couleurDemandee, CardTarot _card) {
        EnumList<Suit> couleursAppelees_ = _info.getCalledSuits();
        boolean carteAppeleeJouee_ =_info.isCarteAppeleeJouee();
        Bytes joueursConfiance_ = _info.getJoueursConfiance();
        Bytes joueursNonConfiance_ = _info.getJoueursNonConfiance();
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        Bytes joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, joueursNonConfiance_);
        Bytes joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, joueursConfiance_);
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        if (couleursAppelees_.containsObj(_couleurDemandee)
                && !carteAppeleeJouee_ && joueursNonConfiance_.containsObj(_info.getTaker())) {
            /* The player, probably called by the current taker
            and still owing one called card of the current led suit,
            must follow a card belonging to the current demanded suit.*/
            Bytes joueursNonConfiancePreneur_ = new Bytes();
            for (byte j: joueursNonConfianceNonJoue_) {
                if (j != _info.getTaker()) {
                    continue;
                }
                joueursNonConfiancePreneur_.add(j);
            }
            joueursNonConfianceNonJoue_ = joueursNonConfiancePreneur_;
        }
            /*
            On cherche a savoir si le ramasseur virtuel (joueur de
            confiance) va avec sa coupe sur la couleur demandee
            dominer tous les atouts des joueurs de non confiance
            eventuels
            */
        if (ramasseurBatAdvSur(joueursNonConfianceNonJoue_,
                _couleurDemandee, _card, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.TEAM;
        }
            /*
            On cherche les joueurs de confiance battant de maniere
            certaine les joueurs de non confiance n'ayant pas joue ou
            possedant des cartes que les joueurs ayant joue n'ont pas
            ainsi que les joueurs de non confiance n'ayant pas joue
            */
        if (existeJoueurNonJoueBattantAdv(
                joueursNonConfianceNonJoue_,
                joueursConfianceNonJoue_, _couleurDemandee,
                cartesPossibles_, cartesCertaines_)) {
            return PossibleTrickWinner.TEAM;
        }
            /*
            On cherche les joueurs de non confiance battant de
            maniere certaine les joueurs de confiance n'ayant pas
            joue ou possedant des cartes que les joueurs ayant joue
            n'ont pas ainsi que les joueurs de non confiance n'ayant
            pas joue
            */
        if (existeJoueurAdvRamBatAdvSur(
                joueursConfianceNonJoue_,
                joueursNonConfianceNonJoue_, _couleurDemandee,
                _card, cartesPossibles_, cartesCertaines_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    static PossibleTrickWinner getPossibleTrickWinnerNoTrump(TarotInfoPliEnCours _info) {
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        CardTarot card_ = _info.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), _info.getNbPlayers());
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        byte player_ = _info.getCurrentPlayer();
        Bytes joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Bytes joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
        joueursConfianceNonJoue_.removeObj(player_);
        Suit couleurDemandee_ = _info.getProgressingTrick().couleurDemandee();
        /* La couleur demandee n 'est pas de l 'atout et le pli n 'est pas coupe */
        boolean canTrump_ = peutCouper(couleurDemandee_, player_, cartesPossibles_);
        boolean ramasseurVirtuelEgalCertain_ = vaCouperListe(cartesPossibles_, cartesCertaines_, couleurDemandee_, joueursConfianceNonJoue_);
        if (ramasseurVirtuelEgalCertain_ && !canTrump_) {
            /*
        Si un joueur de confiance n
        ayant pas joue va surement
        couper le pli
        */
            return getPossibleTrickWinnerOtherTrump(_info,PossibleTrickWinner.TEAM,joueursConfianceNonJoue_,PossibleTrickWinner.FOE_TEAM,joueursNonConfianceNonJoue_);
        }
        ramasseurVirtuelEgalCertain_ = vaCouperListe(cartesPossibles_, cartesCertaines_, couleurDemandee_, joueursNonConfianceNonJoue_);
        if (ramasseurVirtuelEgalCertain_ && !canTrump_) {
            return getPossibleTrickWinnerOtherTrump(_info,PossibleTrickWinner.FOE_TEAM,joueursNonConfianceNonJoue_,PossibleTrickWinner.TEAM,joueursConfianceNonJoue_);
        }
        if (!cartesPossibles_.getVal(couleurDemandee_).get(player_).estVide()
                && cartesPossibles_.getVal(couleurDemandee_).get(player_)
                .premiereCarte().strength(couleurDemandee_) > card_.strength(couleurDemandee_)) {
            /* Si le joueur numero peut prendre la main sans couper */
            /*
            On ne sait pas si un joueur n'ayant pas joue va couper le pli
            ou non
            */
            return PossibleTrickWinner.UNKNOWN;
        }
        /* Fin si le joueur numero peut prendre la main sans couper */
        if (canTrump_) {
            /* Si le joueur
            numero peut
            prendre la
            main en coupant
            */
            Bytes other_ = new Bytes(joueursNonJoue_);
            other_.removeObj(player_);
            CardTarot cardPl_ = cartesPossibles_.getVal(Suit.TRUMP).get(player_).premiereCarte();
            if (ramasseurBatAdvSur(other_,
                    couleurDemandee_, cardPl_, cartesPossibles_,
                    cartesCertaines_)) {
                return PossibleTrickWinner.UNKNOWN;
            }
            /*
            On cherche les joueurs de confiance battant de maniere
            certaine les joueurs de non confiance n'ayant pas joue ou
            possedant des cartes que les joueurs ayant joue n'ont pas
            ainsi que les joueurs de non confiance n'ayant pas joue
            */
            if (existeJoueurBatAdvNum(joueursNonConfianceNonJoue_,
                    joueursConfianceNonJoue_, player_, couleurDemandee_,
                    cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*
            On cherche les joueurs de non confiance battant de maniere
            certaine les joueurs de confiance n'ayant pas joue ou
            possedant des cartes que les joueurs ayant joue n'ont pas
            ainsi que les joueurs de non confiance n'ayant pas joue
            */
            if (existeJoueurBatAdvNum(joueursConfianceNonJoue_,
                    joueursNonConfianceNonJoue_, player_,
                    couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        /* Le joueur numero ne peut pas prendre la main */
        return getPossibleTrickWinnerNoCurrentTrump(_info,card_);
    }

    private static boolean vaCouperListe(EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, Suit _couleurDemandee, Bytes _team) {
        boolean ramasseurVirtuelEgalCertain_ = false;
        for (byte joueur_ : _team) {
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles, _cartesCertaines)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
        }
        return ramasseurVirtuelEgalCertain_;
    }

    static PossibleTrickWinner getPossibleTrickWinnerOtherTrump(TarotInfoPliEnCours _info,
                                                                PossibleTrickWinner _current,
                                                                Bytes _currentNotPl,
                                                                PossibleTrickWinner _other,
                                                                Bytes _otherNotPl) {
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurDemandee_ = _info.getProgressingTrick().couleurDemandee();
        boolean ramasseurVirtuelEgalCertain_ = nePeutCouperListe(cartesPossibles_, cartesCertaines_, couleurDemandee_, _otherNotPl);
        if (ramasseurVirtuelEgalCertain_) {
                /*
                Si aucun joueur de non
                confiance n ayant pas
                joue ne va couper le pli
                */
            return _current;
        }
        if (existeJoueurNonJoueBattantAdv(
                _otherNotPl,
                _currentNotPl, couleurDemandee_,
                cartesPossibles_, cartesCertaines_)) {
            return _current;
        }
        if (existeJoueurNonJoueBattantAdv(
                _currentNotPl,
                _otherNotPl, couleurDemandee_,
                cartesPossibles_, cartesCertaines_)) {
            return _other;
        }
        return PossibleTrickWinner.UNKNOWN;
    }
    static PossibleTrickWinner getPossibleTrickWinnerNoCurrentTrump(TarotInfoPliEnCours _info, CardTarot _card) {
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        Bytes joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Bytes joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
        joueursConfianceNonJoue_.removeObj(_info.getCurrentPlayer());
        Suit couleurDemandee_ = _info.getProgressingTrick().couleurDemandee();
        int str_ = _card.strength(couleurDemandee_);
        if (_info.getJoueursConfiance().containsObj(_info.getRamasseurVirtuel())) {
            if (ramasseurBatSsCprAdv(joueursNonConfianceNonJoue_,
                    couleurDemandee_, str_, cartesPossibles_,
                    cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            boolean ramasseurVirtuelEgalCertain_ = nePeutCouperListe(cartesPossibles_, cartesCertaines_, couleurDemandee_, joueursNonConfianceNonJoue_);
            if (ramasseurVirtuelEgalCertain_ && existPlayerNoTrump(joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, couleurDemandee_, _card, cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            ramasseurVirtuelEgalCertain_ = nePeutCouperListe(cartesPossibles_, cartesCertaines_, couleurDemandee_, joueursConfianceNonJoue_);
            if (ramasseurVirtuelEgalCertain_ && existPlayerNoTrump(joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurDemandee_, _card, cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        /* Fin joueursDeConfiance.contains(ramasseurVirtuel) */
        /* Maintenant le ramasseur virtuel n'est pas un joueur de confiance */
        if (ramasseurBatSsCprAdv(joueursConfianceNonJoue_,
                couleurDemandee_, str_, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        boolean ramasseurVirtuelEgalCertain_ = nePeutCouperListe(cartesPossibles_, cartesCertaines_, couleurDemandee_, joueursConfianceNonJoue_);
        if (ramasseurVirtuelEgalCertain_ && existPlayerNoTrump(joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurDemandee_, _card, cartesPossibles_, cartesCertaines_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        ramasseurVirtuelEgalCertain_ = nePeutCouperListe(cartesPossibles_, cartesCertaines_, couleurDemandee_, joueursNonConfianceNonJoue_);
        if (ramasseurVirtuelEgalCertain_) {
            if (existPlayerNoTrump(joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,couleurDemandee_, _card,cartesPossibles_,cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    private static boolean nePeutCouperListe(EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, Suit _couleurDemandee, Bytes _team) {
        boolean ramasseurVirtuelEgalCertain_ = true;
        for (byte joueur_ : _team) {
            if (!nePeutCouper(_couleurDemandee, joueur_, _cartesPossibles, _cartesCertaines)) {
                ramasseurVirtuelEgalCertain_ = false;
            }
        }
        return ramasseurVirtuelEgalCertain_;
    }

    static boolean existPlayerNoTrump(Bytes _equipeABattre,Bytes _equipeDom, Suit _couleurDemandee,
                                      CardTarot _carteForte,EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                      EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        boolean det_ = false;
        for (byte joueur_ : _equipeDom) {
            if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur_).estVide()) {
                CardTarot cardPl_ = _cartesCertaines.getVal(_couleurDemandee).get(joueur_).premiereCarte();
                byte str_ = cardPl_.strength(_couleurDemandee);
                if (str_ <= _carteForte.strength(_couleurDemandee)) {
                    continue;
                }
                if (ramasseurBatSsCprAdv(_equipeABattre,_couleurDemandee,str_,_cartesPossibles,_cartesCertaines)) {
                    det_ = true;
                }
            }
        }
        return det_;
    }

    static PossibleTrickWinner getPossibleTrickWinnerTrump(TarotInfoPliEnCours _info) {
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        CardTarot card_ = _info.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), _info.getNbPlayers());
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        byte player_ = _info.getCurrentPlayer();
        Bytes joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Bytes joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
        joueursConfianceNonJoue_.removeObj(player_);
        /* Le pli n'est pas coupe et la couleur demandee est l'atout */
        if (cartesCertaines_.getVal(Suit.TRUMP).get(player_).estVide()
                || cartesCertaines_.getVal(Suit.TRUMP).get(player_).premiereCarte().strength(Suit.TRUMP) < card_
                .strength(Suit.TRUMP)) {
            return possibleTrickWinnerTrumpCannotUseGreaterTrump(_info, card_, joueursNonConfianceNonJoue_, joueursConfianceNonJoue_);
        }
        /*
        Fin !cartesCertaines.get(couleurDemandee).get(numero).estVide()||
        cartesCertaines
        .get(1).get(numero).estVide()||cartesCertaines.get(Suit.TRUMP)
        .get(numero).premiereCarte().getforceJeu(couleurDemandee)<carteForte.getforceJeu(couleurDemandee) (fin
        test de possibilite pour le joueur numero de prendre le pli)
        */
        /*
        Le joueur numero peut prendre la main en utilisant un atout sur
        demande d'atout
        */
        CardTarot cardPl_ = cartesCertaines_.getVal(Suit.TRUMP).get(player_)
                .premiereCarte();
        Bytes other_ = new Bytes(joueursNonJoue_);
        other_.removeObj(player_);
        if (ramasseurBatAdvDemat(other_,
                cardPl_, cartesPossibles_)) {
            return PossibleTrickWinner.UNKNOWN;
        }
        /*
        On cherche les joueurs de confiance battant de maniere certaine les
        joueurs de non confiance n'ayant pas joue ou possedant des cartes que
        les joueurs ayant joue n'ont pas ainsi que les joueurs de non
        confiance n'ayant pas joue
        */
        if (existeJouBatAdvNumDemat(joueursNonConfianceNonJoue_,
                joueursConfianceNonJoue_, player_, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*
        On cherche les joueurs de non confiance battant de maniere certaine
        les joueurs de confiance n'ayant pas joue ou possedant des cartes que
        les joueurs ayant joue n'ont pas ainsi que les joueurs de non
        confiance n'ayant pas joue
        */
        if (existeJouBatAdvNumDemat(joueursConfianceNonJoue_,
                joueursNonConfianceNonJoue_, player_, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    private static PossibleTrickWinner possibleTrickWinnerTrumpCannotUseGreaterTrump(TarotInfoPliEnCours _info, CardTarot _card, Bytes _joueursNonConfianceNonJoue, Bytes _joueursConfianceNonJoue) {
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        Bytes joueursConfiance_ = _info.getJoueursConfiance();
        /*
    Si le joueur numero ne peut pas prendre
    la main sur demande d'atout
    */
        if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
            /*
            Si le ramasseur virtuel (de confiance, ici) domine
            certainement les joueurs de non confiance n'ayant pas joue
            */
            if (ramasseurBatAdvDemat(_joueursNonConfianceNonJoue,
                    _card, cartesPossibles_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*
            On cherche les joueurs de confiance battant de maniere
            certaine les joueurs de non confiance n'ayant pas joue ou
            possedant des cartes que les joueurs ayant joue n'ont pas
            ainsi que les joueurs de non confiance n'ayant pas joue
            */
            if (existeJouBatAdvDemat(_joueursNonConfianceNonJoue,
                    _joueursConfianceNonJoue, cartesPossibles_,
                    cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*
            On cherche les joueurs de non confiance battant de maniere
            certaine les joueurs de confiance n'ayant pas joue ou
            possedant des cartes que les joueurs ayant joue n'ont pas
            ainsi que les joueurs de non confiance n'ayant pas joue
            */
            if (existeJouBatAdvSurDemat(_joueursConfianceNonJoue,
                    _joueursNonConfianceNonJoue, _card,
                    cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
            /*
            ramasseurVirtuel n'est pas un joueur de confiance pour le joueur
            numero
            */
            /*
            Si le ramasseur virtuel (de non confiance, ici) domine
            certainement les joueurs de non confiance n'ayant pas joue
            */
        if (ramasseurBatAdvDemat(_joueursConfianceNonJoue,
                _card, cartesPossibles_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
            /*
            On cherche les joueurs de non confiance battant de maniere
            certaine les joueurs de confiance n'ayant pas joue ou possedant
            des cartes que les joueurs ayant joue n'ont pas ainsi que les
            joueurs de non confiance n'ayant pas joue
            */
        if (existeJouBatAdvDemat(_joueursConfianceNonJoue,
                _joueursNonConfianceNonJoue, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
        if (existeJouBatAdvSurDemat(_joueursNonConfianceNonJoue,
                _joueursConfianceNonJoue, _card, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
        /* Fin joueurDeConfiance.contains(ramasseurVirtuel) */
    }

    static boolean existeJouBatAdvNumDemat(
            Bytes _equipeABattre, Bytes _equipeDom, byte _numero,
            EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        byte strength_ = _cartesPossibles.getVal(Suit.TRUMP).get(_numero).premiereCarte().strength(Suit.TRUMP);
        return beatSureListTrumpDemand(_equipeABattre, _equipeDom, _cartesPossibles, _cartesCertaines, strength_);
    }

    static boolean existeJouBatAdvSurDemat(
            Bytes _equipeABattre, Bytes _equipeDom,
            CardTarot _carteForte, EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        byte strength_ = _carteForte.strength(Suit.TRUMP);
        return beatSureListTrumpDemand(_equipeABattre, _equipeDom, _cartesPossibles, _cartesCertaines, strength_);
    }

    static boolean existeJouBatAdvDemat(
            Bytes _equipeABattre, Bytes _equipeDom,
            EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        for (byte joueur_ : _equipeDom) {
            if (!_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                boolean joueurBatAdversaire_ = beatFoeTrumpDemand(_equipeABattre, _cartesPossibles, _cartesCertaines, joueur_);
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    static boolean ramasseurBatAdvDemat(
            Bytes _equipeABattre, CardTarot _carteForte,
            EnumMap<Suit,CustList<HandTarot>> _cartesPossibles) {
        byte strength_ = _carteForte.strength(Suit.TRUMP);
        return beatSureListTrumpDemandPast(_equipeABattre, _cartesPossibles, strength_);
    }


    static boolean existeJoueurBatAdvNum(
            Bytes _equipeABattre, Bytes _equipeDom, byte _numero,
            Suit _couleurDemandee, EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        byte strength_ = _cartesCertaines.getVal(Suit.TRUMP).get(_numero).premiereCarte().strength(_couleurDemandee);
        return beatSureListTrumpNormalSuit(_equipeABattre, _equipeDom, _couleurDemandee, _cartesPossibles, _cartesCertaines, strength_);
    }

    static boolean existeJoueurAdvRamBatAdvSur(
            Bytes _equipeABattre, Bytes _equipeDom,
            Suit _couleurDemandee, CardTarot _carteForte,
            EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        byte strength_ = _carteForte.strength(_couleurDemandee);
        return beatSureListTrumpNormalSuit(_equipeABattre, _equipeDom, _couleurDemandee, _cartesPossibles, _cartesCertaines, strength_);
    }


    static boolean existeJoueurNonJoueBattantAdv(
            Bytes _equipeABattre, Bytes _equipeDom,
            Suit _couleurDemandee, EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        for (byte joueur_ : _equipeDom) {
            /*
        On cherche les joueurs de confiance
        battant de maniere certaine les
        joueurs de non confiance n'ayant pas
        joue ou possedant des cartes que les
        joueurs ayant joue n'ont pas ainsi
        que les joueurs de non confiance
        n'ayant pas joue
        */
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                boolean joueurBatAdversaire_ = beatByTrumpNormalSuit(_equipeABattre, _couleurDemandee, _cartesPossibles, _cartesCertaines, joueur_);
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    static boolean ramasseurBatSsCprAdv(
            Bytes _equipeABattre, Suit _couleurDemandee,
            int _strength, EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = true;
        for (byte joueur_ : _equipeABattre) {
            boolean ramasseurVirtuelEgalCertain_;
            ramasseurVirtuelEgalCertain_ = !_cartesCertaines
                    .getVal(_couleurDemandee).get(joueur_).estVide();
            if (ramasseurVirtuelEgalCertain_ && _strength <= _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().strength(_couleurDemandee)) {
                ramasseurVirtuelEgalCertain_ = false;
            }
            if (defausse(_cartesPossibles, joueur_, _couleurDemandee) || nePeutCouper(_couleurDemandee, joueur_, _cartesPossibles, _cartesCertaines) && _strength > _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().strength(_couleurDemandee)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }

    static boolean ramasseurBatAdvSur(Bytes _equipeABattre,
                                      Suit _couleurDemandee, CardTarot _carteForte,
                                      EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                      EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        byte strength_ = _carteForte.strength(_couleurDemandee);
        return beatByTrumpNormalSuitStrength(_equipeABattre, _couleurDemandee, _cartesPossibles, _cartesCertaines, strength_);
    }

    static boolean beatSureListTrumpDemand(Bytes _equipeABattre, Bytes _equipeDom, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                           EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, byte _strength) {
        boolean ramasseurDeter_ = false;
        for (byte joueur_ : _equipeDom) {
            if (!_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                boolean joueurBatAdversaire_ = beatFoeTrumpDemand(_equipeABattre, _cartesPossibles, _cartesCertaines, joueur_);
                if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) <= _strength) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    static boolean beatFoeTrumpDemand(Bytes _equipeABattre, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, byte _joueur) {
        byte strength_ = _cartesCertaines.getVal(Suit.TRUMP).get(_joueur).premiereCarte().strength(Suit.TRUMP);
        return beatSureListTrumpDemandPast(_equipeABattre, _cartesPossibles, strength_);
    }

    static boolean beatSureListTrumpDemandPast(Bytes _equipeABattre, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, byte _strength) {
        boolean joueurBatAdversaire_ = true;
        for (byte joueur_ : _equipeABattre) {
            boolean ramasseurVirtuelEgalCertain_;
            ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                    .get(joueur_).estVide();
            if (!ramasseurVirtuelEgalCertain_ && _strength > _cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                joueurBatAdversaire_ = false;
            }
        }
        return joueurBatAdversaire_;
    }
    static boolean beatSureListTrumpNormalSuit(Bytes _equipeABattre, Bytes _equipeDom, Suit _couleurDemandee,
                                               EnumMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                               EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, byte _strength) {
        boolean ramasseurDeter_ = false;
        for (byte joueur_ : _equipeDom) {
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                boolean joueurBatAdversaire_ = beatByTrumpNormalSuit(_equipeABattre, _couleurDemandee, _cartesPossibles, _cartesCertaines, joueur_);
                if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) <= _strength) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    static boolean beatByTrumpNormalSuit(Bytes _equipeABattre, Suit _couleurDemandee, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                         EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, byte _joueur) {
        byte strength_ = _cartesCertaines.getVal(Suit.TRUMP).get(_joueur).premiereCarte().strength(_couleurDemandee);
        return beatByTrumpNormalSuitStrength(_equipeABattre, _couleurDemandee, _cartesPossibles, _cartesCertaines, strength_);
    }


    static boolean beatByTrumpNormalSuitStrength(Bytes _equipeABattre, Suit _couleurDemandee, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                                 EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, byte _strength) {
        boolean joueurBatAdversaire_ = true;
        for (byte joueur_ : _equipeABattre) {
            boolean ramasseurVirtuelEgalCertain_;
            ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                    .get(joueur_).estVide();
            if (!ramasseurVirtuelEgalCertain_ && _strength > _cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur_).estVide()) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                joueurBatAdversaire_ = false;
            }
        }
        return joueurBatAdversaire_;
    }
    static EnumList<Suit> couleursPouvantEtreCoupees(Bytes _joueurs,
                                                     EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if(joueursSusceptiblesCoupe(_cartesPossibles, couleur_, _joueurs).isEmpty()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static Bytes joueursSusceptiblesCoupe(
            EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Bytes _joueurs) {
        Bytes joueursSusceptiblesDeCouper_ = new Bytes();
        for (byte joueur_ : _joueurs) {
            if (peutCouper(_couleurDemandee, joueur_,
                    _cartesPossibles)) {
                joueursSusceptiblesDeCouper_
                        .add(joueur_);
            }
        }
        return joueursSusceptiblesDeCouper_;
    }
    static Bytes joueursPouvantCouperCouleurs(HandTarot _main,
                                                      Bytes _joueurs,
                                                      EnumMap<Suit,CustList<HandTarot>> _cartesPossibles, EnumList<Suit> _couleurs) {
        Bytes joueurs_ = new Bytes();
        for (byte joueur_ : _joueurs) {
            for (Suit couleur_ : _couleurs) {
                if (!_main.couleur(couleur_).estVide() && peutCouper(couleur_, joueur_, _cartesPossibles)) {
                    joueurs_.add(joueur_);
                    break;
                }
            }
        }
        return joueurs_;
    }
    static Bytes joueursPossedantAtout(Bytes _joueurs,
                                               EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        Bytes joueurs_ = new Bytes();
        for (byte joueur_ : _joueurs) {
            if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static Bytes joueursPouvantPossederPetit(Bytes _joueurs,
                                                     EnumMap<Suit,CustList<HandTarot>> _cartesPossibles) {
        Bytes joueurs_ = new Bytes();
        CustList<HandTarot> atoutsPossibles_ = _cartesPossibles
                .getVal(Suit.TRUMP);
        for (byte joueur_ : _joueurs) {
            if (!atoutsPossibles_.get(joueur_).contient(CardTarot.petit())) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static Bytes joueursPossedantAtoutMaitre(Bytes _joueurs,
                                                     EnumMap<Suit,CustList<HandTarot>> _cartesCertaines, HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Bytes joueurs_ = new Bytes();
        CustList<HandTarot> atoutsCertains_ = _cartesCertaines
                .getVal(Suit.TRUMP);
        for (byte joueur_ : _joueurs) {
            if (atoutsCertains_.get(joueur_).estVide()) {
                continue;
            }
            HandTarot atouts_ = new HandTarot();
            atouts_.ajouterCartes(atoutsCertains_.get(joueur_));
            HandTarot atoutsMaitres_ = atouts_.atoutsMaitres(cartesJouees_);
            if (!atoutsMaitres_.estVide()) {
                joueurs_.add(joueur_);
            }
        }
        return joueurs_;
    }
    static Bytes joueursPossedantNbAtout(Bytes _joueurs,
                                                 EnumMap<Suit,CustList<HandTarot>> _cartesCertaines, Rate _nbAtoutMin) {
        Bytes joueurs_ = new Bytes();
        for (byte joueur_ : _joueurs) {
            if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).total() < _nbAtoutMin.ll()) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static boolean nePeutAvoirFigures(
            EnumMap<Suit,CustList<HandTarot>> _cartesPossibles, byte _numero,
            Suit _couleur) {
        return _cartesPossibles.getVal(_couleur).get(_numero).estVide()
                || !_cartesPossibles.getVal(_couleur).get(_numero).premiereCarte()
                .isCharacter();
    }
    static boolean pasAtout(
            Bytes _joueursDeNonConfianceNonJoue,
            EnumMap<Suit,CustList<HandTarot>> _cartesPossibles) {
        boolean pasAtout_ = true;
        for (byte joueur_ : _joueursDeNonConfianceNonJoue) {
            if (!_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                pasAtout_ = false;
            }
        }
        return pasAtout_;
    }

    static boolean defausse(EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
                            byte _numero, Suit _couleur) {
        return _cartesPossibles.getVal(Suit.TRUMP).get(_numero).estVide()
                && _cartesPossibles.getVal(_couleur).get(_numero).estVide();
    }
    static boolean vaCouper(Suit _couleur, byte _joueur,
                            EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
                            EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        return _cartesPossibles.getVal(_couleur).get(_joueur).estVide()
                && !_cartesCertaines.getVal(Suit.TRUMP).get(_joueur).estVide();
    }
    static boolean peutCouper(Suit _couleur, byte _numero,
                              EnumMap<Suit,CustList<HandTarot>> _cartesPossibles) {
        return _cartesPossibles.getVal(_couleur).get(_numero).estVide()
                && !_cartesPossibles.getVal(Suit.TRUMP).get(_numero).estVide();
    }
    static boolean nePeutCouper(Suit _couleur, byte _numero,
                                EnumMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                EnumMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        return _cartesPossibles.getVal(Suit.TRUMP).get(_numero).estVide()
                || !_cartesCertaines.getVal(_couleur).get(_numero).estVide();
    }
}
