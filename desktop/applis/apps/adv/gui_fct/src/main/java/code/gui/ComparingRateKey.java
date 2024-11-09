package code.gui;

import code.maths.*;
import code.util.ints.*;

public class ComparingRateKey<V> implements Comparing<EditedCrudPair<Rate, V>> {
    @Override
    public int compare(EditedCrudPair<Rate, V> _one, EditedCrudPair<Rate, V> _two) {
        return _one.getKey().cmp(_two.getKey());
    }
}
