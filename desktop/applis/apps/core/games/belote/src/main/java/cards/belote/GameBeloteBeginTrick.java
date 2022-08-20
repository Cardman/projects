package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Role;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumList;
import code.util.IdMap;
import code.util.*;

public final class GameBeloteBeginTrick {

    private final HandBelote currentHand;
    private final GameBeloteCommonPlaying common;
    private final Role currentStatus;
    private final HandBelote playableCards;
    private final BidBeloteSuit bid;
    private final byte taker;
    private final HandBelote lastSeenHand;

    public GameBeloteBeginTrick(GameBeloteTrickInfo _done, GameBeloteTeamsRelation _teamsRelation, HandBelote _currentHand) {
        currentHand = _currentHand;
        lastSeenHand = _done.getLastSeenHand();
        bid = _done.getBid();
        taker = _teamsRelation.getTaker();
        common = new GameBeloteCommonPlaying(_done,_teamsRelation);
        byte nbPlayers_ = _teamsRelation.getNombreDeJoueurs();
        TrickBelote trBelote_ = _done.getProgressingTrick();
        byte nextPlayer_ = trBelote_.getNextPlayer(nbPlayers_);
        playableCards = common.playableCards(currentHand.couleurs(bid));
        currentStatus = _teamsRelation.statutDe(nextPlayer_);
    }
    CardBelote entame() {
        if(playableCards.total()==1) {
            return playableCards.premiereCarte();
        }
        BeloteInfoPliEnCours info_ = initInformations();
        if(bid.getCouleurDominante()) {
            return entameCouleurDominante(info_);
        }
        return entameSansAtoutToutAtout(info_);
    }

    BeloteInfoPliEnCours initInformations() {
        return common.initInformations(currentHand);
    }

    CardBelote entameCouleurDominante(BeloteInfoPliEnCours _info) {
        if(_info.isMaitreJeu()) {
            return playBestCardsDom(_info);
        }
        if(GameBeloteCommon.hand(currentHand.couleurs(bid), bid.getSuit()).total()==currentHand.total()) {
            return playWhenOnlyTrumps(_info);

        }
        if(GameBeloteCommon.hand(currentHand.couleurs(bid), bid.getSuit()).total()+1==currentHand.total()) {
            return playWhenAtMostOneNormalSuit(_info);

        }
        if(currentStatus == Role.TAKER) {
            return playAsTakerDom(_info);
        }
        //Appele
        if(currentStatus == Role.CALLED_PLAYER) {
            return playAsCalledPlayerDom(_info);
        }
        return playAsDefenderDom(_info);
    }

    CardBelote playBestCardsDom(BeloteInfoPliEnCours _info) {
        IdMap<Suit,CustList<HandBelote>> suites_= _info.getSuitesTouteCouleur();
        EnumList<Suit> couleursMaitres_= _info.getCouleursMaitresses();
        return jeuMainMaitresseCouleurDominante(suites_,
                currentHand.couleurs(bid),
                couleursMaitres_,
                bid.getSuit());
    }

    CardBelote playAsDefenderDom(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        Suit couleurAtout_= bid.getSuit();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        IdMap<Suit,HandBelote> repartitionCartesJouees_= _info.getRepartitionCartesJouees();
        CustList<TrickBelote> plisFaits_= _info.getPlisFaits();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        IdMap<Suit,HandBelote> cartesMaitresses_= _info.getCartesMaitresses();
        EnumList<Suit> couleursNonAtouts_=common.couleursNonAtouts();
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(currentHand, couleursNonAtouts_);
        EnumList<Suit> couleurs_=GameBeloteCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleursNonVides_);
        /*Cas d'un defenseur*/
        couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(cartesMaitresses_,couleurs_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(bid, couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(adversaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return faireCouper(bid, couleurs_,repartition_,repartitionCartesJouees_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
        couleurs_ = GameBeloteCommonPlaying.couleursNonCoupeeParJoueurs(adversaire_, bid, cartesPossibles_, cartesCertaines_, couleurs_);

        if(!couleurs_.isEmpty()) {
            return ouvrir(bid, couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
        }
        return faireCouper(bid, couleursNonVides_,repartition_,repartitionCartesJouees_);
    }

    CardBelote playAsCalledPlayerDom(BeloteInfoPliEnCours _info) {
        EnumList<Suit> couleursNonAtouts_=common.couleursNonAtouts();
        Suit couleurAtout_= bid.getSuit();
        IdMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        IdMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        CustList<TrickBelote> plisFaits_=_info.getPlisFaits();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_=_info.getCartesCertaines();
        IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        Bytes partenaire_=_info.getJoueursConfiance();
        Bytes adversaire_ =_info.getJoueursNonConfiance();

        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(currentHand, couleursNonAtouts_);
        if (!GameBeloteCommon.hand(repartition_, couleurAtout_).estVide() && playedLeading(bid, taker, couleurAtout_, repartitionCartesJouees_, cartesCertaines_, Order.TRUMP)) {
            return GameBeloteCommon.hand(repartition_, couleurAtout_).derniereCarte();
        }
        if (!lastSeenHand.estVide()) {
            CardBelote carteDessus_=lastSeenHand.premiereCarte();
            Suit couleurDessus_= carteDessus_.getId().getCouleur();

            if (couleurDessus_ != couleurAtout_
                    && playedLeading(bid, taker, couleurDessus_, repartitionCartesJouees_, cartesCertaines_, Order.SUIT) && !GameBeloteCommon.hand(repartition_, couleurDessus_).estVide()) {
                return GameBeloteCommon.hand(repartition_, couleurDessus_).premiereCarte();
            }
        }


        EnumList<Suit> couleurs_=GameBeloteCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleursNonAtouts_);
        couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(cartesMaitresses_,couleurs_);
        /*On considere que l'appele est place apres le preneur*/
        if(!couleurs_.isEmpty()) {
            return ouvrir(bid, couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
        couleurs_ = GameBeloteCommon.couleursAvecPoints(currentHand, bid, couleurs_);
        if(!couleurs_.isEmpty()) {
            return faireCouperPreneurFigure(bid, couleurs_,repartition_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
        couleurs_ = GameBeloteCommon.couleursAvecNbPointsInfEg(currentHand, bid, couleurs_, 4);
        if(!couleurs_.isEmpty()) {
            return faireCouper(bid, couleurs_,repartition_,repartitionCartesJouees_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
        couleurs_ = GameBeloteCommonPlaying.couleursNonCoupeeParJoueurs(adversaire_, bid, cartesPossibles_, cartesCertaines_, couleurs_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(bid, couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(adversaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return faireCouper(bid, couleurs_,repartition_,repartitionCartesJouees_);
        }
        return faireCouper(bid, couleursNonVides_,repartition_,repartitionCartesJouees_);
    }

    static boolean playedLeading(BidBeloteSuit _bid, byte _taker, Suit _couleurAtout, IdMap<Suit, HandBelote> _repartitionCartesJouees,
                                 IdMap<Suit, CustList<HandBelote>> _cartesCertaines, Order _or) {
        if (GameBeloteCommon.hand(_cartesCertaines, _couleurAtout, _taker).estVide()) {
            return false;
        }
        boolean cartesMaitressesJouees_ = true;
        CardBelote carteCertaine_ = GameBeloteCommon.hand(_cartesCertaines, _couleurAtout, _taker).premiereCarte();
        for (CardBelote c : HandBelote.couleurComplete(_couleurAtout, _or)) {
            if (c.strength(_couleurAtout, _bid)
                    <= carteCertaine_.strength(_couleurAtout, _bid) || _repartitionCartesJouees.getVal(_couleurAtout).contient(c)) {
                continue;
            }
            cartesMaitressesJouees_ = false;
        }
        return cartesMaitressesJouees_;
    }

    CardBelote playAsTakerDom(BeloteInfoPliEnCours _info) {
        Suit couleurAtout_= bid.getSuit();
        IdMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        HandBelote cartesJouees_=_info.getCartesJouees();
        IdMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        CustList<TrickBelote> plisFaits_=_info.getPlisFaits();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        boolean strictMaitreAtout_;
        IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        IdMap<Suit, CustList<HandBelote>> suitesTouteCouleur_ = _info.getSuitesTouteCouleur();
        Bytes partenaire_=_info.getJoueursConfiance();
        Bytes adversaire_ =_info.getJoueursNonConfiance();

        strictMaitreAtout_=_info.isMaitreAtout();
        EnumList<Suit> couleursNonAtouts_=common.couleursNonAtouts();
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(currentHand, couleursNonAtouts_);
        EnumList<Suit> couleursMaitressesAvecPoints_ = GameBeloteCommon.couleursAvecCarteMaitresse(currentHand, cartesJouees_, bid, couleursNonVides_);
        couleursMaitressesAvecPoints_ = GameBeloteCommon.couleursAvecPoints(currentHand, bid, couleursMaitressesAvecPoints_);
        if (!couleursMaitressesAvecPoints_.containsAllObj(couleursNonVides_)) {
            return notAll(_info, repartition_, couleursNonVides_, couleursMaitressesAvecPoints_);
        }
        if (GameBeloteTrickHypothesis.pasAtoutJoueurs(adversaire_, cartesPossibles_, couleurAtout_)) {
            return GameBeloteCommonPlaying.carteMaitresse(bid, couleursMaitressesAvecPoints_, cartesMaitresses_, currentHand, cartesJouees_);
        }
        if (strictMaitreAtout_) {
            return GameBeloteCommon.hand(repartition_, couleurAtout_).premiereCarte();
        }
        if (!cartesMaitresses_.getVal(couleurAtout_).estVide()) {
            return cartesMaitresses_.getVal(couleurAtout_).premiereCarte();
        }
        if (!repartition_.getVal(couleurAtout_).estVide() && repartition_.getVal(couleurAtout_).total() == suitesTouteCouleur_.getVal(couleurAtout_).first().total()) {
            return repartition_.getVal(couleurAtout_).derniereCarte();
        }
        EnumList<Suit> couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(adversaire_, cartesPossibles_, couleurAtout_, couleursMaitressesAvecPoints_);
        if (!couleurs_.isEmpty()) {
            return faireCouper(bid, couleurs_, repartition_, repartitionCartesJouees_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursMaitressesAvecPoints_);
        if (!couleurs_.isEmpty()) {
            return faireCouperAppele(couleurs_, repartition_, repartitionCartesJouees_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursOuvertes(plisFaits_, couleursMaitressesAvecPoints_);
        if (!couleurs_.isEmpty()) {
            return ouvrirCouleur(bid, couleurs_, repartition_);
        }
        return ouvrirCouleur(bid, couleursMaitressesAvecPoints_, repartition_);
    }

    private CardBelote notAll(BeloteInfoPliEnCours _info, IdMap<Suit, HandBelote> _repartition, EnumList<Suit> _couleursNonVides, EnumList<Suit> _couleursMaitressesAvecPoints) {
        IdMap<Suit,HandBelote> repartitionCartesJouees_=_info.getRepartitionCartesJouees();
        IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        IdMap<Suit, CustList<HandBelote>> suitesTouteCouleur_ = _info.getSuitesTouteCouleur();
        Suit couleurAtout_= bid.getSuit();
        if (!_couleursMaitressesAvecPoints.isEmpty()) {
            if (!cartesMaitresses_.getVal(couleurAtout_).estVide()) {
                return cartesMaitresses_.getVal(couleurAtout_).premiereCarte();
            }
            if (!_repartition.getVal(couleurAtout_).estVide() && _repartition.getVal(couleurAtout_).total() == suitesTouteCouleur_.getVal(couleurAtout_).first().total()) {
                return _repartition.getVal(couleurAtout_).derniereCarte();
            }
        }
        return faireCouper(bid, _couleursNonVides, _repartition, repartitionCartesJouees_);
    }

    CardBelote playWhenAtMostOneNormalSuit(BeloteInfoPliEnCours _info) {
        Suit couleurAtout_= bid.getSuit();
        IdMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_=_info.getCartesPossibles();
        boolean strictMaitreAtout_;
        IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        Bytes partenaire_=_info.getJoueursConfiance();

        strictMaitreAtout_=_info.isMaitreAtout();
        EnumList<Suit> couleursNonAtouts_=common.couleursNonAtouts();
        /*On cherche la couleur autre que l'atout non vide*/
        EnumList<Suit> couleursNonAtoutNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(repartition_, couleursNonAtouts_);
        Suit couleurNonAtout_ = couleursNonAtoutNonVides_.first();
        if(!GameBeloteCommon.hand(cartesMaitresses_,couleurNonAtout_).estVide()) {
            if(GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte().points(bid)==0) {
                return GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte();
            }
            if(currentHand.total()==2) {
                return GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte();
            }
            if(GameBeloteCommon.hand(cartesMaitresses_,couleurAtout_).estVide()) {
                return GameBeloteCommon.hand(repartition_,couleurAtout_).derniereCarte();
            }
            return GameBeloteCommon.hand(repartition_,couleurAtout_).premiereCarte();
        }
        EnumList<Suit> couleursPouvantEtreCoupees_ =
                GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonAtoutNonVides_);
        if(couleursPouvantEtreCoupees_.containsAllObj(couleursNonAtoutNonVides_)) {
            return GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte();
        }
        if(strictMaitreAtout_) {
            return GameBeloteCommon.hand(repartition_,couleurAtout_).premiereCarte();
        }
        return GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte();
    }

    CardBelote playWhenOnlyTrumps(BeloteInfoPliEnCours _info) {
        Suit couleurAtout_= bid.getSuit();
        IdMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        IdMap<Suit,CustList<HandBelote>> suites_=_info.getSuitesTouteCouleur();
        IdMap<Suit,HandBelote> cartesMaitresses_=_info.getCartesMaitresses();
        /*Si le joueur ne possede que de l'atout*/
        if(GameBeloteCommon.suite(suites_,couleurAtout_).size()==1) {
            return GameBeloteCommon.hand(repartition_,couleurAtout_).derniereCarte();
        }
        if(GameBeloteCommon.hand(cartesMaitresses_,couleurAtout_).estVide()) {
            return GameBeloteCommon.hand(repartition_,couleurAtout_).derniereCarte();
        }
        return GameBeloteCommon.hand(repartition_,couleurAtout_).premiereCarte();
    }

    CardBelote entameSansAtoutToutAtout(BeloteInfoPliEnCours _info) {
        /*Jeu sans atout ou tout atout*/
        if(_info.isMaitreJeu()) {
            return playBestCards(_info);
        }
        if(currentStatus == Role.TAKER) {
            return playAsTaker(_info);
        }
        if(currentStatus == Role.CALLED_PLAYER) {
            return playAsCalledPlayer(_info);
        }
        return playAsDefender(_info);
    }

    CardBelote playBestCards(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumList<Suit> couleursMaitres_= _info.getCouleursMaitresses();
        return jeuMainMaitresseCouleursEgales(repartition_,couleursMaitres_);
    }

    CardBelote playAsDefender(BeloteInfoPliEnCours _info) {
        EnumList<Suit> couleurs_;
        IdMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        IdMap<Suit,HandBelote> repartitionCartesJouees_= _info.getRepartitionCartesJouees();
        CustList<TrickBelote> plisFaits_= _info.getPlisFaits();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        IdMap<Suit,HandBelote> cartesMaitresses_= _info.getCartesMaitresses();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        EnumList<Suit> couleursNonAtouts_=GameBeloteCommon.couleurs();
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(currentHand, couleursNonAtouts_);
        /*Cas d'un defenseur*/
        couleurs_ = GameBeloteCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleursNonVides_);
        couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(cartesMaitresses_,couleurs_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(bid, couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(bid, couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
        }
        return faireCouper(bid, couleursNonVides_,repartition_,repartitionCartesJouees_);
    }

    CardBelote playAsCalledPlayer(BeloteInfoPliEnCours _info) {
        EnumList<Suit> couleurs_;
        IdMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        IdMap<Suit,HandBelote> repartitionCartesJouees_= _info.getRepartitionCartesJouees();
        CustList<TrickBelote> plisFaits_= _info.getPlisFaits();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_= _info.getCartesPossibles();
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_= _info.getCartesCertaines();
        IdMap<Suit,HandBelote> cartesMaitresses_= _info.getCartesMaitresses();
        Bytes adversaire_ = _info.getJoueursNonConfiance();
        EnumList<Suit> couleursNonAtouts_=GameBeloteCommon.couleurs();
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(currentHand, couleursNonAtouts_);
        if (!lastSeenHand.estVide()) {
            Suit couleurDessus_= lastSeenHand.premiereCarte().getId().getCouleur();
            if (playedLeading(bid, taker, couleurDessus_, repartitionCartesJouees_, cartesCertaines_, bid.getOrdre()) && !GameBeloteCommon.hand(repartition_, couleurDessus_).estVide()) {
                return GameBeloteCommon.hand(repartition_, couleurDessus_).premiereCarte();
            }
        }
        /*On considere que l'appele est place apres le preneur*/
        couleurs_ = GameBeloteCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleursNonVides_);
        couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(cartesMaitresses_,couleurs_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(bid, couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(bid, couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
        }
        return faireCouper(bid, couleursNonVides_,repartition_,repartitionCartesJouees_);
    }

    CardBelote playAsTaker(BeloteInfoPliEnCours _info) {
        IdMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        HandBelote cartesJouees_= _info.getCartesJouees();
        IdMap<Suit,HandBelote> repartitionCartesJouees_= _info.getRepartitionCartesJouees();
        IdMap<Suit,HandBelote> cartesMaitresses_= _info.getCartesMaitresses();
        EnumList<Suit> couleursNonAtouts_=GameBeloteCommon.couleurs();
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(currentHand, couleursNonAtouts_);
        EnumList<Suit> couleursMaitressesAvecPoints_ = GameBeloteCommon.couleursAvecCarteMaitresse(currentHand, cartesJouees_, bid, couleursNonVides_);
        couleursMaitressesAvecPoints_ = GameBeloteCommon.couleursAvecPoints(currentHand, bid, couleursMaitressesAvecPoints_);
        if(couleursMaitressesAvecPoints_.containsAllObj(couleursNonVides_)) {
            /*Il existe une carte de couleur autre que l'atout*/
            return GameBeloteCommonPlaying.carteMaitresse(bid, couleursMaitressesAvecPoints_, cartesMaitresses_, currentHand, cartesJouees_);
        }
        return faireCouper(bid, couleursNonVides_, repartition_, repartitionCartesJouees_);
    }

    private static CardBelote jeuMainMaitresseCouleurDominante(
            IdMap<Suit,CustList<HandBelote>> _suites,
            IdMap<Suit,HandBelote> _repartition,
            EnumList<Suit> _couleursMaitres,
            Suit _couleurAtout) {
        if(!GameBeloteCommon.suite(_suites,_couleurAtout).isEmpty()) {
            return GameBeloteCommon.hand(_suites,_couleurAtout,0).premiereCarte();
        }
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(_repartition, _couleursMaitres);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    private static CardBelote jeuMainMaitresseCouleursEgales(
            IdMap<Suit,HandBelote> _repartition,
            EnumList<Suit> _couleursMaitres) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(_repartition, _couleursMaitres);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    public static byte nombreCartesPoints(IdMap<Suit,HandBelote> _repartition,BidBeloteSuit _contrat,Suit _couleur) {
        return _repartition.getVal(_couleur).nombreCartesPoints(_contrat);
    }

    private static CardBelote faireCouperPreneurFigure(BidBeloteSuit _bid,
                                                       EnumList<Suit> _couleurs, IdMap<Suit, HandBelote> _repartition) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, _bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, _bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    static CardBelote ouvrir(BidBeloteSuit _bid, EnumList<Suit> _couleurs,
                                     IdMap<Suit, HandBelote> _repartition,
                                     IdMap<Suit, HandBelote> _repartitionCartesJouees,
                                     IdMap<Suit, HandBelote> _cartesMaitresses) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_cartesMaitresses, _couleurs);
        //maitre
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, couleurs_);
        //jouees
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, couleurs_);
        //longues
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, _bid, couleurs_);
        //basses
        if(!GameBeloteCommon.hand(_cartesMaitresses,couleurs_.first()).estVide()) {
            return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
        }
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    static CardBelote ouvrirCouleur(BidBeloteSuit _bid,
                                            EnumList<Suit> _couleurs, IdMap<Suit, HandBelote> _repartition) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, _bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }

    static CardBelote faireCouper(BidBeloteSuit _bid, EnumList<Suit> _couleurs,
                                          IdMap<Suit, HandBelote> _repartition,
                                          IdMap<Suit, HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartitionCartesJouees, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, _bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    static CardBelote faireCouperAppele(
            EnumList<Suit> _couleurs,IdMap<Suit,HandBelote> _repartition,
            IdMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }

    Role getCurrentStatus() {
        return currentStatus;
    }
}
