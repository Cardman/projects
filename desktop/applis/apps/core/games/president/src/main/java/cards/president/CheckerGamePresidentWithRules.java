package cards.president;

import cards.president.enumerations.CardPresident;
import code.util.CustList;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class CheckerGamePresidentWithRules {

    private static final String MESSAGE_ERROR = "error";

    private CheckerGamePresidentWithRules() {
    }
    public static void check(GamePresident _loadedGame) {
        RulesPresident rules_ = _loadedGame.getRules();
        if (!rules_.isValidRules()) {
            _loadedGame.setError(MESSAGE_ERROR);
            return;
        }
        byte nbPlayers_ = (byte) rules_.getNbPlayers();
        if (_loadedGame.getDeal().nombreDeMains() != nbPlayers_) {
            _loadedGame.setError(MESSAGE_ERROR);
            return;
        }
        if (_loadedGame.getScores().size() != nbPlayers_) {
            _loadedGame.setError(MESSAGE_ERROR);
            return;
        }
        Bytes ranks_ = _loadedGame.getRanks();
        _loadedGame.loadGame();
        CustList<TrickPresident> allTricks_ = _loadedGame.unionPlis();
        HandPresident cards_ = allCards(_loadedGame);
        CustList<TrickPresident> allTricksPlusCurr_ = new CustList<TrickPresident>(allTricks_);
        allTricksPlusCurr_.add(_loadedGame.getProgressingTrick());
        for (TrickPresident t: allTricksPlusCurr_) {
            if (notEmptyAlthough(t)) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        for (CardPresident e : cards_) {
            if (!e.getId().isJouable()) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        DealPresident deal_ = buildDeal(_loadedGame, nbPlayers_, allTricks_);
        if (!ranks_.isEmpty()) {
            if (ranks_.size() != nbPlayers_) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
            Bytes copyRanks_ = new Bytes(ranks_);
            if (copyRanks_.hasDuplicates()) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        chSwitch(_loadedGame, deal_);
    }

    private static void chSwitch(GamePresident _loadedGame, DealPresident _deal) {
        byte nbPlayers_ = (byte) _loadedGame.getRules().getNbPlayers();
        Bytes winners_ = _loadedGame.getWinners();
        Bytes loosers_ = _loadedGame.getLoosers();
        if (!_loadedGame.availableSwitchingCards()) {
            if (!_loadedGame.getSwitchedCards().isEmpty()) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
            checkHandsPlay(_loadedGame, _deal, true, winners_, loosers_);
            return;
        }
        if (_loadedGame.getSwitchedCards().size() != nbPlayers_) {
            _loadedGame.setError(MESSAGE_ERROR);
            return;
        }
        prepareCheck(_loadedGame, _deal, winners_, loosers_);
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

    private static void prepareCheck(GamePresident _loadedGame, DealPresident _deal, Bytes _winners, Bytes _loosers) {
        boolean readyToPlay_ = true;
        for (byte w : _winners) {
            int ind_ = _winners.indexOfNb(w);
            int nbGivenCards_ = _loadedGame.nombresCartesEchangesMax()
                    - ind_;
            HandPresident sw_ = _loadedGame.getSwitchedCards().get(w);
            if (w == DealPresident.NUMERO_UTILISATEUR) {
                if (!sw_.estVide()) {
                    if (sw_.total() != nbGivenCards_) {
                        _loadedGame.setError(MESSAGE_ERROR);
                        return;
                    }
                } else {
                    readyToPlay_ = false;
                }
                continue;
            }
            if (sw_.total() != nbGivenCards_) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        prepareCheck(_loadedGame, _deal, readyToPlay_, _loosers, _winners);
    }

    private static void prepareCheck(GamePresident _loadedGame, DealPresident _deal, boolean _readyToPlay, Bytes _loosers, Bytes _winners) {
        byte nbPlayers_ = (byte) _loadedGame.getRules().getNbPlayers();
        for (byte l : _loosers) {
            int ind_ = _loosers.indexOfNb(l);
            int nbGivenCards_ = _loadedGame.nombresCartesEchangesMax()
                    - ind_;
            if (_loadedGame.getSwitchedCards().get(l).total() != nbGivenCards_) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
            if (_winners.containsObj(p) || _loosers.containsObj(p)) {
                continue;
            }
            if (!_loadedGame.getSwitchedCards().get(p).estVide()) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        if (_readyToPlay) {
            readyToPlay(_loadedGame, _deal, _winners, _loosers);
            return;
        }
        notReadToPlay(_loadedGame, _deal, _loosers, _winners);
    }

    private static void notReadToPlay(GamePresident _loadedGame, DealPresident _deal, Bytes _loosers, Bytes _winners) {
        if (oneCardPlayAtLeast(_loadedGame)) {
            _loadedGame.setError(MESSAGE_ERROR);
            return;
        }
        for (byte w : _winners) {
            if (w == DealPresident.NUMERO_UTILISATEUR) {
                continue;
            }
            byte pl_ = GamePresident.getMatchingLoser(_winners,_loosers, w);
            HandPresident hl_ = new HandPresident(_deal.hand(w));
            hl_.ajouterCartes(_loadedGame.getSwitchedCards()
                    .get(pl_));
            if (!hl_.containsCards(_loadedGame.getSwitchedCards()
                    .get(w))) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        checkGiftsHandsPlay(_loadedGame, _deal, false, _loosers, _winners);
    }

    private static void readyToPlay(GamePresident _loadedGame, DealPresident _deal, Bytes _winners, Bytes _loosers) {
        byte nbPlayers_ = (byte) _loadedGame.getRules().getNbPlayers();
        for (byte w : _winners) {
            byte pl_ = GamePresident.getMatchingLoser(_winners, _loosers, w);
            HandPresident hl_ = new HandPresident(_deal.hand(pl_));
            hl_.ajouterCartes(_loadedGame.getSwitchedCards()
                    .get(pl_));
            if (!hl_.containsCards(_loadedGame.getSwitchedCards()
                    .get(w))) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        for (byte i = 0; i< nbPlayers_; i++) {
            _deal.hand(i).ajouterCartes(
                    _loadedGame.getSwitchedCards().get(i));
        }
        checkGiftsHandsPlay(_loadedGame, _deal, true, _loosers, _winners);
    }

    private static void checkGiftsHandsPlay(GamePresident _loadedGame, DealPresident _deal, boolean _readyToPlay, Bytes _loosers, Bytes _winners) {
        revSwitchCards(_loadedGame, _deal, _loosers, _winners);
        for (byte l : _loosers) {
            HandPresident hCopy_ = new HandPresident(_deal.hand(l));
            HandPresident hSwitchCopy_ = new HandPresident(_loadedGame
                    .getSwitchedCards().get(l));
            hCopy_.sortCardsBegin();
            hSwitchCopy_.sortCardsBegin();
            int lenGiv_ = hSwitchCopy_.total();
            for (int i = IndexConstants.FIRST_INDEX; i < lenGiv_; i++) {
                byte strGiv_ = hSwitchCopy_.carte(i).getForce();
                byte str_ = hCopy_.carte(i).getForce();
                if (strGiv_ != str_) {
                    _loadedGame.setError(MESSAGE_ERROR);
                    return;
                }
            }
        }
        checkHandsPlay(_loadedGame, _deal, _readyToPlay, _winners, _loosers);
    }

    private static void checkHandsPlay(GamePresident _loadedGame, DealPresident _deal, boolean _readyToPlay, Bytes _winners, Bytes _loosers) {
        byte nbPlayers_ = (byte) _loadedGame.getRules().getNbPlayers();
        int nbCards_ = _loadedGame.getRules().getNbStacks() * HandPresident.pileBase().total();
        int rem_ = nbCards_ % nbPlayers_;
        boolean noRem_ = rem_ == 0;
        int nbCardsPerPlayer_ = nbCards_ / nbPlayers_;
        if (noRem_) {
            for (HandPresident h : _deal) {
                if (h.total() != nbCardsPerPlayer_) {
                    _loadedGame.setError(MESSAGE_ERROR);
                    return;
                }
            }
        } else {
            for (HandPresident h : _deal) {
                if (h.total() > nbCardsPerPlayer_ + 1 || h.total() < nbCardsPerPlayer_) {
                    _loadedGame.setError(MESSAGE_ERROR);
                    return;
                }
            }
        }
        afterHands(_loadedGame, _deal, _readyToPlay, _winners, _loosers);
    }

    private static void afterHands(GamePresident _loadedGame, DealPresident _deal, boolean _readyToPlay, Bytes _winners, Bytes _loosers) {
        if (!allCardsUsedNb(_loadedGame.getRules(), _deal)) {
            _loadedGame.setError(MESSAGE_ERROR);
            return;
        }
        if (!_readyToPlay) {
            return;
        }
        switchCards(_loadedGame, _deal, _winners, _loosers);
        if (!oneCardPlayAtLeast(_loadedGame)) {
            return;
        }
        play(_loadedGame, _deal);
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

    private static void revSwitchCards(GamePresident _loadedGame, DealPresident _deal, Bytes _loosers, Bytes _winners) {
        for (byte l : _loosers) {
            byte pl_ = GamePresident.getMatchingWinner(_winners,_loosers,l);
            _deal.hand(l).supprimerCartes(
                    _loadedGame.getSwitchedCards().get(pl_));
        }
        for (byte w : _winners) {
            byte pl_ =  GamePresident.getMatchingLoser(_winners,_loosers,w);
            _deal.hand(w).supprimerCartes(
                    _loadedGame.getSwitchedCards().get(pl_));
        }
    }

    private static void switchCards(GamePresident _loadedGame, DealPresident _deal, Bytes _winners, Bytes _loosers) {
        byte nbPlayers_ = _loadedGame.getNombreDeJoueurs();
        if (_loadedGame.availableSwitchingCards()) {
            for (byte w : _winners) {
                byte pl_ = GamePresident.getMatchingLoser(_winners,_loosers,w);
                _deal.hand(w).ajouterCartes(
                        _loadedGame.getSwitchedCards().get(pl_));
            }
            for (byte l : _loosers) {
                byte pl_ = GamePresident.getMatchingWinner(_winners,_loosers,l);
                _deal.hand(l).ajouterCartes(
                        _loadedGame.getSwitchedCards().get(pl_));
            }
            for (byte i = 0; i< nbPlayers_; i++) {
                _deal.hand(i).supprimerCartes(
                        _loadedGame.getSwitchedCards().get(i));
            }
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
        RulesPresident rules_ = _loadedGame.getRules();
        GamePresident loadedGameCopy_ = new GamePresident(
                _loadedGame.getType(), _deal, rules_, _loadedGame.getRanks());
        loadedGameCopy_.copySwitchCards(_loadedGame.getSwitchedCards());
        int ind_ = 0;
        loadedGameCopy_.initializeFirstTrick();
        while (true) {
            if (!keepTrick(_loadedGame,rules_, loadedGameCopy_,ind_)) {
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
    private static boolean keepTrick(GamePresident _loadedGame, RulesPresident _rules, GamePresident _loadedGameCopy, int _ind){
        byte nbPlayers_ = (byte) _rules.getNbPlayers();
        TrickPresident trick_ = trick(_loadedGame, _ind);
        if (trick_.estVide() || _ind > _loadedGame.getTricks().size()) {
            return false;
        }
        int nbCardsPerPlayerTrick_ = trick_.getNombreDeCartesParJoueur();
        int nbHands_ = trick_.total();
        for (int i = IndexConstants.FIRST_INDEX; i < nbHands_; i++) {
            if (!keepTrickIt(_loadedGame, trick_,_loadedGameCopy,i)) {
                return false;
            }
        }
        if (nbCardsPerPlayerTrick_ == 0) {
            _loadedGameCopy.initializeTrick(trick_.getPlayer(
                    trick_.total(), nbPlayers_));
        }
//        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
//            _loadedGameCopy.getPassOrFinish().set(p,
//                    ComparatorBoolean.of(_loadedGameCopy.getDeal().hand(p).estVide()));
//        }
        return true;
    }
    private static boolean keepTrickIt(GamePresident _loadedGame, TrickPresident _trick, GamePresident _loadedGameCopy, int _i){
//        byte nbPlayers_ = (byte) _rules.getNbPlayers();
        int nbCardsPerPlayerTrick_ = _trick.getNombreDeCartesParJoueur();
//        byte player_ = _trick.getPlayer(_i, nbPlayers_);
        HandPresident curHand_ = _trick.carte(_i);
        if (!sameStrength(curHand_)) {
            _loadedGame.setError(MESSAGE_ERROR);
            return false;
        }
        int count_ = curHand_.total();
        if (!curHand_.estVide()) {
            if (!_loadedGameCopy.allowPlaying(curHand_)) {
                _loadedGame.setError(MESSAGE_ERROR);
                return false;
            }
            _loadedGameCopy.addCardsToCurrentTrick(
                    curHand_);
//            if (_loadedGameCopy.getDeal().hand(player_)
//                    .estVide()) {
//                _loadedGameCopy.getPassOrFinish()
//                        .set(player_, BoolVal.TRUE);
//            }
            return okCount(_loadedGame, _trick, _i, count_, _loadedGameCopy.getProgressingTrick());
        }
        if (nbCardsPerPlayerTrick_ != 0) {
            count_ = nbCardsPerPlayerTrick_;
            if (_loadedGameCopy.getProgressingTrick().getCartes().estVide()) {
                _loadedGame.setError(MESSAGE_ERROR);
                return false;
            }
            if (!_loadedGameCopy.canPass()) {
                _loadedGame.setError(MESSAGE_ERROR);
                return false;
            }
//            if (_loadedGameCopy.getStatus() == Playing.CAN_PLAY) {
//                _loadedGameCopy.getPassOrFinish()
//                        .set(player_, BoolVal.TRUE);
//            }
            _loadedGameCopy.addCardsToCurrentTrick(curHand_);
        } else {
            _loadedGameCopy.play(curHand_);
//            _loadedGameCopy.getProgressingTrick().ajouter(curHand_,
//                    player_);
        }
        return okCount(_loadedGame, _trick, _i, count_, _loadedGameCopy.getProgressingTrick());
    }

    private static boolean okCount(GamePresident _loadedGame, TrickPresident _trick, int _i, int _count, TrickPresident _prog) {
        int nbHands_ = _trick.total();
        int nbCardsPerPlayerTrick_ = _trick.getNombreDeCartesParJoueur();
        if (_count != nbCardsPerPlayerTrick_) {
            _loadedGame.setError(MESSAGE_ERROR);
            return false;
        }
        if (_prog.estVide() && _i + 1 < nbHands_ && !_trick.carte(_i + 1).estVide()) {
            _loadedGame.setError(MESSAGE_ERROR);
            return false;
        }
        return true;
    }

    private static TrickPresident trick(GamePresident _loadedGame, int _ind) {
        CustList<TrickPresident> tricks_ = _loadedGame.getTricks();
        TrickPresident trick_;
        TrickPresident firstTrick_;
        if (!tricks_.isEmpty()) {
            firstTrick_ = tricks_.first();
        } else {
            firstTrick_ = _loadedGame.getProgressingTrick();
        }
        if (_ind == 0) {
            trick_ = firstTrick_;
        } else if (_ind + 1 <= tricks_.size()) {
            trick_ = tricks_.get(_ind);
        } else {
            trick_ = _loadedGame.getProgressingTrick();
        }
        return trick_;
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
