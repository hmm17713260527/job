package com.dj.job.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import java.util.Date;
import java.util.Properties;

public class EmailUtil {

	// �����˵������ǳ�
	private static final String nickname = "hzm";
	// ����������
	private static final String username = "2101265151@qq.com";
	// ������������������е���Ȩ��
	private static final String password = "cuekzsehpjyvbdij";
	// �ʼ��ķ���Э��
	private static final String protocol = "smtp";
	// ����������� SMTP ��������ַ, ����׼ȷ, ��ͬ�ʼ���������ַ��ͬ, һ���ʽΪ: smtp.xxx.com
	private static final String host = "smtp.qq.com";
	// �ʼ��ļ����˿ں�
	private static final String port = "25";

	/*
	 * main����ʵ���ʼ�����
	 */
	public static void main(String[] args) {
		// �ռ��������ַ
		String to = "2101265151@qq.com";
		// �ʼ�������
		String subject = "HelloWorld";
		// �ʼ����ĵ��ı�����
		String body = "<h1>HelloWorld</h1>";
		// ��װ�ʼ�ʵ��
		// �����ʼ�
		if (sendEmail(to, subject, body)) {
			System.out.println("�ʼ����ͳɹ�!");
		} else {
			System.out.println("�ʼ�����ʧ��!�뼰ʱ�����");
		}
	}

	/**
	 * �����ʼ�
	 * 
	 * @return �Ƿ��ͳɹ� TRUE ���ͳɹ� FALSE ����ʧ��
	 */
	public static boolean sendEmail(String to, String subject, String content) {
		try {
			// ����Sessionʵ������
			/*
			 * Session�����ڶ�������JavaMailӦ�ó�������Ҫ�Ļ�����Ϣ�� �Լ��ռ��ͻ������ʼ������������������ӻỰ��Ϣ�����ʼ�
			 * �������������š��˿ںš����õ��ʼ����ͺͽ��յ�Э��ȡ�
			 */
			Session session = createSession();
			session.setDebug(true);
			// ����MimeMessageʵ������
			
			MimeMessage message = createMessage(session, to, subject, content);

			// �����ʼ�
			/*
			 * send����ִ���ʼ���������ʱ�������ȴӲ���Message�����л��Session����
			 * Ȼ�����Session.getTransport����������ڷ����ʼ��ʼ���Transportʵ������
			 * ����ʹ�ñ�����Session�����е�������������ص�JavaMail���ԣ�����Transport
			 * �����connect���������ʼ���������Ȼ�����Transport�����sendMessage����
			 * �����ʼ���������close�����Ͽ����ʼ������������ӡ�
			 */
			Transport.send(message);
			return true;
		} catch (Exception e) {
			System.out.println("�����ʼ��쳣==��");
			e.printStackTrace();
			return false;
		} finally {
			System.out.println("�ʼ����ͽ���...");
		}
	}

	/**
	 * �������ʼ��������ĻỰ����
	 * 
	 * @return �������ĻỰ����
	 */
	public static Session createSession() {
		// ��װ���Բ���
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", protocol); // �ʼ������Э��
		props.setProperty("mail.smtp.host", host); // �ʼ��ķ�����
		props.setProperty("mail.smtp.port", port); // �ʼ��ļ����˿�
		props.setProperty("mail.smtp.auth", "true"); // �Ƿ���Ҫ��֤����ΪTRUE��ʹ����Ȩ�뷢���ʼ���Ҫ��֤
		// ��ȡ��������ĻỰSession����
		/*
		 * getInstance��getDefaultInstance��Session�ľ�̬������������������ȡSession���ʵ������ �����������������ڣ�
		 * getDefaultInstance��������һ��Session����󣬽������Session��������ΪĬ�ϵ�Session����
		 * �Ժ�ÿһ�ε���getDefaultInstance�������᷵�����Ĭ��Session����
		 * ��getInstance��������ÿ�ε��ö��᷵��һ���µ�Session����
		 */
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// ��½�˺ż����룬������Ҫ�ǵ�������Ȩ��
				/*
				 * ����Session������ע���Authenticator���󣬴��л�ȡ���û���֤��Ϣ�󴫵ݸ��ʼ�������
				 * PasswordAuthentication����ָ�����û��������봴��ʵ������
				 */
				return new PasswordAuthentication(username, password);
			}
		});

		return session;
	}

	/**
	 * �����ʼ��ĺ�������
	 * 
	 * @param session ������������ĻỰ����
	 * @return ���ɵ�MimeMessageʵ������
	 * @throws Exception
	 */
	public static MimeMessage createMessage(Session session, String to, 
			String subject, String content) throws Exception {
		// ����MimeMessageʵ�����󣺱�ʾ�����ʼ�
		MimeMessage message = new MimeMessage(session);

		// ���÷�����
		if (!nickname.isEmpty()) {
			// �Զ��巢�����ǳ�
			message.setFrom(new InternetAddress(MimeUtility.encodeText(nickname) + " <" + username + ">"));
		} else {
			message.setFrom(new InternetAddress(username));
		}

		// �����ռ���
		// InternetAddress���ʾ�����ʼ��ĵ�ַ
		// Message.RecipientType��ʾ�ռ��˵����ͣ�����Message���е�һ����̬�ڲ���
		// TO �ռ��� CC ������ BCC ������
		message.setRecipients(Message.RecipientType.TO, to);
		// ����������
		/*
		 * String[] bccEmails = email.getBccEmails(); if (bccEmails != null &&
		 * bccEmails.length != 0) { InternetAddress[] internetAddressBCC = new
		 * InternetAddress[bccEmails.length]; for (int i = 0; i < bccEmails.length; i++)
		 * { internetAddressBCC[i] = new InternetAddress(bccEmails[i]); }
		 * message.setRecipients(Message.RecipientType.BCC, internetAddressBCC); }
		 */
		// ���÷�������
		message.setSentDate(new Date());

		// �����ʼ�����
		message.setSubject(subject);

		// ���ô��ı����ʼ�����
		message.setContent(content, "text/html; charset=UTF-8");
		//message.setText(email.getContent());

		// ���沢�������յ��ʼ�����
		message.saveChanges();

		return message;
	}
}
