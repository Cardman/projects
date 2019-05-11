package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.*;
import code.util.CustList;
import code.util.Numbers;
import code.util.consts.Constants;

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
        if (!rules_.isValidRules()) {
            _loadedGame.setError(INVALID_RULES);
            return;
        }
        _loadedGame.loadGame();
        if (_loadedGame.getDistribution().nombreDeMains() != rules_
                .getRepartition().getNombreJoueurs() + 1) {
            _loadedGame.setError(BAD_COUNT_FOR_DEAL);
            return;
        }
        if (_loadedGame.getDistribution().derniereMain().total() != rules_
                .getRepartition().getNombreCartesChien()) {
            _loadedGame.setError(BAD_COUNT_FOR_REMAINING_CARDS);
            return;
        }
        CustList<TrickTarot> allTricks_ = _loadedGame.unionPlis(true);
        HandTarot cards_ = new HandTarot();
        for (TrickTarot t : allTricks_) {
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
                .getDonneur())) {
            for (CardTarot c : _loadedGame.getPoignee(p)) {
                cards_.ajouter(c);
            }
        }
        for (CardTarot c : _loadedGame.getCarteAppelee()) {
            cards_.ajouter(c);
        }
        for (CardTarot c : cards_) {
            if (!c.isPlayable()) {
                _loadedGame.setError(BAD_CARD);
                return;
            }
        }
        int nbTricks_ = allTricks_.size();
        for (int i = CustList.FIRST_INDEX; i < nbTricks_; i++) {
            if (i == CustList.FIRST_INDEX) {
                if (allTricks_.get(i).getVuParToutJoueur()) {
                    _loadedGame.setError(BAD_TRICK);
                    return;
                }
            } else {
                if (!allTricks_.get(i).getVuParToutJoueur()) {
                    _loadedGame.setError(BAD_TRICK);
                    return;
                }
            }
        }
        for (TrickTarot t : allTricks_) {
            if (!t.getVuParToutJoueur()) {
                if (t.total() > rules_.getRepartition().getNombreCartesChien()) {
                    _loadedGame.setError(TRICK_WITH_BAD_COUNT);
                    return;
                }
            } else {
                if (t.total() != _loadedGame.getNombreDeJoueurs()) {
                    _loadedGame.setError(TRICK_WITH_BAD_COUNT);
                    return;
                }
            }
        }
        if (!_loadedGame.getPliEnCours().getVuParToutJoueur()) {
            if (_loadedGame.getPliEnCours().total() > rules_.getRepartition()
                    .getNombreCartesChien()) {
                _loadedGame.setError(TRICK_WITH_BAD_COUNT);
                return;
            }
        } else {
            if (_loadedGame.getPliEnCours().total() > _loadedGame
                    .getNombreDeJoueurs()) {
                _loadedGame.setError(TRICK_WITH_BAD_COUNT);
                return;
            }
        }
        boolean noTrick_ = true;
        for (TrickTarot t : allTricks_) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            noTrick_ = false;
        }
        Numbers<Byte> players_ = _loadedGame.orderedPlayers(_loadedGame
                .getDistribution().getDonneur());
        DealTarot deal_ = new DealTarot(_loadedGame.getDistribution());
        for (TrickTarot t : allTricks_) {
            if (!t.getVuParToutJoueur()) {
                continue;
            }
            for (CardTarot c : t) {
                byte player_ = t.joueurAyantJoue(c);
                deal_.main(player_).ajouter(c);
            }
        }
        if (_loadedGame.getPliEnCours().getVuParToutJoueur()) {
            for (CardTarot c : _loadedGame.getPliEnCours()) {
                byte player_ = _loadedGame.getPliEnCours()
                        .joueurAyantJouePliEnCours(c,
                                _loadedGame.getNombreDeJoueurs());
                deal_.main(player_).ajouter(c);
            }
        }
        boolean completed_ = false;
        if (!allTricks_.isEmpty()) {
            completed_ = true;
        }
        if (completed_) {
            for (byte p : players_) {
                if (deal_.main(p).total() != rules_.getRepartition()
                        .getNombreCartesParJoueur()) {
                    _loadedGame.setError(BAD_COUNT_FOR_HANDS);
                    return;
                }
            }
        } else {
            if (_loadedGame.getContrat().getJeuChien() == PlayingDog.WITH) {
                for (byte p : players_) {
                    if (p == _loadedGame.getPreneur()) {
                        continue;
                    }
                    if (deal_.main(p).total() != rules_.getRepartition()
                            .getNombreCartesParJoueur()) {
                        _loadedGame.setError(BAD_COUNT_FOR_HANDS);
                        return;
                    }
                }
            } else {
                for (byte p : players_) {
                    if (deal_.main(p).total() != rules_.getRepartition()
                            .getNombreCartesParJoueur()) {
                        _loadedGame.setError(BAD_COUNT_FOR_HANDS);
                        return;
                    }
                }
            }
        }
        if (_loadedGame.getContrat().getJeuChien() == PlayingDog.WITH) {
            if (!allTricks_.isEmpty()) {
                deal_.main(_loadedGame.getPreneur()).ajouterCartes(
                        allTricks_.first().getCartes());
            } else {
                deal_.main(_loadedGame.getPreneur()).ajouterCartes(
                        _loadedGame.getPliEnCours().getCartes());
            }
            deal_.main(_loadedGame.getPreneur()).supprimerCartes(
                    _loadedGame.getDistribution().derniereMain());
        }
        boolean allCardsUsedOnce_ = true;
        for (CardTarot c : HandTarot.pileBase()) {
            int nbUses_ = 0;
            for (HandTarot t : deal_) {
                for (CardTarot c2_ : t) {
                    if (c == c2_) {
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
        GameTarot loadedGameCopy_ = new GameTarot(_loadedGame.getType(), deal_,
                rules_);
        if (loadedGameCopy_.avecContrat()) {
            players_ = loadedGameCopy_
                    .orderedPlayers(loadedGameCopy_.playerAfter(loadedGameCopy_
                            .getDistribution().getDonneur()));
            int i_ = 0;
            while (i_ < _loadedGame.contrats()) {
                byte player_ = players_.get(i_);
                if (!_loadedGame.contrat(i_).estDemandable(
                        loadedGameCopy_.getContrat())) {
                    _loadedGame.setError(INVALID_BID);
                    return;
                }
                if (!loadedGameCopy_.allowedBids().containsObj(
                        _loadedGame.contrat(i_))) {
                    _loadedGame.setError(INVALID_BID);
                    return;
                }
                loadedGameCopy_.ajouterContrat(_loadedGame.contrat(i_), player_);
                if (!loadedGameCopy_.keepBidding()) {
                    if (_loadedGame.contrats() > i_ + 1) {
                        _loadedGame.setError(TOO_MUCH_BIDS);
                        return;
                    }
                    break;
                }
                i_++;
            }
            if (!loadedGameCopy_.getContrat().isJouerDonne()) {
                if (!loadedGameCopy_.pasJeuApresPasse()) {
                    loadedGameCopy_.initPlayWithoutBid();
                    loadedGameCopy_.setEntameur(loadedGameCopy_
                            .playerAfter(_loadedGame.getDistribution()
                                    .getDonneur()));
                }
            }
            if (!_loadedGame.getCarteAppelee().estVide()) {
                if (loadedGameCopy_.keepBidding()) {
                    _loadedGame.setError(ERROR_BID_MUST_BE_BEFORE_CALLING);
                    return;
                }
            }
            if (_loadedGame.getRegles().getDiscardAfterCall()) {
                if (_loadedGame.getRegles().getRepartition().callCard()
                        && _loadedGame.existePreneur()) {
                    if ((!allTricks_.isEmpty() || !_loadedGame.getPliEnCours()
                            .estVide())
                            && _loadedGame.getCarteAppelee().estVide()) {
                        _loadedGame
                                .setError(ERROR_CALLING_MUST_BE_BEFORE_DISCARD);
                        return;
                    }
                }
            }
        } else {
            if (_loadedGame.contrats() > 0) {
                _loadedGame.setError(NO_ALLOWED_BID);
                return;
            }
            loadedGameCopy_.setEntameur(loadedGameCopy_.playerAfter(_loadedGame
                    .getDistribution().getDonneur()));
        }
        if (!_loadedGame.getContrat().isJouerDonne()) {
            if (_loadedGame.pasJeuApresPasse()) {
                if (!allTricks_.isEmpty()) {
                    _loadedGame.setError(THERE_SHOULD_NOT_BE_ANY_TRICK);
                    return;
                }
                if (!_loadedGame.getPliEnCours().estVide()) {
                    _loadedGame.setError(THERE_SHOULD_NOT_BE_ANY_TRICK);
                    return;
                }
                return;
            }
        }
        if (_loadedGame.avecContrat()) {
            if (_loadedGame.existePreneur() && allTricks_.isEmpty()
                    && _loadedGame.getPliEnCours().estVide()) {
                if (_loadedGame.isCallingState()) {
                    return;
                }
            }
            if (_loadedGame.getContrat().getJeuChien() == PlayingDog.WITH) {
                TrickTarot discardedCards_;
                if (allTricks_.isEmpty()) {
                    discardedCards_ = _loadedGame.getPliEnCours();
                } else {
                    discardedCards_ = allTricks_.first();
                }
                if (!rules_.getDiscardAfterCall()) {
                    loadedGameCopy_.ajouterCartes(loadedGameCopy_.getPreneur(),
                            loadedGameCopy_.derniereMain());
                    HandTarot callableCards_ = loadedGameCopy_.callableCards();
                    if (!callableCards_.contientCartes(_loadedGame
                            .getCarteAppelee())) {
                        _loadedGame
                                .setError(A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE);
                        return;
                    }
                    for (CardTarot c : discardedCards_) {
                        if (loadedGameCopy_.autoriseEcartDe(c
                        ) != ReasonDiscard.NOTHING) {
                            _loadedGame.setError(THIS_CARD_IS_NOT_DISCARDABLE);
                            return;
                        }
                        loadedGameCopy_.ajouterUneCarteDansPliEnCours(
                                loadedGameCopy_.getPreneur(), c);
                    }
                    loadedGameCopy_.addCurTrick();
                    if (discardedCards_.total() != _loadedGame.derniereMain()
                            .total()) {
                        if (!_loadedGame.getCarteAppelee().estVide()) {
                            _loadedGame
                                    .setError(A_CARD_IS_MISSING_FOR_DISCARDING);
                            return;
                        }
                    }
                    loadedGameCopy_.setCarteAppelee(_loadedGame
                            .getCarteAppelee());
                    loadedGameCopy_.initConfianceAppele();
                } else {
                    HandTarot callableCards_ = loadedGameCopy_.callableCards();
                    if (!callableCards_.contientCartes(_loadedGame
                            .getCarteAppelee())) {
                        _loadedGame
                                .setError(A_CARD_MUST_BE_CALLED_AMONG_ALL_POSSIBLE);
                        return;
                    }
                    loadedGameCopy_.setCarteAppelee(_loadedGame
                            .getCarteAppelee());
                    loadedGameCopy_.initConfianceAppele();
                    loadedGameCopy_.ajouterCartes(loadedGameCopy_.getPreneur(),
                            loadedGameCopy_.derniereMain());
                    for (CardTarot c : discardedCards_) {
                        if (loadedGameCopy_.autoriseEcartDe(c
                        ) != ReasonDiscard.NOTHING) {
                            _loadedGame.setError(THIS_CARD_IS_NOT_DISCARDABLE);
                            return;
                        }
                        loadedGameCopy_.ajouterUneCarteDansPliEnCours(
                                loadedGameCopy_.getPreneur(), c);
                    }
                    loadedGameCopy_.addCurTrick();
                }
                if (_loadedGame.chelemAnnonce(_loadedGame.getPreneur())) {
                    loadedGameCopy_.setEntameur(_loadedGame.getPreneur());
                }
            } else if (_loadedGame.getContrat().isJouerDonne()) {
                loadedGameCopy_.gererChienInconnu();
                TrickTarot discardedCards_;
                discardedCards_ = allTricks_.first();
                if (!HandTarot.equalsSet(loadedGameCopy_.getPliEnCours()
                        .getCartes(), discardedCards_.getCartes())) {
                    _loadedGame
                            .setError(A_CARD_IS_MISSING_OR_EXCEDING_FOR_DISCARDING);
                    return;
                }
                if (_loadedGame.getContrat().isFaireTousPlis()) {
                    if (_loadedGame.chelemAnnonce(_loadedGame.getPreneur())) {
                        _loadedGame.setError(NOT_BOTH_KIND_OF_DECLARING_SLAM);
                        return;
                    }
                }
            }
            if (noTrick_) {
                if (!_loadedGame.getPliEnCours().getVuParToutJoueur()) {
                    return;
                }
                if (_loadedGame.getPliEnCours().estVide()) {
                    return;
                }
            }
            if (_loadedGame.getContrat().isJouerDonne()) {
                if (_loadedGame.chelemAnnonce(_loadedGame.getPreneur())) {
                    loadedGameCopy_.annoncerUnChelem(_loadedGame.getPreneur());
                    loadedGameCopy_.setEntameur(_loadedGame.getPreneur());
                }
            }
        } else {
            if (noTrick_) {
                if (!_loadedGame.getPliEnCours().getVuParToutJoueur()) {
                    return;
                }
                if (_loadedGame.getPliEnCours().estVide()) {
                    return;
                }
            }
        }
        int ind_ = 1;
        boolean passe_ = false;
        loadedGameCopy_.setPliEnCours(true);
        while (true) {
            if (passe_) {
                loadedGameCopy_.ajouterPliEnCours();
                loadedGameCopy_.setPliEnCours(true);
            }
            TrickTarot trick_;
            if (ind_ == allTricks_.size()) {
                if (allTricks_.get(ind_ - 1).getVuParToutJoueur()) {
                    if (allTricks_.get(ind_ - 1).getRamasseur() != _loadedGame
                            .getPliEnCours().getEntameur()) {
                        _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                        return;
                    }
                } else {
                    if (loadedGameCopy_.getEntameur() != _loadedGame
                            .getPliEnCours().getEntameur()) {
                        _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                        return;
                    }
                }
                trick_ = _loadedGame.getPliEnCours();
            } else {
                if (allTricks_.get(ind_ - 1).getVuParToutJoueur()) {
                    if (allTricks_.get(ind_ - 1).getRamasseur() != allTricks_
                            .get(ind_).getEntameur()) {
                        _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                        return;
                    }
                } else {
                    if (loadedGameCopy_.getEntameur() != allTricks_.get(ind_)
                            .getEntameur()) {
                        _loadedGame.setError(BAD_MATCHING_WITH_TRICK_LEADER);
                        return;
                    }
                }
                trick_ = allTricks_.get(ind_);
            }
            for (byte p : loadedGameCopy_.orderedPlayers(loadedGameCopy_
                    .getEntameur())) {
                if (!trick_.aJoue(p, loadedGameCopy_.getNombreDeJoueurs())) {
                    return;
                }
                CardTarot ct_ = trick_.carteDuJoueur(p,
                        loadedGameCopy_.getNombreDeJoueurs());
                if (!loadedGameCopy_.autorise(ct_)) {
                    _loadedGame.setError(BAD_PLAYING);
                    return;
                }
                if (!passe_) {
                    passe_ = true;
                }
                if (loadedGameCopy_.premierTour()) {
                    if (loadedGameCopy_.pasJeuMisere()) {
                        for (Miseres m : _loadedGame.getAnnoncesMiseres(p)) {
                            if (!_loadedGame.getRegles().getMiseres()
                                    .containsObj(m)) {
                                _loadedGame.setError(BAD_DECLARING);
                                return;
                            }
                            if (!_loadedGame.getAnnoncesMiseresPossibles(p)
                                    .containsObj(m)) {
                                _loadedGame.setError(BAD_DECLARING);
                                return;
                            }
                        }
                        loadedGameCopy_.setAnnoncesMiseres(p,
                                _loadedGame.getAnnoncesMiseres(p));
                        if (_loadedGame.getAnnoncesPoignees(p).size() > 1) {
                            _loadedGame.setError(BAD_DECLARING);
                            return;
                        }
                        if (_loadedGame.getAnnoncesPoignees(p).size() == 1) {
                            Handfuls h_ = _loadedGame.getAnnoncesPoignees(p)
                                    .first();
                            if (!_loadedGame.getRegles().poigneeAutorisee(h_)) {
                                _loadedGame.setError(BAD_DECLARING);
                                return;
                            }
                            HandTarot excludedTrumps_ = new HandTarot();
                            for (CardTarot c : _loadedGame.getDistribution()
                                    .main(p).couleur(Suit.TRUMP)) {
                                if (_loadedGame.getPoignee(p).contient(c)) {
                                    continue;
                                }
                                excludedTrumps_.ajouter(c);
                            }
                            if (!_loadedGame.getPoignee(p).contient(
                                    CardTarot.EXCUSE)) {
                                excludedTrumps_.ajouter(CardTarot.EXCUSE);
                            }
                            if (!loadedGameCopy_.isValidHandful(h_,
                                    _loadedGame.getPoignee(p), excludedTrumps_
                            )) {
                                _loadedGame.setError(BAD_DECLARING);
                                return;
                            }
                            loadedGameCopy_.setAnnoncesPoignees(p,
                                    _loadedGame.getAnnoncesPoignees(p));
                            loadedGameCopy_.ajouterPoignee(
                                    _loadedGame.getPoignee(p), p);
                        } else {
                            if (!_loadedGame.getPoignee(p).estVide()) {
                                _loadedGame.setError(BAD_DECLARING);
                                return;
                            }
                        }
                    } else {
                        if (!_loadedGame.getAnnoncesMiseres(p).isEmpty()) {
                            _loadedGame.setError(BAD_DECLARING);
                            return;
                        }
                        if (!_loadedGame.getAnnoncesPoignees(p).isEmpty()) {
                            _loadedGame.setError(BAD_DECLARING);
                            return;
                        }
                        if (!_loadedGame.getPoignee(p).estVide()) {
                            _loadedGame.setError(BAD_DECLARING);
                            return;
                        }
                    }
                }
                loadedGameCopy_.jouer(p, ct_);
                loadedGameCopy_.ajouterUneCarteDansPliEnCours(ct_);
            }
            ind_++;
            loadedGameCopy_.ajouterPetitAuBoutFinPartie();
            if (!loadedGameCopy_.keepPlayingCurrentGame()) {
                break;
            }
        }
    }
}
