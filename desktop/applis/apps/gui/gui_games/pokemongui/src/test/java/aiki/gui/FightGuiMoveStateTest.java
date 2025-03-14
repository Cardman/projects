package aiki.gui;

import aiki.db.DataBase;
import aiki.fight.items.Ball;
import aiki.fight.items.HealingItem;
import aiki.fight.items.HealingPp;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.effects.EffectBatonPass;
import aiki.fight.moves.effects.EffectDamage;
import aiki.fight.moves.effects.EffectSwitchPosition;
import aiki.fight.moves.enums.TargetChoice;
import aiki.game.Game;
import aiki.game.UsesOfMove;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.actions.ActionHeal;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.actions.ChosenMove;
import aiki.game.fight.actions.ChosenReplacing;
import aiki.game.fight.enums.ActionType;
import aiki.gui.components.PaginatorHealingItem;
import aiki.gui.dialogs.SelectHealingItem;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.levels.AreaApparition;
import aiki.map.places.Road;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.gui.AbsCustComponent;
import code.maths.LgInt;
import code.maths.Rate;
import code.mock.MockCustComponent;
import code.util.IdList;
import code.util.StringMap;
import org.junit.Test;

public final class FightGuiMoveStateTest extends InitDbGuiAiki {

    @Test
    public void state1() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void state2() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(18, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
    }

    @Test
    public void state3() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getBattle().getBattle().getNicknameField().setText("_");
        assertEq("_",window_.getBattle().getBattle().getNicknameField().getText());
    }

    @Test
    public void state4() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().select(-1);
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().events();
        window_.getBattle().getBattle().getNicknameField().setText("_");
        assertEq("_",window_.getBattle().getBattle().getNicknameField().getText());
    }

    @Test
    public void state5() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStOneMove(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(17, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertFalse(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
    }

    @Test
    public void state6() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(18, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
    }

    @Test
    public void state7() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(18, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
    }

    @Test
    public void state8() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.SWITCH)));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(17, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanelSub().getListe().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
    }

    @Test
    public void state9() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoice(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(20, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getTargets().getFoeTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1).getPaintableLabel()));
    }

    @Test
    public void state10() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoice(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(20, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getTargets().getFoeTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1).getPaintableLabel()));
    }

    @Test
    public void state11() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoice(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(20, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getTargets().getFoeTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1).getPaintableLabel()));
        assertEq(ECLAIR_4,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getFirstChosenMove());
        assertEq(1,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getChosenTargets().size());
        assertEq(0,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getChosenTargets().get(0).getPosition());
        assertEq(Fight.CST_FOE,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getChosenTargets().get(0).getTeam());
    }

    @Test
    public void state12() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoice2(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(20, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(3,window_.getBattle().getBattle().getTargets().getPlayerTargets().size());
        assertFalse(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(2).getPaintableLabel()));
    }

    @Test
    public void state13() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoice2(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(1));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(20, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(3,window_.getBattle().getBattle().getTargets().getPlayerTargets().size());
        assertFalse(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(2).getPaintableLabel()));
    }

    @Test
    public void state14() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoice2(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(1));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(20, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(3,window_.getBattle().getBattle().getTargets().getPlayerTargets().size());
        assertFalse(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(2).getPaintableLabel()));
        assertEq(ECLAIR_4,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getFirstChosenMove());
        assertEq(1,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getChosenTargets().size());
        assertEq(1,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getChosenTargets().get(0).getPosition());
        assertEq(Fight.CST_PLAYER,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getChosenTargets().get(0).getTeam());
    }

    @Test
    public void state15() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.SWITCH)));
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(17, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanelSub().getListe().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(1,((ChosenReplacing)window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getAction()).getSubstitute());
    }

    @Test
    public void state16() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoiceSoloKos(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(20, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(1,window_.getBattle().getBattle().getTargets().getFoeTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0).getPaintableLabel()));
        assertEq(1,window_.getBattle().getBattle().getTargets().getPlayerTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(0).getPaintableLabel()));
    }

    @Test
    public void state17() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoiceSoloKos(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(20, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(1,window_.getBattle().getBattle().getTargets().getFoeTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0).getPaintableLabel()));
        assertEq(1,window_.getBattle().getBattle().getTargets().getPlayerTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(0).getPaintableLabel()));
    }

    @Test
    public void state18() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoiceSolo(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(20, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(1,window_.getBattle().getBattle().getTargets().getFoeTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0).getPaintableLabel()));
        assertEq(1,window_.getBattle().getBattle().getTargets().getPlayerTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(0).getPaintableLabel()));
    }

    @Test
    public void state19() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoiceSolo(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(20, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(1,window_.getBattle().getBattle().getTargets().getFoeTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0).getPaintableLabel()));
        assertEq(1,window_.getBattle().getBattle().getTargets().getPlayerTargets().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(0).getPaintableLabel()));
    }

    @Test
    public void state20() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoice3(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(21, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(4,window_.getBattle().getBattle().getTargets().getPlayerTargets().size());
        assertFalse(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(3).getPaintableLabel()));
    }

    @Test
    public void state21() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoice3(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(1));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(21, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(4,window_.getBattle().getBattle().getTargets().getPlayerTargets().size());
        assertFalse(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(3).getPaintableLabel()));
    }

    @Test
    public void state22() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoice3(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(1));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(21, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(4,window_.getBattle().getBattle().getTargets().getPlayerTargets().size());
        assertFalse(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTargets().getPlayerTargets().get(3).getPaintableLabel()));
        assertEq(ECLAIR_4,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getFirstChosenMove());
        assertEq(1,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getChosenTargets().size());
        assertEq(1,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getChosenTargets().get(0).getPosition());
        assertEq(Fight.CST_PLAYER,window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getChosenTargets().get(0).getTeam());
    }

    @Test
    public void state23() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStHeal(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.HEALING)));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(17, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getChooseHealItem()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
    }

    @Test
    public void state24() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStHeal(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.HEALING)));
        tryClick(window_.getBattle().getBattle().getChooseHealItem());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(sel_.getCancelButton());
        assertFalse(sel_.getSelectDial().isVisible());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(17, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getChooseHealItem()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
    }

    @Test
    public void state25() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStHeal(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.HEALING)));
        tryClick(window_.getBattle().getBattle().getChooseHealItem());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        tryClick(sel_.getOkButton());
        assertFalse(sel_.getSelectDial().isVisible());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(17, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getChooseHealItem()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
    }

    @Test
    public void state26() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStHeal(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.HEALING)));
        tryClick(window_.getBattle().getBattle().getChooseHealItem());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("graisse");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(sel_.getSelectDial().isVisible());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(18, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getChooseHealItem()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertFalse(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
    }

    @Test
    public void state27() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStHeal(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.HEALING)));
        tryClick(window_.getBattle().getBattle().getChooseHealItem());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("grace");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(sel_.getSelectDial().isVisible());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(17, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getChooseHealItem()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
    }

    @Test
    public void state28() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStHeal(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.HEALING)));
        tryClick(window_.getBattle().getBattle().getChooseHealItem());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("graisse");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        assertFalse(sel_.getSelectDial().isVisible());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(18, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getChooseHealItem()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertFalse(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(ECLAIR_4,((ChosenMove)window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getAction()).getFirstChosenMove());
        assertEq(HUILE,((ActionHeal)window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getAction()).getChosenHealingItem());
    }
    @Test
    public void state29() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoiceBatonPass(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(19, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanelSub().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
    }
    @Test
    public void state30() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoiceBatonPass(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(19, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanelSub().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(4,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(3).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMovesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMovesLabels().get(1).getPaintableLabel()));
        assertEq(ECLAIR_4,((ActionMove)window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getAction()).getFirstChosenMove());
        assertEq(2,((ActionMove)window_.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).getAction()).getSubstitute());
    }

    @Test
    public void state31() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStChoiceSoloKos(window_);
        window_.getBattle().getBattle().getFighterBackPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(14, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getActionsLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getActionsLabels().get(1).getPaintableLabel()));
    }

    @Test
    public void front() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getBattle().getPaintableLabel().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        assertTrue(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }

    private static void coreDataBaseAttSt(WindowAiki _window) {
        loadRom(_window, coreDataBaseAttSt(def()));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove( 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStOneMove(WindowAiki _window) {
        loadRom(_window, coreDataBaseAttSt(def()));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove( 1));
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().getValue(1).setCurrent( 0);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStHeal(WindowAiki _window) {
        //_window
        HealingPp single_ = Instances.newHealingPp();
        single_.setHealedMovePp(5);
        HealingPp all_ = Instances.newHealingPp();
        all_.setHealingAllMovesFullpp(5);
        loadRom(_window, coreDataBaseAttStHeal(single_, all_));
        _window.getFacade().healTr();
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove( 1));
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().getValue(1).setCurrent( 0);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getItem(HUILE);
        game_.getPlayer().getItem(HUILE_MAX);
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStChoice(WindowAiki _window) {
        DamagingMoveData md_ = Instances.newDamagingMoveData();
        md_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectDamage dame_ = Instances.newEffectDamage();
        dame_.setTargetChoice(TargetChoice.ANY_FOE);
        dame_.setConstDamage(true);
        dame_.setPower("1");
        md_.getEffects().add(dame_);
        loadRom(_window, coreDataBaseAttSt2(md_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove( 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStChoiceSoloKos(WindowAiki _window) {
        DamagingMoveData md_ = Instances.newDamagingMoveData();
        md_.setTargetChoice(TargetChoice.UNIQUE_IMPORTE);
        EffectDamage dame_ = Instances.newEffectDamage();
        dame_.setTargetChoice(TargetChoice.UNIQUE_IMPORTE);
        dame_.setConstDamage(true);
        dame_.setPower("1");
        md_.getEffects().add(dame_);
        loadRom(_window, coreDataBaseAttSt2(md_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove( 1));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
        _window.getFacade().getFight().getFighter(Fight.toFoeFighter( 1)).setRemainingHp(Rate.zero());
        _window.getFacade().getFight().getFighter(Fight.toFoeFighter( 1)).setGroundPlace(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toFoeFighter( 1)).setGroundPlaceSubst(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 1)).setRemainingHp(Rate.zero());
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 1)).setGroundPlace(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 1)).setGroundPlaceSubst(Fighter.BACK);
        _window.getFacade().getFight().getFirstPositPlayerFighters().put(1,Fighter.BACK);
        _window.getFacade().getFight().getFirstPositFoeFighters().put(1,Fighter.BACK);
        _window.getBattle().setComments();
        _window.getBattle().initializeFight(true);
    }
    private static void coreDataBaseAttStChoiceBatonPass(WindowAiki _window) {
        DamagingMoveData md_ = Instances.newDamagingMoveData();
        md_.setTargetChoice(TargetChoice.LANCEUR);
        EffectBatonPass dame_ = Instances.newEffectBatonPass();
        dame_.setTargetChoice(TargetChoice.LANCEUR);
        md_.getEffects().add(dame_);
        loadRom(_window, coreDataBaseAttSt2(md_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove( 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStChoiceSolo(WindowAiki _window) {
        DamagingMoveData md_ = Instances.newDamagingMoveData();
        md_.setTargetChoice(TargetChoice.UNIQUE_IMPORTE);
        EffectDamage dame_ = Instances.newEffectDamage();
        dame_.setTargetChoice(TargetChoice.UNIQUE_IMPORTE);
        dame_.setConstDamage(true);
        dame_.setPower("1");
        md_.getEffects().add(dame_);
        loadRom(_window, coreDataBaseAttSt(md_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove( 1));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStChoice2(WindowAiki _window) {
        DamagingMoveData md_ = Instances.newDamagingMoveData();
        md_.setTargetChoice(TargetChoice.ALLIE);
        EffectSwitchPosition dame_ = Instances.newEffectSwitchPosition();
        dame_.setTargetChoice(TargetChoice.ALLIE);
        md_.getEffects().add(dame_);
        loadRom(_window, coreDataBaseAttSt3(md_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove( 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStChoice3(WindowAiki _window) {
        DamagingMoveData md_ = Instances.newDamagingMoveData();
        md_.setTargetChoice(TargetChoice.ALLIE);
        EffectSwitchPosition dame_ = Instances.newEffectSwitchPosition();
        dame_.setTargetChoice(TargetChoice.ALLIE);
        md_.getEffects().add(dame_);
        loadRom(_window, coreDataBaseAttSt4(md_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove( 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    public static DataBase coreDataBaseAttSt(MoveData _md) {
        return coreDataBaseAttSt(_md, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttSt(MoveData _md, Ball _ball, String _rateCatch, String _rateFlee) {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,_rateCatch,_rateFlee,"1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        data_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", def());
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);

        return ball_;
    }

    public static DataBase coreDataBaseAttStHeal(HealingItem _hi1, HealingItem _hi2) {
        return coreDataBaseAttStHeal(def(),_hi1,_hi2, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttStHeal(MoveData _md, HealingItem _hi1, HealingItem _hi2, Ball _ball, String _rateCatch, String _rateFlee) {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,_rateCatch,_rateFlee,"1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        data_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", def());
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(withIt(withIt(res_, HUILE, trsIt_, "graisse", _hi1,trsDesc_,"toi"), HUILE_MAX, trsIt_, "grace", _hi2,trsDesc_,"toi"), POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);

        return ball_;
    }

    public static DataBase coreDataBaseAttSt2(MoveData _md) {
        return coreDataBaseAttSt2(_md, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttSt2(MoveData _md, Ball _ball, String _rateCatch, String _rateFlee) {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,_rateCatch,_rateFlee,"1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        data_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", def());
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea2(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);

        return ball_;
    }
    public static DataBase coreDataBaseAttSt3(MoveData _md) {
        return coreDataBaseAttSt3(_md, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttSt3(MoveData _md, Ball _ball, String _rateCatch, String _rateFlee) {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,_rateCatch,_rateFlee,"1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        data_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", def());
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea3(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);

        return ball_;
    }

    public static DataBase coreDataBaseAttSt4(MoveData _md) {
        return coreDataBaseAttSt4(_md, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttSt4(MoveData _md, Ball _ball, String _rateCatch, String _rateFlee) {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,_rateCatch,_rateFlee,"1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        data_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", def());
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea4(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);

        return ball_;
    }

    public static void initBeginAttSt(DataBase _data) {
        DataMap map_ = _data.getMap();
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel( 1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 1));
    }
    public static DataBase withMvGene(DataBase _data, String _key, StringMap<String> _trs, String _val, MoveData _d) {
        _data.completeQuickMembers(_key, _d);
        _trs.addEntry(_key,_val);
        return _data;
    }

    public static Road withArea2(Road _road) {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setAvgNbSteps( 1);
        a_.setMultFight( 2);
        a_.getWildPokemon().add(wild());
        a_.getWildPokemonFishing().add(wild());
        _road.getLevelRoad().getWildPokemonAreas().add(a_);
        return _road;
    }

    public static Road withArea3(Road _road) {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setAvgNbSteps( 1);
        a_.setMultFight( 3);
        a_.getWildPokemon().add(wild());
        a_.getWildPokemonFishing().add(wild());
        _road.getLevelRoad().getWildPokemonAreas().add(a_);
        return _road;
    }

    public static Road withArea4(Road _road) {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setAvgNbSteps( 1);
        a_.setMultFight( 4);
        a_.getWildPokemon().add(wild());
        a_.getWildPokemonFishing().add(wild());
        _road.getLevelRoad().getWildPokemonAreas().add(a_);
        return _road;
    }
}
