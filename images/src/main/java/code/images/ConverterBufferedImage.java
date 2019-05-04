package code.images;
import code.util.*;

public final class ConverterBufferedImage {

    public static final int WHITE_RGB_INT = 256*256*256-1;

    private static boolean _handsFeet_ = true;

    private ConverterBufferedImage() {
    }

    public static int[][] stackImages(int[][] _back, int[][] _front) {
        int[][] img_ = _front;
        if (img_.length == 0) {
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
                str_[i][j] =_back[i][j];
            }
        }
        int offx_ = (w_ - wMin_) / 2;
        int offy_ = (h_ - hMin_) / 2;
        hLoc_ = _front.length + offy_;
        wLoc_ = _front[0].length + offx_;
        for (int i = offy_; i < hLoc_; i++) {
            for (int j = offx_; j < wLoc_; j++) {
                int pixel_ = _front[i-offy_][j-offx_];
                if (pixel_ != 256*256*256 - 1) {
                    str_[i][j] =pixel_;
                }
            }
        }
        return str_;
    }

    public static int getIntColor(String _color, String _separator) {
        StringList list_ = StringList.splitStrings(_color,_separator);
        Numbers<Integer> ints_ = new Numbers<Integer>();
        for (String c: list_) {
            ints_.add((int)Numbers.parseLongZero(c));
        }
        if (ints_.size() != 3) {
            return -1;
        }
        return Numbers.mod((ints_.first() *256*256 + ints_.get(1) *256 + ints_.last()), (256*256*256));
    }

    public static boolean containsInsideWhites(int[][] _buffered) {
        int h_ = _buffered.length;
        int w_ = _buffered[0].length;
        int white_ = WHITE_RGB_INT;
        EqList<IntPoint> addedPixels_ = whitePixels(_buffered);
        for (int i = CustList.FIRST_INDEX; i < h_; i++) {
            for (int j = CustList.FIRST_INDEX; j < w_; j++) {
                if (addedPixels_.containsObj(new IntPoint(j,i))) {
                    continue;
                }
                int rgb_ = _buffered[i][j];
                if (rgb_ == white_) {
                    return true;
                }
            }
        }
        return false;
    }

    public static EqList<IntPoint> containedWhiteInside(int[][] _buffered) {
        int h_ = _buffered.length;
        int w_ = _buffered[0].length;
        int white_ = WHITE_RGB_INT;
        EqList<IntPoint> addedPixels_ = whitePixels(_buffered);
        EqList<IntPoint> list_ = new EqList<IntPoint>();
        for (int i = CustList.FIRST_INDEX; i < h_; i++) {
            for (int j = CustList.FIRST_INDEX; j < w_; j++) {
                if (addedPixels_.containsObj(new IntPoint(j,i))) {
                    continue;
                }
                int rgb_ = _buffered[i][j];
                if (rgb_ != white_) {
                    continue;
                }
                list_.add(new IntPoint(j,i));
            }
        }
        return list_;
    }

    public static EqList<IntPoint> whitePixels(int[][] _buffered) {
        int h_ = _buffered.length;
        int w_ = _buffered[0].length;
        int white_ = WHITE_RGB_INT;
        EqList<IntPoint> addedPixels_ = new EqList<IntPoint>();
        EqList<IntPoint> currentPixels_ = new EqList<IntPoint>();
        EqList<IntPoint> newPixels_ = new EqList<IntPoint>();
        if (isHandsFeet()) {
            if (_buffered[CustList.FIRST_INDEX][CustList.FIRST_INDEX] == white_) {
                addedPixels_.add(new IntPoint((int)CustList.FIRST_INDEX, (int)CustList.FIRST_INDEX));
            }
            if (_buffered[CustList.FIRST_INDEX][w_ - 1] == white_) {
                addedPixels_.add(new IntPoint(w_ - 1, (int)CustList.FIRST_INDEX));
            }
            if (_buffered[h_ - 1][CustList.FIRST_INDEX] == white_) {
                addedPixels_.add(new IntPoint((int)CustList.FIRST_INDEX, h_ - 1));
            }
            if (_buffered[h_ - 1][w_ - 1] == white_) {
                addedPixels_.add(new IntPoint(w_ -1, h_ - 1));
            }
        } else {
            for (int i = CustList.FIRST_INDEX; i < w_; i++) {
                if (_buffered[CustList.FIRST_INDEX][i] == white_) {
                    addedPixels_.add(new IntPoint(i, (int)CustList.FIRST_INDEX));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < w_; i++) {
                if (_buffered[h_-1][i] == white_) {
                    addedPixels_.add(new IntPoint(i, h_ - 1));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < h_; i++) {
                if (_buffered[i][CustList.FIRST_INDEX] == white_) {
                    addedPixels_.add(new IntPoint((int)CustList.FIRST_INDEX, i));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < h_; i++) {
                if (_buffered[i][w_-1] == white_) {
                    addedPixels_.add(new IntPoint(w_ - 1, i));
                }
            }
            addedPixels_.removeDuplicates();
        }
        currentPixels_.addAllElts(addedPixels_);
        while (true) {
            newPixels_ = new EqList<IntPoint>();
            for (IntPoint coords_: currentPixels_) {
                for (IntPoint coordsChild_: getNext(w_, h_, coords_)) {
                    if (addedPixels_.containsObj(coordsChild_)) {
                        continue;
                    }
                    int rgb_ = _buffered[coordsChild_.getYcoords()][coordsChild_.getXcoords()];
                    if (rgb_ != white_) {
                        continue;
                    }
                    addedPixels_.add(coordsChild_);
                    newPixels_.add(coordsChild_);
                }
            }
            if (newPixels_.isEmpty()) {
                break;
            }
            currentPixels_ = new EqList<IntPoint>(newPixels_);
        }
        return addedPixels_;
    }

    public static boolean isHandsFeet() {
        return _handsFeet_;
    }

    public static void setHandsFeet(boolean _handsFeet) {
        _handsFeet_ = _handsFeet;
    }

    public static String getSquareColorSixtyFour(String _color,
            String _separatorRgb, int _sideLength) {
        StringList list_ = StringList.splitStrings(_color,_separatorRgb);
        Numbers<Integer> ints_ = new Numbers<Integer>();
        for (String c: list_) {
            ints_.add(Numbers.parseInt(c));
        }
        int rgb_ = Numbers.mod(ints_.first() *256*256 + ints_.get(1) *256 + ints_.last(), 256*256*256);
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
        if (_visited.getXcoords() - 1 >= CustList.FIRST_INDEX) {
            list_.add(new IntPoint(_visited.getXcoords() - 1, _visited.getYcoords()));
        }
        if (_visited.getYcoords() + 1 < _h) {
            list_.add(new IntPoint(_visited.getXcoords(), _visited.getYcoords() + 1));
        }
        if (_visited.getYcoords() - 1 >= CustList.FIRST_INDEX) {
            list_.add(new IntPoint(_visited.getXcoords(), _visited.getYcoords() - 1));
        }
        return list_;
    }
    public static CustList<EqList<IntPoint>> getPolygons(EqList<IntPoint> _classes) {
        ObjectMap<IntPoint,EqList<IntPoint>> mapClasses_;
        mapClasses_ = new ObjectMap<IntPoint,EqList<IntPoint>>();
        for (IntPoint point_: _classes) {
            mapClasses_.put(point_, new EqList<IntPoint>(point_));
        }
        CustList<EqList<IntPoint>> polygons_ = new CustList<EqList<IntPoint>>();
        for (IntPoint point_: mapClasses_.getKeys()) {
            EqList<IntPoint> visitedPoints_ = mapClasses_.getVal(point_);
            EqList<IntPoint> currentPoints_ = new EqList<IntPoint>(point_);
            EqList<IntPoint> newPoints_;
            while (true) {
                newPoints_ = new EqList<IntPoint>();
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
                    EqList<IntPoint> nextPoints_ = new EqList<IntPoint>();
                    nextPoints_.add(ptOne_);
                    nextPoints_.add(ptTwo_);
                    nextPoints_.add(ptThree_);
                    nextPoints_.add(ptFour_);
                    for (IntPoint next_: nextPoints_) {
                        if (!_classes.containsObj(next_)) {
                            continue;
                        }
                        if (visitedPoints_.containsObj(next_)) {
                            continue;
                        }
                        newPoints_.add(next_);
                        visitedPoints_.add(next_);
                    }
                }
                if (newPoints_.isEmpty()) {
                    break;
                }
                currentPoints_ = new EqList<IntPoint>(newPoints_);
            }
            visitedPoints_.sortElts(new ComparatorIntPoint());
            polygons_.add(visitedPoints_);
        }
        return polygons_;
    }
}
