package test.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class VoiceCommandProcessorTest {
    private VoiceCommandProcessor voiceCommandProcessor;
    private StateManager mockStateManager;

    @BeforeEach
    void setUp() {
        mockStateManager = Mockito.mock(StateManager.class);
        voiceCommandProcessor = new VoiceCommandProcessor(mockStateManager);
    }

    @Test
    void testTurnOnLightsCommand() {
        voiceCommandProcessor.processCommand("Turn on the lights");
        Mockito.verify(mockStateManager).setState("lightsOn", true);
    }

    @Test
    void testSetTemperatureCommand() {
        voiceCommandProcessor.processCommand("Set temperature to 22");
        Mockito.verify(mockStateManager).setState("targetTemperature", 22.0);
    }
}
