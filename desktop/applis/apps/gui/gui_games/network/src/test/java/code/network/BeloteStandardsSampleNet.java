package code.network;

import cards.belote.beans.BeloteStandards;
import code.bean.nat.AbstractNativeInit;
import code.bean.nat.InvokedPageOutput;
import code.bean.nat.NaSt;
import code.bean.nat.NatNavigation;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.util.StringList;
import code.util.StringMap;

public final class BeloteStandardsSampleNet extends BeloteStandards {
    @Override
    public void build() {
        getStds().clear();
    }

    @Override
    public void initBeans(NatConfigurationCore _conf, String _language) {
        getStds().clear();
    }

    @Override
    protected void buildAddon() {
        getStds().clear();
    }

    @Override
    public InvokedPageOutput processAfterInvoke(NatConfigurationCore _conf, String _dest, String _curUrl, NaSt _bean, String _language, NatRendStackCall _rendStack) {
        String empty_ = "<html/>";
        InvokedPageOutput out_ = new InvokedPageOutput(_dest, empty_);
        _rendStack.setDocument(DocumentBuilder.parseSax(empty_));
        return out_;
    }

    @Override
    public NatNavigation nav(StringList _languages, String _lg, AbstractNativeInit _init, StringMap<Document> _built, StringMap<String> _other, StringMap<String> _otherMessage, String _rel) {
        return EquallableNetworkUtil.nav();
    }
}
