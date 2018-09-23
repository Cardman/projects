package code.expressionlanguage.methods;
import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.InitializationLgNames;
import code.util.StringList;
import code.util.StringMap;

public class ClassesTest {
	@Test
    public void getSortedSuperInterfaces1Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex {}\n");
        files_.put("pkg/Ex", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"),cont_);
        assertEq(1, s_.size());
        assertEq("pkg.Ex", s_.first());
    }

    @Test
    public void getSortedSuperInterfaces2Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo{}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"),cont_);
        assertEq(2, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces3Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"),cont_);
        assertEq(3, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.ExThree", s_.get(1));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces4Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree:pkg.ExFour{}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo {}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree {}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"),cont_);
        assertEq(4, s_.size());
        assertEq("pkg.ExTwo", s_.first());
        assertEq("pkg.ExThree", s_.get(1));
        assertEq("pkg.ExFour", s_.get(2));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces5Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {}\n");
        files_.put("pkg/ExFour", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex"),cont_);
        assertEq(4, s_.size());
        assertEq("pkg.ExFour", s_.first());
        assertEq("pkg.ExTwo", s_.get(1));
        assertEq("pkg.ExThree", s_.get(2));
        assertEq("pkg.Ex", s_.last());
    }

    @Test
    public void getSortedSuperInterfaces6Test() {
        StringMap<String> files_ = new StringMap<String>();
        StringBuilder xml_;
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.Ex :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/Ex", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExTwo :pkg.ExFour{}\n");
        files_.put("pkg/ExTwo", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExThree :pkg.ExFour{}\n");
        files_.put("pkg/ExThree", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFour {}\n");
        files_.put("pkg/ExFour", xml_.toString());
        xml_ = new StringBuilder();
        xml_.append("$public $interface pkg.ExFive :pkg.ExTwo:pkg.ExThree{}\n");
        files_.put("pkg/ExFive", xml_.toString());
        ContextEl cont_ = new ContextEl();
        Classes classes_ = cont_.getClasses();
        InitializationLgNames.initAdvStandards(cont_);
        Classes.buildPredefinedBracesBodies(cont_);
        Classes.tryBuildBracedClassesBodies(files_, cont_);
        assertTrue(classes_.displayErrors(), classes_.isEmptyErrors());
        StringList s_ = classes_.getSortedSuperInterfaces(new StringList("pkg.Ex","pkg.ExFive"),cont_);
        assertEq(5, s_.size());
        assertEq("pkg.ExFour", s_.first());
        assertEq("pkg.ExTwo", s_.get(1));
        assertEq("pkg.ExThree", s_.get(2));
        assertEq("pkg.Ex", s_.get(3));
        assertEq("pkg.ExFive", s_.last());
    }
}
