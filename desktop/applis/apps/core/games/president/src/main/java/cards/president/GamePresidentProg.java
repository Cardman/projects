package cards.president;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.enumerations.CardPresident;
import code.util.*;
import code.util.core.IndexConstants;

final class GamePresidentProg {

    private final TrickPresident progressingTrick;
    private final CustList<TrickPresident> tricks;
    private final boolean reversed;
    private final RulesPresident rules;
    private final HandPresident playable;
    private final HandPresident fullHand;

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

    static HandPresident progressTrick(TreeMap<CardPresident, Byte> _possibleRep, HandPresident _playable,
                                       HandPresident _fullHand, TrickPresident _progressingTrick,
                                       boolean _reversed, RulesPresident _rules) {
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
            CustList<HandPresident> notEmptyWorst_ = getNotEmptyWorst(_playable, _progressingTrick, _reversed, GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH);
            if (!notEmptyWorst_.isEmpty()) {
                return notEmptyWorst_.first();
            }
        }
        int midHand_ = IndexConstants.FIRST_INDEX;
        for (CardPresident c: _fullHand) {
            midHand_ += c.strength(_reversed);
        }
        midHand_ /= _fullHand.total();
        CustList<HandPresident> notEmptyWorst_ = getNotEmptyWorst(_playable, _progressingTrick, _reversed, midHand_);
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
        CustList<HandPresident> notEmpty_ = GamePresidentCommon.getNotEmpty(m_);
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
        if (notEmpty_.size() == IndexConstants.ONE_ELEMENT) {
            return oneElt(_reversed, _rules, _rep, m_, notEmpty_, nb_);
        }
        return new HandPresident();
    }

    private static HandPresident oneElt(boolean _reversed, RulesPresident _rules, TreeMap<CardPresident, Byte> _rep, ByteTreeMap<HandPresident> m_, CustList<HandPresident> notEmpty_, int nb_) {
        if (notEmpty_.first().total() == nb_) {
            return notEmpty_.first();
        }
        //notEmpty_.first().total() > progressingTrick.getNombreDeCartesParJoueur()
        if (GamePresidentCommon.dominantGroup(_reversed, _rules, notEmpty_.first(), nb_, _rep, m_)) {
            HandPresident h_ = new HandPresident();
            for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
                h_.ajouter(notEmpty_.first().carte(i));
            }
            return h_;
        }
        return new HandPresident();
    }

    private static HandPresident tryPlayDomHand(HandPresident _fullHand, HandPresident _playable,
                                                TrickPresident _progressingTrick, boolean _reversed, RulesPresident _rules,
                                                TreeMap<CardPresident, Byte> _rep) {
        ByteTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        if (!GamePresidentCommon.dominantHand(_reversed, _rules, _fullHand, _rep).estVide()) {
            return lastGroup(_progressingTrick, m_);
        }
        return new HandPresident();
    }

    private static HandPresident lastGroup(TrickPresident _progressingTrick, ByteTreeMap<HandPresident> _m) {
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        return lastGroup(_m, nb_);
    }

    static HandPresident lastGroup(ByteTreeMap<HandPresident> _m, int _nb) {
        for (EntryCust<Byte, HandPresident> b: _m.entryList()) {
            HandPresident h_ = b.getValue();
            if (h_.total() == _nb) {
                return h_;
            }
        }
        for (EntryCust<Byte, HandPresident> b: _m.entryList()) {
            if (b.getKey() >= GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                continue;
            }
            HandPresident h_ = b.getValue();
            if (h_.total() > _nb) {
                return subHand(_nb, h_);
            }
        }
        return new HandPresident();
    }

    private static HandPresident subHand(int _nb, HandPresident _hd) {
        HandPresident hSub_ = new HandPresident();
        for (int i = IndexConstants.FIRST_INDEX; i < _nb; i++) {
            hSub_.ajouter(_hd.carte(i));
        }
        return hSub_;
    }

    static CustList<HandPresident> getHandPresidents(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed) {
        ByteTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        CustList<HandPresident> notEmptyWorst_;
        notEmptyWorst_ = new CustList<HandPresident>();
        for (EntryCust<Byte, HandPresident> b: m_.entryList()) {
            if (b.getKey() > GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                continue;
            }
            HandPresident h_ = b.getValue();
            if (h_.total() >= nb_) {
                HandPresident hSub_ = subHand(nb_, h_);
                notEmptyWorst_.add(hSub_);
            }
        }
        return notEmptyWorst_;
    }

    static CustList<HandPresident> getNotEmptyWorst(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed, int _str) {
        ByteTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        CustList<HandPresident> notEmptyWorst_ = new CustList<HandPresident>();
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        for (byte b: m_.getKeys()) {
            if (b > _str) {
                continue;
            }
            HandPresident h_ = m_.getVal(b);
            if (h_.total() == nb_) {
                notEmptyWorst_.add(h_);
            }
        }
        return notEmptyWorst_;
    }
    static HandPresident getDefaultCards(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed) {
        ByteTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        CustList<HandPresident> notEmpty_ = GamePresidentCommon.getNotEmpty(m_);
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
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
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
                return existBestCards_;
            }
            return false;
        }
        return true;
    }
}
