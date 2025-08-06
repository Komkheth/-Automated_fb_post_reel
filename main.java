import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ReelScheduler extends JFrame {
    // UI Components
    private JButton uploadButton, generateButton, scheduleButton;
    private JTextArea captionArea;
    private JList<String> videoList;
    private JProgressBar progressBar;
    private JLabel statusLabel, copyrightLabel;
    private JComboBox<String> pageSelector;
    private JSpinner dateSpinner;
    
    // Data
    private DefaultListModel<String> videoListModel;
    private String[] facebookPages = {"Tech Innovations", "Marketing Pro", "Travel Adventures"};
    
    public ReelScheduler() {
        setTitle("Java Reel Scheduler for Facebook");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize components
        initComponents();
        
        // Layout
        createLayout();
        
        // Set window icon
        ImageIcon icon = new ImageIcon(getClass().getResource("icon.png"));
        if (icon.getImage() != null) {
            setIconImage(icon.getImage());
        }
    }
    
    private void initComponents() {
        // Upload button
        uploadButton = new JButton("Upload Videos");
        uploadButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        uploadButton.setBackground(new Color(24, 119, 242));
        uploadButton.setForeground(Color.WHITE);
        uploadButton.addActionListener(e -> uploadVideos());
        
        // Generate caption button
        generateButton = new JButton("Generate Caption");
        generateButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        generateButton.setBackground(new Color(255, 122, 0));
        generateButton.setForeground(Color.WHITE);
        generateButton.addActionListener(e -> generateCaption());
        
        // Schedule button
        scheduleButton = new JButton("Schedule Reels");
        scheduleButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        scheduleButton.setBackground(new Color(49, 162, 76));
        scheduleButton.setForeground(Color.WHITE);
        scheduleButton.addActionListener(e -> scheduleReels());
        
        // Caption area
        captionArea = new JTextArea();
        captionArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        captionArea.setLineWrap(true);
        captionArea.setWrapStyleWord(true);
        captionArea.setBorder(BorderFactory.createTitledBorder("Reel Caption"));
        
        // Video list
        videoListModel = new DefaultListModel<>();
        videoList = new JList<>(videoListModel);
        videoList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        videoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        videoList.setBorder(BorderFactory.createTitledBorder("Uploaded Videos"));
        
        // Progress bar
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);
        
        // Status label
        statusLabel = new JLabel("Ready to upload videos");
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        // Copyright label
        copyrightLabel = new JLabel("Copyright status: Not scanned");
        copyrightLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        // Page selector
        pageSelector = new JComboBox<>(facebookPages);
        pageSelector.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        // Date spinner
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd HH:mm"));
    }
    
    private void createLayout() {
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Top panel with title
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Java Reel Scheduler for Facebook");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(new Color(24, 119, 242));
        topPanel.add(titleLabel, BorderLayout.WEST);
        
        // Facebook logo
        JLabel fbLogo = new JLabel("f");
        fbLogo.setFont(new Font("Arial", Font.BOLD, 24));
        fbLogo.setForeground(new Color(24, 119, 242));
        topPanel.add(fbLogo, BorderLayout.EAST);
        
        // Center panel with split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(300);
        
        // Left panel for videos and actions
        JPanel leftPanel = new JPanel(new BorderLayout(10, 10));
        
        // Upload section
        JPanel uploadPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        uploadPanel.setBorder(BorderFactory.createTitledBorder("Video Upload"));
        uploadPanel.add(uploadButton);
        
        // Settings panel
        JPanel settingsPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        settingsPanel.setBorder(BorderFactory.createTitledBorder("Scheduling Settings"));
        settingsPanel.add(new JLabel("Facebook Page:"));
        settingsPanel.add(pageSelector);
        settingsPanel.add(new JLabel("Schedule Time:"));
        settingsPanel.add(dateSpinner);
        
        leftPanel.add(uploadPanel, BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(videoList), BorderLayout.CENTER);
        leftPanel.add(settingsPanel, BorderLayout.SOUTH);
        
        // Right panel for caption and actions
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        
        // Caption panel
        JPanel captionPanel = new JPanel(new BorderLayout());
        captionPanel.add(new JScrollPane(captionArea), BorderLayout.CENTER);
        
        // Action buttons
        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        actionPanel.add(generateButton);
        actionPanel.add(scheduleButton);
        
        // Status panel
        JPanel statusPanel = new JPanel(new BorderLayout(5, 5));
        statusPanel.add(progressBar, BorderLayout.CENTER);
        statusPanel.add(statusLabel, BorderLayout.SOUTH);
        
        // Copyright panel
        JPanel copyrightPanel = new JPanel(new BorderLayout());
        copyrightPanel.add(copyrightLabel, BorderLayout.CENTER);
        
        rightPanel.add(captionPanel, BorderLayout.CENTER);
        rightPanel.add(actionPanel, BorderLayout.SOUTH);
        rightPanel.add(statusPanel, BorderLayout.NORTH);
        rightPanel.add(copyrightPanel, BorderLayout.SOUTH);
        
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(splitPane, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private void uploadVideos() {
        // Simulate video upload
        statusLabel.setText("Uploading videos...");
        progressBar.setVisible(true);
        progressBar.setValue(0);
        
        // Start a thread to simulate upload progress
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(30);
                    progressBar.setValue(i);
                }
                
                // Add sample videos
                videoListModel.clear();
                videoListModel.addElement("Product Launch.mp4");
                videoListModel.addElement("Customer Review.mp4");
                videoListModel.addElement("Behind the Scenes.mp4");
                
                statusLabel.setText("3 videos uploaded successfully!");
                
                // Simulate copyright scan
                copyrightLabel.setText("Scanning for copyright issues...");
                Thread.sleep(1500);
                copyrightLabel.setText("Copyright status: Clean - No issues found");
                copyrightLabel.setForeground(new Color(49, 162, 76));
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    private void generateCaption() {
        String[] captions = {
            "Just launched our new product! 🚀 After months of hard work, we're thrilled to share this innovation with you. " +
            "#TechInnovation #ProductLaunch #BehindTheScenes",
            
            "Check out our latest creation! This product is a game changer in the industry. " +
            "#Innovation #TechUpdate #MustWatch",
            
            "We're excited to share this behind-the-scenes look at our development process! " +
            "#TechLovers #DevelopmentJourney #NewProduct"
        };
        
        Random rand = new Random();
        captionArea.setText(captions[rand.nextInt(captions.length)]);
        statusLabel.setText("AI-generated caption created!");
    }
    
    private void scheduleReels() {
        if (videoListModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please upload videos first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String selectedPage = (String) pageSelector.getSelectedItem();
        String scheduledDate = ((JSpinner.DateEditor) dateSpinner.getEditor()).getFormat().format(dateSpinner.getValue());
        
        // Show confirmation
        int response = JOptionPane.showConfirmDialog(
            this, 
            "Schedule " + videoListModel.size() + " reels for " + selectedPage + " at " + scheduledDate + "?",
            "Confirm Schedule",
            JOptionPane.YES_NO_OPTION
        );
        
        if (response == JOptionPane.YES_OPTION) {
            // Simulate scheduling
            statusLabel.setText("Scheduling reels...");
            progressBar.setVisible(true);
            progressBar.setValue(0);
            
            new Thread(() -> {
                try {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(20);
                        progressBar.setValue(i);
                    }
                    
                    statusLabel.setText(videoListModel.size() + " reels scheduled for " + scheduledDate);
                    
                    // Reset after scheduling
                    Thread.sleep(3000);
                    videoListModel.clear();
                    captionArea.setText("");
                    progressBar.setVisible(false);
                    copyrightLabel.setText("Copyright status: Not scanned");
                    copyrightLabel.setForeground(Color.BLACK);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    
    public static void main(String[] args) {
        // Set look and feel for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            ReelScheduler scheduler = new ReelScheduler();
            scheduler.setVisible(true);
        });
    }
}
