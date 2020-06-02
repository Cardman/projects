package cards.president;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import code.util.ByteTreeMap;
import code.util.CustList;
import code.util.EqList;
import code.util.TreeMap;

final class GamePresidentBegin {

    private TrickPresident progressingTrick;
    private CustList<TrickPresident> tricks;
    private boolean reversed;
    private RulesPresident rules;
    private HandPresident playable;

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
        TreeMap<CardPresident,Byte> possibleRep_ = GamePresidentCommon.getNotFullPlayedCardsByStrength(reversed, tricks, progressingTrick,nbMaxLen_);
        if (notEmpty_.size() == 2) {
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
        }
        if (notEmpty_.size() == 1) {
            return notEmpty_.first();
        }
        HandPresident d_ = GamePresidentCommon.dominantHand(reversed, rules, playable, possibleRep_);
        if (!d_.estVide()) {
            return d_;
        }
        int maxStack_ = rules.getNbStacks() * GamePresidentCommon.NB_SUITS;
        CustList<HandPresident> notEmptyWorst_ = new CustList<HandPresident>();
        for (byte b: m_.getKeys()) {
            if (b > GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                continue;
            }
            HandPresident h_ = m_.getVal(b);
            if (h_.estVide()) {
                continue;
            }
            if (h_.total() <= maxStack_ / 2) {
                continue;
            }
            notEmptyWorst_.add(h_);
        }
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.last();
        }
        return notEmpty_.first();
    }

    static CustList<HandPresident> getLeadingCardsPlayer(boolean _reversed, RulesPresident _rules, ByteTreeMap<HandPresident> _m, TreeMap<CardPresident, Byte> _playedCards) {
        TreeMap<CardPresident,Byte> virtualPlayedCards_ = new TreeMap<CardPresident, Byte>(new GameStrengthCardPresidentComparator(_reversed, true));
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

    private static CustList<HandPresident> getLeadingCards(boolean _reversed, RulesPresident _rules, ByteTreeMap<HandPresident> _m, TreeMap<CardPresident, Byte> _playedCards) {
        CustList<HandPresident> hands_ = new CustList<HandPresident>();
        for (byte s: _m.getKeys()) {
            HandPresident h_ = _m.getVal(s);
            if (h_.estVide()) {
                continue;
            }
            byte strength_ = h_.premiereCarte().strength(_reversed);
            if (strength_ == CardPresident.getMaxStrength(_reversed)) {
                hands_.add(h_);
                continue;
            }
            int rem_ = 0;
            for (CardPresident c: _playedCards.getKeys()) {
                if (c.strength(_reversed) >= strength_) {
                    int remLoc_ = GamePresidentCommon.NB_SUITS * _rules.getNbStacks() - _playedCards.getVal(c);
                    if (remLoc_ > rem_) {
                        rem_ = remLoc_;
                    }
                }
            }
            if (h_.total() > rem_) {
                hands_.add(h_);
            }
        }
        return hands_;
    }
}
