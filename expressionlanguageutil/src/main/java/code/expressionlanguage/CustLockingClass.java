package code.expressionlanguage;

import java.util.concurrent.locks.ReentrantLock;

import code.expressionlanguage.methods.RootBlock;
import code.util.StringMap;

public class CustLockingClass extends DefaultLockingClass {

    private StringMap<ReentrantLock> locks = new StringMap<ReentrantLock>();
    private StringMap<ContextEl> types = new StringMap<ContextEl>();

    @Override
    public void init(ContextEl _context) {
        super.init(_context);
        for (RootBlock r: _context.getClasses().getClassBodies()) {
            String name_ = r.getFullName();
            locks.put(name_, new ReentrantLock());
            types.put(name_, null);
        }
    }

    @Override
    public InitClassState getState(ContextEl _context, String _className) {
        while (true) {
            ReentrantLock lock_ = locks.getVal(_className);
            lock_.lock();
            InitClassState res_ = getClasses().getVal(_className);
            if (res_.isFinished()) {
                lock_.unlock();
                return res_;
            }
            ContextEl cont_ = types.getVal(_className);
            if (cont_ == _context) {
                lock_.unlock();
                return InitClassState.PROGRESSING;
            }
            if (cont_ == null) {
                types.put(_className, _context);
                lock_.unlock();
                return InitClassState.NOT_YET;
            }
            lock_.unlock();
        }
    }
    @Override
    public void successClass(ContextEl _context, String _className) {
        ReentrantLock lock_ = locks.getVal(_className);
        lock_.lock();
        super.successClass(_context, _className);
        lock_.unlock();
    }
    @Override
    public void errorClass(ContextEl _context, String _className) {
        ReentrantLock lock_ = locks.getVal(_className);
        lock_.lock();
        super.errorClass(_context, _className);
        lock_.unlock();
    }
}
