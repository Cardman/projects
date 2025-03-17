package cards.tarot;

import cards.consts.PossibleTrickWinner;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.*;

public final class GameTarotMisere {

    private final HandTarot currentHand;
    private final HandTarot playableCards;
    private final IdMap<Suit, HandTarot> repartitionJouables;
    private final TarotInfoPliEnCours tarotInfoPliEnCours;
    private final IdMap<Suit, HandTarot> repartitionCartesJouees;
    private final HandTarot atoutsJouables;
    private final HandTarot cartesJouees;
    private final IdMap<Suit, CustList<HandTarot>> cartesPossibles;
    private final IdMap<Suit, CustList<HandTarot>> cartesCertaines;
    private final CardTarot carteForte;
    private final Ints notPlayed;
    private final Ints notConfidentPlayers;
    private final CustList<TrickTarot> plisFaits;
    private final CustList<HandTarot> suitesAtoutsJouables;
    private final Suit couleurDemandee;
    private final HandTarot repartitionDemJouables;
    private final CustList<HandTarot> suitesDemJouables;

    public GameTarotMisere(GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation,
                           HandTarot _currentHand) {
        currentHand = _currentHand;
        GameTarotCommonPlaying common_ = new GameTarotCommonPlaying(_done, _teamsRelation);
        int nbPlayers_ = _teamsRelation.getNombreDeJoueurs();
        TrickTarot trTarot_ = _done.getProgressingTrick();
        int nextPlayer_ = trTarot_.getNextPlayer(nbPlayers_);
        playableCards = HandTarotResult.cartesJouables(_teamsRelation.getRules(),_teamsRelation.getTaker(),currentHand.couleurs(),_done.getProgressingTrick(),_done.getTricks(),new HandTarot()).getPlayable();
        repartitionJouables = playableCards.couleurs();
        Ints confidentPlayers_ = _teamsRelation.joueursConfiance(nextPlayer_, GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        Ints notConfidentPlayers_ = _teamsRelation.joueursNonConfiance(nextPlayer_, GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        tarotInfoPliEnCours = common_.initInformations(currentHand, confidentPlayers_, notConfidentPlayers_);
        repartitionCartesJouees = tarotInfoPliEnCours.getRepartitionCartesJouees();
        cartesJouees = tarotInfoPliEnCours.getCartesJouees();
        cartesPossibles = tarotInfoPliEnCours.getCartesPossibles();
        cartesCertaines = tarotInfoPliEnCours.getCartesCertaines();
        atoutsJouables = repartitionJouables.getVal(Suit.TRUMP);
        couleurDemandee = trTarot_.couleurDemandee();
        suitesAtoutsJouables = atoutsJouables.eclaterEnCours(repartitionCartesJouees, couleurDemandee);
        int ramasseurVirtuel_ = tarotInfoPliEnCours.getRamasseurVirtuel();
        int nombreDeJoueurs_ = _teamsRelation.getNombreDeJoueurs();
        if (!_done.getProgressingTrick().estVide()) {
            carteForte = _done.getProgressingTrick().carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        } else {
            carteForte = CardTarot.WHITE;
        }
        Ints played_ = trTarot_.joueursAyantJoue(nbPlayers_);
        int currentPlayer_ = trTarot_.getNextPlayer(nbPlayers_);
        notPlayed = GameTarotTeamsRelation.autresJoueurs(played_,nbPlayers_);
        notPlayed.removeObj(currentPlayer_);
        notConfidentPlayers = _teamsRelation.joueursNonConfiance(currentPlayer_,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        plisFaits = tarotInfoPliEnCours.getPlisFaits();
        repartitionDemJouables = repartitionJouables.getVal(couleurDemandee);
        suitesDemJouables = repartitionDemJouables
                .eclaterEnCours(repartitionCartesJouees, couleurDemandee);
    }

    CardTarot entame() {
        /* Jeu de misere */
        if (playableCards.total() == 1) {
            return playableCards.premiereCarte();
        }
        return entameMiserePetite();
    }
    private CardTarot entameMiserePetite() {
        HandTarot atoutsMaitres_ = atoutsJouables
                .atoutsMaitres(repartitionCartesJouees);

        if (atoutsJouables.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        if (atoutsJouables.total() == 1 && atoutsMaitres_.estVide()) {
            return atoutsJouables.premiereCarte();
        }
        return tryDiscard();
    }

    CardTarot tryDiscard() {
        HandTarot atoutsMaitres_ = atoutsJouables
                .atoutsMaitres(repartitionCartesJouees);

        Ints joueursNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(notPlayed, notConfidentPlayers);
        IdList<Suit> suits_  = new IdList<Suit>();
        for (Suit couleur_ : GameTarotCommon.couleursNonAtoutNonVides(playableCards,Suit.couleursOrdinaires())) {
            for (int joueur_ : joueursNonJoue_) {
                if (GameTarotTrickHypothesis.vaCouper(couleur_, joueur_, cartesPossibles, cartesCertaines)) {
                    suits_.add(couleur_);
                }
            }
        }
        suits_.removeDuplicates();
        if (!suits_.isEmpty()) {
            IdList<Suit> couleurs_ = GameTarotCommon.couleursAvecFigures(playableCards,suits_);
            if (!couleurs_.isEmpty()) {
                return depouilleFigure(couleurs_,
                        repartitionCartesJouees);
            }
            return depouillePetiteCarte(suits_,
                    repartitionCartesJouees);
        }
        if (!atoutsJouables.estVide()) {
            return discardTrump(atoutsMaitres_);
        }
        IdList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards,Suit.couleursOrdinaires());
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusGrandNbFigures(cartesJouees, couleurs_);
        return repartitionJouables.getVal(couleurs_.first()).derniereCarte();
    }
    private CardTarot discardTrump(HandTarot _atoutsMaitres) {
        if (_atoutsMaitres.estVide()) {
            return suitesAtoutsJouables.first().derniereCarte();
        }
        return atoutsJouables.derniereCarte();
    }


    CardTarot enCours() {
        if (playableCards.total() == 1) {
            return playableCards.premiereCarte();
        }
        if (couleurDemandee == Suit.UNDEFINED) {
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
        if (Suit.couleursOrdinaires().containsObj(couleurDemandee)) {
            if (!repartitionDemJouables.estVide()) {
                return fournirCouleurOrdinaireMisere();
            }
            if (!atoutsJouables.estVide()) {
                return coupeMisere();
            }
            return defausseMisere();
        }
        if (!repartitionDemJouables.estVide()) {
            return fournirAtoutMisere();
        }
        return defausseMisere();
    }

    private CardTarot fournirCouleurOrdinaireMisere() {

        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                tarotInfoPliEnCours
        );
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return repartitionDemJouables.premiereCarte();
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return repartitionDemJouables.derniereCarte();
        }
        int i_ = 0;
        int c_ = repartitionDemJouables.total();
        while (i_ < c_) {
            CardTarot card_ = repartitionDemJouables.carte(i_);
            if (card_.strength(couleurDemandee) < carteForte.strength(couleurDemandee)) {
                return card_;
            }
            i_++;
        }
        return suitesDemJouables.last().derniereCarte();
    }
    private CardTarot fournirAtoutMisere() {

        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                tarotInfoPliEnCours
        );
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (atoutsJouables.contient(CardTarot.petit())) {
                return CardTarot.petit();
            }
            return atoutsJouables.premiereCarte();
        }
        return atoutsJouables.premiereCarte();
    }
    private CardTarot coupeMisere() {
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                tarotInfoPliEnCours
        );
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (atoutsJouables.contient(CardTarot.petit())) {
                return CardTarot.petit();
            }
            return atoutsJouables.premiereCarte();
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            return atoutsJouables.premiereCarte();
        }
        Ints joueursNonJoue_ = GameTarotTeamsRelation.intersectionJoueurs(notPlayed, notConfidentPlayers);
        boolean surcoupePro_ = false;
        for (int joueur_ : joueursNonJoue_) {
            if (GameTarotTrickHypothesis.peutCouper(couleurDemandee, joueur_, cartesPossibles)) {
                surcoupePro_ = true;
            }
        }
        if (surcoupePro_
                && atoutsJouables.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        return atoutsJouables.premiereCarte();
    }
    private CardTarot defausseMisere() {
        PossibleTrickWinner ramasseurCertain_ = GameTarotTrickHypothesis.equipeQuiVaFairePli(
                tarotInfoPliEnCours
        );
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            IdList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, Suit.couleursOrdinaires());
            couleurs_ = GameTarotCommon.couleursSansFigures(currentHand,couleurs_);
            if (!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand,couleurs_);
                return repartitionJouables.getVal(couleurs_.first()).premiereCarte();
            }
            couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, Suit.couleursOrdinaires());
            couleurs_ = GameTarotCommon.couleursAvecCartesBasses(currentHand,couleurs_);
            if (!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand,couleurs_);
                return repartitionJouables.getVal(couleurs_.first()).derniereCarte();
            }
        }
        IdMap<Suit,HandTarot> repartition_ = currentHand.couleurs();

        IdList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, Suit.couleursOrdinaires());
        boolean contFigureTtClr_ = contFigureTtClr(repartition_, couleurs_);
        if (contFigureTtClr_) {
            return depouilleFigureEnCours(couleurs_,
                    repartitionCartesJouees);
        }
        contFigureTtClr_ = true;
        for (Suit couleur_ :  Suit.couleursOrdinaires()) {
            if (repartition_.getVal(couleur_).estVide()) {
                contFigureTtClr_ = false;
            }
        }
        if (contFigureTtClr_) {
            IdList<Suit> couleursNonEntamees_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(playableCards, plisFaits,  Suit.couleursOrdinaires());
            couleursNonEntamees_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(currentHand, couleursNonEntamees_, 1);
            couleursNonEntamees_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, couleursNonEntamees_);
            if (!couleursNonEntamees_.isEmpty()) {
                return singletonFortDepouille(
                        couleursNonEntamees_);
            }
            couleursNonEntamees_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(playableCards, couleurs_, 1);
            couleursNonEntamees_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, couleursNonEntamees_);
            if (!couleursNonEntamees_.isEmpty()) {
                return singletonFortDepouille(
                        couleursNonEntamees_);
            }
        }
        IdList<Suit> couleursAvecFigures_ = GameTarotCommon.couleursAvecFigures(playableCards, couleurs_);
        if (!couleursAvecFigures_.isEmpty()) {
            return depouilleFigureDefausse(
                    couleursAvecFigures_, repartitionCartesJouees);
        }
        return enCoursMiserePetite(couleurs_,
                repartitionCartesJouees);
    }

    private boolean contFigureTtClr(IdMap<Suit, HandTarot> _repartition, IdList<Suit> _couleurs) {
        boolean contFigureTtClr_ = true;
        for (Suit couleur_ : _couleurs) {
            if (!_repartition.getVal(couleur_).premiereCarte().isCharacter()) {
                contFigureTtClr_ = false;
            }
        }
        return contFigureTtClr_;
    }

    TarotInfoPliEnCours initInformations() {
        return tarotInfoPliEnCours;
    }

    private CardTarot depouilleFigure(IdList<Suit> _couleurs,
                                      IdMap<Suit, HandTarot> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(repartitionJouables), _couleurs);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(HandTarot.reunion(repartitionJouables), couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        return repartitionJouables.getVal(couleurs_.first()).premiereCarte();
    }

    private CardTarot depouillePetiteCarte(IdList<Suit> _couleurs,
                                           IdMap<Suit, HandTarot> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(repartitionJouables), _couleurs);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(HandTarot.reunion(repartitionJouables), couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        return repartitionJouables.getVal(couleurs_.first()).derniereCarte();
    }


    private CardTarot enCoursMiserePetite(IdList<Suit> _couleurs,
                                          IdMap<Suit, HandTarot> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(repartitionJouables), _couleurs);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(HandTarot.reunion(repartitionJouables), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(HandTarot.reunion(repartitionJouables), couleurs_);
        couleurs_ = GameTarotCommon.couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        return repartitionJouables.getVal(couleurs_.first()).derniereCarte();
    }

    private CardTarot depouilleFigureDefausse(
            IdList<Suit> _couleurs,
            IdMap<Suit, HandTarot> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(repartitionJouables), _couleurs);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(HandTarot.reunion(repartitionJouables), couleurs_);
        return repartitionJouables.getVal(couleurs_.first()).premiereCarte();
    }

    private CardTarot singletonFortDepouille(
            IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusHautes(HandTarot.reunion(repartitionJouables), _couleurs);
        return repartitionJouables.getVal(couleurs_.first()).premiereCarte();
    }

    private CardTarot depouilleFigureEnCours(
            IdList<Suit> _couleurs,
            IdMap<Suit, HandTarot> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusCourtes(HandTarot.reunion(repartitionJouables), _couleurs);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(HandTarot.reunion(repartitionJouables), couleurs_);
        return repartitionJouables.getVal(couleurs_.first()).premiereCarte();
    }

}
