package code.expressionlanguage;

import java.util.concurrent.locks.ReentrantLock;

import code.util.EntryCust;
import code.util.StringMap;

public class CustLockingClass extends DefaultLockingClass {

    private StringMap<ReentrantLock> locks = new StringMap<ReentrantLock>();
    private StringMap<ContextEl> types = new StringMap<ContextEl>();

    @Override
    public void initAlwaysSuccess() {
        super.initAlwaysSuccess();
        for (EntryCust<String, InitClassState> e: getClasses().entryList()) {
            if (e.getValue() == InitClassState.SUCCESS) {
                continue;
            }
            String name_ = e.getKey();
            locks.put(name_, new ReentrantLock());
            types.put(name_, null);
        }
    }

    @Override
    public InitClassState getState(ContextEl _context, String _className) {
        if (_context.isInitEnums()) {
            return super.getState(_context, _className);
        }
        if (getAlwayasInit().containsStr(_className)) {
            return InitClassState.SUCCESS;
        }
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
        if (_context.isInitEnums()) {
            super.successClass(_context, _className);
            return;
        }
        ReentrantLock lock_ = locks.getVal(_className);
        lock_.lock();
        super.successClass(_context, _className);
        lock_.unlock();
    }
    @Override
    public void errorClass(ContextEl _context, String _className) {
        if (_context.isInitEnums()) {
            super.errorClass(_context, _className);
            return;
        }
        ReentrantLock lock_ = locks.getVal(_className);
        lock_.lock();
        super.errorClass(_context, _className);
        lock_.unlock();
    }
}
