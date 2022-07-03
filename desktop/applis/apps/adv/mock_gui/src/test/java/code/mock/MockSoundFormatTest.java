package code.mock;

import code.stream.AbsClipStream;
import org.junit.Test;

public final class MockSoundFormatTest extends EquallableMockGuiUtil {
    @Test
    public void s1() {
        assertNull(init().openClip(wrapInts()));
    }
    @Test
    public void s2() {
        assertNull(init().openClip(wrapInts('R','I','F','F',0,0,0,0,'W','A','V','E')));
    }
    @Test
    public void s3() {
        assertNull(init().openClip(wrapInts('R','I','F','F',0,0,0,0,'W','A','V',203)));
    }
    @Test
    public void s4() {
        assertNull(init().openClip(wrapInts('R','I','F','F',0,0,0,0,'W','A','v',203)));
    }
    @Test
    public void s5() {
        assertNull(init().openClip(wrapInts('R','I','F','F',0,0,0,0,'W','a','v',203)));
    }
    @Test
    public void s6() {
        assertNull(init().openClip(wrapInts('R','I','F','F',0,0,0,0,'w','a','v',203)));
    }
    @Test
    public void s7() {
        assertNull(init().openClip(wrapInts('R','I','F','f',0,0,0,0,'w','a','v',203)));
    }
    @Test
    public void s8() {
        assertNull(init().openClip(wrapInts('R','I','f','f',0,0,0,0,'w','a','v',203)));
    }
    @Test
    public void s9() {
        assertNull(init().openClip(wrapInts('R','i','f','f',0,0,0,0,'w','a','v',203)));
    }
    @Test
    public void s10() {
        assertNull(init().openClip(wrapInts('r','i','f','f',0,0,0,0,'w','a','v',203)));
    }
    @Test
    public void s11() {
        assertNull(init().openClip(wrapInts('R','I','F','F',0,0,0,0,'W','A','V','E',-1)));
    }
    @Test
    public void s12() {
        assertNull(init().openClip(wrapInts('R','I','F','F',0,0,0,0,'W','A','V','E',10)));
    }
    @Test
    public void s13() {
        AbsClipStream expected_ = init().openClip(wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1));
        assertEq(1, expected_.getMicrosecondLength());
    }
    @Test
    public void s14() {
        assertNull(init().openMp3(wrapInts()));
    }
    @Test
    public void s15() {
        assertNull(init().openMp3(wrapInts('I','D','3')));
    }
    @Test
    public void s16() {
        assertNull(init().openMp3(wrapInts('I','D','4')));
    }
    @Test
    public void s17() {
        assertNull(init().openMp3(wrapInts('I','d','4')));
    }
    @Test
    public void s18() {
        assertNull(init().openMp3(wrapInts('i','d','4')));
    }
    @Test
    public void s19() {
        assertNull(init().openMp3(wrapInts(255,251)));
    }
    @Test
    public void s20() {
        assertNull(init().openMp3(wrapInts(255,25)));
    }
    @Test
    public void s21() {
        assertNull(init().openMp3(wrapInts(255,243)));
    }
    @Test
    public void s22() {
        assertNull(init().openMp3(wrapInts(255,242)));
    }
    @Test
    public void s23() {
        AbsClipStream expected_ = init().openMp3(wrapInts('I', 'D', '3', 1));
        assertEq(1, expected_.getMicrosecondLength());
    }
    @Test
    public void s24() {
        AbsClipStream expected_ = init().openMp3(wrapInts(255, 251, 1));
        assertEq(1, expected_.getMicrosecondLength());
    }
    @Test
    public void s25() {
        AbsClipStream expected_ = init().openMp3(wrapInts(255, 243, 1));
        assertEq(1, expected_.getMicrosecondLength());
    }
    @Test
    public void s26() {
        AbsClipStream expected_ = init().openMp3(wrapInts(255, 242, 1));
        assertEq(1, expected_.getMicrosecondLength());
    }
}
