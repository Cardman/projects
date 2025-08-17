package aiki.beans.simulation;

import aiki.beans.*;
import aiki.db.*;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.util.*;
import code.util.*;

public final class SimulationBeanAbstractAction implements IntBeanAction {
    private final Fighter fighter;
    private final SimulationFighterForm fighterCgh;
    private final IntBeanChgAction action;

    public SimulationBeanAbstractAction(Fighter _f, SimulationFighterForm _c, IntBeanChgAction _a) {
        this.fighter = _f;
        fighterCgh = _c;
        this.action = _a;
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
        fighter.setName(fighterCgh.getName().tryRet());
        fighter.setNickname(fighterCgh.getNickname().tryRet());
        fighter.setGender(fighterCgh.getGender().valGender());
        fighter.setWeight(fighterCgh.getWeight().valueRate());
        fighter.setHeight(fighterCgh.getHeight().valueRate());
        fighter.setCurrentName(fighterCgh.getCurrentName().tryRet());
        fighter.setCurrentGender(fighterCgh.getCurrentGender().valGender());
        fighter.setLastUsedItem(fighterCgh.getLastUsedItem().tryRet());
        fighter.setItem(fighterCgh.getItem().tryRet());
        fighter.setExpItem(fighterCgh.getExpItem().tryRet());
        fighter.setAbility(fighterCgh.getAbility().tryRet());
        fighter.setCurrentAbility(fighterCgh.getCurrentAbility().tryRet());
        fighter.setNbRounds(fighterCgh.getNbRounds().valueLgInt());
        fighter.setTypes(new StringList(fighterCgh.getTypes().tryRet()));
        fighter.setRemainingHp(fighterCgh.getRemainingHp().valueRate());
        fighter.setClone(fighterCgh.getClone().valueRate());
        fighter.setProtectedAgainstMoveTypes(new StringList(fighterCgh.getProtectedAgainstMoveTypes().tryRet()));
        fighter.setActed(fighterCgh.getActed().isSelected());
        fighter.setGroundPlace(fighterCgh.getGroundPlace().valueInt());
        fighter.setGroundPlaceSubst(fighterCgh.getGroundPlaceSubst().valueInt());
        fighter.setWonExp(fighterCgh.getWonExp().valueRate());
        fighter.setWonExpSinceLastLevel(fighterCgh.getWonExpSinceLastLevel().valueRate());
        fighter.setLevel(fighterCgh.getLevel().valueLong());
        fighter.setHappiness(fighterCgh.getHappiness().valueLong());
        fighter.setUsedBallCatching(fighterCgh.getUsedBallCatching().tryRet());
        fighter.setNbPrepaRound(fighterCgh.getNbPrepaRound().valueLong());
        fighter.setDisappeared(fighterCgh.getDisappeared().isSelected());
        fighter.setNeedingToRecharge(fighterCgh.getNeedingToRecharge().isSelected());
        fighter.setLastSufferedMove(fighterCgh.getLastSufferedMove().tryRet());
        fighter.setLastSufferedMoveTypes(new StringList(fighterCgh.getLastSufferedMoveTypes().tryRet()));
        fighter.setLastUsedMove(fighterCgh.getLastUsedMove().tryRet());
        fighter.setUsedMoveLastRound(fighterCgh.getUsedMoveLastRound().tryRet());
        fighter.setAlreadyInvokedMovesRound(new StringList(fighterCgh.getAlreadyInvokedMovesRound().tryRet()));
        fighter.setLastSuccessfulMove(fighterCgh.getLastSuccessfulMove().tryRet());
        fighter.setNbRepeatingSuccessfulMoves(fighterCgh.getNbRepeatingSuccessfulMoves().valueLgInt());
        fighter.setUsingItem(fighterCgh.getUsingItem().isSelected());
        fighter.setSuccessfulMove(fighterCgh.getSuccessfulMove().isSelected());
        fighter.setChanged(fighterCgh.getChanged().isSelected());
        fighter.setMovesToBeLearnt(new StringList(fighterCgh.getMovesToBeLearnt().tryRet()));
        return DataBase.EMPTY_STRING;
    }

}
