package code.scripts.confs;

import code.scripts.pages.cards.HelpCards;
import org.junit.Test;

public final class BeanScriptsTest extends EquallableScriptsBeanUtil {
    @Test
    public void cards(){
        assertNotNullDoc(HelpCards.build());
        assertNotNull(HelpCards.en());
        assertNotNull(HelpCards.fr());
    }
    @Test
    public void help() {
        assertNotNullFullDoc(HelpScriptConfPages.info());
//        assertNotNullStrConf(HelpScriptPages.cf());
        assertNotNullStrNat(HelpScriptPagesImgs.ct());
        assertNotNull(new BeanHelpCardsSample().self());
    }
}
