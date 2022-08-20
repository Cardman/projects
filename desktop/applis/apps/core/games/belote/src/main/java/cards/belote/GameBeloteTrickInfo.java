package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.Hypothesis;
import cards.consts.Order;
import cards.consts.Suit;
import code.util.*;
import code.util.core.IndexConstants;

public final class GameBeloteTrickInfo {

    private final TrickBelote progressingTrick;
    private final CustList<TrickBelote> tricks;
    private final CustList<DeclareHandBelote> declares;
    private final CustList<HandBelote> declaresBeloteRebelote;
    private final BidBeloteSuit bid;
    private final Ints handLengths;

    private byte nbPlayers;
    private byte taker;
    private final HandBelote lastSeenHand = new HandBelote();
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
    public IdMap<Hypothesis,IdMap<Suit,CustList<HandBelote>>> cartesCertaines(
            IdMap<Suit, CustList<HandBelote>> _cartesPossibles) {
        Bytes joueursRepartitionConnue_ = new Bytes();
        Bytes joueursRepartitionConnue2_ = new Bytes();
        Bytes joueursRepartitionConnueMemo_ = new Bytes();
        Bytes joueursRepartitionInconnue_ = new Bytes();
        IdMap<Suit,CustList<HandBelote>> cartesPossibles_ = new IdMap<Suit,CustList<HandBelote>>(
                _cartesPossibles);
        /*
        Indique le nombre de mains pour les
        cartes possibles ou apparait la carte
        */
        EnumList<Suit> toutesCouleurs_ = GameBeloteCommon.couleurs();
        IdMap<Suit, CustList<HandBelote>> cartesCertaines_ = sure(joueursRepartitionConnue_, joueursRepartitionConnueMemo_, cartesPossibles_, toutesCouleurs_);
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
            iterate(joueursRepartitionConnue_, joueursRepartitionConnue2_, joueursRepartitionConnueMemo_, joueursRepartitionInconnue_, cartesPossibles_, toutesCouleurs_, cartesCertaines_);
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
        IdMap<Hypothesis,IdMap<Suit,CustList<HandBelote>>> retour_ = new IdMap<Hypothesis,IdMap<Suit,CustList<HandBelote>>>();
        retour_.put(Hypothesis.POSSIBLE, cartesPossibles_);
        retour_.put(Hypothesis.SURE, cartesCertaines_);
        return retour_;
    }

    private void iterate(Bytes _joueursRepartitionConnue, Bytes _joueursRepartitionConnue2, Bytes _joueursRepartitionConnueMemo, Bytes _joueursRepartitionInconnue, IdMap<Suit, CustList<HandBelote>> _cartesPossibles, EnumList<Suit> _toutesCouleurs, IdMap<Suit, CustList<HandBelote>> _cartesCertaines) {
        for (byte joueur_ : _joueursRepartitionConnue) {
            for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nbPlayers; joueur2_++) {
                if (!_joueursRepartitionConnueMemo.containsObj(joueur2_)) {
                    remImpos(_cartesPossibles, _toutesCouleurs, _cartesCertaines, joueur_, joueur2_);
                }
                addToKnown(_toutesCouleurs, _cartesPossibles,joueur_, _cartesCertaines, _joueursRepartitionConnue2, _joueursRepartitionConnueMemo);
            }
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            if (!_joueursRepartitionConnueMemo.containsObj(joueur_)) {
                _joueursRepartitionInconnue.add(joueur_);
            }
        }
        for (byte joueur_ : _joueursRepartitionInconnue) {
            validatePlayer(_cartesPossibles, _toutesCouleurs, _cartesCertaines, joueur_);
            addToKnown(_toutesCouleurs, _cartesCertaines,joueur_, _cartesPossibles, _joueursRepartitionConnue2, _joueursRepartitionConnueMemo);
        }
        _joueursRepartitionInconnue.clear();
        _joueursRepartitionConnue.clear();
        _joueursRepartitionConnue.addAllElts(_joueursRepartitionConnue2);
        _joueursRepartitionConnue2.clear();
    }

    private void validatePlayer(IdMap<Suit, CustList<HandBelote>> _cartesPossibles, EnumList<Suit> _toutesCouleurs, IdMap<Suit, CustList<HandBelote>> _cartesCertaines, byte _joueur) {
        for (Suit couleur_: _toutesCouleurs) {
            CustList<HandBelote> cardSuit_ = _cartesPossibles.getVal(couleur_);
            for (CardBelote carte_ : cardSuit_.get(
                    _joueur)) {
                int nombreDApparitionCarte_ = nombreDApparitionCarte(carte_, cardSuit_);
                if (nombreDApparitionCarte_ == 1) {
                    _cartesCertaines.getVal(couleur_).get(_joueur).removeCardIfPresent(carte_);
                    _cartesCertaines.getVal(couleur_).get(_joueur)
                            .ajouter(carte_);
                }
            }
        }
    }

    private void remImpos(IdMap<Suit, CustList<HandBelote>> _cartesPossibles, EnumList<Suit> _toutesCouleurs, IdMap<Suit, CustList<HandBelote>> _cartesCertaines, byte _joueur, byte _joueur2) {
        for (Suit couleur_: _toutesCouleurs) {
            _cartesCertaines.getVal(couleur_)
                    .get(_joueur2).supprimerCartes(
                    _cartesCertaines.getVal(couleur_).get(
                            _joueur));
            _cartesPossibles.getVal(couleur_)
                    .get(_joueur2).supprimerCartes(
                    _cartesCertaines.getVal(couleur_).get(
                            _joueur));
        }
    }

    private int nombreDApparitionCarte(CardBelote _carte, CustList<HandBelote> _cards) {
        int nombreDApparitionCarte_ = 0;
        for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nbPlayers; joueur2_++) {
            if (_cards.get(joueur2_)
                    .contient(_carte)) {
                nombreDApparitionCarte_++;
            }
        }
        return nombreDApparitionCarte_;
    }

    private IdMap<Suit, CustList<HandBelote>> sure(Bytes _joueursRepartitionConnue, Bytes _joueursRepartitionConnueMemo, IdMap<Suit, CustList<HandBelote>> _cartesPossibles, EnumList<Suit> _toutesCouleurs) {
        IdMap<Suit,CustList<HandBelote>> cartesCertaines_ = new IdMap<Suit,CustList<HandBelote>>();
        for(Suit couleur_: _toutesCouleurs) {
            cartesCertaines_.put(couleur_,new CustList<HandBelote>());
        }
        for (Suit couleur_:cartesCertaines_.getKeys()) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                cartesCertaines_.getVal(couleur_).add(new HandBelote());
            }
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            addToKnown(_toutesCouleurs, _cartesPossibles,joueur_,cartesCertaines_, _joueursRepartitionConnue, _joueursRepartitionConnueMemo);
        }
        return cartesCertaines_;
    }

    void sortSuits(IdMap<Suit, CustList<HandBelote>> _reps, byte _player) {
        for(Suit couleur_:GameBeloteCommon.couleurs()) {
            if(bid.getCouleurDominante()) {
                if(couleur_!= bid.getSuit()) {
                    GameBeloteCommon.hand(_reps,couleur_, _player).setOrdre(Order.SUIT);
                }
            } else if(bid.ordreCouleur()) {
                GameBeloteCommon.hand(_reps,couleur_, _player).setOrdre(Order.SUIT);
            }
            GameBeloteCommon.hand(_reps,couleur_, _player).trierUnicolore(true);
        }
    }

    void addToKnown(EnumList<Suit> _all,IdMap<Suit,CustList<HandBelote>> _poss,byte _player,
                    IdMap<Suit,CustList<HandBelote>> _sure,
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
    static void affect(EnumList<Suit> _all,IdMap<Suit,CustList<HandBelote>> _from, byte _player,IdMap<Suit,CustList<HandBelote>> _to) {
        for (Suit s: _all) {
            _to.getVal(s).get(_player).supprimerCartes();
            _to.getVal(s).get(_player).ajouterCartes(_from.getVal(s).get(_player));
        }
    }

    public IdMap<Suit,CustList<HandBelote>> cartesPossibles(
            HandBelote _curHand) {
        IdMap<Suit,CustList<HandBelote>> m=new IdMap<Suit,CustList<HandBelote>>();
        for(Suit couleur_:GameBeloteCommon.couleurs()) {
            //On fait une boucle sur les couleurs autres que l'atout
            if(bid.getSuit() !=couleur_&&!bid.ordreAtout()) {
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
            if (main_.estVide() || joueur_ == next_) {
                joueur_++;
                continue;
            }
            HandBelote atoutsFiltres_ = sousCoupeBelote(_couleurAtout, joueur_,
                    main_);
            m.set(joueur_, atoutsFiltres_);
            joueur_++;
        }
        return m;
    }
    CustList<HandBelote> atoutsPossiblesRegles(
            Suit _couleurAtout,
            HandBelote _curHand) {
        IdMap<Suit,HandBelote> repartition_ = _curHand.couleurs(bid);
        HandBelote trumps_ = GameBeloteCommon.hand(repartition_,_couleurAtout);
        HandBelote cartesJouees_ = cartesJouees();
        cartesJouees_.ajouterCartes(progressingTrick.getCartes());
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        CustList<HandBelote> m=new CustList<HandBelote>();
        for (byte j = IndexConstants.FIRST_INDEX; j<nbPlayers; j++) {
            HandBelote h_ = new HandBelote();
            m.add(h_);
            playerTr(_couleurAtout, trumps_, cartesJouees_, next_, j, h_);
        }
        if(bid.getCouleurDominante()) {
            belReb(next_, m);
            trickNormal(_couleurAtout, next_, m);
        }
        trickTrump(_couleurAtout, next_, m);
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        if (couleurDemandee_ == _couleurAtout) {
            for (CardBelote c: progressingTrick) {
                Suit couleurJoueur_ = c.getId().getCouleur();
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

    private void trickTrump(Suit _couleurAtout, byte _next, CustList<HandBelote> _m) {
        CustList<TrickBelote> plis_ = new CustList<TrickBelote>(tricks);
        plis_.add(progressingTrick);
        for(TrickBelote pli_:plis_) {
            Suit couleurDemande_=pli_.couleurDemandee();
            if(couleurDemande_!= _couleurAtout) {
                continue;
            }
            //couleurDemande == couleurAtout
            for(CardBelote c: pli_) {
                trickCardTrump(_next, _m, pli_, couleurDemande_, c);
            }

        }
    }

    private void trickCardTrump(byte _next, CustList<HandBelote> _m, TrickBelote _pli, Suit _couleurDemande, CardBelote _c) {
        byte joueur_ = _pli.joueurAyantJoue(_c,nbPlayers);
        if(joueur_ == _next) {
            return;
        }
        Bytes joueursAvant_ = _pli.joueursAyantJoueAvant(joueur_, rules.getDealing());
        byte forceLoc_ = _c.strength(_couleurDemande, bid);
        byte ramasseurVirtuel_ = ramasseurVirtuel(_pli,_couleurDemande,joueur_,joueursAvant_,forceLoc_);
        //joueursAvant non vide
        if(ramasseurVirtuel_ == joueur_) {
            return;
        }
        byte max_ = _pli.carteDuJoueur(ramasseurVirtuel_, nbPlayers).strength(_couleurDemande, bid);
        HandBelote cartesExclues_ = excHand(_couleurDemande, max_, _m.get(joueur_));
        _m.get(joueur_).supprimerCartes(cartesExclues_);
    }

    private void trickNormal(Suit _couleurAtout, byte _next, CustList<HandBelote> _m) {
        CustList<TrickBelote> plis_ = new CustList<TrickBelote>(tricks);
        plis_.add(progressingTrick);
        for(TrickBelote pli_:plis_) {
            Suit couleurDemande_=pli_.couleurDemandee();
            if(couleurDemande_== _couleurAtout) {
                continue;
            }
            for(CardBelote c: pli_) {
                trickCardNormal(_couleurAtout, _next, _m, pli_, couleurDemande_, c);
            }
        }
    }

    private void trickCardNormal(Suit _couleurAtout, byte _next, CustList<HandBelote> _m, TrickBelote _pli, Suit _couleurDemande, CardBelote _c) {
        boolean sousCoupeObligAdv_=relations.sousCoupeObligatoireAdversaire();
        if(_c.getId().getCouleur() == _couleurDemande) {
            return;
        }
        byte joueur_ = _pli.joueurAyantJoue(_c,nbPlayers);
        if(joueur_ == _next) {
            return;
        }
        Bytes joueursAvant_ = _pli.joueursAyantJoueAvant(joueur_, rules.getDealing());
        byte forceLoc_ = _c.strength(_couleurDemande, bid);
        if(_c.getId().getCouleur() == _couleurAtout) {
            //coupe
            //joueursAvant non vide
            usingTrump(_m, _pli, _couleurDemande, joueur_, joueursAvant_, forceLoc_);
            return;
        }
        //defausse
        boolean defausseSurAtout_ = false;
        //joueursAvant non vide
        for(byte j: joueursAvant_) {
            if(_pli.carteDuJoueur(j,nbPlayers).getId().getCouleur() == _couleurAtout) {
                defausseSurAtout_ = true;
            }
        }
        byte ramasseurVirtuel_ = ramasseurVirtuel(_pli,_couleurDemande,joueur_,joueursAvant_, (byte) 0);
        byte max_ = _pli.carteDuJoueur(ramasseurVirtuel_, nbPlayers).strength(_couleurDemande, bid);
        if(relations.memeEquipe(ramasseurVirtuel_, joueur_)) {
            sameTeam(_m, _couleurDemande, joueur_, max_, defausseSurAtout_);
            return;
        }
        HandBelote cartesExclues_;
        //!memeEquipe(ramasseurVirtuel, joueur)
        if(!defausseSurAtout_) {
            //defausse sur une carte de couleur ordinaire d'un adversaire
            cartesExclues_ = new HandBelote();
            _m.get(joueur_).supprimerCartes();
        } else {
            //defausse sur un atout d'un adversaire
            cartesExclues_ = exc(_m, _couleurDemande, joueur_, max_);
            if(sousCoupeObligAdv_) {
                filterUnder(_m, _couleurDemande, joueur_, max_, cartesExclues_);
            }
        }
        _m.get(joueur_).supprimerCartes(cartesExclues_);
    }

    private void sameTeam(CustList<HandBelote> _m, Suit _couleurDemande, byte _joueur, byte _max, boolean _defausseSurAtout) {
        boolean surCoupeObligPart_=relations.surCoupeObligatoirePartenaire();
        boolean sousCoupeObligPart_=relations.sousCoupeObligatoirePartenaire();
        HandBelote cartesExclues_;
        if(!_defausseSurAtout) {
            cartesExclues_ = new HandBelote();
            //defausse sur une carte de couleur ordinaire du partenaire
            if(surCoupeObligPart_) {
                _m.get(_joueur).supprimerCartes();
            }
        } else {
            //defausse sur un atout du partenaire
            if(surCoupeObligPart_) {
                cartesExclues_ = exc(_m, _couleurDemande, _joueur, _max);
            } else {
                cartesExclues_ = new HandBelote();
            }
            if(sousCoupeObligPart_) {
                filterUnder(_m, _couleurDemande, _joueur, _max, cartesExclues_);
            }
        }
        _m.get(_joueur).supprimerCartes(cartesExclues_);
    }

    private void usingTrump(CustList<HandBelote> _m, TrickBelote _pli, Suit _couleurDemande, byte _joueur, Bytes _joueursAvant, byte _forceLoc) {
        byte ramasseurVirtuel_ = ramasseurVirtuel(_pli, _couleurDemande, _joueur, _joueursAvant, _forceLoc);
        if(ramasseurVirtuel_ == _joueur) {
            return;
        }
        byte max_ = _pli.carteDuJoueur(ramasseurVirtuel_, nbPlayers).strength(_couleurDemande, bid);
        HandBelote cartesExclues_;
        if(relations.memeEquipe(ramasseurVirtuel_, _joueur)) {
            //max > 0
            if(relations.sousCoupeObligatoirePartenaire()) {
                //supprimer les atouts superieurs a celui joue du partenaire
                cartesExclues_ = exc(_m, _couleurDemande, _joueur, max_);
            } else {
                cartesExclues_ = new HandBelote();
            }
        } else {
            //max > 0
            //supprimer les atouts superieurs a celui joue de l'adversaire
            cartesExclues_ = exc(_m, _couleurDemande, _joueur, max_);
        }
        _m.get(_joueur).supprimerCartes(cartesExclues_);
    }
    private byte ramasseurVirtuel(TrickBelote _pli, Suit _couleurDemande, byte _joueur, Bytes _joueursAvant, byte _forceLoc) {
        byte max_ = 0;
        byte ramasseurVirtuel_ = _joueur;
        for(byte j: _joueursAvant) {
            CardBelote carte_ = _pli.carteDuJoueur(j,nbPlayers);
            byte forceLoc2_ = carte_.strength(_couleurDemande, bid);
            if (forceLoc2_ < _forceLoc || forceLoc2_ < max_) {
                continue;
            }
            max_ = forceLoc2_;
            ramasseurVirtuel_ = j;
        }
        return ramasseurVirtuel_;
    }

    private HandBelote exc(CustList<HandBelote> _m, Suit _couleurDemande, byte _joueur, byte _max) {
        return excHand(_couleurDemande, _max, _m.get(_joueur));
    }

    private HandBelote excHand(Suit _couleurDemande, byte _max, HandBelote _list) {
        HandBelote cartesExclues_ = new HandBelote();
        for(CardBelote c2_: _list) {
            if(c2_.strength(_couleurDemande, bid) < _max) {
                continue;
            }
            cartesExclues_.ajouter(c2_);
        }
        return cartesExclues_;
    }

    private void belReb(byte _next, CustList<HandBelote> _m) {
        HandBelote cartesAnnonces_ = GameBeloteCommonPlaying.cartesBeloteRebelote(bid);
        for(TrickBelote p: tricks) {
            for(CardBelote c: p) {
                byte joueur_ = p.joueurAyantJoue(c);
                if(!declaresBeloteRebelote.get(joueur_).contient(c)) {
                    continue;
                }
                for (byte j = IndexConstants.FIRST_INDEX; j<nbPlayers; j++) {
                    if (j == _next || j == joueur_) {
                        continue;
                    }
                    //Les autres joueurs ne peuvent pas posseder les cartes a annoncer par unicite
                    _m.get(j).supprimerCartes(cartesAnnonces_);
                }
            }
        }
    }

    private void playerTr(Suit _couleurAtout, HandBelote _trumps, HandBelote _cartesJouees, byte _next, byte _j, HandBelote _h) {
        if (_j == _next) {
            _h.ajouterCartes(_trumps);
            return;
        }
        feedOtherPlayer(_trumps, _cartesJouees, _j, _h, GameBeloteCommonPlaying.cartesAtouts(_couleurAtout));
        if(bid.getCouleurDominante()) {
            boolean defausse_ = false;
            for(Suit couleur_:GameBeloteCommon.couleurs()) {
                if (defausseBelote(couleur_, _j,tricks)) {
                    defausse_ = true;
                }
            }
            if(defausse_) {
                //Les joueurs se defaussant sur atout ou couleur demandee ne peuvent pas avoir de l'atout
                _h.supprimerCartes();
            }
        }
        if(neFournitPas(_couleurAtout, _j, tricks)) {
            _h.supprimerCartes();
        }
    }

    private void filterUnder(CustList<HandBelote> _m, Suit _couleurDemande, byte _joueur, byte _max, HandBelote _cartesExclues) {
        for(CardBelote c2_: _m.get(_joueur)) {
            if(c2_.strength(_couleurDemande, bid) > _max) {
                continue;
            }
            _cartesExclues.ajouter(c2_);
        }
    }

    HandBelote joueCartePoint(Suit _couleur, byte _numero,
                              HandBelote _atoutsPossibles) {
        HandBelote retour_ = new HandBelote();
        retour_.ajouterCartes(_atoutsPossibles);
        HandBelote cartesJouees_ = cartesJouees();
        cartesJouees_.ajouterCartes(progressingTrick.getCartes());
        CustList<TrickBelote> plis_ = new CustList<TrickBelote>();
        for (TrickBelote pli_ : tricks) {
            CardBelote carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.getId().getCouleur() != _couleur || carteObservee_.points(bid) == 0 || relations.memeEquipe(pli_.getRamasseur(bid), _numero)) {
                continue;
            }
            // Plis sous coupes (couleur demandee) ou avec un atout joue en
            // dessous du ramasseur (demande atout)
            plis_.add(pli_);
        }
        for (TrickBelote pli_ : plis_) {
            HandBelote mainLocale_ = delTr(_numero, pli_, HandBelote.couleurComplete(_couleur, Order.SUIT));
            retour_.supprimerCartes(mainLocale_);
        }
        return retour_;
    }

    private HandBelote delTr(byte _numero, TrickBelote _pli, HandBelote _all) {
        CardBelote carteObservee_ = _pli.carteDuJoueur(_numero);
        Suit couleurDemandee_ = _pli.couleurDemandee();
        HandBelote mainLocale_ = new HandBelote();
        //supprimer les atouts sous la carte observee
        for(CardBelote carte_: _all) {
            if (carte_.strength(couleurDemandee_, bid) < carteObservee_
                    .strength(couleurDemandee_, bid)) {
                mainLocale_.ajouter(carte_);
            }
        }
        return mainLocale_;
    }

    HandBelote sousCoupeBelote(Suit _couleur, byte _numero,
                               HandBelote _atoutsPossibles) {
        HandBelote retour_ = new HandBelote();
        retour_.ajouterCartes(_atoutsPossibles);
        HandBelote cartesJouees_ = cartesJouees();
        cartesJouees_.ajouterCartes(progressingTrick.getCartes());
        CustList<TrickBelote> plis_ = new CustList<TrickBelote>();
        for (TrickBelote pli_ : tricks) {
            CardBelote carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.getId().getCouleur() != _couleur || foeLeader(_numero, pli_)) {
                continue;
            }
            // Plis sous coupes (couleur demandee) ou avec un atout joue en
            // dessous du ramasseur (demande atout)
            plis_.add(pli_);
        }
        for (TrickBelote pli_ : plis_) {
            HandBelote mainLocale_ = delTr(_numero, pli_, GameBeloteCommonPlaying.cartesAtouts(_couleur));
            retour_.supprimerCartes(mainLocale_);
        }
        return retour_;
    }

    private boolean foeLeader(byte _numero, TrickBelote _pli) {
        byte ramasseur_ = _pli.getRamasseur(bid);
        boolean ramasseurAvantNumero_ = false;
        for (byte p: _pli.joueursAyantJoueAvant(_numero,rules.getDealing())) {
            if (p == ramasseur_) {
                ramasseurAvantNumero_ = true;
                break;
            }
        }
        return !ramasseurAvantNumero_ || relations.memeEquipe(ramasseur_, _numero);
    }

    CustList<HandBelote> cartesPossibles(Suit _couleur, HandBelote _curHand) {
        CustList<HandBelote> m = cartesPossiblesRegles(_couleur,_curHand);
        byte joueur_ = 0;
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        for (HandBelote main_ : m) {
            if (main_.estVide() || joueur_ == next_) {
                joueur_++;
                continue;
            }
            HandBelote atoutsFiltres_ = joueCartePoint(_couleur, joueur_,
                    main_);
            m.set(joueur_, atoutsFiltres_);
            joueur_++;
        }
        return m;
    }
    /**Retourne l'ensemble des cartes d'une meme couleur autre que l'atout probablement possedees par les autres joueurs on tient compte du pli en cours
     @param numero*/
    CustList<HandBelote> cartesPossiblesRegles(Suit _couleur, HandBelote _cartesJoueur) {
        IdMap<Suit,HandBelote> repartition_ = _cartesJoueur.couleurs(bid);
        HandBelote suitCards_ = GameBeloteCommon.hand(repartition_,_couleur);
        HandBelote cartesJouees_ = cartesJouees();
        cartesJouees_.ajouterCartes(progressingTrick.getCartes());
        CustList<HandBelote> m=new CustList<HandBelote>();
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbPlayers; joueur_++) {
            HandBelote h_ = new HandBelote();
            m.add(h_);
            plNormal(_couleur, suitCards_, cartesJouees_, next_, joueur_, h_);
        }
        if(progressingTrick.couleurDemandee()==_couleur) {
            currentTrick(_couleur, m, next_);
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

    private void currentTrick(Suit _couleur, CustList<HandBelote> _m, byte _next) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nbPlayers; joueur_++) {
            if(joueur_ == _next) {
                continue;
            }
            if(progressingTrick.aJoue(joueur_, nbPlayers)) {
                /*Si un joueur a joue une carte autre que l'Excuse et pas de la couleur demandee dans le pli en cours, alors il coupe ou se defausse*/
                CardBelote carteJouee_=progressingTrick.carteDuJoueur(joueur_,nbPlayers);
                if(carteJouee_.getId().getCouleur() != _couleur) {
                    _m.get(joueur_).supprimerCartes();
                }
            }
        }
    }

    private void plNormal(Suit _couleur, HandBelote _suitCards, HandBelote _cartesJouees, byte _next, byte _joueur, HandBelote _h) {
        if(_joueur == _next) {
            _h.ajouterCartes(_suitCards);
            return;
        }
        feedOtherPlayer(_suitCards, _cartesJouees, _joueur, _h, GameBeloteCommonPlaying.cartesCouleurs(_couleur));
        if(neFournitPas(_couleur, _joueur, tricks)) {
            //Les joueurs se defaussant sur atout ou couleur demandee ne peuvent pas avoir de l'atout
            _h.supprimerCartes();
        }
    }

    private void feedOtherPlayer(HandBelote _suitCards, HandBelote _cartesJouees, byte _joueur, HandBelote _h, HandBelote _allSuitCards) {
        for(CardBelote carte_: _allSuitCards) {
            if (!_cartesJouees.contient(carte_) && !_suitCards.contient(carte_) && (lastSeenHand.estVide() || !CardBelote.eq(lastSeenHand.premiereCarte(), carte_) || _joueur == taker)) {
                _h.ajouter(carte_);
            }
        }
    }

    /**Retourne vrai si et seulement si le joueur ne peut pas jouer atout sur demande d'atout ou couper quand il le faut
     (sur un adversaire ayant joue une carte de la couleur demandee forte virtuellement)*/
    boolean defausseBelote(Suit _couleur, byte _joueur,CustList<TrickBelote> _plisFaits) {
        if(!bid.getCouleurDominante() || bid.getSuit() == _couleur) {
            return neFournitPas(_couleur,_joueur,_plisFaits);
        }
        int lastIndex_ = _plisFaits.getLastIndex();
        for(int indicePli_ = lastIndex_; indicePli_>= IndexConstants.FIRST_INDEX; indicePli_--) {
            if(doitCouperMaisNePeutPas(_joueur, _couleur, _plisFaits, indicePli_)) {
                return true;
            }
        }
        return false;
    }

    private boolean doitCouperMaisNePeutPas(byte _joueur, Suit _couleur, CustList<TrickBelote> _plisFaits, int _i) {
        TrickBelote pli_=_plisFaits.get(_i);
        if(pli_.couleurDemandee()!=_couleur) {
            return false;
        }
        Suit couleurAtout_= bid.getSuit();
        Suit couleurJoueur_= pli_.carteDuJoueur(_joueur).getId().getCouleur();
        if (couleurJoueur_ == _couleur || couleurJoueur_ == couleurAtout_) {
            return false;
        }
        //defausse de joueur
        Bytes joueurs_=pli_.joueursAyantJoueAvant(_joueur, rules.getDealing());
        //Le pli n'est pas vide.
        byte ramasseurAvant_ = ramasseurVirtuel(pli_,_couleur,pli_.getEntameur(),joueurs_, (byte) 0);
        CardBelote carteForte_ = pli_.carteDuJoueur(ramasseurAvant_);
        boolean surCoupeObligPart_=relations.surCoupeObligatoirePartenaire();
        boolean sousCoupeObligPart_=relations.sousCoupeObligatoirePartenaire();
        boolean sousCoupeObligAdv_=relations.sousCoupeObligatoireAdversaire();
        boolean doitCouper_;
        if(relations.memeEquipe(_joueur, ramasseurAvant_)) {
            if(carteForte_.getId().getCouleur() == couleurAtout_) {
                doitCouper_ = surCoupeObligPart_ && sousCoupeObligPart_;
            } else {
                doitCouper_ = surCoupeObligPart_;
            }
        } else {
            if(carteForte_.getId().getCouleur() == couleurAtout_) {
                doitCouper_ = sousCoupeObligAdv_;
            } else {
                doitCouper_ = true;
            }
        }
        return doitCouper_;
    }

    static boolean neFournitPas(Suit _couleur, byte _joueur,CustList<TrickBelote> _plisFaits) {
        int lastIndex_ = _plisFaits.getLastIndex();
        for(int indicePli_ = lastIndex_; indicePli_>= IndexConstants.FIRST_INDEX; indicePli_--) {
            TrickBelote pli_=_plisFaits.get(indicePli_);
            if (pli_.couleurDemandee() == _couleur && pli_.carteDuJoueur(_joueur).getId().getCouleur() != _couleur) {
                return true;
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
