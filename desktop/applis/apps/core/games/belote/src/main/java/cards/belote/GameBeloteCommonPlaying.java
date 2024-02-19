package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.CardChar;
import cards.consts.Hypothesis;
import cards.consts.Suit;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class GameBeloteCommonPlaying {
    private final GameBeloteTrickInfo doneTrickInfo;
    private final GameBeloteTeamsRelation teamsRelation;
    private final BidBeloteSuit bid;
    private ReasonPlayBelote reason = ReasonPlayBelote.NOTHING;

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
        IdMap<Suit,HandBelote> repartition_ = _cartes.couleurs(bid);
        Bytes joueursNonJoue_ = joueursNAyantPasJoue(nextPlayer_);
        CustList<TrickBelote> plisFaits_ = doneTrickInfo.getTricks();
        HandBelote cartesJouees_ = doneTrickInfo.cartesJouees();
        IdMap<Suit,HandBelote> repartitionCartesJouees_ = cartesJouees_.couleurs(bid);
        Bytes joueursJoue_ = GameBeloteTeamsRelation.autresJoueurs(joueursNonJoue_,nbPlayers_);
        joueursJoue_.removeObj(nextPlayer_);
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = doneTrickInfo.cartesPossibles(_cartes);

        IdMap<Hypothesis,IdMap<Suit,CustList<HandBelote>>> hypotheses_ = doneTrickInfo.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        byte ramasseurVirtuel_ = prog_.getRamasseurPliEnCours(nbPlayers_, bid);
        IdMap<Suit,CustList<HandBelote>> suitesTouteCouleur_ = _cartes.eclaterTout(repartitionCartesJouees_, bid);

        boolean maitreAtout_ = strictMaitreAtout(bid, cartesPossibles_,nextPlayer_,GameBeloteCommon.suite(suitesTouteCouleur_, bid.getSuit()),repartitionCartesJouees_);
        IdList<Suit> couleursMaitresses_ = couleursMaitres(
                bid, suitesTouteCouleur_, repartitionCartesJouees_, cartesPossibles_,nextPlayer_);
        IdMap<Suit,HandBelote> cartesMaitresses_ = GameBeloteCommon.cartesMaitresses(
                repartition_, repartitionCartesJouees_, bid);
        boolean maitreJeu_ = maitreAtout_ && couleursMaitresses_.size() == couleursNonAtouts().size();

        IdList<Suit> strSuits_ = strictCouleursMaitres(bid, suitesTouteCouleur_, repartitionCartesJouees_, cartesPossibles_, nextPlayer_);
        BeloteInfoPliEnCours info_ = new BeloteInfoPliEnCours();
        info_.setContrat(bid);
        info_.setProgressingTrick(prog_);
        info_.setNbPlayers(teamsRelation.getNombreDeJoueurs());
        info_.setNextPlayer(nextPlayer_);
        info_.setJoueursConfiance(teamsRelation.partenaires(nextPlayer_));
        info_.setJoueursNonConfiance(teamsRelation.adversaires(nextPlayer_));
        info_.setJoueursNonJoue(joueursNonJoue_);
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
    HandBelote playableCards(IdMap<Suit,HandBelote> _repartitionMain) {
        /*Ensemble des cartes jouees sur ce pli*/
        byte nbPlayers_ = teamsRelation.getNombreDeJoueurs();
        TrickBelote pr_ = doneTrickInfo.getProgressingTrick();
        byte numero_=pr_.getNextPlayer(nbPlayers_);
        HandBelote m= pr_.getCartes();
        if(pr_.estVide()) {
            //L'entame est libre a la belote
            return HandBelote.reunion(_repartitionMain);
        }
        if (!bid.getCouleurDominante()) {
            return noDomSuit(_repartitionMain, nbPlayers_, pr_);
        }
        Suit couleurDemandee_ = pr_.couleurDemandee();
        Suit couleurAtout_ = couleurAtout();
        byte ramasseurVirtuel_ = pr_.getRamasseurPliEnCours(nbPlayers_, bid);
        HandBelote leadingSuit_ = GameBeloteCommon.hand(_repartitionMain, couleurDemandee_);
        HandBelote trumps_ = GameBeloteCommon.hand(_repartitionMain, couleurAtout_);
        byte valeurForte_ = pr_.carteDuJoueur(ramasseurVirtuel_, nbPlayers_).strength(couleurDemandee_, bid);
        if (couleurAtout_ == couleurDemandee_) {
            //Nombre d'atouts dans la main du joueur
            return domSuit(_repartitionMain, couleurDemandee_, trumps_, valeurForte_);
        }
        if (!leadingSuit_.estVide()) {
            reason = ReasonPlayBelote.FOLLOW_SUIT;
            return leadingSuit_;
        }
        if (trumps_.estVide()) {
            return HandBelote.reunion(_repartitionMain);
        }
        if (teamsRelation.memeEquipe(ramasseurVirtuel_, numero_)) {
            /*Le partenaire est maitre temporairement*/
            return atLeastOneTrumpSameTeam(_repartitionMain, couleurDemandee_, trumps_, valeurForte_);
        }
        HandBelote trumpsTrick_ = GameBeloteCommon.hand(m.couleurs(bid), couleurAtout_);
        if (trumpsTrick_.estVide()) {
            /*PliBelote non coupe*/
            reason = ReasonPlayBelote.TR_TRICK;
            return trumps_;
        }
        /*PliBelote coupe par un adversaire*/
        if (trumps_.derniereCarte().strength(couleurDemandee_, bid) > valeurForte_) {
            reason = ReasonPlayBelote.OVER_TR_TRICK;
            return trumps_;
        }
        if (trumps_.premiereCarte().strength(couleurDemandee_, bid) > valeurForte_) {
            HandBelote gr_ = greaterCards(couleurDemandee_, trumps_, valeurForte_);
            reason = ReasonPlayBelote.OVER_TR_TRICK;
            return gr_;
        }
        if (!sousCoupeObligatoireAdversaire()) {
            return HandBelote.reunion(_repartitionMain);
        }
        reason = ReasonPlayBelote.UNDER_TR_TRICK;
        return trumps_;
    }

    private HandBelote atLeastOneTrumpSameTeam(IdMap<Suit, HandBelote> _repartitionMain, Suit _couleurDemandee, HandBelote _trumps, byte _valeurForte) {
        if (teamsRelation.surCoupeObligatoirePartenaire()) {
            if (teamsRelation.sousCoupeObligatoirePartenaire() && _trumps.premiereCarte().strength(_couleurDemandee, bid) < _valeurForte) {
                reason = ReasonPlayBelote.UNDER_PART;
                return _trumps;
            }
            if (_trumps.derniereCarte().strength(_couleurDemandee, bid) > _valeurForte) {
                reason = ReasonPlayBelote.OVER_PART;
                return _trumps;
            }
            if (_trumps.premiereCarte().strength(_couleurDemandee, bid) > _valeurForte) {
                HandBelote gr_ = greaterCards(_couleurDemandee, _trumps, _valeurForte);
                reason = ReasonPlayBelote.OVER_PART;
                return gr_;
            }
//                    (!sousCoupeObligatoirePartenaire()||
//                    main(repartitionMain,couleurAtout).premiereCarte().force(couleurDemandee,contrat)>valeurForte)
            //main(repartitionMain,couleurAtout).premiereCarte().force(couleurDemandee,contrat)<valeurForte
            //!sousCoupeObligatoirePartenaire() && main(repartitionMain,couleurAtout).premiereCarte().force(couleurDemandee,contrat)<valeurForte
        }
        if (teamsRelation.sousCoupeObligatoirePartenaire() && _trumps.premiereCarte().strength(_couleurDemandee, bid) < _valeurForte) {
            reason = ReasonPlayBelote.UNDER_PART;
            return _trumps;
        }
        return HandBelote.reunion(_repartitionMain);
    }

    private HandBelote noDomSuit(IdMap<Suit, HandBelote> _repartitionMain, byte _nbPlayers, TrickBelote _pr) {
        Suit couleurDemandee_ = _pr.couleurDemandee();
        HandBelote leadingSuit_ = GameBeloteCommon.hand(_repartitionMain, couleurDemandee_);
        if (bid.ordreCouleur()) {
            if (leadingSuit_.estVide()) {
                return HandBelote.reunion(_repartitionMain);
            }
            reason = ReasonPlayBelote.FOLLOW_SUIT;
            return leadingSuit_;
        }
        if (leadingSuit_.estVide()) {
            return HandBelote.reunion(_repartitionMain);
        }
        byte valeurForte_ = _pr.carteDuJoueur(_pr.getRamasseurPliEnCours(_nbPlayers, bid), _nbPlayers).strength(couleurDemandee_, bid);
        if (leadingSuit_.derniereCarte().strength(couleurDemandee_, bid) > valeurForte_
                || leadingSuit_.premiereCarte().strength(couleurDemandee_, bid) < valeurForte_) {
            reason = ReasonPlayBelote.FOLLOW_SUIT;
            return leadingSuit_;
        }
        return greaterCards(couleurDemandee_, leadingSuit_, valeurForte_);
    }

    private HandBelote domSuit(IdMap<Suit, HandBelote> _repartitionMain, Suit _couleurDemandee, HandBelote _trumps, byte _valeurForte) {
        if (_trumps.estVide()) {
            return HandBelote.reunion(_repartitionMain);
        }
        if (_trumps.derniereCarte().strength(_couleurDemandee, bid) > _valeurForte || _trumps.premiereCarte().strength(_couleurDemandee, bid) < _valeurForte) {
            reason = ReasonPlayBelote.FOLLOW_SUIT;
            return _trumps;
        }
        return greaterCards(_couleurDemandee, _trumps, _valeurForte);
    }

    private HandBelote greaterCards(Suit _couleurDemandee, HandBelote _trumps, byte _valeurForte) {
        HandBelote cartesJouables_=new HandBelote();
        byte indexTrump_ = IndexConstants.FIRST_INDEX;
        while (_trumps.carte(indexTrump_).strength(_couleurDemandee, bid)> _valeurForte) {
            cartesJouables_.ajouter(_trumps.carte(indexTrump_));
            indexTrump_++;
        }
        reason = ReasonPlayBelote.FOLLOW_TR_GREATER;
        return cartesJouables_;
    }

    public ReasonPlayBelote getReason() {
        return reason;
    }

    static CardBelote carteMaitresse(BidBeloteSuit _bid,
                                     IdList<Suit> _couleurs,
                                     IdMap<Suit, HandBelote> _cartesMaitresses,
                                     HandBelote _repartition,
                                     HandBelote _repartitionCartesJouees) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_cartesMaitresses, _couleurs);
        //maitre
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartitionCartesJouees.couleurs(_bid), couleurs_);
        //jouees
        couleurs_ = GameBeloteCommon.couleursLesPlusLongues(_repartition.couleurs(_bid), couleurs_);
        //longues
        return GameBeloteCommon.hand(_repartition.couleurs(_bid),couleurs_.first()).premiereCarte();
    }

    static boolean strictMaitreAtout(BidBeloteSuit _bid, IdMap<Suit, CustList<HandBelote>> _cartesPossibles, byte _numero, CustList<HandBelote> _suites, IdMap<Suit, HandBelote> _cartesJouees) {
        if (!_bid.getCouleurDominante()) {
            return true;
        }
        Suit couleurAtout_= _bid.getSuit();
        int max_= getNbMaxPossPlayerCards(_cartesPossibles,_numero,couleurAtout_);
        /*max designe le nombre maximal de cartes probablement possedees par un joueur*/
        if(GameBeloteCommon.hand(_cartesJouees,couleurAtout_).total()==HandBelote.couleurComplete(couleurAtout_, _bid).total()) {
            return true;
        }
        if(_suites.isEmpty()) {
            return false;
        }
        int lead_ = getNbLeadingCards(_bid, _cartesJouees, couleurAtout_, _suites.first());
        return lead_>=max_;
    }
    static IdList<Suit> couleursMaitres(BidBeloteSuit _bid,
                                          IdMap<Suit, CustList<HandBelote>> _suites,
                                          IdMap<Suit, HandBelote> _cartesJouees,
                                          IdMap<Suit, CustList<HandBelote>> _cartesPossibles, byte _numero) {
        IdList<Suit> couleurs_ = strictCouleursMaitres(_bid, _suites, _cartesJouees, _cartesPossibles, _numero);
        for (Suit couleur_ : couleursNonAtouts(_bid)) {
            if (_suites.getVal(couleur_).isEmpty()) {
                couleurs_.add(couleur_);
            }
        }
        couleurs_.removeDuplicates();
        return couleurs_;
    }

    static IdList<Suit> strictCouleursMaitres(BidBeloteSuit _bid, IdMap<Suit, CustList<HandBelote>> _suites,
                                                IdMap<Suit, HandBelote> _cartesJouees,
                                                IdMap<Suit, CustList<HandBelote>> _cartesPossibles, byte _numero) {
        IdList<Suit> couleurs_=new IdList<Suit>();
        if (_bid.getCouleurDominante()) {
            for(Suit couleur_:couleursNonAtouts(_bid)) {
                addNormalSuit(_bid, _suites, _cartesJouees, _cartesPossibles, _numero, couleurs_, couleur_);
            }
            return couleurs_;
        }
        if(_bid.ordreCouleur()) {
            for(Suit couleur_:GameBeloteCommon.couleurs()) {
                addNormalSuit(_bid, _suites, _cartesJouees, _cartesPossibles, _numero, couleurs_, couleur_);
            }
        } else {
            for(Suit couleur_:GameBeloteCommon.couleurs()) {
                if(completelyPlayedSuit(_cartesJouees, couleur_, _bid)) {
                    couleurs_.add(couleur_);
                } else if(!GameBeloteCommon.suite(_suites,couleur_).isEmpty()) {
                    int maitres_ = getNbLeadingTrumpCards(_bid, _suites,
                            _cartesJouees, couleur_);
                    addSuit(_suites, _cartesPossibles, _numero, couleurs_, couleur_, maitres_);
                }
            }
        }
        return couleurs_;
    }

    private static void addNormalSuit(BidBeloteSuit _bid, IdMap<Suit, CustList<HandBelote>> _suites,
                                      IdMap<Suit, HandBelote> _cartesJouees, IdMap<Suit, CustList<HandBelote>> _cartesPossibles,
                                      byte _numero, IdList<Suit> _couleurs, Suit _couleur) {
        if(completelyPlayedSuit(_cartesJouees, _couleur, _bid)) {
            _couleurs.add(_couleur);
        } else if(!GameBeloteCommon.suite(_suites, _couleur).isEmpty()) {
            int maitres_ = getNbLeadingSuitCards(_bid, _suites, _cartesJouees,
                    _couleur);
            addSuit(_suites, _cartesPossibles, _numero, _couleurs, _couleur, maitres_);
        }
    }

    private static void addSuit(IdMap<Suit, CustList<HandBelote>> _suites,
                                IdMap<Suit, CustList<HandBelote>> _cartesPossibles, byte _numero,
                                IdList<Suit> _couleurs, Suit _couleur, int _maitres) {
        int max_ = getNbMaxPossPlayerCards(_cartesPossibles,
                _numero, _couleur);
        if(_maitres >=max_|| _maitres > IndexConstants.SIZE_EMPTY&&GameBeloteCommon.suite(_suites, _couleur).size()== IndexConstants.ONE_ELEMENT) {
            _couleurs.add(_couleur);
        }
    }

    IdList<Suit> couleursNonAtouts() {
        return couleursNonAtouts(bid);
    }
    static IdList<Suit> couleursNonAtouts(BidBeloteSuit _bid) {
        IdList<Suit> couleursNonAtouts_=new IdList<Suit>();
        if(_bid.getCouleurDominante()) {
            for(Suit couleur_:GameBeloteCommon.couleurs()) {
                if(couleur_!= _bid.getSuit()) {
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
            IdMap<Suit, CustList<HandBelote>> _possibleCards, byte _player, Suit _suit) {
        int max_= IndexConstants.SIZE_EMPTY;
        /*max designe le nombre maximal de cartes probablement possedees par un joueur a une couleur donnee*/
        CustList<HandBelote> poss_ = _possibleCards.getVal(_suit);
        int nbPls_ = poss_.size();
        for(int joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbPls_; joueur_++) {
            if(joueur_!=_player) {
                max_= NumberUtil.max(poss_.get(joueur_).total(),max_);
            }
        }
        return max_;
    }

    static int getNbLeadingTrumpCards(BidBeloteSuit _bid, IdMap<Suit, CustList<HandBelote>> _seqs,
                                      IdMap<Suit, HandBelote> _playedCards, Suit _suit) {
        return getNbLeadingCards(_bid,_seqs,_playedCards,_suit);
    }
    static int getNbLeadingSuitCards(BidBeloteSuit _bid, IdMap<Suit, CustList<HandBelote>> _seqs,
                                     IdMap<Suit, HandBelote> _playedCards, Suit _suit) {
        return getNbLeadingCards(_bid,_seqs,_playedCards,_suit);
    }
    private static int getNbLeadingCards(BidBeloteSuit _bid, IdMap<Suit, CustList<HandBelote>> _seqs,
                                         IdMap<Suit, HandBelote> _playedCards, Suit _suit) {
        HandBelote s_ = GameBeloteCommon.hand(_seqs, _suit, IndexConstants.FIRST_INDEX);
        return getNbLeadingCards(_bid, _playedCards, _suit, s_);
    }

    private static int getNbLeadingCards(BidBeloteSuit _bid, IdMap<Suit, HandBelote> _playedCards, Suit _suit, HandBelote _s) {
        CardBelote c= _s.premiereCarte();
        for(CardBelote carte_:cartes(_suit, _bid)) {
            if(carte_.strength(_suit, _bid)<c.strength(_suit, _bid)) {
                break;
            }
            if(!GameBeloteCommon.hand(_playedCards, _suit).contient(carte_)&&!_s.contient(carte_)) {
                return IndexConstants.SIZE_EMPTY;
            }
        }
        return _s.total();
    }

    private static boolean completelyPlayedSuit(IdMap<Suit, HandBelote> _playedCards,
                                                Suit _suit, BidBeloteSuit _bid) {
        return GameBeloteCommon.hand(_playedCards,_suit).total()==HandBelote.couleurComplete(_suit, _bid).total();
    }
//    static HandBelote cartesAtouts(Suit _couleur, BidBeloteSuit _bid) {
//        return HandBelote.couleurComplete(_couleur,_bid);
//    }
//    static HandBelote cartesCouleurs(Suit _couleur) {
//        return HandBelote.couleurComplete(_couleur,Order.SUIT);
//    }
    static HandBelote cartes(Suit _couleur, BidBeloteSuit _or) {
        return HandBelote.couleurComplete(_couleur, _or);
    }
    static HandBelote cartesBeloteRebelote(BidBeloteSuit _bid) {
        HandBelote cartes_ = new HandBelote();
        for(CardBelote c: GameBeloteCommonPlaying.cartes(_bid.getSuit(),_bid)) {
            if(c.getId().getNomFigure() == CardChar.KING) {
                cartes_.ajouter(c);
            }
            if(c.getId().getNomFigure() == CardChar.QUEEN) {
                cartes_.ajouter(c);
            }
        }
        return cartes_;
    }

    static Bytes joueursSusceptiblesCoupe(
            IdMap<Suit,CustList<HandBelote>> _cartesPossibles,
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

    static IdList<Suit> couleursSansCarteMaitresse(HandBelote _main,
                                                             HandBelote _cartesJouees, BidBeloteSuit _contrat, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdMap<Suit,HandBelote> couleursMains_ = _main.couleurs(_contrat);
        IdMap<Suit,HandBelote> cartesJouees_ = _cartesJouees.couleurs(_contrat);
        for (Suit couleur_ : _couleurs) {
            HandBelote cartesMaitresses_ = GameBeloteCommon.cartesMaitresses(couleursMains_,
                    cartesJouees_,_contrat).getVal(couleur_);
            if (cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    static IdList<Suit> couleursNonEntamees(
            CustList<TrickBelote> _plis, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdList<Suit> couleursOuvertes_ = new IdList<Suit>();
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
    static IdList<Suit> couleursNonOuvertesNonVides(HandBelote _main,
                                                              CustList<TrickBelote> _plis, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = GameBeloteCommon.couleursNonAtoutAyantNbCartesSupEg(_main, _couleurs, 1);
        return couleursNonEntamees(_plis,couleurs_);
    }

    static IdList<Suit> couleursOuvertes(
            CustList<TrickBelote> _plis, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdList<Suit> couleursOuvertes_ = new IdList<Suit>();
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


    static IdList<Suit> couleursCoupeePar(byte _joueur, BidBeloteSuit _contrat, IdMap<Suit, CustList<HandBelote>> _cartesPossibles,
                                            IdMap<Suit, CustList<HandBelote>> _cartesCertaines, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        if (!_cartesCertaines.getVal(_contrat.getSuit()).get(_joueur).estVide()) {
            for (Suit couleur_ : _couleurs) {
                if (!_cartesPossibles.getVal(couleur_).get(_joueur).estVide()) {
                    continue;
                }
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    static IdList<Suit> couleursPouvantEtreCoupees(
            Bytes _joueurs,
            IdMap<Suit,CustList<HandBelote>> _cartesPossibles,
            Suit _couleurAtout, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if(joueursSusceptiblesCoupe(_cartesPossibles, couleur_, _couleurAtout, _joueurs).isEmpty()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    static IdList<Suit> couleursDefausseeParJoueurs(
            Bytes _joueurs, BidBeloteSuit _contrat, IdMap<Suit,CustList<HandBelote>> _cartesPossibles,
            IdList<Suit> _couleurs) {
        IdList<Suit> couleursDefausses_ = new IdList<Suit>();

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
    static IdList<Suit> couleursNonCoupeeParJoueurs(Bytes _joueurs, BidBeloteSuit _contrat, IdMap<Suit, CustList<HandBelote>> _cartesPossibles,
                                                      IdMap<Suit, CustList<HandBelote>> _cartesCertaines, IdList<Suit> _couleurs) {
        IdList<Suit> couleursCoupees_ = new IdList<Suit>();

        for (byte joueur_ : _joueurs) {
            IdList<Suit> couleursLoc_ = couleursCoupeePar(
                    joueur_, _contrat, _cartesPossibles,
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
    
    private static boolean defausse(Suit _couleur, byte _joueur,
                                    IdMap<Suit, CustList<HandBelote>> _cartesPossibles, BidBeloteSuit _contrat) {
        if(_contrat.getCouleurDominante()) {
            return GameBeloteCommon.hand(_cartesPossibles, _contrat.getSuit(),_joueur).estVide()&&GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide();
        }
        return GameBeloteCommon.hand(_cartesPossibles,_couleur,_joueur).estVide();
    }
    /**Retourne vrai si et seulement si le joueur ne peut pas fournir la couleur donnee et peut couper avec un atout*/
    static boolean peutCouper(Suit _couleur, byte _numero, IdMap<Suit, CustList<HandBelote>> _cartesPossibles, Suit _couleurAtout) {
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

    private Suit couleurAtout() {
        return bid.getSuit();
    }

    private boolean sousCoupeObligatoireAdversaire() {
        return teamsRelation.getRules().getSousCoupeAdv();
    }
}
