package aiki.map.levels;

import aiki.db.EquallablePkUtil;
import aiki.db.ImageArrayBaseSixtyFour;
import code.maths.montecarlo.DefaultGenerator;
import org.junit.Test;

import aiki.db.DataBase;
import aiki.map.levels.enums.EnvironmentType;
import aiki.util.Point;


public class BlockTest extends EquallablePkUtil {


    @Test
    public void bounds1Test() {
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        BlockBounds bounds_ = block_.bounds(newPoint(2,3));
        assertEq(2, bounds_.getxLeftTop());
        assertEq(3, bounds_.getyLeftTop());
        assertEq(6, bounds_.getxRightTop());
        assertEq(3, bounds_.getyRightTop());
        assertEq(2, bounds_.getxLeftBottom());
        assertEq(5, bounds_.getyLeftBottom());
        assertEq(6, bounds_.getxRightBottom());
        assertEq(5, bounds_.getyRightBottom());
    }

    @Test
    public void intersection1Test() {
        Block blockOne_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        Block blockTwo_ = new Block((short)3, (short)5, EnvironmentType.ROAD, "");
        BlockBounds intersection_ = Block.intersection(blockOne_.bounds(newPoint(2,3)), blockTwo_.bounds(newPoint(4,4)));
        assertEq(4, intersection_.getxLeftTop());
        assertEq(4, intersection_.getyLeftTop());
        assertEq(6, intersection_.getxRightTop());
        assertEq(4, intersection_.getyRightTop());
        assertEq(4, intersection_.getxLeftBottom());
        assertEq(5, intersection_.getyLeftBottom());
        assertEq(6, intersection_.getxRightBottom());
        assertEq(5, intersection_.getyRightBottom());
    }

    @Test
    public void intersection2Test() {
        Block blockOne_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        Block blockTwo_ = new Block((short)3, (short)5, EnvironmentType.ROAD, "");
        BlockBounds intersection_ = Block.intersection(blockOne_.bounds(newPoint(2,3)), blockTwo_.bounds(newPoint(4,6)));
        assertTrue(!intersection_.isValid());
    }

    @Test
    public void isValidForEditing1Test() {
        Block blockOne_ = new Block((short)0, (short)3, EnvironmentType.ROAD, "");
        DataBase dataBase_ = newData();
        dataBase_.getMap().setSideLength(1);
        assertTrue(!blockOne_.hasValidImage(dataBase_));
    }

    private static DataBase newData() {
        return new DataBase(DefaultGenerator.oneElt());
    }

    @Test
    public void isValidForEditing2Test() {
        Block blockOne_ = new Block((short)3, (short)0, EnvironmentType.ROAD, "");
        DataBase dataBase_ = newData();
        dataBase_.getMap().setSideLength(1);
        assertTrue(!blockOne_.hasValidImage(dataBase_));
    }

    @Test
    public void isValidForEditing3Test() {
        Block blockOne_ = new Block((short)2, (short)3, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = newData();
        dataBase_.addImage("tile.png", rows());
        dataBase_.getMap().setSideLength(2);
        assertTrue(!blockOne_.hasValidImage(dataBase_));
    }

    @Test
    public void isValidForEditing4Test() {
        Block blockOne_ = new Block((short)3, (short)2, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = newData();
        dataBase_.addImage("tile.png", rows());
        dataBase_.getMap().setSideLength(2);
        assertTrue(blockOne_.hasValidImage(dataBase_));
    }

    @Test
    public void isValidForEditing5Test() {
        Block blockOne_ = new Block((short)3, (short)2, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = newData();
        dataBase_.addImage("tile.png", rows());
        dataBase_.getMap().setSideLength(2);
        assertTrue(blockOne_.hasValidImage(dataBase_));
        Block blockTwo_ = new Block((short)3, (short)2, EnvironmentType.ROAD, "tile.png");
        assertTrue(blockTwo_.hasValidImage(dataBase_));
    }

    @Test
    public void isValidForEditing6Test() {
        Block blockOne_ = new Block((short)3, (short)2, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = newData();
        dataBase_.addImage("tile.png", rows());
        dataBase_.getMap().setSideLength(2);
        assertTrue(blockOne_.hasValidImage(dataBase_));
        Block blockTwo_ = new Block((short)2, (short)3, EnvironmentType.ROAD, "tile.png");
        assertTrue(!blockTwo_.hasValidImage(dataBase_));
    }

    @Test
    public void isValidForEditing7Test() {
        Block blockOne_ = new Block((short)2, (short)3, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = newData();
        dataBase_.addImage("tile.png", rows());
        dataBase_.getMap().setSideLength(2);
        assertTrue(!blockOne_.hasValidImage(dataBase_));
        Block blockTwo_ = new Block((short)3, (short)2, EnvironmentType.ROAD, "tile.png");
        assertTrue(blockTwo_.hasValidImage(dataBase_));
    }

    @Test
    public void isValidForEditing8Test() {
        Block blockOne_ = new Block((short)2, (short)3, EnvironmentType.ROAD, "tile2.png");
        DataBase dataBase_ = newData();
        dataBase_.addImage("tile.png", rows());
        dataBase_.getMap().setSideLength(2);
        assertTrue(!blockOne_.hasValidImage(dataBase_));
    }

    @Test
    public void isValidForEditing9Test() {
        Block blockOne_ = new Block((short)2, (short)3, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = newData();
        dataBase_.addImage("tile.png", rows());
        dataBase_.getMap().setSideLength(3);
        assertTrue(!blockOne_.hasValidImage(dataBase_));
    }

    static ImageArrayBaseSixtyFour rows() {
        return instance(new int[][]{row(),row(),row(),row()});
    }
    static int[] row() {
        return new int[]{1,2,6,9,-1,12};
    }
}
