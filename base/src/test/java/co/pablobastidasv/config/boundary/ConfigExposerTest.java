package co.pablobastidasv.config.boundary;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author pablobastidasv
 */
@RunWith(CdiTestRunner.class)
public class ConfigExposerTest {

    private InjectionTargetSupport its;

    @Inject
    public void setIts(InjectionTargetSupport its) {
        this.its = its;
    }

    @Test
    public void getString() throws Exception {
        assertThat(its.getStringValue()).
                isNotNull().
                isEqualTo(TestPreLoader.EXPECTED_STRING);
    }

    @Test
    public void getLong() throws Exception {
        assertThat(its.getLongValue()).
                isNotNull().
                isEqualTo(TestPreLoader.EXPECTED_LONG);

    }

    @Test
    public void getInt() throws Exception {
        assertThat(its.getIntValue()).
                isNotNull().
                isEqualTo(TestPreLoader.EXPECTED_INT);

    }

    @Test
    public void getBoolean() throws Exception {
        assertThat(its.getBooleanValue()).
                isNotNull().
                isEqualTo(TestPreLoader.EXPECTED_BOOLEAN);

    }
}