package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class ContentComponentModelEffectStatus {
    private AbsCustCheckBox koUserHealSubst;
    private AbsCustCheckBox statusFromUser;
    private CrudGeneFormMonteCarloSub<String> lawStatus;
    private GeneComponentModelLsStrSub<String,StringList> deletedStatus;
    private CrudGeneFormSimpleFormSub<String,String> localFailStatus;

    private AbsPanel form;

    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        koUserHealSubst = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffstatus.M_P_59_KO_USER,koUserHealSubst));
        statusFromUser = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffstatus.M_P_59_FORWARD,statusFromUser));
        deletedStatus = ConverterCommonMapUtil.buildStatusList(_core.getProgramInfos(),_core.getFacadeGame(),_core.getFactory());
        selected_.add(line(_core,MessagesDataEffstatus.M_P_59_DELETED_STATUS,deletedStatus.geneEnum()));
        localFailStatus = buildLocalFail(_core);
        selected_.add(line(_core,MessagesDataEffstatus.M_P_58_FAIL_VAR,localFailStatus.getGroup()));
        lawStatus = ConverterCommonMapUtil.buildStatusLaw(_core.getFrame(), _core.getProgramInfos(), _core.getFacadeGame(), _core.getFactory(),MessagesPkBean.EFF_STATUS,MessagesDataEffstatus.M_P_59_STATUS,MessagesDataEffstatus.M_P_59_RATE_EVENT);
        selected_.add(line(_core,MessagesDataEffstatus.M_P_59_LAW_STATUS,lawStatus.getGroup()));
        selected_.setVisible(false);
        form =selected_;
        return selected_;
    }

    private CrudGeneFormSimpleFormSub<String,String> buildLocalFail(AbsGeneComponentModelEffect _parent) {
        CrudGeneFormSimpleFormSub<String,String> out_ = new CrudGeneFormSimpleFormSub<String,String>(_parent.getProgramInfos(),_parent.getFacadeGame(),_parent.getFactory(),_parent.getFrame());
        out_.initFormWithVal(new DisplayEntryCustSubElementImpl<String,String>(_parent.getFactory().getFactorySt(),_parent.getProgramInfos(),_parent.getFacadeGame(), new StringMap<String>()), buildPart(_parent, _parent.getFactory().getFactorySt(), new StringMap<String>()), new GeneComponentModelSubscribeFactoryString(_parent.getProgramInfos(),_parent.getFacadeGame()),MessagesPkBean.EFF_STATUS,MessagesDataEffstatus.M_P_59_STATUS,MessagesDataEffstatus.M_P_59_FAIL);
        return out_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_STATUS, _key,_input);
    }

    private GeneComponentModelSubscribeFactorySelElt buildPart(AbsGeneComponentModelEffect _core, SubscribedTranslationMessagesFactory _facto, StringMap<String> _abs) {
        return new GeneComponentModelSubscribeFactorySelElt(_core.getProgramInfos(), _core.getFacadeGame(), _facto, _abs);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }


    void buildEntity(EffectStatus _edited) {
        _edited.setDeletedStatus(deletedStatus.tryRet());
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

    public GeneComponentModelLsStrSub<String,StringList> getDeletedStatus() {
        return deletedStatus;
    }

    public CrudGeneFormSimpleFormSub<String, String> getLocalFailStatus() {
        return localFailStatus;
    }

    public CrudGeneFormMonteCarloSub<String> getLawStatus() {
        return lawStatus;
    }

}
