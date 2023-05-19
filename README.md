# Toby's Spring3

DAOFactory까지 적용된 버전 Toby의 스프링3의 ~94p

Spring Framework은 포함 하지 않았습니다.

## 들어가기 전에

Java DB연동 코드

```java
import java.sql.*;

public class ConnectChecker {
    public void check() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test-db1",
                "root", "12345678");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("show databases");
        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }
    public void select() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test-db1",
                "root", "12345678");

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from users");
        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            String str2 = rs.getString(2);
            String str3 = rs.getString(3);
            System.out.println(str + str2 + str3);
        }
    }

    public void add() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test-db1",
                "root", "12345678");

        PreparedStatement pstmt = con.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, "1");
        pstmt.setString(2, "kyeongrok");
        pstmt.setString(3, "12345678");
        pstmt.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectChecker cc = new ConnectChecker();
        cc.check();
        cc.add();
    }
}
```


```java
public class DaoFactory {
    private ConnectionMaker connectionMaker(){
        BinaryConnectionMaker binaryConnectionMaker =  new BinaryConnectionMaker();
        return binaryConnectionMaker;
    }
    public UserDao userDao(){
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }
}
```

#### Spring Framework 적용된 버전

https://github.com/Kyeongrok/toby-spring-template-callback



### 프로젝트 정보

Build : Gradle

DB Driver : MySql


### Connection을 DI받는 Dao

생성자를 통해 connection을 주입 받습니다.

```java
public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();

}
```


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
