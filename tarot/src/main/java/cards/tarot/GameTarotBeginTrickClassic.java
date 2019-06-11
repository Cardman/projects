package cards.tarot;

import cards.consts.Status;
import cards.consts.Suit;
import cards.tarot.comparators.GameSeqLengthTarotComparator;
import cards.tarot.enumerations.CardTarot;
import code.maths.Rate;
import code.util.*;

public final class GameTarotBeginTrickClassic {

    private GameTarotTeamsRelation teamsRelation;

    private HandTarot calledCards;

    private HandTarot currentHand;
    private GameTarotCommonPlaying common;
    private Status currentStatus;

    public GameTarotBeginTrickClassic(GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation, HandTarot _calledCards, HandTarot _currentHand, byte _currentPlayer) {
        teamsRelation = _teamsRelation;
        calledCards = _calledCards;
        currentHand = _currentHand;
        common = new GameTarotCommonPlaying(_done,_teamsRelation);
        currentStatus = _teamsRelation.statutDe(_currentPlayer);
    }

    CardTarot entameSurExcuseClassique(
            HandTarot _lastHand,
            HandTarot _cartesJouables,
            byte _starter) {
        TarotInfoPliEnCours info_ = common.initInformations(_lastHand, currentHand,_cartesJouables, currentStatus);
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        HandTarot cartesJouees_ = info_.getCartesJouees();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean carteAppeleeJouee_ = info_.isCarteAppeleeJouee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(info_.getCurrentPlayer(), joueursNonJoue_);
        Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(info_.getCurrentPlayer(), joueursNonJoue_);
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        if(_starter == teamsRelation.getTaker()) {
            if(currentStatus == Status.CALLED_PLAYER) {
                HandTarot atoutsSansPetit_ = new HandTarot();
                for(CardTarot carte_ :atouts_) {
                    if(CardTarot.eq(carte_, CardTarot.petit())) {
                        continue;
                    }
                    atoutsSansPetit_.ajouter(carte_);
                }
                if(!atoutsSansPetit_.estVide()) {
                    return atoutsSansPetit_.premiereCarte();
                }
                EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
                if(!carteAppeleeJouee_) {
                    couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
                }

                EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(_cartesJouables, couleursAjouer_);
                EnumList<Suit> couleurs_ = couleursOuvertes(currentHand,
                        plisFaits_, couleursNonVidesAjouer_);
                couleurs_ = GameTarotCommon.couleursAvecCartesBasses(currentHand, couleurs_);
                if(!couleurs_.isEmpty()) {
                    EnumList<Suit> couleursCoupeesAdv_ = couleursCoupeeParJoueurs(currentHand,
                            joueursNonConfiance_, cartesPossibles_,
                            cartesCertaines_, couleurs_);
                    if(!couleursCoupeesAdv_.isEmpty()) {
                        couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursCoupeesAdv_);
                        couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                        return repartition_.getVal(couleurs_.first()).derniereCarte();
                    }
                }
                couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                        plisFaits_, couleursNonVidesAjouer_);
                couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(currentHand, cartesJouees_, couleurs_);
                if(!couleurs_.isEmpty()) {
                    couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                    return repartition_.getVal(couleurs_.first()).premiereCarte();
                }
                return entameClassique(currentHand, _cartesJouables);
            }
            //defenseur entamant sur l'excuse du preneur
            if(!teamsRelation.existeAppele() || teamsRelation.getCalledPlayers().containsObj(teamsRelation.getTaker())) {
                //jeu atout s'il n'exite aucun partenaire avec un atout
                // ou si le defenseur possede le petit
                if(GameTarotTrickHypothesis.joueursPossedantAtout(joueursConfiance_, cartesCertaines_).isEmpty()) {
                    if(!atouts_.estVide()) {
                        return atouts_.derniereCarte();
                    }
                }
                if(atouts_.contient(CardTarot.petit())) {
                    return CardTarot.petit();
                }
                EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
                EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(_cartesJouables, couleursAjouer_);
                EnumList<Suit> couleurs_ = couleursNonVidesAjouer_;
                EnumList<Suit> couleursCoupees_ = couleursCoupeePar(currentHand,
                        teamsRelation.getTaker(), cartesPossibles_,
                        cartesCertaines_, couleurs_);
                if(!couleursCoupees_.isEmpty()) {
                    couleurs_ = couleursNonTotalementJoueesEnFigures(currentHand, cartesJouees_, couleursCoupees_);
                    if(!couleurs_.isEmpty()) {
                        couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                        couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                        couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                        CardTarot carteHaute_ = repartition_.getVal(couleurs_.first()).premiereCarte();
                        if(carteHaute_.isCharacter()) {
                            return carteHaute_;
                        }
                        return repartition_.getVal(couleurs_.first()).derniereCarte();
                    }
                    couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursCoupees_);
                    couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                    return repartition_.getVal(couleurs_.first()).derniereCarte();
                }
                couleurs_ = couleursNonOuvertesAttaque(currentHand,
                        plisFaits_, teamsRelation.joueursNonConfiance(info_.getCurrentPlayer(),GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_)), couleursNonVidesAjouer_);
                if(!couleurs_.isEmpty()) {
                    couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                    return repartition_.getVal(couleurs_.first()).premiereCarte();
                }
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleursNonVidesAjouer_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).premiereCarte();
            }
            if(common.appeleConnuDefenseur(info_.getCurrentPlayer(),cartesPossibles_)) {
                //L'appele existe et est connu du defenseur courant
                EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
                EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(_cartesJouables, couleursAjouer_);
                EnumList<Suit> couleurs_ = couleursNonVidesAjouer_;
                boolean pasAtoutAppeles_ = true;
                for (byte a: teamsRelation.getCalledPlayers()) {
                    if(cartesPossibles_.getVal(Suit.TRUMP).get(a).estVide()) {
                        continue;
                    }
                    pasAtoutAppeles_ = false;
                    break;
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
                        return repartition_.getVal(couleurs_.first()).premiereCarte();
                    }
                    return entameClassique(currentHand, _cartesJouables);
                }
                couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleursNonVidesAjouer_);
                couleurs_ = GameTarotCommon.couleursAvecFigures(currentHand, couleurs_);
                couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(currentHand, cartesJouees_, couleurs_);
                if(!couleurs_.isEmpty()) {
                    couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                    return repartition_.getVal(couleurs_.first()).premiereCarte();
                }
                return entameClassique(currentHand, _cartesJouables);
            }
            //l'appele est a determiner
            EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
            EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(_cartesJouables, couleursAjouer_);
            EnumList<Suit> couleurs_ = couleursNonVidesAjouer_;
            couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleurs_);
            couleurs_ = GameTarotCommon.couleursAvecFigures(currentHand, couleurs_);
            couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(currentHand, cartesJouees_, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).premiereCarte();
            }
            couleurs_ = GameTarotCommon.couleursAvecFigures(currentHand, couleursNonVidesAjouer_);
            couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(currentHand, cartesJouees_, couleurs_);
            couleurs_ = couleursCoupeePar(currentHand,
                    teamsRelation.getTaker(), cartesPossibles_,
                    cartesCertaines_, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).premiereCarte();
            }
            for(Suit c: common.couleursAppelees()) {
                if(!carteAppeleeJouee_ && !repartition_.getVal(c).cartesBasses(c).estVide()) {
                    return repartition_.getVal(c).derniereCarte();
                }
            }

            couleurs_ = couleursNonAppelees(couleursNonVidesAjouer_);
            if(!carteAppeleeJouee_ && !couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            for(Suit c: common.couleursAppelees()) {
                if(!carteAppeleeJouee_ && !repartition_.getVal(c).estVide()) {
                    return repartition_.getVal(c).derniereCarte();
                }
            }

            if(!atouts_.estVide() && !atouts_.petitSec()) {
                return jeuAtoutOffensif(currentHand, cartesJouees_);
            }
        }
        return entameClassique(currentHand, _cartesJouables);
    }
    CardTarot entameClassique(HandTarot _lastHand,
                              HandTarot _cartesJouables) {
        if (_cartesJouables.total() == 1) {
            return _cartesJouables.premiereCarte();
        }
        TarotInfoPliEnCours info_ = common.initInformations(_lastHand, currentHand,_cartesJouables, currentStatus);
        if (info_.getCouleursMaitresses().size() == Suit.couleursOrdinaires().size() && info_.isMaitreAtout()) {
            return playWithStrongestHand(info_);
        }
        if(GameTarotCommonPlaying.maitreAtout(info_) && teamsRelation.existePreneur()) {
            CardTarot card_ = playWithStrongestTrumps(info_);
            if (card_ != CardTarot.WHITE) {
                return card_;
            }
        }
        CardTarot cardTr_ = playWithAtMostOneSuitCard(info_);
        if (cardTr_ != CardTarot.WHITE) {
            return cardTr_;
        }
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        boolean atoutsTousJoues_ = false;
        if(atouts_.total() + info_.getRepartitionCartesJouees().getVal(Suit.TRUMP).total() == HandTarot.atoutsSansExcuse().total()) {
            atoutsTousJoues_ = true;
        }
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
        if(info_.getCurrentPlayer() == teamsRelation.getTaker() || !teamsRelation.existePreneur()) {
            return playAsTaker(info_);
        }
        if(currentStatus == Status.CALLED_PLAYER) {
            return playAsCalledPlayer(info_);
        }
        return playAsDefender(info_);
        //couleur appelee

    }

    CardTarot playWithAtMostOneSuitCard(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        if(currentHand.total() == atouts_ .total() + repartition_.getVal(CardTarot.excuse().couleur()).total()) {
            //il n'y a que de l'atout (ev excuse)
            CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
            boolean contientExcuse_ = _info.isContientExcuse();
            boolean carteAppeleeJouee_ = _info.isCarteAppeleeJouee();
            HandTarot cartesJouees_ = _info.getCartesJouees();
            if (!contientExcuse_) {
                return jeuAtoutOffensif(currentHand, cartesJouees_);
            }
            if(atouts_.total() + repartition_.getVal(CardTarot.excuse().couleur()).total() == 2) {
                if(carteAppeleeJouee_ && teamsRelation.aucunPliAdverse(_info.getCurrentPlayer(),plisFaits_)) {
                    return atouts_.premiereCarte();
                }
                return CardTarot.excuse();
            }
            return jeuAtoutOffensif(currentHand, cartesJouees_);
        }
        if(currentHand.total() == atouts_.total() + repartition_.getVal(CardTarot.excuse().couleur()).total() + 1) {
            //une seule carte de couleur est presente
            boolean contientExcuse_ = _info.isContientExcuse();
            HandTarot cartesJouees_ = _info.getCartesJouees();
            EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
            EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
            EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
            Numbers<Byte> joueursNonJoue_ = _info.getJoueursNonJoue();
            Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(_info.getCurrentPlayer(), joueursNonJoue_);
            Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(_info.getCurrentPlayer(), joueursNonJoue_);
            HandTarot atoutsMaitres_ = repartition_.getVal(Suit.TRUMP)
                    .atoutsMaitres(repartitionCartesJouees_);
            EnumList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
            CardTarot carteCouleur_ = repartition_.getVal(couleurs_.first()).premiereCarte();
            Numbers<Byte> partenaires_ = GameTarotTrickHypothesis.joueursPouvantCouperCouleurs(currentHand, joueursConfiance_,
                    cartesPossibles_, couleurs_);
            partenaires_ = GameTarotTrickHypothesis.joueursPossedantAtoutMaitre(partenaires_, cartesCertaines_,
                    cartesJouees_);
            if(!partenaires_.isEmpty()) {
                return carteCouleur_;
            }
            EnumList<Suit> couleursMaitres_ = _info.getCouleursMaitresses();
            if(couleursMaitres_.size() == Suit.couleursOrdinaires().size()) {
                if(atouts_.total() == 1 && atouts_.contient(CardTarot.petit())) {
                    return carteCouleur_;
                }
                int nbAtoutsMaitres_ = atoutsMaitres_.total();
                if(nbAtoutsMaitres_ > 0) {
                    atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
                    return atoutsMaitres_.premiereCarte();
                }
                if(!carteCouleur_.isCharacter()) {
                    return carteCouleur_;
                }
                if(currentHand.total() == 2) {
                    if (contientExcuse_) {
                        return CardTarot.excuse();
                    }
                    return carteCouleur_;
                }
                if(currentHand.total() == 3) {
                    if (contientExcuse_ && atouts_.contient(CardTarot.petit())) {
                        return carteCouleur_;
                    }
                    if (contientExcuse_) {
                        return CardTarot.excuse();
                    }
                    return carteCouleur_;
                }
                atouts_.trierParForceEnCours(Suit.TRUMP);
                if (atouts_.contient(CardTarot.petit())) {
                    return atouts_.premiereCarte();
                }
                return atouts_.derniereCarte();
            }
            int nbAtoutsMaitres_ = atoutsMaitres_.total();
            if(nbAtoutsMaitres_ > 0) {
                if(currentHand.total() == 2 && atouts_.contient(CardTarot.petit())) {
                    return carteCouleur_;
                }
                atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
                return atoutsMaitres_.premiereCarte();
            }
            if(!GameTarotTrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles_,carteCouleur_.couleur(),joueursNonConfiance_).isEmpty()) {
                if(atouts_.total() >= 3) {
                    atouts_.trierParForceEnCours(Suit.TRUMP);
                    return atouts_.premiereCarte();
                }
                if(!GameTarotTrickHypothesis.joueursPossedantAtoutMaitre(joueursConfiance_,
                        cartesCertaines_, cartesJouees_).isEmpty()) {
                    atouts_.trierParForceEnCours(Suit.TRUMP);
                    return atouts_.derniereCarte();
                }
            }
            return carteCouleur_;
        }
        return CardTarot.WHITE;
    }
    CardTarot playWithStrongestTrumps(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suites_ = _info.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        Numbers<Byte> joueursNonJoue_ = _info.getJoueursNonJoue();
        EnumList<Suit> couleursStrictementMaitresses_ = GameTarotCommonPlaying.strictCouleursMaitres(
                suites_, repartitionCartesJouees_, cartesPossibles_, _info.getCurrentPlayer());
        HandTarot atoutsMaitres_ = repartition_.getVal(Suit.TRUMP)
                .atoutsMaitres(repartitionCartesJouees_);
        HandTarot cartesFictives_ = GameTarotCommonPlaying.getVirtualCards(common,currentStatus,calledCards);
        EnumList<Suit> couleursNonAppelees_ = couleursNonAppelees(Suit.couleursOrdinaires());
        EnumList<Suit> couleursPseudosMaitres_ = GameTarotBid.couleursPseudosMaitres(
                repartition_,
                GameTarotBid.cartesPseudoMaitresses(repartition_, cartesFictives_,
                        _info.getRepartitionCartesJouees()));
        EnumList<Suit> couleursMaitres_ = _info.getCouleursMaitresses();
        int nbAtoutsMaitres_ = atoutsMaitres_.total();
        if(atoutsMaitres_.contient(CardTarot.petit())) {
            nbAtoutsMaitres_--;
        }
        if(couleursPseudosMaitres_.containsAllObj(couleursAppelees_)) {
            EnumList<Suit> couleursStrictementMaitressesNonAppelees_ = couleursNonAppelees(couleursStrictementMaitresses_);
            if(couleursStrictementMaitressesNonAppelees_.containsAllObj(couleursNonAppelees_)) {
                if(nbAtoutsMaitres_ > 0) {
                    atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
                    return atoutsMaitres_.premiereCarte();
                }
                EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursAppelees_);
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                if(!couleurs_.isEmpty()) {
                    return carteCouleurAppeleeSousCarte(currentHand, couleurs_.first(), calledCards);
                }
            }
            if(couleursStrictementMaitressesNonAppelees_.size() + 1 == couleursNonAppelees_.size()) {
                if(couleursNonAppelees(couleursMaitres_).size() == 1) {
                    if(nbAtoutsMaitres_ > 1) {
                        atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
                        return atoutsMaitres_.premiereCarte();
                    }
                    if(nbAtoutsMaitres_ == 1) {
                        for(Suit c: couleursAppelees_) {
                            if(repartition_.getVal(c).total() == cartesMaitresses_.getVal(c).total() + 1) {
                                atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
                                return atoutsMaitres_.premiereCarte();
                            }
                        }
                        EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursAppelees_);
                        couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                        if(!couleurs_.isEmpty()) {
                            return carteCouleurAppeleeSousCarte(currentHand, couleurs_.first(), calledCards);
                        }
                    }
                    couleursMaitres_ =GameTarotCommon.couleursLesPlusLongues(currentHand, couleursMaitres_);
                    HandTarot couleurCandidate_ = repartition_.getVal(couleursMaitres_.first());
                    couleurCandidate_.trierParForceEnCours(couleursMaitres_.first());
                    return couleurCandidate_.premiereCarte();
                }
            }
            if(couleursNonAppelees(couleursMaitres_).size() == Suit.couleursOrdinaires().size() - 1) {
                boolean defausseTousJoueurs_ = true;
                EqList<HandTarot> atoutsJoueurs_ = cartesPossibles_.getVal(Suit.TRUMP);
                for(byte joueur_: joueursNonJoue_) {
                    if (!atoutsJoueurs_.get(joueur_).estVide()) {
                        defausseTousJoueurs_ = false;
                    }
                }
                if(defausseTousJoueurs_) {
                    EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursAppelees_);
                    couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                    if(!couleurs_.isEmpty()) {
                        return carteCouleurAppeleeSousCarte(currentHand, couleurs_.first(), calledCards);
                    }
                }
                if(nbAtoutsMaitres_ > 0) {
                    atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
                    return atoutsMaitres_.premiereCarte();
                }
                if(!couleursMaitres_.isEmpty()) {
                    couleursMaitres_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursMaitres_);
                    HandTarot couleurCandidate_ = repartition_.getVal(couleursMaitres_.first());
                    couleurCandidate_.trierParForceEnCours(couleursMaitres_.first());
                    return couleurCandidate_.premiereCarte();
                }
                EnumList<Suit> couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursAppelees_);
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                if(!couleurs_.isEmpty()) {
                    return carteCouleurAppeleeSousCarte(currentHand, couleurs_.first(), calledCards);
                }
            }
        }
        return CardTarot.WHITE;
    }
    CardTarot playAfterAllTrumps(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        boolean contientExcuse_ = _info.isContientExcuse();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suites_ = _info.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        Numbers<Byte> joueursNonJoue_ = _info.getJoueursNonJoue();
        byte nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        HandTarot cartesNonMaitresses_ = GameTarotCommonPlaying.cartesNonMaitresses(repartition_,
                cartesMaitresses_, suites_);
        EnumList<Suit> couleursMaitres_ = _info.getCouleursMaitresses();
        EnumList<Suit> notEmptySuits_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, couleursMaitres_);
        if(cartesNonMaitresses_.total() == 1) {
            if(!notEmptySuits_.isEmpty()) {
                return jeuAvecCarteMaitresseSansAtout(currentHand, cartesJouees_, notEmptySuits_);
            }
            return cartesNonMaitresses_.premiereCarte();
        }
        boolean aucuneCoupe_ = noTrumping(repartition_, cartesPossibles_, joueursNonJoue_, nombreDeJoueurs_);
        boolean touteCouleurPossedeCarteMaitresse_ = allSuitOwnLeadingCard(repartition_, cartesMaitresses_);
        if(aucuneCoupe_) {
            if(touteCouleurPossedeCarteMaitresse_) {
                return jeuAvecCarteMaitresseSansAtout(currentHand, cartesJouees_, Suit.couleursOrdinaires());
            }
            if(!notEmptySuits_.isEmpty()) {
                return jeuAvecCarteMaitresseSansAtout(currentHand, cartesJouees_, notEmptySuits_);
            }
            if(!contientExcuse_) {
                EnumList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
                couleurs_ = couleursSansCarteMaitresse(currentHand, cartesJouees_, couleurs_);
                if(!couleurs_.isEmpty()) {
                    couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                    return repartition_.getVal(couleurs_.first()).derniereCarte();
                }
                if(cartesNonMaitresses_.total() + atouts_.total() == currentHand.total()) {
                    couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, Suit.couleursOrdinaires());
                    return repartition_.getVal(couleurs_.first()).derniereCarte();
                }
                return jeuAvecCarteMaitresseSansAtout(currentHand, cartesJouees_, notEmptySuits_);
            }
            return CardTarot.excuse();
        }
        if(!notEmptySuits_.isEmpty()) {
            return jeuAvecCarteMaitresseSansAtout(currentHand, cartesJouees_, notEmptySuits_);
        }
        if(touteCouleurPossedeCarteMaitresse_) {
            return jeuAvecCarteMaitresseSansAtout(currentHand, cartesJouees_, Suit.couleursOrdinaires());
        }
        return CardTarot.WHITE;
    }
    CardTarot tryDemandTrumpSuit(TarotInfoPliEnCours _info) {
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        Numbers<Byte> joueursNonJoue_ = _info.getJoueursNonJoue();
        Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(_info.getCurrentPlayer(), joueursNonJoue_);
        if(!GameTarotTrickHypothesis.joueursPouvantPossederPetit(joueursConfiance_,
                cartesPossibles_).isEmpty()) {
            EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _info.getRepartitionCartesJouees();
            HandTarot atoutsMaitres_ = repartition_.getVal(Suit.TRUMP)
                    .atoutsMaitres(repartitionCartesJouees_);
            int nbAtoutsMaitres_ = atoutsMaitres_.total();
            if(nbAtoutsMaitres_ > 0) {
                atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
                return atoutsMaitres_.premiereCarte();
            }
        }
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        if(!GameTarotTrickHypothesis.joueursPossedantAtoutMaitre(joueursConfiance_,
                cartesCertaines_, cartesJouees_).isEmpty()) {
            if(atouts_.contient(CardTarot.petit())) {
                return CardTarot.petit();
            }
        }
        HandTarot atoutsJoues_ = cartesJouees_.couleur(Suit.TRUMP);
        byte nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        Rate moyenneAtout_ = GameTarotCommonPlaying.moyenneAtout(currentHand, atoutsJoues_,
                cartesPossibles_, nombreDeJoueurs_);
        if(!GameTarotTrickHypothesis.joueursPossedantNbAtout(joueursConfiance_,
                cartesCertaines_, moyenneAtout_).isEmpty()) {
            if(!atouts_.contient(CardTarot.petit()) && atouts_.total() >= 1) {
                return jeuAtoutOffensif(currentHand, cartesJouees_);
            }
            if(atouts_.total() > moyenneAtout_.ll() && atouts_.total() >= 2) {
                return jeuAtoutOffensif(currentHand, cartesJouees_);
            }
        }
        return CardTarot.WHITE;
    }
    private CardTarot playAsDefender(TarotInfoPliEnCours _info) {
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        byte nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        HandTarot cartesJouables_ = _info.getCartesJouables();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        Numbers<Byte> joueursNonJoue_ = _info.getJoueursNonJoue();
        byte next_ = _info.getProgressingTrick().getNextPlayer(nombreDeJoueurs_);
        boolean carteAppeleeJouee_ = _info.isCarteAppeleeJouee();
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(next_, joueursNonJoue_);
        EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        if(!carteAppeleeJouee_) {
            couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
        }
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(cartesJouables_, couleursAjouer_);
        //Defenseur
        if (!carteAppeleeJouee_) {
            for(Suit c: couleursAppelees_) {
                if(!cartesJouables_.couleur(c).estVide()) {
                    boolean defausseTousJoueurs_ = true;
                    EqList<HandTarot> atoutsJoueurs_ = cartesPossibles_.getVal(Suit.TRUMP);
                    for(byte joueur_: joueursNonConfiance_) {
                        if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                            defausseTousJoueurs_ = false;
                        }
                    }
                    if(!defausseTousJoueurs_) {
                        if(GameTarotCommon.couleursAvecFigures(cartesJouables_,Suit.couleursOrdinaires()).containsObj(c)) {
                            if(couleursCoupeeParJoueurs(cartesJouables_,
                                    joueursNonConfiance_, cartesPossibles_,
                                    cartesCertaines_, Suit.couleursOrdinaires()).containsObj(c)) {
                                return repartition_.getVal(c).premiereCarte();
                            }
                        }
                    }
                    if(GameTarotCommon.couleursAvecCartesBasses(cartesJouables_,Suit.couleursOrdinaires()).containsObj(c)) {
                        return repartition_.getVal(c).derniereCarte();
                    }
                }
            }
        }

        //ouvreur
        if(defenseOuvreur(next_,carteAppeleeJouee_)) {
            if(defenseOuvreurStrict(next_)) {
                EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(cartesJouables_,
                        plisFaits_, couleursNonVidesAjouer_);
                couleurs_ = GameTarotCommon.couleursSansFigures(cartesJouables_, couleurs_);
                if(!couleurs_.isEmpty()) {
                    couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusBasses(cartesJouables_, couleurs_);
                    return repartition_.getVal(couleurs_.first()).derniereCarte();
                }
            }
            EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(cartesJouables_,
                    plisFaits_, couleursNonVidesAjouer_);
            couleurs_ = GameTarotCommon.couleursAvecCartesBasses(cartesJouables_, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(cartesJouables_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(cartesJouables_,
                    plisFaits_, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(cartesJouables_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursAvecCartesBasses(cartesJouables_, couleursNonVidesAjouer_);
            couleurs_ = couleursNonOuvertesAttaque(cartesJouables_, plisFaits_,
                    joueursNonConfiance_, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(cartesJouables_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = couleursNonOuvertesAttaque(cartesJouables_, plisFaits_,
                    joueursNonConfiance_, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(cartesJouables_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            return playDefaultCardAsDefender(cartesJouables_, cartesJouees_, repartition_, atouts_, couleursNonVidesAjouer_);
            //couleur appelee
        }
        EnumList<Suit> couleurs_ = couleursOuvertes(cartesJouables_,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(cartesJouables_, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommonPlaying.couleursLesMoinsEntameesParJoueurs(plisFaits_, joueursNonConfiance_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(cartesJouables_, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(cartesJouables_,
                plisFaits_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommonPlaying.couleursLesMoinsEntameesParJoueurs(plisFaits_, joueursNonConfiance_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouables_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(cartesJouables_, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        return playDefaultCardAsDefender(cartesJouables_, cartesJouees_, repartition_, atouts_, couleursNonVidesAjouer_);
    }

    private CardTarot playDefaultCardAsDefender(HandTarot _cartesJouables, HandTarot _cartesJouees, EnumMap<Suit, HandTarot> _repartition, HandTarot _atouts, EnumList<Suit> _couleursNonVidesAjouer) {
        EnumList<Suit> couleurs_;
        if (!_couleursNonVidesAjouer.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_cartesJouables, _couleursNonVidesAjouer);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouees, couleurs_);
            return _repartition.getVal(couleurs_.first()).derniereCarte();
        }
        return playDefaultCard(_repartition, _atouts);
    }

    static boolean allSuitOwnLeadingCard(EnumMap<Suit, HandTarot> _repartition, EnumMap<Suit, HandTarot> _cartesMaitresses) {
        boolean touteCouleurPossedeCarteMaitresse_ = true;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if(_repartition.getVal(couleur_).estVide()) {
                continue;
            }
            if(!_cartesMaitresses.getVal(couleur_).estVide()) {
                continue;
            }
            touteCouleurPossedeCarteMaitresse_ = false;
            break;
        }
        return touteCouleurPossedeCarteMaitresse_;
    }

    static boolean noTrumping(EnumMap<Suit, HandTarot> _repartition, EnumMap<Suit, EqList<HandTarot>> _cartesPossibles, Numbers<Byte> _joueursNonJoue, byte _nombreDeJoueurs) {
        boolean aucuneCoupe_ = true;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            boolean plusCartesCouleurAutres_ = true;
            Numbers<Byte> joueurs_ = new Numbers<Byte>(GameTarotTeamsRelation.autresJoueurs(_joueursNonJoue, _nombreDeJoueurs));
            for(byte joueur_: GameTarotTeamsRelation.autresJoueurs(joueurs_, _nombreDeJoueurs)) {
                if(_cartesPossibles.getVal(couleur_).get(joueur_).estVide()) {
                    continue;
                }
                plusCartesCouleurAutres_ = false;
                break;
            }
            if(plusCartesCouleurAutres_) {
                continue;
            }
            if(!_repartition.getVal(couleur_).estVide()) {
                continue;
            }
            aucuneCoupe_ = false;
            break;
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
    private CardTarot playAsCalledPlayer(TarotInfoPliEnCours _info) {
        byte nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        HandTarot playableCards_ = _info.getCartesJouables();
        Numbers<Byte> joueursNonJoue_ = _info.getJoueursNonJoue();
        byte next_ = _info.getProgressingTrick().getNextPlayer(nombreDeJoueurs_);
        Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(next_, joueursNonJoue_);
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot cartesChien_ = common.cartesVuesAuChien();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        HandTarot atoutsJoues_ = cartesJouees_.couleur(Suit.TRUMP);
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        boolean carteAppeleeJouee_ = _info.isCarteAppeleeJouee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        boolean touteCouleurPossedeCarteMaitresse_ = allSuitOwnLeadingCard(repartition_, cartesMaitresses_);
        if(joueursNonJoue_.containsObj(teamsRelation.getTaker())) {
            boolean jouerUneCouleurAppelee_ = false;
            HandTarot cartesPossedees_ = new HandTarot();
            HandTarot cartesNonPossedees_ = new HandTarot();
            for(CardTarot c: calledCards) {
                if(currentHand.contient(c)) {
                    cartesPossedees_.ajouter(c);
                    continue;
                }
                cartesNonPossedees_.ajouter(c);
            }
            if(!cartesPossedees_.estVide()) {
                boolean defausseTousJoueurs_ = true;
                EqList<HandTarot> atoutsJoueurs_ = cartesPossibles_.getVal(Suit.TRUMP);
                for(byte joueur_: joueursNonConfiance_) {
                    if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                        defausseTousJoueurs_ = false;
                    }
                }
                if(defausseTousJoueurs_) {
                    jouerUneCouleurAppelee_ = true;
                }
                if(couleursNonAppelees(Suit.couleursOrdinaires()).isEmpty() && !jouerAtout(currentHand, atoutsJoues_,
                        cartesPossibles_, nombreDeJoueurs_)) {
                    jouerUneCouleurAppelee_ = true;
                }
            }
            if(jouerUneCouleurAppelee_) {
                if(!cartesNonPossedees_.estVide()) {
                    EnumList<Suit> couleursCartesNonPossedees_ = new EnumList<Suit>();
                    for(CardTarot c: cartesNonPossedees_) {
                        if(couleursCartesNonPossedees_.containsObj(c.couleur())) {
                            continue;
                        }
                        couleursCartesNonPossedees_.add(c.couleur());
                    }
                    EnumList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, couleursCartesNonPossedees_);
                    if(!couleurs_.isEmpty()) {
                        couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                        return repartition_.getVal(couleurs_.first()).derniereCarte();
                    }
                } else {
                    return cartesPossedees_.premiereCarte();
                }
            }
        }
        EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
        if(!carteAppeleeJouee_) {
            couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
        }
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards_, couleursAjouer_);
        EnumList<Suit> couleurs_;
        if(!couleursNonVidesAjouer_.isEmpty() && GameTarotTeamsRelation.justeApresJoueur(next_, teamsRelation.getTaker(), nombreDeJoueurs_) && joueursNonJoue_.containsObj(teamsRelation.getTaker())) {
            couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                    plisFaits_, couleursNonVidesAjouer_);
            couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                    plisFaits_, couleursNonVidesAjouer_);
            couleurs_ = couleursSansCarteMaitresse(currentHand, cartesJouees_, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            if(jouerAtout(currentHand, atoutsJoues_,
                    cartesPossibles_, nombreDeJoueurs_) && touteCouleurPossedeCarteMaitresse_) {
                return jeuAtoutOffensif(currentHand, cartesJouees_);
            }
            couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                    plisFaits_, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                return repartition_.getVal(couleurs_.first()).premiereCarte();
            }
            couleurs_ = couleursCoupeePar(currentHand,
                    teamsRelation.getTaker(), cartesPossibles_,
                    cartesCertaines_, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                if(cartesPossibles_.getVal(Suit.TRUMP).get(teamsRelation.getTaker()).contient(CardTarot.petit())) {
                    couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                    return repartition_.getVal(couleurs_.first()).derniereCarte();
                }
            }
            CardTarot card_ = tryPlayCalledCard(_info, couleursAjouer_);
            if (card_ != CardTarot.WHITE) {
                return card_;
            }
        }
        Numbers<Byte> defenseurs_ = joueursAvantAppeleApresPreneur(GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_),next_);
        boolean defausseTousDefenseursIntermediaire_ = true;
        EqList<HandTarot> atoutsJoueurs_ = cartesPossibles_.getVal(Suit.TRUMP);
        for(byte joueur_: defenseurs_) {
            if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                defausseTousDefenseursIntermediaire_ = false;
            }
        }
        if(defausseTousDefenseursIntermediaire_
                && cartesPossibles_.getVal(Suit.TRUMP).get(teamsRelation.getTaker()).contient(CardTarot.petit())
                && joueursNonJoue_.containsObj(teamsRelation.getTaker())) {
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleursNonVidesAjouer_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        Numbers<Byte> partenaires_ = new Numbers<Byte>();
        partenaires_.add(teamsRelation.getTaker());
        if(GameTarotTeamsRelation.apresJoueur(next_, teamsRelation.getTaker(), nombreDeJoueurs_) && joueursNonJoue_.containsObj(teamsRelation.getTaker())) {
            CardTarot card_ = tryPlayCalledCard(_info, couleursAjouer_);
            if (card_ != CardTarot.WHITE) {
                return card_;
            }
        }
        couleurs_ = couleursEntameesParJoueurs(plisFaits_, partenaires_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursEntameesParJoueurs(plisFaits_, partenaires_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        if(jouerAtout(currentHand, atoutsJoues_,
                cartesPossibles_, nombreDeJoueurs_) && touteCouleurPossedeCarteMaitresse_) {
            return jeuAtoutOffensif(currentHand, cartesJouees_);
        }
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = couleursSansCarteMaitresse(currentHand, cartesJouees_, couleurs_);
        couleurs_ = couleursCoupeePar(currentHand,
                teamsRelation.getTaker(), cartesPossibles_,
                cartesCertaines_, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            boolean defausseTousJoueurs_ = true;
            for(byte joueur_: joueursNonConfiance_) {
                if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                    defausseTousJoueurs_ = false;
                }
            }
            if(defausseTousJoueurs_) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
                return repartition_.getVal(couleurs_.first()).premiereCarte();
            }
            EnumList<Suit> couleursCoupeesAdv_ = couleursCoupeeParJoueurs(currentHand,
                    joueursNonConfiance_, cartesPossibles_,
                    cartesCertaines_, couleurs_);
            if(!couleursCoupeesAdv_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleursCoupeesAdv_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursAvecCartesMaitressesVuesChien(currentHand, cartesJouees_,
                cartesChien_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursNonVidesAjouer_;
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        HandTarot cartesPossedees_ = new HandTarot();
        for(CardTarot c: calledCards) {
            if(currentHand.contient(c)) {
                continue;
            }
            cartesPossedees_.ajouter(c);
        }
        if(!cartesPossedees_.estVide()) {
            return cartesPossedees_.premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(cartesJouees_, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
        return repartition_.getVal(couleurs_.first()).derniereCarte();
    }

    CardTarot tryPlayCalledCard(TarotInfoPliEnCours _info, EnumList<Suit> _suits) {
        Numbers<Byte> partenaires_ = new Numbers<Byte>();
        partenaires_.add(teamsRelation.getTaker());
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot playableCards_ = _info.getCartesJouables();
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards_, _suits);
        EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursLesPlusEntameesParJoueurs(plisFaits_, partenaires_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusCourtes(currentHand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        HandTarot cartesPossedees_ = new HandTarot();
        for(CardTarot c: calledCards) {
            if(currentHand.contient(c)) {
                continue;
            }
            cartesPossedees_.ajouter(c);
        }
        if(!cartesPossedees_.estVide()) {
            return cartesPossedees_.premiereCarte();
        }
        return CardTarot.WHITE;
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
    private CardTarot playAsTaker(TarotInfoPliEnCours _info) {
        byte nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        HandTarot playableCards_ = _info.getCartesJouables();
        HandTarot playedCards_ = _info.getCartesJouees();
        HandTarot atoutsJoues_ = playedCards_.couleur(Suit.TRUMP);
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        Numbers<Byte> joueursNonJoue_ = _info.getJoueursNonJoue();
        byte next_ = _info.getProgressingTrick().getNextPlayer(nombreDeJoueurs_);
        Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(next_, joueursNonJoue_);
        Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(next_, joueursNonJoue_);
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        boolean carteAppeleeJouee_ = _info.isCarteAppeleeJouee();
        boolean noPossibleTrump_ = noTrumping(repartition_, cartesPossibles_, joueursNonJoue_, nombreDeJoueurs_);
        EnumMap<Suit,HandTarot> cartesMaitresses_ = _info.getCartesMaitresses();
        boolean touteCouleurPossedeCarteMaitresse_ = allSuitOwnLeadingCard(repartition_, cartesMaitresses_);
        for(Suit c: couleursAppelees_) {
            if(!carteAppeleeJouee_ && !playableCards_.couleur(c).estVide()) {
                //jouer la couleur appelee dans certains cas
                boolean defausseTousJoueurs_ = true;
                EqList<HandTarot> atoutsJoueurs_ = cartesPossibles_.getVal(Suit.TRUMP);
                for(byte joueur_: joueursNonConfiance_) {
                    if (!atoutsJoueurs_.get(joueur_).estVide()) {
                        defausseTousJoueurs_ = false;
                    }
                }
                if(defausseTousJoueurs_ && touteCouleurPossedeCarteMaitresse_) {
                    return repartition_.getVal(c).derniereCarte();
                }
                EnumList<Suit> couleurs_ = couleursNonAppelees(Suit.couleursOrdinaires());
                couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                        plisFaits_, couleurs_);
                if(couleurs_.isEmpty()) {
                    if(atouts_.estVide() || !jouerAtout(currentHand, atoutsJoues_,
                            cartesPossibles_, nombreDeJoueurs_)) {
                        return repartition_.getVal(c).derniereCarte();
                    }
                }
            }
        }
        EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
        if(!carteAppeleeJouee_) {
            couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
        }
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(playableCards_, couleursAjouer_);
        EnumList<Suit> couleurs_ = couleursOuvertes(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(currentHand, couleurs_);
        if(!couleurs_.isEmpty()) {
            Numbers<Byte> joueurs_ = new Numbers<Byte>(GameTarotTeamsRelation.autresJoueurs(joueursNonJoue_,nombreDeJoueurs_));
            EnumList<Suit> couleursPossiblementCoupees_ = GameTarotTrickHypothesis.couleursPouvantEtreCoupees(currentHand,
                    GameTarotTeamsRelation.autresJoueurs(joueurs_,nombreDeJoueurs_),
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
        couleurs_ = couleursSansCarteMaitresse(currentHand, playedCards_, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        if(jouerAtout(currentHand, atoutsJoues_,
                cartesPossibles_, nombreDeJoueurs_)) {
            if(touteCouleurPossedeCarteMaitresse_) {
                return jeuAtoutOffensif(currentHand, playedCards_);
            }
            if(!couleursNonVidesAjouer_.isEmpty()) {
                couleurs_ = couleursSansCarteMaitresse(currentHand, playedCards_, couleursNonVidesAjouer_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(currentHand, playedCards_, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(currentHand,
                plisFaits_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(playedCards_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        if(noPossibleTrump_ && !atouts_.contient(CardTarot.petit()) && !atouts_.estVide()) {
            return jeuAtoutOffensif(currentHand, playedCards_);
        }
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(currentHand,
                couleursNonVidesAjouer_);
        couleurs_ = couleursCoupeeParJoueurs(currentHand,
                joueursNonConfiance_, cartesPossibles_,
                cartesCertaines_, couleurs_);
        if(!couleurs_.isEmpty()) {
            EnumList<Suit> couleursNonCoupeesJoueursConfiance_ = couleursNonCoupeeParJoueurs(currentHand,
                    joueursConfiance_, cartesPossibles_,
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
        return playDefaultCard(repartition_, atouts_);
        //couleur appelee
    }

    CardTarot playDefaultCard(EnumMap<Suit, HandTarot> _repartition, HandTarot _atouts) {
        EnumList<Suit> couleurs_;
        if (!_atouts.estVide()) {
            _atouts.trierParForceEnCours(Suit.TRUMP);
            if (_atouts.contient(CardTarot.petit())) {
                return _atouts.premiereCarte();
            }
            return _atouts.derniereCarte();
        }
        if (currentHand.contient(CardTarot.EXCUSE)) {
            return CardTarot.EXCUSE;
        }
        couleurs_ = Suit.couleursOrdinaires();
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
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
        HandTarot cartesJouees_ = _info.getCartesJouees();
        CustList<TrickTarot> plisFaits_ = _info.getPlisFaits();
        boolean contientExcuse_ = _info.isContientExcuse();
        boolean carteAppeleeJouee_ = _info.isCarteAppeleeJouee();
        if (!contientExcuse_) {
            return jeuMainMaitresse(currentHand,cartesJouees_);
        }
        if (teamsRelation.getTaker() == next_ || !teamsRelation.existePreneur()) {
            //Preneur
            boolean playExc_ = true;
            if (carteAppeleeJouee_) {
                if(teamsRelation.aucunPliAdverse(next_,plisFaits_)) {
                    playExc_ = false;
                }
            } else if(!currentHand.contientCartes(calledCards)) {
                if(plisTousFaitsParPreneurJoueur(teamsRelation.getTaker(),plisFaits_,nbPlayers_)) {
                    playExc_ = false;
                }
            } else {
                Numbers<Byte> joueursRamasseurs_ = new Numbers<Byte>();
                joueursRamasseurs_.add(teamsRelation.getTaker());
                if(GameTarotTrickInfo.plisTousFaitsPar(joueursRamasseurs_,plisFaits_,nbPlayers_)) {
                    playExc_ = false;
                }
            }
            if (playExc_) {
                return CardTarot.excuse();
            }
            return jeuMainMaitresse(currentHand,cartesJouees_);
        }
        if(currentStatus == Status.CALLED_PLAYER) {
            //Appele
            //existeAppele
            if(teamsRelation.aucunPliAdverse(next_,plisFaits_)) {
                return jeuMainMaitresse(currentHand,cartesJouees_);
            }
            return CardTarot.excuse();
        }
        Numbers<Byte> joueursConfianceNumero_ = new Numbers<Byte>(teamsRelation.joueursConfiance(next_,GameTarotTeamsRelation.tousJoueurs(nbPlayers_)));
        joueursConfianceNumero_.add(next_);
        if(GameTarotTrickInfo.plisTousFaitsPar(joueursConfianceNumero_,plisFaits_,nbPlayers_)) {
            return jeuMainMaitresse(currentHand,cartesJouees_);
        }
        return CardTarot.excuse();
    }

    boolean defenseOuvreur(byte _joueur, boolean _playedCalledCard) {
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        byte nextPlayer_ = common.playerAfter(_joueur);
        if (_playedCalledCard) {
            Numbers<Byte> attaque_ = teamsRelation.joueursNonConfiance(_joueur,GameTarotTeamsRelation.tousJoueurs(teamsRelation.getNombreDeJoueurs()));
            return attaque_.containsObj(nextPlayer_);
        }
        if (GameTarotTeamsRelation.justeApresJoueur(teamsRelation.getTaker(), _joueur, nombreDeJoueurs_)) {
            return true;
        }
        return teamsRelation.getTaker() == (_joueur + 2) % nombreDeJoueurs_;
    }

    boolean defenseOuvreurStrict(byte _joueur) {
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        Numbers<Byte> attaque_ = teamsRelation.joueursNonConfiance(_joueur,GameTarotTeamsRelation.tousJoueurs(teamsRelation.getNombreDeJoueurs()));
        if (attaque_.size() == 2) {
            byte nextPlayer_ = common.playerAfter(_joueur);
            return attaque_.containsObj(nextPlayer_);
        }
        return GameTarotTeamsRelation.justeApresJoueur(teamsRelation.getTaker(), _joueur, nombreDeJoueurs_);
    }
    Numbers<Byte> joueursAvantAppeleApresPreneur(Numbers<Byte> _joueurs, byte _appele) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        byte player_ = common.playerAfter(teamsRelation.getTaker());
        //called player exists
        while (!Numbers.eq(player_, _appele)) {
            if (_joueurs.containsObj(player_)) {
                joueurs_.add(player_);
            }
            player_ =  common.playerAfter(player_);
        }
        return joueurs_;
    }
    static EnumList<Suit> couleursEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            boolean couleurEntamee_ = false;
            for(TrickTarot pli_: _plisFaits) {
                if (!pli_.getVuParToutJoueur()) {
                    continue;
                }
                if (pli_.couleurDemandee() != couleur_) {
                    continue;
                }
                if (!_joueurs.containsObj(pli_.getEntameur())) {
                    continue;
                }
                couleurEntamee_ = true;
                break;
            }
            if (!couleurEntamee_) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    static CardTarot jeuMainMaitresse(HandTarot _currentHand,HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> repartition_ = _currentHand.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        HandTarot atoutsSansPetit_ = new HandTarot();
        for(CardTarot carte_ :atouts_) {
            if(CardTarot.eq(carte_, CardTarot.petit())) {
                continue;
            }
            atoutsSansPetit_.ajouter(carte_);
        }
        if(!atoutsSansPetit_.estVide()) {
            return atoutsSansPetit_.premiereCarte();
        }
        EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(_currentHand,
                _cartesJouees, Suit.couleursOrdinaires());
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_currentHand,couleurs_);
            HandTarot cartesCouleur_ = repartition_.getVal(couleurs_.first());
            cartesCouleur_.trierParForceEnCours(couleurs_.first());
            return cartesCouleur_.premiereCarte();
        }
        return CardTarot.petit();
    }
    static EnumList<Suit> couleursSansCarteMaitresse(HandTarot _main,
                                                     HandTarot _cartesJouees, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        for (Suit couleur_ : _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            HandTarot cartesMaitresses_ = GameTarotCommon.cartesMaitresses(couleursMains_,
                    cartesJouees_).getVal(couleur_);
            if (cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }
    static CardTarot carteCouleurAppeleeSousCarte(HandTarot _main,
                                                  Suit _couleur,
                                                  HandTarot _carteAppelee) {
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
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
                                                   Numbers<Byte> _joueurs, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
                                                   EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, EnumList<Suit> _couleurs) {
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
                              EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _nombreJoueurs) {
        EqList<HandTarot> repartitionAtout_ = _cartesPossibles
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
        int nb_ = _main.couleur(Suit.TRUMP).total();
        if (_main.contient(CardTarot.petit())) {
            nb_--;
        }
        return nb_ * nombreJoueursAvecAtout_ >= atoutsNonJoues_
                .total();
    }
    static CardTarot jeuAtoutOffensif(HandTarot _currentHand,HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> repartition_ = _currentHand.couleurs();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _cartesJouees.couleurs();
        HandTarot atoutsMaitres_ = repartition_.getVal(Suit.TRUMP)
                .atoutsMaitres(repartitionCartesJouees_);
        if(!atoutsMaitres_.estVide()) {
            return atoutsMaitres_.premiereCarte();
        }
        EqList<HandTarot> suitesAtouts_ = repartition_.getVal(Suit.TRUMP).eclaterEnCours(
                repartitionCartesJouees_, Suit.TRUMP);
        suitesAtouts_.sortElts(new GameSeqLengthTarotComparator());
        return suitesAtouts_.first().premiereCarte();
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
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for (TrickTarot pli_ : _plis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (couleurDemandee_ == Suit.TRUMP) {
                continue;
            }
            couleursOuvertes_.add(couleurDemandee_);
        }
        for (Suit couleur_ : _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            if (!couleursOuvertes_.containsObj(couleur_)) {
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
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
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
            if (cartesMaitressesChien_.estVide()) {
                continue;
            }
            couleurs_.add(couleur_);
        }

        return couleurs_;
    }
    static EnumList<Suit> couleursNonCoupeeParJoueurs(HandTarot _main,
                                                      Numbers<Byte> _joueurs, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
                                                      EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleursCoupees_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            EnumList<Suit> couleursLoc_ = couleursCoupeePar(_main,
                    joueur_, _cartesPossibles,
                    _cartesCertaines, _couleurs);
            for (Suit couleur_ : couleursLoc_) {
                couleursCoupees_.add(couleur_);
            }
        }
        couleursCoupees_.removeDuplicates();
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
                                                             CustList<TrickTarot> _plis, Numbers<Byte> _attaquants,
                                                             EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for (TrickTarot pli_ : _plis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (couleurDemandee_ == Suit.TRUMP) {
                continue;
            }
            if (!_attaquants.containsObj(pli_.getEntameur())) {
                continue;
            }
            couleursOuvertes_.add(couleurDemandee_);
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
                                                    byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
                                                    EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        if (!_cartesCertaines.getVal(Suit.TRUMP).get(_joueur).estVide()) {
            for (Suit couleur_ : _couleurs) {
                if (!_cartesPossibles.getVal(couleur_).get(_joueur).estVide()) {
                    continue;
                }
                if (couleursMains_.getVal(couleur_).estVide()) {
                    continue;
                }
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursNonTotalementJoueesEnFigures(
            HandTarot _main, HandTarot _cartesJouees, EnumList<Suit> _couleurs) {
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (_main.couleur(couleur_).estVide()) {
                continue;
            }
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
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        joueurs_.add(_preneur);
        Numbers<Byte> autresJoueurs_ = GameTarotTeamsRelation.autresJoueurs(joueurs_, _nombreJoueurs);
        for (byte joueur_ : autresJoueurs_) {
            Numbers<Byte> joueursLoc_ = new Numbers<Byte>(joueurs_);
            joueursLoc_.add(joueur_);
            if (GameTarotTrickInfo.plisTousFaitsPar(joueursLoc_, _unionPlis, _nombreJoueurs)) {
                return true;
            }
        }
        return false;
    }
}
