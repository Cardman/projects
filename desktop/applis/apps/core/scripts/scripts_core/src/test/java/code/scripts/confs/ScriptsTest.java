package code.scripts.confs;

import code.scripts.messages.gui.*;
import org.junit.Test;

public final class ScriptsTest extends EquallableScriptsUtil {
    @Test
    public void global(){
        assertNotNull(MessCdmGuiGr.ms());
        assertNotNull(MessPkVideoGr.ms());
        assertNotNull(MessGuiGr.ms());
        assertNotNull(MessPlayerGr.ms());
    }
}
