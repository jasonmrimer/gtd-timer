package edu.louisville.cse640.rimer.gtd_timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class JDBCContextListener implements ServletContextListener {
  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    System.out.println("!!!!!!!!!destructo");
    // This fixes the JDBC driver not unloading corectly on a context reload  for DB2 JDBC 4.19.66
    try {
      System.out.println("Trying to stop the DB2 timer thread");
      new com.ibm.db2.jcc.am.lg() {
        {
          if (a != null) {
            a.cancel();
          } else {
            System.out.println("Timer is null, skipped");
          }
        }
      };
      System.out.println("Stopped the timer");
    } catch (Exception e) {
      System.out.println("Could not stop the DB2 timer thread " + e.getMessage());
    }
  }
}
