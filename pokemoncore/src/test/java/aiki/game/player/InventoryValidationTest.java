package aiki.game.player;
import org.junit.Test;

import aiki.exceptions.GameLoadException;
import aiki.game.fight.InitializationDataBase;
import code.maths.LgInt;

@SuppressWarnings("static-method")
public class InventoryValidationTest extends InitializationDataBase {

    @Test
    public void validate1Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate2Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.getItems().put(INVALID_DATA_KEY, LgInt.one());
        inventory_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate3Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.getItems().clear();
        inventory_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate4Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.use(POTION);
        inventory_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate5Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.getTm((short) -1);
        inventory_.validate(_data_);
    }

    @Test(expected=GameLoadException.class)
    public void validate6Test() {
        Inventory inventory_ = new Inventory(_data_);
        inventory_.getHm((short) -1);
        inventory_.validate(_data_);
    }
}
