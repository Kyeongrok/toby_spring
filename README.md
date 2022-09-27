# Toby's Spring3

DAOFactory까지 적용된 버전

Spring Framework은 포함 하지 않았습니다.

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
