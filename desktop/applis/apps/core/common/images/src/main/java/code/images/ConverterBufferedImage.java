package code.images;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ConverterBufferedImage {

    public static final int WHITE_RGB_INT = 256*256*256-1;

    private ConverterBufferedImage() {
    }

    public static int[][] stackImages(int[][] _back, int[][] _front) {
        if (_front.length == 0) {
            return _back;
        }
        int w_ = Math.max(_back[0].length, _front[0].length);
        int h_ = Math.max(_back.length, _front.length);
        int wMin_ = _front[0].length;
        int hMin_ = _front.length;

        int[][] str_ = new int[h_][w_];
        int hLoc_ = _back.length;
        int wLoc_ = _back[0].length;
        for (int i = 0; i < hLoc_; i++) {
            for (int j = 0; j < wLoc_; j++) {
                copy(_back, str_, i, j);
            }
        }
        int offx_ = (w_ - wMin_) / 2;
        int offy_ = (h_ - hMin_) / 2;
        hLoc_ = _front.length + offy_;
        wLoc_ = _front[0].length + offx_;
        for (int i = offy_; i < hLoc_; i++) {
            for (int j = offx_; j < wLoc_; j++) {
                int pixel_ = _front[i-offy_][j-offx_];
                if (pixel_ != WHITE_RGB_INT) {
                    str_[i][j] =pixel_;
                }
            }
        }
        return str_;
    }

    private static void copy(int[][] _back, int[][] _str, int _i, int _j) {
        _str[_i][_j] =_back[_i][_j];
    }

    public static int getIntColor(String _color, String _separator) {
        StringList list_ = StringUtil.splitStrings(_color,_separator);
        Ints ints_ = new Ints();
        for (String c: list_) {
            ints_.add((int) NumberUtil.parseLongZero(c));
        }
        if (ints_.size() != 3) {
            return -1;
        }
        return NumberUtil.mod(ints_.first() *256*256 + ints_.get(1) *256 + ints_.last(), 256*256*256);
    }

    public static CustList<IntPoint> containedWhiteInside(boolean _hf,int[][] _buffered) {
        int h_ = _buffered.length;
        int w_ = _buffered[0].length;
        CustList<IntPoint> addedPixels_ = whitePixels(_hf,_buffered);
        CustList<IntPoint> list_ = new CustList<IntPoint>();
        for (int i = IndexConstants.FIRST_INDEX; i < h_; i++) {
            for (int j = IndexConstants.FIRST_INDEX; j < w_; j++) {
                if (contains(addedPixels_,new IntPoint(j,i))) {
                    continue;
                }
                tryAddNew(_buffered[i][j], list_, i, j);
            }
        }
        return list_;
    }

    private static void tryAddNew(int _rgb, CustList<IntPoint> _list, int _i, int _j) {
        if (_rgb != WHITE_RGB_INT) {
            return;
        }
        _list.add(new IntPoint(_j, _i));
    }

    public static CustList<IntPoint> whitePixels(boolean _hf,int[][] _buffered) {
        int h_ = _buffered.length;
        int w_ = _buffered[0].length;
        int white_ = WHITE_RGB_INT;
        CustList<IntPoint> addedPixels_ = new CustList<IntPoint>();
        if (_hf) {
            bounds(_buffered, h_, w_, white_, addedPixels_);
        } else {
            border(_buffered, h_, w_, white_, addedPixels_);
        }
        return loop(_buffered, h_, w_, white_, addedPixels_);
    }

    private static void border(int[][] _buffered, int _h, int _w, int _white, CustList<IntPoint> _addedPixels) {
        for (int i = IndexConstants.FIRST_INDEX; i < _w; i++) {
            if (_buffered[IndexConstants.FIRST_INDEX][i] == _white) {
                _addedPixels.add(new IntPoint(i, IndexConstants.FIRST_INDEX));
            }
        }
        for (int i = IndexConstants.FIRST_INDEX; i < _w; i++) {
            if (_buffered[_h -1][i] == _white) {
                _addedPixels.add(new IntPoint(i, _h - 1));
            }
        }
        for (int i = IndexConstants.FIRST_INDEX; i < _h; i++) {
            if (_buffered[i][IndexConstants.FIRST_INDEX] == _white) {
                _addedPixels.add(new IntPoint(IndexConstants.FIRST_INDEX, i));
            }
        }
        for (int i = IndexConstants.FIRST_INDEX; i < _h; i++) {
            if (_buffered[i][_w -1] == _white) {
                _addedPixels.add(new IntPoint(_w - 1, i));
            }
        }
        removeDuplicates(_addedPixels);
    }

    private static void removeDuplicates(CustList<IntPoint> _addedPixels) {
        CustList<IntPoint> next_ = new CustList<IntPoint>();
        for (IntPoint i: _addedPixels) {
            if (contains(next_,i)) {
                continue;
            }
            next_.add(i);
        }
        _addedPixels.clear();
        _addedPixels.addAllElts(next_);
    }

    private static void bounds(int[][] _buffered, int _h, int _w, int _white, CustList<IntPoint> _addedPixels) {
        if (_buffered[IndexConstants.FIRST_INDEX][IndexConstants.FIRST_INDEX] == _white) {
            _addedPixels.add(new IntPoint(IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX));
        }
        if (_buffered[IndexConstants.FIRST_INDEX][_w - 1] == _white) {
            _addedPixels.add(new IntPoint(_w - 1, IndexConstants.FIRST_INDEX));
        }
        if (_buffered[_h - 1][IndexConstants.FIRST_INDEX] == _white) {
            _addedPixels.add(new IntPoint(IndexConstants.FIRST_INDEX, _h - 1));
        }
        if (_buffered[_h - 1][_w - 1] == _white) {
            _addedPixels.add(new IntPoint(_w -1, _h - 1));
        }
    }

    private static CustList<IntPoint> loop(int[][] _buffered, int _h, int _w, int _white, CustList<IntPoint> _addedPixels) {
        CustList<IntPoint> newPixels_;
        CustList<IntPoint> currentPixels_ = new CustList<IntPoint>();
        currentPixels_.addAllElts(_addedPixels);
        while (true) {
            newPixels_ = new CustList<IntPoint>();
            nextPixels(_buffered, _h, _w, _white, _addedPixels, newPixels_, currentPixels_);
            if (newPixels_.isEmpty()) {
                break;
            }
            currentPixels_ = new CustList<IntPoint>(newPixels_);
        }
        return _addedPixels;
    }

    private static void nextPixels(int[][] _buffered, int _h, int _w, int _white, CustList<IntPoint> _addedPixels, CustList<IntPoint> _newPixels, CustList<IntPoint> _currentPixels) {
        for (IntPoint coords_: _currentPixels) {
            for (IntPoint coordsChild_: getNext(_w, _h, coords_)) {
                if (contains(_addedPixels,coordsChild_)) {
                    continue;
                }
                tryAddNew(_buffered[coordsChild_.getYcoords()], _white, _addedPixels, _newPixels, coordsChild_);
            }
        }
    }

    private static void tryAddNew(int[] _ints, int _white, CustList<IntPoint> _addedPixels, CustList<IntPoint> _newPixels, IntPoint _coordsChild) {
        int rgb_ = _ints[_coordsChild.getXcoords()];
        if (rgb_ != _white) {
            return;
        }
        _addedPixels.add(_coordsChild);
        _newPixels.add(_coordsChild);
    }

    public static String getSquareColorSixtyFour(String _color,
            String _separatorRgb, int _sideLength) {
        StringList list_ = StringUtil.splitStrings(_color,_separatorRgb);
        Ints ints_ = new Ints();
        for (String c: list_) {
            ints_.add(NumberUtil.parseInt(c));
        }
        int rgb_ = NumberUtil.mod(ints_.first() *256*256 + ints_.get(1) *256 + ints_.last(), 256*256*256);
        int[][] pixels_ = new int[_sideLength][_sideLength];
        for (int i = 0; i < _sideLength; i++) {
            for (int j = 0; j < _sideLength; j++) {
                pixels_[i][j] = rgb_;
            }
        }
        return BaseSixtyFourUtil.getStringByImage(pixels_);
    }

    public static CustList<IntPoint> getNext(int _w, int _h, IntPoint _visited) {
        CustList<IntPoint> list_ = new CustList<IntPoint>();
        if (_visited.getXcoords() + 1 < _w) {
            list_.add(new IntPoint(_visited.getXcoords() + 1, _visited.getYcoords()));
        }
        if (_visited.getXcoords() - 1 >= IndexConstants.FIRST_INDEX) {
            list_.add(new IntPoint(_visited.getXcoords() - 1, _visited.getYcoords()));
        }
        if (_visited.getYcoords() + 1 < _h) {
            list_.add(new IntPoint(_visited.getXcoords(), _visited.getYcoords() + 1));
        }
        if (_visited.getYcoords() - 1 >= IndexConstants.FIRST_INDEX) {
            list_.add(new IntPoint(_visited.getXcoords(), _visited.getYcoords() - 1));
        }
        return list_;
    }
    public static CustList<CustList<IntPoint>> getPolygons(CustList<IntPoint> _classes) {
        CustList<CustList<IntPoint>> polygons_ = new CustList<CustList<IntPoint>>();
        for (IntPoint point_: _classes) {
            processPoint(_classes, polygons_, point_, new CustList<IntPoint>(point_));
        }
        return polygons_;
    }

    private static void processPoint(CustList<IntPoint> _classes, CustList<CustList<IntPoint>> _polygons, IntPoint _point, CustList<IntPoint> _visited) {
        CustList<IntPoint> currentPoints_ = new CustList<IntPoint>(_point);
        CustList<IntPoint> newPoints_;
        while (true) {
            newPoints_ = new CustList<IntPoint>();
            for (IntPoint currentPoint_: currentPoints_) {
                IntPoint ptOne_ = new IntPoint();
                ptOne_.setXcoords(currentPoint_.getXcoords()+1);
                ptOne_.setYcoords(currentPoint_.getYcoords());
                IntPoint ptTwo_ = new IntPoint();
                ptTwo_.setXcoords(currentPoint_.getXcoords());
                ptTwo_.setYcoords(currentPoint_.getYcoords()+1);
                IntPoint ptThree_ = new IntPoint();
                ptThree_.setXcoords(currentPoint_.getXcoords()-1);
                ptThree_.setYcoords(currentPoint_.getYcoords());
                IntPoint ptFour_ = new IntPoint();
                ptFour_.setXcoords(currentPoint_.getXcoords());
                ptFour_.setYcoords(currentPoint_.getYcoords()-1);
                CustList<IntPoint> nextPoints_ = new CustList<IntPoint>();
                nextPoints_.add(ptOne_);
                nextPoints_.add(ptTwo_);
                nextPoints_.add(ptThree_);
                nextPoints_.add(ptFour_);
                tryAddNext(_classes, _visited, newPoints_, nextPoints_);
            }
            if (newPoints_.isEmpty()) {
                break;
            }
            currentPoints_ = new CustList<IntPoint>(newPoints_);
        }
        _visited.sortElts(new ComparatorIntPoint());
        _polygons.add(_visited);
    }

    private static void tryAddNext(CustList<IntPoint> _classes, CustList<IntPoint> _visitedPoints, CustList<IntPoint> _newPoints, CustList<IntPoint> _nextPoints) {
        for (IntPoint next_: _nextPoints) {
            if (!contains(_classes,next_) || contains(_visitedPoints,next_)) {
                continue;
            }
            _newPoints.add(next_);
            _visitedPoints.add(next_);
        }
    }
    private static boolean contains(CustList<IntPoint> _ls, IntPoint _pt) {
        for (IntPoint i: _ls) {
            if (_pt.eq(i)) {
                return true;
            }
        }
        return false;
    }
}
