public class Policy implements Comparable<Policy> {
    private String direction;
    private String protocol;
    private Range ipRange;
    private Range portRange;

    Policy(String direction, String protocol, String portRange, String ipRange) {
        this.direction = direction;
        this.protocol = protocol;
        this.portRange = new Range(portRange);
        this.ipRange = new Range(ipRange);
    }

    // Comparing end IPs by their comparable values
    @Override
    public int compareTo(Policy that) {
        long thisIpValue = getIpValueByIp(this.ipRange.getEnd());
        long thatIpValue = getIpValueByIp(that.ipRange.getEnd());

        if (thisIpValue == thatIpValue) return 0;
        else if (thisIpValue < thatIpValue) return -1;
        else return 1;
    }

    // convert IP to comparable value
    private long getIpValueByIp(String ip) {
        String[] endIp = ip.split("\\.");
        return Long.valueOf(endIp[0]) * 255 * 255 * 255 + Long.valueOf(endIp[1]) * 255 * 255
                + Long.valueOf(endIp[2]) * 255 + Long.valueOf(endIp[3]);
    }

    // representing IPs in comparable values, so given IP can be compared to given bounds
    public boolean isInIpRange(String ip) {
        long ipValue = getIpValueByIp(ip);
        return ipValue >= getIpValueByIp(ipRange.getStart()) && ipValue <= getIpValueByIp(ipRange.getEnd());
    }

    public boolean isInPortRange(String port) {
        int portInt = Integer.valueOf(port);
        return portInt >= Integer.valueOf(portRange.getStart()) && portInt <= Integer.valueOf(portRange.getEnd());
    }

    public String getDirection() {
        return direction;
    }

    public String getProtocol() {
        return protocol;
    }
}
