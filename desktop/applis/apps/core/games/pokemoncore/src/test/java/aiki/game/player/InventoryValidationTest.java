package aiki.game.player;

import aiki.db.DataBase;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import code.maths.LgInt;


public class InventoryValidationTest extends InitializationDataBase {

    @Test
    public void validate1Test() {
        DataBase data_ = initDb();
        Inventory inventory_ = new Inventory(data_);
        assertTrue(inventory_.validate(data_));
    }

    @Test
    public void validate2Test() {
        DataBase data_ = initDb();
        Inventory inventory_ = new Inventory(data_);
        inventory_.getItems().put(INVALID_DATA_KEY, LgInt.one());
        assertTrue(!inventory_.validate(data_));
    }

    @Test
    public void validate3Test() {
        DataBase data_ = initDb();
        Inventory inventory_ = new Inventory(data_);
        inventory_.getItems().clear();
        assertTrue(!inventory_.validate(data_));
    }

    @Test
    public void validate4Test() {
        DataBase data_ = initDb();
        Inventory inventory_ = new Inventory(data_);
        inventory_.use(POTION);
        assertTrue(!inventory_.validate(data_));
    }

    @Test
    public void validate5Test() {
        DataBase data_ = initDb();
        Inventory inventory_ = new Inventory(data_);
        inventory_.getTm((short) -1);
        assertTrue(!inventory_.validate(data_));
    }

    @Test
    public void validate6Test() {
        DataBase data_ = initDb();
        Inventory inventory_ = new Inventory(data_);
        inventory_.getHm((short) -1);
        assertTrue(!inventory_.validate(data_));
    }
}
