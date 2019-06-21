package cards.belote;

import cards.belote.enumerations.BeloteTrumpPartner;
import cards.belote.enumerations.CardBelote;
import cards.consts.CardChar;
import cards.consts.Hypothesis;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.*;

import static cards.belote.GameBeloteCommon.suite;

public final class GameBeloteCommonPlaying {
    private GameBeloteTrickInfo doneTrickInfo;
    private GameBeloteTeamsRelation teamsRelation;

    public GameBeloteCommonPlaying(GameBeloteTrickInfo _doneTrickInfo, GameBeloteTeamsRelation _teamsRelation) {
        doneTrickInfo = _doneTrickInfo;
        teamsRelation = _teamsRelation;
    }
    BeloteInfoPliEnCours initInformations(
            HandBelote _cartes) {
        byte nbPlayers_ = teamsRelation.getNombreDeJoueurs();
        TrickBelote prog_ = doneTrickInfo.getProgressingTrick();
        byte nextPlayer_ = prog_.getNextPlayer(nbPlayers_);
        EnumMap<Suit,HandBelote> repartition_ = _cartes.couleurs(doneTrickInfo.getBid());
        Numbers<Byte> joueursNonJoue_ = joueursNAyantPasJoue(nextPlayer_);
        CustList<TrickBelote> plisFaits_ = unionPlis();
        HandBelote cartesJouees_ = doneTrickInfo.cartesJouees();
        cartesJouees_.ajouterCartes(prog_.getCartes());
        EnumMap<Suit,HandBelote> repartitionCartesJouees_ = cartesJouees_.couleurs(doneTrickInfo.getBid());
        Numbers<Byte> joueursJoue_ = new Numbers<Byte>();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers_; joueur_++) {
            if (!joueursNonJoue_.containsObj(joueur_) && joueur_ != nextPlayer_) {
                joueursJoue_.add(joueur_);
            }
        }
        EnumMap<Suit,EqList<HandBelote>> cartesPossibles_ = doneTrickInfo.cartesPossibles(_cartes);

        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandBelote>>> hypotheses_ = doneTrickInfo.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandBelote>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        byte ramasseurVirtuel_ = prog_.getRamasseurPliEnCours(nbPlayers_, doneTrickInfo.getBid());
        EnumMap<Suit,EqList<HandBelote>> suitesTouteCouleur_ = new EnumMap<Suit,EqList<HandBelote>>();

        for (Suit i : GameBeloteCommon.couleurs()) {
            suitesTouteCouleur_.put(i,repartition_.getVal(i).eclater(
                    repartitionCartesJouees_, doneTrickInfo.getBid()));
            //les couleurs sont classees comme si elles etaient demandees
        }


        boolean maitreAtout_ = true;
        if(doneTrickInfo.getBid().getCouleurDominante()) {
            maitreAtout_ = strictMaitreAtout(cartesPossibles_,nextPlayer_,suite(suitesTouteCouleur_,doneTrickInfo.getBid().getCouleur()),repartitionCartesJouees_);
        }
        EnumList<Suit> couleursMaitresses_ = couleursMaitres(
                suitesTouteCouleur_, repartitionCartesJouees_, cartesPossibles_,nextPlayer_);
        EnumMap<Suit,HandBelote> cartesMaitresses_ = GameBeloteCommon.cartesMaitresses(
                repartition_, repartitionCartesJouees_, doneTrickInfo.getBid());
        boolean maitreJeu_ = maitreAtout_ && couleursMaitresses_.size() == Suit.couleursOrdinaires().size();

        EnumList<Suit> strSuits_ = strictCouleursMaitres(suitesTouteCouleur_, repartitionCartesJouees_, cartesPossibles_, nextPlayer_);
        BeloteInfoPliEnCours info_ = new BeloteInfoPliEnCours();
        info_.setContrat(doneTrickInfo.getBid());
        info_.setProgressingTrick(prog_);
        info_.setNbPlayers(teamsRelation.getNombreDeJoueurs());
        info_.setNextPlayer(nextPlayer_);
        info_.setJoueursConfiance(teamsRelation.partenaires(nextPlayer_));
        info_.setJoueursNonConfiance(teamsRelation.adversaires(nextPlayer_));
        info_.setJoueursNonJoue(joueursNonJoue_);
        info_.setJoueursJoue(joueursJoue_);
        info_.setPlisFaits(plisFaits_);
        info_.setCartesJouees(cartesJouees_);
        info_.setRepartitionCartesJouees(repartitionCartesJouees_);
        info_.setCartesPossibles(cartesPossibles_);
        info_.setCartesCertaines(cartesCertaines_);
        info_.setRamasseurVirtuel(ramasseurVirtuel_);
        info_.setSuitesTouteCouleur(suitesTouteCouleur_);
        info_.setMaitreAtout(maitreAtout_);
        info_.setCouleursMaitresses(couleursMaitresses_);
        info_.setStrictCouleursMaitresses(strSuits_);
        info_.setCartesMaitresses(cartesMaitresses_);
        info_.setMaitreJeu(maitreJeu_);
        //depend de partie et de cartesJouables
        //carteEntamee
        //joueursNonJoue
        //joueursConfianceNonJoue
        //joueursNonConfianceNonJoue
        //CustList<PliBelote> plisFaits
        //CustList<Byte> joueursJoue
        return info_;
    }
    HandBelote playableCards(EnumMap<Suit,HandBelote> _repartitionMain) {
        /*Ensemble des cartes jouees sur ce pli*/
        byte nbPlayers_ = teamsRelation.getNombreDeJoueurs();
        TrickBelote pr_ = doneTrickInfo.getProgressingTrick();
        byte numero_=pr_.getNextPlayer(nbPlayers_);
        HandBelote m= pr_.getCartes();
        HandBelote cartesJouables_=new HandBelote();
        if(pr_.estVide()) {
            //L'entame est libre a la belote
            for(HandBelote couleur_:_repartitionMain.values()) {
                cartesJouables_.ajouterCartes(couleur_);
            }
            return cartesJouables_;
        }
        Suit couleurDemandee_= pr_.couleurDemandee();
        Suit couleurAtout_=couleurAtout();
        BidBeloteSuit bid_ = doneTrickInfo.getBid();
        byte ramasseurVirtuel_= pr_.getRamasseurPliEnCours(nbPlayers_, bid_);
        CardBelote carteForte_= pr_.carteDuJoueur(ramasseurVirtuel_, nbPlayers_);
        HandBelote leadingSuit_ = GameBeloteCommon.hand(_repartitionMain,couleurDemandee_);
        if(bid_.getCouleurDominante()) {
            HandBelote trumps_ = GameBeloteCommon.hand(_repartitionMain,couleurAtout_);
            byte valeurForte_=carteForte_.strength(couleurDemandee_, bid_);
            if(couleurAtout_==couleurDemandee_) {
                //Nombre d'atouts dans la main du joueur
                if(trumps_.estVide()) {
                    for(HandBelote couleur_:_repartitionMain.values()) {
                        cartesJouables_.ajouterCartes(couleur_);
                    }
                    return cartesJouables_;
                }
                if(trumps_.derniereCarte().strength(couleurDemandee_, bid_)>valeurForte_) {
                    cartesJouables_.ajouterCartes(trumps_);
                    return cartesJouables_;
                }
                if(trumps_.premiereCarte().strength(couleurDemandee_, bid_)<valeurForte_) {
                    cartesJouables_.ajouterCartes(trumps_);
                    return cartesJouables_;
                }
                byte indexTrump_ = CustList.FIRST_INDEX;
                while (trumps_.carte(indexTrump_).strength(couleurDemandee_, bid_)>valeurForte_) {
                    cartesJouables_.ajouter(trumps_.carte(indexTrump_));
                    indexTrump_++;
                }
                return cartesJouables_;
            }
            if(!leadingSuit_.estVide()) {
                cartesJouables_.ajouterCartes(leadingSuit_);
                return cartesJouables_;
            }
            if(trumps_.estVide()) {
                for(HandBelote couleur_:_repartitionMain.values()) {
                    cartesJouables_.ajouterCartes(couleur_);
                }
                return cartesJouables_;
            }
            if(teamsRelation.memeEquipe(ramasseurVirtuel_, numero_)) {
                /*Le partenaire est maitre temporairement*/
                if(surCoupeObligatoirePartenaire()) {
                    if(sousCoupeObligatoirePartenaire()) {
                        if(trumps_.premiereCarte().strength(couleurDemandee_, bid_)<valeurForte_) {
                            cartesJouables_.ajouterCartes(trumps_);
                            return cartesJouables_;
                        }
                    }
                    if(trumps_.derniereCarte().strength(couleurDemandee_, bid_)>valeurForte_) {
                        cartesJouables_.ajouterCartes(trumps_);
                        return cartesJouables_;
                    }
                    if(trumps_.premiereCarte().strength(couleurDemandee_, bid_)>valeurForte_) {
                        byte indexTrump_ = CustList.FIRST_INDEX;
                        while (trumps_.carte(indexTrump_).strength(couleurDemandee_, bid_)>valeurForte_) {
                            cartesJouables_.ajouter(trumps_.carte(indexTrump_));
                            indexTrump_++;
                        }
                        return cartesJouables_;
                    }
//                    (!sousCoupeObligatoirePartenaire()||
//                    main(repartitionMain,couleurAtout).premiereCarte().force(couleurDemandee,contrat)>valeurForte)
                    //main(repartitionMain,couleurAtout).premiereCarte().force(couleurDemandee,contrat)<valeurForte
                    //!sousCoupeObligatoirePartenaire() && main(repartitionMain,couleurAtout).premiereCarte().force(couleurDemandee,contrat)<valeurForte
                }
                if(sousCoupeObligatoirePartenaire()) {
                    if(trumps_.premiereCarte().strength(couleurDemandee_, bid_)<valeurForte_) {
                        cartesJouables_.ajouterCartes(trumps_);
                        return cartesJouables_;
                    }
                }
                for(HandBelote couleur_:_repartitionMain.values()) {
                    cartesJouables_.ajouterCartes(couleur_);
                }
                return cartesJouables_;
            }
            HandBelote trumpsTrick_ = GameBeloteCommon.hand(m.couleurs(bid_),couleurAtout_);
            if(trumpsTrick_.estVide()) {
                /*PliBelote non coupe*/
                cartesJouables_.ajouterCartes(trumps_);
                return cartesJouables_;
            }
            /*PliBelote coupe par un adversaire*/
            if(trumps_.derniereCarte().strength(couleurDemandee_, bid_)>valeurForte_) {
                cartesJouables_.ajouterCartes(trumps_);
                return cartesJouables_;
            }
            if(trumps_.premiereCarte().strength(couleurDemandee_, bid_)>valeurForte_) {
                byte indexTrump_ = CustList.FIRST_INDEX;
                while (trumps_.carte(indexTrump_).strength(couleurDemandee_, bid_)>valeurForte_) {
                    cartesJouables_.ajouter(trumps_.carte(indexTrump_));
                    indexTrump_++;
                }
                return cartesJouables_;
            }
            if(!sousCoupeObligatoireAdversaire()) {
                for(HandBelote couleur_:_repartitionMain.values()) {
                    cartesJouables_.ajouterCartes(couleur_);
                }
                return cartesJouables_;
            }
            cartesJouables_.ajouterCartes(trumps_);
            return cartesJouables_;
        }
        if(bid_.ordreCouleur()) {
            if(leadingSuit_.estVide()) {
                for(HandBelote couleur_:_repartitionMain.values()) {
                    cartesJouables_.ajouterCartes(couleur_);
                }
                return cartesJouables_;
            }
            cartesJouables_.ajouterCartes(leadingSuit_);
            return cartesJouables_;
        }
        if(leadingSuit_.estVide()) {
            for(HandBelote couleur_:_repartitionMain.values()) {
                cartesJouables_.ajouterCartes(couleur_);
            }
            return cartesJouables_;
        }
        byte valeurForte_=carteForte_.strength(couleurDemandee_, bid_);
        if(leadingSuit_.derniereCarte().strength(couleurDemandee_, bid_)>valeurForte_
                ||leadingSuit_.premiereCarte().strength(couleurDemandee_, bid_)<valeurForte_) {
            cartesJouables_.ajouterCartes(leadingSuit_);
            return cartesJouables_;
        }
        byte indexTrump_ = CustList.FIRST_INDEX;
        while (leadingSuit_.carte(indexTrump_).strength(couleurDemandee_, bid_)>valeurForte_) {
            cartesJouables_.ajouter(leadingSuit_.carte(indexTrump_));
            indexTrump_++;
        }
        return cartesJouables_;
    }
    CardBelote carteMaitresse(
            EnumList<Suit> _couleurs,
            EnumMap<Suit,HandBelote> _cartesMaitresses,
            HandBelote _repartition,
            HandBelote _repartitionCartesJouees,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,byte _numero,
            EnumMap<Suit,EqList<HandBelote>> _suites) {
        BidBeloteSuit bid_ = doneTrickInfo.getBid();
        CustList<EnumList<Suit>> couleursMaitres_=couleursMaitresSansReprise(_suites,_couleurs,_repartition.couleurs(bid_),_cartesPossibles,_repartitionCartesJouees,_numero);
        if (!couleursMaitres_.isEmpty()) {
            if (!couleursMaitres_.first().isEmpty()) {
                return GameBeloteCommon.hand(_repartition.couleurs(bid_),couleursMaitres_.first().first()).premiereCarte();
            }
        }
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_cartesMaitresses, _couleurs);
        //maitre
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartitionCartesJouees.couleurs(bid_), couleurs_);
        //jouees
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition.couleurs(bid_), couleurs_);
        //longues
        return GameBeloteCommon.hand(_repartition.couleurs(bid_),couleurs_.first()).premiereCarte();
    }
    CustList<EnumList<Suit>> couleursMaitresSansReprise(
            EnumMap<Suit,EqList<HandBelote>> _suites,
            EnumList<Suit> _couleurs,
            EnumMap<Suit,HandBelote> _repartition,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            HandBelote _cartesJouees,byte _numero) {
        BidBeloteSuit bid_ = doneTrickInfo.getBid();
        EnumList<Suit> couleursMaitres_=couleursMaitres(_suites, _cartesJouees.couleurs(bid_), _cartesPossibles, _numero);
        couleursMaitres_ = GameBeloteCommonPlaying.intersectionCouleurs(couleursMaitres_, _couleurs);
        return GameBeloteCommon.couleursTrieesPlusCourtes(_repartition, couleursMaitres_);
    }
    boolean strictMaitreAtout(EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, byte _numero,EqList<HandBelote> _suites,EnumMap<Suit,HandBelote> _cartesJouees) {
        Suit couleurAtout_=couleurAtout();
        int max_=CustList.SIZE_EMPTY;
        /*max designe le nombre maximal de cartes probablement possedees par un joueur*/
        int nbPlayers_ = suite(_cartesPossibles,couleurAtout_).size();
        for(int joueur_=CustList.FIRST_INDEX;joueur_<nbPlayers_;joueur_++) {
            //La taille de cartesPossibles correspond au nombre de joueurs
            if(joueur_!=_numero) {
                max_=Math.max(GameBeloteCommon.hand(_cartesPossibles,couleurAtout_,joueur_).total(),max_);
            }
        }
        /*Fin for int joueur=0;joueur<cartesPossibles.get(1).size();joueur++
        (Fin boucle sur le calcul de la valeur maximale possible des atouts*/
        if(max_==CustList.SIZE_EMPTY) {
            /*S'il est impossible que les autres joueurs aient de l'atout*/
            return true;
        }
        if(GameBeloteCommon.hand(_cartesJouees,couleurAtout_).total()==HandBelote.couleurComplete(couleurAtout_, Order.TRUMP).total()) {
            return true;
        }
        if(_suites.isEmpty()) {
            return false;
        }
        boolean existeAtoutMaitre_=true;
        CardBelote atoutPetitSuiteHaute_=_suites.first().premiereCarte();
        for(CardBelote carte_:GameBeloteCommonPlaying.cartesAtouts(couleurAtout_)) {
            if(carte_.strength(couleurAtout_,doneTrickInfo.getBid())<=atoutPetitSuiteHaute_.strength(couleurAtout_,doneTrickInfo.getBid())) {
                break;
            }
            if(!GameBeloteCommon.hand(_cartesJouees,couleurAtout_).contient(carte_)&&!_suites.first().contient(carte_)) {
                existeAtoutMaitre_=false;
                break;
            }
        }
        return existeAtoutMaitre_&&_suites.first().total()>=max_;
    }
    EnumList<Suit> couleursMaitres(
            EnumMap<Suit,EqList<HandBelote>> _suites,
            EnumMap<Suit,HandBelote> _cartesJouees,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, byte _numero) {
        EnumList<Suit> couleurs_=new EnumList<Suit>();
        if(!doneTrickInfo.getBid().getCouleurDominante()) {
            if(doneTrickInfo.getBid().ordreCouleur()) {
                for(Suit couleur_:GameBeloteCommon.couleurs()) {
                    if(completelyPlayedSuit(_cartesJouees, couleur_)||suite(_suites,couleur_).isEmpty()) {
                        couleurs_.add(couleur_);
                    } else {
                        int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                                _numero, couleur_);
                        int maitres_ = getNbLeadingSuitCards(_suites,
                                _cartesJouees, couleur_);
                        if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                            couleurs_.add(couleur_);
                        }
                    }
                }
            } else {
                for(Suit couleur_:GameBeloteCommon.couleurs()) {
                    if(completelyPlayedSuit(_cartesJouees, couleur_)||suite(_suites,couleur_).isEmpty()) {
                        couleurs_.add(couleur_);
                    } else {
                        int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                                _numero, couleur_);
                        int maitres_ = getNbLeadingTrumpCards(_suites,
                                _cartesJouees, couleur_);
                        if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                            couleurs_.add(couleur_);
                        }
                    }
                }
            }
        } else {
            for(Suit couleur_:couleursNonAtouts()) {
                if(completelyPlayedSuit(_cartesJouees, couleur_)||suite(_suites,couleur_).isEmpty()) {
                    couleurs_.add(couleur_);
                } else {
                    int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                            _numero, couleur_);
                    int maitres_ = getNbLeadingSuitCards(_suites, _cartesJouees,
                            couleur_);
                    if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                        couleurs_.add(couleur_);
                    }
                }
            }
        }
        return couleurs_;
    }
    EnumList<Suit> strictCouleursMaitres(EnumMap<Suit,EqList<HandBelote>> _suites,
                                                 EnumMap<Suit,HandBelote> _cartesJouees,
                                                 EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, byte _numero) {
        EnumList<Suit> couleurs_=new EnumList<Suit>();
        if(!doneTrickInfo.getBid().getCouleurDominante()) {
            if(doneTrickInfo.getBid().ordreCouleur()) {
                for(Suit couleur_:GameBeloteCommon.couleurs()) {
                    if(completelyPlayedSuit(_cartesJouees, couleur_)) {
                        couleurs_.add(couleur_);
                    } else if(!suite(_suites,couleur_).isEmpty()) {
                        int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                                _numero, couleur_);
                        int maitres_ = getNbLeadingSuitCards(_suites,
                                _cartesJouees, couleur_);
                        if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                            couleurs_.add(couleur_);
                        }
                    }
                }
            } else {
                for(Suit couleur_:GameBeloteCommon.couleurs()) {
                    if(completelyPlayedSuit(_cartesJouees, couleur_)) {
                        couleurs_.add(couleur_);
                    } else if(!suite(_suites,couleur_).isEmpty()) {
                        int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                                _numero, couleur_);
                        int maitres_ = getNbLeadingTrumpCards(_suites,
                                _cartesJouees, couleur_);
                        if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                            couleurs_.add(couleur_);
                        }
                    }
                }
            }
        } else {
            for(Suit couleur_:couleursNonAtouts()) {
                if(completelyPlayedSuit(_cartesJouees, couleur_)) {
                    couleurs_.add(couleur_);
                } else if(!suite(_suites,couleur_).isEmpty()) {
                    int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                            _numero, couleur_);
                    int maitres_ = getNbLeadingSuitCards(_suites, _cartesJouees,
                            couleur_);
                    if(maitres_>=max_||maitres_>CustList.SIZE_EMPTY&&suite(_suites,couleur_).size()==CustList.ONE_ELEMENT) {
                        couleurs_.add(couleur_);
                    }
                }
            }
        }
        return couleurs_;
    }
    EnumList<Suit> couleursNonAtouts() {
        EnumList<Suit> couleursNonAtouts_=new EnumList<Suit>();
        if(doneTrickInfo.getBid().getCouleurDominante()) {
            for(Suit couleur_:GameBeloteCommon.couleurs()) {
                if(couleur_!=couleurAtout()) {
                    couleursNonAtouts_.add(couleur_);
                }
            }
            return couleursNonAtouts_;
        }
        return GameBeloteCommon.couleurs();
    }
    static CustList<TrickBelote> tours(Suit _couleur,CustList<TrickBelote> _plisFaits) {
        CustList<TrickBelote> tricksNumbers_=new CustList<TrickBelote>();
        for(TrickBelote pli_:_plisFaits) {
            if(pli_.couleurDemandee()==_couleur) {
                tricksNumbers_.add(pli_);
            }
        }
        return tricksNumbers_;
    }
    static int getNbMaxPossPlayerCards(
            EnumMap<Suit,EqList<HandBelote>> _possibleCards, byte _player, Suit _suit) {
        int max_=CustList.SIZE_EMPTY;
        /*max designe le nombre maximal de cartes probablement possedees par un joueur a une couleur donnee*/
        int nbSeqs_ = suite(_possibleCards,_suit).size();
        for(int joueur_=CustList.FIRST_INDEX;joueur_<nbSeqs_;joueur_++) {
            if(joueur_!=_player) {
                max_=Math.max(GameBeloteCommon.hand(_possibleCards,_suit,joueur_).total(),max_);
            }
        }
        return max_;
    }

    int getNbLeadingTrumpCards(EnumMap<Suit,EqList<HandBelote>> _seqs,
                                       EnumMap<Suit,HandBelote> _playedCards, Suit _suit) {
        boolean existeCarteMaitresse_=true;
        CardBelote c=GameBeloteCommon.hand(_seqs,_suit,CustList.FIRST_INDEX).premiereCarte();
        HandBelote cartesCouleurs_ = GameBeloteCommonPlaying.cartesCouleurs(_suit);
        cartesCouleurs_.setOrdre(Order.TRUMP);
        cartesCouleurs_.trierUnicolore(true);
        for(CardBelote carte_:cartesCouleurs_) {
            if(carte_.strength(_suit,doneTrickInfo.getBid())<=c.strength(_suit,doneTrickInfo.getBid())) {
                break;
            }
            if(!GameBeloteCommon.hand(_playedCards,_suit).contient(carte_)&&!GameBeloteCommon.hand(_seqs,_suit,CustList.FIRST_INDEX).contient(carte_)) {
                existeCarteMaitresse_=false;
                break;
            }
        }


        int maitres_ = CustList.SIZE_EMPTY;
        if(existeCarteMaitresse_) {
            maitres_ = GameBeloteCommon.hand(_seqs,_suit,CustList.FIRST_INDEX).total();
        }
        return maitres_;
    }
    int getNbLeadingSuitCards(EnumMap<Suit,EqList<HandBelote>> _seqs,
                              EnumMap<Suit,HandBelote> _playedCards, Suit _suit) {
        boolean existeCarteMaitresse_=true;
        CardBelote c=GameBeloteCommon.hand(_seqs,_suit,CustList.FIRST_INDEX).premiereCarte();
        for(CardBelote carte_:GameBeloteCommonPlaying.cartesCouleurs(_suit)) {
            if(carte_.strength(_suit,doneTrickInfo.getBid())<=c.strength(_suit,doneTrickInfo.getBid())) {
                break;
            }
            if(!GameBeloteCommon.hand(_playedCards,_suit).contient(carte_)&&!GameBeloteCommon.hand(_seqs,_suit,CustList.FIRST_INDEX).contient(carte_)) {
                existeCarteMaitresse_=false;
                break;
            }
        }
        int maitres_ = CustList.SIZE_EMPTY;
        if(existeCarteMaitresse_) {
            maitres_ = GameBeloteCommon.hand(_seqs,_suit,CustList.FIRST_INDEX).total();
        }
        return maitres_;
    }
    static boolean completelyPlayedSuit(EnumMap<Suit,HandBelote> _playedCards,
                                        Suit _suit) {
        return GameBeloteCommon.hand(_playedCards,_suit).total()==HandBelote.couleurComplete(_suit, Order.SUIT).total();
    }
    static HandBelote cartesAtouts(Suit _couleur) {
        return HandBelote.couleurComplete(_couleur, Order.TRUMP);
    }
    static HandBelote cartesCouleurs(Suit _couleur) {
        return HandBelote.couleurComplete(_couleur,Order.SUIT);
    }
    static HandBelote cartesBeloteRebelote(BidBeloteSuit _bid) {
        HandBelote cartes_ = new HandBelote();
        if(!_bid.getCouleurDominante()) {
            return cartes_;
        }
        for(CardBelote c: GameBeloteCommonPlaying.cartesAtouts(_bid.getCouleur())) {
            if(c.getNomFigure() == CardChar.KING) {
                cartes_.ajouter(c);
            }
            if(c.getNomFigure() == CardChar.QUEEN) {
                cartes_.ajouter(c);
            }
        }
        return cartes_;
    }
    static Numbers<Byte> joueursPouvantCouperCouleurs(HandBelote _main,
                                                              Numbers<Byte> _joueurs, BidBeloteSuit _contrat,
                                                              EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, EnumList<Suit> _couleurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if(joueurs_.containsObj(joueur_)) {
                continue;
            }
            for (Suit couleur_ : _couleurs) {
                if (_main.couleur(_contrat,couleur_).estVide()) {
                    continue;
                }
                if (peutCouper(couleur_, joueur_, _cartesPossibles, _contrat.getCouleur())) {
                    joueurs_.add(joueur_);
                    break;
                }
            }
        }
        return joueurs_;
    }

    static Numbers<Byte> joueursSusceptiblesCoupe(
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Suit _couleurDemandee,
            Suit _couleurAtout,
            Numbers<Byte> _joueurs) {
        Numbers<Byte> joueursSusceptiblesDeCouper_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (peutCouper(_couleurDemandee, joueur_, _cartesPossibles, _couleurAtout)) {
                joueursSusceptiblesDeCouper_
                        .add(joueur_);
            }
        }
        return joueursSusceptiblesDeCouper_;
    }

    static EnumList<Suit> couleursSansCarteMaitresse(HandBelote _main,
                                                             HandBelote _cartesJouees, BidBeloteSuit _contrat, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandBelote> couleursMains_ = _main.couleurs(_contrat);
        EnumMap<Suit,HandBelote> cartesJouees_ = _cartesJouees.couleurs(_contrat);
        for (Suit couleur_ : _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            HandBelote cartesMaitresses_ = GameBeloteCommon.cartesMaitresses(couleursMains_,
                    cartesJouees_,_contrat).getVal(couleur_);
            if (cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursNonEntamees(
            CustList<TrickBelote> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        EnumList<Suit> toutesCouleursOrdinaires_ = GameBeloteCommon.couleurs();
        for (TrickBelote pli_ : _plis) {
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (toutesCouleursOrdinaires_.containsObj(couleurDemandee_)) {
                couleursOuvertes_.add(couleurDemandee_);
            }
        }
        for (Suit couleur_ : _couleurs) {
            if (!couleursOuvertes_.containsObj(couleur_)) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }
    static EnumList<Suit> couleursNonOuvertesNonVides(HandBelote _main,
                                                              CustList<TrickBelote> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(_main, _couleurs, 1);
        return couleursNonEntamees(_plis,couleurs_);
    }

    static EnumList<Suit> couleursOuvertes(
            CustList<TrickBelote> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        EnumList<Suit> toutesCouleursOrdinaires_ = GameBeloteCommon.couleurs();
        for (TrickBelote pli_ : _plis) {
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (toutesCouleursOrdinaires_.containsObj(couleurDemandee_)) {
                couleursOuvertes_.add(couleurDemandee_);
            }
        }
        for (Suit couleur_ : _couleurs) {
            if (!couleursOuvertes_.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }


    static EnumList<Suit> couleursCoupeePar(HandBelote _main,
                                                    byte _joueur, BidBeloteSuit _contrat, EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
                                                    EnumMap<Suit,EqList<HandBelote>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandBelote> couleursMains_ = _main.couleurs(_contrat);
        for (Suit couleur_ : _couleurs) {
            if (!_cartesPossibles.getVal(couleur_).get(_joueur).estVide()) {
                continue;
            }
            if (_cartesCertaines.getVal(_contrat.getCouleur()).get(_joueur).estVide()) {
                continue;
            }
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursPouvantEtreCoupees(
            Numbers<Byte> _joueurs,
            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            Suit _couleurAtout, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if(joueursSusceptiblesCoupe(_cartesPossibles, couleur_, _couleurAtout, _joueurs).isEmpty()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursDefausseeParJoueurs(
            Numbers<Byte> _joueurs, BidBeloteSuit _contrat, EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleursDefausses_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            for (Suit couleur_ : _couleurs) {
                if(couleursDefausses_.containsObj(couleur_)) {
                    continue;
                }
                if(!defausse(couleur_, joueur_, _cartesPossibles, _contrat)) {
                    continue;
                }
                couleursDefausses_.add(couleur_);
            }
        }
        return couleursDefausses_;
    }
    static EnumList<Suit> couleursNonCoupeeParJoueurs(HandBelote _main,
                                                              Numbers<Byte> _joueurs, BidBeloteSuit _contrat, EnumMap<Suit,EqList<HandBelote>> _cartesPossibles,
                                                              EnumMap<Suit,EqList<HandBelote>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleursCoupees_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            EnumList<Suit> couleursLoc_ = couleursCoupeePar(_main,
                    joueur_, _contrat, _cartesPossibles,
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
    
    static boolean defausse(Suit _couleur,byte _joueur,
                            EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, BidBeloteSuit _contrat) {
        if(_contrat.getCouleurDominante()) {
            return GameBeloteCommon.hand(_cartesPossibles,_contrat.getCouleur(),_joueur).estVide()&&GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide();
        }
        return GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide();
    }
    /**Retourne vrai si et seulement si le joueur ne peut pas fournir la couleur donnee et peut couper avec un atout*/
    static boolean peutCouper(Suit _couleur,byte _numero,EnumMap<Suit,EqList<HandBelote>> _cartesPossibles, Suit _couleurAtout) {
        return GameBeloteCommon.hand(_cartesPossibles,_couleur,_numero).estVide()&&!GameBeloteCommon.hand(_cartesPossibles,_couleurAtout,_numero).estVide();
    }
    /**@throws NullPointerException si un des arguments est null*/
    static boolean egaliteCouleurs(EnumList<Suit> _couleurs1, EnumList<Suit> _couleurs2) {
        return _couleurs1.containsAllObj(_couleurs2) && _couleurs2.containsAllObj(_couleurs1);
    }
    Numbers<Byte> joueursNAyantPasJoue(byte _numero) {
        Numbers<Byte> joueursNAyantPasJoue_ = new Numbers<Byte>();
        byte nombreJoueurs_ = teamsRelation.getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            if (joueur_ != _numero && !doneTrickInfo.getProgressingTrick().aJoue(joueur_, nombreJoueurs_)) {
                joueursNAyantPasJoue_.add(joueur_);
            }
        }
        return joueursNAyantPasJoue_;
    }
    static EnumList<Suit> intersectionCouleurs(EnumList<Suit> _couleurs1, EnumList<Suit> _couleurs2) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs1) {
            if(!_couleurs2.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    CustList<TrickBelote> unionPlis() {
        CustList<TrickBelote> unionPlis_ = new CustList<TrickBelote>();
        unionPlis_.addAllElts(doneTrickInfo.getTricks());
        return unionPlis_;
    }
    private Suit couleurAtout() {
        return doneTrickInfo.getBid().getCouleur();
    }

    public boolean surCoupeObligatoirePartenaire() {
        RulesBelote rules_ = teamsRelation.getRules();
        return rules_.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP
                ||rules_.getGestionCoupePartenaire()==BeloteTrumpPartner.OVERTRUMP_ONLY;
    }
    public boolean sousCoupeObligatoirePartenaire() {
        RulesBelote rules_ = teamsRelation.getRules();
        return rules_.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_OVERTRUMP
                || rules_.getGestionCoupePartenaire()==BeloteTrumpPartner.UNDERTRUMP_ONLY;
    }
    private boolean sousCoupeObligatoireAdversaire() {
        return teamsRelation.getRules().getSousCoupeAdv();
    }
}
