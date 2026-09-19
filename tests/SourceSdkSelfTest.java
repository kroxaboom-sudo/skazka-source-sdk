import com.kroxaboom.skazka.source.SourceAdapter;
import com.kroxaboom.skazka.source.SourceAdapters;
import com.kroxaboom.skazka.source.SourceCapability;
import com.kroxaboom.skazka.source.registry.HostPolicy;
import com.kroxaboom.skazka.source.registry.SourceEndpoint;

import java.util.EnumSet;
import java.util.Set;

public final class SourceSdkSelfTest {
    public static void main(String[] args) {
        SourceAdapters adapters = new SourceAdapters();
        SourceAdapter source = new SourceAdapter() {
            @Override
            public String id() {
                return "example";
            }

            @Override
            public Set<SourceCapability> capabilities() {
                return EnumSet.of(SourceCapability.SEARCH, SourceCapability.CHAPTER_CONTENT);
            }
        };

        adapters.install(source);
        check(adapters.require("example").supports(SourceCapability.SEARCH), "capability lookup");
        check(!adapters.require("example").supports(SourceCapability.AUTH), "unsupported capability");

        boolean duplicateRejected = false;
        try {
            adapters.install(source);
        } catch (IllegalStateException expected) {
            duplicateRejected = true;
        }
        check(duplicateRejected, "duplicate adapter rejected");

        SourceEndpoint healthy = new SourceEndpoint(
                "example",
                "mirror.example.org",
                SourceEndpoint.Status.VERIFIED,
                SourceEndpoint.Health.HEALTHY,
                10,
                10,
                3,
                2,
                0,
                80
        );
        SourceEndpoint down = new SourceEndpoint(
                "example",
                "old.example.org",
                SourceEndpoint.Status.VERIFIED,
                SourceEndpoint.Health.UNREACHABLE,
                10,
                5,
                3,
                2,
                1,
                80
        );

        check(healthy.trusted(), "verified endpoint trusted");
        check(down.trusted(), "unreachable verified endpoint remains trusted");
        check(healthy.globalPriority() > down.globalPriority(), "health changes priority, not trust");

        check(HostPolicy.safePublicHost("mirror.example.org"), "public hostname");
        check(!HostPolicy.safePublicHost("localhost"), "localhost rejected");
        check(!HostPolicy.safePublicHost("10.0.0.1"), "IPv4 rejected");
        check(!HostPolicy.safePublicHost("service.internal"), "internal suffix rejected");
        check(
                "mirror.example.org".equals(HostPolicy.httpsHost("https://mirror.example.org/path")),
                "HTTPS host extraction"
        );
        check(HostPolicy.httpsHost("http://mirror.example.org").isEmpty(), "HTTP rejected");

        System.out.println("PASS: Skazka Source SDK contracts and registry policy");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
