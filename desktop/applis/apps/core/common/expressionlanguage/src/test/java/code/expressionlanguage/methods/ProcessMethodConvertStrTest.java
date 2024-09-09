package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import org.junit.Test;

public final class ProcessMethodConvertStrTest extends ProcessMethodCommon {
    @Test
    public void convertStr1() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");
        Struct ret_ =  instanceNormal("pkg.Ex", id_, cont_);
        assertEq("pkg.Ex",convertStr(ret_,cont_));
    }
    @Test
    public void convertStr2() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public String $toString()\n");
        xml_.append(" {\n");
        xml_.append("  $return \"sample\";\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = ctxReadOnlyOk(files_);
        ConstructorId id_ = getConstructorId("pkg.Ex");
        Struct ret_ =  instanceNormal("pkg.Ex", id_, cont_);
        assertEq("sample",convertStr(ret_,cont_));
    }
}
