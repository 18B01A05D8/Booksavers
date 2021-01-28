package com.ts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.dao.BookDAO;
import com.dao.UserDAO;
import com.dto.Book;
import com.dto.User;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	@Path("hi")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hi() throws UnsupportedEncodingException {
		System.out.println("Hi...");
		return "Hi Service!";
	}
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	@Path("getUserByEmail/{emailId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByEmail(@PathParam("emailId") String emailId) {
		System.out.println("Recieved path params: "+emailId); 
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserByEmail(emailId);
		return user;
	}

	@Path("getUserByUserPass/{loginId}/{password}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getEmpByUserPass(@PathParam("loginId") String loginId,@PathParam("password") String password) {
		System.out.println("Recieved path params: "+loginId+" "+password); 
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserByUserPass(loginId, password);
		return user;
	}


	@Path("getAllUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUser() {

		UserDAO userDAO = new UserDAO();
		List <User> userList = userDAO.getAllUsers();

		return userList;
	}
	
	@Path("getUser/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("userId") int userId) {

		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(userId);

		return user;
	}


	@Path("getBooks")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBooks() {        
		System.out.println("Inside api get books...");
		BookDAO bookDao = new BookDAO();
		List <Book> bookList = bookDao.getAllBooks();
		return bookList;
	}

	@Path("getBookByName/{bookName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBookByName(@PathParam("bookName") String bookName) {        
		System.out.println(bookName);      
		BookDAO bookDao = new BookDAO();
		List<Book> books = bookDao.getBookByName(bookName);
		System.out.println(books); 
		return books;
	}
	
	
	@Path("getBookByCategoryName/{categoryName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBookByCategoryName(@PathParam("categoryName") String categoryName) {        
		System.out.println(categoryName);      
		BookDAO bookDao = new BookDAO();
		List<Book> books = bookDao.getBookByName(categoryName);
		System.out.println(books); 
		return books;
	}
	
	@Path("mail/{emailId}/{subject}/{body}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String mail(@PathParam("emailId") String emailId,@PathParam("subject") String subject1,@PathParam("body") String body1) throws MessagingException {
			String subject= subject1;
			String body= body1;
			String email= emailId;
			String host = "smtp.gmail.com";
			String from = "tejaswit0708@gmail.com";
			String pass = "Tejaswi@708";

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true"); // added this line
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");

			String[] to = {email}; // added this line

			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses

			for( int i=0; i < to.length; i++ )
			{
				// changed from a while loop
				toAddress[i] = new InternetAddress(to[i]);
			}

			for( int i=0; i < toAddress.length; i++)
			{
				// changed from a while loop
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);

			Transport transport = session.getTransport("smtp");

			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());

			transport.close();

			return "Successful";
    	}
	
	@Path("registerUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerUser(User user) {
		System.out.println("Data Recieved in User Register : " + user);
		UserDAO userDao = new UserDAO();
		userDao.register(user);
	
	}
	
	@Path("getBookByColumns/{bookName}/{selltype}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBookByColumns(@PathParam("bookName") String bookName,@PathParam("selltype") String selltype) {        
		System.out.println(bookName);      
		BookDAO bookDao = new BookDAO();
		List<Book> books = bookDao.getBookByColumns(bookName,selltype);
		System.out.println(books); 
		return books;
	}

	@Path("registerBook")
	@POST
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void registerBook(@FormDataParam("bookImage") InputStream fileInputStream, @FormDataParam("bookImage") FormDataContentDisposition
			formDataConnectionDisposition, @FormDataParam("bookName") String bookName,
			@FormDataParam("authorName") String authorName,
			@FormDataParam("bookPrice") Double bookPrice,
			@FormDataParam("categoryName") String categoryName,
			@FormDataParam("selltype") String selltype,
			@FormDataParam("userId") Integer userId) throws IOException{
			int read = 0;
			byte[] bytes = new byte[1024];
			
			String path = this.getClass().getClassLoader().getResource("").getPath();
			String pathArr[] = path.split("/WEB-INF/classes/"); 
			FileOutputStream out = new FileOutputStream(new File(pathArr[0]+"/Image/",formDataConnectionDisposition.getFileName()));
			
			while((read = fileInputStream.read(bytes))!= -1){
				out.write(bytes,0,read);
			}
			out.flush();
			out.close();
			User user = new User();
			user.setUserId(userId);
			Book book = new Book();
			book.setBookName(bookName);
			book.setCategoryName(categoryName);
			book.setAuthorName(authorName);
			book.setBookImage(formDataConnectionDisposition.getFileName());
			book.setBookPrice(bookPrice);
			book.setSelltype(selltype);
			book.setUser(user);
			System.out.println(user);
			
			
			BookDAO bookDao = new BookDAO();
			bookDao.register(book);
	}

	@Path("getBookByUserId/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getBookByUserId(@PathParam("userId") int userId) {        
		System.out.println(userId);      
		BookDAO bookDao = new BookDAO();
		User user = new User();
		user.setUserId(userId);
		List<Book> books = bookDao.getBookById(user);
		System.out.println(books); 
		return books;
	}
	
	@Path("updateUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateUser(User user) {
		System.out.println("Data Recieved in User Update : " + user);
		UserDAO userDao = new UserDAO();
		userDao.updateUser(user);
	
	}
	/*@Path("registerBook")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerBook(Book book) {
		System.out.println("Data Recieved in Book Register : "+book); 
		BookDAO bookDao = new BookDAO();
		bookDao.register(book);
	}
	
	//It is sample code if need to test and insert values into any tables
	/*@Path("registerEmp")
	@GET
	public void registerEmp() {
		DeptDAO deptDao = new DeptDAO();
		
		Department dept = deptDao.getDept(1);
		
		Employee employee = new Employee();
		employee.setEmpName("PASHA");
		employee.setEmail("email@gmail.com");
		employee.setGender("Male");
		employee.setJoinDate(new java.util.Date());
		employee.setDepartment(dept); 
		
		EmployeeDAO employeeDao = new EmployeeDAO();
		employeeDao.register(employee);
	
	}*/
	/*@Path("registerUser1")
	@GET
	public String registerUser1() {
				
		User user = new User();
		user.setUserId(1);
		user.setFirstName("VYSHNAVI");
		user.setLastName("TADIKONDA");
		user.setEmailId("email@gmail.com");
		user.setMobile("7981160809");
		user.setState("AP");
		user.setCity("Gudivada");
		user.setStreet("RajendraNagar");
		
		UserDAO userDao = new UserDAO();
		userDao.register(user);
		
		return "Success";
	
	}
	
	@Path("registerBook1")
	@GET
	public String registerBook1() {
		UserDAO userDao = new UserDAO();
		
		User user = userDao.getUser(1);
		
		Book book = new Book();
		book.setBookName("Financial Managment");
		book.setAuthorName("IM Pandey");
		book.setBookImage("Yes");
		book.setBookPrice(1000.20);
		book.setCategoryName("MBA");
		
		BookDAO bookDao = new BookDAO();
		bookDao.register(book);
		
		return "Success";	
	}*/

}
