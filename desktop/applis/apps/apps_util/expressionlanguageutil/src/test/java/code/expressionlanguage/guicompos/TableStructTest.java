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
        call(new FctTableAddSelect(),null,ctx_,t_,one(NullStruct.NULL_VALUE),st_);
        call(new FctTableAddSelect(),null,ctx_,t_,one(ctx_.getInit().processInit(ctx_,NullStruct.NULL_VALUE,new ExecFormattedRootBlock(new ExecClassBlock(new ExecRootBlockContent(new AnaRootBlockContent()),AccessEnum.PUBLIC,new ExecClassContent(new AnaClassContent(true,false,true))),""),"",-1)),st_);
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
        call(new FctTableSetRowCount(),null,ctx_,t_,one(new IntStruct(2)),st_);
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
        call(new FctTableSetRowCount(),null,ctx_,t_,one(new IntStruct(2)),st_);
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
        call(new FctTableSetRowCount(),null,ctx_,t_,one(new IntStruct(8)),st_);
        call(new FctTableAddInterval(),null,ctx_,t_,two(new IntStruct(2),new IntStruct(4)),st_);
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
        call(new FctTableSetRowCount(),null,ctx_,t_,one(new IntStruct(8)),st_);
        call(new FctTableAddInterval(),null,ctx_,t_,two(new IntStruct(2),new IntStruct(4)),st_);
        call(new FctTableRemoveInterval(),null,ctx_,t_,two(new IntStruct(2),new IntStruct(4)),st_);
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
        call(new FctTableSetRowCount(),null,ctx_,t_,one(new IntStruct(8)),st_);
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
        call(new FctTableSetRowCount(),null,ctx_,t_,one(new IntStruct(8)),st_);
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
        call(new FctTableSetRowCount(),null,ctx_,t_,one(new IntStruct(8)),st_);
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
}
