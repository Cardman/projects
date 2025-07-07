package aiki.beans.simulation;

import aiki.beans.*;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.util.*;
import code.util.*;

public final class SimulationBeanAbstractAction implements IntBeanAction {
    private final Fighter fighter;
    private final IntBeanChgAction action;
    private final IntBeanChgFighter1 act1;
    private final IntBeanChgFighter2 act2;
    private final IntBeanChgFighter3 act3;
    private final IntBeanChgFighter4 act4;
    private final IntBeanChgFighter5 act5;
    private final IntBeanChgFighter6 act6;

    public SimulationBeanAbstractAction(Fighter _f, IntBeanChgFighter _a) {
        this.fighter = _f;
        this.action = _a.getPart6().getAction();
        act1 = _a.getPart1();
        act2 = _a.getPart2();
        act3 = _a.getPart3();
        act4 = _a.getPart4();
        act5 = _a.getPart5();
        act6 = _a.getPart6();
    }


    @Override
    public String actionBean() {
        KindAction ka_ = action.getKindAction().valueKa();
        if (ka_ == KindAction.MOVE) {
            fighter.setAction(new ActionMove());
        } else if (ka_ == KindAction.HEAL_MOVE) {
            fighter.setAction(new ActionHealMove());
        } else if (ka_ == KindAction.HEAL) {
            fighter.setAction(new ActionSimpleHeal());
        } else if (ka_ == KindAction.SWITCH) {
            fighter.setAction(new ActionSwitch());
        } else {
            fighter.setAction(null);
        }
        AbstractAction act_ = fighter.getAction();
        if (act_ instanceof ChosenMove) {
            ((ChosenMove) act_).setFirstChosenMove(action.getFirst().tryRet());
        }
        if (act_ instanceof ChosenHealing) {
            ((ChosenHealing) act_).setChosenHealingItem(action.getItem().tryRet());
        }
        if (act_ instanceof ActionMove) {
            if (action.getTarget().valueTc().eq(TargetCoords.def())) {
                ((ActionMove) act_).setChosenTargets(TargetCoordsList.newList());
            } else {
                ((ActionMove) act_).setChosenTargets(TargetCoordsList.newList(action.getTarget().valueTc()));
            }
            ((ActionMove)act_).setFinalChosenMove(action.getLast().tryRet());
        }
        if (act_ instanceof ChosenReplacing) {
            ((ChosenReplacing)act_).setSubstitute(action.getSub().valueInt());
        }
        if (act_ instanceof ActionHeal) {
            ((ActionHeal)act_).setTeam(action.getTeam().isSelected());
        }
        fighter.setName(act1.getName().tryRet());
        fighter.setNickname(act1.getNickname().tryRet());
        fighter.setGender(act1.getGender().valGender());
        fighter.setWeight(act1.getWeight().valueRate());
        fighter.setHeight(act1.getHeight().valueRate());
        fighter.setCurrentName(act1.getCurrentName().tryRet());
        fighter.setCurrentGender(act1.getCurrentGender().valGender());
        fighter.setLastUsedItem(act2.getLastUsedItem().tryRet());
        fighter.setItem(act2.getItem().tryRet());
        fighter.setAbility(act2.getAbility().tryRet());
        fighter.setCurrentAbility(act2.getCurrentAbility().tryRet());
        fighter.setNbRounds(act2.getNbRounds().valueLgInt());
        fighter.setTypes(new StringList(act2.getTypes().tryRet()));
        fighter.setRemainingHp(act2.getRemainingHp().valueRate());
        fighter.setClone(act3.getClone().valueRate());
        fighter.setProtectedAgainstMoveTypes(new StringList(act3.getProtectedAgainstMoveTypes().tryRet()));
        fighter.setActed(act3.getActed().isSelected());
        fighter.setGroundPlace(act3.getGroundPlace().valueInt());
        fighter.setGroundPlaceSubst(act3.getGroundPlaceSubst().valueInt());
        fighter.setWonExp(act3.getWonExp().valueRate());
        fighter.setWonExpSinceLastLevel(act3.getWonExpSinceLastLevel().valueRate());
        fighter.setLevel(act4.getLevel().valueLong());
        fighter.setHappiness(act4.getHappiness().valueLong());
        fighter.setUsedBallCatching(act4.getUsedBallCatching().tryRet());
        fighter.setNbPrepaRound(act4.getNbPrepaRound().valueLong());
        fighter.setDisappeared(act4.getDisappeared().isSelected());
        fighter.setNeedingToRecharge(act4.getNeedingToRecharge().isSelected());
        fighter.setLastSufferedMove(act4.getLastSufferedMove().tryRet());
        fighter.setLastSufferedMoveTypes(new StringList(act5.getLastSufferedMoveTypes().tryRet()));
        fighter.setLastUsedMove(act5.getLastUsedMove().tryRet());
        fighter.setUsedMoveLastRound(act5.getUsedMoveLastRound().tryRet());
        fighter.setAlreadyInvokedMovesRound(new StringList(act5.getAlreadyInvokedMovesRound().tryRet()));
        fighter.setLastSuccessfulMove(act5.getLastSuccessfulMove().tryRet());
        fighter.setNbRepeatingSuccessfulMoves(act5.getNbRepeatingSuccessfulMoves().valueLgInt());
        fighter.setUsingItem(act5.getUsingItem().isSelected());
        fighter.setSuccessfulMove(act6.getSuccessfulMove().isSelected());
        fighter.setChanged(act6.getChanged().isSelected());
        fighter.setMovesToBeLearnt(new StringList(act6.getMovesToBeLearnt().tryRet()));
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
