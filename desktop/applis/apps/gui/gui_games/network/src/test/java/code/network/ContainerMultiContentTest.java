package code.network;

import cards.gui.containers.ContainerMultiContent;
import org.junit.Test;

public final class ContainerMultiContentTest extends EquallableNetworkUtil {
    @Test
    public void relative1() {
        assertEq(0,ContainerMultiContent.relative(5,5,8));
    }
    @Test
    public void relative2() {
        assertEq(3,ContainerMultiContent.relative(0,5,8));
    }
    @Test
    public void relative3() {
        assertEq(2,ContainerMultiContent.relative(7,5,8));
    }
    @Test
    public void relative4() {
        assertEq(7,ContainerMultiContent.relative(4,5,8));
    }
}
