public interface FirewallDefinition {
    public boolean accept_packet(String direction, String protocol, int port, String ip_address);
}
