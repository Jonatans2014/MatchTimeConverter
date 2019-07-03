import com.match.time.converter.TimeConverter;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeConverterTest {
    private TimeConverter timeConverter;

    LinkedList<String> matchInput = new LinkedList<String>();

    @Test
    public void testPrematchValid() {

        matchInput.add("[PM] 0:00.000");

        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();

        assertEquals("00:00 - PRE_MATCH", timeConverter.displayNewFormat());
    }

    @Test
    public void testFirstHAlfValid() {

        matchInput.add("[H1] 0:15.025");

        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();

        assertEquals("00:15 - FIRST_HALF", timeConverter.displayNewFormat());
    }

    @Test
    public void testFirstHalfExtraSecondFromMillisecondsValid() {
        matchInput.add("[H1] 0:15.025");
        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();

        assertEquals("00:15 - FIRST_HALF", timeConverter.displayNewFormat());
    }

    @Test
    public void testFirstHalfExtraTimeExpectedValid() {

        matchInput.add("[H1] 45:00.001");
        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();
        assertEquals("45:00 +00:00 - FIRST_HALF", timeConverter.displayNewFormat());
    }

    @Test
    public void testFirstHalfExtraTimeWithMinuteSecondMillisecondValid() {
        matchInput.add("[H1] 46:15.752");
        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();

        assertEquals("45:00 +01:16 - FIRST_HALF", timeConverter.displayNewFormat());
    }

    @Test
    public void testValidHalfTime() {

        matchInput.add("[HT] 45:00.000");
        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();

        assertEquals("45:00 - HALF_TIME", timeConverter.displayNewFormat());
    }

    @Test
    public void testValidSecondHalfWithMilliseconds() {
        matchInput.add("[H2] 45:00.500");

        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();

        assertEquals("45:01 - SECOND_HALF", timeConverter.displayNewFormat());
    }

    @Test
    public void testValidSecondHalfFullTimeExtraTimeWithMilisecond() {
        matchInput.add("[H2] 90:00.908");
        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();

        assertEquals("90:00 +00:01 - SECOND_HALF", timeConverter.displayNewFormat());
    }

    @Test
    public void testValidFullTimeExtraTime() {
        matchInput.add("[FT] 90:00.000");
        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();
        assertEquals("90:00 - FULL_TIME", timeConverter.displayNewFormat());
    }

    @Test
    public void testInvalidInputNoPeriod() {
        matchInput.add("90:00");
        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();
        assertEquals("INVALID", timeConverter.displayNewFormat());
    }

    @Test
    public void testInvalidInputUndefinedPeriod() {
        matchInput.add("[H3] 90:00.000");
        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();
        assertEquals("INVALID", timeConverter.displayNewFormat());
    }

    @Test
    public void testNegativeTimeInvalidInput() {
        matchInput.add("[PM] -10:00.000");

        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();
        assertEquals("INVALID", timeConverter.displayNewFormat());
    }

    @Test
    public void testInputInvalid() {
        matchInput.add("FOO");

        timeConverter = new TimeConverter(matchInput);
        timeConverter.parseMatch();
        assertEquals("INVALID", timeConverter.displayNewFormat());
    }
}
