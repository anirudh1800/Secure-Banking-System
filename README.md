#Secure Banking System
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<meta name="generator" content="LibreOffice 5.0.2.2 (Linux)"/>
	<meta name="created" content="2015-11-29T20:37:46.672815026"/>
	<meta name="changed" content="2015-12-03T17:19:47.417660923"/>
</head>
<body lang="en-US" dir="ltr">
<div title="header">
	<p align="center" style="margin-bottom: 0.2in; line-height: 100%"><br/>
	</p>
</div>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
  <font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i><b>Abstract</b></i><b>–</b><b>Bank
of  </b><b>Arizona is a secure on-line banking web application
developed for individual customers, merchants and employees of a
bank. This application is developed with major focus on the security
of the application following secure software design principles. 
Application was designed adhering to the design guidelines and
required security features were implemented.  After the
implemen</b><b>ta</b><b>tion phase, security testing was performed to
expose the vulnerabilities present in the application. Several
discovered vulnerabilities were discussed and possible solutions to
patch the vulnerabilities in the application were also presented. </b></font></font>
</p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
  <font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i><b>Keywords</b></i><b>
– Software; Security; Web</b></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>I.
 INTRODUCTION</b></font></font></p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><br/>

</p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">Nowadays,
banking system has been more accessible than ever to perform
essential duties e.g  online transfer, change account information
without having the need to visit nearby bank and perform all of them
through online. While the online access to banking functionality for
users has given great flexibility but it requires sufficient security
provided by the banking company to help protect from unauthorized
access to user's accounts and also help the user to perform his
activity securely maintaining the privacy. The objective of this
project is to design such banking application securely according to
design guidelines set in the course and as well perform security
testing to expose the vulnerabilities.  In following sections, users
of the system and their functionality is presented. </span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">Application
f</span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">unctional</span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">ity
</span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">and
security aspect of the system are discussed in later sections.	</span></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>II.
 OVERVIEW</b></font></font></p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">The
Secure Online Banking System is a web application, compatible with
any web browser with access to Internet, providing online banking
services for the customers of the bank and also ensures security,
integrity and confidentiality of customer's activity and personal
information from unauthorized access. The system also provides
interface for the internal users; regular employees and system
manager of the bank to process all the customer transactions and
requests. Individual customers, merchants, regular employees, system
managers and system administrators can use this application. Each
type of user have their own functionality defined and interfaces to
perform the functionality. To make the application more secure,
SSL/TLS protocol is used for encryption of data transmitted between
client and server. A two-factor authentication is setup for
individual users to sign into the application. First factor is by
supplying username, password and second factor is using One Time
Password (OTP). An additional layer of security is to be provided to
customers to perform critical transactions using challenge response
authentication using public key infrastructure (PKI). </font></font>
</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>III.
USER CHARACTERISTICS</b></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><br/>
The
users of Secure Banking System are broadly categorized into two –
internal and external. Internal users are bank employees, consisting
of regular employee, system manager and system administrator. The
external users are individual customers of the bank or merchants or
organizations.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i><b>Individual
Customer</b></i></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">Individual
customers having bank accounts with Bank of Arizona are general end
users of the bank performing the usual bank operations like
debit/credit, funds transfer,</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">making
payments etc.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i><b>Merchant/
Organization or Business Customer</b></i></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">These
are specialized users, having account with bank primarily responsible
for client payment processing.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i><b>Regular
employees</b></i></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">They
are employees of the bank responsible for authorizing/ declining low
priority banking operations raised by external users.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i><b>System
manager</b></i></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">These
type of internal employees are responsible for higher priority
banking operations. Any transaction requests raised for more than
$500 are queued for system managers approval.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i><b>System
administrators</b></i></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">They
are responsible for granting privileges to internal employees and
maintain all the internal user accounts.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>IV.
 FUNCTIONAL FEATURES</b></font></font></p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><br/>

</p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>Core
Banking Functionality for each Internal user</b></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i>Debit
and Credit Funds</i>: Using this feature external user will be able
to deposit and withdraw money from their own account. </font></font>
</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i>Transfer
Fund</i>: With this functionality implemented, any external user can
securely transfer funds within his different types of accounts or
from his account to another user’s account.  Once a transfer fund
transaction is initiated by an external user above critical
transaction amount, a regular employee can approve or decline the
request based on the account’s funds availability. If the request
is granted the end users account is updated accordingly.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i>Payments</i>:
An individual external user can make payments to a merchandise (also
a customer of the bank). Once an end user initiates the request to
pay to a merchandise, the</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">request
is forwarded to the bank, and after proper authorization from
internal bank employee the payment is reflected in both individual
user and merchant’s account.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i>Banking
statements</i>: This feature will ensure that an external user will
be able to v his/her banking statements. </font></font>
</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>Banking
Services Access to Employees</b></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i>Technical
Account Access</i>: Through this interface, an internal employee with
proper privileges from the system administrator will be able to
access external users’ account in order to perform any maintenance
requests raised by end users.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><i>Transactions
Access</i>: This feature emphasizes that, once any transaction
request (Debit/Credit/Funds transfer/Make Payments) has been raised
by an end user internal users with proper privileges will be able to
approve or decline those requests. The critical transactions requests
will directly go to the system manager’s queue.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>V.
 SECURITY AND PRIVACY REQUIREMENTS</b></font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">1.
After 3 unsuccessful attempts of logging into account, password will
be reset and an email will be sent to the user to reset his password.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">2.
Passwords will not be stored in the database as plain text. Instead
they will be passed through standard MD5 hash function and digest is
stored in the database. Spring security is being used for user
password authentication in the login.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">3.
Input fields entered by the user into the website are validated
against suspicious scripts or values to prevent any Cross-Site
Scripting(XSS) attacks.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">4.
While logging in, an OTP will be generated and sent to the user’s
email to verify his authenticity. Generated OTP is a time based and
is valid for only 3 minutes.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">5.
 User is automatically logged out when inactive for more than 3
minutes in the website and session is invalidated.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">6.
Any monetary transaction involving more than critical amount will
require authentication from the user and this will be achieved using
public private key authentication  (PKI infrastructure will be used
to authenticate the request).  A public – private key is generated
for each individual customer during registration. Private key is used
by the user to authenticate critical transaction. Only public key is
stored at server side.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">7.
Web browser connects to the server using HTTPS connection. SSL
protocol is used to encrypt the transmission between the client and
server.</font></font></p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">8.
</span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">User
access the functionality based on their privileges assigned to </span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">his</span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">
role.</span></font></font></p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">9.
</span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">Prevent
Cross-Site Request Forgery (CSRF) on the web pages by maintaining
csrf token for each user session.</span></font></font></p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">1</span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">0.
Used Google Google </span></font></font><span style="font-variant: normal"><font color="#000000"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="letter-spacing: normal"><span style="font-style: normal"><span style="font-weight: normal">reCAPTCHA</span></span></span></font></font></font></span><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">
in user registration and forgot password web pages to defend against
abuse by bots. </span></font></font>
</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>VI.
 ENVIRONMENT</b></font></font></p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>Operating
System:</b> Windows 7</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>Web
browser:</b> Mozilla Firefox, Chrome and Internet Explorer 9 or
higher</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>Programming
Language:</b> Java and JavaScript</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>Database:</b>
MySQL  5.6 </font></font>
</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>Server:</b>
Apache Tomcat 8</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>Frameworks:</b>
Spring 4.0 , Hiberate ORM 4.3 and Bootstrap 3</font></font></p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><br/>

</p>
<p align="justify" style="margin-bottom: 0in; line-height: 100%"><br/>

</p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><b>VII.
 CONTRIBUTIONS</b></font></font></p>
<p align="center" style="margin-bottom: 0in; line-height: 100%"><br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">The
project was developed in eight member team and was completed in the
span of two months. Popular frameworks Spring[3] and Hibernate[8]
using Java are studied well and used in development of the project.
Various vulnerabilities[1] found in a web application are analyzed
and their solutions were studied. Vulnerability report is prepared
based on findings by external testers of the application. Security
mechanisms used in real world banking applications are also
researched. Learned to use appropriate security testing tools;
Postman[4], Chrome developer tools[9] and XSS Me[2]. Contribution of
each member is listed below:-</font></font></p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">Anirudh
 Gali(Team Leader) - Most of his contribution in the project
deals with designing the application, assigning the work to each team
member and scheduling the meetings. Apart from the leadership, He was
responsible for developing the modules - transaction processing,
employee services including User Interface and integration of Google
<span style="font-variant: normal"><font color="#000000"><span style="letter-spacing: normal"><span style="font-style: normal">reCAPTCHA</span></span></font></span><span style="font-variant: normal"><font color="#000000"><span style="letter-spacing: normal"><span style="font-style: normal">[7]</span></span></font></span><span style="font-variant: normal"><font color="#000000"><span style="letter-spacing: normal"><span style="font-style: normal">
</span></span></font></span>into the application.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">Chandrani
Mukherjee – Responsible for developing individual customer module
involving functionality -  debit, credit, transfer funds, payment to
merchant, authentication of critical transactions using PKI and
hibernate integration as well. </font></font>
</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">Vishnu
Priya Chandra Sekar – Responsible for developing Merchant services
merchant's user interface, registration, forgot password and  sign up
modules including user interfaces.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">Rutvik
Patel – Responsible for integration of Hibernate into the
application to query the database, setting up SSL configuration in
the Apache tomcat and JavaScript validation of client user
interfaces. </font></font>
</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">Sravani
Puttagunta – Responsible for designing the user guide of the
application and also wrote JavaScript client side validations for
each  Java server page. </font></font>
</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">Reshma
Venkatakrishna - Reponsible for implementation of OTP 2<sup>nd</sup>
factor authentication using Google authenticator's TOTP algorithm and
also coming up with the user guide.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">Sai
Harinya Turaga – Responsible for  designing user interfaces using
bootstrap, implementing forgot password module, creating test plan
and also security testing on all the web pages.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">Deepak
Parameshwaran - Responsible for  implementing forgot password module,
creating test plan and security testing on all the web pages to
discover any vulnerabilities.</font></font></p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
 
</p>
<p align="justify" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="center" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">REFERENCES</font></font></p>
<p align="center" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">[1]
Open Web Application Security Project. (n.d.). Retrieved December 1,
2015, from https://www.owasp.org/index.php/Main_Page</font></font></p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">[2]
XSS-Me | Security Compass. (n.d.). Retrieved December 1, 2015, from
http://labs.securitycompass.com/exploit-me/xss-me/</font></font></p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">[3]
Spring.io. (n.d.). Retrieved December 1, 2015, from
https://spring.io/</font></font></p>
<p align="left" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">[4]
</span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">Modern
software is built on APIs. (n.d.). Retrieved December 1, 2015, from
https://www.getpostman.com/	</span></font></font></p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">[5]
RFC 2818 - HTTP Over TLS. (n.d.). Retrieved May 24, 2015, from
https://tools.ietf.org/html/rfc2818</font></font></p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">[6]
Secure Banking System source code. (n.d.). Retrieved December 1,
2015, from https://github.com/anirudhgali/ Secure-Banking-System</font></font></p>
<p align="left" style="margin-bottom: 0in; line-height: 100%"><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">[</span></font></font><font face="Times New Roman, serif"><font size="2" style="font-size: 10pt"><span style="font-weight: normal">7]
ReCAPTCHA. (n.d.). Retrieved December 1, 2015, from
https://www.google.com/recaptcha/intro/index.html</span></font></font></p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">[8]
 Hibernate ORM. (n.d.). Retrieved December 1, 2015, from
http://hibernate.org/orm/</font></font></p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<font face="Times New Roman, serif"><font size="2" style="font-size: 10pt">[9]
Chrome DevTools. (n.d.). Retrieved December 1, 2015, from
https://developer.chrome.com/devtools</font></font></p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
<p align="left" style="margin-bottom: 0in; font-weight: normal; line-height: 100%">
<br/>

</p>
</body>
</html>
