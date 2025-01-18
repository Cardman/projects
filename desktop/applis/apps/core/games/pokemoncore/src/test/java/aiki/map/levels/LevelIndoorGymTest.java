package aiki.map.levels;

import aiki.db.EquallablePkUtil;
import aiki.util.Points;
import aiki.util.PointsBlock;
import org.junit.Test;

import aiki.map.characters.GymTrainer;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.util.Limits;
import aiki.map.util.ScreenCoords;
import aiki.util.Point;



public class LevelIndoorGymTest extends EquallablePkUtil {

    @Test
    public void limits1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new PointsBlock());
        Limits limits_ = level_.limits();
        assertEq(0, limits_.getTopLeft().getx());
        assertEq(0, limits_.getTopLeft().gety());
        assertEq(0, limits_.getBottomRight().getx());
        assertEq(0, limits_.getBottomRight().gety());
    }
    @Test
    public void limits2Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block(5, 3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(newPoint(0,0), block_);
        Limits limits_ = level_.limits();
        assertEq(0, limits_.getTopLeft().getx());
        assertEq(0, limits_.getTopLeft().gety());
        assertEq(4, limits_.getBottomRight().getx());
        assertEq(2, limits_.getBottomRight().gety());
    }

    @Test
    public void getScreenCoordsByPoint1FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new PointsBlock());
        ScreenCoords blockId_ = level_.getScreenCoordsByPoint(newPoint(0,0));
        assertEq(new ScreenCoords(-1,-1), blockId_);
    }

    @Test
    public void getScreenCoordsByPoint2FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block(5, 3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(newPoint(0,0), block_);
        ScreenCoords blockId_ = level_.getScreenCoordsByPoint(newPoint(5,3));
        assertEq(new ScreenCoords(-1,-1), blockId_);
    }

    @Test
    public void getBlockIdByPoint1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block(5, 3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(newPoint(1,1), block_);
        ScreenCoords blockId_ = level_.getScreenCoordsByPoint(newPoint(2,2));
        assertEq(new ScreenCoords(1,1), blockId_);
    }

    @Test
    public void getBlockByPoint1FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new PointsBlock());
        assertTrue(!level_.getBlockByPoint(newPoint(0,0)).isValid());
    }

    @Test
    public void getBlockByPoint2FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block(5, 3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(newPoint(0,0), block_);
        assertTrue(!level_.getBlockByPoint(newPoint(5,3)).isValid());
    }

    @Test
    public void getBlockByPoint1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new PointsBlock());
        Block block_ = new Block(5, 3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(newPoint(0,0), block_);
        Block storedBlock_ = level_.getBlockByPoint(newPoint(1,1));
        assertSame(block_, storedBlock_);
    }

}
