package cards.belote;

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
    private BidBeloteSuit bid;

    public GameBeloteCommonPlaying(GameBeloteTrickInfo _doneTrickInfo, GameBeloteTeamsRelation _teamsRelation) {
        doneTrickInfo = _doneTrickInfo;
        teamsRelation = _teamsRelation;
        bid = doneTrickInfo.getBid();
    }
    BeloteInfoPliEnCours initInformations(
            HandBelote _cartes) {
        byte nbPlayers_ = teamsRelation.getNombreDeJoueurs();
        TrickBelote prog_ = doneTrickInfo.getProgressingTrick();
        byte nextPlayer_ = prog_.getNextPlayer(nbPlayers_);
        EnumMap<Suit,HandBelote> repartition_ = _cartes.couleurs(bid);
        Bytes joueursNonJoue_ = joueursNAyantPasJoue(nextPlayer_);
        CustList<TrickBelote> plisFaits_ = unionPlis();
        HandBelote cartesJouees_ = doneTrickInfo.cartesJouees();
        cartesJouees_.ajouterCartes(prog_.getCartes());
        EnumMap<Suit,HandBelote> repartitionCartesJouees_ = cartesJouees_.couleurs(bid);
        Bytes joueursJoue_ = GameBeloteTeamsRelation.autresJoueurs(joueursNonJoue_,nbPlayers_);
        joueursJoue_.removeObj(nextPlayer_);
        EnumMap<Suit,CustList<HandBelote>> cartesPossibles_ = doneTrickInfo.cartesPossibles(_cartes);

        EnumMap<Hypothesis,EnumMap<Suit,CustList<HandBelote>>> hypotheses_ = doneTrickInfo.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,CustList<HandBelote>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        byte ramasseurVirtuel_ = prog_.getRamasseurPliEnCours(nbPlayers_, bid);
        EnumMap<Suit,CustList<HandBelote>> suitesTouteCouleur_ = _cartes.eclaterTout(repartitionCartesJouees_, bid);

        boolean maitreAtout_ = strictMaitreAtout(bid, cartesPossibles_,nextPlayer_,suite(suitesTouteCouleur_,bid.getCouleur()),repartitionCartesJouees_);
        EnumList<Suit> couleursMaitresses_ = couleursMaitres(
                bid, suitesTouteCouleur_, repartitionCartesJouees_, cartesPossibles_,nextPlayer_);
        EnumMap<Suit,HandBelote> cartesMaitresses_ = GameBeloteCommon.cartesMaitresses(
                repartition_, repartitionCartesJouees_, bid);
        boolean maitreJeu_ = maitreAtout_ && couleursMaitresses_.size() == couleursNonAtouts().size();

        EnumList<Suit> strSuits_ = strictCouleursMaitres(bid, suitesTouteCouleur_, repartitionCartesJouees_, cartesPossibles_, nextPlayer_);
        BeloteInfoPliEnCours info_ = new BeloteInfoPliEnCours();
        info_.setContrat(bid);
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
        byte ramasseurVirtuel_= pr_.getRamasseurPliEnCours(nbPlayers_, bid);
        CardBelote carteForte_= pr_.carteDuJoueur(ramasseurVirtuel_, nbPlayers_);
        HandBelote leadingSuit_ = GameBeloteCommon.hand(_repartitionMain,couleurDemandee_);
        if(bid.getCouleurDominante()) {
            HandBelote trumps_ = GameBeloteCommon.hand(_repartitionMain,couleurAtout_);
            byte valeurForte_=carteForte_.strength(couleurDemandee_, bid);
            if(couleurAtout_==couleurDemandee_) {
                //Nombre d'atouts dans la main du joueur
                if(trumps_.estVide()) {
                    for(HandBelote couleur_:_repartitionMain.values()) {
                        cartesJouables_.ajouterCartes(couleur_);
                    }
                    return cartesJouables_;
                }
                if(trumps_.derniereCarte().strength(couleurDemandee_, bid)>valeurForte_) {
                    cartesJouables_.ajouterCartes(trumps_);
                    return cartesJouables_;
                }
                if(trumps_.premiereCarte().strength(couleurDemandee_, bid)<valeurForte_) {
                    cartesJouables_.ajouterCartes(trumps_);
                    return cartesJouables_;
                }
                byte indexTrump_ = CustList.FIRST_INDEX;
                while (trumps_.carte(indexTrump_).strength(couleurDemandee_, bid)>valeurForte_) {
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
                if(teamsRelation.surCoupeObligatoirePartenaire()) {
                    if(teamsRelation.sousCoupeObligatoirePartenaire()) {
                        if(trumps_.premiereCarte().strength(couleurDemandee_, bid)<valeurForte_) {
                            cartesJouables_.ajouterCartes(trumps_);
                            return cartesJouables_;
                        }
                    }
                    if(trumps_.derniereCarte().strength(couleurDemandee_, bid)>valeurForte_) {
                        cartesJouables_.ajouterCartes(trumps_);
                        return cartesJouables_;
                    }
                    if(trumps_.premiereCarte().strength(couleurDemandee_, bid)>valeurForte_) {
                        byte indexTrump_ = CustList.FIRST_INDEX;
                        while (trumps_.carte(indexTrump_).strength(couleurDemandee_, bid)>valeurForte_) {
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
                if(teamsRelation.sousCoupeObligatoirePartenaire()) {
                    if(trumps_.premiereCarte().strength(couleurDemandee_, bid)<valeurForte_) {
                        cartesJouables_.ajouterCartes(trumps_);
                        return cartesJouables_;
                    }
                }
                for(HandBelote couleur_:_repartitionMain.values()) {
                    cartesJouables_.ajouterCartes(couleur_);
                }
                return cartesJouables_;
            }
            HandBelote trumpsTrick_ = GameBeloteCommon.hand(m.couleurs(bid),couleurAtout_);
            if(trumpsTrick_.estVide()) {
                /*PliBelote non coupe*/
                cartesJouables_.ajouterCartes(trumps_);
                return cartesJouables_;
            }
            /*PliBelote coupe par un adversaire*/
            if(trumps_.derniereCarte().strength(couleurDemandee_, bid)>valeurForte_) {
                cartesJouables_.ajouterCartes(trumps_);
                return cartesJouables_;
            }
            if(trumps_.premiereCarte().strength(couleurDemandee_, bid)>valeurForte_) {
                byte indexTrump_ = CustList.FIRST_INDEX;
                while (trumps_.carte(indexTrump_).strength(couleurDemandee_, bid)>valeurForte_) {
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
        if(bid.ordreCouleur()) {
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
        byte valeurForte_=carteForte_.strength(couleurDemandee_, bid);
        if(leadingSuit_.derniereCarte().strength(couleurDemandee_, bid)>valeurForte_
                ||leadingSuit_.premiereCarte().strength(couleurDemandee_, bid)<valeurForte_) {
            cartesJouables_.ajouterCartes(leadingSuit_);
            return cartesJouables_;
        }
        byte indexTrump_ = CustList.FIRST_INDEX;
        while (leadingSuit_.carte(indexTrump_).strength(couleurDemandee_, bid)>valeurForte_) {
            cartesJouables_.ajouter(leadingSuit_.carte(indexTrump_));
            indexTrump_++;
        }
        return cartesJouables_;
    }
    static CardBelote carteMaitresse(BidBeloteSuit _bid,
                                     EnumList<Suit> _couleurs,
                                     EnumMap<Suit, HandBelote> _cartesMaitresses,
                                     HandBelote _repartition,
                                     HandBelote _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_cartesMaitresses, _couleurs);
        //maitre
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartitionCartesJouees.couleurs(_bid), couleurs_);
        //jouees
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition.couleurs(_bid), couleurs_);
        //longues
        return GameBeloteCommon.hand(_repartition.couleurs(_bid),couleurs_.first()).premiereCarte();
    }

    static boolean strictMaitreAtout(BidBeloteSuit _bid, EnumMap<Suit, CustList<HandBelote>> _cartesPossibles, byte _numero, CustList<HandBelote> _suites, EnumMap<Suit, HandBelote> _cartesJouees) {
        if (!_bid.getCouleurDominante()) {
            return true;
        }
        Suit couleurAtout_= _bid.getSuit();
        int max_=CustList.SIZE_EMPTY;
        /*max designe le nombre maximal de cartes probablement possedees par un joueur*/
        CustList<HandBelote> trumps_ = _cartesPossibles.getVal(couleurAtout_);
        int nbPlayers_ = trumps_.size();
        for(int joueur_=CustList.FIRST_INDEX;joueur_<nbPlayers_;joueur_++) {
            //La taille de cartesPossibles correspond au nombre de joueurs
            if(joueur_!=_numero) {
                max_=Math.max(trumps_.get(joueur_).total(),max_);
            }
        }
        /*Fin for int joueur=0;joueur<cartesPossibles.get(1).size();joueur++
        (Fin boucle sur le calcul de la valeur maximale possible des atouts*/
        if(GameBeloteCommon.hand(_cartesJouees,couleurAtout_).total()==HandBelote.couleurComplete(couleurAtout_, Order.TRUMP).total()) {
            return true;
        }
        if(_suites.isEmpty()) {
            return false;
        }
        boolean existeAtoutMaitre_=true;
        CardBelote atoutPetitSuiteHaute_=_suites.first().premiereCarte();
        for(CardBelote carte_:GameBeloteCommonPlaying.cartesAtouts(couleurAtout_)) {
            if(carte_.strength(couleurAtout_, _bid)<atoutPetitSuiteHaute_.strength(couleurAtout_, _bid)) {
                break;
            }
            if(!GameBeloteCommon.hand(_cartesJouees,couleurAtout_).contient(carte_)&&!_suites.first().contient(carte_)) {
                existeAtoutMaitre_=false;
                break;
            }
        }
        return existeAtoutMaitre_&&_suites.first().total()>=max_;
    }
    static EnumList<Suit> couleursMaitres(BidBeloteSuit _bid,
                                          EnumMap<Suit, CustList<HandBelote>> _suites,
                                          EnumMap<Suit, HandBelote> _cartesJouees,
                                          EnumMap<Suit, CustList<HandBelote>> _cartesPossibles, byte _numero) {
        EnumList<Suit> couleurs_ = strictCouleursMaitres(_bid, _suites, _cartesJouees, _cartesPossibles, _numero);
        for (Suit couleur_ : couleursNonAtouts(_bid)) {
            if (_suites.getVal(couleur_).isEmpty()) {
                couleurs_.add(couleur_);
            }
        }
        couleurs_.removeDuplicates();
        return couleurs_;
    }

    static EnumList<Suit> strictCouleursMaitres(BidBeloteSuit _bid, EnumMap<Suit, CustList<HandBelote>> _suites,
                                                EnumMap<Suit, HandBelote> _cartesJouees,
                                                EnumMap<Suit, CustList<HandBelote>> _cartesPossibles, byte _numero) {
        EnumList<Suit> couleurs_=new EnumList<Suit>();
        if(!_bid.getCouleurDominante()) {
            if(_bid.ordreCouleur()) {
                for(Suit couleur_:GameBeloteCommon.couleurs()) {
                    addNormalSuit(_bid, _suites, _cartesJouees, _cartesPossibles, _numero, couleurs_, couleur_);
                }
            } else {
                for(Suit couleur_:GameBeloteCommon.couleurs()) {
                    if(completelyPlayedSuit(_cartesJouees, couleur_)) {
                        couleurs_.add(couleur_);
                    } else if(!suite(_suites,couleur_).isEmpty()) {
                        int maitres_ = getNbLeadingTrumpCards(_bid, _suites,
                                _cartesJouees, couleur_);
                        addSuit(_suites, _cartesPossibles, _numero, couleurs_, couleur_, maitres_);
                    }
                }
            }
        } else {
            for(Suit couleur_:couleursNonAtouts(_bid)) {
                addNormalSuit(_bid, _suites, _cartesJouees, _cartesPossibles, _numero, couleurs_, couleur_);
            }
        }
        return couleurs_;
    }

    private static void addNormalSuit(BidBeloteSuit _bid, EnumMap<Suit, CustList<HandBelote>> _suites, EnumMap<Suit, HandBelote> _cartesJouees, EnumMap<Suit, CustList<HandBelote>> _cartesPossibles, byte _numero, EnumList<Suit> _couleurs, Suit _couleur) {
        if(completelyPlayedSuit(_cartesJouees, _couleur)) {
            _couleurs.add(_couleur);
        } else if(!suite(_suites, _couleur).isEmpty()) {
            int maitres_ = getNbLeadingSuitCards(_bid, _suites, _cartesJouees,
                    _couleur);
            addSuit(_suites, _cartesPossibles, _numero, _couleurs, _couleur, maitres_);
        }
    }

    private static void addSuit(EnumMap<Suit, CustList<HandBelote>> _suites, EnumMap<Suit, CustList<HandBelote>> _cartesPossibles, byte _numero, EnumList<Suit> _couleurs, Suit _couleur, int _maitres) {
        int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                _numero, _couleur);
        if(_maitres >=max_|| _maitres >CustList.SIZE_EMPTY&&suite(_suites, _couleur).size()==CustList.ONE_ELEMENT) {
            _couleurs.add(_couleur);
        }
    }

    EnumList<Suit> couleursNonAtouts() {
        return couleursNonAtouts(bid);
    }
    static EnumList<Suit> couleursNonAtouts(BidBeloteSuit _bid) {
        EnumList<Suit> couleursNonAtouts_=new EnumList<Suit>();
        if(_bid.getCouleurDominante()) {
            for(Suit couleur_:GameBeloteCommon.couleurs()) {
                if(couleur_!=_bid.getCouleur()) {
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
    private static int getNbMaxPossPlayerCards(
            EnumMap<Suit, CustList<HandBelote>> _possibleCards, byte _player, Suit _suit) {
        int max_=CustList.SIZE_EMPTY;
        /*max designe le nombre maximal de cartes probablement possedees par un joueur a une couleur donnee*/
        CustList<HandBelote> poss_ = _possibleCards.getVal(_suit);
        int nbPls_ = poss_.size();
        for(int joueur_=CustList.FIRST_INDEX;joueur_<nbPls_;joueur_++) {
            if(joueur_!=_player) {
                max_=Math.max(poss_.get(joueur_).total(),max_);
            }
        }
        return max_;
    }

    static int getNbLeadingTrumpCards(BidBeloteSuit _bid, EnumMap<Suit, CustList<HandBelote>> _seqs,
                                      EnumMap<Suit, HandBelote> _playedCards, Suit _suit) {
        return getNbLeadingCards(_bid,_seqs,_playedCards,_suit,Order.TRUMP);
    }
    static int getNbLeadingSuitCards(BidBeloteSuit _bid, EnumMap<Suit, CustList<HandBelote>> _seqs,
                                     EnumMap<Suit, HandBelote> _playedCards, Suit _suit) {
        return getNbLeadingCards(_bid,_seqs,_playedCards,_suit,Order.SUIT);
    }
    private static int getNbLeadingCards(BidBeloteSuit _bid, EnumMap<Suit, CustList<HandBelote>> _seqs,
                                         EnumMap<Suit, HandBelote> _playedCards, Suit _suit, Order _or) {
        boolean existeCarteMaitresse_=true;
        CardBelote c=GameBeloteCommon.hand(_seqs,_suit,CustList.FIRST_INDEX).premiereCarte();
        for(CardBelote carte_:cartes(_suit,_or)) {
            if(carte_.strength(_suit, _bid)<c.strength(_suit, _bid)) {
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
    private static boolean completelyPlayedSuit(EnumMap<Suit, HandBelote> _playedCards,
                                                Suit _suit) {
        return GameBeloteCommon.hand(_playedCards,_suit).total()==HandBelote.couleurComplete(_suit, Order.SUIT).total();
    }
    static HandBelote cartesAtouts(Suit _couleur) {
        return HandBelote.couleurComplete(_couleur, Order.TRUMP);
    }
    static HandBelote cartesCouleurs(Suit _couleur) {
        return HandBelote.couleurComplete(_couleur,Order.SUIT);
    }
    static HandBelote cartes(Suit _couleur, Order _or) {
        return HandBelote.couleurComplete(_couleur, _or);
    }
    static HandBelote cartesBeloteRebelote(BidBeloteSuit _bid) {
        HandBelote cartes_ = new HandBelote();
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

    static Bytes joueursSusceptiblesCoupe(
            EnumMap<Suit,CustList<HandBelote>> _cartesPossibles,
            Suit _couleurDemandee,
            Suit _couleurAtout,
            Bytes _joueurs) {
        Bytes joueursSusceptiblesDeCouper_ = new Bytes();
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
        for (TrickBelote pli_ : _plis) {
            Suit couleurDemandee_ = pli_.couleurDemandee();
            couleursOuvertes_.add(couleurDemandee_);
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
        for (TrickBelote pli_ : _plis) {
            Suit couleurDemandee_ = pli_.couleurDemandee();
            couleursOuvertes_.add(couleurDemandee_);
        }
        for (Suit couleur_ : _couleurs) {
            if (!couleursOuvertes_.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }


    static EnumList<Suit> couleursCoupeePar(byte _joueur, BidBeloteSuit _contrat, EnumMap<Suit, CustList<HandBelote>> _cartesPossibles,
                                            EnumMap<Suit, CustList<HandBelote>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        if (!_cartesCertaines.getVal(_contrat.getCouleur()).get(_joueur).estVide()) {
            for (Suit couleur_ : _couleurs) {
                if (!_cartesPossibles.getVal(couleur_).get(_joueur).estVide()) {
                    continue;
                }
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    static EnumList<Suit> couleursPouvantEtreCoupees(
            Bytes _joueurs,
            EnumMap<Suit,CustList<HandBelote>> _cartesPossibles,
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
            Bytes _joueurs, BidBeloteSuit _contrat, EnumMap<Suit,CustList<HandBelote>> _cartesPossibles,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleursDefausses_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            for (Suit couleur_ : _couleurs) {
                if(!defausse(couleur_, joueur_, _cartesPossibles, _contrat)) {
                    continue;
                }
                couleursDefausses_.add(couleur_);
            }
        }
        couleursDefausses_.removeDuplicates();
        return couleursDefausses_;
    }
    static EnumList<Suit> couleursNonCoupeeParJoueurs(Bytes _joueurs, BidBeloteSuit _contrat, EnumMap<Suit, CustList<HandBelote>> _cartesPossibles,
                                                      EnumMap<Suit, CustList<HandBelote>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleursCoupees_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            EnumList<Suit> couleursLoc_ = couleursCoupeePar(
                    joueur_, _contrat, _cartesPossibles,
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
    
    private static boolean defausse(Suit _couleur, byte _joueur,
                                    EnumMap<Suit, CustList<HandBelote>> _cartesPossibles, BidBeloteSuit _contrat) {
        if(_contrat.getCouleurDominante()) {
            return GameBeloteCommon.hand(_cartesPossibles,_contrat.getCouleur(),_joueur).estVide()&&GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide();
        }
        return GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide();
    }
    /**Retourne vrai si et seulement si le joueur ne peut pas fournir la couleur donnee et peut couper avec un atout*/
    static boolean peutCouper(Suit _couleur, byte _numero, EnumMap<Suit, CustList<HandBelote>> _cartesPossibles, Suit _couleurAtout) {
        return GameBeloteCommon.hand(_cartesPossibles,_couleur,_numero).estVide()&&!GameBeloteCommon.hand(_cartesPossibles,_couleurAtout,_numero).estVide();
    }

    Bytes joueursNAyantPasJoue(byte _numero) {
        Bytes joueursNAyantPasJoue_ = new Bytes();
        byte nombreJoueurs_ = teamsRelation.getNombreDeJoueurs();
        Bytes virtualPl_ = new Bytes();
        virtualPl_.addAllElts(doneTrickInfo.getProgressingTrick().playersHavingPlayed(nombreJoueurs_));
        virtualPl_.add(_numero);
        joueursNAyantPasJoue_.addAllElts(GameBeloteTeamsRelation.autresJoueurs(virtualPl_,nombreJoueurs_));
        return joueursNAyantPasJoue_;
    }

    CustList<TrickBelote> unionPlis() {
        CustList<TrickBelote> unionPlis_ = new CustList<TrickBelote>();
        unionPlis_.addAllElts(doneTrickInfo.getTricks());
        return unionPlis_;
    }
    private Suit couleurAtout() {
        return bid.getCouleur();
    }

    private boolean sousCoupeObligatoireAdversaire() {
        return teamsRelation.getRules().getSousCoupeAdv();
    }
}
