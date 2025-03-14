package aiki.beans.items;

import aiki.beans.CommonBean;
import org.junit.Test;

public final class FossilBeanTest extends InitDbItemEvolving {
    @Test
    public void getLavel() {
        assertEq(1,callFossilBeanLevelGet());
    }
    @Test
    public void getTrPokemon() {
        assertEq(P_POKEMON_TR,callFossilBeanGetTrPokemon());
    }
    @Test
    public void clickPokemon1() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_POKEMON_DATA_HTML,callFossilBeanClickPokemon());
    }
    @Test
    public void clickPokemon2() {
        assertEq(P_POKEMON,callFossilBeanClickPokemonId());
    }

}
