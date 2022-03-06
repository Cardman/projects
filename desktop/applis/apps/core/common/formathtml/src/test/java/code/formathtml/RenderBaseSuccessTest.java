package code.formathtml;

import code.util.StringMap;
import org.junit.Test;

public final class RenderBaseSuccessTest extends CommonRender {
    @Test
    public void process1Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int[]\" value=\"arg={2,4}\"/>{arg.clone()[0]},{arg.clone()[1]},{arg.clone()==arg}</body></html>";
        assertEq("<html><body>2,4,false</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process2Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"$int\" value=\"arg=2,arg2=4\"/>{arg*arg2}</body></html>";
        assertEq("<html><body>8</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
    @Test
    public void process3Test() {
        String folder_ = "messages";
        String relative_ = "sample/file";
        String html_ = "<html><body><c:set className=\"String\" value=\"f=&quot;varargs;{0} {1} {2}&quot;,v=&quot;7&quot;,d=&quot;8&quot;\"/>{(f.format($vararg(CharSequence),$firstopt(v),d,v)+'\\'').length()}</body></html>";
        assertEq("<html><body>14</body></html>", getRes2(folder_, relative_, html_, new StringMap<String>()));
    }
}
