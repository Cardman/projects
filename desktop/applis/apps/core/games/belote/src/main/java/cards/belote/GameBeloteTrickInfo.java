package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.Hypothesis;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.*;
import code.util.core.IndexConstants;

public final class GameBeloteTrickInfo {

    private TrickBelote progressingTrick;
    private CustList<TrickBelote> tricks;
    private CustList<DeclareHandBelote> declares;
    private CustList<HandBelote> declaresBeloteRebelote;
    private BidBeloteSuit bid;
    private Ints handLengths;

    private byte nbPlayers;
    private byte taker;
    private HandBelote lastSeenHand = new HandBelote();
    private GameBeloteTeamsRelation relations;
    private RulesBelote rules;

    public GameBeloteTrickInfo(TrickBelote _progressingTrick, CustList<TrickBelote> _tricks,
                               CustList<DeclareHandBelote> _declares,
                               CustList<HandBelote> _declaresBeloteRebelote,
                              BidBeloteSuit _bid,
                              Ints _handLengths) {
        progressingTrick = _progressingTrick;
        tricks = _tricks;
        declares = _declares;
        declaresBeloteRebelote = _declaresBeloteRebelote;
        bid = _bid;
        handLengths = _handLengths;
    }

    void addSeenDeck(HandBelote _h, GameBeloteTeamsRelation _rel) {
        rules = _rel.getRules();
        relations = _rel;
        nbPlayers = _rel.getNombreDeJoueurs();
        taker = _rel.getTaker();
        if (!_h.estVide()) {
            lastSeenHand.ajouter(_h.premiereCarte());
        }
    }
    /**
     Retourne l'ensemble des cartes certainement possedees par les joueurs
     classees par couleur puis par joueurs
     @param cartesPossibles
     l'ensemble des cartes probablement possedees par les joueurs
     ou a l'ecart (visible uniquement pour un preneur ayant demande
     petite ou garde ou partiellement lorsque des atouts sont
     ecartes) Cet ensemble peut etre reduit apres appel de methode
     @return l'ensemble des cartes dont on connait par deduction la main
     */
    public EnumMap<Hypothesis,EnumMap<Suit,CustList<HandBelote>>> cartesCertaines(
            EnumMap<Suit, CustList<HandBelote>> _cartesPossibles) {
        Bytes joueursRepartitionConnue_ = new Bytes();
        Bytes joueursRepartitionConnue2_ = new Bytes();
        Bytes joueursRepartitionConnueMemo_ = new Bytes();
        Bytes joueursRepartitionInconnue_ = new Bytes();
        EnumMap<Suit,CustList<HandBelote>> cartesCertaines_ = new EnumMap<Suit,CustList<HandBelote>>();
        EnumMap<Suit,CustList<HandBelote>> cartesPossibles_ = new EnumMap<Suit,CustList<HandBelote>>(
                _cartesPossibles);
        EnumMap<Hypothesis,EnumMap<Suit,CustList<HandBelote>>> retour_ = new EnumMap<Hypothesis,EnumMap<Suit,CustList<HandBelote>>>();
        int nombreDApparitionCarte_;
        /*
        Indique le nombre de mains pour les
        cartes possibles ou apparait la carte
        */
        EnumList<Suit> toutesCouleurs_ = GameBeloteCommon.couleurs();
        for(Suit couleur_: toutesCouleurs_) {
            cartesCertaines_.put(couleur_,new CustList<HandBelote>());
        }
        for (Suit couleur_:cartesCertaines_.getKeys()) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                cartesCertaines_.getVal(couleur_).add(new HandBelote());
            }
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            addToKnown(toutesCouleurs_,cartesPossibles_,joueur_,cartesCertaines_,joueursRepartitionConnue_,joueursRepartitionConnueMemo_);
        }
        while (!joueursRepartitionConnue_.isEmpty()) {
            /*
        Tant qu'on arrive a
        deduire la
        repartition exacte
        des joueurs on boucle
        sur l'ensemble des
        joueurs dont la
        repartition vient
        juste d'etre connue
        pour eliminer les
        cartes impossibles
        d'etre possedees par
        les joueurs
        */
            for (byte joueur_ : joueursRepartitionConnue_) {
                for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nbPlayers; joueur2_++) {
                    if (!joueursRepartitionConnueMemo_.containsObj(joueur2_)) {
                        for (Suit couleur_:toutesCouleurs_) {
                            cartesPossibles_.getVal(couleur_)
                                    .get(joueur2_).supprimerCartes(
                                    cartesCertaines_.getVal(couleur_).get(
                                            joueur_));
                        }
                    }
                    addToKnown(toutesCouleurs_,cartesPossibles_,joueur_,cartesCertaines_,joueursRepartitionConnue2_,joueursRepartitionConnueMemo_);
                }
            }
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                if (!joueursRepartitionConnueMemo_.containsObj(joueur_)) {
                    joueursRepartitionInconnue_.add(joueur_);
                }
            }
            for (byte joueur_ : joueursRepartitionInconnue_) {
                for (Suit couleur_:toutesCouleurs_) {
                    for (CardBelote carte_ : cartesPossibles_.getVal(couleur_).get(
                            joueur_)) {
                        nombreDApparitionCarte_ = 0;
                        for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nbPlayers; joueur2_++) {
                            if (cartesPossibles_.getVal(couleur_).get(joueur2_)
                                    .contient(carte_)) {
                                nombreDApparitionCarte_++;
                            }
                        }
                        if (nombreDApparitionCarte_ == 1) {
                            cartesCertaines_.getVal(couleur_).get(joueur_).removeCardIfPresent(carte_);
                            cartesCertaines_.getVal(couleur_).get(joueur_)
                                    .ajouter(carte_);
                        }
                    }
                }
                addToKnown(toutesCouleurs_,cartesCertaines_,joueur_,cartesPossibles_,joueursRepartitionConnue2_,joueursRepartitionConnueMemo_);
            }
            joueursRepartitionInconnue_.clear();
            joueursRepartitionConnue_.clear();
            joueursRepartitionConnue_.addAllElts(joueursRepartitionConnue2_);
            joueursRepartitionConnue2_.clear();
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            if (!joueursRepartitionConnueMemo_.containsObj(joueur_)) {
                joueursRepartitionInconnue_.add(joueur_);
            }
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            sortSuits(cartesPossibles_, joueur_);
            sortSuits(cartesCertaines_, joueur_);
        }
        retour_.put(Hypothesis.POSSIBLE, cartesPossibles_);
        retour_.put(Hypothesis.SURE, cartesCertaines_);
        return retour_;
    }

    void sortSuits(EnumMap<Suit, CustList<HandBelote>> _reps, byte _player) {
        for(Suit couleur_:GameBeloteCommon.couleurs()) {
            if(bid.getCouleurDominante()) {
                if(couleur_!=bid.getCouleur()) {
                    GameBeloteCommon.hand(_reps,couleur_, _player).setOrdre(Order.SUIT);
                }
            } else if(bid.ordreCouleur()) {
                GameBeloteCommon.hand(_reps,couleur_, _player).setOrdre(Order.SUIT);
            }
            GameBeloteCommon.hand(_reps,couleur_, _player).trierUnicolore(true);
        }
    }

    void addToKnown(EnumList<Suit> _all,EnumMap<Suit,CustList<HandBelote>> _poss,byte _player,
                    EnumMap<Suit,CustList<HandBelote>> _sure,
                    Bytes _joueursRepartitionConnue, Bytes _joueursRepartitionConnueMemo) {
        int nombreCartesPossiblesJoueur_ = 0;
        for (Suit couleur_: _all) {
            nombreCartesPossiblesJoueur_ += _poss.getVal(couleur_)
                    .get(_player).total();
        }
        if (nombreCartesPossiblesJoueur_ ==handLengths
                .get(_player) && !_joueursRepartitionConnueMemo.containsObj(_player)) {
                /*
                    L'ensemble des cartes d'un joueur
                    reellement possedees est inclus
                    dans l'ensemble des cartes
                    probablement possedees par ce
                    joueur
                    */
            affect(_all,_poss,_player,_sure);
            _joueursRepartitionConnue.add(_player);
            _joueursRepartitionConnueMemo.add(_player);
        }
    }
    static void affect(EnumList<Suit> _all,EnumMap<Suit,CustList<HandBelote>> _from, byte _player,EnumMap<Suit,CustList<HandBelote>> _to) {
        for (Suit s: _all) {
            _to.getVal(s).get(_player).supprimerCartes();
            _to.getVal(s).get(_player).ajouterCartes(_from.getVal(s).get(_player));
        }
    }

    public EnumMap<Suit,CustList<HandBelote>> cartesPossibles(
            HandBelote _curHand) {
        EnumMap<Suit,CustList<HandBelote>> m=new EnumMap<Suit,CustList<HandBelote>>();
        for(Suit couleur_:GameBeloteCommon.couleurs()) {
            //On fait une boucle sur les couleurs autres que l'atout
            if(bid.getCouleur()!=couleur_&&!bid.ordreAtout()) {
                m.put(couleur_, cartesPossibles(couleur_, _curHand));
            } else {
                m.put(couleur_,atoutsPossibles(couleur_,_curHand));
            }
        }
        return m;
    }
    /**Retourne l'ensemble des atouts probablement possedes par les autres joueurs
     @param numero*/
    CustList<HandBelote> atoutsPossibles(
            Suit _couleurAtout,
            HandBelote _curHand) {
        CustList<HandBelote> m = atoutsPossiblesRegles(_couleurAtout,_curHand);
        byte joueur_ = 0;
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        for (HandBelote main_ : m) {
            if (main_.estVide()) {
                joueur_++;
                continue;
            }
            if (joueur_ == next_) {
                joueur_++;
                continue;
            }
            HandBelote atoutsFiltres_ = sousCoupeBelote(_couleurAtout, _curHand,joueur_,
                    main_);
            m.set(joueur_, atoutsFiltres_);
            joueur_++;
        }
        return m;
    }
    CustList<HandBelote> atoutsPossiblesRegles(
            Suit _couleurAtout,
            HandBelote _curHand) {
        EnumMap<Suit,HandBelote> repartition_ = _curHand.couleurs(bid);
        HandBelote trumps_ = GameBeloteCommon.hand(repartition_,_couleurAtout);
        HandBelote cartesJouees_ = cartesJouees();
        cartesJouees_.ajouterCartes(progressingTrick.getCartes());
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        CustList<HandBelote> m=new CustList<HandBelote>();
        boolean defausse_;
        for (byte j = IndexConstants.FIRST_INDEX; j<nbPlayers; j++) {
            HandBelote h_ = new HandBelote();
            m.add(h_);
            if (j == next_) {
                h_.ajouterCartes(trumps_);
                continue;
            }
            for(CardBelote carte_:GameBeloteCommonPlaying.cartesAtouts(_couleurAtout)) {
                if(!cartesJouees_.contient(carte_)&&!trumps_.contient(carte_)) {
                    if (lastSeenHand.estVide()) {
                        h_.ajouter(carte_);
                    } else if(!CardBelote.eq(lastSeenHand.premiereCarte(), carte_)||j==taker) {
                        h_.ajouter(carte_);
                    }
                }
            }
            if(bid.getCouleurDominante()) {
                defausse_=false;
                for(Suit couleur_:GameBeloteCommon.couleurs()) {
                    if (defausseBelote(couleur_,j,tricks)) {
                        defausse_ = true;
                    }
                }
                if(defausse_) {
                    //Les joueurs se defaussant sur atout ou couleur demandee ne peuvent pas avoir de l'atout
                    h_.supprimerCartes();
                }
            }
            if(neFournitPas(_couleurAtout, j, tricks)) {
                h_.supprimerCartes();
            }
        }
        if(bid.getCouleurDominante()) {
            boolean surCoupeObligPart_=relations.surCoupeObligatoirePartenaire();
            boolean sousCoupeObligPart_=relations.sousCoupeObligatoirePartenaire();
            boolean sousCoupeObligAdv_=relations.sousCoupeObligatoireAdversaire();
            HandBelote cartesAnnonces_ = GameBeloteCommonPlaying.cartesBeloteRebelote(bid);
            for(TrickBelote p: tricks) {
                for(CardBelote c: p) {
                    byte joueur_ = p.joueurAyantJoue(c);
                    if(!declaresBeloteRebelote.get(joueur_).contient(c)) {
                        continue;
                    }
                    for (byte j = IndexConstants.FIRST_INDEX; j<nbPlayers; j++) {
                        if(j == next_) {
                            continue;
                        }
                        if(j == joueur_) {
                            continue;
                        }
                        //Les autres joueurs ne peuvent pas posseder les cartes a annoncer par unicite
                        m.get(j).supprimerCartes(cartesAnnonces_);
                    }
                }
            }
            CustList<TrickBelote> plis_ = new CustList<TrickBelote>(tricks);
            plis_.add(progressingTrick);
            for(TrickBelote pli_:plis_) {
                Suit couleurDemande_=pli_.couleurDemandee();
                if(couleurDemande_==_couleurAtout) {
                    continue;
                }
                for(CardBelote c: pli_) {
                    if(c.couleur() == couleurDemande_) {
                        continue;
                    }
                    byte joueur_ = pli_.joueurAyantJoue(c,nbPlayers);
                    if(joueur_ == next_) {
                        continue;
                    }
                    Bytes joueursAvant_ = pli_.joueursAyantJoueAvant(joueur_,rules.getRepartition());
                    byte forceLoc_ = c.strength(couleurDemande_, bid);
                    byte max_ = 0;
                    byte ramasseurVirtuel_ = joueur_;
                    if(c.couleur() == _couleurAtout) {
                        //coupe
                        //joueursAvant non vide
                        for(byte j: joueursAvant_) {
                            CardBelote carte_ = pli_.carteDuJoueur(j,nbPlayers);
                            byte forceLoc2_ = carte_.strength(couleurDemande_, bid);
                            if(forceLoc2_ < forceLoc_) {
                                continue;
                            }
                            if(forceLoc2_ < max_) {
                                continue;
                            }
                            max_ = forceLoc2_;
                            ramasseurVirtuel_ = j;
                        }
                        if(ramasseurVirtuel_ == joueur_) {
                            continue;
                        }
                        HandBelote cartesExclues_ = new HandBelote();
                        if(relations.memeEquipe(ramasseurVirtuel_, joueur_)) {
                            //max > 0
                            if(sousCoupeObligPart_) {
                                //supprimer les atouts superieurs a celui joue du partenaire
                                for(CardBelote c2_: m.get(joueur_)) {
                                    if(c2_.strength(couleurDemande_, bid) < max_) {
                                        continue;
                                    }
                                    cartesExclues_.ajouter(c2_);
                                }
                            }
                        } else {
                            //max > 0
                            //supprimer les atouts superieurs a celui joue de l'adversaire
                            for(CardBelote c2_: m.get(joueur_)) {
                                if(c2_.strength(couleurDemande_, bid) < max_) {
                                    continue;
                                }
                                cartesExclues_.ajouter(c2_);
                            }
                        }
                        m.get(joueur_).supprimerCartes(cartesExclues_);
                        continue;
                    }
                    //defausse
                    boolean defausseSurAtout_ = false;
                    //joueursAvant non vide
                    for(byte j: joueursAvant_) {
                        CardBelote carte_ = pli_.carteDuJoueur(j,nbPlayers);
                        byte forceLoc2_ = carte_.strength(couleurDemande_, bid);
                        if(forceLoc2_ == 0) {
                            continue;
                        }
                        if(carte_.couleur() == _couleurAtout) {
                            defausseSurAtout_ = true;
                        }
                        if(forceLoc2_ < max_) {
                            continue;
                        }
                        max_ = forceLoc2_;
                        ramasseurVirtuel_ = j;
                    }
                    HandBelote cartesExclues_ = new HandBelote();
                    if(relations.memeEquipe(ramasseurVirtuel_, joueur_)) {
                        if(!defausseSurAtout_) {
                            //defausse sur une carte de couleur ordinaire du partenaire
                            if(surCoupeObligPart_) {
                                m.get(joueur_).supprimerCartes();
                            }
                        } else {
                            //defausse sur un atout du partenaire
                            if(surCoupeObligPart_) {
                                for(CardBelote c2_: m.get(joueur_)) {
                                    if(c2_.strength(couleurDemande_, bid) < max_) {
                                        continue;
                                    }
                                    cartesExclues_.ajouter(c2_);
                                }
                            }
                            if(sousCoupeObligPart_) {
                                filterUnder(m, couleurDemande_, joueur_, max_, cartesExclues_);
                            }
                        }
                        m.get(joueur_).supprimerCartes(cartesExclues_);
                        continue;
                    }
                    //!memeEquipe(ramasseurVirtuel, joueur)
                    if(!defausseSurAtout_) {
                        //defausse sur une carte de couleur ordinaire d'un adversaire
                        m.get(joueur_).supprimerCartes();
                    } else {
                        //defausse sur un atout d'un adversaire
                        for(CardBelote c2_: m.get(joueur_)) {
                            if(c2_.strength(couleurDemande_, bid) < max_) {
                                continue;
                            }
                            cartesExclues_.ajouter(c2_);
                        }
                        if(sousCoupeObligAdv_) {
                            filterUnder(m, couleurDemande_, joueur_, max_, cartesExclues_);
                        }
                    }
                    m.get(joueur_).supprimerCartes(cartesExclues_);
                }
            }
        }
        CustList<TrickBelote> plis_ = new CustList<TrickBelote>(tricks);
        plis_.add(progressingTrick);
        for(TrickBelote pli_:plis_) {
            Suit couleurDemande_=pli_.couleurDemandee();
            if(couleurDemande_!=_couleurAtout) {
                continue;
            }
            //couleurDemande == couleurAtout
            for(CardBelote c: pli_) {
                byte joueur_ = pli_.joueurAyantJoue(c,nbPlayers);
                if(joueur_ == next_) {
                    continue;
                }
                Bytes joueursAvant_ = pli_.joueursAyantJoueAvant(joueur_,rules.getRepartition());
                byte forceLoc_ = c.strength(couleurDemande_, bid);
                byte max_ = 0;
                byte ramasseurVirtuel_ = joueur_;
                //joueursAvant non vide
                for(byte j: joueursAvant_) {
                    CardBelote carte_ = pli_.carteDuJoueur(j,nbPlayers);
                    byte forceLoc2_ = carte_.strength(couleurDemande_, bid);
                    if(forceLoc2_ < forceLoc_) {
                        continue;
                    }
                    if(forceLoc2_ < max_) {
                        continue;
                    }
                    max_ = forceLoc2_;
                    ramasseurVirtuel_ = j;
                }
                if(ramasseurVirtuel_ == joueur_) {
                    continue;
                }
                HandBelote cartesExclues_ = new HandBelote();
                for(CardBelote c2_: m.get(joueur_)) {
                    if(c2_.strength(couleurDemande_, bid) < max_) {
                        continue;
                    }
                    cartesExclues_.ajouter(c2_);
                }
                m.get(joueur_).supprimerCartes(cartesExclues_);
            }

        }
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        if (couleurDemandee_ == _couleurAtout) {
            for (CardBelote c: progressingTrick) {
                Suit couleurJoueur_ = c.couleur();
                if (couleurJoueur_ != _couleurAtout) {
                    byte p_ = progressingTrick.joueurAyantJoue(c, nbPlayers);
                    m.get(p_).supprimerCartes();
                }
            }
        }
        for(DeclareHandBelote a:declares) {
            HandBelote mainCouleur_ = a.getHand().couleurs(bid).getVal(_couleurAtout);
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbPlayers; joueur_++) {
                if(joueur_ == a.getPlayer()) {
                    continue;
                }
                m.get(joueur_).supprimerCartes(mainCouleur_);
            }
        }
        return m;
    }

    private void filterUnder(CustList<HandBelote> _m, Suit _couleurDemande, byte _joueur, byte _max, HandBelote _cartesExclues) {
        for(CardBelote c2_: _m.get(_joueur)) {
            if(c2_.strength(_couleurDemande, bid) > _max) {
                continue;
            }
            _cartesExclues.ajouter(c2_);
        }
    }

    HandBelote joueCartePoint(Suit _couleur, HandBelote _curHand, byte _numero,
                               HandBelote _atoutsPossibles) {
        HandBelote retour_ = new HandBelote();
        retour_.ajouterCartes(_atoutsPossibles);
        HandBelote cartesJouees_ = cartesJouees();
        cartesJouees_.ajouterCartes(progressingTrick.getCartes());
        CustList<TrickBelote> plis_ = new CustList<TrickBelote>();
        for (TrickBelote pli_ : tricks) {
            CardBelote carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.couleur() != _couleur) {
                continue;
            }
            if (carteObservee_.points(bid) == 0) {
                continue;
            }
            byte ramasseur_ = pli_.getRamasseur(bid);
            if (relations.memeEquipe(ramasseur_, _numero)) {
                continue;
            }
            // Plis sous coupes (couleur demandee) ou avec un atout joue en
            // dessous du ramasseur (demande atout)
            plis_.add(pli_);
        }
        HandBelote cartesVues_ = new HandBelote(Order.TRUMP);
        cartesVues_.ajouterCartes(cartesJouees_.couleurs(bid).getVal(_couleur));
        cartesVues_.ajouterCartes(_curHand.couleurs(bid).getVal(_couleur));
        cartesVues_.trierUnicolore(true);
        for (TrickBelote pli_ : plis_) {
            CardBelote carteObservee_ = pli_.carteDuJoueur(_numero);
            Suit couleurDemandee_ = pli_.couleurDemandee();
            HandBelote mainLocale_ = new HandBelote();
            //supprimer les atouts sous la carte observee
            for(CardBelote carte_: HandBelote.couleurComplete(_couleur,Order.SUIT)) {
                if (carte_.strength(couleurDemandee_,bid) < carteObservee_
                        .strength(couleurDemandee_,bid)) {
                    mainLocale_.ajouter(carte_);
                }
            }
            retour_.supprimerCartes(mainLocale_);
        }
        return retour_;
    }
    HandBelote sousCoupeBelote(Suit _couleur, HandBelote _curHand, byte _numero,
                                       HandBelote _atoutsPossibles) {
        HandBelote retour_ = new HandBelote();
        retour_.ajouterCartes(_atoutsPossibles);
        HandBelote cartesJouees_ = cartesJouees();
        cartesJouees_.ajouterCartes(progressingTrick.getCartes());
        CustList<TrickBelote> plis_ = new CustList<TrickBelote>();
        for (TrickBelote pli_ : tricks) {
            CardBelote carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.couleur() != _couleur) {
                continue;
            }
            byte ramasseur_ = pli_.getRamasseur(bid);
            boolean ramasseurAvantNumero_ = false;
            for (byte p: pli_.joueursAyantJoueAvant(_numero,rules.getDealing())) {
                if (p == ramasseur_) {
                    ramasseurAvantNumero_ = true;
                    break;
                }
            }
            if (!ramasseurAvantNumero_) {
                continue;
            }
            if (relations.memeEquipe(ramasseur_, _numero)) {
                continue;
            }
            // Plis sous coupes (couleur demandee) ou avec un atout joue en
            // dessous du ramasseur (demande atout)
            plis_.add(pli_);
        }
        HandBelote cartesVues_ = new HandBelote(Order.TRUMP);
        cartesVues_.ajouterCartes(cartesJouees_.couleurs(bid).getVal(_couleur));
        cartesVues_.ajouterCartes(_curHand.couleurs(bid).getVal(_couleur));
        cartesVues_.trierUnicolore(true);
        for (TrickBelote pli_ : plis_) {
            CardBelote carteObservee_ = pli_.carteDuJoueur(_numero);
            Suit couleurDemandee_ = pli_.couleurDemandee();
            HandBelote mainLocale_ = new HandBelote();
            //supprimer les atouts sous la carte observee
            for(CardBelote carte_: GameBeloteCommonPlaying.cartesAtouts(_couleur)) {
                if (carte_.strength(couleurDemandee_,bid) < carteObservee_
                        .strength(couleurDemandee_,bid)) {
                    mainLocale_.ajouter(carte_);
                }
            }
            retour_.supprimerCartes(mainLocale_);
        }
        return retour_;
    }
    CustList<HandBelote> cartesPossibles(Suit _couleur, HandBelote _curHand) {
        CustList<HandBelote> m = cartesPossiblesRegles(_couleur,_curHand);
        byte joueur_ = 0;
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        for (HandBelote main_ : m) {
            if (main_.estVide()) {
                joueur_++;
                continue;
            }
            if (joueur_ == next_) {
                joueur_++;
                continue;
            }
            HandBelote atoutsFiltres_ = joueCartePoint(_couleur, _curHand,joueur_,
                    main_);
            m.set(joueur_, atoutsFiltres_);
            joueur_++;
        }
        return m;
    }
    /**Retourne l'ensemble des cartes d'une meme couleur autre que l'atout probablement possedees par les autres joueurs on tient compte du pli en cours
     @param numero*/
    CustList<HandBelote> cartesPossiblesRegles(Suit _couleur, HandBelote _cartesJoueur) {
        EnumMap<Suit,HandBelote> repartition_ = _cartesJoueur.couleurs(bid);
        HandBelote suitCards_ = GameBeloteCommon.hand(repartition_,_couleur);
        HandBelote cartesJouees_ = cartesJouees();
        cartesJouees_.ajouterCartes(progressingTrick.getCartes());
        CustList<HandBelote> m=new CustList<HandBelote>();
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbPlayers; joueur_++) {
            HandBelote h_ = new HandBelote();
            m.add(h_);
            if(joueur_ == next_) {
                h_.ajouterCartes(suitCards_);
                continue;
            }
            for(CardBelote carte_:GameBeloteCommonPlaying.cartesCouleurs(_couleur)) {
                if(!cartesJouees_.contient(carte_)&&!suitCards_.contient(carte_)) {
                    if (lastSeenHand.estVide()) {
                        h_.ajouter(carte_);
                    } else if (!CardBelote.eq(lastSeenHand.premiereCarte(), carte_)||joueur_==taker) {
                        h_.ajouter(carte_);
                    }
                }
            }
            if(neFournitPas(_couleur, joueur_, tricks)) {
                //Les joueurs se defaussant sur atout ou couleur demandee ne peuvent pas avoir de l'atout
                h_.supprimerCartes();
            }
        }
        if(progressingTrick.couleurDemandee()==_couleur) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbPlayers; joueur_++) {
                if(joueur_ == next_) {
                    continue;
                }
                if(progressingTrick.aJoue(joueur_, nbPlayers)) {
                    /*Si un joueur a joue une carte autre que l'Excuse et pas de la couleur demandee dans le pli en cours, alors il coupe ou se defausse*/
                    CardBelote carteJouee_=progressingTrick.carteDuJoueur(joueur_,nbPlayers);
                    if(carteJouee_.couleur()!=_couleur) {
                        m.get(joueur_).supprimerCartes();
                    }
                }
            }
        }
        for(DeclareHandBelote a:declares) {
            HandBelote mainCouleur_ = a.getHand().couleurs(bid).getVal(_couleur);
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbPlayers; joueur_++) {
                if(joueur_ == a.getPlayer()) {
                    continue;
                }
                m.get(joueur_).supprimerCartes(mainCouleur_);
            }
        }
        return m;
    }

    /**Retourne vrai si et seulement si le joueur ne peut pas jouer atout sur demande d'atout ou couper quand il le faut
     (sur un adversaire ayant joue une carte de la couleur demandee forte virtuellement)*/
    boolean defausseBelote(Suit _couleur, byte _joueur,CustList<TrickBelote> _plisFaits) {
        boolean surCoupeObligPart_=relations.surCoupeObligatoirePartenaire();
        boolean sousCoupeObligPart_=relations.sousCoupeObligatoirePartenaire();
        boolean sousCoupeObligAdv_=relations.sousCoupeObligatoireAdversaire();
        if(!bid.getCouleurDominante() || bid.getCouleur() == _couleur) {
            return neFournitPas(_couleur,_joueur,_plisFaits);
        }
        Suit couleurAtout_=bid.getCouleur();
        int lastIndex_ = _plisFaits.getLastIndex();
        for(int indicePli_ = lastIndex_; indicePli_>= IndexConstants.FIRST_INDEX; indicePli_--) {
            TrickBelote pli_=_plisFaits.get(indicePli_);
            if(pli_.couleurDemandee()!=_couleur) {
                continue;
            }
            Suit couleurJoueur_=pli_.carteDuJoueur(_joueur).couleur();
            if(couleurJoueur_==_couleur) {
                continue;
            }
            if(couleurJoueur_==couleurAtout_) {
                continue;
            }
            //defausse de joueur
            Bytes joueurs_=pli_.joueursAyantJoueAvant(_joueur,rules.getRepartition());
            //Le pli n'est pas vide.
            CardBelote carteForte_ = pli_.premiereCarte();
            byte forcePremiereCarte_ = carteForte_.strength(_couleur,bid);
            byte ramasseurAvant_ = pli_.getEntameur();
            boolean doitCouper_;
            for(byte j: joueurs_) {
                CardBelote carte_ = pli_.carteDuJoueur(j);
                byte forceCarte_ = carte_.strength(_couleur,bid);
                if (forceCarte_ > forcePremiereCarte_) {
                    forcePremiereCarte_ = forceCarte_;
                    carteForte_ = carte_;
                    ramasseurAvant_ = j;
                }
            }
            if(relations.memeEquipe(_joueur, ramasseurAvant_)) {
                if(carteForte_.couleur() == couleurAtout_) {
                    doitCouper_ = surCoupeObligPart_ && sousCoupeObligPart_;
                } else {
                    doitCouper_ = surCoupeObligPart_;
                }
            } else {
                if(carteForte_.couleur() == couleurAtout_) {
                    doitCouper_ = sousCoupeObligAdv_;
                } else {
                    doitCouper_ = true;
                }
            }
            if(doitCouper_) {
                return true;
            }
        }
        return false;
    }
    static boolean neFournitPas(Suit _couleur, byte _joueur,CustList<TrickBelote> _plisFaits) {
        int lastIndex_ = _plisFaits.getLastIndex();
        for(int indicePli_ = lastIndex_; indicePli_>= IndexConstants.FIRST_INDEX; indicePli_--) {
            TrickBelote pli_=_plisFaits.get(indicePli_);
            if(pli_.couleurDemandee()==_couleur) {
                if(pli_.carteDuJoueur(_joueur).couleur()!=_couleur) {
                    return true;
                }
            }
        }
        return false;
    }
    public HandBelote cartesJouees() {
        HandBelote m = new HandBelote();
        for (TrickBelote t: tricks) {
            m.ajouterCartes(t.getCartes());
        }
        return m;
    }

    CustList<TrickBelote> getTricks() {
        return tricks;
    }

    TrickBelote getProgressingTrick() {
        return progressingTrick;
    }

    BidBeloteSuit getBid() {
        return bid;
    }

    HandBelote getLastSeenHand() {
        return lastSeenHand;
    }
}
