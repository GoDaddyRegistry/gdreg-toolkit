package godaddy.registry.jtoolkit2.se.mzb;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import godaddy.registry.jtoolkit2.Timer;
import godaddy.registry.jtoolkit2.se.CLTRID;

public class MzbTransferApproveCommandTest {
    private static final String CL_ID = "JTKUTEST";
    private static final String NAME_1 = "roid-roid";
    private static final String PW_1 = "app1evo";

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID(CL_ID);
    }

    @Test
    public void testDomainTransferApproveCommandWithNameOnly() throws SAXException {
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" "
                + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                + "<command><transfer op=\"approve\">"
                + "<transfer xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\" "
                + "xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">"
                + "<roid>roid-roid</roid>"
                + "<authInfo><pw>app1evo</pw></authInfo>"
                + "</transfer></transfer><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>";

        String actual = new MzbTransferApproveCommand(NAME_1, PW_1).toXML();
        assertEquals(expected, actual);
    }

}
