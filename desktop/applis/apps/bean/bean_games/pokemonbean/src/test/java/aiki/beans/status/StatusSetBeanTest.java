package aiki.beans.status;

import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.CustList;
import code.util.core.StringUtil;
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
        CustList<String> keys_ = forms(bean_).getValStatusData(CST_STATUS_SET).getKeys();
        assertEq(13,keys_.size());
        assertTrue(StringUtil.contains(keys_,S_STA_00));
        assertTrue(StringUtil.contains(keys_,S_STA_01));
        assertTrue(StringUtil.contains(keys_,S_STA_02));
        assertTrue(StringUtil.contains(keys_,S_STA_03));
        assertTrue(StringUtil.contains(keys_,S_STA_04));
        assertTrue(StringUtil.contains(keys_,S_STA_05));
        assertTrue(StringUtil.contains(keys_,S_STA_06));
        assertTrue(StringUtil.contains(keys_,S_STA_07));
        assertTrue(StringUtil.contains(keys_,S_STA_08));
        assertTrue(StringUtil.contains(keys_,S_STA_09));
        assertTrue(StringUtil.contains(keys_,S_STA_10));
        assertTrue(StringUtil.contains(keys_,S_STA_11));
        assertTrue(StringUtil.contains(keys_,S_STA_12));
    }
    @Test
    public void search2() {
        NaSt bean_ = dispAllStatus();
        callStatusSetBeanTypedStatusSet(bean_,S_STA_00_TR);
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_STATUS_DATA_HTML, navigateStatusSearch(bean_));
        assertTrue(forms(bean_).contains(CST_STATUS_SET));
        CustList<String> keys_ = forms(bean_).getValStatusData(CST_STATUS_SET).getKeys();
        assertEq(1,keys_.size());
        assertTrue(StringUtil.contains(keys_,S_STA_00));
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
