package cards.president;

import cards.consts.GameType;
import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import code.maths.montecarlo.AbstractGenerator;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;


public final class GamePresident {

    /**
    Le type d'une partie est aleatoire ou encore edite ou encore un
    entrainement
    */
    private GameType type;
    /** Contient toutes les cartes au debut de chaque partie */
    private DealPresident deal;

    /** Ce sont les plis faits par les joueurs */
    /** PliTarot en cours d'etre joue */
    private TrickPresident progressingTrick = new TrickPresident(IndexConstants.INDEX_NOT_FOUND_ELT);

    /** Ensemble des plis faits par les joueurs */
    private CustList<TrickPresident> tricks = new CustList<TrickPresident>();

    /**
    Scores cumules au cours des parties Chaque nombre (Short) represente un
    score pour le joueur
    */
    private Shorts scores = new Shorts();
    /** Nombre de fois qu'a ete joue la partie(partie fini) */
    private long number;
    private RulesPresident rules = new RulesPresident();

    private Bytes ranks = new Bytes();

    private CustList<HandPresident> switchedCards = new CustList<HandPresident>();

    private String error = "";

//    private CustList<BoolVal> passOrFinish = new CustList<BoolVal>();

    private ByteMap<Playing> lastStatus = new ByteMap<Playing>();

    private boolean ended;

    /** Constructeur permettant le chargement d'une partie de tarot */
    public GamePresident() {
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
    public GamePresident(GameType _type, DealPresident _donne, RulesPresident _regles, Bytes _ranks) {
        type = _type;
        deal = _donne;
        rules = _regles;
        lastStatus = new ByteMap<Playing>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.add((short) 0);
//            passOrFinish.add(BoolVal.FALSE);
            lastStatus.addEntry((byte) i, Playing.CAN_PLAY);
        }
        ranks = new Bytes(_ranks);
        progressingTrick.setEntameur(getFirstLeader());
    }

    public void initPartie() {
        tricks = new CustList<TrickPresident>();
        progressingTrick = new TrickPresident(getFirstLeader());
        lastStatus = new ByteMap<Playing>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.set(i, (short) 0);
//            passOrFinish.set(i, BoolVal.FALSE);
            lastStatus.addEntry((byte) i, Playing.CAN_PLAY);
        }
    }

    CustList<TrickPresidentIndexesCheck> loadGame() {
        deal.setDealer((byte) (NumberUtil.mod(deal.getDealer(), getNombreDeJoueurs())));
        byte leader_ = getFirstLeader();
        for (TrickPresident t: tricks) {
            t.setEntameur(leader_);
            leader_ = t.getRamasseur(getNombreDeJoueurs());
//            if (t.getNombreDeCartesParJoueur() > 0) {
//                leader_ = t.getRamasseur(getNombreDeJoueurs());
//            } else {
//                leader_ = (byte) ((leader_ + t.total()) % getNombreDeJoueurs());
//            }
        }
        progressingTrick.setEntameur(leader_);
//        passOrFinish = passOrFinish();
        lastStatus = new ByteMap<Playing>();
        emptyTrick();
//        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++){
//            if (getDeal().hand((byte)i).estVide()) {
//                lastStatus.put((byte) i, Playing.FINISH);
//            } else {
//                lastStatus.put((byte) i, Playing.CAN_PLAY);
//            }
//        }
        return setLastStatus();
    }
    public boolean availableSwitchingCardsNotReady(){
        return availableSwitchingCards() && !readyToPlay();
    }

    public boolean availableSwitchingCards() {
        return !ranks.isEmpty()&&allowSwitchCards();
    }

    public void initCartesEchanges() {
        if(availableSwitchingCards()) {
            switchedCards.clear();
            for(byte p=0;p<getNombreDeJoueurs();p++) {
                switchedCards.add(new HandPresident());
            }
        }
    }

    public boolean simulate(int _nbTimes, SimulatingPresident _simu, AbstractGenerator _gene) {
        ended = false;
//        _simu.prepare();
//        _simu.sleepSimu(500);
//        _simu.beginDemo();
//        _simu.displayUserHand(mainUtilisateurTriee(_simu.getDisplaying()));
//        _simu.pause();
        HandPresident userHand_ = _simu.userHand(this);
        int noDeal_ = IndexConstants.SIZE_EMPTY;
        while (noDeal_ < _nbTimes) {
            if (_simu.stopped() == AbstractSimulatingPresident.STATE_STOPPED) {
//            if (_simu.stoppedDemo() == AbstractSimulatingPresident.STATE_STOPPED)
                return false;
            }
            HandPresident firstUserHand_ = new HandPresident(deal.hand());
            initCartesEchanges();
            donnerMeilleuresCartes();
            HandPresident secondUserHand_ = new HandPresident(deal.hand());
            giveWorstCards(_simu.getInt());
            CustList<HandPresident> switchedCards_ = new CustList<HandPresident>();
            for (HandPresident p: switchedCards) {
                switchedCards_.add(new HandPresident(p));
            }
            HandPresident thirdUserHand_ = new HandPresident(deal.hand());
            byte leader_ = befDeal(_simu, userHand_, noDeal_, firstUserHand_, secondUserHand_, switchedCards_, thirdUserHand_);
//            byte leader_ = getFirstLeader();
            progressingTrick = new TrickPresident(leader_);
            long nextNo_ = noDeal_;
//            passOrFinish = passOrFinish();
            while (true) {
                HandPresident h_ = _simu.playedCards(this);
//                HandPresident h_ = _simu.getInt().playedCards(this);
//                beforeCards(_simu);
//                _simu.sleepSimu(100);
                if (_simu.stopped() == AbstractSimulatingPresident.STATE_STOPPED) {
//                if (_simu.stoppedDemo() == AbstractSimulatingPresident.STATE_STOPPED)
                    return false;
                }
//                if (_simu.stopped()) {
//                    _simu.stopDemo();
//                    return;
//                }
//                _simu.pause();
//                byte nextPlayerBk_ = addCardsToCurrentTrickAndLoop(h_);
////                byte nextPlayerBk_ = setupStatus(h_);
////                _simu.gearStatusChange(lastStatus, nextPlayerBk_);
////                addCardsToCurrentTrick(h_);
////                lookupNextPlayer();
//                _simu.displayPlayedHand(h_);
//                _simu.gearStatusChange(lastStatus, nextPlayer());
//                _simu.displayPlayedHandMessage(h_,nextPlayerBk_);
//                endCards(_simu, userHand_, h_, nextPlayerBk_);

                _simu.addCardsToCurrentTrickAndLoop(this,h_,userHand_);

                if (!keepPlayingCurrentGame()) {
                    TricksHandsPresident tricksHands_ = new TricksHandsPresident();
                    tricksHands_.setDistributionCopy(getDeal());
                    tricksHands_.setNumberMaxSwitchedCards(nombresCartesEchangesMax());
                    tricksHands_.setRanks(new Bytes(ranks));
                    tricksHands_.setSwitchedCards(switchedCards_);
                    tricksHands_.setTricks(new CustList<TrickPresident>(tricks), new TrickPresident(), getNombreDeJoueurs());
                    tricksHands_.sortHands(_simu.getDisplaying(), getNombreDeJoueurs());
                    _simu.getHistory().add(tricksHands_);
                    nextNo_++;
//                    _simu.endDeal();
//                    _simu.sleepSimu(5000);
//                    _simu.pause();
                    break;
                }
            }
            Bytes ranks_ = _simu.getNewRanks(this, noDeal_);
//            HandPresident stackNext_ = empiler();
//            byte dealer_ = getDeal().getDealer();
            deal = _simu.getInt().empiler(nextNo_,this,_gene);
//            deal = _simu.getInt().empiler(noDeal_ + 1L,this,_gene);
//            deal = new DealPresident(noDeal_ + 1L, stackNext_);
//            deal.donneurSuivant(dealer_,rules);
//            deal.initDonne(rules,_gene);
            tricks = new CustList<TrickPresident>();
            ranks = new Bytes(ranks_);
            byte nombreJoueurs_ = getNombreDeJoueurs();
            for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
                lastStatus.put((byte)i, Playing.CAN_PLAY);
            }
//            _simu.prepare();
            noDeal_ = _simu.prepareNext(noDeal_);
//            noDeal_++;
        }
        ended = true;
        return true;
    }

//    private void endCards(SimulatingPresident _simu, HandPresident _userHand, HandPresident _h, byte _nextPlayerBk) {
//        if (_nextPlayerBk == DealPresident.NUMERO_UTILISATEUR) {
//            _userHand.supprimerCartes(_h);
//            _simu.displayUserHand(_userHand);
//        }
//        if (progressingTrick.estVide()) {
//            _simu.displayTrickLeader(_nextPlayerBk);
//            _simu.sleepSimu(2000);
//        }
//    }

//    private void beforeCards(SimulatingPresident _simu) {
//        if (progressingTrick.estVide()) {
//            _simu.setupDeck();
//            _simu.gearStatusChange(lastStatus,progressingTrick.getEntameur());
//        }
//    }

    private byte befDeal(SimulatingPresident _simu, HandPresident _userHand, int _noDeal, HandPresident _firstUserHand, HandPresident _secondUserHand, CustList<HandPresident> _switchedCards, HandPresident _thirdUserHand) {
        if (availableSwitchingCards()) {
            Bytes losers_ = GamePresident.getLoosers(ranks, nombresCartesEchangesMax());
            Bytes winners_ = GamePresident.getWinners(ranks, nombresCartesEchangesMax());
//            HandPresident hUser_;
            _userHand.supprimerCartes();
            _userHand.ajouterCartes(_simu.displayUserHand(_firstUserHand,this));


//            hUser_ = mainUtilisateurTriee(_firstUserHand, _simu.getDisplaying());
//            _userHand.ajouterCartes(hUser_);
//            _simu.displayUserHand(_userHand);
//            for (byte l: losers_) {
//                byte w_ = GamePresident.getMatchingWinner(winners_, losers_, l);
//                HandPresident h_ = _switchedCards.get(l);
//                _simu.displayLooserMessage(h_,l,w_);
//            }
//            _simu.displayLineReturn();
            _userHand.supprimerCartes();
            _userHand.ajouterCartes(_simu.displayUserHand(_secondUserHand,this));


//            hUser_ = mainUtilisateurTriee(_secondUserHand, _simu.getDisplaying());
//            _userHand.ajouterCartes(hUser_);
//            _simu.displayUserHand(_userHand);
//            for (byte w: winners_) {
//                byte l_ = GamePresident.getMatchingLoser(winners_, losers_, w);
//                HandPresident h_ = _switchedCards.get(w);
//                _simu.displayWinnerMessage(h_,l_,w);
//            }
//            _simu.displayLineReturn();
            _userHand.supprimerCartes();
            _userHand.ajouterCartes(_simu.displayUserHand(_thirdUserHand,this));

//            hUser_ = mainUtilisateurTriee(_thirdUserHand, _simu.getDisplaying());
//            _userHand.ajouterCartes(hUser_);
//            _simu.displayUserHand(_userHand);
            return _simu.displaySwitchedUserHand(this,winners_,losers_, _noDeal,_switchedCards);
        }
        _userHand.supprimerCartes();
        _userHand.ajouterCartes(_simu.displayUserHand(_thirdUserHand,this));
        return getFirstLeader();

//            HandPresident h_ = mainUtilisateurTriee(_thirdUserHand, _simu.getDisplaying());
//            _userHand.supprimerCartes();
//            _userHand.ajouterCartes(h_);
//            _simu.displayUserHand(_userHand);
    }

//    void initializeFirstTrick() {
//        progressingTrick = new TrickPresident(getFirstLeader());
//    }

    void initializeTrick(byte _leader) {
        tricks.add(progressingTrick);
        progressingTrick = new TrickPresident(_leader);
    }

    public byte getFirstLeader() {
        byte leader_;
        if (ranks.isEmpty() || !rules.isLooserStartsFirst()) {
            leader_ = (byte) ((deal.getDealer() + 1) % getNombreDeJoueurs());
        } else {
            long min_ = ranks.getMaximum((byte) -1);
            int pl_ = ranks.indexOfNb(min_);
            leader_ = (byte) pl_;
        }
        return leader_;
    }

    public byte numberGivenCards(byte _player) {
        int nb_ = nombresCartesEchangesMax();
        int ind_ = getWinners(nb_, ranks).indexOfNb(_player);
        if (ind_ >= 0) {
            return (byte) (nb_ - ind_);
        }
        return IndexConstants.SIZE_EMPTY;
    }

    public byte nombresCartesEchangesMax() {
        if (getNombreDeJoueurs() <= 3) {
            return 1;
        }
        return 2;
    }

    Bytes getWinners() {
        int nb_ = nombresCartesEchangesMax();
        return getWinners(nb_, ranks);
    }

    static Bytes getWinners(int _nb, Bytes _ranks) {
        if (_ranks.isEmpty()) {
            return new Bytes();
        }
        ByteTreeMap<Byte> players_ = begin(_ranks);
        Bytes w_ = new Bytes();
        for (byte i = IndexConstants.FIRST_INDEX; i < _nb; i++) {
            w_.add(players_.getValue(i));
        }
        return w_;
    }

    Bytes getLoosers() {
        int nb_ = nombresCartesEchangesMax();
        return getLoosers(nb_, ranks);
    }

    static Bytes getLoosers(int _nb, Bytes _ranks) {
        if (_ranks.isEmpty()) {
            return new Bytes();
        }
        ByteTreeMap<Byte> players_ = begin(_ranks);
        Bytes l_ = new Bytes();
        int i_ = players_.size() - 1;
        while (l_.size() < _nb) {
            l_.add(players_.getValue(i_));
            i_--;
        }
        return l_;
    }

    private static ByteTreeMap<Byte> begin(Bytes _ranks) {
        ByteTreeMap<Byte> players_ = new ByteTreeMap<Byte>();
        int r_ = _ranks.size();
        for (byte i = IndexConstants.FIRST_INDEX; i < r_; i++) {
            players_.put(_ranks.get(i), i);
        }
        return players_;
    }

    public static Bytes getWinners(Bytes _ranks, int _nbMax) {
        return getWinners(_nbMax, _ranks);
    }

    public static Bytes getLoosers(Bytes _ranks, int _nbMax) {
        return getLoosers(_nbMax, _ranks);
    }

    public void donnerMeilleuresCartes() {
        if (switchedCards.isEmpty()) {
            return;
        }
        int nb_ = nombresCartesEchangesMax();
        Bytes loosers_ = getLoosers(nb_, ranks);
        for (byte l: loosers_) {
            donnerMeilleuresCartes(l, nb_ - loosers_.indexOfNb(l));
        }
        Bytes winners_ = getWinners(nb_, ranks);
        for (byte w: winners_) {
            recevoirCartes(w, getMatchingLoser(winners_,loosers_,w));
        }
    }

    public Bytes getLoosers(Bytes _humanPlayers) {
        Bytes l_ = new Bytes();
        for (byte w: getLoosers()) {
            if (_humanPlayers.containsObj(w)) {
                l_.add(w);
            }
        }
        return l_;
    }

    public Bytes getWinners(Bytes _humanPlayers) {
        Bytes l_ = new Bytes();
        for (byte w: getWinners()) {
            if (_humanPlayers.containsObj(w)) {
                l_.add(w);
            }
        }
        return l_;
    }

    public static byte getMatchingWinner(Bytes _winners, Bytes _losers, byte _loser) {
        int ind_ = _losers.indexOfNb(_loser);
        return _winners.get(ind_);
    }

    public static byte getMatchingLoser(Bytes _winners, Bytes _losers, byte _winner) {
        int ind_ = _winners.indexOfNb(_winner);
        return _losers.get(ind_);
    }

    public byte getMatchingWinner(byte _loser) {
        return getMatchingWinner(getWinners(),getLoosers(),_loser);
    }

    public byte getMatchingLoser(byte _winner) {
        return getMatchingLoser(getWinners(),getLoosers(),_winner);
    }

    //single mode
    public boolean readyToPlay() {
        return readyToPlayMulti(Bytes.newList(DealPresident.NUMERO_UTILISATEUR));
    }

    //Case empty human winners:
    public boolean readyToPlayMulti(Bytes _bytes) {
        if (switchedCards.isEmpty()) {
            return true;
        }
        for (byte w: getWinners(_bytes)) {
            if (switchedCards.get(w).estVide()) {
                return false;
            }
        }
        return true;
    }

    public void giveWorstCards() {
        giveWorstCards(new DefGamePresident());
    }
    public void giveWorstCards(IntGamePresident _ia) {
        if (switchedCards.isEmpty()) {
            return;
        }
        for (byte w: getWinners()) {
            HandPresident h_ = _ia.strategieEchange(this,w);
            switchedCards.get(w).ajouterCartes(h_);
        }
        receiveAndClear();
    }

    //Case not empty human winners:
    public void giveWorstCards(Bytes _humanPlayers) {
        giveWorstCards(new DefGamePresident(),_humanPlayers);
    }
    public void giveWorstCards(IntGamePresident _ia,Bytes _humanPlayers) {
        if (switchedCards.isEmpty()) {
            return;
        }
        for (byte w: getWinners()) {
            if (_humanPlayers.containsObj(w)) {
                continue;
            }
            HandPresident h_ = _ia.strategieEchange(this,w);
            switchedCards.get(w).ajouterCartes(h_);
        }
    }

    //multi mode
    public boolean giveWorstCards(Bytes _humanPlayers, byte _player, HandPresident _hand) {
        switchedCards.get(_player).ajouterCartes(_hand);
        boolean finished_ = true;
        byte nb_ = getNombreDeJoueurs();
        for (int i = 0; i < nb_; i++) {
            if (_humanPlayers.containsObj(i)) {
                HandPresident h_ = switchedCards.get(i);
                if (h_.estVide()) {
                    finished_ = false;
                    break;
                }
            }
        }
        if (finished_) {
            receiveAndClear();
        }
        return finished_;
    }

    //single mode
    public void giveWorstCards(HandPresident _hand) {
        switchedCards.get(DealPresident.NUMERO_UTILISATEUR).ajouterCartes(_hand);
        receiveAndClear();
    }

    public void donnerMeilleuresCartes(byte _joueur, int _nbCards) {
        HandPresident copie_=new HandPresident();
        copie_.ajouterCartes(getDeal().hand(_joueur));
        copie_.sortCardsBegin();
        for(byte i = 0; i< _nbCards; i++) {
            switchedCards.get(_joueur).ajouter(copie_.carte(i));
        }
    }

    public void recevoirCartes(byte _joueur, byte _from) {
        getDeal().hand(_joueur).ajouterCartes(switchedCards.get(_from));
    }

    public void receiveAndClear() {
        int nb_ = nombresCartesEchangesMax();
        Bytes loosers_ = getLoosers(nb_, ranks);
        Bytes winners_ = getWinners(nb_, ranks);
        for (byte l: loosers_) {
            recevoirCartes(l, getMatchingWinner(winners_,loosers_,l));
        }
        supprimerDons();
    }

    public void supprimerDons() {
        int nbPl_ = getNombreDeJoueurs();
        for (byte i = 0; i < nbPl_; i++) {
            HandPresident h_ = switchedCards.get(i);
            getDeal().hand(i).supprimerCartes(h_);
        }
    }

    void revertGifts() {
//        if (switchedCards.isEmpty()) {
//            return;
//        }
        int nb_ = nombresCartesEchangesMax();
        revert(nb_, ranks, switchedCards, getDeal());
        for (HandPresident h: switchedCards) {
            h.supprimerCartes();
        }
    }

    static boolean revert(int _nb, Bytes _ranks, CustList<HandPresident> _switchedCards, DealPresident _deal) {
        if (_switchedCards.isEmpty()) {
            return true;
        }
        Bytes winners_ = getWinners(_nb, _ranks);
        Bytes loosers_ = getLoosers(_nb, _ranks);
        boolean ready_ = ready(_switchedCards, winners_);
        if (ready_) {
            for (byte w: winners_) {
                _deal.hand(w).ajouterCartes(_switchedCards.get(w));
            }
            for (byte l: loosers_) {
                _deal.hand(l).ajouterCartes(_switchedCards.get(l));
            }
            for (byte l: loosers_) {
                byte pl_ = getMatchingWinner(winners_, loosers_,l);
                _deal.hand(l).supprimerCartes(_switchedCards.get(pl_));
            }
        }
        for (byte w: winners_) {
            byte pl_ = getMatchingLoser(winners_, loosers_,w);
            _deal.hand(w).supprimerCartes(_switchedCards.get(pl_));
        }
        return ready_;
    }

    static boolean ready(CustList<HandPresident> _switchedCards, Bytes _winners) {
        if (_switchedCards.isEmpty()) {
            return true;
        }
        boolean ready_ = true;
        for (byte w: _winners) {
            if (w == DealPresident.NUMERO_UTILISATEUR && _switchedCards.get(w).estVide()) {
                ready_ = false;
                break;
            }
        }
        return ready_;
    }

    /**Retourne la main a donner au dernier joueur ayant fini la derniere partie si ce joueur est le president
    la main a donner a l'avant dernier joueur ayant fini la derniere partie si ce joueur est le vice-president*/
    public HandPresident strategieEchange(byte _joueur) {
        HandPresident h_ = new HandPresident();
        HandPresident hPlayer_= getDeal().hand(_joueur);
        byte gifts_ =(byte) (nombresCartesEchangesMax() - getWinners().indexOfNb(_joueur));
        //0 == h_.total() < gifts_
        CustList<HandPresident> rep_ = getCardsSortedByLengthSortedByStrength(hPlayer_);
        int index_ = IndexConstants.FIRST_INDEX;
        while (true) {
            HandPresident hLoc_ = rep_.get(index_);
            if (exitSwitch(gifts_, h_, hLoc_)) {
                break;
            }
            //hLoc_.total() + h_.total() < gifts_
            h_.ajouterCartes(hLoc_);
            //h_.total() < gifts_
            index_++;
        }
        if (h_.total() == gifts_) {
            return h_;
        }
        HandPresident sorted_ = new HandPresident(hPlayer_);
        sorted_.sortCards(false, false);
        return GamePresidentProg.subHand(gifts_, sorted_);
//        rep_ = hPlayer_.getCardsByStrength(false).values();
//        h_ = new HandPresident();
//        //0 == h_.total() < gifts_
//        index_ = IndexConstants.FIRST_INDEX;
//        while (true) {
//            HandPresident hLoc_ = rep_.get(index_);
//            int nbCards_ = gifts_ - h_.total();
//            if (hLoc_.total() >= nbCards_) {
////                for (int i = IndexConstants.FIRST_INDEX; i < nbCards_; i++) {
////                    h_.ajouter(hLoc_.carte(i));
////                }
//                h_.ajouterCartes(GamePresidentProg.subHand(nbCards_,hLoc_));
//                break;
//            }
//            //hLoc_.total() + h_.total() < gifts_
//            h_.ajouterCartes(hLoc_);
//            //h_.total() < gifts_
//            index_++;
//        }
//        return h_;
    }
    private boolean exitSwitch(byte _gifts, HandPresident _h, HandPresident _hLoc) {
        if (_hLoc.premiereCarte().getForce() > GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
            return true;
        }
        int nbCards_ = _gifts - _h.total();
        if (_hLoc.total() >= nbCards_) {
            _h.ajouterCartes(GamePresidentProg.subHand(nbCards_, _hLoc));
//            GamePresidentProg.subHand(nbCards_,hLoc_);
//            for (int i = IndexConstants.FIRST_INDEX; i < nbCards_; i++) {
//                _h.ajouter(hLoc_.carte(i));
//            }
            return true;
        }
        return false;
    }

    public boolean currentPlayerHasPlayed(byte _player) {
        return currentPlayerHasPlayed(new DefGamePresident(),_player);
    }
    public boolean currentPlayerHasPlayed(IntGamePresident _ia,byte _player) {
        if (aJoue(_player)) {
            return true;
        }
        addCardsToCurrentTrick(_ia);
        return false;
    }
    public boolean aJoue(byte _player){
        return nextPlayer() != _player;
    }

    public HandPresident addCardsToCurrentTrick() {
        return addCardsToCurrentTrick(new DefGamePresident());
    }
    public HandPresident addCardsToCurrentTrick(IntGamePresident _ia) {
        HandPresident h_ = _ia.playedCards(this);
        addCardsToCurrentTrickAndLoop(h_);
        return h_;
    }

    public HandPresident addCardsToCurrentTrick(CardPresident _card, byte _nb) {
        return addCardsToCurrentTrick(new DefGamePresident(), _card,_nb);
    }
    public HandPresident addCardsToCurrentTrick(IntGamePresident _ia, CardPresident _card, byte _nb) {
        byte player_ = nextPlayer();
        HandPresident h_ = _ia.playedCardsUser(playHand(player_, _card, _nb));
        addCardsToCurrentTrickAndLoop(h_);
        return h_;
    }

    public void noPlay() {
        noPlay(new DefGamePresident());
    }
    public HandPresident noPlay(IntGamePresident _ia) {
        HandPresident h_ = _ia.playedCardsUser(new HandPresident());
        addCardsToCurrentTrickAndLoop(h_);
        return h_;
    }

    public byte addCardsToCurrentTrickAndLoop(HandPresident _hand) {
        byte pl_ = setupStatus(_hand);
        addCardsToCurrentTrick(_hand);
        lookupNextPlayer();
        return pl_;
    }

    private void lookupNextPlayer() {
        if (progressingTrick.estVide()) {
            emptyTrick();
            return;
        }
        while (true) {
            byte pl_ = nextPlayer();
            if (foundNextPlayer(pl_)) {
                break;
            }
            play(new HandPresident());
        }
    }

    boolean foundNextPlayer(byte _player) {
        if (!passOrFinish(_player)) {
            Playing playing_ = retStatus(progressingTrick, rules, lastStatus, getDeal(), _player);
            if (playing_ != Playing.SKIPPED) {
                if (playing_ == Playing.HAS_TO_EQUAL) {
                    lastStatus.put(_player, Playing.HAS_TO_EQUAL);
                } else {
                    //if (playing_ == Playing.DO_NOT_EQUAL)
                    lastStatus.put(_player, Playing.CAN_PLAY);
                }
                return true;
            }
            lastStatus.put(_player, Playing.SKIPPED);
        }
        return false;
    }

    boolean passOrFinish(byte _pl) {
        return passOrFinish(lastStatus, _pl);
    }

    private static boolean passOrFinish(ByteMap<Playing> _status,byte _pl) {
        Playing st_ = _status.getVal(_pl);
        return st_ == Playing.FINISH || st_ == Playing.PASS;
    }

    private void emptyTrick() {
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (byte p = IndexConstants.FIRST_INDEX; p < nombreJoueurs_; p++) {
//            passOrFinish.set(p, ComparatorBoolean.of(getDeal().hand(p).estVide()));
            if (getDeal().hand(p).estVide()) {
                lastStatus.put(p, Playing.FINISH);
            } else {
                lastStatus.put(p, Playing.CAN_PLAY);
            }
        }
    }

    private byte setupStatus(HandPresident _hand) {
        byte player_ = nextPlayer();
//        lastStatus.clear();
        Playing playingStatus_ = retStatus(progressingTrick, rules, lastStatus, getDeal(), player_);
        status(_hand, player_, playingStatus_, lastStatus);
        if (getDeal().hand(player_).total() == _hand.total()) {
            lastStatus.put(player_, Playing.FINISH);
//            passOrFinish.set(player_, BoolVal.TRUE);
        }
        return player_;
    }

    private static void status(HandPresident _hand, byte _player, Playing _playing, ByteMap<Playing> _status) {
        if (_hand.estVide() && _playing == Playing.CAN_PLAY) {
            _status.put(_player, Playing.PASS);
            return;
//            passOrFinish.set(player_, BoolVal.TRUE);
        }
        if (_hand.estVide()) {
//        if (_hand.estVide() && _playing == Playing.HAS_TO_EQUAL) {
            _status.put(_player, Playing.DO_NOT_EQUAL);
            return;
        }
//        if (!_hand.estVide()) {
//            _status.put(_player, Playing.CAN_PLAY);
//        }
        _status.put(_player, Playing.CAN_PLAY);
    }

    public void addCardsToCurrentTrick(HandPresident _hand) {
        if (_hand.estVide()) {
            playAndCheck(_hand);
            finishGame();
            return;
        }
        boolean rev_ = isReversed();
        byte str_ = _hand.premiereCarte().strength(rev_);
        if (str_ == GameStrengthCardPresidentComparator.CARD_MAX_STRENGTH) {
            finishDirectlyTrick(_hand);
            finishGame();
            return;
        }
        CustList<HandPresident> previousHands_ = progressingTrick.getFilledHandsBefore(progressingTrick.total());
        HandPresident bestCards_ = new HandPresident();
        for (HandPresident p: previousHands_) {
            bestCards_.ajouterCartes(p.getCardsByStrength(str_, rev_));
        }
        bestCards_.ajouterCartes(_hand);
        if (bestCards_.total() == rules.getNbStacks() * GamePresidentCommon.NB_SUITS && equalling(rules.getEqualty())) {
            finishDirectlyTrick(_hand);
            finishGame();
            return;
        }
        playAndCheck(_hand);
        finishGame();
    }

    private void playAndCheck(HandPresident _hand) {
        play(_hand);
        if (!keepPlayingCurrentTrick()) {
            addEmptyTrick();
        }
    }

    public static boolean equalling(EqualtyPlaying _eq) {
        return _eq == EqualtyPlaying.SKIP_DIFF_NEXT_STOP || _eq == EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL;
    }

    public void play(HandPresident _hand) {
        byte player_ = nextPlayer();
        play(_hand, player_);
    }

    void play(HandPresident _hand, byte _player) {
        progressingTrick.ajouter(_hand);
        getDeal().hand(_player).supprimerCartes(_hand);
    }

    public byte nextPlayer() {
        int count_ = progressingTrick.total();
        return progressingTrick.getPlayer(count_, getNombreDeJoueurs());
    }

    private boolean finishGame() {
        CustList<HandPresident> players_ = keepPlayingCurrentGameList();
        if (players_.size() != IndexConstants.ONE_ELEMENT) {
            return false;
        }
        addEmptyTrick();
        byte n_ = nextPlayer();
        play(new HandPresident(players_.first()));
//        progressingTrick.ajouter(new HandPresident(getDeal().hand(p_)), p_);
        tricks.add(progressingTrick);
//        getDeal().hand(p_).supprimerCartes();
        progressingTrick = new TrickPresident(n_);
        return true;
    }

    private void finishDirectlyTrick(HandPresident _hand) {
        play(_hand);
        emptyTrick();
//        for (byte p = IndexConstants.FIRST_INDEX; p < nb_; p++) {
////            passOrFinish.set(p,ComparatorBoolean.of(getDeal().hand(p).estVide()));
//            if (getDeal().hand(p).estVide()) {
//                lastStatus.put(p,Playing.FINISH);
//            } else {
//                lastStatus.put(p,Playing.CAN_PLAY);
//            }
//        }
        addEmptyTrick();
    }

    private void addEmptyTrick() {
        byte leader_ = progressingTrick.getRamasseur(getNombreDeJoueurs());
        if (!progressingTrick.estVide()) {
            tricks.add(progressingTrick);
        }
        if (getDeal().hand(leader_).estVide()) {
            progressingTrick = new TrickPresident(leader_);
            byte pl_ = leader_;
            for (byte p: rules.getSortedPlayersAfterEq(leader_)) {
                if (getDeal().hand(p).estVide()) {
//                    progressingTrick.ajouter(new HandPresident(), p);
                    play(new HandPresident());
                } else {
                    pl_ = p;
                    break;
                }
            }
            tricks.add(progressingTrick);
            progressingTrick = new TrickPresident(pl_);
        } else {
            progressingTrick = new TrickPresident(leader_);
        }
    }

    private CustList<HandPresident> keepPlayingCurrentGameList() {
        byte nbPlayers_ = getNombreDeJoueurs();
        CustList<HandPresident> l_ = new CustList<HandPresident>();
        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
            if (!getDeal().hand(p).estVide()) {
                l_.add(getDeal().hand(p));
            }
        }
        return l_;
    }

    public boolean keepPlayingCurrentGame() {
        int s_ = keepPlayingCurrentGameList().size();
        if (s_ == 0) {
            return false;
        }
//        boolean k_ = s_ > IndexConstants.ONE_ELEMENT;
//        if (!k_) {
//            finishGame();
//        }
//        return k_;
        return !finishGame();
    }

    boolean keepPlayingCurrentTrick() {
        byte nb_ = getNombreDeJoueurs();
        byte nbPass_ = 0;
        for (byte p = IndexConstants.FIRST_INDEX; p < nb_; p++) {
            if (passOrFinish(p)) {
                nbPass_++;
            }
        }
        if (nbPass_ + 1 == nb_) {
            byte winner_ = progressingTrick.getRamasseur(nb_);
            return passOrFinish(winner_);
        }
        return nbPass_ < nb_;
    }

    public boolean canPass() {
        HandPresident playable_ = cartesJouables();
        HandPresident full_ = deal.hand(nextPlayer());
        return GamePresidentProg.canPass(playable_, rules, progressingTrick, full_, isReversed());
    }


    boolean allowPlaying(HandPresident _card) {
        HandPresident playable_ = cartesJouables();
        boolean rev_ = isReversed();
        return !playable_.getCardsByStrength(_card.premiereCarte().strength(rev_), rev_).estVide();
    }

    public boolean allowPlaying(CardPresident _card) {
        HandPresident playable_ = cartesJouables();
        boolean rev_ = isReversed();
        return !playable_.getCardsByStrength(_card.strength(rev_), rev_).estVide();
    }

    private HandPresident playHand(byte _player, CardPresident _card, byte _nb) {
        HandPresident main_ = getDeal().hand(_player);
        return GamePresidentCommon.playHand(_card, _nb, main_, isReversed(), progressingTrick);
    }

    public HandPresident cartesJouables() {
        return cartesJouables(getDeal().hand(nextPlayer()),nextPlayer());
    }

    HandPresident cartesJouables(HandPresident _hand, byte _player) {
        if (progressingTrick.estVide()) {
            return new HandPresident(_hand);
        }
        Playing playing_ = retStatus(progressingTrick, rules, lastStatus, getDeal(), _player);
        return GamePresidentCommon.getPlayable(_hand, playing_, progressingTrick, isReversed(), rules);
    }


    public Playing getStatus() {
        byte pl_ = nextPlayer();
        return retStatus(progressingTrick, rules, lastStatus, getDeal(), pl_);
    }

    public static Playing retStatus(TrickPresident _prog, RulesPresident _rules, ByteMap<Playing> _status, DealPresident _deal, byte _next) {
        if (_deal.hand(_next).estVide()) {
            return Playing.FINISH;
        }
        if (_status.getVal(_next) == Playing.PASS) {
            return Playing.PASS;
        }
        if (_rules.getEqualty() == EqualtyPlaying.NO_SKIP) {
            return Playing.CAN_PLAY;
        }
        return procSkipping(_prog, _rules, _status);
    }

    private static Playing procSkipping(TrickPresident _prog, RulesPresident _rules, ByteMap<Playing> _status) {
        int count_ = _prog.total();
        int nbPlayers_ = _rules.getNbPlayers();
        CustList<HandPresident> hands_ = new CustList<HandPresident>();
        Ints indexes_ = new Ints();
        int index_;
        if (count_ >= nbPlayers_) {
            index_ = count_ - nbPlayers_;
            for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
                hands_.add(_prog.carte(index_));
                indexes_.add(index_);
                index_++;
            }
        } else {
            index_ = IndexConstants.FIRST_INDEX;
            for (HandPresident h: _prog) {
                hands_.add(h);
                indexes_.add(index_);
                index_++;
            }
        }
        CustList<HandPresident> filledHands_ = new CustList<HandPresident>();
        Ints filledHandsIndexes_ = new Ints();
        index_ = IndexConstants.FIRST_INDEX;
        for (HandPresident h: hands_) {
            if (h.estVide()) {
                index_++;
                continue;
            }
            filledHands_.add(h);
            filledHandsIndexes_.add(indexes_.get(index_));
            index_++;
        }
        if (filledHands_.size() <= IndexConstants.ONE_ELEMENT) {
            return Playing.CAN_PLAY;
        }
        HandPresident prev_ = filledHands_.last();
        HandPresident befPrev_ = filledHands_.get(filledHands_.size() - 2);
        boolean apply_ = applySkippedPlayer(count_, filledHandsIndexes_.last(), _rules, _prog, _status);
        if (apply_ && prev_.premiereCarte().getForce() == befPrev_.premiereCarte().getForce()) {
            if (equalling(_rules.getEqualty())) {
                return Playing.HAS_TO_EQUAL;
            }
            return Playing.SKIPPED;
        }
        return Playing.CAN_PLAY;
    }

    private static boolean applySkippedPlayer(int _count, int _indexCardInTrick, RulesPresident _rules, TrickPresident _tr, ByteMap<Playing> _status) {
        if (_rules.getEqualty() == EqualtyPlaying.SKIP_DIFF_NEXT_STOP_ALL) {
            return true;
        }
        boolean apply_ = true;
        for (int i = _indexCardInTrick + 1; i < _count; i++) {
            byte curPlayer_ = _tr.getPlayer(i, (byte) _rules.getNbPlayers());
            if (!passOrFinish(_status,curPlayer_)) {
                //curPlayer_ was skipped
                apply_ = false;
                break;
            }
        }
        return apply_;
    }

//    private CustList<BoolVal> passOrFinish() {
//        CustList<BoolVal> l_ = new CustList<BoolVal>();
//        byte nbPlayer_ = getNombreDeJoueurs();
//        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayer_; p++) {
//            l_.add(ComparatorBoolean.of(getDeal().hand(p).estVide()));
//        }
//        int nbHands_ = progressingTrick.total();
//        boolean skipped_ = false;
//        for (int i = IndexConstants.FIRST_INDEX; i < nbHands_; i++) {
//            byte player_ = progressingTrick.getPlayer(i, nbPlayer_);
//            if (l_.get(player_) == BoolVal.TRUE || i == IndexConstants.FIRST_INDEX) {
//                continue;
//            }
//            if (!progressingTrick.carte(i).estVide()) {
//                boolean rev_ = isReversed();
//                byte str_ = progressingTrick.carte(i).premiereCarte().strength(rev_);
//                int j_ = prevPlayHand(i);
//                if (j_ >= 0 && progressingTrick.carte(j_).premiereCarte().strength(rev_) == str_) {
//                    skipped_ = true;
//                }
//            } else if (skipped_) {
//                skipped_ = false;
//            } else {
//                l_.set(player_, BoolVal.TRUE);
//            }
//        }
//        return l_;
//    }

//    private int prevPlayHand(int _i) {
//        int j_ = _i - 1;
//        while (j_ >= 0) {
//            if (!progressingTrick.carte(j_).estVide()) {
//                break;
//            }
//            j_--;
//        }
//        return j_;
//    }

    public HandPresident playedCards() {
        if (progressingTrick.estVide()) {
            return beginTrick();
        }
        return progressTrick();
    }

    private HandPresident beginTrick() {
        HandPresident playable_ = cartesJouables();
        GamePresidentBegin g_ = new GamePresidentBegin(progressingTrick,tricks, isReversed(),rules,playable_);
        return g_.beginTrick();
    }

    private HandPresident progressTrick() {
        byte player_ = nextPlayer();
        HandPresident fullHand_ = deal.hand(player_);
        HandPresident playable_ = cartesJouables();
        GamePresidentProg g_ = new GamePresidentProg(progressingTrick,tricks, isReversed(),rules,playable_,fullHand_);
        return g_.progressTrick();
    }

    public AbsBasicTreeMap<CardPresident,Byte> getPlayedCardsByStrength() {
        int nbMaxLen_ = rules.getNbStacks() * GamePresidentCommon.NB_SUITS;
        return GamePresidentCommon.getNotFullPlayedCardsByStrength(isReversed(), tricks, progressingTrick,nbMaxLen_);
    }

    private CustList<HandPresident> getCardsSortedByLengthSortedByStrength(HandPresident _hand) {
        CustList<HandPresident> l_ = new CustList<HandPresident>();
        int nbMaxLen_ = rules.getNbStacks() * GamePresidentCommon.NB_SUITS;
        for (int i = IndexConstants.SECOND_INDEX; i <= nbMaxLen_; i++) {
            l_.addAllElts(_hand.getCardsByLengthSortedByStrength(false, i));
        }
        return l_;
    }

    public HandPresident mainUtilisateurTriee(DisplayingPresident _regles) {
        HandPresident main_ = new HandPresident();
        main_.ajouterCartes(getDeal().hand());
        main_.sortCards(_regles.getDisplaying(), isReversed());
        return main_;
    }

    public HandPresident mainUtilisateurTriee(HandPresident _hand,DisplayingPresident _regles) {
        HandPresident main_ = new HandPresident();
        main_.ajouterCartes(_hand);
        main_.sortCards(_regles.getDisplaying(), isReversed());
        return main_;
    }

    public void restituerMainsDepartRejouerDonne() {
        byte nombreDeJoueurs_ = getNombreDeJoueurs();
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < nombreDeJoueurs_; joueur_++) {
            getDeal().hand(joueur_).supprimerCartes();
        }
        for (TrickPresident pli_ : tricks) {
            int index_ = IndexConstants.FIRST_INDEX;
            for (HandPresident carte_ : pli_) {
                getDeal().hand(pli_.getPlayer(index_, nombreDeJoueurs_)).ajouterCartes(carte_);
                index_++;
            }
        }
        revertGifts();
        initPartie();
    }

    public boolean isReversed() {
        CustList<TrickPresident> tricks_ = new CustList<TrickPresident>(tricks);
        tricks_.add(progressingTrick);
//        boolean reversedRet_;
//        if (rules.isPossibleReversing()) {
//            boolean reversed_ = false;
//            for (TrickPresident t: tricks) {
//                if (t.getNombreDeCartesParJoueur() == GamePresidentCommon.NB_SUITS * rules.getNbStacks()) {
//                    reversed_ = !reversed_;
//                }
//            }
//            reversedRet_ = reversed_;
//        } else {
//            reversedRet_ = false;
//        }
        return allFullHandsMerge(tricks_,new TrickPresident(),0,rules) % 2 == 1;
//        return isReversed(tricks_, rules);
    }

//    public static boolean isReversed(CustList<TrickPresident> _tricks, RulesPresident _rules) {
//        boolean reversed_ = false;
//        if (_rules.isPossibleReversing()) {
//            int nbFullHands_ = IndexConstants.SIZE_EMPTY;
//            for (TrickPresident t: _tricks) {
//                nbFullHands_ += allFullHands(t,t.total(),_rules);
////                for (HandPresident h: t) {
////                    if (h.total() == GamePresidentCommon.NB_SUITS * _rules.getNbStacks()) {
////                        nbFullHands_++;
////                    }
////                }
//            }
//            reversed_ = nbFullHands_ % 2 == 1;
//        }
//        return reversed_;
//    }
    private static int allFullHandsMerge(CustList<TrickPresident> _previous,TrickPresident _trick, int _until, RulesPresident _rules) {
        if (!_rules.isPossibleReversing()) {
            return IndexConstants.SIZE_EMPTY;
        }
        int nbFullHands_ = IndexConstants.SIZE_EMPTY;
        for (TrickPresident t: _previous) {
            nbFullHands_ += allFullHands(t,t.total(),_rules);
        }
        nbFullHands_ += allFullHands(_trick,_until,_rules);

        return nbFullHands_;
    }
    private static int allFullHands(TrickPresident _trick, int _until, RulesPresident _rules) {
        int o_ = 0;
        for (HandPresident h: _trick.getCards().left(_until)) {
            if (h.total() == GamePresidentCommon.NB_SUITS * _rules.getNbStacks()) {
                o_++;
            }
        }
        return o_;
    }
    private boolean allowSwitchCards() {
        return rules.isSwitchCards();
    }

    /** Renvoie le nombre de joueurs jouant a la partie */
    public byte getNombreDeJoueurs() {
        return (byte) rules.getNbPlayers();
    }

    public Bytes getNewRanks() {
        Bytes r_ = new Bytes();
        Ints t_ = new Ints();
        Ints c_ = new Ints();
        byte nbPlayers_ = getNombreDeJoueurs();
        DealPresident deal_ = new DealPresident(deal);
        for (TrickPresident t: tricks) {
            int index_ = IndexConstants.FIRST_INDEX;
            for (HandPresident c: t) {
                byte player_ = t.getPlayer(index_, nbPlayers_);
                deal_.hand(player_).ajouterCartes(c);
                index_++;
            }
        }
        for (byte p = IndexConstants.FIRST_INDEX; p <nbPlayers_; p++) {
            t_.add((int) IndexConstants.INDEX_NOT_FOUND_ELT);
            c_.add((int) IndexConstants.INDEX_NOT_FOUND_ELT);
            r_.add(IndexConstants.FIRST_INDEX);
        }
        tricksCards(t_, c_, nbPlayers_);
        Bytes normalEnds_ = new Bytes();
        Bytes eStrongestCards_ = new Bytes();
        if (rules.isLoosingIfFinishByBestCards()) {
            strongestCards(t_, c_, nbPlayers_, deal_, eStrongestCards_);
        }
        for (byte p = IndexConstants.FIRST_INDEX; p <nbPlayers_; p++) {
            if (eStrongestCards_.containsObj(p)) {
                continue;
            }
            normalEnds_.add(p);
        }
        Bytes tricksCardsPlayers_;
        tricksCardsPlayers_ = new Bytes();
        tricksCardsPlayers_.addAllElts(getTricksCardsPlayers(normalEnds_, t_, c_));
        tricksCardsPlayers_.addAllElts(getTricksCardsPlayers(eStrongestCards_, t_, c_));
        byte curRank_ = IndexConstants.SECOND_INDEX;
        for (byte p: tricksCardsPlayers_) {
            r_.set(p, curRank_);
            curRank_++;
        }
        return r_;
    }

    private void tricksCards(Ints _t, Ints _c, byte _nbPlayers) {
        for (byte p = IndexConstants.FIRST_INDEX; p < _nbPlayers; p++) {
            int tInd_ = tricks.size() - 1;
            while (true) {
                TrickPresident curTrick_ = tricks.get(tInd_);
                Ints cInd_ = curTrick_.getFilledHandsIndexesBefore(curTrick_.total());
                Ints cIndPl_ = new Ints();
                for (int i: cInd_) {
                    byte curPlayer_ = curTrick_.getPlayer(i, _nbPlayers);
                    if (curPlayer_ == p) {
                        cIndPl_.add(i);
                    }
                }
                if (!cIndPl_.isEmpty()) {
                    _t.set(p, tInd_);
                    _c.set(p, cIndPl_.last());
                    break;
                }
                tInd_--;
            }
        }
    }

    private void strongestCards(Ints _t, Ints _c, byte _nbPlayers, DealPresident _deal, Bytes _eStrongestCards) {
        for (byte p = IndexConstants.FIRST_INDEX; p < _nbPlayers; p++) {
            strongestCards(_deal, _eStrongestCards, p, _t.get(p), _c.get(p));
        }
    }

    private void strongestCards(DealPresident _deal, Bytes _eStrongestCards, byte _p, int _t, int _c) {
        CustList<TrickPresident> tricks_ = tricks.left(_t);
//        CustList<TrickPresident> tricks_ = tricks.left(_t.get(_p) + 1);
        TrickPresident curTrick_ = tricks.get(_t);
        boolean reversed_ = allFullHandsMerge(tricks_,curTrick_, _c,rules) % 2 == 1;
//        boolean reversed_ = allFullHandsMerge(tricks_,new TrickPresident(),0,rules) % 2 == 1;
//        boolean reversed_ = isReversed(tricks_, rules);
        HandPresident curHand_ = curTrick_.carte(_c);
        if (curHand_.premiereCarte().strength(reversed_) != GameStrengthCardPresidentComparator.CARD_MAX_STRENGTH) {
            return;
        }
        boolean existMin_ = false;
        for (CardPresident c: _deal.hand(_p)) {
            if (c.strength(reversed_) < GameStrengthCardPresidentComparator.CARD_MAX_STRENGTH) {
                existMin_ = true;
                break;
            }
        }
        if (existMin_) {
            _eStrongestCards.add(_p);
        }
    }

    private static Bytes getTricksCardsPlayers(Bytes _players, Ints _t, Ints _c) {
        IntTreeMap<Bytes> tricksPlayers_ = new IntTreeMap<Bytes>();
        for (byte p : _players) {
            int noTrick_ = _t.get(p);
            if (tricksPlayers_.contains(noTrick_)) {
                tricksPlayers_.getVal(noTrick_).add(p);
            } else {
                tricksPlayers_.put(noTrick_, Bytes.newList(p));
            }
        }
        Bytes tricksCardsPlayers_ = new Bytes();
        for (EntryCust<Integer, Bytes> k: tricksPlayers_.entryList()) {
            Bytes players_ = k.getValue();
            IntTreeMap<Byte> ordPlayers_ = new IntTreeMap<Byte>();
            for (byte p: players_) {
                ordPlayers_.put(_c.get(p), p);
            }
            tricksCardsPlayers_.addAllElts(ordPlayers_.values());
        }
        return tricksCardsPlayers_;
    }

    public HandPresident empiler() {
        HandPresident h_ = new HandPresident();
        for (TrickPresident t: tricks) {
            for (HandPresident h: t) {
                h_.ajouterCartes(h);
            }
        }
        return h_;
    }

    /**Inclut tous les plis sauf celui qui est en cours*/
    public CustList<TrickPresident> unionPlis() {
        return tricks;
    }

    public TrickPresident getProgressingTrick() {
        return progressingTrick;
    }

//    public CustList<BoolVal> getPassOrFinish() {
//        return passOrFinish;
//    }

    public GameType getType() {
        return type;
    }

    public Bytes getRanks() {
        return ranks;
    }

    public CustList<HandPresident> getSwitchedCards() {
        return switchedCards;
    }

    HandPresident getCards() {
        HandPresident nb_ = new HandPresident();
        CustList<TrickPresident> union_ = new CustList<TrickPresident>();
        union_.addAllElts(tricks);
        union_.add(progressingTrick);
        for (TrickPresident t: union_) {
            for (HandPresident h: t) {
                nb_.ajouterCartes(h);
            }
        }
        for (HandPresident h: deal) {
            nb_.ajouterCartes(h);
        }
        nb_.sortCardsBegin();
        return nb_;
    }

    void copySwitchCards(CustList<HandPresident> _switchedCards) {
        switchedCards = new CustList<HandPresident>();
        for (HandPresident k: _switchedCards) {
            switchedCards.add(new HandPresident(k));
        }
    }

    public ByteMap<Playing> getLastStatus() {
        return lastStatus;
    }

    private CustList<TrickPresidentIndexesCheck> setLastStatus() {
//        lastStatus.clear();
        TrickPresident cp_ = new TrickPresident(progressingTrick.getEntameur());
        int count_ = progressingTrick.total();
        for (int i = 0; i< count_; i++) {
            cp_.ajouter(progressingTrick.carte(i));
        }
        progressingTrick.getCards().clear();
        int next_ = 0;
        CustList<TrickPresidentIndexesCheck> ls_ = new CustList<TrickPresidentIndexesCheck>();
        for (int i = 0; i< count_; i++) {
            if (next_ > i) {
                ls_.add(new TrickPresidentIndexesCheck(cp_,generate(i,i+1)));
                continue;
            }
            addCardsToCurrentTrickAndLoop(cp_.carte(i));
            if (progressingTrick.estVide()) {
                ls_.add(new TrickPresidentIndexesCheck(cp_,generate(i+1,count_)));
                return ls_;
            }
            next_ = progressingTrick.total();
        }
        return ls_;
//        lookupNextPlayer();
//        if (!progressingTrick.estVide()) {
//            foundNextPlayer(nextPlayer());
//        }
//        if (deal.hand(nextPlayer()).estVide()) {
//            return;
//        }
//        lastStatus.put(nextPlayer(),retStatus(cp_,rules,lastStatus, nextPlayer()));
//        byte nbPlayers_ = getNombreDeJoueurs();
//        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
//            setLastStatus(nbPlayers_, p);
//        }
    }
    private static Ints generate(int _from, int _to) {
        Ints indexes_ = new Ints();
        for (int j = _from; j< _to; j++) {
            indexes_.add(j);
        }
        return indexes_;
    }

//    private void setLastStatus(byte _nbPlayers, byte _p) {
//        if (deal.hand(_p).estVide()) {
//            lastStatus.put(_p, Playing.FINISH);
//            return;
//        }
//        if (passOrFinish(_p)) {
//            lastStatus.put(_p, Playing.PASS);
//            return;
//        }
//        if (rules.getEqualty() == EqualtyPlaying.NO_SKIP) {
//            lastStatus.put(_p, Playing.CAN_PLAY);
//            return;
//        }
//        Ints indexes_ = progressingTrick.getPlayedCardsIndexes(_p, _nbPlayers);
//        if (progressingTrick.getPlayer(progressingTrick.total(), _nbPlayers) != _p) {
//            if (!indexes_.isEmpty()) {
//                HandPresident lastHand_ = progressingTrick.carte(indexes_.last());
//                if (!lastHand_.estVide()) {
//                    lastStatus.put(_p, Playing.CAN_PLAY);
//                    return;
//                }
//                if (rules.getEqualty() == EqualtyPlaying.SKIP_ALWAYS_NEXT) {
//                    lastStatus.put(_p, Playing.SKIPPED);
//                    return;
//                }
//                lastStatus.put(_p, Playing.DO_NOT_EQUAL);
//                return;
//            }
//            lastStatus.put(_p, Playing.CAN_PLAY);
//            return;
//        }
//        lastStatus.put(_p, getStatus());
//    }

//    private void setLastStatus(byte _p, TrickPresident _re, HandPresident _hand) {
////        if (deal.hand(_p).estVide()) {
////            lastStatus.put(_p, Playing.FINISH);
////            return;
////        }
////        if (!_hand.estVide()) {
////            lastStatus.put(_p, Playing.CAN_PLAY);
////            return;
////        }
//        Playing playingStatus_ = retStatus(_re,rules,lastStatus, deal, _p);
//        if (status(_hand, _p, playingStatus_, lastStatus)) {
//            return;
//        }
//
////        if (playingStatus_ == Playing.CAN_PLAY) {
////            lastStatus.put(_p, Playing.PASS);
//////            passOrFinish.set(_p, BoolVal.TRUE);
////            return;
////        }
////        if (playingStatus_ == Playing.HAS_TO_EQUAL) {
////            lastStatus.put(_p, Playing.DO_NOT_EQUAL);
////            return;
////        }
////        if (getDeal().hand(_p).total() == _hand.total()) {
////            lastStatus.put(_p, Playing.FINISH);
////            passOrFinish.set(_p, BoolVal.TRUE);
////        }
////        if (passOrFinish(_p)) {
////            lastStatus.put(_p, Playing.PASS);
////            return;
////        }
////        if (rules.getEqualty() == EqualtyPlaying.NO_SKIP) {
////            lastStatus.put(_p, Playing.CAN_PLAY);
////            return;
////        }
////        Ints indexes_ = _re.getPlayedCardsIndexes(_p, _nbPlayers);
////        if (_re.getPlayer(_re.total(), _nbPlayers) != _p) {
////            if (!indexes_.isEmpty()) {
////                HandPresident lastHand_ = _re.carte(indexes_.last());
////                if (!lastHand_.estVide()) {
////                    lastStatus.put(_p, Playing.CAN_PLAY);
////                    return;
////                }
////                if (rules.getEqualty() == EqualtyPlaying.SKIP_ALWAYS_NEXT) {
////                    lastStatus.put(_p, Playing.SKIPPED);
////                    return;
////                }
////                lastStatus.put(_p, Playing.DO_NOT_EQUAL);
////                return;
////            }
////            lastStatus.put(_p, Playing.CAN_PLAY);
////            return;
////        }
////        if (rules.getEqualty() == EqualtyPlaying.NO_SKIP) {
////            return;
////        }
//        lastStatus.put(_p, playingStatus_);
//    }

    public void setNombre() {
        number++;
    }

    public CustList<TrickPresident> getTricks() {
        return tricks;
    }

    public DealPresident getDeal() {
        return deal;
    }

    public void setDeal(DealPresident _deal) {
        deal = _deal;
    }

    public Shorts getScores() {
        return scores;
    }

    public void setScores(Shorts _scores) {
        scores = _scores;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long _number) {
        number = _number;
    }

    public RulesPresident getRules() {
        return rules;
    }

    public void setRules(RulesPresident _rules) {
        rules = _rules;
    }

    public void setType(GameType _type) {
        type = _type;
    }

    public void setProgressingTrick(TrickPresident _progressingTrick) {
        progressingTrick = _progressingTrick;
    }

    public void setTricks(CustList<TrickPresident> _tricks) {
        tricks = _tricks;
    }

    public void setRanks(Bytes _ranks) {
        ranks = _ranks;
    }

    public void setSwitchedCards(CustList< HandPresident> _switchedCards) {
        switchedCards = _switchedCards;
    }

    public String getError() {
        return error;
    }

    public void setError(String _error) {
        error = _error;
    }

    public boolean isEnded() {
        return ended;
    }
}
