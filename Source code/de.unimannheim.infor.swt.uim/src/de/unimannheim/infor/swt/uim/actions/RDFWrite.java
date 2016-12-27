/*******************************************************************************
 * Copyright (c) 2016 University of Mannheim: Chair for Software Engineering
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dongze Wang - initial API and implementation and initial documentation
 *******************************************************************************/
package de.unimannheim.infor.swt.uim.actions;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.jena.atlas.logging.LogCtl;
//import com.hp.hpl.jena.rdf.model.Model;
//import com.hp.hpl.jena.rdf.model.ModelFactory;
//import com.hp.hpl.jena.rdf.model.Property;
//import com.hp.hpl.jena.rdf.model.Resource;
//import com.hp.hpl.jena.sparql.algebra.Op;
//import com.hp.hpl.jena.vocabulary.VCARD;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;


public class RDFWrite {
//	static String  outputpath="C:/Users/Dongze/Documents/UIMrdf5.owl";
	
	static String  outputpath=Login.trtextrdffoutputx();
	static String  nsproperties = "http://uim/properties/";
	static String   nrelationship= "http://uim/relationship/";
	static String   dname= null;

	static  Model model2 = ModelFactory.createDefaultModel();
	static Property pid=model2.createProperty(nsproperties, "id");
	static Property pname =model2.createProperty(nsproperties, "name");
	static  Property pFQN=model2.createProperty(nsproperties, "FQN");
	static  Property pcontainer=model2.createProperty(nsproperties, "container");
	static Property pp_id=model2.createProperty(nsproperties, "p_id");
	static  Property pnumber=model2.createProperty(nsproperties, "number");
	static  Property ppotincy=model2.createProperty(nsproperties, "potincy");
	static  Property pdirecttype=model2.createProperty(nsproperties, "direct_type");
    static Property plabel=model2.createProperty(nsproperties, "label");
    static   Property pkind=model2.createProperty(nsproperties, "kind");
    static   Property pparticipant_id=model2.createProperty(nsproperties, "participant_id");
    static   Property plower=model2.createProperty(nsproperties, "lower");
    static   Property pupper=model2.createProperty(nsproperties, "upper");
    static    Property prow_name=model2.createProperty(nsproperties, "row_name");
    static    Property pwhole=model2.createProperty(nsproperties, "whole");
    static   Property pdisjoint=model2.createProperty(nsproperties, "disjoint");
    static   Property pcomplete=model2.createProperty(nsproperties, "complete");
    static   Property pparticipant=model2.createProperty(nsproperties, "participant");
    static   Property pinheritanceRelationship_id=model2.createProperty(nsproperties, "inheritanceRelationship_id");
    static   Property pduribility=model2.createProperty(nsproperties, "duribility");
    static   Property ptype=model2.createProperty(nsproperties, "type");
    static   Property pvalue=model2.createProperty(nsproperties, "value");
    static   Property pmutability=model2.createProperty(nsproperties, "mutability");
    static   Property pmethod=model2.createProperty(nsproperties, "method");
    static   Property psignature=model2.createProperty(nsproperties, "signature");
    static   Property pbody=model2.createProperty(nsproperties, "body");
  //  static   Property pkey=model2.createProperty(nsproperties, "key");
    static   Property pbinarlabel=model2.createProperty(nsproperties, "pbinarlabel");
    static   Property pparticipant1=model2.createProperty(nsproperties, "participant2");
    static   Property pparticipant2=model2.createProperty(nsproperties, "articipant2");   
    static   Property proleName1=model2.createProperty(nsproperties, "roleName1");
    static   Property proleName2=model2.createProperty(nsproperties, "roleName2");
    static   Property plower1=model2.createProperty(nsproperties, "lower1");
    static   Property plower2=model2.createProperty(nsproperties, "lower2");
    static   Property pupper1=model2.createProperty(nsproperties, "upper1");
    static   Property pupper2=model2.createProperty(nsproperties, "upper2");    
    static   Property pnavigalbeTo1=model2.createProperty(nsproperties, "navigalbeTo1");
    static   Property pnavigableTo2=model2.createProperty(nsproperties, "navigableTo2");
 
    static   Property rparticipant1=model2.createProperty(nrelationship, "participant1");    
    static   Property rparticpant2=model2.createProperty(nrelationship, "participant2");
    static   Property rparent=model2.createProperty(nrelationship, "parent");
    static   Property inheritance=model2.createProperty(nrelationship, "inheritance");    
    static   Property rparticipant=model2.createProperty(nrelationship, "participant");
    static   Property directtype=model2.createProperty(nrelationship, "direct_type");
   
    
    
    

    
    
	
	
	public static void main(String[] args){  

	}
	


public static void  Deepmodelresourcecreate(int i,int j,String FQN,String name, String container,Model model1) 
{
LogCtl.setCmdLogging();
       dname=name;
       outputpath=Login.trtextrdffoutputx();
       if(outputpath.equals(null))
	   { 
		System.out.println("UIM service can not get RDF outpath, please restart the UIM service");   
	   }  
       
     String nsdeepmodel = "http://uim/deepmodel/";
     Resource deepmodelx = model1.createResource(nsdeepmodel+name);  
     deepmodelx.addProperty(plabel, "deepmodel");  
    String si =String.valueOf(i);  
     deepmodelx.addProperty(pid, si);  
     deepmodelx.addProperty(pname, name);  
     deepmodelx.addProperty(pFQN, FQN); 
     deepmodelx.addProperty(pcontainer, container); 
     String sj =String.valueOf(j);  
     deepmodelx.addProperty(pp_id, sj);

       }

public static void  Levelresourcecreate(int id,String FQN,String name, String container,String number,Model model1) 
    {
       LogCtl.setCmdLogging();
        String nslevel = "http://uim/level/";
        Resource level = model1.createResource(nslevel+FQN);
        level.addProperty(plabel, "level");
        String sid=String.valueOf(id);
        level.addProperty(pid, sid);  
        level.addProperty(pFQN, FQN); 
        level.addProperty(pname, name); 
        level.addProperty(pcontainer, container); 
        level.addProperty(pnumber, number); 
}
public static void  Entityresourcecreate(int id,String name ,String FQN, String container,int potincy,String directtype,Model model1) 
   {
     LogCtl.setCmdLogging();


        String nsentity = "http://uim/entity/";

       Resource entity = model1.createResource(nsentity+FQN); 
       String spotency =String.valueOf(potincy); 
       String sid =String.valueOf(id);  
       entity.addProperty(plabel, "entity");  
       entity.addProperty(pid, sid);  
       entity.addProperty(pname, name); 
       entity.addProperty(pFQN, FQN); 
       entity.addProperty(pcontainer, container); 

      entity.addProperty(ppotincy, spotency);      
          }

public static void  attributeresourcecreate(int id,String name ,String FQN, String container,String duribility,String type,String value,String mutability,Model model1) 
{
         LogCtl.setCmdLogging();
         String nsattribute = "http://uim/attribute/";
         Resource attribute = model1.createResource(nsattribute+FQN); 
         String sid =String.valueOf(id);  
    	 if(type==null)
    	 { 	
    		 type="null";
    		 
    	 }
    	
   
    	 if(value==null)
    	 { 	
    		 value="null";
    		 
    	 }
         attribute.addProperty(plabel, "attribute");  
         attribute.addProperty(pid, sid);  
         attribute.addProperty(pname, name); 
         attribute.addProperty(pFQN, FQN); 
         attribute.addProperty(pcontainer, container);         
         attribute.addProperty(pduribility, duribility); 
         attribute.addProperty(ptype, type); 
         attribute.addProperty(pvalue, value); 
         attribute.addProperty(pmutability, mutability); 

            }
public static void  methodresourcecreate(int id,String name ,String FQN, String container,String duribility,String signature,String body,Model model1) 
{
  LogCtl.setCmdLogging();


     String nsentity = "http://uim/method/";

         Resource method = model1.createResource(nsentity+FQN);     
         String sid =String.valueOf(id);  
         method.addProperty(plabel, "method");  
         method.addProperty(pid, sid);  
         method.addProperty(pname, name); 
         method.addProperty(pFQN, FQN); 
         method.addProperty(pcontainer, container);
         method.addProperty(pduribility, duribility); 
         method.addProperty(psignature, signature);
         if(body==null)
    	 { 	
    		 body="null";
    		 
    	 }
         method.addProperty(pbody, body); 
        
            }
public static void  Binaryresourcecreate(int id,String FQN,String name, String container,int potency,String directtype,String label ,String particpant1,
		String particpant2, String roleName1 ,String roleName2,String  lower1, String lower2 ,	String upper1 ,String upper2 , 
		String navigalbeTo1, String navigableTo2,Model model1) 
     {
	     LogCtl.setCmdLogging();
	     String nsbinar = "http://uim/binaryconnection/";
	     Resource binar = model1.createResource(nsbinar+FQN);  
	     binar.addProperty(plabel, "binaryconnection");  
	     String sid=String.valueOf(id);
	     binar.addProperty(pid, sid);  
	     binar.addProperty(pname, name); 
	     binar.addProperty(pFQN, FQN); 
	     binar.addProperty(pcontainer, container); 
	     String spotency=String.valueOf(potency);
    	 binar.addProperty(ppotincy, spotency); 
        binar.addProperty(pparticipant1, particpant1);  
          binar.addProperty(pparticipant2, particpant2);  
          binar.addProperty(pbinarlabel, label); 
          binar.addProperty(proleName1, roleName1); 
          binar.addProperty(proleName2, roleName2);
          binar.addProperty(plower1, lower1); 
          binar.addProperty(plower2, lower2);
          binar.addProperty(pupper1, upper1); 
          binar.addProperty(pupper2, upper2);
          binar.addProperty(pnavigalbeTo1, navigalbeTo1); 
          binar.addProperty(pnavigableTo2, navigableTo2);
  
        	}
         
 

public static void  Binaryrelationshipsetting(String FQN,String particpant1,String particpant2,Model model) 
{
        LogCtl.setCmdLogging();
        String nsentity = "http://uim/entity/";
        String nsbinar = "http://uim/binaryconnection/";
        Resource resource = model.getResource(nsbinar+FQN);
        Resource resource1 = model.getResource(nsentity+particpant1);
        Resource resource2 = model.getResource(nsentity+particpant2);
        resource.addProperty(rparticipant1, resource1); 
        resource.addProperty(rparticpant2, resource2); 
}

public static void  inhrresourcecreate(int id,String name ,String FQN, String container,String disjoint,String complete,Model model1) 
{
  LogCtl.setCmdLogging();


         String nsinhr = "http://uim/inheritancerelationship/";
         Resource inhr = model1.createResource(nsinhr+FQN);     
         String sid =String.valueOf(id);  
         inhr.addProperty(plabel, "inheritancerelationship");  
         inhr.addProperty(pid, sid);  
         inhr.addProperty(pname, name); 
         inhr.addProperty(pFQN, FQN); 
         inhr.addProperty(pcontainer, container);
         inhr.addProperty(pdisjoint, disjoint); 
         inhr.addProperty(pcomplete, complete);

        
            }
public static void  inhpresourcecreate(int id,String name ,String FQN, String container,String participant,int inheritanceRelationship_id,Model model1) 
{
         LogCtl.setCmdLogging();
         String nsentity = "http://uim/inheritanceparticipation/";
         Resource inhp = model1.createResource(nsentity+FQN);   
         String sid =String.valueOf(id);  
         String sinheritanceRelationship_id =String.valueOf(inheritanceRelationship_id);
         inhp.addProperty(plabel, "inheritanceparticipation");  
         inhp.addProperty(pid, sid);  
         inhp.addProperty(pname, name); 
         inhp.addProperty(pFQN, FQN); 
         inhp.addProperty(pcontainer, container);
         inhp.addProperty(pparticipant, participant); 
         inhp.addProperty(pinheritanceRelationship_id, sinheritanceRelationship_id);

       
            }

public static void  inhrrelationshipsetting(String pFQN1,String PFQN2,String IP,Model model) 
{

	if(IP=="IP1")
	{
		    String nsinhr = "http://uim/inheritancerelationship/";
	        String nsinhp = "http://uim/inheritanceparticipation/";
	        Resource resource = model.getResource(nsinhr+pFQN1);
	        Resource resource1 = model.getResource(nsinhp+PFQN2);
	        resource.addProperty(inheritance, resource1); 
		
	}
	else
	{
		String nsinhr = "http://uim/inheritancerelationship/";
        String nsinhp = "http://uim/inheritanceparticipation/";
        Resource resource = model.getResource(nsinhp+pFQN1);
        Resource resource1 = model.getResource(nsinhr+PFQN2);
        resource.addProperty(inheritance, resource1); 
	}
       
}

public static void  inhprelationshipsetting(String pFQN1,String PFQN2,Model model) 
{
        LogCtl.setCmdLogging();      
        String nsinhp = "http://uim/inheritanceparticipation/";
        String nsentity = "http://uim/entity/";
        Resource resource = model.getResource(nsinhp+pFQN1);
        Resource resource1 = model.getResource(nsentity+PFQN2);
        resource.addProperty(rparticipant, resource1); 
}

//............................................................................................Classification set
public static void  classificationset(String particpant1,String particpant2,Model model) 
{
	String ns1 = "http://uim/entity/";
	String ns2 = "http://uim/entity/";
	Resource resource1 = model.getResource(ns1+particpant1);
	Resource resource2 = model.getResource(ns2+particpant2);
	resource1.addProperty(pdirecttype, particpant2);
	resource1.addProperty(directtype,resource2 ); 
	
}
public static void  conclassificationset(String particpant1,String particpant2,Model model) 
{
	String ns1 = "http://uim/binaryconnection/";
	String ns2 = "http://uim/binaryconnection/";
	Resource resource1 = model.getResource(ns1+particpant1);
	Resource resource2 = model.getResource(ns2+particpant2);
	resource1.addProperty(pdirecttype, particpant2);
	resource1.addProperty(directtype,resource2 ); 
	
}


public static void  Parentsetting(String particpant1,String particpant2,String label,Model model) 
{
LogCtl.setCmdLogging();
if (label=="level"){
	String ns2 = "http://uim/deepmodel/";
	String ns1 = "http://uim/level/";
	Resource resource1 = model.getResource(ns1+particpant1);
	Resource resource2 = model.getResource(ns2+particpant2);
	resource1.addProperty(rparent,resource2 ); 
}

if (label=="entity"){
	String ns2 = "http://uim/level/";
	String ns1 = "http://uim/entity/";
	Resource resource1 = model.getResource(ns1+particpant1);
	Resource resource2 = model.getResource(ns2+particpant2);
	resource1.addProperty(rparent,resource2 ); 
}
	
else if (label=="binaryconnection"){	
	String ns1 = "http://uim/binaryconnection/";
	String ns2 = "http://uim/level/";
	Resource resource1 = model.getResource(ns1+particpant1);
	Resource resource2 = model.getResource(ns2+particpant2);
	resource1.addProperty(rparent,resource2 ); 
}
else if (label=="inheritancerelationship"){
	String ns2 = "http://uim/level/";
	String ns1 = "http://uim/inheritancerelationship/";
	Resource resource1 = model.getResource(ns1+particpant1);
	Resource resource2 = model.getResource(ns2+particpant2);
	resource1.addProperty(rparent,resource2 ); 
}
else if (label=="attribute"){
	String ns2 = "http://uim/entity/";
	String ns1 = "http://uim/attribute/";
	Resource resource1 = model.getResource(ns1+particpant1);
	Resource resource2 = model.getResource(ns2+particpant2);
	resource1.addProperty(rparent,resource2 );  
}

else if (label=="binattribute"){
	String ns2 = "http://uim/binaryconnection/";
	String ns1 = "http://uim/attribute/";
	Resource resource1 = model.getResource(ns1+particpant1);
	Resource resource2 = model.getResource(ns2+particpant2);
	resource1.addProperty(rparent,resource2 );  
}
else if (label=="binmethod"){
	String ns2 = "http://uim/binaryconnection/";
	String ns1 = "http://uim/method/";
	Resource resource1 = model.getResource(ns1+particpant1);
	Resource resource2 = model.getResource(ns2+particpant2);
	resource1.addProperty(rparent,resource2 );  
}
}


public static void  writefile(Model model) {

try {
 
	
       PrintStream p= new PrintStream("D:/datardf/data/UIMrdf5.owl");

      model.write(new FileOutputStream(outputpath+"/"+dname+".owl"), "RDF/XML");

       System.out.print("Sucessfully");  
     p.close();
 }
 catch (FileNotFoundException e) { System.out.println(e); }
}

    }

