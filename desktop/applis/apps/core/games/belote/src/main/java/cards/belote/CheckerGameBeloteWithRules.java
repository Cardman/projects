package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.util.CustList;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class CheckerGameBeloteWithRules {

    private static final String BAD_PLAYING = "Bad playing";
    private static final String BAD_MATCHING_WITH_TRICK_LEADER = "Bad matching with trick leader";
    private static final String BAD_DECLARING = "Bad declaring";
    private static final String BIDDING_TOO_MUCH_LOW = "Bidding too much low";
    private static final String BIDDING_LOWER = "Bidding lower";
    private static final String TOO_MUCH_BIDS = "too much bids";
    private static final String INVALID_BID = "invalid bid";
    private static final String THERE_SHOULD_NOT_BE_ANY_TRICK = "There should not be any trick";
    private static final String ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE = "all cards at remaining cards are not used once";
    private static final String BAD_COUNT_FOR_HANDS = "Bad count for hands";
    private static final String TRICK_WITH_BAD_COUNT = "trick with bad count";
    private static final String BAD_COUNT_FOR_REMAINING_CARDS = "Bad count for remaining cards";
    private static final String BAD_COUNT_FOR_DEAL = "Bad count for deal";
    private static final String BAD_CARD = "bad card";

    private CheckerGameBeloteWithRules() {
    }

    public static void check(GameBelote _loadedGame) {
        RulesBelote rules_ = _loadedGame.getRegles();
        int nombreJoueurs_ = rules_.getDealing().getId().getNombreJoueurs();
        if (_loadedGame.getDistribution().nombreDeMains() != nombreJoueurs_ + 1) {
            _loadedGame.setError(BAD_COUNT_FOR_DEAL);
            return;
        }
        if (_loadedGame.getDistribution().derniereMain().total() != rules_.getDealing().getRemainingCards() || _loadedGame.getWonLastTrick().size() != nombreJoueurs_ || _loadedGame.getDeclares().size() != nombreJoueurs_ || _loadedGame.getDeclaresBeloteRebelote().size() != nombreJoueurs_ || _loadedGame.getScores().size() != nombreJoueurs_ || !_loadedGame.getRules().isValidRules()) {
            _loadedGame.setError(BAD_COUNT_FOR_REMAINING_CARDS);
            return;
        }
        _loadedGame.loadGame();
        CustList<TrickBelote> allTricks_ = _loadedGame.getTricks();
        HandBelote cards_ = retrieveCards(_loadedGame, allTricks_);
        for (CardBelote e : cards_) {
            if (!e.getId().isJouable()) {
                _loadedGame.setError(BAD_CARD);
                return;
            }
        }
        byte nbPl_ = _loadedGame
                .getNombreDeJoueurs();
        for (TrickBelote t : allTricks_) {
            if (t.total() != nbPl_) {
                _loadedGame.setError(TRICK_WITH_BAD_COUNT);
                return;
            }
        }
        if (_loadedGame.getPliEnCours().total() > nbPl_) {
            _loadedGame.setError(TRICK_WITH_BAD_COUNT);
            return;
        }
        Bytes players_ = _loadedGame.orderedPlayers(_loadedGame
                .getDistribution().getDealer());
        DealBelote deal_ = buildDeal(_loadedGame, allTricks_, nbPl_);
        if (reinitializeKo(_loadedGame, rules_, players_, deal_)) {
            return;
        }
        boolean allCardsUsedOnce_ = allCardsUsedOnce(reinitialize(_loadedGame, rules_, players_, deal_));
        if (!allCardsUsedOnce_) {
            _loadedGame
                    .setError(ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE);
            return;
        }
        GameBelote loadedGameCopy_ = new GameBelote(_loadedGame.getType(),
                deal_, rules_);
        if (koBid(_loadedGame, rules_, loadedGameCopy_) || _loadedGame.noPlayed()) {
            return;
        }
//        loadedGameCopy_.completerDonne();
//        byte nombreDeJoueurs_ = loadedGameCopy_.getNombreDeJoueurs();
//        loadedGameCopy_.setEntameur((byte)((loadedGameCopy_.getDeal().getDealer()+1)%nombreDeJoueurs_));
//        int firstPlayerTrick_ = firstPlayerTrick(_loadedGame, loadedGameCopy_);
        int firstPlayerTrick_ = loadedGameCopy_.completerDonne();
        loadedGameCopy_.setPliEnCours();
        HandBelote playedCards_ = _loadedGame.getDoneTrickInfo().cartesJouees();
        if (koBeloteRebelote(_loadedGame, loadedGameCopy_, playedCards_)) {
            return;
        }
        checkPlayed(_loadedGame, allTricks_, loadedGameCopy_, firstPlayerTrick_);

    }

    private static void checkPlayed(GameBelote _loadedGame, CustList<TrickBelote> _allTricks, GameBelote _loadedGameCopy, int _firstPlayerTrick) {
        int ind_ = 0;
        while (true) {
            if (!keepTrick(_loadedGame, _allTricks, _loadedGameCopy, _firstPlayerTrick,ind_)){
                return;
            }
            ind_++;
        }
    }

    private static boolean koBeloteRebelote(GameBelote _loadedGame, GameBelote _loadedGameCopy, HandBelote _playedCards) {
        byte nbPl_ = _loadedGame
                .getNombreDeJoueurs();
        for (byte b = IndexConstants.FIRST_INDEX; b < nbPl_; b++) {
            for (CardBelote c : _loadedGame.getAnnoncesBeloteRebelote(b)) {
                if (!_loadedGame.cartesBeloteRebelote().contient(c) || !_playedCards.contient(c) || !_loadedGameCopy.getDistribution().hand(b).contient(c)) {
                    _loadedGame.setError(BAD_DECLARING);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean koBid(GameBelote _loadedGame, RulesBelote _rules, GameBelote _loadedGameCopy) {
        if (!_loadedGame.getBid().jouerDonne() && !_loadedGame.noPlayed()) {
            _loadedGame.setError(THERE_SHOULD_NOT_BE_ANY_TRICK);
            return true;
        }
        if (!_rules.dealAll()) {
            return koBidNotDealAll(_loadedGame, _loadedGameCopy);
        }
        return koBidDealAll(_loadedGame, _loadedGameCopy);
    }

    private static boolean reinitializeKo(GameBelote _loadedGame, RulesBelote _rules, Bytes _players, DealBelote _deal) {
        if (!_loadedGame.noPlayed() || _rules.dealAll()) {
            for (byte p : _players) {
                if (_deal.hand(p).total() != _rules.getDealing()
                        .getNombreCartesParJoueur()) {
                    _loadedGame.setError(BAD_COUNT_FOR_HANDS);
                    return true;
                }
            }
            return false;
        }
        if (allCompleted(_rules, _players, _deal)) {
            return false;
        }
        for (byte p : _players) {
            if (_deal.hand(p).total() != _rules.getDealing()
                    .getFirstCards()) {
                _loadedGame.setError(BAD_COUNT_FOR_HANDS);
                return true;
            }
        }
        return false;
    }
    private static CustList<HandBelote> reinitialize(GameBelote _loadedGame, RulesBelote _rules, Bytes _players, DealBelote _deal) {
        if (!_loadedGame.noPlayed()) {
            if (!_rules.dealAll()) {
                reinitializeGame(_deal, _loadedGame);
            }
            return _deal.getDeal();
        }
        if (_rules.dealAll()) {
            return _deal.getDeal();
        }
        if (allCompleted(_rules, _players, _deal)) {
            reinitializeGame(_deal, _loadedGame);
        }
        return _deal.getDeal();
    }

    private static DealBelote buildDeal(GameBelote _loadedGame, CustList<TrickBelote> _allTricks, byte _nbPl) {
        DealBelote deal_ = new DealBelote(_loadedGame.getDistribution());
        for (TrickBelote t : _allTricks) {
            for (CardBelote c : t) {
                byte player_ = t.joueurAyantJoue(c);
                deal_.hand(player_).ajouter(c);
            }
        }
        for (CardBelote c : _loadedGame.getPliEnCours()) {
            byte player_ = _loadedGame.getPliEnCours().joueurAyantJoue(c,
                    _nbPl);
            deal_.hand(player_).ajouter(c);
        }
        return deal_;
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
        for (byte p : _loadedGame.orderedPlayers(_loadedGame.getDistribution()
                .getDealer())) {
            for (CardBelote c : _loadedGame.getAnnoncesBeloteRebelote(p)) {
                cards_.ajouter(c);
            }
            for (CardBelote c : _loadedGame.getAnnonce(p).getHand()) {
                cards_.ajouter(c);
            }
        }
        return cards_;
    }

    private static boolean keepTrick(GameBelote _loadedGame, CustList<TrickBelote> _allTricks, GameBelote _loadedGameCopy, int _firstPlayerTrick, int _ind) {
        TrickBelote firstTrick_;
        if (!_allTricks.isEmpty()) {
            firstTrick_ = _allTricks.first();
        } else {
            firstTrick_ = _loadedGame.getPliEnCours();
        }
        TrickBelote trick_;
        if (_ind == 0) {
            if (firstTrick_.getEntameur() != _firstPlayerTrick) {
                _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                return false;
            }
            trick_ = firstTrick_;
        } else if (_ind == _allTricks.size()) {
            if (_allTricks.last().getRamasseur(_loadedGame.getBid()) != _loadedGame
                    .getPliEnCours().getEntameur()) {
                _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                return false;
            }
            trick_ = _loadedGame.getPliEnCours();
        } else {
            if (_allTricks.get(_ind - 1).getRamasseur(
                    _loadedGame.getBid()) != _allTricks.get(_ind)
                    .getEntameur()) {
                _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                return false;
            }
            trick_ = _allTricks.get(_ind);
        }
        for (byte p : _loadedGameCopy.orderedPlayers(_loadedGameCopy
                .getEntameur())) {
            if (!keepTrickIt(_loadedGame,_loadedGameCopy,trick_,p)) {
                return false;
            }
        }
        if (!_loadedGameCopy.keepPlayingCurrentGame()) {
            /* Il y a dix_ de_ der_ */
            _loadedGameCopy.ajouterPliEnCours();
            _loadedGameCopy.setDixDeDer(_loadedGameCopy.getRamasseur());
            return false;
        }
        _loadedGameCopy.ajouterPliEnCours();
        return true;
    }
    private static boolean keepTrickIt(GameBelote _loadedGame, GameBelote _loadedGameCopy, TrickBelote _trick, byte _p) {
        byte nbPl_ = _loadedGame
                .getNombreDeJoueurs();
        if (!_trick.aJoue(_p, nbPl_)) {
            return false;
        }
        CardBelote ct_ = _trick.carteDuJoueur(_p,
                nbPl_);
        if (!_loadedGameCopy.autorise(ct_)) {
            _loadedGame.setError(BAD_PLAYING);
            return false;
        }
        if (_loadedGame.getAnnoncesBeloteRebelote(_p).contient(ct_)) {
            _loadedGameCopy.setAnnoncesBeloteRebelote(_p, ct_);
        }
        if (_loadedGameCopy.premierTour()) {
            if (_loadedGame.getAnnonce(_p).getDeclare() != DeclaresBelote.UNDEFINED) {
                DeclareHandBelote declaring_ = _loadedGameCopy
                        .strategieAnnonces();
                if (!_loadedGame.getAnnonce(_p).eq(declaring_)) {
                    _loadedGame.setError(BAD_DECLARING);
                    return false;
                }
            }
            if (_loadedGame.getAnnonce(_p).getDeclare() != DeclaresBelote.UNDEFINED) {
                _loadedGameCopy.annoncer(_p);
            }
        }
        _loadedGameCopy.getDistribution().jouer(_p, ct_);
        _loadedGameCopy.ajouterUneCarteDansPliEnCours(ct_);
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

    private static boolean koBidNotDealAll(GameBelote _loadedGame, GameBelote _loadedGameCopy) {
        Bytes players_;
        players_ = _loadedGameCopy
                .orderedPlayers(_loadedGameCopy.playerAfter(_loadedGameCopy
                        .getDistribution().getDealer()));
        int i_ = round(_loadedGame, _loadedGameCopy, players_, 0);
        if (!_loadedGame.getError().isEmpty()) {
            return true;
        }
        if (i_ < players_.size()) {
            return false;
        }
//        _loadedGameCopy.finEncherePremierTour();
        round(_loadedGame, _loadedGameCopy, players_, i_);
        return !_loadedGame.getError().isEmpty();
    }

    private static int round(GameBelote _loadedGame, GameBelote _loadedGameCopy, Bytes _players, int _i) {
        int j_ = _i;
        int pl_ = _players.size();
        for (int i = 0; i < pl_; i++) {
            boolean keep_ = keepRound(_loadedGame, _loadedGameCopy, j_);
            if (!keep_) {
                break;
            }
            j_++;
        }
        return j_;
    }

    private static boolean koBidDealAll(GameBelote _loadedGame, GameBelote _loadedGameCopy) {
        int i_ = 0;
        int nbFold_ = 0;
        int pts_ = 0;
        while (i_ < _loadedGame.tailleContrats()) {
            BidBeloteSuit bid_ = _loadedGame.contrat(i_);
            if (bid_.jouerDonne()) {
                if (_loadedGame.getRegles().getAllowedBids()
                        .getVal(bid_.getBid()) != BoolVal.TRUE || !RulesBelote.getPoints().containsObj(bid_.getPoints()) || pts_ >= bid_.getPoints()) {
                    _loadedGame.setError(BIDDING_LOWER);
                    return true;
                }
                nbFold_ = 0;
                pts_ = bid_.getPoints();
            } else {
                nbFold_++;
                if (nbFold_ >= _loadedGameCopy.getNombreDeJoueurs() || nbFold_ >= _loadedGameCopy.getNombreDeJoueurs() - 1 && i_ + 1 < _loadedGame.tailleContrats()
                        && _loadedGame.contrat(i_ + 1).jouerDonne()) {
                    _loadedGame.setError(BIDDING_TOO_MUCH_LOW);
                    return true;
                }
            }
            _loadedGameCopy.ajouterContrat(bid_
            );
            i_++;
        }
        return false;
    }

    private static boolean keepRound(GameBelote _loadedGame, GameBelote _loadedGameCopy, int _i) {
        if (_i == _loadedGame.tailleContrats()) {
            return false;
        }
        if (!_loadedGame.contrat(_i).estDemandable(
                _loadedGameCopy.getBid())) {
            _loadedGame.setError(INVALID_BID);
            return false;
        }
        boolean found_ = okBidSuit(_loadedGame, _loadedGameCopy, _i);
        if (!found_) {
            _loadedGame.setError(INVALID_BID);
            return false;
        }
        _loadedGameCopy.ajouterContrat(_loadedGame.contrat(_i));
        if (!_loadedGameCopy.keepBidding()) {
            if (_loadedGame.tailleContrats() > _i + 1) {
                _loadedGame.setError(TOO_MUCH_BIDS);
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
        HandBelote talon_ = new HandBelote();
        talon_.ajouterCartes(_loadedGame.getDistribution().derniereMain());
        HandBelote h_ = new HandBelote();
        h_.ajouter(talon_.jouer(IndexConstants.FIRST_INDEX));
        _restitutedDeal.hand(_loadedGame.getPreneur()).supprimerCartes(h_);
        for (int i : rules_.getDealing().getDistributionFin()) {
            for (byte j : _loadedGame.orderedPlayers(_loadedGame
                    .playerAfter(_loadedGame.getDistribution().getDealer()))) {
                for (int k = IndexConstants.SECOND_INDEX; k < i; k++) {
                    h_ = new HandBelote();
                    h_.ajouter(talon_.jouer(IndexConstants.FIRST_INDEX));
                    _restitutedDeal.hand(j).supprimerCartes(h_);
                }
                if (j != _loadedGame.getPreneur()) {
                    h_ = new HandBelote();
                    h_.ajouter(talon_.jouer(IndexConstants.FIRST_INDEX));
                    _restitutedDeal.hand(j).supprimerCartes(h_);
                }
            }
        }
    }
}
