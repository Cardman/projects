package aiki.beans.help;

import aiki.beans.TranslatedKey;
import aiki.facade.FacadeGame;
import aiki.fight.util.TypesDuo;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanTypesTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        CustList<TranslatedKey> ls_ = FightHelpBean.typesInit(db().getData(),EN);
        assertEq(2,ls_.size());
        assertEq(T_TYPE1_TR,ls_.get(0).getTranslation());
        assertEq(T_TYPE2_TR,ls_.get(1).getTranslation());
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        f_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1,T_TYPE1_TR);
        f_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE2,T_TYPE2_TR);
        f_.getData().getTableTypes().addEntry(new TypesDuo(T_TYPE1,T_TYPE1), Rate.newRate("2"));
        f_.getData().getTableTypes().addEntry(new TypesDuo(T_TYPE2,T_TYPE2), Rate.newRate("3"));
        f_.getData().getTableTypes().addEntry(new TypesDuo(T_TYPE2,T_TYPE1), Rate.newRate("4"));
        f_.getData().getTableTypes().addEntry(new TypesDuo(T_TYPE1,T_TYPE2), Rate.newRate("5"));
        return f_;
    }
}
