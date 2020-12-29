import java.sql.*;
public class FuncionarioDAO {
    public Funcionario funcionario;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;

    public static final byte INCLUSAO = 1;
    public static final byte ALTERACAO = 2;
    public static final byte EXCLUSAO = 3;
    
    public FuncionarioDAO(){
        bd = new BD();
        funcionario = new Funcionario();
    }
    
    public boolean localizar(){
        sql = "SELECT * FROM funcionario WHERE codigo = ?";
        try{
            statement = bd.connection.prepareStatement(sql);
            statement.setString(1, funcionario.getCodigo());
            resultSet = statement.executeQuery();
            resultSet.next();
            funcionario.setCodigo(resultSet.getString(1));
            funcionario.setNome(resultSet.getString(2));
            funcionario.setTelefone(resultSet.getString(3));
            return true;
        } catch(SQLException erro){
            return false;
        }
    }
    
    public String atualizar (int operacao){
        men = "Operação realizada com sucesso";
        try{
            if(operacao == INCLUSAO){
                sql = "INSERT INTO funcionario VALUES (?,?,?)";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, funcionario.getCodigo());
                statement.setString(2, funcionario.getNome());
                statement.setString(3, funcionario.getTelefone());
            } else if (operacao == ALTERACAO){
                sql = "UPDATE funcionario SET nome = ?, telefone WHERE codigo = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, funcionario.getNome());
                statement.setString(2, funcionario.getTelefone());
                statement.setString(5, funcionario.getCodigo());
            } else if (operacao == EXCLUSAO){
                sql = "DELETE FROM funcionario WHERE codigo = ?";
                statement = bd.connection.prepareStatement(sql);
                statement.setString(1, funcionario.getCodigo());
            }
            if(statement.executeUpdate() == 0){
                men = "Falha na operação!";
            }
        } catch(SQLException erro){
            men = "Falha na operação" + erro.toString();
        }
        return men;
    }
    
}
