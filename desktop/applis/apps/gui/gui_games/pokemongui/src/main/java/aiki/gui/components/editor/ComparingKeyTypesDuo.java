package aiki.gui.components.editor;

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
        return ConverterCommonMapUtil.compare(ConverterCommonMapUtil.build(_one.getKey(), cats),ConverterCommonMapUtil.build(_two.getKey(), cats));
    }
}
