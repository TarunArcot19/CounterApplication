package CounterApplication;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class CounterServlet extends HttpServlet {
   public static final Logger LOG;
   private String titleMessage = "Counter Servlet";

   public void init(ServletConfig config) throws ServletException {
      super.init(config);
      this.titleMessage = config.getInitParameter("titleMessage");
   }

   public void destroy() {
   }

   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");
      HttpSession session = request.getSession();
      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<head>");
      out.println("<title>" + this.titleMessage + "</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<font size='16'>");
      out.println(this.titleMessage);
      out.println("</font><br><br>");
      Counter counter = this.getSessionObj(session);
      counter.increment();
      LOG.info("*****************");
      LOG.info("Counter = " + counter.getValue());
      LOG.info("sessionID = " + request.getSession().getId());
      LOG.info("*****************");
      out.println("Counter = " + counter.getValue());
      out.println("</body>");
      out.println("</html>");
      out.close();
   }

   private Counter getSessionObj(HttpSession session) {
      Counter counter = (Counter)session.getAttribute("foo");
      if (counter == null) {
         counter = new Counter();
         session.setAttribute("foo", counter);
      }

      return counter;
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.processRequest(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.processRequest(request, response);
   }

   public String getServletInfo() {
      return "Counter Servlet";
   }

   static {
      LOG = Logger.getLogger(CounterServlet.class);
   }
}
