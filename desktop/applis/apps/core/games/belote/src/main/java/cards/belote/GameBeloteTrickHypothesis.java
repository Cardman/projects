package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.PossibleTrickWinner;
import cards.consts.Suit;
import code.util.IdMap;
import code.util.*;

public final class GameBeloteTrickHypothesis {
    private GameBeloteTrickHypothesis() {
    }
    /**Renvoie un entier 0 si joueur de non confiance qui va faire le pli 1 si joueur de confiance va faire le pli et -1 sinon
     @param cartes_possibles l'ensemble des cartes probablement possedees par les joueurs
     @param cartes_certaines l'ensemble des cartes surement possedees par les joueurs
     @param ramasseur_virtuel le joueur, qui sans les cartes jouees par les derniers joueurs du pli est ramasseur
     @param joueurs_non_joue l'ensemble des joueurs n'ayant pas encore joue leur carte
     */
    static PossibleTrickWinner equipeQuiVaFairePliCouleurDominante(
            BeloteInfoPliEnCours _info) {
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        Ints joueursNonJoue_=_info.getJoueursNonJoue();
        int ramasseurVirtuel_=_info.getRamasseurVirtuel();
        int next_ = _info.getNextPlayer();
        Suit couleurDemandee_=_info.getProgressingTrick().couleurDemandee();
        Suit couleurAtout_=_info.getCouleurAtout();
        int nbPlayers_ = _info.getNbPlayers();
        CardBelote carteForte_=_info.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_,nbPlayers_);
        Ints joueursNonConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Ints joueursConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
        /*Le contrat n est ni sans-atout ni tout atout.*/
        BidBeloteSuit bid_ = _info.getContrat();
        int strength_ = carteForte_.strength(couleurDemandee_, bid_);
        if (couleurDemandee_ == couleurAtout_) {
            return overFollowSuit(_info);
        }
        if(carteForte_.getId().getCouleur() == couleurAtout_) {
            /*Le pli est coupe*/
            if(!GameBeloteCommon.hand(cartesCertaines_,couleurDemandee_,next_).estVide()||GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,next_).estVide()||
                    GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,next_).premiereCarte().strength(couleurDemandee_, bid_)
                            < strength_) {
                /*Le joueur numero ne peut pas prendre la main*/
                if(_info.getJoueursConfiance().containsObj(ramasseurVirtuel_)) {
                    return getPossibleOverTrump(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_,PossibleTrickWinner.TEAM,PossibleTrickWinner.FOE_TEAM);
                }
                return getPossibleOverTrump(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_,PossibleTrickWinner.FOE_TEAM,PossibleTrickWinner.TEAM);
            }
        /*Fin !cartesCertaines.get(couleurDemandee).get(numero).estVide()
        ||cartesCertaines.get(1).get(numero).estVide()
        ||cartesCertaines.get(1).get(numero).premiereCarte().getValeur()<carteForte.getValeur()
        (fin test de possibilite pour le joueur numero de prendre le pli)*/
            /*Le joueur numero peut prendre la main en surcoupant le ramasseur virtuel*/
            return getPossibleTrickWinnerOverTrump(_info, GameBeloteCommon.hand(_info.getCartesCertaines(), _info.getCouleurAtout(), next_).premiereCarte().strength(_info.getProgressingTrick().couleurDemandee(), _info.getContrat()));
        }
        boolean canTrump_ = GameBeloteCommonPlaying.peutCouper(couleurDemandee_, next_, cartesPossibles_, couleurAtout_);
        boolean ramasseurVirtuelEgalCertain_ = vaCouperListe(cartesPossibles_, cartesCertaines_, couleurDemandee_, couleurAtout_, joueursConfianceNonJoue_);
        if(ramasseurVirtuelEgalCertain_ && !canTrump_) {
            return getPossibleTrickWinnerOtherTrump(_info,PossibleTrickWinner.TEAM,joueursConfianceNonJoue_,PossibleTrickWinner.FOE_TEAM,joueursNonConfianceNonJoue_);
        }
        ramasseurVirtuelEgalCertain_ = vaCouperListe(cartesPossibles_, cartesCertaines_, couleurDemandee_, couleurAtout_, joueursNonConfianceNonJoue_);
        if(ramasseurVirtuelEgalCertain_ && !canTrump_) {
            return getPossibleTrickWinnerOtherTrump(_info,PossibleTrickWinner.FOE_TEAM,joueursNonConfianceNonJoue_,PossibleTrickWinner.TEAM,joueursConfianceNonJoue_);
        }
        if(!GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,next_).estVide()
                &&GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,next_).premiereCarte().strength(couleurDemandee_,bid_)> strength_) {
            /*Si le joueur numero peut prendre la main sans couper*/
            /*On ne sait pas si un joueur n'ayant pas joue va couper le pli ou non*/
            return PossibleTrickWinner.UNKNOWN;
        }
        /*Fin si le joueur numero peut prendre la main sans couper*/
        if(canTrump_) {
            /*Si le joueur numero peut prendre la main en coupant*/
            return getPossibleTrickWinnerOverTrump(_info, max(_info,_info.getProgressingTrick().couleurDemandee(),GameBeloteCommon.hand(_info.getCartesCertaines(), _info.getCouleurAtout(), next_)));
        }
        return getPossibleTrickWinnerNoCurrentTrump(_info,carteForte_);
        /*Le pli n'est pas coupe et la couleur demandee est l'atout*/
    }

    private static boolean vaCouperListe(IdMap<Suit, CustList<HandBelote>> _cartesPossibles, IdMap<Suit, CustList<HandBelote>> _cartesCertaines, Suit _couleurDemandee, Suit _couleurAtout, Ints _players) {
        boolean ramasseurVirtuelEgalCertain_=false;
        /*La couleur demandee n'est pas de l'atout et le pli n'est pas coupe*/
        for(int joueur_: _players) {
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles, _cartesCertaines, _couleurAtout)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
        }
        return ramasseurVirtuelEgalCertain_;
    }

    static PossibleTrickWinner getPossibleTrickWinnerOverTrump(BeloteInfoPliEnCours _info, int _str) {
        Ints joueursNonJoue_=_info.getJoueursNonJoue();
        int next_ = _info.getNextPlayer();
        Ints joueursNonConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Ints joueursConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
        Suit couleurDemandee_=_info.getProgressingTrick().couleurDemandee();
        Ints other_ = new Ints(joueursNonJoue_);
        other_.removeObj(next_);
        if (ramasseurBatAdvSur(_info,other_,
                couleurDemandee_, _str)) {
            return PossibleTrickWinner.UNKNOWN;
        }
        if(existeJoueurBatAdvNum(_info,joueursNonConfianceNonJoue_, joueursConfianceNonJoue_, couleurDemandee_, _str)) {
            return PossibleTrickWinner.TEAM;
        }
        if(existeJoueurBatAdvNum(_info,joueursConfianceNonJoue_, joueursNonConfianceNonJoue_, couleurDemandee_, _str)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    static PossibleTrickWinner getPossibleOverTrump(BeloteInfoPliEnCours _info,Ints _curTeam,Ints _otherTeam,PossibleTrickWinner _tmpLeader,PossibleTrickWinner _afterLeader) {
        int ramasseurVirtuel_=_info.getRamasseurVirtuel();
        Suit couleurDemandee_=_info.getProgressingTrick().couleurDemandee();
        int nbPlayers_ = _info.getNbPlayers();
        CardBelote carteForte_=_info.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_,nbPlayers_);
        if(ramasseurBatAdvSur(_info,_otherTeam, couleurDemandee_, carteForte_.strength(couleurDemandee_, _info.getContrat()))) {
            return _tmpLeader;
        }
        if(existeJoueurNonJoueBattantAdv(_info,_otherTeam, _curTeam, couleurDemandee_)) {
            return _tmpLeader;
        }
        if(existeJoueurAdvRamBatAdvSur(_info,_curTeam, _otherTeam, couleurDemandee_, carteForte_)) {
            return _afterLeader;
        }
        return PossibleTrickWinner.UNKNOWN;
    }
    static PossibleTrickWinner equipeQuiVaFairePliSansAtout(
            BeloteInfoPliEnCours _info) {

        IdMap<Suit,CustList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        int ramasseurVirtuel_=_info.getRamasseurVirtuel();
        int next_ = _info.getNextPlayer();
        Suit couleurDemandee_=_info.getProgressingTrick().couleurDemandee();
        int nbPlayers_ = _info.getNbPlayers();
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
        return getPossibleTrickWinnerNoCurrentTrump(_info,carteForte_);
    }
    static PossibleTrickWinner equipeQuiVaFairePliToutAtout(
            BeloteInfoPliEnCours _info) {
        return overFollowSuit(_info);
    }

    private static PossibleTrickWinner overFollowSuit(BeloteInfoPliEnCours _info) {
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        Ints joueursNonJoue_=_info.getJoueursNonJoue();
        int ramasseurVirtuel_=_info.getRamasseurVirtuel();
        int nbPlayers_ = _info.getNbPlayers();
        CardBelote carteForte_=_info.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_,nbPlayers_);
        int next_ = _info.getNextPlayer();
        Suit couleurDemandee_=_info.getProgressingTrick().couleurDemandee();
        Ints joueursNonConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Ints joueursConfianceNonJoue_=GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
        BidBeloteSuit bid_ = _info.getContrat();
        int strength_ = carteForte_.strength(couleurDemandee_, bid_);
        HandBelote sure_ = GameBeloteCommon.hand(cartesCertaines_, couleurDemandee_, next_);
        if(sure_.estVide()
                || sure_.premiereCarte().strength(couleurDemandee_, bid_)< strength_) {
            /*Si le joueur numero ne peut pas prendre la main sur demande d'atout*/
            if(_info.getJoueursConfiance().containsObj(ramasseurVirtuel_)) {
                return getPossibleTrickWinnerTrumpDemand(_info,PossibleTrickWinner.TEAM,joueursConfianceNonJoue_,PossibleTrickWinner.FOE_TEAM,joueursNonConfianceNonJoue_);
            }
            /*ramasseurVirtuel n'est pas un joueur de confiance pour le joueur numero*/
            return getPossibleTrickWinnerTrumpDemand(_info,PossibleTrickWinner.FOE_TEAM,joueursNonConfianceNonJoue_,PossibleTrickWinner.TEAM,joueursConfianceNonJoue_);
            /*Fin joueurDeConfiance.contains(ramasseurVirtuel)*/
        }
        /*Fin !cartesCertaines.get(couleurDemandee).get(numero).estVide()
        ||cartesCertaines.get(1).get(numero).estVide()
        ||cartesCertaines.get(1).get(numero).premiereCarte().getValeur()<carteForte.getValeur()
            (fin test de possibilite pour le joueur numero de prendre le pli)*/
        /*Le joueur numero peut prendre la main en utilisant un atout sur demande d'atout*/
        CardBelote cardPl_ = sure_
                .premiereCarte();
        Ints other_ = new Ints(joueursNonJoue_);
        other_.removeObj(next_);
        if (ramasseurBatAdvDemat(_info,other_,
                couleurDemandee_,cardPl_)) {
            return PossibleTrickWinner.UNKNOWN;
        }
        /*On cherche les joueurs de confiance battant de maniere certaine les joueurs de non
        confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        int str_ = max(_info,couleurDemandee_,next_);
        if(existeJouBatAdvNumDemat(_info,joueursNonConfianceNonJoue_,joueursConfianceNonJoue_, couleurDemandee_, str_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*On cherche les joueurs de non confiance battant de maniere certaine
        les joueurs de confiance n'ayant pas joue ou possedant des cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs de non confiance n'ayant pas joue*/
        if(existeJouBatAdvNumDemat(_info,joueursConfianceNonJoue_,joueursNonConfianceNonJoue_, couleurDemandee_, str_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    static PossibleTrickWinner getPossibleTrickWinnerTrumpDemand(BeloteInfoPliEnCours _info,
                                                                 PossibleTrickWinner _current, Ints _team,
                                                                 PossibleTrickWinner _after, Ints _other) {
        Suit couleurDemandee_=_info.getProgressingTrick().couleurDemandee();
        int ramasseurVirtuel_=_info.getRamasseurVirtuel();
        int nbPlayers_ = _info.getNbPlayers();
        CardBelote carteForte_=_info.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_,nbPlayers_);
        if(ramasseurBatAdvDemat(_info,_other, couleurDemandee_, carteForte_)) {
            return _current;
        }
        if(existeJouBatAdvDemat(_info,_other, _team, couleurDemandee_)) {
            return _current;
        }
        if(existeJouBatAdvSurDemat(_info,_team, _other, carteForte_, couleurDemandee_)) {
            return _after;
        }
        return PossibleTrickWinner.UNKNOWN;
    }
    static PossibleTrickWinner getPossibleTrickWinnerNoCurrentTrump(BeloteInfoPliEnCours _info, CardBelote _card) {
        int ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        Ints joueursNonJoue_ = _info.getJoueursNonJoue();
        int player_ = _info.getNextPlayer();
        Ints joueursNonConfianceNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursNonConfiance());
        Ints joueursConfianceNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(joueursNonJoue_, _info.getJoueursConfiance());
        joueursConfianceNonJoue_.removeObj(player_);
        if (_info.getJoueursConfiance().containsObj(ramasseurVirtuel_)) {
            return getPossibleTrickWinnerCurrentNoTrump(_info,_card,PossibleTrickWinner.TEAM,joueursConfianceNonJoue_,PossibleTrickWinner.FOE_TEAM,joueursNonConfianceNonJoue_);
        }
        /* Fin joueursDeConfiance.contains(ramasseurVirtuel) */
        /* Maintenant le ramasseur virtuel n'est pas un joueur de confiance */
        return getPossibleTrickWinnerCurrentNoTrump(_info,_card,PossibleTrickWinner.FOE_TEAM,joueursNonConfianceNonJoue_,PossibleTrickWinner.TEAM,joueursConfianceNonJoue_);
    }

    static PossibleTrickWinner getPossibleTrickWinnerCurrentNoTrump(BeloteInfoPliEnCours _info,CardBelote _card,
                                                                    PossibleTrickWinner _current, Ints _team,
                                                                    PossibleTrickWinner _after, Ints _other) {
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        TrickBelote t_ = _info.getProgressingTrick();
        Suit couleurDemandee_ = t_.couleurDemandee();
        BidBeloteSuit bid_ = _info.getContrat();
        Suit couleurAtout_ = _info.getCouleurAtout();
        int str_ = _card.strength(couleurDemandee_,bid_);
        if (ramasseurBatSsCprAdv(_info,_other,
                couleurDemandee_, str_)) {
            return _current;
        }
        boolean ramasseurVirtuelEgalCertain_ = nePeutCouperListe(_other, cartesPossibles_, cartesCertaines_, couleurDemandee_, couleurAtout_);
        if (ramasseurVirtuelEgalCertain_ && existPlayerNoTrump(_info, _other, _team, couleurDemandee_, _card, cartesCertaines_)) {
            return _current;
        }
        ramasseurVirtuelEgalCertain_ = nePeutCouperListe(_team, cartesPossibles_, cartesCertaines_, couleurDemandee_, couleurAtout_);
        if (ramasseurVirtuelEgalCertain_ && existPlayerNoTrump(_info, _team, _other, couleurDemandee_, _card, cartesCertaines_)) {
            return _after;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    private static boolean nePeutCouperListe(Ints _players, IdMap<Suit, CustList<HandBelote>> _cartesPossibles, IdMap<Suit, CustList<HandBelote>> _cartesCertaines, Suit _couleurDemandee, Suit _couleurAtout) {
        boolean ramasseurVirtuelEgalCertain_ = true;
        for (int joueur_ : _players) {
            if (!nePeutCouper(_couleurDemandee, joueur_, _cartesPossibles, _cartesCertaines, _couleurAtout)) {
                ramasseurVirtuelEgalCertain_ = false;
            }
        }
        return ramasseurVirtuelEgalCertain_;
    }

    static PossibleTrickWinner getPossibleTrickWinnerOtherTrump(BeloteInfoPliEnCours _info,
                                                                PossibleTrickWinner _current,
                                                                Ints _currentNotPl,
                                                                PossibleTrickWinner _other,
                                                                Ints _otherNotPl) {
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        TrickBelote t_ = _info.getProgressingTrick();
        Suit couleurDemandee_ = t_.couleurDemandee();
        Suit couleurAtout_ = _info.getCouleurAtout();
        boolean ramasseurVirtuelEgalCertain_ = nePeutCouperListe(_otherNotPl,cartesPossibles_,cartesCertaines_,couleurDemandee_,couleurAtout_);
        if (ramasseurVirtuelEgalCertain_) {
                /*
                Si aucun joueur de non
                confiance n ayant pas
                joue ne va couper le pli
                */
            return _current;
        }
        if (existeJoueurNonJoueBattantAdv(_info,
                _otherNotPl,
                _currentNotPl, couleurDemandee_)) {
            return _current;
        }
        if (existeJoueurNonJoueBattantAdv(_info,
                _currentNotPl,
                _otherNotPl, couleurDemandee_)) {
            return _other;
        }
        return PossibleTrickWinner.UNKNOWN;
    }
    static boolean existPlayerNoTrump(BeloteInfoPliEnCours _info, Ints _equipeABattre,Ints _equipeDom, Suit _couleurDemandee,
                                      CardBelote _carteForte,
                                      IdMap<Suit,CustList<HandBelote>> _cartesCertaines) {
        boolean det_ = false;
        BidBeloteSuit bid_ = _info.getContrat();
        for (int joueur_ : _equipeDom) {
            HandBelote sure_ = _cartesCertaines.getVal(_couleurDemandee).get(joueur_);
            if (!sure_.estVide()) {
                CardBelote cardPl_ = sure_.premiereCarte();
                int str_ = cardPl_.strength(_couleurDemandee,bid_);
                if (str_ <= _carteForte.strength(_couleurDemandee,bid_)) {
                    continue;
                }
                if (ramasseurBatSsCprAdv(_info,_equipeABattre,_couleurDemandee,str_)) {
                    det_ = true;
                }
            }
        }
        return det_;
    }
    static boolean existeJouBatAdvNumDemat(
            BeloteInfoPliEnCours _info,
            Ints _equipeABattre,
            Ints _equipeDom,
            Suit _couleurDemandee, int _str) {
        BidBeloteSuit contrat_ = _info.getContrat();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        return beatSureListTrumpDemand(_equipeABattre, _equipeDom, _couleurDemandee, contrat_, cartesPossibles_, cartesCertaines_, _str);
    }

    static boolean existeJouBatAdvSurDemat(
            BeloteInfoPliEnCours _info,
            Ints _equipeABattre,
            Ints _equipeDom,
            CardBelote _carteForte,
            Suit _couleurDemadee) {
        return existeJouBatAdvNumDemat(_info, _equipeABattre, _equipeDom, _couleurDemadee, _carteForte.strength(_couleurDemadee, _info.getContrat()));
    }

    static boolean existeJouBatAdvDemat(
            BeloteInfoPliEnCours _info,
            Ints _equipeABattre,
            Ints _equipeDom,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurDeter_=false;
        for(int joueur_:_equipeDom) {
            HandBelote sure_ = GameBeloteCommon.hand(cartesCertaines_, _couleurDemandee, joueur_);
            if(!sure_.estVide()) {
                int maxForce_= sure_.premiereCarte().strength(_couleurDemandee,contrat_);
                boolean joueurBatAdversaire_ = beatFoeTrumpDemand(_equipeABattre, _couleurDemandee, contrat_, cartesPossibles_, maxForce_);
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    static boolean ramasseurBatAdvDemat(
            BeloteInfoPliEnCours _info,
            Ints _equipeABattre,
            Suit _couleurDemandee,CardBelote _carteForte) {
        BidBeloteSuit contrat_ = _info.getContrat();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        int maxForce_=_carteForte.strength(_couleurDemandee,contrat_);
        return beatFoeTrumpDemand(_equipeABattre,_couleurDemandee,contrat_,cartesPossibles_,maxForce_);
    }

    static boolean existeJoueurBatAdvNum(
            BeloteInfoPliEnCours _info,
            Ints _equipeABattre,
            Ints _equipeDom,
            Suit _couleurDemandee, int _str) {
        BidBeloteSuit contrat_ = _info.getContrat();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        return beatSureListTrumpNormalSuit(_equipeABattre, _equipeDom, _couleurDemandee, contrat_, cartesPossibles_, cartesCertaines_, _str);
    }


    static boolean existeJoueurAdvRamBatAdvSur(
            BeloteInfoPliEnCours _info,
            Ints _equipeABattre,
            Ints _equipeDom,
            Suit _couleurDemandee,
            CardBelote _carteForte) {
        return existeJoueurBatAdvNum(_info, _equipeABattre, _equipeDom, _couleurDemandee, _carteForte.strength(_couleurDemandee, _info.getContrat()));
    }

    static boolean existeJoueurNonJoueBattantAdv(
            BeloteInfoPliEnCours _info,
            Ints _equipeABattre,
            Ints _equipeDom,
            Suit _couleurDemandee) {
        BidBeloteSuit contrat_ = _info.getContrat();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        Suit couleurAtout_=_info.getCouleurAtout();
        boolean ramasseurDeter_=false;
        for(int joueur_:_equipeDom) {
            /*On cherche les joueurs de confiance battant
            de maniere certaine les joueurs de non confiance
            n'ayant pas joue ou possedant des cartes que les joueurs
            ayant joue n'ont pas ainsi que les joueurs de non
            confiance n'ayant pas joue*/
            if(vaCouper(_couleurDemandee,joueur_,cartesPossibles_,cartesCertaines_,couleurAtout_)) {
                int maxForce_ = GameBeloteCommon.hand(cartesCertaines_, couleurAtout_, joueur_).premiereCarte().strength(_couleurDemandee, contrat_);
                boolean joueurBatAdversaire_ = beatByTrumpNormalSuitStrength(_equipeABattre, _couleurDemandee, contrat_, cartesPossibles_, cartesCertaines_, maxForce_);
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }
    static boolean ramasseurBatAdvSur(
            BeloteInfoPliEnCours _info,
            Ints _equipeABattre,
            Suit _couleurDemandee, int _str) {
        BidBeloteSuit contrat_ = _info.getContrat();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        return beatByTrumpNormalSuitStrength(_equipeABattre,_couleurDemandee,contrat_, cartesPossibles_,cartesCertaines_, _str);
    }
    static boolean ramasseurBatSsCprAdv(
            BeloteInfoPliEnCours _info,
            Ints _equipeABattre,
            Suit _couleurDemandee,
            int _strength) {
        BidBeloteSuit contrat_ = _info.getContrat();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_ = _info.getCartesCertaines();
        boolean ramasseurDeter_=true;
        Suit couleurAtout_ = _info.getCouleurAtout();
        for(int joueur_:_equipeABattre) {
            boolean ramasseurVirtuelEgalCertain_ = !GameBeloteCommon.hand(cartesCertaines_, _couleurDemandee, joueur_).estVide();
            HandBelote possible_ = GameBeloteCommon.hand(cartesPossibles_, _couleurDemandee, joueur_);
            int max_ = max(_info,_couleurDemandee, joueur_);
            if (ramasseurVirtuelEgalCertain_ && _strength <= max_) {
                ramasseurVirtuelEgalCertain_ = false;
            }
            if (defausse(_couleurDemandee, joueur_, cartesPossibles_, contrat_) || !possible_.estVide()&&nePeutCouper(_couleurDemandee, joueur_, cartesPossibles_, cartesCertaines_, couleurAtout_) && _strength > max_) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }

    private static int max(BeloteInfoPliEnCours _info, Suit _couleurDemandee, int _joueur) {
        HandBelote possible_ = GameBeloteCommon.hand(_info.getCartesPossibles(), _couleurDemandee, _joueur);
        return max(_info, _couleurDemandee, possible_);
    }

    private static int max(BeloteInfoPliEnCours _info, Suit _couleurDemandee, HandBelote _h) {
        int max_;
        if (_h.estVide()) {
            max_ = 0;
        } else {
            max_ = _h.premiereCarte().strength(_couleurDemandee, _info.getContrat());
        }
        return max_;
    }

    static boolean beatSureListTrumpDemand(Ints _equipeABattre, Ints _equipeDom, Suit _couleurDemandee, BidBeloteSuit _bid,
                                           IdMap<Suit, CustList<HandBelote>> _cartesPossibles, IdMap<Suit, CustList<HandBelote>> _cartesCertaines, int _strength) {
        boolean ramasseurDeter_=false;
        for(int joueur_:_equipeDom) {
            HandBelote sure_ = GameBeloteCommon.hand(_cartesCertaines, _couleurDemandee, joueur_);
            if(!sure_.estVide()) {
                int maxForce_= sure_.premiereCarte().strength(_couleurDemandee,_bid);
                boolean joueurBatAdversaire_ = beatFoeTrumpDemand(_equipeABattre, _couleurDemandee, _bid, _cartesPossibles, maxForce_);
                if (maxForce_ <= _strength) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    static boolean beatFoeTrumpDemand(Ints _equipeABattre, Suit _couleurDemandee, BidBeloteSuit _bid,
                                              IdMap<Suit, CustList<HandBelote>> _cartesPossibles, int _strength) {
        boolean joueurBatAdversaire_=true;
        for(int joueur_:_equipeABattre) {
            HandBelote possible_ = GameBeloteCommon.hand(_cartesPossibles, _couleurDemandee, joueur_);
            boolean ramasseurVirtuelEgalCertain_ = possible_.estVide();
            if (!ramasseurVirtuelEgalCertain_ && _strength > possible_.premiereCarte().strength(_couleurDemandee, _bid)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                joueurBatAdversaire_ = false;
            }
        }
        return joueurBatAdversaire_;
    }

    static boolean beatSureListTrumpNormalSuit(Ints _equipeABattre, Ints _equipeDom, Suit _couleurDemandee, BidBeloteSuit _contrat,
                                               IdMap<Suit, CustList<HandBelote>> _cartesPossibles, IdMap<Suit, CustList<HandBelote>> _cartesCertaines, int _strength) {
        Suit couleurAtout_ = _contrat.getSuit();
        boolean ramasseurDeter_=false;
        for(int joueur_:_equipeDom) {
            if(vaCouper(_couleurDemandee, joueur_, _cartesPossibles, _cartesCertaines, couleurAtout_)) {
                int maxForce_ = GameBeloteCommon.hand(_cartesCertaines, couleurAtout_,joueur_).premiereCarte().strength(_couleurDemandee, _contrat);
                boolean joueurBatAdversaire_ = beatByTrumpNormalSuitStrength(_equipeABattre, _couleurDemandee, _contrat, _cartesPossibles, _cartesCertaines, maxForce_);
                if (maxForce_ <= _strength) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    static boolean beatByTrumpNormalSuitStrength(Ints _equipeABattre, Suit _couleurDemandee, BidBeloteSuit _contrat,
                                                 IdMap<Suit, CustList<HandBelote>> _cartesPossibles, IdMap<Suit, CustList<HandBelote>> _cartesCertaines, int _maxForce) {
        Suit couleurAtout_ = _contrat.getSuit();
        boolean joueurBatAdversaire_=true;
        for(int joueur_:_equipeABattre) {
            HandBelote possible_ = GameBeloteCommon.hand(_cartesPossibles, couleurAtout_, joueur_);
            boolean ramasseurVirtuelEgalCertain_ = possible_.estVide();
            if (!ramasseurVirtuelEgalCertain_ && _maxForce > possible_.premiereCarte().strength(_couleurDemandee, _contrat)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!GameBeloteCommon.hand(_cartesCertaines,_couleurDemandee,joueur_).estVide()) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                joueurBatAdversaire_ = false;
            }
        }
        return joueurBatAdversaire_;
    }
    static boolean pasAtoutJoueurs(Ints _joueurs,
                                   IdMap<Suit,CustList<HandBelote>> _cartesPossibles,
                                   Suit _couleurAtout) {
        for (int j: _joueurs) {
            if (!pasAtout(j, _cartesPossibles, _couleurAtout)) {
                return false;
            }
        }
        return true;
    }
    static boolean pasAtout(
            int _joueur,IdMap<Suit,CustList<HandBelote>> _cartesPossibles,
            Suit _couleurAtout) {
        return GameBeloteCommon.hand(_cartesPossibles,_couleurAtout,_joueur).estVide();
    }

    static boolean defausse(Suit _couleur,int _joueur,
                            IdMap<Suit,CustList<HandBelote>> _cartesPossibles, BidBeloteSuit _contrat) {
        if(_contrat.getCouleurDominante()) {
            return GameBeloteCommon.hand(_cartesPossibles, _contrat.getSuit(),_joueur).estVide()&&GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide();
        }
        return GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide();
    }
    /**Retourne vrai si et seulement si le joueur ne peut pas couper avec un atout, car il possede encore de la couleur demandee ou ne possede pas d atout*/
    static boolean nePeutCouper(Suit _couleur,int _numero,
                                IdMap<Suit,CustList<HandBelote>> _cartesPossibles,
                                IdMap<Suit,CustList<HandBelote>> _cartesCertaines,
                                Suit _couleurAtout) {
        if (_couleurAtout == Suit.UNDEFINED) {
            return true;
        }
        return GameBeloteCommon.hand(_cartesPossibles,_couleurAtout,_numero).estVide()||!GameBeloteCommon.hand(_cartesCertaines,_couleur,_numero).estVide();
    }
    static boolean vaCouper(Suit _couleur,int _joueur,
                            IdMap<Suit,CustList<HandBelote>> _cartesPossibles,
                            IdMap<Suit,CustList<HandBelote>> _cartesCertaines,
                            Suit _couleurAtout) {
        return GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide()&&!GameBeloteCommon.hand(_cartesCertaines,_couleurAtout,_joueur).estVide();
    }

}
