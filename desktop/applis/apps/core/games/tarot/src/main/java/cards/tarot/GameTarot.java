package cards.tarot;

import cards.consts.*;
import cards.tarot.enumerations.*;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

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
    private CustList<IdList<Handfuls>> declaresHandfuls = new CustList<IdList<Handfuls>>();
    /** Ce sont les miseres annoncees par le(s) joueur(s) */
    private CustList<IdList<Miseres>> declaresMiseres = new CustList<IdList<Miseres>>();
//    /** Ce sont les primes annoncees par le(s) joueur(s) */
//    private CustList<BoolVal> declaresSlam = new CustList<BoolVal>();
    /** Ce sont les petits au bout par le(s) joueur(s) */
    private CustList<BoolVal> smallBound = new CustList<BoolVal>();
    /** Poignees */
    private CustList<HandTarot> handfuls = new CustList<HandTarot>();
//    /**
//    Au tarot lors d'un appel il faut savoir si les joueurs ont confiance ou
//    non en les autres
//    */
//    private CustList<CustList<BoolVal>> confidence = new CustList<CustList<BoolVal>>();
    /**
    Le contrat permet de dire quel va etre le deroulement de la partie
    */
    private BidTarot bid = BidTarot.FOLD;
    /** Ce sont les plis faits par les joueurs */
    /** PliTarot en cours d'etre joue */
    private TrickTarot progressingTrick = new TrickTarot(IndexConstants.INDEX_NOT_FOUND_ELT);

    /** Ensemble des plis faits par les joueurs */
    private CustList<TrickTarot> tricks = new CustList<TrickTarot>();
    private Bytes calledPlayers = new Bytes();
    /**The called cards are the cards owned by the taker's probably parteners
    */
    private HandTarot calledCards = new HandTarot();
//    /** Entameur du pli qui est en cours d'etre joue */
//    private byte starter;
    /** Ensembe des contrats annonces */
    private IdList<BidTarot> bids = new IdList<BidTarot>();
//    /** Ramasseur du pli qui vient d'etre joue */
//    private byte trickWinner;
    /**
    Scores cumules au cours des parties Chaque nombre (Short) represente un
    score pour le joueur
    */
    private Shorts scores = new Shorts();
    /** Nombre de fois qu'a ete joue la partie(partie fini) */
    private long number;
    private RulesTarot rules = new RulesTarot();

    private String error = "";
//    private byte lastHasBid = IndexConstants.INDEX_NOT_FOUND_ELT;

//    private BidTarot lastBid = BidTarot.FOLD;

    private int cardsToBeDiscardedCount;

//    private CardTarot playedCard = CardTarot.WHITE;

    private boolean ended;
    private ReasonPlayTarot reason = ReasonPlayTarot.NOTHING;
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
        firstTrickIfNoBid();
//        if (!avecContrat()) {
//            tricks.add(
//                    new TrickTarot(getDistribution().derniereMain(),
//                            (byte) (nombreJoueurs_ + 1), false));
//        }
//        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
//        /*
//        Initialise la confiance a un
//        jeu non solitaire
//        */
//            confidence.add(new CustList<BoolVal>());
//            for (int j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
//                confidence.last().add(ComparatorBoolean.of(i == j));
//            }
//        }
//        initConstTeamWithoutTaker();
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            declaresHandfuls.add(new IdList<Handfuls>());
            declaresMiseres.add(new IdList<Miseres>());
//            declaresSlam.add(BoolVal.FALSE);
            smallBound.add(BoolVal.FALSE);
        }
        // Par default tout le monde est defenseur
        for (byte j_ = IndexConstants.FIRST_INDEX; j_ < nombreJoueurs_; j_++) {
            handfuls.add(new HandTarot());
        }
    }

//    void initConstTeamWithoutTaker() {
//        if(initTeamWithoutTaker()) {
//            initEquipeDetermineeSansPreneur();
//        }
//    }

//    boolean initTeamWithoutTaker() {
//        return !avecContrat()
//                && getRegles().getDealing().getAppel() == CallingCard.DEFINED;
//    }

    public void initPlayWithoutBid() {
        tricks.add(
                new TrickTarot(getDistribution().derniereMain(),
                        (byte) (getNombreDeJoueurs() + 1)));
//        if (getRegles().getDealing().getAppel() == CallingCard.DEFINED) {
//            initEquipeDetermineeSansPreneur();
//        }
    }


    public void initPartie() {
        taker = -1;
        calledPlayers.clear();
        bids = new IdList<BidTarot>();
        bid = BidTarot.FOLD;
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.set( i, (short) 0);
        }
        tricks.clear();
        firstTrickIfNoBid();
//        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
//            for (int j = IndexConstants.FIRST_INDEX; j < nombreJoueurs_; j++) {
//                confidence.get(i).set(j, ComparatorBoolean.of(i == j));
//            }
//        }
//        initConstTeamWithoutTaker();
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            declaresHandfuls.set( i, new IdList<Handfuls>());
            declaresMiseres.set( i, new IdList<Miseres>());
            smallBound.set(i, BoolVal.FALSE);
//            declaresSlam.set(i, BoolVal.FALSE);
        }
        // Par default tout le monde est defenseur
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreJoueurs_; joueur_++) {
            handfuls.set( joueur_, new HandTarot());
        }
        cardsToBeDiscardedCount = 0;
    }

    private void firstTrickIfNoBid() {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        if (!avecContrat()) {
            tricks.add(new TrickTarot(getDistribution().derniereMain(),
                    (byte) (nombreJoueurs_ + 1)));
            firstLead();
        }
    }

    void loadGame() {
        if (tricks.isEmpty()) {
            firstTrickIfNoBid();
        }
        deal.setDealer((byte) (NumberUtil.mod(deal.getDealer(), getNombreDeJoueurs())));
        patchBids();
        BidTarotTaker bt_ = bid();
        taker = bt_.getTaker();
        bid = bt_.getBid();
        boolean defined_ = avContrat(rules) && bid.isJouerDonne() && rules.getDealing().getAppel() == CallingCard.DEFINED;
        cardsToBeDiscarded();
        if (!defined_) {
            calledPlayers = new Bytes(joueursAyantCarteAppelee());
        } else {
            calledPlayers = new Bytes(rules.getDealing().getAppelesDetermines(taker));
        }
        seenTricks();
        for (TrickTarot t: tricks.mid(1)) {
//            if (!t.getVuParToutJoueur()) {
//                continue;
//            }
            retrieveCalledPlayers(t);
        }
        if (!tricks.isEmpty()) {
            initStarters();
//            trickWinner = progressingTrick.getRamasseur(getNombreDeJoueurs());
            retrieveCalledPlayers(progressingTrick);
            if (progressingTrick.total() == getNombreDeJoueurs()) {
                ajouterPetitAuBoutPliEnCours();
            }
//        } else if (!avecContrat() || contrats() < getNombreDeJoueurs() || !pasJeuApresPasse()) {
//        } else if (!pasJeuApresPasse() || contrats() < getNombreDeJoueurs()) {
//            trickWinner = playerAfter(deal.getDealer());
//        } else {
//            //if existePreneur()
//            trickWinner = taker;
        }
//        confianceAppele();
    }

    private void patchBids() {
        if (existePreneur() && (existPlayedCard() || getDeal().hand(getPreneur()).total() != getRegles().getDealing().getNombreCartesParJoueur())) {
            while (keepBidding()) {
                ajouterContrat(BidTarot.FOLD);
            }
        }
    }

    private void seenTricks() {
//        int nb_ = tricks.size();
//        for (int i = 0; i < nb_; i++) {
//            tricks.get(i).setSeenByAllPlayers(i > 0);
//        }
        if (rules.getDiscardAfterCall() && bid.getJeuChien() == PlayingDog.WITH && getTricks().isEmpty() && getPreneur() != DealTarot.NUMERO_UTILISATEUR && progressingTrick.total() == rules.getDealing().getNombreCartesChien()) {
            fwdToDog(progressingTrick.getCartes());
        }
        if (progressingTrick.foundFirst(tricks)) {
            firstLead();
        } else if (progressingTrick.foundLast(tricks)) {
//            trickWinner = progressingTrick.getRamasseur(getNombreDeJoueurs());
            byte next_ = progressingTrick.getRamasseur(getNombreDeJoueurs());
            ajouterPetitAuBout(next_);
            progressingTrick = new TrickTarot(next_);
//        } else {
//            progressingTrick.setSeenByAllPlayers(nb_ > 0);
        }
    }

    private void initStarters() {
        byte leader_ = firstStarter();
        for (TrickTarot t: tricks.mid(1)) {
//            if (!t.getVuParToutJoueur()) {
//                continue;
//            }
            t.setEntameur(leader_);
            leader_ = t.getRamasseur(getNombreDeJoueurs());
        }
        progressingTrick.setEntameur(leader_);
    }

    static void defined(BidTarot _bid, RulesTarot _rules, byte _taker, CustList<CustList<BoolVal>> _confidence) {
        if (!avContrat(_rules) || !_bid.isJouerDonne()) {
            confSansPreneur(_rules, _confidence);
        } else if (_rules.getDealing().getAppel() == CallingCard.DEFINED) {
            confDeterminee(_rules, _taker, _confidence);
        } else if (_rules.getDealing().getAppel() == CallingCard.WITHOUT) {
            confDef(_rules, _taker, _confidence);
        }
    }

    private void cardsToBeDiscarded() {
        cardsToBeDiscardedCount = 0;
        if (bid.getJeuChien() == PlayingDog.WITH) {
            if (tricks.isEmpty()) {
                cardsToBeDiscardedCount += getPliEnCours().total();
            } else {
                cardsToBeDiscardedCount += tricks.first().total();
            }
        }
    }

    private BidTarotTaker bid() {
        return bidding(rules.getDealing(), bids, deal.getDealer());
    }

    static BidTarotTaker bidding(DealingTarot _dealing, CustList<BidTarot> _bids, byte _dealer) {
        byte player_ = _dealing.getId().getNextPlayer(_dealer);
        byte t_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        BidTarot bid_ = BidTarot.FOLD;
        byte i_ = 0;
        byte index_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        for (BidTarot b: _bids) {
            if (b.strongerThan(bid_)) {
                t_ = player_;
                bid_ = b;
                index_ = i_;
            }
            player_ = _dealing.getId().getNextPlayer(player_);
            i_++;
        }
        return new BidTarotTaker(bid_, t_, index_);
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

    public boolean simuler(SimulatingTarot _simu) {
        ended = false;
//        _simu.prepare();
//        _simu.sleepSimu(500);
//        _simu.beginDemo();
//        _simu.displayUserHand(mainUtilisateurTriee(_simu.getDisplaying()));
//        _simu.pause();
        byte donneur_ = _simu.dealer(this);
        bid = BidTarot.FOLD;
        if (avecContrat()) {
            Bytes pls_ = orderedPlayers(playerAfter(donneur_));
            int s_ = pls_.size();
            for (int i = 0; i < s_; i++) {
                bidSimulate(_simu);
                if (_simu.stopped() == AbstractSimulatingTarot.STATE_STOPPED) {
//                if (_simu.stoppedDemo() == AbstractSimulatingTarot.STATE_STOPPED)
//                    _simu.stopDemo();
                    return false;
                }
            }
        }
        return simuPlayCards(_simu);
    }

    boolean simuPlayCards(SimulatingTarot _simu) {
        if (_simu.noBid(this)) {
//        if (!bid.isJouerDonne() && pasJeuApresPasse())
//            _simu.noBid();
            ended = true;
            return true;
        }
        simuCallDiscard(bid,_simu);
        _simu.firstLead(this);
//        _simu.displayLineReturn();
//        _simu.beginPlay();
        while (true) {
            Bytes pls_ = orderedPlayers(getEntameur());
            int s_ = pls_.size();
            for (int i = 0; i < s_; i++) {
                _simu.play(this);
                if (_simu.stopped() == AbstractSimulatingTarot.STATE_STOPPED) {
//                if (_simu.stoppedDemo() == AbstractSimulatingTarot.STATE_STOPPED)
//                    _simu.stopDemo();
                    return false;
                }
            }
//            for (byte joueur_ : pls_) {
//                beforeCards(_simu, joueur_);
//                _simu.sleepSimu(1000);
////                _simu.pause();
//                CardTarot ct_ = currentPlayerHasPlayed(_simu.getInt());
//                endCards(_simu, joueur_, ct_);
//                if (_simu.stopped()) {
//                    _simu.stopDemo();
//                    return;
//                }
//            }
            if (getDistribution().hand().estVide()) {
                _simu.ajouterPetitAuBoutPliEnCours(this);
//                _simu.displayTrickWinner(w_);
//                _simu.displaySmallBound(smallBound,w_);
                break;
            }
            _simu.ajouterPetitAuBoutPliEnCours(this);
//            _simu.displayTrickWinner(w_);
//            _simu.displaySmallBound(smallBound,w_);
//            _simu.sleepSimu(4000);
//            _simu.pause();
//            _simu.clearCarpet(getNombreDeJoueurs());
        }
//        _simu.endDeal();
        ended = true;
        return true;
    }

//    private void endCards(SimulatingTarot _simu, byte _joueur, CardTarot _ct) {
//        if (premierTourNoMisere()) {
//            _simu.declareHandfuls(_joueur,getAnnoncesPoignees(_joueur),getPoignee(_joueur));
//            _simu.declareMiseres(_joueur,getAnnoncesMiseres(_joueur));
//        }
//        if (getCalledCards().contient(_ct)) {
//            _simu.displayCalled(_joueur);
//        }
//        _simu.played(_joueur,_ct);
//        if(_joueur ==DealTarot.NUMERO_UTILISATEUR) {
//            _simu.displayUserHand(mainUtilisateurTriee(_simu.getDisplaying()));
//        }
//    }

//    private void beforeCards(SimulatingTarot _simu, byte _joueur) {
//        if (getProgressingTrick().estVide()) {
//            _simu.firstCardPlaying(_joueur);
//        } else {
//            _simu.nextCardPlaying(_joueur);
//        }
//    }
//
//    void simuStarter() {
//        if (!chelemAnnonce()) {
//            /*
//        Si un joueur n'a pas annonce de Chelem on
//        initialise l'entameur du premier pli
//        */
//            byte nbPl_ = getNombreDeJoueurs();
//            byte deal_ = deal.getDealer();
//            starter = (byte) ((deal_ + 1) % nbPl_);
//        }
//    }

    void simuCallDiscard(BidTarot _bid,SimulatingTarot _simu) {
//        DisplayingTarot displaying_ = _simu.getDisplaying();
        if (_bid.isJouerDonne()) {
//            HandTarot last_ = getDeal().derniereMain();
            if (rules.getDiscardAfterCall()) {
                initSimuTeam(_simu);
                if (_bid.getJeuChien() == PlayingDog.WITH) {
                    _simu.ecarter(this);
//                    HandTarot curHand_ = mainUtilisateurTriee(displaying_);
//                    _simu.seeDog(last_);
////                    _simu.pause();
//                    _simu.beforeSeeDog(taker,curHand_);
//                    HandTarot curHandAdd_ = new HandTarot();
//                    curHandAdd_.ajouterCartes(curHand_);
//                    curHandAdd_.ajouterCartes(last_);
//                    curHandAdd_.trier(displaying_.getDisplaying().getSuits(), displaying_.getDisplaying().isDecreasing());
//                    _simu.mergeDog(taker,curHandAdd_,last_);
//                    ecarter(_simu.getInt());
//                    HandTarot nextHand_ = mainUtilisateurTriee(displaying_);
//                    _simu.mergedDog(taker,nextHand_);
//                    _simu.autoCall(getAppele(),taker);
                } else {
                    _simu.gererChienInconnu(this);
//                    gererChienInconnu();
//                    slam(_simu.getInt());
//                    _simu.declareSlam(taker,bid);
                }
            } else {
                if (_bid.getJeuChien() == PlayingDog.WITH) {
                    _simu.appelApresEcart(this);
//                    HandTarot curHand_ = mainUtilisateurTriee(displaying_);
//                    _simu.seeDog(last_);
//                    _simu.beforeSeeDog(taker,curHand_);
//                    HandTarot curHandAdd_ = new HandTarot();
//                    curHandAdd_.ajouterCartes(curHand_);
//                    curHandAdd_.ajouterCartes(last_);
//                    curHandAdd_.trier(displaying_.getDisplaying().getSuits(), displaying_.getDisplaying().isDecreasing());
//                    _simu.mergeDog(taker,curHandAdd_, last_);
//                    appelApresEcart(_simu.getInt());
//                    HandTarot nextHand_ = mainUtilisateurTriee(displaying_);
//                    _simu.mergedDog(taker,nextHand_);
//                    _simu.callCard();
//                    _simu.callCard(taker,getCalledCards());
                } else {
                    initSimuTeam(_simu);
                    _simu.gererChienInconnu(this);
//                    gererChienInconnu();
//                    slam(_simu.getInt());
//                    _simu.declareSlam(taker,bid);
                }
            }
        } else if (avecContrat()){
            initPlayWithoutBid();
        }
    }

    void initSimuTeam(SimulatingTarot _simu) {
        if (rules.getDealing().getAppel() == CallingCard.CHARACTER_CARD
                || rules.getDealing().getAppel() == CallingCard.KING) {
            _simu.intelligenceArtificielleAppel(this);
//            intelligenceArtificielleAppel(_simu.getInt());
//            _simu.callCard();
//            _simu.callCard(taker,getCalledCards());
        } else if (rules.getDealing().getAppel() == CallingCard.DEFINED) {
            initEquipeDeterminee();
            for (byte c: getAppele()) {
                _simu.constCallPlayerCall(c);
            }
//        } else {
//            initDefense();
        }
        //....
//        if (getCalledCards().estVide()) {
//            for (byte c: getAppele()) {
//                _simu.constCallPlayer(c);
//            }
//        }
    }

    void bidSimulate(SimulatingTarot _simu) {
        if (maximumBid(bid)) {
            return;
        }
        _simu.bid(this);
//        byte p_ = playerHavingToBid();
//        BidTarot contratTmp_ = _simu.getInt().strategieContrat(this);
//        _simu.actingBid(p_);
//        _simu.sleepSimu(1000);
//        ajouterContrat(contratTmp_);
//        _simu.actedBid(p_,contratTmp_);
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

    public void ajouterCartesUtilisateur() {
//        setEntameur(getPreneur());
        progressingTrick = new TrickTarot(getPreneur());
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

    public IdList<BidTarot> allowedBids() {
        IdList<BidTarot> bids_ = new IdList<BidTarot>();
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
        return avContrat(rules);
    }

    static boolean avContrat(RulesTarot _rules) {
        ModeTarot mode_ = _rules.getMode();
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

    public boolean playerHasAlreadyBidded(byte _player) {
        return playerHasAlreadyBidded(new DefGameTarot(),_player);
    }
    /**for multi player*/
    public boolean playerHasAlreadyBidded(IntGameTarot _ia,byte _player) {
        if (hasBid(_player)) {
            return true;
        }
        playerHasAlreadyBidded(_ia);
        return false;
    }

    public BidTarot playerHasAlreadyBidded(IntGameTarot _ia) {
        BidTarot bid_ = _ia.strategieContrat(this);
        ajouterContrat(bid_);
        return bid_;
    }

    public boolean hasBid(int _player) {
        int l_ = playerHavingToBid();
        return l_ != _player;
    }

//    public BidTarot getLastBid() {
//        return lastBid;
//    }

    public BidTarot strategieContrat() {
        byte numero_ = playerHavingToBid();
        HandTarot mj_ = getDistribution().hand(numero_);
        GameTarotBid helper_ = new GameTarotBid(mj_,rules,bids,bid);
        return helper_.strategieContrat();
    }

    /**
    Renvoie la carte a appeler
     @param numero
    */
    public void ajouterContrat(BidTarot _c) {
//        if (lastHasBid != -1 && lastHasBid == _t) {
//            return;
//        }
//        lastHasBid = _t;
        byte t_ = playerHavingToBid();
        bids.add(_c);
        if (_c.isJouerDonne()) {
            setContrat(_c);
            setPreneur(t_);
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
        for (byte p: orderedPlayers((byte) 0)) {
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

//    public void initEquipeDetermineeSansPreneur() {
//        confSansPreneur(rules, confidence);
//    }

    static void confSansPreneur(RulesTarot _rules, CustList<CustList<BoolVal>> _confidence) {
        byte nombreDeJoueurs_ = (byte) _rules.getDealing().getId().getNombreJoueurs();
        for (byte i = IndexConstants.FIRST_INDEX; i < nombreDeJoueurs_; i++) {
            for (byte p: _rules.getDealing().getAppelesDetermines(i)) {
                faireConfiance(i, p, _confidence);
            }
            faireConfiance(i, i, _confidence);
        }
    }

    public void initEquipeDeterminee() {
        calledPlayers = new Bytes(rules.getDealing().getAppelesDetermines(taker));
//        confDeterminee(rules, taker, confidence);
    }

    static void confDeterminee(RulesTarot _rules, byte _taker, CustList<CustList<BoolVal>> _confidence) {
        Bytes attaquants_= _rules.getDealing().getAppelesDetermines(_taker);
        attaquants_.add(_taker);
        byte nombreDeJoueurs_ = (byte) _rules.getDealing().getId().getNombreJoueurs();
        Bytes defenseurs_=GameTarotTeamsRelation.autresJoueurs(attaquants_, nombreDeJoueurs_);
        for(byte j1_:attaquants_) {
            for(byte j2_:attaquants_) {
                if(j1_==j2_) {
                    continue;
                }
                faireConfiance(j1_, j2_, _confidence);
            }
        }
        for(byte j1_:defenseurs_) {
            for(byte j2_:defenseurs_) {
                if(j1_==j2_) {
                    continue;
                }
                faireConfiance(j1_, j2_, _confidence);
            }
        }
    }

//    public void initDefense() {
//        confDef(rules, taker, confidence);
//    }

    static void confDef(RulesTarot _rules, byte _taker, CustList<CustList<BoolVal>> _confidence) {
        Bytes defenseurs_=new Bytes();
        byte nombreDeJoueurs_ = (byte) _rules.getDealing().getId().getNombreJoueurs();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_<nombreDeJoueurs_; joueur_++) {
            if(joueur_== _taker) {
                continue;
            }
            defenseurs_.add(joueur_);
        }
        for(byte j1_:defenseurs_) {
            for(byte j2_:defenseurs_) {
                if(j1_==j2_) {
                    continue;
                }
                faireConfiance(j1_, j2_, _confidence);
            }
        }
    }

    public void appelApresEcart() {
        appelApresEcart(new DefGameTarot());
    }
    public HandTarot appelApresEcart(IntGameTarot _ia) {
        ajouterCartes(taker,derniereMain());
        CallDiscard appel_ = _ia.strategieAppelApresEcart(this,false);
        if(!appel_.getCarteAppelee().estVide()) {
            setCarteAppelee(appel_.getCarteAppelee());
            initConfianceAppele();
        }
        HandTarot atouts_ = fwdToDog(appel_.getEcartAFaire());
        ajouterChelem(appel_.isChelem());
        return atouts_;
//        if(appel_.isChelem()) {
//            ajouterChelem(true);
//            setEntameur(taker);
//        } else {
//            ajouterChelem(false);
//        }
    }

    private HandTarot fwdToDog(HandTarot _hand) {
        supprimerCartes(taker, _hand);
        return trickTaker(_hand);
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
            IdList<Suit> couleursNonAppelees_ = new IdList<Suit>();
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
        intelligenceArtificielleAppel(new DefGameTarot());
    }
    public void intelligenceArtificielleAppel(IntGameTarot _ia) {
        HandTarot cartesAppeler_ = _ia.strategieAppel(this);
        setCarteAppelee(cartesAppeler_);
        initConfianceAppele();
    }

    public boolean isCallingState() {
        if (!bid.isJouerDonne()) {
            return false;
        }
        return getCarteAppelee().estVide() && !callableCards().estVide();
    }
//    boolean noCalledCardsInHand() {
//        for (byte p :orderedPlayers(taker)) {
//            for (CardTarot c: calledCards) {
//                if (getDistribution().hand(p).contient(c)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
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
        byte nombreDeJoueurs_ = (byte) NumberUtil.min(deal.nombreDeMains(),getNombreDeJoueurs());
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
//        confianceAppele();
//        if (getContrat().getJeuChien() == PlayingDog.WITH && noCalledCardsInHand()) {
//            initDefense();
//        }
    }

//    void confianceAppele() {
//        if (taker == -1) {
//            return;
//        }
//        for(byte a: calledPlayers) {
//            if(taker!=a) {
//                faireConfiance(a,taker, confidence);
//            }
//        }
//    }

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
        boolean allowed_ = ecartables(m, cardsToBeDiscardedCount - 1).contient(_c);
        if (!allowed_) {
            cardsToBeDiscardedCount--;
        }
        if (allowed_) {
            return ReasonDiscard.NOTHING;
        }
        return reasonDiscard(_c);
    }
    public HandTarot ecartables() {
        HandTarot union_ = new HandTarot();
        union_.ajouterCartes(getDistribution().hand(getPreneur()));
        union_.ajouterCartes(getPliEnCours().getCartes());
        return ecartables(union_, 0);
    }

    private HandTarot ecartables(HandTarot _m, int _ec) {
        return GameTarotCallDiscard.getCartesEcartables(getDistribution()
                        .derniereMain().total() - _ec,
                _m.couleurs());
    }

    public static ReasonDiscard reasonDiscard(CardTarot _c) {
        if (_c.getId().getNomFigure() == CardChar.KING) {
            return ReasonDiscard.KING;
        }
        if (_c.getId().getCouleur() == Suit.TRUMP) {
            if (_c.estUnBout()) {
                return ReasonDiscard.TRUMP_CARD_OULDER;
            }
            return ReasonDiscard.TRUMP_CARD;
        }
        return ReasonDiscard.OULDER;
    }

    public void ecarter() {
        ecarter(new DefGameTarot());
    }
//    public void ecarter(IntGameTarot _ia,boolean _createTrick) {
////        if (!_createTrick) {
////            ajouterCartes(getPreneur(),getPliEnCours().getCartes());
////            getPliEnCours().getCartes().supprimerCartes();
////            //On ajoute les cartes du chien au preneur pour en ecarter d'autres
////            ecarter(_ia);
////            return;
////        }
//        ecarter(_ia);
//    }

    public HandTarot ecarter(IntGameTarot _ia) {
        ajouterCartes(getPreneur(),derniereMain());
        //On ajoute les cartes du chien au preneur pour en ecarter d'autres
        HandTarot mt_=_ia.strategieEcart(this);
        //Le preneur ecarte les cartes qu'il veut
        HandTarot atouts_ = fwdToDog(mt_);

        ajouterChelem(_ia.annoncerUnChelem(this));
        return atouts_;
//        setStarterIfSlam();
    }

    void ajouterCartesDansPliEnCours(HandTarot _mt) {
        for(CardTarot ct_: _mt) {
            ajouterUneCarteDansPliEnCoursSimple(ct_);
        }
    }

//    private void setStarterIfSlam() {
//        if(chelemAnnonce()) {
//            setEntameur(taker);
//        }
//    }

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
        deal.hand(getPreneur()).ajouter(_ct);
        invaliderAjoutCarteAuChien();
    }


    public void gererChienInconnuDirect() {
        gererChienInconnu();
        firstLead();
    }

    public void gererChienInconnuChelemDirect() {
        gererChienInconnu();
        ajouterChelemUtilisateur();
        firstLead();
    }
    public void gererChienInconnu() {
        trickTaker(derniereMain());
    }

    private HandTarot trickTaker(HandTarot _cards) {
//        setEntameur(taker);
        progressingTrick = new TrickTarot(taker);
        ajouterCartesDansPliEnCours(_cards);
        tricks.add(progressingTrick);
        return getPliEnCours().getCartes().couleur(Suit.TRUMP);
    }
    public boolean annoncerUnChelem() {
        HandTarot mainJoueur_ = getDistribution().hand(getPreneur());
        return annoncerUnChelem(mainJoueur_);
    }
    private boolean annoncerUnChelem(HandTarot _mainJoueur) {
        return annoncerUnChelem(_mainJoueur,calledCards);
    }
    private boolean annoncerUnChelem(HandTarot _mainJoueur, HandTarot _called) {
        IdMap<Suit,HandTarot> repartition_ = _mainJoueur.couleurs();
        return GameTarotBid.estUnJeuDeChelem(repartition_, new HandTarot().couleurs(), rules, _called);
    }

    public void slam() {
        slam(new DefGameTarot());
    }
    public void slam(IntGameTarot _ia) {
        ajouterChelem(_ia.annoncerUnChelem(this));
//        if (bid.isFaireTousPlis()) {
//            setEntameur(getPreneur());
//        }
    }
    public void ajouterChelemUtilisateur() {
        ajouterChelem(true);
//        setEntameur(getPreneur());
    }
    void ajouterChelem(boolean _annonce) {
        BidTarot n_;
        if (bid == BidTarot.TAKE) {
            n_ = BidTarot.SLAM_TAKE;
        } else if (bid == BidTarot.GUARD) {
            n_ = BidTarot.SLAM_GUARD;
        } else if (bid == BidTarot.GUARD_WITHOUT) {
            n_ = BidTarot.SLAM_GUARD_WITHOUT;
        } else if (bid == BidTarot.GUARD_AGAINST) {
            n_ = BidTarot.SLAM_GUARD_AGAINST;
        } else {
            n_ = bid;
        }
        byte b_ = bid().getIndex();
        if (bids.isValidIndex(b_) && _annonce) {
            bid = n_;
            bids.set(b_, bid);
        }
//        declaresSlam.set( _b,ComparatorBoolean.of(_annonce));
    }


//    public boolean chelemAnnonce(byte _numero) {
//        return bid.isFaireTousPlis();
////        return declaresSlam.get(_numero) == BoolVal.TRUE;
//    }

    /** Est vrai si et seulement si un chelem est annonce */
    public boolean chelemAnnonce() {
//        boolean contientChelem_ = bid.isFaireTousPlis();
//        for (BoolVal chelem_ : declaresSlam) {
//            if (chelem_ == BoolVal.TRUE) {
//                contientChelem_ = true;
//            }
//        }
        return bid.isFaireTousPlis();
    }
//    public void firstLeadIfPossible() {
//        if(premierTour()) {
//            firstLead();
//        }
//    }

    public void firstLead() {
        byte starter_ = firstStarter();
        progressingTrick = new TrickTarot(starter_);
    }

    private byte firstStarter() {
         if(!chelemAnnonce()) {
            /*Si un joueur n'a pas annonce de Chelem on initialise l'entameur du premier pli*/
             byte donneur_=getDistribution().getDealer();
             return playerAfter(donneur_);
         } else {
             return getPreneur();
         }
    }

    public TrickTarot getPliEnCours() {
        return getProgressingTrick();
    }

    public IdList<Handfuls> getAnnoncesPoigneesPossibles(byte _numero) {
        HandTarot mainJoueur_ = getDistribution().hand(_numero);
        return GameTarotDeclaring.getAnnoncesPoigneesPossibles(rules,mainJoueur_);

    }


    public IdList<Handfuls> strategieAnnoncesPoignees(byte _numeroJoueur) {
        HandTarot mainJoueur_ = getDistribution().hand(_numeroJoueur);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation(changerConfiance());
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo(teamsRelation_);
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls);
        return g_.strategieAnnoncesPoignees(calledCards);
    }

    public boolean isValidHandful(Handfuls _h, HandTarot _hand, HandTarot _excludedCards) {
        return isValidHandful(rules, _h, _hand, _excludedCards);
    }

    public static boolean isValidHandful(RulesTarot _rules, Handfuls _h, HandTarot _hand, HandTarot _excludedCards) {
        int nbTrumps_ = _rules.getAllowedHandfuls().getVal(_h);
        return _hand.total() == nbTrumps_ && (!_hand.contient(CardTarot.excuse()) || _excludedCards.estVide());
    }

    public HandTarot strategiePoignee(byte _numeroJoueur) {
        HandTarot mainJoueur_ = getDistribution().hand(_numeroJoueur);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation(changerConfiance());
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo(teamsRelation_);
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls);
        return g_.strategiePoignee();
    }

    public void setAnnoncesPoignees(IdList<Handfuls> _ann) {
        declaresHandfuls.set(playerHavingToPlay(), _ann);
    }

    public void ajouterPoignee(HandTarot _mt) {
        handfuls.set(playerHavingToPlay(), _mt);
    }

    public IdList<Handfuls> getAnnoncesPoignees(byte _numero) {
        return declaresHandfuls.get(_numero);
    }

    public HandTarot getPoignee(byte _b) {
        return handfuls.get(_b);
    }

    public IdList<Miseres> getAnnoncesMiseresPossibles(byte _numero) {
        HandTarot mainJoueur_ = getDistribution().hand(_numero);
        return GameTarotDeclaring.getAnnoncesMiseresPossibles(mainJoueur_);
    }

    public IdList<Miseres> strategieAnnoncesMiseres(byte _numeroJoueur) {
        HandTarot mainJoueur_ = getDistribution().hand(_numeroJoueur);
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation(changerConfiance());
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo(teamsRelation_);
        GameTarotDeclaring g_ = new GameTarotDeclaring(doneTrickInfo_,teamsRelation_,
                mainJoueur_,declaresHandfuls);
        return g_.strategieAnnoncesMiseres();
    }

    public void setAnnoncesMiseres(IdList<Miseres> _ann) {
        declaresMiseres.set(playerHavingToPlay(), _ann);
    }

    public IdList<Miseres> getAnnoncesMiseres(byte _numero) {
        return declaresMiseres.get(_numero);
    }
//
//
//    /** Appelee au debut d'une partie */
//    public void setEntameur(byte _i) {
//        if (bid.isFaireTousPlis()) {
//            starter = taker;
//        } else {
//            setStarter(_i);
//        }
//    }

//    public void setStarter(byte _starter) {
//        starter = _starter;
//    }

    public boolean autorise(CardTarot _c) {
        HandTarot pl_ = autorise();
        return pl_.contient(_c);
    }

    public HandTarot autorise() {
        HandTarot main_ = getDistribution().hand(playerHavingToPlay());
        IdMap<Suit,HandTarot> repartition_ = main_.couleurs();
        HandTarotResult pl_ = HandTarotResult.cartesJouables(getRegles(),taker, repartition_, getProgressingTrick(), getTricks(), getCalledCards());
        reason = pl_.getReason();
        return pl_.getPlayable();
    }

    HandTarot playableCards(IdMap<Suit,HandTarot> _repartitionMain) {
        return HandTarotResult.cartesJouables(getRegles(),taker,_repartitionMain, getProgressingTrick(), getTricks(), getCalledCards()).getPlayable();
    }

    public ReasonPlayTarot getReason() {
        return reason;
    }

    public boolean premierTourNoMisere() {
        return premierTour() && pasJeuMisere();
    }
    public boolean premierTour() {
        return GameTarotCommonPlaying.premierTour(taker,getTricks());
    }

    public boolean currentPlayerHasPlayed(byte _player) {
        return currentPlayerHasPlayed(new DefGameTarot(),_player);
    }
    /**for multi player*/
    public boolean currentPlayerHasPlayed(IntGameTarot _ia,byte _player) {
        if (aJoue(_player)) {
            return true;
        }
        currentPlayerHasPlayed(_ia);
        return false;
    }

    public CardTarot currentPlayerHasPlayed(IntGameTarot _ia) {
        CardTarot card_ = _ia.changerConfianceJeuCarteUnique(this);
        ajouterUneCarteDansPliEnCours(card_);
        return card_;
    }

    public boolean aJoue(byte _player) {
        return getPliEnCours().aJoue(_player, getNombreDeJoueurs());
    }

    public CardTarot changerConfianceJeuCarteUnique() {
        CardTarot playedCard_ = changerConfianceJeuCarteUniqueQuick();
        if (premierTourNoMisere())  {
            byte nombreDeJoueurs_ = getNombreDeJoueurs();
            byte joueur_ = progressingTrick.getNextPlayer(nombreDeJoueurs_);
            IdList<Miseres> annoncesMiseres_ = strategieAnnoncesMiseres(
                    joueur_);
            setAnnoncesMiseres(annoncesMiseres_);
            IdList<Handfuls> annoncesPoignees_ = strategieAnnoncesPoignees(
                    joueur_);
            setAnnoncesPoignees(annoncesPoignees_);
            HandTarot poignee_ = strategiePoignee(joueur_);
            ajouterPoignee(poignee_);
        }
        return playedCard_;
    }
    public CardTarot changerConfianceJeuCarteUniqueQuick() {
//        changerConfiance();
        return strategieJeuCarteUnique();
    }
    public CustList<CustList<BoolVal>> changerConfiance() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        byte numero_ = progressingTrick.getNextPlayer(nombreDeJoueurs_);
        CustList<CustList<BoolVal>> cf_ = buildConfidence();
//        if (!def_) {
//            if (getRegles().getDealing().getAppel() == CallingCard.DEFINED) {
//                confSansPreneur(rules, cf_);
//            }
//            if (getContrat().getJeuChien() == PlayingDog.WITH && noCalledCardsInHand()) {
//                confDef(rules, taker, cf_);
//            }
//        }
        HandTarot mainJoueur_ = getDistribution().hand(numero_);
        if(mainJoueur_.contientCartes(calledCards)) {
//            GameTarotTeamsRelation teamRel_ = getTeamsRelation();
//            if (taker != -1 && teamRel_.statutDe(numero_) == Role.CALLED_PLAYER) {
//                faireConfiance(numero_, taker, cf_);
//            }
            return cf_;
        }
        boolean carteAppeleeJouee_ = carteAppeleeJouee();
        if(carteAppeleeJouee_) {
            GameTarotTeamsRelation teamRel_ = getTeamsRelation(cf_);
            teamRel_.determinerConfiance(numero_, nombreDeJoueurs_);
            return cf_;
        }
        return updateConfidence(cf_);
    }

    public CustList<CustList<BoolVal>> buildConfidence() {
        CustList<CustList<BoolVal>> cf_ = buildConfidenceSupp();
        boolean carteAppeleeJouee_ = carteAppeleeJouee();
        if(carteAppeleeJouee_ && taker != -1) {
            GameTarotTeamsRelation teamRel_ = getTeamsRelation(cf_);
            teamRel_.determinerConfiances();
        }
        return cf_;
    }

    public CustList<CustList<BoolVal>> buildConfidenceSupp() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CustList<CustList<BoolVal>> cf_ = buildConfidenceCore();
        byte numero_ = progressingTrick.getNextPlayer(nombreDeJoueurs_);
        HandTarot mainJoueur_ = getDistribution().hand(numero_);
        if (mainJoueur_.contientCartes(calledCards) && taker != -1 && GameTarotTeamsRelation.statutDe(numero_, taker, calledPlayers) == Role.CALLED_PLAYER) {
            faireConfiance(numero_, taker, cf_);
        }
        return cf_;
    }

    public CustList<CustList<BoolVal>> buildConfidenceCore() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        CustList<CustList<BoolVal>> cf_ = confidence(nombreDeJoueurs_);
        defined(bid, rules, taker, cf_);
        return cf_;
    }

    CustList<CustList<BoolVal>> updateConfidence(CustList<CustList<BoolVal>> _cf) {
        //        IdMap<Suit,HandTarot> repartition_ = mainJoueur_.couleurs();
        GameTarotTeamsRelation tr_ = getTeamsRelation(_cf);
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo(tr_);
//        HandTarot cartesJouees_ = doneTrickInfo_.cartesJoueesEnCours(numero_);
//        IdMap<Suit,HandTarot> repartitionCartesJouees_ = cartesJouees_.couleurs();
//        CustList<CustList<HandTarot>> suites_ = new CustList<CustList<HandTarot>>();
//        suites_.add(new CustList<HandTarot>());
//        suites_.add(repartition_.getVal(Suit.TRUMP).eclaterEnCours(
//                repartitionCartesJouees_, Suit.TRUMP));
//        for (Suit i : couleursOrdinaires()) {
//            suites_.add(repartition_.getVal(i).eclaterEnCours(
//                    repartitionCartesJouees_, i));
//        }
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        byte numero_ = progressingTrick.getNextPlayer(nombreDeJoueurs_);
        HandTarot mainJoueur_ = getDistribution().hand(numero_);
        IdMap<Suit,CustList<HandTarot>> cartesPossibles_ = doneTrickInfo_.cartesPossibles(mainJoueur_);
        IdMap<Hypothesis,IdMap<Suit,CustList<HandTarot>>> hypotheses_ = doneTrickInfo_.cartesCertaines(cartesPossibles_);
        cartesPossibles_ = hypotheses_.getVal(Hypothesis.POSSIBLE);
        IdMap<Suit,CustList<HandTarot>> cartesCertaines_ = hypotheses_
                .getVal(Hypothesis.SURE);
        CustList<TrickTarot> plisFaits_ = new CustList<TrickTarot>();
        plisFaits_.addAllElts(tricks);
        plisFaits_.add(progressingTrick);
        return GameTarotTrickHypothesis.hypothesesRepartitionsJoueurs(tr_,calledCards, plisFaits_, numero_, cartesPossibles_,
                cartesCertaines_);
    }

    public static CustList<CustList<BoolVal>> confidence(byte _nb) {
        CustList<CustList<BoolVal>> cf_ = new CustList<CustList<BoolVal>>();
        for (int i = IndexConstants.FIRST_INDEX; i < _nb; i++) {
        /*
        Initialise la confiance a un
        jeu non solitaire
        */
            cf_.add(new CustList<BoolVal>());
            for (int j = IndexConstants.FIRST_INDEX; j < _nb; j++) {
                cf_.last().add(ComparatorBoolean.of(i == j));
            }
        }
        return cf_;
    }

    static void faireConfiance(byte _joueur, byte _enjoueur, CustList<CustList<BoolVal>> _conf) {
        _conf.get(_joueur).set( _enjoueur, BoolVal.TRUE);
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
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation(changerConfiance());
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo(teamsRelation_);
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
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation(changerConfiance());
        GameTarotTrickInfo doneTrickInfo_ = getDoneTrickInfo(teamsRelation_);
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
        return getPliEnCours().getEntameur();
    }

    /**
    Renvoie vrai si et seulement si la carte appelee n'existe pas pour cette
    partie ou si la carte appelee existe et est jouee
    */
    public boolean carteAppeleeJouee() {
        HandTarot m = new HandTarot();
        for (TrickTarot t: tricks.mid(1)) {
//            if (!t.getVuParToutJoueur()) {
//                continue;
//            }
            m.ajouterCartes(t.getCartes());
        }
        m.ajouterCartes(progressingTrick.getCartes());
        return m.contientCartes(calledCards);
    }

//    boolean confiance(byte _joueur, byte _enjoueur) {
//        return confidence.get(_joueur).get(_enjoueur) == BoolVal.TRUE;
//    }

    static BoolVal confiance(CustList<CustList<BoolVal>> _cf,byte _joueur, byte _enjoueur) {
        return _cf.get(_joueur).get(_enjoueur);
    }


//    /**
//    Inclut tous les plis sauf celui qui est en cours classes dans l'ordre
//    chronologique (par leur numero) On a pour tout pli d'indice i
//    unionPlis.get(i).getNumero()==i
//    */
//    public CustList<TrickTarot> unionPlis() {
//        CustList<TrickTarot> unionPlis_ = new CustList<TrickTarot>();
//        unionPlis_.addAllElts(tricks);
//        return unionPlis_;
//    }


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

    public void ajouterUneCarteDansPliEnCoursPreneur(CardTarot _c) {
        jouer(taker,_c);
        ajouterUneCarteDansPliEnCoursSimple(_c);
    }
    public void ajouterUneCarteDansPliEnCours(CardTarot _c) {
        jouer(playerHavingToPlay(),_c);
        ajouterUneCarteDansPliEnCoursSimple(_c);
    }

    void ajouterUneCarteDansPliEnCoursSimple(CardTarot _c) {
        progressingTrick.ajouter(_c);
    }

    /**
    A la fin d'un pli on ramasse les cartes et on les ajoute dans des tas
    */
    byte ajouterPliEnCours() {

        // nombreJoueurs jouant au tarot
//        trickWinner = progressingTrick.getRamasseur();

        tricks.add(progressingTrick);
//        if(!getDistribution().hand().estVide()) {
//            starter = trickWinner;
//        }
        return progressingTrick.getRamasseur();
    }

    public HandTarot addCurTrickDiscarded() {
        addCurTrick();
        HandTarot atouts_ = getPliEnCours().getCartes().couleur(Suit.TRUMP);
        firstLead();
        return atouts_;
    }
    public void addCurTrick() {
        getTricks().add(getProgressingTrick());
    }

//    public byte getRamasseur() {
//        return trickWinner;
//    }

    /**
    Appele au debut d'un pli mais pas d'une partie, celui qui ramasse entame
    le pli suivant
    */
//    private void setEntameur() {
//        starter = trickWinner;
//    }

    public byte ajouterPetitAuBoutPliEnCours() {
        byte win_ = ajouterPliEnCours();
        ajouterPetitAuBout(win_);
        progressingTrick = new TrickTarot(win_);
        return win_;
    }

    void ajouterPetitAuBout(byte _winner) {
        if(getDistribution().hand().total() > 1) {
            return;
        }
        if(!getPliEnCours().contient(CardTarot.petit())) {
            return;
        }
        if(getDistribution().hand().estVide()) {
            /*Le Petit est mene au bout*/
            smallBound.set( _winner, BoolVal.TRUE);
            return;
        }
        //getDistribution().main().total() == 1
        GameTarotTeamsRelation teamsRelation_ = getTeamsRelation();
        Bytes partenaires_ = teamsRelation_.tousCoequipiers(_winner);
        partenaires_.add(_winner);
        boolean possedeExcuseMemeEquipe_ = false;
        for (byte b1_ : partenaires_) {
            if (getDistribution().hand(b1_).contient(CardTarot.excuse())) {
                possedeExcuseMemeEquipe_ = true;
            }
        }
        if (possedeExcuseMemeEquipe_ && !teamsRelation_.adversaireAFaitPlis(_winner, tricks)) {
            //ajouterPetitAuBoutCasChelem
            smallBound.set(_winner, BoolVal.TRUE);
        }
    }

    public EndTarotGame getEndTarotGame() {
        GameTarotTeamsRelation t_ = getTeamsRelation();
        return new EndTarotGame(t_,tricks,declaresHandfuls,declaresMiseres, smallBound);
    }

    public GameTarotTeamsRelation getTeamsRelation() {
        return getTeamsRelation(buildConfidenceCore());
    }

    public GameTarotTeamsRelation getTeamsRelation(CustList<CustList<BoolVal>> _confidence) {
        return new GameTarotTeamsRelation(taker,calledPlayers,_confidence,rules);
    }
    public GameTarotTrickInfo getDoneTrickInfo(GameTarotTeamsRelation _tr) {
        Ints handLengths_ = new Ints();
        for (HandTarot h: deal) {
            handLengths_.add(h.total());
        }
        GameTarotTrickInfo gameTarotTrickInfo_ = new GameTarotTrickInfo(progressingTrick, tricks,
                declaresMiseres,
                handfuls, bid, calledCards,
                handLengths_);
        gameTarotTrickInfo_.addSeenDeck(deal.derniereMain(),_tr);
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
        for (TrickTarot pli_ : _plisFaits.mid(1)) {
            for (CardTarot carte_ : pli_) {
//                if (pli_.getVuParToutJoueur()) {
//
//                }
                ajouter(pli_.joueurAyantJoue(carte_),carte_);
            }
        }
        if (existePreneur()) {
            ajouterCartes(taker,_plisFaits.first().getCartes());
            supprimerCartes(taker,derniereMain());
        }
    }

    boolean existPlayedCard() {
        return !getTricks().isEmpty() || !getPliEnCours().estVide();
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

    private static IdList<Suit> couleursOrdinaires() {
        return Suit.couleursOrdinaires();
    }

    public DealTarot getDeal() {
        return deal;
    }

    public void setDeal(DealTarot _deal) {
        deal = _deal;
    }

    public CustList<IdList<Handfuls>> getDeclaresHandfuls() {
        return declaresHandfuls;
    }

    public void setDeclaresHandfuls(CustList<IdList<Handfuls>> _declaresHandfuls) {
        declaresHandfuls = _declaresHandfuls;
    }

    public CustList<IdList<Miseres>> getDeclaresMiseres() {
        return declaresMiseres;
    }

    public void setDeclaresMiseres(CustList<IdList<Miseres>> _declaresMiseres) {
        declaresMiseres = _declaresMiseres;
    }

//    public CustList<BoolVal> getDeclaresSlam() {
//        return declaresSlam;
//    }

//    public void setDeclaresSlam(CustList<BoolVal> _declaresSlam) {
//        declaresSlam = _declaresSlam;
//    }

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

//    public CustList<CustList<BoolVal>> getConfidence() {
//        return confidence;
//    }

//    public void setConfidence(CustList<CustList<BoolVal>> _confidence) {
//        confidence = _confidence;
//    }

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

    public IdList<BidTarot> getBids() {
        return bids;
    }

    public void setBids(IdList<BidTarot> _bids) {
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
