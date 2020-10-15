package code.expressionlanguage.utilcompo;

import code.maths.montecarlo.AbstractGenerator;

public final class FileInfos {
    private final AbstractResourcesReader reader;
    private final AbstractLogger logger;
    private final AbstractFileSystem fileSystem;
    private final AbstractReporter reporter;
    private final AbstractGenerator generator;

    public FileInfos(AbstractResourcesReader _reader, AbstractLogger _logger, AbstractFileSystem _fileSystem, AbstractReporter _reporter, AbstractGenerator _generator) {
        this.reader = _reader;
        this.logger = _logger;
        this.fileSystem = _fileSystem;
        this.reporter = _reporter;
        this.generator = _generator;
    }

    public AbstractResourcesReader getReader() {
        return reader;
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
}
