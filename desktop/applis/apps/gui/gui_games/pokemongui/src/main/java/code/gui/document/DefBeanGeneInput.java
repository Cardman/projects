package code.gui.document;

import aiki.beans.*;
import aiki.game.fight.*;
import aiki.game.fight.actions.*;
import aiki.game.fight.util.*;
import aiki.gui.components.editor.NbDefValue;
import aiki.map.pokemon.enums.*;
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
    public IntBeanChgList<String> newStringList(AbsMap<String, String> _map) {
        GeneComponentModelLs<String> sel_ = new GeneComponentModelLs<String>(api, _map);
        AbsCustComponent ch_ = sel_.geneCommon(new CustList<String>());
        sel_.getSelect().setVisibleRowCount(8);
        sel_.getSelect().applyRows();
        DefBeanChgList<String> chg_ = new DefBeanChgList<String>(sel_);
        helper.feedParent(ch_);
        return chg_;
    }

    @Override
    public IntBeanChgList<Integer> newInts(AbsMap<Integer, String> _map) {
        GeneComponentModelLs<Integer> sel_ = new GeneComponentModelLs<Integer>(api, _map);
        AbsCustComponent ch_ = sel_.geneCommon(new CustList<Integer>());
        sel_.getSelect().setVisibleRowCount(8);
        sel_.getSelect().applyRows();
        DefBeanChgList<Integer> chg_ = new DefBeanChgList<Integer>(sel_);
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
    public IntBeanChgAffectedMove newAff(AbsMap<String, String> _mv) {
        AbsCustCheckBox ch_ = api.getCompoFactory().newCustCheckBox();
        helper.feedParent(ch_);
        GeneComponentModelLong comp_ = new GeneComponentModelLong(api);
        helper.feedParent(comp_.geneLong());
        GeneComponentModelElt<String> kept_ = new GeneComponentModelElt<String>(api, _mv, new EmptyDefValue());
        helper.feedParent(kept_.geneEnum());
        return new DefBeanChgAffectedMove(kept_, ch_, comp_);
    }

    @Override
    public IntBeanChgCopiedMove newCp(AbsMap<String, String> _mv) {
        GeneComponentModelLong comp_ = new GeneComponentModelLong(api);
        helper.feedParent(comp_.geneLong());
        GeneComponentModelElt<String> kept_ = new GeneComponentModelElt<String>(api, _mv, new EmptyDefValue());
        helper.feedParent(kept_.geneEnum());
        return new DefBeanChgCopiedMove(kept_, comp_);
    }

    @Override
    public IntBeanChgStackOfUses newStack() {
        AbsCustCheckBox first_ = api.getCompoFactory().newCustCheckBox();
        helper.feedParent(first_);
        AbsCustCheckBox last_ = api.getCompoFactory().newCustCheckBox();
        helper.feedParent(last_);
        GeneComponentModelLong comp_ = new GeneComponentModelLong(api);
        AbsSpinner s_ = comp_.geneLong();
        helper.feedParent(s_);
        return new DefBeanChgStackOfUses(first_,last_,comp_);
    }

    @Override
    public IntBeanChgUsesOfMove newUse() {
        GeneComponentModelLong u_ = new GeneComponentModelLong(api);
        helper.feedParent(u_.geneLong());
        GeneComponentModelLong m_ = new GeneComponentModelLong(api);
        helper.feedParent(m_.geneLong());
        return new DefBeanChgUsesOfMove(u_,m_);
    }

    @Override
    public IntBeanChgAnticipation newAnt(AbsMap<TargetCoords, String> _pk) {
        AbsCustCheckBox inc_ = api.getCompoFactory().newCustCheckBox();
        helper.feedParent(inc_);
        GeneComponentModelRate d_ = new GeneComponentModelRate(api);
        helper.feedParent(d_.geneRate());
        GeneComponentModelLong r_ = new GeneComponentModelLong(api);
        helper.feedParent(r_.geneLong());
        GeneComponentModelElt<TargetCoords> t_ = new GeneComponentModelElt<TargetCoords>(api,_pk,new TargetCoordsDefValue());
        helper.feedParent(t_.geneEnum());
        return new DefBeanChgAnticipation(inc_,d_, r_, t_);
    }

    @Override
    public IntBeanChgTargetCoords newTc(AbsMap<TargetCoords, String> _pk) {
        GeneComponentModelElt<TargetCoords> t_ = new GeneComponentModelElt<TargetCoords>(api,_pk,new TargetCoordsDefValue());
        helper.feedParent(t_.geneEnum());
        return new DefBeanChgTargetCoords(t_);
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
    public IntBeanChgMovesAbilities newEvo(AbsMap<String, String> _mv, AbsMap<String, String> _ab) {
        GeneComponentModelLs<String> kept_ = new GeneComponentModelLs<String>(api, _mv);
        helper.feedParent(kept_.geneCommon(new CustList<String>()));
        kept_.getSelect().setVisibleRowCount(8);
        kept_.getSelect().applyRows();
        GeneComponentModelLs<String> abName_ = new GeneComponentModelLs<String>(api, _ab);
        helper.feedParent(abName_.geneCommon(new CustList<String>()));
        abName_.getSelect().setVisibleRowCount(8);
        abName_.getSelect().applyRows();
        return new DefBeanChgMovesAbilities(kept_, abName_);
    }

    @Override
    public IntBeanChgMoveTarget newMt(AbsMap<MoveTarget, String> _pk) {
        GeneComponentModelElt<MoveTarget> ch_ = new GeneComponentModelElt<MoveTarget>(api, _pk, new MoveTargetDefValue());
        helper.feedParent(ch_.geneEnum());
        return new DefBeanChgMoveTarget(ch_);
    }

    @Override
    public IntBeanChgKindAction newKa(AbsMap<KindAction, String> _map) {
        GeneComponentModelElt<KindAction> ch_ = new GeneComponentModelElt<KindAction>(api, _map, new KindActionDefValue());
        helper.feedParent(ch_.geneEnum());
        return new DefBeanChgKindAction(ch_);
    }

    @Override
    public IntBeanChgGender newGender(AbsMap<Gender, String> _map) {
        GeneComponentModelElt<Gender> ch_ = new GeneComponentModelElt<Gender>(api, _map, new GenderDefValue());
        helper.feedParent(ch_.geneEnum());
        return new DefBeanChgGender(ch_);
    }
}
