package cards.president;

import cards.consts.GameType;
import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EqList;
import code.util.*;
import code.util.Ints;
import code.util.TreeMap;


public final class GamePresident {

    private static final String EMPTY = "";

    private static final int NB_SUITS = 4;

    /**
    Le type d'une partie est aleatoire ou encore edite ou encore un
    entrainement
    */
    private GameType type;
    /** Contient toutes les cartes au debut de chaque partie */
    private DealPresident deal;

    /** Ce sont les plis faits par les joueurs */
    /** PliTarot en cours d'etre joue */
    private TrickPresident progressingTrick = new TrickPresident(CustList.INDEX_NOT_FOUND_ELT);

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

    private ByteMap<HandPresident> switchedCards = new ByteMap<HandPresident>();

    private String error = "";
    private boolean reversed;

    private BooleanList passOrFinish = new BooleanList();

    private byte nextPlayer = CustList.INDEX_NOT_FOUND_ELT;

    private ByteMap<Playing> lastStatus = new ByteMap<Playing>();

    private HandPresident playedCards = new HandPresident();

    private String reason = EMPTY;

    private CustList<CustList<EqList<HandPresident>>> userHands = new CustList<CustList<EqList<HandPresident>>>();

    private CustList<EqList<HandPresident>> userHandsPerDeal = new CustList<EqList<HandPresident>>();

    private EqList<HandPresident> currentUserHands = new EqList<HandPresident>();

    private CustList<CustList<TrickPresident>> dealTricks = new CustList<CustList<TrickPresident>>();

    private CustList<Bytes> ranksDeals = new CustList<Bytes>();

    private IntMap<ByteMap<Playing>> lastStatusTrick = new IntMap<ByteMap<Playing>>();

    private CustList<IntMap<ByteMap<Playing>>> lastStatusDeal = new CustList<IntMap<ByteMap<Playing>>>();

    private CustList<CustList<IntMap<ByteMap<Playing>>>> lastStatusDeals = new CustList<CustList<IntMap<ByteMap<Playing>>>>();

    private IntMap<Byte> nextPlayerTrick = new IntMap<Byte>();

    private CustList<IntMap<Byte>> nextPlayerDeal = new CustList<IntMap<Byte>>();

    private CustList<CustList<IntMap<Byte>>> nextPlayerDeals = new CustList<CustList<IntMap<Byte>>>();

    private CustList<ByteMap<HandPresident>> switchedCardsDeals = new CustList<ByteMap<HandPresident>>();

    private boolean simulated;

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
        for (int i = CustList.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.add((short) 0);
            passOrFinish.add(false);
        }
        setLastStatus();
        ranks = new Bytes(_ranks);
        byte leader_ = getFirstLeader();
        progressingTrick.setEntameur(leader_);
        nextPlayer = leader_;
        currentUserHands = new EqList<HandPresident>();
        simulated = false;
    }

    public void initPartie() {
        progressingTrick = new TrickPresident(getFirstLeader());
        nextPlayer = progressingTrick.getEntameur();
        lastStatus = new ByteMap<Playing>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i = CustList.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.set(i, (short) 0);
            passOrFinish.set(i, false);
        }
        setLastStatus();
        currentUserHands = new EqList<HandPresident>();
        simulated = false;
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
        if (rules.isPossibleReversing()) {
            boolean reversed_ = false;
            for (TrickPresident t: tricks) {
                if (t.getNombreDeCartesParJoueur() == NB_SUITS * rules.getNbStacks()) {
                    reversed_ = !reversed_;
                }
            }
            reversed = reversed_;
        } else {
            reversed = false;
        }
        passOrFinish = passOrFinish();
        nextPlayer = progressingTrick.getPlayer(progressingTrick.total(), getNombreDeJoueurs());
        lastStatus = new ByteMap<Playing>();
        setLastStatus();
        currentUserHands = new EqList<HandPresident>();
        simulated = false;
    }

    public boolean availableSwitchingCards() {
        return !ranks.isEmpty()&&allowSwitchCards();
    }

    public void initCartesEchanges() {
        if(!ranks.isEmpty()&&allowSwitchCards()) {
            for(byte p=0;p<getNombreDeJoueurs();p++) {
                switchedCards.put(p, new HandPresident());
            }
        }
    }

    public void simulate(int _nbTimes) {
        simulated = true;
        userHands.clear();
        currentUserHands.clear();
        dealTricks.clear();
        ranksDeals.clear();
        lastStatusDeals.clear();
        nextPlayerDeals.clear();
        switchedCardsDeals.clear();
        int noDeal_ = CustList.SIZE_EMPTY;
        while (noDeal_ < _nbTimes) {
            lastStatusDeal = new CustList<IntMap<ByteMap<Playing>>>();
            nextPlayerDeal = new CustList<IntMap<Byte>>();
            lastStatusTrick = new IntMap<ByteMap<Playing>>();
            lastStatusTrick.put(-1, new ByteMap<Playing>(lastStatus));
            currentUserHands = new EqList<HandPresident>();
            userHandsPerDeal = new CustList<EqList<HandPresident>>();
            currentUserHands.add(new HandPresident(deal.main()));
            initCartesEchanges();
            donnerMeilleuresCartes();
            currentUserHands.add(new HandPresident(deal.main()));
            giveWorstCards();
            ByteMap<HandPresident> switchedCards_ = new ByteMap<HandPresident>();
            for (byte p: switchedCards.getKeys()) {
                switchedCards_.put(p, new HandPresident(switchedCards.getVal(p)));
            }
            switchedCardsDeals.add(switchedCards_);
            currentUserHands.add(new HandPresident(deal.main()));
            userHandsPerDeal.add(currentUserHands);
            byte leader_ = getFirstLeader();
            nextPlayerTrick = new IntMap<Byte>();
            nextPlayerTrick.put(-1, leader_);
            progressingTrick = new TrickPresident(leader_);
            passOrFinish = passOrFinish();
            nextPlayer = leader_;
            while (true) {
                HandPresident h_ = playedCards();
                addCardsToCurrentTrickAndLoop(nextPlayer, h_);
                if (!keepPlayingCurrentGame()) {
                    break;
                }
            }
            nextPlayerDeals.add(nextPlayerDeal);
            lastStatusDeals.add(lastStatusDeal);
            userHands.add(userHandsPerDeal);
            if (!ranks.isEmpty()) {
                ranksDeals.add(ranks);
            }
            dealTricks.add(tricks);
            Bytes ranks_ = getNewRanks();
            HandPresident stackNext_ = empiler();
            byte dealer_ = getDistribution().getDonneur();
            deal = new DealPresident(noDeal_ + 1, stackNext_);
            deal.donneurSuivant(dealer_,rules);
            deal.initDonne(rules);
            tricks = new CustList<TrickPresident>();
            ranks = new Bytes(ranks_);
            setLastStatus();
            noDeal_++;
        }
        ranksDeals.add(ranks);
    }

    void initializeFirstTrick() {
        progressingTrick = new TrickPresident(getFirstLeader());
    }

    void initializeTrick(byte _leader) {
        progressingTrick = new TrickPresident(_leader);
    }

    byte getFirstLeader() {
        byte leader_;
        if (ranks.isEmpty() || !rules.isLooserStartsFirst()) {
            leader_ = (byte) ((deal.getDonneur() + 1) % getNombreDeJoueurs());
        } else {
            long min_ = ranks.getMaximum((byte) -1);
            int pl_ = ranks.indexOfObj(min_);
            leader_ = (byte) pl_;
        }
        return leader_;
    }

    public byte numberGivenCards(byte _player) {
        if (getWinners().containsObj(_player)) {
            int ind_ = getWinners().indexOfObj(_player);
            return (byte) (nombresCartesEchangesMax() - ind_);
        }
        return CustList.SIZE_EMPTY;
    }

    public byte nombresCartesEchangesMax() {
        if (getNombreDeJoueurs() <= 3) {
            return 1;
        }
        return 2;
    }

    public Bytes getWinners() {
        if (ranks.isEmpty()) {
            return new Bytes();
        }
        ByteTreeMap<Byte> players_ = new ByteTreeMap<Byte>();
        int r_ = ranks.size();
        for (byte i = CustList.FIRST_INDEX; i < r_; i++) {
            players_.put(ranks.get(i), i);
        }
        Bytes w_ = new Bytes();
        int nb_ = nombresCartesEchangesMax();
        for (byte i = CustList.FIRST_INDEX; i < nb_; i++) {
            w_.add(players_.getValue(i));
        }
        return w_;
    }

    public Bytes getLoosers() {
        if (ranks.isEmpty()) {
            return new Bytes();
        }
        ByteTreeMap<Byte> players_ = new ByteTreeMap<Byte>();
        int r_ = ranks.size();
        for (byte i = CustList.FIRST_INDEX; i < r_; i++) {
            players_.put(ranks.get(i), i);
        }
        Bytes l_ = new Bytes();
        int nb_ = nombresCartesEchangesMax();
        int i_ = players_.size() - 1;
        while (l_.size() < nb_) {
            l_.add(players_.getValue(i_));
            i_--;
        }
        return l_;
    }

    public static Bytes getWinners(Bytes _ranks, int _nbMax) {
        if (_ranks.isEmpty()) {
            return new Bytes();
        }
        ByteTreeMap<Byte> players_ = new ByteTreeMap<Byte>();
        int r_ = _ranks.size();
        for (byte i = CustList.FIRST_INDEX; i < r_; i++) {
            players_.put(_ranks.get(i), i);
        }
        Bytes w_ = new Bytes();
        int nb_ = _nbMax;
        for (byte i = CustList.FIRST_INDEX; i < nb_; i++) {
            w_.add(players_.getValue(i));
        }
        return w_;
    }

    public static Bytes getLoosers(Bytes _ranks, int _nbMax) {
        if (_ranks.isEmpty()) {
            return new Bytes();
        }
        ByteTreeMap<Byte> players_ = new ByteTreeMap<Byte>();
        int r_ = _ranks.size();
        for (byte i = CustList.FIRST_INDEX; i < r_; i++) {
            players_.put(_ranks.get(i), i);
        }
        Bytes l_ = new Bytes();
        int nb_ = _nbMax;
        int i_ = players_.size() - 1;
        while (l_.size() < nb_) {
            l_.add(players_.getValue(i_));
            i_--;
        }
        return l_;
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
        int ind_ = _losers.indexOfObj(_loser);
        return _winners.get(ind_);
    }

    public static byte getMatchingLoser(Bytes _winners, Bytes _losers, byte _winner) {
        int ind_ = _winners.indexOfObj(_winner);
        return _losers.get(ind_);
    }

    public byte getMatchingWinner(byte _loser) {
        int ind_ = getLoosers().indexOfObj(_loser);
        return getWinners().get(ind_);
    }

    public byte getMatchingLoser(byte _winner) {
        int ind_ = getWinners().indexOfObj(_winner);
        return getLoosers().get(ind_);
    }

    //single mode
    public boolean readyToPlay() {
        if (switchedCards.isEmpty()) {
            return true;
        }
        if (getWinners().containsObj(DealPresident.NUMERO_UTILISATEUR)) {
            if (switchedCards.getVal(DealPresident.NUMERO_UTILISATEUR).estVide()) {
                return false;
            }
        }
        return true;
    }

    //Case empty human winners:
    public boolean readyToPlayMulti() {
        if (switchedCards.isEmpty()) {
            return true;
        }
        for (byte w: getWinners()) {
            if (switchedCards.getVal(w).estVide()) {
                return false;
            }
        }
        return true;
    }

    public void giveWorstCards() {
        if (switchedCards.isEmpty()) {
            return;
        }
        for (byte w: getWinners()) {
            HandPresident h_ = strategieEchange(w);
            switchedCards.getVal(w).ajouterCartes(h_);
        }
        for (byte l: getLoosers()) {
            recevoirPiresCartes(l);
        }
        supprimerDons();
    }

    //Case not empty human winners:
    public void giveWorstCards(Bytes _humanPlayers) {
        if (switchedCards.isEmpty()) {
            return;
        }
        for (byte w: getWinners()) {
            if (_humanPlayers.containsObj(w)) {
                continue;
            }
            HandPresident h_ = strategieEchange(w);
            switchedCards.getVal(w).ajouterCartes(h_);
        }
    }

    //multi mode
    public boolean giveWorstCards(Bytes _humanPlayers, byte _player, HandPresident _hand) {
        switchedCards.getVal(_player).ajouterCartes(_hand);
        boolean finished_ = true;
        for (byte w: switchedCards.getKeys()) {
            if (!_humanPlayers.containsObj(w)) {
                continue;
            }
            HandPresident h_ = switchedCards.getVal(w);
            if (h_.estVide()) {
                finished_ = false;
                break;
            }
        }
        if (finished_) {
            for (byte l: getLoosers()) {
                recevoirPiresCartes(l);
            }
            supprimerDons();
        }
        return finished_;
    }

    //single mode
    public void giveWorstCards(HandPresident _hand) {
        switchedCards.getVal(DealPresident.NUMERO_UTILISATEUR).ajouterCartes(_hand);
        for (byte l: getLoosers()) {
            recevoirPiresCartes(l);
        }
        supprimerDons();
    }

    public void donnerMeilleuresCartes(byte _joueur) {
        int ind_= getLoosers().indexOfObj(_joueur);
        int nbCards_ = nombresCartesEchangesMax() - ind_;
        HandPresident copie_=new HandPresident();
        copie_.ajouterCartes(getDistribution().main(_joueur));
        copie_.sortCardsBegin();
        for(byte i=0;i<nbCards_;i++) {
            switchedCards.getVal(_joueur).ajouter(copie_.carte(i));
        }
    }

    public void recevoirMeilleuresCartes(byte _joueur) {
        int ind_= getWinners().indexOfObj(_joueur);
        byte pl_ = getLoosers().get(ind_);
        getDistribution().main(_joueur).ajouterCartes(switchedCards.getVal(pl_));
    }

    public void recevoirPiresCartes(byte _joueur) {
        int ind_= getLoosers().indexOfObj(_joueur);
        byte pl_ = getWinners().get(ind_);
        getDistribution().main(_joueur).ajouterCartes(switchedCards.getVal(pl_));
    }

    public void supprimerDons() {
        for(byte p: switchedCards.getKeys()) {
            getDistribution().main(p).supprimerCartes(switchedCards.getVal(p));
        }
    }

    void revertGifts() {
        if (switchedCards.isEmpty()) {
            return;
        }
        for (byte w: getWinners()) {
            int ind_= getWinners().indexOfObj(w);
            byte pl_ = getLoosers().get(ind_);
            getDistribution().main(w).supprimerCartes(switchedCards.getVal(pl_));
            getDistribution().main(w).ajouterCartes(switchedCards.getVal(w));
        }
        for (byte l: getLoosers()) {
            int ind_= getLoosers().indexOfObj(l);
            byte pl_ = getWinners().get(ind_);
            getDistribution().main(l).supprimerCartes(switchedCards.getVal(pl_));
            getDistribution().main(l).ajouterCartes(switchedCards.getVal(l));
        }
        for (byte p: switchedCards.getKeys()) {
            switchedCards.getVal(p).supprimerCartes();
        }
    }

    /**Retourne la main a donner au dernier joueur ayant fini la derniere partie si ce joueur est le president
    la main a donner a l'avant dernier joueur ayant fini la derniere partie si ce joueur est le vice-president*/
    public HandPresident strategieEchange(byte _joueur) {
        HandPresident h_ = new HandPresident();
        HandPresident hPlayer_= getDistribution().main(_joueur);
        byte gifts_ =(byte) (nombresCartesEchangesMax() - getWinners().indexOfObj(_joueur));
        //0 == h_.total() < gifts_
        byte mid_ = GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH;
        EqList<HandPresident> rep_ = getCardsSortedByLengthSortedByStrength(hPlayer_);
        int index_ = CustList.FIRST_INDEX;
        while (true) {
            HandPresident hLoc_ = rep_.get(index_);
            if (hLoc_.premiereCarte().strength(reversed) > mid_) {
                break;
            }
            if (hLoc_.total() + h_.total() >= gifts_) {
                int nbCards_ = gifts_ - h_.total();
                for (int i = CustList.FIRST_INDEX; i < nbCards_; i++) {
                    h_.ajouter(hLoc_.carte(i));
                }
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
        index_ = CustList.FIRST_INDEX;
        while (true) {
            HandPresident hLoc_ = rep_.get(index_);
            if (hLoc_.total() + h_.total() >= gifts_) {
                int nbCards_ = gifts_ - h_.total();
                for (int i = CustList.FIRST_INDEX; i < nbCards_; i++) {
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

    public byte getNextPlayer() {
        return nextPlayer;
    }

    public byte getNextPlayer(byte _player) {
        return (byte)((_player + 1) % getNombreDeJoueurs());
    }

    public boolean currentPlayerHasPlayed(byte _player) {
        Bytes players_ = progressingTrick.getPlayers();
        if (!players_.isEmpty()) {
            byte lastPlayer_ = players_.last();
            byte nextPlayer_ = (byte) ((lastPlayer_ + 1) % getNombreDeJoueurs());
            if (nextPlayer_ != _player) {
                return true;
            }
        }
        addCardsToCurrentTrick(_player);
        return false;
    }

    public void addCardsToCurrentTrick(byte _player) {
        HandPresident h_ = playedCards();
        playedCards = h_;
        addCardsToCurrentTrickAndLoop(_player, h_);
    }

    public void addCardsToCurrentTrick(byte _player, CardPresident _card, byte _nb) {
        HandPresident h_ = playHand(_player, _card, _nb);
        playedCards = h_;
        addCardsToCurrentTrickAndLoop(_player, h_);
    }

    public void noPlay(byte _player) {
        playedCards = new HandPresident();
        addCardsToCurrentTrickAndLoop(_player, new HandPresident());
    }

    public void addCardsToCurrentTrickAndLoop(byte _player, HandPresident _hand) {
        lastStatus.clear();
        Playing playingStatus_ = getStatus(_player);
        if (_hand.estVide() && playingStatus_ == Playing.CAN_PLAY) {
            lastStatus.put(_player, Playing.PASS);
            passOrFinish.set(_player, true);
        }
        if (_hand.estVide() && playingStatus_ == Playing.HAS_TO_EQUAL) {
            lastStatus.put(_player, Playing.DO_NOT_EQUAL);
        }
        if (!_hand.estVide()) {
            lastStatus.put(_player, Playing.CAN_PLAY);
        }
        if (getDistribution().main(_player).total() == _hand.total()) {
            lastStatus.put(_player, Playing.FINISH);
            passOrFinish.set(_player, true);
        }
        putLastStatusSimu(progressingTrick.total());
        addCardsToCurrentTrick(_player, _hand);
        byte nbPlayers_ = getNombreDeJoueurs();
        if (progressingTrick.estVide()) {
            putLastStatusSimu(-1);
            for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
                passOrFinish.set(p, getDistribution().main(p).estVide());
                if (passOrFinish.get(p)) {
                    lastStatus.put(p, Playing.FINISH);
                } else {
                    lastStatus.put(p, Playing.CAN_PLAY);
                }
            }
            return;
        }
        byte pl_ = (byte) ((_player + 1) % nbPlayers_);
        while (true) {
            if (!passOrFinish.get(pl_)) {
                Playing playing_ = getStatus(pl_);
                if (playing_ != Playing.SKIPPED) {
                    if (playing_ == Playing.HAS_TO_EQUAL) {
                        lastStatus.put(pl_, Playing.HAS_TO_EQUAL);
                    } else {
                        //if (playing_ == Playing.DO_NOT_EQUAL)
                        lastStatus.put(pl_, Playing.CAN_PLAY);
                    }
                    putLastStatusSimu(progressingTrick.total());
                    break;
                }
                lastStatus.put(pl_, Playing.SKIPPED);
            }
            progressingTrick.ajouter(new HandPresident(), pl_);
            pl_ = (byte) ((pl_ + 1) % getNombreDeJoueurs());
        }
        nextPlayer = pl_;
    }

    void putLastStatusSimu(int _key) {
        if (!simulated) {
            return;
        }
        nextPlayerTrick.put(_key, nextPlayer);
        lastStatusTrick.put(_key, new ByteMap<Playing>(lastStatus));
    }

    public void addCardsToCurrentTrick(byte _player, HandPresident _hand) {
        if (_hand.estVide()) {
            addEmptyHandToCurrentTrick(_player);
            return;
        }
        byte str_ = _hand.premiereCarte().strength(reversed);
        if (str_ == CardPresident.getMaxStrength(reversed)) {
            finishDirectlyTrick(_player, _hand);
            nextPlayer = progressingTrick.getEntameur();
            finishGame();
            return;
        }
        EqList<HandPresident> previousHands_ = progressingTrick.getFilledHandsBefore(progressingTrick.total());
        HandPresident bestCards_ = new HandPresident();
        for (HandPresident p: previousHands_) {
            bestCards_.ajouterCartes(p.getCardsByStrength(str_, reversed));
        }
        bestCards_.ajouterCartes(_hand);
        if (bestCards_.total() == rules.getNbStacks() * NB_SUITS) {
            if (rules.getEqualty() == EqualtyPlaying.SKIP_DIFF_NEXT_STOP) {
                finishDirectlyTrick(_player, _hand);
                nextPlayer = progressingTrick.getEntameur();
                finishGame();
                return;
            }
        }
        progressingTrick.ajouter(_hand, _player);
        getDistribution().main(_player).supprimerCartes(_hand);
        reverseStrength(_hand);
        if (!keepPlayingCurrentTrick()) {
            tricks.add(progressingTrick);
            initializeNextSimuTrick();
            byte leader_ = progressingTrick.getRamasseur(getNombreDeJoueurs());
            addEmptyTrick(leader_);
            nextPlayer = progressingTrick.getEntameur();
        }
        finishGame();
    }

    void addEmptyHandToCurrentTrick(byte _player) {
        progressingTrick.ajouter(new HandPresident(), _player);
        if (!keepPlayingCurrentTrick()) {
            tricks.add(progressingTrick);
            initializeNextSimuTrick();
            byte leader_ = progressingTrick.getRamasseur(getNombreDeJoueurs());
            addEmptyTrick(leader_);
            nextPlayer = progressingTrick.getEntameur();
            finishGame();
        }
    }

    void initializeNextSimuTrick() {
        if (!simulated) {
            return;
        }
        nextPlayerDeal.add(nextPlayerTrick);
        nextPlayerTrick = new IntMap<Byte>();
        lastStatusDeal.add(lastStatusTrick);
        lastStatusTrick = new IntMap<ByteMap<Playing>>();
    }

    private void finishGame() {
        Bytes players_ = keepPlayingCurrentGameList();
        if (players_.size() != CustList.ONE_ELEMENT) {
            return;
        }
        byte p_ = players_.first();
        byte win_ = progressingTrick.getRamasseur(getNombreDeJoueurs());
        tricks.add(progressingTrick);
        addEmptyTrick(win_);
        progressingTrick.ajouter(new HandPresident(getDistribution().main(p_)), p_);
        tricks.add(progressingTrick);
        getDistribution().main(p_).supprimerCartes();
        progressingTrick = new TrickPresident(p_);
    }

    private void finishDirectlyTrick(byte _player, HandPresident _hand) {
        progressingTrick.ajouter(_hand, _player);
        getDistribution().main(_player).supprimerCartes(_hand);
        tricks.add(progressingTrick);
        initializeNextSimuTrick();
        byte nb_ = getNombreDeJoueurs();
        for (byte p = CustList.FIRST_INDEX; p < nb_; p++) {
            passOrFinish.set(p, getDistribution().main(p).estVide());
        }
        addEmptyTrick(_player);
        reverseStrength(_hand);
    }

    void addEmptyTrick(byte _player) {
        if (getDistribution().main(_player).estVide()) {
            progressingTrick = new TrickPresident(_player);
            byte pl_ = _player;
            for (byte p: rules.getSortedPlayersAfterEq(_player)) {
                if (getDistribution().main(p).estVide()) {
                    progressingTrick.ajouter(new HandPresident(), p);
                } else {
                    pl_ = p;
                    break;
                }
            }
            tricks.add(progressingTrick);
            progressingTrick = new TrickPresident(pl_);
        } else {
            progressingTrick = new TrickPresident(_player);
        }
    }

    void reverseStrength(HandPresident _hand) {
        if (rules.isPossibleReversing() && _hand.total() == rules.getNbStacks() * NB_SUITS) {
            reversed = !reversed;
        }
    }

    public Bytes keepPlayingCurrentGameList() {
        byte nbPlayers_ = getNombreDeJoueurs();
        Bytes l_ = new Bytes();
        for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
            if (!getDistribution().main(p).estVide()) {
                l_.add(p);
            }
        }
        return l_;
    }

    public boolean keepPlayingCurrentGame() {
        return keepPlayingCurrentGameList().size() > CustList.ONE_ELEMENT;
    }

    boolean keepPlayingCurrentTrick() {
        byte nb_ = getNombreDeJoueurs();
        byte nbPass_ = 0;
        for (byte p = CustList.FIRST_INDEX; p < nb_; p++) {
            if (passOrFinish.get(p)) {
                nbPass_++;
            }
        }
        if (nbPass_ + 1 == nb_) {
            byte winner_ = progressingTrick.getRamasseur(nb_);
            return passOrFinish.get(winner_);
        }
        return nbPass_ < nb_;
    }

    public boolean canPass(byte _player) {
        HandPresident playable_ = cartesJouables(_player);
        if (rules.isHasToPlay() && !playable_.estVide()) {
            HandPresident b_ = progressingTrick.getBestCards();
            if (b_.total() == playable_.total() && rules.isLoosingIfFinishByBestCards()) {
                HandPresident h_ = deal.main(_player);
                boolean existBestCards_ = false;
                for (CardPresident c: h_) {
                    if (c.strength(reversed) == CardPresident.getMaxStrength(reversed)) {
                        existBestCards_ = true;
                    }
                }
                if (existBestCards_) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public boolean allowPlaying(byte _player, HandPresident _card) {
        HandPresident playable_ = cartesJouables(_player);
        return !playable_.getCardsByStrength(_card.premiereCarte().strength(reversed), reversed).estVide();
    }

    public boolean allowPlaying(byte _player, CardPresident _card, byte _nb) {
        HandPresident playable_ = cartesJouables(_player);
        return !playable_.getCardsByStrength(_card.strength(reversed), reversed).estVide();
    }

    public HandPresident playHand(byte _player, CardPresident _card, byte _nb) {
        HandPresident main_ = getDistribution().main(_player);
        HandPresident h_ = main_.getCardsByStrength(_card.strength(reversed), reversed);
        HandPresident cardsToBePlayed_ = new HandPresident();
        if (progressingTrick.estVide()) {
            for (CardPresident c: h_) {
                if (cardsToBePlayed_.total() >= _nb) {
                    break;
                }
                cardsToBePlayed_.ajouter(c);
            }
        } else {
            for (CardPresident c: h_) {
                if (cardsToBePlayed_.total() >= progressingTrick.getNombreDeCartesParJoueur()) {
                    break;
                }
                cardsToBePlayed_.ajouter(c);
            }
        }
        return cardsToBePlayed_;
    }

    HandPresident cartesJouables(byte _player) {
        return cartesJouables(_player, getDistribution().main(_player));
    }

    HandPresident cartesJouables(byte _player, HandPresident _hand) {
        if (progressingTrick.estVide()) {
            return new HandPresident(_hand);
        }
        Playing playing_ = getStatus(_player);
        if (playing_ == Playing.FINISH) {
            return new HandPresident();
        }
        if (playing_ == Playing.PASS) {
            return new HandPresident();
        }
        if (playing_ == Playing.SKIPPED) {
            return new HandPresident();
        }
        ByteTreeMap<HandPresident> filtered_;
        filtered_ = new ByteTreeMap<HandPresident>();
        HandPresident l_ = progressingTrick.getBestCards();
        byte str_ = l_.premiereCarte().strength(reversed);
        if (playing_ == Playing.HAS_TO_EQUAL) {
            ByteTreeMap<HandPresident> cards_ = _hand.getCardsByStrength(reversed);
            for (byte s: cards_.getKeys()) {
                HandPresident h_ = cards_.getVal(s);
                if (s != str_) {
                    continue;
                }
                filtered_.put(s, h_);
            }
        } else if (rules.getEqualty() == EqualtyPlaying.FORBIDDEN) {
            ByteTreeMap<HandPresident> cards_ = _hand.getCardsByStrength(reversed);
            for (byte s: cards_.getKeys()) {
                HandPresident h_ = cards_.getVal(s);
                if (s <= str_) {
                    continue;
                }
                filtered_.put(s, h_);
            }
        } else {
            ByteTreeMap<HandPresident> cards_ = _hand.getCardsByStrength(reversed);
            for (byte s: cards_.getKeys()) {
                HandPresident h_ = cards_.getVal(s);
                if (s < str_) {
                    continue;
                }
                filtered_.put(s, h_);
            }
        }
        HandPresident plCards_ = new HandPresident();
        for (byte s: filtered_.getKeys()) {
            HandPresident h_ = filtered_.getVal(s);
            if (h_.total() < l_.total()) {
                continue;
            }
            plCards_.ajouterCartes(h_);
        }
        return plCards_;
    }

    public Playing getStatus(byte _player) {
        if (getDistribution().main(_player).estVide()) {
            return Playing.FINISH;
        }
        if (passOrFinish.get(_player)) {
            return Playing.PASS;
        }
        if (rules.getEqualty() == EqualtyPlaying.NO_SKIP) {
            return Playing.CAN_PLAY;
        }
        int count_ = progressingTrick.total();
        int nbPlayers_ = getNombreDeJoueurs();
        EqList<HandPresident> hands_ = new EqList<HandPresident>();
        Ints indexes_ = new Ints();
        int index_;
        if (count_ >= nbPlayers_) {
            index_ = count_ - nbPlayers_;
            for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
                hands_.add(progressingTrick.carte(index_));
                indexes_.add(index_);
                index_++;
            }
        } else {
            index_ = CustList.FIRST_INDEX;
            for (HandPresident h: progressingTrick) {
                hands_.add(h);
                indexes_.add(index_);
                index_++;
            }
        }
        EqList<HandPresident> filledHands_ = new EqList<HandPresident>();
        Ints filledHandsIndexes_ = new Ints();
        index_ = CustList.FIRST_INDEX;
        for (HandPresident h: hands_) {
            if (h.estVide()) {
                index_++;
                continue;
            }
            filledHands_.add(h);
            filledHandsIndexes_.add(indexes_.get(index_));
            index_++;
        }
        if (filledHands_.size() > CustList.ONE_ELEMENT) {
            int max_ = filledHandsIndexes_.last();
            boolean apply_ = true;
            for (int i = max_ + 1; i < count_; i++) {
                byte curPlayer_ = progressingTrick.getPlayer(i, getNombreDeJoueurs());
                if (!passOrFinish.get(curPlayer_)) {
                    //curPlayer_ was skipped
                    apply_ = false;
                    break;
                }
            }
            HandPresident prev_ = filledHands_.last();
            HandPresident befPrev_ = filledHands_.get(filledHands_.size() - 2);
            if (apply_ && prev_.premiereCarte().strength(reversed) == befPrev_.premiereCarte().strength(reversed)) {
                if (rules.getEqualty() == EqualtyPlaying.SKIP_ALWAYS_NEXT) {
                    return Playing.SKIPPED;
                }
                return Playing.HAS_TO_EQUAL;
            }
        }
        return Playing.CAN_PLAY;
    }

    public BooleanList passOrFinish() {
        BooleanList l_ = new BooleanList();
        byte nbPlayer_ = getNombreDeJoueurs();
        for (byte p = CustList.FIRST_INDEX; p < nbPlayer_; p++) {
            if (getDistribution().main(p).estVide()) {
                l_.add(true);
            } else {
                l_.add(false);
            }
        }
        int nbHands_ = progressingTrick.total();
        boolean skipped_ = false;
        for (int i = CustList.FIRST_INDEX; i < nbHands_; i++) {
            byte player_ = progressingTrick.getPlayer(i, nbPlayer_);
            if (l_.get(player_)) {
                continue;
            }
            if (i == CustList.FIRST_INDEX) {
                continue;
            }
            if (!progressingTrick.carte(i).estVide()) {
                byte str_ = progressingTrick.carte(i).premiereCarte().strength(reversed);
                int j_ = i - 1;
                while (progressingTrick.carte(j_).estVide()) {
                    j_--;
                }
                if (progressingTrick.carte(j_).premiereCarte().strength(reversed) == str_) {
                    skipped_ = true;
                }
                continue;
            }
            if (skipped_) {
                skipped_ = false;
                continue;
            }
            l_.set(player_, true);
        }
        return l_;
    }

    public HandPresident playedCards() {
        if (progressingTrick.estVide()) {
            return beginTrick();
        }
        return progressTrick();
    }

    public HandPresident beginTrick() {
        HandPresident playable_ = cartesJouables(progressingTrick.getEntameur());
        ByteTreeMap<HandPresident> m_ = playable_.getCardsByStrength(reversed);
        EqList<HandPresident> notEmpty_ = new EqList<HandPresident>();
        for (byte b: m_.getKeys()) {
            HandPresident h_ = m_.getVal(b);
            if (!h_.estVide()) {
                notEmpty_.add(h_);
            }
        }
        TreeMap<CardPresident,Byte> possibleRep_ = getPlayedCardsByStrength();
        if (notEmpty_.size() == 2) {
            if (notEmpty_.last().derniereCarte().strength(reversed) == CardPresident.getMaxStrength(reversed)) {
                return notEmpty_.last();
            }
            EqList<HandPresident> l_ = getLeadingCardsPlayer(m_, possibleRep_);
            if (rules.isPossibleReversing()) {
                int max_ = NB_SUITS * rules.getNbStacks();
                if (!l_.isEmpty() && l_.first().total() < max_) {
                    return l_.first();
                }
                if (!l_.isEmpty() && l_.last().total() < max_) {
                    return l_.last();
                }
            } else {
                if (!l_.isEmpty()) {
                    return l_.first();
                }
            }
        }
        if (notEmpty_.size() == 1) {
            return notEmpty_.first();
        }
        HandPresident d_ = dominantHand(playable_, possibleRep_);
        if (!d_.estVide()) {
            return d_;
        }
        int maxStack_ = rules.getNbStacks() * NB_SUITS;
        byte avg_ = GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH;
        EqList<HandPresident> notEmptyWorst_ = new EqList<HandPresident>();
        for (byte b: m_.getKeys()) {
            if (b > avg_) {
                continue;
            }
            HandPresident h_ = m_.getVal(b);
            if (h_.total() <= maxStack_ / 2) {
                continue;
            }
            if (!h_.estVide()) {
                notEmptyWorst_.add(h_);
            }
        }
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.last();
        }
        for (byte b: m_.getKeys()) {
            HandPresident h_ = m_.getVal(b);
            if (!h_.estVide()) {
                return h_;
            }
        }
        HandPresident h_ = new HandPresident();
        h_.ajouter(playable_.derniereCarte());
        return h_;
    }

    EqList<HandPresident> getLeadingCardsPlayer(ByteTreeMap<HandPresident> _m, TreeMap<CardPresident,Byte> _playedCards) {
        TreeMap<CardPresident,Byte> virtualPlayedCards_ = new TreeMap<CardPresident, Byte>(new GameStrengthCardPresidentComparator(reversed, true));
        virtualPlayedCards_.putAllMap(_playedCards);
        for (byte s: _m.getKeys()) {
            HandPresident h_ = _m.getVal(s);
            if (h_.estVide()) {
                continue;
            }
            byte strength_ = h_.premiereCarte().strength(reversed);
            for (CardPresident c: virtualPlayedCards_.getKeys()) {
                if (c.strength(reversed) == strength_) {
                    byte c_ = virtualPlayedCards_.getVal(c);
                    c_ += h_.total();
                    virtualPlayedCards_.put(c, c_);
                }
            }
        }
        return getLeadingCards(_m, virtualPlayedCards_);
    }

    EqList<HandPresident> getLeadingCards(ByteTreeMap<HandPresident> _m, TreeMap<CardPresident,Byte> _playedCards) {
        EqList<HandPresident> hands_ = new EqList<HandPresident>();
        for (byte s: _m.getKeys()) {
            HandPresident h_ = _m.getVal(s);
            if (h_.estVide()) {
                continue;
            }
            byte strength_ = h_.premiereCarte().strength(reversed);
            if (strength_ == CardPresident.getMaxStrength(reversed)) {
                hands_.add(h_);
                continue;
            }
            int rem_ = 0;
            for (CardPresident c: _playedCards.getKeys()) {
                if (c.strength(reversed) >= strength_) {
                    int remLoc_ = NB_SUITS * rules.getNbStacks() - _playedCards.getVal(c);
                    if (remLoc_ > rem_) {
                        rem_ = remLoc_;
                    }
                }
            }
            if (h_.total() > rem_) {
                hands_.add(h_);
            }
        }
        return hands_;
    }

    public HandPresident progressTrick() {
        int count_ = progressingTrick.total();
        int index_ = count_;
        byte player_ = progressingTrick.getPlayer(index_, getNombreDeJoueurs());
        HandPresident playable_ = cartesJouables(player_);
        if (playable_.estVide()) {
            return playable_;
        }
        ByteTreeMap<HandPresident> m_ = playable_.getCardsByStrength(reversed);
        EqList<HandPresident> notEmpty_ = new EqList<HandPresident>();
        for (byte b: m_.getKeys()) {
            HandPresident h_ = m_.getVal(b);
            if (!h_.estVide()) {
                notEmpty_.add(h_);
            }
        }
        int nb_ = progressingTrick.getNombreDeCartesParJoueur();
        if (notEmpty_.size() == 2 && playable_.total() == deal.main(player_).total()) {
            for (byte b: m_.getKeys()) {
                HandPresident h_ = m_.getVal(b);
                if (h_.total() != nb_) {
                    continue;
                }
                if (h_.premiereCarte().strength(reversed) == CardPresident.getMaxStrength(reversed)) {
                    return h_;
                }
            }
        }
        TreeMap<CardPresident,Byte> possibleRep_ = getPlayedCardsByStrength();
        if (notEmpty_.size() == CustList.ONE_ELEMENT && playable_.total() == deal.main(player_).total()) {
            if (notEmpty_.first().total() == nb_) {
                return notEmpty_.first();
            }
            //notEmpty_.first().total() > progressingTrick.getNombreDeCartesParJoueur()
            if (dominantGroup(notEmpty_.first(), nb_, possibleRep_, m_)) {
                HandPresident h_ = new HandPresident();
                for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                    h_.ajouter(notEmpty_.first().carte(i));
                }
                return h_;
            }
        }
        HandPresident fullHand_ = deal.main(player_);
        if (dominantHandProgressTrick(fullHand_, possibleRep_)) {
            HandPresident h_ = lastGroup(m_);
            if (!h_.estVide()) {
                return h_;
            }
        }
        byte avg_ = GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH;
        if (progressingTrick.getBestCards().derniereCarte().strength(reversed) <= avg_) {
            EqList<HandPresident> notEmptyWorst_ = new EqList<HandPresident>();
            for (byte b: m_.getKeys()) {
                if (b > avg_) {
                    continue;
                }
                HandPresident h_ = m_.getVal(b);
                if (h_.total() != nb_) {
                    continue;
                }
                notEmptyWorst_.add(h_);
            }
            if (!notEmptyWorst_.isEmpty()) {
                return notEmptyWorst_.first();
            }
        }
        EqList<HandPresident> notEmptyWorst_ = new EqList<HandPresident>();
        int midHand_ = CustList.FIRST_INDEX;
        for (CardPresident c: playable_) {
            midHand_ += c.strength(reversed);
        }
        midHand_ /= playable_.total();
        for (byte b: m_.getKeys()) {
            if (b > midHand_) {
                continue;
            }
            HandPresident h_ = m_.getVal(b);
            if (h_.total() != nb_) {
                continue;
            }
            notEmptyWorst_.add(h_);
        }
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.first();
        }
        boolean strongHand_ = true;
        boolean weakHand_ = true;
        for (CardPresident c: fullHand_) {
            if (c.strength(reversed) < avg_) {
                strongHand_ = false;
            }
            if (c.strength(reversed) > avg_) {
                weakHand_ = false;
            }
        }
        if (strongHand_ || weakHand_) {
            HandPresident h_ = defaultGroup(m_);
            if (!h_.estVide()) {
                return h_;
            }
        }
        notEmptyWorst_ = new EqList<HandPresident>();
        for (byte b: m_.getKeys()) {
            if (b > avg_) {
                continue;
            }
            HandPresident h_ = m_.getVal(b);
            if (h_.estVide()) {
                continue;
            }
            HandPresident hSub_ = new HandPresident();
            for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                hSub_.ajouter(h_.carte(i));
            }
            notEmptyWorst_.add(hSub_);
        }
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.first();
        }
        HandPresident b_ = progressingTrick.getBestCards();
        if (b_.total() == playable_.total() && rules.isLoosingIfFinishByBestCards()) {
            HandPresident h_ = deal.main(player_);
            boolean existBestCards_ = false;
            for (CardPresident c: h_) {
                if (c.strength(reversed) == CardPresident.getMaxStrength(reversed)) {
                    existBestCards_ = true;
                }
            }
            if (existBestCards_) {
                return new HandPresident();
            }
        }
        if (canPass(player_)) {
            return new HandPresident();
        }
        for (byte b: m_.getKeys()) {
            if (b > avg_) {
                continue;
            }
            HandPresident h_ = m_.getVal(b);
            if (h_.total() == nb_) {
                return h_;
            }
        }
        notEmpty_ = new EqList<HandPresident>();
        for (byte b: m_.getKeys()) {
            HandPresident h_ = m_.getVal(b);
            if (!h_.estVide()) {
                notEmpty_.add(h_);
            }
        }
        HandPresident hSub_ = new HandPresident();
        for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
            hSub_.ajouter(notEmpty_.first().carte(i));
        }
        return hSub_;
    }

    HandPresident defaultGroup(ByteTreeMap<HandPresident> _m) {
        int nb_ = progressingTrick.getNombreDeCartesParJoueur();
        for (byte b: _m.getKeys()) {
            HandPresident h_ = _m.getVal(b);
            if (h_.total() == nb_) {
                return h_;
            }
        }
        for (byte b: _m.getKeys()) {
            HandPresident h_ = _m.getVal(b);
            if (h_.total() > nb_) {
                HandPresident hSub_ = new HandPresident();
                for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                    hSub_.ajouter(h_.carte(i));
                }
                return hSub_;
            }
        }
        return new HandPresident();
    }

    HandPresident lastGroup(ByteTreeMap<HandPresident> _m) {
        int nb_ = progressingTrick.getNombreDeCartesParJoueur();
        byte avg_ = GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH;
        for (byte b: _m.getKeys()) {
            HandPresident h_ = _m.getVal(b);
            if (h_.total() == nb_) {
                return h_;
            }
        }
        for (byte b: _m.getKeys()) {
            if (b >= avg_) {
                continue;
            }
            HandPresident h_ = _m.getVal(b);
            if (h_.total() > nb_) {
                HandPresident hSub_ = new HandPresident();
                for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                    hSub_.ajouter(h_.carte(i));
                }
                return hSub_;
            }
        }
        return new HandPresident();
    }

    public TreeMap<CardPresident,Byte> getPlayedCardsByStrength() {
        TreeMap<CardPresident,Byte> tree_;
        tree_ = new TreeMap<CardPresident,Byte>(new GameStrengthCardPresidentComparator(reversed, true));
        HandPresident playedCards_ = new HandPresident();
        for (TrickPresident t: tricks) {
            for (HandPresident h: t) {
                playedCards_.ajouterCartes(h);
            }
        }
        for (HandPresident h: progressingTrick) {
            playedCards_.ajouterCartes(h);
        }
        for (CardPresident c: playedCards_) {
            boolean eqStrPres_ = false;
            for (CardPresident s: tree_.getKeys()) {
                byte str_ = s.strength(reversed);
                if (str_ == c.strength(reversed)) {
                    eqStrPres_ = true;
                    byte nb_ = tree_.getVal(s);
                    nb_++;
                    tree_.put(s, nb_);
                    break;
                }
            }
            if (!eqStrPres_) {
                tree_.put(c, CustList.ONE_ELEMENT);
            }
        }
        for (CardPresident c: HandPresident.pileBase()) {
            boolean eqStrPres_ = false;
            for (CardPresident s: tree_.getKeys()) {
                byte str_ = s.strength(reversed);
                if (str_ == c.strength(reversed)) {
                    eqStrPres_ = true;
                    break;
                }
            }
            if (!eqStrPres_) {
                tree_.put(c, CustList.SIZE_EMPTY);
            }
        }
        return tree_;
    }

    public EqList<HandPresident> getCardsSortedByLengthSortedByStrength(HandPresident _hand) {
        EqList<HandPresident> l_ = new EqList<HandPresident>();
        int nbMaxLen_ = rules.getNbStacks() * NB_SUITS;
        for (int i = CustList.SECOND_INDEX; i <= nbMaxLen_; i++) {
            l_.addAllElts(_hand.getCardsByLengthSortedByStrength(reversed, i));
        }
        return l_;
    }

    public EqList<HandPresident> getCardsSortedByLengthSortedByStrengthReduce(HandPresident _hand) {
        EqList<HandPresident> l_ = new EqList<HandPresident>();
        int nbMaxLen_ = rules.getNbStacks() * NB_SUITS;
        for (int i = CustList.SECOND_INDEX; i <= nbMaxLen_; i++) {
            HandPresident hLoc_ = new HandPresident();
            for (HandPresident h: _hand.getCardsByLengthSortedByStrength(reversed, i)) {
                hLoc_.ajouterCartes(h);
            }
            l_.add(hLoc_);
        }
        return l_;
    }

    boolean dominantHandProgressTrick(HandPresident _h, TreeMap<CardPresident, Byte> _playedCards) {
        HandPresident c_ = new HandPresident(_h);
        ByteTreeMap<HandPresident> gl_ = _h.getCardsByStrength(reversed);
        EqList<HandPresident> rep_ = getCardsSortedByLengthSortedByStrengthReduce(c_);
        EqList<HandPresident> hWorst_ = new EqList<HandPresident>();
        EqList<HandPresident> hBest_ = new EqList<HandPresident>();
        int maxStack_ = rules.getNbStacks() * NB_SUITS;
        byte avg_ = GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH;
        for (int i = CustList.SECOND_INDEX; i <= maxStack_; i++) {
            HandPresident repLoc_ = new HandPresident(rep_.get(i-1));
            for (HandPresident h: c_.getCardsByStrength(reversed).values()) {
                if (h.estVide()) {
                    continue;
                }
                if (dominantGroup(h, 1, _playedCards, gl_)) {
                    repLoc_.ajouterCartes(h);
                }
            }
            for (HandPresident h: repLoc_.getCardsByStrength(reversed).values()) {
                if (h.estVide()) {
                    continue;
                }
                if (dominantGroup(h, i, _playedCards, gl_)) {
                    int nbGroup_ = h.total() / i;
                    int index_ = CustList.FIRST_INDEX;
                    for (int j = CustList.FIRST_INDEX; j < nbGroup_; j++) {
                        HandPresident hBestCards_ = new HandPresident();
                        for (int k = CustList.FIRST_INDEX; k < i; k++) {
                            hBestCards_.ajouter(h.carte(index_));
                            index_++;
                        }
                        hBest_.add(hBestCards_);
                    }
                } else if (h.premiereCarte().strength(reversed) < avg_) {
                    hWorst_.add(h);
                }
            }
            if (hWorst_.size() > hBest_.size()) {
                return false;
            }
            int m_ = Math.min(hWorst_.size(), hBest_.size());
            for (int j = CustList.FIRST_INDEX; j < m_; j++) {
                c_.supprimerCartes(hBest_.get(j));
                c_.supprimerCartes(hWorst_.get(j));
            }
            if (c_.estVide()) {
                if (!hWorst_.isEmpty()) {
                    return true;
                }
            }
            hBest_.clear();
            hWorst_.clear();
            rep_ = getCardsSortedByLengthSortedByStrengthReduce(c_);
        }
        if (c_.estVide()) {
            return false;
        }
        EqList<HandPresident> cardsLists_ = new EqList<HandPresident>();
        for (HandPresident h: c_.getCardsByStrength(reversed).values()) {
            if (h.estVide()) {
                continue;
            }
            if (h.premiereCarte().strength(reversed) < avg_) {
                cardsLists_.add(h);
            }
        }
        if (cardsLists_.size() >= CustList.ONE_ELEMENT) {
            return false;
        }
        return true;
    }

    HandPresident dominantHand(HandPresident _h, TreeMap<CardPresident, Byte> _playedCards) {
        HandPresident c_ = new HandPresident(_h);
        ByteTreeMap<HandPresident> gl_ = _h.getCardsByStrength(reversed);
        EqList<HandPresident> rep_ = getCardsSortedByLengthSortedByStrengthReduce(c_);
        EqList<HandPresident> hWorst_ = new EqList<HandPresident>();
        EqList<HandPresident> hBest_ = new EqList<HandPresident>();
        int maxStack_ = rules.getNbStacks() * NB_SUITS;
        byte avg_ = GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH;
        for (int i = CustList.SECOND_INDEX; i <= maxStack_; i++) {
            HandPresident repLoc_ = new HandPresident(rep_.get(i-1));
            for (HandPresident h: c_.getCardsByStrength(reversed).values()) {
                if (h.estVide()) {
                    continue;
                }
                if (dominantGroup(h, 1, _playedCards, gl_)) {
                    repLoc_.ajouterCartes(h);
                }
            }
            for (HandPresident h: repLoc_.getCardsByStrength(reversed).values()) {
                if (h.estVide()) {
                    continue;
                }
                if (dominantGroup(h, i, _playedCards, gl_)) {
                    int nbGroup_ = h.total() / i;
                    int index_ = CustList.FIRST_INDEX;
                    for (int j = CustList.FIRST_INDEX; j < nbGroup_; j++) {
                        HandPresident hBestCards_ = new HandPresident();
                        for (int k = CustList.FIRST_INDEX; k < i; k++) {
                            hBestCards_.ajouter(h.carte(index_));
                            index_++;
                        }
                        hBest_.add(hBestCards_);
                    }
                } else if (h.premiereCarte().strength(reversed) < avg_) {
                    hWorst_.add(h);
                }
            }
            if (hWorst_.size() > hBest_.size() + 1) {
                return new HandPresident();
            }
            int m_ = Math.min(hWorst_.size(), hBest_.size());
            for (int j = CustList.FIRST_INDEX; j < m_; j++) {
                c_.supprimerCartes(hBest_.get(j));
                c_.supprimerCartes(hWorst_.get(j));
            }
            if (c_.estVide()) {
                if (!hWorst_.isEmpty()) {
                    return hWorst_.first();
                }
            }
            hBest_.clear();
            hWorst_.clear();
            rep_ = getCardsSortedByLengthSortedByStrengthReduce(c_);
        }
        if (c_.estVide()) {
            return c_;
        }
        EqList<HandPresident> cardsLists_ = new EqList<HandPresident>();
        for (HandPresident h: c_.getCardsByStrength(reversed).values()) {
            if (h.estVide()) {
                continue;
            }
            if (h.premiereCarte().strength(reversed) < avg_) {
                cardsLists_.add(h);
            }
        }
        if (cardsLists_.size() > CustList.ONE_ELEMENT) {
            return new HandPresident();
        }
        if (cardsLists_.size() == CustList.ONE_ELEMENT) {
            return cardsLists_.last();
        }
        return c_.getCardsByStrength(reversed).values().first();
    }

    boolean dominantGroup(HandPresident _h, int _nb, TreeMap<CardPresident, Byte> _playedCards, ByteTreeMap<HandPresident> _rep) {
        int maxStack_ = rules.getNbStacks() * NB_SUITS;
        if (_h.premiereCarte().strength(reversed) == CardPresident.getMaxStrength(reversed)) {
            return true;
        }
        int str_ = _h.premiereCarte().strength(reversed);
        if (rules.getEqualty() == EqualtyPlaying.FORBIDDEN) {
            for (CardPresident c: _playedCards.getKeys()) {
                byte strLoc_ = c.strength(reversed);
                if (strLoc_ <= str_) {
                    break;
                }
                byte rem_ = (byte) (maxStack_ - _playedCards.getVal(c) - _rep.getVal(strLoc_).total());
                if (rem_ >= _nb) {
                    return false;
                }
            }
            return true;
        }
        for (CardPresident c: _playedCards.getKeys()) {
            byte strLoc_ = c.strength(reversed);
            if (strLoc_ < str_) {
                break;
            }
            byte rem_ = (byte) (maxStack_ - _playedCards.getVal(c) - _rep.getVal(strLoc_).total());
            if (rem_ >= _nb) {
                return false;
            }
        }
        return true;
    }

    public HandPresident mainUtilisateurTriee(DisplayingPresident _regles) {
        HandPresident main_ = new HandPresident();
        main_.ajouterCartes(getDistribution().main());
        main_.sortCards(_regles.getDecroissant(), reversed);
        return main_;
    }

    public HandPresident mainUtilisateurTriee(HandPresident _hand,DisplayingPresident _regles) {
        HandPresident main_ = new HandPresident();
        main_.ajouterCartes(_hand);
        main_.sortCards(_regles.getDecroissant(), reversed);
        return main_;
    }

    public void restituerMainsDepartRejouerDonne(CustList<TrickPresident> _plisFaits,
            byte _nombreJoueurs) {
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            getDistribution().main(joueur_).supprimerCartes();
        }
        for (TrickPresident pli_ : _plisFaits) {
            int index_ = CustList.FIRST_INDEX;
            for (HandPresident carte_ : pli_) {
                getDistribution().main(pli_.getPlayer(index_, getNombreDeJoueurs())).ajouterCartes(carte_);
                index_++;
            }
        }
        revertGifts();
    }

    public boolean isReversed() {
        return reversed;
    }

    public boolean allowSwitchCards() {
        return rules.isSwitchCards();
    }

    /** Renvoie le nombre de joueurs jouant a la partie */
    public byte getNombreDeJoueurs() {
        return (byte) rules.getNbPlayers();
    }

    public RulesPresident getRegles() {
        return rules;
    }

    public Bytes getNewRanks() {
        Bytes r_ = new Bytes();
        Ints t_ = new Ints();
        Ints c_ = new Ints();
        byte nbPlayers_ = getNombreDeJoueurs();
        DealPresident deal_ = new DealPresident(deal);
        for (TrickPresident t: tricks) {
            int index_ = CustList.FIRST_INDEX;
            for (HandPresident c: t) {
                byte player_ = t.getPlayer(index_, nbPlayers_);
                deal_.main(player_).ajouterCartes(c);
                index_++;
            }
        }
        for (byte p = CustList.FIRST_INDEX; p <nbPlayers_; p++) {
            t_.add((int)CustList.INDEX_NOT_FOUND_ELT);
            c_.add((int)CustList.INDEX_NOT_FOUND_ELT);
            r_.add(CustList.FIRST_INDEX);
        }
        for (byte p = CustList.FIRST_INDEX; p <nbPlayers_; p++) {
            int tInd_ = tricks.size() - 1;
            while (true) {
                TrickPresident curTrick_ = tricks.get(tInd_);
                Ints cInd_ = curTrick_.getFilledHandsIndexesBefore(curTrick_.total());
                Ints cIndPl_ = new Ints();
                for (int i: cInd_) {
                    byte curPlayer_ = curTrick_.getPlayer(i, nbPlayers_);
                    if (curPlayer_ == p) {
                        cIndPl_.add(i);
                    }
                }
                if (!cIndPl_.isEmpty()) {
                    t_.set(p, tInd_);
                    c_.set(p, cIndPl_.last());
                    break;
                }
                tInd_--;
            }
        }
        Bytes normalEnds_ = new Bytes();
        Bytes eStrongestCards_ = new Bytes();
        if (rules.isLoosingIfFinishByBestCards()) {
            for (byte p = CustList.FIRST_INDEX; p <nbPlayers_; p++) {
                CustList<TrickPresident> tricks_ = tricks.sub(CustList.FIRST_INDEX, t_.get(p) + 1);
                boolean reversed_ = false;
                if (rules.isPossibleReversing()) {
                    int nbFullHands_ = CustList.SIZE_EMPTY;
                    for (TrickPresident t: tricks_) {
                        for (HandPresident h: t) {
                            if (h.total() == NB_SUITS * rules.getNbStacks()) {
                                nbFullHands_++;
                            }
                        }
                    }
                    reversed_ = nbFullHands_ % 2 == 1;
                }
                TrickPresident curTrick_ = tricks.get(t_.get(p));
                HandPresident curHand_ = curTrick_.carte(c_.get(p));
                if (curHand_.premiereCarte().strength(reversed_) == CardPresident.getMaxStrength(reversed_)) {
                    boolean existMin_ = false;
                    for (CardPresident c: deal_.main(p)) {
                        if (c.strength(reversed_) < CardPresident.getMaxStrength(reversed_)) {
                            existMin_ = true;
                            break;
                        }
                    }
                    if (existMin_) {
                        eStrongestCards_.add(p);
                    }
                }
            }
        }
        for (byte p = CustList.FIRST_INDEX; p <nbPlayers_; p++) {
            if (eStrongestCards_.containsObj(p)) {
                continue;
            }
            normalEnds_.add(p);
        }
        Bytes tricksCardsPlayers_;
        tricksCardsPlayers_ = new Bytes();
        tricksCardsPlayers_.addAllElts(getTricksCardsPlayers(normalEnds_, t_, c_));
        tricksCardsPlayers_.addAllElts(getTricksCardsPlayers(eStrongestCards_, t_, c_));
        byte curRank_ = CustList.SECOND_INDEX;
        for (byte p: tricksCardsPlayers_) {
            r_.set(p, curRank_);
            curRank_++;
        }
        return r_;
    }

    static Bytes getTricksCardsPlayers(Bytes _players, Ints _t, Ints _c) {
        IntTreeMap< Bytes> tricksPlayers_;
        tricksPlayers_ = new IntTreeMap< Bytes>();
        for (byte p : _players) {
            int noTrick_ = _t.get(p);
            if (tricksPlayers_.contains(noTrick_)) {
                tricksPlayers_.getVal(noTrick_).add(p);
            } else {
                tricksPlayers_.put(noTrick_, new Bytes(p));
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

    public BooleanList getPassOrFinish() {
        return passOrFinish;
    }

    public DealPresident getDistribution() {
        return deal;
    }

    public GameType getType() {
        return type;
    }

    public Bytes getRanks() {
        return ranks;
    }

    public ByteMap<HandPresident> getSwitchedCards() {
        return switchedCards;
    }

    HandPresident getCards() {
        HandPresident nb_ = new HandPresident();
        for (TrickPresident t: tricks) {
            for (HandPresident h: t) {
                nb_.ajouterCartes(h);
            }
        }
        for (HandPresident h: deal) {
            nb_.ajouterCartes(h);
        }
        for (HandPresident h: progressingTrick) {
            nb_.ajouterCartes(h);
        }
        nb_.sortCardsBegin();
        return nb_;
    }

    public void copySwitchCards(ByteMap<HandPresident> _switchedCards) {
        switchedCards = new ByteMap<HandPresident>();
        for (byte k: _switchedCards.getKeys()) {
            switchedCards.put(k, new HandPresident(_switchedCards.getVal(k)));
        }
    }

    public HandPresident getPlayedCards() {
        return playedCards;
    }

    public String getReason() {
        return reason;
    }

    public ByteMap<Playing> getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus() {
        lastStatus.clear();
        byte nbPlayers_ = getNombreDeJoueurs();
        for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
            if (deal.main(p).estVide()) {
                lastStatus.put(p, Playing.FINISH);
                continue;
            }
            if (passOrFinish.get(p)) {
                lastStatus.put(p, Playing.PASS);
                continue;
            }
            if (rules.getEqualty() == EqualtyPlaying.NO_SKIP) {
                lastStatus.put(p, Playing.CAN_PLAY);
                continue;
            }
            Ints indexes_ = progressingTrick.getPlayedCardsIndexes(p, nbPlayers_);
            if (progressingTrick.getPlayer(progressingTrick.total(), nbPlayers_) != p) {
                if (!indexes_.isEmpty()) {
                    HandPresident lastHand_ = progressingTrick.carte(indexes_.last());
                    if (!lastHand_.estVide()) {
                        lastStatus.put(p, Playing.CAN_PLAY);
                        continue;
                    }
                    if (rules.getEqualty() == EqualtyPlaying.SKIP_ALWAYS_NEXT) {
                        lastStatus.put(p, Playing.SKIPPED);
                        continue;
                    }
                    lastStatus.put(p, Playing.DO_NOT_EQUAL);
                    continue;
                }
                lastStatus.put(p, Playing.CAN_PLAY);
                continue;
            }
            lastStatus.put(p, getStatus(p));
        }
    }

    public void setNombre() {
        number++;
    }

    public long getNombre() {
        return number;
    }

    public CustList<TrickPresident> getTricks() {
        return tricks;
    }

    void setNextPlayer(byte _i) {
        nextPlayer = _i;
    }

    void setReversed(boolean _b) {
        reversed = _b;
    }

    public CustList<CustList<EqList<HandPresident>>> getUserHands() {
        return userHands;
    }

    public CustList<CustList<TrickPresident>> getDealTricks() {
        return dealTricks;
    }

    public CustList<Bytes> getRanksDeals() {
        return ranksDeals;
    }

    public CustList<CustList<IntMap<ByteMap<Playing>>>> getLastStatusDeals() {
        return lastStatusDeals;
    }

    public CustList<CustList<IntMap<Byte>>> getNextPlayerDeals() {
        return nextPlayerDeals;
    }

    public CustList<ByteMap<HandPresident>> getSwitchedCardsDeals() {
        return switchedCardsDeals;
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

    public void setSwitchedCards(ByteMap< HandPresident> _switchedCards) {
        switchedCards = _switchedCards;
    }

    public String getError() {
        return error;
    }

    public void setError(String _error) {
        error = _error;
    }
}
