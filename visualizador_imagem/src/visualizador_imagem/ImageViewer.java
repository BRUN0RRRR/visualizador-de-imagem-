import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageViewer extends JFrame {

    private JLabel imageLabel; // Área de visualização da imagem
    private JButton loadButton; // Botão para carregar a imagem
    private JButton clearButton; // Botão para limpar a imagem

    public ImageViewer() {
        // Configurações da janela principal
        setTitle("Visualizador de Imagens");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cria os botões
        loadButton = new JButton("Carregar Imagem");
        clearButton = new JButton("Limpar Imagem");

        // Cria a área de visualização da imagem
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(clearButton);

        // Adiciona a área de visualização e o painel de botões à janela
        add(buttonPanel, BorderLayout.SOUTH);
        add(imageLabel, BorderLayout.CENTER);

        // Ação do botão Carregar
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage();
            }
        });

        // Ação do botão Limpar
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearImage();
            }
        });
    }

    // Método para carregar a imagem
    private void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());

            // Ajuste da imagem para caber no JLabel
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }

    // Método para limpar a imagem exibida
    private void clearImage() {
        imageLabel.setIcon(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageViewer viewer = new ImageViewer();
            viewer.setVisible(true);
        });
    }
}
