package real.world.data.pipelines.service;

import java.util.function.Supplier;

public interface GroupingPolicy {
    void logOnConflict(boolean condition);

    void failOnConflict(boolean condition);

    void onConflict(Supplier<?> condition);

    void run(GroupingPolicyActivator groupingPolicyActivator);
}
