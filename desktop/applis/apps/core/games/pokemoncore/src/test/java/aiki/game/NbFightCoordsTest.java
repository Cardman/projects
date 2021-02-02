package aiki.game;

import aiki.db.EquallablePkUtil;
import org.junit.Test;

public class NbFightCoordsTest extends EquallablePkUtil {
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
