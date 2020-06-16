package com.java.login.dao.daoimpl;

import com.java.login.dao.daohelper.JDBCConstants;
import com.java.login.dao.daohelper.JDBCHelper;
import com.java.login.entity.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCDaoImpl {
    public static final String INSERT_SQL_QUERY     = "INSERT INTO "+ JDBCConstants.TABLE_NAME +"(ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD) VALUES(?,?,?,?,?)";
    public static final String UPDATE_SQL_QUERY     = "UPDATE "+ JDBCConstants.TABLE_NAME +" SET FIRST_NAME=? WHERE ID=?";
    public static final String SELECT_SQL_QUERY     = "SELECT ID,FIRST_NAME,LAST_NAME,EMAIL,USERNAME FROM "+ JDBCConstants.TABLE_NAME +" WHERE ID=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT ID,FIRST_NAME,LAST_NAME,EMAIL,UserName FROM "+ JDBCConstants.TABLE_NAME +"";
    public static final String DELETE_SQL_QUERY     = "DELETE FROM "+ JDBCConstants.TABLE_NAME +" WHERE ID=?";
    public static final String DELETE_ALL_SQL_QUERY = "DELETE FROM "+ JDBCConstants.TABLE_NAME +"";

    public static Person getPerson(int id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Person person = new Person();

        try{
            con = JDBCHelper.getConnection();
            if ( con == null )
            {
                System.out.println( "Error getting the connection. Please check if the DB server is running" );
                return person;
            }
            ps = con.prepareStatement( SELECT_SQL_QUERY );
            ps.setLong( 1, id );
            rs = ps.executeQuery();
            System.out.println( "retrivePerson => " + ps.toString() );
            while ( rs.next() )
            {
                person.setId( rs.getLong( "ID" ) );
                person.setFirstName( rs.getString( "FIRST_NAME" ) );
                person.setLastName( rs.getString( "LAST_NAME" ) );
                person.setEmail( rs.getString( 4 ) ); // this is to show that we can retrive using index of the column
            }
        }
        catch (SQLException e){
            System.out.println("FOR GET CONNECTION IN PERSON " + e);
        }
        finally {
            try{
                JDBCHelper.closeResultSet( rs );
                JDBCHelper.closePrepaerdStatement( ps );
                JDBCHelper.closeConnection( con );
            }catch (SQLException e){
                System.out.println("FOR CLOSE CONNECTION IN PERSON" + e);
            }
        }

        return person;
    }

    public static List<Person> getPersons(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Person> persons = new ArrayList<Person>();
        try{
            con = JDBCHelper.getConnection();
            if(con == null){
                System.out.println("Error Getting Connection");
                return persons;
            }
            ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
            rs = ps.executeQuery();

            while (rs.next()){
                Person p = new Person();
                p.setId( rs.getLong( "ID" ) );
                p.setFirstName( rs.getString( "FIRST_NAME" ) );
                p.setLastName( rs.getString( "LAST_NAME" ) );
                p.setEmail( rs.getString( 4 ) );
                p.setUserName("USERNAME");
                persons.add(p);
            }

        }
        catch (SQLException e){
            System.out.println("FOR GET CONNECTION in PROFILE " + e);
        }
        finally
        {
            try
            {
                JDBCHelper.closeResultSet( rs );
                JDBCHelper.closePrepaerdStatement( ps );
                JDBCHelper.closeConnection( con );
            }
            catch ( SQLException e )
            {
                System.out.println("FOR GET Close CONNECTION in PROFILE" + e);
            }
        }

        return persons;
    }
}
