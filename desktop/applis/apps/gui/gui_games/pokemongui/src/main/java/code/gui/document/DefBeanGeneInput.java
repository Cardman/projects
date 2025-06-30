package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.util.*;
import aiki.gui.components.editor.NbDefValue;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class DefBeanGeneInput implements IntBeanGeneInput{
    private final BeanBuilderHelper helper;
    private final AbstractProgramInfos api;

    public DefBeanGeneInput(BeanBuilderHelper _h, AbstractProgramInfos _a) {
        this.helper = _h;
        this.api = _a;
    }

    @Override
    public IntBeanChgBool newBool() {
        AbsCustCheckBox ch_ = api.getCompoFactory().newCustCheckBox();
        DefBeanChgBool chg_ = new DefBeanChgBool(ch_);
        helper.feedParent(ch_);
        return chg_;
    }

    @Override
    public IntBeanChgLong newLong() {
        GeneComponentModelLong comp_ = new GeneComponentModelLong(api);
        AbsSpinner ch_ = comp_.geneLong();
        DefBeanChgLong chg_ = new DefBeanChgLong(comp_);
        helper.feedParent(ch_);
        return chg_;
    }

    @Override
    public IntBeanChgRate newRate() {
        GeneComponentModelRate comp_ = new GeneComponentModelRate(api);
        AbsCustComponent ch_ = comp_.geneRate();
        DefBeanChgRate chg_ = new DefBeanChgRate(comp_);
        helper.feedParent(ch_);
        return chg_;
    }

    @Override
    public IntBeanChgLgInt newLgInt() {
        GeneComponentModelLgInt comp_ = new GeneComponentModelLgInt(api);
        AbsCustComponent ch_ = comp_.geneLgInt();
        DefBeanChgLgInt chg_ = new DefBeanChgLgInt(comp_);
        helper.feedParent(ch_);
        return chg_;
    }

    @Override
    public IntBeanChgString newString(AbsMap<String,String> _map) {
        GeneComponentModelElt<String> sel_ = new GeneComponentModelElt<String>(api, _map,new EmptyDefValue());
        AbsCustComponent ch_ = sel_.geneEnum();
        DefBeanChgString chg_ = new DefBeanChgString(sel_);
        helper.feedParent(ch_);
        return chg_;
    }

    @Override
    public IntBeanChgStringList newStringList(AbsMap<String, String> _map) {
        GeneComponentModelLs<String> sel_ = new GeneComponentModelLs<String>(api, _map);
        AbsCustComponent ch_ = sel_.geneCommon(new CustList<String>());
        sel_.getSelect().setVisibleRowCount(8);
        sel_.getSelect().applyRows();
        DefBeanChgStringList chg_ = new DefBeanChgStringList(sel_);
        helper.feedParent(ch_);
        return chg_;
    }

    @Override
    public IntBeanChgInt newInt(AbsMap<Integer, String> _map) {
        GeneComponentModelElt<Integer> sel_ = new GeneComponentModelElt<Integer>(api, _map,new NbDefValue<Integer>(0));
        AbsCustComponent ch_ = sel_.geneEnum();
        DefBeanChgInt chg_ = new DefBeanChgInt(sel_);
        helper.feedParent(ch_);
        return chg_;
    }

    @Override
    public IntBeanChgString newText() {
        AbsTextField ch_ = api.getCompoFactory().newTextField();
        DefBeanChgTxt chg_ = new DefBeanChgTxt(ch_);
        helper.feedParent(ch_);
        return chg_;
    }

    @Override
    public IntBeanChgSubmit newSubmit(String _text) {
        AbsButton but_ = api.getCompoFactory().newPlainButton(_text);
        DefBeanChgSubmit sub_ = new DefBeanChgSubmit(but_,helper);
        helper.feedParent(but_);
        return sub_;
    }

    @Override
    public IntBeanChgActivityOfMove newAc() {
        AbsCustCheckBox ch_ = api.getCompoFactory().newCustCheckBox();
        helper.feedParent(ch_);
        GeneComponentModelLong comp_ = new GeneComponentModelLong(api);
        AbsSpinner s_ = comp_.geneLong();
        helper.feedParent(s_);
        return new DefBeanChgActivityOfMove(ch_,comp_);
    }

    @Override
    public IntBeanChgChoiceOfEvolutionAndMoves newChoice(AbsMap<String,String> _pk,AbsMap<String,String> _mv,AbsMap<String,String> _ab) {
        GeneComponentModelElt<String> evo_ = new GeneComponentModelElt<String>(api, _pk,new EmptyDefValue());
        helper.feedParent(evo_.geneEnum());
        GeneComponentModelLs<String> kept_ = new GeneComponentModelLs<String>(api, _mv);
        helper.feedParent(kept_.geneCommon(new CustList<String>()));
        kept_.getSelect().setVisibleRowCount(8);
        kept_.getSelect().applyRows();
        GeneComponentModelElt<String> abName_ = new GeneComponentModelElt<String>(api, _ab,new EmptyDefValue());
        helper.feedParent(abName_.geneEnum());
        return new DefBeanChgChoiceOfEvolutionAndMoves(evo_, kept_, abName_);
    }

    @Override
    public IntBeanChgMoveTarget newMt(AbsMap<MoveTarget, String> _pk) {
        GeneComponentModelElt<MoveTarget> ch_ = new GeneComponentModelElt<MoveTarget>(api, _pk, new MoveTargetDefValue());
        helper.feedParent(ch_.geneEnum());
        return new DefBeanChgMoveTarget(ch_);
    }
}
