package code.expressionlanguage.utilcompo;

import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.stream.core.ContentTime;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FolderStructTest extends EquallableElIntUtil {
    @Test
    public void build1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(), StringUtil.wrapStringArray("/")));
        FolderStruct build_ = FolderStruct.build(params(),init(), init(), pr_.getThreadFactory());
        assertEq(10, build_.getLastDate());
        assertEq(0, build_.getFolders().size());
        assertEq(0, build_.getFiles().size());
    }
    @Test
    public void build2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(5,7,9), StringUtil.wrapStringArray("/")));
        StringMap<ContentTime> folders_ = with(pr_,init(),"/folder");
        assertEq(22, folders_.getVal("/folder").getLastModifTime());
        StringMap<ContentTime> files_ = with(pr_, init(),"/folder/file.txt","content");
        assertEq(31, files_.getVal("/folder/file.txt").getLastModifTime());
        FolderStruct build_ = FolderStruct.build(params(),folders_, files_, pr_.getThreadFactory());
        assertEq(36, build_.getLastDate());
        assertEq(0, build_.getFiles().size());
        assertEq(1, build_.getFolders().size());
        assertEq("",build_.getFolders().getKey(0));
        assertEq(22,build_.getFolders().getValue(0).getLastDate());
        assertEq(0,build_.getFolders().getValue(0).getFiles().size());
        assertEq(1,build_.getFolders().getValue(0).getFolders().size());
        assertEq("folder",build_.getFolders().getValue(0).getFolders().getKey(0));
        assertEq(22,build_.getFolders().getValue(0).getFolders().getValue(0).getLastDate());
        assertEq(0,build_.getFolders().getValue(0).getFolders().getValue(0).getFolders().size());
        assertEq(1,build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().size());
        assertEq("file.txt",build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().getKey(0));
        assertEq(31,build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().getValue(0).getLastDate());
        assertEq("content",StringUtil.decode(build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().getValue(0).getContent()));
    }
    @Test
    public void build3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(5,7,9,11), StringUtil.wrapStringArray("/")));
        StringMap<ContentTime> folders_ = with(pr_,init(),"/folder");
        assertEq(22, folders_.getVal("/folder").getLastModifTime());
        StringMap<ContentTime> files_ = with(pr_, with(pr_, init(),"/folder/file1.txt","content1"),"/folder/file2.txt","content2");
        assertEq(31, files_.getVal("/folder/file1.txt").getLastModifTime());
        assertEq(42, files_.getVal("/folder/file2.txt").getLastModifTime());
        FolderStruct build_ = FolderStruct.build(params(),folders_, files_, pr_.getThreadFactory());
        assertEq(47, build_.getLastDate());
        assertEq(0, build_.getFiles().size());
        assertEq(1, build_.getFolders().size());
        assertEq("",build_.getFolders().getKey(0));
        assertEq(22,build_.getFolders().getValue(0).getLastDate());
        assertEq(0,build_.getFolders().getValue(0).getFiles().size());
        assertEq(1,build_.getFolders().getValue(0).getFolders().size());
        assertEq("folder",build_.getFolders().getValue(0).getFolders().getKey(0));
        assertEq(22,build_.getFolders().getValue(0).getFolders().getValue(0).getLastDate());
        assertEq(0,build_.getFolders().getValue(0).getFolders().getValue(0).getFolders().size());
        assertEq(2,build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().size());
        assertEq("file1.txt",build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().getKey(0));
        assertEq(31,build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().getValue(0).getLastDate());
        assertEq("content1",StringUtil.decode(build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().getValue(0).getContent()));
        assertEq("file2.txt",build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().getKey(1));
        assertEq(42,build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().getValue(1).getLastDate());
        assertEq("content2",StringUtil.decode(build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().getValue(1).getContent()));
    }
    @Test
    public void build4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(5,7,9,11), StringUtil.wrapStringArray("/")));
        StringMap<ContentTime> folders_ = with(pr_,with(pr_,init(),"/folder"),"/folder/sub");
        assertEq(22, folders_.getVal("/folder").getLastModifTime());
        assertEq(31, folders_.getVal("/folder/sub").getLastModifTime());
        StringMap<ContentTime> files_ = init();
        FolderStruct build_ = FolderStruct.build(params(),folders_, files_, pr_.getThreadFactory());
        assertEq(42, build_.getLastDate());
        assertEq(0, build_.getFiles().size());
        assertEq(1, build_.getFolders().size());
        assertEq("",build_.getFolders().getKey(0));
        assertEq(22,build_.getFolders().getValue(0).getLastDate());
        assertEq(0,build_.getFolders().getValue(0).getFiles().size());
        assertEq(1,build_.getFolders().getValue(0).getFolders().size());
        assertEq("folder",build_.getFolders().getValue(0).getFolders().getKey(0));
        assertEq(22,build_.getFolders().getValue(0).getFolders().getValue(0).getLastDate());
        assertEq(1,build_.getFolders().getValue(0).getFolders().getValue(0).getFolders().size());
        assertEq(0,build_.getFolders().getValue(0).getFolders().getValue(0).getFiles().size());
        assertEq("sub",build_.getFolders().getValue(0).getFolders().getValue(0).getFolders().getKey(0));
        assertEq(31,build_.getFolders().getValue(0).getFolders().getValue(0).getFolders().getValue(0).getLastDate());
        assertEq(0,build_.getFolders().getValue(0).getFolders().getValue(0).getFolders().getValue(0).getFolders().size());
        assertEq(0,build_.getFolders().getValue(0).getFolders().getValue(0).getFolders().getValue(0).getFiles().size());
    }
    @Test
    public void exportAll1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(5,7,9,11), StringUtil.wrapStringArray("/")));
        StringMap<ContentTime> folders_ = with(pr_,init(),"/folder");
        assertEq(22, folders_.getVal("/folder").getLastModifTime());
        StringMap<ContentTime> files_ = with(pr_, with(pr_, init(),"/folder/file1.txt","content1"),"/folder/file2.txt","content2");
        assertEq(31, files_.getVal("/folder/file1.txt").getLastModifTime());
        assertEq(42, files_.getVal("/folder/file2.txt").getLastModifTime());
        FolderStruct build_ = FolderStruct.build(params(),folders_, files_, pr_.getThreadFactory());
        StringMap<ContentTime> res_ = build_.exportAll();
        assertEq(4,res_.size());
        assertTrue(StringUtil.contains(res_.getKeys(),""));
        assertTrue(StringUtil.contains(res_.getKeys(),"/folder"));
        assertTrue(StringUtil.contains(res_.getKeys(),"/folder/file1.txt"));
        assertTrue(StringUtil.contains(res_.getKeys(),"/folder/file2.txt"));
    }
    @Test
    public void exportAll2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(DefaultGenerator.oneEltArr()), new MockFileSet(10, lgs(5,7,9,11), StringUtil.wrapStringArray("/")));
        StringMap<ContentTime> folders_ = with(pr_,init(),"/e");
        StringMap<ContentTime> files_ = with(pr_, init(),"/e","content");
        FolderStruct build_ = FolderStruct.build(params(),folders_, files_, pr_.getThreadFactory());
        StringMap<ContentTime> res_ = build_.exportAll();
        assertEq(3,res_.size());
        assertTrue(StringUtil.contains(res_.getKeys(),""));
        assertTrue(StringUtil.contains(res_.getKeys(),"/de"));
        assertTrue(StringUtil.contains(res_.getKeys(),"/fe"));
    }
}
