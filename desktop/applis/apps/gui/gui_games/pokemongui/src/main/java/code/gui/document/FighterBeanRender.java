package code.gui.document;

import aiki.beans.*;
import aiki.beans.facade.fight.*;
import aiki.beans.fight.*;
import aiki.facade.*;
import aiki.game.*;
import aiki.game.fight.*;
import aiki.game.fight.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.confs.*;
import code.util.*;
import code.util.core.*;

public final class FighterBeanRender extends AbsFightBeanRender {
    private final FighterBean bean = new FighterBean();
    @Override
    public AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade, StringMapObject _form) {
        StringMapObject sm_ = new StringMapObject();
        sm_.putAllMap(_form);
        init(bean,_facade, sm_);
        AbsPanel form_ = _api.getCompoFactory().newPageBox();
        form_.setBackground(GuiConstants.WHITE);
        form_.setTitledBorder(StringUtil.simpleStringsFormat(file(_api).getVal(MessagesFightFighter.M_P_91_TITLE)));
        AbsPanel line_ = _api.getCompoFactory().newPageBox();
        formatMessageAnc(_api,line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_FIGHT).addMouseListener(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,getRenders(), _form));
        nextPart();
        formatMessageAnc(_api,line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_TEAM).addMouseListener(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML,getRenders(), _form));
        nextPart();
        formatMessageAnc(_api,line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_REFRESH).addMouseListener(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHTER_HTML,getRenders(), _form));
        nextPart();
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_NAME,bean.getName());
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_GENDER,bean.getGender());
        displayBoolTrue(_api,line_,MessagesPkBean.FIGHTER,bean.getChanged(),MessagesFightFighter.M_P_91_CHANGED);
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_CURRENT_NAME,bean.getCurrentName());
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_CURRENT_GENDER,bean.getCurrentGender());
        displayNotEmpty(_api,line_, MessagesPkBean.FIGHTER,bean.getUsedBallCatching(),MessagesFightFighter.M_P_91_USED_BALL_CATCHING);
        displayBoolFull(_api,line_, MessagesPkBean.FIGHTER,bean.getBelongingToPlayer(),MessagesFightFighter.M_P_91_BELONG_PLAYER,MessagesFightFighter.M_P_91_BELONG_PLAYER_NO);
        place(_api, line_, bean.getGroundPlaceSubst(), MessagesFightFighter.M_P_91_GROUND_SUBSTIT_BACK, MessagesFightFighter.M_P_91_GROUND_SUBSTIT);
        place(_api, line_, bean.getGroundPlace(), MessagesFightFighter.M_P_91_GROUND_PLACE_BACK, MessagesFightFighter.M_P_91_GROUND_PLACE);
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_NICKNAME,bean.getNickname());
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_HAPPINESS,Long.toString(bean.getHappiness()));
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_LEVEL,Long.toString(bean.getLevel()));
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_WON_EXP_LAST_LEVEL,bean.getWonExpSinceLastLevel().toNumberString());
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_NECESSARY_PTS,bean.getNecessaryPointsNextLevel().toNumberString());
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_WEIGHT,bean.getWeight().toNumberString(),bean.getWeightStr());
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_HEIGHT,bean.getHeight().toNumberString(),bean.getHeightStr());
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_REMAIN_HP,bean.getRemainingHp().toNumberString(),bean.getRemainingHpStr(),bean.getRemainingHpStrPerCent(),bean.getMaxHp().toNumberString());
        formatMessage(_api, line_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_CLONE,bean.getClone().toNumberString(),bean.getCloneStr());
        displayNotEmpty(_api,line_, MessagesPkBean.FIGHTER,bean.getCurrentAbility(),MessagesFightFighter.M_P_91_CURRENT_ABILITY);
        displayEmpty(_api,line_, MessagesPkBean.FIGHTER,bean.getCurrentAbility(),MessagesFightFighter.M_P_91_CURRENT_ABILITY_NO);
        displayBoolFull(_api,line_, MessagesPkBean.FIGHTER,bean.getActed(),MessagesFightFighter.M_P_91_ACTED,MessagesFightFighter.M_P_91_ACTED_NO);
        displayNotEmpty(_api,line_, MessagesPkBean.FIGHTER,bean.getLastUsedItem(),MessagesFightFighter.M_P_91_LAST_USED_ITEM);
        displayEmpty(_api,line_, MessagesPkBean.FIGHTER,bean.getLastUsedItem(),MessagesFightFighter.M_P_91_LAST_USED_ITEM_NO);
        displayNotEmpty(_api,line_, MessagesPkBean.FIGHTER,bean.getItem(),MessagesFightFighter.M_P_91_ITEM);
        displayEmpty(_api,line_, MessagesPkBean.FIGHTER,bean.getItem(),MessagesFightFighter.M_P_91_ITEM_NO);
        displayNotEmpty(_api,line_, MessagesPkBean.FIGHTER,bean.getExpItem(),MessagesFightFighter.M_P_91_EXP_ITEM);
        displayEmpty(_api,line_, MessagesPkBean.FIGHTER,bean.getExpItem(),MessagesFightFighter.M_P_91_EXP_ITEM_NO);
        displayBoolTrue(_api,line_, MessagesPkBean.FIGHTER,bean.getUsingItem(),MessagesFightFighter.M_P_91_USING_ITEM);
        feedParents(form_,line_);
        nextPart();
        displayStringList(_api,form_, MessagesPkBean.FIGHTER,bean.getTypes(),MessagesFightFighter.M_P_91_TYPES);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getMoves(),MessagesFightFighter.M_P_91_MOVES);
        AbsPanel formMoves_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formMoves_, MessagesPkBean.FIGHTER, bean.getMoves(), MessagesFightFighter.M_P_91_MOVES_KEY, MessagesFightFighter.M_P_91_MOVES_VALUE_ONE, MessagesFightFighter.M_P_91_MOVES_VALUE_TWO);
        new BeanDisplayMap<String, UsesOfMove>(new BeanDisplayString(),new BeanDisplayUsesOfMove()).display(this,_api,formMoves_,bean.getMoves(),3);
        feedParents(form_,formMoves_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getCurrentMoves(),MessagesFightFighter.M_P_91_CURRENT_MOVES);
        AbsPanel formCurrentMoves_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formCurrentMoves_, MessagesPkBean.FIGHTER, bean.getCurrentMoves(), MessagesFightFighter.M_P_91_CURRENT_MOVES_KEY, MessagesFightFighter.M_P_91_CURRENT_MOVES_VALUE_ONE, MessagesFightFighter.M_P_91_CURRENT_MOVES_VALUE_TWO);
        new BeanDisplayMap<String, UsesOfMove>(new BeanDisplayString(),new BeanDisplayUsesOfMove()).display(this,_api,formCurrentMoves_,bean.getCurrentMoves(),3);
        feedParents(form_,formCurrentMoves_);
        nextPart();
        formatMessage(_api, form_, MessagesPkBean.FIGHTER, MessagesFightFighter.M_P_91_NB_ROUNDS, bean.getNbRounds().toNumberString());
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getCopiedMoves(),MessagesFightFighter.M_P_91_COPIED_MOVES);
        AbsPanel formCopiedMoves_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formCopiedMoves_, MessagesPkBean.FIGHTER, bean.getCopiedMoves(), MessagesFightFighter.M_P_91_COPIED_MOVES_OLD, MessagesFightFighter.M_P_91_COPIED_MOVES_NEW, MessagesFightFighter.M_P_91_COPIED_MOVES_PP);
        new BeanDisplayMap<String, CopiedMove>(new BeanDisplayString(),new BeanDisplayCopiedMove()).display(this,_api,formCopiedMoves_,bean.getCopiedMoves(),3);
        feedParents(form_,formCopiedMoves_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getStatistics(),MessagesFightFighter.M_P_91_STATISTICS);
        AbsPanel formStatistics_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formStatistics_, MessagesPkBean.FIGHTER, bean.getStatistics(), MessagesFightFighter.M_P_91_STATISTICS_KEY, MessagesFightFighter.M_P_91_STATISTICS_BASE, MessagesFightFighter.M_P_91_STATISTICS_EV, MessagesFightFighter.M_P_91_STATISTICS_IV, MessagesFightFighter.M_P_91_STATISTICS_BOOST);
        for (StatisticInfo s: bean.getStatistics()) {
            formatMessageDir(_api,formStatistics_,_api.getCompoFactory().newGridCts(),s.getDisplayStatistic());
            cell(_api, formStatistics_, s.base(), s.getStatisBase().toNumberString(), _api.getCompoFactory().newGridCts());
            cell(_api, formStatistics_, s.base(), Long.toString(s.getEv()), _api.getCompoFactory().newGridCts());
            cell(_api, formStatistics_, s.base(), Long.toString(s.getIv()), _api.getCompoFactory().newGridCts());
            cell(_api, formStatistics_, s.boost(), Long.toString(s.getStatisBoost()), AbsBeanRender.remainder(_api));
        }
        feedParents(form_,formStatistics_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getDamageRateByType(),MessagesFightFighter.M_P_91_DAMAGE_POWER_TYPES);
        AbsPanel formDamageRateByType_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formDamageRateByType_, MessagesPkBean.FIGHTER, bean.getDamageRateByType(), MessagesFightFighter.M_P_91_DAMAGE_POWER_TYPES_KEY, MessagesFightFighter.M_P_91_DAMAGE_POWER_TYPES_VALUE_ONE, MessagesFightFighter.M_P_91_DAMAGE_POWER_TYPES_VALUE_TWO);
        new BeanDisplayMap<String, MultPowerMoves>(new BeanDisplayString(),new BeanDisplayMultPowerMoves()).display(this,_api,formDamageRateByType_,bean.getDamageRateByType(),3);
        feedParents(form_,formDamageRateByType_);
        nextPart();
        displayStringList(_api,form_, MessagesPkBean.FIGHTER,bean.getProtectedAgainstMoveTypes(),MessagesFightFighter.M_P_91_TYPES_PROTECTED);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getDamageSufferedCateg(),MessagesFightFighter.M_P_91_SUFFERING_DAMAGE);
        AbsPanel formDamageSufferedCateg_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formDamageSufferedCateg_, MessagesPkBean.FIGHTER, bean.getDamageSufferedCateg(), MessagesFightFighter.M_P_91_SUFFERING_DAMAGE_KEY, MessagesFightFighter.M_P_91_SUFFERING_DAMAGE_VALUE_ROUND, MessagesFightFighter.M_P_91_SUFFERING_DAMAGE_VALUE_USING);
        new BeanDisplayMap<String, SufferedDamageCategory>(new BeanDisplayString(),new BeanDisplaySufferedDamageCategory()).display(this,_api,formDamageSufferedCateg_,bean.getDamageSufferedCateg(),3);
        feedParents(form_,formDamageSufferedCateg_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getEnabledMoves(),MessagesFightFighter.M_P_91_ENBALED_MOVES);
        AbsPanel formEnabledMoves_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formEnabledMoves_, MessagesPkBean.FIGHTER,bean.getEnabledMoves(), MessagesFightFighter.M_P_91_ENBALED_MOVES_KEY, MessagesFightFighter.M_P_91_ENBALED_MOVES_ENABLED, MessagesFightFighter.M_P_91_ENBALED_MOVES_NB_ROUND);
        new BeanDisplayMap<String,ActivityOfMoveStill>(new BeanDisplayString(),new BeanDisplayActivityOfMoveStill(file(_api).getVal(MessagesFightFighter.M_P_91_ENBALED_MOVES_ENABLED_Y),file(_api).getVal(MessagesFightFighter.M_P_91_ENBALED_MOVES_ENABLED_N),file(_api).getVal(MessagesFightFighter.M_P_91_ENBALED_MOVES_NO))).display(this,_api,formEnabledMoves_,bean.getEnabledMoves(),3);
        feedParents(form_,formEnabledMoves_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getEnabledMovesForAlly(),MessagesFightFighter.M_P_91_ENBALED_MOVES_ALLY);
        AbsPanel formEnabledMovesForAlly_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formEnabledMovesForAlly_, MessagesPkBean.FIGHTER,bean.getEnabledMovesForAlly(), MessagesFightFighter.M_P_91_ENBALED_MOVES_ALLY_KEY, MessagesFightFighter.M_P_91_ENBALED_MOVES_ALLY_ENABLED);
        new BeanDisplayMap<String,Integer>(new BeanDisplayString(),new BeanDisplayBool(file(_api).getVal(MessagesFightFighter.M_P_91_ENBALED_MOVES_ALLY_ENABLED_Y),file(_api).getVal(MessagesFightFighter.M_P_91_ENBALED_MOVES_ALLY_ENABLED_N))).display(this,_api,formEnabledMovesForAlly_,bean.getEnabledMovesForAlly(),2);
        feedParents(form_,formEnabledMovesForAlly_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getNbUsesMoves(),MessagesFightFighter.M_P_91_NB_USES);
        AbsPanel formNbUsesMoves_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formNbUsesMoves_, MessagesPkBean.FIGHTER,bean.getNbUsesMoves(), MessagesFightFighter.M_P_91_NB_USES_KEY, MessagesFightFighter.M_P_91_NB_USES_VALUE);
        new BeanDisplayMap<String,Long>(new BeanDisplayString(),new BeanDisplayLong()).display(this,_api,formNbUsesMoves_,bean.getNbUsesMoves(),2);
        feedParents(form_,formNbUsesMoves_);
        nextPart();
        AbsPanel lineSec_ = _api.getCompoFactory().newPageBox();
        formatMessage(_api,lineSec_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_NB_SUCCESS_MOVES,bean.getNbRepeatingSuccessfulMoves().toNumberString());
        displayBoolFull(_api,lineSec_, MessagesPkBean.FIGHTER,bean.getSuccessfulMove(),MessagesFightFighter.M_P_91_SUCCESSFUL_MOVE,MessagesFightFighter.M_P_91_SUCCESSFUL_MOVE_NO);
        displayNotEmpty(_api,lineSec_, MessagesPkBean.FIGHTER,bean.getLastSuccessfulMove(),MessagesFightFighter.M_P_91_LAST_SUCCESSFUL_MOVE);
        displayNotEmpty(_api,lineSec_, MessagesPkBean.FIGHTER,bean.getLastUsedMove(),MessagesFightFighter.M_P_91_LAST_USED_MOVE);
        displayNotEmpty(_api,lineSec_, MessagesPkBean.FIGHTER,bean.getUsedMoveLastRound(),MessagesFightFighter.M_P_91_USED_MOVE_LAST_ROUND);
        formatMessage(_api,lineSec_, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_NB_ROUND_PREPA,Long.toString(bean.getNbPrepaRound()));
        feedParents(form_,lineSec_);
        nextPart();
        displayStringList(_api,form_, MessagesPkBean.FIGHTER,bean.getAlreadyInvokedMovesRound(),MessagesFightFighter.M_P_91_INVOKED_MOVES);
        nextPart();
        AbsPanel lineThird_ = _api.getCompoFactory().newPageBox();
        displayNotEmpty(_api,lineThird_, MessagesPkBean.FIGHTER,bean.getLastSufferedMove(),MessagesFightFighter.M_P_91_LAST_SUFFERED_MOVE);
        feedParents(form_,lineThird_);
        nextPart();
        displayStringList(_api,form_, MessagesPkBean.FIGHTER,bean.getLastSufferedMoveTypes(),MessagesFightFighter.M_P_91_LAST_SUFFERED_MOVE_TYPES);
        nextPart();
        AbsPanel lineFourth_ = _api.getCompoFactory().newPageBox();
        displayBoolFull(_api,lineFourth_, MessagesPkBean.FIGHTER,bean.getDisappeared(),MessagesFightFighter.M_P_91_DISAPPEARED,MessagesFightFighter.M_P_91_DISAPPEARED_NO);
        displayBoolTrue(_api,lineFourth_, MessagesPkBean.FIGHTER,bean.getNeedingToRecharge(),MessagesFightFighter.M_P_91_NEEDING_RECHARGE);
        feedParents(form_,lineFourth_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getStatus(),MessagesFightFighter.M_P_91_STATUS);
        AbsPanel formStatus_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formStatus_, MessagesPkBean.FIGHTER,bean.getStatus(), MessagesFightFighter.M_P_91_STATUS_KEY, MessagesFightFighter.M_P_91_STATUS_VALUE);
        new BeanDisplayMap<String,Long>(new BeanDisplayString(),new BeanDisplayLong()).display(this,_api,formStatus_,bean.getStatus(),2);
        feedParents(form_,formStatus_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getStatusRelat(),MessagesFightFighter.M_P_91_STATUS_REL);
        AbsPanel formStatusRelat_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formStatusRelat_, MessagesPkBean.FIGHTER,bean.getStatusRelat(), MessagesFightFighter.M_P_91_STATUS_REL_KEY_ONE, MessagesFightFighter.M_P_91_STATUS_REL_KEY_TWO, MessagesFightFighter.M_P_91_STATUS_REL_KEY_THREE, MessagesFightFighter.M_P_91_STATUS_REL_ENABLED);
        new BeanDisplayMap<MoveTeamPositionFighterName,Long>(new BeanDisplayMoveTeamPositionFighterName(file(_api).getVal(MessagesFightFighter.M_P_91_STATUS_REL_FOE),file(_api).getVal(MessagesFightFighter.M_P_91_STATUS_REL_PLAYER)),new BeanDisplayBoolLong(file(_api).getVal(MessagesFightFighter.M_P_91_STATUS_REL_ENABLED_Y),file(_api).getVal(MessagesFightFighter.M_P_91_STATUS_REL_ENABLED_N))).display(this,_api,formStatusRelat_,bean.getStatusRelat(),4);
        feedParents(form_,formStatusRelat_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getPrivateMoves(),MessagesFightFighter.M_P_91_PRIVATE_MOVES);
        AbsPanel formPrivateMoves_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formPrivateMoves_, MessagesPkBean.FIGHTER,bean.getPrivateMoves(), MessagesFightFighter.M_P_91_PRIVATE_MOVES_KEY_ONE, MessagesFightFighter.M_P_91_PRIVATE_MOVES_KEY_TWO, MessagesFightFighter.M_P_91_PRIVATE_MOVES_KEY_THREE, MessagesFightFighter.M_P_91_PRIVATE_MOVES_MV);
        new BeanDisplayMap<MoveTeamPositionFighterName,String>(new BeanDisplayMoveTeamPositionFighterName(file(_api).getVal(MessagesFightFighter.M_P_91_PRIVATE_MOVES_FOE),file(_api).getVal(MessagesFightFighter.M_P_91_PRIVATE_MOVES_PLAYER)),new BeanDisplayString()).display(this,_api,formPrivateMoves_,bean.getPrivateMoves(),4);
        feedParents(form_,formPrivateMoves_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getTrappingMoves(),MessagesFightFighter.M_P_91_TRAPPING_MOVES);
        AbsPanel formTrappingMoves_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formTrappingMoves_, MessagesPkBean.FIGHTER,bean.getTrappingMoves(), MessagesFightFighter.M_P_91_TRAPPING_MOVES_KEY_ONE, MessagesFightFighter.M_P_91_TRAPPING_MOVES_KEY_TWO, MessagesFightFighter.M_P_91_TRAPPING_MOVES_KEY_THREE, MessagesFightFighter.M_P_91_TRAPPING_MOVES_ENABLED, MessagesFightFighter.M_P_91_TRAPPING_MOVES_NB_ROUND);
        new BeanDisplayMap<MoveTeamPositionFighterName,ActivityOfMoveStill>(new BeanDisplayMoveTeamPositionFighterName(file(_api).getVal(MessagesFightFighter.M_P_91_TRAPPING_MOVES_FOE),file(_api).getVal(MessagesFightFighter.M_P_91_TRAPPING_MOVES_PLAYER)),new BeanDisplayActivityOfMoveStill(file(_api).getVal(MessagesFightFighter.M_P_91_TRAPPING_MOVES_ENABLED_Y),file(_api).getVal(MessagesFightFighter.M_P_91_TRAPPING_MOVES_ENABLED_N),file(_api).getVal(MessagesFightFighter.M_P_91_TRAPPING_MOVES_NO))).display(this,_api,formTrappingMoves_,bean.getTrappingMoves(),5);
        feedParents(form_,formTrappingMoves_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getTrackingMoves(),MessagesFightFighter.M_P_91_TRACKING_MOVES);
        AbsPanel formTrackingMoves_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formTrackingMoves_, MessagesPkBean.FIGHTER,bean.getTrackingMoves(), MessagesFightFighter.M_P_91_TRACKING_MOVES_KEY_ONE, MessagesFightFighter.M_P_91_TRACKING_MOVES_KEY_TWO, MessagesFightFighter.M_P_91_TRACKING_MOVES_KEY_THREE, MessagesFightFighter.M_P_91_TRACKING_MOVES_V, MessagesFightFighter.M_P_91_TRACKING_MOVES_ENABLED, MessagesFightFighter.M_P_91_TRACKING_MOVES_NB_ROUND);
        new BeanDisplayMap<MoveTeamPositionFighterName, AffectedMove>(new BeanDisplayMoveTeamPositionFighterName(file(_api).getVal(MessagesFightFighter.M_P_91_TRACKING_MOVES_FOE),file(_api).getVal(MessagesFightFighter.M_P_91_TRACKING_MOVES_PLAYER)),new BeanDisplayAffectedMove(file(_api).getVal(MessagesFightFighter.M_P_91_TRACKING_MOVES_ENABLED_Y),file(_api).getVal(MessagesFightFighter.M_P_91_TRACKING_MOVES_ENABLED_N),file(_api).getVal(MessagesFightFighter.M_P_91_TRACKING_MOVES_NO))).display(this,_api,formTrackingMoves_,bean.getTrackingMoves(),6);
        feedParents(form_,formTrackingMoves_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHTER,bean.getIncrUserAccuracy(),MessagesFightFighter.M_P_91_INC_ACCURACY);
        AbsPanel formIncrUserAccuracy_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formIncrUserAccuracy_, MessagesPkBean.FIGHTER,bean.getIncrUserAccuracy(), MessagesFightFighter.M_P_91_INC_ACCURACY_KEY_ONE, MessagesFightFighter.M_P_91_INC_ACCURACY_KEY_TWO, MessagesFightFighter.M_P_91_INC_ACCURACY_KEY_THREE, MessagesFightFighter.M_P_91_INC_ACCURACY_ENABLED);
        new BeanDisplayMap<MoveTeamPositionFighterName, Integer>(new BeanDisplayMoveTeamPositionFighterName(file(_api).getVal(MessagesFightFighter.M_P_91_INC_ACCURACY_FOE),file(_api).getVal(MessagesFightFighter.M_P_91_INC_ACCURACY_PLAYER)),new BeanDisplayBool(file(_api).getVal(MessagesFightFighter.M_P_91_INC_ACCURACY_ENABLED_Y),file(_api).getVal(MessagesFightFighter.M_P_91_INC_ACCURACY_ENABLED_N))).display(this,_api,formIncrUserAccuracy_,bean.getIncrUserAccuracy(),4);
        feedParents(form_,formIncrUserAccuracy_);
        nextPart();
        return form_;
    }

    private void cell(AbstractProgramInfos _api, AbsPanel _form, int _v, String _txt, AbsGridConstraints _cts) {
        if (_v == CommonBean.TRUE_VALUE) {
            formatMessageDir(_api, _form, _cts, _txt);
        } else {
            formatMessage(_api, _form, _cts, MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_STATISTICS_NO);
        }
    }

    private void place(AbstractProgramInfos _api, AbsPanel _line, int _value, String _alt, String _found) {
        if (_value == Fighter.BACK) {
            formatMessage(_api, _line, MessagesPkBean.FIGHTER, _alt);
        } else {
            formatMessage(_api, _line, MessagesPkBean.FIGHTER, _found, Long.toString(_value));
        }
    }

    public static StringMap<String> file(AbstractProgramInfos _api) {
        return filesFight(_api).getVal(MessagesPkBean.FIGHTER).getMapping();
    }
}
