package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.AnalyzedTestContext;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaPartTypeUtil;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.Matching;
import code.expressionlanguage.inherits.MatchingEnum;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.structs.DoubleStruct;
import code.expressionlanguage.structs.IntStruct;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.Test;

public final class AnaTemplatesTest extends ProcessMethodCommon {

    private static final String ARR_OBJECT = "[java.lang.Object";
    private static final String ARR_STRING = "[java.lang.String";
    private static final String ARR_VAR_S = "[#S";
    private static final String ARR_VAR_T = "[#T";

    @Test
    public void getAllInnerTypesSingleDotted1_Test(){
        assertEq(new StringList("int"), AnaInherits.getAllInnerTypes("int", new StringList()));
    }
    @Test
    public void getAllInnerTypesSingleDotted2_Test(){
        assertEq(new StringList("String"), AnaInherits.getAllInnerTypes("String", new StringList()));
    }
    @Test
    public void getAllInnerTypesSingleDotted3_Test(){
        assertEq(new StringList("pkg.Ex"), AnaInherits.getAllInnerTypes("pkg.Ex", new StringList("pkg")));
    }
    @Test
    public void getAllInnerTypesSingleDotted4_Test(){
        assertEq(new StringList("pkg.Ex",".","Inner"), AnaInherits.getAllInnerTypes("pkg.Ex.Inner", new StringList("pkg")));
    }
    @Test
    public void getAllInnerTypesSingleDotted5_Test(){
        assertEq(new StringList("Ex"), AnaInherits.getAllInnerTypes("Ex", new StringList("pkg")));
    }
    @Test
    public void getAllInnerTypesSingleDotted6_Test(){
        assertEq(new StringList("pkg.Ex",".","Inner"), AnaInherits.getAllInnerTypes("pkg.Ex.Inner", new StringList("pkg")));
    }
    @Test
    public void getAllInnerTypesSingleDotted7_Test(){
        assertEq(new StringList("Ex",".","Inner"), AnaInherits.getAllInnerTypes("Ex.Inner", new StringList("pkg")));
    }
    @Test
    public void getAllInnerTypesSingleDotted8_Test(){
        assertEq(new StringList("Ex",".","pkg",".","Inner"), AnaInherits.getAllInnerTypes("Ex.pkg.Inner", new StringList("pkg")));
    }
    @Test
    public void getAllInnerTypesSingleDotted9_Test(){
        assertEq(new StringList("Ex",".","pkg",".","Inner",".","Outer"), AnaInherits.getAllInnerTypes("Ex.pkg.Inner.Outer", new StringList("pkg")));
    }
    @Test
    public void getAllInnerTypesSingleDotted10_Test(){
        assertEq(new StringList("pkg.Ex",".","Inner","..","Outer"), AnaInherits.getAllInnerTypes("pkg.Ex.Inner..Outer", new StringList("pkg")));
    }
    @Test
    public void getAllInnerTypesSingleDotted11_Test(){
        assertEq(new StringList("pkg","..","Outer"), AnaInherits.getAllInnerTypes("pkg..Outer", new StringList("pkg")));
    }


    @Test
    public void getComponentForm1Test() {
        DimComp inferred_ = AnaInherits.getComponentForm("pkg.ExTwo");
        assertEq("pkg.ExTwo", inferred_.getComponent());
        assertEq(0, inferred_.getDim());
    }

    @Test
    public void getComponentForm2Test() {
        DimComp inferred_ = AnaInherits.getComponentForm("pkg.ExTwo[]");
        assertEq("pkg.ExTwo", inferred_.getComponent());
        assertEq(1, inferred_.getDim());
    }

    @Test
    public void getComponentForm3Test() {
        DimComp inferred_ = AnaInherits.getComponentForm("pkg.ExTwo[] ");
        assertEq("pkg.ExTwo", inferred_.getComponent());
        assertEq(1, inferred_.getDim());
    }

    @Test
    public void getComponentForm4Test() {
        DimComp inferred_ = AnaInherits.getComponentForm("pkg.ExTwo[ ]");
        assertEq("pkg.ExTwo", inferred_.getComponent());
        assertEq(1, inferred_.getDim());
    }

    @Test
    public void getComponentForm5Test() {
        DimComp inferred_ = AnaInherits.getComponentForm("pkg.ExTwo[][]");
        assertEq("pkg.ExTwo", inferred_.getComponent());
        assertEq(2, inferred_.getDim());
    }

    @Test
    public void getComponentForm6Test() {
        DimComp inferred_ = AnaInherits.getComponentForm("[]");
        assertEq("", inferred_.getComponent());
        assertEq(0, inferred_.getDim());
    }

    @Test
    public void getComponentForm7Test() {
        DimComp inferred_ = AnaInherits.getComponentForm("]");
        assertEq("", inferred_.getComponent());
        assertEq(0, inferred_.getDim());
    }

    @Test
    public void getComponentForm8Test() {
        DimComp inferred_ = AnaInherits.getComponentForm(" ]");
        assertEq("", inferred_.getComponent());
        assertEq(0, inferred_.getDim());
    }
    @Test
    public void tryInfer1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<W> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.Number>", new StringMap<String>());
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive {}\n");
        xml_.append("$public $interface pkg.ExFive {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertNull(tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive", new StringMap<String>()));
    }

    @Test
    public void tryInfer3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive {}\n");
        xml_.append("$public $interface pkg.ExFive {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        xml_.append("$public $class pkg.ExOther{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertNull(tryInfer(cont_, "pkg.ExTwo", "pkg.ExOther", new StringMap<String>()));
    }

    @Test
    public void tryInfer4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.$iterable<java.lang.Number>>", new StringMap<String>());
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<?W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.$iterable<?java.lang.Number>>", new StringMap<String>());
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<!W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.$iterable<!java.lang.Number>>", new StringMap<String>());
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<?>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertNull(tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.$iterable<?>>", new StringMap<String>()));
    }

    @Test
    public void tryInfer8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<W[]>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.$iterable<[java.lang.Number>>", new StringMap<String>());
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<?W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertNull(tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.$iterable<!java.lang.Number>>", new StringMap<String>()));
    }

    @Test
    public void tryInfer10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<!W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertNull(tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.$iterable<?java.lang.Number>>", new StringMap<String>()));
    }

    @Test
    public void tryInfer11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<java.lang.$iterable<W[]>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertNull(tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.$iterable<java.lang.Number>>", new StringMap<String>()));
    }

    @Test
    public void tryInfer12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<ExIter<W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        xml_.append("$public $class pkg.ExIter<Z> :$iterable<Z>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertNull(tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.$iterable<java.lang.Number>>", new StringMap<String>()));
    }
    @Test
    public void tryInfer13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<ExIter<W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        xml_.append("$public $class pkg.ExIter<Z> :$iterable<Z>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_, "pkg.ExTwo", "pkg.ExTwo<java.lang.$iterable<java.lang.Number>>", new StringMap<String>());
        assertEq("pkg.ExTwo<java.lang.$iterable<java.lang.Number>>", inferred_);
    }

    @Test
    public void tryInfer14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<W> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_, "pkg.ExTwo", "pkg.ExTwo<java.lang.Number>", new StringMap<String>());
        assertEq("pkg.ExTwo<java.lang.Number>", inferred_);
    }

    @Test
    public void tryInfer15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<W> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_, "java.lang.$Fct", "java.lang.$Fct<java.lang.Number>", new StringMap<String>());
        assertEq("java.lang.$Fct<java.lang.Number>", inferred_);
    }
    @Test
    public void tryInfer16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<X> {\n");
        xml_.append("$public $class Inner<Y> {}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        RootBlock root_ = cont_.getAnalyzing().getAnaClassBody("pkg.Ex");
        StringMap<String> vars_ = getVarTypes(root_, "pkg.Ex<java.lang.Number>");
        String inferred_ = tryInfer(cont_,"pkg.Ex..Inner", "pkg.Ex<java.lang.Number>..Inner<java.lang.Number>",vars_);
        assertEq("pkg.Ex<java.lang.Number>..Inner<java.lang.Number>", inferred_);
    }
    @Test
    public void tryInfer17Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<W> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_,"", "", new StringMap<String>());
        assertNull(inferred_);
        assertEq(0, AnaInherits.getBoundAll(null).size());
    }
    @Test
    public void tryInfer18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T> {}\n");
        xml_.append("$public $interface pkg.Param {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U:Param> :pkg.Ex<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_,"pkg.ExTwo", "pkg.Ex<java.lang.Object>", new StringMap<String>());
        assertNull(inferred_);
    }
    @Test
    public void tryInfer19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T> {}\n");
        xml_.append("$public $interface pkg.Param {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U:Param> :pkg.Ex<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_,"pkg.ExTwo", "pkg.Ex<pkg.Param>", new StringMap<String>());
        assertEq("pkg.ExTwo<pkg.Param>", inferred_);
    }
    @Test
    public void tryInfer20Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T> {}\n");
        xml_.append("$public $interface pkg.Param {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U:Param> :pkg.Ex<pkg.Ex<U>>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inferred_ = tryInfer(cont_,"pkg.ExTwo", "pkg.Ex", new StringMap<String>());
        assertNull(inferred_);
    }

    @Test
    public void tryInfer21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T>:ExFour<T> {}\n");
        xml_.append("$public $interface pkg.ExThree<V>:ExFour<V> {}\n");
        xml_.append("$public $interface pkg.ExFour<W>:ExFive<ExIter<W,W>> {}\n");
        xml_.append("$public $interface pkg.ExFive<X> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> :pkg.Ex<U>:ExThree<U>{}\n");
        xml_.append("$public $class pkg.ExIter<Z,A> :$iterable<Z>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        assertNull(tryInfer(cont_, "pkg.ExTwo", "pkg.ExFive<java.lang.$iterable<java.lang.Number>>", new StringMap<String>()));
    }

    @Test
    public void tryAdd() {
        StringList ls_ = new StringList();
        AnaTemplates.tryAdd(ls_,null);
        assertTrue(ls_.isEmpty());
    }

    @Test
    public void removeDup1() {
        assertNull(AnaTemplates.removeDup(null));
    }

    @Test
    public void removeDup2() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(1,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
    }

    @Test
    public void removeDup3() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(1,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
    }

    @Test
    public void removeDup4() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExParam");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(1,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
    }

    @Test
    public void removeDup5() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(1,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
    }

    @Test
    public void removeDup6() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(1,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUP,res_.get(0).getMatchEq());
    }

    @Test
    public void removeDup7() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExParam");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(1,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
    }

    @Test
    public void removeDup8() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExParam");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(1,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUP,res_.get(0).getMatchEq());
    }

    @Test
    public void removeDup9() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParamTwo");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("pkg.ExParamTwo",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup10() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExTwo");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("pkg.ExTwo",res_.get(1).getArg());
        assertEq("pkg.ExParam",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup11() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExParamTwo");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("pkg.ExParamTwo",res_.get(1).getArg());
        assertEq("pkg.Ex",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup12() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExParam");
        mapp_.setParam("pkg.ExTwo");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("pkg.ExParam",res_.get(1).getArg());
        assertEq("pkg.ExTwo",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup13() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExParam");
        mapp_.setParam("pkg.ExTwo");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
        assertEq("pkg.ExParam",res_.get(1).getArg());
        assertEq("pkg.ExTwo",res_.get(1).getParam());
        assertSame(MatchingEnum.SUP,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup14() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExParamTwo");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
        assertEq("pkg.ExParamTwo",res_.get(1).getArg());
        assertEq("pkg.Ex",res_.get(1).getParam());
        assertSame(MatchingEnum.SUP,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup15() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExParam");
        mapp_.setParam("pkg.ExTwo");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUP,res_.get(0).getMatchEq());
        assertEq("pkg.ExParam",res_.get(1).getArg());
        assertEq("pkg.ExTwo",res_.get(1).getParam());
        assertSame(MatchingEnum.SUB,res_.get(1).getMatchEq());
    }
    @Test
    public void removeDup16() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExParamTwo");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUP,res_.get(0).getMatchEq());
        assertEq("pkg.ExParamTwo",res_.get(1).getArg());
        assertEq("pkg.Ex",res_.get(1).getParam());
        assertSame(MatchingEnum.SUB,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup17() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParamTwo");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("pkg.ExParamTwo",res_.get(1).getParam());
        assertSame(MatchingEnum.SUB,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup18() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExTwo");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
        assertEq("pkg.ExTwo",res_.get(1).getArg());
        assertEq("pkg.ExParam",res_.get(1).getParam());
        assertSame(MatchingEnum.SUB,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup19() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParamTwo");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUP,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("pkg.ExParamTwo",res_.get(1).getParam());
        assertSame(MatchingEnum.SUP,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup20() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExTwo");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUP,res_.get(0).getMatchEq());
        assertEq("pkg.ExTwo",res_.get(1).getArg());
        assertEq("pkg.ExParam",res_.get(1).getParam());
        assertSame(MatchingEnum.SUP,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup21() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUP,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("pkg.ExParam",res_.get(1).getParam());
        assertSame(MatchingEnum.SUB,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup22() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("pkg.ExParam",res_.get(1).getParam());
        assertSame(MatchingEnum.SUP,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup23() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("pkg.ExParam",res_.get(1).getParam());
        assertSame(MatchingEnum.SUP,res_.get(1).getMatchEq());
    }

    @Test
    public void removeDup24() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.removeDup(l_);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("pkg.ExParam",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("pkg.ExParam",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void resolveEq1() {
        assertNull(AnaTemplates.resolveEq(null, 2));
    }
    @Test
    public void resolveEq2() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("#0",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("#1",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void resolveEq3() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("#0",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("#1",res_.get(1).getArg());
        assertEq("pkg.Ex",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void resolveEq4() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("#0",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void resolveEq5() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(2,res_.size());
        assertEq("#1",res_.get(0).getArg());
        assertEq("pkg.Ex",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("#0",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }

    @Test
    public void resolveEq6() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(2,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("pkg.Ex",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("#1",res_.get(1).getArg());
        assertEq("pkg.Ex",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void resolveEq7() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(2,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("pkg.Ex",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("pkg.Ex",res_.get(1).getArg());
        assertEq("#1",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void resolveEq8() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(2,res_.size());
        assertEq("#1",res_.get(0).getArg());
        assertEq("pkg.Ex",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("#0",res_.get(1).getArg());
        assertEq("pkg.Ex",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void resolveEq9() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(2,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("#0",res_.get(1).getArg());
        assertEq("pkg.Ex",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void resolveEq10() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExTwo");
        mapp_.setParam("pkg.ExTwo");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(3,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("#0",res_.get(1).getArg());
        assertEq("pkg.Ex",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
        assertEq("pkg.ExTwo",res_.get(2).getArg());
        assertEq("pkg.ExTwo",res_.get(2).getParam());
        assertSame(MatchingEnum.EQ,res_.get(2).getMatchEq());
    }
    @Test
    public void resolveEq11() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("pkg.ExTwo");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(2,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("pkg.Ex",res_.get(0).getParam());
        assertSame(MatchingEnum.SUP,res_.get(0).getMatchEq());
        assertEq("#1",res_.get(1).getArg());
        assertEq("pkg.ExTwo",res_.get(1).getParam());
        assertSame(MatchingEnum.SUP,res_.get(1).getMatchEq());
    }
    @Test
    public void resolveEq12() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("pkg.ExTwo");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(2,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("pkg.Ex",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
        assertEq("#1",res_.get(1).getArg());
        assertEq("pkg.ExTwo",res_.get(1).getParam());
        assertSame(MatchingEnum.SUB,res_.get(1).getMatchEq());
    }
    @Test
    public void resolveEq13() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExTwo");
        mapp_.setParam("pkg.ExTwo");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(3,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("#0",res_.get(1).getArg());
        assertEq("pkg.Ex",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
        assertEq("pkg.ExTwo",res_.get(2).getArg());
        assertEq("pkg.ExTwo",res_.get(2).getParam());
        assertSame(MatchingEnum.SUB,res_.get(2).getMatchEq());
    }

    @Test
    public void resolveEq14() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExTwo");
        mapp_.setParam("pkg.ExTwo");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.resolveEq(l_, 2);
        assertEq(3,res_.size());
        assertEq("pkg.Ex",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("#0",res_.get(1).getArg());
        assertEq("pkg.Ex",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
        assertEq("pkg.ExTwo",res_.get(2).getArg());
        assertEq("pkg.ExTwo",res_.get(2).getParam());
        assertSame(MatchingEnum.SUP,res_.get(2).getMatchEq());
    }

    @Test
    public void mergeEq1() {
        assertNull(AnaTemplates.mergeEq(null));
    }
    @Test
    public void mergeEq2() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.mergeEq(l_);
        assertEq(1,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
    }
    @Test
    public void mergeEq3() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.mergeEq(l_);
        assertEq(1,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
    }
    @Test
    public void mergeEq4() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.mergeEq(l_);
        assertEq(1,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
    }
    @Test
    public void mergeEq5() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#2");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.mergeEq(l_);
        assertEq(2,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
        assertEq("#0",res_.get(1).getArg());
        assertEq("#2",res_.get(1).getParam());
        assertSame(MatchingEnum.SUP,res_.get(1).getMatchEq());
    }
    @Test
    public void mergeEq6() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#2");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.mergeEq(l_);
        assertEq(2,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
        assertEq("#2",res_.get(1).getArg());
        assertEq("#0",res_.get(1).getParam());
        assertSame(MatchingEnum.SUB,res_.get(1).getMatchEq());
    }
    @Test
    public void mergeEq7() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#2");
        mapp_.setParam("#3");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.mergeEq(l_);
        assertEq(2,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("#2",res_.get(1).getArg());
        assertEq("#3",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void mergeEq8() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#2");
        mapp_.setParam("#3");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.mergeEq(l_);
        assertEq(2,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.EQ,res_.get(0).getMatchEq());
        assertEq("#2",res_.get(1).getArg());
        assertEq("#3",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void mergeEq9() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.mergeEq(l_);
        assertEq(2,res_.size());
        assertEq("#1",res_.get(0).getArg());
        assertEq("#0",res_.get(0).getParam());
        assertSame(MatchingEnum.SUB,res_.get(0).getMatchEq());
        assertEq("#1",res_.get(1).getArg());
        assertEq("#0",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void mergeEq10() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        CustList<Matching> res_ = AnaTemplates.mergeEq(l_);
        assertEq(2,res_.size());
        assertEq("#0",res_.get(0).getArg());
        assertEq("#1",res_.get(0).getParam());
        assertSame(MatchingEnum.SUP,res_.get(0).getMatchEq());
        assertEq("#1",res_.get(1).getArg());
        assertEq("#0",res_.get(1).getParam());
        assertSame(MatchingEnum.EQ,res_.get(1).getMatchEq());
    }
    @Test
    public void getSubTypes1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExOther{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringList res_ = AnaTypeUtil.getSubTypes(new StringList("pkg.Ex", "pkg.ExOther"), new StringMap<StringList>(), cont_.getAnalyzing());
        assertEq(2,res_.size());
        assertTrue(StringUtil.contains(res_,"pkg.Ex"));
        assertTrue(StringUtil.contains(res_,"pkg.ExOther"));
    }
    @Test
    public void getSubTypes2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper{}\n");
        xml_.append("$public $class pkg.ExSuper{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringList res_ = AnaTypeUtil.getSubTypes(new StringList("pkg.Ex", "pkg.ExSuper"), new StringMap<StringList>(), cont_.getAnalyzing());
        assertEq(1,res_.size());
        assertTrue(StringUtil.contains(res_,"pkg.Ex"));
    }
    @Test
    public void getSubTypes3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringList res_ = AnaTypeUtil.getSubTypes(new StringList("pkg.Ex", "pkg.Ex"), new StringMap<StringList>(), cont_.getAnalyzing());
        assertTrue(res_.onlyOneElt());
        assertTrue(StringUtil.contains(res_,"pkg.Ex"));
    }
    @Test
    public void getSubTypes4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringList res_ = AnaTypeUtil.getSubTypes(new StringList("pkg.ExParam<pkg.Ex>", "pkg.ExParam<pkg.ExTwo>"), new StringMap<StringList>(), cont_.getAnalyzing());
        assertEq(2,res_.size());
        assertTrue(StringUtil.contains(res_,"pkg.ExParam<pkg.Ex>"));
        assertTrue(StringUtil.contains(res_,"pkg.ExParam<pkg.ExTwo>"));
    }
    @Test
    public void getSubTypes5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringList res_ = AnaTypeUtil.getSubTypes(new StringList("pkg.ExParam<pkg.Ex>", "pkg.ExParam<pkg.Ex>"), new StringMap<StringList>(), cont_.getAnalyzing());
        assertTrue(res_.onlyOneElt());
        assertTrue(StringUtil.contains(res_,"pkg.ExParam<pkg.Ex>"));
    }
    @Test
    public void getSubTypes6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>:ExParamSuper<T>{}\n");
        xml_.append("$public $class pkg.ExParamSuper<S>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringList res_ = AnaTypeUtil.getSubTypes(new StringList("pkg.ExParam<pkg.Ex>", "pkg.ExParamSuper<pkg.Ex>"), new StringMap<StringList>(), cont_.getAnalyzing());
        assertEq(1,res_.size());
        assertTrue(StringUtil.contains(res_,"pkg.ExParam<pkg.Ex>"));
    }
    @Test
    public void getSubTypes7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper{}\n");
        xml_.append("$public $class pkg.ExSuper{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringList res_ = AnaTypeUtil.getSubTypes(new StringList("pkg.ExParam<pkg.Ex>", "pkg.ExParam<pkg.ExSuper>"), new StringMap<StringList>(), cont_.getAnalyzing());
        assertEq(2,res_.size());
        assertTrue(StringUtil.contains(res_,"pkg.ExParam<pkg.Ex>"));
        assertTrue(StringUtil.contains(res_,"pkg.ExParam<pkg.ExSuper>"));
    }
    @Test
    public void getSubTypes8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper{}\n");
        xml_.append("$public $class pkg.ExSuper{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringList res_ = AnaTypeUtil.getSubTypes(new StringList("pkg.ExParam<?pkg.Ex>", "pkg.ExParam<?pkg.ExSuper>"), new StringMap<StringList>(), cont_.getAnalyzing());
        assertEq(1,res_.size());
        assertTrue(StringUtil.contains(res_,"pkg.ExParam<?pkg.Ex>"));
    }
    @Test
    public void getSubTypes9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExSuper{}\n");
        xml_.append("$public $class pkg.ExSuper{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringList res_ = AnaTypeUtil.getSubTypes(new StringList("pkg.ExParam<!pkg.Ex>", "pkg.ExParam<!pkg.ExSuper>"), new StringMap<StringList>(), cont_.getAnalyzing());
        assertEq(1,res_.size());
        assertTrue(StringUtil.contains(res_,"pkg.ExParam<!pkg.ExSuper>"));
    }
    @Test
    public void reverseEq1() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        AnaTemplates.reverseEq(l_);
        assertEq("pkg.Ex",l_.get(0).getArg());
        assertEq("#0",l_.get(0).getParam());
        assertSame(MatchingEnum.EQ,l_.get(0).getMatchEq());
    }
    @Test
    public void reverseEq2() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        AnaTemplates.reverseEq(l_);
        assertEq("pkg.Ex",l_.get(0).getArg());
        assertEq("#0",l_.get(0).getParam());
        assertSame(MatchingEnum.EQ,l_.get(0).getMatchEq());
    }
    @Test
    public void reverseEq3() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("#A");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        AnaTemplates.reverseEq(l_);
        assertEq("pkg.Ex",l_.get(0).getArg());
        assertEq("#A",l_.get(0).getParam());
        assertSame(MatchingEnum.EQ,l_.get(0).getMatchEq());
    }
    @Test
    public void reverseEq4() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#A");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        AnaTemplates.reverseEq(l_);
        assertEq("#A",l_.get(0).getArg());
        assertEq("pkg.Ex",l_.get(0).getParam());
        assertSame(MatchingEnum.EQ,l_.get(0).getMatchEq());
    }
    @Test
    public void reverseEq5() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        AnaTemplates.reverseEq(l_);
        assertEq("pkg.Ex",l_.get(0).getArg());
        assertEq("#0",l_.get(0).getParam());
        assertSame(MatchingEnum.SUB,l_.get(0).getMatchEq());
    }
    @Test
    public void reverseEq6() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        AnaTemplates.reverseEq(l_);
        assertEq("pkg.Ex",l_.get(0).getArg());
        assertEq("#0",l_.get(0).getParam());
        assertSame(MatchingEnum.SUB,l_.get(0).getMatchEq());
    }

    @Test
    public void processConstraints0() {
        assertNull(AnaTemplates.processConstraints(null,2));
    }
    @Test
    public void processConstraints1() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        StringMap<StringList> res_ = AnaTemplates.processConstraints(l_,2);
        assertEq(2,res_.size());
        assertEq("#0",res_.getKey(0));
        assertEq(1,res_.getValue(0).size());
        assertEq("pkg.Ex",res_.getValue(0).get(0));
        assertEq("#1",res_.getKey(1));
        assertEq(1,res_.getValue(1).size());
        assertEq("pkg.Ex",res_.getValue(1).get(0));
    }
    @Test
    public void processConstraints2() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        StringMap<StringList> res_ = AnaTemplates.processConstraints(l_,1);
        assertEq(1,res_.size());
        assertEq("#0",res_.getKey(0));
        assertEq(1,res_.getValue(0).size());
        assertEq("pkg.Ex",res_.getValue(0).get(0));
    }
    @Test
    public void processConstraints3() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        StringMap<StringList> res_ = AnaTemplates.processConstraints(l_,1);
        assertEq(1,res_.size());
        assertEq("#0",res_.getKey(0));
        assertEq(1,res_.getValue(0).size());
        assertEq("pkg.Ex",res_.getValue(0).get(0));
    }
    @Test
    public void processConstraints4() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        StringMap<StringList> res_ = AnaTemplates.processConstraints(l_,1);
        assertEq(1,res_.size());
        assertEq("#0",res_.getKey(0));
        assertEq(1,res_.getValue(0).size());
        assertEq("pkg.Ex",res_.getValue(0).get(0));
    }
    @Test
    public void processConstraints5() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.ExThree");
        mapp_.setParam("pkg.ExThree");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("pkg.ExTwo");
        mapp_.setMatchEq(MatchingEnum.SUB);
        l_.add(mapp_);
        StringMap<StringList> res_ = AnaTemplates.processConstraints(l_,2);
        assertEq(2,res_.size());
        assertEq("#0",res_.getKey(0));
        assertEq(1,res_.getValue(0).size());
        assertEq("pkg.Ex",res_.getValue(0).get(0));
        assertEq("#1",res_.getKey(1));
        assertEq(1,res_.getValue(1).size());
        assertEq("pkg.ExTwo",res_.getValue(1).get(0));
    }
    @Test
    public void processConstraints6() {
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.ExThree");
        mapp_.setParam("pkg.ExThree");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("#1");
        mapp_.setParam("pkg.ExTwo");
        mapp_.setMatchEq(MatchingEnum.SUP);
        l_.add(mapp_);
        StringMap<StringList> res_ = AnaTemplates.processConstraints(l_,2);
        assertEq(2,res_.size());
        assertEq("#0",res_.getKey(0));
        assertEq(1,res_.getValue(0).size());
        assertEq("pkg.Ex",res_.getValue(0).get(0));
        assertEq("#1",res_.getKey(1));
        assertEq(1,res_.getValue(1).size());
        assertEq("pkg.ExTwo",res_.getValue(1).get(0));
    }
    @Test
    public void infer1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.Ex");
        assertEq(0,infer(cont_, mapp_).size());
    }

    @Test
    public void infer2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex>");
        mapp_.setParam("pkg.ExParam<pkg.Ex>");
        assertEq(0,infer(cont_, mapp_).size());
    }

    @Test
    public void infer3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        assertEq(0,infer(cont_, mapp_).size());
    }

    @Test
    public void infer4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExOther{}\n");
        xml_.append("$public $class pkg.ExOther{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExOther");
        mapp_.setParam("pkg.Ex");
        assertEq(0,infer(cont_, mapp_).size());
    }

    @Test
    public void infer5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex>");
        mapp_.setParam("pkg.ExParam<pkg.ExTwo>");
        assertEq(0,infer(cont_, mapp_).size());
    }

    @Test
    public void infer5_() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T,S>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,pkg.Ex>");
        mapp_.setParam("pkg.ExParam<pkg.Ex,pkg.ExTwo>");
        assertEq(0,infer(cont_, mapp_).size());
    }

    @Test
    public void infer5__() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T,S>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,pkg.Ex>");
        mapp_.setParam("pkg.ExParam<pkg.Ex,pkg.ExParam<pkg.Ex,pkg.Ex>>");
        assertEq(0,infer(cont_, mapp_).size());
    }

    @Test
    public void infer6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("#0");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.SUB, cts_.get(0).getMatchEq());
    }

    @Test
    public void infer6_() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("#A");
        mapp_.setParam("#B");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("A",new StringList("#B"));
        inh_.addEntry("B",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void infer6__() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("[#0");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("[#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.SUB, cts_.get(0).getMatchEq());
    }
    @Test
    public void infer7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex>");
        mapp_.setParam("pkg.ExParam<#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void infer8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<?pkg.Ex>");
        mapp_.setParam("pkg.ExParam<?#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.SUB, cts_.get(0).getMatchEq());
    }

    @Test
    public void infer9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<!pkg.Ex>");
        mapp_.setParam("pkg.ExParam<!#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.SUP, cts_.get(0).getMatchEq());
    }
    @Test
    public void infer10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<?pkg.ExParam<pkg.Ex>>");
        mapp_.setParam("pkg.ExParam<?pkg.ExParam<#0>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }
    @Test
    public void infer10_() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<?pkg.ExParam<pkg.Ex>>");
        mapp_.setParam("pkg.ExParam<?pkg.ExParam<pkg.Ex>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void infer11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<!pkg.ExParam<pkg.Ex>>");
        mapp_.setParam("pkg.ExParam<!pkg.ExParam<#0>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getParam());
        assertEq("#0", cts_.get(0).getArg());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void infer11_() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<!pkg.ExParam<pkg.Ex>>");
        mapp_.setParam("pkg.ExParam<!pkg.ExParam<pkg.Ex>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void infer12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,pkg.Ex>");
        mapp_.setParam("pkg.ExParam<#0,#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void infer13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,pkg.ExTwo>");
        mapp_.setParam("pkg.ExParam<#0,#1>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        inh_.addEntry("1",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(2, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
        assertEq("pkg.ExTwo", cts_.get(1).getArg());
        assertEq("#1", cts_.get(1).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(1).getMatchEq());
    }

    @Test
    public void infer14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T,U:T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,#0>");
        mapp_.setParam("pkg.ExParam<#0,#1>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        inh_.addEntry("1",new StringList("#0"));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(2, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
        assertEq("#0", cts_.get(1).getArg());
        assertEq("#1", cts_.get(1).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(1).getMatchEq());
    }

    @Test
    public void infer15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("");
        mapp_.setParam("#0");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void infer16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("#0", cts_.get(0).getArg());
        assertEq("pkg.Ex", cts_.get(0).getParam());
        assertSame(MatchingEnum.SUB, cts_.get(0).getMatchEq());
    }

    @Test
    public void infer17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("[#0");
        mapp_.setParam("pkg.Ex");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("[#0", cts_.get(0).getArg());
        assertEq("pkg.Ex", cts_.get(0).getParam());
        assertSame(MatchingEnum.SUB, cts_.get(0).getMatchEq());
    }

    @Test
    public void infer18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("[#0");
        mapp_.setParam("[pkg.Ex");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = infer(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("#0", cts_.get(0).getArg());
        assertEq("pkg.Ex", cts_.get(0).getParam());
        assertSame(MatchingEnum.SUB, cts_.get(0).getMatchEq());
    }

    @Test
    public void inferEx1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.Ex");
        assertEq(0,inferEx(cont_, mapp_).size());
    }

    @Test
    public void inferEx2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex>");
        mapp_.setParam("pkg.ExParam<pkg.Ex>");
        assertEq(0,inferEx(cont_, mapp_).size());
    }

    @Test
    public void inferEx3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExParam");
        assertEq(0,inferEx(cont_, mapp_).size());
    }

    @Test
    public void inferEx4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex:ExOther{}\n");
        xml_.append("$public $class pkg.ExOther{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExOther");
        mapp_.setParam("pkg.Ex");
        assertEq(0,inferEx(cont_, mapp_).size());
    }

    @Test
    public void inferEx5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex>");
        mapp_.setParam("pkg.ExParam<pkg.ExTwo>");
        assertEq(0,inferEx(cont_, mapp_).size());
    }

    @Test
    public void inferEx6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("#0");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void inferEx_6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("#0");
        mapp_.setParam("pkg.Ex");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("#0", cts_.get(0).getArg());
        assertEq("pkg.Ex", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void infer6Ex_() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("#A");
        mapp_.setParam("#B");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("A",new StringList("#B"));
        inh_.addEntry("B",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void infer6Ex__() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("[#0");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("[#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void inferEx7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex>");
        mapp_.setParam("pkg.ExParam<#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void inferEx8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<?pkg.Ex>");
        mapp_.setParam("pkg.ExParam<?#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void inferEx9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<!pkg.Ex>");
        mapp_.setParam("pkg.ExParam<!#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }
    @Test
    public void inferEx10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<?pkg.ExParam<pkg.Ex>>");
        mapp_.setParam("pkg.ExParam<?pkg.ExParam<#0>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void inferEx11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<!pkg.ExParam<pkg.Ex>>");
        mapp_.setParam("pkg.ExParam<!pkg.ExParam<#0>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("#0", cts_.get(0).getParam());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void inferEx12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,pkg.Ex>");
        mapp_.setParam("pkg.ExParam<#0,#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void inferEx12_() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        xml_.append("$public $class pkg.ExParam2<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,pkg.Ex>");
        mapp_.setParam("pkg.ExParam2<#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void inferEx12__() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        xml_.append("$public $class pkg.ExParam2<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,pkg.Ex>");
        mapp_.setParam("pkg.ExParam2<#0,#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void inferEx12___() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<?pkg.Ex,pkg.Ex>");
        mapp_.setParam("pkg.ExParam<?#0,#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(1, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
    }

    @Test
    public void inferEx13___() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<?pkg.Ex,pkg.Ex>");
        mapp_.setParam("pkg.ExParam<!pkg.Ex,#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void inferEx14___() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<?pkg.Ex,pkg.Ex>");
        mapp_.setParam("pkg.ExParam<!#0,#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void inferEx15___() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<!#0,#0>");
        mapp_.setParam("pkg.ExParam<?pkg.Ex,pkg.Ex>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void inferEx13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,pkg.ExTwo>");
        mapp_.setParam("pkg.ExParam<#0,#1>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        inh_.addEntry("1",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(2, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
        assertEq("pkg.ExTwo", cts_.get(1).getArg());
        assertEq("#1", cts_.get(1).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(1).getMatchEq());
    }

    @Test
    public void inferEx13_() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,pkg.Ex>");
        mapp_.setParam("[pkg.ExParam<#0,#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(0, cts_.size());
    }

    @Test
    public void inferEx14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T,U:T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.ExParam<pkg.Ex,#0>");
        mapp_.setParam("pkg.ExParam<#0,#1>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        inh_.addEntry("1",new StringList("#0"));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(2, cts_.size());
        assertEq("pkg.Ex", cts_.get(0).getArg());
        assertEq("#0", cts_.get(0).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(0).getMatchEq());
        assertEq("#0", cts_.get(1).getArg());
        assertEq("#1", cts_.get(1).getParam());
        assertSame(MatchingEnum.EQ, cts_.get(1).getMatchEq());
    }

    @Test
    public void inferEx15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("");
        mapp_.setParam("#0");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> cts_ = inferEx(cont_, mapp_);
        assertEq(0, cts_.size());
    }


    @Test
    public void isVar1() {
        assertTrue(!AnaTemplates.isVar("pkg.MyClass"));
    }

    @Test
    public void isVar2() {
        assertTrue(!AnaTemplates.isVar("pkg.MyClass<#T>"));
    }

    @Test
    public void isVar3() {
        assertTrue(AnaTemplates.isVar("pkg.MyClass<#0>"));
    }

    @Test
    public void isVar4() {
        assertTrue(!AnaTemplates.isVar("#"));
    }

    @Test
    public void getCorrectTemplateAllAll() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        assertEq("", AnaInherits.getCorrectTemplateAllAll("",new StringList(),new StringMap<StringList>(),cont_.getAnalyzing()));
    }

    @Test
    public void tryGetDeclaredImplicitCast1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{\n");
        xml_.append(" $public $static ExTwo $(Ex p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("pkg.ExTwo");
        ClassMethodIdReturn result_ = tryGetDeclaredImplicitCast(cont_, mapp_);
        assertTrue(result_.isFoundMethod());
        assertEq("pkg.Ex",result_.getId().getClassName());
        assertEq("pkg.ExTwo",result_.getReturnType());
    }

    @Test
    public void tryGetDeclaredImplicitCast2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<#0>");
        mapp_.setParam("pkg.ExTwo<#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        ClassMethodIdReturn result_ = tryGetDeclaredImplicitCast(cont_, mapp_);
        assertTrue(result_.isFoundMethod());
        assertEq("pkg.Ex<#0>",result_.getId().getClassName());
        assertEq("pkg.ExTwo<#0>",result_.getReturnType());
    }

    @Test
    public void tryGetDeclaredImplicitCast3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<#0>");
        mapp_.setParam("pkg.ExTwo<#1>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        inh_.addEntry("1",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        ClassMethodIdReturn result_ = tryGetDeclaredImplicitCast(cont_, mapp_);
        assertTrue(result_.isFoundMethod());
        assertEq("pkg.Ex<#0>",result_.getId().getClassName());
        assertEq("pkg.ExTwo<#0>",result_.getReturnType());
    }

    @Test
    public void tryGetDeclaredImplicitCast4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append(" $public $static ExSub<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<#0>");
        mapp_.setParam("pkg.ExTwo<#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        ClassMethodIdReturn result_ = tryGetDeclaredImplicitCast(cont_, mapp_);
        assertTrue(result_.isFoundMethod());
        assertEq("pkg.Ex<#0>",result_.getId().getClassName());
        assertEq("pkg.ExSub<#0>",result_.getReturnType());
    }


    @Test
    public void tryGetDeclaredImplicitCast5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        assertEq("pkg.Ex<$int>",tryGetDeclaredImplicitCast("pkg.ExTwo<$int>",new StringMap<String>(),"pkg.Ex", cont_));
    }

    @Test
    public void tryGetDeclaredImplicitCast6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        assertNull(tryGetDeclaredImplicitCast("pkg.ExTwo<$int>",new StringMap<String>(),"pkg.Ex", cont_));
    }

    @Test
    public void tryGetDeclaredImplicitCast7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append(" $public $static ExTwo<U> $(Ex<U> p){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        assertEq("pkg.Ex<$int>",tryGetDeclaredImplicitCast("pkg.ExTwo<$int>",new StringMap<String>(),"pkg.Ex", cont_));
    }

    @Test
    public void tryGetDeclaredImplicitCast8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append(" $public $static ExTwo<U> $(Ex<U> p){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        assertNull(tryGetDeclaredImplicitCast("pkg.Ex",new StringMap<String>(),"", cont_));
    }

    @Test
    public void inferOrImplicit1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append(" $public $static ExSub<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Simple>");
        mapp_.setParam("pkg.ExTwo<#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("pkg.Simple",result_.get(0).getArg());
        assertEq("#0",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Simple>");
        mapp_.setParam("pkg.ExTwo<#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(0,result_.size());
    }

    @Test
    public void inferOrImplicit3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Simple>");
        mapp_.setParam("pkg.ExTwo<#0>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("pkg.Simple",result_.get(0).getArg());
        assertEq("#0",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append(" $public $static ExSub<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<#0>");
        mapp_.setParam("pkg.ExTwo<pkg.Simple>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("#0",result_.get(0).getArg());
        assertEq("pkg.Simple",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<#0>");
        mapp_.setParam("pkg.ExTwo<pkg.Simple>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(0,result_.size());
    }

    @Test
    public void inferOrImplicit6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<T>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<#0>");
        mapp_.setParam("pkg.ExTwo<pkg.Simple>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("0",new StringList(cont_.getAnalyzing().getAliasObject()));
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("#0",result_.get(0).getArg());
        assertEq("pkg.Simple",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Simple>");
        mapp_.setParam("pkg.ExTwo<pkg.Simple>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(0,result_.size());
    }

    @Test
    public void inferOrImplicit8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Simple>");
        mapp_.setParam("pkg.ExTwo<pkg.Simple2>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(0,result_.size());
    }

    @Test
    public void inferOrImplicit9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Ex<pkg.Simple>>");
        mapp_.setParam("pkg.ExTwo<pkg.Ex<pkg.Simple>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(0,result_.size());
    }

    @Test
    public void inferOrImplicit10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Ex<pkg.Simple>>");
        mapp_.setParam("pkg.ExTwo<pkg.Ex<pkg.Simple2>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(0,result_.size());
    }

    @Test
    public void inferOrImplicit11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Ex<pkg.Simple>>");
        mapp_.setParam("pkg.ExTwo<pkg.ExTwo<pkg.Simple2>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(0,result_.size());
    }

    @Test
    public void inferOrImplicit12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Ex<#0>>");
        mapp_.setParam("pkg.ExTwo<pkg.Ex<pkg.Simple>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("#0",result_.get(0).getArg());
        assertEq("pkg.Simple",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Ex<pkg.Simple>>");
        mapp_.setParam("pkg.ExTwo<pkg.Ex<#0>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("pkg.Simple",result_.get(0).getArg());
        assertEq("#0",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Ex<?#0>>");
        mapp_.setParam("pkg.ExTwo<pkg.Ex<?pkg.Simple>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("#0",result_.get(0).getArg());
        assertEq("pkg.Simple",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.Ex<?pkg.Simple>>");
        mapp_.setParam("pkg.ExTwo<pkg.Ex<?#0>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("pkg.Simple",result_.get(0).getArg());
        assertEq("#0",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.ExTwoPar<?pkg.Ex<pkg.Simple2>,?pkg.Simple>>");
        mapp_.setParam("pkg.ExTwo<pkg.ExTwoPar<?pkg.Ex<pkg.Simple2>,?#0>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("pkg.Simple",result_.get(0).getArg());
        assertEq("#0",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.ExTwoPar<?pkg.Ex<pkg.Simple2>,?pkg.Simple>>");
        mapp_.setParam("pkg.ExTwo<pkg.ExTwoPar<?java.lang.Object,?#0>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("pkg.Simple",result_.get(0).getArg());
        assertEq("#0",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.ExTwoPar<!java.lang.Object,?pkg.Simple>>");
        mapp_.setParam("pkg.ExTwo<pkg.ExTwoPar<!pkg.Ex<pkg.Simple2>,?#0>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(1,result_.size());
        assertEq("pkg.Simple",result_.get(0).getArg());
        assertEq("#0",result_.get(0).getParam());
        assertSame(MatchingEnum.EQ,result_.get(0).getMatchEq());
    }

    @Test
    public void inferOrImplicit19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.ExTwoPar<!java.lang.Object,?>>");
        mapp_.setParam("pkg.ExTwo<pkg.ExTwoPar<!java.lang.Object,?pkg.Simple>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(0,result_.size());
    }

    @Test
    public void inferOrImplicit20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Ex<pkg.ExTwoPar<?java.lang.String,pkg.ExTwoPar<#0,java.lang.Object>>>");
        mapp_.setParam("pkg.ExTwo<pkg.ExTwoPar<?java.lang.Object,pkg.Ex<java.lang.Object>>>");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        CustList<Matching> result_ = inferOrImplicit(cont_, mapp_,MatchingEnum.SUB);
        assertEq(0,result_.size());
    }
    @Test
    public void isCorrectOrNumbersVars1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("pkg.Simple");
        mapp_.setParam("#0");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        assertTrue(AnaTemplates.isCorrectOrNumbersVars(mapp_,cont_.getAnalyzing()));
    }
    @Test
    public void isCorrectOrNumbersVars2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("#A");
        mapp_.setParam("#A");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("A",new StringList("pkg.Simple"));
        mapp_.setMapping(inh_);
        assertTrue(AnaTemplates.isCorrectOrNumbersVars(mapp_,cont_.getAnalyzing()));
    }

    @Test
    public void isCorrectOrNumbersVars3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("");
        mapp_.setParam("#A");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("A",new StringList("pkg.Simple"));
        mapp_.setMapping(inh_);
        assertTrue(AnaTemplates.isCorrectOrNumbersVars(mapp_,cont_.getAnalyzing()));
    }

    @Test
    public void isCorrectOrNumbersVars4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("");
        mapp_.setParam("#A");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        inh_.addEntry("A",new StringList("pkg.Simple"));
        mapp_.setMapping(inh_);
        assertTrue(AnaTemplates.isCorrectVars(mapp_,cont_.getAnalyzing()));
    }

    @Test
    public void isCorrectOrNumbersVars5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg("");
        mapp_.setParam(cont_.getAliasPrimInteger());
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        assertTrue(!AnaTemplates.isCorrectOrNumbersVars(mapp_,cont_.getAnalyzing()));
    }

    @Test
    public void isCorrectOrNumbersVars6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg(cont_.getAliasPrimInteger());
        mapp_.setParam(cont_.getAliasPrimInteger());
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        assertTrue(AnaTemplates.isCorrectOrNumbersVars(mapp_,cont_.getAnalyzing()));
    }

    @Test
    public void isCorrectOrNumbersVars7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>{\n");
        xml_.append(" $public $static ExTwo<T> $(Ex<T> p){$return $null;}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwo<U>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExTwoPar<W,X>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.ExSub<V>:ExTwo<V>{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple{\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Simple2{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethodsIds(files_);
        Mapping mapp_ = new Mapping();
        mapp_.setArg(cont_.getAliasPrimInteger());
        mapp_.setParam("");
        StringMap<StringList> inh_ = new StringMap<StringList>();
        mapp_.setMapping(inh_);
        assertTrue(!AnaTemplates.isCorrectOrNumbersVars(mapp_,cont_.getAnalyzing()));
    }
    @Test
    public void inhNb() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExParam<T,U:T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> res_ = AnaTemplates.inhNb(cont_.getAnalyzing().getAnaClassBody("pkg.ExParam"));
        assertEq(2,res_.size());
        assertEq(1,res_.getVal("0").size());
        assertEq(cont_.getAnalyzing().getAliasObject(), res_.getVal("0").get(0));
        assertEq(1,res_.getVal("1").size());
        assertEq("#0",res_.getVal("1").get(0));
    }

    @Test
    public void processConstraints_1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String geneNb_ = AnaTemplates.getGeneNb(cont_.getAnalyzing().getAnaClassBody("pkg.ExParam"));
        assertEq("",AnaTemplates.processConstraints(geneNb_,null,2, new StringMap<StringList>(),cont_.getAnalyzing()));
    }

    @Test
    public void processConstraints_2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExTwo{}\n");
        xml_.append("$public $class pkg.ExParam<T,U>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String geneNb_ = AnaTemplates.getGeneNb(cont_.getAnalyzing().getAnaClassBody("pkg.ExParam"));
        CustList<Matching> l_ = new CustList<Matching>();
        Matching mapp_ = new Matching();
        mapp_.setArg("pkg.Ex");
        mapp_.setParam("#0");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        mapp_ = new Matching();
        mapp_.setArg("pkg.ExTwo");
        mapp_.setParam("#1");
        mapp_.setMatchEq(MatchingEnum.EQ);
        l_.add(mapp_);
        assertEq("pkg.ExParam<pkg.Ex,pkg.ExTwo>",AnaTemplates.processConstraints(geneNb_,l_,2, new StringMap<StringList>(),cont_.getAnalyzing()));
    }

    @Test
    public void tryInferMethod1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>:ExSuper<T>{}\n");
        xml_.append("$public $class pkg.ExSuper<S>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExSuper",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#S>"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>:ExSuper<T>{}\n");
        xml_.append("$public $class pkg.ExSuper<S>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExSuper",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExSuper<#S>"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>:ExSuper<T>{}\n");
        xml_.append("$public $class pkg.ExSuper<S>{}\n");
        xml_.append("$public $class pkg.ExParam2<T2>:ExSuper2<T2>{}\n");
        xml_.append("$public $class pkg.ExSuper2<S2>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam2<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExSuper",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExSuper2<#S>"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(true),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        args_.add(new AnaClassArgumentMatching(""));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>",cont_.getAliasObject()), new BooleanList(false,false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("[pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }


    @Test
    public void tryInferMethod9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("[pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(true),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","pkg.ExParam<#T>"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(0, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","pkg.ExParam<#T>"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(0, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","pkg.ExParam<#T>"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("",inf_);
    }

    @Test
    public void tryInferMethod15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","pkg.ExParam<#T>"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("",inf_);
    }

    @Test
    public void tryInferMethod16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(2, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","pkg.ExParam<#T>"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethod17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","pkg.ExParam<#T>"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("",inf_);
    }

    @Test
    public void tryInferMethod18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(1, "",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","pkg.ExParam<#T>"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("",inf_);
    }

    @Test
    public void tryInferMethod19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","pkg.ExParam<#T>"), new BooleanList(false,false),
                        true),
                "",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("",inf_);
    }

    @Test
    public void tryInferMethod20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        args_.add(new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"));
        String inf_ = AnaTemplates.tryInferMethod(0, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","pkg.ExParam<#T>"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("",inf_);
    }

    @Test
    public void tryInferMethod21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>:ExSuper<T>{}\n");
        xml_.append("$public $class pkg.ExSuper<S>{}\n");
        xml_.append("$public $class pkg.ExParam2<T2>:ExSuper2<T2>{}\n");
        xml_.append("$public $class pkg.ExSuper2<S2>{}\n");
        xml_.append("$public $class pkg.ExCont<A>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching("pkg.ExParam2<#A>"));
        StringMap<StringList> vars_ = new StringMap<StringList>();
        vars_.addEntry("A",new StringList(cont_.getAliasObject()));
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExSuper",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExSuper2<#S>"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                vars_,
                args_,
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<#A>",inf_);
    }

    @Test
    public void tryInferMethod22() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        args_.add(new AnaClassArgumentMatching(cont_.getAliasString()));
        String inf_ = AnaTemplates.tryInferMethod(-1, cont_.getAliasString(),
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList(cont_.getAliasString()), new BooleanList(false),
                        false),
                cont_.getAliasString(),
                new StringMap<StringList>(),
                args_,
                "", "", cont_.getAnalyzing());
        assertEq(cont_.getAliasString(),inf_);
    }

    @Test
    public void tryInferMethodEv1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList(), new BooleanList(),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "pkg.ExParam<#T>", "pkg.ExParam<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethodEv2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>:ExSuper<T>{}\n");
        xml_.append("$public $class pkg.ExSuper<S>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList(), new BooleanList(),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "pkg.ExParam<#T>", "pkg.ExSuper<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethodEv3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>:ExSuper<T>{}\n");
        xml_.append("$public $class pkg.ExSuper<S>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExSuper",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList(), new BooleanList(),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "pkg.ExParam<#S>", "pkg.ExSuper<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethodEv4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>:ExSuper<T>{}\n");
        xml_.append("$public $class pkg.ExSuper<S>{}\n");
        xml_.append("$public $class pkg.ExParam2<T2>:ExSuper2<T2>{}\n");
        xml_.append("$public $class pkg.ExSuper2<S2>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExSuper",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList(), new BooleanList(),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "pkg.ExParam2<#S>", "pkg.ExSuper2<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }

    @Test
    public void tryInferMethodEv5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList(), new BooleanList(),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "pkg.ExParam<#T>", "", cont_.getAnalyzing());
        assertEq("",inf_);
    }

    @Test
    public void tryInferMethodEv6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        CustList<AnaClassArgumentMatching> args_ = new CustList<AnaClassArgumentMatching>();
        String inf_ = AnaTemplates.tryInferMethod(-1, "pkg.ExParam",
                new MethodId(true, MethodAccessKind.STATIC_CALL, "",
                        new StringList(), new BooleanList(),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                args_,
                "pkg.ExParam<#T>", "pkg.ExParam<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }


    @Test
    public void tryInferMethodByOneArg1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"),
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",-1,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("$int"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("$int"),
                "pkg.ExParam<#T>", "pkg.ExParam<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",1,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("$int"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("$int"),
                "pkg.ExParam<#T>", "pkg.ExParam<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(true),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"),
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.Ex"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching(""),
                "pkg.ExParam<#T>", "pkg.ExParam<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(true, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.Ex"), new BooleanList(false),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching(""),
                "pkg.ExParam<#T>", "pkg.ExParam<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(true),
                        false),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"),
                "pkg.ExParam<#T>", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"),
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("[pkg.ExParam<pkg.Ex>"),
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(true),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("[pkg.ExParam<pkg.Ex>"),
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",1,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>"), new BooleanList(false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"),
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","$int"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"),
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","$int"), new BooleanList(true,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching("pkg.ExParam<pkg.Ex>"),
                "", "", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",0,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","$int"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching(""),
                "pkg.ExParam<#T>", "pkg.ExParam<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",-1,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","$int"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching(""),
                "pkg.ExParam<#T>", "pkg.ExParam<pkg.Ex>", cont_.getAnalyzing());
        assertEq("pkg.ExParam<pkg.Ex>",inf_);
    }
    @Test
    public void tryInferMethodByOneArg16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("",-1,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","$int"), new BooleanList(false,false),
                        true),
                "pkg.ExParam",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching(""),
                "pkg.ExParam<#T>", "pkg.ExParam<pkg.Ex>", cont_.getAnalyzing());
        assertEq("",inf_);
    }
    @Test
    public void tryInferMethodByOneArg17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex{}\n");
        xml_.append("$public $class pkg.ExParam<T>{}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String inf_ = AnaTemplates.tryInferMethodByOneArg("pkg.ExParam",-1,
                new MethodId(false, MethodAccessKind.STATIC_CALL, "",
                        new StringList("pkg.ExParam<#T>","$int"), new BooleanList(false,false),
                        true),
                "",
                new StringMap<StringList>(),
                new AnaClassArgumentMatching(""),
                "pkg.ExParam<#T>", "pkg.ExParam<pkg.Ex>", cont_.getAnalyzing());
        assertEq("",inf_);
    }
    @Test
    public void getVarTypes() {
        StringMap<String> files_ = new StringMap<String>();
        unfullValidateOverridingMethods(files_);
        StringMap<String> vars_ = getVarTypes(null, "");
        assertEq(0, vars_.size());
    }

    private static StringMap<String> getVarTypes(RootBlock _root, String _s) {
        return AnaInherits.getVarTypes(_root, _s);
    }

    @Test
    public void getInferForm1Test() {
        assertNull(AnaTemplates.getInferForm("java.lang.Number"));
    }
    @Test
    public void getInferForm2Test() {
        assertNull(AnaTemplates.getInferForm("java.lang.Number>"));
    }
    @Test
    public void getInferForm3Test() {
        assertNull(AnaTemplates.getInferForm("java.lang[].Number<>"));
    }
    @Test
    public void getInferForm4Test() {
        assertEq("java.lang.$iterable",AnaTemplates.getInferForm("java.lang.$iterable<>"));
    }
    @Test
    public void getInferForm5Test() {
        assertEq("java.lang .$iterable",AnaTemplates.getInferForm("java.lang .$iterable<>"));
    }
    @Test
    public void getInferForm6Test() {
        assertEq("java.lang.ExClass..Inner",AnaTemplates.getInferForm("java.lang.ExClass..Inner<>"));
    }

    @Test
    public void isCorrect109Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("");
        m_.setParam("");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrectOrNumbers(context_, m_));
    }

    private static boolean isCorrectOrNumbers(AnalyzedTestContext _context, Mapping _m) {
        return AnaInherits.isCorrectOrNumbers(_m, _context.getAnalyzing());
    }

    @Test
    public void isCorrect1Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Object");
        m_.setParam("java.lang.Object");
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect2Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.String");
        m_.setParam("java.lang.Object");
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect3Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Object");
        m_.setParam("java.lang.String");
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect4Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$iterable<java.lang.Object>");
        m_.setParam("java.lang.$iterable<java.lang.Object>");
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect5Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$iterable<java.lang.String>");
        m_.setParam("java.lang.$iterable<java.lang.Object>");
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect6Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$iterable<java.lang.Object>");
        m_.setParam("java.lang.$iterable<java.lang.String>");
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect7Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.$Enum<#E>"));
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect8Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect9Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("[java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("[java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect10Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("[java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }











































































    @Test
    public void isCorrect11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<pkg.Ex<java.lang.Number>>");
        m_.setParam("java.lang.$Fct<pkg.ExTwo<java.lang.Number>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect12Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<pkg.ExTwo<java.lang.Number>,$int>");
        m_.setParam("java.lang.$Fct<pkg.Ex<java.lang.Number>,$int>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect13Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<$void>");
        m_.setParam("java.lang.$Fct<java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect14Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<java.lang.Object>");
        m_.setParam("java.lang.$Fct<$void>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect15_Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<$void>");
        m_.setParam("java.lang.$Fct<java.lang.Integer>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }
    @Test
    public void isCorrect15Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<$void>");
        m_.setParam("java.lang.$Fct<$void>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<pkg.Ex<java.lang.Number>,pkg.Ex<java.lang.Number>>");
        m_.setParam("java.lang.$Fct<pkg.ExTwo<java.lang.Number>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }
    @Test
    public void isCorrect17Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#E");
        m_.setParam("java.lang.Number");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("[java.lang.Integer"));
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect18Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<pkg.Ex<java.lang.Number>,pkg.Ex<java.lang.Number>>");
        m_.setParam("pkg.ExTwo<java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect19Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<pkg.Ex<java.lang.Number>,pkg.Ex<java.lang.Number>>");
        m_.setParam("java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }
    @Test
    public void isCorrect20Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam("#S");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }
    @Test
    public void isCorrect21Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#S");
        m_.setParam("#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Object");
        m_.setParam("$Fct<pkg.Ex<java.lang.Number>,pkg.Ex<java.lang.Number>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect23Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg(ARR_STRING);
        m_.setParam(ARR_OBJECT);
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect24Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?pkg.ExTwo>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect25Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.ExTwo>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }


    @Test
    public void isCorrect26Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<pkg.ExTwo>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect27Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.ExTwo>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect28Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect29Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect31Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect32Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!pkg.Ex>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?pkg.Ex>");
        m_.setParam("pkg.Param<pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect36Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_T);
        m_.setParam(ARR_VAR_S);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect37Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_S);
        m_.setParam(ARR_VAR_T);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect38Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_S);
        m_.setParam(ARR_VAR_T);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect39Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg(ARR_VAR_T);
        m_.setParam(ARR_VAR_S);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("#S"));
        t_.put("S", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }


    @Test
    public void isCorrect40Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!pkg.Ex>");
        m_.setParam("pkg.Param<pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }


    @Test
    public void isCorrect41Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?pkg.ExTwo>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect42Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<!pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.ExTwo>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect43Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<pkg.ExTwo>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect44Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.ExTwo>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect45Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect46Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<!pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect47Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?pkg.Ex>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect48Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?>");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect49Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }



















































    @Test
    public void isCorrect50Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#H");
        m_.setParam(context_.getAliasObject());
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("H", new StringList());
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect51Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<!pkg.Ex>");
        m_.setParam("pkg.Param<?pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }
    @Test
    public void isCorrect52Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.Ex");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect53Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?pkg.Ex>");
        m_.setParam("pkg.Param<!pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }
    @Test
    public void isCorrect54Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Number");
        m_.setParam("pkg.Ex");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect55Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect56Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect57Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect58Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect59Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect60Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect61Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect62Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex");
        m_.setParam("pkg.ExTwo");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect63Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<E> :pkg.Ex<E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.ExTwo<#T>");
        m_.setParam("pkg.Ex<#T>");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect64Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<E> :pkg.Ex<E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.Ex<#T>");
        m_.setParam("pkg.ExTwo<#T>");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect65Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<E> :pkg.Ex<E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.ExTwo<#T>");
        m_.setParam("pkg.Ex<#T>");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect66Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<E> :pkg.Ex<E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.Ex<#T>");
        m_.setParam("pkg.ExTwo<#T>");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect67Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<E> :pkg.Ex<E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("S", new StringList("java.lang.Object"));
        m_.setArg("pkg.ExTwo<#S>");
        m_.setParam("pkg.Ex<#S>");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect68Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<?pkg.Ex>");
        m_.setParam("pkg.Param<pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }
    @Test
    public void isCorrect69Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<E> :pkg.Ex<E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.getMapping().put("U", new StringList("#T"));
        m_.getMapping().put("V", new StringList("#U"));
        m_.setArg("#V");
        m_.setParam("#T");
        assertTrue(isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect70Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<E> :pkg.Ex<E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.getMapping().put("U", new StringList("#T"));
        m_.getMapping().put("V", new StringList("#U"));
        m_.getMapping().put("W", new StringList("#U"));
        m_.getMapping().put("X", new StringList("#V","#W"));
        m_.setArg("#X");
        m_.setParam("#T");
        assertTrue(isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect71Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamTwo<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamTwo<!pkg.Ex>");
        m_.setParam("pkg.Param<pkg.Ex>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }
    @Test
    public void isCorrect72Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<E> :pkg.Ex<E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.getMapping().put("U", new StringList("#T"));
        m_.getMapping().put("X", new StringList("#V","#W"));
        m_.setArg("#X");
        m_.setParam("#T");
        assertTrue(!isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect73Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<E> :pkg.Ex<E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("[java.lang.Object"));
        m_.setArg("[[pkg.ExTwo<#T>");
        m_.setParam("[pkg.Ex<#T>");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect74Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo<E> :pkg.Ex<E>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("[java.lang.Object"));
        m_.setArg("[pkg.Ex<#T>");
        m_.setParam("[[pkg.ExTwo<#T>");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect75Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex<java.lang.Object>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ExTwo");
        m_.setParam("pkg.Ex<java.lang.Object>");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect76Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.Ex<java.lang.Object>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("T", new StringList("java.lang.Object"));
        m_.setArg("pkg.Ex<java.lang.Object>");
        m_.setParam("pkg.ExTwo");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect77Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> :pkg.Ex<T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("pkg.ExTwo<java.lang.Number>"));
        m_.setArg("#U");
        m_.setParam("pkg.ExTwo<java.lang.Number>");
        assertTrue(isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect78Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> :pkg.Ex<T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("pkg.ExTwo<java.lang.Number>"));
        m_.setArg("#U");
        m_.setParam("pkg.Ex<java.lang.Number>");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect79Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> :pkg.Ex<T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("pkg.ExTwo<java.lang.Number>"));
        m_.setArg("[#U");
        m_.setParam("[pkg.ExTwo<java.lang.Number>");
        assertTrue(isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect80Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> :pkg.Ex<T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("pkg.ExTwo<java.lang.Number>"));
        m_.setArg("[#U");
        m_.setParam("[pkg.Ex<java.lang.Number>");
        assertTrue(isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect81Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> :pkg.Ex<T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U,V:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("java.lang.Object"));
        m_.getMapping().put("V", new StringList("pkg.ExTwo<#U>"));
        m_.setArg("[#V");
        m_.setParam("[pkg.Ex<#U>");
        assertTrue(isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect82Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> :pkg.Ex<T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U,V:pkg.ExTwo<java.lang.Number>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("java.lang.Object"));
        m_.getMapping().put("V", new StringList("pkg.ExTwo<#U>"));
        m_.setArg("[#V");
        m_.setParam("[pkg.ExThree<#U>");
        assertTrue(!isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect83Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<?,?>");
        m_.setParam("java.lang.$Fct<?,?>");
        assertTrue(isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect84Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<?,java.lang.Number>");
        m_.setParam("java.lang.$Fct<?,?>");
        assertTrue(isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect85Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<java.lang.Number,?>");
        m_.setParam("java.lang.$Fct<?,?>");
        assertTrue(isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect86Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<?,?>");
        m_.setParam("java.lang.$Fct<?,java.lang.Number>");
        assertTrue(!isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect87Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<?,?>");
        m_.setParam("java.lang.$Fct<java.lang.Number,?>");
        assertTrue(!isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect88Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E,F> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Ex<?java.lang.Integer,java.lang.Integer>");
        m_.setParam("pkg.Ex<?java.lang.Number,java.lang.Number>");
        assertTrue(!isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect89Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> :pkg.Ex<T>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<U,V:pkg.ExTwo<U>>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.getMapping().put("U", new StringList("java.lang.Object"));
        m_.getMapping().put("V", new StringList("pkg.ExTwo<#U>"));
        m_.setArg("[#V");
        m_.setParam("[pkg.ExThree<#U>");
        assertTrue(!isCorrect(cont_, m_));
    }

    @Test
    public void isCorrect90Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<$void>");
        m_.setParam("java.lang.$Fct");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect91Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct");
        m_.setParam("java.lang.$Fct<java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect92Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct");
        m_.setParam("java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }
    @Test
    public void isCorrect93Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("#T");
        m_.setParam("[#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }
    @Test
    public void isCorrect94Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("[#T");
        m_.setParam("#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }
    @Test
    public void isCorrect95Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.Number");
        m_.setParam("#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }
    @Test
    public void isCorrect96Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("$int");
        m_.setParam("pkg.Ex<java.lang.Number>");
        assertTrue(!isCorrect(cont_, m_));
    }
    @Test
    public void isCorrect97Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        Mapping m_ = new Mapping();
        m_.setArg("");
        m_.setParam("#T");
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("T", new StringList("java.lang.Object"));
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    private static boolean isCorrect(AnalyzedTestContext _context, Mapping _m) {
        return AnaInherits.isCorrect(_m, _context.getAnalyzing());
    }

    @Test
    public void isCorrect98Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?$int>");
        m_.setParam("pkg.Param<?java.lang.Integer>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }
    @Test
    public void isCorrect99Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<?$int>");
        m_.setParam("pkg.Param<?$long>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }
    @Test
    public void isCorrect100Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!java.lang.Integer>");
        m_.setParam("pkg.Param<!$int>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }
    @Test
    public void isCorrect101Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.Param<!$long>");
        m_.setParam("pkg.Param<!$int>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }
    @Test
    public void isCorrect102Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Param<T>{}\n");
        xml_.append("$public $class pkg.ParamSub<S>:Param<S>{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("pkg.ParamSub");
        m_.setParam("pkg.Param<?>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(isCorrect(context_, m_));
    }

    @Test
    public void isCorrect103Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("[java.lang.$Fct<pkg.Ex<java.lang.Number>,pkg.Ex<java.lang.Number>>");
        m_.setParam("[pkg.ExTwo<java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect104Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T>:ExTwo<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<S> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("[pkg.ExTwo<java.lang.Object>");
        m_.setParam("[java.lang.$Fct<pkg.Ex<java.lang.Number>,pkg.Ex<java.lang.Number>>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect105Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<java.lang.Object>");
        m_.setParam("java.lang.$Fct<$void>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect106Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("java.lang.$Fct<java.lang.Object>");
        m_.setParam("[java.lang.Object");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect107Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("[[java.lang.$Fct<java.lang.Object>");
        m_.setParam("[java.lang.$Fct<java.lang.Object>");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void isCorrect108Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        Mapping m_ = new Mapping();
        m_.setArg("");
        m_.setParam("");
        StringMap<StringList> t_ = new StringMap<StringList>();
        m_.setMapping(t_);
        assertTrue(!isCorrect(context_, m_));
    }

    @Test
    public void getSuperTypesSet1Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("$boolean"));
        assertEq(3, res_.size());
        assertTrue(StringUtil.contains(res_, "$boolean"));
        assertTrue(StringUtil.contains(res_, "java.lang.Boolean"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet2Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("$byte"));
        assertEq(10, res_.size());
        assertTrue(StringUtil.contains(res_, "$byte"));
        assertTrue(StringUtil.contains(res_, "java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "$long"));
        assertTrue(StringUtil.contains(res_, "java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "$int"));
        assertTrue(StringUtil.contains(res_, "java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "$short"));
        assertTrue(StringUtil.contains(res_, "java.lang.Short"));
        assertTrue(StringUtil.contains(res_, "java.lang.Byte"));
    }
    @Test
    public void getSuperTypesSet3Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("$char"));
        assertEq(8, res_.size());
        assertTrue(StringUtil.contains(res_, "$char"));
        assertTrue(StringUtil.contains(res_, "java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "$long"));
        assertTrue(StringUtil.contains(res_, "java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "$int"));
        assertTrue(StringUtil.contains(res_, "java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "java.lang.Character"));
    }
    @Test
    public void getSuperTypesSet4Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("java.lang.Boolean"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "java.lang.Boolean"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet5Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("java.lang.Byte"));
        assertEq(6, res_.size());
        assertTrue(StringUtil.contains(res_, "java.lang.Byte"));
        assertTrue(StringUtil.contains(res_, "java.lang.Short"));
        assertTrue(StringUtil.contains(res_, "java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet6Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("java.lang.Character"));
        assertEq(5, res_.size());
        assertTrue(StringUtil.contains(res_, "java.lang.Character"));
        assertTrue(StringUtil.contains(res_, "java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet7Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[$boolean"));
        assertEq(4, res_.size());
        assertTrue(StringUtil.contains(res_, "[$boolean"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Boolean"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet8Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[$byte"));
        assertEq(11, res_.size());
        assertTrue(StringUtil.contains(res_, "[$byte"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[$long"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "[$int"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "[$short"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Short"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Byte"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet9Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[$char"));
        assertEq(9, res_.size());
        assertTrue(StringUtil.contains(res_, "[$char"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[$long"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "[$int"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Character"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet10Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[java.lang.Boolean"));
        assertEq(3, res_.size());
        assertTrue(StringUtil.contains(res_, "[java.lang.Boolean"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet11Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[java.lang.Byte"));
        assertEq(7, res_.size());
        assertTrue(StringUtil.contains(res_, "[java.lang.Byte"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Short"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet12Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[java.lang.Character"));
        assertEq(6, res_.size());
        assertTrue(StringUtil.contains(res_, "[java.lang.Character"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("pkg.ExThree"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "pkg.ExThree"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("pkg.ExTwo"));
        assertEq(3, res_.size());
        assertTrue(StringUtil.contains(res_, "pkg.ExTwo"));
        assertTrue(StringUtil.contains(res_, "pkg.ExThree"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet15Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("[pkg.ExThree"));
        assertEq(3, res_.size());
        assertTrue(StringUtil.contains(res_, "[pkg.ExThree"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet16Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("[pkg.ExTwo"));
        assertEq(4, res_.size());
        assertTrue(StringUtil.contains(res_, "[pkg.ExTwo"));
        assertTrue(StringUtil.contains(res_, "[pkg.ExThree"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet17Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("#T"));
        assertEq(3, res_.size());
        assertTrue(StringUtil.contains(res_, "#T"));
        assertTrue(StringUtil.contains(res_, "java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet18Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("S", new StringList("#T"));
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("#S"));
        assertEq(4, res_.size());
        assertTrue(StringUtil.contains(res_, "#S"));
        assertTrue(StringUtil.contains(res_, "#T"));
        assertTrue(StringUtil.contains(res_, "java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet19Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("[#T"));
        assertEq(4, res_.size());
        assertTrue(StringUtil.contains(res_, "[#T"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet20Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("S", new StringList("#T"));
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("[#S"));
        assertEq(5, res_.size());
        assertTrue(StringUtil.contains(res_, "[#S"));
        assertTrue(StringUtil.contains(res_, "[#T"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet21Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("#T"));
        assertEq(4, res_.size());
        assertTrue(StringUtil.contains(res_, "#T"));
        assertTrue(StringUtil.contains(res_, "pkg.ExTwo"));
        assertTrue(StringUtil.contains(res_, "pkg.ExThree"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet22Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("[#T"));
        assertEq(5, res_.size());
        assertTrue(StringUtil.contains(res_, "[#T"));
        assertTrue(StringUtil.contains(res_, "[pkg.ExTwo"));
        assertTrue(StringUtil.contains(res_, "[pkg.ExThree"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet23Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[[$boolean"));
        assertEq(5, res_.size());
        assertTrue(StringUtil.contains(res_, "[[$boolean"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Boolean"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet24Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[[$byte"));
        assertEq(12, res_.size());
        assertTrue(StringUtil.contains(res_, "[[$byte"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[[$long"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "[[$int"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "[[$short"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Short"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Byte"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet25Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[[$char"));
        assertEq(10, res_.size());
        assertTrue(StringUtil.contains(res_, "[[$char"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[[$long"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "[[$int"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Character"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet26Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[[java.lang.Boolean"));
        assertEq(4, res_.size());
        assertTrue(StringUtil.contains(res_, "[[java.lang.Boolean"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet27Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[[java.lang.Byte"));
        assertEq(8, res_.size());
        assertTrue(StringUtil.contains(res_, "[[java.lang.Byte"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Short"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }

    @Test
    public void getSuperTypesSet28Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringList res_ = getSuperTypesSet(c_, new StringMap<StringList>(), new StringList("[[java.lang.Character"));
        assertEq(7, res_.size());
        assertTrue(StringUtil.contains(res_, "[[java.lang.Character"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Integer"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Long"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet29Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("[[pkg.ExThree"));
        assertEq(4, res_.size());
        assertTrue(StringUtil.contains(res_, "[[pkg.ExThree"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet30Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("[[pkg.ExTwo"));
        assertEq(5, res_.size());
        assertTrue(StringUtil.contains(res_, "[[pkg.ExTwo"));
        assertTrue(StringUtil.contains(res_, "[[pkg.ExThree"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet31Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("[[#T"));
        assertEq(5, res_.size());
        assertTrue(StringUtil.contains(res_, "[[#T"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet32Test() {
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(new StringMap<String>());
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("S", new StringList("#T"));
        map_.put("T", new StringList("java.lang.Number"));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("[[#S"));
        assertEq(6, res_.size());
        assertTrue(StringUtil.contains(res_, "[[#S"));
        assertTrue(StringUtil.contains(res_, "[[#T"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Number"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet33Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("[[#T"));
        assertEq(6, res_.size());
        assertTrue(StringUtil.contains(res_, "[[#T"));
        assertTrue(StringUtil.contains(res_, "[[pkg.ExTwo"));
        assertTrue(StringUtil.contains(res_, "[[pkg.ExThree"));
        assertTrue(StringUtil.contains(res_, "[[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "[java.lang.Object"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet34Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> :pkg.ExThree<$iterable<?T>>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList(c_.getAliasObject()));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("pkg.ExTwo<?T>"));
        assertEq(5, res_.size());
        assertTrue(StringUtil.contains(res_, "pkg.ExTwo<?>"));
        assertTrue(StringUtil.contains(res_, "pkg.ExThree<?>"));
        assertTrue(StringUtil.contains(res_, "pkg.ExTwo<?T>"));
        assertTrue(StringUtil.contains(res_, "pkg.ExThree<java.lang.$iterable<?T>>"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }
    @Test
    public void getSuperTypesSet35Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T> :pkg.ExThree<T>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree<S>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("U", new StringList(c_.getAliasObject()));
        StringList res_ = getSuperTypesSet(c_, map_, new StringList("pkg.ExTwo<#U>"));
        assertEq(5, res_.size());
        assertTrue(StringUtil.contains(res_, "pkg.ExTwo<?>"));
        assertTrue(StringUtil.contains(res_, "pkg.ExThree<?>"));
        assertTrue(StringUtil.contains(res_, "pkg.ExTwo<#U>"));
        assertTrue(StringUtil.contains(res_, "pkg.ExThree<#U>"));
        assertTrue(StringUtil.contains(res_, "java.lang.Object"));
    }

    @Test
    public void getTernarySubclasses1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("pkg.ExTwo", "pkg.ExThree"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "pkg.ExTwo"));
        assertTrue(StringUtil.contains(res_, "pkg.ExThree"));
    }

    @Test
    public void getTernarySubclasses2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("pkg.ExTwo","pkg.ExThree","pkg.ExFour","pkg.ExFive"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "pkg.ExTwo"));
        assertTrue(StringUtil.contains(res_, "pkg.ExThree"));
    }
    @Test
    public void getTernarySubclasses3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("pkg.ExTwo", "pkg.ExThree"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "pkg.ExTwo"));
        assertTrue(StringUtil.contains(res_, "pkg.ExThree"));
    }
    @Test
    public void getTernarySubclasses4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("pkg.ExThree"));
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("#T","#S"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "#S"));
        assertTrue(StringUtil.contains(res_, "#T"));
    }
    @Test
    public void getTernarySubclasses5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("pkg.ExThree"));
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("#T","#S","pkg.ExTwo"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "#S"));
        assertTrue(StringUtil.contains(res_, "#T"));
    }
    @Test
    public void getTernarySubclasses6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("#T","#S","pkg.ExTwo"));
        assertEq(1, res_.size());
        assertTrue(StringUtil.contains(res_, "#S"));
    }
    @Test
    public void getTernarySubclasses7Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("pkg.ExThree"));
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("[#T","[#S"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "[#S"));
        assertTrue(StringUtil.contains(res_, "[#T"));
    }
    @Test
    public void getTernarySubclasses8Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("pkg.ExThree"));
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("[#T","[#S","[pkg.ExTwo"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "[#S"));
        assertTrue(StringUtil.contains(res_, "[#T"));
    }
    @Test
    public void getTernarySubclasses9Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("[#T","[#S","[pkg.ExTwo"));
        assertEq(1, res_.size());
        assertTrue(StringUtil.contains(res_, "[#S"));
    }
    @Test
    public void getTernarySubclasses10Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("[[#T","[#S","[pkg.ExTwo"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "[#S"));
        assertTrue(StringUtil.contains(res_, "[[#T"));
    }
    @Test
    public void getTernarySubclasses11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList res_ = getTernarySubclasses(c_, map_, new StringList("[#T","[[#S","[pkg.ExTwo"));
        assertEq(2, res_.size());
        assertTrue(StringUtil.contains(res_, "[[#S"));
        assertTrue(StringUtil.contains(res_, "[#T"));
    }
    @Test
    public void getResultTernary0Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Integer");
        Argument argOne_ = new Argument(new IntStruct(0));
        Argument argTwo_ = new Argument(new IntStruct(0));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }

    @Test
    public void getResultTernary1Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Integer");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary2Test() {
        StringList one_ = new StringList("java.lang.Integer");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary3Test() {
        StringList one_ = new StringList("");
        StringList two_ = new StringList("java.lang.Integer");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Integer", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary4Test() {
        StringList one_ = new StringList("java.lang.Integer");
        StringList two_ = new StringList("");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Integer", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary5Test() {
        StringList one_ = new StringList("");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Integer", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary6Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Integer", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary7Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary8Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary9Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$short");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary10Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary11Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary12Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary13Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$short");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary14Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary15Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$byte", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary16Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$byte", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary17Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary18Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary19Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$char");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary20Test() {
        StringList one_ = new StringList("$char");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary21Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary22Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary23Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary24Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary25Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary26Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary27Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary28Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary29Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$char");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary30Test() {
        StringList one_ = new StringList("$char");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary31Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary32Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary33Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary34Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary35Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$char");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary36Test() {
        StringList one_ = new StringList("$char");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary37Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary38Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary39Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary40Test() {
        StringList one_ = new StringList("$short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary41Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$char");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary42Test() {
        StringList one_ = new StringList("$char");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary43Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$char");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(80000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary44Test() {
        StringList one_ = new StringList("$char");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(80000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary45Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$byte", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary46Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$byte", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary47Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary48Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary49Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Character");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary50Test() {
        StringList one_ = new StringList("java.lang.Character");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary51Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary52Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary53Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary54Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary55Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary56Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary57Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary58Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$short", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary59Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Character");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-129));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary60Test() {
        StringList one_ = new StringList("java.lang.Character");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-129));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary61Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary62Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary63Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary64Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary65Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Character");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary66Test() {
        StringList one_ = new StringList("java.lang.Character");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$char", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary67Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary68Test() {
        StringList one_ = new StringList("java.lang.Byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary69Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Short");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary70Test() {
        StringList one_ = new StringList("java.lang.Short");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary71Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Character");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(-40000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary72Test() {
        StringList one_ = new StringList("java.lang.Character");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(-40000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary73Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Character");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new IntStruct(80000));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary74Test() {
        StringList one_ = new StringList("java.lang.Character");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new IntStruct(80000));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary75Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary76Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("$int", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary77Test() {
        StringList one_ = new StringList("$double");
        StringList two_ = new StringList("$byte");
        Argument argOne_ = new Argument();
        argOne_.setStruct(new DoubleStruct(1));
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Number", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary78Test() {
        StringList one_ = new StringList("$byte");
        StringList two_ = new StringList("$double");
        Argument argOne_ = null;
        Argument argTwo_ = new Argument();
        argTwo_.setStruct(new DoubleStruct(1));
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Number", res_.getTypes().first());
        assertTrue(res_.isUnwrapFirst());
        assertTrue(res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary79Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("pkg.ExTwo");
        StringList two_ = new StringList("pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "pkg.ExFour"));
        assertTrue(StringUtil.contains(res_.getTypes(), "pkg.ExFive"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary80Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("[pkg.ExTwo");
        StringList two_ = new StringList("[pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "[pkg.ExFour"));
        assertTrue(StringUtil.contains(res_.getTypes(), "[pkg.ExFive"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary81Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        StringList one_ = new StringList("#T");
        StringList two_ = new StringList("pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "pkg.ExFour"));
        assertTrue(StringUtil.contains(res_.getTypes(), "pkg.ExFive"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary82Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        StringList one_ = new StringList("[pkg.ExTwo");
        StringList two_ = new StringList("[pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "[pkg.ExFour"));
        assertTrue(StringUtil.contains(res_.getTypes(), "[pkg.ExFive"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary83Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList one_ = new StringList("#T");
        StringList two_ = new StringList("#S");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "#T"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary84Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        StringList one_ = new StringList("[#T");
        StringList two_ = new StringList("[#S");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "[#T"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary85Test() {
        StringList one_ = new StringList("java.lang.Integer");
        StringList two_ = new StringList("java.lang.Integer");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Integer", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary86Test() {
        StringList one_ = new StringList("$int");
        StringList two_ = new StringList("java.lang.Number");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Number", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary87Test() {
        StringList one_ = new StringList("java.lang.Number");
        StringList two_ = new StringList("$int");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Number", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary88Test() {
        StringList one_ = new StringList("java.lang.Integer");
        StringList two_ = new StringList("java.lang.Number");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Number", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary89Test() {
        StringList one_ = new StringList("java.lang.Number");
        StringList two_ = new StringList("java.lang.Integer");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.Number", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary90Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour<java.lang.Number>:pkg.ExFive<java.lang.Object>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour<java.lang.Number>:pkg.ExFive<java.lang.Object>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive<U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("pkg.ExTwo");
        StringList two_ = new StringList("pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "pkg.ExFour<java.lang.Number>"));
        assertTrue(StringUtil.contains(res_.getTypes(), "pkg.ExFive<java.lang.Object>"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary91Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour<java.lang.Number>:pkg.ExFive<java.lang.Number>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour<java.lang.Number>:pkg.ExFive<java.lang.Object>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive<U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("pkg.ExTwo");
        StringList two_ = new StringList("pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "pkg.ExFour<java.lang.Number>"));
        assertTrue(StringUtil.contains(res_.getTypes(), "pkg.ExFive<?>"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary92Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour<java.lang.Object>:pkg.ExFive<java.lang.Number>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour<java.lang.Number>:pkg.ExFive<java.lang.Object>{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour<T> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive<U> {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("pkg.ExTwo");
        StringList two_ = new StringList("pkg.ExThree");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(2, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "pkg.ExFour<?>"));
        assertTrue(StringUtil.contains(res_.getTypes(), "pkg.ExFive<?>"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary93Test() {
        StringList one_ = new StringList("java.lang.$Fct<java.lang.Number>");
        StringList two_ = new StringList("java.lang.$Fct<java.lang.Integer>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.$Fct<java.lang.Number>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary94Test() {
        StringList one_ = new StringList("java.lang.$Fct<java.lang.Integer>");
        StringList two_ = new StringList("java.lang.$Fct<java.lang.Number>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.$Fct<java.lang.Number>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary95Test() {
        StringList one_ = new StringList("java.lang.$Fct<#T>");
        StringList two_ = new StringList("java.lang.$Fct<#U>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        map_.put("U", new StringList("#T"));
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.$Fct<#T>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary96Test() {
        StringList one_ = new StringList("java.lang.$Fct<#U>");
        StringList two_ = new StringList("java.lang.$Fct<#T>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        map_.put("U", new StringList("#T"));
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.$Fct<#T>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary97Test() {
        StringList one_ = new StringList("java.lang.$Fct<#T>");
        StringList two_ = new StringList("java.lang.$Fct<java.lang.Number>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.$Fct<java.lang.Number>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary98Test() {
        StringList one_ = new StringList("java.lang.$Fct<java.lang.Number>");
        StringList two_ = new StringList("java.lang.$Fct<#T>");
        Argument argOne_ = null;
        Argument argTwo_ = null;
        StringMap<String> files_ = new StringMap<String>();
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("java.lang.Number"));
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertEq("java.lang.$Fct<java.lang.Number>", res_.getTypes().first());
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary99Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList("pkg.ExTwo"));
        map_.put("S", new StringList("#T"));
        map_.put("U", new StringList("#T"));
        map_.put("V", new StringList("#S","#U"));
        map_.put("W", new StringList("#V"));
        StringList one_ = new StringList("#W");
        StringList two_ = new StringList("#V");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "#V"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary100Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExFour:pkg.ExFive{\n");
        xml_.append("}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive {\n");
        xml_.append("}\n");
        files_.put("pkg/ExFive", xml_.toString());
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put("T", new StringList());
        map_.put("S", new StringList("#T"));
        map_.put("U", new StringList("#T"));
        map_.put("V", new StringList("#S","#U"));
        map_.put("W", new StringList("#V"));
        StringList one_ = new StringList("#W");
        StringList two_ = new StringList("#V");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "#V"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }
    @Test
    public void getResultTernary101Test() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext c_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> map_ = new StringMap<StringList>();
        StringList one_ = new StringList("[java.lang.CharSequence");
        StringList two_ = new StringList("[[java.lang.Object");
        Argument argOne_ = null;
        Argument argTwo_ =  null;
        ResultTernary res_ = getResultTernary(one_, two_, argOne_, argTwo_, map_, c_);
        assertEq(1, res_.getTypes().size());
        assertTrue(StringUtil.contains(res_.getTypes(), "[java.lang.Object"));
        assertTrue(!res_.isUnwrapFirst());
        assertTrue(!res_.isUnwrapSecond());
    }

    @Test
    public void wildCardFormat1Test() {
        AnalyzedTestContext context_ = simpleContextEl();
        String first_ = context_.getAliasString();
        String second_ = context_.getAliasInteger();
        assertEq(second_, wildCardFormatReturn(context_, first_, second_));
    }


    @Test
    public void wildCardFormat2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<#T>";
        assertEq("pkg.Ex<?>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<?#T>";
        assertEq("pkg.Ex<?>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat4Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<?>", wildCardFormatReturn(context_, first_, second_));
    }

    private static String wildCardFormatReturn(AnalyzedTestContext _context, String _first, String _second) {
        return AnaInherits.wildCardFormatReturn(_first, _second, _context.getAnalyzing());
    }


    @Test
    public void wildCardFormat5Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat6Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<?>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat7Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<java.lang.Object>", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void wildCardFormat8Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<java.lang.Object>", wildCardFormatParam(context_, first_, second_));
    }

    private static String wildCardFormatParam(AnalyzedTestContext _context, String _first, String _second) {
        return AnaInherits.wildCardFormatParam(_first, _second, _context.getAnalyzing());
    }


    @Test
    public void wildCardFormat9Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<#E>";
        String second_ = "#T";
        assertEq("#E",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat10Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String>";
        String second_ = "#T";
        assertEq("java.lang.String",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat11Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#U";
        assertEq("java.lang.Object",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat12Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#T";
        assertEq("java.lang.String",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat13Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "#T";
        assertEq("java.lang.String",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat14Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "[#T";
        assertEq("[java.lang.String",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat15Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "code.util.CustList<#V>";
        assertEq("code.util.CustList<#V>",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat16Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:java.lang.Number,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number,java.lang.Number>";
        String second_ = "code.util.CustList<#T>";
        assertEq("code.util.CustList<java.lang.Number>",wildCardFormatReturn(cont_, first_, second_));
    }
    @Test
    public void wildCardFormat17Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<E:java.lang.Number,F> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<T:java.lang.Number,U> {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.ExTwo<java.lang.Number,java.lang.Number>";
        String second_ = "pkg.Ex<#T,#T>";
        assertEq("pkg.Ex<java.lang.Number,java.lang.Number>",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat18Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<#T>";
        assertEq("pkg.Ex<?java.lang.Number>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat19Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<?#T>";
        assertEq("pkg.Ex<?java.lang.Number>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat20Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<?>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat21Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?[java.lang.Number>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat22Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<?>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat23Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<!java.lang.Number>", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void wildCardFormat24Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<![java.lang.Number>", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void wildCardFormat25Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<#T>";
        assertEq("pkg.Ex<!java.lang.Number>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat26Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<?#T>";
        assertEq("pkg.Ex<?>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat27Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<!java.lang.Number>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat28Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat29Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<![java.lang.Number>", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat30Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<!#T>";
        assertEq("pkg.Ex<java.lang.Object>", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void wildCardFormat31Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<?#T>";
        assertEq("pkg.Ex<?java.lang.Number>", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void wildCardFormat32Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<![#T>";
        assertEq("pkg.Ex<java.lang.Object>", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void wildCardFormat33Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<?[#T>";
        assertEq("pkg.Ex<?[java.lang.Number>", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void wildCardFormat34Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "pkg.Ex<#T>";
        assertEq("", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void wildCardFormat35Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "pkg.Ex<#T>";
        assertEq("", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void wildCardFormat36Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "pkg.Ex<#T>";
        assertEq("", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void wildCardFormat37Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "#T";
        assertEq("java.lang.Object",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat38Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "[#T";
        assertEq("[java.lang.Object",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat39Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "#T";
        assertEq("java.lang.Number",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat40Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "[#T";
        assertEq("[java.lang.Number",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat41Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "#T";
        assertEq("java.lang.Object",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat42Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "[#T";
        assertEq("[java.lang.Object",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat43Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "#T";
        assertEq("",wildCardFormatParam(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat44Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?>";
        String second_ = "[#T";
        assertEq("",wildCardFormatParam(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat45Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "#T";
        assertEq("",wildCardFormatParam(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat46Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?java.lang.Number>";
        String second_ = "[#T";
        assertEq("",wildCardFormatParam(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat47Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "#T";
        assertEq("java.lang.Number",wildCardFormatParam(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat48Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "[#T";
        assertEq("[java.lang.Number",wildCardFormatParam(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat49Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "#V";
        assertEq("",wildCardFormatParam(cont_, first_, second_));
    }
    @Test
    public void wildCardFormat50Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.Number>";
        String second_ = "#T";
        assertEq("java.lang.Number",wildCardFormatParam(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat51Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<!java.lang.Number>";
        String second_ = "#V";
        assertEq("java.lang.Object",wildCardFormatReturn(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat52Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T,U> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<java.lang.String,java.lang.Object>";
        String second_ = "code.util.CustList<#V>";
        assertEq("code.util.CustList<#V>",wildCardFormatParam(cont_, first_, second_));
    }

    @Test
    public void wildCardFormat53Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex<?#E>";
        String second_ = "[#T";
        assertEq("[#E", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat54Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex";
        String second_ = "pkg.Ex<[#T>";
        assertEq("java.lang.Object", wildCardFormatReturn(context_, first_, second_));
    }

    @Test
    public void wildCardFormat55Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        StringMap<String> files_ = new StringMap<String>();
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext context_ = unfullValidateOverridingMethods(files_);
        String first_ = "pkg.Ex";
        String second_ = "pkg.Ex<[#T>";
        assertEq("", wildCardFormatParam(context_, first_, second_));
    }

    @Test
    public void isCorrectTemplate48Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "java.lang.$Fct"));
    }
    @Test
    public void isCorrectTemplate49Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "java.lang.$Fct<?>"));
    }
    @Test
    public void isCorrectTemplate50Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "java.lang.$Fct<?,?>"));
    }
    @Test
    public void isCorrectTemplate51Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex"));
    }

    @Test
    public void isCorrectTemplate52Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex"));
    }

    @Test
    public void isCorrectTemplate53Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex"));
    }

    @Test
    public void isCorrectTemplate54Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex[]"));
    }

    @Test
    public void isCorrectTemplate55Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex[]"));
    }

    @Test
    public void isCorrectTemplate56Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex[]"));
    }

    @Test
    public void isCorrectTemplate57Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<java.lang.Object>"));
    }

    @Test
    public void isCorrectTemplate58Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<java.lang.Object>"));
    }

    @Test
    public void isCorrectTemplate59Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<java.lang.Object>[]"));
    }

    @Test
    public void isCorrectTemplate60Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<java.lang.Object>[]"));
    }

    @Test
    public void isCorrectTemplate61Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>[]"));
    }

    @Test
    public void isCorrectTemplate62Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>[]"));
    }

    @Test
    public void isCorrectTemplate63Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>[]"));
    }

    @Test
    public void isCorrectTemplate64Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Integer"));
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>[]"));
    }

    @Test
    public void isCorrectTemplate65Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>[]"));
    }

    @Test
    public void isCorrectTemplate66Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("java.lang.Object"));
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>[]"));
    }

    @Test
    public void isCorrectTemplate67Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<java.lang.Object>[]"));
    }

    @Test
    public void isCorrectTemplate68Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<java.lang.Object>[]"));
    }

    @Test
    public void isCorrectTemplate70Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<java.lang.Integer>[]"));
    }

    @Test
    public void isCorrectTemplate71Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<java.lang.Object>[]"));
    }

    @Test
    public void isCorrectTemplate72Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<java.lang.Object>[]"));
    }

    @Test
    public void isCorrectTemplate73Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>[]"));
    }

    @Test
    public void isCorrectTemplate74Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>[]"));
    }

    @Test
    public void isCorrectTemplate75Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExTwo{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>[]"));
    }

    @Test
    public void isCorrectTemplate76Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExTwo{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>[]"));
    }

    @Test
    public void isCorrectTemplate77Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExTwo{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<pkg.ExTwo>[]"));
    }

    @Test
    public void isCorrectTemplate78Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree :pkg.ExTwo{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<pkg.ExTwo>[]"));
    }

    @Test
    public void isCorrectTemplate79Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> {}\n");
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.ExTwo<pkg.Ex<java.lang.String>>"));
    }

    @Test
    public void isCorrectTemplate80Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U> {}\n");
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAll(cont_, t_, "pkg.ExTwo<java.lang.Number,java.lang.String>", false));
    }

    private static boolean isCorrectTemplateAll(AnalyzedTestContext _cont, StringMap<StringList> _t, String _className, boolean _exact) {
        return process(_cont, _t, _className, _exact);
    }

    private static boolean process(AnalyzedTestContext _cont, StringMap<StringList> _t, String _className, boolean _exact) {
        AnaResultPartType resType_;
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.getCurrentBadIndexes().clear();
        page_.getAvailableVariables().clear();
        for (EntryCust<String,StringList> s: _t.entryList()) {
            page_.getAvailableVariables().addEntry(s.getKey(),0);
        }
        if (_exact) {
            resType_ = AnaPartTypeUtil.processAccessAnalyze(_className, false,"", null,null, -1,new CustList<PartOffset>(), page_);
        } else {
            resType_ = AnaPartTypeUtil.processAnalyzeLine(_className, "", null,null, -1,new CustList<PartOffset>(), page_);
        }
        assertTrue(!resType_.getResult().isEmpty());
        return AnaPartTypeUtil.processAnalyzeConstraints(resType_, _t, _exact,page_);
    }

    @Test
    public void isCorrectTemplate81Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAll(cont_, t_, "pkg.Ex<java.lang.Number>", false));
    }

    @Test
    public void isCorrectTemplate82Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T:java.lang.Number> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAll(cont_, t_, "pkg.Ex<java.lang.String>", false));
    }

    @Test
    public void isCorrectTemplate83Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAll(cont_, t_, "pkg.Ex<pkg.Ex>", false));
    }
    @Test
    public void isCorrectTemplate84Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        xml_.append("$public $class pkg.ExTwo<U,S> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAll(cont_, t_, "pkg.ExTwo<pkg.Ex,pkg.Ex<java.lang.Object>>", false));
    }
    @Test
    public void isCorrectTemplate85Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex<T> {}\n");
        xml_.append("$public $class pkg.ExTwo<U> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAll(cont_, t_, "pkg.ExTwo<pkg.Ex<java.lang.Object>>", true));
    }
    @Test
    public void isCorrectTemplateAll1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:pkg.ExTwo> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "java.lang.Object"));
    }

    @Test
    public void isCorrectTemplateAll2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:pkg.ExTwo> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, cont_.getAliasPrimInteger()));
    }

    @Test
    public void isCorrectTemplateAll3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:pkg.ExTwo> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "java.lang.Object[]"));
    }

    @Test
    public void isCorrectTemplateAll4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:pkg.ExTwo> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "$int[]"));
    }

    @Test
    public void isCorrectTemplateAll11Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:pkg.ExTwo> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>"));
    }

















    @Test
    public void isCorrectTemplateAll13Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:pkg.ExThree> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo :pkg.ExThree{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        t_.put("E", new StringList("pkg.ExTwo"));
        assertTrue(isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<E>"));
    }

    @Test
    public void isCorrectTemplateAll14Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex<T:pkg.ExTwo<?T>> {}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.ExTwo<U>{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(!isCorrectTemplateAllExact(cont_, t_, "pkg.Ex<pkg.ExTwo<?pkg.ExTwo<?java.lang.Number>>>"));
    }


    @Test
    public void getCorrectTemplateAll1() {
        StringMap<String> files_ = new StringMap<String>();
        AnalyzedTestContext cont_ = unfullValidateOverridingMethods(files_);
        StringMap<StringList> t_ = new StringMap<StringList>();
        assertTrue(getCorrectTemplateAll(cont_, t_).isEmpty());
    }

    private static String getCorrectTemplateAll(AnalyzedTestContext _cont, StringMap<StringList> _t) {
        return AnaInherits.getCorrectTemplateAll("",new StringList(),_t, _cont.getAnalyzing());
    }


    private static AnalyzedTestContext unfullValidateOverridingMethods(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ctxAna();
        parseCustomFiles(_files, cont_);
        assertTrue( isEmptyErrors(cont_));
        validateInheritingClasses(cont_);
        assertTrue( isEmptyErrors(cont_));
        return cont_;
    }

    private static AnalyzedTestContext unfullValidateOverridingMethodsIds(StringMap<String> _files) {
        AnalyzedTestContext cont_ = ctxAna();
        parseCustomFiles(_files, cont_);
        assertTrue( isEmptyErrors(cont_));
        validateInheritingClasses(cont_);
        assertTrue( isEmptyErrors(cont_));
        validateIds(cont_);
        assertTrue( isEmptyErrors(cont_));
        return cont_;
    }

    private static AnalyzedTestContext simpleContextEl() {
        AnalyzedTestContext cont_ = ctxAna();
        AnalyzedPageEl page_ = cont_.getAnalyzing();
        StringMap<String> files_ = page_.buildFiles();
        buildFilesBodies(cont_, files_);
        parseFiles(cont_);
        validateInheritingClasses(cont_);
        validateIds(cont_);
        validateOverridingInherit(cont_);
        validateEl(cont_);
        checkInterfaces(cont_);
        return cont_;
    }


    private static boolean isCorrectTemplateAllExact(AnalyzedTestContext _cont, StringMap<StringList> _t, String _className) {
        return process(_cont, _t, _className, true);
    }

    private static String tryInfer(AnalyzedTestContext _cont, String _s, String _s2, StringMap<String> _vars) {
        return AnaTemplates.tryInfer(_s, _vars, _s2, _cont.getAnalyzing());
    }

    private static CustList<Matching> infer(AnalyzedTestContext _cont, Mapping _m) {
        return AnaTemplates.removeDup(AnaTemplates.infer(_m,MatchingEnum.SUB, _cont.getAnalyzing()));
    }

    private static CustList<Matching> inferEx(AnalyzedTestContext _cont, Mapping _m) {
        return AnaTemplates.removeDup(AnaTemplates.infer(_m,MatchingEnum.EQ, _cont.getAnalyzing()));
    }

    static String tryGetDeclaredImplicitCast(String _classes, StringMap<String> _varsOwner, String _arg, AnalyzedTestContext _page) {
        return AnaTemplates.tryGetDeclaredImplicitCast(_classes, _varsOwner, _arg, _page.getAnalyzing(), new StringMap<StringList>());
    }
    private static ClassMethodIdReturn tryGetDeclaredImplicitCast(AnalyzedTestContext _cont, Mapping _m) {
        return AnaTemplates.tryGetDeclaredImplicitCast(_m.getParam().getSingleNameOrEmpty(), _m.getArg(), _cont.getAnalyzing(),_m.getMapping());
    }

    private static CustList<Matching> inferOrImplicit(AnalyzedTestContext _cont, Mapping _m, MatchingEnum _mat) {
        return AnaTemplates.inferOrImplicit(_m.getArg(),_m.getParam().getSingleNameOrEmpty(),_mat, _m.getMapping(), _cont.getAnalyzing());
    }


    private static ResultTernary getResultTernary(StringList _one, StringList _two, Argument _argOne, Argument _argTwo, StringMap<StringList> _map, AnalyzedTestContext _c) {
        return ResultTernary.getResultTernary(_one, _argOne, _two, _argTwo, _map, _c.getAnalyzing());
    }

    private static StringList getSuperTypesSet(AnalyzedTestContext _c, StringMap<StringList> _vars, StringList _list) {
        return ResultTernary.getSuperTypesSet(_list, _vars, _c.getAnalyzing());
    }


    private static StringList getTernarySubclasses(AnalyzedTestContext _c, StringMap<StringList> _map, StringList _list) {
        return ResultTernary.getTernarySubclasses(_list, _map, _c.getAnalyzing());
    }
}
