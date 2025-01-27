package code.gui.document;

import aiki.beans.*;
import aiki.beans.facade.fight.*;
import aiki.beans.fight.*;
import aiki.facade.*;
import aiki.game.fight.Fight;
import code.formathtml.render.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.confs.*;
import code.util.*;
import code.util.core.*;

public final class FightCalculationBeanRender extends AbsFightBeanRender {
    private final FightCalculationBean bean = new FightCalculationBean();
    @Override
    public AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade, _form);
        AbsPanel form_ = _api.getCompoFactory().newPageBox();
        form_.setBackground(GuiConstants.WHITE);
        form_.setTitledBorder(StringUtil.simpleStringsFormat(file(_api).getVal(MessagesFightFight.M_P_90_TITLE_DETAIL_FIGHT)));
        AbsPanel line_ = _api.getCompoFactory().newPageBox();
        formatMessageAnc(_api,line_, MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_FIGHT).addMouseListener(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,getRenders(), _form));
        nextPart();
        formatMessageAnc(_api,line_, MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_REFRESH).addMouseListener(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHTDETAIL_HTML,getRenders(), _form));
        nextPart();
        feedParents(form_,line_);
        displayStringList(_api, form_, MessagesPkBean.FIGHT, bean.getSortedFighters(), MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHT,bean.getSortedFightersWildFight(),MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD);
        AbsPanel formSortedFightersWildFight_ = _api.getCompoFactory().newPageBox();
        for (ImgMovesListTeamPositionsList i: bean.getSortedFightersWildFight()) {
            nextPart();
            AbsPanel lineFighters_ = _api.getCompoFactory().newLineBox();
            paintMetaLabelDisk(_api,lineFighters_);
            AbsPanel fightersContent_ = _api.getCompoFactory().newPageBox();
            display(_api,fightersContent_, MessagesPkBean.FIGHT,i.getKeyPks(),MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD_KEY);
            for (FighterImgPkNameMv p:i.getKeyPks()) {
                AbsPanel lineFighter_ = _api.getCompoFactory().newLineBox();
                paintMetaLabelDisk(_api,lineFighter_);
                addImg(_api,lineFighter_,p.getImagePk());
                formatMessageDir(_api,lineFighter_,p.getNameMvTr());
                feedParents(fightersContent_,lineFighter_);
                getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
            }
            displayStringList(_api,fightersContent_, MessagesPkBean.FIGHT,i.getNamesPk(),MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD_VALUE);
            feedParents(lineFighters_,fightersContent_);
            feedParents(formSortedFightersWildFight_,lineFighters_);
        }
        feedParents(form_,formSortedFightersWildFight_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHT,bean.getDamage(),MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES);
        AbsPanel formDamage_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formDamage_, MessagesPkBean.FIGHT,bean.getDamage(), MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_KEY_ONE,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_KEY_TWO,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_KEY_THREE,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_DAMAGE);
        for(KeyHypothesis k:bean.getDamage()) {
            formatMessageDir(_api,formDamage_,_api.getCompoFactory().newGridCts(),k.getPlayerPokemon());
            formatMessageDir(_api,formDamage_,_api.getCompoFactory().newGridCts(),k.getMove());
            String message_ = targetPk(_api, k);
            formatMessageDir(_api,formDamage_,_api.getCompoFactory().newGridCts(),message_);
            formatMessageDir(_api,formDamage_,remainder(_api),k.getDamage().toNumberString()+" - "+k.getDamageSecond().toNumberString());
        }
        feedParents(form_,formDamage_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHT,bean.getAllyChoice(),MessagesFightFight.M_P_90_ALLY_CHOICES);
        AbsPanel formAllyChoice_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formAllyChoice_, MessagesPkBean.FIGHT,bean.getAllyChoice(), MessagesFightFight.M_P_90_ALLY_CHOICES_KEY, MessagesFightFight.M_P_90_ALLY_CHOICES_KEY_TEAM, MessagesFightFight.M_P_90_ALLY_CHOICES_KEY_PLACE, MessagesFightFight.M_P_90_ALLY_CHOICES_KEY_NAME, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE_TEAM, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE_PLACE, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE_NAME);
        for(EntryCust<TrPkMoveTarget, TrPkMoveTarget> e:bean.getAllyChoice().entryList()) {
            displayTrPkMoveTarget(_api,formAllyChoice_,true, MessagesPkBean.FIGHT,e.getKey());
            displayTrPkMoveTarget(_api,formAllyChoice_,false, MessagesPkBean.FIGHT,e.getValue());
        }
        feedParents(form_,formAllyChoice_);
        nextPart();
        display(_api,form_, MessagesPkBean.FIGHT,bean.getFoeChoices(),MessagesFightFight.M_P_90_FOE_CHOICES);
        AbsPanel formFoeChoice_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formFoeChoice_, MessagesPkBean.FIGHT,bean.getFoeChoices(), MessagesFightFight.M_P_90_FOE_CHOICES_KEY, MessagesFightFight.M_P_90_FOE_CHOICES_KEY_NAME, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE_TEAM, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE_PLACE, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE_NAME);
        int len_ = bean.getFoeChoices().size();
        for (int i = 0; i < len_; i++) {
            formatMessageDir(_api,formFoeChoice_,_api.getCompoFactory().newGridCts(),Long.toString(bean.getFoeChoices().getKey(i).getIndex()));
            formatMessageDir(_api,formFoeChoice_,_api.getCompoFactory().newGridCts(),bean.getFoeChoices().getKey(i).getTranslation());
            formatMessageDir(_api,formFoeChoice_,_api.getCompoFactory().newGridCts(),bean.getFoeChoices().getValue(i).getMoveTarget().getMove());
            if (bean.isChosenTarget(i)) {
                if (bean.getFoeChoices().getValue(i).getMoveTarget().getTarget().getTeam() == Fight.CST_FOE) {
                    formatMessage(_api,formFoeChoice_,_api.getCompoFactory().newGridCts(), MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_FOE);
                } else {
                    formatMessage(_api,formFoeChoice_,_api.getCompoFactory().newGridCts(), MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_PLAYER);
                }
                formatMessageDir(_api,formFoeChoice_,_api.getCompoFactory().newGridCts(),Long.toString(bean.getFoeChoices().getValue(i).getMoveTarget().getTarget().getPosition()));
                formatMessageDir(_api,formFoeChoice_,remainder(_api),bean.getFoeChoices().getValue(i).getTranslation());
            } else {
                formatMessage(_api,formFoeChoice_,_api.getCompoFactory().newGridCts(), MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
                formatMessage(_api,formFoeChoice_,_api.getCompoFactory().newGridCts(), MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
                formatMessage(_api,formFoeChoice_,remainder(_api), MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
            }
        }
        feedParents(form_,formFoeChoice_);
        return form_;
    }

    private String targetPk(AbstractProgramInfos _api, KeyHypothesis _k) {
        String message_;
        if (_k.isBelongsToUser()) {
            message_ = formatMessage(_api, MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_PLAYER);
        } else {
            message_ = formatMessage(_api, MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_FOE);
        }
        message_+="\u00A0";
        message_+= _k.getTargetPokemon();
        return message_;
    }

    public static StringMap<String> file(AbstractProgramInfos _api) {
        return filesFight(_api).getVal(MessagesPkBean.FIGHT).getMapping();
    }
}
