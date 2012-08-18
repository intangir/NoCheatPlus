package fr.neatmonster.nocheatplus.checks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.entity.Player;

public class ViolationHistory {
    public class Violation {
        public final String check;

        public long         time;
        public double       VL;

        public Violation(final String check, final double VL) {
            this.check = check;
            time = System.currentTimeMillis();
            this.VL = VL;
        }

        public void add(final double VL) {
            time = System.currentTimeMillis();
            this.VL += VL;
        }
    }

    private static Map<String, ViolationHistory> histories = new HashMap<String, ViolationHistory>();

    public static ViolationHistory getHistory(final Player player) {
        if (!histories.containsKey(player.getName()))
            histories.put(player.getName(), new ViolationHistory());
        return histories.get(player.getName());
    }

    private final List<Violation> violations = new ArrayList<Violation>();

    public TreeMap<Long, Violation> getViolations() {
        final TreeMap<Long, Violation> violations = new TreeMap<Long, Violation>();
        for (final Violation violation : this.violations)
            violations.put(violation.time, violation);
        return violations;
    }

    public void log(final String check, final double VLAdded) {
        for (final Violation violation : violations)
            if (check.equals(violation.check)) {
                violation.add(VLAdded);
                return;
            }
        violations.add(new Violation(check, VLAdded));
    }
}
