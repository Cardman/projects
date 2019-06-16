package cards.tarot;

import cards.consts.PossibleTrickWinner;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.*;

public final class GameTarotMisere {

    private GameTarotTeamsRelation teamsRelation;

    private GameTarotTrickInfo doneTrickInfo;

    private HandTarot currentHand;
    private GameTarotCommonPlaying common;
    private HandTarot playableCards;
    private Numbers<Byte> confidentPlayers;
    private Numbers<Byte> notConfidentPlayers;

    public GameTarotMisere(GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
                           HandTarot _currentHand) {
        doneTrickInfo = _done;
        teamsRelation = _teamsRelation;
        currentHand = _currentHand;
        common = new GameTarotCommonPlaying(_done,_teamsRelation);
        byte nbPlayers_ = _teamsRelation.getNombreDeJoueurs();
        TrickTarot trTarot_ = _done.getProgressingTrick();
        byte nextPlayer_ = trTarot_.getNextPlayer(nbPlayers_);
        playableCards = common.cartesJouables(currentHand.couleurs());
        confidentPlayers = _teamsRelation.joueursConfiance(nextPlayer_,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        notConfidentPlayers = _teamsRelation.joueursNonConfiance(nextPlayer_,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
    }

    CardTarot entame() {
        /* Jeu de misere */
        if (playableCards.total() == 1) {
            return playableCards.premiereCarte();
        }
        return entameMiserePetite();
    }
    private CardTarot entameMiserePetite() {
        TarotInfoPliEnCours info_ = initInformations();
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot cartesJouees_ = info_.getCartesJouees();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suites_ = info_.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        HandTarot atoutsMaitres_ = repartition_.getVal(Suit.TRUMP)
                .atoutsMaitres(repartitionCartesJouees_);

        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        if (repartition_.getVal(Suit.TRUMP).contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        if (atouts_.total() + repartition_.getVal(CardTarot.excuse().couleur()).total() == currentHand
                .total()) {
            if (atoutsMaitres_.estVide()) {
                return suites_.getVal(Suit.TRUMP).first().derniereCarte();
            }
            return repartition_.getVal(Suit.TRUMP).derniereCarte();
        }
        if (atouts_.total() == 1 && atoutsMaitres_.estVide()) {
            return repartition_.getVal(Suit.TRUMP).premiereCarte();
        }
        boolean coupeSure_ = false;
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            for (byte joueur_ : joueursNonJoue_) {
                if (GameTarotTrickHypothesis.vaCouper(couleur_, joueur_, cartesPossibles_, cartesCertaines_)) {
                    coupeSure_ = true;
                }
            }
        }
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        if (coupeSure_) {
            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                if (!repartition_.getVal(couleur_).estVide()
                        && repartition_.getVal(couleur_).premiereCarte()
                        .isCharacter()) {
                    couleurs_.add(couleur_);
                }
            }
            if (!couleurs_.isEmpty()) {
                return depouilleFigure(couleurs_, repartition_,
                        repartitionCartesJouees_);
            }
            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                if (!repartition_.getVal(couleur_).estVide()) {
                    couleurs_.add(couleur_);
                }
            }
            return depouillePetiteCarte(couleurs_, repartition_,
                    repartitionCartesJouees_);
        }
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if (!repartition_.getVal(couleur_).estVide()) {
                couleurs_.add(couleur_);
            }
        }
        if (!couleurs_.isEmpty() && !repartition_.getVal(Suit.TRUMP).estVide()) {
            if (atoutsMaitres_.estVide()) {
                return suites_.getVal(Suit.TRUMP).first().derniereCarte();
            }
            return repartition_.getVal(Suit.TRUMP).derniereCarte();
        }
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusGrandNbFigures(cartesJouees_, couleurs_);
        return repartition_.getVal(couleurs_.first()).derniereCarte();
    }


    CardTarot enCours() {
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        if (playableCards.total() == 1) {
            return playableCards.premiereCarte();
        }
        if (doneTrickInfo.getProgressingTrick().couleurDemandee() == Suit.UNDEFINED) {
            // Cela
            // se
            // passe
            // comme
            // une
            // entame
            // avec
            // un
            // joueur
            // en
            // moins
            /* C'est une entame sur Excuse */
            return entame();
        }
        if (Suit.couleursOrdinaires().containsObj(couleurDemandee_)) {
            if (!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                return fournirCouleurOrdinaireMisere();
            }
            if (!repartitionJouables_.getVal(Suit.TRUMP).estVide()) {
                return coupeMisere();
            }
            return defausseMisere();
        }
        if (!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
            return fournirAtoutMisere();
        }
        return defausseMisere();
    }

    private CardTarot fournirCouleurOrdinaireMisere() {
        TarotInfoPliEnCours info_ = initInformations();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot repartitionCouleDem_ = repartitionJouables_.getVal(couleurDemandee_);
        EqList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                info_
        );
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return repartitionCouleDem_.premiereCarte();
        }
        if (repartitionCouleDem_.derniereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            return carteInfVirt(suites_, carteForte_,couleurDemandee_);
        }
        if (!repartitionCouleDem_.derniereCarte().isCharacter()) {
            return carteInfFigure(suites_);
        }
        return suites_.last().derniereCarte();
    }
    private CardTarot fournirAtoutMisere() {
        TarotInfoPliEnCours info_ = initInformations();
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                info_
        );
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (repartitionJouables_.getVal(Suit.TRUMP).contient(CardTarot.petit())) {
                return CardTarot.petit();
            }
            return repartitionJouables_.getVal(Suit.TRUMP).premiereCarte();
        }
        return repartitionJouables_.getVal(Suit.TRUMP).premiereCarte();
    }
    private CardTarot coupeMisere() {
        TarotInfoPliEnCours info_ = initInformations();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        HandTarot trumps_ = repartitionJouables_.getVal(Suit.TRUMP);
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                info_
        );
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (repartitionJouables_.getVal(Suit.TRUMP).contient(CardTarot.petit())) {
                return CardTarot.petit();
            }
            return repartitionJouables_.getVal(Suit.TRUMP).premiereCarte();
        }
        boolean surcoupeSure_ = false;
        for (byte joueur_ : joueursNonJoue_) {
            if (GameTarotTrickHypothesis.vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_)) {
                surcoupeSure_ = true;
            }
        }
        if (surcoupeSure_
                && trumps_.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        if (GameTarotCommonPlaying.tours(couleurDemandee_, plisFaits_).isEmpty()) {
            return trumps_.premiereCarte();
        }
        surcoupeSure_ = false;
        byte valeur_ = 0;
        for (byte joueur_ : joueursNonJoue_) {
            if (GameTarotTrickHypothesis.vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_)) {
                surcoupeSure_ = true;
            }
            if (surcoupeSure_
                    && cartesCertaines_.getVal(Suit.TRUMP).get(joueur_).premiereCarte()
                    .strength(couleurDemandee_)> valeur_) {
                valeur_ = cartesCertaines_.getVal(Suit.TRUMP).get(joueur_)
                        .premiereCarte().strength(couleurDemandee_);
            }
        }
        if (surcoupeSure_) {
            if (trumps_.derniereCarte().strength(couleurDemandee_)< valeur_) {
                byte carteAJouer_ = CustList.INDEX_NOT_FOUND_ELT;
                byte lastIndex_ = (byte) (trumps_.total() - 1);
                for (byte indiceCarte_ = lastIndex_; indiceCarte_ >= CustList.FIRST_INDEX; indiceCarte_--) {
                    if (trumps_.carte(indiceCarte_)
                            .strength(couleurDemandee_)< valeur_) {
                        carteAJouer_++;
                    } else {
                        break;
                    }
                }
                return trumps_.carte(carteAJouer_);
            }
            return trumps_.derniereCarte();
        }
        return trumps_.premiereCarte();
    }
    private CardTarot defausseMisere() {
        TarotInfoPliEnCours info_ = initInformations();
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();

        EnumList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
        boolean contFigureTtClr_ = true;
        for (Suit couleur_ : couleurs_) {
            if (!(repartition_.getVal(couleur_).premiereCarte().isCharacter())) {
                contFigureTtClr_ = false;
            }
        }
        if (contFigureTtClr_ && !couleurs_.isEmpty()) {
            return depouilleFigureEnCours(repartitionJouables_, couleurs_,
                    repartitionCartesJouees_);
        }
        contFigureTtClr_ = true;
        for (Suit couleur_ :  Suit.couleursOrdinaires()) {
            if (!repartition_.getVal(couleur_).estVide() && repartitionCartesJouees_.getVal(couleur_).total() >= HandTarot.couleurComplete(couleur_).total()) {
                contFigureTtClr_ = false;
            }
            if (repartition_.getVal(couleur_).estVide() && repartitionCartesJouees_.getVal(couleur_).total() < HandTarot.couleurComplete(couleur_).total()) {
                contFigureTtClr_ = false;
            }
        }
        if (contFigureTtClr_) {
            EnumList<Suit> couleursNonEntamees_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_,  Suit.couleursOrdinaires());
            couleursNonEntamees_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(currentHand, couleursNonEntamees_, 1);
            couleursNonEntamees_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, couleursNonEntamees_);
            if (!couleursNonEntamees_.isEmpty()) {
                return singletonFortDepouille(repartition_,
                        couleursNonEntamees_);
            }
            couleursNonEntamees_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(currentHand, couleurs_, 1);
            couleursNonEntamees_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, couleursNonEntamees_);
            if (!couleursNonEntamees_.isEmpty()) {
                return singletonFortDepouille(repartition_,
                        couleursNonEntamees_);
            }
        }
        EnumList<Suit> couleursAvecFigures_ = GameTarotCommon.couleursAvecFigures(currentHand, couleurs_);
        if (!couleursAvecFigures_.isEmpty()) {
            return depouilleFigureDefausse(repartition_,
                    couleursAvecFigures_, repartitionCartesJouees_);
        }
        return enCoursMiserePetite(couleurs_, repartition_,
                repartitionCartesJouees_);
    }
    TarotInfoPliEnCours initInformations() {
        return common.initInformations(currentHand,playableCards, confidentPlayers,notConfidentPlayers);
    }

    private static CardTarot depouilleFigure(EnumList<Suit> _couleurs,
                                             EnumMap<Suit,HandTarot> _repartition,
                                             EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartition), _couleurs);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(HandTarot.reunion(_repartition), couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        return _repartition.getVal(couleurs_.first()).premiereCarte();
    }

    private static CardTarot depouillePetiteCarte(EnumList<Suit> _couleurs,
                                                  EnumMap<Suit,HandTarot> _repartition,
                                                  EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartition), _couleurs);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(HandTarot.reunion(_repartition), couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        return _repartition.getVal(couleurs_.first()).derniereCarte();
    }

    private static CardTarot carteInfFigure(EqList<HandTarot> _suites) {
        byte indiceSuiteJoue_ = (byte) _suites.size();
        byte lastIndex_ = (byte) _suites.getLastIndex();
        for (byte indiceSuite_ = lastIndex_; indiceSuite_ >= CustList.FIRST_INDEX; indiceSuite_--) {
            if (!_suites.get(indiceSuite_).premiereCarte().isCharacter()) {
                indiceSuiteJoue_--;
            } else {
                break;
            }
        }
        return _suites.get(indiceSuiteJoue_).premiereCarte();
    }

    private static CardTarot carteInfVirt(EqList<HandTarot> _suites,
                                          CardTarot _carteVirtuelle,
                                          Suit _couleurDemandee) {
        byte indiceSuiteJoue_ = (byte) _suites.size();
        byte lastIndex_ = (byte) _suites.getLastIndex();
        for (byte indiceSuite_ = lastIndex_; indiceSuite_ >= CustList.FIRST_INDEX; indiceSuite_--) {
            if (_suites.get(indiceSuite_).premiereCarte().strength(_couleurDemandee) < _carteVirtuelle
                    .strength(_couleurDemandee)) {
                indiceSuiteJoue_--;
            } else {
                break;
            }
        }
        return _suites.get(indiceSuiteJoue_).premiereCarte();
    }


    private static CardTarot enCoursMiserePetite(EnumList<Suit> _couleurs,
                                                 EnumMap<Suit,HandTarot> _repartition,
                                                 EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartition), _couleurs);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartition), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(HandTarot.reunion(_repartition), couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        return _repartition.getVal(couleurs_.first()).derniereCarte();
    }

    private static CardTarot depouilleFigureDefausse(
            EnumMap<Suit,HandTarot> _repartition, EnumList<Suit> _couleurs,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartition), _couleurs);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(HandTarot.reunion(_repartition), couleurs_);
        return _repartition.getVal(couleurs_.first()).premiereCarte();
    }

    private static CardTarot singletonFortDepouille(
            EnumMap<Suit,HandTarot> _repartition, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusHautes(HandTarot.reunion(_repartition), _couleurs);
        return _repartition.getVal(couleurs_.first()).premiereCarte();
    }

    private static CardTarot depouilleFigureEnCours(
            EnumMap<Suit,HandTarot> _repartition, EnumList<Suit> _couleurs,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartition), _couleurs);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(HandTarot.reunion(_repartition), couleurs_);
        return _repartition.getVal(couleurs_.first()).premiereCarte();
    }

}
