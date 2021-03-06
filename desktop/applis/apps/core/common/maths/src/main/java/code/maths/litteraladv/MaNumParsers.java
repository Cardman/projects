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
        if (_this instanceof MaBoolStruct) {
            if (MaBoolStruct.isTrue(_this)) {
                return "1";
            }
            return "0";
        }
        if (_this instanceof MaBezoutNbStruct) {
            IdBezoutNb idBezout_ = ((MaBezoutNbStruct) _this).getIdBezout();
            return "("+idBezout_.getFirst().toNumberString()+","+idBezout_.getSecond().toNumberString()+","
                    +idBezout_.getPgcd().toNumberString()+","+idBezout_.getPpcm().toNumberString()+")";
        }
        if (_this instanceof MaDividersNbStruct) {
            CustList<LgInt> divs_ = ((MaDividersNbStruct)_this).getDividers();
            StringList list_ = new StringList(new CollCapacity(divs_.size()));
            for (LgInt d: divs_) {
                list_.add(d.toNumberString());
            }
            return "("+StringUtil.join(list_,",")+")";
        }
        return toRate(_this).getRate().toNumberString();
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
    public static MaRateStruct toRate(MaStruct _inst) {
        if (!(_inst instanceof MaRateStruct)) {
            return new MaRateStruct(Rate.zero());
        }
        return (MaRateStruct) _inst;
    }
}
