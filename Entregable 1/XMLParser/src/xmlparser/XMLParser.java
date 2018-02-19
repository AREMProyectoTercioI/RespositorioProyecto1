/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author David
 */
public class XMLParser {

    /**
     * Este programa esta diseñado para generar la documentacion automatica de un documento bpmn, el cual esta referenciado en el inicio del metodo 
     */
    public static void main(String[] args) {
        FileOutputStream archivoHTML;
        PrintStream p = null;
        try {
            archivoHTML= new FileOutputStream("documentacion.html");
            p = new PrintStream(archivoHTML);
            generarHTML(archivoHTML, p);
            
            
            File inputFile = new File("TestM.bpmn");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList actorList = doc.getElementsByTagName("model:participant");
            NodeList processList = doc.getElementsByTagName("model:process");
            NodeList laneList = doc.getElementsByTagName("model:laneSet");
            NodeList startEventList = doc.getElementsByTagName("model:startEvent");
            NodeList activitiesList = doc.getElementsByTagName("model:userTask");
            NodeList temporiList = doc.getElementsByTagName("model:boundaryEvent");
            NodeList gatewaysList = doc.getElementsByTagName("model:parallelGateway");
            NodeList endList = doc.getElementsByTagName("model:endEvent");
            NodeList servicesList = doc.getElementsByTagName("model:serviceTask");
            NodeList scriptsList = doc.getElementsByTagName("model:scriptTask");
            NodeList relationsList = doc.getElementsByTagName("model:sequenceFlow");
            if(actorList.getLength() >=1){
                agregarAHTML(archivoHTML, p, "Actores ");
            }
            for(int i = 0; i < actorList.getLength(); i++){
                Node nod = actorList.item(i);
                Element eElement = (Element) nod;                
                if (nod.getNodeType() == Node.ELEMENT_NODE ) {
                    agregarSEPHTML(archivoHTML,p);
                    agregarAHTML(archivoHTML, p, "Nombre del Actor: " + eElement.getAttribute("name"));
                    if(eElement.hasChildNodes()){
                        agregarDEHTML(archivoHTML,p, eElement.getElementsByTagName("model:documentation").item(0).getTextContent());
                    }                    
                }
                cerrarSEPHTML(archivoHTML, p);
            }
            if(processList.getLength() >=1){
                agregarAHTML(archivoHTML, p, "Procesos ");
            }
            for(int i = 0; i < processList.getLength(); i++){
                Node nod = processList.item(i);
                Element eElement = (Element) nod;
                if (nod.getNodeType() == Node.ELEMENT_NODE) {                          
                    agregarSEPHTML(archivoHTML,p);
                    agregarAHTML(archivoHTML, p, "Nombre del Proceso: " +eElement.getAttribute("name"));
                    NodeList nd = eElement.getChildNodes();
                    for(int j = 0; j < nd.getLength(); j++){                             
                        if(nd.item(j).getNodeName()=="model:documentation"){                            
                            agregarDEHTML(archivoHTML,p, eElement.getElementsByTagName("model:documentation").item(0).getTextContent());                            
                        }
                    }
                }
                cerrarSEPHTML(archivoHTML, p);
            }
            if(temporiList.getLength() >=1){
                agregarAHTML(archivoHTML, p, "Temporizadores ");
            }
            for(int i = 0; i < temporiList.getLength(); i++){
                Node nod = temporiList.item(i);
                Element eElement = (Element) nod;
                if (nod.getNodeType() == Node.ELEMENT_NODE) {            
                    agregarSEPHTML(archivoHTML,p);
                    agregarAHTML(archivoHTML, p, "Nombre del Temporizador: " +eElement.getAttribute("name"));
                    NodeList nd = eElement.getChildNodes();
                    for(int j = 0; j < nd.getLength(); j++){                        
                        if(nd.item(j).getNodeName()=="model:documentation"){                            
                            agregarDEHTML(archivoHTML,p, eElement.getElementsByTagName("model:documentation").item(0).getTextContent());                            
                        }                                                                            
                    }
                }
                cerrarSEPHTML(archivoHTML, p);
            }
            if(gatewaysList.getLength() >=1){
                agregarAHTML(archivoHTML, p, "Gateways ");
            }
            for(int i = 0; i < gatewaysList.getLength(); i++){
                Node nod = gatewaysList.item(i);
                Element eElement = (Element) nod;
                if (nod.getNodeType() == Node.ELEMENT_NODE) {                   
                    agregarSEPHTML(archivoHTML,p);
                    agregarAHTML(archivoHTML, p, "Definicion del Gateway : " + eElement.getAttribute("name"));
                    NodeList nd = eElement.getChildNodes();
                    for(int j = 0; j < nd.getLength(); j++){                             
                        if(nd.item(j).getNodeName()=="model:documentation"){                            
                            agregarDEHTML(archivoHTML,p, eElement.getElementsByTagName("model:documentation").item(0).getTextContent());                            
                        }
                    }
                }    
                cerrarSEPHTML(archivoHTML, p);
            }
            if(endList.getLength() >=1){
                agregarAHTML(archivoHTML, p, "End Events");            
            }
            for(int i = 0; i < endList.getLength(); i++){
                Node nod = endList.item(i);
                Element eElement = (Element) nod;
                if (nod.getNodeType() == Node.ELEMENT_NODE) {
                    agregarSEPHTML(archivoHTML,p);
                    agregarAHTML(archivoHTML, p, "Nombre del End Event : " + eElement.getAttribute("name"));
                    NodeList nd = eElement.getChildNodes();
                    for(int j = 0; j < nd.getLength(); j++){                             
                        if(nd.item(j).getNodeName()=="model:documentation"){                            
                            agregarDEHTML(archivoHTML,p, eElement.getElementsByTagName("model:documentation").item(0).getTextContent());                                                        
                        }
                    }
                }
                cerrarSEPHTML(archivoHTML,p);
            }
            if(servicesList.getLength() >=1){
                agregarAHTML(archivoHTML, p, "Tareas de servicio");            
            }
            for(int i = 0; i < servicesList.getLength(); i++){
                Node nod = servicesList.item(i);
                Element eElement = (Element) nod;
                if (nod.getNodeType() == Node.ELEMENT_NODE) {
                    agregarSEPHTML(archivoHTML,p);
                    agregarAHTML(archivoHTML, p, "Nombre de la tarea de Servicio : " + eElement.getAttribute("name"));
                    NodeList nd = eElement.getChildNodes();
                    for(int j = 0; j < nd.getLength(); j++){                             
                        if(nd.item(j).getNodeName()=="model:documentation"){                            
                            agregarDEHTML(archivoHTML,p, eElement.getElementsByTagName("model:documentation").item(0).getTextContent());                                                        
                        }
                    }
                }
                cerrarSEPHTML(archivoHTML, p);
            }
            if(scriptsList.getLength() >=1){
                agregarAHTML(archivoHTML, p, "Scripts ");            
            }
            for(int i = 0; i < scriptsList.getLength(); i++){
                Node nod = scriptsList.item(i);
                Element eElement = (Element) nod;
                if (nod.getNodeType() == Node.ELEMENT_NODE) {
                    agregarSEPHTML(archivoHTML,p);
                    agregarAHTML(archivoHTML, p, "Nombre del Script : " + eElement.getAttribute("name"));
                    NodeList nd = eElement.getChildNodes();
                    for(int j = 0; j < nd.getLength(); j++){                             
                        if(nd.item(j).getNodeName()=="model:documentation"){                            
                            agregarDEHTML(archivoHTML,p, eElement.getElementsByTagName("model:documentation").item(0).getTextContent());                                                        
                        }
                    }
                }
                cerrarSEPHTML(archivoHTML, p);
            }
            if(relationsList.getLength() >=1){
                agregarAHTML(archivoHTML, p, "Relaciones");            
            }
            for(int i = 0; i < relationsList.getLength(); i++){
                Node nod = relationsList.item(i);
                Element eElement = (Element) nod;                
                if (nod.getNodeType() == Node.ELEMENT_NODE ) {                                                            
                    if(eElement.hasChildNodes()){
                        agregarSEPHTML(archivoHTML,p);
                        agregarAHTML(archivoHTML, p, "Nombre de la Relacion: " + eElement.getAttribute("name"));
                        NodeList nd = eElement.getChildNodes();
                        for(int j = 0; j < nd.getLength(); j++){                             
                            if(nd.item(j).getNodeName()=="model:documentation"){                            
                                agregarDEHTML(archivoHTML,p, eElement.getElementsByTagName("model:documentation").item(0).getTextContent());                                                        
                            }
                        }
                    }
                }
                cerrarSEPHTML(archivoHTML,p);
            }
            
            cerrarHTML(archivoHTML, p);
        } catch (Exception e) {
            e.printStackTrace();
        }
      }   
    public static void generarHTML(FileOutputStream archivoHTML, PrintStream p){
        
        p.println("<HTML>\n" +
            "    <HEAD>\n" +
            "        <TITLE>BPMNDoc</TITLE>\n" +
            "    </HEAD>\n" +
            "\n" +
            "    <BODY>\n" +
            "        <h1>BPMNDoc</h1>\n"+
            "        <ol type='I'>" +    
            " \n" 
            );
    
    }
    
    
    
    public static void  agregarSEPHTML(FileOutputStream archivoHTML, PrintStream p){
        p.println("<ul class='inside' type='square'>");    
    }
    public static void  cerrarSEPHTML(FileOutputStream archivoHTML, PrintStream p){
        p.println("</ul> \n");    
    }            
    public static void  agregarAHTML(FileOutputStream archivoHTML, PrintStream p, String cad){
        p.println("<li>"+cad+"</li>");    
    }
    public static void agregarDEHTML(FileOutputStream archivoHTML, PrintStream p, String cad){
        p.println("<dd>"+cad+"</dd>");    
    }
    
    
    public static void cerrarHTML(FileOutputStream archivoHTML, PrintStream p){
        p.println("      </OL>\n"+
                "    </BODY>\n" +
            "</HTML>");
        p.close();
    
    }
    
    
    
    
}
