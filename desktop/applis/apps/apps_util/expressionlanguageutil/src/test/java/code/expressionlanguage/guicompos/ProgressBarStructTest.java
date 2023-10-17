package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.*;
import code.gui.EquallableElUtUtil;
import code.maths.montecarlo.CustomSeedGene;
import code.mock.MockFileSet;
import code.mock.MockProgramInfos;
import code.util.StringList;
import org.junit.Test;

public final class ProgressBarStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctProgBar(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void min() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctProgBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctProgBarMin1(),null,ctx_,r_,one(new IntStruct(1)),st_);
        assertEq(1,toLong(call(new FctProgBarMin0(),null,ctx_,r_,null,st_)));
    }
    @Test
    public void max() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctProgBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctProgBarMax1(),null,ctx_,r_,one(new IntStruct(1)),st_);
        assertEq(1,toLong(call(new FctProgBarMax0(),null,ctx_,r_,null,st_)));
    }
    @Test
    public void value() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctProgBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctProgBarValue1(),null,ctx_,r_,one(new IntStruct(1)),st_);
        assertEq(1,toLong(call(new FctProgBarValue0(),null,ctx_,r_,null,st_)));
    }
    @Test
    public void mult1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctProgBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctProgBarHoriz1(),null,ctx_,r_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctProgBarHoriz0(),null,ctx_,r_,null,st_));
    }
    @Test
    public void mult2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctProgBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctProgBarHoriz1(),null,ctx_,r_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctProgBarHoriz0(),null,ctx_,r_,null,st_));
    }
    @Test
    public void visible1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctProgBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctCompoSetVisible(),null,ctx_,r_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctCompoIsVisible(),null,ctx_,r_,null,st_));
    }
    @Test
    public void visible2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctProgBar(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctCompoSetVisible(),null,ctx_,r_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctCompoIsVisible(),null,ctx_,r_,null,st_));
    }
}
