package co.edu.unicauca.openmarket.access;

import java.sql.Connection;

/**
 * @author Ledy Astudillo
 * @author Santiago Nieto
 */
public interface IRepoDataBase{
    
    void initDatabase();

    void connect();

    void disconnect();
    
    Connection getConection();
}
