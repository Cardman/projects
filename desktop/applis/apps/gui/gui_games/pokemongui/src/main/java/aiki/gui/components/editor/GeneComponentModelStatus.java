package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.enums.*;
import aiki.fight.moves.effects.*;
import aiki.fight.status.*;
import aiki.fight.status.effects.*;
import aiki.instances.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.initialize.*;
import code.maths.*;
import code.util.*;

public final class GeneComponentModelStatus extends GeneComponentModelEntity<Status> {
    private CrudGeneFormSimpleElementSub<EffectEndRoundStatus> effectEndRound;
    private CrudGeneFormSimpleElementSub<EffectPartnerStatus> effectsPartner;
    private CrudGeneFormSimpleFormSub<Statistic, Rate> multStat;
    private GeneComponentModelString fail;
    private final GeneComponentModelRate catchingRate;
    private final GeneComponentModelInt incrementEndRound;
    private AbsCustCheckBox disabledEffIfSwitch;
    private AbsCustCheckBox incrementingEndRound;
    private AbsCustCheckBox statusType;

    public GeneComponentModelStatus(AbsCommonFrame _frame, AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        super(_frame,_core, _facade, _sub);
        catchingRate = new GeneComponentModelRate(_core);
        incrementEndRound = new GeneComponentModelInt(_core);
    }
    @Override
    public AbsCustComponent gene(int _select) {
        SubscribedTranslationMessagesFactorySt factorySt_ = getSubscribedTranslationList().getFactorySt();
        buildKey(_select,factorySt_,factorySt_.all(getFacade()).getKeys());
        AbsCompoFactory compoFactory_ = getCompoFactory().getCompoFactory();
        AbsScrollPane sc_ = compoFactory_.newAbsScrollPane();
        AbsPanel page_ = compoFactory_.newPageBox();
        page_.add(geneComponentModelSelectKey());
        AbsPanel form_ = compoFactory_.newLineBox();
        effectEndRound=new CrudGeneFormSimpleElementSub<EffectEndRoundStatus>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectEndRound.initForm(new DisplayEntryCustSubElementEffect<EffectEndRoundStatus>(),new GeneComponentModelSubscribeFactoryDirect<EffectEndRoundStatus>(new GeneComponentModelSubscribeEffectEndRoundStatus(new GeneComponentModelEffectEndRoundStatus(getFrame(),getCompoFactory(),getFacade(),getSubscribedTranslationList()))));
        form_.add(effectEndRound.getGroup());
        effectsPartner=new CrudGeneFormSimpleElementSub<EffectPartnerStatus>(getCompoFactory(),getFacade(),getSubscribedTranslationList(),getFrame());
        effectsPartner.initForm(new DisplayEntryCustSubElementEffect<EffectPartnerStatus>(),new GeneComponentModelSubscribeFactoryDirect<EffectPartnerStatus>(new GeneComponentModelSubscribeEffectPartnerStatus(getCompoFactory())));
        form_.add(effectsPartner.getGroup());
        multStat=new CrudGeneFormSimpleFormSub<Statistic,Rate>(getCompoFactory(),getFacade(),getSubscribedTranslationList(), getFrame());
        multStat.initFormWithVal(new DisplayEntryCustSubElementImpl<Statistic,Rate>(getSubscribedTranslationList().getFactoryStat(),getCompoFactory(),getFacade(), new IdMap<Statistic, String>()), new GeneComponentModelSubscribeFactorySelEltEnum<Statistic>(getCompoFactory(), getSubscribedTranslationList().getFactoryStat(), getFacade()), new GeneComponentModelSubscribeFactoryDirect<Rate>(new GeneComponentModelSubscribeRate(getCompoFactory())));
        form_.add(multStat.getGroup());
        fail = new GeneComponentModelString(getCompoFactory(),new StringList(),new DefValidateText());
        form_.add(fail.geneString());
        form_.add(catchingRate.geneRate(Rate.zero()));
        form_.add(incrementEndRound.geneInt());
        disabledEffIfSwitch = compoFactory_.newCustCheckBox();
        form_.add(disabledEffIfSwitch);
        incrementingEndRound = compoFactory_.newCustCheckBox();
        form_.add(incrementingEndRound);
        statusType = compoFactory_.newCustCheckBox();
        form_.add(statusType);
        sc_.setViewportView(form_);
        page_.add(sc_);
        return page_;
    }

    @Override
    public EditedCrudPair<String,Status> value() {
        Status ent_ = Instances.newStatusSimple();
        ent_.setEffectEndRound(effectEndRound.getList());
        ent_.setEffectsPartner(effectsPartner.getList());
        ent_.setMultStat(ConverterCommonMapUtil.buildIdMapStatisticRate(multStat.getList()));
        ent_.setFail(fail.valueString());
        ent_.setCatchingRate(catchingRate.valueRate());
        ent_.setIncrementEndRound(incrementEndRound.valueInt());
        ent_.setDisabledEffIfSwitch(disabledEffIfSwitch.isSelected());
        ent_.setIncrementingEndRound(incrementingEndRound.isSelected());
        if (statusType.isSelected()) {
            ent_.setStatusType(StatusType.INDIVIDUEL);
        } else {
            ent_.setStatusType(StatusType.RELATION_UNIQUE);
        }
        return new EditedCrudPair<String, Status>(getGeneComponentModelSelectKey().tryRet(),ent_);
    }

    @Override
    public void value(EditedCrudPair<String,Status> _v) {
        getGeneComponentModelSelectKey().setupValue(_v.getKey());
        updateSelector();
        Status status_ = _v.getValue();
        effectEndRound.setupValues(status_.getEffectEndRound());
        effectsPartner.setupValues(status_.getEffectsPartner());
        multStat.setupValues(new MapToEntriesListUtil<Statistic,Rate>().build(status_.getMultStat()));
        catchingRate.valueRate(status_.getCatchingRate());
        incrementEndRound.valueInt(status_.getIncrementEndRound());
        fail.valueString(status_.getFail());
        disabledEffIfSwitch.setSelected(status_.getDisabledEffIfSwitch());
        incrementingEndRound.setSelected(status_.getIncrementingEndRound());
        statusType.setSelected(status_.getStatusType() == StatusType.INDIVIDUEL);
    }
    public IdList<SubscribedTranslation> all() {
        IdList<SubscribedTranslation> ids_ = new IdList<SubscribedTranslation>();
        ids_.addAllElts(multStat.subscribeButtons());
        ids_.addAllElts(getGeneComponentModelSelectKey().getSubs());
        return ids_;
    }

    public CrudGeneFormSimpleElementSub<EffectEndRoundStatus> getEffectEndRound() {
        return effectEndRound;
    }

    public CrudGeneFormSimpleElementSub<EffectPartnerStatus> getEffectsPartner() {
        return effectsPartner;
    }

    public AbsCustCheckBox getDisabledEffIfSwitch() {
        return disabledEffIfSwitch;
    }

    public AbsCustCheckBox getIncrementingEndRound() {
        return incrementingEndRound;
    }

    public AbsCustCheckBox getStatusType() {
        return statusType;
    }

    public GeneComponentModelRate getCatchingRate() {
        return catchingRate;
    }

}
