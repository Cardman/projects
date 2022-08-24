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

    private final CustList<EnumList<Miseres>> declaresMiseres;

    private final CustList<HandTarot> handfuls;
    private final BidTarot bid;
    private final HandTarot calledCards;
    private final Ints handLengths;

    private final HandTarot lastSeenHand = new HandTarot();
    private byte nbPlayers;
    private byte taker;
    private RulesTarot rules;
    private final CustList<CustList<Confidence>> confidence = new CustList<CustList<Confidence>>();

    public GameTarotTrickInfo(TrickTarot _progressingTrick, CustList<TrickTarot> _tricks,
                              CustList<EnumList<Miseres>> _declaresMiseres,
                              CustList<HandTarot> _handfuls, BidTarot _bid, HandTarot _calledCards,
                              Ints _handLengths) {
        progressingTrick = _progressingTrick;
        tricks = _tricks;
        declaresMiseres = _declaresMiseres;
        handfuls = _handfuls;
        bid = _bid;
        calledCards = _calledCards;
        handLengths = _handLengths;
        for (TrickTarot t: tricks) {
            if (t.getVuParToutJoueur()) {
                tricksSeen.add(t);
            }
        }
    }

    void addSeenDeck(HandTarot _h, GameTarotTeamsRelation _rel) {
        nbPlayers = _rel.getNombreDeJoueurs();
        taker = _rel.getTaker();
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        if (cartesJoueesEnCours(next_).contientCartes(calledCards)) {
            for (byte i = 0; i < nbPlayers; i++) {
                CustList<Confidence> l_ = new CustList<Confidence>();
                feedConf(_rel, i, l_);
                confidence.add(l_);
            }
        } else {
            for (byte i = 0; i < nbPlayers; i++) {
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

    private void feedConf(GameTarotTeamsRelation _rel, byte _i, CustList<Confidence> _l) {
        for (byte j = 0; j < nbPlayers; j++) {
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
    public EnumMap<Suit,CustList<HandTarot>> cartesPossibles(HandTarot _cartesJoueur) {
        EnumMap<Suit,CustList<HandTarot>> m = new EnumMap<Suit,CustList<HandTarot>>();
        CustList<HandTarot> possibleExcuse_ = excusePossibleRegles(_cartesJoueur);
        m.put(CardTarot.EXCUSE.getId().getCouleur(), possibleExcuse_);
        m.put(Suit.TRUMP,atoutsPossibles(
                _cartesJoueur));
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            // On fait une boucle sur les
            // couleurs autres que l'atout
            m.put(couleur_,cartesPossibles(couleur_,
                    _cartesJoueur));
        }
        return m;
    }

    CustList<HandTarot> excusePossibleRegles(HandTarot _cartesJoueur) {
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        HandTarot playedCards_ = cartesJoueesEnCours(next_);
        boolean plExcuse_ = playedCards_.contient(CardTarot.EXCUSE);
        boolean containsExcuse_ = _cartesJoueur.contient(CardTarot.EXCUSE);
        boolean noExc_ = plExcuse_ || containsExcuse_;
        CustList<HandTarot> possibleExcuse_ = new CustList<HandTarot>();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
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
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
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

    private void playerExcuse(byte _next, boolean _containsExcuse, boolean _noExc, byte _joueur, HandTarot _h) {
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

    private void handfulExcuse(byte _next, CustList<HandTarot> _possibleExcuse) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
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
            for (byte i = IndexConstants.FIRST_INDEX; i < nbHandfuls_; i++) {
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
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        HandTarot playedCards_ = cartesJoueesEnCours(next_);
        boolean playedCalledCard_ = playedCards_.contientCartes(calledCards);
        CustList<HandTarot> m = atoutsPossiblesRegles(_curHand);
        for (byte i = 0; i < nbPlayers; i++) {
            HandTarot main_ = m.get(i);
            if (i == next_) {
                continue;
            }
            //filtre sur le jeu d'une carte couleur atout apres un adversaire ramasseur
            HandTarot atoutsFiltres_ = sousCoupeTarot(next_, _curHand,i,
                    main_);
            m.set(i, atoutsFiltres_);
        }
        for (byte i = 0; i < nbPlayers; i++) {
            HandTarot main_ = m.get(i);
            if (i == next_) {
                continue;
            }
            //filtre sur la fourniture d'un atout a une couleur
            HandTarot atoutsFiltres_ = coupeTarot(next_, _curHand,i,
                    main_);
            m.set(i, atoutsFiltres_);
        }
        if(playedCalledCard_) {
            for (byte i = 0; i < nbPlayers; i++) {
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
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        EnumMap<Suit,HandTarot> curRep_ = _curHand.couleurs();
        HandTarot playedCards_ = cartesJoueesEnCours(next_);
        EnumMap<Suit,HandTarot> plRep_ = playedCards_.couleurs();
        HandTarot plTr_ = plRep_.getVal(Suit.TRUMP);
        HandTarot curTr_ = curRep_.getVal(Suit.TRUMP);
        CustList<HandTarot> m = new CustList<HandTarot>();

        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
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

    private void handfulsAll(byte _next, CustList<HandTarot> _m) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
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

    private void tricksTrumps(byte _next, CustList<HandTarot> _m) {
        CustList<TrickTarot> plis_ = new CustList<TrickTarot>(tricks);
        plis_.add(progressingTrick);
        for(TrickTarot pli_:plis_) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemande_=pli_.couleurDemandee();
            for(CardTarot c: pli_) {
                trickCard(_next, _m, pli_, couleurDemande_, c);
            }

        }
    }

    private void excludeTrumpByDog(byte _next, CustList<HandTarot> _m) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            if (joueur_ == _next) {
                continue;
            }
            excludeTrumpByDogInd(_m, joueur_);
        }
    }

    private void excludeTrumpByDogInd(CustList<HandTarot> _m, byte _joueur) {
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

    private void handfuls(CustList<HandTarot> _m, byte _joueur) {
        int nbHandfuls_ = handfuls.size();
        for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ < nbHandfuls_; joueur2_++) {
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

    private void trickCard(byte _next, CustList<HandTarot> _m, TrickTarot _pli, Suit _couleurDemande, CardTarot _c) {
        if (_c.getId().getCouleur() != Suit.TRUMP) {
            return;
        }
        byte joueur_ = _pli.joueurAyantJouePliEnCours(_c,nbPlayers);
        if (joueur_ == _next) {
            return;
        }
        Bytes joueursAvant_ = _pli.joueursAyantJoueAvant(joueur_, rules.getDealing());
        byte forceLoc_ = _c.strength(_couleurDemande);
        byte max_ = 0;
        byte ramasseurVirtuel_ = joueur_;
        //joueursAvant non vide
        for(byte j: joueursAvant_) {
            CardTarot carte_ = _pli.carteDuJoueur(j,nbPlayers);
            byte forceLoc2_ = carte_.strength(_couleurDemande);
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

    private void playerTr(byte _next, HandTarot _plTr, HandTarot _curTr, CustList<HandTarot> _m, byte _joueur, HandTarot _h) {
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
        if (GameTarotTrickInfo.defausseTarot(_joueur, tricks)) {
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
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        HandTarot playedCards_ = cartesJoueesEnCours(next_);
        boolean playedCalledCard_ = playedCards_.contientCartes(calledCards);
        CustList<HandTarot> m = cartesPossiblesRegles(_couleur,_curHand);
        for (byte i = 0; i < nbPlayers; i++) {
            HandTarot couleurLoc_ = m.get(i);
            if (i == next_ || couleurLoc_.estVide()) {
                continue;
            }
            Suit noCouleur_ = couleurLoc_.premiereCarte().getId().getCouleur();
            //filtre sur le jeu d'une carte couleur ordinaire apres un adversaire ramasseur
            HandTarot atoutsFiltres_ = joueCarteBasseTarot(next_,_curHand,
                    i, noCouleur_, couleurLoc_, tricks);
            m.set(i, atoutsFiltres_);
        }
        if (playedCalledCard_) {
            for (byte i = 0; i < nbPlayers; i++) {
                HandTarot couleurLoc_ = m.get(i);
                if (i == next_ || couleurLoc_.estVide()) {
                    continue;
                }
                Suit noCouleur_ = couleurLoc_.premiereCarte().getId().getCouleur();
                HandTarot filteredCharacters_ = playCharacterCardTarot(
                        i, noCouleur_, couleurLoc_, tricks);
                m.set(i, filteredCharacters_);
            }
        }
        return m;
    }

    CustList<HandTarot> cartesPossiblesRegles(Suit _couleur,
                                            HandTarot _curHand) {
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        HandTarot playedCards_ = cartesJoueesEnCours(next_);
        CustList<HandTarot> m = new CustList<HandTarot>();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
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
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
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

    private void excludeByDog(Suit _couleur, CustList<HandTarot> _m, byte _joueur) {
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

    private void dog(Suit _couleur, HandTarot _curHand, byte _next, HandTarot _playedCards, CustList<HandTarot> _m) {
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

    private void currentTrick(Suit _couleur, byte _next, CustList<HandTarot> _m) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
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

    private void plNormal(Suit _couleur, HandTarot _curHand, byte _next, HandTarot _playedCards, byte _joueur, HandTarot _h) {
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
        if (GameTarotTrickInfo.defausseTarot(_joueur, _couleur, tricks)
                || GameTarotTrickInfo.coupeTarot(_couleur, _joueur, tricks)) {
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
                for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                    byte pl_ = progressingTrick.joueurAyantJouePliEnCours(cardTarot_, (byte) rules.getDealing().getId().getNombreJoueurs());
                    supprimerCartes(pl_, joueur_, _m, _copy, _all);
                }
            }
        } else if (tricks.get(1).couleurDemandee() == _couleur) {
            CardTarot cardTarot_ = tricks.get(1).premiereCarteNonExc();
            if (notApplyFilter(_calledCard, cardTarot_)) {
                return;
            }
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                byte pl_ = tricks.get(1).joueurAyantJoue(cardTarot_);
                supprimerCartes(pl_, joueur_, _m, _copy, _all);
            }
        }
    }

    private void supprimerCartes(byte _pl, byte _joueur, CustList<HandTarot> _m, HandTarot _copy, boolean _all) {
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
    public EnumMap<Hypothesis,EnumMap<Suit,CustList<HandTarot>>> cartesCertaines(
            EnumMap<Suit, CustList<HandTarot>> _cartesPossibles) {
        Bytes joueursRepartitionConnue_ = new Bytes();
        Bytes joueursRepartitionConnue2_ = new Bytes();
        Bytes joueursRepartitionConnueMemo_ = new Bytes();
        Bytes joueursRepartitionInconnue_ = new Bytes();
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_ = new EnumMap<Suit,CustList<HandTarot>>();
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_ = new EnumMap<Suit,CustList<HandTarot>>(
                _cartesPossibles);
        EnumMap<Hypothesis,EnumMap<Suit,CustList<HandTarot>>> retour_ = new EnumMap<Hypothesis,EnumMap<Suit,CustList<HandTarot>>>();
        /*
        Indique le nombre de mains pour les
        cartes possibles ou apparait la carte
        */
        EnumList<Suit> toutesCouleurs_ = new EnumList<Suit>();
        toutesCouleurs_.add(CardTarot.EXCUSE.getId().getCouleur());
        toutesCouleurs_.add(Suit.TRUMP);
        toutesCouleurs_.addAllElts(Suit.couleursOrdinaires());
        for(Suit couleur_: toutesCouleurs_) {
            cartesCertaines_.put(couleur_,new CustList<HandTarot>());
        }
        for (Suit couleur_:cartesCertaines_.getKeys()) {
            for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ <= nbPlayers; joueur_++) {
                cartesCertaines_.getVal(couleur_).add(new HandTarot());
            }
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ <= nbPlayers; joueur_++) {
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
        for (byte joueur_ : joueursRepartitionInconnue_) {
            cartesCertaines_.getVal(Suit.TRUMP).get(joueur_).trierParForceEnCours(Suit.TRUMP);
            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                cartesCertaines_.getVal(couleur_).get(joueur_).trierParForceEnCours(couleur_);
            }
        }
        retour_.put(Hypothesis.POSSIBLE, cartesPossibles_);
        retour_.put(Hypothesis.SURE, cartesCertaines_);
        return retour_;
    }

    private void iterate(Bytes _joueursRepartitionConnue, Bytes _joueursRepartitionConnue2, Bytes _joueursRepartitionConnueMemo, Bytes _joueursRepartitionInconnue, EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, EnumList<Suit> _toutesCouleurs) {
        for (byte joueur_ : _joueursRepartitionConnue) {
            for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ <= nbPlayers; joueur2_++) {
                if (!_joueursRepartitionConnueMemo.containsObj(joueur2_)) {
                    remImposTarot(_cartesCertaines, _cartesPossibles, _toutesCouleurs, joueur_, joueur2_);
                }
                addToKnown(_toutesCouleurs, _cartesPossibles,joueur_, _cartesCertaines, _joueursRepartitionConnue2, _joueursRepartitionConnueMemo);
            }
        }
        nextPlayers(_joueursRepartitionConnueMemo, _joueursRepartitionInconnue, nbPlayers);
        for (byte joueur_ : _joueursRepartitionInconnue) {
            validatePlayerTarot(_cartesCertaines, _cartesPossibles, _toutesCouleurs, joueur_);
            addToKnown(_toutesCouleurs, _cartesCertaines,joueur_, _cartesPossibles, _joueursRepartitionConnue2, _joueursRepartitionConnueMemo);
        }
        SortedPlayers.shift(_joueursRepartitionConnue,_joueursRepartitionConnue2,_joueursRepartitionInconnue);
    }

    static void nextPlayers(Bytes _joueursRepartitionConnueMemo, Bytes _joueursRepartitionInconnue, byte _nbPlayers) {
        SortedPlayers.nextPlayers(_joueursRepartitionConnueMemo,_joueursRepartitionInconnue, (byte) (_nbPlayers+1));
    }

    private void validatePlayerTarot(EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, EnumList<Suit> _toutesCouleurs, byte _joueur) {
        for (Suit couleur_: _toutesCouleurs) {
            for (CardTarot carte_ : _cartesPossibles.getVal(couleur_).get(
                    _joueur)) {
                int nombreDApparitionCarte_ = 0;
                for (byte joueur2_ = IndexConstants.FIRST_INDEX; joueur2_ <= nbPlayers; joueur2_++) {
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

    private void remImposTarot(EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, EnumList<Suit> _toutesCouleurs, byte _joueur, byte _joueur2) {
        for (Suit couleur_: _toutesCouleurs) {
            remSure(_cartesCertaines, _joueur, _joueur2, couleur_);
            remPoss(_cartesCertaines, _cartesPossibles, _joueur, _joueur2, couleur_);
        }
    }

    private void remPoss(EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, EnumMap<Suit, CustList<HandTarot>> _cartesPossibles, byte _joueur, byte _joueur2, Suit _couleur) {
        _cartesPossibles.getVal(_couleur)
                .get(_joueur2).supprimerCartes(
                _cartesCertaines.getVal(_couleur).get(
                        _joueur));
    }

    private void remSure(EnumMap<Suit, CustList<HandTarot>> _cartesCertaines, byte _joueur, byte _joueur2, Suit _couleur) {
        _cartesCertaines.getVal(_couleur)
                .get(_joueur2).supprimerCartes(
                        _cartesCertaines.getVal(_couleur).get(
                                _joueur));
    }

    void addToKnown(EnumList<Suit> _all,EnumMap<Suit,CustList<HandTarot>> _poss,byte _player,
                    EnumMap<Suit,CustList<HandTarot>> _sure,
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
    static void affect(EnumList<Suit> _all,EnumMap<Suit,CustList<HandTarot>> _from, byte _player,EnumMap<Suit,CustList<HandTarot>> _to) {
        for (Suit s: _all) {
            _to.getVal(s).get(_player).supprimerCartes();
            _to.getVal(s).get(_player).ajouterCartes(_from.getVal(s).get(_player));
        }
    }

    HandTarot playCharacterCardTarot(byte _numero,
                                     Suit _couleur, HandTarot _probablyCharacterCard,
                                     CustList<TrickTarot> _unionPlis) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_probablyCharacterCard);
        HandTarot playedCards_ = new HandTarot();
        ByteMap<BoolVal> defausses_ = defausses(_unionPlis);
        for (TrickTarot pli_ : _unionPlis) {
            if (!pli_.getVuParToutJoueur() || pli_.getEntameur() == _numero) {
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

    HandTarot joueCarteBasseTarot(byte _joueurCourant, HandTarot _curHand, byte _numero,
                                  Suit _couleur, HandTarot _cartesCouleurPossibles,
                                  CustList<TrickTarot> _unionPlis) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_cartesCouleurPossibles);
        HandTarot cartesJouees_ = cartesJoueesEnCours(_joueurCourant);
        HandTarot playedCards_ = new HandTarot();
        for (TrickTarot pli_ : _unionPlis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
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

    HandTarot sousCoupeTarot(byte _joueurCourant, HandTarot _curHand, byte _numero,
                             HandTarot _atoutsPossibles) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_atoutsPossibles);
        HandTarot cartesJouees_ = cartesJoueesEnCours(_joueurCourant);
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

    private boolean sousCoupe(byte _numero, TrickTarot _pli, CardTarot _carteObservee) {
        if (_carteObservee.getId().getCouleur() != Suit.TRUMP) {
            return false;
        }
        boolean sousCoupe_ = false;
        Suit couleurDemandee_ = _pli.couleurDemandee();
        byte force_ = _carteObservee.strength(couleurDemandee_);
        for(byte j: _pli.joueursAyantJoueAvant(_numero, rules.getDealing())) {
            if (_pli.carteDuJoueur(j).strength(couleurDemandee_) >= force_) {
                sousCoupe_ = true;
                break;
            }
        }
        return sousCoupe_;
    }

    HandTarot coupeTarot(byte _joueurCourant, HandTarot _curHand, byte _numero,
                         HandTarot _atoutsPossibles) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_atoutsPossibles);
        HandTarot cartesJouees_ = cartesJoueesEnCours(_joueurCourant);
        CustList<TrickTarot> plis_ = new CustList<TrickTarot>();
        ByteMap<HandTarot> atoutsJouesPlis_ = new ByteMap<HandTarot>();
        ByteMap<BoolVal> defausses_ = defausses(tricks);
        byte key_ = 0;
        for (TrickTarot pli_ : tricksSeen) {
            HandTarot atoutsJouesAvant_ = new HandTarot();
            if (!coupe(_numero, pli_, atoutsJouesAvant_) || !defausseToutJoueurApres(_numero, defausses_, pli_)) {
                key_++;
                continue;
            }
            atoutsJouesPlis_.put(key_, atoutsJouesAvant_);
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
        for (EntryCust<Byte,HandTarot> e: atoutsJouesPlis_.entryList()) {
            excludeLowerTrumpsOverMaxCard(_numero, retour_, cartesVues_, e.getValue(), plis_.get(key_));
            key_++;
        }
        return retour_;
    }

    private ByteMap<BoolVal> defausses(CustList<TrickTarot> _tricks) {
        ByteMap<BoolVal> defausses_ = new ByteMap<BoolVal>();
        for (byte j = IndexConstants.FIRST_INDEX; j<nbPlayers; j++) {
            defausses_.put(j, ComparatorBoolean.of(GameTarotTrickInfo.defausseTarot(j, _tricks)));
        }
        return defausses_;
    }

    private boolean coupe(byte _numero, TrickTarot _pli, HandTarot _atoutsJouesAvant) {
        if (_pli.getEntameur() == _numero) {
            return false;
        }
        CardTarot carteObservee_ = _pli.carteDuJoueur(_numero);
        if (carteObservee_.getId().getCouleur() != Suit.TRUMP) {
            return false;
        }
        boolean coupe_ = true;
        Suit couleurDemandee_ = _pli.couleurDemandee();
        byte force_ = carteObservee_.strength(couleurDemandee_);
        for(byte j: _pli.joueursAyantJoueAvant(_numero, rules.getDealing())) {
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

    private boolean defausseToutJoueurApres(byte _numero, ByteMap<BoolVal> _defausses, TrickTarot _pli) {
        boolean defausseToutJoueurApres_ = true;
        for(byte j: _pli.joueursAyantJoueApres(_numero, rules.getDealing())) {
            if (_defausses.getVal(j) != BoolVal.TRUE) {
                defausseToutJoueurApres_ = false;
                break;
            }
        }
        return defausseToutJoueurApres_;
    }

    private void excludeLowerTrumpsOverMaxCard(byte _numero, HandTarot _retour, HandTarot _cartesVues, HandTarot _atoutsJouesAvant, TrickTarot _pli) {
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

    private HandTarot subCardSeenCards(byte _numero, HandTarot _cartesVues, TrickTarot _pli) {
        CardTarot carteObservee_ = _pli.carteDuJoueur(_numero);
        return subCardSeenCards(_cartesVues, Suit.TRUMP, carteObservee_);
    }

    boolean petitJoueDemandeAtoutRamasseurAdv(byte _numero) {
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

    public HandTarot cartesJoueesEnCours(byte _numero) {
        HandTarot retour_ = cartesJouees(_numero);
        retour_.ajouterCartes(progressingTrick.getCartes());
        return retour_;
    }
    HandTarot cartesJouees(byte _numero) {
        HandTarot m = new HandTarot();
        if (_numero == taker && bid.getJeuChien() == PlayingDog.WITH) {
            for (TrickTarot t: tricks) {
                m.ajouterCartes(t.getCartes());
            }
        } else {
            for (TrickTarot t: tricksSeen) {
                m.ajouterCartes(t.getCartes());
            }
        }
        return m;
    }

    /**
     Retourne vrai si le joueur ne peut pas jouer de l'atout sur demande
     d'atout ou sur demande de coupe de couleur sauf pli en cours
     */
    static boolean defausseTarot(byte _numero, CustList<TrickTarot> _unionPlis) {
        return coupeTarot(Suit.TRUMP, _numero, _unionPlis);
    }

    /**
     Retourne vrai si le joueur ne peut pas jouer de l'atout ni fournir sur
     demande de la couleur "couleur" sauf pli en cours
     */
    static boolean defausseTarot(byte _numero, Suit _couleurDonnee,
                                         CustList<TrickTarot> _unionPlis) {
        boolean coupe_ = coupeTarot(Suit.TRUMP, _numero, _unionPlis);
        // coupe retourne vrai si on sait que le joueur ne
        // peut que jouer de l'atout sur des couleurs
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if (coupeTarot(couleur_, _numero, _unionPlis)) {
                coupe_ = true;
            }
        }
        // coupe est vrai si et seulement si il existe au moins une coupe a une
        // des couleurs
        if (!coupe_) {
            return false;
        }
        // Le joueur a deja joue une carte d'une autre couleur que celle
        // demandee differente de l'atout
        int lastIndex_ = _unionPlis.size() - 1;
        for (int b = lastIndex_; b >= IndexConstants.FIRST_INDEX; b--) {
            TrickTarot pli_ = _unionPlis.get(b);
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (_couleurDonnee == couleurDemandee_) {
                Suit couleurCarte_ = pli_.carteDuJoueur(_numero).getId().getCouleur();
                if (couleurCarte_ != couleurDemandee_ && couleurCarte_ != Suit.TRUMP) {
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
    static boolean coupeTarot(Suit _couleur, byte _numero,
                                      CustList<TrickTarot> _unionPlis) {
        if (Suit.couleursOrdinaires().containsObj(_couleur)) {
            int lastIndex_ = _unionPlis.size() - 1;
            for (int b = lastIndex_; b >= IndexConstants.FIRST_INDEX; b--) {
                TrickTarot pli_ = _unionPlis.get(b);
                if (!pli_.getVuParToutJoueur() || pli_.couleurDemandee() != _couleur || pli_.carteDuJoueur(_numero).getId().getCouleur() != Suit.TRUMP) {
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
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }

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
    static boolean plisTousFaitsPar(Bytes _joueurs,
                                    CustList<TrickTarot> _unionPlis, byte _nombreJoueurs) {
        Bytes autresJoueurs_ = GameTarotTeamsRelation.autresJoueurs(_joueurs, _nombreJoueurs);
        for (TrickTarot pli_ : _unionPlis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            if (autresJoueurs_.containsObj(pli_.getRamasseur())) {
                return false;
            }
        }
        return true;
    }
    HandTarot getPoignee(byte _b) {
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
