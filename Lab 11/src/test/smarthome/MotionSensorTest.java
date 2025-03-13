package test.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class MotionSensorTest {
    private MotionSensor motionSensor;
    private StateManager mockStateManager;

    @BeforeEach
    void setUp() {
        mockStateManager = Mockito.mock(StateManager.class);
        motionSensor = new MotionSensor(mockStateManager);
    }

    @Test
    void testMotionDetection() {
        motionSensor.detectMotion();
        Mockito.verify(mockStateManager).setState("motionDetected", true);
    }

    @Test
    void testNoMotion() {
        motionSensor.resetMotion();
        Mockito.verify(mockStateManager).setState("motionDetected", false);
    }
}
