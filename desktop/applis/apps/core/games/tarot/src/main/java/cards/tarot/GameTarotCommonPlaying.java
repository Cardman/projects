package cards.tarot;

import cards.consts.Hypothesis;
import cards.consts.Suit;
import cards.tarot.comparators.GameTarotMostDemandedSuitComparator;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.PlayingDog;
import code.maths.Rate;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class GameTarotCommonPlaying {
    private final GameTarotTrickInfo doneTrickInfo;
    private final GameTarotTeamsRelation teamsRelation;

    public GameTarotCommonPlaying(GameTarotTrickInfo _doneTrickInfo, GameTarotTeamsRelation _teamsRelation) {
        doneTrickInfo = _doneTrickInfo;
        teamsRelation = _teamsRelation;
    }

    boolean premierTour() {
        return premierTour(teamsRelation.getTaker(), doneTrickInfo.getTricks());
    }

    static boolean premierTour(int _t, CustList<TrickTarot> _tricks) {
        if(GameTarotTeamsRelation.existePreneur(_t)) {
//            for(TrickTarot p: _tricks.mid(1)) {
////                if(!p.getVuParToutJoueur()) {
////                    continue;
////                }
//                plisTotal_++;
//            }
            return _tricks.mid(1).size() == 0;
//            return plisTotal_ == 0;
        }
        int plisTotal_ = _tricks.size();
        return plisTotal_ <= 1;
    }
    TarotInfoPliEnCours initInformations(
            HandTarot _cartes,
            Ints _confident, Ints _notConfident) {
        int nextPlayer_ = doneTrickInfo.getProgressingTrick().getNextPlayer(teamsRelation.getNombreDeJoueurs());
        IdMap<Suit,HandTarot> repartition_ = _cartes.couleurs();
        Ints joueursNonJoue_ = joueursNAyantPasJoue(nextPlayer_);
        CustList<TrickTarot> plisFaits_ = doneTrickInfo.getTricks();
//        CustList<TrickTarot> plisFaits_ = unionPlis();
        HandTarot cartesJouees_ = doneTrickInfo.cartesJoueesEnCours();
        IdMap<Suit,HandTarot> repartitionCartesJouees_ = cartesJouees_.couleurs();
        boolean carteAppeleeJouee_ = cartesJouees_.contientCartes(doneTrickInfo.getCalledCards());
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = doneTrickInfo.cartesPossibles(_cartes);
        IdMap<Hypothesis,IdMap<Suit,CustList<HandTarot>>> hypotheses_ = doneTrickInfo.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        int ramasseurVirtuel_ = doneTrickInfo.getProgressingTrick().getRamasseur(nombreDeJoueurs_);
        IdMap<Suit,CustList<HandTarot>> suitesTouteCouleur_ = _cartes.eclaterToutEnCours(repartitionCartesJouees_);

        boolean maitreAtout_ = GameTarotCommonPlaying.strictMaitreAtout(
                cartesPossibles_,
                nextPlayer_,
                suitesTouteCouleur_.getVal(Suit.TRUMP), cartesJouees_);
        IdList<Suit> couleursMaitresses_ = couleursMaitres(
                suitesTouteCouleur_, cartesJouees_,
                cartesPossibles_, nextPlayer_);
        IdMap<Suit,HandTarot> cartesMaitresses_ = GameTarotCommon.cartesMaitresses(
                repartition_, repartitionCartesJouees_);
        boolean maitreJeu_ = maitreAtout_ && couleursMaitresses_.size() == Suit.couleursOrdinaires().size();
        IdList<Suit> coupesFranches_ = GameTarotCommonPlaying.coupesFranchesStrictes(plisFaits_,
                repartition_, nextPlayer_);

        TarotInfoPliEnCours info_ = new TarotInfoPliEnCours();
        info_.setCurrentPlayer(nextPlayer_);
        info_.setJoueursNonJoue(joueursNonJoue_);
        info_.setPlisFaits(plisFaits_);
        info_.setCartesJouees(cartesJouees_);
        info_.setRepartitionCartesJouees(repartitionCartesJouees_);
        info_.setCarteAppeleeJouee(carteAppeleeJouee_);
        info_.setCartesPossibles(cartesPossibles_);
        info_.setCartesCertaines(cartesCertaines_);
        info_.setRamasseurVirtuel(ramasseurVirtuel_);
        info_.setSuitesTouteCouleur(suitesTouteCouleur_);
        boolean maitreAtoutOwn_ = maitreAtout_;
        if (premierTour() && atoutsSansPetit(repartition_.getVal(Suit.TRUMP)).estVide()) {
            maitreAtoutOwn_ = false;
        }
        info_.setMaitreAtout(maitreAtoutOwn_);
        info_.setCouleursMaitresses(couleursMaitresses_);
        info_.setCartesMaitresses(cartesMaitresses_);
        info_.setMaitreJeu(maitreJeu_);
        info_.setCoupesFranches(coupesFranches_);
        info_.setCalledSuits(couleursAppelees());
        info_.setProgressingTrick(doneTrickInfo.getProgressingTrick());
        info_.setNbPlayers(nombreDeJoueurs_);
        info_.setTaker(teamsRelation.getTaker());
        info_.setJoueursConfiance(_confident);
        info_.setJoueursNonConfiance(_notConfident);
        //depend de partie et de cartesJouables
        //carteEntamee
        //joueursNonJoue
        //joueursConfianceNonJoue
        //joueursNonConfianceNonJoue
        //CustList<PliTarot> plisFaits
        //CustList<Byte> joueursJoue
        return info_;
    }
//    public CustList<TrickTarot> unionPlis() {
//        CustList<TrickTarot> unionPlis_ = new CustList<TrickTarot>();
//        if(teamsRelation.existePreneur()) {
//            unionPlis_.addAllElts(doneTrickInfo.getTricks());
//            return unionPlis_;
//        }
//        for (TrickTarot t: doneTrickInfo.getTricks()) {
//            if (!t.getVuParToutJoueur()) {
//                continue;
//            }
//            unionPlis_.add(t);
//        }
//        return unionPlis_;
//    }
    IdList<Suit> couleursAppelees() {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for(CardTarot c: doneTrickInfo.getCalledCards()) {
            couleurs_.add(c.getId().getCouleur());
        }
        return couleurs_;
    }
    Ints joueursNAyantPasJoue(int _numero) {
        Ints joueursNAyantPasJoue_ = new Ints();
        int nombreJoueurs_ = teamsRelation.getNombreDeJoueurs();
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            if (joueur_ != _numero && !doneTrickInfo.getProgressingTrick().aJoue(joueur_, nombreJoueurs_)) {
                joueursNAyantPasJoue_.add(joueur_);
            }
        }
        return joueursNAyantPasJoue_;
    }

    HandTarot cartesVuesAuChien() {
        HandTarot cartes_ = new HandTarot();
        cartes_.ajouterCartes(doneTrickInfo.getLastSeenHand());
        return cartes_;
    }
    boolean appeleConnuDefenseur(int _joueur, IdMap<Suit, CustList<HandTarot>> _cartesPossibles) {
        int nombreDeJoueurs_ = teamsRelation.getNombreDeJoueurs();
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
    static IdList<Suit> couleursNonOuvertesNonVides(HandTarot _main,
                                                      CustList<TrickTarot> _plis, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = GameTarotCommon.couleursNonAtoutNonVides(_main, _couleurs);
        return couleursNonEntamees(_plis,couleurs_);
    }
    static IdList<Suit> couleursNonEntamees(
            CustList<TrickTarot> _plis, IdList<Suit> _couleurs) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdList<Suit> couleursOuvertes_ = new IdList<Suit>();
        IdList<Suit> toutesCouleursOrdinaires_ = Suit.couleursOrdinaires();
        for (TrickTarot pli_ : _plis.mid(1)) {
//            if (!pli_.getVuParToutJoueur()) {
//                continue;
//            }
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
    static IdList<Suit> strictCouleursMaitres(
            IdMap<Suit,CustList<HandTarot>> _suites, IdMap<Suit,HandTarot> _cartesJouees,
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles, int _numero) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot couleurComplete_ = HandTarot.couleurComplete(couleur_);
            if (_cartesJouees.getVal(couleur_).total() == couleurComplete_.total()) {
                couleurs_.add(couleur_);
                continue;
            }
            if (!_suites.getVal(couleur_).isEmpty()) {
                addSuit(_suites, _cartesJouees, _cartesPossibles, _numero, couleurs_, couleur_);
            }
        }
        return couleurs_;
    }

    private static void addSuit(IdMap<Suit, CustList<HandTarot>> _suites, IdMap<Suit, HandTarot> _cartesJouees, IdMap<Suit, CustList<HandTarot>> _cartesPossibles, int _numero, IdList<Suit> _couleurs, Suit _couleur) {
        int max_ = getNbMaxPossPlayerCards(_cartesPossibles, _numero, _couleur);
        boolean existeAtoutMaitre_ = true;
        CardTarot c = _suites.getVal(_couleur).first().premiereCarte();
        for (CardTarot carte_ : HandTarot.cartesCouleursAuDessusDe(c)) {
            if (!_cartesJouees.getVal(_couleur).contient(carte_)
                    && !_suites.getVal(_couleur).first().contient(carte_)) {
                existeAtoutMaitre_ = false;
                break;
            }
        }
        int maitres_ = IndexConstants.SIZE_EMPTY;
        if (existeAtoutMaitre_) {
            maitres_ = _suites.getVal(_couleur).first().total();
        }
        if (maitres_ >= max_ || maitres_ > IndexConstants.SIZE_EMPTY
                && _suites.getVal(_couleur).size() == IndexConstants.ONE_ELEMENT) {
            _couleurs.add(_couleur);
        }
    }

    private static int getNbMaxPossPlayerCards(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, int _numero, Suit _couleur) {
        int max_ = IndexConstants.SIZE_EMPTY;
            /*
            max designe le nombre maximal de cartes probablement
            possedees par un joueur a une couleur donnee
            */
        CustList<HandTarot> possibleSuits_ = _cartesPossibles.getVal(_couleur);
        int nbSuits_ = possibleSuits_.size() - 1;
        CustList<HandTarot> possibleExcuse_ = _cartesPossibles.getVal(CardTarot.EXCUSE.getId().getCouleur());
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbSuits_; joueur_++) {
            if (joueur_ != _numero) {
                int sum_ = possibleSuits_.get(joueur_).total() + possibleExcuse_.get(joueur_).total();
                if (sum_ > max_) {
                    max_ = sum_;
                }
            }
        }
        return max_;
    }

    static IdList<Suit> couleursMaitres(
            IdMap<Suit,CustList<HandTarot>> _suites, HandTarot _cartesJouees,
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles, int _numero) {
        IdMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        IdList<Suit> couleurs_ = strictCouleursMaitres(_suites,cartesJouees_,_cartesPossibles,_numero);
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            if (_suites.getVal(couleur_).isEmpty()) {
                couleurs_.add(couleur_);
            }
        }
        couleurs_.removeDuplicates();
        return couleurs_;
    }
    static IdList<Suit> couleursAvecCarteMaitresse(IdList<Suit> _couleurs, IdMap<Suit, HandTarot> _cartesMaitresses) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot cartesMaitresses_ = _cartesMaitresses.getVal(couleur_);
            if (!cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }
    static IdList<Suit> couleursAvecCarteNonMaitresse(HandTarot _main,
                                                        IdList<Suit> _couleurs, IdMap<Suit, HandTarot> _cartesMaitresses) {
        IdList<Suit> couleurs_ = new IdList<Suit>();
        IdMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for (Suit couleur_ : _couleurs) {
            HandTarot cartesMaitresses_ = _cartesMaitresses.getVal(couleur_);
            if (cartesMaitresses_.total() != couleursMains_.getVal(couleur_).total()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }
    int playerAfter(int _player) {
        return teamsRelation.getRules().getDealing().getId().getNextPlayer(_player);
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
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            int _numero,
            CustList<HandTarot> _suites, HandTarot _cartesJouees) {
        IdMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Suit couleurAtout_ = Suit.TRUMP;
        int nbTotalAtouts_ = HandTarot.atoutsSansExcuse().total();
        int max_ = IndexConstants.SIZE_EMPTY;
        /*
        max designe le nombre maximal de cartes probablement
        possedees par un joueur
        */
        CustList<HandTarot> possibleTrumps_ = _cartesPossibles.getVal(couleurAtout_);
        CustList<HandTarot> possibleExc_ = _cartesPossibles.getVal(Suit.UNDEFINED);
        int nbPlayers_ = possibleTrumps_.size() - 1;
        for (int joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nbPlayers_; joueur_++) {
            // La taille de atoutsPossibles
            // correspond au nombre de joueurs+1
            if (joueur_ == _numero) {
                continue;
            }
            int sum_ = possibleTrumps_.get(joueur_).total()
                    + possibleExc_.get(joueur_).total();
            if (sum_ > max_) {
                max_ = sum_;
            }
        }
        /*
        Fin boucle sur le calcul de la valeur maximale possible des atouts
        */
        if (max_ == IndexConstants.SIZE_EMPTY) {
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
        boolean existeAtoutMaitre_ = existeAtoutMaitre(_suites, cartesJouees_, couleurAtout_);
        return existeAtoutMaitre_ && _suites.first().total() >= max_;
    }

    private static boolean existeAtoutMaitre(CustList<HandTarot> _suites, IdMap<Suit, HandTarot> _cartesJouees, Suit _couleurAtout) {
        boolean existeAtoutMaitre_ = true;
        for (CardTarot carte_ : HandTarot.atoutsAuDessusDe(_suites.first()
                .premiereCarte())) {
            if (!_cartesJouees.getVal(_couleurAtout).contient(carte_) && !_suites.first().contient(carte_)) {
                existeAtoutMaitre_ = false;
                break;
            }
        }
        return existeAtoutMaitre_;
    }

    /**
     Est vrai si et seulement si le nombre
     2 x nb_atout_maitres_joueur+3 x nb_atout_non_maitres_joueur/2+atouts_jouees
     depasse strictement 21
     */
    static boolean maitreAtout(CustList<HandTarot> _suites,
                               HandTarot _cartesJouees, boolean _excuse) {
        int maitres_;
        int nonmaitres_;
        int nb_;
        IdMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Suit couleurAtout_ = Suit.TRUMP;
        int nbTotalAtouts_ = HandTarot.atoutsSansExcuse().total();
        if (cartesJouees_.getVal(couleurAtout_).total() == nbTotalAtouts_) {
            return true;
        }
        if (_suites.isEmpty()) {
            return false;
        }
        boolean existeAtoutMaitre_ = existeAtoutMaitre(_suites, cartesJouees_, couleurAtout_);
        if (existeAtoutMaitre_) {
            maitres_ = _suites.first().total();
            nonmaitres_ = 0;
        } else {
            maitres_ = 0;
            nonmaitres_ = _suites.first().total();
        }
        int nbSeqs_ = _suites.size();
        for (int suite_ = IndexConstants.SECOND_INDEX; suite_ < nbSeqs_; suite_++) {
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

    static IdList<Suit> coupesFranchesStrictes(
            CustList<TrickTarot> _plisFaits, IdMap<Suit,HandTarot> _repartitionCouleur, int _numero) {
        IdList<Suit> coupesFranchesStrictes_ = new IdList<Suit>();
        for (Suit c: Suit.couleursOrdinaires()) {
            if (!_repartitionCouleur.getVal(c).estVide()) {
                continue;
            }
            boolean coupeToujours_ = true;
            for (TrickTarot pli_ : _plisFaits.mid(1)) {
                if (pli_.couleurDemandee() != c || pli_.carteDuJoueur(_numero).getId().getCouleur() == Suit.TRUMP) {
                    //!pli_.getVuParToutJoueur() ||
                    continue;
                }
                coupeToujours_ = false;
            }
            if (coupeToujours_) {
                coupesFranchesStrictes_.add(c);
            }
        }
        return coupesFranchesStrictes_;
    }
    static HandTarot cartesNonMaitresses(IdMap<Suit,HandTarot> _couleurs,
                                         IdMap<Suit,HandTarot> _cartesMaitresses,
                                         IdMap<Suit,CustList<HandTarot>> _suites) {
        HandTarot cards_ = new HandTarot();
        for (Suit couleur_ : Suit.couleursOrdinaires()) {
            HandTarot cartesCouleurMaitresse_ = _cartesMaitresses
                    .getVal(couleur_);
            if (!_couleurs.getVal(couleur_).estVide()) {
                CustList<HandTarot> seqs_ = _suites.getVal(couleur_);
                if (cartesCouleurMaitresse_.estVide()) {
                    cards_.ajouterCartes(seqs_.first());
                }
                int nbSeqs_ = seqs_.size();
                for (int indiceSuite_ = IndexConstants.SECOND_INDEX; indiceSuite_ < nbSeqs_; indiceSuite_++) {
                    cards_.ajouterCartes(seqs_.get(indiceSuite_));
                }
            }
        }
        return cards_;
    }
    static Rate moyenneAtout(HandTarot _main, HandTarot _atoutsJoues,
                             IdMap<Suit,CustList<HandTarot>> _cartesPossibles, int _nombreJoueurs) {
        CustList<HandTarot> repartitionAtout_ = _cartesPossibles
                .getVal(Suit.TRUMP);
        int nombreDefausses_ = 0;
        for (int i = 0; i < _nombreJoueurs; i++) {
            HandTarot main_ = repartitionAtout_.get(i);
            if (!main_.estVide()) {
                continue;
            }
            nombreDefausses_++;
        }
        HandTarot atoutsNonJoues_ = new HandTarot();
        int nombreJoueursAvecAtout_ = _nombreJoueurs
                - nombreDefausses_ - 1;
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
    public static HandTarot atoutsPoignee(IdMap<Suit,HandTarot> _repartition) {
        HandTarot m = new HandTarot();
        m.ajouterCartes(_repartition.getVal(Suit.TRUMP));
        if (!_repartition.getVal(CardTarot.excuse().getId().getCouleur()).estVide()) {
            m.ajouter(CardTarot.excuse(), 0);
        }
        return m;
    }
    static CustList<TrickTarot> tours(Suit _couleur, CustList<TrickTarot> _plisFaits) {
        CustList<TrickTarot> tricksNumbers_ = new CustList<TrickTarot>();
        for (TrickTarot pli_ : _plisFaits.mid(1)) {
            if (pli_.couleurDemandee() != _couleur) {
                //!pli_.getVuParToutJoueur() ||
                continue;
            }
            tricksNumbers_.add(pli_);
        }
        return tricksNumbers_;
    }
    static Ints ramasseurs(CustList<TrickTarot> _plisFaits) {
        Ints ramasseurs_ = new Ints();
        for(TrickTarot pli_: _plisFaits.mid(1)) {
//            if(!pli_.getVuParToutJoueur()) {
//                continue;
//            }
            int ramasseur_ = pli_.getRamasseur();
            if (!ramasseurs_.containsObj(ramasseur_)) {
                ramasseurs_.add(ramasseur_);
            }
        }
        return ramasseurs_;
    }
    static IdList<Suit> couleursLesPlusEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Ints _joueurs, IdList<Suit> _couleurs) {
        return couleursTrieesPlusEntameesParJoueurs(_plisFaits, _joueurs, _couleurs).first();
    }

    private static CustList<IdList<Suit>> couleursTrieesPlusEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Ints _joueurs, IdList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameTarotMostDemandedSuitComparator(_plisFaits, _joueurs));
    }

    static IdList<Suit> couleursLesMoinsEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Ints _joueurs, IdList<Suit> _couleurs) {
        return couleursTrieesPlusEntameesParJoueurs(_plisFaits, _joueurs, _couleurs).last();
    }

    static CustList<HandTarot> cartesRelativementMaitreEncours(
            CustList<HandTarot> _suites,
            IdMap<Suit,CustList<HandTarot>> _cartesPossibles,
            Ints _joueursNAyantPasJoue, Suit _couleurJoueur,
            Suit _couleurDemandee, IdMap<Suit,CustList<HandTarot>> _cartesCertaines,
            CardTarot _carteForte) {
        if (!Suit.couleursOrdinaires().containsObj(_couleurJoueur) && Suit.couleursOrdinaires().containsObj(_couleurDemandee) ) {
            int maxForce_ = maxForceCoupe(_cartesPossibles, _joueursNAyantPasJoue, _couleurJoueur, _couleurDemandee, _cartesCertaines, _carteForte);
            return filterSeq(_suites, _couleurDemandee, maxForce_);
        } else {
            int maxForce_ = maxForceFournnir(_cartesPossibles, _joueursNAyantPasJoue, _couleurJoueur, _couleurDemandee, _carteForte);
            return filterSeq(_suites, _couleurDemandee, maxForce_);
        }
    }

    private static int maxForceFournnir(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Ints _joueursNAyantPasJoue, Suit _couleurJoueur, Suit _couleurDemandee, CardTarot _carteForte) {
        int maxForce_ = 0;
        /* Pour fournir a une demande couleur ordinaire ou a une demande atout */
        for (int joueur_ : _joueursNAyantPasJoue) {
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
        maxForce_ = NumberUtil.max(maxForce_, _carteForte.strength(_couleurDemandee));
        return maxForce_;
    }

    private static int maxForceCoupe(IdMap<Suit, CustList<HandTarot>> _cartesPossibles, Ints _joueursNAyantPasJoue, Suit _couleurJoueur, Suit _couleurDemandee, IdMap<Suit, CustList<HandTarot>> _cartesCertaines, CardTarot _carteForte) {
        int maxForce_ = 0;
        /* Pour une coupe */
        for (int joueur_ : _joueursNAyantPasJoue) {
            if (_cartesPossibles.getVal(_couleurJoueur).get(joueur_).estVide() || !_cartesCertaines.getVal(_couleurDemandee).get(joueur_)
                    .estVide()) {
                continue;
            }
            //joueur a un atout
            //joueur ne possede pas une carte de la couleur demandee
            if (_cartesPossibles.getVal(_couleurJoueur).get(joueur_)
                    .premiereCarte().strength(_couleurDemandee) > maxForce_) {
                maxForce_ = _cartesPossibles.getVal(_couleurJoueur)
                        .get(joueur_).premiereCarte().strength(_couleurDemandee);
            }
        }
        if (_carteForte.getId().getCouleur() == Suit.TRUMP) {
            maxForce_ = NumberUtil.max(maxForce_, _carteForte.strength(_couleurDemandee));
        }
        return maxForce_;
    }

    private static CustList<HandTarot> filterSeq(CustList<HandTarot> _suites, Suit _couleurDemandee, int _maxForce) {
        CustList<HandTarot> suitesBis_ = new CustList<HandTarot>();
        for (HandTarot suite_ : _suites) {
            if (suite_.premiereCarte().strength(_couleurDemandee) <= _maxForce) {
                break;
            }
            //suite.premiereCarte().forceJeu(couleurDemandee) > maxForce
            suitesBis_.add(suite_);
        }
        return suitesBis_;
    }

    static HandTarot atoutsSansPetit(HandTarot _atouts) {
        HandTarot atoutsSansPetit_ = new HandTarot();
        for (CardTarot carte_ : _atouts) {
            if (CardTarot.eq(carte_, CardTarot.petit())) {
                continue;
            }
            atoutsSansPetit_.ajouter(carte_);
        }
        return atoutsSansPetit_;
    }

}
