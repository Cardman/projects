package code.maths.litteraladv;

import code.maths.IdBezoutNb;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class MaNumParsers {
    private MaNumParsers() {
    }
    public static String toStrNb(MaStruct _this,MaError _error) {
        if (_this == null) {
            return _error.display();
        }
        return _this.displayRsult();
    }

    public static MaStruct tryGet(MethodMaOperation _parent, int _index) {
        CustList<MaOperationNode> childrenNodes_ = _parent.getChildren();
        return tryGet(childrenNodes_, _index);
    }

    public static MaStruct tryGet(CustList<MaOperationNode> _childrenNodes, int _index) {
        if (!_childrenNodes.isValidIndex(_index)) {
            return null;
        }
        return _childrenNodes.get(_index).getStruct();
    }

    public static boolean eqNb(MaStruct _this, MaStruct _other, String _oper) {
        if (StringUtil.quickEq("=",_oper)) {
            return eqNb(_this, _other);
        }
        return !eqNb(_this, _other);
    }
    public static boolean eqNb(MaStruct _this, MaStruct _other) {
        if (_this == null) {
            return _other == null;
        }
        if (_other == null) {
            return false;
        }
        return _this.sameReference(_other);
    }
    public static boolean cmpNb(MaRateStruct _this, MaRateStruct _other, String _oper) {
        if (StringUtil.quickEq("<",_oper)) {
            return Rate.strLower(_this.getRate(),_other.getRate());
        }
        if (StringUtil.quickEq("<=",_oper)) {
            return Rate.lowerEq(_this.getRate(),_other.getRate());
        }
        if (StringUtil.quickEq(">",_oper)) {
            return Rate.strGreater(_this.getRate(),_other.getRate());
        }
        return Rate.greaterEq(_this.getRate(),_other.getRate());
    }
}
