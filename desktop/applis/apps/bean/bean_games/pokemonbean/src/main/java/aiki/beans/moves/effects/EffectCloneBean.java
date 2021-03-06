package aiki.beans.moves.effects;
import aiki.comparators.ComparatorTrStrings;
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
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        EffectClone effect_ = (EffectClone) getEffect();
        hpRateClone = effect_.getHpRateClone();
        StringList movesEndRound_;
        movesEndRound_ = new StringList();
        movesEndRound_.addAllElts(data_.getMovesAnticipation());
        movesEndRound_.addAllElts(data_.getTrappingMoves());
        movesEndRound_.removeDuplicates();
        movesEndRound_.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesEndRound = movesEndRound_;
        StringList movesBatonPass_;
        movesBatonPass_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectBatonPass)) {
                    continue;
                }
                movesBatonPass_.add(m);
            }
        }
        movesBatonPass_.removeDuplicates();
        movesBatonPass_.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesBatonPass = movesBatonPass_;
        StringList movesSending_;
        movesSending_ = new StringList();
        for (String m: data_.getMoves().getKeys()) {
            MoveData move_ = data_.getMove(m);
            for (Effect e: move_.getEffects()) {
                if (!(e instanceof EffectTeamWhileSendFoe)) {
                    continue;
                }
                EffectTeamWhileSendFoe eff_ = (EffectTeamWhileSendFoe) e;
                if (eff_.getDamageRateAgainstFoe().isEmpty()) {
                    continue;
                }
                movesSending_.add(m);
            }
        }
        movesSending_.removeDuplicates();
        movesSending_.sortElts(new ComparatorTrStrings(translatedMoves_));
        movesSending = movesSending_;
    }
    public String clickMoveEndRound(int _index) {
        String move_ = movesEndRound.get(_index);
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
    }
    public String getTrMovesEndRound(int _index) {
        String move_ = movesEndRound.get(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickMoveBatonPass(int _index) {
        String move_ = movesBatonPass.get(_index);
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
    }
    public String getTrMovesBatonPass(int _index) {
        String move_ = movesBatonPass.get(_index);
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
    public String clickMoveSending(int _index) {
        String move_ = movesSending.get(_index);
        getForms().put(CST_MOVE, move_);
        return CST_MOVE;
    }
    public String getTrMovesSending(int _index) {
        String move_ = movesSending.get(_index);
        DataBase data_ = (DataBase) getDataBase();
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