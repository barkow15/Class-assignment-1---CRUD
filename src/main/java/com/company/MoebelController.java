package com.company;

import java.sql.SQLException;

public class MoebelController {
    DatabaseModel dbModel;

    public MoebelController() throws SQLException, ClassNotFoundException {
        this.dbModel = new DatabaseModel();
     }

}
