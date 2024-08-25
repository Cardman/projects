package code.netw;

import code.sml.util.TranslationsLg;
import org.junit.Test;

public final class MessagesNetWorkTest extends EquallableNetWorkMesUtil {
    @Test
    public void test() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        MessagesNetWork.enTr(MessagesNetWork.initAppliTr(en_));
        MessagesNetWork.frTr(MessagesNetWork.initAppliTr(fr_));
        assertFalse(MessagesNetWork.getMessages(MessagesNetWork.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(MessagesNetWork.getMessages(MessagesNetWork.getAppliTr(fr_)).getMapping().isEmpty());
    }
}
