package aiki.game.fight.comparators;
import aiki.game.fight.Fight;
import aiki.game.fight.Fighter;
import aiki.game.fight.TeamPosition;
import aiki.game.fight.actions.AbstractAction;
import aiki.game.fight.actions.ActionHeal;
import code.util.CustList;
import code.util.Numbers;
import code.util.ints.Comparing;

/** Comparator without case eq*/
public final class SortedFighterHealActsComparator implements
        Comparing<TeamPosition> {

    private Fight fight;

    public SortedFighterHealActsComparator(Fight _fight) {
        fight = _fight;
    }

    @Override
    public int compare(TeamPosition _fighterOne, TeamPosition _fighterTwo) {
        Fighter creatureOne_=fight.getFighter(_fighterOne);
        AbstractAction actionOne_=creatureOne_.getAction();
        Fighter creatureTwo_=fight.getFighter(_fighterTwo);
        AbstractAction actionTwo_=creatureTwo_.getAction();
        boolean permuter_=false;

        ActionHeal ac1_ = (ActionHeal) actionOne_;
        ActionHeal ac2_ = (ActionHeal) actionTwo_;
        if(ac2_.isTeam()&&!ac1_.isTeam()){
            if(Numbers.eq(_fighterOne.getTeam(),_fighterTwo.getTeam())){
                permuter_=true;
            }
        }
        if(permuter_){
            return CustList.SWAP_SORT;
        }
        return CustList.NO_SWAP_SORT;
    }

}
