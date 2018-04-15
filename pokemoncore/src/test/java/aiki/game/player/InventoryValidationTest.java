package aiki.game.player;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import code.maths.LgInt;

@SuppressWarnings("static-method")
public class InventoryValidationTest extends InitializationDataBase {

    @Test
    public void validate1Test() {
        Inventory inventory_ = new Inventory(_data_);
        assertTrue(inventory_.validate(_data_));
    }

    @Test
    public void validate2Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.getItems().put(INVALID_DATA_KEY, LgInt.one());
        assertTrue(!inventory_.validate(_data_));
    }

    @Test
    public void validate3Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.getItems().clear();
        assertTrue(!inventory_.validate(_data_));
    }

    @Test
    public void validate4Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.use(POTION);
        assertTrue(!inventory_.validate(_data_));
    }

    @Test
    public void validate5Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.getTm((short) -1);
        assertTrue(!inventory_.validate(_data_));
    }

    @Test
    public void validate6Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.getHm((short) -1);
        assertTrue(!inventory_.validate(_data_));
    }
}
