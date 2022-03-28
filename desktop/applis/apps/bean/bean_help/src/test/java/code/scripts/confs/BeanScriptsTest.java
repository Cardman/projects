package code.scripts.confs;

import code.scripts.pages.cards.HelpCards;
import org.junit.Test;

public final class BeanScriptsTest extends EquallableScriptsBeanUtil {
    @Test
    public void cards(){
        assertNotNull(HelpCards.build());
        assertNotNull(HelpCards.ms());
    }
    @Test
    public void help() {
        assertNotNull(HelpScriptConfPages.infoLg());
        assertNotNull(HelpScriptPages.cf());
        assertNotNull(HelpScriptPagesImgs.ct());
        assertNotNull(new BeanHelpCardsSample().self());
    }
}
