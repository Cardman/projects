package aiki.sml;

import aiki.db.DataBase;
import aiki.db.LoadFlag;
import aiki.db.PerCent;
import code.maths.montecarlo.AbstractGenerator;

public interface LoadingData {
    DataBase loadResource(AbstractGenerator _gene, PerCent _p, LoadFlag _l);
}
