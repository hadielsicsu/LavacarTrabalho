/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lavacar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 *
 * @author hadiel
 */
public class TelaLogin extends JFrame {

    static Armazenamento armazenamento = new Armazenamento();
    private JTextField nomeField;
    private JButton loginButton;

    public TelaLogin() {
        setTitle("Tela de Login");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Cliente cliente1 = new Cliente("Pedro", "Silva", "Masculino", "1234567890", "01/01/1990", "Rua A, 123");
        Veiculo veiculo1 = new Veiculo("Marca1", "Modelo1", 2020, "Vermelho", "ABC123");
        cliente1.setVeiculo(veiculo1);
        armazenamento.adicionarCliente(cliente1);

        Cliente cliente2 = new Cliente("Maria", "Souza", "Feminino", "9876543210", "02/02/1985", "Rua B, 456");
        Veiculo veiculo2 = new Veiculo("Marca2", "Modelo2", 2019, "Azul", "XYZ789");
        cliente2.setVeiculo(veiculo2);
        armazenamento.adicionarCliente(cliente2);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);

        URL imageUrl = getClass().getResource("/images/carro.png");
        if (imageUrl != null) {
            ImageIcon carroIcon = new ImageIcon(imageUrl);
            JLabel carroLabel = new JLabel(carroIcon);

            // Adicione o JLabel do carro ao seu painel
            panel.add(carroLabel, gbc);
        } else {
            System.err.println("Imagem do carro não encontrada.");
        }

        panel.add(new JLabel("Nome:"), gbc);
        nomeField = new JTextField();
        panel.add(nomeField, gbc);

        loginButton = new JButton("Login / Cadastro");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                // Verificar se o cliente já está cadastrado
                Cliente clienteExistente = buscarClientePorNome(nome);

                if (clienteExistente != null) {
                    // Cliente já cadastrado
                    JOptionPane.showMessageDialog(null, "Bem-vindo de volta, " + nome + "!");
                    dispose(); // Fecha a tela de login
                    Principal principal = new Principal(clienteExistente, armazenamento); // Abre a tela principal com o cliente existente
                    principal.pack();
                    principal.setLocationRelativeTo(null);
                    principal.setVisible(true);
                } else {
                    // Novo cliente
                    int resposta = JOptionPane.showConfirmDialog(null, "Você ainda não está cadastrado. Deseja se cadastrar?",
                            "Cadastro", JOptionPane.YES_NO_OPTION);

                    if (resposta == JOptionPane.YES_OPTION) {
                        // Redirecionar para a tela de cadastro
                        dispose(); // Fecha a tela de login
                        CadastroCliente cadastroCliente = new CadastroCliente(armazenamento); // Abre a tela de cadastro
                    }
                }
            }
        });
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(loginButton, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(null);        
        setVisible(true);
    }

    // Função para buscar um cliente pelo nome (simulação)
    private Cliente buscarClientePorNome(String nome) {
        for (Cliente cliente : armazenamento.obterTodosClientes()) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Macintosh".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin();
            }
        });
    }
}
