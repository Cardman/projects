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
    @Test
    public void refs46() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer()[0]++;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",43);
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(43,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(54,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(34,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs47() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",49);
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(49,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(48,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs48() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$operator- $staticCall Outer(Outer o,Outer p){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer a=$operator(-,Outer)(a,Outer.ONE);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",48);
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(48,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(80,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs49() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$operator- $staticCall Outer(Outer o,Outer p){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer a=a-Outer.ONE;$int b=a==a?0:1;a-=Outer.ONE;a&&a;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",49);
        assertEq(2,r_.getCallNamedUse().size());
        assertEq(49,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(80,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
        assertEq(77,r_.getCallNamedUse().get(1).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(1).getFile().getFileName());
        assertEq(80,r_.getCallNamedUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(1).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedUse().get(1).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(1).getCaller().getFile().getFileName());
    }
    @Test
    public void refs50() {
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
        xml_.append("{Outer2 n=$null;Outer o=n;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",63);
        assertEq(1,r_.getCallNamedUseImpl().size());
        assertEq(63,r_.getCallNamedUseImpl().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUseImpl().get(0).getFile().getFileName());
        assertEq(48,r_.getCallNamedUseImpl().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUseImpl().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedUseImpl().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUseImpl().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs51() {
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
        xml_.append("{Outer2 n=$null;Outer o=n;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("{Outer2 n=$null;Outer o=n;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",63);
        assertEq(2,r_.getCallNamedUseImpl().size());
    }
    @Test
    public void refs52() {
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
        xml_.append("{Outer o=$new();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",48);
        assertEq(1,r_.getInstanceNewTypes().size());
        assertEq(48,r_.getInstanceNewTypes().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypes().get(0).getFile().getFileName());
        assertEq(15,r_.getInstanceNewTypes().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getInstanceNewTypes().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getInstanceNewTypes().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypes().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs53() {
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
        xml_.append("{Outer o=$lambda(Outer,$new);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",62);
        assertEq(1,r_.getInstanceNewTypesRef().size());
        assertEq(62,r_.getInstanceNewTypesRef().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesRef().get(0).getFile().getFileName());
        assertEq(15,r_.getInstanceNewTypesRef().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getInstanceNewTypesRef().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getInstanceNewTypesRef().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesRef().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs54() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer2 {\n");
        xml_.append("$public Outer(){$super();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",50);
        assertEq(1,r_.getInstanceNewTypesFwd().size());
        assertEq(50,r_.getInstanceNewTypesFwd().get(0).getIndex());
        assertEq("pkg/Ex",r_.getInstanceNewTypesFwd().get(0).getFile().getFileName());
        assertEq(15,r_.getInstanceNewTypesFwd().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesFwd().get(0).getCallee().getFile().getFileName());
        assertEq(34,r_.getInstanceNewTypesFwd().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getInstanceNewTypesFwd().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs55() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",33);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(33,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(26,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(216,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs56() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",33);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(33,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(26,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs57() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($class(SecAnnot))[0]).f();\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.SecAnnot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",33);
        assertEq(0,r_.getAnnotCandidatesCallsInitMembers().size());
        assertEq(0,r_.getInterfacesInit().size());
        assertEq(0,r_.getInterfacesInitRef().size());
    }
    @Test
    public void refs58() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations(c)[0]).f();\n");
        xml_.append("$Class c=$class(Annot);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",33);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(33,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(26,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs59() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("(@Annot(f=2)$int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredConstructors()[0].getAnnotationsParameters()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",51);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(51,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(44,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(227,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs60() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("(@Annot(f=2)$int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredConstructors()[0].getAnnotationsParameters($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",51);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(51,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(44,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(240,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs61() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,@Annot(f=2)$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredMethods()[0].getAnnotationsSupp()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",69);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(69,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(127,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(62,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(239,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(127,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(166,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs62() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,@Annot(f=2)$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredMethods()[0].getAnnotationsSupp($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",69);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(69,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(127,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(62,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(252,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(127,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(166,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs63() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("Annot g()@Annot(f=2);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Annot).getDeclaredMethods()[0].getDefaultValue()).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",137);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(137,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(116,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(127,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(244,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(116,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(177,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs64() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int g=$switch[$int:@Annot(f=2):@Annot(f=2)](0){$default;$return 0;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",97);
        assertEq(2,r_.getCallNamedFieldUse().size());
        assertEq(97,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(36,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(90,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
        assertEq(109,r_.getCallNamedFieldUse().get(1).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(1).getFile().getFileName());
        assertEq(36,r_.getCallNamedFieldUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(1).getCallee().getFile().getFileName());
        assertEq(102,r_.getCallNamedFieldUse().get(1).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(1).getCaller().getFile().getFileName());
    }
    @Test
    public void refs65() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int g=$switch[$int:@Annot(e=2):@Annot(e=2)](0){$default;$return 0;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",97);
        assertEq(0,r_.getCallNamedFieldUse().size());
    }
    @Test
    public void refs66() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($null)[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",33);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(33,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(26,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs67() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@Annot(f=2)\n");
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",7);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(7,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(0,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(188,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs68() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@Annot(f=2)\n");
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",7);
        assertEq(1,r_.getCallNamedFieldUse().size());
        assertEq(7,r_.getCallNamedFieldUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedFieldUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCallee().getFile().getFileName());
        assertEq(0,r_.getCallNamedFieldUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedFieldUse().get(0).getCaller().getFile().getFileName());
        assertEq(0,r_.getAnnotCandidatesCallsInitMembers().size());
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(216,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(104,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs69() {
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
        xml_.append("$Fct<Outer2> fct;\n");
        xml_.append("{fct=$lambda(Outer2,,ONE);}\n");
        xml_.append("{fct.call();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",78);
        assertEq(1,r_.getDynCallPotential().size());
        assertEq(90,r_.getDynCallPotential().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getDynCallPotential().get(0).getFile().getFileName());
        assertEq(27,r_.getDynCallPotential().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getDynCallPotential().get(0).getCallee().getFile().getFileName());
        assertEq(84,r_.getDynCallPotential().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getDynCallPotential().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs70() {
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
        xml_.append("$Fct<Outer2> fct;\n");
        xml_.append("{fct=$lambda(Outer2,,ONE);}\n");
        xml_.append("$Fct<Outer> fct2;\n");
        xml_.append("{fct2.call();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",78);
        assertEq(0,r_.getDynCallPotential().size());
    }
    @Test
    public void refs71() {
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
        xml_.append("$Fct fct;\n");
        xml_.append("{fct=$lambda(Outer2,,ONE);}\n");
        xml_.append("{fct.call();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",70);
        assertEq(1,r_.getDynCallPotential().size());
        assertEq(82,r_.getDynCallPotential().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getDynCallPotential().get(0).getFile().getFileName());
        assertEq(27,r_.getDynCallPotential().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getDynCallPotential().get(0).getCallee().getFile().getFileName());
        assertEq(76,r_.getDynCallPotential().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getDynCallPotential().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs72() {
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
        xml_.append("$Fct<Outer2> fct;\n");
        xml_.append("{fct=$lambda(Outer2,,ONE);}\n");
        xml_.append("$Fct<Outer> fct2;\n");
        xml_.append("{fct2.inex();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",78);
        assertEq(0,r_.getDynCallPotential().size());
    }
    @Test
    public void refs73() {
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
        xml_.append("$Fct<Outer2> fct;\n");
        xml_.append("{fct=$lambda(Outer2,,ONE);}\n");
        xml_.append("$Fct<Outer> fct2;\n");
        xml_.append("{fct2.metaInfo();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",78);
        assertEq(0,r_.getDynCallPotential().size());
    }
    @Test
    public void refs74() {
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
        xml_.append("$public $enum pkg.Outer2<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>{};\n");
        xml_.append("$Fct<T,Outer> fct;\n");
        xml_.append("{fct.call($null);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",78);
        assertEq(0,r_.getDynCallPotential().size());
    }
    @Test
    public void refs75() {
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
        xml_.append("$public $enum pkg.Outer2<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>{};\n");
        xml_.append("$Fct<Outer,T> fct;\n");
        xml_.append("{fct.call($null);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",78);
        assertEq(0,r_.getDynCallPotential().size());
    }
    @Test
    public void refs76() {
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
        xml_.append("$public $enum pkg.Outer2<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>{};\n");
        xml_.append("$Fct<Outer,Outer2<T>> fct;\n");
        xml_.append("{fct.call($null);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",78);
        assertEq(0,r_.getDynCallPotential().size());
    }
    @Test
    public void refs77() {
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
        xml_.append("$public $enum pkg.Outer2<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>{};\n");
        xml_.append("$Fct<Outer,$Fct<T>> fct;\n");
        xml_.append("{fct.call($null);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",78);
        assertEq(0,r_.getDynCallPotential().size());
    }
    @Test
    public void refs78() {
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
        xml_.append("$public $enum pkg.Outer2<T> {\n");
        xml_.append("ONE<$int>,\n");
        xml_.append("TWO<$int>{};\n");
        xml_.append("$Fct<Outer,~$Fct<T>> fct;\n");
        xml_.append("{fct.call($null);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",78);
        assertEq(0,r_.getDynCallPotential().size());
    }
    @Test
    public void refs79() {
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
        xml_.append("$Fct<$byte,Outer> fct;\n");
        xml_.append("{fct=$lambda(Outer,$new,Outer3.field);}\n");
        xml_.append("{fct.call(1y);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",93);
        assertEq(1,r_.getDynCallPotential().size());
        assertEq(107,r_.getDynCallPotential().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getDynCallPotential().get(0).getFile().getFileName());
        assertEq(37,r_.getDynCallPotential().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getDynCallPotential().get(0).getCallee().getFile().getFileName());
        assertEq(101,r_.getDynCallPotential().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getDynCallPotential().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs80() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",27);
        assertEq(1,r_.getAnnotCandidatesCallsInitMembers().size());
        assertEq(195,r_.getAnnotCandidatesCallsInitMembers().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getFile().getFileName());
        assertEq(88,r_.getAnnotCandidatesCallsInitMembers().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitMembers().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getAnnotCandidatesCallsInitArobaseMembers().size());
        assertEq(195,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getFile().getFileName());
        assertEq(26,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs81() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",27);
        assertEq(1,r_.getAnnotCandidatesCallsInitMembers().size());
        assertEq(195,r_.getAnnotCandidatesCallsInitMembers().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getFile().getFileName());
        assertEq(88,r_.getAnnotCandidatesCallsInitMembers().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitMembers().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getAnnotCandidatesCallsInitArobaseMembers().size());
        assertEq(195,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getFile().getFileName());
        assertEq(26,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs82() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations(c)[0]).f();\n");
        xml_.append("$Class c=$class(Annot);\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",27);
        assertEq(1,r_.getAnnotCandidatesCallsInitMembers().size());
        assertEq(195,r_.getAnnotCandidatesCallsInitMembers().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getFile().getFileName());
        assertEq(88,r_.getAnnotCandidatesCallsInitMembers().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitMembers().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getAnnotCandidatesCallsInitArobaseMembers().size());
        assertEq(195,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getFile().getFileName());
        assertEq(26,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs83() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("(@Annot(f=2)$int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredConstructors()[0].getAnnotationsParameters()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",45);
        assertEq(1,r_.getAnnotCandidatesCallsInitParameters().size());
        assertEq(196,r_.getAnnotCandidatesCallsInitParameters().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitParameters().get(0).getFile().getFileName());
        assertEq(88,r_.getAnnotCandidatesCallsInitParameters().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitParameters().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitParameters().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitParameters().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getAnnotCandidatesCallsInitArobaseParameters().size());
        assertEq(196,r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getFile().getFileName());
        assertEq(44,r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs84() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("(@Annot(f=2)$int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredConstructors()[0].getAnnotationsParameters($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",45);
        assertEq(1,r_.getAnnotCandidatesCallsInitParameters().size());
        assertEq(196,r_.getAnnotCandidatesCallsInitParameters().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitParameters().get(0).getFile().getFileName());
        assertEq(88,r_.getAnnotCandidatesCallsInitParameters().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitParameters().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitParameters().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitParameters().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getAnnotCandidatesCallsInitArobaseParameters().size());
        assertEq(196,r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getFile().getFileName());
        assertEq(44,r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseParameters().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs85() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,@Annot(f=2)$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredMethods()[0].getAnnotationsSupp()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",63);
        assertEq(1,r_.getAnnotCandidatesCallsInitSuppl().size());
        assertEq(214,r_.getAnnotCandidatesCallsInitSuppl().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitSuppl().get(0).getFile().getFileName());
        assertEq(111,r_.getAnnotCandidatesCallsInitSuppl().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitSuppl().get(0).getCallee().getFile().getFileName());
        assertEq(166,r_.getAnnotCandidatesCallsInitSuppl().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitSuppl().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getAnnotCandidatesCallsInitArobaseSuppl().size());
        assertEq(214,r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getFile().getFileName());
        assertEq(62,r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getCallee().getFile().getFileName());
        assertEq(166,r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs86() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,@Annot(f=2)$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredMethods()[0].getAnnotationsSupp($class(Annot))[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",63);
        assertEq(1,r_.getAnnotCandidatesCallsInitSuppl().size());
        assertEq(214,r_.getAnnotCandidatesCallsInitSuppl().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitSuppl().get(0).getFile().getFileName());
        assertEq(111,r_.getAnnotCandidatesCallsInitSuppl().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitSuppl().get(0).getCallee().getFile().getFileName());
        assertEq(166,r_.getAnnotCandidatesCallsInitSuppl().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitSuppl().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getAnnotCandidatesCallsInitArobaseSuppl().size());
        assertEq(214,r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getFile().getFileName());
        assertEq(62,r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getCallee().getFile().getFileName());
        assertEq(166,r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseSuppl().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs87() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("$void $this($int i,$int $value){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("Annot g()@Annot(f=2);\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Annot).getDeclaredMethods()[0].getDefaultValue()).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",131);
        assertEq(1,r_.getAnnotCandidatesCallsInitDefValue().size());
        assertEq(225,r_.getAnnotCandidatesCallsInitDefValue().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitDefValue().get(0).getFile().getFileName());
        assertEq(100,r_.getAnnotCandidatesCallsInitDefValue().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitDefValue().get(0).getCallee().getFile().getFileName());
        assertEq(177,r_.getAnnotCandidatesCallsInitDefValue().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitDefValue().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getAnnotCandidatesCallsInitArobaseDefValue().size());
        assertEq(225,r_.getAnnotCandidatesCallsInitArobaseDefValue().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseDefValue().get(0).getFile().getFileName());
        assertEq(127,r_.getAnnotCandidatesCallsInitArobaseDefValue().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseDefValue().get(0).getCallee().getFile().getFileName());
        assertEq(177,r_.getAnnotCandidatesCallsInitArobaseDefValue().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseDefValue().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs88() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($null)[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",27);
        assertEq(1,r_.getAnnotCandidatesCallsInitMembers().size());
        assertEq(195,r_.getAnnotCandidatesCallsInitMembers().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getFile().getFileName());
        assertEq(88,r_.getAnnotCandidatesCallsInitMembers().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitMembers().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getAnnotCandidatesCallsInitArobaseMembers().size());
        assertEq(195,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getFile().getFileName());
        assertEq(26,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs89() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@Annot(f=2)\n");
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",1);
        assertEq(1,r_.getAnnotCandidatesCallsInitMembers().size());
        assertEq(167,r_.getAnnotCandidatesCallsInitMembers().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getFile().getFileName());
        assertEq(88,r_.getAnnotCandidatesCallsInitMembers().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitMembers().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitMembers().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getAnnotCandidatesCallsInitArobaseMembers().size());
        assertEq(167,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getFile().getFileName());
        assertEq(0,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCallee().getFile().getFileName());
        assertEq(143,r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getAnnotCandidatesCallsInitArobaseMembers().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs90() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("@Annot(f=2)\n");
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE(1),\n");
        xml_.append("TWO(2);\n");
        xml_.append("($int i){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",1);
        assertEq(1,r_.getInstanceArobase().size());
        assertEq(1,r_.getInstanceArobase().get(0).getIndex());
        assertEq("pkg/Ex",r_.getInstanceArobase().get(0).getFile().getFileName());
        assertEq(88,r_.getInstanceArobase().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getInstanceArobase().get(0).getCallee().getFile().getFileName());
        assertEq(0,r_.getInstanceArobase().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getInstanceArobase().get(0).getCaller().getFile().getFileName());
        assertSame(EnSrcLocation.ANNOTATION,r_.getInstanceArobase().get(0).getCaller().build(null).getKind());
    }
    @Test
    public void refs91() {
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
        CallersRef r_ = refs(files_,"pkg/Ex",105);
        assertEq(1,r_.getTypesFinders().size());
        assertEq(105,r_.getTypesFinders().get(0).getIndex());
        assertEq("pkg/Ex",r_.getTypesFinders().get(0).getFile().getFileName());
        assertEq(26,r_.getTypesFinders().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getTypesFinders().get(0).getCallee().getFile().getFileName());
        assertEq(49,r_.getTypesFinders().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getTypesFinders().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs92() {
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
        CallersRef r_ = refs(files_,"pkg/Ex",78);
        assertEq(1,r_.getTypesFinders().size());
        assertEq(77,r_.getTypesFinders().get(0).getIndex());
        assertEq("pkg/Ex",r_.getTypesFinders().get(0).getFile().getFileName());
        assertEq(15,r_.getTypesFinders().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getTypesFinders().get(0).getCallee().getFile().getFileName());
        assertEq(52,r_.getTypesFinders().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getTypesFinders().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs93() {
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
        CallersRef r_ = refs(files_,"pkg/Ex",60);
        assertEq(1,r_.getStaticAccess().size());
        assertEq(59,r_.getStaticAccess().get(0).getIndex());
        assertEq("pkg/Ex",r_.getStaticAccess().get(0).getFile().getFileName());
        assertEq(15,r_.getStaticAccess().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getStaticAccess().get(0).getCallee().getFile().getFileName());
        assertEq(49,r_.getStaticAccess().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getStaticAccess().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs94() {
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
        CallersRef r_ = refs(files_,"pkg/Ex",84);
        assertEq(1,r_.getStaticAccess().size());
        assertEq(84,r_.getStaticAccess().get(0).getIndex());
        assertEq("pkg/Ex",r_.getStaticAccess().get(0).getFile().getFileName());
        assertEq(25,r_.getStaticAccess().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getStaticAccess().get(0).getCallee().getFile().getFileName());
        assertEq(56,r_.getStaticAccess().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getStaticAccess().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs95() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$public $staticCall $void method(){\n");
        xml_.append("$staticCall(Outer<String>).callee();\n");
        xml_.append("$staticCall(Outer<StringBuilder>).callee();\n");
        xml_.append("}\n");
        xml_.append("$public $staticCall $void callee(){\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",84);
        assertEq(1,r_.getStaticAccess().size());
        assertEq(84,r_.getStaticAccess().get(0).getIndex());
        assertEq("pkg/Ex",r_.getStaticAccess().get(0).getFile().getFileName());
        assertEq(0,r_.getStaticAccess().get(0).getCallee().getIndex());
        assertEq("",r_.getStaticAccess().get(0).getCallee().getFileName());
        assertEq(56,r_.getStaticAccess().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getStaticAccess().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs96() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer[]{};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",45);
        assertEq(1,r_.getInstanceNewTypesEltArray().size());
        assertEq(45,r_.getInstanceNewTypesEltArray().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesEltArray().get(0).getFile().getFileName());
        assertEq(15,r_.getInstanceNewTypesEltArray().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getInstanceNewTypesEltArray().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getInstanceNewTypesEltArray().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesEltArray().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs97() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer[0];}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",45);
        assertEq(1,r_.getInstanceNewTypesEltArray().size());
        assertEq(45,r_.getInstanceNewTypesEltArray().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesEltArray().get(0).getFile().getFileName());
        assertEq(15,r_.getInstanceNewTypesEltArray().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getInstanceNewTypesEltArray().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getInstanceNewTypesEltArray().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesEltArray().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs98() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("@Annot2(g=@Annot(f=2))\n");
        xml_.append("ONE,\n");
        xml_.append("TWO;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $annotation pkg.Annot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.Annot2{\n");
        xml_.append("Annot g();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",37);
        assertEq(1,r_.getInstanceArobase().size());
        assertEq(37,r_.getInstanceArobase().get(0).getIndex());
        assertEq("pkg/Ex",r_.getInstanceArobase().get(0).getFile().getFileName());
        assertEq(20,r_.getInstanceArobase().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getInstanceArobase().get(0).getCallee().getFile().getFileName());
        assertEq(26,r_.getInstanceArobase().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getInstanceArobase().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs99() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("$static $int THREE(){$return 1;}\n");
        xml_.append("$operator- $staticCall Outer(Outer o,Outer p){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{Outer a=$operator(-,Outer)(a,Outer.ONE);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",60);
        assertEq(1,r_.getTypesFinders().size());
        assertEq(60,r_.getTypesFinders().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getTypesFinders().get(0).getFile().getFileName());
        assertEq(14,r_.getTypesFinders().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getTypesFinders().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getTypesFinders().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getTypesFinders().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs100() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getAnnotations()[0]).f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",27);
        assertEq(0,r_.getAnnotCandidatesCallsInitMembers().size());
        assertEq(0,r_.getAnnotCandidatesCallsInitArobaseMembers().size());
    }
    @Test
    public void refs101() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T>:Outer3 {\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer<Outer3>[0];}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",51);
        assertEq(1,r_.getInstanceNewTypesEltArray().size());
        assertEq(51,r_.getInstanceNewTypesEltArray().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesEltArray().get(0).getFile().getFileName());
        assertEq(19,r_.getInstanceNewTypesEltArray().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInstanceNewTypesEltArray().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getInstanceNewTypesEltArray().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesEltArray().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs102() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$public $class Inner<T>{}\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer().$new Inner<Outer3>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",64);
        assertEq(1,r_.getInstanceNewTypesElt().size());
        assertEq(64,r_.getInstanceNewTypesElt().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesElt().get(0).getFile().getFileName());
        assertEq(19,r_.getInstanceNewTypesElt().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInstanceNewTypesElt().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getInstanceNewTypesElt().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getInstanceNewTypesElt().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs103() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",59);
        assertEq(1,r_.getTypesFindersRef().size());
        assertEq(59,r_.getTypesFindersRef().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getTypesFindersRef().get(0).getFile().getFileName());
        assertEq(19,r_.getTypesFindersRef().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getTypesFindersRef().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getTypesFindersRef().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getTypesFindersRef().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs104() {
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
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int f=((Annot)$class(Outer).getDeclaredFields(\"ONE\")[0].getAnnotations($class(SecAnnot))[0]).f();\n");
        xml_.append("}\n");
        xml_.append("$public $annotation pkg.SecAnnot{\n");
        xml_.append("$int f();\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",27);
        assertEq(0,r_.getAnnotCandidatesCallsInitMembers().size());
        assertEq(0,r_.getInterfacesInit().size());
        assertEq(0,r_.getInterfacesInitRef().size());
    }
    @Test
    public void refs105() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",47);
        assertEq(1,r_.getCallNamedUse().size());
        assertEq(47,r_.getCallNamedUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(0).getFile().getFileName());
        assertEq(57,r_.getCallNamedUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCallNamedUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallNamedUse().get(0).getCaller().getFile().getFileName());
        assertEq(0,r_.getReturnType().size());
    }
    @Test
    public void refs106() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",56);
        assertEq(1,r_.getStaticAccess().size());
        assertEq(56,r_.getStaticAccess().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getStaticAccess().get(0).getFile().getFileName());
        assertEq(14,r_.getStaticAccess().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getStaticAccess().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getStaticAccess().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getStaticAccess().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs107() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",55);
        assertEq(1,r_.getStaticAccess().size());
        assertEq(55,r_.getStaticAccess().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getStaticAccess().get(0).getFile().getFileName());
        assertEq(14,r_.getStaticAccess().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getStaticAccess().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getStaticAccess().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getStaticAccess().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs108() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",51);
        assertEq(1,r_.getCastOperation().size());
        assertEq(51,r_.getCastOperation().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCastOperation().get(0).getFile().getFileName());
        assertEq(15,r_.getCastOperation().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCastOperation().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getCastOperation().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCastOperation().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs109() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",67);
        assertEq(1,r_.getInstanceOperation().size());
        assertEq(67,r_.getInstanceOperation().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getInstanceOperation().get(0).getFile().getFileName());
        assertEq(14,r_.getInstanceOperation().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getInstanceOperation().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getInstanceOperation().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getInstanceOperation().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs110() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Caller{\n");
        xml_.append("$int g=$switch[$short:@Annot(e=2):@Annot(e=2)](0){$default;$return 0;};\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",42);
        assertEq(1,r_.getReturnType().size());
        assertEq(42,r_.getReturnType().get(0).getIndex());
        assertEq("pkg/Ex",r_.getReturnType().get(0).getFile().getFileName());
        assertEq(0,r_.getReturnType().get(0).getCallee().getIndex());
        assertEq("",r_.getReturnType().get(0).getCallee().getFileName());
        assertEq(32,r_.getReturnType().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getReturnType().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs111() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$public $class Inner<T>{}\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer().$new Inner<Outer3>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",64);
        assertEq(1,r_.getInstanceNewTypesEltFwd().size());
        assertEq(107,r_.getInstanceNewTypesEltFwd().get(0).getIndex());
        assertEq("pkg/Ex",r_.getInstanceNewTypesEltFwd().get(0).getFile().getFileName());
        assertEq(19,r_.getInstanceNewTypesEltFwd().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInstanceNewTypesEltFwd().get(0).getCallee().getFile().getFileName());
        assertEq(80,r_.getInstanceNewTypesEltFwd().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getInstanceNewTypesEltFwd().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs112() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",73);
        assertEq(1,r_.getTypesFinders().size());
        assertEq(73,r_.getTypesFinders().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getTypesFinders().get(0).getFile().getFileName());
        assertEq(15,r_.getTypesFinders().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getTypesFinders().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getTypesFinders().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getTypesFinders().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs113() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",80);
        assertEq(1,r_.getTypesInfosDef().size());
        assertEq(80,r_.getTypesInfosDef().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getTypesInfosDef().get(0).getFile().getFileName());
        assertEq(14,r_.getTypesInfosDef().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getTypesInfosDef().get(0).getCallee().getFile().getFileName());
        assertEq(60,r_.getTypesInfosDef().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getTypesInfosDef().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs114() {
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
        CallersRef r_ = refs(files_,"pkg/Ex2",54);
        assertEq(1,r_.getTypesInfos().size());
        assertEq(54,r_.getTypesInfos().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getTypesInfos().get(0).getFile().getFileName());
        assertEq(14,r_.getTypesInfos().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getTypesInfos().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getTypesInfos().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getTypesInfos().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs115() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer a;\n");
        xml_.append("Outer b;\n");
        xml_.append("b=a->0;\n");
        xml_.append("(($Fct)b).call(0);\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",81);
        assertEq(1,r_.getCallAnonRefUse().size());
        assertEq(79,r_.getCallAnonRefUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getCallAnonRefUse().get(0).getFile().getFileName());
        assertEq(80,r_.getCallAnonRefUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getCallAnonRefUse().get(0).getCallee().getFile().getFileName());
        assertEq(49,r_.getCallAnonRefUse().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getCallAnonRefUse().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getDynCallPotential().size());
        assertEq(95,r_.getDynCallPotential().get(0).getIndex());
        assertEq("pkg/Ex",r_.getDynCallPotential().get(0).getFile().getFileName());
        assertEq(80,r_.getDynCallPotential().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getDynCallPotential().get(0).getCallee().getFile().getFileName());
        assertEq(49,r_.getDynCallPotential().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getDynCallPotential().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs116() {
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
        xml_.append("$public $enum pkg.Outer2<T:Outer3> {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(){};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("$void m()$intern(Outer3:m(Outer3)){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",52);
        assertEq(1,r_.getInternEltsFct().size());
        assertEq(52,r_.getInternEltsFct().get(0).getIndex());
        assertEq("pkg/Ex3",r_.getInternEltsFct().get(0).getFile().getFileName());
        assertEq(34,r_.getInternEltsFct().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInternEltsFct().get(0).getCallee().getFile().getFileName());
        assertEq(34,r_.getInternEltsFct().get(0).getCaller().getIndex());
        assertEq("pkg/Ex3",r_.getInternEltsFct().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs117() {
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
        xml_.append("$public $enum pkg.Outer2<T:Outer3> {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(){};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("$intern{m():m(Outer3)};\n");
        xml_.append("$void m()$intern(Outer3:m(Outer3);Outer3:m(Outer3)){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",40);
        assertEq(1,r_.getInternElts().size());
        assertEq(40,r_.getInternElts().get(0).getIndex());
        assertEq("pkg/Ex3",r_.getInternElts().get(0).getFile().getFileName());
        assertEq(58,r_.getInternElts().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInternElts().get(0).getCallee().getFile().getFileName());
        assertEq(15,r_.getInternElts().get(0).getCaller().getIndex());
        assertEq("pkg/Ex3",r_.getInternElts().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs118() {
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
        xml_.append("$public $enum pkg.Outer2<T:Outer3> {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(){};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("$void m()$intern(Outer3:m(Outer3)){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",45);
        assertEq(2,r_.getInternEltsFct().size());
        assertEq(45,r_.getInternEltsFct().get(0).getIndex());
        assertEq("pkg/Ex3",r_.getInternEltsFct().get(0).getFile().getFileName());
        assertEq(15,r_.getInternEltsFct().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInternEltsFct().get(0).getCallee().getFile().getFileName());
        assertEq(34,r_.getInternEltsFct().get(0).getCaller().getIndex());
        assertEq("pkg/Ex3",r_.getInternEltsFct().get(0).getCaller().getFile().getFileName());
        assertEq(54,r_.getInternEltsFct().get(1).getIndex());
        assertEq("pkg/Ex3",r_.getInternEltsFct().get(1).getFile().getFileName());
        assertEq(15,r_.getInternEltsFct().get(1).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInternEltsFct().get(1).getCallee().getFile().getFileName());
        assertEq(34,r_.getInternEltsFct().get(1).getCaller().getIndex());
        assertEq("pkg/Ex3",r_.getInternEltsFct().get(1).getCaller().getFile().getFileName());
    }
    @Test
    public void refs119() {
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
        xml_.append("$public $enum pkg.Outer2<T:Outer3> {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(){};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("$intern{m():m(Outer3)};\n");
        xml_.append("$void m()$intern(Outer3:m(Outer3);Outer3:m(Outer3)){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex3",42);
        assertEq(1,r_.getInternElts().size());
        assertEq(42,r_.getInternElts().get(0).getIndex());
        assertEq("pkg/Ex3",r_.getInternElts().get(0).getFile().getFileName());
        assertEq(15,r_.getInternElts().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInternElts().get(0).getCallee().getFile().getFileName());
        assertEq(15,r_.getInternElts().get(0).getCaller().getIndex());
        assertEq("pkg/Ex3",r_.getInternElts().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs120() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static Outer3 $(Outer o){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",44);
        assertEq(1,r_.getParamType().size());
        assertEq(44,r_.getParamType().get(0).getIndex());
        assertEq("pkg/Ex",r_.getParamType().get(0).getFile().getFileName());
        assertEq(15,r_.getParamType().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getParamType().get(0).getCallee().getFile().getFileName());
        assertEq(42,r_.getParamType().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getParamType().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs121() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$static Outer2 $(Outer o){$return $null;}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2 {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",35);
        assertEq(1,r_.getReturnType().size());
        assertEq(35,r_.getReturnType().get(0).getIndex());
        assertEq("pkg/Ex",r_.getReturnType().get(0).getFile().getFileName());
        assertEq(15,r_.getReturnType().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getReturnType().get(0).getCallee().getFile().getFileName());
        assertEq(42,r_.getReturnType().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getReturnType().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs122() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer a;\n");
        xml_.append("Outer b;\n");
        xml_.append("b=(Outer a:Outer)->a;\n");
        xml_.append("(($Fct)b).call(0);\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",80);
        assertEq(1,r_.getParamType().size());
        assertEq(80,r_.getParamType().get(0).getIndex());
        assertEq("pkg/Ex",r_.getParamType().get(0).getFile().getFileName());
        assertEq(15,r_.getParamType().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getParamType().get(0).getCallee().getFile().getFileName());
        assertEq(94,r_.getParamType().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getParamType().get(0).getCaller().getFile().getFileName());
        assertEq(1,r_.getReturnType().size());
        assertEq(88,r_.getReturnType().get(0).getIndex());
        assertEq("pkg/Ex",r_.getReturnType().get(0).getFile().getFileName());
        assertEq(15,r_.getReturnType().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getReturnType().get(0).getCallee().getFile().getFileName());
        assertEq(94,r_.getReturnType().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getReturnType().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs123() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("Outer b;\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer a;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",68);
        assertEq(1,r_.getVariableDeclaring().size());
        assertEq(68,r_.getVariableDeclaring().get(0).getIndex());
        assertEq("pkg/Ex",r_.getVariableDeclaring().get(0).getFile().getFileName());
        assertEq(15,r_.getVariableDeclaring().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariableDeclaring().get(0).getCallee().getFile().getFileName());
        assertEq(58,r_.getVariableDeclaring().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getVariableDeclaring().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs124() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer {\n");
        xml_.append("Outer b;\n");
        xml_.append("$public $static $void method(){\n");
        xml_.append("Outer a;\n");
        xml_.append("}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",27);
        assertEq(1,r_.getFieldDeclaring().size());
        assertEq(27,r_.getFieldDeclaring().get(0).getIndex());
        assertEq("pkg/Ex",r_.getFieldDeclaring().get(0).getFile().getFileName());
        assertEq(15,r_.getFieldDeclaring().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getFieldDeclaring().get(0).getCallee().getFile().getFileName());
        assertEq(33,r_.getFieldDeclaring().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getFieldDeclaring().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs125() {
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
        xml_.append("$public $enum pkg.Outer2<T:Outer3> {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer(){};}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer3 {\n");
        xml_.append("$intern{m():m(Outer3)};\n");
        xml_.append("$void m()$intern(Outer3:m(Outer3);Outer3:m(Outer3)){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",27);
        assertEq(1,r_.getConstraints().size());
        assertEq(27,r_.getConstraints().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getConstraints().get(0).getFile().getFileName());
        assertEq(15,r_.getConstraints().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getConstraints().get(0).getCallee().getFile().getFileName());
        assertEq(14,r_.getConstraints().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getConstraints().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs126() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer:Outer3 {\n");
        xml_.append("$public $class Inner<T>{}\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer().$new Inner<Outer3>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",25);
        assertEq(1,r_.getInherits().size());
        assertEq(25,r_.getInherits().get(0).getIndex());
        assertEq("pkg/Ex",r_.getInherits().get(0).getFile().getFileName());
        assertEq(19,r_.getInherits().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInherits().get(0).getCallee().getFile().getFileName());
        assertEq(15,r_.getInherits().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getInherits().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs127() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class $interfaces(Outer3;Outer3) pkg.Outer:Outer3 {\n");
        xml_.append("$public $class Inner<T>{}\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer().$new Inner<Outer3>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",35);
        assertEq(1,r_.getInterfacesInstance().size());
        assertEq(35,r_.getInterfacesInstance().get(0).getIndex());
        assertEq("pkg/Ex",r_.getInterfacesInstance().get(0).getFile().getFileName());
        assertEq(19,r_.getInterfacesInstance().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInterfacesInstance().get(0).getCallee().getFile().getFileName());
        assertEq(43,r_.getInterfacesInstance().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getInterfacesInstance().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs128() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public @$class $interfaces(Outer3;Outer3) pkg.Outer:Outer3 {\n");
        xml_.append("$public $class Inner<T>{}\n");
        xml_.append("Outer3(){$this(0);}\n");
        xml_.append("Outer3($int i){$interfaces(Outer3)();}\n");
        xml_.append("$int $this($int v){$return 1;}\n");
        xml_.append("$void $this($int v){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $enum pkg.Outer2 {\n");
        xml_.append("ONE,\n");
        xml_.append("TWO{};\n");
        xml_.append("{$new Outer().$new Inner<Outer3>();}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Outer3 {\n");
        xml_.append("$int field;\n");
        xml_.append("}\n");
        files_.put("pkg/Ex3", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex",28);
        assertEq(1,r_.getInterfacesStatic().size());
        assertEq(28,r_.getInterfacesStatic().get(0).getIndex());
        assertEq("pkg/Ex",r_.getInterfacesStatic().get(0).getFile().getFileName());
        assertEq(19,r_.getInterfacesStatic().get(0).getCallee().getIndex());
        assertEq("pkg/Ex3",r_.getInterfacesStatic().get(0).getCallee().getFile().getFileName());
        assertEq(43,r_.getInterfacesStatic().get(0).getCaller().getIndex());
        assertEq("pkg/Ex",r_.getInterfacesStatic().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs129() {
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
        xml_.append("{$Fct<String,$int> v;v.call(\"\");}\n");
        xml_.append("{$Fct<$int,String> v;v.call(0);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",62);
        assertEq(1,r_.getCallDyn().size());
        assertEq(62,r_.getCallDyn().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getCallDyn().get(0).getFile().getFileName());
        assertEq(38,r_.getCallDyn().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getCallDyn().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs130() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer<T> {\n");
        xml_.append("$void method(T t){}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Outer2<T> {\n");
        xml_.append("$void m(T t){$new Outer<T>().$lambda(Outer<T>,method,T).call(t);}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",77);
        assertFalse(r_.getDynCallPotential().isEmpty());
    }
    @Test
    public void refs131() {
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
        xml_.append("{$for(Outer o,$var p:$($iterableTable<Outer,Outer2>)$null){}}\n");
        xml_.append("{$for(Outer2 o,$var p:$($iterableTable<Outer2,Outer>)$null){}}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",53);
        assertEq(1,r_.getVariableDeclaring().size());
        assertEq(53,r_.getVariableDeclaring().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariableDeclaring().get(0).getFile().getFileName());
        assertEq(53,r_.getVariableDeclaring().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariableDeclaring().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariableDeclaring().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariableDeclaring().get(0).getCaller().getFile().getFileName());
    }
    @Test
    public void refs132() {
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
        xml_.append("{$for($var o,Outer2 p:$($iterableTable<Outer,Outer2>)$null){}}\n");
        xml_.append("{$for(Outer o,$var p:$($iterableTable<Outer,Outer2>)$null){}}\n");
        xml_.append("}\n");
        files_.put("pkg/Ex2", xml_.toString());
        CallersRef r_ = refs(files_,"pkg/Ex2",45);
        assertEq(1,r_.getVariableDeclaring().size());
        assertEq(45,r_.getVariableDeclaring().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariableDeclaring().get(0).getFile().getFileName());
        assertEq(45,r_.getVariableDeclaring().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariableDeclaring().get(0).getCallee().getFile().getFileName());
        assertEq(38,r_.getVariableDeclaring().get(0).getCaller().getIndex());
        assertEq("pkg/Ex2",r_.getVariableDeclaring().get(0).getCaller().getFile().getFileName());
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
