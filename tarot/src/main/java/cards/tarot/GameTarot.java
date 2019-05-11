package cards.tarot;
import java.util.concurrent.atomic.AtomicInteger;

import cards.consts.CardChar;
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
import code.util.Numbers;

/** */

public final class GameTarot {

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
        if (!avecContrat() || !bid_.isJouerDonne()) {
            initEquipeDetermineeSansPreneur();
            calledPlayers = new Numbers<Byte>();
        } else if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
            initEquipeDeterminee();
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

    public HandTarot derniereMain() {
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
        HandTarot bouts_ = getOulderInHand(couleurs_);
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
        if (total_ >= garde_) {
            if (nombreJoueurs_ == DealingTarot.DEAL_1_VS_2.getNombreJoueurs()) {
                BidTarot f_ = tryGetStrongBid(sansAppel_,12,numero_);
                if (f_.isJouerDonne()) {
                    return f_;
                }
            } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_2_WITHOUT_CALL.getNombreJoueurs()) {
                BidTarot f_ = tryGetStrongBid(sansAppel_,9,numero_);
                if (f_.isJouerDonne()) {
                    return f_;
                }
            } else if (nombreJoueurs_ == DealingTarot.DEAL_2_VS_2_CALL_KING.getNombreJoueurs()) {
                BidTarot f_ = tryGetStrongBid(sansAppel_,7,numero_);
                if (f_.isJouerDonne()) {
                    return f_;
                }
            } else {
                // nombreJoueurs == 6
                BidTarot f_ = tryGetStrongBid(sansAppel_,6,numero_);
                if (f_.isJouerDonne()) {
                    return f_;
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

    BidTarot tryGetStrongBid(boolean _withoutCall,int _trumps, byte _current) {
        HandTarot mj_ = getDistribution().main(_current);
        EnumMap<Suit,HandTarot> couleurs_ = mj_.couleurs();
        int atouts_ = couleurs_.getVal(CardTarot.excuse().couleur()).total() + couleurs_.getVal(couleurAtout()).total();
        HandTarot bouts_ = getOulderInHand(couleurs_);
        byte nombreJoueurs_ = getNombreDeJoueurs();
        int nbCouleurs_ = couleursOrdinaires().size();
        if (_withoutCall) {
            if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs()) == nbCouleurs_) {
                if (atouts_ >= _trumps) {
                    BidTarot e_ = getStrongBid(bouts_);
                    if(e_.isJouerDonne()) {
                        return e_;
                    }
                }
            }
        } else if (nbCouleursMaitresses(couleurs_, new HandTarot().couleurs())
                + nbCouleursPseudoMaitresses(couleurs_, cartesAppeler(_current), nombreJoueurs_) == nbCouleurs_) {
            if (atouts_ >= _trumps) {
                BidTarot e_ = getStrongBid(bouts_);
                if(e_.isJouerDonne()) {
                    return e_;
                }
            }
        }
        return BidTarot.FOLD;
    }

    private static HandTarot getOulderInHand(EnumMap<Suit, HandTarot> _couleurs) {
        HandTarot trumps_ = _couleurs.getVal(Suit.TRUMP);
        HandTarot bouts_ = new HandTarot();
        if (!_couleurs.getVal(CardTarot.EXCUSE.couleur()).estVide()) {
            bouts_.ajouter(CardTarot.excuse());
        }
        if (trumps_.contient(CardTarot.vingtEtUn())) {
            bouts_.ajouter(CardTarot.vingtEtUn());
        }
        if (trumps_.contient(CardTarot.petit())) {
            bouts_.ajouter(CardTarot.petit());
        }
        return bouts_;
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
        Numbers<Byte> defenseurs_=TeamsRelation.autresJoueurs(attaquants_, nombreDeJoueurs_);
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
        tricks.add(progressingTrick);
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
            tricks.add(progressingTrick);
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
        tricks.add(progressingTrick);
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
        tricks.add(progressingTrick);
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
            for(TrickTarot p: getTricks()) {
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
        TeamsRelation teamRel_ = getTeamsRelation();
        if(carteAppeleeJouee_) {
            teamRel_.determinerConfiance(numero_, nombreDeJoueurs_);
            return;
        }
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = cartesJouees_.couleurs();
        CustList<EqList<HandTarot>> suites_ = new CustList<EqList<HandTarot>>();
        suites_.add(new EqList<HandTarot>());
        suites_.add(repartition_.getVal(couleurAtout()).eclaterEnCours(
                repartitionCartesJouees_, couleurAtout()));
        for (Suit i : couleursOrdinaires()) {
            suites_.add(repartition_.getVal(i).eclaterEnCours(
                    repartitionCartesJouees_, i));
        }
        DoneTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = doneTrickInfo_.cartesPossibles(
                teamRel_,
                plisFaits_,
                mainJoueur_,
                numero_,
                derniereMain());
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> hypotheses_ = doneTrickInfo_.cartesCertaines(teamRel_,cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,EqList<HandTarot>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        TrickHypothesis.hypothesesRepartitionsJoueurs(teamRel_,calledCards,bid,plisFaits_, numero_, cartesPossibles_,
                cartesCertaines_);
    }

    private void faireConfiance(byte _joueur, byte _enjoueur) {
        confidence.get(_joueur).set( _enjoueur, true);
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
        TeamsRelation teamRel_ = info_.getTeamsRelation();
        if (teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        EnumList<Suit> couleursPseudosMaitres_ = couleursPseudosMaitres(
                repartition_,
                cartesPseudoMaitresses(repartition_, cartesFictives_,
                        info_.getRepartitionCartesJouees()));
        Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero, joueursNonJoue_);
        Numbers<Byte> joueursNonConfiance_ = teamRel_.joueursNonConfiance(_numero, joueursNonJoue_);
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        if (couleursMaitres_.size() == couleursOrdinaires().size() && strictMaitreAtout_) {
            return playWithStrongestHand(teamRel_,_numero, _mainJoueur, cartesJouees_,
                    plisFaits_, contientExcuse_, carteAppeleeJouee_
            );
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
                    Numbers<Byte> joueurs_ = new Numbers<Byte>(TeamsRelation.autresJoueurs(joueursNonJoue_,nombreDeJoueurs_));
                    for(byte joueur_: TeamsRelation.autresJoueurs(joueurs_,nombreDeJoueurs_)) {
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
                if(carteAppeleeJouee_ && aucunPliAdverse(teamRel_,_numero,plisFaits_)) {
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
            Numbers<Byte> partenaires_ = TrickHypothesis.joueursPouvantCouperCouleurs(_mainJoueur, joueursConfiance_,
                    cartesPossibles_, couleurs_);
            partenaires_ = TrickHypothesis.joueursPossedantAtoutMaitre(partenaires_, cartesCertaines_,
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
            if(!TrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles_,carteCouleur_.couleur(),joueursNonConfiance_).isEmpty()) {
                if(atouts_.total() >= 3) {
                    atouts_.trierParForceEnCours(couleurAtout());
                    return jeuAtoutSuperieurPetit(atouts_);
                }
                if(!TrickHypothesis.joueursPossedantAtoutMaitre(joueursConfiance_,
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
            Numbers<Byte> joueurs_ = new Numbers<Byte>(TeamsRelation.autresJoueurs(joueursNonJoue_,nombreDeJoueurs_));
            for(byte joueur_: TeamsRelation.autresJoueurs(joueurs_, nombreDeJoueurs_)) {
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
        if(!TrickHypothesis.joueursPouvantPossederPetit(joueursConfiance_,
                cartesPossibles_).isEmpty()) {
            int nbAtoutsMaitres_ = atoutsMaitres_.total();
            if(nbAtoutsMaitres_ > 0) {
                atoutsMaitres_.trierParForceEnCours(couleurAtout());
                return atoutsMaitres_.premiereCarte();
            }
        }
        if(!TrickHypothesis.joueursPossedantAtoutMaitre(joueursConfiance_,
                cartesCertaines_, cartesJouees_).isEmpty()) {
            if(atouts_.contient(CardTarot.petit())) {
                return CardTarot.petit();
            }
        }
        Rate moyenneAtout_ = moyenneAtout(_mainJoueur, atoutsJoues_,
                cartesPossibles_, nombreDeJoueurs_);
        if(!TrickHypothesis.joueursPossedantNbAtout(joueursConfiance_,
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
        if(teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
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
                if(!appeleConnuDefenseur(teamRel_,_numero,cartesPossibles_)) {
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
        if(defenseOuvreur(teamRel_,_numero, cartesPossibles_)) {
            if(defenseOuvreurStrict(teamRel_,_numero, cartesPossibles_)) {
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
        Numbers<Byte> defenseurs_ = joueursAvantAppeleApresPreneur(TeamsRelation.tousJoueurs(_nbPlayers),_player);
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
            Numbers<Byte> joueurs_ = new Numbers<Byte>(TeamsRelation.autresJoueurs(_playerNoPlay,_nbPlayers));
            EnumList<Suit> couleursPossiblementCoupees_ = TrickHypothesis.couleursPouvantEtreCoupees(_hand,
                    TeamsRelation.autresJoueurs(joueurs_,_nbPlayers),
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
    private CardTarot playWithStrongestHand(TeamsRelation _teamRel, byte _player, HandTarot _hand,
                                            HandTarot _playedCards, CustList<TrickTarot> _tricks,
                                            boolean _containsExcuse, boolean _playedCalledCard) {
        /*
        Cas ou le joueur entameur
        deborde les autres joueurs en
        couleurs et en atout
        */
        byte nbPlayers_ = _teamRel.getNombreDeJoueurs();
        if (taker == _player || !existePreneur()) {
            //Preneur
            if (_containsExcuse) {
                if (_playedCalledCard) {
                    if(aucunPliAdverse(_teamRel,_player,_tricks)) {
                        return jeuMainMaitresse(_hand,_playedCards);
                    }
                    return CardTarot.excuse();
                }
                if(!_hand.contientCartes(getCarteAppelee())) {
                    if(plisTousFaitsParPreneurJoueur(taker,_tricks,nbPlayers_)) {
                        return jeuMainMaitresse(_hand,_playedCards);
                    }
                    return CardTarot.excuse();
                }
                Numbers<Byte> joueursRamasseurs_ = new Numbers<Byte>();
                joueursRamasseurs_.add(taker);
                if(DoneTrickInfo.plisTousFaitsPar(joueursRamasseurs_,_tricks,nbPlayers_)) {
                    return jeuMainMaitresse(_hand,_playedCards);
                }
                return CardTarot.excuse();
            }
            return jeuMainMaitresse(_hand,_playedCards);
        }
        if(_teamRel.statutDe(_player) == Status.CALLED_PLAYER) {
            //Appele
            //existeAppele
            if (_containsExcuse) {
                if(aucunPliAdverse(_teamRel,_player,_tricks)) {
                    return jeuMainMaitresse(_hand,_playedCards);
                }
                return CardTarot.excuse();
            }
            return jeuMainMaitresse(_hand,_playedCards);
        }
        if (_containsExcuse) {
            Numbers<Byte> joueursConfianceNumero_ = new Numbers<Byte>(_teamRel.joueursConfiance(_player,TeamsRelation.tousJoueurs(nbPlayers_)));
            joueursConfianceNumero_.add(_player);
            if(DoneTrickInfo.plisTousFaitsPar(joueursConfianceNumero_,_tricks,nbPlayers_)) {
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
                if (TrickHypothesis.vaCouper(couleur_, joueur_, cartesPossibles_, cartesCertaines_)) {
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
        TeamsRelation teamRel_ = info_.getTeamsRelation();
        if (teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero, joueursNonJoue_);
        Numbers<Byte> joueursNonConfiance_ = teamRel_.joueursNonConfiance(_numero, joueursNonJoue_);
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        if(starter == taker) {
            if(teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
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
                if(TrickHypothesis.joueursPossedantAtout(joueursConfiance_, cartesCertaines_).isEmpty()) {
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
                        plisFaits_, teamRel_.adversaires(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_)), couleursNonVidesAjouer_);
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
            if(appeleConnuDefenseur(teamRel_,_numero,cartesPossibles_)) {
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
        TeamsRelation teamRel_ = info_.getTeamsRelation();
        if (teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }
        Numbers<Byte> joueursNonJoue_ = info_.getJoueursNonJoue();
        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*CarteTarot temporairement
        maitresse*/
        PossibleTrickWinner ramasseurCertain_ = TrickHypothesis.equipeQuiVaFairePli(
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
                        suites_,cartesPossibles_,couleurDemandee_,teamRel_.autresJoueurs(_numero))) {
                    return repartitionCouleDem_.premiereCarte();
                }
                return weakestCard(suites_);
            }
            if (_mainJoueur.total() == 2 && contientExcuse_) {
                Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
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
        Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = teamRel_.joueursNonConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfianceNonJoue_ = TeamsRelation.intersectionJoueurs(joueursNonConfiance_,joueursNonJoue_);

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
            joueursSusceptiblesDeCouper_ = TrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles_,couleurDemandee_,joueursNonJoue_);
            if (!joueursSusceptiblesDeCouper_.isEmpty()) {
                if (!TeamsRelation.intersectionJoueurs(joueursNonConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_ && contientExcuse_) {
                        return CardTarot.excuse();
                    }
                    return carteLaPlusPetite(suites_);
                }
                if (!TeamsRelation.intersectionJoueurs(joueursConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_) {
                        if (contientExcuse_) {
                            return CardTarot.excuse();
                        }
                        if (premiereSuitePlusLongueQueTotalCouleurDemandee(
                                suites_,cartesPossibles_,couleurDemandee_,teamRel_.autresJoueurs(_numero))) {
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
        if (teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
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
            joueursSusceptiblesDeCouper_ = TrickHypothesis.joueursSusceptiblesCoupe(cartesPossibles_,couleurDemandee_,joueursNonJoue_);
            if (!joueursSusceptiblesDeCouper_.isEmpty()) {
                if (!TeamsRelation.intersectionJoueurs(joueursNonConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_ && contientExcuse_) {
                        return CardTarot.excuse();
                    }
                    return carteLaPlusPetite(suites_);
                }
                if (!TeamsRelation.intersectionJoueurs(joueursConfiance_, joueursSusceptiblesDeCouper_).isEmpty()) {
                    if (maitreJeu_) {
                        if (contientExcuse_) {
                            return CardTarot.excuse();
                        }
                        if (premiereSuitePlusLongueQueTotalCouleurDemandee(
                                suites_,cartesPossibles_,couleurDemandee_,teamRel_.autresJoueurs(_numero))) {
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
            if (TrickHypothesis.pasAtout(joueursNonConfianceNonJoue_,
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
                        if (TrickHypothesis.defausse(cartesPossibles_, joueur_, couleurDemandee_)) {
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
                if (TrickHypothesis.pasAtout(joueursNonConfianceNonJoue_,
                        cartesPossibles_)) {
                    return sauveQuiPeutFigure(cartesPossibles_,
                            suites_, cartesRelMaitres_,
                            joueursNonConfianceNonJoue_,
                            couleurDemandee_);
                }
            }
            return carteLaPlusPetite(suites_);
        }
        if (TrickHypothesis.pasAtout(joueursNonConfianceNonJoue_,
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
        TeamsRelation teamRel_ = info_.getTeamsRelation();
        if (teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = TrickHypothesis.equipeQuiVaFairePli(
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
                Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
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
            Numbers<Byte> joueursNonConfiance_ = teamRel_.joueursNonConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
            if (TrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles_)) {
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
        Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = teamRel_.joueursNonConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfianceNonJoue_ = TeamsRelation.intersectionJoueurs(joueursNonConfiance_, joueursNonJoue_);

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
                    _numero, joueursNonJoue_, TeamsRelation.autresJoueurs(joueursNonJoue_, nombreDeJoueurs_), couleurDemandee_) && !joueursNonConfianceNonJoue_.isEmpty()) {
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
        TeamsRelation teamRel_ = info_.getTeamsRelation();
        if (teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }
        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = TrickHypothesis.equipeQuiVaFairePli(
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
                Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
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
            Numbers<Byte> joueursNonConfiance_ = teamRel_.joueursNonConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
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
        Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfiance_ = teamRel_.joueursNonConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
        Numbers<Byte> joueursNonConfianceNonJoue_ = TeamsRelation.intersectionJoueurs(joueursNonConfiance_, joueursNonJoue_);
        Numbers<Byte> joueursConfianceNonJoue_ = TeamsRelation.intersectionJoueurs(joueursConfiance_, joueursNonJoue_);

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
            if (TrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles_)) {
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
            if (TrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles_)) {
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
                        if (!(TrickHypothesis.nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
                            carteMaitresse_ = false;
                        }
                    }
                    if (carteMaitresse_) {
                        return CardTarot.petit();
                    }
                    return atoutLePlusPetit(suites_);
                }
                /* Appele */
                if (teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
                    return CardTarot.petit();
                }
                if (atoutsCoupe_.total() < 5) {
                    return CardTarot.petit();
                }
                carteMaitresse_ = true;
                for (byte joueur_ : joueursNonConfianceNonJoue_) {
                    if (!(TrickHypothesis.nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
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
                if (!(TrickHypothesis.nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
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
                if (!(TrickHypothesis.nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
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
                        if (TrickHypothesis.vaSurcouper(cartesPossibles_, cartesCertaines_, _numero, joueur_, couleurDemandee_)) {
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
                            if (!(TrickHypothesis.peutSurcouper(cartesPossibles_, joueur2_, joueur_, couleurDemandee_))) {
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
                    if(TrickHypothesis.nePeutAvoirFigures(cartesPossibles_, joueur_, couleurDemandee_)) {
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
                        if(TrickHypothesis.peutCouper(
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
        if (TrickHypothesis.pasAtout(joueursNonConfiance_, cartesPossibles_)) {
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
            if (!(TrickHypothesis.nePeutCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_))) {
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
                if (TeamsRelation.intersectionJoueurs(joueursNonConfiance_, TeamsRelation.autresJoueurs(joueursCoupePreTour_, nombreDeJoueurs_)).isEmpty()) {
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
        TeamsRelation teamRel_ = info_.getTeamsRelation();
        if (teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = TrickHypothesis.equipeQuiVaFairePli(
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
                Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
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
            Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
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
        TeamsRelation teamRel_ = info_.getTeamsRelation();
        if (teamRel_.statutDe(_numero) == Status.CALLED_PLAYER) {
            cartesFictives_.ajouterCartes(cartesChien_);
        }

        byte ramasseurVirtuel_ = info_.getRamasseurVirtuel();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CardTarot carteForte_ = progressingTrick.carteDuJoueur(ramasseurVirtuel_, nombreDeJoueurs_);
        /*
        CarteTarot temporairement
        maitresse
        */
        PossibleTrickWinner ramasseurCertain_ = TrickHypothesis.equipeQuiVaFairePli(
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
                Numbers<Byte> joueursConfiance_ = teamRel_.joueursConfiance(_numero,TeamsRelation.tousJoueurs(nombreDeJoueurs_));
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
        PossibleTrickWinner ramasseurCertain_ = TrickHypothesis.equipeQuiVaFairePli(
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
        PossibleTrickWinner ramasseurCertain_ = TrickHypothesis.equipeQuiVaFairePli(
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
        PossibleTrickWinner ramasseurCertain_ = TrickHypothesis.equipeQuiVaFairePli(
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
            if (TrickHypothesis.vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_)) {
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
            if (TrickHypothesis.vaCouper(couleurDemandee_, joueur_, cartesPossibles_, cartesCertaines_)) {
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
        TeamsRelation teamsRelation_ = getTeamsRelation();
        EnumMap<Suit,HandTarot> repartition_ = _cartes.couleurs();
        Numbers<Byte> joueursNonJoue_ = joueursNAyantPasJoue(_joueurCourant);
        CustList<TrickTarot> plisFaits_ = unionPlis(false);
        HandTarot cartesJouees_ = cartesJoueesEnCours(_joueurCourant);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = cartesJouees_.couleurs();
        boolean carteAppeleeJouee_ = carteAppeleeJouee(cartesJouees_);
        boolean contientExcuse_ = _cartesJouables.contient(CardTarot.excuse());
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        Numbers<Byte> joueursJoue_ = TeamsRelation.autresJoueurs(joueursNonJoue_, nombreDeJoueurs_);
        joueursJoue_.removeObj(_joueurCourant);
        DoneTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        EnumMap<Suit,EqList<HandTarot>> cartesPossibles_ = doneTrickInfo_.cartesPossibles(
                teamsRelation_,
                plisFaits_,
                _cartes,
                _joueurCourant,
                derniereMain());
        EnumMap<Hypothesis,EnumMap<Suit,EqList<HandTarot>>> hypotheses_ = doneTrickInfo_.cartesCertaines(teamsRelation_,cartesPossibles_);
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
        info_.setCalledSuits(couleursAppelees());
        info_.setProgressingTrick(progressingTrick);
        info_.setTeamsRelation(teamsRelation_);
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
            if (!TrickHypothesis.defausse(_cartesPossibles, joueur_,
                    _couleurDemandee)
                    && !TrickHypothesis.nePeutAvoirFigures(
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
            if (!(TrickHypothesis.peutCouper(_couleurDemandee, joueur_, _cartesPossibles))) {
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
            cartes_.ajouterCartes(derniereMain());
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
        Numbers<Byte> autresJoueurs_ = TeamsRelation.autresJoueurs(joueurs_, _nombreJoueurs);
        for (byte joueur_ : autresJoueurs_) {
            Numbers<Byte> joueursLoc_ = new Numbers<Byte>(joueurs_);
            joueursLoc_.add(joueur_);
            if (DoneTrickInfo.plisTousFaitsPar(joueursLoc_, _unionPlis, _nombreJoueurs)) {
                return true;
            }
        }
        return false;
    }

    private static boolean aucunPliAdverse(TeamsRelation _t,byte _joueur, CustList<TrickTarot> _unionPlis) {
        byte nombreDeJoueurs_ = _t.getNombreDeJoueurs();
        Numbers<Byte> partenaires_ = _t.coequipiers(_joueur,
                TeamsRelation.tousJoueurs(nombreDeJoueurs_));
        partenaires_.add(_joueur);
        return DoneTrickInfo.plisTousFaitsPar(partenaires_, _unionPlis, nombreDeJoueurs_);
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

    private boolean defenseOuvreur(TeamsRelation _teamRel,byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        Numbers<Byte> attaque_ = new Numbers<Byte>();
        attaque_.add(taker);
        if (appeleConnuDefenseur(_teamRel,_joueur,_cartesPossibles) && existeAppele()) {
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

    private boolean defenseOuvreurStrict(TeamsRelation _teamRel,byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        if (!defenseOuvreur(_teamRel,_joueur, _cartesPossibles)) {
            return false;
        }
        if (appeleConnuDefenseur(_teamRel,_joueur,_cartesPossibles) && existeAppele() && !calledPlayers.containsObj(taker)) {
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

    private boolean appeleConnuDefenseur(TeamsRelation _teamRel,byte _joueur, EnumMap<Suit,EqList<HandTarot>> _cartesPossibles) {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        if (bid.getJeuChien() == PlayingDog.WITH) {
            return _teamRel.joueursConfiance(_joueur, TeamsRelation.tousJoueurs(nombreDeJoueurs_)).size() + 2 + calledPlayers.size() == nombreDeJoueurs_;
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
                return _teamRel.joueursConfiance(_joueur, TeamsRelation.tousJoueurs(nombreDeJoueurs_)).size() + 2 + calledPlayers.size() == nombreDeJoueurs_;
            }
        }
        return _teamRel.joueursConfiance(_joueur, TeamsRelation.tousJoueurs(nombreDeJoueurs_)).size() + 2 == nombreDeJoueurs_;
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

        // nombreJoueurs jouant au tarot
        trickWinner = progressingTrick.getRamasseur();

        tricks.add(progressingTrick);
        if(!getDistribution().main().estVide()) {
            setEntameur();
        }
    }

    public void addCurTrick() {
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
        TeamsRelation teamsRelation_ = getTeamsRelation();
        Numbers<Byte> partenaires_ = teamsRelation_.tousCoequipiers(trickWinner);
        boolean possedeExcuseMemeEquipe_ = false;
        for (byte b1_ : partenaires_) {
            if (getDistribution().main(b1_).contient(CardTarot.excuse())) {
                possedeExcuseMemeEquipe_ = true;
            }
        }
        if (getDistribution().main(trickWinner).contient(
                CardTarot.excuse())
                || possedeExcuseMemeEquipe_) {
            if (!teamsRelation_.adversaireAFaitPlis(trickWinner,tricks)) {
                //ajouterPetitAuBoutCasChelem
                smallBound.set( trickWinner, true);
            }
        }
    }

    public boolean petitMeneAuBout(byte _numero) {
        return smallBound.get(_numero);
    }

    public EndTarotGame getEndTarotGame() {
        TeamsRelation t_ = getTeamsRelation();
        return new EndTarotGame(t_,tricks,declaresHandfuls,declaresMiseres,declaresSlam,smallBound);
    }

    public TeamsRelation getTeamsRelation() {
        return new TeamsRelation(taker,calledPlayers,confidence,rules);
    }
    public DoneTrickInfo getDoneTrickInfo() {
        Numbers<Integer> handLengths_ = new Numbers<Integer>();
        for (HandTarot h: deal) {
            handLengths_.add(h.total());
        }
        return new DoneTrickInfo(progressingTrick, tricks,
                declaresMiseres,
                handfuls, bid, calledCards,
                handLengths_);
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
            for (TrickTarot pli_ : tricks) {
                m.ajouterCartes(pli_.getCartes());
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
