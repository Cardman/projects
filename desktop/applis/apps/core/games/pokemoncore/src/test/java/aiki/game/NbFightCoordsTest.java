package aiki.game;

import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNotNull;

public class NbFightCoordsTest {
    @Test
    public void new_NbFightCoords_1_test() {
        NbFightCoords u_ = new NbFightCoords("");
        assertNotNull(u_.getCoords());
    }
    @Test
    public void new_NbFightCoordse_2_test() {
        NbFightCoords u_ = NbFightCoords.newNbFightCoords("");
        assertNotNull(u_.display());
    }
}
