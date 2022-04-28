package codechicken.lib.config;

import codechicken.lib.config.parser.AbstractConfigFile;
import codechicken.lib.config.parser.StandardConfigSerializer;

import java.nio.file.Path;

/**
 * Created by covers1624 on 5/3/20.
 */
public class StandardConfigFile extends AbstractConfigFile {

    public StandardConfigFile(Path file) {
        super(file, StandardConfigSerializer.INSTANCE);
    }
}
