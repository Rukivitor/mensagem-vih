import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class DesculpaParaVih extends JFrame {
    
    private JButton botaoDesculpa;
    private JLabel mensagemLabel;
    private JLabel imagemLabel;
    private JLabel imagemGatinhoLabel;
    private JPanel painelConteudo;
    private BufferedImage imagemCoracao;
    private ImageIcon gifGatinho;
    
    public DesculpaParaVih() {
        super("Mensagem para Vih");
        carregarImagem();
        carregarGifGatinho();
        configurarJanela();
        criarInterface();
    }
    
    private void carregarImagem() {
        try {
            // Tenta carregar a imagem do diretório de downloads
            File arquivoImagem = new File("C:\\Users\\Usuário\\Downloads.jpeg");
            if (arquivoImagem.exists()) {
                imagemCoracao = ImageIO.read(arquivoImagem);
            } else {
                // Se não encontrar a imagem específica, tenta encontrar qualquer imagem JPEG no diretório
                File diretorioDownloads = new File("C:\\Users\\Usuário\\Downloads");
                File[] arquivos = diretorioDownloads.listFiles((dir, name) -> 
                    name.toLowerCase().endsWith(".jpeg") || 
                    name.toLowerCase().endsWith(".jpg") || 
                    name.toLowerCase().endsWith(".png"));
                
                if (arquivos != null && arquivos.length > 0) {
                    // Usa a primeira imagem encontrada
                    imagemCoracao = ImageIO.read(arquivos[0]);
                } else {
                    // Se não encontrar nenhuma imagem, cria um coração simples usando desenho
                    criarCoracaoSimples();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Em caso de erro, cria um coração simples usando desenho
            criarCoracaoSimples();
        }
    }
    
    private void carregarGifGatinho() {
        try {
            String caminhoGif = "C:\\Users\\Usuário\\Downloads\\e14392b3bb9f745025a5cf6a9abe11be.gif";
            File arquivoGif = new File(caminhoGif);
            
            System.out.println("Tentando carregar GIF de: " + caminhoGif);
            System.out.println("Arquivo existe? " + arquivoGif.exists());
            
            if (arquivoGif.exists()) {
                // Tenta carregar diretamente com URL
                gifGatinho = new ImageIcon(arquivoGif.toURI().toURL());
                System.out.println("GIF carregado com sucesso!");
                
                // Verifica se a imagem foi carregada corretamente
                if (gifGatinho.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    System.out.println("Dimensões originais do GIF: " + 
                        gifGatinho.getIconWidth() + "x" + gifGatinho.getIconHeight());
                    
                    // Redimensiona o GIF mantendo a animação
                    Image img = gifGatinho.getImage();
                    Image newImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    gifGatinho = new ImageIcon(newImg);
                    
                    System.out.println("GIF redimensionado com sucesso!");
                } else {
                    System.out.println("Erro ao carregar a imagem do GIF!");
                }
            } else {
                System.out.println("Arquivo GIF não encontrado!");
                // Tenta listar os arquivos no diretório para debug
                File dir = new File("C:\\Users\\Usuário\\Downloads");
                File[] arquivos = dir.listFiles();
                if (arquivos != null) {
                    System.out.println("Arquivos encontrados no diretório:");
                    for (File f : arquivos) {
                        if (f.getName().endsWith(".gif")) {
                            System.out.println(f.getName());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar o GIF: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void criarCoracaoSimples() {
        imagemCoracao = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imagemCoracao.createGraphics();
        g2d.setColor(Color.RED);
        g2d.fillOval(20, 20, 30, 30);
        g2d.fillOval(50, 20, 30, 30);
        int[] xPoints = {20, 50, 80, 50, 20};
        int[] yPoints = {50, 80, 50, 50, 50};
        g2d.fillPolygon(xPoints, yPoints, 5);
        g2d.dispose();
    }
    
    private void configurarJanela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(255, 192, 203)); // Rosa claro
    }
    
    private void criarInterface() {
        // Configuração do layout
        setLayout(new BorderLayout(10, 10));
        
        // Criação do painel de conteúdo
        painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBackground(new Color(255, 192, 203)); // Rosa claro
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Adiciona o título
        mensagemLabel = new JLabel("Querida Vih,");
        mensagemLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mensagemLabel.setForeground(new Color(75, 0, 130)); // Índigo escuro para contrastar com o rosa
        mensagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(mensagemLabel);
        
        // Adiciona espaço
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Adiciona o GIF do gatinho na tela inicial
        if (gifGatinho != null) {
            imagemGatinhoLabel = new JLabel(gifGatinho);
            imagemGatinhoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelConteudo.add(imagemGatinhoLabel);
            
            // Adiciona espaço
            painelConteudo.add(Box.createRigidArea(new Dimension(0, 30)));
        }
        
        // Adiciona o botão
        botaoDesculpa = new JButton("Clique aqui para ver algo especial");
        botaoDesculpa.setFont(new Font("Arial", Font.BOLD, 25));
        botaoDesculpa.setBackground(new Color(255, 105, 180)); // Rosa quente
        botaoDesculpa.setForeground(Color.BLACK);
        botaoDesculpa.setFocusPainted(false);
        botaoDesculpa.setBorderPainted(false);
        botaoDesculpa.setOpaque(true);
        botaoDesculpa.setContentAreaFilled(true);
        botaoDesculpa.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoDesculpa.setMaximumSize(new Dimension(600, 60));
        
        // Adiciona efeito hover ao botão
        botaoDesculpa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                botaoDesculpa.setBackground(new Color(255, 20, 147)); // Rosa mais escuro ao passar o mouse
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                botaoDesculpa.setBackground(new Color(255, 105, 180)); // Volta para rosa quente
            }
        });
        
        botaoDesculpa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMensagemCompleta();
            }
        });
        
        painelConteudo.add(botaoDesculpa);
        
        // Adiciona o painel de conteúdo à janela
        add(painelConteudo, BorderLayout.CENTER);
    }
    
    private void mostrarMensagemCompleta() {
        // Limpa o painel de conteúdo
        painelConteudo.removeAll();
        
        // Adiciona a imagem do coração
        if (imagemCoracao != null) {
            Image imagemRedimensionada = imagemCoracao.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imagemLabel = new JLabel(new ImageIcon(imagemRedimensionada));
            imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelConteudo.add(imagemLabel);
            
            // Adiciona espaço
            painelConteudo.add(Box.createRigidArea(new Dimension(0, 30)));
        }
        
        // Adiciona a mensagem
        JTextArea areaTexto = new JTextArea();
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 24));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setEditable(false);
        areaTexto.setBackground(new Color(255, 192, 203)); // Rosa claro
        areaTexto.setForeground(new Color(75, 0, 130)); // Índigo escuro para contrastar com o rosa
        
        // Definindo o texto com quebras de linha explícitas
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("vih me desculpa por n te responder.\n\n");
        mensagem.append("Você é uma pessoa especial e importante para mim.\n\n");
        mensagem.append("fiz isso como pedido de desculpa.\n\n");
        mensagem.append("te amo , vih   <3    \n");
      
        
        areaTexto.setText(mensagem.toString());
        
        // Configurando o tamanho e alinhamento
        areaTexto.setMaximumSize(new Dimension(700, 400));
        areaTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Adicionando a área de texto ao painel
        painelConteudo.add(areaTexto);
        
        // Atualiza a interface
        painelConteudo.revalidate();
        painelConteudo.repaint();
        
        // Força a atualização da interface
        SwingUtilities.invokeLater(() -> {
            areaTexto.setCaretPosition(0);
            areaTexto.requestFocus();
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                DesculpaParaVih app = new DesculpaParaVih();
                app.setVisible(true);
            }
        });
    }
}