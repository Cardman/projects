package aiki.game.fight.util;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NbEffectFighterCoordsTest {
    @Test
    public void new_NbEffectFighterCoords_1_test() {
        NbEffectFighterCoords n_ = new NbEffectFighterCoords(",");
        assertNotNull(n_.getPosition());
    }
    @Test
    public void new_NbEffectFighterCoords_2_test() {
        NbEffectFighterCoords n_ = NbEffectFighterCoords.newNbEffectFighterCoords(",");
        assertNotNull(n_.getPosition());
    }
    @Test
    public void displayTest() {
        NbEffectFighterCoords n_ = NbEffectFighterCoords.newNbEffectFighterCoords(",");
        assertNotNull(n_.display());
    }
}
