package aiki.sml;

import aiki.db.DataBase;
import code.maths.montecarlo.AbstractGenerator;
import code.threads.AbstractAtomicBooleanCore;
import code.threads.AbstractAtomicIntegerCoreAdd;

public interface LoadingData {
    DataBase loadResource(AbstractGenerator _gene, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l);
}
