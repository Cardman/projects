package code.maths.geo;

import code.util.CustList;

public final class SiteInfo {

    public static final int QUAD_ONE = 1;

    public static final int QUAD_TWO = 2;

    public static final int QUAD_THREE = 3;

    public static final int QUAD_FOUR = 4;

    private int number;

    private long scal;

    private long squareLength;

    public static int compare(SiteInfo _one, SiteInfo _two) {
        if (_one.number < _two.number) {
            return CustList.NO_SWAP_SORT;
        }
        if (_one.number > _two.number) {
            return CustList.SWAP_SORT;
        }
        long firstMember_ = _one.scal * _one.scal * _two.squareLength;
        long secondMember_ = _two.scal * _two.scal * _one.squareLength;
        if (_one.number == QUAD_ONE || _one.number == QUAD_THREE) {
            if (firstMember_ > secondMember_) {
                return CustList.NO_SWAP_SORT;
            }
            if (firstMember_ < secondMember_) {
                return CustList.SWAP_SORT;
            }
        } else {
            if (firstMember_ < secondMember_) {
                return CustList.NO_SWAP_SORT;
            }
            if (firstMember_ > secondMember_) {
                return CustList.SWAP_SORT;
            }
        }
        return CustList.EQ_CMP;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int _number) {
        number = _number;
    }

    public long getScal() {
        return scal;
    }

    public void setScal(long _scal) {
        scal = _scal;
    }

    public long getSquareLength() {
        return squareLength;
    }

    public void setSquareLength(long _squareLength) {
        squareLength = _squareLength;
    }
}
