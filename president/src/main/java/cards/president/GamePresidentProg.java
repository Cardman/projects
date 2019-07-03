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
        ByteTreeMap<HandPresident> m_ = playable.getCardsByStrength(reversed);
        EqList<HandPresident> notEmpty_ = GamePresidentCommon.getNotEmpty(m_);
        int nb_ = progressingTrick.getNombreDeCartesParJoueur();
        if (notEmpty_.size() == 2 && playable.total() == fullHand.total()) {
            for (byte b: m_.getKeys()) {
                HandPresident h_ = m_.getVal(b);
                if (h_.total() != nb_) {
                    continue;
                }
                if (h_.premiereCarte().strength(reversed) == CardPresident.getMaxStrength(reversed)) {
                    return h_;
                }
            }
        }
        TreeMap<CardPresident,Byte> possibleRep_ = GamePresidentCommon.getNotFullPlayedCardsByStrength(reversed, tricks, progressingTrick,nbMaxLen_);
        if (notEmpty_.size() == CustList.ONE_ELEMENT && playable.total() == fullHand.total()) {
            if (notEmpty_.first().total() == nb_) {
                return notEmpty_.first();
            }
            //notEmpty_.first().total() > progressingTrick.getNombreDeCartesParJoueur()
            if (GamePresidentCommon.dominantGroup(reversed, rules, notEmpty_.first(), nb_, possibleRep_, m_)) {
                HandPresident h_ = new HandPresident();
                for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
                    h_.ajouter(notEmpty_.first().carte(i));
                }
                return h_;
            }
        }
        if (!GamePresidentCommon.dominantHand(reversed, rules, fullHand, possibleRep_,false).estVide()) {
            HandPresident h_ = lastGroup(progressingTrick, m_);
            if (!h_.estVide()) {
                return h_;
            }
        }
        if (progressingTrick.getBestCards().derniereCarte().strength(reversed) <= GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH) {
            EqList<HandPresident> notEmptyWorst_ = getNotEmptyWorst(playable, progressingTrick, reversed, GameStrengthCardPresidentComparator.CARD_AVG_STRENGTH);
            if (!notEmptyWorst_.isEmpty()) {
                return notEmptyWorst_.first();
            }
        }
        int midHand_ = CustList.FIRST_INDEX;
        for (CardPresident c: playable) {
            midHand_ += c.strength(reversed);
        }
        midHand_ /= playable.total();
        EqList<HandPresident> notEmptyWorst_ = getNotEmptyWorst(playable, progressingTrick, reversed, midHand_);
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.first();
        }
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
        if (!notEmptyWorst_.isEmpty()) {
            return notEmptyWorst_.first();
        }
        HandPresident b_ = progressingTrick.getBestCards();
        if (b_.total() == playable.total() && rules.isLoosingIfFinishByBestCards()) {
            boolean existBestCards_ = false;
            for (CardPresident c: fullHand) {
                if (c.strength(reversed) == CardPresident.getMaxStrength(reversed)) {
                    existBestCards_ = true;
                }
            }
            if (existBestCards_) {
                return new HandPresident();
            }
        }
        if (canPass(playable,rules,progressingTrick,fullHand,reversed)) {
            return new HandPresident();
        }
        return getDefaultCards(playable, progressingTrick, reversed);
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
