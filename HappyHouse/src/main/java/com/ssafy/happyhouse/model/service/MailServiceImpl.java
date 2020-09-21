package com.ssafy.happyhouse.model.service;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.model.dto.AccountDto;
import com.ssafy.happyhouse.model.dto.NewsDto;
import com.ssafy.happyhouse.model.repo.AccountDao;
import com.ssafy.happyhouse.model.repo.NewsDao;

@Component
public class MailServiceImpl implements MailService {

	@Autowired
	private AccountDao accDao;

	@Autowired
	private NewsDao newsDao;

	@Autowired(required = false)
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Scheduled(cron="0 0 12 * * ?")
//	@Scheduled(fixedDelay = 500000)
	public void emailScheduler() {
		try {
			List<String> userlist = accDao.getUserEmail();
			sendNewsMail(userlist.toArray(new String[userlist.size()]), newsDao.getDailyNews());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendWelcomeMail(AccountDto acc, HttpServletRequest request) {
		String title = "Welcome, HappyHouse!";
		String tomail = acc.getEmail();
		String to_msg = "Welcome, " + acc.getUserid() + "!";

		StringBuffer sb = new StringBuffer();
		sb.append(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><!--[if IE]><html xmlns=\"http://www.w3.org/1999/xhtml\" class=\"ie\"><![endif]--><!--[if !IE]><!--><html style=\"margin: 0;padding: 0;\" xmlns=\"http://www.w3.org/1999/xhtml\"><!--<![endif]--><head>\n"
						+ "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
						+ "  </head>\n"
						+ "   <body class=\"full-padding\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;\">\n"
						+ "<!--<![endif]-->\n"
						+ "    <table class=\"wrapper\" style=\"border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;background-color: #f6f6f6;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tbody><tr><td>\n"
						+ "      <div role=\"banner\">\n"
						+ "        <div class=\"preheader\" style=\"Margin: 0 auto;max-width: 560px;min-width: 280px; width: 280px;width: calc(28000% - 167440px);\">\n"
						+ "          <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n"
						+ "          <!--[if (mso)|(IE)]><table align=\"center\" class=\"preheader\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr><td style=\"width: 280px\" valign=\"top\"><![endif]-->\n"
						+ "            <div class=\"snippet\" style=\"display: table-cell;Float: left;font-size: 12px;line-height: 19px;max-width: 280px;min-width: 140px; width: 140px;width: calc(14000% - 78120px);padding: 10px 0 5px 0;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;\">\n"
						+ "              \n" + "            </div>\n"
						+ "          <!--[if (mso)|(IE)]></td><td style=\"width: 280px\" valign=\"top\"><![endif]-->\n"
						+ "            <div class=\"webversion\" style=\"display: table-cell;Float: left;font-size: 12px;line-height: 19px;max-width: 280px;min-width: 139px; width: 139px;width: calc(14100% - 78680px);padding: 10px 0 5px 0;text-align: right;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;\">\n"
						+ "              \n" + "            </div>\n"
						+ "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "          </div>\n"
						+ "        </div>\n"
						+ "        <div class=\"header\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\" id=\"emb-email-header-container\">\n"
						+ "        <!--[if (mso)|(IE)]><table align=\"center\" class=\"header\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr><td style=\"width: 600px\"><![endif]-->\n"
						+ "          <div class=\"logo emb-logo-margin-box\" style=\"font-size: 26px;line-height: 32px;Margin-top: 6px;Margin-bottom: 20px;color: #c3ced9;font-family: Roboto,Tahoma,sans-serif;Margin-left: 20px;Margin-right: 20px;\" align=\"center\">\n"
						+ "            <div class=\"logo-center\" align=\"center\" id=\"emb-email-header\"></div>\n"
						+ "          </div>\n" + "        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n"
						+ "        </div>\n" + "      </div>\n" + "      <div role=\"section\">\n"
						+ "      <div class=\"layout one-col fixed-width\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n"
						+ "        <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;background-color: #ffffff;\" emb-background-style>\n"
						+ "        <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-fixed-width\" emb-background-style><td style=\"width: 600px\" class=\"w560\"><![endif]-->\n"
						+ "          <div class=\"column\" style=\"text-align: left;color: #8e8e8e;font-size: 16px;line-height: 24px;font-family: PT Sans,Trebuchet MS,sans-serif;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\">\n"
						+ "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 24px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;mso-text-raise: 4px;\">\n"
						+ "        <p style=\"Margin-top: 0;Margin-bottom: 20px;text-align: center;\">" + to_msg
						+ "</p>\n" + "      </div>\n" + "    </div>\n" + "        \n"
						+ "        <div style=\"font-size: 12px;font-style: normal;font-weight: normal;line-height: 19px;\" align=\"center\">\n"
						+ "          <img style=\"border: 0;display: block;height: auto;width: 100%;max-width: 81px;\" alt=\"\" width=\"81\" src=\"https://i.imgur.com/IjH65Q8.png\" />\n"
						+ "        </div>\n" + "      \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;line-height: 20px;font-size: 1px;\">&nbsp;</div>\n"
						+ "    </div>\n" + "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;line-height: 25px;font-size: 1px;\">&nbsp;</div>\n"
						+ "    </div>\n" + "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;mso-text-raise: 4px;\">\n"
						+ "        <p class=\"size-16\" style=\"Margin-top: 0;Margin-bottom: 20px;font-size: 16px;line-height: 24px;text-align: center;\" lang=\"x-size-16\">Oh늘의 하우스, HappyHouse에 가입하신 것을 환영합니다!</p>\n"
						+ "      </div>\n" + "    </div>\n" + "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;line-height: 20px;font-size: 1px;\">&nbsp;</div>\n"
						+ "    </div>\n" + "        \n"
						+ "        <div style=\"font-size: 12px;font-style: normal;font-weight: normal;line-height: 19px;\" align=\"center\">\n"
						+ "          <img style=\"border: 0;display: block;height: auto;width: 100%;max-width: 174px;\" alt=\"\" width=\"174\" src=\"https://i.imgur.com/ss56Krv.png\" />\n");
		sb.append("On HappyHouse, you can..." + "      \n"
				+ "<p style=\"Margin-top: 20px;Margin-bottom: 0;\">주택 거래내역 조회</p>");
		sb.append("- 4가지 타입으로 거래내역을 조회");
		sb.append("<p style=\"Margin-top: 20px;Margin-bottom: 0;\">최신 뉴스 모음</p>");
		sb.append("- 주택, 부동산 관련 최신 뉴스 모음");
		sb.append("<p style=\"Margin-top: 20px;Margin-bottom: 0;\">관심 지역 설정</p>");
		sb.append("- 관심 있는 지역을 빠르게 조회" + "<p></p><table style=\'text-align:center\'><tr><td></td><tr>");
		sb.append("<p style=\"Margin-top: 20px;Margin-bottom: 0;\">'start happyhouse' 버튼을 누르시면 회원가입이 완료됩니다!</p>");
		sb.append("<a href='http://localhost:8080" + request.getContextPath() + "/checkuser?user_id=" + acc.getUserid()
				+ "&user_key=" + acc.getStatus()
				+ "'>start happyhouse</a><p></p><table style=\\'text-align:center\\'><tr><td></td><tr>");
		sb.append("        </table><p></p></div>\n" + "      \n" + "          </div>\n"
				+ "        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "        </div>\n"
				+ "      </div>\n");

		sb.append("      <div style=\"mso-line-height-rule: exactly;\" role=\"contentinfo\">\n"
				+ "        <div class=\"layout email-footer\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n"
				+ "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n"
				+ "          <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-email-footer\"><td style=\"width: 400px;\" valign=\"top\" class=\"w360\"><![endif]-->\n"
				+ "            <div class=\"column wide\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;Float: left;max-width: 400px;min-width: 320px; width: 320px;width: calc(8000% - 47600px);\">\n"
				+ "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n"
				+ "                \n" + "                <div style=\"font-size: 12px;line-height: 19px;\">\n"
				+ "                  <div>GSITM</div>\n" + "                </div>\n"
				+ "                <div style=\"font-size: 12px;line-height: 19px;Margin-top: 18px;\">\n"
				+ "                  <div>&#51060; &#47700;&#51068;&#51008; &#48156;&#49888;&#51204;&#50857;&#47700;&#51068;&#51077;&#45768;&#45796;.</div>\n"
				+ "                </div>\n" + "                <!--[if mso]>&nbsp;<![endif]-->\n"
				+ "              </div>\n" + "            </div>\n"
				+ "          <!--[if (mso)|(IE)]></td><td style=\"width: 200px;\" valign=\"top\" class=\"w160\"><![endif]-->\n"
				+ "            <div class=\"column narrow\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\n"
				+ "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n"
				+ "                \n" + "              </div>\n" + "            </div>\n"
				+ "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "          </div>\n"
				+ "        </div>\n"
				+ "        <div class=\"layout one-col email-footer\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n"
				+ "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n"
				+ "          <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-email-footer\"><td style=\"width: 600px;\" class=\"w560\"><![endif]-->\n"
				+ "            <div class=\"column\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\">\n"
				+ "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n"
				+ "                <div style=\"font-size: 12px;line-height: 19px;\">\n"
				+ "                  <unsubscribe style=\"text-decoration: underline;\">Unsubscribe</unsubscribe>\n"
				+ "                </div>\n" + "              </div>\n" + "            </div>\n"
				+ "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "          </div>\n"
				+ "        </div>\n" + "      </div>\n"
				+ "      <div style=\"mso-line-height-rule: exactly;line-height: 40px;font-size: 40px;\">&nbsp;</div>\n"
				+ "    </div></td></tr></tbody></table>\n" + "  \n" + "</body></html>");

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(sender);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
			messageHelper.setText(sb.toString(), true);

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findPasswdMail(AccountDto acc, HttpServletRequest request) {
		String title = "비밀번호 찾기 서비스";
		String tomail = acc.getEmail();
		String to_msg = "안녕하세요, " + acc.getUserid() + " 님!";

		StringBuffer sb = new StringBuffer();
		sb.append(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><!--[if IE]><html xmlns=\"http://www.w3.org/1999/xhtml\" class=\"ie\"><![endif]--><!--[if !IE]><!--><html style=\"margin: 0;padding: 0;\" xmlns=\"http://www.w3.org/1999/xhtml\"><!--<![endif]--><head>\n"
						+ "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
						+ "  </head>\n"
						+ "   <body class=\"full-padding\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;\">\n"
						+ "<!--<![endif]-->\n"
						+ "    <table class=\"wrapper\" style=\"border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;background-color: #f6f6f6;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tbody><tr><td>\n"
						+ "      <div role=\"banner\">\n"
						+ "        <div class=\"preheader\" style=\"Margin: 0 auto;max-width: 560px;min-width: 280px; width: 280px;width: calc(28000% - 167440px);\">\n"
						+ "          <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n"
						+ "          <!--[if (mso)|(IE)]><table align=\"center\" class=\"preheader\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr><td style=\"width: 280px\" valign=\"top\"><![endif]-->\n"
						+ "            <div class=\"snippet\" style=\"display: table-cell;Float: left;font-size: 12px;line-height: 19px;max-width: 280px;min-width: 140px; width: 140px;width: calc(14000% - 78120px);padding: 10px 0 5px 0;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;\">\n"
						+ "              \n" + "            </div>\n"
						+ "          <!--[if (mso)|(IE)]></td><td style=\"width: 280px\" valign=\"top\"><![endif]-->\n"
						+ "            <div class=\"webversion\" style=\"display: table-cell;Float: left;font-size: 12px;line-height: 19px;max-width: 280px;min-width: 139px; width: 139px;width: calc(14100% - 78680px);padding: 10px 0 5px 0;text-align: right;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;\">\n"
						+ "              \n" + "            </div>\n"
						+ "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "          </div>\n"
						+ "        </div>\n"
						+ "        <div class=\"header\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\" id=\"emb-email-header-container\">\n"
						+ "        <!--[if (mso)|(IE)]><table align=\"center\" class=\"header\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr><td style=\"width: 600px\"><![endif]-->\n"
						+ "          <div class=\"logo emb-logo-margin-box\" style=\"font-size: 26px;line-height: 32px;Margin-top: 6px;Margin-bottom: 20px;color: #c3ced9;font-family: Roboto,Tahoma,sans-serif;Margin-left: 20px;Margin-right: 20px;\" align=\"center\">\n"
						+ "            <div class=\"logo-center\" align=\"center\" id=\"emb-email-header\"></div>\n"
						+ "          </div>\n" + "        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n"
						+ "        </div>\n" + "      </div>\n" + "      <div role=\"section\">\n"
						+ "      <div class=\"layout one-col fixed-width\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n"
						+ "        <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;background-color: #ffffff;\" emb-background-style>\n"
						+ "        <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-fixed-width\" emb-background-style><td style=\"width: 600px\" class=\"w560\"><![endif]-->\n"
						+ "          <div class=\"column\" style=\"text-align: left;color: #8e8e8e;font-size: 16px;line-height: 24px;font-family: PT Sans,Trebuchet MS,sans-serif;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\">\n"
						+ "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 24px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;mso-text-raise: 4px;\">\n"
						+ "        <p style=\"Margin-top: 0;Margin-bottom: 20px;text-align: center;\">" + to_msg
						+ "</p>\n" + "      </div>\n" + "    </div>\n" + "        \n"
						+ "        <div style=\"font-size: 12px;font-style: normal;font-weight: normal;line-height: 19px;\" align=\"center\">\n"
						+ "          <img style=\"border: 0;display: block;height: auto;width: 100%;max-width: 81px;\" alt=\"\" width=\"81\" src=\"https://i.imgur.com/IjH65Q8.png\" />\n"
						+ "        </div>\n" + "      \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;line-height: 20px;font-size: 1px;\">&nbsp;</div>\n"
						+ "    </div>\n" + "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;line-height: 25px;font-size: 1px;\">&nbsp;</div>\n"
						+ "    </div>\n" + "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;mso-text-raise: 4px;\">\n"
						+ "        <p class=\"size-16\" style=\"Margin-top: 0;Margin-bottom: 20px;font-size: 16px;line-height: 24px;text-align: center;\" lang=\"x-size-16\">Oh늘의 하우스를 이용해주셔서 감사합니다!</p>\n"
						+ "      </div>\n" + "    </div>\n" + "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;line-height: 20px;font-size: 1px;\">&nbsp;</div>\n"
						+ "    </div>\n" + "        \n"
						+ "        <div style=\"font-size: 12px;font-style: normal;font-weight: normal;line-height: 19px;\" align=\"center\">\n"
						+ "          <img style=\"border: 0;display: block;height: auto;width: 100%;max-width: 174px;\" alt=\"\" width=\"174\" src=\"https://i.imgur.com/ss56Krv.png\" />\n");
		sb.append("회원님의 비밀번호는" + "      \n" + "<p style=\"Margin-top: 20px;Margin-bottom: 0;\">" + acc.getPasswd()
				+ "</p>");
		sb.append("입니다.");
		sb.append("<p style=\"Margin-top: 20px;Margin-bottom: 0;\"></p>");
		sb.append("tip) 보안을 위해 3개월에 한 번 비밀번호를 바꿔주세요!");
		sb.append(
				"<p style=\"Margin-top: 20px;Margin-bottom: 0;\">'start happyhouse' 버튼을 누르시면 'Oh늘의집' 사이트로 이동합니다.</p>");
		sb.append("<a href='http://localhost:8080" + request.getContextPath()
				+ "/'>start happyhouse</a><p></p><table style=\\'text-align:center\\'><tr><td></td><tr>");
		sb.append("        </table><p></p></div>\n" + "      \n" + "          </div>\n"
				+ "        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "        </div>\n"
				+ "      </div>\n");

		sb.append("      <div style=\"mso-line-height-rule: exactly;\" role=\"contentinfo\">\n"
				+ "        <div class=\"layout email-footer\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n"
				+ "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n"
				+ "          <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-email-footer\"><td style=\"width: 400px;\" valign=\"top\" class=\"w360\"><![endif]-->\n"
				+ "            <div class=\"column wide\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;Float: left;max-width: 400px;min-width: 320px; width: 320px;width: calc(8000% - 47600px);\">\n"
				+ "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n"
				+ "                \n" + "                <div style=\"font-size: 12px;line-height: 19px;\">\n"
				+ "                  <div>GSITM</div>\n" + "                </div>\n"
				+ "                <div style=\"font-size: 12px;line-height: 19px;Margin-top: 18px;\">\n"
				+ "                  <div>&#51060; &#47700;&#51068;&#51008; &#48156;&#49888;&#51204;&#50857;&#47700;&#51068;&#51077;&#45768;&#45796;.</div>\n"
				+ "                </div>\n" + "                <!--[if mso]>&nbsp;<![endif]-->\n"
				+ "              </div>\n" + "            </div>\n"
				+ "          <!--[if (mso)|(IE)]></td><td style=\"width: 200px;\" valign=\"top\" class=\"w160\"><![endif]-->\n"
				+ "            <div class=\"column narrow\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\n"
				+ "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n"
				+ "                \n" + "              </div>\n" + "            </div>\n"
				+ "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "          </div>\n"
				+ "        </div>\n"
				+ "        <div class=\"layout one-col email-footer\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n"
				+ "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n"
				+ "          <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-email-footer\"><td style=\"width: 600px;\" class=\"w560\"><![endif]-->\n"
				+ "            <div class=\"column\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\">\n"
				+ "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n"
				+ "                <div style=\"font-size: 12px;line-height: 19px;\">\n"
				+ "                  <unsubscribe style=\"text-decoration: underline;\">Unsubscribe</unsubscribe>\n"
				+ "                </div>\n" + "              </div>\n" + "            </div>\n"
				+ "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "          </div>\n"
				+ "        </div>\n" + "      </div>\n"
				+ "      <div style=\"mso-line-height-rule: exactly;line-height: 40px;font-size: 40px;\">&nbsp;</div>\n"
				+ "    </div></td></tr></tbody></table>\n" + "  \n" + "</body></html>");

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(sender);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
			messageHelper.setText(sb.toString(), true);

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendNewsMail(String[] userlist, List<NewsDto> news) {
		String title = "Oh!늘의집, 최신 News!";
		String to_msg = "오늘의집! 새로운 소식을 전해드립니다!";

		StringBuffer sb = new StringBuffer();
		sb.append(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><!--[if IE]><html xmlns=\"http://www.w3.org/1999/xhtml\" class=\"ie\"><![endif]--><!--[if !IE]><!--><html style=\"margin: 0;padding: 0;\" xmlns=\"http://www.w3.org/1999/xhtml\"><!--<![endif]--><head>\n"
						+ "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
						+ "  </head>\n"
						+ "   <body class=\"full-padding\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;\">\n"
						+ "<!--<![endif]-->\n"
						+ "    <table class=\"wrapper\" style=\"border-collapse: collapse;table-layout: fixed;min-width: 320px;width: 100%;background-color: #f6f6f6;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tbody><tr><td>\n"
						+ "      <div role=\"banner\">\n"
						+ "        <div class=\"preheader\" style=\"Margin: 0 auto;max-width: 560px;min-width: 280px; width: 280px;width: calc(28000% - 167440px);\">\n"
						+ "          <div style=\"border-collapse: collapse;display: table;width: 100%;\">\n"
						+ "          <!--[if (mso)|(IE)]><table align=\"center\" class=\"preheader\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr><td style=\"width: 280px\" valign=\"top\"><![endif]-->\n"
						+ "            <div class=\"snippet\" style=\"display: table-cell;Float: left;font-size: 12px;line-height: 19px;max-width: 280px;min-width: 140px; width: 140px;width: calc(14000% - 78120px);padding: 10px 0 5px 0;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;\">\n"
						+ "              \n" + "            </div>\n"
						+ "          <!--[if (mso)|(IE)]></td><td style=\"width: 280px\" valign=\"top\"><![endif]-->\n"
						+ "            <div class=\"webversion\" style=\"display: table-cell;Float: left;font-size: 12px;line-height: 19px;max-width: 280px;min-width: 139px; width: 139px;width: calc(14100% - 78680px);padding: 10px 0 5px 0;text-align: right;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;\">\n"
						+ "              \n" + "            </div>\n"
						+ "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "          </div>\n"
						+ "        </div>\n"
						+ "        <div class=\"header\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\" id=\"emb-email-header-container\">\n"
						+ "        <!--[if (mso)|(IE)]><table align=\"center\" class=\"header\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr><td style=\"width: 600px\"><![endif]-->\n"
						+ "          <div class=\"logo emb-logo-margin-box\" style=\"font-size: 26px;line-height: 32px;Margin-top: 6px;Margin-bottom: 20px;color: #c3ced9;font-family: Roboto,Tahoma,sans-serif;Margin-left: 20px;Margin-right: 20px;\" align=\"center\">\n"
						+ "            <div class=\"logo-center\" align=\"center\" id=\"emb-email-header\"></div>\n"
						+ "          </div>\n" + "        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n"
						+ "        </div>\n" + "      </div>\n" + "      <div role=\"section\">\n"
						+ "      <div class=\"layout one-col fixed-width\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n"
						+ "        <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;background-color: #ffffff;\" emb-background-style>\n"
						+ "        <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-fixed-width\" emb-background-style><td style=\"width: 600px\" class=\"w560\"><![endif]-->\n"
						+ "          <div class=\"column\" style=\"text-align: left;color: #8e8e8e;font-size: 16px;line-height: 24px;font-family: PT Sans,Trebuchet MS,sans-serif;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\">\n"
						+ "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 24px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;mso-text-raise: 4px;\">\n"
						+ "        <p style=\"Margin-top: 0;Margin-bottom: 20px;text-align: center;\">" + to_msg
						+ "</p>\n" + "      </div>\n" + "    </div>\n" + "        \n"
						+ "        <div style=\"font-size: 12px;font-style: normal;font-weight: normal;line-height: 19px;\" align=\"center\">\n"
						+ "          <img style=\"border: 0;display: block;height: auto;width: 100%;max-width: 81px;\" alt=\"\" width=\"81\" src=\"https://i.imgur.com/IjH65Q8.png\" />\n"
						+ "        </div>\n" + "      \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;line-height: 20px;font-size: 1px;\">&nbsp;</div>\n"
						+ "    </div>\n" + "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;line-height: 25px;font-size: 1px;\">&nbsp;</div>\n"
						+ "    </div>\n" + "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;mso-text-raise: 4px;\">\n"
						+ "        <p class=\"size-16\" style=\"Margin-top: 0;Margin-bottom: 20px;font-size: 16px;line-height: 24px;text-align: center;\" lang=\"x-size-16\"></p>\n"
						+ "      </div>\n" + "    </div>\n" + "        \n"
						+ "            <div style=\"Margin-left: 20px;Margin-right: 20px;\">\n"
						+ "      <div style=\"mso-line-height-rule: exactly;line-height: 20px;font-size: 1px;\">&nbsp;</div>\n"
						+ "    </div>\n" + "        \n"
						+ "        <div style=\"font-size: 12px;font-style: normal;font-weight: normal;line-height: 19px;\" align=\"center\">\n"
						+ "          <img style=\"border: 0;display: block;height: auto;width: 100%;max-width: 174px;\" alt=\"\" width=\"174\" src=\"https://i.imgur.com/ss56Krv.png\" />\n");
		sb.append("Oh!늘의집 - 최신 뉴스!" + "      \n");
		for (NewsDto n : news) {
			sb.append("<p style=\"Margin-top: 20px;Margin-bottom: 0;\"><a href='" + n.getLink() + "'>" + n.getTitle()
					+ "</a></p>");
			sb.append("- " + n.getDescription());
			sb.append("<p style=\"Margin-top: 20px;Margin-bottom: 0;\"></p>");
		}

		sb.append("<p style=\"Margin-top: 20px;Margin-bottom: 0;\">더 많은 뉴스를 보고 싶으시면 아래 버튼을 눌러주세요!</p>");
		sb.append("<a href='http://localhost:8080/happyhouse"
				+ "/'>start happyhouse</a><p></p><table style=\\'text-align:center\\'><tr><td></td><tr>");
		sb.append("        </table><p></p></div>\n" + "      \n" + "          </div>\n"
				+ "        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "        </div>\n"
				+ "      </div>\n");

		sb.append("      <div style=\"mso-line-height-rule: exactly;\" role=\"contentinfo\">\n"
				+ "        <div class=\"layout email-footer\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n"
				+ "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n"
				+ "          <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-email-footer\"><td style=\"width: 400px;\" valign=\"top\" class=\"w360\"><![endif]-->\n"
				+ "            <div class=\"column wide\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;Float: left;max-width: 400px;min-width: 320px; width: 320px;width: calc(8000% - 47600px);\">\n"
				+ "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n"
				+ "                \n" + "                <div style=\"font-size: 12px;line-height: 19px;\">\n"
				+ "                  <div>GSITM</div>\n" + "                </div>\n"
				+ "                <div style=\"font-size: 12px;line-height: 19px;Margin-top: 18px;\">\n"
				+ "                  <div>&#51060; &#47700;&#51068;&#51008; &#48156;&#49888;&#51204;&#50857;&#47700;&#51068;&#51077;&#45768;&#45796;.</div>\n"
				+ "                </div>\n" + "                <!--[if mso]>&nbsp;<![endif]-->\n"
				+ "              </div>\n" + "            </div>\n"
				+ "          <!--[if (mso)|(IE)]></td><td style=\"width: 200px;\" valign=\"top\" class=\"w160\"><![endif]-->\n"
				+ "            <div class=\"column narrow\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\n"
				+ "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n"
				+ "                \n" + "              </div>\n" + "            </div>\n"
				+ "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "          </div>\n"
				+ "        </div>\n"
				+ "        <div class=\"layout one-col email-footer\" style=\"Margin: 0 auto;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;\">\n"
				+ "          <div class=\"layout__inner\" style=\"border-collapse: collapse;display: table;width: 100%;\">\n"
				+ "          <!--[if (mso)|(IE)]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><tr class=\"layout-email-footer\"><td style=\"width: 600px;\" class=\"w560\"><![endif]-->\n"
				+ "            <div class=\"column\" style=\"text-align: left;font-size: 12px;line-height: 19px;color: #8e8e8e;font-family: PT Sans,Trebuchet MS,sans-serif;max-width: 600px;min-width: 320px; width: 320px;width: calc(28000% - 167400px);\">\n"
				+ "              <div style=\"Margin-left: 20px;Margin-right: 20px;Margin-top: 10px;Margin-bottom: 10px;\">\n"
				+ "                <div style=\"font-size: 12px;line-height: 19px;\">\n"
				+ "                  <unsubscribe style=\"text-decoration: underline;\">Unsubscribe</unsubscribe>\n"
				+ "                </div>\n" + "              </div>\n" + "            </div>\n"
				+ "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" + "          </div>\n"
				+ "        </div>\n" + "      </div>\n"
				+ "      <div style=\"mso-line-height-rule: exactly;line-height: 40px;font-size: 40px;\">&nbsp;</div>\n"
				+ "    </div></td></tr></tbody></table>\n" + "  \n" + "</body></html>");

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(sender);
			messageHelper.setTo(userlist);
			messageHelper.setSubject(title);
			messageHelper.setText(sb.toString(), true);

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
