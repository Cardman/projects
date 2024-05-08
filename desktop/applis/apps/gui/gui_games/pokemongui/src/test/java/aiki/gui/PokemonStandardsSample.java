package aiki.gui;

import aiki.beans.PokemonStandards;
import code.bean.nat.InvokedPageOutput;
import code.bean.nat.NaSt;
import code.bean.nat.NatNavigation;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;
import code.sml.DocumentBuilder;

public final class PokemonStandardsSample extends PokemonStandards {
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
    public void execute(boolean _form, NatNavigation _navigation) {
        _navigation.setSession(new NatConfigurationCore());
    }

    @Override
    public InvokedPageOutput processAfterInvoke(NatConfigurationCore _conf, String _dest, String _curUrl, NaSt _bean, String _language, NatRendStackCall _rendStack) {
        String empty_ = "<html/>";
        InvokedPageOutput out_ = new InvokedPageOutput(_dest, empty_);
        _rendStack.setDocument(DocumentBuilder.parseSax(empty_));
        return out_;
    }

}
