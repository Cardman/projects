package code.format;

import org.junit.Test;

public final class FormatTest extends EquallableFormat {
    @Test
    public void getConstanteLangue1() {
        assertEq("",Format.getConstanteLangue("","",""));
    }
    @Test
    public void getConstanteLangue2() {
        assertEq("value",Format.getConstanteLangue("key.sub:value","key","sub"));
    }
}
