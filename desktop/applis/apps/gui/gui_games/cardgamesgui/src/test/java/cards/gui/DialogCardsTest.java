package cards.gui;

import cards.facade.Nicknames;
import code.gui.AbsCustComponent;
import code.mock.MockCustComponent;
import code.util.IdList;
import org.junit.Test;

public final class DialogCardsTest extends EquallableCardsGuiUtil {
    @Test
    public void nickname() {
        WindowCards fr_ = frameDialogNicknames("/__/","/_/");
        tryClick(fr_.getPlayers());
        assertTrue(fr_.getDialogNicknames().getCardDialog().isVisible());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) fr_.getDialogNicknames().getCardDialog().getPane()).getTreeAccessible();
        assertEq(41, tr_.size());
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNickname()));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getValidate()));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesBelote().get(0)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesBelote().get(1)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesBelote().get(2)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(0)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(1)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(2)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(3)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(4)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(5)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(6)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(7)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(8)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(9)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(10)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(11)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(12)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(13)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(14)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(15)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(16)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(17)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(18)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(19)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(20)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(21)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(22)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(23)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(24)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(25)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(26)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(27)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(28)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(29)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesPresident().get(30)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesTarot().get(0)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesTarot().get(1)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesTarot().get(2)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesTarot().get(3)));
        assertTrue(tr_.containsObj(fr_.getDialogNicknames().getNicknamesTarot().get(4)));
        fr_.getDialogNicknames().getNickname().setText("__");
        fr_.getDialogNicknames().getNicknamesBelote().get(1).setText("B1");
        fr_.getDialogNicknames().getNicknamesPresident().get(10).setText("P10");
        fr_.getDialogNicknames().getNicknamesTarot().get(3).setText("T3");
        tryClick(fr_.getDialogNicknames().getValidate());
        Nicknames v_ = fr_.getCore().getFacadeCards().getNicknamesCrud().value();
        assertEq("__",v_.getPseudo());
        assertEq("B1",v_.getPseudosBelote().get(1));
        assertEq("P10",v_.getPseudosPresident().get(10));
        assertEq("T3",v_.getPseudosTarot().get(3));
    }
}
