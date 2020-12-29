import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiAddFunc extends JFrame{

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    
    private JButton btGravar;
    private JButton btAlterar;
    private JButton btExcluir;
    private JButton btNovo;
    private JButton btLocalizar;
    private JButton btSair;
    
    private JTextField tfCodigo;
    private JTextField tfNome;
    private JTextField tfTelefone;
    
    private FuncionarioDAO funcionarios;
    
    public static void main (String args[]){
        JFrame janela = new GuiAddFunc();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
    
    public GuiAddFunc(){
        inicializaComponentes();
        definirEventos();
    }
    
    private void inicializaComponentes(){
        setLayout(new FlowLayout (FlowLayout.LEFT));
        setTitle("Cadastro de Funcionarios");
        setBounds(200,400,610,120);
        
        label1 = new JLabel("Código");
        label2 = new JLabel("Nome");
        label3 = new JLabel("Telefone");
        
        tfCodigo = new JTextField(2);
        tfNome = new JTextField(20);
        tfTelefone = new JTextField(15);

        btGravar = new JButton("Gravar");        
        btAlterar = new JButton("Alterar");
        btExcluir = new JButton("Excluir");
        btLocalizar = new JButton("Localizar");
        btNovo = new JButton("Novo");
        btSair = new JButton("Sair");
        
        add(label1);
        add(tfCodigo);
        add(label2);
        add(tfNome);
        add(label3);
        add(tfTelefone);
        
        add(btNovo);
        add(btLocalizar);
        add(btGravar);
        add(btAlterar);
        add(btExcluir);
        add(btSair);
        
        setResizable(false);
    
        funcionarios = new FuncionarioDAO();
        if(!funcionarios.bd.getConnection()) {
            JOptionPane.showMessageDialog(null, "Falha ao conectar, o sistema será fechado!");
            System.exit(0);
        }
    }
    
    public void definirEventos(){
        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionarios.bd.close();
                System.exit(0);
            }
        });
        btNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfCodigo.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O código não pode ser vazio!");
                    tfCodigo.requestFocus();
                    return;
                }
                if(tfNome.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O nome não pode ser vazio!");
                    tfNome.requestFocus();
                    return;
                }
                if(tfTelefone.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "O telefone não pode ser vazio!");
                    tfTelefone.requestFocus();
                    return;
                }
                funcionarios.funcionario.setCodigo(tfCodigo.getText());
                funcionarios.funcionario.setNome(tfNome.getText());
                funcionarios.funcionario.setTelefone(tfTelefone.getText());
                JOptionPane.showMessageDialog(null, funcionarios.atualizar(FuncionarioDAO.INCLUSAO));
                limparCampos();
            }
        });
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionarios.funcionario.setCodigo(tfCodigo.getText());
                funcionarios.funcionario.setNome(tfNome.getText());
                funcionarios.funcionario.setTelefone(tfTelefone.getText());
                JOptionPane.showMessageDialog(null, funcionarios.atualizar(FuncionarioDAO.ALTERACAO));
                limparCampos();
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                funcionarios.funcionario.setCodigo(tfCodigo.getText());
                funcionarios.localizar();
                int n = JOptionPane.showConfirmDialog(null, funcionarios.funcionario.getNome(),"Excluir funcionario?", JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_NO_OPTION){
                    JOptionPane.showMessageDialog(null, funcionarios.atualizar(FuncionarioDAO.EXCLUSAO));
                    limparCampos();
                }
            }
        });
        btLocalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCampos();
            }
        });
    }
    
    public void limparCampos(){
        tfCodigo.setText("");
        tfNome.setText("");
        tfTelefone.setText("");
        tfCodigo.requestFocus();
    }
    
    public void atualizarCampos(){
        funcionarios.funcionario.setCodigo(tfCodigo.getText());
        if(funcionarios.localizar()){
            tfCodigo.setText(funcionarios.funcionario.getCodigo());
            tfNome.setText(funcionarios.funcionario.getNome());
            tfTelefone.setText(funcionarios.funcionario.getTelefone());
        } else{
            JOptionPane.showMessageDialog(null, "Funcionario não encontrado");
            limparCampos();
        }
    }
}
