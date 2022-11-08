package aiki.beans.items;

import org.junit.Test;

public final class OhterItemsTest extends InitDbItemOther {
    @Test
    public void getSteps() {
        assertEq(1,callRepelBeanStepsGet());
    }
    @Test
    public void sell() {
        assertEq(1,sellBean());
    }
}
