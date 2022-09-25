package cards.president;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.comparators.HandPresidentRepartition;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

final class GamePresidentCommon {

    static final int NB_SUITS = 4;

    private GamePresidentCommon(){
    }

    static HandPresident playHand(CardPresident _card, byte _nb, HandPresident _main, boolean _reversed, TrickPresident _progressingTrick) {
        HandPresident h_ = _main.getCardsByStrength(_card.strength(_reversed), _reversed);
        HandPresident cardsToBePlayed_ = new HandPresident();
        if (_progressingTrick.estVide()) {
            for (CardPresident c: h_) {
                if (cardsToBePlayed_.total() >= _nb) {
                    break;
                }
                cardsToBePlayed_.ajouter(c);
            }
        } else {
            for (CardPresident c: h_) {
                if (cardsToBePlayed_.total() >= _progressingTrick.getNombreDeCartesParJoueur()) {
                    break;
                }
                cardsToBePlayed_.ajouter(c);
            }
        }
        return cardsToBePlayed_;
    }

    static HandPresident dominantHand(boolean _reversed, RulesPresident _rules, HandPresident _h, HandPresidentRepartition _playedCards) {
        HandPresident c_ = new HandPresident(_h);
        ByteTreeMap<HandPresident> gl_ = _h.getCardsByStrength(_reversed);
        CustList<HandPresident> rep_ = getCardsSortedByLengthSortedByStrengthReduce(_reversed, _rules, c_);
        CustList<HandPresident> hWorst_ = new CustList<HandPresident>();
        CustList<HandPresident> hBest_ = new CustList<HandPresident>();
        int maxStack_ = _rules.getNbStacks() * NB_SUITS;
        for (int i = IndexConstants.SECOND_INDEX; i <= maxStack_; i++) {
            HandPresident repLoc_ = new HandPresident(rep_.get(i-1));
            completeRepLoc(_reversed, _rules, _playedCards, c_, gl_, i, repLoc_);
            for (HandPresident h: repLoc_.getCardsByStrength(_reversed).values()) {
                if (h.estVide()) {
                    continue;
                }
                if (dominantGroup(_reversed, _rules, h, i, _playedCards, gl_)) {
                    feedBest(hBest_, i, h);
                } else if (h.premiereCarte().strength(_reversed) < GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                    hWorst_.add(h);
                }
            }
            if (hWorst_.size() > hBest_.size() + 1) {
                return new HandPresident();
            }
            delBounds(c_, hWorst_, hBest_);
            if (c_.estVide() && !hWorst_.isEmpty()) {
                return hWorst_.first();
            }
            hBest_.clear();
            hWorst_.clear();
            rep_ = getCardsSortedByLengthSortedByStrengthReduce(_reversed, _rules, c_);
        }
        return lookForDominant(_reversed, c_);
    }

    private static HandPresident lookForDominant(boolean _reversed, HandPresident _c) {
        if (_c.estVide()) {
            return _c;
        }
        CustList<HandPresident> cardsLists_ = new CustList<HandPresident>();
        for (HandPresident h: _c.getCardsByStrength(_reversed).values()) {
            if (h.estVide()) {
                continue;
            }
            if (h.premiereCarte().strength(_reversed) < GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                cardsLists_.add(h);
            }
        }
        if (cardsLists_.size() > IndexConstants.ONE_ELEMENT) {
            return new HandPresident();
        }
        if (cardsLists_.size() == IndexConstants.ONE_ELEMENT) {
            return cardsLists_.last();
        }
        return _c.getCardsByStrength(_reversed).values().first();
    }

    private static void delBounds(HandPresident _cc, CustList<HandPresident> _hWorst, CustList<HandPresident> _hBest) {
        int m_ = NumberUtil.min(_hWorst.size(), _hBest.size());
        for (int j = IndexConstants.FIRST_INDEX; j < m_; j++) {
            _cc.supprimerCartes(_hBest.get(j));
            _cc.supprimerCartes(_hWorst.get(j));
        }
    }

    private static void completeRepLoc(boolean _reversed, RulesPresident _rules, HandPresidentRepartition _playedCards, HandPresident _c, ByteTreeMap<HandPresident> _gl, int _i, HandPresident _repLoc) {
        for (HandPresident h: _c.getCardsByStrength(_reversed).values()) {
            if (h.estVide() || h.total() == _i) {
                continue;
            }
            if (dominantGroup(_reversed, _rules, h, 1, _playedCards, _gl)) {
                _repLoc.ajouterCartes(h);
            }
        }
    }

    private static void feedBest(CustList<HandPresident> _hBest, int _i, HandPresident _h) {
        int nbGroup_ = _h.total() / _i;
        int index_ = IndexConstants.FIRST_INDEX;
        for (int j = IndexConstants.FIRST_INDEX; j < nbGroup_; j++) {
            HandPresident hBestCards_ = new HandPresident();
            for (int k = IndexConstants.FIRST_INDEX; k < _i; k++) {
                hBestCards_.ajouter(_h.carte(index_));
                index_++;
            }
            _hBest.add(hBestCards_);
        }
    }

    static HandPresidentRepartition getNotFullPlayedCardsByStrength(boolean _reversed, CustList<TrickPresident> _tricks, TrickPresident _progressingTrick, int _max) {
        HandPresidentRepartition tree_;
        tree_ = new HandPresidentRepartition(_reversed);
        HandPresidentRepartition playedCardsByStrength_ = getPlayedCardsByStrength(_reversed, _tricks, _progressingTrick);
        return filter(_max, playedCardsByStrength_, tree_);
    }

    static HandPresidentRepartition filter(int _max, HandPresidentRepartition _playedCardsByStrength, HandPresidentRepartition _tree) {
        for (EntryCust<CardPresident, Byte> e: _playedCardsByStrength.entryList()) {
            Byte count_ = e.getValue();
            if (count_ == _max) {
                continue;
            }
            _tree.addEntry(e.getKey(), count_);
        }
        return _tree;
    }

    static HandPresidentRepartition getPlayedCardsByStrength(boolean _reversed, CustList<TrickPresident> _tricks, TrickPresident _progressingTrick) {
        HandPresident playedCards_ = new HandPresident();
        for (TrickPresident t: _tricks) {
            for (HandPresident h: t) {
                playedCards_.ajouterCartes(h);
            }
        }
        for (HandPresident h: _progressingTrick) {
            playedCards_.ajouterCartes(h);
        }
        return getPlayedCardsByStrength(_reversed, playedCards_);
    }

    static HandPresidentRepartition getPlayedCardsByStrength(boolean _reversed, HandPresident _playedCards) {
        HandPresidentRepartition tree_;
        tree_ = new HandPresidentRepartition(_reversed);
        for (CardPresident c: _playedCards) {
            boolean eqStrPres_ = false;
            for (CardPresident s: tree_.getKeys()) {
                byte str_ = s.strength(_reversed);
                if (str_ == c.strength(_reversed)) {
                    eqStrPres_ = true;
                    byte nb_ = tree_.getVal(s);
                    nb_++;
                    tree_.put(s, nb_);
                    break;
                }
            }
            if (!eqStrPres_) {
                tree_.put(c, IndexConstants.ONE_ELEMENT);
            }
        }
        for (CardPresident c: HandPresident.pileBase()) {
            boolean eqStrPres_ = foundEqStrength(_reversed, tree_, c);
            if (!eqStrPres_) {
                tree_.put(c, IndexConstants.SIZE_EMPTY);
            }
        }
        return tree_;
    }

    private static boolean foundEqStrength(boolean _reversed, HandPresidentRepartition _tree, CardPresident _c) {
        boolean eqStrPres_ = false;
        for (CardPresident s: _tree.getKeys()) {
            byte str_ = s.strength(_reversed);
            if (str_ == _c.strength(_reversed)) {
                eqStrPres_ = true;
                break;
            }
        }
        return eqStrPres_;
    }

    static boolean dominantGroup(boolean _reversed, RulesPresident _rules, HandPresident _h, int _nb, HandPresidentRepartition _playedCards, ByteTreeMap<HandPresident> _rep) {
        int maxStack_ = _rules.getNbStacks() * NB_SUITS;
        if (_h.premiereCarte().strength(_reversed) == CardPresident.getMaxStrength(_reversed)) {
            return true;
        }
        int str_ = _h.premiereCarte().strength(_reversed);
        HandPresidentRepartition tree_;
        if (_rules.getEqualty() == EqualtyPlaying.FORBIDDEN) {
            tree_ = getTreeExcluding(_reversed, _playedCards, str_);
        } else {
            tree_ = _playedCards;
        }
        for (EntryCust<CardPresident,Byte> e: tree_.entryList()) {
            CardPresident c_ = e.getKey();
            byte v_ = e.getValue();
            byte strLoc_ = c_.strength(_reversed);
            if (strLoc_ < str_) {
                break;
            }
            byte rem_ = (byte) (maxStack_ - v_ - _rep.getVal(strLoc_).total());
            if (rem_ >= _nb) {
                return false;
            }
        }
        return true;
    }

    private static HandPresidentRepartition getTreeExcluding(boolean _reversed, HandPresidentRepartition _tree, int _str) {
        HandPresidentRepartition tree_;
        tree_ = new HandPresidentRepartition(_reversed);
        for (EntryCust<CardPresident, Byte> e:_tree.entryList()) {
            if (e.getKey().strength(_reversed) == _str) {
                continue;
            }
            Byte count_ = e.getValue();
            tree_.addEntry(e.getKey(), count_);
        }
        return tree_;
    }
    static CustList<HandPresident> getCardsSortedByLengthSortedByStrengthReduce(boolean _reversed, RulesPresident _rules, HandPresident _hand) {
        CustList<HandPresident> l_ = new CustList<HandPresident>();
        int nbMaxLen_ = _rules.getNbStacks() * NB_SUITS;
        for (int i = IndexConstants.SECOND_INDEX; i <= nbMaxLen_; i++) {
            HandPresident hLoc_ = new HandPresident();
            for (HandPresident h: _hand.getCardsByLengthSortedByStrength(_reversed, i)) {
                hLoc_.ajouterCartes(h);
            }
            l_.add(hLoc_);
        }
        return l_;
    }

    static HandPresident getPlayable(HandPresident _hand, Playing _playing, TrickPresident _progressingTrick, boolean _reversed, RulesPresident _rules) {
        if (_playing == Playing.FINISH) {
            return new HandPresident();
        }
        if (_playing == Playing.PASS) {
            return new HandPresident();
        }
        if (_playing == Playing.SKIPPED) {
            return new HandPresident();
        }
        ByteTreeMap<HandPresident> filtered_;
        filtered_ = new ByteTreeMap<HandPresident>();
        HandPresident l_ = _progressingTrick.getBestCards();
        byte str_ = l_.premiereCarte().strength(_reversed);
        if (_playing == Playing.HAS_TO_EQUAL) {
            sameStrength(_hand, _reversed, filtered_, str_);
        } else if (_rules.getEqualty() == EqualtyPlaying.FORBIDDEN) {
            strictGreater(_hand, _reversed, filtered_, str_);
        } else {
            greaterEqual(_hand, _reversed, filtered_, str_);
        }
        HandPresident plCards_ = new HandPresident();
        for (byte s: filtered_.getKeys()) {
            HandPresident h_ = filtered_.getVal(s);
            if (h_.total() < l_.total()) {
                continue;
            }
            plCards_.ajouterCartes(h_);
        }
        return plCards_;
    }

    private static void greaterEqual(HandPresident _hand, boolean _reversed, ByteTreeMap<HandPresident> _filtered, byte _str) {
        ByteTreeMap<HandPresident> cards_ = _hand.getCardsByStrength(_reversed);
        for (byte s: cards_.getKeys()) {
            HandPresident h_ = cards_.getVal(s);
            if (s < _str) {
                continue;
            }
            _filtered.put(s, h_);
        }
    }

    private static void strictGreater(HandPresident _hand, boolean _reversed, ByteTreeMap<HandPresident> _filtered, byte _str) {
        ByteTreeMap<HandPresident> cards_ = _hand.getCardsByStrength(_reversed);
        for (byte s: cards_.getKeys()) {
            HandPresident h_ = cards_.getVal(s);
            if (s <= _str) {
                continue;
            }
            _filtered.put(s, h_);
        }
    }

    private static void sameStrength(HandPresident _hand, boolean _reversed, ByteTreeMap<HandPresident> _filtered, byte _str) {
        ByteTreeMap<HandPresident> cards_ = _hand.getCardsByStrength(_reversed);
        for (byte s: cards_.getKeys()) {
            HandPresident h_ = cards_.getVal(s);
            if (s != _str) {
                continue;
            }
            _filtered.put(s, h_);
        }
    }

    static CustList<HandPresident> getNotEmpty(ByteTreeMap<HandPresident> _m) {
        CustList<HandPresident> notEmpty_ = new CustList<HandPresident>();
        for (byte b: _m.getKeys()) {
            HandPresident h_ = _m.getVal(b);
            if (!h_.estVide()) {
                notEmpty_.add(h_);
            }
        }
        return notEmpty_;
    }

}
