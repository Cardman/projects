package cards.president;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.comparators.HandPresidentRepartition;
import cards.president.enumerations.CardPresident;
import code.util.ByteTreeMap;
import code.util.CustList;

final class GamePresidentBegin {

    private final TrickPresident progressingTrick;
    private final CustList<TrickPresident> tricks;
    private final boolean reversed;
    private final RulesPresident rules;
    private final HandPresident playable;

    GamePresidentBegin(TrickPresident _progressingTrick, CustList<TrickPresident> _tricks,
                              boolean _reversed, RulesPresident _rules, HandPresident _playable) {
        progressingTrick = _progressingTrick;
        tricks = _tricks;
        reversed = _reversed;
        rules = _rules;
        playable = _playable;
    }

    HandPresident beginTrick() {
        int nbMaxLen_ = rules.getNbStacks() * GamePresidentCommon.NB_SUITS;
        ByteTreeMap<HandPresident> m_ = playable.getCardsByStrength(reversed);
        CustList<HandPresident> notEmpty_ = GamePresidentCommon.getNotEmpty(m_);
        HandPresidentRepartition possibleRep_ = GamePresidentCommon.getNotFullPlayedCardsByStrength(reversed, tricks, progressingTrick,nbMaxLen_);
        if (notEmpty_.size() != 2) {
            return defBegin(m_, notEmpty_, possibleRep_);
        }
        if (notEmpty_.last().derniereCarte().strength(reversed) == CardPresident.getMaxStrength(reversed)) {
            return notEmpty_.last();
        }
        CustList<HandPresident> l_ = getLeadingCardsPlayer(reversed, rules, m_, possibleRep_);
        if (!l_.isEmpty()) {
            if (rules.isPossibleReversing()) {
                int max_ = GamePresidentCommon.NB_SUITS * rules.getNbStacks();
                CustList<HandPresident> f_ = new CustList<HandPresident>();
                f_.add(l_.first());
                f_.add(l_.last());
                for (HandPresident h: f_) {
                    if (h.total() < max_) {
                        return h;
                    }
                }
            } else {
                return l_.first();
            }
        }
        return defBegin(m_, notEmpty_, possibleRep_);
    }

    private HandPresident defBegin(ByteTreeMap<HandPresident> _m, CustList<HandPresident> _notEmpty, HandPresidentRepartition _possibleRep) {
        if (_notEmpty.size() == 1) {
            return _notEmpty.first();
        }
        HandPresident d_ = GamePresidentCommon.dominantHand(reversed, rules, playable, _possibleRep);
        if (!d_.estVide()) {
            return d_;
        }
        int maxStack_ = rules.getNbStacks() * GamePresidentCommon.NB_SUITS;
        CustList<HandPresident> notEmptyWorst_ = new CustList<HandPresident>();
        for (byte b: _m.getKeys()) {
            if (b > GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                continue;
            }
            HandPresident h_ = _m.getVal(b);
            if (!h_.estVide() && h_.total() > maxStack_ / 2) {
                notEmptyWorst_.add(h_);
            }
        }
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.last();
        }
        return _notEmpty.first();
    }

    static CustList<HandPresident> getLeadingCardsPlayer(boolean _reversed, RulesPresident _rules, ByteTreeMap<HandPresident> _m, HandPresidentRepartition _playedCards) {
        HandPresidentRepartition virtualPlayedCards_ = new HandPresidentRepartition(_reversed);
        virtualPlayedCards_.putAllMap(_playedCards);
        for (byte s: _m.getKeys()) {
            HandPresident h_ = _m.getVal(s);
            if (h_.estVide()) {
                continue;
            }
            byte strength_ = h_.premiereCarte().strength(_reversed);
            for (CardPresident c: virtualPlayedCards_.getKeys()) {
                if (c.strength(_reversed) == strength_) {
                    byte c_ = virtualPlayedCards_.getVal(c);
                    c_ += h_.total();
                    virtualPlayedCards_.put(c, c_);
                }
            }
        }
        return getLeadingCards(_reversed, _rules, _m, virtualPlayedCards_);
    }

    private static CustList<HandPresident> getLeadingCards(boolean _reversed, RulesPresident _rules, ByteTreeMap<HandPresident> _m, HandPresidentRepartition _playedCards) {
        CustList<HandPresident> hands_ = new CustList<HandPresident>();
        for (byte s: _m.getKeys()) {
            HandPresident h_ = _m.getVal(s);
            if (!h_.estVide()) {
                byte strength_ = h_.premiereCarte().strength(_reversed);
                if (strength_ == CardPresident.getMaxStrength(_reversed)) {
                    hands_.add(h_);
                    continue;
                }
                int rem_ = maxRemGreater(_reversed, _rules, _playedCards, strength_);
                if (h_.total() > rem_) {
                    hands_.add(h_);
                }
            }
        }
        return hands_;
    }

    private static int maxRemGreater(boolean _reversed, RulesPresident _rules, HandPresidentRepartition _playedCards, byte _strength) {
        int rem_ = 0;
        for (CardPresident c: _playedCards.getKeys()) {
            if (c.strength(_reversed) >= _strength) {
                int remLoc_ = GamePresidentCommon.NB_SUITS * _rules.getNbStacks() - _playedCards.getVal(c);
                if (remLoc_ > rem_) {
                    rem_ = remLoc_;
                }
            }
        }
        return rem_;
    }
}
