import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;  //准备 - 导入数据库包

public class JDBCExample {
    public static void main(String[] args){
        //String JDriver = "com.mysql.jdbc.Driver";  错误旧
        String JDriver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String pwd = "123456";
        //定义JDBC的url对象
        String url = "jdbc:mysql://localhost:3306/demo2?useUnicode=true&characterEncoding=utf-8";
        try {            //第一步：加载（注册）数据库
            Class.forName(JDriver);
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println("无法加载JDBC驱动程序。" + e.getMessage());
        }
        Connection con = null;  //第二步：建立连接
        Statement s = null;        //声明Statement的对象
        try {            //连接数据库URL
            con = DriverManager.getConnection(url, user,pwd);
            //第三步：执行SQL语句。实例化Statement类对象
            s = con.createStatement();
            //使用SQL命令insert向表中插入一行数据
            String r1 = "insert into student values('5','张三','23','男','A')";
            s.executeUpdate(r1);
            System.out.println("插入数据成功！");
        } catch (SQLException e) {
            System.out.println("SQLException:" + e.getMessage());
        }finally { //无论try中是否发生异常，都会执行finally块，通常用于资源释放。
            try {
                if (s != null) {
                    s.close();
                    s = null;
                }
                if (con != null) {
                    con.close(); //关闭与数据库的连接
                    con = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
