import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Firewall implements FirewallDefinition {

    Firewall(String pathToPolicies) {
        parsePolicies(pathToPolicies);
    }

    private void parsePolicies(String pathToPolicies) {
        try {
            BufferedReader policyReader = new BufferedReader(new FileReader(new File(pathToPolicies)));
            String policyLine;
            while ((policyLine = policyReader.readLine()) != null) {
                String[] policyElements = policyLine.split(",");
                String direction = policyElements[0];
                String protocol = policyElements[1];
                // Unify single port/ip into ranged format
                String portRange = policyElements[2].contains("-") ? policyElements[2] : policyElements[2] + "-" + policyElements[2];
                String ipRange = policyElements[3].contains("-") ? policyElements[3] : policyElements[3] + "-" + policyElements[3];
                storePolicy(direction, protocol, portRange, ipRange);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void storePolicy(String direction, String protocol, String portRange, String ipRange) {
        // TODO: implement policies
    }

    @Override
    public boolean accept_packet(String direction, String protocol, int port, String ip_address) {
        return false;
    }
}
