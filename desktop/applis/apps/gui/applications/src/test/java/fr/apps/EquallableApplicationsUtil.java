package fr.apps;

import aiki.db.DataBase;
import aiki.db.ImageArrayBaseSixtyFour;
import aiki.db.MessagesDataBaseConstants;
import code.gui.files.FileDialogContent;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Assert;

public abstract class EquallableApplicationsUtil {
    public static final String BASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    public static final String VAR_PREFIX = MessagesDataBaseConstants.VAR_DEF+DataBase.SEP_BETWEEN_KEYS;
    public static final String EN = StringUtil.EN;
    public static MockProgramInfos build() {
        return build("", "",dbs(0.75));
    }
    public static MockProgramInfos build(String _h, String _t, double[] _dbs) {
        MockProgramInfos pr_ = MockProgramInfos.inst(_h, _t, new CustomSeedGene(_dbs), new MockFileSet(0, new long[1], new String[]{"/"}));
        pr_.setLanguage(EN);
        StringMap<String> indexes_ = pr_.getTranslations().getIndexes();
        indexes_.clear();
        indexes_.addAllEntries(FileDialogContent.index(EN, pr_.getValidator()));
        return pr_;
    }

    public static double[] dbs(double... _args) {
        return _args;
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static ImageArrayBaseSixtyFour instance(int[][] _img) {
        return ImageArrayBaseSixtyFour.instance(_img,BASE);
    }
}
