package eapli.ecafeteria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * the application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {

    private static final String PROPERTIES_RESOURCE = "ecafeteria.properties";
    private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private static final String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private static final String SCHEMA_GENERATION_KEY = "javax.persistence.schema-generation.database.action";
    private static final String HIGH_CALORIES_DISH_LIMIT = "HighCaloriesDishLimit";
    
    private static final String LIMIT_HOUR_DAY_BEFORE_NO_COST = "LimitHourDayBeforeNoCost";
    private static final String DINNER_LIMIT_HOUR_DAY_BEFORE_NO_COST = "DinnerLimitHourDayBeforeNoCost";
        
    private final Properties applicationProperties = new Properties();

    public AppSettings() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader()
                .getResourceAsStream(PROPERTIES_RESOURCE)) {
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException(
                        "property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            setDefaultProperties();

            Logger.getLogger(AppSettings.class.getName()).log(Level.SEVERE, null, exio);
        }
    }

    private void setDefaultProperties() {
        this.applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "eapli.ecafeteria.persistence.jpa.JpaRepositoryFactory");
        this.applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
        this.applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "eapli.eCafeteriaPU");
        this.applicationProperties.setProperty(HIGH_CALORIES_DISH_LIMIT, "300");
        
        
        this.applicationProperties.setProperty(LIMIT_HOUR_DAY_BEFORE_NO_COST, 
                "10");
        this.applicationProperties.setProperty(DINNER_LIMIT_HOUR_DAY_BEFORE_NO_COST, 
                "16");
    }

    public Boolean isMenuLayoutHorizontal() {
        return "horizontal"
                .equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
    }

    public String getPersistenceUnitName() {
        return this.applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    public String getRepositoryFactory() {
        return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    public Integer getHighCaloriesDishLimit() {
        return Integer.valueOf(this.applicationProperties.getProperty(HIGH_CALORIES_DISH_LIMIT));
    }

    public Integer getDINNER_LIMIT_HOUR_DAY_BEFORE_NO_COST() {
        return Integer.valueOf(this.applicationProperties.getProperty(DINNER_LIMIT_HOUR_DAY_BEFORE_NO_COST));
    }

    public Integer getLIMIT_HOUR_DAY_BEFORE_NO_COST() {
        return Integer.valueOf(this.applicationProperties.getProperty(LIMIT_HOUR_DAY_BEFORE_NO_COST));
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getExtendedPersistenceProperties() {
        final Map ret = new HashMap();
        ret.put(SCHEMA_GENERATION_KEY,
                this.applicationProperties.getProperty(SCHEMA_GENERATION_KEY));
        return ret;
    }

    public String getProperty(String prop) {
        return this.applicationProperties.getProperty(prop);
    }
}
