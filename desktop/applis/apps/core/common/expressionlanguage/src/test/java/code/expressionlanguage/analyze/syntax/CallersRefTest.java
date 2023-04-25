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
        assertEq(2,r_.getVariablesParamsUse().size());
        assertEq(71,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(71,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(89,r_.getVariablesParamsUse().get(1).getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(1).getFile().getFileName());
        assertEq(71,r_.getVariablesParamsUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(1).getCallee().getFile().getFileName());
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
        assertEq(2,r_.getVariablesParamsUse().size());
        assertEq(71,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(71,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(127,r_.getVariablesParamsUse().get(1).getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(1).getFile().getFileName());
        assertEq(71,r_.getVariablesParamsUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(1).getCallee().getFile().getFileName());
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
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(45,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
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
        assertEq(2,r_.getVariablesParamsUse().size());
        assertEq(45,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(51,r_.getVariablesParamsUse().get(1).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getCallee().getFile().getFileName());
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
        assertEq(2,r_.getVariablesParamsUse().size());
        assertEq(45,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(78,r_.getVariablesParamsUse().get(1).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getCallee().getFile().getFileName());
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
        assertEq(4,r_.getVariablesParamsUse().size());
        assertEq(132,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(132,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(164,r_.getVariablesParamsUse().get(1).getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(1).getFile().getFileName());
        assertEq(164,r_.getVariablesParamsUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(1).getCallee().getFile().getFileName());
        assertEq(57,r_.getVariablesParamsUse().get(2).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(2).getFile().getFileName());
        assertEq(132,r_.getVariablesParamsUse().get(2).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(2).getCallee().getFile().getFileName());
        assertEq(57,r_.getVariablesParamsUse().get(3).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(3).getFile().getFileName());
        assertEq(164,r_.getVariablesParamsUse().get(3).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(3).getCallee().getFile().getFileName());
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
        assertEq(4,r_.getVariablesParamsUse().size());
        assertEq(125,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(125,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(171,r_.getVariablesParamsUse().get(1).getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(1).getFile().getFileName());
        assertEq(171,r_.getVariablesParamsUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(1).getCallee().getFile().getFileName());
        assertEq(53,r_.getVariablesParamsUse().get(2).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(2).getFile().getFileName());
        assertEq(125,r_.getVariablesParamsUse().get(2).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(2).getCallee().getFile().getFileName());
        assertEq(53,r_.getVariablesParamsUse().get(3).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(3).getFile().getFileName());
        assertEq(171,r_.getVariablesParamsUse().get(3).getCallee().getIndex());
        assertEq("pkg/Ex",r_.getVariablesParamsUse().get(3).getCallee().getFile().getFileName());
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
        assertEq(2,r_.getVariablesParamsUse().size());
        assertEq(45,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(54,r_.getVariablesParamsUse().get(1).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getCallee().getFile().getFileName());
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
        assertEq(3,r_.getVariablesParamsUse().size());
        assertEq(45,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
        assertEq(58,r_.getVariablesParamsUse().get(1).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(1).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(1).getCallee().getFile().getFileName());
        assertEq(74,r_.getVariablesParamsUse().get(2).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(2).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(2).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(2).getCallee().getFile().getFileName());
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
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(50,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(50,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
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
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(50,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(50,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
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
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(50,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(50,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
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
        assertEq(1,r_.getVariablesParamsUse().size());
        assertEq(45,r_.getVariablesParamsUse().get(0).getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getFile().getFileName());
        assertEq(45,r_.getVariablesParamsUse().get(0).getCallee().getIndex());
        assertEq("pkg/Ex2",r_.getVariablesParamsUse().get(0).getCallee().getFile().getFileName());
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
