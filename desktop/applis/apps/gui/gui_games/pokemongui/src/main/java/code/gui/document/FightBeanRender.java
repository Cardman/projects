package code.gui.document;

import aiki.beans.*;
import aiki.beans.fight.*;
import aiki.facade.*;
import aiki.game.fight.*;
import code.gui.*;
import code.scripts.confs.*;
import code.util.*;
import code.util.core.*;

public final class FightBeanRender extends AbsFightBeanRender {
    private final FightBean bean = new FightBean();
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade, _form);
        initPage();
        setBackground(GuiConstants.WHITE);
        setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesFightFight.M_P_90_TITLE_FIGHT)));
        initPage();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,bean),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_REFRESH);
        nextPart();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHTDETAIL_HTML,bean),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_GO_DETAIL);
        nextPart();
        formatMessageAnc(new BeanAnchorToTeamEvent(Fight.CST_PLAYER,bean),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_YOURS);
        nextPart();
        formatMessageAnc(new BeanAnchorToTeamEvent(Fight.CST_FOE,bean),MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_FOES);
        nextPart();
        formatMessage(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_MULT,Long.toString(bean.getMult()));
        formatMessage(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_NB_ROUNDS,bean.getNbRounds().toNumberString());
        formatMessage(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_NB_FLEE_ATTEMPTS,Long.toString(bean.getNbFleeAttempt()));
        formatMessage(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_WIN_MONEY,bean.getWinningMoney().toNumberString());
        feedParents();
        nextPart();
        display(MessagesPkBean.FIGHT,bean.getEnabledMoves(),MessagesFightFight.M_P_90_ENBALED_MOVES);
        initGrid();
        headerCols(MessagesPkBean.FIGHT,bean.getEnabledMoves(), MessagesFightFight.M_P_90_ENBALED_MOVES_KEY, MessagesFightFight.M_P_90_ENBALED_MOVES_STILL, MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED, MessagesFightFight.M_P_90_ENBALED_MOVES_NB_ROUND);
        int len_ = bean.getEnabledMoves().size();
        for (int i = 0; i < len_; i++) {
            formatMessageDirCts(bean.getEnabledMoves().getKey(i));
            if (bean.isStillEnabled(i)) {
                displayActivityOfMoveEnabled(MessagesPkBean.FIGHT,bean.getEnabledMoves().getValue(i).getActivity(),MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_Y,MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_N);
            } else {
                formatMessageDirCts(formatMessageRend(MessagesPkBean.FIGHT,MessagesFightFight.M_P_90_ENBALED_MOVES_NO));
            }
            displayActivityOfMoveEnabled(MessagesPkBean.FIGHT,bean.getEnabledMoves().getValue(i).getActivity(),MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_Y,MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_N);
            displayActivityOfMoveNbRound(MessagesPkBean.FIGHT,bean.getEnabledMoves().getValue(i).getActivity(),MessagesFightFight.M_P_90_ENBALED_MOVES_NO);
        }
        feedParents();
    }

    public StringMap<String> file() {
        return filesFight().getVal(MessagesPkBean.FIGHT).getMapping();
    }
}
