package godaddy.registry.jtoolkit2.se.mzb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import godaddy.registry.jtoolkit2.Timer;
import godaddy.registry.jtoolkit2.se.CLTRID;
import godaddy.registry.jtoolkit2.se.Command;

public class MzbTransferRejectCommandTest {

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void testMzbTransferRejectCommandWithRoidAndPassword() {
        Command cmd = new MzbTransferRejectCommand("roid-roid", "jtkUt3st");
        try {
            String xml = cmd.toXML();
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                    "<command><transfer op=\"reject\">" +
                    "<transfer xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\"" +
                    " xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">" +
                    "<roid>roid-roid</roid>" +
                    "<authInfo><pw>jtkUt3st</pw></authInfo>" +
                    "</transfer>" +
                    "</transfer>" +
                    "<clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }
}
