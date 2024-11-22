/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domain.GenericEntity;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.swing.text.html.parser.Entity;
import repository.DBConnectionFactory;
import repository.DbRepository;

/**
 *
 * @author Nikolina
 */
public class RepositoryDBGeneric implements DbRepository<GenericEntity> {

    @Override
    public List<GenericEntity> get(GenericEntity entity) throws Exception {
        List<GenericEntity> entities = new ArrayList<>();
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            //System.out.println(entity);
            sb.append(entity.getSelectValues());
            String query = sb.toString();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                entities.add(entity.getNewObject(rs));
            }
            return entities;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;

        }

    }

    @Override
    public GenericEntity create(GenericEntity entity) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(entity.getColumnNamesFromInsert())
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");
            String query = sb.toString();
            Statement statement = connection.createStatement();
            System.out.println(query);
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Long id = rs.getLong(1);
                entity.setId(id);
            }
            statement.close();
            rs.close();
            System.out.println("Posle rs.close" + query);
            return entity;

        } catch (Exception ex) {
            
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public GenericEntity update(GenericEntity entity) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(entity.getTableName())
                    .append(" SET ")
                    .append(entity.getUpdateSetValues(entity))
                    .append(" WHERE ")
                    .append(entity.getDeleteAndUpdateCondition(entity));
            String query = sb.toString();
            System.out.println("Posle st.close" + query);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            return entity;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    @Override
    public boolean delete(GenericEntity entity) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(entity.getTableName())
                    .append(" WHERE ")
                    .append(entity.getDeleteAndUpdateCondition(entity));
            Statement statement = connection.createStatement();
            String query = sb.toString();
            statement.executeUpdate(query);
            statement.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;

        }
    }

    @Override
    public GenericEntity getById(GenericEntity entity) throws Exception {
        try {
            Connection connection = DBConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append(entity.getSelectValues())
                    .append(" WHERE ")
                    .append(entity.getDeleteAndUpdateCondition(entity));
            String query = sb.toString();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                GenericEntity e = entity.getNewObject(rs);
                return e;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;

        }
    }

}
