package code.maths.geo;

import code.maths.Rate;
import code.util.core.SortConstants;

public final class SiteInfo {

    public static final int QUAD_ONE = 1;

    public static final int QUAD_TWO = 2;

    public static final int QUAD_THREE = 3;

    public static final int QUAD_FOUR = 4;

    private int number;

    private Rate scal;

    private Rate squareLength;

    public static int compare(SiteInfo _one, SiteInfo _two) {
        if (_one.getNumber() < _two.getNumber()) {
            return SortConstants.NO_SWAP_SORT;
        }
        if (_one.getNumber() > _two.getNumber()) {
            return SortConstants.SWAP_SORT;
        }
        Rate firstMember_ = Rate.multiply(Rate.multiply(_one.getScal(), _one.getScal()),_two.getSquareLength());
        Rate secondMember_ = Rate.multiply(Rate.multiply(_two.getScal(), _two.getScal()),_one.getSquareLength());
        if (_one.getNumber() == QUAD_ONE || _one.getNumber() == QUAD_THREE) {
            if (Rate.strGreater(firstMember_, secondMember_)) {
                return SortConstants.NO_SWAP_SORT;
            }
            if (Rate.strLower(firstMember_, secondMember_)) {
                return SortConstants.SWAP_SORT;
            }
        } else {
            if (Rate.strLower(firstMember_, secondMember_)) {
                return SortConstants.NO_SWAP_SORT;
            }
            if (Rate.strGreater(firstMember_, secondMember_)) {
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

    public Rate getScal() {
        return scal;
    }

    public void setScal(Rate _scal) {
        scal = _scal;
    }

    public Rate getSquareLength() {
        return squareLength;
    }

    public void setSquareLength(Rate _squareLength) {
        squareLength = _squareLength;
    }
}
