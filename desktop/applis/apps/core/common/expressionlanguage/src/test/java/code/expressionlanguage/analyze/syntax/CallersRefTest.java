package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.sample.CustLgNames;
import code.util.StringMap;
import code.util.core.IndexConstants;
import org.junit.Test;

public final class CallersRefTest extends ProcessMethodCommon {
    @Test
    public void refs1() {
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
        CallersRef r_ = refs(files_,"pkg/Ex",91);
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(89,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(71,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(51,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs2() {
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
        CallersRef r_ = refs(files_,"pkg/Ex",129);
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(127,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(71,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(96,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs3() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",45);
        assertEq(0,r_.getVariablesParamsUse().size());
    }
    @Test
    public void refs4() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",51);
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(51,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs5() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",78);
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(78,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(58,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs6() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]++;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",57);
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(57,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(132,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs7() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]++;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",53);
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(53,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(125,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs8() {
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
        xml_.append("{$int a=0;\n");
        xml_.append("$if(a==0){}};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",45);
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(54,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs9() {
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
        xml_.append("{$int a=0;\n");
        xml_.append("$switch(a){$case $int b:a+b==0;$default b;}};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",45);
        assertEq(2,r_.getVariablesParamsUse().size());
        assertEq(58,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
        assertEq(74,r_.getVariablesParamsUse().get(1).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariablesParamsUse().get(1).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getCaller().getFile().getFileName());
    }
    @Test
    public void refs10() {
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
        xml_.append("{$int a=([b]);};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",47);
        assertEq(0,r_.getVariablesParamsUse().size());
    }
    @Test
    public void refs11() {
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
        xml_.append("{$int #a;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",45);
        assertEq(0,r_.getVariablesParamsUse().size());
    }
    @Test
    public void refs12() {
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
        xml_.append("{$for($int a=0;;){}};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",50);
        assertEq(0,r_.getVariablesParamsUse().size());
    }
    @Test
    public void refs13() {
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
        xml_.append("{$for($int a:{}){}};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",50);
        assertEq(0,r_.getVariablesParamsUse().size());
    }
    @Test
    public void refs14() {
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
        xml_.append("{$for($int a,$int b:{}){$throw $null;}};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",50);
        assertEq(0,r_.getVariablesParamsUse().size());
    }
    @Test
    public void refs15() {
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
        xml_.append("{$int b=$switch(a){$case $int b:a+b==0;$return 0;$default b;$return 0;};};\n");
        xml_.append("$static{}\n");
        xml_.append("Outer2(){}\n");
        xml_.append("}\n");
        xml_.append("$operator/ pkg.Outer(pkg.Outer a, pkg.Outer b){$return $null;}\n");
        xml_.append("$public $annotation pkg.Annot{$int meth();}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",45);
        assertEq(0,r_.getVariablesParamsUse().size());
    }
    @Test
    public void refs16() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",57);
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(57,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(164,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs17() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",53);
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(53,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(171,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs18() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,field2;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",53);
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(53,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(171,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariablesParamsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs19() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,field2=field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",50);
        assertEq(1,r_.getFieldsUse().size());
        assertEq(50,r_.getFieldsUse().get(0).getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getFile().getFileName());
        assertEq(37,r_.getFieldsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getCallee().getFile().getFileName());
        assertEq(43,r_.getFieldsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs20() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field=field2,field2;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",43);
        assertEq(1,r_.getFieldsUse().size());
        assertEq(43,r_.getFieldsUse().get(0).getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getFile().getFileName());
        assertEq(50,r_.getFieldsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getCallee().getFile().getFileName());
        assertEq(37,r_.getFieldsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs21() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,=field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",43);
        assertEq(0,r_.getFieldsUse().size());
    }
    @Test
    public void refs22() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,field2=inex;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",50);
        assertEq(0,r_.getFieldsUse().size());
    }
    @Test
    public void refs23() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",40);
        assertEq(1,r_.getFieldsRefUse().size());
        assertEq(56,r_.getFieldsRefUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getFieldsRefUse().get(0).getFile().getFileName());
        assertEq(27,r_.getFieldsRefUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getFieldsRefUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getFieldsRefUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getFieldsRefUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs24() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",66);
        assertEq(1,r_.getFieldsRefUse().size());
        assertEq(66,r_.getFieldsRefUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getFieldsRefUse().get(0).getFile().getFileName());
        assertEq(37,r_.getFieldsRefUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getFieldsRefUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getFieldsRefUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getFieldsRefUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs25() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",66);
        assertEq(0,r_.getFieldsRefUse().size());
    }
    @Test
    public void refs26() {
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
        xml_.append("{$new Outer(Outer3.field:0);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",58);
        assertEq(1,r_.getFieldsUseInit().size());
        assertEq(58,r_.getFieldsUseInit().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getFieldsUseInit().get(0).getFile().getFileName());
        assertEq(37,r_.getFieldsUseInit().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUseInit().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getFieldsUseInit().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getFieldsUseInit().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs27() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,field2=Short.MIN_VALUE+Short.MAX_VALUE+field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",56);
        assertEq(1,r_.getFieldsUse().size());
        assertEq(56,r_.getFieldsUse().get(0).getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getFile().getFileName());
        assertEq(0,r_.getFieldsUse().get(0).getCallee().getIndex());
        assertEq("",r_.getFieldsUse().get(0).getCallee().getFileName());
        assertEq(43,r_.getFieldsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs28() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$static Outer $(Outer2 p){$return $null;}\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$int $this($int v,$int w){$return 1;}\n");
        xml_.append("$void $this($int w,$int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[v:0,w:0]=2;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field,field2=Short.MIN_VALUE+Short.MAX_VALUE+field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",82);
        assertEq(1,r_.getFieldsUse().size());
        assertEq(82,r_.getFieldsUse().get(0).getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getFile().getFileName());
        assertEq(37,r_.getFieldsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getCallee().getFile().getFileName());
        assertEq(43,r_.getFieldsUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex3",r_.getFieldsUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs29() {
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
        CallersRef r_ = refs(files_,"pkg/Ex",60);
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(59,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(93,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(49,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs30() {
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
        CallersRef r_ = refs(files_,"pkg/Ex",81);
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(79,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(0,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("",r_.getCallNamedUse().get(0).getCallee().getFileName());
        assertEq(49,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs31() {
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
        CallersRef r_ = refs(files_,"pkg/Ex",49);
        assertEq(0,r_.getCallNamedUse().size());
    }
    @Test
    public void refs32() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("$int i=0;\n");
        xml_.append("$int j=\"\".indexOf(' ',i),k=\"\".indexOf(' ');\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",81);
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(79,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(0,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("",r_.getCallNamedUse().get(0).getCallee().getFileName());
        assertEq(49,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs33() {
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
        xml_.append("{$lambda(Outer,THREE);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",54);
        assertEq(1,r_.getCallNamedRefUse().size());
        assertEq(54,r_.getCallNamedRefUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedRefUse().get(0).getFile().getFileName());
        assertEq(48,r_.getCallNamedRefUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedRefUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedRefUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedRefUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs34() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(){\n");
        xml_.append("callee();\n");
        xml_.append("}\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",52);
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(51,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(77,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(41,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getCallNamedUsePoly().size());
        assertEq(51,r_.getCallNamedUsePoly().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getFile().getFileName());
        assertEq(77,r_.getCallNamedUsePoly().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getCallee().getFile().getFileName());
        assertEq(41,r_.getCallNamedUsePoly().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs35() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(){\n");
        xml_.append("callee();\n");
        xml_.append("}\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2:Outer {\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",48);
        assertEq(1,r_.getCallNamedUsePoly().size());
        assertEq(51,r_.getCallNamedUsePoly().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getFile().getFileName());
        assertEq(48,r_.getCallNamedUsePoly().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUsePoly().get(0).getCallee().getFile().getFileName());
        assertEq(41,r_.getCallNamedUsePoly().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getCaller().getFile().getFileName());
        assertEq(0,r_.getCallNamedUse().size());
        assertFalse(r_.getCallNamedOverridden().isEmpty());
        assertFalse(r_.getCallNamedOverriding().isEmpty());
    }
    @Test
    public void refs36() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(){\n");
        xml_.append("$that.callee();\n");
        xml_.append("}\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",58);
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(57,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(83,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(41,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
        assertEq(0,r_.getCallNamedUsePoly().size());
    }
    @Test
    public void refs37() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void callee(){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer3.f.$lambda(Outer,$that,callee);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3:Outer {\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("$static Outer f;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",69);
        assertEq(1,r_.getCallNamedRefUse().size());
        assertEq(69,r_.getCallNamedRefUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedRefUse().get(0).getFile().getFileName());
        assertEq(41,r_.getCallNamedRefUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedRefUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedRefUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedRefUse().get(0).getCaller().getFile().getFileName());
        assertEq(0,r_.getCallNamedRefUsePoly().size());
    }
    @Test
    public void refs38() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void callee(){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer3.f.$lambda(Outer,callee);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3:Outer {\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("$static Outer f;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",48);
        assertEq(1,r_.getCallNamedRefUsePoly().size());
        assertEq(63,r_.getCallNamedRefUsePoly().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedRefUsePoly().get(0).getFile().getFileName());
        assertEq(48,r_.getCallNamedRefUsePoly().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getCallNamedRefUsePoly().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedRefUsePoly().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedRefUsePoly().get(0).getCaller().getFile().getFileName());
        assertEq(0,r_.getCallNamedRefUse().size());
    }
    @Test
    public void refs39() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void callee(){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer3.f.$lambda(Outer,callee);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3:Outer {\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("$static Outer f;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",63);
        assertEq(1,r_.getCallNamedRefUsePoly().size());
        assertEq(63,r_.getCallNamedRefUsePoly().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedRefUsePoly().get(0).getFile().getFileName());
        assertEq(41,r_.getCallNamedRefUsePoly().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedRefUsePoly().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedRefUsePoly().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedRefUsePoly().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getCallNamedRefUse().size());
        assertEq(63,r_.getCallNamedRefUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedRefUse().get(0).getFile().getFileName());
        assertEq(41,r_.getCallNamedRefUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedRefUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedRefUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedRefUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs40() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $void method(){\n");
        xml_.append("callee();\n");
        xml_.append("}\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2:Outer {\n");
        xml_.append("$public $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",51);
        assertEq(1,r_.getCallNamedUsePoly().size());
        assertEq(51,r_.getCallNamedUsePoly().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getFile().getFileName());
        assertEq(77,r_.getCallNamedUsePoly().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getCallee().getFile().getFileName());
        assertEq(41,r_.getCallNamedUsePoly().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(51,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(77,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(41,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs41() {
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
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2<T>:Outer<T> {\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",54);
        assertEq(0,r_.getCallNamedUse().size());
        assertEq(1,r_.getCallNamedUsePoly().size());
        assertEq(85,r_.getCallNamedUsePoly().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getFile().getFileName());
        assertEq(54,r_.getCallNamedUsePoly().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUsePoly().get(0).getCallee().getFile().getFileName());
        assertEq(52,r_.getCallNamedUsePoly().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs42() {
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
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2<T>:Outer<T> {\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",81);
        assertEq(0,r_.getCallNamedUse().size());
        assertEq(1,r_.getCallNamedUsePoly().size());
        assertEq(85,r_.getCallNamedUsePoly().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getFile().getFileName());
        assertEq(81,r_.getCallNamedUsePoly().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUsePoly().get(0).getCallee().getFile().getFileName());
        assertEq(52,r_.getCallNamedUsePoly().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUsePoly().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs43() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer<$int> v=$new();\n");
        xml_.append("v.$that[$id(Outer,T,[]=,Outer,T),0]++;\n");
        xml_.append("}\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2<T>:Outer<T> {\n");
        xml_.append("$public $void $this(T i){\n");
        xml_.append("}\n");
        xml_.append("$public $int $this(T i){\n");
        xml_.append("$return 0;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",91);
        assertEq(0,r_.getCallNamedUsePoly().size());
        assertEq(2,r_.getCallNamedUse().size());
        assertEq(91,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(166,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(52,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
        assertEq(91,r_.getCallNamedUse().get(1).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(1).getFile().getFileName());
        assertEq(139,r_.getCallNamedUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(1).getCallee().getFile().getFileName());
        assertEq(52,r_.getCallNamedUse().get(1).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(1).getCaller().getFile().getFileName());
    }
    @Test
    public void refs44() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$for(;;)label{$break label;}}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",48);
        assertEq(1,r_.getLabels().size());
        assertEq(60,r_.getLabels().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getLabels().get(0).getFile().getFileName());
        assertEq(48,r_.getLabels().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getLabels().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getLabels().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getLabels().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs45() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$static $boolean $(Outer o){$return $true;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$for(;;)label{$continue label;}}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",48);
        assertEq(1,r_.getLabels().size());
        assertEq(63,r_.getLabels().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getLabels().get(0).getFile().getFileName());
        assertEq(48,r_.getLabels().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getLabels().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getLabels().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getLabels().get(0).getCaller().getFile().getFileName());
    }
    private static CallersRef refs(StringMap<String> _files, String _fileName, int _caret) {
        AnalyzedPageEl a_ = quickAnalyze(_files);
        return CallersRef.loop(a_,ResultExpressionOperationNode.locations(a_,_fileName,_caret));
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
