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
    private final Ints confidentPlayers;
    private final Ints notConfidentPlayers;
    private final Ints played;
    private final Ints notPlayed;
    private final Ints notConfidentPlayersNotPlay;
    private final HandTarot playableCards;
    private final HandTarot discarded = new HandTarot();
    private final Suit couleurDemandee;
    private final TarotInfoPliEnCours tarotInfoPliEnCours;
    private final CardTarot carteForte;
    private final IdMap<Suit, HandTarot> repartitionCartesJouees;
    private final IdMap<Suit, HandTarot> repartitionJouables;
    private final HandTarot repartitionDemJouables;
    private final HandTarot repartitionAtJouables;
    private final int currentPlayer;
    private final CustList<HandTarot> suitesDemJouables;
    private final CustList<HandTarot> suitesAtJouables;
    private final int ramasseurVirtuel;
    private final IdMap<Suit, CustList<HandTarot>> cartesPossibles;
    private final IdMap<Suit, CustList<HandTarot>> cartesCertaines;
    private final CustList<TrickTarot> plisFaits;
    private final IdMap<Suit, HandTarot> cartesMaitresses;
    private final boolean maitreJeu;
    private final IdList<Suit> coupesFranches;
    private final IdMap<Suit, CustList<HandTarot>> suitesTouteCouleurJouable;
    private final CustList<HandTarot> cartesRelMaitresDemJouables;
    private final CustList<HandTarot> cartesRelMaitresAtJouables;
    private final CustList<TrickTarot> tours;
    private final IdList<Suit> couleursStrictesMaitresses;
    private final Ints ramasseurs;

    public GameTarotProgTrickClassic(GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
                                     HandTarot _calledCards, HandTarot _currentHand) {
        doneTrickInfo = _done;
        teamsRelation = _teamsRelation;
        calledCards = _calledCards;
        currentHand = _currentHand;
        common = new GameTarotCommonPlaying(_done,_teamsRelation);
        int nbPlayers_ = _teamsRelation.getNombreDeJoueurs();
        TrickTarot trTarot_ = _done.getProgressingTrick();
        couleurDemandee = trTarot_.couleurDemandee();
        played = trTarot_.joueursAyantJoue(nbPlayers_);
        currentPlayer = trTarot_.getNextPlayer(nbPlayers_);
        notPlayed = GameTarotTeamsRelation.autresJoueurs(played,nbPlayers_);
        notPlayed.removeObj(currentPlayer);
        playableCards = HandTarotResult.cartesJouables(_teamsRelation.getRules(),_teamsRelation.getTaker(),currentHand.couleurs(),_done.getProgressingTrick(),_done.getTricks(),_calledCards).getPlayable();
        currentStatus = _teamsRelation.statutDe(currentPlayer);
        confidentPlayers = _teamsRelation.joueursConfiance(currentPlayer,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        notConfidentPlayers = _teamsRelation.joueursNonConfiance(currentPlayer,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        notConfidentPlayersNotPlay = GameTarotTeamsRelation.intersectionJoueurs(notConfidentPlayers,notPlayed);
        tarotInfoPliEnCours = common.initInformations(currentHand, confidentPlayers, notConfidentPlayers);
        repartitionCartesJouees = tarotInfoPliEnCours.getRepartitionCartesJouees();
        ramasseurVirtuel = tarotInfoPliEnCours.getRamasseurVirtuel();
        cartesPossibles = tarotInfoPliEnCours.getCartesPossibles();
        cartesCertaines = tarotInfoPliEnCours.getCartesCertaines();
        cartesMaitresses = tarotInfoPliEnCours.getCartesMaitresses();
        carteForte = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel, teamsRelation.getNombreDeJoueurs());
        repartitionJouables = playableCards.couleurs();
        repartitionDemJouables = HandTarot.nullToEmpty(repartitionJouables.getVal(couleurDemandee));
        repartitionAtJouables = repartitionJouables.getVal(Suit.TRUMP);
        plisFaits = tarotInfoPliEnCours.getPlisFaits();
        maitreJeu = tarotInfoPliEnCours.isMaitreJeu();
        coupesFranches = tarotInfoPliEnCours.getCoupesFranches();
        boolean knownLastHand_ = !doneTrickInfo.getLastSeenHand().estVide();
        if (knownLastHand_ && teamsRelation.isVirtualTaker(currentPlayer)) {
            discarded.ajouterCartes(_done.getTricks().first().getCartes());
        }
        suitesDemJouables = repartitionDemJouables
                .eclaterEnCours(repartitionCartesJouees, couleurDemandee);
        suitesAtJouables = repartitionAtJouables
                .eclaterEnCours(repartitionCartesJouees, couleurDemandee);
        suitesTouteCouleurJouable = playableCards.eclaterToutEnCours(repartitionCartesJouees);
        cartesRelMaitresDemJouables = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suitesDemJouables, cartesPossibles, notPlayed,
                couleurDemandee, couleurDemandee, cartesCertaines,
                carteForte);
        cartesRelMaitresAtJouables = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suitesAtJouables, cartesPossibles, notPlayed,
                Suit.TRUMP, couleurDemandee, cartesCertaines,
                carteForte);
        tours = GameTarotCommonPlaying.tours(couleurDemandee, plisFaits);
        couleursStrictesMaitresses = GameTarotCommonPlaying.strictCouleursMaitres(
                suitesTouteCouleurJouable, repartitionCartesJouees,
                cartesPossibles, currentPlayer);
        ramasseurs = GameTarotCommonPlaying.ramasseurs(plisFaits);
    }
    CardTarot enCoursClassic() {
        if (playableCards.total() == 1) {
            return playableCards.premiereCarte();
        }
        if (couleurDemandee == Suit.UNDEFINED) {
            //cartesJouables.total() > 1
            GameTarotBeginTrickClassic g_ = new GameTarotBeginTrickClassic(doneTrickInfo,teamsRelation,calledCards,currentHand);
            if (teamsRelation.existePreneur()) {
                return g_.entameSurExcuseClassique();
                /* Variables locales avec jeu d'equipe */
            }
            return g_.entameClassique();
        }
        CardTarot card_ = tryPlayExcuseOrLead();
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        if (Suit.couleursOrdinaires().containsObj(couleurDemandee)) {
            if (!repartitionJouables.getVal(couleurDemandee).estVide()) {
                return fournirCouleurOrdinaireClassique();
            }
            if (!repartitionAtJouables.estVide()) {
                return coupeClassique();
            }
            return defausseCouleurOrdinaireClassique();
        }
        if (!repartitionJouables.getVal(couleurDemandee).estVide()) {
            return fournirAtoutClassique();
        }
        return defausseAtoutClassique();
    }
    CardTarot fournirCouleurOrdinaireClassique() {
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(tarotInfoPliEnCours);
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return followNormalSuitFoe();
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return followNormalSuitPartners();
        }
        return followDefaultSuit();
    }

    CardTarot followDefaultSuit() {
        //incertitude
        CardTarot card_ = followNormalSuit();
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        /* Maintenant le joueur peut prendre la main avec une figure */
        if (teamsRelation.isVirtualTaker(currentPlayer)) {
            //fournir a la couleur demandee ordinaire
            return followAsTaker();
        }
        /* Appele */
        if (currentStatus == Role.CALLED_PLAYER) {
            return followAsCalledPlayer();

        }
        /* Defenseur */
        return followAsDefender();
    }

    private static boolean hasExcPlusCard(HandTarot _current) {
        return _current.total() == 2 && _current.contient(CardTarot.EXCUSE);
    }

    CardTarot followAsDefender() {
        //fournir a la couleur demandee ordinaire
        if (canLeadTrick(maitreJeu, cartesRelMaitresDemJouables)) {
            return cartesRelMaitresDemJouables.last()
                    .premiereCarte();
        }
        if (GameTarotTrickHypothesis.pasAtout(notConfidentPlayersNotPlay,
                cartesPossibles)) {
            return sauveQuiPeutFigure(cartesPossibles,
                    suitesDemJouables, cartesRelMaitresDemJouables,
                    notConfidentPlayersNotPlay,
                    couleurDemandee);
        }
        if (tours.isEmpty()) {
            IdList<Suit> couleursAppelees_ = common.couleursAppelees();
            if (couleursAppelees_.containsObj(couleurDemandee)) {
                return carteLaPlusPetite(suitesDemJouables);
            }
            if (cartesRelMaitresDemJouables.isEmpty()) {
                return carteLaPlusPetite(suitesDemJouables);
            }
            if (!notPlayed.containsObj(teamsRelation.getTaker())
                    || carteForte.isCharacter()) {
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
                return gearRelativeLeadingCards(suitesDemJouables, cartesRelMaitresDemJouables);
            }
            if (cartesRelMaitresDemJouables.first().total() == 1
                    && repartitionDemJouables.total() == 2) {
                return repartitionDemJouables.premiereCarte();
            }
            return carteLaPlusPetite(suitesDemJouables);
        }
        /* Maintenant on est au moins au deuxieme tour */
        return carteLaPlusPetite(suitesDemJouables);
    }

    private static CardTarot gearRelativeLeadingCards(CustList<HandTarot> _suitesJouables, CustList<HandTarot> _cartesRelMaitresDemJouables) {
        if (_cartesRelMaitresDemJouables.size() == 1
                || !_cartesRelMaitresDemJouables.last()
                .premiereCarte().isCharacter()) {
            return _suitesJouables.first().premiereCarte();
        }
        return _cartesRelMaitresDemJouables.last()
                .premiereCarte();
    }

    CardTarot followAsCalledPlayer() {
        //fournir a la couleur demandee ordinaire
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        if (tours.isEmpty()) {
            if (canLeadTrick(maitreJeu, cartesRelMaitresDemJouables)) {
                return cartesRelMaitresDemJouables.last()
                        .premiereCarte();
            }
            HandTarot cartesChienCouleur_ = doneTrickInfo.getLastSeenHand().couleurs()
                    .getVal(couleurDemandee);
            if(!cartesRelMaitresDemJouables.isEmpty() && peutSauverFigureAppele(
                    cartesPossibles,
                    couleurDemandee,
                    cartesChienCouleur_,
                    notConfidentPlayersNotPlay
            )) {
                if (!notPlayed.containsObj(teamsRelation.getTaker())) {
                    /*Si l'appele
                joue apres le preneur*/
                    return gearRelativeLeadingCards(cartesRelMaitresDemJouables, cartesRelMaitresDemJouables);
                }
                if (cartesChienCouleur_
                        .nombreDeFigures() > 0) {
                    /*Si l'appele joue
                    avant le preneur*/
                    return jeuFigureHauteDePlusFaibleSuite(suitesDemJouables);
                }
            }
            return playWhenLastDiscard();
        }
        TrickTarot dernierPli_;
        dernierPli_ = tours.last();
        Ints dernieresDefausses_;
        dernieresDefausses_ = dernierPli_.joueursDefausses(nombreDeJoueurs_);
            /*
            Deuxieme tour pour un appele ne coupant pas la
            couleur demandee differente de l'atout
            */
        Ints joueursSusceptiblesDeCouper_;
        joueursSusceptiblesDeCouper_ = GameTarotTrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles,couleurDemandee,notPlayed);
        if (!joueursSusceptiblesDeCouper_.isEmpty()) {
            return processWhenPossibleTrumps(joueursSusceptiblesDeCouper_);
        }
        /* Si la coupe semble improbable */
        if (discardAtSecondRound(tours, dernieresDefausses_)) {
            return playWhenLastDiscard();
        }
            /*
            Le pli d'avant n'est pas defausse ou c'est au moins
            le troisieme tour
            */
        return tryLeadTrick(cartesMaitresses, couleurDemandee, repartitionDemJouables, suitesDemJouables, cartesRelMaitresDemJouables);
    }

    private static boolean discardAtSecondRound(CustList<TrickTarot> _tours, Ints _dernieresDefausses) {
        return !_dernieresDefausses.isEmpty() && _tours.size() == 1;
    }

    CardTarot followAsTaker() {
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        if (tours.isEmpty()) {
            return playWhenLastDiscard();
        }
        boolean joueurConfianceRamasseur_ = confidentPlayers.containsObj(ramasseurVirtuel);
        boolean joueurConfianceRamasseurProbaPli_ = joueurConfianceRamasseur_ &&
                joueurConfianceRamasseurProbaPli(
                        notConfidentPlayersNotPlay,
                        couleurDemandee,
                        cartesPossibles,
                        carteForte);
        /* C'est au moins le deuxieme tour */
        TrickTarot dernierPli_;
        dernierPli_ = tours.last();
        Ints dernieresDefausses_;
        dernieresDefausses_ = dernierPli_.joueursDefausses(nombreDeJoueurs_);
        Ints joueursSusceptiblesDeCouper_;
        joueursSusceptiblesDeCouper_ = GameTarotTrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles,couleurDemandee,notPlayed);
        if (!joueursSusceptiblesDeCouper_.isEmpty()) {
            return processWhenPossibleTrumps(joueursSusceptiblesDeCouper_);
        }
        if (joueurConfianceRamasseurProbaPli_) {
            return repartitionDemJouables
                    .premiereCarte();
        }
        /* Si la coupe semble improbable */
        if (!discardAtSecondRound(tours, dernieresDefausses_)) {
            return tryLeadTrick(cartesMaitresses, couleurDemandee, repartitionDemJouables, suitesDemJouables, cartesRelMaitresDemJouables);
        }
        if (canLeadTrick(maitreJeu,cartesRelMaitresDemJouables)) {
            return cartesRelMaitresDemJouables.last()
                    .premiereCarte();
        }
        CardTarot cardTarot_ = tryFollow();
        if (cardTarot_ != CardTarot.WHITE) {
            return cardTarot_;
        }
        return repartitionDemJouables
                .premiereCarte();
    }
    private CardTarot tryFollow() {
        IdList<Suit> couleursAppelees_ = common.couleursAppelees();
        if (couleursAppelees_.containsObj(couleurDemandee)) {/* La couleur demandee est la couleur appelee */
            if (cartesMaitresses.getVal(couleurDemandee)
                    .estVide()) {
                return jeuFigureHauteDePlusFaibleSuite(suitesDemJouables);
            }
            return repartitionDemJouables.premiereCarte();
        /*Le pli d'avant n'est pas defausse ou c'est au moins
            le troisieme tour*/
        }
        if (cartesMaitresses.getVal(couleurDemandee)
                .estVide()) {
            if (aucunePriseMainPossibleParFigure(
                    cartesPossibles, couleurDemandee, notConfidentPlayersNotPlay)) {
                return repartitionDemJouables
                        .premiereCarte();
            }
            return carteLaPlusPetite(suitesDemJouables);
        }
        if (suitesDemJouables.size() == 1
                || !suitesDemJouables.get(1).premiereCarte()
                .isCharacter()) {
            return repartitionDemJouables
                    .premiereCarte();
        }
        if (aucunePriseMainPossibleParFigure(
                cartesPossibles, couleurDemandee, notConfidentPlayersNotPlay)) {
            return jeuFigureHauteDePlusFaibleSuite(suitesDemJouables);
        }
        return CardTarot.WHITE;
    }


    private CardTarot processWhenPossibleTrumps(Ints _joueursSusceptiblesDeCouper) {
        if (!GameTarotTeamsRelation.intersectionJoueurs(notConfidentPlayers, _joueursSusceptiblesDeCouper).isEmpty()) {
            return carteLaPlusPetite(suitesDemJouables);
        }
        if (maitreJeu) {
            return carteLaPlusPetite(suitesDemJouables);
        }
        return jeuFigureHauteDePlusFaibleSuite(suitesDemJouables);
    }

    CardTarot followNormalSuitPartners() {
        if (maitreJeu) {
            if (suitesDemJouables.size() == 1) {
                return repartitionDemJouables.premiereCarte();
            }
            return weakestCard(suitesDemJouables);
        }
        /* couleur demandee non atout */
        if (repartitionDemJouables.premiereCarte().isCharacter()) {
            return repartitionDemJouables.premiereCarte();
        }
        return weakestCard(suitesDemJouables);
    }

    CardTarot followNormalSuitFoe() {
        return carteLaPlusPetite(suitesDemJouables);
    }

    CardTarot playWhenLastDiscard() {
        boolean joueurConfianceRamasseur_ = confidentPlayers.containsObj(ramasseurVirtuel);
        boolean joueurConfianceRamasseurProbaPli_ = joueurConfianceRamasseur_ &&
                joueurConfianceRamasseurProbaPli(
                        notConfidentPlayersNotPlay,
                        couleurDemandee,
                        cartesPossibles,
                        carteForte);
        if (canLeadTrick(maitreJeu, cartesRelMaitresDemJouables)) {
            return cartesRelMaitresDemJouables.last()
                    .premiereCarte();
        }
        CardTarot cardTarot_ = tryFollow();
        if (cardTarot_ != CardTarot.WHITE) {
            return cardTarot_;
        }
        if (carteForte.isCharacter()) {
            if (!joueurConfianceRamasseurProbaPli_) {
                return repartitionDemJouables
                        .premiereCarte();
            }
            return jeuFigureHauteDePlusFaibleSuite(suitesDemJouables);
        }
        return repartitionDemJouables
                .premiereCarte();
    }

    CardTarot followNormalSuit() {
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        if (repartitionDemJouables.premiereCarte().strength(couleurDemandee)< carteForte
                .strength(couleurDemandee)) {
            return followNormalSuitCannotLead();
        }
        /* Maintenant on sait le joueur peut prendre la main */
        if (!repartitionDemJouables.premiereCarte().isCharacter()) {
            if (canLeadTrick(maitreJeu, cartesRelMaitresDemJouables)) {
                return cartesRelMaitresDemJouables.last()
                        .premiereCarte();
            }
            Ints joueursNonConfiance_ = teamsRelation.joueursNonConfiance(currentPlayer,GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
            Ints joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_,notPlayed);
            if (aucunePriseMainPossibleParFigure(
                    cartesPossibles, couleurDemandee, joueursNonConfianceNonJoue_) && !cartesRelMaitresDemJouables.isEmpty()) {
                return cartesRelMaitresDemJouables.last()
                        .premiereCarte();
            }
            if (tours.isEmpty()) {
                return repartitionDemJouables.premiereCarte();
            }
            return carteLaPlusPetite(suitesDemJouables);
        }
        return CardTarot.WHITE;
    }

    private CardTarot followNormalSuitCannotLead() {
        /* Si le joueur ne peut pas prendre la main */
        boolean joueurConfianceRamasseur_ = confidentPlayers.containsObj(ramasseurVirtuel);
        if (!repartitionDemJouables.premiereCarte().isCharacter()) {
            /*Si le joueur
            ne possede pas
            de figure*/
            return carteLaPlusPetite(suitesDemJouables);
        }
        /* Le joueur possede au moins une figure */
        if (tours.isEmpty()) {
            /*
        Si cette couleur est entamee pour
        la premiere fois
        */
            if (joueurConfianceRamasseur_ && aucunePriseMainPossibleCouleur(
                    cartesPossibles, couleurDemandee,
                    carteForte, notConfidentPlayersNotPlay)) {
                return repartitionDemJouables
                        .premiereCarte();
            }
            return carteLaPlusPetite(suitesDemJouables);
        }
        if (joueurConfianceRamasseur_ && carteForte.getId().getCouleur() == Suit.TRUMP) {
                /*
                L'espoir fait
                vivre
                */
            return repartitionDemJouables
                    .premiereCarte();
        }
        return carteLaPlusPetite(suitesDemJouables);
    }

    static CardTarot tryLeadTrick(IdMap<Suit, HandTarot> _cartesMaitresses, Suit _couleurDemandee,
                                  HandTarot _repartitionDemJouables,
                                  CustList<HandTarot> _suitesDemJouables, CustList<HandTarot> _cartesRelMaitresDemJouables) {
        if (!_cartesMaitresses.getVal(_couleurDemandee)
                .estVide()) {
            return _repartitionDemJouables.premiereCarte();
        }
        if (!_cartesRelMaitresDemJouables.isEmpty()) {
            return _cartesRelMaitresDemJouables.last()
                    .premiereCarte();
        }
        return carteLaPlusPetite(_suitesDemJouables);
    }

    private static boolean canLeadTrick(boolean _maitreJeu, CustList<HandTarot> _cartesRelMaitres) {
        return !_cartesRelMaitres.isEmpty() && _maitreJeu;
    }

    CardTarot fournirAtoutClassique() {
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(tarotInfoPliEnCours);
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return followTrumpFoe();
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return followTrumpTeam();
        }
        return followTrumpDefault();

    }

    CardTarot followTrumpDefault() {
        //incertitude du ramasseur a la couleur demandee (founiture obligatoire de la couleur demandee)
        /*
        CarteTarot temporairement
        maitresse
        */


        //fournir d'un atout a la demande d'atout
        if (repartitionAtJouables.premiereCarte().strength(couleurDemandee)< carteForte
                .strength(couleurDemandee)) {
            return atoutLePlusPetit(suitesAtJouables, playableCards.contient(CardTarot.excuse()));
        }
        if (CardTarot.eq(doneTrickInfo.getProgressingTrick().premiereCarteNonExc(), CardTarot.petit())) {
            return followWhenTrumpAcePlayedFirst();
        }
        if (canLeadTrick(maitreJeu, cartesRelMaitresAtJouables)) {
            return cartesRelMaitresAtJouables.last().premiereCarte();
        }
        boolean carteMaitresse_ = leadTrumps(cartesPossibles, carteForte, notConfidentPlayersNotPlay);
        if (carteMaitresse_
                && confidentPlayers.containsObj(ramasseurVirtuel)) {
            return atoutLePlusPetit(suitesAtJouables, playableCards.contient(CardTarot.excuse()));
        }
        int nombrePoints_ = getNbPointsInCurrentTrick();
        if (nombrePoints_ > 6) {
            if (!cartesRelMaitresAtJouables.isEmpty() && !notConfidentPlayersNotPlay.isEmpty()) {
                return cartesRelMaitresAtJouables.last().premiereCarte();
            }
            return atoutLePlusPetit(suitesAtJouables);
        }
        if (carteMaitresse_) {
            return atoutLePlusPetit(suitesAtJouables);
        }
        return atoutLePlusPetit(suitesAtJouables, playableCards.contient(CardTarot.excuse()));
    }

    private CardTarot followWhenTrumpAcePlayedFirst() {
        if (confidentPlayers.containsObj(ramasseurVirtuel) && notConfidentPlayersNotPlay.isEmpty()) {
            return atoutLePlusPetit(suitesAtJouables, playableCards.contient(CardTarot.excuse()));
        }
        if (!cartesRelMaitresAtJouables.isEmpty()) {
            return cartesRelMaitresAtJouables.last().premiereCarte();
        }
        if (peutRamasserDemandeAtoutNonMaitre(cartesPossibles, cartesCertaines,
                repartitionAtJouables.premiereCarte().strength(Suit.TRUMP), notPlayed, played) && !notConfidentPlayersNotPlay.isEmpty()) {
            return repartitionAtJouables.premiereCarte();
        }
        return suitesAtJouables.last().premiereCarte();
    }

    CardTarot followTrumpTeam() {
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        /* couleur demandee atout */
        //jeu obligatoire d'atout
        if (!repartitionAtJouables.contient(CardTarot.petit())
                || maitreJeu) {
            return atoutLePlusPetit(suitesAtJouables, playableCards.contient(CardTarot.excuse()));
        }
        Ints joueursNonConfiance_ = teamsRelation.joueursNonConfiance(currentPlayer,GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        if (GameTarotTrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles)) {
            return atoutLePlusPetit(suitesAtJouables);
        }
        return CardTarot.petit();
    }

    CardTarot followTrumpFoe() {
        /* La couleur demandee est atout */
        return atoutLePlusPetit(
                suitesAtJouables, playableCards.contient(CardTarot.excuse()));
    }

    static boolean leadTrumps(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, CardTarot _carteForte, Ints _players) {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        for (int joueur_ : _players) {
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

    CardTarot coupeClassique() {
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(tarotInfoPliEnCours);
        //jouer un atout en coupe, surcoupe ou souscoupe
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return underTrumpFoe();
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return underTrumpPartners();
        }
        CardTarot card_ = underTrump();
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        //incertitude du ramasseur a la couleur demandee (jeu atout obligatoire de la couleur demandee)
        if (tours.isEmpty()) {
            return trumpFirstRound();

        }
        /* Deuxieme tour et plus */
        card_ = trumpNoAce();
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        return defaultTrump();
    }

    CardTarot defaultTrump() {
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        /*
        Le joueur possede le Petit et c'est le duxieme tour a
        cette couleur ou plus
        */
        if (GameTarotTrickHypothesis.pasAtout(notConfidentPlayers, cartesPossibles)) {
            return atoutLePlusPetit(suitesAtJouables);
        }
        if (canLeadTrick(maitreJeu, cartesRelMaitresAtJouables)) {
            if(notConfidentPlayersNotPlay.isEmpty()) {
                return atoutLePlusPetit(suitesAtJouables);
            }
            return cartesRelMaitresAtJouables.last().premiereCarte();
        }
        boolean carteMaitresse_ = noOverTrump(notConfidentPlayersNotPlay);
        if (carteMaitresse_) {
            return CardTarot.petit();
        }
        if (nombreDeJoueurs_ < 5 && tours.size() == 1) {
            Ints joueursCoupePreTour_ = tours.first().joueursCoupes();
            if (GameTarotTeamsRelation.intersectionJoueurs(notConfidentPlayers, joueursCoupePreTour_).isEmpty()) {
                return CardTarot.petit();
            }
        }
        /* Le jeu s'effectue maintenant a 5 joueurs */
        return atoutLePlusPetit(suitesAtJouables);
    }

    CardTarot trumpFirstRound() {
    /*
    Le joueur peut surcouper si le pli est deja coupe ou
    couper avec n'importe quel atout
    */
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        Ints joueursNonConfiance_ = teamsRelation.joueursNonConfiance(currentPlayer,GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Ints joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_, notPlayed);
        if (!repartitionAtJouables.contient(CardTarot.petit())) {
            return playOtherThanTrumpAce(joueursNonConfiance_, joueursNonConfianceNonJoue_);
        }
        HandTarot atoutsCoupeSansPetit_ = new HandTarot();
        atoutsCoupeSansPetit_.ajouterCartes(repartitionAtJouables);
        atoutsCoupeSansPetit_.removeCardIfPresent(CardTarot.TRUMP_1);
        CustList<HandTarot> suitesSansPetit_ = atoutsCoupeSansPetit_.eclaterEnCours(
                repartitionCartesJouees, couleurDemandee);
        CustList<HandTarot> cartesRelMaitresSansPetit_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suitesSansPetit_, cartesPossibles, notPlayed,
                Suit.TRUMP, couleurDemandee, cartesCertaines,
                carteForte);
        /* Le joueur peut couper avec le Petit */
        if (canLeadTrick(maitreJeu, cartesRelMaitresSansPetit_)) {
            return cartesRelMaitresSansPetit_.
                    last().premiereCarte();
        }
        if (GameTarotTrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles)) {
            return atoutLePlusPetit(suitesAtJouables);
        }
        boolean carteMaitresse_ = noOverTrump(joueursNonConfianceNonJoue_);
        if (carteMaitresse_) {
            return CardTarot.petit();
        }
        if (coupesFranches.size() == 1) {
            return CardTarot.petit();
        }
        carteMaitresse_ = notPlayed();
        if (carteMaitresse_) {
            return CardTarot.petit();
        }
        if (!discarded.estVide()) {
            IdList<Suit> couleursExclues_ = new IdList<Suit>();
            couleursExclues_.add(couleurDemandee);
            IdList<Suit> coupesNonJoues_ = GameTarotCommon.complementaireCouleurs(coupesFranches, couleursExclues_);
            coupesNonJoues_ = GameTarotCommonPlaying.couleursNonEntamees(plisFaits, coupesNonJoues_);
            int nbCartesEcarteesCouleurDemandee_ = discarded.couleur(couleurDemandee).total();
            coupesNonJoues_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(
                    discarded,
                    coupesNonJoues_, nbCartesEcarteesCouleurDemandee_ - 1);
            if (!coupesNonJoues_.isEmpty()) {
                return atoutLePlusPetit(suitesAtJouables);
            }
        }
        return CardTarot.petit();
    }

    private CardTarot playOtherThanTrumpAce(Ints _joueursNonConfiance, Ints _joueursNonConfianceNonJoue) {
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
        if (!playableCards.contient(CardTarot.excuse())) {
            if (canLeadTrick(maitreJeu, cartesRelMaitresAtJouables)) {
                return cartesRelMaitresAtJouables.last()
                        .premiereCarte();
            }
            return atoutLePlusPetit(suitesAtJouables,
                    false);
        }
        /* Maintenant le joueur possede l'Excuse */
        CardTarot carteHautePasAtout_;
        carteHautePasAtout_ = doneTrickInfo.getProgressingTrick().getCartes().couleurs()
                .getVal(couleurDemandee).premiereCarte();
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
            if (carteForte.getId().getCouleur() == Suit.TRUMP) {
                return trumpOrExcuse(_joueursNonConfiance);
            }
            boolean joueurConfianceRamasseurProbaPli_ = !_joueursNonConfiance.contains(ramasseurVirtuel) &&
                    joueurConfianceRamasseurProbaPli(
                            notConfidentPlayersNotPlay,
                            couleurDemandee,
                            cartesPossibles,
                            carteForte);
            if (joueurConfianceRamasseurProbaPli_) {
                return CardTarot.EXCUSE;
            }
            return atoutLePlusPetit(suitesAtJouables);
        }
        if (doneTrickInfo.getProgressingTrick().contient(CardTarot.TRUMP_1)) {
            return trumpOrExcuse(_joueursNonConfiance);
        }
        if (_joueursNonConfianceNonJoue
                .isEmpty()) {
            return CardTarot.excuse();
        }
        return atoutLePlusPetit(suitesAtJouables);
    }

    private CardTarot trumpOrExcuse(Ints _joueursNonConfiance) {
        if (_joueursNonConfiance.contains(ramasseurVirtuel)) {
            return atoutLePlusPetit(suitesAtJouables);
        }
        return CardTarot.EXCUSE;
    }

    private boolean notPlayed() {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        /* Il existe au moins deux coupes franches */
        for (Suit coupe_ : coupesFranches) {
            if (coupe_ != couleurDemandee && GameTarotCommonPlaying.tours(coupe_, plisFaits).isEmpty()) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }

    private boolean noOverTrump(Ints _joueursNonConfianceNonJoue) {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        for (int joueur_ : _joueursNonConfianceNonJoue) {
            if (!GameTarotTrickHypothesis.nePeutCouper(couleurDemandee, joueur_, cartesPossibles, cartesCertaines)) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }

    CardTarot trumpNoAce() {
        if (repartitionAtJouables.contient(CardTarot.petit())) {
            return CardTarot.WHITE;
        }

        if (canLeadTrick(maitreJeu, cartesRelMaitresAtJouables)) {
            return cartesRelMaitresAtJouables.last()
                    .premiereCarte();
        }
        CardTarot first_ = repartitionAtJouables.premiereCarte();
        boolean carteMaitresse_ = noOverTrump(notConfidentPlayersNotPlay);
        if (!carteMaitresse_) {
            int nombrePoints_ = getNbPointsInCurrentTrick();
            if (nombrePoints_ > 7) {
                if (!cartesRelMaitresAtJouables.isEmpty()) {
                    return cartesRelMaitresAtJouables.last()
                            .premiereCarte();
                }
                return first_;
            }
            /*
            Moins de 8 points (les points sont doubles pour
            eviter les 1/2 points)
            */
            return suitesAtJouables.last().premiereCarte();
            // != CarteTarot.petit()
        }
        return leads();
    }

    private CardTarot leads() {
        boolean carteMaitresse_;
    /*
    Il n'est pas probable qu'un joueur de non confiance
    n'ayant pas joue coupe le pli
    */
        if (carteForte.getId().getCouleur() == Suit.TRUMP) {
            /*
        Si le pli est deja
        coupe
        */
            if (confidentPlayers.containsObj(ramasseurVirtuel)) {
                return atoutLePlusPetit(suitesAtJouables,
                        playableCards.contient(CardTarot.excuse()));
            }
            return atoutLePlusPetit(suitesAtJouables);
        }
        CardTarot carteHautePasAtout_;
        carteHautePasAtout_ = doneTrickInfo.getProgressingTrick().getCartes().couleurs()
                .getVal(couleurDemandee).premiereCarte();
        if (!carteHautePasAtout_.isCharacter()) {
            carteMaitresse_ = nePeutAvoirFigures();
            if (carteMaitresse_) {
                return atoutLePlusPetit(suitesAtJouables,
                        playableCards.contient(CardTarot.excuse()));
            }
        } else if (confidentPlayers.containsObj(ramasseurVirtuel)) {
            carteMaitresse_ = nePeutMonter();
            if (carteMaitresse_) {
                return atoutLePlusPetit(suitesAtJouables,
                        playableCards.contient(CardTarot.excuse()));
            }
        }
        return atoutLePlusPetit(suitesAtJouables);
    }

    private boolean nePeutMonter() {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        for (int joueur_ : notConfidentPlayersNotPlay) {
            if(cartesPossibles
                    .getVal(couleurDemandee)
                    .get(joueur_).estVide()) {
                continue;
            }
            if (cartesPossibles.getVal(couleurDemandee).get(joueur_).premiereCarte().strength(couleurDemandee) >= carteForte.strength(couleurDemandee)) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }

    private boolean nePeutAvoirFigures() {
        boolean carteMaitresse_;
        carteMaitresse_ = true;
        for (int joueur_ : notConfidentPlayersNotPlay) {
            if (!GameTarotTrickHypothesis.nePeutAvoirFigures(cartesPossibles, joueur_, couleurDemandee)) {
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

    CardTarot underTrumpFoe() {
        return atoutLePlusPetit(
                suitesAtJouables, playableCards.contient(CardTarot.excuse()));
    }

    CardTarot underTrumpPartners() {
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        /* couleur demandee atout */
        //jeu obligatoire d'atout
        if (!repartitionAtJouables.contient(CardTarot.petit())
                || maitreJeu) {
            return atoutLePlusPetit(suitesAtJouables, playableCards.contient(CardTarot.excuse()));
        }
        boolean carteMaitresse_ = true;
        Ints joueursNonConfiance_ = teamsRelation.joueursNonConfiance(currentPlayer,GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        for (int joueur_ : joueursNonConfiance_) {
            if (!cartesPossibles.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                carteMaitresse_ = false;
            }
        }
        if (carteMaitresse_) {
            return atoutLePlusPetit(suitesAtJouables);
        }
        return CardTarot.petit();
    }

    CardTarot underTrump() {
        if (repartitionAtJouables.premiereCarte().strength(couleurDemandee)< carteForte
                .strength(couleurDemandee)) {
            /*
                Si le
                joueur ne
                peut pas
                surcouper
                */
            if (!repartitionAtJouables.contient(CardTarot.petit())) {
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
                return atoutLePlusPetit(suitesAtJouables, playableCards.contient(CardTarot.excuse()));
            }
            if (confidentPlayers.containsObj(ramasseurVirtuel)) {
                return repartitionAtJouables.derniereCarte();
            }
            return atoutLePlusPetit(suitesAtJouables, playableCards.contient(CardTarot.excuse()));
        }
        return CardTarot.WHITE;
    }
    CardTarot defausseCouleurOrdinaireClassique() {
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(tarotInfoPliEnCours);
        //defausse sur une couleur ordinaire
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return discardOnFoe();
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return discardTeamSuit();
        }
        return defaultDiscardSuit();

    }

    CardTarot defaultDiscardSuit() {
        IdList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, Suit.couleursOrdinaires());
        //incertitude du ramasseur a la couleur demandee (defausse sur la couleur demandee ordinaire)
        IdList<Suit> couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(suitesTouteCouleurJouable,
                    couleurs_, playableCards,
                    repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(suitesTouteCouleurJouable,
                couleursNonVides_, playableCards, repartitionCartesJouees);
    }

    CardTarot discardTeamSuit() {
        return defausseCouleurDemandeeSurPartenaire(
                suitesTouteCouleurJouable, repartitionCartesJouees,
                playableCards, cartesMaitresses,
                couleursStrictesMaitresses, couleurDemandee);
    }

//    CardTarot playIfPartnersWin() {
//        Bytes equipeNumero_ = equipeNumero();
//        if (GameTarotTeamsRelation.contientJoueurs(equipeNumero_, ramasseurs)) {
//            HandTarot noExc_ = noExc();
//            return noExc_.premiereCarte();
//        }
//        return CardTarot.excuse();
//    }

    private HandTarot noExc() {
        HandTarot noExc_ = new HandTarot();
        noExc_.ajouterCartes(playableCards);
        noExc_.removeCardIfPresent(CardTarot.EXCUSE);
        return noExc_;
    }

    CardTarot defausseAtoutClassique() {
        //defausse sur l'atout
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(tarotInfoPliEnCours);
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return discardOnFoe();
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return discardOnTeamTrump();
        }
        return defaultDiscard();
    }

    CardTarot defaultDiscard() {
        return defaultDiscardSuit();
    }

    CardTarot discardOnTeamTrump() {
        return defausseAtoutSurPartenaire(suitesTouteCouleurJouable,
                repartitionCartesJouees, playableCards,
                cartesMaitresses, couleursStrictesMaitresses);
    }

    CardTarot discardOnFoe() {
        return discardFoe(suitesTouteCouleurJouable,repartitionCartesJouees, playableCards);
    }
    TarotInfoPliEnCours initInformations() {
        return tarotInfoPliEnCours;
    }

    CardTarot tryPlayExcuseOrLead() {
        if (hasExcPlusCard(currentHand) && GameTarotTrickHypothesis.equipeQuiVaFairePli(tarotInfoPliEnCours) == PossibleTrickWinner.TEAM) {
            Ints equipeNumero_ = equipeNumero();
            if (GameTarotTeamsRelation.contientJoueurs(equipeNumero_, ramasseurs)) {
                HandTarot noExc_ = noExc();
                return noExc_.premiereCarte();
            }
        }
        return tryPlayExcuse(maitreJeu,playableCards);
    }

    private Ints equipeNumero() {
        Ints equipeNumero_ = new Ints();
        equipeNumero_.addAllElts(confidentPlayers);
        equipeNumero_.add(currentPlayer);
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
            Ints _joueursNonConfianceNonJoue, Suit _couleurDemandee,
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles, CardTarot _carteForte) {
        boolean bat_ = true;
        for (int joueur_ : _joueursNonConfianceNonJoue) {
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
            Ints _joueursNonConfianceNonJoue) {
        boolean carteMaitresse_ = true;
        for (int joueur_ : _joueursNonConfianceNonJoue) {
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
            Ints _joueursNonConfianceNonJoue) {
        int max_ = 0;
        for (int joueur_ : _joueursNonConfianceNonJoue) {
            if (_cartesPossibles.getVal(_couleurDemandee)
                    .get(joueur_).estVide()) {
                if (!_cartesPossibles.getVal(Suit.TRUMP)
                        .get(joueur_).estVide()) {
                    return false;
                }
                continue;
            }
            max_ = NumberUtil.max(
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
            Ints _joueursNonConfianceNonJoue) {
        boolean carteMaitresse_ = true;
        for (int joueur_ : _joueursNonConfianceNonJoue) {
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
//    static boolean premiereSuitePlusLongueQueTotalCouleurDemandee(
//            CustList<HandTarot> _suites,
//            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
//            Suit _couleurDemandee,
//            Bytes _joueurs) {
//        if(!_suites.first().premiereCarte().isCharacter()) {
//            return false;
//        }
//        //il existe une suite de la couleur demandee ordinaire avec une figure
//        byte max_ = 0;
//        for (byte joueur_ : _joueurs) {
//            max_ = (byte) NumberUtil.max(
//                    _cartesPossibles
//                            .getVal(_couleurDemandee)
//                            .get(joueur_).total(),
//                    max_);
//        }
//        return _suites.first().total() > max_;
//    }
    /**
     Couleur demand&eacute;e atout: retourne vrai si et seulement si le joueur
     numero peut ramasser le pli en jouant son plus grand atout
     */
    static boolean peutRamasserDemandeAtoutNonMaitre(
            IdMap<Suit, CustList<HandTarot>> _cartesPossibles,
            IdMap<Suit, CustList<HandTarot>> _cartesCertaines,
            int _maxAtoutNumero, Ints _joueursNonJoue, Ints _joueursJoue) {
        boolean existe_ = false;
        CustList<HandTarot> atoutsCertains_ = _cartesCertaines.getVal(Suit.TRUMP);
        for (int joueur_ : _joueursNonJoue) {
            if(atoutsCertains_.get(joueur_).estVide()) {
                continue;
            }
            if (atoutsCertains_.get(joueur_).premiereCarte().strength(Suit.TRUMP) > _maxAtoutNumero) {
                existe_ = true;
            }
        }
        if (existe_) {
            return false;
        }
        return peutRamasserDemandeAtoutNonMaitre(_cartesPossibles, _joueursNonJoue, _joueursJoue);
    }

    private static boolean peutRamasserDemandeAtoutNonMaitre(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Ints _joueursNonJoue, Ints _joueursJoue) {
        boolean existe_ = false;
        CustList<HandTarot> atoutsPossibles_ = _cartesPossibles.getVal(Suit.TRUMP);
        for (int joueur_ : _joueursNonJoue) {
            if(atoutsPossibles_.get(joueur_).estVide()) {
                continue;
            }
            int maxAtoutJoueurNonJoue_ = atoutsPossibles_.get(joueur_).premiereCarte()
                    .strength(Suit.TRUMP);
            boolean local_ = true;
            for (int joueur2_ : _joueursJoue) {
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
            CustList<HandTarot> _suitesDemJouables, CustList<HandTarot> _cartesRelMaitresDemJouables,
            Ints _joueursDeNonConfianceNonJoue,
            Suit _couleurDemandee) {
        boolean pasFigure_ = true;
        for (int joueur_ : _joueursDeNonConfianceNonJoue) {
            if (!_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide() && _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().isCharacter()) {
                pasFigure_ = false;
            }
        }
        if (!_cartesRelMaitresDemJouables.isEmpty()) {
            if (_cartesRelMaitresDemJouables.size() == 1) {
                return _suitesDemJouables.first().premiereCarte();
            }
            //cartesRelMaitres.size() > 1 && cartesRelMaitres.get(1).premiereCarte().estUneFigure()
            return _cartesRelMaitresDemJouables.last().premiereCarte();
        }
        if (pasFigure_) {
            return jeuFigureHauteDePlusFaibleSuite(_suitesDemJouables);
        }
        return weakestCard(_suitesDemJouables);
    }

    /**
     Renvoie la plus grande carte basse (n'etant pas une figure) a jouer dans
     la suite la plus faible de la couleur
     */
    static CardTarot carteLaPlusPetite(CustList<HandTarot> _suitesCouleursJouables) {
        CustList<HandTarot> suitesFiguresJouable_ = getCharacterSeq(_suitesCouleursJouables);
        CustList<HandTarot> suitesCartesBassesJouable_ = getLowCardSeq(_suitesCouleursJouables);
        if (!suitesCartesBassesJouable_.isEmpty()) {
            return suitesCartesBassesJouable_.last().premiereCarte();
        }
        return suitesFiguresJouable_.last().derniereCarte();
    }

    /**
     Renvoie la plus grande carte basse (n'etant pas une figure) a jouer dans
     la suite la plus faible de la couleur
     */
    static CardTarot weakestCard(CustList<HandTarot> _suitesDemJouables) {
        CustList<HandTarot> suitesFiguresJouable_ = getCharacterSeq(_suitesDemJouables);
        CustList<HandTarot> suitesCartesBassesJouable_ = getLowCardSeq(_suitesDemJouables);
        if (!suitesCartesBassesJouable_.isEmpty()) {
            return suitesCartesBassesJouable_.last().premiereCarte();
        }
        return suitesFiguresJouable_.last().premiereCarte();
    }
    /**
     Renvoie la figure la plus grande dans la suite de figure la moins
     haute(en valeur)
     */
    static CardTarot jeuFigureHauteDePlusFaibleSuite(
            CustList<HandTarot> _suitesDemJouables) {
        CustList<HandTarot> suitesFiguresJouable_ = getCharacterSeq(_suitesDemJouables);
        CustList<HandTarot> suitesCartesBassesJouable_ = getLowCardSeq(_suitesDemJouables);
        if (!suitesFiguresJouable_.isEmpty()) {
            return suitesFiguresJouable_.last().premiereCarte();
        }
        return suitesCartesBassesJouable_.last().premiereCarte();

    }

    static CustList<HandTarot> getCharacterSeq(CustList<HandTarot> _suitesJouable) {
        CustList<HandTarot> suitesFiguresJouable_ = new CustList<HandTarot>();
        for(HandTarot m: _suitesJouable) {
            int nbFigures_ = m.nombreDeFigures();
            if(nbFigures_ == m.total()) {
                suitesFiguresJouable_.add(m);
                continue;
            }
            if (nbFigures_ != 0) {
                //nbFigures > 0 && nbFigures < m.total()
                //==> m.total() >= 2 && !suiteFigures.isEmpty() && !suiteCartesBasses.isEmpty()
                HandTarot suiteFigures_ = suiteFigures(m);
                suitesFiguresJouable_.add(suiteFigures_);
            }
        }
        return suitesFiguresJouable_;
    }

    private static HandTarot suiteFigures(HandTarot _mainJouable) {
        HandTarot suiteFiguresJouable_ = new HandTarot();
        for(CardTarot c: _mainJouable) {
            if(c.isCharacter()) {
                suiteFiguresJouable_.ajouter(c);
            }
        }
        return suiteFiguresJouable_;
    }

    static CustList<HandTarot> getLowCardSeq(CustList<HandTarot> _suitesJouable) {
        CustList<HandTarot> suitesCartesBassesJouable_ = new CustList<HandTarot>();
        for(HandTarot m: _suitesJouable) {
            int nbFigures_ = m.nombreDeFigures();
            if (nbFigures_ != m.total()) {
                if (nbFigures_ == 0) {
                    suitesCartesBassesJouable_.add(m);
                    continue;
                }
                //nbFigures > 0 && nbFigures < m.total()
                //==> m.total() >= 2 && !suiteFigures.isEmpty() && !suiteCartesBasses.isEmpty()=
                HandTarot suiteCartesBasses_ = suiteCartesBasses(m);
                suitesCartesBassesJouable_.add(suiteCartesBasses_);
            }
        }
        return suitesCartesBassesJouable_;
    }

    private static HandTarot suiteCartesBasses(HandTarot _mainJouable) {
        HandTarot suiteCartesBassesJouable_ = new HandTarot();
        for(CardTarot c: _mainJouable) {
            if(c.isCharacter()) {
                continue;
            }
            suiteCartesBassesJouable_.ajouter(c);
        }
        return suiteCartesBassesJouable_;
    }

    /**Methode retournant un atout different du Petit sauf si ce dernier est sec.*/
    static CardTarot atoutLePlusPetit(CustList<HandTarot> _suitesAtJouables) {
        if (_suitesAtJouables.size() == 1) {
            return _suitesAtJouables.first().premiereCarte();
        }
        if (!_suitesAtJouables.last().contient(CardTarot.petit())) {
            return _suitesAtJouables.last().premiereCarte();
        }
        /* Le joueur a le Petit maintenant */
        //Petit sec ==> suites.size() == 1 && suites.last().total() == 1
        //Or nombre d'atout >= 2, donc suites.last().total() == 1 ==> suites.size() >= 2
        if (_suitesAtJouables.last().total() == 1) {
            //Petit seul dans sa suite
            return _suitesAtJouables.get(_suitesAtJouables.size() - 2).premiereCarte();
        }
        return _suitesAtJouables.last().premiereCarte();
        //Petit accompagne d'un atout dans la meme suite
    }

    /**
     Retourne l'atout le plus grand dans la suite la plus faible si l'Excuse
     n'est pas possedee et le Petit n'est pas dans la main; ou l'Excuse si
     elle est possedee
     @throws RuntimeException nombre d'atout < 2 et !contientExcuse et suites.last().contient(CarteTarot.petit())
     (le Petit est sec)
     */
    static CardTarot atoutLePlusPetit(CustList<HandTarot> _suitesAtJouables,
                                              boolean _contientExcuse) {
        if (_contientExcuse) {
            return CardTarot.excuse();
        }
        /* Le joueur n'a pas l'Excuse */
        return atoutLePlusPetit(_suitesAtJouables);
    }

    static CardTarot discardFoe(IdMap<Suit, CustList<HandTarot>> _suitesTouteCouleurJouable, IdMap<Suit, HandTarot> _repartitionCartesJouees, HandTarot _playableCards) {
        IdList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_playableCards, Suit.couleursOrdinaires());
        IdList<Suit> couleursSansFigure_ = GameTarotCommon.couleursSansFigures(_playableCards, couleursNonVides_);
        if (!couleursSansFigure_.isEmpty()) {
            return jouerPetiteCarteDefausse(_suitesTouteCouleurJouable, couleursSansFigure_,
                    _playableCards, _repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(_suitesTouteCouleurJouable, couleursNonVides_,
                _playableCards, _repartitionCartesJouees);
    }

    private static CardTarot jeuPetiteCarteCouleurFigure(
            IdMap<Suit,CustList<HandTarot>> _suitesTouteCouleurJouable, IdList<Suit> _couleursNonVides,
            HandTarot _playableCards,
            IdMap<Suit,HandTarot> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursAvecLaPlusPetiteCarteBasse(_playableCards, _couleursNonVides);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_playableCards, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(_playableCards, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees),couleurs_);
        return carteLaPlusPetite(_suitesTouteCouleurJouable.getVal(couleurs_.first()));
    }

    static CardTarot defausseAtoutSurPartenaire(
            IdMap<Suit,CustList<HandTarot>> _suitesTouteCouleurJouable,
            IdMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _playableCards, IdMap<Suit,HandTarot> _cartesMaitresses,
            IdList<Suit> _couleursStrictementMaitresses) {
        boolean carteMaitresse_ = _couleursStrictementMaitresses.size() == Suit.couleursOrdinaires().size();
        if (_repartitionCartesJouees.getVal(Suit.TRUMP).total() > 17) {
            IdMap<Suit,HandTarot> repartition_=_playableCards.couleurs();
            if (carteMaitresse_) {
                IdList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_playableCards, Suit.couleursOrdinaires());
                if (leadingCard(_cartesMaitresses, _couleursStrictementMaitresses, repartition_)) {
                    IdList<Suit> couleursFigures_ = GameTarotCommon.couleursAvecFigures(_playableCards, couleursNonVides_);
                    if (!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(
                                couleursFigures_, _playableCards);
                    }
                }
            }
            /*
            Il peut exister une couleur pour se defausser non strictement
            maitresse
            */
            return discardOptimPartner(_suitesTouteCouleurJouable, _repartitionCartesJouees, _playableCards, _cartesMaitresses);
        }
        /*
        Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur
        demandee sont jouees
        */
        return discardCardPartner(_suitesTouteCouleurJouable, _repartitionCartesJouees, _playableCards, _cartesMaitresses, _couleursStrictementMaitresses, carteMaitresse_);
    }

    static CardTarot defausseCouleurDemandeeSurPartenaire(
            IdMap<Suit,CustList<HandTarot>> _suitesTouteCouleurJouable,
            IdMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _playableCards, IdMap<Suit,HandTarot> _cartesMaitresses,
            IdList<Suit> _couleursStrictementMaitresses, Suit _couleurDemandee) {
        IdList<Suit> couleursAutreQueDemandee_ = new IdList<Suit>();
        for(Suit c: Suit.couleursOrdinaires()) {
            if(c == _couleurDemandee) {
                continue;
            }
            couleursAutreQueDemandee_.add(c);
        }
        boolean carteMaitresse_ = _couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandee_);
        if (_repartitionCartesJouees.getVal(Suit.TRUMP).total() > 19
                && _repartitionCartesJouees.getVal(_couleurDemandee).total() > 12) {
            IdMap<Suit,HandTarot> repartition_=_playableCards.couleurs();
            if (carteMaitresse_) {
                IdList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_playableCards, Suit.couleursOrdinaires());
                if (leadingCard(_cartesMaitresses, _couleursStrictementMaitresses, repartition_)) {
                    IdList<Suit> couleursFigures_ = GameTarotCommon.couleursAvecFigures(_playableCards, couleursNonVides_);
                    if (!couleursFigures_.isEmpty()) {
                        couleursFigures_ = GameTarotCommon.couleursLesPlusLongues(_playableCards,couleursFigures_);
                        return jeuGrandeCarteDefausseMaitre(
                                couleursFigures_, _playableCards);
                    }
                }
            }
            /*
            Il peut exister une couleur pour se defausser non strictement
            maitresse
            */
            return discardOptimPartner(_suitesTouteCouleurJouable, _repartitionCartesJouees, _playableCards, _cartesMaitresses);
        }
        /*
        Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur
        demandee sont jouees
        */
        return discardCardPartner(_suitesTouteCouleurJouable, _repartitionCartesJouees, _playableCards, _cartesMaitresses, _couleursStrictementMaitresses, carteMaitresse_);
    }

    static CardTarot discardOptimPartner(IdMap<Suit, CustList<HandTarot>> _suitesTouteCouleurJouable,
                                         IdMap<Suit, HandTarot> _repartitionCartesJouees,
                                         HandTarot _playableCards, IdMap<Suit, HandTarot> _cartesMaitresses) {
        IdList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_playableCards, Suit.couleursOrdinaires());
        IdList<Suit> couleurs_ = GameTarotCommonPlaying.couleursAvecCarteNonMaitresse(_playableCards,
                Suit.couleursOrdinaires(), _cartesMaitresses);
        if (!couleurs_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_suitesTouteCouleurJouable,
                    _cartesMaitresses, _playableCards, couleurs_);
        }
        couleurs_ = GameTarotCommon.couleursAvecFigures(_playableCards, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playableCards,couleurs_);
            return sauverFiguresDefausse(couleurs_, _playableCards,
                    _repartitionCartesJouees);
        }
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playableCards,couleursNonVides_);
        return jouerPetiteCarteDefausse(_suitesTouteCouleurJouable, couleurs_,
                _playableCards, _repartitionCartesJouees);
    }

    static CardTarot discardCardPartner(IdMap<Suit, CustList<HandTarot>> _suitesTouteCouleurJouable,
                                        IdMap<Suit, HandTarot> _repartitionCartesJouees, HandTarot _playableCards,
                                        IdMap<Suit, HandTarot> _cartesMaitresses, IdList<Suit> _couleursStrictementMaitresses,
                                        boolean _carteMaitresse) {
        IdMap<Suit,HandTarot> repartition_=_playableCards.couleurs();
        IdList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_playableCards, Suit.couleursOrdinaires());
        if (_carteMaitresse && leadingCard(_cartesMaitresses, _couleursStrictementMaitresses, repartition_)) {
            IdList<Suit> couleursFigures_ = GameTarotCommon.couleursAvecFigures(_playableCards, couleursNonVides_);
            if (!couleursFigures_.isEmpty()) {
                return jeuGrandeCarteDefausseMaitre(couleursFigures_,
                        _playableCards);
            }
        }
        IdList<Suit> couleurs_ = GameTarotCommon.couleursAvecFigures(_playableCards, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_, _playableCards,
                    _repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(_suitesTouteCouleurJouable, couleursNonVides_,
                _playableCards, _repartitionCartesJouees);
    }

    static boolean leadingCard(IdMap<Suit, HandTarot> _cartesMaitresses, IdList<Suit> _couleursStrictementMaitresses, IdMap<Suit, HandTarot> _repartition) {
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
            IdList<Suit> _couleursFiguresNonVide, HandTarot _playableCards) {
        IdList<Suit> couleursAvecFigures_ = GameTarotCommon.couleursAvecFigures(_playableCards, _couleursFiguresNonVide);
        IdList<Suit> couleurs_ = _couleursFiguresNonVide;
        if(!couleursAvecFigures_.isEmpty()) {
            couleurs_ = couleursAvecFigures_;
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playableCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(_playableCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursAvecLePlusGrandNbFigures(_playableCards, couleurs_);
            return _playableCards.couleur(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playableCards, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(_playableCards, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusGrandNbFigures(_playableCards, couleurs_);
        return _playableCards.couleur(couleurs_.first()).derniereCarte();
    }

    static CardTarot sauverFiguresDefausse(
            IdList<Suit> _couleursFiguresNonVide, HandTarot _playableCards,
            IdMap<Suit,HandTarot> _repartitionCartesJouees) {
        IdList<Suit> couleursAvecFigures_ = GameTarotCommon.couleursAvecFigures(_playableCards, _couleursFiguresNonVide);
        IdList<Suit> couleurs_ = _couleursFiguresNonVide;
        if(!couleursAvecFigures_.isEmpty()) {
            couleurs_ = couleursAvecFigures_;
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_playableCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(_playableCards, couleurs_);
            return _playableCards.couleur(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_playableCards, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(_playableCards, couleurs_);
        return _playableCards.couleur(couleurs_.first()).derniereCarte();
    }

    static CardTarot jeuPetiteDefausseMaitre(
            IdMap<Suit,CustList<HandTarot>> _suitesTouteCouleurJouable,
            IdMap<Suit,HandTarot> _cartesMaitresses,
            HandTarot _playableCards,
            IdList<Suit> _couleursNonVides) {
        IdList<Suit> couleursSansFigures_ = GameTarotCommon.couleursSansFigures(_playableCards, _couleursNonVides);
        IdList<Suit> couleurs_ = _couleursNonVides;
        if(!couleursSansFigures_.isEmpty()) {
            couleurs_ = couleursSansFigures_;
        }
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_playableCards, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_cartesMaitresses),couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(_playableCards, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(_playableCards, couleurs_);
        return carteLaPlusPetite(_suitesTouteCouleurJouable.getVal(couleurs_.first()));
    }

    static CardTarot jouerPetiteCarteDefausse(
            IdMap<Suit,CustList<HandTarot>> _suitesTouteCouleurJouable, IdList<Suit> _couleursNonVides,
            HandTarot _playableCards,
            IdMap<Suit,HandTarot> _repartitionCartesJouees) {
        IdList<Suit> couleursSansFigures_ = GameTarotCommon.couleursSansFigures(_playableCards, _couleursNonVides);
        IdList<Suit> couleurs_ = _couleursNonVides;
        if(!couleursSansFigures_.isEmpty()) {
            couleurs_ = couleursSansFigures_;

        }
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_playableCards, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees),couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(_playableCards, couleurs_);
        return carteLaPlusPetite(_suitesTouteCouleurJouable.getVal(couleurs_.first()));
    }

    public Role getCurrentStatus() {
        return currentStatus;
    }
}
