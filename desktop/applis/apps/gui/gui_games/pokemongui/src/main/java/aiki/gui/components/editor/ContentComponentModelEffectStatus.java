package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.util.*;
import code.util.ints.*;

public final class ContentComponentModelEffectStatus {
    private AbsCustCheckBox koUserHealSubst;
    private AbsCustCheckBox statusFromUser;
    private CrudGeneFormMonteCarloSub<String> lawStatus;
    private GeneComponentModelLsStrSub<String> deletedStatus;
    private CrudGeneFormSimpleFormSub<String,String> localFailStatus;

    private final IdList<SubscribedTranslation> subscribedTranslations = new IdList<SubscribedTranslation>();
    private AbsPanel form;

    AbsPanel effectForm(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        koUserHealSubst = _core.getCompoFactory().newCustCheckBox();
        selected_.add(koUserHealSubst);
        statusFromUser = _core.getCompoFactory().newCustCheckBox();
        selected_.add(statusFromUser);
        deletedStatus = ConverterCommonMapUtil.buildStatusList(_core,_fac,_fact);
        selected_.add(deletedStatus.geneEnum());
        localFailStatus = buildLocalFail(_f, _core, _fac, _fact);
        selected_.add(localFailStatus.getGroup());
        subscribedTranslations.clear();
        lawStatus = buildLaw(_f, _core, _fac, _fact);
        selected_.add(lawStatus.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private CrudGeneFormMonteCarloSub<String> buildLaw(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        AbsMap<String, String> messages_ = _fact.getFactorySt().buildMessages(_core,_fac,ConverterCommonMapUtil.defKeyEmpty(" "));
        Comparing<EditedCrudPair<String, LgInt>> cmp_ = new ComparatorTrWrapperPairs<String, LgInt>().wrap(messages_);

        CrudGeneFormMonteCarloSub<String> out_ = new CrudGeneFormMonteCarloSub<String>(_f, _core, cmp_, new SubscribeBuilderUtil<String>(_fact.getFactorySt()).merge(_core, _fac, new CustList<String>(), ConverterCommonMapUtil.defKeyEmpty(" "),new EmptyDefValue()), new DisplayKeyOnly<String, LgInt>(messages_));
        subscribedTranslations.add(new SubscribedTranslationMessages<String>(messages_,_fact.getFactorySt(),ConverterCommonMapUtil.defKeyEmpty(" ")));
        subscribedTranslations.add(new SubscribedTranslationPkKey<EditedCrudPair<String,LgInt>>(out_.getLaw()));
        return out_;
    }
    private CrudGeneFormSimpleFormSub<String,String> buildLocalFail(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        CrudGeneFormSimpleFormSub<String,String> out_ = new CrudGeneFormSimpleFormSub<String,String>(_core, _fac, _fact, _f);
        out_.initForm(new DisplayEntryCustSubImpl<String>(_fact.getFactorySt(), new StringMap<String>()),_fact.getFactorySt().buildMessages(_core,_fac), buildPart(_core,_fac, _fact.getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryString(_core));
        return out_;
    }
    private GeneComponentModelSubscribeFactorySelElt buildPart(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core, _fac, _facto, _abs);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }


    void buildEntity(EffectStatus _edited) {
        _edited.setDeletedStatus(new StringList(deletedStatus.tryRet()));
        _edited.setKoUserHealSubst(koUserHealSubst.isSelected());
        _edited.setStatusFromUser(statusFromUser.isSelected());
        _edited.setLocalFailStatus(new StringMap<String>());
        new MapToEntriesListUtil<String,String>().feedMap(localFailStatus.getList(), _edited.getLocalFailStatus());
        _edited.setLawStatus(new MonteCarloString());
        new MapToEntriesListUtil<String,LgInt>().feedMap(lawStatus.getList(), _edited.getLawStatus());
    }
    void feedForm(EffectStatus _edited) {
        koUserHealSubst.setSelected(_edited.getKoUserHealSubst());
        statusFromUser.setSelected(_edited.getStatusFromUser());
        lawStatus.setupValues(new MapToEntriesListUtil<String,LgInt>().build(_edited.getLawStatus()));
        localFailStatus.setupValues(new MapToEntriesListUtil<String,String>().build(_edited.getLocalFailStatus()));
        deletedStatus.setupValue(_edited.getDeletedStatus());
    }

    public AbsCustCheckBox getKoUserHealSubst() {
        return koUserHealSubst;
    }

    public AbsCustCheckBox getStatusFromUser() {
        return statusFromUser;
    }

    public GeneComponentModelLsStrSub<String> getDeletedStatus() {
        return deletedStatus;
    }

    public CrudGeneFormSimpleFormSub<String, String> getLocalFailStatus() {
        return localFailStatus;
    }

    public CrudGeneFormMonteCarloSub<String> getLawStatus() {
        return lawStatus;
    }

    public IdList<SubscribedTranslation> getSubscribedTranslations() {
        return subscribedTranslations;
    }
}
