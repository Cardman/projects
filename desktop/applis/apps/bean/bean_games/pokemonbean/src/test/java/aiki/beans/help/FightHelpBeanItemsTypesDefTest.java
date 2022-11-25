package aiki.beans.help;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.instances.Instances;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanItemsTypesDefTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        DataBase d_ = db().getData();
        StringList ls_ = FightHelpBean.itemsTypesDefInit(FightHelpBean.movesTypesDefItemInit(d_), d_);
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,I_ITEM));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.getTypesByOwnedItem().addEntry(I_ITEM,NULL_REF);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newStatusMoveData());
        f_.getData().completeMembers(I_ITEM, Instances.newItemForBattle());
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().getVal(EN).addEntry(I_ITEM,I_ITEM_TR);
        return f_;
    }
}
