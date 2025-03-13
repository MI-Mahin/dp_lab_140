package test.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StateManagerTest {
    private StateManager stateManager;

    @BeforeEach
    void setUp() {
        stateManager = new StateManager();
    }

    @Test
    void testStateManagement() {
        stateManager.setState("temperature", 25);
        assertEquals(25, stateManager.getState("temperature"));
    }

    @Test
    void testMultipleStates() {
        stateManager.setState("lightsOn", true);
        stateManager.setState("motionDetected", false);
        assertTrue((Boolean) stateManager.getState("lightsOn"));
        assertFalse((Boolean) stateManager.getState("motionDetected"));
    }
}

