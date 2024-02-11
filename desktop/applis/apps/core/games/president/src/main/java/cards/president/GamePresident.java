package cards.president;

import cards.consts.GameType;
import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import code.maths.montecarlo.AbstractGenerator;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;


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

    private CustList<BoolVal> passOrFinish = new CustList<BoolVal>();

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
            passOrFinish.add(BoolVal.FALSE);
        }
        setLastStatus();
        ranks = new Bytes(_ranks);
        byte leader_ = getFirstLeader();
        progressingTrick.setEntameur(leader_);
    }

    public void initPartie() {
        progressingTrick = new TrickPresident(getFirstLeader());
        lastStatus = new ByteMap<Playing>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i = IndexConstants.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.set(i, (short) 0);
            passOrFinish.set(i, BoolVal.FALSE);
        }
        setLastStatus();
    }

    void loadGame() {
        byte leader_ = getFirstLeader();
        for (TrickPresident t: tricks) {
            t.setEntameur(leader_);
            if (t.getNombreDeCartesParJoueur() > 0) {
                leader_ = t.getRamasseur(getNombreDeJoueurs());
            } else {
                leader_ = (byte) ((leader_ + t.total()) % getNombreDeJoueurs());
            }
        }
        progressingTrick.setEntameur(leader_);
        passOrFinish = passOrFinish();
        lastStatus = new ByteMap<Playing>();
        setLastStatus();
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

    public void simulate(int _nbTimes, SimulatingPresident _simu, AbstractGenerator _gene) {
        ended = false;
        _simu.prepare();
        _simu.sleepSimu(500);
        _simu.beginDemo();
//        _simu.pause();
        HandPresident userHand_ = new HandPresident();
        int noDeal_ = IndexConstants.SIZE_EMPTY;
        while (noDeal_ < _nbTimes) {
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
            befDeal(_simu, userHand_, noDeal_, firstUserHand_, secondUserHand_, switchedCards_, thirdUserHand_);
            byte leader_ = getFirstLeader();
            progressingTrick = new TrickPresident(leader_);
            passOrFinish = passOrFinish();
            while (true) {
                HandPresident h_ = _simu.getInt().playedCards(this);
                beforeCards(_simu);
                _simu.sleepSimu(100);
                if (_simu.stopped()) {
                    _simu.stopDemo();
                    return;
                }
//                _simu.pause();
                byte nextPlayerBk_ = setupStatus(h_);
                _simu.gearStatusChange(lastStatus, nextPlayerBk_);
                addCardsToCurrentTrick(h_);
                _simu.displayPlayedHand(h_);
                lookupNextPlayer();
                _simu.gearStatusChange(lastStatus, nextPlayer());
                _simu.displayPlayedHandMessage(h_,nextPlayerBk_);
                endCards(_simu, userHand_, h_, nextPlayerBk_);
                if (!keepPlayingCurrentGame()) {
                    _simu.endDeal();
                    _simu.sleepSimu(5000);
//                    _simu.pause();
                    break;
                }
            }
            Bytes ranks_ = getNewRanks();
//            HandPresident stackNext_ = empiler();
//            byte dealer_ = getDeal().getDealer();
            deal = _simu.getInt().empiler(noDeal_ + 1L,this,_gene);
//            deal = new DealPresident(noDeal_ + 1L, stackNext_);
//            deal.donneurSuivant(dealer_,rules);
//            deal.initDonne(rules,_gene);
            tricks = new CustList<TrickPresident>();
            ranks = new Bytes(ranks_);
            setLastStatus();
            _simu.prepare();
            noDeal_++;
        }
        ended = true;
    }

    private void endCards(SimulatingPresident _simu, HandPresident _userHand, HandPresident _h, byte _nextPlayerBk) {
        if (_nextPlayerBk == DealPresident.NUMERO_UTILISATEUR) {
            _userHand.supprimerCartes(_h);
            _simu.displayUserHand(_userHand);
        }
        if (progressingTrick.estVide()) {
            _simu.displayTrickLeader(_nextPlayerBk);
            _simu.sleepSimu(2000);
        }
    }

    private void beforeCards(SimulatingPresident _simu) {
        if (progressingTrick.estVide()) {
            _simu.setupDeck();
            _simu.gearStatusChange(lastStatus,progressingTrick.getEntameur());
        }
    }

    private void befDeal(SimulatingPresident _simu, HandPresident _userHand, int _noDeal, HandPresident _firstUserHand, HandPresident _secondUserHand, CustList<HandPresident> _switchedCards, HandPresident _thirdUserHand) {
        if (availableSwitchingCards()) {
            Bytes losers_ = GamePresident.getLoosers(ranks, nombresCartesEchangesMax());
            Bytes winners_ = GamePresident.getWinners(ranks, nombresCartesEchangesMax());
            HandPresident hUser_;
            _userHand.supprimerCartes();
            hUser_ = mainUtilisateurTriee(_firstUserHand, _simu.getDisplaying());
            _userHand.ajouterCartes(hUser_);
            _simu.displayUserHand(_userHand);
            for (byte l: losers_) {
                byte w_ = GamePresident.getMatchingWinner(winners_, losers_, l);
                HandPresident h_ = _switchedCards.get(l);
                _simu.displayLooserMessage(h_,l,w_);
            }
            _simu.displayLineReturn();
            _userHand.supprimerCartes();
            hUser_ = mainUtilisateurTriee(_secondUserHand, _simu.getDisplaying());
            _userHand.ajouterCartes(hUser_);
            _simu.displayUserHand(_userHand);
            for (byte w: winners_) {
                byte l_ = GamePresident.getMatchingLoser(winners_, losers_, w);
                HandPresident h_ = _switchedCards.get(w);
                _simu.displayWinnerMessage(h_,l_,w);
            }
            _simu.displayLineReturn();
            _userHand.supprimerCartes();
            hUser_ = mainUtilisateurTriee(_thirdUserHand, _simu.getDisplaying());
            _userHand.ajouterCartes(hUser_);
            _simu.displayUserHand(_userHand);
            _simu.displaySwitchedUserHand(winners_,losers_, _noDeal,switchedCards);
        } else {
            HandPresident h_ = mainUtilisateurTriee(_thirdUserHand, _simu.getDisplaying());
            _userHand.supprimerCartes();
            _userHand.ajouterCartes(h_);
            _simu.displayUserHand(_userHand);
        }
    }

    void initializeFirstTrick() {
        progressingTrick = new TrickPresident(getFirstLeader());
    }

    void initializeTrick(byte _leader) {
        progressingTrick = new TrickPresident(_leader);
    }

    private byte getFirstLeader() {
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
        if (getWinners().containsObj(_player)) {
            int ind_ = getWinners().indexOfNb(_player);
            return (byte) (nombresCartesEchangesMax() - ind_);
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
        ByteTreeMap<Byte> players_ = new ByteTreeMap<Byte>();
        int r_ = _ranks.size();
        for (byte i = IndexConstants.FIRST_INDEX; i < r_; i++) {
            players_.put(_ranks.get(i), i);
        }
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
        ByteTreeMap<Byte> players_ = new ByteTreeMap<Byte>();
        int r_ = _ranks.size();
        for (byte i = IndexConstants.FIRST_INDEX; i < r_; i++) {
            players_.put(_ranks.get(i), i);
        }
        Bytes l_ = new Bytes();
        int i_ = players_.size() - 1;
        while (l_.size() < _nb) {
            l_.add(players_.getValue(i_));
            i_--;
        }
        return l_;
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
        for (byte l: getLoosers()) {
            donnerMeilleuresCartes(l);
        }
        for (byte w: getWinners()) {
            recevoirMeilleuresCartes(w);
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
        int ind_ = getLoosers().indexOfNb(_loser);
        return getWinners().get(ind_);
    }

    public byte getMatchingLoser(byte _winner) {
        int ind_ = getWinners().indexOfNb(_winner);
        return getLoosers().get(ind_);
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

    public void donnerMeilleuresCartes(byte _joueur) {
        int ind_= getLoosers().indexOfNb(_joueur);
        int nbCards_ = nombresCartesEchangesMax() - ind_;
        HandPresident copie_=new HandPresident();
        copie_.ajouterCartes(getDeal().hand(_joueur));
        copie_.sortCardsBegin();
        for(byte i=0;i<nbCards_;i++) {
            switchedCards.get(_joueur).ajouter(copie_.carte(i));
        }
    }

    public void recevoirMeilleuresCartes(byte _joueur) {
        int ind_= getWinners().indexOfNb(_joueur);
        byte pl_ = getLoosers().get(ind_);
        getDeal().hand(_joueur).ajouterCartes(switchedCards.get(pl_));
    }

    void receiveAndClear() {
        for (byte l: getLoosers()) {
            recevoirPiresCartes(l);
        }
        supprimerDons();
    }

    public void recevoirPiresCartes(byte _joueur) {
        int ind_= getLoosers().indexOfNb(_joueur);
        byte pl_ = getWinners().get(ind_);
        getDeal().hand(_joueur).ajouterCartes(switchedCards.get(pl_));
    }

    public void supprimerDons() {
        int nbPl_ = getNombreDeJoueurs();
        for (byte i = 0; i < nbPl_; i++) {
            HandPresident h_ = switchedCards.get(i);
            getDeal().hand(i).supprimerCartes(h_);
        }
    }

    void revertGifts() {
        if (switchedCards.isEmpty()) {
            return;
        }
        for (byte w: getWinners()) {
            int ind_= getWinners().indexOfNb(w);
            byte pl_ = getLoosers().get(ind_);
            getDeal().hand(w).supprimerCartes(switchedCards.get(pl_));
            getDeal().hand(w).ajouterCartes(switchedCards.get(w));
        }
        for (byte l: getLoosers()) {
            int ind_= getLoosers().indexOfNb(l);
            byte pl_ = getWinners().get(ind_);
            getDeal().hand(l).supprimerCartes(switchedCards.get(pl_));
            getDeal().hand(l).ajouterCartes(switchedCards.get(l));
        }
        for (HandPresident h: switchedCards) {
            h.supprimerCartes();
        }
    }

    /**Retourne la main a donner au dernier joueur ayant fini la derniere partie si ce joueur est le president
    la main a donner a l'avant dernier joueur ayant fini la derniere partie si ce joueur est le vice-president*/
    public HandPresident strategieEchange(byte _joueur) {
        HandPresident h_ = new HandPresident();
        HandPresident hPlayer_= getDeal().hand(_joueur);
        byte gifts_ =(byte) (nombresCartesEchangesMax() - getWinners().indexOfNb(_joueur));
        //0 == h_.total() < gifts_
        boolean rev_ = isReversed();
        CustList<HandPresident> rep_ = getCardsSortedByLengthSortedByStrength(hPlayer_, rev_);
        int index_ = IndexConstants.FIRST_INDEX;
        while (true) {
            HandPresident hLoc_ = rep_.get(index_);
            if (exitSwitch(gifts_,rep_,h_,index_, rev_)) {
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
        h_ = new HandPresident();
        //0 == h_.total() < gifts_
        index_ = IndexConstants.FIRST_INDEX;
        while (true) {
            HandPresident hLoc_ = rep_.get(index_);
            if (hLoc_.total() + h_.total() >= gifts_) {
                int nbCards_ = gifts_ - h_.total();
                for (int i = IndexConstants.FIRST_INDEX; i < nbCards_; i++) {
                    h_.ajouter(hLoc_.carte(i));
                }
                break;
            }
            //hLoc_.total() + h_.total() < gifts_
            h_.ajouterCartes(hLoc_);
            //h_.total() < gifts_
            index_++;
        }
        return h_;
    }
    private boolean exitSwitch(byte _gifts, CustList<HandPresident> _rep, HandPresident _h, int _index, boolean _rev) {
        HandPresident hLoc_ = _rep.get(_index);
        if (hLoc_.premiereCarte().strength(_rev) > GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
            return true;
        }
        if (hLoc_.total() + _h.total() >= _gifts) {
            int nbCards_ = _gifts - _h.total();
            for (int i = IndexConstants.FIRST_INDEX; i < nbCards_; i++) {
                _h.ajouter(hLoc_.carte(i));
            }
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
        Bytes players_ = progressingTrick.getPlayers();
        if (!players_.isEmpty()) {
            byte lastPlayer_ = players_.last();
            byte nextPlayer_ = (byte) ((lastPlayer_ + 1) % getNombreDeJoueurs());
            return nextPlayer_ != _player;
        }
        return false;
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

    public void addCardsToCurrentTrickAndLoop(HandPresident _hand) {
        setupStatus(_hand);
        addCardsToCurrentTrick(_hand);
        lookupNextPlayer();
    }

    private void lookupNextPlayer() {
        byte nbPlayers_ = getNombreDeJoueurs();
        if (progressingTrick.estVide()) {
            emptyTrick(nbPlayers_);
            return;
        }
        while (true) {
            byte pl_ = nextPlayer();
            if (passOrFinish.get(pl_) != BoolVal.TRUE) {
                Playing playing_ = getStatus();
                if (playing_ != Playing.SKIPPED) {
                    if (playing_ == Playing.HAS_TO_EQUAL) {
                        lastStatus.put(pl_, Playing.HAS_TO_EQUAL);
                    } else {
                        //if (playing_ == Playing.DO_NOT_EQUAL)
                        lastStatus.put(pl_, Playing.CAN_PLAY);
                    }
                    break;
                }
                lastStatus.put(pl_, Playing.SKIPPED);
            }
            play(new HandPresident(),pl_);
        }
    }

    private void emptyTrick(byte _nbPlayers) {
        for (byte p = IndexConstants.FIRST_INDEX; p < _nbPlayers; p++) {
            passOrFinish.set(p, ComparatorBoolean.of(getDeal().hand(p).estVide()));
            if (passOrFinish.get(p) == BoolVal.TRUE) {
                lastStatus.put(p, Playing.FINISH);
            } else {
                lastStatus.put(p, Playing.CAN_PLAY);
            }
        }
    }

    private byte setupStatus(HandPresident _hand) {
        byte player_ = nextPlayer();
        lastStatus.clear();
        Playing playingStatus_ = getStatus();
        if (_hand.estVide() && playingStatus_ == Playing.CAN_PLAY) {
            lastStatus.put(player_, Playing.PASS);
            passOrFinish.set(player_, BoolVal.TRUE);
        }
        if (_hand.estVide() && playingStatus_ == Playing.HAS_TO_EQUAL) {
            lastStatus.put(player_, Playing.DO_NOT_EQUAL);
        }
        if (!_hand.estVide()) {
            lastStatus.put(player_, Playing.CAN_PLAY);
        }
        if (getDeal().hand(player_).total() == _hand.total()) {
            lastStatus.put(player_, Playing.FINISH);
            passOrFinish.set(player_, BoolVal.TRUE);
        }
        return player_;
    }

    public void addCardsToCurrentTrick(HandPresident _hand) {
        if (_hand.estVide()) {
            addEmptyHandToCurrentTrick();
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
        if (bestCards_.total() == rules.getNbStacks() * GamePresidentCommon.NB_SUITS && rules.getEqualty() == EqualtyPlaying.SKIP_DIFF_NEXT_STOP) {
            finishDirectlyTrick(_hand);
            finishGame();
            return;
        }
        play(_hand);
        if (!keepPlayingCurrentTrick()) {
            addEmptyTrick();
        }
        finishGame();
    }

    private void play(HandPresident _hand) {
        byte player_ = nextPlayer();
        play(_hand, player_);
    }

    void play(HandPresident _hand, byte _player) {
        progressingTrick.ajouter(_hand, _player);
        getDeal().hand(_player).supprimerCartes(_hand);
    }

    public byte nextPlayer() {
        int count_ = progressingTrick.total();
        return progressingTrick.getPlayer(count_, getNombreDeJoueurs());
    }

    void addEmptyHandToCurrentTrick() {
        play(new HandPresident());
        if (!keepPlayingCurrentTrick()) {
            addEmptyTrick();
            finishGame();
        }
    }

    private void finishGame() {
        Bytes players_ = keepPlayingCurrentGameList();
        if (players_.size() != IndexConstants.ONE_ELEMENT) {
            return;
        }
        byte p_ = players_.first();
        addEmptyTrick();
        play(new HandPresident(getDeal().hand(p_)),p_);
//        progressingTrick.ajouter(new HandPresident(getDeal().hand(p_)), p_);
        tricks.add(progressingTrick);
//        getDeal().hand(p_).supprimerCartes();
        progressingTrick = new TrickPresident(p_);
    }

    private void finishDirectlyTrick(HandPresident _hand) {
        play(_hand);
        byte nb_ = getNombreDeJoueurs();
        for (byte p = IndexConstants.FIRST_INDEX; p < nb_; p++) {
            passOrFinish.set(p,ComparatorBoolean.of(getDeal().hand(p).estVide()));
        }
        addEmptyTrick();
    }

    private void addEmptyTrick() {
        byte leader_ = progressingTrick.getRamasseur(getNombreDeJoueurs());
        tricks.add(progressingTrick);
        if (getDeal().hand(leader_).estVide()) {
            progressingTrick = new TrickPresident(leader_);
            byte pl_ = leader_;
            for (byte p: rules.getSortedPlayersAfterEq(leader_)) {
                if (getDeal().hand(p).estVide()) {
//                    progressingTrick.ajouter(new HandPresident(), p);
                    play(new HandPresident(),p);
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

    private Bytes keepPlayingCurrentGameList() {
        byte nbPlayers_ = getNombreDeJoueurs();
        Bytes l_ = new Bytes();
        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
            if (!getDeal().hand(p).estVide()) {
                l_.add(p);
            }
        }
        return l_;
    }

    public boolean keepPlayingCurrentGame() {
        return keepPlayingCurrentGameList().size() > IndexConstants.ONE_ELEMENT;
    }

    boolean keepPlayingCurrentTrick() {
        byte nb_ = getNombreDeJoueurs();
        byte nbPass_ = 0;
        for (byte p = IndexConstants.FIRST_INDEX; p < nb_; p++) {
            if (passOrFinish.get(p) == BoolVal.TRUE) {
                nbPass_++;
            }
        }
        if (nbPass_ + 1 == nb_) {
            byte winner_ = progressingTrick.getRamasseur(nb_);
            return passOrFinish.get(winner_) == BoolVal.TRUE;
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
        return cartesJouables(getDeal().hand(nextPlayer()));
    }

    HandPresident cartesJouables(HandPresident _hand) {
        if (progressingTrick.estVide()) {
            return new HandPresident(_hand);
        }
        Playing playing_ = getStatus();
        return GamePresidentCommon.getPlayable(_hand, playing_, progressingTrick, isReversed(), rules);
    }


    public Playing getStatus() {
        byte pl_ = nextPlayer();
        if (getDeal().hand(pl_).estVide()) {
            return Playing.FINISH;
        }
        if (passOrFinish.get(pl_) == BoolVal.TRUE) {
            return Playing.PASS;
        }
        if (rules.getEqualty() == EqualtyPlaying.NO_SKIP) {
            return Playing.CAN_PLAY;
        }
        return procSkipping();
    }

    private Playing procSkipping() {
        int count_ = progressingTrick.total();
        int nbPlayers_ = getNombreDeJoueurs();
        CustList<HandPresident> hands_ = new CustList<HandPresident>();
        Ints indexes_ = new Ints();
        int index_;
        if (count_ >= nbPlayers_) {
            index_ = count_ - nbPlayers_;
            for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
                hands_.add(progressingTrick.carte(index_));
                indexes_.add(index_);
                index_++;
            }
        } else {
            index_ = IndexConstants.FIRST_INDEX;
            for (HandPresident h: progressingTrick) {
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
        int max_ = filledHandsIndexes_.last();
        boolean apply_ = applySkippedPlayer(count_, max_);
        HandPresident prev_ = filledHands_.last();
        HandPresident befPrev_ = filledHands_.get(filledHands_.size() - 2);
        boolean rev_ = isReversed();
        if (apply_ && prev_.premiereCarte().strength(rev_) == befPrev_.premiereCarte().strength(rev_)) {
            if (rules.getEqualty() == EqualtyPlaying.SKIP_ALWAYS_NEXT) {
                return Playing.SKIPPED;
            }
            return Playing.HAS_TO_EQUAL;
        }
        return Playing.CAN_PLAY;
    }

    private boolean applySkippedPlayer(int _count, int _max) {
        boolean apply_ = true;
        for (int i = _max + 1; i < _count; i++) {
            byte curPlayer_ = progressingTrick.getPlayer(i, getNombreDeJoueurs());
            if (passOrFinish.get(curPlayer_) != BoolVal.TRUE) {
                //curPlayer_ was skipped
                apply_ = false;
                break;
            }
        }
        return apply_;
    }

    private CustList<BoolVal> passOrFinish() {
        CustList<BoolVal> l_ = new CustList<BoolVal>();
        byte nbPlayer_ = getNombreDeJoueurs();
        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayer_; p++) {
            l_.add(ComparatorBoolean.of(getDeal().hand(p).estVide()));
        }
        int nbHands_ = progressingTrick.total();
        boolean skipped_ = false;
        for (int i = IndexConstants.FIRST_INDEX; i < nbHands_; i++) {
            byte player_ = progressingTrick.getPlayer(i, nbPlayer_);
            if (l_.get(player_) == BoolVal.TRUE || i == IndexConstants.FIRST_INDEX) {
                continue;
            }
            if (!progressingTrick.carte(i).estVide()) {
                boolean rev_ = isReversed();
                byte str_ = progressingTrick.carte(i).premiereCarte().strength(rev_);
                int j_ = prevPlayHand(i);
                if (j_ >= 0 && progressingTrick.carte(j_).premiereCarte().strength(rev_) == str_) {
                    skipped_ = true;
                }
            } else if (skipped_) {
                skipped_ = false;
            } else {
                l_.set(player_, BoolVal.TRUE);
            }
        }
        return l_;
    }

    private int prevPlayHand(int _i) {
        int j_ = _i - 1;
        while (j_ >= 0) {
            if (!progressingTrick.carte(j_).estVide()) {
                break;
            }
            j_--;
        }
        return j_;
    }

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

    private CustList<HandPresident> getCardsSortedByLengthSortedByStrength(HandPresident _hand, boolean _rev) {
        CustList<HandPresident> l_ = new CustList<HandPresident>();
        int nbMaxLen_ = rules.getNbStacks() * GamePresidentCommon.NB_SUITS;
        for (int i = IndexConstants.SECOND_INDEX; i <= nbMaxLen_; i++) {
            l_.addAllElts(_hand.getCardsByLengthSortedByStrength(_rev, i));
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

    public void restituerMainsDepartRejouerDonne(CustList<TrickPresident> _plisFaits,
            byte _nombreJoueurs) {
        for (byte joueur_ = IndexConstants.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            getDeal().hand(joueur_).supprimerCartes();
        }
        for (TrickPresident pli_ : _plisFaits) {
            int index_ = IndexConstants.FIRST_INDEX;
            for (HandPresident carte_ : pli_) {
                getDeal().hand(pli_.getPlayer(index_, getNombreDeJoueurs())).ajouterCartes(carte_);
                index_++;
            }
        }
        revertGifts();
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
            strongestCards(_t, _c, _deal, _eStrongestCards, p);
        }
    }

    private void strongestCards(Ints _t, Ints _c, DealPresident _deal, Bytes _eStrongestCards, byte _p) {
        int previousTricks_ = _t.get(_p);
        CustList<TrickPresident> tricks_ = tricks.left(previousTricks_);
//        CustList<TrickPresident> tricks_ = tricks.left(_t.get(_p) + 1);
        TrickPresident curTrick_ = tricks.get(previousTricks_);
        int previousCards_ = _c.get(_p);
        boolean reversed_ = allFullHandsMerge(tricks_,curTrick_, previousCards_,rules) % 2 == 1;
//        boolean reversed_ = allFullHandsMerge(tricks_,new TrickPresident(),0,rules) % 2 == 1;
//        boolean reversed_ = isReversed(tricks_, rules);
        HandPresident curHand_ = curTrick_.carte(previousCards_);
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
        IntTreeMap< Bytes> tricksPlayers_;
        tricksPlayers_ = new IntTreeMap< Bytes>();
        for (byte p : _players) {
            int noTrick_ = _t.get(p);
            if (tricksPlayers_.contains(noTrick_)) {
                tricksPlayers_.getVal(noTrick_).add(p);
            } else {
                tricksPlayers_.put(noTrick_, Bytes.newList(p));
            }
        }
        Bytes tricksCardsPlayers_;
        tricksCardsPlayers_ = new Bytes();
        for (int k: tricksPlayers_.getKeys()) {
            Bytes players_ = tricksPlayers_.getVal(k);
            IntTreeMap< Byte> ordPlayers_;
            ordPlayers_ = new IntTreeMap< Byte>();
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

    public CustList<BoolVal> getPassOrFinish() {
        return passOrFinish;
    }

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

    private void setLastStatus() {
        lastStatus.clear();
        byte nbPlayers_ = getNombreDeJoueurs();
        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
            setLastStatus(nbPlayers_, p);
        }
    }

    private void setLastStatus(byte _nbPlayers, byte _p) {
        if (deal.hand(_p).estVide()) {
            lastStatus.put(_p, Playing.FINISH);
            return;
        }
        if (passOrFinish.get(_p) == BoolVal.TRUE) {
            lastStatus.put(_p, Playing.PASS);
            return;
        }
        if (rules.getEqualty() == EqualtyPlaying.NO_SKIP) {
            lastStatus.put(_p, Playing.CAN_PLAY);
            return;
        }
        Ints indexes_ = progressingTrick.getPlayedCardsIndexes(_p, _nbPlayers);
        if (progressingTrick.getPlayer(progressingTrick.total(), _nbPlayers) != _p) {
            if (!indexes_.isEmpty()) {
                HandPresident lastHand_ = progressingTrick.carte(indexes_.last());
                if (!lastHand_.estVide()) {
                    lastStatus.put(_p, Playing.CAN_PLAY);
                    return;
                }
                if (rules.getEqualty() == EqualtyPlaying.SKIP_ALWAYS_NEXT) {
                    lastStatus.put(_p, Playing.SKIPPED);
                    return;
                }
                lastStatus.put(_p, Playing.DO_NOT_EQUAL);
                return;
            }
            lastStatus.put(_p, Playing.CAN_PLAY);
            return;
        }
        lastStatus.put(_p, getStatus());
    }

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
