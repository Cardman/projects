package aiki.game.fight.comparators;
import aiki.game.fight.Fight;
import aiki.game.fight.TeamPosition;
import aiki.util.TeamPositionList;
import code.util.EqList;
import code.util.core.NumberUtil;
import code.util.ints.Comparing;

public final class SortedFighterActsComparator implements Comparing<TeamPosition> {

    private Fight fight;

    public SortedFighterActsComparator(Fight _fight) {
        fight = _fight;
    }

    @Override
    public int compare(TeamPosition _o1, TeamPosition _o2) {
        TeamPositionList sorted_;
        sorted_ = fight.getOrderedFighters();
        return NumberUtil.compareLg(sorted_.indexOfObj(_o1), sorted_.indexOfObj(_o2));
    }

}
