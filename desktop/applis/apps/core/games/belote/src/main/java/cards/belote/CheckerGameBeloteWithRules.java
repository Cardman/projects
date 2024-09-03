package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.util.CustList;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public final class CheckerGameBeloteWithRules {
    public static final String BAD_PLAYING="0";
    public static final String BAD_DECLARING="1";
    public static final String BIDDING_TOO_MUCH_LOW="2";
    public static final String BIDDING_LOWER="3";
    public static final String TOO_MUCH_BIDS="4";
    public static final String INVALID_BID="5";
    public static final String THERE_SHOULD_NOT_BE_ANY_TRICK="6";
    public static final String ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE="7";
    public static final String BAD_COUNT_FOR_HANDS="8";
    public static final String BAD_REP_FOR_HANDS="9";
    public static final String TRICK_WITH_BAD_COUNT="10";
    public static final String BAD_COUNT_FOR_REMAINING_CARDS="11";
    public static final String BAD_COUNT_FOR_DEAL="12";
    public static final String BAD_CARD="13";

    private CheckerGameBeloteWithRules() {
    }

    public static void check(GameBelote _loadedGame, StringMap<String> _mes) {
        RulesBelote rules_ = _loadedGame.getRegles();
        int nombreJoueurs_ = rules_.getDealing().getId().getNombreJoueurs();
        if (_loadedGame.getDistribution().nombreDeMains() != nombreJoueurs_ + 1) {
            _loadedGame.setError(_mes.getVal(BAD_COUNT_FOR_DEAL));
            return;
        }
        if (_loadedGame.getDistribution().derniereMain().total() != rules_.getDealing().remainingCards() || _loadedGame.getWonLastTrick().size() != nombreJoueurs_ || _loadedGame.getDeclares().size() != nombreJoueurs_ || _loadedGame.getDeclaresBeloteRebelote().size() != nombreJoueurs_ || _loadedGame.getScores().size() != nombreJoueurs_ || !_loadedGame.getRules().isValidRules()) {
            _loadedGame.setError(_mes.getVal(BAD_COUNT_FOR_REMAINING_CARDS));
            return;
        }
        _loadedGame.loadGame();
        CustList<TrickBelote> allTricks_ = _loadedGame.getTricks();
        HandBelote cards_ = retrieveCards(_loadedGame, allTricks_);
        for (CardBelote e : cards_) {
            if (!e.getId().isJouable()) {
                _loadedGame.setError(_mes.getVal(BAD_CARD));
                return;
            }
        }
        if (koTricks(_loadedGame, rules_, _mes)) {
            return;
        }
        Bytes players_ = _loadedGame.getDeal().orderedPlayersBegin(_loadedGame
                .getRegles());
        DealBelote deal_ = buildDeal(_loadedGame);
        if (reinitializeKo(_loadedGame, rules_, players_, deal_, _mes)) {
            return;
        }
        boolean allCardsUsedOnce_ = allCardsUsedOnce(reinitialize(_loadedGame, rules_, deal_));
        if (!allCardsUsedOnce_) {
            _loadedGame
                    .setError(_mes.getVal(ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE));
            return;
        }
        GameBelote loadedGameCopy_ = new GameBelote(_loadedGame.getType(),
                deal_, rules_);
        if (koBid(_loadedGame, rules_, loadedGameCopy_, _mes) || _loadedGame.noPlayed()) {
            return;
        }
        completerDonne(_loadedGame, loadedGameCopy_);
//        loadedGameCopy_.completerDonne();
//        byte nombreDeJoueurs_ = loadedGameCopy_.getNombreDeJoueurs();
//        loadedGameCopy_.setEntameur((byte)((loadedGameCopy_.getDeal().getDealer()+1)%nombreDeJoueurs_));
//        int firstPlayerTrick_ = firstPlayerTrick(_loadedGame, loadedGameCopy_);
//        loadedGameCopy_.completerDonne();
        HandBelote playedCards_ = _loadedGame.getDoneTrickInfo().cartesJouees();
        if (koBeloteRebelote(_loadedGame, loadedGameCopy_, playedCards_, _mes)) {
            return;
        }
        checkPlayed(_loadedGame, loadedGameCopy_, _mes);

    }

    private static void completerDonne(GameBelote _loadedGame, GameBelote _loadedGameCopy) {
        RulesBelote rules_ = _loadedGame.getRegles();
        if (rules_.getDealing().getDiscarded() > 0) {
            TrickBelote discardedCards_ = discardedCards(_loadedGame);
            _loadedGameCopy.ajouterCartesUtilisateur();
            for (CardBelote c : discardedCards_) {
                _loadedGameCopy.ajouterUneCarteDansPliEnCoursPreneur(c);
            }
            _loadedGameCopy.ajouterChelem(_loadedGame.changeFirstLeader());
            _loadedGameCopy.validateDiscard();
        } else {
            _loadedGameCopy.completerDonne();
        }
    }

    private static boolean koTricks(GameBelote _loadedGame, RulesBelote _rules, StringMap<String> _mes) {
        int off_ = RulesBelote.offset(_rules);
        CustList<TrickBelote> all_ = new CustList<TrickBelote>();
        all_.addAllElts(_loadedGame.getTricks());
        all_.add(_loadedGame.getPliEnCours());
        for (TrickBelote t : all_.left(off_)) {
            if (t.total() > _rules.getDealing().getDiscarded()) {
                _loadedGame.setError(_mes.getVal(TRICK_WITH_BAD_COUNT));
                return true;
            }
        }
        CustList<TrickBelote> allTricks_ = _loadedGame.getTricks();
        byte nbPl_ = _loadedGame
                .getNombreDeJoueurs();
        for (TrickBelote t : allTricks_.mid(off_)) {
            if (t.total() != nbPl_) {
                _loadedGame.setError(_mes.getVal(TRICK_WITH_BAD_COUNT));
                return true;
            }
        }
        if (!_loadedGame.addCurrentTrick()) {
            return false;
        }
        if (_loadedGame.getPliEnCours().total() > nbPl_) {
            _loadedGame.setError(_mes.getVal(TRICK_WITH_BAD_COUNT));
            return true;
        }
        return false;
    }

    private static void checkPlayed(GameBelote _loadedGame, GameBelote _loadedGameCopy, StringMap<String> _mes) {
        int ind_ = RulesBelote.offset(_loadedGame.getRegles());
        while (true) {
            if (!keepTrick(_loadedGame, _loadedGameCopy, ind_, _mes)){
                return;
            }
            ind_++;
        }
    }

    private static boolean koBeloteRebelote(GameBelote _loadedGame, GameBelote _loadedGameCopy, HandBelote _playedCards, StringMap<String> _mes) {
        byte nbPl_ = _loadedGame
                .getNombreDeJoueurs();
        for (byte b = IndexConstants.FIRST_INDEX; b < nbPl_; b++) {
            for (CardBelote c : _loadedGame.getAnnoncesBeloteRebelote(b)) {
                if (!_loadedGame.cartesBeloteRebelote().contient(c) || !_playedCards.contient(c) || !_loadedGameCopy.getDistribution().hand(b).contient(c)) {
                    _loadedGame.setError(_mes.getVal(BAD_DECLARING));
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean koBid(GameBelote _loadedGame, RulesBelote _rules, GameBelote _loadedGameCopy, StringMap<String> _mes) {
        if (!_loadedGame.getBid().jouerDonne() && !_loadedGame.noPlayedClassic()) {
            _loadedGame.setError(_mes.getVal(THERE_SHOULD_NOT_BE_ANY_TRICK));
            return true;
        }
        boolean ko_;
        if (!_rules.withBidPointsForAllPlayers()) {
            ko_ = koBidNotDealAll(_loadedGame, _loadedGameCopy, _mes);
        } else {
            ko_ = koBidDealAll(_loadedGame, _loadedGameCopy, _mes);
        }
        return ko_;
    }

    private static boolean reinitializeKo(GameBelote _loadedGame, RulesBelote _rules, Bytes _players, DealBelote _deal, StringMap<String> _mes) {
        if (koDealAfter(_loadedGame, _rules, _deal, _mes)) {
            return true;
        }
        if (!_loadedGame.noPlayedClassic() || _rules.getDealing().getDiscarded() > 0) {
            for (byte p : _players) {
                if (_rules.getDealing().getDiscarded() > 0 && p == DealBelote.NUMERO_UTILISATEUR && p == _loadedGame.getPreneur()) {
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
        if (allCompleted(_rules, _players, _deal)) {
            return false;
        }
        return koDealBefore(_loadedGame, _rules, _players, _deal, _mes);
    }

    private static boolean koDealBefore(GameBelote _loadedGame, RulesBelote _rules, Bytes _players, DealBelote _deal, StringMap<String> _mes) {
        for (byte p : _players) {
            if (_deal.hand(p).total() != _rules.getDealing()
                    .getFirstCards()) {
                _loadedGame.setError(_mes.getVal(BAD_COUNT_FOR_HANDS));
                return true;
            }
        }
        return false;
    }

    private static boolean koDealAfter(GameBelote _loadedGame, RulesBelote _rules, DealBelote _deal, StringMap<String> _mes) {
        if (_rules.splitHand()) {
            HandBelote der_ = _deal.derniereMain();
            HandBelote low_ = HandBelote.low(HandBelote.pileBase());
            HandBelote disc_ = new HandBelote();
            disc_.setCards(new IdList<CardBelote>(der_.getCards().mid(der_.total()-8)));
            if (!HandBelote.equalsSet(low_,disc_)) {
                _loadedGame.setError(_mes.getVal(BAD_REP_FOR_HANDS));
                return true;
            }
        }
        CustList<HandBelote> handBelotes_ = _deal.mainsSupp(_loadedGame.getPreneur(), _rules);
        int toCmp_ = NumberUtil.min(handBelotes_.size(), _deal.nombreDeMains());
        for (byte b = 0; b < toCmp_; b++) {
            HandBelote hand_ = _deal.hand(b);
            if (hand_.total() == _rules.getDealing()
                    .getNombreCartesParJoueur() && !hand_.contientCartes(handBelotes_.get(b))) {
                _loadedGame.setError(_mes.getVal(BAD_REP_FOR_HANDS));
                return true;
            }
        }
        return false;
    }

    private static CustList<HandBelote> reinitialize(GameBelote _loadedGame, RulesBelote _rules, DealBelote _deal) {
        if (_rules.getDealing().getDiscarded() > 0 && _loadedGame.getPreneur() > -1) {
            TrickBelote discardedCards_ = discardedCards(_loadedGame);
            TricksHandsBelote.endRestore(_deal.getDeal(),_loadedGame.getPreneur(),discardedCards_,_rules);
//            _deal.hand(_loadedGame.getPreneur()).ajouterCartes(discardedCards_.getCartes());
//            _deal.hand(_loadedGame.getPreneur()).supprimerCartes(
//                    _loadedGame.getDistribution().derniereMain());
        }
        reinitializeGame(_deal, _loadedGame);
        return _deal.getDeal();
    }

    private static DealBelote buildDeal(GameBelote _loadedGame) {
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.players(_loadedGame);
        DealBelote d_ = new DealBelote(tricksHands_.getCardsHandsAtInitialState(), _loadedGame.getDeal().getDealer());
        d_.setNbDeals(_loadedGame.getDeal().getNbDeals());
        return d_;
    }

    private static HandBelote retrieveCards(GameBelote _loadedGame, CustList<TrickBelote> _allTricks) {
        HandBelote cards_ = new HandBelote();
        for (TrickBelote t : _allTricks) {
            for (CardBelote c : t) {
                cards_.ajouter(c);
            }
        }
        for (CardBelote c : _loadedGame.getPliEnCours()) {
            cards_.ajouter(c);
        }
        for (HandBelote c : _loadedGame.getDistribution()) {
            cards_.ajouterCartes(c);
        }
        for (byte p : _loadedGame.getDeal().orderedPlayersBegin(_loadedGame.getRegles())) {
            for (CardBelote c : _loadedGame.getAnnoncesBeloteRebelote(p)) {
                cards_.ajouter(c);
            }
            for (CardBelote c : _loadedGame.getAnnonce(p).getHand()) {
                cards_.ajouter(c);
            }
        }
        return cards_;
    }

    private static boolean keepTrick(GameBelote _loadedGame, GameBelote _loadedGameCopy, int _ind, StringMap<String> _mes) {
//        TrickBelote firstTrick_;
//        if (!_allTricks.isEmpty()) {
//            firstTrick_ = _allTricks.first();
//        } else {
//            firstTrick_ = _loadedGame.getPliEnCours();
//        }
        TrickBelote trick_ = trick(_loadedGame,_ind);
//        if (_ind == 0) {
////            if (firstTrick_.getEntameur() != _firstPlayerTrick) {
////                _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
////                return false;
////            }
//            trick_ = firstTrick_;
//        } else if (_ind == _allTricks.size()) {
////            if (_allTricks.last().getRamasseur(_loadedGame.getBid()) != _loadedGame
////                    .getPliEnCours().getEntameur()) {
////                _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
////                return false;
////            }
//            trick_ = _loadedGame.getPliEnCours();
//        } else {
////            if (_allTricks.get(_ind - 1).getRamasseur(
////                    _loadedGame.getBid()) != _allTricks.get(_ind)
////                    .getEntameur()) {
////                _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
////                return false;
////            }
//            trick_ = _allTricks.get(_ind);
//        }
        for (byte p : _loadedGameCopy.orderedPlayers(_loadedGameCopy
                .getEntameur())) {
            if (!keepTrickIt(_loadedGame,_loadedGameCopy,trick_,p, _mes)) {
                return false;
            }
        }
        if (!_loadedGameCopy.keepPlayingCurrentGame()) {
            /* Il y a dix_ de_ der_ */
            _loadedGameCopy.ajouterDixDeDerPliEnCours();
            return false;
        }
        _loadedGameCopy.ajouterDixDeDerPliEnCours();
        return true;
    }
    private static TrickBelote trick(GameBelote _loadedGame, int _ind) {
        CustList<TrickBelote> union_ = new CustList<TrickBelote>(_loadedGame.getTricks());
        union_.add(_loadedGame.getProgressingTrick());
        return union_.get(_ind);
    }
    private static boolean keepTrickIt(GameBelote _loadedGame, GameBelote _loadedGameCopy, TrickBelote _trick, byte _p, StringMap<String> _mes) {
        byte nbPl_ = _loadedGame
                .getNombreDeJoueurs();
        if (!_trick.aJoue(_p, nbPl_)) {
            return false;
        }
        CardBelote ct_ = _trick.carteDuJoueur(_p,
                nbPl_);
        if (!_loadedGameCopy.autorise(ct_)) {
            _loadedGame.setError(_mes.getVal(BAD_PLAYING));
            return false;
        }
        if (_loadedGame.getAnnoncesBeloteRebelote(_p).contient(ct_)) {
            _loadedGameCopy.setAnnoncesBeloteRebelote(ct_);
        }
        if (_loadedGameCopy.premierTour()) {
            if (_loadedGame.getAnnonce(_p).getDeclare() != DeclaresBelote.UNDEFINED) {
                DeclareHandBelote declaring_ = _loadedGameCopy
                        .strategieAnnonces();
                if (!_loadedGame.getAnnonce(_p).eq(declaring_)) {
                    _loadedGame.setError(_mes.getVal(BAD_DECLARING));
                    return false;
                }
            }
            if (_loadedGame.getAnnonce(_p).getDeclare() != DeclaresBelote.UNDEFINED) {
                _loadedGameCopy.annoncer();
            }
        }
        _loadedGameCopy.ajouterUneCarteDansPliEnCoursJoue(ct_);
        return true;
    }

//    private static int firstPlayerTrick(GameBelote _loadedGame, GameBelote _loadedGameCopy) {
//        int firstPlayerTrick_ = _loadedGame.playerAfter(_loadedGame
//                .getDistribution().getDealer());
//        if (_loadedGameCopy.getRegles().dealAll()) {
//            int pts_ = _loadedGameCopy.getBid().getPoints();
//            if (pts_ >= HandBelote.pointsTotauxDixDeDer(_loadedGameCopy.getBid())) {
//                _loadedGameCopy.setEntameur(_loadedGameCopy.getPreneur());
//                firstPlayerTrick_ = _loadedGame.getPreneur();
//            }
//        }
//        return firstPlayerTrick_;
//    }

    private static boolean koBidNotDealAll(GameBelote _loadedGame, GameBelote _loadedGameCopy, StringMap<String> _mes) {
        Bytes players_;
        players_ = _loadedGameCopy
                .getDeal().orderedPlayersBegin(_loadedGameCopy.getRegles());
        int i_ = roundNotDealAll(_loadedGame, _loadedGameCopy, players_, 0, _mes);
        if (!_loadedGame.getError().isEmpty()) {
            return true;
        }
        if (i_ < players_.size()) {
            return false;
        }
//        _loadedGameCopy.finEncherePremierTour();
        roundNotDealAll(_loadedGame, _loadedGameCopy, players_, i_, _mes);
        return !_loadedGame.getError().isEmpty();
    }

    private static int roundNotDealAll(GameBelote _loadedGame, GameBelote _loadedGameCopy, Bytes _players, int _i, StringMap<String> _mes) {
        int j_ = _i;
        int pl_ = _players.size();
        for (int i = 0; i < pl_; i++) {
            boolean keep_ = keepRoundNotDealAll(_loadedGame, _loadedGameCopy, j_, _mes);
            if (!keep_) {
                break;
            }
            j_++;
        }
        return j_;
    }

    private static boolean koBidDealAll(GameBelote _loadedGame, GameBelote _loadedGameCopy, StringMap<String> _mes) {
        int i_ = 0;
        int nbFold_ = 0;
        int pts_ = 0;
        while (i_ < _loadedGame.tailleContrats()) {
            BidBeloteSuit bid_ = _loadedGame.contrat(i_);
            if (bid_.jouerDonne()) {
                if (_loadedGame.getRegles().getAllowedBids()
                        .getVal(bid_.getBid()) != BoolVal.TRUE || !RulesBelote.getPoints().containsObj(bid_.getPoints()) || pts_ >= bid_.getPoints()) {
                    _loadedGame.setError(_mes.getVal(BIDDING_LOWER));
                    return true;
                }
                nbFold_ = 0;
                pts_ = bid_.getPoints();
            } else {
                nbFold_++;
                if (nbFold_ >= _loadedGameCopy.getNombreDeJoueurs() || nbFold_ >= _loadedGameCopy.getNombreDeJoueurs() - 1 && i_ + 1 < _loadedGame.tailleContrats()
                        && _loadedGame.contrat(i_ + 1).jouerDonne()) {
                    _loadedGame.setError(_mes.getVal(BIDDING_TOO_MUCH_LOW));
                    return true;
                }
            }
            _loadedGameCopy.ajouterContrat(bid_
            );
            i_++;
        }
        return false;
    }

    private static boolean keepRoundNotDealAll(GameBelote _loadedGame, GameBelote _loadedGameCopy, int _i, StringMap<String> _mes) {
        if (_i == _loadedGame.tailleContrats()) {
            return false;
        }
        if (!_loadedGame.contrat(_i).estDemandable(
                _loadedGameCopy.getBid())) {
            _loadedGame.setError(_mes.getVal(INVALID_BID));
            return false;
        }
        boolean found_ = okBidSuit(_loadedGame, _loadedGameCopy, _i);
        if (!found_) {
            _loadedGame.setError(_mes.getVal(INVALID_BID));
            return false;
        }
        _loadedGameCopy.ajouterContrat(_loadedGame.contrat(_i));
        if (!_loadedGameCopy.keepBidding()) {
            if (_loadedGame.tailleContrats() > _i + 1) {
                _loadedGame.setError(_mes.getVal(TOO_MUCH_BIDS));
            }
            return false;
        }
        return true;
    }

    private static boolean okBidSuit(GameBelote _loadedGame, GameBelote _loadedGameCopy, int _i) {
        boolean found_ = false;
        for (BidBeloteSuit bid_ : _loadedGameCopy.getGameBeloteBid().allowedBids()) {
            if (bid_.getBid() == _loadedGame.contrat(_i).getBid() && bid_.getSuit() == _loadedGame.contrat(_i).getSuit()) {
                found_ = true;
                break;
            }
        }
        return found_;
    }

    private static boolean allCardsUsedOnce(CustList<HandBelote> _deal) {
        boolean allCardsUsedOnce_ = true;
        for (CardBelote c : HandBelote.pileBase()) {
            int nbUses_ = 0;
            for (HandBelote t : _deal) {
                for (CardBelote cTwo_ : t) {
                    if (c == cTwo_) {
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

    private static boolean allCompleted(RulesBelote _rules, Bytes _players, DealBelote _deal) {
        boolean allCompleted_ = true;
        for (byte p : _players) {
            if (_deal.hand(p).total() != _rules.getDealing()
                    .getNombreCartesParJoueur()) {
                allCompleted_ = false;
                break;
            }
        }
        return allCompleted_;
    }

    private static void reinitializeGame(DealBelote _restitutedDeal,
                                         GameBelote _loadedGame) {
        RulesBelote rules_ = _loadedGame.getRegles();
        CustList<HandBelote> handBelotes_ = _restitutedDeal.mainsSupp(_loadedGame.getPreneur(),rules_);
        int toFeed_ = NumberUtil.min(handBelotes_.size(),_restitutedDeal.getDeal().size());
        for (byte i = 0; i < toFeed_; i++) {
            _restitutedDeal.hand(i).supprimerCartes(handBelotes_.get(i));
        }
    }
    private static TrickBelote discardedCards(GameBelote _loadedGame) {
        return _loadedGame.discardedCards();
    }
}
