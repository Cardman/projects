package code.scripts.imgs.cards;

import code.util.StringMap;
import org.junit.Assert;

public abstract class EquallableScriptsCardsUtil {

    public static void assertNotNullStr(StringMap<StringMap<String>> _value) {
        Assert.assertNotNull(_value);
    }

}
