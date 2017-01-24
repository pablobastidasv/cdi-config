package co.pablobastidasv.config.boundary;

import javax.enterprise.inject.Produces;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pablobastidasv
 */
public class TestPreLoader {

    final static String EXPECTED_STRING_KEY = "stringValue";
    final static String EXPECTED_STRING = "hey buddy";
    final static String EXPECTED_INT_KEY = "intValue";
    private final static String INT_VALUE = "42";
    final static Integer EXPECTED_INT = 42;
    final static String EXPECTED_LONG_KEY = "longValue";
    private final static String LONG_VALUE = "424242424242424242";
    final static Long EXPECTED_LONG = 424242424242424242L;
    final static String EXPECTED_BOOLEAN_KEY = "BooleanValue";
    private final static String BOOLEAN_VALUE = "true";
    final static Boolean EXPECTED_BOOLEAN = Boolean.TRUE;


    @Produces
    public Map<String, String> getInitialConfiguration() {
        Map<String, String> initial = new HashMap<>();

        initial.put(EXPECTED_STRING_KEY, EXPECTED_STRING);
        initial.put(EXPECTED_INT_KEY, INT_VALUE);
        initial.put(EXPECTED_LONG_KEY, LONG_VALUE);
        initial.put(EXPECTED_BOOLEAN_KEY, BOOLEAN_VALUE);

        return initial;
    }


}
