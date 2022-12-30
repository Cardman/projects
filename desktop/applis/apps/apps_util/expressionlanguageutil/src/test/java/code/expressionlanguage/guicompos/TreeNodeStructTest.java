package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.*;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.*;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.stds.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.events.*;
import code.gui.events.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class TreeNodeStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode0(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        n_.randCode();
    }
    @Test
    public void init2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetParentNode(),null,ctx_,n_,null,st_));
    }
    @Test
    public void child1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_));
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_));
    }
    @Test
    public void child2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertFalse(n2_.sameReference(n_));
        assertFalse(n2_.sameReference(NullStruct.NULL_VALUE));
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void child3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
        assertTrue(n_.sameReference(call(new FctTreeNodeGetParentNode(),null,ctx_,n2_,null,st_)));
        assertEq(1,toLong(call(new FctTreeNodeNb(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void child4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeInsert(),null,ctx_,n_,two(new IntStruct(0),NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_));
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_));
    }
    @Test
    public void child5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertFalse(n2_.sameReference(n_));
        assertFalse(n2_.sameReference(NullStruct.NULL_VALUE));
        call(new FctTreeNodeInsert(),null,ctx_,n_,two(new IntStruct(0),n2_),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void child6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeInsert(),null,ctx_,n_,two(new IntStruct(0),n2_),st_);
        call(new FctTreeNodeInsert(),null,ctx_,n_,two(new IntStruct(0),n2_),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
        assertTrue(n_.sameReference(call(new FctTreeNodeGetParentNode(),null,ctx_,n2_,null,st_)));
        assertEq(1,toLong(call(new FctTreeNodeNb(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void child7() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertFalse(n2_.sameReference(n_));
        assertFalse(n2_.sameReference(NullStruct.NULL_VALUE));
        call(new FctTreeNodeInsert(),null,ctx_,n_,two(new IntStruct(-1),n2_),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_));
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_));
    }
    @Test
    public void child8() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertFalse(n2_.sameReference(n_));
        assertFalse(n2_.sameReference(NullStruct.NULL_VALUE));
        call(new FctTreeNodeInsert(),null,ctx_,n_,two(new IntStruct(1),n2_),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_));
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_));
    }
    @Test
    public void child9() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n2_,one(n_),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
        assertTrue(n_.sameReference(call(new FctTreeNodeGetParentNode(),null,ctx_,n2_,null,st_)));
        assertEq(1,toLong(call(new FctTreeNodeNb(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void child10() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeInsert(),null,ctx_,n_,two(new IntStruct(0),n2_),st_);
        call(new FctTreeNodeInsert(),null,ctx_,n2_,two(new IntStruct(0),n_),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
        assertTrue(n_.sameReference(call(new FctTreeNodeGetParentNode(),null,ctx_,n2_,null,st_)));
        assertEq(1,toLong(call(new FctTreeNodeNb(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void next1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetNextSibling(),null,ctx_,n2_,null,st_));
    }
    @Test
    public void next2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n3_),st_);
        assertTrue(n3_.sameReference(call(new FctTreeNodeGetNextSibling(),null,ctx_,n2_,null,st_)));
    }
    @Test
    public void previous1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetPreviousSibling(),null,ctx_,n2_,null,st_));
    }
    @Test
    public void previous2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n3_),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetPreviousSibling(),null,ctx_,n3_,null,st_)));
    }
    @Test
    public void isAncestorMethod1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertFalse(call(new FctTreeNodeIsAncestor(),null,ctx_,n_,one(NullStruct.NULL_VALUE),st_));
    }
    @Test
    public void isAncestorMethod2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertFalse(call(new FctTreeNodeIsAncestor(),null,ctx_,n2_,one(n_),st_));
    }
    @Test
    public void isAncestorMethod3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n3_,one(n_),st_);
        assertFalse(call(new FctTreeNodeIsAncestor(),null,ctx_,n2_,one(n_),st_));
    }
    @Test
    public void isAncestorMethod4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n3_,one(n_),st_);
        assertFalse(call(new FctTreeNodeIsAncestor(),null,ctx_,n2_,one(n3_),st_));
    }
    @Test
    public void isAncestorMethod5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n3_,one(n_),st_);
        assertTrue(call(new FctTreeNodeIsAncestor(),null,ctx_,n_,one(n3_),st_));
    }
    @Test
    public void isAncestorMethod6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertTrue(call(new FctTreeNodeIsAncestor(),null,ctx_,n_,one(n_),st_));
    }
    @Test
    public void isDescendantMethod1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertFalse(call(new FctTreeNodeIsDescendant(),null,ctx_,n_,one(NullStruct.NULL_VALUE),st_));
    }
    @Test
    public void isDescendantMethod2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertFalse(call(new FctTreeNodeIsDescendant(),null,ctx_,n_,one(n2_),st_));
    }
    @Test
    public void isDescendantMethod3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n3_,one(n_),st_);
        assertFalse(call(new FctTreeNodeIsDescendant(),null,ctx_,n_,one(n2_),st_));
    }
    @Test
    public void isDescendantMethod4() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n3_,one(n_),st_);
        assertFalse(call(new FctTreeNodeIsDescendant(),null,ctx_,n3_,one(n2_),st_));
    }
    @Test
    public void isDescendantMethod5() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n3_,one(n_),st_);
        assertTrue(call(new FctTreeNodeIsDescendant(),null,ctx_,n3_,one(n_),st_));
    }
    @Test
    public void isDescendantMethod6() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        assertTrue(call(new FctTreeNodeIsDescendant(),null,ctx_,n_,one(n_),st_));
    }
    @Test
    public void removeNode1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n4_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n3_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n4_),st_);
        call(new FctTreeNodeRemove1(),null,ctx_,n_,one(NullStruct.NULL_VALUE),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n3_.sameReference(call(new FctTreeNodeGetNextSibling(),null,ctx_,n2_,null,st_)));
        assertTrue(n3_.sameReference(call(new FctTreeNodeGetPreviousSibling(),null,ctx_,n4_,null,st_)));
        assertTrue(n4_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void removeNode2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n4_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n3_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n4_),st_);
        call(new FctTreeNodeRemove1(),null,ctx_,n_,one(n_),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n3_.sameReference(call(new FctTreeNodeGetNextSibling(),null,ctx_,n2_,null,st_)));
        assertTrue(n3_.sameReference(call(new FctTreeNodeGetPreviousSibling(),null,ctx_,n4_,null,st_)));
        assertTrue(n4_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void removeNode3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n4_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n3_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n4_),st_);
        call(new FctTreeNodeRemove1(),null,ctx_,n_,one(n3_),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n4_.sameReference(call(new FctTreeNodeGetNextSibling(),null,ctx_,n2_,null,st_)));
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetPreviousSibling(),null,ctx_,n4_,null,st_)));
        assertTrue(n4_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void remove1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n4_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n3_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n4_),st_);
        call(new FctTreeNodeRemove0(),null,ctx_,n_,one(new IntStruct(-1)),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n3_.sameReference(call(new FctTreeNodeGetNextSibling(),null,ctx_,n2_,null,st_)));
        assertTrue(n3_.sameReference(call(new FctTreeNodeGetPreviousSibling(),null,ctx_,n4_,null,st_)));
        assertTrue(n4_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void remove2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n4_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n3_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n4_),st_);
        call(new FctTreeNodeRemove0(),null,ctx_,n_,one(new IntStruct(3)),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n3_.sameReference(call(new FctTreeNodeGetNextSibling(),null,ctx_,n2_,null,st_)));
        assertTrue(n3_.sameReference(call(new FctTreeNodeGetPreviousSibling(),null,ctx_,n4_,null,st_)));
        assertTrue(n4_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void remove3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n4_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n3_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n4_),st_);
        call(new FctTreeNodeRemove0(),null,ctx_,n_,one(new IntStruct(1)),st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n4_.sameReference(call(new FctTreeNodeGetNextSibling(),null,ctx_,n2_,null,st_)));
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetPreviousSibling(),null,ctx_,n4_,null,st_)));
        assertTrue(n4_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void removeFromParent1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeRemoveFromParent(),null,ctx_,n_,null,st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetParentNode(),null,ctx_,n_,null,st_));
    }
    @Test
    public void removeFromParent2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n4_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n3_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n4_),st_);
        call(new FctTreeNodeRemoveFromParent(),null,ctx_,n3_,null,st_);
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(n4_.sameReference(call(new FctTreeNodeGetNextSibling(),null,ctx_,n2_,null,st_)));
        assertTrue(n2_.sameReference(call(new FctTreeNodeGetPreviousSibling(),null,ctx_,n4_,null,st_)));
        assertTrue(n4_.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
        assertSame(NullStruct.NULL_VALUE,call(new FctTreeNodeGetParentNode(),null,ctx_,n3_,null,st_));
    }
    @Test
    public void removeAll() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n4_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n3_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n_,one(n4_),st_);
        call(new FctTreeNodeRemoveAllChildren(),null,ctx_,n_,null,st_);
        assertTrue(NullStruct.NULL_VALUE.sameReference(call(new FctTreeNodeGetFirstChild(),null,ctx_,n_,null,st_)));
        assertTrue(NullStruct.NULL_VALUE.sameReference(call(new FctTreeNodeGetLastChild(),null,ctx_,n_,null,st_)));
    }
    @Test
    public void eq() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n1_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n2_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n3_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n4_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n1_,one(n2_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n1_,one(n3_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n1_,one(n4_),st_);
        Struct n5_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n6_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n7_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        Struct n8_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeAdd(),null,ctx_,n5_,one(n6_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n5_,one(n7_),st_);
        call(new FctTreeNodeAdd(),null,ctx_,n5_,one(n8_),st_);
        assertTrue(call(new FctTreeNodeEq(),null,ctx_,null,two(n1_,n5_),st_));
        assertTrue(call(new FctTreeNodeEq(),null,ctx_,null,two(n2_,n6_),st_));
        assertTrue(call(new FctTreeNodeEq(),null,ctx_,null,two(n3_,n7_),st_));
        assertTrue(call(new FctTreeNodeEq(),null,ctx_,null,two(n4_,n8_),st_));
        assertTrue(call(new FctTreeNodeEq(),null,ctx_,null,two(NullStruct.NULL_VALUE,NullStruct.NULL_VALUE),st_));
        assertFalse(call(new FctTreeNodeEq(),null,ctx_,null,two(n1_,NullStruct.NULL_VALUE),st_));
        assertFalse(call(new FctTreeNodeEq(),null,ctx_,null,two(NullStruct.NULL_VALUE,n6_),st_));
        assertFalse(call(new FctTreeNodeEq(),null,ctx_,null,two(n1_,n6_),st_));
        assertFalse(call(new FctTreeNodeEq(),null,ctx_,null,two(n2_,n5_),st_));
        assertFalse(call(new FctTreeNodeEq(),null,ctx_,null,two(n3_,n8_),st_));
        assertFalse(call(new FctTreeNodeEq(),null,ctx_,null,two(n4_,n7_),st_));
    }
    @Test
    public void userObject() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_,new CdmFactory(pr_,new MockInterceptor(),new MockAdvGraphicListGenerator(true),new AdvGraphicListGeneratorStruct()));
        Options opt_ = new Options();
        ContextEl ctx_ = stds_.newContext(opt_, getForwards(stds_, opt_));
        StackCall st_ = stack(ctx_);
        Struct n_ = call(new FctTreeNode1(stds_.getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, one(new StringStruct("")), st_);
        call(new FctTreeNodeSetUserObject(),null,ctx_,n_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctTreeNodeGetUserObject(),null,ctx_,n_,null,st_));
    }
}
