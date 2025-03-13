package test.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class RemoteAccessAppTest {
    private RemoteAccessApp remoteAccessApp;
    private StateManager mockStateManager;

    @BeforeEach
    void setUp() {
        mockStateManager = Mockito.mock(StateManager.class);
        remoteAccessApp = new RemoteAccessApp(mockStateManager);
    }

    @Test
    void testGetCurrentState() {
        Mockito.when(mockStateManager.getState("lightsOn")).thenReturn(true);
        assertEquals("Lights are ON", remoteAccessApp.getStateInfo("lightsOn"));
    }

    @Test
    void testSendNotification() {
        remoteAccessApp.sendNotification("Intruder Alert!");
        Mockito.verify(mockStateManager).setState("notification", "Intruder Alert!");
    }
}
