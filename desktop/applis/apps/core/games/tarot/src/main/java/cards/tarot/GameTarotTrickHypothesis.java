package cards.tarot;

import cards.consts.PossibleTrickWinner;
import cards.consts.Role;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.maths.Rate;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

final class GameTarotTrickHypothesis {

    private GameTarotTrickHypothesis(){
    }
    static CustList<CustList<BoolVal>> hypothesesRepartitionsJoueurs(GameTarotTeamsRelation _teamReal, HandTarot _calledCards,
                                                                     CustList<TrickTarot> _plisFaits, int _numero,
                                                                     IdMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                                                     IdMap<Suit, CustList<HandTarot>> _cartesCertaines) {
        Role st_ = _teamReal.statutDe(_numero);
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        CustList<TrickTarot> fullTricksProg_ = new CustList<TrickTarot>();
        for (TrickTarot t:_plisFaits.mid(1)) {
//        for (TrickTarot t:_plisFaits)
//            if (!t.getVuParToutJoueur()) {
//                continue;
//            }
            fullTricksProg_.add(t);
        }
        boolean appelesTousConnus_ = GameTarotTeamsRelation.allKnownCalledPlayers(_calledCards,_cartesCertaines,
                nombreJoueurs_);
        Ints joueursNonConfiancePresqueSure_ = new Ints();
        Ints possibleAlly_ = new Ints();
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
        joueursNonConfiancePresqueSure_.removeDuplicates();
        joueursNonConfiancePresqueSure_.removeObj(_numero);
        changeConfidence(_teamReal, _numero, joueursNonConfiancePresqueSure_, possibleAlly_);
        return _teamReal.getConfidence();
    }

    private static void completeJoueursNonConfiancePresqueSurePossibleAlly(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Role _st, Ints _joueursNonConfiancePresqueSure, Ints _possibleAlly) {
        if (_st == Role.DEFENDER) {
            completeJoueursNonConfiancePresqueSurePossibleAllyDefender(_teamReal, _calledCards, _cartesPossibles, _possibleAlly);
        } else {
            completeJoueursNonConfiancePresqueSurePossibleAllyAttacker(_teamReal, _calledCards, _cartesPossibles, _joueursNonConfiancePresqueSure);
        }
    }

    private static void completeJoueursNonConfiancePresqueSurePossibleAllyAttacker(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Ints _joueursNonConfiancePresqueSure) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for(CardTarot c: _calledCards) {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (_cartesPossibles.getVal(c.getId().getCouleur())
                        .get(joueur_).estVide()) {
                    _joueursNonConfiancePresqueSure.add(joueur_);
                }
            }
        }
    }

    private static void completeJoueursNonConfiancePresqueSurePossibleAllyDefender(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Ints _possibleAlly) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for(CardTarot c: _calledCards) {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
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

    private static void changeConfidence(GameTarotTeamsRelation _teamReal, int _numero, Ints _joueursNonConfiancePresqueSure, Ints _possibleAlly) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        Role st_ = _teamReal.statutDe(_numero);
        Ints allConf_ = new Ints();
        allConf_.addAllElts(_possibleAlly);
        allConf_.addAllElts(_joueursNonConfiancePresqueSure);
        if (NumberUtil.equalsSetInts(allConf_, GameTarotTeamsRelation.tousJoueurs(nombreJoueurs_))) {
            for (int b: _possibleAlly) {
                _teamReal.faireConfiance(_numero,b);
            }
            for (int b: _joueursNonConfiancePresqueSure) {
                _teamReal.faireMefiance(_numero,b);
            }
        } else if (st_ == Role.DEFENDER) {
            for (int b: _possibleAlly) {
                _teamReal.faireConfiance(_numero,b);
            }
        } else {
            if (_possibleAlly.size() < 3) {
                for (int b: _possibleAlly) {
                    _teamReal.faireConfiance(_numero,b);
                }
            }
        }
    }

    private static void charsDiscard(GameTarotTeamsRelation _teamReal, int _numero, CustList<TrickTarot> _fullTricksProg, Ints _joueursNonConfiancePresqueSure, Ints _possibleAlly) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        Role st_ = _teamReal.statutDe(_numero);
        for (int j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant les figures defaussese sur demande d'atout
            for(Suit c: Suit.couleursOrdinaires()) {
                HandTarot cartesCouleurJouees_ = new HandTarot();
                int ramasseurVirtuel_ = ramasseurVirtuelCharsDiscard(_teamReal, _fullTricksProg, j, c, cartesCouleurJouees_);
                if (ramasseurVirtuel_ == -1 || onlyOnePlayedCard(_fullTricksProg, nombreJoueurs_, cartesCouleurJouees_, j)) {
                    continue;
                }
                res(j, _possibleAlly, ramasseurVirtuel_, _teamReal, st_, _joueursNonConfiancePresqueSure, _numero);
            }

        }
    }

    private static int ramasseurVirtuelCharsDiscard(GameTarotTeamsRelation _teamReal, CustList<TrickTarot> _fullTricksProg, int _j, Suit _c, HandTarot _cartesCouleurJouees) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        int ramasseurVirtuel_ = -1;
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

    private static void charsFollowSuit(GameTarotTeamsRelation _teamReal, int _numero, CustList<TrickTarot> _fullTricksProg, Ints _joueursNonConfiancePresqueSure, Ints _possibleAlly) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        Role st_ = _teamReal.statutDe(_numero);
        for (int j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant les figures jouees au premier tour d'une couleur ordinaire demandee
            for(Suit c: Suit.couleursOrdinaires()) {
                HandTarot cartesCouleurJouees_ = new HandTarot();
                int ramasseurVirtuel_ = charsFollowSuitRamasseurVirtuel(_teamReal, _fullTricksProg, j, c, cartesCouleurJouees_);
                if (ramasseurVirtuel_ == -1 || onlyOnePlayedCard(_fullTricksProg, nombreJoueurs_, cartesCouleurJouees_, j)) {
                    continue;
                }
                res(j, _possibleAlly, ramasseurVirtuel_, _teamReal, st_, _joueursNonConfiancePresqueSure, _numero);
            }

        }
    }

    private static int charsFollowSuitRamasseurVirtuel(GameTarotTeamsRelation _teamReal, CustList<TrickTarot> _fullTricksProg, int _j, Suit _c, HandTarot _cartesCouleurJouees) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        int ramasseurVirtuel_ = -1;
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

    private static int majRamasseurVirtuel(GameTarotTeamsRelation _teamReal, int _j, Suit _c, HandTarot _cartesCouleurJouees, int _ramasseurVirtuel, TrickTarot _p, CardTarot _carteJouee) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        int ramasseurVirtuel_ = _ramasseurVirtuel;
        boolean carteJoueeRamassee_ = false;
        int max_ = _carteJouee.strength(_c);
        for(int j2_: _p.joueursAyantJoueAvant(_j, _teamReal.getRules().getDealing())) {
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

    private static void trumpAce(GameTarotTeamsRelation _teamReal, int _numero, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, CustList<TrickTarot> _fullTricksProg, Ints _joueursNonConfiancePresqueSure, Ints _possibleAlly) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for (int j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
            int ramasseur_ = trumpAceWin(_teamReal, _cartesPossibles, _fullTricksProg, j);
            if (ramasseur_ != -1) {
                if (ramasseur_ == -2) {
                    break;
                }
                trumpAceFeed(_teamReal, _numero, _joueursNonConfiancePresqueSure, _possibleAlly, j);
            }
        }
    }

    private static int trumpAceWin(GameTarotTeamsRelation _teamReal, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, CustList<TrickTarot> _fullTricksProg, int _j) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        int ramasseur_ = -1;
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

    private static void trumpAceFeed(GameTarotTeamsRelation _teamReal, int _numero, Ints _joueursNonConfiancePresqueSure, Ints _possibleAlly, int _j) {
        Role st_ = _teamReal.statutDe(_numero);
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        //le ramasseur du pli et le joueur du Petit (entameur) sont dans la meme equipe
        if(st_ == Role.DEFENDER) {
            //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
            //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
            //de plus numero est un defenseur
            Ints found_ = new Ints();
            found_.add(_j);
            found_.add(_numero);
            for (int b: GameTarotTeamsRelation.autresJoueurs(found_, nombreJoueurs_)) {
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

    private static void trumpAceStarter(GameTarotTeamsRelation _teamReal, int _numero, CustList<TrickTarot> _fullTricksProg, Ints _joueursNonConfiancePresqueSure, Ints _possibleAlly) {
        Role st_ = _teamReal.statutDe(_numero);
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for (int j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant l'entameur du Petit
            int ramasseur_ = trumpAceStarterWin(_fullTricksProg, nombreJoueurs_, j);
            if (ramasseur_ != -1) {
                if (ramasseur_ == -2) {
                    break;
                }
                res(j, _possibleAlly, ramasseur_, _teamReal, st_, _joueursNonConfiancePresqueSure, _numero);
            }
        }
    }

    private static int trumpAceStarterWin(CustList<TrickTarot> _fullTricksProg, int _nombreJoueurs, int _j) {
        int ramasseur_ = -1;
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

    private static boolean trumpAceAsFirstPlayedCardTrick(int _nombreJoueurs, int _j, TrickTarot _p) {
        return _j == _p.getEntameur() && _p.carteDuJoueur(_j, _nombreJoueurs) == CardTarot.petit();
    }

    private static void trumpAceFirstPlayedTrump(GameTarotTeamsRelation _teamReal, int _numero, CustList<TrickTarot> _fullTricksProg, Ints _joueursNonConfiancePresqueSure, Ints _possibleAlly) {
        Role st_ = _teamReal.statutDe(_numero);
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for (int j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
            int ramasseur_ = trumpAceFirstPlayedTrumpPlayer(_teamReal, _fullTricksProg, j);
            if(ramasseur_ == -1) {
                continue;
            }
            res(j, _possibleAlly, ramasseur_, _teamReal, st_, _joueursNonConfiancePresqueSure, _numero);
        }
    }

    private static int trumpAceFirstPlayedTrumpPlayer(GameTarotTeamsRelation _teamReal, CustList<TrickTarot> _fullTricksProg, int _j) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        //iteration sur la confiance du joueur numero en le joueur j
        //vis a vis du Petit joue en premier atout mais jamais virtuellement maitre
        //le Petit doit etre ramasse par le plus grand atout encore en jeu
        //ramasse par un autre atout
        int ramasseur_ = -1;
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

    private static boolean petitRamasse(GameTarotTeamsRelation _teamReal, int _j, TrickTarot _p, CardTarot _carte) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        boolean petitRamasse_ = false;
        Suit couleurDemandee_ = _p.couleurDemandee();
        int forcePetit_ = _carte.strength(couleurDemandee_);
        for (int j2_ : _p.joueursAyantJoueAvant(_j, _teamReal.getRules().getDealing())) {
            if (_p.carteDuJoueur(j2_, nombreJoueurs_).strength(couleurDemandee_) >= forcePetit_) {//la carte du joueur j2 est un atout ramassant temporairement le Petit
                petitRamasse_ = true;
                break;
            }
        }
        return petitRamasse_;
    }

    private static void feedJoueursNonConfiancePresqueSurePossibleAlly(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, IdMap<Suit, CustList<HandTarot>> _cartesCertaines, Role _st, Ints _joueursNonConfiancePresqueSure, Ints _possibleAlly) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        if (_st == Role.DEFENDER) {
            feedJoueursNonConfiancePresqueSurePossibleAllyDefender(_teamReal, _calledCards, _cartesCertaines, _possibleAlly);
        } else {
            feedJoueursNonConfiancePresqueSurePossibleAllyAttacker(_teamReal, _calledCards, _cartesCertaines, _possibleAlly);
        }
        for (int b: GameTarotTeamsRelation.autresJoueurs(_possibleAlly, nombreJoueurs_)) {
            _joueursNonConfiancePresqueSure.add(b);
        }
    }

    private static void feedJoueursNonConfiancePresqueSurePossibleAllyAttacker(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, IdMap<Suit, CustList<HandTarot>> _cartesCertaines, Ints _possibleAlly) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for(CardTarot c: _calledCards) {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (_cartesCertaines.getVal(c.getId().getCouleur())
                        .get(joueur_).contient(c)) {
                    _possibleAlly.add(joueur_);
                }
            }
        }
    }

    private static void feedJoueursNonConfiancePresqueSurePossibleAllyDefender(GameTarotTeamsRelation _teamReal, HandTarot _calledCards, IdMap<Suit, CustList<HandTarot>> _cartesCertaines, Ints _possibleAlly) {
        int nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        for(CardTarot c: _calledCards) {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
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

    private static boolean onlyOnePlayedCard(CustList<TrickTarot> _full, int _nbPl, HandTarot _played, int _cur) {
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
    private static CustList<TrickTarot> filter(CustList<TrickTarot> _tricks, int _nbPl, int _current) {
        CustList<TrickTarot> l_ = new CustList<TrickTarot>();
        for (TrickTarot t: _tricks) {
            if (!t.aJoue(_current,_nbPl)) {
                continue;
            }
            l_.add(t);
        }
        return l_;
    }
    private static void res(int _current, Ints _possibleAlly, int _ram, GameTarotTeamsRelation _teamReal, Role _st,
                            Ints _potentialFoesNearlySure, int _numero) {
        //ramasseur != j && ramasseur != -1
        int nbPl_ = _teamReal.getNombreDeJoueurs();
        Ints all_ = GameTarotTeamsRelation.tousJoueurs(nbPl_);
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
            Ints found_ = new Ints();
            found_.add(_current);
            found_.add(_ram);
            _possibleAlly.add(_current);
            _possibleAlly.add(_ram);
            return;
        }
        //!aPourDefenseur(numero) ==> numero == preneur ==> j == appele
        if(_numero == _ram) {
            Ints found_ = new Ints();
            found_.add(_current);
            _possibleAlly.add(_current);
            return;
        }
        addPotentialFoePlayers(_potentialFoesNearlySure, _current,
                _ram);
    }
    private static void addPotentialFoePlayers(Ints _potentialFoesNearlySure,
                                               int _otherPlayer, int _leader) {
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
        int ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        int nbPlayers_ = _info.getNbPlayers();
        CardTarot card_ = _info.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nbPlayers_);
        if (card_.getId().getCouleur() == Suit.TRUMP) {
            return getPossibleTrickWinnerTrumpSuit(_info);
        }
        return getPossibleTrickWinnerNoTrump(_info);
    }

    static PossibleTrickWinner getPossibleTrickWinnerTrumpSuit(TarotInfoPliEnCours _info) {
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        CardTarot card_ = _info.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), _info.getNbPlayers());
        Ints joueursNonJoue_ = _info.getJoueursNonJoue();
        int player_ = _info.getCurrentPlayer();
        Ints joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Ints joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
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
        Ints other_ = new Ints(joueursNonJoue_);
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
        IdList<Suit> couleursAppelees_ = _info.getCalledSuits();
        boolean carteAppeleeJouee_ =_info.isCarteAppeleeJouee();
        int player_ = _info.getCurrentPlayer();
        CardTarot card_ = _info.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), _info.getNbPlayers());
        Ints joueursConfiance_ = _info.getJoueursConfiance();
        Ints joueursNonConfiance_ = _info.getJoueursNonConfiance();
        Ints joueursNonJoue_ = _info.getJoueursNonJoue();
        Ints joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, joueursNonConfiance_);
        Ints joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, joueursConfiance_);
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
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
            joueursConfianceNonJoue_ = new Ints();
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
        IdList<Suit> couleursAppelees_ = _info.getCalledSuits();
        boolean carteAppeleeJouee_ =_info.isCarteAppeleeJouee();
        Ints joueursConfiance_ = _info.getJoueursConfiance();
        Ints joueursNonConfiance_ = _info.getJoueursNonConfiance();
        Ints joueursNonJoue_ = _info.getJoueursNonJoue();
        Ints joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, joueursNonConfiance_);
        Ints joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, joueursConfiance_);
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        if (couleursAppelees_.containsObj(_couleurDemandee)
                && !carteAppeleeJouee_ && joueursNonConfiance_.containsObj(_info.getTaker())) {
            /* The player, probably called by the current taker
            and still owing one called card of the current led suit,
            must follow a card belonging to the current demanded suit.*/
            Ints joueursNonConfiancePreneur_ = new Ints();
            for (int j: joueursNonConfianceNonJoue_) {
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
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        CardTarot card_ = _info.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), _info.getNbPlayers());
        Ints joueursNonJoue_ = _info.getJoueursNonJoue();
        int player_ = _info.getCurrentPlayer();
        Ints joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Ints joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
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
            Ints other_ = new Ints(joueursNonJoue_);
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

    private static boolean vaCouperListe(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, IdMap<Suit, CustList<HandTarot>> _cartesCertaines, Suit _couleurDemandee, Ints _team) {
        boolean ramasseurVirtuelEgalCertain_ = false;
        for (int joueur_ : _team) {
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles, _cartesCertaines)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
        }
        return ramasseurVirtuelEgalCertain_;
    }

    static PossibleTrickWinner getPossibleTrickWinnerOtherTrump(TarotInfoPliEnCours _info,
                                                                PossibleTrickWinner _current,
                                                                Ints _currentNotPl,
                                                                PossibleTrickWinner _other,
                                                                Ints _otherNotPl) {
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
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
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        Ints joueursNonJoue_ = _info.getJoueursNonJoue();
        Ints joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Ints joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
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

    private static boolean nePeutCouperListe(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, IdMap<Suit, CustList<HandTarot>> _cartesCertaines, Suit _couleurDemandee, Ints _team) {
        boolean ramasseurVirtuelEgalCertain_ = true;
        for (int joueur_ : _team) {
            if (!nePeutCouper(_couleurDemandee, joueur_, _cartesPossibles, _cartesCertaines)) {
                ramasseurVirtuelEgalCertain_ = false;
            }
        }
        return ramasseurVirtuelEgalCertain_;
    }

    static boolean existPlayerNoTrump(Ints _equipeABattre,Ints _equipeDom, Suit _couleurDemandee,
                                      CardTarot _carteForte,IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                      IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        boolean det_ = false;
        for (int joueur_ : _equipeDom) {
            if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur_).estVide()) {
                CardTarot cardPl_ = _cartesCertaines.getVal(_couleurDemandee).get(joueur_).premiereCarte();
                int str_ = cardPl_.strength(_couleurDemandee);
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
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        CardTarot card_ = _info.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), _info.getNbPlayers());
        Ints joueursNonJoue_ = _info.getJoueursNonJoue();
        int player_ = _info.getCurrentPlayer();
        Ints joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Ints joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
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
        Ints other_ = new Ints(joueursNonJoue_);
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

    private static PossibleTrickWinner possibleTrickWinnerTrumpCannotUseGreaterTrump(TarotInfoPliEnCours _info, CardTarot _card, Ints _joueursNonConfianceNonJoue, Ints _joueursConfianceNonJoue) {
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        int ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        Ints joueursConfiance_ = _info.getJoueursConfiance();
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
            Ints _equipeABattre, Ints _equipeDom, int _numero,
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        int strength_ = max(Suit.TRUMP,_cartesPossibles.getVal(Suit.TRUMP).get(_numero));
//        byte strength_ = _cartesPossibles.getVal(Suit.TRUMP).get(_numero).premiereCarte().strength(Suit.TRUMP);
        return beatSureListTrumpDemand(_equipeABattre, _equipeDom, _cartesPossibles, _cartesCertaines, strength_);
    }

    static boolean existeJouBatAdvSurDemat(
            Ints _equipeABattre, Ints _equipeDom,
            CardTarot _carteForte, IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        int strength_ = _carteForte.strength(Suit.TRUMP);
        return beatSureListTrumpDemand(_equipeABattre, _equipeDom, _cartesPossibles, _cartesCertaines, strength_);
    }

    static boolean existeJouBatAdvDemat(
            Ints _equipeABattre, Ints _equipeDom,
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        for (int joueur_ : _equipeDom) {
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
            Ints _equipeABattre, CardTarot _carteForte,
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles) {
        int strength_ = _carteForte.strength(Suit.TRUMP);
        return beatSureListTrumpDemandPast(_equipeABattre, _cartesPossibles, strength_);
    }


    static boolean existeJoueurBatAdvNum(
            Ints _equipeABattre, Ints _equipeDom, int _numero,
            Suit _couleurDemandee, IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        int strength_ = _cartesCertaines.getVal(Suit.TRUMP).get(_numero).premiereCarte().strength(_couleurDemandee);
        return beatSureListTrumpNormalSuit(_equipeABattre, _equipeDom, _couleurDemandee, _cartesPossibles, _cartesCertaines, strength_);
    }

    static boolean existeJoueurAdvRamBatAdvSur(
            Ints _equipeABattre, Ints _equipeDom,
            Suit _couleurDemandee, CardTarot _carteForte,
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        int strength_ = _carteForte.strength(_couleurDemandee);
        return beatSureListTrumpNormalSuit(_equipeABattre, _equipeDom, _couleurDemandee, _cartesPossibles, _cartesCertaines, strength_);
    }


    static boolean existeJoueurNonJoueBattantAdv(
            Ints _equipeABattre, Ints _equipeDom,
            Suit _couleurDemandee, IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        for (int joueur_ : _equipeDom) {
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
            Ints _equipeABattre, Suit _couleurDemandee,
            int _strength, IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = true;
        for (int joueur_ : _equipeABattre) {
            boolean ramasseurVirtuelEgalCertain_;
            ramasseurVirtuelEgalCertain_ = !_cartesCertaines
                    .getVal(_couleurDemandee).get(joueur_).estVide();
            int m_ = max(_couleurDemandee, _cartesPossibles.getVal(_couleurDemandee).get(joueur_));
            if (ramasseurVirtuelEgalCertain_ && _strength <= m_) {
                ramasseurVirtuelEgalCertain_ = false;
            }
            if (defausse(_cartesPossibles, joueur_, _couleurDemandee) || nePeutCouper(_couleurDemandee, joueur_, _cartesPossibles, _cartesCertaines) && _strength > m_) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }

    static boolean ramasseurBatAdvSur(Ints _equipeABattre,
                                      Suit _couleurDemandee, CardTarot _carteForte,
                                      IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                      IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        int strength_ = _carteForte.strength(_couleurDemandee);
        return beatByTrumpNormalSuitStrength(_equipeABattre, _couleurDemandee, _cartesPossibles, _cartesCertaines, strength_);
    }

    static boolean beatSureListTrumpDemand(Ints _equipeABattre, Ints _equipeDom, IdMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                           IdMap<Suit, CustList<HandTarot>> _cartesCertaines, int _strength) {
        boolean ramasseurDeter_ = false;
        for (int joueur_ : _equipeDom) {
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

    static boolean beatFoeTrumpDemand(Ints _equipeABattre, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, IdMap<Suit, CustList<HandTarot>> _cartesCertaines, int _joueur) {
        int strength_ = _cartesCertaines.getVal(Suit.TRUMP).get(_joueur).premiereCarte().strength(Suit.TRUMP);
        return beatSureListTrumpDemandPast(_equipeABattre, _cartesPossibles, strength_);
    }

    static boolean beatSureListTrumpDemandPast(Ints _equipeABattre, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, int _strength) {
        boolean joueurBatAdversaire_ = true;
        for (int joueur_ : _equipeABattre) {
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
    static boolean beatSureListTrumpNormalSuit(Ints _equipeABattre, Ints _equipeDom, Suit _couleurDemandee,
                                               IdMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                               IdMap<Suit, CustList<HandTarot>> _cartesCertaines, int _strength) {
        boolean ramasseurDeter_ = false;
        for (int joueur_ : _equipeDom) {
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

    static boolean beatByTrumpNormalSuit(Ints _equipeABattre, Suit _couleurDemandee, IdMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                         IdMap<Suit, CustList<HandTarot>> _cartesCertaines, int _joueur) {
        int strength_ = _cartesCertaines.getVal(Suit.TRUMP).get(_joueur).premiereCarte().strength(_couleurDemandee);
        return beatByTrumpNormalSuitStrength(_equipeABattre, _couleurDemandee, _cartesPossibles, _cartesCertaines, strength_);
    }


    static boolean beatByTrumpNormalSuitStrength(Ints _equipeABattre, Suit _couleurDemandee, IdMap<Suit, CustList<HandTarot>> _cartesPossibles,
                                                 IdMap<Suit, CustList<HandTarot>> _cartesCertaines, int _strength) {
        boolean joueurBatAdversaire_ = true;
        for (int joueur_ : _equipeABattre) {
            int m_ = max(_couleurDemandee, _cartesPossibles.getVal(Suit.TRUMP)
                    .get(joueur_));
            boolean ramasseurVirtuelEgalCertain_;
            ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                    .get(joueur_).estVide();
            if (!ramasseurVirtuelEgalCertain_ && _strength > m_) {
//            if (!ramasseurVirtuelEgalCertain_ && _strength > _cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee))
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
    private static int max(Suit _couleurDemandee, HandTarot _h) {
        int max_;
        if (_h.estVide()) {
            max_ = 0;
        } else {
            max_ = _h.premiereCarte().strength(_couleurDemandee);
        }
        return max_;
    }
    static IdList<Suit> couleursPouvantEtreCoupees(Ints _joueurs,
                                                     IdMap<Suit, CustList<HandTarot>> _cartesPossibles, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if(joueursSusceptiblesCoupe(_cartesPossibles, couleur_, _joueurs).isEmpty()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static Ints joueursSusceptiblesCoupe(
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Ints _joueurs) {
        Ints joueursSusceptiblesDeCouper_ = new Ints();
        for (int joueur_ : _joueurs) {
            if (peutCouper(_couleurDemandee, joueur_,
                    _cartesPossibles)) {
                joueursSusceptiblesDeCouper_
                        .add(joueur_);
            }
        }
        return joueursSusceptiblesDeCouper_;
    }
    static Ints joueursPouvantCouperCouleurs(HandTarot _main,
                                                      Ints _joueurs,
                                                      IdMap<Suit,CustList<HandTarot>> _cartesPossibles, IdList<Suit> _couleurs) {
        Ints joueurs_ = new Ints();
        for (int joueur_ : _joueurs) {
            for (Suit couleur_ : _couleurs) {
                if (!_main.couleur(couleur_).estVide() && peutCouper(couleur_, joueur_, _cartesPossibles)) {
                    joueurs_.add(joueur_);
                    break;
                }
            }
        }
        return joueurs_;
    }
    static Ints joueursPossedantAtout(Ints _joueurs,
                                               IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        Ints joueurs_ = new Ints();
        for (int joueur_ : _joueurs) {
            if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static Ints joueursPouvantPossederPetit(Ints _joueurs,
                                                     IdMap<Suit,CustList<HandTarot>> _cartesPossibles) {
        Ints joueurs_ = new Ints();
        CustList<HandTarot> atoutsPossibles_ = _cartesPossibles
                .getVal(Suit.TRUMP);
        for (int joueur_ : _joueurs) {
            if (!atoutsPossibles_.get(joueur_).contient(CardTarot.petit())) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static Ints joueursPossedantAtoutMaitre(Ints _joueurs,
                                                     IdMap<Suit,CustList<HandTarot>> _cartesCertaines, HandTarot _cartesJouees) {
        IdMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Ints joueurs_ = new Ints();
        CustList<HandTarot> atoutsCertains_ = _cartesCertaines
                .getVal(Suit.TRUMP);
        for (int joueur_ : _joueurs) {
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
    static Ints joueursPossedantNbAtout(Ints _joueurs,
                                                 IdMap<Suit,CustList<HandTarot>> _cartesCertaines, Rate _nbAtoutMin) {
        Ints joueurs_ = new Ints();
        for (int joueur_ : _joueurs) {
            if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).total() < _nbAtoutMin.ll()) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static boolean nePeutAvoirFigures(
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles, int _numero,
            Suit _couleur) {
        return _cartesPossibles.getVal(_couleur).get(_numero).estVide()
                || !_cartesPossibles.getVal(_couleur).get(_numero).premiereCarte()
                .isCharacter();
    }
    static boolean pasAtout(
            Ints _joueursDeNonConfianceNonJoue,
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles) {
        boolean pasAtout_ = true;
        for (int joueur_ : _joueursDeNonConfianceNonJoue) {
            if (!_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                pasAtout_ = false;
            }
        }
        return pasAtout_;
    }

    static boolean defausse(IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                            int _numero, Suit _couleur) {
        return _cartesPossibles.getVal(Suit.TRUMP).get(_numero).estVide()
                && _cartesPossibles.getVal(_couleur).get(_numero).estVide();
    }
    static boolean vaCouper(Suit _couleur, int _joueur,
                            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                            IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        return _cartesPossibles.getVal(_couleur).get(_joueur).estVide()
                && !_cartesCertaines.getVal(Suit.TRUMP).get(_joueur).estVide();
    }
    static boolean peutCouper(Suit _couleur, int _numero,
                              IdMap<Suit,CustList<HandTarot>> _cartesPossibles) {
        return _cartesPossibles.getVal(_couleur).get(_numero).estVide()
                && !_cartesPossibles.getVal(Suit.TRUMP).get(_numero).estVide();
    }
    static boolean nePeutCouper(Suit _couleur, int _numero,
                                IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                IdMap<Suit,CustList<HandTarot>> _cartesCertaines) {
        return _cartesPossibles.getVal(Suit.TRUMP).get(_numero).estVide()
                || !_cartesCertaines.getVal(_couleur).get(_numero).estVide();
    }
}
