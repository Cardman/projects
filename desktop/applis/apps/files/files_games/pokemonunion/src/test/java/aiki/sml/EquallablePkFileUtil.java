package aiki.sml;

import aiki.db.ImageArrayBaseSixtyFour;
import aiki.game.Game;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import org.junit.Assert;

public abstract class EquallablePkFileUtil {
    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    public static MockProgramInfos pr(long _init, long..._incrs) {
        return prTmp("",_init,_incrs);
    }

    public static MockProgramInfos prTmp(String _tmp,long _init,long..._incrs) {
        MockProgramInfos pr_ = new MockProgramInfos("", _tmp, new CustomSeedGene(new double[]{0.75}), new MockFileSet(_init, _incrs, new String[]{"/"}));
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(pr_.getTranslations()));
        return pr_;
    }

    public static double[] dbs(double... _args) {
        return _args;
    }
    public static void assertNull(Game _value) {
        Assert.assertNull(_value);
    }
    public static void assertNotNull(Game _value) {
        Assert.assertNotNull(_value);
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

    public static ImageArrayBaseSixtyFour instance(int[][] _img) {
        return ImageArrayBaseSixtyFour.instance(_img,BASE);
    }
}
