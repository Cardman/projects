package code.expressionlanguage.methods;
import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.CustList;
import code.util.EqList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class ClassesTest {
    private static final String PUBLIC_ACCESS = "PUBLIC";

    @Test
    public void getMethodBodiesByFormattedId1Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='"+PrimitiveTypeUtil.PRIM_INT+"'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId("pkg.Ex<#F>", "instancemethod", new StringList(PrimitiveTypeUtil.PRIM_INT));
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq(PrimitiveTypeUtil.PRIM_INT, methods_.first().getId().getClassNames().first().getName());
    }

    @Test
    public void getMethodBodiesByFormattedId2Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId("pkg.Ex<#F>", "instancemethod", new StringList("#F"));
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
    }

    @Test
    public void getMethodBodiesByFormattedId3Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId("pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"));
        assertEq(1, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
    }

    @Test
    public void getMethodBodiesByFormattedId4Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId("pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.String"));
        assertEq(0, methods_.size());
    }

    @Test
    public void getMethodBodiesByFormattedId5Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='#E'>\n";
        xml_ += "</method>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' name='instancemethod' class='"+OperationNode.VOID_RETURN+"' modifier='normal' var0='i' class0='java.lang.Object'>\n";
        xml_ += "</method>\n";
        xml_ += "</class>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingClasses(files_);
        Classes classes_ = context_.getClasses();
        CustList<MethodBlock> methods_ = classes_.getMethodBodiesByFormattedId("pkg.Ex<java.lang.Object>", "instancemethod", new StringList("java.lang.Object"));
        assertEq(2, methods_.size());
        assertEq("instancemethod", methods_.first().getName());
        assertEq(1, methods_.first().getId().getClassNames().size());
        assertEq("#E", methods_.first().getId().getClassNames().first().getName());
        assertEq("instancemethod", methods_.last().getName());
        assertEq(1, methods_.last().getId().getClassNames().size());
        assertEq("java.lang.Object", methods_.last().getId().getClassNames().first().getName());
    }

    @Test
    public void getSortedSuperInterfaces1Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(files_, cont_);
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"));
        assertEq(1, s_.size());
        assertEq("pkg.Ex", s_.first());
    }

    @Test
    public void getSortedSuperInterfaces2Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(files_, cont_);
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"));
        assertEq(2, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces3Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(files_, cont_);
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"));
        assertEq(3, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.ExThree", s_.get(1));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces4Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree' class2='pkg.ExFour'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'/>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(files_, cont_);
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"));
        assertEq(4, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.ExThree", s_.get(1));
        assertEq("pkg.ExFour", s_.get(2));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces5Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'/>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(files_, cont_);
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"));
        assertEq(4, s_.size());
        assertEq("pkg.ExFour", s_.first());
        assertEq("pkg.ExTwo", s_.get(1));
        assertEq("pkg.ExThree", s_.get(2));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces6Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'/>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(files_, cont_);
        cont_.setClasses(classes_);
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex","pkg.ExFive"));
        assertEq(5, s_.size());
        assertEq("pkg.ExFour", s_.first());
        assertEq("pkg.ExTwo", s_.get(1));
        assertEq("pkg.ExThree", s_.get(2));
        assertEq("pkg.Ex", s_.get(3));
        assertEq("pkg.ExFive", s_.last());
    }

    @Test
    public void getAllOverridingMethods1Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='absgetter' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        InterfaceBlock i_ = (InterfaceBlock) classes_.getClassBody("pkg.ExFour");
        ObjectMap<FctConstraints, StringList> sgn_ = i_.getAllInstanceSignatures(classes_);
        sgn_ = RootBlock.getAllOverridingMethods(sgn_, classes_);
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExFour"),sgn_.getVal(new FctConstraints("absgetter", new EqList<StringList>())));
    }

    @Test
    public void getAllOverridingMethods2Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='absgetter' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='absgetter' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        InterfaceBlock i_ = (InterfaceBlock) classes_.getClassBody("pkg.Ex");
        ObjectMap<FctConstraints, StringList> sgn_ = i_.getAllInstanceSignatures(classes_);
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.Ex","pkg.ExFour"),sgn_.getVal(new FctConstraints("absgetter", new EqList<StringList>())));
        sgn_ = RootBlock.getAllOverridingMethods(sgn_, classes_);
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.Ex"),sgn_.getVal(new FctConstraints("absgetter", new EqList<StringList>())));
    }

    @Test
    public void getAllOverridingMethods3Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='absgetter' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' class0='pkg.ExFour'>\n";
        xml_ += "<method access='"+PUBLIC_ACCESS+"' modifier='abstract' name='absgetter' class='"+PrimitiveTypeUtil.PRIM_INT+"'/>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg'>\n";
        xml_ += "</interface>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFive' package='pkg' class0='pkg.ExTwo' class1='pkg.ExThree'/>\n";
        files_.put("pkg/ExFive."+Classes.EXT, xml_);
        ContextEl context_ = unfullValidateOverridingMethods(files_);
        Classes classes_ = context_.getClasses();
        InterfaceBlock i_ = (InterfaceBlock) classes_.getClassBody("pkg.Ex");
        ObjectMap<FctConstraints, StringList> sgn_ = i_.getAllInstanceSignatures(classes_);
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExTwo","pkg.ExThree"),sgn_.getVal(new FctConstraints("absgetter", new EqList<StringList>())));
        sgn_ = RootBlock.getAllOverridingMethods(sgn_, classes_);
        assertEq(1, sgn_.size());
        assertEq(new StringList("pkg.ExTwo","pkg.ExThree"),sgn_.getVal(new FctConstraints("absgetter", new EqList<StringList>())));
    }
    private ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(_files, cont_);
        cont_.setClasses(classes_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateIds(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        return cont_;
    }
    private ContextEl unfullValidateOverridingClasses(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(_files, cont_);
        cont_.setClasses(classes_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateSingleParameterizedClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        return cont_;
    }
}
