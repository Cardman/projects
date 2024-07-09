package cards.gui;

import cards.president.beans.*;
import code.bean.nat.*;
import code.bean.nat.analyze.*;
import code.bean.nat.exec.*;
import code.sml.*;
import code.util.*;

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

    @Override
    public NatNavigation nav(StringList _languages, String _lg, AbstractNativeInit _init, StringMap<Document> _built, StringMap<String> _other, StringMap<String> _otherMessage, String _rel) {
        return EquallableCardsGuiUtil.nav();
    }
}
