package cards.tarot;

import cards.consts.GameType;
import cards.consts.Hypothesis;
import cards.consts.Suit;
import cards.tarot.enumerations.*;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

/** */

public final class GameTarot {

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
    private byte taker = IndexConstants.INDEX_NOT_FOUND_ELT;
    /** Ce sont les poignees annoncees par le(s) joueur(s) */
    private CustList<EnumList<Handfuls>> declaresHandfuls = new CustList<EnumList<Handfuls>>();
    /** Ce sont les miseres annoncees par le(s) joueur(s) */
    private CustList<EnumList<Miseres>> declaresMiseres = new CustList<EnumList<Miseres>>();
    /** Ce sont les primes annoncees par le(s) joueur(s) */
    private CustList<BoolVal> declaresSlam = new CustList<BoolVal>();
    /** Ce sont les petits au bout par le(s) joueur(s) */
    private CustList<BoolVal> smallBound = new CustList<BoolVal>();
    /** Poignees */
    private CustList<HandTarot> handfuls = new CustList<HandTarot>();
    /**
    Au tarot lors d'un appel il faut savoir si les joueurs ont confiance ou
    non en les autres
    */
    private CustList<CustList<BoolVal>> confidence = new CustList<CustList<BoolVal>>();
    /**
    Le contrat permet de dire quel va etre le deroulement de la partie
    */
    private BidTarot bid = BidTarot.FOLD;
    /** Ce sont les plis faits par les joueurs */
    /** PliTarot en cours d'etre joue */
    private TrickTarot progressingTrick = new TrickTarot(IndexConstants.INDEX_NOT_FOUND_ELT, false);

    /** Ensemble des plis faits par les joueurs */
    private CustList<TrickTarot> tricks = new CustList<TrickTarot>();
    private Bytes calledPlayers = new Bytes();
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
    Scores cumules au cours des parties Chaque nombre (Short) represente un
    score pour le joueur
    */
    private Shorts scores = new Shorts();
    /** Nombre de fois qu'a ete joue la partie(partie fini) */
    private long number;
    private RulesTarot rules = new RulesTarot();

    private String error = "";
    private byte lastHasBid = IndexConstants.INDEX_NOT_FOUND_ELT;

    private BidTarot lastBid = BidTarot.FOLD;

    private int cardsToBeDiscardedCount;

    private CardTarot playedCard = CardTarot.WHITE;

    private boolean ended;

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
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.add((short) 0);
        }
        if (!avecContrat()) {
            tricks.add(
                    new TrickTarot(getDistribution().derniereMain(),
                            (byte) (nombreJoueurs_ + 1), false));
        }
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
        /*
        Initialise la confiance a un
        jeu non solitaire
        */
            confidence.add(new CustList<BoolVal>());
            for (int j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
                confidence.last().add(ComparatorBoolean.of(i == j));
            }
        }
        initConstTeamWithoutTaker();
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            declaresHandfuls.add(new EnumList<Handfuls>());
            declaresMiseres.add(new EnumList<Miseres>());
            declaresSlam.add(BoolVal.FALSE);
            smallBound.add(BoolVal.FALSE);
        }
        // Par default tout le monde est defenseur
        for (byte j_ = IndexConstants.FIRST_INDEX; j_ < nombreJoueurs_; j_++) {
            handfuls.add(new HandTarot());
        }
        lastHasBid = -1;
    }

    void initConstTeamWithoutTaker() {
        if(initTeamWithoutTaker()) {
            initEquipeDetermineeSansPreneur();
        }
    }

    boolean initTeamWithoutTaker() {
        return !avecContrat()
                && getRegles().getDealing().getAppel() == CallingCard.DEFINED;
    }

    public void initPlayWithoutBid() {
        tricks.add(
                new TrickTarot(getDistribution().derniereMain(),
                        (byte) (getNombreDeJoueurs() + 1), false));
        if (getRegles().getDealing().getAppel() == CallingCard.DEFINED) {
            initEquipeDetermineeSansPreneur();
        }
    }


    public void initPartie() {
        taker = -1;
        calledPlayers.clear();
        bids = new EnumList<BidTarot>();
        bid = BidTarot.FOLD;
        progressingTrick = new TrickTarot((byte) -1, false);
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.set( i, (short) 0);
        }
        tricks.clear();
        if (!avecContrat()) {
            tricks.add(new TrickTarot(getDistribution().derniereMain(),
                    (byte) (nombreJoueurs_ + 1), false));
        }
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
                confidence.get(i).set(j, ComparatorBoolean.of(i == j));
            }
        }
        initConstTeamWithoutTaker();
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            declaresHandfuls.set( i, new EnumList<Handfuls>());
            declaresMiseres.set( i, new EnumList<Miseres>());
            smallBound.set(i, BoolVal.FALSE);
            declaresSlam.set(i, BoolVal.FALSE);
        }
        // Par default tout le monde est defenseur
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            handfuls.set( joueur_, new HandTarot());
        }
        cardsToBeDiscardedCount = 0;
        lastHasBid = -1;
    }

    void loadGame() {
        byte player_ = playerAfter(deal.getDealer());
        taker = IndexConstants.INDEX_NOT_FOUND_ELT;
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
        } else if (rules.getDealing().getAppel() == CallingCard.DEFINED) {
            initEquipeDeterminee();
            defined_ = true;
        } else if (rules.getDealing().getAppel() == CallingCard.WITHOUT) {
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
        if (!defined_) {
            calledPlayers = new Bytes();
            calledPlayers.addAllElts(joueursAyantCarteAppelee());
        }
        for (TrickTarot t: tricks) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            retrieveCalledPlayers(t);
        }
        if (progressingTrick.getVuParToutJoueur()) {
            starter = progressingTrick.getEntameur();
            trickWinner = progressingTrick.getEntameur();
            retrieveCalledPlayers(progressingTrick);
            if (progressingTrick.total() == getNombreDeJoueurs()) {
                ajouterPetitAuBoutPliEnCours();
                setPliEnCours(true);
            }
        } else if (!avecContrat()) {
            starter = playerAfter(deal.getDealer());
            trickWinner = starter;
        } else if (contrats() < getNombreDeJoueurs()) {
            starter = playerAfter(deal.getDealer());
            trickWinner = starter;
        } else if (pasJeuApresPasse()) {
            //if existePreneur()
            starter = taker;
            trickWinner = taker;
        } else {
            starter = playerAfter(deal.getDealer());
            trickWinner = starter;
        }
    }

    void retrieveCalledPlayers(TrickTarot _t) {
        byte nb_ = getNombreDeJoueurs();
        for (CardTarot c: calledCards) {
            byte called_ = _t.joueurAyantJouePliEnCours(c,nb_);
            if (called_ > -1) {
                calledPlayers.add(called_);
            }
        }
    }

    public void simuler(SimulatingTarot _simu) {
        ended = false;
        _simu.prepare();
        _simu.sleepSimu(500);
        _simu.beginDemo();
//        _simu.pause();
        byte donneur_ = getDistribution().getDealer();
        bid = BidTarot.FOLD;
        if (avecContrat()) {
            for (byte joueur_ : orderedPlayers(playerAfter(donneur_))) {
                bidSimulate(joueur_,_simu);
                if (_simu.stopped()) {
                    _simu.stopDemo();
                    return;
                }
            }
        }
        simuPlayCards(_simu);
    }

    void simuPlayCards(SimulatingTarot _simu) {
        if (!bid.isJouerDonne() && pasJeuApresPasse()) {
            _simu.noBid();
            ended = true;
            return;
        }
        simuCallDiscard(bid,_simu);
        simuStarter();
        _simu.displayLineReturn();
        _simu.beginPlay();
        while (true) {
            setPliEnCours(true);
            for (byte joueur_ : orderedPlayers(starter)) {
                if (getProgressingTrick().estVide()) {
                    _simu.firstCardPlaying(joueur_);
                } else {
                    _simu.nextCardPlaying(joueur_);
                }
                _simu.sleepSimu(1000);
//                _simu.pause();
                currentPlayerHasPlayed(joueur_);
                if (premierTourNoMisere()) {
                    _simu.declareHandfuls(joueur_,getAnnoncesPoignees(joueur_),getPoignee(joueur_));
                    _simu.declareMiseres(joueur_,getAnnoncesMiseres(joueur_));
                }
                if (getCalledCards().contient(playedCard)) {
                    _simu.displayCalled(joueur_);
                }
                _simu.played(joueur_,playedCard);
                if(joueur_==DealTarot.NUMERO_UTILISATEUR) {
                    _simu.displayUserHand(mainUtilisateurTriee(_simu.getDisplaying()));
                }
                if (_simu.stopped()) {
                    _simu.stopDemo();
                    return;
                }
            }
            if (getDistribution().hand().estVide()) {
                ajouterPetitAuBoutPliEnCours();
                _simu.displayTrickWinner(trickWinner);
                _simu.displaySmallBound(smallBound,trickWinner);
                break;
            }
            ajouterPliEnCours();
            _simu.displayTrickWinner(trickWinner);
            ajouterPetitAuBout();
            _simu.displaySmallBound(smallBound,trickWinner);
            _simu.sleepSimu(4000);
//            _simu.pause();
            _simu.clearCarpet(getNombreDeJoueurs());
        }
        _simu.endDeal();
        ended = true;
    }

    void simuStarter() {
        if (!chelemAnnonce()) {
            /*
        Si un joueur n'a pas annonce de Chelem on
        initialise l'entameur du premier pli
        */
            byte nbPl_ = getNombreDeJoueurs();
            byte deal_ = deal.getDealer();
            starter = (byte) ((deal_ + 1) % nbPl_);
        }
    }

    void simuCallDiscard(BidTarot _bid,SimulatingTarot _simu) {
        DisplayingTarot displaying_ = _simu.getDisplaying();
        if (_bid.isJouerDonne()) {
            HandTarot last_ = getDeal().derniereMain();
            if (rules.getDiscardAfterCall()) {
                initSimuTeam(_simu);
                if (_bid.getJeuChien() == PlayingDog.WITH) {
                    HandTarot curHand_ = mainUtilisateurTriee(displaying_);
                    _simu.seeDog(last_);
//                    _simu.pause();
                    _simu.beforeSeeDog(taker,curHand_);
                    HandTarot curHandAdd_ = new HandTarot();
                    curHandAdd_.ajouterCartes(curHand_);
                    curHandAdd_.ajouterCartes(last_);
                    curHandAdd_.trier(displaying_.getDisplaying().getSuits(), displaying_.getDisplaying().isDecreasing());
                    _simu.mergeDog(taker,curHandAdd_,last_);
                    ecarter(true);
                    HandTarot nextHand_ = mainUtilisateurTriee(displaying_);
                    _simu.mergedDog(taker,nextHand_);
                    _simu.autoCall(getAppele(),taker);
                } else {
                    gererChienInconnu();
                    slam();
                    _simu.declareSlam(declaresSlam,taker,bid);
                }
            } else {
                if (_bid.getJeuChien() == PlayingDog.WITH) {
                    HandTarot curHand_ = mainUtilisateurTriee(displaying_);
                    _simu.seeDog(last_);
                    _simu.beforeSeeDog(taker,curHand_);
                    HandTarot curHandAdd_ = new HandTarot();
                    curHandAdd_.ajouterCartes(curHand_);
                    curHandAdd_.ajouterCartes(last_);
                    curHandAdd_.trier(displaying_.getDisplaying().getSuits(), displaying_.getDisplaying().isDecreasing());
                    _simu.mergeDog(taker,curHandAdd_, last_);
                    appelApresEcart();
                    HandTarot nextHand_ = mainUtilisateurTriee(displaying_);
                    _simu.mergedDog(taker,nextHand_);
                    _simu.callCard();
                    _simu.callCard(taker,getCalledCards());
                } else {
                    initSimuTeam(_simu);
                    gererChienInconnu();
                    slam();
                    _simu.declareSlam(declaresSlam,taker,bid);
                }
            }
        }
    }

    void initSimuTeam(SimulatingTarot _simu) {
        if (rules.getDealing().getAppel() == CallingCard.CHARACTER_CARD
                || rules.getDealing().getAppel() == CallingCard.KING) {
            intelligenceArtificielleAppel();
        } else if (rules.getDealing().getAppel() == CallingCard.DEFINED) {
            initEquipeDeterminee();
        } else {
            initDefense();
        }
        if (getCalledCards().estVide()) {
            for (byte c: getAppele()) {
                _simu.constCallPlayer(c);
            }
        } else {
            _simu.callCard();
            _simu.callCard(taker,getCalledCards());
        }
    }

    void bidSimulate(byte _p,SimulatingTarot _simu) {
        if (maximumBid(bid)) {
            return;
        }
        BidTarot contratTmp_ = strategieContrat();
        _simu.actingBid(_p);
        _simu.sleepSimu(1000);
        ajouterContrat(contratTmp_,_p);
        _simu.actedBid(_p,contratTmp_);
    }

    public HandTarot mainUtilisateurTriee(DisplayingTarot _regles) {
        HandTarot main_ = new HandTarot();
        main_.ajouterCartes(getDistribution().hand());
        main_.trier(_regles.getDisplaying().getSuits(), _regles.getDisplaying().isDecreasing());
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
        return (byte) rules.getDealing().getId().getNombreJoueurs();
    }

    public RulesTarot getRegles() {
        return getRules();
    }

    public DealTarot getDistribution() {
        return getDeal();
    }

    public EnumList<BidTarot> allowedBids() {
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        for (BidTarot b: BidTarot.getValidBids()) {
            if (!contratAccepte(b)) {
                continue;
            }
            bids_.add(b);
        }
        return bids_;
    }
    boolean contratAccepte(BidTarot _enchere) {
        return rules.getAllowedBids().getVal(_enchere) == BoolVal.TRUE;
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
        HandTarot mj_ = getDistribution().hand(numero_);
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
            if (!getDistribution().hand(p).estVide()) {
                return true;
            }
        }
        return false;
    }
    boolean maximumBid(BidTarot _enchere) {
        BidTarot e_ = allowedBids().last();
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
        for (byte i = IndexConstants.FIRST_INDEX; i < nombreDeJoueurs_; i++) {
            for (byte p: rules.getDealing().getAppelesDetermines(i)) {
                faireConfiance(i, p);
            }
            faireConfiance(i, i);
        }
    }

    public void initEquipeDeterminee() {
        Bytes attaquants_= rules.getDealing().getAppelesDetermines(taker);
        calledPlayers = new Bytes(attaquants_);
        attaquants_.add(taker);
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        Bytes defenseurs_=GameTarotTeamsRelation.autresJoueurs(attaquants_, nombreDeJoueurs_);
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
        Bytes defenseurs_=new Bytes();
        byte indice_=taker;
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreDeJoueurs_; joueur_++) {
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
        CallDiscard appelEcart_ = new CallDiscard();
        HandTarot mainPreneur_ = getDistribution().hand(taker);
        CustList<HandTarot> cartesAppelablesFinales_ = new CustList<HandTarot>();
        cartesAppelablesFinales_.add(new HandTarot());
        CustList<HandTarot> cartesAppelables_ = new CustList<HandTarot>();
        boolean call_ = rules.getDealing().callCard();
        HandTarot cartesAppeler_ = callableCards();
        cartesAppeler_.supprimerCartes(mainPreneur_);
        if (_removeDog) {
            cartesAppeler_.supprimerCartes(getPliEnCours().getCartes());
        }
        if (call_) {
            for(CardTarot c:cartesAppeler_) {
                HandTarot m2_ = new HandTarot();
                m2_.ajouter(c);
                cartesAppelables_.add(m2_);
            }
            if(!cartesAppelables_.isEmpty()) {
                cartesAppelablesFinales_=new CustList<HandTarot>(cartesAppelables_);
            }
        }
        for(HandTarot carte_: cartesAppelablesFinales_) {
            HandTarot copieMainPreneur_ = copyHand(_removeDog, mainPreneur_);
            HandTarot ecart_ = discarding(carte_,copieMainPreneur_);
            copieMainPreneur_.supprimerCartes(ecart_);
            boolean annoncer_ = annoncerUnChelem(copieMainPreneur_,carte_);
            if(annoncer_) {
                appelEcart_.setCarteAppelee(carte_);
                appelEcart_.setEcartAFaire(ecart_);
                appelEcart_.setChelem(true);
                return appelEcart_;
            }
        }
        HandTarot hand_ = copyHand(_removeDog, mainPreneur_);
        if (call_) {
            cartesAppeler_ = strategieAppel(hand_);
            EnumList<Suit> couleursNonAppelees_ = new EnumList<Suit>();
            for (Suit couleur_ : couleursOrdinaires()) {
                if(cartesAppeler_.tailleCouleur(couleur_) == 0) {
                    couleursNonAppelees_.add(couleur_);
                }
            }
        }
        HandTarot ecart_ = discarding(cartesAppeler_,hand_);
        appelEcart_.setCarteAppelee(cartesAppeler_);
        appelEcart_.setEcartAFaire(ecart_);
        return appelEcart_;
    }

    private HandTarot copyHand(boolean _removeDog, HandTarot _mainPreneur) {
        HandTarot copieMainPreneur_ = new HandTarot();
        copieMainPreneur_.ajouterCartes(_mainPreneur);
        if (_removeDog) {
            copieMainPreneur_.ajouterCartes(getPliEnCours().getCartes());
        }
        return copieMainPreneur_;
    }

    /** Appele pour un appel de carte pour que le preneur ait un partenaire */
    public HandTarot callableCards() {
        HandTarot hand_ = getDistribution().hand(taker);
        GameTarotBid g_ = new GameTarotBid(hand_,rules,bids,bid);
        return g_.cartesAppeler();
    }

    public void intelligenceArtificielleAppel() {
        HandTarot cartesAppeler_ = strategieAppel();
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
                if (getDistribution().hand(p).contient(c)) {
                    return false;
                }
            }
        }
        return true;
    }
    public HandTarot strategieAppel() {
        HandTarot mainPreneur_ = getDistribution().hand(taker);
        return strategieAppel(mainPreneur_);
    }

    private HandTarot strategieAppel(HandTarot _mainPreneur) {

        GameTarotBid gb_ = new GameTarotBid(_mainPreneur,rules,bids,bid);
        GameTarotCallDiscard g_ = new GameTarotCallDiscard(gb_,derniereMain().total());
        return g_.strategieAppel();

    }

    public void setCarteAppelee(HandTarot _c) {
        calledCards = new HandTarot();
        calledCards.ajouterCartes(_c);
    }

    public HandTarot getCarteAppelee() {
        return getCalledCards();
    }

    /**
    Appele pour determiner le joueur ayant la carte appelee et initialiser sa
    confiance envers les autres joueurs
    */
    Bytes joueursAyantCarteAppelee() {
        Bytes joueurs_ = new Bytes();
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte b = IndexConstants.FIRST_INDEX; b < nombreDeJoueurs_; b++) {
            for(CardTarot c: calledCards) {
                if (deal.hand(b).contient(c)) {
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

    public Bytes getAppele() {
        return calledPlayers;
    }

    public ReasonDiscard autoriseEcartDe(CardTarot _c) {
        HandTarot m = getDistribution().hand(getPreneur());
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
            getPliEnCours().getCartes().supprimerCartes();
            //On ajoute les cartes du chien au preneur pour en ecarter d'autres
            HandTarot mt_=strategieEcart();
            //Le preneur ecarte les cartes qu'il veut
            supprimerCartes(taker,mt_);

            ajouterChelem(taker, annoncerUnChelem(taker));
            for(CardTarot ct_:mt_) {
                ajouterUneCarteDansPliEnCours(ct_);
            }
            tricks.add(progressingTrick);
            setStarterIfSlam();
            return;
        }
        ajouterCartes(taker,derniereMain());
        //On ajoute les cartes du chien au preneur pour en ecarter d'autres
        HandTarot mt_=strategieEcart();
        //Le preneur ecarte les cartes qu'il veut
        supprimerCartes(taker,mt_);

        ajouterChelem(taker, annoncerUnChelem(taker));

        setEntameur(taker);
        setPliEnCours(false);
        for(CardTarot ct_:mt_) {
            ajouterUneCarteDansPliEnCours(ct_);
        }
        tricks.add(progressingTrick);
        setStarterIfSlam();
    }

    private void setStarterIfSlam() {
        if(chelemAnnonce(taker)) {
            setEntameur(taker);
        }
    }

    public HandTarot strategieEcart() {
        HandTarot mainPreneur_ = getDistribution().hand(taker);
        return discarding(calledCards,mainPreneur_);
    }
    private HandTarot discarding(HandTarot _carteAppelee, HandTarot _taker) {
        HandTarot handStrat_ = strategieEcart(_carteAppelee,_taker);
        cardsToBeDiscardedCount = 0;
        return handStrat_;
    }

    private HandTarot strategieEcart(HandTarot _carteAppelee, HandTarot _taker) {
        int tailleChien_ = getDistribution().derniereMain().total();
        GameTarotBid gb_ = new GameTarotBid(_taker,rules,bids,bid);
        GameTarotCallDiscard g_ = new GameTarotCallDiscard(gb_,tailleChien_);
        return g_.strategieEcart(_carteAppelee);
    }

    public void invaliderAjoutCarteAuChien() {
        cardsToBeDiscardedCount--;
    }

    public void retirerUneCarteDuChien(CardTarot _ct) {
        progressingTrick.retirer(_ct);
        invaliderAjoutCarteAuChien();
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
        HandTarot mainJoueur_ = getDistribution().hand(_numeroJoueur);
        return annoncerUnChelem(mainJoueur_);
    }
    private boolean annoncerUnChelem(HandTarot _mainJoueur) {
        return annoncerUnChelem(_mainJoueur,calledCards);
    }
    private boolean annoncerUnChelem(HandTarot _mainJoueur, HandTarot _called) {
        EnumMap<Suit,HandTarot> repartition_ = _mainJoueur.couleurs();
        return GameTarotBid.estUnJeuDeChelem(repartition_, new HandTarot().couleurs(), rules, _called);
    }

    public void slam() {
        ajouterChelem(getPreneur(),annoncerUnChelem(getPreneur()));
        if (declaresSlam.get(getPreneur()) == BoolVal.TRUE) {
            setEntameur(getPreneur());
        }
    }
    public void ajouterChelemUtilisateur() {
        ajouterChelem(getPreneur(), true);
        setEntameur(getPreneur());
    }
    void ajouterChelem(byte _b, boolean _annonce) {
        declaresSlam.set( _b,ComparatorBoolean.of(_annonce));
    }


    public boolean chelemAnnonce(byte _numero) {
        return declaresSlam.get(_numero) == BoolVal.TRUE;
    }

    /** Est vrai si et seulement si un chelem est annonce */
    public boolean chelemAnnonce() {
        boolean contientChelem_ = bid.isFaireTousPlis();
        for (BoolVal chelem_ : declaresSlam) {
            if (chelem_ == BoolVal.TRUE) {
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
        HandTarot mainJoueur_ = getDistribution().hand(_numero);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls);
        return g_.getAnnoncesPoigneesPossibles();

    }


    public EnumList<Handfuls> strategieAnnoncesPoignees(byte _numeroJoueur) {
        HandTarot mainJoueur_ = getDistribution().hand(_numeroJoueur);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls);
        return g_.strategieAnnoncesPoignees(calledCards);
    }

    public boolean isValidHandful(Handfuls _h, HandTarot _hand, HandTarot _excludedCards) {
        int nbTrumps_ = rules.getAllowedHandfuls().getVal(_h);
        return _hand.total() == nbTrumps_ && (!_hand.contient(CardTarot.excuse()) || _excludedCards.estVide());
    }

    public HandTarot strategiePoignee(byte _numeroJoueur) {
        HandTarot mainJoueur_ = getDistribution().hand(_numeroJoueur);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls);
        return g_.strategiePoignee();
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
        HandTarot mainJoueur_ = getDistribution().hand(_numero);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls);
        return g_.getAnnoncesMiseresPossibles();
    }

    public EnumList<Miseres> strategieAnnoncesMiseres(byte _numeroJoueur) {
        HandTarot mainJoueur_ = getDistribution().hand(_numeroJoueur);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls);
        return g_.strategieAnnoncesMiseres();
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
            setStarter(_i);
        }
    }

    public void setStarter(byte _starter) {
        starter = _starter;
    }

    void setTrickWinner(byte _trickWinner) {
        trickWinner = _trickWinner;
    }

    public boolean autorise(CardTarot _c) {
        HandTarot main_ = getDistribution().hand(playerHavingToPlay());
        EnumMap<Suit,HandTarot> repartition_ = main_.couleurs();
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotCommonPlaying g_ = new GameTarotCommonPlaying(doneTrickInfo_,teamsRelation_);
        return g_.cartesJouables(repartition_).contient(_c);
    }

    HandTarot playableCards(EnumMap<Suit,HandTarot> _repartitionMain) {
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotCommonPlaying g_ = new GameTarotCommonPlaying(doneTrickInfo_,teamsRelation_);
        return g_.cartesJouables(_repartitionMain);
    }

    public boolean premierTourNoMisere() {
        return premierTour() && pasJeuMisere();
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
        if (premierTourNoMisere())  {
            byte nombreDeJoueurs_ = getNombreDeJoueurs();
            byte joueur_ = progressingTrick.getNextPlayer(nombreDeJoueurs_);
            EnumList<Miseres> annoncesMiseres_ = strategieAnnoncesMiseres(
                    joueur_);
            setAnnoncesMiseres(joueur_, annoncesMiseres_);
            EnumList<Handfuls> annoncesPoignees_ = strategieAnnoncesPoignees(
                    joueur_);
            setAnnoncesPoignees(joueur_, annoncesPoignees_);
            HandTarot poignee_ = strategiePoignee(joueur_);
            ajouterPoignee(poignee_, joueur_);
        }
    }
    public void changerConfiance() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        byte numero_ = progressingTrick.getNextPlayer(nombreDeJoueurs_);
        HandTarot mainJoueur_ = getDistribution().hand(numero_);
        if(mainJoueur_.contientCartes(calledCards)) {
            return;
        }
        GameTarotTeamsRelation teamRel_ = getTeamsRelation();
        boolean carteAppeleeJouee_ = carteAppeleeJouee();
        if(carteAppeleeJouee_) {
            teamRel_.determinerConfiance(numero_, nombreDeJoueurs_);
            return;
        }
        EnumMap<Suit,HandTarot> repartition_ = mainJoueur_.couleurs();
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        HandTarot cartesJouees_ = doneTrickInfo_.cartesJoueesEnCours(numero_);
        EnumMap<Suit,HandTarot> repartitionCartesJouees_ = cartesJouees_.couleurs();
        CustList<CustList<HandTarot>> suites_ = new CustList<CustList<HandTarot>>();
        suites_.add(new CustList<HandTarot>());
        suites_.add(repartition_.getVal(Suit.TRUMP).eclaterEnCours(
                repartitionCartesJouees_, Suit.TRUMP));
        for (Suit i : couleursOrdinaires()) {
            suites_.add(repartition_.getVal(i).eclaterEnCours(
                    repartitionCartesJouees_, i));
        }
        EnumMap<Suit,CustList<HandTarot>> cartesPossibles_ = doneTrickInfo_.cartesPossibles(mainJoueur_);
        EnumMap<Hypothesis,EnumMap<Suit,CustList<HandTarot>>> hypotheses_ = doneTrickInfo_.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        EnumMap<Suit,CustList<HandTarot>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        CustList<TrickTarot> plisFaits_ = unionPlis();
        plisFaits_.add(progressingTrick);
        GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(teamRel_,calledCards, plisFaits_, numero_, cartesPossibles_,
                cartesCertaines_);
    }

    private void faireConfiance(byte _joueur, byte _enjoueur) {
        confidence.get(_joueur).set( _enjoueur, BoolVal.TRUE);
    }

    public CardTarot strategieJeuCarteUnique() {
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
        byte numero_ = progressingTrick.getNextPlayer(nombreJoueurs_);
        HandTarot mainJoueur_ = getDistribution().hand(numero_);
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        if (existePreneur() || pasJeuMisere()) {
            GameTarotBeginTrickClassic g_ = new GameTarotBeginTrickClassic(doneTrickInfo_,teamsRelation_,calledCards,mainJoueur_);
            return g_.entameClassique();
        }
        GameTarotMisere g_ = new GameTarotMisere(doneTrickInfo_,teamsRelation_,mainJoueur_);
        return g_.entame();
    }


    // DEBUT FONCTION IA



    private CardTarot enCours() {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        byte numero_ = progressingTrick.getNextPlayer(nombreJoueurs_);
        HandTarot mainJoueur_ = getDistribution().hand(numero_);
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo();
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        if (existePreneur() || pasJeuMisere()) {
            GameTarotProgTrickClassic g_ = new GameTarotProgTrickClassic(doneTrickInfo_,teamsRelation_,
                    calledCards,mainJoueur_);
            return g_.enCoursClassic();
        }
        GameTarotMisere g_ = new GameTarotMisere(doneTrickInfo_,teamsRelation_,
                mainJoueur_);
        return g_.enCours();
    }

    public byte getEntameur() {
        return starter;
    }

    /**
    Renvoie vrai si et seulement si la carte appelee n'existe pas pour cette
    partie ou si la carte appelee existe et est jouee
    */
    public boolean carteAppeleeJouee() {
        HandTarot m = new HandTarot();
        for (TrickTarot t: tricks) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            m.ajouterCartes(t.getCartes());
        }
        m.ajouterCartes(progressingTrick.getCartes());
        return m.contientCartes(calledCards);
    }

    boolean confiance(byte _joueur, byte _enjoueur) {
        return confidence.get(_joueur).get(_enjoueur) == BoolVal.TRUE;
    }


    public CardTarot getCarteJoueee() {
        return playedCard;
    }

    /**
    Inclut tous les plis sauf celui qui est en cours classes dans l'ordre
    chronologique (par leur numero) On a pour tout pli d'indice i
    unionPlis.get(i).getNumero()==i
    */
    public CustList<TrickTarot> unionPlis() {
        CustList<TrickTarot> unionPlis_ = new CustList<TrickTarot>();
        unionPlis_.addAllElts(tricks);
        return unionPlis_;
    }


    public Bytes orderedPlayers(byte _leader) {
        return rules.getDealing().getId().getSortedPlayers(_leader);
    }

    public byte playerHavingToBid() {
        byte dealer_ = getDistribution().getDealer();
        byte playerHavingToBid_ = playerAfter(dealer_);
        int nbBids_ = contrats();
        for (byte b = IndexConstants.FIRST_INDEX; b<nbBids_; b++) {
            playerHavingToBid_ = playerAfter(playerHavingToBid_);
        }
        return playerHavingToBid_;
    }
    public byte playerHavingToPlay() {
        return getPliEnCours().getNextPlayer(getNombreDeJoueurs());
    }
    public byte playerAfter(byte _player) {
        return rules.getDealing().getId().getNextPlayer(_player);
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
        if(!getDistribution().hand().estVide()) {
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

    void ajouterPetitAuBout() {
        if(getDistribution().hand().total() > 1) {
            return;
        }
        if(!getPliEnCours().contient(CardTarot.petit())) {
            return;
        }
        if(getDistribution().hand().estVide()) {
            /*Le Petit est mene au bout*/
            smallBound.set( trickWinner, BoolVal.TRUE);
            return;
        }
        //getDistribution().main().total() == 1
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        Bytes partenaires_ = teamsRelation_.tousCoequipiers(trickWinner);
        partenaires_.add(trickWinner);
        boolean possedeExcuseMemeEquipe_ = false;
        for (byte b1_ : partenaires_) {
            if (getDistribution().hand(b1_).contient(CardTarot.excuse())) {
                possedeExcuseMemeEquipe_ = true;
            }
        }
        if (possedeExcuseMemeEquipe_) {
            if (!teamsRelation_.adversaireAFaitPlis(trickWinner,tricks)) {
                //ajouterPetitAuBoutCasChelem
                smallBound.set( trickWinner, BoolVal.TRUE);
            }
        }
    }

    public EndTarotGame getEndTarotGame() {
        GameTarotTeamsRelation t_ = getTeamsRelation();
        return new EndTarotGame(t_,tricks,declaresHandfuls,declaresMiseres,declaresSlam,smallBound);
    }

    public GameTarotTeamsRelation getTeamsRelation() {
        return new GameTarotTeamsRelation(taker,calledPlayers,confidence,rules);
    }
    public GameTarotTrickInfo getDoneTrickInfo() {
        Ints handLengths_ = new Ints();
        for (HandTarot h: deal) {
            handLengths_.add(h.total());
        }
        GameTarotTrickInfo gameTarotTrickInfo_ = new GameTarotTrickInfo(progressingTrick, tricks,
                declaresMiseres,
                handfuls, bid, calledCards,
                handLengths_);
        gameTarotTrickInfo_.addSeenDeck(deal.derniereMain(),getTeamsRelation());
        return gameTarotTrickInfo_;
    }
    public Shorts getScoresRef() {
        return scores;
    }
    public Shorts getScores() {
        return new Shorts(scores);
    }

    public HandTarot empiler() {
        HandTarot m = new HandTarot();
        if (tricks.isEmpty()) {
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
        if (_plisFaits.isEmpty()) {
            return;
        }
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
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

    public GameType getType() {
        return type;
    }

    public CustList<TrickTarot> getTricks() {
        return tricks;
    }

    private static EnumList<Suit> couleursOrdinaires() {
        return Suit.couleursOrdinaires();
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

    public CustList<EnumList<Miseres>> getDeclaresMiseres() {
        return declaresMiseres;
    }

    public void setDeclaresMiseres(CustList<EnumList<Miseres>> _declaresMiseres) {
        declaresMiseres = _declaresMiseres;
    }

    public CustList<BoolVal> getDeclaresSlam() {
        return declaresSlam;
    }

    public void setDeclaresSlam(CustList<BoolVal> _declaresSlam) {
        declaresSlam = _declaresSlam;
    }

    public CustList<BoolVal> getSmallBound() {
        return smallBound;
    }

    public void setSmallBound(CustList<BoolVal> _smallBound) {
        smallBound = _smallBound;
    }

    public CustList<HandTarot> getHandfuls() {
        return handfuls;
    }

    public void setHandfuls(CustList<HandTarot> _handfuls) {
        handfuls = _handfuls;
    }

    public CustList<CustList<BoolVal>> getConfidence() {
        return confidence;
    }

    public void setConfidence(CustList<CustList<BoolVal>> _confidence) {
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

    public void setScores(Shorts _scores) {
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

    public boolean isEnded() {
        return ended;
    }
}
