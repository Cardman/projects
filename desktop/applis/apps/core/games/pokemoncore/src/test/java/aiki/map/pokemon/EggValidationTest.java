package aiki.map.pokemon;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import aiki.db.DataBase;
import code.util.core.StringUtil;
import org.junit.Before;
import org.junit.Test;

import aiki.game.fight.InitializationDataBase;


public class EggValidationTest extends InitializationDataBase {

    private static final String SEPARATOR = Character.toString(Egg.SEPARATOR);

    private DataBase data;
    @Before
    public void initTests() {
        data = initDb();
    }
    @Test
    public void isValid1Test() {
        Egg egg_ = new Egg(StringUtil.concat(PIKACHU,SEPARATOR,"1"));
        assertTrue(egg_.validate(data));
    }

    @Test
    public void isValid2Test() {
        Egg egg_ = new Egg(PIKACHU);
        assertTrue(egg_.validate(data));
    }

    @Test
    public void isValid3Test() {
        Egg egg_ = new Egg(StringUtil.concat(INVALID_DATA_KEY,SEPARATOR,"1"));
        assertTrue(!egg_.validate(data));
    }

    @Test
    public void isValid4Test() {
        Egg egg_ = new Egg(StringUtil.concat(PIKACHU,SEPARATOR,"-1"));
        assertTrue(!egg_.validate(data));
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
