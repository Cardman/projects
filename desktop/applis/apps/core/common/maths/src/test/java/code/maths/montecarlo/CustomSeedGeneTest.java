package code.maths.montecarlo;

import code.maths.EquallableMathUtil;
import org.junit.Test;

public final class CustomSeedGeneTest extends EquallableMathUtil {
    @Test
    public void copy() {
        assertNotNull(CustomSeedGene.copy(new CustomSeedGene()));
    }
    @Test
    public void pick1() {
        assertEq(0.5,new DefaultGenerator(DefaultGenerator.oneEltGene()).pick());
    }
    @Test
    public void pick2() {
        assertTrue(!Double.isInfinite(DefaultGenerator.sizeOne().pick()));
    }
    @Test
    public void pick3() {
        CustomSeedGene c_ = new CustomSeedGene(DefaultGenerator.oneEltArr());
        assertEq(0.5,c_.pick(DefaultGenerator.oneElt()));
    }
    @Test
    public void pick4() {
        CustomSeedGene c_ = new CustomSeedGene();
        assertEq(0.75,c_.pick(new DefaultGenerator(0.75)));
    }
}
