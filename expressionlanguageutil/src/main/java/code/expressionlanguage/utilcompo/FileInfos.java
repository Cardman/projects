package code.expressionlanguage.utilcompo;

import code.maths.montecarlo.AbstractGenerator;

public final class FileInfos {
    private final AbstractResourcesReader reader;
    private final AbstractLogger logger;
    private final AbstractFileSystem fileSystem;
    private final AbstractReporter reporter;
    private final AbstractGenerator generator;

    public FileInfos(AbstractResourcesReader reader, AbstractLogger logger, AbstractFileSystem fileSystem, AbstractReporter reporter, AbstractGenerator generator) {
        this.reader = reader;
        this.logger = logger;
        this.fileSystem = fileSystem;
        this.reporter = reporter;
        this.generator = generator;
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
