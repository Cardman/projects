package cards.president;
import java.util.concurrent.atomic.AtomicInteger;

import cards.consts.GameType;
import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import code.format.Format;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.TreeMap;


public final class GamePresident {

    private static final String GAME_PRESIDENT = "cards.president.gamepresident";
    private static final String FOLDER = "resources_cards/classes";

    private static final String EMPTY = "";

    private static final String RETURN_LINE = "\n";

    private static final String SKIPPED = "skipped";

    private static final String HAVE_PASSED = "havePassed";

    private static final String HAS_TO_EQUAL_OR_SKIP = "hasToEqualOrSkip";

    private static final String CANNOT_USE_LOWER_OR_EQ = "cannotUseLowerOrEq";

    private static final String CANNOT_USE_LOWER = "cannotUseLower";

    private static final String HAVE_PLAY_GIVEN_NUMBER_CARDS = "hasPlayGivenNumberCards";

    private static final String CANNOT_PASS = "cannotPass";

    private static final int PERCENT_MAX = 100;

    private static final int NB_SUITS = 4;

    private AtomicInteger chargementSimulation = new AtomicInteger();

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
    private Numbers<Short> scores = new Numbers<Short>();
    /** Nombre de fois qu'a ete joue la partie(partie fini) */
    private long number;
    private RulesPresident rules = new RulesPresident();

    private Numbers<Byte> ranks = new Numbers<Byte>();

    private NumberMap<Byte,HandPresident> switchedCards = new NumberMap<Byte,HandPresident>();

    private String error = "";
    private boolean reversed;

    private BooleanList passOrFinish = new BooleanList();

    private HandPresident cardsToBePlayed = new HandPresident();

    private byte nextPlayer = CustList.INDEX_NOT_FOUND_ELT;

    private NumberMap<Byte,Playing> lastStatus = new NumberMap<Byte,Playing>();

    private StringBuilder errorPlaying = new StringBuilder();

    private HandPresident playedCards = new HandPresident();

    private String reason = EMPTY;

    private final String file = Format.getClassProperties(GAME_PRESIDENT);

    private CustList<CustList<EqList<HandPresident>>> userHands = new CustList<CustList<EqList<HandPresident>>>();

    private CustList<EqList<HandPresident>> userHandsPerDeal = new CustList<EqList<HandPresident>>();

    private EqList<HandPresident> currentUserHands = new EqList<HandPresident>();

    private CustList<CustList<TrickPresident>> dealTricks = new CustList<CustList<TrickPresident>>();

    private CustList<Numbers<Byte>> ranksDeals = new CustList<Numbers<Byte>>();

    private NumberMap<Integer,NumberMap<Byte,Playing>> lastStatusTrick = new NumberMap<Integer,NumberMap<Byte,Playing>>();

    private CustList<NumberMap<Integer,NumberMap<Byte,Playing>>> lastStatusDeal = new CustList<NumberMap<Integer,NumberMap<Byte,Playing>>>();

    private CustList<CustList<NumberMap<Integer,NumberMap<Byte,Playing>>>> lastStatusDeals = new CustList<CustList<NumberMap<Integer,NumberMap<Byte,Playing>>>>();

    private NumberMap<Integer,Byte> nextPlayerTrick = new NumberMap<Integer,Byte>();

    private CustList<NumberMap<Integer,Byte>> nextPlayerDeal = new CustList<NumberMap<Integer,Byte>>();

    private CustList<CustList<NumberMap<Integer,Byte>>> nextPlayerDeals = new CustList<CustList<NumberMap<Integer,Byte>>>();

    private CustList<NumberMap<Byte,HandPresident>> switchedCardsDeals = new CustList<NumberMap<Byte,HandPresident>>();

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
    public GamePresident(GameType _type, DealPresident _donne, RulesPresident _regles, Numbers<Byte> _ranks) {
        type = _type;
        deal = _donne;
        rules = _regles;
        lastStatus = new NumberMap<Byte,Playing>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i = CustList.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.add((short) 0);
            passOrFinish.add(false);
        }
        setLastStatus();
        ranks = new Numbers<Byte>(_ranks);
        byte leader_ = getFirstLeader();
        progressingTrick.setEntameur(leader_);
        nextPlayer = leader_;
        currentUserHands = new EqList<HandPresident>();
        simulated = false;
    }

    public void initPartie() {
        progressingTrick = new TrickPresident(getFirstLeader());
        nextPlayer = progressingTrick.getEntameur();
        lastStatus = new NumberMap<Byte,Playing>();
        byte nombreJoueurs_ = getNombreDeJoueurs();
        for (int i = CustList.FIRST_INDEX; i < nombreJoueurs_; i++) {
            scores.set(i, (short) 0);
            passOrFinish.set(i, false);
        }
        setLastStatus();
        cardsToBePlayed = new HandPresident();
        errorPlaying = new StringBuilder();
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
        lastStatus = new NumberMap<Byte,Playing>();
        setLastStatus();
        currentUserHands = new EqList<HandPresident>();
        simulated = false;
    }

    public boolean availableSwitchingCards() {
        return !ranks.isEmpty()&&allowSwitchCards();
    }

    public void initCartesEchanges() {
        if(!ranks.isEmpty()&&allowSwitchCards()) {
//            NatTreeMap<Byte,Byte> players_ = new NatTreeMap<Byte,Byte>();
//            int r_ = ranks.size();
//            for (byte i = CustList.FIRST_INDEX; i < r_; i++) {
//                players_.put(ranks.get(i), i);
//            }
            for(byte p=0;p<getNombreDeJoueurs();p++) {
                switchedCards.put(p, new HandPresident());
            }
        }
    }

    public void simulate(int _nbTimes, String _lg) {
//        byte nombreJoueurs_ = getNombreDeJoueurs();
//        byte donneur_ = getDistribution().getDonneur();
        simulated = true;
        chargementSimulation.set(CustList.SIZE_EMPTY);
        userHands.clear();
        currentUserHands.clear();
        dealTricks.clear();
        ranksDeals.clear();
        lastStatusDeals.clear();
        nextPlayerDeals.clear();
        switchedCardsDeals.clear();
        int noDeal_ = CustList.SIZE_EMPTY;
        int nbPlayedCardsCount_ = _nbTimes * HandPresident.pileBase().total();
        int nbPlayedCards_ = CustList.SIZE_EMPTY;
        while (noDeal_ < _nbTimes) {
            lastStatusDeal = new CustList<NumberMap<Integer,NumberMap<Byte,Playing>>>();
            nextPlayerDeal = new CustList<NumberMap<Integer,Byte>>();
            lastStatusTrick = new NumberMap<Integer,NumberMap<Byte,Playing>>();
            lastStatusTrick.put(-1, new NumberMap<Byte,Playing>(lastStatus));
            currentUserHands = new EqList<HandPresident>();
            userHandsPerDeal = new CustList<EqList<HandPresident>>();
            currentUserHands.add(new HandPresident(deal.main()));
            initCartesEchanges();
            donnerMeilleuresCartes();
            currentUserHands.add(new HandPresident(deal.main()));
            giveWorstCards();
            NumberMap<Byte,HandPresident> switchedCards_ = new NumberMap<Byte,HandPresident>();
            for (byte p: switchedCards.getKeys()) {
                switchedCards_.put(p, new HandPresident(switchedCards.getVal(p)));
            }
            switchedCardsDeals.add(switchedCards_);
            currentUserHands.add(new HandPresident(deal.main()));
            userHandsPerDeal.add(currentUserHands);
//            currentUserHands = new CustList<>();
            byte leader_ = getFirstLeader();
            nextPlayerTrick = new NumberMap<Integer,Byte>();
            nextPlayerTrick.put(-1, leader_);
            progressingTrick = new TrickPresident(leader_);
            passOrFinish = passOrFinish();
//            byte player_ = leader_;
            nextPlayer = leader_;
            while (true) {
//                if (progressingTrick.estVide()) {
//                    player_ = progressingTrick.getEntameur();
//                } else {
//                    player_ = nextPlayer;
//                }
//                if (progressingTrick.estVide()) {
//                    player_ = progressingTrick.getEntameur();
//                    starter = player_;
//                } else {
//                    player_ = (byte)((player_ + 1) % getNombreDeJoueurs());
//                }
                HandPresident h_ = playedCards(_lg);
                nbPlayedCards_ += h_.total();
                chargementSimulation.set(PERCENT_MAX * nbPlayedCards_ / nbPlayedCardsCount_);
//                addCardsToCurrentTrick(player_, h_);
                addCardsToCurrentTrickAndLoop(nextPlayer, h_);
//                lastStatus_.add(new Map<>(lastStatus));
                if (!keepPlayingCurrentGame()) {
                    break;
                }
//                player_ = nextPlayer;
            }
            nextPlayerDeals.add(nextPlayerDeal);
            lastStatusDeals.add(lastStatusDeal);
            userHands.add(userHandsPerDeal);
            if (!ranks.isEmpty()) {
                ranksDeals.add(ranks);
            }
            dealTricks.add(tricks);
            Numbers<Byte> ranks_ = getNewRanks();
            HandPresident stackNext_ = empiler();
            byte dealer_ = getDistribution().getDonneur();
            deal = new DealPresident(noDeal_ + 1, stackNext_);
            deal.donneurSuivant(dealer_,rules);
            deal.initDonne(rules);
            tricks = new CustList<TrickPresident>();
            ranks = new Numbers<Byte>(ranks_);
            setLastStatus();
            noDeal_++;
        }
        ranksDeals.add(ranks);
        chargementSimulation.set(PERCENT_MAX);
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
            byte min_ = ranks.getMaximum();
            int pl_ = ranks.indexOfObj(min_);
            leader_ = (byte) pl_;
        }
        return leader_;
    }

    public int getFilledTricksCount() {
        int nb_ = CustList.SIZE_EMPTY;
        for (TrickPresident t: tricks) {
            if (t.getNombreDeCartesParJoueur() > CustList.SIZE_EMPTY) {
                nb_++;
            }
        }
        return nb_;
    }

    public int getFilledTricksIndex(int _index) {
        int ret_ = CustList.FIRST_INDEX;
        int in_ = CustList.FIRST_INDEX;
        for (TrickPresident t: tricks) {
            if (t.getNombreDeCartesParJoueur() == CustList.SIZE_EMPTY) {
                ret_++;
                continue;
            }
            if (in_ == _index) {
                return ret_;
            }
            in_++;
            ret_++;
        }
        return CustList.INDEX_NOT_FOUND_ELT;
    }

    public byte numberGivenCards(byte _player) {
        if (getWinners().containsObj(_player)) {
            int ind_ = getWinners().indexOfObj(_player);
            return (byte) (nombresCartesEchangesMax() - ind_);
        }
//        if (getLoosers().containsObj(_player)) {
//            int ind_ = getLoosers().indexOfObj(_player);
//            return (byte) (nombresCartesEchangesMax() - ind_);
//        }
        return CustList.SIZE_EMPTY;
    }

    public byte nombresCartesEchangesMax() {
        if (getNombreDeJoueurs() <= 3) {
            return 1;
        }
        return 2;
    }

    public Numbers<Byte> getWinners() {
        if (ranks.isEmpty()) {
            return new Numbers<Byte>();
        }
        NatTreeMap<Byte,Byte> players_ = new NatTreeMap<Byte,Byte>();
        int r_ = ranks.size();
        for (byte i = CustList.FIRST_INDEX; i < r_; i++) {
            players_.put(ranks.get(i), i);
        }
        Numbers<Byte> w_ = new Numbers<Byte>();
        int nb_ = nombresCartesEchangesMax();
        for (byte i = CustList.FIRST_INDEX; i < nb_; i++) {
            w_.add(players_.getValue(i));
        }
        return w_;
    }

    public Numbers<Byte> getLoosers() {
        if (ranks.isEmpty()) {
            return new Numbers<Byte>();
        }
        NatTreeMap<Byte,Byte> players_ = new NatTreeMap<Byte,Byte>();
        int r_ = ranks.size();
        for (byte i = CustList.FIRST_INDEX; i < r_; i++) {
            players_.put(ranks.get(i), i);
        }
        Numbers<Byte> l_ = new Numbers<Byte>();
        int nb_ = nombresCartesEchangesMax();
        int i_ = players_.size() - 1;
        while (l_.size() < nb_) {
            l_.add(players_.getValue(i_));
            i_--;
        }
        return l_;
    }

    public static Numbers<Byte> getWinners(Numbers<Byte> _ranks, int _nbMax) {
        if (_ranks.isEmpty()) {
            return new Numbers<Byte>();
        }
        NatTreeMap<Byte,Byte> players_ = new NatTreeMap<Byte,Byte>();
        int r_ = _ranks.size();
        for (byte i = CustList.FIRST_INDEX; i < r_; i++) {
            players_.put(_ranks.get(i), i);
        }
        Numbers<Byte> w_ = new Numbers<Byte>();
        int nb_ = _nbMax;
        for (byte i = CustList.FIRST_INDEX; i < nb_; i++) {
            w_.add(players_.getValue(i));
        }
        return w_;
    }

    public static Numbers<Byte> getLoosers(Numbers<Byte> _ranks, int _nbMax) {
        if (_ranks.isEmpty()) {
            return new Numbers<Byte>();
        }
        NatTreeMap<Byte,Byte> players_ = new NatTreeMap<Byte,Byte>();
        int r_ = _ranks.size();
        for (byte i = CustList.FIRST_INDEX; i < r_; i++) {
            players_.put(_ranks.get(i), i);
        }
        Numbers<Byte> l_ = new Numbers<Byte>();
        int nb_ = _nbMax;
        int i_ = players_.size() - 1;
        while (l_.size() < nb_) {
            l_.add(players_.getValue(i_));
            i_--;
        }
        return l_;
    }

//    public void remplirCartesEchanges(HandPresident _main,byte _joueur) {
//        switchedCards.put(_joueur, _main);
//    }

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

    public Numbers<Byte> getLoosers(Numbers<Byte> _humanPlayers) {
        Numbers<Byte> l_ = new Numbers<Byte>();
        for (byte w: getLoosers()) {
            if (_humanPlayers.containsObj(w)) {
                l_.add(w);
            }
        }
        return l_;
    }

    public Numbers<Byte> getWinners(Numbers<Byte> _humanPlayers) {
        Numbers<Byte> l_ = new Numbers<Byte>();
        for (byte w: getWinners()) {
            if (_humanPlayers.containsObj(w)) {
                l_.add(w);
            }
        }
        return l_;
    }

    public static byte getMatchingWinner(Numbers<Byte> _winners, Numbers<Byte> _losers, byte _loser) {
        int ind_ = _losers.indexOfObj(_loser);
        if (ind_ == CustList.INDEX_NOT_FOUND_ELT) {
            return (byte) ind_;
        }
        return _winners.get(ind_);
    }

    public static byte getMatchingLoser(Numbers<Byte> _winners, Numbers<Byte> _losers, byte _winner) {
        int ind_ = _winners.indexOfObj(_winner);
        if (ind_ == CustList.INDEX_NOT_FOUND_ELT) {
            return (byte) ind_;
        }
        return _losers.get(ind_);
    }

    public byte getMatchingWinner(byte _loser) {
        int ind_ = getLoosers().indexOfObj(_loser);
        if (ind_ == CustList.INDEX_NOT_FOUND_ELT) {
            return (byte) ind_;
        }
        return getWinners().get(ind_);
    }

    public byte getMatchingLoser(byte _winner) {
        int ind_ = getWinners().indexOfObj(_winner);
        if (ind_ == CustList.INDEX_NOT_FOUND_ELT) {
            return (byte) ind_;
        }
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
    public void giveWorstCards(Numbers<Byte> _humanPlayers) {
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
    public boolean giveWorstCards(Numbers<Byte> _humanPlayers, byte _player, HandPresident _hand) {
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
//        for(byte p: switchedCards.getKeys()) {
//            getDistribution().main(p).ajouterCartes(switchedCards.getVal(p));
//        }
//        for (byte l: getLoosers()) {
//            int ind_= getLoosers().indexOfObj(l);
//            byte pl_ = getWinners().get(ind_);
//            getDistribution().main(l).supprimerCartes(switchedCards.getVal(pl_));
//        }
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
//        HandPresident nouvelle_main=new HandPresident();
//        HandPresident nouvelle_main_2=new HandPresident();
//        CustList<MainPresident> repartition=main_joueur.couleurs();
        byte gifts_ =(byte) (nombresCartesEchangesMax() - getWinners().indexOfObj(_joueur));
//        int min=main_joueur.total();
//        int somme=0;
//        int milieu=main_joueur.total()/2;
        byte mid_ = CardPresident.getAvgStrength(reversed);
        EqList<HandPresident> rep_ = getCardsSortedByLengthSortedByStrength(hPlayer_);
        int index_ = CustList.FIRST_INDEX;
        while (h_.total() < gifts_) {
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
            h_.ajouterCartes(hLoc_);
            index_++;
        }
        if (h_.total() == gifts_) {
            return h_;
        }
        h_ = new HandPresident();
        index_ = CustList.FIRST_INDEX;
        while (h_.total() < gifts_) {
            HandPresident hLoc_ = rep_.get(index_);
            if (hLoc_.total() + h_.total() >= gifts_) {
                int nbCards_ = gifts_ - h_.total();
                for (int i = CustList.FIRST_INDEX; i < nbCards_; i++) {
                    h_.ajouter(hLoc_.carte(i));
                }
                break;
            }
            h_.ajouterCartes(hLoc_);
            index_++;
        }
//        for(int indice_valeur=0;indice_valeur<repartition.size();indice_valeur++)
//        {
//            if(somme>milieu)
//            {
//                min=indice_valeur;
//                break;
//            }
//            somme+=repartition.get(indice_valeur).total();
//        }
//        for(int indice_valeur=min;indice_valeur<repartition.size();indice_valeur++)
//        {
//            repartition_basse.addElement(repartition.get(indice_valeur));
//        }
//        trier_nombre_valeur(repartition_basse);
//        for(HandPresident main_p:repartition_basse)
//        {
//            nouvelle_main_2.ajouterCartes(main_p);
//        }
//        raison[0]="Donnez vos simples d'abord, puis les doubles,etc.";
//        if(repartition_basse.size()>=nombre_carte_donner)
//        {//On donne d'abord les simples les plus petits possibles puis les doubles,...
//            for(int nombre=0;nombre<nombre_carte_donner;nombre--)
//            {
//                main.ajouter(nouvelle_main_2.carte(nouvelle_main.total()-nombre-1));
//            }
//            return main;
//        }
//        //Par defaut
//        for(int indice_valeur=0;indice_valeur<repartition.size();indice_valeur++)
//        {
//            nouvelle_main.ajouterCartes(repartition.get(indice_valeur));
//        }
//        for(int nombre=0;nombre<nombre_carte_donner;nombre--)
//        {
//            main.ajouter(nouvelle_main.carte(nouvelle_main.total()-nombre-1));
//        }
        return h_;
    }

    public byte getNextPlayer() {
        return nextPlayer;
    }

    public byte getNextPlayer(byte _player) {
        return (byte)((_player + 1) % getNombreDeJoueurs());
    }

    public boolean currentPlayerHasPlayed(byte _player, String _lg) {
        Numbers<Byte> players_ = progressingTrick.getPlayers();
        if (!players_.isEmpty()) {
            byte lastPlayer_ = players_.last();
            byte nextPlayer_ = (byte) ((lastPlayer_ + 1) % getNombreDeJoueurs());
            if (nextPlayer_ != _player) {
                return true;
            }
        }
        addCardsToCurrentTrick(_player, _lg);
        return false;
    }

    public void addCardsToCurrentTrick(byte _player, String _lg) {
        HandPresident h_ = playedCards(_lg);
        playedCards = h_;
        addCardsToCurrentTrickAndLoop(_player, h_);
    }

    public void addCardsToCurrentTrick(byte _player, CardPresident _card, byte _nb) {
        HandPresident h_ = playHand(_player, _card, _nb);
        playedCards = h_;
        addCardsToCurrentTrickAndLoop(_player, h_);
    }

//    public void passTrick(byte _player) {
//        passOrFinish.set(_player, true);
//        noEqual(_player);
//    }

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
//            progressingTrick.ajouter(_hand, _player);
        }
        if (_hand.estVide() && playingStatus_ == Playing.HAS_TO_EQUAL) {
            lastStatus.put(_player, Playing.DO_NOT_EQUAL);
//            progressingTrick.ajouter(_hand, _player);
        }
//        if (!_hand.estVide() && playingStatus_ == Playing.CAN_PLAY) {
//            lastStatus.put(_player, Playing.CAN_PLAY);
//        }
        if (!_hand.estVide()) {
            lastStatus.put(_player, Playing.CAN_PLAY);
        }
        if (getDistribution().main(_player).total() == _hand.total()) {
            lastStatus.put(_player, Playing.FINISH);
            passOrFinish.set(_player, true);
        }
        putLastStatusSimu(progressingTrick.total());
        addCardsToCurrentTrick(_player, _hand);
//        addUserHandSimulation(_player);
        byte nbPlayers_ = getNombreDeJoueurs();
//        if (!keepPlayingCurrentTrick() || progressingTrick.estVide()) {
//            for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
//                passOrFinish.set(p, getDistribution().main(p).estVide());
//            }
//            return;
//        }
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
//            if (pl_ == _player) {
//                nextPlayer = pl_;
//                return;
//            }
//            if (pl_ == _player) {
//                finishDirectlyTrick(pl_, new HandPresident());
//                nextPlayer = progressingTrick.getEntameur();
//                for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
//                    passOrFinish.set(p, getDistribution().main(p).estVide());
//                }
//                return;
//            }
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
//            if (!keepPlayingCurrentTrick()) {
//                finishDirectlyTrick(pl_, new HandPresident());
//                nextPlayer = progressingTrick.getEntameur();
//                for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
//                    passOrFinish.set(p, getDistribution().main(p).estVide());
//                }
//                return;
//            }
            progressingTrick.ajouter(new HandPresident(), pl_);
//            addUserHandSimulation(pl_);
            pl_ = (byte) ((pl_ + 1) % getNombreDeJoueurs());
        }
        nextPlayer = pl_;
    }

    void putLastStatusSimu(int _key) {
        if (!simulated) {
            return;
        }
        nextPlayerTrick.put(_key, nextPlayer);
        lastStatusTrick.put(_key, new NumberMap<Byte,Playing>(lastStatus));
    }

//    void addUserHandSimulation(byte _player) {
//        if (!simulated) {
//            return;
//        }
//        if (_player == DealPresident.NUMERO_UTILISATEUR) {
//            currentUserHands.add(new HandPresident(deal.main()));
//        }
//    }

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
//            byte player_ = leader_;
            addEmptyTrick(leader_);
//            if (getDistribution().main(leader_).estVide()) {
//                progressingTrick = new TrickPresident(leader_);
//                for (byte p: rules.getSortedPlayersAfterEq(leader_)) {
////                    progressingTrick.ajouter(new HandPresident(), p);
////                    if (!getDistribution().main(p).estVide()) {
////                        pl_ = p;
////                        break;
////                    }
//                    if (getDistribution().main(p).estVide()) {
//                        progressingTrick.ajouter(new HandPresident(), p);
//                    } else {
//                        player_ = p;
//                        break;
//                    }
//                }
//                tricks.add(progressingTrick);
//            }
//            progressingTrick = new TrickPresident(player_);
            nextPlayer = progressingTrick.getEntameur();
            finishGame();
        }
    }

    void initializeNextSimuTrick() {
        if (!simulated) {
            return;
        }
        nextPlayerDeal.add(nextPlayerTrick);
        nextPlayerTrick = new NumberMap<Integer,Byte>();
        lastStatusDeal.add(lastStatusTrick);
        lastStatusTrick = new NumberMap<Integer,NumberMap<Byte,Playing>>();
//        userHandsPerDeal.add(currentUserHands);
//        currentUserHands = new CustList<HandPresident>();
    }

    private void finishGame() {
        Numbers<Byte> players_ = keepPlayingCurrentGameList();
        if (players_.size() != CustList.ONE_ELEMENT) {
            return;
        }
        byte p_ = players_.first();
        byte win_ = progressingTrick.getRamasseur(getNombreDeJoueurs());
        tricks.add(progressingTrick);
//        userHandsPerDeal.add(currentUserHands);
//        currentUserHands = new CustList<HandPresident>();
        addEmptyTrick(win_);
//        progressingTrick = new TrickPresident(win_);
//        for (byte o: rules.getSortedPlayersAfterEq(win_)) {
//            if (getDistribution().main(o).estVide()) {
//                progressingTrick.ajouter(new HandPresident(), o);
//            } else {
//                break;
//            }
//        }
//        tricks.add(progressingTrick);
//        progressingTrick = new TrickPresident(p);
        progressingTrick.ajouter(new HandPresident(getDistribution().main(p_)), p_);
        tricks.add(progressingTrick);
//        userHandsPerDeal.add(currentUserHands);
//        currentUserHands = new CustList<HandPresident>();
        getDistribution().main(p_).supprimerCartes();
        progressingTrick = new TrickPresident(p_);
//        for (Byte p: rules.getSortedPlayersAfter(starter)) {
//            if (!getDistribution().main(p).estVide()) {
//                byte win_ = progressingTrick.getRamasseur(getNombreDeJoueurs());
//                tricks.add(progressingTrick);
//                addEmptyTrick(win_);
////                progressingTrick = new TrickPresident(win_);
////                for (byte o: rules.getSortedPlayersAfterEq(win_)) {
////                    if (getDistribution().main(o).estVide()) {
////                        progressingTrick.ajouter(new HandPresident(), o);
////                    } else {
////                        break;
////                    }
////                }
////                tricks.add(progressingTrick);
////                progressingTrick = new TrickPresident(p);
//                progressingTrick.ajouter(new HandPresident(getDistribution().main(p)), p);
//                tricks.add(progressingTrick);
//                getDistribution().main(p).supprimerCartes();
//                progressingTrick = new TrickPresident(p);
//                break;
//            }
//        }
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
//            userHandsPerDeal.add(currentUserHands);
//            currentUserHands = new CustList<HandPresident>();
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

    public Numbers<Byte> keepPlayingCurrentGameList() {
        byte nbPlayers_ = getNombreDeJoueurs();
        Numbers<Byte> l_ = new Numbers<Byte>();
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
//        if (progressingTrick.total() < getNombreDeJoueurs()) {
//            return true;
//        }
        byte nb_ = getNombreDeJoueurs();
        byte nbPass_ = 0;
        for (byte p = CustList.FIRST_INDEX; p < nb_; p++) {
//            if (deal.main(p).estVide()) {
//                continue;
//            }
            if (passOrFinish.get(p)) {
                nbPass_++;
            }
        }
//        for (byte p = CustList.FIRST_INDEX; p < nb_; p++) {
//            CustList<Integer> indexes_ = progressingTrick.getPlayedCardsIndexes(p, nb_);
//            boolean pass_ = false;
//            for (Integer i: indexes_) {
//                HandPresident h_ = progressingTrick.carte(i);
//                if (!h_.estVide()) {
//                    continue;
//                }
//                CustList<HandPresident> filledHands_ = progressingTrick.getFilledHandsBefore(i, getNombreDeJoueurs());
//                if (filledHands_.size() <= CustList.ONE_ELEMENT) {
//                    pass_ = true;
//                    break;
//                }
//                HandPresident prev_ = filledHands_.last();
//                HandPresident befPrev_ = filledHands_.get(filledHands_.size() - 2);
//                if (prev_.premiereCarte().strength(reversed) == befPrev_.premiereCarte().strength(reversed)) {
//                    continue;
//                }
//                pass_ = true;
//                break;
//            }
//            if (pass_) {
//                nbPass_++;
//            }
//        }
        if (nbPass_ + 1 == nb_) {
            byte winner_ = progressingTrick.getRamasseur(nb_);
            return passOrFinish.get(winner_);
        }
        return nbPass_ < nb_;
//        byte winner_ = progressingTrick.getRamasseur(nb_);
//        if (!deal.main(winner_).estVide()) {
////            if (nbPass_ + 1 == nb_) {
////                return false;
////            }
////            return nbPass_ + 1 < nb_;
//            nbPass_++;
//        }
//        return nbPass_ < nb_;
//        return nbPass_ < nb_;
//        byte winner_ = progressingTrick.getRamasseur(nb_);
//        if (passOrFinish.get(winner_)) {
//            nbPass_--;
//        }
//        return nbPass_ < nb_;
//        int i_ = CustList.SECOND_INDEX;
//        int index_ = progressingTrick.total() - 1;
//        boolean finishedTrick_ = true;
//        while (i_ < getNombreDeJoueurs()) {
//            HandPresident h_ = progressingTrick.carte(index_);
//            if (!h_.estVide()) {
//                finishedTrick_ = false;
//                break;
//            }
//            index_--;
//            i_++;
//        }
//        return !finishedTrick_;
    }

    public boolean canPass(byte _player, String _loc) {
        errorPlaying = new StringBuilder();
        HandPresident playable_ = cartesJouables(_player, _loc);
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
            errorPlaying = new StringBuilder(Format.formatter(FOLDER, file, _loc, CANNOT_PASS, b_.premiereCarte().toString(_loc)));
            return false;
        }
        return true;
    }

    public boolean allowPlaying(byte _player, HandPresident _card, String _loc) {
        errorPlaying = new StringBuilder();
        cardsToBePlayed = new HandPresident(_card);
        HandPresident playable_ = cartesJouables(_player, _loc);
        return !playable_.getCardsByStrength(_card.premiereCarte().strength(reversed), reversed).estVide();
    }

    public boolean allowPlaying(byte _player, CardPresident _card, byte _nb, String _loc) {
        errorPlaying = new StringBuilder();
        cardsToBePlayed = playHand(_player, _card, _nb);
        HandPresident playable_ = cartesJouables(_player, _loc);
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

    HandPresident cartesJouables(byte _player, String _loc) {
        return cartesJouables(_player, getDistribution().main(_player), _loc);
    }

    HandPresident cartesJouables(byte _player, HandPresident _hand, String _loc) {
        if (progressingTrick.estVide()) {
            return new HandPresident(_hand);
        }
        errorPlaying = new StringBuilder();
        Playing playing_ = getStatus(_player);
        if (playing_ == Playing.FINISH) {
            return new HandPresident();
        }
        if (playing_ == Playing.PASS) {
            if (!cardsToBePlayed.estVide()) {
                errorPlaying = new StringBuilder(Format.formatter(FOLDER, file, _loc, HAVE_PASSED));
            }
            return new HandPresident();
        }
        if (playing_ == Playing.SKIPPED) {
            if (!cardsToBePlayed.estVide()) {
                errorPlaying = new StringBuilder(Format.formatter(FOLDER, file, _loc, SKIPPED));
            }
            return new HandPresident();
        }
        NatTreeMap<Byte,HandPresident> filtered_;
        filtered_ = new NatTreeMap<Byte,HandPresident>();
        HandPresident l_ = progressingTrick.getBestCards();
        byte str_ = l_.premiereCarte().strength(reversed);
        if (playing_ == Playing.HAS_TO_EQUAL) {
            if (!cardsToBePlayed.estVide() && cardsToBePlayed.premiereCarte().strength(reversed) != str_) {
                errorPlaying.append(Format.formatter(FOLDER, file, _loc, HAS_TO_EQUAL_OR_SKIP, l_.premiereCarte().toString(_loc))).append(RETURN_LINE);
            }
            NatTreeMap<Byte,HandPresident> cards_ = _hand.getCardsByStrength(reversed);
//            HandPresident plCards_ = new HandPresident();
            for (byte s: cards_.getKeys()) {
                HandPresident h_ = cards_.getVal(s);
                if (s != str_) {
                    continue;
                }
                filtered_.put(s, h_);
//                if (h_.total() < l_.total()) {
//                    continue;
//                }
//                plCards_.ajouterCartes(h_);
            }
//            return plCards_;
        } else if (rules.getEqualty() == EqualtyPlaying.FORBIDDEN) {
//            HandPresident l_ = progressingTrick.getBestCards(getNombreDeJoueurs());
//            byte str_ = l_.premiereCarte().strength(reversed);
            NatTreeMap<Byte,HandPresident> cards_ = _hand.getCardsByStrength(reversed);
//            HandPresident plCards_ = new HandPresident();
            if (!cardsToBePlayed.estVide() && cardsToBePlayed.premiereCarte().strength(reversed) <= str_) {
                errorPlaying.append(Format.formatter(FOLDER, file, _loc, CANNOT_USE_LOWER_OR_EQ, l_.premiereCarte().toString(_loc))).append(RETURN_LINE);
            }
            for (byte s: cards_.getKeys()) {
                HandPresident h_ = cards_.getVal(s);
                if (s <= str_) {
                    continue;
                }
                filtered_.put(s, h_);
//                if (h_.total() < l_.total()) {
//                    continue;
//                }
//                plCards_.ajouterCartes(h_);
            }
//            return plCards_;
        } else {
//            HandPresident l_ = progressingTrick.getBestCards(getNombreDeJoueurs());
//            byte str_ = l_.premiereCarte().strength(reversed);
            NatTreeMap<Byte,HandPresident> cards_ = _hand.getCardsByStrength(reversed);
//            HandPresident plCards_ = new HandPresident();
            if (!cardsToBePlayed.estVide() && cardsToBePlayed.premiereCarte().strength(reversed) <= str_) {
                errorPlaying.append(Format.formatter(FOLDER, file, _loc, CANNOT_USE_LOWER, l_.premiereCarte().toString(_loc))).append(RETURN_LINE);
            }
            for (byte s: cards_.getKeys()) {
                HandPresident h_ = cards_.getVal(s);
                if (s < str_) {
                    continue;
                }
                filtered_.put(s, h_);
//                if (h_.total() < l_.total()) {
//                    continue;
//                }
//                plCards_.ajouterCartes(h_);
            }
//            return plCards_;
        }
        HandPresident plCards_ = new HandPresident();
        for (byte s: filtered_.getKeys()) {
            HandPresident h_ = filtered_.getVal(s);
            if (h_.total() < l_.total()) {
                continue;
            }
            plCards_.ajouterCartes(h_);
        }
        if (cardsToBePlayed.total() != progressingTrick.getNombreDeCartesParJoueur()) {
            errorPlaying.append(Format.formatter(FOLDER, file, _loc, HAVE_PLAY_GIVEN_NUMBER_CARDS, Long.toString(progressingTrick.getNombreDeCartesParJoueur()))).append(RETURN_LINE);
        }
        return plCards_;
    }

    public Playing getStatus(byte _player) {
        if (getDistribution().main(_player).estVide()) {
            return Playing.FINISH;
        }
//        CustList<Boolean> passOrFinish_ = passOrFinish();
//        if (passOrFinish_.get(_player)) {
//            return Playing.PASS;
//        }
        if (passOrFinish.get(_player)) {
            return Playing.PASS;
        }
//        CustList<Integer> indexes_ = progressingTrick.getPlayedCardsIndexes(_player, getNombreDeJoueurs());
//        for (int i: indexes_) {
//            HandPresident h_ = progressingTrick.carte(i);
//            if (!h_.estVide()) {
//                continue;
//            }
//            CustList<HandPresident> filledHands_ = progressingTrick.getFilledHandsBefore(i, getNombreDeJoueurs());
//            if (filledHands_.size() <= CustList.ONE_ELEMENT) {
//                return Playing.PASS;
//            }
//            CustList<Integer> filledHandsIndexes_ = progressingTrick.getFilledHandsIndexesBefore(i, getNombreDeJoueurs());
//            HandPresident prev_ = filledHands_.last();
//            HandPresident befPrev_ = filledHands_.get(filledHands_.size() - 2);
//            if (prev_.premiereCarte().strength(reversed) == befPrev_.premiereCarte().strength(reversed)) {
//                continue;
//            }
//            return Playing.PASS;
//        }
        if (rules.getEqualty() == EqualtyPlaying.NO_SKIP) {
            return Playing.CAN_PLAY;
        }
        int count_ = progressingTrick.total();
        int nbPlayers_ = getNombreDeJoueurs();
        EqList<HandPresident> hands_ = new EqList<HandPresident>();
        Numbers<Integer> indexes_ = new Numbers<Integer>();
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
        Numbers<Integer> filledHandsIndexes_ = new Numbers<Integer>();
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
//        CustList<Integer> filledHandsIndexes_ = progressingTrick.getFilledHandsIndexesBefore(count_, getNombreDeJoueurs());
//        CustList<HandPresident> filledHands_ = progressingTrick.getFilledHandsBefore(count_, getNombreDeJoueurs());
        if (filledHands_.size() > CustList.ONE_ELEMENT) {
            int max_ = filledHandsIndexes_.last();
            boolean apply_ = true;
            for (int i = max_ + 1; i < count_; i++) {
                byte curPlayer_ = progressingTrick.getPlayer(i, getNombreDeJoueurs());
//                if (!passOrFinish_.get(curPlayer_)) {
//                    //curPlayer_ was skipped
//                    apply_ = false;
//                    break;
//                }
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
//        if (!indexes_.isEmpty()) {
//            CustList<HandPresident> filledHands_ = progressingTrick.getFilledHandsBefore(indexes_.last(), getNombreDeJoueurs());
//            if (filledHands_.size() > CustList.ONE_ELEMENT) {
//                HandPresident prev_ = filledHands_.last();
//                HandPresident befPrev_ = filledHands_.get(filledHands_.size() - 2);
//                if (prev_.premiereCarte().strength(reversed) == befPrev_.premiereCarte().strength(reversed)) {
//                    if (rules.getEqualty() == EqualtyPlaying.SKIP_ALWAYS_NEXT) {
//                        return Playing.SKIPPED;
//                    }
//                    return Playing.HAS_TO_EQUAL;
//                }
//            }
//        }
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

    public HandPresident playedCards(String _lg) {
        if (progressingTrick.estVide()) {
            return beginTrick(_lg);
        }
        return progressTrick(_lg);
    }

    public HandPresident beginTrick(String _lg) {
        HandPresident playable_ = cartesJouables(progressingTrick.getEntameur(), _lg);
        NatTreeMap<Byte,HandPresident> m_ = playable_.getCardsByStrength(reversed);
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
        byte avg_ = CardPresident.getAvgStrength(reversed);
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

    EqList<HandPresident> getLeadingCardsPlayer(NatTreeMap<Byte,HandPresident> _m, TreeMap<CardPresident,Byte> _playedCards) {
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

    EqList<HandPresident> getLeadingCards(NatTreeMap<Byte,HandPresident> _m, TreeMap<CardPresident,Byte> _playedCards) {
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

    public HandPresident defaultBeginTrick(String _lg) {
        HandPresident playable_ = cartesJouables(progressingTrick.getEntameur(), _lg);
        NatTreeMap<Byte,HandPresident> m_ = playable_.getCardsByStrength(reversed);
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

    public HandPresident progressTrick(String _lg) {
        int count_ = progressingTrick.total();
        int index_ = count_;
        byte player_ = progressingTrick.getPlayer(index_, getNombreDeJoueurs());
        HandPresident playable_ = cartesJouables(player_, _lg);
        if (playable_.estVide()) {
            return playable_;
        }
        NatTreeMap<Byte,HandPresident> m_ = playable_.getCardsByStrength(reversed);
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
        byte avg_ = CardPresident.getAvgStrength(reversed);
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
        if (canPass(player_, _lg)) {
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

    HandPresident defaultGroup(NatTreeMap<Byte,HandPresident> _m) {
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

    HandPresident lastGroup(NatTreeMap<Byte,HandPresident> _m) {
        int nb_ = progressingTrick.getNombreDeCartesParJoueur();
        byte avg_ = CardPresident.getAvgStrength(reversed);
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

    public HandPresident defaultProgressTrick(String _lg) {
        int count_ = progressingTrick.total();
        int index_ = count_;
        byte player_ = progressingTrick.getPlayer(index_, getNombreDeJoueurs());
        HandPresident playable_ = cartesJouables(player_, _lg);
        if (playable_.estVide()) {
            return playable_;
        }
        NatTreeMap<Byte,HandPresident> m_ = playable_.getCardsByStrength(reversed);
        for (byte b: m_.getKeys()) {
            HandPresident h_ = m_.getVal(b);
            if (h_.total() == progressingTrick.getNombreDeCartesParJoueur()) {
                return h_;
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

    public CustList<EqList<HandPresident>> getCardsSortedByLengthSortedByStrengthList(HandPresident _hand) {
        CustList<EqList<HandPresident>> l_ = new CustList<EqList<HandPresident>>();
        int nbMaxLen_ = rules.getNbStacks() * NB_SUITS;
        for (int i = CustList.SECOND_INDEX; i <= nbMaxLen_; i++) {
            l_.add(_hand.getCardsByLengthSortedByStrength(reversed, i));
        }
        return l_;
    }

    boolean maitreSimpleNUupleEntame(HandPresident _rep, TreeMap<CardPresident, Byte> _playedCards){
        /*repartition est triee par nombre puis par valeur*/
        NatTreeMap<Byte,HandPresident> m_ = _rep.getCardsByStrength(reversed);
        CustList<EqList<HandPresident>> repartition_=getCardsSortedByLengthSortedByStrengthList(_rep);
        CustList<EqList<HandPresident>> repartitionNotEmpty_=new CustList<EqList<HandPresident>>();
        for (EqList<HandPresident> l :repartition_) {
            if (!l.isEmpty()) {
                repartitionNotEmpty_.add(l);
            }
        }
        repartitionNotEmpty_ = repartitionNotEmpty_.getReverse();
        EqList<HandPresident> tmp_;
        EqList<HandPresident> tmpBis_;
        int nombreNonMaitres_;
        int nombreMaitres_;
        int difference_=0;
        int somme_=0;
        int total_;
        int totalPrecedent_=0;
        int autreSomme_=0;
        Numbers<Integer> retenue_=new Numbers<Integer>();
        for(int i=repartitionNotEmpty_.size()-1;i>=CustList.FIRST_INDEX;i--) {
            total_=repartitionNotEmpty_.get(i).first().total();
            if(total_>0) {
                tmp_=carteNonNMaitresNUple((byte)total_, m_, _playedCards);
                nombreNonMaitres_=0;
                for(HandPresident main_:tmp_) {
                    nombreNonMaitres_+=main_.total();
                }
                tmp_=carteNMaitres((byte)total_, m_, _playedCards);
                if(totalPrecedent_>0) {
                    tmpBis_=carteNMaitresBis((byte)totalPrecedent_, m_, _playedCards);
                    autreSomme_=0;
                    for(HandPresident main_:tmpBis_) {
                        autreSomme_+=main_.total();
                    }
                    retenue_.add(autreSomme_);
                    somme_=0;
                    for(int partiel_:retenue_) {
                        somme_+=partiel_;
                    }
                    autreSomme_=0;
                    for(HandPresident main_:tmp_) {
                        autreSomme_+=main_.total();
                    }
                    nombreMaitres_=autreSomme_-somme_;
                } else {
                    for(HandPresident main_:tmp_) {
                        autreSomme_+=main_.total();
                    }
                    nombreMaitres_=autreSomme_;
                }
                difference_+=nombreMaitres_-nombreNonMaitres_;
                totalPrecedent_=total_;
                if(difference_<0) {
                    return false;
                }
            }
        }
        return true;
    }

    EqList<HandPresident> carteNonNMaitresNUple(byte _nb,NatTreeMap<Byte,HandPresident> _rep, TreeMap<CardPresident, Byte> _playedCards) {
        EqList<HandPresident> cartes_=new EqList<HandPresident>();
        for(HandPresident v:_rep.values()) {
            if(!v.estVide()&&v.total()==_nb&&!maitreValeur(v.premiereCarte().strength(reversed), _nb, _playedCards)) {
                cartes_.add(v);
            }
        }
        return cartes_;
    }

    EqList<HandPresident> carteNMaitres(byte _nb, NatTreeMap<Byte,HandPresident> _rep, TreeMap<CardPresident, Byte> _playedCards) {
        EqList<HandPresident> cartes_=new EqList<HandPresident>();
        for(HandPresident v:_rep.values()) {
            if(!v.estVide()&&v.total()>=_nb&&maitreValeur(v.premiereCarte().strength(reversed), _nb, _playedCards)) {
                cartes_.add(v);
            }
        }
        return cartes_;
    }
    EqList<HandPresident> carteNMaitresBis(byte _nb, NatTreeMap<Byte,HandPresident> _rep, TreeMap<CardPresident, Byte> _playedCards) {
        EqList<HandPresident> cartes_=new EqList<HandPresident>();
        for(HandPresident v:_rep.values()) {
            if(!v.estVide()&&maitreValeur(v.premiereCarte().strength(reversed), _nb, _playedCards)) {
                cartes_.add(v);
            }
        }
        return cartes_;
    }

    boolean dominantHandProgressTrick(HandPresident _h, TreeMap<CardPresident, Byte> _playedCards) {
        HandPresident c_ = new HandPresident(_h);
        NatTreeMap<Byte,HandPresident> gl_ = _h.getCardsByStrength(reversed);
        EqList<HandPresident> rep_ = getCardsSortedByLengthSortedByStrengthReduce(c_);
        EqList<HandPresident> hWorst_ = new EqList<HandPresident>();
        EqList<HandPresident> hBest_ = new EqList<HandPresident>();
        int maxStack_ = rules.getNbStacks() * NB_SUITS;
        byte avg_ = CardPresident.getAvgStrength(reversed);
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
        NatTreeMap<Byte,HandPresident> gl_ = _h.getCardsByStrength(reversed);
        EqList<HandPresident> rep_ = getCardsSortedByLengthSortedByStrengthReduce(c_);
        EqList<HandPresident> hWorst_ = new EqList<HandPresident>();
        EqList<HandPresident> hBest_ = new EqList<HandPresident>();
        int maxStack_ = rules.getNbStacks() * NB_SUITS;
        byte avg_ = CardPresident.getAvgStrength(reversed);
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

    boolean dominantGroup(HandPresident _h, int _nb, TreeMap<CardPresident, Byte> _playedCards, NatTreeMap<Byte,HandPresident> _rep) {
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

//    HandPresident maitreValeur(NatTreeMap<Byte,HandPresident> _rep, TreeMap<CardPresident, Byte> _playedCards) {
//        HandPresident h_ = new HandPresident();
//        int maxStack_ = rules.getNbStacks() * NB_SUITS;
//        TreeMap<CardPresident, Byte> sum_ = new TreeMap<CardPresident, Byte>(new _playedCards);
//        for (byte s: _rep.getKeys()) {
//            HandPresident hStr_ = _rep.getVal(s);
//            if (hStr_.estVide()) {
//                continue;
//            }
//            byte strValue_ = hStr_.premiereCarte().strength(reversed);
//            for (CardPresident c: sum_.getKeys()) {
//                if (c.strength(reversed) == strValue_) {
//                    byte v_ = sum_.getVal(c);
//                    v_ += hStr_.total();
//                    sum_.put(c, v_);
//                }
//            }
//        }
//        EnumList<CardPresident> l_ = new EnumList<CardPresident>();
//        for (CardPresident c: sum_.getKeys()) {
//            byte v_ = sum_.getVal(c);
//            if (v_ == maxStack_) {
//                l_.add(c);
//            }
//        }
//        for (byte s: _rep.getKeys()) {
//            HandPresident hStr_ = _rep.getVal(s);
//            if (hStr_.estVide()) {
//                continue;
//            }
//            byte strValue_ = hStr_.premiereCarte().strength(reversed);
//            for (CardPresident c: l_) {
//                if (c.strength(reversed) == strValue_) {
//                    h_.ajouterCartes(hStr_);
//                    break;
//                }
//            }
//        }
//        return h_;
//    }

    boolean maitreValeur(byte _strength, byte _nb, TreeMap<CardPresident, Byte> _playedCards) {
        if (_strength == CardPresident.getMaxStrength(reversed)) {
            return true;
        }
        int maxStack_ = rules.getNbStacks() * NB_SUITS;
        if (rules.getEqualty() == EqualtyPlaying.FORBIDDEN) {
            for (CardPresident c: _playedCards.getKeys()) {
                byte strLoc_ = c.strength(reversed);
                if (strLoc_ <= _strength) {
                    break;
                }
                byte rem_ = (byte) (maxStack_ - _playedCards.getVal(c));
                if (rem_ >= _nb) {
                    return false;
                }
            }
            return true;
        }
        for (CardPresident c: _playedCards.getKeys()) {
            byte strLoc_ = c.strength(reversed);
            if (strLoc_ < _strength) {
                break;
            }
            byte rem_ = (byte) (maxStack_ - _playedCards.getVal(c));
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

    public void restituerMainsDepart(DisplayingPresident _displaying,CustList<TrickPresident> _plisFaits,
            byte _nombreJoueurs) {
        for (TrickPresident pli_ : _plisFaits) {
            int index_ = CustList.FIRST_INDEX;
            for (HandPresident carte_ : pli_) {
                getDistribution().main(pli_.getPlayer(index_, getNombreDeJoueurs())).ajouterCartes(carte_);
                index_++;
            }
        }
        for (byte joueur_ = CustList.FIRST_INDEX; joueur_ < _nombreJoueurs; joueur_++) {
            getDistribution().main(joueur_).sortCards(_displaying.getDecroissant(), false);
        }
        revertGifts();
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

    public boolean debutDernier() {
        return rules.isLooserStartsFirst();
    }

    public boolean isReversed() {
        return reversed;
    }

    public boolean allowPass() {
        return !rules.isHasToPlay();
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

    public Numbers<Byte> getNewRanks() {
        Numbers<Byte> r_ = new Numbers<Byte>();
        Numbers<Integer> t_ = new Numbers<Integer>();
        Numbers<Integer> c_ = new Numbers<Integer>();
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
            while (tInd_ >= CustList.FIRST_INDEX) {
                TrickPresident curTrick_ = tricks.get(tInd_);
                Numbers<Integer> cInd_ = curTrick_.getFilledHandsIndexesBefore(curTrick_.total());
                Numbers<Integer> cIndPl_ = new Numbers<Integer>();
                for (int i: cInd_) {
                    byte curPlayer_ = curTrick_.getPlayer(i, nbPlayers_);
                    if (curPlayer_ == p) {
                        cIndPl_.add(i);
                    }
                }
//                if (!cInd_.isEmpty()) {
//                    t_.set(p, tInd_);
//                    c_.set(p, cInd_.last());
//                    break;
//                }
                if (!cIndPl_.isEmpty()) {
                    t_.set(p, tInd_);
                    c_.set(p, cIndPl_.last());
                    break;
                }
                tInd_--;
            }
        }
        Numbers<Byte> normalEnds_ = new Numbers<Byte>();
        Numbers<Byte> eStrongestCards_ = new Numbers<Byte>();
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
//        NatTreeMap<Integer, Numbers<Byte>> tricksPlayers_;
//        tricksPlayers_ = new NatTreeMap<Integer, Numbers<Byte>>();
//        for (byte p = CustList.FIRST_INDEX; p <nbPlayers_; p++) {
//            if (eStrongestCards_.containsObj(p)) {
//                continue;
//            }
//            int noTrick_ = t_.get(p);
//            if (tricksPlayers_.contains(noTrick_)) {
//                tricksPlayers_.getVal(noTrick_).add(p);
//            } else {
//                tricksPlayers_.put(noTrick_, new Numbers<Byte>(p));
//            }
//        }
        Numbers<Byte> tricksCardsPlayers_;
        tricksCardsPlayers_ = new Numbers<Byte>();
//        for (Integer k: tricksPlayers_.getKeys()) {
//            Numbers<Byte> players_ = tricksPlayers_.getVal(k);
//            NatTreeMap<Integer, Byte> ordPlayers_;
//            ordPlayers_ = new NatTreeMap<Integer, Byte>();
//            for (Byte p: players_) {
//                ordPlayers_.put(c_.get(p), p);
//            }
//            tricksCardsPlayers_.addAll(ordPlayers_.values());
//        }
//        tricksPlayers_.clear();
//        for (byte p : eStrongestCards_) {
//            int noTrick_ = t_.get(p);
//            if (tricksPlayers_.contains(noTrick_)) {
//                tricksPlayers_.getVal(noTrick_).add(p);
//            } else {
//                tricksPlayers_.put(noTrick_, new Numbers<Byte>(p));
//            }
//        }
//        for (Integer k: tricksPlayers_.getKeys()) {
//            Numbers<Byte> players_ = tricksPlayers_.getVal(k);
//            NatTreeMap<Integer, Byte> ordPlayers_;
//            ordPlayers_ = new NatTreeMap<Integer, Byte>();
//            for (Byte p: players_) {
//                ordPlayers_.put(c_.get(p), p);
//            }
//            tricksCardsPlayers_.addAll(ordPlayers_.values());
//        }
        tricksCardsPlayers_.addAllElts(getTricksCardsPlayers(normalEnds_, t_, c_));
        tricksCardsPlayers_.addAllElts(getTricksCardsPlayers(eStrongestCards_, t_, c_));
        byte curRank_ = CustList.SECOND_INDEX;
        for (byte p: tricksCardsPlayers_) {
            r_.set(p, curRank_);
            curRank_++;
        }
        return r_;
    }

    static Numbers<Byte> getTricksCardsPlayers(Numbers<Byte> _players, Numbers<Integer> _t, Numbers<Integer> _c) {
        NatTreeMap<Integer, Numbers<Byte>> tricksPlayers_;
        tricksPlayers_ = new NatTreeMap<Integer, Numbers<Byte>>();
        for (byte p : _players) {
            int noTrick_ = _t.get(p);
            if (tricksPlayers_.contains(noTrick_)) {
                tricksPlayers_.getVal(noTrick_).add(p);
            } else {
                tricksPlayers_.put(noTrick_, new Numbers<Byte>(p));
            }
        }
        Numbers<Byte> tricksCardsPlayers_;
        tricksCardsPlayers_ = new Numbers<Byte>();
        for (int k: tricksPlayers_.getKeys()) {
            Numbers<Byte> players_ = tricksPlayers_.getVal(k);
            NatTreeMap<Integer, Byte> ordPlayers_;
            ordPlayers_ = new NatTreeMap<Integer, Byte>();
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

    public Numbers<Byte> getRanks() {
        return ranks;
    }

    public String getErrorPlaying() {
        return errorPlaying.toString();
    }

    public NumberMap<Byte,HandPresident> getSwitchedCards() {
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

    public void copySwitchCards(NumberMap<Byte,HandPresident> _switchedCards) {
        switchedCards = new NumberMap<Byte,HandPresident>();
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

    public NumberMap<Byte,Playing> getLastStatus() {
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
            Numbers<Integer> indexes_ = progressingTrick.getPlayedCardsIndexes(p, nbPlayers_);
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
//            int maxIndex_ = progressingTrick.total();
//            CustList<HandPresident> hands_ = new CustList<HandPresident>();
//            CustList<Integer> indexesTurn_ = new CustList<Integer>();
//            int index_;
//            if (maxIndex_ >= nbPlayers_) {
//                index_ = maxIndex_ - nbPlayers_;
//                for (byte q = CustList.FIRST_INDEX; q < nbPlayers_; q++) {
//                    hands_.add(progressingTrick.carte(index_));
//                    indexesTurn_.add(index_);
//                    index_++;
//                }
//            } else {
//                index_ = CustList.FIRST_INDEX;
//                for (HandPresident h: progressingTrick) {
//                    hands_.add(h);
//                    indexesTurn_.add(index_);
//                    index_++;
//                }
//            }
//            CustList<HandPresident> filledHands_ = new CustList<HandPresident>();
//            CustList<Integer> filledHandsIndexes_ = new CustList<Integer>();
//            index_ = CustList.FIRST_INDEX;
//            for (HandPresident h: hands_) {
//                if (h.estVide()) {
//                    index_++;
//                    continue;
//                }
//                filledHands_.add(h);
//                filledHandsIndexes_.add(indexesTurn_.get(index_));
//                index_++;
//            }
//            CustList<Integer> filledHandsIndexes_ = progressingTrick.getFilledHandsIndexesBefore(maxIndex_, nbPlayers_);
//            CustList<HandPresident> filledHands_ = progressingTrick.getFilledHandsBefore(maxIndex_, nbPlayers_);
//            if (filledHands_.size() > CustList.ONE_ELEMENT) {
//                int max_ = filledHandsIndexes_.last();
//                boolean apply_ = true;
//                for (int i = max_ + 1; i < maxIndex_; i++) {
//                    byte curPlayer_ = progressingTrick.getPlayer(i, nbPlayers_);
//                    if (!passOrFinish.get(curPlayer_)) {
//                        //curPlayer_ was skipped
//                        apply_ = false;
//                        break;
//                    }
//                }
//                HandPresident prev_ = filledHands_.last();
//                HandPresident befPrev_ = filledHands_.get(filledHands_.size() - 2);
//                if (apply_ && prev_.premiereCarte().strength(reversed) == befPrev_.premiereCarte().strength(reversed)) {
//                    if (rules.getEqualty() == EqualtyPlaying.SKIP_ALWAYS_NEXT) {
//                        lastStatus.put(p, Playing.SKIPPED);
//                        continue;
//                    }
//                    lastStatus.put(p, Playing.HAS_TO_EQUAL);
////                    if (progressingTrick.getPlayer(progressingTrick.total(), nbPlayers_) == p) {
////                        lastStatus.put(p, Playing.HAS_TO_EQUAL);
////                        continue;
////                    }
////                    lastStatus.put(p, Playing.DO_NOT_EQUAL);
//                    continue;
//                }
//            }
//            lastStatus.put(p, Playing.CAN_PLAY);
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

    public int getChargementSimulation() {
        return chargementSimulation.get();
    }

    public void setChargementSimulation(int _chargementSimulation) {
        chargementSimulation.set(_chargementSimulation);
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

    public CustList<Numbers<Byte>> getRanksDeals() {
        return ranksDeals;
    }

    public CustList<CustList<NumberMap<Integer,NumberMap<Byte,Playing>>>> getLastStatusDeals() {
        return lastStatusDeals;
    }

    public CustList<CustList<NumberMap<Integer,Byte>>> getNextPlayerDeals() {
        return nextPlayerDeals;
    }

    public CustList<NumberMap<Byte,HandPresident>> getSwitchedCardsDeals() {
        return switchedCardsDeals;
    }

    public DealPresident getDeal() {
        return deal;
    }

    public void setDeal(DealPresident _deal) {
        deal = _deal;
    }

    public Numbers<Short> getScores() {
        return scores;
    }

    public void setScores(Numbers<Short> _scores) {
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

    public void setRanks(Numbers<Byte> _ranks) {
        ranks = _ranks;
    }

    public void setSwitchedCards(NumberMap<Byte, HandPresident> _switchedCards) {
        switchedCards = _switchedCards;
    }

    public String getError() {
        return error;
    }

    public void setError(String _error) {
        error = _error;
    }
}
