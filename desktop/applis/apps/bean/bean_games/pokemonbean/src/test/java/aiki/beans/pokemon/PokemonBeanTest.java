package aiki.beans.pokemon;

import org.junit.Test;

public final class PokemonBeanTest extends InitDbPkOne {
    @Test
    public void getDisplayName() {
        assertEq(P_POK_00_TR,callPokemonBeanDisplayNameGet(0));
    }
    @Test
    public void isAppearing1() {
        assertTrue(callPokemonBeanIsAppearing(0,2,0));
    }
    @Test
    public void isAppearing2() {
        assertTrue(callPokemonBeanIsAppearing(8,2,0));
    }
    @Test
    public void isAppearing3() {
        assertTrue(callPokemonBeanIsAppearing(15,0,1));
    }
    @Test
    public void isAppearing4() {
        assertFalse(callPokemonBeanIsAppearing(1,0,1));
    }
    @Test
    public void isAppearingZero1() {
        assertTrue(callPokemonBeanIsAppearingZero(0,2));
    }
    @Test
    public void isAppearingZero2() {
        assertFalse(callPokemonBeanIsAppearingZero(15,2));
    }
    @Test
    public void isAppearingPlace1() {
        assertTrue(callPokemonBeanIsAppearingPlace(15,0));
    }
    @Test
    public void isAppearingPlace2() {
        assertFalse(callPokemonBeanIsAppearingPlace(1,0));
    }
    @Test
    public void isAppearingAnyWhere1() {
        assertTrue(callPokemonBeanIsAppearingAnyWhere(15));
    }
    @Test
    public void isAppearingAnyWhere2() {
        assertFalse(callPokemonBeanIsAppearingAnyWhere(2));
    }
    @Test
    public void layers() {
        assertSizeEq(2,callPokemonBeanLayers(0));
    }
    @Test
    public void isMultiLayer1() {
        assertTrue(callPokemonBeanIsMultiLayer(0));
    }
    @Test
    public void isMultiLayer2() {
        assertFalse(callPokemonBeanIsMultiLayer(1));
    }
    @Test
    public void isMultiLayer3() {
        assertFalse(callPokemonBeanIsMultiLayer(2));
    }
    @Test
    public void isFirstRow1() {
        assertFalse(callPokemonBeanIsFirstRow(0));
    }
    @Test
    public void isFirstRow2() {
        assertFalse(callPokemonBeanIsFirstRow(1));
    }
    @Test
    public void isFirstRow3() {
        assertTrue(callPokemonBeanIsFirstRow(2));
    }
    @Test
    public void isFirstRow4() {
        assertFalse(callPokemonBeanIsFirstRow(3));
    }
    @Test
    public void getMapWidth() {
        assertEq(2,callPokemonBeanGetMapWidth());
    }
}
