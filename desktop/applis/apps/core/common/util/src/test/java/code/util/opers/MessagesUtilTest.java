package code.util.opers;

import code.util.EquallableExUtil;
import code.util.StringMap;
import org.junit.Test;

public final class MessagesUtilTest extends EquallableExUtil {
    @Test
    public void getMessagesTest() {
        StringMap<String> messages_ = MessagesUtil.getMessages("\ttab\nzero\none=hello\n\t world\ntwo=every body\n");
        assertEq(2,messages_.size());
        assertEq("hello world",messages_.getVal("one"));
        assertEq("every body",messages_.getVal("two"));
    }
}
