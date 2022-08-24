package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.*;
import code.util.CustList;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class CheckerGameTarotWithRules {

    private static final String BAD_DECLARING = "Bad declaring";
    private static final String BAD_PLAYING = "Bad playing";
    private static final String BAD_MATCHING_WITH_TRICK_LEADER = "Bad matching with trick leader";
    private static final String NOT_BOTH_KIND_OF_DECLARING_SLAM = "Not both kind of declaring slam";
    private static final String A_CARD_IS_MISSING_OR_EXCEDING_FOR_DISCARDING = "A card is missing or exceding for discarding";
    private static final String A_CARD_IS_MISSING_FOR_DISCARDING = "A card is missing for discarding";
    private static final String THIS_CARD_IS_NOT_DISCARDABLE = "This card is not discardable";
    private static final String A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE = "A card must be called among all possible";
    private static final String THERE_SHOULD_NOT_BE_ANY_TRICK = "There should not be any trick";
    private static final String NO_ALLOWED_BID = "no allowed bid";
    private static final String ERROR_CALLING_MUST_BE_BEFORE_DISCARD = "error calling must be before discard";
    private static final String ERROR_BID_MUST_BE_BEFORE_CALLING = "error bid must be before calling";
    private static final String TOO_MUCH_BIDS = "too much bids";
    private static final String INVALID_BID = "invalid bid";
    private static final String ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE = "all cards at remaining cards are not used once";
    private static final String BAD_COUNT_FOR_HANDS = "Bad count for hands";
    private static final String TRICK_WITH_BAD_COUNT = "trick with bad count";
    private static final String BAD_TRICK = "bad trick";
    private static final String BAD_COUNT_FOR_REMAINING_CARDS = "Bad count for remaining cards";
    private static final String BAD_COUNT_FOR_DEAL = "Bad count for deal";
    private static final String INVALID_RULES = "invalid rules";
    private static final String BAD_CARD = "bad card";

    private CheckerGameTarotWithRules() {
    }

    public static void check(GameTarot _loadedGame) {
        RulesTarot rules_ = _loadedGame.getRegles();
        if (koBeforeLoad(_loadedGame)) {
            return;
        }
        _loadedGame.loadGame();
        CustList<TrickTarot> allTricks_ = _loadedGame.unionPlis();
        HandTarot cards_ = retrieveCards(_loadedGame, allTricks_);
        if (koCards(_loadedGame, cards_) || koTricksCore(_loadedGame, rules_, allTricks_)) {
            return;
        }
        boolean noTrick_ = noTrick(allTricks_);
        Bytes players_ = _loadedGame.orderedPlayers(_loadedGame
                .getDistribution().getDealer());
        DealTarot deal_ = buildDeal(_loadedGame, allTricks_);
        if (koHandsDog(_loadedGame, rules_, allTricks_, players_, deal_)) {
            return;
        }
        completeDeal(_loadedGame, allTricks_, deal_);
        boolean allCardsUsedOnce_ = allCardsUsedOnce(deal_);
        if (!allCardsUsedOnce_) {
            _loadedGame
                    .setError(ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE);
            return;
        }
        GameTarot loadedGameCopy_ = new GameTarot(_loadedGame.getType(), deal_,
                rules_);
        if (koBidsCallDiscard(_loadedGame, rules_, allTricks_, noTrick_, deal_, loadedGameCopy_)) {
            return;
        }
        int ind_ = 1;
        loadedGameCopy_.setPliEnCours(true);
        while (true) {
            if (!keepTrick(_loadedGame,allTricks_,loadedGameCopy_,ind_)) {
                return;
            }
            ind_++;
            if (!loadedGameCopy_.keepPlayingCurrentGame()) {
                loadedGameCopy_.ajouterPetitAuBoutPliEnCours();
                return;
            }
            loadedGameCopy_.ajouterPetitAuBoutPliEnCours();
            loadedGameCopy_.setPliEnCours(true);
        }
    }

    private static boolean koBeforeLoad(GameTarot _loadedGame) {
        RulesTarot rules_ = _loadedGame.getRegles();
        if (!rules_.isValidRules()) {
            _loadedGame.setError(INVALID_RULES);
            return true;
        }
        if (_loadedGame.getDeclaresMiseres().size() != _loadedGame.getNombreDeJoueurs() || _loadedGame.getConfidence().size() != _loadedGame.getNombreDeJoueurs()) {
            _loadedGame.setError(BAD_COUNT_FOR_DEAL);
            return true;
        }
        for (CustList<BoolVal> b: _loadedGame.getConfidence()) {
            if (b.size() != _loadedGame.getNombreDeJoueurs()) {
                _loadedGame.setError(BAD_COUNT_FOR_DEAL);
                return true;
            }
        }
        if (_loadedGame.getDeclaresHandfuls().size() != _loadedGame.getNombreDeJoueurs() || _loadedGame.getHandfuls().size() != _loadedGame.getNombreDeJoueurs()) {
            _loadedGame.setError(BAD_COUNT_FOR_DEAL);
            return true;
        }
        if (_loadedGame.getScores().size() != _loadedGame.getNombreDeJoueurs()) {
            _loadedGame.setError(BAD_COUNT_FOR_REMAINING_CARDS);
            return true;
        }
        if (_loadedGame.getDistribution().nombreDeMains() != rules_.getDealing().getId().getNombreJoueurs() + 1) {
            _loadedGame.setError(BAD_COUNT_FOR_DEAL);
            return true;
        }
        if (_loadedGame.getDistribution().derniereMain().total() != rules_.getDealing().getNombreCartesChien()) {
            _loadedGame.setError(BAD_COUNT_FOR_REMAINING_CARDS);
            return true;
        }
        return false;
    }

    private static boolean keepTrick(GameTarot _loadedGame, CustList<TrickTarot> _allTricks, GameTarot _loadedGameCopy, int _ind) {
        if (koStarter(_loadedGame, _allTricks, _loadedGameCopy, _ind)) {
            return false;
        }
        TrickTarot trick_ = firstTrick(_loadedGame, _allTricks, _ind);
        for (byte p : _loadedGameCopy.orderedPlayers(_loadedGameCopy
                .getEntameur())) {
            if (!keepTrickIt(_loadedGame,_loadedGameCopy,trick_,p)) {
                return false;
            }
        }
        return true;
    }

    private static boolean koStarter(GameTarot _loadedGame, CustList<TrickTarot> _allTricks, GameTarot _loadedGameCopy, int _ind) {
        if (_ind == _allTricks.size()) {
            if (_allTricks.get(_ind - 1).getVuParToutJoueur()) {
                if (_allTricks.get(_ind - 1).getRamasseur() != _loadedGame
                        .getPliEnCours().getEntameur()) {
                    _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                    return true;
                }
            } else if (_loadedGameCopy.getEntameur() != _loadedGame
                    .getPliEnCours().getEntameur()) {
                _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                return true;
            }
        } else {
            if (_allTricks.get(_ind - 1).getVuParToutJoueur()) {
                if (_allTricks.get(_ind - 1).getRamasseur() != _allTricks
                        .get(_ind).getEntameur()) {
                    _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                    return true;
                }
            } else if (_loadedGameCopy.getEntameur() != _allTricks.get(_ind)
                    .getEntameur()) {
                _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                return true;
            }
        }
        return false;
    }

    private static TrickTarot firstTrick(GameTarot _loadedGame, CustList<TrickTarot> _allTricks, int _ind) {
        TrickTarot trick_;
        if (_ind == _allTricks.size()) {
            trick_ = _loadedGame.getPliEnCours();
        } else {
            trick_ = _allTricks.get(_ind);
        }
        return trick_;
    }

    private static boolean keepTrickIt(GameTarot _loadedGame, GameTarot _loadedGameCopy, TrickTarot _trick,byte _p) {
        if (!_trick.aJoue(_p, _loadedGameCopy.getNombreDeJoueurs())) {
            return false;
        }
        CardTarot ct_ = _trick.carteDuJoueur(_p,
                _loadedGameCopy.getNombreDeJoueurs());
        if (!_loadedGameCopy.autorise(ct_)) {
            _loadedGame.setError(BAD_PLAYING);
            return false;
        }
        if (!_loadedGameCopy.premierTour()) {
            _loadedGameCopy.jouer(_p, ct_);
            _loadedGameCopy.ajouterUneCarteDansPliEnCours(ct_);
            return true;
        }
        if (!_loadedGameCopy.pasJeuMisere()) {
            if (!_loadedGame.getAnnoncesMiseres(_p).isEmpty() || !_loadedGame.getAnnoncesPoignees(_p).isEmpty() || !_loadedGame.getPoignee(_p).estVide()) {
                _loadedGame.setError(BAD_DECLARING);
                return false;
            }
            _loadedGameCopy.jouer(_p, ct_);
            _loadedGameCopy.ajouterUneCarteDansPliEnCours(ct_);
            return true;
        }
        return koDecl(_loadedGame, _loadedGameCopy, _p, ct_);
    }

    private static boolean koDecl(GameTarot _loadedGame, GameTarot _loadedGameCopy, byte _p, CardTarot _ct) {
        for (Miseres m : _loadedGame.getAnnoncesMiseres(_p)) {
            if (!_loadedGame.getRegles().getMiseres()
                    .containsObj(m) || !_loadedGame.getAnnoncesMiseresPossibles(_p)
                    .containsObj(m)) {
                _loadedGame.setError(BAD_DECLARING);
                return false;
            }
        }
        _loadedGameCopy.setAnnoncesMiseres(_p,
                _loadedGame.getAnnoncesMiseres(_p));
        if (_loadedGame.getAnnoncesPoignees(_p).size() > 1) {
            _loadedGame.setError(BAD_DECLARING);
            return false;
        }
        if (_loadedGame.getAnnoncesPoignees(_p).size() == 1) {
            Handfuls h_ = _loadedGame.getAnnoncesPoignees(_p)
                    .first();
            if (!_loadedGame.getRegles().poigneeAutorisee(h_) || !_loadedGameCopy.isValidHandful(h_,
                    _loadedGame.getPoignee(_p), excludedTrumps(_loadedGame, _p)
            )) {
                _loadedGame.setError(BAD_DECLARING);
                return false;
            }
            _loadedGameCopy.setAnnoncesPoignees(_p,
                    _loadedGame.getAnnoncesPoignees(_p));
            _loadedGameCopy.ajouterPoignee(
                    _loadedGame.getPoignee(_p), _p);
        } else {
            if (!_loadedGame.getPoignee(_p).estVide()) {
                _loadedGame.setError(BAD_DECLARING);
                return false;
            }
        }
        _loadedGameCopy.jouer(_p, _ct);
        _loadedGameCopy.ajouterUneCarteDansPliEnCours(_ct);
        return true;
    }

    private static HandTarot excludedTrumps(GameTarot _loadedGame, byte _p) {
        HandTarot excludedTrumps_ = new HandTarot();
        for (CardTarot c : _loadedGame.getDistribution()
                .hand(_p).couleur(Suit.TRUMP)) {
            if (_loadedGame.getPoignee(_p).contient(c)) {
                continue;
            }
            excludedTrumps_.ajouter(c);
        }
        if (!_loadedGame.getPoignee(_p).contient(
                CardTarot.EXCUSE)) {
            excludedTrumps_.ajouter(CardTarot.EXCUSE);
        }
        return excludedTrumps_;
    }

    private static boolean koCards(GameTarot _loadedGame, HandTarot _cards) {
        for (CardTarot c : _cards) {
            if (!c.getId().isJouable()) {
                _loadedGame.setError(BAD_CARD);
                return true;
            }
        }
        return false;
    }

    private static boolean koBidsCallDiscard(GameTarot _loadedGame, RulesTarot _rules, CustList<TrickTarot> _allTricks, boolean _noTrick, DealTarot _deal, GameTarot _loadedGameCopy) {
        if (_loadedGameCopy.avecContrat()) {
            if (koDeclBids(_loadedGame, _loadedGameCopy)) {
                return true;
            }
            trySetStarter(_loadedGame, _loadedGameCopy);
            if (koCallBid(_loadedGame, _loadedGameCopy)) {
                _loadedGame.setError(ERROR_BID_MUST_BE_BEFORE_CALLING);
                return true;
            }
            if (koCallDiscard(_loadedGame, _allTricks)) {
                _loadedGame
                        .setError(ERROR_CALLING_MUST_BE_BEFORE_DISCARD);
                return true;
            }
        } else {
            if (_loadedGame.contrats() > 0) {
                _loadedGame.setError(NO_ALLOWED_BID);
                return true;
            }
            _loadedGameCopy.setEntameur(_loadedGameCopy.playerAfter(_loadedGame
                    .getDistribution().getDealer()));
        }
        if (noPlay(_loadedGame)) {
            if (!_allTricks.isEmpty()) {
                _loadedGame.setError(THERE_SHOULD_NOT_BE_ANY_TRICK);
                return true;
            }
            if (!_loadedGame.getPliEnCours().estVide()) {
                _loadedGame.setError(THERE_SHOULD_NOT_BE_ANY_TRICK);
            }
            return true;
        }
        return koBeforePlayOrIncomplete(_loadedGame, _rules, _allTricks, _noTrick, _deal, _loadedGameCopy);
    }

    private static boolean koCallBid(GameTarot _loadedGame, GameTarot _loadedGameCopy) {
        return !_loadedGame.getCarteAppelee().estVide() && _loadedGameCopy.keepBidding();
    }

    private static boolean noPlay(GameTarot _loadedGame) {
        return !_loadedGame.getContrat().isJouerDonne() && _loadedGame.pasJeuApresPasse();
    }

    private static void trySetStarter(GameTarot _loadedGame, GameTarot _loadedGameCopy) {
        if (!_loadedGameCopy.getContrat().isJouerDonne() && !_loadedGameCopy.pasJeuApresPasse()) {
            _loadedGameCopy.initPlayWithoutBid();
            _loadedGameCopy.setEntameur(_loadedGameCopy
                    .playerAfter(_loadedGame.getDistribution()
                            .getDealer()));
        }
    }

    private static boolean koCallDiscard(GameTarot _loadedGame, CustList<TrickTarot> _allTricks) {
        return _loadedGame.getRegles().getDiscardAfterCall() && _loadedGame.getRegles().getDealing().callCard()
                && _loadedGame.existePreneur() && (!_allTricks.isEmpty() || !_loadedGame.getPliEnCours()
                .estVide())
                && _loadedGame.getCarteAppelee().estVide();
    }

    private static boolean noTrick(CustList<TrickTarot> _allTricks) {
        boolean noTrick_ = true;
        for (TrickTarot t : _allTricks) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            noTrick_ = false;
        }
        return noTrick_;
    }

    private static void completeDeal(GameTarot _loadedGame, CustList<TrickTarot> _allTricks, DealTarot _deal) {
        if (_loadedGame.getContrat().getJeuChien() == PlayingDog.WITH) {
            if (!_allTricks.isEmpty()) {
                _deal.hand(_loadedGame.getPreneur()).ajouterCartes(
                        _allTricks.first().getCartes());
            } else {
                _deal.hand(_loadedGame.getPreneur()).ajouterCartes(
                        _loadedGame.getPliEnCours().getCartes());
            }
            _deal.hand(_loadedGame.getPreneur()).supprimerCartes(
                    _loadedGame.getDistribution().derniereMain());
        }
    }

    private static boolean koHandsDog(GameTarot _loadedGame, RulesTarot _rules, CustList<TrickTarot> _allTricks, Bytes _players, DealTarot _deal) {
        boolean completed_ = !_allTricks.isEmpty();
        if (completed_) {
            for (byte p : _players) {
                if (_deal.hand(p).total() != _rules.getDealing()
                        .getNombreCartesParJoueur()) {
                    _loadedGame.setError(BAD_COUNT_FOR_HANDS);
                    return true;
                }
            }
            return false;
        }
        return koHandsDogIncomplete(_loadedGame, _rules, _players, _deal);
    }

    private static boolean koHandsDogIncomplete(GameTarot _loadedGame, RulesTarot _rules, Bytes _players, DealTarot _deal) {
        if (_loadedGame.getContrat().getJeuChien() == PlayingDog.WITH) {
            for (byte p : _players) {
                if (p == _loadedGame.getPreneur()) {
                    continue;
                }
                if (_deal.hand(p).total() != _rules.getDealing()
                        .getNombreCartesParJoueur()) {
                    _loadedGame.setError(BAD_COUNT_FOR_HANDS);
                    return true;
                }
            }
            return false;
        }
        for (byte p : _players) {
            if (_deal.hand(p).total() != _rules.getDealing()
                    .getNombreCartesParJoueur()) {
                _loadedGame.setError(BAD_COUNT_FOR_HANDS);
                return true;
            }
        }
        return false;
    }

    private static DealTarot buildDeal(GameTarot _loadedGame, CustList<TrickTarot> _allTricks) {
        DealTarot deal_ = new DealTarot(_loadedGame.getDistribution());
        for (TrickTarot t : _allTricks) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            for (CardTarot c : t) {
                byte player_ = t.joueurAyantJoue(c);
                deal_.hand(player_).ajouter(c);
            }
        }
        if (_loadedGame.getPliEnCours().getVuParToutJoueur()) {
            for (CardTarot c : _loadedGame.getPliEnCours()) {
                byte player_ = _loadedGame.getPliEnCours()
                        .joueurAyantJouePliEnCours(c,
                                _loadedGame.getNombreDeJoueurs());
                deal_.hand(player_).ajouter(c);
            }
        }
        return deal_;
    }

    private static boolean koTricksCore(GameTarot _loadedGame, RulesTarot _rules, CustList<TrickTarot> _allTricks) {
        if (koTricksCoreSeen(_loadedGame, _allTricks)) {
            return true;
        }
        if (koTricksCoreDoneTricks(_loadedGame, _rules, _allTricks)) {
            return true;
        }
        if (!_loadedGame.getPliEnCours().getVuParToutJoueur()) {
            if (_loadedGame.getPliEnCours().total() > _rules.getDealing()
                    .getNombreCartesChien()) {
                _loadedGame.setError(TRICK_WITH_BAD_COUNT);
                return true;
            }
        } else {
            if (_loadedGame.getPliEnCours().total() > _loadedGame
                    .getNombreDeJoueurs()) {
                _loadedGame.setError(TRICK_WITH_BAD_COUNT);
                return true;
            }
        }
        return false;
    }

    private static boolean koTricksCoreDoneTricks(GameTarot _loadedGame, RulesTarot _rules, CustList<TrickTarot> _allTricks) {
        for (TrickTarot t : _allTricks) {
            if (!t.getVuParToutJoueur()) {
                if (t.total() > _rules.getDealing().getNombreCartesChien()) {
                    _loadedGame.setError(TRICK_WITH_BAD_COUNT);
                    return true;
                }
            } else {
                if (t.total() != _loadedGame.getNombreDeJoueurs()) {
                    _loadedGame.setError(TRICK_WITH_BAD_COUNT);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean koTricksCoreSeen(GameTarot _loadedGame, CustList<TrickTarot> _allTricks) {
        int nbTricks_ = _allTricks.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nbTricks_; i++) {
            if (i == IndexConstants.FIRST_INDEX) {
                if (_allTricks.get(i).getVuParToutJoueur()) {
                    _loadedGame.setError(BAD_TRICK);
                    return true;
                }
            } else {
                if (!_allTricks.get(i).getVuParToutJoueur()) {
                    _loadedGame.setError(BAD_TRICK);
                    return true;
                }
            }
        }
        return false;
    }

    private static HandTarot retrieveCards(GameTarot _loadedGame, CustList<TrickTarot> _allTricks) {
        HandTarot cards_ = new HandTarot();
        for (TrickTarot t : _allTricks) {
            for (CardTarot c : t) {
                cards_.ajouter(c);
            }
        }
        for (CardTarot c : _loadedGame.getPliEnCours()) {
            cards_.ajouter(c);
        }
        for (HandTarot c : _loadedGame.getDistribution()) {
            cards_.ajouterCartes(c);
        }
        for (byte p : _loadedGame.orderedPlayers(_loadedGame.getDistribution()
                .getDealer())) {
            for (CardTarot c : _loadedGame.getPoignee(p)) {
                cards_.ajouter(c);
            }
        }
        for (CardTarot c : _loadedGame.getCarteAppelee()) {
            cards_.ajouter(c);
        }
        return cards_;
    }

    private static boolean allCardsUsedOnce(DealTarot _deal) {
        boolean allCardsUsedOnce_ = true;
        for (CardTarot c : HandTarot.pileBase()) {
            int nbUses_ = 0;
            for (HandTarot t : _deal) {
                for (CardTarot c2_ : t) {
                    if (c == c2_) {
                        nbUses_++;
                    }
                }
            }
            if (nbUses_ != 1) {
                allCardsUsedOnce_ = false;
                break;
            }
        }
        return allCardsUsedOnce_;
    }

    private static boolean koDeclBids(GameTarot _loadedGame, GameTarot _loadedGameCopy) {
        Bytes players_;
        players_ = _loadedGameCopy
                .orderedPlayers(_loadedGameCopy.playerAfter(_loadedGameCopy
                        .getDistribution().getDealer()));
        int i_ = 0;
        while (i_ < _loadedGame.contrats()) {
            byte player_ = players_.get(i_);
            if (!_loadedGame.contrat(i_).estDemandable(
                    _loadedGameCopy.getContrat())) {
                _loadedGame.setError(INVALID_BID);
                return true;
            }
            if (!_loadedGameCopy.allowedBids().containsObj(
                    _loadedGame.contrat(i_))) {
                _loadedGame.setError(INVALID_BID);
                return true;
            }
            _loadedGameCopy.ajouterContrat(_loadedGame.contrat(i_), player_);
            if (!_loadedGameCopy.keepBidding()) {
                if (_loadedGame.contrats() > i_ + 1) {
                    _loadedGame.setError(TOO_MUCH_BIDS);
                    return true;
                }
                break;
            }
            i_++;
        }
        return false;
    }

    private static boolean koBeforePlayOrIncomplete(GameTarot _loadedGame, RulesTarot _rules, CustList<TrickTarot> _allTricks, boolean _noTrick, DealTarot _deal, GameTarot _loadedGameCopy) {
        if (_loadedGame.avecContrat()) {
            if (_loadedGame.existePreneur() && _allTricks.isEmpty()
                    && _loadedGame.getPliEnCours().estVide() && _loadedGame.isCallingState()) {
                return true;
            }
            if (koBid(_loadedGame, _rules, _allTricks, _loadedGameCopy)) {
                return true;
            }
            if (koWhenNoTrick(_noTrick, _loadedGame)) {
                return true;
            }
            if (_loadedGame.getContrat().isJouerDonne() && _loadedGame.chelemAnnonce(_loadedGame.getPreneur())) {
                _loadedGameCopy.annoncerUnChelem(_loadedGame.getPreneur());
                _loadedGameCopy.setEntameur(_loadedGame.getPreneur());
            } else {
                _loadedGameCopy.setEntameur(_loadedGameCopy.playerAfter(_deal.getDealer()));
            }
        } else {
            if (koWhenNoTrick(_noTrick, _loadedGame)) {
                return true;
            }
            _loadedGameCopy.setEntameur(_loadedGameCopy.playerAfter(_deal.getDealer()));
        }
        return false;
    }

    private static boolean koWhenNoTrick(boolean _noTrick, GameTarot _loadedGame) {
        if (_noTrick) {
            if (!_loadedGame.getPliEnCours().getVuParToutJoueur()) {
                return true;
            }
            return _loadedGame.getPliEnCours().estVide();
        }
        return false;
    }

    private static boolean koBid(GameTarot _loadedGame, RulesTarot _rules, CustList<TrickTarot> _allTricks, GameTarot _loadedGameCopy) {
        if (_loadedGame.getContrat().getJeuChien() == PlayingDog.WITH) {
            return koBidWith(_loadedGame, _rules, _allTricks, _loadedGameCopy);
        }
        if (_loadedGame.getContrat().isJouerDonne()) {
            _loadedGameCopy.gererChienInconnu();
            TrickTarot discardedCards_;
            discardedCards_ = _allTricks.first();
            if (!HandTarot.equalsSet(_loadedGameCopy.getPliEnCours()
                    .getCartes(), discardedCards_.getCartes())) {
                _loadedGame
                        .setError(A_CARD_IS_MISSING_OR_EXCEDING_FOR_DISCARDING);
                return true;
            }
            if (_loadedGame.getContrat().isFaireTousPlis() && _loadedGame.chelemAnnonce(_loadedGame.getPreneur())) {
                _loadedGame.setError(NOT_BOTH_KIND_OF_DECLARING_SLAM);
                return true;
            }
        }
        return false;
    }

    private static boolean koBidWith(GameTarot _loadedGame, RulesTarot _rules, CustList<TrickTarot> _allTricks, GameTarot _loadedGameCopy) {
        TrickTarot discardedCards_ = discardedCards(_loadedGame, _allTricks);
        if (!_rules.getDiscardAfterCall()) {
            _loadedGameCopy.ajouterCartes(_loadedGameCopy.getPreneur(),
                    _loadedGameCopy.derniereMain());
            HandTarot callableCards_ = _loadedGameCopy.callableCards();
            if (!callableCards_.contientCartes(_loadedGame
                    .getCarteAppelee())) {
                _loadedGame
                        .setError(A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE);
                return true;
            }
            if (koDiscarded(discardedCards_, _loadedGameCopy, _loadedGame)) {
                return true;
            }
            _loadedGameCopy.addCurTrick();
            if (discardedCards_.total() != _loadedGame.derniereMain()
                    .total() && !_loadedGame.getCarteAppelee().estVide()) {
                _loadedGame
                        .setError(A_CARD_IS_MISSING_FOR_DISCARDING);
                return true;
            }
            _loadedGameCopy.setCarteAppelee(_loadedGame
                    .getCarteAppelee());
            _loadedGameCopy.initConfianceAppele();
        } else {
            HandTarot callableCards_ = _loadedGameCopy.callableCards();
            if (!callableCards_.contientCartes(_loadedGame
                    .getCarteAppelee())) {
                _loadedGame
                        .setError(A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE);
                return true;
            }
            _loadedGameCopy.setCarteAppelee(_loadedGame
                    .getCarteAppelee());
            _loadedGameCopy.initConfianceAppele();
            _loadedGameCopy.ajouterCartes(_loadedGameCopy.getPreneur(),
                    _loadedGameCopy.derniereMain());
            if (koDiscarded(discardedCards_, _loadedGameCopy, _loadedGame)) {
                return true;
            }
            _loadedGameCopy.addCurTrick();
        }
        if (_loadedGame.chelemAnnonce(_loadedGame.getPreneur())) {
            _loadedGameCopy.setEntameur(_loadedGame.getPreneur());
        }
        return false;
    }

    private static boolean koDiscarded(TrickTarot _discardedCards, GameTarot _loadedGameCopy, GameTarot _loadedGame) {
        for (CardTarot c : _discardedCards) {
            if (_loadedGameCopy.autoriseEcartDe(c
            ) != ReasonDiscard.NOTHING) {
                _loadedGame.setError(THIS_CARD_IS_NOT_DISCARDABLE);
                return true;
            }
            _loadedGameCopy.ajouterUneCarteDansPliEnCours(
                    _loadedGameCopy.getPreneur(), c);
        }
        return false;
    }

    private static TrickTarot discardedCards(GameTarot _loadedGame, CustList<TrickTarot> _allTricks) {
        TrickTarot discardedCards_;
        if (_allTricks.isEmpty()) {
            discardedCards_ = _loadedGame.getPliEnCours();
        } else {
            discardedCards_ = _allTricks.first();
        }
        return discardedCards_;
    }
}
