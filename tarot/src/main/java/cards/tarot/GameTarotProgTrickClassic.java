package cards.tarot;

import cards.consts.PossibleTrickWinner;
import cards.consts.Status;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.*;

public final class GameTarotProgTrickClassic {

    private GameTarotTeamsRelation teamsRelation;

    private GameTarotTrickInfo doneTrickInfo;
    private HandTarot calledCards;

    private HandTarot currentHand;
    private GameTarotCommonPlaying common;
    private Status currentStatus;
    private Bytes confidentPlayers;
    private Bytes notConfidentPlayers;
    private Bytes played;
    private Bytes notPlayed;
    private Bytes notConfidentPlayersNotPlay;
    private HandTarot playableCards;
    private HandTarot discarded = new HandTarot();
    public GameTarotProgTrickClassic(GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
                                     HandTarot _calledCards, HandTarot _currentHand) {
        doneTrickInfo = _done;
        teamsRelation = _teamsRelation;
        calledCards = _calledCards;
        currentHand = _currentHand;
        common = new GameTarotCommonPlaying(_done,_teamsRelation);
        byte nbPlayers_ = _teamsRelation.getNombreDeJoueurs();
        TrickTarot trTarot_ = _done.getProgressingTrick();
        played = trTarot_.joueursAyantJoue(nbPlayers_);
        notPlayed = GameTarotTeamsRelation.autresJoueurs(played,nbPlayers_);
        byte nextPlayer_ = trTarot_.getNextPlayer(nbPlayers_);
        playableCards = common.cartesJouables(currentHand.couleurs());
        notPlayed.removeObj(nextPlayer_);
        currentStatus = _teamsRelation.statutDe(nextPlayer_);
        confidentPlayers = _teamsRelation.joueursConfiance(nextPlayer_,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        notConfidentPlayers = _teamsRelation.joueursNonConfiance(nextPlayer_,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        notConfidentPlayersNotPlay = GameTarotTeamsRelation.intersectionJoueurs(notConfidentPlayers,notPlayed);
        boolean knownLastHand_ = !doneTrickInfo.getLastSeenHand().estVide();
        if (knownLastHand_ && teamsRelation.isVirtualTaker(nextPlayer_)) {
            discarded.ajouterCartes(_done.getTricks().first().getCartes());
        }
    }
    CardTarot enCoursClassic() {
        if (playableCards.total() == 1) {
            return playableCards.premiereCarte();
        }
        if (doneTrickInfo.getProgressingTrick().couleurDemandee() == Suit.UNDEFINED) {
            //cartesJouables.total() > 1
            GameTarotBeginTrickClassic g_ = new GameTarotBeginTrickClassic(doneTrickInfo,teamsRelation,calledCards,currentHand);
            if (teamsRelation.existePreneur()) {
                return g_.entameSurExcuseClassique();
                /* Variables locales avec jeu d'equipe */
            }
            return g_.entameClassique();
        }
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        TarotInfoPliEnCours info_ = initInformations();
        CardTarot card_ = tryPlayExcuseOrLead(info_,currentHand);
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        if (Suit.couleursOrdinaires().containsObj(couleurDemandee_)) {
            if (!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                return fournirCouleurOrdinaireClassique(info_);
            }
            if (!repartitionJouables_.getVal(Suit.TRUMP).estVide()) {
                return coupeClassique(info_);
            }
            return defausseCouleurOrdinaireClassique(info_);
        }
        if (!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
            return fournirAtoutClassique(info_);
        }
        return defausseAtoutClassique(info_);
    }
    CardTarot fournirCouleurOrdinaireClassique(TarotInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(_info);
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return followNormalSuitFoe(_info);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return followNormalSuitPartners(_info);
        }
        return followDefaultSuit(_info);
    }

    CardTarot followDefaultSuit(TarotInfoPliEnCours _info) {
        //incertitude
        CardTarot card_ = followNormalSuit(_info);
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        /* Maintenant le joueur peut prendre la main avec une figure */
        if (teamsRelation.isVirtualTaker(_info.getCurrentPlayer())) {
            //fournir a la couleur demandee ordinaire
            return followAsTaker(_info);
        }
        /* Appele */
        if (currentStatus == Status.CALLED_PLAYER) {
            return followAsCalledPlayer(_info);

        }
        /* Defenseur */
        return followAsDefender(_info);
    }

    private static boolean hasExcPlusCard(HandTarot _current) {
        return _current.total() == 2 && _current.contient(CardTarot.EXCUSE);
    }

    CardTarot followAsDefender(TarotInfoPliEnCours _info) {
        //fournir a la couleur demandee ordinaire
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), teamsRelation.getNombreDeJoueurs());
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EqList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        EqList<HandTarot> cartesRelMaitres_;
        cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                couleurDemandee_, couleurDemandee_, cartesCertaines_,
                carteForte_);
        CustList<TrickTarot> tours_ = GameTarotCommonPlaying.tours(couleurDemandee_, plisFaits_);
        boolean maitreJeu_ = _info.isMaitreJeu();
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last()
                    .premiereCarte();
        }
        if (GameTarotTrickHypothesis.pasAtout(notConfidentPlayersNotPlay,
                cartesPossibles_)) {
            return sauveQuiPeutFigure(cartesPossibles_,
                    suites_, cartesRelMaitres_,
                    notConfidentPlayersNotPlay,
                    couleurDemandee_);
        }
        if (tours_.isEmpty()) {
            EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
            if (couleursAppelees_.containsObj(couleurDemandee_)) {
                return carteLaPlusPetite(suites_);
            }
            if (cartesRelMaitres_.isEmpty()) {
                return carteLaPlusPetite(suites_);
            }
            if (!joueursNonJoue_.containsObj(teamsRelation.getTaker())
                    || carteForte_.isCharacter()) {
                /*
                    Si le joueur
                    (defenseur)
                    va jouer
                    apres le
                    preneur et il
                    reste des
                    joueurs
                    susceptibles
                    d'etre
                    l'appele ou
                    il existe une
                    figure que
                    peut prendre
                    le joueur
                    */
                return gearRelativeLeadingCards(suites_, cartesRelMaitres_);
            }
            if (cartesRelMaitres_.first().total() == 1
                    && repartitionCouleDem_.total() == 2) {
                return repartitionCouleDem_.premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        /* Maintenant on est au moins au deuxieme tour */
        return carteLaPlusPetite(suites_);
    }

    private static CardTarot gearRelativeLeadingCards(EqList<HandTarot> _suites, EqList<HandTarot> _cartesRelMaitres) {
        if (_cartesRelMaitres.size() == 1
                || !_cartesRelMaitres.last()
                .premiereCarte().isCharacter()) {
            return _suites.first().premiereCarte();
        }
        return _cartesRelMaitres.last()
                .premiereCarte();
    }

    CardTarot followAsCalledPlayer(TarotInfoPliEnCours _info) {
        //fournir a la couleur demandee ordinaire
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), teamsRelation.getNombreDeJoueurs());
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
        EqList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        EnumMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        EqList<HandTarot> cartesRelMaitres_;
        cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                couleurDemandee_, couleurDemandee_, cartesCertaines_,
                carteForte_);
        CustList<TrickTarot> tours_ = GameTarotCommonPlaying.tours(couleurDemandee_, plisFaits_);
        boolean maitreJeu_ = _info.isMaitreJeu();
        if (tours_.isEmpty()) {
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            HandTarot cartesChienCouleur_ = doneTrickInfo.getLastSeenHand().couleurs()
                    .getVal(couleurDemandee_);
            if(!cartesRelMaitres_.isEmpty() && peutSauverFigureAppele(
                    cartesPossibles_,
                    couleurDemandee_,
                    cartesChienCouleur_,
                    notConfidentPlayersNotPlay,
                    true)) {
                if (!joueursNonJoue_.containsObj(teamsRelation.getTaker())) {
                    /*Si l'appele
                joue apres le preneur*/
                    return gearRelativeLeadingCards(cartesRelMaitres_, cartesRelMaitres_);
                }
                if (cartesChienCouleur_
                        .nombreDeFigures() > 0) {
                    /*Si l'appele joue
                    avant le preneur*/
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
            }
            return playWhenLastDiscard(_info);
        }
        TrickTarot dernierPli_;
        dernierPli_ = tours_.last();
        Bytes dernieresDefausses_;
        dernieresDefausses_ = dernierPli_.joueursDefausses(nombreDeJoueurs_);
            /*
            Deuxieme tour pour un appele ne coupant pas la
            couleur demandee differente de l'atout
            */
        Bytes joueursSusceptiblesDeCouper_;
        joueursSusceptiblesDeCouper_ = GameTarotTrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles_,couleurDemandee_,joueursNonJoue_);
        if (!joueursSusceptiblesDeCouper_.isEmpty()) {
            return processWhenPossibleTrumps(suites_, joueursSusceptiblesDeCouper_, maitreJeu_);
        }
        /* Si la coupe semble improbable */
        if (discardAtSecondRound(tours_, dernieresDefausses_)) {
            return playWhenLastDiscard(_info);
        }
            /*
            Le pli d'avant n'est pas defausse ou c'est au moins
            le troisieme tour
            */
        return tryLeadTrick(cartesMaitresses_, couleurDemandee_, repartitionCouleDem_, suites_, cartesRelMaitres_);
    }

    private static boolean discardAtSecondRound(CustList<TrickTarot> _tours, Bytes _dernieresDefausses) {
        return !_dernieresDefausses.isEmpty() && _tours.size() == 1;
    }

    CardTarot followAsTaker(TarotInfoPliEnCours _info) {
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), teamsRelation.getNombreDeJoueurs());
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
        EqList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        CustList<TrickTarot> tours_ = GameTarotCommonPlaying.tours(couleurDemandee_, plisFaits_);
        if (tours_.isEmpty()) {
            return playWhenLastDiscard(_info);
        }
        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        boolean joueurConfianceRamasseur_ = confidentPlayers.containsObj(ramasseurVirtuel_);
        boolean joueurConfianceRamasseurProbaPli_ = joueurConfianceRamasseur_ &&
                joueurConfianceRamasseurProbaPli(
                        notConfidentPlayersNotPlay,
                        couleurDemandee_,
                        cartesPossibles_,
                        carteForte_);
        /* C'est au moins le deuxieme tour */
        TrickTarot dernierPli_;
        dernierPli_ = tours_.last();
        Bytes dernieresDefausses_;
        dernieresDefausses_ = dernierPli_.joueursDefausses(nombreDeJoueurs_);
        Bytes joueursSusceptiblesDeCouper_;
        joueursSusceptiblesDeCouper_ = GameTarotTrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles_,couleurDemandee_,joueursNonJoue_);
        boolean maitreJeu_ = _info.isMaitreJeu();
        if (!joueursSusceptiblesDeCouper_.isEmpty()) {
            return processWhenPossibleTrumps(suites_, joueursSusceptiblesDeCouper_, maitreJeu_);
        }
        if (joueurConfianceRamasseurProbaPli_) {
            return repartitionCouleDem_
                    .premiereCarte();
        }
        /* Si la coupe semble improbable */
        EqList<HandTarot> cartesRelMaitres_;
        cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                couleurDemandee_, couleurDemandee_, cartesCertaines_,
                carteForte_);
        if (discardAtSecondRound(tours_, dernieresDefausses_)) {
            if (canLeadTrick(maitreJeu_,cartesRelMaitres_)) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
            if (!couleursAppelees_.containsObj(couleurDemandee_)) {
                if (!cartesMaitresses_.getVal(couleurDemandee_)
                        .estVide()) {
                    if (suites_.size() == 1
                            || !suites_.get(1).premiereCarte()
                            .isCharacter()) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    if (aucunePriseMainPossibleParFigure(
                            cartesPossibles_,couleurDemandee_,notConfidentPlayersNotPlay)) {
                        return jeuFigureHauteDePlusFaibleSuite(suites_);
                    }
                    return repartitionCouleDem_
                            .premiereCarte();
                }
                if (aucunePriseMainPossibleParFigure(
                        cartesPossibles_,couleurDemandee_,notConfidentPlayersNotPlay)) {
                    return repartitionCouleDem_
                            .premiereCarte();
                }
                return carteLaPlusPetite(suites_);
            }
            /* La couleur demandee est la couleur appelee */
            if (cartesMaitresses_.getVal(couleurDemandee_)
                    .estVide()) {
                return jeuFigureHauteDePlusFaibleSuite(suites_);
            }
            return repartitionCouleDem_.premiereCarte();
        }
            /*Le pli d'avant n'est pas defausse ou c'est au moins
            le troisieme tour*/
        return tryLeadTrick(cartesMaitresses_, couleurDemandee_, repartitionCouleDem_, suites_, cartesRelMaitres_);
    }

    private CardTarot processWhenPossibleTrumps(EqList<HandTarot> _suites, Bytes _joueursSusceptiblesDeCouper, boolean _maitreJeu) {
        if (!GameTarotTeamsRelation.intersectionJoueurs(notConfidentPlayers, _joueursSusceptiblesDeCouper).isEmpty()) {
            return carteLaPlusPetite(_suites);
        }
        if (_maitreJeu) {
            return carteLaPlusPetite(_suites);
        }
        return jeuFigureHauteDePlusFaibleSuite(_suites);
    }

    CardTarot followNormalSuitPartners(TarotInfoPliEnCours _info) {
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        boolean maitreJeu_ = _info.isMaitreJeu();
        EqList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        if (maitreJeu_) {
            if (suites_.size() == 1) {
                return repartitionCouleDem_.premiereCarte();
            }
            return weakestCard(suites_);
        }
        /* couleur demandee non atout */
        if (repartitionCouleDem_.premiereCarte().isCharacter()) {
            return repartitionCouleDem_.premiereCarte();
        }
        return weakestCard(suites_);
    }

    CardTarot followNormalSuitFoe(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
        EqList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        return carteLaPlusPetite(suites_);
    }

    CardTarot playWhenLastDiscard(TarotInfoPliEnCours _info) {
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        boolean maitreJeu_ = _info.isMaitreJeu();
        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        boolean joueurConfianceRamasseur_ = confidentPlayers.containsObj(ramasseurVirtuel_);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        boolean joueurConfianceRamasseurProbaPli_ = joueurConfianceRamasseur_ &&
                joueurConfianceRamasseurProbaPli(
                        notConfidentPlayersNotPlay,
                        couleurDemandee_,
                        cartesPossibles_,
                        carteForte_);
        HandTarot repartitionCouleDem_ = repartitionJouables_.getVal(couleurDemandee_);
        EqList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                couleurDemandee_, couleurDemandee_, cartesCertaines_,
                carteForte_);
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last()
                    .premiereCarte();
        }
        if (!couleursAppelees_.containsObj(couleurDemandee_)) {
            if (!cartesMaitresses_.getVal(couleurDemandee_)
                    .estVide()) {
                if (suites_.size() == 1
                        || !suites_.get(1).premiereCarte()
                        .isCharacter()) {
                    return repartitionCouleDem_
                            .premiereCarte();
                }
                if (aucunePriseMainPossibleParFigure(
                        cartesPossibles_,couleurDemandee_,notConfidentPlayersNotPlay)) {
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
                if (carteForte_.isCharacter()) {
                    if (!joueurConfianceRamasseurProbaPli_) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
                return repartitionCouleDem_
                        .premiereCarte();
            }
            /* Le joueur n'a aucune cartes maitresses */
            if (aucunePriseMainPossibleParFigure(
                    cartesPossibles_,couleurDemandee_,notConfidentPlayersNotPlay)) {
                return repartitionCouleDem_
                        .premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        /* La couleur demandee est la couleur appelee */
        if (cartesMaitresses_.getVal(couleurDemandee_)
                .estVide()) {
            return jeuFigureHauteDePlusFaibleSuite(suites_);
        }
        return repartitionCouleDem_.premiereCarte();
    }

    CardTarot followNormalSuit(TarotInfoPliEnCours _info) {
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), nombreDeJoueurs_);
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        if (_info.getCartesJouables().couleurs().getVal(couleurDemandee_).premiereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            /* Si le joueur ne peut pas prendre la main */
            HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
            EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
            EqList<HandTarot> suites_ = repartitionCouleDem_
                    .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
            byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
            CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
            EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
            boolean joueurConfianceRamasseur_ = confidentPlayers.containsObj(ramasseurVirtuel_);
            if (!repartitionCouleDem_.premiereCarte().isCharacter()) {
                /*Si le joueur
                ne possede pas
                de figure*/
                return carteLaPlusPetite(suites_);
            }
            /* Le joueur possede au moins une figure */
            CustList<TrickTarot> tours_ = GameTarotCommonPlaying.tours(couleurDemandee_, plisFaits_);
            if (tours_.isEmpty()) {
                /*
            Si cette couleur est entamee pour
            la premiere fois
            */
                if (joueurConfianceRamasseur_) {
                    if(aucunePriseMainPossibleCouleur(
                            cartesPossibles_,couleurDemandee_,
                            carteForte_,notConfidentPlayersNotPlay)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                }
                return carteLaPlusPetite(suites_);
            }
            if (joueurConfianceRamasseur_) {
                if (carteForte_.couleur() == Suit.TRUMP) {
                    /*
                    L'espoir fait
                    vivre
                    */
                    return repartitionCouleDem_
                            .premiereCarte();
                }
            }
            return carteLaPlusPetite(suites_);
        }
        /* Maintenant on sait le joueur peut prendre la main */
        if (!_info.getCartesJouables().couleurs().getVal(couleurDemandee_).premiereCarte().isCharacter()) {
            HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
            EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
            EqList<HandTarot> suites_ = repartitionCouleDem_
                    .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
            CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
            EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
            EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
            Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
            EqList<HandTarot> cartesRelMaitres_;
            cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                    suites_, cartesPossibles_, joueursNonJoue_,
                    couleurDemandee_, couleurDemandee_, cartesCertaines_,
                    carteForte_);
            boolean maitreJeu_ = _info.isMaitreJeu();
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            Bytes joueursNonConfiance_ = teamsRelation.joueursNonConfiance(_info.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
            Bytes joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_,joueursNonJoue_);
            if (aucunePriseMainPossibleParFigure(
                    cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
                if (!cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
            }
            CustList<TrickTarot> tours_ = GameTarotCommonPlaying.tours(couleurDemandee_, plisFaits_);
            if (tours_.isEmpty()) {
                return repartitionCouleDem_.premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        return CardTarot.WHITE;
    }
    static CardTarot tryLeadTrick(EnumMap<Suit, HandTarot> _cartesMaitresses, Suit _couleurDemandee, HandTarot _repartitionCouleDem, EqList<HandTarot> _suites, EqList<HandTarot> _cartesRelMaitres) {
        if (!_cartesMaitresses.getVal(_couleurDemandee)
                .estVide()) {
            return _repartitionCouleDem.premiereCarte();
        }
        if (!_cartesRelMaitres.isEmpty()) {
            return _cartesRelMaitres.last()
                    .premiereCarte();
        }
        return carteLaPlusPetite(_suites);
    }

    private static boolean canLeadTrick(boolean _maitreJeu, EqList<HandTarot> _cartesRelMaitres) {
        return !_cartesRelMaitres.isEmpty() && _maitreJeu;
    }

    CardTarot fournirAtoutClassique(TarotInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(_info);
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return followTrumpFoe(_info);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return followTrumpTeam(_info);
        }
        return followTrumpDefault(_info);

    }

    CardTarot followTrumpDefault(TarotInfoPliEnCours _info) {
        //incertitude du ramasseur a la couleur demandee (founiture obligatoire de la couleur demandee)
        boolean maitreJeu_ = _info.isMaitreJeu();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot repartitionCouleDem_;
        EqList<HandTarot> suites_;
        boolean contientExcuse_ = _info.isContientExcuse();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();

        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        EqList<HandTarot> cartesRelMaitres_;


        //fournir d'un atout a la demande d'atout
        suites_ = repartitionJouables_.getVal(Suit.TRUMP)
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(suites_,
                cartesPossibles_, notPlayed, Suit.TRUMP,
                couleurDemandee_, cartesCertaines_, carteForte_);
        repartitionCouleDem_ = repartitionJouables_.getVal(Suit.TRUMP);
        if (repartitionCouleDem_.premiereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        if (CardTarot.eq(doneTrickInfo.getProgressingTrick().premiereCarteNonExc(), CardTarot.petit())) {
            if (confidentPlayers.containsObj(ramasseurVirtuel_) && notConfidentPlayersNotPlay.isEmpty()) {
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            if (!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            if (peutRamasserDemandeAtoutNonMaitre(cartesPossibles_, cartesCertaines_,
                    _info.getCurrentPlayer(), notPlayed, played) && !notConfidentPlayersNotPlay.isEmpty()) {
                return repartitionCouleDem_.premiereCarte();
            }
            return suites_.last().premiereCarte();
        }
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        boolean carteMaitresse_ = leadTrumps(cartesPossibles_, carteForte_, notConfidentPlayersNotPlay);
        if (carteMaitresse_
                && confidentPlayers.containsObj(ramasseurVirtuel_)) {
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        int nombrePoints_ = getNbPointsInCurrentTrick();
        if (nombrePoints_ > 6) {
            if (!cartesRelMaitres_.isEmpty() && !notConfidentPlayersNotPlay.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return atoutLePlusPetit(suites_);
        }
        if (carteMaitresse_) {
            return atoutLePlusPetit(suites_);
        }
        return atoutLePlusPetit(suites_, contientExcuse_);
    }

    CardTarot followTrumpTeam(TarotInfoPliEnCours _info) {
        boolean maitreJeu_ = _info.isMaitreJeu();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot repartitionCouleDem_;
        EqList<HandTarot> suites_;
        boolean contientExcuse_ = _info.isContientExcuse();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        /* couleur demandee atout */
        //jeu obligatoire d'atout
        repartitionCouleDem_ = repartitionJouables_.getVal(Suit.TRUMP);
        suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        if (!repartitionCouleDem_.contient(CardTarot.petit())
                || maitreJeu_) {
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        Bytes joueursNonConfiance_ = teamsRelation.joueursNonConfiance(_info.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        if (GameTarotTrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles_)) {
            return atoutLePlusPetit(suites_);
        }
        return CardTarot.petit();
    }

    CardTarot followTrumpFoe(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        boolean contientExcuse_ = _info.isContientExcuse();
        /* La couleur demandee est atout */
        return atoutLePlusPetit(
                repartitionJouables_.getVal(Suit.TRUMP).eclaterEnCours(
                        repartitionCartesJouees_, couleurDemandee_), contientExcuse_);
    }

    static boolean leadTrumps(EnumMap<Suit, EqList<HandTarot>> _cartesPossibles, CardTarot _carteForte, Bytes _players) {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        for (byte joueur_ : _players) {
            if(_cartesPossibles.getVal(Suit.TRUMP).get(joueur_)
                    .estVide()) {
                continue;
            }
            if (_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(Suit.TRUMP) >= _carteForte.strength(Suit.TRUMP)) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }

    CardTarot coupeClassique(TarotInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(_info);
        //jouer un atout en coupe, surcoupe ou souscoupe
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return underTrumpFoe(_info);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return underTrumpPartners(_info);
        }
        CardTarot card_ = underTrump(_info);
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        //incertitude du ramasseur a la couleur demandee (jeu atout obligatoire de la couleur demandee)
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        if (GameTarotCommonPlaying.tours(couleurDemandee_, _info.getPlisFaits()).isEmpty()) {
            return trumpFirstRound(_info);

        }
        /* Deuxieme tour et plus */
        card_ = trumpNoAce(_info);
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        return defaultTrump(_info);
    }

    CardTarot defaultTrump(TarotInfoPliEnCours _info) {
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), nombreDeJoueurs_);
        EnumMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        /*
        Le joueur possede le Petit et c'est le duxieme tour a
        cette couleur ou plus
        */
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot atoutsCoupe_;
        atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        EqList<HandTarot> suites_;
        suites_ = atoutsCoupe_.eclaterEnCours(
                repartitionCartesJouees_, couleurDemandee_);
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, notPlayed,
                Suit.TRUMP, couleurDemandee_, cartesCertaines_,
                carteForte_);
        if (GameTarotTrickHypothesis.pasAtout(notConfidentPlayers, cartesPossibles_)) {
            return atoutLePlusPetit(suites_);
        }
        boolean maitreJeu_ = _info.isMaitreJeu();
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            if(notConfidentPlayersNotPlay.isEmpty()) {
                return atoutLePlusPetit(suites_);
            }
            return cartesRelMaitres_.last().premiereCarte();
        }
        boolean carteMaitresse_ = noOverTrump(couleurDemandee_, cartesPossibles_, cartesCertaines_, notConfidentPlayersNotPlay);
        if (carteMaitresse_) {
            return CardTarot.petit();
        }
        if (nombreDeJoueurs_ < 5) {
            CustList<TrickTarot> tours_ = GameTarotCommonPlaying.tours(couleurDemandee_, _info.getPlisFaits());
            if (tours_.size() == 1) {
                Bytes joueursCoupePreTour_ = tours_.first().joueursCoupes();
                if (GameTarotTeamsRelation.intersectionJoueurs(notConfidentPlayers, joueursCoupePreTour_).isEmpty()) {
                    return CardTarot.petit();
                }
            }
        }
        /* Le jeu s'effectue maintenant a 5 joueurs */
        return atoutLePlusPetit(suites_);
    }

    CardTarot trumpFirstRound(TarotInfoPliEnCours _info) {
    /*
    Le joueur peut surcouper si le pli est deja coupe ou
    couper avec n'importe quel atout
    */
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        byte virtLead_ = _info.getRamasseurVirtuel();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(virtLead_, nombreDeJoueurs_);
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        boolean maitreJeu_ = _info.isMaitreJeu();
        boolean contientExcuse_ = _info.isContientExcuse();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot atoutsCoupe_;
        atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        EqList<HandTarot> suites_;
        suites_ = atoutsCoupe_.eclaterEnCours(
                repartitionCartesJouees_, couleurDemandee_);
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                Suit.TRUMP, couleurDemandee_, cartesCertaines_,
                carteForte_);
        Bytes joueursNonConfiance_ = teamsRelation.joueursNonConfiance(_info.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Bytes joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_, joueursNonJoue_);
        if (!atoutsCoupe_.contient(CardTarot.petit())) {
        /*
        Si
        le
        joueur
        ne
        peut
        pas
        couper
        avec
        le
        Petit
        */
            if (!contientExcuse_) {
                if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
                return atoutLePlusPetit(suites_,
                        false);
            }
            /* Maintenant le joueur possede l'Excuse */
            CardTarot carteHautePasAtout_;
            carteHautePasAtout_ = doneTrickInfo.getProgressingTrick().getCartes().couleurs()
                    .getVal(couleurDemandee_).premiereCarte();
            if (carteHautePasAtout_.isCharacter()) {
            /*
            S'il
            existe
            une
            figure
            de la
            couleur
            demandee
            */
                if (carteForte_.couleur() == Suit.TRUMP) {
                    if (joueursNonConfiance_.contains(virtLead_)) {
                        return atoutLePlusPetit(suites_);
                    }
                    return CardTarot.EXCUSE;
                }
                boolean joueurConfianceRamasseurProbaPli_ = !joueursNonConfiance_.contains(virtLead_) &&
                        joueurConfianceRamasseurProbaPli(
                                notConfidentPlayersNotPlay,
                                couleurDemandee_,
                                cartesPossibles_,
                                carteForte_);
                if (joueurConfianceRamasseurProbaPli_) {
                    return CardTarot.EXCUSE;
                }
                return atoutLePlusPetit(suites_);
            }
            if (doneTrickInfo.getProgressingTrick().contient(CardTarot.TRUMP_1)) {
                if (joueursNonConfiance_.contains(virtLead_)) {
                    return atoutLePlusPetit(suites_);
                }
                return CardTarot.EXCUSE;
            }
            if (joueursNonConfianceNonJoue_
                    .isEmpty()) {
                return CardTarot.excuse();
            }
            return atoutLePlusPetit(suites_);
        }
        HandTarot atoutsCoupeSansPetit_ = new HandTarot();
        atoutsCoupeSansPetit_.ajouterCartes(atoutsCoupe_);
        atoutsCoupeSansPetit_.removeCardIfPresent(CardTarot.TRUMP_1);
        EqList<HandTarot> suitesSansPetit_;
        suitesSansPetit_ = atoutsCoupeSansPetit_.eclaterEnCours(
                repartitionCartesJouees_, couleurDemandee_);
        EqList<HandTarot> cartesRelMaitresSansPetit_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suitesSansPetit_, cartesPossibles_, joueursNonJoue_,
                Suit.TRUMP, couleurDemandee_, cartesCertaines_,
                carteForte_);
        /* Le joueur peut couper avec le Petit */
        if (canLeadTrick(maitreJeu_, cartesRelMaitresSansPetit_)) {
            return cartesRelMaitresSansPetit_.
                    last().premiereCarte();
        }
        if (GameTarotTrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles_)) {
            return atoutLePlusPetit(suites_);
        }
        boolean carteMaitresse_ = noOverTrump(couleurDemandee_, cartesPossibles_, cartesCertaines_, joueursNonConfianceNonJoue_);
        if (carteMaitresse_) {
            return CardTarot.petit();
        }
        EnumList<Suit> coupesFranches_ = _info.getCoupesFranches();
        if (coupesFranches_.size() == 1) {
            return CardTarot.petit();
        }
        carteMaitresse_ = true;
        /* Il existe au moins deux coupes franches */
        for (Suit coupe_ : coupesFranches_) {
            if (coupe_ != couleurDemandee_) {
                if (GameTarotCommonPlaying.tours(coupe_, plisFaits_).isEmpty()) {
                    carteMaitresse_ = false;
                }
            }
        }
        if (carteMaitresse_) {
            return CardTarot.petit();
        }
        if (!discarded.estVide()) {
            EnumList<Suit> coupesNonJoues_;
            EnumList<Suit> couleursExclues_ = new EnumList<Suit>();
            couleursExclues_.add(couleurDemandee_);
            coupesNonJoues_ = GameTarotCommon.complementaireCouleurs(coupesFranches_, couleursExclues_);
            coupesNonJoues_ = GameTarotCommonPlaying.couleursNonEntamees(plisFaits_, coupesNonJoues_);
            int nbCartesEcarteesCouleurDemandee_ = discarded.couleur(couleurDemandee_).total();
            coupesNonJoues_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(
                    discarded,
                    coupesNonJoues_, nbCartesEcarteesCouleurDemandee_ - 1);
            if (!coupesNonJoues_.isEmpty()) {
                return atoutLePlusPetit(suites_);
            }
        }
        return CardTarot.petit();
    }

    private boolean noOverTrump(Suit _couleurDemandee, EnumMap<Suit, EqList<HandTarot>> _cartesPossibles, EnumMap<Suit, EqList<HandTarot>> _cartesCertaines, Bytes _joueursNonConfianceNonJoue) {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if (!GameTarotTrickHypothesis.nePeutCouper(_couleurDemandee, joueur_, _cartesPossibles, _cartesCertaines)) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }

    CardTarot trumpNoAce(TarotInfoPliEnCours _info) {
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), nombreDeJoueurs_);
        EnumMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        HandTarot atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        if (!atoutsCoupe_.contient(CardTarot.petit())) {
            Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
            boolean maitreJeu_ = _info.isMaitreJeu();
            boolean contientExcuse_ = _info.isContientExcuse();
            EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
            EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
            EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
            EqList<HandTarot> suites_;
            suites_ = atoutsCoupe_.eclaterEnCours(
                    repartitionCartesJouees_, couleurDemandee_);
            Bytes joueursNonJoue_ = _info.getJoueursNonJoue();

            EqList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                    suites_, cartesPossibles_, joueursNonJoue_,
                    Suit.TRUMP, couleurDemandee_, cartesCertaines_,
                    carteForte_);
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            CardTarot first_ = atoutsCoupe_.premiereCarte();
            boolean carteMaitresse_ = noOverTrump(couleurDemandee_, cartesPossibles_, cartesCertaines_, notConfidentPlayersNotPlay);
            if (!carteMaitresse_) {
                int nombrePoints_ = getNbPointsInCurrentTrick();
                if (nombrePoints_ > 7) {
                    if (!cartesRelMaitres_.isEmpty()) {
                        return cartesRelMaitres_.last()
                                .premiereCarte();
                    }
                    return first_;
                }
                /*
                Moins de 8 points (les points sont doubles pour
                eviter les 1/2 points)
                */
                return suites_.last().premiereCarte();
                // != CarteTarot.petit()
            }
            /*
            Il n'est pas probable qu'un joueur de non confiance
            n'ayant pas joue coupe le pli
            */
            byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
            if (carteForte_.couleur() == Suit.TRUMP) {
                /*
            Si le pli est deja
            coupe
            */
                if (confidentPlayers.containsObj(ramasseurVirtuel_)) {
                    return atoutLePlusPetit(suites_,
                            contientExcuse_);
                }
                return atoutLePlusPetit(suites_);
            }
            CardTarot carteHautePasAtout_;
            carteHautePasAtout_ = doneTrickInfo.getProgressingTrick().getCartes().couleurs()
                    .getVal(couleurDemandee_).premiereCarte();
            if (!carteHautePasAtout_.isCharacter()) {
                for (byte joueur_ : notConfidentPlayersNotPlay) {
                    if(GameTarotTrickHypothesis.nePeutAvoirFigures(cartesPossibles_, joueur_, couleurDemandee_)) {
                        continue;
                    }
                    carteMaitresse_ = false;
                    break;
                }
                if (carteMaitresse_) {
                    return atoutLePlusPetit(suites_,
                            contientExcuse_);
                }
            } else if (confidentPlayers.containsObj(ramasseurVirtuel_)) {
                for (byte joueur_ : notConfidentPlayersNotPlay) {
                    if(cartesPossibles_
                            .getVal(couleurDemandee_)
                            .get(joueur_).estVide()) {
                        continue;
                    }
                    if (cartesPossibles_.getVal(couleurDemandee_).get(joueur_).premiereCarte().strength(couleurDemandee_) >= carteForte_.strength(couleurDemandee_)) {
                        carteMaitresse_ = false;
                    }
                }
                if (carteMaitresse_) {
                    return atoutLePlusPetit(suites_,
                            contientExcuse_);
                }
            }
            return atoutLePlusPetit(suites_);
        }
        return CardTarot.WHITE;
    }

    private int getNbPointsInCurrentTrick() {
        int nombrePoints_ = 0;
        for (CardTarot carte_ : doneTrickInfo.getProgressingTrick()) {
            if (carte_ != CardTarot.EXCUSE) {
                nombrePoints_ += carte_.points();
            }
        }
        return nombrePoints_;
    }

    CardTarot underTrumpFoe(TarotInfoPliEnCours _info) {
        boolean contientExcuse_ = _info.isContientExcuse();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        return atoutLePlusPetit(
                repartitionJouables_.getVal(Suit.TRUMP).eclaterEnCours(
                        repartitionCartesJouees_, couleurDemandee_), contientExcuse_);
    }

    CardTarot underTrumpPartners(TarotInfoPliEnCours _info) {
        boolean contientExcuse_ = _info.isContientExcuse();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        /* couleur demandee atout */
        //jeu obligatoire d'atout
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        HandTarot atoutsCoupe_;
        atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        EqList<HandTarot> suites_;
        suites_ = atoutsCoupe_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        boolean maitreJeu_ = _info.isMaitreJeu();
        if (!atoutsCoupe_.contient(CardTarot.petit())
                || maitreJeu_) {
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        boolean carteMaitresse_ = true;
        Bytes joueursNonConfiance_ = teamsRelation.joueursNonConfiance(_info.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        for (byte joueur_ : joueursNonConfiance_) {
            if (!cartesPossibles_.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                carteMaitresse_ = false;
            }
        }
        if (carteMaitresse_) {
            return atoutLePlusPetit(suites_);
        }
        return CardTarot.petit();
    }

    CardTarot underTrump(TarotInfoPliEnCours _info) {
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), nombreDeJoueurs_);
        EnumMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        HandTarot atoutsCoupe_;
        atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        if (atoutsCoupe_.premiereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            /*
                Si le
                joueur ne
                peut pas
                surcouper
                */
            EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
            EqList<HandTarot> suites_;
            suites_ = atoutsCoupe_.eclaterEnCours(
                    repartitionCartesJouees_, couleurDemandee_);
            boolean contientExcuse_ = _info.isContientExcuse();
            if (!atoutsCoupe_.contient(CardTarot.petit())) {
                /*
            Si
            le
            joueur
            n
            'a
            pas
            le
            Petit
            */
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
            if (confidentPlayers.containsObj(ramasseurVirtuel_)) {
                return atoutsCoupe_.derniereCarte();
            }
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        return CardTarot.WHITE;
    }
    CardTarot defausseCouleurOrdinaireClassique(TarotInfoPliEnCours _info) {
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(_info);
        //defausse sur une couleur ordinaire
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return discardOnFoe(_info);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return discardTeamSuit(_info);
        }
        return defaultDiscardSuit(_info);

    }

    CardTarot defaultDiscardSuit(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = _info.getSuitesTouteCouleur();
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
        //incertitude du ramasseur a la couleur demandee (defausse sur la couleur demandee ordinaire)
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(suitesTouteCouleur_,
                    couleurs_, currentHand,
                    repartitionCartesJouees_);
        }
        return jeuPetiteCarteCouleurFigure(suitesTouteCouleur_,
                couleursNonVides_, currentHand, repartitionCartesJouees_);
    }

    CardTarot discardTeamSuit(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = _info.getSuitesTouteCouleur();
        EnumList<Suit> couleursStrictesMaitresses_ = GameTarotCommonPlaying.strictCouleursMaitres(
                suitesTouteCouleur_, repartitionCartesJouees_,
                cartesPossibles_, _info.getCurrentPlayer());
        return defausseCouleurDemandeeSurPartenaire(
                suitesTouteCouleur_, repartitionCartesJouees_,
                currentHand, cartesMaitresses_,
                couleursStrictesMaitresses_, couleurDemandee_);
    }

    CardTarot playIfPartnersWin(TarotInfoPliEnCours _info) {
        HandTarot cartesJouables_ = _info.getCartesJouables();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        Bytes equipeNumero_ = new Bytes();
        equipeNumero_.addAllElts(confidentPlayers);
        equipeNumero_.add(_info.getCurrentPlayer());
        if (GameTarotTeamsRelation.contientJoueurs(equipeNumero_,GameTarotCommonPlaying.ramasseurs(plisFaits_))) {
            HandTarot noExc_ = new HandTarot();
            noExc_.ajouterCartes(cartesJouables_);
            noExc_.removeCardIfPresent(CardTarot.EXCUSE);
            return noExc_.premiereCarte();
        }
        return CardTarot.excuse();
    }

    CardTarot defausseAtoutClassique(TarotInfoPliEnCours _info) {
        //defausse sur l'atout
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(_info);
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return discardOnFoe(_info);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return discardOnTeamTrump(_info);
        }
        return defaultDiscard(_info);
    }

    CardTarot defaultDiscard(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = _info.getSuitesTouteCouleur();
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
        //incertitude du ramasseur a la couleur demandee (defausse sur l'atout)
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(suitesTouteCouleur_,
                    couleurs_, currentHand, repartitionCartesJouees_);
        }
        return jeuPetiteCarteCouleurFigure(suitesTouteCouleur_,
                couleursNonVides_, currentHand, repartitionCartesJouees_);
    }

    CardTarot discardOnTeamTrump(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = _info.getSuitesTouteCouleur();
        EnumList<Suit> couleursStrictesMaitresses_ = GameTarotCommonPlaying.strictCouleursMaitres(
                suitesTouteCouleur_, repartitionCartesJouees_,
                cartesPossibles_, _info.getCurrentPlayer());
        return defausseAtoutSurPartenaire(suitesTouteCouleur_,
                repartitionCartesJouees_, currentHand,
                cartesMaitresses_, couleursStrictesMaitresses_);
    }

    CardTarot discardOnFoe(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = _info.getSuitesTouteCouleur();
        return discardFoe(suitesTouteCouleur_,repartitionCartesJouees_, currentHand);
    }
    TarotInfoPliEnCours initInformations() {
        return common.initInformations(currentHand,playableCards, confidentPlayers,notConfidentPlayers);
    }

    CardTarot tryPlayExcuseOrLead(TarotInfoPliEnCours _info, HandTarot _currentHand) {
        if (hasExcPlusCard(_currentHand) && GameTarotTrickHypothesis.equipeQuiVaFairePli(_info) == PossibleTrickWinner.TEAM) {
            HandTarot cartesJouables_ = _info.getCartesJouables();
            CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
            Bytes equipeNumero_ = new Bytes();
            equipeNumero_.addAllElts(confidentPlayers);
            equipeNumero_.add(_info.getCurrentPlayer());
            if (GameTarotTeamsRelation.contientJoueurs(equipeNumero_,GameTarotCommonPlaying.ramasseurs(plisFaits_))) {
                HandTarot noExc_ = new HandTarot();
                noExc_.ajouterCartes(cartesJouables_);
                noExc_.removeCardIfPresent(CardTarot.EXCUSE);
                return noExc_.premiereCarte();
            }
        }
        return tryPlayExcuse(_info.isMaitreJeu(),_currentHand);
    }
    static CardTarot tryPlayExcuse(boolean _leadGame, HandTarot _currentHand) {
        boolean contExc_ = _currentHand.contient(CardTarot.EXCUSE);
        if (_leadGame && contExc_) {
            return CardTarot.EXCUSE;
        }
        if (hasExcPlusCard(_currentHand)) {
            return CardTarot.EXCUSE;
        }
        return CardTarot.WHITE;
    }
    /**
     Est vrai si et seulement si le partenaire du joueur qui va jouer domine
     l'adversaire n'ayant pas joue (uniquement si le partenaire est maitre
     temporairement du pli)
     */
    static boolean joueurConfianceRamasseurProbaPli(
            Bytes _joueursNonConfianceNonJoue, Suit _couleurDemandee,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, CardTarot _carteForte) {
        boolean bat_ = true;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if(_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide()) {
                if(!_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                    bat_ = false;
                }
                continue;
            }
            if (_carteForte.strength(_couleurDemandee) <= _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().strength(_couleurDemandee)) {
                bat_ = false;
            }
        }
        return bat_;
    }

    static boolean aucunePriseMainPossibleParFigure(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Bytes _joueursNonConfianceNonJoue) {
        boolean carteMaitresse_ = true;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if (!GameTarotTrickHypothesis.defausse(_cartesPossibles, joueur_,
                    _couleurDemandee)
                    && !GameTarotTrickHypothesis.nePeutAvoirFigures(
                    _cartesPossibles, joueur_,
                    _couleurDemandee)) {
                carteMaitresse_ = false;
                break;
            }
        }
        return carteMaitresse_;
    }
    static boolean aucunePriseMainPossibleCouleur(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            CardTarot _carteForte,
            Bytes _joueursNonConfianceNonJoue) {
        byte max_ = 0;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if (_cartesPossibles.getVal(_couleurDemandee)
                    .get(joueur_).estVide()) {
                if (!_cartesPossibles.getVal(Suit.TRUMP)
                        .get(joueur_).estVide()) {
                    return false;
                }
                continue;
            }
            max_ = (byte) Math.max(
                    _cartesPossibles.getVal(_couleurDemandee)
                            .get(joueur_).premiereCarte()
                            .strength(_couleurDemandee), max_);
        }
        return _carteForte.strength(_couleurDemandee) > max_;
    }
    static boolean peutSauverFigureAppele(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            HandTarot _cartesChien,
            Bytes _joueursNonConfianceNonJoue,
            boolean _figure) {
        if(!_figure) {
            return false;
        }
        boolean carteMaitresse_ = true;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            boolean local_ = true;
            if (!_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide()
                    && !_cartesChien.estVide()) {
                CardTarot max_ = _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte();
                if (max_.isCharacter()) {
                    if (_cartesChien.premiereCarte().strength(_couleurDemandee) <= max_.strength(_couleurDemandee)) {
                        local_ = false;
                    }
                }
            } else if (!GameTarotTrickHypothesis.defausse(_cartesPossibles,joueur_,_couleurDemandee)) {
                local_ = false;
            }
            if (!local_) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }
    static boolean premiereSuitePlusLongueQueTotalCouleurDemandee(
            EqList<HandTarot> _suites,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Bytes _joueurs) {
        if(!_suites.first().premiereCarte().isCharacter()) {
            return false;
        }
        //il existe une suite de la couleur demandee ordinaire avec une figure
        byte max_ = 0;
        for (byte joueur_ : _joueurs) {
            max_ = (byte) Math.max(
                    _cartesPossibles
                            .getVal(_couleurDemandee)
                            .get(joueur_).total(),
                    max_);
        }
        return _suites.first().total() > max_;
    }
    /**
     Couleur demand&eacute;e atout: retourne vrai si et seulement si le joueur
     numero peut ramasser le pli en jouant son plus grand atout
     */
    static boolean peutRamasserDemandeAtoutNonMaitre(
            EnumMap<Suit, EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit, EqList<HandTarot>> _cartesCertaines, byte _numero,
            Bytes _joueursNonJoue, Bytes _joueursJoue) {
        boolean existe_ = false;
        EqList<HandTarot> atoutsCertains_ = _cartesCertaines.getVal(Suit.TRUMP);
        byte maxAtoutNumero_ = atoutsCertains_.get(_numero)
                .premiereCarte().strength(Suit.TRUMP);
        for (byte joueur_ : _joueursNonJoue) {
            if(atoutsCertains_.get(joueur_).estVide()) {
                continue;
            }
            if (atoutsCertains_.get(joueur_).premiereCarte().strength(Suit.TRUMP) > maxAtoutNumero_) {
                existe_ = true;
            }
        }
        if (existe_) {
            return false;
        }
        EqList<HandTarot> atoutsPossibles_ = _cartesPossibles.getVal(Suit.TRUMP);
        for (byte joueur_ : _joueursNonJoue) {
            if(atoutsPossibles_.get(joueur_).estVide()) {
                continue;
            }
            byte maxAtoutJoueurNonJoue_ = atoutsPossibles_.get(joueur_).premiereCarte()
                    .strength(Suit.TRUMP);
            boolean local_ = true;
            for (byte joueur2_ : _joueursJoue) {
                if (atoutsPossibles_.get(joueur2_).estVide()) {
                    continue;
                }
                if (maxAtoutJoueurNonJoue_ <= atoutsPossibles_.get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
                    local_ = false;
                }
            }
            if (local_) {
                existe_ = true;
            }
        }
        return !existe_;
    }


    static CardTarot sauveQuiPeutFigure(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EqList<HandTarot> _suites, EqList<HandTarot> _cartesRelMaitres,
            Bytes _joueursDeNonConfianceNonJoue,
            Suit _couleurDemandee) {
        boolean pasFigure_ = true;
        for (byte joueur_ : _joueursDeNonConfianceNonJoue) {
            if (!_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide()) {
                if (_cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().isCharacter()) {
                    pasFigure_ = false;
                }
            }
        }
        if (!_cartesRelMaitres.isEmpty()) {
            if (_cartesRelMaitres.size() == 1) {
                return _suites.first().premiereCarte();
            }
            //cartesRelMaitres.size() > 1 && cartesRelMaitres.get(1).premiereCarte().estUneFigure()
            return _cartesRelMaitres.last().premiereCarte();
        }
        if (pasFigure_) {
            return jeuFigureHauteDePlusFaibleSuite(_suites);
        }
        return weakestCard(_suites);
    }

    /**
     Renvoie la plus grande carte basse (n'etant pas une figure) a jouer dans
     la suite la plus faible de la couleur
     */
    static CardTarot carteLaPlusPetite(EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesFigures_ = getCharacterSeq(_suites);
        EqList<HandTarot> suitesCartesBasses_ = getLowCardSeq(_suites);
        if (!suitesCartesBasses_.isEmpty()) {
            return suitesCartesBasses_.last().premiereCarte();
        }
        return suitesFigures_.last().derniereCarte();
    }

    /**
     Renvoie la plus grande carte basse (n'etant pas une figure) a jouer dans
     la suite la plus faible de la couleur
     */
    static CardTarot weakestCard(EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesFigures_ = getCharacterSeq(_suites);
        EqList<HandTarot> suitesCartesBasses_ = getLowCardSeq(_suites);
        if (!suitesCartesBasses_.isEmpty()) {
            return suitesCartesBasses_.last().premiereCarte();
        }
        return suitesFigures_.last().premiereCarte();
    }
    /**
     Renvoie la figure la plus grande dans la suite de figure la moins
     haute(en valeur)
     */
    static CardTarot jeuFigureHauteDePlusFaibleSuite(
            EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesFigures_ = getCharacterSeq(_suites);
        EqList<HandTarot> suitesCartesBasses_ = getLowCardSeq(_suites);
        if (!suitesFigures_.isEmpty()) {
            return suitesFigures_.last().premiereCarte();
        }
        return suitesCartesBasses_.last().premiereCarte();

    }

    static EqList<HandTarot> getCharacterSeq(EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesFigures_ = new EqList<HandTarot>();
        for(HandTarot m: _suites) {
            int nbFigures_ = m.nombreDeFigures();
            if(nbFigures_ == m.total()) {
                suitesFigures_.add(m);
                continue;
            }
            if(nbFigures_ == 0) {
                continue;
            }
            //nbFigures > 0 && nbFigures < m.total()
            //==> m.total() >= 2 && !suiteFigures.isEmpty() && !suiteCartesBasses.isEmpty()
            HandTarot suiteFigures_ = new HandTarot();
            HandTarot suiteCartesBasses_ = new HandTarot();
            for(CardTarot c: m) {
                if(c.isCharacter()) {
                    suiteFigures_.ajouter(c);
                    continue;
                }
                suiteCartesBasses_.ajouter(c);
            }
            suitesFigures_.add(suiteFigures_);
        }
        return suitesFigures_;
    }

    static EqList<HandTarot> getLowCardSeq(EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesCartesBasses_ = new EqList<HandTarot>();
        for(HandTarot m: _suites) {
            int nbFigures_ = m.nombreDeFigures();
            if(nbFigures_ == m.total()) {
                continue;
            }
            if(nbFigures_ == 0) {
                suitesCartesBasses_.add(m);
                continue;
            }
            //nbFigures > 0 && nbFigures < m.total()
            //==> m.total() >= 2 && !suiteFigures.isEmpty() && !suiteCartesBasses.isEmpty()
            HandTarot suiteFigures_ = new HandTarot();
            HandTarot suiteCartesBasses_ = new HandTarot();
            for(CardTarot c: m) {
                if(c.isCharacter()) {
                    suiteFigures_.ajouter(c);
                    continue;
                }
                suiteCartesBasses_.ajouter(c);
            }
            suitesCartesBasses_.add(suiteCartesBasses_);
        }
        return suitesCartesBasses_;
    }
    /**Methode retournant un atout different du Petit sauf si ce dernier est sec.*/
    static CardTarot atoutLePlusPetit(EqList<HandTarot> _suites) {
        if (_suites.size() == 1) {
            return _suites.first().premiereCarte();
        }
        if (!_suites.last().contient(CardTarot.petit())) {
            return _suites.last().premiereCarte();
        }
        /* Le joueur a le Petit maintenant */
        //Petit sec ==> suites.size() == 1 && suites.last().total() == 1
        //Or nombre d'atout >= 2, donc suites.last().total() == 1 ==> suites.size() >= 2
        if (_suites.last().total() == 1) {
            //Petit seul dans sa suite
            return _suites.get(_suites.size() - 2).premiereCarte();
        }
        return _suites.last().premiereCarte();
        //Petit accompagne d'un atout dans la meme suite
    }

    /**
     Retourne l'atout le plus grand dans la suite la plus faible si l'Excuse
     n'est pas possedee et le Petit n'est pas dans la main; ou l'Excuse si
     elle est possedee
     @throws RuntimeException nombre d'atout < 2 et !contientExcuse et suites.last().contient(CarteTarot.petit())
     (le Petit est sec)
     */
    static CardTarot atoutLePlusPetit(EqList<HandTarot> _suites,
                                              boolean _contientExcuse) {
        if (_contientExcuse) {
            return CardTarot.excuse();
        }
        /* Le joueur n'a pas l'Excuse */
        return atoutLePlusPetit(_suites);
    }

    static CardTarot discardFoe(EnumMap<Suit, EqList<HandTarot>> _suitesTouteCouleur, EnumMap<Suit, HandTarot> _repartitionCartesJouees, HandTarot _main) {
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
        EnumList<Suit> couleursSansFigure_ = GameTarotCommon.couleursSansFigures(_main, couleursNonVides_);
        if (!couleursSansFigure_.isEmpty()) {
            return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleursSansFigure_,
                    _main, _repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(_suitesTouteCouleur, couleursNonVides_,
                _main, _repartitionCartesJouees);
    }

    private static CardTarot jeuPetiteCarteCouleurFigure(
            EnumMap<Suit,EqList<HandTarot>> _suites, EnumList<Suit> _couleursNonVides,
            HandTarot _main,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursAvecLaPlusPetiteCarteBasse(_main, _couleursNonVides);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees),couleurs_);
        return carteLaPlusPetite(_suites.getVal(couleurs_.first()));
    }

    static CardTarot defausseAtoutSurPartenaire(
            EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        boolean carteMaitresse_ = _couleursStrictementMaitresses.size() == Suit.couleursOrdinaires().size();
        if (_repartitionCartesJouees.getVal(Suit.TRUMP).total() > 17) {
            EnumMap<Suit,HandTarot> repartition_=_main.couleurs();
            if (carteMaitresse_) {
                EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
                if (leadingCard(_cartesMaitresses, _couleursStrictementMaitresses, repartition_)) {
                    EnumList<Suit> couleursFigures_ = GameTarotCommon.couleursAvecFigures(_main, couleursNonVides_);
                    if (!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(
                                couleursFigures_, _main);
                    }
                }
            }
            /*
            Il peut exister une couleur pour se defausser non strictement
            maitresse
            */
            return discardOptimPartner(_suitesTouteCouleur, _repartitionCartesJouees, _main, _cartesMaitresses);
        }
        /*
        Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur
        demandee sont jouees
        */
        return discardCardPartner(_suitesTouteCouleur, _repartitionCartesJouees, _main, _cartesMaitresses, _couleursStrictementMaitresses, carteMaitresse_);
    }

    static CardTarot defausseCouleurDemandeeSurPartenaire(
            EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses, Suit _couleurDemandee) {
        EnumList<Suit> couleursAutreQueDemandee_ = new EnumList<Suit>();
        for(Suit c: Suit.couleursOrdinaires()) {
            if(c == _couleurDemandee) {
                continue;
            }
            couleursAutreQueDemandee_.add(c);
        }
        boolean carteMaitresse_ = _couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandee_);
        if (_repartitionCartesJouees.getVal(Suit.TRUMP).total() > 19
                && _repartitionCartesJouees.getVal(_couleurDemandee).total() > 12) {
            EnumMap<Suit,HandTarot> repartition_=_main.couleurs();
            if (carteMaitresse_) {
                EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
                if (leadingCard(_cartesMaitresses, _couleursStrictementMaitresses, repartition_)) {
                    EnumList<Suit> couleursFigures_ = GameTarotCommon.couleursAvecFigures(_main, couleursNonVides_);
                    if (!couleursFigures_.isEmpty()) {
                        couleursFigures_ = GameTarotCommon.couleursLesPlusLongues(_main,couleursFigures_);
                        return jeuGrandeCarteDefausseMaitre(
                                couleursFigures_, _main);
                    }
                }
            }
            /*
            Il peut exister une couleur pour se defausser non strictement
            maitresse
            */
            return discardOptimPartner(_suitesTouteCouleur, _repartitionCartesJouees, _main, _cartesMaitresses);
        }
        /*
        Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur
        demandee sont jouees
        */
        return discardCardPartner(_suitesTouteCouleur, _repartitionCartesJouees, _main, _cartesMaitresses, _couleursStrictementMaitresses, carteMaitresse_);
    }

    static CardTarot discardOptimPartner(EnumMap<Suit, EqList<HandTarot>> _suitesTouteCouleur, EnumMap<Suit, HandTarot> _repartitionCartesJouees, HandTarot _main, EnumMap<Suit, HandTarot> _cartesMaitresses) {
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
        EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursAvecCarteNonMaitresse(_main, HandTarot.reunion(_repartitionCartesJouees), Suit.couleursOrdinaires());
        if (!couleurs_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                    _cartesMaitresses, _main, couleurs_);
        }
        couleurs_ = GameTarotCommon.couleursAvecFigures(_main, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_main,couleurs_);
            return sauverFiguresDefausse(couleurs_, _main,
                    _repartitionCartesJouees);
        }
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_main,couleursNonVides_);
        return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleurs_,
                _main, _repartitionCartesJouees);
    }

    static CardTarot discardCardPartner(EnumMap<Suit, EqList<HandTarot>> _suitesTouteCouleur, EnumMap<Suit, HandTarot> _repartitionCartesJouees, HandTarot _main, EnumMap<Suit, HandTarot> _cartesMaitresses, EnumList<Suit> _couleursStrictementMaitresses, boolean _carteMaitresse) {
        EnumMap<Suit,HandTarot> repartition_=_main.couleurs();
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
        if (_carteMaitresse) {
            if (leadingCard(_cartesMaitresses, _couleursStrictementMaitresses, repartition_)) {
                EnumList<Suit> couleursFigures_ = GameTarotCommon.couleursAvecFigures(_main, couleursNonVides_);
                if (!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_,
                            _main);
                }
            }
        }
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursAvecFigures(_main, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_, _main,
                    _repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleursNonVides_,
                _main, _repartitionCartesJouees);
    }

    static boolean leadingCard(EnumMap<Suit, HandTarot> _cartesMaitresses, EnumList<Suit> _couleursStrictementMaitresses, EnumMap<Suit, HandTarot> _repartition) {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        for (Suit couleur_ : _couleursStrictementMaitresses) {
            if (_cartesMaitresses.getVal(couleur_).total() != _repartition.getVal(couleur_).total()
                    || _cartesMaitresses.getVal(couleur_).total() <= 1 && !_repartition.getVal(couleur_).estVide()) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }

    static CardTarot jeuGrandeCarteDefausseMaitre(
            EnumList<Suit> _couleursFiguresNonVide, HandTarot _main) {
        EnumList<Suit> couleursAvecFigures_ = GameTarotCommon.couleursAvecFigures(_main, _couleursFiguresNonVide);
        EnumList<Suit> couleurs_ = _couleursFiguresNonVide;
        if(!couleursAvecFigures_.isEmpty()) {
            couleurs_ = couleursAvecFigures_;
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_main, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(_main, couleurs_);
            couleurs_ = GameTarotCommon.couleursAvecLePlusGrandNbFigures(_main, couleurs_);
            return _main.couleur(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusGrandNbFigures(_main, couleurs_);
        return _main.couleur(couleurs_.first()).derniereCarte();
    }

    static CardTarot sauverFiguresDefausse(
            EnumList<Suit> _couleursFiguresNonVide, HandTarot _main,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleursAvecFigures_ = GameTarotCommon.couleursAvecFigures(_main, _couleursFiguresNonVide);
        EnumList<Suit> couleurs_ = _couleursFiguresNonVide;
        if(!couleursAvecFigures_.isEmpty()) {
            couleurs_ = couleursAvecFigures_;
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_main, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(_main, couleurs_);
            return _main.couleur(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(_main, couleurs_);
        return _main.couleur(couleurs_.first()).derniereCarte();
    }

    static CardTarot jeuPetiteDefausseMaitre(
            EnumMap<Suit,EqList<HandTarot>> _suites,
            EnumMap<Suit,HandTarot> _cartesMaitresses,
            HandTarot _main,
            EnumList<Suit> _couleursNonVides) {
        EnumList<Suit> couleursSansFigures_ = GameTarotCommon.couleursSansFigures(_main, _couleursNonVides);
        EnumList<Suit> couleurs_ = _couleursNonVides;
        if(!couleursSansFigures_.isEmpty()) {
            couleurs_ = couleursSansFigures_;
        }
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_cartesMaitresses),couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(_main, couleurs_);
        return carteLaPlusPetite(_suites.getVal(couleurs_.first()));
    }

    static CardTarot jouerPetiteCarteDefausse(
            EnumMap<Suit,EqList<HandTarot>> _suites, EnumList<Suit> _couleursNonVides,
            HandTarot _main,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleursSansFigures_ = GameTarotCommon.couleursSansFigures(_main, _couleursNonVides);
        EnumList<Suit> couleurs_ = _couleursNonVides;
        if(!couleursSansFigures_.isEmpty()) {
            couleurs_ = couleursSansFigures_;

        }
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees),couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(_main, couleurs_);
        return carteLaPlusPetite(_suites.getVal(couleurs_.first()));
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }
}
