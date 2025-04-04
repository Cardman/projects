package aiki.beans.abilities;

import aiki.beans.*;
import code.util.*;
import code.util.core.*;
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
        AbilitiesBean bean_ = dispAllAbilities(feedDb());
        callAbilitiesBeanTypedAbilitySet(bean_,C_CAT);
        assertEq(C_CAT,callAbilitiesBeanTypedAbilityGet(bean_));
    }
    @Test
    public void search1() {
        AbilitiesBean bean_ = dispAllAbilities(feedDb());
        assertEq(CommonBean.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML, navigateAbilitiesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ABILITIES_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValAbilityData(CST_ABILITIES_SET).getKeys());
        assertEq(2,keys_.size());
        assertTrue(StringUtil.contains(keys_,A_ABILITY));
        assertTrue(StringUtil.contains(keys_,A_ABILITY2));
    }
    @Test
    public void search2() {
        AbilitiesBean bean_ = dispAllAbilities(feedDb());
        callAbilitiesBeanTypedAbilitySet(bean_,A_ABILITY_TR);
        assertEq(CommonBean.REN_ADD_WEB_HTML_ABILITY_DATA_HTML, navigateAbilitiesSearch(bean_));
        assertTrue(forms(bean_).contains(CST_ABILITIES_SET));
        CustList<String> keys_ = WithFilterBean.keys(forms(bean_).getValAbilityData(CST_ABILITIES_SET).getKeys());
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,A_ABILITY));
    }
    @Test
    public void clickLink() {
        AbilitiesBean bean_ = dispAllAbilities(feedDb());
        navigateAbilitiesSearch(bean_);
        beforeDisplaying(bean_);
        assertEq(CommonBean.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,callAbilitiesBeanClickAbility(bean_,0));
        assertEq(A_ABILITY,getValAbilityId(bean_));
    }
    @Test
    public void getTrAbility() {
        AbilitiesBean bean_ = dispAllAbilities(feedDb());
        navigateAbilitiesSearch(bean_);
        beforeDisplaying(bean_);
        assertEq(A_ABILITY_TR,callAbilitiesBeanGetTrAbility(bean_,0));
    }
}
