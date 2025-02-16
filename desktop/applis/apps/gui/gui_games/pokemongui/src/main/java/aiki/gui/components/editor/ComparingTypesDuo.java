package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.util.*;
import code.util.ints.*;

public final class ComparingTypesDuo implements Comparing<TypesDuo> {

    private final AbsMap<String,String> cats;

    public ComparingTypesDuo(AbsMap<String, String> _c) {
        cats = _c;
    }

    @Override
    public int compare(TypesDuo _one, TypesDuo _two) {
        return ConverterCommonMapUtil.compare(ConverterCommonMapUtil.build(_one, cats),ConverterCommonMapUtil.build(_two, cats));
    }
}
