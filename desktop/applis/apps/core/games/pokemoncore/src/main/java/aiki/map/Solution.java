package aiki.map;
import aiki.map.places.Place;
import aiki.map.tree.Tree;
import aiki.util.Coords;
import aiki.util.CoordssCondition;
import code.util.CustList;
import code.util.*;


public class Solution {

    private CustList<Step> steps;

    public Solution(CoordssCondition _accessibility, CustList<Place> _places, Tree _tree) {
        steps = new CustList<Step>();
        Step step_ = new Step(_accessibility, _places, _tree);
        steps.add(step_);
        while (step_.keepSteps()) {
            step_ = step_.nextStep(_accessibility, _places, _tree);
            steps.add(step_);
        }
    }

    public CustList<Step> getSteps() {
        return steps;
    }
}
