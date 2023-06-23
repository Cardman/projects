package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecClassBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.ClassMetaInfo;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.utilcompo.LightLgNamesWithNewAliases;
import code.gui.AbsTextField;
import code.gui.AutoCompleteDocument;
import code.gui.initialize.AbstractProgramInfos;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class FormFindReplaceExpression {
    private final AutoCompleteDocument completeClasses;
    private final AbsTextField finderExpClasses;
    private ExecConstructorOverrideInfo targetMethodView;
    private ExecConstructorOverrideInfo targetMethodReplace;
    private final StringMap<ExecConstructorOverrideInfo> dico = new StringMap<ExecConstructorOverrideInfo>();
    private final StringMap<ExecConstructorOverrideInfo> dicoRepl = new StringMap<ExecConstructorOverrideInfo>();
    private final ResultContextViewReplacer resultContext = new ResultContextViewReplacer();
    public FormFindReplaceExpression(AbstractProgramInfos _frames) {
        finderExpClasses = _frames.getCompoFactory().newTextField();
        completeClasses = new AutoCompleteDocument(finderExpClasses, new StringList(), _frames,new FeedExpressionClassValue());
    }

    public void refresh(ResultContext _base, ContextEl _ctx) {
        LightLgNamesWithNewAliases lg_ = (LightLgNamesWithNewAliases) _base.getForwards().getGenerator();
        ResultContextViewReplacer vr_ = getResultContext();
        vr_.update(lg_.getStrAlias(), lg_.getContent(),_ctx);
        ExecRootBlock typeView_ = vr_.getViewType();
        ExecNamedFunctionBlock methodView_ = vr_.getViewMethod();
        ExecRootBlock typeRepl_ = vr_.getReplaceType();
        ExecNamedFunctionBlock methodRepl_ = vr_.getReplaceMethod();
        dico.clear();
        dicoRepl.clear();
        StringList dict_ = new StringList();
        for (EntryCust<String, ExecRootBlock> e: _ctx.getClasses().getClassesBodies().entryList()) {
            ExecRootBlock type_ = e.getValue();
            String name_ = e.getKey();
            ExecConstructorOverrideInfo ov_ = isValid(name_, type_, _ctx, typeView_, methodView_);
            if (ov_ != null) {
                dict_.add(name_);
                dico.addEntry(name_,ov_);
            }
            ExecConstructorOverrideInfo ovRep_ = isValid(name_, type_, _ctx, typeRepl_, methodRepl_);
            if (ovRep_ != null) {
                dicoRepl.addEntry(name_,ovRep_);
            }
        }
        completeClasses.setDictionary(dict_);
        finderExpClasses.setEnabled(true);
    }

    private static ExecConstructorOverrideInfo isValid(String _k, ExecRootBlock _type, ContextEl _ctx, ExecRootBlock _look, ExecNamedFunctionBlock _method) {
        if (!(_type instanceof ExecClassBlock) || ((ExecClassBlock)_type).isAbstractType() || !_type.withoutInstance()) {
            return null;
        }
        ExecOverrideInfo o_ = _ctx.getClasses().getRedirections().get(_look.getNumberType()).getVal(_method, _k);
        if (o_ == null) {
            return null;
        }
        for (ConstructorMetaInfo c: new ClassMetaInfo(new ExecFormattedRootBlock(_type), _ctx).getConstructorsInfos()) {
            if (c.getFid().getParametersTypesLength() == 0) {
                return new ExecConstructorOverrideInfo(c,o_);
            }
        }
        return null;
    }

    public AbsTextField getFinderExpClasses() {
        return finderExpClasses;
    }

    public AutoCompleteDocument getCompleteClasses() {
        return completeClasses;
    }

    public void usedType(String _u) {
        usedTypeReplace(_u);
        targetMethodView = dico.getVal(_u);
    }

    public StringMap<ExecConstructorOverrideInfo> getDico() {
        return dico;
    }

    public StringMap<ExecConstructorOverrideInfo> getDicoRepl() {
        return dicoRepl;
    }

    public void usedTypeReplace(String _u) {
        targetMethodReplace = dicoRepl.getVal(_u);
    }


    public ExecConstructorOverrideInfo getTargetMethodView() {
        return targetMethodView;
    }

    public ExecConstructorOverrideInfo getTargetMethodReplace() {
        return targetMethodReplace;
    }

    public ResultContextViewReplacer getResultContext() {
        return resultContext;
    }
}
