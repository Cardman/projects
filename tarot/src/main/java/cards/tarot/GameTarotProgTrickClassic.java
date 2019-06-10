package cards.tarot;

import cards.consts.CardChar;
import cards.consts.PossibleTrickWinner;
import cards.consts.Status;
import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.PlayingDog;
import code.util.*;

public final class GameTarotProgTrickClassic {

    private GameTarotTeamsRelation teamsRelation;

    private GameTarotTrickInfo doneTrickInfo;
    private HandTarot calledCards;

    private HandTarot currentHand;
    private GameTarotCommonPlaying common;
    private byte starter;
    private BidTarot bid;
    private Status currentStatus;

    public GameTarotProgTrickClassic(GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
                                     HandTarot _calledCards, HandTarot _currentHand,
                                     byte _starter, BidTarot _bid) {
        doneTrickInfo = _done;
        teamsRelation = _teamsRelation;
        calledCards = _calledCards;
        currentHand = _currentHand;
        common = new GameTarotCommonPlaying(_done,_teamsRelation);
        starter = _starter;
        bid = _bid;
        currentStatus = _teamsRelation.statutDe(_done.getProgressingTrick().getNextPlayer(_teamsRelation.getNombreDeJoueurs()));
    }
    CardTarot enCoursClassic(
            HandTarot _lastHand) {
        byte nombreJoueurs_ = teamsRelation.getNombreDeJoueurs();
        byte numero_ = doneTrickInfo.getProgressingTrick().getNextPlayer(nombreJoueurs_);
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot cartesJouables_ = common.cartesJouables(calledCards, repartition_);
        if (cartesJouables_.total() == 1) {
            return cartesJouables_.premiereCarte();
        }
        if (doneTrickInfo.getProgressingTrick().couleurDemandee() == Suit.UNDEFINED) {
            //cartesJouables.total() > 1
            GameTarotBeginTrickClassic g_ = new GameTarotBeginTrickClassic(doneTrickInfo,teamsRelation,calledCards,currentHand,numero_);
            if (teamsRelation.existePreneur()) {
                return g_.entameSurExcuseClassique(_lastHand,cartesJouables_,starter);
                /* Variables locales avec jeu d'equipe */
            }
            return g_.entameClassique(_lastHand, cartesJouables_);
        }
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        EnumMap<Suit,HandTarot> repartitionJouables_ = cartesJouables_.couleurs();
        if (Suit.couleursOrdinaires().containsObj(couleurDemandee_)) {
            if (!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                return fournirCouleurOrdinaireClassique(_lastHand, cartesJouables_);
            }
            if (!repartitionJouables_.getVal(Suit.TRUMP).estVide()) {
                return coupeClassique(_lastHand, cartesJouables_);
            }
            return defausseCouleurOrdinaireClassique(_lastHand, cartesJouables_);
        }
        if (!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
            return fournirAtoutClassique(_lastHand, cartesJouables_);
        }
        return defausseAtoutClassique(_lastHand, cartesJouables_);
    }
    private CardTarot fournirCouleurOrdinaireClassique(
            HandTarot _lastHand,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = common.initInformations(_lastHand, currentHand,_cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = info_.getCartesMaitresses();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _cartesJouables.couleurs();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot repartitionCouleDem_ = repartitionJouables_.getVal(couleurDemandee_);
        EqList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        boolean carteAppeleeJouee_ = info_.isCarteAppeleeJouee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesChien_ = common.cartesVuesAuChien();
        if (currentStatus == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        } else if (currentStatus == Status.TAKER) {
            cartesFictives_.ajouterCartes(calledCards);
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*CarteTarot temporairement
        maitresse*/
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                info_,
                currentStatus == Status.DEFENDER,
                carteForte_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (maitreJeu_ && contientExcuse_) {
                return CardTarot.excuse();
            }
            if (currentHand.total() == 2 && contientExcuse_) {
                return CardTarot.excuse();
            }
            return carteLaPlusPetite(suites_);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            if (maitreJeu_) {
                if (contientExcuse_) {
                    return CardTarot.excuse();
                }
                if (suites_.size() == 1) {
                    return repartitionCouleDem_.premiereCarte();
                }
                if (premiereSuitePlusLongueQueTotalCouleurDemandee(
                        suites_,cartesPossibles_,couleurDemandee_,teamsRelation.autresJoueurs(info_.getCurrentPlayer()))) {
                    return repartitionCouleDem_.premiereCarte();
                }
                return weakestCard(suites_);
            }
            if (currentHand.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
                Numbers<Byte> equipeNumero_ = new Numbers<Byte>();
                equipeNumero_.addAllElts(joueursConfiance_);
                equipeNumero_.add(info_.getCurrentPlayer());
                if (equipeNumero_.containsAllObj(GameTarotCommonPlaying.ramasseurs(plisFaits_))) {
                    if (CardTarot.eq(_cartesJouables.premiereCarte(),
                            CardTarot.excuse())) {
                        return _cartesJouables.carte(1);
                    }
                    return _cartesJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            /* couleur demandee non atout */
            if (repartitionCouleDem_.premiereCarte().isCharacter()) {
                return repartitionCouleDem_.premiereCarte();
            }
            return weakestCard(suites_);
        }
        //incertitude
        if (currentHand.total() == 2 && contientExcuse_) {
            return CardTarot.excuse();
        }
        TrickTarot dernierPli_;
        Numbers<Byte> dernieresCoupes_;
        Numbers<Byte> dernieresDefausses_;
        Numbers<Byte> joueursSusceptiblesDeCouper_;
        EqList<HandTarot> cartesRelMaitres_;
        Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_,joueursNonJoue_);

        //fournir a la couleur demandee ordinaire
        Numbers<Byte> tours_ = GameTarotCommonPlaying.tours(couleurDemandee_, plisFaits_);
        boolean joueurConfianceRamasseur_ = joueursConfiance_.containsObj(ramasseurVirtuel_);
        boolean joueurConfianceRamasseurProbaPli_ = joueurConfianceRamasseur_ &&
                joueurConfianceRamasseurProbaPli(
                        joueursNonConfianceNonJoue_,
                        couleurDemandee_,
                        cartesPossibles_,
                        carteForte_);
        if (repartitionCouleDem_.premiereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            /* Si le joueur ne peut pas prendre la main */
            if (!repartitionCouleDem_.premiereCarte().isCharacter()) {
                /*Si le joueur
                ne possede pas
                de figure*/
                if (contientExcuse_ && maitreJeu_) {
                    return CardTarot.excuse();
                }
                return carteLaPlusPetite(suites_);
            }
            if (maitreJeu_) {
                if (contientExcuse_) {
                    return CardTarot.excuse();
                }
                return carteLaPlusPetite(suites_);
            }
            /* Le joueur possede au moins une figure */
            if (tours_.isEmpty()) {
                /*
            Si cette couleur est entamee pour
            la premiere fois
            */
                if (joueurConfianceRamasseur_) {
                    if(aucunePriseMainPossibleCouleur(
                            cartesPossibles_,couleurDemandee_,
                            carteForte_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                }
                return carteLaPlusPetite(suites_);
            }
            dernierPli_ = plisFaits_.get(tours_.last());
            dernieresCoupes_ = dernierPli_.joueursCoupes();
            /* Maintenant on aborde au moins le deuxieme tour */
            if (dernieresCoupes_.isEmpty()) {
                /*
            Si le dernier pli
            n'est pas coupe a
            cette couleur
            */
                if (joueurConfianceRamasseur_) {
                    if (carteForte_.couleur() == Suit.TRUMP) {
                        /*
                    L'espoir fait
                    vivre
                    */
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    if(aucunePriseMainPossibleCouleur(
                            cartesPossibles_,couleurDemandee_,
                            carteForte_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                }
                return carteLaPlusPetite(suites_);
            }
            /*Maintenant on sait qu'au dernier tour le pli a ete coupe*/
            if (joueursConfiance_.containsObj(GameTarotCommonPlaying.ramasseur(plisFaits_,
                    tours_.last()))) {
                if (carteForte_.couleur() == Suit.TRUMP) {
                    /*L'espoir fait vivre*/
                    return repartitionCouleDem_.premiereCarte();
                }
                if(aucunePriseMainPossibleCouleur(
                        cartesPossibles_,couleurDemandee_,
                        carteForte_,joueursNonConfianceNonJoue_)) {
                    return repartitionCouleDem_
                            .premiereCarte();
                }
            }
            return carteLaPlusPetite(suites_);
        }
        /* Maintenant on sait le joueur peut prendre la main */
        cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                couleurDemandee_, couleurDemandee_, cartesCertaines_,
                carteForte_);
        if (!repartitionCouleDem_.premiereCarte().isCharacter()) {
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            if (aucunePriseMainPossibleParFigure(
                    cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
                if (!cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
            }
            if (tours_.isEmpty()) {
                return repartitionCouleDem_.premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        /* Maintenant le joueur peut prendre la main avec une figure */
        if (info_.getCurrentPlayer() == teamsRelation.getTaker() || !teamsRelation.existePreneur()) {
            if (tours_.isEmpty()) {
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
                                cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)
                                && (!carteForte_.isCharacter() || joueurConfianceRamasseur_)) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (carteForte_.isCharacter()) {
                            if (suites_.size() == 1
                                    || !suites_.get(1).premiereCarte().isCharacter()
                                    || !joueurConfianceRamasseurProbaPli_) {
                                return repartitionCouleDem_
                                        .premiereCarte();
                            }
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (joueurConfianceRamasseurProbaPli_) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    /* Le joueur n'a aucune cartes maitresses */
                    if (aucunePriseMainPossibleParFigure(
                            cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
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
            /* C'est au moins le deuxieme tour */
            dernierPli_ = plisFaits_.get(tours_.last());
            dernieresDefausses_ = dernierPli_.joueursDefausses(nombreDeJoueurs_);
            joueursSusceptiblesDeCouper_ = GameTarotTrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles_,couleurDemandee_,joueursNonJoue_);
            if (!joueursSusceptiblesDeCouper_.isEmpty()) {
                if (!GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_ && contientExcuse_) {
                        return CardTarot.excuse();
                    }
                    return carteLaPlusPetite(suites_);
                }
                if (!GameTarotTeamsRelation.intersectionJoueurs(joueursConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_) {
                        if (contientExcuse_) {
                            return CardTarot.excuse();
                        }
                        if (premiereSuitePlusLongueQueTotalCouleurDemandee(
                                suites_,cartesPossibles_,couleurDemandee_,teamsRelation.autresJoueurs(info_.getCurrentPlayer()))) {
                            return repartitionCouleDem_
                                    .premiereCarte();
                        }
                        return carteLaPlusPetite(suites_);
                    }
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
                if (joueursConfiance_.containsObj(GameTarotCommonPlaying.ramasseur(plisFaits_,
                        tours_.last()))) {
                    if (carteForte_.couleur() == Suit.TRUMP) {
                        /*L'espoir fait vivre*/
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    if(aucunePriseMainPossibleCouleur(
                            cartesPossibles_,couleurDemandee_,
                            carteForte_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                }
                return carteLaPlusPetite(suites_);
            }
            /* Si la coupe semble improbable */
            if (!dernieresDefausses_.isEmpty() && tours_.size() == 1) {
                if (canLeadTrick(maitreJeu_,cartesRelMaitres_)) {
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
                                cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)
                                && (!carteForte_.isCharacter() || joueurConfianceRamasseur_)) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (carteForte_.isCharacter()) {
                            if (suites_.size() == 1
                                    || !suites_.get(1).premiereCarte().isCharacter()
                                    || !joueurConfianceRamasseurProbaPli_) {
                                return repartitionCouleDem_
                                        .premiereCarte();
                            }
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (!teamsRelation.existePreneur()) {
                            return repartitionCouleDem_.premiereCarte();
                        }
                        if (joueurConfianceRamasseurProbaPli_) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                    }
                    /* Le joueur n'a aucune cartes maitresses */
                    if (aucunePriseMainPossibleParFigure(
                            cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
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
            if (!cartesMaitresses_.getVal(couleurDemandee_)
                    .estVide()) {
                return repartitionCouleDem_.premiereCarte();
            }
            if (!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        /* Appele */
        if (currentStatus == Status.CALLED_PLAYER) {
            if (tours_.isEmpty()) {
                if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
                HandTarot cartesChienCouleur_ = _lastHand.couleurs()
                        .getVal(couleurDemandee_);
                if (joueursNonJoue_.containsObj(teamsRelation.getTaker())
                        && bid.getJeuChien() == PlayingDog.WITH
                        && cartesChienCouleur_
                        .nombreDeFigures() > 0) {
                        /*Si l'appele joue
                        avant le preneur*/
                    if(peutSauverFigureAppele(
                            cartesPossibles_,
                            couleurDemandee_,
                            cartesChienCouleur_,
                            joueursNonConfianceNonJoue_,
                            repartitionCouleDem_
                                    .premiereCarte().isCharacter())) {
                        return jeuFigureHauteDePlusFaibleSuite(suites_);
                    }
                }
                if (!joueursNonJoue_.containsObj(teamsRelation.getTaker())) {
                    /*Si l'appele
                    joue apres le preneur*/
                    if(peutSauverFigureAppele(
                            cartesPossibles_,
                            couleurDemandee_,
                            cartesChienCouleur_,
                            joueursNonConfianceNonJoue_,
                            repartitionCouleDem_
                                    .premiereCarte().isCharacter())) {
                        if(!cartesRelMaitres_.isEmpty()) {
                            if (cartesRelMaitres_.size() == 1
                                    || !cartesRelMaitres_.get(1)
                                    .premiereCarte().isCharacter()) {
                                return cartesRelMaitres_.first()
                                        .premiereCarte();
                            }
                            return cartesRelMaitres_.get(1)
                                    .premiereCarte();
                        }
                    }
                }
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
                                cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)
                                && (!carteForte_.isCharacter() || joueurConfianceRamasseur_)) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (carteForte_.isCharacter()) {
                            if (suites_.size() == 1
                                    || !suites_.get(1)
                                    .premiereCarte()
                                    .isCharacter()
                                    || !joueurConfianceRamasseurProbaPli_) {
                                return repartitionCouleDem_
                                        .premiereCarte();
                            }
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (joueurConfianceRamasseurProbaPli_) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    /* Le joueur n'a aucune cartes maitresses */
                    if (aucunePriseMainPossibleParFigure(
                            cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
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
            dernierPli_ = plisFaits_.get(tours_.last());
            dernieresDefausses_ = dernierPli_.joueursDefausses(nombreDeJoueurs_);
            /*
            Deuxieme tour pour un appele ne coupant pas la
            couleur demandee differente de l'atout
            */
            joueursSusceptiblesDeCouper_ = GameTarotTrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles_,couleurDemandee_,joueursNonJoue_);
            if (!joueursSusceptiblesDeCouper_.isEmpty()) {
                if (!GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_ && contientExcuse_) {
                        return CardTarot.excuse();
                    }
                    return carteLaPlusPetite(suites_);
                }
                if (!GameTarotTeamsRelation.intersectionJoueurs(joueursConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_) {
                        if (contientExcuse_) {
                            return CardTarot.excuse();
                        }
                        if (premiereSuitePlusLongueQueTotalCouleurDemandee(
                                suites_,cartesPossibles_,couleurDemandee_,teamsRelation.autresJoueurs(info_.getCurrentPlayer()))) {
                            return repartitionCouleDem_
                                    .premiereCarte();
                        }
                        return carteLaPlusPetite(suites_);
                    }
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
                if (joueursConfiance_.containsObj(GameTarotCommonPlaying.ramasseur(plisFaits_,
                        tours_.last()))) {
                    if (carteForte_.couleur() == Suit.TRUMP) {
                        /*
                    L'espoir fait
                    vivre
                    */
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    if(aucunePriseMainPossibleCouleur(
                            cartesPossibles_,couleurDemandee_,
                            carteForte_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                }
                return carteLaPlusPetite(suites_);
            }
            /* Si la coupe semble improbable */
            if (!dernieresDefausses_.isEmpty() && tours_.size() == 1) {
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
                                cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)
                                && (!carteForte_.isCharacter() || joueurConfianceRamasseur_)) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (carteForte_.isCharacter()) {
                            if (suites_.size() == 1
                                    || !suites_.get(1)
                                    .premiereCarte()
                                    .isCharacter()
                                    || !joueurConfianceRamasseurProbaPli_) {
                                return repartitionCouleDem_
                                        .premiereCarte();
                            }
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (joueurConfianceRamasseurProbaPli_) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    /* Le joueur n'a aucune cartes maitresses */
                    if (aucunePriseMainPossibleParFigure(
                            cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
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
            /*
            Le pli d'avant n'est pas defausse ou c'est au moins
            le troisieme tour
            */
            if (!cartesMaitresses_.getVal(couleurDemandee_)
                    .estVide()) {
                return repartitionCouleDem_.premiereCarte();
            }
            if (!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        /* Defenseur */
        if (tours_.isEmpty()) {
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            if (couleursAppelees_.containsObj(couleurDemandee_)) {
                boolean hauteFigureCouleurAppelee_ = true;
                boolean roiAppeleCouleur_ = false;
                for(CardTarot c: calledCards) {
                    if (c.couleur() != couleurDemandee_) {
                        continue;
                    }
                    if (c.getNomFigure() == CardChar.KING) {
                        roiAppeleCouleur_ = true;
                        break;
                    }
                }
                if (roiAppeleCouleur_) {
                    //appel d'un roi
                    hauteFigureCouleurAppelee_ = false;
                }
                if (doneTrickInfo.getProgressingTrick().total() + 2 == nombreDeJoueurs_
                        && joueursNonJoue_.first() == teamsRelation.getTaker()) {
                    //le preneur joue en dernier
                    hauteFigureCouleurAppelee_ = false;
                }
                if(hauteFigureCouleurAppelee_) {
                    for(CardTarot c: calledCards) {
                        if(cartesCertaines_.getVal(couleurDemandee_)
                                .get(teamsRelation.getTaker()).contient(c)) {
                            hauteFigureCouleurAppelee_ = false;
                            break;
                        }
                    }
                    if (repartitionCouleDem_.premiereCarte().
                            getNomFigure() != CardChar.QUEEN) {
                        hauteFigureCouleurAppelee_ = false;
                    }
                }
                if(hauteFigureCouleurAppelee_) {
                    /*
                    Tant pis si la
                    dame du defenseur
                    se fait prendre
                    par le roi appele
                    du preneur
                    s'appelant
                    volontairement
                    */
                    return repartitionCouleDem_.premiereCarte();
                }
                if (!cartesCertaines_.getVal(Suit.TRUMP).get(teamsRelation.getTaker()).estVide()
                        && cartesPossibles_.getVal(couleurDemandee_)
                        .get(teamsRelation.getTaker()).estVide()) {
                    return carteLaPlusPetite(suites_);
                }
                for (byte joueur_ : joueursNonJoue_) {
                    if(cartesCertaines_.getVal(Suit.TRUMP).get(joueur_)
                            .estVide()) {
                        continue;
                    }
                    if(!cartesPossibles_
                            .getVal(couleurDemandee_)
                            .get(joueur_).estVide()) {
                        continue;
                    }
                    return repartitionCouleDem_
                            .premiereCarte();
                }
                return carteLaPlusPetite(suites_);
            }
            if (GameTarotTrickHypothesis.pasAtout(joueursNonConfianceNonJoue_,
                    cartesPossibles_)) {
                return sauveQuiPeutFigure(cartesPossibles_,
                        suites_, cartesRelMaitres_,
                        joueursNonConfianceNonJoue_,
                        couleurDemandee_);
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
                if (!cartesRelMaitres_.isEmpty()) {
                    if (cartesRelMaitres_.size() == 1
                            || !cartesRelMaitres_.get(1)
                            .premiereCarte().isCharacter()) {
                        return suites_.first().premiereCarte();
                    }
                    return cartesRelMaitres_.get(1)
                            .premiereCarte();
                }
                return carteLaPlusPetite(suites_);
            }
            if (!cartesRelMaitres_.isEmpty()
                    && cartesRelMaitres_.first().total() == 1
                    && repartitionCouleDem_.total() == 2) {
                if (suites_.size() == 1) {
                    return repartitionCouleDem_.premiereCarte();
                }
                if (joueurConfianceRamasseur_) {
                    boolean carteMaitresse_ = true;
                    for (byte joueur_ : joueursNonConfianceNonJoue_) {
                        boolean local_ = false;
                        if (GameTarotTrickHypothesis.defausse(cartesPossibles_, joueur_, couleurDemandee_)) {
                            local_ = true;
                        }
                        if (!cartesPossibles_.getVal(couleurDemandee_)
                                .get(joueur_).estVide()) {
                            if (cartesPossibles_.getVal(couleurDemandee_).get(joueur_).premiereCarte().strength(couleurDemandee_)< carteForte_.strength(couleurDemandee_)) {
                                local_ = true;
                            }
                        }
                        if (!local_) {
                            carteMaitresse_ = false;
                        }
                    }
                    if (carteMaitresse_) {
                        return repartitionCouleDem_.carte(1);
                    }
                }
                return repartitionCouleDem_.premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        /* Maintenant on est au moins au deuxieme tour */
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if (couleursAppelees_.containsObj(couleurDemandee_)) {
            if (carteAppeleeJouee_) {
                if (!cartesMaitresses_.getVal(couleurDemandee_)
                        .estVide()
                        && cartesMaitresses_
                        .getVal(couleurDemandee_)
                        .premiereCarte().isCharacter()) {
                    boolean roiAppeleCouleur_ = false;
                    for(CardTarot c: calledCards) {
                        if (c.couleur() != couleurDemandee_) {
                            continue;
                        }
                        if (c.getNomFigure() == CardChar.KING) {
                            roiAppeleCouleur_ = true;
                        }
                    }
                    if (roiAppeleCouleur_) {
                        return jeuFigureHauteDePlusFaibleSuite(suites_);
                    }
                    return repartitionCouleDem_.premiereCarte();
                }
                if (GameTarotTrickHypothesis.pasAtout(joueursNonConfianceNonJoue_,
                        cartesPossibles_)) {
                    return sauveQuiPeutFigure(cartesPossibles_,
                            suites_, cartesRelMaitres_,
                            joueursNonConfianceNonJoue_,
                            couleurDemandee_);
                }
            }
            return carteLaPlusPetite(suites_);
        }
        if (GameTarotTrickHypothesis.pasAtout(joueursNonConfianceNonJoue_,
                cartesPossibles_)) {
            return sauveQuiPeutFigure(cartesPossibles_, suites_,
                    cartesRelMaitres_,
                    joueursNonConfianceNonJoue_, couleurDemandee_);
        }
        return carteLaPlusPetite(suites_);
    }

    private boolean canLeadTrick(boolean _maitreJeu, EqList<HandTarot> _cartesRelMaitres) {
        return _maitreJeu && !_cartesRelMaitres.isEmpty();
    }

    private CardTarot fournirAtoutClassique(HandTarot _lastHand,
                                            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = common.initInformations(_lastHand, currentHand,_cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _cartesJouables.couleurs();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot repartitionCouleDem_;
        EqList<HandTarot> suites_;
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesChien_ = common.cartesVuesAuChien();
        if (currentStatus == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        } else if (currentStatus == Status.TAKER) {
            cartesFictives_.ajouterCartes(calledCards);
        }

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                info_,
                currentStatus == Status.DEFENDER,
                carteForte_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (maitreJeu_ && contientExcuse_) {
                return CardTarot.excuse();
            }
            if (currentHand.total() == 2 && contientExcuse_) {
                return CardTarot.excuse();
            }
            /* La couleur demandee est atout */
            return atoutLePlusPetit(
                    repartitionJouables_.getVal(Suit.TRUMP).eclaterEnCours(
                            repartitionCartesJouees_, couleurDemandee_), contientExcuse_);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            if (currentHand.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
                Numbers<Byte> equipeNumero_ = new Numbers<Byte>();
                equipeNumero_.addAllElts(joueursConfiance_);
                equipeNumero_.add(info_.getCurrentPlayer());
                if (equipeNumero_.containsAllObj(GameTarotCommonPlaying.ramasseurs(plisFaits_))) {
                    if (CardTarot.eq(_cartesJouables.premiereCarte(),
                            CardTarot.excuse())) {
                        return _cartesJouables.carte(1);
                    }
                    return _cartesJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            /* couleur demandee atout */
            //jeu obligatoire d'atout
            repartitionCouleDem_ = repartitionJouables_.getVal(Suit.TRUMP);
            suites_ = repartitionCouleDem_
                    .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
            if (!repartitionCouleDem_.contient(CardTarot.petit())
                    || maitreJeu_) {
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
            if (GameTarotTrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles_)) {
                return atoutLePlusPetit(suites_);
            }
            return CardTarot.petit();
        }
        //incertitude du ramasseur a la couleur demandee (founiture obligatoire de la couleur demandee)
        if (currentHand.total() == 2 && contientExcuse_) {
            return CardTarot.excuse();
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        boolean carteMaitresse_;
        EqList<HandTarot> cartesRelMaitres_;
        int nombrePoints_ = 0;
        Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_, joueursNonJoue_);

        //fournir d'un atout a la demande d'atout
        suites_ = repartitionJouables_.getVal(Suit.TRUMP)
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(suites_,
                cartesPossibles_, joueursNonJoue_, Suit.TRUMP,
                couleurDemandee_, cartesCertaines_, carteForte_);
        repartitionCouleDem_ = repartitionJouables_.getVal(Suit.TRUMP);
        if (repartitionCouleDem_.premiereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        if (CardTarot.eq(doneTrickInfo.getProgressingTrick().premiereCarte(), CardTarot.petit())) {
            if (joueursConfiance_.containsObj(ramasseurVirtuel_) && joueursNonJoue_.isEmpty()) {
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            if (!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            if (peutRamasserDemandeAtout(cartesPossibles_, cartesCertaines_,
                    info_.getCurrentPlayer(), joueursNonJoue_, GameTarotTeamsRelation.autresJoueurs(joueursNonJoue_, nombreDeJoueurs_), couleurDemandee_) && !joueursNonConfianceNonJoue_.isEmpty()) {
                return repartitionCouleDem_.premiereCarte();
            }
            return suites_.last().premiereCarte();
        }
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            if(joueursNonJoue_.isEmpty()) {
                return atoutLePlusPetit(suites_);
            }
            return cartesRelMaitres_.last().premiereCarte();
        }
        carteMaitresse_ = true;
        for (byte joueur_ : joueursNonConfianceNonJoue_) {
            if(cartesPossibles_.getVal(Suit.TRUMP).get(joueur_)
                    .estVide()) {
                continue;
            }
            if (!(cartesPossibles_.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(couleurDemandee_)< carteForte_.strength(couleurDemandee_))) {
                carteMaitresse_ = false;
            }
        }
        if (carteMaitresse_
                && joueursConfiance_.containsObj(ramasseurVirtuel_)) {
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        for (CardTarot carte_ : doneTrickInfo.getProgressingTrick()) {
            if (carte_ != CardTarot.EXCUSE) {
                nombrePoints_ += carte_.points();
            }
        }
        if (nombrePoints_ > 6) {
            if (!cartesRelMaitres_.isEmpty() && !joueursNonConfianceNonJoue_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return atoutLePlusPetit(suites_);
        }
        carteMaitresse_ = true;
        for (byte joueur_ : joueursNonConfianceNonJoue_) {
            if(cartesPossibles_.getVal(Suit.TRUMP).get(joueur_)
                    .estVide()) {
                continue;
            }
            if (!(cartesPossibles_.getVal(Suit.TRUMP).get(joueur_).premiereCarte().strength(couleurDemandee_)< carteForte_.strength(couleurDemandee_))) {
                carteMaitresse_ = false;
            }
        }
        if (carteMaitresse_) {
            return atoutLePlusPetit(suites_);
        }
        boolean nePeutFournirJoueursNonConfiance_ = true;
        for (byte joueur_ : joueursNonConfianceNonJoue_) {
            if (cartesPossibles_.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                nePeutFournirJoueursNonConfiance_ = false;
            }
        }
        if (nePeutFournirJoueursNonConfiance_) {
            if (!repartitionCartesJouees_.getVal(Suit.TRUMP).contient(CardTarot.petit())) {
                return atoutLePlusPetit(suites_);
            }
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        if (!cartesRelMaitres_.isEmpty() && !joueursNonConfianceNonJoue_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        return atoutLePlusPetit(suites_, contientExcuse_);
    }
    private CardTarot coupeClassique(HandTarot _lastHand,
                                     HandTarot _cartesJouables) {

        TarotInfoPliEnCours info_ = common.initInformations(_lastHand, currentHand,_cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _cartesJouables.couleurs();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot atoutsCoupe_;
        EqList<HandTarot> suites_;
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        boolean carteAppeleeJouee_ = info_.isCarteAppeleeJouee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesChien_ = common.cartesVuesAuChien();
        if (currentStatus == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        } else if (currentStatus == Status.TAKER) {
            cartesFictives_.ajouterCartes(calledCards);
        }
        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                info_,
                currentStatus == Status.DEFENDER,
                carteForte_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        //jouer un atout en coupe, surcoupe ou souscoupe
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (maitreJeu_ && contientExcuse_) {
                return CardTarot.excuse();
            }
            if (currentHand.total() == 2 && contientExcuse_) {
                return CardTarot.excuse();
            }
            return atoutLePlusPetit(
                    repartitionJouables_.getVal(Suit.TRUMP).eclaterEnCours(
                            repartitionCartesJouees_, couleurDemandee_), contientExcuse_);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            if (currentHand.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
                Numbers<Byte> equipeNumero_ = new Numbers<Byte>();
                equipeNumero_.addAllElts(joueursConfiance_);
                equipeNumero_.add(info_.getCurrentPlayer());
                if (equipeNumero_.containsAllObj(GameTarotCommonPlaying.ramasseurs(plisFaits_))) {
                    if (CardTarot.eq(_cartesJouables.premiereCarte(),
                            CardTarot.excuse())) {
                        return _cartesJouables.carte(1);
                    }
                    return _cartesJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            /* couleur demandee atout */
            //jeu obligatoire d'atout
            atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
            suites_ = atoutsCoupe_
                    .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
            if (!atoutsCoupe_.contient(CardTarot.petit())
                    || maitreJeu_) {
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            boolean carteMaitresse_ = true;
            Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
            for (byte joueur_ : joueursNonConfiance_) {
                if (!(cartesPossibles_.getVal(Suit.TRUMP).get(joueur_).estVide())) {
                    carteMaitresse_ = false;
                }
            }
            if (carteMaitresse_) {
                return atoutLePlusPetit(suites_);
            }
            return CardTarot.petit();
        }
        if (currentHand.total() == 2 && contientExcuse_) {
            return CardTarot.excuse();
        }
        //incertitude du ramasseur a la couleur demandee (jeu atout obligatoire de la couleur demandee)
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        Numbers<Byte> joueursJoue_ = info_.getJoueursJoue();
        boolean carteMaitresse_;
        EqList<HandTarot> cartesRelMaitres_;
        CardTarot carteHautePasAtout_;
        EnumList<Suit> coupesFranches_ = info_.getCoupesFranches();
        int nombrePoints_ = 0;
        Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_, joueursNonJoue_);
        Numbers<Byte> joueursConfianceNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(joueursConfiance_, joueursNonJoue_);

        carteHautePasAtout_ = doneTrickInfo.getProgressingTrick().getCartes().couleurs()
                .getVal(couleurDemandee_).premiereCarte();
        suites_ = repartitionJouables_.getVal(Suit.TRUMP).eclaterEnCours(
                repartitionCartesJouees_, couleurDemandee_);
        cartesRelMaitres_ = GameTarotCommonPlaying.cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                Suit.TRUMP, couleurDemandee_, cartesCertaines_,
                carteForte_);
        atoutsCoupe_ = repartitionJouables_.getVal(Suit.TRUMP);
        Numbers<Byte> tours_ = GameTarotCommonPlaying.tours(couleurDemandee_, plisFaits_);
        if (atoutsCoupe_.premiereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            /*
                Si le
                joueur ne
                peut pas
                surcouper
                */
            if (!teamsRelation.existePreneur()) {
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
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
            if (GameTarotTrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles_)) {
                return atoutLePlusPetit(suites_);
            }
            if (tours_.isEmpty()) {
                /* Si c'est le premier tour */
                if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                    return atoutsCoupe_.derniereCarte();
                }
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            /* Deuxieme tour et plus */
            if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                if (repartitionCartesJouees_.getVal(couleurDemandee_)
                        .total() < 8
                        || doneTrickInfo.getProgressingTrick().joueursCoupes(nombreDeJoueurs_)
                        .size() > 1) {
                    return atoutsCoupe_.derniereCarte();
                }
            }
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        if (tours_.isEmpty()) {
            /*
            Le joueur peut surcouper si le pli est deja coupe ou
            couper avec n'importe quel atout
            */
            if (!repartitionJouables_.getVal(Suit.TRUMP).contient(CardTarot.petit())) {
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
                    return atoutLePlusPetit(suites_);
                }
                if (Suit.couleursOrdinaires().containsObj(carteForte_.couleur())) {
                /*
                Si le joueur ne
                surcoupe pas un
                autre joueur
                alors il n'a pas
                le Petit par
                hypothese par
                (m.derniereCarte
                ().getforceJeu(couleurDemandee)>1)
                */
                    int nbChars_ = repartitionCartesJouees_
                            .getVal(couleurDemandee_)
                            .nombreDeFigures();
                    int nbFullChars_ = HandTarot.couleurComplete(couleurDemandee_).nombreDeFigures();
                    if (nbChars_ == nbFullChars_) {
                        return CardTarot.excuse();
                    }
                    if (joueursNonConfianceNonJoue_
                            .isEmpty()) {
                        return CardTarot.excuse();
                    }
                }
                if (!carteAppeleeJouee_ && teamsRelation.existeAppele()
                        && info_.getCurrentPlayer() == teamsRelation.getTaker()
                        && !doneTrickInfo.getProgressingTrick().contient(CardTarot.petit())) {
                    return CardTarot.excuse();
                }
                return atoutLePlusPetit(suites_);
            }
            /* Le joueur peut couper avec le Petit */
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                if (contientExcuse_) {
                    return CardTarot.excuse();
                }
                if (!cartesRelMaitres_.last()
                        .contient(CardTarot.petit())
                        || cartesRelMaitres_.last().total() > 1) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
                //cartesRelMaitres.last().contient(CarteTarot.petit())
                //&& cartesRelMaitres.last().total() == 1
                // ==> cartesRelMaitres == suites
                return cartesRelMaitres_.get(
                        cartesRelMaitres_.size() - 2)
                        .premiereCarte();
            }
            if (GameTarotTrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles_)) {
                return atoutLePlusPetit(suites_);
            }
            if (coupesFranches_.size() == 1) {
                if (info_.getCurrentPlayer() == teamsRelation.getTaker() || !teamsRelation.existePreneur()) {
                    if (atoutsCoupe_.total()
                            + nombreDeJoueurs_ <= 13) {
                        return CardTarot.petit();
                    }
                    carteMaitresse_ = true;
                    for (byte joueur_ : joueursNonConfianceNonJoue_) {
                        if (!(GameTarotTrickHypothesis.nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                            carteMaitresse_ = false;
                        }
                    }
                    if (carteMaitresse_) {
                        return CardTarot.petit();
                    }
                    return atoutLePlusPetit(suites_);
                }
                /* Appele */
                if (currentStatus == Status.CALLED_PLAYER) {
                    return CardTarot.petit();
                }
                if (atoutsCoupe_.total() < 5) {
                    return CardTarot.petit();
                }
                carteMaitresse_ = true;
                for (byte joueur_ : joueursNonConfianceNonJoue_) {
                    if (!(GameTarotTrickHypothesis.nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                        carteMaitresse_ = false;
                    }
                }
                if (carteMaitresse_) {
                    return CardTarot.petit();
                }
                if (atoutsCoupe_.total() > 1
                        || !atoutsCoupe_.contient(CardTarot.petit())) {
                    return atoutLePlusPetit(suites_);
                }
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
            if (bid.getJeuChien() == PlayingDog.WITHOUT || bid.getJeuChien() == PlayingDog.AGAINST) {
                carteMaitresse_ = false;
                for (byte joueur_ : joueursNonConfianceNonJoue_) {
                    if (!cartesPossibles_.getVal(Suit.TRUMP).get(joueur_).estVide()) {
                        carteMaitresse_ = true;
                    }
                }
                if (carteMaitresse_) {
                    if (atoutsCoupe_.total() > 1
                            || !atoutsCoupe_
                            .contient(CardTarot.petit())) {
                        return atoutLePlusPetit(suites_);
                    }
                    return CardTarot.petit();
                }
            }
            carteMaitresse_ = true;
            for (byte joueur_ : joueursNonConfianceNonJoue_) {
                if (!(GameTarotTrickHypothesis.nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                    carteMaitresse_ = false;
                }
            }
            if (carteMaitresse_) {
                return CardTarot.petit();
            }
            if (bid.getJeuChien() == PlayingDog.WITH && info_.getCurrentPlayer() == teamsRelation.getTaker()) {
                EnumList<Suit> coupesNonJoues_;
                EnumList<Suit> couleursExclues_ = new EnumList<Suit>();
                couleursExclues_.add(couleurDemandee_);
                coupesNonJoues_ = GameTarotCommon.complementaireCouleurs(coupesFranches_, couleursExclues_);
                coupesNonJoues_ = GameTarotCommonPlaying.couleursNonEntamees(plisFaits_, coupesNonJoues_);
                HandTarot ecart_ = plisFaits_.first().getCartes();
                int nbCartesEcarteesCouleurDemandee_ = ecart_.couleur(couleurDemandee_).total();
                coupesNonJoues_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(
                        ecart_,
                        coupesNonJoues_, nbCartesEcarteesCouleurDemandee_ - 1);
                if (!coupesNonJoues_.isEmpty()) {
                    if (atoutsCoupe_.total() > 1
                            || !atoutsCoupe_
                            .contient(CardTarot.petit())) {
                        return atoutLePlusPetit(suites_);
                    }
                    return CardTarot.petit();
                }
            }
            if (atoutsCoupe_.total() + nombreDeJoueurs_ <= 15) {
                return CardTarot.petit();
            }
            if (atoutsCoupe_.total() > 1
                    || !atoutsCoupe_.contient(CardTarot.petit())) {
                return atoutLePlusPetit(suites_);
            }
            return CardTarot.petit();
        }
        /* Deuxieme tour et plus */
        if (!repartitionJouables_.getVal(Suit.TRUMP).contient(CardTarot.petit())) {
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            carteMaitresse_ = true;
            for (byte joueur_ : joueursNonConfianceNonJoue_) {
                if (!(GameTarotTrickHypothesis.nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                    carteMaitresse_ = false;
                }
            }
            if (!carteMaitresse_) {
                for (CardTarot carte_ : doneTrickInfo.getProgressingTrick()) {
                    if (carte_ != CardTarot.EXCUSE) {
                        nombrePoints_ += carte_.points();
                    }
                }
                if (nombrePoints_ > 7) {
                    if (!cartesRelMaitres_.isEmpty()) {
                        return cartesRelMaitres_.last()
                                .premiereCarte();
                    }
                    carteMaitresse_ = false;
                    for (byte joueur_ : joueursNonJoue_) {
                        if (GameTarotTrickHypothesis.vaSurcouper(cartesPossibles_, cartesCertaines_, info_.getCurrentPlayer(), joueur_, couleurDemandee_)) {
                            carteMaitresse_ = true;
                        }
                        if (carteMaitresse_) {
                            return atoutLePlusPetit(suites_,
                                    contientExcuse_);
                        }
                    }
                    carteMaitresse_ = false;
                    for (byte joueur_ : joueursNonJoue_) {
                        boolean local_ = true;
                        for (byte joueur2_ : joueursJoue_) {
                            if (!(GameTarotTrickHypothesis.peutSurcouper(cartesPossibles_, joueur2_, joueur_, couleurDemandee_))) {
                                local_ = false;
                            }
                        }
                        if (local_) {
                            carteMaitresse_ = true;
                        }
                        if (carteMaitresse_) {
                            return atoutLePlusPetit(suites_,
                                    contientExcuse_);
                        }
                    }
                    return repartitionJouables_.getVal(Suit.TRUMP).premiereCarte();
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
            if (carteForte_.couleur() == Suit.TRUMP) {
                /*
            Si le pli est deja
            coupe
            */
                if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                    return atoutLePlusPetit(suites_,
                            contientExcuse_);
                }
                return atoutLePlusPetit(suites_);
            }
            if (!carteHautePasAtout_.isCharacter()) {
                carteMaitresse_ = true;
                for (byte joueur_ : joueursNonConfianceNonJoue_) {
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
                if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                    carteMaitresse_ = true;
                    for (byte joueur_ : joueursNonConfianceNonJoue_) {
                        if(GameTarotTrickHypothesis.peutCouper(
                                couleurDemandee_, joueur_,
                                cartesPossibles_)) {
                            carteMaitresse_ = false;
                            break;
                        }
                        if(cartesPossibles_
                                .getVal(couleurDemandee_)
                                .get(joueur_).estVide()) {
                            continue;
                        }
                        if (!(cartesPossibles_.getVal(couleurDemandee_).get(joueur_).premiereCarte().strength(couleurDemandee_)< carteForte_.strength(couleurDemandee_))) {
                            carteMaitresse_ = false;
                        }
                    }
                    if (carteMaitresse_) {
                        return atoutLePlusPetit(suites_,
                                contientExcuse_);
                    }
                }
            }
            return atoutLePlusPetit(suites_);
        }
        /*
        Le joueur possede le Petit et c'est le duxieme tour a
        cette couleur ou plus
        */
        if (GameTarotTrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles_)) {
            return atoutLePlusPetit(suites_);
        }
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            if(joueursConfianceNonJoue_.isEmpty()) {
                return atoutLePlusPetit(suites_);
            }
            return cartesRelMaitres_.last().premiereCarte();
        }
        carteMaitresse_ = true;
        for (byte joueur_ : joueursNonConfianceNonJoue_) {
            if (!(GameTarotTrickHypothesis.nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                carteMaitresse_ = false;
            }
        }
        if (carteMaitresse_) {
            return CardTarot.petit();
        }
        if (nombreDeJoueurs_ < 5) {
            if (tours_.size() == 1) {
                if (atoutsCoupe_.total() > 1
                        || !atoutsCoupe_.contient(CardTarot.petit())) {
                    return atoutLePlusPetit(suites_);
                }
                Numbers<Byte> joueursCoupePreTour_ = plisFaits_
                        .get(tours_.first()).joueursCoupes();
                if (GameTarotTeamsRelation.intersectionJoueurs(joueursNonConfiance_, GameTarotTeamsRelation.autresJoueurs(joueursCoupePreTour_, nombreDeJoueurs_)).isEmpty()) {
                    return CardTarot.petit();
                }
            }
        }
        /* Le jeu s'effectue maintenant a 5 joueurs */
        if (atoutsCoupe_.total() > 1
                || !atoutsCoupe_.contient(CardTarot.petit())) {
            return atoutLePlusPetit(suites_);
        }
        return CardTarot.petit();
    }
    private CardTarot defausseCouleurOrdinaireClassique(HandTarot _lastHand,
                                                        HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = common.initInformations(_lastHand, currentHand,_cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = info_.getCartesMaitresses();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesChien_ = common.cartesVuesAuChien();
        if (currentStatus == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        } else if (currentStatus == Status.TAKER) {
            cartesFictives_.ajouterCartes(calledCards);
        }

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                info_,
                currentStatus == Status.DEFENDER,
                carteForte_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = info_.getSuitesTouteCouleur();
        EnumList<Suit> couleursStrictesMaitresses_ = GameTarotCommonPlaying.strictCouleursMaitres(
                suitesTouteCouleur_, repartitionCartesJouees_,
                cartesPossibles_, info_.getCurrentPlayer());
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());

        //defausse sur une couleur ordinaire
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (maitreJeu_ && contientExcuse_) {
                return CardTarot.excuse();
            }
            if (currentHand.total() == 2 && contientExcuse_) {
                return CardTarot.excuse();
            }
            return defausseCouleurDemandeeSurAdversaire(
                    suitesTouteCouleur_, repartitionCartesJouees_,
                    currentHand, cartesMaitresses_,
                    couleursStrictesMaitresses_, couleurDemandee_);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            if (currentHand.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
                Numbers<Byte> equipeNumero_ = new Numbers<Byte>();
                equipeNumero_.addAllElts(joueursConfiance_);
                equipeNumero_.add(info_.getCurrentPlayer());
                if (equipeNumero_.containsAllObj(GameTarotCommonPlaying.ramasseurs(plisFaits_))) {
                    if (CardTarot.eq(_cartesJouables.premiereCarte(),
                            CardTarot.excuse())) {
                        return _cartesJouables.carte(1);
                    }
                    return _cartesJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            return defausseCouleurDemandeeSurPartenaire(
                    suitesTouteCouleur_, repartitionCartesJouees_,
                    currentHand, cartesMaitresses_,
                    couleursStrictesMaitresses_, couleurDemandee_);
        }
        if (currentHand.total() == 2 && contientExcuse_) {
            return CardTarot.excuse();
        }
        //incertitude du ramasseur a la couleur demandee (defausse sur la couleur demandee ordinaire)
        Numbers<Byte> tours_ = GameTarotCommonPlaying.tours(couleurDemandee_, plisFaits_);
        if (tours_.isEmpty()) {
            Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
            if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                return defausseCouleurDemandeeSurPartenaire(
                        suitesTouteCouleur_, repartitionCartesJouees_,
                        currentHand, cartesMaitresses_,
                        couleursStrictesMaitresses_, couleurDemandee_);
            }
            /* Le ramasseur virtuel n'est pas un joueur de confiance */
            return defausseCouleurDemandeeSurAdversaire(
                    suitesTouteCouleur_, repartitionCartesJouees_,
                    currentHand, cartesMaitresses_,
                    couleursStrictesMaitresses_, couleurDemandee_);
        }
        /*
        Au dexieme tour et au de la il est preferable de jouer une
        petite carte
        */
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(suitesTouteCouleur_,
                    couleurs_, currentHand,
                    repartitionCartesJouees_);
        }
        return jeuPetiteCarteCouleurFigure(suitesTouteCouleur_,
                couleursNonVides_, currentHand, repartitionCartesJouees_);

    }
    private CardTarot defausseAtoutClassique(HandTarot _lastHand,
                                             HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = common.initInformations(_lastHand, currentHand,_cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = info_.getCartesMaitresses();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesChien_ = common.cartesVuesAuChien();
        if (currentStatus == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        } else if (currentStatus == Status.TAKER) {
            cartesFictives_.ajouterCartes(calledCards);
        }

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                info_,
                currentStatus == Status.DEFENDER,
                carteForte_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = info_.getSuitesTouteCouleur();
        EnumList<Suit> couleursStrictesMaitresses_ = GameTarotCommonPlaying.strictCouleursMaitres(
                suitesTouteCouleur_, repartitionCartesJouees_,
                cartesPossibles_, info_.getCurrentPlayer());
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
        //defausse sur l'atout
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (maitreJeu_ && contientExcuse_) {
                return CardTarot.excuse();
            }
            if (currentHand.total() == 2 && contientExcuse_) {
                return CardTarot.excuse();
            }
            return defausseAtoutSurAdversaire(suitesTouteCouleur_,
                    repartitionCartesJouees_, currentHand,
                    cartesMaitresses_, couleursStrictesMaitresses_);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            if (currentHand.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
                Numbers<Byte> equipeNumero_ = new Numbers<Byte>();
                equipeNumero_.addAllElts(joueursConfiance_);
                equipeNumero_.add(info_.getCurrentPlayer());
                if (equipeNumero_.containsAllObj(GameTarotCommonPlaying.ramasseurs(plisFaits_))) {
                    if (CardTarot.eq(_cartesJouables.premiereCarte(),
                            CardTarot.excuse())) {
                        return _cartesJouables.carte(1);
                    }
                    return _cartesJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            return defausseAtoutSurPartenaire(suitesTouteCouleur_,
                    repartitionCartesJouees_, currentHand,
                    cartesMaitresses_, couleursStrictesMaitresses_);
        }
        if (currentHand.total() == 2 && contientExcuse_) {
            return CardTarot.excuse();
        }
        //incertitude du ramasseur a la couleur demandee (defausse sur l'atout)
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(suitesTouteCouleur_,
                    couleurs_, currentHand, repartitionCartesJouees_);
        }
        return jeuPetiteCarteCouleurFigure(suitesTouteCouleur_,
                couleursNonVides_, currentHand, repartitionCartesJouees_);
    }

    /**
     Est vrai si et seulement si le partenaire du joueur qui va jouer domine
     l'adversaire n'ayant pas joue (uniquement si le partenaire est maitre
     temporairement du pli)
     */
    private static boolean joueurConfianceRamasseurProbaPli(
            Numbers<Byte> _joueursNonConfianceNonJoue, Suit _couleurDemandee,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, CardTarot _carteForte) {
        boolean bat_ = true;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if(_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide()) {
                continue;
            }
            if (!(_carteForte.strength(_couleurDemandee) > _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().strength(_couleurDemandee))) {
                bat_ = false;
            }
        }
        return bat_;
    }

    private static boolean aucunePriseMainPossibleParFigure(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Numbers<Byte> _joueursNonConfianceNonJoue) {
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
    private static boolean aucunePriseMainPossibleCouleur(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            CardTarot _carteForte,
            Numbers<Byte> _joueursNonConfianceNonJoue) {
        byte max_ = 0;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if (_cartesPossibles.getVal(_couleurDemandee)
                    .get(joueur_).estVide()) {
                return false;
            }
            max_ = (byte) Math.max(
                    _cartesPossibles.getVal(_couleurDemandee)
                            .get(joueur_).premiereCarte()
                            .strength(_couleurDemandee), max_);
        }
        return _carteForte.strength(_couleurDemandee) > max_;
    }
    private static boolean peutSauverFigureAppele(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            HandTarot _cartesChien,
            Numbers<Byte> _joueursNonConfianceNonJoue,
            boolean _figure) {
        boolean carteMaitresse_ = true;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if (!(GameTarotTrickHypothesis.peutCouper(_couleurDemandee, joueur_, _cartesPossibles))) {
                carteMaitresse_ = false;
            }
            if (!carteMaitresse_) {
                carteMaitresse_ = true;
                if (!_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide()
                        && !_cartesChien.estVide()) {
                    if (!(_cartesChien.premiereCarte().strength(_couleurDemandee)> _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().strength(_couleurDemandee))) {
                        carteMaitresse_ = false;
                    }
                }
            } else {
                if (_cartesPossibles.getVal(Suit.TRUMP)
                        .get(joueur_).estVide()) {
                    continue;
                }
                carteMaitresse_ = false;
                break;
            }
        }
        if(!_figure) {
            return false;
        }
        return carteMaitresse_;
    }
    private static boolean premiereSuitePlusLongueQueTotalCouleurDemandee(
            EqList<HandTarot> _suites,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Numbers<Byte> _joueurs) {
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
    private static boolean peutRamasserDemandeAtout(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, byte _numero,
            Numbers<Byte> _joueursNonJoue, Numbers<Byte> _joueursJoue, Suit _couleurDemandee) {
        boolean existe_ = false;
        EqList<HandTarot> atoutsCertains_ = _cartesCertaines.getVal(Suit.TRUMP);
        byte maxAtoutNumero_ = atoutsCertains_.get(_numero)
                .premiereCarte().strength(_couleurDemandee);
        for (byte joueur_ : _joueursNonJoue) {
            if(atoutsCertains_.get(joueur_).estVide()) {
                continue;
            }
            if (atoutsCertains_.get(joueur_).premiereCarte().strength(_couleurDemandee) > maxAtoutNumero_) {
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
                    .strength(_couleurDemandee);
            boolean local_ = true;
            for (byte joueur2_ : _joueursJoue) {
                if (!(maxAtoutJoueurNonJoue_ > atoutsPossibles_.get(joueur2_).premiereCarte().strength(_couleurDemandee))) {
                    local_ = false;
                }
            }
            if (local_) {
                existe_ = true;
            }
        }
        return !existe_;
    }


    private static CardTarot sauveQuiPeutFigure(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EqList<HandTarot> _suites, EqList<HandTarot> _cartesRelMaitres,
            Numbers<Byte> _joueursDeNonConfianceNonJoue,
            Suit _couleurDemandee) {
        if (_joueursDeNonConfianceNonJoue.isEmpty()) {
            return jeuFigureHauteDePlusFaibleSuite(_suites);
        }
        boolean pasAtout_ = true;
        for (byte joueur_ : _joueursDeNonConfianceNonJoue) {
            if (!_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide()) {
                if (_cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().isCharacter()) {
                    pasAtout_ = false;
                }
            }
            if (!(_cartesPossibles.getVal(Suit.TRUMP).get(joueur_).estVide())) {
                pasAtout_ = false;
            }
        }
        if (pasAtout_) {
            return jeuFigureHauteDePlusFaibleSuite(_suites);
        }
        if (!_cartesRelMaitres.isEmpty()) {
            if (_cartesRelMaitres.size() == 1
                    || !_cartesRelMaitres.get(1).premiereCarte().isCharacter()) {
                return _suites.first().premiereCarte();
            }
            //cartesRelMaitres.size() > 1 && cartesRelMaitres.get(1).premiereCarte().estUneFigure()
            return _cartesRelMaitres.get(1).premiereCarte();
        }
        return weakestCard(_suites);
    }

    /**
     Renvoie la plus grande carte basse (n'etant pas une figure) a jouer dans
     la suite la plus faible de la couleur
     */
    private static CardTarot carteLaPlusPetite(EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesFigures_ = new EqList<HandTarot>();
        EqList<HandTarot> suitesCartesBasses_ = new EqList<HandTarot>();
        for(HandTarot m: _suites) {
            int nbFigures_ = m.nombreDeFigures();
            if(nbFigures_ == m.total()) {
                suitesFigures_.add(m);
                continue;
            }
            if(nbFigures_ == 0) {
                suitesCartesBasses_.add(m);
                continue;
            }
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
            suitesCartesBasses_.add(suiteCartesBasses_);
        }
        if (!suitesCartesBasses_.isEmpty()) {
            return suitesCartesBasses_.last().premiereCarte();
        }
        return suitesFigures_.last().derniereCarte();
    }

    /**
     Renvoie la plus grande carte basse (n'etant pas une figure) a jouer dans
     la suite la plus faible de la couleur
     */
    private static CardTarot weakestCard(EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesFigures_ = new EqList<HandTarot>();
        EqList<HandTarot> suitesCartesBasses_ = new EqList<HandTarot>();
        for(HandTarot m: _suites) {
            int nbFigures_ = m.nombreDeFigures();
            if(nbFigures_ == m.total()) {
                suitesFigures_.add(m);
                continue;
            }
            if(nbFigures_ == 0) {
                suitesCartesBasses_.add(m);
                continue;
            }
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
            suitesCartesBasses_.add(suiteCartesBasses_);
        }
        if (!suitesCartesBasses_.isEmpty()) {
            return suitesCartesBasses_.last().premiereCarte();
        }
        return suitesFigures_.last().premiereCarte();
    }
    /**
     Renvoie la figure la plus grande dans la suite de figure la moins
     haute(en valeur)
     */
    private static CardTarot jeuFigureHauteDePlusFaibleSuite(
            EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesFigures_ = new EqList<HandTarot>();
        EqList<HandTarot> suitesCartesBasses_ = new EqList<HandTarot>();
        for(HandTarot m: _suites) {
            int nbFigures_ = m.nombreDeFigures();
            if(nbFigures_ == m.total()) {
                suitesFigures_.add(m);
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
            suitesFigures_.add(suiteFigures_);
            suitesCartesBasses_.add(suiteCartesBasses_);
        }
        if (!suitesFigures_.isEmpty()) {
            return suitesFigures_.last().premiereCarte();
        }
        return suitesCartesBasses_.last().premiereCarte();

    }

    /**Methode retournant un atout different du Petit sauf si ce dernier est sec.*/
    private static CardTarot atoutLePlusPetit(EqList<HandTarot> _suites) {
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
    private static CardTarot atoutLePlusPetit(EqList<HandTarot> _suites,
                                              boolean _contientExcuse) {
        if (_contientExcuse) {
            return CardTarot.excuse();
        }
        /* Le joueur n'a pas l'Excuse */
        return atoutLePlusPetit(_suites);
    }

    private static CardTarot defausseAtoutSurAdversaire(
            EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
        if (_repartitionCartesJouees.getVal(Suit.TRUMP).total() > 17) {
            if (_couleursStrictementMaitresses.size() == Suit.couleursOrdinaires().size()) {
                return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                        _cartesMaitresses, _main, couleursNonVides_);
            }
            EnumList<Suit> couleurs_ = GameTarotCommon.couleursSansFigures(_main, couleursNonVides_);
            if (!couleurs_.isEmpty()) {
                return jouerPetiteCarteDefausse(_suitesTouteCouleur,
                        couleurs_, _main, _repartitionCartesJouees);
            }
            return jeuPetiteCarteCouleurFigure(_suitesTouteCouleur,
                    couleursNonVides_, _main, _repartitionCartesJouees);
        }
        /*
        Moins de 20 atouts sont joues et moins de 13 cartes de la couleur
        demandee sont jouees
        */
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursSansFigures(_main, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleurs_,
                    _main, _repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(_suitesTouteCouleur, couleursNonVides_,
                _main, _repartitionCartesJouees);
    }

    private static CardTarot defausseCouleurDemandeeSurAdversaire(
            EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses, Suit _couleurDemandee) {
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
        EnumList<Suit> couleursAutreQueDemandee_ = new EnumList<Suit>();
        for(Suit c: Suit.couleursOrdinaires()) {
            if(c == _couleurDemandee) {
                continue;
            }
            couleursAutreQueDemandee_.add(c);
        }
        if (_repartitionCartesJouees.getVal(Suit.TRUMP).total() > 19
                && _repartitionCartesJouees.getVal(_couleurDemandee).total() > 12) {
            if (_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandee_)) {
                if (!couleursNonVides_.isEmpty()) {
                    return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                            _cartesMaitresses, _main, couleursNonVides_);
                }
            }
            EnumList<Suit> couleursSansFigure_ = GameTarotCommon.couleursSansFigures(_main, couleursNonVides_);
            if (!couleursSansFigure_.isEmpty()) {
                return jouerPetiteCarteDefausse(_suitesTouteCouleur,
                        couleursSansFigure_, _main, _repartitionCartesJouees);
            }
            return jeuPetiteCarteCouleurFigure(_suitesTouteCouleur,
                    couleursNonVides_, _main, _repartitionCartesJouees);
        }
        /*
        Moins de 20 atouts sont joues et moins de 13 cartes de la couleur
        demandee sont jouees
        */
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
        EnumList<Suit> couleursSansFigures_ = GameTarotCommon.couleursSansFigures(_main, _couleursNonVides);
        EnumList<Suit> couleurs_ = _couleursNonVides;
        if(!couleursSansFigures_.isEmpty()) {
            couleurs_ = couleursSansFigures_;

        }
        couleurs_ = GameTarotCommon.couleursAvecLaPlusPetiteCarteBasse(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees),couleurs_);
        return carteLaPlusPetite(_suites.getVal(couleurs_.first()));
    }

    private static CardTarot defausseAtoutSurPartenaire(
            EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        boolean carteMaitresse_;
        EnumMap<Suit,HandTarot> repartition_=_main.couleurs();
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
        if (_repartitionCartesJouees.getVal(Suit.TRUMP).total() > 17) {
            if (_couleursStrictementMaitresses.size() == Suit.couleursOrdinaires().size()) {
                carteMaitresse_ = true;
                for (Suit couleur_ : _couleursStrictementMaitresses) {
                    if (!(_cartesMaitresses.getVal(couleur_).total() == repartition_.getVal(couleur_).total())) {
                        carteMaitresse_ = false;
                    }
                }
                if (carteMaitresse_) {
                    EnumList<Suit> couleursFigures_ = GameTarotCommon.couleursAvecFigures(_main, couleursNonVides_);
                    if (!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(
                                couleursFigures_, _main);
                    }
                }
                return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                        _cartesMaitresses, _main, couleursNonVides_);
            }
            /*
            Il peut exister une couleur pour se defausser non strictement
            maitresse
            */
            EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(_main, HandTarot.reunion(_repartitionCartesJouees), Suit.couleursOrdinaires());
            if (!couleurs_.isEmpty()) {
                return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                        _cartesMaitresses, _main, couleurs_);
            }
            couleurs_ = GameTarotCommon.couleursAvecFigures(_main, couleursNonVides_);
            if (!couleurs_.isEmpty()) {
                return sauverFiguresDefausse(couleurs_, _main,
                        _repartitionCartesJouees);
            }
            return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleursNonVides_,
                    _main, _repartitionCartesJouees);
        }
        /*
        Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur
        demandee sont jouees
        */
        if (_couleursStrictementMaitresses.size() == Suit.couleursOrdinaires().size()) {
            carteMaitresse_ = true;
            for (Suit couleur_ : _couleursStrictementMaitresses) {
                if (!(_cartesMaitresses.getVal(couleur_).total() == repartition_.getVal(couleur_).total())) {
                    carteMaitresse_ = false;
                }
            }
            if (carteMaitresse_) {
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

    private static CardTarot defausseCouleurDemandeeSurPartenaire(
            EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses, Suit _couleurDemandee) {
        boolean carteMaitresse_;
        EnumMap<Suit,HandTarot> repartition_=_main.couleurs();
        EnumList<Suit> couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_main, Suit.couleursOrdinaires());
        EnumList<Suit> couleursAutreQueDemandee_ = new EnumList<Suit>();
        for(Suit c: Suit.couleursOrdinaires()) {
            if(c == _couleurDemandee) {
                continue;
            }
            couleursAutreQueDemandee_.add(c);
        }
        if (_repartitionCartesJouees.getVal(Suit.TRUMP).total() > 19
                && _repartitionCartesJouees.getVal(_couleurDemandee).total() > 12) {
            if (_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandee_)) {
                carteMaitresse_ = true;
                for (Suit couleur_ : _couleursStrictementMaitresses) {
                    if (!(_cartesMaitresses.getVal(couleur_).total() == repartition_.getVal(couleur_).total())) {
                        carteMaitresse_ = false;
                    }
                }
                if (carteMaitresse_) {
                    EnumList<Suit> couleursFigures_ = GameTarotCommon.couleursAvecFigures(_main, couleursNonVides_);
                    if (!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(
                                couleursFigures_, _main);
                    }
                }
                if (!couleursNonVides_.isEmpty()) {
                    return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                            _cartesMaitresses, _main, couleursNonVides_);
                }
            }
            /*
            Il peut exister une couleur pour se defausser non strictement
            maitresse
            */
            EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(_main, HandTarot.reunion(_repartitionCartesJouees), Suit.couleursOrdinaires());
            if (!couleurs_.isEmpty()) {
                return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                        _cartesMaitresses, _main, couleurs_);
            }
            couleurs_ = GameTarotCommon.couleursAvecFigures(_main, couleursNonVides_);
            if (!couleurs_.isEmpty()) {
                return sauverFiguresDefausse(couleurs_, _main,
                        _repartitionCartesJouees);
            }
            return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleursNonVides_,
                    _main, _repartitionCartesJouees);
        }
        /*
        Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur
        demandee sont jouees
        */
        if (_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandee_)) {
            carteMaitresse_ = true;
            for (Suit couleur_ : _couleursStrictementMaitresses) {
                if (!(_cartesMaitresses.getVal(couleur_).total() == repartition_.getVal(couleur_).total())) {
                    carteMaitresse_ = false;
                }
            }
            if (carteMaitresse_) {
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

    private static CardTarot jeuGrandeCarteDefausseMaitre(
            EnumList<Suit> _couleursFiguresNonVide, HandTarot _main) {
        EnumList<Suit> couleursAvecFigures_ = GameTarotCommon.couleursAvecFigures(_main, _couleursFiguresNonVide);
        EnumList<Suit> couleurs_ = _couleursFiguresNonVide;
        if(!couleursAvecFigures_.isEmpty()) {
            couleurs_ = couleursAvecFigures_;
        }
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusGrandNbFigures(_main, couleurs_);
        return _main.couleur(couleurs_.first()).premiereCarte();
    }

    private static CardTarot sauverFiguresDefausse(
            EnumList<Suit> _couleursFiguresNonVide, HandTarot _main,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleursAvecFigures_ = GameTarotCommon.couleursAvecFigures(_main, _couleursFiguresNonVide);
        EnumList<Suit> couleurs_ = _couleursFiguresNonVide;
        if(!couleursAvecFigures_.isEmpty()) {
            couleurs_ = couleursAvecFigures_;
        }
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(_main, couleurs_);
        return _main.couleur(couleurs_.first()).premiereCarte();
    }

    private static CardTarot jeuPetiteDefausseMaitre(
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

    private static CardTarot jouerPetiteCarteDefausse(
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

}
