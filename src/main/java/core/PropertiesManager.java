package core;

import com.utils.TestUtils;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    public static final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    PropertiesManager() {
        throw new IllegalStateException("Properties Manager class");
    }

    public static String getEnvironmentSpecFromProperty(String propertyName) {
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(propertyName);
    }

}
