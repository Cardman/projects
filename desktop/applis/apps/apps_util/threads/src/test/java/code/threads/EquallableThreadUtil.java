package code.threads;

import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.MockFileSet;
import org.junit.Assert;

public abstract class EquallableThreadUtil {

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    protected static MockFileSet fileSet(String... _roots) {
        return new MockFileSet(0,new long[1],_roots);
    }
    protected static DefaultGenerator gene(double... _args) {
        return new DefaultGenerator(new CustomSeedGene(dbs(_args)));
    }
    protected static double[] dbs(double... _args) {
        return _args;
    }
}
