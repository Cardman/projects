package cards.president;

import cards.consts.SortedPlayers;
import cards.president.enumerations.CardPresident;
import code.util.CustList;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class CheckerGamePresidentWithRules {

    private static final String ERROR_RULES = "invalid rules";
    private static final String HANDS_COUNT = "bad hand count";
    private static final String SCORES_COUNT = "bad scores points count";
    private static final String TRICK_EVENTS = "either no played card or the first group is not empty";
    private static final String EMPTY_TRICK = "empty trick";
    private static final String NOT_PLAYABLE = "not playable";
    private static final String BAD_RANK_COUNT = "bad rank count";
    private static final String BAD_SWITCH_CARD_GROUP_COUNT = "bad switch card group count";
    private static final String BAD_SWITCH_CARD_GROUP_COUNT_OTHER = "other players cannot switch any card";
    private static final String BAD_SWITCH_CARD_GROUP_COUNT_WINNER = "winner players must switch exactly the required count";
    private static final String BAD_SWITCH_CARD_GROUP_COUNT_LOOSER = "looser players must switch exactly the required count";
    private static final String BAD_SWITCH_CARD_GROUP_COUNT_WINNER_CONTENT = "cards do not come from looser players to winner players";
    private static final String BAD_SWITCH_CARD_GROUP_COUNT_LOOSER_CONTENT = "looser players must give their best cards";
    private static final String DUPLICATE_RANK_COUNT = "duplicate ranks";
    private static final String BAD_CARD_COUNT = "bad card count for player";
    private static final String BAD_CARD_UNIT_COUNT = "the number of each different card is the following (because of stack count):";
    private static final String NOT_ALLOWED_PLAYED_CARD = "cards must be switched before played";
    private static final String MISS_MATCH_TRICK_EVENTS_NOT_EMPTY_GROUP = "there is a mismatch between events count or the possible first played cards group";
    private static final String MISS_MATCH_STRENGTH = "played card must have the same strength in a same group";
    private static final String BAD_PLAYED_CARD = "played cards are not allowed or bad played count cards";
    private static final String FIRST_GROUP_CANNOT_BE_EMPTY = "first card group cannot be empty";
    private static final String CANNOT_PASS = "there must be played card";
    private static final String NO_CARD_AFTER_FINISHED_DIRECTLY_CARD = "there must not be any card after directly finished trick";

    private CheckerGamePresidentWithRules() {
    }
    public static void check(GamePresident _loadedGame) {
        RulesPresident rules_ = _loadedGame.getRules();
        if (!rules_.isValidRules()) {
            _loadedGame.setError(ERROR_RULES);
            return;
        }
        byte nbPlayers_ = (byte) rules_.getNbPlayers();
        if (_loadedGame.getDeal().nombreDeMains() != nbPlayers_) {
            _loadedGame.setError(HANDS_COUNT);
            return;
        }
        if (_loadedGame.getScores().size() != nbPlayers_) {
            _loadedGame.setError(SCORES_COUNT);
            return;
        }
        Bytes ranks_ = _loadedGame.getRanks();
        CustList<TrickPresidentIndexesCheck> check_ = _loadedGame.loadGame();
        for (TrickPresidentIndexesCheck c: check_) {
            koNext(_loadedGame,c);
        }
        CustList<TrickPresident> allTricks_ = _loadedGame.unionPlis();
        HandPresident cards_ = allCards(_loadedGame);
        CustList<TrickPresident> allTricksPlusCurr_ = new CustList<TrickPresident>(allTricks_);
        allTricksPlusCurr_.add(_loadedGame.getProgressingTrick());
        for (TrickPresident t: allTricksPlusCurr_) {
            if (notEmptyAlthough(t)) {
                _loadedGame.setError(TRICK_EVENTS);
                return;
            }
        }
        for (CardPresident e : cards_) {
            if (!e.getId().isJouable()) {
                _loadedGame.setError(NOT_PLAYABLE);
                return;
            }
        }
        DealPresident deal_ = buildDeal(_loadedGame, nbPlayers_, allTricks_);
        if (!ranks_.isEmpty()) {
            if (ranks_.size() != nbPlayers_) {
                _loadedGame.setError(BAD_RANK_COUNT);
                return;
            }
            Bytes copyRanks_ = new Bytes(ranks_);
            if (copyRanks_.hasDuplicates()) {
                _loadedGame.setError(DUPLICATE_RANK_COUNT);
                return;
            }
        }
        chSwitch(_loadedGame, deal_);
    }

    private static void chSwitch(GamePresident _loadedGame, DealPresident _deal) {
        Bytes winners_ = _loadedGame.getWinners();
        Bytes loosers_ = _loadedGame.getLoosers();
        for (TrickPresident t: _loadedGame.unionPlis()) {
            if (t.estVide()) {
                _loadedGame.setError(EMPTY_TRICK);
                return;
            }
        }
        checkGiftsHandsPlay(_loadedGame, _deal, winners_, loosers_);
    }

    private static boolean notEmptyAlthough(TrickPresident _t) {
        return _t.getNombreDeCartesParJoueur() == 0 && !_t.getCartes().estVide();
    }

    private static HandPresident allCards(GamePresident _loadedGame) {
        CustList<TrickPresident> allTricks_ = _loadedGame.unionPlis();
        HandPresident cards_ = new HandPresident();
        for (TrickPresident t : allTricks_) {
            for (HandPresident c : t) {
                cards_.ajouterCartes(c);
            }
        }
        for (HandPresident c : _loadedGame.getProgressingTrick()) {
            cards_.ajouterCartes(c);
        }
        for (HandPresident c : _loadedGame.getDeal()) {
            cards_.ajouterCartes(c);
        }
        for (HandPresident s : _loadedGame.getSwitchedCards()) {
            cards_.ajouterCartes(s);
        }
        return cards_;
    }

    private static void checkGiftsHandsPlay(GamePresident _loadedGame, DealPresident _deal, Bytes _winners, Bytes _loosers) {
        if (!_loadedGame.availableSwitchingCards() && !_loadedGame.getSwitchedCards().isEmpty()) {
            _loadedGame.setError(BAD_SWITCH_CARD_GROUP_COUNT);
            return;
        }
        if (_loadedGame.availableSwitchingCards() && _loadedGame.getSwitchedCards().size() != _loadedGame.getRules().getNbPlayers()) {
            _loadedGame.setError(BAD_SWITCH_CARD_GROUP_COUNT);
            return;
        }
        DealPresident dcp_ = new DealPresident(_deal);
        if (skipPlay(_loadedGame, _deal, _winners, _loosers)) {
            return;
        }
        play(_loadedGame, dcp_);
    }

    private static boolean skipPlay(GamePresident _loadedGame, DealPresident _deal, Bytes _winners, Bytes _loosers) {
        boolean readyToPlay_ = GamePresident.revert(_loadedGame.nombresCartesEchangesMax(), _loadedGame.getRanks(), _loadedGame.getSwitchedCards(), _deal);
        Bytes swPl_ = new Bytes();
        swPl_.addAllElts(_winners);
        swPl_.addAllElts(_loosers);
        for (byte o : SortedPlayers.autresJoueurs(swPl_,(byte) _loadedGame.getRules().getNbPlayers())) {
            if (koSwOther(_loadedGame, o)) {
                _loadedGame.setError(BAD_SWITCH_CARD_GROUP_COUNT_OTHER);
                return true;
            }
        }
        for (byte w : _winners) {
            if (koSwWinner(_loadedGame, _deal, _winners, _loosers, readyToPlay_, w)) {
                return true;
            }
        }
        for (byte l : _loosers) {
            if (koSwLooser(_loadedGame, _deal, _loosers, l)) {
                return true;
            }
        }
        if (koCount(_loadedGame, _deal)) {
            return true;
        }
        if (!allCardsUsedNb(_loadedGame.getRules(), _deal)) {
            _loadedGame.setError(BAD_CARD_UNIT_COUNT+ _loadedGame.getRules().getNbStacks());
            return true;
        }
        if (!readyToPlay_) {
            if (oneCardPlayAtLeast(_loadedGame)) {
                _loadedGame.setError(NOT_ALLOWED_PLAYED_CARD);
            }
            return true;
        }
        return !oneCardPlayAtLeast(_loadedGame);
    }

    private static boolean koSwOther(GamePresident _loadedGame, byte _o) {
        return _loadedGame.getSwitchedCards().isValidIndex(_o) && !_loadedGame.getSwitchedCards().get(_o).estVide();
    }

    private static boolean koSwWinner(GamePresident _loadedGame, DealPresident _deal, Bytes _winners, Bytes _loosers, boolean _readyToPlay, byte _w) {
        if (!_loadedGame.getSwitchedCards().isValidIndex(_w)) {
            return false;
        }
        if (skipUser(_readyToPlay, _w)) {
            return false;
        }
        int ind_ = _winners.indexOfNb(_w);
        int nbGivenCards_ = _loadedGame.nombresCartesEchangesMax()
                - ind_;
        HandPresident sw_ = _loadedGame.getSwitchedCards().get(_w);
        if (sw_.total() != nbGivenCards_) {
            _loadedGame.setError(BAD_SWITCH_CARD_GROUP_COUNT_WINNER);
            return true;
        }
        byte pl_ = GamePresident.getMatchingLoser(_winners, _loosers, _w);
        HandPresident hl_ = new HandPresident(_deal.hand(_w));
        hl_.ajouterCartes(_loadedGame.getSwitchedCards()
                .get(pl_));
        if (!hl_.containsCards(_loadedGame.getSwitchedCards()
                .get(_w))) {
            _loadedGame.setError(BAD_SWITCH_CARD_GROUP_COUNT_WINNER_CONTENT);
            return true;
        }
        return false;
    }

    private static boolean koSwLooser(GamePresident _loadedGame, DealPresident _deal, Bytes _loosers, byte _l) {
        if (!_loadedGame.getSwitchedCards().isValidIndex(_l)) {
            return false;
        }
        int ind_ = _loosers.indexOfNb(_l);
        int nbGivenCards_ = _loadedGame.nombresCartesEchangesMax()
                - ind_;
        if (_loadedGame.getSwitchedCards().get(_l).total() != nbGivenCards_) {
            _loadedGame.setError(BAD_SWITCH_CARD_GROUP_COUNT_LOOSER);
            return true;
        }
        HandPresident hCopy_ = new HandPresident(_deal.hand(_l));
        HandPresident hSwitchCopy_ = new HandPresident(_loadedGame
                .getSwitchedCards().get(_l));
        hCopy_.sortCardsBegin();
        hSwitchCopy_.sortCardsBegin();
        int lenGiv_ = hSwitchCopy_.total();
        for (int i = IndexConstants.FIRST_INDEX; i < lenGiv_; i++) {
            byte strGiv_ = hSwitchCopy_.carte(i).getForce();
            byte str_ = hCopy_.carte(i).getForce();
            if (strGiv_ != str_) {
                _loadedGame.setError(BAD_SWITCH_CARD_GROUP_COUNT_LOOSER_CONTENT);
                return true;
            }
        }
        return false;
    }

    private static boolean skipUser(boolean _readyToPlay, byte _w) {
        return !_readyToPlay && _w == DealPresident.NUMERO_UTILISATEUR;
    }

    private static boolean koCount(GamePresident _loadedGame, DealPresident _deal) {
        byte nbPlayers_ = (byte) _loadedGame.getRules().getNbPlayers();
        int nbCards_ = _loadedGame.getRules().getNbStacks() * HandPresident.pileBase().total();
        int rem_ = nbCards_ % nbPlayers_;
        boolean noRem_ = rem_ == 0;
        int nbCardsPerPlayer_ = nbCards_ / nbPlayers_;
        if (noRem_) {
            for (HandPresident h : _deal) {
                if (h.total() != nbCardsPerPlayer_) {
                    _loadedGame.setError(BAD_CARD_COUNT);
                    return true;
                }
            }
        } else {
            for (HandPresident h : _deal) {
                if (h.total() > nbCardsPerPlayer_ + 1 || h.total() < nbCardsPerPlayer_) {
                    _loadedGame.setError(BAD_CARD_COUNT);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean oneCardPlayAtLeast(GamePresident _load) {
        return !_load.getTricks().isEmpty() || !_load.getProgressingTrick().estVide();
    }

    private static DealPresident buildDeal(GamePresident _loadedGame, byte _nbPlayers, CustList<TrickPresident> _allTricks) {
        DealPresident deal_ = new DealPresident(_loadedGame.getDeal());
        for (TrickPresident t : _allTricks) {
            dealTrick(_nbPlayers, deal_, t);
        }
        dealTrick(_nbPlayers, deal_, _loadedGame.getProgressingTrick());
        return deal_;
    }

    private static void dealTrick(byte _nbPlayers, DealPresident _deal, TrickPresident _t) {
        int index_ = IndexConstants.FIRST_INDEX;
        for (HandPresident c : _t) {
            byte player_ = _t.getPlayer(
                    index_, _nbPlayers);
            _deal.hand(player_).ajouterCartes(c);
            index_++;
        }
    }
    private static boolean allCardsUsedNb(RulesPresident _rules, DealPresident _deal) {
        boolean allCardsUsedNb_ = true;
        for (CardPresident c : HandPresident.pileBase()) {
            int nbUses_ = 0;
            for (HandPresident t : _deal) {
                for (CardPresident c2_ : t) {
                    if (c == c2_) {
                        nbUses_++;
                    }
                }
            }
            if (nbUses_ != _rules.getNbStacks()) {
                allCardsUsedNb_ = false;
                break;
            }
        }
        return allCardsUsedNb_;
    }

    private static void play(GamePresident _loadedGame, DealPresident _deal) {
        GamePresident loadedGameCopy_ = new GamePresident(
                _loadedGame.getType(), _deal, _loadedGame.getRules(), _loadedGame.getRanks());
        loadedGameCopy_.copySwitchCards(_loadedGame.getSwitchedCards());
        int ind_ = 0;
//        loadedGameCopy_.initializeFirstTrick();
        while (true) {
            if (!keepTrick(_loadedGame, loadedGameCopy_,ind_)) {
                return;
            }
            if (ind_ >= loadedGameCopy_.unionPlis().size()) {
                return;
            }
            ind_++;
            if (!loadedGameCopy_.keepPlayingCurrentGame()) {
                return;
            }
        }
    }
    private static boolean keepTrick(GamePresident _loadedGame, GamePresident _loadedGameCopy, int _ind){
        TrickPresident trick_ = trick(_loadedGame, _ind);
//        if (!_loadedGame.getTricks().isValidIndex(_ind) && empty(_loadedGameCopy) != null) {
//            _loadedGame.setError(MESSAGE_ERROR);
//            return false;
//        }
        int nbCardsPerPlayerTrick_ = trick_.getNombreDeCartesParJoueur();
        if (nbCardsPerPlayerTrick_ == 0 && !trick_.estVide()) {
            return true;
        }
        int nbHands_ = trick_.total();
        int currentIndex_ = 0;
        for (int i = IndexConstants.FIRST_INDEX; i < nbHands_; i++) {
            if (currentIndex_ > i) {
                continue;
            }
            int next_ = keepTrickIt(_loadedGame, trick_, _loadedGameCopy, i, nbCardsPerPlayerTrick_);
            if (next_ < 0) {
                return false;
            }
            TrickPresident e_ = empty(_loadedGameCopy);
            TrickPresident s_ = source(e_,_loadedGame,_ind);
            int d1_ = NumberUtil.abs(total(e_) - total(s_));
            int d2_ = NumberUtil.abs(cardPerPlayer(e_) - cardPerPlayer(s_));
            boolean ko_;
            if (s_ != null) {
                ko_ = d1_ + d2_ != 0;
            } else {
                ko_ = false;
            }
            if (ko_) {
                _loadedGame.setError(MISS_MATCH_TRICK_EVENTS_NOT_EMPTY_GROUP);
                return true;
            }
            currentIndex_ = next_;
        }
//        _loadedGameCopy.initializeTrick(trick_.getPlayer(
//                trick_.total(), nbPlayers_));
//        if (nbCardsPerPlayerTrick_ == 0) {
//            _loadedGameCopy.initializeTrick(trick_.getPlayer(
//                    trick_.total(), nbPlayers_));
//        }
//        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
//            _loadedGameCopy.getPassOrFinish().set(p,
//                    ComparatorBoolean.of(_loadedGameCopy.getDeal().hand(p).estVide()));
//        }
        return true;
    }

    private static TrickPresident empty(GamePresident _loadedGameCopy) {
        if (!_loadedGameCopy.getTricks().isEmpty() && virtualTrick(_loadedGameCopy.getTricks().last()) && _loadedGameCopy.getProgressingTrick().estVide()) {
            return _loadedGameCopy.getTricks().last();
        }
        return null;
    }

    private static TrickPresident source(TrickPresident _e, GamePresident _loadedGame, int _ind) {
        if (_e == null) {
            return null;
        }
        return trick(_loadedGame, _ind + 1);
    }
    private static int total(TrickPresident _t) {
        return TrickPresident.nullToEmpty(_t).total();
    }
    private static int cardPerPlayer(TrickPresident _t) {
        if (_t == null) {
            return 0;
        }
        return _t.getNombreDeCartesParJoueur();
    }

    private static boolean virtualTrick(TrickPresident _t) {
        return _t.getNombreDeCartesParJoueur() == 0;
    }

    private static int keepTrickIt(GamePresident _loadedGame, TrickPresident _trick, GamePresident _loadedGameCopy, int _i, int _nombreDeCartesParJoueur){
//        byte nbPlayers_ = (byte) _rules.getNbPlayers();
//        byte player_ = _trick.getPlayer(_i, nbPlayers_);
        HandPresident curHand_ = _trick.carte(_i);
        if (!sameStrength(curHand_)) {
            _loadedGame.setError(MISS_MATCH_STRENGTH);
            return -1;
        }
        if (!curHand_.estVide()) {
            if (!_loadedGameCopy.allowPlaying(curHand_) || curHand_.total() != _nombreDeCartesParJoueur) {
                _loadedGame.setError(BAD_PLAYED_CARD);
                return -1;
            }
//            if (_loadedGameCopy.getDeal().hand(player_)
//                    .estVide()) {
//                _loadedGameCopy.getPassOrFinish()
//                        .set(player_, BoolVal.TRUE);
//            }
//            if (count_ != _nombreDeCartesParJoueur || _loadedGameCopy.getProgressingTrick().estVide() && _i + 1 < _trick.total() && !_trick.carte(_i + 1).estVide()) {
//                _loadedGame.setError(MESSAGE_ERROR);
//                return false;
//            }
//            return true;
        } else {
//        } else if (_nombreDeCartesParJoueur != 0) {
            if (_loadedGameCopy.getProgressingTrick().getCartes().estVide()) {
                _loadedGame.setError(FIRST_GROUP_CANNOT_BE_EMPTY);
                return -1;
            }
            if (!_loadedGameCopy.canPass()) {
                _loadedGame.setError(CANNOT_PASS);
                return -1;
            }
//            if (_loadedGameCopy.getStatus() == Playing.CAN_PLAY) {
//                _loadedGameCopy.getPassOrFinish()
//                        .set(player_, BoolVal.TRUE);
//            }
            //        } else {
//            Bytes players2_ = new Bytes();
//            Bytes players_ = new Bytes();
//            byte next2_ = _loadedGame.playersAfter(players2_);
//            byte next_ = _loadedGameCopy.playersAfter(players_);
//            _loadedGameCopy.play(curHand_);
//            if (_trick.total() < _loadedGameCopy.getProgressingTrick().total()) {
//                _loadedGame.setError(MESSAGE_ERROR);
//                return false;
//            }
//            _trick.total()+","+_loadedGameCopy.getProgressingTrick().total()
//            _loadedGameCopy.getProgressingTrick().ajouter(curHand_,
//                    player_);
        }
        _loadedGameCopy.addCardsToCurrentTrickAndLoop(
                curHand_);
        if (koNext(_loadedGame, _trick, _i + 1, until(_loadedGameCopy, _trick))) {
            return -1;
        }
//        if (exist(_trick, _i + 1, until(_loadedGameCopy, _trick))) {
//            _loadedGame.setError(MESSAGE_ERROR);
//            return -1;
//        }
        return _loadedGameCopy.getProgressingTrick().total();
    }

    private static void koNext(GamePresident _loadedGame, TrickPresidentIndexesCheck _trick) {
        for (int i: _trick.getIndexes()) {
            koNext(_loadedGame,_trick.getTrick(),i, i+1);
        }
    }
    private static boolean koNext(GamePresident _loadedGame, TrickPresident _trick, int _i, int _to) {
        if (exist(_trick, _i, _to)) {
            _loadedGame.setError(NO_CARD_AFTER_FINISHED_DIRECTLY_CARD);
            return true;
        }
        return false;
    }
    private static int until(GamePresident _loadedGameCopy, TrickPresident _trick) {
        if (_loadedGameCopy.getProgressingTrick().estVide()) {
            return _trick.total();
        }
        return _loadedGameCopy.getProgressingTrick().total();
    }
    private static boolean exist(TrickPresident _trick, int _from, int _to) {
        int total_ = NumberUtil.min(_trick.total(),_to);
        for (int i = _from; i < total_; i++) {
            if (!_trick.carte(i).estVide()) {
                return true;
            }
        }
        return false;
    }

    private static TrickPresident trick(GamePresident _loadedGame, int _ind) {
        CustList<TrickPresident> union_ = new CustList<TrickPresident>(_loadedGame.getTricks());
        union_.add(_loadedGame.getProgressingTrick());
        if (!union_.isValidIndex(_ind)) {
            return new TrickPresident();
        }
        return union_.get(_ind);
    }

    private static boolean sameStrength(HandPresident _curHand) {
        if (_curHand.estVide()) {
            return true;
        }
        Bytes str_ = new Bytes();
        for (CardPresident c : _curHand) {
            str_.add(c.getForce());
        }
        return NumberUtil.eq(str_.getMinimum((byte) 0),
                str_.getMaximum((byte) 0));
    }

    static void cancelStarter(CustList<TrickPresident> _tricks) {
        for (TrickPresident t: _tricks) {
            t.setEntameur(-1);
        }
    }
}
