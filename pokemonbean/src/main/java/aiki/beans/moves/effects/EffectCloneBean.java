package aiki.beans.moves.effects;
import code.bean.Accessible;
import code.maths.Rate;
import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.comparators.ComparatorTrStrings;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.Effect;
import aiki.fight.moves.effects.EffectBatonPass;
import aiki.fight.moves.effects.EffectClone;
import aiki.fight.moves.effects.EffectTeamWhileSendFoe;

public class EffectCloneBean extends EffectBean {

    @Accessible
    private Rate hpRateClone;

    @Accessible
    private StringList movesEndRound;

    @Accessible
    private StringList movesBatonPass;

    @Accessible
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

    @Accessible
    private String clickMoveEndRound(Long _index) {
        String move_ = movesEndRound.get(_index.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }

    @Accessible
    private String getTrMovesEndRound(Long _index) {
        String move_ = movesEndRound.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }

    @Accessible
    private String clickMoveBatonPass(Long _index) {
        String move_ = movesBatonPass.get(_index.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }

    @Accessible
    private String getTrMovesBatonPass(Long _index) {
        String move_ = movesBatonPass.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }

    @Accessible
    private String clickMoveSending(Long _index) {
        String move_ = movesSending.get(_index.intValue());
        getForms().put(MOVE, move_);
        return MOVE;
    }

    @Accessible
    private String getTrMovesSending(Long _index) {
        String move_ = movesSending.get(_index.intValue());
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        return translatedMoves_.getVal(move_);
    }
}
