package code.expressionlanguage.methods;

import static org.junit.Assert.assertTrue;
import static code.expressionlanguage.EquallableElUtil.assertEq;

import org.junit.Test;

import code.expressionlanguage.AccessValueEx;
import code.expressionlanguage.ContextEl;
import code.util.StringList;
import code.util.StringMap;

@SuppressWarnings("static-method")
public class RootBlockTest {
    private static final String PUBLIC_ACCESS = "PUBLIC";

    @Test
    public void getAllGenericSuperTypes1Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExTwo").getAllGenericSuperTypes(cont_);
        assertEq(2, superTypes_.size());
        assertEq("pkg.Ex<#T>", superTypes_.first());
        assertEq(Object.class.getName(), superTypes_.last());
    }

    @Test
    public void getAllGenericSuperTypes2Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' template='&lt;#S&gt;' superclass='pkg.ExTwo&lt;#S&gt;'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExThree").getAllGenericSuperTypes(cont_);
        assertEq(3, superTypes_.size());
        assertEq("pkg.ExTwo<#S>", superTypes_.first());
        assertEq("pkg.Ex<#S>", superTypes_.get(1));
        assertEq(Object.class.getName(), superTypes_.last());
    }

    @Test
    public void getAllGenericSuperTypes3Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' superclass='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<class access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' superclass='pkg.ExTwo&lt;java.lang.String&gt;'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExThree").getAllGenericSuperTypes(cont_);
        assertEq(3, superTypes_.size());
        assertEq("pkg.ExTwo<java.lang.String>", superTypes_.first());
        assertEq("pkg.Ex<java.lang.String>", superTypes_.get(1));
        assertEq(Object.class.getName(), superTypes_.last());
    }

    @Test
    public void getAllGenericSuperTypes4Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' class0='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' template='&lt;#S&gt;' class0='pkg.Ex&lt;#S&gt;'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg' class0='pkg.ExTwo&lt;java.lang.String&gt;' class1='pkg.ExThree&lt;java.lang.String&gt;'/>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExFour").getAllGenericSuperTypes(cont_);
        assertEq(4, superTypes_.size());
        assertEq("pkg.ExTwo<java.lang.String>", superTypes_.first());
        assertEq("pkg.ExThree<java.lang.String>", superTypes_.get(1));
        assertEq("pkg.Ex<java.lang.String>", superTypes_.get(2));
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
    }

    @Test
    public void getAllGenericSuperTypes5Test() {
        StringMap<String> files_ = new StringMap<String>();
        String xml_;
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='Ex' package='pkg' template='&lt;#E&gt;'/>\n";
        files_.put("pkg/Ex."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExTwo' package='pkg' template='&lt;#T&gt;' class0='pkg.Ex&lt;#T&gt;'/>\n";
        files_.put("pkg/ExTwo."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExThree' package='pkg' template='&lt;#S&gt;' class0='pkg.Ex&lt;#S&gt;'/>\n";
        files_.put("pkg/ExThree."+Classes.EXT, xml_);
        xml_ = "<interface access='"+PUBLIC_ACCESS+"' name='ExFour' package='pkg' class0='pkg.ExTwo&lt;java.lang.Number&gt;' class1='pkg.ExThree&lt;java.lang.String&gt;'/>\n";
        files_.put("pkg/ExFour."+Classes.EXT, xml_);
        ContextEl cont_ = unfullValidateOverridingMethods(files_);
        cont_.setAccessValue(new AccessValueEx());
        Classes classes_ = cont_.getClasses();
        StringList superTypes_ = classes_.getClassBody("pkg.ExFour").getAllGenericSuperTypes(cont_);
        assertEq(4, superTypes_.size());
        assertEq("pkg.ExTwo<java.lang.Number>", superTypes_.first());
        assertEq("pkg.ExThree<java.lang.String>", superTypes_.get(1));
        assertEq("pkg.Ex<java.lang.Number>", superTypes_.get(2));
        assertEq("pkg.Ex<java.lang.String>", superTypes_.last());
    }

    private static ContextEl unfullValidateOverridingMethods(StringMap<String> _files) {
        ContextEl cont_ = new ContextEl();
        Classes classes_ = new Classes(_files, cont_);
        cont_.setClasses(classes_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        classes_.validateInheritingClasses(cont_);
        assertTrue(classes_.getErrorsDet().toString(), classes_.getErrorsDet().isEmpty());
        return cont_;
    }
}
