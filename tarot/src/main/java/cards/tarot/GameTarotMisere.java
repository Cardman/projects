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
    private Bytes confidentPlayers;
    private Bytes notConfidentPlayers;

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
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suites_ = info_.getSuitesTouteCouleur();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        HandTarot atoutsMaitres_ = atouts_
                .atoutsMaitres(repartitionCartesJouees_);

        if (atouts_.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        if (atouts_.total() + repartition_.getVal(CardTarot.excuse().couleur()).total() == currentHand
                .total()) {
            return discardTrump(suites_, atouts_, atoutsMaitres_);
        }
        if (atouts_.total() == 1 && atoutsMaitres_.estVide()) {
            return atouts_.premiereCarte();
        }
        return tryDiscard(info_);
    }

    CardTarot tryDiscard(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suites_ = _info.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        HandTarot atoutsMaitres_ = atouts_
                .atoutsMaitres(repartitionCartesJouees_);

        Bytes joueursNonJoue_ = _info.getJoueursNonJoue();
        EnumList<Suit> suits_  = new EnumList<Suit>();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            for (byte joueur_ : joueursNonJoue_) {
                if (GameTarotTrickHypothesis.vaCouper(couleur_, joueur_, cartesPossibles_, cartesCertaines_)) {
                    suits_.add(couleur_);
                }
            }
        }
        suits_.removeDuplicates();
        if (!suits_.isEmpty()) {
            EnumList<Suit> couleurs_ = GameTarotCommon.couleursAvecFigures(currentHand,suits_);
            if (!couleurs_.isEmpty()) {
                return depouilleFigure(couleurs_, repartition_,
                        repartitionCartesJouees_);
            }
            couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand,suits_);
            return depouillePetiteCarte(couleurs_, repartition_,
                    repartitionCartesJouees_);
        }
        if (!atouts_.estVide()) {
            return discardTrump(suites_, atouts_, atoutsMaitres_);
        }
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand,Suit.couleursOrdinaires());
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusGrandNbFigures(cartesJouees_, couleurs_);
        return repartition_.getVal(couleurs_.first()).derniereCarte();
    }
    private static CardTarot discardTrump(EnumMap<Suit, EqList<HandTarot>> _suites, HandTarot _atouts, HandTarot _atoutsMaitres) {
        if (_atoutsMaitres.estVide()) {
            return _suites.getVal(Suit.TRUMP).first().derniereCarte();
        }
        return _atouts.derniereCarte();
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
        int i_ = 0;
        int c_ = repartitionCouleDem_.total();
        while (i_ < c_) {
            CardTarot card_ = repartitionCouleDem_.carte(i_);
            if (card_.strength(couleurDemandee_) < carteForte_.strength(couleurDemandee_)) {
                return card_;
            }
            i_++;
        }
        return suites_.last().derniereCarte();
    }
    private CardTarot fournirAtoutMisere() {
        TarotInfoPliEnCours info_ = initInformations();
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();

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
        EnumMap<Suit,HandTarot> repartitionJouables_ = playableCards.couleurs();
        HandTarot trumps_ = repartitionJouables_.getVal(Suit.TRUMP);
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        Bytes joueursNonJoue_ = info_.getJoueursNonJoue();
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
            if (!repartition_.getVal(couleur_).premiereCarte().isCharacter()) {
                contFigureTtClr_ = false;
            }
        }
        if (contFigureTtClr_ && !couleurs_.isEmpty()) {
            return depouilleFigureEnCours(repartitionJouables_, couleurs_,
                    repartitionCartesJouees_);
        }
        contFigureTtClr_ = true;
        for (Suit couleur_ :  Suit.couleursOrdinaires()) {
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
