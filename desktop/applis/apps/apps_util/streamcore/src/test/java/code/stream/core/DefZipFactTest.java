package code.stream.core;

import code.mock.MockNameFile;
import code.mock.MockZipFact;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public class DefZipFactTest extends EquallableStreamCoreUtil {
    @Test
    public void t1() {
        DefZipFact defZipFact_ = new DefZipFact(new MockZipFactory());
        StringMap<ContentTime> res_ = defZipFact_.zippedBinaryFiles(defZipFact_.zipBinFiles(MockZipFact.wrapText(wrap(new MockNameFile("1/",(byte[]) null,4),new MockNameFile("_", "___",5), new MockNameFile("0", "__",7)))));
        assertEq(3,res_.size());
        assertEq("",StringUtil.decode(res_.getVal("1/").getContent()));
        assertEq(4, res_.getVal("1/").getLastModifTime());
        assertEq("___", StringUtil.decode(res_.getVal("_").getContent()));
        assertEq(5, res_.getVal("_").getLastModifTime());
        assertEq("__", StringUtil.decode(res_.getVal("0").getContent()));
        assertEq(7, res_.getVal("0").getLastModifTime());
    }
    @Test
    public void t2() {
        DefZipFact defZipFact_ = new DefZipFact(new MockZipFactory());
        StringMap<ContentTime> res_ = defZipFact_.zippedBinaryFiles(defZipFact_.zipBinFiles(MockZipFact.wrapText(wrap(new MockNameFile("1",(byte[]) null,4),new MockNameFile("_", "___",5), new MockNameFile("0", "__",7)))));
        assertEq(3,res_.size());
        assertEq("",StringUtil.decode(res_.getVal("1/").getContent()));
        assertEq(4, res_.getVal("1/").getLastModifTime());
        assertEq("___", StringUtil.decode(res_.getVal("_").getContent()));
        assertEq(5, res_.getVal("_").getLastModifTime());
        assertEq("__", StringUtil.decode(res_.getVal("0").getContent()));
        assertEq(7, res_.getVal("0").getLastModifTime());
    }
    @Test
    public void t3() {
        DefZipFact defZipFact_ = new DefZipFact(new MockZipFactory());
        StringMap<ContentTime> res_ = defZipFact_.zippedBinaryFiles(defZipFact_.zipBinFiles(MockZipFact.wrapText(wrap(new MockNameFile("1/",new byte[0],4),new MockNameFile("_", "___",5), new MockNameFile("0", "__",7)))));
        assertEq(3,res_.size());
        assertEq("",StringUtil.decode(res_.getVal("1/").getContent()));
        assertEq(4, res_.getVal("1/").getLastModifTime());
        assertEq("___", StringUtil.decode(res_.getVal("_").getContent()));
        assertEq(5, res_.getVal("_").getLastModifTime());
        assertEq("__", StringUtil.decode(res_.getVal("0").getContent()));
        assertEq(7, res_.getVal("0").getLastModifTime());
    }
    @Test
    public void t4() {
        AbstractZipFact defZipFact_ = new DefZipFact(new MockZipFactory());
        StringMap<ContentTime> res_ = defZipFact_.zippedBinaryFiles(null);
        assertNull(res_);
    }
    private static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }
}
