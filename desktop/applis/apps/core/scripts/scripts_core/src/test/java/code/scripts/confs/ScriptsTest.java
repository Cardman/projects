package code.scripts.confs;

import org.junit.Assert;
import org.junit.Test;

public final class ScriptsTest {
    @Test
    public void belote(){
        Assert.assertNotNull(BeloteScriptPages.infos());
    }

    @Test
    public void president(){
        Assert.assertNotNull(PresidentScriptPages.infos());
    }
    @Test
    public void tarot(){
        Assert.assertNotNull(TarotScriptPages.infos());
    }
}
