import java.util.*;
import java.util.Date;
import java.sql.*;


public class EmpDao {

	public static Connection getConnection(){
		Connection con=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","123456");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(Employee e){
		int status=0;
		try{
			Connection con=EmpDao.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into employee(name,address,gender,salary,birthdate) values (?,?,?,?,?)");
			
			ps.setString(1,e.getName());
			ps.setString(2,e.getAddress());
			ps.setByte(3, e.getGender());
			ps.setDouble(4, e.getSalary());
			ps.setDate(5, new java.sql.Date(e.getBirthDate().getTime()));
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}

	public static List<Employee> getAllEmployees(){  
        List<Employee> list=new ArrayList<Employee>();  
          
        try{  
            Connection con=EmpDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from employee");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Employee e=new Employee();  
                e.setEmployeeId(rs.getInt(1));  
                e.setName(rs.getString(2)); 
                e.setAddress(rs.getString(3));
                e.setGender(rs.getByte(4));
                e.setSalary(rs.getDouble(5));
                e.setBirthDate(rs.getDate(6)); 
                list.add(e);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    } 
	
	public static Employee getEmployeeById(int id){  
        Employee e=new Employee();  
          
        try{  
            Connection con=EmpDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from employee where employeeid=?");  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){ 
            	e.setEmployeeId(rs.getByte(1));
                e.setName(rs.getString(2));
                e.setAddress(rs.getString(3));
                e.setGender(rs.getByte(4));
                e.setSalary(rs.getDouble(5)); 
                e.setBirthDate(rs.getDate(6));
            }  
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}  
          
        return e;  
    }  
	
	 public static int update(Employee e){  
	        int status=0;  
	        try{  
	        	System.out.println(e.toString()); 
	            Connection con=EmpDao.getConnection();  
	            PreparedStatement ps=con.prepareStatement("update employee set name=?, address=?, gender=?, salary=?, birthdate=? where employeeid=?");  
	            ps.setString(1,e.getName());  
	            ps.setString(2,e.getAddress());  
	            ps.setByte(3,e.getGender());
	            ps.setDouble(4,e.getSalary());
	            ps.setDate(5, new java.sql.Date(e.getBirthDate().getTime()));
	            ps.setInt(6,e.getEmployeeId());
	         
	            //ps.setDate(5,e.getBirthDate());  
	           
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return status;  
	    }  
	
	 public static int delete(int id){  
	        int status=0;  
	        try{  
	            Connection con=EmpDao.getConnection();  
	            PreparedStatement ps=con.prepareStatement("delete from employee where employeeid=?");  
	            ps.setInt(1,id);  
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception e){e.printStackTrace();}  
	          
	        return status;  
	    }  
}