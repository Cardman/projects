package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.*;
import code.util.CustList;
import code.util.*;

public final class CheckerGameTarotWithRules {

    public static final String BAD_DECLARING="0";
    public static final String BAD_PLAYING="1";
    public static final String A_CARD_IS_MISSING_OR_EXCEDING_FOR_DISCARDING="2";
    public static final String A_CARD_IS_MISSING_FOR_DISCARDING="3";
    public static final String THIS_CARD_IS_NOT_DISCARDABLE="4";
    public static final String A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE="5";
    public static final String THERE_SHOULD_NOT_BE_ANY_TRICK="6";
    public static final String NO_ALLOWED_BID="7";
    public static final String ERROR_CALLING_MUST_BE_BEFORE_DISCARD="8";
    public static final String ERROR_BID_MUST_BE_BEFORE_CALLING="9";
    public static final String TOO_MUCH_BIDS="10";
    public static final String INVALID_BID="11";
    public static final String ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE="12";
    public static final String BAD_COUNT_FOR_HANDS="13";
    public static final String TRICK_WITH_BAD_COUNT="14";
    public static final String BAD_COUNT_FOR_REMAINING_CARDS="15";
    public static final String BAD_COUNT_FOR_DEAL="16";
    public static final String INVALID_RULES="17";
    public static final String BAD_CARD="18";

    private CheckerGameTarotWithRules() {
    }

    public static void check(GameTarot _loadedGame, StringMap<String> _mes) {
        RulesTarot rules_ = _loadedGame.getRegles();
        if (koBeforeLoad(_loadedGame, _mes)) {
            return;
        }
        _loadedGame.loadGame();
//        CustList<TrickTarot> allTricks_ = _loadedGame.getTricks();
        HandTarot cards_ = retrieveCards(_loadedGame);
        if (koCards(_loadedGame, cards_, _mes) || koTricksCoreDoneTricks(_loadedGame, rules_, _mes)) {
            return;
        }
//        boolean noTrick_ = noTrick(allTricks_);
        Ints players_ = _loadedGame.orderedPlayers(_loadedGame
                .getDistribution().getDealer());
        DealTarot deal_ = buildDeal(_loadedGame);
        if (koHandsDog(_loadedGame, rules_, players_, deal_, _mes)) {
            return;
        }
        completeDeal(_loadedGame, deal_);
        boolean allCardsUsedOnce_ = allCardsUsedOnce(deal_);
        if (!allCardsUsedOnce_) {
            _loadedGame
                    .setError(_mes.getVal(ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE));
            return;
        }
        GameTarot loadedGameCopy_ = new GameTarot(_loadedGame.getType(), deal_,
                rules_);
        if (koBidsCallDiscard(_loadedGame, rules_, loadedGameCopy_, _mes)) {
            return;
        }
        int ind_ = 1;
        loadedGameCopy_.firstLead();
        while (true) {
            if (!keepTrick(_loadedGame, loadedGameCopy_,ind_, _mes)) {
                return;
            }
            ind_++;
            if (!loadedGameCopy_.keepPlayingCurrentGame()) {
                loadedGameCopy_.ajouterPetitAuBoutPliEnCours();
                return;
            }
            loadedGameCopy_.ajouterPetitAuBoutPliEnCours();
        }
    }

    private static boolean koBeforeLoad(GameTarot _loadedGame, StringMap<String> _mes) {
        RulesTarot rules_ = _loadedGame.getRegles();
        if (!rules_.isValidRules()) {
            _loadedGame.setError(_mes.getVal(INVALID_RULES));
            return true;
        }
        if (_loadedGame.getDeclaresMiseres().size() != _loadedGame.getNombreDeJoueurs()) {
//        if (_loadedGame.getDeclaresMiseres().size() != _loadedGame.getNombreDeJoueurs() || _loadedGame.getConfidence().size() != _loadedGame.getNombreDeJoueurs()) {
            _loadedGame.setError(_mes.getVal(BAD_COUNT_FOR_DEAL));
            return true;
        }
//        for (CustList<BoolVal> b: _loadedGame.getConfidence()) {
//            if (b.size() != _loadedGame.getNombreDeJoueurs()) {
//                _loadedGame.setError(BAD_COUNT_FOR_DEAL);
//                return true;
//            }
//        }
        if (_loadedGame.getDeclaresHandfuls().size() != _loadedGame.getNombreDeJoueurs() || _loadedGame.getHandfuls().size() != _loadedGame.getNombreDeJoueurs()) {
            _loadedGame.setError(_mes.getVal(BAD_COUNT_FOR_DEAL));
            return true;
        }
        if (_loadedGame.getScores().size() != _loadedGame.getNombreDeJoueurs()) {
            _loadedGame.setError(_mes.getVal(BAD_COUNT_FOR_REMAINING_CARDS));
            return true;
        }
        if (_loadedGame.getDistribution().nombreDeMains() != rules_.getDealing().getId().getNombreJoueurs() + 1) {
            _loadedGame.setError(_mes.getVal(BAD_COUNT_FOR_DEAL));
            return true;
        }
        if (_loadedGame.getDistribution().derniereMain().total() != rules_.getDealing().getNombreCartesChien()) {
            _loadedGame.setError(_mes.getVal(BAD_COUNT_FOR_REMAINING_CARDS));
            return true;
        }
        return false;
    }

    private static boolean keepTrick(GameTarot _loadedGame, GameTarot _loadedGameCopy, int _ind, StringMap<String> _mes) {
//        if (koStarter(_loadedGame, _allTricks, _loadedGameCopy, _ind)) {
//            return false;
//        }
        CustList<TrickTarot> union_ = new CustList<TrickTarot>(_loadedGame.getTricks());
        union_.add(_loadedGame.getProgressingTrick());
//        if (!union_.isValidIndex(_ind)) {
//            return false;
//        }
        TrickTarot trick_ = union_.get(_ind);
        for (int p : _loadedGameCopy.orderedPlayers(_loadedGameCopy
                .getEntameur())) {
            if (!keepTrickIt(_loadedGame,_loadedGameCopy,trick_,p, _mes)) {
                return false;
            }
        }
        return true;
    }

//    private static boolean koStarter(GameTarot _loadedGame, CustList<TrickTarot> _allTricks, GameTarot _loadedGameCopy, int _ind) {
//        if (_ind == _allTricks.size()) {
//            if (_allTricks.get(_ind - 1).getVuParToutJoueur()) {
//                if (_allTricks.get(_ind - 1).getRamasseur() != _loadedGame
//                        .getPliEnCours().getEntameur()) {
//                    _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
//                    return true;
//                }
//            } else if (_loadedGameCopy.getEntameur() != _loadedGame
//                    .getPliEnCours().getEntameur()) {
//                _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
//                return true;
//            }
//        } else {
//            if (_allTricks.get(_ind - 1).getVuParToutJoueur()) {
//                if (_allTricks.get(_ind - 1).getRamasseur() != _allTricks
//                        .get(_ind).getEntameur()) {
//                    _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
//                    return true;
//                }
//            } else if (_loadedGameCopy.getEntameur() != _allTricks.get(_ind)
//                    .getEntameur()) {
//                _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
//                return true;
//            }
//        }
//        return false;
//    }

    private static boolean keepTrickIt(GameTarot _loadedGame, GameTarot _loadedGameCopy, TrickTarot _trick, int _p, StringMap<String> _mes) {
        if (!_trick.aJoue(_p, _loadedGameCopy.getNombreDeJoueurs())) {
            return false;
        }
        CardTarot ct_ = _trick.carteDuJoueur(_p,
                _loadedGameCopy.getNombreDeJoueurs());
        if (!_loadedGameCopy.autorise(ct_)) {
            _loadedGame.setError(_mes.getVal(BAD_PLAYING));
            return false;
        }
        if (!_loadedGameCopy.premierTour()) {
            _loadedGameCopy.ajouterUneCarteDansPliEnCours(ct_);
            return true;
        }
        if (!_loadedGameCopy.pasJeuMisere()) {
            if (!_loadedGame.getAnnoncesMiseres(_p).isEmpty() || !_loadedGame.getAnnoncesPoignees(_p).isEmpty() || !_loadedGame.getPoignee(_p).estVide()) {
                _loadedGame.setError(_mes.getVal(BAD_DECLARING));
                return false;
            }
            _loadedGameCopy.ajouterUneCarteDansPliEnCours(ct_);
            return true;
        }
        return koDecl(_loadedGame, _loadedGameCopy, _p, ct_, _mes);
    }

    private static boolean koDecl(GameTarot _loadedGame, GameTarot _loadedGameCopy, int _p, CardTarot _ct, StringMap<String> _mes) {
        for (Miseres m : _loadedGame.getAnnoncesMiseres(_p)) {
            if (!_loadedGame.getRegles().getMiseres()
                    .containsObj(m) || !_loadedGame.getAnnoncesMiseresPossibles(_p)
                    .containsObj(m)) {
                _loadedGame.setError(_mes.getVal(BAD_DECLARING));
                return false;
            }
        }
        _loadedGameCopy.setAnnoncesMiseres(
                _loadedGame.getAnnoncesMiseres(_p));
        if (_loadedGame.getAnnoncesPoignees(_p).size() > 1) {
            _loadedGame.setError(_mes.getVal(BAD_DECLARING));
            return false;
        }
        if (_loadedGame.getAnnoncesPoignees(_p).size() == 1) {
            Handfuls h_ = _loadedGame.getAnnoncesPoignees(_p)
                    .first();
            if (!_loadedGame.getRegles().poigneeAutorisee(h_) || !_loadedGameCopy.isValidHandful(h_,
                    _loadedGame.getPoignee(_p), excludedTrumps(_loadedGame, _p)
            )) {
                _loadedGame.setError(_mes.getVal(BAD_DECLARING));
                return false;
            }
            _loadedGameCopy.setAnnoncesPoignees(
                    _loadedGame.getAnnoncesPoignees(_p));
            _loadedGameCopy.ajouterPoignee(
                    _loadedGame.getPoignee(_p));
        } else {
            if (!_loadedGame.getPoignee(_p).estVide()) {
                _loadedGame.setError(_mes.getVal(BAD_DECLARING));
                return false;
            }
        }
        _loadedGameCopy.ajouterUneCarteDansPliEnCours(_ct);
        return true;
    }

    private static HandTarot excludedTrumps(GameTarot _loadedGame, int _p) {
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

    private static boolean koCards(GameTarot _loadedGame, HandTarot _cards, StringMap<String> _mes) {
        for (CardTarot c : _cards) {
            if (!c.getId().isJouable()) {
                _loadedGame.setError(_mes.getVal(BAD_CARD));
                return true;
            }
        }
        return false;
    }

    private static boolean koBidsCallDiscard(GameTarot _loadedGame, RulesTarot _rules, GameTarot _loadedGameCopy, StringMap<String> _mes) {
        if (_loadedGameCopy.avecContrat()) {
            if (koDeclBids(_loadedGame, _loadedGameCopy, _mes)) {
                return true;
            }
            trySetStarter(_loadedGameCopy);
            if (koCallBid(_loadedGame, _loadedGameCopy)) {
                _loadedGame.setError(_mes.getVal(ERROR_BID_MUST_BE_BEFORE_CALLING));
                return true;
            }
            if (koCallDiscard(_loadedGame)) {
                _loadedGame
                        .setError(_mes.getVal(ERROR_CALLING_MUST_BE_BEFORE_DISCARD));
                return true;
            }
        } else {
            if (_loadedGame.contrats() > 0) {
                _loadedGame.setError(_mes.getVal(NO_ALLOWED_BID));
                return true;
            }
        }
        if (noPlay(_loadedGame)) {
            if (existPlayedCard(_loadedGame)) {
                _loadedGame.setError(_mes.getVal(THERE_SHOULD_NOT_BE_ANY_TRICK));
                return true;
            }
            return true;
        }
        return koBeforePlayOrIncomplete(_loadedGame, _rules, _loadedGameCopy, _mes);
    }

    private static boolean existPlayedCard(GameTarot _loadedGame) {
        return _loadedGame.existPlayedCard();
//        return !_loadedGame.getTricks().isEmpty() || !_loadedGame.getPliEnCours().estVide();
    }

    private static boolean koCallBid(GameTarot _loadedGame, GameTarot _loadedGameCopy) {
        return !_loadedGame.getCarteAppelee().estVide() && _loadedGameCopy.keepBidding();
    }

    private static boolean noPlay(GameTarot _loadedGame) {
        return !_loadedGame.getContrat().isJouerDonne() && _loadedGame.pasJeuApresPasse();
    }

    private static void trySetStarter(GameTarot _loadedGameCopy) {
        if (!_loadedGameCopy.getContrat().isJouerDonne() && !_loadedGameCopy.pasJeuApresPasse()) {
            _loadedGameCopy.initPlayWithoutBid();
//            _loadedGameCopy.setEntameur(_loadedGameCopy
//                    .playerAfter(_loadedGame.getDistribution()
//                            .getDealer()));
        }
    }

    private static boolean koCallDiscard(GameTarot _loadedGame) {
        return _loadedGame.getRegles().getDiscardAfterCall() && _loadedGame.getRegles().getDealing().callCard()
                && _loadedGame.existePreneur() && existPlayedCard(_loadedGame)
                && _loadedGame.getCarteAppelee().estVide() || _loadedGame.isCallingState() && !_loadedGame.getTricks().isEmpty();
    }

//    private static boolean noTrick(CustList<TrickTarot> _allTricks) {
////        boolean noTrick_ = true;
////        for (TrickTarot t : _allTricks) {
////            if (!t.getVuParToutJoueur()) {
////                continue;
////            }
////            noTrick_ = false;
////        }
////        if (_allTricks.isEmpty()) {
////            return true;
////        }
////        if (_allTricks.size() > 1) {
////            return false;
////        }
//        return _allTricks.size() <= 1;
//    }

    private static void completeDeal(GameTarot _loadedGame, DealTarot _deal) {
        if (_loadedGame.getContrat().getJeuChien() == PlayingDog.WITH) {
            TrickTarot discardedCards_ = discardedCards(_loadedGame);
//            if (!_loadedGame.getTricks().isEmpty()) {
//                _deal.hand(_loadedGame.getPreneur()).ajouterCartes(
//                        _loadedGame.getTricks().first().getCartes());
//            } else {
//                _deal.hand(_loadedGame.getPreneur()).ajouterCartes(
//                        _loadedGame.getPliEnCours().getCartes());
//            }
            _deal.hand(_loadedGame.getPreneur()).ajouterCartes(discardedCards_.getCartes());
            _deal.hand(_loadedGame.getPreneur()).supprimerCartes(
                    _loadedGame.getDistribution().derniereMain());
        }
    }

    private static boolean koHandsDog(GameTarot _loadedGame, RulesTarot _rules, Ints _players, DealTarot _deal, StringMap<String> _mes) {
        boolean completed_ = !_loadedGame.getTricks().isEmpty();
        if (completed_) {
            return all(_loadedGame, _rules, _players, _deal, _mes);
        }
        return koHandsDogIncomplete(_loadedGame, _rules, _players, _deal, _mes);
    }

    private static boolean koHandsDogIncomplete(GameTarot _loadedGame, RulesTarot _rules, Ints _players, DealTarot _deal, StringMap<String> _mes) {
        if (_loadedGame.getContrat().getJeuChien() == PlayingDog.WITH && _loadedGame.getPreneur() == DealTarot.NUMERO_UTILISATEUR) {
            for (int p : _players) {
                if (p == _loadedGame.getPreneur()) {
                    continue;
                }
                if (_deal.hand(p).total() != _rules.getDealing()
                        .getNombreCartesParJoueur()) {
                    _loadedGame.setError(_mes.getVal(BAD_COUNT_FOR_HANDS));
                    return true;
                }
            }
            return false;
        }
        return all(_loadedGame, _rules, _players, _deal, _mes);
    }

    private static boolean all(GameTarot _loadedGame, RulesTarot _rules, Ints _players, DealTarot _deal, StringMap<String> _mes) {
        for (int p : _players) {
            if (_deal.hand(p).total() != _rules.getDealing()
                    .getNombreCartesParJoueur()) {
                _loadedGame.setError(_mes.getVal(BAD_COUNT_FOR_HANDS));
                return true;
            }
        }
        return false;
    }

    private static DealTarot buildDeal(GameTarot _loadedGame) {
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.players(_loadedGame);
//        DealTarot deal_ = new DealTarot(_loadedGame.getDistribution());
//        for (TrickTarot t : _allTricks) {
//            if (!t.getVuParToutJoueur()) {
//                continue;
//            }
//            for (CardTarot c : t) {
//                byte player_ = t.joueurAyantJoue(c);
//                deal_.hand(player_).ajouter(c);
//            }
//        }
//        if (_loadedGame.getPliEnCours().getVuParToutJoueur()) {
//            for (CardTarot c : _loadedGame.getPliEnCours()) {
//                byte player_ = _loadedGame.getPliEnCours()
//                        .joueurAyantJouePliEnCours(c,
//                                _loadedGame.getNombreDeJoueurs());
//                deal_.hand(player_).ajouter(c);
//            }
//        }
        DealTarot deal_ = new DealTarot(tricksHands_.getCardsHandsAtInitialState(),_loadedGame.getDeal().getDealer());
        deal_.setNbDeals(_loadedGame.getDeal().getNbDeals());
        return deal_;
    }

//    private static boolean koTricksCore(GameTarot _loadedGame, RulesTarot _rules, CustList<TrickTarot> _allTricks) {
////        if (koTricksCoreSeen(_loadedGame, _allTricks)) {
////            return true;
////        }
//        if (koTricksCoreDoneTricks(_loadedGame, _rules, _allTricks)) {
//            return true;
//        }
////        if (!_loadedGame.getPliEnCours().getVuParToutJoueur()) {
////            if (_loadedGame.getPliEnCours().total() > _rules.getDealing()
////                    .getNombreCartesChien()) {
////                _loadedGame.setError(TRICK_WITH_BAD_COUNT);
////                return true;
////            }
////        } else {
////            if (_loadedGame.getPliEnCours().total() > _loadedGame
////                    .getNombreDeJoueurs()) {
////                _loadedGame.setError(TRICK_WITH_BAD_COUNT);
////                return true;
////            }
////        }
//        return false;
//    }

    private static boolean koTricksCoreDoneTricks(GameTarot _loadedGame, RulesTarot _rules, StringMap<String> _mes) {
        int i = 0;
        CustList<TrickTarot> all_ = new CustList<TrickTarot>();
        all_.addAllElts(_loadedGame.getTricks());
        all_.add(_loadedGame.getPliEnCours());
        for (TrickTarot t : all_) {
            if (i == 0) {
                if (t.total() > _rules.getDealing().getNombreCartesChien()) {
                    _loadedGame.setError(_mes.getVal(TRICK_WITH_BAD_COUNT));
                    return true;
                }
            } else if (i + 1 < all_.size()){
                if (t.total() != _loadedGame
                        .getNombreDeJoueurs()) {
                    _loadedGame.setError(_mes.getVal(TRICK_WITH_BAD_COUNT));
                    return true;
                }
            } else {
                if (t.total() > _loadedGame.getNombreDeJoueurs()) {
                    _loadedGame.setError(_mes.getVal(TRICK_WITH_BAD_COUNT));
                    return true;
                }
            }
            i++;
        }
        return false;
    }

//    private static boolean koTricksCoreSeen(GameTarot _loadedGame, CustList<TrickTarot> _allTricks) {
//        int nbTricks_ = _allTricks.size();
//        for (int i = IndexConstants.FIRST_INDEX; i < nbTricks_; i++) {
//            if (i == IndexConstants.FIRST_INDEX) {
//                if (_allTricks.get(i).getVuParToutJoueur()) {
//                    _loadedGame.setError(BAD_TRICK);
//                    return true;
//                }
//            } else {
//                if (!_allTricks.get(i).getVuParToutJoueur()) {
//                    _loadedGame.setError(BAD_TRICK);
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    private static HandTarot retrieveCards(GameTarot _loadedGame) {
        HandTarot cards_ = new HandTarot();
        for (TrickTarot t : _loadedGame.getTricks()) {
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
        for (int p : _loadedGame.orderedPlayers(_loadedGame.getDistribution()
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

    private static boolean koDeclBids(GameTarot _loadedGame, GameTarot _loadedGameCopy, StringMap<String> _mes) {
        int i_ = 0;
        while (i_ < _loadedGame.contrats()) {
            BidTarot bid_ = BidTarot.toFirstBid(_loadedGame.contrat(i_));
            if (!bid_.estDemandable(
                    _loadedGameCopy.getContrat())) {
                _loadedGame.setError(_mes.getVal(INVALID_BID));
                return true;
            }
            if (!_loadedGameCopy.allowedBids().containsObj(
                    bid_)) {
                _loadedGame.setError(_mes.getVal(INVALID_BID));
                return true;
            }
            _loadedGameCopy.ajouterContrat(bid_);
            if (!_loadedGameCopy.keepBidding()) {
                if (_loadedGame.contrats() > i_ + 1) {
                    _loadedGame.setError(_mes.getVal(TOO_MUCH_BIDS));
                    return true;
                }
                break;
            }
            i_++;
        }
        return false;
    }

    private static boolean koBeforePlayOrIncomplete(GameTarot _loadedGame, RulesTarot _rules, GameTarot _loadedGameCopy, StringMap<String> _mes) {
        if (_loadedGame.avecContrat()) {
            if (_loadedGame.existePreneur() && !_loadedGame.existPlayedCard() && _loadedGame.isCallingState()) {
                return true;
            }
//            if (_loadedGame.existePreneur() && _loadedGame.getTricks().isEmpty()
//                    && _loadedGame.getPliEnCours().estVide() && _loadedGame.isCallingState()) {
//                return true;
//            }
            if (koBid(_loadedGame, _rules, _loadedGameCopy, _mes)) {
                return true;
            }
            //            if (_loadedGame.chelemAnnonce()) {
////            if (_loadedGame.getContrat().isJouerDonne() && _loadedGame.chelemAnnonce(_loadedGame.getPreneur())) {
////                _loadedGameCopy.annoncerUnChelem(_loadedGame.getPreneur());
//                _loadedGameCopy.setEntameur(_loadedGame.getPreneur());
//            } else {
//                _loadedGameCopy.setEntameur(_loadedGameCopy.playerAfter(_deal.getDealer()));
//            }
//        } else {
            //            _loadedGameCopy.setEntameur(_loadedGameCopy.playerAfter(_deal.getDealer()));
        }
        return koWhenNoTrick(_loadedGame);
    }

    private static boolean koWhenNoTrick(GameTarot _loadedGame) {
        return _loadedGame.getTricks().isEmpty();
//        if (_loadedGame.getTricks().size() <= 1) {
////        if (_noTrick)
////            if (!_loadedGame.getPliEnCours().getVuParToutJoueur()) {
////                return true;
////            }
//            return _loadedGame.getPliEnCours().estVide();
//        }
//        return false;
    }

    private static boolean koBid(GameTarot _loadedGame, RulesTarot _rules, GameTarot _loadedGameCopy, StringMap<String> _mes) {
        if (_loadedGame.getContrat().getJeuChien() == PlayingDog.WITH) {
            return koBidWith(_loadedGame, _rules, _loadedGameCopy, _mes);
        }
        if (_loadedGame.getContrat().isJouerDonne() && badCall(_loadedGame, _loadedGameCopy.callableCards())) {
            _loadedGame
                    .setError(_mes.getVal(A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE));
            return true;
        }
        if (_loadedGame.getContrat().isJouerDonne() && !_loadedGame.getTricks().isEmpty()) {
//            if (!_loadedGame.existPlayedCard()) {
//                return false;
//            }
            _loadedGameCopy.setCarteAppelee(_loadedGame
                    .getCarteAppelee());
            _loadedGameCopy.initConfianceAppele();
            _loadedGameCopy.gererChienInconnu();
            TrickTarot discardedCards_ = discardedCards(_loadedGame);
//            discardedCards_ = _loadedGame.getTricks().first();
            if (!HandTarot.equalsSet(_loadedGameCopy.getPliEnCours()
                    .getCartes(), discardedCards_.getCartes())) {
                _loadedGame
                        .setError(_mes.getVal(A_CARD_IS_MISSING_OR_EXCEDING_FOR_DISCARDING));
                return true;
            }
            _loadedGameCopy.ajouterChelem(_loadedGame.chelemAnnonce());
//            if (_loadedGame.getContrat().isFaireTousPlis() && _loadedGame.chelemAnnonce(_loadedGame.getPreneur())) {
//                _loadedGame.setError(NOT_BOTH_KIND_OF_DECLARING_SLAM);
//                return true;
//            }
        }
        return false;
    }

    private static boolean koBidWith(GameTarot _loadedGame, RulesTarot _rules, GameTarot _loadedGameCopy, StringMap<String> _mes) {
        TrickTarot discardedCards_ = discardedCards(_loadedGame);
        if (!_rules.getDiscardAfterCall()) {
            _loadedGameCopy.ajouterCartes(_loadedGameCopy.getPreneur(),
                    _loadedGameCopy.derniereMain());
            HandTarot callableCards_ = _loadedGameCopy.callableCards();
            if (badCall(_loadedGame, callableCards_)) {
                _loadedGame
                        .setError(_mes.getVal(A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE));
                return true;
            }
            if (koDiscarded(discardedCards_, _loadedGameCopy, _loadedGame, _mes)) {
                return true;
            }
//            _loadedGameCopy.addCurTrick();
            if (discardedCards_.total() != _loadedGame.derniereMain()
                    .total() && !_loadedGame.getCarteAppelee().estVide()) {
                _loadedGame
                        .setError(_mes.getVal(A_CARD_IS_MISSING_FOR_DISCARDING));
                return true;
            }
            _loadedGameCopy.setCarteAppelee(_loadedGame
                    .getCarteAppelee());
            _loadedGameCopy.initConfianceAppele();
        } else {
            HandTarot callableCards_ = _loadedGameCopy.callableCards();
            if (badCall(_loadedGame, callableCards_)) {
                _loadedGame
                        .setError(_mes.getVal(A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE));
                return true;
            }
            _loadedGameCopy.setCarteAppelee(_loadedGame
                    .getCarteAppelee());
            _loadedGameCopy.initConfianceAppele();
            _loadedGameCopy.ajouterCartes(_loadedGameCopy.getPreneur(),
                    _loadedGameCopy.derniereMain());
            if (koDiscarded(discardedCards_, _loadedGameCopy, _loadedGame, _mes)) {
                return true;
            }
//            _loadedGameCopy.addCurTrick();
        }
        _loadedGameCopy.addCurTrickDiscarded();
        _loadedGameCopy.ajouterChelem(_loadedGame.chelemAnnonce());
//        if (_loadedGame.chelemAnnonce()) {
//            _loadedGameCopy.setEntameur(_loadedGame.getPreneur());
//        }
        return false;
    }

    private static boolean badCall(GameTarot _loadedGame, HandTarot _callableCards) {
        HandTarot carteAppelee_ = _loadedGame
                .getCarteAppelee();
        if (carteAppelee_.total() > 1) {
            return true;
        }
        return !_callableCards.contientCartes(carteAppelee_);
    }

    private static boolean koDiscarded(TrickTarot _discardedCards, GameTarot _loadedGameCopy, GameTarot _loadedGame, StringMap<String> _mes) {
        for (CardTarot c : _discardedCards) {
            if (_loadedGameCopy.autoriseEcartDe(c
            ) != ReasonDiscard.NOTHING) {
                _loadedGame.setError(_mes.getVal(THIS_CARD_IS_NOT_DISCARDABLE));
                return true;
            }
            _loadedGameCopy.ajouterUneCarteDansPliEnCoursPreneur(
                    c);
        }
        return false;
    }

    private static TrickTarot discardedCards(GameTarot _loadedGame) {
        TrickTarot discardedCards_;
        if (_loadedGame.getTricks().isEmpty()) {
            discardedCards_ = _loadedGame.getPliEnCours();
        } else {
            discardedCards_ = _loadedGame.getTricks().first();
        }
        return discardedCards_;
    }
}
