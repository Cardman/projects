package cards.tarot;

import cards.consts.CardChar;
import cards.consts.Hypothesis;
import cards.consts.SortedPlayers;
import cards.consts.Suit;
import cards.tarot.enumerations.*;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class GameTarotTrickInfo {

    private final TrickTarot progressingTrick;
    private final CustList<TrickTarot> tricks;
    private final CustList<TrickTarot> tricksSeen = new CustList<TrickTarot>();

    private final CustList<IdList<Miseres>> declaresMiseres;

    private final CustList<HandTarot> handfuls;
    private final BidTarot bid;
    private final HandTarot calledCards;
    private final Ints handLengths;

    private final HandTarot lastSeenHand = new HandTarot();
    private int nbPlayers;
    private int taker;
    private RulesTarot rules;
    private final CustList<CustList<Confidence>> confidence = new CustList<CustList<Confidence>>();

    public GameTarotTrickInfo(TrickTarot _progressingTrick, CustList<TrickTarot> _tricks,
                              CustList<IdList<Miseres>> _declaresMiseres,
                              CustList<HandTarot> _handfuls, BidTarot _bid, HandTarot _calledCards,
                              Ints _handLengths) {
        progressingTrick = _progressingTrick;
        tricks = _tricks;
        declaresMiseres = _declaresMiseres;
        handfuls = _handfuls;
        bid = _bid;
        calledCards = _calledCards;
        handLengths = _handLengths;
        tricksSeen.addAllElts(tricksSeen(tricks));
    }

    public static CustList<TrickTarot> tricksSeen(CustList<TrickTarot> _tricks) {
//        CustList<TrickTarot> tricksSeen_ = new CustList<TrickTarot>();
//        for (TrickTarot t: _tricks) {
//            if (t.getVuParToutJoueur()) {
//                tricksSeen_.add(t);
//            }
//        }
//        return tricksSeen_;
        return _tricks.mid(1);
    }

    public static IdMap<Suit, CustList<HandTarot>> allRep(GameTarotTrickInfo _info, HandTarot _current) {
        IdMap<Suit,CustList<HandTarot>> m = new IdMap<Suit,CustList<HandTarot>>();
        CustList<HandTarot> possibleExcuse_ = _info.excusePossibleRegles(_current);
        m.addEntry(CardTarot.EXCUSE.getId().getCouleur(), possibleExcuse_);
        m.addEntry(Suit.TRUMP, _info.atoutsPossiblesRegles(
                _current));
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            // On fait une boucle sur les
            // couleurs autres que l'atout
            m.addEntry(couleur_, _info.cartesPossiblesRegles(couleur_,
                    _current));
        }
        return m;
    }

    void addSeenDeck(HandTarot _h, GameTarotTeamsRelation _rel) {
        nbPlayers = _rel.getNombreDeJoueurs();
        taker = _rel.getTaker();
        int next_ = progressingTrick.getNextPlayer(nbPlayers);
        if (cartesJoueesEnCours().contientCartes(calledCards)) {
            for (int i = 0; i < nbPlayers; i++) {
                CustList<Confidence> l_ = new CustList<Confidence>();
                feedConf(_rel, i, l_);
                confidence.add(l_);
            }
        } else {
            for (int i = 0; i < nbPlayers; i++) {
                CustList<Confidence> l_ = new CustList<Confidence>();
                if (i == next_) {
                    feedConf(_rel, i, l_);
                } else {
                    l_.add(Confidence.UNKNOWN);
                }
                confidence.add(l_);
            }
        }
        rules = _rel.getRules();
        if (bid.getJeuChien() == PlayingDog.WITH) {
            lastSeenHand.ajouterCartes(_h);
        }
    }

    private void feedConf(GameTarotTeamsRelation _rel, int _i, CustList<Confidence> _l) {
        for (int j = 0; j < nbPlayers; j++) {
            if (_rel.confiance(_i,j)) {
                _l.add(Confidence.YES);
            } else {
                _l.add(Confidence.NO);
            }
        }
    }

    /**
     Retourne l'ensemble des cartes des couleurs (avec l'Excuse) probablement
     possedees par les autres joueurs Pour premier indice (premier get)
     couleur, deuxieme indice joueur
     @param _numero
     */
    public IdMap<Suit,CustList<HandTarot>> cartesPossibles(HandTarot _cartesJoueur) {
        IdMap<Suit,CustList<HandTarot>> m = new IdMap<Suit,CustList<HandTarot>>();
        CustList<HandTarot> possibleExcuse_ = excusePossibleRegles(_cartesJoueur);
        m.addEntry(CardTarot.EXCUSE.getId().getCouleur(), possibleExcuse_);
        m.addEntry(Suit.TRUMP,atoutsPossibles(
                _cartesJoueur));
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            // On fait une boucle sur les
            // couleurs autres que l'atout
            m.addEntry(couleur_,cartesPossibles(couleur_,
                    _cartesJoueur));
        }
        return m;
    }

    CustList<HandTarot> excusePossibleRegles(HandTarot _cartesJoueur) {
        int next_ = progressingTrick.getNextPlayer(nbPlayers);
        HandTarot playedCards_ = cartesJoueesEnCours();
        boolean plExcuse_ = playedCards_.contient(CardTarot.EXCUSE);
        boolean containsExcuse_ = _cartesJoueur.contient(CardTarot.EXCUSE);
        boolean noExc_ = plExcuse_ || containsExcuse_;
        CustList<HandTarot> possibleExcuse_ = new CustList<HandTarot>();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            HandTarot h_ = new HandTarot();
            possibleExcuse_.add(h_);
            playerExcuse(next_, containsExcuse_, noExc_, joueur_, h_);
        }
        possibleExcuse_.add(new HandTarot());
        calledCardsTrumps(possibleExcuse_);
        if (bid.getJeuChien() != PlayingDog.WITH) {
            if (!noExc_) {
                possibleExcuse_.last().ajouter(CardTarot.excuse());
            }
        } else {
            /*
            Si le contrat
            est Petite ou
            Garde alors
            l'Excuse ne
            peut pas
            appartenir au
            chien
            */
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                if (joueur_ == next_) {
                    continue;
                }
                if (taker != joueur_ && lastSeenHand.contient(CardTarot.EXCUSE)) {
                    // L'Excuse du chien (si il est vu) ne
                    // peut etre possedee que par le preneur
                    possibleExcuse_.get(joueur_).supprimerCartes();
                }
            }
        }
        handfulExcuse(next_, possibleExcuse_);
        return possibleExcuse_;
    }

    private void playerExcuse(int _next, boolean _containsExcuse, boolean _noExc, int _joueur, HandTarot _h) {
        if(_joueur == _next) {
            if (_containsExcuse) {
                _h.ajouter(CardTarot.EXCUSE);
            }
            return;
        }
        if (!_noExc) {
            _h.ajouter(CardTarot.EXCUSE);
        }
        if (declaresMiseres.get(_joueur).containsObj(Miseres.POINT)
                || declaresMiseres.get(_joueur).containsObj(Miseres.TRUMP)) {
            _h.supprimerCartes();
        }
    }

    private void handfulExcuse(int _next, CustList<HandTarot> _possibleExcuse) {
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            // L'Excuse dans
            // une poignee
            // annule toute
            // possibilite
            // qu'un autre
            // joueur ait
            // celle-ci
            if (joueur_ == _next) {
                continue;
            }
            int nbHandfuls_ = handfuls.size();
            for (int i = IndexConstants.FIRST_INDEX; i < nbHandfuls_; i++) {
                if (!getPoignee(i).contient(CardTarot.excuse())) {
                    continue;
                }
                /* The current poignee contains the Excuse*/
                if (i != joueur_) {
                    _possibleExcuse.get(joueur_).supprimerCartes();
                }
            }
        }
        for (HandTarot poignee_ : handfuls) {
            if (poignee_.contient(CardTarot.EXCUSE)) {
                _possibleExcuse.last().supprimerCartes();
            }
        }
    }

    /**
     Retourne l'ensemble des atouts (sans l'Excuse) probablement possedes par
     les autres joueurs
     @param _numero
     */
    CustList<HandTarot> atoutsPossibles(HandTarot _curHand) {
        int next_ = progressingTrick.getNextPlayer(nbPlayers);
        HandTarot playedCards_ = cartesJoueesEnCours();
        boolean playedCalledCard_ = playedCards_.contientCartes(calledCards);
        CustList<HandTarot> m = atoutsPossiblesRegles(_curHand);
        for (int i = 0; i < nbPlayers; i++) {
            HandTarot main_ = m.get(i);
            if (i == next_) {
                continue;
            }
            //filtre sur le jeu d'une carte couleur atout apres un adversaire ramasseur
            HandTarot atoutsFiltres_ = sousCoupeTarot(_curHand,i,
                    main_);
            m.set(i, atoutsFiltres_);
        }
        for (int i = 0; i < nbPlayers; i++) {
            HandTarot main_ = m.get(i);
            if (i == next_) {
                continue;
            }
            //filtre sur la fourniture d'un atout a une couleur
            HandTarot atoutsFiltres_ = coupeTarot(_curHand,i,
                    main_);
            m.set(i, atoutsFiltres_);
        }
        if(playedCalledCard_) {
            for (int i = 0; i < nbPlayers; i++) {
                HandTarot main_ = m.get(i);
                if (i == next_) {
                    continue;
                }
                if(petitJoueDemandeAtoutRamasseurAdv(i)) {
                    main_.supprimerCartes();
                }
                //filtre sur la fourniture d'un atout a une couleur
            }
        }
        return m;
    }

    CustList<HandTarot> atoutsPossiblesRegles(HandTarot _curHand) {
        int next_ = progressingTrick.getNextPlayer(nbPlayers);
        IdMap<Suit,HandTarot> curRep_ = _curHand.couleurs();
        HandTarot playedCards_ = cartesJoueesEnCours();
        IdMap<Suit,HandTarot> plRep_ = playedCards_.couleurs();
        HandTarot plTr_ = plRep_.getVal(Suit.TRUMP);
        HandTarot curTr_ = curRep_.getVal(Suit.TRUMP);
        CustList<HandTarot> m = new CustList<HandTarot>();

        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            HandTarot h_ = new HandTarot();
            m.add(h_);
            playerTr(next_, plTr_, curTr_, m, joueur_, h_);
        }
        m.add(new HandTarot());
        calledCardsTrumps(m);
        if (bid.getJeuChien() == PlayingDog.WITH) {
            /*
            Les atouts ecartes sont annonces donc certains de faire partie du
            chien
            */
            m.last().ajouterCartes(
                    tricks.first().getCartes().couleur(Suit.TRUMP));
        } else {
            /*
            Si le chien est inconnu de tous alors n'importe quel atout non
            joue et non possede par le joueur peut etre dans le chien
            */
            for (CardTarot carte_ : HandTarot.atoutsSansExcuse()) {
                if (!plTr_.contient(carte_)
                        && !curTr_.contient(carte_)) {
                    m.last().ajouter(carte_);
                }
            }
        }
        tricksTrumps(next_, m);
        if (bid.getJeuChien() == PlayingDog.WITH) {
            excludeTrumpByDog(next_, m);
        }
        handfulsAll(next_, m);
        return m;
    }

    private void handfulsAll(int _next, CustList<HandTarot> _m) {
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            if (joueur_ == _next) {
                continue;
            }
            handfuls(_m, joueur_);
        }
        for (HandTarot main_ : handfuls) {
            for (CardTarot carte_ : main_) {
                _m.last().removeCardIfPresent(carte_);
            }
        }
    }

    private void tricksTrumps(int _next, CustList<HandTarot> _m) {
        CustList<TrickTarot> plis_ = new CustList<TrickTarot>(tricks);
        plis_.add(progressingTrick);
        for(TrickTarot pli_:plis_.mid(1)) {
//            if (!pli_.getVuParToutJoueur()) {
//                continue;
//            }
            Suit couleurDemande_=pli_.couleurDemandee();
            for(CardTarot c: pli_) {
                trickCard(_next, _m, pli_, couleurDemande_, c);
            }

        }
    }

    private void excludeTrumpByDog(int _next, CustList<HandTarot> _m) {
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            if (joueur_ == _next) {
                continue;
            }
            excludeTrumpByDogInd(_m, joueur_);
        }
    }

    private void excludeTrumpByDogInd(CustList<HandTarot> _m, int _joueur) {
        if (taker != _joueur) {
            // Les atouts du chien (si il est vu) ne peuvent possedes
            // que par le preneur
            for (CardTarot carte_ : lastSeenHand) {
                if (!Suit.couleursOrdinaires().containsObj(carte_.getId().getCouleur())) {
                    _m.get(_joueur).removeCardIfPresent(carte_);
                }
            }
        }
            /*
            Les atouts eventuellement ecartes au chien sont vus par les
            autres joueurs et ne peuvent pas etre joues dans les plis
            suivants
            */
        for (CardTarot carte_ : tricks.first()) {
            if (carte_.getId().getCouleur() == Suit.TRUMP) {
                _m.get(_joueur).removeCardIfPresent(carte_);
            }
        }
    }

    private void handfuls(CustList<HandTarot> _m, int _joueur) {
        int nbHandfuls_ = handfuls.size();
        for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nbHandfuls_; joueur2_++) {
            if (joueur2_ != _joueur) {
                _m.get(_joueur).supprimerCartes(getPoignee(joueur2_));
            } else if (getPoignee(_joueur).contient(CardTarot.excuse())) {
                HandTarot atoutsPoignee_ = new HandTarot();
                for (CardTarot c: _m.get(_joueur)) {
                    if (!getPoignee(_joueur)
                            .contient(c)) {
                        continue;
                    }
                    atoutsPoignee_.ajouter(c);
                }
                _m.set(_joueur, atoutsPoignee_);
            }
        }
        if (!progressingTrick.aJoue(_joueur, nbPlayers)) {
            return;
        }
        CardTarot carteDuJoueur_ = progressingTrick.carteDuJoueur(
                _joueur, nbPlayers);
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        if (Suit.couleursOrdinaires().containsObj(carteDuJoueur_.getId().getCouleur())
                && couleurDemandee_ != carteDuJoueur_.getId().getCouleur()) {
            /*
                Si le
                joueur
                a
                joue
                une
                carte
                autre
                que
                l'atout
                et
                l'Excuse
                et
                que
                la
                couleur
                demandee
                alors
                il se
                defausse
                */
            _m.get(_joueur).supprimerCartes();
        }
    }

    private void trickCard(int _next, CustList<HandTarot> _m, TrickTarot _pli, Suit _couleurDemande, CardTarot _c) {
        if (_c.getId().getCouleur() != Suit.TRUMP) {
            return;
        }
        int joueur_ = _pli.joueurAyantJouePliEnCours(_c,nbPlayers);
        if (joueur_ == _next) {
            return;
        }
        Ints joueursAvant_ = _pli.joueursAyantJoueAvant(joueur_, rules.getDealing());
        int forceLoc_ = _c.strength(_couleurDemande);
        int max_ = 0;
        int ramasseurVirtuel_ = joueur_;
        //joueursAvant non vide
        for(int j: joueursAvant_) {
            CardTarot carte_ = _pli.carteDuJoueur(j,nbPlayers);
            int forceLoc2_ = carte_.strength(_couleurDemande);
            if (forceLoc2_ < forceLoc_ || forceLoc2_ < max_) {
                continue;
            }
            max_ = forceLoc2_;
            ramasseurVirtuel_ = j;
        }
        if(ramasseurVirtuel_ == joueur_) {
            return;
        }
        HandTarot cartesExclues_ = new HandTarot();
        for(CardTarot c2_: _m.get(joueur_)) {
            if(c2_.strength(_couleurDemande) < max_) {
                continue;
            }
            cartesExclues_.ajouter(c2_);
        }
        _m.get(joueur_).supprimerCartes(cartesExclues_);
    }

    private void playerTr(int _next, HandTarot _plTr, HandTarot _curTr, CustList<HandTarot> _m, int _joueur, HandTarot _h) {
        if(_joueur == _next) {
            _h.ajouterCartes(_curTr);
            return;
        }
        for (CardTarot carte_ : HandTarot.atoutsSansExcuse()) {
            if (!_plTr.contient(carte_)
                    && !_curTr.contient(carte_)) {
                _h.ajouter(carte_);
            }
        }
        if (GameTarotTrickInfo.defausseTarot(_joueur, tricks.mid(1))) {
            // Les joueurs se defaussant
            // sur atout ou couleur
            // demandee ne peuvent pas
            // avoir de l'atout
            _h.supprimerCartes();
        }
        if (declaresMiseres.get(_joueur).containsObj(Miseres.TRUMP)) {
            _h.supprimerCartes();
        }
        if (declaresMiseres.get(_joueur).containsObj(Miseres.POINT)) {
            _h.supprimerCartes(_m.get(_joueur).bouts());
        }
    }

    private void calledCardsTrumps(CustList<HandTarot> _m) {
        HandTarot copy_ = calledCardsTrumpsExcuse();
        if (!copy_.estVide()) {
            CardTarot calledCard_ = copy_.premiereCarte();
            Suit couleur_ = calledCard_.getId().getCouleur();
            calledCards(_m, copy_, calledCard_, couleur_, true);
        }
    }

    private HandTarot calledCardsTrumpsExcuse() {
        HandTarot copy_ = new HandTarot();
        copy_.ajouterCartes(calledCards);
        copy_.supprimerCartes(HandTarot.trumpsPlusExcuse());
        return copy_;
    }

    /**
     Retourne l'ensemble des cartes d'une meme couleur autre que l'atout
     probablement possedees par les autres joueurs on tient compte du pli en
     cours
     @param _numero
     */
    CustList<HandTarot> cartesPossibles(Suit _couleur,
                                      HandTarot _curHand) {
        int next_ = progressingTrick.getNextPlayer(nbPlayers);
        HandTarot playedCards_ = cartesJoueesEnCours();
        boolean playedCalledCard_ = playedCards_.contientCartes(calledCards);
        CustList<HandTarot> m = cartesPossiblesRegles(_couleur,_curHand);
        for (int i = 0; i < nbPlayers; i++) {
            HandTarot couleurLoc_ = m.get(i);
            if (i == next_ || couleurLoc_.estVide()) {
                continue;
            }
            Suit noCouleur_ = couleurLoc_.premiereCarte().getId().getCouleur();
            //filtre sur le jeu d'une carte couleur ordinaire apres un adversaire ramasseur
            HandTarot atoutsFiltres_ = joueCarteBasseTarot(_curHand,
                    i, noCouleur_, couleurLoc_, tricks.mid(1));
            m.set(i, atoutsFiltres_);
        }
        if (playedCalledCard_) {
            for (int i = 0; i < nbPlayers; i++) {
                HandTarot couleurLoc_ = m.get(i);
                if (i == next_ || couleurLoc_.estVide()) {
                    continue;
                }
                Suit noCouleur_ = couleurLoc_.premiereCarte().getId().getCouleur();
                HandTarot filteredCharacters_ = playCharacterCardTarot(
                        i, noCouleur_, couleurLoc_, tricks.mid(1));
                m.set(i, filteredCharacters_);
            }
        }
        return m;
    }

    CustList<HandTarot> cartesPossiblesRegles(Suit _couleur,
                                            HandTarot _curHand) {
        int next_ = progressingTrick.getNextPlayer(nbPlayers);
        HandTarot playedCards_ = cartesJoueesEnCours();
        CustList<HandTarot> m = new CustList<HandTarot>();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            HandTarot h_ = new HandTarot();
            m.add(h_);
            plNormal(_couleur, _curHand, next_, playedCards_, joueur_, h_);
        }
        m.add(new HandTarot());
        calledCardsNormal(_couleur, m);
        if (bid.getJeuChien() != PlayingDog.WITH) {
            noDog(_couleur, _curHand, playedCards_, m);
        } else {
            dog(_couleur, _curHand, next_, playedCards_, m);
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                if (joueur_ == next_) {
                    continue;
                }
                excludeByDog(_couleur, m, joueur_);
            }
        }
        if (progressingTrick.couleurDemandee() == _couleur) {
            currentTrick(_couleur, next_, m);
        }
        return m;
    }

    private void excludeByDog(Suit _couleur, CustList<HandTarot> _m, int _joueur) {
        if (taker != _joueur) {
            // Les cartes d'une couleur du chien (si il est vu) ne
            // peuvent possedes que par le preneur ou etre ecartees
            for (CardTarot carte_ : lastSeenHand) {
                if (carte_.getId().getCouleur() == _couleur) {
                    _m.get(_joueur).removeCardIfPresent(carte_);
                }
            }
        } else if (tricks.first().getCartes().tailleCouleur(Suit.TRUMP) > 0) {
            /* Si le preneur a ecarte des
            atouts dans le chien alors
            les cartes autres que
            les atouts incluant
            l 'Excuse et les rois
            ne peuvent pas etre
            possedees par le preneur*/
            for (CardTarot carte_ : HandTarot.couleurComplete(_couleur)) {
                if (carte_.getId().getNomFigure() == CardChar.KING) {
                    continue;
                }
                _m.get(taker).removeCardIfPresent(carte_);
            }
        }
    }

    private void dog(Suit _couleur, HandTarot _curHand, int _next, HandTarot _playedCards, CustList<HandTarot> _m) {
        if (_next == taker) {
            /*
        Le preneur sait ce qu'il a mis au chien
        pour une Petite ou une Garde
        */
            _m.last().ajouterCartes(
                    tricks.first().getCartes().couleur(_couleur));
        } else {
            for (CardTarot carte_ : HandTarot.couleurComplete(_couleur)) {
                if (carte_.getId().getNomFigure() == CardChar.KING) {
                    continue;
                }
                if (notPlayedNotHand(_curHand, _playedCards, carte_)) {
                    _m.last().ajouter(carte_);
                }
            }
        }
    }

    private void noDog(Suit _couleur, HandTarot _curHand, HandTarot _playedCards, CustList<HandTarot> _m) {
        for (CardTarot carte_ : HandTarot.couleurComplete(_couleur)) {
            if (notPlayedNotHand(_curHand, _playedCards, carte_)) {
                _m.last().ajouter(carte_);
            }
        }
    }

    private boolean notPlayedNotHand(HandTarot _curHand, HandTarot _playedCards, CardTarot _carte) {
        return !_playedCards.contient(_carte)
                && !_curHand.contient(_carte);
    }

    private void currentTrick(Suit _couleur, int _next, CustList<HandTarot> _m) {
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            if (joueur_ == _next || !progressingTrick.aJoue(joueur_, nbPlayers)) {
                continue;
            }
            CardTarot carteJouee_ = progressingTrick.carteDuJoueur(
                    joueur_, nbPlayers);
            if (carteJouee_.getId().getCouleur() != _couleur
                    && carteJouee_ != CardTarot.EXCUSE) {
                /*
                    Si un joueur a joue
                    une carte autre que
                    l'Excuse et pas de la
                    couleur demandee dans
                    le pli en cours,
                    alors il coupe ou se
                    defausse
                    */
                _m.get(joueur_).supprimerCartes();
            }
        }
    }

    private void plNormal(Suit _couleur, HandTarot _curHand, int _next, HandTarot _playedCards, int _joueur, HandTarot _h) {
        if(_joueur == _next) {
            _h.ajouterCartes(_curHand.couleur(_couleur));
            return;
        }
        for (CardTarot carte_ : HandTarot.couleurComplete(_couleur)) {
            if (!_playedCards.contient(carte_)
                    && !_curHand.contient(carte_)) {
                _h.ajouter(carte_);
            }
        }
        if (GameTarotTrickInfo.defausseTarot(_joueur, _couleur, tricks.mid(1))
                || GameTarotTrickInfo.coupeTarot(_couleur, _joueur, tricks.mid(1))) {
            // Les joueurs
            // se defaussant
            // sur atout ou
            // couleur
            // demandee ne
            // peuvent pas
            // avoir de
            // l'atout
            _h.supprimerCartes();
        }
        if (declaresMiseres.get(_joueur).containsObj(Miseres.SUIT)) {
            _h.supprimerCartes();
        }
        if (declaresMiseres.get(_joueur).containsObj(Miseres.POINT)
                || declaresMiseres.get(_joueur).containsObj(Miseres.CHARACTER)) {
            _h.supprimerCartes(_h.charCardsBySuit(_couleur));
        }
        if (declaresMiseres.get(_joueur).containsObj(Miseres.LOW_CARDS)) {
            _h.supprimerCartes(
                    _h.cartesBasses(_couleur));
        }
    }

    private void calledCardsNormal(Suit _couleur, CustList<HandTarot> _m) {
        HandTarot copy_ = calledCardsTrumpsExcuse();
        if (!copy_.estVide()) {
            CardTarot calledCard_ = copy_.premiereCarte();
            Suit couleur_ = calledCard_.getId().getCouleur();
            calledCards(_m, copy_, calledCard_, couleur_, _couleur != couleur_);
        }
    }

    private void calledCards(CustList<HandTarot> _m, HandTarot _copy, CardTarot _calledCard, Suit _couleur, boolean _all) {
        if (tricks.size() == 1) {
            if (progressingTrick.couleurDemandee() == _couleur) {
                CardTarot cardTarot_ = progressingTrick.premiereCarteNonExc();
                if (notApplyFilter(_calledCard, cardTarot_)) {
                    return;
                }
                for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                    int pl_ = progressingTrick.joueurAyantJouePliEnCours(cardTarot_, rules.getDealing().getId().getNombreJoueurs());
                    supprimerCartes(pl_, joueur_, _m, _copy, _all);
                }
            }
        } else if (tricks.get(1).couleurDemandee() == _couleur) {
            CardTarot cardTarot_ = tricks.get(1).premiereCarteNonExc();
            if (notApplyFilter(_calledCard, cardTarot_)) {
                return;
            }
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                int pl_ = tricks.get(1).joueurAyantJoue(cardTarot_);
                supprimerCartes(pl_, joueur_, _m, _copy, _all);
            }
        }
    }

    private void supprimerCartes(int _pl, int _joueur, CustList<HandTarot> _m, HandTarot _copy, boolean _all) {
        if (_pl != _joueur) {
            return;
        }
        HandTarot hand_ = _m.get(_pl);
        if (_all) {
            hand_.supprimerCartes();
        } else {
            hand_.supprimerCartes(_copy);
        }
    }

    private boolean notApplyFilter(CardTarot _calledCard, CardTarot _cardTarot) {
        return _calledCard == _cardTarot || rules.isAllowPlayCalledSuit();
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
    public IdMap<Hypothesis,IdMap<Suit,CustList<HandTarot>>> cartesCertaines(
            IdMap<Suit, CustList<HandTarot>> _cartesPossibles) {
        Ints joueursRepartitionConnue_ = new Ints();
        Ints joueursRepartitionConnue2_ = new Ints();
        Ints joueursRepartitionConnueMemo_ = new Ints();
        Ints joueursRepartitionInconnue_ = new Ints();
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = new IdMap<Suit,CustList<HandTarot>>();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = new IdMap<Suit,CustList<HandTarot>>(
                _cartesPossibles);
        IdMap<Hypothesis,IdMap<Suit,CustList<HandTarot>>> retour_ = new IdMap<Hypothesis,IdMap<Suit,CustList<HandTarot>>>();
        /*
        Indique le nombre de mains pour les
        cartes possibles ou apparait la carte
        */
        IdList<Suit> toutesCouleurs_ = new IdList<Suit>();
        toutesCouleurs_.add(CardTarot.EXCUSE.getId().getCouleur());
        toutesCouleurs_.add(Suit.TRUMP);
        toutesCouleurs_.addAllElts(Suit.couleursOrdinaires());
        for(Suit couleur_: toutesCouleurs_) {
            cartesCertaines_.addEntry(couleur_,new CustList<HandTarot>());
        }
        for (EntryCust<Suit, CustList<HandTarot>> couleur_:cartesCertaines_.entryList()) {
            for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ <= nbPlayers; joueur_++) {
                couleur_.getValue().add(new HandTarot());
            }
        }
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ <= nbPlayers; joueur_++) {
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
            iterate(joueursRepartitionConnue_, joueursRepartitionConnue2_, joueursRepartitionConnueMemo_, joueursRepartitionInconnue_, cartesCertaines_, cartesPossibles_, toutesCouleurs_);
        }
        nextPlayers(joueursRepartitionConnueMemo_, joueursRepartitionInconnue_, nbPlayers);
        for (int joueur_ : joueursRepartitionInconnue_) {
            cartesCertaines_.getVal(Suit.TRUMP).get(joueur_).trierParForceEnCours(Suit.TRUMP);
            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                cartesCertaines_.getVal(couleur_).get(joueur_).trierParForceEnCours(couleur_);
            }
        }
        retour_.put(Hypothesis.POSSIBLE, cartesPossibles_);
        retour_.put(Hypothesis.SURE, cartesCertaines_);
        return retour_;
    }

    private void iterate(Ints _joueursRepartitionConnue, Ints _joueursRepartitionConnue2, Ints _joueursRepartitionConnueMemo, Ints _joueursRepartitionInconnue, IdMap<Suit, CustList<HandTarot>> _cartesCertaines, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, IdList<Suit> _toutesCouleurs) {
        for (int joueur_ : _joueursRepartitionConnue) {
            for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ <= nbPlayers; joueur2_++) {
                if (!_joueursRepartitionConnueMemo.containsObj(joueur2_)) {
                    remImposTarot(_cartesCertaines, _cartesPossibles, _toutesCouleurs, joueur_, joueur2_);
                }
                addToKnown(_toutesCouleurs, _cartesPossibles,joueur_, _cartesCertaines, _joueursRepartitionConnue2, _joueursRepartitionConnueMemo);
            }
        }
        nextPlayers(_joueursRepartitionConnueMemo, _joueursRepartitionInconnue, nbPlayers);
        for (int joueur_ : _joueursRepartitionInconnue) {
            validatePlayerTarot(_cartesCertaines, _cartesPossibles, _toutesCouleurs, joueur_);
            addToKnown(_toutesCouleurs, _cartesCertaines,joueur_, _cartesPossibles, _joueursRepartitionConnue2, _joueursRepartitionConnueMemo);
        }
        SortedPlayers.shift(_joueursRepartitionConnue,_joueursRepartitionConnue2,_joueursRepartitionInconnue);
    }

    static void nextPlayers(Ints _joueursRepartitionConnueMemo, Ints _joueursRepartitionInconnue, int _nbPlayers) {
        SortedPlayers.nextPlayers(_joueursRepartitionConnueMemo,_joueursRepartitionInconnue, _nbPlayers+1);
    }

    private void validatePlayerTarot(IdMap<Suit, CustList<HandTarot>> _cartesCertaines, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, IdList<Suit> _toutesCouleurs, int _joueur) {
        for (Suit couleur_: _toutesCouleurs) {
            for (CardTarot carte_ : _cartesPossibles.getVal(couleur_).get(
                    _joueur)) {
                int nombreDApparitionCarte_ = 0;
                for (int joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ <= nbPlayers; joueur2_++) {
                    if (_cartesPossibles.getVal(couleur_).get(joueur2_)
                            .contient(carte_)) {
                        nombreDApparitionCarte_++;
                    }
                }
                if (nombreDApparitionCarte_ == 1) {
                    _cartesCertaines.getVal(couleur_).get(_joueur).removeCardIfPresent(carte_);
                    _cartesCertaines.getVal(couleur_).get(_joueur)
                            .ajouter(carte_);
                }
            }
        }
    }

    private void remImposTarot(IdMap<Suit, CustList<HandTarot>> _cartesCertaines, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, IdList<Suit> _toutesCouleurs, int _joueur, int _joueur2) {
        for (Suit couleur_: _toutesCouleurs) {
            remSure(_cartesCertaines, _joueur, _joueur2, couleur_);
            remPoss(_cartesCertaines, _cartesPossibles, _joueur, _joueur2, couleur_);
        }
    }

    private void remPoss(IdMap<Suit, CustList<HandTarot>> _cartesCertaines, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, int _joueur, int _joueur2, Suit _couleur) {
        _cartesPossibles.getVal(_couleur)
                .get(_joueur2).supprimerCartes(
                _cartesCertaines.getVal(_couleur).get(
                        _joueur));
    }

    private void remSure(IdMap<Suit, CustList<HandTarot>> _cartesCertaines, int _joueur, int _joueur2, Suit _couleur) {
        _cartesCertaines.getVal(_couleur)
                .get(_joueur2).supprimerCartes(
                        _cartesCertaines.getVal(_couleur).get(
                                _joueur));
    }

    void addToKnown(IdList<Suit> _all,IdMap<Suit,CustList<HandTarot>> _poss,int _player,
                    IdMap<Suit,CustList<HandTarot>> _sure,
                    Ints _joueursRepartitionConnue, Ints _joueursRepartitionConnueMemo) {
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
    static void affect(IdList<Suit> _all,IdMap<Suit,CustList<HandTarot>> _from, int _player,IdMap<Suit,CustList<HandTarot>> _to) {
        for (Suit s: _all) {
            _to.getVal(s).get(_player).supprimerCartes();
            _to.getVal(s).get(_player).ajouterCartes(_from.getVal(s).get(_player));
        }
    }

    HandTarot playCharacterCardTarot(int _numero,
                                     Suit _couleur, HandTarot _probablyCharacterCard,
                                     CustList<TrickTarot> _unionPlis) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_probablyCharacterCard);
        HandTarot playedCards_ = new HandTarot();
        IntMap<BoolVal> defausses_ = defausses(_unionPlis);
        for (TrickTarot pli_ : _unionPlis) {
            if (pli_.getEntameur() == _numero) {
                //!pli_.getVuParToutJoueur() ||
                continue;
            }
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.getId().getCouleur() == _couleur && carteObservee_.isCharacter() && confidence.get(_numero).get(pli_.getRamasseur()) != Confidence.YES && pli_.joueursAyantJoueAvant(_numero, rules.getDealing()).containsObj(pli_.getRamasseur()) && defausseToutJoueurApres(_numero, defausses_, pli_)) {
                //winner of the trick foe for the viewed player
                // Plis (sur)coupes (couleur demandee) sans joueur pouvant sur/sous/couper
                // Plis fournis (demande atout) sans joueur pouvant fournir un atout
                playedCards_.ajouter(carteObservee_);
            }
        }
        if (playedCards_.estVide()) {
            return retour_;
        }
        CardTarot maxCarte_ = playedCards_.premiereCarte();
        HandTarot cartesImpossibles_ = excLowerCardsSuit(_couleur, maxCarte_, HandTarot.couleurComplete(_couleur));
        retour_.supprimerCartes(cartesImpossibles_);
        return retour_;
    }

    HandTarot joueCarteBasseTarot(HandTarot _curHand, int _numero,
                                  Suit _couleur, HandTarot _cartesCouleurPossibles,
                                  CustList<TrickTarot> _unionPlis) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_cartesCouleurPossibles);
        HandTarot cartesJouees_ = cartesJoueesEnCours();
        HandTarot playedCards_ = new HandTarot();
        for (TrickTarot pli_ : _unionPlis) {
//            if (!pli_.getVuParToutJoueur()) {
//                continue;
//            }
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.getId().getCouleur() == _couleur && !carteObservee_.isCharacter()) {
                playedCards_.ajouter(carteObservee_);
                // Plis sous coupes (couleur demandee) ou avec un atout joue en
                // dessous du ramasseur (demande atout)
            }
        }
        HandTarot cartesVues_ = new HandTarot();
        cartesVues_.ajouterCartes(cartesJouees_.couleur(_couleur).cartesBasses(
                _couleur));
        cartesVues_.ajouterCartes(_curHand.couleur(_couleur).cartesBasses(
                _couleur));
        cartesVues_.trierParForceEnCours(_couleur);
        playedCards_.trierParForceEnCours(_couleur);
        if (playedCards_.estVide()) {
            return retour_;
        }
        CardTarot carteObservee_ = playedCards_.derniereCarte();
        HandTarot mainLocale_ = subCardSeenCards(cartesVues_, _couleur, carteObservee_);
        if (!mainLocale_.estVide()) {
            CardTarot maxCarte_ = mainLocale_.premiereCarte();
            HandTarot cartesImpossibles_ = excLowerCardsSuit(_couleur, maxCarte_, HandTarot.couleurComplete(_couleur)
                    .cartesBasses(_couleur));
            retour_.supprimerCartes(cartesImpossibles_);
        }
        return retour_;
    }


    private HandTarot excLowerCardsSuit(Suit _couleur, CardTarot _maxCarte, HandTarot _cards) {
        HandTarot cartesImpossibles_ = new HandTarot();
        for (CardTarot atout_ : _cards) {
            if (atout_.strength(_couleur) >= _maxCarte.strength(_couleur)) {
                continue;
            }
            cartesImpossibles_.ajouter(atout_);
        }
        return cartesImpossibles_;
    }

    private HandTarot subCardSeenCards(HandTarot _cartesVues, Suit _couleur, CardTarot _card) {
        HandTarot mainLocale_ = new HandTarot();
        for (CardTarot carte_ : _cartesVues) {
            if (carte_.strength(_couleur) < _card
                    .strength(_couleur)) {
                mainLocale_.ajouter(carte_);
            }
        }
        return mainLocale_;
    }

    HandTarot sousCoupeTarot(HandTarot _curHand, int _numero,
                             HandTarot _atoutsPossibles) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_atoutsPossibles);
        HandTarot cartesJouees_ = cartesJoueesEnCours();
        HandTarot playedCards_ = new HandTarot();
        for (TrickTarot pli_ : tricksSeen) {
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            if (!sousCoupe(_numero, pli_, carteObservee_)) {
                continue;
            }
            // Plis sous coupes (couleur demandee) ou avec un atout joue en
            // dessous du ramasseur (demande atout)
            playedCards_.ajouter(carteObservee_);
        }
        HandTarot cartesVues_ = new HandTarot();
        cartesVues_.ajouterCartes(cartesJouees_.couleur(Suit.TRUMP));
        cartesVues_.ajouterCartes(_curHand.couleur(Suit.TRUMP));
        cartesVues_.trierParForceEnCours(Suit.TRUMP);
        playedCards_.trierParForceEnCours(Suit.TRUMP);
        if (!playedCards_.estVide()) {
            CardTarot carteObservee_ = playedCards_.derniereCarte();
            HandTarot mainLocale_ = subCardSeenCards(cartesVues_, Suit.TRUMP, carteObservee_);
            if (!mainLocale_.estVide()) {
                CardTarot maxCarte_ = mainLocale_.premiereCarte();
                HandTarot atoutsImpossibles_ = new HandTarot();
                for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
                    if (CardTarot.eq(atout_, CardTarot.petit()) || atout_.strength(Suit.TRUMP) >= maxCarte_
                            .strength(Suit.TRUMP)) {
                        continue;
                    }
                    atoutsImpossibles_.ajouter(atout_);
                }
                retour_.supprimerCartes(atoutsImpossibles_);
            }
        }
        return retour_;
    }

    private boolean sousCoupe(int _numero, TrickTarot _pli, CardTarot _carteObservee) {
        if (_carteObservee.getId().getCouleur() != Suit.TRUMP) {
            return false;
        }
        boolean sousCoupe_ = false;
        Suit couleurDemandee_ = _pli.couleurDemandee();
        int force_ = _carteObservee.strength(couleurDemandee_);
        for(int j: _pli.joueursAyantJoueAvant(_numero, rules.getDealing())) {
            if (_pli.carteDuJoueur(j).strength(couleurDemandee_) >= force_) {
                sousCoupe_ = true;
                break;
            }
        }
        return sousCoupe_;
    }

    HandTarot coupeTarot(HandTarot _curHand, int _numero,
                         HandTarot _atoutsPossibles) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_atoutsPossibles);
        HandTarot cartesJouees_ = cartesJoueesEnCours();
        CustList<TrickTarot> plis_ = new CustList<TrickTarot>();
        IntMap<HandTarot> atoutsJouesPlis_ = new IntMap<HandTarot>();
        IntMap<BoolVal> defausses_ = defausses(tricks.mid(1));
        int key_ = 0;
        for (TrickTarot pli_ : tricksSeen) {
            HandTarot atoutsJouesAvant_ = new HandTarot();
            if (!coupe(_numero, pli_, atoutsJouesAvant_) || !defausseToutJoueurApres(_numero, defausses_, pli_)) {
                key_++;
                continue;
            }
            atoutsJouesPlis_.addEntry(key_, atoutsJouesAvant_);
            // Plis (sur)coupes (couleur demandee) sans joueur pouvant sur/sous/couper
            // Plis fournis (demande atout) sans joueur pouvant fournir un atout
            plis_.add(pli_);
            key_++;
        }
        HandTarot cartesVues_ = new HandTarot();
        cartesVues_.ajouterCartes(cartesJouees_.couleur(Suit.TRUMP));
        cartesVues_.ajouterCartes(_curHand.couleur(Suit.TRUMP));
        cartesVues_.trierParForceEnCours(Suit.TRUMP);
        key_ = 0;
        for (EntryCust<Integer,HandTarot> e: atoutsJouesPlis_.entryList()) {
            excludeLowerTrumpsOverMaxCard(_numero, retour_, cartesVues_, e.getValue(), plis_.get(key_));
            key_++;
        }
        return retour_;
    }

    private IntMap<BoolVal> defausses(CustList<TrickTarot> _tricks) {
        IntMap<BoolVal> defausses_ = new IntMap<BoolVal>();
        for (int j = IndexConstants.FIRST_INDEX; j<nbPlayers; j++) {
            defausses_.put(j, ComparatorBoolean.of(GameTarotTrickInfo.defausseTarot(j, _tricks)));
        }
        return defausses_;
    }

    private boolean coupe(int _numero, TrickTarot _pli, HandTarot _atoutsJouesAvant) {
        if (_pli.getEntameur() == _numero) {
            return false;
        }
        CardTarot carteObservee_ = _pli.carteDuJoueur(_numero);
        if (carteObservee_.getId().getCouleur() != Suit.TRUMP) {
            return false;
        }
        boolean coupe_ = true;
        Suit couleurDemandee_ = _pli.couleurDemandee();
        int force_ = carteObservee_.strength(couleurDemandee_);
        for(int j: _pli.joueursAyantJoueAvant(_numero, rules.getDealing())) {
            CardTarot carteJouee_ = _pli.carteDuJoueur(j);
            if (carteJouee_.strength(couleurDemandee_) >= force_) {
                coupe_ = false;
                break;
            }
            if (carteJouee_.getId().getCouleur() == Suit.TRUMP) {
                _atoutsJouesAvant.ajouter(carteJouee_);
            }
        }
        return coupe_;
    }

    private boolean defausseToutJoueurApres(int _numero, IntMap<BoolVal> _defausses, TrickTarot _pli) {
        boolean defausseToutJoueurApres_ = true;
        for(int j: _pli.joueursAyantJoueApres(_numero, rules.getDealing())) {
            if (_defausses.getVal(j) != BoolVal.TRUE) {
                defausseToutJoueurApres_ = false;
                break;
            }
        }
        return defausseToutJoueurApres_;
    }

    private void excludeLowerTrumpsOverMaxCard(int _numero, HandTarot _retour, HandTarot _cartesVues, HandTarot _atoutsJouesAvant, TrickTarot _pli) {
        HandTarot mainLocale_ = subCardSeenCards(_numero, _cartesVues, _pli);
        //mainLocale: cartesVues inferieures a la carte observee
        //le joueur courant ne possede pas de carte en dessous de la carte observee
        //aucune carte en dessous de la carte observee n'a ete jouee
        //tout reste possible
        if (mainLocale_.estVide()) {
            return;
        }
        CardTarot maxCarte_ = mainLocale_.premiereCarte();
        HandTarot atoutsImpossibles_ = new HandTarot();
        if(_atoutsJouesAvant.estVide()) {
            for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
                if (atout_.strength(Suit.TRUMP) >= maxCarte_
                        .strength(Suit.TRUMP)) {
                    continue;
                }
                atoutsImpossibles_.ajouter(atout_);
            }
        } else {
            _atoutsJouesAvant.trierParForceEnCours(_pli.couleurDemandee());
            CardTarot maxAtoutJouePli_ = _atoutsJouesAvant.premiereCarte();
            for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
                if (atout_.strength(Suit.TRUMP) >= maxCarte_
                        .strength(Suit.TRUMP) || atout_.strength(Suit.TRUMP) <= maxAtoutJouePli_
                        .strength(Suit.TRUMP)) {
                    continue;
                }
                atoutsImpossibles_.ajouter(atout_);
            }
        }
        _retour.supprimerCartes(atoutsImpossibles_);
    }

    private HandTarot subCardSeenCards(int _numero, HandTarot _cartesVues, TrickTarot _pli) {
        CardTarot carteObservee_ = _pli.carteDuJoueur(_numero);
        return subCardSeenCards(_cartesVues, Suit.TRUMP, carteObservee_);
    }

    boolean petitJoueDemandeAtoutRamasseurAdv(int _numero) {
        boolean playedSmall_ = false;
        for (TrickTarot pli_ : tricksSeen) {
            if (pli_.couleurDemandee() == Suit.TRUMP && pli_.carteDuJoueur(_numero) == CardTarot.petit()) {//jeu du Petit sur demande d'atout
                if (confidence.get(_numero).get(pli_.getRamasseur()) == Confidence.NO) {
                    playedSmall_ = true;
                }
                break;
            }
        }
        return playedSmall_;
    }

    public HandTarot cartesJoueesEnCours() {
        return enCours(progressingTrick, nbPlayers, taker, bid, tricks, tricksSeen);
    }

    public static HandTarot enCours(GameTarot _gt) {
        CustList<TrickTarot> trs_ = _gt.getTricks();
        CustList<TrickTarot> seen_ = tricksSeen(trs_);
        return enCours(_gt.getProgressingTrick(), _gt.getNombreDeJoueurs(), _gt.getPreneur(), _gt.getContrat(), trs_, seen_);
    }
    static HandTarot enCours(TrickTarot _pr, int _nbPlayers, int _taker, BidTarot _bid, CustList<TrickTarot> _tricks, CustList<TrickTarot> _tricksSeen) {
        HandTarot retour_ = cartesJouees(_pr, _nbPlayers, _taker, _bid, _tricks, _tricksSeen);
        retour_.ajouterCartes(_pr.getCartes());
        return retour_;
    }

    static HandTarot cartesJouees(TrickTarot _pr, int _nbPlayers, int _taker, BidTarot _bid, CustList<TrickTarot> _tricks, CustList<TrickTarot> _tricksSeen) {
        HandTarot m = new HandTarot();
        if (_pr.getNextPlayer(_nbPlayers) == _taker && _bid.getJeuChien() == PlayingDog.WITH) {
            for (TrickTarot t: _tricks) {
                m.ajouterCartes(t.getCartes());
            }
        } else {
            for (TrickTarot t: _tricksSeen) {
                m.ajouterCartes(t.getCartes());
            }
        }
        return m;
    }

    /**
     Retourne vrai si le joueur ne peut pas jouer de l'atout sur demande
     d'atout ou sur demande de coupe de couleur sauf pli en cours
     */
    static boolean defausseTarot(int _numero, CustList<TrickTarot> _unionPlis) {
        return coupeTarot(Suit.TRUMP, _numero, _unionPlis);
    }

    /**
     Retourne vrai si le joueur ne peut pas jouer de l'atout ni fournir sur
     demande de la couleur "couleur" sauf pli en cours
     */
    static boolean defausseTarot(int _numero, Suit _couleurDonnee,
                                         CustList<TrickTarot> _unionPlis) {
//        boolean coupe_ = coupeTarot(Suit.TRUMP, _numero, _unionPlis);
//        // coupe retourne vrai si on sait que le joueur ne
//        // peut que jouer de l'atout sur des couleurs
//        for (Suit couleur_ : Suit.couleursOrdinaires()) {
//            if (coupeTarot(couleur_, _numero, _unionPlis)) {
//                coupe_ = true;
//            }
//        }
//        // coupe est vrai si et seulement si il existe au moins une coupe a une
//        // des couleurs
//        if (!coupe_) {
//            return false;
//        }
        // Le joueur a deja joue une carte d'une autre couleur que celle
        // demandee differente de l'atout
        int lastIndex_ = _unionPlis.size() - 1;
        for (int b = lastIndex_; b >= IndexConstants.FIRST_INDEX; b--) {
            TrickTarot pli_ = _unionPlis.get(b);
//            if (!pli_.getVuParToutJoueur()) {
//                continue;
//            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (_couleurDonnee == couleurDemandee_) {
                Suit couleurCarte_ = pli_.carteDuJoueur(_numero).getId().getCouleur();
//                if (couleurCarte_ != couleurDemandee_ && couleurCarte_ != Suit.TRUMP)
                if (couleurCarte_ != couleurDemandee_ && couleurCarte_ != Suit.TRUMP && couleurCarte_ != Suit.UNDEFINED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     Retourne vrai dans les cas suivants
     <ol>
     <li>Si couleur vaut 1(C'est la couleur de l'atout (Excuse exclue)), alors
     vrai est retourne lorsque le joueur a joue une couleur differente de
     l'atout sur entame atout ou sur une entame de couleur differente de
     l'atout en ayant fourni une carte autre que de l'atout et celle qui est
     demandee et l'Excuse sauf pli en cours</li>
     <li>Sinon vrai est retourne lorsque le joueur a joue un atout sur entame
     d'une couleur autre que de l'atout sauf pli en cours</li>
     </ol>
     */
    static boolean coupeTarot(Suit _couleur, int _numero,
                                      CustList<TrickTarot> _unionPlis) {
        if (Suit.couleursOrdinaires().containsObj(_couleur)) {
            int lastIndex_ = _unionPlis.size() - 1;
            for (int b = lastIndex_; b >= IndexConstants.FIRST_INDEX; b--) {
                TrickTarot pli_ = _unionPlis.get(b);
                if (pli_.couleurDemandee() != _couleur || pli_.carteDuJoueur(_numero).getId().getCouleur() != Suit.TRUMP) {
                    //!pli_.getVuParToutJoueur() ||
                    continue;
                }
                // On ne cherche que les plis dont la couleur demande
                // est couleur
                return true;
            }
            return false;
        }
        // Le joueur ne coupe pas la couleur passee en parametre a ce niveau si
        // couleur > 1
        // couleur == Couleu.Atout
        int lastIndex_ = _unionPlis.size() - 1;
        for (int b = lastIndex_; b >= IndexConstants.FIRST_INDEX; b--) {
            TrickTarot pli_ = _unionPlis.get(b);
//            if (!pli_.getVuParToutJoueur()) {
//                continue;
//            }

            Suit couleurDemandee_ = pli_.couleurDemandee();
            Suit couleurJoueur_ = pli_.carteDuJoueur(_numero).getId().getCouleur();
            if (Suit.couleursOrdinaires().containsObj(couleurJoueur_) && (couleurDemandee_ == _couleur || couleurJoueur_ != couleurDemandee_)) {
                /*
                Si la couleur demandee
                est atout alors il suffit
                que le joueur n'ait pas
                joue de l'atout pour
                conclure qu'il ne possede
                pas d'atout sinon on
                verifie de plus que la
                couleur fournie par le
                joueur est une autre
                couleur que celle
                demandee
                */
                //couleurDemandee est une couleur ordinaire
                //couleurJoueur est une couleur ordinaire
                //donc le joueur se defausse
                return true;
            }
        }

        return false;
    }
    static boolean plisTousFaitsPar(Ints _joueurs,
                                    CustList<TrickTarot> _unionPlis, int _nombreJoueurs) {
        Ints autresJoueurs_ = GameTarotTeamsRelation.autresJoueurs(_joueurs, _nombreJoueurs);
        for (TrickTarot pli_ : _unionPlis.mid(1)) {
//            if (!pli_.getVuParToutJoueur()) {
//                continue;
//            }
            if (autresJoueurs_.containsObj(pli_.getRamasseur())) {
                return false;
            }
        }
        return true;
    }
    HandTarot getPoignee(int _b) {
        return handfuls.get(_b);
    }

    TrickTarot getProgressingTrick() {
        return progressingTrick;
    }

    HandTarot getCalledCards() {
        return calledCards;
    }

    CustList<TrickTarot> getTricks() {
        return tricks;
    }

    BidTarot getBid() {
        return bid;
    }

    HandTarot getLastSeenHand() {
        return lastSeenHand;
    }
}
