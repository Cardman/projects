package aiki.map.levels;

import aiki.db.EquallablePkUtil;
import aiki.util.Points;
import aiki.util.PointsBlock;
import org.junit.Test;

import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.Point;
import code.util.CustList;



public class LevelWithWildPokemonTest extends EquallablePkUtil {

    @Test
    public void getAreaByPoint1FailTest() {
        LevelWithWildPokemon level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        assertTrue(level_.getAreaByPoint(new Point((short)0,(short)0)).isVirtual());
    }

    @Test
    public void getAreaByPoint2FailTest() {
        LevelWithWildPokemon level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        assertTrue(level_.getAreaByPoint(new Point((short)0,(short)0)).isVirtual());
    }

    @Test
    public void getAreaByPoint1Test() {
        LevelWithWildPokemon level_ = new LevelRoad();
        level_.setBlocks(new PointsBlock());
        level_.setWildPokemonAreas(new CustList<AreaApparition>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        AreaApparition area_ = new AreaApparition();
        area_.setWildPokemon(new CustList<WildPk>());
        WildPk pk_;
        pk_ = new WildPk();
        pk_.setName("PIKACHU");
        pk_.setAbility("STATIK");
        pk_.setGender(Gender.NO_GENDER);
        pk_.setLevel((short) 3);
        pk_.setItem("");
        area_.getWildPokemon().add(pk_);
        level_.getWildPokemonAreas().add(area_);
        AreaApparition storedArea_ = level_.getAreaByPoint(new Point((short)1,(short)0));
        assertSame(area_, storedArea_);
    }
}
