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

    public GameTarotBeginTrickClassic(GameTarotTrickInfo _done, GameTarotTeamsRelation _teamsRelation, HandTarot _calledCards, HandTarot _currentHand) {
        teamsRelation = _teamsRelation;
        calledCards = _calledCards;
        currentHand = _currentHand;
        common = new GameTarotCommonPlaying(_done,_teamsRelation);
    }

    CardTarot entameSurExcuseClassique(
            byte _numero,
            HandTarot _lastHand,
            HandTarot _cartesJouables,
            byte _starter) {
        TarotInfoPliEnCours info_ = common.initInformations(_lastHand,_numero, currentHand);
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        HandTarot cartesJouees_ = info_.getCartesJouees();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean carteAppeleeJouee_ = info_.isCarteAppeleeJouee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesFictives_.ajouterCartes(calledCards);
        cartesChien_ = common.cartesVuesAuChien();
        if (teamsRelation.statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(_numero, joueursNonJoue_);
        Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(_numero, joueursNonJoue_);
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        if(_starter == teamsRelation.getTaker()) {
            if(teamsRelation.statutDe(_numero) == Status.CALLED_PLAYER) {
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
                return entameClassique(_numero, currentHand, _cartesJouables);
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
                        plisFaits_, teamsRelation.adversaires(_numero,GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_)), couleursNonVidesAjouer_);
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
            if(common.appeleConnuDefenseur(teamsRelation,_numero,cartesPossibles_)) {
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
                    return entameClassique(_numero, currentHand, _cartesJouables);
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
                return entameClassique(_numero, currentHand, _cartesJouables);
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
        return entameClassique(_numero, currentHand, _cartesJouables);
    }
    CardTarot entameClassique(byte _numero,
                              HandTarot _lastHand,
                              HandTarot _cartesJouables) {
        if (_cartesJouables.total() == 1) {
            return _cartesJouables.premiereCarte();
        }
        TarotInfoPliEnCours info_ = common.initInformations(_lastHand, _numero, _cartesJouables);
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
        HandTarot cartesJouees_ = info_.getCartesJouees();
        HandTarot atoutsJoues_ = cartesJouees_.couleur(Suit.TRUMP);
        boolean excuseJouee_ = cartesJouees_.contient(CardTarot.excuse());
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suites_ = info_.getSuitesTouteCouleur();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        boolean carteAppeleeJouee_ = info_.isCarteAppeleeJouee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        boolean strictMaitreAtout_ = info_.isMaitreAtout();
        boolean maitreAtout_ = GameTarotCommonPlaying.maitreAtout(suites_.getVal(Suit.TRUMP),
                cartesJouees_, excuseJouee_, contientExcuse_);
        EnumList<Suit> couleursMaitres_ = info_.getCouleursMaitresses();
        HandTarot atoutsMaitres_ = repartition_.getVal(Suit.TRUMP)
                .atoutsMaitres(repartitionCartesJouees_);
        EnumList<Suit> couleursAppelees_ = common.couleursAppelees();
        HandTarot cartesChien_;
        EnumList<Suit> couleursStrictementMaitresses_ = GameTarotCommonPlaying.strictCouleursMaitres(
                suites_, repartitionCartesJouees_, cartesPossibles_, _numero);
        EnumMap<Suit,HandTarot> cartesMaitresses_ = info_.getCartesMaitresses();
        HandTarot cartesNonMaitresses_ = GameTarotCommonPlaying.cartesNonMaitresses(repartition_,
                cartesMaitresses_, suites_);
        HandTarot cartesFictives_ = new HandTarot();
        cartesChien_ = common.cartesVuesAuChien();
        if (teamsRelation.statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(calledCards);
            cartesFictives_.ajouterCartes(cartesChien_);
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        EnumList<Suit> couleursPseudosMaitres_ = GameTarotBid.couleursPseudosMaitres(
                repartition_,
                GameTarotBid.cartesPseudoMaitresses(repartition_, cartesFictives_,
                        info_.getRepartitionCartesJouees()));
        Numbers<Byte> joueursConfiance_ = teamsRelation.joueursConfiance(_numero, joueursNonJoue_);
        Numbers<Byte> joueursNonConfiance_ = teamsRelation.joueursNonConfiance(_numero, joueursNonJoue_);
        byte nombreDeJoueurs_ =teamsRelation.getNombreDeJoueurs();
        if (couleursMaitres_.size() == Suit.couleursOrdinaires().size() && strictMaitreAtout_) {
            return playWithStrongestHand(teamsRelation,_numero, currentHand, cartesJouees_,
                    plisFaits_, contientExcuse_, carteAppeleeJouee_
            );
        }
        EnumList<Suit> couleursNonAppelees_ = couleursNonAppelees(Suit.couleursOrdinaires());
        if(maitreAtout_ && teamsRelation.existePreneur()) {
            if(couleursPseudosMaitres_.containsAllObj(couleursAppelees_)) {
                EnumList<Suit> couleursStrictementMaitressesNonAppelees_ = couleursNonAppelees(couleursStrictementMaitresses_);
                if(couleursStrictementMaitressesNonAppelees_.containsAllObj(couleursNonAppelees_)) {
                    int nbAtoutsMaitres_ = atoutsMaitres_.total();
                    if(atoutsMaitres_.contient(CardTarot.petit())) {
                        nbAtoutsMaitres_--;
                    }
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
                        int nbAtoutsMaitres_ = atoutsMaitres_.total();
                        if(atoutsMaitres_.contient(CardTarot.petit())) {
                            nbAtoutsMaitres_--;
                        }
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
                        couleursMaitres_ =GameTarotCommon. couleursLesPlusLongues(currentHand, couleursMaitres_);
                        HandTarot couleurCandidate_ = repartition_.getVal(couleursMaitres_.first());
                        couleurCandidate_.trierParForceEnCours(couleursMaitres_.first());
                        return couleurCandidate_.premiereCarte();
                    }
                }
                if(couleursNonAppelees(couleursMaitres_).size() == Suit.couleursOrdinaires().size() - 1) {
                    boolean defausseTousJoueurs_ = true;
                    EqList<HandTarot> atoutsJoueurs_ = cartesPossibles_.getVal(Suit.TRUMP);
                    Numbers<Byte> joueurs_ = new Numbers<Byte>(GameTarotTeamsRelation.autresJoueurs(joueursNonJoue_,nombreDeJoueurs_));
                    for(byte joueur_: GameTarotTeamsRelation.autresJoueurs(joueurs_,nombreDeJoueurs_)) {
                        if (!(atoutsJoueurs_.get(joueur_).estVide())) {
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
                    int nbAtoutsMaitres_ = atoutsMaitres_.total();
                    if(atoutsMaitres_.contient(CardTarot.petit())) {
                        nbAtoutsMaitres_--;
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
        }
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        if(currentHand.total() == atouts_ .total() + repartition_.getVal(CardTarot.excuse().couleur()).total()) {
            //il n'y a que de l'atout (ev excuse)
            if (!contientExcuse_) {
                return jeuAtoutOffensif(currentHand, cartesJouees_);
            }
            if(atouts_.total() + repartition_.getVal(CardTarot.excuse().couleur()).total() == 2) {
                if(carteAppeleeJouee_ && aucunPliAdverse(teamsRelation,_numero,plisFaits_)) {
                    return atouts_.premiereCarte();
                }
                return CardTarot.excuse();
            }
            return jeuAtoutOffensif(currentHand, cartesJouees_);
        }
        if(currentHand.total() == atouts_.total() + repartition_.getVal(CardTarot.excuse().couleur()).total() + 1) {
            //une seule carte de couleur est presente
            EnumList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, Suit.couleursOrdinaires());
            CardTarot carteCouleur_ = repartition_.getVal(couleurs_.first()).premiereCarte();
            Numbers<Byte> partenaires_ = GameTarotTrickHypothesis.joueursPouvantCouperCouleurs(currentHand, joueursConfiance_,
                    cartesPossibles_, couleurs_);
            partenaires_ = GameTarotTrickHypothesis.joueursPossedantAtoutMaitre(partenaires_, cartesCertaines_,
                    cartesJouees_);
            if(!partenaires_.isEmpty()) {
                return carteCouleur_;
            }
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
                if (atouts_.contient(CardTarot.petit())) {
                    return jeuAtoutSuperieurPetit(atouts_);
                }
                atouts_.trierParForceEnCours(Suit.TRUMP);
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
                    return jeuAtoutSuperieurPetit(atouts_);
                }
                if(!GameTarotTrickHypothesis.joueursPossedantAtoutMaitre(joueursConfiance_,
                        cartesCertaines_, cartesJouees_).isEmpty()) {
                    atouts_.trierParForceEnCours(Suit.TRUMP);
                    return atouts_.derniereCarte();
                }
            }
            return carteCouleur_;
        }
        boolean aucuneCoupe_ = true;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            boolean plusCartesCouleurAutres_ = true;
            Numbers<Byte> joueurs_ = new Numbers<Byte>(GameTarotTeamsRelation.autresJoueurs(joueursNonJoue_,nombreDeJoueurs_));
            for(byte joueur_: GameTarotTeamsRelation.autresJoueurs(joueurs_, nombreDeJoueurs_)) {
                if(cartesPossibles_.getVal(couleur_).get(joueur_).estVide()) {
                    continue;
                }
                plusCartesCouleurAutres_ = false;
                break;
            }
            if(plusCartesCouleurAutres_) {
                continue;
            }
            if(!repartition_.getVal(couleur_).estVide()) {
                continue;
            }
            aucuneCoupe_ = false;
            break;
        }
        boolean touteCouleurPossedeCarteMaitresse_ = true;
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if(repartition_.getVal(couleur_).estVide()) {
                continue;
            }
            if(!cartesMaitresses_.getVal(couleur_).estVide()) {
                continue;
            }
            touteCouleurPossedeCarteMaitresse_ = false;
            break;
        }
        boolean atoutsTousJoues_ = false;
        if(atouts_.total() + repartitionCartesJouees_.getVal(Suit.TRUMP).total() == HandTarot.atoutsSansExcuse().total()) {
            atoutsTousJoues_ = true;
        }
        if(atoutsTousJoues_) {
            EnumList<Suit> notEmptySuits_ = GameTarotCommon.couleursNonAtoutNonVides(currentHand, couleursMaitres_);
            if(cartesNonMaitresses_.total() == 1) {
                if(!notEmptySuits_.isEmpty()) {
                    return jeuAvecCarteMaitresseSansAtout(currentHand, cartesJouees_, notEmptySuits_);
                }
                return cartesNonMaitresses_.premiereCarte();
            }
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
        }
        if(!GameTarotTrickHypothesis.joueursPouvantPossederPetit(joueursConfiance_,
                cartesPossibles_).isEmpty()) {
            int nbAtoutsMaitres_ = atoutsMaitres_.total();
            if(nbAtoutsMaitres_ > 0) {
                atoutsMaitres_.trierParForceEnCours(Suit.TRUMP);
                return atoutsMaitres_.premiereCarte();
            }
        }
        if(!GameTarotTrickHypothesis.joueursPossedantAtoutMaitre(joueursConfiance_,
                cartesCertaines_, cartesJouees_).isEmpty()) {
            if(atouts_.contient(CardTarot.petit())) {
                return CardTarot.petit();
            }
        }
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
        //differents cas de jeu
        if(_numero == teamsRelation.getTaker() || !teamsRelation.existePreneur()) {
            return playAsTaker(currentHand, _cartesJouables, repartition_,
                    cartesJouees_, atoutsJoues_, plisFaits_,
                    carteAppeleeJouee_, cartesPossibles_, cartesCertaines_,
                    couleursAppelees_, joueursNonJoue_, joueursConfiance_,
                    joueursNonConfiance_, nombreDeJoueurs_, atouts_,
                    aucuneCoupe_, touteCouleurPossedeCarteMaitresse_);
        }
        if(teamsRelation.statutDe(_numero) == Status.CALLED_PLAYER) {
            return playAsCalledPlayer(_numero, currentHand, _cartesJouables,
                    repartition_, cartesJouees_, atoutsJoues_, plisFaits_,
                    carteAppeleeJouee_, cartesPossibles_, cartesCertaines_,
                    cartesChien_, joueursNonJoue_, joueursNonConfiance_,
                    nombreDeJoueurs_, touteCouleurPossedeCarteMaitresse_);
        }
        EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
        if(!carteAppeleeJouee_) {
            couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
        }
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(_cartesJouables, couleursAjouer_);
        //Defenseur
        for(Suit c: couleursAppelees_) {
            if(!carteAppeleeJouee_ && !_cartesJouables.couleur(c).estVide()) {
                if(!common.appeleConnuDefenseur(teamsRelation,_numero,cartesPossibles_)) {
                    boolean defausseTousJoueurs_ = true;
                    EqList<HandTarot> atoutsJoueurs_ = cartesPossibles_.getVal(Suit.TRUMP);
                    for(byte joueur_: joueursNonConfiance_) {
                        if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                            defausseTousJoueurs_ = false;
                        }
                    }
                    if(!defausseTousJoueurs_) {
                        if(GameTarotCommon.couleursAvecFigures(_cartesJouables,Suit.couleursOrdinaires()).containsObj(c)) {
                            if(couleursCoupeeParJoueurs(_cartesJouables,
                                    joueursNonConfiance_, cartesPossibles_,
                                    cartesCertaines_, Suit.couleursOrdinaires()).containsObj(c)) {
                                return repartition_.getVal(c).premiereCarte();
                            }
                        }
                    }
                    if(GameTarotCommon.couleursAvecCartesBasses(_cartesJouables,Suit.couleursOrdinaires()).containsObj(c)) {
                        return repartition_.getVal(c).derniereCarte();
                    }
                }
            }
        }

        //ouvreur
        if(defenseOuvreur(teamsRelation,_numero, cartesPossibles_)) {
            if(defenseOuvreurStrict(teamsRelation,_numero, cartesPossibles_)) {
                EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(_cartesJouables,
                        plisFaits_, couleursNonVidesAjouer_);
                couleurs_ = GameTarotCommon.couleursSansFigures(_cartesJouables, couleurs_);
                if(!couleurs_.isEmpty()) {
                    couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusBasses(_cartesJouables, couleurs_);
                    return repartition_.getVal(couleurs_.first()).derniereCarte();
                }
            }
            EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(_cartesJouables,
                    plisFaits_, couleursNonVidesAjouer_);
            couleurs_ = GameTarotCommon.couleursAvecCartesBasses(_cartesJouables, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(_cartesJouables, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(_cartesJouables,
                    plisFaits_, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(_cartesJouables, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursAvecCartesBasses(_cartesJouables, couleursNonVidesAjouer_);
            couleurs_ = couleursNonOuvertesAttaque(_cartesJouables, plisFaits_,
                    joueursNonConfiance_, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(_cartesJouables, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = couleursNonOuvertesAttaque(_cartesJouables, plisFaits_,
                    joueursNonConfiance_, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(_cartesJouables, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            if(!couleursNonVidesAjouer_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(_cartesJouables, couleursNonVidesAjouer_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            if(!atouts_.estVide()) {
                if (atouts_.contient(CardTarot.petit())) {
                    return jeuAtoutSuperieurPetit(atouts_);
                }
                atouts_.trierParForceEnCours(Suit.TRUMP);
                return atouts_.derniereCarte();
            }
            if(currentHand.contient(CardTarot.EXCUSE)) {
                return CardTarot.EXCUSE;
            }
            couleurs_ = Suit.couleursOrdinaires();
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
            //couleur appelee
        }
        EnumList<Suit> couleurs_ = couleursOuvertes(_cartesJouables,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(_cartesJouables, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommonPlaying.couleursLesMoinsEntameesParJoueurs(plisFaits_, joueursNonConfiance_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(_cartesJouables, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(_cartesJouables,
                plisFaits_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommonPlaying.couleursLesMoinsEntameesParJoueurs(plisFaits_, joueursNonConfiance_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_cartesJouables, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        if(!couleursNonVidesAjouer_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_cartesJouables, couleursNonVidesAjouer_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_cartesJouables, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(cartesJouees_, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }

        if(!atouts_.estVide()) {
            if (atouts_.contient(CardTarot.petit())) {
                return jeuAtoutSuperieurPetit(atouts_);
            }
            atouts_.trierParForceEnCours(Suit.TRUMP);
            return atouts_.derniereCarte();
        }
        if(currentHand.contient(CardTarot.EXCUSE)) {
            return CardTarot.EXCUSE;
        }
        couleurs_ = Suit.couleursOrdinaires();
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand, couleurs_);
        return repartition_.getVal(couleurs_.first()).derniereCarte();
        //couleur appelee

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
     @param _sureCards
     @param _dog
     @param _playersNoPlayed
     @param _noConfidentPlayers
     @param _nbPlayers
     @param _allSuitsWithLeadCard
     @param _nbPlayersTwo
     @return
     */
    private CardTarot playAsCalledPlayer(byte _player, HandTarot _hand,
                                         HandTarot _playableCards, EnumMap<Suit,HandTarot> _dealing,
                                         HandTarot _playedCards, HandTarot _playedTrumps,
                                         CustList<TrickTarot> _tricks, boolean _playedCalledCard,
                                         EnumMap<Suit,EqList<HandTarot>> _possibleCards,
                                         EnumMap<Suit,EqList<HandTarot>> _sureCards, HandTarot _dog,
                                         Numbers<Byte> _playersNoPlayed, Numbers<Byte> _noConfidentPlayers,
                                         byte _nbPlayers, boolean _allSuitsWithLeadCard) {
        if(_playersNoPlayed.containsObj(teamsRelation.getTaker())) {
            boolean jouerUneCouleurAppelee_ = false;
            HandTarot cartesPossedees_ = new HandTarot();
            HandTarot cartesNonPossedees_ = new HandTarot();
            for(CardTarot c: calledCards) {
                if(_hand.contient(c)) {
                    cartesPossedees_.ajouter(c);
                    continue;
                }
                cartesNonPossedees_.ajouter(c);
            }
            if(!cartesPossedees_.estVide()) {
                boolean defausseTousJoueurs_ = true;
                EqList<HandTarot> atoutsJoueurs_ = _possibleCards.getVal(Suit.TRUMP);
                for(byte joueur_: _noConfidentPlayers) {
                    if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                        defausseTousJoueurs_ = false;
                    }
                }
                if(defausseTousJoueurs_) {
                    jouerUneCouleurAppelee_ = true;
                }
                if(couleursNonAppelees(Suit.couleursOrdinaires()).isEmpty() && !jouerAtout(_hand, _playedTrumps,
                        _possibleCards, _nbPlayers)) {
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
                    EnumList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(_hand, couleursCartesNonPossedees_);
                    if(!couleurs_.isEmpty()) {
                        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
                        couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
                        return _dealing.getVal(couleurs_.first()).derniereCarte();
                    }
                } else {
                    return cartesPossedees_.premiereCarte();
                }
            }
        }
        EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
        if(!_playedCalledCard) {
            couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
        }
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(_playableCards, couleursAjouer_);
        EnumList<Suit> couleurs_;
        if(GameTarotTeamsRelation.justeApresJoueur(_player, teamsRelation.getTaker(), _nbPlayers) && _playersNoPlayed.containsObj(teamsRelation.getTaker())) {
            couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(_hand,
                    _tricks, couleursNonVidesAjouer_);
            couleurs_ = GameTarotCommon.couleursSansFigures(_hand, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(_hand,
                    _tricks, couleursNonVidesAjouer_);
            couleurs_ = couleursSansCarteMaitresse(_hand, _playedCards, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
            if(jouerAtout(_hand, _playedTrumps,
                    _possibleCards, _nbPlayers) && _allSuitsWithLeadCard) {
                return jeuAtoutOffensif(_hand, _playedCards);
            }
            couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(_hand,
                    _tricks, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).premiereCarte();
            }
            couleurs_ = couleursCoupeePar(_hand,
                    teamsRelation.getTaker(), _possibleCards,
                    _sureCards, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                if(_possibleCards.getVal(Suit.TRUMP).get(teamsRelation.getTaker()).contient(CardTarot.petit())) {
                    couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_playedCards, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_hand, couleurs_);
                    couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
                    return _dealing.getVal(couleurs_.first()).derniereCarte();
                }
            }
            Numbers<Byte> partenaires_ = new Numbers<Byte>();
            partenaires_.add(teamsRelation.getTaker());
            couleurs_ = GameTarotCommonPlaying.couleursLesPlusEntameesParJoueurs(_tricks, partenaires_, couleursNonVidesAjouer_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            if(!couleurs_.isEmpty()) {
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
            HandTarot cartesPossedees_ = new HandTarot();
            for(CardTarot c: calledCards) {
                if(_hand.contient(c)) {
                    continue;
                }
                cartesPossedees_.ajouter(c);
            }
            if(!cartesPossedees_.estVide()) {
                return cartesPossedees_.premiereCarte();
            }
        }
        Numbers<Byte> defenseurs_ = joueursAvantAppeleApresPreneur(GameTarotTeamsRelation.tousJoueurs(_nbPlayers),_player);
        boolean defausseTousDefenseursIntermediaire_ = true;
        EqList<HandTarot> atoutsJoueurs_ = _possibleCards.getVal(Suit.TRUMP);
        for(byte joueur_: defenseurs_) {
            if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                defausseTousDefenseursIntermediaire_ = false;
            }
        }
        if(defausseTousDefenseursIntermediaire_
                && _possibleCards.getVal(Suit.TRUMP).get(teamsRelation.getTaker()).contient(CardTarot.petit())
                && _playersNoPlayed.containsObj(teamsRelation.getTaker())) {
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_playedCards, couleursNonVidesAjouer_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        Numbers<Byte> partenaires_ = new Numbers<Byte>();
        partenaires_.add(teamsRelation.getTaker());
        if(GameTarotTeamsRelation.apresJoueur(_player, teamsRelation.getTaker(), _nbPlayers) && _playersNoPlayed.containsObj(teamsRelation.getTaker())) {
            couleurs_ = GameTarotCommonPlaying.couleursLesPlusEntameesParJoueurs(_tricks, partenaires_, couleursNonVidesAjouer_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusCourtes(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            if(!couleurs_.isEmpty()) {
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
            HandTarot cartesPossedees_ = new HandTarot();
            for(CardTarot c: calledCards) {
                if(_hand.contient(c)) {
                    continue;
                }
                cartesPossedees_.ajouter(c);
            }
            if(!cartesPossedees_.estVide()) {
                return cartesPossedees_.premiereCarte();
            }
        }
        couleurs_ = couleursEntameesParJoueurs(_tricks, partenaires_, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(_hand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursEntameesParJoueurs(_tricks, partenaires_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        if(jouerAtout(_hand, _playedTrumps,
                _possibleCards, _nbPlayers) && _allSuitsWithLeadCard) {
            return jeuAtoutOffensif(_hand, _playedCards);
        }
        couleurs_ = couleursOuvertes(_hand,
                _tricks, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(_hand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(_hand,
                _tricks, couleursNonVidesAjouer_);
        couleurs_ = couleursSansCarteMaitresse(_hand, _playedCards, couleurs_);
        couleurs_ = couleursCoupeePar(_hand,
                teamsRelation.getTaker(), _possibleCards,
                _sureCards, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(_hand,
                _tricks, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            boolean defausseTousJoueurs_ = true;
            for(byte joueur_: _noConfidentPlayers) {
                if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                    defausseTousJoueurs_ = false;
                }
            }
            if(defausseTousJoueurs_) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusHautes(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).premiereCarte();
            }
            EnumList<Suit> couleursCoupeesAdv_ = couleursCoupeeParJoueurs(_hand,
                    _noConfidentPlayers, _possibleCards,
                    _sureCards, couleurs_);
            if(!couleursCoupeesAdv_.isEmpty()) {
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleursCoupeesAdv_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursAvecCartesMaitressesVuesChien(_hand, _playedCards,
                _dog, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursNonVidesAjouer_;
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        HandTarot cartesPossedees_ = new HandTarot();
        for(CardTarot c: calledCards) {
            if(_hand.contient(c)) {
                continue;
            }
            cartesPossedees_.ajouter(c);
        }
        if(!cartesPossedees_.estVide()) {
            return cartesPossedees_.premiereCarte();
        }
        couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(_hand, Suit.couleursOrdinaires());
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
        couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
        return _dealing.getVal(couleurs_.first()).derniereCarte();
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
    private CardTarot playAsTaker(HandTarot _hand, HandTarot _playableCards,
                                  EnumMap<Suit,HandTarot> _dealing, HandTarot _playedCards,
                                  HandTarot _playedTrumps, CustList<TrickTarot> _tricks,
                                  boolean _playedCalledCard,
                                  EnumMap<Suit,EqList<HandTarot>> _possibleCards,
                                  EnumMap<Suit,EqList<HandTarot>> _sureCards, EnumList<Suit> _calledSuits,
                                  Numbers<Byte> _playerNoPlay, Numbers<Byte> _confidentPlayers,
                                  Numbers<Byte> _noConfidentPlayers, byte _nbPlayers,
                                  HandTarot _trumpCards, boolean _noPossibleTrump,
                                  boolean _allSuitsWithLeadCard) {
        for(Suit c: _calledSuits) {
            if(!_playedCalledCard && !_playableCards.couleur(c).estVide()) {
                //jouer la couleur appelee dans certains cas
                boolean defausseTousJoueurs_ = true;
                EqList<HandTarot> atoutsJoueurs_ = _possibleCards.getVal(Suit.TRUMP);
                for(byte joueur_: _noConfidentPlayers) {
                    if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                        defausseTousJoueurs_ = false;
                    }
                }
                if(defausseTousJoueurs_ && _allSuitsWithLeadCard) {
                    return _dealing.getVal(c).derniereCarte();
                }
                EnumList<Suit> couleurs_ = couleursNonAppelees(Suit.couleursOrdinaires());
                couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(_hand,
                        _tricks, couleurs_);
                if(couleurs_.isEmpty()) {
                    if(_trumpCards.estVide() || !jouerAtout(_hand, _playedTrumps,
                            _possibleCards, _nbPlayers)) {
                        return _dealing.getVal(c).derniereCarte();
                    }
                }
            }
        }
        EnumList<Suit> couleursAjouer_ = Suit.couleursOrdinaires();
        if(!_playedCalledCard) {
            couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
        }
        EnumList<Suit> couleursNonVidesAjouer_ = GameTarotCommon.couleursNonAtoutNonVides(_playableCards, couleursAjouer_);
        EnumList<Suit> couleurs_ = couleursOuvertes(_hand,
                _tricks, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommon.couleursSansFigures(_hand, couleurs_);
        if(!couleurs_.isEmpty()) {
            Numbers<Byte> joueurs_ = new Numbers<Byte>(GameTarotTeamsRelation.autresJoueurs(_playerNoPlay,_nbPlayers));
            EnumList<Suit> couleursPossiblementCoupees_ = GameTarotTrickHypothesis.couleursPouvantEtreCoupees(_hand,
                    GameTarotTeamsRelation.autresJoueurs(joueurs_,_nbPlayers),
                    _possibleCards, couleurs_);
            if(!couleursPossiblementCoupees_.isEmpty()) {
                couleursPossiblementCoupees_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleursPossiblementCoupees_);
                couleursPossiblementCoupees_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleursPossiblementCoupees_);
                return _dealing.getVal(couleursPossiblementCoupees_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(_hand,
                _tricks, couleursNonVidesAjouer_);
        couleurs_ = couleursSansCarteMaitresse(_hand, _playedCards, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        if(jouerAtout(_hand, _playedTrumps,
                _possibleCards, _nbPlayers)) {
            if(_allSuitsWithLeadCard) {
                return jeuAtoutOffensif(_hand, _playedCards);
            }
            if(!couleursNonVidesAjouer_.isEmpty()) {
                couleurs_ = couleursSansCarteMaitresse(_hand, _playedCards, couleursNonVidesAjouer_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(_hand,
                _tricks, couleursNonVidesAjouer_);
        couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(_hand, _playedCards, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusHautes(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = GameTarotCommonPlaying.couleursNonOuvertesNonVides(_hand,
                _tricks, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        if(_noPossibleTrump && !_trumpCards.contient(CardTarot.petit()) && !_trumpCards.estVide()) {
            return jeuAtoutOffensif(_hand, _playedCards);
        }
        couleurs_ = GameTarotCommon.couleursAvecCartesBasses(_hand,
                couleursNonVidesAjouer_);
        couleurs_ = couleursCoupeeParJoueurs(_hand,
                _noConfidentPlayers, _possibleCards,
                _sureCards, couleurs_);
        if(!couleurs_.isEmpty()) {
            EnumList<Suit> couleursNonCoupeesJoueursConfiance_ = couleursNonCoupeeParJoueurs(_hand,
                    _confidentPlayers, _possibleCards,
                    _sureCards, couleurs_);
            if(!couleursNonCoupeesJoueursConfiance_.isEmpty()) {
                couleursNonCoupeesJoueursConfiance_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleursNonCoupeesJoueursConfiance_);
                couleursNonCoupeesJoueursConfiance_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleursNonCoupeesJoueursConfiance_);
                couleursNonCoupeesJoueursConfiance_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleursNonCoupeesJoueursConfiance_);
                return _dealing.getVal(couleursNonCoupeesJoueursConfiance_.first()).derniereCarte();
            }
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursNonVidesAjouer_;
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = GameTarotCommon.couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).premiereCarte();
        }
        if(!_trumpCards.estVide()) {
            if (_trumpCards.contient(CardTarot.petit())) {
                return jeuAtoutSuperieurPetit(_trumpCards);
            }
            _trumpCards.trierParForceEnCours(Suit.TRUMP);
            return _trumpCards.derniereCarte();
        }
        if(_hand.contient(CardTarot.EXCUSE)) {
            return CardTarot.EXCUSE;
        }
        couleurs_ = Suit.couleursOrdinaires();
        couleurs_ = GameTarotCommon.couleursLesPlusLongues(_hand, couleurs_);
        return _dealing.getVal(couleurs_.first()).derniereCarte();
        //couleur appelee
    }
    private CardTarot playWithStrongestHand(GameTarotTeamsRelation _teamRel, byte _player, HandTarot _hand,
                                            HandTarot _playedCards, CustList<TrickTarot> _tricks,
                                            boolean _containsExcuse, boolean _playedCalledCard) {
        /*
        Cas ou le joueur entameur
        deborde les autres joueurs en
        couleurs et en atout
        */
        byte nbPlayers_ = _teamRel.getNombreDeJoueurs();
        if (teamsRelation.getTaker() == _player || !teamsRelation.existePreneur()) {
            //Preneur
            if (_containsExcuse) {
                if (_playedCalledCard) {
                    if(aucunPliAdverse(_teamRel,_player,_tricks)) {
                        return jeuMainMaitresse(_hand,_playedCards);
                    }
                    return CardTarot.excuse();
                }
                if(!_hand.contientCartes(calledCards)) {
                    if(plisTousFaitsParPreneurJoueur(teamsRelation.getTaker(),_tricks,nbPlayers_)) {
                        return jeuMainMaitresse(_hand,_playedCards);
                    }
                    return CardTarot.excuse();
                }
                Numbers<Byte> joueursRamasseurs_ = new Numbers<Byte>();
                joueursRamasseurs_.add(teamsRelation.getTaker());
                if(GameTarotTrickInfo.plisTousFaitsPar(joueursRamasseurs_,_tricks,nbPlayers_)) {
                    return jeuMainMaitresse(_hand,_playedCards);
                }
                return CardTarot.excuse();
            }
            return jeuMainMaitresse(_hand,_playedCards);
        }
        if(_teamRel.statutDe(_player) == Status.CALLED_PLAYER) {
            //Appele
            //existeAppele
            if (_containsExcuse) {
                if(aucunPliAdverse(_teamRel,_player,_tricks)) {
                    return jeuMainMaitresse(_hand,_playedCards);
                }
                return CardTarot.excuse();
            }
            return jeuMainMaitresse(_hand,_playedCards);
        }
        if (_containsExcuse) {
            Numbers<Byte> joueursConfianceNumero_ = new Numbers<Byte>(_teamRel.joueursConfiance(_player,GameTarotTeamsRelation.tousJoueurs(nbPlayers_)));
            joueursConfianceNumero_.add(_player);
            if(GameTarotTrickInfo.plisTousFaitsPar(joueursConfianceNumero_,_tricks,nbPlayers_)) {
                return jeuMainMaitresse(_hand,_playedCards);
            }
            if(teamsRelation.confiance(_player, common.playerAfter(_player))) {
                return jeuMainMaitresse(_hand,_playedCards);
            }
            return CardTarot.excuse();
        }
        return jeuMainMaitresse(_hand,_playedCards);
    }

    boolean defenseOuvreur(GameTarotTeamsRelation _teamRel, byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        Numbers<Byte> attaque_ = new Numbers<Byte>();
        attaque_.add(teamsRelation.getTaker());
        if (common.appeleConnuDefenseur(_teamRel,_joueur,_cartesPossibles) && teamsRelation.existeAppele()) {
            for (byte a: teamsRelation.getCalledPlayers()) {
                if (!attaque_.containsObj(a)) {
                    attaque_.add(a);
                }
            }
        }
        if (attaque_.containsObj(_joueur)) {
            return false;
        }
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        boolean justeAvantAttaque_ = false;
        byte nextPlayer_ = common.playerAfter(_joueur);
        if (nextPlayer_ == teamsRelation.getTaker()) {
            justeAvantAttaque_ = true;
        }
        if (teamsRelation.existeAppele() && !teamsRelation.getCalledPlayers().containsObj(nextPlayer_)) {
            justeAvantAttaque_ = true;
        }
        if (!justeAvantAttaque_) {
            return false;
        }
        return GameTarotTeamsRelation.justeApresJoueur(_joueur, teamsRelation.getTaker(), nombreDeJoueurs_);
    }

    boolean defenseOuvreurStrict(GameTarotTeamsRelation _teamRel, byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        if (!defenseOuvreur(_teamRel,_joueur, _cartesPossibles)) {
            return false;
        }
        if (common.appeleConnuDefenseur(_teamRel,_joueur,_cartesPossibles) && teamsRelation.existeAppele() && !teamsRelation.getCalledPlayers().containsObj(teamsRelation.getTaker())) {
            Numbers<Byte> attaque_ = new Numbers<Byte>();
            attaque_.add(teamsRelation.getTaker());
            for (byte a: teamsRelation.getCalledPlayers()) {
                if (!attaque_.containsObj(a)) {
                    attaque_.add(a);
                }
            }
            int nbJoueursAvantEtApres_ = 0;
            int nbJoueursAvant_ = 0;
            int nbJoueursApres_ = 0;
            byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
            for (byte a: attaque_) {
                boolean existeJoueurJusteAvant_ = false;
                boolean existeJoueurJusteApres_ = false;
                for (byte a2_: attaque_) {
                    if (a == a2_) {
                        continue;
                    }
                    if(GameTarotTeamsRelation.justeApresJoueur(a, a2_, nombreDeJoueurs_)) {
                        existeJoueurJusteAvant_ = true;
                    }
                    if(GameTarotTeamsRelation.justeApresJoueur(a2_, a, nombreDeJoueurs_)) {
                        existeJoueurJusteApres_ = true;
                    }
                }
                if (existeJoueurJusteAvant_) {
                    if (existeJoueurJusteApres_ ) {
                        nbJoueursAvantEtApres_++;
                    } else {
                        nbJoueursAvant_++;
                    }
                } else if (existeJoueurJusteApres_) {
                    nbJoueursApres_++;
                }
            }
            return nbJoueursAvant_== 1 && nbJoueursApres_ == 1 && nbJoueursAvantEtApres_ == attaque_.size() - 2;
        }
        return false;
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
    static CardTarot jeuMainMaitresse(HandTarot currentHand,HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
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
        EnumList<Suit> couleurs_ = GameTarotCommonPlaying.couleursAvecCarteMaitresse(currentHand,
                _cartesJouees, Suit.couleursOrdinaires());
        if(!couleurs_.isEmpty()) {
            couleurs_ = GameTarotCommon.couleursLesPlusLongues(currentHand,couleurs_);
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
                if(couleurs_.containsObj(couleur_)) {
                    continue;
                }
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }
    static boolean jouerAtout(HandTarot _main, HandTarot _atoutsJoues,
                              EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _nombreJoueurs) {
        EqList<HandTarot> repartitionAtout_ = _cartesPossibles
                .getVal(Suit.TRUMP);
        byte nombreDefausses_ = 0;
        for (HandTarot main_ : repartitionAtout_) {
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
            return _main.couleur(Suit.TRUMP).total() * nombreJoueursAvecAtout_ >= atoutsNonJoues_.total();
        }
        return _main.couleur(Suit.TRUMP).total() * nombreJoueursAvecAtout_ >= 2 * atoutsNonJoues_
                .total();
    }
    static CardTarot jeuAtoutOffensif(HandTarot currentHand,HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> repartition_ = currentHand.couleurs();
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
    static CardTarot jeuAtoutSuperieurPetit(HandTarot _atouts) {
        for(CardTarot carte_: _atouts) {
            if(CardTarot.eq(carte_, CardTarot.petit())) {
                continue;
            }
            return carte_;
        }
        return _atouts.premiereCarte();
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
        EnumList<Suit> toutesCouleursOrdinaires_ = Suit.couleursOrdinaires();
        for (TrickTarot pli_ : _plis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (couleurDemandee_ == Suit.TRUMP) {
                continue;
            }
            if (toutesCouleursOrdinaires_.containsObj(couleurDemandee_)) {
                couleursOuvertes_.add(couleurDemandee_);
            }
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
            if (couleursMains_.getVal(couleur_).estVide()) {
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
                if(couleursCoupees_.containsObj(couleur_)) {
                    continue;
                }
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
                                                             CustList<TrickTarot> _plis, Numbers<Byte> _attaquants,
                                                             EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        EnumList<Suit> toutesCouleursOrdinaires_ = Suit.couleursOrdinaires();
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
            if (toutesCouleursOrdinaires_.containsObj(couleurDemandee_)) {
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
                                                    byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
                                                    EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for (Suit couleur_ : _couleurs) {
            if (!_cartesPossibles.getVal(couleur_).get(_joueur).estVide()) {
                continue;
            }
            if (_cartesCertaines.getVal(Suit.TRUMP).get(_joueur).estVide()) {
                continue;
            }
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            couleurs_.add(couleur_);
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
    static boolean aucunPliAdverse(GameTarotTeamsRelation _t, byte _joueur, CustList<TrickTarot> _unionPlis) {
        byte nombreDeJoueurs_ = _t.getNombreDeJoueurs();
        Numbers<Byte> partenaires_ = _t.coequipiers(_joueur,
                GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_));
        partenaires_.add(_joueur);
        return GameTarotTrickInfo.plisTousFaitsPar(partenaires_, _unionPlis, nombreDeJoueurs_);
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
