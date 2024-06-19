package CounterApplication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.log4j.Logger;

public class Counter implements Serializable {
  public static final Logger LOG = Logger.getLogger(CounterApplication.Counter.class);
  
  private int counter = 0;
  
  public Counter() {
    LOG.info("************************");
    LOG.info("Counter is being created");
    LOG.info("************************");
  }
  
  public void increment() {
    this.counter++;
  }
  
  public int getValue() {
    return this.counter;
  }
  
  private void writeObject(ObjectOutputStream out) throws IOException {
    LOG.info("****************************");
    LOG.info("Serialization is under way.");
    LOG.info("Counter = " + this.counter);
    LOG.info("****************************");
    out.defaultWriteObject();
  }
  
  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
    LOG.info("****************************");
    LOG.info("I've been desrialized!!!");
    LOG.info("Counter = " + this.counter);
    LOG.info("****************************");
  }
}

