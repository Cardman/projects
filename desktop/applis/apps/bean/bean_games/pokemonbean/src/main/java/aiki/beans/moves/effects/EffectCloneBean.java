package aiki.beans.moves.effects;

import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectBatonPass;
import aiki.fight.moves.effects.EffectClone;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;

public class EffectCloneBean extends EffectBean {
    private Rate hpRateClone;
    private StringList movesEndRound;
    private StringList movesBatonPass;
    private StringList movesSending;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        DataBase data_ = getDataBase();
        EffectClone effect_ = (EffectClone) getEffect();
        hpRateClone = effect_.getHpRateClone();
        StringList movesEndRound_;
        movesEndRound_ = new StringList();
        movesEndRound_.addAllElts(data_.getMovesAnticipation());
        movesEndRound_.addAllElts(data_.getTrappingMoves());
        movesEndRound_.removeDuplicates();
        movesEndRound_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesEndRound = movesEndRound_;
        StringList movesBatonPass_ = movesBatonPass(data_);
        movesBatonPass_.removeDuplicates();
        movesBatonPass_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesBatonPass = movesBatonPass_;
        StringList movesSending_ = movesSending(data_);
        movesSending_.removeDuplicates();
        movesSending_.sortElts(DictionaryComparatorUtil.cmpMoves(data_,getLanguage()));
        movesSending = movesSending_;
    }

    public static StringList movesSending(DataBase _data) {
        StringList movesSending_;
        movesSending_ = new StringList();
        for (String m: _data.getMoves().getKeys()) {
            MoveData move_ = _data.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectTeamWhileSendFoe)) {
                    continue;
                }
                EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) e;
                if (!eff_.getDamageRateAgainstFoe().isEmpty()) {
                    movesSending_.add(m);
                }
            }
        }
        return movesSending_;
    }

    public static StringList movesBatonPass(DataBase _data) {
        StringList movesBatonPass_;
        movesBatonPass_ = new StringList();
        for (String m: _data.getMoves().getKeys()) {
            MoveData move_ = _data.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectBatonPass)) {
                    continue;
                }
                movesBatonPass_.add(m);
            }
        }
        return movesBatonPass_;
    }

    public String clickMoveEndRound(int _index) {
        String move_ = movesEndRound.get(_index);
        return tryRedirectMv(move_);
    }
    public String getTrMovesEndRound(int _index) {
        String move_ = movesEndRound.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickMoveBatonPass(int _index) {
        String move_ = movesBatonPass.get(_index);
        return tryRedirectMv(move_);
    }
    public String getTrMovesBatonPass(int _index) {
        String move_ = movesBatonPass.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickMoveSending(int _index) {
        String move_ = movesSending.get(_index);
        return tryRedirectMv(move_);
    }
    public String getTrMovesSending(int _index) {
        String move_ = movesSending.get(_index);
        DataBase data_ = getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }

    public Rate getHpRateClone() {
        return hpRateClone;
    }

    public StringList getMovesEndRound() {
        return movesEndRound;
    }

    public StringList getMovesBatonPass() {
        return movesBatonPass;
    }

    public StringList getMovesSending() {
        return movesSending;
    }
}