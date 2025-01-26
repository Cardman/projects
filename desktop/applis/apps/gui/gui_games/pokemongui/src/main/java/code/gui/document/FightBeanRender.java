package code.gui.document;

import aiki.beans.*;
import aiki.beans.fight.*;
import aiki.facade.*;
import aiki.game.fight.*;
import code.gui.*;
import code.gui.initialize.*;
import code.scripts.confs.*;
import code.util.*;
import code.util.core.*;

public final class FightBeanRender extends AbsBeanRender {
    private final FightBean bean = new FightBean();
    @Override
    public AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade, StringMapObject _form) {
        init(bean,_facade, _form);
        AbsPanel form_ = _api.getCompoFactory().newPageBox();
        form_.setBackground(GuiConstants.WHITE);
        form_.setTitledBorder(StringUtil.simpleStringsFormat(file(_api).getVal(MessagesFightFight.M_P_90_TITLE_FIGHT)));
        AbsPanel line_ = _api.getCompoFactory().newPageBox();
        formatMessageAnc(_api,line_,MessagesFightFight.FIGHT,MessagesFightFight.M_P_90_REFRESH).addMouseListener(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,getRenders(), _form));
        nextPart();
        formatMessageAnc(_api,line_,MessagesFightFight.FIGHT,MessagesFightFight.M_P_90_GO_DETAIL).addMouseListener(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHTDETAIL_HTML,getRenders(), _form));
        nextPart();
        formatMessageAnc(_api,line_,MessagesFightFight.FIGHT,MessagesFightFight.M_P_90_YOURS).addMouseListener(new BeanAnchorToTeamEvent(Fight.CST_PLAYER,bean,getRenders()));
        nextPart();
        formatMessageAnc(_api,line_,MessagesFightFight.FIGHT,MessagesFightFight.M_P_90_FOES).addMouseListener(new BeanAnchorToTeamEvent(Fight.CST_FOE,bean,getRenders()));
        nextPart();
        formatMessage(_api,line_,MessagesFightFight.FIGHT,MessagesFightFight.M_P_90_MULT,Long.toString(bean.getMult()));
        formatMessage(_api,line_,MessagesFightFight.FIGHT,MessagesFightFight.M_P_90_NB_ROUNDS,bean.getNbRounds().toNumberString());
        formatMessage(_api,line_,MessagesFightFight.FIGHT,MessagesFightFight.M_P_90_NB_FLEE_ATTEMPTS,Long.toString(bean.getNbFleeAttempt()));
        formatMessage(_api,line_,MessagesFightFight.FIGHT,MessagesFightFight.M_P_90_WIN_MONEY,bean.getWinningMoney().toNumberString());
        feedParents(form_,line_);
        nextPart();
        DisplayingBeanCountable.display(this,_api,form_,MessagesFightFight.FIGHT,bean.getEnabledMoves(),MessagesFightFight.M_P_90_ENBALED_MOVES);
        AbsPanel enabledMoves_ = _api.getCompoFactory().newGrid();
        DisplayingBeanCountable.headerCols(this,_api,enabledMoves_,MessagesFightFight.FIGHT,bean.getEnabledMoves(), MessagesFightFight.M_P_90_ENBALED_MOVES_KEY, MessagesFightFight.M_P_90_ENBALED_MOVES_STILL, MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED, MessagesFightFight.M_P_90_ENBALED_MOVES_NB_ROUND);
        int len_ = bean.getEnabledMoves().size();
        for (int i = 0; i < len_; i++) {
            formatMessageDir(_api,enabledMoves_,_api.getCompoFactory().newGridCts(),bean.getEnabledMoves().getKey(i));
            if (bean.isStillEnabled(i)) {
                displayActivityOfMoveEnabled(_api,enabledMoves_,_api.getCompoFactory().newGridCts(),MessagesFightFight.FIGHT,bean.getEnabledMoves().getValue(i).getActivity(),MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_Y,MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_N);
            } else {
                formatMessageDir(_api,enabledMoves_,_api.getCompoFactory().newGridCts(),formatMessage(_api,MessagesFightFight.FIGHT,MessagesFightFight.M_P_90_ENBALED_MOVES_NO));
            }
            displayActivityOfMoveEnabled(_api,enabledMoves_,_api.getCompoFactory().newGridCts(),MessagesFightFight.FIGHT,bean.getEnabledMoves().getValue(i).getActivity(),MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_Y,MessagesFightFight.M_P_90_ENBALED_MOVES_ENABLED_N);
            displayActivityOfMoveNbRound(_api,enabledMoves_,remainder(_api),MessagesFightFight.FIGHT,bean.getEnabledMoves().getValue(i).getActivity(),MessagesFightFight.M_P_90_ENBALED_MOVES_NO);
        }
        feedParents(form_,enabledMoves_);
        return form_;
    }

    @Override
    public String formatMessage(AbstractProgramInfos _api, String _file, String _key, String... _values) {
        return StringUtil.simpleStringsFormat(filesFight(_api).getVal(_file).getMapping().getVal(_key), _values);
    }
    public static StringMap<String> file(AbstractProgramInfos _api) {
        return filesFight(_api).getVal(MessagesFightFight.FIGHT).getMapping();
    }
}
