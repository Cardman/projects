package code.maths.geo;

import code.util.core.SortConstants;

public final class SiteInfo {

    public static final int QUAD_ONE = 1;

    public static final int QUAD_TWO = 2;

    public static final int QUAD_THREE = 3;

    public static final int QUAD_FOUR = 4;

    private int number;

    private long scal;

    private long squareLength;

    public static int compare(SiteInfo _one, SiteInfo _two) {
        if (_one.getNumber() < _two.getNumber()) {
            return SortConstants.NO_SWAP_SORT;
        }
        if (_one.getNumber() > _two.getNumber()) {
            return SortConstants.SWAP_SORT;
        }
        long firstMember_ = _one.getScal() * _one.getScal() * _two.getSquareLength();
        long secondMember_ = _two.getScal() * _two.getScal() * _one.getSquareLength();
        if (_one.getNumber() == QUAD_ONE || _one.getNumber() == QUAD_THREE) {
            if (firstMember_ > secondMember_) {
                return SortConstants.NO_SWAP_SORT;
            }
            if (firstMember_ < secondMember_) {
                return SortConstants.SWAP_SORT;
            }
        } else {
            if (firstMember_ < secondMember_) {
                return SortConstants.NO_SWAP_SORT;
            }
            if (firstMember_ > secondMember_) {
                return SortConstants.SWAP_SORT;
            }
        }
        return SortConstants.EQ_CMP;
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
