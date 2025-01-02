package aiki.map.levels;
import aiki.db.DataBase;
import aiki.map.levels.enums.EnvironmentType;
import aiki.util.Point;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;


public final class Block {

    private int width;

    private int height;

    private int indexApparition = IndexConstants.INDEX_NOT_FOUND_ELT;

    private String tileFileName;

    private EnvironmentType type;

    public Block() {
        type = EnvironmentType.NOTHING;
    }

    public Block(short _width, short _height, EnvironmentType _env, String _tileFileName) {
        width = _width;
        height = _height;
        type = _env;
        tileFileName = _tileFileName;
    }
    public boolean isValid() {
        if (width <= 0 || height <= 0) {
            return false;
        }
        if (type == EnvironmentType.NOTHING) {
            return indexApparition == IndexConstants.INDEX_NOT_FOUND_ELT;
        }
        return true;
    }

    public boolean hasValidImage(DataBase _data) {
        int[][] image_ = _data.getImage(tileFileName);
        if (image_.length == 0) {
            return false;
        }
        int scale_ = _data.getMap().getSideLength();
        if (image_[0].length != width * scale_) {
            return false;
        }
        return image_.length == height * scale_;
    }

    public BlockBounds bounds(Point _leftTop) {
        BlockBounds bounds_ = new BlockBounds();
        bounds_.setxLeftTop(_leftTop.getx());
        bounds_.setyLeftTop(_leftTop.gety());
        bounds_.setxRightTop(_leftTop.getx()+width-1);
        bounds_.setyRightTop(_leftTop.gety());
        bounds_.setxLeftBottom(_leftTop.getx());
        bounds_.setyLeftBottom(_leftTop.gety()+height-1);
        bounds_.setxRightBottom(_leftTop.getx()+width-1);
        bounds_.setyRightBottom(_leftTop.gety()+height-1);
        return bounds_;
    }

    public static BlockBounds intersection(BlockBounds _currentBounds,BlockBounds _otherBounds) {
        BlockBounds intersection_ = new BlockBounds();
        intersection_.setxLeftTop(NumberUtil.max(_currentBounds.getxLeftTop(), _otherBounds.getxLeftTop()));
        intersection_.setyLeftTop(NumberUtil.max(_currentBounds.getyLeftTop(), _otherBounds.getyLeftTop()));
        intersection_.setxLeftBottom(NumberUtil.max(_currentBounds.getxLeftBottom(), _otherBounds.getxLeftBottom()));
        intersection_.setyLeftBottom(NumberUtil.min(_currentBounds.getyLeftBottom(), _otherBounds.getyLeftBottom()));
        intersection_.setxRightTop(NumberUtil.min(_currentBounds.getxRightTop(), _otherBounds.getxRightTop()));
        intersection_.setyRightTop(NumberUtil.max(_currentBounds.getyRightTop(), _otherBounds.getyRightTop()));
        intersection_.setxRightBottom(NumberUtil.min(_currentBounds.getxRightBottom(), _otherBounds.getxRightBottom()));
        intersection_.setyRightBottom(NumberUtil.min(_currentBounds.getyRightBottom(), _otherBounds.getyRightBottom()));
        intersection_.invalidate();
        return intersection_;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int _width) {
        width = _width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int _height) {
        height = _height;
    }

    public int getIndexApparition() {
        return indexApparition;
    }

    public void setIndexApparition(int _indexApparition) {
        indexApparition = _indexApparition;
    }

    public String getTileFileName() {
        return tileFileName;
    }

    public void setTileFileName(String _tileFileName) {
        tileFileName = _tileFileName;
    }

    public EnvironmentType getType() {
        return type;
    }

    public void setType(EnvironmentType _type) {
        type = _type;
    }
}
