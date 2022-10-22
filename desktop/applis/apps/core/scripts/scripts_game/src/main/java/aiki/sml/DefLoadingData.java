package aiki.sml;

import aiki.db.DataBase;
import aiki.db.LoadFlag;
import aiki.db.PerCent;
import aiki.facade.SexListInt;
import code.maths.montecarlo.AbstractGenerator;
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
    public DataBase loadResource(AbstractGenerator _gene, PerCent _p, LoadFlag _l) {
        return LoadRes.loadResource(_gene, _p, _l, languages, displayed,sexListInt);
    }
}
