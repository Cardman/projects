package cards.tarot;
import java.util.concurrent.atomic.AtomicInteger;

import cards.consts.CardChar;
import cards.consts.EndGameState;
import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.PossibleTrickWinner;
import cards.consts.Status;
import cards.consts.Suit;
import cards.tarot.comparators.BidTarotComparator;
import cards.tarot.comparators.CalledSuitComparator;
import cards.tarot.comparators.GameSeqLengthTarotComparator;
import cards.tarot.comparators.GameStrengthGreatHandTarotComparator;
import cards.tarot.comparators.GameStrengthLowHandTarotComparator;
import cards.tarot.comparators.GameStrengthLowLastHandTarotComparator;
import cards.tarot.comparators.GameTarotLeastDemandedSuitComparator;
import cards.tarot.comparators.GameTarotMostDemandedSuitComparator;
import cards.tarot.comparators.HandTarotCharLongLengthComparator;
import cards.tarot.comparators.HandTarotCharShortLengthComparator;
import cards.tarot.comparators.HandTarotLongLengthComparator;
import cards.tarot.comparators.HandTarotShortLengthComparator;
import cards.tarot.enumerations.*;
import code.maths.Rate;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.EqList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.TreeMap;
import code.util.comparators.ComparatorEnum;

/** */

public final class GameTarot {

    public static final int NO_OUDLER_PTS = 56;
    public static final int ONE_OUDLER_PTS = 51;
    public static final int TWO_OUDLERS_PTS = 41;
    public static final int ALL_OUDLERS_PTS = 36;

    public static final int PTS_BASE = 25;

    private static final int PERCENT_MAX = 100;

    private static final int PERCENT_DELTA = 10;

    private AtomicInteger chargementSimulation = new AtomicInteger();

    /**
    Le type d'une partie est aleatoire ou encore edite ou encore un
    entrainement
    */
    private GameType type;
    /** Contient toutes les cartes au debut de chaque partie */
    private DealTarot deal;
    /**
    Au dbut on a besoin d'un variable preneur pour stocker le joueur ayant
    annonce temporairement le plus haut contrat De plus, le choix par defaut
    de -1 pour le preneur sert pour le tarot lorsque personne ne prend un
    contrat et chacun joue pour soi
    */
    private byte taker = CustList.INDEX_NOT_FOUND_ELT;
    /** Ce sont les poignees annoncees par le(s) joueur(s) */
    private CustList<EnumList<Handfuls>> declaresHandfuls = new CustList<EnumList<Handfuls>>();
    /** Ce sont les miseres annoncees par le(s) joueur(s) */
    private EqList<EnumList<Miseres>> declaresMiseres = new EqList<EnumList<Miseres>>();
    /** Ce sont les primes annoncees par le(s) joueur(s) */
    private BooleanList declaresSlam = new BooleanList();
    /** Ce sont les petits au bout par le(s) joueur(s) */
    private BooleanList smallBound = new BooleanList();
    /** Poignees */
    private EqList<HandTarot> handfuls = new EqList<HandTarot>();
    /**
    Au tarot lors d'un appel il faut savoir si les joueurs ont confiance ou
    non en les autres
    */
    private CustList<BooleanList> confidence = new CustList<BooleanList>();
    /**
    Le contrat permet de dire quel va etre le deroulement de la partie
    */
    private BidTarot bid = BidTarot.FOLD;
    /** Ce sont les plis faits par les joueurs */
    /** PliTarot en cours d'etre joue */
    private TrickTarot progressingTrick = new TrickTarot(CustList.INDEX_NOT_FOUND_ELT, false);

    /** Ensemble des plis faits par les joueurs */
    private CustList<TrickTarot> tricks = new CustList<TrickTarot>();
    private Numbers<Byte> calledPlayers = new Numbers<Byte>();
    /**The called cards are the cards owned by the taker's probably parteners
    */
    private HandTarot calledCards = new HandTarot();
    /** Entameur du pli qui est en cours d'etre joue */
    private byte starter;
    /** Ensembe des contrats annonces */
    private EnumList<BidTarot> bids = new EnumList<BidTarot>();
    /** Ramasseur du pli qui vient d'etre joue */
    private byte trickWinner;
    /**
    utilise pour dire si l'utilisateur a annonce un contrat ou non au
    chargement d'une partie
    */
    private boolean simulationWithBids;
    /**
    Scores cumules au cours des parties Chaque nombre (Short) represente un
    score pour le joueur
    */
    private Numbers<Short> scores = new Numbers<Short>();
    /** Nombre de fois qu'a ete joue la partie(partie fini) */
    private long number;
    private RulesTarot rules = new RulesTarot();

    private String error = "";
    private byte lastHasBid = CustList.INDEX_NOT_FOUND_ELT;

    private BidTarot lastBid = BidTarot.FOLD;

    private HandTarot cardsToBeDiscarded = new HandTarot();

    private CardTarot playedCard = CardTarot.WHITE;

    private StringBuilder reason = new StringBuilder();

    /** Constructeur permettant le chargement d'une partie de tarot */
    public GameTarot() {
    }

    /**
    Cree une partie de tarot ayant pour parametres
    @param _type
        le type de partie aleatoire, editee ou entrainement
    @param _donne
        la distribution de cartes entre les joueurs
    @param mode2
        le mode indiquant le deroulement de la partie
    */
    public GameTarot(GameType _type, DealTarot _donne, RulesTarot _regles) {
        type = _type;
        deal = _donne;
        rules = _regles;
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i = CustList.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.add((short) 0);
        }
        if (!avecContrat()) {
            tricks.add(
                    new TrickTarot(getDistribution().derniereMain(),
                            (byte) (nombreJoueurs_ + 1), false));
        }
        for (int i = CustList.FIRST_INDEX; i < nombreJoueurs_; i++) {
        /*
        Initialise la confiance a un
        jeu non solitaire
        */
            confidence.add(new BooleanList());
            for (int j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
                confidence.last().add(i == j);
            }
        }
        if(!avecContrat()
                && getRegles().getRepartition().getAppel() == CallingCard.DEFINED) {
            initEquipeDetermineeSansPreneur();
        }
        for (int i = CustList.FIRST_INDEX; i < nombreJoueurs_; i++) {
            declaresHandfuls.add(new EnumList<Handfuls>());
            declaresMiseres.add(new EnumList<Miseres>());
            declaresSlam.add(false);
            smallBound.add(false);
        }
        // Par default tout le monde est defenseur
        for (byte j_ = CustList.FIRST_INDEX; j_ < nombreJoueurs_; j_++) {
            handfuls.add(new HandTarot());
        }
        lastHasBid = -1;
    }

    public void initPlayWithoutBid() {
        tricks.add(
                new TrickTarot(getDistribution().derniereMain(),
                        (byte) (getNombreDeJoueurs() + 1), false));
        if (getRegles().getRepartition().getAppel() == CallingCard.DEFINED) {
            initEquipeDetermineeSansPreneur();
        }
    }

    HandTarot getWonTricksTeam(byte _player) {
        HandTarot cards_ = new HandTarot();
        for (TrickTarot t: tricks) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            if (!memeEquipe(t.getRamasseur(), _player)) {
                continue;
            }
            cards_.ajouterCartes(t.getCartes());
        }
        return cards_;
    }

    CustList<TrickTarot> getWonTricksListTeam(byte _player) {
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        for (TrickTarot t: tricks) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            if (!memeEquipe(t.getRamasseur(), _player)) {
                continue;
            }
            tricks_.add(t);
        }
        return tricks_;
    }

    private static Suit couleurAtout() {
        return Suit.TRUMP;
    }


    public void initPartie() {
        taker = -1;
        calledPlayers.clear();
        bids = new EnumList<BidTarot>();
        bid = BidTarot.FOLD;
        progressingTrick = new TrickTarot((byte) -1, false);
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i = CustList.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.set( i, (short) 0);
        }
        if (!unionPlis(true).isEmpty()) {
            tricks.clear();
            tricks.add(new TrickTarot(getDistribution().derniereMain(),
                    (byte) (nombreJoueurs_ + 1), false));
        }
        if(!avecContrat()
                && getRegles().getRepartition().getAppel() == CallingCard.DEFINED) {
            initEquipeDetermineeSansPreneur();
        } else {
            for (int i = CustList.FIRST_INDEX; i < nombreJoueurs_; i++) {
            /*
            Initialise la confiance a un
            jeu non solitaire
            */
                for (int j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
                    confidence.get(i).set(j, i == j);
                }
            }
        }
        for (int i = CustList.FIRST_INDEX; i < nombreJoueurs_; i++) {
            declaresHandfuls.set( i, new EnumList<Handfuls>());
            declaresMiseres.set( i, new EnumList<Miseres>());
            smallBound.set(i, false);
            declaresSlam.set(i, false);
        }
        // Par default tout le monde est defenseur
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            handfuls.set( joueur_, new HandTarot());
        }
        cardsToBeDiscarded = new HandTarot();
        lastHasBid = -1;
    }

    void loadGame() {
        byte player_ = playerAfter(deal.getDonneur());
        taker = CustList.INDEX_NOT_FOUND_ELT;
        BidTarot bid_ = BidTarot.FOLD;
        for (BidTarot b: bids) {
            if (b.strongerThan(bid_)) {
                taker = player_;
                bid_ = b;
            }
            player_ = playerAfter(player_);
        }
        bid = bid_;
        if (!avecContrat()) {
            initEquipeDetermineeSansPreneur();
            calledPlayers = new Numbers<Byte>();
        } else if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
            if (!keepBidding()) {
                initEquipeDeterminee();
            } else {
                calledPlayers = new Numbers<Byte>();
            }
        } else if (rules.getRepartition().getAppel() == CallingCard.WITHOUT) {
            calledPlayers = new Numbers<Byte>();
            initDefense();
        } else {
            calledPlayers = joueursAyantCarteAppelee();
        }
        cardsToBeDiscarded.supprimerCartes();
        if (bid.getJeuChien() == PlayingDog.WITH) {
            if (tricks.isEmpty()) {
                cardsToBeDiscarded.ajouterCartes(getPliEnCours().getCartes());
            } else {
                cardsToBeDiscarded.ajouterCartes(tricks.first().getCartes());
            }
        }
        CustList<TrickTarot> tricks_ = unionPlis(false);
        if (!tricks_.isEmpty()) {
            starter = progressingTrick.getEntameur();
            trickWinner = progressingTrick.getEntameur();
        } else if (progressingTrick.getVuParToutJoueur()) {
            starter = progressingTrick.getEntameur();
            trickWinner = progressingTrick.getEntameur();
        } else if (!avecContrat()) {
            starter = playerAfter(deal.getDonneur());
            trickWinner = starter;
        } else if (contrats() < getNombreDeJoueurs()) {
            starter = playerAfter(deal.getDonneur());
            trickWinner = starter;
        } else if (pasJeuApresPasse()) {
            //if existePreneur()
            starter = taker;
            trickWinner = taker;
        } else {
            starter = playerAfter(deal.getDonneur());
            trickWinner = starter;
        }
    }

    public void simuler() {
        simulationWithBids = false;
        if (joueurAyantPetitSec() > -1) {
            setChargementSimulation(PERCENT_MAX);
            return;
        }
        byte nombreJoueurs_ = getNombreDeJoueurs();
        byte donneur_ = getDistribution().getDonneur();
        if (avecContrat()) {
            BidTarot contratTmp_;
            for (byte joueur_ : orderedPlayers(playerAfter(donneur_))) {
                contratTmp_ = strategieContrat();
                ajouterContrat(contratTmp_,joueur_);
                if(maximumBid(contratTmp_)) {
                    break;
                }
            }
            setChargementSimulation(getChargementSimulation() + PERCENT_DELTA);
        }
        if (!bid.isJouerDonne() && pasJeuApresPasse()) {
            setChargementSimulation(PERCENT_MAX);
            return;
        }
        simulationWithBids = true;
        if (bid.isJouerDonne()) {
            if (rules.getDiscardAfterCall()) {
                if (rules.getRepartition().getAppel() == CallingCard.CHARACTER_CARD
                        || rules.getRepartition().getAppel() == CallingCard.KING) {
                    intelligenceArtificielleAppel();
                } else if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
                    initEquipeDeterminee();
                } else {
                    initDefense();
                }
                if (bid.getJeuChien() == PlayingDog.WITH) {
                    ecarter(true);
                } else {
                    gererChienInconnu();
                    ajouterChelem(taker, annoncerUnChelem(taker));
                    if (declaresSlam.get(taker)) {
                        setEntameur(taker);
                    }
                }
            } else {
                if (bid.getJeuChien() == PlayingDog.WITH) {
                    appelApresEcart();
                } else {
                    if (rules.getRepartition().getAppel() == CallingCard.CHARACTER_CARD
                            || rules.getRepartition().getAppel() == CallingCard.KING) {
                        intelligenceArtificielleAppel();
                    } else if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
                        initEquipeDeterminee();
                    } else {
                        initDefense();
                    }
                    gererChienInconnu();
                    ajouterChelem(taker, annoncerUnChelem(taker));
                    if (declaresSlam.get(taker)) {
                        setEntameur(taker);
                    }
                }
            }
        }
        if (!chelemAnnonce()) {
            /*
        Si un joueur n'a pas annonce de Chelem on
        initialise l'entameur du premier pli
        */
            starter = (byte) ((donneur_ + 1) % nombreJoueurs_);
        }
        setChargementSimulation(getChargementSimulation() + PERCENT_DELTA);
        int rapport_ = 75 / getDistribution().main().total();
        setPliEnCours(true);
        for (byte joueur_ : orderedPlayers(starter)) {
            changerConfiance();
            CardTarot ct_ = strategieJeuCarteUnique();
            if (pasJeuMisere()) {
                EnumList<Miseres> annoncesMiseres_ = strategieAnnoncesMiseres(
                        joueur_);
                setAnnoncesMiseres(joueur_, annoncesMiseres_);
                EnumList<Handfuls> annoncesPoignees_ = strategieAnnoncesPoignees(
                        joueur_);
                setAnnoncesPoignees(joueur_, annoncesPoignees_);
                HandTarot poignee_ = strategiePoignee(joueur_);
                ajouterPoignee(poignee_, joueur_);
            }
            jouer(joueur_,ct_);
            ajouterUneCarteDansPliEnCours(ct_);
        }
        setChargementSimulation(getChargementSimulation() + rapport_);
        while (!getDistribution().main().estVide()) {
            ajouterPliEnCours();
            setPliEnCours(true);
            for (byte joueur_ : orderedPlayers(starter)) {
                changerConfiance();
                CardTarot ct_ = strategieJeuCarteUnique();
                jouer(joueur_,ct_);
                ajouterUneCarteDansPliEnCours(ct_);
            }
            ajouterPetitAuBoutFinPartie();
            setChargementSimulation(getChargementSimulation() + rapport_);
        }
        setChargementSimulation(PERCENT_MAX);
    }

    public HandTarot mainUtilisateurTriee(DisplayingTarot _regles) {
        HandTarot main_ = new HandTarot();
        main_.ajouterCartes(getDistribution().main());
        main_.trier(_regles.getCouleurs(),_regles.getDecroissant());
        return main_;
    }

    void jouer(byte _joueur, CardTarot _ct) {
        deal.jouer(_joueur,_ct);
    }

    public void ajouterUtilisateur(CardTarot _carte) {
        deal.ajouterUtilisateur(_carte);
    }
    public void addCard(byte _place, CardTarot _card) {
        deal.ajouter(_place, _card);
    }

    public void ajouterCartesUtilisateur() {
        setEntameur(getPreneur());
        setPliEnCours(false);
        deal.ajouterCartes(getPreneur(),deal.derniereMain());
    }
    void supprimerCartes(byte _preneur, HandTarot _main) {
        deal.supprimerCartes(_preneur, _main);
    }

    void ajouterCartes(byte _preneur, HandTarot _derniereMain) {
        deal.ajouterCartes(_preneur, _derniereMain);
    }

    HandTarot derniereMain() {
        return deal.derniereMain();
    }

    /** Renvoie le nombre de joueurs jouant a la partie */
    public byte getNombreDeJoueurs() {
        return (byte) rules.getRepartition().getNombreJoueurs();
    }

    public RulesTarot getRegles() {
        return getRules();
    }

    public DealTarot getDistribution() {
        return getDeal();
    }

    public EnumList<BidTarot> allowedBids() {
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        for (BidTarot b: BidTarot.values()) {
            if (!contratAccepte(b)) {
                continue;
            }
            bids_.add(b);
        }
        bids_.sortElts(new BidTarotComparator());
        return bids_;
    }
    boolean contratAccepte(BidTarot _enchere) {
        return rules.getContrats().getVal(_enchere);
    }

    public boolean avecContrat() {
        ModeTarot mode_ = rules.getMode();
        if (mode_ == ModeTarot.NORMAL) {
            return true;
        }
        if (mode_ == ModeTarot.NORMAL_WITH_ONE_FOR_ONE) {
            return true;
        }
        return mode_ == ModeTarot.NORMAL_WITH_MISERE;
    }

    public boolean pasJeuApresPasse() {
        return rules.getMode() == ModeTarot.NORMAL;
    }

    public boolean pasJeuMisere() {
        ModeTarot mode_ = rules.getMode();
        if (mode_ == ModeTarot.NORMAL_WITH_MISERE) {
            return false;
        }
        return mode_ != ModeTarot.MISERE;
    }

    public byte joueurAyantPetitSec() {
        int nbPlayers_ = getNombreDeJoueurs();
        for (byte p_ = CustList.FIRST_INDEX; p_ < nbPlayers_; p_++) {
            if (getDistribution().main(p_).petitSec()) {
                return p_;
            }
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    /**for multi player*/
    public boolean playerHasAlreadyBidded(byte _player) {
        BidTarot bid_ =strategieContrat();
        int nbBids_ = contrats();
        ajouterContrat(bid_,_player);
        if (nbBids_ == contrats()) {
            return true;
        }
        lastBid = bid_;
        return false;
    }

    public BidTarot getLastBid() {
        return lastBid;
    }

    public BidTarot strategieContrat() {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        byte numero_ = playerHavingToBid();
        HandTarot mj_ = getDistribution().main(numero_);
        EnumMap<Suit,HandTarot> couleurs_ = mj_.couleurs();
        int atouts_ = couleurs_.getVal(CardTarot.excuse().couleur()).total() + couleurs_.getVal(couleurAtout()).total();
        boolean chelem_ = estUnJeuDeChelem(couleurs_, new HandTarot().couleurs(), rules, cartesAppeler(numero_), nombreJoueurs_);
        if (chelem_) {
            BidTarot e_ = BidTarot.FOLD;
            for(BidTarot e: allowedBids()) {
                if(!e.isFaireTousPlis()) {
                    continue;
                }
                if(e.estDemandable(e_)) {
                    e_ = e;
                }
            }
            if(e_.isJouerDonne()) {
                return e_;
            }
        }
        if (chelem_) {
            BidTarot e_ = BidTarot.FOLD;
            for(BidTarot e: allowedBids()) {
                if(e.estDemandable(e_)) {
                    e_ = e;
                }
            }
            if(e_.isJouerDonne()) {
                return e_;
            }
        }
        Suit couleurAtout_ = couleurAtout();
        HandTarot trumps_ = couleurs_.getVal(couleurAtout_);
        EqList<HandTarot> suitesAtouts_ = trumps_.eclaterDebutPartie();
        int valeurAtout_;
        int valeurAtoutMoyen_;
        int valeurAtoutMajeur_;
        int nbAtoutsQuinzeAuVingtEtUn_ = 0;
        int nbAtoutsSeptAuQuatorze_ = 0;
        int nbAtoutsMajeursConsecutifs_ = 0;
        HandTarot bouts_ = new HandTarot();
        if (!couleurs_.getVal(CardTarot.EXCUSE.couleur()).estVide()) {
            bouts_.ajouter(CardTarot.excuse());
        }
        if (trumps_.contient(CardTarot.vingtEtUn())) {
            bouts_.ajouter(CardTarot.vingtEtUn());
        }
        if (trumps_.contient(CardTarot.petit())) {
            bouts_.ajouter(CardTarot.petit());
        }
        for (HandTarot main_ : suitesAtouts_) {
            if (main_.total() > 1 && main_.premiereCarte().valeur() > 14) {
                nbAtoutsMajeursConsecutifs_ += main_.total();
            }
        }
        if (!trumps_.estVide()) {
            if (trumps_.premiereCarte().valeur() > 14) {
                nbAtoutsQuinzeAuVingtEtUn_++;
            } else if (trumps_.premiereCarte().valeur() > 6) {
                nbAtoutsSeptAuQuatorze_++;
            }
        }
        int nbTrumps_ = trumps_.total();
        for (int indiceCarte_ = CustList.SECOND_INDEX; indiceCarte_ < nbTrumps_; indiceCarte_++) {
            if (trumps_.carte(indiceCarte_).valeur() > 14) {
                nbAtoutsQuinzeAuVingtEtUn_++;
            } else if (trumps_.carte(indiceCarte_).valeur() > 6) {
                boolean continuer_ = false;
                for (HandTarot main_ : suitesAtouts_) {
                    if (main_.premiereCarte().valeur() <= 14) {
                        break;
                    }
                    if (main_.contient(trumps_.carte(
                            indiceCarte_))) {
                        nbAtoutsQuinzeAuVingtEtUn_++;
                        continuer_ = true;
                        break;
                    }
                }
                if (continuer_) {
                    continue;
                }
                nbAtoutsSeptAuQuatorze_++;
            } else {
                boolean continuer_ = false;
                for (HandTarot main_ : suitesAtouts_) {
                    if (main_.premiereCarte().valeur() <= 14) {
                        break;
                    }
                    if (main_.contient(trumps_.carte(
                            indiceCarte_))) {
                        nbAtoutsQuinzeAuVingtEtUn_++;
                        continuer_ = true;
                        break;
                    }
                }
                if (continuer_) {
                    continue;
                }
                for (HandTarot main_ : suitesAtouts_) {
                    if (main_.premiereCarte().valeur() <= 6) {
                        break;
                    }
                    if (main_.contient(trumps_.carte(
                            indiceCarte_))) {
                        nbAtoutsSeptAuQuatorze_++;
                        break;
                    }
                }
            }
        }
        int total_ = 0;
        int valeurVingtEtUnSeul_;
        int valeurExcuseSeule_;
        int valeurVingtEtUnExcuse_;
        int valeurPetitBout_;
        int valeurMariageRoiDame_;
        // roi - dame
        int valeurRoiSeul_;
        int valeurDameSeul_;
        int valeurCavalier_;
        int valeurValet_;
        int valeurCoupe_;
        int valeurLongue_;
        if (nombreJoueurs_ == 3) {
            if (atouts_ <= 7) {
                valeurPetitBout_ = 0;
            } else if (atouts_ <= 12) {
                valeurPetitBout_ = 2;
            } else {
                valeurPetitBout_ = 4;
            }
            if (atouts_ <= 12) {
                valeurAtoutMoyen_ = 1;
                valeurAtout_ = 3;
                valeurAtoutMajeur_ = 2;
                valeurVingtEtUnSeul_ = 4;
                valeurExcuseSeule_ = 2;
                valeurVingtEtUnExcuse_ = 10;
                valeurMariageRoiDame_ = 10;
                valeurRoiSeul_ = 6;
                valeurDameSeul_ = 4;
                valeurCavalier_ = 3;
                valeurValet_ = 1;
                valeurCoupe_ = 1;
                valeurLongue_ = 3;
                total_ += nbAtoutsMajeursConsecutifs_;
            } else {
                valeurAtoutMoyen_ = 2;
                valeurAtout_ = 4;
                valeurAtoutMajeur_ = 3;
                valeurVingtEtUnSeul_ = 6;
                valeurExcuseSeule_ = 3;
                valeurVingtEtUnExcuse_ = 14;
                valeurMariageRoiDame_ = 18;
                valeurRoiSeul_ = 8;
                valeurDameSeul_ = 7;
                valeurCavalier_ = 3;
                valeurValet_ = 2;
                valeurCoupe_ = 2;
                valeurLongue_ = 6;
                total_ += nbAtoutsMajeursConsecutifs_ * 2;
            }
        } else if (nombreJoueurs_ == 4) {
            if (atouts_ <= 6) {
                valeurPetitBout_ = 0;
            } else if (atouts_ <= 9) {
                valeurPetitBout_ = 3;
            } else {
                valeurPetitBout_ = 6;
            }
            valeurCavalier_ = 2;
            valeurValet_ = 1;
            if (atouts_ <= 9) {
                valeurAtout_ = 4;
                valeurAtoutMoyen_ = 2;
                valeurAtoutMajeur_ = 3;
                valeurVingtEtUnSeul_ = 6;
                valeurExcuseSeule_ = 3;
                valeurVingtEtUnExcuse_ = 12;
                valeurMariageRoiDame_ = 8;
                valeurRoiSeul_ = 5;
                valeurDameSeul_ = 3;
                valeurCoupe_ = 2;
                valeurLongue_ = 4;
                total_ += nbAtoutsMajeursConsecutifs_ * 2;
            } else {
                valeurAtout_ = 5;
                valeurAtoutMoyen_ = 3;
                valeurAtoutMajeur_ = 4;
                valeurVingtEtUnSeul_ = 8;
                valeurExcuseSeule_ = 4;
                valeurVingtEtUnExcuse_ = 16;
                valeurMariageRoiDame_ = 12;
                valeurRoiSeul_ = 6;
                valeurDameSeul_ = 4;
                valeurCoupe_ = 4;
                valeurLongue_ = 8;
                total_ += nbAtoutsMajeursConsecutifs_ * 4;
            }
        } else if (nombreJoueurs_ == 5) {
            if (atouts_ <= 5) {
                valeurPetitBout_ = 0;
            } else if (atouts_ <= 7) {
                valeurPetitBout_ = 3;
            } else {
                valeurPetitBout_ = 6;
            }
            valeurCavalier_ = 2;
            valeurValet_ = 1;
            if (atouts_ <= 7) {
                valeurAtout_ = 4;
                valeurAtoutMoyen_ = 2;
                valeurAtoutMajeur_ = 3;
                valeurVingtEtUnSeul_ = 6;
                valeurExcuseSeule_ = 3;
                valeurVingtEtUnExcuse_ = 12;
                valeurMariageRoiDame_ = 8;
                valeurRoiSeul_ = 5;
                valeurDameSeul_ = 3;
                valeurCoupe_ = 2;
                valeurLongue_ = 4;
                total_ += nbAtoutsMajeursConsecutifs_ * 2;
            } else {
                valeurAtout_ = 5;
                valeurAtoutMoyen_ = 3;
                valeurAtoutMajeur_ = 4;
                valeurVingtEtUnSeul_ = 8;
                valeurExcuseSeule_ = 4;
                valeurVingtEtUnExcuse_ = 16;
                valeurMariageRoiDame_ = 12;
                valeurRoiSeul_ = 6;
                valeurDameSeul_ = 4;
                valeurCoupe_ = 4;
                valeurLongue_ = 8;
                total_ += nbAtoutsMajeursConsecutifs_ * 4;
            }
        } else {
            /* 6 joueurs */
            if (atouts_ <= 4) {
                valeurPetitBout_ = 0;
            } else if (atouts_ <= 6) {
                valeurPetitBout_ = 5;
            } else {
                valeurPetitBout_ = 10;
            }
            valeurAtoutMoyen_ = 3;
            valeurCavalier_ = 2;
            valeurValet_ = 1;
            if (atouts_ <= 6) {
                valeurAtout_ = 5;
                valeurAtoutMajeur_ = 4;
                valeurVingtEtUnSeul_ = 8;
                valeurExcuseSeule_ = 5;
                valeurVingtEtUnExcuse_ = 16;
                valeurMariageRoiDame_ = 6;
                valeurRoiSeul_ = 4;
                valeurDameSeul_ = 2;
                valeurCoupe_ = 3;
                valeurLongue_ = 5;
                total_ += nbAtoutsMajeursConsecutifs_ * 3;
            } else {
                valeurAtout_ = 7;
                valeurAtoutMajeur_ = 7;
                valeurVingtEtUnSeul_ = 10;
                valeurExcuseSeule_ = 7;
                valeurVingtEtUnExcuse_ = 21;
                valeurMariageRoiDame_ = 9;
                valeurRoiSeul_ = 5;
                valeurDameSeul_ = 3;
                valeurCoupe_ = 7;
                valeurLongue_ = 11;
                total_ += nbAtoutsMajeursConsecutifs_ * 6;
            }
        }
        if (atouts_ == 0) {
            valeurCoupe_ = 0;
            valeurLongue_ = 0;
        }
        int nombreLimiteLongue_;
        int totalCouleur_=0;
        for(Suit c: couleursOrdinaires()) {
            totalCouleur_+= HandTarot.couleurComplete(c).total();
        }
        if(totalCouleur_ > 0) {
            totalCouleur_ /= couleursOrdinaires().size();
        }
        if (totalCouleur_ % nombreJoueurs_ == 0) {
            nombreLimiteLongue_ = totalCouleur_ / nombreJoueurs_ + 1;
        } else {
            nombreLimiteLongue_ = totalCouleur_ / nombreJoueurs_ + 2;
        }
        if (bouts_.contient(CardTarot.petit())) {
            total_ += valeurPetitBout_;
        }
        if (bouts_.contient(CardTarot.excuse())
                && bouts_.contient(CardTarot.vingtEtUn())) {
            total_ += valeurVingtEtUnExcuse_;
        } else if (bouts_.contient(CardTarot.excuse())) {
            total_ += valeurExcuseSeule_;
        } else if (bouts_.contient(CardTarot.vingtEtUn())) {
            total_ += valeurVingtEtUnSeul_;
        }
        total_ += valeurAtout_ * atouts_ + valeurAtoutMajeur_ * nbAtoutsQuinzeAuVingtEtUn_
                + valeurAtoutMoyen_ * nbAtoutsSeptAuQuatorze_;
        for (Suit couleur_ : couleursOrdinaires()) {
            HandTarot mt_ = couleurs_.getVal(couleur_);
            int roi_ = mt_.tailleRois();
            int dame_ = mt_.tailleDames();
            int cavalier_ = mt_.tailleCavaliers();
            int valet_ = mt_.tailleValets();
            if (roi_ + dame_ == 2) {
                total_ += valeurMariageRoiDame_;
            } else if (roi_ == 1) {
                total_ += valeurRoiSeul_;
            } else if (dame_ == 1) {
                total_ += valeurDameSeul_;
            }
            total_ += cavalier_ * valeurCavalier_;
            total_ += valet_ * valeurValet_;
            if (mt_.total() >= nombreLimiteLongue_) {
                total_ += valeurLongue_;
            }
            if (mt_.estVide()) {
                total_ += valeurCoupe_;
            }
        }
        int petite_;
        int garde_;
        if (nombreJoueurs_ == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
            petite_ = 90;
            garde_ = 140;
        } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getNombreJoueurs()) {
            if (rules.getRepartition().getAppel() == CallingCard.WITHOUT) {
                petite_ = 90;
                garde_ = 160;
            } else if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
                petite_ = 50;
                garde_ = 90;
            } else {
                petite_ = 60;
                garde_ = 110;
            }
        } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getNombreJoueurs()) {
            if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
                petite_ = 50;
                garde_ = 90;
            } else {
                petite_ = 90;
                garde_ = 150;
            }
        } else {
            if (rules.getRepartition() == DealingTarot.DEAL_1_VS_4) {
                petite_ = 80;
                garde_ = 130;
            } else {
                petite_ = 60;
                garde_ = 100;
            }
        }
        boolean sansAppel_ = rules.getRepartition().getAppel() == CallingCard.DEFINED
                || rules.getRepartition().getAppel() == CallingCard.WITHOUT;
        int nbCouleurs_ = couleursOrdinaires().size();
        if (total_ >= garde_) {
            if (nombreJoueurs_ == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
                if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs()) == nbCouleurs_) {
                    if (atouts_ >= 12) {
                        BidTarot e_ = getStrongBid(bouts_);
                        if(e_.isJouerDonne()) {
                            return e_;
                        }
                    }
                }
            } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getNombreJoueurs()) {
                if (sansAppel_) {
                    if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs()) == nbCouleurs_) {
                        if (atouts_ >= 9) {
                            BidTarot e_ = getStrongBid(bouts_);
                            if(e_.isJouerDonne()) {
                                return e_;
                            }
                        }
                    }
                } else if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs())
                        + nbCouleursPseudoMaitresses(couleurs_, cartesAppeler(numero_), nombreJoueurs_) == nbCouleurs_) {
                    if (atouts_ >= 9) {
                        BidTarot e_ = getStrongBid(bouts_);
                        if(e_.isJouerDonne()) {
                            return e_;
                        }
                    }
                }
            } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_2_CALL_KING.getNombreJoueurs()) {
                if (sansAppel_) {
                    if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs()) == nbCouleurs_) {
                        if (atouts_ >= 7) {
                            BidTarot e_ = getStrongBid(bouts_);
                            if(e_.isJouerDonne()) {
                                return e_;
                            }
                        }
                    }
                } else if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs())
                        + nbCouleursPseudoMaitresses(couleurs_, cartesAppeler(numero_), nombreJoueurs_) == nbCouleurs_) {
                    if (atouts_ >= 7) {
                        BidTarot e_ = getStrongBid(bouts_);
                        if(e_.isJouerDonne()) {
                            return e_;
                        }
                    }
                }
            } else {
                // nombreJoueurs == 6
                if (sansAppel_) {
                    if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs()) == nbCouleurs_) {
                        if (atouts_ >= 6) {
                            BidTarot e_ = getStrongBid(bouts_);
                            if(e_.isJouerDonne()) {
                                return e_;
                            }
                        }
                    }
                } else if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs())
                        + nbCouleursPseudoMaitresses(couleurs_, cartesAppeler(numero_), nombreJoueurs_) == nbCouleurs_) {
                    if (atouts_ >= 6) {
                        BidTarot e_ = getStrongBid(bouts_);
                        if(e_.isJouerDonne()) {
                            return e_;
                        }
                    }
                }
            }
        }
        BidTarot c_;
        if (total_ < petite_) {
            c_ = BidTarot.FOLD;
        } else if (total_ < garde_ && contratAccepte(BidTarot.TAKE)) {
            c_ = BidTarot.TAKE;
        } else {
            c_ = BidTarot.GUARD;
        }
        if (c_.estDemandable(bid)) {
            if (c_ == BidTarot.TAKE) {
                if (bids.size() == nombreJoueurs_ - 1) {
                    c_ = BidTarot.GUARD;
                }
            }
            return c_;
        }
        return BidTarot.FOLD;
    }

    BidTarot getStrongBid(HandTarot _bouts) {
        BidTarot e_ = BidTarot.FOLD;
        PlayingDog jeuChien_ = PlayingDog.WITHOUT;
        if (_bouts.total() >= 2) {
            jeuChien_ = PlayingDog.AGAINST;
        } else if (_bouts.contient(CardTarot.vingtEtUn())) {
            jeuChien_ = PlayingDog.AGAINST;
        }
        for(BidTarot e: allowedBids()) {
            if(e.getJeuChien() != jeuChien_) {
                continue;
            }
            if(e.estDemandable(e_)) {
                e_ = e;
            }
        }
        return e_;
    }

    /**
    Renvoie la carte a appeler
    @param _t
    @param numero
    */
    public void ajouterContrat(BidTarot _c, byte _t) {
        if (lastHasBid == -1) {
            lastHasBid = _t;
        } else if (lastHasBid == _t) {
            return;
        }
        lastHasBid = _t;
        bids.add(_c);
        if (_c.isJouerDonne()) {
            setContrat(_c);
            setPreneur(_t);
        }
    }

    public void setContrat(BidTarot _pcontrat) {
        bid = _pcontrat;
    }

    public BidTarot getContrat() {
        return bid;
    }

    public BidTarot contrat(int _i) {
        return bids.get(_i);
    }

    public EnumList<BidTarot> tousContrats() {
        return bids;
    }

    public int contrats() {
        return bids.size();
    }

    public boolean keepBidding() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (BidTarot e: allowedBids()) {
            if (!e.isJouerDonne()) {
                continue;
            }
            if (e.estDemandable(bid)) {
                return contrats() < nombreDeJoueurs_;
            }
        }
        return false;
    }
    public boolean keepPlayingCurrentTrick() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        return progressingTrick.total() < nombreDeJoueurs_;
    }
    public boolean keepPlayingCurrentGame() {
        for (byte p: orderedPlayers(starter)) {
            if (!getDistribution().main(p).estVide()) {
                return true;
            }
        }
        return false;
    }
    boolean maximumBid(BidTarot _enchere) {
        BidTarot e_ = BidTarot.FOLD;
        for(BidTarot e: allowedBids()) {
            if(e.estDemandable(e_)) {
                e_ = e;
            }
        }
        return e_.getForce() == _enchere.getForce();
    }

    public void setPreneur(byte _ppreneur) {
        taker = _ppreneur;
    }

    public byte getPreneur() {
        return taker;
    }

    boolean existePreneur() {
        return taker > -1;
    }

    public void initEquipeDetermineeSansPreneur() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte i = CustList.FIRST_INDEX; i < nombreDeJoueurs_; i++) {
            for (byte p: rules.getRepartition().getAppelesDetermines(i)) {
                faireConfiance(i, p);
            }
            faireConfiance(i, i);
        }
    }

    public void initEquipeDeterminee() {
        Numbers<Byte> attaquants_=rules.getRepartition().getAppelesDetermines(taker);
        calledPlayers = new Numbers<Byte>(attaquants_);
        attaquants_.add(taker);
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        Numbers<Byte> defenseurs_=autresJoueurs(attaquants_, nombreDeJoueurs_);
        for(byte j1_:attaquants_) {
            for(byte j2_:attaquants_) {
                if(j1_==j2_) {
                    continue;
                }
                faireConfiance(j1_, j2_);
            }
        }
        for(byte j1_:defenseurs_) {
            for(byte j2_:defenseurs_) {
                if(j1_==j2_) {
                    continue;
                }
                faireConfiance(j1_, j2_);
            }
        }
    }

    public void initDefense() {
        Numbers<Byte> defenseurs_=new Numbers<Byte>();
        byte indice_=taker;
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX;joueur_<nombreDeJoueurs_;joueur_++) {
            if(joueur_==indice_) {
                continue;
            }
            defenseurs_.add(joueur_);
        }
        for(byte j1_:defenseurs_) {
            for(byte j2_:defenseurs_) {
                if(j1_==j2_) {
                    continue;
                }
                faireConfiance(j1_, j2_);
            }
        }
    }
    public void appelApresEcart() {
        ajouterCartes(taker,derniereMain());
        CallDiscard appel_ = strategieAppelApresEcart(false);
        if(!appel_.getCarteAppelee().estVide()) {
            setCarteAppelee(appel_.getCarteAppelee());
            initConfianceAppele();
        }
        setPliEnCours(false);
        for(CardTarot ct_:appel_.getEcartAFaire()) {
            ajouterUneCarteDansPliEnCours(ct_);
        }
        ajouterPliAttaque();
        supprimerCartes(taker,appel_.getEcartAFaire());
        if(appel_.isChelem()) {
            ajouterChelem(taker, true);
            setEntameur(taker);
        } else {
            ajouterChelem(taker, false);
        }
    }
    //pour le conseil lorsqu'aucune carte n'est ecartee
    public CallDiscard strategieAppelApresEcart(boolean _removeDog) {
        reason = new StringBuilder();
        CallDiscard appelEcart_ = new CallDiscard();
        HandTarot mainPreneur_ = getDistribution().main(taker);
        EqList<HandTarot> cartesAppelablesFinales_ = new EqList<HandTarot>();
        cartesAppelablesFinales_.add(new HandTarot());
        EqList<HandTarot> cartesAppelables_ = new EqList<HandTarot>();
        int nbAppeles_ = rules.getRepartition().getNbAppeles();
        HandTarot cartesAppeler_ = callableCards();
        cartesAppeler_.supprimerCartes(mainPreneur_);
        if (_removeDog) {
            cartesAppeler_.supprimerCartes(getPliEnCours().getCartes());
        }
        if (nbAppeles_ > 0) {
            for(HandTarot m: cartesAppelablesFinales_) {
                for(CardTarot c:cartesAppeler_) {
                    if(m.contient(c)) {
                        continue;
                    }
                    HandTarot m2_ = new HandTarot();
                    m2_.ajouterCartes(m);
                    m2_.ajouter(c);
                    cartesAppelables_.add(m2_);
                }
            }
            if(!cartesAppelables_.isEmpty()) {
                cartesAppelablesFinales_=new EqList<HandTarot>(cartesAppelables_);
            }
        }
        EqList<HandTarot> mainPossibles_ = new EqList<HandTarot>();
        for(HandTarot m: cartesAppelablesFinales_) {
            boolean existeMainSimilaire_ = false;
            for(HandTarot m2_: mainPossibles_) {
                if(!m2_.contientCartes(m)) {
                    continue;
                }
                if(!m.contientCartes(m2_)) {
                    continue;
                }
                existeMainSimilaire_ = true;
            }
            if(existeMainSimilaire_) {
                continue;
            }
            mainPossibles_.add(m);
        }
        for(HandTarot carte_: mainPossibles_) {
            HandTarot copieMainPreneur_ = new HandTarot();
            copieMainPreneur_.ajouterCartes(mainPreneur_);
            if (_removeDog) {
                copieMainPreneur_.ajouterCartes(getPliEnCours().getCartes());
            }
            EnumList<Suit> couleursNonAppelees_ = new EnumList<Suit>();
            for (Suit couleur_ : couleursOrdinaires()) {
                if(carte_.tailleCouleur(couleur_) == 0) {
                    couleursNonAppelees_.add(couleur_);
                }
            }
            HandTarot ecart_ = discarding(true,carte_,couleursNonAppelees_);
            copieMainPreneur_.supprimerCartes(ecart_);
            boolean annoncer_ = annoncerUnChelem(copieMainPreneur_);
            if(annoncer_) {
                appelEcart_.setCarteAppelee(carte_);
                appelEcart_.setEcartAFaire(ecart_);
                appelEcart_.setChelem(true);
                return appelEcart_;
            }
        }
        HandTarot hand_ = new HandTarot();
        hand_.ajouterCartes(mainPreneur_);
        if (_removeDog) {
            hand_.ajouterCartes(getPliEnCours().getCartes());
        }
        cartesAppeler_ = strategieAppel(hand_);
        EnumList<Suit> couleursNonAppelees_ = new EnumList<Suit>();
        for (Suit couleur_ : couleursOrdinaires()) {
            if(cartesAppeler_.tailleCouleur(couleur_) == 0) {
                couleursNonAppelees_.add(couleur_);
            }
        }
        HandTarot ecart_ = discarding(!cartesAppeler_.estVide(),cartesAppeler_,couleursNonAppelees_);
        appelEcart_.setCarteAppelee(cartesAppeler_);
        appelEcart_.setEcartAFaire(ecart_);
        return appelEcart_;
    }
    /** Appele pour un appel de carte pour que le preneur ait un partenaire */
    public HandTarot callableCards() {
        return cartesAppeler(taker);
    }
    private HandTarot cartesAppeler(byte _numero) {
        HandTarot main_ = new HandTarot();
        if (getRegles().getRepartition().getAppel() == CallingCard.KING) {
            int nbAppeles_ = getRegles().getRepartition().getNbAppeles();
            HandTarot mainPreneur_ = getDistribution().main(_numero);
            if (mainPreneur_.tailleRois() < HandTarot.charCards(CardChar.KING).total()) {
                main_.ajouterCartes(HandTarot.charCards(CardChar.KING));
                if (mainPreneur_.tailleRois() + nbAppeles_ > HandTarot.charCards(CardChar.KING).total()) {
                    //nbAppeles > 0
                    if (mainPreneur_.tailleDames() < HandTarot.charCards(CardChar.QUEEN).total()) {
                        main_.ajouterCartes(HandTarot.charCards(CardChar.QUEEN));
                        if (mainPreneur_.tailleDames() + nbAppeles_ > HandTarot.charCards(CardChar.QUEEN).total()) {
                            if (mainPreneur_.tailleCavaliers() < HandTarot.charCards(CardChar.KNIGHT).total()) {
                                main_.ajouterCartes(HandTarot.charCards(CardChar.KNIGHT));
                                if (mainPreneur_.tailleCavaliers() + nbAppeles_ > HandTarot.charCards(CardChar.KNIGHT).total()) {
                                    if (mainPreneur_.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                        main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                        if (mainPreneur_.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                            main_.ajouter(CardTarot.petit());
                                            main_.ajouter(CardTarot.vingtEtUn());
                                            main_.ajouter(CardTarot.excuse());
                                        }
                                    } else {
                                        main_.ajouter(CardTarot.petit());
                                        main_.ajouter(CardTarot.vingtEtUn());
                                        main_.ajouter(CardTarot.excuse());
                                    }
                                }
                            } else {
                                if (mainPreneur_.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                    if (mainPreneur_.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                        main_.ajouter(CardTarot.petit());
                                        main_.ajouter(CardTarot.vingtEtUn());
                                        main_.ajouter(CardTarot.excuse());
                                    }
                                } else {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            }
                        }
                    } else {
                        if (mainPreneur_.tailleCavaliers() < HandTarot.charCards(CardChar.KNIGHT).total()) {
                            main_.ajouterCartes(HandTarot.charCards(CardChar.KNIGHT));
                            if (mainPreneur_.tailleCavaliers() + nbAppeles_ > HandTarot.charCards(CardChar.KNIGHT).total()) {
                                if (mainPreneur_.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                    if (mainPreneur_.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                        main_.ajouter(CardTarot.petit());
                                        main_.ajouter(CardTarot.vingtEtUn());
                                        main_.ajouter(CardTarot.excuse());
                                    }
                                } else {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            }
                        } else {
                            if (mainPreneur_.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                if (mainPreneur_.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            } else {
                                main_.ajouter(CardTarot.petit());
                                main_.ajouter(CardTarot.vingtEtUn());
                                main_.ajouter(CardTarot.excuse());
                            }
                        }
                    }
                }
            } else {
                if (mainPreneur_.tailleDames() < HandTarot.charCards(CardChar.QUEEN).total()) {
                    main_.ajouterCartes(HandTarot.charCards(CardChar.QUEEN));
                    if (mainPreneur_.tailleDames() + nbAppeles_ > HandTarot.charCards(CardChar.QUEEN).total()) {
                        if (mainPreneur_.tailleCavaliers() < HandTarot.charCards(CardChar.KNIGHT).total()) {
                            main_.ajouterCartes(HandTarot.charCards(CardChar.KNIGHT));
                            if (mainPreneur_.tailleCavaliers() + nbAppeles_ > HandTarot.charCards(CardChar.KNIGHT).total()) {
                                if (mainPreneur_.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                    if (mainPreneur_.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                        main_.ajouter(CardTarot.petit());
                                        main_.ajouter(CardTarot.vingtEtUn());
                                        main_.ajouter(CardTarot.excuse());
                                    }
                                } else {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            }
                        } else {
                            if (mainPreneur_.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                if (mainPreneur_.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            } else {
                                main_.ajouter(CardTarot.petit());
                                main_.ajouter(CardTarot.vingtEtUn());
                                main_.ajouter(CardTarot.excuse());
                            }
                        }
                    }
                } else {
                    if (mainPreneur_.tailleCavaliers() < HandTarot.charCards(CardChar.KNIGHT).total()) {
                        main_.ajouterCartes(HandTarot.charCards(CardChar.KNIGHT));
                        if (mainPreneur_.tailleCavaliers() + nbAppeles_ > HandTarot.charCards(CardChar.KNIGHT).total()) {
                            if (mainPreneur_.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                                main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                                if (mainPreneur_.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                    main_.ajouter(CardTarot.petit());
                                    main_.ajouter(CardTarot.vingtEtUn());
                                    main_.ajouter(CardTarot.excuse());
                                }
                            } else {
                                main_.ajouter(CardTarot.petit());
                                main_.ajouter(CardTarot.vingtEtUn());
                                main_.ajouter(CardTarot.excuse());
                            }
                        }
                    } else {
                        if (mainPreneur_.tailleValets() < HandTarot.charCards(CardChar.JACK).total()) {
                            main_.ajouterCartes(HandTarot.charCards(CardChar.JACK));
                            if (mainPreneur_.tailleValets() + nbAppeles_ > HandTarot.charCards(CardChar.JACK).total()) {
                                main_.ajouter(CardTarot.petit());
                                main_.ajouter(CardTarot.vingtEtUn());
                                main_.ajouter(CardTarot.excuse());
                            }
                        } else {
                            main_.ajouter(CardTarot.petit());
                            main_.ajouter(CardTarot.vingtEtUn());
                            main_.ajouter(CardTarot.excuse());
                        }
                    }
                }
            }
        } else if (getRegles().getRepartition().getAppel() == CallingCard.CHARACTER_CARD) {
            main_.ajouterCartes(HandTarot.figuesCouleurs());
            main_.ajouter(CardTarot.petit());
            main_.ajouter(CardTarot.vingtEtUn());
            main_.ajouter(CardTarot.excuse());
        }
        return main_;
    }

    public void intelligenceArtificielleAppel() {
        HandTarot cartesAppeler_ = strategieAppel();
        if(cartesAppeler_.estVide()) {
            return;
        }
        setCarteAppelee(cartesAppeler_);
        initConfianceAppele();
    }

    public boolean isCallingState() {
        if (!bid.isJouerDonne()) {
            return false;
        }
        return getCarteAppelee().estVide() && !callableCards().estVide();
    }
    boolean noCalledCardsInHand() {
        for (byte p :orderedPlayers(taker)) {
            for (CardTarot c: calledCards) {
                if (getDistribution().main(p).contient(c)) {
                    return false;
                }
            }
        }
        return true;
    }
    public HandTarot strategieAppel() {
        HandTarot mainPreneur_ = getDistribution().main(taker);
        reason = new StringBuilder();
        return strategieAppel(mainPreneur_);
    }

    private HandTarot strategieAppel(HandTarot _mainPreneur) {

        byte joueurs_ = getNombreDeJoueurs();
        boolean figure_ = rules.getRepartition().getAppel() == CallingCard.CHARACTER_CARD;
        EnumMap<Suit,HandTarot> repartition_ = _mainPreneur.couleurs();
        if (estUnJeuDeChelemSur(repartition_, new HandTarot().couleurs())) {
            HandTarot figuresPreneur_ = _mainPreneur.figures();
            HandTarot cartesAppelerChelem_ = new HandTarot();
            for(CardTarot c: callableCards()) {
                if(figuresPreneur_.contient(c)) {
                    cartesAppelerChelem_.ajouter(c);
                    return cartesAppelerChelem_;
                }
            }
            HandTarot h_ = new HandTarot();
            h_.ajouter(callableCards().premiereCarte());
            return h_;
        }
        HandTarot rois_ = _mainPreneur.figuresMain(CardChar.KING);
        HandTarot roisAppeler_ = callableCards().figuresMain(CardChar.KING);
        if (rois_.total() < roisAppeler_.total()) {
            //0 <= rois.total() && rois.total() < roisAppeler.total()
            // ==> 0 < roisAppeler.total() ==> !roisAppeler.estVide()
            //il manque au moins un roi pour le preneur
            //il faut appeler un roi
            Suit couleurRoiAppele_;
            int nbAtoutLimite_ = _mainPreneur.total() / 2;
            int nbAtouts_ = _mainPreneur.couleur(couleurAtout()).total();
            EnumList<Suit> couleurs_;
            EnumList<Suit> couleursAppelables_ = couleursNonAtoutNonVides(roisAppeler_,couleursOrdinaires());
            if (nbAtouts_ <= nbAtoutLimite_) {
                couleurs_ = couleursNonAtoutAyantNbCartesInfEg(_mainPreneur,
                        couleursAppelables_, 3);
                EnumList<Suit> couleursSansRoi_ = couleursSansCartes(_mainPreneur,
                        rois_,
                        couleurs_);
                if (!couleursSansRoi_.isEmpty()) {
                    // il existe une couleur ayant moins de trois cartes sans roi
                    EnumList<Suit> couleursAvecFigues_ = couleursAvecFigures(
                            _mainPreneur, couleursSansRoi_);
                    if (!couleursAvecFigues_.isEmpty()) {
                        couleursAvecFigues_ = couleursPLonguePHaute(
                                _mainPreneur, couleursAvecFigues_);
                        couleurRoiAppele_ = couleursAvecFigues_.first();
                    } else {
                        couleursSansRoi_ = couleursPLongueMHaute(_mainPreneur,
                                couleursSansRoi_);
                        couleurRoiAppele_ = couleursSansRoi_.first();
                    }
                } else {
                    couleursSansRoi_ = couleursSansCartes(_mainPreneur,
                            rois_,
                            couleursAppelables_);
                    couleursSansRoi_ = couleursNonAtoutNonVides(_mainPreneur, couleursSansRoi_);
                    if (!couleursSansRoi_.isEmpty()) {
                        EnumList<Suit> couleursAvecFigues_ = couleursAvecFigures(
                                _mainPreneur, couleursSansRoi_);
                        if (!couleursAvecFigues_.isEmpty()) {
                            couleursAvecFigues_ = couleursPHauteMLongue(
                                    _mainPreneur, couleursAvecFigues_);
                            couleurRoiAppele_ = couleursAvecFigues_.first();
                        } else {
                            couleursSansRoi_ = couleursMLongueMHaute(
                                    _mainPreneur, couleursSansRoi_);
                            couleurRoiAppele_ = couleursSansRoi_.first();
                        }
                    } else {
                        //couleurs avec roi ou couleurs vides et pas tous les rois => au moins une couleur vide
                        couleursSansRoi_ = couleursNonAtoutAyantNbCartesInfEg(
                                _mainPreneur, couleursAppelables_, 0);
                        couleurRoiAppele_ = couleursSansRoi_.first();
                    }
                }
            } else {
                couleurs_ = couleursNonAtoutNonVides(_mainPreneur, couleursAppelables_);
                EnumList<Suit> couleursSansRoi_ = couleursSansCartes(_mainPreneur,
                        rois_,
                        couleurs_);
                if (!couleursSansRoi_.isEmpty()) {
                    couleursSansRoi_ = couleursPLonguePHaute(_mainPreneur,
                            couleursSansRoi_);
                    couleurRoiAppele_ = couleursSansRoi_.first();
                } else {
                    //couleurs avec roi ou couleurs vides et pas tous les rois => au moins une couleur vide
                    couleursSansRoi_ = couleursNonAtoutAyantNbCartesInfEg(
                            _mainPreneur, couleursAppelables_, 0);
                    couleurRoiAppele_ = couleursSansRoi_.first();
                }
            }
            return HandTarot.figureCouleur(couleurRoiAppele_, CardChar.KING);
        }
        Suit couleur_ = couleurAappeler(callableCards(), _mainPreneur);
        HandTarot carteChoisie_ = valeurAappeler(joueurs_, figure_, couleur_, repartition_);
        if(!carteChoisie_.estVide()) {
            return carteChoisie_;
        }
        return callableCards();

    }

    /**
    Retourne la couleur a appeler
    @param reason
    @param repartition
    */
    private static Suit couleurAappeler(
            HandTarot _cartesAppeler, HandTarot _mainPreneur) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        couleurs_.add(CardTarot.EXCUSE.couleur());
        couleurs_.add(Suit.TRUMP);
        for (Suit couleur_ :couleursOrdinaires()) {
            couleurs_.add(couleur_);
        }
        couleurs_.sortElts(new CalledSuitComparator(_cartesAppeler,_mainPreneur));
        return couleurs_.first();
    }

    private static HandTarot valeurAappeler(byte _nombreDeJoueurs, boolean _figure,
            Suit _couleurChoisie,
            EnumMap<Suit,HandTarot> _repartition) {
        EnumList<CardChar> ordreFigureAppeler_ = new EnumList<CardChar>();
        HandTarot main_ = HandTarot.reunion(_repartition);
        CardChar roi_ = CardChar.KING;
        ordreFigureAppeler_.add(roi_);
        CardChar dame_ = CardChar.QUEEN;
        ordreFigureAppeler_.add(dame_);
        CardChar cavalier_ = CardChar.KNIGHT;
        ordreFigureAppeler_.add(cavalier_);
        CardChar valet_ = CardChar.JACK;
        ordreFigureAppeler_.add(valet_);
        if (!_figure) {
            for(CardChar f: ordreFigureAppeler_) {
                HandTarot figures_ = main_.figuresMain(f);
                if(figures_.total() < HandTarot.charCards(f).total()) {
                    return HandTarot.figureCouleur(_couleurChoisie, f);
                }
            }
            HandTarot atoutAChoisir_ = new HandTarot();
            if (!_repartition.getVal(couleurAtout()).contient(CardTarot.vingtEtUn())) {
                atoutAChoisir_.ajouter(CardTarot.vingtEtUn());
            } else if (_repartition.getVal(CardTarot.EXCUSE.couleur()).estVide()) {
                atoutAChoisir_.ajouter(CardTarot.excuse());
            } else {
                atoutAChoisir_.ajouter(CardTarot.petit());
            }
            return atoutAChoisir_;
        }
        int nombreDeCoupes_ = nombreDeCoupesFranches(_repartition);
        for(CardChar f: ordreFigureAppeler_) {
            HandTarot figures_ = main_.figuresMain(f);
            if(figures_.total() != HandTarot.charCards(f).total() - nombreDeCoupes_) {
                return HandTarot.figureCouleur(_couleurChoisie, f);
            }
        }
        if (_nombreDeJoueurs == 4) {
            HandTarot atoutAChoisir_ = new HandTarot();
            if (!_repartition.getVal(couleurAtout()).contient(CardTarot.vingtEtUn())) {
                atoutAChoisir_.ajouter(CardTarot.vingtEtUn());
                return atoutAChoisir_;
            }
            if (_repartition.getVal(CardTarot.EXCUSE.couleur()).estVide()) {
                atoutAChoisir_.ajouter(CardTarot.excuse());
                return atoutAChoisir_;
            }
            if (!_repartition.getVal(couleurAtout()).contient(CardTarot.petit())) {
                atoutAChoisir_.ajouter(CardTarot.petit());
                return atoutAChoisir_;
            }
        }
        for(CardChar f: ordreFigureAppeler_) {
            HandTarot figures_ = main_.figuresMain(f);
            if(figures_.total() < HandTarot.charCards(f).total()) {
                return HandTarot.figureCouleur(_couleurChoisie, f);
            }
        }
        return new HandTarot();
    }

    private boolean appelAFaire() {
        CallingCard appel_ = rules.getRepartition().getAppel();
        if (appel_ == CallingCard.CHARACTER_CARD || appel_ == CallingCard.KING) {
            return !callableCards().estVide();
        }
        return false;
    }
    public void setCarteAppelee(HandTarot _c) {
        calledCards = new HandTarot();
        calledCards.ajouterCartes(_c);
    }

    public boolean existeCarteAppelee() {
        return !calledCards.estVide();
    }

    private boolean existeAppele() {
        return !calledPlayers.isEmpty();
    }

    public HandTarot getCarteAppelee() {
        return getCalledCards();
    }

    /**
    Appele pour determiner le joueur ayant la carte appelee et initialiser sa
    confiance envers les autres joueurs
    */
    Numbers<Byte> joueursAyantCarteAppelee() {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte b = CustList.FIRST_INDEX; b < nombreDeJoueurs_; b++) {
            for(CardTarot c: calledCards) {
                if (deal.main(b).contient(c)) {
                    joueurs_.add(b);
                    break;
                }
            }
        }
        return joueurs_;
    }
    public void initConfianceAppeleUtilisateur(HandTarot _carteAppelee) {
        setCarteAppelee(_carteAppelee);
        initConfianceAppele();
    }
    void initConfianceAppele() {
        calledPlayers = joueursAyantCarteAppelee();
        for(byte a: calledPlayers) {
            if(taker!=a) {
                faireConfiance(a,taker);
            }
        }
        if (getContrat().getJeuChien() == PlayingDog.WITH && noCalledCardsInHand()) {
            initDefense();
        }
    }

    public Numbers<Byte> getAppele() {
        return calledPlayers;
    }

    /**
    Status des joueurs pour la partie:<br/>
    Les status sont preneur, appele, defenseur.<br/>
    Le preneur fait equipe avec l'eventuel appele<br/>
    Les defenseurs font equipes entre eux.
    */
    public Status statutDe(byte _numero) {
        if (_numero == taker) {
            return Status.TAKER;
        }
        if (calledPlayers.containsObj(_numero)) {
            return Status.CALLED_PLAYER;
        }
        return Status.DEFENDER;
    }

    public ReasonDiscard autoriseEcartDe(CardTarot _c) {
        HandTarot m = getDistribution().main(getPreneur());
        if(cardsToBeDiscarded.total() >= getDistribution()
                .derniereMain().total()) {
            return ReasonDiscard.TOO_MUCH;
        }
        cardsToBeDiscarded.ajouter(_c);
        boolean allowed_ = getCartesEcartables(getDistribution()
                .derniereMain().total() - (cardsToBeDiscarded.total() - 1),
                m.couleurs()).contient(_c);
        if (!allowed_) {
            cardsToBeDiscarded.jouer(_c);
        }
        if (allowed_) {
            return ReasonDiscard.NOTHING;
        }
        return ReasonDiscard.FORBIDDEN;
    }
    HandTarot getCartesEcartables(int _nombreCartesChien,
                                  EnumMap<Suit, HandTarot> _repartition) {
        HandTarot cartesEcartables_ = new HandTarot();
        int atoutsExcuse_ = atoutsAvecExcuse(_repartition);
        int total_ = atoutsExcuse_;
        int rois_ = 0;
        for (Suit couleur_ : couleursOrdinaires()) {
            HandTarot main_ = _repartition.getVal(couleur_);
            if (!main_.estVide()) {
                total_ += main_.total();
                for (CardTarot c: main_) {
                    if (c.getNomFigure() == CardChar.KING) {
                        rois_++;
                        continue;
                    }
                    cartesEcartables_.ajouter(c);
                }
            }
        }
        if (total_ - rois_ - atoutsExcuse_ < _nombreCartesChien) {
            /*
        S il y a
        moins de
        cartes de
        couleur
        autres que
        des rois que
        de cartes a
        ecarter alors
        il existe
        forcement au
        moins un
        atout dans la
        main
        */
            HandTarot atouts_ = _repartition.getVal(couleurAtout());
            /*
            atouts est trie dans le sens
            decroissant des numeros
            */
            for (CardTarot c: atouts_) {
                if (CardTarot.eq(c, CardTarot.vingtEtUn())) {
                    /*
                    Pas de 21 d atout
                    dans la main
                    */
                    continue;
                }
                if (CardTarot.eq(c, CardTarot.petit())) {
                    /*
                    Pas de Petit d atout dans
                    la main
                    */
                    continue;
                }
                cartesEcartables_.ajouter(c);
            }
        }
        return cartesEcartables_;
    }

    public void ecarter(boolean _createTrick) {
        if (!_createTrick) {
            ajouterCartes(taker,getPliEnCours().getCartes());
            //On ajoute les cartes du chien au preneur pour en ecarter d'autres
            HandTarot mt_=strategieEcart();
            //Le preneur ecarte les cartes qu'il veut
            supprimerCartes(taker,mt_);

            ajouterChelem(taker, annoncerUnChelem(taker));
            if(chelemAnnonce(taker)) {
                setEntameur(taker);
            }
            for(CardTarot ct_:mt_) {
                ajouterUneCarteDansPliEnCours(ct_);
            }
            ajouterPliAttaque();
            return;
        }
        ajouterCartes(taker,derniereMain());
        //On ajoute les cartes du chien au preneur pour en ecarter d'autres
        HandTarot mt_=strategieEcart();
        //Le preneur ecarte les cartes qu'il veut
        supprimerCartes(taker,mt_);

        ajouterChelem(taker, annoncerUnChelem(taker));
        if(chelemAnnonce(taker)) {
            setEntameur(taker);
        }

        setPliEnCours(false);
        for(CardTarot ct_:mt_) {
            ajouterUneCarteDansPliEnCours(ct_);
        }
        ajouterPliAttaque();
    }
    public HandTarot strategieEcart() {
        EnumList<Suit> couleursNonAppelees_ = new EnumList<Suit>();
        if (!autoAppelPourEcart()) {
            for (Suit couleur_ : couleursOrdinaires()) {
                if (calledCards.tailleCouleur(couleur_) != 0) {
                    continue;
                }
                couleursNonAppelees_.add(couleur_);
            }
        } else {
            couleursNonAppelees_ = couleursOrdinaires();
        }
        return discarding(existeCarteAppelee(),calledCards,couleursNonAppelees_);
    }
    private HandTarot discarding(boolean _carteAppeleeExistante,HandTarot _carteAppelee,
            EnumList<Suit> _couleursNonAppelees) {
        HandTarot handStrat_ = strategieEcart(_carteAppeleeExistante,_carteAppelee,_couleursNonAppelees);
        cardsToBeDiscarded = new HandTarot();
        return handStrat_;
    }

    private HandTarot strategieEcart(boolean _carteAppeleeExistante,HandTarot _carteAppelee,
            EnumList<Suit> _couleursNonAppelees) {
        HandTarot mainPreneur_ = getDistribution().main(taker);
        EnumMap<Suit,HandTarot> repartition_ = mainPreneur_.couleurs();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        int tailleChien_ = getDistribution().derniereMain().total();
        cardsToBeDiscarded = new HandTarot();
        HandTarot ecartables_ = getCartesEcartables(tailleChien_, repartition_);
        EnumMap<Suit,HandTarot> repEcartables_ = ecartables_.couleurs();
        HandTarot ecart_ = new HandTarot();
        Suit couleurAtout_ = couleurAtout();
        if (!repEcartables_.getVal(couleurAtout_).estVide()) {
            // Si des atouts sont a ecarter
            for (Suit couleur_ : couleursOrdinaires()) {
                ecart_.ajouterCartes(repEcartables_.getVal(couleur_));
            }
            int reste_ = tailleChien_ - ecart_.total();
            HandTarot atoutsEcartables_ = repEcartables_.getVal(couleurAtout_);
            int nbAtoutsEcartables_ = atoutsEcartables_.total();
            for (byte carte_ = CustList.FIRST_INDEX; carte_ < reste_; carte_++) {
                ecart_.ajouter(atoutsEcartables_.carte(nbAtoutsEcartables_ - 1
                        - carte_));
            }
            return ecart_;
        }
        if (ecartables_.total() == tailleChien_) {
            return ecartables_;
        }
        if(nombreDeCoupesFranches(repartition_) == 0) {
            if(!maitreAtoutPourChelem(repartition_,nombreJoueurs_)) {
                //si le preneur n'est pas maitre de l'atout au debut du jeu
                if(mainPreneur_.contient(CardTarot.petit())) {
                    EnumList<Suit> couleursEntieresEcartables_ = couleursTotalEcartables(
                            mainPreneur_, tailleChien_, ecart_,
                            couleursOrdinaires());
                    if(!couleursEntieresEcartables_.isEmpty()) {
                        //Sauver le PETIT sur une coupe franche courte
                        couleursEntieresEcartables_ =
                                couleursAvecFigures(mainPreneur_, couleursEntieresEcartables_);
                        couleursEntieresEcartables_ =
                                intersectionCouleurs(couleursEntieresEcartables_, _couleursNonAppelees);
                        if(!couleursEntieresEcartables_.isEmpty()) {
                            //couleurs non appelees entierement ecartables avec figures
                            couleursEntieresEcartables_ = couleursLesPlusCourtes(mainPreneur_, couleursEntieresEcartables_);
                            couleursEntieresEcartables_ = couleursLesPlusHautes(mainPreneur_, couleursEntieresEcartables_);
                            ecart_.ajouterCartes(mainPreneur_.couleur(couleursEntieresEcartables_.first()));
                        } else {
                            couleursEntieresEcartables_ = couleursTotalEcartables(
                                    mainPreneur_, tailleChien_, ecart_,
                                    couleursOrdinaires());
                            couleursEntieresEcartables_ =
                                    intersectionCouleurs(couleursEntieresEcartables_, _couleursNonAppelees);
                            if(!couleursEntieresEcartables_.isEmpty()) {
                                //couleurs non appelees entierement ecartables
                                couleursEntieresEcartables_ = couleursLesPlusCourtes(mainPreneur_, couleursEntieresEcartables_);
                                couleursEntieresEcartables_ = couleursLesPlusBasses(mainPreneur_, couleursEntieresEcartables_);
                                ecart_.ajouterCartes(mainPreneur_.couleur(couleursEntieresEcartables_.first()));
                            } else {
                                couleursEntieresEcartables_ = couleursTotalEcartables(
                                        mainPreneur_, tailleChien_, ecart_,
                                        couleursOrdinaires());
                                couleursEntieresEcartables_ = couleursLesPlusCourtes(mainPreneur_, couleursEntieresEcartables_);
                                couleursEntieresEcartables_ = couleursLesPlusHautes(mainPreneur_, couleursEntieresEcartables_);
                                ecart_.ajouterCartes(mainPreneur_.couleur(couleursEntieresEcartables_.first()));
                            }
                        }
                    }
                }
            }
        }
        EnumMap<Suit,HandTarot> cartesMaitresses_ = cartesMaitressesDebutPartie(repartition_);
        if(maitreAtoutPourChelem(repartition_,nombreJoueurs_)) {
            HandTarot carteAppelee_ = new HandTarot();
            HandTarot cartesPseudosMaitres_ = new HandTarot();
            int nbCartesMaitresses_ = 0;
            int nbCartesCouleurs_ = 0;
            if (_carteAppeleeExistante) {
                EnumList<Suit> couleursAppelees_ = new EnumList<Suit>();
                for(CardTarot c: _carteAppelee) {
                    if(couleursAppelees_.containsObj(c.couleur())) {
                        continue;
                    }
                    couleursAppelees_.add(c.couleur());
                }
                carteAppelee_.ajouterCartes(_carteAppelee);
                for (Suit couleur_ : couleursAppelees_) {
                    cartesPseudosMaitres_.ajouterCartes(cartesPseudoMaitresses(repartition_,
                            carteAppelee_, new HandTarot().couleurs()).getVal(couleur_));
                }
                for (Suit couleur_ : couleursOrdinaires()) {
                    HandTarot main_ = cartesMaitresses_.getVal(couleur_);
                    if (main_.estVide()) {
                        continue;
                    }
                    if (!couleursAppelees_.containsObj(couleur_)) {
                        nbCartesMaitresses_ += cartesPseudosMaitres_.total();
                    } else {
                        nbCartesMaitresses_ += main_.total();
                    }
                }
            } else {
                for (HandTarot main_ : cartesMaitresses_.values()) {
                    nbCartesMaitresses_ += main_.total();
                }
            }
            for (Suit couleur_ : couleursOrdinaires()) {
                nbCartesCouleurs_ += repartition_.getVal(couleur_).total();
            }
            if (nbCartesMaitresses_ + tailleChien_ >= nbCartesCouleurs_) {
                HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(
                        cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
                for (CardTarot c: cartesNonMaitresses_) {
                    if (!mainPreneur_.contient(c)) {
                        continue;
                    }
                    ecart_.ajouter(c);
                }
                if (ecart_.total() == tailleChien_) {
                    return ecart_;
                }
                //cartesPseudosMaitres est suppose etre trie decroissant
                for (CardTarot carte_ : cartesPseudosMaitres_) {
                    if (ecart_.total() == tailleChien_) {
                        return ecart_;
                    }
                    if (repEcartables_.getVal(carte_.couleur()).contient(carte_)) {
                        ecart_.ajouter(carte_);
                    }
                }
                for (HandTarot main_ : cartesMaitresses_.values()) {
                    for (CardTarot carte_ : main_) {
                        if (ecart_.total() == tailleChien_) {
                            return ecart_;
                        }
                        if (repEcartables_.getVal(carte_.couleur()).contient(carte_)) {
                            ecart_.ajouter(carte_);
                        }
                    }
                }
            } else {
                EnumList<Suit> couleurs_ = couleursSansRoi(mainPreneur_, couleursOrdinaires());
                couleurs_ = couleursAvecFigures(mainPreneur_, couleurs_);
                for(EnumList<Suit> suits_: couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
                    HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(
                            cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
                    for(Suit couleur_: suits_) {
                        cartesNonMaitresses_ = cartesNonMaitresses_.charCardsBySuit(couleur_);
                        cartesNonMaitresses_.trierParForceEnCours(couleur_);
                        for(CardTarot carte_: cartesNonMaitresses_) {
                            if (!mainPreneur_.contient(carte_)) {
                                continue;
                            }
                            if (ecart_.total() == tailleChien_) {
                                return ecart_;
                            }
                            ecart_.ajouter(carte_);
                        }
                    }

                }
                couleurs_ = couleursSansRoi(mainPreneur_, couleursOrdinaires());
                for(EnumList<Suit> suits_: couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
                    HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(
                            cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
                    for(Suit couleur_: suits_) {
                        cartesNonMaitresses_ = cartesNonMaitresses_.cartesBasses(couleur_);
                        cartesNonMaitresses_.trierParForceEcart(couleur_);
                        for(CardTarot carte_: cartesNonMaitresses_) {
                            if (!mainPreneur_.contient(carte_)) {
                                continue;
                            }
                            if (ecart_.total() == tailleChien_) {
                                return ecart_;
                            }
                            ecart_.ajouter(carte_);
                        }
                    }

                }
                couleurs_ = couleursOrdinaires();
                for(EnumList<Suit> suits_: couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
                    HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(
                            cartesMaitresses_, carteAppelee_, cartesPseudosMaitres_);
                    for(Suit couleur_: suits_) {
                        cartesNonMaitresses_ = cartesNonMaitresses_.cartesBasses(couleur_);
                        cartesNonMaitresses_.trierParForceEcart(couleur_);
                        for(CardTarot carte_: cartesNonMaitresses_) {
                            if (!mainPreneur_.contient(carte_)) {
                                continue;
                            }
                            if (ecart_.total() == tailleChien_) {
                                return ecart_;
                            }
                            ecart_.ajouter(carte_);
                        }
                    }

                }
                couleurs_ = couleursAvecFigures(mainPreneur_, _couleursNonAppelees);
                for(EnumList<Suit> suits_: couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
                    for(Suit couleur_: suits_) {
                        HandTarot figures_ = mainPreneur_.charCardsBySuit(couleur_);
                        figures_.trierParForceEnCours(couleur_);
                        for(CardTarot carte_: figures_) {
                            if (!ecartables_.contient(carte_)) {
                                continue;
                            }
                            if (ecart_.total() == tailleChien_) {
                                return ecart_;
                            }
                            ecart_.ajouter(carte_);
                        }
                    }
                }
                couleurs_ = couleursAvecFigures(mainPreneur_, couleursOrdinaires());
                for(EnumList<Suit> suits_: couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
                    for(Suit couleur_: suits_) {
                        HandTarot figures_ = mainPreneur_.charCardsBySuit(couleur_);
                        figures_.trierParForceEnCours(couleur_);
                        for(CardTarot carte_: figures_) {
                            if (!ecartables_.contient(carte_)) {
                                continue;
                            }
                            if (ecart_.total() == tailleChien_) {
                                return ecart_;
                            }
                            ecart_.ajouter(carte_);
                        }
                    }
                }
            }
            return ecart_;
        }
        EnumList<Suit> couleurs_ = couleursSansRoi(mainPreneur_, couleursOrdinaires());
        couleurs_ = couleursAvecFigures(mainPreneur_, couleurs_);
        for(EnumList<Suit> suits_: couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot figures_ = mainPreneur_.charCardsBySuit(couleur_);
                figures_.trierParForceEnCours(couleur_);
                for(CardTarot carte_: figures_) {
                    if (ecart_.total() == tailleChien_) {
                        return ecart_;
                    }
                    ecart_.ajouter(carte_);
                }
            }
        }
        couleurs_ = couleursTotalEcartables(mainPreneur_, tailleChien_, ecart_, _couleursNonAppelees);
        for(EnumList<Suit> suits_: couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
            for(EnumList<Suit> couleurs2_: couleursTrieesPlusHautes(mainPreneur_, suits_)) {
                for(Suit s_: couleurs2_) {
                    HandTarot couleur_ = mainPreneur_.couleur(s_);
                    for(CardTarot carte_: couleur_) {
                        if (ecart_.total() == tailleChien_) {
                            return ecart_;
                        }
                        ecart_.ajouter(carte_);
                    }
                }
            }
        }
        couleurs_ = couleursSansRoi(mainPreneur_, couleursOrdinaires());
        for(EnumList<Suit> suits_: couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot cartesBasses_ = mainPreneur_.cartesBasses(couleur_);
                cartesBasses_.trierParForceEcart(couleur_);
                for(CardTarot carte_: cartesBasses_) {
                    if (ecart_.total() == tailleChien_) {
                        return ecart_;
                    }
                    ecart_.ajouter(carte_);
                }
            }
        }
        couleurs_ = couleursOrdinaires();
        for(EnumList<Suit> suits_: couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
            HandTarot cartesNonMaitresses_ = cartesNonMaitressesDebut(
                    cartesMaitresses_, new HandTarot(), new HandTarot());
            for(Suit couleur_: suits_) {
                cartesNonMaitresses_ = cartesNonMaitresses_.charCardsBySuit(couleur_);
                cartesNonMaitresses_.trierParForceEcart(couleur_);
                for(CardTarot carte_: cartesNonMaitresses_) {
                    if (!mainPreneur_.contient(carte_)) {
                        continue;
                    }
                    if (ecart_.total() == tailleChien_) {
                        return ecart_;
                    }
                    ecart_.ajouter(carte_);
                }
            }
        }
        couleurs_ = couleursOrdinaires();
        for(EnumList<Suit> suits_: couleursTrieesPlusCourtes(mainPreneur_, couleurs_)) {
            for(Suit couleur_: suits_) {
                HandTarot cartesBasses_ = mainPreneur_.cartesBasses(couleur_);
                cartesBasses_.trierParForceEcart(couleur_);
                for(CardTarot carte_: cartesBasses_) {
                    if (ecart_.total() == tailleChien_) {
                        return ecart_;
                    }
                    ecart_.ajouter(carte_);
                }
            }
        }
        return ecart_;
    }

    public void invaliderAjoutCarteAuChien(CardTarot _ct) {
        cardsToBeDiscarded.jouer(_ct);
    }

    public void retirerUneCarteDuChien(CardTarot _ct) {
        progressingTrick.retirer(_ct);
        invaliderAjoutCarteAuChien(_ct);
    }

    private boolean autoAppelPourEcart() {
        CallingCard appel_ = rules.getRepartition().getAppel();
        if (appel_ == CallingCard.CHARACTER_CARD || appel_ == CallingCard.KING) {
            if (callableCards().estVide()) {
                return true;
            }
            if (!existeCarteAppelee()) {
                return true;
            }
            return deal.main(taker).contientCartes(calledCards);
        }
        return true;
    }


    /**
    Retourne l'ensemble des cartes maitresses dans leur propre couleur mais
    pas les atouts maitres donc pour recuperer la couleur n i il faut ecrire
    cartesMaitresses.get(i)
    */
    private static EnumMap<Suit,HandTarot> cartesMaitressesDebutPartie(
            EnumMap<Suit,HandTarot> _couleurs) {
        return cartesMaitresses(_couleurs, new HandTarot().couleurs());
    }

    /**
    @param _cartesMaitresses
    @param _carteAppelee
    @param _cartesPseudosMaitres
    @return les cartes non pseudo maitresses
    */
    private static HandTarot cartesNonMaitressesDebut(
            EnumMap<Suit,HandTarot> _cartesMaitresses, HandTarot _carteAppelee,
            HandTarot _cartesPseudosMaitres) {
        HandTarot cartesNonMaitresses_ = new HandTarot();
        for (Suit couleur_ : couleursOrdinaires()) {
            HandTarot main_ = _cartesMaitresses.getVal(couleur_);
            if (_cartesMaitresses.getVal(couleur_).estVide()) {
                continue;
            }
            HandTarot couleurTotale_ = HandTarot.couleurComplete(couleur_);
            couleurTotale_.trierParForceEnCours(couleur_);
            if (!_carteAppelee.estVide()) {
                if (_carteAppelee.premiereCarte().couleur() == couleur_) {
                    for (CardTarot carte_ : couleurTotale_) {
                        if (!_cartesPseudosMaitres.contient(carte_)) {
                            cartesNonMaitresses_.ajouter(carte_);
                        }
                    }
                } else {
                    for (CardTarot carte_ : couleurTotale_) {
                        if (!main_.contient(carte_)) {
                            cartesNonMaitresses_.ajouter(carte_);
                        }
                    }
                }
            } else {
                for (CardTarot carte_ : couleurTotale_) {
                    if (!main_.contient(carte_)) {
                        cartesNonMaitresses_.ajouter(carte_);
                    }
                }
            }
        }
        return cartesNonMaitresses_;
    }

    private static EnumList<Suit> couleursTotalEcartables(HandTarot _mainPreneur,
            int _tailleChien, HandTarot _ecart, EnumList<Suit> _couleursNonAppelees) {
        EnumList<Suit> couleursEntieresEcartables_ = couleursSansRoi(
                _mainPreneur, _couleursNonAppelees);
        couleursEntieresEcartables_ = couleursNonAtoutNonVides(
                _mainPreneur, couleursEntieresEcartables_);
        byte nombreCartesAjoutees_ = (byte) (_tailleChien - _ecart.total());
        couleursEntieresEcartables_ = couleursNonAtoutAyantNbCartesInfEg(
                _mainPreneur, couleursEntieresEcartables_,
                nombreCartesAjoutees_);
        return couleursEntieresEcartables_;
    }

    private static EnumList<Suit> couleursPLonguePHaute(HandTarot _main,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (EnumList<Suit> groupeCouleurs_ : couleursTrieesPlusLongues(_main,
                _couleurs)) {
            for (EnumList<Suit> groupeCouleursBis_ : couleursTrieesPlusHautes(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursPLongueMHaute(HandTarot _main,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (EnumList<Suit> groupeCouleurs_ : couleursTrieesPlusLongues(_main,
                _couleurs)) {
            for (EnumList<Suit> groupeCouleursBis_ : couleursTrieesPlusBasses(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursMLongueMHaute(HandTarot _main,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (EnumList<Suit> groupeCouleurs_ : couleursTrieesPlusCourtes(_main,
                _couleurs)) {
            for (EnumList<Suit> groupeCouleursBis_ : couleursTrieesPlusBasses(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursPHauteMLongue(HandTarot _main,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (EnumList<Suit> groupeCouleurs_ : couleursTrieesPlusHautes(_main,
                _couleurs)) {
            for (EnumList<Suit> groupeCouleursBis_ : couleursTrieesPlusCourtes(
                    _main, groupeCouleurs_)) {
                couleurs_.addAllElts(groupeCouleursBis_);
            }
        }
        return couleurs_;
    }

    public void gererChienInconnu() {
        setEntameur(taker);
        setPliEnCours(false);
        for(CardTarot carte_:derniereMain()) {
            ajouterUneCarteDansPliEnCours(carte_);
        }
        if(getContrat().getJeuChien() == PlayingDog.WITHOUT) {
            ajouterPliAttaque();
        } else {
            ajouterPliDefense();
        }
    }
    public boolean annoncerUnChelem(byte _numeroJoueur) {
        HandTarot mainJoueur_ = getDistribution().main(_numeroJoueur);
        reason = new StringBuilder();
        return annoncerUnChelem(mainJoueur_);
    }
    private boolean annoncerUnChelem(HandTarot _mainJoueur) {
        EnumMap<Suit,HandTarot> repartition_ = _mainJoueur.couleurs();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        return estUnJeuDeChelem(repartition_, new HandTarot().couleurs(), rules, calledCards, nombreJoueurs_);
    }

    private static boolean maitreAtoutPourChelem(EnumMap<Suit,HandTarot> _couleurs,
            byte _joueurs) {
        byte atouts_ = (byte) (_couleurs.getVal(CardTarot.excuse().couleur()).total() + _couleurs.getVal(couleurAtout()).total());
        byte atoutsMaitres_ = nbAtoutsMaitres(_couleurs);
        if (_joueurs == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
            if (atouts_ > 20) {
                return atoutsMaitres_ > 0;
            }
            if (atouts_ > 18) {
                return atoutsMaitres_ > 1;
            }
            if (atouts_ > 16) {
                return atoutsMaitres_ > 2;
            }
            if (atouts_ > 14) {
                return atoutsMaitres_ > 3;
            }
            if (atouts_ > 12) {
                return atoutsMaitres_ > 4;
            }
            if (atouts_ > 10) {
                return atoutsMaitres_ > 5;
            }
            if (atouts_ > 8) {
                return atoutsMaitres_ > 6;
            }
            return false;
        }
        if (_joueurs == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getNombreJoueurs()) {
            if (atouts_ > 15) {
                return atoutsMaitres_ > 1;
            }
            if (atouts_ > 13) {
                return atoutsMaitres_ > 2;
            }
            if (atouts_ > 11) {
                return atoutsMaitres_ > 3;
            }
            if (atouts_ > 9) {
                return atoutsMaitres_ > 4;
            }
            if (atouts_ > 7) {
                return atoutsMaitres_ > 5;
            }
            return false;
        }
        if (_joueurs == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getNombreJoueurs()) {
            if (atouts_ > 10) {
                return atoutsMaitres_ > 2;
            }
            if (atouts_ > 8) {
                return atoutsMaitres_ > 3;
            }
            if (atouts_ > 6) {
                return atoutsMaitres_ > 4;
            }
            if (atouts_ == 6) {
                return atoutsMaitres_ > 5;
            }
            return false;
        }
        if (atouts_ == 15) {
            return atoutsMaitres_ > 1;
        }
        if (atouts_ > 12) {
            return atoutsMaitres_ > 2;
        }
        if (atouts_ > 10) {
            return atoutsMaitres_ > 3;
        }
        if (atouts_ > 8) {
            return atoutsMaitres_ > 4;
        }
        if (atouts_ > 6) {
            return atoutsMaitres_ > 5;
        }
        return false;
    }
    private static boolean estUnJeuDeChelem(EnumMap<Suit,HandTarot> _couleurs,
            EnumMap<Suit,HandTarot> _cartesJouees,
            RulesTarot _infos, HandTarot _cartesAppeler,byte _joueurs) {
        if (estUnJeuDeChelemSur(_couleurs,_cartesJouees)) {
            return true;
        }
        if (!maitreAtoutPourChelem(_couleurs,_joueurs)) {
            return false;
        }
        byte nombreCouleursLargMait_ = nbCouleursLargementMaitresses(
                _couleurs, _joueurs);
        if (_joueurs == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
            return nombreCouleursLargMait_ == couleursOrdinaires().size();
        }
        byte nombreCouleursPseuMait_ = nbCouleursPseudoMaitresses(_couleurs,
                _cartesAppeler,
                _joueurs);
        boolean avecAppel_ = _infos.getRepartition().getAppel() == CallingCard.KING;
        if (_infos.getRepartition().getAppel() == CallingCard.CHARACTER_CARD) {
            avecAppel_ = true;
        }
        if (avecAppel_) {
            return nombreCouleursPseuMait_ == 1
                    && nombreCouleursLargMait_ == couleursOrdinaires().size() - 1 || nombreCouleursLargMait_ == couleursOrdinaires().size();
        }
        return nombreCouleursLargMait_ == couleursOrdinaires().size();
    }

    public void slam() {
        ajouterChelem(getPreneur(),annoncerUnChelem(getPreneur()));
        if (declaresSlam.get(getPreneur())) {
            setEntameur(getPreneur());
        }
    }
    public void ajouterChelemUtilisateur() {
        ajouterChelem(getPreneur(), true);
        if (declaresSlam.get(getPreneur())) {
            setEntameur(getPreneur());
        }
    }
    void ajouterChelem(byte _b, boolean _annonce) {
        declaresSlam.set( _b, _annonce);
    }


    public boolean chelemAnnonce(byte _numero) {
        return declaresSlam.get(_numero);
    }

    /** Est vrai si et seulement si un chelem est annonce */
    public boolean chelemAnnonce() {
        boolean contientChelem_ = bid.isFaireTousPlis();
        for (boolean chelem_ : declaresSlam) {
            if (chelem_) {
                contientChelem_ = true;
            }
        }
        return contientChelem_;
    }

    public void setPliEnCours(boolean _vuParAutreJoueur) {
        progressingTrick = new TrickTarot(new HandTarot(), starter, _vuParAutreJoueur);
    }

    public TrickTarot getPliEnCours() {
        return getProgressingTrick();
    }

    public EnumList<Handfuls> getAnnoncesPoigneesPossibles(byte _numero) {
        HandTarot mainJoueur_ = getDistribution().main(_numero);
        EnumMap<Suit,HandTarot> repartition_ = mainJoueur_.couleurs();
        int nombreAtoutsEx_ = atoutsAvecExcuse(repartition_);
        EnumList<Handfuls> annoncesPossibles_ = new EnumList<Handfuls>();
        for (Handfuls poignee_ : rules.getPoigneesAutorisees().getKeys()) {
            if(nombreAtoutsEx_ < rules.getPoigneesAutorisees().getVal(poignee_)) {
                continue;
            }
            annoncesPossibles_.add(poignee_);
        }
        return annoncesPossibles_;

    }


    public EnumList<Handfuls> strategieAnnoncesPoignees(byte _numeroJoueur) {

        EnumList<Handfuls> va_ = new EnumList<Handfuls>();
        HandTarot mainJoueur_ = getDistribution().main(_numeroJoueur);
        EnumMap<Suit,HandTarot> repartition_ = mainJoueur_.couleurs();
        // peuvent etre annoncees par le joueur si toutes les annonces etaient
        // autorisees
        HandTarot atouts_ = atoutsPoignee(repartition_);
        boolean chienInvisible_ = bid.getJeuChien() == PlayingDog.WITHOUT;
        if (bid.getJeuChien() == PlayingDog.AGAINST) {
            chienInvisible_ = true;
        }
        if (taker != _numeroJoueur || existeAppele() || existeCarteAppelee()
                && chienInvisible_ || estUnJeuDeChelemSur(repartition_,cartesJoueesEnCours(_numeroJoueur).couleurs())) {
            EnumList<Handfuls> poigneesOrdonnees_ = rules.getPoigneesOrdonnees();
            EnumList<Handfuls> poigneesAutorisees_ = new EnumList<Handfuls>();
            for(Handfuls p: poigneesOrdonnees_) {
                if(!rules.poigneeAutorisee(p)) {
                    continue;
                }
                poigneesAutorisees_.add(p);
            }
            if(poigneesAutorisees_.isEmpty()) {
                return va_;
            }
            EnumMap<Handfuls,Integer> poigneesNbAtout_ = rules.getPoigneesAutorisees();
            if (atouts_.total() < poigneesNbAtout_.getVal(poigneesAutorisees_.first())) {
                return va_;
            }
            int nbHandfuls_ = poigneesOrdonnees_.size();
            for(int i=CustList.SECOND_INDEX;i<nbHandfuls_;i++) {
                Handfuls p_ = poigneesOrdonnees_.get(i);
                if(atouts_.total() < poigneesNbAtout_.getVal(p_)) {
                    va_.add(poigneesAutorisees_.getPrev(i));
                    return va_;
                }

            }
            va_.add(poigneesAutorisees_.last());
            return va_;
        }
        return va_;
    }

    public boolean isValidHandful(Handfuls _h, HandTarot _hand, HandTarot _excludedCards) {
        int nbTrumps_ = rules.getPoigneesAutorisees().getVal(_h);
        return _hand.total() == nbTrumps_ && (!_hand.contient(CardTarot.excuse()) || _excludedCards.estVide());
    }

    public HandTarot strategiePoignee(byte _numeroJoueur) {
        reason = new StringBuilder();
        HandTarot mainJoueur_ = getDistribution().main(_numeroJoueur);
        EnumMap<Suit,HandTarot> repartition_ = mainJoueur_.couleurs();
        HandTarot atouts_ = atoutsPoignee(repartition_);
        HandTarot poignee_ = new HandTarot();
        for(Handfuls p: getAnnoncesPoignees(_numeroJoueur)) {
            byte max_ = rules.getPoigneesAutorisees().getVal(p).byteValue();
            byte trumpIndex_ = CustList.FIRST_INDEX;
            if(atouts_.total() == max_) {
                while (poignee_.total() < max_) {
                    poignee_.ajouter(atouts_.carte(trumpIndex_));
                    trumpIndex_++;
                }
            } else {
                //atouts_.total() > max_ because of strategieAnnoncesPoignees
                while (poignee_.total() < max_) {
                    CardTarot card_ = atouts_.carte(trumpIndex_);
                    if (card_.couleur() == Suit.TRUMP) {
                        poignee_.ajouter(card_);
                    }
                    trumpIndex_++;
                }
            }
        }
        return poignee_;
    }

    /** trie les atouts de la maniere suivante: (Excuse, 21, 20, ... 1) */
    public static HandTarot atoutsPoignee(EnumMap<Suit,HandTarot> _repartition) {
        HandTarot m = new HandTarot();
        m.ajouterCartes(_repartition.getVal(couleurAtout()));
        if (!_repartition.getVal(CardTarot.excuse().couleur()).estVide()) {
            m.ajouter(CardTarot.excuse(), 0);
        }
        return m;
    }


    static boolean estUnJeuDeChelemSur(EnumMap<Suit,HandTarot> _couleurs, EnumMap<Suit,HandTarot> _cartesJouees) {
        int nbTr_ = nbAtoutsMaitres(_couleurs) + _cartesJouees.getVal(Suit.TRUMP).total() + _couleurs.getVal(Suit.TRUMP).total();
        int nbFullTr_ = HandTarot.atoutsSansExcuse().total() + _couleurs.getVal(CardTarot.excuse().couleur()).total();
        if (nbTr_ == nbFullTr_) {
            return nbCouleursMaitresses(_couleurs, _cartesJouees) == couleursOrdinaires().size();
        }
        return false;
    }

    private static byte nbAtoutsMaitres(EnumMap<Suit,HandTarot> _repartition) {
        return (byte) _repartition.getVal(couleurAtout()).atoutsMaitres(new HandTarot().couleurs()).total();
    }

    private static byte nbCouleursMaitresses(EnumMap<Suit,HandTarot> _couleurs, EnumMap<Suit,HandTarot> _cartesJouees) {
        byte nb_ = 0;
        for (Suit b : couleursOrdinaires()) {
            if (maitreDansUneCouleur(_couleurs, _cartesJouees, b)) {
                nb_++;
            }
        }
        return nb_;
    }

    private static byte nbCouleursPseudoMaitresses(EnumMap<Suit,HandTarot> _couleurs,
            HandTarot _cartesAppeler,
            byte _nombreJoueurs) {
        byte nb_ = 0;
        for (Suit couleur_ : couleursOrdinaires()) {
            if (pseudoMaitreDansUneCouleurContrat(_couleurs, couleur_,_cartesAppeler, _nombreJoueurs)) {
                nb_++;
            }
        }
        return nb_;
    }

    private static byte nbCouleursLargementMaitresses(
            EnumMap<Suit,HandTarot> _couleurs, byte _nombreJoueurs) {
        byte nb_ = 0;
        for (Suit couleur_ : couleursOrdinaires()) {
            if (largementMaitreDansUneCouleurAuDebut(_couleurs, couleur_, _nombreJoueurs)) {
                nb_++;
            }
        }
        return nb_;
    }

    private static boolean pseudoMaitreDansUneCouleurContrat(
            EnumMap<Suit,HandTarot> _couleurs, Suit _noCouleur, HandTarot _cartesAppeler, byte _nombreJoueurs) {
        if (largementMaitreDansUneCouleurAuDebut(_couleurs, _noCouleur, _nombreJoueurs)) {
            return false;
        }
        return pseudoMaitreDansUneCouleur(_couleurs, _cartesAppeler, _noCouleur);
    }

    private static boolean largementMaitreDansUneCouleurAuDebut(
            EnumMap<Suit,HandTarot> _couleurs, Suit _noCouleur, byte _nombreJoueurs) {
        if (maitreDansUneCouleur(_couleurs, new HandTarot().couleurs(), _noCouleur)) {
            return true;
        }
        if (_nombreJoueurs == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
            return nbCartesMaitresses(_couleurs, new HandTarot().couleurs(), _noCouleur) > 5;
        }
        if (_nombreJoueurs == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getNombreJoueurs()) {
            return nbCartesMaitresses(_couleurs, new HandTarot().couleurs(), _noCouleur) > 4;
        }
        if (_nombreJoueurs == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL.getNombreJoueurs()) {
            return nbCartesMaitresses(_couleurs, new HandTarot().couleurs(), _noCouleur) > 2;
        }
        return nbCartesMaitresses(_couleurs, new HandTarot().couleurs(), _noCouleur) > 3;
    }

    private static boolean pseudoMaitreDansUneCouleur(
            EnumMap<Suit,HandTarot> _couleurs, HandTarot _cartesAppeler, Suit _noCouleur) {
        return couleursPseudosMaitres(_couleurs, cartesPseudoMaitresses(_couleurs, _cartesAppeler, new HandTarot().couleurs())).containsObj(_noCouleur);
    }

    private static boolean maitreDansUneCouleur(EnumMap<Suit,HandTarot> _couleurs,
            EnumMap<Suit,HandTarot> _cartesJouees,
            Suit _noCouleur) {
        int nombreCartesMaitresses_ = nbCartesMaitresses(_couleurs, _cartesJouees, _noCouleur);
        if (nombreCartesMaitresses_ == _couleurs.getVal(_noCouleur).total()) {
            return true;
        }
        byte totalCouleur_ = (byte) HandTarot.couleurComplete(_noCouleur).total();
        if (nombreCartesMaitresses_ > totalCouleur_/2) {
            return true;
        }
        int nb_ = nombreCartesMaitresses_ + _couleurs.getVal(_noCouleur).total();
        if (nb_ > totalCouleur_) {
            return true;
        }
        return nb_ > totalCouleur_ - 1 && !_couleurs.getVal(CardTarot.EXCUSE.couleur()).estVide();
    }

    static int nbCartesMaitresses(EnumMap<Suit,HandTarot> _couleurs,
            EnumMap<Suit,HandTarot> _cartesJouees,
            Suit _noCouleur) {
        HandTarot couleur_ = _couleurs.getVal(_noCouleur);
        EqList<HandTarot> suites_ = couleur_.eclaterEnCours(_cartesJouees, _noCouleur);
        HandTarot cartesJoueesOuPossedes_ = new HandTarot();
        cartesJoueesOuPossedes_.ajouterCartes(couleur_);
        cartesJoueesOuPossedes_.ajouterCartes(_cartesJouees.getVal(_noCouleur));
        cartesJoueesOuPossedes_.trierParForceEnCours(_noCouleur);
        HandTarot couleurComplete_ = HandTarot.couleurComplete(_noCouleur);
        couleurComplete_.trierParForceEnCours(_noCouleur);
        boolean cartesMaitressesToutesJoueesOuPossedes_ = true;
        for(CardTarot carte_: couleurComplete_) {
            if(!cartesJoueesOuPossedes_.contient(carte_)) {
                cartesMaitressesToutesJoueesOuPossedes_ = false;
                break;
            }
            if(couleur_.contient(carte_)) {
                break;
            }
        }
        if(cartesMaitressesToutesJoueesOuPossedes_) {
            return suites_.first().total();
        }
        return 0;
    }

    public void ajouterAnnoncesPoignees(byte _b, EnumList<Handfuls> _ann) {
        declaresHandfuls.get(_b).addAllElts(_ann);
    }

    public void setAnnoncesPoignees(byte _joueur, EnumList<Handfuls> _ann) {
        declaresHandfuls.set( _joueur, _ann);
    }

    public void ajouterPoignee(HandTarot _mt, byte _numero) {
        handfuls.set( _numero, _mt);
    }

    public EnumList<Handfuls> getAnnoncesPoignees(byte _numero) {
        return declaresHandfuls.get(_numero);
    }

    public HandTarot getPoignee(byte _b) {
        return handfuls.get(_b);
    }

    public EnumList<Miseres> getAnnoncesMiseresPossibles(byte _numero) {
        HandTarot mainJoueur_ = getDistribution().main(_numero);
        EnumMap<Suit,HandTarot> repartition_ = mainJoueur_.couleurs();
        int nombreAtoutsEx_ = atoutsAvecExcuse(repartition_);
        EnumList<Miseres> annoncesPossibles_ = new EnumList<Miseres>();
        if (nombreAtoutsEx_ == 0) {
            annoncesPossibles_.add(Miseres.TRUMP);
        }
        if (nombreAtoutsEx_ + mainJoueur_.nombreDeFigures() == mainJoueur_
                .total()) {
            annoncesPossibles_.add(Miseres.LOW_CARDS);
        }
        if (nombreAtoutsEx_ == mainJoueur_.total()) {
            annoncesPossibles_.add(Miseres.SUIT);
        }
        if (mainJoueur_.nombreDeFigures() == 0) {
            annoncesPossibles_.add(Miseres.CHARACTER);
        }
        if (mainJoueur_.nombreDeFigures() + mainJoueur_.nombreDeBouts() == 0) {
            annoncesPossibles_.add(Miseres.POINT);
        }
        return annoncesPossibles_;
    }

    public EnumList<Miseres> strategieAnnoncesMiseres(byte _numeroJoueur) {

        EnumList<Miseres> vaa_ = rules.getMiseres();
        EnumList<Miseres> vap_ = getAnnoncesMiseresPossibles(_numeroJoueur);
        EnumList<Miseres> vainter_ = new EnumList<Miseres>();
        // Intersection entre
        // les annonces
        // auorisees par les
        // regles du jeu et
        // celles
        // peuvent etre annoncees par le joueur si toutes les annonces etaient
        // autorisees
        for (Miseres m: vaa_) {
            if (vap_.containsObj(m)) {
                vainter_.add(m);
            }
        }
        return vainter_;
    }

    public void ajouterAnnoncesMiseres(byte _b, EnumList<Miseres> _ann) {
        declaresMiseres.get(_b).addAllElts(_ann);
    }

    public void setAnnoncesMiseres(byte _joueur, EnumList<Miseres> _ann) {
        declaresMiseres.set( _joueur, _ann);
    }

    public EnumList<Miseres> getAnnoncesMiseres(byte _numero) {
        return declaresMiseres.get(_numero);
    }


    public static int atoutsAvecExcuse(EnumMap<Suit,HandTarot> _couleurs) {
        return _couleurs.getVal(CardTarot.excuse().couleur()).total() + _couleurs.getVal(couleurAtout()).total();
    }

    /** Appelee au debut d'une partie */
    public void setEntameur(byte _i) {
        if (bid == BidTarot.SLAM) {
            starter = taker;
        } else {
            starter = _i;
        }
    }

    public boolean autorise(CardTarot _c) {
        HandTarot main_ = getDistribution().main(playerHavingToPlay());
        EnumMap<Suit,HandTarot> repartition_ = main_.couleurs();
        return cartesJouables(repartition_).contient(_c);
    }

    HandTarot playableCards(EnumMap<Suit,HandTarot> _repartitionMain) {
        return cartesJouables(_repartitionMain);
    }
    public HandTarot cartesJouables(EnumMap<Suit, HandTarot> _repartitionMain) {
        HandTarot atoutsJoues_ = progressingTrick.getCartes().couleurs().getVal(couleurAtout());
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        HandTarot cartesJouables_ = new HandTarot();
        int nombreCartes_ = 0;
        /* Dans la main */
        for (HandTarot main_ : _repartitionMain.values()) {
            nombreCartes_ += main_.total();
        }
        if (progressingTrick.couleurDemandee() == Suit.UNDEFINED) {
            if (premierTour() && calledCards.total() == 1) {
                Suit couleurAppele_ = calledCards.premiereCarte().couleur();
                if (nombreCartes_ > _repartitionMain
                        .getVal(couleurAppele_).total()) {
                    cartesJouables_.ajouterCartes(_repartitionMain
                            .getVal(CardTarot.excuse().couleur()));
                    cartesJouables_.ajouterCartes(_repartitionMain
                            .getVal(couleurAtout()));

                    for (Suit couleur_ : couleursOrdinaires()) {
                        if(couleur_ != couleurAppele_) {
                            cartesJouables_.ajouterCartes(_repartitionMain
                                    .getVal(couleur_));
                            continue;
                        }
                        for(CardTarot carte_:_repartitionMain
                                .getVal(couleur_)) {
                            if(!CardTarot.eq(carte_, calledCards.premiereCarte())) {
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
        if (couleursOrdinaires().containsObj(couleurDemandee_)
                && !_repartitionMain.getVal(couleurDemandee_).estVide()) {
            cartesJouables_
            .ajouterCartes(_repartitionMain.getVal(couleurDemandee_));
            return cartesJouables_;
        }
        if (_repartitionMain.getVal(couleurAtout()).estVide()) {
            for (Suit couleur_ : couleursOrdinaires()) {
                cartesJouables_.ajouterCartes(_repartitionMain.getVal(couleur_));
            }
            return cartesJouables_;
        }
        if (atoutsJoues_.estVide()) {
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(couleurAtout()));
            return cartesJouables_;
        }
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        byte ramasseurVirtuel_ = progressingTrick.getRamasseur(nombreDeJoueurs_);
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(
                ramasseurVirtuel_, nombreDeJoueurs_);
        byte valeurForte_ = carteForte_.strength(couleurDemandee_);
        if (_repartitionMain.getVal(couleurAtout()).premiereCarte().strength(couleurDemandee_) < valeurForte_) {
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(couleurAtout()));
            return cartesJouables_;
        }
        if (valeurForte_ < _repartitionMain.getVal(couleurAtout()).derniereCarte().strength(couleurDemandee_)) {
            cartesJouables_.ajouterCartes(_repartitionMain.getVal(couleurAtout()));
            return cartesJouables_;
        }
        byte indiceCarte_ = CustList.FIRST_INDEX;
        HandTarot trumps_ = _repartitionMain.getVal(couleurAtout());
        while (trumps_.carte(indiceCarte_)
                .strength(couleurDemandee_) > valeurForte_) {
            cartesJouables_.ajouter(trumps_.carte(indiceCarte_));
            indiceCarte_++;
        }
        return cartesJouables_;
    }

    public boolean premierTour() {
        int plisTotal_ = 0;
        if(existePreneur()) {
            for(TrickTarot p: getPlisAttaque()) {
                if(!p.getVuParToutJoueur()) {
                    continue;
                }
                plisTotal_++;
            }
            for(TrickTarot p: getPlisDefense()) {
                if(!p.getVuParToutJoueur()) {
                    continue;
                }
                plisTotal_++;
            }
            return plisTotal_ == 0;
        }
        plisTotal_ = tricks.size();
        return plisTotal_ <= 1;
    }

    /**for multi player*/
    public boolean currentPlayerHasPlayed(byte _player) {
        if (getPliEnCours().aJoue(_player,getNombreDeJoueurs())) {
            return true;
        }
        changerConfianceJeuCarteUnique();
        ajouterUneCarteDansPliEnCours(_player, getCarteJoueee());
        return false;
    }

    public void changerConfianceJeuCarteUnique() {
        changerConfiance();
        playedCard = strategieJeuCarteUnique();
    }
    public void changerConfiance() {
        if (!existePreneur()) {
            return;
        }
        if (!appelAFaire()) {
            return;
        }
        if (!existeAppele()) {
            return;
        }
        if (!existeCarteAppelee()) {
            return;
        }
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        byte numero_ = (byte) ((starter + progressingTrick.total()) % nombreDeJoueurs_);
        HandTarot mainJoueur_ = getDistribution().main(numero_);
        if (numero_ == taker) {
            if(mainJoueur_.contientCartes(calledCards)) {
                return;
            }
        }
        EnumMap<Suit,HandTarot> repartition_ = mainJoueur_.couleurs();
        CustList<TrickTarot> plisFaits_ = unionPlis(true);
        HandTarot cartesJouees_ = cartesJoueesEnCours(numero_);
        boolean carteAppeleeJouee_ = carteAppeleeJouee(cartesJouees_);
        if(carteAppeleeJouee_) {
            determinerConfiance(numero_, nombreDeJoueurs_);
            return;
        }
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = cartesJouees_.couleurs();
        boolean excuseJouee_ = cartesJouees_.contient(CardTarot.excuse());
        CustList<EqList<HandTarot>> suites_ = new CustList<EqList<HandTarot>>();
        suites_.add(new EqList<HandTarot>());
        suites_.add(repartition_.getVal(couleurAtout()).eclaterEnCours(
                repartitionCartesJouees_, couleurAtout()));
        for (Suit i : couleursOrdinaires()) {
            suites_.add(repartition_.getVal(i).eclaterEnCours(
                    repartitionCartesJouees_, i));
        }
        boolean contientExcuse_ = mainJoueur_.contient(CardTarot.excuse());
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = cartesPossibles(
                excuseJouee_,
                repartitionCartesJouees_, plisFaits_, contientExcuse_,
                repartition_, numero_,
                false);
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> hypotheses_ = cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        hypothesesRepartitionsJoueurs(plisFaits_, numero_, cartesPossibles_,
                cartesCertaines_);
    }
    private void hypothesesRepartitionsJoueurs(CustList<TrickTarot> _plisFaits, byte _numero,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        boolean appelesTousConnus_ = true;
        for(CardTarot c: calledCards) {
            boolean trouve_ = false;
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (_cartesCertaines.getVal(c.couleur())
                        .get(joueur_).contient(c)) {
                    trouve_ = true;
                    break;
                }
            }
            if(!trouve_) {
                appelesTousConnus_ = false;
                break;
            }
        }
        if(appelesTousConnus_) {
            determinerConfiance(_numero, nombreJoueurs_);
            return;
        }
        Numbers<Byte> joueursNonConfiancePresqueSure_ = new Numbers<Byte>();
        for(CardTarot c: calledCards) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (_cartesPossibles.getVal(c.couleur())
                        .get(joueur_).estVide()) {
                    //si le joueur ne possede pas de la couleur appele
                    if (aPourDefenseur(_numero)) {
                        faireConfiance(_numero, joueur_);
                    } else {
                        //numero == preneur
                        if(!joueursNonConfiancePresqueSure_.containsObj(joueur_)) {
                            joueursNonConfiancePresqueSure_.add(joueur_);
                        }
                    }
                }
            }
        }
        boolean ramasseurDuPliAvecPetitNonPreneur_ = false;
        boolean arreterRechercheJoueurJoueCartePoint_;
        for (byte j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //iteration sur la confiance du joueur numero en le joueur j
            //vis a vis du Petit joue en premier atout mais jamais virtuellement maitre
            //le Petit doit etre ramasse par le plus grand atout encore en jeu
            //ramasse par un autre atout
            boolean passerAuJoueurSuivant_ = false;
            byte ramasseur_ = -1;
            for(TrickTarot p: _plisFaits) {
                if(!p.getVuParToutJoueur()) {
                    continue;
                }
                CardTarot carte_ = p.carteDuJoueur(j);
                if(carte_.couleur() != Suit.TRUMP) {
                    continue;
                }
                //arret de la boucle sur les plis des que le joueur a joue un atout
                if(carte_ != CardTarot.petit()) {
                    //Si le joueur j n'a pas joue le Petit en tant que premier atout,
                    //alors on passe au joueur suivant
                    passerAuJoueurSuivant_ = true;
                    break;
                }
                Suit couleurDemandee_ = p.couleurDemandee();
                byte forcePetit_ = carte_.strength(couleurDemandee_);
                boolean petitRamasse_ = false;
                for(byte j2_: p.joueursAyantJoueAvant(j)) {
                    if(p.carteDuJoueur(j2_).strength(couleurDemandee_) < forcePetit_) {
                        continue;
                    }
                    //la carte du joueur j2 est un atout ramassant temporairement le Petit
                    petitRamasse_ = true;
                    break;
                }
                if(!petitRamasse_) {
                    break;
                }
                //carte == CarteTarot.petit()
                ramasseur_ = p.getRamasseur();
                if(ramasseur_ == j) {
                    //Si le joueur j a ramasse le pli avec le Petit,
                    //alors on passe au joueur suivant
                    passerAuJoueurSuivant_ = true;
                }
                //ramasseur != -1 && passerAuJoueurSuivant = false
                break;
            }
            if(ramasseur_ == -1) {
                return;
            }
            if(passerAuJoueurSuivant_) {
                continue;
            }
            //ramasseur != j && ramasseur != -1
            if(aPourDefenseur(_numero)) {
                if(ramasseur_ == taker || j == taker) {
                    fixConfidenceDefender(_numero, nombreJoueurs_);
                    return;
                }
                //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
                //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
                //de plus numero est un defenseur
                faireConfiance(_numero, j);
                faireConfiance(_numero, ramasseur_);
            } else {
                //!aPourDefenseur(numero) ==> numero == preneur ==> j == appele
                if(_numero == ramasseur_) {
                    faireConfiance(_numero, j);
                    return;
                }
                ramasseurDuPliAvecPetitNonPreneur_ = true;
                addPotentialFoePlayers(joueursNonConfiancePresqueSure_, j,
                        ramasseur_);
            }
        }
        arreterRechercheJoueurJoueCartePoint_ = false;
        for (byte j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant l'entameur du Petit
            byte ramasseur_ = -1;
            for(TrickTarot p: _plisFaits) {
                if(!p.getVuParToutJoueur()) {
                    continue;
                }
                if(j != p.getEntameur()) {
                    continue;
                }
                //le joueur j a entame
                CardTarot carte_ = p.carteDuJoueur(j);
                if(carte_ != CardTarot.petit()) {
                    continue;
                }
                //Entame du Petit par le joueur j
                ramasseur_ = p.getRamasseur(nombreJoueurs_);
                if(ramasseur_ == j) {
                    //Si le joueur j a entame et ramasse le pli avec le Petit,
                    //alors on passe au joueur suivant
                    arreterRechercheJoueurJoueCartePoint_ = true;
                }
                //ramasseur != -1 && passerAuJoueurSuivant = false
                break;
            }
            if(ramasseur_ == -1) {
                return;
            }
            if(arreterRechercheJoueurJoueCartePoint_) {
                break;
            }
            //le ramasseur du pli et le joueur du Petit (entameur) sont dans la meme equipe
            if(aPourDefenseur(_numero)) {
                if(ramasseur_ == taker || j == taker) {
                    fixConfidenceDefender(_numero, nombreJoueurs_);
                    return;
                }
                //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
                //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
                //de plus numero est un defenseur
                faireConfiance(_numero, j);
                faireConfiance(_numero, ramasseur_);
            } else {
                //!aPourDefenseur(numero) ==> numero == preneur ==> j == appele
                if(_numero == ramasseur_) {
                    faireConfiance(_numero, j);
                    return;
                }
                ramasseurDuPliAvecPetitNonPreneur_ = true;
                addPotentialFoePlayers(joueursNonConfiancePresqueSure_, j,
                        ramasseur_);
            }
        }
        arreterRechercheJoueurJoueCartePoint_ = false;
        for (byte j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
            byte ramasseur_ = -1;
            byte nombreAtoutsJouesAvantPetit_ = 0;
            boolean petitJoueDemandeAtout_ = false;
            boolean defausse_ = defausseTarot(j, _plisFaits);
            for(TrickTarot p: _plisFaits) {
                if(!p.getVuParToutJoueur()) {
                    continue;
                }
                CardTarot carte_ = p.carteDuJoueur(j);
                if(petitJoueDemandeAtout_) {
                    if(!defausse_) {
                        arreterRechercheJoueurJoueCartePoint_ = true;
                        break;
                    }
                }
                if(carte_.couleur() != Suit.TRUMP) {
                    continue;
                }
                if(!petitJoueDemandeAtout_) {
                    nombreAtoutsJouesAvantPetit_++;
                }
                if(p.couleurDemandee() != Suit.TRUMP) {
                    continue;
                }
                if(carte_ != CardTarot.petit()) {
                    continue;
                }
                if(nombreAtoutsJouesAvantPetit_ == 0) {
                    arreterRechercheJoueurJoueCartePoint_ = true;
                    break;
                }
                ramasseur_ = p.getRamasseur(nombreJoueurs_);
                if(ramasseur_ != taker) {
                    arreterRechercheJoueurJoueCartePoint_ = true;
                    break;
                }
                petitJoueDemandeAtout_ = true;
            }
            if(ramasseur_ == -1) {
                continue;
            }
            if(arreterRechercheJoueurJoueCartePoint_) {
                break;
            }
            //le ramasseur du pli et le joueur du Petit (entameur) sont dans la meme equipe
            if(aPourDefenseur(_numero)) {
                //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
                //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
                //de plus numero est un defenseur
                faireConfiance(_numero, j);
                // confiance en j
            } else {
                //!aPourDefenseur(numero) ==> numero == preneur ==> mefiance de j
                ramasseurDuPliAvecPetitNonPreneur_ = true;
                if(!joueursNonConfiancePresqueSure_.containsObj(j)) {
                    joueursNonConfiancePresqueSure_.add(j);
                }
            }
        }
        for (byte j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant les figures jouees au premier tour d'une couleur ordinaire demandee
            for(Suit c: couleursOrdinaires()) {
                HandTarot cartesCouleurJouees_ = new HandTarot();
                boolean passerCouleurSuivante_ = false;
                int nbTours_ = 0;
                byte ramasseurVirtuel_ = -1;
                for(TrickTarot p: _plisFaits) {
                    if(!p.getVuParToutJoueur()) {
                        continue;
                    }
                    Suit couleurDemandee_ = p.couleurDemandee();
                    if(couleurDemandee_ != c) {
                        continue;
                    }
                    nbTours_++;
                    CardTarot carteJouee_ = p.carteDuJoueur(j);
                    //Premier tour a la couleur demandee c
                    if(carteJouee_.couleur() != c) {
                        passerCouleurSuivante_ = true;
                        break;
                    }
                    //carteJouee est une carte de la couleur demandee au premier tour
                    if(!carteJouee_.isCharacter()) {
                        passerCouleurSuivante_ = true;
                        break;
                    }
                    //carteJouee est une figure de la couleur demandee au premier tour
                    boolean carteJoueeRamassee_ = false;
                    byte max_ = carteJouee_.strength(c);
                    for(byte j2_: p.joueursAyantJoueAvant(j)) {
                        CardTarot carteJoueeAvant_ = p.carteDuJoueur(j2_);
                        if(carteJoueeAvant_.strength(c) < max_) {
                            continue;
                        }
                        max_ = carteJoueeAvant_.strength(c);
                        ramasseurVirtuel_ = j2_;
                        carteJoueeRamassee_ = true;
                    }
                    if(!carteJoueeRamassee_) {
                        passerCouleurSuivante_ = true;
                        break;
                    }
                    cartesCouleurJouees_.ajouter(carteJouee_);
                    if(ramasseurVirtuel_ == j) {
                        passerCouleurSuivante_ = true;
                    }
                    break;
                }
                if(ramasseurVirtuel_ == -1) {
                    continue;
                }
                if(nbTours_ < 1) {
                    continue;
                }
                if(passerCouleurSuivante_) {
                    continue;
                }
                boolean autreCarteCouleurJouee_ = false;
                boolean figureJouee_ = false;
                CardTarot premiereFigureJouee_ = cartesCouleurJouees_.premiereCarte();
                for(TrickTarot p: _plisFaits) {
                    if(!p.getVuParToutJoueur()) {
                        continue;
                    }
                    CardTarot carteJouee_ = p.carteDuJoueur(j);
                    if(carteJouee_.couleur() != premiereFigureJouee_.couleur()) {
                        continue;
                    }
                    if(carteJouee_ == premiereFigureJouee_) {
                        figureJouee_ = true;
                        continue;
                    }
                    if(!figureJouee_) {
                        continue;
                    }
                    if(carteJouee_.points() > premiereFigureJouee_.points()) {
                        continue;
                    }
                    //la carte jouee est une carte inferieure a la premiere en points mais de la meme couleur
                    autreCarteCouleurJouee_ = true;
                    break;
                }
                if(!autreCarteCouleurJouee_) {
                    continue;
                }
                if(aPourDefenseur(_numero)) {
                    if(ramasseurVirtuel_ == taker || j == taker) {
                        fixConfidenceDefender(_numero, nombreJoueurs_);
                        return;
                    }
                    //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
                    //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
                    //de plus numero est un defenseur
                    faireConfiance(_numero, j);
                    faireConfiance(_numero, ramasseurVirtuel_);
                } else {
                    //!aPourDefenseur(numero) ==> numero == preneur ==> j == appele
                    if(_numero == ramasseurVirtuel_) {
                        faireConfiance(_numero, j);
                        return;
                    }
                    ramasseurDuPliAvecPetitNonPreneur_ = true;
                    addPotentialFoePlayers(joueursNonConfiancePresqueSure_, j,
                            ramasseurVirtuel_);
                }
            }

        }
        for (byte j = CustList.FIRST_INDEX; j < nombreJoueurs_; j++) {
            //boucle cherchant les figures defaussese sur demande d'atout
            for(Suit c: couleursOrdinaires()) {
                HandTarot cartesCouleurJouees_ = new HandTarot();
                int nbTours_ = 0;
                byte ramasseurVirtuel_ = -1;
                for(TrickTarot p: _plisFaits) {
                    if(!p.getVuParToutJoueur()) {
                        continue;
                    }
                    Suit couleurDemandee_ = p.couleurDemandee();
                    if(couleurDemandee_ != Suit.TRUMP) {
                        continue;
                    }
                    nbTours_++;
                    CardTarot carteJouee_ = p.carteDuJoueur(j);
                    //Premier tour d'atout
                    if(carteJouee_.couleur() != c) {
                        continue;
                    }
                    //carteJouee est une carte defaussee de la couleur c
                    if(!carteJouee_.isCharacter()) {
                        continue;
                    }
                    //carteJouee est une figure defaussee de la couleur c
                    ramasseurVirtuel_ = p.getRamasseur(nombreJoueurs_);
                    //ramasseurVirtuel != j, car une defause ne permet JAMAIS de prendre la main
                    cartesCouleurJouees_.ajouter(carteJouee_);
                    break;
                }
                if(ramasseurVirtuel_ == -1) {
                    continue;
                }
                if(nbTours_ < 1) {
                    continue;
                }
                boolean autreCarteCouleurJouee_ = false;
                boolean figureJouee_ = false;
                CardTarot premiereFigureJouee_ = cartesCouleurJouees_.premiereCarte();
                for(TrickTarot p: _plisFaits) {
                    if(!p.getVuParToutJoueur()) {
                        continue;
                    }
                    CardTarot carteJouee_ = p.carteDuJoueur(j);
                    if(carteJouee_.couleur() != premiereFigureJouee_.couleur()) {
                        continue;
                    }
                    if(carteJouee_ == premiereFigureJouee_) {
                        figureJouee_ = true;
                        continue;
                    }
                    if(!figureJouee_) {
                        continue;
                    }
                    if(carteJouee_.points() > premiereFigureJouee_.points()) {
                        continue;
                    }
                    //la carte jouee est une carte inferieure a la premiere en points mais de la meme couleur
                    autreCarteCouleurJouee_ = true;
                    break;
                }
                if(!autreCarteCouleurJouee_) {
                    continue;
                }
                if(aPourDefenseur(_numero)) {
                    if(ramasseurVirtuel_ == taker || j == taker) {
                        fixConfidenceDefender(_numero, nombreJoueurs_);
                        return;
                    }
                    //confiance de j en ramasseur, qui n'est pas le preneur ni l'appele
                    //car il serait absurde de jouer le Petit en premier sur un joueur dont l'equipe n'est pas connu
                    //de plus numero est un defenseur
                    faireConfiance(_numero, j);
                    faireConfiance(_numero, ramasseurVirtuel_);
                } else {
                    //!aPourDefenseur(numero) ==> numero == preneur ==> j == appele
                    if(_numero == ramasseurVirtuel_) {
                        faireConfiance(_numero, j);
                        return;
                    }
                    ramasseurDuPliAvecPetitNonPreneur_ = true;
                    addPotentialFoePlayers(joueursNonConfiancePresqueSure_, j,
                            ramasseurVirtuel_);
                }
            }

        }
        appelesTousConnus_ = allKnownCalledPlayers(_cartesCertaines,
                nombreJoueurs_);
        if(appelesTousConnus_) {
            determinerConfiance(_numero, nombreJoueurs_);
            return;
        }
        for(CardTarot c: calledCards) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (_cartesPossibles.getVal(c.couleur())
                        .get(joueur_).estVide()) {
                    //si le joueur ne possede pas de la couleur appele
                    if (aPourDefenseur(_numero)) {
                        faireConfiance(_numero, joueur_);
                    } else {
                        //numero == preneur
                        if(!joueursNonConfiancePresqueSure_.containsObj(joueur_)) {
                            joueursNonConfiancePresqueSure_.add(joueur_);
                        }
                    }
                }
            }
        }
        if(ramasseurDuPliAvecPetitNonPreneur_) {
            if(joueursNonConfiancePresqueSure_.size() + 2 == nombreJoueurs_ && bid.getJeuChien() == PlayingDog.WITH) {
                for(byte a: calledPlayers) {
                    faireConfiance(taker, a);
                }
            }
        }
    }

    /**
    @param _potentialFoesNearlySure
    @param _otherPlayer
    @param _leader
    */
    private static void addPotentialFoePlayers(Numbers<Byte> _potentialFoesNearlySure,
            byte _otherPlayer, byte _leader) {
        if(!_potentialFoesNearlySure.containsObj(_leader)) {
            _potentialFoesNearlySure.add(_leader);
        }
        if(!_potentialFoesNearlySure.containsObj(_otherPlayer)) {
            _potentialFoesNearlySure.add(_otherPlayer);
        }
    }

    /**
    @param _cartesCertaines
    @param _nbPlayers
    @return
    */
    private boolean allKnownCalledPlayers(
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, byte _nbPlayers) {
        boolean appelesTousConnus_;
        appelesTousConnus_ = true;
        for(CardTarot c: calledCards) {
            boolean trouve_ = false;
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nbPlayers; joueur_++) {
                if (_cartesCertaines.getVal(c.couleur())
                        .get(joueur_).contient(c)) {
                    trouve_ = true;
                    break;
                }
            }
            if(!trouve_) {
                appelesTousConnus_ = false;
                break;
            }
        }
        return appelesTousConnus_;
    }

    /**
    @param _numero
    @param _nbPlayers
    */
    private void fixConfidenceDefender(byte _numero, byte _nbPlayers) {
        //ramasseur == preneur ==> j == appele
        //j == preneur ==> ramasseur == appele
        for(byte j2_: tousJoueurs(_nbPlayers)) {
            if(!memeEquipe(_numero, j2_)) {
                continue;
            }
            faireConfiance(_numero, j2_);
        }
    }
    private void determinerConfiance(byte _numero, byte _nombreJoueurs) {
        if (!aPourDefenseur(_numero)) {
            faireConfiance(_numero, taker);
            for (byte joueur_: calledPlayers) {
                faireConfiance(_numero, joueur_);
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
                if (joueur_ != taker && !calledPlayers.containsObj(joueur_)) {
                    faireMefiance(_numero, joueur_);
                }
            }
        } else {
            faireMefiance(_numero, taker);
            for (byte joueur_: calledPlayers) {
                faireMefiance(_numero, joueur_);
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
                if (joueur_ != taker && !calledPlayers.containsObj(joueur_)) {
                    faireConfiance(_numero, joueur_);
                }
            }
        }
    }

    private void faireConfiance(byte _joueur, byte _enjoueur) {
        confidence.get(_joueur).set( _enjoueur, true);
    }

    private void faireMefiance(byte _joueur, byte _enjoueur) {
        confidence.get(_joueur).set( _enjoueur, false);
    }

    public CardTarot strategieJeuCarteUnique() {
        reason = new StringBuilder();
        CardTarot card_;
        if (progressingTrick.estVide()) {
            card_ = entame();
            return card_;
        }
        card_ = enCours();
        return card_;
    }

    private CardTarot entame() {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        byte numero_ = (byte) ((starter + progressingTrick.total()) % nombreJoueurs_);
        HandTarot mainJoueur_ = getDistribution().main(numero_);
        EnumMap<Suit,HandTarot> repartition_ = mainJoueur_.couleurs();
        HandTarot cartesJouables_ = playableCards(repartition_);
        if (cartesJouables_.total() == 1) {
            return cartesJouables_.premiereCarte();
        }
        if (existePreneur() || pasJeuMisere()) {
            return entameClassique(numero_, mainJoueur_, cartesJouables_);
        }
        /* Jeu de misere */
        return entameMiserePetite(numero_, mainJoueur_, cartesJouables_);
    }

//    private void formatBeginTrick() {
//        reason = new StringBuilder(format(SINGLE_CARD));
//    }

    private CardTarot entameClassique(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartition_ = _mainJoueur.couleurs();
        HandTarot cartesJouees_ = info_.getCartesJouees();
        HandTarot atoutsJoues_ = cartesJouees_.couleur(couleurAtout());
        boolean excuseJouee_ = cartesJouees_.contient(CardTarot.excuse());
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suites_ = info_.getSuitesTouteCouleur();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        boolean carteAppeleeJouee_ = info_.isCarteAppeleeJouee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        boolean strictMaitreAtout_ = info_.isMaitreAtout();
        boolean maitreAtout_ = maitreAtout(suites_.getVal(couleurAtout()),
                cartesJouees_, excuseJouee_, contientExcuse_);
        EnumList<Suit> couleursMaitres_ = info_.getCouleursMaitresses();
        HandTarot atoutsMaitres_ = repartition_.getVal(couleurAtout())
                .atoutsMaitres(repartitionCartesJouees_);
        EnumList<Suit> couleursAppelees_ = couleursAppelees();
        HandTarot cartesChien_;
        EnumList<Suit> couleursStrictementMaitresses_ = strictCouleursMaitres(
                suites_, repartitionCartesJouees_, cartesPossibles_, _numero);
        EnumMap<Suit,HandTarot> cartesMaitresses_ = info_.getCartesMaitresses();
        HandTarot cartesNonMaitresses_ = cartesNonMaitresses(repartition_,
                cartesMaitresses_, suites_);
        HandTarot cartesFictives_ = new HandTarot();
        cartesFictives_.ajouterCartes(getCarteAppelee());
        cartesChien_ = cartesVuesAuChien();
        if (statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        EnumList<Suit> couleursPseudosMaitres_ = couleursPseudosMaitres(
                repartition_,
                cartesPseudoMaitresses(repartition_, cartesFictives_,
                        info_.getRepartitionCartesJouees()));
        Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero, joueursNonJoue_);
        Numbers<Byte> joueursNonConfiance_ = joueursNonConfiance(_numero, joueursNonJoue_);
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        if (couleursMaitres_.size() == couleursOrdinaires().size() && strictMaitreAtout_) {
            return playWithStrongestHand(_numero, _mainJoueur, cartesJouees_,
                    plisFaits_, contientExcuse_, carteAppeleeJouee_,
                    nombreDeJoueurs_);
        }
        EnumList<Suit> couleursNonAppelees_ = couleursNonAppelees(couleursOrdinaires());
        if(maitreAtout_ && existePreneur()) {
            if(couleursPseudosMaitres_.containsAllObj(couleursAppelees_)) {
                EnumList<Suit> couleursStrictementMaitressesNonAppelees_ = couleursNonAppelees(couleursStrictementMaitresses_);
                if(couleursStrictementMaitressesNonAppelees_.containsAllObj(couleursNonAppelees_)) {
                    int nbAtoutsMaitres_ = atoutsMaitres_.total();
                    if(atoutsMaitres_.contient(CardTarot.petit())) {
                        nbAtoutsMaitres_--;
                    }
                    if(nbAtoutsMaitres_ > 0) {
                        atoutsMaitres_.trierParForceEnCours(couleurAtout());
                        return atoutsMaitres_.premiereCarte();
                    }
                    EnumList<Suit> couleurs_ = couleursLesPlusLongues(_mainJoueur, couleursAppelees_);
                    couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                    if(!couleurs_.isEmpty()) {
                        return carteCouleurAppeleeSousCarte(_mainJoueur, couleurs_.first(), calledCards);
                    }
                }
                if(couleursStrictementMaitressesNonAppelees_.size() + 1 == couleursNonAppelees_.size()) {
                    if(couleursNonAppelees(couleursMaitres_).size() == 1) {
                        int nbAtoutsMaitres_ = atoutsMaitres_.total();
                        if(atoutsMaitres_.contient(CardTarot.petit())) {
                            nbAtoutsMaitres_--;
                        }
                        if(nbAtoutsMaitres_ > 1) {
                            atoutsMaitres_.trierParForceEnCours(couleurAtout());
                            return atoutsMaitres_.premiereCarte();
                        }
                        if(nbAtoutsMaitres_ == 1) {
                            for(Suit c: couleursAppelees_) {
                                if(repartition_.getVal(c).total() == cartesMaitresses_.getVal(c).total() + 1) {
                                    atoutsMaitres_.trierParForceEnCours(couleurAtout());
                                    return atoutsMaitres_.premiereCarte();
                                }
                            }
                            EnumList<Suit> couleurs_ = couleursLesPlusLongues(_mainJoueur, couleursAppelees_);
                            couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                            if(!couleurs_.isEmpty()) {
                                return carteCouleurAppeleeSousCarte(_mainJoueur, couleurs_.first(), calledCards);
                            }
                        }
                        couleursMaitres_ = couleursLesPlusLongues(_mainJoueur, couleursMaitres_);
                        HandTarot couleurCandidate_ = repartition_.getVal(couleursMaitres_.first());
                        couleurCandidate_.trierParForceEnCours(couleursMaitres_.first());
                        return couleurCandidate_.premiereCarte();
                    }
                }
                if(couleursNonAppelees(couleursMaitres_).size() == couleursOrdinaires().size() - 1) {
                    boolean defausseTousJoueurs_ = true;
                    EqList<HandTarot> atoutsJoueurs_ = cartesPossibles_.getVal(couleurAtout());
                    Numbers<Byte> joueurs_ = new Numbers<Byte>(autresJoueurs(joueursNonJoue_,nombreDeJoueurs_));
                    for(byte joueur_: autresJoueurs(joueurs_,nombreDeJoueurs_)) {
                        if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                            defausseTousJoueurs_ = false;
                        }
                    }
                    if(defausseTousJoueurs_) {
                        EnumList<Suit> couleurs_ = couleursLesPlusLongues(_mainJoueur, couleursAppelees_);
                        couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                        if(!couleurs_.isEmpty()) {
                            return carteCouleurAppeleeSousCarte(_mainJoueur, couleurs_.first(), calledCards);
                        }
                    }
                    int nbAtoutsMaitres_ = atoutsMaitres_.total();
                    if(atoutsMaitres_.contient(CardTarot.petit())) {
                        nbAtoutsMaitres_--;
                    }
                    if(nbAtoutsMaitres_ > 0) {
                        atoutsMaitres_.trierParForceEnCours(couleurAtout());
                        return atoutsMaitres_.premiereCarte();
                    }
                    if(!couleursMaitres_.isEmpty()) {
                        couleursMaitres_ = couleursLesPlusLongues(_mainJoueur, couleursMaitres_);
                        HandTarot couleurCandidate_ = repartition_.getVal(couleursMaitres_.first());
                        couleurCandidate_.trierParForceEnCours(couleursMaitres_.first());
                        return couleurCandidate_.premiereCarte();
                    }
                    EnumList<Suit> couleurs_ = couleursLesPlusLongues(_mainJoueur, couleursAppelees_);
                    couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                    if(!couleurs_.isEmpty()) {
                        return carteCouleurAppeleeSousCarte(_mainJoueur, couleurs_.first(), calledCards);
                    }
                }
            }
        }
        HandTarot atouts_ = repartition_.getVal(Suit.TRUMP);
        if(_mainJoueur.total() == atouts_ .total() + repartition_.getVal(CardTarot.excuse().couleur()).total()) {
            //il n'y a que de l'atout (ev excuse)
            if (!contientExcuse_) {
                return jeuAtoutOffensif(_mainJoueur, cartesJouees_);
            }
            if(atouts_.total() + repartition_.getVal(CardTarot.excuse().couleur()).total() == 2) {
                if(carteAppeleeJouee_ && aucunPliAdverse(_numero,plisFaits_)) {
                    return atouts_.premiereCarte();
                }
                return CardTarot.excuse();
            }
            return jeuAtoutOffensif(_mainJoueur, cartesJouees_);
        }
        if(_mainJoueur.total() == atouts_.total() + repartition_.getVal(CardTarot.excuse().couleur()).total() + 1) {
            //une seule carte de couleur est presente
            EnumList<Suit> couleurs_ = couleursNonAtoutNonVides(_mainJoueur, couleursOrdinaires());
            CardTarot carteCouleur_ = repartition_.getVal(couleurs_.first()).premiereCarte();
            Numbers<Byte> partenaires_ = joueursPouvantCouperCouleurs(_mainJoueur, joueursConfiance_,
                    cartesPossibles_, couleurs_);
            partenaires_ = joueursPossedantAtoutMaitre(partenaires_, cartesCertaines_,
                    cartesJouees_);
            if(!partenaires_.isEmpty()) {
                return carteCouleur_;
            }
            if(couleursMaitres_.size() == couleursOrdinaires().size()) {
                if(atouts_.total() == 1 && atouts_.contient(CardTarot.petit())) {
                    return carteCouleur_;
                }
                int nbAtoutsMaitres_ = atoutsMaitres_.total();
                if(nbAtoutsMaitres_ > 0) {
                    atoutsMaitres_.trierParForceEnCours(couleurAtout());
                    return atoutsMaitres_.premiereCarte();
                }
                if(!carteCouleur_.isCharacter()) {
                    return carteCouleur_;
                }
                if(_mainJoueur.total() == 2) {
                    if (contientExcuse_) {
                        return CardTarot.excuse();
                    }
                    return carteCouleur_;
                }
                if(_mainJoueur.total() == 3) {
                    if (contientExcuse_ && atouts_.contient(CardTarot.petit())) {
                        return carteCouleur_;
                    }
                    if (contientExcuse_) {
                        return CardTarot.excuse();
                    }
                    return carteCouleur_;
                }
                if (atouts_.contient(CardTarot.petit())) {
                    return jeuAtoutSuperieurPetit(atouts_);
                }
                atouts_.trierParForceEnCours(couleurAtout());
                return atouts_.derniereCarte();
            }
            int nbAtoutsMaitres_ = atoutsMaitres_.total();
            if(nbAtoutsMaitres_ > 0) {
                if(_mainJoueur.total() == 2 && atouts_.contient(CardTarot.petit())) {
                    return carteCouleur_;
                }
                atoutsMaitres_.trierParForceEnCours(couleurAtout());
                return atoutsMaitres_.premiereCarte();
            }
            if(!joueursSusceptiblesCoupe(cartesPossibles_,carteCouleur_.couleur(),joueursNonConfiance_).isEmpty()) {
                if(atouts_.total() >= 3) {
                    atouts_.trierParForceEnCours(couleurAtout());
                    return jeuAtoutSuperieurPetit(atouts_);
                }
                if(!joueursPossedantAtoutMaitre(joueursConfiance_,
                        cartesCertaines_, cartesJouees_).isEmpty()) {
                    atouts_.trierParForceEnCours(couleurAtout());
                    return atouts_.derniereCarte();
                }
            }
            return carteCouleur_;
        }
        boolean aucuneCoupe_ = true;
        for (Suit couleur_ : couleursOrdinaires()) {
            boolean plusCartesCouleurAutres_ = true;
            Numbers<Byte> joueurs_ = new Numbers<Byte>(autresJoueurs(joueursNonJoue_,nombreDeJoueurs_));
            for(byte joueur_: autresJoueurs(joueurs_, nombreDeJoueurs_)) {
                if(cartesPossibles_.getVal(couleur_).get(joueur_).estVide()) {
                    continue;
                }
                plusCartesCouleurAutres_ = false;
                break;
            }
            if(plusCartesCouleurAutres_) {
                continue;
            }
            if(!repartition_.getVal(couleur_).estVide()) {
                continue;
            }
            aucuneCoupe_ = false;
            break;
        }
        boolean touteCouleurPossedeCarteMaitresse_ = true;
        for (Suit couleur_ : couleursOrdinaires()) {
            if(repartition_.getVal(couleur_).estVide()) {
                continue;
            }
            if(!cartesMaitresses_.getVal(couleur_).estVide()) {
                continue;
            }
            touteCouleurPossedeCarteMaitresse_ = false;
            break;
        }
        boolean atoutsTousJoues_ = false;
        if(atouts_.total() + repartitionCartesJouees_.getVal(couleurAtout()).total() == HandTarot.atoutsSansExcuse().total()) {
            atoutsTousJoues_ = true;
        }
        if(atoutsTousJoues_) {
            EnumList<Suit> notEmptySuits_ = couleursNonAtoutNonVides(_mainJoueur, couleursMaitres_);
            if(cartesNonMaitresses_.total() == 1) {
                if(!notEmptySuits_.isEmpty()) {
                    return jeuAvecCarteMaitresseSansAtout(_mainJoueur, cartesJouees_, notEmptySuits_);
                }
                return cartesNonMaitresses_.premiereCarte();
            }
            if(aucuneCoupe_) {
                if(touteCouleurPossedeCarteMaitresse_) {
                    return jeuAvecCarteMaitresseSansAtout(_mainJoueur, cartesJouees_, couleursOrdinaires());
                }
                if(!notEmptySuits_.isEmpty()) {
                    return jeuAvecCarteMaitresseSansAtout(_mainJoueur, cartesJouees_, notEmptySuits_);
                }
                if(!contientExcuse_) {
                    EnumList<Suit> couleurs_ = couleursNonAtoutNonVides(_mainJoueur, couleursOrdinaires());
                    couleurs_ = couleursSansCarteMaitresse(_mainJoueur, cartesJouees_, couleurs_);
                    if(!couleurs_.isEmpty()) {
                        couleurs_ = couleursLesPlusLongues(_mainJoueur, couleurs_);
                        return repartition_.getVal(couleurs_.first()).derniereCarte();
                    }
                    if(cartesNonMaitresses_.total() + atouts_.total() == _mainJoueur.total()) {
                        couleurs_ = couleursLesPlusLongues(_mainJoueur, couleursOrdinaires());
                        return repartition_.getVal(couleurs_.first()).derniereCarte();
                    }
                    return jeuAvecCarteMaitresseSansAtout(_mainJoueur, cartesJouees_, notEmptySuits_);
                }
                return CardTarot.excuse();
            }
            if(!notEmptySuits_.isEmpty()) {
                return jeuAvecCarteMaitresseSansAtout(_mainJoueur, cartesJouees_, notEmptySuits_);
            }
            if(touteCouleurPossedeCarteMaitresse_) {
                return jeuAvecCarteMaitresseSansAtout(_mainJoueur, cartesJouees_, couleursOrdinaires());
            }
        }
        if(!joueursPouvantPossederPetit(joueursConfiance_,
                cartesPossibles_).isEmpty()) {
            int nbAtoutsMaitres_ = atoutsMaitres_.total();
            if(nbAtoutsMaitres_ > 0) {
                atoutsMaitres_.trierParForceEnCours(couleurAtout());
                return atoutsMaitres_.premiereCarte();
            }
        }
        if(!joueursPossedantAtoutMaitre(joueursConfiance_,
                cartesCertaines_, cartesJouees_).isEmpty()) {
            if(atouts_.contient(CardTarot.petit())) {
                return CardTarot.petit();
            }
        }
        Rate moyenneAtout_ = moyenneAtout(_mainJoueur, atoutsJoues_,
                cartesPossibles_, nombreDeJoueurs_);
        if(!joueursPossedantNbAtout(joueursConfiance_,
                cartesCertaines_, moyenneAtout_).isEmpty()) {
            if(!atouts_.contient(CardTarot.petit()) && atouts_.total() >= 1) {
                return jeuAtoutOffensif(_mainJoueur, cartesJouees_);
            }
            if(atouts_.total() > moyenneAtout_.ll() && atouts_.total() >= 2) {
                return jeuAtoutOffensif(_mainJoueur, cartesJouees_);
            }
        }
        //differents cas de jeu
        if(_numero == taker || !existePreneur()) {
            return playAsTaker(_mainJoueur, _cartesJouables, repartition_,
                    cartesJouees_, atoutsJoues_, plisFaits_,
                    carteAppeleeJouee_, cartesPossibles_, cartesCertaines_,
                    couleursAppelees_, joueursNonJoue_, joueursConfiance_,
                    joueursNonConfiance_, nombreDeJoueurs_, atouts_,
                    aucuneCoupe_, touteCouleurPossedeCarteMaitresse_);
        }
        if(statutDe(_numero) == Status.CALLED_PLAYER) {
            return playAsCalledPlayer(_numero, _mainJoueur, _cartesJouables,
                    repartition_, cartesJouees_, atoutsJoues_, plisFaits_,
                    carteAppeleeJouee_, cartesPossibles_, cartesCertaines_,
                    cartesChien_, joueursNonJoue_, joueursNonConfiance_,
                    nombreDeJoueurs_, touteCouleurPossedeCarteMaitresse_);
        }
        EnumList<Suit> couleursAjouer_ = couleursOrdinaires();
        if(!carteAppeleeJouee_) {
            couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
        }
        EnumList<Suit> couleursNonVidesAjouer_ = couleursNonAtoutNonVides(_cartesJouables, couleursAjouer_);
        //Defenseur
        for(Suit c: couleursAppelees_) {
            if(!carteAppeleeJouee_ && !_cartesJouables.couleur(c).estVide()) {
                if(!appeleConnuDefenseur(_numero,cartesPossibles_)) {
                    boolean defausseTousJoueurs_ = true;
                    EqList<HandTarot> atoutsJoueurs_ = cartesPossibles_.getVal(couleurAtout());
                    for(byte joueur_: joueursNonConfiance_) {
                        if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                            defausseTousJoueurs_ = false;
                        }
                    }
                    if(!defausseTousJoueurs_) {
                        if(couleursAvecFigures(_cartesJouables,couleursOrdinaires()).containsObj(c)) {
                            if(couleursCoupeeParJoueurs(_cartesJouables,
                                    joueursNonConfiance_, cartesPossibles_,
                                    cartesCertaines_, couleursOrdinaires()).containsObj(c)) {
                                return repartition_.getVal(c).premiereCarte();
                            }
                        }
                    }
                    if(couleursAvecCartesBasses(_cartesJouables,couleursOrdinaires()).containsObj(c)) {
                        return repartition_.getVal(c).derniereCarte();
                    }
                }
            }
        }

        //ouvreur
        if(defenseOuvreur(_numero, cartesPossibles_)) {
            if(defenseOuvreurStrict(_numero, cartesPossibles_)) {
                EnumList<Suit> couleurs_ = couleursNonOuvertesNonVides(_cartesJouables,
                        plisFaits_, couleursNonVidesAjouer_);
                couleurs_ = couleursSansFigures(_cartesJouables, couleurs_);
                if(!couleurs_.isEmpty()) {
                    couleurs_ = couleursLesPlusCourtes(_cartesJouables, couleurs_);
                    couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
                    couleurs_ = couleursLesPlusBasses(_cartesJouables, couleurs_);
                    return repartition_.getVal(couleurs_.first()).derniereCarte();
                }
            }
            EnumList<Suit> couleurs_ = couleursNonOuvertesNonVides(_cartesJouables,
                    plisFaits_, couleursNonVidesAjouer_);
            couleurs_ = couleursAvecCartesBasses(_cartesJouables, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = couleursLesPlusHautes(_cartesJouables, couleurs_);
                couleurs_ = couleursLesPlusCourtes(_cartesJouables, couleurs_);
                couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = couleursNonOuvertesNonVides(_cartesJouables,
                    plisFaits_, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = couleursLesPlusBasses(_cartesJouables, couleurs_);
                couleurs_ = couleursLesPlusCourtes(_cartesJouables, couleurs_);
                couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = couleursAvecCartesBasses(_cartesJouables, couleursNonVidesAjouer_);
            couleurs_ = couleursNonOuvertesAttaque(_cartesJouables, plisFaits_,
                    joueursNonConfiance_, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = couleursLesPlusBasses(_cartesJouables, couleurs_);
                couleurs_ = couleursLesPlusCourtes(_cartesJouables, couleurs_);
                couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = couleursNonOuvertesAttaque(_cartesJouables, plisFaits_,
                    joueursNonConfiance_, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = couleursLesPlusBasses(_cartesJouables, couleurs_);
                couleurs_ = couleursLesPlusCourtes(_cartesJouables, couleurs_);
                couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            if(!couleursNonVidesAjouer_.isEmpty()) {
                couleurs_ = couleursLesPlusBasses(_cartesJouables, couleursNonVidesAjouer_);
                couleurs_ = couleursLesPlusCourtes(_cartesJouables, couleurs_);
                couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            if(!atouts_.estVide()) {
                if (atouts_.contient(CardTarot.petit())) {
                    return jeuAtoutSuperieurPetit(atouts_);
                }
                atouts_.trierParForceEnCours(couleurAtout());
                return atouts_.derniereCarte();
            }
            if(_mainJoueur.contient(CardTarot.EXCUSE)) {
                return CardTarot.EXCUSE;
            }
            couleurs_ = couleursOrdinaires();
            couleurs_ = couleursLesPlusLongues(_mainJoueur, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
            //couleur appelee
        }
        EnumList<Suit> couleurs_ = couleursOuvertes(_cartesJouables,
                plisFaits_, couleursNonVidesAjouer_);
        couleurs_ = couleursAvecCartesBasses(_cartesJouables, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesMoinsEntameesParJoueurs(plisFaits_, joueursNonConfiance_, couleurs_);
            couleurs_ = couleursLesPlusCourtes(_cartesJouables, couleurs_);
            couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
            couleurs_ = couleursLesPlusHautes(_cartesJouables, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(_cartesJouables,
                plisFaits_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesMoinsEntameesParJoueurs(plisFaits_, joueursNonConfiance_, couleurs_);
            couleurs_ = couleursLesPlusCourtes(_cartesJouables, couleurs_);
            couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
            couleurs_ = couleursLesPlusBasses(_cartesJouables, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }
        if(!couleursNonVidesAjouer_.isEmpty()) {
            couleurs_ = couleursLesPlusBasses(_cartesJouables, couleursNonVidesAjouer_);
            couleurs_ = couleursLesPlusCourtes(_cartesJouables, couleurs_);
            couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
            return repartition_.getVal(couleurs_.first()).derniereCarte();
        }

        if(!atouts_.estVide()) {
            if (atouts_.contient(CardTarot.petit())) {
                return jeuAtoutSuperieurPetit(atouts_);
            }
            atouts_.trierParForceEnCours(couleurAtout());
            return atouts_.derniereCarte();
        }
        if(_mainJoueur.contient(CardTarot.EXCUSE)) {
            return CardTarot.EXCUSE;
        }
        couleurs_ = couleursOrdinaires();
        couleurs_ = couleursLesPlusLongues(_mainJoueur, couleurs_);
        return repartition_.getVal(couleurs_.first()).derniereCarte();
        //couleur appelee

    }

    /**
    @param _player
    @param _hand
    @param _playableCards
    @param _dealing
    @param _playedCards
    @param _playedTrumps
    @param _tricks
    @param _playedCalledCard
    @param _possibleCards
    @param _sureCards
    @param _dog
    @param _playersNoPlayed
    @param _noConfidentPlayers
    @param _nbPlayers
    @param _allSuitsWithLeadCard
    @param _nbPlayersTwo
    @return
    */
    private CardTarot playAsCalledPlayer(byte _player, HandTarot _hand,
            HandTarot _playableCards, EnumMap<Suit,HandTarot> _dealing,
            HandTarot _playedCards, HandTarot _playedTrumps,
            CustList<TrickTarot> _tricks, boolean _playedCalledCard,
            EnumMap<Suit,EqList<HandTarot>> _possibleCards,
            EnumMap<Suit,EqList<HandTarot>> _sureCards, HandTarot _dog,
            Numbers<Byte> _playersNoPlayed, Numbers<Byte> _noConfidentPlayers,
            byte _nbPlayers, boolean _allSuitsWithLeadCard) {
        if(_playersNoPlayed.containsObj(taker)) {
            boolean jouerUneCouleurAppelee_ = false;
            HandTarot cartesPossedees_ = new HandTarot();
            HandTarot cartesNonPossedees_ = new HandTarot();
            for(CardTarot c: calledCards) {
                if(_hand.contient(c)) {
                    cartesPossedees_.ajouter(c);
                    continue;
                }
                cartesNonPossedees_.ajouter(c);
            }
            if(!cartesPossedees_.estVide()) {
                boolean defausseTousJoueurs_ = true;
                EqList<HandTarot> atoutsJoueurs_ = _possibleCards.getVal(couleurAtout());
                for(byte joueur_: _noConfidentPlayers) {
                    if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                        defausseTousJoueurs_ = false;
                    }
                }
                if(defausseTousJoueurs_) {
                    jouerUneCouleurAppelee_ = true;
                }
                if(couleursNonAppelees(couleursOrdinaires()).isEmpty() && !jouerAtout(_hand, _playedTrumps,
                        _possibleCards, _nbPlayers)) {
                    jouerUneCouleurAppelee_ = true;
                }
            }
            if(jouerUneCouleurAppelee_) {
                if(!cartesNonPossedees_.estVide()) {
                    EnumList<Suit> couleursCartesNonPossedees_ = new EnumList<Suit>();
                    for(CardTarot c: cartesNonPossedees_) {
                        if(couleursCartesNonPossedees_.containsObj(c.couleur())) {
                            continue;
                        }
                        couleursCartesNonPossedees_.add(c.couleur());
                    }
                    EnumList<Suit> couleurs_ = couleursNonAtoutNonVides(_hand, couleursCartesNonPossedees_);
                    if(!couleurs_.isEmpty()) {
                        couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
                        couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
                        return _dealing.getVal(couleurs_.first()).derniereCarte();
                    }
                } else {
                    return cartesPossedees_.premiereCarte();
                }
            }
        }
        EnumList<Suit> couleursAjouer_ = couleursOrdinaires();
        if(!_playedCalledCard) {
            couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
        }
        EnumList<Suit> couleursNonVidesAjouer_ = couleursNonAtoutNonVides(_playableCards, couleursAjouer_);
        EnumList<Suit> couleurs_;
        if(justeApresJoueur(_player, taker, _nbPlayers) && _playersNoPlayed.containsObj(taker)) {
            couleurs_ = couleursNonOuvertesNonVides(_hand,
                    _tricks, couleursNonVidesAjouer_);
            couleurs_ = couleursSansFigures(_hand, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
                couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = couleursNonOuvertesNonVides(_hand,
                    _tricks, couleursNonVidesAjouer_);
            couleurs_ = couleursSansCarteMaitresse(_hand, _playedCards, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
                couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
            if(jouerAtout(_hand, _playedTrumps,
                    _possibleCards, _nbPlayers) && _allSuitsWithLeadCard) {
                return jeuAtoutOffensif(_hand, _playedCards);
            }
            couleurs_ = couleursNonOuvertesNonVides(_hand,
                    _tricks, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
                couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).premiereCarte();
            }
            couleurs_ = couleursCoupeePar(_hand,
                    taker, _possibleCards,
                    _sureCards, couleursNonVidesAjouer_);
            if(!couleurs_.isEmpty()) {
                if(_possibleCards.getVal(couleurAtout()).get(taker).contient(CardTarot.petit())) {
                    couleurs_ = couleursLesPlusCourtes(_playedCards, couleurs_);
                    couleurs_ = couleursLesPlusCourtes(_hand, couleurs_);
                    couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
                    return _dealing.getVal(couleurs_.first()).derniereCarte();
                }
            }
            Numbers<Byte> partenaires_ = new Numbers<Byte>();
            partenaires_.add(taker);
            couleurs_ = couleursLesPlusEntameesParJoueurs(_tricks, partenaires_, couleursNonVidesAjouer_);
            couleurs_ = couleursLesPlusCourtes(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusCourtes(_hand, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            if(!couleurs_.isEmpty()) {
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
            HandTarot cartesPossedees_ = new HandTarot();
            for(CardTarot c: calledCards) {
                if(_hand.contient(c)) {
                    continue;
                }
                cartesPossedees_.ajouter(c);
            }
            if(!cartesPossedees_.estVide()) {
                return cartesPossedees_.premiereCarte();
            }
        }
        Numbers<Byte> defenseurs_ = joueursAvantAppeleApresPreneur(tousJoueurs(_nbPlayers),_player);
        boolean defausseTousDefenseursIntermediaire_ = true;
        EqList<HandTarot> atoutsJoueurs_ = _possibleCards.getVal(couleurAtout());
        for(byte joueur_: defenseurs_) {
            if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                defausseTousDefenseursIntermediaire_ = false;
            }
        }
        if(defausseTousDefenseursIntermediaire_
                && _possibleCards.getVal(couleurAtout()).get(taker).contient(CardTarot.petit())
                && _playersNoPlayed.containsObj(taker)) {
            couleurs_ = couleursLesPlusCourtes(_playedCards, couleursNonVidesAjouer_);
            couleurs_ = couleursLesPlusCourtes(_hand, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        Numbers<Byte> partenaires_ = new Numbers<Byte>();
        partenaires_.add(taker);
        if(apresJoueur(_player, taker, _nbPlayers) && _playersNoPlayed.containsObj(taker)) {
            couleurs_ = couleursLesPlusEntameesParJoueurs(_tricks, partenaires_, couleursNonVidesAjouer_);
            couleurs_ = couleursLesPlusCourtes(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusCourtes(_hand, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            if(!couleurs_.isEmpty()) {
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
            HandTarot cartesPossedees_ = new HandTarot();
            for(CardTarot c: calledCards) {
                if(_hand.contient(c)) {
                    continue;
                }
                cartesPossedees_.ajouter(c);
            }
            if(!cartesPossedees_.estVide()) {
                return cartesPossedees_.premiereCarte();
            }
        }
        couleurs_ = couleursEntameesParJoueurs(_tricks, partenaires_, couleursNonVidesAjouer_);
        couleurs_ = couleursSansFigures(_hand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursEntameesParJoueurs(_tricks, partenaires_, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        if(jouerAtout(_hand, _playedTrumps,
                _possibleCards, _nbPlayers) && _allSuitsWithLeadCard) {
            return jeuAtoutOffensif(_hand, _playedCards);
        }
        couleurs_ = couleursOuvertes(_hand,
                _tricks, couleursNonVidesAjouer_);
        couleurs_ = couleursSansFigures(_hand, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(_hand,
                _tricks, couleursNonVidesAjouer_);
        couleurs_ = couleursSansCarteMaitresse(_hand, _playedCards, couleurs_);
        couleurs_ = couleursCoupeePar(_hand,
                taker, _possibleCards,
                _sureCards, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(_hand,
                _tricks, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            boolean defausseTousJoueurs_ = true;
            for(byte joueur_: _noConfidentPlayers) {
                if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                    defausseTousJoueurs_ = false;
                }
            }
            if(defausseTousJoueurs_) {
                couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
                couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = couleursLesPlusHautes(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).premiereCarte();
            }
            EnumList<Suit> couleursCoupeesAdv_ = couleursCoupeeParJoueurs(_hand,
                    _noConfidentPlayers, _possibleCards,
                    _sureCards, couleurs_);
            if(!couleursCoupeesAdv_.isEmpty()) {
                couleurs_ = couleursLesPlusLongues(_hand, couleursCoupeesAdv_);
                couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusHautes(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursAvecCartesMaitressesVuesChien(_hand, _playedCards,
                _dog, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusHautes(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursNonVidesAjouer_;
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        HandTarot cartesPossedees_ = new HandTarot();
        for(CardTarot c: calledCards) {
            if(_hand.contient(c)) {
                continue;
            }
            cartesPossedees_.ajouter(c);
        }
        if(!cartesPossedees_.estVide()) {
            return cartesPossedees_.premiereCarte();
        }
        couleurs_ = couleursNonAtoutNonVides(_hand, couleursOrdinaires());
        couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
        couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
        couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
        return _dealing.getVal(couleurs_.first()).derniereCarte();
    }

    /**
    @param _hand
    @param _playableCards
    @param _dealing
    @param _playedCards
    @param _playedTrumps
    @param _tricks
    @param _playedCalledCard
    @param _possibleCards
    @param _sureCards
    @param _calledSuits
    @param _playerNoPlay
    @param _confidentPlayers
    @param _noConfidentPlayers
    @param _nbPlayers
    @param _trumpCards
    @param _noPossibleTrump
    @param _allSuitsWithLeadCard
    @param _nbPlayersTwo
    @return
    */
    private CardTarot playAsTaker(HandTarot _hand, HandTarot _playableCards,
            EnumMap<Suit,HandTarot> _dealing, HandTarot _playedCards,
            HandTarot _playedTrumps, CustList<TrickTarot> _tricks,
            boolean _playedCalledCard,
            EnumMap<Suit,EqList<HandTarot>> _possibleCards,
            EnumMap<Suit,EqList<HandTarot>> _sureCards, EnumList<Suit> _calledSuits,
            Numbers<Byte> _playerNoPlay, Numbers<Byte> _confidentPlayers,
            Numbers<Byte> _noConfidentPlayers, byte _nbPlayers,
            HandTarot _trumpCards, boolean _noPossibleTrump,
            boolean _allSuitsWithLeadCard) {
        for(Suit c: _calledSuits) {
            if(!_playedCalledCard && !_playableCards.couleur(c).estVide()) {
                //jouer la couleur appelee dans certains cas
                boolean defausseTousJoueurs_ = true;
                EqList<HandTarot> atoutsJoueurs_ = _possibleCards.getVal(couleurAtout());
                for(byte joueur_: _noConfidentPlayers) {
                    if (!(atoutsJoueurs_.get(joueur_).estVide())) {
                        defausseTousJoueurs_ = false;
                    }
                }
                if(defausseTousJoueurs_ && _allSuitsWithLeadCard) {
                    return _dealing.getVal(c).derniereCarte();
                }
                EnumList<Suit> couleurs_ = couleursNonAppelees(couleursOrdinaires());
                couleurs_ = couleursNonOuvertesNonVides(_hand,
                        _tricks, couleurs_);
                if(couleurs_.isEmpty()) {
                    if(_trumpCards.estVide() || !jouerAtout(_hand, _playedTrumps,
                            _possibleCards, _nbPlayers)) {
                        return _dealing.getVal(c).derniereCarte();
                    }
                }
            }
        }
        EnumList<Suit> couleursAjouer_ = couleursOrdinaires();
        if(!_playedCalledCard) {
            couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
        }
        EnumList<Suit> couleursNonVidesAjouer_ = couleursNonAtoutNonVides(_playableCards, couleursAjouer_);
        EnumList<Suit> couleurs_ = couleursOuvertes(_hand,
                _tricks, couleursNonVidesAjouer_);
        couleurs_ = couleursSansFigures(_hand, couleurs_);
        if(!couleurs_.isEmpty()) {
            Numbers<Byte> joueurs_ = new Numbers<Byte>(autresJoueurs(_playerNoPlay,_nbPlayers));
            EnumList<Suit> couleursPossiblementCoupees_ = couleursPouvantEtreCoupees(_hand,
                    autresJoueurs(joueurs_,_nbPlayers),
                    _possibleCards, couleurs_);
            if(!couleursPossiblementCoupees_.isEmpty()) {
                couleursPossiblementCoupees_ = couleursLesPlusLongues(_hand, couleursPossiblementCoupees_);
                couleursPossiblementCoupees_ = couleursLesPlusLongues(_playedCards, couleursPossiblementCoupees_);
                return _dealing.getVal(couleursPossiblementCoupees_.first()).derniereCarte();
            }
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursOuvertes(_hand,
                _tricks, couleursNonVidesAjouer_);
        couleurs_ = couleursSansCarteMaitresse(_hand, _playedCards, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        if(jouerAtout(_hand, _playedTrumps,
                _possibleCards, _nbPlayers)) {
            if(_allSuitsWithLeadCard) {
                return jeuAtoutOffensif(_hand, _playedCards);
            }
            if(!couleursNonVidesAjouer_.isEmpty()) {
                couleurs_ = couleursSansCarteMaitresse(_hand, _playedCards, couleursNonVidesAjouer_);
                couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
                couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
                couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
                return _dealing.getVal(couleurs_.first()).derniereCarte();
            }
        }
        couleurs_ = couleursNonOuvertesNonVides(_hand,
                _tricks, couleursNonVidesAjouer_);
        couleurs_ = couleursAvecCarteMaitresse(_hand, _playedCards, couleurs_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusHautes(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).premiereCarte();
        }
        couleurs_ = couleursNonOuvertesNonVides(_hand,
                _tricks, couleursNonVidesAjouer_);
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        if(_noPossibleTrump && !_trumpCards.contient(CardTarot.petit()) && !_trumpCards.estVide()) {
            return jeuAtoutOffensif(_hand, _playedCards);
        }
        couleurs_ = couleursAvecCartesBasses(_hand,
                couleursNonVidesAjouer_);
        couleurs_ = couleursCoupeeParJoueurs(_hand,
                _noConfidentPlayers, _possibleCards,
                _sureCards, couleurs_);
        if(!couleurs_.isEmpty()) {
            EnumList<Suit> couleursNonCoupeesJoueursConfiance_ = couleursNonCoupeeParJoueurs(_hand,
                    _confidentPlayers, _possibleCards,
                    _sureCards, couleurs_);
            if(!couleursNonCoupeesJoueursConfiance_.isEmpty()) {
                couleursNonCoupeesJoueursConfiance_ = couleursLesPlusLongues(_hand, couleursNonCoupeesJoueursConfiance_);
                couleursNonCoupeesJoueursConfiance_ = couleursLesPlusLongues(_playedCards, couleursNonCoupeesJoueursConfiance_);
                couleursNonCoupeesJoueursConfiance_ = couleursLesPlusBasses(_hand, couleursNonCoupeesJoueursConfiance_);
                return _dealing.getVal(couleursNonCoupeesJoueursConfiance_.first()).derniereCarte();
            }
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).derniereCarte();
        }
        couleurs_ = couleursNonVidesAjouer_;
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
            couleurs_ = couleursLesPlusLongues(_playedCards, couleurs_);
            couleurs_ = couleursLesPlusBasses(_hand, couleurs_);
            return _dealing.getVal(couleurs_.first()).premiereCarte();
        }
        if(!_trumpCards.estVide()) {
            if (_trumpCards.contient(CardTarot.petit())) {
                return jeuAtoutSuperieurPetit(_trumpCards);
            }
            _trumpCards.trierParForceEnCours(couleurAtout());
            return _trumpCards.derniereCarte();
        }
        if(_hand.contient(CardTarot.EXCUSE)) {
            return CardTarot.EXCUSE;
        }
        couleurs_ = couleursOrdinaires();
        couleurs_ = couleursLesPlusLongues(_hand, couleurs_);
        return _dealing.getVal(couleurs_.first()).derniereCarte();
        //couleur appelee
    }

    /**
    @param _player
    @param _hand
    @param _playedCards
    @param _tricks
    @param _containsExcuse
    @param _playedCalledCard
    @param _nbPlayers
    @return
    */
    private CardTarot playWithStrongestHand(byte _player, HandTarot _hand,
            HandTarot _playedCards, CustList<TrickTarot> _tricks,
            boolean _containsExcuse, boolean _playedCalledCard, byte _nbPlayers) {
        /*
        Cas ou le joueur entameur
        deborde les autres joueurs en
        couleurs et en atout
        */
        if (taker == _player || !existePreneur()) {
            //Preneur
            if (_containsExcuse) {
                if (_playedCalledCard) {
                    if(aucunPliAdverse(_player,_tricks)) {
                        return jeuMainMaitresse(_hand,_playedCards);
                    }
                    return CardTarot.excuse();
                }
                if(!_hand.contientCartes(getCarteAppelee())) {
                    if(plisTousFaitsParPreneurJoueur(taker,_tricks,_nbPlayers)) {
                        return jeuMainMaitresse(_hand,_playedCards);
                    }
                    return CardTarot.excuse();
                }
                Numbers<Byte> joueursRamasseurs_ = new Numbers<Byte>();
                joueursRamasseurs_.add(taker);
                if(plisTousFaitsPar(joueursRamasseurs_,_tricks,_nbPlayers)) {
                    return jeuMainMaitresse(_hand,_playedCards);
                }
                return CardTarot.excuse();
            }
            return jeuMainMaitresse(_hand,_playedCards);
        }
        if(statutDe(_player) == Status.CALLED_PLAYER) {
            //Appele
            //existeAppele
            if (_containsExcuse) {
                if(aucunPliAdverse(_player,_tricks)) {
                    return jeuMainMaitresse(_hand,_playedCards);
                }
                return CardTarot.excuse();
            }
            return jeuMainMaitresse(_hand,_playedCards);
        }
        if (_containsExcuse) {
            Numbers<Byte> joueursConfianceNumero_ = new Numbers<Byte>(joueursConfiance(_player,tousJoueurs(_nbPlayers)));
            joueursConfianceNumero_.add(_player);
            if(plisTousFaitsPar(joueursConfianceNumero_,_tricks,_nbPlayers)) {
                return jeuMainMaitresse(_hand,_playedCards);
            }
            if(confiance(_player, playerAfter(_player))) {
                return jeuMainMaitresse(_hand,_playedCards);
            }
            return CardTarot.excuse();
        }
        return jeuMainMaitresse(_hand,_playedCards);
    }
    private static HandTarot cartesNonMaitresses(EnumMap<Suit,HandTarot> _couleurs,
            EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumMap<Suit,EqList<HandTarot>> _suites) {
        HandTarot cards_ = new HandTarot();
        for (Suit couleur_ : couleursOrdinaires()) {
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

    private static EnumMap<Suit,HandTarot> cartesPseudoMaitresses(
            EnumMap<Suit,HandTarot> _couleurs, HandTarot _autresCartes,
            EnumMap<Suit,HandTarot> _playedCards) {
        EnumMap<Suit,HandTarot> suits_ = new EnumMap<Suit,HandTarot>();
        HandTarot pileBase_ = HandTarot.pileBase();
        for (Suit i : couleursOrdinaires()) {
            HandTarot cartes_ = _couleurs.getVal(i);
            HandTarot couleurTotale_ = pileBase_.couleur(i);
            HandTarot cartesJoueesOuPossedees_ = new HandTarot();
            cartesJoueesOuPossedees_.ajouterCartes(_playedCards.getVal(i));
            cartesJoueesOuPossedees_.ajouterCartes(cartes_);
            for (CardTarot autre_ : _autresCartes.couleur(i)) {
                if (!cartesJoueesOuPossedees_.contient(autre_)) {
                    cartesJoueesOuPossedees_.ajouter(autre_);
                }
            }
            cartesJoueesOuPossedees_.trierParForceEnCours(i);
            HandTarot cartesMaitresses_ = new HandTarot();
            int length_ = cartesJoueesOuPossedees_.total();
            for (byte c = CustList.FIRST_INDEX; c < length_; c++) {
                if (!CardTarot.eq(cartesJoueesOuPossedees_.carte(c),
                        couleurTotale_.carte(c))) {
                    break;
                }
                if (!cartes_.contient(cartesJoueesOuPossedees_.carte(c))) {
                    continue;
                }
                cartesMaitresses_.ajouter(cartesJoueesOuPossedees_.carte(c));
            }
            int nbLeadingCards_ = cartesMaitresses_.total();
            if (nbLeadingCards_ + nbLeadingCards_ + _playedCards.getVal(i).total() >= couleurTotale_
                    .total()) {
                for (CardTarot carte_ : cartes_) {
                    if (!cartesMaitresses_.contient(carte_)) {
                        cartesMaitresses_.ajouter(carte_);
                    }
                }
            }
            suits_.put(i,cartesMaitresses_);
        }
        return suits_;
    }

    private static Rate moyenneAtout(HandTarot _main, HandTarot _atoutsJoues,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _nombreJoueurs) {
        EqList<HandTarot> repartitionAtout_ = _cartesPossibles
                .getVal(couleurAtout());
        byte nombreDefausses_ = 0;
        for (HandTarot main_ : repartitionAtout_) {
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
    private static CardTarot jeuMainMaitresse(HandTarot _mainJoueur,HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> repartition_ = _mainJoueur.couleurs();
        HandTarot atouts_ = repartition_.getVal(couleurAtout());
        HandTarot atoutsSansPetit_ = new HandTarot();
        for(CardTarot carte_ :atouts_) {
            if(CardTarot.eq(carte_, CardTarot.petit())) {
                continue;
            }
            atoutsSansPetit_.ajouter(carte_);
        }
        if(!atoutsSansPetit_.estVide()) {
            return atoutsSansPetit_.premiereCarte();
        }
        EnumList<Suit> couleurs_ = couleursAvecCarteMaitresse(_mainJoueur,
                _cartesJouees, couleursOrdinaires());
        if(!couleurs_.isEmpty()) {
            couleurs_ = couleursLesPlusLongues(_mainJoueur,couleurs_);
            HandTarot cartesCouleur_ = repartition_.getVal(couleurs_.first());
            cartesCouleur_.trierParForceEnCours(couleurs_.first());
            return cartesCouleur_.premiereCarte();
        }
        return CardTarot.petit();
    }

    private static CardTarot jeuAvecCarteMaitresseSansAtout(HandTarot _main, HandTarot _cartesJouees, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = couleursLesPlusLongues(_main, _couleurs);
        couleurs_ = couleursLesPlusLongues(_cartesJouees, couleurs_);
        couleurs_ = couleursLesPlusHautes(_main, couleurs_);
        return _main.couleur(couleurs_.first()).premiereCarte();
    }

    private static CardTarot jeuAtoutSuperieurPetit(HandTarot _atouts) {
        for(CardTarot carte_: _atouts) {
            if(CardTarot.eq(carte_, CardTarot.petit())) {
                continue;
            }
            return carte_;
        }
        return _atouts.premiereCarte();
    }



    private CardTarot entameMiserePetite(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartition_ = _mainJoueur.couleurs();
        HandTarot cartesJouees_ = info_.getCartesJouees();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,EqList<HandTarot>> suites_ = info_.getSuitesTouteCouleur();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        HandTarot atoutsMaitres_ = repartition_.getVal(couleurAtout())
                .atoutsMaitres(repartitionCartesJouees_);

        HandTarot atouts_ = repartition_.getVal(couleurAtout());
        if (repartition_.getVal(couleurAtout()).contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        if (atouts_.total() + repartition_.getVal(CardTarot.excuse().couleur()).total() == _mainJoueur
                .total()) {
            if (atoutsMaitres_.estVide()) {
                return suites_.getVal(couleurAtout()).first().derniereCarte();
            }
            return repartition_.getVal(couleurAtout()).derniereCarte();
        }
        if (atouts_.total() == 1 && atoutsMaitres_.estVide()) {
            return repartition_.getVal(couleurAtout()).premiereCarte();
        }
        boolean coupeSure_ = false;
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        for (Suit couleur_ : couleursOrdinaires()) {
            for (byte joueur_ : joueursNonJoue_) {
                if (vaCouper(couleur_, joueur_, cartesPossibles_, cartesCertaines_)) {
                    coupeSure_ = true;
                }
            }
        }
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        if (coupeSure_) {
            for (Suit couleur_ : couleursOrdinaires()) {
                if (!repartition_.getVal(couleur_).estVide()
                        && repartition_.getVal(couleur_).premiereCarte()
                        .isCharacter()) {
                    couleurs_.add(couleur_);
                }
            }
            if (!couleurs_.isEmpty()) {
                return depouilleFigure(couleurs_, repartition_,
                        repartitionCartesJouees_);
            }
            for (Suit couleur_ : couleursOrdinaires()) {
                if (!repartition_.getVal(couleur_).estVide()) {
                    couleurs_.add(couleur_);
                }
            }
            return depouillePetiteCarte(couleurs_, repartition_,
                    repartitionCartesJouees_);
        }
        for (Suit couleur_ : couleursOrdinaires()) {
            if (!repartition_.getVal(couleur_).estVide()) {
                couleurs_.add(couleur_);
            }
        }
        if (!couleurs_.isEmpty() && !repartition_.getVal(couleurAtout()).estVide()) {
            if (atoutsMaitres_.estVide()) {
                return suites_.getVal(couleurAtout()).first().derniereCarte();
            }
            return repartition_.getVal(couleurAtout()).derniereCarte();
        }
        couleurs_ = couleursLesPlusCourtes(_mainJoueur, couleurs_);
        couleurs_ = couleursAvecLePlusPetitNbFigures(_mainJoueur, couleurs_);
        couleurs_ = couleursLesPlusBasses(_mainJoueur, couleurs_);
        couleurs_ = couleursAvecLePlusGrandNbFigures(cartesJouees_, couleurs_);
        return repartition_.getVal(couleurs_.first()).derniereCarte();
    }

    private static CardTarot depouilleFigure(EnumList<Suit> _couleurs,
            EnumMap<Suit,HandTarot> _repartition,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursLesPlusCourtes(HandTarot.reunion(_repartition), _couleurs);
        couleurs_ = couleursLesPlusHautes(HandTarot.reunion(_repartition), couleurs_);
        couleurs_ = couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        return _repartition.getVal(couleurs_.first()).premiereCarte();
    }

    private static CardTarot depouillePetiteCarte(EnumList<Suit> _couleurs,
            EnumMap<Suit,HandTarot> _repartition,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursLesPlusCourtes(HandTarot.reunion(_repartition), _couleurs);
        couleurs_ = couleursLesPlusHautes(HandTarot.reunion(_repartition), couleurs_);
        couleurs_ = couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        return _repartition.getVal(couleurs_.first()).derniereCarte();
    }

    private CardTarot entameSurExcuse() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        byte numero_ = (byte) ((starter + progressingTrick.total()) % nombreDeJoueurs_);
        HandTarot mainJoueur_ = getDistribution().main(numero_);
        EnumMap<Suit,HandTarot> repartition_ = mainJoueur_.couleurs();
        HandTarot cartesJouables_ = playableCards(repartition_);
        //cartesJouables.total() > 1
        if (existePreneur()) {
            return entameSurExcuseClassique(
                    numero_,
                    mainJoueur_,
                    cartesJouables_);
            /* Variables locales avec jeu d'equipe */
        }
        return entame();
    }

    private CardTarot entameSurExcuseClassique(
            byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartition_ = _mainJoueur.couleurs();
        HandTarot atouts_ = repartition_.getVal(couleurAtout());
        HandTarot cartesJouees_ = info_.getCartesJouees();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean carteAppeleeJouee_ = info_.isCarteAppeleeJouee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesFictives_.ajouterCartes(getCarteAppelee());
        cartesChien_ = cartesVuesAuChien();
        if (statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero, joueursNonJoue_);
        Numbers<Byte> joueursNonConfiance_ = joueursNonConfiance(_numero, joueursNonJoue_);
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        if(starter == taker) {
            if(statutDe(_numero) == Status.CALLED_PLAYER) {
                HandTarot atoutsSansPetit_ = new HandTarot();
                for(CardTarot carte_ :atouts_) {
                    if(CardTarot.eq(carte_, CardTarot.petit())) {
                        continue;
                    }
                    atoutsSansPetit_.ajouter(carte_);
                }
                if(!atoutsSansPetit_.estVide()) {
                    return atoutsSansPetit_.premiereCarte();
                }
                EnumList<Suit> couleursAjouer_ = couleursOrdinaires();
                if(!carteAppeleeJouee_) {
                    couleursAjouer_ = couleursNonAppelees(couleursAjouer_);
                }

                EnumList<Suit> couleursNonVidesAjouer_ = couleursNonAtoutNonVides(_cartesJouables, couleursAjouer_);
                EnumList<Suit> couleurs_ = couleursOuvertes(_mainJoueur,
                        plisFaits_, couleursNonVidesAjouer_);
                couleurs_ = couleursAvecCartesBasses(_mainJoueur, couleurs_);
                if(!couleurs_.isEmpty()) {
                    EnumList<Suit> couleursCoupeesAdv_ = couleursCoupeeParJoueurs(_mainJoueur,
                            joueursNonConfiance_, cartesPossibles_,
                            cartesCertaines_, couleurs_);
                    if(!couleursCoupeesAdv_.isEmpty()) {
                        couleurs_ = couleursLesPlusLongues(_mainJoueur, couleursCoupeesAdv_);
                        couleurs_ = couleursLesPlusLongues(cartesJouees_, couleurs_);
                        couleurs_ = couleursLesPlusBasses(_mainJoueur, couleurs_);
                        return repartition_.getVal(couleurs_.first()).derniereCarte();
                    }
                }
                couleurs_ = couleursNonOuvertesNonVides(_mainJoueur,
                        plisFaits_, couleursNonVidesAjouer_);
                couleurs_ = couleursAvecCarteMaitresse(_mainJoueur, cartesJouees_, couleurs_);
                if(!couleurs_.isEmpty()) {
                    couleurs_ = couleursLesPlusLongues(_mainJoueur, couleurs_);
                    couleurs_ = couleursLesPlusLongues(cartesJouees_, couleurs_);
                    couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                    return repartition_.getVal(couleurs_.first()).premiereCarte();
                }
                return entame();
            }
            //defenseur entamant sur l'excuse du preneur
            if(!existeAppele() || calledPlayers.containsObj(taker)) {
                //jeu atout s'il n'exite aucun partenaire avec un atout
                // ou si le defenseur possede le petit
                if(joueursPossedantAtout(joueursConfiance_, cartesCertaines_).isEmpty()) {
                    if(!atouts_.estVide()) {
                        return atouts_.derniereCarte();
                    }
                }
                if(atouts_.contient(CardTarot.petit())) {
                    return CardTarot.petit();
                }
                EnumList<Suit> couleursAjouer_ = couleursOrdinaires();
                EnumList<Suit> couleursNonVidesAjouer_ = couleursNonAtoutNonVides(_cartesJouables, couleursAjouer_);
                EnumList<Suit> couleurs_ = couleursNonVidesAjouer_;
                EnumList<Suit> couleursCoupees_ = couleursCoupeePar(_mainJoueur,
                        taker, cartesPossibles_,
                        cartesCertaines_, couleurs_);
                if(!couleursCoupees_.isEmpty()) {
                    couleurs_ = couleursNonTotalementJoueesEnFigures(_mainJoueur, cartesJouees_, couleursCoupees_);
                    if(!couleurs_.isEmpty()) {
                        couleurs_ = couleursLesPlusLongues(_mainJoueur, couleurs_);
                        couleurs_ = couleursLesPlusLongues(cartesJouees_, couleurs_);
                        couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                        CardTarot carteHaute_ = repartition_.getVal(couleurs_.first()).premiereCarte();
                        if(carteHaute_.isCharacter()) {
                            return carteHaute_;
                        }
                        return repartition_.getVal(couleurs_.first()).derniereCarte();
                    }
                    couleurs_ = couleursLesPlusLongues(_mainJoueur, couleursCoupees_);
                    couleurs_ = couleursLesPlusLongues(cartesJouees_, couleurs_);
                    couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                    return repartition_.getVal(couleurs_.first()).derniereCarte();
                }
                couleurs_ = couleursNonOuvertesAttaque(_mainJoueur,
                        plisFaits_, adversaires(_numero,tousJoueurs(nombreDeJoueurs_)), couleursNonVidesAjouer_);
                if(!couleurs_.isEmpty()) {
                    couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                    couleurs_ = couleursLesPlusLongues(_mainJoueur, couleurs_);
                    couleurs_ = couleursLesPlusLongues(cartesJouees_, couleurs_);
                    return repartition_.getVal(couleurs_.first()).premiereCarte();
                }
                couleurs_ = couleursLesPlusHautes(_mainJoueur, couleursNonVidesAjouer_);
                couleurs_ = couleursLesPlusLongues(_mainJoueur, couleurs_);
                couleurs_ = couleursLesPlusLongues(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).premiereCarte();
            }
            if(appeleConnuDefenseur(_numero,cartesPossibles_)) {
                //L'appele existe et est connu du defenseur courant
                EnumList<Suit> couleursAjouer_ = couleursOrdinaires();
                EnumList<Suit> couleursNonVidesAjouer_ = couleursNonAtoutNonVides(_cartesJouables, couleursAjouer_);
                EnumList<Suit> couleurs_ = couleursNonVidesAjouer_;
                boolean pasAtoutAppeles_ = true;
                for (byte a: calledPlayers) {
                    if(cartesPossibles_.getVal(couleurAtout()).get(a).estVide()) {
                        continue;
                    }
                    pasAtoutAppeles_ = false;
                    break;
                }
                if (pasAtoutAppeles_) {
                    //attention s'il existe une couleur que l'appele ne possede pas
                    //attention s'il existe une couleur avec carte rel maitre sur l'appele
                    HandTarot cartesPossiblesAppele_ = new HandTarot();
                    for (byte a: calledPlayers) {
                        for (Suit couleur_ : couleursOrdinaires()) {
                            cartesPossiblesAppele_.ajouterCartes(cartesPossibles_.getVal(couleur_).get(a));
                        }
                    }
                    couleurs_ = couleursNonAtoutAyantNbCartesInfEg(cartesPossiblesAppele_, couleurs_, 0);
                    if(!couleurs_.isEmpty()) {
                        couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                        couleurs_ = couleursLesPlusLongues(_mainJoueur, couleurs_);
                        couleurs_ = couleursLesPlusLongues(cartesJouees_, couleurs_);
                        return repartition_.getVal(couleurs_.first()).premiereCarte();
                    }
                    return entame();
                }
                couleurs_ = couleursNonOuvertesNonVides(_mainJoueur, plisFaits_, couleursNonVidesAjouer_);
                couleurs_ = couleursAvecFigures(_mainJoueur, couleurs_);
                couleurs_ = couleursAvecCarteMaitresse(_mainJoueur, cartesJouees_, couleurs_);
                if(!couleurs_.isEmpty()) {
                    couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                    couleurs_ = couleursLesPlusCourtes(_mainJoueur, couleurs_);
                    couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
                    return repartition_.getVal(couleurs_.first()).premiereCarte();
                }
                return entame();
            }
            //l'appele est a determiner
            EnumList<Suit> couleursAjouer_ = couleursOrdinaires();
            EnumList<Suit> couleursNonVidesAjouer_ = couleursNonAtoutNonVides(_cartesJouables, couleursAjouer_);
            EnumList<Suit> couleurs_ = couleursNonVidesAjouer_;
            couleurs_ = couleursNonOuvertesNonVides(_mainJoueur, plisFaits_, couleurs_);
            couleurs_ = couleursAvecFigures(_mainJoueur, couleurs_);
            couleurs_ = couleursAvecCarteMaitresse(_mainJoueur, cartesJouees_, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                couleurs_ = couleursLesPlusCourtes(_mainJoueur, couleurs_);
                couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).premiereCarte();
            }
            couleurs_ = couleursAvecFigures(_mainJoueur, couleursNonVidesAjouer_);
            couleurs_ = couleursAvecCarteMaitresse(_mainJoueur, cartesJouees_, couleurs_);
            couleurs_ = couleursCoupeePar(_mainJoueur,
                    taker, cartesPossibles_,
                    cartesCertaines_, couleurs_);
            if(!couleurs_.isEmpty()) {
                couleurs_ = couleursLesPlusHautes(_mainJoueur, couleurs_);
                couleurs_ = couleursLesPlusCourtes(_mainJoueur, couleurs_);
                couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).premiereCarte();
            }
            for(Suit c: couleursAppelees()) {
                if(!carteAppeleeJouee_ && !repartition_.getVal(c).cartesBasses(c).estVide()) {
                    return repartition_.getVal(c).derniereCarte();
                }
            }

            couleurs_ = couleursNonAppelees(couleursNonVidesAjouer_);
            if(!carteAppeleeJouee_ && !couleurs_.isEmpty()) {
                couleurs_ = couleursLesPlusBasses(_mainJoueur, couleurs_);
                couleurs_ = couleursLesPlusCourtes(_mainJoueur, couleurs_);
                couleurs_ = couleursLesPlusCourtes(cartesJouees_, couleurs_);
                return repartition_.getVal(couleurs_.first()).derniereCarte();
            }
            for(Suit c: couleursAppelees()) {
                if(!carteAppeleeJouee_ && !repartition_.getVal(c).estVide()) {
                    return repartition_.getVal(c).derniereCarte();
                }
            }

            if(!atouts_.estVide() && !atouts_.petitSec()) {
                return jeuAtoutOffensif(_mainJoueur, cartesJouees_);
            }
        }
        return entame();
    }
    private static boolean jouerAtout(HandTarot _main, HandTarot _atoutsJoues,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _nombreJoueurs) {
        EqList<HandTarot> repartitionAtout_ = _cartesPossibles
                .getVal(couleurAtout());
        byte nombreDefausses_ = 0;
        for (HandTarot main_ : repartitionAtout_) {
            if (!main_.estVide()) {
                continue;
            }
            nombreDefausses_++;
        }
        HandTarot atoutsNonJoues_ = new HandTarot();
        byte nombreJoueursAvecAtout_ = (byte) (_nombreJoueurs
                - nombreDefausses_ - 1);
        for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
            if (_main.contient(atout_)) {
                continue;
            }
            if (!_atoutsJoues.contient(atout_)) {
                atoutsNonJoues_.ajouter(atout_);
            }
        }
        if (_main.contient(CardTarot.petit())) {
            return _main.couleur(couleurAtout()).total() * nombreJoueursAvecAtout_ >= atoutsNonJoues_.total();
        }
        return _main.couleur(couleurAtout()).total() * nombreJoueursAvecAtout_ >= 2 * atoutsNonJoues_
                .total();
    }

    private static CardTarot jeuAtoutOffensif(HandTarot _mainJoueur,HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> repartition_ = _mainJoueur.couleurs();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = _cartesJouees.couleurs();
        HandTarot atoutsMaitres_ = repartition_.getVal(couleurAtout())
                .atoutsMaitres(repartitionCartesJouees_);
        if(!atoutsMaitres_.estVide()) {
            return atoutsMaitres_.premiereCarte();
        }
        EqList<HandTarot> suitesAtouts_ = repartition_.getVal(couleurAtout()).eclaterEnCours(
                repartitionCartesJouees_, couleurAtout());
        suitesAtouts_.sortElts(new GameSeqLengthTarotComparator());
        return suitesAtouts_.first().premiereCarte();
    }

    // DEBUT FONCTION IA



    private CardTarot enCours() {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        byte numero_ = (byte) ((progressingTrick.getEntameur() + progressingTrick.total()) % nombreJoueurs_);
        HandTarot mainJoueur_ = getDistribution().main(numero_);
        EnumMap<Suit,HandTarot> repartition_ = mainJoueur_.couleurs();
        HandTarot cartesJouables_ = playableCards(repartition_);
        if (cartesJouables_.total() == 1) {
            return cartesJouables_.premiereCarte();
        }
        if (progressingTrick.couleurDemandee() == Suit.UNDEFINED) {
            // Cela
            // se
            // passe
            // comme
            // une
            // entame
            // avec
            // un
            // joueur
            // en
            // moins
            /* C'est une entame sur Excuse */
            return entameSurExcuse();
        }
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        EnumMap<Suit,HandTarot> repartitionJouables_ = cartesJouables_.couleurs();
        if (existePreneur() || pasJeuMisere()) {
            if (couleursOrdinaires().containsObj(couleurDemandee_)) {
                if (!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                    return fournirCouleurOrdinaireClassique(numero_, mainJoueur_, cartesJouables_);
                }
                if (!repartitionJouables_.getVal(Suit.TRUMP).estVide()) {
                    return coupeClassique(numero_, mainJoueur_, cartesJouables_);
                }
                return defausseCouleurOrdinaireClassique(numero_, mainJoueur_, cartesJouables_);
            }
            if (!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                return fournirAtoutClassique(numero_, mainJoueur_, cartesJouables_);
            }
            return defausseAtoutClassique(numero_, mainJoueur_, cartesJouables_);
        }
        if (couleursOrdinaires().containsObj(couleurDemandee_)) {
            if (!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
                return fournirCouleurOrdinaireMisere(numero_, mainJoueur_, cartesJouables_);
            }
            if (!repartitionJouables_.getVal(Suit.TRUMP).estVide()) {
                return coupeMisere(numero_, mainJoueur_, cartesJouables_);
            }
            return defausseMisere(numero_, mainJoueur_, cartesJouables_);
        }
        if (!repartitionJouables_.getVal(couleurDemandee_).estVide()) {
            return fournirAtoutMisere(numero_, mainJoueur_, cartesJouables_);
        }
        return defausseMisere(numero_, mainJoueur_, cartesJouables_);
    }

    private CardTarot fournirCouleurOrdinaireClassique(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = info_.getCartesMaitresses();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _cartesJouables.couleurs();
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        HandTarot repartitionCouleDem_ = repartitionJouables_.getVal(couleurDemandee_);
        EqList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        boolean carteAppeleeJouee_ = info_.isCarteAppeleeJouee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        EnumList<Suit> couleursAppelees_ = couleursAppelees();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesFictives_.ajouterCartes(getCarteAppelee());
        cartesChien_ = cartesVuesAuChien();
        if (statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*CarteTarot temporairement
        maitresse*/
        PossibleTrickWinner ramasseurCertain_ = equipeQuiVaFairePli(
                info_,
                _numero,
                carteForte_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (maitreJeu_ && contientExcuse_) {
                return CardTarot.excuse();
            }
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                return CardTarot.excuse();
            }
            return carteLaPlusPetite(suites_);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            if (maitreJeu_) {
                if (contientExcuse_) {
                    return CardTarot.excuse();
                }
                if (suites_.size() == 1) {
                    return repartitionCouleDem_.premiereCarte();
                }
                if (premiereSuitePlusLongueQueTotalCouleurDemandee(
                        suites_,cartesPossibles_,couleurDemandee_,autresJoueurs(_numero))) {
                    return repartitionCouleDem_.premiereCarte();
                }
                return weakestCard(suites_);
            }
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
                Numbers<Byte> equipeNumero_ = new Numbers<Byte>();
                equipeNumero_.addAllElts(joueursConfiance_);
                equipeNumero_.add(_numero);
                if (equipeNumero_.containsAllObj(ramasseurs(plisFaits_))) {
                    if (CardTarot.eq(_cartesJouables.premiereCarte(),
                            CardTarot.excuse())) {
                        return _cartesJouables.carte(1);
                    }
                    return _cartesJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            /* couleur demandee non atout */
            if (repartitionCouleDem_.premiereCarte().isCharacter()) {
                return repartitionCouleDem_.premiereCarte();
            }
            return weakestCard(suites_);
        }
        //incertitude
        if (_mainJoueur.total() == 2 && contientExcuse_) {
            return CardTarot.excuse();
        }
        TrickTarot dernierPli_;
        Numbers<Byte> dernieresCoupes_;
        Numbers<Byte> dernieresDefausses_;
        Numbers<Byte> joueursSusceptiblesDeCouper_;
        EqList<HandTarot> cartesRelMaitres_;
        Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = joueursNonConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfianceNonJoue_ = intersectionJoueurs(joueursNonConfiance_,joueursNonJoue_);

        //fournir a la couleur demandee ordinaire
        Numbers<Byte> tours_ = tours(couleurDemandee_, plisFaits_);
        boolean joueurConfianceRamasseur_ = joueursConfiance_.containsObj(ramasseurVirtuel_);
        boolean joueurConfianceRamasseurProbaPli_ = joueurConfianceRamasseur_ &&
                joueurConfianceRamasseurProbaPli(
                        joueursNonConfianceNonJoue_,
                        couleurDemandee_,
                        cartesPossibles_,
                        carteForte_);
        if (repartitionCouleDem_.premiereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            /* Si le joueur ne peut pas prendre la main */
            if (!repartitionCouleDem_.premiereCarte().isCharacter()) {
                /*Si le joueur
                ne possede pas
                de figure*/
                if (contientExcuse_ && maitreJeu_) {
                    return CardTarot.excuse();
                }
                return carteLaPlusPetite(suites_);
            }
            if (maitreJeu_) {
                if (contientExcuse_) {
                    return CardTarot.excuse();
                }
                return carteLaPlusPetite(suites_);
            }
            /* Le joueur possede au moins une figure */
            if (tours_.isEmpty()) {
                /*
            Si cette couleur est entamee pour
            la premiere fois
            */
                if (joueurConfianceRamasseur_) {
                    if(aucunePriseMainPossibleCouleur(
                            cartesPossibles_,couleurDemandee_,
                            carteForte_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                }
                return carteLaPlusPetite(suites_);
            }
            dernierPli_ = plisFaits_.get(tours_.last());
            dernieresCoupes_ = dernierPli_.joueursCoupes();
            /* Maintenant on aborde au moins le deuxieme tour */
            if (dernieresCoupes_.isEmpty()) {
                /*
            Si le dernier pli
            n'est pas coupe a
            cette couleur
            */
                if (joueurConfianceRamasseur_) {
                    if (carteForte_.couleur() == couleurAtout()) {
                        /*
                    L'espoir fait
                    vivre
                    */
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    if(aucunePriseMainPossibleCouleur(
                            cartesPossibles_,couleurDemandee_,
                            carteForte_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                }
                return carteLaPlusPetite(suites_);
            }
            /*Maintenant on sait qu'au dernier tour le pli a ete coupe*/
            if (joueursConfiance_.containsObj(ramasseur(plisFaits_,
                    tours_.last()))) {
                if (carteForte_.couleur() == couleurAtout()) {
                    /*L'espoir fait vivre*/
                    return repartitionCouleDem_.premiereCarte();
                }
                if(aucunePriseMainPossibleCouleur(
                        cartesPossibles_,couleurDemandee_,
                        carteForte_,joueursNonConfianceNonJoue_)) {
                    return repartitionCouleDem_
                            .premiereCarte();
                }
            }
            return carteLaPlusPetite(suites_);
        }
        /* Maintenant on sait le joueur peut prendre la main */
        cartesRelMaitres_ = cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                couleurDemandee_, couleurDemandee_, cartesCertaines_,
                carteForte_);
        if (!repartitionCouleDem_.premiereCarte().isCharacter()) {
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            if (aucunePriseMainPossibleParFigure(
                    cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
                if (!cartesRelMaitres_.isEmpty()) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
            }
            if (tours_.isEmpty()) {
                return repartitionCouleDem_.premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        /* Maintenant le joueur peut prendre la main avec une figure */
        if (_numero == taker || !existePreneur()) {
            if (tours_.isEmpty()) {
                if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
                if (!couleursAppelees_.containsObj(couleurDemandee_)) {
                    if (!cartesMaitresses_.getVal(couleurDemandee_)
                            .estVide()) {
                        if (suites_.size() == 1
                                || !suites_.get(1).premiereCarte()
                                .isCharacter()) {
                            return repartitionCouleDem_
                                    .premiereCarte();
                        }
                        if (aucunePriseMainPossibleParFigure(
                                cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)
                                && (!carteForte_.isCharacter() || joueurConfianceRamasseur_)) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (carteForte_.isCharacter()) {
                            if (suites_.size() == 1
                                    || !suites_.get(1).premiereCarte().isCharacter()
                                    || !joueurConfianceRamasseurProbaPli_) {
                                return repartitionCouleDem_
                                        .premiereCarte();
                            }
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (joueurConfianceRamasseurProbaPli_) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    /* Le joueur n'a aucune cartes maitresses */
                    if (aucunePriseMainPossibleParFigure(
                            cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    return carteLaPlusPetite(suites_);
                }
                /* La couleur demandee est la couleur appelee */
                if (cartesMaitresses_.getVal(couleurDemandee_)
                        .estVide()) {
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
                return repartitionCouleDem_.premiereCarte();
            }
            /* C'est au moins le deuxieme tour */
            dernierPli_ = plisFaits_.get(tours_.last());
            dernieresDefausses_ = dernierPli_.joueursDefausses();
            joueursSusceptiblesDeCouper_ = joueursSusceptiblesCoupe(cartesPossibles_,couleurDemandee_,joueursNonJoue_);
            if (!joueursSusceptiblesDeCouper_.isEmpty()) {
                if (!intersectionJoueurs(joueursNonConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_ && contientExcuse_) {
                        return CardTarot.excuse();
                    }
                    return carteLaPlusPetite(suites_);
                }
                if (!intersectionJoueurs(joueursConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_) {
                        if (contientExcuse_) {
                            return CardTarot.excuse();
                        }
                        if (premiereSuitePlusLongueQueTotalCouleurDemandee(
                                suites_,cartesPossibles_,couleurDemandee_,autresJoueurs(_numero))) {
                            return repartitionCouleDem_
                                    .premiereCarte();
                        }
                        return carteLaPlusPetite(suites_);
                    }
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
                if (joueursConfiance_.containsObj(ramasseur(plisFaits_,
                        tours_.last()))) {
                    if (carteForte_.couleur() == couleurAtout()) {
                        /*L'espoir fait vivre*/
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    if(aucunePriseMainPossibleCouleur(
                            cartesPossibles_,couleurDemandee_,
                            carteForte_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                }
                return carteLaPlusPetite(suites_);
            }
            /* Si la coupe semble improbable */
            if (!dernieresDefausses_.isEmpty() && tours_.size() == 1) {
                if (canLeadTrick(maitreJeu_,cartesRelMaitres_)) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
                if (!couleursAppelees_.containsObj(couleurDemandee_)) {
                    if (!cartesMaitresses_.getVal(couleurDemandee_)
                            .estVide()) {
                        if (suites_.size() == 1
                                || !suites_.get(1).premiereCarte()
                                .isCharacter()) {
                            return repartitionCouleDem_
                                    .premiereCarte();
                        }
                        if (aucunePriseMainPossibleParFigure(
                                cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)
                                && (!carteForte_.isCharacter() || joueurConfianceRamasseur_)) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (carteForte_.isCharacter()) {
                            if (suites_.size() == 1
                                    || !suites_.get(1).premiereCarte().isCharacter()
                                    || !joueurConfianceRamasseurProbaPli_) {
                                return repartitionCouleDem_
                                        .premiereCarte();
                            }
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (!existePreneur()) {
                            return repartitionCouleDem_.premiereCarte();
                        }
                        if (joueurConfianceRamasseurProbaPli_) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                    }
                    /* Le joueur n'a aucune cartes maitresses */
                    if (aucunePriseMainPossibleParFigure(
                            cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    return carteLaPlusPetite(suites_);
                }
                /* La couleur demandee est la couleur appelee */
                if (cartesMaitresses_.getVal(couleurDemandee_)
                        .estVide()) {
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
                return repartitionCouleDem_.premiereCarte();
            }
            /*Le pli d'avant n'est pas defausse ou c'est au moins
            le troisieme tour*/
            if (!cartesMaitresses_.getVal(couleurDemandee_)
                    .estVide()) {
                return repartitionCouleDem_.premiereCarte();
            }
            if (!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        /* Appele */
        if (statutDe(_numero) == Status.CALLED_PLAYER) {
            if (tours_.isEmpty()) {
                if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
                HandTarot cartesChienCouleur_ = getDistribution()
                        .derniereMain().couleurs()
                        .getVal(couleurDemandee_);
                if (joueursNonJoue_.containsObj(taker)
                        && bid.getJeuChien() == PlayingDog.WITH
                        && cartesChienCouleur_
                        .nombreDeFigures() > 0) {
                        /*Si l'appele joue
                        avant le preneur*/
                    if(peutSauverFigureAppele(
                            cartesPossibles_,
                            couleurDemandee_,
                            cartesChienCouleur_,
                            joueursNonConfianceNonJoue_,
                            repartitionCouleDem_
                            .premiereCarte().isCharacter())) {
                        return jeuFigureHauteDePlusFaibleSuite(suites_);
                    }
                }
                if (!joueursNonJoue_.containsObj(taker)) {
                    /*Si l'appele
                    joue apres le preneur*/
                    if(peutSauverFigureAppele(
                            cartesPossibles_,
                            couleurDemandee_,
                            cartesChienCouleur_,
                            joueursNonConfianceNonJoue_,
                            repartitionCouleDem_
                            .premiereCarte().isCharacter())) {
                        if(!cartesRelMaitres_.isEmpty()) {
                            if (cartesRelMaitres_.size() == 1
                                    || !cartesRelMaitres_.get(1)
                                    .premiereCarte().isCharacter()) {
                                return cartesRelMaitres_.first()
                                        .premiereCarte();
                            }
                            return cartesRelMaitres_.get(1)
                                    .premiereCarte();
                        }
                    }
                }
                if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
                if (!couleursAppelees_.containsObj(couleurDemandee_)) {
                    if (!cartesMaitresses_.getVal(couleurDemandee_)
                            .estVide()) {
                        if (suites_.size() == 1
                                || !suites_.get(1).premiereCarte()
                                .isCharacter()) {
                            return repartitionCouleDem_
                                    .premiereCarte();
                        }
                        if (aucunePriseMainPossibleParFigure(
                                cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)
                                && (!carteForte_.isCharacter() || joueurConfianceRamasseur_)) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (carteForte_.isCharacter()) {
                            if (suites_.size() == 1
                                    || !suites_.get(1)
                                    .premiereCarte()
                                    .isCharacter()
                                    || !joueurConfianceRamasseurProbaPli_) {
                                return repartitionCouleDem_
                                        .premiereCarte();
                            }
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (joueurConfianceRamasseurProbaPli_) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    /* Le joueur n'a aucune cartes maitresses */
                    if (aucunePriseMainPossibleParFigure(
                            cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    return carteLaPlusPetite(suites_);
                }
                /* La couleur demandee est la couleur appelee */
                if (cartesMaitresses_.getVal(couleurDemandee_)
                        .estVide()) {
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
                return repartitionCouleDem_.premiereCarte();
            }
            dernierPli_ = plisFaits_.get(tours_.last());
            dernieresDefausses_ = dernierPli_.joueursDefausses();
            /*
            Deuxieme tour pour un appele ne coupant pas la
            couleur demandee differente de l'atout
            */
            joueursSusceptiblesDeCouper_ = joueursSusceptiblesCoupe(cartesPossibles_,couleurDemandee_,joueursNonJoue_);
            if (!joueursSusceptiblesDeCouper_.isEmpty()) {
                if (!intersectionJoueurs(joueursNonConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_ && contientExcuse_) {
                        return CardTarot.excuse();
                    }
                    return carteLaPlusPetite(suites_);
                }
                if (!intersectionJoueurs(joueursConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_) {
                        if (contientExcuse_) {
                            return CardTarot.excuse();
                        }
                        if (premiereSuitePlusLongueQueTotalCouleurDemandee(
                                suites_,cartesPossibles_,couleurDemandee_,autresJoueurs(_numero))) {
                            return repartitionCouleDem_
                                    .premiereCarte();
                        }
                        return carteLaPlusPetite(suites_);
                    }
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
                if (joueursConfiance_.containsObj(ramasseur(plisFaits_,
                        tours_.last()))) {
                    if (carteForte_.couleur() == couleurAtout()) {
                        /*
                    L'espoir fait
                    vivre
                    */
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    if(aucunePriseMainPossibleCouleur(
                            cartesPossibles_,couleurDemandee_,
                            carteForte_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                }
                return carteLaPlusPetite(suites_);
            }
            /* Si la coupe semble improbable */
            if (!dernieresDefausses_.isEmpty() && tours_.size() == 1) {
                if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
                if (!couleursAppelees_.containsObj(couleurDemandee_)) {
                    if (!cartesMaitresses_.getVal(couleurDemandee_)
                            .estVide()) {
                        if (suites_.size() == 1
                                || !suites_.get(1).premiereCarte()
                                .isCharacter()) {
                            return repartitionCouleDem_
                                    .premiereCarte();
                        }
                        if (aucunePriseMainPossibleParFigure(
                                cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)
                                && (!carteForte_.isCharacter() || joueurConfianceRamasseur_)) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (carteForte_.isCharacter()) {
                            if (suites_.size() == 1
                                    || !suites_.get(1)
                                    .premiereCarte()
                                    .isCharacter()
                                    || !joueurConfianceRamasseurProbaPli_) {
                                return repartitionCouleDem_
                                        .premiereCarte();
                            }
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        if (joueurConfianceRamasseurProbaPli_) {
                            return jeuFigureHauteDePlusFaibleSuite(suites_);
                        }
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    /* Le joueur n'a aucune cartes maitresses */
                    if (aucunePriseMainPossibleParFigure(
                            cartesPossibles_,couleurDemandee_,joueursNonConfianceNonJoue_)) {
                        return repartitionCouleDem_
                                .premiereCarte();
                    }
                    return carteLaPlusPetite(suites_);
                }
                /* La couleur demandee est la couleur appelee */
                if (cartesMaitresses_.getVal(couleurDemandee_)
                        .estVide()) {
                    return jeuFigureHauteDePlusFaibleSuite(suites_);
                }
                return repartitionCouleDem_.premiereCarte();
            }
            /*
            Le pli d'avant n'est pas defausse ou c'est au moins
            le troisieme tour
            */
            if (!cartesMaitresses_.getVal(couleurDemandee_)
                    .estVide()) {
                return repartitionCouleDem_.premiereCarte();
            }
            if (!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        /* Defenseur */
        if (tours_.isEmpty()) {
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            if (couleursAppelees_.containsObj(couleurDemandee_)) {
                boolean hauteFigureCouleurAppelee_ = true;
                boolean roiAppeleCouleur_ = false;
                for(CardTarot c: calledCards) {
                    if (c.couleur() != couleurDemandee_) {
                        continue;
                    }
                    if (c.getNomFigure() == CardChar.KING) {
                        roiAppeleCouleur_ = true;
                        break;
                    }
                }
                if (roiAppeleCouleur_) {
                    //appel d'un roi
                    hauteFigureCouleurAppelee_ = false;
                }
                if (progressingTrick.total() + 2 == nombreDeJoueurs_
                        && joueursNonJoue_.first() == taker) {
                    //le preneur joue en dernier
                    hauteFigureCouleurAppelee_ = false;
                }
                if(hauteFigureCouleurAppelee_) {
                    for(CardTarot c: calledCards) {
                        if(cartesCertaines_.getVal(couleurDemandee_)
                                .get(taker).contient(c)) {
                            hauteFigureCouleurAppelee_ = false;
                            break;
                        }
                    }
                    if (repartitionCouleDem_.premiereCarte().
                            getNomFigure() != CardChar.QUEEN) {
                        hauteFigureCouleurAppelee_ = false;
                    }
                }
                if(hauteFigureCouleurAppelee_) {
                    /*
                    Tant pis si la
                    dame du defenseur
                    se fait prendre
                    par le roi appele
                    du preneur
                    s'appelant
                    volontairement
                    */
                    return repartitionCouleDem_.premiereCarte();
                }
                if (!cartesCertaines_.getVal(couleurAtout()).get(taker).estVide()
                        && cartesPossibles_.getVal(couleurDemandee_)
                        .get(taker).estVide()) {
                    return carteLaPlusPetite(suites_);
                }
                for (byte joueur_ : joueursNonJoue_) {
                    if(cartesCertaines_.getVal(couleurAtout()).get(joueur_)
                            .estVide()) {
                        continue;
                    }
                    if(!cartesPossibles_
                            .getVal(couleurDemandee_)
                            .get(joueur_).estVide()) {
                        continue;
                    }
                    return repartitionCouleDem_
                            .premiereCarte();
                }
                return carteLaPlusPetite(suites_);
            }
            if (pasAtout(joueursNonConfianceNonJoue_,
                    cartesPossibles_)) {
                return sauveQuiPeutFigure(cartesPossibles_,
                        suites_, cartesRelMaitres_,
                        joueursNonConfianceNonJoue_,
                        couleurDemandee_);
            }
            if (!joueursNonJoue_.containsObj(taker)
                    || carteForte_.isCharacter()) {
                /*
                    Si le joueur
                    (defenseur)
                    va jouer
                    apres le
                    preneur et il
                    reste des
                    joueurs
                    susceptibles
                    d'etre
                    l'appele ou
                    il existe une
                    figure que
                    peut prendre
                    le joueur
                    */
                if (!cartesRelMaitres_.isEmpty()) {
                    if (cartesRelMaitres_.size() == 1
                            || !cartesRelMaitres_.get(1)
                            .premiereCarte().isCharacter()) {
                        return suites_.first().premiereCarte();
                    }
                    return cartesRelMaitres_.get(1)
                            .premiereCarte();
                }
                return carteLaPlusPetite(suites_);
            }
            if (!cartesRelMaitres_.isEmpty()
                    && cartesRelMaitres_.first().total() == 1
                    && repartitionCouleDem_.total() == 2) {
                if (suites_.size() == 1) {
                    return repartitionCouleDem_.premiereCarte();
                }
                if (joueurConfianceRamasseur_) {
                    boolean carteMaitresse_ = true;
                    for (byte joueur_ : joueursNonConfianceNonJoue_) {
                        boolean local_ = false;
                        if (defausse(cartesPossibles_, joueur_, couleurDemandee_)) {
                            local_ = true;
                        }
                        if (!cartesPossibles_.getVal(couleurDemandee_)
                                .get(joueur_).estVide()) {
                            if (cartesPossibles_.getVal(couleurDemandee_).get(joueur_).premiereCarte().strength(couleurDemandee_)< carteForte_.strength(couleurDemandee_)) {
                                local_ = true;
                            }
                        }
                        if (!local_) {
                            carteMaitresse_ = false;
                        }
                    }
                    if (carteMaitresse_) {
                        return repartitionCouleDem_.carte(1);
                    }
                }
                return repartitionCouleDem_.premiereCarte();
            }
            return carteLaPlusPetite(suites_);
        }
        /* Maintenant on est au moins au deuxieme tour */
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        if (couleursAppelees_.containsObj(couleurDemandee_)) {
            if (carteAppeleeJouee_) {
                if (!cartesMaitresses_.getVal(couleurDemandee_)
                        .estVide()
                        && cartesMaitresses_
                        .getVal(couleurDemandee_)
                        .premiereCarte().isCharacter()) {
                    boolean roiAppeleCouleur_ = false;
                    for(CardTarot c: calledCards) {
                        if (c.couleur() != couleurDemandee_) {
                            continue;
                        }
                        if (c.getNomFigure() == CardChar.KING) {
                            roiAppeleCouleur_ = true;
                        }
                    }
                    if (roiAppeleCouleur_) {
                        return jeuFigureHauteDePlusFaibleSuite(suites_);
                    }
                    return repartitionCouleDem_.premiereCarte();
                }
                if (pasAtout(joueursNonConfianceNonJoue_,
                        cartesPossibles_)) {
                    return sauveQuiPeutFigure(cartesPossibles_,
                            suites_, cartesRelMaitres_,
                            joueursNonConfianceNonJoue_,
                            couleurDemandee_);
                }
            }
            return carteLaPlusPetite(suites_);
        }
        if (pasAtout(joueursNonConfianceNonJoue_,
                cartesPossibles_)) {
            return sauveQuiPeutFigure(cartesPossibles_, suites_,
                    cartesRelMaitres_,
                    joueursNonConfianceNonJoue_, couleurDemandee_);
        }
        return carteLaPlusPetite(suites_);
    }

    private boolean canLeadTrick(boolean _maitreJeu, EqList<HandTarot> _cartesRelMaitres) {
        return _maitreJeu && !_cartesRelMaitres.isEmpty();
    }

    private CardTarot fournirAtoutClassique(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _cartesJouables.couleurs();
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        HandTarot repartitionCouleDem_;
        EqList<HandTarot> suites_;
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesFictives_.ajouterCartes(getCarteAppelee());
        cartesChien_ = cartesVuesAuChien();
        if (statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = equipeQuiVaFairePli(
                info_,
                _numero,
                carteForte_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (maitreJeu_ && contientExcuse_) {
                return CardTarot.excuse();
            }
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                return CardTarot.excuse();
            }
            /* La couleur demandee est atout */
            return atoutLePlusPetit(
                    repartitionJouables_.getVal(couleurAtout()).eclaterEnCours(
                            repartitionCartesJouees_, couleurDemandee_), contientExcuse_);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
                Numbers<Byte> equipeNumero_ = new Numbers<Byte>();
                equipeNumero_.addAllElts(joueursConfiance_);
                equipeNumero_.add(_numero);
                if (equipeNumero_.containsAllObj(ramasseurs(plisFaits_))) {
                    if (CardTarot.eq(_cartesJouables.premiereCarte(),
                            CardTarot.excuse())) {
                        return _cartesJouables.carte(1);
                    }
                    return _cartesJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            /* couleur demandee atout */
            //jeu obligatoire d'atout
            repartitionCouleDem_ = repartitionJouables_.getVal(couleurAtout());
            suites_ = repartitionCouleDem_
                    .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
            if (!repartitionCouleDem_.contient(CardTarot.petit())
                    || maitreJeu_) {
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            Numbers<Byte> joueursNonConfiance_ = joueursNonConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
            if (pasAtout(joueursNonConfiance_, cartesPossibles_)) {
                return atoutLePlusPetit(suites_);
            }
            return CardTarot.petit();
        }
        //incertitude du ramasseur a la couleur demandee (founiture obligatoire de la couleur demandee)
        if (_mainJoueur.total() == 2 && contientExcuse_) {
            return CardTarot.excuse();
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        boolean carteMaitresse_;
        EqList<HandTarot> cartesRelMaitres_;
        int nombrePoints_ = 0;
        Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = joueursNonConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfianceNonJoue_ = intersectionJoueurs(joueursNonConfiance_, joueursNonJoue_);

        //fournir d'un atout a la demande d'atout
        suites_ = repartitionJouables_.getVal(couleurAtout())
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
        cartesRelMaitres_ = cartesRelativementMaitreEncours(suites_,
                cartesPossibles_, joueursNonJoue_, couleurAtout(),
                couleurDemandee_, cartesCertaines_, carteForte_);
        repartitionCouleDem_ = repartitionJouables_.getVal(couleurAtout());
        if (repartitionCouleDem_.premiereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        if (CardTarot.eq(progressingTrick.premiereCarte(), CardTarot.petit())) {
            if (joueursConfiance_.containsObj(ramasseurVirtuel_) && joueursNonJoue_.isEmpty()) {
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            if (!cartesRelMaitres_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            if (peutRamasserDemandeAtout(cartesPossibles_, cartesCertaines_,
                    _numero, joueursNonJoue_, autresJoueurs(joueursNonJoue_, nombreDeJoueurs_), couleurDemandee_) && !joueursNonConfianceNonJoue_.isEmpty()) {
                return repartitionCouleDem_.premiereCarte();
            }
            return suites_.last().premiereCarte();
        }
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            if(joueursNonJoue_.isEmpty()) {
                return atoutLePlusPetit(suites_);
            }
            return cartesRelMaitres_.last().premiereCarte();
        }
        carteMaitresse_ = true;
        for (byte joueur_ : joueursNonConfianceNonJoue_) {
            if(cartesPossibles_.getVal(couleurAtout()).get(joueur_)
                    .estVide()) {
                continue;
            }
            if (!(cartesPossibles_.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurDemandee_)< carteForte_.strength(couleurDemandee_))) {
                carteMaitresse_ = false;
            }
        }
        if (carteMaitresse_
                && joueursConfiance_.containsObj(ramasseurVirtuel_)) {
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        for (CardTarot carte_ : progressingTrick) {
            if (carte_ != CardTarot.EXCUSE) {
                nombrePoints_ += carte_.points();
            }
        }
        if (nombrePoints_ > 6) {
            if (!cartesRelMaitres_.isEmpty() && !joueursNonConfianceNonJoue_.isEmpty()) {
                return cartesRelMaitres_.last().premiereCarte();
            }
            return atoutLePlusPetit(suites_);
        }
        carteMaitresse_ = true;
        for (byte joueur_ : joueursNonConfianceNonJoue_) {
            if(cartesPossibles_.getVal(couleurAtout()).get(joueur_)
                    .estVide()) {
                continue;
            }
            if (!(cartesPossibles_.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurDemandee_)< carteForte_.strength(couleurDemandee_))) {
                carteMaitresse_ = false;
            }
        }
        if (carteMaitresse_) {
            return atoutLePlusPetit(suites_);
        }
        boolean nePeutFournirJoueursNonConfiance_ = true;
        for (byte joueur_ : joueursNonConfianceNonJoue_) {
            if (cartesPossibles_.getVal(couleurAtout()).get(joueur_).estVide()) {
                nePeutFournirJoueursNonConfiance_ = false;
            }
        }
        if (nePeutFournirJoueursNonConfiance_) {
            if (!repartitionCartesJouees_.getVal(couleurAtout()).contient(CardTarot.petit())) {
                return atoutLePlusPetit(suites_);
            }
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        if (!cartesRelMaitres_.isEmpty() && !joueursNonConfianceNonJoue_.isEmpty()) {
            return cartesRelMaitres_.last().premiereCarte();
        }
        return atoutLePlusPetit(suites_, contientExcuse_);
    }
    private CardTarot coupeClassique(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {

        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _cartesJouables.couleurs();
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        HandTarot atoutsCoupe_;
        EqList<HandTarot> suites_;
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        boolean carteAppeleeJouee_ = info_.isCarteAppeleeJouee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesFictives_.ajouterCartes(getCarteAppelee());
        cartesChien_ = cartesVuesAuChien();
        if (statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }
        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = equipeQuiVaFairePli(
                info_,
                _numero,
                carteForte_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        //jouer un atout en coupe, surcoupe ou souscoupe
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (maitreJeu_ && contientExcuse_) {
                return CardTarot.excuse();
            }
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                return CardTarot.excuse();
            }
            return atoutLePlusPetit(
                    repartitionJouables_.getVal(couleurAtout()).eclaterEnCours(
                            repartitionCartesJouees_, couleurDemandee_), contientExcuse_);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
                Numbers<Byte> equipeNumero_ = new Numbers<Byte>();
                equipeNumero_.addAllElts(joueursConfiance_);
                equipeNumero_.add(_numero);
                if (equipeNumero_.containsAllObj(ramasseurs(plisFaits_))) {
                    if (CardTarot.eq(_cartesJouables.premiereCarte(),
                            CardTarot.excuse())) {
                        return _cartesJouables.carte(1);
                    }
                    return _cartesJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            /* couleur demandee atout */
            //jeu obligatoire d'atout
            atoutsCoupe_ = repartitionJouables_.getVal(couleurAtout());
            suites_ = atoutsCoupe_
                    .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);
            if (!atoutsCoupe_.contient(CardTarot.petit())
                    || maitreJeu_) {
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            boolean carteMaitresse_ = true;
            Numbers<Byte> joueursNonConfiance_ = joueursNonConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
            for (byte joueur_ : joueursNonConfiance_) {
                if (!(cartesPossibles_.getVal(couleurAtout()).get(joueur_).estVide())) {
                    carteMaitresse_ = false;
                }
            }
            if (carteMaitresse_) {
                return atoutLePlusPetit(suites_);
            }
            return CardTarot.petit();
        }
        if (_mainJoueur.total() == 2 && contientExcuse_) {
            return CardTarot.excuse();
        }
        //incertitude du ramasseur a la couleur demandee (jeu atout obligatoire de la couleur demandee)
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        Numbers<Byte> joueursJoue_ = info_.getJoueursJoue();
        boolean carteMaitresse_;
        EqList<HandTarot> cartesRelMaitres_;
        CardTarot carteHautePasAtout_;
        EnumList<Suit> coupesFranches_ = info_.getCoupesFranches();
        int nombrePoints_ = 0;
        Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = joueursNonConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfianceNonJoue_ = intersectionJoueurs(joueursNonConfiance_, joueursNonJoue_);
        Numbers<Byte> joueursConfianceNonJoue_ = intersectionJoueurs(joueursConfiance_, joueursNonJoue_);

        carteHautePasAtout_ = progressingTrick.getCartes().couleurs()
                .getVal(couleurDemandee_).premiereCarte();
        suites_ = repartitionJouables_.getVal(couleurAtout()).eclaterEnCours(
                repartitionCartesJouees_, couleurDemandee_);
        cartesRelMaitres_ = cartesRelativementMaitreEncours(
                suites_, cartesPossibles_, joueursNonJoue_,
                couleurAtout(), couleurDemandee_, cartesCertaines_,
                carteForte_);
        atoutsCoupe_ = repartitionJouables_.getVal(couleurAtout());
        Numbers<Byte> tours_ = tours(couleurDemandee_, plisFaits_);
        if (atoutsCoupe_.premiereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            /*
                Si le
                joueur ne
                peut pas
                surcouper
                */
            if (!existePreneur()) {
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            if (!atoutsCoupe_.contient(CardTarot.petit())) {
                /*
            Si
            le
            joueur
            n
            'a
            pas
            le
            Petit
            */
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            if (pasAtout(joueursNonConfiance_, cartesPossibles_)) {
                return atoutLePlusPetit(suites_);
            }
            if (tours_.isEmpty()) {
                /* Si c'est le premier tour */
                if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                    return atoutsCoupe_.derniereCarte();
                }
                return atoutLePlusPetit(suites_, contientExcuse_);
            }
            /* Deuxieme tour et plus */
            if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                if (repartitionCartesJouees_.getVal(couleurDemandee_)
                        .total() < 8
                        || progressingTrick.joueursCoupes(nombreDeJoueurs_)
                        .size() > 1) {
                    return atoutsCoupe_.derniereCarte();
                }
            }
            return atoutLePlusPetit(suites_, contientExcuse_);
        }
        if (tours_.isEmpty()) {
            /*
            Le joueur peut surcouper si le pli est deja coupe ou
            couper avec n'importe quel atout
            */
            if (!repartitionJouables_.getVal(couleurAtout()).contient(CardTarot.petit())) {
            /*
            Si
            le
            joueur
            ne
            peut
            pas
            couper
            avec
            le
            Petit
            */
                if (!contientExcuse_) {
                    if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                        return cartesRelMaitres_.last()
                                .premiereCarte();
                    }
                    return atoutLePlusPetit(suites_,
                            false);
                }
                /* Maintenant le joueur possede l'Excuse */
                if (carteHautePasAtout_.isCharacter()) {
                /*
                S'il
                existe
                une
                figure
                de la
                couleur
                demandee
                */
                    return atoutLePlusPetit(suites_);
                }
                if (couleursOrdinaires().containsObj(carteForte_.couleur())) {
                /*
                Si le joueur ne
                surcoupe pas un
                autre joueur
                alors il n'a pas
                le Petit par
                hypothese par
                (m.derniereCarte
                ().getforceJeu(couleurDemandee)>1)
                */
                    int nbChars_ = repartitionCartesJouees_
                            .getVal(couleurDemandee_)
                            .nombreDeFigures();
                    int nbFullChars_ = HandTarot.couleurComplete(couleurDemandee_).nombreDeFigures();
                    if (nbChars_ == nbFullChars_) {
                        return CardTarot.excuse();
                    }
                    if (joueursNonConfianceNonJoue_
                            .isEmpty()) {
                        return CardTarot.excuse();
                    }
                }
                if (!carteAppeleeJouee_ && existeAppele()
                        && _numero == taker
                        && !progressingTrick.contient(CardTarot.petit())) {
                    return CardTarot.excuse();
                }
                return atoutLePlusPetit(suites_);
            }
            /* Le joueur peut couper avec le Petit */
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                if (contientExcuse_) {
                    return CardTarot.excuse();
                }
                if (!cartesRelMaitres_.last()
                        .contient(CardTarot.petit())
                        || cartesRelMaitres_.last().total() > 1) {
                    return cartesRelMaitres_.last()
                            .premiereCarte();
                }
                //cartesRelMaitres.last().contient(CarteTarot.petit())
                //&& cartesRelMaitres.last().total() == 1
                // ==> cartesRelMaitres == suites
                return cartesRelMaitres_.get(
                        cartesRelMaitres_.size() - 2)
                        .premiereCarte();
            }
            if (pasAtout(joueursNonConfiance_, cartesPossibles_)) {
                return atoutLePlusPetit(suites_);
            }
            if (coupesFranches_.size() == 1) {
                if (_numero == taker || !existePreneur()) {
                    if (atoutsCoupe_.total()
                            + nombreDeJoueurs_ <= 13) {
                        return CardTarot.petit();
                    }
                    carteMaitresse_ = true;
                    for (byte joueur_ : joueursNonConfianceNonJoue_) {
                        if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                            carteMaitresse_ = false;
                        }
                    }
                    if (carteMaitresse_) {
                        return CardTarot.petit();
                    }
                    return atoutLePlusPetit(suites_);
                }
                /* Appele */
                if (statutDe(_numero) == Status.CALLED_PLAYER) {
                    return CardTarot.petit();
                }
                if (atoutsCoupe_.total() < 5) {
                    return CardTarot.petit();
                }
                carteMaitresse_ = true;
                for (byte joueur_ : joueursNonConfianceNonJoue_) {
                    if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                        carteMaitresse_ = false;
                    }
                }
                if (carteMaitresse_) {
                    return CardTarot.petit();
                }
                if (atoutsCoupe_.total() > 1
                        || !atoutsCoupe_.contient(CardTarot.petit())) {
                    return atoutLePlusPetit(suites_);
                }
                return CardTarot.petit();
            }
            carteMaitresse_ = true;
            /* Il existe au moins deux coupes franches */
            for (Suit coupe_ : coupesFranches_) {
                if (coupe_ != couleurDemandee_) {
                    if (tours(coupe_, plisFaits_).isEmpty()) {
                        carteMaitresse_ = false;
                    }
                }
            }
            if (carteMaitresse_) {
                return CardTarot.petit();
            }
            if (bid.getJeuChien() == PlayingDog.WITHOUT || bid.getJeuChien() == PlayingDog.AGAINST) {
                carteMaitresse_ = false;
                for (byte joueur_ : joueursNonConfianceNonJoue_) {
                    if (!cartesPossibles_.getVal(couleurAtout()).get(joueur_).estVide()) {
                        carteMaitresse_ = true;
                    }
                }
                if (carteMaitresse_) {
                    if (atoutsCoupe_.total() > 1
                            || !atoutsCoupe_
                            .contient(CardTarot.petit())) {
                        return atoutLePlusPetit(suites_);
                    }
                    return CardTarot.petit();
                }
            }
            carteMaitresse_ = true;
            for (byte joueur_ : joueursNonConfianceNonJoue_) {
                if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                    carteMaitresse_ = false;
                }
            }
            if (carteMaitresse_) {
                return CardTarot.petit();
            }
            if (bid.getJeuChien() == PlayingDog.WITH && _numero == taker) {
                EnumList<Suit> coupesNonJoues_;
                EnumList<Suit> couleursExclues_ = new EnumList<Suit>();
                couleursExclues_.add(couleurDemandee_);
                coupesNonJoues_ = complementaireCouleurs(coupesFranches_, couleursExclues_);
                coupesNonJoues_ = couleursNonEntamees(plisFaits_, coupesNonJoues_);
                HandTarot ecart_ = plisFaits_.first().getCartes();
                int nbCartesEcarteesCouleurDemandee_ = ecart_.couleur(couleurDemandee_).total();
                coupesNonJoues_ = couleursNonAtoutAyantNbCartesInfEg(
                        ecart_,
                        coupesNonJoues_, nbCartesEcarteesCouleurDemandee_ - 1);
                if (!coupesNonJoues_.isEmpty()) {
                    if (atoutsCoupe_.total() > 1
                            || !atoutsCoupe_
                            .contient(CardTarot.petit())) {
                        return atoutLePlusPetit(suites_);
                    }
                    return CardTarot.petit();
                }
            }
            if (atoutsCoupe_.total() + nombreDeJoueurs_ <= 15) {
                return CardTarot.petit();
            }
            if (atoutsCoupe_.total() > 1
                    || !atoutsCoupe_.contient(CardTarot.petit())) {
                return atoutLePlusPetit(suites_);
            }
            return CardTarot.petit();
        }
        /* Deuxieme tour et plus */
        if (!repartitionJouables_.getVal(couleurAtout()).contient(CardTarot.petit())) {
            if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
                return cartesRelMaitres_.last()
                        .premiereCarte();
            }
            carteMaitresse_ = true;
            for (byte joueur_ : joueursNonConfianceNonJoue_) {
                if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                    carteMaitresse_ = false;
                }
            }
            if (!carteMaitresse_) {
                for (CardTarot carte_ : progressingTrick) {
                    if (carte_ != CardTarot.EXCUSE) {
                        nombrePoints_ += carte_.points();
                    }
                }
                if (nombrePoints_ > 7) {
                    if (!cartesRelMaitres_.isEmpty()) {
                        return cartesRelMaitres_.last()
                                .premiereCarte();
                    }
                    carteMaitresse_ = false;
                    for (byte joueur_ : joueursNonJoue_) {
                        if (vaSurcouper(cartesPossibles_, cartesCertaines_, _numero, joueur_, couleurDemandee_)) {
                            carteMaitresse_ = true;
                        }
                        if (carteMaitresse_) {
                            return atoutLePlusPetit(suites_,
                                    contientExcuse_);
                        }
                    }
                    carteMaitresse_ = false;
                    for (byte joueur_ : joueursNonJoue_) {
                        boolean local_ = true;
                        for (byte joueur2_ : joueursJoue_) {
                            if (!(peutSurcouper(cartesPossibles_, joueur2_, joueur_, couleurDemandee_))) {
                                local_ = false;
                            }
                        }
                        if (local_) {
                            carteMaitresse_ = true;
                        }
                        if (carteMaitresse_) {
                            return atoutLePlusPetit(suites_,
                                    contientExcuse_);
                        }
                    }
                    return repartitionJouables_.getVal(couleurAtout()).premiereCarte();
                }
                /*
                Moins de 8 points (les points sont doubles pour
                eviter les 1/2 points)
                */
                return suites_.last().premiereCarte();
                // != CarteTarot.petit()
            }
            /*
            Il n'est pas probable qu'un joueur de non confiance
            n'ayant pas joue coupe le pli
            */
            if (carteForte_.couleur() == couleurAtout()) {
                /*
            Si le pli est deja
            coupe
            */
                if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                    return atoutLePlusPetit(suites_,
                            contientExcuse_);
                }
                return atoutLePlusPetit(suites_);
            }
            if (!carteHautePasAtout_.isCharacter()) {
                carteMaitresse_ = true;
                for (byte joueur_ : joueursNonConfianceNonJoue_) {
                    if(nePeutAvoirFigures(cartesPossibles_, joueur_, couleurDemandee_)) {
                        continue;
                    }
                    carteMaitresse_ = false;
                    break;
                }
                if (carteMaitresse_) {
                    return atoutLePlusPetit(suites_,
                            contientExcuse_);
                }
                if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                    carteMaitresse_ = true;
                    for (byte joueur_ : joueursNonConfianceNonJoue_) {
                        if(peutCouper(
                                couleurDemandee_, joueur_,
                                cartesPossibles_)) {
                            carteMaitresse_ = false;
                            break;
                        }
                        if(cartesPossibles_
                                .getVal(couleurDemandee_)
                                .get(joueur_).estVide()) {
                            continue;
                        }
                        if (!(cartesPossibles_.getVal(couleurDemandee_).get(joueur_).premiereCarte().strength(couleurDemandee_)< carteForte_.strength(couleurDemandee_))) {
                            carteMaitresse_ = false;
                        }
                    }
                    if (carteMaitresse_) {
                        return atoutLePlusPetit(suites_,
                                contientExcuse_);
                    }
                }
            }
            return atoutLePlusPetit(suites_);
        }
        /*
        Le joueur possede le Petit et c'est le duxieme tour a
        cette couleur ou plus
        */
        if (pasAtout(joueursNonConfiance_, cartesPossibles_)) {
            return atoutLePlusPetit(suites_);
        }
        if (canLeadTrick(maitreJeu_, cartesRelMaitres_)) {
            if(joueursConfianceNonJoue_.isEmpty()) {
                return atoutLePlusPetit(suites_);
            }
            return cartesRelMaitres_.last().premiereCarte();
        }
        carteMaitresse_ = true;
        for (byte joueur_ : joueursNonConfianceNonJoue_) {
            if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                carteMaitresse_ = false;
            }
        }
        if (carteMaitresse_) {
            return CardTarot.petit();
        }
        if (nombreDeJoueurs_ < 5) {
            if (tours_.size() == 1) {
                if (atoutsCoupe_.total() > 1
                        || !atoutsCoupe_.contient(CardTarot.petit())) {
                    return atoutLePlusPetit(suites_);
                }
                Numbers<Byte> joueursCoupePreTour_ = plisFaits_
                        .get(tours_.first()).joueursCoupes();
                if (intersectionJoueurs(joueursNonConfiance_, autresJoueurs(joueursCoupePreTour_, nombreDeJoueurs_)).isEmpty()) {
                    return CardTarot.petit();
                }
            }
        }
        /* Le jeu s'effectue maintenant a 5 joueurs */
        if (atoutsCoupe_.total() > 1
                || !atoutsCoupe_.contient(CardTarot.petit())) {
            return atoutLePlusPetit(suites_);
        }
        return CardTarot.petit();
    }
    private CardTarot defausseCouleurOrdinaireClassique(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = info_.getCartesMaitresses();
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesFictives_.ajouterCartes(getCarteAppelee());
        cartesChien_ = cartesVuesAuChien();
        if (statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = equipeQuiVaFairePli(
                info_,
                _numero,
                carteForte_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = info_.getSuitesTouteCouleur();
        EnumList<Suit> couleursStrictesMaitresses_ = strictCouleursMaitres(
                suitesTouteCouleur_, repartitionCartesJouees_,
                cartesPossibles_, _numero);
        EnumList<Suit> couleursNonVides_ = couleursNonAtoutNonVides(_mainJoueur, couleursOrdinaires());

        //defausse sur une couleur ordinaire
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (maitreJeu_ && contientExcuse_) {
                return CardTarot.excuse();
            }
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                return CardTarot.excuse();
            }
            return defausseCouleurDemandeeSurAdversaire(
                    suitesTouteCouleur_, repartitionCartesJouees_,
                    _mainJoueur, cartesMaitresses_,
                    couleursStrictesMaitresses_, couleurDemandee_);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
                Numbers<Byte> equipeNumero_ = new Numbers<Byte>();
                equipeNumero_.addAllElts(joueursConfiance_);
                equipeNumero_.add(_numero);
                if (equipeNumero_.containsAllObj(ramasseurs(plisFaits_))) {
                    if (CardTarot.eq(_cartesJouables.premiereCarte(),
                            CardTarot.excuse())) {
                        return _cartesJouables.carte(1);
                    }
                    return _cartesJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            return defausseCouleurDemandeeSurPartenaire(
                    suitesTouteCouleur_, repartitionCartesJouees_,
                    _mainJoueur, cartesMaitresses_,
                    couleursStrictesMaitresses_, couleurDemandee_);
        }
        if (_mainJoueur.total() == 2 && contientExcuse_) {
            return CardTarot.excuse();
        }
        //incertitude du ramasseur a la couleur demandee (defausse sur la couleur demandee ordinaire)
        Numbers<Byte> tours_ = tours(couleurDemandee_, plisFaits_);
        if (tours_.isEmpty()) {
            Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
            if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                return defausseCouleurDemandeeSurPartenaire(
                        suitesTouteCouleur_, repartitionCartesJouees_,
                        _mainJoueur, cartesMaitresses_,
                        couleursStrictesMaitresses_, couleurDemandee_);
            }
            /* Le ramasseur virtuel n'est pas un joueur de confiance */
            return defausseCouleurDemandeeSurAdversaire(
                    suitesTouteCouleur_, repartitionCartesJouees_,
                    _mainJoueur, cartesMaitresses_,
                    couleursStrictesMaitresses_, couleurDemandee_);
        }
        /*
        Au dexieme tour et au de la il est preferable de jouer une
        petite carte
        */
        EnumList<Suit> couleurs_ = couleursSansFigures(_mainJoueur, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(suitesTouteCouleur_,
                    couleurs_, _mainJoueur,
                    repartitionCartesJouees_);
        }
        return jeuPetiteCarteCouleurFigure(suitesTouteCouleur_,
                couleursNonVides_, _mainJoueur, repartitionCartesJouees_);

    }
    private CardTarot defausseAtoutClassique(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> cartesMaitresses_ = info_.getCartesMaitresses();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        boolean contientExcuse_ = info_.isContientExcuse();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        HandTarot cartesChien_;
        HandTarot cartesFictives_ = new HandTarot();
        cartesFictives_.ajouterCartes(getCarteAppelee());
        cartesChien_ = cartesVuesAuChien();
        if (statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = equipeQuiVaFairePli(
                info_,
                _numero,
                carteForte_);
        boolean maitreJeu_ = info_.isMaitreJeu();
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = info_.getSuitesTouteCouleur();
        EnumList<Suit> couleursStrictesMaitresses_ = strictCouleursMaitres(
                suitesTouteCouleur_, repartitionCartesJouees_,
                cartesPossibles_, _numero);
        EnumList<Suit> couleursNonVides_ = couleursNonAtoutNonVides(_mainJoueur, couleursOrdinaires());
        //defausse sur l'atout
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (maitreJeu_ && contientExcuse_) {
                return CardTarot.excuse();
            }
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                return CardTarot.excuse();
            }
            return defausseAtoutSurAdversaire(suitesTouteCouleur_,
                    repartitionCartesJouees_, _mainJoueur,
                    cartesMaitresses_, couleursStrictesMaitresses_);
        }
        if (ramasseurCertain_ == PossibleTrickWinner.TEAM) {
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
                Numbers<Byte> equipeNumero_ = new Numbers<Byte>();
                equipeNumero_.addAllElts(joueursConfiance_);
                equipeNumero_.add(_numero);
                if (equipeNumero_.containsAllObj(ramasseurs(plisFaits_))) {
                    if (CardTarot.eq(_cartesJouables.premiereCarte(),
                            CardTarot.excuse())) {
                        return _cartesJouables.carte(1);
                    }
                    return _cartesJouables.premiereCarte();
                }
                return CardTarot.excuse();
            }
            return defausseAtoutSurPartenaire(suitesTouteCouleur_,
                    repartitionCartesJouees_, _mainJoueur,
                    cartesMaitresses_, couleursStrictesMaitresses_);
        }
        if (_mainJoueur.total() == 2 && contientExcuse_) {
            return CardTarot.excuse();
        }
        //incertitude du ramasseur a la couleur demandee (defausse sur l'atout)
        EnumList<Suit> couleurs_ = couleursSansFigures(_mainJoueur, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(suitesTouteCouleur_,
                    couleurs_, _mainJoueur, repartitionCartesJouees_);
        }
        return jeuPetiteCarteCouleurFigure(suitesTouteCouleur_,
                couleursNonVides_, _mainJoueur, repartitionCartesJouees_);
    }
    private CardTarot fournirCouleurOrdinaireMisere(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _cartesJouables.couleurs();
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        HandTarot repartitionCouleDem_ = repartitionJouables_.getVal(couleurDemandee_);
        EqList<HandTarot> suites_ = repartitionCouleDem_
                .eclaterEnCours(repartitionCartesJouees_, couleurDemandee_);

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = equipeQuiVaFairePli(
                info_,
                _numero,
                carteForte_);
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            return repartitionCouleDem_.premiereCarte();
        }
        if (repartitionCouleDem_.derniereCarte().strength(couleurDemandee_)< carteForte_
                .strength(couleurDemandee_)) {
            return carteInfVirt(suites_, carteForte_,couleurDemandee_);
        }
        if (!repartitionCouleDem_.derniereCarte().isCharacter()) {
            return carteInfFigure(suites_);
        }
        return suites_.last().derniereCarte();
    }
    private CardTarot fournirAtoutMisere(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartitionJouables_ = _cartesJouables.couleurs();

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = equipeQuiVaFairePli(
                info_,
                _numero,
                carteForte_);
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (repartitionJouables_.getVal(couleurAtout()).contient(CardTarot.petit())) {
                return CardTarot.petit();
            }
            return repartitionJouables_.getVal(couleurAtout()).premiereCarte();
        }
        return repartitionJouables_.getVal(couleurAtout()).premiereCarte();
    }
    private CardTarot coupeMisere(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _cartesJouables.couleurs();
        HandTarot trumps_ = repartitionJouables_.getVal(Suit.TRUMP);
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = info_.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = info_.getCartesCertaines();
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = equipeQuiVaFairePli(
                info_,
                _numero,
                carteForte_);
        if (ramasseurCertain_ == PossibleTrickWinner.FOE_TEAM) {
            if (repartitionJouables_.getVal(couleurAtout()).contient(CardTarot.petit())) {
                return CardTarot.petit();
            }
            return repartitionJouables_.getVal(couleurAtout()).premiereCarte();
        }
        boolean surcoupeSure_ = false;
        for (byte joueur_ : joueursNonJoue_) {
            if (vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_)) {
                surcoupeSure_ = true;
            }
        }
        if (surcoupeSure_
                && trumps_.contient(CardTarot.petit())) {
            return CardTarot.petit();
        }
        if (tours(couleurDemandee_, plisFaits_).isEmpty()) {
            return trumps_.premiereCarte();
        }
        surcoupeSure_ = false;
        byte valeur_ = 0;
        for (byte joueur_ : joueursNonJoue_) {
            if (vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_)) {
                surcoupeSure_ = true;
            }
            if (surcoupeSure_
                    && cartesCertaines_.getVal(couleurAtout()).get(joueur_).premiereCarte()
                    .strength(couleurDemandee_)> valeur_) {
                valeur_ = cartesCertaines_.getVal(couleurAtout()).get(joueur_)
                        .premiereCarte().strength(couleurDemandee_);
            }
        }
        if (surcoupeSure_) {
            if (trumps_.derniereCarte().strength(couleurDemandee_)< valeur_) {
                byte carteAJouer_ = CustList.INDEX_NOT_FOUND_ELT;
                byte lastIndex_ = (byte) (trumps_.total() - 1);
                for (byte indiceCarte_ = lastIndex_; indiceCarte_ >= CustList.FIRST_INDEX; indiceCarte_--) {
                    if (trumps_.carte(indiceCarte_)
                            .strength(couleurDemandee_)< valeur_) {
                        carteAJouer_++;
                    } else {
                        break;
                    }
                }
                return trumps_.carte(carteAJouer_);
            }
            return trumps_.derniereCarte();
        }
        return trumps_.premiereCarte();
    }
    private CardTarot defausseMisere(byte _numero,
            HandTarot _mainJoueur,
            HandTarot _cartesJouables) {
        TarotInfoPliEnCours info_ = initInformations(_numero, _mainJoueur, _cartesJouables);
        EnumMap<Suit,HandTarot> repartition_ = _mainJoueur.couleurs();
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = info_.getRepartitionCartesJouees();
        EnumMap<Suit,HandTarot> repartitionJouables_ = _cartesJouables.couleurs();
        CustList<TrickTarot> plisFaits_ = info_.getPlisFaits();

        EnumList<Suit> couleurs_ = couleursNonAtoutNonVides(_mainJoueur, couleursOrdinaires());
        boolean contFigureTtClr_ = true;
        for (Suit couleur_ : couleurs_) {
            if (!(repartition_.getVal(couleur_).premiereCarte().isCharacter())) {
                contFigureTtClr_ = false;
            }
        }
        if (contFigureTtClr_ && !couleurs_.isEmpty()) {
            return depouilleFigureEnCours(repartitionJouables_, couleurs_,
                    repartitionCartesJouees_);
        }
        contFigureTtClr_ = true;
        for (Suit couleur_ : couleursOrdinaires()) {
            if (!repartition_.getVal(couleur_).estVide() && repartitionCartesJouees_.getVal(couleur_).total() >= HandTarot.couleurComplete(couleur_).total()) {
                contFigureTtClr_ = false;
            }
            if (repartition_.getVal(couleur_).estVide() && repartitionCartesJouees_.getVal(couleur_).total() < HandTarot.couleurComplete(couleur_).total()) {
                contFigureTtClr_ = false;
            }
        }
        if (contFigureTtClr_) {
            EnumList<Suit> couleursNonEntamees_ = couleursNonOuvertesNonVides(_mainJoueur, plisFaits_, couleursOrdinaires());
            couleursNonEntamees_ = couleursNonAtoutAyantNbCartesInfEg(_mainJoueur, couleursNonEntamees_, 1);
            couleursNonEntamees_ = couleursNonAtoutNonVides(_mainJoueur, couleursNonEntamees_);
            if (!couleursNonEntamees_.isEmpty()) {
                return singletonFortDepouille(repartition_,
                        couleursNonEntamees_);
            }
            couleursNonEntamees_ = couleursNonAtoutAyantNbCartesInfEg(_mainJoueur, couleurs_, 1);
            couleursNonEntamees_ = couleursNonAtoutNonVides(_mainJoueur, couleursNonEntamees_);
            if (!couleursNonEntamees_.isEmpty()) {
                return singletonFortDepouille(repartition_,
                        couleursNonEntamees_);
            }
        }
        EnumList<Suit> couleursAvecFigures_ = couleursAvecFigures(_mainJoueur, couleurs_);
        if (!couleursAvecFigures_.isEmpty()) {
            return depouilleFigureDefausse(repartition_,
                    couleursAvecFigures_, repartitionCartesJouees_);
        }
        return enCoursMiserePetite(couleurs_, repartition_,
                repartitionCartesJouees_);
    }
    private TarotInfoPliEnCours initInformations(
            byte _joueurCourant,
            HandTarot _cartes,
            HandTarot _cartesJouables) {
        EnumMap<Suit,HandTarot> repartition_ = _cartes.couleurs();
        Numbers<Byte> joueursNonJoue_ = joueursNAyantPasJoue(_joueurCourant);
        CustList<TrickTarot> plisFaits_ = unionPlis(false);
        HandTarot cartesJouees_ = cartesJoueesEnCours(_joueurCourant);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = cartesJouees_.couleurs();
        boolean carteAppeleeJouee_ = carteAppeleeJouee(cartesJouees_);
        boolean contientExcuse_ = _cartesJouables.contient(CardTarot.excuse());
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        Numbers<Byte> joueursJoue_ = autresJoueurs(joueursNonJoue_, nombreDeJoueurs_);
        joueursJoue_.removeObj(_joueurCourant);
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = cartesPossibles(
                !repartitionCartesJouees_.getVal(CardTarot.EXCUSE.couleur()).estVide(),
                repartitionCartesJouees_, plisFaits_, contientExcuse_,
                _cartes.couleurs(), _joueurCourant,
                carteAppeleeJouee_);
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> hypotheses_ = cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        byte ramasseurVirtuel_ = progressingTrick.getRamasseur(nombreDeJoueurs_);
        EnumMap<Suit,EqList<HandTarot>> suitesTouteCouleur_ = new EnumMap<Suit,EqList<HandTarot>>();

        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        if(couleurDemandee_ == Suit.UNDEFINED) {
            suitesTouteCouleur_.put(CardTarot.EXCUSE.couleur(),repartition_.getVal(CardTarot.excuse().couleur()).eclaterEnCours(
                    repartitionCartesJouees_, CardTarot.EXCUSE.couleur()));
            suitesTouteCouleur_.put(Suit.TRUMP,repartition_.getVal(couleurAtout()).eclaterEnCours(
                    repartitionCartesJouees_, Suit.TRUMP));
            for (Suit i : couleursOrdinaires()) {
                suitesTouteCouleur_.put(i,repartition_.getVal(i).eclaterEnCours(
                        repartitionCartesJouees_, i));
                //les couleurs sont classees comme si elles etaient demandees
            }
        } else {
            suitesTouteCouleur_.put(CardTarot.EXCUSE.couleur(),repartition_.getVal(CardTarot.excuse().couleur()).eclaterEnCours(
                    repartitionCartesJouees_, couleurDemandee_));
            suitesTouteCouleur_.put(Suit.TRUMP,repartition_.getVal(couleurAtout()).eclaterEnCours(
                    repartitionCartesJouees_, couleurDemandee_));
            for (Suit i : couleursOrdinaires()) {
                suitesTouteCouleur_.put(i,repartition_.getVal(i).eclaterEnCours(
                        repartitionCartesJouees_, i));
                //les couleurs sont classees comme si elles etaient demandees
            }
        }


        boolean maitreAtout_ = strictMaitreAtout(
                cartesPossibles_,
                cartesPossibles_.getVal(CardTarot.excuse().couleur()), _joueurCourant,
                suitesTouteCouleur_.getVal(couleurAtout()), cartesJouees_);
        EnumList<Suit> couleursMaitresses_ = couleursMaitres(
                suitesTouteCouleur_, cartesJouees_,
                cartesPossibles_, _joueurCourant);
        EnumMap<Suit,HandTarot> cartesMaitresses_ = cartesMaitresses(
                repartition_, repartitionCartesJouees_);
        boolean maitreJeu_ = maitreAtout_ && couleursMaitresses_.size() == couleursOrdinaires().size();
        EnumList<Suit> coupesFranches_ = coupesFranchesStrictes(plisFaits_,
                repartition_, _joueurCourant);

        TarotInfoPliEnCours info_ = new TarotInfoPliEnCours();
        info_.setJoueursNonJoue(joueursNonJoue_);
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
        //depend de partie et de cartesJouables
        //carteEntamee
        //joueursNonJoue
        //joueursConfianceNonJoue
        //joueursNonConfianceNonJoue
        //CustList<PliTarot> plisFaits
        //CustList<Byte> joueursJoue
        return info_;
    }
    public byte getEntameur() {
        return starter;
    }

    /**
    Renvoie vrai si et seulement si la carte appelee n'existe pas pour cette
    partie ou si la carte appelee existe et est jouee
    */
    public boolean carteAppeleeJouee(HandTarot _cartesJouees) {
        if (!existeCarteAppelee()) {
            return true;
        }
        return _cartesJouees.contientCartes(calledCards);
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
    private static boolean strictMaitreAtout(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EqList<HandTarot> _excusePossible, byte _numero,
            EqList<HandTarot> _suites, HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Suit couleurAtout_ = couleurAtout();
        int nbTotalAtouts_ = HandTarot.atoutsSansExcuse().total();
        int max_ = CustList.SIZE_EMPTY;
        /*
        max designe le nombre maximal de cartes probablement
        possedees par un joueur
        */
        EqList<HandTarot> possibleTrumps_ = _cartesPossibles.getVal(couleurAtout_);
        int nbPlayers_ = possibleTrumps_.size() - 1;
        for (int joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers_; joueur_++) {
            // La taille de atoutsPossibles
            // correspond au nombre de joueurs+1
            if (joueur_ == _numero) {
                continue;
            }
            if (possibleTrumps_.get(joueur_).total()
                    + _excusePossible.get(joueur_).total() <= max_) {
                continue;
            }
            max_ = possibleTrumps_.get(joueur_).total()
                    + _excusePossible.get(joueur_).total();
        }
        /*
        Fin for int joueur=0;joueur<cartesPossibles.get(couleurAtout()).size()-1;joueur++
        (Fin boucle sur le calcul de la valeur maximale possible des atouts
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

    /**
    Est vrai si et seulement si le nombre
    2 x nb_atout_maitres_joueur+3 x nb_atout_non_maitres_joueur/2+atouts_jouees
    depasse strictement 21
    */
    private static boolean maitreAtout(EqList<HandTarot> _suites,
            HandTarot _cartesJouees, boolean _excuseJouee, boolean _excuse) {
        int maitres_;
        int nonmaitres_;
        int nb_;
        CardTarot c;
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Suit couleurAtout_ = couleurAtout();
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
        if (_excuseJouee) {
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

    /**
    Retourne l'ensemble des cartes des couleurs (avec l'Excuse) probablement
    possedees par les autres joueurs Pour premier indice (premier get)
    couleur, deuxieme indice joueur
    @param _numero
    */
    public EnumMap<Suit,EqList<HandTarot>> cartesPossibles(boolean _excuseJouee,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            CustList<TrickTarot> _plisFaits, boolean _joueurExcuse,
            EnumMap<Suit,HandTarot> _cartesJoueur, byte _numero,
            boolean _carteAppeleeJouee) {
        EnumMap<Suit,EqList<HandTarot>> m = new EnumMap<Suit,EqList<HandTarot>>();
        EqList<HandTarot> possibleExcuse_ = new EqList<HandTarot>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            possibleExcuse_.add(new HandTarot());
            if (_joueurExcuse) {
                possibleExcuse_.last().ajouter(CardTarot.excuse());
                continue;
            }
            if (!_excuseJouee) {
                possibleExcuse_.last().ajouter(CardTarot.excuse());
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.POINT)
                    || declaresMiseres.get(joueur_).containsObj(Miseres.TRUMP)) {
                possibleExcuse_.get(joueur_).supprimerCartes();
            }
        }
        possibleExcuse_.add(new HandTarot());
        if (bid.getJeuChien() != PlayingDog.WITH) {
            if (!_excuseJouee && !_joueurExcuse) {
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
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ == _numero) {
                    continue;
                }
                if (taker != joueur_) {
                    // L'Excuse du chien (si il est vu) ne
                    // peut etre possedee que par le preneur
                    if (!possibleExcuse_.get(joueur_).estVide()
                            && getDistribution().derniereMain().contient(
                                    CardTarot.excuse())) {
                        possibleExcuse_.get(joueur_).jouer(CardTarot.excuse());
                    }
                }
            }
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            // L'Excuse dans
            // une poignee
            // annule toute
            // possibilite
            // qu'un autre
            // joueur ait
            // celle-ci
            if (joueur_ == _numero) {
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
            if (progressingTrick.contient(CardTarot.excuse())) {
                possibleExcuse_.get(joueur_).supprimerCartes();
            }
        }
        if (bid.getJeuChien() != PlayingDog.WITH) {
            for (HandTarot poignee_ : handfuls) {
                if (!possibleExcuse_.last().estVide()
                        && poignee_.contient(CardTarot.excuse())) {
                    possibleExcuse_.last().jouer(CardTarot.excuse());
                }
            }
            if (!possibleExcuse_.last().estVide()
                    && progressingTrick.contient(CardTarot.excuse())) {
                possibleExcuse_.last().jouer(CardTarot.excuse());
            }
        }
        m.put(CardTarot.EXCUSE.couleur(), possibleExcuse_);
        m.put(Suit.TRUMP,atoutsPossibles(_repartitionCartesJouees.getVal(couleurAtout()), _plisFaits,
                _cartesJoueur.getVal(couleurAtout()), _numero, _carteAppeleeJouee));
        for (Suit couleur_ : couleursOrdinaires()) {
            // On fait une boucle sur les
            // couleurs autres que l'atout
            m.put(couleur_,cartesPossibles(couleur_,
                    _repartitionCartesJouees.getVal(couleur_), _plisFaits,
                    _cartesJoueur.getVal(couleur_), _numero, _carteAppeleeJouee));
        }
        return m;
    }

    /**
    Retourne l'ensemble des atouts (sans l'Excuse) probablement possedes par
    les autres joueurs
    @param _numero
    */
    private EqList<HandTarot> atoutsPossibles(HandTarot _atoutsJoues,
            CustList<TrickTarot> _plisFaits, HandTarot _atoutsJoueur, byte _numero,
            boolean _carteAppeleeJouee) {
        EqList<HandTarot> m = new EqList<HandTarot>();
        byte nombreJoueurs_ = getNombreDeJoueurs();

        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            m.add(new HandTarot());
            if(joueur_ == _numero) {
                m.last().ajouterCartes(_atoutsJoueur);
                continue;
            }
            for (CardTarot carte_ : HandTarot.atoutsSansExcuse()) {
                if (!_atoutsJoues.contient(carte_)
                        && !_atoutsJoueur.contient(carte_)) {
                    m.last().ajouter(carte_);
                }
            }
            if (defausseTarot(joueur_, _plisFaits)) {
                // Les joueurs se defaussant
                // sur atout ou couleur
                // demandee ne peuvent pas
                // avoir de l'atout
                m.get(joueur_).supprimerCartes();
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.TRUMP)) {
                m.get(joueur_).supprimerCartes();
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.POINT)) {
                m.get(joueur_).supprimerCartes(m.get(joueur_).bouts());
            }
        }
        m.add(new HandTarot());
        if (bid.getJeuChien() == PlayingDog.WITH) {
            /*
            Les atouts ecartes sont annonces donc certains de faire partie du
            chien
            */
            m.last().ajouterCartes(
                    _plisFaits.first().getCartes().couleur(couleurAtout()));
        } else {
            /*
            Si le chien est inconnu de tous alors n'importe quel atout non
            joue et non possede par le joueur peut etre dans le chien
            */
            for (CardTarot carte_ : HandTarot.atoutsSansExcuse()) {
                if (!_atoutsJoues.contient(carte_)
                        && !_atoutsJoueur.contient(carte_)) {
                    m.last().ajouter(carte_);
                }
            }
        }
        CustList<TrickTarot> plis_ = new CustList<TrickTarot>(_plisFaits);
        plis_.add(progressingTrick);
        for(TrickTarot pli_:plis_) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemande_=pli_.couleurDemandee();
            for(CardTarot c: pli_) {
                byte joueur_ = pli_.joueurAyantJouePliEnCours(c,nombreJoueurs_);
                if (joueur_ == _numero) {
                    continue;
                }
                Numbers<Byte> joueursAvant_ = pli_.joueursAyantJoueAvant(joueur_,nombreJoueurs_);
                byte forceLoc_ = c.strength(couleurDemande_);
                byte max_ = 0;
                byte ramasseurVirtuel_ = joueur_;
                //joueursAvant non vide
                for(byte j: joueursAvant_) {
                    CardTarot carte_ = pli_.carteDuJoueur(j,nombreJoueurs_);
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
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ == _numero) {
                    continue;
                }
                if (taker != joueur_) {
                    // Les atouts du chien (si il est vu) ne peuvent possedes
                    // que par le preneur
                    for (CardTarot carte_ : getDistribution().derniereMain()) {
                        if (!couleursOrdinaires().containsObj(carte_.couleur())
                                && m.get(joueur_).contient(carte_)) {
                            m.get(joueur_).jouer(carte_);
                        }
                    }
                }
                /*
                Les atouts eventuellement ecartes au chien sont vus par les
                autres joueurs et ne peuvent pas etre joues dans les plis
                suivants
                */
                for (CardTarot carte_ : _plisFaits.first()) {
                    if (carte_.couleur() == couleurAtout() && m.get(joueur_).contient(carte_)) {
                        m.get(joueur_).jouer(carte_);
                    }
                }
            }
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            if (joueur_ == _numero) {
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
            for (CardTarot carte_ : progressingTrick) {
                if (m.get(joueur_).contient(carte_)) {
                    m.get(joueur_).jouer(carte_);
                }
            }
            if (!progressingTrick.aJoue(joueur_, nombreJoueurs_)) {
                continue;
            }
            CardTarot carteDuJoueur_ = progressingTrick.carteDuJoueur(
                    joueur_, nombreJoueurs_);
            Suit couleurDemandee_ = progressingTrick.couleurDemandee();
            if (couleursOrdinaires().containsObj(carteDuJoueur_.couleur())
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
        if (bid.getJeuChien() != PlayingDog.WITH) {
            for (HandTarot main_ : handfuls) {
                for (CardTarot carte_ : main_) {
                    if (m.last().contient(carte_)) {
                        m.last().jouer(carte_);
                    }
                }
            }
            for (CardTarot carte_ : progressingTrick) {
                if (m.last().contient(carte_)) {
                    m.last().jouer(carte_);
                }
            }
        }
        byte joueur_ = 0;
        for (HandTarot main_ : m) {
            if (joueur_ == nombreJoueurs_) {
                break;
            }
            if (joueur_ == _numero) {
                joueur_++;
                continue;
            }
            if (main_.estVide()) {
                joueur_++;
                continue;
            }
            //filtre sur le jeu d'une carte couleur atout apres un adversaire ramasseur
            HandTarot atoutsFiltres_ = sousCoupeTarot(_numero, joueur_,
                    main_, _plisFaits);
            m.set( joueur_, atoutsFiltres_);
            joueur_++;
        }
        joueur_ = 0;
        for (HandTarot main_ : m) {
            if (joueur_ == nombreJoueurs_) {
                break;
            }
            if (joueur_ == _numero) {
                joueur_++;
                continue;
            }
            if (main_.estVide()) {
                joueur_++;
                continue;
            }
            //filtre sur la fourniture d'un atout a une couleur
            HandTarot atoutsFiltres_ = coupeTarot(_numero, joueur_,
                    main_, _plisFaits);
            m.set( joueur_, atoutsFiltres_);
            joueur_++;
        }
        if(_carteAppeleeJouee) {
            joueur_ = 0;
            for (HandTarot main_ : m) {
                if (joueur_ == nombreJoueurs_) {
                    break;
                }
                if (joueur_ == _numero) {
                    joueur_++;
                    continue;
                }
                if (main_.estVide()) {
                    joueur_++;
                    continue;
                }
                if(petitJoueDemandeAtoutRamasseurAdv(joueur_,_plisFaits)) {
                    main_.supprimerCartes();
                }
                //filtre sur la fourniture d'un atout a une couleur
                joueur_++;
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
    private EqList<HandTarot> cartesPossibles(Suit _couleur,
            HandTarot _cartesJouees, CustList<TrickTarot> _plisFaits,
            HandTarot _cartesJoueur, byte _numero,
            boolean _carteAppeleeJouee) {
        EqList<HandTarot> m = new EqList<HandTarot>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            m.add(new HandTarot());
            if(joueur_ == _numero) {
                m.last().ajouterCartes(_cartesJoueur);
                continue;
            }
            for (CardTarot carte_ : HandTarot.couleurComplete(_couleur)) {
                if (!_cartesJouees.contient(carte_)
                        && !_cartesJoueur.contient(carte_)) {
                    m.last().ajouter(carte_);
                }
            }
            if (defausseTarot(joueur_, _couleur, _plisFaits)
                    || coupeTarot(_couleur, joueur_, _plisFaits)) {
                // Les joueurs
                // se defaussant
                // sur atout ou
                // couleur
                // demandee ne
                // peuvent pas
                // avoir de
                // l'atout
                m.get(joueur_).supprimerCartes();
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.SUIT)) {
                m.get(joueur_).supprimerCartes();
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.POINT)
                    || declaresMiseres.get(joueur_).containsObj(Miseres.CHARACTER)) {
                m.get(joueur_).supprimerCartes(m.get(joueur_).charCardsBySuit(_couleur));
            }
            if (declaresMiseres.get(joueur_).containsObj(Miseres.LOW_CARDS)) {
                m.get(joueur_).supprimerCartes(
                        m.get(joueur_).cartesBasses(_couleur));
            }
        }
        m.add(new HandTarot());
        if (bid.getJeuChien() != PlayingDog.WITH) {
            for (CardTarot carte_ : HandTarot.couleurComplete(_couleur)) {
                if (!_cartesJouees.contient(carte_)
                        && !_cartesJoueur.contient(carte_)) {
                    m.last().ajouter(carte_);
                }
            }
        } else {
            if (_numero == taker) {
                /*
            Le preneur sait ce qu'il a mis au chien
            pour une Petite ou une Garde
            */
                m.last().ajouterCartes(
                        _plisFaits.first().getCartes().couleur(_couleur));
            } else {
                if (_plisFaits.first().getCartes().tailleCouleur(couleurAtout()) > 0) {
                    /* Si le preneur est oblige
                    d 'ecarter des atouts
                    alors les cartes autre que
                    le roi de couleur
                    du chien sont
                    certainement ecartees*/
                    for (CardTarot carte_ : getDistribution().derniereMain()
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
                        if (!_cartesJouees.contient(carte_)
                                && !_cartesJoueur.contient(carte_)) {
                            m.last().ajouter(carte_);
                        }
                    }
                }
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ == _numero) {
                    continue;
                }
                if (taker != joueur_) {
                    // Les cartes d'une couleur du chien (si il est vu) ne
                    // peuvent possedes que par le preneur ou etre ecartees
                    for (CardTarot carte_ : getDistribution().derniereMain()) {
                        if (carte_.couleur() == _couleur
                                && m.get(joueur_).contient(carte_)) {
                            m.get(joueur_).jouer(carte_);
                        }
                    }
                } else if (_plisFaits.first().getCartes().tailleCouleur(couleurAtout()) > 0) {
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
                        if (m.get(taker).contient(carte_)) {
                            m.get(taker).jouer(carte_);
                        }
                    }
                }
            }
        }
        /*
        Les cartes jouees dans le pli en cours ne peuvent pas (ou plus) etre
        possedees par les joueurs ni faire partie de l'ecart
        */
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            if (joueur_ == _numero) {
                continue;
            }
            for (CardTarot carte_ : progressingTrick.getCartes()) {
                if (m.get(joueur_).contient(carte_)) {
                    m.get(joueur_).jouer(carte_);
                }
            }
        }
        for (CardTarot carte_ : progressingTrick.getCartes()) {
            if (m.last().contient(carte_)) {
                m.last().jouer(carte_);
            }
        }
        if (progressingTrick.couleurDemandee() == _couleur) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ == _numero) {
                    continue;
                }
                if (!progressingTrick.aJoue(joueur_, nombreJoueurs_)) {
                    continue;
                }
                CardTarot carteJouee_ = progressingTrick.carteDuJoueur(
                        joueur_, nombreJoueurs_);
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
        byte joueur_ = 0;
        for (HandTarot couleurLoc_ : m) {
            if (joueur_ == nombreJoueurs_) {
                break;
            }
            if (joueur_ == _numero) {
                joueur_++;
                continue;
            }
            if (couleurLoc_.estVide()) {
                joueur_++;
                continue;
            }
            Suit noCouleur_ = couleurLoc_.premiereCarte()
                    .couleur();
            //filtre sur le jeu d'une carte couleur ordinaire apres un adversaire ramasseur
            HandTarot atoutsFiltres_ = joueCarteBasseTarot(_numero,
                    joueur_, noCouleur_, couleurLoc_, _plisFaits);
            m.set( joueur_, atoutsFiltres_);
            joueur_++;
        }
        if (_carteAppeleeJouee) {
            joueur_ = 0;
            for (HandTarot couleurLoc_ : m) {
                if (joueur_ == nombreJoueurs_) {
                    break;
                }
                if (joueur_ == _numero) {
                    joueur_++;
                    continue;
                }
                if (couleurLoc_.estVide()) {
                    joueur_++;
                    continue;
                }
                Suit noCouleur_ = couleurLoc_.premiereCarte()
                        .couleur();
                HandTarot filteredCharacters_ = playCharacterCardTarot(
                        joueur_, noCouleur_, couleurLoc_, _plisFaits);
                m.set( joueur_, filteredCharacters_);
                joueur_++;
            }
        }
        //playCharacterCardTarot
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
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        Numbers<Byte> joueursRepartitionConnue_ = new Numbers<Byte>();
        Numbers<Byte> joueursRepartitionConnue2_ = new Numbers<Byte>();
        Numbers<Byte> joueursRepartitionConnueMemo_ = new Numbers<Byte>();
        Numbers<Byte> joueursRepartitionInconnue_ = new Numbers<Byte>();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = new EnumMap<Suit,EqList<HandTarot>>();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = new EnumMap<Suit,EqList<HandTarot>>(
                _cartesPossibles);
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> retour_ = new EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        int nombreDApparitionCarte_;
        /*
        Indique le nombre de mains pour les
        cartes possibles ou apparait la carte
        */
        EnumList<Suit> toutesCouleurs_ = new EnumList<Suit>();
        toutesCouleurs_.add(CardTarot.EXCUSE.couleur());
        toutesCouleurs_.add(Suit.TRUMP);
        toutesCouleurs_.addAllElts(couleursOrdinaires());
        for(Suit couleur_: toutesCouleurs_) {
            cartesCertaines_.put(couleur_,new EqList<HandTarot>());
        }
        for (Suit couleur_:cartesCertaines_.getKeys()) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ <= nombreJoueurs_; joueur_++) {
                cartesCertaines_.getVal(couleur_).add(new HandTarot());
            }
        }
        int nombreCartesPossiblesJoueur_;
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ <= nombreJoueurs_; joueur_++) {
            nombreCartesPossiblesJoueur_ = 0;
            for (Suit couleur_: toutesCouleurs_) {
                nombreCartesPossiblesJoueur_ += cartesPossibles_.getVal(couleur_)
                        .get(joueur_).total();
            }
            if (nombreCartesPossiblesJoueur_ == getDistribution()
                    .main(joueur_).total()) {
                /*
                    L'ensemble des cartes d'un joueur
                    reellement possedees est inclus
                    dans l'ensemble des cartes
                    probablement possedees par ce
                    joueur
                    */

                for (Suit couleur_:toutesCouleurs_) {
                    cartesCertaines_.getVal(couleur_)
                        .get(joueur_).ajouterCartes(
                            cartesPossibles_.getVal(couleur_).get(joueur_));
                }
                joueursRepartitionConnue_.add(joueur_);
                joueursRepartitionConnueMemo_.add(joueur_);
            }
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
                for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ <= nombreJoueurs_; joueur2_++) {
                    if (!joueursRepartitionConnueMemo_.containsObj(joueur2_)) {
                        for (Suit couleur_:toutesCouleurs_) {
                            cartesPossibles_.getVal(couleur_)
                                .get(joueur2_).supprimerCartes(
                                    cartesCertaines_.getVal(couleur_).get(
                                            joueur_));
                        }
                    }
                    nombreCartesPossiblesJoueur_ = 0;
                    for (Suit couleur_:toutesCouleurs_) {
                        nombreCartesPossiblesJoueur_ += cartesPossibles_
                                .getVal(couleur_).get(joueur2_).total();
                    }
                    if (nombreCartesPossiblesJoueur_ == getDistribution()
                            .main(joueur2_).total()
                            && !joueursRepartitionConnueMemo_
                            .containsObj(joueur2_)) {
                        for (Suit couleur_:toutesCouleurs_) {
                            cartesCertaines_.getVal(couleur_).get(joueur2_)
                            .supprimerCartes();
                            cartesCertaines_.getVal(couleur_)
                                .get(joueur2_).ajouterCartes(
                                    cartesPossibles_.getVal(couleur_).get(
                                            joueur2_));
                        }
                        joueursRepartitionConnue2_.add(joueur2_);
                        joueursRepartitionConnueMemo_.add(joueur2_);
                    }
                }
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ <= nombreJoueurs_; joueur_++) {
                if (!joueursRepartitionConnueMemo_.containsObj(joueur_)) {
                    joueursRepartitionInconnue_.add(joueur_);
                }
            }
            for (byte joueur_ : joueursRepartitionInconnue_) {
                for (Suit couleur_:toutesCouleurs_) {
                    for (CardTarot carte_ : cartesPossibles_.getVal(couleur_).get(
                            joueur_)) {
                        nombreDApparitionCarte_ = 0;
                        for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ <= nombreJoueurs_; joueur2_++) {
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
                nombreCartesPossiblesJoueur_ = 0;
                for (Suit couleur_:toutesCouleurs_) {
                    nombreCartesPossiblesJoueur_ += cartesCertaines_
                            .getVal(couleur_).get(joueur_).total();
                }
                if (nombreCartesPossiblesJoueur_ == getDistribution().main(
                        joueur_).total()
                        && !joueursRepartitionConnueMemo_.containsObj(joueur_)) {
                    cartesPossibles_.getVal(Suit.TRUMP).get(joueur_).supprimerCartes();
                    cartesPossibles_.getVal(Suit.TRUMP).get(joueur_).ajouterCartes(
                            cartesCertaines_.getVal(Suit.TRUMP).get(joueur_));
                    cartesPossibles_.getVal(Suit.TRUMP).get(joueur_).trierParForceEnCours(Suit.TRUMP);

                    for (Suit couleur_ : couleursOrdinaires()) {
                        cartesPossibles_.getVal(couleur_).get(joueur_)
                        .supprimerCartes();
                        cartesPossibles_
                            .getVal(couleur_).get(joueur_).ajouterCartes(
                                cartesCertaines_.getVal(couleur_).get(
                                        joueur_));
                        cartesPossibles_.getVal(couleur_).get(joueur_).trierParForceEnCours(couleur_);
                    }
                    joueursRepartitionConnueMemo_.add(joueur_);
                    joueursRepartitionConnue2_.add(joueur_);
                }
            }
            joueursRepartitionInconnue_.clear();
            joueursRepartitionConnue_.clear();
            joueursRepartitionConnue_.addAllElts(joueursRepartitionConnue2_);
            joueursRepartitionConnue2_.clear();
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ <= nombreJoueurs_; joueur_++) {
            if (!joueursRepartitionConnueMemo_.containsObj(joueur_)) {
                joueursRepartitionInconnue_.add(joueur_);
            }
        }
        for (byte joueur_ : joueursRepartitionInconnue_) {
            cartesCertaines_.getVal(Suit.TRUMP).get(joueur_).trierParForceEnCours(Suit.TRUMP);
            for (Suit couleur_ : couleursOrdinaires()) {
                cartesCertaines_.getVal(couleur_).get(joueur_).trierParForceEnCours(couleur_);
            }
        }
        retour_.put(Hypothesis.POSSIBLE, cartesPossibles_);
        retour_.put(Hypothesis.SURE, cartesCertaines_);
        return retour_;
    }

    /**
    Retourne vrai si le joueur ne peut pas jouer de l'atout sur demande
    d'atout ou sur demande de coupe de couleur sauf pli en cours
    */
    private static boolean defausseTarot(byte _numero, CustList<TrickTarot> _unionPlis) {
        boolean coupe_ = false;
        // coupe retourne vrai si on sait que le joueur ne
        // peut que jouer de l'atout sur des couleurs
        if (coupeTarot(couleurAtout(), _numero, _unionPlis)) {
            coupe_ = true;
        }
        for (Suit couleur_ : couleursOrdinaires()) {
            if (coupeTarot(couleur_, _numero, _unionPlis)) {
                coupe_ = true;
            }
        }
        // coupe est vrai si et seulement si il existe au moins une coupe a une
        // des couleurs
        if (!coupe_) {
            return false;
        }
        if (coupeTarot(couleurAtout(), _numero, _unionPlis)) {
            // Si le joueur ne
            // joue pas d'atout
            // sur demande
            // d'atout
            return true;
        }
        // Le joueur a deja joue une carte d'une autre couleur que celle
        // demandee differente de l'atout
        int lastIndex_ = _unionPlis.getLastIndex();
        for (int indicePli_ = lastIndex_; indicePli_ >= CustList.FIRST_INDEX; indicePli_--) {
            /*
            On effectue une boucle
            sur les plis faits
            par les joueurs en
            commencant par
            le plus recent
            ( numero le plus eleve )
            */
            TrickTarot pli_ = _unionPlis.get(indicePli_);
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
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
    Retourne vrai si le joueur ne peut pas jouer de l'atout ni fournir sur
    demande de la couleur "couleur" sauf pli en cours
    */
    private static boolean defausseTarot(byte _numero, Suit _couleurDonnee,
            CustList<TrickTarot> _unionPlis) {
        boolean coupe_ = false;
        // coupe retourne vrai si on sait que le joueur ne
        // peut que jouer de l'atout sur des couleurs
        if (coupeTarot(couleurAtout(), _numero, _unionPlis)) {
            coupe_ = true;
        }
        for (Suit couleur_ : couleursOrdinaires()) {
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
    private static boolean coupeTarot(Suit _couleur, byte _numero,
            CustList<TrickTarot> _unionPlis) {
        if (couleursOrdinaires().containsObj(_couleur)) {
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
            if (!couleursOrdinaires().containsObj(couleurJoueur_)) {
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

    private HandTarot playCharacterCardTarot(byte _numero,
            Suit _couleur, HandTarot _probablyCharacterCard,
            CustList<TrickTarot> _unionPlis) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_probablyCharacterCard);
        HandTarot playedCards_ = new HandTarot();
        NumberMap<Byte,Boolean> defausses_ = new NumberMap<Byte,Boolean>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte j = CustList.FIRST_INDEX;j<nombreDeJoueurs_;j++) {
            defausses_.put(j, defausseTarot(j, _unionPlis));
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
            if (confiance(_numero,pli_.getRamasseur())) {
                continue;
            }
            //winner of the trick foe for the viewed player
            if (!pli_.joueursAyantJoueAvant(_numero).containsObj(pli_.getRamasseur())) {
                continue;
            }
            boolean entameSurExcuse_ = true;
            for(byte j: pli_.joueursAyantJoueAvant(_numero)) {
                CardTarot carteJouee_ = pli_.carteDuJoueur(j);
                if(carteJouee_ != CardTarot.EXCUSE) {
                    entameSurExcuse_ = false;
                    break;
                }
            }
            if(entameSurExcuse_) {
                continue;
            }
            boolean defausseToutJoueurApres_ = true;
            for(byte j: pli_.joueursAyantJoueApres(_numero)) {
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

    private HandTarot joueCarteBasseTarot(byte _joueurCourant, byte _numero,
            Suit _couleur, HandTarot _cartesCouleurPossibles,
            CustList<TrickTarot> _unionPlis) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_cartesCouleurPossibles);
        HandTarot mainCourante_ = deal.main(_joueurCourant);
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
        cartesVues_.ajouterCartes(mainCourante_.couleur(_couleur).cartesBasses(
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

    private HandTarot sousCoupeTarot(byte _joueurCourant, byte _numero,
            HandTarot _atoutsPossibles, CustList<TrickTarot> _unionPlis) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_atoutsPossibles);
        HandTarot mainCourante_ = deal.main(_joueurCourant);
        HandTarot cartesJouees_ = cartesJoueesEnCours(_joueurCourant);
        HandTarot playedCards_ = new HandTarot();
        for (TrickTarot pli_ : _unionPlis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.couleur() != couleurAtout()) {
                continue;
            }
            boolean sousCoupe_ = false;
            Suit couleurDemandee_ = pli_.couleurDemandee();
            byte force_ = carteObservee_.strength(couleurDemandee_);
            for(byte j: pli_.joueursAyantJoueAvant(_joueurCourant)) {
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
        cartesVues_.ajouterCartes(cartesJouees_.couleur(couleurAtout()));
        cartesVues_.ajouterCartes(mainCourante_.couleur(couleurAtout()));
        cartesVues_.trierParForceEnCours(couleurAtout());
        playedCards_.trierParForceEnCours(couleurAtout());
        if (!playedCards_.estVide()) {
            HandTarot mainLocale_ = new HandTarot();
            CardTarot carteObservee_ = playedCards_.derniereCarte();
            for (CardTarot carte_ : cartesVues_) {
                if (carte_.strength(couleurAtout()) < carteObservee_
                        .strength(couleurAtout())) {
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
                    if (atout_.strength(couleurAtout()) >= maxCarte_
                            .strength(couleurAtout())) {
                        continue;
                    }
                    atoutsImpossibles_.ajouter(atout_);
                }
                retour_.supprimerCartes(atoutsImpossibles_);
            }
        }
        return retour_;
    }

    private HandTarot coupeTarot(byte _joueurCourant, byte _numero,
            HandTarot _atoutsPossibles, CustList<TrickTarot> _unionPlis) {
        HandTarot retour_ = new HandTarot();
        retour_.ajouterCartes(_atoutsPossibles);
        HandTarot mainCourante_ = deal.main(_joueurCourant);
        HandTarot cartesJouees_ = cartesJoueesEnCours(_joueurCourant);
        CustList<TrickTarot> plis_ = new CustList<TrickTarot>();
        NumberMap<Byte,HandTarot> atoutsJouesPlis_ = new NumberMap<Byte,HandTarot>();
        NumberMap<Byte,Boolean> defausses_ = new NumberMap<Byte,Boolean>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte j = CustList.FIRST_INDEX;j<nombreDeJoueurs_;j++) {
            defausses_.put(j, defausseTarot(j, _unionPlis));
        }
        byte key_ = 0;
        for (TrickTarot pli_ : _unionPlis) {
            if (!pli_.getVuParToutJoueur()) {
                key_++;
                continue;
            }
            if (pli_.getEntameur() == _numero) {
                key_++;
                continue;
            }
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            if (carteObservee_.couleur() != couleurAtout()) {
                key_++;
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            boolean coupe_ = true;
            byte force_ = carteObservee_.strength(couleurDemandee_);
            HandTarot atoutsJouesAvant_ = new HandTarot();
            boolean entameSurExcuse_ = true;
            for(byte j: pli_.joueursAyantJoueAvant(_numero)) {
                CardTarot carteJouee_ = pli_.carteDuJoueur(j);
                if(carteJouee_ != CardTarot.EXCUSE) {
                    entameSurExcuse_ = false;
                }
                if(carteJouee_.strength(couleurDemandee_) < force_) {
                    if(carteJouee_.couleur() == Suit.TRUMP) {
                        atoutsJouesAvant_.ajouter(carteJouee_);
                    }
                    continue;
                }
                coupe_ = false;
                break;
            }
            if(entameSurExcuse_) {
                key_++;
                continue;
            }
            if(!coupe_) {
                key_++;
                continue;
            }
            boolean defausseToutJoueurApres_ = true;
            for(byte j: pli_.joueursAyantJoueApres(_numero)) {
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
        cartesVues_.ajouterCartes(cartesJouees_.couleur(couleurAtout()));
        cartesVues_.ajouterCartes(mainCourante_.couleur(couleurAtout()));
        cartesVues_.trierParForceEnCours(couleurAtout());
        key_ = 0;
        for (TrickTarot pli_ : plis_) {
            if (!atoutsJouesPlis_.contains(key_)) {
                key_++;
                continue;
            }
            HandTarot atoutsJouesPli_ = atoutsJouesPlis_.getVal(key_);
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            HandTarot mainLocale_ = new HandTarot();
            for (CardTarot carte_ : cartesVues_) {
                if (carte_.strength(couleurAtout()) < carteObservee_
                        .strength(couleurAtout())) {
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
                    if (atout_.strength(couleurAtout()) >= maxCarte_
                            .strength(couleurAtout())) {
                        continue;
                    }
                    atoutsImpossibles_.ajouter(atout_);
                }
            } else {
                atoutsJouesPli_.trierParForceEnCours(pli_.couleurDemandee());
                CardTarot maxAtoutJouePli_ = atoutsJouesPli_.premiereCarte();
                for (CardTarot atout_ : HandTarot.atoutsSansExcuse()) {
                    if (atout_.strength(couleurAtout()) >= maxCarte_
                            .strength(couleurAtout())) {
                        continue;
                    }
                    if (atout_.strength(couleurAtout()) <= maxAtoutJouePli_
                            .strength(couleurAtout())) {
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
    private boolean petitJoueDemandeAtoutRamasseurAdv(
            byte _numero,
            CustList<TrickTarot> _unionPlis) {
        boolean playedSmall_ = false;
        for (TrickTarot pli_ : _unionPlis) {
            if(pli_.couleurDemandee() != Suit.TRUMP) {
                continue;
            }
            CardTarot carteObservee_ = pli_.carteDuJoueur(_numero);
            if(carteObservee_ != CardTarot.petit()) {
                continue;
            }
            //jeu du Petit sur demande d'atout
            if(!confiance(_numero,pli_.getRamasseur())) {
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
    private HandTarot cartesJouees(byte _numero) {
        HandTarot m = new HandTarot();
        if (!existePreneur()) {
            for (TrickTarot t: tricks) {
                if (!t.getVuParToutJoueur()) {
                    continue;
                }
                m.ajouterCartes(t.getCartes());
            }
        } else if (_numero == taker && bid.getJeuChien() == PlayingDog.WITH) {
            for (TrickTarot t: tricks) {
                m.ajouterCartes(t.getCartes());
            }
        } else if (bid.getJeuChien() != PlayingDog.AGAINST) {
            for (TrickTarot t: tricks) {
                if (!t.getVuParToutJoueur()) {
                    continue;
                }
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

    boolean confiance(byte _joueur, byte _enjoueur) {
        return confidence.get(_joueur).get(_enjoueur);
    }

    /**
    Renvoie un entier 0 si joueur de non confiance qui va faire le pli 1 si
    joueur de confiance va faire le pli et -1 sinon
    @param cartes_possibles
                l'ensemble des cartes probablement possedees par les joueurs
    @param cartes_certaines
                l'ensemble des cartes surement possedees par les joueurs
    @param ramasseur_virtuel
                le joueur, qui sans les cartes jouees par les derniers joueurs
                du pli est ramasseur
    @param _carteForte
                la carte qui est en train de dominer le pli
    @param joueurs_non_joue
                l'ensemble des joueurs n'ayant pas encore joue leur carte
    @param joueurs_confiance
                l'ensemble des joueurs de confiance
    @param joueurs_non_confiance
                l'ensemble des joueurs de non confiance
    @param _numero
                le numero du joueur qui va jouer
    @param couleur_appelee
                la couleur appelee si elle existe -1 sinon
    @param carte_appelee_jouee
                une valeur booleenne vrai si et seulement si la carte appelee
                est jouee
    */
    private PossibleTrickWinner equipeQuiVaFairePli(
            TarotInfoPliEnCours _info,
            byte _numero,
            CardTarot _carteForte) {
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = _info.getCartesPossibles();
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = _info.getCartesCertaines();
        byte ramasseurVirtuel_ = _info.getRamasseurVirtuel();
        Numbers<Byte> joueursNonJoue_ = _info.getJoueursNonJoue();
        EnumList<Suit> couleursAppelees_ = couleursAppelees();
        boolean carteAppeleeJouee_ =_info.isCarteAppeleeJouee();
        Suit couleurDemandee_ = progressingTrick.couleurDemandee();
        boolean ramasseurVirtuelEgalCertain_;
        Numbers<Byte> joueursNonConfianceNonJoue_ = new Numbers<Byte>(
                joueursNonJoue_);
        Numbers<Byte> joueursConfianceNonJoue_ = new Numbers<Byte>(
                joueursNonJoue_);
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        Numbers<Byte> joueursConfiance_ = joueursConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = joueursNonConfiance(_numero,tousJoueurs(nombreDeJoueurs_));
        joueursNonConfianceNonJoue_.retainAllElements(joueursNonConfiance_);
        joueursConfianceNonJoue_.retainAllElements(joueursConfiance_);
        Numbers<Byte> joueursJoue_ = _info.getJoueursJoue();
        if (_carteForte.couleur() == couleurAtout() && couleursOrdinaires().containsObj(couleurDemandee_)) {
        /*
        Le pli est
        coupe
        */
            if (!cartesCertaines_.getVal(couleurDemandee_).get(_numero).estVide()
                    || cartesCertaines_.getVal(couleurAtout()).get(_numero).estVide()
                    || cartesCertaines_.getVal(couleurAtout()).get(_numero).premiereCarte()
                    .strength(couleurDemandee_) < _carteForte.strength(couleurDemandee_)) {
                    /*
                    Le joueur
                    numero ne
                    peut pas
                    prendre la
                    main
                    */
                if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                    if (couleursAppelees_.containsObj(couleurDemandee_)
                            && !carteAppeleeJouee_ && aPourDefenseur(_numero)) {
                        /* The player, probably called by the current taker
                        and still owing one called card of the current led suit,
                        must follow a card belonging to the current demanded suit.*/
                        Numbers<Byte> joueursNonConfiancePreneur_ = new Numbers<Byte>();
                        for (byte j: joueursNonConfianceNonJoue_) {
                            if (statutDe(j) != Status.TAKER) {
                                continue;
                            }
                            joueursNonConfiancePreneur_.add(j);
                        }
                        joueursNonConfianceNonJoue_ = joueursNonConfiancePreneur_;
                    }
                    /*
                    On cherche a savoir si le ramasseur virtuel (joueur de
                    confiance) va avec sa coupe sur la couleur demandee
                    dominer tous les atouts des joueurs de non confiance
                    eventuels
                    */
                    if (ramasseurBatAdvSur(joueursNonConfianceNonJoue_,
                            couleurDemandee_, _carteForte, cartesPossibles_,
                            cartesCertaines_)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    /*
                    On cherche les joueurs de confiance battant de maniere
                    certaine les joueurs de non confiance n'ayant pas joue ou
                    possedant des cartes que les joueurs ayant joue n'ont pas
                    ainsi que les joueurs de non confiance n'ayant pas joue
                    */
                    if (existeJoueurNonJoueBattantAdv(
                            joueursNonConfianceNonJoue_,
                            joueursConfianceNonJoue_, couleurDemandee_,
                            cartesPossibles_, cartesCertaines_)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    /*
                    On cherche les joueurs de confiance battant de maniere
                    certaine les joueurs de non confiance n'ayant pas joue ou
                    possedant des cartes que les joueurs ayant joue n'ont pas
                    ainsi que les joueurs de non confiance n'ayant pas joue
                    */
                    if (existeJoueurNonJoueBattantPtm(
                            joueursNonConfianceNonJoue_,
                            joueursConfianceNonJoue_, joueursJoue_,
                            couleurDemandee_, cartesPossibles_,
                            cartesCertaines_)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    /*
                    On cherche les joueurs de non confiance battant de
                    maniere certaine les joueurs de confiance n'ayant pas
                    joue ou possedant des cartes que les joueurs ayant joue
                    n'ont pas ainsi que les joueurs de non confiance n'ayant
                    pas joue
                    */
                    if (existeJoueurAdvRamBatAdvSur(
                            joueursConfianceNonJoue_,
                            joueursNonConfianceNonJoue_, couleurDemandee_,
                            _carteForte, cartesPossibles_, cartesCertaines_)) {
                        return PossibleTrickWinner.FOE_TEAM;
                    }
                    /*
                    On cherche les joueurs de non confiance battant de
                    maniere certaine les joueurs de confiance n'ayant pas
                    joue ou possedant des cartes que les joueurs ayant joue
                    n'ont pas ainsi que les joueurs de non confiance n'ayant
                    pas joue
                    */
                    if (existeJoueurNonJoueBattantPtm(
                            joueursConfianceNonJoue_,
                            joueursNonConfianceNonJoue_, joueursJoue_,
                            couleurDemandee_, cartesPossibles_,
                            cartesCertaines_)) {
                        return PossibleTrickWinner.FOE_TEAM;
                    }
                    return PossibleTrickWinner.UNKNOWN;
                }
                /*
                ramasseurVirtuel n'est pas un joueur de confiance pour le
                joueur numero
                */
                if (couleursAppelees_.containsObj(couleurDemandee_) && !carteAppeleeJouee_
                        && _numero == taker) {
                    Numbers<Byte> joueursConfianceNonJoueDiffAppele_ = new Numbers<Byte>();
                    for (byte j: joueursConfianceNonJoue_) {
                        if (statutDe(j) == Status.CALLED_PLAYER) {
                            continue;
                        }
                        joueursConfianceNonJoueDiffAppele_.add(j);
                    }
                    joueursConfianceNonJoue_ = joueursConfianceNonJoueDiffAppele_;
                }
                /*
                On cherche a savoir si le ramasseur virtuel (joueur de non
                confiance) bat tous les joueurs de confiance n'ayant pas joue
                */
                if (ramasseurBatAdvSur(joueursConfianceNonJoue_,
                        couleurDemandee_, _carteForte, cartesPossibles_,
                        cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*
                On cherche les joueurs de non confiance battant de maniere
                certaine les joueurs de confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurNonJoueBattantAdv(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurNonJoueBattantPtm(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurAdvRamBatAdvSur(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, couleurDemandee_,
                        _carteForte, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurNonJoueBattantPtm(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
                /* Fin joueurDeConfiance.contains(ramasseurVirtuel) */
            }
            /*
            Fin
            !cartesCertaines.get(couleurDemandee).get(numero).estVide()||
            cartesCertaines
            .get(1).get(numero).estVide()||cartesCertaines.get
            (1).get(numero)
            .premiereCarte().getforceJeu(couleurDemandee)<carteForte.getforceJeu(couleurDemandee) (fin test de
            possibilite pour le joueur numero de prendre le pli)
            */
            /*
            Le joueur numero peut prendre la main en surcoupant le ramasseur
            virtuel
            */
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
            if (existeJoueurBatAdvNum(joueursNonConfianceNonJoue_,
                    joueursConfianceNonJoue_, _numero, couleurDemandee_,
                    cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
            if (existeJoueurBatPtmNum(joueursNonConfianceNonJoue_,
                    joueursConfianceNonJoue_, joueursJoue_, _numero,
                    couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*
            On cherche les joueurs de non confiance battant de maniere
            certaine les joueurs de confiance n'ayant pas joue ou possedant
            des cartes que les joueurs ayant joue n'ont pas ainsi que les
            joueurs de non confiance n'ayant pas joue
            */
            if (existeJoueurBatAdvNum(joueursConfianceNonJoue_,
                    joueursNonConfianceNonJoue_, _numero, couleurDemandee_,
                    cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
            if (existeJoueurBatPtmNum(joueursConfianceNonJoue_,
                    joueursNonConfianceNonJoue_, joueursJoue_, _numero,
                    couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        if (_carteForte.couleur() == couleurDemandee_ && couleursOrdinaires().containsObj(couleurDemandee_)) {
            /* La couleur demandee n 'est pas de l 'atout et le pli n 'est pas coupe */
            ramasseurVirtuelEgalCertain_ = false;
            for (byte joueur_ : joueursConfianceNonJoue_) {
                if (vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (ramasseurVirtuelEgalCertain_) {
                /*
            Si un joueur de confiance n
            ayant pas joue va surement
            couper le pli
            */
                for (byte joueur_ : joueursNonConfianceNonJoue_) {
                    if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                        ramasseurVirtuelEgalCertain_ = false;
                    }
                }
                if (ramasseurVirtuelEgalCertain_) {
                    /*
                Si aucun joueur de non
                confiance n ayant pas
                joue ne va couper le pli
                */
                    return PossibleTrickWinner.TEAM;
                }
                if (existeJoueurNonJoueBattantAdv(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                if (existeJoueurNonJoueBattantPtm(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                if (existeJoueurNonJoueBattantAdv(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if (existeJoueurNonJoueBattantPtm(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            ramasseurVirtuelEgalCertain_ = false;
            for (byte joueur_ : joueursNonConfianceNonJoue_) {
                if (vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (ramasseurVirtuelEgalCertain_) {
                /*
            Si un joueur de non confiance
            n ayant pas joue va surement
            couper le pli
            */
                for (byte joueur_ : joueursConfianceNonJoue_) {
                    if (!(nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                        ramasseurVirtuelEgalCertain_ = false;
                    }
                }
                if (ramasseurVirtuelEgalCertain_) {
                    /*
                Si aucun joueur de
                confiance n ayant pas
                joue ne va couper le pli
                */
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if (existeJoueurNonJoueBattantAdv(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if (existeJoueurNonJoueBattantPtm(
                        joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                if (existeJoueurNonJoueBattantAdv(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                if (existeJoueurNonJoueBattantPtm(
                        joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, joueursJoue_,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            if (!cartesPossibles_.getVal(couleurDemandee_).get(_numero).estVide()
                    && cartesPossibles_.getVal(couleurDemandee_).get(_numero)
                    .premiereCarte().strength(couleurDemandee_) > _carteForte.strength(couleurDemandee_)) {
                /* Si le joueur numero peut prendre la main sans couper */
                /*
                On ne sait pas si un joueur n'ayant pas joue va couper le pli
                ou non
                */
                if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                    if (ramasseurBatSsCprAdv(
                            joueursNonConfianceNonJoue_, couleurDemandee_,
                            _carteForte, cartesPossibles_, cartesCertaines_)) {
                        return PossibleTrickWinner.TEAM;
                    }
                    return PossibleTrickWinner.UNKNOWN;
                }
                /* Fin joueursDeConfiance.contains(ramasseurVirtuel) */
                return PossibleTrickWinner.UNKNOWN;
            }
            /* Fin si le joueur numero peut prendre la main sans couper */
            if (peutCouper(couleurDemandee_, _numero, cartesPossibles_)) {
                /* Si le joueur
                numero peut
                prendre la
                main en coupant
                */
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurBatAdvNum(joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, _numero, couleurDemandee_,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurBatPtmNum(joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, joueursJoue_, _numero,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de non confiance battant de maniere
                certaine les joueurs de confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurBatAdvNum(joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, _numero,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*
                On cherche les joueurs de non confiance battant de maniere
                certaine les joueurs de confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJoueurBatPtmNum(joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, joueursJoue_, _numero,
                        couleurDemandee_, cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /* Le joueur numero ne peut pas prendre la main */
            if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                if (ramasseurBatSsCprAdv(joueursNonConfianceNonJoue_,
                        couleurDemandee_, _carteForte, cartesPossibles_,
                        cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /* Fin joueursDeConfiance.contains(ramasseurVirtuel) */
            /* Maintenant le ramasseur virtuel n'est pas un joueur de confiance */
            if (ramasseurBatSsCprAdv(joueursConfianceNonJoue_,
                    couleurDemandee_, _carteForte, cartesPossibles_,
                    cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
        }
        /* Le pli n'est pas coupe et la couleur demandee est l'atout */
        if (cartesCertaines_.getVal(couleurAtout()).get(_numero).estVide()
                || cartesCertaines_.getVal(couleurAtout()).get(_numero).premiereCarte().strength(couleurDemandee_) < _carteForte
                .strength(couleurDemandee_)) {
            /*
                Si le joueur numero ne peut pas prendre
                la main sur demande d'atout
                */
            if (joueursConfiance_.containsObj(ramasseurVirtuel_)) {
                /*
                Si le ramasseur virtuel (de confiance, ici) domine
                certainement les joueurs de non confiance n'ayant pas joue
                */
                if (ramasseurBatAdvDemat(joueursNonConfianceNonJoue_,
                        _carteForte, cartesPossibles_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJouBatAdvDemat(joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, cartesPossibles_,
                        cartesCertaines_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de confiance battant de maniere
                certaine les joueurs de non confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJouBatPtmDemat(joueursNonConfianceNonJoue_,
                        joueursConfianceNonJoue_, joueursJoue_,
                        cartesPossibles_)) {
                    return PossibleTrickWinner.TEAM;
                }
                /*
                On cherche les joueurs de non confiance battant de maniere
                certaine les joueurs de confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJouBatAdvSurDemat(joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, _carteForte,
                        cartesPossibles_, cartesCertaines_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                /*
                On cherche les joueurs de non confiance battant de maniere
                certaine les joueurs de confiance n'ayant pas joue ou
                possedant des cartes que les joueurs ayant joue n'ont pas
                ainsi que les joueurs de non confiance n'ayant pas joue
                */
                if (existeJouBatPtmSurDemat(joueursConfianceNonJoue_,
                        joueursNonConfianceNonJoue_, joueursJoue_,
                        _carteForte, cartesPossibles_)) {
                    return PossibleTrickWinner.FOE_TEAM;
                }
                return PossibleTrickWinner.UNKNOWN;
            }
            /*
            ramasseurVirtuel n'est pas un joueur de confiance pour le joueur
            numero
            */
            /*
            Si le ramasseur virtuel (de non confiance, ici) domine
            certainement les joueurs de non confiance n'ayant pas joue
            */
            if (ramasseurBatAdvDemat(joueursConfianceNonJoue_,
                    _carteForte, cartesPossibles_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*
            On cherche les joueurs de non confiance battant de maniere
            certaine les joueurs de confiance n'ayant pas joue ou possedant
            des cartes que les joueurs ayant joue n'ont pas ainsi que les
            joueurs de non confiance n'ayant pas joue
            */
            if (existeJouBatAdvDemat(joueursConfianceNonJoue_,
                    joueursNonConfianceNonJoue_, cartesPossibles_,
                    cartesCertaines_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*
            On cherche les joueurs de non confiance battant de maniere
            certaine les joueurs de confiance n'ayant pas joue ou possedant
            des cartes que les joueurs ayant joue n'ont pas ainsi que les
            joueurs de non confiance n'ayant pas joue
            */
            if (existeJouBatPtmDemat(joueursConfianceNonJoue_,
                    joueursNonConfianceNonJoue_, joueursJoue_,
                    cartesPossibles_)) {
                return PossibleTrickWinner.FOE_TEAM;
            }
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
            if (existeJouBatAdvSurDemat(joueursNonConfianceNonJoue_,
                    joueursConfianceNonJoue_, _carteForte, cartesPossibles_,
                    cartesCertaines_)) {
                return PossibleTrickWinner.TEAM;
            }
            /*
            On cherche les joueurs de confiance battant de maniere certaine
            les joueurs de non confiance n'ayant pas joue ou possedant des
            cartes que les joueurs ayant joue n'ont pas ainsi que les joueurs
            de non confiance n'ayant pas joue
            */
            if (existeJouBatPtmSurDemat(joueursNonConfianceNonJoue_,
                    joueursConfianceNonJoue_, joueursJoue_, _carteForte,
                    cartesPossibles_)) {
                return PossibleTrickWinner.TEAM;
            }
            return PossibleTrickWinner.UNKNOWN;
            /* Fin joueurDeConfiance.contains(ramasseurVirtuel) */
        }
        /*
        Fin !cartesCertaines.get(couleurDemandee).get(numero).estVide()||
        cartesCertaines
        .get(1).get(numero).estVide()||cartesCertaines.get(couleurAtout())
        .get(numero).premiereCarte().getforceJeu(couleurDemandee)<carteForte.getforceJeu(couleurDemandee) (fin
        test de possibilite pour le joueur numero de prendre le pli)
        */
        /*
        Le joueur numero peut prendre la main en utilisant un atout sur
        demande d'atout
        */
        /*
        On cherche les joueurs de confiance battant de maniere certaine les
        joueurs de non confiance n'ayant pas joue ou possedant des cartes que
        les joueurs ayant joue n'ont pas ainsi que les joueurs de non
        confiance n'ayant pas joue
        */
        if (existeJouBatAdvNumDemat(joueursNonConfianceNonJoue_,
                joueursConfianceNonJoue_, _numero, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*
        On cherche les joueurs de confiance battant de maniere certaine les
        joueurs de non confiance n'ayant pas joue ou possedant des cartes que
        les joueurs ayant joue n'ont pas ainsi que les joueurs de non
        confiance n'ayant pas joue
        */
        if (existeJouBatPtmNumDemat(joueursNonConfianceNonJoue_,
                joueursConfianceNonJoue_, joueursJoue_, _numero,
                cartesPossibles_)) {
            return PossibleTrickWinner.TEAM;
        }
        /*
        On cherche les joueurs de non confiance battant de maniere certaine
        les joueurs de confiance n'ayant pas joue ou possedant des cartes que
        les joueurs ayant joue n'ont pas ainsi que les joueurs de non
        confiance n'ayant pas joue
        */
        if (existeJouBatAdvNumDemat(joueursConfianceNonJoue_,
                joueursNonConfianceNonJoue_, _numero, cartesPossibles_,
                cartesCertaines_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        /*
        On cherche les joueurs de non confiance battant de maniere certaine
        les joueurs de confiance n'ayant pas joue ou possedant des cartes que
        les joueurs ayant joue n'ont pas ainsi que les joueurs de non
        confiance n'ayant pas joue
        */
        if (existeJouBatPtmNumDemat(joueursConfianceNonJoue_,
                joueursNonConfianceNonJoue_, joueursJoue_, _numero,
                cartesPossibles_)) {
            return PossibleTrickWinner.FOE_TEAM;
        }
        return PossibleTrickWinner.UNKNOWN;
    }

    private static boolean existeJouBatPtmNumDemat(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue, byte _numero,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesPossibles.getVal(couleurAtout()).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(couleurAtout())) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for (byte joueur2_ : _joueursJoue) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(couleurAtout())) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(_numero).premiereCarte().strength(couleurAtout()))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJouBatAdvNumDemat(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom, byte _numero,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesCertaines.getVal(couleurAtout()).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(couleurAtout())) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesCertaines.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(_numero).premiereCarte().strength(couleurAtout()))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJouBatPtmSurDemat(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue, CardTarot _carteForte,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesPossibles.getVal(couleurAtout()).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(couleurAtout())) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for (byte joueur2_ : _joueursJoue) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(couleurAtout())) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _carteForte.strength(couleurAtout()))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJouBatAdvSurDemat(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            CardTarot _carteForte, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesCertaines.getVal(couleurAtout()).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(couleurAtout())) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesCertaines.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _carteForte.strength(couleurAtout()))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJouBatPtmDemat(
            Numbers<Byte> _equipeABattre,Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesPossibles.getVal(couleurAtout()).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(couleurAtout())) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for (byte joueur2_ : _joueursJoue) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(couleurAtout())) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJouBatAdvDemat(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (!_cartesCertaines.getVal(couleurAtout()).get(joueur_).estVide()) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(couleurAtout())) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean ramasseurBatAdvDemat(
            Numbers<Byte> _equipeABattre, CardTarot _carteForte,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        boolean ramasseurDeter_ = true;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeABattre) {
            ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                    .get(joueur_).estVide();
            if (!ramasseurVirtuelEgalCertain_) {
                if (_carteForte.strength(couleurAtout()) > _cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(couleurAtout())) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJoueurBatPtmNum(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue, byte _numero, Suit _couleurDemandee,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for (byte joueur2_ : _joueursJoue) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesCertaines.getVal(couleurAtout()).get(_numero).premiereCarte().strength(_couleurDemandee))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJoueurBatAdvNum(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom, byte _numero,
            Suit _couleurDemandee, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesCertaines.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesCertaines.getVal(couleurAtout()).get(_numero).premiereCarte().strength(_couleurDemandee))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJoueurAdvRamBatAdvSur(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Suit _couleurDemandee, CardTarot _carteForte,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (!(_cartesCertaines.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee) > _carteForte.strength(_couleurDemandee))) {
                    joueurBatAdversaire_ = false;
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJoueurNonJoueBattantPtm(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Numbers<Byte> _joueursJoue, Suit _couleurDemandee,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            boolean joueurBatAdversaire_ = true;
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                for (byte joueur2_ : _joueursJoue) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean existeJoueurNonJoueBattantAdv(
            Numbers<Byte> _equipeABattre, Numbers<Byte> _equipeDom,
            Suit _couleurDemandee, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurDeter_ = false;
        boolean ramasseurVirtuelEgalCertain_;
        for (byte joueur_ : _equipeDom) {
            /*
        On cherche les joueurs de confiance
        battant de maniere certaine les
        joueurs de non confiance n'ayant pas
        joue ou possedant des cartes que les
        joueurs ayant joue n'ont pas ainsi
        que les joueurs de non confiance
        n'ayant pas joue
        */
            boolean joueurBatAdversaire_ = true;
            if (vaCouper(_couleurDemandee, joueur_, _cartesPossibles,
                    _cartesCertaines)) {
                for (byte joueur2_ : _equipeABattre) {
                    ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                            .get(joueur2_).estVide();
                    if (!ramasseurVirtuelEgalCertain_) {
                        if (_cartesCertaines.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee) > _cartesPossibles.getVal(couleurAtout()).get(joueur2_).premiereCarte().strength(_couleurDemandee)) {
                            ramasseurVirtuelEgalCertain_ = true;
                        }
                    }
                    if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur2_).estVide()) {
                        ramasseurVirtuelEgalCertain_ = true;
                    }
                    if (!ramasseurVirtuelEgalCertain_) {
                        joueurBatAdversaire_ = false;
                    }
                }
                if (joueurBatAdversaire_) {
                    ramasseurDeter_ = true;
                }
            }
        }
        return ramasseurDeter_;
    }

    private static boolean ramasseurBatAdvSur(Numbers<Byte> _equipeABattre,
            Suit _couleurDemandee, CardTarot _carteForte,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurVirtuelEgalCertain_;
        boolean ramasseurDeter_ = true;
        for (byte joueur_ : _equipeABattre) {
            ramasseurVirtuelEgalCertain_ = _cartesPossibles.getVal(couleurAtout())
                    .get(joueur_).estVide();
            if (!ramasseurVirtuelEgalCertain_) {
                if (_carteForte.strength(_couleurDemandee) > _cartesPossibles.getVal(couleurAtout()).get(joueur_).premiereCarte().strength(_couleurDemandee)) {
                    ramasseurVirtuelEgalCertain_ = true;
                }
            }
            if (!_cartesCertaines.getVal(_couleurDemandee).get(joueur_).estVide()) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }

    private static boolean ramasseurBatSsCprAdv(
            Numbers<Byte> _equipeABattre, Suit _couleurDemandee,
            CardTarot _carteForte, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        boolean ramasseurVirtuelEgalCertain_;
        boolean ramasseurDeter_ = true;
        for (byte joueur_ : _equipeABattre) {
            ramasseurVirtuelEgalCertain_ = !_cartesCertaines
                    .getVal(_couleurDemandee).get(joueur_).estVide();
            if (ramasseurVirtuelEgalCertain_) {
                if (!(_carteForte.strength(_couleurDemandee) > _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().strength(_couleurDemandee))) {
                    ramasseurVirtuelEgalCertain_ = false;
                }
            }
            if (defausse(_cartesPossibles, joueur_, _couleurDemandee)) {
                ramasseurVirtuelEgalCertain_ = true;
            }
            if (!ramasseurVirtuelEgalCertain_) {
                ramasseurDeter_ = false;
            }
        }
        return ramasseurDeter_;
    }

    /**
    Est vrai si et seulement si le partenaire du joueur qui va jouer domine
    l'adversaire n'ayant pas joue (uniquement si le partenaire est maitre
    temporairement du pli)
    */
    private static boolean joueurConfianceRamasseurProbaPli(
            Numbers<Byte> _joueursNonConfianceNonJoue, Suit _couleurDemandee,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, CardTarot _carteForte) {
        boolean bat_ = true;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if(_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide()) {
                continue;
            }
            if (!(_carteForte.strength(_couleurDemandee) > _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().strength(_couleurDemandee))) {
                bat_ = false;
            }
        }
        return bat_;
    }

    private static boolean aucunePriseMainPossibleParFigure(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Numbers<Byte> _joueursNonConfianceNonJoue) {
        boolean carteMaitresse_ = true;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if (!defausse(_cartesPossibles, joueur_,
                    _couleurDemandee)
                    && !nePeutAvoirFigures(
                            _cartesPossibles, joueur_,
                            _couleurDemandee)) {
                carteMaitresse_ = false;
                break;
            }
        }
        return carteMaitresse_;
    }
    private static boolean aucunePriseMainPossibleCouleur(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            CardTarot _carteForte,
            Numbers<Byte> _joueursNonConfianceNonJoue) {
        byte max_ = 0;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if (_cartesPossibles.getVal(_couleurDemandee)
                    .get(joueur_).estVide()) {
                return false;
            }
            max_ = (byte) Math.max(
                    _cartesPossibles.getVal(_couleurDemandee)
                    .get(joueur_).premiereCarte()
                    .strength(_couleurDemandee), max_);
        }
        return _carteForte.strength(_couleurDemandee) > max_;
    }
    private static boolean peutSauverFigureAppele(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            HandTarot _cartesChien,
            Numbers<Byte> _joueursNonConfianceNonJoue,
            boolean _figure) {
        boolean carteMaitresse_ = true;
        for (byte joueur_ : _joueursNonConfianceNonJoue) {
            if (!(peutCouper(_couleurDemandee, joueur_, _cartesPossibles))) {
                carteMaitresse_ = false;
            }
            if (!carteMaitresse_) {
                carteMaitresse_ = true;
                if (!_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide()
                        && !_cartesChien.estVide()) {
                    if (!(_cartesChien.premiereCarte().strength(_couleurDemandee)> _cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().strength(_couleurDemandee))) {
                        carteMaitresse_ = false;
                    }
                }
            } else {
                if (_cartesPossibles.getVal(couleurAtout())
                        .get(joueur_).estVide()) {
                    continue;
                }
                carteMaitresse_ = false;
                break;
            }
        }
        if(!_figure) {
            return false;
        }
        return carteMaitresse_;
    }
    private static boolean premiereSuitePlusLongueQueTotalCouleurDemandee(
            EqList<HandTarot> _suites,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Numbers<Byte> _joueurs) {
        if(!_suites.first().premiereCarte().isCharacter()) {
            return false;
        }
        //il existe une suite de la couleur demandee ordinaire avec une figure
        byte max_ = 0;
        for (byte joueur_ : _joueurs) {
            max_ = (byte) Math.max(
                    _cartesPossibles
                    .getVal(_couleurDemandee)
                    .get(joueur_).total(),
                    max_);
        }
        return _suites.first().total() > max_;
    }
    /**
    Couleur demand&eacute;e atout: retourne vrai si et seulement si le joueur
    numero peut ramasser le pli en jouant son plus grand atout
    */
    private static boolean peutRamasserDemandeAtout(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, byte _numero,
            Numbers<Byte> _joueursNonJoue, Numbers<Byte> _joueursJoue, Suit _couleurDemandee) {
        boolean existe_ = false;
        EqList<HandTarot> atoutsCertains_ = _cartesCertaines.getVal(couleurAtout());
        byte maxAtoutNumero_ = atoutsCertains_.get(_numero)
                .premiereCarte().strength(_couleurDemandee);
        for (byte joueur_ : _joueursNonJoue) {
            if(atoutsCertains_.get(joueur_).estVide()) {
                continue;
            }
            if (atoutsCertains_.get(joueur_).premiereCarte().strength(_couleurDemandee) > maxAtoutNumero_) {
                existe_ = true;
            }
        }
        if (existe_) {
            return false;
        }
        EqList<HandTarot> atoutsPossibles_ = _cartesPossibles.getVal(couleurAtout());
        for (byte joueur_ : _joueursNonJoue) {
            if(atoutsPossibles_.get(joueur_).estVide()) {
                continue;
            }
            byte maxAtoutJoueurNonJoue_ = atoutsPossibles_.get(joueur_).premiereCarte()
                    .strength(_couleurDemandee);
            boolean local_ = true;
            for (byte joueur2_ : _joueursJoue) {
                if (!(maxAtoutJoueurNonJoue_ > atoutsPossibles_.get(joueur2_).premiereCarte().strength(_couleurDemandee))) {
                    local_ = false;
                }
            }
            if (local_) {
                existe_ = true;
            }
        }
        return !existe_;
    }

    private static boolean vaSurcouper(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, byte _numero,
            byte _numeroBis, Suit _couleurDemandee) {
        return _cartesPossibles.getVal(_couleurDemandee).get(_numeroBis).estVide()
                && !_cartesCertaines.getVal(couleurAtout()).get(_numeroBis).estVide()
                && _cartesCertaines.getVal(couleurAtout()).get(_numeroBis).premiereCarte()
                .strength(_couleurDemandee) > _cartesCertaines.getVal(couleurAtout()).get(_numero)
                .premiereCarte().strength(_couleurDemandee);
    }

    private static boolean peutSurcouper(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _numero,
            byte _numeroBis, Suit _couleurDemandee) {
        return _cartesPossibles.getVal(_couleurDemandee).get(_numeroBis).estVide()
                && !_cartesPossibles.getVal(couleurAtout()).get(_numeroBis).estVide()
                && _cartesPossibles.getVal(couleurAtout()).get(_numeroBis).premiereCarte()
                .strength(_couleurDemandee) > _cartesPossibles.getVal(couleurAtout()).get(_numero)
                .premiereCarte().strength(_couleurDemandee);
    }

    /**
    Est vrai si et seulement si on est sur que le joueur va couper le pli a
    la couleur demandee
    */
    private static boolean vaCouper(Suit _couleur, byte _joueur,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        return _cartesPossibles.getVal(_couleur).get(_joueur).estVide()
                && !_cartesCertaines.getVal(couleurAtout()).get(_joueur).estVide();
    }

    private static boolean nePeutCouper(Suit _couleur, byte _numero,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        return _cartesPossibles.getVal(couleurAtout()).get(_numero).estVide()
                || !_cartesCertaines.getVal(_couleur).get(_numero).estVide();
    }

    private static int nombreDeCoupesFranches(EnumMap<Suit,HandTarot> _repartition) {
        int nombre_ = 0;
        for (Suit couleur_ : couleursOrdinaires()) {
            if (_repartition.getVal(couleur_).estVide()) {
                nombre_++;
            }
        }
        return nombre_;
    }



    private static EnumList<Suit> coupesFranchesStrictes(
            CustList<TrickTarot> _plisFaits, EnumMap<Suit,HandTarot> _repartitionCouleur, byte _numero) {
        EnumList<Suit> coupesFranchesStrictes_ = new EnumList<Suit>();
        for (Suit c: couleursOrdinaires()) {
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
                if (!(pli_.carteDuJoueur(_numero).couleur() == couleurAtout())) {
                    coupeToujours_ = false;
                }
            }
            if (!coupeToujours_) {
                continue;
            }
            coupesFranchesStrictes_.add(c);
        }
        return coupesFranchesStrictes_;
    }
    private static Numbers<Byte> tours(Suit _couleur, CustList<TrickTarot> _plisFaits) {
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





    /**
    Retourne l'ensemble des joueurs ayant ramasse les plis Les entameurs des
    plis (sauf le premier car c'est une entame de jeu) sont les ramasseurs
    des plis precedents respectifs
    */
    private static Numbers<Byte> ramasseurs(CustList<TrickTarot> _plisFaits) {
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


    private static CardTarot sauveQuiPeutFigure(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EqList<HandTarot> _suites, EqList<HandTarot> _cartesRelMaitres,
            Numbers<Byte> _joueursDeNonConfianceNonJoue,
            Suit _couleurDemandee) {
        if (_joueursDeNonConfianceNonJoue.isEmpty()) {
            return jeuFigureHauteDePlusFaibleSuite(_suites);
        }
        boolean pasAtout_ = true;
        for (byte joueur_ : _joueursDeNonConfianceNonJoue) {
            if (!_cartesPossibles.getVal(_couleurDemandee).get(joueur_).estVide()) {
                if (_cartesPossibles.getVal(_couleurDemandee).get(joueur_).premiereCarte().isCharacter()) {
                    pasAtout_ = false;
                }
            }
            if (!(_cartesPossibles.getVal(couleurAtout()).get(joueur_).estVide())) {
                pasAtout_ = false;
            }
        }
        if (pasAtout_) {
            return jeuFigureHauteDePlusFaibleSuite(_suites);
        }
        if (!_cartesRelMaitres.isEmpty()) {
            if (_cartesRelMaitres.size() == 1
                    || !_cartesRelMaitres.get(1).premiereCarte().isCharacter()) {
                return _suites.first().premiereCarte();
            }
            //cartesRelMaitres.size() > 1 && cartesRelMaitres.get(1).premiereCarte().estUneFigure()
            return _cartesRelMaitres.get(1).premiereCarte();
        }
        return weakestCard(_suites);
    }

    private static boolean pasAtout(
            Numbers<Byte> _joueursDeNonConfianceNonJoue,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        boolean pasAtout_ = true;
        for (byte joueur_ : _joueursDeNonConfianceNonJoue) {
            if (!(_cartesPossibles.getVal(couleurAtout()).get(joueur_).estVide())) {
                pasAtout_ = false;
            }
        }
        return pasAtout_;
    }

    private static boolean nePeutAvoirFigures(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _numero,
            Suit _couleur) {
        return _cartesPossibles.getVal(_couleur).get(_numero).estVide()
                || !_cartesPossibles.getVal(_couleur).get(_numero).premiereCarte()
                .isCharacter();
    }

    private static boolean defausse(EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            byte _numero, Suit _couleur) {
        return _cartesPossibles.getVal(couleurAtout()).get(_numero).estVide()
                && _cartesPossibles.getVal(_couleur).get(_numero).estVide();
    }

    /**
    Renvoie la plus grande carte basse (n'etant pas une figure) a jouer dans
    la suite la plus faible de la couleur
    */
    private static CardTarot carteLaPlusPetite(EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesFigures_ = new EqList<HandTarot>();
        EqList<HandTarot> suitesCartesBasses_ = new EqList<HandTarot>();
        for(HandTarot m: _suites) {
            int nbFigures_ = m.nombreDeFigures();
            if(nbFigures_ == m.total()) {
                suitesFigures_.add(m);
                continue;
            }
            if(nbFigures_ == 0) {
                suitesCartesBasses_.add(m);
                continue;
            }
            HandTarot suiteFigures_ = new HandTarot();
            HandTarot suiteCartesBasses_ = new HandTarot();
            for(CardTarot c: m) {
                if(c.isCharacter()) {
                    suiteFigures_.ajouter(c);
                    continue;
                }
                suiteCartesBasses_.ajouter(c);
            }
            suitesFigures_.add(suiteFigures_);
            suitesCartesBasses_.add(suiteCartesBasses_);
        }
        if (!suitesCartesBasses_.isEmpty()) {
            return suitesCartesBasses_.last().premiereCarte();
        }
        return suitesFigures_.last().derniereCarte();
    }

    /**
    Renvoie la plus grande carte basse (n'etant pas une figure) a jouer dans
    la suite la plus faible de la couleur
    */
    private static CardTarot weakestCard(EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesFigures_ = new EqList<HandTarot>();
        EqList<HandTarot> suitesCartesBasses_ = new EqList<HandTarot>();
        for(HandTarot m: _suites) {
            int nbFigures_ = m.nombreDeFigures();
            if(nbFigures_ == m.total()) {
                suitesFigures_.add(m);
                continue;
            }
            if(nbFigures_ == 0) {
                suitesCartesBasses_.add(m);
                continue;
            }
            HandTarot suiteFigures_ = new HandTarot();
            HandTarot suiteCartesBasses_ = new HandTarot();
            for(CardTarot c: m) {
                if(c.isCharacter()) {
                    suiteFigures_.ajouter(c);
                    continue;
                }
                suiteCartesBasses_.ajouter(c);
            }
            suitesFigures_.add(suiteFigures_);
            suitesCartesBasses_.add(suiteCartesBasses_);
        }
        if (!suitesCartesBasses_.isEmpty()) {
            return suitesCartesBasses_.last().premiereCarte();
        }
        return suitesFigures_.last().premiereCarte();
    }
    /**
    Renvoie la figure la plus grande dans la suite de figure la moins
    haute(en valeur)
    */
    private static CardTarot jeuFigureHauteDePlusFaibleSuite(
            EqList<HandTarot> _suites) {
        EqList<HandTarot> suitesFigures_ = new EqList<HandTarot>();
        EqList<HandTarot> suitesCartesBasses_ = new EqList<HandTarot>();
        for(HandTarot m: _suites) {
            int nbFigures_ = m.nombreDeFigures();
            if(nbFigures_ == m.total()) {
                suitesFigures_.add(m);
                continue;
            }
            if(nbFigures_ == 0) {
                suitesCartesBasses_.add(m);
                continue;
            }
            //nbFigures > 0 && nbFigures < m.total()
            //==> m.total() >= 2 && !suiteFigures.isEmpty() && !suiteCartesBasses.isEmpty()
            HandTarot suiteFigures_ = new HandTarot();
            HandTarot suiteCartesBasses_ = new HandTarot();
            for(CardTarot c: m) {
                if(c.isCharacter()) {
                    suiteFigures_.ajouter(c);
                    continue;
                }
                suiteCartesBasses_.ajouter(c);
            }
            suitesFigures_.add(suiteFigures_);
            suitesCartesBasses_.add(suiteCartesBasses_);
        }
        if (!suitesFigures_.isEmpty()) {
            return suitesFigures_.last().premiereCarte();
        }
        return suitesCartesBasses_.last().premiereCarte();

    }

    /**Methode retournant un atout different du Petit sauf si ce dernier est sec.*/
    private static CardTarot atoutLePlusPetit(EqList<HandTarot> _suites) {
        if (!_suites.last().contient(CardTarot.petit())) {
            return _suites.last().premiereCarte();
        }
        /* Le joueur a le Petit maintenant */
        //Petit sec ==> suites.size() == 1 && suites.last().total() == 1
        //Or nombre d'atout >= 2, donc suites.last().total() == 1 ==> suites.size() >= 2
        if (_suites.last().total() == 1) {
            //Petit seul dans sa suite
            return _suites.get(_suites.size() - 2).premiereCarte();
        }
        return _suites.last().premiereCarte();
        //Petit accompagne d'un atout dans la meme suite
    }

    /**
    Retourne l'atout le plus grand dans la suite la plus faible si l'Excuse
    n'est pas possedee et le Petit n'est pas dans la main; ou l'Excuse si
    elle est possedee
    @throws RuntimeException nombre d'atout < 2 et !contientExcuse et suites.last().contient(CarteTarot.petit())
    (le Petit est sec)
    */
    private static CardTarot atoutLePlusPetit(EqList<HandTarot> _suites,
            boolean _contientExcuse) {
        if (_contientExcuse) {
            return CardTarot.excuse();
        }
        /* Le joueur n'a pas l'Excuse */
        return atoutLePlusPetit(_suites);
    }

    private static EqList<HandTarot> cartesRelativementMaitreEncours(
            EqList<HandTarot> _suites,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Numbers<Byte> _joueursNAyantPasJoue, Suit _couleurJoueur,
            Suit _couleurDemandee, EnumMap<Suit,EqList<HandTarot>> _cartesCertaines,
            CardTarot _carteForte) {
        byte maxForce_ = 0;
        EqList<HandTarot> suitesBis_ = new EqList<HandTarot>();
        if (!couleursOrdinaires().containsObj(_couleurJoueur) && couleursOrdinaires().containsObj(_couleurDemandee) ) {
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
            if (_carteForte.couleur() == couleurAtout()) {
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

    private static CardTarot defausseAtoutSurAdversaire(
            EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        EnumList<Suit> couleursNonVides_ = couleursNonAtoutNonVides(_main, couleursOrdinaires());
        if (_repartitionCartesJouees.getVal(couleurAtout()).total() > 17) {
            if (_couleursStrictementMaitresses.size() == couleursOrdinaires().size()) {
                return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                        _cartesMaitresses, _main, couleursNonVides_);
            }
            EnumList<Suit> couleurs_ = couleursSansFigures(_main, couleursNonVides_);
            if (!couleurs_.isEmpty()) {
                return jouerPetiteCarteDefausse(_suitesTouteCouleur,
                        couleurs_, _main, _repartitionCartesJouees);
            }
            return jeuPetiteCarteCouleurFigure(_suitesTouteCouleur,
                    couleursNonVides_, _main, _repartitionCartesJouees);
        }
        /*
        Moins de 20 atouts sont joues et moins de 13 cartes de la couleur
        demandee sont jouees
        */
        EnumList<Suit> couleurs_ = couleursSansFigures(_main, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleurs_,
                    _main, _repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(_suitesTouteCouleur, couleursNonVides_,
                _main, _repartitionCartesJouees);
    }

    private static CardTarot defausseCouleurDemandeeSurAdversaire(
            EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses, Suit _couleurDemandee) {
        EnumList<Suit> couleursNonVides_ = couleursNonAtoutNonVides(_main, couleursOrdinaires());
        EnumList<Suit> couleursAutreQueDemandee_ = new EnumList<Suit>();
        for(Suit c: couleursOrdinaires()) {
            if(c == _couleurDemandee) {
                continue;
            }
            couleursAutreQueDemandee_.add(c);
        }
        if (_repartitionCartesJouees.getVal(couleurAtout()).total() > 19
                && _repartitionCartesJouees.getVal(_couleurDemandee).total() > 12) {
            if (_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandee_)) {
                if (!couleursNonVides_.isEmpty()) {
                    return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                            _cartesMaitresses, _main, couleursNonVides_);
                }
            }
            EnumList<Suit> couleursSansFigure_ = couleursSansFigures(_main, couleursNonVides_);
            if (!couleursSansFigure_.isEmpty()) {
                return jouerPetiteCarteDefausse(_suitesTouteCouleur,
                        couleursSansFigure_, _main, _repartitionCartesJouees);
            }
            return jeuPetiteCarteCouleurFigure(_suitesTouteCouleur,
                    couleursNonVides_, _main, _repartitionCartesJouees);
        }
        /*
        Moins de 20 atouts sont joues et moins de 13 cartes de la couleur
        demandee sont jouees
        */
        EnumList<Suit> couleursSansFigure_ = couleursSansFigures(_main, couleursNonVides_);
        if (!couleursSansFigure_.isEmpty()) {
            return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleursSansFigure_,
                    _main, _repartitionCartesJouees);
        }
        return jeuPetiteCarteCouleurFigure(_suitesTouteCouleur, couleursNonVides_,
                _main, _repartitionCartesJouees);
    }

    private static CardTarot jeuPetiteCarteCouleurFigure(
            EnumMap<Suit,EqList<HandTarot>> _suites, EnumList<Suit> _couleursNonVides,
            HandTarot _main,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleursSansFigures_ = couleursSansFigures(_main, _couleursNonVides);
        EnumList<Suit> couleurs_ = _couleursNonVides;
        if(!couleursSansFigures_.isEmpty()) {
            couleurs_ = couleursSansFigures_;

        }
        couleurs_ = couleursAvecLaPlusPetiteCarteBasse(_main, couleurs_);
        couleurs_ = couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = couleursAvecLePlusPetitNbFigures(_main, couleurs_);
        couleurs_ = couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees),couleurs_);
        return carteLaPlusPetite(_suites.getVal(couleurs_.first()));
    }

    private static CardTarot defausseAtoutSurPartenaire(
            EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses) {
        boolean carteMaitresse_;
        EnumMap<Suit,HandTarot> repartition_=_main.couleurs();
        EnumList<Suit> couleursNonVides_ = couleursNonAtoutNonVides(_main, couleursOrdinaires());
        if (_repartitionCartesJouees.getVal(couleurAtout()).total() > 17) {
            if (_couleursStrictementMaitresses.size() == couleursOrdinaires().size()) {
                carteMaitresse_ = true;
                for (Suit couleur_ : _couleursStrictementMaitresses) {
                    if (!(_cartesMaitresses.getVal(couleur_).total() == repartition_.getVal(couleur_).total())) {
                        carteMaitresse_ = false;
                    }
                }
                if (carteMaitresse_) {
                    EnumList<Suit> couleursFigures_ = couleursAvecFigures(_main, couleursNonVides_);
                    if (!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(
                                couleursFigures_, _main);
                    }
                }
                return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                        _cartesMaitresses, _main, couleursNonVides_);
            }
            /*
            Il peut exister une couleur pour se defausser non strictement
            maitresse
            */
            EnumList<Suit> couleurs_ = couleursAvecCarteMaitresse(_main, HandTarot.reunion(_repartitionCartesJouees), couleursOrdinaires());
            if (!couleurs_.isEmpty()) {
                return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                        _cartesMaitresses, _main, couleurs_);
            }
            couleurs_ = couleursAvecFigures(_main, couleursNonVides_);
            if (!couleurs_.isEmpty()) {
                return sauverFiguresDefausse(couleurs_, _main,
                        _repartitionCartesJouees);
            }
            return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleursNonVides_,
                    _main, _repartitionCartesJouees);
        }
        /*
        Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur
        demandee sont jouees
        */
        if (_couleursStrictementMaitresses.size() == couleursOrdinaires().size()) {
            carteMaitresse_ = true;
            for (Suit couleur_ : _couleursStrictementMaitresses) {
                if (!(_cartesMaitresses.getVal(couleur_).total() == repartition_.getVal(couleur_).total())) {
                    carteMaitresse_ = false;
                }
            }
            if (carteMaitresse_) {
                EnumList<Suit> couleursFigures_ = couleursAvecFigures(_main, couleursNonVides_);
                if (!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_,
                            _main);
                }
            }
        }
        EnumList<Suit> couleurs_ = couleursAvecFigures(_main, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_, _main,
                    _repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleursNonVides_,
                _main, _repartitionCartesJouees);
    }

    private static CardTarot defausseCouleurDemandeeSurPartenaire(
            EnumMap<Suit,EqList<HandTarot>> _suitesTouteCouleur,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees,
            HandTarot _main, EnumMap<Suit,HandTarot> _cartesMaitresses,
            EnumList<Suit> _couleursStrictementMaitresses, Suit _couleurDemandee) {
        boolean carteMaitresse_;
        EnumMap<Suit,HandTarot> repartition_=_main.couleurs();
        EnumList<Suit> couleursNonVides_ = couleursNonAtoutNonVides(_main, couleursOrdinaires());
        EnumList<Suit> couleursAutreQueDemandee_ = new EnumList<Suit>();
        for(Suit c: couleursOrdinaires()) {
            if(c == _couleurDemandee) {
                continue;
            }
            couleursAutreQueDemandee_.add(c);
        }
        if (_repartitionCartesJouees.getVal(couleurAtout()).total() > 19
                && _repartitionCartesJouees.getVal(_couleurDemandee).total() > 12) {
            if (_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandee_)) {
                carteMaitresse_ = true;
                for (Suit couleur_ : _couleursStrictementMaitresses) {
                    if (!(_cartesMaitresses.getVal(couleur_).total() == repartition_.getVal(couleur_).total())) {
                        carteMaitresse_ = false;
                    }
                }
                if (carteMaitresse_) {
                    EnumList<Suit> couleursFigures_ = couleursAvecFigures(_main, couleursNonVides_);
                    if (!couleursFigures_.isEmpty()) {
                        return jeuGrandeCarteDefausseMaitre(
                                couleursFigures_, _main);
                    }
                }
                if (!couleursNonVides_.isEmpty()) {
                    return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                            _cartesMaitresses, _main, couleursNonVides_);
                }
            }
            /*
            Il peut exister une couleur pour se defausser non strictement
            maitresse
            */
            EnumList<Suit> couleurs_ = couleursAvecCarteMaitresse(_main, HandTarot.reunion(_repartitionCartesJouees), couleursOrdinaires());
            if (!couleurs_.isEmpty()) {
                return jeuPetiteDefausseMaitre(_suitesTouteCouleur,
                        _cartesMaitresses, _main, couleurs_);
            }
            couleurs_ = couleursAvecFigures(_main, couleursNonVides_);
            if (!couleurs_.isEmpty()) {
                return sauverFiguresDefausse(couleurs_, _main,
                        _repartitionCartesJouees);
            }
            return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleursNonVides_,
                    _main, _repartitionCartesJouees);
        }
        /*
        Moins de 20 atouts sont joues ou moins de 13 cartes de la couleur
        demandee sont jouees
        */
        if (_couleursStrictementMaitresses.containsAllObj(couleursAutreQueDemandee_)) {
            carteMaitresse_ = true;
            for (Suit couleur_ : _couleursStrictementMaitresses) {
                if (!(_cartesMaitresses.getVal(couleur_).total() == repartition_.getVal(couleur_).total())) {
                    carteMaitresse_ = false;
                }
            }
            if (carteMaitresse_) {
                EnumList<Suit> couleursFigures_ = couleursAvecFigures(_main, couleursNonVides_);
                if (!couleursFigures_.isEmpty()) {
                    return jeuGrandeCarteDefausseMaitre(couleursFigures_,
                            _main);
                }
            }
        }
        EnumList<Suit> couleurs_ = couleursAvecFigures(_main, couleursNonVides_);
        if (!couleurs_.isEmpty()) {
            return sauverFiguresDefausse(couleurs_, _main,
                    _repartitionCartesJouees);
        }
        return jouerPetiteCarteDefausse(_suitesTouteCouleur, couleursNonVides_,
                _main, _repartitionCartesJouees);
    }

    private static CardTarot jeuGrandeCarteDefausseMaitre(
            EnumList<Suit> _couleursFiguresNonVide, HandTarot _main) {
        EnumList<Suit> couleursAvecFigures_ = couleursAvecFigures(_main, _couleursFiguresNonVide);
        EnumList<Suit> couleurs_ = _couleursFiguresNonVide;
        if(!couleursAvecFigures_.isEmpty()) {
            couleurs_ = couleursAvecFigures_;
        }
        couleurs_ = couleursLesPlusLongues(_main, couleurs_);
        couleurs_ = couleursLesPlusHautes(_main, couleurs_);
        couleurs_ = couleursAvecLePlusGrandNbFigures(_main, couleurs_);
        return _main.couleur(couleurs_.first()).premiereCarte();
    }

    private static CardTarot sauverFiguresDefausse(
            EnumList<Suit> _couleursFiguresNonVide, HandTarot _main,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleursAvecFigures_ = couleursAvecFigures(_main, _couleursFiguresNonVide);
        EnumList<Suit> couleurs_ = _couleursFiguresNonVide;
        if(!couleursAvecFigures_.isEmpty()) {
            couleurs_ = couleursAvecFigures_;
        }
        couleurs_ = couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = couleursLesPlusLongues(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = couleursLesPlusHautes(_main, couleurs_);
        return _main.couleur(couleurs_.first()).premiereCarte();
    }

    private static CardTarot jeuPetiteDefausseMaitre(
            EnumMap<Suit,EqList<HandTarot>> _suites,
            EnumMap<Suit,HandTarot> _cartesMaitresses,
            HandTarot _main,
            EnumList<Suit> _couleursNonVides) {
        EnumList<Suit> couleursSansFigures_ = couleursSansFigures(_main, _couleursNonVides);
        EnumList<Suit> couleurs_ = _couleursNonVides;
        if(!couleursSansFigures_.isEmpty()) {
            couleurs_ = couleursSansFigures_;

        }
        couleurs_ = couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = couleursLesPlusCourtes(HandTarot.reunion(_cartesMaitresses),couleurs_);
        couleurs_ = couleursLesPlusBasses(_main, couleurs_);
        couleurs_ = couleursAvecLePlusPetitNbFigures(_main, couleurs_);
        return carteLaPlusPetite(_suites.getVal(couleurs_.first()));
    }

    private static CardTarot jouerPetiteCarteDefausse(
            EnumMap<Suit,EqList<HandTarot>> _suites, EnumList<Suit> _couleursNonVides,
            HandTarot _main,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleursSansFigures_ = couleursSansFigures(_main, _couleursNonVides);
        EnumList<Suit> couleurs_ = _couleursNonVides;
        if(!couleursSansFigures_.isEmpty()) {
            couleurs_ = couleursSansFigures_;

        }
        couleurs_ = couleursLesPlusCourtes(_main, couleurs_);
        couleurs_ = couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees),couleurs_);
        couleurs_ = couleursLesPlusBasses(_main, couleurs_);
        return carteLaPlusPetite(_suites.getVal(couleurs_.first()));
    }

    private static CardTarot carteInfFigure(EqList<HandTarot> _suites) {
        byte indiceSuiteJoue_ = (byte) _suites.size();
        byte lastIndex_ = (byte) _suites.getLastIndex();
        for (byte indiceSuite_ = lastIndex_; indiceSuite_ >= CustList.FIRST_INDEX; indiceSuite_--) {
            if (!_suites.get(indiceSuite_).premiereCarte().isCharacter()) {
                indiceSuiteJoue_--;
            } else {
                break;
            }
        }
        return _suites.get(indiceSuiteJoue_).premiereCarte();
    }

    private static CardTarot carteInfVirt(EqList<HandTarot> _suites,
            CardTarot _carteVirtuelle,
            Suit _couleurDemandee) {
        byte indiceSuiteJoue_ = (byte) _suites.size();
        byte lastIndex_ = (byte) _suites.getLastIndex();
        for (byte indiceSuite_ = lastIndex_; indiceSuite_ >= CustList.FIRST_INDEX; indiceSuite_--) {
            if (_suites.get(indiceSuite_).premiereCarte().strength(_couleurDemandee) < _carteVirtuelle
                    .strength(_couleurDemandee)) {
                indiceSuiteJoue_--;
            } else {
                break;
            }
        }
        return _suites.get(indiceSuiteJoue_).premiereCarte();
    }


    private static CardTarot enCoursMiserePetite(EnumList<Suit> _couleurs,
            EnumMap<Suit,HandTarot> _repartition,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursLesPlusCourtes(HandTarot.reunion(_repartition), _couleurs);
        couleurs_ = couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartition), couleurs_);
        couleurs_ = couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = couleursLesPlusBasses(HandTarot.reunion(_repartition), couleurs_);
        couleurs_ = couleursAvecLePlusPetitNbFigures(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        return _repartition.getVal(couleurs_.first()).derniereCarte();
    }

    private static CardTarot depouilleFigureDefausse(
            EnumMap<Suit,HandTarot> _repartition, EnumList<Suit> _couleurs,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursLesPlusCourtes(HandTarot.reunion(_repartition), _couleurs);
        couleurs_ = couleursLesPlusCourtes(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = couleursLesPlusHautes(HandTarot.reunion(_repartition), couleurs_);
        return _repartition.getVal(couleurs_.first()).premiereCarte();
    }

    private static CardTarot singletonFortDepouille(
            EnumMap<Suit,HandTarot> _repartition, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = couleursLesPlusHautes(HandTarot.reunion(_repartition), _couleurs);
        return _repartition.getVal(couleurs_.first()).premiereCarte();
    }

    private static CardTarot depouilleFigureEnCours(
            EnumMap<Suit,HandTarot> _repartition, EnumList<Suit> _couleurs,
            EnumMap<Suit,HandTarot> _repartitionCartesJouees) {
        EnumList<Suit> couleurs_ = couleursLesPlusCourtes(HandTarot.reunion(_repartition), _couleurs);
        couleurs_ = couleursLesPlusLongues(HandTarot.reunion(_repartitionCartesJouees), couleurs_);
        couleurs_ = couleursLesPlusHautes(HandTarot.reunion(_repartition), couleurs_);
        return _repartition.getVal(couleurs_.first()).premiereCarte();
    }

    public CardTarot getCarteJoueee() {
        return playedCard;
    }

    private static EnumList<Suit> intersectionCouleurs(EnumList<Suit> _couleurs1, EnumList<Suit> _couleurs2) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs1) {
            if(!_couleurs2.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    private static EnumList<Suit> complementaireCouleurs(EnumList<Suit> _couleurs, EnumList<Suit> _couleursExclues) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if(!_couleursExclues.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }
    private static EnumList<Suit> couleursNonAtoutAyantNbCartesInfEg(
            HandTarot _main, EnumList<Suit> _couleurs, int _nb) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.total() > _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursNonAtoutAyantNbCartesSupEg(
            HandTarot _main, EnumList<Suit> _couleurs, int _nb) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.total() < _nb) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private EnumList<Suit> couleursNonAppelees(EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursAppelees_ = couleursAppelees();
        for(Suit couleur_: _couleurs) {
            if(couleursAppelees_.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursSansRoi(HandTarot _main,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.tailleRois() > 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursSansCartes(HandTarot _main,
            HandTarot _cartesExclues,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            boolean passerAcouleurSuivante_ = false;
            HandTarot main_ = _main.couleur(couleur_);
            for(CardTarot c: _cartesExclues) {
                if(main_.contient(c)) {
                    passerAcouleurSuivante_ = true;
                    break;
                }
            }
            if (passerAcouleurSuivante_) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursAvecFigures(HandTarot _main,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.nombreDeFigures() == 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursAvecCartesBasses(HandTarot _main,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.cartesBasses(couleur_).estVide()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursAvecCarteMaitresse(HandTarot _main,
            HandTarot _cartesJouees, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        for (Suit couleur_ : _couleurs) {
            HandTarot cartesMaitresses_ = cartesMaitresses(couleursMains_,
                    cartesJouees_).getVal(couleur_);
            if (!cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursSansCarteMaitresse(HandTarot _main,
            HandTarot _cartesJouees, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        for (Suit couleur_ : _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            HandTarot cartesMaitresses_ = cartesMaitresses(couleursMains_,
                    cartesJouees_).getVal(couleur_);
            if (cartesMaitresses_.estVide()) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursNonEntamees(
            CustList<TrickTarot> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        EnumList<Suit> toutesCouleursOrdinaires_ = couleursOrdinaires();
        for (TrickTarot pli_ : _plis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (couleurDemandee_ == couleurAtout()) {
                continue;
            }
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
    private static EnumList<Suit> couleursNonOuvertesNonVides(HandTarot _main,
            CustList<TrickTarot> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = couleursNonAtoutNonVides(_main, _couleurs);
        return couleursNonEntamees(_plis,couleurs_);
    }

    /**
    @param _main
    @param _couleurs
    @return
    */
    private static EnumList<Suit> couleursNonAtoutNonVides(HandTarot _main, EnumList<Suit> _couleurs) {
        return couleursNonAtoutAyantNbCartesSupEg(_main, _couleurs, 1);
    }

    private static EnumList<Suit> couleursOuvertes(HandTarot _main,
            CustList<TrickTarot> _plis, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        EnumList<Suit> toutesCouleursOrdinaires_ = couleursOrdinaires();
        for (TrickTarot pli_ : _plis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (couleurDemandee_ == couleurAtout()) {
                continue;
            }
            if (toutesCouleursOrdinaires_.containsObj(couleurDemandee_)) {
                couleursOuvertes_.add(couleurDemandee_);
            }
        }
        for (Suit couleur_ : _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            if (!couleursOuvertes_.containsObj(couleur_)) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursAvecCartesMaitressesVuesChien(
            HandTarot _main, HandTarot _cartesJouees, HandTarot _chien,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for(Suit couleur_: _couleurs) {
            HandTarot unionLogique_ = new HandTarot();
            unionLogique_.ajouterCartes(_cartesJouees.couleur(couleur_));
            for (CardTarot carte_ : _chien.couleur(couleur_)) {
                if (!unionLogique_.contient(carte_)) {
                    unionLogique_.ajouter(carte_);
                }
            }
            unionLogique_.trierParForceEnCours(couleur_);
            HandTarot cartesMaitressesChien_ = cartesMaitresses(couleursMains_,
                    unionLogique_.couleurs()).getVal(couleur_);
            if (cartesMaitressesChien_.estVide()) {
                continue;
            }
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            couleurs_.add(couleur_);
        }

        return couleurs_;
    }

    private static EnumList<Suit> couleursNonOuvertesAttaque(HandTarot _main,
            CustList<TrickTarot> _plis, Numbers<Byte> _attaquants,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumList<Suit> couleursOuvertes_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        EnumList<Suit> toutesCouleursOrdinaires_ = couleursOrdinaires();
        for (TrickTarot pli_ : _plis) {
            if (!pli_.getVuParToutJoueur()) {
                continue;
            }
            Suit couleurDemandee_ = pli_.couleurDemandee();
            if (couleurDemandee_ == couleurAtout()) {
                continue;
            }
            if (!_attaquants.containsObj(pli_.getEntameur())) {
                continue;
            }
            if (toutesCouleursOrdinaires_.containsObj(couleurDemandee_)) {
                couleursOuvertes_.add(couleurDemandee_);
            }
        }
        for (Suit couleur_ : _couleurs) {
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            if (!couleursOuvertes_.containsObj(couleur_)) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursCoupeePar(HandTarot _main,
            byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        for (Suit couleur_ : _couleurs) {
            if (!_cartesPossibles.getVal(couleur_).get(_joueur).estVide()) {
                continue;
            }
            if (_cartesCertaines.getVal(couleurAtout()).get(_joueur).estVide()) {
                continue;
            }
            if (couleursMains_.getVal(couleur_).estVide()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursNonTotalementJoueesEnFigures(
            HandTarot _main, HandTarot _cartesJouees, EnumList<Suit> _couleurs) {
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (_main.couleur(couleur_).estVide()) {
                continue;
            }
            if (cartesJouees_.getVal(couleur_).nombreDeFigures() == HandTarot.couleurComplete(
                    couleur_).nombreDeFigures()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursMaitres(
            EnumMap<Suit,EqList<HandTarot>> _suites, HandTarot _cartesJouees,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _numero) {
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : couleursOrdinaires()) {
            HandTarot couleurComplete_ = HandTarot.couleurComplete(couleur_);
            if (cartesJouees_.getVal(couleur_).total() == couleurComplete_.total()) {
                couleurs_.add(couleur_);
                continue;
            }
            if (_suites.getVal(couleur_).isEmpty()) {
                couleurs_.add(couleur_);
                continue;
            }
            int max_ = CustList.SIZE_EMPTY;
            /*
            max designe le nombre maximal de cartes probablement
            possedees par un joueur a une couleur donnee
            */
            EqList<HandTarot> possibleSuits_ = _cartesPossibles.getVal(couleur_);
            EqList<HandTarot> possibleExcuse_ = _cartesPossibles.getVal(CardTarot.EXCUSE.couleur());
            int nbPlayers_ = possibleSuits_.getLastIndex();
            for (int joueur_ = CustList.FIRST_INDEX; joueur_ < nbPlayers_; joueur_++) {
                if (joueur_ != _numero) {
                    if (possibleSuits_.get(joueur_).total() + possibleExcuse_.get(joueur_).total() > max_) {
                        max_ = possibleSuits_.get(joueur_).total() + possibleExcuse_.get(joueur_).total();
                    }
                }
            }
            boolean existeCarteMaitresse_ = true;
            CardTarot c = _suites.getVal(couleur_).first().premiereCarte();
            for (CardTarot carte_ : HandTarot.cartesCouleursAuDessusDe(c)) {
                if (!cartesJouees_.getVal(couleur_).contient(carte_)
                        && !_suites.getVal(couleur_).first().contient(carte_)) {
                    existeCarteMaitresse_ = false;
                    break;
                }
            }
            int maitres_ = CustList.SIZE_EMPTY;
            if (existeCarteMaitresse_) {
                maitres_ = _suites.getVal(couleur_).first().total();
            }
            if (maitres_ >= max_ || maitres_ > CustList.SIZE_EMPTY
                    && _suites.getVal(couleur_).size() == CustList.ONE_ELEMENT) {
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }
    private static EnumList<Suit> couleursPseudosMaitres(
            EnumMap<Suit,HandTarot> _couleurs, EnumMap<Suit,HandTarot> _hashMap) {
        EnumList<Suit> nombre_ = new EnumList<Suit>();
        for (Suit couleur_ : couleursOrdinaires()) {
            if (_couleurs.getVal(couleur_).total() == _hashMap.getVal(
                    couleur_).total()
                    && !_couleurs.getVal(couleur_).estVide()) {
                nombre_.add(couleur_);
            }
        }
        return nombre_;
    }

    private static EnumList<Suit> strictCouleursMaitres(
            EnumMap<Suit,EqList<HandTarot>> _suites, EnumMap<Suit,HandTarot> _cartesJouees,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, byte _numero) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : couleursOrdinaires()) {
            HandTarot couleurComplete_ = HandTarot.couleurComplete(couleur_);
            if (_cartesJouees.getVal(couleur_).total() == couleurComplete_.total()) {
                couleurs_.add(couleur_);
                continue;
            }
            if (_suites.getVal(couleur_).isEmpty()) {
                continue;
            }
            int max_ = CustList.SIZE_EMPTY;
            EqList<HandTarot> possibleSuits_ = _cartesPossibles.getVal(couleur_);
            int nbSuits_ = possibleSuits_.size();
            EqList<HandTarot> possibleExcuse_ = _cartesPossibles.getVal(CardTarot.EXCUSE.couleur());
            for (int joueur_ = CustList.FIRST_INDEX; joueur_ < nbSuits_; joueur_++) {
                if (joueur_ != _numero) {
                    if (possibleSuits_.get(joueur_).total() + possibleExcuse_.get(joueur_).total() > max_) {
                        max_ = possibleSuits_.get(joueur_).total() + possibleExcuse_.get(joueur_).total();
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

    private static EnumList<Suit> couleursSansFigures(HandTarot _main,
            EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            HandTarot main_ = _main.couleur(couleur_);
            if (main_.nombreDeFigures() > 0) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursPouvantEtreCoupees(HandTarot _main,
            Numbers<Byte> _joueurs,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            if (_main.couleur(couleur_).estVide()) {
                continue;
            }
            if(joueursSusceptiblesCoupe(_cartesPossibles, couleur_, _joueurs).isEmpty()) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursCoupeeParJoueurs(HandTarot _main,
            Numbers<Byte> _joueurs, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            EnumList<Suit> couleursLoc_ = couleursCoupeePar(_main,
                    joueur_, _cartesPossibles,
                    _cartesCertaines, _couleurs);
            for (Suit couleur_ : couleursLoc_) {
                if(couleurs_.containsObj(couleur_)) {
                    continue;
                }
                couleurs_.add(couleur_);
            }
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursNonCoupeeParJoueurs(HandTarot _main,
            Numbers<Byte> _joueurs, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleursCoupees_ = new EnumList<Suit>();

        for (byte joueur_ : _joueurs) {
            EnumList<Suit> couleursLoc_ = couleursCoupeePar(_main,
                    joueur_, _cartesPossibles,
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

    private static EnumList<Suit> couleursEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for (Suit couleur_ : _couleurs) {
            boolean couleurEntamee_ = false;
            for(TrickTarot pli_: _plisFaits) {
                if (!pli_.getVuParToutJoueur()) {
                    continue;
                }
                if (pli_.couleurDemandee() != couleur_) {
                    continue;
                }
                if (!_joueurs.containsObj(pli_.getEntameur())) {
                    continue;
                }
                couleurEntamee_ = true;
                break;
            }
            if (!couleurEntamee_) {
                continue;
            }
            couleurs_.add(couleur_);
        }
        return couleurs_;
    }

    private static EnumList<Suit> couleursLesPlusCourtes(HandTarot _main,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusCourtes(_main, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusCourtes(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandTarotShortLengthComparator(_main));
    }

    private static EnumList<Suit> couleursAvecLePlusPetitNbFigures(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return couleursTrieesPlusPetitNbFigures(_main,_couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusPetitNbFigures(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandTarotCharShortLengthComparator(_main));
    }

    private static EnumList<Suit> couleursLesPlusLongues(HandTarot _main,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusLongues(_main, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusLongues(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandTarotLongLengthComparator(_main));
    }

    private static EnumList<Suit> couleursAvecLePlusGrandNbFigures(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return couleursTrieesPlusGrandNbFigures(_main,_couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusGrandNbFigures(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new HandTarotCharLongLengthComparator(_main));
    }

    private static EnumList<Suit> couleursLesPlusBasses(HandTarot _main,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusBasses(_main, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusBasses(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowHandTarotComparator(_main));
    }

    private static EnumList<Suit> couleursAvecLaPlusPetiteCarteBasse(HandTarot _main,
            EnumList<Suit> _couleurs) {
        return couleursTrieesAvecLaPlusPetiteCarteBasse(_main, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesAvecLaPlusPetiteCarteBasse(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthLowLastHandTarotComparator(_main));
    }

    private static EnumList<Suit> couleursLesPlusHautes(HandTarot _main,
            EnumList<Suit> _couleurs) {
        return couleursTrieesPlusHautes(_main, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusHautes(
            HandTarot _main, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameStrengthGreatHandTarotComparator(_main));
    }

    private static EnumList<Suit> couleursLesPlusEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return couleursTrieesPlusEntameesParJoueurs(_plisFaits, _joueurs, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesPlusEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameTarotMostDemandedSuitComparator(_plisFaits, _joueurs));
    }

    private static EnumList<Suit> couleursLesMoinsEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return couleursTrieesMoinsEntameesParJoueurs(_plisFaits, _joueurs, _couleurs).first();
    }

    private static CustList<EnumList<Suit>> couleursTrieesMoinsEntameesParJoueurs(
            CustList<TrickTarot> _plisFaits, Numbers<Byte> _joueurs, EnumList<Suit> _couleurs) {
        return _couleurs.getGroupsSameCompare(new GameTarotLeastDemandedSuitComparator(_plisFaits, _joueurs));
    }

    /**
    Retourne l'ensemble des cartes maitresses dans leur propre couleur mais
    pas les atouts maitres donc pour recuperer la couleur n i il faut ecrire
    cartesMaitresses.get(i)
    */
    private static EnumMap<Suit,HandTarot> cartesMaitresses(
            EnumMap<Suit,HandTarot> _couleurs, EnumMap<Suit,HandTarot> _cartesJouees) {
        EnumMap<Suit,HandTarot> suits_ = new EnumMap<Suit,HandTarot>();
        HandTarot pileBase_ = HandTarot.pileBase();
        for (Suit i : couleursOrdinaires()) {
            HandTarot cartes_ = _couleurs.getVal(i);
            HandTarot couleurTotale_ = pileBase_.couleur(i);
            HandTarot cartesJoueesOuPossedees_ = new HandTarot();
            cartesJoueesOuPossedees_.ajouterCartes(_cartesJouees.getVal(i));
            cartesJoueesOuPossedees_.ajouterCartes(cartes_);
            cartesJoueesOuPossedees_.trierParForceEnCours(i);
            HandTarot cartesMaitresses_ = new HandTarot();
            int nbPlayedOrOwnedCards_ = cartesJoueesOuPossedees_.total();
            for (byte c = CustList.FIRST_INDEX; c < nbPlayedOrOwnedCards_; c++) {
                if (!CardTarot.eq(cartesJoueesOuPossedees_.carte(c),
                        couleurTotale_.carte(c))) {
                    break;
                }
                if (!cartes_.contient(cartesJoueesOuPossedees_.carte(c))) {
                    continue;
                }
                cartesMaitresses_.ajouter(cartesJoueesOuPossedees_.carte(c));
            }
            int nbLeadingCards_ = cartesMaitresses_.total();
            if (nbLeadingCards_ + nbLeadingCards_ + _cartesJouees.getVal(i).total() >= couleurTotale_
                    .total()) {
                for (CardTarot carte_ : cartes_) {
                    if (!cartesMaitresses_.contient(carte_)) {
                        cartesMaitresses_.ajouter(carte_);
                    }
                }
            }
            cartesMaitresses_.trierParForceEnCours(i);
            suits_.put(i,cartesMaitresses_);
        }
        return suits_;
    }

    private HandTarot cartesVuesAuChien() {
        HandTarot cartes_ = new HandTarot();
        if (bid.getJeuChien() == PlayingDog.WITH) {
            cartes_.ajouterCartes(getDistribution().derniereMain());
        }
        return cartes_;
    }

    private static CardTarot carteCouleurAppeleeSousCarte(HandTarot _main,
            Suit _couleur,
            HandTarot _carteAppelee) {
        EnumMap<Suit,HandTarot> couleursMains_ = _main.couleurs();
        HandTarot cartesCouleurAppelee_ = _carteAppelee.couleur(_couleur);
        cartesCouleurAppelee_.trierParForceEnCours(_couleur);
        HandTarot couleurAppeleePossedee_ = couleursMains_.getVal(_couleur);
        for (CardTarot c: couleurAppeleePossedee_) {
            if (c.strength(_couleur) < cartesCouleurAppelee_.premiereCarte()
                    .strength(_couleur)) {
                return c;
            }
        }
        return couleurAppeleePossedee_.premiereCarte();
    }

    private EnumList<Suit> couleursAppelees() {
        EnumList<Suit> couleurs_ = new EnumList<Suit>();
        for(CardTarot c: calledCards) {
            if(couleurs_.containsObj(c.couleur())) {
                continue;
            }
            couleurs_.add(c.couleur());
        }
        return couleurs_;
    }
    /**
    Inclut tous les plis sauf celui qui est en cours classes dans l'ordre
    chronologique (par leur numero) On a pour tout pli d'indice i
    unionPlis.get(i).getNumero()==i
    */
    public CustList<TrickTarot> unionPlis(boolean _addAllTricks) {
        CustList<TrickTarot> unionPlis_ = new CustList<TrickTarot>();
        if(existePreneur()) {
            unionPlis_.addAllElts(tricks);
            return unionPlis_;
        }
        if (_addAllTricks) {
            unionPlis_.addAllElts(tricks);
        } else {
            for (TrickTarot t: tricks) {
                if (!t.getVuParToutJoueur()) {
                    continue;
                }
                unionPlis_.add(t);
            }
        }
        return unionPlis_;
    }


    private static boolean plisTousFaitsParPreneurJoueur(byte _preneur,
            CustList<TrickTarot> _unionPlis, byte _nombreJoueurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        joueurs_.add(_preneur);
        Numbers<Byte> autresJoueurs_ = autresJoueurs(joueurs_, _nombreJoueurs);
        for (byte joueur_ : autresJoueurs_) {
            Numbers<Byte> joueursLoc_ = new Numbers<Byte>(joueurs_);
            joueursLoc_.add(joueur_);
            if (plisTousFaitsPar(joueursLoc_, _unionPlis, _nombreJoueurs)) {
                return true;
            }
        }
        return false;
    }

    private static boolean plisTousFaitsPar(Numbers<Byte> _joueurs,
            CustList<TrickTarot> _unionPlis, byte _nombreJoueurs) {
        Numbers<Byte> autresJoueurs_ = autresJoueurs(_joueurs, _nombreJoueurs);
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



    private boolean aucunPliAdverse(byte _joueur, CustList<TrickTarot> _unionPlis) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        Numbers<Byte> partenaires_ = coequipiers(_joueur,
                tousJoueurs(nombreDeJoueurs_));
        partenaires_.add(_joueur);
        return plisTousFaitsPar(partenaires_, _unionPlis, nombreDeJoueurs_);
    }
    /**
    Retourne vrai si et seulement si l'ensemble des joueurs adeverses a un
    joueur donne a fait au moins un pli
    */
    private boolean adversaireAFaitPlis(byte _numero) {
        return !aucunPliAdverse(_numero, unionPlis(false));
    }

    private static Numbers<Byte> tousJoueurs(byte _nombreJoueurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }

    public boolean isSameTeam(Numbers<Byte> _players) {
        int nbPlayers_ = _players.size();
        for (byte i=CustList.SECOND_INDEX;i<nbPlayers_;i++) {
            if (!memeEquipe(_players.getPrev(i), _players.get(i))) {
                return false;
            }
        }
        return true;
    }
    public CustList<Numbers<Byte>> playersBelongingToSameTeam() {
        CustList<Numbers<Byte>> teams_ = new CustList<Numbers<Byte>>();
        if (existePreneur()) {
            Numbers<Byte> takerTeam_ = tousCoequipiers(taker);
            takerTeam_.add(taker);
            teams_.add(takerTeam_);
            byte nombreDeJoueurs_ = getNombreDeJoueurs();
            Numbers<Byte> takerFoeTeam_ = adversaires(taker, tousJoueurs(nombreDeJoueurs_));
            teams_.add(takerFoeTeam_);
        }
        return teams_;
    }
    private static Numbers<Byte> intersectionJoueurs(Numbers<Byte> _joueurs1, Numbers<Byte> _joueurs2) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte j : _joueurs1) {
            if(!_joueurs2.containsObj(j)) {
                continue;
            }
            joueurs_.add(j);
        }
        return joueurs_;
    }

    private static Numbers<Byte> autresJoueurs(Numbers<Byte> _joueurs,
            byte _nombreJoueurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            if (!_joueurs.containsObj(joueur_)) {
                joueurs_.add(joueur_);
            }
        }
        return joueurs_;
    }

    private Numbers<Byte> autresJoueurs(byte _numero) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        return autresJoueurs(new Numbers<Byte>(_numero), nombreDeJoueurs_);
    }

    public Numbers<Byte> orderedPlayers(byte _leader) {
        return rules.getRepartition().getSortedPlayers(_leader);
    }

    public byte playerHavingToBid() {
        byte dealer_ = getDistribution().getDonneur();
        byte playerHavingToBid_ = playerAfter(dealer_);
        int nbBids_ = contrats();
        for (byte b=CustList.FIRST_INDEX;b<nbBids_;b++) {
            playerHavingToBid_ = playerAfter(playerHavingToBid_);
        }
        return playerHavingToBid_;
    }
    public byte playerHavingToPlay() {
        byte leader_ = getPliEnCours().getEntameur();
        for (byte p: rules.getRepartition().getSortedPlayers(leader_)) {
            if (getPliEnCours().aJoue(p, getNombreDeJoueurs())) {
                continue;
            }
            return p;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }
    public byte playerAfter(byte _player) {
        return rules.getRepartition().getNextPlayer(_player);
    }

    /** Utilise l'attribut confiance */
    private Numbers<Byte> joueursNonConfiance(byte _joueur, Numbers<Byte> _joueurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (confidence.get(_joueur).get(joueur_)) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }

    /** Utilise l'attribut confiance */
    private Numbers<Byte> joueursConfiance(byte _joueur, Numbers<Byte> _joueurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (joueur_ == _joueur) {
                continue;
            }
            if (!confidence.get(_joueur).get(joueur_)) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }

    private Numbers<Byte> tousCoequipiers(byte _joueur) {
        Numbers<Byte> equipe_ = new Numbers<Byte>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ : tousJoueurs(nombreDeJoueurs_)) {
            if (joueur_ == _joueur) {
                continue;
            }
            if (memeEquipe(_joueur, joueur_)) {
                equipe_.add(joueur_);
            }
        }
        return equipe_;
    }

    private static boolean justeApresJoueur(byte _joueur, byte _joueurPrecedent,
            byte _nombreJoueurs) {
        return _joueur == (_joueurPrecedent + 1) % _nombreJoueurs;
    }

    private static boolean apresJoueur(byte _joueur, byte _joueurPrecedent,
            byte _nombreJoueurs) {
        byte joueur_ = _joueurPrecedent;
        byte nombreIterations_ = (byte) (_nombreJoueurs / 2);
        if (_nombreJoueurs % 2 == 0) {
            nombreIterations_--;
        }
        for (byte j = CustList.FIRST_INDEX; j < nombreIterations_; j++) {
            joueur_++;
            joueur_ %= _nombreJoueurs;
            if (joueur_ == _joueur) {
                return true;
            }
        }
        return false;
    }

    // Methode appelee pour l'entame de l'appele
    // "defenseurs entre lui et le preneur"
    private Numbers<Byte> joueursAvantAppeleApresPreneur(Numbers<Byte> _joueurs, byte _appele) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        byte player_ = playerAfter(taker);
        //called player exists
        while (!Numbers.eq(player_, _appele)) {
            if (_joueurs.containsObj(player_)) {
                joueurs_.add(player_);
            }
            player_ = playerAfter(player_);
        }
        return joueurs_;
    }

    private boolean defenseOuvreur(byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        Numbers<Byte> attaque_ = new Numbers<Byte>();
        attaque_.add(taker);
        if (appeleConnuDefenseur(_joueur,_cartesPossibles) && existeAppele()) {
            for (byte a: calledPlayers) {
                if (!attaque_.containsObj(a)) {
                    attaque_.add(a);
                }
            }
        }
        if (attaque_.containsObj(_joueur)) {
            return false;
        }
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        boolean justeAvantAttaque_ = false;
        byte nextPlayer_ = playerAfter(_joueur);
        if (nextPlayer_ == taker) {
            justeAvantAttaque_ = true;
        }
        if (existeAppele() && !calledPlayers.containsObj(nextPlayer_)) {
            justeAvantAttaque_ = true;
        }
        if (!justeAvantAttaque_) {
            return false;
        }
        return justeApresJoueur(_joueur, taker, nombreDeJoueurs_);
    }

    private boolean defenseOuvreurStrict(byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        if (!defenseOuvreur(_joueur, _cartesPossibles)) {
            return false;
        }
        if (appeleConnuDefenseur(_joueur,_cartesPossibles) && existeAppele() && !calledPlayers.containsObj(taker)) {
            Numbers<Byte> attaque_ = new Numbers<Byte>();
            attaque_.add(taker);
            for (byte a: calledPlayers) {
                if (!attaque_.containsObj(a)) {
                    attaque_.add(a);
                }
            }
            int nbJoueursAvantEtApres_ = 0;
            int nbJoueursAvant_ = 0;
            int nbJoueursApres_ = 0;
            byte nombreDeJoueurs_ = getNombreDeJoueurs();
            for (byte a: attaque_) {
                boolean existeJoueurJusteAvant_ = false;
                boolean existeJoueurJusteApres_ = false;
                for (byte a2_: attaque_) {
                    if (a == a2_) {
                        continue;
                    }
                    if(justeApresJoueur(a, a2_, nombreDeJoueurs_)) {
                        existeJoueurJusteAvant_ = true;
                    }
                    if(justeApresJoueur(a2_, a, nombreDeJoueurs_)) {
                        existeJoueurJusteApres_ = true;
                    }
                }
                if (existeJoueurJusteAvant_) {
                    if (existeJoueurJusteApres_ ) {
                        nbJoueursAvantEtApres_++;
                    } else {
                        nbJoueursAvant_++;
                    }
                } else if (existeJoueurJusteApres_) {
                    nbJoueursApres_++;
                }
            }
            return nbJoueursAvant_== 1 && nbJoueursApres_ == 1 && nbJoueursAvantEtApres_ == attaque_.size() - 2;
        }
        return false;
    }

    private boolean appeleConnuDefenseur(byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        if (bid.getJeuChien() == PlayingDog.WITH) {
            return joueursConfiance(_joueur, tousJoueurs(nombreDeJoueurs_)).size() + 2 + calledPlayers.size() == nombreDeJoueurs_;
        }
        if (existeCarteAppelee()) {
            boolean aucuneCouleurAppelePreneur_ = true;
            for(Suit c: couleursAppelees()) {
                if (_cartesPossibles.getVal(c).get(taker).estVide()) {
                    continue;
                }
                aucuneCouleurAppelePreneur_ = false;
            }
            if (aucuneCouleurAppelePreneur_) {
                return joueursConfiance(_joueur, tousJoueurs(nombreDeJoueurs_)).size() + 2 + calledPlayers.size() == nombreDeJoueurs_;
            }
        }
        return joueursConfiance(_joueur, tousJoueurs(nombreDeJoueurs_)).size() + 2 == nombreDeJoueurs_;
    }


    private Numbers<Byte> coequipiers(byte _joueur, Numbers<Byte> _joueurs) {
        Numbers<Byte> equipe_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (joueur_ == _joueur) {
                continue;
            }
            if (memeEquipe(_joueur, joueur_)) {
                equipe_.add(joueur_);
            }
        }
        return equipe_;
    }

    private Numbers<Byte> adversaires(byte _joueur, Numbers<Byte> _joueurs) {
        Numbers<Byte> equipe_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (!memeEquipe(_joueur, joueur_)) {
                equipe_.add(joueur_);
            }
        }
        return equipe_;
    }

    private boolean memeEquipe(byte _numero1, byte _numero2) {
        if (existePreneur()) {
            if (aPourDefenseur(_numero1)) {
                return aPourDefenseur(_numero2);
            }
            return !aPourDefenseur(_numero2);
        }
        return confiance(_numero1, _numero2) || _numero1 == _numero2;
    }

    public boolean aPourDefenseur(byte _numero) {
        return _numero != taker && statutDe(_numero) != Status.CALLED_PLAYER;
    }


    /**
    Retourne l'ensemble des joueurs n'ayant pas joue autre que le joueur
    numero
    */
    private Numbers<Byte> joueursNAyantPasJoue(byte _numero) {
        Numbers<Byte> joueursNAyantPasJoue_ = new Numbers<Byte>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            if (joueur_ != _numero && !progressingTrick.aJoue(joueur_, nombreJoueurs_)) {
                joueursNAyantPasJoue_.add(joueur_);
            }
        }
        return joueursNAyantPasJoue_;
    }


    private static Numbers<Byte> joueursPouvantCouperCouleurs(HandTarot _main,
            Numbers<Byte> _joueurs,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles, EnumList<Suit> _couleurs) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if(joueurs_.containsObj(joueur_)) {
                continue;
            }
            for (Suit couleur_ : _couleurs) {
                if (_main.couleur(couleur_).estVide()) {
                    continue;
                }
                if (peutCouper(couleur_, joueur_, _cartesPossibles)) {
                    joueurs_.add(joueur_);
                    break;
                }
            }
        }
        return joueurs_;
    }

    private static Numbers<Byte> joueursSusceptiblesCoupe(
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles,
            Suit _couleurDemandee,
            Numbers<Byte> _joueurs) {
        Numbers<Byte> joueursSusceptiblesDeCouper_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (peutCouper(_couleurDemandee, joueur_,
                    _cartesPossibles)) {
                joueursSusceptiblesDeCouper_
                .add(joueur_);
            }
        }
        return joueursSusceptiblesDeCouper_;
    }
    private static Numbers<Byte> joueursPossedantAtout(Numbers<Byte> _joueurs,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (_cartesCertaines.getVal(couleurAtout()).get(joueur_).estVide()) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }

    private static Numbers<Byte> joueursPouvantPossederPetit(Numbers<Byte> _joueurs,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        EqList<HandTarot> atoutsPossibles_ = _cartesPossibles
                .getVal(couleurAtout());
        for (byte joueur_ : _joueurs) {
            if (!atoutsPossibles_.get(joueur_).contient(CardTarot.petit())) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }

    private static Numbers<Byte> joueursPossedantAtoutMaitre(Numbers<Byte> _joueurs,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, HandTarot _cartesJouees) {
        EnumMap<Suit,HandTarot> cartesJouees_ = _cartesJouees.couleurs();
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        EqList<HandTarot> atoutsCertains_ = _cartesCertaines
                .getVal(couleurAtout());
        for (byte joueur_ : _joueurs) {
            if (atoutsCertains_.get(joueur_).estVide()) {
                continue;
            }
            HandTarot atouts_ = new HandTarot();
            atouts_.ajouterCartes(atoutsCertains_.get(joueur_));
            HandTarot atoutsMaitres_ = atouts_.atoutsMaitres(cartesJouees_);
            if (atoutsMaitres_.estVide()) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }

    private static Numbers<Byte> joueursPossedantNbAtout(Numbers<Byte> _joueurs,
            EnumMap<Suit,EqList<HandTarot>> _cartesCertaines, Rate _nbAtoutMin) {
        Numbers<Byte> joueurs_ = new Numbers<Byte>();
        for (byte joueur_ : _joueurs) {
            if (_cartesCertaines.getVal(couleurAtout()).get(joueur_).total() < _nbAtoutMin.ll()) {
                continue;
            }
            joueurs_.add(joueur_);
        }
        return joueurs_;
    }

    /**
    Retourne vrai si et seulement si le joueur ne peut pas fournir la couleur
    donnee et peut couper avec un atout
    */
    private static boolean peutCouper(Suit _couleur, byte _numero,
            EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        return _cartesPossibles.getVal(_couleur).get(_numero).estVide()
                && !_cartesPossibles.getVal(couleurAtout()).get(_numero).estVide();
    }


    // FIN FONCTION IA












    public void ajouterUneCarteDansPliEnCours(byte _numero, CardTarot _c) {
        jouer(_numero,_c);
        ajouterUneCarteDansPliEnCours(_c);
    }
    void ajouterUneCarteDansPliEnCours(CardTarot _c) {
        progressingTrick.ajouter(_c);
    }

    /**
    A la fin d'un pli on ramasse les cartes et on les ajoute dans des tas
    */
    void ajouterPliEnCours() {

        byte nombreJoueurs_ = getNombreDeJoueurs();
        // nombreJoueurs jouant au tarot
        trickWinner = progressingTrick.getRamasseur();

        // On veut savoir si c'est le dernier tour
        int positionExcuse_ = progressingTrick.getCartes().position(
                CardTarot.excuse());
        if (getDistribution().main(starter).estVide() && positionExcuse_ > -1) {
            //Cas de l'Excuse menee au bout
            byte joueurLAyantPossede_ = (byte) ((positionExcuse_ + starter) % nombreJoueurs_);
            // Nombre de plis faits par les autres joueurs
            byte nombreDePlisAutres_ = 0;
            if (!existePreneur()) {
                tricks.add(progressingTrick);
            } else if (aPourDefenseur(joueurLAyantPossede_)) {
                nombreDePlisAutres_ += getPlisAttaque().size();
                // Le joueur est defenseur
                if (bid.getJeuChien() != PlayingDog.AGAINST) {
                    // Le chien appartient a
                    // l'attaque
                    // Au dernier tour, ici la
                    // defense a effectue un pli
                    // de cartes, le nombre de
                    // cartes
                    // de ce pli vaut le nombre
                    // de joueurs
                    if (nombreDePlisAutres_ > 1) {
                        // Si la defense, avant le
                        // dernier tour n'avait
                        // pas fait de pli, et
                        // si l'Excuse est jouee
                        // par la defense, elle
                        // ne permet pas de
                        // faire de pli
                        if (aPourDefenseur(trickWinner)) {
                            ajouterPliDefense();
                        } else {
                            ajouterPliAttaque();
                        }
                    } else {
                        // Si la defense, avant le dernier tour n'avait pas fait
                        // de pli, et si l'Excuse est jouee
                        // par l'attaque, elle permet non seulement de faire le
                        // pli mais aussi de realiser le grand chelem meme non
                        // demande
                        ajouterPliDefense();
                    }
                } else if (nombreDePlisAutres_ > 0) {
                    if (aPourDefenseur(trickWinner)) {
                        ajouterPliDefense();
                    } else {
                        ajouterPliAttaque();
                    }
                } else {
                    ajouterPliDefense();
                }
            } else {
                nombreDePlisAutres_ += getPlisDefense().size();
                if (bid.getJeuChien() != PlayingDog.AGAINST) {
                    if (nombreDePlisAutres_ > 0) {
                        if (aPourDefenseur(trickWinner)) {
                            ajouterPliDefense();
                        } else {
                            ajouterPliAttaque();
                        }
                    } else {
                        // Si la defense, avant le dernier tour n'avait pas fait
                        // de pli, et si l'Excuse est jouee
                        // par l'attaque, elle permet non seulement de faire le
                        // pli mais aussi de realiser le grand chelem meme non
                        // demande
                        ajouterPliAttaque();
                    }
                } else if (nombreDePlisAutres_ > 1) {
                    // Au dernier tour, ici l'attaque a effectue un pli de
                    // cartes, le nombre de cartes
                    // de ce pli vaut le nombre de joueurs, car le chien
                    // appartient a la defense
                    // Si l'attaque, avant le dernier tour n'avait pas fait de
                    // pli, et si l'Excuse est jouee
                    // par l'attaque, elle ne permet pas de faire de pli
                    if (aPourDefenseur(trickWinner)) {
                        ajouterPliDefense();
                    } else {
                        ajouterPliAttaque();
                    }
                } else {
                    // Au dernier tour, ici l'attaque a effectue un pli de
                    // cartes, le nombre de cartes
                    // de ce pli vaut le nombre de joueurs, car le chien
                    // appartient a la defense
                    // Si la attaque, avant le dernier tour n'avait pas fait de
                    // pli, et si l'Excuse est jouee
                    // par la defense, elle permet non seulement de faire le pli
                    // mais aussi de realiser le grand chelem meme non demande
                    ajouterPliAttaque();
                }
            }
        } else if (!existePreneur()) {
            tricks.add(progressingTrick);
        } else {
            if (aPourDefenseur(trickWinner)) {
                ajouterPliDefense();
            } else {
                ajouterPliAttaque();
            }
        }
        if(!getDistribution().main().estVide()) {
            setEntameur();
        }
    }





    public void ajouterPliAttaque() {
        tricks.add(progressingTrick);
    }
    public void ajouterPliDefense() {
        tricks.add(progressingTrick);
    }
    public byte getRamasseur() {
        return trickWinner;
    }

    /**
    Appele au debut d'un pli mais pas d'une partie, celui qui ramasse entame
    le pli suivant
    */
    private void setEntameur() {
        starter = trickWinner;
    }

    public CustList<TrickTarot> getPlisAttaque() {
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        if (bid.getJeuChien() != PlayingDog.AGAINST) {
            if (!tricks.isEmpty()) {
                tricks_.add(tricks.first());
            }
        }
        for (TrickTarot t: getWonTricksListTeam(taker)) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            tricks_.add(t);
        }
        return tricks_;
    }

    public CustList<TrickTarot> getPlisDefense() {
        CustList<TrickTarot> tricks_ = new CustList<TrickTarot>();
        if (bid.getJeuChien() == PlayingDog.AGAINST) {
            if (!tricks.isEmpty()) {
                tricks_.add(tricks.first());
            }
        }
        for (TrickTarot t: tricks) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            if (!aPourDefenseur(t.getRamasseur())) {
                continue;
            }
            tricks_.add(t);
        }
        return tricks_;
    }


    public void ajouterPetitAuBoutPliEnCours() {
        ajouterPliEnCours();
        ajouterPetitAuBout();
    }

    public void ajouterPetitAuBoutFinPartie() {
        if(getDistribution().main().estVide()) {
            ajouterPliEnCours();
        }
        ajouterPetitAuBout();
    }
    private void ajouterPetitAuBout() {
        if(getDistribution().main().total() > 1) {
            return;
        }
        if(!getPliEnCours().contient(CardTarot.petit())) {
            return;
        }
        if(getDistribution().main().estVide()) {
            /*Le Petit est mene au bout*/
            smallBound.set( trickWinner, true);
            return;
        }
        //getDistribution().main().total() == 1
        Numbers<Byte> partenaires_ = tousCoequipiers(trickWinner);
        boolean possedeExcuseMemeEquipe_ = false;
        for (byte b1_ : partenaires_) {
            if (getDistribution().main(b1_).contient(CardTarot.excuse())) {
                possedeExcuseMemeEquipe_ = true;
            }
        }
        if (getDistribution().main(trickWinner).contient(
                CardTarot.excuse())
                || possedeExcuseMemeEquipe_) {
            if (!adversaireAFaitPlis(trickWinner)) {
                //ajouterPetitAuBoutCasChelem
                smallBound.set( trickWinner, true);
            }
        }
    }

    public boolean petitMeneAuBout(byte _numero) {
        return smallBound.get(_numero);
    }




    public short scoreJoueurPlisDouble(byte _joueur) {
        short nbPointsAtt_ = CustList.SIZE_EMPTY;
        boolean excuseDansPlisAttaque_ = false;
        boolean chelemAttaque_ =false;
        boolean chelemDefense_ =false;
        boolean excuseEcartee_;
        CustList<TrickTarot> plisFaits_ = unionPlis(false);
        if(aucunPliAdverse(_joueur, plisFaits_)) {
            chelemAttaque_ = true;
        }
        if(getWonTricksListTeam(_joueur).isEmpty()) {
            chelemDefense_ = true;
        }
        excuseEcartee_ = tricks.first().contient(CardTarot.excuse());
        for (TrickTarot pli_ : getWonTricksListTeam(_joueur)) {
            if(!pli_.getVuParToutJoueur()) {
                continue;
            }
            if (pli_.contient(CardTarot.excuse())) {
                excuseDansPlisAttaque_ = true;
            }
            for (CardTarot carte_ : pli_) {
                nbPointsAtt_ += carte_.points();
            }
        }
        if(excuseEcartee_) {
            return nbPointsAtt_;
        }
        if(chelemAttaque_) {
            if(!getWonTricksListTeam(_joueur).isEmpty() && !tricks.last().contient(CardTarot.excuse())) {
                for (TrickTarot pli_ : getWonTricksListTeam(_joueur)) {
                    if(!pli_.getVuParToutJoueur()) {
                        continue;
                    }
                    if(!pli_.contient(CardTarot.excuse())) {
                        continue;
                    }
                    byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                    if(!memeEquipe(_joueur, joueurExcuse_)) {
                        nbPointsAtt_ -= CardTarot.excuse().points();
                        break;
                    }
                }
            }
        } else if(chelemDefense_) {
            if(!plisFaits_.isEmpty() && !plisFaits_.last().contient(CardTarot.excuse())) {
                for (TrickTarot pli_ : plisFaits_) {
                    if(!pli_.getVuParToutJoueur()) {
                        continue;
                    }
                    if(!pli_.contient(CardTarot.excuse())) {
                        continue;
                    }
                    byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                    if(memeEquipe(_joueur, joueurExcuse_)) {
                        nbPointsAtt_ += CardTarot.excuse().points();
                        break;
                    }
                }
            }
        } else {
            if(excuseDansPlisAttaque_) {
                if(!getWonTricksListTeam(_joueur).isEmpty() && !tricks.last().contient(CardTarot.excuse())) {
                    for (TrickTarot pli_ : getWonTricksListTeam(_joueur)) {
                        if(!pli_.getVuParToutJoueur()) {
                            continue;
                        }
                        if(!pli_.contient(CardTarot.excuse())) {
                            continue;
                        }
                        byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                        if(!memeEquipe(_joueur, joueurExcuse_)) {
                            nbPointsAtt_ -= CardTarot.excuse().points();
                            break;
                        }
                    }
                }
            } else {
                if(!plisFaits_.isEmpty() && !plisFaits_.last().contient(CardTarot.excuse())) {
                    for (TrickTarot pli_ : plisFaits_) {
                        if(!pli_.getVuParToutJoueur()) {
                            continue;
                        }
                        if(pli_.getRamasseur() == _joueur) {
                            continue;
                        }
                        if(!pli_.contient(CardTarot.excuse())) {
                            continue;
                        }
                        byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                        if(memeEquipe(_joueur, joueurExcuse_)) {
                            nbPointsAtt_ += CardTarot.excuse().points();
                            break;
                        }
                    }
                }
            }
        }
        return nbPointsAtt_;
    }

    public short scoreNecessaireJoueur(byte _joueur) {


        short nombreBouts_ = nombreDeBoutsJoueur(_joueur);
        if (nombreBouts_ == 0) {
            return NO_OUDLER_PTS;
        }
        if (nombreBouts_ == 1) {
            return ONE_OUDLER_PTS;
        }
        if (nombreBouts_ == 2) {
            return TWO_OUDLERS_PTS;
        }
        return ALL_OUDLERS_PTS;
    }

    public short nombreDeBoutsJoueur(byte _joueur) {
        byte nombreBouts_ = 0;
        boolean excuseDansPlisAttaque_ = false;
        boolean chelemAttaque_ =false;
        boolean chelemDefense_ =false;
        boolean excuseEcartee_;
        CustList<TrickTarot> plisFaits_ = unionPlis(false);
        if(aucunPliAdverse(_joueur, plisFaits_)) {
            chelemAttaque_ = true;
        }
        if(getWonTricksTeam(_joueur).estVide()) {
            chelemDefense_ = true;
        }
        excuseEcartee_ = tricks.first().contient(CardTarot.excuse());
        for (TrickTarot pli_ : getWonTricksListTeam(_joueur)) {
            if(!pli_.getVuParToutJoueur()) {
                continue;
            }
            if (pli_.contient(CardTarot.excuse())) {
                excuseDansPlisAttaque_ = true;
            }
            for (CardTarot carte_ : pli_) {
                if(carte_.estUnBout()) {
                    nombreBouts_++;
                }
            }
        }
        if(!excuseEcartee_) {
            if(chelemAttaque_) {
                if(!getWonTricksListTeam(_joueur).isEmpty() && !tricks.last().contient(CardTarot.excuse())) {
                    for (TrickTarot pli_ : getWonTricksListTeam(_joueur)) {
                        if(!pli_.getVuParToutJoueur()) {
                            continue;
                        }
                        if(!pli_.contient(CardTarot.excuse())) {
                            continue;
                        }
                        byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                        if(!memeEquipe(_joueur, joueurExcuse_)) {
                            nombreBouts_--;
                            break;
                        }
                    }
                }
            } else if(chelemDefense_) {
                if(!plisFaits_.isEmpty() && !plisFaits_.last().contient(CardTarot.excuse())) {
                    for (TrickTarot pli_ : plisFaits_) {
                        if(!pli_.getVuParToutJoueur()) {
                            continue;
                        }
                        if(!pli_.contient(CardTarot.excuse())) {
                            continue;
                        }
                        byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                        if(memeEquipe(_joueur, joueurExcuse_)) {
                            nombreBouts_++;
                            break;
                        }
                    }
                }
            } else {
                if(excuseDansPlisAttaque_) {
                    if(!getWonTricksListTeam(_joueur).isEmpty() && !tricks.last().contient(CardTarot.excuse())) {
                        for (TrickTarot pli_ : getWonTricksListTeam(_joueur)) {
                            if(!pli_.getVuParToutJoueur()) {
                                continue;
                            }
                            if(!pli_.contient(CardTarot.excuse())) {
                                continue;
                            }
                            byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                            if(!memeEquipe(_joueur, joueurExcuse_)) {
                                nombreBouts_--;
                                break;
                            }
                        }
                    }
                } else {
                    if(!plisFaits_.isEmpty() && !plisFaits_.last().contient(CardTarot.excuse())) {
                        for (TrickTarot pli_ : plisFaits_) {
                            if(!pli_.getVuParToutJoueur()) {
                                continue;
                            }
                            if(!pli_.contient(CardTarot.excuse())) {
                                continue;
                            }
                            byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                            if(memeEquipe(_joueur, joueurExcuse_)) {
                                nombreBouts_++;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return nombreBouts_;
    }

    public static short differenceJoueurDouble(short _scoreNecessaireJoueur,
            short _scoreJoueurPlisDouble) {
        return (short) (_scoreJoueurPlisDouble - 2 * _scoreNecessaireJoueur);
    }

    public static short differenceJoueurDoubleMisere(
            short _scoreNecessaireJoueur, short _scoreJoueurPlisDouble) {
        return (short) (2 * _scoreNecessaireJoueur - _scoreJoueurPlisDouble);
    }

    public static Numbers<Short> positionsDifference(Numbers<Short> _differences) {
        Numbers<Short> positions_ = new Numbers<Short>();
        int nbDiff_ = _differences.size();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nbDiff_; joueur_++) {
            positions_.add((short) 1);
            for (short difference_ : _differences) {
                if (difference_ > _differences.get(joueur_)) {
                    positions_.set(joueur_, (short) (positions_.get(joueur_)+1));
                }
            }
        }
        return positions_;
    }

    /**
    On classe les joueurs selon certains criteres pour les departager en
    changeant le tableau des positions
    */
    public Numbers<Short> changePositionsOne(Numbers<Short> _positions, boolean _pasJeuMisere) {
        Numbers<Short> positions_ = new Numbers<Short>(_positions);
        CustList<Numbers<Short>> groupes_ = new CustList<Numbers<Short>>();
        Numbers<Short> positionsDistinctes_ = new Numbers<Short>();
        short indice_;
        HandTarot main_;
        HandTarot main2_;
        byte nombreBouts_;
        byte positionTemporaire_;
        byte nombreBouts2_;
        for (short position_ : positions_) {
            if (!positionsDistinctes_.containsObj(position_)) {
                positionsDistinctes_.add(position_);
            }
        }
        for (short position2_ : positionsDistinctes_) {
            groupes_.add(new Numbers<Short>());
            indice_ = 0;
            for (short position_ : positions_) {
                if (position_ == position2_) {
                    groupes_.last().add(indice_);
                }
                indice_++;
            }
        }
        CustList<Numbers<Short>> ensemblesPluriels_ = new CustList<Numbers<Short>>();
        for (Numbers<Short> g: groupes_) {
            if (g.size() < 2) {
                continue;
            }
            ensemblesPluriels_.add(g);
        }
        groupes_ = ensemblesPluriels_;
        if (_pasJeuMisere) {
            for (Numbers<Short> groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        for (short joueur2_ : groupe_) {
                            main2_ = getWonTricksTeam((byte) joueur2_);
                            nombreBouts2_ = (byte) main2_.nombreDeBouts();
                            if (nombreBouts2_ > nombreBouts_) {
                                positionTemporaire_++;
                            }
                        }
                        positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                    }
                }
            }
        } else {
            for (Numbers<Short> groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        for (short joueur2_ : groupe_) {
                            main2_ = getWonTricksTeam((byte)joueur2_);
                            nombreBouts2_ = (byte) main2_.nombreDeBouts();
                            if (nombreBouts2_ < nombreBouts_) {
                                positionTemporaire_++;
                            }
                        }
                        positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                    }
                }
            }
        }
        return positions_;
    }

    /**
    On classe les joueurs selon certains criteres pour les departager en
    changeant le tableau des positions
    */
    public Numbers<Short> changePositionsTwo(Numbers<Short> _positions, boolean _pasJeuMisere) {
        Numbers<Short> positions_ = new Numbers<Short>(_positions);
        CustList<Numbers<Short>> groupes_;
        Numbers<Short> positionsDistinctes_;
        short indice_;
        HandTarot main_;
        HandTarot main2_;
        byte nombreBouts_;
        byte nombreFigures_;
        byte nombreFigures2_;
        byte positionTemporaire_;
        CardTarot bout_;
        CardTarot bout2_;
        groupes_ = new CustList<Numbers<Short>>();
        positionsDistinctes_ = new Numbers<Short>();
        for (short position_ : positions_) {
            if (!positionsDistinctes_.containsObj(position_)) {
                positionsDistinctes_.add(position_);
            }
        }
        for (short position2_ : positionsDistinctes_) {
            groupes_.add(new Numbers<Short>());
            indice_ = 0;
            for (short position_ : positions_) {
                if (position_ == position2_) {
                    groupes_.last().add(indice_);
                }
                indice_++;
            }
        }
        CustList<Numbers<Short>> ensemblesPluriels_ = new CustList<Numbers<Short>>();
        for (Numbers<Short> g: groupes_) {
            if (g.size() < 2) {
                continue;
            }
            ensemblesPluriels_.add(g);
        }
        groupes_ = ensemblesPluriels_;
        if (_pasJeuMisere) {
            for (Numbers<Short> groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        nombreFigures_ = (byte) main_.nombreDeFigures();
                        if (nombreBouts_ == 0) {
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                nombreFigures2_ = (byte) main2_
                                        .nombreDeFigures();
                                if (nombreFigures2_ > nombreFigures_) {
                                    positionTemporaire_++;
                                }
                            }
                        } else {
                            bout_ = main_.bouts().premiereCarte();
                            if (CardTarot.eq(bout_,CardTarot.excuse())) {
                                for (short joueur2_ : groupe_) {
                                    main2_ = getWonTricksTeam((byte) joueur2_);
                                    bout2_ = main2_.bouts().premiereCarte();
                                    if (CardTarot.eq(bout2_,CardTarot.vingtEtUn())) {
                                        positionTemporaire_++;
                                    }
                                }
                            } else if (CardTarot.eq(bout_,CardTarot.petit())) {
                                for (short joueur2_ : groupe_) {
                                    main2_ = getWonTricksTeam((byte) joueur2_);
                                    bout2_ = main2_.bouts().premiereCarte();
                                    if (CardTarot.eq(bout2_,CardTarot.excuse())
                                            || CardTarot.eq(bout2_,CardTarot
                                                    .vingtEtUn())) {
                                        positionTemporaire_++;
                                    }
                                }
                            }
                        }
                        positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                    }
                }
            }
        } else {
            for (Numbers<Short> groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        nombreFigures_ = (byte) main_.nombreDeFigures();
                        if (nombreBouts_ == 0) {
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                nombreFigures2_ = (byte) main2_
                                        .nombreDeFigures();
                                if (nombreFigures2_ < nombreFigures_) {
                                    positionTemporaire_++;
                                }
                            }
                        } else {
                            bout_ = main_.bouts().premiereCarte();
                            if (CardTarot.eq(bout_, CardTarot.excuse())) {
                                for (short joueur2_ : groupe_) {
                                    main2_ = getWonTricksTeam((byte) joueur2_);
                                    bout2_ = main2_.bouts().premiereCarte();
                                    if (CardTarot.eq(bout2_, CardTarot.petit())) {
                                        positionTemporaire_++;
                                    }
                                }
                            } else if (CardTarot.eq(bout_, CardTarot.vingtEtUn())) {
                                for (short joueur2_ : groupe_) {
                                    main2_ = getWonTricksTeam((byte) joueur2_);
                                    bout2_ = main2_.bouts().premiereCarte();
                                    if (CardTarot.eq(bout2_, CardTarot.excuse())
                                            || CardTarot.eq(bout2_, CardTarot.petit())) {
                                        positionTemporaire_++;
                                    }
                                }
                            }
                        }
                        positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                    }
                }
            }
        }
        return positions_;
    }

    /**
    On classe les joueurs selon certains criteres pour les departager en
    changeant le tableau des positions
    */
    public Numbers<Short> changePositionsThree(Numbers<Short> _positions, boolean _pasJeuMisere) {
        Numbers<Short> positions_ = new Numbers<Short>(_positions);

        CustList<Numbers<Short>> groupes_;
        Numbers<Short> positionsDistinctes_;
        short indice_;
        HandTarot main_;
        HandTarot main2_;
        HandTarot figures_;
        HandTarot figures2_;
        byte nombreBouts_;
        byte positionTemporaire_;
        groupes_ = new CustList<Numbers<Short>>();
        positionsDistinctes_ = new Numbers<Short>();
        for (short position_ : positions_) {
            if (!positionsDistinctes_.containsObj(position_)) {
                positionsDistinctes_.add(position_);
            }
        }
        for (short position2_ : positionsDistinctes_) {
            groupes_.add(new Numbers<Short>());
            indice_ = 0;
            for (short position_ : positions_) {
                if (position_ == position2_) {
                    groupes_.last().add(indice_);
                }
                indice_++;
            }
        }
        CustList<Numbers<Short>> ensemblesPluriels_ = new CustList<Numbers<Short>>();
        for (Numbers<Short> g: groupes_) {
            if (g.size() < 2) {
                continue;
            }
            ensemblesPluriels_.add(g);
        }
        groupes_ = ensemblesPluriels_;
        if (_pasJeuMisere) {
            for (Numbers<Short> groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        if (nombreBouts_ == 0) {
                            figures_ = new HandTarot();
                            for (Suit couleur_ : couleursOrdinaires()) {
                                figures_.ajouterCartes(main_.charCardsBySuit(couleur_));
                            }
                            figures_.sortCharsByGreaterPoints();
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                figures2_ = new HandTarot();
                                for (Suit couleur_ : couleursOrdinaires()) {
                                    figures2_.ajouterCartes(main2_
                                            .charCardsBySuit(couleur_));
                                }
                                figures2_.sortCharsByGreaterPoints();
                                positionTemporaire_ = incrementPosByLowerPoints(
                                        figures_, figures2_, positionTemporaire_);
                            }
                            positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                        }
                    }
                }
            }
        } else {
            for (Numbers<Short> groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        if (nombreBouts_ == 0) {
                            figures_ = new HandTarot();
                            for (Suit couleur_ : couleursOrdinaires()) {
                                figures_.ajouterCartes(main_.charCardsBySuit(couleur_));
                            }
                            figures_.sortCharsByGreaterPoints();
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                figures2_ = new HandTarot();
                                for (Suit couleur_ : couleursOrdinaires()) {
                                    figures2_.ajouterCartes(main2_
                                            .charCardsBySuit(couleur_));
                                }
                                figures2_.sortCharsByGreaterPoints();
                                positionTemporaire_ = incrementPosByGreaterPoints(
                                        figures_, figures2_, positionTemporaire_);
                            }
                            positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                        }
                    }
                }
            }
        }
        return positions_;
    }

    private static byte incrementPosByGreaterPoints(HandTarot _charactersOne,
            HandTarot _charactersTwo, byte _positionTmp) {
        byte positionTmp_ = _positionTmp;
        int nbCharacters_ = _charactersOne.total();
        for (int indiceFigure_ = CustList.FIRST_INDEX; indiceFigure_ < nbCharacters_; indiceFigure_++) {
            if (_charactersTwo.carte(indiceFigure_).points() < _charactersOne
                    .carte(indiceFigure_).points()) {
                positionTmp_++;
                break;
            } else if (_charactersTwo.carte(indiceFigure_)
                    .points() > _charactersOne.carte(
                            indiceFigure_).points()) {
                break;
            }
        }
        return positionTmp_;
    }

    private static byte incrementPosByLowerPoints(HandTarot _charactersOne,
            HandTarot _charactersTwo, byte _positionTmp) {
        byte positionTmp_ = _positionTmp;
        int nbCharacters_ = _charactersOne.total();
        for (int indiceFigure_ = CustList.FIRST_INDEX; indiceFigure_ < nbCharacters_; indiceFigure_++) {
            if (_charactersTwo.carte(indiceFigure_).points() > _charactersOne
                    .carte(indiceFigure_).points()) {
                positionTmp_++;
                break;
            } else if (_charactersTwo.carte(indiceFigure_)
                    .points() < _charactersOne.carte(
                            indiceFigure_).points()) {
                break;
            }
        }
        return positionTmp_;
    }

    /**
    On classe les joueurs selon certains criteres pour les departager en
    changeant le tableau des positions
    */
    public Numbers<Short> changePositionsFour(Numbers<Short> _positions, boolean _pasJeuMisere) {
        Numbers<Short> positions_ = new Numbers<Short>(_positions);
        CustList<Numbers<Short>> groupes_;
        Numbers<Short> positionsDistinctes_;
        boolean egaliteFigures_;
        short indice_;
        HandTarot main_;
        HandTarot main2_;
        HandTarot figures_;
        HandTarot figures2_;
        byte nombreBouts_;
        byte positionTemporaire_;
        groupes_ = new CustList<Numbers<Short>>();
        positionsDistinctes_ = new Numbers<Short>();
        for (short position_ : positions_) {
            if (!positionsDistinctes_.containsObj(position_)) {
                positionsDistinctes_.add(position_);
            }
        }
        for (short position2_ : positionsDistinctes_) {
            groupes_.add(new Numbers<Short>());
            indice_ = 0;
            for (short position_ : positions_) {
                if (position_ == position2_) {
                    groupes_.last().add(indice_);
                }
                indice_++;
            }
        }
        CustList<Numbers<Short>> ensemblesPluriels_ = new CustList<Numbers<Short>>();
        for (Numbers<Short> g: groupes_) {
            if (g.size() < 2) {
                continue;
            }
            ensemblesPluriels_.add(g);
        }
        groupes_ = ensemblesPluriels_;
        if (_pasJeuMisere) {
            for (Numbers<Short> groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        if (nombreBouts_ == 0) {
                            figures_ = new HandTarot();
                            for (Suit couleur_ : couleursOrdinaires()) {
                                figures_.ajouterCartes(main_.charCardsBySuit(couleur_));
                            }
                            figures_.sortCharsByGreaterPoints();
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                figures2_ = new HandTarot();
                                for (Suit couleur_ : couleursOrdinaires()) {
                                    figures2_.ajouterCartes(main2_
                                            .charCardsBySuit(couleur_));
                                }
                                figures2_.sortCharsByGreaterPoints();
                                egaliteFigures_ = equalChars(figures_, figures2_);
                                int indexOne_ = -1;
                                int indexTwo_ = -1;
                                int index_ = 0;
                                for (TrickTarot t: tricks) {
                                    if (!t.getVuParToutJoueur()) {
                                        index_++;
                                        continue;
                                    }
                                    if (memeEquipe(t.getRamasseur(), (byte) joueur_)) {
                                        if (indexOne_ == -1) {
                                            indexOne_ = index_;
                                        }
                                    }
                                    if (memeEquipe(t.getRamasseur(), (byte) joueur2_)) {
                                        if (indexTwo_ == -1) {
                                            indexTwo_ = index_;
                                        }
                                    }
                                    index_++;
                                }
                                if (egaliteFigures_
                                        && indexTwo_ <
                                        indexOne_) {
                                    positionTemporaire_++;
                                }
                            }
                            positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                        }
                    }
                }
            }
        } else {
            for (Numbers<Short> groupe_ : groupes_) {
                for (short joueur_ : groupe_) {
                    positionTemporaire_ = 1;
                    main_ = getWonTricksTeam((byte) joueur_);
                    if (!main_.estVide()) {
                        nombreBouts_ = (byte) main_.nombreDeBouts();
                        if (nombreBouts_ == 0) {
                            figures_ = new HandTarot();
                            for (Suit couleur_ : couleursOrdinaires()) {
                                figures_.ajouterCartes(main_.charCardsBySuit(couleur_));
                            }
                            figures_.sortCharsByGreaterPoints();
                            for (short joueur2_ : groupe_) {
                                main2_ = getWonTricksTeam((byte) joueur2_);
                                figures2_ = new HandTarot();
                                for (Suit couleur_ : couleursOrdinaires()) {
                                    figures2_.ajouterCartes(main2_
                                            .charCardsBySuit(couleur_));
                                }
                                figures2_.sortCharsByGreaterPoints();
                                egaliteFigures_ = equalChars(figures_, figures2_);
                                int indexOne_ = -1;
                                int indexTwo_ = -1;
                                int index_ = 0;
                                for (TrickTarot t: tricks) {
                                    if (!t.getVuParToutJoueur()) {
                                        index_++;
                                        continue;
                                    }
                                    if (memeEquipe(t.getRamasseur(), (byte) joueur_)) {
                                        if (indexOne_ == -1) {
                                            indexOne_ = index_;
                                        }
                                    }
                                    if (memeEquipe(t.getRamasseur(), (byte) joueur2_)) {
                                        if (indexTwo_ == -1) {
                                            indexTwo_ = index_;
                                        }
                                    }
                                    index_++;
                                }
                                if (egaliteFigures_
                                        && indexTwo_ >
                                            indexOne_) {
                                    positionTemporaire_++;
                                }
                            }
                            positions_.set(joueur_, (short) (positions_.get(joueur_) + positionTemporaire_ - 1));
                        }
                    }
                }
            }
        }
        return positions_;
    }

    private static boolean equalChars(HandTarot _charactersOne,
            HandTarot _charactersTwo) {
        boolean egaliteFigures_;
        egaliteFigures_ = true;
        int nbCards_ = _charactersOne.total();
        for (int indiceFigure_ = CustList.FIRST_INDEX; indiceFigure_ < nbCards_; indiceFigure_++) {
            if (_charactersTwo.carte(indiceFigure_).points() != _charactersOne
                    .carte(indiceFigure_).points()) {
                egaliteFigures_ = false;
                break;
            }
        }
        return egaliteFigures_;
    }

    public Numbers<Short> coefficients(Numbers<Short> _positions) {
        byte maxPosition_ = 0;
        byte nombreLitiges_;
        byte nombreJoueurs_ = getNombreDeJoueurs();
        Numbers<Short> coefficients_ = new Numbers<Short>();
        for (short position_ : _positions) {
            maxPosition_ = (byte) Math.max(position_, maxPosition_);
        }
        nombreLitiges_ = (byte) (nombreJoueurs_ - maxPosition_ + 1);
        if (rules.getRepartition() == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 1);
                } else {
                    coefficients_.add((short) -1);
                }
            }
            return coefficients_;
        }
        if (rules.getRepartition() == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL) {
            Numbers<Short> positionsDist_ = new Numbers<Short>(_positions);
            positionsDist_.removeDuplicates();
            if (positionsDist_.size() == 3) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -1);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else {
                    coefficients_.add((short) -1);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 3) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -1);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else {
                    coefficients_.add((short) -1);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 4) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 2) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 3);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 6);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 5) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) 0);
                    } else if (position_ == 4) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 2) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 3);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 3) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 6);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 8);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 1) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 3);
                } else if (position_ == 2) {
                    coefficients_.add((short) 2);
                } else if (position_ == 3) {
                    coefficients_.add((short) 1);
                } else if (position_ == 4) {
                    coefficients_.add((short) -1);
                } else if (position_ == 5) {
                    coefficients_.add((short) -2);
                } else {
                    coefficients_.add((short) -3);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 2) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 3);
                } else if (position_ == 2) {
                    coefficients_.add((short) 2);
                } else if (position_ == 3) {
                    coefficients_.add((short) 1);
                } else if (position_ == 4) {
                    coefficients_.add((short) 0);
                } else {
                    coefficients_.add((short) -3);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 3) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 4);
                } else if (position_ == 2) {
                    coefficients_.add((short) 2);
                } else if (position_ == 3) {
                    coefficients_.add((short) 0);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 4) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 8);
                } else if (position_ == 2) {
                    coefficients_.add((short) 0);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add((short) 10);
            } else {
                coefficients_.add((short) -2);
            }
        }
        return coefficients_;
    }

    public Numbers<Short> coefficientsMisere(Numbers<Short> _positions) {
        byte maxPosition_ = 0;
        byte nombreLitiges_;
        byte nombreJoueurs_ = getNombreDeJoueurs();
        Numbers<Short> coefficients_ = new Numbers<Short>();
        for (short position_ : _positions) {
            maxPosition_ = (byte) Math.max(position_, maxPosition_);
        }
        nombreLitiges_ = (byte) (nombreJoueurs_ - maxPosition_ + 1);
        if (rules.getRepartition() == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 1);
                } else {
                    coefficients_.add((short) -1);
                }
            }
            return coefficients_;
        }
        if (rules.getRepartition() == DealingTarot.DEAL_2_VS_4_WITHOUT_CALL) {
            Numbers<Short> positionsDist_ = new Numbers<Short>(_positions);
            positionsDist_.removeDuplicates();
            if (positionsDist_.size() == 3) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -1);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 1);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 3) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -1);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 1);
                } else {
                    coefficients_.add((short) -2);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 4) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 2) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -3);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else {
                    coefficients_.add((short) -6);
                }
            }
            return coefficients_;
        }
        if (nombreJoueurs_ == 5) {
            if (nombreLitiges_ == 1) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 1);
                    } else if (position_ == 3) {
                        coefficients_.add((short) 0);
                    } else if (position_ == 4) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -2);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 2) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else if (position_ == 3) {
                        coefficients_.add((short) -1);
                    } else {
                        coefficients_.add((short) -3);
                    }
                }
                return coefficients_;
            }
            if (nombreLitiges_ == 3) {
                for (short position_ : _positions) {
                    if (position_ == 1) {
                        coefficients_.add((short) 2);
                    } else if (position_ == 2) {
                        coefficients_.add((short) 0);
                    } else {
                        coefficients_.add((short) -6);
                    }
                }
                return coefficients_;
            }
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else {
                    coefficients_.add((short) -8);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 1) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 3);
                } else if (position_ == 2) {
                    coefficients_.add((short) 2);
                } else if (position_ == 3) {
                    coefficients_.add((short) 1);
                } else if (position_ == 4) {
                    coefficients_.add((short) -1);
                } else if (position_ == 5) {
                    coefficients_.add((short) -2);
                } else {
                    coefficients_.add((short) -3);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 2) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 3);
                } else if (position_ == 2) {
                    coefficients_.add((short) 0);
                } else if (position_ == 3) {
                    coefficients_.add((short) -1);
                } else if (position_ == 4) {
                    coefficients_.add((short) -2);
                } else {
                    coefficients_.add((short) -3);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 3) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else if (position_ == 2) {
                    coefficients_.add((short) 0);
                } else if (position_ == 3) {
                    coefficients_.add((short) -2);
                } else {
                    coefficients_.add((short) -4);
                }
            }
            return coefficients_;
        }
        if (nombreLitiges_ == 4) {
            for (short position_ : _positions) {
                if (position_ == 1) {
                    coefficients_.add((short) 2);
                } else if (position_ == 2) {
                    coefficients_.add((short) 0);
                } else {
                    coefficients_.add((short) -8);
                }
            }
            return coefficients_;
        }
        for (short position_ : _positions) {
            if (position_ == 1) {
                coefficients_.add((short) 2);
            } else {
                coefficients_.add((short) -10);
            }
        }
        return coefficients_;
    }

    public CustList<EnumMap<Handfuls,Short>> calculHandfulsScorePlayer(byte _player) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CustList<EnumMap<Handfuls,Short>> scores1_ = new CustList<EnumMap<Handfuls,Short>>();
        for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {
            scores1_.add(new EnumMap<Handfuls,Short>());

            if (joueur2_ == _player) {
                for (Handfuls poignee_ : declaresHandfuls.get(joueur2_)) {
                    scores1_.last().put(poignee_,
                            (short) poignee_.getPoints());
                }
            } else {
                for (Handfuls poignee_ : declaresHandfuls.get(joueur2_)) {
                    scores1_.last().put(poignee_,
                            (short) -poignee_.getPoints());
                }
            }
        }
        return scores1_;
    }
    public CustList<EnumMap<Miseres,Short>> calculMiseresScorePlayer(byte _player) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CustList<EnumMap<Miseres,Short>> scores1_ = new CustList<EnumMap<Miseres,Short>>();
        for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {
            scores1_.add(new EnumMap<Miseres,Short>());

            if (joueur2_ == _player) {
                for (Miseres poignee_ : declaresMiseres.get(joueur2_)) {
                    scores1_.last().put(poignee_,
                            (short) poignee_.getPoints());
                }
            } else {
                for (Miseres poignee_ : declaresMiseres.get(joueur2_)) {
                    scores1_.last().put(poignee_,
                            (short) -poignee_.getPoints());
                }
            }
        }
        return scores1_;
    }
    public CustList<Numbers<Short>> calculSmallLastTurnScorePlayer(byte _player) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CustList<Numbers<Short>> scores1_ = new CustList<Numbers<Short>>();
        for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ < nombreDeJoueurs_; joueur2_++) {
            scores1_.add(new Numbers<Short>());

            if (joueur2_ == _player) {
                if (smallBound.get(joueur2_)) {
                    scores1_.last().add(
                            (short) BonusTarot.SMALL_BOUND.getPoints());
                }
            } else {
                if (smallBound.get(joueur2_)) {
                    scores1_.last().add(
                            (short) -BonusTarot.SMALL_BOUND.getPoints());
                }
            }
        }
        return scores1_;
    }

    public short primeSupplementaire(byte _joueur) {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        CustList<TrickTarot> plisAdversaires_ = new CustList<TrickTarot>();
        for (byte joueur2_ = CustList.FIRST_INDEX; joueur2_ < nombreJoueurs_; joueur2_++) {
            if (!memeEquipe(_joueur, joueur2_)) {
                plisAdversaires_.addAllElts(getWonTricksListTeam(joueur2_));
            }
        }
        if (plisAdversaires_.isEmpty()) {
            return (short) (BonusTarot.SLAM.getPoints() / 2);
        }
        return 0;
    }

    public void calculerScoresJoueurs(Numbers<Short> _coefficients,
            short _differenceMaxDouble) {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        byte nombrePointsChien_ = 0;
        for (TrickTarot t: tricks) {
            if (t.getVuParToutJoueur()) {
                continue;
            }
            for (CardTarot c: t) {
                nombrePointsChien_ += c.points();
            }
        }
        byte parite_;
        if ((_differenceMaxDouble + nombrePointsChien_) / 2 * 2 == _differenceMaxDouble
                + nombrePointsChien_) {
            parite_ = 0;
        } else {
            parite_ = 1;
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            scores.set(joueur_,
                    (short) (4 * (_coefficients.get(joueur_) * (PTS_BASE + (_differenceMaxDouble
                            + nombrePointsChien_ + parite_) / 2))));
        }


    }

    public short differenceMax(short _differenceMaxDouble) {
        byte nombrePointsChien_ = 0;
        for (TrickTarot t: tricks) {
            if (t.getVuParToutJoueur()) {
                continue;
            }
            for (CardTarot c: t) {
                nombrePointsChien_ += c.points();
            }
        }
        byte parite_;
        if ((_differenceMaxDouble + nombrePointsChien_) / 2 * 2 == _differenceMaxDouble
                + nombrePointsChien_) {
            parite_ = 0;
        } else {
            parite_ = 1;
        }
        return (short) ((_differenceMaxDouble + nombrePointsChien_ + parite_) / 2);
    }

    public void calculerScoresJoueurs(Numbers<Short> _coefficients,
            short _differenceMaxDouble, Numbers<Short> _primeSupplementaire) {

        byte nombreJoueurs_ = getNombreDeJoueurs();
        byte nombrePointsChien_ = CustList.SIZE_EMPTY;
        byte joueur2_;
        short pointsAnnoncesJoueur_;
        short pointsAnnoncesAutresJoueurs_;
        short sommePrimeSupplementaire_;
        for (TrickTarot t: tricks) {
            if (t.getVuParToutJoueur()) {
                continue;
            }
            for (CardTarot c: t) {
                nombrePointsChien_ += c.points();
            }
        }
        byte parite_;
        if ((_differenceMaxDouble + nombrePointsChien_) / 2 * 2 == _differenceMaxDouble
                + nombrePointsChien_) {
            parite_ = 0;
        } else {
            parite_ = 1;
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            pointsAnnoncesJoueur_ = 0;
            pointsAnnoncesAutresJoueurs_ = 0;
            joueur2_ = 0;
            sommePrimeSupplementaire_ = 0;
            for (EnumMap<Handfuls,Short> annoncesJoueur_ : calculHandfulsScorePlayer(joueur_)) {
                if (!memeEquipe(joueur2_, joueur_)) {
                    for (short pointsAnnonce_ : annoncesJoueur_.values()) {
                        pointsAnnoncesAutresJoueurs_ += pointsAnnonce_;
                    }
                    sommePrimeSupplementaire_ += _primeSupplementaire.get(joueur2_);
                } else {
                    for (short pointsAnnonce_ : annoncesJoueur_.values()) {
                        pointsAnnoncesJoueur_ += pointsAnnonce_;
                    }
                }
                joueur2_++;
            }
            joueur2_ = 0;
            for (EnumMap<Miseres,Short> annoncesJoueur_ : calculMiseresScorePlayer(joueur_)) {
                if (!memeEquipe(joueur2_, joueur_)) {
                    for (short pointsAnnonce_ : annoncesJoueur_.values()) {
                        pointsAnnoncesAutresJoueurs_ += pointsAnnonce_;
                    }
                    sommePrimeSupplementaire_ += _primeSupplementaire.get(joueur2_);
                } else {
                    for (short pointsAnnonce_ : annoncesJoueur_.values()) {
                        pointsAnnoncesJoueur_ += pointsAnnonce_;
                    }
                }
                joueur2_++;
            }
            joueur2_ = 0;
            for (Numbers<Short> annoncesJoueur_ : calculSmallLastTurnScorePlayer(joueur_)) {
                if (!memeEquipe(joueur2_, joueur_)) {
                    for (short pointsAnnonce_ : annoncesJoueur_) {
                        pointsAnnoncesAutresJoueurs_ += pointsAnnonce_;
                    }
                    sommePrimeSupplementaire_ += _primeSupplementaire.get(joueur2_);
                } else {
                    for (short pointsAnnonce_ : annoncesJoueur_) {
                        pointsAnnoncesJoueur_ += pointsAnnonce_;
                    }
                }
                joueur2_++;
            }
            scores.set(joueur_,
                    (short) (4 * (_coefficients.get(joueur_) * (PTS_BASE + (_differenceMaxDouble
                                    + nombrePointsChien_ + parite_) / 2)
                                    + (nombreJoueurs_ - 1) * (pointsAnnoncesJoueur_ + _primeSupplementaire.get(joueur_))
                                    - pointsAnnoncesAutresJoueurs_ - sommePrimeSupplementaire_)));
        }
    }


    public short scorePreneurPlisDouble() {
        short nbPointsAtt_ = 0;
        boolean excuseDansPlisAttaque_ = false;
        boolean chelemAttaque_ =false;
        boolean chelemDefense_ =false;
        boolean excuseEcartee_ = false;
        if(aucunPliAdverse(taker, unionPlis(false))) {
            chelemAttaque_ = true;
        }
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        if(plisTousFaitsPar(adversaires(taker, tousJoueurs(nombreDeJoueurs_)), unionPlis(false), nombreDeJoueurs_)) {
            chelemDefense_ = true;
        }
        for (TrickTarot pli_ : getPlisAttaque()) {
            if(!pli_.getVuParToutJoueur()) {
                if(pli_.contient(CardTarot.excuse())) {
                    excuseEcartee_ = true;
                }
            }
            if (pli_.contient(CardTarot.excuse())) {
                excuseDansPlisAttaque_ = true;
            }
            for (CardTarot carte_ : pli_) {
                nbPointsAtt_ += carte_.points();
            }
        }
        for (TrickTarot pli_ : getPlisDefense()) {
            if(!pli_.getVuParToutJoueur()) {
                if(pli_.contient(CardTarot.excuse())) {
                    excuseEcartee_ = true;
                }
                break;
            }
        }
        if(excuseEcartee_) {
            return nbPointsAtt_;
        }
        if(chelemAttaque_) {
            if(!getPlisAttaque().last().contient(CardTarot.excuse())) {
                for (TrickTarot pli_ : getPlisAttaque()) {
                    if(!pli_.getVuParToutJoueur()) {
                        continue;
                    }
                    if(!pli_.contient(CardTarot.excuse())) {
                        continue;
                    }
                    byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                    if(!memeEquipe(taker, joueurExcuse_)) {
                        nbPointsAtt_ -= CardTarot.excuse().points()+2;
                        break;
                    }
                }
            }
        } else if(chelemDefense_) {
            if(!getPlisDefense().last().contient(CardTarot.excuse())) {
                for (TrickTarot pli_ : getPlisDefense()) {
                    if(!pli_.getVuParToutJoueur()) {
                        continue;
                    }
                    if(!pli_.contient(CardTarot.excuse())) {
                        continue;
                    }
                    byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                    if(memeEquipe(taker, joueurExcuse_)) {
                        nbPointsAtt_ += CardTarot.excuse().points()-2;
                        break;
                    }
                }
            }
        } else {
            if(excuseDansPlisAttaque_) {
                if(!getPlisAttaque().last().contient(CardTarot.excuse())) {
                    for (TrickTarot pli_ : getPlisAttaque()) {
                        if(!pli_.getVuParToutJoueur()) {
                            continue;
                        }
                        if(!pli_.contient(CardTarot.excuse())) {
                            continue;
                        }
                        byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                        if(!memeEquipe(taker, joueurExcuse_)) {
                            nbPointsAtt_ -= CardTarot.excuse().points();
                            if(nombreDeJoueurs_%2 == 0) {
                                nbPointsAtt_+=2;
                            }
                            break;
                        }
                    }
                }
            } else {
                if(!getPlisDefense().last().contient(CardTarot.excuse())) {
                    for (TrickTarot pli_ : getPlisDefense()) {
                        if(!pli_.getVuParToutJoueur()) {
                            continue;
                        }
                        if(!pli_.contient(CardTarot.excuse())) {
                            continue;
                        }
                        byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                        if(memeEquipe(taker, joueurExcuse_)) {
                            nbPointsAtt_ += CardTarot.excuse().points();
                            if(nombreDeJoueurs_%2 == 0) {
                                nbPointsAtt_-=2;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return nbPointsAtt_;
    }

    public byte nombreBoutsPreneur() {
        byte nombreBouts_ = 0;
        boolean excuseDansPlisAttaque_ = false;
        boolean chelemAttaque_ =false;
        boolean chelemDefense_ =false;
        boolean excuseEcartee_ = false;
        if(aucunPliAdverse(taker, unionPlis(false))) {
            chelemAttaque_ = true;
        }
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        if(plisTousFaitsPar(adversaires(taker, tousJoueurs(nombreDeJoueurs_)), unionPlis(false), nombreDeJoueurs_)) {
            chelemDefense_ = true;
        }
        for (TrickTarot pli_ : getPlisAttaque()) {
            if(!pli_.getVuParToutJoueur()) {
                if(pli_.contient(CardTarot.excuse())) {
                    excuseEcartee_ = true;
                }
            }
            if (pli_.contient(CardTarot.excuse())) {
                excuseDansPlisAttaque_ = true;
            }
            for (CardTarot carte_ : pli_) {
                if(!carte_.estUnBout()) {
                    continue;
                }
                nombreBouts_++;
            }
        }
        for (TrickTarot pli_ : getPlisDefense()) {
            if(!pli_.getVuParToutJoueur()) {
                if(pli_.contient(CardTarot.excuse())) {
                    excuseEcartee_ = true;
                }
                break;
            }
        }
        if(excuseEcartee_) {
            return nombreBouts_;
        }
        if(chelemAttaque_) {
            if(!getPlisAttaque().last().contient(CardTarot.excuse())) {
                for (TrickTarot pli_ : getPlisAttaque()) {
                    if(!pli_.getVuParToutJoueur()) {
                        continue;
                    }
                    if(!pli_.contient(CardTarot.excuse())) {
                        continue;
                    }
                    byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                    if(!memeEquipe(taker, joueurExcuse_)) {
                        nombreBouts_--;
                        break;
                    }
                }
            }
        } else if(chelemDefense_) {
            if(!getPlisDefense().last().contient(CardTarot.excuse())) {
                for (TrickTarot pli_ : getPlisDefense()) {
                    if(!pli_.getVuParToutJoueur()) {
                        continue;
                    }
                    if(!pli_.contient(CardTarot.excuse())) {
                        continue;
                    }
                    byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                    if(memeEquipe(taker, joueurExcuse_)) {
                        nombreBouts_++;
                        break;
                    }
                }
            }
        } else {
            if(excuseDansPlisAttaque_) {
                if(!getPlisAttaque().last().contient(CardTarot.excuse())) {
                    for (TrickTarot pli_ : getPlisAttaque()) {
                        if(!pli_.getVuParToutJoueur()) {
                            continue;
                        }
                        if(!pli_.contient(CardTarot.excuse())) {
                            continue;
                        }
                        byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                        if(!memeEquipe(taker, joueurExcuse_)) {
                            nombreBouts_--;
                            break;
                        }
                    }
                }
            } else {
                if(!getPlisDefense().last().contient(CardTarot.excuse())) {
                    for (TrickTarot pli_ : getPlisDefense()) {
                        if(!pli_.getVuParToutJoueur()) {
                            continue;
                        }
                        if(!pli_.contient(CardTarot.excuse())) {
                            continue;
                        }
                        byte joueurExcuse_ = pli_.joueurAyantJoue(CardTarot.excuse());
                        if(memeEquipe(taker, joueurExcuse_)) {
                            nombreBouts_++;
                        }
                    }
                }
            }
        }
        return nombreBouts_;
    }

    public short scoreNecessairePreneur() {
        byte nombreBouts_ = nombreBoutsPreneur();
        if (nombreBouts_ == 0) {
            return NO_OUDLER_PTS;
        }
        if (nombreBouts_ == 1) {
            return ONE_OUDLER_PTS;
        }
        if (nombreBouts_ == 2) {
            return TWO_OUDLERS_PTS;
        }
        return ALL_OUDLERS_PTS;
    }

    public short scorePreneurPlis(short _scorePreneurPlisDouble,
            short _scoreNecessairePreneur) {
        short scorePreneurPlis_ = (short) (_scorePreneurPlisDouble / 2);
        if (scorePreneurPlis_ >= _scoreNecessairePreneur) {
            if (_scorePreneurPlisDouble % 2 == 1) {
                scorePreneurPlis_++;
            }
        } else if (scorePreneurPlis_ + 1 == _scoreNecessairePreneur) {
            if (rules.getFinPartieTarot() == EndDealTarot.ATTACK_WIN) {
                if (_scorePreneurPlisDouble % 2 == 1) {
                    scorePreneurPlis_++;
                }
            }
        }
        return scorePreneurPlis_;
    }

    public short base(short _scorePreneurPlisDouble,
            short _differenceScorePreneur) {
        if (_differenceScorePreneur >= 0) {
            return PTS_BASE;
        }
        if (_differenceScorePreneur == -1
                && _scorePreneurPlisDouble % 2 == 1) {
            if (rules.getFinPartieTarot() == EndDealTarot.ATTACK_LOOSE) {
                return -PTS_BASE;
            }
            if (rules.getFinPartieTarot() == EndDealTarot.ZERO) {
                return 0;
            }
            return PTS_BASE;
        }
        return -PTS_BASE;
    }

    public short scorePreneurSansAnnonces(short _differenceScorePreneur,
            short _base) {
        short scorePreneurSansAnnonces_ = 0;
        byte nombreJoueurs_ = getNombreDeJoueurs();
        if (_base != 0) {
            scorePreneurSansAnnonces_ = (short) (_base + _differenceScorePreneur);
            if (smallBound.get(taker)) {
                scorePreneurSansAnnonces_ += BonusTarot.SMALL_BOUND
                        .getPoints();
            }
            if (existeAppele()) {
                boolean appelePetitAuBout_ = false;
                for (byte a: calledPlayers) {
                    if (smallBound.get(a)) {
                        appelePetitAuBout_ = true;
                    }
                }
                if(appelePetitAuBout_) {
                    scorePreneurSansAnnonces_ += BonusTarot.SMALL_BOUND
                            .getPoints();
                }
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                if (joueur_ != taker && !calledPlayers.containsObj(joueur_)
                        && smallBound.get(joueur_)) {
                    scorePreneurSansAnnonces_ -= BonusTarot.SMALL_BOUND
                            .getPoints();
                }
            }
        }
        return scorePreneurSansAnnonces_;
    }

    public byte joueurPetitAuBout() {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            if (smallBound.get(joueur_)) {
                return joueur_;
            }
        }
        return -1;
    }

    public CustList<TreeMap<Miseres,Short>> getMiseresPointsForTaker() {

        CustList<TreeMap<Miseres,Short>> scores1_ = new CustList<TreeMap<Miseres,Short>>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreDeJoueurs_; joueur_++) {
            scores1_.add(new TreeMap<Miseres,Short>(new ComparatorEnum<Miseres>()));
            if (joueur_ == taker) {
                for (Miseres m : declaresMiseres.get(joueur_)) {
                    scores1_.last().put(m,
                            (short) m.getPoints());
                }
            } else if (statutDe(joueur_) == Status.CALLED_PLAYER) {
                for (Miseres m : declaresMiseres.get(joueur_)) {
                    scores1_.last().put(m,
                            (short) m.getPoints());
                }
            } else {
                for (Miseres m : declaresMiseres.get(joueur_)) {
                    scores1_.last().put(m,
                            (short) (-m.getPoints()));
                }
            }
        }
        return scores1_;

    }
    public CustList<TreeMap<Handfuls,Short>> getHandfulsPointsForTaker(short _pointsTakerWithoutDeclaring) {

        CustList<TreeMap<Handfuls,Short>> scores1_ = new CustList<TreeMap<Handfuls,Short>>();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreDeJoueurs_; joueur_++) {
            scores1_.add(new TreeMap<Handfuls,Short>(new ComparatorEnum<Handfuls>()));
            for (Handfuls poignee_ : declaresHandfuls.get(joueur_)) {
                if (_pointsTakerWithoutDeclaring >= 0) {
                    scores1_.last().put(poignee_,
                            (short) poignee_.getPoints());
                } else {
                    scores1_.last().put(poignee_,
                            (short) (-poignee_.getPoints()));
                }
            }
        }
        return scores1_;

    }

    public short additionnalBonusesAttack() {
        short primesSupplementaires_ =0;
        CustList<TrickTarot> plisDefense_ = getPlisDefense();
        if (bid == BidTarot.SLAM || declaresSlam.get(taker)) {
            if (plisDefense_.isEmpty()
                    || !plisDefense_.last().getVuParToutJoueur()) {
                primesSupplementaires_ = (short) BonusTarot.SLAM
                        .getPoints();
            } else {
                primesSupplementaires_ = (short) (-BonusTarot.SLAM
                        .getPoints() / 2);
            }
            return primesSupplementaires_;
        }
        if (plisDefense_.isEmpty()
                || !plisDefense_.last().getVuParToutJoueur()) {
            primesSupplementaires_ = (short) (BonusTarot.SLAM
                    .getPoints() / 2);
        }
        return primesSupplementaires_;
    }
    public short additionnalBonusesDefense() {
        short primesSupplementaires_ = 0;
        CustList<TrickTarot> plisAttaque_ = getPlisAttaque();
        if (plisAttaque_.isEmpty()
                || !plisAttaque_.last().getVuParToutJoueur()) {
            primesSupplementaires_ = (short) (BonusTarot.SLAM
                    .getPoints() / 2);
        }
        return primesSupplementaires_;
    }

    public short temporarySum(short _scorePreneurSansAnnonces,
            CustList<TreeMap<Miseres,Short>> _miseres,
            CustList<TreeMap<Handfuls,Short>> _handfuls, short _primesSupplementairesAttack,
            short _primesSupplementairesDefense) {
        short sommeTemporaire_ = 0;
        for (TreeMap<Miseres,Short> m: _miseres) {
            for (short p: m.values()) {
                sommeTemporaire_+=p;
            }
        }
        for (TreeMap<Handfuls,Short> h: _handfuls) {
            for (short p: h.values()) {
                sommeTemporaire_+=p;
            }
        }
        sommeTemporaire_ += _primesSupplementairesAttack
                - _primesSupplementairesDefense;
        if (_scorePreneurSansAnnonces != 0) {
            return (short) (bid.getCoefficient() * _scorePreneurSansAnnonces + sommeTemporaire_);
        }
        return 0;
    }

    public EnumMap<Status,Rate> coefficientsRepartition() {
        EnumMap<Status,Rate> coefficientsRepartition_;
        byte nombreJoueurs_ = getNombreDeJoueurs();
        coefficientsRepartition_ = new EnumMap<Status,Rate>();
        if (!existeAppele()) {
            coefficientsRepartition_.put(Status.TAKER,new Rate(nombreJoueurs_ - 1));
            coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
        } else {
            if (nombreJoueurs_ == 4) {
                if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
                    coefficientsRepartition_.put(Status.TAKER,new Rate(1));
                    coefficientsRepartition_.put(Status.CALLED_PLAYER,new Rate(1));
                    coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
                } else {
                    coefficientsRepartition_.put(Status.TAKER,new Rate(3,2));
                    coefficientsRepartition_.put(Status.CALLED_PLAYER,new Rate(1,2));
                    coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
                }
            } else if (nombreJoueurs_ == 5) {
                coefficientsRepartition_.put(Status.TAKER,new Rate(2));
                coefficientsRepartition_.put(Status.CALLED_PLAYER,new Rate(1));
                coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
            } else if (nombreJoueurs_ == 6) {
                if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
                    coefficientsRepartition_.put(Status.TAKER,new Rate(2));
                    coefficientsRepartition_.put(Status.CALLED_PLAYER,new Rate(2));
                    coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
                } else {
                    coefficientsRepartition_.put(Status.TAKER,new Rate(3));
                    coefficientsRepartition_.put(Status.CALLED_PLAYER,new Rate(1));
                    coefficientsRepartition_.put(Status.DEFENDER,new Rate(-1));
                }
            }
        }
        return coefficientsRepartition_;
    }


    public EndGameState getUserState(short _scorePreneurSansAnnonces, byte _user) {
        if (!aPourDefenseur(_user)) {
            if (_scorePreneurSansAnnonces > 0) {
                return EndGameState.WIN;
            }
            if (_scorePreneurSansAnnonces == 0) {
                return EndGameState.EQUALLITY;
            }
            return EndGameState.LOOSE;
        }
        if (_scorePreneurSansAnnonces < 0) {
            return EndGameState.WIN;
        }
        if (_scorePreneurSansAnnonces == 0) {
            return EndGameState.EQUALLITY;
        }
        return EndGameState.LOOSE;
    }

    public void calculateScores(EnumMap<Status,Rate> _coefficientsRepartition,
            short _sommeTemporaire, short _scorePreneurSansAnnonces) {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        byte parite_;
        if (_sommeTemporaire == 0) {
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                scores.set( joueur_, (short) 0);
            }
        } else {
            if (_sommeTemporaire / 2 * 2 == _sommeTemporaire) {
                parite_ = 0;
            } else {
                parite_ = 1;
            }
            for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
                Status st_ = statutDe(joueur_);
                Rate rate_ = _coefficientsRepartition.getVal(st_);
                if (st_ == Status.DEFENDER) {
                    scores.set(joueur_,
                            (short) Rate.multiply(rate_, new Rate(_sommeTemporaire)).ll());
                } else if (st_ == Status.CALLED_PLAYER) {

                    Rate mult_ = Rate.multiply(new Rate(rate_.getNumeratorCopy()), new Rate(_sommeTemporaire));
                    if (_scorePreneurSansAnnonces > 0) {
                        mult_.removeNb(new Rate(parite_));
                    } else {
                        mult_.addNb(new Rate(parite_));
                    }
                    mult_.divideBy(new Rate(rate_.getDenominatorCopy()));
                    scores.set(joueur_, (short) mult_.ll());
                } else {
                    Rate mult_ = Rate.multiply(new Rate(rate_.getNumeratorCopy()), new Rate(_sommeTemporaire));
                    if (_scorePreneurSansAnnonces > 0) {
                        mult_.addNb(new Rate(parite_));
                    } else {
                        mult_.removeNb(new Rate(parite_));
                    }
                    mult_.divideBy(new Rate(rate_.getDenominatorCopy()));
                    scores.set(joueur_, (short) mult_.ll());
                }
            }
        }
    }

    public Numbers<Short> getScores() {
        return new Numbers<Short>(scores);
    }

    public HandTarot empiler() {
        HandTarot m = new HandTarot();
        if (unionPlis(true).isEmpty()) {
            for (HandTarot main_ : getDistribution()) {
                m.ajouterCartes(main_);
            }
        } else {
            if(existePreneur()) {
                for (TrickTarot pli_ : getPlisAttaque()) {
                    m.ajouterCartes(pli_.getCartes());
                }
                for (TrickTarot pli_ : getPlisDefense()) {
                    m.ajouterCartes(pli_.getCartes());
                }
            } else {
                for (TrickTarot pli_ : tricks) {
                    m.ajouterCartes(pli_.getCartes());
                }
            }
        }
        return m;
    }

    public void restituerMainsDepartRejouerDonne(CustList<TrickTarot> _plisFaits,
            byte _nombreJoueurs) {
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            supprimerCartes(joueur_);
        }
        for (TrickTarot pli_ : _plisFaits) {
            for (CardTarot carte_ : pli_) {
                if (pli_.getVuParToutJoueur()) {
                    ajouter(pli_.joueurAyantJoue(carte_),carte_);
                }
            }
        }
        if (existePreneur()) {
            ajouterCartes(taker,_plisFaits.first().getCartes());
            supprimerCartes(taker,derniereMain());
        }
    }

    private void supprimerCartes(byte _joueur) {
        deal.supprimerCartes(_joueur);
    }

    private void ajouter(byte _joueurAyantJoue, CardTarot _carte) {
        deal.ajouter(_joueurAyantJoue, _carte);
    }

    public void setNombre() {
        number++;
    }

    public boolean getSimulationAvecContrats() {
        return simulationWithBids;
    }

    public GameType getType() {
        return type;
    }
    public String getRaison() {
        return reason.toString();
    }

    public CustList<TrickTarot> getTricks() {
        return tricks;
    }

    private static EnumList<Suit> couleursOrdinaires() {
        return Suit.couleursOrdinaires();
    }

    public int getChargementSimulation() {
        return chargementSimulation.get();
    }

    public void setChargementSimulation(int _chargementSimulation) {
        chargementSimulation.set(_chargementSimulation);
    }

    public DealTarot getDeal() {
        return deal;
    }

    public void setDeal(DealTarot _deal) {
        deal = _deal;
    }

    public CustList<EnumList<Handfuls>> getDeclaresHandfuls() {
        return declaresHandfuls;
    }

    public void setDeclaresHandfuls(CustList<EnumList<Handfuls>> _declaresHandfuls) {
        declaresHandfuls = _declaresHandfuls;
    }

    public EqList<EnumList<Miseres>> getDeclaresMiseres() {
        return declaresMiseres;
    }

    public void setDeclaresMiseres(EqList<EnumList<Miseres>> _declaresMiseres) {
        declaresMiseres = _declaresMiseres;
    }

    public BooleanList getDeclaresSlam() {
        return declaresSlam;
    }

    public void setDeclaresSlam(BooleanList _declaresSlam) {
        declaresSlam = _declaresSlam;
    }

    public BooleanList getSmallBound() {
        return smallBound;
    }

    public void setSmallBound(BooleanList _smallBound) {
        smallBound = _smallBound;
    }

    public EqList<HandTarot> getHandfuls() {
        return handfuls;
    }

    public void setHandfuls(EqList<HandTarot> _handfuls) {
        handfuls = _handfuls;
    }

    public CustList<BooleanList> getConfidence() {
        return confidence;
    }

    public void setConfidence(CustList<BooleanList> _confidence) {
        confidence = _confidence;
    }

    public TrickTarot getProgressingTrick() {
        return progressingTrick;
    }

    public void setProgressingTrick(TrickTarot _progressingTrick) {
        progressingTrick = _progressingTrick;
    }

    public HandTarot getCalledCards() {
        return calledCards;
    }

    public void setCalledCards(HandTarot _calledCards) {
        calledCards = _calledCards;
    }

    public EnumList<BidTarot> getBids() {
        return bids;
    }

    public void setBids(EnumList<BidTarot> _bids) {
        bids = _bids;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long _number) {
        number = _number;
    }

    public RulesTarot getRules() {
        return rules;
    }

    public void setRules(RulesTarot _rules) {
        rules = _rules;
    }

    public void setType(GameType _type) {
        type = _type;
    }

    public void setTricks(CustList<TrickTarot> _tricks) {
        tricks = _tricks;
    }

    public void setScores(Numbers<Short> _scores) {
        scores = _scores;
    }

    public String getError() {
        return error;
    }

    public void setError(String _error) {
        error = _error;
    }

    public HandTarot getCardsToBeDiscarded() {
        return cardsToBeDiscarded;
    }

}
