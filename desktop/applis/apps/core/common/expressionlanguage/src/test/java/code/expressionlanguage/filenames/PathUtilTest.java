package code.expressionlanguage.filenames;

import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;

public final class PathUtilTest {
    @Test
    public void transform1() {
        assertEq("/folder",PathUtil.transform("/","folder"));
    }
    @Test
    public void transform2() {
        assertEq("/",PathUtil.transform("/folder/",".."));
    }
    @Test
    public void transform3() {
        assertEq("/folder/",PathUtil.transform("/folder/sub/",".."));
    }
    @Test
    public void transform4() {
        assertEq("/folder/other",PathUtil.transform("/folder/sub/","../other"));
    }
    @Test
    public void transform5() {
        assertEq("/folder/other/",PathUtil.transform("/folder/sub/","../other/"));
    }
    @Test
    public void transform6() {
        assertEq("/",PathUtil.transform("/folder/sub/","../.."));
    }
    @Test
    public void transform7() {
        assertEq("/",PathUtil.transform("/folder/sub/","../../"));
    }
    @Test
    public void transform8() {
        assertEq("/folder/",PathUtil.transform("/folder/sub/",".."));
    }
    @Test
    public void transform9() {
        assertEq("/folder/",PathUtil.transform("/folder/sub/","../"));
    }
    @Test
    public void transform10() {
        assertEq("/",PathUtil.transform("/",".."));
    }
    @Test
    public void transform11() {
        assertEq("/",PathUtil.transform("/folder/","../.."));
    }
    @Test
    public void transform12() {
        assertEq("/",PathUtil.transform("/folder/","../../"));
    }
    @Test
    public void transform13() {
        assertEq("/",PathUtil.transform("/","../../"));
    }
    @Test
    public void transform14() {
        assertEq("/folder/",PathUtil.transform("/","folder/"));
    }
    @Test
    public void transform15() {
        assertEq("/folder/",PathUtil.transform("/folder/sup/sub/","../.."));
    }
    @Test
    public void transform16() {
        assertEq("/folder/",PathUtil.transform("/folder/sup/sub/","../../"));
    }
}
