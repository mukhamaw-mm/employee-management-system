# employee-managemnt-system
* This project is mini backend project for Employee Management System.

## Technology
Language - Java
Building - Maven
Framework - Spring boot + JPA + Hibernate
Database - PostgreSQL
External Tools - Lombok

## Prerequisite
* Java 8
* Maven
* PostgreSQL  (username - postgres and password - root or you can change your db username and password under application.properties)
* Postman (for testing api)

## Instruction

* You can download postman exported file 'employee-mgmt-system-postman-collection.json' under project and import in your postman for testing purpose.

* You can run my application in your local with your IDE or you can run my application in docker(pls check out dockerfile).

* After importing my postman collection,

	* Firstly, you need to add sample data for role, department and position by calling APIs which I already drop as api in my collection.

	* After that, you can register (add) employee into system.
	* My system will auto generate Employee Id for you, it will be unique and format look like (EMP_10001, EMP_10002, etc).

	* You can update some employee information via Postman too.
	* You can search all employee or search by filer like by name, by employee id, by department and by email.

	* You can add and deduct leave balance to employee.

	* You can delete employee after he resigned.
	* My system will delete as logically, not physically. So that we can get back every employee information.

* External Feature
    * You can cooperate my system with Frontend and you can build mini HR system.
    * My system provide Login, Change Password APIs. 
    * System have Role and you can filter by role to every employee in Frontend.
    * Admin can manage employee (add, delete, leave process).
    * Employee can view his/ her information and others information, but they shouldn't has permission to manage other and his information. 
    

* If any concerns, you can reach me at mukhamaw@gmail.com or linked Id - https://www.linkedin.com/in/mu-kham-aw-b12860196