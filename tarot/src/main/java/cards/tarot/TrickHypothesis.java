package cards.tarot;

import cards.consts.PossibleTrickWinner;
import cards.consts.Status;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.PlayingDog;
import code.maths.Rate;
import code.util.*;

public final class TrickHypothesis {

    private TrickHypothesis(){
    }
    static void hypothesesRepartitionsJoueurs(TeamsRelation _teamReal, HandTarot _calledCards,
                                       BidTarot _bid,
                                       CustList<TrickTarot> _plisFaits, byte _numero,
                                       EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
                                       EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        byte nombreJoueurs_ = _teamReal.getNombreDeJoueurs();
        boolean appelesTousConnus_ = true;
        for(CardTarot c: _calledCards) {
            boolean trouve_ = false;
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
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
        if(appelesTousConnus_) {
            _teamReal.determinerConfiance(_numero, nombreJoueurs_);
            return;
        }
        Numbers<Byte> joueursNonConfiancePresqueSure_ = new Numbers<Byte>();
        for(CardTarot c: _calledCards) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (_cartesPossibles.getVal(c.couleur())
                        .get(joueur_).estVide()) {
                    //si le joueur ne possede pas de la couleur appele
                    if (_teamReal.aPourDefenseur(_numero)) {
                        _teamReal.faireConfiance(_numero, joueur_);
                    } else {
                        //numero == preneur
                        if(!joueursNonConfiancePresqueSure_.containsObj(joueur_)) {
                            joueursNonConfiancePresqueSure_.add(joueur_);
                        }
                    }
                }
            }
        }
        boolean ramasseurDuPliAvecPetitNonPreneur_ = false;
        boolean arreterRechercheJoueurJoueCartePoint_;
        for (byte j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //iteration sur la confiance du joueur numero en le joueur j
            //vis a vis du Petit joue en premier atout mais jamais virtuellement maitre
            //le Petit doit etre ramasse par le plus grand atout encore en jeu
            //ramasse par un autre atout
            boolean passerAuJoueurSuivant_ = false;
            byte ramasseur_ = -1;
            for(TrickTarot p: _plisFaits) {
                if(!p.getVuParToutJoueur()) {
                    continue;
                }
                CardTarot carte_ = p.carteDuJoueur(j);
                if(carte_.couleur() != Suit.TRUMP) {
                    continue;
                }
                //arret de la boucle sur les plis des que le joueur a joue un atout
                if(carte_ != CardTarot.petit()) {
                    //Si le joueur j n'a pas joue le Petit en tant que premier atout,
                    //alors on passe au joueur suivant
                    passerAuJoueurSuivant_ = true;
                    break;
                }
                Suit couleurDemandee_ = p.couleurDemandee();
                byte forcePetit_ = carte_.strength(couleurDemandee_);
                boolean petitRamasse_ = false;
                for(byte j2_: p.joueursAyantJoueAvant(j, _teamReal.getRules().getDealing())) {
                    if(p.carteDuJoueur(j2_).strength(couleurDemandee_) < forcePetit_) {
                        continue;
                    }
                    //la carte du joueur j2 est un atout ramassant temporairement le Petit
                    petitRamasse_ = true;
                    break;
                }
                if(!petitRamasse_) {
                    break;
                }
                //carte == CarteTarot.petit()
                ramasseur_ = p.getRamasseur();
                if(ramasseur_ == j) {
                    //Si le joueur j a ramasse le pli avec le Petit,
                    //alors on passe au joueur suivant
                    passerAuJoueurSuivant_ = true;
                }
                //ramasseur != -1 && passerAuJoueurSuivant = false
                break;
            }
            if(ramasseur_ == -1) {
                return;
            }
            if(passerAuJoueurSuivant_) {
                continue;
            }
            //ramasseur != j && ramasseur != -1
            if(_teamReal.aPourDefenseur(_numero)) {
                if(ramasseur_ == _teamReal.getTaker() || j == _teamReal.getTaker()) {
                    _teamReal.fixConfidenceDefender(_numero, nombreJoueurs_);
                    return;
                }
                //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
                //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
                //de plus numero est un defenseur
                _teamReal.faireConfiance(_numero, j);
                _teamReal.faireConfiance(_numero, ramasseur_);
            } else {
                //!aPourDefenseur(numero) ==> numero == preneur ==> j == appele
                if(_numero == ramasseur_) {
                    _teamReal.faireConfiance(_numero, j);
                    return;
                }
                ramasseurDuPliAvecPetitNonPreneur_ = true;
                addPotentialFoePlayers(joueursNonConfiancePresqueSure_, j,
                        ramasseur_);
            }
        }
        arreterRechercheJoueurJoueCartePoint_ = false;
        for (byte j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant l'entameur du Petit
            byte ramasseur_ = -1;
            for(TrickTarot p: _plisFaits) {
                if(!p.getVuParToutJoueur()) {
                    continue;
                }
                if(j != p.getEntameur()) {
                    continue;
                }
                //le joueur j a entame
                CardTarot carte_ = p.carteDuJoueur(j);
                if(carte_ != CardTarot.petit()) {
                    continue;
                }
                //Entame du Petit par le joueur j
                ramasseur_ = p.getRamasseur(nombreJoueurs_);
                if(ramasseur_ == j) {
                    //Si le joueur j a entame et ramasse le pli avec le Petit,
                    //alors on passe au joueur suivant
                    arreterRechercheJoueurJoueCartePoint_ = true;
                }
                //ramasseur != -1 && passerAuJoueurSuivant = false
                break;
            }
            if(ramasseur_ == -1) {
                return;
            }
            if(arreterRechercheJoueurJoueCartePoint_) {
                break;
            }
            //le ramasseur du pli et le joueur du Petit (entameur) sont dans la meme equipe
            if(_teamReal.aPourDefenseur(_numero)) {
                if(ramasseur_ == _teamReal.getTaker() || j == _teamReal.getTaker()) {
                    _teamReal.fixConfidenceDefender(_numero, nombreJoueurs_);
                    return;
                }
                //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
                //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
                //de plus numero est un defenseur
                _teamReal.faireConfiance(_numero, j);
                _teamReal.faireConfiance(_numero, ramasseur_);
            } else {
                //!aPourDefenseur(numero) ==> numero == preneur ==> j == appele
                if(_numero == ramasseur_) {
                    _teamReal.faireConfiance(_numero, j);
                    return;
                }
                ramasseurDuPliAvecPetitNonPreneur_ = true;
                addPotentialFoePlayers(joueursNonConfiancePresqueSure_, j,
                        ramasseur_);
            }
        }
        arreterRechercheJoueurJoueCartePoint_ = false;
        for (byte j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
            byte ramasseur_ = -1;
            byte nombreAtoutsJouesAvantPetit_ = 0;
            boolean petitJoueDemandeAtout_ = false;
            boolean defausse_ = DoneTrickInfo.defausseTarot(j, _plisFaits);
            for(TrickTarot p: _plisFaits) {
                if(!p.getVuParToutJoueur()) {
                    continue;
                }
                CardTarot carte_ = p.carteDuJoueur(j);
                if(petitJoueDemandeAtout_) {
                    if(!defausse_) {
                        arreterRechercheJoueurJoueCartePoint_ = true;
                        break;
                    }
                }
                if(carte_.couleur() != Suit.TRUMP) {
                    continue;
                }
                if(!petitJoueDemandeAtout_) {
                    nombreAtoutsJouesAvantPetit_++;
                }
                if(p.couleurDemandee() != Suit.TRUMP) {
                    continue;
                }
                if(carte_ != CardTarot.petit()) {
                    continue;
                }
                if(nombreAtoutsJouesAvantPetit_ == 0) {
                    arreterRechercheJoueurJoueCartePoint_ = true;
                    break;
                }
                ramasseur_ = p.getRamasseur(nombreJoueurs_);
                if(ramasseur_ != _teamReal.getTaker()) {
                    arreterRechercheJoueurJoueCartePoint_ = true;
                    break;
                }
                petitJoueDemandeAtout_ = true;
            }
            if(ramasseur_ == -1) {
                continue;
            }
            if(arreterRechercheJoueurJoueCartePoint_) {
                break;
            }
            //le ramasseur du pli et le joueur du Petit (entameur) sont dans la meme equipe
            if(_teamReal.aPourDefenseur(_numero)) {
                //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
                //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
                //de plus numero est un defenseur
                _teamReal.faireConfiance(_numero, j);
                // confiance en j
            } else {
                //!aPourDefenseur(numero) ==> numero == preneur ==> mefiance de j
                ramasseurDuPliAvecPetitNonPreneur_ = true;
                if(!joueursNonConfiancePresqueSure_.containsObj(j)) {
                    joueursNonConfiancePresqueSure_.add(j);
                }
            }
        }
        for (byte j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant les figures jouees au premier tour d'une couleur ordinaire demandee
            for(Suit c: Suit.couleursOrdinaires()) {
                HandTarot cartesCouleurJouees_ = new HandTarot();
                boolean passerCouleurSuivante_ = false;
                int nbTours_ = 0;
                byte ramasseurVirtuel_ = -1;
                for(TrickTarot p: _plisFaits) {
                    if(!p.getVuParToutJoueur()) {
                        continue;
                    }
                    Suit couleurDemandee_ = p.couleurDemandee();
                    if(couleurDemandee_ != c) {
                        continue;
                    }
                    nbTours_++;
                    CardTarot carteJouee_ = p.carteDuJoueur(j);
                    //Premier tour a la couleur demandee c
                    if(carteJouee_.couleur() != c) {
                        passerCouleurSuivante_ = true;
                        break;
                    }
                    //carteJouee est une carte de la couleur demandee au premier tour
                    if(!carteJouee_.isCharacter()) {
                        passerCouleurSuivante_ = true;
                        break;
                    }
                    //carteJouee est une figure de la couleur demandee au premier tour
                    boolean carteJoueeRamassee_ = false;
                    byte max_ = carteJouee_.strength(c);
                    for(byte j2_: p.joueursAyantJoueAvant(j, _teamReal.getRules().getDealing())) {
                        CardTarot carteJoueeAvant_ = p.carteDuJoueur(j2_);
                        if(carteJoueeAvant_.strength(c) < max_) {
                            continue;
                        }
                        max_ = carteJoueeAvant_.strength(c);
                        ramasseurVirtuel_ = j2_;
                        carteJoueeRamassee_ = true;
                    }
                    if(!carteJoueeRamassee_) {
                        passerCouleurSuivante_ = true;
                        break;
                    }
                    cartesCouleurJouees_.ajouter(carteJouee_);
                    if(ramasseurVirtuel_ == j) {
                        passerCouleurSuivante_ = true;
                    }
                    break;
                }
                if(ramasseurVirtuel_ == -1) {
                    continue;
                }
                if(nbTours_ < 1) {
                    continue;
                }
                if(passerCouleurSuivante_) {
                    continue;
                }
                boolean autreCarteCouleurJouee_ = false;
                boolean figureJouee_ = false;
                CardTarot premiereFigureJouee_ = cartesCouleurJouees_.premiereCarte();
                for(TrickTarot p: _plisFaits) {
                    if(!p.getVuParToutJoueur()) {
                        continue;
                    }
                    CardTarot carteJouee_ = p.carteDuJoueur(j);
                    if(carteJouee_.couleur() != premiereFigureJouee_.couleur()) {
                        continue;
                    }
                    if(carteJouee_ == premiereFigureJouee_) {
                        figureJouee_ = true;
                        continue;
                    }
                    if(!figureJouee_) {
                        continue;
                    }
                    if(carteJouee_.points() > premiereFigureJouee_.points()) {
                        continue;
                    }
                    //la carte jouee est une carte inferieure a la premiere en points mais de la meme couleur
                    autreCarteCouleurJouee_ = true;
                    break;
                }
                if(!autreCarteCouleurJouee_) {
                    continue;
                }
                if(_teamReal.aPourDefenseur(_numero)) {
                    if(ramasseurVirtuel_ == _teamReal.getTaker() || j == _teamReal.getTaker()) {
                        _teamReal.fixConfidenceDefender(_numero, nombreJoueurs_);
                        return;
                    }
                    //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
                    //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
                    //de plus numero est un defenseur
                    _teamReal.faireConfiance(_numero, j);
                    _teamReal.faireConfiance(_numero, ramasseurVirtuel_);
                } else {
                    //!aPourDefenseur(numero) ==> numero == preneur ==> j == appele
                    if(_numero == ramasseurVirtuel_) {
                        _teamReal.faireConfiance(_numero, j);
                        return;
                    }
                    ramasseurDuPliAvecPetitNonPreneur_ = true;
                    addPotentialFoePlayers(joueursNonConfiancePresqueSure_, j,
                            ramasseurVirtuel_);
                }
            }

        }
        for (byte j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant les figures defaussese sur demande d'atout
            for(Suit c: Suit.couleursOrdinaires()) {
                HandTarot cartesCouleurJouees_ = new HandTarot();
                int nbTours_ = 0;
                byte ramasseurVirtuel_ = -1;
                for(TrickTarot p: _plisFaits) {
                    if(!p.getVuParToutJoueur()) {
                        continue;
                    }
                    Suit couleurDemandee_ = p.couleurDemandee();
                    if(couleurDemandee_ != Suit.TRUMP) {
                        continue;
                    }
                    nbTours_++;
                    CardTarot carteJouee_ = p.carteDuJoueur(j);
                    //Premier tour d'atout
                    if(carteJouee_.couleur() != c) {
                        continue;
                    }
                    //carteJouee est une carte defaussee de la couleur c
                    if(!carteJouee_.isCharacter()) {
                        continue;
                    }
                    //carteJouee est une figure defaussee de la couleur c
                    ramasseurVirtuel_ = p.getRamasseur(nombreJoueurs_);
                    //ramasseurVirtuel != j, car une defause ne permet JAMAIS de prendre la main
                    cartesCouleurJouees_.ajouter(carteJouee_);
                    break;
                }
                if(ramasseurVirtuel_ == -1) {
                    continue;
                }
                if(nbTours_ < 1) {
                    continue;
                }
                boolean autreCarteCouleurJouee_ = false;
                boolean figureJouee_ = false;
                CardTarot premiereFigureJouee_ = cartesCouleurJouees_.premiereCarte();
                for(TrickTarot p: _plisFaits) {
                    if(!p.getVuParToutJoueur()) {
                        continue;
                    }
                    CardTarot carteJouee_ = p.carteDuJoueur(j);
                    if(carteJouee_.couleur() != premiereFigureJouee_.couleur()) {
                        continue;
                    }
                    if(carteJouee_ == premiereFigureJouee_) {
                        figureJouee_ = true;
                        continue;
                    }
                    if(!figureJouee_) {
                        continue;
                    }
                    if(carteJouee_.points() > premiereFigureJouee_.points()) {
                        continue;
                    }
                    //la carte jouee est une carte inferieure a la premiere en points mais de la meme couleur
                    autreCarteCouleurJouee_ = true;
                    break;
                }
                if(!autreCarteCouleurJouee_) {
                    continue;
                }
                if(_teamReal.aPourDefenseur(_numero)) {
                    if(ramasseurVirtuel_ == _teamReal.getTaker() || j == _teamReal.getTaker()) {
                        _teamReal.fixConfidenceDefender(_numero, nombreJoueurs_);
                        return;
                    }
                    //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
                    //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
                    //de plus numero est un defenseur
                    _teamReal.faireConfiance(_numero, j);
                    _teamReal.faireConfiance(_numero, ramasseurVirtuel_);
                } else {
                    //!aPourDefenseur(numero) ==> numero == preneur ==> j == appele
                    if(_numero == ramasseurVirtuel_) {
                        _teamReal.faireConfiance(_numero, j);
                        return;
                    }
                    ramasseurDuPliAvecPetitNonPreneur_ = true;
                    addPotentialFoePlayers(joueursNonConfiancePresqueSure_, j,
                            ramasseurVirtuel_);
                }
            }

        }
        appelesTousConnus_ = _teamReal.allKnownCalledPlayers(_calledCards,_cartesCertaines,
                nombreJoueurs_);
        if(appelesTousConnus_) {
            _teamReal.determinerConfiance(_numero, nombreJoueurs_);
            return;
        }
        for(CardTarot c: _calledCards) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (_cartesPossibles.getVal(c.couleur())
                        .get(joueur_).estVide()) {
                    //si le joueur ne possede pas de la couleur appele
                    if (_teamReal.aPourDefenseur(_numero)) {
                        _teamReal.faireConfiance(_numero, joueur_);
                    } else {
                        //numero == preneur
                        if(!joueursNonConfiancePresqueSure_.containsObj(joueur_)) {
                            joueursNonConfiancePresqueSure_.add(joueur_);
                        }
                    }
                }
            }
        }
        if(ramasseurDuPliAvecPetitNonPreneur_) {
            if(joueursNonConfiancePresqueSure_.size() + 2 == nombreJoueurs_ && _bid.getJeuChien() == PlayingDog.WITH) {
                for(byte a: _teamReal.getCalledPlayers()) {
                    _teamReal.faireConfiance(_teamReal.getTaker(), a);
                }
            }
        }
    }
    static void addPotentialFoePlayers(Numbers<Byte> _potentialFoesNearlySure,
                                       byte _otherPlayer, byte _leader) {
        if(!_potentialFoesNearlySure.containsObj(_leader)) {
            _potentialFoesNearlySure.add(_leader);
        }
        if(!_potentialFoesNearlySure.containsObj(_otherPlayer)) {
            _potentialFoesNearlySure.add(_otherPlayer);
        }
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
     @param _carteForte
     la carte qui est en train de dominer le pli
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
            TarotInfoPliEnCours _info,
            byte _numero,
            CardTarot _carteForte) {
        TeamsRelation teamRel_ = _info.getTeamsRelation();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        Numbers<Byte> joueursNonJoue_ = _info.getJoueursNonJoue();
        EnumList<Suit> couleursAppelees_ = _info.getCalledSuits();
        boolean carteAppeleeJouee_ =_info.isCarteAppeleeJouee();
        Suit couleurDemandee_ = _info.getProgressingTrick().couleurDemandee();
        boolean ramasseurVirtuelEgalCertain_;
        Numbers<Byte> joueursNonConfianceNonJoue_ = new Numbers<Byte>(
                joueursNonJoue_);
        Numbers<Byte> joueursConfianceNonJoue_ = new Numbers<Byte>(
                joueursNonJoue_);
        byte nombreDeJoueurs_ = teamRel_.getNombreDeJoueurs();
        Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = teamRel_.joueursNonConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
        joueursNonConfianceNonJoue_.retainAllElements(joueursNonConfiance_);
        joueursConfianceNonJoue_.retainAllElements(joueursConfiance_);
        Numbers<Byte> joueursJoue_ = _info.getJoueursJoue();
        if (_carteForte.couleur() == Suit.TRUMP && Suit.couleursOrdinaires().containsObj(couleurDemandee_)) {
        /*
        Le pli est
        coupe
        */
            if (!cartesCertaines_.getVal(couleurDemandee_).get(_numero).estVide()
                    || cartesCertaines_.getVal(Suit.TRUMP).get(_numero).estVide()
                    || cartesCertaines_.getVal(Suit.TRUMP).get(_numero).premiereCarte()
                    .strength(couleurDemandee_) < _carteForte.strength(couleurDemandee_)) {
                    /*
                    Le joueur
                    numero ne
                    peut pas
                    prendre la
                    main
                    */
                if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                    if (couleursAppelees_.containsObj(couleurDemandee_)
                            && !carteAppeleeJouee_ && teamRel_.aPourDefenseur(_numero)) {
                        /* The player, probably called by the current taker
                        and still owing one called card of the current led suit,
                        must follow a card belonging to the current demanded suit.*/
                        Numbers<Byte> joueursNonConfiancePreneur_ = new Numbers<Byte>();
                        for (byte j: joueursNonConfianceNonJoue_) {
                            if (teamRel_.statutDe(j) != Status.TAKER) {
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
                            couleurDemandee_, _carteForte, cartesPossibles_,
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
                            joueursConfianceNonJoue_, couleurDemandee_,
                            cartesPossibles_, cartesCertaines_)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    /*
                    On cherche les joueurs de confiance battant de maniere
                    certaine les joueurs de non confiance n'ayant pas joue ou
                    possedant des cartes que les joueurs ayant joue n'ont pas
                    ainsi que les joueurs de non confiance n'ayant pas joue
                    */
                    if (existeJoueurNonJoueBattantPtm(
                            joueursNonConfianceNonJoue_,
                            joueursConfianceNonJoue_, joueursJoue_,
                            couleurDemandee_, cartesPossibles_,
                            cartesCertaines_)) {
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
                            joueursNonConfianceNonJoue_, couleurDemandee_,
                            _carteForte, cartesPossibles_, cartesCertaines_)) {
                        return PossibleTrickWinner.FOE_TEAM;
                    }
                    /*
                    On cherche les joueurs de non confiance battant de
                    maniere certaine les joueurs de confiance n'ayant pas
                    joue ou possedant des cartes que les joueurs ayant joue
                    n'ont pas ainsi que les joueurs de non confiance n'ayant
                    pas joue
                    */
                    if (existeJoueurNonJoueBattantPtm(
                            joueursConfianceNonJoue_,
                            joueursNonConfianceNonJoue_, joueursJoue_,
                            couleurDemandee_, cartesPossibles_,
                            cartesCertaines_)) {
                        return PossibleTrickWinner.FOE_TEAM;
                    }
                    return PossibleTrickWinner.UNKNOWN;
                }
                /*
                ramasseurVirtuel n'est pas un joueur de confiance pour le
                joueur numero
                */
                if (couleursAppelees_.containsObj(couleurDemandee_) && !carteAppeleeJouee_
                        && _numero == teamRel_.getTaker()) {
                    Numbers<Byte> joueursConfianceNonJoueDiffAppele_ = new Numbers<Byte>();
                    for (byte j: joueursConfianceNonJoue_) {
                        if (teamRel_.statutDe(j) == Status.CALLED_PLAYER) {
                            continue;
                        }
                        joueursConfianceNonJoueDiffAppele_.add(j);
                    }
                    joueursConfianceNonJoue_ = joueursConfianceNonJoueDiffAppele_;
                }
                /*
                On cherche a savoir si le ramasseur virtuel (joueur de non
                confiance) bat tous les joueurs de confiance n'ayant pas joue
                */
                if (ramasseurBatAdvSur(joueursConfianceNonJoue_,
                        couleurDemandee_, _carteForte, cartesPossibles_,
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
                        joueursNonConfianceNonJoue_, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurNonJoueBattantPtm(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
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
                        joueursConfianceNonJoue_, couleurDemandee_,
                        _carteForte, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurNonJoueBattantPtm(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
                /* Fin joueurDeConfiance.contains(ramasseurVirtuel) */
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
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
            if (existeJoueurBatAdvNum(joueursNonConfianceNonJoue_,
                    joueursConfianceNonJoue_, _numero, couleurDemandee_,
                    cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
            if (existeJoueurBatPtmNum(joueursNonConfianceNonJoue_,
                    joueursConfianceNonJoue_, joueursJoue_, _numero,
                    couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*
            On cherche les joueurs de non confiance battant de maniere
            certaine les joueurs de confiance n'ayant pas joue ou possedant
            des cartes que les joueurs ayant joue n'ont pas ainsi que les
            joueurs de non confiance n'ayant pas joue
            */
            if (existeJoueurBatAdvNum(joueursConfianceNonJoue_,
                    joueursNonConfianceNonJoue_, _numero, couleurDemandee_,
                    cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
            if (existeJoueurBatPtmNum(joueursConfianceNonJoue_,
                    joueursNonConfianceNonJoue_, joueursJoue_, _numero,
                    couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        if (_carteForte.couleur() == couleurDemandee_ && Suit.couleursOrdinaires().containsObj(couleurDemandee_)) {
            /* La couleur demandee n 'est pas de l 'atout et le pli n 'est pas coupe */
            ramasseurVirtuelEgalCertain_ = false;
            for (byte joueur_ : joueursConfianceNonJoue_) {
                if (vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (ramasseurVirtuelEgalCertain_) {
                /*
            Si un joueur de confiance n
            ayant pas joue va surement
            couper le pli
            */
                for (byte joueur_ : joueursNonConfianceNonJoue_) {
                    if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                        ramasseurVirtuelEgalCertain_ = false;
                    }
                }
                if (ramasseurVirtuelEgalCertain_) {
                    /*
                Si aucun joueur de non
                confiance n ayant pas
                joue ne va couper le pli
                */
                    return PossibleTrickWinner.TEAM;
                }
                if (existeJoueurNonJoueBattantAdv(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                if (existeJoueurNonJoueBattantPtm(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                if (existeJoueurNonJoueBattantAdv(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if (existeJoueurNonJoueBattantPtm(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            ramasseurVirtuelEgalCertain_ = false;
            for (byte joueur_ : joueursNonConfianceNonJoue_) {
                if (vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (ramasseurVirtuelEgalCertain_) {
                /*
            Si un joueur de non confiance
            n ayant pas joue va surement
            couper le pli
            */
                for (byte joueur_ : joueursConfianceNonJoue_) {
                    if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                        ramasseurVirtuelEgalCertain_ = false;
                    }
                }
                if (ramasseurVirtuelEgalCertain_) {
                    /*
                Si aucun joueur de
                confiance n ayant pas
                joue ne va couper le pli
                */
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if (existeJoueurNonJoueBattantAdv(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if (existeJoueurNonJoueBattantPtm(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if (existeJoueurNonJoueBattantAdv(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                if (existeJoueurNonJoueBattantPtm(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            if (!cartesPossibles_.getVal(couleurDemandee_).get(_numero).estVide()
                    && cartesPossibles_.getVal(couleurDemandee_).get(_numero)
                    .premiereCarte().strength(couleurDemandee_) > _carteForte.strength(couleurDemandee_)) {
                /* Si le joueur numero peut prendre la main sans couper */
                /*
                On ne sait pas si un joueur n'ayant pas joue va couper le pli
                ou non
                */
                if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                    if (ramasseurBatSsCprAdv(
                            joueursNonConfianceNonJoue_, couleurDemandee_,
                            _carteForte, cartesPossibles_, cartesCertaines_)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    return PossibleTrickWinner.UNKNOWN;
                }
                /* Fin joueursDeConfiance.contains(ramasseurVirtuel) */
                return PossibleTrickWinner.UNKNOWN;
            }
            /* Fin si le joueur numero peut prendre la main sans couper */
            if (peutCouper(couleurDemandee_, _numero, cartesPossibles_)) {
                /* Si le joueur
                numero peut
                prendre la
                main en coupant
                */
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurBatAdvNum(joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, _numero, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurBatPtmNum(joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, joueursJoue_, _numero,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de non confiance battant de maniere
                certaine les joueurs de confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurBatAdvNum(joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, _numero,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*
                On cherche les joueurs de non confiance battant de maniere
                certaine les joueurs de confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurBatPtmNum(joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, joueursJoue_, _numero,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /* Le joueur numero ne peut pas prendre la main */
            if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                if (ramasseurBatSsCprAdv(joueursNonConfianceNonJoue_,
                        couleurDemandee_, _carteForte, cartesPossibles_,
                        cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /* Fin joueursDeConfiance.contains(ramasseurVirtuel) */
            /* Maintenant le ramasseur virtuel n'est pas un joueur de confiance */
            if (ramasseurBatSsCprAdv(joueursConfianceNonJoue_,
                    couleurDemandee_, _carteForte, cartesPossibles_,
                    cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        /* Le pli n'est pas coupe et la couleur demandee est l'atout */
        if (cartesCertaines_.getVal(Suit.TRUMP).get(_numero).estVide()
                || cartesCertaines_.getVal(Suit.TRUMP).get(_numero).premiereCarte().strength(couleurDemandee_) < _carteForte
                .strength(couleurDemandee_)) {
            /*
                Si le joueur numero ne peut pas prendre
                la main sur demande d'atout
                */
            if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                /*
                Si le ramasseur virtuel (de confiance, ici) domine
                certainement les joueurs de non confiance n'ayant pas joue
                */
                if (ramasseurBatAdvDemat(joueursNonConfianceNonJoue_,
                        _carteForte, cartesPossibles_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJouBatAdvDemat(joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, cartesPossibles_,
                        cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJouBatPtmDemat(joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, joueursJoue_,
                        cartesPossibles_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de non confiance battant de maniere
                certaine les joueurs de confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJouBatAdvSurDemat(joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, _carteForte,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*
                On cherche les joueurs de non confiance battant de maniere
                certaine les joueurs de confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJouBatPtmSurDemat(joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, joueursJoue_,
                        _carteForte, cartesPossibles_)) {
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
            if (ramasseurBatAdvDemat(joueursConfianceNonJoue_,
                    _carteForte, cartesPossibles_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*
            On cherche les joueurs de non confiance battant de maniere
            certaine les joueurs de confiance n'ayant pas joue ou possedant
            des cartes que les joueurs ayant joue n'ont pas ainsi que les
            joueurs de non confiance n'ayant pas joue
            */
            if (existeJouBatAdvDemat(joueursConfianceNonJoue_,
                    joueursNonConfianceNonJoue_, cartesPossibles_,
                    cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*
            On cherche les joueurs de non confiance battant de maniere
            certaine les joueurs de confiance n'ayant pas joue ou possedant
            des cartes que les joueurs ayant joue n'ont pas ainsi que les
            joueurs de non confiance n'ayant pas joue
            */
            if (existeJouBatPtmDemat(joueursConfianceNonJoue_,
                    joueursNonConfianceNonJoue_, joueursJoue_,
                    cartesPossibles_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
            if (existeJouBatAdvSurDemat(joueursNonConfianceNonJoue_,
                    joueursConfianceNonJoue_, _carteForte, cartesPossibles_,
                    cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
            if (existeJouBatPtmSurDemat(joueursNonConfianceNonJoue_,
                    joueursConfianceNonJoue_, joueursJoue_, _carteForte,
                    cartesPossibles_)) {
                return PossibleTrickWinner.TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
            /* Fin joueurDeConfiance.contains(ramasseurVirtuel) */
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
        /*
        On cherche les joueurs de confiance battant de maniere certaine les
        joueurs de non confiance n'ayant pas joue ou possedant des cartes que
        les joueurs ayant joue n'ont pas ainsi que les joueurs de non
        confiance n'ayant pas joue
        */
        if (existeJouBatAdvNumDemat(joueursNonConfianceNonJoue_,
                joueursConfianceNonJoue_, _numero, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*
        On cherche les joueurs de confiance battant de maniere certaine les
        joueurs de non confiance n'ayant pas joue ou possedant des cartes que
        les joueurs ayant joue n'ont pas ainsi que les joueurs de non
        confiance n'ayant pas joue
        */
        if (existeJouBatPtmNumDemat(joueursNonConfianceNonJoue_,
                joueursConfianceNonJoue_, joueursJoue_, _numero,
                cartesPossibles_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*
        On cherche les joueurs de non confiance battant de maniere certaine
        les joueurs de confiance n'ayant pas joue ou possedant des cartes que
        les joueurs ayant joue n'ont pas ainsi que les joueurs de non
        confiance n'ayant pas joue
        */
        if (existeJouBatAdvNumDemat(joueursConfianceNonJoue_,
                joueursNonConfianceNonJoue_, _numero, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        /*
        On cherche les joueurs de non confiance battant de maniere certaine
        les joueurs de confiance n'ayant pas joue ou possedant des cartes que
        les joueurs ayant joue n'ont pas ainsi que les joueurs de non
        confiance n'ayant pas joue
        */
        if (existeJouBatPtmNumDemat(joueursConfianceNonJoue_,
                joueursNonConfianceNonJoue_, joueursJoue_, _numero,
                cartesPossibles_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    private static boolean existeJouBatPtmNumDemat(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue, byte _numero,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for (byte joueur2_ : _joueursJoue) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(_numero).premiereCarte().strength(Suit.TRUMP))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJouBatAdvNumDemat(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom, byte _numero,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(_numero).premiereCarte().strength(Suit.TRUMP))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJouBatPtmSurDemat(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue, CardTarot _carteForte,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for (byte joueur2_ : _joueursJoue) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _carteForte.strength(Suit.TRUMP))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJouBatAdvSurDemat(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            CardTarot _carteForte, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _carteForte.strength(Suit.TRUMP))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJouBatPtmDemat(
            Numbers<Byte> _equipeABattre,Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for (byte joueur2_ : _joueursJoue) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJouBatAdvDemat(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean ramasseurBatAdvDemat(
            Numbers<Byte> _equipeABattre, CardTarot _carteForte,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        boolean ramasseurDeter_ = true;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeABattre) {
            ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                    .get(joueur_).estVide();
            if (!ramasseurVirtuelEgalCertain_) {
                if (_carteForte.strength(Suit.TRUMP) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJoueurBatPtmNum(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue, byte _numero, Suit _couleurDemandee,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for (byte joueur2_ : _joueursJoue) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesCertaines.getVal(Suit.TRUMP).get(_numero).premiereCarte().strength(_couleurDemandee))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJoueurBatAdvNum(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom, byte _numero,
            Suit _couleurDemandee, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesCertaines.getVal(Suit.TRUMP).get(_numero).premiereCarte().strength(_couleurDemandee))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJoueurAdvRamBatAdvSur(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Suit _couleurDemandee, CardTarot _carteForte,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) > _carteForte.strength(_couleurDemandee))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJoueurNonJoueBattantPtm(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue, Suit _couleurDemandee,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for (byte joueur2_ : _joueursJoue) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJoueurNonJoueBattantAdv(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Suit _couleurDemandee, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
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
            boolean joueurBatAdversaire_ = true;
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean ramasseurBatAdvSur(Numbers<Byte> _equipeABattre,
                                              Suit _couleurDemandee, CardTarot _carteForte,
                                              EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
                                              EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurVirtuelEgalCertain_;
        boolean ramasseurDeter_ = true;
        for (byte joueur_ : _equipeABattre) {
            ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(Suit.TRUMP)
                    .get(joueur_).estVide();
            if (!ramasseurVirtuelEgalCertain_) {
                if (_carteForte.strength(_couleurDemandee) > _cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(_couleurDemandee)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur_).estVide()) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }

    private static boolean ramasseurBatSsCprAdv(
            Numbers<Byte> _equipeABattre, Suit _couleurDemandee,
            CardTarot _carteForte, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurVirtuelEgalCertain_;
        boolean ramasseurDeter_ = true;
        for (byte joueur_ : _equipeABattre) {
            ramasseurVirtuelEgalCertain_ = !_cartesCertaines
                    .getVal(_couleurDemandee).get(joueur_).estVide();
            if (ramasseurVirtuelEgalCertain_) {
                if (!(_carteForte.strength(_couleurDemandee) > _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().strength(_couleurDemandee))) {
                    ramasseurVirtuelEgalCertain_ = false;
                }
            }
            if (defausse(_cartesPossibles, joueur_, _couleurDemandee)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }
    static EnumList<Suit> couleursPouvantEtreCoupees(HandTarot _main,
                                                     Numbers<Byte> _joueurs,
                                                     EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (_main.couleur(couleur_).estVide()) {
                continue;
            }
            if(joueursSusceptiblesCoupe(_cartesPossibles, couleur_, _joueurs).isEmpty()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static Numbers<Byte> joueursSusceptiblesCoupe(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Numbers<Byte> _joueurs) {
        Numbers<Byte> joueursSusceptiblesDeCouper_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (peutCouper(_couleurDemandee, joueur_,
                    _cartesPossibles)) {
                joueursSusceptiblesDeCouper_
                        .add(joueur_);
            }
        }
        return joueursSusceptiblesDeCouper_;
    }
    static Numbers<Byte> joueursPouvantCouperCouleurs(HandTarot _main,
                                                      Numbers<Byte> _joueurs,
                                                      EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, EnumList<Suit> _couleurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if(joueurs_.containsObj(joueur_)) {
                continue;
            }
            for (Suit couleur_ : _couleurs) {
                if (_main.couleur(couleur_).estVide()) {
                    continue;
                }
                if (peutCouper(couleur_, joueur_, _cartesPossibles)) {
                    joueurs_.add(joueur_);
                    break;
                }
            }
        }
        return joueurs_;
    }
    static Numbers<Byte> joueursPossedantAtout(Numbers<Byte> _joueurs,
                                               EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static Numbers<Byte> joueursPouvantPossederPetit(Numbers<Byte> _joueurs,
                                                     EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        EqList<HandTarot> atoutsPossibles_ = _cartesPossibles
                .getVal(Suit.TRUMP);
        for (byte joueur_ : _joueurs) {
            if (!atoutsPossibles_.get(joueur_).contient(CardTarot.petit())) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static Numbers<Byte> joueursPossedantAtoutMaitre(Numbers<Byte> _joueurs,
                                                     EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        EqList<HandTarot> atoutsCertains_ = _cartesCertaines
                .getVal(Suit.TRUMP);
        for (byte joueur_ : _joueurs) {
            if (atoutsCertains_.get(joueur_).estVide()) {
                continue;
            }
            HandTarot atouts_ = new HandTarot();
            atouts_.ajouterCartes(atoutsCertains_.get(joueur_));
            HandTarot atoutsMaitres_ = atouts_.atoutsMaitres(cartesJouees_);
            if (atoutsMaitres_.estVide()) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static Numbers<Byte> joueursPossedantNbAtout(Numbers<Byte> _joueurs,
                                                 EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, Rate _nbAtoutMin) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (_cartesCertaines.getVal(Suit.TRUMP).get(joueur_).total() < _nbAtoutMin.ll()) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }
    static boolean nePeutAvoirFigures(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _numero,
            Suit _couleur) {
        return _cartesPossibles.getVal(_couleur).get(_numero).estVide()
                || !_cartesPossibles.getVal(_couleur).get(_numero).premiereCarte()
                .isCharacter();
    }
    static boolean pasAtout(
            Numbers<Byte> _joueursDeNonConfianceNonJoue,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        boolean pasAtout_ = true;
        for (byte joueur_ : _joueursDeNonConfianceNonJoue) {
            if (!(_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).estVide())) {
                pasAtout_ = false;
            }
        }
        return pasAtout_;
    }

    static boolean vaSurcouper(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, byte _numero,
            byte _numeroBis, Suit _couleurDemandee) {
        return _cartesPossibles.getVal(_couleurDemandee).get(_numeroBis).estVide()
                && !_cartesCertaines.getVal(Suit.TRUMP).get(_numeroBis).estVide()
                && _cartesCertaines.getVal(Suit.TRUMP).get(_numeroBis).premiereCarte()
                .strength(_couleurDemandee) > _cartesCertaines.getVal(Suit.TRUMP).get(_numero)
                .premiereCarte().strength(_couleurDemandee);
    }

    static boolean peutSurcouper(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _numero,
            byte _numeroBis, Suit _couleurDemandee) {
        return _cartesPossibles.getVal(_couleurDemandee).get(_numeroBis).estVide()
                && !_cartesPossibles.getVal(Suit.TRUMP).get(_numeroBis).estVide()
                && _cartesPossibles.getVal(Suit.TRUMP).get(_numeroBis).premiereCarte()
                .strength(_couleurDemandee) > _cartesPossibles.getVal(Suit.TRUMP).get(_numero)
                .premiereCarte().strength(_couleurDemandee);
    }

    static boolean defausse(EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
                            byte _numero, Suit _couleur) {
        return _cartesPossibles.getVal(Suit.TRUMP).get(_numero).estVide()
                && _cartesPossibles.getVal(_couleur).get(_numero).estVide();
    }
    static boolean vaCouper(Suit _couleur, byte _joueur,
                            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
                            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        return _cartesPossibles.getVal(_couleur).get(_joueur).estVide()
                && !_cartesCertaines.getVal(Suit.TRUMP).get(_joueur).estVide();
    }
    static boolean peutCouper(Suit _couleur, byte _numero,
                              EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        return _cartesPossibles.getVal(_couleur).get(_numero).estVide()
                && !_cartesPossibles.getVal(Suit.TRUMP).get(_numero).estVide();
    }
    static boolean nePeutCouper(Suit _couleur, byte _numero,
                                EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
                                EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        return _cartesPossibles.getVal(Suit.TRUMP).get(_numero).estVide()
                || !_cartesCertaines.getVal(_couleur).get(_numero).estVide();
    }
}
