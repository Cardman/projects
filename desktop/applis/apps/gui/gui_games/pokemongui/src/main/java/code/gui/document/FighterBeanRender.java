package code.gui.document;

import aiki.beans.*;
import aiki.beans.facade.fight.*;
import aiki.beans.fight.*;
import aiki.facade.*;
import aiki.game.*;
import aiki.game.fight.*;
import aiki.game.fight.util.*;
import code.gui.*;
import code.scripts.confs.*;
import code.util.*;
import code.util.core.*;

public final class FighterBeanRender extends AbsFightBeanRender {
    private final FighterBean bean = new FighterBean();
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        StringMapObject sm_ = new StringMapObject();
        sm_.putAllMap(_form);
        init(bean,_facade, sm_);
        initPage();
        setBackground(GuiConstants.WHITE);
        setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesFightFighter.M_P_91_TITLE)));
        initPage();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,bean),MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_FIGHT);
        nextPart();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML,bean),MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_TEAM);
        nextPart();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHTER_HTML,bean),MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_REFRESH);
        nextPart();
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_NAME,bean.getName());
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_GENDER,bean.getGender());
        displayBoolTrue(MessagesPkBean.FIGHTER,bean.getChanged(),MessagesFightFighter.M_P_91_CHANGED);
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_CURRENT_NAME,bean.getCurrentName());
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_CURRENT_GENDER,bean.getCurrentGender());
        displayNotEmpty(MessagesPkBean.FIGHTER,bean.getUsedBallCatching(),MessagesFightFighter.M_P_91_USED_BALL_CATCHING);
        displayBoolFull(MessagesPkBean.FIGHTER,bean.getBelongingToPlayer(),MessagesFightFighter.M_P_91_BELONG_PLAYER,MessagesFightFighter.M_P_91_BELONG_PLAYER_NO);
        place(bean.getGroundPlaceSubst(), MessagesFightFighter.M_P_91_GROUND_SUBSTIT_BACK, MessagesFightFighter.M_P_91_GROUND_SUBSTIT);
        place(bean.getGroundPlace(), MessagesFightFighter.M_P_91_GROUND_PLACE_BACK, MessagesFightFighter.M_P_91_GROUND_PLACE);
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_NICKNAME,bean.getNickname());
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_HAPPINESS,Long.toString(bean.getHappiness()));
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_LEVEL,Long.toString(bean.getLevel()));
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_WON_EXP_LAST_LEVEL,bean.getWonExpSinceLastLevel().toNumberString());
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_NECESSARY_PTS,bean.getNecessaryPointsNextLevel().toNumberString());
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_WEIGHT,bean.getWeight().toNumberString(),bean.getWeightStr());
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_HEIGHT,bean.getHeight().toNumberString(),bean.getHeightStr());
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_REMAIN_HP,bean.getRemainingHp().toNumberString(),bean.getRemainingHpStr(),bean.getRemainingHpStrPerCent(),bean.getMaxHp().toNumberString());
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_CLONE,bean.getClone().toNumberString(),bean.getCloneStr());
        displayNotEmpty(MessagesPkBean.FIGHTER,bean.getCurrentAbility(),MessagesFightFighter.M_P_91_CURRENT_ABILITY);
        displayEmpty(MessagesPkBean.FIGHTER,bean.getCurrentAbility(),MessagesFightFighter.M_P_91_CURRENT_ABILITY_NO);
        displayBoolFull(MessagesPkBean.FIGHTER,bean.getActed(),MessagesFightFighter.M_P_91_ACTED,MessagesFightFighter.M_P_91_ACTED_NO);
        displayNotEmpty(MessagesPkBean.FIGHTER,bean.getLastUsedItem(),MessagesFightFighter.M_P_91_LAST_USED_ITEM);
        displayEmpty(MessagesPkBean.FIGHTER,bean.getLastUsedItem(),MessagesFightFighter.M_P_91_LAST_USED_ITEM_NO);
        displayNotEmpty(MessagesPkBean.FIGHTER,bean.getItem(),MessagesFightFighter.M_P_91_ITEM);
        displayEmpty(MessagesPkBean.FIGHTER,bean.getItem(),MessagesFightFighter.M_P_91_ITEM_NO);
        displayNotEmpty(MessagesPkBean.FIGHTER,bean.getExpItem(),MessagesFightFighter.M_P_91_EXP_ITEM);
        displayEmpty(MessagesPkBean.FIGHTER,bean.getExpItem(),MessagesFightFighter.M_P_91_EXP_ITEM_NO);
        displayBoolTrue(MessagesPkBean.FIGHTER,bean.getUsingItem(),MessagesFightFighter.M_P_91_USING_ITEM);
        feedParents();
        nextPart();
        displayStringList(MessagesPkBean.FIGHTER,bean.getTypes(),MessagesFightFighter.M_P_91_TYPES);
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getMoves(),MessagesFightFighter.M_P_91_MOVES);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER, bean.getMoves(), MessagesFightFighter.M_P_91_MOVES_KEY, MessagesFightFighter.M_P_91_MOVES_VALUE_ONE, MessagesFightFighter.M_P_91_MOVES_VALUE_TWO);
        new BeanDisplayMap<String, UsesOfMove>(new BeanDisplayString(),new BeanDisplayUsesOfMove()).display(this, bean.getMoves());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getCurrentMoves(),MessagesFightFighter.M_P_91_CURRENT_MOVES);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER, bean.getCurrentMoves(), MessagesFightFighter.M_P_91_CURRENT_MOVES_KEY, MessagesFightFighter.M_P_91_CURRENT_MOVES_VALUE_ONE, MessagesFightFighter.M_P_91_CURRENT_MOVES_VALUE_TWO);
        new BeanDisplayMap<String, UsesOfMove>(new BeanDisplayString(),new BeanDisplayUsesOfMove()).display(this, bean.getCurrentMoves());
        feedParents();
        nextPart();
        formatMessage(MessagesPkBean.FIGHTER, MessagesFightFighter.M_P_91_NB_ROUNDS, bean.getNbRounds().toNumberString());
        display(MessagesPkBean.FIGHTER,bean.getCopiedMoves(),MessagesFightFighter.M_P_91_COPIED_MOVES);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER, bean.getCopiedMoves(), MessagesFightFighter.M_P_91_COPIED_MOVES_OLD, MessagesFightFighter.M_P_91_COPIED_MOVES_NEW, MessagesFightFighter.M_P_91_COPIED_MOVES_PP);
        new BeanDisplayMap<String, CopiedMove>(new BeanDisplayString(),new BeanDisplayCopiedMove()).display(this, bean.getCopiedMoves());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getStatistics(),MessagesFightFighter.M_P_91_STATISTICS);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER, bean.getStatistics(), MessagesFightFighter.M_P_91_STATISTICS_KEY, MessagesFightFighter.M_P_91_STATISTICS_BASE, MessagesFightFighter.M_P_91_STATISTICS_EV, MessagesFightFighter.M_P_91_STATISTICS_IV, MessagesFightFighter.M_P_91_STATISTICS_BOOST);
        for (StatisticInfo s: bean.getStatistics()) {
            formatMessageDirCts(s.getDisplayStatistic());
            cell(s.base(), s.getStatisBase().toNumberString());
            cell(s.base(), Long.toString(s.getEv()));
            cell(s.base(), Long.toString(s.getIv()));
            cell(s.boost(), Long.toString(s.getStatisBoost()));
        }
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getDamageRateByType(),MessagesFightFighter.M_P_91_DAMAGE_POWER_TYPES);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER, bean.getDamageRateByType(), MessagesFightFighter.M_P_91_DAMAGE_POWER_TYPES_KEY, MessagesFightFighter.M_P_91_DAMAGE_POWER_TYPES_VALUE_ONE, MessagesFightFighter.M_P_91_DAMAGE_POWER_TYPES_VALUE_TWO);
        new BeanDisplayMap<String, MultPowerMoves>(new BeanDisplayString(),new BeanDisplayMultPowerMoves()).display(this, bean.getDamageRateByType());
        feedParents();
        nextPart();
        displayStringList(MessagesPkBean.FIGHTER,bean.getProtectedAgainstMoveTypes(),MessagesFightFighter.M_P_91_TYPES_PROTECTED);
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getDamageSufferedCateg(),MessagesFightFighter.M_P_91_SUFFERING_DAMAGE);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER, bean.getDamageSufferedCateg(), MessagesFightFighter.M_P_91_SUFFERING_DAMAGE_KEY, MessagesFightFighter.M_P_91_SUFFERING_DAMAGE_VALUE_ROUND, MessagesFightFighter.M_P_91_SUFFERING_DAMAGE_VALUE_USING);
        new BeanDisplayMap<String, SufferedDamageCategory>(new BeanDisplayString(),new BeanDisplaySufferedDamageCategory()).display(this, bean.getDamageSufferedCateg());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getEnabledMoves(),MessagesFightFighter.M_P_91_ENBALED_MOVES);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER,bean.getEnabledMoves(), MessagesFightFighter.M_P_91_ENBALED_MOVES_KEY, MessagesFightFighter.M_P_91_ENBALED_MOVES_ENABLED, MessagesFightFighter.M_P_91_ENBALED_MOVES_NB_ROUND);
        new BeanDisplayMap<String,ActivityOfMoveStill>(new BeanDisplayString(),new BeanDisplayActivityOfMoveStill(file().getVal(MessagesFightFighter.M_P_91_ENBALED_MOVES_ENABLED_Y),file().getVal(MessagesFightFighter.M_P_91_ENBALED_MOVES_ENABLED_N),file().getVal(MessagesFightFighter.M_P_91_ENBALED_MOVES_NO))).display(this, bean.getEnabledMoves());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getEnabledMovesForAlly(),MessagesFightFighter.M_P_91_ENBALED_MOVES_ALLY);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER,bean.getEnabledMovesForAlly(), MessagesFightFighter.M_P_91_ENBALED_MOVES_ALLY_KEY, MessagesFightFighter.M_P_91_ENBALED_MOVES_ALLY_ENABLED);
        new BeanDisplayMap<String,Integer>(new BeanDisplayString(),new BeanDisplayBool(file().getVal(MessagesFightFighter.M_P_91_ENBALED_MOVES_ALLY_ENABLED_Y),file().getVal(MessagesFightFighter.M_P_91_ENBALED_MOVES_ALLY_ENABLED_N))).display(this, bean.getEnabledMovesForAlly());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getNbUsesMoves(),MessagesFightFighter.M_P_91_NB_USES);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER,bean.getNbUsesMoves(), MessagesFightFighter.M_P_91_NB_USES_KEY, MessagesFightFighter.M_P_91_NB_USES_VALUE);
        new BeanDisplayMap<String,Long>(new BeanDisplayString(),new BeanDisplayLong()).display(this, bean.getNbUsesMoves());
        feedParents();
        nextPart();
        initPage();
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_NB_SUCCESS_MOVES,bean.getNbRepeatingSuccessfulMoves().toNumberString());
        displayBoolFull(MessagesPkBean.FIGHTER,bean.getSuccessfulMove(),MessagesFightFighter.M_P_91_SUCCESSFUL_MOVE,MessagesFightFighter.M_P_91_SUCCESSFUL_MOVE_NO);
        displayNotEmpty(MessagesPkBean.FIGHTER,bean.getLastSuccessfulMove(),MessagesFightFighter.M_P_91_LAST_SUCCESSFUL_MOVE);
        displayNotEmpty(MessagesPkBean.FIGHTER,bean.getLastUsedMove(),MessagesFightFighter.M_P_91_LAST_USED_MOVE);
        displayNotEmpty(MessagesPkBean.FIGHTER,bean.getUsedMoveLastRound(),MessagesFightFighter.M_P_91_USED_MOVE_LAST_ROUND);
        formatMessage(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_NB_ROUND_PREPA,Long.toString(bean.getNbPrepaRound()));
        feedParents();
        nextPart();
        displayStringList(MessagesPkBean.FIGHTER,bean.getAlreadyInvokedMovesRound(),MessagesFightFighter.M_P_91_INVOKED_MOVES);
        nextPart();
        initPage();
        displayNotEmpty(MessagesPkBean.FIGHTER,bean.getLastSufferedMove(),MessagesFightFighter.M_P_91_LAST_SUFFERED_MOVE);
        feedParents();
        nextPart();
        displayStringList(MessagesPkBean.FIGHTER,bean.getLastSufferedMoveTypes(),MessagesFightFighter.M_P_91_LAST_SUFFERED_MOVE_TYPES);
        nextPart();
        initPage();
        displayBoolFull(MessagesPkBean.FIGHTER,bean.getDisappeared(),MessagesFightFighter.M_P_91_DISAPPEARED,MessagesFightFighter.M_P_91_DISAPPEARED_NO);
        displayBoolTrue(MessagesPkBean.FIGHTER,bean.getNeedingToRecharge(),MessagesFightFighter.M_P_91_NEEDING_RECHARGE);
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getStatus(),MessagesFightFighter.M_P_91_STATUS);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER,bean.getStatus(), MessagesFightFighter.M_P_91_STATUS_KEY, MessagesFightFighter.M_P_91_STATUS_VALUE);
        new BeanDisplayMap<String,Long>(new BeanDisplayString(),new BeanDisplayLong()).display(this, bean.getStatus());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getStatusRelat(),MessagesFightFighter.M_P_91_STATUS_REL);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER,bean.getStatusRelat(), MessagesFightFighter.M_P_91_STATUS_REL_KEY_ONE, MessagesFightFighter.M_P_91_STATUS_REL_KEY_TWO, MessagesFightFighter.M_P_91_STATUS_REL_KEY_THREE, MessagesFightFighter.M_P_91_STATUS_REL_ENABLED);
        new BeanDisplayMap<MoveTeamPositionFighterName,Long>(new BeanDisplayMoveTeamPositionFighterName(file().getVal(MessagesFightFighter.M_P_91_STATUS_REL_FOE),file().getVal(MessagesFightFighter.M_P_91_STATUS_REL_PLAYER)),new BeanDisplayBoolLong(file().getVal(MessagesFightFighter.M_P_91_STATUS_REL_ENABLED_Y),file().getVal(MessagesFightFighter.M_P_91_STATUS_REL_ENABLED_N))).display(this, bean.getStatusRelat());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getPrivateMoves(),MessagesFightFighter.M_P_91_PRIVATE_MOVES);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER,bean.getPrivateMoves(), MessagesFightFighter.M_P_91_PRIVATE_MOVES_KEY_ONE, MessagesFightFighter.M_P_91_PRIVATE_MOVES_KEY_TWO, MessagesFightFighter.M_P_91_PRIVATE_MOVES_KEY_THREE, MessagesFightFighter.M_P_91_PRIVATE_MOVES_MV);
        new BeanDisplayMap<MoveTeamPositionFighterName,String>(new BeanDisplayMoveTeamPositionFighterName(file().getVal(MessagesFightFighter.M_P_91_PRIVATE_MOVES_FOE),file().getVal(MessagesFightFighter.M_P_91_PRIVATE_MOVES_PLAYER)),new BeanDisplayString()).display(this, bean.getPrivateMoves());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getTrappingMoves(),MessagesFightFighter.M_P_91_TRAPPING_MOVES);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER,bean.getTrappingMoves(), MessagesFightFighter.M_P_91_TRAPPING_MOVES_KEY_ONE, MessagesFightFighter.M_P_91_TRAPPING_MOVES_KEY_TWO, MessagesFightFighter.M_P_91_TRAPPING_MOVES_KEY_THREE, MessagesFightFighter.M_P_91_TRAPPING_MOVES_ENABLED, MessagesFightFighter.M_P_91_TRAPPING_MOVES_NB_ROUND);
        new BeanDisplayMap<MoveTeamPositionFighterName,ActivityOfMoveStill>(new BeanDisplayMoveTeamPositionFighterName(file().getVal(MessagesFightFighter.M_P_91_TRAPPING_MOVES_FOE),file().getVal(MessagesFightFighter.M_P_91_TRAPPING_MOVES_PLAYER)),new BeanDisplayActivityOfMoveStill(file().getVal(MessagesFightFighter.M_P_91_TRAPPING_MOVES_ENABLED_Y),file().getVal(MessagesFightFighter.M_P_91_TRAPPING_MOVES_ENABLED_N),file().getVal(MessagesFightFighter.M_P_91_TRAPPING_MOVES_NO))).display(this, bean.getTrappingMoves());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getTrackingMoves(),MessagesFightFighter.M_P_91_TRACKING_MOVES);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER,bean.getTrackingMoves(), MessagesFightFighter.M_P_91_TRACKING_MOVES_KEY_ONE, MessagesFightFighter.M_P_91_TRACKING_MOVES_KEY_TWO, MessagesFightFighter.M_P_91_TRACKING_MOVES_KEY_THREE, MessagesFightFighter.M_P_91_TRACKING_MOVES_V, MessagesFightFighter.M_P_91_TRACKING_MOVES_ENABLED, MessagesFightFighter.M_P_91_TRACKING_MOVES_NB_ROUND);
        new BeanDisplayMap<MoveTeamPositionFighterName, AffectedMove>(new BeanDisplayMoveTeamPositionFighterName(file().getVal(MessagesFightFighter.M_P_91_TRACKING_MOVES_FOE),file().getVal(MessagesFightFighter.M_P_91_TRACKING_MOVES_PLAYER)),new BeanDisplayAffectedMove(file().getVal(MessagesFightFighter.M_P_91_TRACKING_MOVES_ENABLED_Y),file().getVal(MessagesFightFighter.M_P_91_TRACKING_MOVES_ENABLED_N),file().getVal(MessagesFightFighter.M_P_91_TRACKING_MOVES_NO))).display(this, bean.getTrackingMoves());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHTER,bean.getIncrUserAccuracy(),MessagesFightFighter.M_P_91_INC_ACCURACY);
        initGrid();
        headerCols(MessagesPkBean.FIGHTER,bean.getIncrUserAccuracy(), MessagesFightFighter.M_P_91_INC_ACCURACY_KEY_ONE, MessagesFightFighter.M_P_91_INC_ACCURACY_KEY_TWO, MessagesFightFighter.M_P_91_INC_ACCURACY_KEY_THREE, MessagesFightFighter.M_P_91_INC_ACCURACY_ENABLED);
        new BeanDisplayMap<MoveTeamPositionFighterName, Integer>(new BeanDisplayMoveTeamPositionFighterName(file().getVal(MessagesFightFighter.M_P_91_INC_ACCURACY_FOE),file().getVal(MessagesFightFighter.M_P_91_INC_ACCURACY_PLAYER)),new BeanDisplayBool(file().getVal(MessagesFightFighter.M_P_91_INC_ACCURACY_ENABLED_Y),file().getVal(MessagesFightFighter.M_P_91_INC_ACCURACY_ENABLED_N))).display(this, bean.getIncrUserAccuracy());
        feedParents();
        nextPart();
    }

    private void cell(int _v, String _txt) {
        if (_v == CommonBean.TRUE_VALUE) {
            formatMessageDirCts(_txt);
        } else {
            formatMessageCts(MessagesPkBean.FIGHTER,MessagesFightFighter.M_P_91_STATISTICS_NO);
        }
    }

    private void place(int _value, String _alt, String _found) {
        if (_value == Fighter.BACK) {
            formatMessage(MessagesPkBean.FIGHTER, _alt);
        } else {
            formatMessage(MessagesPkBean.FIGHTER, _found, Long.toString(_value));
        }
    }

    public StringMap<String> file() {
        return filesFight().getVal(MessagesPkBean.FIGHTER).getMapping();
    }
}
