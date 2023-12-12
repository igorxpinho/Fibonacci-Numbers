import java.sql.*;

public class Model
{
    public static String selSql(String strBusca) {
        String status = "Não encontrada";
        try {
            Connection conn = MySQLConnector.conectar();
            String strSqlSelect = "SELECT * from `teste`.`tbl_usuarios` WHERE `usuario` = '" + strBusca + "';";
            Statement stmSql = conn.createStatement();
            ResultSet rsSql = stmSql.executeQuery(strSqlSelect);
            while (rsSql.next()) {
                status = rsSql.getString("senha");
            }
            stmSql.close();
            rsSql.close();
        } catch (SQLException e) {
            status = "Erro SQLException: " + e;
        } catch (Exception e) {
            status = "Erro Exception: " + e;
        }
        return status;
    }
}