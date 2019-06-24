package com.negocio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.soap.*;
import com.servicios.*;

public class SOAPClientSAAJ {
	/**
     * Method used to create the SOAP Request
     */
   public void SOAPClientSAAJ(){}
   private static SOAPMessage createSOAPRequest(String codigoOperacion, String identificacion, String numeroTurno, String tipoServicio, String oficina, String nombreAgente, String modulo, String fechaEmision, String horaEmision,
		   String horaLlamada, String tiempoEspera, String horaFinalizacion, String tiempoAtencion, String transferido, String evaluacionServicio) throws Exception
    {
    	MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();          
        String hratiempoAtencion = "00:00:00";
        
        String serverURI = "DO_SISTEMAS_TURNOS_SRV.v1";
       // String serverURI = "DO_SISTEMAS_TURNOS_SRV.1.wsdl";
        
         try {
        	 
 	        // SOAP Envelope
 	        SOAPEnvelope envelope = soapPart.getEnvelope();
 	        envelope.addNamespaceDeclaration("new", serverURI);
 	        
 	        // SOAP Body
 	        SOAPBody soapBody = envelope.getBody();
 	        SOAPElement soapBodyElem = soapBody.addChildElement("TurnosCliente", "new");
 	        
 	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("CodigoOperacion");
 	        soapBodyElem1.addTextNode(codigoOperacion); 
 	        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("Identificacion");
 	        soapBodyElem2.addTextNode(identificacion); 
 	        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("NumeroTurno");
 	        soapBodyElem3.addTextNode(numeroTurno);
 	        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("TipoServicio");
 	        soapBodyElem4.addTextNode(tipoServicio);
 	        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("Oficina");
 	        soapBodyElem5.addTextNode(oficina);
 	        SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("NombreAgente");
 	        soapBodyElem6.addTextNode(nombreAgente.toUpperCase());
 	        SOAPElement soapBodyElem7 = soapBodyElem.addChildElement("Modulo");
 	        soapBodyElem7.addTextNode(modulo);	    
 	        SOAPElement soapBodyElem8 = soapBodyElem.addChildElement("FechaEmision");
 	        soapBodyElem8.addTextNode(getFechaActual());
 	        SOAPElement soapBodyElem9 = soapBodyElem.addChildElement("HoraEmision"); 
 	        soapBodyElem9.addTextNode(getHoraActual());	     
 	        SOAPElement soapBodyElem10 = soapBodyElem.addChildElement("HoraLlamada");
 	        soapBodyElem10.addTextNode(getHoraActual());
 	        SOAPElement soapBodyElem11 = soapBodyElem.addChildElement("TiempoEspera");
 	        soapBodyElem11.addTextNode(tiempoEspera);
 	        SOAPElement soapBodyElem12 = soapBodyElem.addChildElement("HoraFinalizacion");
 	        horaFinalizacion = getHoraActual();
 	        soapBodyElem12.addTextNode(horaFinalizacion); 
 	        SOAPElement soapBodyElem13 = soapBodyElem.addChildElement("TiempoAtencion");
 	        hratiempoAtencion= tiempoAtencion;
 	        tiempoAtencion = getCalculaHoras(hratiempoAtencion, horaFinalizacion);
 	        soapBodyElem13.addTextNode(tiempoAtencion);	     
 	        SOAPElement soapBodyElem14 = soapBodyElem.addChildElement("Transferido");
 	        soapBodyElem14.addTextNode(transferido);
 	        
 	        SOAPElement soapBodyElem15 = soapBodyElem.addChildElement("EvaluacionServicio");
 	        soapBodyElem15.addTextNode(evaluacionServicio);	        

 	        MimeHeaders headers = soapMessage.getMimeHeaders();
 	        headers.addHeader("SOAPAction", serverURI);
 	        
             soapMessage.saveChanges();
             soapMessage.removeAllAttachments();
			
         		} catch (Exception e) {
         			e.printStackTrace();
         		}	
        
    	             
            return soapMessage;
    }
	
    /*
    * Method used to retorna the SOAP Request
    */
    public static String soapMessageToString(SOAPMessage message) throws Exception
    {
           String result = null;
           ByteArrayOutputStream baos =  new ByteArrayOutputStream();
           if (message != null) 
           {
               
               try
               {                   
                   message.writeTo(baos);
                   result = baos.toString();
               }
               catch (Exception e)
               {
            	   e.printStackTrace();
               }
               finally
               {
                   if (baos != null)
                   {
                       try
                       {
                           baos.close();
                       }
                       catch (IOException ioe)
                       {
                    	   baos.close();
                    	   ioe.printStackTrace();
                       }
                   }
               }
           }
           return result;
       } 
        	
	public String getDatosCliente(String _codigoOperacion, String _identificacion, String _numeroTurno, String _tipoServicio, String _oficina, String _nombreAgente, String _modulo, String _fechaEmision, String _horaEmision,
			String _horaLlamada, String _tiempoEspera, String _horaFinalizacion, String _tiempoAtencion, String _transferido, String _evaluacionServicio) throws Exception
	{
		String xmlResul = "";
		SOAPConnectionFactory soapConnectionFactory;
        SOAPConnection soapConnection = null; 
        soapConnectionFactory = SOAPConnectionFactory.newInstance();
		soapConnection = soapConnectionFactory.createConnection();
		try
        {
			
         //Send SOAP Message to SOAP Server ** el nombre de la maquina del WS: wspsoft1(ip=10.100.176.13)
         String url = "http://10.100.176.116:8000/PSIGW/PeopleSoftServiceListeningConnector"; //Definitivo en Diners Produccion
         //http://wspswebren1:8000/PSIGW/PeopleSoftServiceListeningConnector/DO_SISTEMAS_TURNOS_SRV.1.wsdl   Nueva URL WS PARA PRUEBAS
                  //"http://10.100.176.13:8000/PSIGW/PeopleSoftServiceListeningConnector"; //Definitivo en Diners Produccion
                  //http://wspsoft1:8000/PSIGW/PeopleSoftServiceListeningConnector/DO_SISTEMAS_TURNOS_SRV.1.wsdl 
                  //http://desanp55a:8000/PSIGW/PeopleSoftServiceListeningConnector
        	       //http://10.100.178.7:8000/PSIGW/PeopleSoftServiceListeningConnector/DO_SISTEMAS_TURNOS_SRV.1.wsdl
         SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(_codigoOperacion, _identificacion, _numeroTurno,  _tipoServicio, _oficina, _nombreAgente, _modulo, _fechaEmision, _horaEmision,
        		 _horaLlamada, _tiempoEspera, _horaFinalizacion, _tiempoAtencion, _transferido, _evaluacionServicio), url);
         //soapConnection.wait(10000);// Tiempo que respponte el ws         
         xmlResul =  soapMessageToString(soapResponse);
         
         soapConnection.close();
        
        }
        catch (Exception e)
        {        	
        	soapConnection.close();	
        	try {
        		TurnosClienteWebServices(_codigoOperacion, _identificacion, _numeroTurno,  _tipoServicio, _oficina, _nombreAgente, _modulo, _fechaEmision, _horaEmision,
                  		 _horaLlamada, _tiempoEspera, _horaFinalizacion, _tiempoAtencion, _transferido, _evaluacionServicio, e.toString());	
			} catch (Exception e2) {
				soapConnection.close();			
			}
        	
        	e.printStackTrace();            
        }		
		
		return xmlResul ;
	}
	
	public void  TurnosClienteWebServices(String codigoOperacion, String identificacion, String numeroTurno, String tipoServicio, String oficina, String nombreAgente, String modulo, String fechaEmision, String horaEmision, String horaLlamada, String tiempoEspera, String horaFinalizacion, String tiempoAtencion, String transferido, String evaluacionServicio, String errorLog) throws IOException {
	    	
	    	Logger log = Logger.getLogger(TurnosClienteWebServices.class.getName());

	        //
	        // Create an instance of FileHandler that write log to a file called
	        // app.log. Each new message will be appended at the at of the log file.
	        //
	        FileHandler fileHandler = new FileHandler("C:\\qmatic\\log\\data.log", true);        
	        log.addHandler(fileHandler);

	        log.warning(codigoOperacion +";"+ identificacion +";"+ numeroTurno +";"+ tipoServicio +";"+ oficina +";"+ nombreAgente +";"+ modulo +";"+ fechaEmision +";"+ horaEmision +";"+ horaLlamada +";"+ tiempoEspera +";"+ horaFinalizacion +";"+ tiempoAtencion +";"+ transferido +";"+ evaluacionServicio +";"+ errorLog);
	        //if (log.isLoggable(Level.INFO)) {
	        //    log.info("This is an information message gf.");
	        //}

	       // if (log.isLoggable(Level.WARNING)) {
	       //     log.warning("This is a warning message gf.");
	       // }
	    }
	  
	
	
	 public static String getFechaActual() {
	     Date fechaSist = new Date();
	     SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	     return formateador.format(fechaSist);
	   }
	    
	    public static String getHoraActual() {
	     Date ahora = new Date();
	     SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
	     return formateador.format(ahora);
	   }
	    
	    /*
		   *Funcion calculo el tiempo de atencion  
		   */	   
	public static String getCalculaHoras(String horaInicial, String horaFinal)
		   {
		// System.out.println( horaInicial+ horaFinal);  
			String result = "";
			   SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			   SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");			  
				try {
					if(horaInicial.length() > 0){						
						if(horaFinal.length() > 0){
							
							Date dateHorInicial = formatter.parse(horaInicial); 			
							Calendar calendarI = Calendar.getInstance();
							calendarI .setTime(dateHorInicial);
						
							Date dateHorFinal = formatter1.parse(horaFinal); 
							Calendar calendarF = Calendar.getInstance();
							calendarF .setTime(dateHorFinal);
			
							long milis1 = 0;
							long milis2 = 0;
							long milisegundos = 0;
							long hora = 0;
					        long restohora = 0;					
					        long minuto = 0;
					        long restominuto = 0;					
					        long segundo = 0;					        
					        String difHora =  "";
					        String difMinuto =  "";
					        String difSegundo =  "";	
							
							// conseguir la representacion de la fecha en milisegundos			
					         milis1 = calendarI.getTimeInMillis();		
					         milis2 = calendarF.getTimeInMillis();
					
					        // calcular la diferencia en milisengundos
					         milisegundos = milis2 - milis1;
					        
					         hora = milisegundos/3600000;
					         restohora = milisegundos%3600000;					
					         minuto = restohora/60000;
					         restominuto = restohora%60000;					
					         segundo = restominuto/1000;	
					        
					         difHora =  Long.toString(hora);
					         difMinuto =  Long.toString(minuto);
					         difSegundo =  Long.toString(segundo);
					        if(difHora.length() < 2)
					        	difHora = "0" + difHora;
					        if(difMinuto.length() < 2)
					        	difMinuto = "0" + difMinuto;
					        if(difSegundo.length() < 2)
					        	difSegundo = "0" + difSegundo;
					        
					        result = difHora + ":" + difMinuto + ":" + difSegundo ;
					        }
						else
						{
							result = "59:59:59";
							}
						}
					else
					{
						result = "59:59:59";
					}
				} catch (Exception e) {
					result = "59:59:59";
					
				}
				 System.out.println( horaInicial+ horaFinal);  
			   return result;
		   }
}
