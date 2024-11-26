package code.gui;

import code.gui.events.AlwaysActionListenerAct;
import code.gui.initialize.CompoundedInitParts;
import code.gui.initialize.ProgramInfosBase;
import code.sml.util.Translations;
import code.stream.AbstractFile;
import code.stream.BytesInfo;
import code.stream.FileListInfo;
import code.stream.core.ComZipStreamIn;
import code.stream.core.ContentTime;
import code.stream.core.TechStreams;
import code.threads.FileStruct;
import code.threads.ThState;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public class ThreadsTest extends EquallableIntGuiUtil {
    @Test
    public void alive1() {
        assertSame(ThState.ALIVE,ThState.of(true));
    }
    @Test
    public void alive2() {
        assertSame(ThState.ENDED,ThState.of(false));
    }
    @Test
    public void file1() {
        FileStruct f_ = new FileStruct(new byte[0],new ThreadFactorySample());
        assertEq(0,f_.getContent().length);
        assertEq(0,f_.getLastDate());
    }
    @Test
    public void file2() {
        FileStruct f_ = new FileStruct(new byte[0],0);
        f_.setContent(new byte[0]);
        assertEq(0,f_.getContent().length);
        assertEq(0,f_.getLastDate());
    }
    @Test
    public void file3() {
        BytesInfo f_ = new BytesInfo(new byte[0],true);
        assertEq(0,f_.getBytes().length);
        assertTrue(f_.isNul());
    }
    @Test
    public void file4() {
        BytesInfo f_ = new BytesInfo(new byte[0],false);
        assertEq(0,f_.getBytes().length);
        assertFalse(f_.isNul());
    }
    @Test
    public void ls() {
        FileListInfo f_ = new FileListInfo(null);
        assertTrue(f_.isNul());
        assertEq(0,f_.getNames().length);
    }
    @Test
    public void ls2() {
        FileListInfo f_ = new FileListInfo(new AbstractFile[0]);
        assertFalse(f_.isNul());
        assertEq(0,f_.getNames().length);
    }
    @Test
    public void ls3() {
        TechStreams t_ = new TechStreams(null, null);
        assertNull(t_.getBinFact());
//        assertNull(t_.getTextFact());
        assertNull(t_.getZipFact());
    }
    @Test
    public void ls4() {
        ContentTime t_ = new ContentTime(new byte[0],0);
        assertEq(0, t_.getLastModifTime());
        assertEq(0, t_.getContent().length);
    }
    @Test
    public void ls5() {
        ComZipStreamIn t_ = new ComZipStreamIn();
        t_.setDirectory(true);
        t_.setName("_");
        t_.setSize(2);
        t_.setTime(3);
        assertEq(2, t_.getSize());
        assertEq(3, t_.getTime());
        assertEq("_", t_.getName());
        assertTrue(t_.isDirectory());
    }
    @Test
    public void ls6() {
        ProgramInfosBase t_ = new ProgramInfosBase("","",null, new CompoundedInitParts(null,null,null,null,null));
        t_.setLanguages(new StringList());
        t_.setDisplayLanguages(new StringMap<String>());
        t_.setTranslations(new Translations());
        t_.getTranslations().getMapping().clear();
        t_.setLanguage("");
        assertTrue(t_.lg("").getMapping().isEmpty());
        assertNull(t_.getThreadFactory());
        assertNull(t_.getCompoFactory());
        assertNull(t_.getGenerator());
//        assertNull(t_.getGeneStrCompo());
        assertNull(t_.getValidator());
        assertNull(t_.getZipFact());
        assertNull(t_.getImageFactory());
//        assertNull(t_.getInterceptor());
        assertEq("",t_.getHomePath());
        assertEq("",t_.getTmpUserFolder());
//        assertEq(0, t_.getCounts().size());
//        assertEq(0, t_.getButtons().size());
        assertEq(0, t_.getFrames().size());
        assertEq(0, t_.getLanguages().size());
        assertEq(0, t_.getDisplayLanguages().size());
        assertEq(1, t_.getTranslations().getMapping().size());
        assertEq("",t_.getLanguage());
        assertEq(0, t_.currentLg().getMapping().size());
    }
    @Test
    public void isZip1(){
        FileStruct f_ = new FileStruct(null,0);
        assertFalse(isZip(f_.getContent()));
    }
    @Test
    public void isZip2(){
        assertFalse(isZip(new byte[0]));
    }
    @Test
    public void isZip3(){
        assertFalse(isZip(new byte[4]));
    }
    @Test
    public void isZip4(){
        assertFalse(isZip(SortConstants.wrapByteArray((byte)0x0,(byte)0x0, (byte)0x0,(byte)0x0)));
    }
    @Test
    public void isZip5(){
        assertFalse(isZip(SortConstants.wrapByteArray((byte)0x50,(byte)0x0, (byte)0x0,(byte)0x0)));
    }
    @Test
    public void isZip6(){
        assertFalse(isZip(SortConstants.wrapByteArray((byte)0x50,(byte)0x4b, (byte)0x0,(byte)0x0)));
    }
    @Test
    public void isZip7(){
        assertFalse(isZip(SortConstants.wrapByteArray((byte)0x50,(byte)0x4b, (byte)0x03,(byte)0x0)));
    }
    @Test
    public void isZip8(){
        assertTrue(isZip(SortConstants.wrapByteArray((byte)0x50,(byte)0x4b, (byte)0x03,(byte)0x4)));
    }
    @Test
    public void s1() {
        assertFalse(FileListInfo.isWav(wrapInts()));
    }
    @Test
    public void s2() {
        assertFalse(FileListInfo.isWav(wrapInts('R','I','F','F',0,0,0,0,'W','A','V',203)));
    }
    @Test
    public void s3() {
        assertFalse(FileListInfo.isWav(wrapInts('R','I','F','F',0,0,0,0,'W','A','v',203)));
    }
    @Test
    public void s4() {
        assertFalse(FileListInfo.isWav(wrapInts('R','I','F','F',0,0,0,0,'W','a','v',203)));
    }
    @Test
    public void s5() {
        assertFalse(FileListInfo.isWav(wrapInts('R','I','F','F',0,0,0,0,'w','a','v',203)));
    }
    @Test
    public void s6() {
        assertFalse(FileListInfo.isWav(wrapInts('R','I','F','f',0,0,0,0,'w','a','v',203)));
    }
    @Test
    public void s7() {
        assertFalse(FileListInfo.isWav(wrapInts('R','I','f','f',0,0,0,0,'w','a','v',203)));
    }
    @Test
    public void s8() {
        assertFalse(FileListInfo.isWav(wrapInts('R','i','f','f',0,0,0,0,'w','a','v',203)));
    }
    @Test
    public void s9() {
        assertFalse(FileListInfo.isWav(wrapInts('r','i','f','f',0,0,0,0,'w','a','v',203)));
    }
    @Test
    public void s10() {
        assertTrue(FileListInfo.isWav(wrapInts('R', 'I', 'F', 'F', 0, 0, 0, 0, 'W', 'A', 'V', 'E', 1)));
    }
    @Test
    public void s11() {
        assertFalse(FileListInfo.isMp3(wrapInts()));
    }
    @Test
    public void s12() {
        assertFalse(FileListInfo.isMp3(wrapInts('I','D','4')));
    }
    @Test
    public void s13() {
        assertFalse(FileListInfo.isMp3(wrapInts('I','d','4')));
    }
    @Test
    public void s14() {
        assertFalse(FileListInfo.isMp3(wrapInts('i','d','4')));
    }
    @Test
    public void s15() {
        assertFalse(FileListInfo.isMp3(wrapInts(255,25)));
    }
    @Test
    public void s16() {
        assertTrue(FileListInfo.isMp3(wrapInts('I', 'D', '3', 1)));
    }
    @Test
    public void s17() {
        assertTrue(FileListInfo.isMp3(wrapInts(255, 251, 1)));
    }
    @Test
    public void s18() {
        assertTrue(FileListInfo.isMp3(wrapInts(255, 243, 1)));
    }
    @Test
    public void s19() {
        assertTrue(FileListInfo.isMp3(wrapInts(255, 242, 1)));
    }
    @Test
    public void isBinary1() {
        assertTrue(FileListInfo.isBinary(new BytesInfo(wrapInts(0),false)));
    }
    @Test
    public void isBinary2() {
        assertFalse(FileListInfo.isBinary(new BytesInfo(wrapInts('\n'),false)));
    }
    @Test
    public void isBinary3() {
        assertFalse(FileListInfo.isBinary(new BytesInfo(wrapInts('\r'),false)));
    }
    @Test
    public void isBinary4() {
        assertFalse(FileListInfo.isBinary(new BytesInfo(wrapInts('\t'),false)));
    }
    @Test
    public void isBinary5() {
        assertFalse(FileListInfo.isBinary(new BytesInfo(wrapInts('0'),false)));
    }
    @Test
    public void isBinary6() {
        assertFalse(FileListInfo.isBinary(new BytesInfo(wrapInts(),true)));
    }
    @Test
    public void start1() {
        assertFalse(FileListInfo.startsWith(wrapInts(5,8,6),wrapInts(5,8,6,7)));
    }
    @Test
    public void start2() {
        assertFalse(FileListInfo.startsWith(wrapInts(5,8,6),wrapInts(5,7)));
    }
    @Test
    public void start3() {
        assertTrue(FileListInfo.startsWith(wrapInts(5,8,6,7),wrapInts(5,8,6)));
    }
    @Test
    public void start4() {
        assertTrue(FileListInfo.extractWithPrefixes(wrapInts(5,8,6),wrapInts(5,7)).isNul());
    }
    @Test
    public void start5() {
        assertFalse(FileListInfo.extractWithPrefixes(wrapInts(5,8,6,7),wrapInts(5,8,6)).isNul());
    }
    @Test
    public void actList() {
        assertTrue(new AlwaysActionListenerAct().act());
    }
    private boolean isZip(byte[] _bs) {
        return FileListInfo.isZip(_bs);
    }

    public static byte[] wrapInts(int... _files) {
        return Ints.newList(_files).toArrByte();
    }
}
