package code.scripts.confs;

import code.scripts.messages.gui.*;
import org.junit.Test;

public final class ScriptsTest extends EquallableScriptsUtil {
    @Test
    public void global(){
        assertNotNull(MessPkVideoGr.ms());
        assertNotNull(MessPlayerGr.ms());
        assertFalse(MessPkVideoGr.resourcesPkPokemon().isEmpty());
        assertFalse(MessPlayerGr.resourcesPlayerPlayer().isEmpty());
    }
}
