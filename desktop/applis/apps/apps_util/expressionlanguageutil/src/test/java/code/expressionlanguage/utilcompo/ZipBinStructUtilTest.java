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
import code.util.core.*;
import org.junit.Test;

public final class ZipBinStructUtilTest extends EquallableElUtUtil {
    @Test
    public void zippedTextFilesToBin1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);

        assertFalse(call(new FctFileZipText(stds_.getExecContent().getCustAliases(),""),null,ctx_,null,two(new StringStruct(""),NullStruct.NULL_VALUE),st_));
        
    }
    @Test
    public void zippedTextFilesToBin2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        Struct e_ = call(new FctEntryText(), null, ctx_, null, two(new StringStruct("file.txt"), NullStruct.NULL_VALUE), st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        assertTrue(call(new FctFileZipText(stds_.getExecContent().getCustAliases(),""),null,ctx_,null,two(new StringStruct("zip"),ent_),st_));
        
    }
    @Test
    public void zippedTextFilesToBin3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        Struct e_ = call(new FctEntryText(), null, ctx_, null, two(new StringStruct("file.txt"), new StringStruct("content")), st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        assertTrue(call(new FctFileZipText(stds_.getExecContent().getCustAliases(),""),null,ctx_,null,two(new StringStruct("zip"),ent_),st_));
        
    }
    @Test
    public void zippedBinFilesToBin1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);

        assertFalse(call(new FctFileZipBin(stds_.getExecContent().getCustAliases()),null,ctx_,null,two(new StringStruct(""),NullStruct.NULL_VALUE),st_));
        
    }
    @Test
    public void zippedBinFilesToBin2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        Struct e_ = call(new FctEntryBinary(), null, ctx_, null, two(NullStruct.NULL_VALUE, NullStruct.NULL_VALUE), st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        assertTrue(call(new FctFileZipBin(stds_.getExecContent().getCustAliases()),null,ctx_,null,two(new StringStruct("zip"),ent_),st_));
        
    }
    @Test
    public void zippedBinFilesToBin3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        ArrayStruct bs_ = new ArrayStruct(7,"");
        bs_.set(0,new ByteStruct((byte) 'c'));
        bs_.set(1,new ByteStruct((byte) 'o'));
        bs_.set(2,new ByteStruct((byte) 'n'));
        bs_.set(3,new ByteStruct((byte) 't'));
        bs_.set(4,new ByteStruct((byte) 'e'));
        bs_.set(5,new ByteStruct((byte) 'n'));
        bs_.set(6,new ByteStruct((byte) 't'));
        Struct e_ = call(new FctEntryBinary(), null, ctx_, null, two(new StringStruct("file.txt"), bs_), st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        assertTrue(call(new FctFileZipBin(stds_.getExecContent().getCustAliases()),null,ctx_,null,two(new StringStruct("zip"),ent_),st_));
        
    }
    @Test
    public void zippedBinFilesToArray1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        ArrayStruct bs_ = new ArrayStruct(7,"");
        bs_.set(0,new ByteStruct((byte) 'c'));
        bs_.set(1,new ByteStruct((byte) 'o'));
        bs_.set(2,new ByteStruct((byte) 'n'));
        bs_.set(3,new ByteStruct((byte) 't'));
        bs_.set(4,new ByteStruct((byte) 'e'));
        bs_.set(5,new ByteStruct((byte) 'n'));
        bs_.set(6,new ByteStruct((byte) 't'));
        Struct e_ = call(new FctEntryBinary(), null, ctx_, null, two(new StringStruct("file.txt"), bs_), st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        Struct out_ = call(new FctFileZipBinArray(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(ent_), st_);
        assertEq(27,((ArrayStruct)out_).getLength());
    }
    @Test
    public void zippedBinFilesToArray2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        ArrayStruct bs_ = new ArrayStruct(7,"");
        bs_.set(0,new ByteStruct((byte) 'c'));
        bs_.set(1,new ByteStruct((byte) 'o'));
        bs_.set(2,new ByteStruct((byte) 'n'));
        bs_.set(3,new ByteStruct((byte) 't'));
        bs_.set(4,new ByteStruct((byte) 'e'));
        bs_.set(5,new ByteStruct((byte) 'n'));
        bs_.set(6,new ByteStruct((byte) 't'));
        Struct e_ = call(new FctEntryBinary(), null, ctx_, null, two(new StringStruct("file.txt"), bs_), st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        Struct out_ = call(new FctFileZipBinArray(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        assertEq(8,((ArrayStruct)out_).getLength());
    }
    @Test
    public void unzippedTextFiles1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);

        assertSame(NullStruct.NULL_VALUE,call(new FctFileZippedText(stds_.getExecContent().getCustAliases(),""),null,ctx_,null,one(new StringStruct("")),st_));
        
    }
    @Test
    public void unzippedTextFiles2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        Struct e_ = call(new FctEntryText(), null, ctx_, null, two(new StringStruct("file.txt"), NullStruct.NULL_VALUE), st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        assertTrue(call(new FctFileZipText(stds_.getExecContent().getCustAliases(),""),null,ctx_,null,two(new StringStruct("zip"),ent_),st_));


        Struct zip_ = call(new FctFileZippedText(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(new StringStruct("zip")), st_);
        assertEq(1, ((ArrayStruct)zip_).getLength());
        Struct elt_ = ((ArrayStruct) zip_).get(0);
        assertEq("file.txt",call(new FctEntryTextName(),null,ctx_,elt_,null,st_));
        assertEq("",call(new FctEntryTextValue(),null,ctx_,elt_,null,st_));
        
    }
    @Test
    public void unzippedTextFiles3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        Struct e_ = call(new FctEntryText(), null, ctx_, null, two(new StringStruct("file.txt"), new StringStruct("content")), st_);
        call(new FctEntryTextTime1(), null,ctx_,e_,one(new IntStruct(8)),st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        assertTrue(call(new FctFileZipText(stds_.getExecContent().getCustAliases(),""),null,ctx_,null,two(new StringStruct("zip"),ent_),st_));


        Struct zip_ = call(new FctFileZippedText(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(new StringStruct("zip")), st_);
        assertEq(1, ((ArrayStruct)zip_).getLength());
        Struct elt_ = ((ArrayStruct) zip_).get(0);
        assertEq("file.txt",call(new FctEntryTextName(),null,ctx_,elt_,null,st_));
        assertEq("content",call(new FctEntryTextValue(),null,ctx_,elt_,null,st_));
        assertEq(8,toLong(call(new FctEntryTextTime0(),null,ctx_,elt_,null,st_)));
        
    }
    @Test
    public void unzippedTextFiles4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);


        ArrayStruct bs_ = new ArrayStruct(1,"");
        bs_.set(0,new ByteStruct((byte) -1));
        Struct e_ = call(new FctEntryBinary(), null, ctx_, null, two(new StringStruct("file.txt"), bs_), st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        assertTrue(call(new FctFileZipBin(stds_.getExecContent().getCustAliases()),null,ctx_,null,two(new StringStruct("zip"),ent_),st_));









        Struct zip_ = call(new FctFileZippedText(stds_.getExecContent().getCustAliases(), ""), null, ctx_, null, one(new StringStruct("zip")), st_);
        assertEq(1, ((ArrayStruct)zip_).getLength());
        Struct elt_ = ((ArrayStruct) zip_).get(0);
        assertEq("file.txt",call(new FctEntryTextName(),null,ctx_,elt_,null,st_));
        assertEq("",call(new FctEntryTextValue(),null,ctx_,elt_,null,st_));
        
    }
    @Test
    public void unzippedBinFiles1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);

        assertSame(NullStruct.NULL_VALUE,call(new FctFileZippedBin(stds_.getExecContent().getCustAliases()),null,ctx_,null,one(new StringStruct("")),st_));
        
    }
    @Test
    public void unzippedBinFiles2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        Struct e_ = call(new FctEntryText(), null, ctx_, null, two(new StringStruct("file.txt"), NullStruct.NULL_VALUE), st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        assertTrue(call(new FctFileZipText(stds_.getExecContent().getCustAliases(), ""),null,ctx_,null,two(new StringStruct("zip"),ent_),st_));


        Struct zip_ = call(new FctFileZippedBin(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(new StringStruct("zip")), st_);
        assertEq(1, ((ArrayStruct)zip_).getLength());
        Struct elt_ = ((ArrayStruct) zip_).get(0);
        assertEq("file.txt",call(new FctEntryBinaryName(),null,ctx_,elt_,null,st_));
        Struct arr_ = call(new FctEntryBinaryValue(), null, ctx_, elt_, null, st_);
        assertEq(0, ((ArrayStruct)arr_).getLength());
        
    }
    @Test
    public void unzippedBinFiles3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        Struct e_ = call(new FctEntryText(), null, ctx_, null, two(new StringStruct("file.txt"), new StringStruct("content")), st_);
        call(new FctEntryTextTime1(), null,ctx_,e_,one(new IntStruct(8)),st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        assertTrue(call(new FctFileZipText(stds_.getExecContent().getCustAliases(),""),null,ctx_,null,two(new StringStruct("zip"),ent_),st_));


        Struct zip_ = call(new FctFileZippedBin(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(new StringStruct("zip")), st_);
        assertEq(1, ((ArrayStruct)zip_).getLength());
        Struct elt_ = ((ArrayStruct) zip_).get(0);
        assertEq("file.txt",call(new FctEntryBinaryName(),null,ctx_,elt_,null,st_));
        Struct arr_ = call(new FctEntryBinaryValue(), null, ctx_, elt_, null, st_);
        assertEq(7, ((ArrayStruct)arr_).getLength());
        assertEq(8,toLong(call(new FctEntryBinaryTime0(),null,ctx_,elt_,null,st_)));
        
    }
    
    @Test
    public void unzippedArrayFiles1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        ArrayStruct bs_ = new ArrayStruct(7,"");
        bs_.set(0,new ByteStruct((byte) 'c'));
        bs_.set(1,new ByteStruct((byte) 'o'));
        bs_.set(2,new ByteStruct((byte) 'n'));
        bs_.set(3,new ByteStruct((byte) 't'));
        bs_.set(4,new ByteStruct((byte) 'e'));
        bs_.set(5,new ByteStruct((byte) 'n'));
        bs_.set(6,new ByteStruct((byte) 't'));
        Struct e_ = call(new FctEntryBinary(), null, ctx_, null, two(new StringStruct("file.txt"), bs_), st_);
        call(new FctEntryBinaryTime1(), null,ctx_,e_,one(new IntStruct(8)),st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        Struct in_ = call(new FctFileZipBinArray(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(ent_), st_);
        Struct arr_ = call(new FctFileZippedBinArray(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(in_), st_);
        assertEq(1,((ArrayStruct)arr_).getLength());
        Struct elt_ = ((ArrayStruct) arr_).get(0);
        assertEq("file.txt",call(new FctEntryBinaryName(),null,ctx_,elt_,null,st_));
        Struct arrBytes_ = call(new FctEntryBinaryValue(), null, ctx_, elt_, null, st_);
        assertEq(7, ((ArrayStruct)arrBytes_).getLength());
        assertEq(8,toLong(call(new FctEntryBinaryTime0(),null,ctx_,elt_,null,st_)));
    }

    @Test
    public void unzippedArrayFiles2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        memoryFileSystem(stds_,pr_);
        ArrayStruct bs_ = new ArrayStruct(7,"");
        bs_.set(0,new ByteStruct((byte) 'c'));
        bs_.set(1,new ByteStruct((byte) 'o'));
        bs_.set(2,new ByteStruct((byte) 'n'));
        bs_.set(3,new ByteStruct((byte) 't'));
        bs_.set(4,new ByteStruct((byte) 'e'));
        bs_.set(5,new ByteStruct((byte) 'n'));
        bs_.set(6,new ByteStruct((byte) 't'));
        Struct e_ = call(new FctEntryBinary(), null, ctx_, null, two(new StringStruct("file.txt"), bs_), st_);
        call(new FctEntryBinaryTime1(), null,ctx_,e_,one(new IntStruct(8)),st_);
        ArrayStruct ent_ = new ArrayStruct(2,"");
        ent_.set(0,NullStruct.NULL_VALUE);
        ent_.set(1,e_);

        call(new FctFileZipBinArray(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(ent_), st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctFileZippedBinArray(stds_.getExecContent().getCustAliases()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_));
    }
}
