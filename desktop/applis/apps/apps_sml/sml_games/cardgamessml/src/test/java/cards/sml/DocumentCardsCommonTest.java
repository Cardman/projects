package cards.sml;

import cards.consts.sml.DocumentReaderCardsCommonUtil;
import org.junit.Test;

public final class DocumentCardsCommonTest extends EquallableCardsSerialUtil {
    @Test
    public void t1() {
        assertEq("<_/>", DocumentReaderCardsCommonUtil.strToDocDoc("").export());
    }
    @Test
    public void t2() {
        assertEq("<0/>", DocumentReaderCardsCommonUtil.strToDocDoc("<0/>").export());
    }
}
