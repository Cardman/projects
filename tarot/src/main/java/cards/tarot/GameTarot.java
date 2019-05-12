package cards.tarot;
import java.util.concurrent.atomic.AtomicInteger;

import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Suit;
import cards.tarot.comparators.BidTarotComparator;
import cards.tarot.enumerations.*;
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

    private int cardsToBeDiscardedCount;

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
        cardsToBeDiscardedCount = 0;
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
        boolean defined_ = false;
        if (!avecContrat() || !bid_.isJouerDonne()) {
            initEquipeDetermineeSansPreneur();
        } else if (rules.getRepartition().getAppel() == CallingCard.DEFINED) {
            initEquipeDeterminee();
            defined_ = true;
        } else if (rules.getRepartition().getAppel() == CallingCard.WITHOUT) {
            initDefense();
        }
        cardsToBeDiscardedCount = 0;
        if (bid.getJeuChien() == PlayingDog.WITH) {
            if (tricks.isEmpty()) {
                cardsToBeDiscardedCount += getPliEnCours().total();
            } else {
                cardsToBeDiscardedCount += tricks.first().total();
            }
        }
        CustList<TrickTarot> tricks_ = unionPlis(false);
        if (!defined_) {
            calledPlayers = new Numbers<Byte>();
            calledPlayers.addAllElts(joueursAyantCarteAppelee());
        }
        if (!tricks_.isEmpty()) {
            starter = progressingTrick.getEntameur();
            trickWinner = progressingTrick.getEntameur();
            for (TrickTarot t: tricks_) {
                if (!t.getVuParToutJoueur()) {
                    continue;
                }
                retrieveCalledPlayers(t);
            }
            retrieveCalledPlayers(progressingTrick);
        } else if (progressingTrick.getVuParToutJoueur()) {
            starter = progressingTrick.getEntameur();
            trickWinner = progressingTrick.getEntameur();
            retrieveCalledPlayers(progressingTrick);
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

    void retrieveCalledPlayers(TrickTarot _t) {
        for (CardTarot c: calledCards) {
            byte called_ = _t.joueurAyantJoue(c);
            if (called_ > -1) {
                calledPlayers.add(called_);
            }
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
        byte numero_ = playerHavingToBid();
        HandTarot mj_ = getDistribution().main(numero_);
        GameTarotBid helper_ = new GameTarotBid(mj_,rules,bids,bid);
        return helper_.strategieContrat();
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
        Numbers<Byte> defenseurs_=GameTarotTeamsRelation.autresJoueurs(attaquants_, nombreDeJoueurs_);
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
        HandTarot hand_ = getDistribution().main(taker);
        GameTarotBid g_ = new GameTarotBid(hand_,rules,bids,bid);
        return g_.cartesAppeler();
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

        GameTarotBid gb_ = new GameTarotBid(_mainPreneur,rules,bids,bid);
        GameTarotCallDiscard g_ = new GameTarotCallDiscard(gb_,derniereMain().total());
        return g_.strategieAppel(_mainPreneur);

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
        if(cardsToBeDiscardedCount >= getDistribution()
                .derniereMain().total()) {
            return ReasonDiscard.TOO_MUCH;
        }
        cardsToBeDiscardedCount++;
        boolean allowed_ = GameTarotCallDiscard.getCartesEcartables(getDistribution()
                .derniereMain().total() - (cardsToBeDiscardedCount - 1),
                m.couleurs()).contient(_c);
        if (!allowed_) {
            cardsToBeDiscardedCount--;
        }
        if (allowed_) {
            return ReasonDiscard.NOTHING;
        }
        return ReasonDiscard.FORBIDDEN;
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
        cardsToBeDiscardedCount = 0;
        return handStrat_;
    }

    private HandTarot strategieEcart(boolean _carteAppeleeExistante,HandTarot _carteAppelee,
            EnumList<Suit> _couleursNonAppelees) {
        HandTarot mainPreneur_ = getDistribution().main(taker);
        int tailleChien_ = getDistribution().derniereMain().total();
        GameTarotBid gb_ = new GameTarotBid(mainPreneur_,rules,bids,bid);
        GameTarotCallDiscard g_ = new GameTarotCallDiscard(gb_,tailleChien_);
        return g_.strategieEcart(_carteAppeleeExistante,_carteAppelee,_couleursNonAppelees);
    }

    public void invaliderAjoutCarteAuChien() {
        cardsToBeDiscardedCount--;
    }

    public void retirerUneCarteDuChien(CardTarot _ct) {
        progressingTrick.retirer(_ct);
        invaliderAjoutCarteAuChien();
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
        return GameTarotBid.estUnJeuDeChelem(repartition_, new HandTarot().couleurs(), rules, calledCards, nombreJoueurs_);
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
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls,declaresMiseres);
        return g_.getAnnoncesPoigneesPossibles();

    }


    public EnumList<Handfuls> strategieAnnoncesPoignees(byte _numeroJoueur) {
        HandTarot mainJoueur_ = getDistribution().main(_numeroJoueur);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls,declaresMiseres);
        return g_.strategieAnnoncesPoignees(bid,calledCards,_numeroJoueur);
    }

    public boolean isValidHandful(Handfuls _h, HandTarot _hand, HandTarot _excludedCards) {
        int nbTrumps_ = rules.getPoigneesAutorisees().getVal(_h);
        return _hand.total() == nbTrumps_ && (!_hand.contient(CardTarot.excuse()) || _excludedCards.estVide());
    }

    public HandTarot strategiePoignee(byte _numeroJoueur) {
        reason = new StringBuilder();
        HandTarot mainJoueur_ = getDistribution().main(_numeroJoueur);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls,declaresMiseres);
        return g_.strategiePoignee(_numeroJoueur);
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
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls,declaresMiseres);
        return g_.getAnnoncesMiseresPossibles();
    }

    public EnumList<Miseres> strategieAnnoncesMiseres(byte _numeroJoueur) {
        HandTarot mainJoueur_ = getDistribution().main(_numeroJoueur);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls,declaresMiseres);
        return g_.strategieAnnoncesMiseres();
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
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotCommonPlaying g_ = new GameTarotCommonPlaying(doneTrickInfo_,teamsRelation_);
        return g_.cartesJouables(calledCards,repartition_).contient(_c);
    }

    HandTarot playableCards(EnumMap<Suit,HandTarot> _repartitionMain) {
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotCommonPlaying g_ = new GameTarotCommonPlaying(doneTrickInfo_,teamsRelation_);
        return g_.cartesJouables(calledCards,_repartitionMain);
    }

    public boolean premierTour() {
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotCommonPlaying g_ = new GameTarotCommonPlaying(doneTrickInfo_,teamsRelation_);
        return g_.premierTour();
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
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotTeamsRelation teamRel_ = getTeamsRelation();
        HandTarot cartesJouees_ = doneTrickInfo_.cartesJoueesEnCours(teamRel_,numero_);
        boolean carteAppeleeJouee_ = carteAppeleeJouee(cartesJouees_);
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
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(teamRel_,calledCards,bid,plisFaits_, numero_, cartesPossibles_,
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
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        if (existePreneur() || pasJeuMisere()) {
            GameTarotBeginTrickClassic g_ = new GameTarotBeginTrickClassic(doneTrickInfo_,teamsRelation_,calledCards,mainJoueur_);
            return g_.entameClassique(numero_, derniereMain(), cartesJouables_);
        }
        GameTarotMisere g_ = new GameTarotMisere(doneTrickInfo_,teamsRelation_,mainJoueur_,starter);
        return g_.entame();
    }


    // DEBUT FONCTION IA



    private CardTarot enCours() {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        byte numero_ = (byte) ((progressingTrick.getEntameur() + progressingTrick.total()) % nombreJoueurs_);
        HandTarot mainJoueur_ = getDistribution().main(numero_);
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        if (existePreneur() || pasJeuMisere()) {
            GameTarotProgTrickClassic g_ = new GameTarotProgTrickClassic(doneTrickInfo_,teamsRelation_,
                    calledCards,mainJoueur_,starter,bid);
            return g_.enCoursClassic(derniereMain());
        }
        GameTarotMisere g_ = new GameTarotMisere(doneTrickInfo_,teamsRelation_,
                mainJoueur_,starter);
        return g_.enCours();
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

    boolean confiance(byte _joueur, byte _enjoueur) {
        return confidence.get(_joueur).get(_enjoueur);
    }


    public CardTarot getCarteJoueee() {
        return playedCard;
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
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
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
        GameTarotTeamsRelation t_ = getTeamsRelation();
        return new EndTarotGame(t_,tricks,declaresHandfuls,declaresMiseres,declaresSlam,smallBound);
    }

    public GameTarotTeamsRelation getTeamsRelation() {
        return new GameTarotTeamsRelation(taker,calledPlayers,confidence,rules);
    }
    public GameTarotTrickInfo getDoneTrickInfo() {
        Numbers<Integer> handLengths_ = new Numbers<Integer>();
        for (HandTarot h: deal) {
            handLengths_.add(h.total());
        }
        return new GameTarotTrickInfo(progressingTrick, tricks,
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

    public int getCardsToBeDiscarded() {
        return cardsToBeDiscardedCount;
    }

}
