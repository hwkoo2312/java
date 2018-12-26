package com.jsp.jdbc;

import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {
	@Override

	public void init() throws ServletException {

		loadJDBCDriver();

		initConnectionPool();

	}

 

	private void loadJDBCDriver() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException ex) {

			throw new RuntimeException("fail to load JDBC Driver", ex);

		}

	}

	

	private void initConnectionPool() {

		try {

			String jdbcDriver = "jdbc:mysql://192.168.99.100:3306/JSP_STUDY?" 
					+"useUnicode=true&autoReconnect=true&useTimezone=true&serverTimezone=UTC&connectTimeout=3000&useSSL=false&noAccessToProcedureBodies=true&characterEncoding=utf8";
			String dbUser = "hwkoo";
			String dbPass = "3927";

 

			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcDriver, dbUser, dbPass);

 

			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);

			poolableConnFactory.setValidationQuery("select 1");

 

			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);

			poolConfig.setTestWhileIdle(true);

			/**

			 * 

    		testOnBorrow: 커넥션 풀에서 커넥션을 얻어올 때 테스트 실행(기본값: true)

    		testOnReturn: 커넥션 풀로 커넥션을 반환할 때 테스트 실행(기본값: false)

    		testWhileIdle: Evictor 스레드가 실행될 때 (timeBetweenEvictionRunMillis > 0) 커넥션 풀 안에 있는 유휴 상태의 커넥션을 대상으로 테스트 실행(기본값: false)

			 */

			

			/**

			 * 속성 이름 		설명

				initialSize 		BasicDataSource 클래스 생성 후 최초로 getConnection() 메서드를 호출할 때 커넥션 풀에 채워 넣을 커넥션 개수

				maxActive (dbcp2.x maxTotal)	동시에 사용할 수 있는 최대 커넥션 개수(기본값: 8) 							

				maxIdle 		커넥션 풀에 반납할 때 최대로 유지될 수 있는 커넥션 개수(기본값: 8)

				minIdle 			최소한으로 유지할 커넥션 개수(기본값: 0) 

			 */

			poolConfig.setMinIdle(4);

			poolConfig.setMaxTotal(50);

 

			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);

			poolableConnFactory.setPool(connectionPool);

			

			Class.forName("org.apache.commons.dbcp2.PoolingDriver");

			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");

			driver.registerPool("jspstudy", connectionPool);

			System.out.println("initConnectionPool init success");

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}

}