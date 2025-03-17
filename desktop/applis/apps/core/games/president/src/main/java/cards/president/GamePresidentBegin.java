package cards.president;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.comparators.HandPresidentRepartition;
import cards.president.enumerations.CardPresident;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IntTreeMap;

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
        IntTreeMap<HandPresident> m_ = playable.getCardsByStrength(reversed);
        CustList<HandPresident> notEmpty_ = GamePresidentCommon.getNotEmpty(m_);
        HandPresidentRepartition possibleRep_ = GamePresidentCommon.getNotFullPlayedCardsByStrength(reversed, tricks, progressingTrick,nbMaxLen_);
        if (notEmpty_.size() != 2) {
            return defBegin(m_, notEmpty_, possibleRep_);
        }
        if (notEmpty_.last().derniereCarte().strength(reversed) == GameStrengthCardPresidentComparator.CARD_MAX_STRENGTH) {
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

    private HandPresident defBegin(IntTreeMap<HandPresident> _m, CustList<HandPresident> _notEmpty, HandPresidentRepartition _possibleRep) {
        if (_notEmpty.size() == 1) {
            return _notEmpty.first();
        }
        HandPresident d_ = GamePresidentCommon.dominantHand(reversed, rules, playable, _possibleRep);
        if (!d_.estVide()) {
            return d_;
        }
        int maxStack_ = rules.getNbStacks() * GamePresidentCommon.NB_SUITS;
        CustList<HandPresident> notEmptyWorst_ = new CustList<HandPresident>();
        for (EntryCust<Integer, HandPresident> b: _m.entryList()) {
            if (b.getKey() > GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                continue;
            }
            HandPresident h_ = b.getValue();
            if (!h_.estVide() && h_.total() > maxStack_ / 2) {
                notEmptyWorst_.add(h_);
            }
        }
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.last();
        }
        return _notEmpty.first();
    }

    static CustList<HandPresident> getLeadingCardsPlayer(boolean _reversed, RulesPresident _rules, IntTreeMap<HandPresident> _m, HandPresidentRepartition _playedCards) {
        HandPresidentRepartition virtualPlayedCards_ = new HandPresidentRepartition(_reversed);
        virtualPlayedCards_.putAllMap(_playedCards);
        for (int  s: _m.getKeys()) {
            HandPresident h_ = _m.getVal(s);
            if (h_.estVide()) {
                continue;
            }
            int  strength_ = h_.premiereCarte().getForce();
            for (EntryCust<CardPresident, Integer> c: virtualPlayedCards_.entryList()) {
                if (c.getKey().getForce() == strength_) {
                    c.setValue(c.getValue()+h_.total());
                }
            }
        }
        return getLeadingCards(_reversed, _rules, _m, virtualPlayedCards_);
    }

    private static CustList<HandPresident> getLeadingCards(boolean _reversed, RulesPresident _rules, IntTreeMap<HandPresident> _m, HandPresidentRepartition _playedCards) {
        CustList<HandPresident> hands_ = new CustList<HandPresident>();
        for (EntryCust<Integer, HandPresident> s: _m.entryList()) {
            HandPresident h_ = s.getValue();
            if (!h_.estVide()) {
                int  strength_ = h_.premiereCarte().strength(_reversed);
                if (strength_ == GameStrengthCardPresidentComparator.CARD_MAX_STRENGTH) {
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

    private static int maxRemGreater(boolean _reversed, RulesPresident _rules, HandPresidentRepartition _playedCards, int  _strength) {
        int rem_ = 0;
        for (EntryCust<CardPresident, Integer> c: _playedCards.entryList()) {
            if (c.getKey().strength(_reversed) >= _strength) {
                int remLoc_ = GamePresidentCommon.NB_SUITS * _rules.getNbStacks() - c.getValue();
                if (remLoc_ > rem_) {
                    rem_ = remLoc_;
                }
            }
        }
        return rem_;
    }
}
