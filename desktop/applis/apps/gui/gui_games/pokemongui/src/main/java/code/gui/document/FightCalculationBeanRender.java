package code.gui.document;

import aiki.beans.*;
import aiki.beans.facade.fight.*;
import aiki.beans.fight.*;
import aiki.facade.*;
import aiki.game.fight.Fight;
import code.scripts.confs.*;
import code.util.*;
import code.util.core.*;

public final class FightCalculationBeanRender extends AbsFightBeanRender {
    private final FightCalculationBean bean = new FightCalculationBean();
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade, _form);
        initPage();
        setBackgroundBody();
        setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesFightFight.M_P_90_TITLE_DETAIL_FIGHT)));
        initPage();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,bean),MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_FIGHT);
        nextPart();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHTDETAIL_HTML,bean),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_REFRESH);
        nextPart();
        feedParents();
        displayStringList(MessagesPkBean.FIGHT, bean.getSortedFighters(), MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES);
        nextPart();
        display(MessagesPkBean.FIGHT,bean.getSortedFightersWildFight(),MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD);
        initPage();
        for (ImgMovesListTeamPositionsList i: bean.getSortedFightersWildFight()) {
            nextPart();
            initLine();
            paintMetaLabelDisk();
            initPage();
            display(MessagesPkBean.FIGHT,i.getKeyPks(),MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD_KEY);
            for (FighterImgPkNameMv p:i.getKeyPks()) {
                initLine();
                paintMetaLabelDisk();
                addImg(p.getImagePk());
                formatMessageDir(p.getNameMvTr());
                feedParents();
                breakLine();
            }
            displayStringList(MessagesPkBean.FIGHT,i.getNamesPk(),MessagesFightFight.M_P_90_SORTED_FIGHTERS_FCT_CHOICES_WILD_VALUE);
            feedParents();
            feedParents();
        }
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHT,bean.getDamage(),MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES);
        initGrid();
        headerCols(MessagesPkBean.FIGHT,bean.getDamage(), MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_KEY_ONE,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_KEY_TWO,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_KEY_THREE,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_DAMAGE);
        for(KeyHypothesis k:bean.getDamage()) {
            formatMessageDirCts(k.getPlayerPokemon());
            formatMessageDirCts(k.getMove());
            String message_ = targetPk(k);
            formatMessageDirCts(message_);
            formatMessageDirCts(k.getDamage().toNumberString()+" - "+k.getDamageSecond().toNumberString());
        }
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHT,bean.getAllyChoice(),MessagesFightFight.M_P_90_ALLY_CHOICES);
        initGrid();
        headerCols(MessagesPkBean.FIGHT,bean.getAllyChoice(), MessagesFightFight.M_P_90_ALLY_CHOICES_KEY, MessagesFightFight.M_P_90_ALLY_CHOICES_KEY_TEAM, MessagesFightFight.M_P_90_ALLY_CHOICES_KEY_PLACE, MessagesFightFight.M_P_90_ALLY_CHOICES_KEY_NAME, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE_TEAM, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE_PLACE, MessagesFightFight.M_P_90_ALLY_CHOICES_VALUE_NAME);
        for(EntryCust<TrPkMoveTarget, TrPkMoveTarget> e:bean.getAllyChoice().entryList()) {
            displayTrPkMoveTarget(MessagesPkBean.FIGHT,e.getKey());
            displayTrPkMoveTarget(MessagesPkBean.FIGHT,e.getValue());
        }
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHT,bean.getFoeChoices(),MessagesFightFight.M_P_90_FOE_CHOICES);
        initGrid();
        headerCols(MessagesPkBean.FIGHT,bean.getFoeChoices(), MessagesFightFight.M_P_90_FOE_CHOICES_KEY, MessagesFightFight.M_P_90_FOE_CHOICES_KEY_NAME, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE_TEAM, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE_PLACE, MessagesFightFight.M_P_90_FOE_CHOICES_VALUE_NAME);
        int len_ = bean.getFoeChoices().size();
        for (int i = 0; i < len_; i++) {
            formatMessageDirCts(Long.toString(bean.getFoeChoices().getKey(i).getIndex()));
            formatMessageDirCts(bean.getFoeChoices().getKey(i).getTranslation());
            formatMessageDirCts(bean.getFoeChoices().getValue(i).getMoveTarget().getMove());
            if (bean.isChosenTarget(i)) {
                if (bean.getFoeChoices().getValue(i).getMoveTarget().getTarget().getTeam() == Fight.CST_FOE) {
                    formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_FOE);
                } else {
                    formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_PLAYER);
                }
                formatMessageDirCts(Long.toString(bean.getFoeChoices().getValue(i).getMoveTarget().getTarget().getPosition()));
                formatMessageDirCts(bean.getFoeChoices().getValue(i).getTranslation());
            } else {
                formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
                formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
                formatMessageCts(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOE_CHOICES_NO);
            }
        }
        feedParents();
    }

    private String targetPk(KeyHypothesis _k) {
        String message_;
        if (_k.isBelongsToUser()) {
            message_ = formatMessageRend(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_PLAYER);
        } else {
            message_ = formatMessageRend(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_DAMAGE_FCT_CHOICES_FOE);
        }
        message_+="\u00A0";
        message_+= _k.getTargetPokemon();
        return message_;
    }

    public StringMap<String> file() {
        return filesFight().getVal(MessagesPkBean.FIGHT).getMapping();
    }
}
