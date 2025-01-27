package code.gui.document;

import aiki.beans.*;
import aiki.beans.fight.*;
import aiki.facade.*;
import aiki.game.fight.*;
import code.formathtml.render.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.LgInt;
import code.scripts.confs.*;
import code.util.*;
import code.util.core.*;

public final class TeamBeanRender extends AbsFightBeanRender {
    private final TeamBean bean = new TeamBean();
    @Override
    public AbsCustComponent build(AbstractProgramInfos _api, FacadeGame _facade, StringMapObject _form) {
        StringMapObject sm_ = new StringMapObject();
        sm_.putAllMap(_form);
        init(bean,_facade, sm_);
        AbsPanel form_ = _api.getCompoFactory().newPageBox();
        form_.setBackground(GuiConstants.WHITE);
        if (bean.getFoeTeam()) {
            form_.setTitledBorder(StringUtil.simpleStringsFormat(file(_api).getVal(MessagesFightTeam.M_P_92_TITLE_FOE)));
        } else {
            form_.setTitledBorder(StringUtil.simpleStringsFormat(file(_api).getVal(MessagesFightTeam.M_P_92_TITLE_PLAYER)));
        }
        AbsPanel line_ = _api.getCompoFactory().newPageBox();
        formatMessageAnc(_api,line_, MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_FIGHT).addMouseListener(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_FIGHT_HTML,getRenders(), _form));
        nextPart();
        formatMessageAnc(_api,line_, MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_REFRESH).addMouseListener(new BeanAnchorCstEvent(PkScriptPages.WEB_FIGHT_HTML_TEAM_HTML,getRenders(), _form));
        nextPart();
        for (EntryCust<Integer,String> e:bean.getMembers().entryList()) {
            AbsPanel lineMember_ = _api.getCompoFactory().newLineBox();
            paintMetaLabelDisk(_api,lineMember_);
            formatMessageDirAnc(_api,lineMember_,e.getValue()).addMouseListener(new BeanAnchorToFighterEvent(e.getKey(),bean,getRenders()));
            nextPart();
            feedParents(line_,lineMember_);
        }
        feedParents(form_,line_);
        display(_api,form_, MessagesPkBean.TEAM,bean.getEnabledMoves(),MessagesFightTeam.M_P_92_ENBALED_MOVES);
        AbsPanel formEnabledMoves_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formEnabledMoves_, MessagesPkBean.TEAM,bean.getEnabledMoves(), MessagesFightTeam.M_P_92_ENBALED_MOVES_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED, MessagesFightTeam.M_P_92_ENBALED_MOVES_NB_ROUND);
        new BeanDisplayMap<String,ActivityOfMoveStill>(new BeanDisplayString(),new BeanDisplayActivityOfMoveStill(file(_api).getVal(MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED_Y),file(_api).getVal(MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED_N),file(_api).getVal(MessagesFightTeam.M_P_92_ENBALED_MOVES_NO))).display(this,_api,formEnabledMoves_,bean.getEnabledMoves(),3);
        feedParents(form_,formEnabledMoves_);
        nextPart();
        display(_api,form_, MessagesPkBean.TEAM,bean.getEnabledMovesByGroup(),MessagesFightTeam.M_P_92_ENBALED_MOVES);
        AbsPanel formEnabledMovesByGroup_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formEnabledMovesByGroup_, MessagesPkBean.TEAM,bean.getEnabledMovesByGroup(), MessagesFightTeam.M_P_92_ENBALED_MOVES_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_ENABLED, MessagesFightTeam.M_P_92_ENBALED_MOVES_NB_ROUND);
        int lenGr_ = bean.getEnabledMovesByGroup().size();
        for (int i = 0; i < lenGr_; i++) {
            formatMessageDir(_api,formEnabledMovesByGroup_,_api.getCompoFactory().newGridCts(),bean.getKey(i));
            displayActivityOfMoveEnabled(_api,formEnabledMovesByGroup_,_api.getCompoFactory().newGridCts(), MessagesPkBean.TEAM,bean.getEnabledMovesByGroup().getValue(i),MessagesFightTeam.M_P_92_ENBALED_MOVES_GROUPS_ENABLED_Y,MessagesFightTeam.M_P_92_ENBALED_MOVES_GROUPS_ENABLED_N);
            displayActivityOfMoveNbRound(_api,formEnabledMovesByGroup_,remainder(_api), MessagesPkBean.TEAM,bean.getEnabledMovesByGroup().getValue(i),MessagesFightTeam.M_P_92_ENBALED_MOVES_GROUPS_NO);
        }
        feedParents(form_,formEnabledMovesByGroup_);
        nextPart();
        display(_api,form_, MessagesPkBean.TEAM,bean.getEnabledMovesWhileSendingFoeUses(),MessagesFightTeam.M_P_92_ENBALED_MOVES_SEND);
        AbsPanel formEnabledMovesWhileSendingFoeUses_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formEnabledMovesWhileSendingFoeUses_, MessagesPkBean.TEAM,bean.getEnabledMovesWhileSendingFoeUses(), MessagesFightTeam.M_P_92_ENBALED_MOVES_SEND_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_SEND_VALUE);
        new BeanDisplayMap<String, LgInt>(new BeanDisplayString(),new BeanDisplayLgInt()).display(this,_api,formEnabledMovesWhileSendingFoeUses_,bean.getEnabledMovesWhileSendingFoeUses(),2);
        feedParents(form_,formEnabledMovesWhileSendingFoeUses_);
        nextPart();
        display(_api,form_, MessagesPkBean.TEAM,bean.getNbUsesMoves(),MessagesFightTeam.M_P_92_ENBALED_MOVES_USES);
        AbsPanel formNbUsesMoves_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formNbUsesMoves_, MessagesPkBean.TEAM,bean.getNbUsesMoves(), MessagesFightTeam.M_P_92_ENBALED_MOVES_USES_KEY, MessagesFightTeam.M_P_92_ENBALED_MOVES_USES_VALUE);
        new BeanDisplayMap<String, Long>(new BeanDisplayString(),new BeanDisplayLong()).display(this,_api,formNbUsesMoves_,bean.getNbUsesMoves(),2);
        feedParents(form_,formNbUsesMoves_);
        nextPart();
        healPart(_api, form_);
        antPart(_api, form_);
        playerFoePart(_api, form_);
        return form_;
    }

    private void healPart(AbstractProgramInfos _api, AbsPanel _form) {
        display(_api, _form, MessagesPkBean.TEAM,bean.getHealAfter(),MessagesFightTeam.M_P_92_HEAL_AFTER);
        AbsPanel formHealAfter_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formHealAfter_, MessagesPkBean.TEAM,bean.getHealAfter(), MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_ONE, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_TWO, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_RD, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_USED_CURRENT, MessagesFightTeam.M_P_92_HEAL_AFTER_KEY_USED_LAST);
        for (EntryCust<String,IntTreeMap<StacksOfUses>> e:bean.getHealAfter().entryList()) {
            for (EntryCust<Integer, StacksOfUses> f:e.getValue().entryList()) {
                formatMessageDir(_api,formHealAfter_, _api.getCompoFactory().newGridCts(),e.getKey());
                formatMessageDir(_api,formHealAfter_, _api.getCompoFactory().newGridCts(),Long.toString(f.getKey()));
                formatMessageDir(_api,formHealAfter_, _api.getCompoFactory().newGridCts(),Long.toString(f.getValue().getNbRounds()));
                if (f.getValue().isFirstStacked()) {
                    formatMessage(_api,formHealAfter_, _api.getCompoFactory().newGridCts(), MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_Y);
                } else {
                    formatMessage(_api,formHealAfter_, _api.getCompoFactory().newGridCts(), MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_N);
                }
                if (f.getValue().isLastStacked()) {
                    formatMessage(_api,formHealAfter_,remainder(_api), MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_Y);
                } else {
                    formatMessage(_api,formHealAfter_,remainder(_api), MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_HEAL_AFTER_N);
                }
            }
        }
        feedParents(_form,formHealAfter_);
        nextPart();
    }

    private void antPart(AbstractProgramInfos _api, AbsPanel _form) {
        display(_api, _form, MessagesPkBean.TEAM,bean.getMovesAnticipation(),MessagesFightTeam.M_P_92_MOVE_ANT);
        AbsPanel formMovesAnticipation_ = _api.getCompoFactory().newGrid();
        headerCols(_api,formMovesAnticipation_, MessagesPkBean.TEAM,bean.getMovesAnticipation(), MessagesFightTeam.M_P_92_MOVE_ANT_KEY_ONE, MessagesFightTeam.M_P_92_MOVE_ANT_KEY_TWO, MessagesFightTeam.M_P_92_MOVE_ANT_TEAM, MessagesFightTeam.M_P_92_MOVE_ANT_GROUND, MessagesFightTeam.M_P_92_MOVE_ANT_DAMAGE, MessagesFightTeam.M_P_92_MOVE_ANT_NB_ROUND);
        for (EntryCust<String, IntTreeMap<Anticipation>> e:bean.getMovesAnticipation().entryList()) {
            for (EntryCust<Integer, Anticipation> f:e.getValue().entryList()) {
                formatMessageDir(_api,formMovesAnticipation_, _api.getCompoFactory().newGridCts(),e.getKey());
                formatMessageDir(_api,formMovesAnticipation_, _api.getCompoFactory().newGridCts(),Long.toString(f.getKey()));
                if (f.getValue().getTargetPosition().getTeam() == Fight.CST_FOE) {
                    formatMessage(_api,formMovesAnticipation_,_api.getCompoFactory().newGridCts(), MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_FOE);
                } else if (f.getValue().getTargetPosition().getTeam() == Fight.CST_PLAYER) {
                    formatMessage(_api,formMovesAnticipation_,_api.getCompoFactory().newGridCts(), MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_PLAYER);
                } else {
                    formatMessage(_api,formMovesAnticipation_,_api.getCompoFactory().newGridCts(), MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
                }
                enabledAnt(_api, formMovesAnticipation_, f.getValue());
                formatMessageDir(_api,formMovesAnticipation_, _api.getCompoFactory().newGridCts(),f.getValue().getDamage().toNumberString());
                if (f.getValue().isIncrementing()) {
                    formatMessageDir(_api,formMovesAnticipation_,AbsBeanRender.remainder(_api),Long.toString(f.getValue().getNbRounds()));
                } else {
                    formatMessage(_api,formMovesAnticipation_,AbsBeanRender.remainder(_api), MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
                }
            }
        }
        feedParents(_form,formMovesAnticipation_);
        nextPart();
    }

    private void enabledAnt(AbstractProgramInfos _api, AbsPanel _form, Anticipation _f) {
        if (_f.getTargetPosition().isEnabled()) {
            formatMessageDir(_api, _form, _api.getCompoFactory().newGridCts(),Long.toString(_f.getTargetPosition().getPosition()));
        } else {
            formatMessage(_api, _form, _api.getCompoFactory().newGridCts(), MessagesPkBean.TEAM,MessagesFightTeam.M_P_92_MOVE_ANT_NO);
        }
    }

    private void playerFoePart(AbstractProgramInfos _api, AbsPanel _form) {
        display(_api, _form, MessagesPkBean.TEAM,bean.getPlayerFightersAgainstFoe(),MessagesFightTeam.M_P_92_PLAYER_FOE);
        AbsPanel formPlayerFoe_ = _api.getCompoFactory().newGrid();
        for (EntryCust<Integer, FighterAgainstFoes> e:bean.getPlayerFightersAgainstFoe().entryList()) {
            nextPart();
            AbsPanel lineFighters_ = _api.getCompoFactory().newLineBox();
            paintMetaLabelDisk(_api,lineFighters_);
            formatMessageDir(_api,lineFighters_,e.getValue().getName());
            AbsPanel fightersContent_ = _api.getCompoFactory().newPageBox();
            for (EntryCust<Integer, String> p:e.getValue().getFoes().entryList()) {
                AbsPanel lineFighter_ = _api.getCompoFactory().newLineBox();
                paintMetaLabelDisk(_api,lineFighter_);
                formatMessageDir(_api,lineFighter_,p.getValue());
                feedParents(fightersContent_,lineFighter_);
                getMetaSearchableContents().add(new MetaSearchableContent(null, getPartGroup(), getRowGroup()));
            }
            feedParents(lineFighters_,fightersContent_);
            feedParents(formPlayerFoe_,lineFighters_);
        }
        feedParents(_form,formPlayerFoe_);
        nextPart();
    }

    public static StringMap<String> file(AbstractProgramInfos _api) {
        return filesFight(_api).getVal(MessagesPkBean.TEAM).getMapping();
    }
}
