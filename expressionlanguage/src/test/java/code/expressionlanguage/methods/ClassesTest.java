package code.expressionlanguage.methods;
import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Classes;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class ClassesTest {

    @Test(timeout=1000)
    public void initTest() {
        String xml_ = "<class name='Ex' package='pkg'>\n";
        xml_ += "<method name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"'>\n";
        xml_ += "<return expression='1i+1i'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>";
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = new ContextEl();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        Classes classes_ = new Classes(files_, cont_);
        cont_.setClasses(classes_);
        classes_.validateClassBodies(cont_);
        classes_.validateClassNames(cont_);
        classes_.validateMethodNames(cont_);
        classes_.validateMethodsId(cont_);
        classes_.validateLocalVariableNamesId(cont_);
        classes_.validateEl(cont_);
    }

    @Test(timeout=1000)
    public void init2Test() {
        String xml_ = "<class name='Ex' package='pkg'>\n";
        xml_ += "<method name='exmeth' class='"+PrimitiveTypeUtil.PRIM_INT+"'>\n";
        xml_ += "<declare var='t' class='"+PrimitiveTypeUtil.PRIM_LONG+"'/>\n";
        xml_ += "<affect left='t;.' oper='=' right='8'/>\n";
//        xml_ += "<return expression='1i+class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)'/>\n";
        xml_ += "<return expression='1i+^class(&quot;"+PrimitiveTypeUtil.PRIM_INT+"&quot;,t;.)'/>\n";
        xml_ += "</method>\n";
        xml_ += "</class>";
        StringMap<String> files_ = new StringMap<String>();
        ContextEl cont_ = new ContextEl();
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        Classes classes_ = new Classes(files_, cont_);
        cont_.setClasses(classes_);
        classes_.validateClassBodies(cont_);
        classes_.validateClassNames(cont_);
        classes_.validateMethodNames(cont_);
        classes_.validateMethodsId(cont_);
        classes_.validateLocalVariableNamesId(cont_);
        classes_.validateEl(cont_);
    }
}
