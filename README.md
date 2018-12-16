# Firewall

## How to test
Since it's a Java program, it can be run and tested in JVM environment with RunTests.java being the main class.
Don't forget to run with "-ea" flag as VM option, otherwise the assertion in tests won't work!

## Design and Thoughts
My design is using binary search tree to manage all IP ranges of policies. All policies parsed from CSV will be stored 
in Java's TreeSet and sorted by IP range's ending IP. Every time there is a incoming packet, it can be compared with
the ceiling entry of BST to see whether the packet will be accepted or not. If n denotes number of different entries in CSV
file, then this solution has worst case O(logn) lookup time, and O(n) space to store policy information.

This solution (A) makes trade-offs. As there is another solution (B) where we can use 2^32 buckets to store policies by IP addresses.
Even though solution B has improvement on time as O(1), it sacrifice a lot of space (2^32). So I still believe my solution (A)
is better because it's decent in both time and space.

## Improvement if I had more time
### Time Used
75 minutes
### Potential Improvements
- I made assumption that there will be no overlapping entries in CSV file (i.e. won't be two entries with same direction, protocol,
but overlapping IP addresses). However, if there are overlapping entries, it won't be too hard to handle since all policies have 
been stored in sorted order, and all I need to do is "merge intervals".
- The test cases coverage can be improved, and unit tests can be introduced.
- Code can be cleaner. To be more specific, I have a couple of "Range" in different format, which can be unified.

## Interests
Platform, Policy, Data
