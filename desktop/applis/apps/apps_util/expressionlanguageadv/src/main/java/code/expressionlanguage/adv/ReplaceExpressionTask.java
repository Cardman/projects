package code.expressionlanguage.adv;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.Ints;
import code.util.core.DefaultUniformingString;
import code.util.core.NumberUtil;

public final class ReplaceExpressionTask implements Runnable {
    private final TabEditor current;
    private final boolean previousReplace;
    private final boolean nextReplace;

    public ReplaceExpressionTask(TabEditor _editor, boolean _p, boolean _n) {
        current = _editor;
        previousReplace = _p;
        nextReplace = _n;
    }

    @Override
    public void run() {
        current.enableExp(false);
        current.getPrevOccExp().setEnabled(false);
        current.getNextOccExp().setEnabled(false);
        ExecOverrideInfo targetMethod_ = current.getTargetMethodReplace().getOverrideInfo();
        String text_ = current.previewText();
        StringBuilder copy_ = new StringBuilder(text_);
        Struct instance_ = current.getInstance();
        ContextEl act_ = current.getAction();
        int cur_ = current.getCurrentPartExp();
        CustList<SegmentFindPart> rev_ = current.getPartsExp();
        int lastIndex_ = rev_.size() - 1;
        Ints toDelete_ = new Ints();
        int from_ = cur_;
        int diff_ = 0;
        SegmentFindPart selAfter_ = new SegmentFindPart(0,0);
        for (int i = lastIndex_; i >= 0; i--) {
            if (i < cur_ && previousReplace || i > cur_ && nextReplace || i == cur_) {
                SegmentFindPart seg_ = rev_.get(i);
                CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
                ls_.add(new ArgumentWrapper(new StringStruct(copy_.toString())));
                ls_.add(new ArgumentWrapper(new IntStruct(i)));
                ls_.add(new ArgumentWrapper(new IntStruct(seg_.getBegin())));
                ls_.add(new ArgumentWrapper(new IntStruct(seg_.getEnd())));
                StackCall stCall_ = StackCall.newInstance(InitPhase.NOTHING, act_);
                ExecTemplates.wrapAndCall(targetMethod_.getPair(), targetMethod_.getClassName(), new Argument(instance_), act_, stCall_, new ArgumentListCall(ls_));
                Struct re_ = ArgumentListCall.toStr(ProcessMethod.calculate(stCall_.getCallingState(),act_,stCall_).getValue());
                if (act_.callsOrException(stCall_)) {
                    return;
                }
                StringStruct str_ = NumParsers.getString(re_);
                copy_.replace(seg_.getBegin(),seg_.getEnd(),str_.getInstance());
                toDelete_.add(i);
                diff_ += str_.getInstance().length()-(seg_.getEnd() - seg_.getBegin());
                from_= NumberUtil.max(i,cur_);
                selAfter_ = new SegmentFindPart(seg_.getBegin()+diff_,seg_.getEnd()+diff_);
            }
        }
        for (int i = from_ + 1; i <= lastIndex_; i++) {
            SegmentFindPart seg_ = rev_.get(i);
            rev_.set(i,new SegmentFindPart(seg_.getBegin()+diff_,seg_.getEnd()+diff_));
        }
        int d_ = toDelete_.size();
        for (int i = 0; i < d_; i++) {
            rev_.remove(toDelete_.get(i));
        }
        current.previewText(new DefaultUniformingString().apply(copy_.toString()));
        current.previewSelect(selAfter_.getBegin(),selAfter_.getEnd());
        FindExpressionTask.updatedSegColorsAndNav(current);
        current.getApplyExp().setEnabled(true);
    }

}
