package code.expressionlanguage.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class InvokingOperation extends MethodOperation {

    public InvokingOperation(int _index,
            ContextEl _importingPage, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _importingPage, _indexChild, _m, _op);
    }

    CustList<ClassArgumentMatching> listClasses(CustList<OperationNode> _children, ContextEl _conf) {
        if (!_children.isEmpty() && _children.first().isVararg()) {
            CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
            CustList<ClassArgumentMatching> optArgs_ = new CustList<ClassArgumentMatching>();
            CustList<OperationNode> optArgsNodes_ = new CustList<OperationNode>();
            boolean opt_ = false;
            for (OperationNode o: _children) {
                if (o.isVararg()) {
                    continue;
                }
                if (o.isFirstOptArg()) {
                    opt_ = true;
                }
                if (opt_) {
                    optArgs_.add(o.getResultClass());
                    optArgsNodes_.add(o);
                } else {
                    firstArgs_.add(o.getResultClass());
                }
            }
            String name_ = _children.first().getResultClass().getName();
            StringMap<StringList> map_;
            map_ = new StringMap<StringList>();
            String glClass_ = _conf.getLastPage().getGlobalClass();
            Classes classes_ = _conf.getClasses();
            if (glClass_ != null) {
                for (TypeVar t: Templates.getConstraints(glClass_, classes_)) {
                    map_.put(t.getName(), t.getConstraints());
                }
            }
            Mapping mapping_ = new Mapping();
            mapping_.setParam(name_);
            for (OperationNode o: optArgsNodes_) {
                setRelativeOffsetPossibleLastPage(o.getIndexInEl(), _conf);
                String argType_ = o.getResultClass().getName();
                mapping_.setArg(argType_);
                mapping_.setMapping(map_);
                if (!Templates.isCorrect(mapping_, classes_)) {
                    throw new DynamicCastClassException(argType_+RETURN_LINE+name_+RETURN_LINE+_conf.joinPages());
                }
            }
            name_ = PrimitiveTypeUtil.getPrettyArrayType(name_);
            ClassArgumentMatching clMatch_ = new ClassArgumentMatching(name_);
            firstArgs_.add(clMatch_);
            return firstArgs_;
        }
        CustList<ClassArgumentMatching> firstArgs_ = new CustList<ClassArgumentMatching>();
        for (OperationNode o: _children) {
            firstArgs_.add(o.getResultClass());
        }
        return firstArgs_;
    }

    static CustList<Argument> listArguments(CustList<OperationNode> _children, int _natVararg, CustList<Argument> _nodes, ContextEl _context) {
        if (!_children.isEmpty() && _children.first().isVararg()) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            boolean opt_ = false;
            int i_ = CustList.FIRST_INDEX;
            for (OperationNode o: _children) {
                if (o.isVararg()) {
                    i_++;
                    continue;
                }
                if (o.isFirstOptArg()) {
                    opt_ = true;
                }
                Argument a_ = _nodes.get(i_);
                if (opt_) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
                i_++;
            }
            Argument argRem_ = new Argument();
            String g_ = _children.first().getResultClass().getName();
            Argument glObj_ = _context.getLastPage().getGlobalArgument();
            boolean native_ = true;
            if (glObj_ != null && !glObj_.isNull()) {
                String glClass_ = glObj_.getObjectClassName();
                String gl_ = _context.getLastPage().getGlobalClass();
                gl_ = StringList.getAllTypes(gl_).first();
                gl_ = Templates.getFullTypeByBases(glClass_, gl_, _context.getClasses());
                glClass_ = gl_;
                g_ = Templates.format(glClass_, g_, _context.getClasses());
                Classes classes_ = _context.getClasses();
                if (classes_ != null) {
                    native_ = !_context.getClasses().isCustomType(g_);
                }
            }
            if (native_) {
                int len_ = optArgs_.size();
                Object array_;
                array_ = InstanceOperation.newClassicArray(_context, g_, g_, new int[]{len_});
                Struct arr_ = new Struct(array_);
                for (int i = 0; i < len_; i++) {
                    Argument chArg_ = optArgs_.get(i);
                    ArrOperation.setCheckedElement(arr_, i, chArg_, _context);
                }
                argRem_.setStruct(arr_);
            } else {
                int len_ = optArgs_.size();
                Struct[] array_ = new Struct[len_];
                String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(g_);
                Struct str_ = new Struct(array_,clArr_);
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Argument chArg_ = optArgs_.get(i);
                    ArrOperation.setCheckedElement(str_, i, chArg_, _context);
                }
                argRem_.setStruct(str_);
            }
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Argument> optArgs_ = new CustList<Argument>();
            int lenCh_ = _children.size();
            for (int i = CustList.FIRST_INDEX; i < lenCh_; i++) {
                Argument a_ = _nodes.get(i);
                if (i >= _natVararg) {
                    optArgs_.add(a_);
                } else {
                    firstArgs_.add(a_);
                }
            }
            Argument argRem_ = new Argument();
            String g_ = _children.first().getResultClass().getName();
            Argument glObj_ = _context.getLastPage().getGlobalArgument();
            boolean native_ = true;
            if (glObj_ != null && !glObj_.isNull()) {
                String glClass_ = glObj_.getObjectClassName();
                String gl_ = _context.getLastPage().getGlobalClass();
                gl_ = StringList.getAllTypes(gl_).first();
                gl_ = Templates.getFullTypeByBases(glClass_, gl_, _context.getClasses());
                glClass_ = gl_;
                g_ = Templates.format(glClass_, g_, _context.getClasses());
                Classes classes_ = _context.getClasses();
                if (classes_ != null) {
                    native_ = !_context.getClasses().isCustomType(g_);
                }
            }
            if (native_) {
                int len_ = optArgs_.size();
                Object array_;
                array_ = InstanceOperation.newClassicArray(_context, g_, g_, new int[]{len_});
                Struct arr_ = new Struct(array_);
                for (int i = 0; i < len_; i++) {
                    Argument chArg_ = optArgs_.get(i);
                    ArrOperation.setCheckedElement(arr_, i, chArg_, _context);
                }
                argRem_.setStruct(arr_);
            } else {
                int len_ = optArgs_.size();
                Struct[] array_ = new Struct[len_];
                String clArr_ = PrimitiveTypeUtil.getPrettyArrayType(g_);
                Struct str_ = new Struct(array_,clArr_);
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Argument chArg_ = optArgs_.get(i);
                    ArrOperation.setCheckedElement(str_, i, chArg_, _context);
                }
                argRem_.setStruct(str_);
            }
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>(_nodes);
        return firstArgs_;
    }

    final boolean lookOnlyForVarArg() {
        OperationNode first_ = getFirstChild();
        if (first_ == null) {
            return false;
        }
        return first_.isVararg();
    }

    abstract boolean isCallMethodCtor();
}
