package code.gui.document;

import aiki.beans.*;
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
    public IntBeanChgString newString(AbsMap<String,String> _map) {
        GeneComponentModelElt<String> sel_ = new GeneComponentModelElt<String>(api, _map,new EmptyDefValue());
        AbsCustComponent ch_ = sel_.geneEnum();
        DefBeanChgString chg_ = new DefBeanChgString(sel_);
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
}
