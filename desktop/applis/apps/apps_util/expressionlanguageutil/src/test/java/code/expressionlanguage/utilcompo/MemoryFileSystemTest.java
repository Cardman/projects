package code.expressionlanguage.utilcompo;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.Test;

public final class MemoryFileSystemTest extends EquallableElUtUtil {
    @Test
    public void absolute1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        memoryFileSystem(stds_,pr_);
        call(new FctFileIsAbsolute(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void absolute2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        call(new FctFileIsAbsolute(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void absolute3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        assertFalse(call(new FctFileIsAbsolute(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("folder")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void absolute4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        assertTrue(call(new FctFileIsAbsolute(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/folder")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void absolute5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        assertTrue(call(new FctFileIsAbsolute(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("\\folder")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void absolutePath1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertEq("/base/folder",call(new FctFileAbsolutePath(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("folder")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void absolutePath2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertEq("/base/folder",call(new FctFileAbsolutePath(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/base/folder")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isDirectory1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertFalse(call(new FctFileIsDirectory(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/folder/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isDirectory2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertTrue(call(new FctFileIsDirectory(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("folder/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isDirectory3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertTrue(call(new FctFileIsDirectory(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/base")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isDirectory4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertTrue(call(new FctFileIsDirectory(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/base/folder/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isDirectory5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertFalse(call(new FctFileIsDirectory(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/fake/folder/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isDirectory6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertFalse(call(new FctFileIsDirectory(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/base/fake/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isDirectory7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertFalse(call(new FctFileIsDirectory(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/base/fake/sec/th/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isDirectory8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertFalse(call(new FctFileIsDirectory(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/base/fake/sec/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isDirectory9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertTrue(call(new FctFileIsDirectory(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isDirectory10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertTrue(call(new FctFileIsDirectory(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/base/folder\\")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isFile1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertFalse(call(new FctFileIsFile(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/folder/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isFile2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/file", StringUtil.encode(""),5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertTrue(call(new FctFileIsFile(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("file")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isFile3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/file", StringUtil.encode(""),5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertFalse(call(new FctFileIsFile(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("fake")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isFile4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/file", StringUtil.encode(""),5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertFalse(call(new FctFileIsFile(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/fake/file")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void isFile5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/file", StringUtil.encode(""),5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertFalse(call(new FctFileIsFile(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void parentPath1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/file", StringUtil.encode(""),5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertEq("/base/",call(new FctFileGetParentPath(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("file")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void parentPath2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder", (byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertEq("/base/",call(new FctFileGetParentPath(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("folder")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void parentPath3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder", (byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertEq("/",call(new FctFileGetParentPath(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/base")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void getName1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/file", StringUtil.encode(""),5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertEq("file",call(new FctFileGetName(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("file")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void getName2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder", (byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertEq("folder",call(new FctFileGetName(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("folder")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void parentPath4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("base/folder", (byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertEq("",call(new FctFileGetParentPath(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/")),st_));
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void currentDir1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(NullStruct.NULL_VALUE,InitPhase.READ_ONLY_OTHERS);
        memoryFileSystem(stds_,pr_);
        call(new FctFileDir0(stds_.getCustAliases()),null,ctx_,null,null,st_);
        assertTrue(st_.isFailInit());
    }
    @Test
    public void currentDir2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        assertEq("/base/",call(new FctFileDir0(stds_.getCustAliases()),null,ctx_,null,null,st_));
    }
    @Test
    public void currentDir3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        call(new FctFileDir1(stds_.getCustAliases()),null,ctx_,null,one(NullStruct.NULL_VALUE),st_);
        assertFalse(st_.isFailInit());
        assertFalse(st_.calls());
    }
    @Test
    public void currentDir4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        call(new FctFileDir1(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/fake/folder")),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq("/base/",((RunnableContextEl)ctx_).getCurrentDir());
    }
    @Test
    public void currentDir5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(0, new long[1], new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_,new MockNameFile("base",(byte[]) null,5),new MockNameFile("folder",(byte[]) null,5));
        ((RunnableContextEl)ctx_).setCurrentDir("/base/");
        call(new FctFileDir1(stds_.getCustAliases()),null,ctx_,null,one(new StringStruct("/folder")),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertEq("/folder/",((RunnableContextEl)ctx_).getCurrentDir());
    }
}
