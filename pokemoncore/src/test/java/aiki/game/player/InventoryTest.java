package aiki.game.player;
import static aiki.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import code.maths.LgInt;

@SuppressWarnings("static-method")
public class InventoryTest extends InitializationDataBase{

    @Test
    public void new_Inventory_DataBase_1Test() {
        Inventory obj_ = new Inventory(_data_);
        assertEq(100,obj_.getItems().size());
        assertTrue(obj_.getItems().containsObj(MUSCLE));
        assertTrue(obj_.getItems().containsObj(PP_PLUS));
        assertTrue(obj_.getItems().containsObj(GRELOT));
        assertTrue(obj_.getItems().containsObj(PIERRE_LUNE));
        assertTrue(obj_.getItems().containsObj(PIERRE_SOLEIL));
        assertEq(4,obj_.getAllTm().size());
        assertTrue(obj_.getAllTm().containsObj((short) 5));
        assertEq(1,obj_.getAllHm().size());
        assertTrue(obj_.getAllHm().containsObj((short) 1));
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
        assertEq(1,obj_.getTm().size());
        assertTrue(obj_.getTm().containsObj((short) 1));
    }

    @Test
    public void getHm1Test() {
        Inventory obj_ = new Inventory(_data_);
        obj_.getHm((short) 1);
        assertEq(1,obj_.getHm().size());
        assertTrue(obj_.getHm().containsObj((short) 1));
    }
}
