package code.scripts.confs;

import org.junit.Test;

public final class ScriptsTest extends EquallableExUtil {
    @Test
    public void belote(){
        assertNotNull(BeloteScriptPages.infos());
    }

    @Test
    public void president(){
        assertNotNull(PresidentScriptPages.infos());
    }
    @Test
    public void tarot(){
        assertNotNull(TarotScriptPages.infos());
    }
}
