package cards.president;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.EqualtyPlaying;
import cards.president.enumerations.Playing;
import code.util.*;

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

    static HandPresident dominantHand(boolean _reversed, RulesPresident _rules, HandPresident _h, TreeMap<CardPresident, Byte> _playedCards, boolean _begin) {
        HandPresident c_ = new HandPresident(_h);
        ByteTreeMap<HandPresident> gl_ = _h.getCardsByStrength(_reversed);
        CustList<HandPresident> rep_ = getCardsSortedByLengthSortedByStrengthReduce(_reversed, _rules, c_);
        EqList<HandPresident> hWorst_ = new EqList<HandPresident>();
        EqList<HandPresident> hBest_ = new EqList<HandPresident>();
        int maxStack_ = _rules.getNbStacks() * NB_SUITS;
        for (int i = CustList.SECOND_INDEX; i <= maxStack_; i++) {
            HandPresident repLoc_ = new HandPresident(rep_.get(i-1));
            for (HandPresident h: c_.getCardsByStrength(_reversed).values()) {
                if (h.estVide()) {
                    continue;
                }
                if (h.total() == i) {
                    continue;
                }
                if (dominantGroup(_reversed, _rules, h, 1, _playedCards, gl_)) {
                    repLoc_.ajouterCartes(h);
                }
            }
            for (HandPresident h: repLoc_.getCardsByStrength(_reversed).values()) {
                if (h.estVide()) {
                    continue;
                }
                if (dominantGroup(_reversed, _rules, h, i, _playedCards, gl_)) {
                    int nbGroup_ = h.total() / i;
                    int index_ = CustList.FIRST_INDEX;
                    for (int j = CustList.FIRST_INDEX; j < nbGroup_; j++) {
                        HandPresident hBestCards_ = new HandPresident();
                        for (int k = CustList.FIRST_INDEX; k < i; k++) {
                            hBestCards_.ajouter(h.carte(index_));
                            index_++;
                        }
                        hBest_.add(hBestCards_);
                    }
                } else if (h.premiereCarte().strength(_reversed) < GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                    hWorst_.add(h);
                }
            }
            if (hWorst_.size() > hBest_.size() + 1) {
                return new HandPresident();
            }
            int m_ = Math.min(hWorst_.size(), hBest_.size());
            for (int j = CustList.FIRST_INDEX; j < m_; j++) {
                c_.supprimerCartes(hBest_.get(j));
                c_.supprimerCartes(hWorst_.get(j));
            }
            if (c_.estVide()) {
                if (!hWorst_.isEmpty()) {
                    return hWorst_.first();
                }
            }
            hBest_.clear();
            hWorst_.clear();
            rep_ = getCardsSortedByLengthSortedByStrengthReduce(_reversed, _rules, c_);
        }
        if (c_.estVide()) {
            return c_;
        }
        EqList<HandPresident> cardsLists_ = new EqList<HandPresident>();
        for (HandPresident h: c_.getCardsByStrength(_reversed).values()) {
            if (h.estVide()) {
                continue;
            }
            if (h.premiereCarte().strength(_reversed) < GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                cardsLists_.add(h);
            }
        }
        if (cardsLists_.size() > CustList.ONE_ELEMENT) {
            return new HandPresident();
        }
        if (cardsLists_.size() == CustList.ONE_ELEMENT && !_begin) {
            return new HandPresident();
        }
        if (cardsLists_.size() == CustList.ONE_ELEMENT) {
            return cardsLists_.last();
        }
        return c_.getCardsByStrength(_reversed).values().first();
    }
    static TreeMap<CardPresident, Byte> getNotFullPlayedCardsByStrength(boolean _reversed, CustList<TrickPresident> _tricks, TrickPresident _progressingTrick, int _max) {
        TreeMap<CardPresident,Byte> tree_;
        tree_ = new TreeMap<CardPresident,Byte>(new GameStrengthCardPresidentComparator(_reversed, true));
        for (EntryCust<CardPresident, Byte> e:getPlayedCardsByStrength(_reversed,_tricks,_progressingTrick).entryList()) {
            Byte count_ = e.getValue();
            if (count_ == _max) {
                continue;
            }
            tree_.addEntry(e.getKey(), count_);
        }
        return tree_;
    }
    static TreeMap<CardPresident, Byte> getPlayedCardsByStrength(boolean _reversed, CustList<TrickPresident> _tricks, TrickPresident _progressingTrick) {
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

    static TreeMap<CardPresident, Byte> getPlayedCardsByStrength(boolean _reversed, HandPresident _playedCards) {
        TreeMap<CardPresident,Byte> tree_;
        tree_ = new TreeMap<CardPresident,Byte>(new GameStrengthCardPresidentComparator(_reversed, true));
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
                tree_.put(c, CustList.ONE_ELEMENT);
            }
        }
        for (CardPresident c: HandPresident.pileBase()) {
            boolean eqStrPres_ = false;
            for (CardPresident s: tree_.getKeys()) {
                byte str_ = s.strength(_reversed);
                if (str_ == c.strength(_reversed)) {
                    eqStrPres_ = true;
                    break;
                }
            }
            if (!eqStrPres_) {
                tree_.put(c, CustList.SIZE_EMPTY);
            }
        }
        return tree_;
    }

    static boolean dominantGroup(boolean _reversed, RulesPresident _rules, HandPresident _h, int _nb, TreeMap<CardPresident, Byte> _playedCards, ByteTreeMap<HandPresident> _rep) {
        int maxStack_ = _rules.getNbStacks() * NB_SUITS;
        if (_h.premiereCarte().strength(_reversed) == CardPresident.getMaxStrength(_reversed)) {
            return true;
        }
        int str_ = _h.premiereCarte().strength(_reversed);
        TreeMap<CardPresident, Byte> tree_;
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

    private static TreeMap<CardPresident, Byte> getTreeExcluding(boolean _reversed, TreeMap<CardPresident, Byte> _tree, int _str) {
        TreeMap<CardPresident,Byte> tree_;
        tree_ = new TreeMap<CardPresident,Byte>(new GameStrengthCardPresidentComparator(_reversed, true));
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
        for (int i = CustList.SECOND_INDEX; i <= nbMaxLen_; i++) {
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
            ByteTreeMap<HandPresident> cards_ = _hand.getCardsByStrength(_reversed);
            for (byte s: cards_.getKeys()) {
                HandPresident h_ = cards_.getVal(s);
                if (s != str_) {
                    continue;
                }
                filtered_.put(s, h_);
            }
        } else if (_rules.getEqualty() == EqualtyPlaying.FORBIDDEN) {
            ByteTreeMap<HandPresident> cards_ = _hand.getCardsByStrength(_reversed);
            for (byte s: cards_.getKeys()) {
                HandPresident h_ = cards_.getVal(s);
                if (s <= str_) {
                    continue;
                }
                filtered_.put(s, h_);
            }
        } else {
            ByteTreeMap<HandPresident> cards_ = _hand.getCardsByStrength(_reversed);
            for (byte s: cards_.getKeys()) {
                HandPresident h_ = cards_.getVal(s);
                if (s < str_) {
                    continue;
                }
                filtered_.put(s, h_);
            }
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

    static EqList<HandPresident> getNotEmpty(ByteTreeMap<HandPresident> _m) {
        EqList<HandPresident> notEmpty_ = new EqList<HandPresident>();
        for (byte b: _m.getKeys()) {
            HandPresident h_ = _m.getVal(b);
            if (!h_.estVide()) {
                notEmpty_.add(h_);
            }
        }
        return notEmpty_;
    }

}
