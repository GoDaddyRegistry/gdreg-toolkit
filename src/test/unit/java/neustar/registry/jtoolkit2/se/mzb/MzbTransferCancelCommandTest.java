package neustar.registry.jtoolkit2.se.mzb;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;

public class MzbTransferCancelCommandTest {

    private String pw = "jtkUT3st";
    private String roid = "C100000-AR";

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void testMzbTransferCancelCommandWithNameAndPassword() throws SAXException {
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" "
                + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                + "<command><transfer op=\"cancel\">"
                + "<transfer xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\" "
                + "xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">"
                + "<roid>C100000-AR</roid>"
                + "<authInfo><pw>jtkUT3st</pw></authInfo>"
                + "</transfer></transfer><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>";

        String actual = new MzbTransferCancelCommand(roid, pw).toXML();
        assertEquals(expected, actual);
    }

}
