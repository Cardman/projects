package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.PossibleTrickWinner;
import cards.consts.Status;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.*;

public final class GameBeloteProgTrick {
    private GameBeloteTeamsRelation teamsRelation;
    private GameBeloteTrickInfo info;
    private TrickBelote progressingTrick;

    private HandBelote currentHand;
    private GameBeloteCommonPlaying common;
    private Status currentStatus;
    private HandBelote playableCards;
    private BidBeloteSuit bid;
    private byte taker;

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
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        BeloteInfoPliEnCours info_ = initInformations();
        if(bid.getCouleurDominante()) {
            Suit couleurAtout_=bid.getCouleur();
            if (couleurDemandee_ != couleurAtout_) {
                if(!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                    return fournirCouleurOrdinaireCouleurDominante(info_);
                }
                if(!repartitionJouables_.getVal(couleurAtout_).estVide()) {
                    return coupeCouleurDominante(info_);
                }
                return defausseCouleurOrdinaireCouleurDominante(info_);
            }
            //entame atout
            if(!repartitionJouables_.getVal(couleurAtout_).estVide()) {
                return fournirAtoutCouleurDominante(info_);
            }
            return defausseAtoutCouleurDominante(info_);
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
        if(currentStatus == Status.TAKER) {
            return followDominantAsTaker(_info);
        }
        /*Appele*/
        if(currentStatus == Status.CALLED_PLAYER) {
            return followDominantAsCalledPlayer(_info);
        }
        return followDominantAsDefender(_info);
    }

    CardBelote followDominantAsDefender(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        Suit couleurAtout_= _info.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_= _info.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        Bytes adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        EqList<HandBelote> cartesRelMaitres_;
        cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurDemandee_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_, cartesCertaines_,carteForte_);
        /*Defenseur*/
        if(!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        boolean pasAtoutAdvNonJoue_ = GameBeloteTrickHypothesis.pasAtoutJoueurs(adversaireNonJoue_, cartesPossibles_, couleurAtout_);
        //si aucun adv non joue n'a de l'atout alors les points peuvent etre joues
        if(pasAtoutAdvNonJoue_) {
            return sauveQuiPeutFigure(cartesPossibles_,GameBeloteCommon.suite(suites_,couleurDemandee_), cartesRelMaitres_, adversaireNonJoue_, couleurDemandee_);
        }
        return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
    }

    CardBelote followDominantAsCalledPlayer(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        EnumMap<Suit,EqList<HandBelote>> suites_= _info.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_= _info.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        EqList<HandBelote> cartesRelMaitres_;
        EqList<HandBelote> seqs_ = GameBeloteCommon.suite(suites_, couleurDemandee_);
        cartesRelMaitres_=cartesRelativementMaitre(seqs_, cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_, cartesCertaines_,carteForte_);
        if (!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if(tours_.isEmpty()) {
            if(joueursNonJoue_.containsObj(taker)) {
                if(!GameBeloteCommon.hand(cartesCertaines_,couleurDemandee_,taker).estVide()) {
                    Order or_ = bid.getOrdre();
                    EnumMap<Suit,HandBelote> repartitionCartesJouees_= _info.getRepartitionCartesJouees();
                    boolean cartesMaitressesJouees_ = GameBeloteBeginTrick.playedLeading(bid, taker, couleurDemandee_, repartitionCartesJouees_, cartesCertaines_, or_);
                    if(cartesMaitressesJouees_) {
                        return seqs_.first().premiereCarte();
                    }
                }
            }
            return playWhenLastDiscard(_info);
        }
        return playAtNextRound(_info);
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
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_= _info.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_= _info.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        Bytes joueursSusceptiblesDeCouper_=new Bytes();
        EqList<HandBelote> cartesRelMaitres_;
        cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurDemandee_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_, cartesCertaines_,carteForte_);
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
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        byte next_ = info.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_= _info.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        boolean maitreJeu_ = _info.isMaitreJeu();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        Bytes joueursSusceptiblesDeCouper_=GameBeloteCommonPlaying.joueursSusceptiblesCoupe(cartesPossibles_,couleurDemandee_,bid.getCouleur(),adversaire_);
        byte maxTwo_;
        for (byte joueur_ : adversaire_) {
            if (joueursSusceptiblesDeCouper_.containsObj(joueur_)) {
                return GameBeloteCommon.hand(repartition_, couleurDemandee_).derniereCarte();
            }
        }
        if (maitreJeu_) {
            maxTwo_ = 0;
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ != next_) {
                    maxTwo_ = (byte) Math.max(GameBeloteCommon.hand(cartesPossibles_, couleurDemandee_, joueur_).total(), maxTwo_);
                }
            }
            if (GameBeloteCommon.hand(suites_, couleurDemandee_, 0).total() > maxTwo_) {
                return GameBeloteCommon.hand(repartitionJouables_, couleurDemandee_).premiereCarte();
            }
            return GameBeloteCommon.hand(repartition_, couleurDemandee_).derniereCarte();
        }
        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_, couleurDemandee_), bid);
    }

    CardBelote playWhenLastDiscard(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_= _info.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        EqList<HandBelote> cartesRelMaitres_;
        cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurDemandee_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_, cartesCertaines_,carteForte_);
        /*Le joueur n'a aucune cartes maitresses*/
        if(!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
    }

    CardBelote followNormalSuit(BeloteInfoPliEnCours _info) {
        CardBelote strongCard_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        //Si le joueur ne coupe pas et ne se defause pas sur la couleur demandee
        if(GameBeloteCommon.hand(playableCards.couleurs(bid),progressingTrick.couleurDemandee()).premiereCarte().strength(progressingTrick.couleurDemandee(),bid)<strongCard_.strength(progressingTrick.couleurDemandee(),bid)) {
            CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(progressingTrick.couleurDemandee(), _info.getPlisFaits());
            /*Si le joueur ne peut pas prendre la GameBeloteCommon.main*/
            if(tours_.isEmpty()) {
                /*Si cette couleur est entamee pour la premiere fois*/
                return followPossiblePtsFirstRound(_info);
            }
            return followSuitFoe();
        }
        /*Maintenant on sait que le joueur peut prendre la main*/
        if(GameBeloteCommon.hand(playableCards.couleurs(bid),progressingTrick.couleurDemandee()).premiereCarte().points(bid)<1) {
            Suit couleurDemandee_=progressingTrick.couleurDemandee();
            EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
            //si aucun adv non joue ne possede de figure
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).premiereCarte();
        }
        return CardBelote.WHITE;
    }

    boolean leadingPartner(Suit _couleurDemandee, CardBelote _carteForte, EnumMap<Suit, EqList<HandBelote>> _cartesPossibles, Bytes _adversaireNonJoue) {
        boolean partenaireMaitre_ = true;
        for(byte j: _adversaireNonJoue) {
            HandBelote couleurPossible_ = GameBeloteCommon.hand(_cartesPossibles, _couleurDemandee,j);
            if (!couleurPossible_.estVide()) {
                byte max_ = couleurPossible_.premiereCarte().strength(_couleurDemandee,bid);
                if(_carteForte.strength(_couleurDemandee,bid)>max_) {
                    continue;
                }
            }
            partenaireMaitre_ = false;
            break;
        }
        return partenaireMaitre_;
    }

    CardBelote followPossiblePtsFirstRound(BeloteInfoPliEnCours _info) {
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        Bytes adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        boolean partenaireMaitre_ = leadingPartner(couleurDemandee_, carteForte_, cartesPossibles_, adversaireNonJoue_);
        if(partenaireMaitre_) {
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
        }
        return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
    }

    CardBelote followNormalSuitPartner(BeloteInfoPliEnCours _info) {
        EnumMap<Suit,EqList<HandBelote>> suites_= _info.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        for(byte joueur_:adversaire_) {
            if(!GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).estVide()) {
                if(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).premiereCarte().strength(couleurDemandee_,bid)
                        >GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
            }
        }
        if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).nombreCartesPoints(bid)>1) {
            return cartePlusPetitePoints(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
    }

    private boolean canLeadTrick(boolean _maitreJeu, EqList<HandBelote> _cartesRelMaitres) {
        return !_cartesRelMaitres.isEmpty() && _maitreJeu;
    }

    CardBelote coupeCouleurDominante(BeloteInfoPliEnCours _info) {
        Suit couleurAtout_=bid.getCouleur();
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
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
            EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
            return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            Suit couleurAtout_=_info.getCouleurAtout();
            EnumMap<Suit,EqList<HandBelote>> suites_= _info.getSuitesTouteCouleur();
            EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
            if(GameBeloteCommon.suite(suites_,couleurAtout_).size()==1) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte();
            }
            return GameBeloteCommon.suite(suites_,couleurAtout_).last().premiereCarte();
        }
        /*Coupe obligatoire (ou surcoupe ou sous-coupe)*/
        return coupe(_info);
    }
    CardBelote coupeFacultativeCouleurDominante(BeloteInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(_info);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur se defausse*/
            EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
            EnumMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
            return discardFoe(repartitionCartesJouees_, repartition_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
            EnumMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
            EnumMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
            EnumList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
            Suit couleurDemandee_=progressingTrick.couleurDemandee();
            return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
        }
        return optTrump(_info);
    }

    CardBelote optTrump(BeloteInfoPliEnCours _info) {
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurAtout_=_info.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandBelote>> suites_=_info.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        Bytes joueursNonJoue_=_info.getJoueursNonJoue();
        Bytes adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        boolean maitreJeu_ = _info.isMaitreJeu();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        EqList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurAtout_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurAtout_, cartesCertaines_,carteForte_);
        /*Coupe possible non obligatoire, car partenaire.contains(ramasseurVirtuel)*/
        if(canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if(!leadingPartner(couleurDemandee_, carteForte_, cartesPossibles_, adversaireNonJoue_)) {
            return coupe(_info);
        }
        return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
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
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        return GameBeloteCommon.hand(repartitionJouables_, progressingTrick.couleurDemandee()).derniereCarte();
    }

    CardBelote followTrumpPartner(BeloteInfoPliEnCours _info) {
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        EqList<HandBelote> suitesJouables_ = repartitionJouables_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        if(suitesJouables_.size()==1) {
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
        }
        for(byte joueur_:adversaire_) {
            if(!GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).estVide()) {
                if(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).premiereCarte().strength(couleurDemandee_,bid)
                        >GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)) {
                    //Il existe un adversaire pouvant surcouper le pli avec un atout relativement maitre sur le joueur courant
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
            }
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
    }

    CardBelote defausseCouleurOrdinaireCouleurDominante(BeloteInfoPliEnCours _info) {
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        EnumMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
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
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        EnumMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
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
        if(currentStatus == Status.TAKER) {
            return followNoTrumpAsTaker(_info);
        }
        /*Appele*/
        if(currentStatus == Status.CALLED_PLAYER) {
            return followNoTrumpAsCalledPlayer(_info);
        }
        return followNoTrumpAsDefender(_info);
    }

    CardBelote followNoTrumpAsDefender(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        EnumMap<Suit,EqList<HandBelote>> suites_= _info.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        Bytes adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        EqList<HandBelote> cartesRelMaitres_;
        cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurDemandee_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_, cartesCertaines_,carteForte_);
        /*Defenseur*/
        if(!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        //si aucun adv non joue n'a de l'atout alors les points peuvent etre joues
        return sauveQuiPeutFigure(cartesPossibles_,GameBeloteCommon.suite(suites_,couleurDemandee_), cartesRelMaitres_, adversaireNonJoue_, couleurDemandee_);
    }

    CardBelote followNoTrumpAsCalledPlayer(BeloteInfoPliEnCours _info) {
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_= _info.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_= _info.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        Bytes joueursNonJoue_= _info.getJoueursNonJoue();
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        EqList<HandBelote> cartesRelMaitres_;
        cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurDemandee_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_, cartesCertaines_,carteForte_);
        EqList<HandBelote> seqs_ = GameBeloteCommon.suite(suites_, couleurDemandee_);
        if (!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if(tours_.isEmpty()) {
            if(joueursNonJoue_.containsObj(taker)) {
                if(!GameBeloteCommon.hand(cartesCertaines_,couleurDemandee_,taker).estVide()) {
                    Order or_ = bid.getOrdre();
                    EnumMap<Suit,HandBelote> repartitionCartesJouees_= _info.getRepartitionCartesJouees();
                    boolean cartesMaitressesJouees_ = GameBeloteBeginTrick.playedLeading(bid, taker, couleurDemandee_, repartitionCartesJouees_, cartesCertaines_, or_);
                    if(cartesMaitressesJouees_) {
                        return seqs_.first().premiereCarte();
                    }
                }
            }
            return playWhenLastDiscard(_info);
        }
        return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
    }

    CardBelote followNoTrumpAsTaker(BeloteInfoPliEnCours _info) {
        return playWhenLastDiscard(_info);
    }

    CardBelote followNormalSuitNoTrump(BeloteInfoPliEnCours _info) {
        CardBelote strongCard_=progressingTrick.carteDuJoueur(_info.getRamasseurVirtuel(),teamsRelation.getNombreDeJoueurs());

        //Si le joueur ne se defause pas sur la couleur demandee
        if(GameBeloteCommon.hand(playableCards.couleurs(bid),progressingTrick.couleurDemandee()).premiereCarte().strength(progressingTrick.couleurDemandee(),bid)<
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
            EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
            /*Si le dernier pli n'est pas coupe a cette couleur*/
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant on sait que le joueur peut prendre la GameBeloteCommon.main*/
        if(GameBeloteCommon.hand(playableCards.couleurs(bid),progressingTrick.couleurDemandee()).premiereCarte().points(bid)<1) {
            Suit couleurDemandee_=progressingTrick.couleurDemandee();
            EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
            //si aucun adv non joue ne possede de figure
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).premiereCarte();
        }
        return CardBelote.WHITE;
    }
    CardBelote defausseCouleurOrdinaireSansAtout(BeloteInfoPliEnCours _info) {
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();

        EnumMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
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
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        EqList<HandBelote> suitesJouables_=repartitionJouables_.getVal(couleurDemandee_).eclater(_info.getRepartitionCartesJouees(), bid);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        Bytes joueursNonJoue_=_info.getJoueursNonJoue();
        EqList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(suitesJouables_, cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_, cartesCertaines_,carteForte_);
        if(!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
    }

    CardBelote defausseAtoutToutAtout(BeloteInfoPliEnCours _info) {
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        EnumMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=_info.getStrictCouleursMaitresses();
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
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_=_info.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        Bytes joueursNonJoue_=_info.getJoueursNonJoue();
        byte ramasseurVirtuel_=_info.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        EqList<HandBelote> cartesRelMaitres_;
        cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurAtout_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurAtout_, cartesCertaines_,carteForte_);
        if(!cartesRelMaitres_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte();
    }

    CardBelote sauveQuiPeutFigure(
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EqList<HandBelote> _suites,
            EqList<HandBelote> _cartesRelMaitres,Bytes _adversaireNonJoue,
            Suit _couleurDemandee) {
        //si aucun adversaire ne possede une carte a point dans la couleur demandee, alors les points peuvent etre sauves
        boolean aucuneCartePointsAdvNonJoue_ = true;
        for(byte j: _adversaireNonJoue) {
            if(GameBeloteCommon.hand(_cartesPossibles,_couleurDemandee,j).estVide()) {
                continue;
            }
            if(GameBeloteCommon.hand(_cartesPossibles,_couleurDemandee,j).premiereCarte().points(bid)<1) {
                continue;
            }
            aucuneCartePointsAdvNonJoue_ = false;
            break;
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

    EqList<HandBelote> cartesRelativementMaitre(
            EqList<HandBelote> _suites, EnumMap<Suit, EqList<HandBelote>> _cartesPossibles,
            Bytes _joueursNonJoue, Suit _couleurDemandee,
            Suit _couleurJoueur,
            EnumMap<Suit, EqList<HandBelote>> _cartesCertaines, CardBelote _carteForte) {
        byte maxValeur_=0;
        Suit dom_ = bid.getCouleur();
        EqList<HandBelote> suitesBis_=new EqList<HandBelote>();
        if(_couleurJoueur==dom_&&_couleurDemandee!=dom_) {
            for(byte joueur_:_joueursNonJoue) {
                if(!GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).estVide()&&GameBeloteCommon.hand(_cartesCertaines,_couleurDemandee,joueur_).estVide()) {
                    maxValeur_=(byte)Math.max(GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).premiereCarte().strength(_couleurDemandee,bid),maxValeur_);
                }
            }
        } else {
            for(byte joueur_:_joueursNonJoue) {
                if(!GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).estVide()) {
                    maxValeur_=(byte)Math.max(GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).premiereCarte().strength(_couleurDemandee,bid),maxValeur_);
                }
            }
        }
        maxValeur_=(byte)Math.max(maxValeur_,_carteForte.strength(_couleurDemandee,bid));
        for(HandBelote suite_:_suites) {
            if(suite_.premiereCarte().strength(_couleurDemandee,bid)>maxValeur_) {
                suitesBis_.add(suite_);
            } else {
                break;
            }
        }
        return suitesBis_;
    }

    private static CardBelote jeuFigureHauteDePlusFaibleSuite(EqList<HandBelote> _suites, BidBeloteSuit _contrat) {
        if(_suites.size()==CustList.ONE_ELEMENT) {
            return _suites.first().premiereCarte();
        }
        return cartePlusPetitePoints(_suites,_contrat);
    }
    static CardBelote cartePlusPetitePoints(EqList<HandBelote> _suites, BidBeloteSuit _contrat) {
        EqList<HandBelote> suitesPoints_ = new EqList<HandBelote>();
        EqList<HandBelote> suitesZeroPoints_ = new EqList<HandBelote>();
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
            EnumMap<Suit, HandBelote> _repartitionCartesJouees,
            EnumMap<Suit, HandBelote> _repartition,
            EnumMap<Suit, HandBelote> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        if(_couleursStrictementMaitresses.size()==common.couleursNonAtouts().size()) {
            EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
            couleursFigures_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(_repartition,couleursFigures_,2);
            if(!couleursFigures_.isEmpty()) {
                return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
            }
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        /*Il peut exister une couleur pour se defausser non strictement maitresse*/
        EnumList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
        if(!couleursBis_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursBis_);
        }
        return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }

    CardBelote defausseAtoutSurPartenaireCouleursEgales(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        if(_couleursStrictementMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
            EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
            couleursFigures_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(_repartition,couleursFigures_,2);
            if(!couleursFigures_.isEmpty()) {
                return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
            }
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        /*Il peut exister une couleur pour se defausser non strictement maitresse*/
        EnumList<Suit> couleurs_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleurs_);
        }
        couleurs_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(couleursNonVides_,_repartition,_repartitionCartesJouees);

    }

    CardBelote discardFoe(EnumMap<Suit, HandBelote> _repartitionCartesJouees, EnumMap<Suit, HandBelote> _repartition) {
        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        EnumList<Suit> couleurs_=GameBeloteCommon.couleursSansPoint(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(couleursNonVides_, _repartition, _repartitionCartesJouees);
    }

    CardBelote defausseCouleurDemandeeSurPartenaireCouleurDominante(
            EnumMap<Suit, HandBelote> _repartitionCartesJouees,
            EnumMap<Suit, HandBelote> _repartition,
            EnumMap<Suit, HandBelote> _cartesMaitresses,
            Suit _couleurDemandee,
            EnumList<Suit> _couleursStrictementMaitresses) {

        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        EnumList<Suit> couleursAutreQueDemandeeEtAtout_ = common.couleursNonAtouts();
        couleursAutreQueDemandeeEtAtout_.removeObj(_couleurDemandee);
        if(_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandeeEtAtout_)) {
            EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
            couleursFigures_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(_repartition,couleursFigures_,2);
            if(!couleursFigures_.isEmpty()) {
                return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
            }
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        /*Il peut exister une couleur non strictement maitresse pour se defausser*/
        EnumList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
        if(!couleursBis_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursBis_);
        }
        return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }

    CardBelote defausseCouleurDemandeeSurPartenaireCouleursEgales(
            EnumMap<Suit, HandBelote> _repartitionCartesJouees,
            EnumMap<Suit, HandBelote> _repartition,
            EnumMap<Suit, HandBelote> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        if(_couleursStrictementMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
            EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
            couleursFigures_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(_repartition,couleursFigures_,2);
            if(!couleursFigures_.isEmpty()) {
                return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
            }
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        /*Il peut exister une couleur non strictement maitresse pour se defausser*/
        EnumList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
        if(!couleursBis_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursBis_);
        }
        return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }

    CardBelote discardPartner(EnumMap<Suit, HandBelote> _repartitionCartesJouees, EnumMap<Suit, HandBelote> _repartition, EnumList<Suit> _couleursNonVides) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursAvecPoints(_repartition, bid, _couleursNonVides);
        if(!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(_couleursNonVides,_repartition,_repartitionCartesJouees);
    }
    private CardBelote sauverFiguresDefausse(
            EnumList<Suit> _couleursFigures,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursAvecLePlusGrandNbPoints(_repartition, bid, _couleursFigures);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartitionCartesJouees, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLaPlusGrandeFigure(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote jeuPetiteCarteCouleurFigure(
            EnumList<Suit> _couleurs, EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursAvecLaPlusPetiteCarteBasse(_repartition, bid, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote jeuGrandeCarteDefausseMaitre(
            EnumList<Suit> _couleursFigures,
            EnumMap<Suit,HandBelote> _repartition) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleursFigures);
        couleurs_ = GameBeloteCommon.couleursLesPlusHautes(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusGrandNbPoints(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote jouerPetiteCarteDefausse(
            EnumList<Suit> _couleurs,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote jeuPetiteDefausseMaitre(
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            EnumMap<Suit,HandBelote> _repartition,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_cartesMaitresses, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }

    Status getCurrentStatus() {
        return currentStatus;
    }
}
