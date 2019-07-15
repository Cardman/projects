package aiki.game.player;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import org.junit.Before;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import code.maths.LgInt;


public class InventoryValidationTest extends InitializationDataBase {

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void validate1Test() {
        Inventory inventory_ = new Inventory(data);
        assertTrue(inventory_.validate(data));
    }

    @Test
    public void validate2Test() {
        Inventory inventory_ = new Inventory(data);
        inventory_.getItems().put(INVALID_DATA_KEY, LgInt.one());
        assertTrue(!inventory_.validate(data));
    }

    @Test
    public void validate3Test() {
        Inventory inventory_ = new Inventory(data);
        inventory_.getItems().clear();
        assertTrue(!inventory_.validate(data));
    }

    @Test
    public void validate4Test() {
        Inventory inventory_ = new Inventory(data);
        inventory_.use(POTION);
        assertTrue(!inventory_.validate(data));
    }

    @Test
    public void validate5Test() {
        Inventory inventory_ = new Inventory(data);
        inventory_.getTm((short) -1);
        assertTrue(!inventory_.validate(data));
    }

    @Test
    public void validate6Test() {
        Inventory inventory_ = new Inventory(data);
        inventory_.getHm((short) -1);
        assertTrue(!inventory_.validate(data));
    }
}
