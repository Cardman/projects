package cards.consts.beans;

import cards.consts.LineDeal;
import code.bean.nat.NatArrayStruct;
import code.bean.nat.SpecialNatClass;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import org.junit.Test;

public final class LineDealStructTest extends EquallableCardConstBeanUtil {
    @Test
    public void getNumber() {
        assertEq(3,callLineDealNumber(lineDeal(3, 5, 9)));
    }
    @Test
    public void getScores() {
        Struct arr_ = callLineDealScores(lineDeal(3, 5, 9));
        assertSizeEq(2,arr_);
        assertEq(5,arr_,0);
        assertEq(9,arr_,1);
    }
    @Test
    public void getLineDealArray() {
        NatArrayStruct res_ = LineDealStruct.getLineDealArray(two(base(4, 51, 49,7), base(3, 55, 91,6,42)));
        assertSizeEq(2,res_);
        assertSizeEq(3,res_,0);
        assertNumberEq(4,res_,0);
        assertEq(51,res_,0,0);
        assertEq(49,res_,0,1);
        assertEq(7,res_,0,2);
        assertSizeEq(4,res_,1);
        assertNumberEq(3,res_,1);
        assertEq(55,res_,1,0);
        assertEq(91,res_,1,1);
        assertEq(6,res_,1,2);
        assertEq(42,res_,1,3);
    }
    @Test
    public void buildLineDeal() {
        StringMap<SpecialNatClass> types_ = new StringMap<SpecialNatClass>();
        LineDealStruct.buildLineDeal(types_);
        assertEq(1, types_.size());
    }
    @Test
    public void scores() {
        CustList<LineDeal> l_ = new CustList<LineDeal>();
        l_.add(new LineDeal());
        assertEq(1, LineDealStruct.scores(l_).size());
    }
}
