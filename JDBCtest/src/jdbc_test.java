import java.sql.*;
import java.util.Scanner;

public class jdbc_test
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*Class.forName("com.mysql.jdbc.Driver");
        //获取数据库链接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/asd?useSSL=false","root","123456");
        //定义SQL语句
        String sql="update user set name = 'yilth' where id=1";
        //获取执行SQL的对象
        Statement statement = conn.createStatement();
        //执行SQL，并接受返回结果
        int count =statement.executeUpdate(sql);
        //处理结果
        System.out.println(count);
        //释放资源
        statement.close();
        conn.close();*/

        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username=sc.nextLine();
        System.out.println("请输入密码");
        String password=sc.nextLine();

        boolean loginFlag=new  jdbc_test().login(username,password);
        if (loginFlag)
        {
            System.out.println("登录成功");
        }else
        {
            System.out.println("失败");
        }
    }

    public boolean login(String username,String password)
    {
        if (username==null||password==null)
        {
            return false;
        }
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            connection=JDBCutils.getConnection();
            //定义SQL
            String sql="select * from user where name = '"+username+"' and password = '"+password+"'";
            //获取执行SQL对象
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCutils.close(resultSet,statement,connection);
        }
        return false;
    }
}
