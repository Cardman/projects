package aiki.beans.items;

import aiki.beans.CommonBean;
import code.maths.Rate;
import org.junit.Test;

public final class BoostBeanTest extends InitDbBoost {
    @Test
    public void getHappiness1() {
        assertSizeEq(2,callBoostBeanHappinessGet(boostDb()));
    }
    @Test
    public void getHappiness2() {
        assertEq(I_BALL,firstTkLgKey(eltTkLgKey(callBoostBeanHappinessGet(boostDb()),0)));
    }
    @Test
    public void getHappiness3() {
        assertEq(1,secondTkLgKey(eltTkLgKey(callBoostBeanHappinessGet(boostDb()),0)));
    }
    @Test
    public void getHappiness4() {
        assertEq(I_BOOST,firstTkLgKey(eltTkLgKey(callBoostBeanHappinessGet(boostDb()),1)));
    }
    @Test
    public void getHappiness5() {
        assertEq(2,secondTkLgKey(eltTkLgKey(callBoostBeanHappinessGet(boostDb()),1)));
    }
    @Test
    public void isBall1() {
        assertTrue(callBoostBeanIsBall(boostDb(),0));
    }
    @Test
    public void isBall2() {
        assertFalse(callBoostBeanIsBall(boostDb(),1));
    }
    @Test
    public void getTrHappiness1() {
        assertEq(I_BALL_TR,callBoostBeanGetTrHappiness(boostDb(),0));
    }
    @Test
    public void getTrHappiness2() {
        assertEq(I_BOOST_TR,callBoostBeanGetTrHappiness(boostDb(),1));
    }
    @Test
    public void clickHappiness1() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_ITEMS_BALL_HTML,callBoostBeanClickHappiness(boostDb(),0));
    }
    @Test
    public void clickHappiness2() {
        assertEq(I_BALL,callBoostBeanClickHappinessId(boostDb(),0));
    }
    @Test
    public void getMaxEv() {
        assertEq(2,callBoostBeanMaxEvGet(boostDb()));
    }
    @Test
    public void getWinPp() {
        assertEq(Rate.one(),callBoostBeanWinPpGet(boostDb()));
    }
    @Test
    public void getEvs1() {
        assertSizeEq(1,callBoostBeanEvsGet(boostDb()));
    }
    @Test
    public void getEvs2() {
        assertEq(1,secondTkLgKey(eltTkLgKey(callBoostBeanEvsGet(boostDb()),0)));
    }
    @Test
    public void getTrEv() {
        assertEq(ST_SPEED_TR,callBoostBeanGetTrEv(boostDb(),0));
    }
}
