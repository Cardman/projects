package code.util.opers;
import code.util.CustList;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.PairNumber;
import code.util.comparators.ComparatorPairNumber;

public final class PairUtil {

    private PairUtil() {
    }

    public static EqList<EqList<PairNumber<Integer,Integer>>> getPolygons(EqList<PairNumber<Integer,Integer>> _classes) {
        ObjectMap<PairNumber<Integer,Integer>,EqList<PairNumber<Integer,Integer>>> mapClasses_;
        mapClasses_ = new ObjectMap<PairNumber<Integer,Integer>,EqList<PairNumber<Integer,Integer>>>();
        for (PairNumber<Integer,Integer> point_: _classes) {
            mapClasses_.put(point_, new EqList<PairNumber<Integer,Integer>>(point_));
        }
        EqList<EqList<PairNumber<Integer,Integer>>> polygons_ = new EqList<EqList<PairNumber<Integer,Integer>>>();
        for (PairNumber<Integer,Integer> point_: mapClasses_.getKeys()) {
            EqList<PairNumber<Integer,Integer>> visitedPoints_ = mapClasses_.getVal(point_);
            EqList<PairNumber<Integer,Integer>> currentPoints_ = new EqList<PairNumber<Integer,Integer>>(point_);
            EqList<PairNumber<Integer,Integer>> newPoints_ = new EqList<PairNumber<Integer,Integer>>();
            while (true) {
                newPoints_ = new EqList<PairNumber<Integer,Integer>>();
                for (PairNumber<Integer,Integer> currentPoint_: currentPoints_) {
                    PairNumber<Integer,Integer> ptOne_ = new PairNumber<Integer,Integer>();
                    ptOne_.setFirst(currentPoint_.getFirst()+1);
                    ptOne_.setSecond(currentPoint_.getSecond());
                    PairNumber<Integer,Integer> ptTwo_ = new PairNumber<Integer,Integer>();
                    ptTwo_.setFirst(currentPoint_.getFirst());
                    ptTwo_.setSecond(currentPoint_.getSecond()+1);
                    PairNumber<Integer,Integer> ptThree_ = new PairNumber<Integer,Integer>();
                    ptThree_.setFirst(currentPoint_.getFirst()-1);
                    ptThree_.setSecond(currentPoint_.getSecond());
                    PairNumber<Integer,Integer> ptFour_ = new PairNumber<Integer,Integer>();
                    ptFour_.setFirst(currentPoint_.getFirst());
                    ptFour_.setSecond(currentPoint_.getSecond()-1);
                    EqList<PairNumber<Integer,Integer>> nextPoints_ = new EqList<PairNumber<Integer,Integer>>();
                    nextPoints_.add(ptOne_);
                    nextPoints_.add(ptTwo_);
                    nextPoints_.add(ptThree_);
                    nextPoints_.add(ptFour_);
                    for (PairNumber<Integer,Integer> next_: nextPoints_) {
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
                currentPoints_ = new EqList<PairNumber<Integer,Integer>>(newPoints_);
            }
            visitedPoints_.sortElts(new ComparatorPairNumber<Integer,Integer>());
            polygons_.add(visitedPoints_);
        }
        polygons_.removeDuplicates();
        return polygons_;
    }

    public static EqList<PairNumber<Integer,Integer>> getNext(int _w, int _h, PairNumber<Integer,Integer> _visited) {
        EqList<PairNumber<Integer,Integer>> list_ = new EqList<PairNumber<Integer,Integer>>();
        if (_visited.getFirst() + 1 < _w) {
            list_.add(new PairNumber<Integer,Integer>(_visited.getFirst() + 1, _visited.getSecond()));
        }
        if (_visited.getFirst() - 1 >= CustList.FIRST_INDEX) {
            list_.add(new PairNumber<Integer,Integer>(_visited.getFirst() - 1, _visited.getSecond()));
        }
        if (_visited.getSecond() + 1 < _h) {
            list_.add(new PairNumber<Integer,Integer>(_visited.getFirst(), _visited.getSecond() + 1));
        }
        if (_visited.getSecond() - 1 >= CustList.FIRST_INDEX) {
            list_.add(new PairNumber<Integer,Integer>(_visited.getFirst(), _visited.getSecond() - 1));
        }
        return list_;
    }
}
