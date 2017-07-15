package cards.belote;
import code.util.CustList;
import code.util.Numbers;
import code.util.consts.Constants;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.exceptions.BeloteRulesException;

public final class CheckerGameBeloteWithRules {

    private static final String BAD_COUNT_FOR_HANDS_BEGINNING = "Bad count for hands beginning";
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
    private static final String INVALID_RULES = "invalid rules";
    private static final String NULL_RULE = "null rule";
    private static final String BAD_CARD = "bad card";

    private CheckerGameBeloteWithRules() {
    }

    public static void check(GameBelote _loadedGame) {
        RulesBelote rules_ = _loadedGame.getRegles();
        if (rules_ == null) {
            throw new BeloteRulesException(NULL_RULE);
        }
        if (!rules_.isValidRules()) {
            throw new BeloteRulesException(INVALID_RULES);
        }
        _loadedGame.loadGame();
        if (_loadedGame.getDistribution().nombreDeMains() != rules_.getRepartition().getNombreJoueurs() + 1) {
            throw new BeloteRulesException(BAD_COUNT_FOR_DEAL);
        }
        if (_loadedGame.getDistribution().derniereMain().total() != rules_.getRepartition().getRemainingCards()) {
            throw new BeloteRulesException(BAD_COUNT_FOR_REMAINING_CARDS);
        }
        CustList<TrickBelote> allTricks_ = _loadedGame.unionPlis();
        HandBelote cards_ = new HandBelote();
        for (TrickBelote t: allTricks_) {
            for (CardBelote c: t) {
                cards_.ajouter(c);
            }
        }
        for (CardBelote c: _loadedGame.getPliEnCours()) {
            cards_.ajouter(c);
        }
        for (HandBelote c: _loadedGame.getDistribution()) {
            cards_.ajouterCartes(c);
        }
        for (byte p: _loadedGame.orderedPlayers(_loadedGame.getDistribution().getDonneur())) {
            for (CardBelote c: _loadedGame.getAnnoncesBeloteRebelote(p)) {
                cards_.ajouter(c);
            }
            for (CardBelote c: _loadedGame.getAnnonce(p).getMain()) {
                cards_.ajouter(c);
            }
        }
        for (CardBelote e: cards_) {
            if (!e.isPlayable()) {
                throw new BeloteRulesException(BAD_CARD);
            }
        }
        for (TrickBelote t: allTricks_) {
            if (t.total() != _loadedGame.getNombreDeJoueurs()) {
                throw new BeloteRulesException(TRICK_WITH_BAD_COUNT);
            }
        }
        if (_loadedGame.getPliEnCours().total() > _loadedGame.getNombreDeJoueurs()) {
            throw new BeloteRulesException(TRICK_WITH_BAD_COUNT);
        }
        Numbers<Byte> players_ = _loadedGame.orderedPlayers(_loadedGame.getDistribution().getDonneur());
        DealBelote deal_ = new DealBelote(_loadedGame.getDistribution());
        for (TrickBelote t: allTricks_) {
            for (CardBelote c: t) {
                byte player_ = t.joueurAyantJoue(c);
                deal_.main(player_).ajouter(c);
            }
        }
        for (CardBelote c: _loadedGame.getPliEnCours()) {
            byte player_ = _loadedGame.getPliEnCours().joueurAyantJoue(c,_loadedGame.getNombreDeJoueurs());
            deal_.main(player_).ajouter(c);
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
            for (byte p: players_) {
                if (deal_.main(p).total() != rules_.getRepartition().getNombreCartesParJoueur()) {
                    throw new BeloteRulesException(BAD_COUNT_FOR_HANDS);
                }
            }
            if (!rules_.dealAll()) {
                reinitializeGame(deal_, _loadedGame);
            }
        } else {
            boolean allCompleted_ = true;
            for (byte p: players_) {
                if (deal_.main(p).total() != rules_.getRepartition().getNombreCartesParJoueur()) {
                    allCompleted_ = false;
                    break;
                }
            }
            if (!allCompleted_) {
                for (byte p: players_) {
                    if (deal_.main(p).total() != rules_.getRepartition().getFirstCards()) {
                        throw new BeloteRulesException(BAD_COUNT_FOR_HANDS);
                    }
                }
            } else {
                reinitializeGame(deal_, _loadedGame);
            }
        }
        boolean allCardsUsedOnce_ = true;
        for (CardBelote c: HandBelote.pileBase()) {
            int nbUses_ = 0;
            for (HandBelote t: deal_) {
                for (CardBelote cTwo_: t) {
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
            throw new BeloteRulesException(ALL_CARDS_AT_REMAINING_CARDS_ARE_NOT_USED_ONCE);
        }
        GameBelote loadedGameCopy_ = new GameBelote(_loadedGame.getType(), deal_, rules_);
        if (!_loadedGame.getContrat().jouerDonne()) {
            if (!allTricks_.isEmpty()) {
                throw new BeloteRulesException(THERE_SHOULD_NOT_BE_ANY_TRICK);
            }
            if (!_loadedGame.getPliEnCours().estVide()) {
                throw new BeloteRulesException(THERE_SHOULD_NOT_BE_ANY_TRICK);
            }
            //return;
        }
        if (!rules_.dealAll()) {
            players_ = loadedGameCopy_.orderedPlayers(loadedGameCopy_.playerAfter(loadedGameCopy_.getDistribution().getDonneur()));
            boolean finished_ = false;
            int i_ = 0;
            for(byte p:players_) {
                if (i_ == _loadedGame.tailleContrats()) {
                    finished_ = true;
                    break;
                }
                if (!_loadedGame.contrat(i_).estDemandable(loadedGameCopy_.getContrat())) {
                    throw new BeloteRulesException(INVALID_BID);
                }
                boolean found_ = false;
                int pts_ = 0;
                for (BidBeloteSuit bid_ : loadedGameCopy_.allowedBids()) {
                    if (bid_.getEnchere() != _loadedGame.contrat(i_).getEnchere()) {
                        continue;
                    }
                    if (bid_.getCouleur() != _loadedGame.contrat(i_).getCouleur()) {
                        continue;
                    }
                    found_ = true;
                    pts_ = bid_.getPoints();
                    break;
                }
                if (!found_) {
                    throw new BeloteRulesException(INVALID_BID);
                }
                if (pts_ > _loadedGame.contrat(i_).getPoints()) {
                    throw new BeloteRulesException(INVALID_BID);
                }
                loadedGameCopy_.ajouterContrat(_loadedGame.contrat(i_),p);
                if (!loadedGameCopy_.keepBidding()) {
                    finished_ = true;
                    if (_loadedGame.tailleContrats() > i_ + 1) {
                        throw new BeloteRulesException(TOO_MUCH_BIDS);
                    }
                    break;
                }
                i_++;
            }
            if (!finished_) {
                loadedGameCopy_.finEncherePremierTour();
                for(byte joueur_:players_) {
                    if (i_ == _loadedGame.tailleContrats()) {
                        break;
                    }
                    if (!_loadedGame.contrat(i_).estDemandable(loadedGameCopy_.getContrat())) {
                        throw new BeloteRulesException(INVALID_BID);
                    }
                    boolean found_ = false;
                    int pts_ = 0;
                    for (BidBeloteSuit bid_ : loadedGameCopy_.allowedBids()) {
                        if (bid_.getEnchere() != _loadedGame.contrat(i_).getEnchere()) {
                            continue;
                        }
                        if (bid_.getCouleur() != _loadedGame.contrat(i_).getCouleur()) {
                            continue;
                        }
                        found_ = true;
                        pts_ = bid_.getPoints();
                        break;
                    }
                    if (!found_) {
                        throw new BeloteRulesException(INVALID_BID);
                    }
                    if (pts_ > _loadedGame.contrat(i_).getPoints()) {
                        throw new BeloteRulesException(INVALID_BID);
                    }
                    loadedGameCopy_.ajouterContrat(_loadedGame.contrat(i_),joueur_);
                    if (!loadedGameCopy_.keepBidding()) {
                        if (_loadedGame.tailleContrats() > i_ + 1) {
                            throw new BeloteRulesException(TOO_MUCH_BIDS);
                        }
                        break;
                    }
                    i_++;
                }
            }
        } else {
            players_ = loadedGameCopy_.orderedPlayers(loadedGameCopy_.playerAfter(loadedGameCopy_.getDistribution().getDonneur()));
            int i_ = 0;
            int nbFold_ = 0;
            int pts_ = 0;
            while (i_ < _loadedGame.tailleContrats()) {
                BidBeloteSuit bid_;
                bid_ = _loadedGame.contrat(i_);
                if (bid_.jouerDonne()) {
                    if (!_loadedGame.getRegles().getEncheresAutorisees().getVal(bid_.getEnchere())) {
                        throw new BeloteRulesException(BIDDING_LOWER);
                    }
                    if (!RulesBelote.getPoints().containsObj(bid_.getPoints())) {
                        throw new BeloteRulesException(BIDDING_LOWER);
                    }
                    nbFold_ = 0;
                    if (pts_ >= bid_.getPoints()) {
                        throw new BeloteRulesException(BIDDING_LOWER);
                    }
                    pts_ = bid_.getPoints();
                } else {
                    nbFold_++;
                    if (nbFold_ >= loadedGameCopy_.getNombreDeJoueurs()) {
                        throw new BeloteRulesException(BIDDING_TOO_MUCH_LOW);
                    }
                    if (nbFold_ >= loadedGameCopy_.getNombreDeJoueurs() - 1) {
                        if (i_ + 1 < _loadedGame.tailleContrats() && _loadedGame.contrat(i_ + 1).jouerDonne()) {
                            throw new BeloteRulesException(BIDDING_TOO_MUCH_LOW);
                        }
                    }
                }
                loadedGameCopy_.ajouterContrat(bid_, (byte) ((i_ + loadedGameCopy_.getDistribution().getDonneur() + 1) % loadedGameCopy_.getNombreDeJoueurs()));
                i_++;
            }
        }
        if (allTricks_.isEmpty() && _loadedGame.getPliEnCours().estVide()) {
            return;
        }
        loadedGameCopy_.completerDonne();
        int firstPlayerTrick_ = _loadedGame.playerAfter(_loadedGame.getDistribution().getDonneur());
        if (loadedGameCopy_.getRegles().dealAll()) {
            int pts_ = loadedGameCopy_.getContrat().getPoints();
            if (pts_ >= HandBelote.pointsTotauxDixDeDer(loadedGameCopy_.getContrat())) {
                loadedGameCopy_.setEntameur(loadedGameCopy_.getPreneur());
                firstPlayerTrick_ = _loadedGame.getPreneur();
            }
        }
        loadedGameCopy_.setPliEnCours();
        HandBelote playedCards_ = _loadedGame.cartesJouees();
        playedCards_.ajouterCartes(_loadedGame.getPliEnCours().getCartes());
        int nbPlayers_ = _loadedGame.getNombreDeJoueurs();
        for (byte b = CustList.FIRST_INDEX; b < nbPlayers_; b++) {
            for (CardBelote c: _loadedGame.getAnnoncesBeloteRebelote(b)) {
                if (!_loadedGame.cartesBeloteRebelote().contient(c)) {
                    throw new BeloteRulesException(BAD_DECLARING);
                }
                if (!playedCards_.contient(c)) {
                    throw new BeloteRulesException(BAD_DECLARING);
                }
                if (!loadedGameCopy_.getDistribution().main(b).contient(c)) {
                    throw new BeloteRulesException(BAD_DECLARING);
                }
            }
        }
        int ind_ = 0;
        boolean passe_=false;
        TrickBelote firstTrick_;
        if (!allTricks_.isEmpty()) {
            firstTrick_ = allTricks_.first();
        } else {
            firstTrick_ = _loadedGame.getPliEnCours();
        }
        while(true) {
            if(passe_) {
                loadedGameCopy_.ajouterPliEnCours();
                loadedGameCopy_.setEntameur();
                loadedGameCopy_.setPliEnCours();
            }
            TrickBelote trick_;
            if (ind_ == 0) {
                if (firstTrick_.getEntameur() != firstPlayerTrick_) {
                    throw new BeloteRulesException(BAD_MATCHING_WITH_TRICK_LEADER);
                }
                trick_ = firstTrick_;
            } else if (ind_ + 1 < allTricks_.size()) {
                if (allTricks_.get(ind_ - 1).getRamasseur(_loadedGame.getContrat()) != allTricks_.get(ind_).getEntameur()) {
                    throw new BeloteRulesException(BAD_MATCHING_WITH_TRICK_LEADER);
                }
                trick_ = allTricks_.get(ind_);
            } else {
                if (allTricks_.last().getRamasseur(_loadedGame.getContrat()) != _loadedGame.getPliEnCours().getEntameur()) {
                    throw new BeloteRulesException(BAD_MATCHING_WITH_TRICK_LEADER);
                }
                trick_ = _loadedGame.getPliEnCours();
            }
            for(byte p:loadedGameCopy_.orderedPlayers(loadedGameCopy_.getEntameur())) {
                if (!trick_.aJoue(p, loadedGameCopy_.getNombreDeJoueurs())) {
                    return;
                }
                CardBelote ct_=trick_.carteDuJoueur(p, _loadedGame.getNombreDeJoueurs());
                if (!loadedGameCopy_.autorise(ct_, Constants.getLanguage())) {
                    throw new BeloteRulesException(BAD_PLAYING);
                }
                if(_loadedGame.getAnnoncesBeloteRebelote(p).contient(ct_)) {
                    loadedGameCopy_.setAnnoncesBeloteRebelote(p,ct_);
                }
                if (loadedGameCopy_.premierTour()) {
                    if (_loadedGame.getAnnonce(p).getAnnonce() != DeclaresBelote.UNDEFINED) {
                        DeclareHandBelote declaring_ = loadedGameCopy_.strategieAnnonces(p);
                        if (!_loadedGame.getAnnonce(p).eq(declaring_)) {
                            throw new BeloteRulesException(BAD_DECLARING);
                        }
                    }
                    if (_loadedGame.getAnnonce(p).getAnnonce() != DeclaresBelote.UNDEFINED) {
                        loadedGameCopy_.annoncer(p);
                    }
                }
                if(!passe_) {
                    passe_=true;
                }
                loadedGameCopy_.getDistribution().jouer(p,ct_);
                loadedGameCopy_.ajouterUneCarteDansPliEnCours(ct_);
            }
            if(loadedGameCopy_.getDistribution().main().estVide()) {
                /*Il y a dix_ de_ der_*/
                loadedGameCopy_.ajouterPliEnCours();
                loadedGameCopy_.setDixDeDer(loadedGameCopy_.getRamasseur());
                break;
            }
            ind_++;
        }

    }

    static void reinitializeGame(DealBelote _restitutedDeal, GameBelote _loadedGame) {
        Numbers<Byte> players_ = _loadedGame.orderedPlayers(_loadedGame.getDistribution().getDonneur());
        RulesBelote rules_ = _loadedGame.getRegles();
        HandBelote talon_=new HandBelote();
        talon_.ajouterCartes(_loadedGame.getDistribution().derniereMain());
        HandBelote h_ = new HandBelote();
        h_.ajouter(talon_.jouer(CustList.FIRST_INDEX));
        _restitutedDeal.main(_loadedGame.getPreneur()).supprimerCartes(h_);
        boolean dejaCommence_ = false;
        for(int i: rules_.getRepartition().getDistributionFin()) {
            for (byte j : _loadedGame.orderedPlayers(_loadedGame.playerAfter(_loadedGame.getDistribution().getDonneur()))) {
                for (int k = CustList.SECOND_INDEX; k < i; k++) {
                    h_ = new HandBelote();
                    h_.ajouter(talon_.jouer(CustList.FIRST_INDEX));
                    _restitutedDeal.main(j).supprimerCartes(h_);
                }
                if(j!=_loadedGame.getPreneur() || dejaCommence_) {
                    h_ = new HandBelote();
                    h_.ajouter(talon_.jouer(CustList.FIRST_INDEX));
                    _restitutedDeal.main(j).supprimerCartes(h_);
                }
            }
            dejaCommence_ = true;
        }

        for (byte p: players_) {
            if (rules_.getRepartition().getFirstCards() != _restitutedDeal.main(p).total()) {
                throw new BeloteRulesException(BAD_COUNT_FOR_HANDS_BEGINNING);
            }
        }
    }
}
