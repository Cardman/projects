package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.Members;
import code.util.CustList;

public final class FetchMemberUtil {
    private FetchMemberUtil() {
    }

    public static void setImplicits(AnaClassArgumentMatching _ana, ImplicitMethods _implicitsOp, ImplicitMethods _implicitsTestOp, Forwards _forwards) {
        CustList<ClassMethodId> implicits_ = _ana.getImplicits();
        String owner_ = "";
        ExecTypeFunction conv_ = null;
        if (!implicits_.isEmpty()) {
            owner_ = implicits_.first().getClassName();
            conv_ = fetchOvTypeFunction(_ana.getMemberId(), _forwards);
        }
        if (conv_ != null) {
            _implicitsOp.getConverter().add(conv_);
            _implicitsOp.setOwnerClass(owner_);
        }
        CustList<ClassMethodId> implicitsTest_ = _ana.getImplicitsTest();
        String ownerTest_ = "";
        ExecTypeFunction convTest_ = null;
        if (!implicitsTest_.isEmpty()) {
            ownerTest_ = implicitsTest_.first().getClassName();
            convTest_ = fetchOvTypeFunction(_ana.getMemberIdTest(), _forwards);
        }
        if (convTest_ != null) {
            _implicitsTestOp.getConverter().add(convTest_);
            _implicitsTestOp.setOwnerClass(ownerTest_);
        }
    }

    public static ImplicitMethods fetchImplicits(ClassMethodId _clMet, MemberId _id, Forwards _forwards) {
        ExecTypeFunction conv_ = null;
        String converterClass_ = "";
        if (_clMet != null) {
            converterClass_ = _clMet.getClassName();
            conv_ = fetchOvTypeFunction(_id, _forwards);
        }
        if (conv_ != null) {
            ImplicitMethods converter_ = new ImplicitMethods();
            converter_.getConverter().add(conv_);
            converter_.setOwnerClass(converterClass_);
            return converter_;
        }
        return null;
    }

    public static ExecRootBlock fetchType(int _nbRoot, Forwards _forwards) {
        if (_forwards.isMember(_nbRoot)) {
            return _forwards.getMember(_nbRoot).getRootBlock();
        }
        return null;
    }

    public static ExecRootBlock fetchType(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        if (_forwards.isMember(rootNumber_)) {
            return _forwards.getMember(rootNumber_).getRootBlock();
        }
        return null;
    }

    public static ExecInfoBlock fetchField(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        int memberNumber_ = _id.getMemberNumber();
        if (_forwards.isMember(rootNumber_) && _forwards.getMember(rootNumber_).isField(memberNumber_)) {
            return _forwards.getMember(rootNumber_).getField(memberNumber_);
        }
        return null;
    }

    public static ExecNamedFunctionBlock fetchFunctionOp(ExecRootBlock _declaring,MemberId _id, Forwards _forwards) {
        return fetchFunctionOrOp(_declaring,_id.getRootNumber(),_id.getMemberNumber(),_id.getMemberNumber(), _forwards);
    }

    private static ExecNamedFunctionBlock fetchFunctionOrOp(ExecRootBlock _declaring,int _rootNumber, int _memberNumber, int _operatorNumber, Forwards _forwards) {
        if (_forwards.isMember(_rootNumber)) {
            if (_declaring instanceof ExecAnnotationBlock) {
                return _forwards.getMember(_rootNumber).getNamed(_memberNumber);
            }
            if (_forwards.getMember(_rootNumber).isOvNamed(_memberNumber)) {
                return _forwards.getMember(_rootNumber).getOvNamed(_memberNumber);
            }
            return null;
        }
        return fetchOperator(_operatorNumber, _forwards);
    }

    public static ExecOperatorBlock fetchOperator(int _operatorNumber, Forwards _forwards) {
        if (_forwards.isOperator(_operatorNumber)) {
            return _forwards.getOperator(_operatorNumber);
        }
        return null;
    }

    public static ExecNamedFunctionBlock fetchCtorFunction(Members _member, int _nbMember) {
        if (_member.isCtor(_nbMember)) {
            return _member.getCtor(_nbMember);
        }
        return null;
    }

    public static ExecTypeFunction fetchTypeFunction(ExecRootBlock _declaring,MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        int memberNumber_ = _id.getMemberNumber();
        if (_forwards.isMember(rootNumber_)) {
            Members mem_ = _forwards.getMember(rootNumber_);
            if (_declaring instanceof ExecAnnotationBlock) {
                return new ExecTypeFunction(mem_.getRootBlock(),mem_.getNamed(memberNumber_));
            }
            if (mem_.isOvNamed(memberNumber_)) {
                return new ExecTypeFunction(mem_.getRootBlock(),mem_.getOvNamed(memberNumber_));
            }
        }
        return null;
    }

    public static ExecTypeFunction fetchOvTypeFunction(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        int memberNumber_ = _id.getMemberNumber();
        if (_forwards.isMember(rootNumber_)) {
            Members mem_ = _forwards.getMember(rootNumber_);
            if (mem_.isOvNamed(memberNumber_)) {
                return new ExecTypeFunction(mem_.getRootBlock(),mem_.getOvNamed(memberNumber_));
            }
        }
        return null;
    }

    public static ExecTypeFunction fetchTypeCtor(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        int memberNumber_ = _id.getMemberNumber();
        if (_forwards.isMember(rootNumber_)) {
            Members mem_ = _forwards.getMember(rootNumber_);
            return new ExecTypeFunction(mem_.getRootBlock(),fetchCtorFunction(mem_,memberNumber_));
        }
        return null;
    }

    public static ExecClassArgumentMatching toExec(AnaClassArgumentMatching _cl) {
        return new ExecClassArgumentMatching(_cl.getNames(),_cl.getUnwrapObjectNb(),
                _cl.isCheckOnlyNullPe(),_cl.isConvertToString());
    }

    public static ExecTypeFunction defPair(ExecRootBlock _ex, ExecTypeFunction _pair) {
        ExecTypeFunction pair_ = _pair;
        if (pair_ == null) {
            pair_ = new ExecTypeFunction(_ex,null);
        }
        return pair_;
    }
}
