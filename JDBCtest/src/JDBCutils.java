import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCutils
{
    private static  String url;
    private static  String user;
    private static  String password;
    private static  String driver;

    //读文件，只读一次
    static {
        try {
            //创建Properties集合类
            Properties pro=new Properties();
            ClassLoader classLoader=JDBCutils.class.getClassLoader();
            URL res =classLoader.getResource("jdbc.properties");
            String path=res.getPath();
            System.out.println(path);
            //加载文件
            try {
                pro.load(new FileReader(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //获取数值
            url =pro.getProperty("url");
            user=pro.getProperty("user");
            password=pro.getProperty("password");
            driver=pro.getProperty("driver");

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取链接
    public  static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
    //释放资源
    public  static  void close(ResultSet rs, Statement stmt,Connection conn)
    {
        if (rs!=null)
        {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stmt!=null)
        {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn!=null)
        {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
