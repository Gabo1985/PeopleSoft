package com.servicios;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.negocio.SOAPClientSAAJ;

/**
 * Servlet implementation class TurnosClienteWebServices
 */
@WebServlet("/TurnosClienteWebServices")
public class TurnosClienteWebServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TurnosClienteWebServices() {
        super();
        // TODO Auto-generated constructor stub
    }
//añadido linea
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String codigoOperacion = request.getParameter("codigoOperacion");
		String identificacion = request.getParameter("identificacion"); 
		String numeroTurno = request.getParameter("numeroTurno"); 
		String tipoServicio = request.getParameter("tipoServicio"); 
		String oficina = request.getParameter("oficina"); 
		String nombreAgente = request.getParameter("nombreAgente"); 
		String modulo = request.getParameter("modulo"); 
		String fechaEmision = request.getParameter("fechaEmision"); 
		String horaEmision = request.getParameter("horaEmision");
		String horaLlamada = request.getParameter("horaLlamada"); 
		String tiempoEspera = request.getParameter("tiempoEspera"); 
		String horaFinalizacion = request.getParameter("horaFinalizacion"); 
		String tiempoAtencion = request.getParameter("tiempoAtencion"); 
		String transferido = request.getParameter("transferido"); 
		String evaluacionServicio = request.getParameter("evaluacionServicio");
		String xmlResul = "";
		try {
			SOAPClientSAAJ soapCliente = new SOAPClientSAAJ();			
			xmlResul = soapCliente.getDatosCliente(codigoOperacion, identificacion, numeroTurno, tipoServicio, oficina, nombreAgente, modulo, fechaEmision, horaEmision, horaLlamada, tiempoEspera, horaFinalizacion, tiempoAtencion, transferido, evaluacionServicio );
			
			response.setContentType("text/xml;charset=UTF-8");
			PrintWriter out = response.getWriter();	
			out.println(xmlResul);	
			
		} catch (Exception e) {			
			//TurnosClienteWebServices(codigoOperacion, identificacion, numeroTurno, tipoServicio, oficina, nombreAgente, modulo, fechaEmision, horaEmision, horaLlamada, tiempoEspera, horaFinalizacion, tiempoAtencion, transferido, evaluacionServicio, e.toString());
			e.printStackTrace();		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	 //public boolean creaArchivoLog(String codigoOperacion, String identificacion, String numeroTurno, String tipoServicio, String oficina, String nombreAgente, String modulo, String fechaEmision, String horaEmision, String horaLlamada, String tiempoEspera, String horaFinalizacion, String tiempoAtencion, String transferido, String evaluacionServicio, String errorLog ) {
	 
	   public void  TurnosClienteWebServices(String codigoOperacion, String identificacion, String numeroTurno, String tipoServicio, String oficina, String nombreAgente, String modulo, String fechaEmision, String horaEmision, String horaLlamada, String tiempoEspera, String horaFinalizacion, String tiempoAtencion, String transferido, String evaluacionServicio, String errorLog) throws IOException {
	    	
	    	Logger log = Logger.getLogger(TurnosClienteWebServices.class.getName());

	        //
	        // Create an instance of FileHandler that write log to a file called
	        // app.log. Each new message will be appended at the at of the log file.
	        //
	        FileHandler fileHandler = new FileHandler("C:\\qmatic\\log\\data.log", true);        
	        log.addHandler(fileHandler);

	        //if (log.isLoggable(Level.INFO)) {
	        //    log.info("This is an information message gf.");
	        //}

	        if (log.isLoggable(Level.WARNING)) {
	            log.warning("This is a warning message gf.");
	        }
	    }
	

}
