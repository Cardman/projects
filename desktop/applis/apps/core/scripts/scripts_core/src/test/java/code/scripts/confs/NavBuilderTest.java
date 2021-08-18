package code.scripts.confs;

import code.formathtml.structs.BeanInfo;
import code.util.StringMap;
import org.junit.Test;

public final class NavBuilderTest extends EquallableScriptsUtil {
    @Test
    public void test1(){
        StringMap<StringMap<String>> n_ = new StringMap<StringMap<String>>();
        NavBuilder.buildNav(n_,"",new EntryNav("",""));
        assertEq(1,n_.size());
        assertEq(1,n_.firstValue().size());
    }
    @Test
    public void test2(){
        StringMap<BeanInfo> n_ = new StringMap<BeanInfo>();
        NavBuilder.buildBeans(n_,"","");
        assertEq(1,n_.size());
    }
}
