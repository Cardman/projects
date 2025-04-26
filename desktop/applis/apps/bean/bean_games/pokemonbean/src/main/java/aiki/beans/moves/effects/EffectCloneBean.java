package aiki.beans.moves.effects;

import aiki.beans.*;
import aiki.db.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import code.maths.*;
import code.scripts.pages.aiki.MessagesDataEffclone;
import code.scripts.pages.aiki.MessagesPkBean;
import code.util.*;

public class EffectCloneBean extends EffectBean {
    private Rate hpRateClone;
    private CustList<TranslatedKey> movesEndRound;
    private CustList<TranslatedKey> movesBatonPass;
    private CustList<TranslatedKey> movesSending;

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
        movesEndRound = listTrStringsMv(movesEndRound_,getFacade());
        StringList movesBatonPass_ = movesBatonPass(data_);
        movesBatonPass_.removeDuplicates();
        movesBatonPass = listTrStringsMv(movesBatonPass_,getFacade());
        StringList movesSending_ = movesSending(data_);
        movesSending_.removeDuplicates();
        movesSending = listTrStringsMv(movesSending_,getFacade());
    }

    @Override
    public void buildSubEffPre() {
        formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_1,getHpRateClone().toNumberString());
    }

    @Override
    public void buildSubEff() {
        formatMessage(MessagesPkBean.EFF_CLONE, MessagesDataEffclone.M_P_40_EFFECT_2);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,getMovesEndRound(),MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_3);
//        batonPassSending(this);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, getMovesBatonPass(),MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_4);
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, getMovesSending(),MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_5);
        formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_6);
        formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_7);
        formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_8);
        formatMessage(MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_9);
    }

//    private void batonPassSending(EffectCloneBean _sub) {
////        if (NumberUtil.signum(_sub.getMovesBatonPass().size()) * NumberUtil.signum(_sub.getMovesSending().size()) == 0) {
////            return;
////        }
//        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getMovesBatonPass(),MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_4);
//        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this, _sub.getMovesSending(),MessagesPkBean.EFF_CLONE,MessagesDataEffclone.M_P_40_EFFECT_5);
//    }

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
        return tryRedirect(movesEndRound.get(_index));
    }
    public String getTrMovesEndRound(int _index) {
        return movesEndRound.get(_index).getTranslation();
//        String move_ = movesEndRound.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(move_);
    }
    public String clickMoveBatonPass(int _index) {
        return tryRedirect(movesBatonPass.get(_index));
    }
    public String getTrMovesBatonPass(int _index) {
        return movesBatonPass.get(_index).getTranslation();
//        String move_ = movesBatonPass.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(move_);
    }
    public String clickMoveSending(int _index) {
        return tryRedirect(movesSending.get(_index));
    }
    public String getTrMovesSending(int _index) {
        return movesSending.get(_index).getTranslation();
//        String move_ = movesSending.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
//        return translatedMoves_.getVal(move_);
    }

    public Rate getHpRateClone() {
        return hpRateClone;
    }

    public CustList<TranslatedKey> getMovesEndRound() {
        return movesEndRound;
    }

    public CustList<TranslatedKey> getMovesBatonPass() {
        return movesBatonPass;
    }

    public CustList<TranslatedKey> getMovesSending() {
        return movesSending;
    }
}