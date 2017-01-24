package co.pablobastidasv.config.boundary;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.lang.reflect.AccessibleObject;
import java.util.Map;
import java.util.Objects;

/**
 * Configuration main class, this expose the principal produces and initialize the store
 *
 * @author pablobastidasv
 */
@ApplicationScoped
public class ConfigExposer {

    private Instance<Map<String, String>> sourceValues;
    private Cache<String, String> store;
    private static final String CONFIGURATION = "configuration";

    @Inject
    public void setSourceValues(Instance<Map<String, String>> sourceValues) {
        this.sourceValues = sourceValues;
    }

    /**
     * Produce a String value depending on the value indicated in @Config
     *
     * @see co.pablobastidasv.config.boundary.Config
     *
     * @return The String value cached with the values indicated in config
     */
    @Produces
    public String getString(InjectionPoint ip){
        Config annotation = ((AccessibleObject) ip.getMember()).getAnnotation(Config.class);
        if(annotation == null){
            return "";
        }

        if(annotation.value().isEmpty()){
            throw new IllegalArgumentException("Config value must not be empty.");
        }

        return this.store.get(annotation.value());
    }

    @Produces
    public Long getLong(InjectionPoint ip){
        String stringValue = getString(ip);
        if(Objects.isNull(stringValue) ||
                stringValue.isEmpty()){
            return 0L;
        }
        return Long.parseLong(stringValue);
    }

    @Produces
    public Integer getInt(InjectionPoint ip){
        String stringValue = getString(ip);
        if(Objects.isNull(stringValue) ||
                stringValue.isEmpty()){
            return 0;
        }
        return Integer.parseInt(stringValue);
    }

    @Produces
    public Boolean getBoolean(InjectionPoint ip){
        String stringValue = getString(ip);
        if(Objects.isNull(stringValue) ||
                stringValue.isEmpty()){
            return Boolean.FALSE;
        }
        return Boolean.parseBoolean(stringValue);
    }

    public void init(@Observes @Initialized(ApplicationScoped.class) Object doesntMatter){
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        store = cacheManager.getCache(CONFIGURATION, String.class, String.class);
        if(store == null){
            this.store = cacheManager.createCache(CONFIGURATION, getConfiguration());
        }

        sourceValues.forEach(store::putAll);
    }

    private Configuration<String, String> getConfiguration() {
        return new MutableConfiguration<String, String>().
                setStoreByValue(false).
                setTypes(String.class, String.class).
                setManagementEnabled(true).
                setStatisticsEnabled(true);
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object doesntMatter){
        store.close();
    }


}
