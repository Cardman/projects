package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.structs.*;
import code.gui.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class CustButtonGroupStructTest extends EquallableElUtUtil {
    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctButtonGroup(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void add() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct a_ = call(new FctButtonGroup(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctButtonGroupAdd(),null,ctx_,a_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,((CustButtonGroupStruct)a_).getRadios().size());
        call(new FctButtonGroupAdd(),null,ctx_,a_,one(call(new FctRadio0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""),null,ctx_,null,null,st_)),st_);
        assertEq(1,((CustButtonGroupStruct)a_).getRadios().size());
    }
    @Test
    public void remove() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct a_ = call(new FctButtonGroup(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        call(new FctButtonGroupAdd(),null,ctx_,a_,one(NullStruct.NULL_VALUE),st_);
        Struct rad_ = call(new FctRadio0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctButtonGroupAdd(),null,ctx_,a_,one(rad_),st_);
        call(new FctButtonGroupRemove(),null,ctx_,a_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,((CustButtonGroupStruct)a_).getRadios().size());
        call(new FctButtonGroupRemove(),null,ctx_,a_,one(rad_),st_);
        assertEq(0,((CustButtonGroupStruct)a_).getRadios().size());
    }
    @Test
    public void buttons() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct a_ = call(new FctButtonGroup(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks()), null, ctx_, null, null, st_);
        Struct rad_ = call(new FctRadio0(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctButtonGroupAdd(),null,ctx_,a_,one(rad_),st_);
        ArrayStruct arr_ = (ArrayStruct) call(new FctButtonGroupGetButtons(), null, ctx_, a_, null, st_);
        assertEq(1, arr_.getLength());
        assertSame(rad_,arr_.get(0));
    }
}
