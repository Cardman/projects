package code.expressionlanguage.utilfiles;

import code.expressionlanguage.utilcompo.AbstractResourcesReader;
import code.resources.ResourceFiles;

public final class DefaultResourcesReader implements AbstractResourcesReader {
    @Override
    public String read(String _resourceFile) {
        return ResourceFiles.ressourceFichier(_resourceFile);
    }
}
