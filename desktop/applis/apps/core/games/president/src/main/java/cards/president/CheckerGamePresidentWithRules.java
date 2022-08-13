package cards.president;

import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.util.CustList;
import code.util.*;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
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
        chSwitch(_loadedGame, rules_, ranks_, allTricks_, deal_);
    }

    private static void chSwitch(GamePresident _loadedGame, RulesPresident _rules, Bytes _ranks, CustList<TrickPresident> _allTricks, DealPresident _deal) {
        byte nbPlayers_ = (byte) _rules.getNbPlayers();
        if (!_loadedGame.availableSwitchingCards()) {
            if (!_loadedGame.getSwitchedCards().isEmpty()) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
            checkHandsPlay(_loadedGame, _rules, _ranks, _allTricks, _deal, true);
            return;
        }
        if (_loadedGame.getSwitchedCards().size() != nbPlayers_) {
            _loadedGame.setError(MESSAGE_ERROR);
            return;
        }
        prepareCheck(_loadedGame, _rules, _ranks, _allTricks, _deal);
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

    private static void prepareCheck(GamePresident _loadedGame, RulesPresident _rules, Bytes _ranks, CustList<TrickPresident> _allTricks, DealPresident _deal) {
        boolean readyToPlay_ = true;
        for (byte w : _loadedGame.getWinners()) {
            int ind_ = _loadedGame.getWinners().indexOfNb(w);
            int nbGivenCards_ = _loadedGame.nombresCartesEchangesMax()
                    - ind_;
            if (w == DealPresident.NUMERO_UTILISATEUR) {
                if (!_loadedGame.getSwitchedCards().get(w).estVide()) {
                    if (_loadedGame.getSwitchedCards().get(w).total() != nbGivenCards_) {
                        _loadedGame.setError(MESSAGE_ERROR);
                        return;
                    }
                } else {
                    readyToPlay_ = false;
                }
                continue;
            }
            if (_loadedGame.getSwitchedCards().get(w).total() != nbGivenCards_) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        prepareCheck(_loadedGame, _rules, _ranks, _allTricks, _deal, readyToPlay_);
    }

    private static void prepareCheck(GamePresident _loadedGame, RulesPresident _rules, Bytes _ranks, CustList<TrickPresident> _allTricks, DealPresident _deal, boolean _readyToPlay) {
        byte nbPlayers_ = (byte) _rules.getNbPlayers();
        for (byte l : _loadedGame.getLoosers()) {
            int ind_ = _loadedGame.getLoosers().indexOfNb(l);
            int nbGivenCards_ = _loadedGame.nombresCartesEchangesMax()
                    - ind_;
            if (_loadedGame.getSwitchedCards().get(l).total() != nbGivenCards_) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
            if (_loadedGame.getWinners().containsObj(p) || _loadedGame.getLoosers().containsObj(p)) {
                continue;
            }
            if (!_loadedGame.getSwitchedCards().get(p).estVide()) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        if (_readyToPlay) {
            readyToPlay(_loadedGame, _rules, _ranks, _allTricks, _deal);
            return;
        }
        notReadToPlay(_loadedGame, _rules, _ranks, _allTricks, _deal);
    }

    private static void notReadToPlay(GamePresident _loadedGame, RulesPresident _rules, Bytes _ranks, CustList<TrickPresident> _allTricks, DealPresident _deal) {
        if (!_loadedGame.getTricks().isEmpty()
                || !_loadedGame.getProgressingTrick().estVide()) {
            _loadedGame.setError(MESSAGE_ERROR);
            return;
        }
        for (byte w : _loadedGame.getWinners()) {
            if (w == DealPresident.NUMERO_UTILISATEUR) {
                continue;
            }
            byte pl_ = _loadedGame.getMatchingLoser(w);
            HandPresident hl_ = new HandPresident(_deal.hand(w));
            hl_.ajouterCartes(_loadedGame.getSwitchedCards()
                    .get(pl_));
            if (!hl_.containsCards(_loadedGame.getSwitchedCards()
                    .get(w))) {
                _loadedGame.setError(MESSAGE_ERROR);
                return;
            }
        }
        checkGiftsHandsPlay(_loadedGame, _rules, _ranks, _allTricks, _deal, false);
    }

    private static void readyToPlay(GamePresident _loadedGame, RulesPresident _rules, Bytes _ranks, CustList<TrickPresident> _allTricks, DealPresident _deal) {
        byte nbPlayers_ = (byte) _rules.getNbPlayers();
        for (byte w : _loadedGame.getWinners()) {
            byte pl_ = _loadedGame.getMatchingLoser(w);
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
        checkGiftsHandsPlay(_loadedGame, _rules, _ranks, _allTricks, _deal, true);
    }

    private static void checkGiftsHandsPlay(GamePresident _loadedGame, RulesPresident _rules, Bytes _ranks, CustList<TrickPresident> _allTricks, DealPresident _deal, boolean _readyToPlay) {
        revSwitchCards(_loadedGame, _deal);
        for (byte l : _loadedGame.getLoosers()) {
            HandPresident hCopy_ = new HandPresident(_deal.hand(l));
            HandPresident hSwitchCopy_ = new HandPresident(_loadedGame
                    .getSwitchedCards().get(l));
            hCopy_.sortCardsBegin();
            hSwitchCopy_.sortCardsBegin();
            int lenGiv_ = hSwitchCopy_.total();
            for (int i = IndexConstants.FIRST_INDEX; i < lenGiv_; i++) {
                byte strGiv_ = hSwitchCopy_.carte(i).strength(false);
                byte str_ = hCopy_.carte(i).strength(false);
                if (strGiv_ != str_) {
                    _loadedGame.setError(MESSAGE_ERROR);
                    return;
                }
            }
        }
        checkHandsPlay(_loadedGame, _rules, _ranks, _allTricks, _deal, _readyToPlay);
    }

    private static void checkHandsPlay(GamePresident _loadedGame, RulesPresident _rules, Bytes _ranks, CustList<TrickPresident> _allTricks, DealPresident _deal, boolean _readyToPlay) {
        byte nbPlayers_ = (byte) _rules.getNbPlayers();
        int nbCards_ = _rules.getNbStacks() * HandPresident.pileBase().total();
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
        afterHands(_loadedGame, _rules, _ranks, _allTricks, _deal, _readyToPlay);
    }

    private static void afterHands(GamePresident _loadedGame, RulesPresident _rules, Bytes _ranks, CustList<TrickPresident> _allTricks, DealPresident _deal, boolean _readyToPlay) {
        byte nbPlayers_ = (byte) _rules.getNbPlayers();
        boolean allCardsUsedNb_ = allCardsUsedNb(_rules, _deal);
        if (!allCardsUsedNb_) {
            _loadedGame.setError(MESSAGE_ERROR);
            return;
        }
        if (!_readyToPlay) {
            return;
        }
        switchCards(_loadedGame, nbPlayers_, _deal);
        if (_allTricks.isEmpty() && _loadedGame.getProgressingTrick().estVide()) {
            return;
        }
        play(_loadedGame, _rules, _ranks, _allTricks, _deal);
    }

    private static DealPresident buildDeal(GamePresident _loadedGame, byte _nbPlayers, CustList<TrickPresident> _allTricks) {
        DealPresident deal_ = new DealPresident(_loadedGame.getDeal());
        int indexCurTrick_ = IndexConstants.FIRST_INDEX;
        for (TrickPresident t : _allTricks) {
            int index_ = IndexConstants.FIRST_INDEX;
            for (HandPresident c : t) {
                byte player_ = t.getPlayer(index_, _nbPlayers);
                deal_.hand(player_).ajouterCartes(c);
                index_++;
            }
        }
        for (HandPresident c : _loadedGame.getProgressingTrick()) {
            byte player_ = _loadedGame.getProgressingTrick().getPlayer(
                    indexCurTrick_, _nbPlayers);
            deal_.hand(player_).ajouterCartes(c);
            indexCurTrick_++;
        }
        return deal_;
    }

    private static void revSwitchCards(GamePresident _loadedGame, DealPresident _deal) {
        for (byte l : _loadedGame.getLoosers()) {
            int ind_ = _loadedGame.getLoosers().indexOfNb(l);
            byte pl_ = _loadedGame.getWinners().get(ind_);
            _deal.hand(l).supprimerCartes(
                    _loadedGame.getSwitchedCards().get(pl_));
        }
        for (byte w : _loadedGame.getWinners()) {
            int ind_ = _loadedGame.getWinners().indexOfNb(w);
            byte pl_ = _loadedGame.getLoosers().get(ind_);
            _deal.hand(w).supprimerCartes(
                    _loadedGame.getSwitchedCards().get(pl_));
        }
    }

    private static void switchCards(GamePresident _loadedGame, byte _nbPlayers, DealPresident _deal) {
        if (_loadedGame.availableSwitchingCards()) {
            for (byte w : _loadedGame.getWinners()) {
                int ind_ = _loadedGame.getWinners().indexOfNb(w);
                byte pl_ = _loadedGame.getLoosers().get(ind_);
                _deal.hand(w).ajouterCartes(
                        _loadedGame.getSwitchedCards().get(pl_));
            }
            for (byte l : _loadedGame.getLoosers()) {
                int ind_ = _loadedGame.getLoosers().indexOfNb(l);
                byte pl_ = _loadedGame.getWinners().get(ind_);
                _deal.hand(l).ajouterCartes(
                        _loadedGame.getSwitchedCards().get(pl_));
            }
            for (byte i = 0; i< _nbPlayers; i++) {
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

    private static void play(GamePresident _loadedGame, RulesPresident _rules, Bytes _ranks, CustList<TrickPresident> _allTricks, DealPresident _deal) {
        GamePresident loadedGameCopy_ = new GamePresident(
                _loadedGame.getType(), _deal, _rules, _ranks);
        loadedGameCopy_.copySwitchCards(_loadedGame.getSwitchedCards());
        int ind_ = 0;
        loadedGameCopy_.initializeFirstTrick();
        while (true) {
            if (!keepTrick(_loadedGame,_rules,_allTricks,loadedGameCopy_,ind_)) {
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
    private static boolean keepTrick(GamePresident _loadedGame, RulesPresident _rules, CustList<TrickPresident> _allTricks, GamePresident _loadedGameCopy, int _ind){
        byte nbPlayers_ = (byte) _rules.getNbPlayers();
        TrickPresident trick_ = trick(_loadedGame, _allTricks, _ind);
        if (trick_.estVide() || _ind > _allTricks.size()) {
            return false;
        }
        int nbCardsPerPlayerTrick_ = trick_.getNombreDeCartesParJoueur();
        int nbHands_ = trick_.total();
        for (int i = IndexConstants.FIRST_INDEX; i < nbHands_; i++) {
            if (!keepTrickIt(_loadedGame,_rules,trick_,_loadedGameCopy,i)) {
                return false;
            }
        }
        if (nbCardsPerPlayerTrick_ == 0) {
            _loadedGameCopy.unionPlis().add(
                    _loadedGameCopy.getProgressingTrick());
            _loadedGameCopy.initializeTrick(trick_.getPlayer(
                    trick_.total(), nbPlayers_));
        }
        for (byte p = IndexConstants.FIRST_INDEX; p < nbPlayers_; p++) {
            _loadedGameCopy.getPassOrFinish().set(p,
                    ComparatorBoolean.of(_loadedGameCopy.getDeal().hand(p).estVide()));
        }
        return true;
    }
    private static boolean keepTrickIt(GamePresident _loadedGame, RulesPresident _rules, TrickPresident _trick, GamePresident _loadedGameCopy, int _i){
        byte nbPlayers_ = (byte) _rules.getNbPlayers();
        int nbCardsPerPlayerTrick_ = _trick.getNombreDeCartesParJoueur();
        byte player_ = _trick.getPlayer(_i, nbPlayers_);
        HandPresident curHand_ = _trick.carte(_i);
        if (!sameStrength(_loadedGameCopy, curHand_)) {
            _loadedGame.setError(MESSAGE_ERROR);
            return false;
        }
        int count_ = curHand_.total();
        if (!curHand_.estVide()) {
            if (!_loadedGameCopy.allowPlaying(player_, curHand_)) {
                _loadedGame.setError(MESSAGE_ERROR);
                return false;
            }
            _loadedGameCopy.addCardsToCurrentTrick(player_,
                    curHand_);
            if (_loadedGameCopy.getDeal().hand(player_)
                    .estVide()) {
                _loadedGameCopy.getPassOrFinish()
                        .set(player_, BoolVal.TRUE);
            }
            return okCount(_loadedGame, _trick, _loadedGameCopy, _i, count_);
        }
        if (nbCardsPerPlayerTrick_ != 0) {
            count_ = nbCardsPerPlayerTrick_;
            if (_loadedGameCopy.getProgressingTrick().getCartes().estVide()) {
                _loadedGame.setError(MESSAGE_ERROR);
                return false;
            }
            if (!_loadedGameCopy.canPass(player_)) {
                _loadedGame.setError(MESSAGE_ERROR);
                return false;
            }
            if (_loadedGameCopy.getStatus(player_) == Playing.CAN_PLAY) {
                _loadedGameCopy.getPassOrFinish()
                        .set(player_, BoolVal.TRUE);
            }
            _loadedGameCopy.addEmptyHandToCurrentTrick(player_);
        } else {
            _loadedGameCopy.getProgressingTrick().ajouter(curHand_,
                    player_);
        }
        return okCount(_loadedGame, _trick, _loadedGameCopy, _i, count_);
    }

    private static boolean okCount(GamePresident _loadedGame, TrickPresident _trick, GamePresident _loadedGameCopy, int _i, int _count) {
        int nbHands_ = _trick.total();
        int nbCardsPerPlayerTrick_ = _trick.getNombreDeCartesParJoueur();
        if (_count != nbCardsPerPlayerTrick_) {
            _loadedGame.setError(MESSAGE_ERROR);
            return false;
        }
        if (_loadedGameCopy.getProgressingTrick().estVide() && _i + 1 < nbHands_ && !_trick.carte(_i + 1).estVide()) {
            _loadedGame.setError(MESSAGE_ERROR);
            return false;
        }
        return true;
    }

    private static TrickPresident trick(GamePresident _loadedGame, CustList<TrickPresident> _allTricks, int _ind) {
        TrickPresident trick_;
        TrickPresident firstTrick_;
        if (!_allTricks.isEmpty()) {
            firstTrick_ = _allTricks.first();
        } else {
            firstTrick_ = _loadedGame.getProgressingTrick();
        }
        if (_ind == 0) {
            trick_ = firstTrick_;
        } else if (_ind + 1 <= _allTricks.size()) {
            trick_ = _allTricks.get(_ind);
        } else {
            trick_ = _loadedGame.getProgressingTrick();
        }
        return trick_;
    }

    private static boolean sameStrength(GamePresident _loadedGameCopy, HandPresident _curHand) {
        if (_curHand.estVide()) {
            return true;
        }
        Bytes str_ = new Bytes();
        for (CardPresident c : _curHand) {
            str_.add(c.strength(_loadedGameCopy.isReversed()));
        }
        return NumberUtil.eq(str_.getMinimum((byte) 0),
                str_.getMaximum((byte) 0));
    }
}
