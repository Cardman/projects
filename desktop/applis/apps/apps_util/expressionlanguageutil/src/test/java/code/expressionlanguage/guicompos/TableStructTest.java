package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.instr.ParsedArgument;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.*;
import code.expressionlanguage.guicompos.stds.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.gui.*;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class TableStructTest extends EquallableElUtUtil {

    @Test
    public void init1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(NullStruct.NULL_VALUE), st_);
        call(new FctTableAddHeader(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        call(new FctTableAddHeader(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
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
        ArrayStruct arr_ = new ArrayStruct(1,"");
        arr_.set(0,new StringStruct(""));
        call(new FctTableGrid(stds_.getExecContent().getCustAliases(),stds_.getGuiExecutingBlocks(),""),null,ctx_,null,one(arr_),st_);
        assertFalse(st_.isFailInit());
        assertTrue(st_.calls());
    }
    @Test
    public void addAction1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(arr()), st_);
        call(new FctTableAddSelect(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        assertEq(0,((TableStruct)t_).getTable().getListSelectionListeners().size());
    }
    @Test
    public void addAction2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(arr()), st_);
        call(new FctTableAddSelect(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((TableStruct)t_).getTable().getListSelectionListeners().size());
    }
    @Test
    public void addAction3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stackLogger(ctx_);
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(arr()), st_);
        call(new FctTableAddSelect(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        assertEq(1,((TableStruct)t_).getTable().getListSelectionListeners().size());
    }
    @Test
    public void removeAction1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(arr()), st_);
        call(new FctTableAddSelect(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
        call(new FctTableRemSelect(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        assertEq(1,((TableStruct)t_).getTable().getListSelectionListeners().size());
    }
    @Test
    public void removeAction2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(arr()), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctTableAddSelect(),null,ctx_,t_,one(list_),st_);
        call(new FctTableRemSelect(),null,ctx_,t_,one(list_),st_);
        assertEq(0,((TableStruct)t_).getTable().getListSelectionListeners().size());
    }
    @Test
    public void removeAction3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stackLogger(ctx_);
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(arr()), st_);
        Struct list_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctTableAddSelect(),null,ctx_,t_,one(list_),st_);
        call(new FctTableRemSelect(),null,ctx_,t_,one(list_),st_);
        assertEq(0,((TableStruct)t_).getTable().getListSelectionListeners().size());
    }
    @Test
    public void actions() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(),""), null, ctx_, null, one(arr()), st_);
        Struct li_ = ctx_.getInit().processInit(ctx_, NullStruct.NULL_VALUE, new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()), AccessEnum.PUBLIC, new ExecClassContent(new AnaClassContent(true, false, true))), ""), "", -1);
        call(new FctTableAddSelect(),null,ctx_,t_,one(li_),st_);
        ((TableStruct)t_).getTable().addListSelectionListener(new MockListSelectionListener());
        ArrayStruct a_ = (ArrayStruct) call(new FctTableGetSelects(), null, ctx_, t_, null, st_);
        assertEq(1,a_.getLength());
        assertSame(li_,a_.get(0));
    }
    @Test
    public void columnName() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(1,"");
        arr_.set(0,new StringStruct(""));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        ArrayStruct arr2_ = new ArrayStruct(1,"");
        arr2_.set(0,new StringStruct("_"));
        call(new FctTableSetColumns(),null,ctx_,t_,one(arr2_),st_);
        assertEq("_",call(new FctTableGetColumnName(),null,ctx_,t_,one(new IntStruct(0)),st_));
    }
    @Test
    public void rowColCount() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetRowCount(""),null,ctx_,t_,one(new IntStruct(2)),st_);
        assertEq(2,toLong(call(new FctTableGetRowCount(),null,ctx_,t_,null,st_)));
        assertEq(4,toLong(call(new FctTableGetColumnCount(),null,ctx_,t_,null,st_)));
    }
    @Test
    public void interval1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetRowCount(""),null,ctx_,t_,one(new IntStruct(2)),st_);
        assertEq(0,toLong(call(new FctTableGetSelectedRowCount(),null,ctx_,t_,null,st_)));
        assertEq(-1,toLong(call(new FctTableGetSelectedRow(),null,ctx_,t_,null,st_)));
        assertEq(0,((ArrayStruct)call(new FctTableGetSelectedRows(),null,ctx_,t_,null,st_)).getLength());
    }
    @Test
    public void interval2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetRowCount(""),null,ctx_,t_,one(new IntStruct(8)),st_);
        call(new FctTableAddInterval(""),null,ctx_,t_,two(new IntStruct(2),new IntStruct(4)),st_);
        assertEq(3,toLong(call(new FctTableGetSelectedRowCount(),null,ctx_,t_,null,st_)));
        assertEq(3,((ArrayStruct)call(new FctTableGetSelectedRows(),null,ctx_,t_,null,st_)).getLength());
    }
    @Test
    public void interval3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetRowCount(""),null,ctx_,t_,one(new IntStruct(8)),st_);
        call(new FctTableAddInterval(""),null,ctx_,t_,two(new IntStruct(-1),new IntStruct(-1)),st_);
        call(new FctTableAddInterval(""),null,ctx_,t_,two(new IntStruct(2),new IntStruct(4)),st_);
        call(new FctTableRemoveInterval(""),null,ctx_,t_,two(new IntStruct(-1),new IntStruct(-1)),st_);
        call(new FctTableRemoveInterval(""),null,ctx_,t_,two(new IntStruct(2),new IntStruct(4)),st_);
        assertEq(0,toLong(call(new FctTableGetSelectedRowCount(),null,ctx_,t_,null,st_)));
        assertEq(0,((ArrayStruct)call(new FctTableGetSelectedRows(),null,ctx_,t_,null,st_)).getLength());
    }
    @Test
    public void value() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetRowCount(""),null,ctx_,t_,one(new IntStruct(8)),st_);
        call(new FctTableSetValue(),null,ctx_,t_,three(new StringStruct("_"),new IntStruct(4),new IntStruct(1)),st_);
        assertEq("_",call(new FctTableGetValue(),null,ctx_,t_,two(new IntStruct(4),new IntStruct(1)),st_));
    }
    @Test
    public void moveColumn() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetRowCount(""),null,ctx_,t_,one(new IntStruct(8)),st_);
        call(new FctTableMoveColumn(),null,ctx_,t_,two(new IntStruct(1),new IntStruct(3)),st_);
        assertEq("0",call(new FctTableGetColumnName(),null,ctx_,t_,one(new IntStruct(0)),st_));
        assertEq("2",call(new FctTableGetColumnName(),null,ctx_,t_,one(new IntStruct(1)),st_));
        assertEq("3",call(new FctTableGetColumnName(),null,ctx_,t_,one(new IntStruct(2)),st_));
        assertEq("1",call(new FctTableGetColumnName(),null,ctx_,t_,one(new IntStruct(3)),st_));
    }
    @Test
    public void coords() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetRowCount(""),null,ctx_,t_,one(new IntStruct(8)),st_);
//        call(new FctTableApplyChanges(),null,ctx_,t_,null,st_);
        assertEq(4,toLong(call(new FctTableGetRowAtPoint(),null,ctx_,t_,two(new IntStruct(3),new IntStruct(4)),st_)));
        assertEq(3,toLong(call(new FctTableGetColumnAtPoint(),null,ctx_,t_,two(new IntStruct(3),new IntStruct(4)),st_)));
    }
    @Test
    public void mult1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetMultiple(),null,ctx_,t_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctTableIsMultiple(),null,ctx_,t_,null,st_));
    }
    @Test
    public void mult2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetMultiple(),null,ctx_,t_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctTableIsMultiple(),null,ctx_,t_,null,st_));
    }
    @Test
    public void reord1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetReorder(),null,ctx_,t_,one(BooleanStruct.of(false)),st_);
        assertFalse(call(new FctTableIsReorder(),null,ctx_,t_,null,st_));
    }
    @Test
    public void reord2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctTableSetReorder(),null,ctx_,t_,one(BooleanStruct.of(true)),st_);
        assertTrue(call(new FctTableIsReorder(),null,ctx_,t_,null,st_));
    }
    @Test
    public void toolTipText1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctCompoToolTip1(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        assertSame(NullStruct.NULL_VALUE,call(new FctCompoToolTip0(),null,ctx_,t_,null,st_));
    }
    @Test
    public void toolTipText2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        LgNamesGui stds_ = newLgNamesGuiSample(pr_, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(),pr_);
        Options opt_ = new Options();
        ContextEl ctx_ = gene(stds_,opt_);
        StackCall st_ = stack(ctx_);
        ArrayStruct arr_ = new ArrayStruct(4,"");
        arr_.set(0,new StringStruct("0"));
        arr_.set(1,new StringStruct("1"));
        arr_.set(2,new StringStruct("2"));
        arr_.set(3,new StringStruct("3"));
        Struct t_ = call(new FctTableGrid(stds_.getExecContent().getCustAliases(), stds_.getGuiExecutingBlocks(), ""), null, ctx_, null, one(arr_), st_);
        call(new FctCompoToolTip1(),null,ctx_,t_,one(new StringStruct("_")),st_);
        assertEq("_",call(new FctCompoToolTip0(),null,ctx_,t_,null,st_));
    }
    @Test
    public void addSelectEvt1(){
        int[] a_ = TableStruct.retrieveBoundsAdd(new int[]{}, -1, -1, 1, 2);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(2,a_[1]);
    }
    @Test
    public void addSelectEvt2(){
        int[] a_ = TableStruct.retrieveBoundsAdd(new int[]{1,2}, 1, 2, 5, 6);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(6,a_[1]);
    }
    @Test
    public void addSelectEvt3(){
        int[] a_ = TableStruct.retrieveBoundsAdd(new int[]{1,2,5,6}, 5, 6, 3, 4);
        assertEq(2,a_.length);
        assertEq(3,a_[0]);
        assertEq(6,a_[1]);
    }
    @Test
    public void addSelectEvt4(){
        int[] a_ = TableStruct.retrieveBoundsAdd(new int[]{1,2,3,4,5,6}, 3, 4, 3, 4);
        assertEq(0,a_.length);
    }
    @Test
    public void addSelectEvt5(){
        int[] a_ = TableStruct.retrieveBoundsAdd(new int[]{}, 1, 6, 8, 16);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(16,a_[1]);
    }
    @Test
    public void addSelectEvt6(){
        int[] a_ = TableStruct.retrieveBoundsAdd(new int[]{}, -1, -1, 1, 3);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(3,a_[1]);
    }
    @Test
    public void addSelectEvt7(){
        int[] a_ = TableStruct.retrieveBoundsAdd(new int[]{1,2,3}, 1, 3, 2, 5);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(5,a_[1]);
    }
    @Test
    public void addSelectEvt8(){
        int[] a_ = TableStruct.retrieveBoundsAdd(new int[]{}, -1, -1, 2, 5);
        assertEq(2,a_.length);
        assertEq(2,a_[0]);
        assertEq(5,a_[1]);
    }
    @Test
    public void addSelectEvt9(){
        int[] a_ = TableStruct.retrieveBoundsAdd(new int[]{2,3,4,5}, 2, 5, 1, 3);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(5,a_[1]);
    }
    @Test
    public void addSelectEvt10(){
        int[] a_ = TableStruct.retrieveBoundsAdd(new int[]{5,6}, 5, 6, 1, 2);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(6,a_[1]);
    }
    @Test
    public void addSelectEvt11(){
        int[] a_ = TableStruct.retrieveBoundsAddSingle(new int[]{5,6}, 5, 6, 1);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(6,a_[1]);
    }
    @Test
    public void addSelectEvt12(){
        int[] a_ = TableStruct.retrieveBoundsAddSingle(new int[]{5,6}, 5, 6, 5);
        assertEq(2,a_.length);
        assertEq(5,a_[0]);
        assertEq(6,a_[1]);
    }
    @Test
    public void addSelectEvt13(){
        int[] a_ = TableStruct.retrieveBoundsAddSingle(new int[]{}, -1, -1, 1);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(1,a_[1]);
    }
    @Test
    public void remSelectEvt1(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{1,2,3,4,5,6}, 3, 4, 3, 4);
        assertEq(2,a_.length);
        assertEq(3,a_[0]);
        assertEq(4,a_[1]);
    }
    @Test
    public void remSelectEvt2(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{1,2,5,6}, 3, 4, 3, 4);
        assertEq(0,a_.length);
    }
    @Test
    public void remSelectEvt3(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{1,2,5,6}, 3, 4, 1, 2);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(4,a_[1]);
    }
    @Test
    public void remSelectEvt4(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{5,6}, 1, 2, 5, 6);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(6,a_[1]);
    }
    @Test
    public void remSelectEvt5(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{}, 5, 6, 1, 6);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(5,a_[1]);
    }
    @Test
    public void remSelectEvt6(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{}, 1, 6, 1, 6);
        assertEq(0,a_.length);
    }
    @Test
    public void remSelectEvt7(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{8,9,10,11,12,13,14,15,16}, 8, 16, 8, 16);
        assertEq(2,a_.length);
        assertEq(8,a_[0]);
        assertEq(16,a_[1]);
    }
    @Test
    public void remSelectEvt8(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{1,2,3,4,5}, 1, 3, 3, 7);
        assertEq(2,a_.length);
        assertEq(1,a_[0]);
        assertEq(7,a_[1]);
    }
    @Test
    public void remSelectEvt9(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{1,2,3,4,5}, 2, 5, 3, 7);
        assertEq(2,a_.length);
        assertEq(2,a_[0]);
        assertEq(7,a_[1]);
    }
    @Test
    public void remSelectEvt10(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{1,2,3,4,5}, 1, 3, 0, 2);
        assertEq(2,a_.length);
        assertEq(0,a_[0]);
        assertEq(3,a_[1]);
    }
    @Test
    public void remSelectEvt11(){
        int[] a_ = TableStruct.retrieveBoundsRem(new int[]{1,2,3,4,5}, 2, 5, 0, 2);
        assertEq(2,a_.length);
        assertEq(0,a_[0]);
        assertEq(5,a_[1]);
    }
    @Test
    public void remSelectEvt12(){
        int[] a_ = TableStruct.retrieveBoundsRemSingle(new int[]{1,2,3,4,5}, 2, 5, 0, 2);
        assertEq(2,a_.length);
        assertEq(0,a_[0]);
        assertEq(5,a_[1]);
    }
    @Test
    public void remSelectEvt13(){
        int[] a_ = TableStruct.retrieveBoundsRemSingle(new int[]{1,2,3,4,5}, 2, 5, 2, 3);
        assertEq(2,a_.length);
        assertEq(2,a_[0]);
        assertEq(5,a_[1]);
    }
    @Test
    public void remSelectEvt14(){
        int[] a_ = TableStruct.retrieveBoundsRemSingle(new int[]{1,2,3,4,5}, 2, 5, 3, 6);
        assertEq(2,a_.length);
        assertEq(2,a_[0]);
        assertEq(6,a_[1]);
    }
    @Test
    public void remSelectEvt15(){
        int[] a_ = TableStruct.retrieveBoundsRemSingle(new int[]{1,2,3,4,5}, 2, 5, 0, 6);
        assertEq(2,a_.length);
        assertEq(0,a_[0]);
        assertEq(6,a_[1]);
    }
    @Test
    public void remSelectEvt16(){
        int[] a_ = TableStruct.retrieveBoundsRemSingle(new int[]{}, -1, -1, 0, 0);
        assertEq(2,a_.length);
        assertEq(0,a_[0]);
        assertEq(0,a_[1]);
    }
    @Test
    public void selectDbg1() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){GridTable g = new();g.setMultiple(true);g.setRowCount(8);g.addSelect((TableListener)(int a, int b:void)->{});g.addInterval(1,3);g.removeInterval(1,3);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void selectDbg2() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){GridTable g = new();g.setMultiple(false);g.setRowCount(8);g.addSelect((TableListener)(int a, int b:void)->{});g.addInterval(1,3);g.removeInterval(1,3);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void selectDbg3() {
        MockProgramInfos pr_ = newMockProgramInfos(new CustomSeedGene(), new MockFileSet(5, lgs(1), new String[]{"/"}));
        StringMap<String> files_ = new StringMap<String>();
        files_.addEntry("src/sample.txt","public class pkg.Sample{public static void run(){GridTable g = new();g.setMultiple(true);g.addSelect((TableListener)(int a, int b:void)->{});g.setRowCount(8);g.addInterval(1,3);g.removeInterval(1,3);}}");
        ResultContext ctx_ = ctx(pr_, files_);
        StackCallReturnValue dbg_ = launchDbg(ctx_);
        assertEq(0,dbg_.getStack().nbPages());
    }
    @Test
    public void rowCount1() {
        int[] a_ = TableStruct.retrieveBoundsRowCount(new int[]{},new int[]{},-1,-1,-1,-1);
        assertEq(0,a_.length);
    }
    @Test
    public void rowCount2() {
        int[] a_ = TableStruct.retrieveBoundsRowCount(new int[]{10,11,12,13},new int[]{14,15,16,17},10,13,14,17);
        assertEq(2,a_.length);
        assertEq(10,a_[0]);
        assertEq(17,a_[1]);
    }
    @Test
    public void rowCount3() {
        int[] a_ = TableStruct.retrieveBoundsRowCount(new int[]{10,11,12,13,15,16,17},new int[]{14,15,16,17,19,20,21},15,17,19,21);
        assertEq(2,a_.length);
        assertEq(10,a_[0]);
        assertEq(21,a_[1]);
    }
    @Test
    public void rowCount4() {
        int[] a_ = TableStruct.retrieveBoundsRowCount(new int[]{6,7,8,9,10,11,12},new int[]{6,7,8,9,10},6,12,6,10);
        assertEq(2,a_.length);
        assertEq(10,a_[0]);
        assertEq(12,a_[1]);
    }
    @Test
    public void rowCount5() {
        int[] a_ = TableStruct.retrieveBoundsRowCount(new int[]{},new int[]{0,1,2,3},1,2,1,2);
        assertEq(2,a_.length);
        assertEq(0,a_[0]);
        assertEq(3,a_[1]);
    }
    @Test
    public void rowCount6() {
        int[] a_ = TableStruct.retrieveBoundsRowCount(new int[]{0,1,2,3},new int[]{},1,2,1,2);
        assertEq(2,a_.length);
        assertEq(0,a_[0]);
        assertEq(3,a_[1]);
    }
    private StackCallReturnValue launchDbg(ResultContext _ctx) {
        ExecRootBlock ex_ = _ctx.getContext().getClasses().getClassBody("pkg.Sample");
        ExecFormattedRootBlock form_ = new ExecFormattedRootBlock(ex_);
        MethodId id_ = new MethodId(MethodAccessKind.STATIC, "run", new StringList());
        return ExecClassesUtil.tryInitStaticlyTypes(_ctx.getContext(), _ctx.getForwards().getOptions(), null, new CustomFoundMethod(form_, new ExecTypeFunction(form_, ExecClassesUtil.getMethodBodiesById(ex_, id_).first()), new Parameters()), StepDbgActionEnum.DEBUG, false);
    }

    protected static StackCallReturnValue dbgContinueNormalValue(StackCall _stack, ContextEl _cont) {
        return ExecClassesUtil.tryInitStaticlyTypes(_cont,null,_stack,null,StepDbgActionEnum.KEEP, false);
    }
    private ResultContext ctx(MockProgramInfos _p, StringMap<String> _files) {
        update(_p);
        LgNamesGui stds_ = newLgNamesGuiSampleGr(_p, null);
        stds_.getGuiExecutingBlocks().initApplicationParts(new StringList(), _p);
        ExecutingOptions e_ = new ExecutingOptions();
        CdmFactory cdm_ = new CdmFactory(_p, new MockInterceptor());
        e_.setLightProgramInfos(_p);
        e_.setListGenerator(cdm_);
        e_.getInterceptor().newMapStringStruct();
        stds_.getExecContent().setExecutingOptions(e_);
        stds_.getExecContent().updateTranslations(_p.getTranslations(),_p.getLanguage(),"en");
        Options opt_ = new Options();
        return buildMock(opt_,e_,new AnalysisMessages(),new KeyWords(),stds_,_files);
    }

    public static ResultContext buildMock(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        preBuild(_definedLgNames, _exec, _mess, _definedKw);
        StringMap<String> s_ = new StringMap<String>();
        s_.addEntry("0",_definedLgNames.getGuiAliases().tableListener(_definedKw, _definedLgNames.getContent()));
        AnalyzedPageEl page_ = beginBuild(_definedLgNames);
        Forwards forwards_ = nextBuild(_options, _definedKw, _definedLgNames, _files, s_, page_);
        ParsedArgument.buildCustom(_options, _definedKw);
        _definedLgNames.buildBase();

        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass component_ = new StandardClass(_definedLgNames.getGuiAliases().getAliasComponent(), fields_, constructors_, methods_, _definedLgNames.getContent().getCoreNames().getAliasObject(), StdClassModifier.ABSTRACT);
        component_.addSuperStdTypes(_definedLgNames.getContent().getCoreNames().getObjType());
        StandardType.addType(_definedLgNames.getContent().getStandards(), _definedLgNames.getGuiAliases().getAliasComponent(), component_);
        _definedLgNames.getGuiAliases().tableGui(_definedLgNames.getContent(), _definedLgNames.getExecContent().getCustAliases(), _definedLgNames.getGuiExecutingBlocks(), component_);

        ValidatorStandard.setupOverrides(page_);
        ResultContext res_ = commonMockDbg(_exec, _definedLgNames, _files, page_, forwards_);
        LgNamesGui stds_ = (LgNamesGui) res_.getContext().getStandards();
        stds_.getGuiExecutingBlocks().tableListener(stds_.getGuiAliases(), _definedLgNames.getContent(), res_.getContext().getClasses());
        Classes.tryInit(res_);
        return res_;
    }

    public static LgNamesGui newLgNamesGuiSampleGr(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.getExecContent().setExecutingOptions(new ExecutingOptions());
        stds_.getExecContent().updateTranslations(_light.getTranslations(), _light.getLanguage(),"en");
        return stds_;
    }
    private Struct arr() {
        ArrayStruct arr_ = new ArrayStruct(1,"");
        arr_.set(0,new StringStruct(""));
        return arr_;
    }
}
