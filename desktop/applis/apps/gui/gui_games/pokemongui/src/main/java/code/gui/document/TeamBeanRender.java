package code.gui.document;

import aiki.beans.*;
import aiki.beans.fight.*;
import aiki.facade.*;
import aiki.game.fight.*;
import code.maths.LgInt;
import code.scripts.confs.*;
import code.util.*;
import code.util.core.*;

public final class TeamBeanRender extends AbsFightBeanRender {
    private final TeamBean bean = new TeamBean();
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        StringMapObject sm_ = new StringMapObject();
        sm_.putAllMap(_form);
        init(bean,_facade, sm_);
        initPage();
        setBackgroundBody();
        if (bean.getFoeTeam()) {
            setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesFightTeam.M_P_92_TITLE_FOE)));
        } else {
            setTitledBorder(StringUtil.simpleStringsFormat(file().getVal(MessagesFightTeam.M_P_92_TITLE_PLAYER)));
        }
        initPage();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,bean),MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_FIGHT);
        nextPart();
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML,bean),MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_REFRESH);
        nextPart();
        for (EntryCust<Integer,String> e:bean.getMembers().entryList()) {
            initLine();
            paintMetaLabelDisk();
            formatMessageDirAnc(e.getValue(),new BeanAnchorToFighterEvent(e.getKey(),bean));
            nextPart();
            feedParents();
        }
        feedParents();
        display(MessagesPkBean.TEAM,bean.getEnabledMoves(),MessagesFightTeam.M_P_92_ENBALED_MOVES);
        initGrid();
        headerCols(MessagesPkBean.TEAM,bean.getEnabledMoves(), MessagesFightTeam.M_P_92_ENBALED_MOVES_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED, MessagesFightTeam.M_P_92_ENBALED_MOVES_NB_ROUND);
        new BeanDisplayMap<String,ActivityOfMoveStill>(new BeanDisplayString(),new BeanDisplayActivityOfMoveStill(file().getVal(MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED_Y),file().getVal(MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED_N),file().getVal(MessagesFightTeam.M_P_92_ENBALED_MOVES_NO))).display(this, bean.getEnabledMoves());
        feedParents();
        nextPart();
        display(MessagesPkBean.TEAM,bean.getEnabledMovesByGroup(),MessagesFightTeam.M_P_92_ENBALED_MOVES);
        initGrid();
        headerCols(MessagesPkBean.TEAM,bean.getEnabledMovesByGroup(), MessagesFightTeam.M_P_92_ENBALED_MOVES_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED, MessagesFightTeam.M_P_92_ENBALED_MOVES_NB_ROUND);
        int lenGr_ = bean.getEnabledMovesByGroup().size();
        for (int i = 0; i < lenGr_; i++) {
            formatMessageDirCts(bean.getKey(i));
            displayActivityOfMoveEnabled(MessagesPkBean.TEAM,bean.getEnabledMovesByGroup().getValue(i),MessagesFightTeam.M_P_92_ENBALED_MOVES_GROUPS_ENABLED_Y,MessagesFightTeam.M_P_92_ENBALED_MOVES_GROUPS_ENABLED_N);
            displayActivityOfMoveNbRound(MessagesPkBean.TEAM,bean.getEnabledMovesByGroup().getValue(i),MessagesFightTeam.M_P_92_ENBALED_MOVES_GROUPS_NO);
        }
        feedParents();
        nextPart();
        display(MessagesPkBean.TEAM,bean.getEnabledMovesWhileSendingFoeUses(),MessagesFightTeam.M_P_92_ENBALED_MOVES_SEND);
        initGrid();
        headerCols(MessagesPkBean.TEAM,bean.getEnabledMovesWhileSendingFoeUses(), MessagesFightTeam.M_P_92_ENBALED_MOVES_SEND_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_SEND_VALUE);
        new BeanDisplayMap<String, LgInt>(new BeanDisplayString(),new BeanDisplayLgInt()).display(this, bean.getEnabledMovesWhileSendingFoeUses());
        feedParents();
        nextPart();
        display(MessagesPkBean.TEAM,bean.getNbUsesMoves(),MessagesFightTeam.M_P_92_ENBALED_MOVES_USES);
        initGrid();
        headerCols(MessagesPkBean.TEAM,bean.getNbUsesMoves(), MessagesFightTeam.M_P_92_ENBALED_MOVES_USES_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_USES_VALUE);
        new BeanDisplayMap<String, Long>(new BeanDisplayString(),new BeanDisplayLong()).display(this, bean.getNbUsesMoves());
        feedParents();
        nextPart();
        healPart();
        antPart();
        playerFoePart();
    }

    private void healPart() {
        display(MessagesPkBean.TEAM,bean.getHealAfter(),MessagesFightTeam.M_P_92_HEAL_AFTER);
        initGrid();
        headerCols(MessagesPkBean.TEAM,bean.getHealAfter(), MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_ONE, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_TWO, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_RD, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_USED_CURRENT, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_USED_LAST);
        for (EntryCust<String,IntTreeMap<StacksOfUses>> e:bean.getHealAfter().entryList()) {
            for (EntryCust<Integer, StacksOfUses> f:e.getValue().entryList()) {
                formatMessageDirCts(e.getKey());
                formatMessageDirCts(Long.toString(f.getKey()));
                formatMessageDirCts(Long.toString(f.getValue().getNbRounds()));
                if (f.getValue().isFirstStacked()) {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_Y);
                } else {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_N);
                }
                if (f.getValue().isLastStacked()) {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_Y);
                } else {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_N);
                }
            }
        }
        feedParents();
        nextPart();
    }

    private void antPart() {
        display(MessagesPkBean.TEAM,bean.getMovesAnticipation(),MessagesFightTeam.M_P_92_MOVE_ANT);
        initGrid();
        headerCols(MessagesPkBean.TEAM,bean.getMovesAnticipation(), MessagesFightTeam.M_P_92_MOVE_ANT_KEY_ONE, MessagesFightTeam.M_P_92_MOVE_ANT_KEY_TWO, MessagesFightTeam.M_P_92_MOVE_ANT_TEAM, MessagesFightTeam.M_P_92_MOVE_ANT_GROUND, MessagesFightTeam.M_P_92_MOVE_ANT_DAMAGE, MessagesFightTeam.M_P_92_MOVE_ANT_NB_ROUND);
        for (EntryCust<String, IntTreeMap<Anticipation>> e:bean.getMovesAnticipation().entryList()) {
            for (EntryCust<Integer, Anticipation> f:e.getValue().entryList()) {
                formatMessageDirCts(e.getKey());
                formatMessageDirCts(Long.toString(f.getKey()));
                if (f.getValue().getTargetPosition().getTeam() == Fight.CST_FOE) {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_FOE);
                } else if (f.getValue().getTargetPosition().getTeam() == Fight.CST_PLAYER) {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_PLAYER);
                } else {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
                }
                enabledAnt(f.getValue());
                formatMessageDirCts(f.getValue().getDamage().toNumberString());
                if (f.getValue().isIncrementing()) {
                    formatMessageDirCts(Long.toString(f.getValue().getNbRounds()));
                } else {
                    formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
                }
            }
        }
        feedParents();
        nextPart();
    }

    private void enabledAnt(Anticipation _f) {
        if (_f.getTargetPosition().isEnabled()) {
            formatMessageDirCts(Long.toString(_f.getTargetPosition().getPosition()));
        } else {
            formatMessageCts(MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
        }
    }

    private void playerFoePart() {
        display(MessagesPkBean.TEAM,bean.getPlayerFightersAgainstFoe(),MessagesFightTeam.M_P_92_PLAYER_FOE);
        initGrid();
        for (EntryCust<Integer, FighterAgainstFoes> e:bean.getPlayerFightersAgainstFoe().entryList()) {
            nextPart();
            initLine();
            paintMetaLabelDisk();
            formatMessageDir(e.getValue().getName());
            initPage();
            for (EntryCust<Integer, String> p:e.getValue().getFoes().entryList()) {
                initLine();
                paintMetaLabelDisk();
                formatMessageDir(p.getValue());
                feedParents();
                breakLine();
            }
            feedParents();
            feedParents();
        }
        feedParents();
        nextPart();
    }

    public StringMap<String> file() {
        return filesFight().getVal(MessagesPkBean.TEAM).getMapping();
    }
}
