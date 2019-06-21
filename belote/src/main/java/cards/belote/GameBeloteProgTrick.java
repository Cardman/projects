package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.PossibleTrickWinner;
import cards.consts.Status;
import cards.consts.Suit;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.Numbers;

public final class GameBeloteProgTrick {
    private GameBeloteTeamsRelation teamsRelation;
    private GameBeloteTrickInfo info;
    private TrickBelote progressingTrick;

    private HandBelote currentHand;
    private GameBeloteCommonPlaying common;
    private Status currentStatus;
    private HandBelote playableCards;
    private BidBeloteSuit bid;
    private byte taker;
    private HandBelote lastSeenHand;

    public GameBeloteProgTrick(GameBeloteTrickInfo _done, GameBeloteTeamsRelation _teamsRelation, HandBelote _currentHand) {
        teamsRelation = _teamsRelation;
        currentHand = _currentHand;
        info = _done;
        progressingTrick = _done.getProgressingTrick();
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

    CardBelote enCours() {
        if(playableCards.total()==1) {
            return playableCards.premiereCarte();
        }
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        if(bid.getCouleurDominante()) {
            Suit couleurAtout_=bid.getCouleur();
            if (couleurDemandee_ != couleurAtout_) {
                if(!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                    return fournirCouleurOrdinaireCouleurDominante();
                }
                if(!repartitionJouables_.getVal(couleurAtout_).estVide()) {
                    return coupeCouleurDominante();
                }
                return defausseCouleurOrdinaireCouleurDominante();
            }
            //entame atout
            if(!repartitionJouables_.getVal(couleurAtout_).estVide()) {
                return fournirAtoutCouleurDominante();
            }
            return defausseAtoutCouleurDominante();
        }
        if(bid.ordreCouleur()) {
            if(!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                return fournirCouleurOrdinaireSansAtout();
            }
            return defausseCouleurOrdinaireSansAtout();
        }
        //jeu tout atout
        if(!GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).estVide()) {
            return fournirAtoutToutAtout();
        }
        return defausseAtoutToutAtout();
    }
    private CardBelote fournirCouleurOrdinaireCouleurDominante() {
        byte next_ = info.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        BeloteInfoPliEnCours info_ = initInformations(currentHand);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> appele_=teamsRelation.partenaires(taker);
        Numbers<Byte> partenaire_ = info_.getJoueursConfiance();
        Numbers<Byte> adversaire_ = info_.getJoueursNonConfiance();
        Numbers<Byte> joueursNonJoue_= info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(info_, carteForte_);
        boolean carteMaitresse_;
        byte maxTwo_;
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);

        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            for(byte joueur_:adversaire_) {
                if(!GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).estVide()) {
                    if(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).premiereCarte().strength(couleurDemandee_,bid)
                            >GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
            }
            if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).nombreCartesPoints(bid)>1) {
                return cartePlusPetitePoints(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
            }
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        TrickBelote dernierPli_;
        EqList<HandBelote> cartesRelMaitres_;
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        Numbers<Byte> dernieresCoupes_;
        Numbers<Byte> dernieresDefausses_;
        Numbers<Byte> joueursSusceptiblesDeCouper_=new Numbers<Byte>();


        //Si le joueur ne coupe pas et ne se defause pas sur la couleur demandee
        cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurDemandee_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_,couleurAtout_, cartesCertaines_,carteForte_);
        if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)<carteForte_.strength(couleurDemandee_,bid)
                ||carteForte_.couleur()==couleurAtout_) {
            /*Si le joueur ne peut pas prendre la GameBeloteCommon.main*/
            if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().points(bid)<1||maitreJeu_) {
                /*Si le joueur ne possede pas de figure ou est maitre du jeu*/
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le joueur possede au moins une figure*/
            if(tours_.isEmpty()) {
                /*Si cette couleur est entamee pour la premiere fois*/
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    //si aucun adv non joue ne peut ramasser le pli ==> pli pour le partenaire
                    boolean partenaireMaitre_ = true;
                    for(byte j: adversaireNonJoue_) {
                        HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j);
                        if(couleurPossible_.estVide()) {
                            continue;
                        }
                        byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                        if(carteForte_.strength(couleurDemandee_,bid)>max_) {
                            continue;
                        }
                        partenaireMaitre_ = false;
                        break;
                    }
                    if(partenaireMaitre_) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            dernierPli_=tours_.last();
            dernieresCoupes_=dernierPli_.joueursCoupes(couleurAtout_);
            /*Maintenant on aborde au moins le deuxieme tour*/
            if(dernieresCoupes_.isEmpty()) {
                /*Si le dernier pli n'est pas coupe a cette couleur*/
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    if(carteForte_.couleur()==couleurAtout_) {
                        /*L'espoir fait vivre*/
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne peut ramasser le pli ==> pli pour le partenaire
                    boolean partenaireMaitre_ = true;
                    for(byte j: adversaireNonJoue_) {
                        HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j);
                        if(couleurPossible_.estVide()) {
                            continue;
                        }
                        byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                        if(carteForte_.strength(couleurDemandee_,bid)>max_) {
                            continue;
                        }
                        partenaireMaitre_ = false;
                        break;
                    }
                    if(partenaireMaitre_) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Maintenant on sait qu'au dernier tour le pli a ete coupe*/
            if(partenaire_.containsObj(tours_.last().getRamasseur(bid))) {
                if(carteForte_.couleur()==couleurAtout_) {
                    /*L'espoir fait vivre*/
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                //si aucun adv non joue ne peut ramasser le pli ==> pli pour le partenaire
                boolean partenaireMaitre_ = true;
                for(byte j: adversaireNonJoue_) {
                    HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j);
                    if(couleurPossible_.estVide()) {
                        continue;
                    }
                    byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                    if(carteForte_.strength(couleurDemandee_,bid)>max_) {
                        continue;
                    }
                    partenaireMaitre_ = false;
                    break;
                }
                if(partenaireMaitre_) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
            }
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant on sait que le joueur peut prendre la GameBeloteCommon.main*/
        if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().points(bid)<1) {
            //si aucun adv non joue ne possede de figure
            if(maitreJeu_||defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                if(!cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
            }
            if(tours_.isEmpty()) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant le joueur peut prendre la GameBeloteCommon.main avec une figure a la couleur demandee*/
        if(currentStatus == Status.TAKER) {
            if(tours_.isEmpty()) {
                if(canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede de figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1
                                ||!partenaire_.containsObj(ramasseurVirtuel_)||!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede de figure
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            /*C'est au moins le deuxieme tour*/
            dernierPli_=tours_.last();
            dernieresDefausses_=dernierPli_.joueursDefausses(couleurAtout_);

            for(byte joueur_:joueursNonJoue_) {
                if(GameBeloteTrickHypothesis.peutCouper(couleurDemandee_, joueur_, cartesPossibles_,couleurAtout_)) {
                    joueursSusceptiblesDeCouper_.add(joueur_);
                }
            }
            if(!joueursSusceptiblesDeCouper_.isEmpty()) {
                for(byte joueur_:adversaire_) {
                    if(joueursSusceptiblesDeCouper_.containsObj(joueur_)) {
                        return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
                    }
                }
                if(maitreJeu_) {
                    maxTwo_=0;
                    for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                        if(joueur_!=next_) {
                            maxTwo_=(byte)Math.max(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).total(),maxTwo_);
                        }
                    }
                    if(GameBeloteCommon.hand(suites_,couleurDemandee_,0).total()>maxTwo_) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
                }
                return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
            }
            /*Si la coupe semble improbable*/
            if(!dernieresDefausses_.isEmpty()&&tours_.size()==1) {
                if(canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1
                            ||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede de figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1
                                ||!partenaire_.containsObj(ramasseurVirtuel_)||!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede de figure
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le pli d'avant n'est pas defausse ou c'est au moins le troisieme tour*/
            if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            if(!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Appele*/
        if(currentStatus == Status.CALLED_PLAYER) {
            if(tours_.isEmpty()) {
                if(canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!joueursNonJoue_.containsObj(taker)) {
                    /*Si l'appele joue apres le preneur*/
                    if (!lastSeenHand.estVide()) {
                        CardBelote carteDessus_=lastSeenHand.premiereCarte();
                        boolean peutCouperAdvNonJoue_ = false;
                        for(byte j: adversaireNonJoue_) {
                            if(!GameBeloteTrickHypothesis.peutCouper(couleurDemandee_,j,cartesPossibles_,couleurAtout_)) {
                                continue;
                            }
                            peutCouperAdvNonJoue_ = true;
                            break;
                        }
                        if(!peutCouperAdvNonJoue_) {
                            //si aucun adv non joue ne ramasser le pli => cartesRelMaitres si existe
                            carteMaitresse_=true;
                            for(byte j: adversaireNonJoue_) {
                                HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j);
                                if(couleurPossible_.estVide()) {
                                    continue;
                                }
                                byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                                if(carteDessus_.strength(couleurDemandee_,bid)>max_) {
                                    continue;
                                }
                                carteMaitresse_ = false;
                                break;
                            }
                            if(canLeadTrick(carteMaitresse_, cartesRelMaitres_)) {
                                if(cartesRelMaitres_.size()==1||cartesRelMaitres_.get(1).premiereCarte().points(bid)<1) {
                                    return cartesRelMaitres_.get(0).premiereCarte();
                                }
                                return cartesRelMaitres_.get(1).premiereCarte();
                            }
                        }
                        if(carteDessus_.points(bid)>0) {
                            /*Si l'appele joue avant le preneur et la carte du dessus vaut des points.*/
                            peutCouperAdvNonJoue_ = false;
                            for(byte j: adversaireNonJoue_) {
                                if(!GameBeloteTrickHypothesis.peutCouper(couleurDemandee_,j,cartesPossibles_,couleurAtout_)) {
                                    continue;
                                }
                                peutCouperAdvNonJoue_ = true;
                                break;
                            }
                            if(!peutCouperAdvNonJoue_) {
                                //si aucun adv non joue ne ramasser le pli => jeuFigureHauteDePlusFaibleSuite
                                carteMaitresse_=true;
                                for(byte j: adversaireNonJoue_) {
                                    HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j);
                                    if(couleurPossible_.estVide()) {
                                        continue;
                                    }
                                    byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                                    if(carteDessus_.strength(couleurDemandee_,bid)>max_) {
                                        continue;
                                    }
                                    carteMaitresse_ = false;
                                    break;
                                }
                                if(carteMaitresse_) {
                                    return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                                }
                            }
                        }
                    }
                }
                if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adversaire non joue ne possede une figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1
                                ||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1
                                ||!partenaire_.containsObj(ramasseurVirtuel_)
                                ||!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adversaire non joue ne possede une figure
                if(partenaire_.containsObj(ramasseurVirtuel_)
                        &&defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            dernierPli_=tours_.last();
            dernieresDefausses_=dernierPli_.joueursDefausses(couleurAtout_);
            /*Deuxieme tour pour un appele ne coupant pas la couleur demandee differente de l'atout*/
            for(byte joueur_:joueursNonJoue_) {
                if(GameBeloteTrickHypothesis.peutCouper(couleurDemandee_, joueur_, cartesPossibles_,couleurAtout_)) {
                    joueursSusceptiblesDeCouper_.add(joueur_);
                }
            }
            if(!joueursSusceptiblesDeCouper_.isEmpty()) {
                for(byte joueur_:adversaire_) {
                    if(joueursSusceptiblesDeCouper_.containsObj(joueur_)) {
                        return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
                    }
                }
                if(maitreJeu_) {
                    maxTwo_=0;
                    for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreJoueurs_;joueur_++) {
                        if(joueur_!=next_) {
                            maxTwo_=(byte)Math.max(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).total(),maxTwo_);
                        }
                    }
                    if(GameBeloteCommon.hand(suites_,couleurDemandee_,0).total()>maxTwo_) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
                }
                return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
            }
            /*Si la coupe semble improbable*/
            if(!dernieresDefausses_.isEmpty()&&tours_.size()==1) {
                if(canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1
                            ||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede une figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1
                                ||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1
                                ||!partenaire_.containsObj(ramasseurVirtuel_)||!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede une figure a la couleur demandee
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le pli d'avant n'est pas defausse ou c'est au moins le troisieme tour*/
            if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            if(!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Defenseur*/
        if(canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        boolean pasAtoutAdvNonJoue_ = GameBeloteTrickHypothesis.pasAtoutJoueurs(adversaireNonJoue_, cartesPossibles_, couleurAtout_);
        //si aucun adv non joue n'a de l'atout alors les points peuvent etre joues
        if(pasAtoutAdvNonJoue_) {
            return sauveQuiPeutFigure(cartesPossibles_,GameBeloteCommon.suite(suites_,couleurDemandee_), cartesRelMaitres_, adversaireNonJoue_, couleurDemandee_);
        }
        if(tours_.isEmpty()) {
            if(!joueursNonJoue_.containsObj(taker)||carteForte_.points(bid)>0) {
                /*Si le joueur (defenseur) va jouer apres le preneur et il reste des joueurs susceptibles d'etre l'appele ou il existe une figure que peut prendre le joueur*/
                if(!cartesRelMaitres_.isEmpty()) {
                    if(cartesRelMaitres_.size()==1||cartesRelMaitres_.get(1).premiereCarte().points(bid)<1) {
                        return GameBeloteCommon.hand(suites_,couleurDemandee_,0).premiereCarte();
                    }
                    return cartesRelMaitres_.get(1).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            if(!cartesRelMaitres_.isEmpty()&&cartesRelMaitres_.get(0).total()==1&&GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).total()==2) {
                if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    boolean pasMonteeAdvNonJoueSurPartenaire_ = true;
                    for(byte j: adversaireNonJoue_) {
                        if(GameBeloteTrickHypothesis.defausse(couleurDemandee_,j,cartesPossibles_,bid)) {
                            continue;
                        }
                        HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j);
                        if(!couleurPossible_.estVide()) {
                            byte max_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j).premiereCarte().strength(couleurDemandee_,bid);
                            if(max_ < carteForte_.strength(couleurDemandee_,bid)) {
                                continue;
                            }
                        }
                        pasMonteeAdvNonJoueSurPartenaire_ = false;
                        break;
                    }
                    //si tous les adv non joue se defaussent ou ne peuvent pas monter sur le partenaire
                    if(pasMonteeAdvNonJoueSurPartenaire_) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).carte(1);
                    }
                }
                return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant on est au moins au deuxieme tour*/
        return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
    }

    private boolean canLeadTrick(boolean _maitreJeu, EqList<HandBelote> _cartesRelMaitres) {
        return _maitreJeu && !_cartesRelMaitres.isEmpty();
    }

    private CardBelote coupeCouleurDominante() {
        Suit couleurAtout_=bid.getCouleur();
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        if(GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).total()==playableCards.total()) {
            return coupeObligatoireCouleurDominante();
        }
        return coupeFacultativeCouleurDominante();
    }
    private CardBelote coupeObligatoireCouleurDominante() {
        BeloteInfoPliEnCours info_ = initInformations(currentHand);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,EqList<HandBelote>> suites_= info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> adversaire_ = info_.getJoueursNonConfiance();
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(info_, carteForte_);
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur est oblige de couper la couleur demandee*/
            return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            if(GameBeloteCommon.suite(suites_,couleurAtout_).size()==1) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte();
            }
            return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).derniereCarte();
        }
        EqList<HandBelote> cartesRelMaitres_;
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurAtout_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurAtout_, couleurAtout_, cartesCertaines_,carteForte_);
        /*Coupe obligatoire (ou surcoupe ou sous-coupe)*/
        return coupe(repartitionJouables_,cartesPossibles_,cartesMaitresses_,suites_,adversaireNonJoue_,couleurAtout_,couleurDemandee_,tours_,carteForte_,cartesRelMaitres_,maitreJeu_);
    }
    private CardBelote coupeFacultativeCouleurDominante() {
        BeloteInfoPliEnCours info_ = initInformations(currentHand);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumList<Suit> couleursMaitres_=info_.getCouleursMaitresses();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=info_.getStrictCouleursMaitresses();
        Numbers<Byte> adversaire_ = info_.getJoueursNonConfiance();
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(info_, carteForte_);
        boolean carteMaitresse_;
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur se defausse*/
            return defausseCouleurDemandeeSurAdversaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
        }
        EqList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurAtout_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurAtout_, couleurAtout_, cartesCertaines_,carteForte_);
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        /*Coupe possible non obligatoire, car partenaire.contains(ramasseurVirtuel)*/
        if(GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte().strength(couleurDemandee_, bid)>carteForte_.strength(couleurDemandee_, bid)) {
            //surcoupe possible non obligatoire
            if(canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            if(GameBeloteCommon.suite(suites_,couleurAtout_).size()==1
                    &&couleursMaitres_.size()==common.couleursNonAtouts().size()
                    &&GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte().points(bid)>0) {
                //si aucun adv non joue ne peut surcouper ==> plus grande carte
                boolean nePeutSurcouper_ = true;
                for(byte j: adversaireNonJoue_) {
                    if(GameBeloteTrickHypothesis.nePeutCouper(couleurDemandee_, j, cartesPossibles_, cartesCertaines_,bid.getCouleur())) {
                        continue;
                    }
                    if(GameBeloteCommon.hand(cartesPossibles_,couleurAtout_,j).premiereCarte().strength(couleurDemandee_,bid)
                            <GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte().strength(couleurDemandee_,bid)) {
                        continue;
                    }
                    nePeutSurcouper_ = false;
                    break;
                }
                if(nePeutSurcouper_) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte();
                }
            }
            if(!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                return coupe(repartitionJouables_,cartesPossibles_,cartesMaitresses_,suites_,adversaireNonJoue_,couleurAtout_,couleurDemandee_,tours_,carteForte_,cartesRelMaitres_,maitreJeu_);
            }
            return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
        }
        //sous coupe possible non obligatoire
        if(maitreJeu_&&GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).derniereCarte().points(bid)<10) {
            carteMaitresse_=true;
            for(Suit couleur_:common.couleursNonAtouts()) {
                if(!GameBeloteCommon.hand(repartition_,couleur_).estVide()) {
                    if (!(GameBeloteCommon.hand(repartition_,couleur_).derniereCarte().points(bid)>0)) {
                        carteMaitresse_ = false;
                    }
                }
            }
            if(carteMaitresse_) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).derniereCarte();
            }
        }
        return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
    }
    private CardBelote fournirAtoutCouleurDominante() {
        BeloteInfoPliEnCours info_ = initInformations(currentHand);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> partenaire_ = info_.getJoueursConfiance();
        Numbers<Byte> adversaire_ = info_.getJoueursNonConfiance();
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(info_, carteForte_);
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        EqList<HandBelote> suitesJouables_ = repartitionJouables_.getVal(couleurAtout_).eclater(info_.getRepartitionCartesJouees(), bid);
        //entame atout
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*La couleur demandee est atout*/
            return GameBeloteCommon.hand(repartition_,couleurAtout_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            if(suitesJouables_.size()==1) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte();
            }
            for(byte joueur_:adversaire_) {
                if(!GameBeloteCommon.hand(cartesPossibles_,couleurAtout_,joueur_).estVide()) {
                    if(GameBeloteCommon.hand(cartesPossibles_,couleurAtout_,joueur_).premiereCarte().strength(couleurDemandee_,bid)
                            >GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte().strength(couleurDemandee_,bid)) {
                        //Il existe un adversaire pouvant surcouper le pli avec un atout relativement maitre sur le joueur courant
                        if(joueursNonJoue_.containsAllObj(partenaire_)) {
                            return cartePlusPetitePoints(suitesJouables_,bid);
                        }
                        return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).premiereCarte();
                    }
                }
            }
            return GameBeloteCommon.hand(repartitionJouables_,couleurAtout_).derniereCarte();
        }
        //peut ramasser

        EqList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(suitesJouables_, cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_,couleurDemandee_, cartesCertaines_,carteForte_);
        if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)<carteForte_.strength(couleurDemandee_,bid)) {
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        //peut monter
        if(canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        boolean nePeutMonterSurCarteForte_ = true;
        for(byte j: adversaireNonJoue_) {
            if(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j).estVide()) {
                //si pas d'atout
                continue;
            }
            if(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j).premiereCarte().strength(couleurDemandee_,bid)<carteForte_.strength(couleurDemandee_,bid)) {
                continue;
            }
            nePeutMonterSurCarteForte_ = false;
            break;
        }
        //si aucun adv non joue ne possede pas d'atout sup ou egal a la carte forte
        if(canLeadTrick(nePeutMonterSurCarteForte_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
            byte min_=-1;
            byte forceMin_ = 0;
            for(byte j: adversaireNonJoue_) {
                byte forceMinLoc_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j).premiereCarte().strength(couleurDemandee_,bid);
                if(forceMinLoc_ > forceMin_) {
                    forceMin_ = forceMinLoc_;
                }
            }
            //pour prendre la GameBeloteCommon.main, il faut avoir une carte superieur ou egal au max des cartes des adversaires n'ayant pas joue
            for(HandBelote suite_:suitesJouables_) {
                if(suite_.premiereCarte().strength(couleurDemandee_,bid)>forceMin_) {
                    min_++;
                } else {
                    break;
                }
            }
            return suitesJouables_.get(min_).premiereCarte();
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
    }
    private CardBelote defausseCouleurOrdinaireCouleurDominante() {
        BeloteInfoPliEnCours info_ = initInformations(currentHand);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurAtout_= info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=info_.getStrictCouleursMaitresses();
        Numbers<Byte> partenaire_ = info_.getJoueursConfiance();
        Numbers<Byte> adversaire_ = info_.getJoueursNonConfiance();
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        if(adversaireNonJoue_.isEmpty()) {
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
            }
        }
        if(tours_.isEmpty()) {
            if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                return defausseCouleurDemandeeSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
            }
        }
        return defausseCouleurDemandeeSurAdversaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleurAtout_, couleursStrictementMaitresses_);
    }
    private CardBelote defausseAtoutCouleurDominante() {
        BeloteInfoPliEnCours info_ = initInformations(currentHand);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=info_.getStrictCouleursMaitresses();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliCouleurDominante(info_, carteForte_);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*La couleur demandee est atout*/
            /*Maintenant le joueur se defausse sur demande d'atout*/
            return defausseAtoutSurAdversaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurAtout_, couleursStrictementMaitresses_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            /*Maintenant le joueur se defausse*/
            return defausseAtoutSurPartenaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurAtout_, couleursStrictementMaitresses_);
        }
        return defausseAtoutSurAdversaireCouleurDominante(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurAtout_, couleursStrictementMaitresses_);
    }
    private CardBelote fournirCouleurOrdinaireSansAtout() {
        BeloteInfoPliEnCours info_ = initInformations(currentHand);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        Suit couleurAtout_=info_.getCouleurAtout();
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,EqList<HandBelote>> suites_=info_.getSuitesTouteCouleur();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> partenaire_ = info_.getJoueursConfiance();
        Numbers<Byte> adversaire_ = info_.getJoueursNonConfiance();
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        boolean maitreJeu_=info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(info_, carteForte_);
        boolean carteMaitresse_;
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);

        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {

            for(byte joueur_:adversaire_) {
                if(!GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).estVide()) {
                    if(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).premiereCarte().strength(couleurDemandee_,bid)>GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
            }
            if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).nombreCartesPoints(bid)>1) {
                return cartePlusPetitePoints(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
            }
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        TrickBelote dernierPli_;
        EqList<HandBelote> cartesRelMaitres_;
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        Numbers<Byte> dernieresDefausses_;

        //Si le joueur ne se defause pas sur la couleur demandee
        cartesRelMaitres_=cartesRelativementMaitre(GameBeloteCommon.suite(suites_,couleurDemandee_), cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_,couleurAtout_, cartesCertaines_,carteForte_);
        if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)<
                carteForte_.strength(couleurDemandee_,bid)) {
            /*Si le joueur ne peut pas prendre la GameBeloteCommon.main*/
            if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().points(bid)<1||maitreJeu_) {
                /*Si le joueur ne possede pas de figure ou est maitre du jeu*/
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le joueur possede au moins une figure*/
            if(tours_.isEmpty()) {
                /*Si cette couleur est entamee pour la premiere fois*/
                if(partenaire_.containsObj(ramasseurVirtuel_)) {
                    //si aucun adv non joue ne peut ramasser le pli ==> pli pour le partenaire
                    boolean partenaireMaitre_ = true;
                    for(byte j: adversaireNonJoue_) {
                        HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j);
                        if(couleurPossible_.estVide()) {
                            continue;
                        }
                        byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                        if(carteForte_.strength(couleurDemandee_,bid)>max_) {
                            continue;
                        }
                        partenaireMaitre_ = false;
                        break;
                    }
                    if(partenaireMaitre_) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Maintenant on aborde au moins le deuxieme tour*/
            /*Si le dernier pli n'est pas coupe a cette couleur*/
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                //si aucun adv non joue ne peut ramasser le pli ==> pli pour le partenaire
                boolean partenaireMaitre_ = true;
                for(byte j: adversaireNonJoue_) {
                    HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j);
                    if(couleurPossible_.estVide()) {
                        continue;
                    }
                    byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                    if(carteForte_.strength(couleurDemandee_,bid)>max_) {
                        continue;
                    }
                    partenaireMaitre_ = false;
                    break;
                }
                if(partenaireMaitre_) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
            }
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant on sait que le joueur peut prendre la GameBeloteCommon.main*/
        if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().points(bid)<1) {
            //si aucun adv non joue ne possede de figure
            if(maitreJeu_||defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                if(!cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
            }
            if(tours_.isEmpty()) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Maintenant le joueur peut prendre la GameBeloteCommon.main avec une figure a la couleur demandee*/
        if(currentStatus == Status.TAKER) {
            if(tours_.isEmpty()) {
                if(canLeadTrick(maitreJeu_,cartesRelMaitres_)) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede de figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if (GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaire_.containsObj(ramasseurVirtuel_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede de figure
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            /*C'est au moins le deuxieme tour*/
            dernierPli_=tours_.last();
            dernieresDefausses_=dernierPli_.joueursDefausses(couleurAtout_);

            /*Si la coupe semble improbable*/
            if(!dernieresDefausses_.isEmpty()&&tours_.size()==1) {
                if(canLeadTrick(maitreJeu_,cartesRelMaitres_)) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede de figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)&&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if (GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaire_.containsObj(ramasseurVirtuel_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede de figure
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le pli d'avant n'est pas defausse ou c'est au moins le troisieme tour*/
            if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            if(!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Appele*/
        if(currentStatus == Status.CALLED_PLAYER) {
            if(tours_.isEmpty()) {
                if(canLeadTrick(maitreJeu_,cartesRelMaitres_)) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!joueursNonJoue_.containsObj(taker)) {
                    /*Si l'appele joue apres le preneur*/
                    //si aucun adv non joue ne ramasser le pli => cartesRelMaitres si existe
                    if (!lastSeenHand.estVide()) {
                        CardBelote carteDessus_=lastSeenHand.premiereCarte();
                        carteMaitresse_=true;
                        for(byte j: adversaireNonJoue_) {
                            HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j);
                            if(couleurPossible_.estVide()) {
                                continue;
                            }
                            byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                            if(carteDessus_.strength(couleurDemandee_,bid)>max_) {
                                continue;
                            }
                            carteMaitresse_ = false;
                            break;
                        }
                        if(canLeadTrick(carteMaitresse_, cartesRelMaitres_)) {
                            if(cartesRelMaitres_.size()==1||cartesRelMaitres_.get(1).premiereCarte().points(bid)<1) {
                                return cartesRelMaitres_.get(0).premiereCarte();
                            }
                            return cartesRelMaitres_.get(1).premiereCarte();
                        }
                        if(carteDessus_.points(bid)>0) {
                            /*Si l'appele joue avant le preneur et la carte du dessus vaut des points.*/
                            //si aucun adv non joue ne ramasser le pli => jeuFigureHauteDePlusFaibleSuite
                            carteMaitresse_=true;
                            for(byte j: adversaireNonJoue_) {
                                HandBelote couleurPossible_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j);
                                if(couleurPossible_.estVide()) {
                                    continue;
                                }
                                byte max_ = couleurPossible_.premiereCarte().strength(couleurDemandee_,bid);
                                if(carteDessus_.strength(couleurDemandee_,bid)>max_) {
                                    continue;
                                }
                                carteMaitresse_ = false;
                                break;
                            }
                            if(carteMaitresse_) {
                                return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                            }
                        }
                    }
                }
                if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adversaire non joue ne possede une figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1
                                ||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1
                                ||!partenaire_.containsObj(ramasseurVirtuel_)
                                ||!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adversaire non joue ne possede une figure
                if(partenaire_.containsObj(ramasseurVirtuel_)
                        &&defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            dernierPli_=tours_.last();
            dernieresDefausses_=dernierPli_.joueursDefausses(couleurAtout_);
            /*Deuxieme tour pour un appele ne coupant pas la couleur demandee differente de l'atout*/
            /*Si la coupe semble improbable*/
            if(!dernieresDefausses_.isEmpty()&&tours_.size()==1) {
                if(canLeadTrick(maitreJeu_,cartesRelMaitres_)) {
                    return cartesRelMaitres_.last().premiereCarte();
                }
                if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                    if(GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1||GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                    //si aucun adv non joue ne possede une figure
                    if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)
                            &&(carteForte_.points(bid)<1||partenaire_.containsObj(ramasseurVirtuel_))) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(carteForte_.points(bid)>0) {
                        if (GameBeloteCommon.suite(suites_,couleurDemandee_).size()==1) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (GameBeloteCommon.hand(suites_,couleurDemandee_,1).premiereCarte().points(bid)<1) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaire_.containsObj(ramasseurVirtuel_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        if (!partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                        }
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                        return jeuFigureHauteDePlusFaibleSuite(GameBeloteCommon.suite(suites_,couleurDemandee_),bid);
                    }
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                /*Le joueur n'a aucune cartes maitresses*/
                //si aucun adv non joue ne possede une figure a la couleur demandee
                if(defausseOuPasDeFigure(cartesPossibles_,couleurDemandee_,adversaireNonJoue_)) {
                    return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                }
                return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
            }
            /*Le pli d'avant n'est pas defausse ou c'est au moins le troisieme tour*/
            if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
                return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
            }
            if(!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return GameBeloteCommon.hand(repartition_,couleurDemandee_).derniereCarte();
        }
        /*Defenseur*/
        if(canLeadTrick(maitreJeu_,cartesRelMaitres_)) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        //si aucun adv non joue n'a de l'atout alors les points peuvent etre joues
        return sauveQuiPeutFigure(cartesPossibles_,GameBeloteCommon.suite(suites_,couleurDemandee_), cartesRelMaitres_, adversaireNonJoue_, couleurDemandee_);
    }
    private CardBelote defausseCouleurOrdinaireSansAtout() {
        BeloteInfoPliEnCours info_ = initInformations(currentHand);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        CustList<TrickBelote> plisFaits_=info_.getPlisFaits();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();

        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=info_.getStrictCouleursMaitresses();
        Numbers<Byte> partenaire_ = info_.getJoueursConfiance();
        Numbers<Byte> adversaire_ = info_.getJoueursNonConfiance();
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliSansAtout(info_, carteForte_);
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Maintenant le joueur se defausse*/
            return defausseCouleurDemandeeSurAdversaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseCouleurDemandeeSurPartenaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
        }
        CustList<TrickBelote> tours_=GameBeloteCommonPlaying.tours(couleurDemandee_, plisFaits_);
        //Defausse
        if(adversaireNonJoue_.isEmpty()) {
            if(partenaire_.containsObj(ramasseurVirtuel_)) {
                return defausseCouleurDemandeeSurPartenaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
            }
        }
        if(tours_.isEmpty()) {
            if(partenaire_.containsObj(ramasseurVirtuel_)&&partenaireBatAdversaireNonJoue(adversaireNonJoue_, couleurDemandee_, cartesPossibles_, carteForte_)) {
                return defausseCouleurDemandeeSurPartenaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
            }
        }
        return defausseCouleurDemandeeSurAdversaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleurDemandee_, couleursStrictementMaitresses_);
    }
    private CardBelote fournirAtoutToutAtout() {
        BeloteInfoPliEnCours info_ = initInformations(currentHand);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_=info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_=info_.getCartesCertaines();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        Numbers<Byte> adversaire_ = info_.getJoueursNonConfiance();
        Numbers<Byte> joueursNonJoue_=info_.getJoueursNonJoue();
        Numbers<Byte> adversaireNonJoue_ = GameBeloteTeamsRelation.intersectionJoueurs(adversaire_,joueursNonJoue_);
        boolean maitreJeu_=info_.isMaitreJeu();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        Suit couleurDemandee_=progressingTrick.couleurDemandee();
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(info_, carteForte_);
        EnumMap<Suit,HandBelote> repartitionJouables_=playableCards.couleurs(bid);
        EqList<HandBelote> suites_=repartitionJouables_.getVal(couleurDemandee_).eclater(info_.getRepartitionCartesJouees(), bid);
        //jeu tout atout
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            for(byte joueur_:adversaire_) {
                if(!GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).estVide()) {
                    if(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,joueur_).premiereCarte().strength(couleurDemandee_,bid)>GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)) {
                        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte();
                    }
                }
            }
            if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).nombreCartesPoints(bid)>1) {
                return cartePlusPetitePoints(suites_,bid);
            }
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();

        }

        EqList<HandBelote> cartesRelMaitres_=cartesRelativementMaitre(suites_, cartesPossibles_, joueursNonJoue_, couleurDemandee_, couleurDemandee_,couleurDemandee_, cartesCertaines_,carteForte_);
        if(GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).premiereCarte().strength(couleurDemandee_,bid)<carteForte_.strength(couleurDemandee_,bid)) {
            return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();
        }
        if(canLeadTrick(maitreJeu_,cartesRelMaitres_)) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        boolean nePeutMonterSurCarteForte_ = true;
        for(byte j: adversaireNonJoue_) {
            if(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j).estVide()) {
                //si pas d'atout
                continue;
            }
            if(GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j).premiereCarte().strength(couleurDemandee_,bid)<carteForte_.strength(couleurDemandee_,bid)) {
                continue;
            }
            nePeutMonterSurCarteForte_ = false;
            break;
        }
        //si aucun adv non joue ne possede pas d'atout sup ou egal a la carte forte
        if(nePeutMonterSurCarteForte_) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if(!GameBeloteCommon.hand(cartesMaitresses_,couleurDemandee_).estVide()) {
            byte min_=-1;
            byte forceMin_ = 0;
            for(byte j: adversaireNonJoue_) {
                byte forceMinLoc_ = GameBeloteCommon.hand(cartesPossibles_,couleurDemandee_,j).premiereCarte().strength(couleurDemandee_,bid);
                if(forceMinLoc_ > forceMin_) {
                    forceMin_ = forceMinLoc_;
                }
            }
            //pour prendre la GameBeloteCommon.main, il faut avoir une carte superieur ou egal au max des cartes des adversaires n'ayant pas joue
            for(HandBelote suite_:suites_) {
                if(suite_.premiereCarte().strength(couleurDemandee_,bid)>forceMin_) {
                    min_++;
                } else {
                    break;
                }
            }
            return suites_.get(min_).premiereCarte();
        }
        return GameBeloteCommon.hand(repartitionJouables_,couleurDemandee_).derniereCarte();

    }
    private CardBelote defausseAtoutToutAtout() {
        BeloteInfoPliEnCours info_ = initInformations(currentHand);
        byte nombreJoueurs_=teamsRelation.getNombreDeJoueurs();
        EnumMap<Suit,HandBelote> repartition_=currentHand.couleurs(bid);
        EnumMap<Suit,HandBelote> repartitionCartesJouees_=info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandBelote> cartesMaitresses_=info_.getCartesMaitresses();
        EnumList<Suit> couleursStrictementMaitresses_=info_.getStrictCouleursMaitresses();
        byte ramasseurVirtuel_=info_.getRamasseurVirtuel();
        CardBelote carteForte_=progressingTrick.carteDuJoueur(ramasseurVirtuel_,nombreJoueurs_);
        PossibleTrickWinner ramasseurCertain_=GameBeloteTrickHypothesis.equipeQuiVaFairePliToutAtout(info_, carteForte_);
        //jeu tout atout
        //
        if(ramasseurCertain_==PossibleTrickWinner.FOE_TEAM) {
            /*Si le joueur se defausse*/
            return defausseAtoutSurAdversaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleursStrictementMaitresses_);

        }
        if(ramasseurCertain_==PossibleTrickWinner.TEAM) {
            return defausseAtoutSurPartenaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleursStrictementMaitresses_);
        }
        return defausseAtoutSurAdversaireCouleursEgales(repartitionCartesJouees_, repartition_, cartesMaitresses_, couleursStrictementMaitresses_);

    }
    BeloteInfoPliEnCours initInformations(
            HandBelote _cartes) {
        return common.initInformations(_cartes);
    }

    //adversaires non joues
    private boolean defausseOuPasDeFigure(
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Suit _couleurDemandee,
            Numbers<Byte> _joueurs) {
        boolean defausseOuPasDeFigure_ = true;
        for(byte j: _joueurs) {
            if(GameBeloteTrickHypothesis.defausse(_couleurDemandee, j, _cartesPossibles,bid)) {
                continue;
            }
            if(nePeutPasAvoirFigures(_cartesPossibles, j, _couleurDemandee, bid)) {
                continue;
            }
            defausseOuPasDeFigure_ = false;
        }
        return defausseOuPasDeFigure_;
    }

    private CardBelote coupe(
            EnumMap<Suit,HandBelote> _repartitionJouables,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            EnumMap<Suit,EqList<HandBelote>> _suites,
            Numbers<Byte> _adversaireNonJoue,
            Suit _couleurAtout, Suit _couleurDemandee,
            CustList<TrickBelote> _tours,CardBelote _carteForte,
            EqList<HandBelote> _cartesRelMaitres,boolean _maitreJeu) {
        byte nombrePointsPli_;
        if(GameBeloteCommon.hand(_repartitionJouables,_couleurAtout).premiereCarte().strength(_couleurDemandee, bid)<_carteForte.strength(_couleurDemandee, bid)) {
            //sous coupe
            return GameBeloteCommon.hand(_repartitionJouables,_couleurAtout).derniereCarte();
        }
        if(canLeadTrick(_maitreJeu, _cartesRelMaitres)) {
            //sur coupe ou coupe
            return _cartesRelMaitres.last().premiereCarte();
        }
        /*Deuxieme tour ou plus si surcoupe possible*/
        boolean surcoupePossible_ = false;
        for(byte j: _adversaireNonJoue) {
            if(GameBeloteCommon.hand(_cartesPossibles,_couleurAtout,j).estVide()) {
                continue;
            }
            if(!GameBeloteCommon.hand(_cartesPossibles,_couleurDemandee,j).estVide()) {
                continue;
            }
            surcoupePossible_ = true;
            break;
        }
        //si pas d'adv non joue => pas de surcoupe possible
        if(!_tours.isEmpty()
                &&surcoupePossible_) {
            if(GameBeloteCommon.suite(_suites,_couleurAtout).size()==1||GameBeloteCommon.hand(_cartesMaitresses,_couleurAtout).estVide()) {
                return GameBeloteCommon.hand(_repartitionJouables,_couleurAtout).derniereCarte();
            }
            nombrePointsPli_=0;
            for(CardBelote carte_:progressingTrick) {
                nombrePointsPli_+=carte_.points(bid);
            }
            if(canLeadTrick(nombrePointsPli_>=10, _cartesRelMaitres)) {
                return _cartesRelMaitres.last().premiereCarte();
            }
            return GameBeloteCommon.hand(_repartitionJouables,_couleurAtout).derniereCarte();
        }
        if(GameBeloteCommon.suite(_suites,_couleurAtout).size()==1) {
            return GameBeloteCommon.hand(_repartitionJouables,_couleurAtout).premiereCarte();
        }
        if(!GameBeloteCommon.hand(_cartesMaitresses,_couleurAtout).estVide()) {
            return GameBeloteCommon.suite(_suites,_couleurAtout).last().premiereCarte();
        }
        if(GameBeloteCommon.hand(_repartitionJouables,_couleurAtout).premiereCarte().points(bid)>0) {
            return GameBeloteCommon.hand(_repartitionJouables,_couleurAtout).premiereCarte();
        }
        return GameBeloteCommon.hand(_repartitionJouables,_couleurAtout).derniereCarte();
    }

    /**Est vrai si et seulement si le partenaire du joueur qui va jouer domine l'adversaire n'ayant pas joue (uniquement si le partenaire est maitre temporairement du pli)*/
    private boolean partenaireBatAdversaireNonJoue(
            Numbers<Byte> _adversaireNonJoue,Suit _couleurDemandee,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            CardBelote _carteForte) {
        boolean adversairesBattus_ = true;
        for(byte j:_adversaireNonJoue) {
            if(GameBeloteCommon.hand(_cartesPossibles,_couleurDemandee,j).estVide()) {
                continue;
            }
            if(_carteForte.strength(_couleurDemandee, bid)>GameBeloteCommon.hand(_cartesPossibles,_couleurDemandee,j).premiereCarte().strength(_couleurDemandee, bid)) {
                continue;
            }
            adversairesBattus_ = false;
            break;
        }
        return adversairesBattus_;
    }

    private CardBelote sauveQuiPeutFigure(
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EqList<HandBelote> _suites,
            EqList<HandBelote> _cartesRelMaitres,Numbers<Byte> _adversaireNonJoue,
            Suit _couleurDemandee) {
        //si aucun adversaire ne possede une carte a point dans la couleur demandee, alors les points peuvent etre sauves
        boolean aucuneCartePointsAdvNonJoue_ = true;
        for(byte j: _adversaireNonJoue) {
            if(GameBeloteCommon.hand(_cartesPossibles,_couleurDemandee,j).estVide()) {
                continue;
            }
            if(GameBeloteCommon.hand(_cartesPossibles,_couleurDemandee,j).premiereCarte().points(bid)<1) {
                continue;
            }
            aucuneCartePointsAdvNonJoue_ = false;
            break;
        }
        if(aucuneCartePointsAdvNonJoue_) {
            return jeuFigureHauteDePlusFaibleSuite(_suites,bid);
        }
        if(!_cartesRelMaitres.isEmpty()) {
            if(_cartesRelMaitres.size()==1||_cartesRelMaitres.get(1).premiereCarte().points(bid)<1) {
                return _suites.get(0).premiereCarte();
            }
            return _cartesRelMaitres.get(1).premiereCarte();
        }
        return _suites.last().derniereCarte();
    }
    //adversairenonjoue
    private static boolean nePeutPasAvoirFigures(EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,byte _joueur, Suit _couleurDemandee, BidBeloteSuit _contrat) {
        return GameBeloteCommon.hand(_cartesPossibles,_couleurDemandee,_joueur).estVide()||GameBeloteCommon.hand(_cartesPossibles,_couleurDemandee,_joueur).premiereCarte().points(_contrat)==0;
    }
    private EqList<HandBelote> cartesRelativementMaitre(
            EqList<HandBelote> _suites,EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Numbers<Byte> _joueursNonJoue, Suit _couleurDemandee,
            Suit _couleurJoueur,Suit _couleurAtout,
            EnumMap<Suit,EqList<HandBelote>> _cartesCertaines,CardBelote _carteForte) {
        byte maxValeur_=0;
        EqList<HandBelote> suitesBis_=new EqList<HandBelote>();
        if(_couleurJoueur==_couleurAtout&&_couleurDemandee!=_couleurAtout) {
            for(byte joueur_:_joueursNonJoue) {
                if(!GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).estVide()&&GameBeloteCommon.hand(_cartesCertaines,_couleurDemandee,joueur_).estVide()) {
                    maxValeur_=(byte)Math.max(GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).premiereCarte().strength(_couleurDemandee,bid),maxValeur_);
                }
            }
        } else {
            for(byte joueur_:_joueursNonJoue) {
                if(!GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).estVide()) {
                    maxValeur_=(byte)Math.max(GameBeloteCommon.hand(_cartesPossibles,_couleurJoueur,joueur_).premiereCarte().strength(_couleurDemandee,bid),maxValeur_);
                }
            }
        }
        maxValeur_=(byte)Math.max(maxValeur_,_carteForte.strength(_couleurDemandee,bid));
        for(HandBelote suite_:_suites) {
            if(suite_.premiereCarte().strength(_couleurDemandee,bid)>maxValeur_) {
                suitesBis_.add(suite_);
            } else {
                break;
            }
        }
        return suitesBis_;
    }

    private static CardBelote jeuFigureHauteDePlusFaibleSuite(EqList<HandBelote> _suites, BidBeloteSuit _contrat) {
        if(_suites.size()==CustList.ONE_ELEMENT) {
            return _suites.first().premiereCarte();
        }
        return cartePlusPetitePoints(_suites,_contrat);
    }
    private static CardBelote cartePlusPetitePoints(EqList<HandBelote> _suites, BidBeloteSuit _contrat) {
        EqList<HandBelote> suitesPoints_ = new EqList<HandBelote>();
        EqList<HandBelote> suitesZeroPoints_ = new EqList<HandBelote>();
        for(HandBelote suite_:_suites) {
            HandBelote points_ = new HandBelote();
            HandBelote zeroPoints_ = new HandBelote();
            for(CardBelote c: suite_) {
                if(c.points(_contrat) > 0) {
                    points_.ajouter(c);
                } else {
                    zeroPoints_.ajouter(c);
                }
            }
            if(!points_.estVide()) {
                suitesPoints_.add(points_);
            }
            if(!zeroPoints_.estVide()) {
                suitesZeroPoints_.add(zeroPoints_);
            }
        }
        if(!suitesZeroPoints_.isEmpty()) {
            return suitesZeroPoints_.last().premiereCarte();
        }
        return suitesPoints_.last().premiereCarte();
    }

    private CardBelote defausseAtoutSurAdversaireCouleurDominante(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurAtout,EnumList<Suit> _couleursStrictementMaitresses) {

        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        if(GameBeloteCommon.hand(_repartitionCartesJouees,_couleurAtout).total()>5) {
            if(_couleursStrictementMaitresses.size()==common.couleursNonAtouts().size()) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            return discardFoe(_repartitionCartesJouees, _repartition, couleursNonVides_);
        }
        /*Moins de 6 atouts sont joues*/
        return discardFoe(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }
    private CardBelote defausseAtoutSurAdversaireCouleursEgales(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        if(_couleursStrictementMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        return discardFoe(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }
    private CardBelote defausseAtoutSurPartenaireCouleurDominante(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurAtout,EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        boolean carteMaitresse_;
        if(GameBeloteCommon.hand(_repartitionCartesJouees,_couleurAtout).total()>5) {
            if(_couleursStrictementMaitresses.size()==common.couleursNonAtouts().size()) {
                carteMaitresse_ = leadCard(_repartition, _cartesMaitresses, _couleursStrictementMaitresses);
                if(carteMaitresse_) {
                    EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
                    if(!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                    }
                }
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            /*Il peut exister une couleur pour se defausser non strictement maitresse*/
            EnumList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
            if(!couleursBis_.isEmpty()) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursBis_);
            }
            return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
        }
        /*Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur demandee sont jouees*/
        if(_couleursStrictementMaitresses.size()==common.couleursNonAtouts().size()) {
            carteMaitresse_ = leadCard(_repartition, _cartesMaitresses, _couleursStrictementMaitresses);
            if(carteMaitresse_) {
                EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
                if(!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                }
            }
        }
        return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }

    private boolean leadCard(EnumMap<Suit, HandBelote> _repartition, EnumMap<Suit, HandBelote> _cartesMaitresses, EnumList<Suit> _couleursStrictementMaitresses) {
        boolean carteMaitresse_;
        carteMaitresse_=true;
        for(Suit couleur_:_couleursStrictementMaitresses) {
            if (GameBeloteCommon.hand(_cartesMaitresses, couleur_).total() != GameBeloteCommon.hand(_repartition, couleur_).total()) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }

    private CardBelote defausseAtoutSurPartenaireCouleursEgales(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        boolean carteMaitresse_;
        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        if(_couleursStrictementMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
            carteMaitresse_ = leadCard(_repartition, _cartesMaitresses, _couleursStrictementMaitresses);
            if(carteMaitresse_) {
                EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
                if(!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                }
            }
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
        }
        /*Il peut exister une couleur pour se defausser non strictement maitresse*/
        EnumList<Suit> couleurs_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleurs_);
        }
        couleurs_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
        if(!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(couleursNonVides_,_repartition,_repartitionCartesJouees);

    }
    private CardBelote defausseCouleurDemandeeSurAdversaireCouleurDominante(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurDemandee,Suit _couleurAtout,
            EnumList<Suit> _couleursStrictementMaitresses) {

        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        EnumList<Suit> couleursAutreQueDemandeeEtAtout_ = common.couleursNonAtouts();
        couleursAutreQueDemandeeEtAtout_.removeObj(_couleurDemandee);
        if((GameBeloteCommon.hand(_repartitionCartesJouees,_couleurAtout).total()>6)&&GameBeloteCommon.hand(_repartitionCartesJouees,_couleurDemandee).total()>6) {
            if(_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandeeEtAtout_)) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            return discardFoe(_repartitionCartesJouees, _repartition, couleursNonVides_);
        }
        /*Moins de 7 atouts sont joues ou moins de 7 cartes de la couleur demandee sont jouees*/
        return discardFoe(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }

    CardBelote discardFoe(EnumMap<Suit, HandBelote> _repartitionCartesJouees, EnumMap<Suit, HandBelote> _repartition, EnumList<Suit> _couleursNonVides) {
        EnumList<Suit> couleurs_=GameBeloteCommon.couleursSansPoint(_repartition, bid, _couleursNonVides);
        if(!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(_couleursNonVides, _repartition, _repartitionCartesJouees);
    }

    private CardBelote defausseCouleurDemandeeSurAdversaireCouleursEgales(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurDemandee,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        if(GameBeloteCommon.hand(_repartitionCartesJouees,_couleurDemandee).total()>6) {
            if(_couleursStrictementMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            return discardFoe(_repartitionCartesJouees, _repartition, couleursNonVides_);
        }
        /*Moins de 7 atouts sont joues ou moins de 7 cartes de la couleur demandee sont jouees*/
        return discardFoe(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }
    private CardBelote defausseCouleurDemandeeSurPartenaireCouleurDominante(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurDemandee,Suit _couleurAtout,
            EnumList<Suit> _couleursStrictementMaitresses) {

        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        EnumList<Suit> couleursAutreQueDemandeeEtAtout_ = common.couleursNonAtouts();
        couleursAutreQueDemandeeEtAtout_.removeObj(_couleurDemandee);
        boolean carteMaitresse_;
        if((GameBeloteCommon.hand(_repartitionCartesJouees,_couleurAtout).total()>6)&&GameBeloteCommon.hand(_repartitionCartesJouees,_couleurDemandee).total()>6) {
            if(_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandeeEtAtout_)) {
                carteMaitresse_ = leadCard(_repartition, _cartesMaitresses, _couleurAtout, _couleursStrictementMaitresses);
                if(carteMaitresse_) {
                    EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
                    if(!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                    }
                }
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            /*Il peut exister une couleur non strictement maitresse pour se defausser*/
            EnumList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
            if(!couleursBis_.isEmpty()) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursBis_);
            }
            return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
        }
        /*Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur demandee sont jouees*/
        if(_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandeeEtAtout_)) {
            carteMaitresse_ = leadCard(_repartition, _cartesMaitresses, _couleurAtout, _couleursStrictementMaitresses);
            if(carteMaitresse_) {
                EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
                if(!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                }
            }
        }
        return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }

    private boolean leadCard(EnumMap<Suit, HandBelote> _repartition, EnumMap<Suit, HandBelote> _cartesMaitresses, Suit _couleurAtout, EnumList<Suit> _couleursStrictementMaitresses) {
        boolean carteMaitresse_;
        carteMaitresse_=true;
        for(Suit couleur_:_couleursStrictementMaitresses) {
            if (GameBeloteCommon.hand(_cartesMaitresses, couleur_).total() != GameBeloteCommon.hand(_repartition, couleur_).total() || _couleurAtout == couleur_) {
                carteMaitresse_ = false;
            }
        }
        return carteMaitresse_;
    }

    private CardBelote defausseCouleurDemandeeSurPartenaireCouleursEgales(
            EnumMap<Suit,HandBelote> _repartitionCartesJouees,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            Suit _couleurDemandee,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_=GameBeloteCommon.couleursNonAtoutNonVides(_repartition,common.couleursNonAtouts());
        boolean carteMaitresse_;
        if(GameBeloteCommon.hand(_repartitionCartesJouees,_couleurDemandee).total()>6) {
            if(_couleursStrictementMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
                carteMaitresse_ = leadCard(_repartition, _cartesMaitresses, _couleursStrictementMaitresses);
                if(carteMaitresse_) {
                    EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
                    if(!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                    }
                }
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursNonVides_);
            }
            /*Il peut exister une couleur non strictement maitresse pour se defausser*/
            EnumList<Suit> couleursBis_=GameBeloteCommon.couleursNonAtoutNonVides(_cartesMaitresses,couleursNonVides_);
            if(!couleursBis_.isEmpty()) {
                return jeuPetiteDefausseMaitre(_cartesMaitresses, _repartition, couleursBis_);
            }
            return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
        }
        /*Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur demandee sont jouees*/
        if(_couleursStrictementMaitresses.size()==GameBeloteCommon.couleurs().size() - 1) {
            carteMaitresse_ = leadCard(_repartition, _cartesMaitresses, _couleursStrictementMaitresses);
            if(carteMaitresse_) {
                EnumList<Suit> couleursFigures_=GameBeloteCommon.couleursAvecPoints(_repartition, bid, couleursNonVides_);
                if(!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_, _repartition);
                }
            }
        }
        return discardPartner(_repartitionCartesJouees, _repartition, couleursNonVides_);
    }

    private CardBelote discardPartner(EnumMap<Suit, HandBelote> _repartitionCartesJouees, EnumMap<Suit, HandBelote> _repartition, EnumList<Suit> _couleursNonVides) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursAvecPoints(_repartition, bid, _couleursNonVides);
        if(!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_,_repartition,_repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(_couleursNonVides,_repartition,_repartitionCartesJouees);
    }
    private CardBelote sauverFiguresDefausse(
            EnumList<Suit> _couleursFigures,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursAvecLePlusGrandNbPoints(_repartition, bid, _couleursFigures);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartitionCartesJouees, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLaPlusGrandeFigure(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote jeuPetiteCarteCouleurFigure(
            EnumList<Suit> _couleurs, EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursAvecLaPlusPetiteCarteBasse(_repartition, bid, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote jeuGrandeCarteDefausseMaitre(
            EnumList<Suit> _couleursFigures,
            EnumMap<Suit,HandBelote> _repartition) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleursFigures);
        couleurs_ = GameBeloteCommon.couleursLesPlusHautes(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusGrandNbPoints(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).premiereCarte();
    }
    private CardBelote jouerPetiteCarteDefausse(
            EnumList<Suit> _couleurs,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,HandBelote> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_repartitionCartesJouees, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
    private CardBelote jeuPetiteDefausseMaitre(
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            EnumMap<Suit,HandBelote> _repartition,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition, _couleurs);
        couleurs_ = GameBeloteCommon.couleursLesPlusCourtes(_cartesMaitresses, couleurs_);
        couleurs_ = GameBeloteCommon.couleursLesPlusBasses(_repartition, bid, couleurs_);
        couleurs_ = GameBeloteCommon.couleursAvecLePlusPetitNbPoints(_repartition, bid, couleurs_);
        return GameBeloteCommon.hand(_repartition,couleurs_.first()).derniereCarte();
    }
}
