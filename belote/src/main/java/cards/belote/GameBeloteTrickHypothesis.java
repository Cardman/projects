package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.PossibleTrickWinner;
import cards.consts.Suit;
import code.util.EnumMap;
import code.util.EqList;
import code.util.*;

public final class GameBeloteTrickHypothesis {
    private GameBeloteTrickHypothesis() {
    }
    /**Retourne vrai si et seulement si le joueur ne peut pas fournir la couleur donnee et peut couper avec un atout*/
    static boolean peutCouper(Suit _couleur, byte _numero, EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, Suit _couleurAtout) {
        return GameBeloteCommon.hand(_cartesPossibles,_couleur,_numero).estVide()&&!GameBeloteCommon.hand(_cartesPossibles,_couleurAtout,_numero).estVide();
    }
    static boolean vaCouper(Suit _couleur,byte _joueur,
                            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
                            EnumMap<Suit,EqList<HandBelote>> _cartesCertaines,
                            Suit _couleurAtout) {
        return GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide()&&!GameBeloteCommon.hand(_cartesCertaines,_couleurAtout,_joueur).estVide();
    }
    /**Renvoie un entier 0 si joueur de non confiance qui va faire le pli 1 si joueur de confiance va faire le pli et -1 sinon
     @param cartes_possibles l'ensemble des cartes probablement possedees par les joueurs
     @param cartes_certaines l'ensemble des cartes surement possedees par les joueurs
     @param ramasseur_virtuel le joueur, qui sans les cartes jouees par les derniers joueurs du pli est ramasseur
     @param joueurs_non_joue l'ensemble des joueurs n'ayant pas encore joue leur carte
     */
    static PossibleTrickWinner equipeQuiVaFairePliCouleurDominante(
            BeloteInfoPliEnCours _info) {
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        Bytes joueursNonJoue_=_info.getJoueursNonJoue();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        byte next_ = _info.getNextPlayer();
        Bytes partenaire_ = _info.getJoueursConfiance();
        Bytes adversaires_=_info.getJoueursNonConfiance();
        Suit couleurDemandee_=_info.getProgressingTrick().couleurDemandee();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurVirtuelEgalCertain_=false;
        byte nbPlayers_ = _info.getNbPlayers();
        CardBelote carteForte_=_info.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_,nbPlayers_);
        Bytes joueursNonConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, adversaires_);
        Bytes joueursConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, partenaire_);
        /*Le contrat n est ni sans-atout ni tout atout.*/
        BidBeloteSuit bid_ = _info.getContrat();
        if(carteForte_.couleur()==couleurAtout_&&couleurDemandee_!=couleurAtout_) {
            /*Le pli est coupe*/
            if(!GameBeloteCommon.hand(cartesCertaines_,couleurDemandee_,next_).estVide()||GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,next_).estVide()||
                    GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,next_).premiereCarte().strength(couleurDemandee_, bid_)
                            <carteForte_.strength(couleurDemandee_, bid_)) {
                /*Le joueur numero ne peut pas prendre la main*/
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    /*On cherche a savoir si le ramasseur virtuel (joueur de confiance) va avec sa coupe sur la couleur demandee dominer tous les atouts des joueurs de non confiance eventuels*/
                    if(ramasseurBatAdvSur(_info,joueursNonConfianceNonJoue_, couleurDemandee_, carteForte_)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                    ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                    if(existeJoueurNonJoueBattantAdv(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, couleurDemandee_)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue
                    ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                    if(existeJoueurAdvRamBatAdvSur(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurDemandee_, carteForte_)) {
                        return PossibleTrickWinner.FOE_TEAM;
                    }
                    return PossibleTrickWinner.UNKNOWN;
                }
                /*ramasseurVirtuel n'est pas un joueur de confiance pour le joueur numero*/
                /*On cherche a savoir si le ramasseur virtuel (joueur de non confiance) bat tous les joueurs de confiance n'ayant pas joue*/
                if(ramasseurBatAdvSur(_info,joueursConfianceNonJoue_, couleurDemandee_, carteForte_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurNonJoueBattantAdv(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurAdvRamBatAdvSur(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, couleurDemandee_, carteForte_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
                /*Fin joueurDeConfiance.contains(ramasseurVirtuel)*/
            }
            /*Fin !cartesCertaines.get(couleurDemandee).get(numero).estVide()||cartesCertaines.get(1).get(numero).estVide()||cartesCertaines.get(1).get(numero).premiereCarte().getValeur()<carteForte.getValeur()
            (fin test de possibilite pour le joueur numero de prendre le pli)*/
            /*Le joueur numero peut prendre la main en surcoupant le ramasseur virtuel*/
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJoueurBatAdvNum(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, next_, couleurDemandee_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJoueurBatAdvNum(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, next_, couleurDemandee_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        if(carteForte_.couleur()==couleurDemandee_&&couleurDemandee_!=couleurAtout_) {
            boolean canTrump_ = peutCouper(couleurDemandee_, next_, cartesPossibles_, couleurAtout_);
            /*La couleur demandee n'est pas de l'atout et le pli n'est pas coupe*/
            for(byte joueur_:joueursConfianceNonJoue_) {
                if (vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if(ramasseurVirtuelEgalCertain_ && !canTrump_) {
                for(byte joueur_:joueursNonConfianceNonJoue_) {
                    if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_,_info.getCouleurAtout()))) {
                        ramasseurVirtuelEgalCertain_ = false;
                    }
                }
                if(ramasseurVirtuelEgalCertain_) {
                    return PossibleTrickWinner.TEAM;
                }
                if(existeJoueurNonJoueBattantAdv(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                if(existeJoueurNonJoueBattantAdv(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            for(byte joueur_:joueursNonConfianceNonJoue_) {
                if (vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if(ramasseurVirtuelEgalCertain_ && !canTrump_) {
                for(byte joueur_:joueursConfianceNonJoue_) {
                    if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_,_info.getCouleurAtout()))) {
                        ramasseurVirtuelEgalCertain_ = false;
                    }
                }
                if(ramasseurVirtuelEgalCertain_) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if(existeJoueurNonJoueBattantAdv(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if(existeJoueurNonJoueBattantAdv(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            if(!GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,next_).estVide()
                    &&GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,next_).premiereCarte().strength(couleurDemandee_,bid_)>carteForte_.strength(couleurDemandee_,bid_)) {
                /*Si le joueur numero peut prendre la main sans couper*/
                /*On ne sait pas si un joueur n'ayant pas joue va couper le pli ou non*/
                return PossibleTrickWinner.UNKNOWN;
            }
            /*Fin si le joueur numero peut prendre la main sans couper*/
            if(canTrump_) {
                /*Si le joueur numero peut prendre la main en coupant*/
                Bytes other_ = new Bytes(joueursNonJoue_);
                other_.removeObj(next_);
                CardBelote cardPl_ = cartesPossibles_.getVal(couleurAtout_).get(next_).premiereCarte();
                if (ramasseurBatAdvSur(_info,other_,
                        couleurDemandee_, cardPl_)) {
                    return PossibleTrickWinner.UNKNOWN;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurBatAdvNum(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, next_, couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJoueurBatAdvNum(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, next_, couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /*Le joueur numero ne peut pas prendre la main*/
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                if(ramasseurBatSsCprAdv(_info,joueursNonConfianceNonJoue_, couleurDemandee_, carteForte_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /*Fin joueursDeConfiance.contains(ramasseurVirtuel)*/
            /*Maintenant le ramasseur virtuel n'est pas un joueur de confiance*/
            if(ramasseurBatSsCprAdv(_info,joueursConfianceNonJoue_, couleurDemandee_, carteForte_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        /*Le pli n'est pas coupe et la couleur demandee est l'atout*/
        if(GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,next_).estVide()
                ||GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,next_).premiereCarte().strength(couleurDemandee_,bid_)<carteForte_.strength(couleurDemandee_,bid_)) {
            /*Si le joueur numero ne peut pas prendre la main sur demande d'atout*/
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                /*Si le ramasseur virtuel (de confiance, ici) domine certainement les joueurs de non confiance n'ayant pas joue*/
                if(ramasseurBatAdvDemat(_info,joueursNonConfianceNonJoue_, couleurAtout_, carteForte_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatAdvDemat(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, couleurAtout_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatAdvSurDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, carteForte_, couleurAtout_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /*ramasseurVirtuel n'est pas un joueur de confiance pour le joueur numero*/
            /*Si le ramasseur virtuel (de non confiance, ici) domine certainement les joueurs de non confiance n'ayant pas joue*/
            if(ramasseurBatAdvDemat(_info,joueursConfianceNonJoue_, couleurAtout_, carteForte_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatAdvDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurAtout_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatAdvSurDemat(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, carteForte_, couleurAtout_)) {
                return PossibleTrickWinner.TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
            /*Fin joueurDeConfiance.contains(ramasseurVirtuel)*/
        }
        /*Fin !cartesCertaines.get(couleurDemandee).get(numero).estVide()||cartesCertaines.get(1).get(numero).estVide()||cartesCertaines.get(1).get(numero).premiereCarte().getValeur()<carteForte.getValeur()
            (fin test de possibilite pour le joueur numero de prendre le pli)*/
        /*Le joueur numero peut prendre la main en utilisant un atout sur demande d'atout*/
        CardBelote cardPl_ = cartesCertaines_.getVal(couleurAtout_).get(next_)
                .premiereCarte();
        Bytes other_ = new Bytes(joueursNonJoue_);
        other_.removeObj(next_);
        if (ramasseurBatAdvDemat(_info,other_,
                couleurAtout_,cardPl_)) {
            return PossibleTrickWinner.UNKNOWN;
        }
        /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatAdvNumDemat(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,next_, couleurAtout_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatAdvNumDemat(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_, next_, couleurAtout_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    static PossibleTrickWinner equipeQuiVaFairePliSansAtout(
            BeloteInfoPliEnCours _info) {

        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        Bytes joueursNonJoue_=_info.getJoueursNonJoue();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        byte next_ = _info.getNextPlayer();
        Bytes partenaire_ = _info.getJoueursConfiance();
        Bytes adversaires_=_info.getJoueursNonConfiance();
        Suit couleurDemandee_=_info.getProgressingTrick().couleurDemandee();
        Bytes joueursNonConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, adversaires_);
        Bytes joueursConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, partenaire_);
        byte nbPlayers_ = _info.getNbPlayers();
        CardBelote carteForte_=_info.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_,nbPlayers_);

        /*La couleur demandee n'est pas de l'atout et le pli n'est pas coupe*/
        BidBeloteSuit bid_ = _info.getContrat();
        if(!GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,next_).estVide()
                &&GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,next_).premiereCarte().strength(couleurDemandee_, bid_)>carteForte_.strength(couleurDemandee_, bid_)) {
            /*Si le joueur numero peut prendre la main sans couper*/
            return PossibleTrickWinner.UNKNOWN;
        }
        /*Fin si le joueur numero peut prendre la main sans couper*/
        /*Le joueur numero ne peut pas prendre la main*/
        if(partenaire_.containsObj(ramasseurVirtuel_)) {
            if(ramasseurBatSsCprAdv(_info,joueursNonConfianceNonJoue_, couleurDemandee_, carteForte_)) {
                return PossibleTrickWinner.TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        /*Fin joueursDeConfiance.contains(ramasseurVirtuel)*/
        /*Maintenant le ramasseur virtuel n'est pas un joueur de confiance*/
        if(ramasseurBatSsCprAdv(_info,joueursConfianceNonJoue_, couleurDemandee_, carteForte_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }
    static PossibleTrickWinner equipeQuiVaFairePliToutAtout(
            BeloteInfoPliEnCours _info) {

        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        Bytes joueursNonJoue_=_info.getJoueursNonJoue();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        byte next_ = _info.getNextPlayer();
        Bytes partenaire_ = _info.getJoueursConfiance();
        Bytes adversaires_=_info.getJoueursNonConfiance();
        Suit couleurDemandee_=_info.getProgressingTrick().couleurDemandee();
        Bytes joueursNonConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, adversaires_);
        Bytes joueursConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, partenaire_);
        byte nbPlayers_ = _info.getNbPlayers();
        CardBelote carteForte_=_info.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_,nbPlayers_);

        /*Le pli n'est pas coupe et la couleur demandee est l'atout*/
        BidBeloteSuit bid_ = _info.getContrat();
        if(GameBeloteCommon.hand(cartesCertaines_,couleurDemandee_,next_).estVide()
                ||GameBeloteCommon.hand(cartesCertaines_,couleurDemandee_,next_).premiereCarte().strength(couleurDemandee_, bid_)<carteForte_.strength(couleurDemandee_, bid_)) {
            /*Si le joueur numero ne peut pas prendre la main sur demande d'atout*/
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                /*Si le ramasseur virtuel (de confiance, ici) domine certainement les joueurs de non confiance n'ayant pas joue*/
                if(ramasseurBatAdvDemat(_info,joueursNonConfianceNonJoue_, couleurDemandee_, carteForte_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatAdvDemat(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, couleurDemandee_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue
                ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
                if(existeJouBatAdvSurDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, carteForte_, couleurDemandee_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /*ramasseurVirtuel n'est pas un joueur de confiance pour le joueur numero*/
            /*Si le ramasseur virtuel (de non confiance, ici) domine certainement les joueurs de non confiance n'ayant pas joue*/
            if(ramasseurBatAdvDemat(_info,joueursConfianceNonJoue_, couleurDemandee_, carteForte_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatAdvDemat(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurDemandee_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            if(existeJouBatAdvSurDemat(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, carteForte_, couleurDemandee_)) {
                return PossibleTrickWinner.TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
            /*Fin joueurDeConfiance.contains(ramasseurVirtuel)*/
        }
        /*Fin !cartesCertaines.get(couleurDemandee).get(numero).estVide()||cartesCertaines.get(1).get(numero).estVide()||cartesCertaines.get(1).get(numero).premiereCarte().getValeur()<carteForte.getValeur()
            (fin test de possibilite pour le joueur numero de prendre le pli)*/
        /*Le joueur numero peut prendre la main en utilisant un atout sur demande d'atout*/
        /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatAdvNumDemat(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,next_, couleurDemandee_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*On cherche les joueurs de non confiance battant de maniere certaine les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatAdvNumDemat(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_, next_, couleurDemandee_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }
    /**Retourne vrai si et seulement si le joueur ne peut pas couper avec un atout, car il possede encore de la couleur demandee ou ne possede pas d atout*/
    static boolean nePeutCouper(Suit _couleur,byte _numero,
                                        EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
                                        EnumMap<Suit,EqList<HandBelote>> _cartesCertaines,
                                        Suit _couleurAtout) {
        return GameBeloteCommon.hand(_cartesPossibles,_couleurAtout,_numero).estVide()||!GameBeloteCommon.hand(_cartesCertaines,_couleur,_numero).estVide();
    }

    static boolean existeJouBatAdvNumDemat(
            BeloteInfoPliEnCours _info,
            Bytes _equipeABattre,
            Bytes _equipeDom,
            byte _numero,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurDeter_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(!GameBeloteCommon.hand(cartesCertaines_,_couleurDemandee,joueur_).estVide()) {
                byte maxForce_=GameBeloteCommon.hand(cartesCertaines_,_couleurDemandee,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    boolean ramasseurVirtuelEgalCertain_ = GameBeloteCommon.hand(cartesPossibles_, _couleurDemandee, joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>GameBeloteCommon.hand(cartesPossibles_,_couleurDemandee,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (maxForce_ <= GameBeloteCommon.hand(cartesPossibles_, _couleurDemandee, _numero).premiereCarte().strength(_couleurDemandee, contrat_)) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    static boolean existeJouBatAdvSurDemat(
            BeloteInfoPliEnCours _info,
            Bytes _equipeABattre,
            Bytes _equipeDom,
            CardBelote _carteForte,
            Suit _couleurDemadee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurDeter_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(!GameBeloteCommon.hand(cartesCertaines_,_couleurDemadee,joueur_).estVide()) {
                byte maxForce_=GameBeloteCommon.hand(cartesCertaines_,_couleurDemadee,joueur_).premiereCarte().strength(_couleurDemadee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    boolean ramasseurVirtuelEgalCertain_ = GameBeloteCommon.hand(cartesPossibles_, _couleurDemadee, joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>GameBeloteCommon.hand(cartesPossibles_,_couleurDemadee,joueur2_).premiereCarte().strength(_couleurDemadee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (maxForce_ <= _carteForte.strength(_couleurDemadee, contrat_)) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    static boolean existeJouBatAdvDemat(
            BeloteInfoPliEnCours _info,
            Bytes _equipeABattre,
            Bytes _equipeDom,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurDeter_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(!GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,joueur_).estVide()) {
                byte maxForce_=GameBeloteCommon.hand(cartesCertaines_,_couleurDemandee,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    boolean ramasseurVirtuelEgalCertain_ = GameBeloteCommon.hand(cartesPossibles_, _couleurDemandee, joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>GameBeloteCommon.hand(cartesPossibles_,_couleurDemandee,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
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
    static boolean ramasseurBatAdvDemat(
            BeloteInfoPliEnCours _info,
            Bytes _equipeABattre,
            Suit _couleurDemandee,CardBelote _carteForte) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        boolean ramasseurDeter_=true;
        byte maxForce_=_carteForte.strength(_couleurDemandee,contrat_);
        for(byte joueur_:_equipeABattre) {
            boolean ramasseurVirtuelEgalCertain_ = GameBeloteCommon.hand(cartesPossibles_,_couleurDemandee,joueur_).estVide();
            if(!ramasseurVirtuelEgalCertain_) {
                if (maxForce_>GameBeloteCommon.hand(cartesPossibles_,_couleurDemandee,joueur_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }

    static boolean existeJoueurBatAdvNum(
            BeloteInfoPliEnCours _info,
            Bytes _equipeABattre,
            Bytes _equipeDom,
            byte _numero,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        Suit couleurAtout_=_info.getCouleurAtout();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurDeter_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(vaCouper(_couleurDemandee, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                byte maxForce_=GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_);
                for(byte joueur2_:_equipeABattre) {
                    boolean ramasseurVirtuelEgalCertain_ = GameBeloteCommon.hand(cartesPossibles_, couleurAtout_, joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (maxForce_>GameBeloteCommon.hand(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!GameBeloteCommon.hand(cartesCertaines_,_couleurDemandee,joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (maxForce_ <= GameBeloteCommon.hand(cartesCertaines_, couleurAtout_, _numero).premiereCarte().strength(_couleurDemandee, contrat_)) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    static boolean existeJoueurAdvRamBatAdvSur(
            BeloteInfoPliEnCours _info,
            Bytes _equipeABattre,
            Bytes _equipeDom,
            Suit _couleurDemandee,
            CardBelote _carteForte) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurDeter_=false;
        for(byte joueur_:_equipeDom) {
            boolean joueurBatAdversaire_=true;
            if(vaCouper(_couleurDemandee, joueur_, cartesPossibles_, cartesCertaines_,couleurAtout_)) {
                for(byte joueur2_:_equipeABattre) {
                    boolean ramasseurVirtuelEgalCertain_ = GameBeloteCommon.hand(cartesPossibles_,couleurAtout_,joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_) >GameBeloteCommon.hand(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!GameBeloteCommon.hand(cartesCertaines_,_couleurDemandee,joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (GameBeloteCommon.hand(cartesCertaines_, couleurAtout_, joueur_).premiereCarte().strength(_couleurDemandee, contrat_) <= _carteForte.strength(_couleurDemandee, contrat_)) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    static boolean existeJoueurNonJoueBattantAdv(
            BeloteInfoPliEnCours _info,
            Bytes _equipeABattre,
            Bytes _equipeDom,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurDeter_=false;
        for(byte joueur_:_equipeDom) {
            /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
            boolean joueurBatAdversaire_ = true;
            if(vaCouper(_couleurDemandee,joueur_,cartesPossibles_,cartesCertaines_,couleurAtout_)) {
                for(byte joueur2_:_equipeABattre) {
                    boolean ramasseurVirtuelEgalCertain_ = GameBeloteCommon.hand(cartesPossibles_, couleurAtout_, joueur2_).estVide();
                    if(!ramasseurVirtuelEgalCertain_) {
                        if (GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_) >GameBeloteCommon.hand(cartesPossibles_,couleurAtout_,joueur2_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!GameBeloteCommon.hand(cartesCertaines_,_couleurDemandee,joueur2_).estVide()) {
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
    static boolean ramasseurBatAdvSur(
            BeloteInfoPliEnCours _info,
            Bytes _equipeABattre,
            Suit _couleurDemandee,
            CardBelote _carteForte) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurDeter_=true;
        byte maxForce_=_carteForte.strength(_couleurDemandee,contrat_);
        for(byte joueur_:_equipeABattre) {
            boolean ramasseurVirtuelEgalCertain_ = GameBeloteCommon.hand(cartesPossibles_, couleurAtout_, joueur_).estVide();
            if(!ramasseurVirtuelEgalCertain_) {
                if (maxForce_>GameBeloteCommon.hand(cartesPossibles_,couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee,contrat_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (!GameBeloteCommon.hand(cartesCertaines_,_couleurDemandee,joueur_).estVide()) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }
    static boolean ramasseurBatSsCprAdv(
            BeloteInfoPliEnCours _info,
            Bytes _equipeABattre,
            Suit _couleurDemandee,
            CardBelote _carteForte) {
        BidBeloteSuit contrat_ = _info.getContrat();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurDeter_=true;
        byte maxForce_=_carteForte.strength(_couleurDemandee,contrat_);
        for(byte joueur_:_equipeABattre) {
            boolean ramasseurVirtuelEgalCertain_ = !GameBeloteCommon.hand(cartesCertaines_, _couleurDemandee, joueur_).estVide();
            if(ramasseurVirtuelEgalCertain_) {
                if (maxForce_ <= GameBeloteCommon.hand(cartesPossibles_, _couleurDemandee, joueur_).premiereCarte().strength(_couleurDemandee, contrat_)) {
                    ramasseurVirtuelEgalCertain_ = false;
                }
            }
            if (defausse(_couleurDemandee, joueur_, cartesPossibles_,contrat_)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }
    static boolean pasAtoutJoueurs(Bytes _joueurs,
                                   EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
                                   Suit _couleurAtout) {
        for (byte j: _joueurs) {
            if (!pasAtout(j, _cartesPossibles, _couleurAtout)) {
                return false;
            }
        }
        return true;
    }
    static boolean pasAtout(
            byte _joueur,EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Suit _couleurAtout) {
        return GameBeloteCommon.hand(_cartesPossibles,_couleurAtout,_joueur).estVide();
    }

    static boolean defausse(Suit _couleur,byte _joueur,
                            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, BidBeloteSuit _contrat) {
        if(_contrat.getCouleurDominante()) {
            return GameBeloteCommon.hand(_cartesPossibles,_contrat.getCouleur(),_joueur).estVide()&&GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide();
        }
        return GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide();
    }
}
