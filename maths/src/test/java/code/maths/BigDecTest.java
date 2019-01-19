package code.maths;
import static code.maths.EquallableMathUtil.assertEq;

import org.junit.Test;


public class BigDecTest {

    @Test
    public void nthRoot1Test() {
        assertEq("1.2",new BigDec("1.44").nthRoot(2).stripTrailingZeros().display());
    }

    @Test
    public void nthRoot2Test() {
        assertEq("1.2",new BigDec("1.728").nthRoot(3).stripTrailingZeros().display());
    }

    @Test
    public void nthRoot3Test() {
        assertEq("1.414213562373095048801688724209698078569671875376948073176679738",new BigDec("2").nthRoot(2).stripTrailingZeros().display());
    }

}
