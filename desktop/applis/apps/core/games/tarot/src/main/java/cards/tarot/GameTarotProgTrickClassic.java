package cards.tarot;

import cards.consts.PossibleTrickWinner;
import cards.consts.Role;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.*;
import code.util.core.NumberUtil;

public final class GameTarotProgTrickClassic {

    private final GameTarotTeamsRelation teamsRelation;

    private final GameTarotTrickInfo doneTrickInfo;
    private final HandTarot calledCards;

    private final HandTarot currentHand;
    private final GameTarotCommonPlaying common;
    private final Role currentStatus;
    private final Bytes confidentPlayers;
    private final Bytes notConfidentPlayers;
    private final Bytes played;
    private final Bytes notPlayed;
    private final Bytes notConfidentPlayersNotPlay;
    private final HandTarot playableCards;
    private final HandTarot discarded = new HandTarot();
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
        CardTarot card_ = tryPlayExcuseOrLead(info_);
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        IdMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
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
        if (currentStatus == Role.CALLED_PLAYER) {
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
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        CustList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        CustList<HandTarot> cartesRelMaitres_;
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

    private static CardTarot gearRelativeLeadingCards(CustList<HandTarot> _suites, CustList<HandTarot> _cartesRelMaitres) {
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
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
        CustList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        IdMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        CustList<HandTarot> cartesRelMaitres_;
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
        IdMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
        CustList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
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
        CustList<HandTarot> cartesRelMaitres_;
        cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                couleurDemandee_, couleurDemandee_, cartesCertaines_,
                carteForte_);
        if (!discardAtSecondRound(tours_, dernieresDefausses_)) {
            return tryLeadTrick(cartesMaitresses_, couleurDemandee_, repartitionCouleDem_, suites_, cartesRelMaitres_);
        }
        if (canLeadTrick(maitreJeu_,cartesRelMaitres_)) {
            return cartesRelMaitres_.last()
                    .premiereCarte();
        }
        CardTarot cardTarot_ = tryFollow(_info);
        if (cardTarot_ != CardTarot.WHITE) {
            return cardTarot_;
        }
        return repartitionCouleDem_
                .premiereCarte();
    }
    private CardTarot tryFollow(TarotInfoPliEnCours _info) {
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        IdMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
        CustList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        if (couleursAppelees_.containsObj(couleurDemandee_)) {/* La couleur demandee est la couleur appelee */
            if (cartesMaitresses_.getVal(couleurDemandee_)
                    .estVide()) {
                return jeuFigureHauteDePlusFaibleSuite(suites_);
            }
            return repartitionCouleDem_.premiereCarte();
        /*Le pli d'avant n'est pas defausse ou c'est au moins
            le troisieme tour*/
        }
        if (cartesMaitresses_.getVal(couleurDemandee_)
                .estVide()) {
            if (aucunePriseMainPossibleParFigure(
                    cartesPossibles_, couleurDemandee_, notConfidentPlayersNotPlay)) {
                return repartitionCouleDem_
                        .premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        if (suites_.size() == 1
                || !suites_.get(1).premiereCarte()
                .isCharacter()) {
            return repartitionCouleDem_
                    .premiereCarte();
        }
        if (aucunePriseMainPossibleParFigure(
                cartesPossibles_, couleurDemandee_, notConfidentPlayersNotPlay)) {
            return jeuFigureHauteDePlusFaibleSuite(suites_);
        }
        return CardTarot.WHITE;
    }


    private CardTarot processWhenPossibleTrumps(CustList<HandTarot> _suites, Bytes _joueursSusceptiblesDeCouper, boolean _maitreJeu) {
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
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        boolean maitreJeu_ = _info.isMaitreJeu();
        CustList<HandTarot> suites_ = repartitionCouleDem_
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
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
        CustList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        return carteLaPlusPetite(suites_);
    }

    CardTarot playWhenLastDiscard(TarotInfoPliEnCours _info) {
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        boolean maitreJeu_ = _info.isMaitreJeu();
        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        boolean joueurConfianceRamasseur_ = confidentPlayers.containsObj(ramasseurVirtuel_);
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        IdMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        boolean joueurConfianceRamasseurProbaPli_ = joueurConfianceRamasseur_ &&
                joueurConfianceRamasseurProbaPli(
                        notConfidentPlayersNotPlay,
                        couleurDemandee_,
                        cartesPossibles_,
                        carteForte_);
        HandTarot repartitionCouleDem_ = repartitionJouables_.getVal(couleurDemandee_);
        CustList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                couleurDemandee_, couleurDemandee_, cartesCertaines_,
                carteForte_);
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last()
                    .premiereCarte();
        }
        CardTarot cardTarot_ = tryFollow(_info);
        if (cardTarot_ != CardTarot.WHITE) {
            return cardTarot_;
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

    CardTarot followNormalSuit(TarotInfoPliEnCours _info) {
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(_info.getRamasseurVirtuel(), nombreDeJoueurs_);
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        if (_info.getCartesJouables().couleurs().getVal(couleurDemandee_).premiereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            return followNormalSuitCannotLead(_info, carteForte_, couleurDemandee_);
        }
        /* Maintenant on sait le joueur peut prendre la main */
        if (!_info.getCartesJouables().couleurs().getVal(couleurDemandee_).premiereCarte().isCharacter()) {
            HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(couleurDemandee_);
            IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
            CustList<HandTarot> suites_ = repartitionCouleDem_
                    .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
            CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
            IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
            IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
            Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
            CustList<HandTarot> cartesRelMaitres_;
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
                    cartesPossibles_, couleurDemandee_, joueursNonConfianceNonJoue_) && !cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            CustList<TrickTarot> tours_ = GameTarotCommonPlaying.tours(couleurDemandee_, plisFaits_);
            if (tours_.isEmpty()) {
                return repartitionCouleDem_.premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        return CardTarot.WHITE;
    }

    private CardTarot followNormalSuitCannotLead(TarotInfoPliEnCours _info, CardTarot _carteForte, Suit _couleurDemandee) {
        /* Si le joueur ne peut pas prendre la main */
        HandTarot repartitionCouleDem_ = _info.getCartesJouables().couleurs().getVal(_couleurDemandee);
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        CustList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, _couleurDemandee);
        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        boolean joueurConfianceRamasseur_ = confidentPlayers.containsObj(ramasseurVirtuel_);
        if (!repartitionCouleDem_.premiereCarte().isCharacter()) {
            /*Si le joueur
            ne possede pas
            de figure*/
            return carteLaPlusPetite(suites_);
        }
        /* Le joueur possede au moins une figure */
        CustList<TrickTarot> tours_ = GameTarotCommonPlaying.tours(_couleurDemandee, plisFaits_);
        if (tours_.isEmpty()) {
            /*
        Si cette couleur est entamee pour
        la premiere fois
        */
            if (joueurConfianceRamasseur_ && aucunePriseMainPossibleCouleur(
                    cartesPossibles_, _couleurDemandee,
                    _carteForte, notConfidentPlayersNotPlay)) {
                return repartitionCouleDem_
                        .premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        if (joueurConfianceRamasseur_ && _carteForte.getId().getCouleur() == Suit.TRUMP) {
                /*
                L'espoir fait
                vivre
                */
            return repartitionCouleDem_
                    .premiereCarte();
        }
        return carteLaPlusPetite(suites_);
    }

    static CardTarot tryLeadTrick(IdMap<Suit, HandTarot> _cartesMaitresses, Suit _couleurDemandee,
                                  HandTarot _repartitionCouleDem,
                                  CustList<HandTarot> _suites, CustList<HandTarot> _cartesRelMaitres) {
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

    private static boolean canLeadTrick(boolean _maitreJeu, CustList<HandTarot> _cartesRelMaitres) {
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
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        IdMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot repartitionCouleDem_;
        CustList<HandTarot> suites_;
        boolean contientExcuse_ = _info.isContientExcuse();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();

        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        CustList<HandTarot> cartesRelMaitres_;


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
            return followWhenTrumpAcePlayedFirst(_info, repartitionCouleDem_, suites_, cartesRelMaitres_);
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

    private CardTarot followWhenTrumpAcePlayedFirst(TarotInfoPliEnCours _info, HandTarot _repartitionCouleDem, CustList<HandTarot> _suites, CustList<HandTarot> _cartesRelMaitres) {
        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        boolean contientExcuse_ = _info.isContientExcuse();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        if (confidentPlayers.containsObj(ramasseurVirtuel_) && notConfidentPlayersNotPlay.isEmpty()) {
            return atoutLePlusPetit(_suites, contientExcuse_);
        }
        if (!_cartesRelMaitres.isEmpty()) {
            return _cartesRelMaitres.last().premiereCarte();
        }
        if (peutRamasserDemandeAtoutNonMaitre(cartesPossibles_, cartesCertaines_,
                _info.getCurrentPlayer(), notPlayed, played) && !notConfidentPlayersNotPlay.isEmpty()) {
            return _repartitionCouleDem.premiereCarte();
        }
        return _suites.last().premiereCarte();
    }

    CardTarot followTrumpTeam(TarotInfoPliEnCours _info) {
        boolean maitreJeu_ = _info.isMaitreJeu();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        IdMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot repartitionCouleDem_;
        CustList<HandTarot> suites_;
        boolean contientExcuse_ = _info.isContientExcuse();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
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
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        IdMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        boolean contientExcuse_ = _info.isContientExcuse();
        /* La couleur demandee est atout */
        return atoutLePlusPetit(
                repartitionJouables_.getVal(Suit.TRUMP).eclaterEnCours(
                        repartitionCartesJouees_, couleurDemandee_), contientExcuse_);
    }

    static boolean leadTrumps(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, CardTarot _carteForte, Bytes _players) {
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
        IdMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        /*
        Le joueur possede le Petit et c'est le duxieme tour a
        cette couleur ou plus
        */
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot atoutsCoupe_;
        atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        CustList<HandTarot> suites_;
        suites_ = atoutsCoupe_.eclaterEnCours(
                repartitionCartesJouees_, couleurDemandee_);
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        IdMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        boolean maitreJeu_ = _info.isMaitreJeu();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        CustList<HandTarot> suites_ = atoutsCoupe_.eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        Bytes joueursNonConfiance_ = teamsRelation.joueursNonConfiance(_info.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Bytes joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_, joueursNonJoue_);
        if (!atoutsCoupe_.contient(CardTarot.petit())) {
            return playOtherThanTrumpAce(_info, joueursNonConfiance_, joueursNonConfianceNonJoue_);
        }
        HandTarot atoutsCoupeSansPetit_ = new HandTarot();
        atoutsCoupeSansPetit_.ajouterCartes(atoutsCoupe_);
        atoutsCoupeSansPetit_.removeCardIfPresent(CardTarot.TRUMP_1);
        CustList<HandTarot> suitesSansPetit_ = atoutsCoupeSansPetit_.eclaterEnCours(
                repartitionCartesJouees_, couleurDemandee_);
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        CustList<HandTarot> cartesRelMaitresSansPetit_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        carteMaitresse_ = notPlayed(couleurDemandee_, plisFaits_, coupesFranches_);
        if (carteMaitresse_) {
            return CardTarot.petit();
        }
        if (!discarded.estVide()) {
            EnumList<Suit> couleursExclues_ = new EnumList<Suit>();
            couleursExclues_.add(couleurDemandee_);
            EnumList<Suit> coupesNonJoues_ = GameTarotCommon.complementaireCouleurs(coupesFranches_, couleursExclues_);
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

    private CardTarot playOtherThanTrumpAce(TarotInfoPliEnCours _info, Bytes _joueursNonConfiance, Bytes _joueursNonConfianceNonJoue) {
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        byte virtLead_ = _info.getRamasseurVirtuel();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(virtLead_, nombreDeJoueurs_);
        IdMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        boolean maitreJeu_ = _info.isMaitreJeu();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        CustList<HandTarot> suites_ = atoutsCoupe_.eclaterEnCours(
                repartitionCartesJouees_, couleurDemandee_);
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                Suit.TRUMP, couleurDemandee_, cartesCertaines_,
                carteForte_);/*
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
        if (!_info.isContientExcuse()) {
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
            if (carteForte_.getId().getCouleur() == Suit.TRUMP) {
                return trumpOrExcuse(_joueursNonConfiance, virtLead_, suites_);
            }
            boolean joueurConfianceRamasseurProbaPli_ = !_joueursNonConfiance.contains(virtLead_) &&
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
            return trumpOrExcuse(_joueursNonConfiance, virtLead_, suites_);
        }
        if (_joueursNonConfianceNonJoue
                .isEmpty()) {
            return CardTarot.excuse();
        }
        return atoutLePlusPetit(suites_);
    }

    private CardTarot trumpOrExcuse(Bytes _joueursNonConfiance, byte _virtLead, CustList<HandTarot> _suites) {
        if (_joueursNonConfiance.contains(_virtLead)) {
            return atoutLePlusPetit(_suites);
        }
        return CardTarot.EXCUSE;
    }

    private boolean notPlayed(Suit _couleurDemandee, CustList<TrickTarot> _plisFaits, EnumList<Suit> _coupesFranches) {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        /* Il existe au moins deux coupes franches */
        for (Suit coupe_ : _coupesFranches) {
            if (coupe_ != _couleurDemandee && GameTarotCommonPlaying.tours(coupe_, _plisFaits).isEmpty()) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }

    private boolean noOverTrump(Suit _couleurDemandee, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, IdMap<Suit, CustList<HandTarot>> _cartesCertaines, Bytes _joueursNonConfianceNonJoue) {
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
        IdMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        HandTarot atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        if (atoutsCoupe_.contient(CardTarot.petit())) {
            return CardTarot.WHITE;
        }
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        boolean maitreJeu_ = _info.isMaitreJeu();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        CustList<HandTarot> suites_;
        suites_ = atoutsCoupe_.eclaterEnCours(
                repartitionCartesJouees_, couleurDemandee_);
        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();

        CustList<HandTarot> cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
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
        return leads(_info, carteForte_, couleurDemandee_, suites_);
    }

    private CardTarot leads(TarotInfoPliEnCours _info, CardTarot _carteForte, Suit _couleurDemandee, CustList<HandTarot> _suites) {
        boolean contientExcuse_ = _info.isContientExcuse();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        boolean carteMaitresse_;
    /*
    Il n'est pas probable qu'un joueur de non confiance
    n'ayant pas joue coupe le pli
    */
        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        if (_carteForte.getId().getCouleur() == Suit.TRUMP) {
            /*
        Si le pli est deja
        coupe
        */
            if (confidentPlayers.containsObj(ramasseurVirtuel_)) {
                return atoutLePlusPetit(_suites,
                        contientExcuse_);
            }
            return atoutLePlusPetit(_suites);
        }
        CardTarot carteHautePasAtout_;
        carteHautePasAtout_ = doneTrickInfo.getProgressingTrick().getCartes().couleurs()
                .getVal(_couleurDemandee).premiereCarte();
        if (!carteHautePasAtout_.isCharacter()) {
            carteMaitresse_ = nePeutAvoirFigures(_couleurDemandee, cartesPossibles_);
            if (carteMaitresse_) {
                return atoutLePlusPetit(_suites,
                        contientExcuse_);
            }
        } else if (confidentPlayers.containsObj(ramasseurVirtuel_)) {
            carteMaitresse_ = nePeutMonter(_carteForte, _couleurDemandee, cartesPossibles_);
            if (carteMaitresse_) {
                return atoutLePlusPetit(_suites,
                        contientExcuse_);
            }
        }
        return atoutLePlusPetit(_suites);
    }

    private boolean nePeutMonter(CardTarot _carteForte, Suit _couleurDemandee, IdMap<Suit, CustList<HandTarot>> _cartesPossibles) {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        for (byte joueur_ : notConfidentPlayersNotPlay) {
            if(_cartesPossibles
                    .getVal(_couleurDemandee)
                    .get(joueur_).estVide()) {
                continue;
            }
            if (_cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().strength(_couleurDemandee) >= _carteForte.strength(_couleurDemandee)) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }

    private boolean nePeutAvoirFigures(Suit _couleurDemandee, IdMap<Suit, CustList<HandTarot>> _cartesPossibles) {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        for (byte joueur_ : notConfidentPlayersNotPlay) {
            if (!GameTarotTrickHypothesis.nePeutAvoirFigures(_cartesPossibles, joueur_, _couleurDemandee)) {
                carteMaitresse_ = false;
                break;
            }
        }
        return carteMaitresse_;
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
        IdMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
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
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
        HandTarot atoutsCoupe_;
        atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        CustList<HandTarot> suites_;
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
        IdMap<Suit,HandTarot> repartitionJouables_ = _info.getCartesJouables().couleurs();
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
            IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
            CustList<HandTarot> suites_;
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
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        IdMap<Suit,CustList<HandTarot>> suitesTouteCouleur_ = playableCards.eclaterToutEnCours(repartitionCartesJouees_);
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, Suit.couleursOrdinaires());
        //incertitude du ramasseur a la couleur demandee (defausse sur la couleur demandee ordinaire)
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(suitesTouteCouleur_,
                    couleurs_, playableCards,
                    repartitionCartesJouees_);
        }
        return jeuPetiteCarteCouleurFigure(suitesTouteCouleur_,
                couleursNonVides_, playableCards, repartitionCartesJouees_);
    }

    CardTarot discardTeamSuit(TarotInfoPliEnCours _info) {
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        IdMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> suitesTouteCouleur_ = playableCards.eclaterToutEnCours(repartitionCartesJouees_);
        EnumList<Suit> couleursStrictesMaitresses_ = GameTarotCommonPlaying.strictCouleursMaitres(
                suitesTouteCouleur_, repartitionCartesJouees_,
                cartesPossibles_, _info.getCurrentPlayer());
        return defausseCouleurDemandeeSurPartenaire(
                suitesTouteCouleur_, repartitionCartesJouees_,
                playableCards, cartesMaitresses_,
                couleursStrictesMaitresses_, couleurDemandee_);
    }

    CardTarot playIfPartnersWin(TarotInfoPliEnCours _info) {
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        Bytes equipeNumero_ = equipeNumero(_info);
        if (GameTarotTeamsRelation.contientJoueurs(equipeNumero_,GameTarotCommonPlaying.ramasseurs(plisFaits_))) {
            HandTarot noExc_ = noExc(_info);
            return noExc_.premiereCarte();
        }
        return CardTarot.excuse();
    }

    private HandTarot noExc(TarotInfoPliEnCours _info) {
        HandTarot cartesJouables_ = _info.getCartesJouables();
        HandTarot noExc_ = new HandTarot();
        noExc_.ajouterCartes(cartesJouables_);
        noExc_.removeCardIfPresent(CardTarot.EXCUSE);
        return noExc_;
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
        return defaultDiscardSuit(_info);
    }

    CardTarot discardOnTeamTrump(TarotInfoPliEnCours _info) {
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        IdMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> suitesTouteCouleur_ = playableCards.eclaterToutEnCours(repartitionCartesJouees_);
        EnumList<Suit> couleursStrictesMaitresses_ = GameTarotCommonPlaying.strictCouleursMaitres(
                suitesTouteCouleur_, repartitionCartesJouees_,
                cartesPossibles_, _info.getCurrentPlayer());
        return defausseAtoutSurPartenaire(suitesTouteCouleur_,
                repartitionCartesJouees_, playableCards,
                cartesMaitresses_, couleursStrictesMaitresses_);
    }

    CardTarot discardOnFoe(TarotInfoPliEnCours _info) {
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        IdMap<Suit,CustList<HandTarot>> suitesTouteCouleur_ = playableCards.eclaterToutEnCours(repartitionCartesJouees_);
        return discardFoe(suitesTouteCouleur_,repartitionCartesJouees_, playableCards);
    }
    TarotInfoPliEnCours initInformations() {
        return common.initInformations(currentHand,playableCards, confidentPlayers,notConfidentPlayers);
    }

    CardTarot tryPlayExcuseOrLead(TarotInfoPliEnCours _info) {
        if (hasExcPlusCard(currentHand) && GameTarotTrickHypothesis.equipeQuiVaFairePli(_info) == PossibleTrickWinner.TEAM) {
            CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
            Bytes equipeNumero_ = equipeNumero(_info);
            if (GameTarotTeamsRelation.contientJoueurs(equipeNumero_,GameTarotCommonPlaying.ramasseurs(plisFaits_))) {
                HandTarot noExc_ = noExc(_info);
                return noExc_.premiereCarte();
            }
        }
        return tryPlayExcuse(_info.isMaitreJeu(),playableCards);
    }

    private Bytes equipeNumero(TarotInfoPliEnCours _info) {
        Bytes equipeNumero_ = new Bytes();
        equipeNumero_.addAllElts(confidentPlayers);
        equipeNumero_.add(_info.getCurrentPlayer());
        return equipeNumero_;
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
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles, CardTarot _carteForte) {
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
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
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
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
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
            max_ = (byte) NumberUtil.max(
                    _cartesPossibles.getVal(_couleurDemandee)
                            .get(joueur_).premiereCarte()
                            .strength(_couleurDemandee), max_);
        }
        return _carteForte.strength(_couleurDemandee) > max_;
    }
    static boolean peutSauverFigureAppele(
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
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
                if (max_.isCharacter() && _cartesChien.premiereCarte().strength(_couleurDemandee) <= max_.strength(_couleurDemandee)) {
                    local_ = false;
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
            CustList<HandTarot> _suites,
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Bytes _joueurs) {
        if(!_suites.first().premiereCarte().isCharacter()) {
            return false;
        }
        //il existe une suite de la couleur demandee ordinaire avec une figure
        byte max_ = 0;
        for (byte joueur_ : _joueurs) {
            max_ = (byte) NumberUtil.max(
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
            IdMap<Suit, CustList<HandTarot>> _cartesPossibles,
            IdMap<Suit, CustList<HandTarot>> _cartesCertaines, byte _numero,
            Bytes _joueursNonJoue, Bytes _joueursJoue) {
        boolean existe_ = false;
        CustList<HandTarot> atoutsCertains_ = _cartesCertaines.getVal(Suit.TRUMP);
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
        return peutRamasserDemandeAtoutNonMaitre(_cartesPossibles, _joueursNonJoue, _joueursJoue);
    }

    private static boolean peutRamasserDemandeAtoutNonMaitre(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Bytes _joueursNonJoue, Bytes _joueursJoue) {
        boolean existe_ = false;
        CustList<HandTarot> atoutsPossibles_ = _cartesPossibles.getVal(Suit.TRUMP);
        for (byte joueur_ : _joueursNonJoue) {
            if(atoutsPossibles_.get(joueur_).estVide()) {
                continue;
            }
            byte maxAtoutJoueurNonJoue_ = atoutsPossibles_.get(joueur_).premiereCarte()
                    .strength(Suit.TRUMP);
            boolean local_ = true;
            for (byte joueur2_ : _joueursJoue) {
                if (!atoutsPossibles_.get(joueur2_).estVide() && maxAtoutJoueurNonJoue_ <= atoutsPossibles_.get(joueur2_).premiereCarte().strength(Suit.TRUMP)) {
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
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            CustList<HandTarot> _suites, CustList<HandTarot> _cartesRelMaitres,
            Bytes _joueursDeNonConfianceNonJoue,
            Suit _couleurDemandee) {
        boolean pasFigure_ = true;
        for (byte joueur_ : _joueursDeNonConfianceNonJoue) {
            if (!_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide() && _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().isCharacter()) {
                pasFigure_ = false;
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
    static CardTarot carteLaPlusPetite(CustList<HandTarot> _suites) {
        CustList<HandTarot> suitesFigures_ = getCharacterSeq(_suites);
        CustList<HandTarot> suitesCartesBasses_ = getLowCardSeq(_suites);
        if (!suitesCartesBasses_.isEmpty()) {
            return suitesCartesBasses_.last().premiereCarte();
        }
        return suitesFigures_.last().derniereCarte();
    }

    /**
     Renvoie la plus grande carte basse (n'etant pas une figure) a jouer dans
     la suite la plus faible de la couleur
     */
    static CardTarot weakestCard(CustList<HandTarot> _suites) {
        CustList<HandTarot> suitesFigures_ = getCharacterSeq(_suites);
        CustList<HandTarot> suitesCartesBasses_ = getLowCardSeq(_suites);
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
            CustList<HandTarot> _suites) {
        CustList<HandTarot> suitesFigures_ = getCharacterSeq(_suites);
        CustList<HandTarot> suitesCartesBasses_ = getLowCardSeq(_suites);
        if (!suitesFigures_.isEmpty()) {
            return suitesFigures_.last().premiereCarte();
        }
        return suitesCartesBasses_.last().premiereCarte();

    }

    static CustList<HandTarot> getCharacterSeq(CustList<HandTarot> _suites) {
        CustList<HandTarot> suitesFigures_ = new CustList<HandTarot>();
        for(HandTarot m: _suites) {
            int nbFigures_ = m.nombreDeFigures();
            if(nbFigures_ == m.total()) {
                suitesFigures_.add(m);
                continue;
            }
            if (nbFigures_ != 0) {
                //nbFigures > 0 && nbFigures < m.total()
                //==> m.total() >= 2 && !suiteFigures.isEmpty() && !suiteCartesBasses.isEmpty()
                HandTarot suiteFigures_ = suiteFigures(m);
                suitesFigures_.add(suiteFigures_);
            }
        }
        return suitesFigures_;
    }

    private static HandTarot suiteFigures(HandTarot _m) {
        HandTarot suiteFigures_ = new HandTarot();
        for(CardTarot c: _m) {
            if(c.isCharacter()) {
                suiteFigures_.ajouter(c);
            }
        }
        return suiteFigures_;
    }

    static CustList<HandTarot> getLowCardSeq(CustList<HandTarot> _suites) {
        CustList<HandTarot> suitesCartesBasses_ = new CustList<HandTarot>();
        for(HandTarot m: _suites) {
            int nbFigures_ = m.nombreDeFigures();
            if (nbFigures_ != m.total()) {
                if (nbFigures_ == 0) {
                    suitesCartesBasses_.add(m);
                    continue;
                }
                //nbFigures > 0 && nbFigures < m.total()
                //==> m.total() >= 2 && !suiteFigures.isEmpty() && !suiteCartesBasses.isEmpty()=
                HandTarot suiteCartesBasses_ = suiteCartesBasses(m);
                suitesCartesBasses_.add(suiteCartesBasses_);
            }
        }
        return suitesCartesBasses_;
    }

    private static HandTarot suiteCartesBasses(HandTarot _m) {
        HandTarot suiteCartesBasses_ = new HandTarot();
        for(CardTarot c: _m) {
            if(c.isCharacter()) {
                continue;
            }
            suiteCartesBasses_.ajouter(c);
        }
        return suiteCartesBasses_;
    }

    /**Methode retournant un atout different du Petit sauf si ce dernier est sec.*/
    static CardTarot atoutLePlusPetit(CustList<HandTarot> _suites) {
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
    static CardTarot atoutLePlusPetit(CustList<HandTarot> _suites,
                                              boolean _contientExcuse) {
        if (_contientExcuse) {
            return CardTarot.excuse();
        }
        /* Le joueur n'a pas l'Excuse */
        return atoutLePlusPetit(_suites);
    }

    static CardTarot discardFoe(IdMap<Suit, CustList<HandTarot>> _suitesTouteCouleur, IdMap<Suit, HandTarot> _repartitionCartesJouees, HandTarot _main) {
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
            IdMap<Suit,CustList<HandTarot>> _suites, EnumList<Suit> _couleursNonVides,
            HandTarot _main,
            IdMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursAvecLaPlusPetiteCarteBasse(_main, _couleursNonVides);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees),couleurs_);
        return carteLaPlusPetite(_suites.getVal(couleurs_.first()));
    }

    static CardTarot defausseAtoutSurPartenaire(
            IdMap<Suit,CustList<HandTarot>> _suitesTouteCouleur,
            IdMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, IdMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        boolean carteMaitresse_ = _couleursStrictementMaitresses.size() == Suit.couleursOrdinaires().size();
        if (_repartitionCartesJouees.getVal(Suit.TRUMP).total() > 17) {
            IdMap<Suit,HandTarot> repartition_=_main.couleurs();
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
            IdMap<Suit,CustList<HandTarot>> _suitesTouteCouleur,
            IdMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, IdMap<Suit,HandTarot> _cartesMaitresses,
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
            IdMap<Suit,HandTarot> repartition_=_main.couleurs();
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

    static CardTarot discardOptimPartner(IdMap<Suit, CustList<HandTarot>> _suitesTouteCouleur,
                                         IdMap<Suit, HandTarot> _repartitionCartesJouees,
                                         HandTarot _main, IdMap<Suit, HandTarot> _cartesMaitresses) {
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
        EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursAvecCarteNonMaitresse(_main,
                Suit.couleursOrdinaires(), _cartesMaitresses);
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

    static CardTarot discardCardPartner(IdMap<Suit, CustList<HandTarot>> _suitesTouteCouleur,
                                        IdMap<Suit, HandTarot> _repartitionCartesJouees, HandTarot _main,
                                        IdMap<Suit, HandTarot> _cartesMaitresses, EnumList<Suit> _couleursStrictementMaitresses,
                                        boolean _carteMaitresse) {
        IdMap<Suit,HandTarot> repartition_=_main.couleurs();
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
        if (_carteMaitresse && leadingCard(_cartesMaitresses, _couleursStrictementMaitresses, repartition_)) {
            EnumList<Suit> couleursFigures_ = GameTarotCommon.couleursAvecFigures(_main, couleursNonVides_);
            if (!couleursFigures_.isEmpty()) {
                return jeuGrandeCarteDefausseMaitre(couleursFigures_,
                        _main);
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

    static boolean leadingCard(IdMap<Suit, HandTarot> _cartesMaitresses, EnumList<Suit> _couleursStrictementMaitresses, IdMap<Suit, HandTarot> _repartition) {
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
            IdMap<Suit,HandTarot> _repartitionCartesJouees) {
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
            IdMap<Suit,CustList<HandTarot>> _suites,
            IdMap<Suit,HandTarot> _cartesMaitresses,
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
            IdMap<Suit,CustList<HandTarot>> _suites, EnumList<Suit> _couleursNonVides,
            HandTarot _main,
            IdMap<Suit,HandTarot> _repartitionCartesJouees) {
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

    public Role getCurrentStatus() {
        return currentStatus;
    }
}
