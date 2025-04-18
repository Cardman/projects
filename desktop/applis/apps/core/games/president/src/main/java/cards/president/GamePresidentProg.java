package cards.president;

import cards.president.comparators.GameStrengthCardPresidentComparator;
import cards.president.comparators.HandPresidentRepartition;
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
        HandPresidentRepartition possibleRep_ = GamePresidentCommon.getNotFullPlayedCardsByStrength(reversed, tricks, progressingTrick,nbMaxLen_);
        return progressTrick(possibleRep_, playable, fullHand, progressingTrick, reversed, rules);
    }

    static HandPresident progressTrick(HandPresidentRepartition _possibleRep, HandPresident _playable,
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

    static HandPresident tryPlayWhenAllPossible(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed, RulesPresident _rules, HandPresidentRepartition _rep) {
        IntTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        CustList<HandPresident> notEmpty_ = GamePresidentCommon.getNotEmpty(m_);
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        if (notEmpty_.size() == 2) {
            for (EntryCust<Integer, HandPresident> b: m_.entryList()) {
                HandPresident h_ = b.getValue();
                if (h_.total() != nb_) {
                    continue;
                }
                if (h_.premiereCarte().strength(_reversed) == GameStrengthCardPresidentComparator.CARD_MAX_STRENGTH) {
                    return h_;
                }
            }
        }
        if (notEmpty_.size() == IndexConstants.ONE_ELEMENT) {
            return oneElt(_reversed, _rules, _rep, m_, notEmpty_, nb_);
        }
        return new HandPresident();
    }

    private static HandPresident oneElt(boolean _reversed, RulesPresident _rules, HandPresidentRepartition _rep, IntTreeMap<HandPresident> _m, CustList<HandPresident> _notEmpty, int _nb) {
        if (_notEmpty.first().total() == _nb) {
            return _notEmpty.first();
        }
        //notEmpty_.first().total() > progressingTrick.getNombreDeCartesParJoueur()
        if (GamePresidentCommon.dominantGroup(_reversed, _rules, _notEmpty.first(), _nb, _rep, _m)) {
//            HandPresident h_ = new HandPresident();
//            for (int i = IndexConstants.FIRST_INDEX; i < _nb; i++) {
//                h_.ajouter(_notEmpty.first().carte(i));
//            }
            return subHand(_nb,_notEmpty.first());
//            return h_;
        }
        return new HandPresident();
    }

    private static HandPresident tryPlayDomHand(HandPresident _fullHand, HandPresident _playable,
                                                TrickPresident _progressingTrick, boolean _reversed, RulesPresident _rules,
                                                HandPresidentRepartition _rep) {
        IntTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        if (!GamePresidentCommon.dominantHand(_reversed, _rules, _fullHand, _rep).estVide()) {
            return lastGroup(_progressingTrick, m_);
        }
        return new HandPresident();
    }

    private static HandPresident lastGroup(TrickPresident _progressingTrick, IntTreeMap<HandPresident> _m) {
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        return lastGroup(_m, nb_);
    }

    static HandPresident lastGroup(IntTreeMap<HandPresident> _m, int _nb) {
        for (EntryCust<Integer, HandPresident> b: _m.entryList()) {
            HandPresident h_ = b.getValue();
            if (h_.total() == _nb) {
                return h_;
            }
        }
        for (EntryCust<Integer, HandPresident> b: _m.entryList()) {
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

    static CustList<HandPresident> getHandPresidents(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed) {
        IntTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        CustList<HandPresident> notEmptyWorst_ = new CustList<HandPresident>();
        for (EntryCust<Integer, HandPresident> b: m_.entryList()) {
            if (b.getKey() > GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                continue;
            }
            HandPresident h_ = b.getValue();
            if (h_.total() >= nb_) {
                notEmptyWorst_.add(subHand(nb_, h_));
            }
        }
        return notEmptyWorst_;
    }

    static CustList<HandPresident> getNotEmptyWorst(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed, int _str) {
        IntTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        CustList<HandPresident> notEmptyWorst_ = new CustList<HandPresident>();
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        for (EntryCust<Integer, HandPresident> b: m_.entryList()) {
            if (b.getKey() > _str) {
                continue;
            }
            HandPresident h_ = b.getValue();
            if (h_.total() == nb_) {
                notEmptyWorst_.add(h_);
            }
        }
        return notEmptyWorst_;
    }
    static HandPresident getDefaultCards(HandPresident _playable, TrickPresident _progressingTrick, boolean _reversed) {
        IntTreeMap<HandPresident> m_ = _playable.getCardsByStrength(_reversed);
        CustList<HandPresident> notEmpty_ = GamePresidentCommon.getNotEmpty(m_);
        int nb_ = _progressingTrick.getNombreDeCartesParJoueur();
        for (EntryCust<Integer, HandPresident> b: m_.entryList()) {
            if (b.getKey() > GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
                continue;
            }
            HandPresident h_ = b.getValue();
            if (h_.total() == nb_) {
                return h_;
            }
        }
//        HandPresident hSub_ = new HandPresident();
//        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
//            hSub_.ajouter(notEmpty_.first().carte(i));
//        }
        return subHand(nb_,notEmpty_.first());
    }

    static HandPresident subHand(int _nb, HandPresident _hd) {
        HandPresident hSub_ = new HandPresident();
        for (int i = IndexConstants.FIRST_INDEX; i < _nb; i++) {
            hSub_.ajouter(_hd.carte(i));
        }
        return hSub_;
    }

    static boolean canPass(HandPresident _playable, RulesPresident _rules, TrickPresident _progressingTrick, HandPresident _fullHand, boolean _reversed) {
        if (_rules.isHasToPlay() && !_playable.estVide()) {
            HandPresident b_ = _progressingTrick.getBestCards();
            if (b_.total() == _playable.total() && _rules.isLoosingIfFinishByBestCards()) {
                boolean existBestCards_ = false;
                for (CardPresident c: _fullHand) {
                    if (c.strength(_reversed) == GameStrengthCardPresidentComparator.CARD_MAX_STRENGTH) {
                        existBestCards_ = true;
                        break;
                    }
                }
                return existBestCards_;
            }
            return false;
        }
        return true;
    }
}
