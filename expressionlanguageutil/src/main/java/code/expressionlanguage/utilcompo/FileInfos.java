package code.expressionlanguage.utilcompo;

public final class FileInfos {
    private final AbstractResourcesReader reader;
    private final AbstractLogger logger;
    private final AbstractFileSystem fileSystem;
    private final AbstractReporter reporter;

    public FileInfos(AbstractResourcesReader reader, AbstractLogger logger, AbstractFileSystem fileSystem, AbstractReporter reporter) {
        this.reader = reader;
        this.logger = logger;
        this.fileSystem = fileSystem;
        this.reporter = reporter;
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
}
