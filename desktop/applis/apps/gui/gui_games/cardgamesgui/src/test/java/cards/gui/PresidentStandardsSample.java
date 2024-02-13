package cards.gui;

import cards.president.beans.PresidentStandards;
import code.bean.nat.InvokedPageOutput;
import code.bean.nat.NaSt;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;
import code.sml.DocumentBuilder;

public final class PresidentStandardsSample extends PresidentStandards {
    @Override
    public void build() {
        getStds().clear();
    }

    @Override
    public void initBeans(NatConfigurationCore _conf, String _language) {
        getStds().clear();
    }

    @Override
    public void buildOther() {
        getStds().clear();
    }

    @Override
    public InvokedPageOutput processAfterInvoke(NatConfigurationCore _conf, String _dest, String _curUrl, NaSt _bean, String _language, NatRendStackCall _rendStack) {
        String empty_ = "<html/>";
        InvokedPageOutput out_ = new InvokedPageOutput(_dest, empty_);
        _rendStack.setDocument(DocumentBuilder.parseSax(empty_));
        return out_;
    }
}
