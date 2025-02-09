package aiki.main;

import aiki.beans.*;
import aiki.facade.*;
import code.bean.nat.*;
import code.bean.nat.analyze.*;
import code.scripts.confs.*;
import code.threads.*;
import code.util.*;

public final class DataWebInitSimu implements IntCallable<AikiNatLgNamesNavigation> {
    private final FacadeGame facade;
    private final PokemonStandards next;
    private final AbstractFutureParam<AikiNatLgNamesNavigation> previous;

    public DataWebInitSimu(FacadeGame _f, AbstractFutureParam<AikiNatLgNamesNavigation> _p, PokemonStandards _n) {
        this.facade = _f;
        this.next = _n;
        this.previous = _p;
    }

    @Override
    public AikiNatLgNamesNavigation call() {
        next.setDataBase(facade);
        AikiNatLgNamesNavigation res_ = previous.attendreResultat();
        next.getRenders().addAllEntries(res_.getBeanNatLgNames().getRenders());
        next.getBeansStruct().addAllEntries(res_.getBeanNatLgNames().getBeansStruct());
        next.getIterables().addAllEntries(res_.getBeanNatLgNames().getIterables());
        next.getStds().addAllEntries(res_.getBeanNatLgNames().getStds());
        NatNavigation cp_ = copy(res_.getNavigation());
        cp_.getSession().setFirstUrl(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML);
        return new AikiNatLgNamesNavigation(next,cp_);
    }
    private static NatNavigation copy(NatNavigation _from) {
        NatNavigation cp_ = new NatNavigation();
        cp_.setSession(copy(_from.getSession()));
        cp_.getApplis().addAllEntries(_from.getApplis());
        cp_.setLanguage(_from.getLanguage());
        cp_.setLanguages(new StringList(_from.getLanguages()));
        cp_.setFiles(new StringMap<String>(_from.getFiles()));
        cp_.setCurrentBeanName(_from.getCurrentBeanName());
        cp_.setCurrentUrl(_from.getCurrentUrl());
        return cp_;
    }
    private static NatConfigurationCore copy(NatConfigurationCore _from) {
        NatConfigurationCore cp_ = new NatConfigurationCore();
        cp_.setPrefix(_from.getPrefix());
        cp_.setBeansInfos(_from.getBeansInfos());
        cp_.setFirstUrl(_from.getFirstUrl());
        cp_.setCurrentLanguage(_from.getCurrentLanguage());
        cp_.setFiles(_from.getFiles());
        return cp_;
    }

}
