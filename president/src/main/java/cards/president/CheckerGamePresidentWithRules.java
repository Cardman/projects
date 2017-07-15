package cards.president;
import code.util.CustList;
import code.util.Numbers;
import code.util.consts.Constants;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import cards.president.exceptions.PresidentRulesException;


public final class CheckerGamePresidentWithRules {

    private static final String EMPTY = "";

    private CheckerGamePresidentWithRules() {
    }

    public static void check(GamePresident _loadedGame) {
        RulesPresident rules_ = _loadedGame.getRegles();
        if (rules_ == null) {
            throw new PresidentRulesException(EMPTY);
        }
        if (!rules_.isValidRules()) {
            throw new PresidentRulesException(EMPTY);
        }
        _loadedGame.loadGame();
        byte nbPlayers_ = (byte) rules_.getNbPlayers();
        if (_loadedGame.getDistribution().nombreDeMains() != nbPlayers_) {
            throw new PresidentRulesException(EMPTY);
        }
        CustList<TrickPresident> allTricks_ = _loadedGame.unionPlis();
        HandPresident cards_ = new HandPresident();
        for (TrickPresident t: allTricks_) {
            for (HandPresident c: t) {
                cards_.ajouterCartes(c);
            }
        }
        for (HandPresident c: _loadedGame.getProgressingTrick()) {
            cards_.ajouterCartes(c);
        }
        for (HandPresident c: _loadedGame.getDistribution()) {
            cards_.ajouterCartes(c);
        }
        for (HandPresident s: _loadedGame.getSwitchedCards().values()) {
            cards_.ajouterCartes(s);
        }
        for (CardPresident e: cards_) {
            if (!e.isPlayable()) {
                throw new PresidentRulesException(EMPTY);
            }
        }
        DealPresident deal_ = new DealPresident(_loadedGame.getDistribution());
        int indexCurTrick_ = CustList.FIRST_INDEX;
        for (TrickPresident t: allTricks_) {
            int index_ = CustList.FIRST_INDEX;
            for (HandPresident c: t) {
//                for (CardPresident e: c) {
//                    if (!e.isPlayable()) {
//                        throw new PresidentRulesException(EMPTY);
//                    }
//                }
                byte player_ = t.getPlayer(index_, nbPlayers_);
                deal_.main(player_).ajouterCartes(c);
                index_++;
            }
        }
        for (HandPresident c: _loadedGame.getProgressingTrick()) {
//            for (CardPresident e: c) {
//                if (!e.isPlayable()) {
//                    throw new PresidentRulesException(EMPTY);
//                }
//            }
            byte player_ = _loadedGame.getProgressingTrick().getPlayer(indexCurTrick_, nbPlayers_);
            deal_.main(player_).ajouterCartes(c);
            indexCurTrick_++;
        }
//        for (HandPresident s: _loadedGame.getSwitchedCards().values()) {
//            for (CardPresident e: s) {
//                if (!e.isPlayable()) {
//                    throw new PresidentRulesException(EMPTY);
//                }
//            }
//        }
        if (!_loadedGame.getRanks().isEmpty()) {
            if (_loadedGame.getRanks().size() != nbPlayers_) {
                throw new PresidentRulesException(EMPTY);
            }
            Numbers<Byte> copyRanks_ = new Numbers<Byte>(_loadedGame.getRanks());
            int len_ = copyRanks_.size();
            copyRanks_.removeDuplicates();
            if (len_ != copyRanks_.size()) {
                throw new PresidentRulesException(EMPTY);
            }
        }
        boolean readyToPlay_ = true;
        if (_loadedGame.availableSwitchingCards()) {
            for (byte w: _loadedGame.getWinners()) {
                int ind_= _loadedGame.getWinners().indexOfObj(w);
                int nbGivenCards_ = _loadedGame.nombresCartesEchangesMax() - ind_;
                if (w == DealPresident.NUMERO_UTILISATEUR) {
                    if (!_loadedGame.getSwitchedCards().getVal(w).estVide()) {
                        if (_loadedGame.getSwitchedCards().getVal(w).total() != nbGivenCards_) {
                            throw new PresidentRulesException(EMPTY);
                        }
                    } else {
                        readyToPlay_ = false;
                    }
                    continue;
                }
                if (_loadedGame.getSwitchedCards().getVal(w).total() != nbGivenCards_) {
                    throw new PresidentRulesException(EMPTY);
                }
            }
            for (byte l: _loadedGame.getLoosers()) {
                int ind_= _loadedGame.getLoosers().indexOfObj(l);
                int nbGivenCards_ = _loadedGame.nombresCartesEchangesMax() - ind_;
                if (_loadedGame.getSwitchedCards().getVal(l).total() != nbGivenCards_) {
                    throw new PresidentRulesException(EMPTY);
                }
            }
            for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
                if (_loadedGame.getWinners().containsObj(p)) {
                    continue;
                }
                if (_loadedGame.getLoosers().containsObj(p)) {
                    continue;
                }
                if (!_loadedGame.getSwitchedCards().getVal(p).estVide()) {
                    throw new PresidentRulesException(EMPTY);
                }
            }
            if (!readyToPlay_) {
                if (!_loadedGame.getTricks().isEmpty() || !_loadedGame.getProgressingTrick().estVide()) {
                    throw new PresidentRulesException(EMPTY);
                }
                for (byte w: _loadedGame.getWinners()) {
                    if (w == DealPresident.NUMERO_UTILISATEUR) {
                        continue;
                    }
                    byte pl_ = _loadedGame.getMatchingLoser(w);
                    HandPresident hl_ = new HandPresident(deal_.main(w));
                    hl_.ajouterCartes(_loadedGame.getSwitchedCards().getVal(pl_));
                    if (!hl_.containsCards(_loadedGame.getSwitchedCards().getVal(w))) {
                        throw new PresidentRulesException(EMPTY);
                    }
                }
            } else {
                for (byte w: _loadedGame.getWinners()) {
                    byte pl_ = _loadedGame.getMatchingLoser(w);
                    HandPresident hl_ = new HandPresident(deal_.main(pl_));
                    hl_.ajouterCartes(_loadedGame.getSwitchedCards().getVal(pl_));
                    if (!hl_.containsCards(_loadedGame.getSwitchedCards().getVal(w))) {
                        throw new PresidentRulesException(EMPTY);
                    }
                }
                for (byte p: _loadedGame.getSwitchedCards().getKeys()) {
                    deal_.main(p).ajouterCartes(_loadedGame.getSwitchedCards().getVal(p));
                }
            }
            for (byte l: _loadedGame.getLoosers()) {
                int ind_= _loadedGame.getLoosers().indexOfObj(l);
                byte pl_ =_loadedGame.getWinners().get(ind_);
                deal_.main(l).supprimerCartes(_loadedGame.getSwitchedCards().getVal(pl_));
            }
            for (byte w: _loadedGame.getWinners()) {
                int ind_= _loadedGame.getWinners().indexOfObj(w);
                byte pl_ = _loadedGame.getLoosers().get(ind_);
                deal_.main(w).supprimerCartes(_loadedGame.getSwitchedCards().getVal(pl_));
            }
            for (byte l: _loadedGame.getLoosers()) {
                HandPresident hCopy_ = new HandPresident(deal_.main(l));
                HandPresident hSwitchCopy_ = new HandPresident(_loadedGame.getSwitchedCards().getVal(l));
                hCopy_.sortCardsBegin();
                hSwitchCopy_.sortCardsBegin();
                int lenGiv_ = hSwitchCopy_.total();
                for (int i = CustList.FIRST_INDEX; i < lenGiv_; i++) {
                    byte strGiv_ = hSwitchCopy_.carte(i).strength(false);
                    byte str_ = hCopy_.carte(i).strength(false);
                    if (strGiv_ != str_) {
                        throw new PresidentRulesException(EMPTY);
                    }
                }
            }
//            for (Byte w: _loadedGame.getWinners()) {
//                int ind_= _loadedGame.getWinners().indexOfObj(w);
//                byte pl_ = _loadedGame.getLoosers().get(ind_);
//                HandPresident hCopy_ = new HandPresident(deal_.main(w));
//                hCopy_.ajouterCartes(_loadedGame.getSwitchedCards().getVal(pl_));
//                if (!hCopy_.containsCards(_loadedGame.getSwitchedCards().getVal(w))) {
//                    throw new PresidentRulesException("");
//                }
//            }
//            return;
        } else {
            if (!_loadedGame.getSwitchedCards().isEmpty()) {
                throw new PresidentRulesException(EMPTY);
            }
        }
//        if (!_loadedGame.getRanks().isEmpty() && !_loadedGame.readyToPlay()) {
//            for (Byte w: _loadedGame.getWinners()) {
//                int ind_= _loadedGame.getWinners().indexOfObj(w);
//                byte pl_ = _loadedGame.getLoosers().get(ind_);
//                deal_.main(w).supprimerCartes(_loadedGame.getSwitchedCards().getVal(pl_));
//            }
//            for (Byte l: _loadedGame.getLoosers()) {
//                int ind_= _loadedGame.getLoosers().indexOfObj(l);
//                byte pl_ =_loadedGame.getWinners().get(ind_);
//                deal_.main(l).supprimerCartes(_loadedGame.getSwitchedCards().getVal(pl_));
//            }
//        }
        int nbCards_ = rules_.getNbStacks() * HandPresident.pileBase().total();
        int rem_ = nbCards_ % nbPlayers_;
        boolean noRem_ = rem_ == 0;
        int nbCardsPerPlayer_ = nbCards_ / nbPlayers_;
        if (noRem_) {
            for (HandPresident h: deal_) {
                if (h.total() != nbCardsPerPlayer_) {
                    throw new PresidentRulesException(EMPTY);
                }
            }
        } else {
            for (HandPresident h: deal_) {
                if (h.total() > nbCardsPerPlayer_ + 1) {
                    throw new PresidentRulesException(EMPTY);
                }
                if (h.total() < nbCardsPerPlayer_) {
                    throw new PresidentRulesException(EMPTY);
                }
            }
//            CustList<Byte> allPlayers_ = rules_.getSortedPlayersAfter(deal_.getDonneur());
//            CustList<Byte> playersWithLongestHands_ = allPlayers_.sub(CustList.FIRST_INDEX, rem_);
//            CustList<Byte> playersWithShortestHands_ = allPlayers_.mid(rem_);
//            for (Byte p: playersWithLongestHands_) {
//                if (deal_.main(p).total() != nbCardsPerPlayer_ + 1) {
//                    throw new PresidentRulesException("");
//                }
//            }
//            for (Byte p: playersWithShortestHands_) {
//                if (deal_.main(p).total() != nbCardsPerPlayer_) {
//                    throw new PresidentRulesException("");
//                }
//            }
        }
        boolean allCardsUsedNb_ = true;
        for (CardPresident c: HandPresident.pileBase()) {
            int nbUses_ = 0;
            for (HandPresident t: deal_) {
                for (CardPresident c2_: t) {
                    if (c == c2_) {
                        nbUses_++;
                    }
                }
            }
            if (nbUses_ == rules_.getNbStacks()) {
                continue;
            }
            allCardsUsedNb_ = false;
            break;
        }
        if (!allCardsUsedNb_) {
            throw new PresidentRulesException(EMPTY);
        }
        if (!readyToPlay_) {
            return;
        }
        if (_loadedGame.availableSwitchingCards()) {
            for (byte w: _loadedGame.getWinners()) {
                int ind_= _loadedGame.getWinners().indexOfObj(w);
                byte pl_ = _loadedGame.getLoosers().get(ind_);
                deal_.main(w).ajouterCartes(_loadedGame.getSwitchedCards().getVal(pl_));
            }
            for (byte l: _loadedGame.getLoosers()) {
                int ind_= _loadedGame.getLoosers().indexOfObj(l);
                byte pl_ =_loadedGame.getWinners().get(ind_);
                deal_.main(l).ajouterCartes(_loadedGame.getSwitchedCards().getVal(pl_));
            }
            for (byte p: _loadedGame.getSwitchedCards().getKeys()) {
                deal_.main(p).supprimerCartes(_loadedGame.getSwitchedCards().getVal(p));
            }
//            for (Byte w: _loadedGame.getWinners()) {
//                int ind_= _loadedGame.getWinners().indexOfObj(w);
//                byte pl_ = _loadedGame.getLoosers().get(ind_);
//                deal_.main(w).supprimerCartes(_loadedGame.getSwitchedCards().getVal(pl_));
//                deal_.main(w).ajouterCartes(_loadedGame.getSwitchedCards().getVal(w));
//            }
//            for (Byte l: _loadedGame.getLoosers()) {
//                int ind_= _loadedGame.getLoosers().indexOfObj(l);
//                byte pl_ =_loadedGame.getWinners().get(ind_);
//                deal_.main(l).supprimerCartes(_loadedGame.getSwitchedCards().getVal(pl_));
//                deal_.main(l).ajouterCartes(_loadedGame.getSwitchedCards().getVal(l));
//            }
//            for (Byte p: switchedCards.getKeys()) {
//                switchedCards.getVal(p).supprimerCartes();
//            }
//            for (Byte w: _loadedGame.getWinners()) {
////                int ind_= _loadedGame.getWinners().indexOfObj(w);
//                if (!deal_.main(w).containsCards(_loadedGame.getSwitchedCards().getVal(w))) {
//                    throw new PresidentRulesException("");
//                }
////                if (!_loadedGame.readyToPlay() && w == DealPresident.NUMERO_UTILISATEUR) {
////                    if (!_loadedGame.getSwitchedCards().getVal(w).estVide()) {
////                        throw new PresidentRulesException("");
////                    }
////                } else {
////                    int nbGivenCards_ = _loadedGame.nombresCartesEchangesMax() - ind_;
////                    if (_loadedGame.getSwitchedCards().getVal(w).total() != nbGivenCards_) {
////                        throw new PresidentRulesException("");
////                    }
////                }
//                //getDistribution().main(w).supprimerCartes(switchedCards.getVal(pl_));
//                //getDistribution().main(w).ajouterCartes(switchedCards.getVal(w));
//            }
//            for (Byte l: _loadedGame.getLoosers()) {
////                int ind_= _loadedGame.getLoosers().indexOfObj(l);
//                if (!deal_.main(l).containsCards(_loadedGame.getSwitchedCards().getVal(l))) {
//                    throw new PresidentRulesException("");
//                }
////                int nbGivenCards_ = _loadedGame.nombresCartesEchangesMax() - ind_;
////                if (_loadedGame.getSwitchedCards().getVal(l).total() != nbGivenCards_) {
////                    throw new PresidentRulesException("");
////                }
//                //getDistribution().main(l).supprimerCartes(switchedCards.getVal(pl_));
//                //getDistribution().main(l).ajouterCartes(switchedCards.getVal(l));
//            }
//            for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
//                if (_loadedGame.getWinners().containsObj(p)) {
//                    continue;
//                }
//                if (_loadedGame.getLoosers().containsObj(p)) {
//                    continue;
//                }
//                if (!_loadedGame.getSwitchedCards().getVal(p).estVide()) {
//                    throw new PresidentRulesException("");
//                }
//            }
//            for (Byte w: _loadedGame.getWinners()) {
//                int ind_= _loadedGame.getWinners().indexOfObj(w);
//                byte pl_ = _loadedGame.getLoosers().get(ind_);
//                deal_.main(w).ajouterCartes(_loadedGame.getSwitchedCards().getVal(pl_));
//                deal_.main(w).supprimerCartes(_loadedGame.getSwitchedCards().getVal(w));
//            }
//            for (Byte l: _loadedGame.getLoosers()) {
//                int ind_= _loadedGame.getLoosers().indexOfObj(l);
//                byte pl_ =_loadedGame. getWinners().get(ind_);
//                deal_.main(l).ajouterCartes(_loadedGame.getSwitchedCards().getVal(pl_));
//                deal_.main(l).supprimerCartes(_loadedGame.getSwitchedCards().getVal(l));
//            }
//            if (!_loadedGame.readyToPlay()) {
//                for (Byte l: _loadedGame.getLoosers()) {
//                    HandPresident hGiv_ = new HandPresident(_loadedGame.getSwitchedCards().getVal(l));
//                    hGiv_.sortCardsBegin();
//                    int lenGiv_ = hGiv_.total();
//                    HandPresident h_ = new HandPresident(deal_.main(l));
//                    h_.sortCardsBegin();
//                    for (int i = CustList.FIRST_INDEX; i < lenGiv_; i++) {
//                        byte strGiv_ = hGiv_.carte(i).strength(false);
//                        byte str_ = h_.carte(i).strength(false);
//                        if (strGiv_ != str_) {
//                            throw new PresidentRulesException("");
//                        }
//                    }
//                    //getDistribution().main(l).supprimerCartes(switchedCards.getVal(pl_));
//                    //getDistribution().main(l).ajouterCartes(switchedCards.getVal(l));
//                }
//            } else {
//                for (Byte l: _loadedGame.getLoosers()) {
//                    HandPresident hGiv_ = new HandPresident(_loadedGame.getSwitchedCards().getVal(l));
//                    hGiv_.sortCardsBegin();
//                    HandPresident h_ = new HandPresident(deal_.main(l));
//                    h_.sortCardsBegin();
//                    if (hGiv_.derniereCarte().strength(false) < h_.premiereCarte().strength(false)) {
//                        throw new PresidentRulesException("");
//                    }
//                    //getDistribution().main(l).supprimerCartes(switchedCards.getVal(pl_));
//                    //getDistribution().main(l).ajouterCartes(switchedCards.getVal(l));
//                }
//            }
//            if (!_loadedGame.readyToPlay()) {
//                return;
//            }
        }
        if (allTricks_.isEmpty() && _loadedGame.getProgressingTrick().estVide()) {
            return;
        }
        GamePresident loadedGameCopy_ = new GamePresident(_loadedGame.getType(), deal_, rules_, _loadedGame.getRanks());
        loadedGameCopy_.copySwitchCards(_loadedGame.getSwitchedCards());
//        loadedGameCopy_.revertGifts();
        int ind_ = 0;
//        boolean passe_=false;
//        loadedGameCopy_.initCartesEchanges();
//        loadedGameCopy_.donnerMeilleuresCartes();
//        for (Byte w: loadedGameCopy_.getWinners()) {
//
//        }
        loadedGameCopy_.initializeFirstTrick();
//        int firstPlayerTrick_ = _loadedGame.getFirstLeader();
        TrickPresident firstTrick_;
        if (!allTricks_.isEmpty()) {
            firstTrick_ = allTricks_.first();
        } else {
            firstTrick_ = _loadedGame.getProgressingTrick();
        }
//        loadedGameCopy_.setPliEnCours(true);
        while (true) {
//            if (passe_) {
//                loadedGameCopy_.ajouterPliEnCours();
//                loadedGameCopy_.setPliEnCours(true);
//            }
            TrickPresident trick_;
            if (ind_ == 0) {
//                if (firstTrick_.getEntameur() != firstPlayerTrick_) {
//                    throw new PresidentRulesException("");
//                }
                trick_ = firstTrick_;
//            } else if (ind_ + 1 < allTricks_.size()) {
            } else if (ind_ + 1 <= allTricks_.size()) {
//                if (allTricks_.get(ind_ - 1).getRamasseur(nbPlayers_) != allTricks_.get(ind_).getEntameur()) {
//                    throw new PresidentRulesException("");
//                }
                trick_ = allTricks_.get(ind_);
            } else {
//                if (allTricks_.last().getRamasseur(nbPlayers_) != _loadedGame.getProgressingTrick().getEntameur()) {
//                    throw new PresidentRulesException("");
//                }
                trick_ = _loadedGame.getProgressingTrick();
            }
            if (trick_.estVide()) {
                return;
            }
//            if (ind_ > loadedGameCopy_.unionPlis().size()) {
//                return;
//            }
//            if (ind_ == allTricks_.size()) {
//                if (allTricks_.get(ind_ - 1).getRamasseur(nbPlayers_) != _loadedGame.getProgressingTrick().getEntameur()) {
//                    throw new PresidentRulesException("");
//                }
//                trick_ = _loadedGame.getProgressingTrick();
//            } else {
//                if (allTricks_.get(ind_ - 1).getRamasseur(nbPlayers_) != allTricks_.get(ind_).getEntameur()) {
//                    throw new PresidentRulesException("");
//                }
//                trick_ = allTricks_.get(ind_);
//            }
            if (ind_ > allTricks_.size()) {
                return;
            }
            int nbCardsPerPlayerTrick_ = trick_.getNombreDeCartesParJoueur();
            int nbHands_ = trick_.total();
            if (nbCardsPerPlayerTrick_ == 0) {
                for (int i = CustList.FIRST_INDEX; i < nbHands_; i++) {
                    byte player_ = trick_.getPlayer(i, nbPlayers_);
                    HandPresident curHand_ = trick_.carte(i);
                    if (!curHand_.estVide()) {
                        throw new PresidentRulesException(EMPTY);
                    }
                    loadedGameCopy_.getProgressingTrick().ajouter(curHand_, player_);
                }
                loadedGameCopy_.unionPlis().add(loadedGameCopy_.getProgressingTrick());
                loadedGameCopy_.initializeTrick(trick_.getPlayer(trick_.total(), nbPlayers_));
            } else {
                for (int i = CustList.FIRST_INDEX; i < nbHands_; i++) {
                    byte player_ = trick_.getPlayer(i, nbPlayers_);
                    HandPresident curHand_ = trick_.carte(i);
                    Numbers<Byte> str_ = new Numbers<Byte>();
                    for (CardPresident c: curHand_) {
                        str_.add(c.strength(loadedGameCopy_.isReversed()));
                    }
                    if (!str_.isEmpty()) {
                        boolean same_ = Numbers.eq(str_.getMinimum(),str_.getMaximum());
//                        if (!loadedGameCopy_.keepPlayingCurrentGame()) {
//                            same_ = true;
//                        }
                        if (!same_ || str_.size() != nbCardsPerPlayerTrick_) {
                            throw new PresidentRulesException(EMPTY);
                        }
                    }
                    if (curHand_.estVide()) {
                        if (!loadedGameCopy_.canPass(player_, Constants.getLanguage())) {
                            throw new PresidentRulesException(EMPTY);
                        }
                        if (loadedGameCopy_.getStatus(player_) == Playing.CAN_PLAY) {
                            loadedGameCopy_.getPassOrFinish().set(player_, true);
                        }
//                        loadedGameCopy_.getProgressingTrick().ajouter(curHand_, player_);
                        loadedGameCopy_.addEmptyHandToCurrentTrick(player_);
                    } else {
                        if (!loadedGameCopy_.allowPlaying(player_, curHand_, Constants.getLanguage())) {
                            throw new PresidentRulesException(EMPTY);
                        }
                        loadedGameCopy_.addCardsToCurrentTrick(player_, curHand_);
                        if (loadedGameCopy_.getDistribution().main(player_).estVide()) {
                            loadedGameCopy_.getPassOrFinish().set(player_, true);
                        }
                    }
//                    if (!loadedGameCopy_.keepPlayingCurrentTrick()) {
//                        for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
//                            loadedGameCopy_.getPassOrFinish().set(p, loadedGameCopy_.getDistribution().main(p).estVide());
//                        }
//                    }
                }
            }
            for (byte p = CustList.FIRST_INDEX; p < nbPlayers_; p++) {
                loadedGameCopy_.getPassOrFinish().set(p, loadedGameCopy_.getDistribution().main(p).estVide());
            }
            if (ind_ >= loadedGameCopy_.unionPlis().size()) {
                return;
            }
//            if (allTricks_.isEmpty()) {
//                break;
//            }
            ind_++;
            if (!loadedGameCopy_.keepPlayingCurrentGame()) {
                break;
            }
        }
    }
}
