package code.expressionlanguage.options;

import code.util.StringList;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;

public final class ValidatorStandardTest {
    @Test
    public void tr1(){
        StringList list_ = new StringList();
        assertEq("a",ValidatorStandard.tr(list_,"a"));
    }
    @Test
    public void tr2(){
        StringList list_ = new StringList();
        list_.add("a");
        assertEq("a0",ValidatorStandard.tr(list_,"a"));
    }
    @Test
    public void tr3(){
        StringList list_ = new StringList();
        list_.add("a");
        list_.add("a1");
        assertEq("a0",ValidatorStandard.tr(list_,"a"));
    }
    @Test
    public void tr4(){
        StringList list_ = new StringList();
        list_.add("a");
        list_.add("a0");
        assertEq("a1",ValidatorStandard.tr(list_,"a"));
    }
    @Test
    public void tr5(){
        StringList list_ = new StringList();
        list_.add("a");
        list_.add("a0");
        list_.add("a1");
        assertEq("a2",ValidatorStandard.tr(list_,"a"));
    }
}
