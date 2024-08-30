package cards.gui.dialogs;

import cards.belote.RulesBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.facade.MessagesCardGames;
import cards.gui.WindowCards;
import code.stream.StreamTextFile;
import code.util.core.StringUtil;

public final class AfterValidateRulesBeloteSingle implements AfterValidateRulesBelote {
    private final WindowCards window;

    public AfterValidateRulesBeloteSingle(WindowCards _c) {
        this.window = _c;
    }

    @Override
    public void apply(RulesBelote _rules) {
        window.getCore().getFacadeCards().setReglesBelote(_rules);
        StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(window.getFrames()), MessagesCardGames.getAppliFilesTr(window.getFrames().getTranslations()).val().getMapping().getVal(MessagesCardGames.RULES_BELOTE)), DocumentWriterBeloteUtil.setRulesBelote(window.getCore().getFacadeCards().getReglesBelote()),window.getStreams());
        window.getCore().getContainerGame().setRulesBelote(window.getCore().getFacadeCards().getReglesBelote());
    }
}
