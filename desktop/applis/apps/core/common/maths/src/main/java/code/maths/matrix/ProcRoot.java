package code.maths.matrix;

import code.maths.LgInt;
import code.maths.Rate;
import code.util.CustList;

final class ProcRoot {
    private int mult;
    boolean exit(LgInt _c, LgInt _m, CustList<RootPol> _r, long _deg, Polynom _pairPol, Polynom _impairPol) {
        Rate cand_=new Rate(_c,_m);
        Rate image_= _pairPol.image(cand_);
        Rate imageTwo_= _impairPol.image(cand_);
        if(Rate.eq(image_,imageTwo_)) {
            cand_.changeSignum();
//            boolean ct_ = containsRoot(_r, cand_);
            _r.add(new RootPol(cand_,1));
            if (mult + 1 == _deg) {
//            if (exitRoots(_deg, mult, ct_))
                return true;
            }
            mult++;
//            mult = incr(ct_,mult);
        }
        if(Rate.eq(image_,imageTwo_.opposNb())){
            cand_=new Rate(_c,_m);
//            boolean ct_ = containsRoot(_r, cand_);
            _r.add(new RootPol(cand_,1));
            if (mult + 1 == _deg) {
//            if (exitRoots(_deg, mult, ct_))
                return true;
            }
            mult++;
//            mult = incr(ct_,mult);
        }
        return false;
    }

//    private static int incr(boolean _ct, int _mult) {
//        if(!_ct) {
//            return _mult + 1;
//        }
//        return _mult;
//    }
//    private static boolean exitRoots(long _deg, int _mult, boolean _ct) {
//        return !_ct && _mult + 1 == _deg;
//    }
//
//    private static boolean containsRoot(CustList<RootPol> _roots, Rate _value) {
////        for (RootPol r: _roots) {
////            if (r.getValue().eq(_value)) {
////                return true;
////            }
////        }
//        _roots.add(new RootPol(_value,1));
//        return false;
//    }
    int getMult() {
        return mult;
    }
}
