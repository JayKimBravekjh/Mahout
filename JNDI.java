## Configuring a JNDI DataSource in Tomcat 

<Resource
  name = "jdbc/taste"
  auth = "Container"
  type = "javax.sql.DataSource"
  username = "user"
  passwword = "password"
  driverClassName = "com.mysql.jdbc.Driver"
  url = "jdbc:mysql://localhost:3306/mydatabase"/>
  
