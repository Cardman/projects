package code.expressionlanguage.adv;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.ArrayRefState;
import code.expressionlanguage.exec.calls.util.CustomReflectConstructor;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class FindExpressionTask implements Runnable {
    private final TabEditor editor;

    public FindExpressionTask(TabEditor _e) {
        this.editor = _e;
    }
    @Override
    public void run() {
        editor.getFindingExpression().setEnabled(false);
        ResultContextViewReplacer vr_ = editor.getResultContext();
        ContextEl rCont_ = editor.getAction();
        String text_ = editor.previewText();
        ExecConstructorOverrideInfo info_ = editor.getTargetMethodView();
        ArrayStruct empty_ = new ArrayStruct(0, StringExpUtil.getPrettyArrayType(rCont_.getStandards().getCoreNames().getAliasObject()));
        StackCall first_ = StackCall.newInstance(InitPhase.NOTHING, rCont_);
        ArrayRefState a_ = ArrayRefState.tryWrap(empty_,0);
        Struct infoStruct_ = ProcessMethod.calculate(new CustomReflectConstructor(info_.getMetaInfo(),a_),rCont_, first_).getValue();
        if (rCont_.callsOrException(first_)) {
            return;
        }
        CustList<SegmentFindPart> found_ = new CustList<SegmentFindPart>();
        ExecOverrideInfo targetMethod_ = info_.getOverrideInfo();
        int currentIndex_ = 0;
        while (currentIndex_ >= 0) {
            CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
            ls_.add(new ArgumentWrapper(new StringStruct(text_)));
            ls_.add(new ArgumentWrapper(new IntStruct(currentIndex_)));
            StackCall stCall_ = StackCall.newInstance(InitPhase.NOTHING, rCont_);
            ExecTemplates.wrapAndCall(new ExecOverrideInfo(targetMethod_.getClassName(),targetMethod_.getPair()), infoStruct_, rCont_, stCall_, new ArgumentListCall(ls_));
            Struct re_ = ProcessMethod.calculate(stCall_.getCallingState(),rCont_,stCall_).getValue();
            if (rCont_.callsOrException(stCall_)) {
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
        updatedSegColorsAndNav(editor);
    }

    static void updatedSegColorsAndNav(TabEditor _editor) {
        int count_ = updatedSegColorsBase(_editor);
        _editor.setCurrentPartExp(FindAction.partIndex(_editor.getPreview().getSelectionStart(), _editor.getPartsExp()));
        _editor.enableExpRepl(_editor.getTargetMethodReplace() != null&&count_ > 0);
        _editor.updateNavSelectExp();
        _editor.getFindingExpression().setEnabled(true);
    }

    static int updatedSegColorsBase(TabEditor _editor) {
        _editor.getFactories().getCompoFactory().invokeNow(new ClearCharacterAttributes(_editor.getPreview()));
        FindAction.syntax(_editor.getWindowSecEditor().getManageOptions(), _editor.getFactories().getCompoFactory(), _editor.getPreview());
        return FindAction.colors(_editor.getPartsExp(), _editor.getFactories().getCompoFactory(), _editor.getPreview());
    }
}
