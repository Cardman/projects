package aiki.beans.simulation;

import aiki.beans.*;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.util.*;

public final class SimulationBeanAbstractAction implements IntBeanAction {
    private final Fighter fighter;
    private final IntBeanChgAction action;

    public SimulationBeanAbstractAction(Fighter _f, IntBeanChgAction _a) {
        this.fighter = _f;
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
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
}
