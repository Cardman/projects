package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.PossibleTrickWinner;
import cards.consts.Role;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class GameBeloteProgTrick {
    private final GameBeloteTeamsRelation teamsRelation;
    private final GameBeloteTrickInfo info;
    private final TrickBelote progressingTrick;

    private final HandBelote currentHand;
    private final GameBeloteCommonPlaying common;
    private final Role currentStatus;
    private final HandBelote playableCards;
    private final BidBeloteSuit bid;
    private final byte taker;

    public GameBeloteProgTrick(GameBeloteTrickInfo _done, GameBeloteTeamsRelation _teamsRelation, HandBelote _currentHand) {
        teamsRelation = _teamsRelation;
        currentHand = _currentHand;
        info = _done;
        progressingTrick = _done.getProgressingTrick();
        bid = _done.getBid();
        taker = _teamsRelation.getTaker();
        common = new GameBeloteCommonPlaying(_done,_teamsRelation);
        byte nbPlayers_ = _teamsRelation.getNombreDeJoueurs();
        TrickBelote trBelote_ = _done.getProgressingTrick();
        byte nextPlayer_ = trBelote_.getNextPlayer(nbPlayers_);
        playableCards = common.playableCards(currentHand.couleurs(bid));
        currentStatus = _teamsRelation.statutDe(nextPlayer_);
    }

    CardBelote enCours() {
        if(playableCards.total()==1) {
            return playableCards.premiereCarte();
        }
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        BeloteInfoPliEnCours info_ = initInformations();
        if(bid.getCouleurDominante()) {
            return domSuit(info_);
        }
        if(bid.ordreCouleur()) {
            if(!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                return fournirCouleurOrdinaireSansAtout(info_);
            }
            return defausseCouleurOrdinaireSansAtout(info_);
        }
        //jeu tout atout
        if(!GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).estVide()) {
            return fournirAtoutToutAtout(info_);
        }
        return defausseAtoutToutAtout(info_);
    }

    private CardBelote domSuit(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        Suit couleurAtout_= bid.getSuit();
        if (couleurDemandee_ != couleurAtout_) {
            if(!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                return fournirCouleurOrdinaireCouleurDominante(_info);
            }
            if(!repartitionJouables_.getVal(couleurAtout_).estVide()) {
                return coupeCouleurDominante(_info);
            }
            return defausseCouleurOrdinaireCouleurDominante(_info);
        }
        //entame atout
        if(!repartitionJouables_.getVal(couleurAtout_).estVide()) {
            return fournirAtoutCouleurDominante(_info);
        }
        return defausseAtoutCouleurDominante(_info);
    }

    CardBelote fournirCouleurOrdinaireCouleurDominante(BeloteInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(_info);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return followSuitFoe();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return followNormalSuitPartner(_info);
        }
        //Si le joueur ne coupe pas et ne se defause pas sur la couleur demandee
        CardBelote card_ = followNormalSuit(_info);
        if (card_ != CardBelote.WHITE) {
            return card_;
        }
        /*Maintenant le joueur peut prendre la GameBeloteCommon.main avec une figure a la couleur demandee*/
        if(currentStatus == Role.TAKER) {
            return followDominantAsTaker(_info);
        }
        /*Appele*/
        if(currentStatus == Role.CALLED_PLAYER) {
            return followDominantAsCalledPlayer(_info);
        }
        return followDominantAsDefender(_info);
    }

    CardBelote followDominantAsDefender(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        CustList<HandBelote> suitesJouables_=repartition_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        CustList<HandBelote> cartesRelMaitres_ = cartesRelativementMaitre(suitesJouables_, cartesPossibles_,
                joueursNonJoue_, couleurDemandee_, couleurDemandee_, _info.getCartesCertaines(), progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs()));
        /*Defenseur*/
        if(!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        Suit couleurAtout_= _info.getCouleurAtout();
        Bytes adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(_info.getJoueursNonConfiance(),joueursNonJoue_);
        boolean pasAtoutAdvNonJoue_ = GameBeloteTrickHypothesis.pasAtoutJoueurs(adversaireNonJoue_, cartesPossibles_, couleurAtout_);
        //si aucun adv non joue n'a de l'atout alors les points peuvent etre joues
        if(pasAtoutAdvNonJoue_) {
            return sauveQuiPeutFigure(cartesPossibles_,suitesJouables_, cartesRelMaitres_, adversaireNonJoue_, couleurDemandee_);
        }
        return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
    }

    CardBelote followDominantAsCalledPlayer(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        CustList<TrickBelote> plisFaits_= _info.getPlisFaits();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        CustList<HandBelote> suitesJouables_=repartitionJouables_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        CustList<HandBelote> cartesRelMaitres_ = cartesRelativementMaitre(suitesJouables_, cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_, cartesCertaines_, carteForte_);
        if (!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if(tours_.isEmpty()) {
            return notStarted(_info, suitesJouables_);
        }
        return playAtNextRound(_info);
    }

    private CardBelote notStarted(BeloteInfoPliEnCours _info, CustList<HandBelote> _suitesJouables) {
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        if (joueursNonJoue_.containsObj(taker) && GameBeloteBeginTrick.playedLeading(bid, taker, couleurDemandee_, _info.getRepartitionCartesJouees(), cartesCertaines_, bid.getOrdre())) {
            return _suitesJouables.first().premiereCarte();
        }
        return playWhenLastDiscard(_info);
    }

    CardBelote followDominantAsTaker(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CustList<TrickBelote> plisFaits_= _info.getPlisFaits();
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        if(tours_.isEmpty()) {
            return playWhenLastDiscard(_info);
        }
        return playAtNextRound(_info);

    }

    CardBelote playAtNextRound(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        Suit couleurAtout_= _info.getCouleurAtout();
        IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
        CustList<TrickBelote> plisFaits_= _info.getPlisFaits();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        Bytes joueursSusceptiblesDeCouper_=new Bytes();
        CustList<HandBelote> suitesJouables_=repartition_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        CustList<HandBelote> cartesRelMaitres_ = cartesRelativementMaitre(suitesJouables_,
                cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_,
                cartesCertaines_, carteForte_);
        TrickBelote dernierPli_;
        Bytes dernieresDefausses_;
        dernierPli_ = tours_.last();
        dernieresDefausses_ = dernierPli_.joueursDefausses(couleurAtout_);
        for (byte joueur_ : joueursNonJoue_) {
            if (GameBeloteCommonPlaying.peutCouper(couleurDemandee_, joueur_, cartesPossibles_, couleurAtout_)) {
                joueursSusceptiblesDeCouper_.add(joueur_);
            }
        }
        if (!joueursSusceptiblesDeCouper_.isEmpty()) {
            return playWhenPossibleTrump(_info);
        }
        /*Si la coupe semble improbable*/
        if (!dernieresDefausses_.isEmpty()) {
            return playWhenLastDiscard(_info);
        }
        /*Le pli d'avant n'est pas defausse ou c'est au moins le troisieme tour*/
        if (!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        return GameBeloteCommon.hand(repartition_, couleurDemandee_).derniereCarte();
    }

    CardBelote playWhenPossibleTrump(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        byte next_ = info.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        CustList<HandBelote> suitesJouables_=repartitionJouables_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        boolean maitreJeu_ = _info.isMaitreJeu();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        Bytes joueursSusceptiblesDeCouper_=GameBeloteCommonPlaying.joueursSusceptiblesCoupe(cartesPossibles_,
                couleurDemandee_, bid.getSuit(),adversaire_);
        byte maxTwo_;
        for (byte joueur_ : adversaire_) {
            if (joueursSusceptiblesDeCouper_.containsObj(joueur_)) {
                return GameBeloteCommon.hand(repartitionJouables_, couleurDemandee_).derniereCarte();
            }
        }
        if (maitreJeu_) {
            maxTwo_ = 0;
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ != next_) {
                    maxTwo_ = (byte) NumberUtil.max(GameBeloteCommon.hand(cartesPossibles_, couleurDemandee_, joueur_).total(), maxTwo_);
                }
            }
            if (suitesJouables_.get(0).total() > maxTwo_) {
                return GameBeloteCommon.hand(repartitionJouables_, couleurDemandee_).premiereCarte();
            }
            return GameBeloteCommon.hand(repartitionJouables_, couleurDemandee_).derniereCarte();
        }
        return jeuFigureHauteDePlusFaibleSuite(suitesJouables_, bid);
    }

    CardBelote playWhenLastDiscard(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
        CustList<HandBelote> suitesJouables_=repartition_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        CustList<HandBelote> cartesRelMaitres_ = cartesRelativementMaitre(suitesJouables_,
                cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_, cartesCertaines_, carteForte_);
        /*Le joueur n'a aucune cartes maitresses*/
        if(!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
    }

    CardBelote followNormalSuit(BeloteInfoPliEnCours _info) {
        CardBelote strongCard_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        //Si le joueur ne coupe pas et ne se defause pas sur la couleur demandee
        CardBelote bestCard_ = GameBeloteCommon.hand(playableCards.couleurs(bid), progressingTrick.couleurDemandee()).premiereCarte();
        if(bestCard_.strength(progressingTrick.couleurDemandee(),bid)
                <strongCard_.strength(progressingTrick.couleurDemandee(),bid)) {
            CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(progressingTrick.couleurDemandee(), _info.getPlisFaits());
            /*Si le joueur ne peut pas prendre la GameBeloteCommon.main*/
            if(tours_.isEmpty()) {
                /*Si cette couleur est entamee pour la premiere fois*/
                return followPossiblePtsFirstRound(_info);
            }
            return followSuitFoe();
        }
        /*Maintenant on sait que le joueur peut prendre la main*/
        if(bestCard_.points(bid)<1) {
            //si aucun adv non joue ne possede de figure
            return bestCard_;
        }
        return CardBelote.WHITE;
    }

    boolean leadingPartner(Suit _couleurDemandee, CardBelote _carteForte, IdMap<Suit, CustList<HandBelote>> _cartesPossibles, Bytes _adversaireNonJoue) {
        boolean partenaireMaitre_ = true;
        for(byte j: _adversaireNonJoue) {
            HandBelote couleurPossible_ = GameBeloteCommon.hand(_cartesPossibles, _couleurDemandee,j);
            if (couleurPossible_.estVide() || _carteForte.strength(_couleurDemandee, bid) <= couleurPossible_.premiereCarte().strength(_couleurDemandee, bid)) {
                partenaireMaitre_ = false;
                break;
            }
        }
        return partenaireMaitre_;
    }

    CardBelote followPossiblePtsFirstRound(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        Bytes adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        boolean partenaireMaitre_ = leadingPartner(couleurDemandee_, carteForte_, cartesPossibles_, adversaireNonJoue_);
        if(partenaireMaitre_) {
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
    }

    CardBelote followNormalSuitPartner(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CustList<HandBelote> suitesJouables_=repartitionJouables_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        for(byte joueur_:adversaire_) {
            if (!GameBeloteCommon.hand(cartesPossibles_, couleurDemandee_, joueur_).estVide() && GameBeloteCommon.hand(cartesPossibles_, couleurDemandee_, joueur_).premiereCarte().strength(couleurDemandee_, bid)
                    > GameBeloteCommon.hand(repartitionJouables_, couleurDemandee_).premiereCarte().strength(couleurDemandee_, bid)) {
                return GameBeloteCommon.hand(repartitionJouables_, couleurDemandee_).premiereCarte();
            }
        }
        if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).nombreCartesPoints(bid)>1) {
            return cartePlusPetitePoints(suitesJouables_,bid);
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
    }

    private boolean canLeadTrick(boolean _maitreJeu, CustList<HandBelote> _cartesRelMaitres) {
        return !_cartesRelMaitres.isEmpty() && _maitreJeu;
    }

    CardBelote coupeCouleurDominante(BeloteInfoPliEnCours _info) {
        Suit couleurAtout_= bid.getSuit();
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        if(GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).total()==playableCards.total()) {
            return coupeObligatoireCouleurDominante(_info);
        }
        return coupeFacultativeCouleurDominante(_info);
    }
    CardBelote coupeObligatoireCouleurDominante(BeloteInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(_info);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur est oblige de couper la couleur demandee*/
            Suit couleurAtout_=_info.getCouleurAtout();
            IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
            return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            Suit couleurAtout_=_info.getCouleurAtout();
            IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
            CustList<HandBelote> suitesJouables_=repartitionJouables_.getVal(couleurAtout_).eclater(_info.getRepartitionCartesJouees(), bid);
            if(suitesJouables_.size()==1) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte();
            }
            return suitesJouables_.last().premiereCarte();
        }
        /*Coupe obligatoire (ou surcoupe ou sous-coupe)*/
        return coupe(_info);
    }
    CardBelote coupeFacultativeCouleurDominante(BeloteInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(_info);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur se defausse*/
            IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
            IdMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
            return discardFoe(repartitionCartesJouees_, repartition_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
            IdMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
            IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
            IdList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
            Suit couleurDemandee_=progressingTrick.couleurDemandee();
            return defausseCouleurDemandeeSurPartenaireCouleurDominante(
                    repartitionCartesJouees_, repartition_, cartesMaitresses_,
                    couleurDemandee_, couleursStrictementMaitresses_);
        }
        return optTrump(_info);
    }

    CardBelote optTrump(BeloteInfoPliEnCours _info) {
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurAtout_=_info.getCouleurAtout();
        IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
        IdMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        IdList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        Bytes joueursNonJoue_=_info.getJoueursNonJoue();
        Bytes adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        boolean maitreJeu_ = _info.isMaitreJeu();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CustList<HandBelote> suitesJouables_=repartition_.getVal(couleurAtout_).eclater(_info.getRepartitionCartesJouees(), bid);
        CustList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(suitesJouables_,
                cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurAtout_, cartesCertaines_,carteForte_);
        /*Coupe possible non obligatoire, car partenaire.contains(ramasseurVirtuel)*/
        if(canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if(!leadingPartner(couleurDemandee_, carteForte_, cartesPossibles_, adversaireNonJoue_)) {
            return coupe(_info);
        }
        return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_,
                cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
    }

    CardBelote fournirAtoutCouleurDominante(BeloteInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(_info);
        //entame atout
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return followSuitFoe();

        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return followTrumpPartner(_info);
        }
        //peut ramasser
        return playDefaultTrump(_info);
    }

    CardBelote followSuitFoe() {
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        return GameBeloteCommon.hand(repartitionJouables_, progressingTrick.couleurDemandee()).derniereCarte();
    }

    CardBelote followTrumpPartner(BeloteInfoPliEnCours _info) {
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        CustList<HandBelote> suitesJouables_ = repartitionJouables_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        if(suitesJouables_.size()==1) {
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
        }
        for(byte joueur_:adversaire_) {
            if (!GameBeloteCommon.hand(cartesPossibles_, couleurDemandee_, joueur_).estVide() && GameBeloteCommon.hand(cartesPossibles_, couleurDemandee_, joueur_).premiereCarte().strength(couleurDemandee_, bid)
                    > GameBeloteCommon.hand(repartitionJouables_, couleurDemandee_).premiereCarte().strength(couleurDemandee_, bid)) {
                //Il existe un adversaire pouvant surcouper le pli avec un atout relativement maitre sur le joueur courant
                return GameBeloteCommon.hand(repartitionJouables_, couleurDemandee_).premiereCarte();
            }
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
    }

    CardBelote defausseCouleurOrdinaireCouleurDominante(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
        IdMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        IdList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(_info);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return discardFoe(repartitionCartesJouees_, repartition_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
        }
        return discardFoe(repartitionCartesJouees_, repartition_);
    }
    CardBelote defausseAtoutCouleurDominante(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        IdMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        IdList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(_info);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*La couleur demandee est atout*/
            /*Maintenant le joueur se defausse sur demande d'atout*/

            return discardFoe(repartitionCartesJouees_, repartition_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            /*Maintenant le joueur se defausse*/
            return defausseAtoutSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleursStrictementMaitresses_);
        }

        return discardFoe(repartitionCartesJouees_, repartition_);
    }
    CardBelote fournirCouleurOrdinaireSansAtout(BeloteInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(_info);

        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return followSuitFoe();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return followNormalSuitPartner(_info);
        }
        CardBelote card_ = followNormalSuitNoTrump(_info);
        if (card_ != CardBelote.WHITE) {
            return card_;
        }
        /*Maintenant le joueur peut prendre la GameBeloteCommon.main avec une figure a la couleur demandee*/
        if(currentStatus == Role.TAKER) {
            return followNoTrumpAsTaker(_info);
        }
        /*Appele*/
        if(currentStatus == Role.CALLED_PLAYER) {
            return followNoTrumpAsCalledPlayer(_info);
        }
        return followNoTrumpAsDefender(_info);
    }

    CardBelote followNoTrumpAsDefender(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        Bytes adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(_info.getJoueursNonConfiance(),joueursNonJoue_);
        CustList<HandBelote> suitesJouables_=repartition_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        CustList<HandBelote> cartesRelMaitres_ = cartesRelativementMaitre(suitesJouables_, cartesPossibles_, joueursNonJoue_,
                couleurDemandee_, couleurDemandee_, _info.getCartesCertaines(), progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs()));
        /*Defenseur*/
        if(!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        //si aucun adv non joue n'a de l'atout alors les points peuvent etre joues
        return sauveQuiPeutFigure(cartesPossibles_,suitesJouables_,
                cartesRelMaitres_, adversaireNonJoue_, couleurDemandee_);
    }

    CardBelote followNoTrumpAsCalledPlayer(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
        CustList<TrickBelote> plisFaits_= _info.getPlisFaits();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        CustList<HandBelote> suitesJouables_=repartition_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        CustList<HandBelote> cartesRelMaitres_ = cartesRelativementMaitre(suitesJouables_, cartesPossibles_, joueursNonJoue_,
                couleurDemandee_, couleurDemandee_, cartesCertaines_, carteForte_);
        if (!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if(tours_.isEmpty()) {
            return notStarted(_info, suitesJouables_);
        }
        return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
    }

    CardBelote followNoTrumpAsTaker(BeloteInfoPliEnCours _info) {
        return playWhenLastDiscard(_info);
    }

    CardBelote followNormalSuitNoTrump(BeloteInfoPliEnCours _info) {
        CardBelote strongCard_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());

        //Si le joueur ne se defause pas sur la couleur demandee
        CardBelote bestCard_ = GameBeloteCommon.hand(playableCards.couleurs(bid), progressingTrick.couleurDemandee()).premiereCarte();
        if(bestCard_.strength(progressingTrick.couleurDemandee(),bid)<
                strongCard_.strength(progressingTrick.couleurDemandee(),bid)) {
            /*Si le joueur ne peut pas prendre la GameBeloteCommon.main*/
            CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(progressingTrick.couleurDemandee(), _info.getPlisFaits());
            /*Le joueur possede au moins une figure*/
            if(tours_.isEmpty()) {
                /*Si cette couleur est entamee pour la premiere fois*/
                return followPossiblePtsFirstRound(_info);
            }
            /*Maintenant on aborde au moins le deuxieme tour*/
            Suit couleurDemandee_=progressingTrick.couleurDemandee();
            /*Si le dernier pli n'est pas coupe a cette couleur*/
            return GameBeloteCommon.hand(playableCards.couleurs(bid),couleurDemandee_).derniereCarte();
        }
        /*Maintenant on sait que le joueur peut prendre la GameBeloteCommon.main*/
        if(bestCard_.points(bid)<1) {
            //si aucun adv non joue ne possede de figure
            return bestCard_;
        }
        return CardBelote.WHITE;
    }
    CardBelote defausseCouleurOrdinaireSansAtout(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
        IdMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();

        IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        IdList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(_info);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Maintenant le joueur se defausse*/
            return discardFoe(repartitionCartesJouees_, repartition_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseCouleurDemandeeSurPartenaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleursStrictementMaitresses_);
        }
        return discardFoe(repartitionCartesJouees_, repartition_);
    }
    CardBelote fournirAtoutToutAtout(BeloteInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(_info);
        //jeu tout atout
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return followSuitFoe();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return followTrumpPartner(_info);

        }

        return playDefaultTrump(_info);

    }

    CardBelote playDefaultTrump(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CustList<HandBelote> suitesJouables_=repartitionJouables_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        Bytes joueursNonJoue_=_info.getJoueursNonJoue();
        CustList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(suitesJouables_, cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_, cartesCertaines_,carteForte_);
        if(!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
    }

    CardBelote defausseAtoutToutAtout(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartition_=playableCards.couleurs(bid);
        IdMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        IdList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(_info);
        //jeu tout atout
        //
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur se defausse*/
            return discardFoe(repartitionCartesJouees_, repartition_);

        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseAtoutSurPartenaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleursStrictementMaitresses_);
        }
        return discardFoe(repartitionCartesJouees_, repartition_);

    }
    BeloteInfoPliEnCours initInformations() {
        return common.initInformations(currentHand);
    }

    CardBelote coupe(BeloteInfoPliEnCours _info) {
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurAtout_=_info.getCouleurAtout();
        IdMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        CustList<HandBelote> suitesJouables_=repartitionJouables_.getVal(couleurAtout_).eclater(_info.getRepartitionCartesJouees(), bid);
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        Bytes joueursNonJoue_=_info.getJoueursNonJoue();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CustList<HandBelote> cartesRelMaitres_ = cartesRelativementMaitre(suitesJouables_, cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurAtout_, cartesCertaines_, carteForte_);
        if(!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte();
    }

    CardBelote sauveQuiPeutFigure(
            IdMap<Suit,CustList<HandBelote>> _cartesPossibles,
            CustList<HandBelote> _suites,
            CustList<HandBelote> _cartesRelMaitres,Bytes _adversaireNonJoue,
            Suit _couleurDemandee) {
        //si aucun adversaire ne possede une carte a point dans la couleur demandee, alors les points peuvent etre sauves
        boolean aucuneCartePointsAdvNonJoue_ = true;
        for(byte j: _adversaireNonJoue) {
            if (!GameBeloteCommon.hand(_cartesPossibles, _couleurDemandee, j).estVide() && GameBeloteCommon.hand(_cartesPossibles, _couleurDemandee, j).premiereCarte().points(bid) >= 1) {
                aucuneCartePointsAdvNonJoue_ = false;
                break;
            }
        }
        if (!_cartesRelMaitres.isEmpty()) {
            if (_cartesRelMaitres.size() == 1) {
                return _suites.first().premiereCarte();
            }
            //cartesRelMaitres.size() > 1 && cartesRelMaitres.get(1).premiereCarte().estUneFigure()
            return _cartesRelMaitres.last().premiereCarte();
        }
        if(aucuneCartePointsAdvNonJoue_) {
            return jeuFigureHauteDePlusFaibleSuite(_suites,bid);
        }
        return _suites.last().derniereCarte();
    }

    CustList<HandBelote> cartesRelativementMaitre(
            CustList<HandBelote> _suites, IdMap<Suit, CustList<HandBelote>> _cartesPossibles,
            Bytes _joueursNonJoue, Suit _couleurDemandee,
            Suit _couleurJoueur,
            IdMap<Suit, CustList<HandBelote>> _cartesCertaines, CardBelote _carteForte) {
        byte maxValeur_=0;
        Suit dom_ = bid.getSuit();
        if(_couleurJoueur==dom_&&_couleurDemandee!=dom_) {
            for(byte joueur_:_joueursNonJoue) {
                if(!GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).estVide()&&GameBeloteCommon.hand(_cartesCertaines,_couleurDemandee,joueur_).estVide()) {
                    maxValeur_=(byte)NumberUtil.max(GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).premiereCarte().strength(_couleurDemandee,bid),maxValeur_);
                }
            }
        } else {
            for(byte joueur_:_joueursNonJoue) {
                if(!GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).estVide()) {
                    maxValeur_=(byte)NumberUtil.max(GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).premiereCarte().strength(_couleurDemandee,bid),maxValeur_);
                }
            }
        }
        maxValeur_=(byte)NumberUtil.max(maxValeur_,_carteForte.strength(_couleurDemandee,bid));
        return filterSeq(_suites, _couleurDemandee, maxValeur_);
    }

    private CustList<HandBelote> filterSeq(CustList<HandBelote> _suites, Suit _couleurDemandee, byte _maxValeur) {
        CustList<HandBelote> suitesBis_=new CustList<HandBelote>();
        for(HandBelote suite_: _suites) {
            if(suite_.premiereCarte().strength(_couleurDemandee,bid)> _maxValeur) {
                suitesBis_.add(suite_);
            } else {
                break;
            }
        }
        return suitesBis_;
    }

    private static CardBelote jeuFigureHauteDePlusFaibleSuite(CustList<HandBelote> _suites, BidBeloteSuit _contrat) {
        if(_suites.size()== IndexConstants.ONE_ELEMENT) {
            return _suites.first().premiereCarte();
        }
        return cartePlusPetitePoints(_suites,_contrat);
    }
    static CardBelote cartePlusPetitePoints(CustList<HandBelote> _suites, BidBeloteSuit _contrat) {
        CustList<HandBelote> suitesPoints_ = new CustList<HandBelote>();
        CustList<HandBelote> suitesZeroPoints_ = new CustList<HandBelote>();
        for(HandBelote suite_:_suites) {
            HandBelote points_ = new HandBelote();
            HandBelote zeroPoints_ = new HandBelote();
            for(CardBelote c: suite_) {
                if(c.points(_contrat) > 0) {
                    points_.ajouter(c);
                } else {
                    zeroPoints_.ajouter(c);
                }
            }
            if(!points_.estVide()) {
                suitesPoints_.add(points_);
            }
            if(!zeroPoints_.estVide()) {
                suitesZeroPoints_.add(zeroPoints_);
            }
        }
        if(!suitesZeroPoints_.isEmpty()) {
            return suitesZeroPoints_.last().premiereCarte();
        }
        return suitesPoints_.last().premiereCarte();
    }

    CardBelote defausseAtoutSurPartenaireCouleurDominante(
            IdMap<Suit, HandBelote> _repartitionCartesJouees,
            IdMap<Suit, HandBelote> _repartition,
            IdMap<Suit, HandBelote> _cartesMaitresses,
            IdList<Suit> _couleursStrictementMaitresses) {
        IdList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        if(_couleursStrictementMaitresses.size()==common.couleursNonAtouts().size()) {
            IdList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
            couleursFigures_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(_repartition,couleursFigures_,2);
            if(!couleursFigures_.isEmpty()) {
                return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
            }
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        /*Il peut exister une couleur pour se defausser non strictement maitresse*/
        IdList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
        if(!couleursBis_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursBis_);
        }
        return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }

    CardBelote defausseAtoutSurPartenaireCouleursEgales(
            IdMap<Suit,HandBelote> _repartitionCartesJouees,
            IdMap<Suit,HandBelote> _repartition,
            IdMap<Suit,HandBelote> _cartesMaitresses,
            IdList<Suit> _couleursStrictementMaitresses) {
        IdList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        if(_couleursStrictementMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
            IdList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
            couleursFigures_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(_repartition,couleursFigures_,2);
            if(!couleursFigures_.isEmpty()) {
                return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
            }
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        /*Il peut exister une couleur pour se defausser non strictement maitresse*/
        IdList<Suit> couleurs_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleurs_);
        }
        couleurs_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(couleursNonVides_,_repartition,_repartitionCartesJouees);

    }

    CardBelote discardFoe(IdMap<Suit, HandBelote> _repartitionCartesJouees, IdMap<Suit, HandBelote> _repartition) {
        IdList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        IdList<Suit> couleurs_=GameBeloteCommon.couleursSansPoint(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(couleursNonVides_, _repartition, _repartitionCartesJouees);
    }

    CardBelote defausseCouleurDemandeeSurPartenaireCouleurDominante(
            IdMap<Suit, HandBelote> _repartitionCartesJouees,
            IdMap<Suit, HandBelote> _repartition,
            IdMap<Suit, HandBelote> _cartesMaitresses,
            Suit _couleurDemandee,
            IdList<Suit> _couleursStrictementMaitresses) {

        IdList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        IdList<Suit> couleursAutreQueDemandeeEtAtout_ = common.couleursNonAtouts();
        couleursAutreQueDemandeeEtAtout_.removeObj(_couleurDemandee);
        if(_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandeeEtAtout_)) {
            IdList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
            couleursFigures_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(_repartition,couleursFigures_,2);
            if(!couleursFigures_.isEmpty()) {
                return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
            }
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        /*Il peut exister une couleur non strictement maitresse pour se defausser*/
        IdList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
        if(!couleursBis_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursBis_);
        }
        return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }

    CardBelote defausseCouleurDemandeeSurPartenaireCouleursEgales(
            IdMap<Suit, HandBelote> _repartitionCartesJouees,
            IdMap<Suit, HandBelote> _repartition,
            IdMap<Suit, HandBelote> _cartesMaitresses,
            IdList<Suit> _couleursStrictementMaitresses) {
        IdList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        if(_couleursStrictementMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
            IdList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
            couleursFigures_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(_repartition,couleursFigures_,2);
            if(!couleursFigures_.isEmpty()) {
                return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
            }
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        /*Il peut exister une couleur non strictement maitresse pour se defausser*/
        IdList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
        if(!couleursBis_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursBis_);
        }
        return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }

    CardBelote discardPartner(IdMap<Suit, HandBelote> _repartitionCartesJouees, IdMap<Suit, HandBelote> _repartition, IdList<Suit> _couleursNonVides) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursAvecPoints(_repartition, bid, _couleursNonVides);
        if(!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(_couleursNonVides,_repartition,_repartitionCartesJouees);
    }
    private CardBelote sauverFiguresDefausse(
            IdList<Suit> _couleursFigures,
            IdMap<Suit,HandBelote> _repartition,
            IdMap<Suit,HandBelote> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursAvecLePlusGrandNbPoints(_repartition, bid, _couleursFigures);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartitionCartesJouees, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLaPlusGrandeFigure(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote jeuPetiteCarteCouleurFigure(
            IdList<Suit> _couleurs, IdMap<Suit,HandBelote> _repartition,
            IdMap<Suit,HandBelote> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursAvecLaPlusPetiteCarteBasse(_repartition, bid, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote jeuGrandeCarteDefausseMaitre(
            IdList<Suit> _couleursFigures,
            IdMap<Suit,HandBelote> _repartition) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleursFigures);
        couleurs_ = GameBeloteCommon.couleursLesPlusHautes(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusGrandNbPoints(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote jouerPetiteCarteDefausse(
            IdList<Suit> _couleurs,
            IdMap<Suit,HandBelote> _repartition,
            IdMap<Suit,HandBelote> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote jeuPetiteDefausseMaitre(
            IdMap<Suit,HandBelote> _cartesMaitresses,
            IdMap<Suit,HandBelote> _repartition,
            IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_cartesMaitresses, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }

    Role getCurrentStatus() {
        return currentStatus;
    }
}
