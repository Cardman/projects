package code.sml;

import code.util.StringMap;
import org.junit.Test;

public final class CustEncodersTest extends EquallableRowColUtil {
    @Test
    public void merge1() {
        StringMap<String> enc_ = new StringMap<String>();
        DocumentBuilder.mergeInto("<lt&60;>",0, enc_);
        assertEq(0,enc_.size());
    }
    @Test
    public void merge2() {
        StringMap<String> enc_ = new StringMap<String>();
        DocumentBuilder.mergeInto("lt&60;<>",6, enc_);
        assertEq(1,enc_.size());
        assertEq("&lt;",enc_.getKey(0));
        assertEq("<",enc_.getValue(0));
    }
    @Test
    public void merge3() {
        StringMap<String> enc_ = new StringMap<String>();
        DocumentBuilder.mergeInto("lt&60;miss&<>",11, enc_);
        assertEq(1,enc_.size());
        assertEq("&lt;",enc_.getKey(0));
        assertEq("<",enc_.getValue(0));
    }
    @Test
    public void merge4() {
        StringMap<String> enc_ = new StringMap<String>();
        DocumentBuilder.mergeInto("lt&60<>",5, enc_);
        assertEq(1,enc_.size());
        assertEq("&lt;",enc_.getKey(0));
        assertEq("<",enc_.getValue(0));
    }
    @Test
    public void merge5() {
        StringMap<String> enc_ = new StringMap<String>();
        DocumentBuilder.mergeInto("lt&60;gt&62;<>",12, enc_);
        assertEq(2,enc_.size());
        assertEq("&lt;",enc_.getKey(0));
        assertEq("<",enc_.getValue(0));
        assertEq("&gt;",enc_.getKey(1));
        assertEq(">",enc_.getValue(1));
    }
    @Test
    public void merge6() {
        StringMap<String> enc_ = new StringMap<String>();
        DocumentBuilder.mergeInto("lt&62;lt&60;<>",12, enc_);
        assertEq(1,enc_.size());
        assertEq("&lt;",enc_.getKey(0));
        assertEq("<",enc_.getValue(0));
    }
}
