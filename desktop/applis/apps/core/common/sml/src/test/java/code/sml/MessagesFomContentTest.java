package code.sml;

import code.util.StringMap;
import org.junit.Test;

public final class MessagesFomContentTest extends EquallableRowColUtil {
    @Test
    public void getMessagesFromContent1Test() {
        StringMap<String> map_ = DocumentBuilder.getMessagesFromContent("");
        assertTrue(map_.isEmpty());
    }
    @Test
    public void getMessagesFromContent2Test() {
        StringMap<String> map_ = DocumentBuilder.getMessagesFromContent("k1=v1");
        assertEq(1, map_.size());
        assertEq("v1", map_.getVal("k1"));
    }
    @Test
    public void getMessagesFromContent3Test() {
        StringMap<String> map_ = DocumentBuilder.getMessagesFromContent("k1=v1\nk2=v2");
        assertEq(2, map_.size());
        assertEq("v1", map_.getVal("k1"));
        assertEq("v2", map_.getVal("k2"));
    }
}
