package test.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class LightManagerTest {
    private LightManager lightManager;
    private StateManager mockStateManager;

    @BeforeEach
    void setUp() {
        mockStateManager = Mockito.mock(StateManager.class);
        lightManager = new LightManager(mockStateManager);
    }

    @Test
    void testTurnOnLights() {
        lightManager.turnOn();
        Mockito.verify(mockStateManager).setState("lightsOn", true);
    }

    @Test
    void testTurnOffLights() {
        lightManager.turnOff();
        Mockito.verify(mockStateManager).setState("lightsOn", false);
    }
}
