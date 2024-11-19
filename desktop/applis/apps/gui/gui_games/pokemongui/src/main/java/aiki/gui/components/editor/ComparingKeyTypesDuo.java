package aiki.gui.components.editor;

import aiki.beans.facade.comparators.*;
import aiki.fight.util.*;
import code.gui.*;
import code.maths.*;
import code.util.*;
import code.util.ints.*;

public final class ComparingKeyTypesDuo implements Comparing<EditedCrudPair<TypesDuo, Rate>> {

    private final AbsMap<String,String> cats;

    public ComparingKeyTypesDuo(AbsMap<String, String> _c) {
        cats = _c;
    }

    @Override
    public int compare(EditedCrudPair<TypesDuo, Rate> _one, EditedCrudPair<TypesDuo, Rate> _two) {
        return ComparatorTypesDuo.compareTr(_one.getKey(),_two.getKey(), cats);
    }
}
