public class RunTests {
    public static void main(String[] args) {
        Firewall fw = new Firewall("./test_policies_1.csv");
        assert fw.accept_packet("inbound", "tcp", 80, "192.168.1.2");
        assert fw.accept_packet("inbound", "udp", 53, "192.168.2.1");
        assert fw.accept_packet("outbound", "tcp", 10234, "192.168.10.11");
        assert !fw.accept_packet("outbound", "tcp", 9200, "192.168.10.11");
        assert !fw.accept_packet("inbound", "tcp", 81, "192.168.1.2");
        assert !fw.accept_packet("inbound", "udp", 24, "52.12.48.92");
        System.out.println("All tests passed!");
    }
}
