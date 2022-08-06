package me.christian.java.firmware.utilities.interfaces;

import java.math.BigDecimal;

public interface IMath {

    Math math = new Math();

    class Math {

        private final Number _c(float a, float min, float max) {
            return a < min ? min : a > max ? max : a;
        }














        public final int    constrain(int a, int min, int max) {
            return _c(a, min, max).intValue();
        }
        public final double constrain(double a, double min, double max) {
            return _c((float)a, (float)min, (float)max).doubleValue();
        }
        public final float  constrain(float a, float min, float max) {
            return _c(a, min, max).floatValue();
        }
        public final long   constrain(long a, long min, long max) {
            return _c(a, min, max).longValue();
        }

    }

}
