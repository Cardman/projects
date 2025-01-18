package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.instances.*;
import aiki.map.characters.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class ContentComponentModelDualFight {
    private DualFight edited = Instances.newDualFight();
    private final ContentComponentModelTrainer trainer = new ContentComponentModelTrainer();
    private GeneComponentModelImgSelect miniFileName;
    private final ContentComponentModelSubscribePkTrainer ally = new ContentComponentModelSubscribePkTrainer();
    private final ContentComponentModelSubscribePkTrainer tempTrainer = new ContentComponentModelSubscribePkTrainer();
    private GeneComponentModelInt reward;
    private FormLevelGridLink secondPt;
    private NullablePoint selectSecond = new NullablePoint();
    private CrudGeneFormSimpleElementSub<String> names;

    AbsPanel effectForm(AbstractProgramInfos _core, FacadeGame _fac, SubscribedTranslationList _fact,AbsCommonFrame _fr, FormLevelGrid _grid) {
        AbsPanel selected_ = _core.getCompoFactory().newLineBox();
        names = new CrudGeneFormSimpleElementSub<String>(_core,_fac,_fact,_fr);
        names.initForm(new DisplayEntryCustSubElementString(),new GeneComponentModelSubscribeFactoryDirect<String>(new GeneComponentModelSubscribeString(_core,_fac)));
        selected_.add(names.getGroup());
        secondPt = new FormLevelGridLink(_grid.getApi(),_grid.getFacadeGame(),_grid.getFrame(),_grid.getTranslationList());
        secondPt.setupGridDims(_grid.getEdited(),_grid.getForeground());
        selected_.add(trainer.effectForm(_core, _fac, _fact));
        miniFileName = new GeneComponentModelImgSelect(_core,_fac,_fact.getImgRetrieverMiniSub());
        selected_.add(miniFileName.gene());
        FormDataMap.baseSelectImage(miniFileName);
        IdList<SubscribedTranslation> group_= new IdList<SubscribedTranslation>();
        group_.addAllElts(trainer.getMiniFileName().subs());
        group_.addAllElts(trainer.getMaxiFileName().subs());
        group_.addAllElts(miniFileName.subs());
        _grid.subs(group_);
        reward = new GeneComponentModelInt(_core);
        selected_.add(reward.geneInt());
        selected_.add(ally.form(_core,_fac,_fact,_fr));
        selected_.add(tempTrainer.form(_core,_fac,_fact,_fr));
        selected_.add(secondPt.getForm());
        secondPt.getGrid().addMouseListener(new SelectOrDeselectPtEvent(this));
        return selected_;
    }
    public void selectOrDeselect(int _x, int _y) {
        Point pt_ = secondPt.toPt(_x, _y);
        NullablePoint bk_ = selectSecond;
        selectSecond = new NullablePoint();
        if (!Point.eq(bk_, pt_)) {
            selectSecond = new NullablePoint(pt_);
        }
        if (selectSecond.isDefined() && FormLevelGridCommon.edited(selectSecond.getPoint(), secondPt.getForeground(), secondPt.getForegroundEdited())) {
            selectSecond = bk_;
            return;
        }
        if (selectSecond.isDefined()) {
            ContentComponentModelLevelWithWild.trySet(secondPt.getFacadeGame().getData().getPeople().getVal(miniFileName.getName().tryRet()), secondPt.getForegroundEdited(), selectSecond.getPoint());
        } else {
            secondPt.getForegroundEdited().removeKey(bk_.getPoint());
        }
        secondPt.refreshImg();
    }

    DualFight buildEntity() {
        TempTrainer tmp_ = new TempTrainer();
        tmp_.setImageMiniSecondTrainerFileName(miniFileName.getName().tryRet());
        tmp_.setTeam(tempTrainer.getWalk().getList());
        tmp_.setReward(reward.valueInt());
        trainer.buildEntity(tmp_);
        edited.setFoeTrainer(tmp_);
        Ally ally_ = new Ally();
        ally_.setTeam(ally.getWalk().getList());
        edited.setAlly(ally_);
        edited.setPt(selectSecond);
        edited.setNames(new StringList(names.getList()));
        return edited;
    }
    void feedForm(DualFight _edited) {
        edited = ConverterCommonMapUtil.copyDualFight(_edited);
        miniFileName.updateValue(edited.getFoeTrainer().getImageMiniSecondTrainerFileName());
        tempTrainer.getWalk().setupValues(edited.getFoeTrainer().getTeam());
        reward.valueInt(edited.getFoeTrainer().getReward());
        trainer.feedForm(edited.getFoeTrainer());
        ally.getWalk().setupValues(edited.getAlly().getTeam());
        selectSecond = edited.getPt();
        names.setupValues(edited.getNames());
    }

    public ContentComponentModelTrainer getTrainer() {
        return trainer;
    }

    public FormLevelGridLink getSecondPt() {
        return secondPt;
    }

}
