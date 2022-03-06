package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

public final class RenderBaseFailTest extends CommonRender {
    //getCommEx
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int[]\" value=\"arg={2}\"/><c:set value=\"arg[0]/=0\"/></body></html>";
        assertNotNull(getCommEx(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"Integer\" value=\"elt=$null\"/><c:set className=\"$int[]\" value=\"arg={elt}\"/></body></html>";
        assertNotNull(getCommEx(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int[]\" value=\"arg=$null\"/><c:set value=\"arg.length\"/></body></html>";
        assertNotNull(getCommEx(folder_, relative_, html_, new StringMap<String>()));
    }
}
