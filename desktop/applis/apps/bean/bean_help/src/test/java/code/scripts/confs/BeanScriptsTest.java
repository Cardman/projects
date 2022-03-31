package code.scripts.confs;

import code.scripts.pages.cards.HelpCards;
import org.junit.Test;

public final class BeanScriptsTest extends EquallableScriptsBeanUtil {
    @Test
    public void cards(){
        assertNotNullDoc(HelpCards.build());
        assertNotNull(HelpCards.ms());
    }
    @Test
    public void help() {
        assertNotNullFullDoc(HelpScriptConfPages.infoLg());
        assertNotNullStrConf(HelpScriptPages.cf());
        assertNotNullStrNat(HelpScriptPagesImgs.ct());
        assertNotNull(new BeanHelpCardsSample().self());
    }
}
