package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.SexListInt;
import code.maths.montecarlo.AbstractGenerator;
import code.threads.AbstractAtomicBooleanCore;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.util.StringList;
import code.util.StringMap;

public final class DefLoadingData implements LoadingData {
    private final StringList languages;
    private final StringMap<String> displayed;
    private final SexListInt sexListInt;
    public DefLoadingData(StringList _lgs, StringMap<String> _dis, SexListInt _sexList) {
        languages = _lgs;
        displayed = _dis;
        sexListInt = _sexList;
    }
    @Override
    public DataBase loadResource(AbstractGenerator _gene, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l) {
        return LoadRes.loadResource(_gene, _p, _l, languages, displayed,sexListInt);
    }
}
