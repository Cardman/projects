package code.scripts.confs;

import code.scripts.messages.gui.*;
import org.junit.Test;

public final class ScriptsTest extends EquallableScriptsUtil {
    @Test
    public void global(){
        assertNotNull(MessCdmGuiGr.ms());
        assertNotNull(MessGuiCardsGr.ms());
        assertNotNull(MessGuiPkGr.ms());
        assertNotNull(MessCdmRenderGr.ms());
        assertNotNull(MessPkVideoGr.ms());
        assertNotNull(MessGuiGr.ms());
        assertNotNull(MessCdmUnitGr.ms());
        assertNotNull(MessPlayerGr.ms());
        assertNotNull(MessGuiNetGr.ms());
    }
}
