package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.Order;
import cards.consts.Status;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.Numbers;

public final class GameBeloteBeginTrick {
    private GameBeloteTeamsRelation teamsRelation;
    private GameBeloteTrickInfo info;

    private HandBelote currentHand;
    private GameBeloteCommonPlaying common;
    private Status currentStatus;
    private HandBelote playableCards;
    private BidBeloteSuit bid;
    private byte taker;
    private HandBelote lastSeenHand;

    public GameBeloteBeginTrick(GameBeloteTrickInfo _done, GameBeloteTeamsRelation _teamsRelation, HandBelote _currentHand) {
        teamsRelation = _teamsRelation;
        currentHand = _currentHand;
        info = _done;
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
        if(bid.getCouleurDominante()) {
            return entameCouleurDominante();
        }
        return entameSansAtoutToutAtout();
    }
    CardBelote entameCouleurDominante() {
        byte next_ = info.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        Suit couleurAtout_=bid.getCouleur();
        BeloteInfoPliEnCours info_ = common.initInformations(currentHand);
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        HandBelote cartesJouees_=info_.getCartesJouees();
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        boolean strictMaitreAtout_;
        EnumList<Suit> couleursMaitres_=info_.getCouleursMaitresses();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> partenaire_=info_.getJoueursConfiance();
        Numbers<Byte> adversaire_ =info_.getJoueursNonConfiance();
        boolean maitreJeu_;

        strictMaitreAtout_=info_.isMaitreAtout();
        maitreJeu_=info_.isMaitreJeu();
        EnumList<Suit> couleursNonAtouts_=common.couleursNonAtouts();
        if(maitreJeu_) {
            return jeuMainMaitresseCouleurDominante(suites_,
                    repartition_,
                    couleursMaitres_,
                    couleurAtout_);
        }
        if(GameBeloteCommon.hand(repartition_,couleurAtout_).total()==currentHand.total()) {
            /*Si le joueur ne possede que de l'atout*/
            if(GameBeloteCommon.suite(suites_,couleurAtout_).size()==1) {
                if(GameBeloteCommon.hand(suites_,couleurAtout_,0).total()==GameBeloteCommon.hand(cartesMaitresses_,couleurAtout_).total()) {
                    /*Si le joueur n'a que des atouts maitres*/
                    return GameBeloteCommon.hand(repartition_,couleurAtout_).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurAtout_).derniereCarte();
            }
            if(GameBeloteCommon.hand(cartesMaitresses_,couleurAtout_).estVide()) {
                return GameBeloteCommon.hand(repartition_,couleurAtout_).derniereCarte();
            }
            if(currentHand.total()<=2*GameBeloteCommon.hand(cartesMaitresses_,couleurAtout_).total()) {
                /*La GameBeloteCommon.main du joueur contient plus d atouts maitres que des atouts non maitres*/
                return GameBeloteCommon.hand(suites_,couleurAtout_,0).premiereCarte();
            }
            return GameBeloteCommon.hand(repartition_,couleurAtout_).derniereCarte();
        }
        if(GameBeloteCommon.hand(repartition_,couleurAtout_).total()+1==currentHand.total()) {
            /*On cherche la couleur autre que l'atout non vide*/
            EnumList<Suit> couleursNonAtoutNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(repartition_, couleursNonAtouts_);
            Suit couleurNonAtout_ = couleursNonAtoutNonVides_.first();
            if(!GameBeloteCommon.hand(cartesMaitresses_,couleurNonAtout_).estVide()) {
                if(GameBeloteCommon.hand(suites_,couleurAtout_,0).total()==GameBeloteCommon.hand(cartesMaitresses_,couleurAtout_).total()) {
                    /*Si le joueur n'a que des atouts maitres*/
                    return GameBeloteCommon.hand(repartition_,couleurAtout_).premiereCarte();
                }
                if(GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte().points(bid)==0) {
                    return GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte();
                }
                if(currentHand.total()==2) {
                    return GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte();
                }
                if(GameBeloteCommon.hand(cartesMaitresses_,couleurAtout_).estVide()) {
                    return GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurAtout_).premiereCarte();
            }
            EnumList<Suit> couleursPouvantEtreCoupees_ =
                    GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonAtoutNonVides_);
            if(GameBeloteCommonPlaying.egaliteCouleurs(couleursPouvantEtreCoupees_, couleursNonAtoutNonVides_)) {
                return GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte();
            }
            if(strictMaitreAtout_) {
                return GameBeloteCommon.hand(repartition_,couleurAtout_).premiereCarte();
            }
            if(GameBeloteCommon.hand(repartition_,couleurAtout_).total()==GameBeloteCommon.hand(cartesMaitresses_,couleurAtout_).total()) {
                Numbers<Byte> joueursPouvantCouper_ = GameBeloteCommonPlaying.joueursPouvantCouperCouleurs(currentHand, adversaire_, bid, cartesPossibles_, couleursNonAtoutNonVides_);
                if(GameBeloteTeamsRelation.egaliteJoueurs(joueursPouvantCouper_,adversaire_)) {
                    return GameBeloteCommon.hand(repartition_,couleurAtout_).premiereCarte();
                }
            }
            return GameBeloteCommon.hand(repartition_,couleurNonAtout_).premiereCarte();
        }
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(currentHand, couleursNonAtouts_);
        if(currentStatus == Status.TAKER) {
            EnumList<Suit> couleursMaitressesAvecPoints_ = GameBeloteCommon.couleursAvecCarteMaitresse(currentHand, cartesJouees_, bid, couleursNonVides_);
            couleursMaitressesAvecPoints_ = GameBeloteCommon.couleursAvecPoints(currentHand, bid, couleursMaitressesAvecPoints_);
            if(GameBeloteCommonPlaying.egaliteCouleurs(couleursMaitressesAvecPoints_, couleursNonVides_)) {
                if(GameBeloteTrickHypothesis.pasAtoutJoueurs(adversaire_, cartesPossibles_, couleurAtout_)) {
                    if(!couleursNonVides_.isEmpty()) {
                        /*Si il existe une carte de couleur autre que l'atout*/
                        return common.carteMaitresse(couleursNonVides_, cartesMaitresses_, currentHand, cartesJouees_, cartesPossibles_, next_, suites_);
                    }
                }
                if(strictMaitreAtout_) {
                    return GameBeloteCommon.hand(repartition_,couleurAtout_).premiereCarte();
                }
                EnumList<Suit> couleurs_=GameBeloteCommonPlaying.couleursPouvantEtreCoupees(adversaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
                if(!couleurs_.isEmpty()) {
                    return faireCouperAdv(couleurs_,repartition_,repartitionCartesJouees_);
                }
                couleurs_=GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
                if(!couleurs_.isEmpty()) {
                    return faireCouperAppele(couleurs_,repartition_,repartitionCartesJouees_);
                }
                couleurs_ = GameBeloteCommonPlaying.couleursOuvertes(plisFaits_, couleursNonVides_);
                if(!couleurs_.isEmpty()) {
                    return ouvrirCouleur(couleurs_,repartition_);
                }
                return ouvrirCouleur(couleursNonVides_,repartition_);
            }
            EnumList<Suit> couleurs_=GameBeloteCommonPlaying.couleursSansCarteMaitresse(currentHand, cartesJouees_, bid, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return faireCouperAdv(couleurs_, repartition_, repartitionCartesJouees_);
            }
            return faireCouperAdv(couleursNonVides_, repartition_, repartitionCartesJouees_);
        }
        //Appele
        if(currentStatus == Status.CALLED_PLAYER) {
            if(!GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,taker).estVide()) {
                if(!GameBeloteCommon.hand(repartition_,couleurAtout_).estVide()) {
                    boolean cartesMaitressesJouees_ = true;
                    CardBelote carteCertaine_ = GameBeloteCommon.hand(cartesCertaines_,couleurAtout_,taker).premiereCarte();
                    boolean possedeDeuxiemeCarteForte_ = false;
                    for(CardBelote c: HandBelote.couleurComplete(couleurAtout_, Order.TRUMP)) {
                        if(c.strength(couleurAtout_, bid)
                                < carteCertaine_.strength(couleurAtout_, bid)) {
                            if(!GameBeloteCommon.hand(repartition_,couleurAtout_).contient(c)) {
                                continue;
                            }
                            boolean possedeDeuxiemeCarte_ = true;
                            for(CardBelote c2_: HandBelote.couleurComplete(couleurAtout_, Order.TRUMP)) {
                                if(c2_.strength(couleurAtout_, bid)
                                        < c.strength(couleurAtout_, bid)) {
                                    continue;
                                }
                                if(c2_ == c) {
                                    continue;
                                }
                                if(c2_ == carteCertaine_) {
                                    continue;
                                }
                                if(repartitionCartesJouees_.getVal(couleurAtout_).contient(c2_)) {
                                    continue;
                                }
                                possedeDeuxiemeCarte_ = false;
                                break;
                            }
                            possedeDeuxiemeCarteForte_ = possedeDeuxiemeCarte_;
                            continue;
                        }
                        if(repartitionCartesJouees_.getVal(couleurAtout_).contient(c)) {
                            continue;
                        }
                        cartesMaitressesJouees_ = false;
                    }
                    if(cartesMaitressesJouees_) {
                        boolean troisCouleursCarteMaitre_=true;
                        for(Suit couleur_:couleursNonAtouts_) {
                            if (GameBeloteCommon.hand(cartesMaitresses_,couleur_).estVide()) {
                                troisCouleursCarteMaitre_ = false;
                            }
                        }
                        if(troisCouleursCarteMaitre_||!possedeDeuxiemeCarteForte_) {
                            return GameBeloteCommon.hand(repartition_,couleurAtout_).premiereCarte();
                        }
                    }
                }
            }
            if(!GameBeloteCommon.hand(repartition_,couleurAtout_).estVide()&&GameBeloteCommon.hand(repartition_,couleurAtout_).derniereCarte().points(bid) < 8) {
                return GameBeloteCommon.hand(repartition_,couleurAtout_).derniereCarte();
            }
            if (!lastSeenHand.estVide()) {
                CardBelote carteDessus_=lastSeenHand.premiereCarte();
                Suit couleurDessus_=carteDessus_.couleur();

                if(couleurDessus_!=couleurAtout_
                        &&!GameBeloteCommon.hand(cartesCertaines_,couleurDessus_,taker).estVide()) {
                    boolean cartesMaitressesJouees_ = true;
                    CardBelote carteCertaine_ = GameBeloteCommon.hand(cartesCertaines_,couleurDessus_,taker).premiereCarte();
                    for(CardBelote c: HandBelote.couleurComplete(couleurDessus_, Order.SUIT)) {
                        if(c.strength(couleurDessus_, bid)
                                < carteCertaine_.strength(couleurDessus_, bid)) {
                            continue;
                        }
                        if(repartitionCartesJouees_.getVal(couleurDessus_).contient(c)) {
                            continue;
                        }
                        cartesMaitressesJouees_ = false;
                    }
                    if(cartesMaitressesJouees_) {
                        if(GameBeloteCommonPlaying.tours(couleurDessus_, plisFaits_).isEmpty()&&!GameBeloteCommon.hand(repartition_,couleurDessus_).estVide()) {
                            return GameBeloteCommon.hand(repartition_,couleurDessus_).premiereCarte();
                        }
                    }
                }
            }


            EnumList<Suit> couleurs_=GameBeloteCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleursNonAtouts_);
            /*On considere que l'appele est place apres le preneur*/
            if(!couleurs_.isEmpty()) {
                return ouvrir(couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
            }
            couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
            couleurs_ = GameBeloteCommon.couleursAvecPoints(currentHand, bid, couleurs_);
            if(!couleurs_.isEmpty()) {
                return faireCouperPreneurFigure(couleurs_,repartition_);
            }
            couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(partenaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
            couleurs_ = GameBeloteCommon.couleursAvecNbPointsInfEg(currentHand, bid, couleurs_, 4);
            if(!couleurs_.isEmpty()) {
                return faireTomberPointsPourPreneur(couleurs_,repartition_,repartitionCartesJouees_);
            }
            couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
            couleurs_ = GameBeloteCommonPlaying.couleursNonCoupeeParJoueurs(currentHand, adversaire_, bid, cartesPossibles_, cartesCertaines_, couleurs_);
            if(!couleurs_.isEmpty()) {
                return ouvrir(couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
            }
            couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(adversaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return faireCouperAdv(couleurs_,repartition_,repartitionCartesJouees_);
            }
            return faireCouperAdv(couleursNonVides_,repartition_,repartitionCartesJouees_);
        }
        EnumList<Suit> couleurs_=GameBeloteCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleursNonVides_);
        /*Cas d'un defenseur*/
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursPouvantEtreCoupees(adversaire_, cartesPossibles_, couleurAtout_, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return faireCouperAdv(couleurs_,repartition_,repartitionCartesJouees_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
        couleurs_ = GameBeloteCommonPlaying.couleursNonCoupeeParJoueurs(currentHand, adversaire_, bid, cartesPossibles_, cartesCertaines_, couleurs_);

        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
        }
        return faireCouperAdv(couleursNonVides_,repartition_,repartitionCartesJouees_);
    }
    CardBelote entameSansAtoutToutAtout() {
        byte next_ = info.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        BeloteInfoPliEnCours info_ = common.initInformations(currentHand);
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        HandBelote cartesJouees_=info_.getCartesJouees();
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumList<Suit> couleursMaitres_=info_.getCouleursMaitresses();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> adversaire_ =info_.getJoueursNonConfiance();
        boolean maitreJeu_;
        /*Jeu sans atout ou tout atout*/
        maitreJeu_=info_.isMaitreJeu();
        EnumList<Suit> couleursNonAtouts_=GameBeloteCommon.couleurs();
        if(maitreJeu_) {
            return jeuMainMaitresseCouleursEgales(repartition_,couleursMaitres_);
        }
        EnumList<Suit> couleursNonVides_ = GameBeloteCommon.couleursNonAtoutNonVides(currentHand, couleursNonAtouts_);
        if(currentStatus == Status.TAKER) {
            EnumList<Suit> couleursMaitressesAvecPoints_ = GameBeloteCommon.couleursAvecCarteMaitresse(currentHand, cartesJouees_, bid, couleursNonVides_);
            couleursMaitressesAvecPoints_ = GameBeloteCommon.couleursAvecPoints(currentHand, bid, couleursMaitressesAvecPoints_);
            EnumList<Suit> couleurs_;
            if(GameBeloteCommonPlaying.egaliteCouleurs(couleursNonVides_, couleursMaitressesAvecPoints_)) {
                /*Il existe une carte de couleur autre que l'atout*/
                return common.carteMaitresse(couleursNonVides_, cartesMaitresses_, currentHand, cartesJouees_, cartesPossibles_, next_, suites_);
            }
            couleurs_ = GameBeloteCommonPlaying.couleursSansCarteMaitresse(currentHand, cartesJouees_, bid, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return faireCouperAdv(couleurs_, repartition_, repartitionCartesJouees_);
            }
            return faireCouperAdv(couleursNonVides_, repartition_, repartitionCartesJouees_);
        }
        EnumList<Suit> couleurs_;
        if(currentStatus == Status.CALLED_PLAYER) {
            if (!lastSeenHand.estVide()) {
                Suit couleurDessus_=lastSeenHand.premiereCarte().couleur();
                if(!GameBeloteCommon.hand(cartesCertaines_,couleurDessus_,taker).estVide()) {
                    boolean cartesMaitressesJouees_ = true;
                    CardBelote carteCertaine_ = GameBeloteCommon.hand(cartesCertaines_,couleurDessus_,taker).premiereCarte();
                    for(CardBelote c: GameBeloteCommonPlaying.cartesAtouts(couleurDessus_)) {
                        if(c.strength(couleurDessus_, bid)
                                < carteCertaine_.strength(couleurDessus_, bid)) {
                            continue;
                        }
                        if(repartitionCartesJouees_.getVal(couleurDessus_).contient(c)) {
                            continue;
                        }
                        cartesMaitressesJouees_ = false;
                    }
                    if(cartesMaitressesJouees_) {
                        if(GameBeloteCommonPlaying.tours(couleurDessus_, plisFaits_).isEmpty()&&!GameBeloteCommon.hand(repartition_,couleurDessus_).estVide()) {
                            return GameBeloteCommon.hand(repartition_,couleurDessus_).premiereCarte();
                        }
                    }
                }
            }
            /*On considere que l'appele est place apres le preneur*/
            couleurs_ = GameBeloteCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return ouvrir(couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
            }
            couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
            if(!couleurs_.isEmpty()) {
                return ouvrir(couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
            }
            return faireCouperAdv(couleursNonVides_,repartition_,repartitionCartesJouees_);
        }
        /*Cas d'un defenseur*/
        couleurs_ = GameBeloteCommonPlaying.couleursNonOuvertesNonVides(currentHand, plisFaits_, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_, repartition_, repartitionCartesJouees_, cartesMaitresses_);
        }
        couleurs_ = GameBeloteCommonPlaying.couleursDefausseeParJoueurs(adversaire_, bid, cartesPossibles_, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return ouvrir(couleurs_,repartition_,repartitionCartesJouees_,cartesMaitresses_);
        }
        return faireCouperAdv(couleursNonVides_,repartition_,repartitionCartesJouees_);
    }
    private static CardBelote jeuMainMaitresseCouleurDominante(
            EnumMap<Suit,EqList<HandBelote>> _suites,
            EnumMap<Suit,HandBelote> _repartition,
            EnumList<Suit> _couleursMaitres,
            Suit _couleurAtout) {
        if(!GameBeloteCommon.suite(_suites,_couleurAtout).isEmpty()) {
            return GameBeloteCommon.hand(_suites,_couleurAtout,0).premiereCarte();
        }
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(_repartition, _couleursMaitres);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    private static CardBelote jeuMainMaitresseCouleursEgales(
            EnumMap<Suit,HandBelote> _repartition,
            EnumList<Suit> _couleursMaitres) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursNonAtoutNonVides(_repartition, _couleursMaitres);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    public static byte nombreCartesPoints(EnumMap<Suit,HandBelote> _repartition,BidBeloteSuit _contrat,Suit _couleur) {
        return _repartition.getVal(_couleur).nombreCartesPoints(_contrat);
    }
    private CardBelote faireTomberPointsPourPreneur(
            EnumList<Suit> _couleurs,EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartitionCartesJouees, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote faireCouperPreneurFigure(
            EnumList<Suit> _couleurs,EnumMap<Suit,HandBelote> _repartition) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote ouvrir(EnumList<Suit> _couleurs,
                              EnumMap<Suit,HandBelote> _repartition,
                              EnumMap<Suit,HandBelote> _repartitionCartesJouees,
                              EnumMap<Suit,HandBelote> _cartesMaitresses) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_cartesMaitresses, _couleurs);
        //maitre
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, couleurs_);
        //jouees
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, couleurs_);
        //longues
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        //basses
        if(!GameBeloteCommon.hand(_cartesMaitresses,couleurs_.first()).estVide()) {
            return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
        }
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote ouvrirCouleur(
            EnumList<Suit> _couleurs,EnumMap<Suit,HandBelote> _repartition) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }

    private CardBelote faireCouperAdv(EnumList<Suit> _couleurs,
                                      EnumMap<Suit,HandBelote> _repartition,
                                      EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartitionCartesJouees, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    private static CardBelote faireCouperAppele(
            EnumList<Suit> _couleurs,EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
}
