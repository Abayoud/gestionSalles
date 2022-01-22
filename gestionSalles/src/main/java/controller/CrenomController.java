package controller;

import java.io.Console;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Crenom;
import beans.Salle;
import service.CrenomService;
import service.SalleService;

@WebServlet(urlPatterns = {"/CrenomController"})
public class CrenomController extends HttpServlet {
	/**
	 * 
	 */
	
	private CrenomService cs=new CrenomService();
	
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("op") != null) {
            if (request.getParameter("op").equals("load")) {
                response.setContentType("application/json");
                List<Crenom> crenoms = cs.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(crenoms));
            }else if(request.getParameter("op").equals("delete")){
            	System.out.println("d5uul l delete");
                int id = Integer.parseInt(request.getParameter("id"));
                cs.delete(cs.findById(id));
                response.setContentType("application/json");
                List<Crenom> crenoms = cs.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(crenoms));
                
            }else if(request.getParameter("op").equals("update")){
            	System.out.println("d5uul l update");
            	System.out.println(request.getParameter("heureDebut"));
            	System.out.println(request.getParameter("heureFin"));
            	 String heureDebut =request.getParameter("heureDebut");
                 String heureFin = request.getParameter("heureFin");
                 
                 
                 
                 
                 Time heureDeb = Time.valueOf(request.getParameter("heureDebut"));
                 Time heureFi = Time.valueOf(request.getParameter("heureFin"));
            	/*DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            	 Time heureDeb=null;
            	 Time heurefi = null;
				try {
					heureDeb = (Time) formatter.parse(heureDebut);
					 heurefi = (Time) formatter.parse(heureFin);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
            	 
                int id = Integer.parseInt(request.getParameter("id"));
               
              
                System.out.println(id);
                
                cs.update(new Crenom(id,heureDeb, heureFi));
              //  Salle salle=ss.findById(id);
               // ss.update(salle);
                response.setContentType("application/json");
                List<Crenom> crenoms = cs.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(crenoms));
                
            }
        } else {
        	System.out.println("d5ul l create");
            String heureDebut = request.getParameter("heureDebut");
            System.out.println(heureDebut);
            String heureFin=request.getParameter("heureFin");
            System.out.println(heureFin);
           // Time heureDeb = Time.valueOf(request.getParameter("heureDebut")+ ":00");
           // Time heureFi = Time.valueOf(request.getParameter("heureFin")+ ":00");
            Time heureDeb = Time.valueOf(request.getParameter("heureDebut"));
            Time heureFi = Time.valueOf(request.getParameter("heureFin"));
            //  Date dateAchat = new Date(request.getParameter("dateAchat").replace("-", "/"));
           // DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
       //	 Time heureDeb=null;
      // 	Time heurefi=null;
	//	try {
	//		heureDeb = (Time) formatter.parse(heureDebut);
	//		heurefi = (Time) formatter.parse(heureFin);
	//	} catch (ParseException e) {
		//	// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
       	  
            System.out.println("avant creat");
            cs.create(new Crenom(heureDeb, heureFi));
            System.out.println("apres json");
            System.out.println("avant json");
            response.setContentType("application/json");
            System.out.println("apres json");
            List<Crenom> crenoms = cs.findAll();
            System.out.println("avant Gson");
            Gson json = new Gson();
            response.getWriter().write(json.toJson(crenoms));
            System.out.println("apres Gson");
            System.out.println("5ruuuuj");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

	
	
}
