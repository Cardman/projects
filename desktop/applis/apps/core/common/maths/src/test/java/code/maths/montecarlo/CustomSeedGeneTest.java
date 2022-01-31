package code.maths.montecarlo;

import code.maths.EquallableMathUtil;
import org.junit.Test;

public final class CustomSeedGeneTest extends EquallableMathUtil {
    @Test
    public void copy() {
        assertNotNull(CustomSeedGene.copy(new CustomSeedGene()));
    }
    @Test
    public void pick() {
        double[] rand_ = new double[1];
        rand_[0] = 0.5;
        assertEq(0.5,new CustomSeedGene(rand_).pick(new DefaultGenerator()));
    }
}
