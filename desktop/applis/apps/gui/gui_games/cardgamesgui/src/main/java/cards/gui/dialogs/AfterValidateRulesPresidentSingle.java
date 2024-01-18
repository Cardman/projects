package cards.gui.dialogs;

import cards.facade.FacadeCards;
import cards.gui.WindowCards;
import cards.president.RulesPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import code.stream.StreamTextFile;
import code.util.core.StringUtil;

public final class AfterValidateRulesPresidentSingle implements AfterValidateRulesPresident {
    private final WindowCards window;

    public AfterValidateRulesPresidentSingle(WindowCards _c) {
        this.window = _c;
    }

    @Override
    public void apply(RulesPresident _rules) {
        window.getCore().getFacadeCards().setReglesPresident(_rules);
        StreamTextFile.saveTextFile(StringUtil.concat(WindowCards.getTempFolderSl(window.getFrames()),FacadeCards.RULES_PRESIDENT), DocumentWriterPresidentUtil.setRulesPresident(window.getCore().getFacadeCards().getReglesPresident()),window.getStreams());
        window.getCore().getContainerGame().setRulesPresident(window.getCore().getFacadeCards().getReglesPresident());
    }
}
