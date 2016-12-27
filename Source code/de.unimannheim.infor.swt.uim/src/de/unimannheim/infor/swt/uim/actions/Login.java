package de.unimannheim.infor.swt.uim.actions;


import java.io.*;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.Line2D;

import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Dimension;



public class Login implements ActionListener{
    JFrame frame=new JFrame("UIM service");
    JTabbedPane tabbedpane0=new JTabbedPane();
    JTabbedPane tabbedpane1=new JTabbedPane();
    JTabbedPane tabbedpane2=new JTabbedPane();
    JTabbedPane tabbedpane3=new JTabbedPane();
    
    Container contomysql=new Container();
    Container contoneo4j=new Container();
    Container contomongodb=new Container();
    Container contordf=new Container();

   
    Container confrommysql=new Container();
    Container confromneo4j=new Container();
    Container confrommongodb =new Container();
    Container confromrdf=new Container();
    JPanel   panel = new JPanel();
    

   
 //......................................................................................Mysql initidal japanel...
    JLabel tmlabelmelanee=new JLabel("Melanee choose");
    //JLabel label1=new JLabel("Melanee import");  
    JLabel labelmysqlhost=new JLabel("MySQLHost/Schema");
    JLabel labeluser=new JLabel("      User");
    JLabel labelpassword=new JLabel("  Password");  
    JLabel ConsoleLabel = new JLabel("Console");
    static JTextField tmtextmelanee=new JTextField();
    static JTextField text2=new JTextField();
    static JTextField textmysqlhost =new JTextField("localhost:3306/uimsql");
    static  JTextField textuser =new JTextField("root");
    static JTextField textpassword =new JTextField("12345678");
    static JTextArea textconsole = new JTextArea("");
    static JTextArea textconsole1 = new JTextArea("");
    static JTextArea textconsole2= new JTextArea("");
   
    JButton tmbuttonadd=new JButton("Add");
    JButton button2=new JButton("...");
    JButton buttontomysql=new JButton("Run");
  //....................................................................................................mysql........ 
    JLabel fmlabelmelanee=new JLabel("Melanee export");
    static JTextField fmtextmelanee=new JTextField();
    JButton fmbuttonbrowse=new JButton("Browse");
    JButton fmbuttonmysqlrun=new JButton("Run");
    JButton updatelmlbutton=new JButton("Update");
    
//....................................................................................................neo4j........
//    JLabel tnmelanee=new JLabel("Melanee import");  
    JLabel tnneo4jdbpath=new JLabel("Neo4j DB");
    static JTextField tntextneo4joutput=new JTextField();
    JButton tnbuttonbrowse=new JButton("Browse");
    JButton tnbuttonrun=new JButton("Run");  
    JButton fnbuttonrun=new JButton("Run");
    JButton neo4jupdatelmlbutton=new JButton("Update");
    
//....................................................................................................mongodb........   
    JLabel tmlabelhost=new JLabel("Host");
    static JTextField tmtexthost=new JTextField("localhost");
    JLabel tmlabelport=new JLabel("Port");
    static JTextField tmtextport=new JTextField("27017");
    JLabel tmlabeldb=new JLabel("MongoDB");
    static JTextField tmtextdb=new JTextField("UIMMongoDB");
    JLabel tmlabeluser=new JLabel("User");
    static JTextField tmtextuser=new JTextField("root");
    JLabel tmlabelpassword=new JLabel("Password");
    static JTextField tmtextpassword=new JTextField("12345678");
    
    JButton tmbuttonrun=new JButton("Run");
    JButton fmbuttonrun=new JButton("Run");
    JButton mongoupdatelmlbutton=new JButton("Update");
    
    
  //....................................................................................................rdf........   
    JLabel trlabelrdffoutput=new JLabel("Rdf output path");
    static JTextField trtextrdffoutput=new JTextField();
    JLabel frlabelrdffinput=new JLabel("Rdf imput path");
    static JTextField frtextrdffinput=new JTextField();
    JButton trbuttonbrowse=new JButton("Browse");
    JButton frbuttonbrowse=new JButton("Browse");
    JButton trbuttonrun=new JButton("Run");
    JButton frbuttonrun=new JButton("Run");
    JButton rdfupdatelmlbutton=new JButton("Update");
    
//....................................................................................................end........       
    JFileChooser jfc=new JFileChooser(); 
    Login(){
        jfc.setCurrentDirectory(new File("d:\\"));
       
        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));
        frame.setSize(480,500);
        frame.setContentPane(tabbedpane0);
  
        tabbedpane0.add("From Melanee to UIM",tabbedpane1);
        tabbedpane0.add("From UIM to Melanee",tabbedpane2);
 

        tabbedpane1.add("To MySQL",contomysql);
        tabbedpane1.add("To Neo4j",contoneo4j);
        tabbedpane1.add("To MongoDB",contomongodb);
        tabbedpane1.add("To RDF",contordf);
 

        tabbedpane2.add("From MySQL",confrommysql);
        tabbedpane2.add("FromNeo4j",confromneo4j);
        tabbedpane2.add("From MongoDB",confrommongodb);
        tabbedpane2.add("From RDF",confromrdf);
 
   
  
//  ...................................................................................From Melanee to MySQL...
           
             
        tmlabelmelanee.setBounds(10,10,120,20); 
        ConsoleLabel.setBounds(50,180, 90, 30);   
        labelmysqlhost.setBounds(10,70,120,20);     
        labeluser.setBounds(10,100,120,20);
        labelpassword.setBounds(10,130,120,20); 
           
       
        tmtextmelanee.setBounds(130,10,220,25);  
        textmysqlhost.setBounds(130,70,180,25); 
        textuser.setBounds(130,100,120,25); 
        textpassword.setBounds(130,130,120,25); 

        tmbuttonadd.setBounds(360,10,60,25); 
        button2.setBounds(360,40,50,20);  
        buttontomysql.setBounds(360,130,60,25); 
//
       
        
       
        JScrollPane scrollPane1=new JScrollPane(textconsole);    
        scrollPane1.setBounds(50, 200,380,120);      
        contomysql.add(scrollPane1);  
       
      
        contomysql.add(ConsoleLabel);
        contomysql.add(tmlabelmelanee);
        contomysql.add(labeluser);
        contomysql.add(labelpassword);
        contomysql.add(tmtextmelanee);
        contomysql.add(labelmysqlhost);
        contomysql.add(ConsoleLabel);
        contomysql.add(textmysqlhost);
        contomysql.add(textuser);
        contomysql.add(textpassword);
        contomysql.add(tmbuttonadd);
        contomysql.add(buttontomysql);
      //  contomysql.add(jfc);       
        contomysql.add(createHorizontalSeparator1());
        contomysql.add(createHorizontalSeparator2());
        
        tmbuttonadd.addActionListener(this); 
        button2.addActionListener(this); 
        buttontomysql.addActionListener(this);
        fmbuttonbrowse.addActionListener(this);
        fmbuttonmysqlrun.addActionListener(this);
        updatelmlbutton.addActionListener(this);
// ........................................................................................From Melanee to Neo4j.. 
  
        tnbuttonbrowse.addActionListener(this);
        tnbuttonrun.addActionListener(this);
        fnbuttonrun.addActionListener(this);
        neo4jupdatelmlbutton.addActionListener(this);
        
        
 //...............................................................................From Melanee to mongodb ......
        tmbuttonrun.addActionListener(this);
        fmbuttonrun.addActionListener(this);
        mongoupdatelmlbutton.addActionListener(this);
 //...............................................................................From Melanee to rdf ......
        trbuttonbrowse.addActionListener(this);
        trbuttonrun.addActionListener(this);
        frbuttonbrowse.addActionListener(this);
        frbuttonrun.addActionListener(this);
        rdfupdatelmlbutton.addActionListener(this);
       
        
//  ......................................................................................tabledPaneo listener
        tabbedpane0.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
           if(tabbedpane0.getSelectedComponent().equals(tabbedpane1))
                  {
        	  if( tabbedpane1.getSelectedComponent().equals(contomysql))
        	  {   
        	        tmlabelmelanee.setBounds(10,10,120,20); 
                     ConsoleLabel.setBounds(50,180, 90, 30);   
                    labelmysqlhost.setBounds(10,70,120,20);     
                    labeluser.setBounds(10,100,120,20);
                     labelpassword.setBounds(10,130,120,20); 
                  
                  
                    tmtextmelanee.setBounds(130,10,220,25);  
                    textmysqlhost.setBounds(130,70,180,25); 
                    textuser.setBounds(130,100,120,25); 
                     textpassword.setBounds(130,130,120,25); 
  
                    tmbuttonadd.setBounds(360,10,60,25); 
                    button2.setBounds(360,40,50,20);  
                    buttontomysql.setBounds(360,130,60,25); 
                    scrollPane1.setBounds(50, 200,380,120);  
        	        contomysql.add(ConsoleLabel);
                    contomysql.add(tmlabelmelanee);
                    contomysql.add(labeluser);
                    contomysql.add(labelpassword);
                    contomysql.add(tmtextmelanee);
                    contomysql.add(labelmysqlhost);
                    contomysql.add(ConsoleLabel);
                    contomysql.add(textmysqlhost);
                    contomysql.add(textuser);
                    contomysql.add(textpassword);
                    contomysql.add(tmbuttonadd);
                    contomysql.add(buttontomysql);  
                    contomysql.add(scrollPane1);  
                    contomysql.add(createHorizontalSeparator1());
                    contomysql.add(createHorizontalSeparator2());
        	  }
        	  //.................................................................update0331
        	  else if( tabbedpane1.getSelectedComponent().equals(contoneo4j))
        	  {    
        		  tmlabelmelanee.setBounds(10,10,120,20); 
             	 tmtextmelanee.setBounds(130,10,220,25); 
             	 tmbuttonadd.setBounds(360,10,60,25); 
        		  
        		  
        		  contoneo4j.add(tmlabelmelanee); 
        	         contoneo4j.add(tmtextmelanee);
        	         contoneo4j.add(tmbuttonadd);      
        	         tnneo4jdbpath.setBounds(10,70, 120, 25);
        	         tntextneo4joutput.setBounds(130,70, 220, 25);
        	         contoneo4j.add(tntextneo4joutput); 
                  contoneo4j.add(tnneo4jdbpath); 
                  tnbuttonbrowse.setBounds(360,70,80,25); 
                  contoneo4j.add(tnbuttonbrowse); 
                  tnbuttonrun.setBounds(360,110,60,25); 
                  contoneo4j.add(tnbuttonrun); 
                  
                  ConsoleLabel.setBounds(50,155, 90, 25);
                  scrollPane1.setBounds(50, 175,380,120);    
                  contoneo4j.add(createHorizontalSeparatortomongdb1());
                  contoneo4j.add(createHorizontalSeparatortoneo4j2());
                  
                  contoneo4j.add(ConsoleLabel);
                  contoneo4j.add(scrollPane1); 
        		  
        	  }
        	  
        	  else if( tabbedpane1.getSelectedComponent().equals(contomongodb))
        	  {  
        		  tmlabelmelanee.setBounds(10,10,120,20); 
              	 tmtextmelanee.setBounds(130,10,220,25); 
              	 tmbuttonadd.setBounds(360,10,60,25); 
        		  contomongodb.add(tmlabelmelanee); 
            	  contomongodb.add(tmtextmelanee);
            	  contomongodb.add(tmbuttonadd);                   	  
            	  tmlabelhost.setBounds(10,60,120,25); 
            	  tmtexthost.setBounds(130,60,120,25); 
            	  tmlabelport.setBounds(10,90,120,25); 
            	  tmtextport.setBounds(130,90,120,25); 
            	  
            	  tmlabeldb.setBounds(10,120,120,25); 
            	  tmtextdb.setBounds(130,120,120,25); 
            	  tmlabeluser.setBounds(10,150,120,25); 
            	  tmtextuser.setBounds(130,150,120,25); 
            	  tmlabelpassword.setBounds(10,180,120,25); 
            	  tmtextpassword.setBounds(130,180,120,25); 
            	  
            	  
          	      contomongodb.add(tmlabelhost); 
            	  contomongodb.add(tmtexthost); 
            	  contomongodb.add(tmlabelport); 
            	  contomongodb.add(tmtextport); 

            	  tmbuttonrun.setBounds(360,180,60,25);
            	  contomongodb.add(tmbuttonrun); 
            	  ConsoleLabel.setBounds(50,220, 90, 30);
                  scrollPane1.setBounds(50, 250,380,80); 
                  contomongodb.add(createHorizontalSeparatortomongdb1());
             	  contomongodb.add(createHorizontalSeparatortomongdb2());
                  contomongodb.add(ConsoleLabel);
                  contomongodb.add(scrollPane1); 
             	  
                  contomongodb.add(tmlabeldb); 
                  contomongodb.add(tmtextdb); 
                  contomongodb.add(tmlabeluser); 
                  contomongodb.add(tmtextuser); 
                  contomongodb.add(tmlabelpassword); 
                  contomongodb.add(tmtextpassword); 
        		  
        	  }
        	  else if( tabbedpane1.getSelectedComponent().equals(contordf))
        	  {
        		  tmlabelmelanee.setBounds(10,10,120,20); 
              	 tmtextmelanee.setBounds(130,10,220,25); 
              	 tmbuttonadd.setBounds(360,10,60,25);  
        		  contordf.add(tmlabelmelanee); 
          	     contordf.add(tmtextmelanee);
          	     contordf.add(tmbuttonadd);  
          	     trlabelrdffoutput.setBounds(10,70,120,25); 
          	     trtextrdffoutput.setBounds(130,70,120,25); 
          	     contordf.add(trlabelrdffoutput);
          	     contordf.add(trtextrdffoutput);              	     
          	     contordf.add(createHorizontalSeparatortordf1()); 
          	     contordf.add(createHorizontalSeparatortordf2()); 
          	    
                   trbuttonrun.setBounds(360,110,60,25);
                   trbuttonbrowse.setBounds(360,70,80,25);
                   contordf.add(trbuttonbrowse);
                   ConsoleLabel.setBounds(50,155, 90, 25);
                   scrollPane1.setBounds(50, 175,380,120); 
                   contordf.add(trbuttonrun);
                   contordf.add(ConsoleLabel);
                   contordf.add(scrollPane1); 
        		  
        	  }
        	
                 }
                else if (tabbedpane0.getSelectedComponent().equals(tabbedpane2))
        		 {  
                	if( tabbedpane2.getSelectedComponent().equals(confrommysql))
                	 { 
                		labelmysqlhost.setBounds(10,10,120,20);     
                        labeluser.setBounds(10,40,120,20);
                     labelpassword.setBounds(10,70,120,20);    
                     fmlabelmelanee.setBounds(10,130,120,20);  
                   
                   textmysqlhost.setBounds(130,10,180,25); 
                       textuser.setBounds(130,40,120,25); 
                   textpassword.setBounds(130,70,120,25); 
                   fmtextmelanee.setBounds(130,130,220,25);
                   
                   tmlabelmelanee.setBounds(10, 210,120,25); 
                   tmtextmelanee.setBounds(130,210,220,25);

                   ConsoleLabel.setBounds(50,280, 90, 30);   
                   scrollPane1.setBounds(50, 310,380,80);      
                 
                   fmbuttonbrowse.setBounds(360,130,80,25); 
                   fmbuttonmysqlrun.setBounds(360,160,60,25); 
                   tmbuttonadd.setBounds(360,210,60,25); 
                   updatelmlbutton.setBounds(360, 240,80,25); 
                  
                   
                   confrommysql.add(ConsoleLabel);
                   confrommysql.add(scrollPane1);
                   confrommysql.add(fmlabelmelanee);
                   confrommysql.add(labeluser);
                   confrommysql.add(labelpassword);
                   confrommysql.add(fmtextmelanee);
                   confrommysql.add(labelmysqlhost);
                   confrommysql.add(ConsoleLabel);
                   confrommysql.add(textmysqlhost);
                   confrommysql.add(textuser);
                   confrommysql.add(textpassword);
                   confrommysql.add(fmbuttonbrowse);
                   confrommysql.add(fmbuttonmysqlrun);  
                   confrommysql.add(fmbuttonmysqlrun);   
                   confrommysql.add(ConsoleLabel);
                   confrommysql.add(updatelmlbutton);  
                   confrommysql.add(tmbuttonadd);  
                   confrommysql.add(tmtextmelanee);  
                   confrommysql.add(tmlabelmelanee); 
                   confrommysql.add(createHorizontalSeparatorfrommysql1()); 
                   confrommysql.add(createHorizontalSeparatorfrommysql2()); 
                   confrommysql.add(createHorizontalSeparatorfrommysql3() );
                	 }
                	else   if( tabbedpane2.getSelectedComponent().equals(confromneo4j))
                 	 {
                		  fmlabelmelanee.setBounds(10,70,120,20);  
                    	  fmtextmelanee.setBounds(130,70,220,25);         
                         fmbuttonbrowse.setBounds(360,70,80,25); 
                         
                            tnneo4jdbpath.setBounds(10,10, 120, 25);
                        tntextneo4joutput.setBounds(130,10, 220, 25);
                           tnbuttonbrowse.setBounds(360,10,80,25);
                             
                              
                              tmlabelmelanee.setBounds(10,165,120,25);
                              tmtextmelanee.setBounds(130,165,220,25);
                              fnbuttonrun.setBounds(360,110,60,25);
                              tmbuttonadd.setBounds(360,165,80,25);
                              neo4jupdatelmlbutton.setBounds(360,200,80,25);
                           ConsoleLabel.setBounds(50,255, 90, 30);   
                           scrollPane1.setBounds(50, 275,380,120); 
                           
                           confromneo4j.add(fmlabelmelanee);
                           confromneo4j.add(fmtextmelanee);
                           confromneo4j.add(ConsoleLabel);
                           confromneo4j.add(scrollPane1);
                           confromneo4j.add(fmlabelmelanee);
                           confromneo4j.add(tnneo4jdbpath);
                           confromneo4j.add(tntextneo4joutput);
                           confromneo4j.add(fmbuttonbrowse);
                           confromneo4j.add(tnbuttonbrowse);
                           confromneo4j.add(fnbuttonrun);
                           confromneo4j.add(tmlabelmelanee);
                           confromneo4j.add(tmtextmelanee);
                           confromneo4j.add(tmbuttonadd);
                           confromneo4j.add(neo4jupdatelmlbutton);
                           confromneo4j.add(createHorizontalSeparatortomongdb1());
                           confromneo4j.add(createHorizontalSeparatorfrommneo4j2());
                           confromneo4j.add(createHorizontalSeparatorfrommneo4j3());
                 	 }
                           else   if( tabbedpane2.getSelectedComponent().equals(confrommongodb))
                        	 {
                        	  	  
                         	  tmlabelhost.setBounds(10,10,120,25); 
                         	  tmtexthost.setBounds(130,10,120,25); 
                         	  tmlabelport.setBounds(10,40,120,25); 
                         	  tmtextport.setBounds(130,40,120,25); 
                         	  
                         	  tmlabeldb.setBounds(10,70,120,25); 
                         	  tmtextdb.setBounds(130,70,120,25); 
                         	  tmlabeluser.setBounds(10,100,120,25); 
                         	  tmtextuser.setBounds(130,100,120,25); 
                         tmlabelpassword.setBounds(10,130,120,25); 
                          tmtextpassword.setBounds(130,130,120,25); 
                         	 
                         	  fmlabelmelanee.setBounds(10,180,120,20); 
                         	  fmtextmelanee.setBounds(130,180,220,25); 
                         	  ConsoleLabel.setBounds(50,320, 90, 30);   
                               scrollPane1.setBounds(50, 340,380,55);   
                             
                               
                               fmbuttonbrowse.setBounds(360,180,80,25); 
                               fmbuttonrun.setBounds(360,210,60,25);
                               
                               tmlabelmelanee.setBounds(10,255,120,25);
                               tmtextmelanee.setBounds(130,255,220,25);
                               
                               tmbuttonadd.setBounds(360,255,60,25);
                               
                               mongoupdatelmlbutton.setBounds(360,285,80,25);
                               
                             
                               
                         	  confrommongodb.add(fmlabelmelanee);
                         	  confrommongodb.add(fmtextmelanee);
                         	  confrommongodb.add(ConsoleLabel);
                         	  confrommongodb.add(scrollPane1);
                         	  
                         	  confrommongodb.add(tmlabelhost); 
                         	  confrommongodb.add(tmtexthost); 
                         	  confrommongodb.add(tmlabelport); 
                         	  confrommongodb.add(tmtextport); 
                         	  confrommongodb.add(fmbuttonbrowse); 
                         	  confrommongodb.add(fmbuttonrun); 
                         	  confrommongodb.add(tmlabeldb); 
                         	  confrommongodb.add(tmtextdb); 
                         	  confrommongodb.add(tmlabeluser); 
                         	  confrommongodb.add(tmtextuser); 
                         	  confrommongodb.add(tmlabelpassword); 
                         	  confrommongodb.add(tmtextpassword); 
                         	  
                         	  confrommongodb.add(tmlabelmelanee); 
                         	  confrommongodb.add(tmtextmelanee); 
                         	  confrommongodb.add(tmbuttonadd);
                         	  confrommongodb.add(mongoupdatelmlbutton); 
                         	  
                         	  
                         	  
                         	  confrommongodb.add(createHorizontalSeparatorfrommongodb1()); 
                         	  confrommongodb.add(createHorizontalSeparatorfrommongodb2() ); 
                         	  confrommongodb.add(createHorizontalSeparatorfrommongodb3() );
                        	 }
                           else   if( tabbedpane2.getSelectedComponent().equals(confromrdf))
                        	 {

             
                        	     fmlabelmelanee.setBounds(10,70,120,20);  
                         	     fmtextmelanee.setBounds(130,70,220,25);         
                                 fmbuttonbrowse.setBounds(360,70,80,25); 
                                 
                                 frlabelrdffinput.setBounds(10,10, 120, 25);
                                 frtextrdffinput.setBounds(130,10, 220, 25);
                                 frbuttonbrowse.setBounds(360,10,80,25);
                                 frbuttonrun.setBounds(360,110,60,25);
                                 
                                 
                                 tmlabelmelanee.setBounds(10,170,120,25);
                                 tmtextmelanee.setBounds(130,170,220,25);
                                 
                                 tmbuttonadd.setBounds(360,170,60,25);
                                 rdfupdatelmlbutton.setBounds(360,200,80,25);
                                 
                                 confromrdf.add(fmlabelmelanee);
                                 confromrdf.add(fmtextmelanee);
                                 confromrdf.add(fmbuttonbrowse);
                                 confromrdf.add(frbuttonbrowse);
                                 

                                 confromrdf.add(frtextrdffinput);
                                 confromrdf.add(frlabelrdffinput);
                               
                                 
                                 confromrdf.add(tmlabelmelanee);
                                 confromrdf.add(tmtextmelanee);
                                 
                                 confromrdf.add(frbuttonrun);
                                 confromrdf.add(tmbuttonadd);
                                 confromrdf.add(rdfupdatelmlbutton);
                                 
                                 ConsoleLabel.setBounds(50,245, 90, 30);   
                                 scrollPane1.setBounds(50, 265,380,120);
                                 confromrdf.add(ConsoleLabel);
                                 confromrdf.add(scrollPane1);
                                 
                                 
                                 confromrdf.add(createHorizontalSeparatortomongdb1());
                                 confromrdf.add(createHorizontalSeparatortomongneo4j2());
                                 confromrdf.add(createHorizontalSeparatorfromrdf3());
                        	 }
                  }
                
                  
                 
            }
            
        });
        //.................................................................update0331 end.........  
  //..................................................................................... tabbedPane1 Listener.....    
        
        tabbedpane1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
           if(tabbedpane1.getSelectedComponent().equals(contomysql))
        	   
                  {
        

         
        	   tmlabelmelanee.setBounds(10,10,120,20); 
               ConsoleLabel.setBounds(50,180, 90, 30);   
              labelmysqlhost.setBounds(10,70,120,20);     
              labeluser.setBounds(10,100,120,20);
               labelpassword.setBounds(10,130,120,20); 
            
            
              tmtextmelanee.setBounds(130,10,220,25);  
              textmysqlhost.setBounds(130,70,180,25); 
              textuser.setBounds(130,100,120,25); 
               textpassword.setBounds(130,130,120,25); 

              tmbuttonadd.setBounds(360,10,60,25); 
              button2.setBounds(360,40,50,20);  
              buttontomysql.setBounds(360,130,60,25); 
              scrollPane1.setBounds(50, 200,380,150);  
  	        contomysql.add(ConsoleLabel);
              contomysql.add(tmlabelmelanee);
              contomysql.add(labeluser);
              contomysql.add(labelpassword);
              contomysql.add(tmtextmelanee);
              contomysql.add(labelmysqlhost);
              contomysql.add(ConsoleLabel);
              contomysql.add(textmysqlhost);
              contomysql.add(textuser);
              contomysql.add(textpassword);
              contomysql.add(tmbuttonadd);
              contomysql.add(buttontomysql);  
              contomysql.add(scrollPane1);  
              contomysql.add(createHorizontalSeparator1());
              contomysql.add(createHorizontalSeparator2());
         
                 }
                else if (tabbedpane1.getSelectedComponent().equals(contoneo4j))
        		 {       	     
                	 tmlabelmelanee.setBounds(10,10,120,20); 
                	 tmtextmelanee.setBounds(130,10,220,25); 
                	 tmbuttonadd.setBounds(360,10,60,25); 
                	
                	contoneo4j.add(tmlabelmelanee); 
           	         contoneo4j.add(tmtextmelanee);
           	         contoneo4j.add(tmbuttonadd);      
           	         tnneo4jdbpath.setBounds(10,70, 120, 25);
           	         tntextneo4joutput.setBounds(130,70, 220, 25);
           	         contoneo4j.add(tntextneo4joutput); 
                     contoneo4j.add(tnneo4jdbpath); 
                     tnbuttonbrowse.setBounds(360,70,80,25); 
                     contoneo4j.add(tnbuttonbrowse); 
                     tnbuttonrun.setBounds(360,110,60,25); 
                     contoneo4j.add(tnbuttonrun); 
                     
                     ConsoleLabel.setBounds(50,155, 90, 25);
                     scrollPane1.setBounds(50, 175,380,150);    
                     contoneo4j.add(createHorizontalSeparatortomongdb1());
                     contoneo4j.add(createHorizontalSeparatortoneo4j2());
                     
                     contoneo4j.add(ConsoleLabel);
                     contoneo4j.add(scrollPane1); 
                	  
                  }
                  
                  else  if(tabbedpane1.getSelectedComponent().equals(contomongodb))
                  {
                	  tmlabelmelanee.setBounds(10,10,120,20); 
                 	 tmtextmelanee.setBounds(130,10,220,25); 
                 	 tmbuttonadd.setBounds(360,10,60,25); 
                	  contomongodb.add(tmlabelmelanee); 
                	  contomongodb.add(tmtextmelanee);
                	  contomongodb.add(tmbuttonadd);                   	  
                	  tmlabelhost.setBounds(10,60,120,25); 
                	  tmtexthost.setBounds(130,60,120,25); 
                	  tmlabelport.setBounds(10,90,120,25); 
                	  tmtextport.setBounds(130,90,120,25); 
                	  
                	  tmlabeldb.setBounds(10,120,120,25); 
                	  tmtextdb.setBounds(130,120,120,25); 
                	  tmlabeluser.setBounds(10,150,120,25); 
                	  tmtextuser.setBounds(130,150,120,25); 
                	  tmlabelpassword.setBounds(10,180,120,25); 
                	  tmtextpassword.setBounds(130,180,120,25); 
                	  
                	  
              	      contomongodb.add(tmlabelhost); 
                	  contomongodb.add(tmtexthost); 
                	  contomongodb.add(tmlabelport); 
                	  contomongodb.add(tmtextport); 

                	  tmbuttonrun.setBounds(360,180,60,25);
                	  contomongodb.add(tmbuttonrun); 
                	  ConsoleLabel.setBounds(50,220, 90, 30);
                      scrollPane1.setBounds(50, 250,380,80); 
                      contomongodb.add(createHorizontalSeparatortomongdb1());
                 	  contomongodb.add(createHorizontalSeparatortomongdb2());
                      contomongodb.add(ConsoleLabel);
                      contomongodb.add(scrollPane1); 
                 	  
                      contomongodb.add(tmlabeldb); 
                      contomongodb.add(tmtextdb); 
                      contomongodb.add(tmlabeluser); 
                      contomongodb.add(tmtextuser); 
                      contomongodb.add(tmlabelpassword); 
                      contomongodb.add(tmtextpassword); 
                    	 
                     }
               else  if(tabbedpane1.getSelectedComponent().equals(contordf)){
            	     
            	     tmlabelmelanee.setBounds(10,10,120,20); 
               	    tmtextmelanee.setBounds(130,10,220,25); 
               	    tmbuttonadd.setBounds(360,10,60,25); 
            	     contordf.add(tmlabelmelanee); 
            	     contordf.add(tmtextmelanee);
            	     contordf.add(tmbuttonadd);  
            	     trlabelrdffoutput.setBounds(10,70,120,25); 
            	     trtextrdffoutput.setBounds(130,70,120,25); 
            	     contordf.add(trlabelrdffoutput);
            	     contordf.add(trtextrdffoutput);              	     
            	     contordf.add(createHorizontalSeparatortordf1()); 
            	     contordf.add(createHorizontalSeparatortordf2()); 
            	    
                     trbuttonrun.setBounds(360,110,60,25);
                     trbuttonbrowse.setBounds(360,70,80,25);
                     contordf.add(trbuttonbrowse);
                     ConsoleLabel.setBounds(50,155, 90, 25);
                     scrollPane1.setBounds(50, 175,380,120); 
                     contordf.add(trbuttonrun);
                     contordf.add(ConsoleLabel);
                     contordf.add(scrollPane1); 
             }
            }
            
        });
   
 //.....................................................................................tabbedPane2 Listener(reverse)...........
        tabbedpane2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
           if(tabbedpane2.getSelectedComponent().equals(confrommysql))
                  {
        	   labelmysqlhost.setBounds(10,10,120,20);     
               labeluser.setBounds(10,40,120,20);
            labelpassword.setBounds(10,70,120,20);    
            fmlabelmelanee.setBounds(10,130,120,20);  
          
          textmysqlhost.setBounds(130,10,180,25); 
              textuser.setBounds(130,40,120,25); 
          textpassword.setBounds(130,70,120,25); 
          fmtextmelanee.setBounds(130,130,220,25);
          
          tmlabelmelanee.setBounds(10, 210,120,25); 
          tmtextmelanee.setBounds(130,210,220,25);

          ConsoleLabel.setBounds(50,280, 90, 30);   
          scrollPane1.setBounds(50, 310,380,80);      
        
          fmbuttonbrowse.setBounds(360,130,80,25); 
          fmbuttonmysqlrun.setBounds(360,160,60,25); 
          tmbuttonadd.setBounds(360,210,60,25); 
          updatelmlbutton.setBounds(360, 240,80,25); 
         
          
          confrommysql.add(ConsoleLabel);
          confrommysql.add(scrollPane1);
          confrommysql.add(fmlabelmelanee);
          confrommysql.add(labeluser);
          confrommysql.add(labelpassword);
          confrommysql.add(fmtextmelanee);
          confrommysql.add(labelmysqlhost);
          confrommysql.add(ConsoleLabel);
          confrommysql.add(textmysqlhost);
          confrommysql.add(textuser);
          confrommysql.add(textpassword);
          confrommysql.add(fmbuttonbrowse);
          confrommysql.add(fmbuttonmysqlrun);  
          confrommysql.add(fmbuttonmysqlrun);   
          confrommysql.add(ConsoleLabel);
          confrommysql.add(updatelmlbutton);  
          confrommysql.add(tmbuttonadd);  
          confrommysql.add(tmtextmelanee);  
          confrommysql.add(tmlabelmelanee); 
          confrommysql.add(createHorizontalSeparatorfrommysql1()); 
          confrommysql.add(createHorizontalSeparatorfrommysql2()); 
          confrommysql.add(createHorizontalSeparatorfrommysql3() );
            
                    
                     
                 }
                else if (tabbedpane2.getSelectedComponent().equals(confromneo4j))
        		 {       	     

                	  fmlabelmelanee.setBounds(10,70,120,20);  
                	  fmtextmelanee.setBounds(130,70,220,25);         
                     fmbuttonbrowse.setBounds(360,70,80,25); 
                     
                        tnneo4jdbpath.setBounds(10,10, 120, 25);
                    tntextneo4joutput.setBounds(130,10, 220, 25);
                       tnbuttonbrowse.setBounds(360,10,80,25);
                         
                          
                          tmlabelmelanee.setBounds(10,165,120,25);
                          tmtextmelanee.setBounds(130,165,220,25);
                          fnbuttonrun.setBounds(360,110,60,25);
                          tmbuttonadd.setBounds(360,165,80,25);
                          neo4jupdatelmlbutton.setBounds(360,200,80,25);
                       ConsoleLabel.setBounds(50,255, 90, 30);   
                       scrollPane1.setBounds(50, 275,380,120); 
                       
                       confromneo4j.add(fmlabelmelanee);
                       confromneo4j.add(fmtextmelanee);
                       confromneo4j.add(ConsoleLabel);
                       confromneo4j.add(scrollPane1);
                       confromneo4j.add(fmlabelmelanee);
                       confromneo4j.add(tnneo4jdbpath);
                       confromneo4j.add(tntextneo4joutput);
                       confromneo4j.add(fmbuttonbrowse);
                       confromneo4j.add(tnbuttonbrowse);
                       confromneo4j.add(fnbuttonrun);
                       confromneo4j.add(tmlabelmelanee);
                       confromneo4j.add(tmtextmelanee);
                       confromneo4j.add(tmbuttonadd);
                       confromneo4j.add(neo4jupdatelmlbutton);
                       confromneo4j.add(createHorizontalSeparatortomongdb1());
                       confromneo4j.add(createHorizontalSeparatorfrommneo4j2());
                       confromneo4j.add(createHorizontalSeparatorfrommneo4j3());
                }
                  
                  else  if(tabbedpane2.getSelectedComponent().equals(confrommongodb))
                  {
                	
                	  
                	  tmlabelhost.setBounds(10,10,120,25); 
                	  tmtexthost.setBounds(130,10,120,25); 
                	  tmlabelport.setBounds(10,40,120,25); 
                	  tmtextport.setBounds(130,40,120,25); 
                	  
                	  tmlabeldb.setBounds(10,70,120,25); 
                	  tmtextdb.setBounds(130,70,120,25); 
                	  tmlabeluser.setBounds(10,100,120,25); 
                	  tmtextuser.setBounds(130,100,120,25); 
                tmlabelpassword.setBounds(10,130,120,25); 
                 tmtextpassword.setBounds(130,130,120,25); 
                	 
                	  fmlabelmelanee.setBounds(10,180,120,20); 
                	  fmtextmelanee.setBounds(130,180,220,25); 
                	  ConsoleLabel.setBounds(50,320, 90, 30);   
                      scrollPane1.setBounds(50, 340,380,55);   
                    
                      
                      fmbuttonbrowse.setBounds(360,180,80,25); 
                      fmbuttonrun.setBounds(360,210,60,25);
                      
                      tmlabelmelanee.setBounds(10,255,120,25);
                      tmtextmelanee.setBounds(130,255,220,25);
                      
                      tmbuttonadd.setBounds(360,255,60,25);
                      
                      mongoupdatelmlbutton.setBounds(360,285,80,25);
                      
                    
                      
                	  confrommongodb.add(fmlabelmelanee);
                	  confrommongodb.add(fmtextmelanee);
                	  confrommongodb.add(ConsoleLabel);
                	  confrommongodb.add(scrollPane1);
                	  
                	  confrommongodb.add(tmlabelhost); 
                	  confrommongodb.add(tmtexthost); 
                	  confrommongodb.add(tmlabelport); 
                	  confrommongodb.add(tmtextport); 
                	  confrommongodb.add(fmbuttonbrowse); 
                	  confrommongodb.add(fmbuttonrun); 
                	  confrommongodb.add(tmlabeldb); 
                	  confrommongodb.add(tmtextdb); 
                	  confrommongodb.add(tmlabeluser); 
                	  confrommongodb.add(tmtextuser); 
                	  confrommongodb.add(tmlabelpassword); 
                	  confrommongodb.add(tmtextpassword); 
                	  
                	  confrommongodb.add(tmlabelmelanee); 
                	  confrommongodb.add(tmtextmelanee); 
                	  confrommongodb.add(tmbuttonadd);
                	  confrommongodb.add(mongoupdatelmlbutton); 
                	  
                	  
                	  
                	  confrommongodb.add(createHorizontalSeparatorfrommongodb1()); 
                	  confrommongodb.add(createHorizontalSeparatorfrommongodb2() ); 
                	  confrommongodb.add(createHorizontalSeparatorfrommongodb3() );
                    	 
                     }
               else  if(tabbedpane2.getSelectedComponent().equals(confromrdf))
               {
            	   
            	   
            	     fmlabelmelanee.setBounds(10,70,120,20);  
             	     fmtextmelanee.setBounds(130,70,220,25);         
                     fmbuttonbrowse.setBounds(360,70,80,25); 
                     
                     frlabelrdffinput.setBounds(10,10, 120, 25);
                     frtextrdffinput.setBounds(130,10, 220, 25);
                     frbuttonbrowse.setBounds(360,10,80,25);
                     frbuttonrun.setBounds(360,110,60,25);
                     
                     
                     tmlabelmelanee.setBounds(10,170,120,25);
                     tmtextmelanee.setBounds(130,170,220,25);
                     
                     tmbuttonadd.setBounds(360,170,60,25);
                     rdfupdatelmlbutton.setBounds(360,200,80,25);
                     
                     confromrdf.add(fmlabelmelanee);
                     confromrdf.add(fmtextmelanee);
                     confromrdf.add(fmbuttonbrowse);
                     confromrdf.add(frbuttonbrowse);
                     

                     confromrdf.add(frtextrdffinput);
                     confromrdf.add(frlabelrdffinput);
                   
                     
                     confromrdf.add(tmlabelmelanee);
                     confromrdf.add(tmtextmelanee);
                     
                     confromrdf.add(frbuttonrun);
                     confromrdf.add(tmbuttonadd);
                     confromrdf.add(rdfupdatelmlbutton);
                     
                     ConsoleLabel.setBounds(50,245, 90, 30);   
                     scrollPane1.setBounds(50, 265,380,120);
                     confromrdf.add(ConsoleLabel);
                     confromrdf.add(scrollPane1);
                     
                     
                     confromrdf.add(createHorizontalSeparatortomongdb1());
                     confromrdf.add(createHorizontalSeparatortomongneo4j2());
                     confromrdf.add(createHorizontalSeparatorfromrdf3());
                     
             }
            }
            
        });
 //...................................................................................tabbedPane2 Listener end..............       
        //biankuang
       //添加布局1
//        myPanel.setComponentPopupMenu(popup);
       // tabPane2.add("dddd",myPanel);
        
        //biankuang
        
        frame.setVisible(true);//窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使能关闭窗口，结束程序

        
    }
    

  
//    
 
    
    
    
    static JComponent createHorizontalSeparator1() {
        JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
      //  x.setPreferredSize(new Dimension(100,10));
        x.setBounds(10, 50, 400, 100);
    
      
        return x;
    }
    
    static JComponent createHorizontalSeparator2() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
        x.setBounds(10, 170, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatortoneo4j1() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 150, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatortoneo4j2() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 150, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatortomongneo4j2() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
        x.setBounds(10, 155, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatortomongdb1() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
        x.setBounds(10, 50, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatortomongdb2() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
        x.setBounds(10, 210, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatortordf1() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 50, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatortordf2() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 150, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatorfrommysql1() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 110, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatorfrommysql2() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 195, 400, 100);
        return x;
    }
    
    
    static JComponent createHorizontalSeparatorfrommysql3() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);       
    	x.setBounds(10, 275, 400, 100);
        return x;
    }
    static JComponent createHorizontalSeparatorfrommongodb1() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 165, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatorfrommongodb2() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 240, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatorfrommongodb3() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 320, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatorfrommneo4j2() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 150, 400, 100);
        return x;
    }
    static JComponent createHorizontalSeparatorfrommneo4j3() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 240, 400, 100);
        return x;
    }
    
    static JComponent createHorizontalSeparatorfromrdf3() {
    	JSeparator x = new JSeparator(SwingConstants.HORIZONTAL);
       
    	x.setBounds(10, 240, 400, 100);
        return x;
    }
    // ....................................................The following code is used to get and return Textfield  

    
    
    public static String  j1textmelanee(){
    	  
    	return  tmtextmelanee.getText();
    }
    public static String  textmysqlhostx(){
  
    	return  textmysqlhost.getText();
    }
    
    public static String  textuserx(){
    	  
    	return  textuser.getText();
    }
    
    public static String  textpasswordx(){
  	  
    	return  textpassword.getText();
    }
    
    public static String  tntextneo4joutputx(){
    	  
    	return  tntextneo4joutput.getText();
    }
    
    public static String  tmtexthostx(){
  	  
    	return  tmtexthost.getText();
    }
    
    public static String  tmtextportx(){
    	  
    	return  tmtextport.getText();
    }
    public static String  trtextrdffoutputx(){
  	  
    	return  trtextrdffoutput.getText();
    }
    
    public static String  fmtextmelaneex(){
    	  
    	return  fmtextmelanee.getText();
    }
    
    public static String  tmtextdbx(){
    	  
    	return  tmtextdb.getText();
    }
    
    public static String  tmtextuserx(){
  	  
    	return  tmtextuser.getText();
    }
    public static String  tmtextpasswordx(){
  	  
    	return  tmtextpassword.getText();
    }
    public static String  frtextrdffinputx(){
    	  
    	return  frtextrdffinput.getText();
    }
    
    //Textfield get end 
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(tmbuttonadd)){
            jfc.setFileSelectionMode(0);
            int state=jfc.showOpenDialog(null);
            if(state==1){
                return;
            }
            else{
                File f=jfc.getSelectedFile();
           
                tmtextmelanee.setText(f.getAbsolutePath().replaceAll("\\\\", "/"));
                     
            }
        }
        else  if(e.getSource().equals(tnbuttonbrowse)){
            jfc.setFileSelectionMode(1); 
            int state=jfc.showOpenDialog(null); 
            if(state==1){
                return; 
            }
            else{
                File f=jfc.getSelectedFile(); 
                tntextneo4joutput.setText(f.getAbsolutePath().replaceAll("\\\\", "/"));
            }
        }
        else  if(e.getSource().equals(trbuttonbrowse)){
            jfc.setFileSelectionMode(1); 
            int state=jfc.showOpenDialog(null); 
            if(state==1){
                return; 
            }
            else{
                File f=jfc.getSelectedFile(); 
                trtextrdffoutput.setText(f.getAbsolutePath().replaceAll("\\\\", "/"));
            }
        }
        
        
        else  if(e.getSource().equals(fmbuttonbrowse)){
            jfc.setFileSelectionMode(1); 
            int state=jfc.showOpenDialog(null); 
            if(state==1){
                return;
            }
            else{
                File f=jfc.getSelectedFile(); 
                fmtextmelanee.setText(f.getAbsolutePath().replaceAll("\\\\", "/"));
            }
                       
        }
        
        else  if(e.getSource().equals(frbuttonbrowse)){
            jfc.setFileSelectionMode(0); 
            int state=jfc.showOpenDialog(null); 
            if(state==1){
                return; 
            }
            else{
                File f=jfc.getSelectedFile(); 
                frtextrdffinput.setText(f.getAbsolutePath().replaceAll("\\\\", "/"));
            }
                       
        }
        
        
        else if(e.getSource().equals(buttontomysql))
           { 
        	 
        	 MelaneeRead.readdeepmodel("mysql");
             System.out.println("Melanee to MySQL program run completed"); 
            }
        else if(e.getSource().equals(tnbuttonrun))
            {
     	   
     	    MelaneeRead.readdeepmodel("neo4j");
       	    System.out.println("Melanee to Neo4j program run completed"); 
             }
          else if(e.getSource().equals(tmbuttonrun)){

          	
          	MelaneeRead.readdeepmodel("mongodb");
          	 System.out.println("Melanee to MongoDB program run completed"); 
           }
          else if(e.getSource().equals(trbuttonrun)){

           
           	MelaneeRead.readdeepmodel("rdf");
           	 System.out.println("Melanee to RDF program run completed"); 
            }
          else if(e.getSource().equals(fmbuttonmysqlrun)){

        	  MySQLToMelanee formmysql=new MySQLToMelanee();
        	  formmysql.readdeepmodel("deepmodel");
        	  System.out.println("MySQLToMelanee program run completed"); 
             }
        
          else if(e.getSource().equals(fnbuttonrun)){

        	  Neo4jToMelanee nex=new Neo4jToMelanee();
        	  nex.GetDeepmodel();
        	  System.out.println("Neo4jToMelanee program run completed"); 
             }
          else if(e.getSource().equals(fmbuttonrun)){

        	  MongoDBToMelanee mgo=new MongoDBToMelanee();
        	  mgo.ReadMongoDB();
        	  System.out.println("MongoDBToMelanee program run completed"); 
             }
          else if(e.getSource().equals(frbuttonrun)){

        	  RDFToMelanee rdfm=new RDFToMelanee();
        	  rdfm.readdeepmodel();
        	  System.out.println("RDFToMelanee program run completed"); 
        	 
             }
          else if(e.getSource().equals(updatelmlbutton)){

        	  MySQLUpdateLML lmlupdate=new MySQLUpdateLML();
        	  lmlupdate.melaneemodelupdate();
        	 MelaneeRead melaneeread=new MelaneeRead();
         	 melaneeread.readdeepmodel("mysql");
         	 System.out.println("MySQLUpdateMelanee program run completed"); 
        	  
             }
        
          else if(e.getSource().equals(neo4jupdatelmlbutton)){

        	  Neo4jUpdateMelanee.melaneemodelupdate();
        	  MelaneeRead.readdeepmodel("neo4j");   
        	  System.out.println("Neo4jUpdateMelanee program run completed"); 
        	  
             }
        
          else if(e.getSource().equals(mongoupdatelmlbutton)){

        	  MongoDBUpdateMelanee.melaneemodelupdate();
        	  MelaneeRead.readdeepmodel("mongodb");
        	  System.out.println("MongoDBUpdateMelanee program run completed"); 
        	  
          }
          else if(e.getSource().equals(rdfupdatelmlbutton)){

        	  RDFUpdateMelanee.melaneemodelupdate();
        	  MelaneeRead.readdeepmodel("rdf");
        	  System.out.println("RDFUpdateMelanee program run completed"); 
        	  
        	  
          }
}
    
  
    
    
    public static void main(String[] args) {
        new Login();
        consoleoutput();

      
        
    }
    public static void begin(){
  //  	 new Login();
         consoleoutput();
    }
    public static void consoleoutput(){

    	OutputStream textAreaStream = new OutputStream() {
    	public void write(int b) throws IOException {
    		textconsole.append(String.valueOf((char)b));
    	}

    	  public void write(byte b[]) throws IOException {
    		  textconsole.append(new String(b));
    	  }
    	 
    	  public void write(byte b[], int off, int len) throws IOException {
    		  textconsole.append(new String(b, off, len));
    	  }
    	}; 	
    	PrintStream myOut = new PrintStream(textAreaStream);
    	System.setOut(myOut);
    	System.setErr(myOut);

    	try {

    	} catch (Exception e) {
    	e.printStackTrace();
    	}
    	}
    


}
