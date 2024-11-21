package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.moves.effects.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class ContentComponentModelEffectStatus {
    private AbsCustCheckBox koUserHealSubst;
    private AbsCustCheckBox statusFromUser;
    private CrudGeneFormMonteCarloSub<String> lawStatus;
    private GeneComponentModelLsStrSub<String> deletedStatus;
    private CrudGeneFormSimpleFormSub<String,String> localFailStatus;

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
        lawStatus = buildLaw(_f, _core, _fac, _fact);
        selected_.add(lawStatus.getGroup());
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }
    private CrudGeneFormMonteCarloSub<String> buildLaw(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        CrudGeneFormMonteCarloSub<String> law_ = new CrudGeneFormMonteCarloSub<String>(_f, _core);
        law_.initFormKeys(ConverterCommonMapUtil.buildStatus(_core,_fac,_fact,ConverterCommonMapUtil.defKeyEmpty(" ")),new DisplayEntryCustSubElementLgIntImpl<String>(_fact.getFactorySt(), _core, _fac, ConverterCommonMapUtil.defKeyEmpty(" ")));
        return law_;
    }
    private CrudGeneFormSimpleFormSub<String,String> buildLocalFail(AbsCommonFrame _f, AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact) {
        CrudGeneFormSimpleFormSub<String,String> out_ = new CrudGeneFormSimpleFormSub<String,String>(_core, _fac, _fact, _f);
        out_.initFormNoVal(new DisplayEntryCustSubElementImpl<String,String>(_fact.getFactorySt(),_core,_fac, new StringMap<String>()), buildPart(_core,_fac, _fact.getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryString(_core));
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
        _edited.setLocalFailStatus(ConverterCommonMapUtil.buildStringMapString(localFailStatus.getList()));
        _edited.setLawStatus(ConverterCommonMapUtil.buildMonteCarloString(lawStatus.getList()));
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

}
