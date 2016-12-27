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
import java.io.File;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;  
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

//import com.mysql.jdbc.PreparedStatement;

public class MySQLWrite {
	
	static String mysqlhost=Login.textmysqlhostx();
	static String mysqluser=Login.textuserx();
	static String mysqlpassword=Login.textpasswordx();
	static String sqldatabase="jdbc:mysql://"+mysqlhost;
	
	   public static void main(String[] args)
	   {  
		   delatetable() ; 
//		   enitityinsert("1","FQN","name","container","potincy","directtype","entity");

	   }
//	public static void read(String table)  
//    {  
//     
//    	try  
//        {  
//            Statement stmt=null;  
//            ResultSet res=null;  
//            Class.forName("com.mysql.jdbc.Driver");  
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/melaneereverse","root","12345678");   
//            stmt = (Statement)conn.createStatement();  
//            res= stmt.executeQuery("SELECT * from  entity ");  
//
//
//            // 增加数据
//            Statement stm = (Statement) conn.createStatement();// 提交查巡
//            String sql = "select * from entity; ";
//            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')+";
//            int n = stm.executeUpdate(sql); // 增加数据 
//            if (n > 0) {
////            JOptionPane.showMessageDialog(null, "成功");
//            
//            } else {
////            JOptionPane.showMessageDialog(null, "失败");
//            }
//            //增加数据结束
//
//        while (res.next())   
//        {   
//    
//        }   
//    }  
//  
//    catch(Exception ex)  
//    {  
//        ex.printStackTrace();  
//    }  
//} 
	   
//package insert 
public static void Packageinsert (String id,String FQN,String name, String container,String table  )
	 	    {     
	 	    	try  
	 	        {  
	 	            Statement stmt=null;  
	 	            ResultSet res=null;  
	 	            Class.forName("com.mysql.jdbc.Driver");  
	 	            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
	 	            stmt = (Statement)conn.createStatement();  
	 	            res= stmt.executeQuery("SELECT * from  package ");  


	 	            // 增加数据
	 	            Statement stm = (Statement) conn.createStatement();// 提交查巡
	 	            String sql = "select * from package";
	 	            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//	 	            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";           
	 	            int n = stm.executeUpdate("insert into "+table+"(id,FQN,name, container) values ("+"'"+id+"'"+","+"'"+FQN+"'"+","+"'"+name+"'"+","+"'"+container+"'"+")"); // 增加数据 
	 	            if (n > 0) {
//	 	            JOptionPane.showMessageDialog(null, "成功");
	 	   
	 	            
	 	            } else {
//	 	            JOptionPane.showMessageDialog(null, "失败");
	 	            }
	 	            //增加数据结束

	 	        while (res.next())   
	 	        {   
	 	            
	 	        }   
	 	    }  
	 	  
	 	    catch(Exception ex)  
	 	    {  
	 	        ex.printStackTrace();  
	 	    }  
	 	} 
//package insert end	   
	   
//deepmodel insert 
	public static void Deepmodelinsert (int id,int p_id,String FQN,String name, String container,String table )
    {  
	 mysqlhost=Login.textmysqlhostx();
		  mysqluser=Login.textuserx();
	  mysqlpassword=Login.textpasswordx();
	  sqldatabase="jdbc:mysql://"+mysqlhost;
     
    	try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);      
            stmt = (Statement)conn.createStatement();  
            res= stmt.executeQuery("SELECT * from  deepmodel ");  


            // 增加数据
            Statement stm = (Statement) conn.createStatement();// 提交查巡
            String sql = "select * from deepmodel";
            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";
           
            int n = stm.executeUpdate("insert into "+table+"(id,p_id,FQN,name, container) values ("+"'"+id+"'"+","+"'"+p_id+"'"+","+"'"+FQN+"'"+","+"'"+name+"'"+","+"'"+container+"'"+")"); // 增加数据 
            if (n > 0) {
//            JOptionPane.showMessageDialog(null, "成功");
          
            
            } else {
//            JOptionPane.showMessageDialog(null, "失败");
            }
            //增加数据结束

        while (res.next())   
        {   
         
        }   
    }  
  
    catch(Exception ex)  
    {  
        ex.printStackTrace();  
    }  
} 
//deepmodel insert end
	
//level insert 
public static void Levelinsert (int id,String FQN,String name, String container,int number,String table )
	    {  
	     
	    	try  
	        {  
	            Statement stmt=null;  
	            ResultSet res=null;  
	            Class.forName("com.mysql.jdbc.Driver");  
	            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
	            stmt = (Statement)conn.createStatement();  
	            res= stmt.executeQuery("SELECT * from  level ");  


	            // 增加数据
	            Statement stm = (Statement) conn.createStatement();// 提交查巡
	            String sql = "select * from level";
	            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//	            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";
	           
	            int n = stm.executeUpdate("insert into "+table+"(id,FQN,name, container,number) values ("+"'"+id+"'"+","+"'"+FQN+"'"+","+"'"+name+"'"+","+"'"+container+"'"+","+"'"+number+"'"+")"); // 增加数据 
	            if (n > 0) {
//	            JOptionPane.showMessageDialog(null, "成功");
	        
	            
	            } else {
//	            JOptionPane.showMessageDialog(null, "失败");
	            }
	            //增加数据结束

	        while (res.next())   
	        {   

	        }   
	    }  
	  
	    catch(Exception ex)  
	    {  
	        ex.printStackTrace();  
	    }  
	} 
	//level insert end
		

	//entity insert 
	public static void Enitityinsert (int id,String name,String FQN, String container,int potincy,String directtype,String table )
    {  
     
    	try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);      
            stmt = (Statement)conn.createStatement();  
            res= stmt.executeQuery("SELECT * from  entity ");  


            // 增加数据
            Statement stm = (Statement) conn.createStatement();// 提交查巡
            String sql = "select * from entity";
            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";
           
            int n = stm.executeUpdate("insert into "+table+"(id,FQN,name,container,potincy,direct_type) values ("+"'"+id+"'"+","+"'"+FQN+"'"+","+"'"+name+"'"+","+"'"+container+"'"+","+"'"+potincy+"'"+","+"'"+directtype+"'"+")"); // 增加数据 
            if (n > 0) {
//            JOptionPane.showMessageDialog(null, "成功");
       
            
            } else {
//            JOptionPane.showMessageDialog(null, "失败");
            }
            //增加数据结束

        while (res.next())   
        {   

        }   
    }  
  
    catch(Exception ex)  
    {  
        ex.printStackTrace();  
    }  
} 
	
	public static void Enitityupdate (String entityinstanceFQN, String entityFQN )
    {  
     
    	try  
        {  
           Statement stmt=null;  
           ResultSet res=null;  
           Class.forName("com.mysql.jdbc.Driver");  
           Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);      
           stmt = (Statement)conn.createStatement();  
           res= stmt.executeQuery("SELECT * from  entity ");  
           Statement stm = (Statement) conn.createStatement();// 提交查巡
           String sql = "select * from entity";
           ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
          
        int n = stm.executeUpdate("update entity  set direct_type="+"'"+ entityFQN+"'"+ "where FQN=" +"'"+entityinstanceFQN+"'" ); // 增加数据
   
        
        if (n > 0) {
        	
        
        } else {

        }
        

        while (res.next())   
        {   

        }   
    }  
  
    catch(Exception ex)  
    {  
        ex.printStackTrace();  
    }  
} 
	
	//entity insert end
	
	//Attribute insert 
	public static void Attributeinsert (int id,String FQN,String name, String container,String duribility,String type,String value,String mutability,String table )
    {  
     
    	try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);      
            stmt = (Statement)conn.createStatement();  
            res= stmt.executeQuery("SELECT * from  attribute ");  


            // 增加数据
            Statement stm = (Statement) conn.createStatement();// 提交查巡
            String sql = "select * from attribute";
            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";
           
            int n = stm.executeUpdate("insert into "+table+"(id,FQN,name, container,duribility,type,value,mutability) values ("+"'"+id+"'"+","+"'"+FQN+"'"+","+"'"+name+"'"+","+"'"+container+"'"
            +","+"'"+duribility+"'"+","+"'"+type+"'"+","+"'"+value+"'"+","+"'"+mutability+"'"+")"); // 增加数据 
            if (n > 0) {
//            JOptionPane.showMessageDialog(null, "成功");
         
            
            } else {
//            JOptionPane.showMessageDialog(null, "失败");
            }
            //增加数据结束

        while (res.next())   
        {   
 
        }   
    }  
  
    catch(Exception ex)  
    {  
        ex.printStackTrace();  
    }  
} 
//Attribute insert end

//Method insert 
public static void Methodinsert(int id,String FQN,String name, String container,String duribility,String signature,String body,String table )
	    {  
	     
	    	try  
	        {  
	            Statement stmt=null;  
	            ResultSet res=null;  
	            Class.forName("com.mysql.jdbc.Driver");  
	            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
	            stmt = (Statement)conn.createStatement();  
	            res= stmt.executeQuery("SELECT * from  method ");  


	            // 增加数据
	            Statement stm = (Statement) conn.createStatement();// 提交查巡
	            String sql = "select * from method";
	            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//	            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";
	           
	            int n = stm.executeUpdate("insert into "+table+"(id,FQN,name, container,duribility,signature,body) values ("+"'"+id+"'"+","+"'"+FQN+"'"+","+"'"+name+"'"+","+"'"+container+"'"
	            +","+"'"+duribility+"'"+","+"'"+signature+"'"+","+"'"+body+"'"+")"); // 增加数据 
	            if (n > 0) {
//	            JOptionPane.showMessageDialog(null, "成功");

	            
	            } else {
//	            JOptionPane.showMessageDialog(null, "失败");
	            }
	            //增加数据结束

	        while (res.next())   
	        {   
	      
	        }   
	    }  
	  
	    catch(Exception ex)  
	    {  
	        ex.printStackTrace();  
	    }  
	} 
//Method insert end


//Binaryconnection insert 
				public static void Binaryconnectioninsert(int id,String FQN,String name, String container,int potency,String directtype,String label ,String particpant1,
			    		String particpant2, String roleName1 ,String roleName2,String  lower1, String lower2 ,	String upper1 ,String upper2 , 
			    		String navigalbeTo1, String navigableTo2,String table )
			    {  
			     
			    	try  
			        {  
			            Statement stmt=null;  
			            ResultSet res=null;  
			            Class.forName("com.mysql.jdbc.Driver");  
			            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
			            stmt = (Statement)conn.createStatement();  
			            res= stmt.executeQuery("SELECT * from  binaryconnection");  


			            // 增加数据
			            Statement stm = (Statement) conn.createStatement();// 提交查巡
			            String sql = "select * from binaryconnection";
			            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//			            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";
			           
			            int n = stm.executeUpdate("insert into "+table+"(id,FQN,name, container,potency,direct_type,label ,particpant1, particpant2, roleName1 ,roleName2, lower1, lower2 ,upper1 ,upper2 ,navigalbeTo1, navigableTo2) values ("
			            +"'"+id+"'"+","+"'"+FQN+"'"+","+"'"+name+"'"+","+"'"+container+"'"+","+"'"+potency+"'"+","+"'"+directtype+"'"+","+"'"+label+"'"+","+"'"+particpant1+"'"+","+"'"+particpant2+"'"+","+"'"+roleName1+"'"+","+"'"+roleName2+"'"+","+"'"+lower1+"'"+","+"'"+lower2+"'"+","+"'"+upper1+"'"+","+"'"+upper2+"'"+","+"'"+navigalbeTo1+"'"+","+"'"+navigableTo2+"'"+")"); // 增加数据 
			            if (n > 0) {
//			            JOptionPane.showMessageDialog(null, "成功");
			          
			            
			            } else {
//			            JOptionPane.showMessageDialog(null, "失败");
			            }
			            //增加数据结束

			        while (res.next())   
			        {   
	
			        }   
			    }  
			  
			    catch(Exception ex)  
			    {  
			        ex.printStackTrace();  
			    }  
			} 
				
	public static void Binaryconnectionupdate (String coninstanceFQN, String conFQN )
			    {  
			     
			    	try  
			        { 
			           Statement stmt=null;  
			           ResultSet res=null;  
			           Class.forName("com.mysql.jdbc.Driver");  
			           Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);      
			           stmt = (Statement)conn.createStatement();  
			           res= stmt.executeQuery("SELECT * from  binaryconnection ");  
			           Statement stm = (Statement) conn.createStatement();// 提交查巡
			           String sql = "select * from binaryconnection";
			           ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
			         
			        int n = stm.executeUpdate("update binaryconnection  set direct_type="+"'"+ conFQN+"'"+ "where FQN=" +"'"+coninstanceFQN+"'" ); // 增加数据
			   
			       
			        if (n > 0) {
			        	
			        
			        } else {

			        }
			        

			        while (res.next())   
			        {   

			        }   
			    }  
			  
			    catch(Exception ex)  
			    {  
			        ex.printStackTrace();  
			    }  
			} 				
//Binaryconnection insert end		
				
//inheritancerelationship"; insert 
public static void Inrinsert(int id,String FQN,String name, String container,String disjoint,String complete,String table)
			    {  
			     
			    	try  
			        {  
			            Statement stmt=null;  
			            ResultSet res=null;  
			            Class.forName("com.mysql.jdbc.Driver");  
			            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
			            stmt = (Statement)conn.createStatement();  
			            res= stmt.executeQuery("SELECT * from  inheritancerelationship");  


			            // 增加数据
			            Statement stm = (Statement) conn.createStatement();// 提交查巡
			            String sql = "select * from inheritancerelationship";
			            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//			            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";
			           
			            int n = stm.executeUpdate("insert into "+table+"(id,FQN,name,container,disjoint,complete) values ("+"'"+id+"'"+","+"'"+FQN+"'"+","+"'"+name+"'"+","+"'"+container+"'"+","+"'"+disjoint+"'"+","+"'"+complete+"'"+")"); // 增加数据 
			            if (n > 0) {
//			            JOptionPane.showMessageDialog(null, "成功");
			            
			            
			            } else {
//			            JOptionPane.showMessageDialog(null, "失败");
			            }
			            //增加数据结束

			        while (res.next())   
			        {   
			 
			        }   
			    }  
			  
			    catch(Exception ex)  
			    {  
			        ex.printStackTrace();  
			    }  
			} 
//inheritancerelationship"; insert end
				
//inheritanceparticipation insert 
public static void Inpinsert(int id,String FQN,String name, String container,String participant,int inheritanceRelationship_id,String table)
			    {  
			     
			    	try  
			        {  
			            Statement stmt=null;  
			            ResultSet res=null;  
			            Class.forName("com.mysql.jdbc.Driver");  
			            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);    
			            stmt = (Statement)conn.createStatement();  
			            res= stmt.executeQuery("SELECT * from  inheritanceparticipation ");  


			            // 增加数据
			            Statement stm = (Statement) conn.createStatement();// 提交查巡
			            String sql = "select * from inheritanceparticipation";
			            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//			            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";
			           
			            int n = stm.executeUpdate("insert into "+table+"(id,FQN,name, container,participant,inheritanceRelationship_id) values ("+"'"+id+"'"+","+"'"+FQN+"'"+","+"'"+name+"'"+","+"'"+container+"'"
			            +","+"'"+participant+"'"+","+"'"+inheritanceRelationship_id+"'"+")"); // 增加数据 
			            if (n > 0) {
//			            JOptionPane.showMessageDialog(null, "成功");
			           
			            
			            } else {
//			            JOptionPane.showMessageDialog(null, "失败");
			            }
			            //增加数据结束

			        while (res.next())   
			        {   
			           
			        }   
			    }  
			  
			    catch(Exception ex)  
			    {  
			        ex.printStackTrace();  
			    }  
			} 
//inheritanceparticipation insert end				

//generalconnection insert 
public static void Generalinsert(int id,String FQN,String name, String container,int potincy,String directtype,String label,
		  String kind,String table)
	    {  
	     
	    	try  
	        {  
	            Statement stmt=null;  
	            ResultSet res=null;  
	            Class.forName("com.mysql.jdbc.Driver");  
	            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
	            stmt = (Statement)conn.createStatement();  
	            res= stmt.executeQuery("SELECT * from  generalconnection ");  


	            // 增加数据
	            Statement stm = (Statement) conn.createStatement();// 提交查巡
	            String sql = "select * from generalconnection";
	            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//	            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";
	           
	            int n = stm.executeUpdate("insert into "+table+"(id,FQN,name, container,potincy,direct_type,label,kind) values ("+"'"+id+"'"+","+"'"+FQN+"'"+","+"'"+name+"'"+","+"'"+container+"'"
	            +","+"'"+potincy+"'"+","+"'"+directtype+"'"+","+"'"+label+"'"+","+"'"+kind+"'"+")"); // 增加数据 
	            if (n > 0) {
//	            JOptionPane.showMessageDialog(null, "成功");
	        
	            
	            } else {
//	            JOptionPane.showMessageDialog(null, "失败");
	            }
	            //增加数据结束

	        while (res.next())   
	        {   
	        
	        }   
	    }  
	  
	    catch(Exception ex)  
	    {  
	        ex.printStackTrace();  
	    }  
	} 
//generalconnection insert end

//participation insert 
public static void Participationinsert( int id,String name,String FQN,String container,int participant_id,String lower,String upper,
		  String row_name,String whole,String generalConnection,String table)
	    {  
	     
	    	try  
	        {  
	            Statement stmt=null;  
	            ResultSet res=null;  
	            Class.forName("com.mysql.jdbc.Driver");  
	            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
	            stmt = (Statement)conn.createStatement();  
	            res= stmt.executeQuery("SELECT * from  participation  ");  


	            // 增加数据
	            Statement stm = (Statement) conn.createStatement();// 提交查巡
	            String sql = "select * from participation";
	            ResultSet rs = stm.executeQuery(sql);// 取得查巡結果
//	            sql = "insert into entity (id,name,FQN,container) values ('6','AccountType','Example2.O2.AccountType','Example2.O10')";
	           
	            int n = stm.executeUpdate("insert into "+table+"( id,name,FQN,container,participant_id,lower,upper,row_name,whole,generalConnection) values ("+"'"+id+"'"+","+"'"+name+"'"+","+"'"+FQN+"'"+","+"'"+container+"'"
	            +","+"'"+participant_id+"'"+","+"'"+lower+"'"+","+"'"+upper+"'"+","+"'"+row_name+"'"+","+"'"+whole+"'"+","+"'"+generalConnection+"'"+")"); // 增加数据 
	            if (n > 0) {
//	            JOptionPane.showMessageDialog(null, "成功");
	           
	            
	            } else {
//	            JOptionPane.showMessageDialog(null, "失败");
	            }
	            //增加数据结束

	        while (res.next())   
	        {   
	           
	        }   
	    }  
	  
	    catch(Exception ex)  
	    {  
	        ex.printStackTrace();  
	    }  
	} 
//participation insert end
				
//Clear table before insert data to mysql
	public static void delatetable()  
    {  
     
    	try  
        {  
            Statement stmt=null;  
            ResultSet res=null;  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(sqldatabase,mysqluser,mysqlpassword);   
            stmt = (Statement)conn.createStatement();  
//          res= stmt.executeQuery("SELECT * from  entity ");                 
            String query1 = "delete from entity";
            String query2 = "delete from deepmodel";
            String query3 = "delete from level";
            String query4 = "delete from package";
            String query5 = "delete from attribute";
            String query6 = "delete from binaryconnection";
            String query7 = "delete from inheritanceparticipation";
            String query8 = "delete from inheritancerelationship";
            String query9 = "delete from method";
            String query10 = "delete from generalconnection";
            String query11 = "delete from participation";
            PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
            PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
            PreparedStatement preparedStmt3 = conn.prepareStatement(query3);
            PreparedStatement preparedStmt4 = conn.prepareStatement(query4);
            PreparedStatement preparedStmt5 = conn.prepareStatement(query5);
            PreparedStatement preparedStmt6 = conn.prepareStatement(query6);
            PreparedStatement preparedStmt7 = conn.prepareStatement(query7);
            PreparedStatement preparedStmt8 = conn.prepareStatement(query8);
            PreparedStatement preparedStmt9 = conn.prepareStatement(query9);
            PreparedStatement preparedStmt10 = conn.prepareStatement(query10);
            PreparedStatement preparedStmt11 = conn.prepareStatement(query11);
            preparedStmt1.execute();
            preparedStmt2.execute();
            preparedStmt3.execute();
            preparedStmt4.execute();
            preparedStmt5.execute();
            preparedStmt6.execute();
            preparedStmt7.execute();
            preparedStmt8.execute();
            preparedStmt9.execute();
            preparedStmt10.execute();
            preparedStmt11.execute();
    
    }  
  
    catch(Exception ex)  
    {  
        ex.printStackTrace();  
    }  
} 
//delete table before insert data to mysql  end

}
