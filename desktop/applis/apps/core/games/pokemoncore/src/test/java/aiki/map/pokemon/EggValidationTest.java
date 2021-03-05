package aiki.map.pokemon;

import aiki.db.DataBase;
import code.util.core.StringUtil;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;


public class EggValidationTest extends InitializationDataBase {

    private static final String SEPARATOR = ""+Egg.SEPARATOR;

    @Test
    public void isValid1Test() {
        DataBase data_ = initDb();
        Egg egg_ = new Egg(StringUtil.concat(PIKACHU,SEPARATOR,"1"));
        assertTrue(egg_.validate(data_));
    }

    @Test
    public void isValid2Test() {
        DataBase data_ = initDb();
        Egg egg_ = new Egg(PIKACHU);
        assertTrue(egg_.validate(data_));
    }

    @Test
    public void isValid3Test() {
        DataBase data_ = initDb();
        Egg egg_ = new Egg(StringUtil.concat(INVALID_DATA_KEY,SEPARATOR,"1"));
        assertTrue(!egg_.validate(data_));
    }

    @Test
    public void isValid4Test() {
        DataBase data_ = initDb();
        Egg egg_ = new Egg(StringUtil.concat(PIKACHU,SEPARATOR,"-1"));
        assertTrue(!egg_.validate(data_));
    }

    @Test
    public void toString1Test() {
        Egg egg_ = new Egg(StringUtil.concat(PIKACHU,SEPARATOR,"1"));
        assertEq(StringUtil.concat(PIKACHU,SEPARATOR,"1"), egg_.display());
    }

    @Test
    public void toString2Test() {
        Egg egg_ = Egg.newEgg(StringUtil.concat(PIKACHU,SEPARATOR,"1"));
        assertEq(StringUtil.concat(PIKACHU,SEPARATOR,"1"), egg_.display());
    }
}
