package aiki.game.fight;

import aiki.game.fight.actions.AbstractAction;
import aiki.game.fight.actions.ActionHeal;
import code.util.core.NumberUtil;
import code.util.core.SortConstants;
import code.util.ints.Comparing;

/** Comparator without case eq*/
public final class SortedFighterHealActsComparator implements
        Comparing<TeamPosition> {

    private final Fight fight;

    public SortedFighterHealActsComparator(Fight _fight) {
        fight = _fight;
    }

    @Override
    public int compare(TeamPosition _fighterOne, TeamPosition _fighterTwo) {
        Fighter creatureOne_=fight.getFighter(_fighterOne);
        AbstractAction actionOne_=creatureOne_.getAction();
        Fighter creatureTwo_=fight.getFighter(_fighterTwo);
        AbstractAction actionTwo_=creatureTwo_.getAction();

        ActionHeal ac1_ = (ActionHeal) actionOne_;
        ActionHeal ac2_ = (ActionHeal) actionTwo_;
        boolean permuter_ = ac2_.isTeam() && !ac1_.isTeam() && NumberUtil.eq(_fighterOne.getTeam(), _fighterTwo.getTeam());
        if(permuter_){
            return SortConstants.SWAP_SORT;
        }
        return SortConstants.NO_SWAP_SORT;
    }

}
