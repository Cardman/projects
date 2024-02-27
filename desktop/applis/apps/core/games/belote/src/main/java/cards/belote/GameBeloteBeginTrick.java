package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.Role;
import cards.consts.Suit;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;

public final class GameBeloteBeginTrick {

    private final HandBelote currentHand;
    private final Role currentStatus;
    private final HandBelote playableCards;
    private final BidBeloteSuit bid;
    private final byte taker;
    private final HandBelote lastSeenHand;
    private final BeloteInfoPliEnCours beloteInfoPliEnCours;
    private final CustList<TrickBelote> plisFaits;
    private final Bytes adversaire;
    private final Bytes partenaire;
    private final IdMap<Suit, HandBelote> repartitionCartesJouees;
    private final IdList<Suit> couleursMaitresses;
    private final IdMap<Suit, HandBelote> cartesMaitresses;
    private final IdMap<Suit, CustList<HandBelote>> suitesTouteCouleur;
    private final IdMap<Suit, CustList<HandBelote>> cartesPossibles;
    private final IdMap<Suit, CustList<HandBelote>> cartesCertaines;
    private final HandBelote cartesJouees;
    private final boolean maitreAtout;
    private final boolean maitreJeu;
    private final IdMap<Suit,HandBelote> repartition;
    private final IdList<Suit> couleursNonAtouts;
    private final IdList<Suit> couleursNonAtoutNonVides;
    private final IdList<Suit> couleursNonVides;
    private final int offset;
    private final IdList<Suit> couleursMaitressesAvecPoints;

    public GameBeloteBeginTrick(GameBeloteTrickInfo _done, GameBeloteTeamsRelation _teamsRelation, HandBelote _currentHand) {
        currentHand = _currentHand;
        lastSeenHand = _done.getLastSeenHand();
        bid = _done.getBid();
        taker = _teamsRelation.getTaker();
        GameBeloteCommonPlaying common_ = new GameBeloteCommonPlaying(_done, _teamsRelation);
        byte nbPlayers_ = _teamsRelation.getNombreDeJoueurs();
        TrickBelote trBelote_ = _done.getProgressingTrick();
        byte nextPlayer_ = trBelote_.getNextPlayer(nbPlayers_);
        repartition= currentHand.couleurs(bid);
        playableCards = common_.playableCards(repartition);
        currentStatus = _teamsRelation.statutDe(nextPlayer_);
        beloteInfoPliEnCours = common_.initInformations(currentHand);
        plisFaits = beloteInfoPliEnCours.getPlisFaits();
        cartesJouees = beloteInfoPliEnCours.getCartesJouees();
        adversaire = beloteInfoPliEnCours.getJoueursNonConfiance();
        partenaire=beloteInfoPliEnCours.getJoueursConfiance();
        repartitionCartesJouees = beloteInfoPliEnCours.getRepartitionCartesJouees();
        couleursMaitresses = beloteInfoPliEnCours.getCouleursMaitresses();
        cartesMaitresses = beloteInfoPliEnCours.getCartesMaitresses();
        suitesTouteCouleur = beloteInfoPliEnCours.getSuitesTouteCouleur();
        cartesPossibles = beloteInfoPliEnCours.getCartesPossibles();
        cartesCertaines = beloteInfoPliEnCours.getCartesCertaines();
        maitreAtout = beloteInfoPliEnCours.isMaitreAtout();
        maitreJeu = beloteInfoPliEnCours.isMaitreJeu();
        couleursNonAtouts = common_.couleursNonAtouts();
        couleursNonAtoutNonVides = GameBeloteCommon.couleursNonAtoutNonVides(currentHand, couleursNonAtouts);
        couleursNonVides = GameBeloteCommon.couleursNonAtoutNonVides(currentHand, GameBeloteCommon.couleurs());
        offset = RulesBelote.offset(_teamsRelation.getRules());
        IdList<Suit> couleursMaitressesAvec_ = GameBeloteCommon.couleursAvecCarteMaitresse(currentHand, cartesJouees, bid, couleursNonAtoutNonVides);
        couleursMaitressesAvecPoints = GameBeloteCommon.couleursAvecPoints(currentHand, bid, couleursMaitressesAvec_);
    }
    CardBelote entame() {
        if(playableCards.total()==1) {
            return playableCards.premiereCarte();
        }
        if(bid.getCouleurDominante()) {
            return entameCouleurDominante();
        }
        return entameSansAtoutToutAtout();
    }

    BeloteInfoPliEnCours initInformations() {
        return beloteInfoPliEnCours;
    }

    CardBelote entameCouleurDominante() {
        if(maitreJeu) {
            return playBestCardsDom();
        }
        if(GameBeloteCommon.hand(repartition, bid.getSuit()).total()==currentHand.total()) {
            return playWhenOnlyTrumps();

        }
        if(GameBeloteCommon.hand(repartition, bid.getSuit()).total()+1==currentHand.total()) {
            return playWhenAtMostOneNormalSuit();

        }
        if(currentStatus == Role.TAKER) {
            return playAsTakerDom();
        }
        //Appele
        if(currentStatus == Role.CALLED_PLAYER) {
            return playAsCalledPlayerDom();
        }
        return playAsDefenderDom();
    }

    CardBelote playBestCardsDom() {
        return jeuMainMaitresseCouleurDominante(
        );
    }

    CardBelote playAsDefenderDom() {
        Suit couleurAtout_= bid.getSuit();
        IdList<Suit> couleurs_= couleursNonOuvertesNonVides(couleursNonAtoutNonVides);
        /*Cas d'un defenseur*/
        couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(cartesMaitresses,couleurs_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(adversaire, cartesPossibles, couleurAtout_, couleursNonAtoutNonVides);
        if(!couleurs_.isEmpty()) {
            return faireCouper(couleurs_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire, bid, cartesPossibles, couleursNonAtoutNonVides);
        couleurs_ = GameBeloteCommonPlaying.couleursNonCoupeeParJoueurs(adversaire, bid, cartesPossibles, cartesCertaines, couleurs_);

        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_);
        }
        return faireCouper(couleursNonAtoutNonVides);
    }

    private IdList<Suit> couleursNonOuvertesNonVides(IdList<Suit> _couleurs) {
        return GameBeloteCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits.mid(offset), _couleurs);
    }

    CardBelote playAsCalledPlayerDom() {
        Suit couleurAtout_= bid.getSuit();

        if (!GameBeloteCommon.hand(repartition, couleurAtout_).estVide() && playedLeading(bid, taker, couleurAtout_, repartitionCartesJouees, cartesCertaines)) {
            return GameBeloteCommon.hand(repartition, couleurAtout_).derniereCarte();
        }
        if (!lastSeenHand.estVide()) {
            CardBelote carteDessus_=lastSeenHand.premiereCarte();
            Suit couleurDessus_= carteDessus_.getId().getCouleur();

            if (couleurDessus_ != couleurAtout_
                    && playedLeading(bid, taker, couleurDessus_, repartitionCartesJouees, cartesCertaines) && !GameBeloteCommon.hand(repartition, couleurDessus_).estVide()) {
                return GameBeloteCommon.hand(repartition, couleurDessus_).premiereCarte();
            }
        }


        IdList<Suit> couleurs_= couleursNonOuvertesNonVides(couleursNonAtouts);
        couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(cartesMaitresses,couleurs_);
        /*On considere que l'appele est place apres le preneur*/
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire, cartesPossibles, couleurAtout_, couleursNonAtoutNonVides);
        couleurs_ = GameBeloteCommon.couleursAvecPoints(currentHand, bid, couleurs_);
        if(!couleurs_.isEmpty()) {
            return faireCouperPreneurFigure(couleurs_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire, cartesPossibles, couleurAtout_, couleursNonAtoutNonVides);
        couleurs_ = GameBeloteCommon.couleursAvecNbPointsInfEg(currentHand, bid, couleurs_, 4);
        if(!couleurs_.isEmpty()) {
            return faireCouper(couleurs_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire, bid, cartesPossibles, couleursNonAtoutNonVides);
        couleurs_ = GameBeloteCommonPlaying.couleursNonCoupeeParJoueurs(adversaire, bid, cartesPossibles, cartesCertaines, couleurs_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(adversaire, cartesPossibles, couleurAtout_, couleursNonAtoutNonVides);
        if(!couleurs_.isEmpty()) {
            return faireCouper(couleurs_);
        }
        return faireCouper(couleursNonAtoutNonVides);
    }

    static boolean playedLeading(BidBeloteSuit _bid, byte _taker, Suit _couleurAtout, IdMap<Suit, HandBelote> _repartitionCartesJouees,
                                 IdMap<Suit, CustList<HandBelote>> _cartesCertaines) {
        if (GameBeloteCommon.hand(_cartesCertaines, _couleurAtout, _taker).estVide()) {
            return false;
        }
        boolean cartesMaitressesJouees_ = true;
        CardBelote carteCertaine_ = GameBeloteCommon.hand(_cartesCertaines, _couleurAtout, _taker).premiereCarte();
        for (CardBelote c : HandBelote.couleurComplete(_couleurAtout, _bid)) {
            if (c.strength(_couleurAtout, _bid)
                    <= carteCertaine_.strength(_couleurAtout, _bid) || _repartitionCartesJouees.getVal(_couleurAtout).contient(c)) {
                continue;
            }
            cartesMaitressesJouees_ = false;
        }
        return cartesMaitressesJouees_;
    }

    CardBelote playAsTakerDom() {
        Suit couleurAtout_= bid.getSuit();
        if (!couleursMaitressesAvecPoints.containsAllObj(couleursNonAtoutNonVides)) {
            return notAll();
        }
        if (GameBeloteTrickHypothesis.pasAtoutJoueurs(adversaire, cartesPossibles, couleurAtout_)) {
            return GameBeloteCommonPlaying.carteMaitresse(bid, couleursMaitressesAvecPoints, cartesMaitresses, currentHand, cartesJouees);
        }
        return playAsTakerDom(maitreAtout);
    }

    CardBelote playAsTakerDom(boolean _maitreAtout) {
        Suit couleurAtout_= bid.getSuit();
        if (!GameBeloteCommon.hand(repartition, couleurAtout_).estVide()&& _maitreAtout) {
            return GameBeloteCommon.hand(repartition, couleurAtout_).premiereCarte();
        }
        if (!cartesMaitresses.getVal(couleurAtout_).estVide()) {
            return cartesMaitresses.getVal(couleurAtout_).premiereCarte();
        }
        if (!repartition.getVal(couleurAtout_).estVide() && repartition.getVal(couleurAtout_).total() == suitesTouteCouleur.getVal(couleurAtout_).first().total()) {
            return repartition.getVal(couleurAtout_).derniereCarte();
        }
        IdList<Suit> couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(adversaire, cartesPossibles, couleurAtout_, couleursMaitressesAvecPoints);
        if (!couleurs_.isEmpty()) {
            return faireCouper(couleurs_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire, cartesPossibles, couleurAtout_, couleursMaitressesAvecPoints);
        if (!couleurs_.isEmpty()) {
            return faireCouperAppele(couleurs_, repartition, repartitionCartesJouees);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursOuvertes(plisFaits.mid(offset), couleursMaitressesAvecPoints);
        if (!couleurs_.isEmpty()) {
            return ouvrirCouleur(couleurs_);
        }
        return ouvrirCouleur(couleursMaitressesAvecPoints);
    }

    private CardBelote ouvrirCouleur(IdList<Suit> _couleurs) {
        return ouvrirCouleur(bid, _couleurs, repartition);
    }

    private CardBelote notAll() {
        Suit couleurAtout_= bid.getSuit();
        if (!couleursMaitressesAvecPoints.isEmpty()) {
            if (!cartesMaitresses.getVal(couleurAtout_).estVide()) {
                return cartesMaitresses.getVal(couleurAtout_).premiereCarte();
            }
            if (!repartition.getVal(couleurAtout_).estVide() && repartition.getVal(couleurAtout_).total() == suitesTouteCouleur.getVal(couleurAtout_).first().total()) {
                return repartition.getVal(couleurAtout_).derniereCarte();
            }
        }
        return faireCouper(couleursNonAtoutNonVides);
    }

    CardBelote playWhenAtMostOneNormalSuit() {
        Suit couleurAtout_= bid.getSuit();
        /*On cherche la couleur autre que l'atout non vide*/
        Suit couleurNonAtout_ = couleursNonAtoutNonVides.first();
        if(!GameBeloteCommon.hand(cartesMaitresses,couleurNonAtout_).estVide()) {
            if(GameBeloteCommon.hand(repartition,couleurNonAtout_).premiereCarte().points(bid)==0) {
                return GameBeloteCommon.hand(repartition,couleurNonAtout_).premiereCarte();
            }
            if(currentHand.total()==2) {
                return GameBeloteCommon.hand(repartition,couleurNonAtout_).premiereCarte();
            }
            if(GameBeloteCommon.hand(cartesMaitresses,couleurAtout_).estVide()) {
                return GameBeloteCommon.hand(repartition,couleurAtout_).derniereCarte();
            }
            return GameBeloteCommon.hand(repartition,couleurAtout_).premiereCarte();
        }
        IdList<Suit> couleursPouvantEtreCoupees_ =
                GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire, cartesPossibles, couleurAtout_, couleursNonAtoutNonVides);
        if(couleursPouvantEtreCoupees_.containsAllObj(couleursNonAtoutNonVides)) {
            return GameBeloteCommon.hand(repartition,couleurNonAtout_).premiereCarte();
        }
        if(maitreAtout) {
            return GameBeloteCommon.hand(repartition,couleurAtout_).premiereCarte();
        }
        return GameBeloteCommon.hand(repartition,couleurNonAtout_).premiereCarte();
    }

    CardBelote playWhenOnlyTrumps() {
        Suit couleurAtout_= bid.getSuit();
        /*Si le joueur ne possede que de l'atout*/
        if(GameBeloteCommon.suite(suitesTouteCouleur,couleurAtout_).size()==1) {
            return GameBeloteCommon.hand(repartition,couleurAtout_).derniereCarte();
        }
        if(GameBeloteCommon.hand(cartesMaitresses,couleurAtout_).estVide()) {
            return GameBeloteCommon.hand(repartition,couleurAtout_).derniereCarte();
        }
        return GameBeloteCommon.hand(repartition,couleurAtout_).premiereCarte();
    }

    CardBelote entameSansAtoutToutAtout() {
        /*Jeu sans atout ou tout atout*/
        if(maitreJeu) {
            return playBestCards();
        }
        if(currentStatus == Role.TAKER) {
            return playAsTaker();
        }
        if(currentStatus == Role.CALLED_PLAYER) {
            return playAsCalledPlayer();
        }
        return playAsDefender();
    }

    CardBelote playBestCards() {
        return jeuMainMaitresseCouleursEgales();
    }

    CardBelote playAsDefender() {
        IdList<Suit> couleurs_;
        /*Cas d'un defenseur*/
        couleurs_ = couleursNonOuvertesNonVides(couleursNonVides);
        couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(cartesMaitresses,couleurs_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire, bid, cartesPossibles, couleursNonVides);
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_);
        }
        return faireCouper(couleursNonVides);
    }

    CardBelote playAsCalledPlayer() {
        IdList<Suit> couleurs_;
        if (!lastSeenHand.estVide()) {
            Suit couleurDessus_= lastSeenHand.premiereCarte().getId().getCouleur();
            if (playedLeading(bid, taker, couleurDessus_, repartitionCartesJouees, cartesCertaines) && !GameBeloteCommon.hand(repartition, couleurDessus_).estVide()) {
                return GameBeloteCommon.hand(repartition, couleurDessus_).premiereCarte();
            }
        }
        /*On considere que l'appele est place apres le preneur*/
        couleurs_ = couleursNonOuvertesNonVides(couleursNonVides);
        couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(cartesMaitresses,couleurs_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire, bid, cartesPossibles, couleursNonVides);
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_);
        }
        return faireCouper(couleursNonVides);
    }

    private CardBelote ouvrir(IdList<Suit> _couleurs) {
        return ouvrir(bid, _couleurs, repartition, repartitionCartesJouees, cartesMaitresses);
    }

    CardBelote playAsTaker() {
//        IdList<Suit> couleursMaitressesAvecPoints_ = GameBeloteCommon.couleursAvecCarteMaitresse(currentHand, cartesJouees, bid, couleursNonVides);
//        couleursMaitressesAvecPoints_ = GameBeloteCommon.couleursAvecPoints(currentHand, bid, couleursMaitressesAvecPoints_);
        if(couleursMaitressesAvecPoints.containsAllObj(couleursNonVides)) {
            /*Il existe une carte de couleur autre que l'atout*/
            return GameBeloteCommonPlaying.carteMaitresse(bid, couleursMaitressesAvecPoints, cartesMaitresses, currentHand, cartesJouees);
        }
        return faireCouper(couleursNonVides);
    }

    private CardBelote faireCouper(IdList<Suit> _couleurs) {
        return faireCouper(bid, _couleurs, repartition, repartitionCartesJouees);
    }

    private CardBelote jeuMainMaitresseCouleurDominante() {
        if(!GameBeloteCommon.suite(suitesTouteCouleur,bid.getSuit()).isEmpty()) {
            return GameBeloteCommon.hand(suitesTouteCouleur,bid.getSuit(),0).premiereCarte();
        }
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(repartition, couleursMaitresses);
        return GameBeloteCommon.hand(repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote jeuMainMaitresseCouleursEgales() {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(repartition, couleursMaitresses);
        return GameBeloteCommon.hand(repartition,couleurs_.first()).premiereCarte();
    }
    public static byte nombreCartesPoints(IdMap<Suit,HandBelote> _repartition,BidBeloteSuit _contrat,Suit _couleur) {
        return _repartition.getVal(_couleur).nombreCartesPoints(_contrat);
    }

    private CardBelote faireCouperPreneurFigure(IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(repartition, bid, couleurs_);
        return GameBeloteCommon.hand(repartition,couleurs_.first()).premiereCarte();
    }
    static CardBelote ouvrir(BidBeloteSuit _bid, IdList<Suit> _couleurs,
                                     IdMap<Suit, HandBelote> _repartition,
                                     IdMap<Suit, HandBelote> _repartitionCartesJouees,
                                     IdMap<Suit, HandBelote> _cartesMaitresses) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_cartesMaitresses, _couleurs);
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
                                            IdList<Suit> _couleurs, IdMap<Suit, HandBelote> _repartition) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, _bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }

    static CardBelote faireCouper(BidBeloteSuit _bid, IdList<Suit> _couleurs,
                                          IdMap<Suit, HandBelote> _repartition,
                                          IdMap<Suit, HandBelote> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartitionCartesJouees, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, _bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    static CardBelote faireCouperAppele(
            IdList<Suit> _couleurs,IdMap<Suit,HandBelote> _repartition,
            IdMap<Suit,HandBelote> _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }

    Role getCurrentStatus() {
        return currentStatus;
    }
}
