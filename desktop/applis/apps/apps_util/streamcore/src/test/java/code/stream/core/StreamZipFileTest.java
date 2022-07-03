package code.stream.core;

import code.mock.MockNameFile;
import code.mock.MockZipFact;
import code.util.core.DefaultUniformingString;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;
import org.junit.Test;

public class StreamZipFileTest extends EquallableStreamCoreUtil {
    @Test
    public void zip1() {
        MockZipFact zipFact_ = new MockZipFact();
        ReadFiles zippedFiles_ = StreamZipFile.getZippedFiles(new DefaultUniformingString(), zipFact_.zipBinFiles(MockZipFact.wrapText(wrap(new MockNameFile("__/", (byte[]) null,5), new MockNameFile("_", "-",6), new MockNameFile("0", wrapInts(-1),7)))), zipFact_);
        assertEq(1, zippedFiles_.getZipFiles().size());
        assertEq("-", zippedFiles_.getZipFiles().getVal("_"));
        assertSame(OutputType.ZIP,zippedFiles_.getType());
    }
    @Test
    public void zip2() {
        MockZipFact zipFact_ = new MockZipFact();
        ReadBinFiles zippedFiles_ = StreamZipFile.getZippedBinFiles(zipFact_.zipBinFiles(MockZipFact.wrapText(wrap(new MockNameFile("__/", (byte[]) null,5), new MockNameFile("_", "-",6), new MockNameFile("0", wrapInts(-1),7)))), zipFact_);
        assertEq(2, zippedFiles_.getZipFiles().size());
        assertEq("-", StringUtil.decode(zippedFiles_.getZipFiles().getVal("_").getContent()));
        assertEq(6, zippedFiles_.getZipFiles().getVal("_").getLastModifTime());
        byte[] content_ = zippedFiles_.getZipFiles().getVal("0").getContent();
        assertEq(1, content_.length);
        assertEq(-1, content_[0]);
        assertEq(7, zippedFiles_.getZipFiles().getVal("0").getLastModifTime());
        assertSame(OutputType.ZIP,zippedFiles_.getType());
        assertEq(1, zippedFiles_.getZipFolders().size());
        assertEq("__", zippedFiles_.getZipFolders().getKey(0));
        assertEq(5, zippedFiles_.getZipFolders().getVal("__").getLastModifTime());
    }
    @Test
    public void zip3() {
        MockZipFact zipFact_ = new MockZipFact();
        ReadFiles zippedFiles_ = StreamZipFile.getZippedFiles(new DefaultUniformingString(), null, zipFact_);
        assertEq(0, zippedFiles_.getZipFiles().size());
        assertSame(OutputType.NOTHING,zippedFiles_.getType());
    }
    @Test
    public void zip4() {
        MockZipFact zipFact_ = new MockZipFact();
        ReadBinFiles zippedFiles_ = StreamZipFile.getZippedBinFiles(null, zipFact_);
        assertEq(0, zippedFiles_.getZipFiles().size());
        assertSame(OutputType.NOTHING,zippedFiles_.getType());
        assertEq(0, zippedFiles_.getZipFolders().size());
    }
    private static byte[] wrapInts(int... _files) {
        return NumberUtil.wrapByteArray(MockZipFact.wrapInts(_files));
    }
    private static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }
}
