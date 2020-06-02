package aiki.map.levels;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.map.characters.GymTrainer;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.util.Limits;
import aiki.map.util.ScreenCoords;
import aiki.util.Point;
import code.util.ObjectMap;


public class LevelIndoorGymTest {

    @Test
    public void limits1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Limits limits_ = level_.limits();
        assertEq(0, limits_.getTopLeft().getx());
        assertEq(0, limits_.getTopLeft().gety());
        assertEq(0, limits_.getBottomRight().getx());
        assertEq(0, limits_.getBottomRight().gety());
    }
    @Test
    public void limits2Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Limits limits_ = level_.limits();
        assertEq(0, limits_.getTopLeft().getx());
        assertEq(0, limits_.getTopLeft().gety());
        assertEq(4, limits_.getBottomRight().getx());
        assertEq(2, limits_.getBottomRight().gety());
    }

    @Test
    public void getScreenCoordsByPoint1FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        ScreenCoords blockId_ = level_.getScreenCoordsByPoint(new Point((short)0,(short)0));
        assertEq(new ScreenCoords(-1,-1), blockId_);
    }

    @Test
    public void getScreenCoordsByPoint2FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        ScreenCoords blockId_ = level_.getScreenCoordsByPoint(new Point((short)5,(short)3));
        assertEq(new ScreenCoords(-1,-1), blockId_);
    }

    @Test
    public void getBlockIdByPoint1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)1,(short)1), block_);
        ScreenCoords blockId_ = level_.getScreenCoordsByPoint(new Point((short)2,(short)2));
        assertEq(new ScreenCoords(1,1), blockId_);
    }

    @Test
    public void getBlockByPoint1FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        assertTrue(!level_.getBlockByPoint(new Point((short)0,(short)0)).isValid());
    }

    @Test
    public void getBlockByPoint2FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        assertTrue(!level_.getBlockByPoint(new Point((short)5,(short)3)).isValid());
    }

    @Test
    public void getBlockByPoint1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block storedBlock_ = level_.getBlockByPoint(new Point((short)1,(short)1));
        assertSame(block_, storedBlock_);
    }

}
