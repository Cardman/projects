package aiki.map;
import code.util.CustList;
import code.util.NumberMap;
import code.util.ObjectMap;
import aiki.map.places.Place;
import aiki.map.tree.Tree;
import aiki.util.Coords;

public class Solution {

    private CustList<Step> steps;

    public Solution(ObjectMap<Coords, Condition> _accessibility, NumberMap<Short,Place> _places, Tree _tree) {
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
