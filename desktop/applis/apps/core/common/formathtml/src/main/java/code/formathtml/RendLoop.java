package code.formathtml;

public interface RendLoop extends RendBreakableBlock, RendWithEl {

    void processLastElementLoop(Configuration _conf);
}
