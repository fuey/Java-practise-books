import demo.dao.MenuDao;
import demo.entity.MenuDO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 * Created by luyuanxing on 11/7/2018 0007.
 */
public class MybatisTest {
    public static void main(String[] args) {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream= Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            //原生sql方式执行
            //connection = sqlSession.getConnection();
            //preparedStatement = connection.prepareStatement("select * from sys_menu where menu_id = 1");
            //ResultSet resultSet = preparedStatement.executeQuery();
            //if (resultSet.next()) {
            //    long aLong = resultSet.getLong(1);
            //    System.out.println(aLong);
            //    String string = resultSet.getString(3);
            //    System.out.println(string);
            //}
//
            ////通过已经加载到内存的mapper的namespace+方法名定位xml中的sql执行
            Object o = sqlSession.selectOne("demo.entity.dao.MenuDao.get","1");
            System.out.println(o);
            MenuDao menuDao = sqlSession.getMapper(MenuDao.class);
            MenuDO menuDO = menuDao.get(1L);
            System.out.println(menuDO);

            //通过加载dao层对象和方法，定位mapper对应namespace下的sql执行
            //MenuDao menuDao1 = sqlSession.getMapper(MenuDao.class);
            //MenuDO menuById = menuDao1.getMenuById(1L);
            //System.out.println(menuById);


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(sqlSession != null){
                sqlSession.close();
            }


        }


    }
}
