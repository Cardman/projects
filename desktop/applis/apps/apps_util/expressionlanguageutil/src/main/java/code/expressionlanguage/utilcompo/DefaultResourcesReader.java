package code.expressionlanguage.utilcompo;

import code.resources.ResourceFiles;

public final class DefaultResourcesReader implements AbstractResourcesReader {
    @Override
    public String read(String _resourceFile) {
        return ResourceFiles.ressourceFichier(_resourceFile);
    }
}
