package code.gui.document;

import aiki.beans.*;
import aiki.beans.facade.game.dto.*;
import aiki.beans.game.*;
import aiki.facade.*;
import aiki.game.*;
import code.formathtml.render.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.pages.aiki.*;
import code.util.*;
import code.util.core.*;

public final class PkPlayerRender extends AbsBeanRender {
    private final PokemonPlayerBean bean = new PokemonPlayerBean();

    @Override
    public AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade, _form);
        AbsPanel form_ = _api.getCompoFactory().newPageBox();
        form_.setBackground(GuiConstants.WHITE);
        form_.setTitledBorder(StringUtil.simpleStringsFormat(file(_api).getVal(MessagesPkPokemon.M_P_94_TITLE),bean.getName()));
        AbsPanel line_ = _api.getCompoFactory().newPageBox();
        formatMessage(_api, line_,MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_NAME, bean.getName());
        addImg(_api, line_, bean.getImage());
        feedParents(form_,line_);
        nextPart();
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        AbsPanel formEvosLine_ = _api.getCompoFactory().newLineBox();
        AbsPanel formEvos_ = _api.getCompoFactory().newPageBox();
        display(_api,formEvos_,MessagesPkPokemon.POKEMON,bean.getEvolutions(),MessagesPkPokemon.M_P_94_EVOLUTIONS);
        for (ImgPkPlayer i:bean.getEvolutions()) {
            nextPart();
            AbsPanel lineEvo_ = _api.getCompoFactory().newLineBox();
            paintMetaLabelDisk(_api,lineEvo_);
            addImg(_api,lineEvo_,i.getImage());
            formatMessageDir(_api,lineEvo_,i.getKey().getTranslation());
            feedParents(formEvos_,lineEvo_);
            getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        }
        feedParents(formEvosLine_,formEvos_);
        feedParents(form_,formEvosLine_);
        nextPart();
        AbsPanel lineInfo_ = _api.getCompoFactory().newPageBox();
        formatMessage(_api, lineInfo_,MessagesPkPokemon.POKEMON,MessagesPkPokemon.M_P_94_LEVEL,Long.toString(bean.getLevel()));
        formatMessage(_api, lineInfo_,MessagesPkPokemon.POKEMON,MessagesPkPokemon.M_P_94_GENDER,bean.getGender());
        formatMessage(_api, lineInfo_,MessagesPkPokemon.POKEMON,MessagesPkPokemon.M_P_94_ABILITY,bean.getAbility());
        displayNotEmpty(_api,lineInfo_,MessagesPkPokemon.POKEMON,bean.getUsedBallCatching(),MessagesPkPokemon.M_P_94_USED_BALL_CATCHING);
        displayNotEmpty(_api,lineInfo_,MessagesPkPokemon.POKEMON,bean.getItem(),MessagesPkPokemon.M_P_94_ITEM);
        displayEmpty(_api,lineInfo_,MessagesPkPokemon.POKEMON,bean.getItem(),MessagesPkPokemon.M_P_94_ITEM_NO);
        formatMessage(_api, lineInfo_,MessagesPkPokemon.POKEMON,MessagesPkPokemon.M_P_94_HP,bean.getRemainingHp().toNumberString(),bean.getRemainingHpPerCent(),bean.getFullHp().toNumberString());
        formatMessage(_api, lineInfo_,MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_NICKNAME,bean.getNickname());
        formatMessage(_api, lineInfo_,MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_WON_EXP_LAST_LEVEL,bean.getWonExpSinceLastLevel().toNumberString());
        formatMessage(_api, lineInfo_,MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_NECESSARY_PTS,bean.getNecessaryPointsNextLevel().toNumberString());
        formatMessage(_api, lineInfo_,MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_HAPPINESS,Long.toString(bean.getHappiness()));
        formatMessage(_api, lineInfo_,MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_NB_STEPS,Long.toString(bean.getNbStepsTeamLead()));
        feedParents(form_,lineInfo_);
        nextPart();
        displayStringList(_api, form_,MessagesPkPokemon.POKEMON, bean.getTypes(), MessagesPkPokemon.M_P_94_TYPES);
        displayStringList(_api, form_,MessagesPkPokemon.POKEMON, bean.getStatus(), MessagesPkPokemon.M_P_94_STATUS);
        nextPart();
        display(_api,form_,MessagesPkPokemon.POKEMON,bean.getMoves(),MessagesPkPokemon.M_P_94_MOVES);
        AbsPanel tableMove_ = _api.getCompoFactory().newGrid();
        headerCol(_api, tableMove_, _api.getCompoFactory().newGridCts(), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_MOVES_KEY);
        headerCol(_api, tableMove_, _api.getCompoFactory().newGridCts(), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_MOVES_CUR_PP);
        headerCol(_api, tableMove_, remainder(_api), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_MOVES_MAX_PP);
        new BeanDisplayMap<String,UsesOfMove>(new BeanDisplayString(),new BeanDisplayUsesOfMove()).display(this,_api,tableMove_,bean.getMoves(),3);
        feedParents(form_,tableMove_);
        nextPart();
        display(_api,form_,MessagesPkPokemon.POKEMON,bean.getStatistics(),MessagesPkPokemon.M_P_94_STATISTICS);
        AbsPanel tableStat_ = _api.getCompoFactory().newGrid();
        headerCol(_api, tableStat_, _api.getCompoFactory().newGridCts(), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_STATISTICS_KEY);
        headerCol(_api, tableStat_, _api.getCompoFactory().newGridCts(), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_STATISTICS_EV);
        headerCol(_api, tableStat_, _api.getCompoFactory().newGridCts(), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_STATISTICS_IV);
        headerCol(_api, tableStat_, remainder(_api), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_STATISTICS_RATE);
        for (StatisticInfoPkPlayer e:bean.getStatistics()) {
            formatMessageDir(_api,tableStat_,_api.getCompoFactory().newGridCts(),e.getName());
            formatMessageDir(_api,tableStat_,_api.getCompoFactory().newGridCts(),Long.toString(e.getEv()));
            formatMessageDir(_api,tableStat_,_api.getCompoFactory().newGridCts(),Long.toString(e.getIv()));
            formatMessageDir(_api,tableStat_,remainder(_api),e.getRate().toNumberString());
        }
        feedParents(form_,tableStat_);
        return form_;
    }


    public static StringMap<String> file(AbstractProgramInfos _api) {
        return files(_api, MessagesInit.APP_BEAN).getVal(MessagesPkPokemon.POKEMON).getMapping();
    }

    @Override
    public String appName() {
        return MessagesInit.APP_BEAN;
    }
}
