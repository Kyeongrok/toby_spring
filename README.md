# toby_spring


### SimpleConnectionMaker 클래스

```java
public class SimpleConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/springbook", "root", "12345678");
        return c;
    }
}
```
