package code.gui.document;

import aiki.beans.*;
import aiki.beans.facade.game.dto.*;
import aiki.beans.game.*;
import aiki.facade.*;
import aiki.game.*;
import code.formathtml.render.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.StringUtil;

public final class PkPlayerRender extends AbsBeanRender {
    private final PokemonPlayerBean bean = new PokemonPlayerBean();

    @Override
    public AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade) {
        getMetaSearchableContents().clear();
        getParents().clear();
        getRefsSearch().clear();
        bean.setDataBase(_facade);
        bean.setForms(new StringMapObject());
        bean.setLanguage(_facade.getLanguage());
        bean.beforeDisplaying();
        setPartGroup(0);
        setRowGroup(0);
        AbsPanel form_ = _api.getCompoFactory().newPageBox();
        form_.setBackground(GuiConstants.WHITE);
        AbsPanel line_ = _api.getCompoFactory().newPageBox();
        formatMessage(_api, line_,MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_NAME, bean.getName());
        addImg(_api, line_, bean.getImage());
        feedParents(form_,line_);
        nextPart();
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        AbsPanel formEvosLine_ = _api.getCompoFactory().newLineBox();
        AbsPanel formEvos_ = _api.getCompoFactory().newPageBox();
        DisplayingBeanCountable.display(this,_api,formEvos_,MessagesPkPokemon.POKEMON,bean.getEvolutions(),MessagesPkPokemon.M_P_94_EVOLUTIONS);
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
        DisplayingBeanCountable.displayNotEmpty(this,_api,lineInfo_,MessagesPkPokemon.POKEMON,bean.getUsedBallCatching(),MessagesPkPokemon.M_P_94_USED_BALL_CATCHING);
        DisplayingBeanCountable.displayNotEmpty(this,_api,lineInfo_,MessagesPkPokemon.POKEMON,bean.getItem(),MessagesPkPokemon.M_P_94_ITEM);
        DisplayingBeanCountable.displayEmpty(this,_api,lineInfo_,MessagesPkPokemon.POKEMON,bean.getItem(),MessagesPkPokemon.M_P_94_ITEM_NO);
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
        DisplayingBeanCountable.display(this,_api,form_,MessagesPkPokemon.POKEMON,bean.getMoves(),MessagesPkPokemon.M_P_94_MOVES);
        AbsPanel tableMove_ = _api.getCompoFactory().newGrid();
        headerCol(_api, tableMove_, _api.getCompoFactory().newGridCts(), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_MOVES_KEY);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        headerCol(_api, tableMove_, _api.getCompoFactory().newGridCts(), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_MOVES_CUR_PP);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        headerCol(_api, tableMove_, remainder(_api), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_MOVES_MAX_PP);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        for (EntryCust<String, UsesOfMove> e:bean.getMoves().entryList()) {
            formatMessageDir(_api,tableMove_,_api.getCompoFactory().newGridCts(),e.getKey());
            getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
            formatMessageDir(_api,tableMove_,_api.getCompoFactory().newGridCts(),Long.toString(e.getValue().getCurrent()));
            getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
            formatMessageDir(_api,tableMove_,remainder(_api),Long.toString(e.getValue().getMax()));
            getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        }
        feedParents(form_,tableMove_);
        nextPart();
        DisplayingBeanCountable.display(this,_api,form_,MessagesPkPokemon.POKEMON,bean.getStatistics(),MessagesPkPokemon.M_P_94_STATISTICS);
        AbsPanel tableStat_ = _api.getCompoFactory().newGrid();
        headerCol(_api, tableStat_, _api.getCompoFactory().newGridCts(), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_STATISTICS_KEY);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        headerCol(_api, tableStat_, _api.getCompoFactory().newGridCts(), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_STATISTICS_EV);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        headerCol(_api, tableStat_, _api.getCompoFactory().newGridCts(), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_STATISTICS_IV);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        headerCol(_api, tableStat_, remainder(_api), MessagesPkPokemon.POKEMON, MessagesPkPokemon.M_P_94_STATISTICS_RATE);
        getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        for (StatisticInfoPkPlayer e:bean.getStatistics()) {
            formatMessageDir(_api,tableStat_,_api.getCompoFactory().newGridCts(),e.getName());
            getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
            formatMessageDir(_api,tableStat_,_api.getCompoFactory().newGridCts(),Long.toString(e.getEv()));
            getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
            formatMessageDir(_api,tableStat_,_api.getCompoFactory().newGridCts(),Long.toString(e.getIv()));
            getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
            formatMessageDir(_api,tableStat_,remainder(_api),e.getRate().toNumberString());
            getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
        }
        feedParents(form_,tableStat_);
        form_.setTitledBorder(StringUtil.simpleStringsFormat(file(_api).getVal(MessagesPkPokemon.M_P_94_TITLE),bean.getName()));
        return form_;
    }

    public void headerCol(AbstractProgramInfos _api, AbsPanel _tableStat, AbsGridConstraints _cts, String _file, String _key) {
        AbsTextPane th_ = formatMessage(_api, _tableStat, _cts, _file, _key);
        th_.setBackground(GuiConstants.YELLOW);
    }

    private AbsGridConstraints remainder(AbstractProgramInfos _api) {
        AbsGridConstraints cts_ = _api.getCompoFactory().newGridCts();
        cts_.gridwidth(GuiConstants.REMAINDER);
        return cts_;
    }


    public static StringMap<String> file(AbstractProgramInfos _api) {
        return files(_api).getVal(MessagesPkPokemon.POKEMON).getMapping();
    }


}
