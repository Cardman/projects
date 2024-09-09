package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.dbg.AbsLogDbg;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.maths.litteraladv.MaParser;
import code.maths.litteraladv.MaUserInput;
import code.maths.montecarlo.AbstractGenerator;
import code.util.CustList;
import code.util.Replacement;
import code.util.core.StringUtil;

public final class FctMathEval extends FctMath {

    private final String id;

    public FctMathEval(String _i) {
        id = _i;
    }

    @Override
    public ArgumentWrapper alea(AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct st_ = argumentWrappers_.get(0).getValue();
        Struct seps_ = argumentWrappers_.get(1).getValue();
        return eval(st_, seps_, _cont, _stackCall, id);
    }


    private static ArgumentWrapper eval(Struct _st, Struct _seps, ContextEl _context, StackCall _stackCall, String _id) {
        LgNames lgNames_ = _context.getStandards();
        AbstractGenerator generator_ = lgNames_.getGenerator();
        String val_ = NumParsers.getStringValue(_st);
        CustList<Replacement> repls_ = NumParsers.getReplValue(_seps);
        boolean ok_ = _seps instanceof ArrayStruct;
        CustList<String> rands_ = new CustList<String>();
        String result_ = MaParser.processEl(generator_, rands_, _stackCall.getSeedCust(), new MaUserInput(val_, repls_, ok_));
        CustList<String> chgs_ = new CustList<String>();
        AbsLogDbg lg_ = _stackCall.getStopper().getLogger();
        String paramStr_;
        if (lg_ != null) {
            String un_ = lgNames_.getDisplayedStrings().getUnicode();
            for (Replacement r: repls_) {
                chgs_.add(NumParsers.exportValue(new StringStruct(r.getOldString()),un_).getInstance()+"-"+NumParsers.exportValue(new StringStruct(r.getNewString()),un_).getInstance());
            }
            paramStr_ = _id +":"+NumParsers.exportValue(new StringStruct(val_),un_).getInstance()+","+StringUtil.join(chgs_, ";");
        } else {
            paramStr_ = "";
        }
        log(_stackCall, rands_, paramStr_);
        return new ArgumentWrapper(new StringStruct(result_));
    }

    public static void log(StackCall _stackCall, CustList<String> _rands, String _id) {
        AbsLogDbg lg_ = _stackCall.getStopper().getLogger();
        if (lg_ != null && !_rands.isEmpty()) {
            lg_.log(_id +"=>"+StringUtil.join(_rands, ";"));
        }
    }
}
