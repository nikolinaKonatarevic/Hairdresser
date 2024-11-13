/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nikolina
 */
public interface GenericEntity extends Serializable {

    String getTableName();

    String getColumnNamesFromInsert();

    String getInsertValues();

    void setId(Long id);

    String getSelectValues();

    GenericEntity getNewObject(ResultSet rs) throws SQLException;

    String getDeleteAndUpdateCondition(Object object);

    String getUpdateSetValues(Object object);
}
