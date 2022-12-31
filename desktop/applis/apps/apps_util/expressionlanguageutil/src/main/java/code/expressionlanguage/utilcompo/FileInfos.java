package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.initialize.AbstractLightProgramInfos;
import code.maths.montecarlo.AbstractGenerator;
import code.stream.core.AbstractZipFact;
import code.threads.AbstractThreadFactory;
import code.util.core.DefaultUniformingString;

public final class FileInfos {
    private final AbstractLogger logger;
    private final AbstractFileSystem fileSystem;
    private final AbstractReporter reporter;
    private final AbstractGenerator generator;
    private final AbstractZipFact zipFact;
    private final AbstractThreadFactory threadFactory;

    public FileInfos(AbstractLogger _logger, AbstractFileSystem _fileSystem, AbstractReporter _reporter, AbstractGenerator _generator, AbstractZipFact _zipFact, AbstractThreadFactory _threadFactory) {
        this.logger = _logger;
        this.fileSystem = _fileSystem;
        this.reporter = _reporter;
        this.generator = _generator;
        zipFact = _zipFact;
        threadFactory = _threadFactory;
    }

    public static FileInfos buildMemoryFromFile(AbstractLightProgramInfos _light, AbstractGenerator _generator, AbstractNameValidating _nameValidating, AbstractIssuer _issuer, MemInputFiles _mem, AbstractZipFact _zipFact, AbstractThreadFactory _threadFactory) {
        DefaultUniformingString uniformingString_ = new DefaultUniformingString();
        return new FileInfos(new MemoryLogger(_issuer, _threadFactory),new MemoryFileSystem(_nameValidating, _threadFactory),new MemoryReporter(_light,_mem.getConf(), _mem.getSrc(), _mem.getFiles(), _nameValidating, uniformingString_),_generator, _zipFact, _threadFactory);
    }
    public void tryLogIssue(String _info) {
        AbstractIssuer issuer_ = logger.getIssuer();
        if (issuer_ != null) {
            issuer_.log(_info);
        }
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

    public AbstractZipFact getZipFact() {
        return zipFact;
    }

}
