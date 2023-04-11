package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.sample.CustLgNames;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import org.junit.Test;

public final class ResultExpressionOperationNodeTest extends ProcessMethodCommon {

    @Test
    public void container1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",26);
        assertEq(0,r_.getOffset());
        assertEq(28,r_.getEndAll());
    }

    @Test
    public void container2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",28);
        assertEq(0,r_.getOffset());
        assertEq(29,r_.getEndAll());
    }

    @Test
    public void container3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("  $public $static $int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",26);
        assertEq(0,r_.getOffset());
        assertEq(58,r_.getEndAll());
    }

    @Test
    public void container4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("  $public $static $int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",56);
        assertEq(0,r_.getOffset());
        assertEq(58,r_.getEndAll());
    }

    @Test
    public void container5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("  $public $static $int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",58);
        assertEq(0,r_.getOffset());
        assertEq(59,r_.getEndAll());
    }

    @Test
    public void container6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("  $public $static $int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",29);
        assertEq(29,r_.getOffset());
        assertEq(56,r_.getEndAll());
    }

    @Test
    public void container7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("  $public $static $int field1;\n");
        xml_.append("  $public $static $int field2;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",29);
        assertEq(29,r_.getOffset());
        assertEq(57,r_.getEndAll());
    }

    @Test
    public void container8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("  $public $static $int field1;\n");
        xml_.append("  $public $static $int field2;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",59);
        assertEq(0,r_.getOffset());
        assertEq(90,r_.getEndAll());
    }

    @Test
    public void container9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("  $public $static $int field1;\n");
        xml_.append("  $public $static $int field2;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",28);
        assertEq(0,r_.getOffset());
        assertEq(90,r_.getEndAll());
    }

    @Test
    public void container10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("  $public $static $int field1;\n");
        xml_.append("  $public $static $int field2;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",88);
        assertEq(0,r_.getOffset());
        assertEq(90,r_.getEndAll());
    }

    @Test
    public void container11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("  $public $static $int field1;\n");
        xml_.append("  $public $static $int field2;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",60);
        assertEq(60,r_.getOffset());
        assertEq(88,r_.getEndAll());
    }

    @Test
    public void container12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("  $public $static $void method(){\n");
        xml_.append("    field++;\n");
        xml_.append("  }\n");
        xml_.append("  $public $static $int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",80);
        assertEq(80,r_.getOffset());
        assertEq(107,r_.getEndAll());
    }

    @Test
    public void container13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",30);
        assertEq(30,r_.begin());
        assertEq(31,r_.end());
    }

    @Test
    public void container14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",26);
        assertEq(25,r_.begin());
        assertEq(32,r_.end());
    }

    @Test
    public void container15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("ONE<$int>(1),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",33);
        assertEq(29,r_.getOffset());
        assertEq(42,r_.getEndAll());
    }
    @Test
    public void container16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("ONE<$int>(1),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",39);
        assertEq(39,r_.begin());
        assertEq(40,r_.end());
    }
    @Test
    public void container17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("ONE<$int>(1),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",29);
        assertEq(30,r_.begin());
        assertEq(41,r_.end());
    }
    @Test
    public void container18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("ONE<$int>(1),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",41);
        assertEq(30,r_.begin());
        assertEq(41,r_.end());
    }
    @Test
    public void container19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("ONE<$int>(1),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",37);
        assertEq(29,r_.getOffset());
        assertEq(42,r_.getEndAll());
    }
    @Test
    public void container20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("@Annot\n");
        xml_.append("ONE<$int>(1),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",29);
        assertEq(29,r_.begin());
        assertEq(35,r_.end());
    }
    @Test
    public void container21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("@Annot\n");
        xml_.append("ONE<String>(\"one\"),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",48);
        assertEq(48,r_.begin());
        assertEq(53,r_.end());
    }
    @Test
    public void container22() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("ONE<$int>(1),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",40);
        assertEq(34,r_.begin());
        assertEq(41,r_.end());
    }
    @Test
    public void container23() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",38);
        assertEq(33,r_.begin());
        assertEq(39,r_.end());
    }
    @Test
    public void container24() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(@Annot $int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",41);
        assertEq(27,r_.getOffset());
        assertEq(65,r_.getEndAll());
    }
    @Test
    public void container25() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(@Annot $int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",55);
        assertEq(27,r_.getOffset());
        assertEq(65,r_.getEndAll());
    }
    @Test
    public void container26() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(@Annot $int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",49);
        assertEq(48,r_.begin());
        assertEq(54,r_.end());
    }
    @Test
    public void container27() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void $this($int i,@Annot $int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",55);
        assertEq(54,r_.begin());
        assertEq(60,r_.end());
    }
    @Test
    public void container28() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("@Annot\n");
        xml_.append("$public $void $this($int i, $int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",28);
        assertEq(27,r_.begin());
        assertEq(33,r_.end());
    }
    @Test
    public void container29() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public Outer(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",35);
        assertEq(27,r_.getOffset());
        assertEq(45,r_.getEndAll());
    }
    @Test
    public void container30() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$iter($int i=0;4;1)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",33);
        assertEq(29,r_.getOffset());
        assertEq(56,r_.getEndAll());
    }
    @Test
    public void container31() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$iter($int i=0;4;1)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",50);
        assertEq(29,r_.getOffset());
        assertEq(56,r_.getEndAll());
    }
    @Test
    public void container32() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$iter($int i=0;4;1)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",42);
        assertEq(42,r_.begin());
        assertEq(43,r_.end());
    }
    @Test
    public void container33() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$iter($int i=0;4;1)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",44);
        assertEq(44,r_.begin());
        assertEq(45,r_.end());
    }
    @Test
    public void container34() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$iter($int i=0;4;1)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",46);
        assertEq(46,r_.begin());
        assertEq(47,r_.end());
    }
    @Test
    public void container35() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$for($int i=0;i<4;i++)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",52);
        assertEq(29,r_.getOffset());
        assertEq(59,r_.getEndAll());
    }
    @Test
    public void container36() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$for($int i=0;i<4;i++)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",40);
        assertEq(39,r_.begin());
        assertEq(42,r_.end());
    }
    @Test
    public void container37() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$for($int i=0;i<4;i++)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",44);
        assertEq(43,r_.begin());
        assertEq(46,r_.end());
    }
    @Test
    public void container38() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$for($int i=0;i<4;i++)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",48);
        assertEq(47,r_.begin());
        assertEq(50,r_.end());
    }
    @Test
    public void container39() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",30);
        assertEq(27,r_.getOffset());
        assertEq(37,r_.getEndAll());
    }
    @Test
    public void container40() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int v;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",30);
        assertEq(27,r_.getOffset());
        assertEq(42,r_.getEndAll());
    }
    @Test
    public void container41() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int v;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",40);
        assertEq(40,r_.begin());
        assertEq(41,r_.end());
    }
    @Test
    public void container42() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$if($true)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",40);
        assertEq(29,r_.getOffset());
        assertEq(47,r_.getEndAll());
    }
    @Test
    public void container43() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$if($true)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",33);
        assertEq(33,r_.begin());
        assertEq(38,r_.end());
    }
    @Test
    public void container44() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$switch(1)label{\n");
        xml_.append("$case 1?:$true;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",40);
        assertEq(29,r_.getOffset());
        assertEq(63,r_.getEndAll());
    }
    @Test
    public void container45() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$switch(1)label{\n");
        xml_.append("$case 1?:$true;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",37);
        assertEq(37,r_.begin());
        assertEq(38,r_.end());
    }
    @Test
    public void container46() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$switch(1)label{\n");
        xml_.append("$case 1?:$true;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",50);
        assertEq(46,r_.getOffset());
        assertEq(63,r_.getEndAll());
    }
    @Test
    public void container47() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$switch(1)label{\n");
        xml_.append("$case 1?:$true;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",52);
        assertEq(52,r_.begin());
        assertEq(53,r_.end());
    }
    @Test
    public void container48() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$switch(1)label{\n");
        xml_.append("$case 1?:$true;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",55);
        assertEq(55,r_.begin());
        assertEq(60,r_.end());
    }
    @Test
    public void container49() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$for($int a,$int b:1)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",40);
        assertEq(29,r_.getOffset());
        assertEq(58,r_.getEndAll());
    }
    @Test
    public void container50() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$for($int a,$int b:1)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",48);
        assertEq(48,r_.begin());
        assertEq(49,r_.end());
    }
    @Test
    public void container51() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$for($int a:1)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",40);
        assertEq(29,r_.getOffset());
        assertEq(51,r_.getEndAll());
    }
    @Test
    public void container52() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$for($int a:1)label{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",41);
        assertEq(41,r_.begin());
        assertEq(42,r_.end());
    }
    @Test
    public void container53() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("a++;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",32);
        assertEq(29,r_.getOffset());
        assertEq(33,r_.getEndAll());
    }
    @Test
    public void container54() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("a++;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",31);
        assertEq(29,r_.begin());
        assertEq(32,r_.end());
    }
    @Test
    public void container55() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$return a++;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",40);
        assertEq(29,r_.getOffset());
        assertEq(41,r_.getEndAll());
    }
    @Test
    public void container56() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$return a++;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",39);
        assertEq(37,r_.begin());
        assertEq(40,r_.end());
    }
    @Test
    public void container57() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$throw a++;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",39);
        assertEq(29,r_.getOffset());
        assertEq(40,r_.getEndAll());
    }
    @Test
    public void container58() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$throw a++;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",38);
        assertEq(36,r_.begin());
        assertEq(39,r_.end());
    }
    @Test
    public void container59() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$new Outer(){\n");
        xml_.append("a++;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",30);
        assertEq(29,r_.begin());
        assertEq(49,r_.end());
    }
    @Test
    public void container60() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$new Outer(){\n");
        xml_.append("a++;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",34);
        assertEq(29,r_.getOffset());
        assertEq(50,r_.getEndAll());
    }
    @Test
    public void container61() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$new Outer(){\n");
        xml_.append("a++;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",40);
        assertEq(41,r_.getOffset());
        assertEq(49,r_.getEndAll());
    }
    @Test
    public void container62() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("$new@Annot Outer(){\n");
        xml_.append("a++;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",34);
        assertEq(33,r_.begin());
        assertEq(39,r_.end());
    }
    @Test
    public void container63() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("b=$switch[$int:@Annot:@Annot](1){\n");
        xml_.append("$case 1;\n");
        xml_.append("$return 1;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",32);
        assertEq(31,r_.begin());
        assertEq(84,r_.end());
    }
    @Test
    public void container64() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("b=$switch[$int:@Annot:@Annot](1){\n");
        xml_.append("$case 1;\n");
        xml_.append("$return 1;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",39);
        assertEq(31,r_.begin());
        assertEq(84,r_.end());
    }
    @Test
    public void container65() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("b=$switch[$int:@Annot:@Annot](1){\n");
        xml_.append("$case 1;\n");
        xml_.append("$return 1;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",45);
        assertEq(44,r_.begin());
        assertEq(50,r_.end());
    }
    @Test
    public void container66() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("b=$switch[$int:@Annot:@Annot](1){\n");
        xml_.append("$case 1;\n");
        xml_.append("$return 1;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",52);
        assertEq(51,r_.begin());
        assertEq(57,r_.end());
    }
    @Test
    public void container67() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("b=$switch[$int:@Annot:@Annot](1){\n");
        xml_.append("$case 1;\n");
        xml_.append("$return 1;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",61);
        assertEq(61,r_.getOffset());
        assertEq(84,r_.getEndAll());
    }
    @Test
    public void container68() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("b=(@Annot $int a:@Annot $int)->{\n");
        xml_.append("$return 1;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",44);
        assertEq(60,r_.getOffset());
        assertEq(74,r_.getEndAll());
    }
    @Test
    public void container69() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("b=(@Annot $int a:@Annot $int)->{\n");
        xml_.append("$return 1;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",61);
        assertEq(60,r_.getOffset());
        assertEq(74,r_.getEndAll());
    }
    @Test
    public void container70() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("b=(@Annot $int a:@Annot $int)->{\n");
        xml_.append("$return 1;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",59);
        assertEq(31,r_.begin());
        assertEq(74,r_.end());
    }
    @Test
    public void container71() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("b=(@Annot $int a:@Annot $int)->{\n");
        xml_.append("$return 1;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",33);
        assertEq(32,r_.begin());
        assertEq(38,r_.end());
    }
    @Test
    public void container72() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("{\n");
        xml_.append("b=(@Annot $int a:@Annot $int)->{\n");
        xml_.append("$return 1;\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",47);
        assertEq(46,r_.begin());
        assertEq(52,r_.end());
    }

    @Test
    public void locations0() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",28);
        assertEq(0,r_.size());
    }
    @Test
    public void locations1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("callee();\n");
        xml_.append("}\n");
        xml_.append("$public $static $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",60);
        assertEq(1,r_.size());
        assertEq(93,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }

    @Test
    public void locations2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("callee();\n");
        xml_.append("}\n");
        xml_.append("$public $static $void other(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",60);
        assertEq(0,r_.size());
    }

    @Test
    public void locations3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$int i=0;\n");
        xml_.append("$int j=\"\".indexOf(' ',i);\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",81);
        assertEq(1,r_.size());
        assertEq(0,r_.get(0).getIndex());
        assertEq("",r_.get(0).getFileName());
        assertNotNull(((SrcFileLocationStdMethod)r_.get(0)).getStd());
    }

    @Test
    public void locations4() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$int i=0;\n");
        xml_.append("$int j=\"\". indexOf(' ',i);\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",79);
        assertEq(0,r_.size());
    }
    private static CustList<SrcFileLocation> locations(StringMap<String> _files, String _fileName, int _caret) {
        AnalyzedPageEl a_ = quickAnalyze(_files);
        return ResultExpressionOperationNode.locations(a_,_fileName,_caret);
    }
    private static AbsBk quickFindBlock(StringMap<String> _files, String _fileName, int _caret) {
        return quickFindOperation(_files,_fileName,_caret).getBlock();
    }

    private static ResultExpressionOperationNode quickFindOperation(StringMap<String> _files, String _fileName, int _caret) {
        AnalyzedPageEl a_ = quickAnalyze(_files);
        return ResultExpressionOperationNode.container(a_,_fileName,_caret);
    }

    private static AnalyzedPageEl quickAnalyze(StringMap<String> _files) {
        Options opt_ = newOptions();
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();
        KeyWords kw_ = new KeyWords();
        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        getForwards(opt_,lgName_,kw_,page_);
        return validateWithoutInit(_files, page_);
    }
}
