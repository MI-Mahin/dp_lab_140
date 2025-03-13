package test.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class EnergyTrackerTest {
    private EnergyTracker energyTracker;
    private StateManager mockStateManager;

    @BeforeEach
    void setUp() {
        mockStateManager = Mockito.mock(StateManager.class);
        energyTracker = new EnergyTracker(mockStateManager);
    }

    @Test
    void testMonitorEnergyUsage() {
        energyTracker.updatePowerUsage(500);
        Mockito.verify(mockStateManager).setState("powerUsage", 500);
    }

    @Test
    void testUnusualConsumption() {
        Mockito.when(mockStateManager.getState("powerUsage")).thenReturn(1000);
        boolean alert = energyTracker.detectUnusualUsage();
        assertTrue(alert);
    }
}
