package railway;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Raildetail extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String driverClassName = null;
		String password=null;
		String username = null;
		String url = null;
		ResultSet rs =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		ServletContext scontext = getServletContext();
		ServletConfig sconfig = getServletConfig();
		
		url = scontext.getInitParameter("url");
		driverClassName = scontext.getInitParameter("fqcn");
		HttpSession hs = req.getSession();
		username=sconfig.getInitParameter("username");
		password=sconfig.getInitParameter("pwd");
		String hiddenVal = req.getParameter("hiddenVal");
		
		if(hiddenVal.equals("2")) {
			String trainno = req.getParameter("tno");
			String tname = req.getParameter("tname");
			String source = req.getParameter("source");
			String destination = req.getParameter("desti");
			String arrival = req.getParameter("arri");
			String deparature = req.getParameter("dept");
			String ttime = req.getParameter("travel");
			String fare = req.getParameter("fare");
			
			hs.setAttribute("Trainno", trainno);
			hs.setAttribute("Tname", tname);
			hs.setAttribute("Source", source);
			hs.setAttribute("Destination",destination);
			hs.setAttribute("Arrival", arrival);
			hs.setAttribute("Deparature", deparature);
			hs.setAttribute("Travel", ttime);
			hs.setAttribute("Fare", fare);
			
			
			String trainno1 = (String)hs.getAttribute("Trainno");
			String tname1=(String)hs.getAttribute("Tname");
			String source1 = (String)hs.getAttribute("Source");
			String destination1=(String)hs.getAttribute("Destination");
			String arrival1 = (String)hs.getAttribute("Arrival");
			String deparature1 = (String)hs.getAttribute("Deparature");
			String travel = (String)hs.getAttribute("Travel");
			String fare1 = (String)hs.getAttribute("Fare");
			
				
			String qry = "insert into password.train values(?,?,?,?,?,?,?,?)";
			PrintWriter out = resp.getWriter();
			
			try {
				Class.forName(driverClassName);
				con=DriverManager.getConnection(url,username,password);
				pstmt = con.prepareStatement(qry);
				
				pstmt.setString(1, trainno1);
				pstmt.setString(2, tname1);
				pstmt.setString(3, source1);
				pstmt.setString(4, destination1);
				pstmt.setString(5, arrival1);
				pstmt.setString(6, deparature1);
				pstmt.setString(7, travel);
				pstmt.setString(8, fare1);
				
				int i = pstmt.executeUpdate();
				if(i==1) {
					out.print("<html><head><meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>index</title>\r\n" + 
							"<link href=\"style.css\" type=\"text/css\" rel=\"stylesheet\" /></head><body><div id=\"wrapper\">\r\n" + 
							"\r\n" + 
							"<!-- header begin -->\r\n" + 
							"<div class=\"head\">\r\n" + 
							"<div id=\"logo\"><img src=\"images/logo.jpg\" width=\"120\" height=\"110\" /> </div>\r\n" + 
							"<div id=\"ttl\"><br> WELCOMES <br> TO<br>INDIAN RAILWAYS </div>\r\n" + 
							"</div>\r\n" + 
							"<!-- header end -->\r\n" + 
							"\r\n" + 
							"<!-- menu begin-->\r\n" + 
							"<div class=\"menu\">  \r\n" + 
							" <a href=\"password.html\">Home</a>  |\r\n" + 
							" <a href=\"Addtrain.html\">AddTrain </a> | \r\n" + 
							" <a href=\"search.html\">Search Train</a> | \r\n" + 
							" <a href=\"password.html\">more</a> | \r\n" + 
							"  <a href=\"password.html\">more</a> | \r\n" + 
							"   <a href=\"password.html\">more</a> \r\n" + 
							" </div>\r\n" + 
							"<!--menu end-->\r\n" + 
							"\r\n" + 
							"<!--content begin-->\r\n" + 
							"<div class=\"content\">\r\n" + 
							"<div id=\"lft\"><center> \r\n" + 
							"<i><br><h2>TRAIN  DETAILS ADDED SUCCESSFULLY</h2></i> <br><br><pre>\r\n" + 
							"<a href=\"search.html\"><button>SEARCH TRAINS</button><br>" + 
							"<a href=\"Addtrain.html\"><button>ADD MORE TRAIN DETAILS </button><br></center>" + 							
							"</div>\r\n" + 
							"<div id=\"rht\">\r\n" + 
							"<h2>  Announcements</h2>\r\n" + 
							"<marquee  direction=\"up\" ><font color=\"#FF0000\">\r\n" + 
							" Train Delays!!!</font><br /><br />\r\n" + 
							" <font color=\"#00FFFF\">Follow train updates for<br /><br />\r\n" + 
							" Canceled  and diverted Trains</marquee></font></div>\r\n" + 
							" </div>\r\n" + 
							"<!-- content end-->\r\n" + 
							"\r\n" + 
							"\r\n" + 
							"<!-- footer begin-->\r\n" + 
							"<div class=\"footer\"><center>\r\n" + 
							"<font face=\"Bradley Hand ITC\" color=\"#FF0000\" size=\"+2\" ><b>\r\n" + 
							"south westren railway</b></font></center></div>\r\n" + 
							"<!-- footer end-->\r\n" + 
							"</body></html>");
					//resp.sendRedirect("./Fourth.html");
				}
				else
				{
					out.print("not inserted");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
			if(pstmt!=null)
				try {
					pstmt.close();
				}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			if(con!=null)
				try {
					con.close();
				    }
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		    }
			
			
		}
		
		
		if(hiddenVal.equals("1")) {
			String uname = req.getParameter("uname");
			String pwd = req.getParameter("pwd");
			
			hs.setAttribute("uname", uname);
			hs.setAttribute("pwd", pwd);
			
			String qry = "select *from password.var where username=? and pass=?";
			PrintWriter out = resp.getWriter();
				try {
					Class.forName(driverClassName);
					con = DriverManager.getConnection(url,username,password);
					pstmt =con.prepareStatement(qry);
					pstmt.setString(1, uname);
					pstmt.setString(2, pwd);					
					rs=pstmt.executeQuery();
					if(rs.next()) {
						resp.sendRedirect("./Addtrain.html");
						
					}
					else {
						out.println("<!DOCTYPE html>\r\n" + 
								"<html>\r\n" + 
								"<head>\r\n" + 
								"<meta charset=\"ISO-8859-1\">\r\n" + 
								"<title>index</title>\r\n" + 
								"<link href=\"style.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n" + 
								"</head>\r\n" + 
								"<body>\r\n" + 
								"<form action=\"gs\" method=\"post\">\r\n" + 
								"<div id=\"wrapper\">\r\n" + 
								"\r\n" + 
								"<!-- header begin -->\r\n" + 
								"<div class=\"head\">\r\n" + 
								"<div id=\"logo\"><img src=\"images/logo.jpg\" width=\"120\" height=\"110\" /> </div>\r\n" + 
								"<div id=\"ttl\"><br> WELCOMES <br> TO<br>INDIAN RAILWAYS </div>\r\n" + 
								"\r\n" + 
								"</div>\r\n" + 
								"<!-- header end -->\r\n" + 
								"\r\n" + 
								"<!-- menu begin-->\r\n" + 
								"<div class=\"menu\">  \r\n" + 
								" <a href=\"password.html\">Home</a>  |\r\n" + 
								" <a href=\"Addtrain.html\">AddTrain </a> | \r\n" + 
								" <a href=\"search.html\">Search Train</a> | \r\n" + 
								" <a href=\"password.html\">more</a> | \r\n" + 
								"  <a href=\"password.html\">more</a> | \r\n" + 
								"   <a href=\"password.html\">more</a> \r\n" + 
								" </div>\r\n" + 
								"<!--menu end-->\r\n" + 
								"\r\n" + 
								"<!--content begin-->\r\n" + 
								"<div class=\"content\">\r\n" + 
								"<div id=\"lft\"><center> \r\n" + 
								"<b><br><font color=\"red\"><h3><i>Enter Your Details To Login</i></h3></font></b> <br><br><pre>\r\n" + 
								"Username: &nbsp;<input type=\"text\" name=\"uname\"><br>\r\n" + 
								"Password: &nbsp;<input type=\"password\" name=\"pwd\"><br></pre>\r\n" + 
								"<input type=\"text\" name=\"hiddenVal\" value=\"1\" hidden=\"true\">\r\n" + 
								"<input type=\"submit\" value=\"Login\"><br><br>Invalid please Re-Enter </center>\r\n" + 
								"</form>\r\n" + 
								"</div>\r\n" + 
								"<div id=\"rht\">\r\n" + 
								"<h2>  Announcements</h2>\r\n" + 
								"<marquee  direction=\"up\" ><font color=\"#FF0000\">\r\n" + 
								" Train Delays!!!</font><br /><br />\r\n" + 
								" <font color=\"#00FFFF\">Follow train updates for<br /><br />\r\n" + 
								" Canceled  and diverted Trains</marquee></font></div>\r\n" + 
								" </div>\r\n" + 
								"<!-- content end-->\r\n" + 
								"\r\n" + 
								"\r\n" + 
								"<!-- footer begin-->\r\n" + 
								"<div class=\"footer\"><center>\r\n" + 
								"<font face=\"Bradley Hand ITC\" color=\"#FF0000\" size=\"+2\" ><b>\r\n" + 
								"south westren railway</b></font></center></div>\r\n" + 
								"<!-- footer end-->\r\n" + 
								"</body>\r\n" + 
								"</html>");
					}
					
				} catch (ClassNotFoundException|SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally
				{
				if(pstmt!=null)
					try {
						pstmt.close();
					}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				if(con!=null)
					try {
						con.close();
					    }
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			    }
		}		
		
	if(hiddenVal.equals("4")) {
									
			String source = req.getParameter("i");
			String desti = req.getParameter("j");
				
			
			hs.setAttribute("i", source);
			hs.setAttribute("j", desti);
							
			String qry = "select *from password.train where source=? and desti=?";
			PrintWriter out = resp.getWriter();
				try {
					Class.forName(driverClassName);
					con = DriverManager.getConnection(url,username,password);
					pstmt =con.prepareStatement(qry);
					pstmt.setString(1, source);
					pstmt.setString(2, desti);					
					rs=pstmt.executeQuery();
										//out.print("yes");
					if(rs.next()) 
					{
						String trainno2 = rs.getString("trainno");
						String trainname=rs.getString("trainname");
						String source2 = rs.getString("source");
						String desti2=rs.getString("desti");
						String dept2=rs.getString("depttime");
						String arrt2=rs.getString("arrtime");
						String tra2=rs.getString("tratime");
						String fare=rs.getString("traincol");
						
						hs.setAttribute("tno", trainno2);
						hs.setAttribute("trainname", trainname);
						hs.setAttribute("source2", source2);
						hs.setAttribute("desti2",desti2);
						hs.setAttribute("dept2",dept2);
						hs.setAttribute("arrt2", arrt2);
						hs.setAttribute("tra2", tra2);
						hs.setAttribute("fare", fare);
					}
					else
					{
						out.print("INVALID SEARCH");
					}
						
						
						String tnum = (String)hs.getAttribute("tno");
						String tnam = (String)hs.getAttribute("trainname");
						String s = (String)hs.getAttribute("source2");
						String d = (String)hs.getAttribute("desti2");
						String de = (String)hs.getAttribute("dept2");
						String a = (String)hs.getAttribute("arrt2");
						String tt = (String)hs.getAttribute("tra2");
						String f = (String)hs.getAttribute("fare");
						//out.print(tnum);
						
						out.print("<html>\r\n" + 
								"<head>\r\n" + 
								"<meta charset=\"ISO-8859-1\">\r\n" + 
								"<title>index</title>\r\n" + 
								"<link href=\"style.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n" + 
								"</head>\r\n" + 
								"<body>\r\n" + 
								"<form action=\"gs\" method=\"post\">\r\n" + 
								"<div id=\"wrapper\">\r\n" + 
								"\r\n" + 
								"<!-- header begin -->\r\n" + 
								"<div class=\"head\">\r\n" + 
								"<div id=\"logo\"><img src=\"images/logo.jpg\" width=\"120\" height=\"110\" /> </div>\r\n" + 
								"<div id=\"ttl\"><br> WELCOMES <br> TO<br>INDIAN RAILWAYS </div>\r\n" + 
								"\r\n" + 
								"</div>\r\n" + 
								"<!-- header end -->\r\n" + 
								"\r\n" + 
								"<!-- menu begin-->\r\n" + 
								"<div class=\"menu\">  \r\n" + 
								" <a href=\"password.html\">Home</a>  |\r\n" + 
								" <a href=\"Addtrain.html\">AddTrain </a> | \r\n" + 
								" <a href=\"search.html\">Search Train</a> | \r\n" + 
								" <a href=\"password.html\">more</a> | \r\n" + 
								"  <a href=\"password.html\">more</a> | \r\n" + 
								"   <a href=\"password.html\">more</a> \r\n" + 
								" </div>\r\n" + 
								"<!--menu end-->\r\n" + 
								"\r\n" + 
								"<!--content begin-->\r\n" + 
								"<div class=\"content\">\r\n" + 
								"<div id=\"lft\"><center> \r\n" + 
								"<font color=\"red\"><i><br><h3>Train details  are</h3></i></font> <pre>\r\n" + 
								"<font color=\"yellow\">Train No &emsp;Train Name&emsp;Source&emsp;Destination&emsp;Arrival Time&emsp;Departure Time&emsp;Travel Time&emsp;Fare</font><br>"+tnum+"&emsp;"+tnam+"&emsp;"+s+"&emsp;"+d+"&emsp;"+a+"&emsp;"+de+"&emsp;"+tt+"&emsp;"+f+"</div>\r\n" + 
								
								"</body>\r\n" + 
								"</html>");
			
			} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
				finally
				{
				if(pstmt!=null)
					try {
						pstmt.close();
					}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				if(con!=null)
					try {
						con.close();
					    }
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			    }
		}
			
	if(hiddenVal.equals("5")) {
		
		String ttno = req.getParameter("text");
			
		
		hs.setAttribute("text", ttno);
								
		String qry = "select *from password.train where trainno=?";
		PrintWriter out = resp.getWriter();
			try {
				Class.forName(driverClassName);
				con = DriverManager.getConnection(url,username,password);
				pstmt =con.prepareStatement(qry);
				pstmt.setString(1,ttno);	
				rs=pstmt.executeQuery();
									//out.print("yes");
				if(rs.next()) 
				{
					String trainno2 = rs.getString("trainno");
					String trainname=rs.getString("trainname");
					String source2 = rs.getString("source");
					String desti2=rs.getString("desti");
					String dept2=rs.getString("depttime");
					String arrt2=rs.getString("arrtime");
					String tra2=rs.getString("tratime");
					String fare=rs.getString("traincol");
					
					hs.setAttribute("tno", trainno2);
					hs.setAttribute("trainname", trainname);
					hs.setAttribute("source2", source2);
					hs.setAttribute("desti2",desti2);
					hs.setAttribute("dept2",dept2);
					hs.setAttribute("arrt2", arrt2);
					hs.setAttribute("tra2", tra2);
					hs.setAttribute("fare", fare);
				}else
				{
					out.print("Invalid search");
				}
					
					
					String tnum = (String)hs.getAttribute("tno");
					String tnam = (String)hs.getAttribute("trainname");
					String s = (String)hs.getAttribute("source2");
					String d = (String)hs.getAttribute("desti2");
					String de = (String)hs.getAttribute("dept2");
					String a = (String)hs.getAttribute("arrt2");
					String tt = (String)hs.getAttribute("tra2");
					String f = (String)hs.getAttribute("fare");
					//out.print(tnum);
					
					out.print("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>index</title>\r\n" + 
							"<link href=\"style.css\" type=\"text/css\" rel=\"stylesheet\" />\r\n" + 
							"</head>\r\n" + 
							"<body>\r\n" + 
							"<form action=\"gs\" method=\"post\">\r\n" + 
							"<div id=\"wrapper\">\r\n" + 
							"\r\n" + 
							"<!-- header begin -->\r\n" + 
							"<div class=\"head\">\r\n" + 
							"<div id=\"logo\"><img src=\"images/logo.jpg\" width=\"120\" height=\"110\" /> </div>\r\n" + 
							"<div id=\"ttl\"><br> WELCOMES <br> TO<br>INDIAN RAILWAYS </div>\r\n" + 
							"\r\n" + 
							"</div>\r\n" + 
							"<!-- header end -->\r\n" + 
							"\r\n" + 
							"<!-- menu begin-->\r\n" + 
							"<div class=\"menu\">  \r\n" + 
							" <a href=\"password.html\">Home</a>  |\r\n" + 
							" <a href=\"Addtrain.html\">AddTrain </a> | \r\n" + 
							" <a href=\"search.html\">Search Train</a> | \r\n" + 
							" <a href=\"password.html\">more</a> | \r\n" + 
							"  <a href=\"password.html\">more</a> | \r\n" + 
							"   <a href=\"password.html\">more</a> \r\n" + 
							" </div>\r\n" + 
							"<!--menu end-->\r\n" + 
							"\r\n" + 
							"<!--content begin-->\r\n" + 
							"<div class=\"content\">\r\n" + 
							"<div id=\"lft\">\r\n" + 
							"<font color=\"red\"><i><br><h3><center>Train details  are</center></h3></i></font>\r\n" + 
							"<font color=\"yellow\">Train No &emsp;Train Name&emsp;&emsp;&emsp;Source&emsp;Destination&emsp;Arrival Time&emsp;Departure Time&emsp;Travel Time&emsp;Fare</font><br>"+
							"<br><br>"+tnum+"&emsp;"+tnam+"&emsp;&emsp;&emsp;&emsp;"+s+"&emsp;"+d+"&emsp;"+a+"&emsp;"+de+"&emsp;"+tt+"&emsp;"+f+"</div>\r\n" + 
							
							"</body>\r\n" + 
							"</html>");
		
		} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
			finally
			{
			if(pstmt!=null)
				try {
					pstmt.close();
				}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			if(con!=null)
				try {
					con.close();
				    }
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		    }
	}		

	}

}
