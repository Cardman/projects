package code.adv;

import org.junit.Test;

public final class AbsBoolIntChoiceTest extends EquallableExUtil {
    @Test
    public void choice1() {
        BoolIntChoiceImpl b_ = new BoolIntChoiceImpl(true);
        assertEq(1, BoolIntChoiceUtil.choice(b_,1,2));
    }
    @Test
    public void choice2() {
        BoolIntChoiceImpl b_ = new BoolIntChoiceImpl(false);
        assertEq(2, BoolIntChoiceUtil.choice(b_,1,2));
    }
}
