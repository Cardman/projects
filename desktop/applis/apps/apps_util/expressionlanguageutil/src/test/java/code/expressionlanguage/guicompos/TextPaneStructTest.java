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

public final class TextPaneStructTest extends EquallableElUtUtil {

    @Test
    public void init0() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        call(new FctTextPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void text() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTextCompoSetText(),null,ctx_,r_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctTextCompoGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void insert() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTextCompoSetText(),null,ctx_,r_,one(new StringStruct("_")),st_);
        call(new FctTextCompoInsert(),null,ctx_,r_,two(new StringStruct("previous"),new IntStruct(0)),st_);
        assertEq("previous_",call(new FctTextCompoGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void remove() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctTextCompoSetText(),null,ctx_,r_,one(new StringStruct("_hello_")),st_);
        call(new FctTextCompoRemove(),null,ctx_,r_,two(new IntStruct(1),new IntStruct(5)),st_);
        assertEq("__",call(new FctTextCompoGetText(),null,ctx_,r_,null,st_));
    }
    @Test
    public void editable1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctInputSetEditable(),null,ctx_,r_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctInputIsEditable(),null,ctx_,r_,null,st_));
    }
    @Test
    public void editable2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct r_ = call(new FctTextPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, null, st_);
        call(new FctInputSetEditable(),null,ctx_,r_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctInputIsEditable(),null,ctx_,r_,null,st_));
    }
    @Test
    public void charAttr1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct a_ = call(new FctAttrSet(stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        call(new FctAttrSetBack(),null,ctx_,a_,one(new IntStruct(1)),st_);
        call(new FctAttrSetFore(),null,ctx_,a_,one(new IntStruct(1)),st_);
        call(new FctAttrSetSize(),null,ctx_,a_,one(new IntStruct(1)),st_);
        call(new FctAttrSetFamily(),null,ctx_,a_,one(new StringStruct("")),st_);
        call(new FctAttrSetBold(),null,ctx_,a_,one(BooleanStruct.of(false)),st_);
        call(new FctAttrSetItalic(),null,ctx_,a_,one(BooleanStruct.of(false)),st_);
        call(new FctAttrSetUnderline(),null,ctx_,a_,one(BooleanStruct.of(false)),st_);
        a_.getClassName(ctx_);
        Struct t_ = call(new FctTextPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,null,st_);
        call(new FctTextPaneCharAttr(),null,ctx_,t_,four(new IntStruct(1),new IntStruct(1),a_,BooleanStruct.of(false)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void charAttr2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct a_ = call(new FctAttrSet(stds_.getGuiExecutingBlocks()),null,ctx_,null,null,st_);
        call(new FctAttrSetBack(),null,ctx_,a_,one(new IntStruct(1)),st_);
        call(new FctAttrSetFore(),null,ctx_,a_,one(new IntStruct(1)),st_);
        call(new FctAttrSetSize(),null,ctx_,a_,one(new IntStruct(1)),st_);
        call(new FctAttrSetFamily(),null,ctx_,a_,one(new StringStruct("")),st_);
        call(new FctAttrSetBold(),null,ctx_,a_,one(BooleanStruct.of(true)),st_);
        call(new FctAttrSetItalic(),null,ctx_,a_,one(BooleanStruct.of(true)),st_);
        call(new FctAttrSetUnderline(),null,ctx_,a_,one(BooleanStruct.of(true)),st_);
        a_.getClassName(ctx_);
        Struct t_ = call(new FctTextPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,null,st_);
        call(new FctTextPaneCharAttr(),null,ctx_,t_,four(new IntStruct(1),new IntStruct(1),a_,BooleanStruct.of(true)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void charAttr3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctTextPane(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""),null,ctx_,null,null,st_);
        call(new FctTextPaneCharAttr(),null,ctx_,t_,four(new IntStruct(1),new IntStruct(1),NullStruct.NULL_VALUE,BooleanStruct.of(true)),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
}
