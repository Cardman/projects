package aiki.map.levels;
import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiki.db.DataBase;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.tree.util.Dims;
import aiki.util.Point;
import code.images.Image;
import code.util.*;
import code.util.StringList;


public class BlockTest {


    @Test
    public void bounds1Test() {
        Block block_ = new Block((short)5, (short)3, EnvironmentType.ROAD, "");
        BlockBounds bounds_ = block_.bounds(new Point((short)2,(short)3));
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
        BlockBounds intersection_ = Block.intersection(blockOne_.bounds(new Point((short)2,(short)3)), blockTwo_.bounds(new Point((short)4,(short)4)));
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
        BlockBounds intersection_ = Block.intersection(blockOne_.bounds(new Point((short)2,(short)3)), blockTwo_.bounds(new Point((short)4,(short)6)));
        assertTrue(!intersection_.isValid());
    }

    @Test
    public void isValidForEditing1Test() {
        Block blockOne_ = new Block((short)0, (short)3, EnvironmentType.ROAD, "");
        DataBase dataBase_ = new DataBase();
        dataBase_.getMap().setSideLength(1);
        assertTrue(!blockOne_.isValidForEditing(dataBase_));
    }

    @Test
    public void isValidForEditing2Test() {
        Block blockOne_ = new Block((short)3, (short)0, EnvironmentType.ROAD, "");
        DataBase dataBase_ = new DataBase();
        dataBase_.getMap().setSideLength(1);
        assertTrue(!blockOne_.isValidForEditing(dataBase_));
    }

    private static StringList getPixels() {
        StringList list_ = new StringList("1","2","6","9","-1","12");
        list_.addAllElts(new StringList("1","2","6","9","-1","12"));
        list_.addAllElts(new StringList("1","2","6","9","-1","12"));
        list_.addAllElts(new StringList("1","2","6","9","-1","12"));
        return list_;
    }

    @Test
    public void isValidForEditing3Test() {
        Block blockOne_ = new Block((short)2, (short)3, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = new DataBase();
        dataBase_.addImage("tile.png", getImageByString(StringList.concat("6",String.valueOf(Image.SEPARATOR_CHAR),getPixels().join(Image.SEPARATOR_CHAR))));
        dataBase_.getMap().setSideLength(2);
        assertTrue(!blockOne_.isValidForEditing(dataBase_));
        assertEq(0, dataBase_.getImagesDimensions().size());
    }

    @Test
    public void isValidForEditing4Test() {
        Block blockOne_ = new Block((short)3, (short)2, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = new DataBase();
        dataBase_.addImage("tile.png", getImageByString(StringList.concat("6",String.valueOf(Image.SEPARATOR_CHAR),getPixels().join(Image.SEPARATOR_CHAR))));
        dataBase_.getMap().setSideLength(2);
        assertTrue(blockOne_.isValidForEditing(dataBase_));
        assertEq(1, dataBase_.getImagesDimensions().size());
        assertTrue(dataBase_.getImagesDimensions().contains("tile.png"));
        assertEq(new Dims(6, 4), dataBase_.getImagesDimensions().getVal("tile.png"));
    }

    @Test
    public void isValidForEditing5Test() {
        Block blockOne_ = new Block((short)3, (short)2, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = new DataBase();
        dataBase_.addImage("tile.png", getImageByString(StringList.concat("6",String.valueOf(Image.SEPARATOR_CHAR),getPixels().join(Image.SEPARATOR_CHAR))));
        dataBase_.getMap().setSideLength(2);
        assertTrue(blockOne_.isValidForEditing(dataBase_));
        Block blockTwo_ = new Block((short)3, (short)2, EnvironmentType.ROAD, "tile.png");
        assertTrue(blockTwo_.isValidForEditing(dataBase_));
        assertEq(1, dataBase_.getImagesDimensions().size());
        assertTrue(dataBase_.getImagesDimensions().contains("tile.png"));
        assertEq(new Dims(6, 4), dataBase_.getImagesDimensions().getVal("tile.png"));
    }

    @Test
    public void isValidForEditing6Test() {
        Block blockOne_ = new Block((short)3, (short)2, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = new DataBase();
        dataBase_.addImage("tile.png", getImageByString(StringList.concat("6",String.valueOf(Image.SEPARATOR_CHAR),getPixels().join(Image.SEPARATOR_CHAR))));
        dataBase_.getMap().setSideLength(2);
        assertTrue(blockOne_.isValidForEditing(dataBase_));
        Block blockTwo_ = new Block((short)2, (short)3, EnvironmentType.ROAD, "tile.png");
        assertTrue(!blockTwo_.isValidForEditing(dataBase_));
        assertEq(1, dataBase_.getImagesDimensions().size());
        assertTrue(dataBase_.getImagesDimensions().contains("tile.png"));
        assertEq(new Dims(6, 4), dataBase_.getImagesDimensions().getVal("tile.png"));
    }

    @Test
    public void isValidForEditing7Test() {
        Block blockOne_ = new Block((short)2, (short)3, EnvironmentType.ROAD, "tile.png");
        DataBase dataBase_ = new DataBase();
        dataBase_.addImage("tile.png", getImageByString(StringList.concat("6",String.valueOf(Image.SEPARATOR_CHAR),getPixels().join(Image.SEPARATOR_CHAR))));
        dataBase_.getMap().setSideLength(2);
        assertTrue(!blockOne_.isValidForEditing(dataBase_));
        assertEq(0, dataBase_.getImagesDimensions().size());
        Block blockTwo_ = new Block((short)3, (short)2, EnvironmentType.ROAD, "tile.png");
        assertTrue(blockTwo_.isValidForEditing(dataBase_));
        assertEq(1, dataBase_.getImagesDimensions().size());
        assertTrue(dataBase_.getImagesDimensions().contains("tile.png"));
        assertEq(new Dims(6, 4), dataBase_.getImagesDimensions().getVal("tile.png"));
    }

    @Test
    public void isValidForEditing8Test() {
        Block blockOne_ = new Block((short)2, (short)3, EnvironmentType.ROAD, "tile2.png");
        DataBase dataBase_ = new DataBase();
        dataBase_.addImage("tile.png", getImageByString(StringList.concat("6",String.valueOf(Image.SEPARATOR_CHAR),getPixels().join(Image.SEPARATOR_CHAR))));
        dataBase_.getMap().setSideLength(2);
        assertTrue(!blockOne_.isValidForEditing(dataBase_));
        assertEq(0, dataBase_.getImagesDimensions().size());
    }
    private static int[][] getImageByString(String _string) {
        Image i_ = new Image(_string);
        Ints pixels_ = i_.getPixels();
        int width_ = i_.getWidth();
        int height_ = i_.getHeight();
        int[][] img_ = new int[height_][width_];
        for (int i = 0; i < height_; i++) {
            for (int j = 0; j < width_; j++) {
                img_[i][j] = pixels_.get(j + width_ * i);
            }
        }
        return img_;
    }
}
