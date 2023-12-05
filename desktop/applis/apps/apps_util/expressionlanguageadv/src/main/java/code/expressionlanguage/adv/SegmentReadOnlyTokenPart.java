package code.expressionlanguage.adv;

import code.expressionlanguage.analyze.files.AbsSegmentColorPart;
import code.util.CustList;

public final class SegmentReadOnlyTokenPart extends AbsSegmentColorPart {
    public SegmentReadOnlyTokenPart(int _begin, int _end) {
        super(_begin, _end);
    }
    public static boolean matches(CustList<SegmentReadOnlyTokenPart> _ls, int _b, int _e) {
        for (SegmentReadOnlyTokenPart f: _ls) {
            if (f.getBegin() == _b && f.getEnd() == _e) {
                return true;
            }
        }
        return false;
    }
}
