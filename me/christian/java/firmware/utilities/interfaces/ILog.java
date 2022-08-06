package me.christian.java.firmware.utilities.interfaces;

import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicReference;

public interface ILog {

    AtomicReference<Integer> senderMode = new AtomicReference<>(0);

    default void setSenderMode(int sender) {
        senderMode.set(sender);
    }

    LogBuilder log = new LogBuilder(senderMode.get());

    class LogBuilder implements IMath {

        private int sender;
        private StringBuilder b = new StringBuilder();

        protected LogBuilder(int sender) {
            this.sender = math.constrain(sender, 0, 1);
        }

        public final LogBuilder log(String content) {
            b.append(content);
            return this;
        }


        public final void push() {
            PrintStream printStream = sender == 0 ? System.out : System.err;

            printStream.println(b.toString());

            b.setLength(0);
        }

    }

}
