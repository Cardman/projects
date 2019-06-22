package aiki.game.player;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import code.util.StringList;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import code.maths.LgInt;


public class InventoryTest extends InitializationDataBase{

    @Test
    public void new_Inventory_DataBase_1Test() {
        Inventory obj_ = new Inventory(_data_);
        assertEq(100,obj_.getItemsKeys().size());
        assertTrue(StringList.contains(obj_.getItemsKeys(), MUSCLE));
        assertTrue(StringList.contains(obj_.getItemsKeys(), PP_PLUS));
        assertTrue(StringList.contains(obj_.getItemsKeys(), GRELOT));
        assertTrue(StringList.contains(obj_.getItemsKeys(), PIERRE_LUNE));
        assertTrue(StringList.contains(obj_.getItemsKeys(), PIERRE_SOLEIL));
        assertEq(4,obj_.getAllTm().size());
        assertEq(2,obj_.getAllTm().get(0));
        assertEq(3,obj_.getAllTm().get(1));
        assertEq(4,obj_.getAllTm().get(2));
        assertEq(5,obj_.getAllTm().get(3));
        assertEq(1,obj_.getAllHm().size());
        assertEq(1,obj_.getAllHm().first());
    }

    @Test
    public void getNumber1Test() {
        Inventory obj_ = new Inventory(_data_);
        assertEq(LgInt.zero(),obj_.getNumber(MUSCLE));
        assertEq(LgInt.zero(),obj_.getNumber(PP_PLUS));
        assertEq(LgInt.zero(),obj_.getNumber(GRELOT));
        assertEq(LgInt.zero(),obj_.getNumber(PIERRE_LUNE));
        assertEq(LgInt.zero(),obj_.getNumber(PIERRE_SOLEIL));
    }

    @Test
    public void get1Test() {
        Inventory obj_ = new Inventory(_data_);
        obj_.getItem(MUSCLE);
        assertEq(LgInt.one(),obj_.getNumber(MUSCLE));
    }

    @Test
    public void use1Test() {
        Inventory obj_ = new Inventory(_data_);
        obj_.getItem(MUSCLE);
        obj_.use(MUSCLE);
        assertEq(LgInt.zero(),obj_.getNumber(MUSCLE));
    }

    @Test
    public void getTm1Test() {
        Inventory obj_ = new Inventory(_data_);
        obj_.getTm((short) 1);
        assertEq(1,obj_.gotTm().size());
        assertTrue(obj_.gotTm().containsObj((short) 1));
    }

    @Test
    public void getTm2Test() {
        Inventory obj_ = new Inventory(_data_);
        assertEq(0,obj_.gotTm().size());
        assertTrue(!obj_.gotTm().containsObj((short) 1));
    }

    @Test
    public void getHm1Test() {
        Inventory obj_ = new Inventory(_data_);
        obj_.getHm((short) 1);
        assertEq(1,obj_.gotHm().size());
        assertTrue(obj_.gotHm().containsObj((short) 1));
    }

    @Test
    public void getHm2Test() {
        Inventory obj_ = new Inventory(_data_);
        assertEq(0,obj_.gotHm().size());
        assertTrue(!obj_.gotHm().containsObj((short) 1));
    }

    @Test
    public void new_Inventory_Test() {
        Inventory obj_ = new Inventory();
        assertEq(0,obj_.getHm().size());
        assertEq(0,obj_.getTm().size());
        assertEq(0,obj_.getItems().size());
    }
}
