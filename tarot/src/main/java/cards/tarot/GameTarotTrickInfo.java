package cards.tarot;

import cards.consts.CardChar;
import cards.consts.Hypothesis;
import cards.consts.Suit;
import cards.tarot.enumerations.*;
import code.util.*;

public final class GameTarotTrickInfo {

    private TrickTarot progressingTrick;
    private CustList<TrickTarot> tricks;

    private EqList<EnumList<Miseres>> declaresMiseres;

    private EqList<HandTarot> handfuls;
    private BidTarot bid;
    private HandTarot calledCards;
    private Numbers<Integer> handLengths;

    private HandTarot lastSeenHand = new HandTarot();
    private byte nbPlayers;
    private byte taker;
    private RulesTarot rules;
    private CustList<CustList<Confidence>> confidence = new CustList<CustList<Confidence>>();

    public GameTarotTrickInfo(TrickTarot _progressingTrick, CustList<TrickTarot> _tricks,
                              EqList<EnumList<Miseres>> _declaresMiseres,
                              EqList<HandTarot> _handfuls, BidTarot _bid, HandTarot _calledCards,
                              Numbers<Integer> _handLengths) {
        progressingTrick = _progressingTrick;
        tricks = _tricks;
        declaresMiseres = _declaresMiseres;
        handfuls = _handfuls;
        bid = _bid;
        calledCards = _calledCards;
        handLengths = _handLengths;
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
    public EnumMap<Suit,EqList<HandTarot>> cartesPossibles(HandTarot _cartesJoueur) {
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        HandTarot playedCards_ = cartesJoueesEnCours(next_);
        boolean plExcuse_ = playedCards_.contient(CardTarot.EXCUSE);
        boolean containsExcuse_ = _cartesJoueur.contient(CardTarot.EXCUSE);
        boolean noExc_ = plExcuse_ || containsExcuse_;
        EnumMap<Suit,EqList<HandTarot>> m = new EnumMap<Suit,EqList<HandTarot>>();
        EqList<HandTarot> possibleExcuse_ = new EqList<HandTarot>();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            HandTarot h_ = new HandTarot();
            possibleExcuse_.add(h_);
            if(joueur_ == next_) {
                if (containsExcuse_) {
                    h_.ajouter(CardTarot.EXCUSE);
                }
                continue;
            }
            if (!noExc_) {
                h_.ajouter(CardTarot.EXCUSE);
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.POINT)
                    || declaresMiseres.get(joueur_).containsObj(Miseres.TRUMP)) {
                h_.supprimerCartes();
            }
        }
        possibleExcuse_.add(new HandTarot());
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
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
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
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            // L'Excuse dans
            // une poignee
            // annule toute
            // possibilite
            // qu'un autre
            // joueur ait
            // celle-ci
            if (joueur_ == next_) {
                continue;
            }
            int nbHandfuls_ = handfuls.size();
            for (byte i = CustList.FIRST_INDEX; i < nbHandfuls_; i++) {
                if (!getPoignee(i).contient(CardTarot.excuse())) {
                    continue;
                }
                /* The current poignee contains the Excuse*/
                if (i != joueur_) {
                    possibleExcuse_.get(joueur_).supprimerCartes();
                }
            }
        }
        for (HandTarot poignee_ : handfuls) {
            if (poignee_.contient(CardTarot.EXCUSE)) {
                possibleExcuse_.last().supprimerCartes();
            }
        }
        m.put(CardTarot.EXCUSE.couleur(), possibleExcuse_);
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

    /**
     Retourne l'ensemble des atouts (sans l'Excuse) probablement possedes par
     les autres joueurs
     @param _numero
     */
    EqList<HandTarot> atoutsPossibles(HandTarot _curHand) {
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        EnumMap<Suit,HandTarot> curRep_ = _curHand.couleurs();
        HandTarot playedCards_ = cartesJoueesEnCours(next_);
        boolean playedCalledCard_ = playedCards_.contientCartes(calledCards);
        EnumMap<Suit,HandTarot> plRep_ = playedCards_.couleurs();
        HandTarot plTr_ = plRep_.getVal(Suit.TRUMP);
        HandTarot curTr_ = curRep_.getVal(Suit.TRUMP);
        EqList<HandTarot> m = new EqList<HandTarot>();

        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            HandTarot h_ = new HandTarot();
            m.add(h_);
            if(joueur_ == next_) {
                h_.ajouterCartes(curTr_);
                continue;
            }
            for (CardTarot carte_ : HandTarot.atoutsSansExcuse()) {
                if (!plTr_.contient(carte_)
                        && !curTr_.contient(carte_)) {
                    h_.ajouter(carte_);
                }
            }
            if (GameTarotTrickInfo.defausseTarot(joueur_, tricks)) {
                // Les joueurs se defaussant
                // sur atout ou couleur
                // demandee ne peuvent pas
                // avoir de l'atout
                h_.supprimerCartes();
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.TRUMP)) {
                h_.supprimerCartes();
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.POINT)) {
                h_.supprimerCartes(m.get(joueur_).bouts());
            }
        }
        m.add(new HandTarot());
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
        CustList<TrickTarot> plis_ = new CustList<TrickTarot>(tricks);
        plis_.add(progressingTrick);
        for(TrickTarot pli_:plis_) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemande_=pli_.couleurDemandee();
            for(CardTarot c: pli_) {
                if (c.couleur() != Suit.TRUMP) {
                    continue;
                }
                byte joueur_ = pli_.joueurAyantJouePliEnCours(c,nbPlayers);
                if (joueur_ == next_) {
                    continue;
                }
                Numbers<Byte> joueursAvant_ = pli_.joueursAyantJoueAvant(joueur_,nbPlayers, rules.getDealing());
                byte forceLoc_ = c.strength(couleurDemande_);
                byte max_ = 0;
                byte ramasseurVirtuel_ = joueur_;
                //joueursAvant non vide
                for(byte j: joueursAvant_) {
                    CardTarot carte_ = pli_.carteDuJoueur(j,nbPlayers);
                    byte forceLoc2_ = carte_.strength(couleurDemande_);
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
                HandTarot cartesExclues_ = new HandTarot();
                for(CardTarot c2_: m.get(joueur_)) {
                    if(c2_.strength(couleurDemande_) < max_) {
                        continue;
                    }
                    cartesExclues_.ajouter(c2_);
                }
                m.get(joueur_).supprimerCartes(cartesExclues_);
            }

        }
        if (bid.getJeuChien() == PlayingDog.WITH) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                if (joueur_ == next_) {
                    continue;
                }
                if (taker != joueur_) {
                    // Les atouts du chien (si il est vu) ne peuvent possedes
                    // que par le preneur
                    for (CardTarot carte_ : lastSeenHand) {
                        if (!Suit.couleursOrdinaires().containsObj(carte_.couleur())) {
                            m.get(joueur_).removeCardIfPresent(carte_);
                        }
                    }
                }
                /*
                Les atouts eventuellement ecartes au chien sont vus par les
                autres joueurs et ne peuvent pas etre joues dans les plis
                suivants
                */
                for (CardTarot carte_ : tricks.first()) {
                    if (carte_.couleur() == Suit.TRUMP) {
                        m.get(joueur_).removeCardIfPresent(carte_);
                    }
                }
            }
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            if (joueur_ == next_) {
                continue;
            }
            int nbHandfuls_ = handfuls.size();
            for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ < nbHandfuls_; joueur2_++) {
                if (joueur2_ != joueur_) {
                    m.get(joueur_).supprimerCartes(getPoignee(joueur2_));
                } else if (getPoignee(joueur_).contient(CardTarot.excuse())) {
                    HandTarot atoutsPoignee_ = new HandTarot();
                    for (CardTarot c: m.get(joueur_)) {
                        if (!getPoignee(joueur_)
                                .contient(c)) {
                            continue;
                        }
                        atoutsPoignee_.ajouter(c);
                    }
                    m.set(joueur_, atoutsPoignee_);
                }
            }
            if (!progressingTrick.aJoue(joueur_, nbPlayers)) {
                continue;
            }
            CardTarot carteDuJoueur_ = progressingTrick.carteDuJoueur(
                    joueur_, nbPlayers);
            Suit couleurDemandee_ = progressingTrick.couleurDemandee();
            if (Suit.couleursOrdinaires().containsObj(carteDuJoueur_.couleur())
                    && couleurDemandee_ != carteDuJoueur_.couleur()) {
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
                m.get(joueur_).supprimerCartes();
            }
        }
        for (HandTarot main_ : handfuls) {
            for (CardTarot carte_ : main_) {
                m.last().removeCardIfPresent(carte_);
            }
        }
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

    /**
     Retourne l'ensemble des cartes d'une meme couleur autre que l'atout
     probablement possedees par les autres joueurs on tient compte du pli en
     cours
     @param _numero
     */
    EqList<HandTarot> cartesPossibles(Suit _couleur,
                                      HandTarot _curHand) {
        byte next_ = progressingTrick.getNextPlayer(nbPlayers);
        HandTarot playedCards_ = cartesJoueesEnCours(next_);
        boolean playedCalledCard_ = playedCards_.contientCartes(calledCards);
        EqList<HandTarot> m = new EqList<HandTarot>();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
            HandTarot h_ = new HandTarot();
            m.add(h_);
            if(joueur_ == next_) {
                h_.ajouterCartes(_curHand.couleur(_couleur));
                continue;
            }
            for (CardTarot carte_ : HandTarot.couleurComplete(_couleur)) {
                if (!playedCards_.contient(carte_)
                        && !_curHand.contient(carte_)) {
                    h_.ajouter(carte_);
                }
            }
            if (GameTarotTrickInfo.defausseTarot(joueur_, _couleur, tricks)
                    || GameTarotTrickInfo.coupeTarot(_couleur, joueur_, tricks)) {
                // Les joueurs
                // se defaussant
                // sur atout ou
                // couleur
                // demandee ne
                // peuvent pas
                // avoir de
                // l'atout
                h_.supprimerCartes();
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.SUIT)) {
                h_.supprimerCartes();
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.POINT)
                    || declaresMiseres.get(joueur_).containsObj(Miseres.CHARACTER)) {
                h_.supprimerCartes(h_.charCardsBySuit(_couleur));
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.LOW_CARDS)) {
                h_.supprimerCartes(
                        h_.cartesBasses(_couleur));
            }
        }
        m.add(new HandTarot());
        if (bid.getJeuChien() != PlayingDog.WITH) {
            for (CardTarot carte_ : HandTarot.couleurComplete(_couleur)) {
                if (!playedCards_.contient(carte_)
                        && !_curHand.contient(carte_)) {
                    m.last().ajouter(carte_);
                }
            }
        } else {
            if (next_ == taker) {
                /*
            Le preneur sait ce qu'il a mis au chien
            pour une Petite ou une Garde
            */
                m.last().ajouterCartes(
                        tricks.first().getCartes().couleur(_couleur));
            } else {
                if (tricks.first().getCartes().tailleCouleur(Suit.TRUMP) > 0) {
                    /* Si le preneur est oblige
                    d 'ecarter des atouts
                    alors les cartes autre que
                    le roi de couleur
                    du chien sont
                    certainement ecartees*/
                    for (CardTarot carte_ : lastSeenHand
                            .couleur(_couleur)) {
                        if (carte_.getNomFigure() == CardChar.KING) {
                            continue;
                        }
                        m.last().ajouter(carte_);
                    }
                } else {
                    for (CardTarot carte_ : HandTarot.couleurComplete(_couleur)) {
                        if (carte_.getNomFigure() == CardChar.KING) {
                            continue;
                        }
                        if (!playedCards_.contient(carte_)
                                && !_curHand.contient(carte_)) {
                            m.last().ajouter(carte_);
                        }
                    }
                }
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                if (joueur_ == next_) {
                    continue;
                }
                if (taker != joueur_) {
                    // Les cartes d'une couleur du chien (si il est vu) ne
                    // peuvent possedes que par le preneur ou etre ecartees
                    for (CardTarot carte_ : lastSeenHand) {
                        if (carte_.couleur() == _couleur) {
                            m.get(joueur_).removeCardIfPresent(carte_);
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
                        if (carte_.getNomFigure() == CardChar.KING) {
                            continue;
                        }
                        m.get(taker).removeCardIfPresent(carte_);
                    }
                }
            }
        }
        if (progressingTrick.couleurDemandee() == _couleur) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers; joueur_++) {
                if (joueur_ == next_) {
                    continue;
                }
                if (!progressingTrick.aJoue(joueur_, nbPlayers)) {
                    continue;
                }
                CardTarot carteJouee_ = progressingTrick.carteDuJoueur(
                        joueur_, nbPlayers);
                if (carteJouee_.couleur() != _couleur
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
                    m.get(joueur_).supprimerCartes();
                }
            }
        }
        for (byte i = 0; i < nbPlayers; i++) {
            HandTarot couleurLoc_ = m.get(i);
            if (i == next_) {
                continue;
            }
            if (couleurLoc_.estVide()) {
                continue;
            }
            Suit noCouleur_ = couleurLoc_.premiereCarte()
                    .couleur();
            //filtre sur le jeu d'une carte couleur ordinaire apres un adversaire ramasseur
            HandTarot atoutsFiltres_ = joueCarteBasseTarot(next_,_curHand,
                    i, noCouleur_, couleurLoc_, tricks);
            m.set(i, atoutsFiltres_);
        }
        if (playedCalledCard_) {
            for (byte i = 0; i < nbPlayers; i++) {
                HandTarot couleurLoc_ = m.get(i);
                if (i == next_) {
                    continue;
                }
                if (couleurLoc_.estVide()) {
                    continue;
                }
                Suit noCouleur_ = couleurLoc_.premiereCarte()
                        .couleur();
                HandTarot filteredCharacters_ = playCharacterCardTarot(
                        i, noCouleur_, couleurLoc_, tricks);
                m.set(i, filteredCharacters_);
            }
        }
        return m;
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
    public EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> cartesCertaines(
            EnumMap<Suit, EqList<HandTarot>> _cartesPossibles) {
        Numbers<Byte> joueursRepartitionConnue_ = new Numbers<Byte>();
        Numbers<Byte> joueursRepartitionConnue2_ = new Numbers<Byte>();
        Numbers<Byte> joueursRepartitionConnueMemo_ = new Numbers<Byte>();
        Numbers<Byte> joueursRepartitionInconnue_ = new Numbers<Byte>();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = new EnumMap<Suit,EqList<HandTarot>>();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = new EnumMap<Suit,EqList<HandTarot>>(
                _cartesPossibles);
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> retour_ = new EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>>();
        int nombreDApparitionCarte_;
        /*
        Indique le nombre de mains pour les
        cartes possibles ou apparait la carte
        */
        EnumList<Suit> toutesCouleurs_ = new EnumList<Suit>();
        toutesCouleurs_.add(CardTarot.EXCUSE.couleur());
        toutesCouleurs_.add(Suit.TRUMP);
        toutesCouleurs_.addAllElts(Suit.couleursOrdinaires());
        for(Suit couleur_: toutesCouleurs_) {
            cartesCertaines_.put(couleur_,new EqList<HandTarot>());
        }
        for (Suit couleur_:cartesCertaines_.getKeys()) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ <= nbPlayers; joueur_++) {
                cartesCertaines_.getVal(couleur_).add(new HandTarot());
            }
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ <= nbPlayers; joueur_++) {
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
                for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ <= nbPlayers; joueur2_++) {
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
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ <= nbPlayers; joueur_++) {
                if (!joueursRepartitionConnueMemo_.containsObj(joueur_)) {
                    joueursRepartitionInconnue_.add(joueur_);
                }
            }
            for (byte joueur_ : joueursRepartitionInconnue_) {
                for (Suit couleur_:toutesCouleurs_) {
                    for (CardTarot carte_ : cartesPossibles_.getVal(couleur_).get(
                            joueur_)) {
                        nombreDApparitionCarte_ = 0;
                        for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ <= nbPlayers; joueur2_++) {
                            if (cartesPossibles_.getVal(couleur_).get(joueur2_)
                                    .contient(carte_)) {
                                nombreDApparitionCarte_++;
                            }
                        }
                        if (nombreDApparitionCarte_ == 1
                                && !cartesCertaines_.getVal(couleur_).get(joueur_)
                                .contient(carte_)) {
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
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ <= nbPlayers; joueur_++) {
            if (!joueursRepartitionConnueMemo_.containsObj(joueur_)) {
                joueursRepartitionInconnue_.add(joueur_);
            }
        }
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

    void addToKnown(EnumList<Suit> _all,EnumMap<Suit,EqList<HandTarot>> _poss,byte _player,
                    EnumMap<Suit,EqList<HandTarot>> _sure,
                    Numbers<Byte> _joueursRepartitionConnue, Numbers<Byte> _joueursRepartitionConnueMemo) {
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
    static void affect(EnumList<Suit> _all,EnumMap<Suit,EqList<HandTarot>> _from, byte _player,EnumMap<Suit,EqList<HandTarot>> _to) {
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
        NumberMap<Byte,Boolean> defausses_ = new NumberMap<Byte,Boolean>();
        for (byte j = CustList.FIRST_INDEX;j<nbPlayers;j++) {
            defausses_.put(j, GameTarotTrickInfo.defausseTarot(j, _unionPlis));
        }
        for (TrickTarot pli_ : _unionPlis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            if (pli_.getEntameur() == _numero) {
                continue;
            }
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.couleur() != _couleur) {
                continue;
            }
            if (!carteObservee_.isCharacter()) {
                continue;
            }
            if (confidence.get(_numero).get(pli_.getRamasseur()) == Confidence.YES) {
                continue;
            }
            //winner of the trick foe for the viewed player
            if (!pli_.joueursAyantJoueAvant(_numero, rules.getDealing()).containsObj(pli_.getRamasseur())) {
                continue;
            }
            boolean defausseToutJoueurApres_ = true;
            for(byte j: pli_.joueursAyantJoueApres(_numero, rules.getDealing())) {
                if(defausses_.getVal(j)) {
                    continue;
                }
                defausseToutJoueurApres_ = false;
                break;
            }
            if(!defausseToutJoueurApres_) {
                continue;
            }
            // Plis (sur)coupes (couleur demandee) sans joueur pouvant sur/sous/couper
            // Plis fournis (demande atout) sans joueur pouvant fournir un atout
            playedCards_.ajouter(carteObservee_);
        }
        if (!playedCards_.estVide()) {
            CardTarot maxCarte_ = playedCards_.premiereCarte();
            HandTarot cartesImpossibles_ = new HandTarot();
            for (CardTarot atout_ : HandTarot.couleurComplete(_couleur)) {
                if (atout_.strength(_couleur) >= maxCarte_.strength(_couleur)) {
                    continue;
                }
                cartesImpossibles_.ajouter(atout_);
            }
            retour_.supprimerCartes(cartesImpossibles_);
        }
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
            if (carteObservee_.couleur() != _couleur) {
                continue;
            }
            if (carteObservee_.isCharacter()) {
                continue;
            }
            playedCards_.ajouter(carteObservee_);
            // Plis sous coupes (couleur demandee) ou avec un atout joue en
            // dessous du ramasseur (demande atout)
        }
        HandTarot cartesVues_ = new HandTarot();
        cartesVues_.ajouterCartes(cartesJouees_.couleur(_couleur).cartesBasses(
                _couleur));
        cartesVues_.ajouterCartes(_curHand.couleur(_couleur).cartesBasses(
                _couleur));
        cartesVues_.trierParForceEnCours(_couleur);
        playedCards_.trierParForceEnCours(_couleur);
        if (!playedCards_.estVide()) {
            HandTarot mainLocale_ = new HandTarot();
            CardTarot carteObservee_ = playedCards_.derniereCarte();
            for (CardTarot carte_ : cartesVues_) {
                if (carte_.strength(_couleur) < carteObservee_
                        .strength(_couleur)) {
                    mainLocale_.ajouter(carte_);
                }
            }
            if (!mainLocale_.estVide()) {
                CardTarot maxCarte_ = mainLocale_.premiereCarte();
                HandTarot cartesImpossibles_ = new HandTarot();
                for (CardTarot atout_ : HandTarot.couleurComplete(_couleur)
                        .cartesBasses(_couleur)) {
                    if (atout_.strength(_couleur) >= maxCarte_.strength(_couleur)) {
                        continue;
                    }
                    cartesImpossibles_.ajouter(atout_);
                }
                retour_.supprimerCartes(cartesImpossibles_);
            }
        }
        return retour_;
    }

    HandTarot sousCoupeTarot(byte _joueurCourant, HandTarot _curHand, byte _numero,
                             HandTarot _atoutsPossibles) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_atoutsPossibles);
        HandTarot cartesJouees_ = cartesJoueesEnCours(_joueurCourant);
        HandTarot playedCards_ = new HandTarot();
        for (TrickTarot pli_ : tricks) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.couleur() != Suit.TRUMP) {
                continue;
            }
            boolean sousCoupe_ = false;
            Suit couleurDemandee_ = pli_.couleurDemandee();
            byte force_ = carteObservee_.strength(couleurDemandee_);
            for(byte j: pli_.joueursAyantJoueAvant(_numero, rules.getDealing())) {
                if(pli_.carteDuJoueur(j).strength(couleurDemandee_) < force_) {
                    continue;
                }
                sousCoupe_ = true;
                break;
            }
            if (!sousCoupe_) {
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
            HandTarot mainLocale_ = new HandTarot();
            CardTarot carteObservee_ = playedCards_.derniereCarte();
            for (CardTarot carte_ : cartesVues_) {
                if (carte_.strength(Suit.TRUMP) < carteObservee_
                        .strength(Suit.TRUMP)) {
                    mainLocale_.ajouter(carte_);
                }
            }
            if (!mainLocale_.estVide()) {
                CardTarot maxCarte_ = mainLocale_.premiereCarte();
                HandTarot atoutsImpossibles_ = new HandTarot();
                for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
                    if (CardTarot.eq(atout_, CardTarot.petit())) {
                        continue;
                    }
                    if (atout_.strength(Suit.TRUMP) >= maxCarte_
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

    HandTarot coupeTarot(byte _joueurCourant, HandTarot _curHand, byte _numero,
                         HandTarot _atoutsPossibles) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_atoutsPossibles);
        HandTarot cartesJouees_ = cartesJoueesEnCours(_joueurCourant);
        CustList<TrickTarot> plis_ = new CustList<TrickTarot>();
        NumberMap<Byte,HandTarot> atoutsJouesPlis_ = new NumberMap<Byte,HandTarot>();
        NumberMap<Byte,Boolean> defausses_ = new NumberMap<Byte,Boolean>();
        for (byte j = CustList.FIRST_INDEX;j<nbPlayers;j++) {
            defausses_.put(j, GameTarotTrickInfo.defausseTarot(j, tricks));
        }
        byte key_ = 0;
        for (TrickTarot pli_ : tricks) {
            if (!pli_.getVuParToutJoueur()) {
                key_++;
                continue;
            }
            if (pli_.getEntameur() == _numero) {
                key_++;
                continue;
            }
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.couleur() != Suit.TRUMP) {
                key_++;
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            boolean coupe_ = true;
            byte force_ = carteObservee_.strength(couleurDemandee_);
            HandTarot atoutsJouesAvant_ = new HandTarot();
            for(byte j: pli_.joueursAyantJoueAvant(_numero, rules.getDealing())) {
                CardTarot carteJouee_ = pli_.carteDuJoueur(j);
                if(carteJouee_.strength(couleurDemandee_) < force_) {
                    if(carteJouee_.couleur() == Suit.TRUMP) {
                        atoutsJouesAvant_.ajouter(carteJouee_);
                    }
                    continue;
                }
                coupe_ = false;
                break;
            }
            if(!coupe_) {
                key_++;
                continue;
            }
            boolean defausseToutJoueurApres_ = true;
            for(byte j: pli_.joueursAyantJoueApres(_numero, rules.getDealing())) {
                if(defausses_.getVal(j)) {
                    continue;
                }
                defausseToutJoueurApres_ = false;
                break;
            }
            if(!defausseToutJoueurApres_) {
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
            TrickTarot pli_ = plis_.get(key_);
            HandTarot atoutsJouesPli_ = e.getValue();
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            HandTarot mainLocale_ = new HandTarot();
            for (CardTarot carte_ : cartesVues_) {
                if (carte_.strength(Suit.TRUMP) < carteObservee_
                        .strength(Suit.TRUMP)) {
                    mainLocale_.ajouter(carte_);
                }
            }
            //mainLocale: cartesVues inferieures a la carte observee
            if (mainLocale_.estVide()) {
                //le joueur courant ne possede pas de carte en dessous de la carte observee
                //aucune carte en dessous de la carte observee n'a ete jouee
                //tout reste possible
                key_++;
                continue;
            }
            CardTarot maxCarte_ = mainLocale_.premiereCarte();
            HandTarot atoutsImpossibles_ = new HandTarot();
            if(atoutsJouesPli_.estVide()) {
                for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
                    if (atout_.strength(Suit.TRUMP) >= maxCarte_
                            .strength(Suit.TRUMP)) {
                        continue;
                    }
                    atoutsImpossibles_.ajouter(atout_);
                }
            } else {
                atoutsJouesPli_.trierParForceEnCours(pli_.couleurDemandee());
                CardTarot maxAtoutJouePli_ = atoutsJouesPli_.premiereCarte();
                for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
                    if (atout_.strength(Suit.TRUMP) >= maxCarte_
                            .strength(Suit.TRUMP)) {
                        continue;
                    }
                    if (atout_.strength(Suit.TRUMP) <= maxAtoutJouePli_
                            .strength(Suit.TRUMP)) {
                        continue;
                    }
                    atoutsImpossibles_.ajouter(atout_);
                }
            }
            retour_.supprimerCartes(atoutsImpossibles_);
            key_++;
        }
        return retour_;
    }
    boolean petitJoueDemandeAtoutRamasseurAdv(byte _numero) {
        boolean playedSmall_ = false;
        for (TrickTarot pli_ : tricks) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            if(pli_.couleurDemandee() != Suit.TRUMP) {
                continue;
            }
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            if(carteObservee_ != CardTarot.petit()) {
                continue;
            }
            //jeu du Petit sur demande d'atout
            if(confidence.get(_numero).get(pli_.getRamasseur()) == Confidence.NO) {
                playedSmall_ = true;
            }
            break;
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
            for (TrickTarot t: tricks) {
                if (!t.getVuParToutJoueur()) {
                    continue;
                }
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
        boolean coupe_ = false;
        // coupe retourne vrai si on sait que le joueur ne
        // peut que jouer de l'atout sur des couleurs
        if (coupeTarot(Suit.TRUMP, _numero, _unionPlis)) {
            coupe_ = true;
        }
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
        for (int b = lastIndex_; b >= CustList.FIRST_INDEX; b--) {
            TrickTarot pli_ = _unionPlis.get(b);
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (_couleurDonnee != couleurDemandee_) {
                continue;
            }
            Suit couleurCarte_ = pli_.carteDuJoueur(_numero).couleur();
            if (couleurCarte_ == couleurDemandee_) {
                continue;
            }
            if (couleurCarte_ == Suit.TRUMP) {
                continue;
            }
            return true;
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
            for (int b = lastIndex_; b >= CustList.FIRST_INDEX; b--) {
                TrickTarot pli_ = _unionPlis.get(b);
                if (!pli_.getVuParToutJoueur()) {
                    continue;
                }
                if (pli_.couleurDemandee() != _couleur) {
                    continue;
                }
                // On ne cherche que les plis dont la couleur demande
                // est couleur
                if (pli_.carteDuJoueur(_numero)
                        .couleur() != Suit.TRUMP) {
                    continue;
                }
                return true;
            }
            return false;
        }
        // Le joueur ne coupe pas la couleur passee en parametre a ce niveau si
        // couleur > 1
        // couleur == Couleu.Atout
        int lastIndex_ = _unionPlis.size() - 1;
        for (int b = lastIndex_; b >= CustList.FIRST_INDEX; b--) {
            TrickTarot pli_ = _unionPlis.get(b);
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }

            Suit couleurDemandee_ = pli_.couleurDemandee();
            Suit couleurJoueur_ = pli_.carteDuJoueur(_numero).couleur();
            if (!Suit.couleursOrdinaires().containsObj(couleurJoueur_)) {
                continue;
            }
            if (couleurDemandee_ == _couleur) {
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
                return true;
            }
            //couleurDemandee est une couleur ordinaire
            //couleurJoueur est une couleur ordinaire
            //donc le joueur se defausse
            if (couleurJoueur_ != couleurDemandee_) {
                return true;
            }
        }

        return false;
    }
    static boolean plisTousFaitsPar(Numbers<Byte> _joueurs,
                                    CustList<TrickTarot> _unionPlis, byte _nombreJoueurs) {
        Numbers<Byte> autresJoueurs_ = GameTarotTeamsRelation.autresJoueurs(_joueurs, _nombreJoueurs);
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
