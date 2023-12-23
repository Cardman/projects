package cards.facade;

import code.maths.montecarlo.CustomSeedGene;
import code.maths.montecarlo.DefaultGenerator;
import code.mock.*;
import code.stream.core.TechStreams;
import org.junit.Test;

public final class NicknamesTest extends EquallableCardsFileUtil {
    @Test
    public void isValidNicknames1() {
        Nicknames n_ = new Nicknames(new Nicknames(""));
        assertTrue(n_.isValidNicknames());
    }
    @Test
    public void isValidNicknames2() {
        Nicknames n_ = new Nicknames(new Nicknames(""));
        n_.getPseudosBelote().clear();
        assertFalse(n_.isValidNicknames());
    }
    @Test
    public void isValidNicknames3() {
        Nicknames n_ = new Nicknames(new Nicknames(""));
        n_.getPseudosPresident().clear();
        assertFalse(n_.isValidNicknames());
    }
    @Test
    public void isValidNicknames4() {
        Nicknames n_ = new Nicknames(new Nicknames(""));
        n_.getPseudosTarot().clear();
        assertFalse(n_.isValidNicknames());
    }
    @Test
    public void save() {
        MockFileSet set_ = new MockFileSet(0, new long[1], new String[]{"/"});
        MockBinFact binFact_ = new MockBinFact(new DefaultGenerator(new CustomSeedGene(dbs(0.75))), set_);
        TechStreams tech_ = new TechStreams(binFact_, new MockTextFact(binFact_), new MockZipFact());
        new Nicknames().sauvegarder("_", tech_);
        assertTrue(new MockFileCoreStream(set_).newFile("_").exists());
    }
}
