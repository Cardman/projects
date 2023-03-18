package code.expressionlanguage.adv;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundConstructor;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.guicompos.GuiContextEl;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class FindExpressionTask implements Runnable {
    private final TabEditor editor;

    public FindExpressionTask(TabEditor _e) {
        this.editor = _e;
    }
    @Override
    public void run() {
        ResultContextViewReplacer vr_ = editor.getResultContext();
        ResultContext res_ = vr_.getResultContext();
        RunnableContextEl rCont_ = (RunnableContextEl) res_.getContext();
        RunnableContextEl rInit_ = new GuiContextEl(NullStruct.NULL_VALUE, rCont_.getExecutionInfos(), rCont_.getArgs());
        RunnableStruct.setupThread(rInit_);
        editor.setAction(rInit_);
        editor.getFindingExpressionCancel().setEnabled(true);
        String text_ = editor.getPreview().getText();
        String usedType_ = editor.getUsedType();
        ExecFormattedRootBlock className_ = ExecFormattedRootBlock.build(usedType_, rInit_.getClasses());
        Struct infoStruct_ = ArgumentListCall.toStr(ProcessMethod.calculate(new CustomFoundConstructor(className_, className_.getRootBlock().getEmptyCtorPair(), new Argument(), new Parameters(), InstancingStep.NEWING),rInit_, StackCall.newInstance(InitPhase.NOTHING,rInit_)).getValue());
        CustList<SegmentFindPart> found_ = new CustList<SegmentFindPart>();
        ExecOverrideInfo targetMethod_ = editor.getTargetMethodView();
        int currentIndex_ = 0;
        while (currentIndex_ >= 0) {
            CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
            ls_.add(new ArgumentWrapper(new StringStruct(text_)));
            ls_.add(new ArgumentWrapper(new IntStruct(currentIndex_)));
            StackCall stCall_ = StackCall.newInstance(InitPhase.NOTHING, rInit_);
            Parameters parameters_ = ExecTemplates.wrapAndCall(targetMethod_.getPair(), targetMethod_.getClassName(), new Argument(infoStruct_), rInit_, stCall_, new ArgumentListCall(ls_));
            Struct re_ = ArgumentListCall.toStr(ProcessMethod.calculate(new CustomFoundMethod(new Argument(infoStruct_), targetMethod_.getClassName(), targetMethod_.getPair(), parameters_),rInit_,stCall_).getValue());
            if (rInit_.getExecutingOptions().getInterrupt().get()) {
                return;
            }
            if (!(re_ instanceof FieldableStruct)) {
                currentIndex_ = -1;
                continue;
            }
            int begin_ = NumParsers.convertToNumber(((FieldableStruct) re_).getEntryStruct(new ClassField(vr_.getAliasStringSegment(), vr_.getAliasStringSegmentBegin())).getStruct()).intStruct();
            int end_ = NumParsers.convertToNumber(((FieldableStruct) re_).getEntryStruct(new ClassField(vr_.getAliasStringSegment(), vr_.getAliasStringSegmentEnd())).getStruct()).intStruct();
            if (begin_ < currentIndex_ || begin_ >= end_) {
                currentIndex_ = -1;
            } else {
                int next_ = NumberUtil.min(end_, text_.length());
                found_.add(new SegmentFindPart(begin_, next_));
                currentIndex_ = next_;
            }
        }
        editor.getPartsExp().clear();
        editor.getPartsExp().addAllElts(found_);
        editor.setInstance(infoStruct_);
        editor.getFactories().getCompoFactory().invokeNow(new ClearCharacterAttributes(editor.getPreview()));
        FindAction.syntax(editor.getWindowSecEditor().getManageOptions(), editor.getFactories().getCompoFactory(), editor.getPreview());
        int count_ = FindAction.colors(editor.getPartsExp(), editor.getFactories().getCompoFactory(), editor.getPreview());
        editor.getFindingExpression().setEnabled(true);
        editor.setCurrentPartExp(FindAction.partIndex(editor.getPreview().getSelectionStart(), editor.getPartsExp()));
        editor.updateNavSelectExp();
        editor.enableExpRepl(editor.getTargetMethodReplace() != null&&count_ > 0);
    }
}
