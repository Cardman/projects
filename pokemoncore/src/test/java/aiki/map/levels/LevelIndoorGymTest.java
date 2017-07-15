package aiki.map.levels;
import static aiki.EquallablePkUtil.assertEq;
import static code.util.opers.EquallableUtil.assertEq;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.util.ObjectMap;
import aiki.exceptions.BlockNotFoundException;
import aiki.map.characters.GymTrainer;
import aiki.map.levels.Block;
import aiki.map.levels.LevelIndoorGym;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.util.Limits;
import aiki.util.Point;

@SuppressWarnings("static-method")
public class LevelIndoorGymTest {

    @Test
    public void canContainsNewBlock1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        assertTrue(level_.canContainsNewBlock(new Point((short)0,(short)0), block_));
    }

    @Test
    public void canContainsNewBlock2Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        assertTrue(level_.canContainsNewBlock(new Point((short)5,(short)0), block_));
    }

    @Test
    public void canContainsNewBlock3Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        assertTrue(!level_.canContainsNewBlock(new Point((short)4,(short)0), block_));
    }

    @Test
    public void canMoveBlock1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)5,(short)0), block_);
        assertTrue(!level_.canMoveBlock(new Point((short)5,(short)0), new Point((short)4,(short)0)));
    }

    @Test
    public void canMoveBlock2Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)5,(short)0), block_);
        assertTrue(level_.canMoveBlock(new Point((short)5,(short)0), new Point((short)0,(short)3)));
    }

    @Test
    public void moveBlock1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)5,(short)0), block_);
        level_.moveBlock(new Point((short)5,(short)0), new Point((short)0,(short)3));
        Block movedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)3));
        assertSame(block_, movedBlock_);
    }

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

    @Test(expected=BlockNotFoundException.class)
    public void getBlockIdByPoint1FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        level_.getBlockIdByPoint(new Point((short)0,(short)0));
    }

    @Test(expected=BlockNotFoundException.class)
    public void getBlockIdByPoint2FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        level_.getBlockIdByPoint(new Point((short)5,(short)3));
    }

    @Test
    public void getBlockIdByPoint1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Point blockId_ = level_.getBlockIdByPoint(new Point((short)1,(short)1));
        assertEq(new Point((short)0,(short)0), blockId_);
    }

    @Test(expected=BlockNotFoundException.class)
    public void getBlockByPoint1FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        level_.getBlockByPoint(new Point((short)0,(short)0));
    }

    @Test(expected=BlockNotFoundException.class)
    public void getBlockByPoint2FailTest() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        level_.getBlockByPoint(new Point((short)5,(short)3));
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

    @Test
    public void insertLine1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setGymTrainers(new ObjectMap<Point,GymTrainer>());
        level_.setGymLeaderCoords(new Point());
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
//        level_.insertLine((short)2, (short)1);
        level_.insertLine((short)2, (short)0);
//        assertEq(2, level_.getBlocks().size());
        assertEq(1, level_.getBlocks().size());
        //assertTrue(level_.getBlocks().contains(new Point((short)0,(short)0)));
//        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)3)));
        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)2)));
//        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)2));
        assertSame(storedBlock_, block_);
//        assertEq(1, storedBlock_.getHeight());
//        assertEq(5, storedBlock_.getWidth());
//        assertEq(2, level_.getBlocks().getVal(new Point((short)0,(short)3)).getHeight());
//        assertEq(5, level_.getBlocks().getVal(new Point((short)0,(short)3)).getWidth());
//        assertEq("", level_.getBlocks().getVal(new Point((short)0,(short)3)).getTileFileName());
//        assertEq(EnvironmentType.ROAD, level_.getBlocks().getVal(new Point((short)0,(short)3)).getType());
//        assertEq(0, level_.getBlocks().getVal(new Point((short)0,(short)3)).getIndexApparition());
    }

    @Test
    public void insertLine2Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)3), secondBlock_);
        level_.insertLine((short)2, (short)1);
//        assertEq(3, level_.getBlocks().size());
        assertEq(2, level_.getBlocks().size());
        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)0)));
        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)3)));
//        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)5)));
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertSame(storedBlock_, block_);
//        assertTrue(secondBlock_ == level_.getBlocks().getVal(new Point((short)0,(short)5)));
        assertSame(secondBlock_, level_.getBlocks().getVal(new Point((short)0,(short)3)));
//        assertEq(1, storedBlock_.getHeight());
//        assertEq(5, storedBlock_.getWidth());
//        assertEq(2, level_.getBlocks().getVal(new Point((short)0,(short)3)).getHeight());
//        assertEq(5, level_.getBlocks().getVal(new Point((short)0,(short)3)).getWidth());
//        assertEq("", level_.getBlocks().getVal(new Point((short)0,(short)3)).getTileFileName());
//        assertEq(EnvironmentType.ROAD, level_.getBlocks().getVal(new Point((short)0,(short)3)).getType());
//        assertEq(0, level_.getBlocks().getVal(new Point((short)0,(short)3)).getIndexApparition());
    }

    @Test
    public void insertLine3Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setGymTrainers(new ObjectMap<Point,GymTrainer>());
        level_.setGymLeaderCoords(new Point());
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)3), secondBlock_);
        level_.insertLine((short)2, (short)3);
        assertEq(2, level_.getBlocks().size());
        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)0)));
        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)5)));
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertSame(storedBlock_, block_);
        assertSame(secondBlock_, level_.getBlocks().getVal(new Point((short)0,(short)5)));
        assertEq(3, storedBlock_.getHeight());
        assertEq(5, storedBlock_.getWidth());
    }

    @Test
    public void addLinesForSelectedBlock1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)3), secondBlock_);
        level_.addLinesForSelectedBlock(new Point((short)0,(short)0), 1);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(3, storedBlock_.getHeight());
    }

    @Test
    public void addLinesForSelectedBlock2Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)4), secondBlock_);
        level_.addLinesForSelectedBlock(new Point((short)0,(short)0), 1);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(4, storedBlock_.getHeight());
    }

    @Test
    public void removeLinesForSelectedBlock1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)4), secondBlock_);
        level_.removeLinesForSelectedBlock(new Point((short)0,(short)0), 1);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(2, storedBlock_.getHeight());
    }

    @Test
    public void addColumnsForSelectedBlock1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)3, (short)5, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)3, (short)5, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)3,(short)0), secondBlock_);
        level_.addColumnsForSelectedBlock(new Point((short)0,(short)0), 1);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(3, storedBlock_.getWidth());
    }

    @Test
    public void addColumnsForSelectedBlock2Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)3, (short)5, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)3, (short)5, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)4,(short)0), secondBlock_);
        level_.addColumnsForSelectedBlock(new Point((short)0,(short)0), 1);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(4, storedBlock_.getWidth());
    }

    @Test
    public void changeImage1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)3, (short)5, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)3, (short)5, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)3,(short)0), secondBlock_);
        level_.changeImage(new Point((short)0,(short)0), "", (short)4, (short)1);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(3, storedBlock_.getWidth());
        assertEq(5, storedBlock_.getHeight());
    }

    @Test
    public void changeImage2Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)3), secondBlock_);
        level_.changeImage(new Point((short)0,(short)0), "", (short)1, (short)4);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(3, storedBlock_.getHeight());
        assertEq(5, storedBlock_.getWidth());
    }

    @Test
    public void changeImage3Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)4), secondBlock_);
        Block thirdBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        thirdBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)5,(short)0), thirdBlock_);
        level_.changeImage(new Point((short)0,(short)0), "", (short)6, (short)4);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(5, storedBlock_.getWidth());
        assertEq(3, storedBlock_.getHeight());
    }

    @Test
    public void changeImage4Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)3, (short)5, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)3, (short)5, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)4,(short)0), secondBlock_);
        level_.changeImage(new Point((short)0,(short)0), "", (short)4, (short)1);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(4, storedBlock_.getWidth());
        assertEq(1, storedBlock_.getHeight());
    }

    @Test
    public void changeImage5Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)4), secondBlock_);
        level_.changeImage(new Point((short)0,(short)0), "", (short)1, (short)4);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(4, storedBlock_.getHeight());
        assertEq(1, storedBlock_.getWidth());
    }

    @Test
    public void changeImage6Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)4), secondBlock_);
        Block thirdBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        thirdBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)6,(short)0), thirdBlock_);
        level_.changeImage(new Point((short)0,(short)0), "", (short)6, (short)4);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(6, storedBlock_.getWidth());
        assertEq(4, storedBlock_.getHeight());
    }

    @Test
    public void changeImage7Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        level_.changeImage(new Point((short)0,(short)0), "", (short)4, (short)2);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(4, storedBlock_.getWidth());
        assertEq(2, storedBlock_.getHeight());
    }

    @Test
    public void removeColumnsForSelectedBlock1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)3, (short)5, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)3, (short)5, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)4,(short)0), secondBlock_);
        level_.removeColumnsForSelectedBlock(new Point((short)0,(short)0), 1);
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertEq(2, storedBlock_.getWidth());
    }

    @Test
    public void hasBlankLine1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setGymTrainers(new ObjectMap<Point,GymTrainer>());
        level_.setGymLeaderCoords(new Point());
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)3), secondBlock_);
//        level_.insertLine((short)2, (short)1);
//        assertTrue(level_.hasBlankLine((short)1, (short)2));
        level_.insertLine((short)2, (short)3);
        assertTrue(level_.hasBlankLine((short)3, (short)2));
    }

    @Test
    public void hasBlankLine2Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)3), secondBlock_);
        assertTrue(!level_.hasBlankLine((short)1, (short)2));
    }

    @Test
    public void removeLine1Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setGymTrainers(new ObjectMap<Point,GymTrainer>());
        level_.setGymLeaderCoords(new Point());
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)3), secondBlock_);
//        level_.insertLine((short)2, (short)1);
//        level_.removeLine((short)1, (short)2);
        level_.insertLine((short)2, (short)3);
        level_.removeLine((short)3, (short)2);
//        assertEq(3, level_.getBlocks().size());
        assertEq(2, level_.getBlocks().size());
        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)0)));
        //assertTrue(level_.getBlocks().contains(new Point((short)0,(short)1)));
        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)3)));
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertSame(storedBlock_, block_);
        assertSame(secondBlock_, level_.getBlocks().getVal(new Point((short)0,(short)3)));
//        assertEq(1, storedBlock_.getHeight());
//        assertEq(5, storedBlock_.getWidth());
//        assertEq(2, level_.getBlocks().getVal(new Point((short)0,(short)1)).getHeight());
//        assertEq(5, level_.getBlocks().getVal(new Point((short)0,(short)1)).getWidth());
//        assertEq("", level_.getBlocks().getVal(new Point((short)0,(short)1)).getTileFileName());
//        assertEq(EnvironmentType.ROAD, level_.getBlocks().getVal(new Point((short)0,(short)1)).getType());
//        assertEq(0, level_.getBlocks().getVal(new Point((short)0,(short)1)).getIndexApparition());
    }

    @Test
    public void removeLine2Test() {
        LevelIndoorGym level_ = new LevelIndoorGym();
        level_.setBlocks(new ObjectMap<Point,Block>());
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        block_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)0), block_);
        Block secondBlock_ = new Block((short)5, (short)3, EnvironmentType.DESERT, "");
        secondBlock_.setIndexApparition((short) 0);
        level_.getBlocks().put(new Point((short)0,(short)3), secondBlock_);
        level_.removeLine((short)1, (short)2);
        assertEq(2, level_.getBlocks().size());
        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)0)));
        assertTrue(level_.getBlocks().contains(new Point((short)0,(short)3)));
        Block storedBlock_ = level_.getBlocks().getVal(new Point((short)0,(short)0));
        assertSame(storedBlock_, block_);
        assertSame(secondBlock_, level_.getBlocks().getVal(new Point((short)0,(short)3)));
    }
}
