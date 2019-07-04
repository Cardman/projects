package cards.president;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import code.util.*;

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
        return progressTrick(possibleRep_, playable, fullHand, progressingTrick, reversed, rules);
    }

    static HandPresident progressTrick(TreeMap<CardPresident, Byte> _possibleRep, HandPresident _playable, HandPresident _fullHand, TrickPresident _progressingTrick, boolean _reversed, RulesPresident _rules) {
        if (_playable.total() == _fullHand.total()) {
            HandPresident all_ = tryPlayWhenAllPossible(_playable, _progressingTrick, _reversed, _rules, _possibleRep);
            if (!all_.estVide()) {
                return all_;
            }
        }
        HandPresident hDom_ = tryPlayDomHand(_fullHand, _playable, _progressingTrick, _reversed, _rules, _possibleRep);
        if (!hDom_.estVide()) {
            return hDom_;
        }
        if (_progressingTrick.getBestCards().derniereCarte().strength(_reversed) <= GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
            EqList<HandPresident> notEmptyWorst_ = getNotEmptyWorst(_playable, _progressingTrick, _reversed, GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH);
            if (!notEmptyWorst_.isEmpty()) {
                return notEmptyWorst_.first();
            }
        }
        int midHand_ = CustList.FIRST_INDEX;
        for (CardPresident c: _fullHand) {
            midHand_ += c.strength(_reversed);
        }
        midHand_ /= _fullHand.total();
        EqList<HandPresident> notEmptyWorst_ = getNotEmptyWorst(_playable, _progressingTrick, _reversed, midHand_);
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.first();
        }
        notEmptyWorst_ = getHandPresidents(_playable, _progressingTrick, _reversed);
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.first();
        }
        if (canPass(_playable, _rules, _progressingTrick, _fullHand, _reversed)) {
            return new HandPresident();
        }
        return getDefaultCards(_playable, _progressingTrick, _reversed);
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
    private static HandPresident tryPlayDomHand(HandPresident _fullHand, HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed, RulesPresident _rules, TreeMap<CardPresident, Byte> _rep) {
        ByteTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        if (!GamePresidentCommon.dominantHand(_reversed, _rules, _fullHand, _rep).estVide()) {
            int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
            HandPresident h_ = GamePresidentCommon.getNotEmpty(m_).first();
            HandPresident hSub_ = new HandPresident();
            for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                hSub_.ajouter(h_.carte(i));
            }
            return hSub_;
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
