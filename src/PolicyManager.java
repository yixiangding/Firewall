import java.util.HashMap;
import java.util.TreeSet;

public class PolicyManager {
    HashMap<String, TreeSet<Policy>> policies; // key: "direction,protocol"  value: BST of policies
    public PolicyManager() {
        policies = new HashMap<>();
    }

    public void addPolicy(Policy policy) {
        String policyKey = policy.getDirection() + "," + policy.getProtocol();
        TreeSet<Policy> policySet = policies.getOrDefault(policyKey, new TreeSet<>());
        policySet.add(policy);
        policies.put(policyKey, policySet);
    }

    public boolean willAcceptPacket(String direction, String protocol, String port, String ip) {
        String policyKey = direction + "," + protocol;
        TreeSet<Policy> policySet = policies.get(policyKey);
        if (policySet == null) return false;

        // Policies are sorted in TreeSet in ascending order by ending ip without overlapping
        // Therefore, packet ip is only possible to be contained in the closest ceiling policy entry
        Policy responsiblePolicy = policySet.ceiling(new Policy(direction, protocol, port + "-" + port, ip + "-" + ip));
        if (responsiblePolicy == null) return false;

        return responsiblePolicy.isInPortRange(port) && responsiblePolicy.isInIpRange(ip);
    }
}
