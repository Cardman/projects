package aiki.sml;

import code.gui.TextAnswerValue;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockEventListIncr;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import org.junit.Assert;

public abstract class EquallablePkFileUtil {

    public static MockProgramInfos pr(long _init, long..._incrs) {
        return prTmp("",_init,_incrs);
    }

    public static MockProgramInfos prTmp(String _tmp,long _init,long..._incrs) {
        return new MockProgramInfos("", _tmp, new MockEventListIncr(new CustomSeedGene(new double[]{0.75}),new int[0], new String[0], new TextAnswerValue[0]), new MockFileSet(_init, _incrs, new String[]{"/"}));
    }

    public static double[] dbs(double... _args) {
        return _args;
    }
    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertEq(String _expected, String _result) {
        Assert.assertEquals(_expected, _result);
    }
}
