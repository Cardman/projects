package aiki.gui;

import aiki.db.*;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.items.HealingPp;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.SwitchType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.util.LevelMove;
import aiki.game.Game;
import aiki.game.UsesOfMove;
import aiki.game.fight.CatchingBallFoeAction;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.FighterPosition;
import aiki.game.fight.actions.ActionSwitch;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.FightState;
import aiki.gui.components.PaginatorHealingItem;
import aiki.gui.dialogs.SelectHealingItem;
import aiki.instances.*;
import aiki.map.DataMap;
import aiki.map.characters.*;
import aiki.map.characters.enums.*;
import aiki.map.enums.*;
import aiki.map.levels.AreaApparition;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.*;
import code.gui.AbsCustComponent;
import code.gui.GuiConstants;
import code.maths.*;
import code.maths.litteral.MbOperationNode;
import code.mock.MockCustComponent;
import code.mock.MockThreadFactory;
import code.util.*;
import code.util.core.BoolVal;
import org.junit.Test;

public final class FightGuiRoundTest extends InitDbGuiAiki {

    @Test
    public void eff1() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff2() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer2(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff3() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff4() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff5() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStFast(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff6() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStFastSelf(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff7() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.SWITCH)));
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().events();
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff8() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.SWITCH)));
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getFoeTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getFoeTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff9() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStatus(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff10() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStatusOther(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff11() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStatusSelf(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff12() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSwitchPos(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff13() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamage(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff14() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageRate(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(Rate.divide(window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).getRemainingHp(),new Rate(2)));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 1)).setRemainingHp(Rate.divide(window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte)1)).getRemainingHp(),new Rate(2)));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(Rate.divide(window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).getRemainingHp(),new Rate(2)));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(Rate.divide(window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte)1)).getRemainingHp(),new Rate(2)));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff15() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageRate2(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff16() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageRate3(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(8, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(3,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
    }

    @Test
    public void eff17() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageRate4(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff18() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageRate5(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(1, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        tryClick(window_.getBattle().getBattle().getValidateActions());
    }

    @Test
    public void eff19() {
        WindowAiki window_ = newFight();
        coreDataBaseAttAcc(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff20() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 1)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(8, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(3,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
    }

    @Test
    public void eff21() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo2(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 1)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(8, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(3,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
    }

    @Test
    public void eff22() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo2(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(8, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(3,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(2).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
    }

    @Test
    public void eff23() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo3(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(1, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff24() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainerHealBack(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterBackPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterBackPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.HEALING)));
        tryClick(window_.getBattle().getBattle().getChooseHealItem());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("graisse");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff25() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainerDual(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff26() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainerDualKoPlayer(window_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(1, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff27() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo4(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(2, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertEq(3,window_.getBattle().getBattle().getFighterPanel().getListe().size());
    }

    @Test
    public void eff28() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo4(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(2, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertEq(3,window_.getBattle().getBattle().getFighterPanel().getListe().size());
    }

    @Test
    public void eff29() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo4(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPokemonPanel().getListe().getGlobal()));
        assertEq(1,window_.getBattle().getBattle().getAbilityLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getAbilityLabels().get(0).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMoveLearnList().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMoveLearnList().get(0).getComponent()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMoveLearnList().get(1).getComponent()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertEq(3,window_.getBattle().getBattle().getFighterPanel().getListe().size());
        assertEq(2,window_.getBattle().getBattle().getPokemonPanel().getListe().size());
    }

    @Test
    public void eff30() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo4(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterPanel().getListe().events();
        window_.getBattle().getBattle().getPokemonPanel().getListe().select(1);
        window_.getBattle().getBattle().getPokemonPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPokemonPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getAbilityLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getAbilityLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getAbilityLabels().get(1).getPaintableLabel()));
        assertEq(6,window_.getBattle().getBattle().getMoveLearnList().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMoveLearnList().get(0).getComponent()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMoveLearnList().get(1).getComponent()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMoveLearnList().get(2).getComponent()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMoveLearnList().get(3).getComponent()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMoveLearnList().get(4).getComponent()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMoveLearnList().get(5).getComponent()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertEq(3,window_.getBattle().getBattle().getFighterPanel().getListe().size());
        assertEq(2,window_.getBattle().getBattle().getPokemonPanel().getListe().size());
    }

    @Test
    public void eff31() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo4(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterPanel().getListe().events();
        window_.getBattle().getBattle().getPokemonPanel().getListe().select(1);
        window_.getBattle().getBattle().getPokemonPanel().getListe().events();
        window_.getBattle().getBattle().getPokemonPanel().getListe().select(0);
        window_.getBattle().getBattle().getPokemonPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPokemonPanel().getListe().getGlobal()));
        assertEq(1,window_.getBattle().getBattle().getAbilityLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getAbilityLabels().get(0).getPaintableLabel()));
        assertEq(2,window_.getBattle().getBattle().getMoveLearnList().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMoveLearnList().get(0).getComponent()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getMoveLearnList().get(1).getComponent()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertEq(3,window_.getBattle().getBattle().getFighterPanel().getListe().size());
        assertEq(2,window_.getBattle().getBattle().getPokemonPanel().getListe().size());
    }

    @Test
    public void eff32() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo4(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterPanel().getListe().events();
        window_.getBattle().getBattle().getPokemonPanel().getListe().select(1);
        window_.getBattle().getBattle().getPokemonPanel().getListe().events();
        window_.getBattle().getBattle().getPokemonPanel().getListe().select(-1);
        window_.getBattle().getBattle().getPokemonPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPokemonPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertEq(3,window_.getBattle().getBattle().getFighterPanel().getListe().size());
        assertEq(2,window_.getBattle().getBattle().getPokemonPanel().getListe().size());
    }

    @Test
    public void eff33() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo4(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterPanel().getListe().events();
        window_.getBattle().getBattle().getPokemonPanel().getListe().select(1);
        window_.getBattle().getBattle().getPokemonPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getAbilityLabels().get(1));
        window_.getBattle().getBattle().getMoveLearnList().get(0).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        window_.getBattle().getBattle().getMoveLearnList().get(1).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        window_.getBattle().getBattle().getMoveLearnList().get(2).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        window_.getBattle().getBattle().getMoveLearnList().get(3).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        window_.getBattle().getBattle().getMoveLearnList().get(4).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        window_.getBattle().getBattle().getMoveLearnList().get(5).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryClick(window_.getBattle().getBattle().getValidateActions());
        assertFalse(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }

    @Test
    public void eff34() {
        WindowAiki window_ = newFight();
        coreDataBaseAttDamageKo4(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterPanel().getListe().events();
        window_.getBattle().getBattle().getPokemonPanel().getListe().select(1);
        window_.getBattle().getBattle().getPokemonPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getAbilityLabels().get(1));
        window_.getBattle().getBattle().getMoveLearnList().get(2).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        window_.getBattle().getBattle().getMoveLearnList().get(3).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        window_.getBattle().getBattle().getMoveLearnList().get(4).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        window_.getBattle().getBattle().getMoveLearnList().get(5).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        assertTrue(window_.getFacade().isErrorFight());
    }

    @Test
    public void eff35() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff36() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getPlacesLabels().get(0));
        window_.getBattle().getBattle().getFighterBackPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanel().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff37() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterBackPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }
    @Test
    public void eff38() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterBackPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getPlacesLabels().get(1));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        window_.getBattle().getBattle().getFighterBackPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void eff39() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(1, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        assertFalse(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }
    @Test
    public void eff40() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getPlacesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        assertTrue(window_.getFacade().isErrorFight());
    }

    @Test
    public void eff41() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBall(window_, MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__FOE_PK_MAX_HP,VAR__FOE_PK_REMOTE_HP),2)", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff42() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBall(window_, MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__FOE_PK_MAX_HP,VAR__FOE_PK_REMOTE_HP),2)", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getCatchBall());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff43() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBall(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getCatchBall());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBallEnd()));
    }

    @Test
    public void eff44() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBall(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_SAUVAGE_VITESSE,VAR__PK_UT_VITESSE),1)");
        window_.getFacade().getGame().getDifficulty().setStillPossibleFlee(false);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getFlee());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void eff45() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBall(window_, "1", "1");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getFlee());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        assertFalse(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }

    @Test
    public void eff46() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBall2(window_, MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__FOE_PK_MAX_HP,VAR__FOE_PK_REMOTE_HP),2)", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBallEnd()));
    }

    @Test
    public void eff47() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBallTwo(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        tryClick(window_.getBattle().getBattle().getCatchBall());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBallEnd()));
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        tryClick(window_.getBattle().getBattle().getCatchBallEnd());
        assertEq(3,window_.getFacade().getPlayer().getTeam().size());
        assertFalse(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }

    @Test
    public void eff48() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBallTwo(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> first_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(8, first_.size());
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(3,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(2).getPaintableLabel()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFlee()));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getPlacesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> second_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, second_.size());
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        tryClick(window_.getBattle().getBattle().getCatchBall());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> third_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, third_.size());
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getCatchBallEnd()));
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        tryClick(window_.getBattle().getBattle().getCatchBallEnd());
        assertEq(3,window_.getFacade().getPlayer().getTeam().size());
        assertFalse(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }

    @Test
    public void eff49() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBall(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_SAUVAGE_VITESSE,VAR__PK_UT_VITESSE),1)");
        window_.getFacade().getGame().getDifficulty().setStillPossibleFlee(false);
        window_.getBattle().getBattle().getFighterFleePanel().getListe().select(-1);
        window_.getBattle().getBattle().getFighterFleePanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
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
    public void eff50() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBallTwo(window_, "1", "1");
        FighterPosition fp_ = window_.getFacade().getSingleFoeToBeCaught(false, 0);
        CatchingBallFoeAction cat_ = window_.getFacade().getGame().getFight().getCatchingBalls().get(fp_.getFirstPosit());
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        cat_.setPlayer(Fighter.BACK);
        window_.getBattle().getBattle().getFighterCatchingPanel().getListe().select(-1);
        window_.getBattle().getBattle().getFighterCatchingPanel().getListe().events();
        assertEq(Fighter.BACK,cat_.getPlayer());
    }

    @Test
    public void eff51() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBallTwo(window_, "1", "1");
        FighterPosition fp_ = window_.getFacade().getSingleFoeToBeCaught(false, 0);
        CatchingBallFoeAction cat_ = window_.getFacade().getGame().getFight().getCatchingBalls().get(fp_.getFirstPosit());
        cat_.setPlayer(Fighter.BACK);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(-1);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getFighterCatchingPanel().getListe().select(-1);
        window_.getBattle().getBattle().getFighterCatchingPanel().getListe().events();
        assertEq(Fighter.BACK,cat_.getPlayer());
    }

    @Test
    public void eff52() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBallTwo(window_, "1", "1");
        FighterPosition fp_ = window_.getFacade().getSingleFoeToBeCaught(false, 0);
        CatchingBallFoeAction cat_ = window_.getFacade().getGame().getFight().getCatchingBalls().get(fp_.getFirstPosit());
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        cat_.setPlayer(Fighter.BACK);
        window_.getBattle().getBattle().getFighterCatchingPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCatchingPanel().getListe().events();
        assertEq(0, cat_.getPlayer());
    }

    @Test
    public void eff53() {
        WindowAiki window_ = newFight();
        coreDataBaseAttChangeInRound(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
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
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, tr_.size());
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void eff54() {
        WindowAiki window_ = newFight();
        coreDataBaseAttChangeInRound(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
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
        window_.getBattle().getBattle().getFighterBackPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, tr_.size());
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void eff55() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainerTwoFighters(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAnNoCheck((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, tr_.size());
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void eff56() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainerThreeFighters(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAnNoCheck((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, tr_.size());
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void eff57() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainerFourFighters(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAnNoCheck((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, tr_.size());
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void eff58() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBallTwo(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        tryClick(window_.getBattle().getBattle().getCatchBall());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBallEnd()));
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getTeamField());
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getCatchBallEnd());
        assertEq(2,window_.getFacade().getPlayer().getTeam().size());
        assertEq(1,window_.getFacade().getPlayer().getBox().size());
        assertFalse(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }

    @Test
    public void eff59() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBallTwo(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> first_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(8, first_.size());
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(3,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(2).getPaintableLabel()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFlee()));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getPlacesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> second_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, second_.size());
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        tryClick(window_.getBattle().getBattle().getCatchBall());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> third_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, third_.size());
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getCatchBallEnd()));
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getTeamField());
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getCatchBallEnd());
        assertEq(2,window_.getFacade().getPlayer().getTeam().size());
        assertEq(1,window_.getFacade().getPlayer().getBox().size());
        assertFalse(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }

    @Test
    public void eff60() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBallTwo(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        tryClick(window_.getBattle().getBattle().getCatchBall());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBallEnd()));
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getTeamField());
        tryClick(window_.getBattle().getBattle().getTeamField());
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().select(-1);
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getTeamField());
        tryClick(window_.getBattle().getBattle().getCatchBallEnd());
        assertEq(3,window_.getFacade().getPlayer().getTeam().size());
        assertEq(0,window_.getFacade().getPlayer().getBox().size());
        assertFalse(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }

    @Test
    public void eff61() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStBallTwo(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> first_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(8, first_.size());
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(3,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(2).getPaintableLabel()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(first_.containsObj(window_.getBattle().getBattle().getFlee()));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getPlacesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> second_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, second_.size());
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(second_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        tryClick(window_.getBattle().getBattle().getCatchBall());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        IdList<AbsCustComponent> third_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, third_.size());
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(third_.containsObj(window_.getBattle().getBattle().getCatchBallEnd()));
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterCaughtPanel().getListe().events();
        window_.getBattle().getBattle().getBallPanel().getListeBall().select(0);
        window_.getBattle().getBattle().getBallPanel().getListeBall().events();
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getTeamField());
        tryClick(window_.getBattle().getBattle().getTeamField());
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().select(-1);
        window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getTeamField());
        tryClick(window_.getBattle().getBattle().getCatchBallEnd());
        assertEq(3,window_.getFacade().getPlayer().getTeam().size());
        assertEq(0,window_.getFacade().getPlayer().getBox().size());
        assertFalse(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }
    @Test
    public void effNo1() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void effNo2() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer2(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void effNo3() {
        WindowAiki window_ = newFight();
        coreDataBaseAttSt(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void effNo4() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        ActionSwitch as_ = new ActionSwitch();
        as_.setSubstitute((byte) 1);
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setAction(as_);
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void effNo5() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStFast(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void effNo6() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStFastSelf(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void effNo7() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.SWITCH)));
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().events();
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(3, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void effNo8() {
        WindowAiki window_ = newFight();
        coreDataBaseTrainer(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getActionsLabels().get(window_.getFacade().getFight().getTemp().getPossibleActionsCurFighter().indexOfObj(ActionType.SWITCH)));
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanelSub().getListe().events();
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 1)).setRemainingHp(new Rate("1"));
        window_.getFacade().getFight().getFoeTeam().getEnabledMovesWhileSendingFoe().setValue(0, BoolVal.TRUE);
        window_.getFacade().getFight().getFoeTeam().getEnabledMovesWhileSendingFoeUses().setValue(0, LgInt.one());
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertEq(2,window_.getBattle().getBattle().getPlacesLabels().size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(0).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getPlacesLabels().get(1).getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void effNo9() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStatus(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void effNo10() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStatusOther(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void effNo11() {
        WindowAiki window_ = newFight();
        coreDataBaseAttStatusSelf(window_);
        window_.getLoadingConf().setEnableAnimation(false);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void effNo12() {
        WindowAiki window_ = newFight();
        window_.getLoadingConf().setEnableAnimation(false);
        coreDataBaseTrainerDualKoPlayer(window_);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(1, tr_.size());
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(1, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void effNo13() {
        WindowAiki window_ = newFight();
        window_.getLoadingConf().setEnableAnimation(false);
        coreDataBaseAttDamageKo3(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(0));
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(1);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(0));
        tryClick(window_.getBattle().getBattle().getTargets().getFoeTargets().get(1));
        window_.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        window_.getFacade().getFight().getFighter(Fight.toFoeFighter((byte) 0)).setRemainingHp(new Rate("1/2"));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(1, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getValidateActions()));
    }

    @Test
    public void effNo14() {
        WindowAiki window_ = newFight();
        window_.getLoadingConf().setEnableAnimation(false);
        coreDataBaseAttStBall(window_, MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__FOE_PK_MAX_HP,VAR__FOE_PK_REMOTE_HP),2)", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getCatchBall());
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
    public void effNo15() {
        WindowAiki window_ = newFight();
        window_.getLoadingConf().setEnableAnimation(false);
        coreDataBaseAttStBall(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getCatchBall());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBallEnd()));
    }

    @Test
    public void effNo16() {
        WindowAiki window_ = newFight();
        window_.getLoadingConf().setEnableAnimation(false);
        coreDataBaseAttStBall(window_, "1", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_SAUVAGE_VITESSE,VAR__PK_UT_VITESSE),1)");
        window_.getFacade().getGame().getDifficulty().setStillPossibleFlee(false);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getFlee());
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
    public void effNo17() {
        WindowAiki window_ = newFight();
        window_.getLoadingConf().setEnableAnimation(false);
        coreDataBaseAttStBall(window_, "1", "1");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getFlee());
        assertFalse(window_.getBattle().getBattle().getCommonFrame().isVisible());
    }

    @Test
    public void effNo18() {
        WindowAiki window_ = newFight();
        window_.getLoadingConf().setEnableAnimation(false);
        coreDataBaseAttStBall2(window_, MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__FOE_PK_MAX_HP,VAR__FOE_PK_REMOTE_HP),2)", MbOperationNode.CARAC_DROITE_FERME+"("+MbOperationNode.DIV_FCT+"(VAR__PK_UT_VITESSE,VAR__PK_SAUVAGE_VITESSE),1)");
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getCatchBallEnd()));
    }

    @Test
    public void effNo19() {
        WindowAiki window_ = newFight();
        window_.getLoadingConf().setEnableAnimation(false);
        coreDataBaseAttChangeInRound(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
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
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, tr_.size());
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }

    @Test
    public void effNo20() {
        WindowAiki window_ = newFight();
        window_.getLoadingConf().setEnableAnimation(false);
        coreDataBaseAttChangeInRound(window_);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterFrontPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getMovesLabels().get(1));
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(11, tree_.size());
        assertTrue(tree_.containsObj(window_.getBattle().getBattle().getFlee()));
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
        window_.getBattle().getBattle().getFighterBackPanel().getListe().select(0);
        window_.getBattle().getBattle().getFighterBackPanel().getListe().events();
        tryClick(window_.getBattle().getBattle().getValidateActions());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getBattle().getBattle().getPane()).getTreeAccessible();
        assertEq(12, tr_.size());
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFlee()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFrontPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterBackPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterFleePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getValidateActions()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCatchingPanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getFighterCaughtNicknamePanel().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getNicknameField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getTeamField()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getCatchBall()));
        assertTrue(tr_.containsObj(window_.getBattle().getBattle().getBallPanel().getListeBall().getGlobal()));
    }
    private static void coreDataBaseTrainer(WindowAiki _window) {
        loadRom(_window, coreDataBaseTrainer());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
    public static DataBase coreDataBaseTrainer() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        StatusMoveData stEnt_ = ppStatus();
        EffectTeamWhileSendFoe eff_ = Instances.newEffectTeamWhileSendFoe();
        eff_.setTargetChoice(TargetChoice.LANCEUR);
        eff_.setDamageRateAgainstFoe("2");
        stEnt_.getEffects().add(eff_);
        stEnt_.setTargetChoice(TargetChoice.LANCEUR);
        DataBase mv_ = withMvGene(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz", stEnt_);
        DataBase res_ = withPk(mv_, PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball");
        initBegin(data_);

        City city_ = withBlocksPkCenter(withBlocks(Instances.newCity()), newGerantPokemon(GeranceType.HOST));
        data_.getMap().addPlace(city_);
        Road road_ = withBlocks(Instances.newRoad());
        TrainerMultiFights tr_ = Instances.newTrainerMultiFights();
        tr_.setImageMaxiFileName(SNOW);
        PokemonTeam team_ = Instances.newPokemonTeam();
        team_.getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        team_.getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        tr_.getTeamsRewards().add(team_);
        road_.getLevelRoad().getCharacters().addEntry(newPoint(0,1), tr_);
        data_.getMap().addPlace(road_);
        data_.addTrainerImage(SNOW, new int[][]{new int[1]});


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0), Direction.RIGHT),newCoords(1,0,0,0));
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,2,0));
        compute(data_);

        return ball_;
    }
    private static void coreDataBaseTrainerDual(WindowAiki _window) {
        loadRom(_window, coreDataBaseTrainerDual());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }

    private static void coreDataBaseTrainerTwoFighters(WindowAiki _window) {
        loadRom(_window, coreDataBaseTrainerTwoFighters());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
    private static void coreDataBaseTrainerThreeFighters(WindowAiki _window) {
        loadRom(_window, coreDataBaseTrainerThreeFighters());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
    private static void coreDataBaseTrainerFourFighters(WindowAiki _window) {
        loadRom(_window, coreDataBaseTrainerFourFighters());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }

    private static void coreDataBaseTrainerDualKoPlayer(WindowAiki _window) {
        loadRom(_window, coreDataBaseTrainerDual());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().move(Direction.RIGHT);
        _window.getFacade().interactNoFish();
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setRemainingHp(Rate.zero());
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setGroundPlace(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 0)).setGroundPlaceSubst(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 1)).setRemainingHp(Rate.zero());
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 1)).setGroundPlace(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 1)).setGroundPlaceSubst(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 2)).setRemainingHp(Rate.zero());
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 2)).setGroundPlace(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 2)).setGroundPlaceSubst(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 3)).setRemainingHp(Rate.zero());
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 3)).setGroundPlace(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter((byte) 3)).setGroundPlaceSubst(Fighter.BACK);
        _window.getFacade().getFight().getFirstPositPlayerFighters().put((byte)0,Fighter.BACK);
        _window.getFacade().getFight().getFirstPositPlayerFighters().put((byte)1,Fighter.BACK);
        _window.getFacade().getFight().getFirstPositPlayerFighters().put((byte)2,Fighter.BACK);
        _window.getFacade().getFight().getFirstPositPlayerFighters().put((byte)3,Fighter.BACK);
        _window.getFacade().getFight().setState(FightState.SWITCH_WHILE_KO_USER);
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    public static DataBase coreDataBaseTrainerDual() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        StatusMoveData stEnt_ = ppStatus();
        EffectTeamWhileSendFoe eff_ = Instances.newEffectTeamWhileSendFoe();
        eff_.setTargetChoice(TargetChoice.LANCEUR);
        eff_.setDamageRateAgainstFoe("2");
        stEnt_.getEffects().add(eff_);
        stEnt_.setTargetChoice(TargetChoice.LANCEUR);
        DataBase mv_ = withMvGene(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz", stEnt_);
        DataBase res_ = withPk(mv_, PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball");
        initBegin(data_);

        City city_ = withBlocksPkCenter(withBlocks(Instances.newCity()), newGerantPokemon(GeranceType.HOST));
        data_.getMap().addPlace(city_);
        Road road_ = withBlocks(Instances.newRoad());
        DualFight tr_ = Instances.newDualFight();
        tr_.getFoeTrainer().setImageMaxiFileName(SNOW);
        tr_.getFoeTrainer().setImageMiniSecondTrainerFileName(SNOW);
        tr_.getFoeTrainer().setImageMiniFileName(SNOW);
        tr_.getFoeTrainer().getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        tr_.getFoeTrainer().getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        tr_.getFoeTrainer().getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        tr_.getFoeTrainer().getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        tr_.getAlly().getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        tr_.getAlly().getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        tr_.setPt(newPoint(0,2));

        road_.getLevelRoad().getDualFights().addEntry(newPoint(0,1), tr_);
        data_.getMap().addPlace(road_);
        data_.addTrainerImage(SNOW, new int[][]{new int[1]});


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0), Direction.RIGHT),newCoords(1,0,0,0));
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,2,0));
        compute(data_);

        return ball_;
    }
    public static DataBase coreDataBaseTrainerTwoFighters() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        StatusMoveData stEnt_ = ppStatus();
        EffectTeamWhileSendFoe eff_ = Instances.newEffectTeamWhileSendFoe();
        eff_.setTargetChoice(TargetChoice.LANCEUR);
        eff_.setDamageRateAgainstFoe("2");
        stEnt_.getEffects().add(eff_);
        stEnt_.setTargetChoice(TargetChoice.LANCEUR);
        DataBase mv_ = withMvGene(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz", stEnt_);
        DataBase res_ = withPk(mv_, PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball");
        initBegin(data_);

        City city_ = withBlocksPkCenter(withBlocks(Instances.newCity()), newGerantPokemon(GeranceType.HOST));
        data_.getMap().addPlace(city_);
        Road road_ = withArea2(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);
        data_.addTrainerImage(SNOW, new int[][]{new int[1]});


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0), Direction.RIGHT),newCoords(1,0,0,0));
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,2,0));
        compute(data_);

        return ball_;
    }
    public static DataBase coreDataBaseTrainerThreeFighters() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        StatusMoveData stEnt_ = ppStatus();
        EffectTeamWhileSendFoe eff_ = Instances.newEffectTeamWhileSendFoe();
        eff_.setTargetChoice(TargetChoice.LANCEUR);
        eff_.setDamageRateAgainstFoe("2");
        stEnt_.getEffects().add(eff_);
        stEnt_.setTargetChoice(TargetChoice.LANCEUR);
        DataBase mv_ = withMvGene(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz", stEnt_);
        DataBase res_ = withPk(mv_, PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball");
        initBegin(data_);

        City city_ = withBlocksPkCenter(withBlocks(Instances.newCity()), newGerantPokemon(GeranceType.HOST));
        data_.getMap().addPlace(city_);
        Road road_ = withArea3(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);
        data_.addTrainerImage(SNOW, new int[][]{new int[1]});


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0), Direction.RIGHT),newCoords(1,0,0,0));
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,2,0));
        compute(data_);

        return ball_;
    }

    public static DataBase coreDataBaseTrainerFourFighters() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        StatusMoveData stEnt_ = ppStatus();
        EffectTeamWhileSendFoe eff_ = Instances.newEffectTeamWhileSendFoe();
        eff_.setTargetChoice(TargetChoice.LANCEUR);
        eff_.setDamageRateAgainstFoe("2");
        stEnt_.getEffects().add(eff_);
        stEnt_.setTargetChoice(TargetChoice.LANCEUR);
        DataBase mv_ = withMvGene(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz", stEnt_);
        DataBase res_ = withPk(mv_, PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball");
        initBegin(data_);

        City city_ = withBlocksPkCenter(withBlocks(Instances.newCity()), newGerantPokemon(GeranceType.HOST));
        data_.getMap().addPlace(city_);
        Road road_ = withArea4(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);
        data_.addTrainerImage(SNOW, new int[][]{new int[1]});


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0), Direction.RIGHT),newCoords(1,0,0,0));
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,2,0));
        compute(data_);

        return ball_;
    }
    private static void coreDataBaseTrainer2(WindowAiki _window) {
        loadRom(_window, coreDataBaseTrainer2());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }

    private static void coreDataBaseTrainerHealBack(WindowAiki _window) {
        loadRom(_window, coreDataBaseTrainerHealBack());
        _window.getFacade().healTr();
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getItem(HUILE);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().getValue(0).setCurrent((short) 0);
    }
    public static DataBase coreDataBaseTrainer2() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMv(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz");
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball");
        initBegin(data_);

        City city_ = withBlocksPkCenter(withBlocks(Instances.newCity()), newGerantPokemon(GeranceType.HOST));
        data_.getMap().addPlace(city_);
        Road road_ = withBlocks(Instances.newRoad());
        TrainerMultiFights tr_ = Instances.newTrainerMultiFights();
        tr_.setImageMaxiFileName(SNOW);
        PokemonTeam team_ = Instances.newPokemonTeam();
        team_.getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        team_.getTeam().add(new PkTrainer(wild(RAICHU,1,PARATONNERRE,NULL_REF),new StringList(ECLAIR)));
        tr_.getTeamsRewards().add(team_);
        road_.getLevelRoad().getCharacters().addEntry(newPoint(0,1), tr_);
        data_.getMap().addPlace(road_);
        data_.addTrainerImage(SNOW, new int[][]{new int[1]});


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0), Direction.RIGHT),newCoords(1,0,0,0));
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,2,0));
        compute(data_);

        return ball_;
    }
    public static DataBase coreDataBaseTrainerHealBack() {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,"1","1","1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDes_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDes_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        DataBase mv_ = withMv(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz");
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        HealingPp pp_ = Instances.newHealingPp();
        pp_.setHealingAllMovesPp(true);
        DataBase ball_ = withIt(withIt(res_, HUILE, trsIt_, "graisse", pp_, trsDes_, "grace"), POKE_BALL, trsIt_, "ball", Instances.newBall(), trsDes_, "chance");
        initBegin(data_);

        City city_ = withBlocksPkCenter(withBlocks(Instances.newCity()), newGerantPokemon(GeranceType.HOST));
        data_.getMap().addPlace(city_);
        Road road_ = withBlocks(Instances.newRoad());
        TrainerMultiFights tr_ = Instances.newTrainerMultiFights();
        tr_.setImageMaxiFileName(SNOW);
        PokemonTeam team_ = Instances.newPokemonTeam();
        team_.getTeam().add(new PkTrainer(wild(),new StringList(ECLAIR)));
        team_.getTeam().add(new PkTrainer(wild(RAICHU,1,PARATONNERRE,NULL_REF),new StringList(ECLAIR)));
        tr_.getTeamsRewards().add(team_);
        road_.getLevelRoad().getCharacters().addEntry(newPoint(0,1), tr_);
        data_.getMap().addPlace(road_);
        data_.addTrainerImage(SNOW, new int[][]{new int[1]});


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        city_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(2,0), Direction.RIGHT),newCoords(1,0,0,0));
        road_.getSavedlinks().addEntry(new PlaceInterConnect(newPoint(0,0),Direction.LEFT),newCoords(0,0,2,0));
        compute(data_);

        return ball_;
    }
    private static void coreDataBaseAttSt(WindowAiki _window) {
        StatusMoveData first_ = ppStatus();
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic eone_ = Instances.newEffectStatistic();
        eone_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 0);
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic etwo_ = Instances.newEffectStatistic();
        etwo_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 0);
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttSt(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStBall(WindowAiki _window, String _rateCatch, String _rateFlee) {
        StatusMoveData first_ = ppStatus();
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic eone_ = Instances.newEffectStatistic();
        eone_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 0);
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic etwo_ = Instances.newEffectStatistic();
        etwo_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 0);
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        second_.getEffects().add(etwo_);
        Ball ball_ = Instances.newBall();
        ball_.setCatchingRate("1");
        loadRom(_window, coreDataBaseAttSt(first_,second_, ball_, _rateCatch,
                _rateFlee));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getItem(POKE_BALL);
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttStBallTwo(WindowAiki _window, String _rateCatch, String _rateFlee) {
        DamagingMoveData first_ = Instances.newDamagingMoveData();
        first_.setPp((short) 1);
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectDamage eone_ = Instances.newEffectDamage();
        eone_.setConstDamage(true);
        eone_.setPower("100");
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        eone_.getHitsLaw().addQuickEvent(Rate.one(),LgInt.one());
        first_.getEffects().add(eone_);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic etwo_ = Instances.newEffectStatistic();
        etwo_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 0);
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        second_.getEffects().add(etwo_);
        Ball ball_ = Instances.newBall();
        ball_.setCatchingRate("1");
        loadRom(_window, coreDataBaseAttStTwo(first_,second_, ball_, _rateCatch,
                _rateFlee));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getItem(POKE_BALL);
        game_.getPlayer().getItem(POKE_BALL);
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStBall2(WindowAiki _window, String _rateCatch, String _rateFlee) {
        DamagingMoveData first_ = Instances.newDamagingMoveData();
        first_.setPp((short) 1);
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectDamage eone_ = Instances.newEffectDamage();
        eone_.setConstDamage(true);
        eone_.setPower("100");
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        eone_.getHitsLaw().addQuickEvent(Rate.one(),LgInt.one());
        first_.getEffects().add(eone_);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic etwo_ = Instances.newEffectStatistic();
        etwo_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 0);
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        second_.getEffects().add(etwo_);
        Ball ball_ = Instances.newBall();
        ball_.setCatchingRate("1");
        loadRom(_window, coreDataBaseAttSt(first_,second_, ball_, _rateCatch,
                _rateFlee));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getItem(POKE_BALL);
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStFast(WindowAiki _window) {
        StatusMoveData first_ = ppStatus();
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic eone_ = Instances.newEffectStatistic();
        eone_.setEvtRate(Rate.one());
        eone_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic etwo_ = Instances.newEffectStatistic();
        etwo_.setEvtRate(Rate.one());
        etwo_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttSt(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttChangeInRound(WindowAiki _window) {
        StatusMoveData first_ = ppStatus();
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic eone_ = Instances.newEffectStatistic();
        eone_.setEvtRate(Rate.one());
        eone_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        first_.getEffects().add(eone_);
        first_.setSwitchType(SwitchType.LANCEUR);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatistic etwo_ = Instances.newEffectStatistic();
        etwo_.setEvtRate(Rate.one());
        etwo_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttSt(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStFastSelf(WindowAiki _window) {
        StatusMoveData first_ = ppStatus();
        first_.setTargetChoice(TargetChoice.LANCEUR);
        EffectStatistic eone_ = Instances.newEffectStatistic();
        eone_.setEvtRate(Rate.one());
        eone_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        eone_.setTargetChoice(TargetChoice.LANCEUR);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.LANCEUR);
        EffectStatistic etwo_ = Instances.newEffectStatistic();
        etwo_.setEvtRate(Rate.one());
        etwo_.getStatisVarRank().addEntry(Statistic.SPEED, (byte) 1);
        etwo_.setTargetChoice(TargetChoice.LANCEUR);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttSt(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStatus(WindowAiki _window) {
        StatusMoveData first_ = ppStatus();
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatus eone_ = Instances.newEffectStatus();
        eone_.getLawStatus().addQuickEvent(NULL_REF,LgInt.one());
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        eone_.getDeletedStatus().add(NULL_REF);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatus etwo_ = Instances.newEffectStatus();
        etwo_.getLawStatus().addQuickEvent(NULL_REF,LgInt.one());
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        etwo_.getDeletedStatus().add(NULL_REF);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttStatus(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStatusOther(WindowAiki _window) {
        StatusMoveData first_ = ppStatus();
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatus eone_ = Instances.newEffectStatus();
        eone_.getLawStatus().addQuickEvent(DESERT,LgInt.one());
        eone_.setTargetChoice(TargetChoice.ANY_FOE);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectStatus etwo_ = Instances.newEffectStatus();
        etwo_.getLawStatus().addQuickEvent(DESERT,LgInt.one());
        etwo_.setTargetChoice(TargetChoice.ANY_FOE);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttStatus(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttStatusSelf(WindowAiki _window) {
        StatusMoveData first_ = ppStatus();
        first_.setTargetChoice(TargetChoice.LANCEUR);
        EffectStatus eone_ = Instances.newEffectStatus();
        eone_.getLawStatus().addQuickEvent(DESERT,LgInt.one());
        eone_.setTargetChoice(TargetChoice.LANCEUR);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.LANCEUR);
        EffectStatus etwo_ = Instances.newEffectStatus();
        etwo_.getLawStatus().addQuickEvent(DESERT,LgInt.one());
        etwo_.setTargetChoice(TargetChoice.LANCEUR);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttStatus(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttSwitchPos(WindowAiki _window) {
        StatusMoveData first_ = ppStatus();
        first_.setTargetChoice(TargetChoice.ALLIE);
        EffectSwitchPosition eone_ = Instances.newEffectSwitchPosition();
        eone_.setTargetChoice(TargetChoice.ALLIE);
        first_.getEffects().add(eone_);
        StatusMoveData second_ = ppStatus();
        second_.setTargetChoice(TargetChoice.ALLIE);
        EffectSwitchPosition etwo_ = Instances.newEffectSwitchPosition();
        etwo_.setTargetChoice(TargetChoice.ALLIE);
        second_.getEffects().add(etwo_);
        loadRom(_window, coreDataBaseAttSwitchPos(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttDamage(WindowAiki _window) {
        DamagingMoveData first_ = def();
        DamagingMoveData second_ = def();
        loadRom(_window, coreDataBaseAttSwitchPos(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttDamageRate(WindowAiki _window) {
        DamagingMoveData first_ = def();
        EffectDamageRate rone_ = Instances.newEffectDamageRate();
        rone_.setTargetChoice(TargetChoice.LANCEUR);
        rone_.setRateDamage(Rate.one());
        first_.getEffects().add(rone_);
        DamagingMoveData second_ = def();
        EffectDamageRate rtwo_ = Instances.newEffectDamageRate();
        rtwo_.setTargetChoice(TargetChoice.LANCEUR);
        rtwo_.setRateDamage(Rate.one());
        second_.getEffects().add(rtwo_);
        loadRom(_window, coreDataBaseAttSwitchPos(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttDamageRate2(WindowAiki _window) {
        DamagingMoveData first_ = def();
        EffectDamageRate rone_ = Instances.newEffectDamageRate();
        rone_.setTargetChoice(TargetChoice.LANCEUR);
        rone_.setRateDamage(new Rate("-1/2"));
        first_.getEffects().add(rone_);
        DamagingMoveData second_ = def();
        EffectDamageRate rtwo_ = Instances.newEffectDamageRate();
        rtwo_.setTargetChoice(TargetChoice.LANCEUR);
        rtwo_.setRateDamage(new Rate("-1/2"));
        second_.getEffects().add(rtwo_);
        loadRom(_window, coreDataBaseAttSwitchPos(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttDamageRate3(WindowAiki _window) {
        DamagingMoveData first_ = def();
        EffectFullHpRate rone_ = Instances.newEffectFullHpRate();
        rone_.setTargetChoice(TargetChoice.LANCEUR);
        rone_.setLeftUserHp(Rate.one());
        first_.getEffects().add(rone_);
        DamagingMoveData second_ = def();
        EffectFullHpRate rtwo_ = Instances.newEffectFullHpRate();
        rtwo_.setTargetChoice(TargetChoice.LANCEUR);
        rtwo_.setLeftUserHp(Rate.one());
        second_.getEffects().add(rtwo_);
        loadRom(_window, coreDataBaseAttSwitchPos(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttDamageRate4(WindowAiki _window) {
        DamagingMoveData first_ = def();
        EffectFullHpRate rone_ = Instances.newEffectFullHpRate();
        rone_.setTargetChoice(TargetChoice.LANCEUR);
        rone_.setRestoredHp("1");
        first_.getEffects().add(rone_);
        DamagingMoveData second_ = def();
        EffectFullHpRate rtwo_ = Instances.newEffectFullHpRate();
        rtwo_.setTargetChoice(TargetChoice.LANCEUR);
        rtwo_.setRestoredHp("1");
        second_.getEffects().add(rtwo_);
        loadRom(_window, coreDataBaseAttSwitchPos(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttDamageRate5(WindowAiki _window) {
        DamagingMoveData first_ = def();
        EffectFullHpRate rone_ = Instances.newEffectFullHpRate();
        rone_.setTargetChoice(TargetChoice.LANCEUR);
        rone_.setRestoredHp("1");
        first_.getEffects().add(rone_);
        DamagingMoveData second_ = def();
        EffectFullHpRate rtwo_ = Instances.newEffectFullHpRate();
        rtwo_.setTargetChoice(TargetChoice.LANCEUR);
        rtwo_.setLeftUserHp(Rate.one());
        second_.getEffects().add(rtwo_);
        loadRom(_window, coreDataBaseAttSwitchPos(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttDamageKo(WindowAiki _window) {
        DamagingMoveData first_ = def();
        DamagingMoveData second_ = def();
        loadRom(_window, coreDataBaseAttSwitchPos(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttDamageKo2(WindowAiki _window) {
        DamagingMoveData first_ = def();
        EffectDamageRate rone_ = Instances.newEffectDamageRate();
        rone_.setTargetChoice(TargetChoice.LANCEUR);
        rone_.setRateDamage(new Rate("-20"));
        first_.getEffects().add(rone_);
        DamagingMoveData second_ = def();
        EffectDamageRate rtwo_ = Instances.newEffectDamageRate();
        rtwo_.setTargetChoice(TargetChoice.LANCEUR);
        rtwo_.setRateDamage(new Rate("-20"));
        second_.getEffects().add(rtwo_);
        loadRom(_window, coreDataBaseAttSwitchPos(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttDamageKo3(WindowAiki _window) {
        DamagingMoveData first_ = def();
        first_.getEffects().get(0).setTargetChoice(TargetChoice.ANY_FOE);
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectDamageRate rone_ = Instances.newEffectDamageRate();
        rone_.setTargetChoice(TargetChoice.LANCEUR);
        rone_.setRateDamage(new Rate("20"));
        first_.getEffects().add(rone_);
        DamagingMoveData second_ = def();
        second_.getEffects().get(0).setTargetChoice(TargetChoice.ANY_FOE);
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectDamageRate rtwo_ = Instances.newEffectDamageRate();
        rtwo_.setTargetChoice(TargetChoice.LANCEUR);
        rtwo_.setRateDamage(new Rate("20"));
        second_.getEffects().add(rtwo_);
        loadRom(_window, coreDataBaseAttSwitchPos2(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }

    private static void coreDataBaseAttDamageKo4(WindowAiki _window) {
        DamagingMoveData first_ = def();
        first_.getEffects().get(0).setTargetChoice(TargetChoice.ANY_FOE);
        first_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectDamageRate rone_ = Instances.newEffectDamageRate();
        rone_.setTargetChoice(TargetChoice.LANCEUR);
        rone_.setRateDamage(new Rate("20"));
        first_.getEffects().add(rone_);
        DamagingMoveData second_ = def();
        second_.getEffects().get(0).setTargetChoice(TargetChoice.ANY_FOE);
        second_.setTargetChoice(TargetChoice.ANY_FOE);
        EffectDamageRate rtwo_ = Instances.newEffectDamageRate();
        rtwo_.setTargetChoice(TargetChoice.LANCEUR);
        rtwo_.setRateDamage(new Rate("20"));
        second_.getEffects().add(rtwo_);
        loadRom(_window, coreDataBaseAttSwitchPos3(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).setWonExpSinceLastLevel(new Rate("1000"));
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).setHappiness((short) 1000);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static void coreDataBaseAttAcc(WindowAiki _window) {
        StatusMoveData first_ = ppStatus();
        EffectAccuracy rone_ = Instances.newEffectAccuracy();
        rone_.setTargetChoice(TargetChoice.TOUS_ADV);
        first_.getEffects().add(rone_);
        StatusMoveData second_ = ppStatus();
        EffectAccuracy rtwo_ = Instances.newEffectAccuracy();
        rtwo_.setTargetChoice(TargetChoice.TOUS_ADV);
        second_.getEffects().add(rtwo_);
        loadRom(_window, coreDataBaseAttSwitchPos(first_,second_));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        ((PokemonPlayer)game_.getPlayer().getTeam().last()).getMoves().addEntry(ECLAIR_4,new UsesOfMove((short) 1));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().attract();
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private static StatusMoveData ppStatus() {
        StatusMoveData first_ = Instances.newStatusMoveData();
        first_.setPp((short) 1);
        return first_;
    }

    public static DataBase coreDataBaseAttSt(MoveData _md, MoveData _sec) {
        return coreDataBaseAttSt(_md, _sec, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttSt(MoveData _md, MoveData _sec, Ball _ball, String _rateCatch, String _rateFlee) {
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
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", _sec);
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        compute(data_);

        return ball_;
    }
    public static DataBase coreDataBaseAttStTwo(MoveData _md, MoveData _sec, Ball _ball, String _rateCatch, String _rateFlee) {
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
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", _sec);
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea2(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        compute(data_);

        return ball_;
    }
    public static DataBase coreDataBaseAttStatus(MoveData _md, MoveData _sec) {
        return coreDataBaseAttStatus(_md, _sec, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttStatus(MoveData _md, MoveData _sec, Ball _ball, String _rateCatch, String _rateFlee) {
        DataBase data_ = init();
        initDefaultConsts(POKE_BALL,_rateCatch,_rateFlee,"1","1","1", ECLAIR_2, PIKACHU, data_);
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsSt_ = new StringMap<String>();
        StringMap<String> trsTypes_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        data_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        data_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        data_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        data_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        data_.getTranslatedStatus().addEntry(LANGUAGE, trsSt_);
        data_.getTranslatedTypes().addEntry(LANGUAGE, trsTypes_);
        data_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        trsTypes_.put(ELECTRICK,"elec");
        DataBase ab_ = withAb(data_, PARATONNERRE, trsAb_, "parra");
        ab_.getAbility(PARATONNERRE).setBreakProtection(true);
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", _sec);
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        ball_.completeQuickMembers(DESERT,Instances.newStatusSimple());
        trsSt_.addEntry(DESERT,"dessert");
        data_.getAnimStatus().addEntry(DESERT, new int[][]{new int[1]});
        initBeginAttSt(data_);

        Road road_ = withArea(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        compute(data_);

        return ball_;
    }

    public static DataBase coreDataBaseAttSwitchPos(MoveData _md, MoveData _sec) {
        return coreDataBaseAttSwitchPos(_md, _sec, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttSwitchPos(MoveData _md, MoveData _sec, Ball _ball, String _rateCatch, String _rateFlee) {
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
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", _sec);
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea2(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        compute(data_);

        return ball_;
    }

    public static DataBase coreDataBaseAttSwitchPos2(MoveData _md, MoveData _sec) {
        return coreDataBaseAttSwitchPos2(_md, _sec, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttSwitchPos2(MoveData _md, MoveData _sec, Ball _ball, String _rateCatch, String _rateFlee) {
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
        ab_.getAbility(PARATONNERRE).setInflictingDamageInsteadOfSuffering(true);
        DataBase mv_ = withMvGene(withMvGene(withMvGene(ab_, ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", _sec);
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea2(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
        compute(data_);

        return ball_;
    }

    public static DataBase coreDataBaseAttSwitchPos3(MoveData _md, MoveData _sec) {
        return coreDataBaseAttSwitchPos3(_md, _sec, Instances.newBall(),"1","1");
    }
    public static DataBase coreDataBaseAttSwitchPos3(MoveData _md, MoveData _sec, Ball _ball, String _rateCatch, String _rateFlee) {
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
        DataBase ab_ = withAb(withAb(data_, PARAFEU, trsAb_, "flamme"), PARATONNERRE, trsAb_, "parra");
        ab_.getAbility(PARATONNERRE).setInflictingDamageInsteadOfSuffering(true);
        DataBase mv_ = withMvGene(withMvGene(withMvGene(withMvGene(withMvGene(withMvGene(withMvGene(ab_, ECLAIR_3, trsMv_, "biz 3", _md), ECLAIR_7, trsMv_, "biz 7", _md), ECLAIR_6, trsMv_, "biz 6", _md), ECLAIR_5, trsMv_, "biz 5", _md), ECLAIR_4, trsMv_, "biz 4", _md), ECLAIR_2, trsMv_, "biz 2", def()), ECLAIR, trsMv_, "biz", _sec);
        DataBase res_ = withPk(withPk(mv_, RAICHU, trsPk_, RAICHU_TR), PIKACHU, trsPk_, PIKACHU_TR);
        res_.getPokemon(PIKACHU).getEvolutions().addEntry(RAICHU,Instances.newEvolutionHappiness());
        res_.getPokemon(RAICHU).setBaseEvo(PIKACHU);
        res_.getPokemon(RAICHU).getAbilities().add(PARAFEU);
        res_.getPokemon(RAICHU).getLevMoves().add(new LevelMove((short) 1,ECLAIR_3));
        res_.getPokemon(RAICHU).getLevMoves().add(new LevelMove((short) 1,ECLAIR_5));
        res_.getPokemon(RAICHU).getLevMoves().add(new LevelMove((short) 1,ECLAIR_6));
        res_.getPokemon(RAICHU).getLevMoves().add(new LevelMove((short) 1,ECLAIR_7));
//        res_.getPokemon(RAICHU).a
        DataBase ball_ = withIt(res_, POKE_BALL, trsIt_, "ball", _ball,trsDesc_,"chance");
        initBeginAttSt(data_);

        Road road_ = withArea2(withFishBlocks(Instances.newRoad()));
        data_.getMap().addPlace(road_);


//        initMiniMap(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTm().addEntry((short)3,ECLAIR_4);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.getTmPrice().addEntry((short)3,new LgInt("2"));
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
        pkm_.setLevel((short) 1);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 1));
    }

    public static Road withArea2(Road _road) {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setAvgNbSteps((short) 1);
        a_.setMultFight((byte) 2);
        a_.getWildPokemon().add(wild());
        a_.getWildPokemonFishing().add(wild());
        _road.getLevelRoad().getWildPokemonAreas().add(a_);
        return _road;
    }

    public static Road withArea3(Road _road) {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setAvgNbSteps((short) 1);
        a_.setMultFight((byte) 3);
        a_.getWildPokemon().add(wild());
        a_.getWildPokemonFishing().add(wild());
        _road.getLevelRoad().getWildPokemonAreas().add(a_);
        return _road;
    }

    public static Road withArea4(Road _road) {
        AreaApparition a_ = Instances.newAreaApparition();
        a_.setAvgNbSteps((short) 1);
        a_.setMultFight((byte) 4);
        a_.getWildPokemon().add(wild());
        a_.getWildPokemonFishing().add(wild());
        _road.getLevelRoad().getWildPokemonAreas().add(a_);
        return _road;
    }
    public static DataBase withMvGene(DataBase _data, String _key, StringMap<String> _trs, String _val, MoveData _d) {
        _data.completeQuickMembers(_key, _d);
        _trs.addEntry(_key,_val);
        return _data;
    }
}
