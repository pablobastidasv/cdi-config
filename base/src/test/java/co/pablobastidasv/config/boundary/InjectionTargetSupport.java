package co.pablobastidasv.config.boundary;

import javax.inject.Inject;

/**
 * @author pablobastidasv
 */
public class InjectionTargetSupport {


    private String stringValue;
    private Integer intValue;
    private Long longValue;
    private Boolean booleanValue;


    @Inject
    @Config(TestPreLoader.EXPECTED_STRING_KEY)
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Inject
    @Config(TestPreLoader.EXPECTED_INT_KEY)
    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    @Inject
    @Config(TestPreLoader.EXPECTED_LONG_KEY)
    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    @Inject
    @Config(TestPreLoader.EXPECTED_BOOLEAN_KEY)
    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    Integer getIntValue() {
        return intValue;
    }

    String getStringValue() {
        return stringValue;
    }

    Long getLongValue() {
        return longValue;
    }

    Boolean getBooleanValue() {
        return booleanValue;
    }
}
