package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.util.CustList;
import code.util.*;
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
        int nombreJoueurs_ = rules_
                .getRepartition().getNombreJoueurs();
        if (_loadedGame.getDistribution().nombreDeMains() != nombreJoueurs_ + 1) {
            _loadedGame.setError(BAD_COUNT_FOR_DEAL);
            return;
        }
        if (_loadedGame.getDistribution().derniereMain().total() != rules_
                .getRepartition().getRemainingCards()) {
            _loadedGame.setError(BAD_COUNT_FOR_REMAINING_CARDS);
            return;
        }
        if (_loadedGame.getWonLastTrick().size() != nombreJoueurs_) {
            _loadedGame.setError(BAD_COUNT_FOR_REMAINING_CARDS);
            return;
        }
        if (_loadedGame.getDeclares().size() != nombreJoueurs_) {
            _loadedGame.setError(BAD_COUNT_FOR_REMAINING_CARDS);
            return;
        }
        if (_loadedGame.getDeclaresBeloteRebelote().size() != nombreJoueurs_) {
            _loadedGame.setError(BAD_COUNT_FOR_REMAINING_CARDS);
            return;
        }
        if (_loadedGame.getScores().size() != nombreJoueurs_) {
            _loadedGame.setError(BAD_COUNT_FOR_REMAINING_CARDS);
            return;
        }
        if (!_loadedGame.getRules().isValidRules()) {
            _loadedGame.setError(BAD_COUNT_FOR_REMAINING_CARDS);
            return;
        }
        _loadedGame.loadGame();
        CustList<TrickBelote> allTricks_ = _loadedGame.getTricks();
        HandBelote cards_ = new HandBelote();
        for (TrickBelote t : allTricks_) {
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
        for (CardBelote e : cards_) {
            if (!e.isPlayable()) {
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
        DealBelote deal_ = new DealBelote(_loadedGame.getDistribution());
        for (TrickBelote t : allTricks_) {
            for (CardBelote c : t) {
                byte player_ = t.joueurAyantJoue(c);
                deal_.hand(player_).ajouter(c);
            }
        }
        for (CardBelote c : _loadedGame.getPliEnCours()) {
            byte player_ = _loadedGame.getPliEnCours().joueurAyantJoue(c,
                    nbPl_);
            deal_.hand(player_).ajouter(c);
        }
        boolean completed_ = false;
        if (!allTricks_.isEmpty()) {
            completed_ = true;
        } else if (!_loadedGame.getPliEnCours().estVide()) {
            completed_ = true;
        } else if (rules_.dealAll()) {
            completed_ = true;
        }
        if (completed_) {
            for (byte p : players_) {
                if (deal_.hand(p).total() != rules_.getRepartition()
                        .getNombreCartesParJoueur()) {
                    _loadedGame.setError(BAD_COUNT_FOR_HANDS);
                    return;
                }
            }
            if (!rules_.dealAll()) {
                reinitializeGame(deal_, _loadedGame);
            }
        } else {
            boolean allCompleted_ = true;
            for (byte p : players_) {
                if (deal_.hand(p).total() != rules_.getRepartition()
                        .getNombreCartesParJoueur()) {
                    allCompleted_ = false;
                    break;
                }
            }
            if (!allCompleted_) {
                for (byte p : players_) {
                    if (deal_.hand(p).total() != rules_.getRepartition()
                            .getFirstCards()) {
                        _loadedGame.setError(BAD_COUNT_FOR_HANDS);
                        return;
                    }
                }
            } else {
                reinitializeGame(deal_, _loadedGame);
            }
        }
        boolean allCardsUsedOnce_ = true;
        for (CardBelote c : HandBelote.pileBase()) {
            int nbUses_ = 0;
            for (HandBelote t : deal_) {
                for (CardBelote cTwo_ : t) {
                    if (c == cTwo_) {
                        nbUses_++;
                    }
                }
            }
            if (nbUses_ == 1) {
                continue;
            }
            allCardsUsedOnce_ = false;
            break;
        }
        if (!allCardsUsedOnce_) {
            _loadedGame
                    .setError(ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE);
            return;
        }
        GameBelote loadedGameCopy_ = new GameBelote(_loadedGame.getType(),
                deal_, rules_);
        if (!_loadedGame.getContrat().jouerDonne()) {
            if (!allTricks_.isEmpty()) {
                _loadedGame.setError(THERE_SHOULD_NOT_BE_ANY_TRICK);
                return;
            }
            if (!_loadedGame.getPliEnCours().estVide()) {
                _loadedGame.setError(THERE_SHOULD_NOT_BE_ANY_TRICK);
                return;
            }
            // return;
        }
        if (!rules_.dealAll()) {
            players_ = loadedGameCopy_
                    .orderedPlayers(loadedGameCopy_.playerAfter(loadedGameCopy_
                            .getDistribution().getDealer()));
            boolean finished_ = false;
            int i_ = 0;
            for (byte p : players_) {
                if (i_ == _loadedGame.tailleContrats()) {
                    finished_ = true;
                    break;
                }
                if (!_loadedGame.contrat(i_).estDemandable(
                        loadedGameCopy_.getContrat())) {
                    _loadedGame.setError(INVALID_BID);
                    return;
                }
                boolean found_ = false;
                for (BidBeloteSuit bid_ : loadedGameCopy_.getGameBeloteBid().allowedBids()) {
                    if (bid_.getEnchere() != _loadedGame.contrat(i_)
                            .getEnchere()) {
                        continue;
                    }
                    if (bid_.getCouleur() != _loadedGame.contrat(i_)
                            .getCouleur()) {
                        continue;
                    }
                    found_ = true;
                    break;
                }
                if (!found_) {
                    _loadedGame.setError(INVALID_BID);
                    return;
                }
                loadedGameCopy_.ajouterContrat(_loadedGame.contrat(i_), p);
                if (!loadedGameCopy_.keepBidding()) {
                    finished_ = true;
                    if (_loadedGame.tailleContrats() > i_ + 1) {
                        _loadedGame.setError(TOO_MUCH_BIDS);
                        return;
                    }
                    break;
                }
                i_++;
            }
            if (!finished_) {
                loadedGameCopy_.finEncherePremierTour();
                int pl_ = 0;
                while (true) {
                    byte p_ = players_.get(pl_);
                    if (!_loadedGame.contrat(i_).estDemandable(
                            loadedGameCopy_.getContrat())) {
                        _loadedGame.setError(INVALID_BID);
                        return;
                    }
                    boolean found_ = false;
                    for (BidBeloteSuit bid_ : loadedGameCopy_.getGameBeloteBid().allowedBids()) {
                        if (bid_.getEnchere() != _loadedGame.contrat(i_)
                                .getEnchere()) {
                            continue;
                        }
                        if (bid_.getCouleur() != _loadedGame.contrat(i_)
                                .getCouleur()) {
                            continue;
                        }
                        found_ = true;
                        break;
                    }
                    if (!found_) {
                        _loadedGame.setError(INVALID_BID);
                        return;
                    }
                    loadedGameCopy_.ajouterContrat(_loadedGame.contrat(i_),
                            p_);
                    if (!loadedGameCopy_.keepBidding()) {
                        if (_loadedGame.tailleContrats() > i_ + 1) {
                            _loadedGame.setError(TOO_MUCH_BIDS);
                            return;
                        }
                        break;
                    }
                    i_++;
                    pl_++;
                }
            }
        } else {
            int i_ = 0;
            int nbFold_ = 0;
            int pts_ = 0;
            while (i_ < _loadedGame.tailleContrats()) {
                BidBeloteSuit bid_;
                bid_ = _loadedGame.contrat(i_);
                if (bid_.jouerDonne()) {
                    if (!_loadedGame.getRegles().getEncheresAutorisees()
                            .getVal(bid_.getEnchere())) {
                        _loadedGame.setError(BIDDING_LOWER);
                        return;
                    }
                    if (!RulesBelote.getPoints().containsObj(bid_.getPoints())) {
                        _loadedGame.setError(BIDDING_LOWER);
                        return;
                    }
                    nbFold_ = 0;
                    if (pts_ >= bid_.getPoints()) {
                        _loadedGame.setError(BIDDING_LOWER);
                        return;
                    }
                    pts_ = bid_.getPoints();
                } else {
                    nbFold_++;
                    if (nbFold_ >= loadedGameCopy_.getNombreDeJoueurs()) {
                        _loadedGame.setError(BIDDING_TOO_MUCH_LOW);
                        return;
                    }
                    if (nbFold_ >= loadedGameCopy_.getNombreDeJoueurs() - 1) {
                        if (i_ + 1 < _loadedGame.tailleContrats()
                                && _loadedGame.contrat(i_ + 1).jouerDonne()) {
                            _loadedGame.setError(BIDDING_TOO_MUCH_LOW);
                            return;
                        }
                    }
                }
                loadedGameCopy_.ajouterContrat(bid_,
                        (byte) ((i_
                                + loadedGameCopy_.getDistribution()
                                        .getDealer() + 1) % loadedGameCopy_
                                .getNombreDeJoueurs()));
                i_++;
            }
        }
        if (allTricks_.isEmpty() && _loadedGame.getPliEnCours().estVide()) {
            return;
        }
        loadedGameCopy_.completerDonne();
        int firstPlayerTrick_ = _loadedGame.playerAfter(_loadedGame
                .getDistribution().getDealer());
        if (loadedGameCopy_.getRegles().dealAll()) {
            int pts_ = loadedGameCopy_.getContrat().getPoints();
            if (pts_ >= HandBelote.pointsTotauxDixDeDer(loadedGameCopy_
                    .getContrat())) {
                loadedGameCopy_.setEntameur(loadedGameCopy_.getPreneur());
                firstPlayerTrick_ = _loadedGame.getPreneur();
            }
        }
        loadedGameCopy_.setPliEnCours();
        HandBelote playedCards_ = _loadedGame.getDoneTrickInfo().cartesJouees();
        playedCards_.ajouterCartes(_loadedGame.getPliEnCours().getCartes());
        for (byte b = IndexConstants.FIRST_INDEX; b < nbPl_; b++) {
            for (CardBelote c : _loadedGame.getAnnoncesBeloteRebelote(b)) {
                if (!_loadedGame.cartesBeloteRebelote().contient(c)) {
                    _loadedGame.setError(BAD_DECLARING);
                    return;
                }
                if (!playedCards_.contient(c)) {
                    _loadedGame.setError(BAD_DECLARING);
                    return;
                }
                if (!loadedGameCopy_.getDistribution().hand(b).contient(c)) {
                    _loadedGame.setError(BAD_DECLARING);
                    return;
                }
            }
        }
        int ind_ = 0;
        boolean passe_ = false;
        TrickBelote firstTrick_;
        if (!allTricks_.isEmpty()) {
            firstTrick_ = allTricks_.first();
        } else {
            firstTrick_ = _loadedGame.getPliEnCours();
        }
        while (true) {
            if (passe_) {
                loadedGameCopy_.ajouterPliEnCours();
                loadedGameCopy_.setEntameur();
                loadedGameCopy_.setPliEnCours();
            }
            TrickBelote trick_;
            if (ind_ == 0) {
                if (firstTrick_.getEntameur() != firstPlayerTrick_) {
                    _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                    return;
                }
                trick_ = firstTrick_;
            } else if (ind_ == allTricks_.size()) {
                if (allTricks_.last().getRamasseur(_loadedGame.getContrat()) != _loadedGame
                        .getPliEnCours().getEntameur()) {
                    _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                    return;
                }
                trick_ = _loadedGame.getPliEnCours();
            } else {
                if (allTricks_.get(ind_ - 1).getRamasseur(
                        _loadedGame.getContrat()) != allTricks_.get(ind_)
                        .getEntameur()) {
                    _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                    return;
                }
                trick_ = allTricks_.get(ind_);
            }
            for (byte p : loadedGameCopy_.orderedPlayers(loadedGameCopy_
                    .getEntameur())) {
                if (!trick_.aJoue(p, nbPl_)) {
                    return;
                }
                CardBelote ct_ = trick_.carteDuJoueur(p,
                        nbPl_);
                if (!loadedGameCopy_.autorise(ct_)) {
                    _loadedGame.setError(BAD_PLAYING);
                    return;
                }
                if (_loadedGame.getAnnoncesBeloteRebelote(p).contient(ct_)) {
                    loadedGameCopy_.setAnnoncesBeloteRebelote(p, ct_);
                }
                if (loadedGameCopy_.premierTour()) {
                    if (_loadedGame.getAnnonce(p).getDeclare() != DeclaresBelote.UNDEFINED) {
                        DeclareHandBelote declaring_ = loadedGameCopy_
                                .strategieAnnonces();
                        if (!_loadedGame.getAnnonce(p).eq(declaring_)) {
                            _loadedGame.setError(BAD_DECLARING);
                            return;
                        }
                    }
                    if (_loadedGame.getAnnonce(p).getDeclare() != DeclaresBelote.UNDEFINED) {
                        loadedGameCopy_.annoncer(p);
                    }
                }
                if (!passe_) {
                    passe_ = true;
                }
                loadedGameCopy_.getDistribution().jouer(p, ct_);
                loadedGameCopy_.ajouterUneCarteDansPliEnCours(ct_);
            }
            if (!loadedGameCopy_.keepPlayingCurrentGame()) {
                /* Il y a dix_ de_ der_ */
                loadedGameCopy_.ajouterPliEnCours();
                loadedGameCopy_.setDixDeDer(loadedGameCopy_.getRamasseur());
                break;
            }
            ind_++;
        }

    }

    private static void reinitializeGame(DealBelote _restitutedDeal,
                                         GameBelote _loadedGame) {
        RulesBelote rules_ = _loadedGame.getRegles();
        HandBelote talon_ = new HandBelote();
        talon_.ajouterCartes(_loadedGame.getDistribution().derniereMain());
        HandBelote h_ = new HandBelote();
        h_.ajouter(talon_.jouer(IndexConstants.FIRST_INDEX));
        _restitutedDeal.hand(_loadedGame.getPreneur()).supprimerCartes(h_);
        for (int i : rules_.getRepartition().getDistributionFin()) {
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
