package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.SexListInt;
import code.maths.montecarlo.AbstractGenerator;
import code.threads.IntCallable;
import code.util.StringList;
import code.util.StringMap;

public final class DefLoadingData implements IntCallable<DataBase> {
    private final AbstractGenerator generator;
    private final StringList languages;
    private final StringMap<String> displayed;
    private final SexListInt sexListInt;
    public DefLoadingData(AbstractGenerator _gene, StringList _lgs, StringMap<String> _dis, SexListInt _sexList) {
        generator = _gene;
        languages = _lgs;
        displayed = _dis;
        sexListInt = _sexList;
    }

    @Override
    public DataBase call() {
        return LoadRes.loadResource(generator, languages, displayed,sexListInt);
    }
}
