package code.formathtml;

import code.formathtml.analyze.blocks.AnaRendBlock;
import code.sml.NavigationCore;
import code.util.StringMap;
import org.junit.Test;

public final class RenderExtractFromResourcesTest extends EquallableRenderUtil {
    @Test
    public void indexCorrectMessages1Test() {
        assertEq(-1,AnaRendBlock.indexCorrectMessages("first=k\nsecond=v"));
    }
    @Test
    public void indexCorrectMessages2Test() {
        assertEq(-1, AnaRendBlock.indexCorrectMessages("first=k\n\tsecond=v"));
    }
    @Test
    public void indexCorrectMessages3Test() {
        assertEq(-1, AnaRendBlock.indexCorrectMessages("first=k\n\nsecond=v"));
    }
    @Test
    public void indexCorrectMessages4Test() {
        assertEq(1, AnaRendBlock.indexCorrectMessages("first"));
    }
    @Test
    public void getMessages1Test() {
        StringMap<String> messages_ = NavigationCore.getMessages("first=k\nsecond=v");
        assertEq(2,messages_.size());
        assertEq("first",messages_.getKey(0));
        assertEq("k",messages_.getValue(0));
        assertEq("second",messages_.getKey(1));
        assertEq("v",messages_.getValue(1));
    }
    @Test
    public void getMessages2Test() {
        StringMap<String> messages_ = NavigationCore.getMessages("first=k\n\t second");
        assertEq(1,messages_.size());
        assertEq("first",messages_.getKey(0));
        assertEq("k second",messages_.getValue(0));
    }
    @Test
    public void getMessages3Test() {
        StringMap<String> messages_ = NavigationCore.getMessages("first=k\n\nsecond=v");
        assertEq(2,messages_.size());
        assertEq("first",messages_.getKey(0));
        assertEq("k",messages_.getValue(0));
        assertEq("second",messages_.getKey(1));
        assertEq("v",messages_.getValue(1));
    }
    @Test
    public void getMessages4Test() {
        StringMap<String> messages_ = NavigationCore.getMessages("\t second\nfirst=k");
        assertEq(1,messages_.size());
        assertEq("first",messages_.getKey(0));
        assertEq("k",messages_.getValue(0));
    }
}
