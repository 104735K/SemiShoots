package net.user.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.core.Action;
import net.core.ActionForward;
import net.user.db.BusinessUserBean;
import net.user.db.UserBean;
import net.user.db.UserDAO;

public class UserSignupProcessAction extends HttpServlet implements Action {
	private static final long serialVersionUID = 1L;

	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String key = req.getParameter("key");
		HttpSession session = req.getSession();
		
		//key값이 들어오는 경우는 email verifycode를 검증하는 경우.
		if(key != null && !key.trim().equals("")) {
			verifyCheck(key, (String)session.getAttribute("verifyNum"), resp);
			return null;
		}
		
		//유효성 검증 및 회원가입 처리. id가 null 이 아닐때만 검증.
		String id = req.getParameter("id");
		System.out.println("state :    -    "+req.getParameter("state") + "//////"+id);
		if(id != null) {
			if(req.getParameter("state").equals("regular")) {
				UserBean userBean = new UserBean();
				//idx
				userBean.setId(req.getParameter("id"));
				userBean.setPassword(req.getParameter("pwd"));
				userBean.setName(req.getParameter("name"));
				userBean.setRRN(Integer.parseInt(req.getParameter("RRN")));
				userBean.setGender(Integer.parseInt(req.getParameter("gender")));
				userBean.setTel(req.getParameter("tel"));
				userBean.setEmail(req.getParameter("email"));
				userBean.setNickname(req.getParameter("nickname"));
				//userBean.setUserfile(req.getParameter("profile"));
				int result = 0;
				result = new UserDAO().insertUser(userBean);
				System.out.println(result);
				resp.setContentType("application/json; charset=UTF-8");
				if(result == 1) {
					System.out.println("r user insert successed");
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.getWriter().println("{\"message\":\"회원가입에 성공하셨습니다!\"}");
					return null;
				}
				else {
					System.out.println("r user insert failed");
					resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					resp.getWriter().println("{\"message\":\"회원가입에 실패하셨습니다!\"}");
					return null;
				}
			}
			
			else if(req.getParameter("state").equals("business")) {
				BusinessUserBean userBean = new BusinessUserBean();
				//id pwd business-name business-number tel email post address+adressDetail description business_file
				userBean.setBusiness_id(req.getParameter("id"));
				userBean.setPassword(req.getParameter("pwd"));
				userBean.setBusiness_name(req.getParameter("business-name"));
				userBean.setBusiness_number(Long.parseLong(req.getParameter("business-number")));
				userBean.setTel(Integer.parseInt(req.getParameter("tel")));
				userBean.setEmail(req.getParameter("email"));
				userBean.setPost(Integer.parseInt(req.getParameter("postcode")));
				userBean.setAddress(req.getParameter("address") + " " +req.getParameter("addressDetail"));
				userBean.setDescription(req.getParameter("description"));
				userBean.setBusiness_file(req.getParameter("business_file"));
				int result = 0;
				result = new UserDAO().insertUser(userBean);
				System.out.println(result);
				resp.setContentType("application/json; charset=UTF-8");
				if(result == 1) {
					System.out.println("b user insert successed");
					resp.setStatus(HttpServletResponse.SC_OK);
					resp.getWriter().println("{\"message\":\"기업 회원가입에 성공하셨습니다!\"}");
					return null;
				}
				else {
					System.out.println("b user insert failed");
					resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					resp.getWriter().println("{\"message\":\"기업 회원가입에 실패하셨습니다!\"}");
					return null;
				}
			}
			
			
		}//if id!=null

		//receiver값이 들어오는 경우는 email verifycode를 검증하는 경우.
		String receiver = req.getParameter("email");
		System.out.println(req.getParameter("email"));
		if(receiver != null) {
			SecureRandom random = new SecureRandom();
			String verifyNum = String.valueOf(100000 + random.nextInt(900000));//인증용 난수
			String subject = "shoots email verification : " + verifyNum;
			String imgPath =  req.getServletContext().getRealPath("/img/logo.png");

			String domain = "naver.com";
			final String username = "shoots1126@"+ domain; // 실제 아이디
			final String password = "shoots11262"; // 실제 비밀번호

			String host = "smtp."+ domain;
			String sender = username;
			sendEmail(req, resp, host, username, password, sender, receiver, subject, imgPath, verifyNum);
			session.setAttribute("verifyNum", verifyNum);
			return null;
		}
		return null;
	}//ActionForward execute

	private void verifyCheck(String key, String target, HttpServletResponse resp) throws IOException {
		System.out.println("key2 : "+ target);
		System.out.println("key : "+ key);
		resp.setContentType("application/json; charset=UTF-8");
		if(key.equals(target)) {
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().println("{\"message\":\"verify code 통과!\"}");
		}
		else {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().println("{\"message\":\"verify code 불일치!\"}");
		}
		
	}


	
	private void sendEmail(HttpServletRequest req, HttpServletResponse resp, String host, String username, String password, String sender, String receiver, String subject, String imgPath, String verifyNum) {
		System.out.println("sendEmail()");
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true"); // TLS 활성화
		props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // TLS 버전 지정

		// 세션 생성
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			 // 이미지 파일을 Base64로 인코딩
	        String base64Image = encodeFileToBase64(imgPath);
	        // HTML 내용 설정
	        String htmlContent = "<h3>This is your verification code</h3>" 
	        					+"<h1>"+verifyNum+"</h1>"
	        					+"<img src = 'data:image/jpeg;base64,"+base64Image+"'/><hr>";
			// 메일 메시지 생성
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sender)); // 보내는 사람 이름 설정
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
			message.setSubject(subject, "UTF-8"); // 제목 설정
			message.setContent(htmlContent, "text/html; charset=UTF-8"); // 본문 내용 설정
			resp.setContentType("text/html; charset=UTF-8");
			
			// 메일 전송
			Transport.send(message);
			resp.setContentType("application/json; charset=UTF-8");
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().println("{\"message\":\"메일이 성공적으로 발송되었습니다!\"}");

		} catch (MessagingException e) {
			e.printStackTrace();
			try {
				resp.setContentType("application/json; charset=UTF-8");
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				resp.getWriter().println("{\"message\":\"메일 발송 중 오류가 발생했습니다: " + e.getMessage() + "\"}");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			req.removeAttribute("email");
		}
	}
	
    // 파일을 Base64로 인코딩하는 메소드
   
	private static String encodeFileToBase64(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] fileContent = new byte[(int) file.length()];
        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(fileContent);
        }
        return Base64.getEncoder().encodeToString(fileContent);
    }


}