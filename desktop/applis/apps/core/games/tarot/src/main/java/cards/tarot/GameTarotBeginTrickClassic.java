package cards.tarot;

import cards.consts.Role;
import cards.consts.Suit;
import cards.tarot.comparators.GameSeqLengthTarotComparator;
import cards.tarot.enumerations.CardTarot;
import code.maths.Rate;
import code.util.*;
import code.util.core.NumberUtil;

public final class GameTarotBeginTrickClassic {

    private final GameTarotTeamsRelation teamsRelation;
    private final GameTarotTrickInfo info;

    private final HandTarot calledCards;

    private final HandTarot currentHand;
    private final GameTarotCommonPlaying common;
    private final Role currentStatus;
    private final Ints confidentPlayers;
    private final Ints notConfidentPlayers;
    private final Ints notPlayed;
    private final Ints confidentPlayersNotPlay;
    private final Ints notConfidentPlayersNotPlay;
    private final HandTarot playableCards;
    private final IdMap<Suit, HandTarot> repartitionJouable;
    private final HandTarot atoutsJouables;
    private final HandTarot atoutsJouablesSansPetit;
    private final TarotInfoPliEnCours tarotInfoPliEnCours;
    private final IdMap<Suit, HandTarot> repartitionCartesJouees;
    private final HandTarot atoutsMaitresJouables;
    private final IdList<Suit> couleursAppelees;
    private final IdMap<Suit, CustList<HandTarot>> cartesPossibles;
    private final IdMap<Suit, CustList<HandTarot>> cartesCertaines;
    private final HandTarot cartesJouees;
    private final IdMap<Suit, CustList<HandTarot>> suitesTouteCouleur;
    private final CustList<TrickTarot> plisFaits;
    private final int currentPlayer;
    private final IdMap<Suit, HandTarot> repartition;
    private final IdMap<Suit, HandTarot> cartesMaitresses;
    private final boolean carteAppeleeJouee;
    private final IdList<Suit> couleursMaitresses;
    private final boolean maitreAtout;

    public GameTarotBeginTrickClassic(GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation, HandTarot _calledCards, HandTarot _currentHand) {
        teamsRelation = _teamsRelation;
        calledCards = _calledCards;
        currentHand = _currentHand;
        info = _done;
        common = new GameTarotCommonPlaying(_done,_teamsRelation);
        int nbPlayers_ = _teamsRelation.getNombreDeJoueurs();
        TrickTarot trTarot_ = _done.getProgressingTrick();
        Ints played_ = trTarot_.joueursAyantJoue(nbPlayers_);
        notPlayed = GameTarotTeamsRelation.autresJoueurs(played_,nbPlayers_);
        currentPlayer = trTarot_.getNextPlayer(nbPlayers_);
        repartition = currentHand.couleurs();
        playableCards = HandTarotResult.cartesJouables(_teamsRelation.getRules(), _teamsRelation.getTaker(), repartition,_done.getProgressingTrick(),_done.getTricks(),_calledCards).getPlayable();
        notPlayed.removeObj(currentPlayer);
        currentStatus = _teamsRelation.statutDe(currentPlayer);
        confidentPlayers = _teamsRelation.joueursConfiance(currentPlayer,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        notConfidentPlayers = _teamsRelation.joueursNonConfiance(currentPlayer,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        confidentPlayersNotPlay = GameTarotTeamsRelation.intersectionJoueurs(confidentPlayers,notPlayed);
        notConfidentPlayersNotPlay = GameTarotTeamsRelation.intersectionJoueurs(notConfidentPlayers,notPlayed);
        repartitionJouable = playableCards.couleurs();
        atoutsJouables = repartitionJouable.getVal(Suit.TRUMP);
        atoutsJouablesSansPetit = GameTarotCommonPlaying.atoutsSansPetit(atoutsJouables);
        tarotInfoPliEnCours = common.initInformations(currentHand, confidentPlayers, notConfidentPlayers);
        cartesPossibles = tarotInfoPliEnCours.getCartesPossibles();
        cartesCertaines = tarotInfoPliEnCours.getCartesCertaines();
        cartesJouees = tarotInfoPliEnCours.getCartesJouees();
        suitesTouteCouleur = tarotInfoPliEnCours.getSuitesTouteCouleur();
        repartitionCartesJouees = tarotInfoPliEnCours.getRepartitionCartesJouees();
        atoutsMaitresJouables = atoutsJouables
                .atoutsMaitres(repartitionCartesJouees);
        couleursAppelees = common.couleursAppelees();
        plisFaits = tarotInfoPliEnCours.getPlisFaits();
        cartesMaitresses = tarotInfoPliEnCours.getCartesMaitresses();
        couleursMaitresses = tarotInfoPliEnCours.getCouleursMaitresses();
        carteAppeleeJouee = tarotInfoPliEnCours.isCarteAppeleeJouee();
        maitreAtout = tarotInfoPliEnCours.isMaitreAtout();
    }
    CardTarot entameSurExcuseClassique() {
        int starter_ = info.getProgressingTrick().getEntameur();
        if(starter_ == teamsRelation.getTaker()) {
            if(currentStatus == Role.CALLED_PLAYER) {
                return playAsCalledPlayerOnExc();
            }
            //defenseur entamant sur l'excuse du preneur
            if(NumberUtil.equalsSetInts(confidentPlayersNotPlay,notPlayed)) {
                return playAsDefenderTeam();
            }
            if(common.appeleConnuDefenseur(currentPlayer,cartesPossibles)) {
                return playAsDefenderWhenFoundCall();
            }
        }
        return entameClassique();
    }
    CardTarot playAsCalledPlayerOnExc() {
        if(!atoutsJouablesSansPetit.estVide()) {
            return atoutsJouablesSansPetit.premiereCarte();
        }
        IdList<Suit> couleursAjouer_ = couleursNonAppelees();

        IdList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, couleursAjouer_);
        IdList<Suit> couleurs_ = couleursOuvertes(currentHand,
                plisFaits, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            IdList<Suit> couleursCoupeesAdv_ = couleursCoupeeParJoueurs(currentHand,
                    notConfidentPlayersNotPlay, cartesPossibles,
                    cartesCertaines, couleurs_);
            if(!couleursCoupeesAdv_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursCoupeesAdv_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
            }
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(couleurs_, cartesMaitresses);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
        }
        return entameClassique();
    }
    CardTarot playAsDefenderTeam() {
        //jeu atout s'il n'exite aucun partenaire avec un atout
        // ou si le defenseur possede le petit
        if (GameTarotTrickHypothesis.joueursPossedantAtout(confidentPlayersNotPlay, cartesCertaines).isEmpty() && !atoutsJouables.estVide()) {
            return atoutsJouables.derniereCarte();
        }
        if(atoutsJouables.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        IdList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
        IdList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, couleursAjouer_);
        IdList<Suit> couleurs_ = couleursNonVidesAjouer_;
        IdList<Suit> couleursCoupees_ = couleursCoupeePar(currentHand,
                teamsRelation.getTaker(), cartesPossibles,
                cartesCertaines, couleurs_);
        if(!couleursCoupees_.isEmpty()) {
            couleurs_ = couleursNonTotalementJoueesEnFigures(cartesJouees, couleursCoupees_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                CardTarot carteHaute_ = repartitionJouable.getVal(couleurs_.first()).premiereCarte();
                if(carteHaute_.isCharacter()) {
                    return carteHaute_;
                }
                return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
            }
        }
        couleurs_ = couleursNonOuvertesAttaque(currentHand,
                plisFaits, notConfidentPlayers, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
        return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
    }
    CardTarot playAsDefenderWhenFoundCall() {
        //L'appele existe et est connu du defenseur courant
        IdList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
        IdList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, couleursAjouer_);
        IdList<Suit> couleurs_ = couleursNonVidesAjouer_;
        boolean pasAtoutAppeles_ = true;
        for (int a: teamsRelation.getCalledPlayers()) {
            if (!cartesPossibles.getVal(Suit.TRUMP).get(a).estVide()) {
                pasAtoutAppeles_ = false;
                break;
            }
        }
        if (pasAtoutAppeles_) {
            //attention s'il existe une couleur que l'appele ne possede pas
            //attention s'il existe une couleur avec carte rel maitre sur l'appele
            HandTarot cartesPossiblesAppele_ = new HandTarot();
            for (int a: teamsRelation.getCalledPlayers()) {
                for (Suit couleur_ : Suit.couleursOrdinaires()) {
                    cartesPossiblesAppele_.ajouterCartes(cartesPossibles.getVal(couleur_).get(a));
                }
            }
            couleurs_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(cartesPossiblesAppele_, couleurs_, 0);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
                return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
            }
            return entameClassique();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursAvecFigures(currentHand, couleurs_);
        couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(couleurs_, cartesMaitresses);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
        }
        return entameClassique();
    }
    CardTarot entameClassique() {
        if (playableCards.total() == 1) {
            return playableCards.premiereCarte();
        }
        if (couleursMaitresses.size() == Suit.couleursOrdinaires().size() && maitreAtout) {
            return playWithStrongestHand();
        }
        if(maitreAtout() && currentStatus == Role.TAKER) {
            CardTarot card_ = playWithStrongestTrumps();
            if (card_ != CardTarot.WHITE) {
                return card_;
            }
        }
        CardTarot cardTr_ = playWithAtMostOneSuitCard();
        if (cardTr_ != CardTarot.WHITE) {
            return cardTr_;
        }
        HandTarot atouts_ = repartition.getVal(Suit.TRUMP);
        boolean atoutsTousJoues_ = !common.premierTour() && atouts_.total() + repartitionCartesJouees.getVal(Suit.TRUMP).total() == HandTarot.atoutsSansExcuse().total();
        if(atoutsTousJoues_) {
            CardTarot card_ = playAfterAllTrumps();
            if (card_ != CardTarot.WHITE) {
                return card_;
            }
        }
        CardTarot card_ = tryDemandTrumpSuit();
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        //differents cas de jeu
        if(teamsRelation.isVirtualTaker(currentPlayer)) {
            return playAsTaker();
        }
        if(currentStatus == Role.CALLED_PLAYER) {
            return playAsCalledPlayer();
        }
        return playAsDefender();
        //couleur appelee

    }

    boolean maitreAtout() {
        return GameTarotCommonPlaying.maitreAtout(suitesTouteCouleur.getVal(Suit.TRUMP),
                cartesJouees, playableCards.contient(CardTarot.excuse()));
    }
    TarotInfoPliEnCours initInformations() {
        return tarotInfoPliEnCours;
    }
    CardTarot playWithAtMostOneSuitCard() {
        if(playableCards.total() == atoutsJouables .total() + repartitionJouable.getVal(CardTarot.excuse().getId().getCouleur()).total()) {
            //il n'y a que de l'atout (ev excuse)
            if (!playableCards.contient(CardTarot.excuse())) {
                return jeuAtoutOffensif();
            }
            if(atoutsJouables.total() + repartitionJouable.getVal(CardTarot.excuse().getId().getCouleur()).total() == 2) {
                if(!playExc(teamsRelation.getNombreDeJoueurs(),currentPlayer,plisFaits,carteAppeleeJouee)) {
                    return atoutsJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            return jeuAtoutOffensif();
        }
        IdList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
        if (couleurs_.size() == 1) {
            HandTarot uniqueCouleur_ = repartitionJouable.getVal(couleurs_.first());
            if (uniqueCouleur_.total() == 1 && !atoutsJouables.estVide()) {
                return tryPlay(couleurs_, uniqueCouleur_.premiereCarte());
            }
        }
        return CardTarot.WHITE;
    }

    private CardTarot tryPlay(IdList<Suit> _couleurs, CardTarot _cartCouleur) {
        //une seule carte de couleur est presente
        Ints partenaires_ = GameTarotTrickHypothesis.joueursPouvantCouperCouleurs(currentHand, confidentPlayersNotPlay,
                cartesPossibles, _couleurs);
        partenaires_ = GameTarotTrickHypothesis.joueursPossedantAtoutMaitre(partenaires_, cartesCertaines,
                cartesJouees);
        if (!partenaires_.isEmpty()) {
            return _cartCouleur;
        }
        return tryPlayWithoutPartner(_cartCouleur);
    }

    private CardTarot tryPlayWithoutPartner(CardTarot _cartCouleur) {
        if (couleursMaitresses.size() == Suit.couleursOrdinaires().size()) {
            if (atoutsJouables.total() == 1 && atoutsJouables.contient(CardTarot.petit())) {
                return _cartCouleur;
            }
            int nbAtoutsMaitres_ = atoutsMaitresJouables.total();
            if (nbAtoutsMaitres_ > 0) {
                atoutsMaitresJouables.trierParForceEnCours(Suit.TRUMP);
                return atoutsMaitresJouables.premiereCarte();
            }
            if (!_cartCouleur.isCharacter()) {
                return _cartCouleur;
            }
            atoutsJouables.trierParForceEnCours(Suit.TRUMP);
            if (atoutsJouables.contient(CardTarot.petit())) {
                return atoutsJouables.premiereCarte();
            }
            return atoutsJouables.derniereCarte();
        }
        int nbAtoutsMaitres_ = atoutsMaitresJouables.total();
        if (nbAtoutsMaitres_ > 0) {
            if (currentHand.total() == 2 && atoutsJouables.contient(CardTarot.petit())) {
                return _cartCouleur;
            }
            atoutsMaitresJouables.trierParForceEnCours(Suit.TRUMP);
            return atoutsMaitresJouables.premiereCarte();
        }
        if (GameTarotTrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles, _cartCouleur.getId().getCouleur(), notConfidentPlayersNotPlay).isEmpty()) {
            return _cartCouleur;
        }
        return CardTarot.WHITE;
    }

    CardTarot playWithStrongestTrumps() {
        IdMap<Suit,HandTarot> repartition_ = repartition;
        IdList<Suit> couleursStrictementMaitresses_ = GameTarotCommonPlaying.strictCouleursMaitres(
                suitesTouteCouleur, repartitionCartesJouees, cartesPossibles, currentPlayer);
        IdList<Suit> couleursNonAppelees_ = couleursNonAppelees(Suit.couleursOrdinaires());
        IdMap<Suit, HandTarot> cards_ = GameTarotBid.cartesPseudoMaitresses(repartition_, calledCards,
                repartitionCartesJouees);
        IdList<Suit> couleursPseudosMaitres_ = GameTarotBid.couleursPseudosMaitres(
                repartition_,
                cards_);
        int nbAtoutsMaitres_ = atoutsMaitresJouables.total();
        if(atoutsMaitresJouables.contient(CardTarot.petit())) {
            nbAtoutsMaitres_--;
        }
        IdList<Suit> couleursAppeleesNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards,couleursAppelees);
        boolean leadTrNotFirstRd_ = maitreAtout;
        if (common.premierTour()) {
            leadTrNotFirstRd_ = false;
        }
        if (couleursAppeleesNonVides_.isEmpty() || !couleursPseudosMaitres_.containsAllObj(couleursAppelees)) {
            return CardTarot.WHITE;
        }
        if(maitreAtout&&nbAtoutsMaitres_ > 1) {
            atoutsMaitresJouables.trierParForceEnCours(Suit.TRUMP);
            return atoutsMaitresJouables.premiereCarte();
        }
        if (leadTrNotFirstRd_) {
            couleursAppeleesNonVides_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursAppeleesNonVides_);
            couleursAppeleesNonVides_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleursAppeleesNonVides_);
            if(cards_.getVal(couleursAppeleesNonVides_.first()).total() > 1) {
                return carteCouleurAppeleeSousCarte(playableCards, couleursAppeleesNonVides_.first(), calledCards);
            }
        }
        if (maitreAtout) {
            IdList<Suit> couleursStrictementMaitressesNonAppelees_ = couleursNonAppelees(couleursStrictementMaitresses_);
            IdList<Suit> couleursMaitres_ = couleursMaitresses;
            IdList<Suit> suitsNoCall_ = couleursNonAppelees(couleursMaitres_);
            suitsNoCall_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards,suitsNoCall_);
            if(!suitsNoCall_.isEmpty() && couleursStrictementMaitressesNonAppelees_.containsAllObj(couleursNonAppelees_)) {
                couleursMaitres_ =GameTarotCommon.couleursLesPlusLongues(currentHand, suitsNoCall_);
                HandTarot couleurCandidate_ = repartitionJouable.getVal(couleursMaitres_.first());
                couleurCandidate_.trierParForceEnCours(couleursMaitres_.first());
                return couleurCandidate_.premiereCarte();
            }
        }
        return CardTarot.WHITE;
    }
    CardTarot playAfterAllTrumps() {
        IdList<Suit> notEmptySuits_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, Suit.couleursOrdinaires());
        notEmptySuits_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(notEmptySuits_, cartesMaitresses);
        if(!notEmptySuits_.isEmpty()) {
            return jeuAvecCarteMaitresseSansAtout(playableCards, cartesJouees, notEmptySuits_);
        }
        return CardTarot.WHITE;
    }
    CardTarot tryDemandTrumpSuit() {
        if(!GameTarotTrickHypothesis.joueursPouvantPossederPetit(confidentPlayersNotPlay,
                cartesPossibles).isEmpty()) {
            int nbAtoutsMaitres_ = atoutsMaitresJouables.total();
            if(nbAtoutsMaitres_ > 0) {
                atoutsMaitresJouables.trierParForceEnCours(Suit.TRUMP);
                return atoutsMaitresJouables.premiereCarte();
            }
        }
        if (!GameTarotTrickHypothesis.joueursPossedantAtoutMaitre(confidentPlayersNotPlay,
                cartesCertaines, cartesJouees).isEmpty() && atoutsJouables.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        HandTarot atoutsJoues_ = cartesJouees.couleur(Suit.TRUMP);
        int nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        Rate moyenneAtout_ = GameTarotCommonPlaying.moyenneAtout(currentHand, atoutsJoues_,
                cartesPossibles, nombreDeJoueurs_);
        if (!GameTarotTrickHypothesis.joueursPossedantNbAtout(confidentPlayersNotPlay,
                cartesCertaines, moyenneAtout_).isEmpty() && atoutsJouables.total() > NumberUtil.max(moyenneAtout_.ll(), 1)) {
            return jeuAtoutOffensif();
        }
        return CardTarot.WHITE;
    }
    CardTarot playAsDefender() {
        IdList<Suit> couleursAjouer_ = couleursNonAppelees();
        IdList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, couleursAjouer_);
        IdList<Suit> couleursNonVides_ = couleursNonVides(couleursNonVidesAjouer_);
        //Defenseur
        if (!carteAppeleeJouee && !common.premierTour()) {
            for(Suit c: couleursAppelees) {
                if (!repartitionJouable.getVal(c).estVide() && GameTarotCommon.couleursAvecCartesBasses(playableCards, Suit.couleursOrdinaires()).containsObj(c)) {
                    return repartitionJouable.getVal(c).derniereCarte();
                }
            }
        }

        //ouvreur
        if(defenseOuvreur(currentPlayer,carteAppeleeJouee)) {
            return playAsDefenderStartNew(currentPlayer, couleursNonVidesAjouer_, couleursNonVides_);
            //couleur appelee
        }
        IdList<Suit> couleurs_ = couleursOuvertes(playableCards,
                plisFaits, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(playableCards, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommonPlaying.couleursLesMoinsEntameesParJoueurs(plisFaits, notConfidentPlayersNotPlay, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(playableCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(playableCards, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(playableCards,
                plisFaits, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommonPlaying.couleursLesMoinsEntameesParJoueurs(plisFaits, notConfidentPlayersNotPlay, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(playableCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(playableCards, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        return playDefaultCardAsDefender(cartesJouees, couleursNonVides_);
    }

    private CardTarot playAsDefenderStartNew(int _next, IdList<Suit> _couleursNonVidesAjouer, IdList<Suit> _couleursNonVides) {
        if(defenseOuvreurStrict(_next)) {
            IdList<Suit> couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(playableCards,
                    plisFaits, _couleursNonVidesAjouer);
            couleurs_ = GameTarotCommon.couleursSansFigures(playableCards, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(playableCards, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(playableCards, couleurs_);
                return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
            }
        }
        IdList<Suit> couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(playableCards,
                plisFaits, _couleursNonVidesAjouer);
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(playableCards, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(playableCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(playableCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(playableCards,
                plisFaits, _couleursNonVidesAjouer);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(playableCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(playableCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        return playDefaultCardAsDefender(cartesJouees, _couleursNonVides);
    }

    private IdList<Suit> couleursNonVides(IdList<Suit> _couleursNonVidesAjouer) {
        IdList<Suit> couleursNonVides_ = _couleursNonVidesAjouer;
        if (couleursNonVides_.isEmpty()) {
            couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards,Suit.couleursOrdinaires());
        }
        return couleursNonVides_;
    }

    private CardTarot playDefaultCardAsDefender(HandTarot _cartesJouees, IdList<Suit> _couleursNonVidesAjouer) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusBasses(playableCards, _couleursNonVidesAjouer);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(playableCards, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouees, couleurs_);
        return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
    }

    static boolean allSuitOwnLeadingCard(IdMap<Suit, HandTarot> _repartition, IdMap<Suit, HandTarot> _cartesMaitresses, IdList<Suit> _suits) {
        boolean touteCouleurPossedeCarteMaitresse_ = true;
        for (Suit couleur_ : _suits) {
            if (!_repartition.getVal(couleur_).estVide() && _cartesMaitresses.getVal(couleur_).estVide()) {
                touteCouleurPossedeCarteMaitresse_ = false;
                break;
            }
        }
        return touteCouleurPossedeCarteMaitresse_;
    }

    static boolean noTrumping(IdMap<Suit, HandTarot> _repartition, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Ints _joueursNonJoue) {
        boolean aucuneCoupe_ = true;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            boolean plusCartesCouleurAutres_ = true;
            for(int joueur_: _joueursNonJoue) {
                if (!_cartesPossibles.getVal(couleur_).get(joueur_).estVide()) {
                    plusCartesCouleurAutres_ = false;
                    break;
                }
            }
            if (!plusCartesCouleurAutres_ && _repartition.getVal(couleur_).estVide()) {
                aucuneCoupe_ = false;
                break;
            }
        }
        return aucuneCoupe_;
    }

    /**
     @param _player
     @param _hand
     @param _playableCards
     @param _dealing
     @param _playedCards
     @param _playedTrumps
     @param _tricks
     @param _playedCalledCard
     @param _possibleCards
     @param _dog
     @param _playersNoPlayed
     @param _noConfidentPlayers
     @param _nbPlayers
     @param _allSuitsWithLeadCard
     @param _nbPlayersTwo
     @return
     */
    CardTarot playAsCalledPlayer() {
        int nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        HandTarot atoutsJoues_ = cartesJouees.couleur(Suit.TRUMP);
        boolean touteCouleurPossedeCarteMaitresse_ = allSuitOwnLeadingCard(repartition, cartesMaitresses, Suit.couleursOrdinaires());
        int taker_ = teamsRelation.getTaker();
        boolean takerNoPlay_ = notPlayed.containsObj(taker_);
        IdList<Suit> couleursAjouer_ = couleursNonAppelees();
        IdList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, couleursAjouer_);
        IdList<Suit> couleursNonVides_ = couleursNonVides(couleursNonVidesAjouer_);
        CardTarot calledCardPoss_ = playAsCalledPlayerCalledCard();
        if (calledCardPoss_ != CardTarot.WHITE) {
            return calledCardPoss_;
        }
        boolean afterTaker_ = GameTarotTeamsRelation.justeApresJoueur(currentPlayer, taker_, nombreDeJoueurs_);
        boolean takerLast_ = afterTaker_ && takerNoPlay_;
        boolean saveLeading_ = jouerAtout(atoutsJoues_,
                cartesPossibles) && touteCouleurPossedeCarteMaitresse_;
        if(takerLast_) {
            return playAsCalledPlayerTakerLast(couleursNonVidesAjouer_, couleursNonVides_, saveLeading_);
        }
        IdList<Suit> couleurs_;
        Ints defenseurs_ = joueursAvantAppeleApresPreneur(currentPlayer);
        boolean defausseTousDefenseursIntermediaire_ = defausseTousJoueursNonConfiance(cartesPossibles, defenseurs_);
        if(defausseTousDefenseursIntermediaire_
                && cartesPossibles.getVal(Suit.TRUMP).get(taker_).contient(CardTarot.petit())
                && takerNoPlay_) {
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees, couleursNonVides_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        Ints partenaires_ = new Ints();
        partenaires_.add(taker_);
        couleurs_ = couleursEntameesParJoueurs(plisFaits, partenaires_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursEntameesParJoueurs(plisFaits, partenaires_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        if(saveLeading_) {
            return jeuAtoutOffensif();
        }
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits, couleursNonVidesAjouer_);
        couleurs_ = couleursSansCarteMaitresse(currentHand, couleurs_, cartesMaitresses);
        couleurs_ = couleursCoupeePar(currentHand,
                taker_, cartesPossibles,
                cartesCertaines, couleurs_);
        if(!couleurs_.isEmpty() && takerNoPlay_) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        return defCalledPlayer(couleursNonVidesAjouer_);
    }

    private CardTarot defCalledPlayer(IdList<Suit> _couleursNonVidesAjouer) {
        HandTarot cartesChien_ = common.cartesVuesAuChien();
        IdList<Suit> couleurs_;
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits, _couleursNonVidesAjouer);
        if(!couleurs_.isEmpty()) {
            boolean defausseTousJoueurs_ = defausseTousJoueursNonConfiance(cartesPossibles, notConfidentPlayersNotPlay);
            if(defausseTousJoueurs_) {
                IdList<Suit> couleursMaitres_ = couleursAvecCartesMaitressesVuesChien(currentHand, cartesJouees, cartesChien_,couleurs_);
                if (!couleursMaitres_.isEmpty()) {
                    couleurs_ = couleursMaitres_;
                }
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
            }
            IdList<Suit> couleursCoupeesAdv_ = couleursCoupeeParJoueurs(currentHand,
                    notConfidentPlayersNotPlay, cartesPossibles,
                    cartesCertaines, couleurs_);
            if(!couleursCoupeesAdv_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursCoupeesAdv_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursAvecCartesMaitressesVuesChien(currentHand, cartesJouees,
                cartesChien_, _couleursNonVidesAjouer);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, Suit.couleursOrdinaires());
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
        return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
    }

    private CardTarot playAsCalledPlayerCalledCard() {
        int taker_ = teamsRelation.getTaker();
        boolean takerNoPlay_ = notPlayed.containsObj(taker_);
        if (!takerNoPlay_) {
            return CardTarot.WHITE;
        }
        HandTarot cartesPossedeesAppeleesJouable_ = new HandTarot();
        for(CardTarot c: calledCards) {
            if(playableCards.contient(c)) {
                cartesPossedeesAppeleesJouable_.ajouter(c);
            }
        }
        boolean jouerUneCouleurAppelee_ = false;
        if(!cartesPossedeesAppeleesJouable_.estVide()) {
            boolean defausseTousJoueurs_ = defausseTousJoueursNonConfiance(cartesPossibles, notConfidentPlayersNotPlay);
            if(defausseTousJoueurs_) {
                jouerUneCouleurAppelee_ = true;
            }
            CustList<HandTarot> atoutsJoueursSur_ = cartesCertaines.getVal(Suit.TRUMP);
            if (atoutsJoueursSur_.get(taker_).contient(CardTarot.TRUMP_1) && currentHand.contient(CardTarot.TRUMP_21)) {
                jouerUneCouleurAppelee_ = true;
            }
        }
        if(jouerUneCouleurAppelee_) {
            return cartesPossedeesAppeleesJouable_.premiereCarte();
        }
        return CardTarot.WHITE;
    }

    private CardTarot playAsCalledPlayerTakerLast(IdList<Suit> _couleursNonVidesAjouer, IdList<Suit> _couleursNonVides, boolean _saveLeading) {
        int taker_ = teamsRelation.getTaker();
        IdList<Suit> couleurs_;
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits, _couleursNonVidesAjouer);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits, _couleursNonVidesAjouer);
        couleurs_ = couleursSansCarteMaitresse(currentHand, couleurs_, cartesMaitresses);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits, _couleursNonVidesAjouer);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursCoupeePar(currentHand,
                taker_, cartesPossibles,
                cartesCertaines, _couleursNonVidesAjouer);
        if (!couleurs_.isEmpty() && cartesPossibles.getVal(Suit.TRUMP).get(taker_).contient(CardTarot.petit())) {
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        if(_saveLeading) {
            return jeuAtoutOffensif();
        }
        Ints partenaires_ = new Ints();
        partenaires_.add(taker_);
        couleurs_ = GameTarotCommonPlaying.couleursLesPlusEntameesParJoueurs(plisFaits, partenaires_, _couleursNonVides);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
        return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
    }

    /**
     @param _hand
     @param _playableCards
     @param _dealing
     @param _playedCards
     @param _playedTrumps
     @param _tricks
     @param _playedCalledCard
     @param _possibleCards
     @param _sureCards
     @param _calledSuits
     @param _playerNoPlay
     @param _confidentPlayers
     @param _noConfidentPlayers
     @param _nbPlayers
     @param _trumpCards
     @param _noPossibleTrump
     @param _allSuitsWithLeadCard
     @param _nbPlayersTwo
     @return
     */
    CardTarot playAsTaker() {
        HandTarot atoutsJoues_ = cartesJouees.couleur(Suit.TRUMP);
        boolean plTr_ = jouerAtout(atoutsJoues_,
                cartesPossibles);
        CardTarot calledCardToPlay_ = possiblePlayCalledCards();
        if (calledCardToPlay_ != CardTarot.WHITE) {
            return calledCardToPlay_;
        }
        IdList<Suit> couleursAjouer_ = couleursNonAppelees();
        IdList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, couleursAjouer_);
        IdList<Suit> couleurs_ = couleursOuvertes(currentHand,
                plisFaits, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            IdList<Suit> couleursPossiblementCoupees_ = GameTarotTrickHypothesis.couleursPouvantEtreCoupees(
                    notPlayed,
                    cartesPossibles, couleurs_);
            if(!couleursPossiblementCoupees_.isEmpty()) {
                couleursPossiblementCoupees_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursPossiblementCoupees_);
                couleursPossiblementCoupees_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleursPossiblementCoupees_);
                return repartitionJouable.getVal(couleursPossiblementCoupees_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits, couleursNonVidesAjouer_);
        couleurs_ = couleursSansCarteMaitresse(currentHand, couleurs_, cartesMaitresses);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursSansCarteMaitresse(currentHand, couleursNonVidesAjouer_, cartesMaitresses);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        if(plTr_) {
            return jeuAtoutOffensif();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(couleurs_, cartesMaitresses);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(currentHand,
                couleursNonVidesAjouer_);
        couleurs_ = couleursCoupeeParJoueurs(currentHand,
                notConfidentPlayersNotPlay, cartesPossibles,
                cartesCertaines, couleurs_);
        if(!couleurs_.isEmpty()) {
            IdList<Suit> couleursNonCoupeesJoueursConfiance_ = couleursNonCoupeeParJoueurs(currentHand,
                    confidentPlayersNotPlay, cartesPossibles,
                    cartesCertaines, couleurs_);
            if(!couleursNonCoupeesJoueursConfiance_.isEmpty()) {
                couleursNonCoupeesJoueursConfiance_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursNonCoupeesJoueursConfiance_);
                couleursNonCoupeesJoueursConfiance_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleursNonCoupeesJoueursConfiance_);
                couleursNonCoupeesJoueursConfiance_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleursNonCoupeesJoueursConfiance_);
                return repartitionJouable.getVal(couleursNonCoupeesJoueursConfiance_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursNonVidesAjouer_;
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable.getVal(couleurs_.first()).premiereCarte();
        }
        return playDefaultCard();
    }
    private CardTarot possiblePlayCalledCards() {
        boolean defausseTousJoueurs_ = defausseTousJoueursNonConfiance(cartesPossibles, notConfidentPlayersNotPlay);
        IdList<Suit> couleursAjouer_ = couleursNonAppelees();
        if (!carteAppeleeJouee) {
            boolean touteCouleurPossedeCarteMaitresse_ = allSuitOwnLeadingCard(repartitionJouable, cartesMaitresses,couleursAjouer_);
            for(Suit c: couleursAppelees) {
                if (!repartitionJouable.getVal(c).estVide() && defausseTousJoueurs_ && touteCouleurPossedeCarteMaitresse_) {
                    //jouer la couleur appelee dans certains cas
                    return repartitionJouable.getVal(c).derniereCarte();
                }
            }
        }
        return CardTarot.WHITE;
    }

    private boolean defausseTousJoueursNonConfiance(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Ints _foeTeam) {
        boolean defausseTousJoueurs_ = true;
        CustList<HandTarot> atoutsJoueurs_ = _cartesPossibles.getVal(Suit.TRUMP);
        for(int joueur_: _foeTeam) {
            if (!atoutsJoueurs_.get(joueur_).estVide()) {
                defausseTousJoueurs_ = false;
                break;
            }
        }
        return defausseTousJoueurs_;
    }

    CardTarot playDefaultCard() {
        IdList<Suit> couleurs_;
        if (!atoutsJouables.estVide()) {
            atoutsJouables.trierParForceEnCours(Suit.TRUMP);
            if (atoutsJouables.contient(CardTarot.petit())) {
                return atoutsJouables.premiereCarte();
            }
            return atoutsJouables.derniereCarte();
        }
        if (playableCards.contient(CardTarot.EXCUSE)) {
            return CardTarot.EXCUSE;
        }
        couleurs_ = Suit.couleursOrdinaires();
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(playableCards, couleurs_);
        return repartitionJouable.getVal(couleurs_.first()).derniereCarte();
    }

    CardTarot playWithStrongestHand() {
        /*
        Cas ou le joueur entameur
        deborde les autres joueurs en
        couleurs et en atout
        */
        int nbPlayers_ = teamsRelation.getNombreDeJoueurs();
        if (!playableCards.contient(CardTarot.excuse())) {
            return jeuMainMaitresse(playableCards, cartesMaitresses);
        }
        if (teamsRelation.isVirtualTaker(currentPlayer)) {
            //Preneur
            boolean playExc_ = playExc(nbPlayers_, currentPlayer, plisFaits, carteAppeleeJouee);
            if (playExc_) {
                return CardTarot.excuse();
            }
            return jeuMainMaitresse(playableCards, cartesMaitresses);
        }
        if(currentStatus == Role.CALLED_PLAYER) {
            //Appele
            //existeAppele
            if(teamsRelation.aucunPliAdverse(currentPlayer,plisFaits)) {
                return jeuMainMaitresse(playableCards, cartesMaitresses);
            }
            return CardTarot.excuse();
        }
        Ints joueursConfianceNumero_ = new Ints(confidentPlayers);
        joueursConfianceNumero_.add(currentPlayer);
        if(GameTarotTrickInfo.plisTousFaitsPar(joueursConfianceNumero_,plisFaits,nbPlayers_)) {
            return jeuMainMaitresse(playableCards, cartesMaitresses);
        }
        return CardTarot.excuse();
    }

    private boolean playExc(int _nbPlayers, int _next, CustList<TrickTarot> _plisFaits, boolean _carteAppeleeJouee) {
        boolean playExc_ = true;
        if (_carteAppeleeJouee) {
            if(teamsRelation.aucunPliAdverse(_next, _plisFaits)) {
                playExc_ = false;
            }
        } else if(!currentHand.contientCartes(calledCards)) {
            if(plisTousFaitsParPreneurJoueur(teamsRelation.getTaker(), _plisFaits, _nbPlayers)) {
                playExc_ = false;
            }
        } else {
            Ints joueursRamasseurs_ = new Ints();
            joueursRamasseurs_.add(teamsRelation.getTaker());
            if(GameTarotTrickInfo.plisTousFaitsPar(joueursRamasseurs_, _plisFaits, _nbPlayers)) {
                playExc_ = false;
            }
        }
        return playExc_;
    }

    boolean defenseOuvreur(int _joueur, boolean _playedCalledCard) {
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        int nextPlayer_ = common.playerAfter(_joueur);
        if (_playedCalledCard) {
            return notConfidentPlayers.containsObj(nextPlayer_);
        }
        if (GameTarotTeamsRelation.justeApresJoueur(teamsRelation.getTaker(), _joueur, nombreDeJoueurs_)) {
            return true;
        }
        return teamsRelation.getTaker() == (_joueur + 2) % nombreDeJoueurs_;
    }

    boolean defenseOuvreurStrict(int _joueur) {
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        if (notConfidentPlayers.size() == 2) {
            int nextPlayer_ = common.playerAfter(_joueur);
            return notConfidentPlayers.containsObj(nextPlayer_);
        }
        return GameTarotTeamsRelation.justeApresJoueur(teamsRelation.getTaker(), _joueur, nombreDeJoueurs_);
    }
    Ints joueursAvantAppeleApresPreneur(int _appele) {
        Ints joueurs_ = new Ints();
        int player_ = common.playerAfter(teamsRelation.getTaker());
        //called player exists
        while (!NumberUtil.eq(player_, _appele)) {
            joueurs_.add(player_);
            player_ =  common.playerAfter(player_);
        }
        return joueurs_;
    }
    static IdList<Suit> couleursEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Ints _joueurs, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            boolean couleurEntamee_ = false;
            for(TrickTarot pli_: _plisFaits.mid(1)) {
                if (pli_.couleurDemandee() == couleur_ && _joueurs.containsObj(pli_.getEntameur())) {
                    //pli_.getVuParToutJoueur() &&
                    couleurEntamee_ = true;
                    break;
                }
            }
            if (!couleurEntamee_) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static CardTarot jeuMainMaitresse(HandTarot _playableCards, IdMap<Suit, HandTarot> _cartesMaitresses) {
        IdMap<Suit,HandTarot> repartitionJouable_ = _playableCards.couleurs();
        HandTarot atoutsJouable_ = repartitionJouable_.getVal(Suit.TRUMP);
        HandTarot atoutsSansPetitJouable_ = GameTarotCommonPlaying.atoutsSansPetit(atoutsJouable_);
        if(!atoutsSansPetitJouable_.estVide()) {
            return atoutsSansPetitJouable_.premiereCarte();
        }
        IdList<Suit> couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(
                Suit.couleursOrdinaires(), _cartesMaitresses);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playableCards,couleurs_);
            HandTarot cartesCouleurJouable_ = repartitionJouable_.getVal(couleurs_.first());
            cartesCouleurJouable_.trierParForceEnCours(couleurs_.first());
            return cartesCouleurJouable_.premiereCarte();
        }
        if (atoutsJouable_.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        return CardTarot.excuse();
    }

    static IdList<Suit> couleursSansCarteMaitresse(HandTarot _main,
                                                     IdList<Suit> _couleurs, IdMap<Suit, HandTarot> _cartesMaitresses) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for (Suit couleur_ : _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            HandTarot cartesMaitresses_ = _cartesMaitresses.getVal(couleur_);
            if (cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }
    static CardTarot carteCouleurAppeleeSousCarte(HandTarot _playableCards,
                                                  Suit _couleur,
                                                  HandTarot _carteAppelee) {
        IdMap<Suit,HandTarot> couleursMainsJouable_ = _playableCards.couleurs();
        HandTarot cartesCouleurAppelee_ = _carteAppelee.couleur(_couleur);
        cartesCouleurAppelee_.trierParForceEnCours(_couleur);
        HandTarot couleurAppeleePossedeeJouable_ = couleursMainsJouable_.getVal(_couleur);
        for (CardTarot c: couleurAppeleePossedeeJouable_) {
            if (c.strength(_couleur) < cartesCouleurAppelee_.premiereCarte()
                    .strength(_couleur)) {
                return c;
            }
        }
        return couleurAppeleePossedeeJouable_.premiereCarte();
    }
    static IdList<Suit> couleursCoupeeParJoueurs(HandTarot _main,
                                                   Ints _joueurs, IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                                   IdMap<Suit,CustList<HandTarot>> _cartesCertaines, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();

        for (int joueur_ : _joueurs) {
            IdList<Suit> couleursLoc_ = couleursCoupeePar(_main,
                    joueur_, _cartesPossibles,
                    _cartesCertaines, _couleurs);
            for (Suit couleur_ : couleursLoc_) {
                couleurs_.add(couleur_);
            }
        }
        couleurs_.removeDuplicates();
        return couleurs_;
    }
    private boolean jouerAtout(HandTarot _atoutsJoues,
                               IdMap<Suit,CustList<HandTarot>> _cartesPossibles) {
        int nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        return jouerAtout(playableCards, _atoutsJoues,
                _cartesPossibles, nombreDeJoueurs_);
    }
    static boolean jouerAtout(HandTarot _main, HandTarot _atoutsJoues,
                              IdMap<Suit,CustList<HandTarot>> _cartesPossibles, int _nombreJoueurs) {
        int nb_ = _main.couleur(Suit.TRUMP).total();
        if (nb_ == 0) {
            return false;
        }
        CustList<HandTarot> repartitionAtout_ = _cartesPossibles
                .getVal(Suit.TRUMP);
        int nombreDefausses_ = 0;
        for (int i = 0; i < _nombreJoueurs; i++) {
            HandTarot main_ = repartitionAtout_.get(i);
            if (!main_.estVide()) {
                continue;
            }
            nombreDefausses_++;
        }
        HandTarot atoutsNonJoues_ = new HandTarot();
        int nombreJoueursAvecAtout_ = _nombreJoueurs
                - nombreDefausses_ - 1;
        for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
            if (_main.contient(atout_)) {
                continue;
            }
            if (!_atoutsJoues.contient(atout_)) {
                atoutsNonJoues_.ajouter(atout_);
            }
        }
        if (_main.contient(CardTarot.petit())) {
            nb_--;
        }
        return nb_ * nombreJoueursAvecAtout_ >= atoutsNonJoues_
                .total();
    }
    private CardTarot jeuAtoutOffensif() {
        return jeuAtoutOffensif(atoutsJouables, atoutsMaitresJouables, repartitionCartesJouees);
    }

    static CardTarot jeuAtoutOffensif(HandTarot _atoutsJouables, HandTarot _atoutsMaitresJouables, IdMap<Suit, HandTarot> _repartitionCartesJouees) {
        if(!_atoutsMaitresJouables.estVide()) {
            return _atoutsMaitresJouables.premiereCarte();
        }
        CustList<HandTarot> suitesAtouts_ = _atoutsJouables.eclaterEnCours(
                _repartitionCartesJouees, Suit.TRUMP);
        suitesAtouts_.sortElts(new GameSeqLengthTarotComparator());
        return suitesAtouts_.first().premiereCarte();
    }

    IdList<Suit> couleursNonAppelees() {
        if (!carteAppeleeJouee) {
            return couleursNonAppelees(Suit.couleursOrdinaires());
        }
        return Suit.couleursOrdinaires();
    }
    IdList<Suit> couleursNonAppelees(IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for(Suit couleur_: _couleurs) {
            if(couleursAppelees.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static CardTarot jeuAvecCarteMaitresseSansAtout(HandTarot _playableCards, HandTarot _cartesJouees, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playableCards, _couleurs);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_cartesJouees, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(_playableCards, couleurs_);
        return _playableCards.couleur(couleurs_.first()).premiereCarte();
    }

    static IdList<Suit> couleursOuvertes(HandTarot _main,
                                                   CustList<TrickTarot> _plis, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdList<Suit> couleursOuvertes_ = new IdList<Suit>();
        IdMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for (TrickTarot pli_ : _plis.mid(1)) {
//            if (!pli_.getVuParToutJoueur()) {
//                continue;
//            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (couleurDemandee_ != Suit.TRUMP) {
                couleursOuvertes_.add(couleurDemandee_);
            }
        }
        for (Suit couleur_ : _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide() || !couleursOuvertes_.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static IdList<Suit> couleursAvecCartesMaitressesVuesChien(
            HandTarot _main, HandTarot _cartesJouees, HandTarot _chien,
            IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for(Suit couleur_: _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            HandTarot unionLogique_ = new HandTarot();
            unionLogique_.ajouterCartes(_cartesJouees.couleur(couleur_));
            for (CardTarot carte_ : _chien.couleur(couleur_)) {
                if (!unionLogique_.contient(carte_)) {
                    unionLogique_.ajouter(carte_);
                }
            }
            unionLogique_.trierParForceEnCours(couleur_);
            HandTarot cartesMaitressesChien_ = GameTarotCommon.cartesMaitresses(couleursMains_,
                    unionLogique_.couleurs()).getVal(couleur_);
            if (!cartesMaitressesChien_.estVide()) {
                couleurs_.add(couleur_);
            }
        }

        return couleurs_;
    }
    static IdList<Suit> couleursNonCoupeeParJoueurs(HandTarot _main,
                                                      Ints _joueurs, IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                                      IdMap<Suit,CustList<HandTarot>> _cartesCertaines, IdList<Suit> _couleurs) {
        IdList<Suit> couleursCoupees_ = new IdList<Suit>();

        for (int joueur_ : _joueurs) {
            IdList<Suit> couleursLoc_ = couleursCoupeePar(_main,
                    joueur_, _cartesPossibles,
                    _cartesCertaines, _couleurs);
            for (Suit couleur_ : couleursLoc_) {
                couleursCoupees_.add(couleur_);
            }
        }
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if(couleursCoupees_.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static IdList<Suit> couleursNonOuvertesAttaque(HandTarot _main,
                                                             CustList<TrickTarot> _plis, Ints _attaquants,
                                                             IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdList<Suit> couleursOuvertes_ = new IdList<Suit>();
        IdMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for (TrickTarot pli_ : _plis.mid(1)) {
//            if (!pli_.getVuParToutJoueur()) {
//                continue;
//            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (couleurDemandee_ != Suit.TRUMP && _attaquants.containsObj(pli_.getEntameur())) {
                couleursOuvertes_.add(couleurDemandee_);
            }
        }
        for (Suit couleur_ : _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            if (!couleursOuvertes_.containsObj(couleur_)) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    static IdList<Suit> couleursCoupeePar(HandTarot _main,
                                                    int _joueur, IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                                    IdMap<Suit,CustList<HandTarot>> _cartesCertaines, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        if (!_cartesCertaines.getVal(Suit.TRUMP).get(_joueur).estVide()) {
            for (Suit couleur_ : _couleurs) {
                if (!_cartesPossibles.getVal(couleur_).get(_joueur).estVide() || couleursMains_.getVal(couleur_).estVide()) {
                    continue;
                }
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    static IdList<Suit> couleursNonTotalementJoueesEnFigures(
            HandTarot _cartesJouees, IdList<Suit> _couleurs) {
        IdMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (cartesJouees_.getVal(couleur_).nombreDeFigures() == HandTarot.couleurComplete(
                    couleur_).nombreDeFigures()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static boolean plisTousFaitsParPreneurJoueur(int _preneur,
                                                 CustList<TrickTarot> _unionPlis, int _nombreJoueurs) {
        Ints joueurs_ = new Ints();
        joueurs_.add(_preneur);
        Ints autresJoueurs_ = GameTarotTeamsRelation.autresJoueurs(joueurs_, _nombreJoueurs);
        for (int joueur_ : autresJoueurs_) {
            Ints joueursLoc_ = new Ints(joueurs_);
            joueursLoc_.add(joueur_);
            if (GameTarotTrickInfo.plisTousFaitsPar(joueursLoc_, _unionPlis, _nombreJoueurs)) {
                return true;
            }
        }
        return false;
    }

    public Role getCurrentStatus() {
        return currentStatus;
    }
}
