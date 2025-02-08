package aiki.beans.abilities;

import aiki.beans.*;
import code.bean.nat.*;
import code.scripts.confs.*;
import code.util.*;
import org.junit.Test;

public final class AbilitiesBeanTest extends InitDbAbilities {
    @Test
    public void itemsBegin() {
        assertSizeEq(0,callAbilitiesBeanSortedAbilitiesGet(dispAllAbilities(feedDb())));
    }
    @Test
    public void typedAbility() {
        assertEq("",callAbilitiesBeanTypedAbilityGet(dispAllAbilities(feedDb())));
    }
    @Test
    public void typedNameSet() {
        NaSt bean_ = dispAllAbilities(feedDb());
        callAbilitiesBeanTypedAbilitySet(bean_,C_CAT);
        assertEq(C_CAT,callAbilitiesBeanTypedAbilityGet(bean_));
    }
    @Test
    public void search1() {
        NaSt bean_ = dispAllAbilities(feedDb());
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML, navigateAbilitiesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ABILITIES_SET));
        CustList<TranslatedKey> keys_ = forms(bean_).getValAbilityData(CST_ABILITIES_SET).getKeys();
        assertEq(2,keys_.size());
        assertEq(A_ABILITY,keys_.get(0).getKey());
        assertEq(A_ABILITY2,keys_.get(1).getKey());
    }
    @Test
    public void search2() {
        NaSt bean_ = dispAllAbilities(feedDb());
        callAbilitiesBeanTypedAbilitySet(bean_,A_ABILITY_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML, navigateAbilitiesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ABILITIES_SET));
        CustList<TranslatedKey> keys_ = forms(bean_).getValAbilityData(CST_ABILITIES_SET).getKeys();
        assertEq(1,keys_.size());
        assertEq(A_ABILITY,keys_.get(0).getKey());
    }
    @Test
    public void clickLink() {
        NaSt bean_ = dispAllAbilities(feedDb());
        navigateAbilitiesSearch(bean_);
        beforeDisplaying(bean_);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callAbilitiesBeanClickAbility(bean_,0));
        assertEq(A_ABILITY,getValAbilityId(bean_));
    }
    @Test
    public void getTrAbility() {
        NaSt bean_ = dispAllAbilities(feedDb());
        navigateAbilitiesSearch(bean_);
        beforeDisplaying(bean_);
        assertEq(A_ABILITY_TR,callAbilitiesBeanGetTrAbility(bean_,0));
    }
}
