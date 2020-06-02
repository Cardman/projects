package aiki.map.pokemon;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import org.junit.Before;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;
import code.util.StringList;


public class EggValidationTest extends InitializationDataBase {

    private static final String SEPARATOR = String.valueOf(Egg.SEPARATOR);

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void isValid1Test() {
        Egg egg_ = new Egg(StringList.concat(PIKACHU,SEPARATOR,"1"));
        assertTrue(egg_.validate(data));
    }

    @Test
    public void isValid2Test() {
        Egg egg_ = new Egg(PIKACHU);
        assertTrue(egg_.validate(data));
    }

    @Test
    public void isValid3Test() {
        Egg egg_ = new Egg(StringList.concat(INVALID_DATA_KEY,SEPARATOR,"1"));
        assertTrue(!egg_.validate(data));
    }

    @Test
    public void isValid4Test() {
        Egg egg_ = new Egg(StringList.concat(PIKACHU,SEPARATOR,"-1"));
        assertTrue(!egg_.validate(data));
    }

    @Test
    public void toString1Test() {
        Egg egg_ = new Egg(StringList.concat(PIKACHU,SEPARATOR,"1"));
        assertEq(StringList.concat(PIKACHU,SEPARATOR,"1"), egg_.display());
    }

    @Test
    public void toString2Test() {
        Egg egg_ = Egg.newEgg(StringList.concat(PIKACHU,SEPARATOR,"1"));
        assertEq(StringList.concat(PIKACHU,SEPARATOR,"1"), egg_.display());
    }
}
