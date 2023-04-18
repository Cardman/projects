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
        assertEq(34,r_.begin());
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
        assertEq(34,r_.begin());
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
    public void container73() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1)\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ResultExpressionOperationNode r_ = quickFindOperation(files_,"pkg/Ex",28);
        assertEq(25,r_.begin());
        assertEq(32,r_.end());
    }
    @Test
    public void container74() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE((1+2]){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        AbsBk r_ = quickFindBlock(files_,"pkg/Ex",28);
        assertEq(36,r_.getBegin());
        assertEq(38,r_.getEndAll());
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
        assertEq(1,r_.size());
        assertEq(0,r_.get(0).getIndex());
        assertEq("",r_.get(0).getFileName());
        assertNotNull(((SrcFileLocationStdMethod)r_.get(0)).getStd());
    }
    @Test
    public void locations5() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer.callee();\n");
        xml_.append("}\n");
        xml_.append("$public $static $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",60);
        assertEq(1,r_.size());
        assertEq(15,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $staticCall $void method(){\n");
        xml_.append("$staticCall(Outer<T>).callee();\n");
        xml_.append("}\n");
        xml_.append("$public $staticCall $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",84);
        assertEq(1,r_.size());
        assertEq(25,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
        assertEq("T",((SrcFileLocationTypeVar)r_.get(0)).getName());
    }
    @Test
    public void locations7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $staticCall $void method(){\n");
        xml_.append("$staticCall(Outer<String>).callee();\n");
        xml_.append("}\n");
        xml_.append("$public $staticCall $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",84);
        assertEq(1,r_.size());
        assertEq(0,r_.get(0).getIndex());
        assertEq("",r_.get(0).getFileName());
        assertEq("java.lang.String",((SrcFileLocationStdType)r_.get(0)).getType());
    }
    @Test
    public void locations8() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$staticCall(Outer2<String>).callee($id(Outer2,T),\"\");\n");
        xml_.append("}\n");
        xml_.append("$public $static $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2<T> {\n");
        xml_.append("$public $staticCall $void callee(T p){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",105);
        assertEq(1,r_.size());
        assertEq(26,r_.get(0).getIndex());
        assertEq("pkg/Ex2",r_.get(0).getFileName());
        assertEq("T",((SrcFileLocationTypeVar)r_.get(0)).getName());
    }
    @Test
    public void locations9() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $staticCall $void method(){\n");
        xml_.append("$static(java.lang.$enums).name($null);\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",74);
        assertEq(1,r_.size());
        assertEq(0,r_.get(0).getIndex());
        assertEq("",r_.get(0).getFileName());
        assertEq("java.lang.$enums",((SrcFileLocationStdType)r_.get(0)).getType());
    }
    @Test
    public void locations10() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $staticCall $void method(){\n");
        xml_.append("$new $interfaces(Inexist) Rec();\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public @$class pkg.Rec {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",83);
        assertEq(0,r_.size());
    }
    @Test
    public void locations11() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $staticCall $void method(){\n");
        xml_.append("$static(Inexist).method();\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",74);
        assertEq(0,r_.size());
    }
    @Test
    public void locations12() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer<$int> v=$new();\n");
        xml_.append("v[$id(Outer,T,[]=,Outer,T),0]++;\n");
        xml_.append("}\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",91);
        assertEq(1,r_.size());
        assertEq(15,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations13() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer<$int> v=$new();\n");
        xml_.append("v[$id(Outer,T,[]=,Outer,T),0]++;\n");
        xml_.append("}\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",103);
        assertEq(1,r_.size());
        assertEq(15,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations14() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer<$int> v=$new();\n");
        xml_.append("v[$id(Outer,T,[]=,Outer,T),0]++;\n");
        xml_.append("}\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",96);
        assertEq(1,r_.size());
        assertEq(25,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
        assertEq("T",((SrcFileLocationTypeVar)r_.get(0)).getName());
    }
    @Test
    public void locations15() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer<$int> v=$new();\n");
        xml_.append("v[$id(Outer,T,[]=,Outer,T),0]++;\n");
        xml_.append("}\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",108);
        assertEq(1,r_.size());
        assertEq(25,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
        assertEq("T",((SrcFileLocationTypeVar)r_.get(0)).getName());
    }
    @Test
    public void locations16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("callee($vararg(Outer<$int>),$firstopt($new()));\n");
        xml_.append("}\n");
        xml_.append("$public $static $void callee(Outer<$int>... i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",78);
        assertEq(1,r_.size());
        assertEq(15,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("ONE<Sample>($null),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sample{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",33);
        assertEq(1,r_.size());
        assertEq(89,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("ONE<Sample>($new Sample()),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sample{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",46);
        assertEq(1,r_.size());
        assertEq(97,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer<T> {\n");
        xml_.append("ONE<Sample.Inner<Sample>>($new Sample().$new Inner<Sample>()),\n");
        xml_.append("TWO<$int>(2);\n");
        xml_.append("(T i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sample{\n");
        xml_.append("$public $class Inner<S>{\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",80);
        assertEq(1,r_.size());
        assertEq(132,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$new Sample();\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Sample{\n");
        xml_.append("$public Sample(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",62);
        assertEq(2,r_.size());
        assertEq(108, r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
        assertEq(96, r_.get(97-96).getIndex());
        assertEq("pkg/Ex",r_.get(97-96).getFileName());
    }
    @Test
    public void locations21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$new Sample();\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public @$class pkg.Sample{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",62);
        assertEq(1,r_.size());
        assertEq(109-12,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations22() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$new Sample();\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",62);
        assertEq(0,r_.size());
    }
    @Test
    public void locations23() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$new StringBuilder();\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",62);
        assertEq(1,r_.size());
        assertEq(0,r_.get(0).getIndex());
        assertEq("",r_.get(0).getFileName());
        assertNotNull(((SrcFileLocationStdMethod)r_.get(0)).getStd());
    }
    @Test
    public void locations24() {
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
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",26);
        assertEq(2,r_.size());
        assertEq(42,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
        assertEq(14,r_.get(1).getIndex());
        assertEq("pkg/Ex",r_.get(1).getFileName());
    }
    @Test
    public void locations25() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",27);
        assertEq(1,r_.size());
        assertEq(83,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations26() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(e=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",33);
        assertEq(0,r_.size());
    }
    @Test
    public void locations27() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot(f=2)\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",33);
        assertEq(1,r_.size());
        assertEq(104,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations28() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer o =$new {} (0){\n");
        xml_.append("$id($int i){}\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",74);
        assertEq(2,r_.size());
        assertEq(81,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
        assertEq(79,r_.get(1).getIndex());
        assertEq("pkg/Ex",r_.get(1).getFileName());
    }
    @Test
    public void locations29() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer o =$new{} @Annot Outer(0){\n");
        xml_.append("($int i){}\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f()0;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",78);
        assertEq(1,r_.size());
        assertEq(130,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations30() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer o =$new{} @Annot()(0){\n");
        xml_.append("($int i){}\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f()0;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",78);
        assertEq(1,r_.size());
        assertEq(126,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations31() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer o =$new{} @Annot(f=2)(0){\n");
        xml_.append("($int i){}\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f()0;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",82);
        assertEq(1,r_.size());
        assertEq(145,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations32() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer o =$new{} @Annot Outer(0){\n");
        xml_.append("($int i){}\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f()0;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",82);
        assertEq(1,r_.size());
        assertEq(15,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations33() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer o =$new{} @Annot Outer(0){\n");
        xml_.append("($int i){$new Outer();}\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f()0;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",106);
        assertEq(1,r_.size());
        assertEq(15,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations34() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer o =$new {} (0){\n");
        xml_.append("$id($int i){}\n");
        xml_.append("};\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",68);
        assertEq(2,r_.size());
        assertEq(81,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
        assertEq(79,r_.get(1).getIndex());
        assertEq("pkg/Ex",r_.get(1).getFileName());
    }
    @Test
    public void locations35() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal();\n");
        xml_.append("$final $int FOUR=THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",58);
        assertEq(1,r_.size());
        assertEq(58,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations36() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal();\n");
        xml_.append("$final $int FOUR=THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",64);
        assertEq(1,r_.size());
        assertEq(26,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations37() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal();\n");
        xml_.append("$final $int FOUR=THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",78);
        assertEq(1,r_.size());
        assertEq(31,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations38() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal(),");
        xml_.append("FOUR=THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",92);
        assertEq(1,r_.size());
        assertEq(92,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations39() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal(),");
        xml_.append("FOUR=THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",98);
        assertEq(1,r_.size());
        assertEq(58,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations40() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal();\n");
        xml_.append("$final $int FOUR=THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",26);
        assertEq(1,r_.size());
        assertEq(14,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations41() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal();\n");
        xml_.append("$final $int FOUR=THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",31);
        assertEq(1,r_.size());
        assertEq(31,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations42() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal(),");
        xml_.append("FOUR=FIVE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",98);
        assertEq(0,r_.size());
    }
    @Test
    public void locations43() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal();\n");
        xml_.append("$final $int FOUR=THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=Outer.THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",71);
        assertEq(1,r_.size());
        assertEq(58,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
        assertEq("pkg.Outer",((SrcFileLocationField)r_.get(0)).getCf().getClassName());
        assertEq("THREE",((SrcFileLocationField)r_.get(0)).getCf().getFieldName());
    }
    @Test
    public void locations44() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal();\n");
        xml_.append("$final $int FOUR=THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=Outer.$classchoice(Outer)THREE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",84);
        assertEq(1,r_.size());
        assertEq(14,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations45() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=Outer.$classchoice(Outer)THREE();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",84);
        assertEq(1,r_.size());
        assertEq(14,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations46() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final Outer THREE=$defaultValue(Outer);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",80);
        assertEq(1,r_.size());
        assertEq(14,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations47() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=([a]);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",68);
        assertEq(0,r_.size());
    }
    @Test
    public void locations48() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){\n");
        xml_.append("$iter($int i=0;1;1){\n");
        xml_.append("$return ([i]);\n");
        xml_.append("}\n");
        xml_.append("$return 1;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",91);
        assertEq(1,r_.size());
        assertEq(71,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
        assertEq(-1,((SrcFileLocationVariable)r_.get(0)).getDeep());
        assertEq("i",((SrcFileLocationVariable)r_.get(0)).getName());
    }
    @Test
    public void locations49() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){\n");
        xml_.append("$iter($int i=0;1;1){\n");
        xml_.append("$return (:$int)->{$iter($int i=0;1;1){$return ([#i])+([i]);}$return 1;}.call();\n");
        xml_.append("}\n");
        xml_.append("$return 1;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex",129);
        assertEq(1,r_.size());
        assertEq(71,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
        assertEq(0,((SrcFileLocationVariable)r_.get(0)).getDeep());
        assertEq("i",((SrcFileLocationVariable)r_.get(0)).getName());
    }
    @Test
    public void locations50() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int #a=0;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",45);
        assertEq(0,r_.size());
    }
    @Test
    public void locations51() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=0;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",45);
        assertEq(1,r_.size());
        assertEq(45,r_.get(0).getIndex());
        assertEq("pkg/Ex2",r_.get(0).getFileName());
        assertEq(-1,((SrcFileLocationVariable)r_.get(0)).getDeep());
        assertEq("a",((SrcFileLocationVariable)r_.get(0)).getName());
    }
    @Test
    public void locations52() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=0,b=a;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",51);
        assertEq(1,r_.size());
        assertEq(45,r_.get(0).getIndex());
        assertEq("pkg/Ex2",r_.get(0).getFileName());
        assertEq(-1,((SrcFileLocationVariable)r_.get(0)).getDeep());
        assertEq("a",((SrcFileLocationVariable)r_.get(0)).getName());
    }
    @Test
    public void locations53() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=0,b=(:$int)->{$int a=0;$return #a+a;}.call();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",78);
        assertEq(1,r_.size());
        assertEq(45,r_.get(0).getIndex());
        assertEq("pkg/Ex2",r_.get(0).getFileName());
        assertEq(0,((SrcFileLocationVariable)r_.get(0)).getDeep());
        assertEq("a",((SrcFileLocationVariable)r_.get(0)).getName());
    }
    @Test
    public void locations54() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=$values(Outer).length;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",55);
        assertEq(1,r_.size());
        assertEq(14,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations55() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=$class(Outer).getName().length;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",54);
        assertEq(1,r_.size());
        assertEq(14,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations56() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=$new Outer().$classchoice(Outer)[0];}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",73);
        assertEq(1,r_.size());
        assertEq(15,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations57() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",40);
        assertEq(0,r_.size());
    }
    @Test
    public void locations58() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer2,,ONE);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",40);
        assertEq(1,r_.size());
        assertEq(27,r_.get(0).getIndex());
        assertEq("pkg/Ex2",r_.get(0).getFileName());
        assertEq("pkg.Outer2",((SrcFileLocationField)r_.get(0)).getCf().getClassName());
        assertEq("ONE",((SrcFileLocationField)r_.get(0)).getCf().getFieldName());
    }
    @Test
    public void locations59() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer,THREE);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",40);
        assertEq(1,r_.size());
        assertEq(40,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations60() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class pkg.Outer:Outer3 {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer,$new,Outer3.field);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",59);
        assertEq(1,r_.size());
        assertEq(19,r_.get(0).getIndex());
        assertEq("pkg/Ex3",r_.get(0).getFileName());
    }
    @Test
    public void locations61() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class pkg.Outer:Outer3 {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer,$new,Outer3.field);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",66);
        assertEq(1,r_.size());
        assertEq(37,r_.get(0).getIndex());
        assertEq("pkg/Ex3",r_.get(0).getFileName());
        assertEq("pkg.Outer3",((SrcFileLocationField)r_.get(0)).getCf().getClassName());
        assertEq("field",((SrcFileLocationField)r_.get(0)).getCf().getFieldName());
    }
    @Test
    public void locations62() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class pkg.Outer:Outer3 {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer,$new,Outer3.inex);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",66);
        assertEq(0,r_.size());
    }
    @Test
    public void locations63() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class pkg.Outer:Outer3 {\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$lambda(Outer,$new,Outer3.field);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",48);
        assertEq(1,r_.size());
        assertEq(16,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations64() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Object o=$(Outer,Outer2)$null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",51);
        assertEq(1,r_.size());
        assertEq(15,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations65() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Object o=$(Outer,Outer2)$null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",57);
        assertEq(1,r_.size());
        assertEq(14,r_.get(0).getIndex());
        assertEq("pkg/Ex2",r_.get(0).getFileName());
    }
    @Test
    public void locations66() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Object o=$(Outer,Outer2)$null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",49);
        assertEq(1,r_.size());
        assertEq(48,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations67() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Object o=$(Outer)$null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",51);
        assertEq(1,r_.size());
        assertEq(15,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations68() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Object o= (Outer)$null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",51);
        assertEq(1,r_.size());
        assertEq(15,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations69() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=$valueOf(Outer,\"\").ordinal();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",56);
        assertEq(1,r_.size());
        assertEq(14,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations70() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$boolean a=ONE $instanceof Outer;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",67);
        assertEq(1,r_.size());
        assertEq(14,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations71() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$operator- $int(Outer o){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=-Outer.ONE;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",47);
        assertEq(1,r_.size());
        assertEq(80,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations72() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$operator- $int(Outer o){$return 1;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$int a=(0);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",47);
        assertEq(0,r_.size());
    }
    @Test
    public void locations73() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$operator-- Outer(Outer o){$return o;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer a=--Outer.ONE;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",48);
        assertEq(1,r_.size());
        assertEq(80,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void locations74() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$operator-- Outer(Outer o){$return o;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer a=Outer.ONE--;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CustList<SrcFileLocation> r_ = locations(files_,"pkg/Ex2",57);
        assertEq(1,r_.size());
        assertEq(80,r_.get(0).getIndex());
        assertEq("pkg/Ex",r_.get(0).getFileName());
    }
    @Test
    public void vexer1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal(),");
        xml_.append("FOUR=FIVE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertEq("",vexerChamps(files_,"pkg/Ex",98));
    }
    @Test
    public void vexer2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal(),");
        xml_.append("FOUR=FIVE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertEq("",vexerChamps(files_,"pkg/Ex",0));
    }
    @Test
    public void vexer3() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $final $int THREE=ONE.ordinal()+TWO.ordinal(),");
        xml_.append("FOUR=FIVE;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        assertEq("FOUR",vexerChamps(files_,"pkg/Ex",93));
    }
    private static CustList<SrcFileLocation> locations(StringMap<String> _files, String _fileName, int _caret) {
        AnalyzedPageEl a_ = quickAnalyze(_files);
        return ResultExpressionOperationNode.locations(a_,_fileName,_caret);
    }
    private static String vexerChamps(StringMap<String> _files, String _fileName, int _caret) {
        AnalyzedPageEl a_ = quickAnalyze(_files);
        return ResultExpressionOperationNode.vexerChamps(a_,_fileName,_caret);
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
        opt_.setReadOnly(true);
        addTypesInit(opt_);
        CustLgNames lgName_ = getLgNames();
        KeyWords kw_ = new KeyWords();
        setOpts(opt_, IndexConstants.INDEX_NOT_FOUND_ELT);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        getForwards(opt_,lgName_,kw_,page_);
        return validateWithoutInit(_files, page_);
    }
}
