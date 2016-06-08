package com.library.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.library.action.JavaMail;
import com.library.material.Apply;
import com.library.material.Book;
import com.library.material.Borrow;
import com.library.material.Category;
import com.library.material.Comment;
import com.library.material.Scorehistory;
import com.library.material.Teacher;
import com.library.material.User;

public class Actions {
	public Actions(){
		
	}
	public Boolean addUser(User user){
		Boolean flag = false;
		System.out.println("addUserStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM User WHERE uid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql).setLong(0, user.getUid()).list();
			if(list.size()>0){
				flag = false;
			}else{
				session.save(user);
				tx.commit();
				flag = true;
			}			
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public Boolean deleteUser(User user){
		Boolean flag = false;
		System.out.println("deleteUserStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			session.delete(user);
			tx.commit();
			flag = true;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;	
	}
	
	public Boolean checkUser(User user){
		Boolean flag = false;
		System.out.println("checkUserStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM User WHERE uid = ? AND password = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql).setLong(0, user.getUid()).setString(1,user.getPassword()).list();		
			int num = list.size();
			
			if(num!=0){
				flag = true;
			}
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;
	}
	
	public Boolean setPassword(int uid,String oldpsw,String newpsw){
		Boolean flag = false;
		System.out.println("setPasswordStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM User WHERE uid = ? AND password = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql).setLong(0, uid).setString(1,oldpsw).list();		
			if(list.size()>0){
				User user = list.get(0);
				user.setPassword(newpsw);
				session.update(user);
				flag = true;
			}
			
			tx.commit();
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;	
		
	}
	
	public Boolean checkTeacher(Teacher teacher){
		Boolean flag = false;
		System.out.println("checkTeacherStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Teacher WHERE id = ? AND password = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Teacher> list = session.createQuery(hql).setLong(0, teacher.getId()).setString(1,teacher.getPassword()).list();		
			int num = list.size();
			
			if(num!=0){
				flag = true;
			}
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;
	}
	
	public Boolean modifyBook(int bid,Book book){
		Boolean flag = false;
		System.out.println("addBookStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			Book booktemp = getBookDetail(bid);
			booktemp.setAuthor(book.getAuthor());
			booktemp.setBookname(book.getBookname());
			Category category = new Category();
			category.setCid(book.getCategory().getCid());
			booktemp.setCategory(category);
			booktemp.setPress(book.getPress());
			
			session.update(booktemp);
			tx.commit();
			flag = true;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;
	}
	
	public Boolean addBook(int uid,Book book){
		Boolean flag = false;
		System.out.println("addBookStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			User user = new User(uid);
			book.setUser(user);
			
			session.save(book);
			tx.commit();
			flag = true;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;
	}
	
	public Boolean deleteBook(int uid,Book book){
		Boolean flag = false;
		System.out.println("deleteBookStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			User user = new User(uid);
			book.setUser(user);
			
			session.delete(book);
			tx.commit();
			flag = true;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public int applyBorrow(int uid, Book book){
		int flag = 0;
		System.out.println("applyBorrowStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql1 = "From Borrow WHERE uid = ? AND borrowstatus = 1";
			@SuppressWarnings("unchecked")
			List<Borrow> bl = session.createQuery(hql1).setLong(0, uid).list();
			String hql2 = "From Apply WHERE uid = ? AND status = 'borrow'";
			@SuppressWarnings("unchecked")
			List<Apply> ap = session.createQuery(hql2).setLong(0, uid).list();
			if((bl.size()+ap.size())>=2){
				flag = 4;
			}else{
				String hql = "FROM Book WHERE bid = ?";
				System.out.println(hql);
				@SuppressWarnings("unchecked")
				List<Book> list = session.createQuery(hql).setLong(0, book.getBid()).list();
				Book bookTemp = list.get(0);
				int bookStatus = bookTemp.getBookstatus();
				if(bookStatus == 2){
					flag = 2;
				}else if(bookStatus == 3){
					flag = 3;
				}else if(bookStatus == 1){
					Apply apply = new Apply();
					apply.setBook(bookTemp);
					User user = new User(uid);
					apply.setUserByUid(user);
					apply.setStatus("borrow");
					User owner = new User(bookTemp.getUser().getUid());
					apply.setUserByOwnerid(owner);
					session.save(apply);
					//next change the bookstatus in book table to 3
					bookTemp.setBookstatus(3);
					session.update(bookTemp);
					
					JavaMail mail = new JavaMail();
					String email = getUserDetail(book.getUser().getUid()).getEmail();
					String bookname = book.getBookname();
					String username = getUserDetail(uid).getUsername();
					mail.sendEmail(username, bookname, email);
					tx.commit();
					flag = 1;
				}
			}
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public Boolean confirmBorrow(int bid){
		Boolean flag = false;
		System.out.println("confirmBorrowStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql1 = "FROM Apply WHERE bid = ?";
			//String hql1 = "FROM Apply a left join a.book WHERE a.bid = ?";
			@SuppressWarnings("unchecked")
			List<Apply> list1 = session.createQuery(hql1).setLong(0, bid).list();
			int num = list1.size();
			if(num>0){
				Apply apply = list1.get(0);
				Book book = getBookDetail(apply.getBook().getBid());			
				book.setBookstatus(2);
				session.update(book);
				
				/*Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String dateString = formatter.format(currentTime); */
				long date = System.currentTimeMillis() / 1000;
				long dateReturn = date + 2592000;
				Borrow borrow = new Borrow();
				borrow.setBook(book);
				borrow.setBorrowdate(date);
				borrow.setReturndate(dateReturn);
				User user = getUserDetail(apply.getUserByUid().getUid());
				borrow.setUser(user);
				borrow.setBorrowstatus(1);
				session.save(borrow);
				apply.setStatus("bring");
				session.update(apply);
				
				Apply apply1 = new Apply();
				apply1.setStatus("get");
				apply1.setBook(apply.getBook());
				apply1.setUserByOwnerid(apply.getUserByUid());
				apply1.setUserByUid(apply.getUserByOwnerid());
				session.save(apply1);
				
				String reason = "借阅图书"+book.getBookname();
				Scorehistory scorehistory = new Scorehistory();
				scorehistory.setScoredate(date);
				scorehistory.setUser(user);
				scorehistory.setScore(10);
				scorehistory.setReason(reason);
				session.save(scorehistory);
				
				int score = user.getScore()+10;
				user.setScore(score);
				session.update(user);
				
				tx.commit();
				flag = true;
			}			
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public Boolean confirmGet(int bid){
		Boolean flag = false;
		System.out.println("confirmBorrowStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql1 = "FROM Apply WHERE bid = ?";
			@SuppressWarnings("unchecked")
			List<Apply> list1 = session.createQuery(hql1).setLong(0, bid).list();
			int num = list1.size();
			for(int i=0;i<num;i++){
				Apply apply = list1.get(i);
				session.delete(apply);
			}
			tx.commit();
			flag = true;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public Boolean rejectBorrow(int bid){
		Boolean flag = false;
		System.out.println("rejectBorrowStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql1 = "FROM Apply WHERE bid = ?";
			@SuppressWarnings("unchecked")
			List<Apply> list1 = session.createQuery(hql1).setLong(0, bid).list();
			int num = list1.size();
			if(num>0){
				Apply apply = list1.get(0);
				session.delete(apply);
				Book book = apply.getBook();
				book.setBookstatus(1);
				session.update(book);
				tx.commit();
				flag = true;
			}
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public int applyReturn(int uid, int bid){
		int flag = 0;
		System.out.println("applyReturnStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql1 = "FROM Book WHERE bid = ?";
			@SuppressWarnings("unchecked")
			List<Book> list = session.createQuery(hql1).setLong(0, bid).list();
			Book booktemp = list.get(0);
			
			Apply apply = new Apply();
			apply.setBook(booktemp);
			User user = new User(uid);
			apply.setUserByUid(user);
			User owner = booktemp.getUser();
			apply.setUserByOwnerid(owner);
			apply.setStatus("return");
			
			String hql2 = "FROM Apply WHERE uid = ? AND bid = ? AND ownerid = ? AND status = 'return'";
			@SuppressWarnings("unchecked")
			List<Apply> list2 = session.createQuery(hql2).setLong(0, uid).setLong(1,booktemp.getBid()).setLong(2, booktemp.getUser().getUid()).list();
			if(list2.size()==0){
				session.save(apply);
			}
					
			tx.commit();
			flag = 1;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public Boolean confirmReturn(int bid){
		Boolean flag = false;
		System.out.println("confirmReturnStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql1 = "FROM Apply WHERE bid = ?";
			@SuppressWarnings("unchecked")
			List<Apply> list1 = session.createQuery(hql1).setLong(0, bid).list();
			int num = list1.size();
			if(num>0){
				Apply apply = list1.get(0);
				Book book = getBookDetail(apply.getBook().getBid());
				book.setBookstatus(1);
				session.update(book);
				
				String hql2 = "FROM Borrow WHERE bid = ? AND borrowstatus = ?";
				@SuppressWarnings("unchecked")
				List<Borrow> list2 = session.createQuery(hql2).setLong(0, book.getBid()).setLong(1, 1).list();
				int num2 = list2.size();
				if(num2>0){
					Borrow borrow = list2.get(0);
					/*Date currentTime = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					String dateString = formatter.format(currentTime); */
					long date = System.currentTimeMillis() / 1000;
					long shouldReturnDate = borrow.getReturndate();
					borrow.setReturndate2(date);
					borrow.setBorrowstatus(2);
					session.update(borrow);
					
					if(date>shouldReturnDate){
						User user = getUserDetail(borrow.getUser().getUid());
						String reason = "逾期不还"+book.getBookname();
						Scorehistory scorehistory = new Scorehistory();
						scorehistory.setScoredate(date);
						scorehistory.setUser(user);
						scorehistory.setScore(-5);
						scorehistory.setReason(reason);
						session.save(scorehistory);
						
						int score = user.getScore()-5;
						user.setScore(score);
						session.update(user);
					}	
				
					flag = true;
				}
				session.delete(apply);
				tx.commit();
			}			
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public Boolean addComment(int uid, int bid, int star, String string){
		Boolean flag = false;
		System.out.println("addUserStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			/*Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String dateString = formatter.format(currentTime); */
			long date = System.currentTimeMillis() / 1000;
			
			Comment comment = new Comment();
			Book book = new Book();
			String hql = "FROM Book WHERE bid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Book> list = session.createQuery(hql).setLong(0, bid).list();
			book = list.get(0);
			User user = getUserDetail(uid);
			comment.setBook(book);
			comment.setUser(user);
			comment.setStar(star);
			comment.setComment(string);
			comment.setCommentdate(date);	
			session.save(comment);
			
			String reason = "评论图书"+book.getBookname();
			Scorehistory scorehistory = new Scorehistory();
			scorehistory.setScoredate(date);
			scorehistory.setUser(user);
			scorehistory.setScore(5);
			scorehistory.setReason(reason);
			session.save(scorehistory);
			
			int score = user.getScore()+5;
			user.setScore(score);
			session.update(user);
			
			tx.commit();
			flag = true;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public User getUserDetail(int uid){
		User user = new User();
		System.out.println("getUserDetailStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM User WHERE uid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql).setLong(0, uid).list();		
			int num = list.size();
			if(num > 0){
				user = list.get(0);
			}
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return user;		
	}
	
	public Book getBookDetail(int bid){
		Book book = new Book();
		System.out.println("getBookDetailStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Book WHERE bid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Book> list = session.createQuery(hql).setLong(0, bid).list();		
			int num = list.size();
			if(num > 0){
				book = list.get(0);
			}
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return book;		
	}
	
	public List<Comment> getComment(int bid){
		List<Comment> returnList = new ArrayList<Comment>();
		System.out.println("getCommentStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Comment WHERE bid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Comment> list = session.createQuery(hql).setLong(0, bid).list();		
			returnList = list;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return returnList;		
	}
	
	public List<Borrow> getBorrowHistory(){
		List<Borrow> returnList = new ArrayList<Borrow>();
		System.out.println("getBorrowHistoryStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Borrow";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Borrow> list = session.createQuery(hql).list();		
			returnList = list;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return returnList;		
	}
	
	public List<Borrow> getUserBorrows(int uid){
		List<Borrow> returnList = new ArrayList<Borrow>();
		System.out.println("getBorrowHistoryStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Borrow WHERE uid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Borrow> list = session.createQuery(hql).setLong(0, uid).list();		
			returnList = list;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return returnList;		
	}
	
	public List<Book> getAllBooks(){
		List<Book> returnList = new ArrayList<Book>();
		System.out.println("getAllBooksStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Book order by cid";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Book> list = session.createQuery(hql).list();		
			returnList = list;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return returnList;		
	}
	
	public List<Scorehistory> getScoreHistory(int uid){
		List<Scorehistory> returnList = new ArrayList<Scorehistory>();
		System.out.println("getScoreHistoryStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Scorehistory WHERE uid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Scorehistory> list = session.createQuery(hql).setLong(0, uid).list();		
			returnList = list;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return returnList;		
	}
	
	public Boolean changeScore(int uid,int score,String reason){
		Boolean flag = false;
		System.out.println("changeScoreStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			/*Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String dateString = formatter.format(currentTime);*/ 
			long date = System.currentTimeMillis() / 1000;
			
			Scorehistory scorehistory = new Scorehistory();
			scorehistory.setReason(reason);
			scorehistory.setScore(score);
			scorehistory.setScoredate(date);
			User user = new User(uid);
			scorehistory.setUser(user);
			session.save(scorehistory);
			
			/*
			 * next change the score in the User table
			 */ 
			String hql = "FROM User WHERE uid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql).setLong(0, uid).list();
			User userTemp = list.get(0);
			int scoreTemp = userTemp.getScore() + score;
			userTemp.setScore(scoreTemp);
			session.update(userTemp);
			
			tx.commit();
			flag = true;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public List<Book> searchBook(String keyname,int type){
		List<Book> returnList = new ArrayList<Book>();
		System.out.println("searchBookStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			String keyvalue = "%"+keyname+"%";
			if(type == 1){
				//search by book name
				String hql = "FROM Book WHERE bookname LIKE ?"; 				 
				@SuppressWarnings("unchecked")
				List<Book> list = session.createQuery(hql).setString(0, keyvalue).list(); 		 
				returnList = list;
			}else if(type == 2){
				//search by author
				String hql = "FROM Book WHERE author LIKE ?"; 				 
				@SuppressWarnings("unchecked")
				List<Book> list = session.createQuery(hql).setString(0, keyvalue).list();
				returnList = list;
			}else if(type == 3){
				//search by category
				String hql = "FROM Category WHERE categoryname LIKE ?"; 				 
				@SuppressWarnings("unchecked")
				List<Category> list = session.createQuery(hql).setString(0, keyvalue).list(); 		 
				for(int i=0;i<list.size();i++){
					int cid = list.get(i).getCid();
					String hql1 = "FROM Book WHERE cid = ?"; 				 
					@SuppressWarnings("unchecked")
					List<Book> list1 = session.createQuery(hql1).setLong(0, cid).list();
					for(int j=0;j<list1.size();j++){
						returnList.add(list1.get(j));
					}
				}
			}else if(type == 4){
				//search by owner
				String hql = "FROM User WHERE username LIKE ?"; 				 
				@SuppressWarnings("unchecked")
				List<User> list = session.createQuery(hql).setString(0, keyvalue).list(); 		 
				for(int i=0;i<list.size();i++){
					int uid = list.get(i).getUid();
					String hql1 = "FROM Book WHERE uid = ?"; 				 
					@SuppressWarnings("unchecked")
					List<Book> list1 = session.createQuery(hql1).setLong(0, uid).list();
					for(int j=0;j<list1.size();j++){
						returnList.add(list1.get(j));
					}
				}
			}
			
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return returnList;		
	}
	
	public int getBookStatus(int bid){
		int flag = 0;
		System.out.println("addUserStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Book WHERE bid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Book> list = session.createQuery(hql).setLong(0, bid).list();
			int num = list.size();
			if (num>0){
				Book book = list.get(0);
				flag = book.getBookstatus();
			}
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;		
	}
	
	public Boolean checkBorrowed(int uid, int bid){
		Boolean flag = false;
		System.out.println("checkUserStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Borrow WHERE uid = ? AND bid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Borrow> list = session.createQuery(hql).setLong(0, uid).setLong(1,bid).list();		
			int num = list.size();
			
			if(num>0){
				flag = true;
			}
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;
	}
	
	public List<Book> getMyBook(int uid){
		List<Book> myBook = new ArrayList<Book>();
		System.out.println("checkUserStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Book WHERE uid=? ";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Book> list = session.createQuery(hql).setLong(0, uid).list();		
			
			myBook = list;
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return myBook;
	}
	
	public List<Book> getMyBorrow(int uid){
		List<Book> myBorrow = new ArrayList<Book>();
		System.out.println("checkUserStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Borrow WHERE uid=? AND borrowstatus=1 ";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Borrow> list = session.createQuery(hql).setLong(0, uid).list();		
			int num = list.size();
			for(int i=0;i<num;i++){
				Borrow borrowtemp = list.get(i);
				Book bookTemp = getBookDetail(borrowtemp.getBook().getBid());
				myBorrow.add(bookTemp);
			}
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return myBorrow;
	}
	
	public List<Borrow> getOneBorrow(int bid){
		List<Borrow> Borrows = new ArrayList<Borrow>();
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Borrow WHERE bid=? AND borrowstatus=1 ";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Borrow> list = session.createQuery(hql).setLong(0, bid).list();		
			Borrows = list;
			
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return Borrows;
	}
	
	public List<Borrow> getSpecialBorrow(int bid,int status){
		List<Borrow> Borrows = new ArrayList<Borrow>();
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Borrow WHERE bid=? AND borrowstatus=? ";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Borrow> list = session.createQuery(hql).setLong(0, bid).setLong(1, status).list();		
			Borrows = list;
			
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return Borrows;
	}
	
	public List<User> getAllUsers(int s){
		List<User> allUsers = new ArrayList<User>();
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			String hql = "";
			if(s==0){
				hql = "FROM User Order By uid";
			}else if(s==1){
				hql = "FROM User Order By score DESC";
			}
			
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql).list();
			for(int i=0;i<list.size();i++){
				User user = list.get(i);
				if(user.getUid()==888888){
					list.remove(i);
					break;
				}
			}
			allUsers = list;
			
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return allUsers;
	}
	
	public List<Apply> getMsgApply(int uid){
		//read hibernate.cfg.xml file
		List<Apply> applyList = new ArrayList<Apply>();
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Apply WHERE ownerid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Apply> list = session.createQuery(hql).setLong(0, uid).list();		
			applyList = list;
			
//			String hql1 = "FROM Apply WHERE uid = ? AND status = bring";
//			@SuppressWarnings("unchecked")
//			List<Apply> list1 = session.createQuery(hql1).setLong(0, uid).list();
//			applyList.addAll(list1);
			
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return applyList;
	}
	
	public List<Borrow> getMsgReturn(int uid){
		//read hibernate.cfg.xml file
		List<Borrow> BorrowList = new ArrayList<Borrow>();
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Borrow WHERE uid = ? AND borrowstatus = 1";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Borrow> list = session.createQuery(hql).setLong(0, uid).list();
			for(int i=0;i<list.size();i++){
				Borrow borrow = list.get(i);
				long currentDate = System.currentTimeMillis() / 1000;
				long difference = borrow.getReturndate()-currentDate;
				if(difference<259200){
					BorrowList.add(borrow);
				}
			}		
			
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return BorrowList;
	}

	public Boolean changeStuPwd(int uid,String password){
		Boolean flag = false;
		System.out.println("setPasswordStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM User WHERE uid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<User> list = session.createQuery(hql).setLong(0, uid).list();		
			if(list.size()>0){
				User user = list.get(0);
				user.setPassword(password);
				session.update(user);
				flag = true;
			}
			
			tx.commit();
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return flag;	
		
	}
	
	public Category getCategory(int cid){
		Category category = new Category();
		System.out.println("setPasswordStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Category WHERE cid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Category> list = session.createQuery(hql).setLong(0, cid).list();		
			if(list.size()>0){
				category = list.get(0);
			}
			
			tx.commit();
	    	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return category;	
		
	}
	
	public User getApplyUseer(int bid){
		User userReturn = new User();
		System.out.println("setPasswordStart");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM User u left join u.appliesForUid a left join a.book b WHERE b.bid = ?";
			System.out.println(hql);
			List list = session.createQuery(hql).setLong(0, bid).list();		
			Iterator it=list.iterator();
			while(it.hasNext()){  
                Object[] obj=(Object[])it.next();  
                User user=(User)obj[0];
                if(user!=null){
                	userReturn = user;
                }
          }     	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return userReturn;	
		
	}
	
	public ArrayList<Apply> getCheckApply(int uid){
		ArrayList<Apply> listReturn = new ArrayList<Apply>();
		System.out.println("getCheckApply");
		//read hibernate.cfg.xml file
		System.out.println("0"); 
	    Configuration cfg = new Configuration().configure();  
	    System.out.println("1");  
	    //build SessionFactory  
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
	    SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);  
	    System.out.println("2");  
	    //get session  
	    Session session = null;
	    Transaction tx = null;
		try{
			session = factory.openSession();
			tx = session.beginTransaction();
			
			String hql = "FROM Apply where uid = ?";
			System.out.println(hql);
			@SuppressWarnings("unchecked")
			List<Apply> list = session.createQuery(hql).setLong(0, uid).list();		
			for(int i=0;i<list.size();i++){
				if(list.get(i).getStatus().equals("borrow")||list.get(i).getStatus().equals("return"))
					listReturn.add(list.get(i));
			}
             	
	    }catch(Exception e){
	        e.printStackTrace();
	        tx.rollback();
	     }finally{
	    	session.close();
		    factory.close();
	     }
		System.out.println("Success");		
		return listReturn;	
		
	}
}
