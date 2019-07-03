package cards.president;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import code.util.ByteTreeMap;
import code.util.CustList;
import code.util.EqList;
import code.util.TreeMap;

final class GamePresidentProg {

    private TrickPresident progressingTrick;
    private CustList<TrickPresident> tricks;
    private boolean reversed;
    private RulesPresident rules;
    private HandPresident playable;
    private HandPresident fullHand;

    GamePresidentProg(TrickPresident _progressingTrick, CustList<TrickPresident> _tricks, boolean _reversed,
                             RulesPresident _rules, HandPresident _playable, HandPresident _fullHand) {
        progressingTrick = _progressingTrick;
        tricks = _tricks;
        reversed = _reversed;
        rules = _rules;
        playable = _playable;
        fullHand = _fullHand;
    }

    HandPresident progressTrick() {
        if (playable.estVide()) {
            return playable;
        }
        int nbMaxLen_ = rules.getNbStacks() * GamePresidentCommon.NB_SUITS;
        TreeMap<CardPresident,Byte> possibleRep_ = GamePresidentCommon.getNotFullPlayedCardsByStrength(reversed, tricks, progressingTrick,nbMaxLen_);
        if (playable.total() == fullHand.total()) {
            HandPresident all_ = tryPlayWhenAllPossible(playable, progressingTrick, reversed, rules, possibleRep_);
            if (!all_.estVide()) {
                return all_;
            }
        }
        HandPresident hDom_ = tryPlayDomHand(fullHand, playable, progressingTrick, reversed, rules, possibleRep_);
        if (!hDom_.estVide()) {
            return hDom_;
        }
        if (progressingTrick.getBestCards().derniereCarte().strength(reversed) <= GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
            EqList<HandPresident> notEmptyWorst_ = getNotEmptyWorst(playable, progressingTrick, reversed, GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH);
            if (!notEmptyWorst_.isEmpty()) {
                return notEmptyWorst_.first();
            }
        }
        int midHand_ = CustList.FIRST_INDEX;
        for (CardPresident c: fullHand) {
            midHand_ += c.strength(reversed);
        }
        midHand_ /= fullHand.total();
        EqList<HandPresident> notEmptyWorst_ = getNotEmptyWorst(playable, progressingTrick, reversed, midHand_);
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.first();
        }
        notEmptyWorst_ = getHandPresidents(playable, progressingTrick, reversed);
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.first();
        }
        if (canPass(playable,rules,progressingTrick,fullHand,reversed)) {
            return new HandPresident();
        }
        return getDefaultCards(playable, progressingTrick, reversed);
    }

    static HandPresident tryPlayWhenAllPossible(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed, RulesPresident _rules, TreeMap<CardPresident, Byte> _rep) {
        ByteTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        EqList<HandPresident> notEmpty_ = GamePresidentCommon.getNotEmpty(m_);
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        if (notEmpty_.size() == 2) {
            for (byte b: m_.getKeys()) {
                HandPresident h_ = m_.getVal(b);
                if (h_.total() != nb_) {
                    continue;
                }
                if (h_.premiereCarte().strength(_reversed) == CardPresident.getMaxStrength(_reversed)) {
                    return h_;
                }
            }
        }
        if (notEmpty_.size() == CustList.ONE_ELEMENT) {
            if (notEmpty_.first().total() == nb_) {
                return notEmpty_.first();
            }
            //notEmpty_.first().total() > progressingTrick.getNombreDeCartesParJoueur()
            if (GamePresidentCommon.dominantGroup(_reversed, _rules, notEmpty_.first(), nb_, _rep, m_)) {
                HandPresident h_ = new HandPresident();
                for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                    h_.ajouter(notEmpty_.first().carte(i));
                }
                return h_;
            }
        }
        return new HandPresident();
    }
    static HandPresident tryPlayDomHand(HandPresident _fullHand, HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed, RulesPresident _rules, TreeMap<CardPresident, Byte> _rep) {
        ByteTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        if (!GamePresidentCommon.dominantHand(_reversed, _rules, _fullHand, _rep,false).estVide()) {
            HandPresident h_ = lastGroup(_progressingTrick, m_);
            if (!h_.estVide()) {
                return h_;
            }
        }
        return new HandPresident();
    }
    static EqList<HandPresident> getHandPresidents(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed) {
        ByteTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        EqList<HandPresident> notEmptyWorst_;
        notEmptyWorst_ = new EqList<HandPresident>();
        for (byte b: m_.getKeys()) {
            if (b > GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                continue;
            }
            HandPresident h_ = m_.getVal(b);
            if (h_.total() < nb_) {
                continue;
            }
            HandPresident hSub_ = new HandPresident();
            for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                hSub_.ajouter(h_.carte(i));
            }
            notEmptyWorst_.add(hSub_);
        }
        return notEmptyWorst_;
    }

    static EqList<HandPresident> getNotEmptyWorst(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed, int _str) {
        ByteTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        EqList<HandPresident> notEmptyWorst_ = new EqList<HandPresident>();
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        for (byte b: m_.getKeys()) {
            if (b > _str) {
                continue;
            }
            HandPresident h_ = m_.getVal(b);
            if (h_.total() != nb_) {
                continue;
            }
            notEmptyWorst_.add(h_);
        }
        return notEmptyWorst_;
    }
    static HandPresident getDefaultCards(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed) {
        ByteTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        EqList<HandPresident> notEmpty_ = GamePresidentCommon.getNotEmpty(m_);
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        for (byte b: m_.getKeys()) {
            if (b > GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                continue;
            }
            HandPresident h_ = m_.getVal(b);
            if (h_.total() == nb_) {
                return h_;
            }
        }
        HandPresident hSub_ = new HandPresident();
        for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
            hSub_.ajouter(notEmpty_.first().carte(i));
        }
        return hSub_;
    }

    static HandPresident lastGroup(TrickPresident _progressingTrick, ByteTreeMap<HandPresident> _m) {
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        for (byte b: _m.getKeys()) {
            HandPresident h_ = _m.getVal(b);
            if (h_.total() == nb_) {
                return h_;
            }
        }
        for (byte b: _m.getKeys()) {
            if (b >= GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                continue;
            }
            HandPresident h_ = _m.getVal(b);
            if (h_.total() > nb_) {
                HandPresident hSub_ = new HandPresident();
                for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                    hSub_.ajouter(h_.carte(i));
                }
                return hSub_;
            }
        }
        return new HandPresident();
    }

    static boolean canPass(HandPresident _playable, RulesPresident _rules, TrickPresident _progressingTrick, HandPresident _fullHand, boolean _reversed) {
        if (_rules.isHasToPlay() && !_playable.estVide()) {
            HandPresident b_ = _progressingTrick.getBestCards();
            if (b_.total() == _playable.total() && _rules.isLoosingIfFinishByBestCards()) {
                boolean existBestCards_ = false;
                for (CardPresident c: _fullHand) {
                    if (c.strength(_reversed) == CardPresident.getMaxStrength(_reversed)) {
                        existBestCards_ = true;
                    }
                }
                if (existBestCards_) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
