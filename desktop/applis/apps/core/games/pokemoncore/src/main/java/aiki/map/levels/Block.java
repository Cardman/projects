package aiki.map.levels;
import aiki.db.DataBase;
import aiki.map.levels.enums.EnvironmentType;
import aiki.util.Point;
import code.util.CustList;


public final class Block {

    private short width;

    private short height;

    private short indexApparition = CustList.INDEX_NOT_FOUND_ELT;

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
            return indexApparition == CustList.INDEX_NOT_FOUND_ELT;
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
        if (image_.length != height * scale_) {
            return false;
        }
        return true;
    }

    public BlockBounds bounds(Point _leftTop) {
        BlockBounds bounds_ = new BlockBounds();
        bounds_.setxLeftTop(_leftTop.getx());
        bounds_.setyLeftTop(_leftTop.gety());
        bounds_.setxRightTop((short) (_leftTop.getx()+width-1));
        bounds_.setyRightTop(_leftTop.gety());
        bounds_.setxLeftBottom(_leftTop.getx());
        bounds_.setyLeftBottom((short) (_leftTop.gety()+height-1));
        bounds_.setxRightBottom((short) (_leftTop.getx()+width-1));
        bounds_.setyRightBottom((short) (_leftTop.gety()+height-1));
        return bounds_;
    }

    public static BlockBounds intersection(BlockBounds _currentBounds,BlockBounds _otherBounds) {
        BlockBounds intersection_ = new BlockBounds();
        intersection_.setxLeftTop((short) Math.max(_currentBounds.getxLeftTop(), _otherBounds.getxLeftTop()));
        intersection_.setyLeftTop((short) Math.max(_currentBounds.getyLeftTop(), _otherBounds.getyLeftTop()));
        intersection_.setxLeftBottom((short) Math.max(_currentBounds.getxLeftBottom(), _otherBounds.getxLeftBottom()));
        intersection_.setyLeftBottom((short) Math.min(_currentBounds.getyLeftBottom(), _otherBounds.getyLeftBottom()));
        intersection_.setxRightTop((short) Math.min(_currentBounds.getxRightTop(), _otherBounds.getxRightTop()));
        intersection_.setyRightTop((short) Math.max(_currentBounds.getyRightTop(), _otherBounds.getyRightTop()));
        intersection_.setxRightBottom((short) Math.min(_currentBounds.getxRightBottom(), _otherBounds.getxRightBottom()));
        intersection_.setyRightBottom((short) Math.min(_currentBounds.getyRightBottom(), _otherBounds.getyRightBottom()));
        intersection_.invalidate();
        return intersection_;
    }

    public short getWidth() {
        return width;
    }

    public void setWidth(short _width) {
        width = _width;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short _height) {
        height = _height;
    }

    public short getIndexApparition() {
        return indexApparition;
    }

    public void setIndexApparition(short _indexApparition) {
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
