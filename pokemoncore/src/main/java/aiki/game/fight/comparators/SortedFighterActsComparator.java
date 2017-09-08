package aiki.game.fight.comparators;
import java.util.Comparator;

import code.util.EqList;
import code.util.Numbers;
import aiki.game.fight.Fight;
import aiki.game.fight.TeamPosition;

public final class SortedFighterActsComparator implements Comparator<TeamPosition> {

    private Fight fight;

    public SortedFighterActsComparator(Fight _fight) {
        fight = _fight;
    }

    @Override
    public int compare(TeamPosition _o1, TeamPosition _o2) {
        EqList<TeamPosition> sorted_;
        sorted_ = fight.getOrderedFighters();
        return Numbers.compare(sorted_.indexOfObj(_o1), sorted_.indexOfObj(_o2));
    }

}
