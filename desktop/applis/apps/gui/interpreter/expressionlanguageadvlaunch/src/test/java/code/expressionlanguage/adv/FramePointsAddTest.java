package code.expressionlanguage.adv;

import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.utilimpl.ManageOptions;
import code.util.StringMap;
import org.junit.Test;

public final class FramePointsAddTest extends EquallableElAdvUtil {
    @Test
    public void valid1() {
        assertFalse(valid("public class pkg.Ex{}",false,"",""));
    }
    @Test
    public void valid2() {
        assertFalse(valid("public class pkg.Ex{}",true,"",""));
    }
    @Test
    public void valid3() {
        assertFalse(valid("public class pkg.Ex{}",false,"pkg.Ex",""));
    }
    @Test
    public void valid4() {
        assertFalse(valid("public class pkg.Ex{}",true,"pkg.Ex",""));
    }
    @Test
    public void valid5() {
        assertTrue(valid("public class pkg.Ex{public int f;}",true,"pkg.Ex","f"));
    }
    @Test
    public void valid6() {
        assertFalse(valid("public annotation pkg.Ex{int f();}",false,"pkg.Ex",""));
    }
    @Test
    public void valid7() {
        assertTrue(valid("public annotation pkg.Ex{int f();}",false,"pkg.Ex","f"));
    }
    @Test
    public void valid8() {
        assertTrue(valid("public annotation pkg.Ex{int f;}",true,"pkg.Ex","f"));
    }
    private static boolean valid(String _src, boolean _trField, String _cl, String _field) {
        AbsDebuggerGui b_ = build();
        ManageOptions o_ = opt(b_);
        ResultContext r_ = res(b_, o_);
        StringMap<String> src_ = new StringMap<String>();
        src_.addEntry("src/file.txt", _src);
        ResultContext res_ = analyzed(b_, o_, r_, src_);
        return OkWpFormEvent.valid(res_.getPageEl().getAnaClassBody(_cl), _trField, _field);
    }

    private static ResultContext analyzed(AbsDebuggerGui _b, ManageOptions _o, ResultContext _r, StringMap<String> _src) {
        guiAna(_r, _b, _o, _src);
        return ((OpenFramePointsEvent)_b.getOpenPoints().getActionListeners().get(0)).getCurrentResult();
    }
}
