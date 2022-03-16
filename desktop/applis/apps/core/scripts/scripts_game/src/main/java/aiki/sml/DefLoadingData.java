package aiki.sml;

import aiki.db.DataBase;
import aiki.db.LoadFlag;
import aiki.db.PerCent;
import code.maths.montecarlo.AbstractGenerator;
import code.util.StringList;
import code.util.StringMap;

public final class DefLoadingData implements LoadingData {
    private final StringList languages;
    private final StringMap<String> displayed;
    public DefLoadingData(StringList _lgs, StringMap<String> _dis) {
        languages = _lgs;
        displayed = _dis;
    }
    @Override
    public DataBase loadResource(AbstractGenerator _gene, PerCent _p, LoadFlag _l) {
        return LoadRes.loadResource(_gene, _p, _l, languages, displayed);
    }
}
