package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.maths.montecarlo.AbstractGenerator;
import code.threads.AbstractThreadFactory;
import code.util.core.DefaultUniformingString;

public final class FileInfos {
    private final AbstractLogger logger;
    private final AbstractFileSystem fileSystem;
    private final AbstractReporter reporter;
    private final AbstractGenerator generator;
    private final AbstractThreadFactory threadFactory;

    public FileInfos(AbstractLogger _logger, AbstractFileSystem _fileSystem, AbstractReporter _reporter, AbstractGenerator _generator, AbstractThreadFactory _threadFactory) {
        this.logger = _logger;
        this.fileSystem = _fileSystem;
        this.reporter = _reporter;
        this.generator = _generator;
        threadFactory = _threadFactory;
    }

    public static FileInfos buildMemoryFromFile(AbstractGenerator _generator, byte[] _conf, byte[] _src, byte[] _files, AbstractNameValidating _nameValidating, AbstractIssuer _issuer, AbstractThreadFactory _threadFactory) {
        DefaultUniformingString uniformingString_ = new DefaultUniformingString();
        return new FileInfos(new MemoryLogger(_nameValidating,_issuer,_threadFactory),new MemoryFileSystem(uniformingString_,_nameValidating,_threadFactory),new MemoryReporter(_conf, _src, _files, _nameValidating, uniformingString_,_threadFactory),_generator,_threadFactory);
    }

    public AbstractLogger getLogger() {
        return logger;
    }

    public AbstractFileSystem getFileSystem() {
        return fileSystem;
    }

    public AbstractReporter getReporter() {
        return reporter;
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public AbstractThreadFactory getThreadFactory() {
        return threadFactory;
    }
}
