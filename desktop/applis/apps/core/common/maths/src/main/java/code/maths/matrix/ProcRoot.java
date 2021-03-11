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
            boolean ctd_ = containedRoot(_r, cand_);
            if (exitRoots(_deg, mult, ctd_)) {
                return true;
            }
            mult = incr(ctd_,mult);
        }
        if(Rate.eq(image_,imageTwo_.opposNb())){
            cand_=new Rate(_c,_m);
            boolean ctd_ = containedRoot(_r, cand_);
            if (exitRoots(_deg, mult, ctd_)) {
                return true;
            }
            mult = incr(ctd_,mult);
        }
        return false;
    }

    private static int incr(boolean _ctd, int _mult) {
        if(!_ctd) {
            return _mult + 1;
        }
        return _mult;
    }
    private static boolean exitRoots(long _deg, int _mult, boolean _ctd) {
        return !_ctd && _mult + 1 == _deg;
    }

    private static boolean containedRoot(CustList<RootPol> _roots, Rate _value) {
        for (RootPol r: _roots) {
            if (r.getValue().eq(_value)) {
                return true;
            }
        }
        _roots.add(new RootPol(_value,1));
        return false;
    }
    int getMult() {
        return mult;
    }
}
