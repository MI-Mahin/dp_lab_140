package test.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureControllerTest {
    private TemperatureController temperatureController;
    private StateManager mockStateManager;

    @BeforeEach
    void setUp() {
        mockStateManager = Mockito.mock(StateManager.class);
        temperatureController = new TemperatureController(mockStateManager);
    }

    @Test
    void testHeatingActivation() {
        Mockito.when(mockStateManager.getState("temperature")).thenReturn(15);
        temperatureController.controlTemperature(22);
        Mockito.verify(mockStateManager).setState("heatingOn", true);
    }

    @Test
    void testCoolingActivation() {
        Mockito.when(mockStateManager.getState("temperature")).thenReturn(30);
        temperatureController.controlTemperature(22);
        Mockito.verify(mockStateManager).setState("coolingOn", true);
    }

    @Test
    void testHysteresisPrevention() {
        Mockito.when(mockStateManager.getState("temperature")).thenReturn(22);
        temperatureController.controlTemperature(22);
        Mockito.verify(mockStateManager, Mockito.never()).setState(Mockito.anyString(), Mockito.any());
    }
}
