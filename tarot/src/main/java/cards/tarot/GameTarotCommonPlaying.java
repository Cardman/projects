package cards.tarot;

import cards.consts.Hypothesis;
import cards.consts.Suit;
import cards.tarot.comparators.GameTarotLeastDemandedSuitComparator;
import cards.tarot.comparators.GameTarotMostDemandedSuitComparator;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.PlayingDog;
import code.maths.Rate;
import code.util.*;

public final class GameTarotCommonPlaying {
    private GameTarotTrickInfo doneTrickInfo;
    private GameTarotTeamsRelation teamsRelation;
    private HandTarot lastHand;

    public GameTarotCommonPlaying(GameTarotTrickInfo _doneTrickInfo, GameTarotTeamsRelation _teamsRelation) {
        doneTrickInfo = _doneTrickInfo;
        teamsRelation = _teamsRelation;
    }

    HandTarot cartesJouables(HandTarot _calledCards, EnumMap<Suit, HandTarot> _repartitionMain) {
        HandTarot atoutsJoues_ = doneTrickInfo.getProgressingTrick().getCartes().couleurs().getVal(Suit.TRUMP);
        Suit couleurDemandee_ = doneTrickInfo.getProgressingTrick().couleurDemandee();
        HandTarot cartesJouables_ = new HandTarot();
        int nombreCartes_ = 0;
        /* Dans la main */
        for (HandTarot main_ : _repartitionMain.values()) {
            nombreCartes_ += main_.total();
        }
        if (doneTrickInfo.getProgressingTrick().couleurDemandee() == Suit.UNDEFINED) {
            if (premierTour() && _calledCards.total() == 1) {
                Suit couleurAppele_ = _calledCards.premiereCarte().couleur();
                if (nombreCartes_ > _repartitionMain
                        .getVal(couleurAppele_).total()) {
                    cartesJouables_.ajouterCartes(_repartitionMain
                            .getVal(CardTarot.excuse().couleur()));
                    cartesJouables_.ajouterCartes(_repartitionMain
                            .getVal(Suit.TRUMP));

                    for (Suit couleur_ : Suit.couleursOrdinaires()) {
                        if(couleur_ != couleurAppele_) {
                            cartesJouables_.ajouterCartes(_repartitionMain
                                    .getVal(couleur_));
                            continue;
                        }
                        for(CardTarot carte_:_repartitionMain
                                .getVal(couleur_)) {
                            if(!CardTarot.eq(carte_, _calledCards.premiereCarte())) {
                                continue;
                            }
                            cartesJouables_.ajouter(carte_);
                        }
                    }
                    return cartesJouables_;
                }
            }
            cartesJouables_.ajouterCartes(HandTarot.reunion(_repartitionMain));
            return cartesJouables_;
        }
        cartesJouables_.ajouterCartes(_repartitionMain.getVal(CardTarot.EXCUSE.couleur()));
        if (Suit.couleursOrdinaires().containsObj(couleurDemandee_)
                && !_repartitionMain.getVal(couleurDemandee_).estVide()) {
            cartesJouables_
                    .ajouterCartes(_repartitionMain.getVal(couleurDemandee_));
            return cartesJouables_;
        }
        if (_repartitionMain.getVal(Suit.TRUMP).estVide()) {
            for (Suit couleur_ : Suit.couleursOrdinaires()) {
                cartesJouables_.ajouterCartes(_repartitionMain.getVal(couleur_));
            }
            return cartesJouables_;
        }
        if (atoutsJoues_.estVide()) {
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(Suit.TRUMP));
            return cartesJouables_;
        }
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        byte ramasseurVirtuel_ = doneTrickInfo.getProgressingTrick().getRamasseur(nombreDeJoueurs_);
        CardTarot carteForte_ = doneTrickInfo.getProgressingTrick().carteDuJoueur(
                ramasseurVirtuel_, nombreDeJoueurs_);
        byte valeurForte_ = carteForte_.strength(couleurDemandee_);
        if (_repartitionMain.getVal(Suit.TRUMP).premiereCarte().strength(couleurDemandee_) < valeurForte_) {
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(Suit.TRUMP));
            return cartesJouables_;
        }
        if (valeurForte_ < _repartitionMain.getVal(Suit.TRUMP).derniereCarte().strength(couleurDemandee_)) {
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(Suit.TRUMP));
            return cartesJouables_;
        }
        byte indiceCarte_ = CustList.FIRST_INDEX;
        HandTarot trumps_ = _repartitionMain.getVal(Suit.TRUMP);
        while (trumps_.carte(indiceCarte_)
                .strength(couleurDemandee_) > valeurForte_) {
            cartesJouables_.ajouter(trumps_.carte(indiceCarte_));
            indiceCarte_++;
        }
        return cartesJouables_;
    }
    boolean premierTour() {
        int plisTotal_ = 0;
        if(teamsRelation.existePreneur()) {
            for(TrickTarot p:doneTrickInfo.getTricks()) {
                if(!p.getVuParToutJoueur()) {
                    continue;
                }
                plisTotal_++;
            }
            return plisTotal_ == 0;
        }
        plisTotal_ = doneTrickInfo.getTricks().size();
        return plisTotal_ <= 1;
    }
    TarotInfoPliEnCours initInformations(
            HandTarot _lastHand,
            HandTarot _cartes,
            HandTarot _cartesJouables) {
        lastHand = _lastHand;
        byte nextPlayer_ = doneTrickInfo.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        EnumMap<Suit,HandTarot> repartition_ = _cartes.couleurs();
        Numbers<Byte> joueursNonJoue_ = joueursNAyantPasJoue(nextPlayer_);
        CustList<TrickTarot> plisFaits_ = unionPlis(false);
        HandTarot cartesJouees_ = doneTrickInfo.cartesJoueesEnCours(teamsRelation,nextPlayer_);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = cartesJouees_.couleurs();
        boolean carteAppeleeJouee_ = cartesJouees_.contientCartes(doneTrickInfo.getCalledCards());
        boolean contientExcuse_ = _cartes.contient(CardTarot.excuse());
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        Numbers<Byte> joueursJoue_ = GameTarotTeamsRelation.autresJoueurs(joueursNonJoue_, nombreDeJoueurs_);
        joueursJoue_.removeObj(nextPlayer_);
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = doneTrickInfo.cartesPossibles(
                teamsRelation,
                _cartes,
                _lastHand);
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> hypotheses_ = doneTrickInfo.cartesCertaines(teamsRelation,cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        byte ramasseurVirtuel_ = doneTrickInfo.getProgressingTrick().getRamasseur(nombreDeJoueurs_);
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = _cartes.eclaterToutEnCours(repartitionCartesJouees_);

        boolean maitreAtout_ = GameTarotCommonPlaying.strictMaitreAtout(
                cartesPossibles_,
                nextPlayer_,
                suitesTouteCouleur_.getVal(Suit.TRUMP), cartesJouees_);
        EnumList<Suit> couleursMaitresses_ = couleursMaitres(
                suitesTouteCouleur_, cartesJouees_,
                cartesPossibles_, nextPlayer_);
        EnumMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                repartition_, repartitionCartesJouees_);
        boolean maitreJeu_ = maitreAtout_ && couleursMaitresses_.size() == Suit.couleursOrdinaires().size();
        EnumList<Suit> coupesFranches_ = GameTarotCommonPlaying.coupesFranchesStrictes(plisFaits_,
                repartition_, nextPlayer_);

        TarotInfoPliEnCours info_ = new TarotInfoPliEnCours();
        info_.setCurrentPlayer(nextPlayer_);
        info_.setJoueursNonJoue(joueursNonJoue_);
        info_.setCartesJouables(_cartesJouables);
        info_.setJoueursJoue(joueursJoue_);
        info_.setPlisFaits(plisFaits_);
        info_.setCartesJouees(cartesJouees_);
        info_.setRepartitionCartesJouees(repartitionCartesJouees_);
        info_.setCarteAppeleeJouee(carteAppeleeJouee_);
        info_.setContientExcuse(contientExcuse_);
        info_.setCartesPossibles(cartesPossibles_);
        info_.setCartesCertaines(cartesCertaines_);
        info_.setRamasseurVirtuel(ramasseurVirtuel_);
        info_.setSuitesTouteCouleur(suitesTouteCouleur_);
        info_.setMaitreAtout(maitreAtout_);
        info_.setCouleursMaitresses(couleursMaitresses_);
        info_.setCartesMaitresses(cartesMaitresses_);
        info_.setMaitreJeu(maitreJeu_);
        info_.setCoupesFranches(coupesFranches_);
        info_.setCalledSuits(couleursAppelees());
        info_.setProgressingTrick(doneTrickInfo.getProgressingTrick());
        info_.setTeamsRelation(teamsRelation);
        //depend de partie et de cartesJouables
        //carteEntamee
        //joueursNonJoue
        //joueursConfianceNonJoue
        //joueursNonConfianceNonJoue
        //CustList<PliTarot> plisFaits
        //CustList<Byte> joueursJoue
        return info_;
    }
    public CustList<TrickTarot> unionPlis(boolean _addAllTricks) {
        CustList<TrickTarot> unionPlis_ = new CustList<TrickTarot>();
        if(teamsRelation.existePreneur()) {
            unionPlis_.addAllElts(doneTrickInfo.getTricks());
            return unionPlis_;
        }
        if (_addAllTricks) {
            unionPlis_.addAllElts(doneTrickInfo.getTricks());
        } else {
            for (TrickTarot t: doneTrickInfo.getTricks()) {
                if (!t.getVuParToutJoueur()) {
                    continue;
                }
                unionPlis_.add(t);
            }
        }
        return unionPlis_;
    }
    EnumList<Suit> couleursAppelees() {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for(CardTarot c: doneTrickInfo.getCalledCards()) {
            couleurs_.add(c.couleur());
        }
        return couleurs_;
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
    HandTarot cartesVuesAuChien() {
        HandTarot cartes_ = new HandTarot();
        if (doneTrickInfo.getBid().getJeuChien() == PlayingDog.WITH) {
            cartes_.ajouterCartes(lastHand);
        }
        return cartes_;
    }
    boolean appeleConnuDefenseur(byte _joueur, EnumMap<Suit, EqList<HandTarot>> _cartesPossibles) {
        byte nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        if (doneTrickInfo.getBid().getJeuChien() == PlayingDog.WITH) {
            return teamsRelation.joueursConfiance(_joueur, GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_)).size() + 2 + teamsRelation.getCalledPlayers().size() == nombreDeJoueurs_;
        }
        if (existeCarteAppelee()) {
            boolean aucuneCouleurAppelePreneur_ = true;
            for(Suit c: couleursAppelees()) {
                if (_cartesPossibles.getVal(c).get(teamsRelation.getTaker()).estVide()) {
                    continue;
                }
                aucuneCouleurAppelePreneur_ = false;
            }
            if (aucuneCouleurAppelePreneur_) {
                return teamsRelation.joueursConfiance(_joueur, GameTarotTeamsRelation.tousJoueurs(nombreDeJoueurs_)).size() + 2 + teamsRelation.getCalledPlayers().size() == nombreDeJoueurs_;
            }
            return false;
        }
        return true;
    }
    private boolean existeCarteAppelee() {
        return !doneTrickInfo.getCalledCards().estVide();
    }
    static EnumList<Suit> couleursNonOuvertesNonVides(HandTarot _main,
                                                      CustList<TrickTarot> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(_main, _couleurs);
        return couleursNonEntamees(_plis,couleurs_);
    }
    static EnumList<Suit> couleursNonEntamees(
            CustList<TrickTarot> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        EnumList<Suit> toutesCouleursOrdinaires_ = Suit.couleursOrdinaires();
        for (TrickTarot pli_ : _plis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
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
    static EnumList<Suit> strictCouleursMaitres(
            EnumMap<Suit,EqList<HandTarot>> _suites, EnumMap<Suit,HandTarot> _cartesJouees,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _numero) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot couleurComplete_ = HandTarot.couleurComplete(couleur_);
            if (_cartesJouees.getVal(couleur_).total() == couleurComplete_.total()) {
                couleurs_.add(couleur_);
                continue;
            }
            if (_suites.getVal(couleur_).isEmpty()) {
                continue;
            }
            int max_ = CustList.SIZE_EMPTY;
            /*
            max designe le nombre maximal de cartes probablement
            possedees par un joueur a une couleur donnee
            */
            EqList<HandTarot> possibleSuits_ = _cartesPossibles.getVal(couleur_);
            int nbSuits_ = possibleSuits_.size() - 1;
            EqList<HandTarot> possibleExcuse_ = _cartesPossibles.getVal(CardTarot.EXCUSE.couleur());
            for (int joueur_ = CustList.FIRST_INDEX; joueur_ < nbSuits_; joueur_++) {
                if (joueur_ != _numero) {
                    int sum_ = possibleSuits_.get(joueur_).total() + possibleExcuse_.get(joueur_).total();
                    if (sum_ > max_) {
                        max_ = sum_;
                    }
                }
            }
            boolean existeAtoutMaitre_ = true;
            CardTarot c = _suites.getVal(couleur_).first().premiereCarte();
            for (CardTarot carte_ : HandTarot.cartesCouleursAuDessusDe(c)) {
                if (!_cartesJouees.getVal(couleur_).contient(carte_)
                        && !_suites.getVal(couleur_).first().contient(carte_)) {
                    existeAtoutMaitre_ = false;
                    break;
                }
            }
            int maitres_ = CustList.SIZE_EMPTY;
            if (existeAtoutMaitre_) {
                maitres_ = _suites.getVal(couleur_).first().total();
            }
            if (maitres_ >= max_ || maitres_ > CustList.SIZE_EMPTY
                    && _suites.getVal(couleur_).size() == CustList.ONE_ELEMENT) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }
    static EnumList<Suit> couleursMaitres(
            EnumMap<Suit,EqList<HandTarot>> _suites, HandTarot _cartesJouees,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _numero) {
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        EnumList<Suit> couleurs_ = strictCouleursMaitres(_suites,cartesJouees_,_cartesPossibles,_numero);
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if (_suites.getVal(couleur_).isEmpty()) {
                couleurs_.add(couleur_);
            }
        }
        couleurs_.removeDuplicates();
        return couleurs_;
    }
    static EnumList<Suit> couleursAvecCarteMaitresse(HandTarot _main,
                                                     HandTarot _cartesJouees, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        for (Suit couleur_ : _couleurs) {
            HandTarot cartesMaitresses_ = GameTarotCommon.cartesMaitresses(couleursMains_,
                    cartesJouees_).getVal(couleur_);
            if (!cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }
    byte playerAfter(byte _player) {
        return teamsRelation.getRules().getRepartition().getNextPlayer(_player);
    }
    /**
     Retourne vrai si et seulement si le joueur peut ramasser tous les atouts
     sans en perdre
     @param _cartesPossibles
     les cartes probablement possedees par les autres joueurs
     @param _numero
     le numero du joueur concerne
     @param _suites
     l'ensemble des suites d'atout du joueur concerne
     @param _cartesJouees
     l'ensemble de toutes les cartes jouees reparties dans toutes
     les couleurs
     */
    static boolean strictMaitreAtout(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            byte _numero,
            EqList<HandTarot> _suites, HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Suit couleurAtout_ = Suit.TRUMP;
        int nbTotalAtouts_ = HandTarot.atoutsSansExcuse().total();
        int max_ = CustList.SIZE_EMPTY;
        /*
        max designe le nombre maximal de cartes probablement
        possedees par un joueur
        */
        EqList<HandTarot> possibleTrumps_ = _cartesPossibles.getVal(couleurAtout_);
        EqList<HandTarot> possibleExc_ = _cartesPossibles.getVal(Suit.UNDEFINED);
        int nbPlayers_ = possibleTrumps_.size() - 1;
        for (int joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers_; joueur_++) {
            // La taille de atoutsPossibles
            // correspond au nombre de joueurs+1
            if (joueur_ == _numero) {
                continue;
            }
            int sum_ = possibleTrumps_.get(joueur_).total()
                    + possibleExc_.get(joueur_).total();
            if (sum_ <= max_) {
                continue;
            }
            max_ = sum_;
        }
        /*
        Fin boucle sur le calcul de la valeur maximale possible des atouts
        */
        if (max_ == CustList.SIZE_EMPTY) {
            /*
        S'il est impossible que les autres joueurs aient de
        l'atout (Excuse incluse)
        */
            return true;
        }
        if (cartesJouees_.getVal(couleurAtout_).total() == nbTotalAtouts_) {
            return true;
        }
        if (_suites.isEmpty()) {
            return false;
        }
        boolean existeAtoutMaitre_ = true;
        CardTarot atoutPetitSuiteHaute_ = _suites.first()
                .premiereCarte();
        for (CardTarot carte_ : HandTarot.atoutsAuDessusDe(atoutPetitSuiteHaute_)) {
            if (cartesJouees_.getVal(couleurAtout_).contient(carte_)) {
                continue;
            }
            if (_suites.first().contient(carte_)) {
                continue;
            }
            existeAtoutMaitre_ = false;
            break;
        }
        return existeAtoutMaitre_ && _suites.first().total() >= max_;
    }

    static boolean maitreAtout(TarotInfoPliEnCours _info) {
        boolean contientExcuse_ = _info.isContientExcuse();
        HandTarot cartesJouees_ = _info.getCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suites_ = _info.getSuitesTouteCouleur();
        return  GameTarotCommonPlaying.maitreAtout(suites_.getVal(Suit.TRUMP),
                cartesJouees_, contientExcuse_);
    }
    /**
     Est vrai si et seulement si le nombre
     2 x nb_atout_maitres_joueur+3 x nb_atout_non_maitres_joueur/2+atouts_jouees
     depasse strictement 21
     */
    static boolean maitreAtout(EqList<HandTarot> _suites,
                               HandTarot _cartesJouees, boolean _excuse) {
        int maitres_;
        int nonmaitres_;
        int nb_;
        CardTarot c;
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Suit couleurAtout_ = Suit.TRUMP;
        int nbTotalAtouts_ = HandTarot.atoutsSansExcuse().total();
        if (cartesJouees_.getVal(couleurAtout_).total() == nbTotalAtouts_) {
            return true;
        }
        if (_suites.isEmpty()) {
            return false;
        }
        boolean existeAtoutMaitre_ = true;
        c = _suites.first().premiereCarte();
        for (CardTarot carte_ : HandTarot.atoutsAuDessusDe(c)) {
            if (cartesJouees_.getVal(couleurAtout_).contient(carte_)) {
                continue;
            }
            if (_suites.first().contient(carte_)) {
                continue;
            }
            existeAtoutMaitre_ = false;
            break;
        }
        if (existeAtoutMaitre_) {
            maitres_ = _suites.first().total();
            nonmaitres_ = 0;
        } else {
            maitres_ = 0;
            nonmaitres_ = _suites.first().total();
        }
        int nbSeqs_ = _suites.size();
        for (int suite_ = CustList.SECOND_INDEX; suite_ < nbSeqs_; suite_++) {
            nonmaitres_ += _suites.get(suite_).total();
        }
        if (_suites.last().contient(CardTarot.petit())) {
            nonmaitres_--;
        }
        nb_ = 2 * maitres_ + 3 * nonmaitres_ / 2
                + cartesJouees_.getVal(couleurAtout_).total();
        if (_cartesJouees.contient(CardTarot.EXCUSE)) {
            nb_++;
        }
        if (_suites.last().contient(CardTarot.petit())) {
            nb_++;
        }
        if (_excuse) {
            nb_++;
        }
        return nb_ > nbTotalAtouts_;
    }
    static EnumList<Suit> coupesFranchesStrictes(
            CustList<TrickTarot> _plisFaits, EnumMap<Suit,HandTarot> _repartitionCouleur, byte _numero) {
        EnumList<Suit> coupesFranchesStrictes_ = new EnumList<Suit>();
        for (Suit c: Suit.couleursOrdinaires()) {
            if (!_repartitionCouleur.getVal(c).estVide()) {
                continue;
            }
            boolean coupeToujours_ = true;
            for (TrickTarot pli_ : _plisFaits) {
                if (!pli_.getVuParToutJoueur()) {
                    continue;
                }
                if (pli_.couleurDemandee() != c) {
                    continue;
                }
                if (pli_.carteDuJoueur(_numero).couleur() == Suit.TRUMP) {
                    continue;
                }
                coupeToujours_ = false;
            }
            if (!coupeToujours_) {
                continue;
            }
            coupesFranchesStrictes_.add(c);
        }
        return coupesFranchesStrictes_;
    }
    static HandTarot cartesNonMaitresses(EnumMap<Suit,HandTarot> _couleurs,
                                         EnumMap<Suit,HandTarot> _cartesMaitresses,
                                         EnumMap<Suit,EqList<HandTarot>> _suites) {
        HandTarot cards_ = new HandTarot();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot cartesCouleurMaitresse_ = _cartesMaitresses
                    .getVal(couleur_);
            if (!_couleurs.getVal(couleur_).estVide()) {
                EqList<HandTarot> seqs_ = _suites.getVal(couleur_);
                if (cartesCouleurMaitresse_.estVide()) {
                    cards_.ajouterCartes(seqs_.first());
                }
                int nbSeqs_ = seqs_.size();
                for (byte indiceSuite_ = CustList.SECOND_INDEX; indiceSuite_ < nbSeqs_; indiceSuite_++) {
                    cards_.ajouterCartes(seqs_.get(indiceSuite_));
                }
            }
        }
        return cards_;
    }
    static Rate moyenneAtout(HandTarot _main, HandTarot _atoutsJoues,
                             EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _nombreJoueurs) {
        EqList<HandTarot> repartitionAtout_ = _cartesPossibles
                .getVal(Suit.TRUMP);
        byte nombreDefausses_ = 0;
        for (int i = 0; i < _nombreJoueurs; i++) {
            HandTarot main_ = repartitionAtout_.get(i);
            if (!main_.estVide()) {
                continue;
            }
            nombreDefausses_++;
        }
        HandTarot atoutsNonJoues_ = new HandTarot();
        byte nombreJoueursAvecAtout_ = (byte) (_nombreJoueurs
                - nombreDefausses_ - 1);
        if (nombreJoueursAvecAtout_ == 0) {
            return Rate.zero();
        }
        for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
            if (_main.contient(atout_)) {
                continue;
            }
            if (!_atoutsJoues.contient(atout_)) {
                atoutsNonJoues_.ajouter(atout_);
            }
        }
        return new Rate(atoutsNonJoues_.total(), nombreJoueursAvecAtout_);
    }
    public static HandTarot atoutsPoignee(EnumMap<Suit,HandTarot> _repartition) {
        HandTarot m = new HandTarot();
        m.ajouterCartes(_repartition.getVal(Suit.TRUMP));
        if (!_repartition.getVal(CardTarot.excuse().couleur()).estVide()) {
            m.ajouter(CardTarot.excuse(), 0);
        }
        return m;
    }
    static Numbers<Byte> tours(Suit _couleur, CustList<TrickTarot> _plisFaits) {
        Numbers<Byte> tricksNumbers_ = new Numbers<Byte>();
        byte key_ = 0;
        for (TrickTarot pli_ : _plisFaits) {
            if (!pli_.getVuParToutJoueur()) {
                key_++;
                continue;
            }
            if (pli_.couleurDemandee() != _couleur) {
                key_++;
                continue;
            }
            tricksNumbers_.add(key_);
            key_++;
        }
        return tricksNumbers_;
    }
    public static byte ramasseur(CustList<TrickTarot> _plisFaits, byte _numeroPli) {
        return _plisFaits.get(_numeroPli).getRamasseur();
    }
    static Numbers<Byte> ramasseurs(CustList<TrickTarot> _plisFaits) {
        Numbers<Byte> ramasseurs_ = new Numbers<Byte>();
        for(TrickTarot pli_: _plisFaits) {
            if(!pli_.getVuParToutJoueur()) {
                continue;
            }
            byte ramasseur_ = pli_.getRamasseur();
            if(ramasseurs_.containsObj(ramasseur_)) {
                continue;
            }
            ramasseurs_.add(ramasseur_);
        }
        return ramasseurs_;
    }
    static EnumList<Suit> couleursLesPlusEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return couleursTrieesPlusEntameesParJoueurs(_plisFaits, _joueurs, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesPlusEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameTarotMostDemandedSuitComparator(_plisFaits, _joueurs));
    }

    static EnumList<Suit> couleursLesMoinsEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return couleursTrieesMoinsEntameesParJoueurs(_plisFaits, _joueurs, _couleurs).first();
    }

    static CustList<EnumList<Suit>> couleursTrieesMoinsEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameTarotLeastDemandedSuitComparator(_plisFaits, _joueurs));
    }
    static EqList<HandTarot> cartesRelativementMaitreEncours(
            EqList<HandTarot> _suites,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Numbers<Byte> _joueursNAyantPasJoue, Suit _couleurJoueur,
            Suit _couleurDemandee, EnumMap<Suit,EqList<HandTarot>> _cartesCertaines,
            CardTarot _carteForte) {
        byte maxForce_ = 0;
        EqList<HandTarot> suitesBis_ = new EqList<HandTarot>();
        if (!Suit.couleursOrdinaires().containsObj(_couleurJoueur) && Suit.couleursOrdinaires().containsObj(_couleurDemandee) ) {
            /* Pour une coupe */
            for (byte joueur_ : _joueursNAyantPasJoue) {
                if(_cartesPossibles.getVal(_couleurJoueur).get(joueur_).estVide()) {
                    //joueur n'a pas d'atout
                    continue;
                }
                if(!_cartesCertaines.getVal(_couleurDemandee).get(joueur_)
                        .estVide()) {
                    //joueur possede une carte de la couleur demandee
                    continue;
                }
                if (_cartesPossibles.getVal(_couleurJoueur).get(joueur_)
                        .premiereCarte().strength(_couleurDemandee) > maxForce_) {
                    maxForce_ = _cartesPossibles.getVal(_couleurJoueur)
                            .get(joueur_).premiereCarte().strength(_couleurDemandee);
                }
            }
            if (_carteForte.couleur() == Suit.TRUMP) {
                maxForce_ = (byte) Math.max(maxForce_, _carteForte.strength(_couleurDemandee));
            }
        } else {
            /* Pour fournir a une demande couleur ordinaire ou a une demande atout */
            for (byte joueur_ : _joueursNAyantPasJoue) {
                if(_cartesPossibles.getVal(_couleurJoueur).get(joueur_).estVide()) {
                    //joueur ne fournit pas
                    continue;
                }
                if (_cartesPossibles.getVal(_couleurJoueur).get(joueur_)
                        .premiereCarte().strength(_couleurDemandee) > maxForce_) {
                    maxForce_ = _cartesPossibles.getVal(_couleurJoueur)
                            .get(joueur_).premiereCarte().strength(_couleurDemandee);
                }
            }
            maxForce_ = (byte) Math.max(maxForce_, _carteForte.strength(_couleurDemandee));
        }
        for (HandTarot suite_ : _suites) {
            if (suite_.premiereCarte().strength(_couleurDemandee) <= maxForce_) {
                break;
            }
            //suite.premiereCarte().forceJeu(couleurDemandee) > maxForce
            suitesBis_.add(suite_);
        }
        return suitesBis_;
    }
}
