package code.netw;

import code.sml.util.TranslationsLg;
import org.junit.Test;

public final class MessagesNetWorkTest extends EquallableNetWorkMesUtil {
    @Test
    public void test() {
        TranslationsLg en_ = new TranslationsLg();
        TranslationsLg fr_ = new TranslationsLg();
        NetWork.enTr(NetWork.initAppliTr(en_));
        NetWork.frTr(NetWork.initAppliTr(fr_));
        assertFalse(NetWork.getMessages(NetWork.getAppliTr(en_)).getMapping().isEmpty());
        assertFalse(NetWork.getMessages(NetWork.getAppliTr(fr_)).getMapping().isEmpty());
    }
}
