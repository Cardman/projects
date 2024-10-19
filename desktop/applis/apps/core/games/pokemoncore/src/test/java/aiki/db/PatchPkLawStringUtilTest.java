package aiki.db;

import aiki.util.PatchPkLawStringUtil;
import code.maths.LgInt;
import code.maths.montecarlo.MonteCarloString;
import org.junit.Test;

public final class PatchPkLawStringUtilTest extends EquallablePkUtil {
    @Test
    public void patch1() {
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent("0", new LgInt(3));
        law_.addQuickEvent("1", new LgInt(4));
        law_.addQuickEvent("2", new LgInt(5));
        MonteCarloString patch_ = PatchPkLawStringUtil.patch(law_);
        assertEq(3, patch_.nbEvents());
        assertEq(new LgInt(3), patch_.getLaw().getVal("0"));
        assertEq(new LgInt(4), patch_.getLaw().getVal("1"));
        assertEq(new LgInt(5), patch_.getLaw().getVal("2"));
    }
    @Test
    public void patch2() {
        MonteCarloString law_ = new MonteCarloString();
        law_.addQuickEvent("0", new LgInt(3));
        law_.addQuickEvent("0", new LgInt(4));
        law_.addQuickEvent("2", new LgInt(5));
        MonteCarloString patch_ = PatchPkLawStringUtil.patch(law_);
        assertEq(2, patch_.nbEvents());
        assertEq(new LgInt(7), patch_.getLaw().getVal("0"));
        assertEq(new LgInt(5), patch_.getLaw().getVal("2"));
    }
}
