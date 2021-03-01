package code.maths;

import code.util.EqList;
import code.util.*;
import code.util.CustList;
import code.util.TreeMap;
import org.junit.Test;


public class LgIntTest extends EquallableMathUtil {
    @Test
    public void base1(){
        assertEq(1000000000, LgInt.base());
    }
    @Test
    public void logBase1(){
        assertEq(9, LgInt.logBase());
    }
    @Test
    public void new_LgInt_1(){
        LgInt entier_ = LgInt.zero();
        assertEq(1, entier_.getGrDigits().size());
        assertEq(0, entier_.getGrDigits().first());
        assertEq(LgInt.SIGNE_POSITIF,entier_.getSignum());
        assertTrue(entier_.isZero());
    }
    @Test
    public void new_LgInt_long_1Test(){
        LgInt int_ = new LgInt(1L);
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(1L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_long_2Test(){
        LgInt int_ = new LgInt(0L);
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(0L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_long_3Test(){
        LgInt int_ = new LgInt(-1L);
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(1L), resDigits_);
        assertEq(!LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_long_4Test(){
        LgInt int_ = new LgInt(1234567890L);
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(1L,234567890L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_long_5Test(){
        LgInt int_ = new LgInt(-1234567890L);
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(1L,234567890L), resDigits_);
        assertEq(!LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_long_6Test(){
        LgInt int_ = new LgInt(Long.MAX_VALUE);
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(9L,223372036L,854775807L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_long_7Test(){
        LgInt int_ = new LgInt(Long.MIN_VALUE);
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(9L,223372036L,854775808L), resDigits_);
        assertEq(!LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_1Test(){
        LgInt int_ = new LgInt("1");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(1L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_2Test(){
        LgInt int_ = new LgInt("0");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(0L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_3Test(){
        LgInt int_ = new LgInt("-1");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(1L), resDigits_);
        assertEq(!LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_4Test(){
        LgInt int_ = new LgInt("1234567890");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(1L,234567890L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_5Test(){
        LgInt int_ = new LgInt("-1234567890");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(1L,234567890L), resDigits_);
        assertEq(!LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_6Test(){
        LgInt int_ = new LgInt("1000000000");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(1L,0L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_7Test(){
        LgInt int_ = new LgInt("-1000000000");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(1L,0L), resDigits_);
        assertEq(!LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_8Test(){
        LgInt int_ = new LgInt("12345678901234567890");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(12L,345678901L,234567890L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_9Test(){
        LgInt int_ = new LgInt("-12345678901234567890");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(12L,345678901L,234567890L), resDigits_);
        assertEq(!LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_10Test(){
        LgInt int_ = new LgInt("12000000000234567890");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(12L,0L,234567890L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_11Test(){
        LgInt int_ = new LgInt("-12000000000234567890");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(12L,0L,234567890L), resDigits_);
        assertEq(!LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_12Test(){
        LgInt int_ = new LgInt("12000000001234567890");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(12L,1L,234567890L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_13Test(){
        LgInt int_ = new LgInt("-12000000001234567890");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(12L,1L,234567890L), resDigits_);
        assertEq(!LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_String_14Test(){
        LgInt int_ = new LgInt("");
        Longs resDigits_ = int_.getGrDigits();
        assertEqDigits(new Longs(0L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, int_.getSignum());
    }
    @Test
    public void new_LgInt_copy_Test() {
        LgInt i_ = new LgInt(2);
        LgInt j_ = new LgInt(i_);
        Longs resDigits_ = j_.getGrDigits();
        assertEqDigits(new Longs(2L), resDigits_);
        assertEq(LgInt.SIGNE_POSITIF, j_.getSignum());
        assertNotSame(i_.getGrDigits(),resDigits_);
    }

    private static void assertEqDigits(Longs _expected, Longs _result) {
        int expectedLen_ = _expected.size();
        assertEq(expectedLen_,_result.size());
        for (int i = 0; i < expectedLen_; i++) {
            assertEq(_expected.get(i),_result.get(i));
        }
    }
    @Test
    public void toString1Test(){
        //_input.setUnmodified();
        assertEq("1", new LgInt(1).toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString2Test(){
        //_input.setUnmodified();
        assertEq("-1", new LgInt(-1).toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString3Test(){
        //_input.setUnmodified();
        assertEq("0", new LgInt("-0").toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString4Test(){
        //_input.setUnmodified();
        assertEq("0", LgInt.zero().toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString5Test(){
        //_input.setUnmodified();
        assertEq("1234567890", new LgInt(1234567890).toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString6Test(){
        //_input.setUnmodified();
        assertEq("1000000000", new LgInt(1000000000).toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString7Test(){
        //_input.setUnmodified();
        assertEq("-1000000000", new LgInt(-1000000000).toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString8Test(){
        //_input.setUnmodified();
        assertEq("-1234567890", new LgInt(-1234567890).toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString9Test(){
        //_input.setUnmodified();
        assertEq("12345678901234567890", new LgInt("12345678901234567890").toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString10Test(){
        //_input.setUnmodified();
        assertEq("-12345678901234567890", new LgInt("-12345678901234567890").toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString11Test(){
        //_input.setUnmodified();
        assertEq("12000000000234567890", new LgInt("12000000000234567890").toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString12Test(){
        //_input.setUnmodified();
        assertEq("-12000000000234567890", new LgInt("-12000000000234567890").toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString13Test(){
        //_input.setUnmodified();
        assertEq("12000000001234567890", new LgInt("12000000001234567890").toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void toString14Test(){
        //_input.setUnmodified();
        assertEq("-12000000001234567890", new LgInt("-12000000001234567890").toNumberString());
        //assertTrue(!_input.isModified());
    }
    @Test
    public void eq1Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("1");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(true, intOne_.eq(intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void eq2Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("-1");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(false, intOne_.eq(intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void eq3Test(){
        LgInt intOne_ = new LgInt("10");
        LgInt intTwo_ = new LgInt("1");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(false, intOne_.eq(intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void eq4Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("10");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(false, intOne_.eq(intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void eq5Test(){
        LgInt intOne_ = new LgInt("1000000000");
        LgInt intTwo_ = new LgInt("1");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(false, intOne_.eq(intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void eq6Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("1000000000");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(false, intOne_.eq(intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void eq7Test(){
        LgInt intOne_ = new LgInt("1000000000");
        LgInt intTwo_ = new LgInt("1000000000");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(true, intOne_.eq(intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void eq8Test(){
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(false, LgInt.eq(new CustList<LgInt>(LgInt.one()),new CustList<LgInt>()));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void different1Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("1");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(false, LgInt.different(intOne_, intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void different2Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("-1");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(true, LgInt.different(intOne_, intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void different3Test(){
        LgInt intOne_ = new LgInt("10");
        LgInt intTwo_ = new LgInt("1");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(true, LgInt.different(intOne_, intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void different4Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("10");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(true, LgInt.different(intOne_, intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void different5Test(){
        LgInt intOne_ = new LgInt("1000000000");
        LgInt intTwo_ = new LgInt("1");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(true, LgInt.different(intOne_, intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void different6Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("1000000000");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(true, LgInt.different(intOne_, intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void different7Test(){
        LgInt intOne_ = new LgInt("1000000000");
        LgInt intTwo_ = new LgInt("1000000000");
        //intOne_.setUnmodified();
        //intTwo_.setUnmodified();
        assertEq(false, LgInt.different(intOne_, intTwo_));
        //assertTrue(!intOne_.isModified());
        //assertTrue(!intTwo_.isModified());
    }
    @Test
    public void ll1Test(){
        LgInt int_ = new LgInt("1");
        assertEq(1L, int_.ll());
        assertEq(new LgInt("1"), int_);
    }
    @Test
    public void ll2Test(){
        LgInt int_ = new LgInt("-1");
        assertEq(-1L, int_.ll());
        assertEq(new LgInt("-1"), int_);
    }
    @Test
    public void ll3Test(){
        LgInt int_ = new LgInt("0");
        assertEq(0L, int_.ll());
        assertEq(new LgInt("0"), int_);
    }
    @Test
    public void ll4Test(){
        LgInt int_ = new LgInt("1234567890");
        assertEq(234567890L, int_.ll());
        assertEq(new LgInt("1234567890"), int_);
    }
    @Test
    public void ll5Test(){
        LgInt int_ = new LgInt("-1234567890");
        assertEq(-234567890L, int_.ll());
        assertEq(new LgInt("-1234567890"), int_);
    }
    @Test
    public void ll6Test(){
        LgInt int_ = new LgInt("12345678901234567890");
        assertEq(234567890L, int_.ll());
        assertEq(new LgInt("12345678901234567890"), int_);
    }
    @Test
    public void ll7Test(){
        LgInt int_ = new LgInt("-12345678901234567890");
        assertEq(-234567890L, int_.ll());
        assertEq(new LgInt("-12345678901234567890"), int_);
    }
    @Test
    public void lowerEq1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("3");
        assertEq(true, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void lowerEq2Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("3");
        assertEq(true, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void lowerEq3Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("0");
        assertEq(true, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void lowerEq4Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(true, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void lowerEq5Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(true, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void lowerEq6Test(){
        LgInt intOne_ = new LgInt("-1234567890");
        LgInt intTwo_ = new LgInt("3");
        assertEq(true, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("-1234567890"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void lowerEq7Test(){
        LgInt intOne_ = new LgInt("-1234567890");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(true, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("-1234567890"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void lowerEq8Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("0");
        assertEq(false, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void lowerEq9Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(false, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void lowerEq10Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("3");
        assertEq(false, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void lowerEq11Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(false, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void lowerEq12Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("-1234567890");
        assertEq(false, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("-1234567890"), intTwo_);
    }
    @Test
    public void lowerEq13Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("-1234567890");
        assertEq(false, LgInt.lowerEq(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("-1234567890"), intTwo_);
    }
    @Test
    public void greaterEq1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("3");
        assertEq(false, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void greaterEq2Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("3");
        assertEq(true, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void greaterEq3Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("0");
        assertEq(false, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void greaterEq4Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(false, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void greaterEq5Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(false, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void greaterEq6Test(){
        LgInt intOne_ = new LgInt("-1234567890");
        LgInt intTwo_ = new LgInt("3");
        assertEq(false, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("-1234567890"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void greaterEq7Test(){
        LgInt intOne_ = new LgInt("-1234567890");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(false, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("-1234567890"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void greaterEq8Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("0");
        assertEq(true, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void greaterEq9Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(true, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void greaterEq10Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("3");
        assertEq(true, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void greaterEq11Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(true, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void greaterEq12Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("-1234567890");
        assertEq(true, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("-1234567890"), intTwo_);
    }
    @Test
    public void greaterEq13Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("-1234567890");
        assertEq(true, LgInt.greaterEq(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("-1234567890"), intTwo_);
    }
    @Test
    public void strLower1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("3");
        assertEq(true, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void strLower2Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("3");
        assertEq(false, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void strLower3Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("0");
        assertEq(true, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void strLower4Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(true, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void strLower5Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(true, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void strLower6Test(){
        LgInt intOne_ = new LgInt("-1234567890");
        LgInt intTwo_ = new LgInt("3");
        assertEq(true, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("-1234567890"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void strLower7Test(){
        LgInt intOne_ = new LgInt("-1234567890");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(true, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("-1234567890"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void strLower8Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("0");
        assertEq(false, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void strLower9Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(false, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void strLower10Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("3");
        assertEq(false, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void strLower11Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(false, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void strLower12Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("-1234567890");
        assertEq(false, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("-1234567890"), intTwo_);
    }
    @Test
    public void strLower13Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("-1234567890");
        assertEq(false, LgInt.strLower(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("-1234567890"), intTwo_);
    }
    @Test
    public void strGreater1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("3");
        assertEq(false, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void strGreater2Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("3");
        assertEq(false, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void strGreater3Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("0");
        assertEq(false, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void strGreater4Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(false, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void strGreater5Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(false, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void strGreater6Test(){
        LgInt intOne_ = new LgInt("-1234567890");
        LgInt intTwo_ = new LgInt("3");
        assertEq(false, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("-1234567890"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void strGreater7Test(){
        LgInt intOne_ = new LgInt("-1234567890");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(false, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("-1234567890"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void strGreater8Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("0");
        assertEq(true, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void strGreater9Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(true, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void strGreater10Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("3");
        assertEq(true, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void strGreater11Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("-3");
        assertEq(true, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("-3"), intTwo_);
    }
    @Test
    public void strGreater12Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("-1234567890");
        assertEq(true, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("-1234567890"), intTwo_);
    }
    @Test
    public void strGreater13Test(){
        LgInt intOne_ = new LgInt("-3");
        LgInt intTwo_ = new LgInt("-1234567890");
        assertEq(true, LgInt.strGreater(intOne_, intTwo_));
        assertEq(new LgInt("-3"), intOne_);
        assertEq(new LgInt("-1234567890"), intTwo_);
    }
    @Test
    public void plus1Test(){
        LgInt one_ = new LgInt("0");
        LgInt two_ = new LgInt("20");
        assertEq(new LgInt("20"), LgInt.plus(one_, two_));
        assertEq(new LgInt("0"), one_);
        assertEq(new LgInt("20"), two_);
    }
    @Test
    public void plus2Test(){
        LgInt one_ = new LgInt("1");
        LgInt two_ = new LgInt("20");
        assertEq(new LgInt("21"), LgInt.plus(one_, two_));
        assertEq(new LgInt("1"), one_);
        assertEq(new LgInt("20"), two_);
    }
    @Test
    public void plus3Test(){
        LgInt one_ = new LgInt("-1");
        LgInt two_ = new LgInt("20");
        assertEq(new LgInt("19"), LgInt.plus(one_, two_));
        assertEq(new LgInt("-1"), one_);
        assertEq(new LgInt("20"), two_);
    }
    @Test
    public void plus4Test(){
        LgInt one_ = new LgInt("1");
        LgInt two_ = new LgInt("-20");
        assertEq(new LgInt("-19"), LgInt.plus(one_, two_));
        assertEq(new LgInt("1"), one_);
        assertEq(new LgInt("-20"), two_);
    }
    @Test
    public void plus5Test(){
        LgInt one_ = new LgInt("-1");
        LgInt two_ = new LgInt("-20");
        assertEq(new LgInt("-21"), LgInt.plus(one_, two_));
        assertEq(new LgInt("-1"), one_);
        assertEq(new LgInt("-20"), two_);
    }
    @Test
    public void plus6Test(){
        LgInt one_ = new LgInt("999999999999999999");
        LgInt two_ = new LgInt("1");
        assertEq(new LgInt("1000000000000000000"), LgInt.plus(one_, two_));
        assertEq(new LgInt("999999999999999999"), one_);
        assertEq(new LgInt("1"), two_);
    }
    @Test
    public void plus7Test(){
        LgInt one_ = new LgInt("1");
        LgInt two_ = new LgInt("999999999999999999");
        assertEq(new LgInt("1000000000000000000"), LgInt.plus(one_, two_));
        assertEq(new LgInt("1"), one_);
        assertEq(new LgInt("999999999999999999"), two_);
    }
    @Test
    public void plus8Test(){
        LgInt one_ = new LgInt("1234567890");
        LgInt two_ = new LgInt("11234567890");
        assertEq(new LgInt("12469135780"), LgInt.plus(one_, two_));
        assertEq(new LgInt("1234567890"), one_);
        assertEq(new LgInt("11234567890"), two_);
    }
    @Test
    public void plus9Test(){
        LgInt one_ = new LgInt("11234567890");
        LgInt two_ = new LgInt("1234567890");
        assertEq(new LgInt("12469135780"), LgInt.plus(one_, two_));
        assertEq(new LgInt("11234567890"), one_);
        assertEq(new LgInt("1234567890"), two_);
    }
    @Test
    public void plus10Test(){
        LgInt one_ = new LgInt("12345678901234567890");
        LgInt two_ = new LgInt("11234567890");
        assertEq(new LgInt("12345678912469135780"), LgInt.plus(one_, two_));
        assertEq(new LgInt("12345678901234567890"), one_);
        assertEq(new LgInt("11234567890"), two_);
    }
    @Test
    public void plus11Test(){
        LgInt one_ = new LgInt("11234567890");
        LgInt two_ = new LgInt("12345678901234567890");
        assertEq(new LgInt("12345678912469135780"), LgInt.plus(one_, two_));
        assertEq(new LgInt("11234567890"), one_);
        assertEq(new LgInt("12345678901234567890"), two_);
    }
    @Test
    public void plus12Test(){
        LgInt one_ = new LgInt("12345678901234567890");
        LgInt two_ = new LgInt("11876543210");
        assertEq(new LgInt("12345678913111111100"), LgInt.plus(one_, two_));
        assertEq(new LgInt("12345678901234567890"), one_);
        assertEq(new LgInt("11876543210"), two_);
    }
    @Test
    public void plus13Test(){
        LgInt one_ = new LgInt("11876543210");
        LgInt two_ = new LgInt("12345678901234567890");
        assertEq(new LgInt("12345678913111111100"), LgInt.plus(one_, two_));
        assertEq(new LgInt("11876543210"), one_);
        assertEq(new LgInt("12345678901234567890"), two_);
    }
    @Test
    public void plus14Test(){
        LgInt one_ = new LgInt("4444444444444444444");
        LgInt two_ = new LgInt("-555555555555555555");
        assertEq(new LgInt("3888888888888888889"), LgInt.plus(one_, two_));
        assertEq(new LgInt("4444444444444444444"), one_);
        assertEq(new LgInt("-555555555555555555"), two_);
    }
    @Test
    public void plus15Test(){
        LgInt one_ = new LgInt("4444444444444444444");
        LgInt two_ = new LgInt("-333333333555555555");
        assertEq(new LgInt("4111111110888888889"), LgInt.plus(one_, two_));
        assertEq(new LgInt("4444444444444444444"), one_);
        assertEq(new LgInt("-333333333555555555"), two_);
    }
    @Test
    public void plus16Test(){
        LgInt one_ = new LgInt("4444444444444444444");
        LgInt two_ = new LgInt("-555555555");
        assertEq(new LgInt("4444444443888888889"), LgInt.plus(one_, two_));
        assertEq(new LgInt("4444444444444444444"), one_);
        assertEq(new LgInt("-555555555"), two_);
    }
    @Test
    public void plus17Test(){
        LgInt one_ = new LgInt("4444444444000000000");
        LgInt two_ = new LgInt("-1");
        assertEq(new LgInt("4444444443999999999"), LgInt.plus(one_, two_));
        assertEq(new LgInt("4444444444000000000"), one_);
        assertEq(new LgInt("-1"), two_);
    }
    @Test
    public void plus18Test(){
        LgInt one_ = new LgInt("4000000000000000000");
        LgInt two_ = new LgInt("-1");
        assertEq(new LgInt("3999999999999999999"), LgInt.plus(one_, two_));
        assertEq(new LgInt("4000000000000000000"), one_);
        assertEq(new LgInt("-1"), two_);
    }
    @Test
    public void plus19Test(){
        LgInt one_ = new LgInt("999999999");
        LgInt two_ = new LgInt("1");
        assertEq(new LgInt("1000000000"), LgInt.plus(one_, two_));
        assertEq(new LgInt("999999999"), one_);
        assertEq(new LgInt("1"), two_);
    }
    @Test
    public void plus20Test(){
        LgInt one_ = new LgInt("1");
        LgInt two_ = new LgInt("999999999");
        assertEq(new LgInt("1000000000"), LgInt.plus(one_, two_));
        assertEq(new LgInt("1"), one_);
        assertEq(new LgInt("999999999"), two_);
    }

    @Test
    public void removeNb1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("-20");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("20"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void removeNb2Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("-20");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("21"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void removeNb3Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("-20");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("19"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void removeNb4Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("20");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("-19"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void removeNb5Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("20");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("-21"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void removeNb6Test(){
        LgInt intOne_ = new LgInt("999999999999999999");
        LgInt intTwo_ = new LgInt("-1");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("1000000000000000000"), intOne_);
        assertEq(new LgInt("-1"), intTwo_);
    }
    @Test
    public void removeNb7Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("-999999999999999999");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("1000000000000000000"), intOne_);
        assertEq(new LgInt("-999999999999999999"), intTwo_);
    }
    @Test
    public void removeNb8Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("-11234567890");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("12469135780"), intOne_);
        assertEq(new LgInt("-11234567890"), intTwo_);
    }
    @Test
    public void removeNb9Test(){
        LgInt intOne_ = new LgInt("11234567890");
        LgInt intTwo_ = new LgInt("-1234567890");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("12469135780"), intOne_);
        assertEq(new LgInt("-1234567890"), intTwo_);
    }
    @Test
    public void removeNb10Test(){
        LgInt intOne_ = new LgInt("12345678901234567890");
        LgInt intTwo_ = new LgInt("-11234567890");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("12345678912469135780"), intOne_);
        assertEq(new LgInt("-11234567890"), intTwo_);
    }
    @Test
    public void removeNb11Test(){
        LgInt intOne_ = new LgInt("11234567890");
        LgInt intTwo_ = new LgInt("-12345678901234567890");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("12345678912469135780"), intOne_);
        assertEq(new LgInt("-12345678901234567890"), intTwo_);
    }
    @Test
    public void removeNb12Test(){
        LgInt intOne_ = new LgInt("12345678901234567890");
        LgInt intTwo_ = new LgInt("-11876543210");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("12345678913111111100"), intOne_);
        assertEq(new LgInt("-11876543210"), intTwo_);
    }
    @Test
    public void removeNb13Test(){
        LgInt intOne_ = new LgInt("11876543210");
        LgInt intTwo_ = new LgInt("-12345678901234567890");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("12345678913111111100"), intOne_);
        assertEq(new LgInt("-12345678901234567890"), intTwo_);
    }
    @Test
    public void removeNb14Test(){
        LgInt intOne_ = new LgInt("4444444444444444444");
        LgInt intTwo_ = new LgInt("555555555555555555");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("3888888888888888889"), intOne_);
        assertEq(new LgInt("555555555555555555"), intTwo_);
    }
    @Test
    public void removeNb15Test(){
        LgInt intOne_ = new LgInt("4444444444444444444");
        LgInt intTwo_ = new LgInt("333333333555555555");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("4111111110888888889"), intOne_);
        assertEq(new LgInt("333333333555555555"), intTwo_);
    }
    @Test
    public void removeNb16Test(){
        LgInt intOne_ = new LgInt("4444444444444444444");
        LgInt intTwo_ = new LgInt("555555555");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("4444444443888888889"), intOne_);
        assertEq(new LgInt("555555555"), intTwo_);
    }
    @Test
    public void removeNb17Test(){
        LgInt intOne_ = new LgInt("4444444444000000000");
        LgInt intTwo_ = new LgInt("1");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("4444444443999999999"), intOne_);
        assertEq(new LgInt("1"), intTwo_);
    }
    @Test
    public void removeNb18Test(){
        LgInt intOne_ = new LgInt("4000000000000000000");
        LgInt intTwo_ = new LgInt("1");
        intOne_.removeNb(intTwo_);
        assertEq(new LgInt("3999999999999999999"), intOne_);
        assertEq(new LgInt("1"), intTwo_);
    }
    @Test
    public void fact1Test(){
        LgInt int_ = new LgInt("0");
        assertEq(new LgInt("1"), int_.fact());
        assertEq(new LgInt("0"), int_);
    }
    @Test
    public void fact2Test(){
        LgInt int_ = new LgInt("1");
        assertEq(new LgInt("1"), int_.fact());
        assertEq(new LgInt("1"), int_);
    }
    @Test
    public void fact3Test(){
        LgInt int_ = new LgInt("2");
        assertEq(new LgInt("2"), int_.fact());
        assertEq(new LgInt("2"), int_);
    }
    @Test
    public void fact4Test(){
        LgInt int_ = new LgInt("3");
        assertEq(new LgInt("6"), int_.fact());
        assertEq(new LgInt("3"), int_);
    }
    @Test
    public void fact5Test(){
        LgInt int_ = new LgInt("4");
        assertEq(new LgInt("24"), int_.fact());
        assertEq(new LgInt("4"), int_);
    }
    @Test
    public void opposNb1Test(){
        LgInt int_ = new LgInt("0");
        assertEq(new LgInt("0"), int_.opposNb());
        assertEq(new LgInt("0"), int_);
    }
    @Test
    public void opposNb2Test(){
        LgInt int_ = new LgInt("1");
        assertEq(new LgInt("-1"), int_.opposNb());
        assertEq(new LgInt("1"), int_);
    }
    @Test
    public void opposNb3Test(){
        LgInt int_ = new LgInt("-1");
        assertEq(new LgInt("1"), int_.opposNb());
        assertEq(new LgInt("-1"), int_);
    }
    @Test
    public void opposNb4Test(){
        LgInt int_ = new LgInt("1234567890");
        assertEq(new LgInt("-1234567890"), int_.opposNb());
        assertEq(new LgInt("1234567890"), int_);
    }
    @Test
    public void opposNb5Test(){
        LgInt int_ = new LgInt("-1234567890");
        assertEq(new LgInt("1234567890"), int_.opposNb());
        assertEq(new LgInt("-1234567890"), int_);
    }
    @Test
    public void opposNb6Test(){
        LgInt int_ = new LgInt("12345678901234567890");
        assertEq(new LgInt("-12345678901234567890"), int_.opposNb());
        assertEq(new LgInt("12345678901234567890"), int_);
    }
    @Test
    public void opposNb7Test(){
        LgInt int_ = new LgInt("-12345678901234567890");
        assertEq(new LgInt("12345678901234567890"), int_.opposNb());
        assertEq(new LgInt("-12345678901234567890"), int_);
    }
    @Test
    public void signum1Test(){
        LgInt int_ = new LgInt("0");
        assertEq(new LgInt("0"), int_.signum());
        assertEq(new LgInt("0"), int_);
    }
    @Test
    public void signum2Test(){
        LgInt int_ = new LgInt("1");
        assertEq(new LgInt("1"), int_.signum());
        assertEq(new LgInt("1"), int_);
    }
    @Test
    public void signum3Test(){
        LgInt int_ = new LgInt("-1");
        assertEq(new LgInt("-1"), int_.signum());
        assertEq(new LgInt("-1"), int_);
    }
    @Test
    public void absNb1Test(){
        LgInt int_ = new LgInt("0");
        assertEq(new LgInt("0"), int_.absNb());
        assertEq(new LgInt("0"), int_);
    }
    @Test
    public void absNb2Test(){
        LgInt int_ = new LgInt("1");
        assertEq(new LgInt("1"), int_.absNb());
        assertEq(new LgInt("1"), int_);
    }
    @Test
    public void absNb3Test(){
        LgInt int_ = new LgInt("-1");
        assertEq(new LgInt("1"), int_.absNb());
        assertEq(new LgInt("-1"), int_);
    }
    @Test
    public void absNb4Test(){
        LgInt int_ = new LgInt("1234567890");
        assertEq(new LgInt("1234567890"), int_.absNb());
        assertEq(new LgInt("1234567890"), int_);
    }
    @Test
    public void absNb5Test(){
        LgInt int_ = new LgInt("-1234567890");
        assertEq(new LgInt("1234567890"), int_.absNb());
        assertEq(new LgInt("-1234567890"), int_);
    }
    @Test
    public void absNb6Test(){
        LgInt int_ = new LgInt("12345678901234567890");
        assertEq(new LgInt("12345678901234567890"), int_.absNb());
        assertEq(new LgInt("12345678901234567890"), int_);
    }
    @Test
    public void absNb7Test(){
        LgInt int_ = new LgInt("-12345678901234567890");
        assertEq(new LgInt("12345678901234567890"), int_.absNb());
        assertEq(new LgInt("-12345678901234567890"), int_);
    }
    @Test
    public void increment1Test(){
        LgInt int_ = new LgInt("0");
        int_.increment();
        LgInt res_ = new LgInt("1");
        assertEq(res_, int_);
    }
    @Test
    public void increment2Test(){
        LgInt int_ = new LgInt("1");
        int_.increment();
        LgInt res_ = new LgInt("2");
        assertEq(res_, int_);
    }
    @Test
    public void increment3Test(){
        LgInt int_ = new LgInt("-1");
        int_.increment();
        LgInt res_ = new LgInt("0");
        assertEq(res_, int_);
    }
    @Test
    public void increment4Test(){
        LgInt int_ = new LgInt("1234567890");
        int_.increment();
        LgInt res_ = new LgInt("1234567891");
        assertEq(res_, int_);
    }
    @Test
    public void increment5Test(){
        LgInt int_ = new LgInt("-1234567890");
        int_.increment();
        LgInt res_ = new LgInt("-1234567889");
        assertEq(res_, int_);
    }
    @Test
    public void increment6Test(){
        LgInt int_ = new LgInt("12345678901234567890");
        int_.increment();
        LgInt res_ = new LgInt("12345678901234567891");
        assertEq(res_, int_);
    }
    @Test
    public void increment7Test(){
        LgInt int_ = new LgInt("-12345678901234567890");
        int_.increment();
        LgInt res_ = new LgInt("-12345678901234567889");
        assertEq(res_, int_);
    }
    @Test
    public void decrement1Test(){
        LgInt int_ = new LgInt("0");
        int_.decrement();
        LgInt res_ = new LgInt("-1");
        assertEq(res_, int_);
    }
    @Test
    public void decrement2Test(){
        LgInt int_ = new LgInt("1");
        int_.decrement();
        LgInt res_ = new LgInt("0");
        assertEq(res_, int_);
    }
    @Test
    public void decrement3Test(){
        LgInt int_ = new LgInt("-1");
        int_.decrement();
        LgInt res_ = new LgInt("-2");
        assertEq(res_, int_);
    }
    @Test
    public void decrement4Test(){
        LgInt int_ = new LgInt("1234567890");
        int_.decrement();
        LgInt res_ = new LgInt("1234567889");
        assertEq(res_, int_);
    }
    @Test
    public void decrement5Test(){
        LgInt int_ = new LgInt("-1234567890");
        int_.decrement();
        LgInt res_ = new LgInt("-1234567891");
        assertEq(res_, int_);
    }
    @Test
    public void decrement6Test(){
        LgInt int_ = new LgInt("12345678901234567890");
        int_.decrement();
        LgInt res_ = new LgInt("12345678901234567889");
        assertEq(res_, int_);
    }
    @Test
    public void decrement7Test(){
        LgInt int_ = new LgInt("-12345678901234567890");
        int_.decrement();
        LgInt res_ = new LgInt("-12345678901234567891");
        assertEq(res_, int_);
    }
    @Test
    public void minus1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("-20"), LgInt.minus(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void minus2Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("-19"), LgInt.minus(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void minus3Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("-21"), LgInt.minus(intOne_, intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void minus4Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("21"), LgInt.minus(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void minus5Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("19"), LgInt.minus(intOne_, intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void minus6Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("11234567890");
        assertEq(new LgInt("-10000000000"), LgInt.minus(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("11234567890"), intTwo_);
    }
    @Test
    public void minus7Test(){
        LgInt intOne_ = new LgInt("12345678901234567890");
        LgInt intTwo_ = new LgInt("11234567890");
        assertEq(new LgInt("12345678890000000000"), LgInt.minus(intOne_, intTwo_));
        assertEq(new LgInt("12345678901234567890"), intOne_);
        assertEq(new LgInt("11234567890"), intTwo_);
    }
    @Test
    public void multiply1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("0"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void multiply2Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("20"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void multiply3Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("-20"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void multiply4Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("-20"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void multiply5Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("20"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void multiply6Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("11234567890");
        assertEq(new LgInt("13869836775019052100"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("11234567890"), intTwo_);
    }
    @Test
    public void multiply7Test(){
        LgInt intOne_ = new LgInt("11234567890");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(new LgInt("13869836775019052100"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("11234567890"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void multiply8Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("1");
        assertEq(new LgInt("1234567890"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("1"), intTwo_);
    }
    @Test
    public void multiply9Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(new LgInt("1234567890"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void multiply10Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("11234567890000000000");
        assertEq(new LgInt("13869836775019052100000000000"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("11234567890000000000"), intTwo_);
    }
    @Test
    public void multiply11Test(){
        LgInt intOne_ = new LgInt("1000000000000000000");
        LgInt intTwo_ = new LgInt("2");
        assertEq(new LgInt("2000000000000000000"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("1000000000000000000"), intOne_);
        assertEq(new LgInt("2"), intTwo_);
    }
    @Test
    public void multiply12Test(){
        LgInt intOne_ = new LgInt("2");
        LgInt intTwo_ = new LgInt("1000000000000000000");
        assertEq(new LgInt("2000000000000000000"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("2"), intOne_);
        assertEq(new LgInt("1000000000000000000"), intTwo_);
    }
    @Test
    public void multiply13Test(){
        LgInt intOne_ = new LgInt("1000000000000000000");
        LgInt intTwo_ = new LgInt("2000000000");
        assertEq(new LgInt("2000000000000000000000000000"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("1000000000000000000"), intOne_);
        assertEq(new LgInt("2000000000"), intTwo_);
    }
    @Test
    public void multiply14Test(){
        LgInt intOne_ = new LgInt("2000000000");
        LgInt intTwo_ = new LgInt("1000000000000000000");
        assertEq(new LgInt("2000000000000000000000000000"), LgInt.multiply(intOne_, intTwo_));
        assertEq(new LgInt("2000000000"), intOne_);
        assertEq(new LgInt("1000000000000000000"), intTwo_);
    }
    @Test
    public void divide0Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("0");
        assertEq(new LgInt("0"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void divide1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("0"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void divide2Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("0"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void divide3Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("-1"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void divide4Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("0"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void divide5Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("1"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void divide6Test(){
        LgInt intOne_ = new LgInt("-40");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("2"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("-40"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void divide7Test(){
        LgInt intOne_ = new LgInt("-21");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("-2"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("-21"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void divide8Test(){
        LgInt intOne_ = new LgInt("21");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("-1"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("21"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void divide9Test(){
        LgInt intOne_ = new LgInt("-21");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("2"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("-21"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void divide10Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("11234567890");
        assertEq(new LgInt("0"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("11234567890"), intTwo_);
    }
    @Test
    public void divide11Test(){
        LgInt intOne_ = new LgInt("11234567890");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(new LgInt("9"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("11234567890"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void divide12Test(){
        LgInt intOne_ = new LgInt("12345678901234567890");
        LgInt intTwo_ = new LgInt("11234567890");
        assertEq(new LgInt("1098901090"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("12345678901234567890"), intOne_);
        assertEq(new LgInt("11234567890"), intTwo_);
    }
    @Test
    public void divide13Test(){
        LgInt intOne_ = new LgInt("11234567890");
        LgInt intTwo_ = new LgInt("12345678901234567890");
        assertEq(new LgInt("0"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("11234567890"), intOne_);
        assertEq(new LgInt("12345678901234567890"), intTwo_);
    }
    @Test
    public void divide14Test(){
        LgInt intOne_ = new LgInt("631392784410367070");
        LgInt intTwo_ = new LgInt("1120937545");
        assertEq(new LgInt("563272046"), LgInt.divide(intOne_, intTwo_));
        assertEq(new LgInt("631392784410367070"), intOne_);
        assertEq(new LgInt("1120937545"), intTwo_);
    }
    @Test
    public void remain1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("0"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void remain2Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("1"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void remain3Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("19"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void remain4Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("1"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void remain5Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("19"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void remain6Test(){
        LgInt intOne_ = new LgInt("-21");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("19"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("-21"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void remain7Test(){
        LgInt intOne_ = new LgInt("21");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("1"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("21"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void remain8Test(){
        LgInt intOne_ = new LgInt("-21");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("19"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("-21"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void remain9Test(){
        LgInt intOne_ = new LgInt("-40");
        LgInt intTwo_ = new LgInt("-20");
        assertEq(new LgInt("0"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("-40"), intOne_);
        assertEq(new LgInt("-20"), intTwo_);
    }
    @Test
    public void remain10Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("11234567890");
        assertEq(new LgInt("1234567890"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("11234567890"), intTwo_);
    }
    @Test
    public void remain11Test(){
        LgInt intOne_ = new LgInt("11234567890");
        LgInt intTwo_ = new LgInt("1234567890");
        assertEq(new LgInt("123456880"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("11234567890"), intOne_);
        assertEq(new LgInt("1234567890"), intTwo_);
    }
    @Test
    public void remain12Test(){
        LgInt intOne_ = new LgInt("12345678901234567890");
        LgInt intTwo_ = new LgInt("11234567890");
        assertEq(new LgInt("1234567790"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("12345678901234567890"), intOne_);
        assertEq(new LgInt("11234567890"), intTwo_);
    }
    @Test
    public void remain13Test(){
        LgInt intOne_ = new LgInt("11234567890");
        LgInt intTwo_ = new LgInt("12345678901234567890");
        assertEq(new LgInt("11234567890"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("11234567890"), intOne_);
        assertEq(new LgInt("12345678901234567890"), intTwo_);
    }
    @Test
    public void remain14Test(){
        LgInt intOne_ = new LgInt("631392784410367070");
        LgInt intTwo_ = new LgInt("1120937545");
        assertEq(new LgInt("0"), LgInt.remain(intOne_, intTwo_));
        assertEq(new LgInt("631392784410367070"), intOne_);
        assertEq(new LgInt("1120937545"), intTwo_);
    }
    @Test
    public void powNb1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("0");
        assertEq(new LgInt("1"), LgInt.powNb(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void powNb2Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("0"), LgInt.powNb(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void powNb3Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("1"), LgInt.powNb(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void powNb4Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("1"), LgInt.powNb(intOne_, intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void powNb5Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("19");
        assertEq(new LgInt("-1"), LgInt.powNb(intOne_, intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("19"), intTwo_);
    }
    @Test
    public void powNb6Test(){
        LgInt intOne_ = new LgInt("2");
        LgInt intTwo_ = new LgInt("3");
        assertEq(new LgInt("8"), LgInt.powNb(intOne_, intTwo_));
        assertEq(new LgInt("2"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void powNb7Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("2");
        assertEq(new LgInt("9"), LgInt.powNb(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("2"), intTwo_);
    }
    @Test
    public void powNb8Test(){
        LgInt intOne_ = new LgInt("1234567890");
        LgInt intTwo_ = new LgInt("0");
        assertEq(new LgInt("1"), LgInt.powNb(intOne_, intTwo_));
        assertEq(new LgInt("1234567890"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void pgcd1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("0");
        assertEq(new LgInt("0"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void pgcd2Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("20"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void pgcd3Test(){
        LgInt intOne_ = new LgInt("20");
        LgInt intTwo_ = new LgInt("0");
        assertEq(new LgInt("20"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("20"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void pgcd4Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("1"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void pgcd5Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("19");
        assertEq(new LgInt("1"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("19"), intTwo_);
    }
    @Test
    public void pgcd6Test(){
        LgInt intOne_ = new LgInt("-2");
        LgInt intTwo_ = new LgInt("-19");
        assertEq(new LgInt("1"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("-2"), intOne_);
        assertEq(new LgInt("-19"), intTwo_);
    }
    @Test
    public void pgcd7Test(){
        LgInt intOne_ = new LgInt("2");
        LgInt intTwo_ = new LgInt("-19");
        assertEq(new LgInt("1"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("2"), intOne_);
        assertEq(new LgInt("-19"), intTwo_);
    }
    @Test
    public void pgcd8Test(){
        LgInt intOne_ = new LgInt("2");
        LgInt intTwo_ = new LgInt("19");
        assertEq(new LgInt("1"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("2"), intOne_);
        assertEq(new LgInt("19"), intTwo_);
    }
    @Test
    public void pgcd9Test(){
        LgInt intOne_ = new LgInt("-2");
        LgInt intTwo_ = new LgInt("19");
        assertEq(new LgInt("1"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("-2"), intOne_);
        assertEq(new LgInt("19"), intTwo_);
    }
    @Test
    public void pgcd10Test(){
        LgInt intOne_ = new LgInt("2");
        LgInt intTwo_ = new LgInt("4");
        assertEq(new LgInt("2"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("2"), intOne_);
        assertEq(new LgInt("4"), intTwo_);
    }
    @Test
    public void pgcd11Test(){
        LgInt intOne_ = new LgInt("6");
        LgInt intTwo_ = new LgInt("4");
        assertEq(new LgInt("2"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("6"), intOne_);
        assertEq(new LgInt("4"), intTwo_);
    }
    @Test
    public void pgcd12Test(){
        LgInt intOne_ = new LgInt("6");
        LgInt intTwo_ = new LgInt("9");
        assertEq(new LgInt("3"), LgInt.pgcd(intOne_, intTwo_));
        assertEq(new LgInt("6"), intOne_);
        assertEq(new LgInt("9"), intTwo_);
    }
    @Test
    public void ppcm1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("0");
        assertEq(new LgInt("0"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void ppcm2Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("0"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void ppcm3Test(){
        LgInt intOne_ = new LgInt("20");
        LgInt intTwo_ = new LgInt("0");
        assertEq(new LgInt("0"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("20"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void ppcm4Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("20"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void ppcm5Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("19");
        assertEq(new LgInt("19"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("19"), intTwo_);
    }
    @Test
    public void ppcm6Test(){
        LgInt intOne_ = new LgInt("-2");
        LgInt intTwo_ = new LgInt("-19");
        assertEq(new LgInt("38"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("-2"), intOne_);
        assertEq(new LgInt("-19"), intTwo_);
    }
    @Test
    public void ppcm7Test(){
        LgInt intOne_ = new LgInt("2");
        LgInt intTwo_ = new LgInt("-19");
        assertEq(new LgInt("38"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("2"), intOne_);
        assertEq(new LgInt("-19"), intTwo_);
    }
    @Test
    public void ppcm8Test(){
        LgInt intOne_ = new LgInt("2");
        LgInt intTwo_ = new LgInt("19");
        assertEq(new LgInt("38"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("2"), intOne_);
        assertEq(new LgInt("19"), intTwo_);
    }
    @Test
    public void ppcm9Test(){
        LgInt intOne_ = new LgInt("-2");
        LgInt intTwo_ = new LgInt("19");
        assertEq(new LgInt("38"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("-2"), intOne_);
        assertEq(new LgInt("19"), intTwo_);
    }
    @Test
    public void ppcm10Test(){
        LgInt intOne_ = new LgInt("2");
        LgInt intTwo_ = new LgInt("4");
        assertEq(new LgInt("4"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("2"), intOne_);
        assertEq(new LgInt("4"), intTwo_);
    }
    @Test
    public void ppcm11Test(){
        LgInt intOne_ = new LgInt("6");
        LgInt intTwo_ = new LgInt("4");
        assertEq(new LgInt("12"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("6"), intOne_);
        assertEq(new LgInt("4"), intTwo_);
    }
    @Test
    public void ppcm12Test(){
        LgInt intOne_ = new LgInt("6");
        LgInt intTwo_ = new LgInt("9");
        assertEq(new LgInt("18"), LgInt.ppcm(intOne_, intTwo_));
        assertEq(new LgInt("6"), intOne_);
        assertEq(new LgInt("9"), intTwo_);
    }
    @Test
    public void among1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("0");
        assertEq(new LgInt("1"), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void among2Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("1"), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void among3Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("20"), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void among4Test(){
        LgInt intOne_ = new LgInt("19");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("20"), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt("19"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void among5Test(){
        LgInt intOne_ = new LgInt("2");
        LgInt intTwo_ = new LgInt("3");
        assertEq(new LgInt("3"), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt("2"), intOne_);
        assertEq(new LgInt("3"), intTwo_);
    }
    @Test
    public void among6Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("2");
        assertEq(new LgInt("0"), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("2"), intTwo_);
    }
    @Test
    public void among7Test(){
        LgInt intOne_ = new LgInt("2");
        LgInt intTwo_ = new LgInt("4");
        assertEq(new LgInt("6"), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt("2"), intOne_);
        assertEq(new LgInt("4"), intTwo_);
    }
    @Test
    public void among8Test(){
        LgInt intOne_ = new LgInt("3");
        LgInt intTwo_ = new LgInt("6");
        assertEq(new LgInt("20"), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt("3"), intOne_);
        assertEq(new LgInt("6"), intTwo_);
    }
    @Test
    public void among9Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("1000000000");
        assertEq(new LgInt("1000000000"), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("1000000000"), intTwo_);
    }
    @Test
    public void among10Test(){
        LgInt intOne_ = new LgInt("1000000000");
        LgInt intTwo_ = new LgInt("1");
        assertEq(new LgInt("0"), LgInt.among(intOne_, intTwo_));
        assertEq(new LgInt("1000000000"), intOne_);
        assertEq(new LgInt("1"), intTwo_);
    }
    @Test
    public void seqAmong1(){
        CustList<LgInt> repartitions_ = new CustList<LgInt>();
        repartitions_.add(new LgInt(8));
        repartitions_.add(new LgInt(8));
        repartitions_.add(new LgInt(8));
        repartitions_.add(new LgInt(8));
        LgInt sommeTotale_ = new LgInt(8);
        AbsMap<CustList<LgInt>,LgInt> combinatoire_ = LgInt.seqAmong(repartitions_, sommeTotale_);
        assertEq(15,combinatoire_.size());
        assertTrue(combinatoire_.contains(new CustList<LgInt>(LgInt.zero(),LgInt.zero(),LgInt.zero(),new LgInt(8))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(LgInt.zero(),LgInt.zero(),new LgInt(1),new LgInt(7))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(LgInt.zero(),LgInt.zero(),new LgInt(2),new LgInt(6))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(LgInt.zero(),new LgInt(1),new LgInt(1),new LgInt(6))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(LgInt.zero(),LgInt.zero(),new LgInt(3),new LgInt(5))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(LgInt.zero(),new LgInt(1),new LgInt(2),new LgInt(5))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(new LgInt(1),new LgInt(1),new LgInt(1),new LgInt(5))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(LgInt.zero(),LgInt.zero(),new LgInt(4),new LgInt(4))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(LgInt.zero(),new LgInt(2),new LgInt(2),new LgInt(4))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(LgInt.zero(),new LgInt(1),new LgInt(3),new LgInt(4))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(new LgInt(1),new LgInt(1),new LgInt(2),new LgInt(4))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(LgInt.zero(),new LgInt(2),new LgInt(3),new LgInt(3))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(new LgInt(1),new LgInt(1),new LgInt(3),new LgInt(3))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(new LgInt(1),new LgInt(2),new LgInt(2),new LgInt(3))));
        assertTrue(combinatoire_.contains(new CustList<LgInt>(new LgInt(2),new LgInt(2),new LgInt(2),new LgInt(2))));
        repartitions_ = new CustList<LgInt>();
        repartitions_.add(new LgInt(22));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
    }
    @Test
    public void seqAmong2(){
        LgInt sommeTotale_ = new LgInt(8);
        CustList<LgInt> repartitions_ = new CustList<LgInt>();
        repartitions_.add(new LgInt(22));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        repartitions_.add(new LgInt(14));
        AbsMap<CustList<LgInt>,LgInt> combinatoire_ = LgInt.seqAmong(repartitions_, sommeTotale_);
        assertEq(18,combinatoire_.size());
        assertEq(5,combinatoire_.getKey(0).size());
        assertEq(new LgInt(0),combinatoire_.getKey(0).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(0).get(1));
        assertEq(new LgInt(0),combinatoire_.getKey(0).get(2));
        assertEq(new LgInt(0),combinatoire_.getKey(0).get(3));
        assertEq(new LgInt(8),combinatoire_.getKey(0).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(0));
        assertEq(5,combinatoire_.getKey(1).size());
        assertEq(new LgInt(0),combinatoire_.getKey(1).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(1).get(1));
        assertEq(new LgInt(0),combinatoire_.getKey(1).get(2));
        assertEq(new LgInt(1),combinatoire_.getKey(1).get(3));
        assertEq(new LgInt(7),combinatoire_.getKey(1).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(1));
        assertEq(5,combinatoire_.getKey(2).size());
        assertEq(new LgInt(0),combinatoire_.getKey(2).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(2).get(1));
        assertEq(new LgInt(0),combinatoire_.getKey(2).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(2).get(3));
        assertEq(new LgInt(6),combinatoire_.getKey(2).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(2));
        assertEq(5,combinatoire_.getKey(3).size());
        assertEq(new LgInt(0),combinatoire_.getKey(3).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(3).get(1));
        assertEq(new LgInt(0),combinatoire_.getKey(3).get(2));
        assertEq(new LgInt(3),combinatoire_.getKey(3).get(3));
        assertEq(new LgInt(5),combinatoire_.getKey(3).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(3));
        assertEq(5,combinatoire_.getKey(4).size());
        assertEq(new LgInt(0),combinatoire_.getKey(4).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(4).get(1));
        assertEq(new LgInt(0),combinatoire_.getKey(4).get(2));
        assertEq(new LgInt(4),combinatoire_.getKey(4).get(3));
        assertEq(new LgInt(4),combinatoire_.getKey(4).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(4));
        assertEq(5,combinatoire_.getKey(5).size());
        assertEq(new LgInt(0),combinatoire_.getKey(5).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(5).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(5).get(2));
        assertEq(new LgInt(1),combinatoire_.getKey(5).get(3));
        assertEq(new LgInt(6),combinatoire_.getKey(5).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(5));
        assertEq(5,combinatoire_.getKey(6).size());
        assertEq(new LgInt(0),combinatoire_.getKey(6).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(6).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(6).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(6).get(3));
        assertEq(new LgInt(5),combinatoire_.getKey(6).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(6));
        assertEq(5,combinatoire_.getKey(7).size());
        assertEq(new LgInt(0),combinatoire_.getKey(7).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(7).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(7).get(2));
        assertEq(new LgInt(3),combinatoire_.getKey(7).get(3));
        assertEq(new LgInt(4),combinatoire_.getKey(7).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(7));
        assertEq(5,combinatoire_.getKey(8).size());
        assertEq(new LgInt(0),combinatoire_.getKey(8).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(8).get(1));
        assertEq(new LgInt(2),combinatoire_.getKey(8).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(8).get(3));
        assertEq(new LgInt(4),combinatoire_.getKey(8).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(8));
        assertEq(5,combinatoire_.getKey(9).size());
        assertEq(new LgInt(0),combinatoire_.getKey(9).get(0));
        assertEq(new LgInt(0),combinatoire_.getKey(9).get(1));
        assertEq(new LgInt(2),combinatoire_.getKey(9).get(2));
        assertEq(new LgInt(3),combinatoire_.getKey(9).get(3));
        assertEq(new LgInt(3),combinatoire_.getKey(9).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(9));
        assertEq(5,combinatoire_.getKey(10).size());
        assertEq(new LgInt(0),combinatoire_.getKey(10).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(10).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(10).get(2));
        assertEq(new LgInt(1),combinatoire_.getKey(10).get(3));
        assertEq(new LgInt(5),combinatoire_.getKey(10).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(10));
        assertEq(5,combinatoire_.getKey(11).size());
        assertEq(new LgInt(0),combinatoire_.getKey(11).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(11).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(11).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(11).get(3));
        assertEq(new LgInt(4),combinatoire_.getKey(11).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(11));
        assertEq(5,combinatoire_.getKey(12).size());
        assertEq(new LgInt(0),combinatoire_.getKey(12).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(12).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(12).get(2));
        assertEq(new LgInt(3),combinatoire_.getKey(12).get(3));
        assertEq(new LgInt(3),combinatoire_.getKey(12).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(12));
        assertEq(5,combinatoire_.getKey(13).size());
        assertEq(new LgInt(0),combinatoire_.getKey(13).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(13).get(1));
        assertEq(new LgInt(2),combinatoire_.getKey(13).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(13).get(3));
        assertEq(new LgInt(3),combinatoire_.getKey(13).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(13));
        assertEq(5,combinatoire_.getKey(14).size());
        assertEq(new LgInt(0),combinatoire_.getKey(14).get(0));
        assertEq(new LgInt(2),combinatoire_.getKey(14).get(1));
        assertEq(new LgInt(2),combinatoire_.getKey(14).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(14).get(3));
        assertEq(new LgInt(2),combinatoire_.getKey(14).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(14));
        assertEq(5,combinatoire_.getKey(15).size());
        assertEq(new LgInt(1),combinatoire_.getKey(15).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(15).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(15).get(2));
        assertEq(new LgInt(1),combinatoire_.getKey(15).get(3));
        assertEq(new LgInt(4),combinatoire_.getKey(15).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(15));
        assertEq(5,combinatoire_.getKey(16).size());
        assertEq(new LgInt(1),combinatoire_.getKey(16).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(16).get(1));
        assertEq(new LgInt(1),combinatoire_.getKey(16).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(16).get(3));
        assertEq(new LgInt(3),combinatoire_.getKey(16).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(16));
        assertEq(5,combinatoire_.getKey(17).size());
        assertEq(new LgInt(1),combinatoire_.getKey(17).get(0));
        assertEq(new LgInt(1),combinatoire_.getKey(17).get(1));
        assertEq(new LgInt(2),combinatoire_.getKey(17).get(2));
        assertEq(new LgInt(2),combinatoire_.getKey(17).get(3));
        assertEq(new LgInt(2),combinatoire_.getKey(17).get(4));
        assertEq(new LgInt(478),combinatoire_.getValue(17));

    }
    @Test
    public void seqAmong3(){
        CustList<LgInt> repartitions_ = new CustList<LgInt>();
        repartitions_.add(new LgInt(-4));
        repartitions_.add(new LgInt(-4));
        repartitions_.add(new LgInt(-4));
        repartitions_.add(new LgInt(-4));
        LgInt sommeTotale_ = new LgInt(8);
        AbsMap<CustList<LgInt>,LgInt> combinatoire_ = LgInt.seqAmong(repartitions_, sommeTotale_);
        assertEq(1,combinatoire_.size());
        assertEq(0, combinatoire_.firstKey().size());
        assertEq(new LgInt(1), combinatoire_.firstValue());
        CustList<LgInt> other_ = new CustList<LgInt>();
        other_.add(LgInt.one());
        assertTrue(new ComparatorEvents().compare(new CustList<LgInt>(),other_) != 0);
    }
    @Test
    public void multiplyDouble1Test(){
        LgInt int_ = new LgInt("0");
        LgInt res_ = int_.multiply(0.5);
        assertEq(new LgInt("0"), res_);
        assertEq(new LgInt("0"), int_);
    }
    @Test
    public void multiplyDouble2Test(){
        LgInt int_ = new LgInt("1");
        LgInt res_ = int_.multiply(0.5);
        assertEq(new LgInt("0"), res_);
        assertEq(new LgInt("1"), int_);
    }
    @Test
    public void multiplyDouble3Test(){
        LgInt int_ = new LgInt("1");
        LgInt res_ = int_.multiply(1.0);
        assertEq(new LgInt("1"), res_);
        assertEq(new LgInt("1"), int_);
    }
    @Test
    public void multiplyDouble4Test(){
        LgInt int_ = new LgInt("2");
        LgInt res_ = int_.multiply(1.0);
        assertEq(new LgInt("2"), res_);
        assertEq(new LgInt("2"), int_);
    }
    @Test
    public void multiplyDouble5Test(){
        LgInt int_ = new LgInt("2");
        LgInt res_ = int_.multiply(1.5);
        assertEq(new LgInt("3"), res_);
        assertEq(new LgInt("2"), int_);
    }
    @Test
    public void multiplyDouble6Test(){
        LgInt int_ = new LgInt("4");
        LgInt res_ = int_.multiply(0.75);
        assertEq(new LgInt("3"), res_);
        assertEq(new LgInt("4"), int_);
    }
    @Test
    public void multiplyDouble7Test(){
        LgInt int_ = new LgInt("12");
        LgInt res_ = int_.multiply(0.75);
        assertEq(new LgInt("9"), res_);
        assertEq(new LgInt("12"), int_);
    }
    @Test
    public void multiplyDouble8Test(){
        LgInt int_ = new LgInt("900000000");
        LgInt res_ = int_.multiply(1.5);
        assertEq(new LgInt("1350000000"), res_);
        assertEq(new LgInt("900000000"), int_);
    }
    @Test
    public void multiplyDouble9Test(){
        LgInt int_ = new LgInt("1234567890123456789");
        LgInt res_ = int_.multiply(0.75);
        assertEq(new LgInt("925925917592592591"), res_);
        assertEq(new LgInt("1234567890123456789"), int_);
    }
    @Test
    public void multiplyDouble10Test(){
        LgInt int_ = new LgInt("1234567890123456789");
        LgInt res_ = int_.multiply(0.0);
        assertEq(new LgInt("0"), res_);
        assertEq(new LgInt("1234567890123456789"), int_);
    }
    @Test
    public void rootAbs1Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("1");
        assertEq(new LgInt("0"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("1"), intTwo_);
    }
    @Test
    public void rootAbs2Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("2");
        assertEq(new LgInt("0"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("2"), intTwo_);
    }
    @Test
    public void rootAbs3Test(){
        LgInt intOne_ = new LgInt("1");
        LgInt intTwo_ = new LgInt("0");
        assertEq(new LgInt("1"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("1"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void rootAbs4Test(){
        LgInt intOne_ = new LgInt("0");
        LgInt intTwo_ = new LgInt("0");
        assertEq(new LgInt("0"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("0"), intOne_);
        assertEq(new LgInt("0"), intTwo_);
    }
    @Test
    public void rootAbs5Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("20");
        assertEq(new LgInt("1"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("20"), intTwo_);
    }
    @Test
    public void rootAbs6Test(){
        LgInt intOne_ = new LgInt("-1");
        LgInt intTwo_ = new LgInt("19");
        assertEq(new LgInt("1"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("-1"), intOne_);
        assertEq(new LgInt("19"), intTwo_);
    }
    @Test
    public void rootAbs7Test(){
        LgInt intOne_ = new LgInt("4");
        LgInt intTwo_ = new LgInt("2");
        assertEq(new LgInt("2"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("4"), intOne_);
        assertEq(new LgInt("2"), intTwo_);
    }
    @Test
    public void rootAbs8Test(){
        LgInt intOne_ = new LgInt("5");
        LgInt intTwo_ = new LgInt("2");
        assertEq(new LgInt("2"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("5"), intOne_);
        assertEq(new LgInt("2"), intTwo_);
    }
    @Test
    public void rootAbs9Test(){
        LgInt intOne_ = new LgInt("32");
        LgInt intTwo_ = new LgInt("5");
        assertEq(new LgInt("2"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("32"), intOne_);
        assertEq(new LgInt("5"), intTwo_);
    }
    @Test
    public void rootAbs10Test(){
        LgInt intOne_ = new LgInt("32");
        LgInt intTwo_ = new LgInt("1");
        assertEq(new LgInt("32"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("32"), intOne_);
        assertEq(new LgInt("1"), intTwo_);
    }
    @Test
    public void rootAbs11Test(){
        LgInt intOne_ = new LgInt("-32");
        LgInt intTwo_ = new LgInt("5");
        assertEq(new LgInt("2"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("-32"), intOne_);
        assertEq(new LgInt("5"), intTwo_);
    }
    @Test
    public void rootAbs12Test(){
        LgInt intOne_ = new LgInt("-16");
        LgInt intTwo_ = new LgInt("4");
        assertEq(new LgInt("2"), intOne_.rootAbs(intTwo_));
        assertEq(new LgInt("-16"), intOne_);
        assertEq(new LgInt("4"), intTwo_);
    }
    @Test
    public void getDividers1Test() {
        LgInt int_ = new LgInt(42);
        CustList<LgInt> div_ = int_.getDividers();
        assertEq(8, div_.size());
        assertEq(new LgInt(1), div_.get(0));
        assertEq(new LgInt(42), div_.get(1));
        assertEq(new LgInt(2), div_.get(2));
        assertEq(new LgInt(21), div_.get(3));
        assertEq(new LgInt(3), div_.get(4));
        assertEq(new LgInt(14), div_.get(5));
        assertEq(new LgInt(6), div_.get(6));
        assertEq(new LgInt(7), div_.get(7));
    }
    @Test
    public void getDividers2Test() {
        LgInt int_ = new LgInt(0);
        CustList<LgInt> div_ = int_.getDividers();
        assertEq(0, div_.size());
    }
    @Test
    public void inRange1(){
        assertTrue(new LgInt(2).inRange(new LgInt(1),new LgInt(3)));
    }
    @Test
    public void inRange2(){
        assertTrue(!new LgInt(0).inRange(new LgInt(1),new LgInt(3)));
    }
    @Test
    public void inRange3(){
        assertTrue(!new LgInt(4).inRange(new LgInt(1),new LgInt(3)));
    }
    @Test
    public void inRange4(){
        assertTrue(new LgInt(1).inRange(new LgInt(1),new LgInt(3)));
    }
    @Test
    public void inRange5(){
        assertTrue(new LgInt(3).inRange(new LgInt(1),new LgInt(3)));
    }
    @Test
    public void inRange6(){
        assertTrue(new LgInt(2).inRange(new LgInt(1),null));
    }
    @Test
    public void inRange7(){
        assertTrue(!new LgInt(0).inRange(new LgInt(1),null));
    }
    @Test
    public void inRange8(){
        assertTrue(new LgInt(2).inRange(null,new LgInt(3)));
    }
    @Test
    public void inRange9(){
        assertTrue(!new LgInt(4).inRange(null,new LgInt(3)));
    }
    @Test
    public void inRange10(){
        assertTrue(new LgInt(1).inRange(new LgInt(1),null));
    }
    @Test
    public void inRange11(){
        assertTrue(new LgInt(3).inRange(null,new LgInt(3)));
    }
    @Test
    public void inRange12(){
        assertTrue(new LgInt(2).inRange(null,null));
    }
    @Test
    public void isValid1Test() {
        assertTrue(!LgInt.isValid(""));
    }
    @Test
    public void isValid2Test() {
        assertTrue(!LgInt.isValid(null));
    }
    @Test
    public void isValid3Test() {
        assertTrue(LgInt.isValid("0"));
    }
    @Test
    public void isValid4Test() {
        assertTrue(LgInt.isValid("1"));
    }
    @Test
    public void isValid5Test() {
        assertTrue(LgInt.isValid("-1"));
    }
    @Test
    public void isValid6Test() {
        assertTrue(!LgInt.isValid("a"));
    }
    @Test
    public void isValid7Test() {
        assertTrue(!LgInt.isValid("-a"));
    }
    @Test
    public void isValid8Test() {
        assertTrue(!LgInt.isValid("1a"));
    }
    @Test
    public void isValid9Test() {
        assertTrue(!LgInt.isValid("-"));
    }
    @Test
    public void isPrime1Test() {
        assertTrue(!new LgInt(1).isPrime());
    }
    @Test
    public void isPrime2Test() {
        assertTrue(new LgInt(2).isPrime());
    }
    @Test
    public void isPrime3Test() {
        assertTrue(new LgInt(3).isPrime());
    }
    @Test
    public void isPrime4Test() {
        assertTrue(!new LgInt(4).isPrime());
    }
    @Test
    public void isPrime5Test() {
        assertTrue(new LgInt(5).isPrime());
    }
    @Test
    public void isPrime6Test() {
        assertTrue(!new LgInt(6).isPrime());
    }
    @Test
    public void identiteBezoutPgcdPpcm1Test() {
        LgInt a_ = new LgInt(27);
        LgInt b_ = new LgInt(15);
        IdBezoutNb out_ = LgInt.identiteBezoutPgcdPpcm(a_, b_);
        assertEq(new LgInt(-1),out_.getFirst());
        assertEq(new LgInt(2),out_.getSecond());
        assertEq(new LgInt(3),out_.getPgcd());
        assertEq(new LgInt(135),out_.getPpcm());
    }
    @Test
    public void identiteBezoutPgcdPpcm2Test() {
        LgInt a_ = new LgInt(30);
        LgInt b_ = new LgInt(15);
        IdBezoutNb out_ = LgInt.identiteBezoutPgcdPpcm(a_, b_);
        assertEq(new LgInt(0),out_.getFirst());
        assertEq(new LgInt(1),out_.getSecond());
        assertEq(new LgInt(15),out_.getPgcd());
        assertEq(new LgInt(30),out_.getPpcm());
    }
    @Test
    public void identiteBezoutPgcdPpcm3Test() {
        LgInt a_ = new LgInt(0);
        LgInt b_ = new LgInt(15);
        IdBezoutNb out_ = LgInt.identiteBezoutPgcdPpcm(a_, b_);
        assertEq(new LgInt(1),out_.getFirst());
        assertEq(new LgInt(1),out_.getSecond());
        assertEq(new LgInt(15),out_.getPgcd());
        assertEq(new LgInt(0),out_.getPpcm());
    }
    @Test
    public void identiteBezoutPgcdPpcm4Test() {
        LgInt a_ = new LgInt(15);
        LgInt b_ = new LgInt(0);
        IdBezoutNb out_ = LgInt.identiteBezoutPgcdPpcm(a_, b_);
        assertEq(new LgInt(1),out_.getFirst());
        assertEq(new LgInt(1),out_.getSecond());
        assertEq(new LgInt(15),out_.getPgcd());
        assertEq(new LgInt(0),out_.getPpcm());
    }
    @Test
    public void decompoPrim1Test() {
        LgInt a_ = new LgInt(800);
        Decomposition dec_ = a_.decompoPrim();
        assertTrue(dec_.isPositive());
        assertEq(2,dec_.getFactors().size());
        assertEq(new LgInt(2),dec_.getFactors().get(0).getPrime());
        assertEq(new LgInt(5),dec_.getFactors().get(0).getExponent());
        assertEq(new LgInt(5),dec_.getFactors().get(1).getPrime());
        assertEq(new LgInt(2),dec_.getFactors().get(1).getExponent());
    }
    @Test
    public void decompoPrim2Test() {
        LgInt a_ = new LgInt(7);
        Decomposition dec_ = a_.decompoPrim();
        assertTrue(dec_.isPositive());
        assertEq(1,dec_.getFactors().size());
        assertEq(new LgInt(7),dec_.getFactors().get(0).getPrime());
        assertEq(new LgInt(1),dec_.getFactors().get(0).getExponent());
    }
    @Test
    public void decompoPrim3Test() {
        LgInt a_ = new LgInt(8);
        Decomposition dec_ = a_.decompoPrim();
        assertTrue(dec_.isPositive());
        assertEq(1,dec_.getFactors().size());
        assertEq(new LgInt(2),dec_.getFactors().get(0).getPrime());
        assertEq(new LgInt(3),dec_.getFactors().get(0).getExponent());
    }
    @Test
    public void decompoPrim4Test() {
        LgInt a_ = new LgInt(0);
        Decomposition dec_ = a_.decompoPrim();
        assertTrue(dec_.isPositive());
        assertEq(0,dec_.getFactors().size());
    }
    @Test
    public void decompoPrim5Test() {
        LgInt a_ = new LgInt(1);
        Decomposition dec_ = a_.decompoPrim();
        assertTrue(dec_.isPositive());
        assertEq(1,dec_.getFactors().size());
        assertEq(new LgInt(1),dec_.getFactors().get(0).getPrime());
        assertEq(new LgInt(1),dec_.getFactors().get(0).getExponent());
    }
    @Test
    public void decompoPrim6Test() {
        LgInt a_ = new LgInt(-1);
        Decomposition dec_ = a_.decompoPrim();
        assertTrue(!dec_.isPositive());
        assertEq(1,dec_.getFactors().size());
        assertEq(new LgInt(1),dec_.getFactors().get(0).getPrime());
        assertEq(new LgInt(1),dec_.getFactors().get(0).getExponent());
    }
    @Test
    public void newLgIntStringTest() {
        LgInt a_ = LgInt.newLgInt("0");
        assertEq(new LgInt(0),a_);
    }
    @Test
    public void displayTest() {
        LgInt a_ = new LgInt(0);
        assertEq("0",a_.display());
    }
    @Test
    public void cmp1() {
        assertEq(1, new ComparatorLgInt().compare(new LgInt("2"),new LgInt("1")));
    }
    @Test
    public void cmp2() {
        assertEq(-1, new ComparatorLgInt().compare(new LgInt("1"),new LgInt("2")));
    }
    @Test
    public void cmp3() {
        assertEq(0, new ComparatorLgInt().compare(new LgInt("1"),new LgInt("1")));
    }
}