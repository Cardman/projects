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
    private final Bytes confidentPlayers;
    private final Bytes notConfidentPlayers;
    private final Bytes notPlayed;
    private final Bytes confidentPlayersNotPlay;
    private final Bytes notConfidentPlayersNotPlay;
    private final HandTarot playableCards;

    public GameTarotBeginTrickClassic(GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation, HandTarot _calledCards, HandTarot _currentHand) {
        teamsRelation = _teamsRelation;
        calledCards = _calledCards;
        currentHand = _currentHand;
        info = _done;
        common = new GameTarotCommonPlaying(_done,_teamsRelation);
        byte nbPlayers_ = _teamsRelation.getNombreDeJoueurs();
        TrickTarot trTarot_ = _done.getProgressingTrick();
        Bytes played_ = trTarot_.joueursAyantJoue(nbPlayers_);
        notPlayed = GameTarotTeamsRelation.autresJoueurs(played_,nbPlayers_);
        byte nextPlayer_ = trTarot_.getNextPlayer(nbPlayers_);
        playableCards = common.cartesJouables(currentHand.couleurs());
        notPlayed.removeObj(nextPlayer_);
        currentStatus = _teamsRelation.statutDe(nextPlayer_);
        confidentPlayers = _teamsRelation.joueursConfiance(nextPlayer_,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        notConfidentPlayers = _teamsRelation.joueursNonConfiance(nextPlayer_,GameTarotTeamsRelation.tousJoueurs(nbPlayers_));
        confidentPlayersNotPlay = GameTarotTeamsRelation.intersectionJoueurs(confidentPlayers,notPlayed);
        notConfidentPlayersNotPlay = GameTarotTeamsRelation.intersectionJoueurs(notConfidentPlayers,notPlayed);
    }
    CardTarot entameSurExcuseClassique() {
        byte starter_ = info.getProgressingTrick().getEntameur();
        TarotInfoPliEnCours info_ = initInformations();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        if(starter_ == teamsRelation.getTaker()) {
            if(currentStatus == Role.CALLED_PLAYER) {
                return playAsCalledPlayerOnExc(info_);
            }
            //defenseur entamant sur l'excuse du preneur
            if(NumberUtil.equalsSetBytes(confidentPlayersNotPlay,notPlayed)) {
                return playAsDefenderTeam(info_);
            }
            if(common.appeleConnuDefenseur(info_.getCurrentPlayer(),cartesPossibles_)) {
                return playAsDefenderWhenFoundCall(info_);
            }
        }
        return entameClassique();
    }
    CardTarot playAsCalledPlayerOnExc(TarotInfoPliEnCours _info) {
        IdMap<Suit,HandTarot> repartitionJouable_ = playableCards.couleurs();
        HandTarot atouts_ = repartitionJouable_.getVal(Suit.TRUMP);
        HandTarot cartesJouees_ = _info.getCartesJouees();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        HandTarot atoutsSansPetit_ = GameTarotCommonPlaying.atoutsSansPetit(atouts_);
        if(!atoutsSansPetit_.estVide()) {
            return atoutsSansPetit_.premiereCarte();
        }
        EnumList<Suit> couleursAjouer_ = couleursNonAppelees(_info);

        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, couleursAjouer_);
        EnumList<Suit> couleurs_ = couleursOuvertes(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            EnumList<Suit> couleursCoupeesAdv_ = couleursCoupeeParJoueurs(currentHand,
                    notConfidentPlayersNotPlay, cartesPossibles_,
                    cartesCertaines_, couleurs_);
            if(!couleursCoupeesAdv_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursCoupeesAdv_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
            }
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(couleurs_, _info.getCartesMaitresses());
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).premiereCarte();
        }
        return entameClassique();
    }
    CardTarot playAsDefenderTeam(TarotInfoPliEnCours _info) {
        IdMap<Suit,HandTarot> repartitionJouable_ = playableCards.couleurs();
        HandTarot atouts_ = repartitionJouable_.getVal(Suit.TRUMP);
        HandTarot cartesJouees_ = _info.getCartesJouees();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        //jeu atout s'il n'exite aucun partenaire avec un atout
        // ou si le defenseur possede le petit
        if (GameTarotTrickHypothesis.joueursPossedantAtout(confidentPlayersNotPlay, cartesCertaines_).isEmpty() && !atouts_.estVide()) {
            return atouts_.derniereCarte();
        }
        if(atouts_.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, couleursAjouer_);
        EnumList<Suit> couleurs_ = couleursNonVidesAjouer_;
        EnumList<Suit> couleursCoupees_ = couleursCoupeePar(currentHand,
                teamsRelation.getTaker(), cartesPossibles_,
                cartesCertaines_, couleurs_);
        if(!couleursCoupees_.isEmpty()) {
            couleurs_ = couleursNonTotalementJoueesEnFigures(cartesJouees_, couleursCoupees_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                CardTarot carteHaute_ = repartitionJouable_.getVal(couleurs_.first()).premiereCarte();
                if(carteHaute_.isCharacter()) {
                    return carteHaute_;
                }
                return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
            }
        }
        couleurs_ = couleursNonOuvertesAttaque(currentHand,
                plisFaits_, notConfidentPlayers, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
        return repartitionJouable_.getVal(couleurs_.first()).premiereCarte();
    }
    CardTarot playAsDefenderWhenFoundCall(TarotInfoPliEnCours _info) {
        //L'appele existe et est connu du defenseur courant
        IdMap<Suit,HandTarot> repartitionJouable_ = playableCards.couleurs();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, couleursAjouer_);
        EnumList<Suit> couleurs_ = couleursNonVidesAjouer_;
        boolean pasAtoutAppeles_ = true;
        for (byte a: teamsRelation.getCalledPlayers()) {
            if (!cartesPossibles_.getVal(Suit.TRUMP).get(a).estVide()) {
                pasAtoutAppeles_ = false;
                break;
            }
        }
        if (pasAtoutAppeles_) {
            //attention s'il existe une couleur que l'appele ne possede pas
            //attention s'il existe une couleur avec carte rel maitre sur l'appele
            HandTarot cartesPossiblesAppele_ = new HandTarot();
            for (byte a: teamsRelation.getCalledPlayers()) {
                for (Suit couleur_ : Suit.couleursOrdinaires()) {
                    cartesPossiblesAppele_.ajouterCartes(cartesPossibles_.getVal(couleur_).get(a));
                }
            }
            couleurs_ = GameTarotCommon.couleursNonAtoutAyantNbCartesInfEg(cartesPossiblesAppele_, couleurs_, 0);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                return repartitionJouable_.getVal(couleurs_.first()).premiereCarte();
            }
            return entameClassique();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursAvecFigures(currentHand, couleurs_);
        couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(couleurs_, _info.getCartesMaitresses());
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).premiereCarte();
        }
        return entameClassique();
    }
    CardTarot entameClassique() {
        if (playableCards.total() == 1) {
            return playableCards.premiereCarte();
        }
        TarotInfoPliEnCours info_ = initInformations();
        if (info_.getCouleursMaitresses().size() == Suit.couleursOrdinaires().size() && info_.isMaitreAtout()) {
            return playWithStrongestHand(info_);
        }
        if(GameTarotCommonPlaying.maitreAtout(info_) && currentStatus == Role.TAKER) {
            CardTarot card_ = playWithStrongestTrumps(info_);
            if (card_ != CardTarot.WHITE) {
                return card_;
            }
        }
        CardTarot cardTr_ = playWithAtMostOneSuitCard(info_);
        if (cardTr_ != CardTarot.WHITE) {
            return cardTr_;
        }
        IdMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        boolean atoutsTousJoues_ = !common.premierTour() && atouts_.total() + info_.getRepartitionCartesJouees().getVal(Suit.TRUMP).total() == HandTarot.atoutsSansExcuse().total();
        if(atoutsTousJoues_) {
            CardTarot card_ = playAfterAllTrumps(info_);
            if (card_ != CardTarot.WHITE) {
                return card_;
            }
        }
        CardTarot card_ = tryDemandTrumpSuit(info_);
        if (card_ != CardTarot.WHITE) {
            return card_;
        }
        //differents cas de jeu
        if(teamsRelation.isVirtualTaker(info_.getCurrentPlayer())) {
            return playAsTaker(info_);
        }
        if(currentStatus == Role.CALLED_PLAYER) {
            return playAsCalledPlayer(info_);
        }
        return playAsDefender(info_);
        //couleur appelee

    }

    TarotInfoPliEnCours initInformations() {
        return common.initInformations(currentHand,playableCards, confidentPlayers,notConfidentPlayers);
    }
    CardTarot playWithAtMostOneSuitCard(TarotInfoPliEnCours _info) {
        IdMap<Suit,HandTarot> repartitionJouable_ = playableCards.couleurs();
        HandTarot atouts_ = repartitionJouable_.getVal(Suit.TRUMP);
        if(playableCards.total() == atouts_ .total() + repartitionJouable_.getVal(CardTarot.excuse().getId().getCouleur()).total()) {
            //il n'y a que de l'atout (ev excuse)
            CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
            boolean contientExcuse_ = _info.isContientExcuse();
            boolean carteAppeleeJouee_ = _info.isCarteAppeleeJouee();
            HandTarot cartesJouees_ = _info.getCartesJouees();
            if (!contientExcuse_) {
                return jeuAtoutOffensif(playableCards, cartesJouees_);
            }
            if(atouts_.total() + repartitionJouable_.getVal(CardTarot.excuse().getId().getCouleur()).total() == 2) {
                if(!playExc(teamsRelation.getNombreDeJoueurs(),_info.getCurrentPlayer(),plisFaits_,carteAppeleeJouee_)) {
                    return atouts_.premiereCarte();
                }
                return CardTarot.excuse();
            }
            return jeuAtoutOffensif(playableCards, cartesJouees_);
        }
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
        if (couleurs_.size() == 1) {
            HandTarot uniqueCouleur_ = repartitionJouable_.getVal(couleurs_.first());
            if (uniqueCouleur_.total() == 1 && !atouts_.estVide()) {
                return tryPlay(_info, repartitionJouable_, atouts_, couleurs_, uniqueCouleur_.premiereCarte());
            }
        }
        return CardTarot.WHITE;
    }

    private CardTarot tryPlay(TarotInfoPliEnCours _info, IdMap<Suit, HandTarot> _repartitionJouable,
                              HandTarot _atouts, EnumList<Suit> _couleurs, CardTarot _cartCouleur) {
        //une seule carte de couleur est presente
        HandTarot cartesJouees_ = _info.getCartesJouees();
        IdMap<Suit, CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit, CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        Bytes partenaires_ = GameTarotTrickHypothesis.joueursPouvantCouperCouleurs(currentHand, confidentPlayersNotPlay,
                cartesPossibles_, _couleurs);
        partenaires_ = GameTarotTrickHypothesis.joueursPossedantAtoutMaitre(partenaires_, cartesCertaines_,
                cartesJouees_);
        if (!partenaires_.isEmpty()) {
            return _cartCouleur;
        }
        return tryPlayWithoutPartner(_info,_repartitionJouable, _atouts, _cartCouleur);
    }

    private CardTarot tryPlayWithoutPartner(TarotInfoPliEnCours _info, IdMap<Suit, HandTarot> _repartitionJouable, HandTarot _atouts, CardTarot _cartCouleur) {
        IdMap<Suit, HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        HandTarot atoutsMaitres_ = _repartitionJouable.getVal(Suit.TRUMP)
                .atoutsMaitres(repartitionCartesJouees_);
        IdMap<Suit, CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumList<Suit> couleursMaitres_ = _info.getCouleursMaitresses();
        if (couleursMaitres_.size() == Suit.couleursOrdinaires().size()) {
            if (_atouts.total() == 1 && _atouts.contient(CardTarot.petit())) {
                return _cartCouleur;
            }
            int nbAtoutsMaitres_ = atoutsMaitres_.total();
            if (nbAtoutsMaitres_ > 0) {
                atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
                return atoutsMaitres_.premiereCarte();
            }
            if (!_cartCouleur.isCharacter()) {
                return _cartCouleur;
            }
            _atouts.trierParForceEnCours(Suit.TRUMP);
            if (_atouts.contient(CardTarot.petit())) {
                return _atouts.premiereCarte();
            }
            return _atouts.derniereCarte();
        }
        int nbAtoutsMaitres_ = atoutsMaitres_.total();
        if (nbAtoutsMaitres_ > 0) {
            if (currentHand.total() == 2 && _atouts.contient(CardTarot.petit())) {
                return _cartCouleur;
            }
            atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
            return atoutsMaitres_.premiereCarte();
        }
        if (GameTarotTrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles_, _cartCouleur.getId().getCouleur(), notConfidentPlayersNotPlay).isEmpty()) {
            return _cartCouleur;
        }
        return CardTarot.WHITE;
    }

    CardTarot playWithStrongestTrumps(TarotInfoPliEnCours _info) {
        IdMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        IdMap<Suit,HandTarot> repartitionJouable_ = playableCards.couleurs();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        IdMap<Suit,CustList<HandTarot>> suites_ = _info.getSuitesTouteCouleur();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        EnumList<Suit> couleursStrictementMaitresses_ = GameTarotCommonPlaying.strictCouleursMaitres(
                suites_, repartitionCartesJouees_, cartesPossibles_, _info.getCurrentPlayer());
        HandTarot atoutsMaitres_ = repartitionJouable_.getVal(Suit.TRUMP)
                .atoutsMaitres(repartitionCartesJouees_);
        EnumList<Suit> couleursNonAppelees_ = couleursNonAppelees(Suit.couleursOrdinaires());
        IdMap<Suit, HandTarot> cards_ = GameTarotBid.cartesPseudoMaitresses(repartition_, calledCards,
                _info.getRepartitionCartesJouees());
        EnumList<Suit> couleursPseudosMaitres_ = GameTarotBid.couleursPseudosMaitres(
                repartition_,
                cards_);
        EnumList<Suit> couleursMaitres_ = _info.getCouleursMaitresses();
        int nbAtoutsMaitres_ = atoutsMaitres_.total();
        if(atoutsMaitres_.contient(CardTarot.petit())) {
            nbAtoutsMaitres_--;
        }
        EnumList<Suit> couleursAppeleesNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards,couleursAppelees_);
        boolean leadTr_ = _info.isMaitreAtout();
        boolean leadTrNotFirstRd_ = leadTr_;
        if (common.premierTour()) {
            leadTrNotFirstRd_ = false;
        }
        if (couleursAppeleesNonVides_.isEmpty() || !couleursPseudosMaitres_.containsAllObj(couleursAppelees_)) {
            return CardTarot.WHITE;
        }
        if(leadTr_&&nbAtoutsMaitres_ > 1) {
            atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
            return atoutsMaitres_.premiereCarte();
        }
        if (leadTrNotFirstRd_) {
            couleursAppeleesNonVides_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursAppeleesNonVides_);
            couleursAppeleesNonVides_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleursAppeleesNonVides_);
            if(cards_.getVal(couleursAppeleesNonVides_.first()).total() > 1) {
                return carteCouleurAppeleeSousCarte(playableCards, couleursAppeleesNonVides_.first(), calledCards);
            }
        }
        if (leadTr_) {
            EnumList<Suit> couleursStrictementMaitressesNonAppelees_ = couleursNonAppelees(couleursStrictementMaitresses_);
            EnumList<Suit> suitsNoCall_ = couleursNonAppelees(couleursMaitres_);
            suitsNoCall_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand,suitsNoCall_);
            if(!suitsNoCall_.isEmpty() && couleursStrictementMaitressesNonAppelees_.containsAllObj(couleursNonAppelees_)) {
                couleursMaitres_ =GameTarotCommon.couleursLesPlusLongues(currentHand, suitsNoCall_);
                HandTarot couleurCandidate_ = repartitionJouable_.getVal(couleursMaitres_.first());
                couleurCandidate_.trierParForceEnCours(couleursMaitres_.first());
                return couleurCandidate_.premiereCarte();
            }
        }
        return CardTarot.WHITE;
    }
    CardTarot playAfterAllTrumps(TarotInfoPliEnCours _info) {
        HandTarot cartesJouees_ = _info.getCartesJouees();
        EnumList<Suit> notEmptySuits_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards, Suit.couleursOrdinaires());
        notEmptySuits_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(notEmptySuits_, _info.getCartesMaitresses());
        if(!notEmptySuits_.isEmpty()) {
            return jeuAvecCarteMaitresseSansAtout(playableCards, cartesJouees_, notEmptySuits_);
        }
        return CardTarot.WHITE;
    }
    CardTarot tryDemandTrumpSuit(TarotInfoPliEnCours _info) {
        IdMap<Suit,HandTarot> repartition_ = playableCards.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        if(!GameTarotTrickHypothesis.joueursPouvantPossederPetit(confidentPlayersNotPlay,
                cartesPossibles_).isEmpty()) {
            IdMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
            HandTarot atoutsMaitres_ = repartition_.getVal(Suit.TRUMP)
                    .atoutsMaitres(repartitionCartesJouees_);
            int nbAtoutsMaitres_ = atoutsMaitres_.total();
            if(nbAtoutsMaitres_ > 0) {
                atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
                return atoutsMaitres_.premiereCarte();
            }
        }
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        if (!GameTarotTrickHypothesis.joueursPossedantAtoutMaitre(confidentPlayersNotPlay,
                cartesCertaines_, cartesJouees_).isEmpty() && atouts_.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        HandTarot atoutsJoues_ = cartesJouees_.couleur(Suit.TRUMP);
        byte nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        Rate moyenneAtout_ = GameTarotCommonPlaying.moyenneAtout(currentHand, atoutsJoues_,
                cartesPossibles_, nombreDeJoueurs_);
        if (!GameTarotTrickHypothesis.joueursPossedantNbAtout(confidentPlayersNotPlay,
                cartesCertaines_, moyenneAtout_).isEmpty() && atouts_.total() > Math.max(moyenneAtout_.ll(), 1)) {
            return jeuAtoutOffensif(playableCards, cartesJouees_);
        }
        return CardTarot.WHITE;
    }
    CardTarot playAsDefender(TarotInfoPliEnCours _info) {
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        byte nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        HandTarot cartesJouables_ = _info.getCartesJouables();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        byte next_ = _info.getProgressingTrick().getNextPlayer(nombreDeJoueurs_);
        boolean carteAppeleeJouee_ = _info.isCarteAppeleeJouee();
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        IdMap<Suit,HandTarot> repartitionJouable_ = playableCards.couleurs();
        EnumList<Suit> couleursAjouer_ = couleursNonAppelees(_info);
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(cartesJouables_, couleursAjouer_);
        EnumList<Suit> couleursNonVides_ = couleursNonVides(cartesJouables_, couleursNonVidesAjouer_);
        //Defenseur
        if (!carteAppeleeJouee_ && !common.premierTour()) {
            for(Suit c: couleursAppelees_) {
                if (!cartesJouables_.couleur(c).estVide() && GameTarotCommon.couleursAvecCartesBasses(cartesJouables_, Suit.couleursOrdinaires()).containsObj(c)) {
                    return repartitionJouable_.getVal(c).derniereCarte();
                }
            }
        }

        //ouvreur
        if(defenseOuvreur(next_,carteAppeleeJouee_)) {
            return playAsDefenderStartNew(_info, next_, couleursNonVidesAjouer_, couleursNonVides_);
            //couleur appelee
        }
        EnumList<Suit> couleurs_ = couleursOuvertes(cartesJouables_,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(cartesJouables_, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommonPlaying.couleursLesMoinsEntameesParJoueurs(plisFaits_, notConfidentPlayersNotPlay, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(cartesJouables_, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(cartesJouables_,
                plisFaits_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommonPlaying.couleursLesMoinsEntameesParJoueurs(plisFaits_, notConfidentPlayersNotPlay, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(cartesJouables_, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        return playDefaultCardAsDefender(cartesJouables_, cartesJouees_, repartitionJouable_, couleursNonVides_);
    }

    private CardTarot playAsDefenderStartNew(TarotInfoPliEnCours _info, byte _next, EnumList<Suit> _couleursNonVidesAjouer, EnumList<Suit> _couleursNonVides) {
        IdMap<Suit,HandTarot> repartitionJouable_ = playableCards.couleurs();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        HandTarot cartesJouables_ = _info.getCartesJouables();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        if(defenseOuvreurStrict(_next)) {
            EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(cartesJouables_,
                    plisFaits_, _couleursNonVidesAjouer);
            couleurs_ = GameTarotCommon.couleursSansFigures(cartesJouables_, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(cartesJouables_, couleurs_);
                return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
            }
        }
        EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(cartesJouables_,
                plisFaits_, _couleursNonVidesAjouer);
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(cartesJouables_, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(cartesJouables_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(cartesJouables_,
                plisFaits_, _couleursNonVidesAjouer);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(cartesJouables_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        return playDefaultCardAsDefender(cartesJouables_, cartesJouees_, repartitionJouable_, _couleursNonVides);
    }

    private EnumList<Suit> couleursNonVides(HandTarot _cartesJouables, EnumList<Suit> _couleursNonVidesAjouer) {
        EnumList<Suit> couleursNonVides_ = _couleursNonVidesAjouer;
        if (couleursNonVides_.isEmpty()) {
            couleursNonVides_ = GameTarotCommon.couleursNonAtoutNonVides(_cartesJouables,Suit.couleursOrdinaires());
        }
        return couleursNonVides_;
    }

    private static CardTarot playDefaultCardAsDefender(HandTarot _cartesJouables, HandTarot _cartesJouees, IdMap<Suit, HandTarot> _repartitionJouable, EnumList<Suit> _couleursNonVidesAjouer) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusBasses(_cartesJouables, _couleursNonVidesAjouer);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouees, couleurs_);
        return _repartitionJouable.getVal(couleurs_.first()).derniereCarte();
    }

    static boolean allSuitOwnLeadingCard(IdMap<Suit, HandTarot> _repartition, IdMap<Suit, HandTarot> _cartesMaitresses, EnumList<Suit> _suits) {
        boolean touteCouleurPossedeCarteMaitresse_ = true;
        for (Suit couleur_ : _suits) {
            if (!_repartition.getVal(couleur_).estVide() && _cartesMaitresses.getVal(couleur_).estVide()) {
                touteCouleurPossedeCarteMaitresse_ = false;
                break;
            }
        }
        return touteCouleurPossedeCarteMaitresse_;
    }

    static boolean noTrumping(IdMap<Suit, HandTarot> _repartition, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Bytes _joueursNonJoue) {
        boolean aucuneCoupe_ = true;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            boolean plusCartesCouleurAutres_ = true;
            for(byte joueur_: _joueursNonJoue) {
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
    CardTarot playAsCalledPlayer(TarotInfoPliEnCours _info) {
        byte nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        HandTarot playableCards_ = _info.getCartesJouables();
        byte next_ = _info.getProgressingTrick().getNextPlayer(nombreDeJoueurs_);
        IdMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        IdMap<Suit,HandTarot> repartitionJouable_ = playableCards.couleurs();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        HandTarot atoutsJoues_ = cartesJouees_.couleur(Suit.TRUMP);
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        IdMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        boolean touteCouleurPossedeCarteMaitresse_ = allSuitOwnLeadingCard(repartition_, cartesMaitresses_, Suit.couleursOrdinaires());
        byte taker_ = teamsRelation.getTaker();
        boolean takerNoPlay_ = notPlayed.containsObj(taker_);
        EnumList<Suit> couleursAjouer_ = couleursNonAppelees(_info);
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards_, couleursAjouer_);
        EnumList<Suit> couleursNonVides_ = couleursNonVides(playableCards_, couleursNonVidesAjouer_);
        CardTarot calledCardPoss_ = playAsCalledPlayerCalledCard(_info);
        if (calledCardPoss_ != CardTarot.WHITE) {
            return calledCardPoss_;
        }
        boolean afterTaker_ = GameTarotTeamsRelation.justeApresJoueur(next_, taker_, nombreDeJoueurs_);
        boolean takerLast_ = afterTaker_ && takerNoPlay_;
        boolean saveLeading_ = jouerAtout(playableCards, atoutsJoues_,
                cartesPossibles_, nombreDeJoueurs_) && touteCouleurPossedeCarteMaitresse_;
        if(takerLast_) {
            return playAsCalledPlayerTakerLast(_info, couleursNonVidesAjouer_, couleursNonVides_, saveLeading_);
        }
        EnumList<Suit> couleurs_;
        Bytes defenseurs_ = joueursAvantAppeleApresPreneur(next_);
        boolean defausseTousDefenseursIntermediaire_ = defausseTousJoueursNonConfiance(cartesPossibles_, defenseurs_);
        if(defausseTousDefenseursIntermediaire_
                && cartesPossibles_.getVal(Suit.TRUMP).get(taker_).contient(CardTarot.petit())
                && takerNoPlay_) {
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleursNonVides_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        Bytes partenaires_ = new Bytes();
        partenaires_.add(taker_);
        couleurs_ = couleursEntameesParJoueurs(plisFaits_, partenaires_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursEntameesParJoueurs(plisFaits_, partenaires_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        if(saveLeading_) {
            return jeuAtoutOffensif(playableCards, cartesJouees_);
        }
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = couleursSansCarteMaitresse(currentHand, couleurs_, _info.getCartesMaitresses());
        couleurs_ = couleursCoupeePar(currentHand,
                taker_, cartesPossibles_,
                cartesCertaines_, couleurs_);
        if(!couleurs_.isEmpty() && takerNoPlay_) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        return defCalledPlayer(_info, couleursNonVidesAjouer_);
    }

    private CardTarot defCalledPlayer(TarotInfoPliEnCours _info, EnumList<Suit> _couleursNonVidesAjouer) {
        HandTarot cartesChien_ = common.cartesVuesAuChien();
        IdMap<Suit,HandTarot> repartitionJouable_ = playableCards.couleurs();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        EnumList<Suit> couleurs_;
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits_, _couleursNonVidesAjouer);
        if(!couleurs_.isEmpty()) {
            boolean defausseTousJoueurs_ = defausseTousJoueursNonConfiance(cartesPossibles_, notConfidentPlayersNotPlay);
            if(defausseTousJoueurs_) {
                EnumList<Suit> couleursMaitres_ = couleursAvecCartesMaitressesVuesChien(currentHand, cartesJouees_, cartesChien_,couleurs_);
                if (!couleursMaitres_.isEmpty()) {
                    couleurs_ = couleursMaitres_;
                }
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                return repartitionJouable_.getVal(couleurs_.first()).premiereCarte();
            }
            EnumList<Suit> couleursCoupeesAdv_ = couleursCoupeeParJoueurs(currentHand,
                    notConfidentPlayersNotPlay, cartesPossibles_,
                    cartesCertaines_, couleurs_);
            if(!couleursCoupeesAdv_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursCoupeesAdv_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursAvecCartesMaitressesVuesChien(currentHand, cartesJouees_,
                cartesChien_, _couleursNonVidesAjouer);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
        return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
    }

    private CardTarot playAsCalledPlayerCalledCard(TarotInfoPliEnCours _info) {
        HandTarot playableCards_ = _info.getCartesJouables();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        byte taker_ = teamsRelation.getTaker();
        boolean takerNoPlay_ = notPlayed.containsObj(taker_);
        if (!takerNoPlay_) {
            return CardTarot.WHITE;
        }
        boolean jouerUneCouleurAppelee_ = false;
        HandTarot cartesPossedees_ = new HandTarot();
        for(CardTarot c: calledCards) {
            if(playableCards_.contient(c)) {
                cartesPossedees_.ajouter(c);
            }
        }
        if(!cartesPossedees_.estVide()) {
            boolean defausseTousJoueurs_ = defausseTousJoueursNonConfiance(cartesPossibles_, notConfidentPlayersNotPlay);
            if(defausseTousJoueurs_) {
                jouerUneCouleurAppelee_ = true;
            }
            CustList<HandTarot> atoutsJoueursSur_ = cartesCertaines_.getVal(Suit.TRUMP);
            if (atoutsJoueursSur_.get(taker_).contient(CardTarot.TRUMP_1) && currentHand.contient(CardTarot.TRUMP_21)) {
                jouerUneCouleurAppelee_ = true;
            }
        }
        if(jouerUneCouleurAppelee_) {
            return cartesPossedees_.premiereCarte();
        }
        return CardTarot.WHITE;
    }

    private CardTarot playAsCalledPlayerTakerLast(TarotInfoPliEnCours _info, EnumList<Suit> _couleursNonVidesAjouer, EnumList<Suit> _couleursNonVides, boolean _saveLeading) {
        byte taker_ = teamsRelation.getTaker();
        IdMap<Suit,HandTarot> repartitionJouable_ = playableCards.couleurs();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        EnumList<Suit> couleurs_;
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits_, _couleursNonVidesAjouer);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits_, _couleursNonVidesAjouer);
        couleurs_ = couleursSansCarteMaitresse(currentHand, couleurs_, _info.getCartesMaitresses());
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits_, _couleursNonVidesAjouer);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursCoupeePar(currentHand,
                taker_, cartesPossibles_,
                cartesCertaines_, _couleursNonVidesAjouer);
        if (!couleurs_.isEmpty() && cartesPossibles_.getVal(Suit.TRUMP).get(taker_).contient(CardTarot.petit())) {
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
        }
        if(_saveLeading) {
            return jeuAtoutOffensif(playableCards, cartesJouees_);
        }
        Bytes partenaires_ = new Bytes();
        partenaires_.add(taker_);
        couleurs_ = GameTarotCommonPlaying.couleursLesPlusEntameesParJoueurs(plisFaits_, partenaires_, _couleursNonVides);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
        return repartitionJouable_.getVal(couleurs_.first()).derniereCarte();
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
    CardTarot playAsTaker(TarotInfoPliEnCours _info) {
        byte nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        HandTarot playableCards_ = _info.getCartesJouables();
        HandTarot playedCards_ = _info.getCartesJouees();
        HandTarot atoutsJoues_ = playedCards_.couleur(Suit.TRUMP);
        IdMap<Suit,HandTarot> repartition_ = playableCards.couleurs();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        boolean plTr_ = jouerAtout(playableCards, atoutsJoues_,
                cartesPossibles_, nombreDeJoueurs_);
        CardTarot calledCardToPlay_ = possiblePlayCalledCards(_info);
        if (calledCardToPlay_ != CardTarot.WHITE) {
            return calledCardToPlay_;
        }
        EnumList<Suit> couleursAjouer_ = couleursNonAppelees(_info);
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards_, couleursAjouer_);
        EnumList<Suit> couleurs_ = couleursOuvertes(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            EnumList<Suit> couleursPossiblementCoupees_ = GameTarotTrickHypothesis.couleursPouvantEtreCoupees(
                    notPlayed,
                    cartesPossibles_, couleurs_);
            if(!couleursPossiblementCoupees_.isEmpty()) {
                couleursPossiblementCoupees_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursPossiblementCoupees_);
                couleursPossiblementCoupees_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleursPossiblementCoupees_);
                return repartition_.getVal(couleursPossiblementCoupees_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = couleursSansCarteMaitresse(currentHand, couleurs_, _info.getCartesMaitresses());
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursSansCarteMaitresse(currentHand, couleursNonVidesAjouer_, _info.getCartesMaitresses());
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        if(plTr_) {
            return jeuAtoutOffensif(playableCards, playedCards_);
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(couleurs_, _info.getCartesMaitresses());
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(currentHand,
                couleursNonVidesAjouer_);
        couleurs_ = couleursCoupeeParJoueurs(currentHand,
                notConfidentPlayersNotPlay, cartesPossibles_,
                cartesCertaines_, couleurs_);
        if(!couleurs_.isEmpty()) {
            EnumList<Suit> couleursNonCoupeesJoueursConfiance_ = couleursNonCoupeeParJoueurs(currentHand,
                    confidentPlayersNotPlay, cartesPossibles_,
                    cartesCertaines_, couleurs_);
            if(!couleursNonCoupeesJoueursConfiance_.isEmpty()) {
                couleursNonCoupeesJoueursConfiance_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursNonCoupeesJoueursConfiance_);
                couleursNonCoupeesJoueursConfiance_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleursNonCoupeesJoueursConfiance_);
                couleursNonCoupeesJoueursConfiance_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleursNonCoupeesJoueursConfiance_);
                return repartition_.getVal(couleursNonCoupeesJoueursConfiance_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursNonVidesAjouer_;
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).premiereCarte();
        }
        return playDefaultCard(repartition_);
    }
    private CardTarot possiblePlayCalledCards(TarotInfoPliEnCours _info) {
        HandTarot playableCards_ = _info.getCartesJouables();
        IdMap<Suit,HandTarot> repartition_ = playableCards.couleurs();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        boolean carteAppeleeJouee_ = _info.isCarteAppeleeJouee();
        IdMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        boolean defausseTousJoueurs_ = defausseTousJoueursNonConfiance(cartesPossibles_, notConfidentPlayersNotPlay);
        EnumList<Suit> couleursAjouer_ = couleursNonAppelees(_info);
        if (!carteAppeleeJouee_) {
            boolean touteCouleurPossedeCarteMaitresse_ = allSuitOwnLeadingCard(repartition_, cartesMaitresses_,couleursAjouer_);
            for(Suit c: couleursAppelees_) {
                if (!playableCards_.couleur(c).estVide() && defausseTousJoueurs_ && touteCouleurPossedeCarteMaitresse_) {
                    //jouer la couleur appelee dans certains cas
                    return repartition_.getVal(c).derniereCarte();
                }
            }
        }
        return CardTarot.WHITE;
    }

    private boolean defausseTousJoueursNonConfiance(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Bytes _foeTeam) {
        boolean defausseTousJoueurs_ = true;
        CustList<HandTarot> atoutsJoueurs_ = _cartesPossibles.getVal(Suit.TRUMP);
        for(byte joueur_: _foeTeam) {
            if (!atoutsJoueurs_.get(joueur_).estVide()) {
                defausseTousJoueurs_ = false;
                break;
            }
        }
        return defausseTousJoueurs_;
    }

    CardTarot playDefaultCard(IdMap<Suit, HandTarot> _repartition) {
        EnumList<Suit> couleurs_;
        HandTarot tr_ = _repartition.getVal(Suit.TRUMP);
        if (!tr_.estVide()) {
            tr_.trierParForceEnCours(Suit.TRUMP);
            if (tr_.contient(CardTarot.petit())) {
                return tr_.premiereCarte();
            }
            return tr_.derniereCarte();
        }
        if (playableCards.contient(CardTarot.EXCUSE)) {
            return CardTarot.EXCUSE;
        }
        couleurs_ = Suit.couleursOrdinaires();
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(playableCards, couleurs_);
        return _repartition.getVal(couleurs_.first()).derniereCarte();
    }

    CardTarot playWithStrongestHand(TarotInfoPliEnCours _info) {
        /*
        Cas ou le joueur entameur
        deborde les autres joueurs en
        couleurs et en atout
        */
        byte nbPlayers_ = teamsRelation.getNombreDeJoueurs();
        byte next_ = _info.getProgressingTrick().getNextPlayer(nbPlayers_);
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        boolean contientExcuse_ = _info.isContientExcuse();
        boolean carteAppeleeJouee_ = _info.isCarteAppeleeJouee();
        if (!contientExcuse_) {
            return jeuMainMaitresse(playableCards, _info.getCartesMaitresses());
        }
        if (teamsRelation.isVirtualTaker(next_)) {
            //Preneur
            boolean playExc_ = playExc(nbPlayers_, next_, plisFaits_, carteAppeleeJouee_);
            if (playExc_) {
                return CardTarot.excuse();
            }
            return jeuMainMaitresse(playableCards, _info.getCartesMaitresses());
        }
        if(currentStatus == Role.CALLED_PLAYER) {
            //Appele
            //existeAppele
            if(teamsRelation.aucunPliAdverse(next_,plisFaits_)) {
                return jeuMainMaitresse(playableCards, _info.getCartesMaitresses());
            }
            return CardTarot.excuse();
        }
        Bytes joueursConfianceNumero_ = new Bytes(confidentPlayers);
        joueursConfianceNumero_.add(next_);
        if(GameTarotTrickInfo.plisTousFaitsPar(joueursConfianceNumero_,plisFaits_,nbPlayers_)) {
            return jeuMainMaitresse(playableCards, _info.getCartesMaitresses());
        }
        return CardTarot.excuse();
    }

    private boolean playExc(byte _nbPlayers, byte _next, CustList<TrickTarot> _plisFaits, boolean _carteAppeleeJouee) {
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
            Bytes joueursRamasseurs_ = new Bytes();
            joueursRamasseurs_.add(teamsRelation.getTaker());
            if(GameTarotTrickInfo.plisTousFaitsPar(joueursRamasseurs_, _plisFaits, _nbPlayers)) {
                playExc_ = false;
            }
        }
        return playExc_;
    }

    boolean defenseOuvreur(byte _joueur, boolean _playedCalledCard) {
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        byte nextPlayer_ = common.playerAfter(_joueur);
        if (_playedCalledCard) {
            return notConfidentPlayers.containsObj(nextPlayer_);
        }
        if (GameTarotTeamsRelation.justeApresJoueur(teamsRelation.getTaker(), _joueur, nombreDeJoueurs_)) {
            return true;
        }
        return teamsRelation.getTaker() == (_joueur + 2) % nombreDeJoueurs_;
    }

    boolean defenseOuvreurStrict(byte _joueur) {
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        if (notConfidentPlayers.size() == 2) {
            byte nextPlayer_ = common.playerAfter(_joueur);
            return notConfidentPlayers.containsObj(nextPlayer_);
        }
        return GameTarotTeamsRelation.justeApresJoueur(teamsRelation.getTaker(), _joueur, nombreDeJoueurs_);
    }
    Bytes joueursAvantAppeleApresPreneur(byte _appele) {
        Bytes joueurs_ = new Bytes();
        byte player_ = common.playerAfter(teamsRelation.getTaker());
        //called player exists
        while (!NumberUtil.eq(player_, _appele)) {
            joueurs_.add(player_);
            player_ =  common.playerAfter(player_);
        }
        return joueurs_;
    }
    static EnumList<Suit> couleursEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Bytes _joueurs, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            boolean couleurEntamee_ = false;
            for(TrickTarot pli_: _plisFaits) {
                if (pli_.getVuParToutJoueur() && pli_.couleurDemandee() == couleur_ && _joueurs.containsObj(pli_.getEntameur())) {
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
    static CardTarot jeuMainMaitresse(HandTarot _currentHand, IdMap<Suit, HandTarot> _cartesMaitresses) {
        IdMap<Suit,HandTarot> repartition_ = _currentHand.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        HandTarot atoutsSansPetit_ = GameTarotCommonPlaying.atoutsSansPetit(atouts_);
        if(!atoutsSansPetit_.estVide()) {
            return atoutsSansPetit_.premiereCarte();
        }
        EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(
                Suit.couleursOrdinaires(), _cartesMaitresses);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_currentHand,couleurs_);
            HandTarot cartesCouleur_ = repartition_.getVal(couleurs_.first());
            cartesCouleur_.trierParForceEnCours(couleurs_.first());
            return cartesCouleur_.premiereCarte();
        }
        if (atouts_.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        return CardTarot.excuse();
    }

    static EnumList<Suit> couleursSansCarteMaitresse(HandTarot _main,
                                                     EnumList<Suit> _couleurs, IdMap<Suit, HandTarot> _cartesMaitresses) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
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
    static CardTarot carteCouleurAppeleeSousCarte(HandTarot _main,
                                                  Suit _couleur,
                                                  HandTarot _carteAppelee) {
        IdMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        HandTarot cartesCouleurAppelee_ = _carteAppelee.couleur(_couleur);
        cartesCouleurAppelee_.trierParForceEnCours(_couleur);
        HandTarot couleurAppeleePossedee_ = couleursMains_.getVal(_couleur);
        for (CardTarot c: couleurAppeleePossedee_) {
            if (c.strength(_couleur) < cartesCouleurAppelee_.premiereCarte()
                    .strength(_couleur)) {
                return c;
            }
        }
        return couleurAppeleePossedee_.premiereCarte();
    }
    static EnumList<Suit> couleursCoupeeParJoueurs(HandTarot _main,
                                                   Bytes _joueurs, IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                                   IdMap<Suit,CustList<HandTarot>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            EnumList<Suit> couleursLoc_ = couleursCoupeePar(_main,
                    joueur_, _cartesPossibles,
                    _cartesCertaines, _couleurs);
            for (Suit couleur_ : couleursLoc_) {
                couleurs_.add(couleur_);
            }
        }
        couleurs_.removeDuplicates();
        return couleurs_;
    }
    static boolean jouerAtout(HandTarot _main, HandTarot _atoutsJoues,
                              IdMap<Suit,CustList<HandTarot>> _cartesPossibles, byte _nombreJoueurs) {
        int nb_ = _main.couleur(Suit.TRUMP).total();
        if (nb_ == 0) {
            return false;
        }
        CustList<HandTarot> repartitionAtout_ = _cartesPossibles
                .getVal(Suit.TRUMP);
        byte nombreDefausses_ = 0;
        for (int i = 0; i < _nombreJoueurs; i++) {
            HandTarot main_ = repartitionAtout_.get(i);
            if (!main_.estVide()) {
                continue;
            }
            nombreDefausses_++;
        }
        HandTarot atoutsNonJoues_ = new HandTarot();
        byte nombreJoueursAvecAtout_ = (byte) (_nombreJoueurs
                - nombreDefausses_ - 1);
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
    static CardTarot jeuAtoutOffensif(HandTarot _currentHand,HandTarot _cartesJouees) {
        IdMap<Suit,HandTarot> repartition_ = _currentHand.couleurs();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = _cartesJouees.couleurs();
        HandTarot atoutsMaitres_ = repartition_.getVal(Suit.TRUMP)
                .atoutsMaitres(repartitionCartesJouees_);
        if(!atoutsMaitres_.estVide()) {
            return atoutsMaitres_.premiereCarte();
        }
        CustList<HandTarot> suitesAtouts_ = repartition_.getVal(Suit.TRUMP).eclaterEnCours(
                repartitionCartesJouees_, Suit.TRUMP);
        suitesAtouts_.sortElts(new GameSeqLengthTarotComparator());
        return suitesAtouts_.first().premiereCarte();
    }

    EnumList<Suit> couleursNonAppelees(TarotInfoPliEnCours _info) {
        boolean carteAppeleeJouee_ = _info.isCarteAppeleeJouee();
        if (!carteAppeleeJouee_) {
            return couleursNonAppelees(Suit.couleursOrdinaires());
        }
        return Suit.couleursOrdinaires();
    }
    EnumList<Suit> couleursNonAppelees(EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        for(Suit couleur_: _couleurs) {
            if(couleursAppelees_.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static CardTarot jeuAvecCarteMaitresseSansAtout(HandTarot _main, HandTarot _cartesJouees, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusLongues(_main, _couleurs);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_cartesJouees, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusHautes(_main, couleurs_);
        return _main.couleur(couleurs_.first()).premiereCarte();
    }

    static EnumList<Suit> couleursOuvertes(HandTarot _main,
                                                   CustList<TrickTarot> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        IdMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for (TrickTarot pli_ : _plis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
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

    static EnumList<Suit> couleursAvecCartesMaitressesVuesChien(
            HandTarot _main, HandTarot _cartesJouees, HandTarot _chien,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
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
    static EnumList<Suit> couleursNonCoupeeParJoueurs(HandTarot _main,
                                                      Bytes _joueurs, IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                                      IdMap<Suit,CustList<HandTarot>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleursCoupees_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            EnumList<Suit> couleursLoc_ = couleursCoupeePar(_main,
                    joueur_, _cartesPossibles,
                    _cartesCertaines, _couleurs);
            for (Suit couleur_ : couleursLoc_) {
                couleursCoupees_.add(couleur_);
            }
        }
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if(couleursCoupees_.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static EnumList<Suit> couleursNonOuvertesAttaque(HandTarot _main,
                                                             CustList<TrickTarot> _plis, Bytes _attaquants,
                                                             EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        IdMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for (TrickTarot pli_ : _plis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
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

    static EnumList<Suit> couleursCoupeePar(HandTarot _main,
                                                    byte _joueur, IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
                                                    IdMap<Suit,CustList<HandTarot>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
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

    static EnumList<Suit> couleursNonTotalementJoueesEnFigures(
            HandTarot _cartesJouees, EnumList<Suit> _couleurs) {
        IdMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (cartesJouees_.getVal(couleur_).nombreDeFigures() == HandTarot.couleurComplete(
                    couleur_).nombreDeFigures()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static boolean plisTousFaitsParPreneurJoueur(byte _preneur,
                                                 CustList<TrickTarot> _unionPlis, byte _nombreJoueurs) {
        Bytes joueurs_ = new Bytes();
        joueurs_.add(_preneur);
        Bytes autresJoueurs_ = GameTarotTeamsRelation.autresJoueurs(joueurs_, _nombreJoueurs);
        for (byte joueur_ : autresJoueurs_) {
            Bytes joueursLoc_ = new Bytes(joueurs_);
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
