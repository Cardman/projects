package code.gui.document;

import aiki.beans.*;
import aiki.beans.facade.game.dto.*;
import aiki.beans.game.*;
import aiki.facade.*;
import aiki.game.*;
import code.gui.*;
import code.util.*;
import code.util.core.*;

public final class PkPlayerRender extends AbsBeanRender {
    private final PokemonPlayerBean bean = new PokemonPlayerBean();

    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade, _form);
        initPage();
        setBackground(GuiConstants.WHITE);
        setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesPkPokemon.M_P_94_TITLE),bean.getName()));
        initPage();
        formatMessage(MessagesPkBean.POKEMON, MessagesPkPokemon.M_P_94_NAME, bean.getName());
        addImg(bean.getImage());
        feedParents();
        nextPart();
        breakLine();
        initLine();
        buildPkList(MessagesPkBean.POKEMON, MessagesPkPokemon.M_P_94_EVOLUTIONS, bean.getEvolutions());
        feedParents();
        feedParents();
        nextPart();
        initPage();
        formatMessage(MessagesPkBean.POKEMON,MessagesPkPokemon.M_P_94_LEVEL,Long.toString(bean.getLevel()));
        formatMessage(MessagesPkBean.POKEMON,MessagesPkPokemon.M_P_94_GENDER,bean.getGender());
        formatMessage(MessagesPkBean.POKEMON,MessagesPkPokemon.M_P_94_ABILITY,bean.getAbility());
        displayNotEmpty(MessagesPkBean.POKEMON,bean.getUsedBallCatching(),MessagesPkPokemon.M_P_94_USED_BALL_CATCHING);
        displayNotEmpty(MessagesPkBean.POKEMON,bean.getItem(),MessagesPkPokemon.M_P_94_ITEM);
        displayEmpty(MessagesPkBean.POKEMON,bean.getItem(),MessagesPkPokemon.M_P_94_ITEM_NO);
        formatMessage(MessagesPkBean.POKEMON,MessagesPkPokemon.M_P_94_HP,bean.getRemainingHp().toNumberString(),bean.getRemainingHpPerCent(),bean.getFullHp().toNumberString());
        formatMessage(MessagesPkBean.POKEMON, MessagesPkPokemon.M_P_94_NICKNAME,bean.getNickname());
        formatMessage(MessagesPkBean.POKEMON, MessagesPkPokemon.M_P_94_WON_EXP_LAST_LEVEL,bean.getWonExpSinceLastLevel().toNumberString());
        formatMessage(MessagesPkBean.POKEMON, MessagesPkPokemon.M_P_94_NECESSARY_PTS,bean.getNecessaryPointsNextLevel().toNumberString());
        formatMessage(MessagesPkBean.POKEMON, MessagesPkPokemon.M_P_94_HAPPINESS,Long.toString(bean.getHappiness()));
        formatMessage(MessagesPkBean.POKEMON, MessagesPkPokemon.M_P_94_NB_STEPS,Long.toString(bean.getNbStepsTeamLead()));
        feedParents();
        nextPart();
        displayStringList(MessagesPkBean.POKEMON, bean.getTypes(), MessagesPkPokemon.M_P_94_TYPES);
        displayStringList(MessagesPkBean.POKEMON, bean.getStatus(), MessagesPkPokemon.M_P_94_STATUS);
        nextPart();
        display(MessagesPkBean.POKEMON,bean.getMoves(),MessagesPkPokemon.M_P_94_MOVES);
        initGrid();
        headerCols(MessagesPkBean.POKEMON,bean.getMoves(), MessagesPkPokemon.M_P_94_MOVES_KEY, MessagesPkPokemon.M_P_94_MOVES_CUR_PP, MessagesPkPokemon.M_P_94_MOVES_MAX_PP);
        new BeanDisplayMap<String,UsesOfMove>(new BeanDisplayString(),new BeanDisplayUsesOfMove()).display(this, bean.getMoves());
        feedParents();
        nextPart();
        display(MessagesPkBean.POKEMON,bean.getStatistics(),MessagesPkPokemon.M_P_94_STATISTICS);
        initGrid();
        headerCols(MessagesPkBean.POKEMON,bean.getStatistics(), MessagesPkPokemon.M_P_94_STATISTICS_KEY, MessagesPkPokemon.M_P_94_STATISTICS_EV, MessagesPkPokemon.M_P_94_STATISTICS_IV, MessagesPkPokemon.M_P_94_STATISTICS_RATE);
        for (StatisticInfoPkPlayer e:bean.getStatistics()) {
            formatMessageDirCts(e.getName());
            formatMessageDirCts(Long.toString(e.getEv()));
            formatMessageDirCts(Long.toString(e.getIv()));
            formatMessageDirCts(e.getRate().toNumberString());
        }
        feedParents();
    }


    public StringMap<String> file() {
        return file(MessagesPkBean.POKEMON).getMapping();
    }

    @Override
    public String appName() {
        return MessagesPkBean.APP_BEAN;
    }
}
