package code.formathtml.exec.blocks;


import code.formathtml.Configuration;

public interface RendWithEl {

    void processEl(Configuration _cont);

    int getOffsetTrim();
}
