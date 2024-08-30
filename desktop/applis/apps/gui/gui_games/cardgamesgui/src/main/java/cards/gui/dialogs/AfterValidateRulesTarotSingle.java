package cards.gui.dialogs;

import cards.facade.MessagesCardGames;
import cards.gui.WindowCards;
import cards.tarot.RulesTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.stream.StreamTextFile;
import code.util.core.StringUtil;

public final class AfterValidateRulesTarotSingle implements AfterValidateRulesTarot {
    private final WindowCards window;

    public AfterValidateRulesTarotSingle(WindowCards _c) {
        this.window = _c;
    }

    @Override
    public void apply(RulesTarot _rules) {
        window.getCore().getFacadeCards().setReglesTarot(_rules);
        StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(window.getFrames()), MessagesCardGames.getAppliFilesTr(window.getFrames().getTranslations()).val().getMapping().getVal(MessagesCardGames.RULES_TAROT)), DocumentWriterTarotUtil.setRulesTarot(window.getCore().getFacadeCards().getReglesTarot()), window.getStreams());
        window.getCore().getContainerGame().setRulesTarot(window.getCore().getFacadeCards().getReglesTarot());
    }
}
