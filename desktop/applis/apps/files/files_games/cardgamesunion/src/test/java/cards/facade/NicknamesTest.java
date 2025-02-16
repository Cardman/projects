package cards.facade;

import code.mock.*;
import code.stream.core.TechStreams;
import org.junit.Test;

public final class NicknamesTest extends EquallableCardsFileUtil {
    @Test
    public void isValidNicknames1() {
        Nicknames n_ = new Nicknames(new Nicknames(nicknames()));
        assertTrue(n_.isValidNicknames());
    }
    @Test
    public void isValidNicknames2() {
        Nicknames n_ = new Nicknames(new Nicknames(nicknames()));
        n_.getPseudosBelote().clear();
        assertFalse(n_.isValidNicknames());
    }
    @Test
    public void isValidNicknames3() {
        Nicknames n_ = new Nicknames(new Nicknames(nicknames()));
        n_.getPseudosPresident().clear();
        assertFalse(n_.isValidNicknames());
    }
    @Test
    public void isValidNicknames4() {
        Nicknames n_ = new Nicknames(new Nicknames(nicknames()));
        n_.getPseudosTarot().clear();
        assertFalse(n_.isValidNicknames());
    }
    @Test
    public void save() {
        MockFileSet set_ = new MockFileSet(0, new long[1], new String[]{"/"});
        MockBinFact binFact_ = new MockBinFact(new MockTrueRand(), set_);
        TechStreams tech_ = new TechStreams(binFact_, new MockZipFact());
        new Nicknames().sauvegarder("_", tech_);
        assertTrue(new MockFileCoreStream(set_).newFile("_").exists());
    }
}
