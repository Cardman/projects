package aiki.beans.status;

import aiki.beans.*;
import code.bean.nat.*;
import code.scripts.confs.*;
import code.util.*;
import org.junit.Test;

public final class StatusSetBeanTest extends InitDbStatusSet {
    @Test
    public void stBegin() {
        assertSizeEq(0,callStatusSetBeanSortedStatusGet(dispAllStatus()));
    }
    @Test
    public void typedStatus() {
        assertEq("",callStatusSetBeanTypedStatusGet(dispAllStatus()));
    }
    @Test
    public void typedNameSet() {
        NaSt bean_ = dispAllStatus();
        callStatusSetBeanTypedStatusSet(bean_,M_DAM_TR);
        assertEq(M_DAM_TR,callStatusSetBeanTypedStatusGet(bean_));
    }
    @Test
    public void search1() {
        NaSt bean_ = dispAllStatus();
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_STATUS_HTML, navigateStatusSearch(bean_));
        assertTrue(forms(bean_).contains(CST_STATUS_SET));
        CustList<TranslatedKey> keys_ = forms(bean_).getValStatusData(CST_STATUS_SET).getKeys();
        assertEq(13,keys_.size());
        assertEq(S_STA_00,keys_.get(0).getKey());
        assertEq(S_STA_01,keys_.get(1).getKey());
        assertEq(S_STA_02,keys_.get(2).getKey());
        assertEq(S_STA_03,keys_.get(3).getKey());
        assertEq(S_STA_04,keys_.get(4).getKey());
        assertEq(S_STA_05,keys_.get(5).getKey());
        assertEq(S_STA_06,keys_.get(6).getKey());
        assertEq(S_STA_07,keys_.get(7).getKey());
        assertEq(S_STA_08,keys_.get(8).getKey());
        assertEq(S_STA_09,keys_.get(9).getKey());
        assertEq(S_STA_10,keys_.get(10).getKey());
        assertEq(S_STA_11,keys_.get(11).getKey());
        assertEq(S_STA_12,keys_.get(12).getKey());
    }
    @Test
    public void search2() {
        NaSt bean_ = dispAllStatus();
        callStatusSetBeanTypedStatusSet(bean_,S_STA_00_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML, navigateStatusSearch(bean_));
        assertTrue(forms(bean_).contains(CST_STATUS_SET));
        CustList<TranslatedKey> keys_ = forms(bean_).getValStatusData(CST_STATUS_SET).getKeys();
        assertEq(1,keys_.size());
        assertEq(S_STA_00,keys_.get(0).getKey());
    }
    @Test
    public void clickStatus1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML,callStatusSetBeanClickStatus(0));
    }
    @Test
    public void clickStatus2() {
        assertEq(S_STA_00,callStatusSetBeanClickStatusId(0));
    }
    @Test
    public void elt() {
        assertEq(S_STA_00,elt(callStatusSetBeanSortedStatusGet(dispAllStatusSearch()),0));
    }
    @Test
    public void getTrStatus() {
        assertEq(S_STA_00_TR,callStatusSetBeanGetTrStatus(dispAllStatusSearch(),0));
    }
}
