package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.exec.blocks.ExecInfoBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
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
            conv_ = fetchTypeFunction(_ana.getMemberId(), _forwards);
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
            convTest_ = fetchTypeFunction(_ana.getMemberIdTest(), _forwards);
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
            conv_ = fetchTypeFunction(_id, _forwards);
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
        if (_forwards.getMapMembers().isValidIndex(_nbRoot)) {
            return _forwards.getMapMembers().getValue(_nbRoot).getRootBlock();
        }
        return null;
    }

    public static ExecRootBlock fetchType(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        if (_forwards.getMapMembers().isValidIndex(rootNumber_)) {
            return _forwards.getMapMembers().getValue(rootNumber_).getRootBlock();
        }
        return null;
    }

    public static ExecInfoBlock fetchField(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        int memberNumber_ = _id.getMemberNumber();
        if (_forwards.getMapMembers().isValidIndex(rootNumber_)) {
            if (_forwards.getMapMembers().getValue(rootNumber_).getAllFields().isValidIndex(memberNumber_)) {
                return _forwards.getMapMembers().getValue(rootNumber_).getAllFields().getValue(memberNumber_);
            }
        }
        return null;
    }

    public static ExecNamedFunctionBlock fetchFunctionOp(MemberId _id, Forwards _forwards) {
        return fetchFunctionOrOp(_id.getRootNumber(),_id.getMemberNumber(),_id.getMemberNumber(), _forwards);
    }

    private static ExecNamedFunctionBlock fetchFunctionOrOp(int _rootNumber, int _memberNumber, int _operatorNumber, Forwards _forwards) {
        if (_forwards.getMapMembers().isValidIndex(_rootNumber)) {
            if (_forwards.getMapMembers().getValue(_rootNumber).getAllNamed().isValidIndex(_memberNumber)) {
                return _forwards.getMapMembers().getValue(_rootNumber).getAllNamed().getValue(_memberNumber);
            }
            return null;
        }
        if (_forwards.getMapOperators().isValidIndex(_operatorNumber)) {
            return _forwards.getMapOperators().getValue(_operatorNumber);
        }
        return null;
    }

    public static ExecNamedFunctionBlock fetchFunction(Members _member, int _nbMember) {
        if (_member.getAllNamed().isValidIndex(_nbMember)) {
            return _member.getAllNamed().getValue(_nbMember);
        }
        return null;
    }

    public static ExecTypeFunction fetchTypeFunction(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        int memberNumber_ = _id.getMemberNumber();
        if (_forwards.getMapMembers().isValidIndex(rootNumber_)) {
            Members mem_ = _forwards.getMapMembers().getValue(rootNumber_);
            if (mem_.getAllNamed().isValidIndex(memberNumber_)) {
                return new ExecTypeFunction(mem_.getRootBlock(),mem_.getAllNamed().getValue(memberNumber_));
            }
        }
        return null;
    }

    public static ExecTypeFunction fetchTypeCtor(MemberId _id, Forwards _forwards) {
        int rootNumber_ = _id.getRootNumber();
        int memberNumber_ = _id.getMemberNumber();
        if (_forwards.getMapMembers().isValidIndex(rootNumber_)) {
            Members mem_ = _forwards.getMapMembers().getValue(rootNumber_);
            return new ExecTypeFunction(mem_.getRootBlock(),fetchFunction(mem_,memberNumber_));
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
