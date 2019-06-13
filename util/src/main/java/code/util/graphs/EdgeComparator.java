package code.util.graphs;

import code.util.Numbers;
import code.util.ints.Comparing;
import code.util.ints.SortedEdge;

public class EdgeComparator<T extends SortedEdge<T>> implements Comparing<T> {

    @Override
    public int compare(T _one, T _two) {
        return Numbers.compareLg(_one.getOrder(), _two.getOrder());
    }

}
