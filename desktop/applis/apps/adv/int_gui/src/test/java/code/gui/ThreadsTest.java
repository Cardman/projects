package code.gui;

import code.stream.AbstractFile;
import code.stream.FileListInfo;
import code.stream.core.ContentTime;
import code.stream.core.TechStreams;
import code.threads.FileStruct;
import code.threads.ThState;
import code.util.core.NumberUtil;
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
        TechStreams t_ = new TechStreams(null,null,null);
        assertNull(t_.getBinFact());
        assertNull(t_.getTextFact());
        assertNull(t_.getZipFact());
    }
    @Test
    public void ls4() {
        ContentTime t_ = new ContentTime(new byte[0],0);
        assertEq(0, t_.getLastModifTime());
        assertEq(0, t_.getContent().length);
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
        assertFalse(isZip(NumberUtil.wrapByteArray((byte)0x0,(byte)0x0, (byte)0x0,(byte)0x0)));
    }
    @Test
    public void isZip5(){
        assertFalse(isZip(NumberUtil.wrapByteArray((byte)0x50,(byte)0x0, (byte)0x0,(byte)0x0)));
    }
    @Test
    public void isZip6(){
        assertFalse(isZip(NumberUtil.wrapByteArray((byte)0x50,(byte)0x4b, (byte)0x0,(byte)0x0)));
    }
    @Test
    public void isZip7(){
        assertFalse(isZip(NumberUtil.wrapByteArray((byte)0x50,(byte)0x4b, (byte)0x03,(byte)0x0)));
    }
    @Test
    public void isZip8(){
        assertTrue(isZip(NumberUtil.wrapByteArray((byte)0x50,(byte)0x4b, (byte)0x03,(byte)0x4)));
    }

    private boolean isZip(byte[] _bs) {
        return FileListInfo.isZip(_bs);
    }
}
