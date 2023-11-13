package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class MenusTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctMenuItem0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctMenuItem1(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctMenuItemCheck0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctMenuItemCheck0(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct m_ = call(new FctMenu0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctMenuAddSeparator(pr_.getCompoFactory(),""),null,ctx_,m_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctMenu1(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,one(new StringStruct("")),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void init7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctMenuBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void text1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctButtonAddAction(),null,ctx_,r_,one(NullStruct.NULL_VALUE),st_);
        call(new FctButtonAddAction(),null,ctx_,r_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctAbsMenuSetText(),null,ctx_,r_,one(NullStruct.NULL_VALUE),st_);
        call(new FctAbsMenuGetText(),null,ctx_,r_,null,st_);
        call(new FctAbsMenuSetText(),null,ctx_,r_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctAbsMenuGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void text2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctMenuItemCheck0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctButtonAddAction(),null,ctx_,r_,one(NullStruct.NULL_VALUE),st_);
        call(new FctButtonAddAction(),null,ctx_,r_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctAbsMenuSetText(),null,ctx_,r_,one(NullStruct.NULL_VALUE),st_);
        call(new FctAbsMenuGetText(),null,ctx_,r_,null,st_);
        call(new FctAbsMenuSetText(),null,ctx_,r_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctAbsMenuGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void enabled1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetDeepEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetDeepEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItemCheck1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItemCheck1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void enabled8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctAbsMenuSetEnabled(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctAbsMenuIsEnabled(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void selected1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItemCheck1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuItemCheckSetSelected(),null,ctx_,ls_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctMenuItemCheckIsSelected(),null,ctx_,ls_,null,st_));
    }
    @Test
    public void selected2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenuItemCheck1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuItemCheckSetSelected(),null,ctx_,ls_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctMenuItemCheckIsSelected(),null,ctx_,ls_,null,st_));
    }

    @Test
    public void count1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,ls_,null,st_)));
    }

    @Test
    public void count2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,ls_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,ls_,null,st_)));
        assertSame(NullStruct.NULL_VALUE,call(new FctMenuGetMenu(),null,ctx_,ls_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
        assertSame(menuItem_,call(new FctMenuGetMenu(),null,ctx_,menu_,one(new IntStruct(0)),st_));
        assertSame(menu_,call(new FctAbsMenuGetParentMenu(),null,ctx_,menuItem_,null,st_));
    }

    @Test
    public void count4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItemCheck1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(menuItem2_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItem1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenuItemCheck1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menuItem_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(menuItem_),st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
        assertSame(NullStruct.NULL_VALUE,call(new FctMenuBarGet(),null,ctx_,bar_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count11() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        assertEq(1,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
        assertSame(menu_,call(new FctMenuBarGet(),null,ctx_,bar_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count12() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        assertEq(1,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
        assertSame(menu_,call(new FctMenuBarGet(),null,ctx_,bar_,one(new IntStruct(0)),st_));
    }

    @Test
    public void count13() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        call(new FctMenuBarRemove(),null,ctx_,bar_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
    }

    @Test
    public void count14() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menuItem2_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menuItem_),st_);
        call(new FctMenuBarRemove(),null,ctx_,bar_,one(menuItem2_),st_);
        assertEq(1,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
    }

    @Test
    public void count15() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        call(new FctMenuBarRemove(),null,ctx_,bar_,one(menu_),st_);
        assertEq(0,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
    }

    @Test
    public void count16() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu2_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menu2_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count17() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu2_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menu2_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(menu2_),st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count18() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu2_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu_),st_);
        call(new FctMenuBarAdd(),null,ctx_,bar_,one(menu2_),st_);
        assertEq(2,toLong(call(new FctMenuBarNb(),null,ctx_,bar_,null,st_)));
    }

    @Test
    public void count19() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu2_ = call(new FctSeparator(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menu2_),st_);
        assertEq(1,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count20() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct menu_ = call(new FctMenu1(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct menu2_ = call(new FctSeparator(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctMenuAdd(),null,ctx_,menu_,one(menu2_),st_);
        call(new FctMenuRemove(),null,ctx_,menu_,one(menu2_),st_);
        assertEq(0,toLong(call(new FctMenuNb(),null,ctx_,menu_,null,st_)));
    }

    @Test
    public void count21() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct bar_ = call(new FctMenuBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctCompoGetWidth(),null,ctx_,bar_,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void or1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new FctSeparator(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, null, st_);
        call(new FctSeparatorSep1(),null,ctx_,ls_,one(new IntStruct(1)),st_);
        assertEq(1,((NumberStruct)call(new FctSeparatorSep0(),null,ctx_,ls_,null,st_)).intStruct());
    }
    @Test
    public void or2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct ls_ = call(new DfSeparator(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, st_);
        call(new FctSeparatorSep1(),null,ctx_,ls_,one(new IntStruct(1)),st_);
        assertEq(1,((NumberStruct)call(new FctSeparatorSep0(),null,ctx_,ls_,null,st_)).intStruct());
    }
}
